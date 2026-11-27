internal fun calendarGrid(cycle: dynamic): String {
    val state: dynamic = js("state")
    val engine: dynamic = js("E")
    val label: (dynamic) -> dynamic = js("kindLabel")
    val stringify: dynamic = js("String")
    val truth: dynamic = js("Boolean")
    val kinds = mapOf("strength" to "FUE", "rest" to "DES", "recovery" to "REC", "cardio" to "CAR", "mixed" to "MIX")
    return buildString {
        append("""<div class="calendar">""")
        val days = cycle.days
        for (index in 0 until (days.length as Int)) {
            if (!(js("Reflect").has(days, index) as Boolean)) continue
            val day = days[index]
            val date = stringify(day.date)
            val kind = stringify(day.kind)
            val done = truth(state.logs[day.date]?.complete) as Boolean
            val completeClass = if (done) "complete" else ""
            val todayClass = if (day.date === engine.dateKey()) "today" else ""
            val ordinal = index + 1
            val mark = if (done) "✓" else ordinal.toString()
            val kindName = stringify(label(day.kind))
            val abbreviation = kinds[kind as String] ?: "undefined"
            append("""<button class="day $kind $completeClass $todayClass" onclick="dayDetail('$date')" aria-label="Día $ordinal, $date, $kindName"><b>$mark</b><small>$abbreviation</small></button>""")
        }
        append("</div>")
    }
}

internal fun calendarScreen(): String {
    val currentCycle: () -> dynamic = js("currentCycle")
    val blocked: () -> String = js("blocked")
    val engine: dynamic = js("E")
    val label: (dynamic) -> dynamic = js("kindLabel")
    val escape: (dynamic) -> String = js("esc")
    val grid: (dynamic) -> String = js("calendar")
    val cycle = currentCycle()
    if (cycle == null) return blocked()
    val today = engine.dateKey()
    val days = cycle.days ?: js("[]")
    val upcoming = buildString {
        var count = 0
        for (index in 0 until (days.length as Int)) {
            if (!(js("Reflect").has(days, index) as Boolean)) continue
            val day = days[index]
            if (!(day.date >= today)) continue
            val date = escape(day.date)
            val number = escape(day.date.slice(8))
            val kind = escape(label(day.kind))
            append("""<div class="quest"><div class="glyph">$number</div><div class="grow"><h3>$kind</h3><small>$date</small></div><button class="btn ghost" type="button" aria-label="Ver sesión del $date" onclick="dayDetail('${day.date}')">Ver →</button></div>""")
            if (++count == 7) break
        }
        if (count == 0) append("""<p class="muted">No quedan sesiones pendientes en este ciclo.</p>""")
    }
    return """<section class="hunter-calendar3855" aria-labelledby="hunter-calendar-title3855" data-screen3855="calendar">
     <span class="eyebrow">PLANIFICACIÓN INDEPENDIENTE</span>
     <h1 id="hunter-calendar-title3855">Calendario</h1>
     <p class="muted">Revisa tus próximos entrenamientos sin salir de esta pantalla. Tus sesiones registradas se conservan.</p>
     <div class="card hunter-calendar3855__main">
       <div class="row between"><h2>Ciclo ${cycle.index + 1}</h2><span class="badge">${escape(cycle.start)} · 7 días visibles</span></div>
       ${grid(cycle)}
     </div>
     <div class="section-title"><h2>Próximas sesiones</h2></div>
     <div class="card">$upcoming</div>
   </section>"""
}
