private val guide: dynamic get() = js("guide11")
private val state: dynamic get() = js("state")
private val engine: dynamic get() = js("E")
private val sequence: dynamic get() = js("RoutineSequence")
private val math: dynamic = js("Math")
private val number: dynamic = js("Number")
private val date: dynamic = js("Date")
private val find: (String) -> dynamic get() = js("$")
private val escape: (dynamic) -> String get() = js("esc")
private val notify: (String) -> Unit get() = js("toast")
private val pause: () -> Unit get() = js("pauseGuide11")
private val persist: () -> Unit get() = js("persistGuide11")
private val paint: () -> Unit get() = js("paintGuide11")
private val updateClock: () -> Unit get() = js("updateGuideClock11")
private val jump: (dynamic) -> Unit get() = js("jumpGuide19")
private val save: () -> Unit get() = js("save")
private val lookupDay: (dynamic) -> dynamic get() = js("dayByDate")
private val validNow: () -> Boolean get() = js("guideValid11")
private val begin: (dynamic, dynamic) -> Unit get() = js("startGuide11")
private val incrementFrame: () -> Unit = js("function(){guideFrame11++}")
private val assignGuide: (dynamic) -> Unit = js("function(value){guide11=value}")
private fun yes(value: dynamic): Boolean = js("Boolean")(value) as Boolean
private fun fallback(value: dynamic, other: dynamic): dynamic = if (yes(value)) value else other
private fun secondsAt(steps: dynamic, index: dynamic): dynamic = fallback(steps[index]?.seconds, 0)
private fun closeAndRender() {
    js("closeActiveModal")()
    js("render")()
}

private fun persistGuide(withMode: Boolean) {
    val g = guide
    if (!yes(g?.record)) return
    val saved = js("({})")
    saved.signature = g.signature
    saved.index = g.index
    saved.remaining = g.remaining
    if (withMode) saved.mode22 = if (yes(g.blockMode22)) g.blockMode22 else js("BlockSession22").mode(state.profile)
    saved.updatedAt = js("new Date().toISOString()")
    state.guidedSessions[g.day.date] = saved
    save()
}

private fun remainingTime(g: dynamic): dynamic = math.max(0, math.ceil((g.deadline - date.now()) / 1000))

private fun pauseGuide(remember: dynamic) {
    val g = guide ?: return
    if (yes(g.running)) {
        g.remaining = remainingTime(g)
        g.running = false
    }
    if (yes(remember)) persist()
}

private fun valid(): Boolean {
    val g = guide ?: return false
    if (!yes(g.record)) return true
    val day = lookupDay(g.day.date)
    val log = state.logs[g.day.date]
    return g.day.date === engine.dateKey() && !yes(log?.pain) && !yes(log?.complete) &&
        yes(sequence.compatible(day, state.profile)) && sequence.signature(sequence.expand(day)) === g.signature
}

private fun openGuide(key: dynamic) {
    val day = lookupDay(key)
    if (!yes(day?.items?.length)) return
    val record = key === engine.dateKey()
    if (record && !yes(sequence.compatible(day, state.profile))) {
        notify("Revisa las restricciones y las indicaciones antes de comenzar esta sesión.")
        return
    }
    val log = state.logs[key]
    if (record && (yes(log?.pain) || yes(log?.complete))) {
        notify("La sesión ya está cerrada. Puedes consultar la técnica desde la ficha.")
        return
    }
    begin(day, record)
}

private fun preview(id: dynamic) {
    val routine = js("allRoutines")().find { it: dynamic -> it.id === id } ?: return
    val day = js("({})")
    day.date = engine.dateKey()
    day.title = routine.name
    day.items = routine.items
    day.sourceSequence = routine.sourceSequence
    begin(day, false)
}

private fun start(day: dynamic, record: dynamic, withBlocks: Boolean) {
    pause()
    val mode = if (withBlocks) js("BlockSession22").mode(state.profile) else null
    val layout = if (withBlocks) js("BlockSession22").expand(day, mode, fallback(state.profile, js("({})"))) else null
    val steps = if (withBlocks) layout?.steps ?: js("expandBefore22")(day) else sequence.expand(day)
    val hash = sequence.signature(steps)
    val saved = when {
        !yes(record) -> null
        withBlocks -> js("migrateGuide22")(day, steps, state.guidedSessions[day.date])
        else -> state.guidedSessions[day.date]
    }
    val usable = saved?.signature === hash && yes(number.isInteger(saved.index)) && saved.index >= 0 && saved.index <= steps.length
    val g = js("({})")
    g.day = day
    g.record = record
    g.steps = steps
    g.signature = hash
    if (withBlocks) {
        g.blockMode22 = mode
        g.layout22 = layout
    }
    g.index = if (usable) saved.index else 0
    val duration = secondsAt(steps, g.index)
    g.remaining = if (usable) math.min(fallback(saved.remaining, 0), duration) else duration
    g.running = false
    g.deadline = 0
    assignGuide(g)
    val source = day.sourceSequence
    js("modal")(buildString {
        val label = if (yes(record)) "MI SESIÓN" else "CONSULTA · SIN REGISTRO"
        append("""<section class="guided-session" data-companion-static="true"><header class="guided-header"><span class="eyebrow">$label</span><h2>${escape(fallback(day.title, "Rutina guiada"))}</h2></header>""")
        if (yes(source)) append("""<p class="notice source-evidence">${escape(source.verificationNote)}</p>""")
        if (withBlocks) append("""<div id="blockSlot22"></div>""")
        append("""<div id="guidedSlot"></div>""")
        if (yes(source)) append("""<a class="source-footer" href="https://www.youtube.com/watch?v=${source.videoId}" target="_blank" rel="noopener">Enlace directo a la rutina original ↗</a>""")
        append("</section>")
    })
    find("#modal").dataset.guide = "true"
    paint()
}

