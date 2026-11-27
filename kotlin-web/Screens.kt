private val routes = listOf(
    "home" to "Panel",
    "plan" to "Misiones",
    "calendar" to "Calendario",
    "routines" to "Rutinas",
    "book" to "Ejercicios",
    "stats" to "Progreso",
)

private fun navigation(): String {
    val current: String = js("tab")
    val selected = when (current) {
        "workout", "goals" -> "plan"
        "monarch" -> "home"
        else -> current
    }
    val icon: (String) -> String = js("icon")
    val alternateIcon: (String) -> String = js("icon2")
    return buildString {
        append("""<nav class="nav nav-six nav-routes3855" aria-label="Navegación principal">""")
        for ((id, label) in routes) {
            val active = if (id == selected) "active" else ""
            val currentPage = if (id == selected) """aria-current="page"""" else ""
            val graphic = when (id) {
                "plan" -> alternateIcon("target")
                "routines" -> alternateIcon("dumbbell")
                "calendar" -> icon("plan")
                else -> icon(id)
            }
            append("""<button data-route3855="$id" onclick="HunterActions.selectRoute('$id')" class="$active" $currentPage>$graphic<span>$label</span></button>""")
        }
        append("</nav>")
    }
}

private fun header(): String {
    val state: dynamic = js("state")
    val engine: dynamic = js("E")
    val icon: (String) -> String = js("icon2")
    val avatar: (String) -> String = js("avatarHtml")
    val truth: dynamic = js("Boolean")
    val ranks = listOf("E", "D", "C", "B", "A", "S")
    val rankMarkup = if (truth(state.profile) as Boolean) {
        val level = engine.stats(state.logs).level as Double
        val index = kotlin.math.floor((level - 1) / 5).coerceAtMost(5.0)
        val rank = if (index.isFinite()) ranks.getOrNull(index.toInt()) ?: "undefined" else "undefined"
        """<span class="header-rank26" aria-label="Rango $rank"><b>$rank</b><span>RANGO $rank</span></span>"""
    } else ""
    val logo = """<svg viewBox="0 0 40 40" fill="none" aria-hidden="true"><path d="m20 2 16 18-16 18L4 20Z" stroke="currentColor"></path><path d="m20 9 10 11-10 11L10 20Z" fill="currentColor" fill-opacity=".12" stroke="var(--accent)" stroke-width="2"></path><path d="m20 15 4 5-4 5-4-5Z" fill="var(--accent)"></path></svg>"""
    return """<header class="topbar"><button class="brand brand-button" onclick="go('home')"><div class="logo">$logo</div><div><span class="brand-title26">HUNTER SYSTEM</span><small>SISTEMA DE SOMBRAS</small></div></button><div class="row">$rankMarkup<button class="icon-button" onclick="go('reminders')" aria-label="Recordatorios">${icon("bell")}</button><button class="avatar-button" onclick="HunterActions.openOrScrollProfile()" aria-label="Editar perfil">${avatar("avatar-small")}</button></div></header>"""
}

private fun flag(condition: Boolean, attribute: String): String = if (condition) attribute else ""

