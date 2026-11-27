private class Missions {
    private val truth: dynamic = js("Boolean")
    private val arrays: dynamic = js("Array")
    private val objects: dynamic = js("Object")
    private val json: dynamic = js("JSON")

    private fun truthy(value: dynamic): Boolean = truth(value) as Boolean

    private fun findDay(date: dynamic): dynamic {
        val cycles: dynamic = js("state.cycles")
        val cycleDays: (dynamic) -> dynamic = { it.days }
        val matches: (dynamic) -> Boolean = { it.date === date }
        return cycles.flatMap(cycleDays).find(matches)
    }

    private fun tasks(day: dynamic): dynamic {
        val logs: dynamic = js("state.logs")
        if (!(arrays.isArray(day.tasks) as Boolean)) {
            day.tasks = js("[]")
            if (!truthy(day.items.length)) {
                val rest = js("({id:'rest',text:'Completar mi recuperación del día'})")
                rest.done = truthy(logs[day.date]?.complete)
                day.tasks.push(rest)
            }
        }
        return day.tasks
    }

    private fun dayLog(day: dynamic): dynamic {
        val logs: dynamic = js("state.logs")
        if (!truthy(logs[day.date])) {
            val log = js("({})")
            log.kind = day.kind
            log.complete = false
            log.rpe = 0
            log.pain = false
            log.note = ""
            val buildItem: (dynamic) -> dynamic = { item ->
                val copy = objects.assign(js("({})"), item)
                val length = js("({})")
                length.length = item.sets
                val buildSet: () -> dynamic = {
                    val set = js("({done:false,value:0,kg:0})")
                    set.value = item.target
                    set
                }
                copy.actual = arrays.from(length, buildSet)
                copy
            }
            log.items = day.items.map(buildItem)
            logs[day.date] = log
        }
        return logs[day.date]
    }

    private fun finished(item: dynamic, log: dynamic): dynamic {
        val missionState = item?.missionState
        if (item != null && jsTypeOf(missionState) != "undefined") return missionState
        val done: (dynamic) -> Boolean = { truthy(it.done) }
        return truthy(item?.selfReported) || truthy(item?.actual?.every(done)) || truthy(log?.complete)
    }

    private fun summary(day: dynamic): dynamic {
        val logs: dynamic = js("state.logs")
        val log = logs[day.date]
        val dayTasks = tasks(day)
        var done = 0
        val items = day.items
        for (index in 0 until (items.length as Int)) {
            if (js("Reflect").has(items, index) as Boolean && truthy(finished(if (log == null) null else log.items[index], log))) done++
        }
        val completed: (dynamic) -> Boolean = { truthy(it.done) }
        val result = js("({})")
        result.done = done + (dayTasks.filter(completed).length as Int)
        result.total = items.length + dayTasks.length
        result.complete = truthy(log?.complete)
        return result
    }

    private fun recompute(day: dynamic): Boolean {
        val log = dayLog(day)
        val sum = summary(day)
        log.complete = sum.total > 0 && sum.done === sum.total && !truthy(log.pain)
        log.manual = true
        log.missionTasks = json.parse(json.stringify(day.tasks))
        if (log.complete as Boolean) log.completedAt = js("new Date().toISOString()")
        else js("Reflect").deleteProperty(log, "completedAt")
        val save: () -> Unit = js("save")
        save()
        return log.complete as Boolean
    }

    private fun open(date: dynamic, assignDate: (dynamic) -> Unit) {
        val engine: dynamic = js("E")
        val target = if (jsTypeOf(date) == "undefined") engine.dateKey() else date
        assignDate(target)
        val day = findDay(target)
        if (!truthy(day)) return
        val window: (dynamic, Boolean) -> String = js("missionWindow")
        val modal: (String) -> Unit = js("modal")
        val query: (String) -> dynamic = js("$")
        modal(window(day, true))
        query("#modal").dataset.mission = target
    }

    private fun refresh() {
        val query: (String) -> dynamic = js("$")
        val modal = query("#modal")
        val date = if (truthy(modal.open)) modal.dataset.mission else null
        if (!truthy(date)) {
            val render: () -> Unit = js("render")
            render()
            return
        }
        val scrollTop = modal.scrollTop
        val window: (dynamic, Boolean) -> String = js("missionWindow")
        modal.innerHTML = window(findDay(date), true) +
            """<button class="btn ghost full" onclick="document.querySelector('#modal').close()">Cerrar</button>"""
        modal.scrollTop = scrollTop
    }

    private fun toggleItem(date: dynamic, index: dynamic, checked: dynamic) {
        val engine: dynamic = js("E")
        if (date !== engine.dateKey()) return
        val day = findDay(date)
        val log = dayLog(day)
        val adapter: dynamic = js("Adapt")
        val profile: dynamic = js("state.profile")
        val refresh: () -> Unit = js("refreshMission")
        if (truthy(checked) && (truthy(log.pain) || !truthy(adapter.allowed(engine.byId(day.items[index].id), profile)))) {
            refresh()
            return
        }
        log.items[index].missionState = checked
        log.items[index].selfReported = checked
        val completed = recompute(day)
        refresh()
        if (completed) {
            val pulse: () -> Unit = js("pulse")
            pulse()
        }
    }

    private fun toggleTask(date: dynamic, index: dynamic, checked: dynamic) {
        val engine: dynamic = js("E")
        if (date !== engine.dateKey()) return
        val day = findDay(date)
        tasks(day)[index].done = checked
        val complete = recompute(day)
        val refresh: () -> Unit = js("refreshMission")
        refresh()
        if (complete) {
            val pulse: () -> Unit = js("pulse")
            pulse()
        }
    }

    private fun note(date: dynamic, text: dynamic) {
        val engine: dynamic = js("E")
        if (date !== engine.dateKey()) return
        dayLog(findDay(date)).note = text.slice(0, 500)
        val save: () -> Unit = js("save")
        save()
    }

    fun register(api: dynamic) {
        api.dayByDate = ::findDay
        api.ensureTasks = ::tasks
        api.ensureDayLog = ::dayLog
        api.itemFinished = ::finished
        api.missionSummary = ::summary
        api.recomputeMission = ::recompute
        api.showMission = ::open
        api.missionWindow = ::renderMissionWindow
        api.refreshMission = ::refresh
        api.toggleMissionItem = ::toggleItem
        api.toggleMissionTask = ::toggleTask
        api.saveMissionNote = ::note
    }
}

internal fun installMissions(api: dynamic) {
    Missions().register(api)
}
