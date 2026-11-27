private class Planning(private val root: dynamic) {
    private val training = root.HunterTraining387
    private val engine = root.Engine
    private val truth: dynamic = js("Boolean")
    private val number: dynamic = js("Number")
    private val string: dynamic = js("String")
    private val array: dynamic = js("Array")
    private val dateType: dynamic = js("Date")
    private val math: dynamic = js("Math")
    private val dayPattern: dynamic = js("/^\\d{4}-\\d{2}-\\d{2}$/")

    private fun present(value: dynamic): Boolean = truth(value) as Boolean

    private fun validDay(value: dynamic): Boolean =
        dayPattern.test(string(if (present(value)) value else "")) as Boolean

    private fun difference(a: dynamic, b: dynamic): Double {
        if (!validDay(a) || !validDay(b)) return Double.POSITIVE_INFINITY
        val days = (dateType.parse(a + "T12:00:00Z") - dateType.parse(b + "T12:00:00Z")) / 86400000
        return math.abs(math.round(days)) as Double
    }

    private fun family(exercise: dynamic): String {
        for (key in listOf("baseId", "family", "id")) {
            val value = if (exercise == null) null else exercise[key]
            if (present(value)) return string(value) as String
        }
        return ""
    }

    private fun pattern(exercise: dynamic): dynamic {
        val value = if (training?.pattern == null) null else training.pattern(exercise)
        return if (present(value)) value else null
    }

    private fun row(date: dynamic, exercise: dynamic, sets: dynamic): dynamic {
        val result = js("({})")
        result.date = date
        result.group = if (present(exercise.group)) exercise.group else ""
        result.id = family(exercise)
        result.pattern = pattern(exercise)
        val count = number(sets) as Double
        result.sets = if (count.isFinite()) count.coerceIn(0.0, 6.0) else 0.0
        return result
    }

    private fun tracker(cycle: dynamic, logs: dynamic, today: dynamic): dynamic {
        val planned: dynamic = js("[]")
        val days = cycle?.days
        val start = if (jsTypeOf(today) == "undefined") "" else today
        if (array.isArray(days) as Boolean) {
            for (index in 0 until (days.length as Int)) {
                val day = days[index]
                if (!validDay(day?.date) || !(array.isArray(day.items) as Boolean) ||
                    (day.items.length as Int) == 0 || day.date < start) continue
                val recorded = if (logs == null) null else logs[day.date]
                val performed: (dynamic) -> Boolean = { item ->
                    val done: (dynamic) -> Boolean = { set -> present(set.done) }
                    present(item.actual?.some(done))
                }
                val taskDone: (dynamic) -> Boolean = { task -> present(task.done) }
                if (!(present(day.customized) || present(day.routineId) || present(day.creatorAssigned) ||
                        present(day.manual) || present(recorded?.complete) || present(recorded?.manual) ||
                        present(recorded?.pain) || present(recorded?.items?.some(performed)) ||
                        present(day.tasks?.some(taskDone)))) continue
                for (itemIndex in 0 until (day.items.length as Int)) {
                    val item = day.items[itemIndex]
                    val exercise = if (engine?.byId == null) null else engine.byId(item.id)
                    if (present(exercise)) planned.push(row(day.date, exercise, item.sets))
                }
            }
        }

        val api = js("({})")
        val score: (dynamic, dynamic) -> Double = { exercise, date ->
            var group = 0.0
            var movement = 0.0
            var repeats = 0
            val exercisePattern = pattern(exercise)
            val exerciseFamily = family(exercise)
            for (index in 0 until (planned.length as Int)) {
                val entry = planned[index]
                if (difference(entry.date, date) > 6) continue
                if (present(exercise?.group) && entry.group === exercise.group) group += entry.sets as Double
                if (present(exercisePattern) && entry.pattern === exercisePattern) movement += entry.sets as Double
                if (exerciseFamily.isNotEmpty() && entry.id === exerciseFamily) repeats++
            }
            val penalty = minOf(24.0, group * 1.25) + minOf(16.0, movement * 2) + minOf(24, repeats * 12)
            if (penalty == 0.0) 0.0 else -penalty
        }
        val add: (dynamic, dynamic, dynamic) -> Unit = { date, exercise, sets ->
            if (validDay(date) && present(exercise?.id)) planned.push(row(date, exercise, sets))
        }
        val copy: (dynamic) -> dynamic = { entry ->
            val objectType: dynamic = js("Object")
            objectType.assign(js("({})"), entry)
        }
        val snapshot: () -> dynamic = { planned.map(copy) }
        api.score = score
        api.add = add
        api.snapshot = snapshot
        return api
    }

    private fun increment(counts: dynamic, key: dynamic) {
        val count = counts[key]
        counts[key] = if (present(count)) count + 1 else 1
    }

    private fun stats(catalog: dynamic): dynamic {
        val items = if (array.isArray(catalog) as Boolean) catalog else engine?.exercises ?: js("[]")
        val result = js("({total:0,annotated:0,withModel:0,groups:{},categories:{},patterns:{}})")
        result.total = items.length
        for (index in 0 until (items.length as Int)) {
            val exercise = items[index]
            if (!present(exercise?.id)) continue
            if (present(exercise.group)) increment(result.groups, exercise.group)
            if (present(exercise.category)) increment(result.categories, exercise.category)
            val movement = pattern(exercise)
            if (present(movement)) increment(result.patterns, movement)
            if (present(exercise.group) && present(exercise.equipment) && (array.isArray(exercise.zones) as Boolean) &&
                (exercise.zones.length as Int) > 0) result.annotated++
            if (present(exercise.demoPath30) && present(exercise.modelPath30)) result.withModel++
        }
        result.notice = "Metadatos y variedad comprobables en catálogo, no validación de posturas ni seguridad clínica."
        return result
    }

    fun install() {
        val api = js("({version:'38.10'})")
        api.tracker = ::tracker
        api.stats = ::stats
        api.dayDiff = ::difference
        val objectType: dynamic = js("Object")
        root.HunterPlan3810 = objectType.freeze(api)
    }
}

fun main() {
    val root: dynamic = js("typeof window !== 'undefined' ? window : globalThis")
    if (root.HunterPlan3810 == null) Planning(root).install()
}
