private class MissionEditor {
    private val truth: dynamic = js("Boolean")
    private val objects: dynamic = js("Object")
    private val arrays: dynamic = js("Array")
    private val reflect: dynamic = js("Reflect")
    private val number: dynamic = js("Number")
    private val query: (String) -> dynamic get() = js("$")
    private val engine: dynamic get() = js("E")
    private val adapter: dynamic get() = js("Adapt")
    private val profile: dynamic get() = js("state.profile")

    private fun truthy(value: dynamic): Boolean = truth(value) as Boolean

    private fun locked(item: dynamic, log: dynamic): Boolean {
        val finished: (dynamic, dynamic) -> dynamic = js("itemFinished")
        val done: (dynamic) -> Boolean = { truthy(it.done) }
        return truthy(item) && (truthy(finished(item, log)) || truthy(item.actual.some(done)))
    }

    private fun edit(date: dynamic, assignDate: (dynamic) -> Unit) {
        val dayByDate: (dynamic) -> dynamic = js("dayByDate")
        val ensureTasks: (dynamic) -> dynamic = js("ensureTasks")
        val escape: (dynamic) -> String = js("esc")
        val modal: (String) -> Unit = js("modal")
        val logs: dynamic = js("state.logs")
        val day = dayByDate(date)
        if (!truthy(day) || date < engine.dateKey()) return
        assignDate(date)
        val log = logs[date]
        val title = escape(if (truthy(day.title)) day.title else "Mi misión diaria")
        val markup = buildString {
            append("""<span class="eyebrow">EDITOR · $date</span><h2>Modifica tu misión</h2><form onsubmit="saveMissionEdit(event)"><label class="label" for="missionTitle">Nombre de la misión</label><input id="missionTitle" maxlength="100" required value="$title"><div class="mission-edit-list">""")
            val items = day.items
            for (index in 0 until (items.length as Int)) {
                if (!(reflect.has(items, index) as Boolean)) continue
                val item = items[index]
                val existing = if (log == null) null else log.items[index]
                val locked = locked(existing, log)
                val disabled = if (locked) "disabled" else ""
                val kept = if (locked) " · registro conservado" else ""
                val exercise = engine.byId(item.id)
                val itemTitle = escape(if (truthy(item.missionTitle)) item.missionTitle else exercise.name)
                val seconds = engine.unitOf(item) === "sec"
                val unit = if (seconds) "Segundos" else "Repeticiones"
                val limit = if (seconds) 600 else 50
                append("""<div class="mission-edit-item"><label class="label" for="mi-title-$index">Objetivo ${index + 1}</label><input id="mi-title-$index" maxlength="100" value="$itemTitle" required><small>${exercise.name}$kept</small><div class="fields"><label>Series<input id="mi-sets-$index" type="number" min="1" max="5" step="1" required value="${item.sets}" $disabled></label><label>$unit<input id="mi-target-$index" type="number" min="1" max="$limit" step="1" required value="${item.target}" $disabled></label></div>""")
                if (!locked) append("""<label class="row remove-mission"><input type="checkbox" id="mi-remove-$index"> Quitar este ejercicio</label>""")
                append("</div>")
            }
            append("</div><h3>Misiones personales</h3>")
            val tasks = ensureTasks(day)
            for (index in 0 until (tasks.length as Int)) {
                if (!(reflect.has(tasks, index) as Boolean)) continue
                val task = tasks[index]
                val done = truthy(task.done)
                val disabled = if (done) "disabled" else ""
                val hint = if (done) "Completada: desmárcala para editarla." else "Deja el campo vacío si quieres quitarla."
                append("""<div class="field"><input aria-label="Misión personal ${index + 1}" id="mt-$index" value="${escape(task.text)}" maxlength="120" $disabled><small>$hint</small></div>""")
            }
            append("""<div class="field"><label class="label" for="newTask">Añadir una misión personal</label><input id="newTask" maxlength="120" placeholder="Ej.: seguir la indicación que recibí hoy"></div><div class="field"><label class="label" for="newExercise">Añadir ejercicio filtrado por mi perfil</label><select id="newExercise"><option value="">No añadir ejercicio</option>""")
            val exercises = engine.exercises
            for (index in 0 until (exercises.length as Int)) {
                if (!(reflect.has(exercises, index) as Boolean)) continue
                val exercise = exercises[index]
                if (exercise.variant === "base" && truthy(adapter.allowed(exercise, profile))) {
                    append("""<option value="${exercise.id}">${exercise.name}</option>""")
                }
            }
            append("""</select></div><div class="notice">Los cambios se aplican solo a este día. Los ejercicios con cantidades registradas se conservan; añade o modifica objetivos pendientes.</div><button class="btn primary full" type="submit">Guardar cambios de misión</button></form>""")
        }
        modal(markup)
        reflect.deleteProperty(query("#modal").dataset, "mission")
    }

