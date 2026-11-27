internal fun goalDone(goal: dynamic): Boolean {
    val engine: dynamic = js("E")
    return if (goal.repeat === "daily") goal.dates.includes(engine.dateKey()) as Boolean
    else (goal.dates.length as Int) > 0
}

internal fun goalMini(): String {
    val goals: dynamic = js("state.goals")
    val escape: (dynamic) -> String = js("esc")
    return buildString {
        for (index in 0 until minOf(3, goals.length as Int)) {
            val goal = goals[index]
            val done = goalDone(goal)
            val classes = if (done) "achieved" else ""
            val check = if (done) "✓" else ""
            val repeat = if (goal.repeat === "daily") "Objetivo diario" else "Objetivo personal"
            append("""<button class="goal-line $classes" onclick="toggleGoal('${escape(goal.id)}')"><span class="goal-box">$check</span><span><b>${escape(goal.title)}</b><small>$repeat</small></span></button>""")
        }
        if (isEmpty()) append("""<p class="empty small-empty">Crea una meta y márcala cuando la hayas terminado.</p>""")
    }
}

internal fun newGoal() {
    val modal: (String) -> Unit = js("modal")
    modal("""<span class="eyebrow">NUEVA META</span><h2>Define tu objetivo</h2><form onsubmit="addGoal(event)"><label class="label" for="goalTitle">¿Qué quieres completar?</label><input id="goalTitle" maxlength="100" required placeholder="Ej.: completar mi sesión de cardio"><div class="field"><label class="label" for="goalRepeat">Frecuencia</label><select id="goalRepeat"><option value="daily">Diario</option><option value="once">Una sola vez</option></select></div><button type="submit" class="btn primary full">Crear objetivo</button></form>""")
}

internal fun addGoal(event: dynamic) {
    event.preventDefault()
    val query: (String) -> dynamic = js("$")
    val goals: dynamic = js("state.goals")
    val toast: (String) -> Unit = js("toast")
    val save: () -> Unit = js("save")
    val go: (String) -> Unit = js("go")
    val title = query("#goalTitle").value.trim() as String
    if (title.isEmpty()) return
    if ((goals.length as Int) >= 100) {
        toast("Puedes guardar hasta 100 objetivos.")
        return
    }
    val goal = js("({})")
    goal.id = js("'g' + Date.now().toString(36)")
    goal.title = title
    goal.repeat = query("#goalRepeat").value
    goal.dates = js("[]")
    goals.push(goal)
    save()
    query("#modal").close()
    go("goals")
}

internal fun toggleGoal(id: dynamic) {
    val goals: dynamic = js("state.goals")
    val engine: dynamic = js("E")
    val matches: (dynamic) -> Boolean = { it.id === id }
    val goal = goals.find(matches) ?: return
    val done = goalDone(goal)
    if (done) {
        val keep: (dynamic) -> Boolean = { it !== engine.dateKey() }
        goal.dates = if (goal.repeat === "daily") goal.dates.filter(keep) else js("[]")
    } else {
        goal.dates.push(engine.dateKey())
    }
    val save: () -> Unit = js("save")
    val render: () -> Unit = js("render")
    save()
    render()
    if (!done) {
        val pulse: () -> Unit = js("pulse")
        val toast: (String) -> Unit = js("toast")
        pulse()
        toast("OBJETIVO FINALIZADO ✓")
    }
}

internal fun deleteGoal(id: dynamic) {
    val modal: (String) -> Unit = js("modal")
    val escape: (dynamic) -> String = js("esc")
    modal("""<h2>Eliminar objetivo</h2><p>También se borrarán las fechas de cumplimiento de este objetivo.</p><button class="btn danger full" onclick="HunterKotlinScreens.confirmDeleteGoal('${escape(id)}')">Eliminar objetivo</button>""")
}

internal fun confirmDeleteGoal(id: dynamic) {
    val state: dynamic = js("state")
    val document: dynamic = js("document")
    val save: () -> Unit = js("save")
    val render: () -> Unit = js("render")
    val keep: (dynamic) -> Boolean = { it.id !== id }
    state.goals = state.goals.filter(keep)
    save()
    document.querySelector("#modal").close()
    render()
}
