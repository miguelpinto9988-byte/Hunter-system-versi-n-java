private class Metadata(engine: dynamic, private val training: dynamic) {
    private val objectType: dynamic = js("Object")
    private val arrayType: dynamic = js("Array")
    private val booleanType: dynamic = js("Boolean")
    private val numberType: dynamic = js("Number")
    private val stringType: dynamic = js("String")
    private val catalog: dynamic = js("new Map()")
    private val strengthPatterns = setOf("empuje_horizontal", "traccion_horizontal", "empuje_vertical", "bisagra_cadera", "rodilla")
    private val musclePatterns = setOf("flexion_codo", "extension_codo", "elevacion_hombro", "empuje_horizontal", "traccion_horizontal")

    init {
        val exercises = engine.exercises
        for (index in 0 until (exercises.length as Int)) {
            val exercise = exercises[index]
            if (jsTypeOf(exercise?.id) != "string" || exercise.id === "") continue
            val fields = js("({})")
            fields.group = jsTypeOf(exercise.group) == "string" && truth(exercise.group.trim())
            fields.category = (exercise.category as? String) in setOf("Fuerza", "Cardio", "Movilidad")
            fields.equipment = jsTypeOf(exercise.equipment) == "string" && truth(exercise.equipment)
            fields.zones = (arrayType.isArray(exercise.zones) as Boolean) && (exercise.zones.length as Int) > 0
            fields.requirements = arrayType.isArray(exercise.requirements)
            fields.level = numberType.isFinite(exercise.level)
            fields.demo = jsTypeOf(exercise.demoPath30) == "string" && jsTypeOf(exercise.modelPath30) == "string"
            fields.pattern = truth(pattern(exercise))
            val record = js("({})")
            record.id = exercise.id
            record.fields = objectType.freeze(fields)
            record.usable = fields.group as Boolean && fields.category as Boolean && fields.equipment as Boolean &&
                fields.zones as Boolean && fields.requirements as Boolean && fields.level as Boolean && fields.demo as Boolean
            record.pattern = if (fields.pattern as Boolean) pattern(exercise) else null
            catalog.set(exercise.id, objectType.freeze(record))
        }
    }

    private fun truth(value: dynamic): Boolean = booleanType(value) as Boolean

    private fun pattern(exercise: dynamic): dynamic {
        val value = if (training?.pattern == null) null else training.pattern(exercise)
        return if (truth(value)) value else null
    }

    private fun goal(profile: dynamic): String =
        (stringType(if (truth(profile?.goal)) profile.goal else "") as String).lowercase()

    private fun lookup(id: dynamic): dynamic = catalog.get(id) ?: null

    private fun analysis(): dynamic {
        val result = js("({version:'38.12',total:0,eligible:0,patternKnown:0,missing:{}})")
        result.total = catalog.size
        val visit: (dynamic) -> Unit = { row ->
            if (row.usable as Boolean) {
                result.eligible++
                if (truth(row.pattern)) result.patternKnown++
            }
            val keys = objectType.keys(row.fields)
            for (i in 0 until (keys.length as Int)) {
                val key = keys[i]
                if (!(row.fields[key] as Boolean)) {
                    val current = result.missing[key]
                    result.missing[key] = if (current == null) 1 else current + 1
                }
            }
        }
        catalog.forEach(visit)
        result.note = "Metadatos del catálogo: no constituyen verificación clínica ni técnica de cada ejercicio."
        return objectType.freeze(result)
    }

    private fun score(exercise: dynamic, profile: dynamic, kind: dynamic): Int {
        val record = lookup(exercise?.id)
        if (record == null || !(record.usable as Boolean)) return 0
        val pattern = record.pattern as? String
        val goal = goal(profile)
        return when {
            kind === "strength" && goal == "strength" && pattern in strengthPatterns -> 6
            kind === "strength" && goal == "muscle" && pattern in musclePatterns -> 4
            kind === "cardio" && goal == "endurance" && record.fields.pattern as Boolean -> 2
            else -> 0
        }
    }

    private fun hasRestrictions(profile: dynamic): Boolean {
        val injuries = profile?.injuries
        val active: (dynamic) -> Boolean = { injury -> truth(injury) && injury.stage !== "past" }
        val health = profile?.health
        return (injuries?.some != null && truth(injuries.some(active))) ||
            truth(health?.special) || truth(health?.acute) ||
            (truth(health?.pain) && health.pain !== "none") || truth(profile?.limits)
    }

    private fun safeNumber(value: dynamic, fallback: Double, min: Double, max: Double): Double {
        val number = numberType(value) as Double
        return if (number.isFinite()) number.coerceIn(min, max) else fallback
    }

    private fun dose(exercise: dynamic, profile: dynamic, kind: dynamic, status: dynamic, feedback: dynamic,
                     date: dynamic, current: dynamic): dynamic {
        val result = objectType.assign(js("({})"), current)
        if (!truth(lookup(exercise?.id)?.usable) || truth(status?.reduced) || truth(feedback?.strain) ||
            hasRestrictions(profile) || kind !== "strength" || exercise.unit === "sec") return result
        val effort = safeNumber(feedback?.avg, 0.0, 0.0, 10.0)
        val progress = feedback?.canProgress === true && effort > 0.0 && effort <= 7.0
        when (goal(profile)) {
            "strength" -> {
                result.target = safeNumber(result.target, 8.0, 1.0, 30.0).coerceIn(6.0, 10.0)
                result.rest = safeNumber(result.rest, 90.0, 0.0, 300.0).coerceAtLeast(90.0)
            }
            "muscle" -> {
                result.target = safeNumber(result.target, 8.0, 1.0, 30.0).coerceIn(8.0, 12.0)
                result.rest = safeNumber(result.rest, 90.0, 0.0, 300.0).coerceAtLeast(60.0)
            }
            "endurance" -> if (progress) {
                result.target = (safeNumber(result.target, 8.0, 1.0, 30.0) + 1).coerceAtMost(15.0)
            }
        }
        result.sets = safeNumber(result.sets, 1.0, 1.0, 6.0).coerceAtMost(3.0)
        return result
    }

    fun api(): dynamic {
        val result = js("({version:'38.12'})")
        result.analysis = ::analysis
        result.metadata = ::lookup
        result.score = ::score
        result.dose = ::dose
        return objectType.freeze(result)
    }
}

fun main() {
    val root: dynamic = js("typeof window === 'undefined' ? globalThis : window")
    val truth: dynamic = js("Boolean")
    val array: dynamic = js("Array")
    val engine = root.Engine
    if (truth(root.HunterMetadata3812) as Boolean || !(truth(engine) as Boolean) ||
        !(array.isArray(engine.exercises) as Boolean)) return
    root.HunterMetadata3812 = Metadata(engine, root.HunterTraining387).api()
}
