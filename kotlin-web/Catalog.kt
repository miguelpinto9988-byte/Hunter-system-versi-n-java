private class Catalog(private val root: dynamic, private val data: dynamic) {
    private val engine = root.Engine
    private val adapt = root.Adapt
    private val obj: dynamic = js("Object")
    private val array: dynamic = js("Array")
    private val boolean: dynamic = js("Boolean")
    private val number: dynamic = js("Number")
    private val string: dynamic = js("String")
    private val ids: dynamic = js("new Set()")
    private val groups: dynamic = js("({upper:'Tren superior',lower:'Tren inferior',core:'Zona media',cardio:'Cardio y agilidad',mobility:'Movilidad'})")
    private val partitions: dynamic = js("new Map()")
    private val dayPattern: dynamic = js("/^\\d{4}-\\d{2}-\\d{2}$/")

    private fun truth(value: dynamic): Boolean = boolean(value) as Boolean
    private fun or(value: dynamic, fallback: dynamic): dynamic = if (truth(value)) value else fallback
    private fun numeric(value: dynamic, fallback: Double): Double {
        val result = number(value) as Double
        return if (result == 0.0 || result.isNaN()) fallback else result
    }

    private fun active(profile: dynamic): dynamic {
        val current: (dynamic) -> Boolean = { truth(it) && it.stage !== "past" }
        if (truth(or(profile?.injuries, js("[]")).some(current))) return true
        if (truth(profile?.limits)) return profile.limits
        val health = profile?.health
        for (key in listOf("special", "acute", "redFlags")) {
            if (health != null && truth(health[key])) return health[key]
        }
        val pain = if (health == null) js("undefined") else health.pain
        return if (truth(pain)) pain !== "none" else pain
    }

    private fun reasons(exercise: dynamic, profile: dynamic): dynamic {
        if (!truth(exercise) || !truth(profile)) return arrayOf("Completa el perfil")
        val result = js("[]")
        if (!truth(exercise.pack46) || !truth(exercise.materials46)) return result
        val maxLevel = maxOf(0, listOf("basic", "intermediate", "advanced").indexOf(profile.difficulty as? String))
        if (exercise.level > maxLevel) result.push("Nivel superior al seleccionado")
        val materials = if (array.isArray(profile.materials46) as Boolean) profile.materials46 else js("[]")
        val skills = if (array.isArray(profile.capabilities46) as Boolean) profile.capabilities46 else js("[]")
        val gym = or(profile.gymEquipment23, js("[]"))
        val requested = exercise.materials46
        for (index in 0 until (requested.length as Int)) {
            val token = requested[index]
            val has = when (token) {
                "db" -> profile.equipment === "db" || profile.equipment === "gym"
                "bench" -> profile.bench === true || gym.includes("bench") as Boolean
                "step" -> profile.step === true
                "barbell", "cable" -> profile.equipment === "gym" && gym.includes(token) as Boolean
                else -> materials.includes(token) as Boolean
            }
            if (!has) result.push("Falta material: " + (string(data.materialLabels[token]) as String))
        }
        val needed = or(exercise.capabilities46, js("[]"))
        for (index in 0 until (needed.length as Int)) {
            val token = needed[index]
            if (!(skills.includes(token) as Boolean)) result.push("Capacidad previa pendiente: " + (string(data.capabilityLabels[token]) as String))
        }
        if (truth(exercise.highSkill46) && ((number(profile.age) as Double) < 18 || truth(active(profile)))) {
            result.push("Movimiento complejo: revisar capacidad y restricciones individuales antes de asignar")
        }
        if (truth(exercise.loaded46) && truth(profile.restrictions?.noLoad)) result.push("Carga o resistencia externa excluida")
        return result
    }

    private fun cap(exercise: dynamic, profile: dynamic, item: dynamic, rawContext: dynamic): dynamic {
        val caps = exercise?.doseCap46
        if (!truth(caps)) return item
        val context = if (jsTypeOf(rawContext) == "undefined") js("({})") else rawContext
        val scale = numeric(profile?.trainingScale, 1.0).coerceIn(0.25, 1.0)
        var risky = truth(active(profile)) || truth(context.status?.reduced) || truth(context.feedback?.strain)
        val logs = or(context.logs, js("({})"))
        val date = if (jsTypeOf(context.date) == "string" && dayPattern.test(context.date) as Boolean) context.date else engine.dateKey()
        val dates = obj.keys(logs)
        val sortDates: (dynamic, dynamic) -> Int = { a, b -> a.localeCompare(b) as Int }
        dates.sort(sortDates)
        var count = 0
        var last: dynamic = null
        for (index in 0 until (dates.length as Int)) {
            val day = dates[index]
            if (day >= date) continue
            val log = logs[day]
            val effort = number(log?.rpe) as Double
            if (day >= engine["plus"](date, -14) && (truth(log?.pain) || truth(log?.fatigue) || effort >= 8)) risky = true
            if (!truth(log?.complete) || truth(log.manual) || truth(log.pain) || truth(log.fatigue) || effort !in 1.0..7.0) continue
            val items = or(log.items, js("[]"))
            for (i in 0 until (items.length as Int)) {
                val record = items[i]
                if (record.id !== exercise.id || record.actual == null || !(record.actual.length >= record.sets)) continue
                val achieved: (dynamic) -> Boolean = { it.done === true && number(it.value) >= number(record.target) }
                if (record.actual.every(achieved) as Boolean) {
                    count++
                    last = record
                }
            }
        }
        val threshold = if (truth(exercise.highSkill46)) 3 else 2
        val progress = context.feedback?.canProgress === true && !risky && count >= threshold
        val start = maxOf(1.0, kotlin.math.floor((number(caps.initial) as Double) * scale))
        val maxTarget = maxOf(1.0, kotlin.math.floor((number(caps.target) as Double) * scale))
        val nextTarget = if (progress) maxOf(start, numeric(last.target, start)) + (if (exercise.unit === "sec") 5 else 1) else start
        val out = obj.assign(js("({})"), item)
        out.target = maxOf(1.0, minOf(numeric(item.target, start), maxTarget, nextTarget))
        out.sets = maxOf(1.0, minOf(numeric(item.sets, 1.0), if (risky) 1.0 else number(caps.sets) as Double))
        out.rest = minOf(90.0, maxOf(number(caps.rest) as Double, numeric(item.rest, 0.0)))
        out.catalog46 = true
        out.noAutoLoad = truth(out.noAutoLoad) || risky || truth(exercise.highSkill46)
        val notes = js("[]")
        if (truth(out.safetyNote)) notes.push(out.safetyNote)
        val restrictions = exercise.restrictions46?.join(" ")
        if (truth(restrictions)) notes.push(restrictions)
        out.safetyNote = notes.join(" ")
        return out
    }

    private fun domain(exercise: dynamic): dynamic {
        if (truth(exercise.domain46)) return exercise.domain46
        return when (exercise.category) {
            "Cardio" -> "cardio"
            "Movilidad" -> "mobility"
            else -> when (exercise.group) {
                "Core", "Abdomen" -> "core"
                "Piernas", "Cadera" -> "lower"
                else -> "upper"
            }
        }
    }

    private fun registerAdapters() {
        val prior = root.HunterMaterials3814
        val materials = obj.assign(js("({})"), prior)
        val eligible: (dynamic, dynamic) -> dynamic = { e, p ->
            val accepted = prior.eligible(e, p)
            if (truth(accepted)) reasons(e, p).length === 0 else accepted
        }
        val requirements: (dynamic) -> dynamic = { prior.requirements(it) }
        val additional: (dynamic) -> dynamic = { or(it?.materials46, js("[]")) }
        materials.version = "38.46"
        materials.eligible = eligible
        materials.requirements = requirements
        materials.additional = additional
        root.HunterMaterials3814 = obj.freeze(materials)

        val routine = adapt.routineCompatible
        val allowed = adapt.allowed
        val why = adapt.reasons
        // Forward the original receiver and optional arguments across the JavaScript API.
        val forward: dynamic = js("(function(callback){return function(){return callback(this, arguments)}})")
        val compatible: (dynamic, dynamic) -> dynamic = { receiver, args ->
            val itemAllowed: (dynamic) -> Boolean = { reasons(engine.byId(it.id), args[1]).length === 0 }
            if (truth(args[0]?.items?.every(itemAllowed))) routine.apply(receiver, args) else false
        }
        val check: (dynamic, dynamic) -> dynamic = { receiver, args ->
            if (reasons(args[0], args[1]).length === 0) allowed.apply(receiver, args) else false
        }
        val explain: (dynamic, dynamic) -> dynamic = { receiver, args ->
            val merged = js("new Set()")
            val priorReasons = array.from(why.apply(receiver, args))
            val extra = reasons(args[0], args[1])
            for (index in 0 until (priorReasons.length as Int)) merged.add(priorReasons[index])
            for (index in 0 until (extra.length as Int)) merged.add(extra[index])
            array.from(merged)
        }
        adapt.routineCompatible = forward(compatible)
        adapt.allowed = forward(check)
        adapt.reasons = forward(explain)
    }

    fun install() {
        if (!truth(engine) || !truth(adapt)) throw IllegalStateException("38.46: catálogo base no disponible")
        val exercises = engine.exercises
        val rows = data.rows
        for (index in 0 until (rows.length as Int)) {
            val row = rows[index]
            val duplicate: (dynamic) -> Boolean = { it.id === row.id }
            if (exercises.some(duplicate) as Boolean) throw IllegalStateException("ID duplicado ${row.id}")
            ids.add(row.id)
            exercises.push(row)
        }
        for (index in 0 until (data.gaps.length as Int)) {
            val meta = data.gaps[index]
            val exercise = engine.byId(meta.id)
            if (!truth(exercise)) throw IllegalStateException("Falta ficha anterior ${meta.id}")
            for (key in listOf("demoPath30", "modelPath30", "view21", "identity46", "motion46", "domain46")) exercise[key] = meta[key]
            exercise.pack46 = true
            exercise.noVector379 = false
            ids.add(exercise.id)
        }
        registerAdapters()
        for (key in listOf("upper", "lower", "core", "cardio", "mobility")) partitions.set(key, js("[]"))
        for (index in 0 until (exercises.length as Int)) {
            val exercise = exercises[index]
            partitions.get(domain(exercise))?.push(exercise)
        }
        val owned: (dynamic) -> Boolean = { ids.has(if (jsTypeOf(it) == "string") it else it?.id) as Boolean }
        val byGroup: (dynamic) -> dynamic = { or(partitions.get(it), js("[]")).slice() }
        val idOf: (dynamic) -> dynamic = { it.id }
        val api = js("({version:'38.46'})")
        api.owned = owned
        api.byGroup = byGroup
        api.ids = array.from(ids)
        api.newIds = rows.map(idOf)
        api.materials = data.materialLabels
        api.capabilities = data.capabilityLabels
        api.reasons = ::reasons
        api.cap = ::cap
        api.active = ::active
        api.groups = groups
        root.HunterCatalog46 = obj.freeze(api)
        if (exercises.length !== 1200) throw IllegalStateException("38.46: se esperaban 1200 fichas; recibidas ${exercises.length}")
    }
}

fun main() {
    Catalog(js("window"), catalogPayload()).install()
}
