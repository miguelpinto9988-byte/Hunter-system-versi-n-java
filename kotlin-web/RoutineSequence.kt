private val engine: dynamic get() = js("Engine")
private val truth: dynamic = js("Boolean")

private fun expand(day: dynamic): dynamic {
    val result = js("[]")
    val source = day.sourceSequence
    val documented = truth(source?.steps) as Boolean
    val blocks = if (documented) source.steps else day.items
    var itemIndex = 0
    for (blockIndex in 0 until (blocks.length as Int)) {
        val block = blocks[blockIndex]
        if (documented && block.kind !== "exercise") {
            val step = js("({})")
            step.kind = block.kind
            step.label = block.label
            step.seconds = block.seconds
            step.blockIndex = blockIndex
            result.push(step)
            continue
        }
        val exercise = engine.byId(if (documented) block.exerciseId else block.id)
        if (exercise == null) throw js("Error")("Ejercicio desconocido")
        val prescribedUnit = if (documented) block.unit else engine.unitOf(block)
        val unit = if (truth(prescribedUnit) as Boolean) prescribedUnit else exercise.unit
        val rest = if (documented) block.restSeconds else block.rest
        var setIndex = 0
        while ((setIndex.toDouble() < (block.sets as Number).toDouble())) {
            val step = js("({kind:'exercise'})")
            step.id = exercise.id
            step.label = exercise.name
            step.target = block.target
            step.unit = unit
            if (truth(block.side) as Boolean) step.side = block.side
            step.seconds = if (unit === "sec") block.target else 0
            step.itemIndex = itemIndex
            step.setIndex = setIndex
            step.sets = block.sets
            step.blockIndex = blockIndex
            result.push(step)
            if (rest > 0 && (setIndex.toDouble() < (block.sets as Number).toDouble() - 1 || !(truth(source) as Boolean) && blockIndex < (blocks.length as Int) - 1)) {
                val pause = js("({kind:'rest', label:'Descanso'})")
                pause.seconds = rest
                pause.blockIndex = blockIndex
                result.push(pause)
            }
            setIndex++
        }
        itemIndex++
    }
    return result
}

// El guardado anterior identifica la secuencia por los primeros UTF-16 de cada
// punto de código del JSON, incluido el orden de las propiedades de cada paso.
private fun signature(steps: dynamic): String {
    val json: String = js("JSON").stringify(steps)
    var hash = 0x811c9dc5.toInt()
    var index = 0
    while (index < json.length) {
        val code = json[index++].code
        hash = (hash xor code) * 16777619
        if (code in 0xD800..0xDBFF && index < json.length && json[index].code in 0xDC00..0xDFFF) index++
    }
    return hash.toUInt().toString(16).padStart(8, '0')
}

private fun compatible(day: dynamic, profile: dynamic): dynamic {
    val adapt = js("Adapt")
    val auto = adapt.mode(profile).auto
    if (!(truth(auto) as Boolean)) return auto
    return day.items.every { item: dynamic -> adapt.allowed(engine.byId(item.id), profile) }
}

fun main() {
    val root = js("typeof window === 'undefined' ? globalThis : window")
    val sequence = js("({})")
    sequence.expand = ::expand
    sequence.signature = ::signature
    sequence.compatible = ::compatible
    root.RoutineSequence = sequence
}