private fun reminders(): String {
    val settings: dynamic = js("state.reminders")
    val nativeStatus: dynamic = js("window.Android && window.Android.notificationStatus")
    val escape: (dynamic) -> String = js("esc")
    val status = if (js("Boolean")(nativeStatus) as Boolean) {
        "Consultando permisos del dispositivo…"
    } else {
        "Los avisos del dispositivo están disponibles en el APK de Android."
    }
    val enabled = flag(js("Boolean")(settings.enabled) as Boolean, "checked")
    val daily = settings.mode === "daily"
    val interval = settings.mode === "interval"
    val intervalOptions = listOf(1, 2, 3, 4, 6).joinToString("") { hours ->
        val selected = flag(settings.interval === hours, "selected")
        val unit = if (hours == 1) "hora" else "horas"
        """<option value="$hours" $selected>Cada $hours $unit</option>"""
    }
    val weekdayOptions = listOf("L", "M", "X", "J", "V", "S", "D").mapIndexed { index, label ->
        val day = index + 1
        val checked = flag(settings.days.includes(day) as Boolean, "checked")
        """<label><input class="reminder-day" type="checkbox" value="$day" $checked><span>$label</span></label>"""
    }.joinToString("")

    return buildString {
        append("""<div class="page-heading"><span class="eyebrow">EL SISTEMA TE ACOMPAÑA</span><h1>Recordatorios</h1><p class="muted">Elige días y horarios para recibir tu próxima misión.</p></div>""")
        append("""<form class="card reminder-form" onsubmit="saveReminders(event)">""")
        append("""<label class="row between"><b>Activar avisos de entrenamiento</b><input id="reminderEnabled" type="checkbox" $enabled></label>""")
        append("""<div id="nativeStatus" class="notice">$status</div>""")
        append("""<div class="field"><label class="label" for="reminderMode">Frecuencia</label><select id="reminderMode" onchange="updateReminderFields()"><option value="daily" ${flag(daily, "selected")}>Una vez al día</option><option value="interval" ${flag(interval, "selected")}>Cada cierto número de horas</option></select></div>""")
        append("""<div id="dailyFields" ${flag(!daily, "hidden")}><div class="field"><label class="label" for="reminderTime">Hora del aviso</label><input id="reminderTime" type="time" value="${escape(settings.time)}" required></div></div>""")
        append("""<div id="intervalFields" ${flag(!interval, "hidden")}><div class="fields"><div class="field"><label class="label" for="reminderStart">Desde</label><input type="time" id="reminderStart" value="${escape(settings.start)}" required></div><div class="field"><label class="label" for="reminderEnd">Hasta</label><input type="time" id="reminderEnd" value="${escape(settings.end)}" required></div></div>""")
        append("""<div class="field"><label class="label" for="reminderInterval">Intervalo</label><select id="reminderInterval">$intervalOptions</select></div></div>""")
        append("""<div class="field"><label class="label">Días de la semana</label><div class="weekday-picker">$weekdayOptions</div></div>""")
        append("""<button class="btn primary full" type="submit">Guardar recordatorios</button><div class="action-pair"><button class="btn ghost" type="button" onclick="testNotification()">Enviar prueba</button><button class="btn ghost" type="button" onclick="openNotificationSettings()">Permisos de Android</button></div></form>""")
        append("""<div class="notice">Los avisos se detienen durante el resto del día cuando completas la misión o registras dolor. Android puede retrasarlos por ahorro de batería; los horarios son aproximados. Tras forzar la detención de la app, ábrela de nuevo para reactivar su programación.</div>""")
        append("""<p class="footnote">Las notificaciones muestran el nombre de la rutina y algunos ejercicios. Puedes controlar su visibilidad en la pantalla bloqueada desde los ajustes de Android.</p>""")
    }
}

private fun reminderField(id: String): dynamic {
    val document: dynamic = js("document")
    return document.getElementById(id)
}

private fun updateReminderFields() {
    val mode = reminderField("reminderMode").value
    reminderField("dailyFields").hidden = mode !== "daily"
    reminderField("intervalFields").hidden = mode !== "interval"
}

private fun saveReminders(event: dynamic) {
    event.preventDefault()
    val document: dynamic = js("document")
    val state: dynamic = js("state")
    val number: dynamic = js("Number")
    val toast: (String) -> Unit = js("toast")
    val save: () -> Unit = js("save")
    val settings = js("({})")
    settings.enabled = reminderField("reminderEnabled").checked
    settings.mode = reminderField("reminderMode").value
    settings.time = reminderField("reminderTime").value
    settings.start = reminderField("reminderStart").value
    settings.end = reminderField("reminderEnd").value
    settings.interval = number(reminderField("reminderInterval").value)
    settings.days = js("[]")
    val checked = document.querySelectorAll(".reminder-day:checked")
    for (index in 0 until (checked.length as Int)) {
        settings.days.push(number(checked[index].value))
    }
    if (settings.enabled as Boolean && (settings.days.length as Int) == 0) {
        toast("Selecciona al menos un día.")
        return
    }
    if (settings.mode === "interval" && settings.start >= settings.end) {
        toast("La hora final debe ser posterior a la inicial del mismo día.")
        return
    }
    state.reminders = settings
    save()
    val android: dynamic = js("window.Android")
    if (js("Boolean")(android?.setReminders) as Boolean) {
        val json: dynamic = js("JSON")
        val refresh: dynamic = js("refreshReminderStatus")
        val timeout: dynamic = js("setTimeout")
        android.setReminders(json.stringify(settings))
        toast("Horario guardado. Revisa el permiso de notificaciones.")
        timeout(refresh, 500)
    } else {
        toast("Preferencias guardadas. Los avisos requieren el APK de Android.")
    }
}

