private val lookup: (String) -> dynamic get() = js("$")
private val escapeText: (dynamic) -> String get() = js("esc")
private fun truthy(value: dynamic): Boolean = js("Boolean")(value) as Boolean
private fun otherwise(value: dynamic, default: dynamic): dynamic = if (truthy(value)) value else default

internal fun guideButton(day: dynamic): String {
    if (!truthy(day?.items?.length)) return ""
    val note = escapeText(otherwise(day.planningNote, "Revisa la demostración, realiza la serie y confirma lo que completaste."))
    val action = if (day.date === js("E").dateKey()) "Abrir sesión paso a paso" else "Consultar la secuencia"
    return """<section class="card guide-launch"><span class="eyebrow">SESIÓN GUIADA · TEXTO Y 3D</span><h3>Un paso a la vez.</h3><p>$note</p><button class="btn primary full" onclick="openGuidedSession11('${day.date}')">$action</button></section>"""
}

private fun effortForm(log: dynamic): String = buildString {
    append("""<form onsubmit="finishGuide11(event)"><label>Esfuerzo de la sesión<select name="effort" required><option value="">Selecciona del 1 al 10</option>""")
    for (i in 1..10) {
        val effort = when (i) {
            in 1..3 -> "suave"
            in 4..6 -> "moderado"
            in 7..8 -> "alto"
            else -> "muy alto"
        }
        append("""<option value="$i">$i · $effort</option>""")
    }
    append("""</select></label><label>Notas<textarea name="note" maxlength="500" rows="2">${escapeText(otherwise(log.note, ""))}</textarea></label><button type="submit" class="btn primary full">Guardar esfuerzo y finalizar</button></form>""")
}

private fun summary(g: dynamic): String = buildString {
    val log = js("state").logs[g.day.date]
    val everySet: (dynamic, Int) -> Boolean = { _, i ->
        val item = if (log == null) null else log.items[i]
        truthy(item?.actual?.every { a: dynamic -> a.done })
    }
    val all = truthy(g.day.items.every(everySet))
    val record = truthy(g.record)
    val title = if (record) "Revisa tu registro" else "Has recorrido la guía."
    val explanation = if (record) "Solo cuentan las series que confirmaste. Puedes volver a los pasos pendientes." else "La consulta de la guía no crea registros de ejercicio."
    append("""<div class="guided-progress"><span>Fin de la secuencia</span><div class="bar"><i style="width:100%"></i></div></div><div class="card"><h3>$title</h3><p>$explanation</p>""")
    if (record && all && truthy(js("guideValid11")())) append(effortForm(log))
    else if (record) append("""<p class="notice">Quedan series pendientes o la sesión está cerrada. Revisa las casillas en Misiones.</p>""")
    append("""<button class="btn ghost full" onclick="closeActiveModal()">Cerrar guía</button></div><button class="btn ghost" onclick="moveGuide11(-1)">← Paso anterior</button>""")
}

private fun side(step: dynamic): String = when {
    step.side === "left" -> " · lado izquierdo"
    step.side === "right" -> " · lado derecho"
    truthy(js("E").sidesOf(step)) -> " por lado"
    else -> ""
}

private fun demonstration(step: dynamic, exercise: dynamic): String = buildString {
    val unit = if (step.unit === "sec") "segundos" else "repeticiones"
    append("""<div class="guided-dose">${step.target} $unit${side(step)}</div><div class="guided-demo"><canvas id="guidedCanvas" width="720" height="800" aria-label="Demostración con volumen de ${escapeText(exercise.name)}"></canvas></div><button class="btn ghost full" id="guidedDemoToggle" onclick="toggleGuidedDemo11()">Pausar demostración</button>${js("MotionPlayback").controls()}<p>${escapeText(exercise.steps)}</p><p class="notice">${escapeText(exercise.cue)}</p>""")
    if (exercise.variant !== "base") append("""<p class="footnote">${escapeText(otherwise(exercise.variantDescription, "La variante comparte el esquema del movimiento base."))}</p>""")
    append("<details><summary>Sobre la demostración</summary><p>Modelo propio con volumen ilustrado 2.5D del catálogo. No es un clip del entrenador ni evalúa tu postura. Las variantes comparten movimientos base.</p></details>")
}