    private fun pendingLog(item: dynamic): dynamic {
        val record = objects.assign(js("({})"), item)
        record.missionState = false
        val length = js("({})")
        length.length = item.sets
        val createSet: () -> dynamic = {
            val actual = js("({done:false,value:0,kg:0})")
            actual.value = item.target
            actual
        }
        record.actual = arrays.from(length, createSet)
        return record
    }

    private fun saveEdit(event: dynamic, date: dynamic): dynamic {
        event.preventDefault()
        if (!truthy(event.target.reportValidity())) return js("undefined")
        val dayByDate: (dynamic) -> dynamic = js("dayByDate")
        val ensureTasks: (dynamic) -> dynamic = js("ensureTasks")
        val toast: (String) -> dynamic = js("toast")
        val logs: dynamic = js("state.logs")
        val day = dayByDate(date)
        val log = logs[day.date]
        val next: dynamic = js("[]")
        val nextLog: dynamic = js("[]")
        for (index in 0 until (day.items.length as Int)) {
            val item = day.items[index]
            val previous = if (log == null) null else log.items[index]
            val locked = locked(previous, log)
            if (!locked && truthy(query("#mi-remove-$index")?.checked)) continue
            val updated = objects.assign(js("({})"), item)
            updated.missionTitle = query("#mi-title-$index").value.trim()
            updated.sets = if (locked) item.sets else number(query("#mi-sets-$index").value)
            updated.target = if (locked) item.target else number(query("#mi-target-$index").value)
            next.push(updated)
            if (locked) {
                val retained = objects.assign(js("({})"), previous)
                retained.missionTitle = updated.missionTitle
                nextLog.push(retained)
            } else {
                nextLog.push(pendingLog(updated))
            }
        }

        val exerciseId = query("#newExercise").value
        if (truthy(exerciseId)) {
            if (!truthy(adapter.allowed(engine.byId(exerciseId), profile))) {
                return toast("Ese ejercicio ya no coincide con tu perfil.")
            }
            val intensity: dynamic = js("TrainingIntensity")
            val plans: dynamic = js("GoalPlans")
            val item = intensity.dose(engine.byId(exerciseId), plans.adjusted(profile), day.kind)
            next.push(item)
            nextLog.push(pendingLog(item))
        }
        if ((next.length as Int) > 12) return toast("Máximo 12 ejercicios por día.")

        val tasks: dynamic = js("[]")
        val existingTasks = ensureTasks(day)
        for (index in 0 until (existingTasks.length as Int)) {
            if (!(reflect.has(existingTasks, index) as Boolean)) continue
            val task = objects.assign(js("({})"), existingTasks[index])
            task.text = query("#mt-$index").value.trim()
            if (truthy(task.text)) tasks.push(task)
        }
        val added = query("#newTask").value.trim()
        if (truthy(added)) {
            val task = js("({})")
            task.id = js("'t' + Date.now().toString(36)")
            task.text = added
            task.done = false
            tasks.push(task)
        }
        if ((tasks.length as Int) > 12) return toast("Máximo 12 misiones personales por día.")
        if ((tasks.length as Int) == 0 && (next.length as Int) == 0) return toast("Deja al menos una misión.")

        day.title = query("#missionTitle").value.trim()
        day.customized = true
        day.items = next
        day.tasks = tasks
        if (truthy(log)) {
            val recompute: (dynamic) -> Unit = js("recomputeMission")
            log.items = nextLog
            recompute(day)
        }
        val save: () -> Unit = js("save")
        val showMission: (dynamic) -> Unit = js("showMission")
        save()
        query("#modal").close()
        showMission(day.date)
        toast("Misión actualizada.")
        return js("undefined")
    }

    fun register(api: dynamic) {
        api.editMission = ::edit
        api.saveMissionEdit = ::saveEdit
    }
}

internal fun installMissionEditor(api: dynamic) {
    MissionEditor().register(api)
}