private fun goalsView(): String {
    val goals: dynamic = js("state.goals")
    val completed: (dynamic) -> Boolean = js("goalDone")
    val escape: (dynamic) -> String = js("esc")
    return buildString {
        append("""<div class="page-heading"><span class="eyebrow">TÚ DECIDES LA META</span><h1>Objetivos personales</h1><p class="muted">Marca «Finalizado» cuando completes lo que te propusiste.</p></div><button class="btn primary" onclick="newGoal()">+ Crear objetivo</button><div class="goal-list">""")
        for (index in 0 until (goals.length as Int)) {
            val goal = goals[index]
            val id = escape(goal.id)
            val title = escape(goal.title)
            val done = completed(goal)
            val daily = goal.repeat === "daily"
            val tag = if (daily) "DIARIO" else "UNA VEZ"
            val cardClass = if (done) "goal-complete" else ""
            val progress = when {
                daily -> "${goal.dates.length} días completados"
                done -> "Completado el ${goal.dates[0]}"
                else -> "Pendiente"
            }
            val buttonClass = if (done) "success" else "primary"
            val buttonText = if (done) "✓ Finalizado · desmarcar" else "Marcar como finalizado"
            append("""<article class="card $cardClass"><div class="row between"><span class="tag">$tag</span><button class="text-button" onclick="deleteGoal('$id')" aria-label="Eliminar objetivo $title">×</button></div><h3>$title</h3><p class="muted">$progress</p><button class="btn $buttonClass full" onclick="toggleGoal('$id')">$buttonText</button></article>""")
        }
        append("""</div><div class="notice">Los objetivos son tu lista personal. No suman repeticiones ni modifican automáticamente la intensidad de las rutinas.</div>""")
    }
}

private fun persistState(): Boolean {
    val runtime: dynamic = js("Runtime20")
    if (js("Boolean")(runtime.initializing) as Boolean) return true
    return try {
        val storage: dynamic = js("localStorage")
        val json: dynamic = js("JSON")
        val state: dynamic = js("state")
        val key: String = js("KEY")
        storage.setItem(key, json.stringify(state))
        true
    } catch (_: dynamic) {
        val toast: (String) -> Unit = js("toast")
        toast("No se pudo guardar. Exporta una copia antes de cerrar.")
        false
    }
}

private fun refreshReminderStatus(assignStatus: (dynamic) -> Unit) {
    val statusField = reminderField("nativeStatus")
    val android: dynamic = js("window.Android")
    val truth: dynamic = js("Boolean")
    if (statusField == null || !(truth(android?.notificationStatus) as Boolean)) return
    try {
        val json: dynamic = js("JSON")
        val status = json.parse(android.notificationStatus())
        assignStatus(status)
        statusField.textContent = when {
            !(truth(status.allowed) as Boolean) ->
                "Las notificaciones están bloqueadas o pendientes de permiso. Puedes habilitarlas en los ajustes de Android."
            !(truth(status.enabled) as Boolean) -> "Permiso concedido. Los recordatorios están desactivados."
            else -> {
                val next = if (truth(status.next) as Boolean) status.next else "pendiente de programación"
                val text: dynamic = js("String")
                "Avisos activados en Android. Próximo aviso aproximado: " + (text(next) as String)
            }
        }
    } catch (_: dynamic) {
        statusField.textContent = "No se pudo consultar el estado de las notificaciones."
    }
}

private fun testNotification() {
    val android: dynamic = js("window.Android")
    if (js("Boolean")(android?.testNotification) as Boolean) android.testNotification()
    else {
        val toast: (String) -> Unit = js("toast")
        toast("La prueba está disponible en la aplicación Android.")
    }
}

private fun openNotificationSettings() {
    val android: dynamic = js("window.Android")
    if (js("Boolean")(android?.openNotificationSettings) as Boolean) android.openNotificationSettings()
    else {
        val toast: (String) -> Unit = js("toast")
        toast("Instala el APK para configurar avisos del dispositivo.")
    }
}

fun main() {
    val window: dynamic = js("window")
    val screens = js("({})")
    screens.navigation = ::navigation
    screens.header = ::header
    screens.reminders = ::reminders
    screens.updateReminderFields = ::updateReminderFields
    screens.saveReminders = ::saveReminders
    screens.goalsView = ::goalsView
    screens.persistState = ::persistState
    screens.refreshReminderStatus = ::refreshReminderStatus
    screens.testNotification = ::testNotification
    screens.openNotificationSettings = ::openNotificationSettings
    screens.calendarGrid = ::calendarGrid
    screens.calendarScreen = ::calendarScreen
    screens.goalDone = ::goalDone
    screens.goalMini = ::goalMini
    screens.newGoal = ::newGoal
    screens.addGoal = ::addGoal
    screens.toggleGoal = ::toggleGoal
    screens.deleteGoal = ::deleteGoal
    screens.confirmDeleteGoal = ::confirmDeleteGoal
    installMissions(screens)
    installMissionEditor(screens)
    window.HunterKotlinScreens = screens
}
