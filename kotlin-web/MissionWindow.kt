internal fun renderMissionWindow(day: dynamic, inModal: dynamic): String {
    val engine: dynamic = js("E")
    val logs: dynamic = js("state.logs")
    val profile: dynamic = js("state.profile")
    val adapt: dynamic = js("Adapt")
    val escape: (dynamic) -> String = js("esc")
    val missionSummary: (dynamic) -> dynamic = js("missionSummary")
    val ensureTasks: (dynamic) -> dynamic = js("ensureTasks")
    val itemFinished: (dynamic, dynamic) -> dynamic = js("itemFinished")
    val truth: dynamic = js("Boolean")
    val log = logs[day.date]
    val summary = missionSummary(day)
    val date = day.date
    val today = date === engine.dateKey()
    val mode = adapt.mode(profile)
    val automatic = truth(mode.auto) as Boolean
    val pain = truth(log?.pain) as Boolean
    val title = escape(if (truth(day.title) as Boolean) day.title else "Completa tus misiones")
    val complete = truth(summary.complete) as Boolean
    val status = if (complete) "✓ MISIÓN FINALIZADA" else "◇ MISIÓN EN CURSO"
    val statusClass = if (complete) "complete" else ""
    val progress = if (truth(summary.total) as Boolean) summary.done / summary.total * 100 else 0
    val modalSuffix = if (truth(inModal) as Boolean) "modal" else "page"
    val dateLabel = if (today) "MISIÓN DIARIA" else date

    return buildString {
        append("""<section class="mission-window"><div class="system-caption"><span class="system-signal"></span> NOTIFICACIÓN DEL SISTEMA <span>◇</span></div><div class="mission-window-head"><span class="eyebrow">$dateLabel</span><h2>$title</h2><p>${summary.done} / ${summary.total} objetivos completados</p></div><div class="mission-progress"><i style="width:$progress%"></i></div>""")
        if (!automatic) append("""<div class="notice">${escape(mode.reason)} Tus casillas y notas siguen disponibles.</div>""")
        if (pain) append("""<div class="notice warn">Sesión detenida por dolor. Conserva lo realizado; no continúes hoy.</div>""")
        append("""<div class="mission-checklist">""")
        val items = day.items
        for (index in 0 until (items.length as Int)) {
            if (!(js("Reflect").has(items, index) as Boolean)) continue
            val item = items[index]
            val exercise = engine.byId(item.id)
            val done = truth(itemFinished(if (log == null) null else log.items[index], log)) as Boolean
            val reasons = adapt.reasons(exercise, profile)
            val hasReasons = (reasons.length as Int) > 0
            val checkedClass = if (done) "checked" else ""
            val disabled = if (!today || (!done && (!automatic || pain || hasReasons))) "disabled" else ""
            val name = escape(if (truth(item.missionTitle) as Boolean) item.missionTitle else exercise.name)
            val unit = if (engine.unitOf(item) === "sec") "segundos" else "repeticiones"
            val sides = if (truth(engine.sidesOf(item)) as Boolean) " por lado" else ""
            append("""<div class="mission-check-row $checkedClass"><label><input type="checkbox" $checkedClass $disabled onchange="toggleMissionItem('$date',$index,this.checked)"><span><b>$name</b><small>${item.sets} × ${item.target} $unit$sides</small>""")
            if (hasReasons) append("""<small class="restriction-note">Revisar: ${escape(reasons[0])}</small>""")
            append("""</span></label><button class="text-button" onclick="technique('${exercise.id}')" aria-label="Detalles de ${exercise.name}">↗</button></div>""")
        }
        val tasks = ensureTasks(day)
        for (index in 0 until (tasks.length as Int)) {
            if (!(js("Reflect").has(tasks, index) as Boolean)) continue
            val task = tasks[index]
            val checked = if (truth(task.done) as Boolean) "checked" else ""
            val disabled = if (today) "" else "disabled"
            append("""<div class="mission-check-row $checked"><label><input type="checkbox" $checked $disabled onchange="toggleMissionTask('$date',$index,this.checked)"><span><b>${escape(task.text)}</b><small>Misión personal · sin cantidades automáticas</small></span></label></div>""")
        }
        append("</div>")
        if (today && !pain && summary.total > 0 && summary.done === summary.total && !complete) {
            append("""<button class="btn primary full" onclick="HunterActions.confirmMission('$date')">Confirmar misión terminada</button>""")
        }
        val editDisabled = if (date < engine.dateKey()) "disabled" else ""
        append("""<div class="mission-status $statusClass">$status</div><div class="action-pair"><button class="btn ghost" onclick="editMission('$date')" $editDisabled>✎ Modificar misión</button>""")
        if (today && truth(items.length) as Boolean) {
            append("""<button class="btn primary" onclick="HunterActions.openDetailedWorkout()">Registro detallado</button>""")
        }
        append("</div>")
        if (today) {
            val note = escape(if (truth(log?.note) as Boolean) log.note else "")
            append("""<div class="field"><label class="label" for="missionNote-$modalSuffix">Notas del día</label><textarea id="missionNote-$modalSuffix" maxlength="500" rows="2" placeholder="Sensaciones o indicaciones que seguiste…" onchange="saveMissionNote('$date',this.value)">$note</textarea></div>""")
        }
        append("""<p class="footnote">Marcar una casilla confirma que terminaste. No añade series, repeticiones ni kg que no hayas registrado. Puedes desmarcar para corregir.</p></section>""")
    }
}