private fun jumpTo(index: dynamic, withFrame: Boolean) {
    val g = guide ?: return
    if (withFrame && !yes(number.isInteger(index))) return
    pause()
    if (withFrame) incrementFrame()
    g.index = math.max(0, math.min(g.steps.length, index))
    g.remaining = secondsAt(g.steps, g.index)
    if (withFrame) g.running = false
    paint()
}

private fun toggleClock() {
    val g = guide
    if (g == null || !validNow()) {
        notify("La sesión ya no está disponible para continuar.")
        return
    }
    if (yes(g.running)) pause() else {
        if (g.remaining <= 0) g.remaining = secondsAt(g.steps, g.index)
        g.running = true
        g.deadline = date.now() + g.remaining * 1000
    }
    updateClock()
}

private fun textIfChanged(element: dynamic, value: String) {
    if (element != null && element.textContent !== value) element.textContent = value
}

private fun refreshClock() {
    val g = guide ?: return
    val clock = find("#guidedClock") ?: return
    if (!yes(find("#modal").open)) return
    textIfChanged(clock, js("time11")(g.remaining))
    val label = when {
        yes(g.running) -> "Pausar reloj"
        yes(g.remaining) -> "Iniciar reloj"
        else -> "Repetir reloj"
    }
    textIfChanged(find("#guideClockToggle"), label)
    if (!yes(g.remaining)) textIfChanged(find("#guideClockMessage"), "Tiempo terminado. Confirma lo que realizaste para continuar.")
}

private fun tick() {
    val g = guide
    if (!yes(g?.running)) return
    if (!yes(find("#modal").open) || find("#guidedSlot") == null || yes(js("document").hidden) || !validNow()) {
        pause()
    } else {
        g.remaining = remainingTime(g)
        if (!yes(g.remaining)) {
            g.running = false
            persist()
            js("window").HunterV27?.alarm?.finish("guide")
        }
    }
    updateClock()
}

private fun storeSet(form: dynamic, completed: dynamic): Boolean {
    val g = guide
    if (!yes(g?.record)) return false
    val step = g.steps[g.index]
    if (step?.kind !== "exercise" || !validNow()) return false
    if (yes(completed) && !yes(form.reportValidity())) return false
    val equipment = engine.byId(step.id).equipment
    val value = number(form.elements.value.value)
    val kg = if (equipment === "db" || equipment === "gym") number(form.elements.kg.value) else 0
    val max = if (step.unit === "sec") 3600 else 300
    if (yes(completed) && (!yes(number.isInteger(value)) || value < 1 || value > max || !yes(number.isFinite(kg)) || kg < 0 || kg > 200)) return false
    val item = js("getLog")().items[step.itemIndex]
    if (item?.id !== step.id || !yes(item.actual[step.setIndex])) return false
    val actual = js("({})")
    actual.done = completed
    actual.value = if (yes(completed)) value else 0
    actual.kg = if (yes(completed)) kg else 0
    item.actual[step.setIndex] = actual
    item.selfReported = false
    item.missionState = item.actual.every { set: dynamic -> set.done }
    save()
    return true
}

private fun check(input: dynamic) {
    if (!yes(js("storeGuideCheck19")(input.form, input.checked))) {
        input.checked = false
        notify("Revisa la cantidad realizada y la disponibilidad de esta sesión.")
        return
    }
    textIfChanged(find("#guideCheckStatus19"), if (yes(input.checked)) "Serie marcada como realizada." else "Serie pendiente.")
}

private fun pending(withBlocks: Boolean) {
    val g = guide ?: return
    val form = find("#guideRecord")
    if (form != null) {
        form.elements.completed.checked = false
        js("storeGuideCheck19")(form, false)
    }
    var rest: Int? = null
    for (i in (g.index as Int) + 1 until (g.steps.length as Int)) {
        val step = g.steps[i]
        if (step.kind === "exercise") {
            jump(rest ?: i)
            return
        }
        if (withBlocks && rest == null && step.kind === "rest" && step.restScope22 === "block") rest = i
    }
    jump(g.steps.length)
}