private fun setForm(step: dynamic, exercise: dynamic, actual: dynamic): String {
    val timed = step.unit === "sec"
    val label = if (timed) "Segundos realizados" else "Repeticiones realizadas"
    val readOnly = if (exercise.equipment !== "db") "readonly" else ""
    val submit = if (truthy(actual?.done)) "Actualizar serie y seguir" else "Registrar serie y continuar"
    return """<form id="guideRecord" onsubmit="confirmGuideSet11(event)"><div class="fields"><label>$label${side(step)}<input name="value" type="number" min="1" max="${if (timed) 3600 else 300}" step="1" required value="${otherwise(actual?.value, step.target)}"></label><label>Carga simultánea · kg<input name="kg" type="number" min="0" max="200" step="0.5" value="${otherwise(actual?.kg, 0)}" $readOnly></label></div><button class="btn primary full" type="submit">$submit</button></form>"""
}

private fun stepMarkup(g: dynamic, step: dynamic, exercise: dynamic, actual: dynamic, allowed: Boolean): String = buildString {
    val percent = if (truthy(g.steps.length)) js("Math").floor(g.index / g.steps.length * 100) else 0
    val heading = when (step.kind) {
        "exercise" -> "SERIE ${step.setIndex + 1} DE ${step.sets}"
        "rest" -> "RECUPERACIÓN"
        "preparation" -> "PREPARACIÓN"
        "cooldown" -> "VUELTA A LA CALMA"
        else -> "undefined"
    }
    append("""<div class="guided-progress"><div class="row between"><b>Paso ${g.index + 1} de ${g.steps.length}</b><small>$percent% del recorrido</small></div><div class="bar"><i style="width:$percent%"></i></div><small>Avanzar por los pasos no equivale a completar ejercicios.</small></div><article class="guided-step"><span class="eyebrow">$heading</span><h3>${escapeText(step.label)}</h3>""")
    if (exercise != null) append(demonstration(step, exercise))
    else append("""<div class="rest-orbit" aria-hidden="true"><span>◌</span></div><p>Completa este bloque y continúa cuando estés listo.</p>""")
    if (truthy(step.seconds)) append("""<div class="guided-clock"><output id="guidedClock" aria-live="off">${js("time11")(g.remaining)}</output><button id="guideClockToggle" class="btn" onclick="toggleGuideClock11()">Iniciar reloj</button><small id="guideClockMessage">El reloj se pausa al cerrar la guía o salir de la app.</small></div>""")
    if (!allowed) append("""<p class="notice warn">La sesión cambió o está cerrada. No se añadirán registros.</p>""")
    if (exercise != null && truthy(g.record) && allowed) append(setForm(step, exercise, actual))
    else append("""<button class="btn primary full" onclick="advanceGuide11()">${if (exercise != null) "Siguiente paso" else "Terminar bloque y continuar"}</button>""")
    append("""<div class="action-pair"><button class="btn ghost" onclick="moveGuide11(-1)" ${if (g.index === 0) "disabled" else ""}>← Anterior</button>""")
    if (exercise != null && truthy(g.record)) append("""<button class="btn ghost" onclick="advanceGuide11()">Dejar serie pendiente →</button>""")
    append("</div>")
    if (truthy(g.record)) append("""<button class="btn danger full" onclick="painGuide11()">Detener la sesión por dolor</button>""")
    append("</article>")
}

internal fun renderGuidedScreen() {
    val g = js("guide11") ?: return
    val slot = lookup("#guidedSlot") ?: return
    js("guideFrame11++")
    val step = g.steps[g.index]
    if (step == null) {
        slot.innerHTML = summary(g)
    } else {
        val exercise = if (step.kind === "exercise") js("E").byId(step.id) else null
        val log = if (truthy(g.record)) js("state").logs[g.day.date] else null
        val item = if (log == null) null else log.items[step.itemIndex]
        val actual = if (item == null) null else item.actual[step.setIndex]
        slot.innerHTML = stepMarkup(g, step, exercise, actual, truthy(js("guideValid11")()))
        g.demoPlaying = js("state").profile?.animate !== false && !truthy(js("matchMedia")("(prefers-reduced-motion: reduce)").matches)
        g.demoFrame = 0
        g.demoLast = 0
        if (exercise != null) {
            js("drawVector")(lookup("#guidedCanvas"), exercise.id, 0, step.side === "left")
            lookup("#guidedDemoToggle").textContent = if (truthy(g.demoPlaying)) "Pausar demostración" else "Reproducir demostración"
            if (truthy(g.demoPlaying)) js("animateGuidedVector11")(js("guideFrame11"))
        }
    }
    js("persistGuide11")()
}
