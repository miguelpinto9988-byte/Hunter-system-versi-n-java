private class Constraints(private val planner: dynamic, private val engine: dynamic) {
    private val jsArray: dynamic = js("Array")
    private val jsObject: dynamic = js("Object")
    private val jsString: dynamic = js("String")
    private val jsNumber: dynamic = js("Number")
    private val jsDate: dynamic = js("Date")
    private val jsBoolean: dynamic = js("Boolean")
    private val datePattern: dynamic = js("/^\\d{4}-\\d{2}-\\d{2}$/")
    private val lower = setOf("hip", "knee", "ankle")
    private val upper = setOf("neck", "shoulder", "elbow", "wrist")
    private val injuryZones = lower + upper + "back"
    private val bodyParts = setOf("Piernas", "Cadera", "Brazos", "Hombros", "Espalda", "Pecho", "Core", "Abdomen")
    private val supportGroups = setOf("Pecho", "Hombros", "Core", "Abdomen")
    private val upperGroups = setOf("Brazos", "Pecho", "Espalda", "Hombros")
    private val lowerGroups = setOf("Piernas", "Cadera")
    private val restrictionKeys = listOf("noImpact", "noFloor", "noOverhead", "noKnee", "noWrist", "noLoad", "seatedOnly", "noSpine")

    private fun truth(value: dynamic): Boolean = jsBoolean(value) as Boolean

    private fun array(value: dynamic): dynamic = if (jsArray.isArray(value) as Boolean) value else js("[]")

    private fun zones(profile: dynamic): Set<String> {
        val result = linkedSetOf<String>()
        val injuries = array(profile?.injuries)
        for (i in 0 until (injuries.length as Int)) {
            val injury = injuries[i]
            if (!truth(injury) || injury.stage === "past") continue
            result.add(jsString(if (truth(injury.zone)) injury.zone else "") as String)
        }
        return result
    }

    private fun activeZones(profile: dynamic): Array<String> = zones(profile).toTypedArray()

    private fun restrictions(profile: dynamic): dynamic {
        val result = js("({})")
        val source = profile?.restrictions
        for (key in restrictionKeys) result[key] = source != null && truth(source[key])
        return result
    }

    private fun attributes(exercise: dynamic): dynamic {
        val facts = js("({})")
        val floor = exercise?.floor === true
        val seated = exercise?.seated === true
        val standing = exercise?.standing === true
        facts.floor = floor
        facts.seated = seated
        facts.standing = standing
        facts.stance = when {
            seated -> "sentado"
            floor -> "suelo"
            standing -> "de pie"
            else -> "no especificada"
        }
        facts.handSupport = exercise?.wristLoad === true
        facts.overhead = exercise?.overhead === true
        facts.externalLoad = exercise?.equipment === "db" || exercise?.equipment === "gym" ||
            array(exercise?.requirements).includes("db") as Boolean
        facts.impact = exercise?.impact === "Con saltos"
        facts.kneeBend = exercise?.kneeBend === true
        facts.spineMove = exercise?.spineMove === true
        facts.metadataComplete = (array(exercise?.zones).length as Int) > 0 && truth(exercise?.category) && truth(exercise?.group)
        return facts
    }

    private fun review(exercise: dynamic, profile: dynamic): dynamic {
        val facts = attributes(exercise)
        val result = js("({allowed:false,flags:[],facts:null})")
        result.facts = facts
        val flags = mutableListOf<String>()
        if (!truth(exercise) || !truth(profile) || jsTypeOf(exercise.id) != "string" || !truth(exercise.id)) {
            flags.add("sin_datos")
        } else {
            val affected = zones(profile)
            val restrictions = restrictions(profile)
            val support = facts.handSupport as Boolean ||
                (facts.floor as Boolean && !(facts.seated as Boolean) && supportGroups.contains(exercise.group as? String))
            if (!(facts.metadataComplete as Boolean)) flags.add("metadatos_insuficientes")
            if (affected.any { it !in injuryZones }) flags.add("zona_desconocida")
            if (affected.any { array(exercise.zones).includes(it) as Boolean }) flags.add("zona_implicada")
            if (restrictions.seatedOnly as Boolean && !(facts.seated as Boolean)) flags.add("requiere_sentado")
            if (restrictions.noFloor as Boolean && facts.floor as Boolean) flags.add("evitar_suelo")
            if (restrictions.noLoad as Boolean && facts.externalLoad as Boolean) flags.add("evitar_carga")
            if (restrictions.noImpact as Boolean && facts.impact as Boolean) flags.add("evitar_impacto")
            if (restrictions.noOverhead as Boolean && facts.overhead as Boolean) flags.add("evitar_sobrecabeza")
            if (restrictions.noKnee as Boolean && facts.kneeBend as Boolean) flags.add("evitar_flexion_rodilla")
            if (restrictions.noSpine as Boolean && facts.spineMove as Boolean) flags.add("evitar_giros_tronco")
            if (restrictions.noWrist as Boolean && support) flags.add("evitar_apoyo_manos")
            if ("wrist" in affected && support) flags.add("muñeca_en_apoyo")
            if ("knee" in affected && facts.floor as Boolean && facts.kneeBend as Boolean) flags.add("rodilla_en_apoyo")
            if ("ankle" in affected && facts.standing as Boolean) flags.add("tobillo_en_apoyo")
            if ("back" in affected && (facts.spineMove as Boolean ||
                    facts.externalLoad as Boolean && facts.standing as Boolean)) flags.add("carga_tronco")
            if ("shoulder" in affected && facts.overhead as Boolean) flags.add("carga_hombro")
        }
        result.allowed = flags.isEmpty()
        result.flags = flags.toTypedArray()
        return result
    }

    private fun allowed(exercise: dynamic, profile: dynamic): Boolean {
        if (!truth(exercise) || !truth(profile) || !truth(planner.status(profile).ok)) return false
        val approved = try {
            truth(planner.approved(exercise, profile))
        } catch (_: dynamic) {
            return false
        }
        return approved && review(exercise, profile).allowed as Boolean
    }

    private fun rank(exercise: dynamic, profile: dynamic): Int {
        if (!truth(exercise) || !truth(profile)) return 0
        val affected = zones(profile)
        if (affected.isEmpty()) return 0
        val facts = attributes(exercise)
        val group = exercise.group as? String
        var score = 0
        if (affected.any { it in lower }) {
            if (facts.seated as Boolean) score += 12
            if (group in upperGroups) score += 4
        }
        if (affected.any { it in upper } && group in lowerGroups) score += 6
        if ("back" in affected && facts.seated as Boolean) score += 5
        if (facts.impact as Boolean || facts.externalLoad as Boolean) score -= 4
        return score
    }

    private fun symptomTrend(logs: dynamic, day: dynamic): dynamic {
        val result = js("({days:0,recentSymptom:false})")
        val start = jsDate.parse(jsString(day) + "T12:00:00Z") as Double
        if (!start.isFinite() || !truth(logs)) return result
        val entries = jsObject.entries(logs)
        for (i in 0 until (entries.length as Int)) {
            val entry = entries[i]
            val date = entry[0]
            val log = entry[1]
            if (!(datePattern.test(date) as Boolean) || !truth(log)) continue
            val time = jsDate.parse(date + "T12:00:00Z") as Double
            val delta = (start - time) / 86400000.0
            if (!delta.isFinite() || delta <= 0.0 || delta > 14.0) continue
            if (truth(log.pain) || truth(log.fatigue) || (jsNumber(log.rpe) as Double) >= 8.0) {
                result.days++
                result.recentSymptom = true
            }
        }
        return result
    }

    private fun count(record: dynamic, key: dynamic) {
        val current = record[key]
        record[key] = if (truth(current)) current + 1 else 1
    }

    private fun summary(profile: dynamic): dynamic {
        val total = js("({examined:0,admissible:0,blocked:0,groups:{},categories:{},restrictions:{}})")
        total.requiresReview = !truth(profile) || !truth(planner.status(profile).ok)
        if (total.requiresReview as Boolean) return total
        val exercises = array(engine.exercises)
        for (i in 0 until (exercises.length as Int)) {
            val exercise = exercises[i]
            if (exercise?.variant !== "base" || truth(exercise.sourceOnly)) continue
            total.examined++
            if (allowed(exercise, profile)) {
                total.admissible++
                if ((exercise.group as? String) in bodyParts) count(total.groups, exercise.group)
                if (truth(exercise.category)) count(total.categories, exercise.category)
            } else {
                total.blocked++
                val flags = review(exercise, profile).flags
                for (index in 0 until (flags.length as Int)) count(total.restrictions, flags[index])
            }
        }
        total.note = "Estadísticas de etiquetas; no certifican que un ejercicio sea adecuado para una lesión."
        return total
    }

    fun api(): dynamic {
        val result = js("({version:'38.11'})")
        result.attributes = ::attributes
        result.activeZones = ::activeZones
        result.constraints = ::restrictions
        result.review = ::review
        result.allowed = ::allowed
        result.rank = ::rank
        result.summary = ::summary
        result.symptomTrend = ::symptomTrend
        return jsObject.freeze(result)
    }
}

fun main() {
    val root: dynamic = js("typeof window === 'undefined' ? globalThis : window")
    val truth: dynamic = js("Boolean")
    if (truth(root.HunterConstraints3811) as Boolean || !(truth(root.Adapt) as Boolean) ||
        !(truth(root.HunterPlanner379) as Boolean) || !(truth(root.Engine) as Boolean)) return
    root.HunterConstraints3811 = Constraints(root.HunterPlanner379, root.Engine).api()
}