private fun confirm(event: dynamic) {
    event.preventDefault()
    val form = event.target
    when {
        !yes(form.elements.completed?.checked) -> js("pendingGuide19")()
        !yes(js("storeGuideCheck19")(form, true)) -> notify("Revisa la cantidad realizada antes de guardar la serie.")
        else -> js("advanceGuide11")()
    }
}

private fun finish(event: dynamic) {
    event.preventDefault()
    if (!yes(event.target.reportValidity()) || !validNow()) return
    val log = js("getLog")()
    val tasks = js("ensureTasks")(guide.day)
    val incomplete: (dynamic) -> Boolean = { !yes(it.done) }
    if (yes(log.items.some { item: dynamic -> item.actual.some(incomplete) }) || yes(tasks.some(incomplete))) {
        notify("Quedan series o misiones personales pendientes.")
        return
    }
    val form: dynamic = js("new FormData(event.target)")
    log.rpe = number(form.get("effort"))
    log.note = js("String")(fallback(form.get("note"), "")).slice(0, 500)
    log.missionTasks = tasks.map { task: dynamic -> js("Object").assign(js("({})"), task) }
    log.complete = true
    log.completedAt = js("new Date().toISOString()")
    pause()
    save()
    closeAndRender()
    notify("Sesión finalizada. Series y esfuerzo guardados.")
}

private fun stopForPain() {
    if (!yes(guide?.record) || !validNow()) return
    pause()
    val log = js("getLog")()
    log.pain = true
    val previous = fallback(log.note, "")
    log.note = ("${previous}\nSesión detenida por dolor desde la guía.").asDynamic().trim().slice(0, 500)
    save()
    closeAndRender()
    notify("Sesión detenida. Se conserva lo que registraste.")
}

private fun anchor(old: dynamic, index: dynamic, steps: dynamic): Int {
    val previous = old[index] ?: return steps.length as Int
    fun locate(item: dynamic): Int = steps.findIndex { s: dynamic ->
        s.kind === "exercise" && s.itemIndex === item.itemIndex && s.setIndex === item.setIndex
    } as Int
    if (previous.kind === "exercise") return locate(previous)
    if (previous.kind !== "rest") return steps.findIndex { s: dynamic -> s.kind === previous.kind } as Int
    val next = old.find { s: dynamic, i: dynamic -> i > index && s.kind === "exercise" }
    if (next == null) return steps.findIndex { s: dynamic -> s.kind === "cooldown" } as Int
    val target = locate(next)
    return if (target > 0 && steps[target - 1].kind === "rest") target - 1 else target
}

private fun migrate(day: dynamic, steps: dynamic, saved: dynamic): dynamic {
    if (!yes(saved) || saved.signature === sequence.signature(steps)) return saved
    val old = js("expandBefore22")(day)
    if (saved.signature !== sequence.signature(old)) return null
    val index = maxOf(0, anchor(old, saved.index, steps))
    val result = js("Object").assign(js("({})"), saved)
    result.signature = sequence.signature(steps)
    result.index = index
    val duration = secondsAt(steps, index)
    result.remaining = if (old[saved.index]?.kind === steps[index]?.kind) math.min(fallback(saved.remaining, 0), duration) else duration
    return result
}

private fun validSave(key: dynamic, saved: dynamic): dynamic {
    if (!Regex("""\d{4}-\d{2}-\d{2}""").matches(key as String) || engine.dateKey(js("new Date(key+'T12:00:00')")) !== key) return false
    if (!yes(saved)) return saved
    return jsTypeOf(saved.signature) == "string" && Regex("[a-f0-9]{8}").matches(saved.signature as String) &&
        yes(number.isInteger(saved.index)) && saved.index >= 0 && saved.index <= 1200 &&
        yes(number.isInteger(saved.remaining)) && saved.remaining >= 0 && saved.remaining <= 3600 &&
        jsTypeOf(saved.updatedAt) == "string" && yes(number.isFinite(date.parse(saved.updatedAt)))
}

fun main() {
    val api = js("({})")
    api.renderScreen = ::renderGuidedScreen
    api.button = ::guideButton
    api.persistGuide = ::persistGuide
    api.pauseGuide = ::pauseGuide
    api.valid = ::valid
    api.open = ::openGuide
    api.preview = ::preview
    api.start = ::start
    api.jump = ::jumpTo
    api.toggleClock = ::toggleClock
    api.refreshClock = ::refreshClock
    api.tick = ::tick
    api.storeSet = ::storeSet
    api.check = ::check
    api.pending = ::pending
    api.confirm = ::confirm
    api.finish = ::finish
    api.stopForPain = ::stopForPain
    api.anchor = ::anchor
    api.migrate = ::migrate
    api.validSave = ::validSave
    js("window").HunterGuided = api
}
