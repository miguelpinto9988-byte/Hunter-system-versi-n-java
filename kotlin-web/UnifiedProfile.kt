private class UnifiedProfile(private val root: dynamic, private val document: dynamic) {
    private val number: dynamic = js("Number")
    private val string: dynamic = js("String")
    private val obj: dynamic = js("Object")
    private val array: dynamic = js("Array")
    private val boolean: dynamic = js("Boolean")
    private val state: dynamic get() = js("state")
    private val categories = listOf("tren_superior", "tren_central", "tren_inferior", "movilidad", "cardio")
    private val weekdays = listOf(1, 2, 3, 4, 5, 6, 0)

    private fun truth(value: dynamic): Boolean = boolean(value) as Boolean
    private fun text(value: dynamic): String = string(if (truth(value)) value else "") as String
    private fun clamp(value: dynamic, low: Int, high: Int, fallback: Int): Int {
        val n = number(value) as Double
        return if (n >= low && n <= high && kotlin.math.floor(n) == n) n.toInt() else fallback
    }

    private fun schedule(profile: dynamic): Array<Int> = weekdays.map { index ->
        val days = profile?.weekPlan
        val focus = profile?.weekFocus
        when (if (days == null) null else days[index]) {
            "cardio" -> 4
            "mobility" -> 3
            "train" -> when (if (focus == null) null else focus[index]) {
                "core" -> 1
                "lower" -> 2
                else -> 0
            }
            else -> 5
        }
    }.toTypedArray()

    private fun preferred(profile: dynamic): Array<String> {
        val days = schedule(profile)
        val counts = categories.indices.map { day -> days.count { it == day } }
        val high = counts.max()
        return categories.filterIndexed { i, _ -> high == 0 || counts[i] == high }.toTypedArray()
    }

    private fun derive(profile: dynamic): dynamic {
        if (!truth(profile)) {
            val rules = root.AsterionRules3872
            val default = if (rules?.defaultProfile == null) null else rules.defaultProfile()
            return if (truth(default)) default else null
        }
        val gear = mutableSetOf("body")
        when (profile.equipment) {
            "db" -> gear.addAll(listOf("db", "dumbbells"))
            "gym" -> gear.addAll(listOf("gym", "dumbbells"))
        }
        if (truth(profile.bench)) gear.add("bench")
        if (truth(profile.step)) gear.add("step")
        val materials = profile.materials46
        if (array.isArray(materials) as Boolean) {
            for (i in 0 until (materials.length as Int)) {
                val value = materials[i]
                if (jsTypeOf(value) == "string" && (value.length as Int) <= 70) gear.add(value as String)
            }
        }
        val routine = profile.trainingMode === "routines"
        val result = js("({active:true})")
        result.experience = when (profile.difficulty) {
            "intermediate" -> "intermedio"
            "advanced" -> "avanzado"
            else -> "principiante"
        }
        result.goal = text(profile.goal)
        result.custom_goal = text(profile.finalGoal).take(500)
        result.weekPlan = schedule(profile)
        result.equipment = gear.sorted().toTypedArray()
        result.modality = if (routine) "rutinas" else "misiones"
        result.intensity = if ((number(profile.trainingScale) as Double) > 0.7) "moderada" else "baja"
        result.dailyLimit = clamp(if (routine) profile.routineExercises3878 else profile.dailyMissions, 1, 7, 3)
        result.minutes = clamp(profile.minutes, 10, 90, 30)
        result.restSeconds = clamp(profile.restSeconds, 0, 1800, 90)
        result.organization = if (profile.blockMode22 === "rounds") "rondas" else "series"
        val restrictions = if (truth(profile.restrictions)) profile.restrictions else js("({})")
        val enabled: (dynamic) -> Boolean = { restrictions[it] === true }
        result.restrictions = obj.keys(restrictions).filter(enabled)
        return result
    }

    private fun modeFields(form: dynamic) {
        if (form == null) return
        val routine = form.querySelector("input[name=\"trainingMode\"]:checked")?.value === "routines"
        val routines = form.querySelector(".unified-routines3878")
        if (routines != null) {
            routines.hidden = !routine
            val selects = routines.querySelectorAll("select")
            for (i in 0 until (selects.length as Int)) selects[i].disabled = !routine
        }
        val missions = form.querySelector(".unified-missions3878")
        if (missions != null) missions.hidden = routine
    }

    private fun options(values: List<Pair<String, String>>, current: String): String {
        val escape: (dynamic) -> String = js("esc")
        return values.joinToString("") { (id, label) ->
            val selected = if (id == current) "selected" else ""
            """<option value="${escape(id)}" $selected>${escape(label)}</option>"""
        }
    }

    private fun updateTraining(form: dynamic, choices: dynamic, profile: dynamic) {
        val difficulty = form.querySelector("[name=\"difficulty\"]")
        val field = difficulty?.closest("label") ?: difficulty?.closest(".field")
        val experienceText = "Nivel de experiencia y dificultad"
        if (field != null) {
            val title = field.querySelector("label")
            if (title?.firstChild?.nodeType === 3) title.firstChild.textContent = experienceText
            else if (field.tagName === "LABEL") {
                val children = field.childNodes
                for (i in 0 until (children.length as Int)) {
                    val child = children[i]
                    if (child.nodeType === 3 && truth(child.textContent.trim())) {
                        child.textContent = experienceText
                        break
                    }
                }
            }
            field.classList.add("unified-experience3878")
            choices.before(field)
        }
        val missionLabel = form.querySelector("select[name=\"dailyMissions\"]")?.closest("label")
        val missions = document.createElement("div")
        missions.className = "unified-missions3878"
        missions.innerHTML = """<h4>Misiones diarias</h4><p class="footnote">Elige de 1 a 7 misiones. Esta cantidad no limita las rutinas.</p>"""
        if (missionLabel != null) missions.append(missionLabel)
        choices.after(missions)
        val minuteChoices = listOf(10, 20, 30, 45)
        val currentMinutes = minuteChoices.firstOrNull { it.toDouble() == number(profile.minutes) as Double } ?: 30
        val minutes = options(minuteChoices.map { it.toString() to "$it minutos" }, currentMinutes.toString())
        val lengths = options((1..7).map { it.toString() to if (it == 1) "1 ejercicio" else "$it ejercicios" },
            clamp(profile.routineExercises3878, 1, 7, 4).toString())
        val routines = document.createElement("div")
        routines.className = "unified-routines3878"
        routines.innerHTML = """<h4>Configuración de Rutinas</h4><div class="fields"><label>Duración aproximada por rutina<select name="routineMinutes3878">$minutes</select></label><label>Cantidad objetivo de ejercicios por rutina<select name="routineExercises3878">$lengths</select></label></div><p class="footnote">El generador dinámico respeta el tiempo, los ejercicios compatibles y los límites del perfil. Las rutinas predefinidas conservan su estructura.</p>"""
        missions.after(routines)
    }

    private fun validZone(zone: dynamic): Boolean {
        if (zone === "system") return true
        if (jsTypeOf(zone) != "string" || (zone.length as Int) > 60) return false
        val intl: dynamic = js("Intl")
        val options = js("({})")
        options.timeZone = zone
        return try { truth(intl.DateTimeFormat("es", options)) } catch (_: dynamic) { false }
    }

    private fun enhance(html: dynamic): dynamic {
        val wrap = document.createElement("div")
        wrap.innerHTML = html
        val form = wrap.querySelector("#profileForm") ?: return html
        val panels = form.querySelectorAll(".portable-settings3868,.ar-settings3872")
        for (i in 0 until (panels.length as Int)) panels[i].remove()
        val notes = wrap.querySelectorAll(".portable-plan3869")
        for (i in 0 until (notes.length as Int)) {
            val note = notes[i]
            if ((note.textContent as String).contains("Configuración inicial de Asterion", ignoreCase = true)) note.remove()
        }
        val profile = state?.profile ?: js("({})")
        val choices = form.querySelector(".training-mode-block")?.querySelector(".mode-choices")
        if (choices != null) updateTraining(form, choices, profile)
        val name = form.querySelector("[name=\"name\"]")
        val first = name?.closest(".profile-block") ?: form.querySelector(".profile-block")
        if (first != null && form.querySelector("select[name=\"timeZone\"]") == null) {
            val old = state?.portable3868?.timeZone
            val zone = when {
                validZone(profile.timeZone) -> profile.timeZone as String
                validZone(old) -> old as String
                else -> "system"
            }
            val zones = mutableListOf("system" to "Automática (dispositivo)", "America/Lima" to "América / Lima",
                "UTC" to "UTC", "Europe/Madrid" to "Europa / Madrid")
            if (zones.none { it.first == zone }) zones.add(zone to zone)
            val field = document.createElement("div")
            field.className = "field unified-timezone3878"
            field.innerHTML = """<label class="label" for="profileTimeZone3878">Zona horaria<select id="profileTimeZone3878" name="timeZone">${options(zones, zone)}</select></label><p class="footnote">Define los días del calendario y las revisiones. El valor automático utiliza la zona horaria del teléfono.</p>"""
            val anchor = name?.closest(".field")
            if (anchor == null) first.append(field) else anchor.after(field)
        }
        modeFields(form)
        return wrap.innerHTML
    }

    private fun installHooks() {
        val forward: dynamic = js("(function(callback){return function(){return callback(this,arguments)}})")
        val form = root.profileForm
        if (jsTypeOf(form) == "function") {
            val call: (dynamic, dynamic) -> dynamic = { receiver, args -> enhance(form.apply(receiver, args)) }
            root.profileForm = forward(call)
        }
        val mode = root.trainingModeFields11
        if (jsTypeOf(mode) == "function") {
            val call: (dynamic, dynamic) -> dynamic = { receiver, args ->
                val result = mode.apply(receiver, args)
                modeFields(args[0])
                result
            }
            root.trainingModeFields11 = forward(call)
        }
        val submit = root.submitProfile
        if (jsTypeOf(submit) == "function") {
            val call: (dynamic, dynamic) -> dynamic = { receiver, args ->
                val prior = state?.profile
                val result = submit.apply(receiver, args)
                if (truth(state?.profile) && state.profile !== prior) {
                    val rules = state.asterion3872
                    if (truth(rules?.rules) && rules.version === 1) {
                        rules.profile = derive(state.profile)
                        if (!truth(rules.rules.initialExperienceDate)) {
                            val engine = root.Engine
                            val date = if (engine?.dateKey == null) null else engine.dateKey()
                            rules.rules.initialExperienceDate = if (truth(date)) date else js("new Date().toISOString().slice(0,10)")
                        }
                    }
                    val integration = root.HunterIntegration3869
                    if (integration?.preferenceChanged != null) integration.preferenceChanged()
                    if (jsTypeOf(root.save) == "function") root.save()
                }
                result
            }
            root.submitProfile = forward(call)
        }
    }

    fun install() {
        installHooks()
        val api = js("({version:'38.78'})")
        api.schedule = ::schedule
        api.preferred = ::preferred
        api.derive = ::derive
        api.enhanceProfile = ::enhance
        api.modeFields = ::modeFields
        val audit: () -> dynamic = {
            val report = js("({source:'state.profile',favorites:[],configurationPanels:0,automaticGuidance:true,automaticReviews:true})")
            report.favorites = preferred(state?.profile)
            report
        }
        api.audit = audit
        root.HunterProfileUnified3878 = obj.freeze(api)
        if (document.getElementById("profileForm") != null && jsTypeOf(root.render) == "function") {
            val update: () -> Unit = {
                if (document.getElementById("profileForm") != null && !truth(root.Runtime20?.initializing)) root.render()
            }
            root.requestAnimationFrame(update)
        }
    }
}

fun main() {
    val root: dynamic = js("window")
    if (root.HunterProfileUnified3878 == null) UnifiedProfile(root, js("document")).install()
}
