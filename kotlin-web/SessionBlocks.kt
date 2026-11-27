private val modes = arrayOf("exercise", "rounds")
private val engine: dynamic get() = js("Engine")
private val truth: dynamic = js("Boolean")
private val undefined: dynamic = js("undefined")

private fun mode(profile: dynamic): String =
    if (profile?.blockMode22 === "rounds") "rounds" else "exercise"

private fun timedStep(kind: String, label: String, seconds: dynamic, index: Int): dynamic {
    val step = js("({})")
    step.kind = kind
    step.label = label
    step.seconds = seconds
    step.blockIndex = index
    return step
}

private fun block(index: Int, label: String): dynamic {
    val block = js("({})")
    block.index = index
    block.label = label
    block.stepIndices = js("[]")
    block.series = js("[]")
    return block
}

private fun addSet(steps: dynamic, block: dynamic, item: dynamic, itemIndex: Int, setIndex: Int) {
    val exercise = engine.byId(item.id)
    if (exercise == null) throw js("Error")("Ejercicio desconocido: ${item.id}")
    val unit = engine.unitOf(item)
    val step = js("({kind:'exercise'})")
    step.id = exercise.id
    step.label = exercise.name
    step.target = item.target
    step.unit = unit
    if (truth(item.side) as Boolean) step.side = item.side
    step.seconds = if (unit === "sec") item.target else js("Math").ceil(js("OwnRoutines18").workSeconds(item))
    step.itemIndex = itemIndex
    step.setIndex = setIndex
    step.sets = item.sets
    step.blockIndex = block.index
    block.stepIndices.push(steps.length)
    steps.push(step)
    block.series.push(step)
}

private fun rest(steps: dynamic, seconds: dynamic, index: Int, label: String, betweenBlocks: Boolean) {
    val pause = timedStep("rest", "Descanso entre $label · $seconds segundos", seconds, index)
    pause.restScope22 = if (betweenBlocks) "block" else "series"
    if (betweenBlocks) pause.nextBlockIndex22 = index + 1
    steps.push(pause)
}

private fun expand(day: dynamic, order: dynamic, settings: dynamic): dynamic {
    if (truth(day.sourceSequence) as Boolean) return null
    val selected = if (order === undefined) "exercise" else order
    val profile = if (settings === undefined) js("({})") else settings
    val items = if (truth(day.items) as Boolean) day.items else js("[]")
    val steps = js("[]")
    val blocks = js("[]")
    val seconds = js("TrainingProgression19").rest(profile)
    val layout = js("({})")
    layout.steps = steps
    layout.blocks = blocks
    layout.mode = selected
    layout.restSeconds = seconds
    val count = items.length as Int
    if (count == 0) return layout
    steps.push(timedStep("preparation", "Preparación · revisa espacio, equipo y técnica", 60, -1))
    if (selected === "rounds") {
        var rounds = 0.0
        for (i in 0 until count) rounds = js("Math").max(rounds, items[i].sets)
        var round = 0
        while (round < rounds) {
            val group = block(round, "Ronda ${round + 1}")
            for (i in 0 until count) {
                if (round < (items[i].sets as Number).toDouble()) addSet(steps, group, items[i], i, round)
            }
            blocks.push(group)
            if (round < rounds - 1) rest(steps, seconds, round, "rondas", true)
            round++
        }
    } else {
        for (i in 0 until count) {
            val item = items[i]
            val group = block(i, engine.byId(item.id).name)
            var set = 0
            while (set < (item.sets as Number).toDouble()) {
                addSet(steps, group, item, i, set)
                if (set < (item.sets as Number).toDouble() - 1) rest(steps, seconds, i, "series", false)
                set++
            }
            blocks.push(group)
            if (i < count - 1) rest(steps, seconds, i, "bloques", true)
        }
    }
    steps.push(timedStep("cooldown", "Vuelta a la calma · baja el ritmo y respira", 60, blocks.length))
    for (i in 0 until (steps.length as Int)) steps[i].blockCount22 = blocks.length
    return layout
}

fun main() {
    val api = js("({})")
    api.expand = ::expand
    api.mode = ::mode
    api.modes = modes
    js("window").BlockSession22 = api
}
