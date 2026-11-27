private class ProfileSync(private val root: dynamic) {
    private val previous = root.submitProfile
    private val obj: dynamic = js("Object")
    private val json: dynamic = js("JSON")
    private val array: dynamic = js("Array")
    private val boolean: dynamic = js("Boolean")
    private val state: dynamic get() = js("state")
    private val stats = js("({version:'38.79',changed:0,unchanged:0,updated:0,preserved:0,failed:0,last:'none'})")
    private var busy = false

    private fun truth(value: dynamic): Boolean = boolean(value) as Boolean
    private fun clone(value: dynamic): dynamic = when {
        jsTypeOf(value) == "undefined" -> js("undefined")
        value == null -> null
        else -> json.parse(json.stringify(value))
    }
    private fun canonical(value: dynamic): dynamic {
        if (array.isArray(value) as Boolean) return value.map(::canonical)
        if (value == null || jsTypeOf(value) != "object") return value
        val keys = obj.keys(value).sort()
        val entries = js("[]")
        for (i in 0 until (keys.length as Int)) {
            val pair = js("[]")
            pair.push(keys[i], canonical(value[keys[i]]))
            entries.push(pair)
        }
        return obj.fromEntries(entries)
    }
    private fun signature(value: dynamic): dynamic = json.stringify(canonical(value ?: null))

    private fun findDay(date: dynamic): dynamic {
        val cycles = state?.cycles ?: return null
        for (index in 0 until (cycles.length as Int)) {
            val cycle = cycles[index]
            val match: (dynamic) -> Boolean = { it.date === date }
            val i = cycle.days?.findIndex(match) ?: -1
            if (i >= 0) {
                val result = js("({})")
                result.cycle = cycle
                result.index = i
                result.day = cycle.days[i]
                return result
            }
        }
        return null
    }

    private fun logFor(date: dynamic): dynamic {
        val logs = state?.logs
        return if (logs == null) js("undefined") else logs[date]
    }

    private fun restoreLog(date: dynamic, log: dynamic) {
        if (!truth(state.logs)) state.logs = js("({})")
        state.logs[date] = log
    }

    private fun save() {
        if (jsTypeOf(root.save) == "function") root.save()
    }

    private fun unchanged(date: dynamic, day: dynamic, log: dynamic) {
        stats.unchanged++
        stats.last = "unchanged"
        val match = findDay(date)
        var restored = false
        if (match != null && truth(day) && signature(match.day) !== signature(day)) {
            match.cycle.days[match.index] = day
            restored = true
        }
        if (truth(log) && signature(logFor(date)) !== signature(log)) {
            restoreLog(date, log)
            restored = true
        }
        if (restored) save()
    }

    private fun persisted(after: dynamic): Boolean {
        try {
            val storage: dynamic = js("localStorage")
            val key: dynamic = js("KEY")
            val raw = storage.getItem(key)
            val disk = json.parse(if (truth(raw)) raw else "null")
            if (signature(disk?.profile) === after) return true
            stats.last = "not-persisted"
        } catch (_: dynamic) {
            stats.last = "storage-error"
        }
        stats.failed++
        return false
    }

    private fun reconcile(date: dynamic, before: dynamic, day: dynamic, log: dynamic) {
        if (findDay(date) == null && jsTypeOf(root.currentCycle) == "function") {
            try { root.currentCycle() } catch (_: dynamic) { /* El perfil puede restringir el plan. */ }
        }
        val match = findDay(date)
        if (match == null) {
            stats.last = "no-day"
            return
        }
        if (truth(log?.complete) || truth(day?.customized) || truth(day?.routineId) || truth(day?.creatorAssigned) || truth(day?.manual)) {
            if (truth(day)) match.cycle.days[match.index] = day
            if (truth(log)) restoreLog(date, log)
            val restored = findDay(date)?.day
            if (truth(restored)) {
                restored.dailyFixed3828 = date
                val stable = root.HunterStable3828
                val key = if (stable?.safetyKey == null) null else stable.safetyKey(state.profile)
                restored.dailySafety3828 = if (truth(key)) key else restored.dailySafety3828
            }
            save()
            stats.preserved++
            stats.last = "completed-or-custom"
            return
        }
        if (truth(log)) restoreLog(date, log)
        try {
            val planner = root.HunterProfile3829
            if (jsTypeOf(planner?.regenerate) != "function") throw IllegalStateException("Replanificador no disponible")
            val result = planner.regenerate(before, log, day)
            val cycle = findDay(date)?.cycle
            val strict = root.HunterStrict3838
            if (truth(cycle) && truth(strict?.apply) && truth(state.profile)) {
                strict["apply"](cycle, state.profile, if (truth(state.logs)) state.logs else js("({})"), date)
            }
            save()
            if (result?.status === "updated" || result?.status === "review") {
                stats.updated++
                stats.last = result.status
                if (jsTypeOf(root.render) == "function") root.render()
            } else {
                stats.last = if (truth(result?.status)) result.status else "no-result"
            }
        } catch (error: dynamic) {
            stats.failed++
            stats.last = "recalculation-error"
            val console: dynamic = js("console")
            console.error("No se pudo sincronizar la misión actual con el perfil", error)
            if (jsTypeOf(root.toast) == "function") root.toast("Perfil guardado. No se pudo recalcular la misión de hoy.")
        }
    }

    private fun submit(receiver: dynamic, args: dynamic): dynamic {
        if (busy || args[0]?.target?.id !== "profileForm") return previous.apply(receiver, args)
        val before = clone(state?.profile)
        val beforeSignature = signature(before)
        val date = root.Engine.dateKey()
        val match = findDay(date)
        val day = clone(if (match == null) js("undefined") else match.day)
        val log = clone(logFor(date))
        busy = true
        val answer = try { previous.apply(receiver, args) } finally { busy = false }
        if (!truth(before) || !truth(state?.profile)) return answer
        val after = signature(state.profile)
        if (beforeSignature === after) unchanged(date, day, log)
        else if (persisted(after)) {
            stats.changed++
            reconcile(date, before, day, log)
        }
        return answer
    }

    fun install() {
        val forward: dynamic = js("(function(callback){return function(){return callback(this,arguments)}})")
        root.submitProfile = forward(::submit)
        val api = js("({version:'38.79'})")
        val diagnostics: () -> dynamic = { obj.assign(js("({})"), stats) }
        api.diagnostics = diagnostics
        root.HunterProfileSync3879 = obj.freeze(api)
    }
}

fun main() {
    val root: dynamic = js("window")
    if (root.HunterProfileSync3879 == null && jsTypeOf(root.submitProfile) == "function") ProfileSync(root).install()
}
