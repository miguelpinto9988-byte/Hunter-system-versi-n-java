@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterdynamic381

import hunter.browser.*

@JsName("console") private external var global_console: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterDynamic381() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var S: dynamic = absent
        var A: dynamic = absent
        var W: dynamic = absent
        var VERSION: dynamic = absent
        var TYPES: dynamic = absent
        var CATEGORY: dynamic = absent
        var SPANISH: dynamic = absent
        var LOWER: dynamic = absent
        var UPPER: dynamic = absent
        var byId: dynamic = absent
        var available: dynamic = absent
        var fieldNames: dynamic = absent
        var asJSON: dynamic = absent
        var hash: dynamic = absent
        var profileKey: dynamic = absent
        var injury: dynamic = absent
        var gate: dynamic = absent
        var activeLog: dynamic = absent
        var locked: dynamic = absent
        var recent: dynamic = absent
        var feedback: dynamic = absent
        var compatible: dynamic = absent
        var poolMemo46: dynamic = absent
        var pool: dynamic = absent
        var goalGroups: dynamic = absent
        var sortPool: dynamic = absent
        var historyIds: dynamic = absent
        var dose: dynamic = absent
        var declaredModes3880: dynamic = absent
        var matchesMode3880: dynamic = absent
        var chooseCombined3880: dynamic = absent
        var choose: dynamic = absent
        var planKey: dynamic = absent
        var patch: dynamic = absent
        var templates: dynamic = absent
        gate = fun(p: dynamic): dynamic {
            if (truth((!truth(p)))) {
                return jsonValue("{\"ok\":false,\"reason\":\"Falta completar el perfil.\"}")
            }
            if (truth(jsAnd(injury(p)) { onPresent(root.HunterPlanner379) { value18 -> value18.status } })) {
                val info: dynamic = root.HunterPlanner379.status(p)
                return jsObject { obj19 ->
                    copyFields(obj19, info)
                    obj19["ok"] = (!truth((!truth(info.ok))))
                    obj19["reduced"] = (info.reduced !== false)
                }
            }
            val m: dynamic = A.mode(p)
            return jsObject { obj20 ->
                obj20["ok"] = (!truth((!truth(m.auto))))
                obj20["reduced"] = (!truth((!truth(m.reduced))))
                obj20["reason"] = jsOr(m.reason) { "" }
            }
        }
        activeLog = fun(l: dynamic): dynamic {
            return (!truth((!truth(jsAnd(l) { jsOr(jsOr(jsOr(W.active(l)) { l.complete }) { l.manual }) { l.pain } }))))
        }
        locked = fun(day: dynamic, logs: dynamic): dynamic {
            val l: dynamic = onPresent(logs) { value25 -> value25[day.date] }
            return (!truth((!truth(jsOr(jsOr(jsOr(jsOr(jsOr(day.customized) { day.routineId }) { day.creatorAssigned }) { day.manual }) { activeLog(l) }) { onPresent(day.tasks) { value26 -> value26.some((fun(t: dynamic): dynamic {
                return t.done
            })) } }))))
        }
        recent = fun(logs: dynamic, date: dynamic, arg29: dynamic): dynamic {
            var days: dynamic = absent
            days = definedOr(arg29) { 14 }
            return global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg30: dynamic): dynamic {
                var day: dynamic = absent
                var l: dynamic = absent
                val unpacked31: dynamic = fromIterable(arg30)
                day = unpacked31[0]
                l = unpacked31[1]
                return jsAnd(jsAnd(jsLess(day, date)) { jsAtLeast(day, E["plus"](date, (-numeric(days)))) }) { l }
            }))
        }
        feedback = fun(logs: dynamic, date: dynamic): dynamic {
            val rows: dynamic = recent(logs, date, 7)
            val done: dynamic = rows.map((fun(arg36: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked37: dynamic = fromIterable(arg36)
                l = unpacked37[1]
                return l
            })).filter((fun(l: dynamic): dynamic {
                return jsAnd(jsAnd(l.complete) { (!truth(l.pain)) }) { (!truth(l.manual)) }
            }))
            val efforts: dynamic = done.map((fun(l: dynamic): dynamic {
                return numeric(l.rpe)
            })).filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAtLeast(x, 1)) { jsAtMost(x, 10) }
            }))
            val avg: dynamic = (if (truth(efforts.length)) jsDivide(efforts.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b)
            }), 0), efforts.length) else 0)
            val strain: dynamic = jsOr(rows.some((fun(arg38: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked39: dynamic = fromIterable(arg38)
                l = unpacked39[1]
                return jsOr(jsOr(l.pain) { l.fatigue }) { jsAtLeast(l.rpe, 8) }
            }))) { (!truth((!truth(onPresent(root.HunterConstraints3811) { value40 -> run { val owner42: dynamic = value40; onPresent(owner42.symptomTrend) { method41 -> onPresent(invokeWith(method41, owner42, arrayOf<dynamic>(logs, date))) { value43 -> value43.recentSymptom } } } })))) }
            val adherence: dynamic = (if (truth(rows.length)) jsDivide(done.length, rows.length) else 0)
            return jsObject { obj44 ->
                obj44["strain"] = strain
                obj44["avg"] = avg
                obj44["adherence"] = adherence
                obj44["completed"] = done.length
                obj44["canProgress"] = jsAnd(jsAnd(jsAnd(jsAnd(jsAtLeast(done.length, 2)) { jsAtLeast(adherence, 0.8) }) { jsMore(avg, 0) }) { jsAtMost(avg, 7) }) { (!truth(strain)) }
            }
        }
        compatible = fun(e: dynamic, p: dynamic, risk: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(e))) { (!truth(byId.has(e.id))) }) { (!truth(root.HunterGroups49.eligible(e, p))) })) {
                return false
            }
            if (truth((!truth(onPresent(root.HunterMaterials3814) { value47 -> run { val owner49: dynamic = value47; onPresent(owner49.eligible) { method48 -> invokeWith(method48, owner49, arrayOf<dynamic>(e, p)) } } })))) {
                return false
            }
            if (truth(risk)) {
                return (!truth((!truth(nullableOr(onPresent(root.HunterConstraints3811) { value50 -> run { val owner52: dynamic = value50; onPresent(owner52.allowed) { method51 -> invokeWith(method51, owner52, arrayOf<dynamic>(e, p)) } } }) { onPresent(root.HunterPlanner379) { value53 -> run { val owner55: dynamic = value53; onPresent(owner55.approved) { method54 -> invokeWith(method54, owner55, arrayOf<dynamic>(e, p)) } } } }))))
            }
            try {
                if (truth((!truth(A.allowed(e, p))))) {
                    return false
                }
            } catch (_: dynamic) {
                return false
            }
            if (truth(jsAnd((e.equipment === "db")) { (!truth(dynamicValue(jsonValue("[\"db\",\"gym\"]")).includes(p.equipment))) })) {
                return false
            }
            if (truth(jsAnd(e.gymOnly23) { (p.equipment !== "gym") })) {
                return false
            }
            if (truth(jsAnd((e.equipment === "gym")) { (p.equipment !== "gym") })) {
                return false
            }
            val difficulty: dynamic = dynamicValue(jsonValue("[\"basic\",\"intermediate\",\"advanced\"]")).indexOf(p.difficulty)
            if (truth(jsMore(root.HunterGroups49.level(e), global_Math.max(0, (if (truth(jsLess(difficulty, 0))) (if (truth((p.experience === "regular"))) 1 else 0) else difficulty))))) {
                return false
            }
            return true
        }
        pool = fun(p: dynamic, kind: dynamic): dynamic {
            val key: dynamic = jsAdd(jsAdd(kind, "|"), asJSON(dynamicValue(jsArray { array59 ->
                appendAll(array59, fieldNames)
                array59.push("physicalLimits")
                array59.push("preferences")
                array59.push("goalAutoRestrictions374")
            }).map((fun(k: dynamic): dynamic {
                return arrayOf<dynamic>(k, onPresent(p) { value60 -> value60[k] })
            }))))
            if (truth(poolMemo46.has(key))) {
                return poolMemo46["get"](key).slice()
            }
            val status: dynamic = gate(p)
            if (truth((!truth(status.ok)))) {
                return js("[]")
            }
            val result: dynamic = available.filter((fun(e: dynamic): dynamic {
                return jsAnd(root.HunterGroups49.inKind(e, kind)) { compatible(e, p, injury(p)) }
            }))
            poolMemo46["set"](key, result)
            if (truth(jsMore(poolMemo46.size, 12))) {
                poolMemo46.delete(poolMemo46.keys().next()["value"])
            }
            return result.slice()
        }
        sortPool = fun(xs: dynamic, p: dynamic, seed: dynamic, kind: dynamic, previous: dynamic, used: dynamic, counts: dynamic, weekLoad: dynamic, patternCounts: dynamic, tracker: dynamic, date: dynamic, arg63: dynamic): dynamic {
            var selected: dynamic = absent
            selected = definedOr(arg63) { js("[]") }
            val focus: dynamic = jsOr(p.focus) { "general" }
            val order: dynamic = jsOr(goalGroups[focus]) { goalGroups.general }
            return xs.map((fun(e: dynamic): dynamic {
                val was: dynamic = (if (truth(previous.has(e.id))) 45 else 0)
                val distinct: dynamic = jsOr(counts[e.group]) { 0 }
                var priority: dynamic = (if (truth(order.includes(e.group))) jsSubtract(26, jsMultiply(order.indexOf(e.group), 2)) else 0)
                if (truth((kind === "recovery"))) {
                    priority = 10
                }
                if (truth(jsAnd((kind === "cardio")) { (p.goal === "endurance") })) {
                    run {
                        priority = jsAdd(priority, 12)
                    }
                }
                if (truth(jsAnd((p.goal === "muscle")) { UPPER.has(e.group) })) {
                    run {
                        priority = jsAdd(priority, 8)
                    }
                }
                if (truth(jsAnd((p.goal === "strength")) { LOWER.has(e.group) })) {
                    run {
                        priority = jsAdd(priority, 8)
                    }
                }
                val hasGear: dynamic = (if (truth((e.equipment === "body"))) 2 else 0)
                val weeklyScore: dynamic = jsOr(onPresent(root.HunterTraining387) { value78 -> run { val owner80: dynamic = value78; onPresent(owner80.score) { method79 -> invokeWith(method79, owner80, arrayOf<dynamic>(e, jsObject { obj81 ->
                    obj81["weekly"] = weekLoad
                    obj81["dayCounts"] = counts
                    obj81["dayPatterns"] = patternCounts
                })) } } }) { 0 }
                val upcomingScore: dynamic = jsOr(onPresent(tracker) { value82 -> run { val owner84: dynamic = value82; onPresent(owner84.score) { method83 -> invokeWith(method83, owner84, arrayOf<dynamic>(e, date)) } } }) { 0 }
                val supportScore: dynamic = jsOr(onPresent(root.HunterConstraints3811) { value85 -> run { val owner87: dynamic = value85; onPresent(owner87.rank) { method86 -> invokeWith(method86, owner87, arrayOf<dynamic>(e, p)) } } }) { 0 }
                val metadataScore: dynamic = jsOr(onPresent(root.HunterMetadata3812) { value88 -> run { val owner90: dynamic = value88; onPresent(owner90.score) { method89 -> invokeWith(method89, owner90, arrayOf<dynamic>(e, p, kind)) } } }) { 0 }
                val rank: dynamic = jsAdd(jsAdd(jsAdd(jsAdd(jsSubtract(jsSubtract(jsSubtract(jsSubtract(jsAdd(jsAdd(jsAdd(root.HunterGroups49.score(e, p, kind, selected), priority), (if (truth(jsAnd(p.dynamicGymPreferred3827) { jsOr(e.gymOnly23) { (e.equipment === "gym") } })) 45 else 0)), hasGear), was), jsMultiply(distinct, 24)), (if (truth(used.has(root.HunterGroups49.family(e)))) 100 else 0)), jsMultiply(e.level, 2)), weeklyScore), upcomingScore), supportScore), metadataScore)
                return jsObject { obj91 ->
                    obj91["e"] = e
                    obj91["rank"] = rank
                    obj91["lottery"] = hash(jsAdd(jsAdd(seed, "|"), e.id))
                }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(jsOr(jsSubtract(b.rank, a.rank)) { jsSubtract(a.lottery, b.lottery) }) { a.e.id.localeCompare(b.e.id) }
            }))
        }
        historyIds = fun(logs: dynamic, date: dynamic): dynamic {
            val prior: dynamic = jsConstruct(global_Set, js("[]"))
            if (true) {
                val iterator94: dynamic = iteratorOf(recent(logs, date, 8))
                while (true) {
                    val next95: dynamic = iterator94.next()
                    if (truth(next95.done)) break
                    var l: dynamic = absent
                    val unpacked96: dynamic = fromIterable(next95.value)
                    l = unpacked96[1]
                    if (truth((!truth(l.complete)))) {
                        continue
                    }
                    if (true) {
                        val iterator97: dynamic = iteratorOf(jsOr(l.items) { js("[]") })
                        while (true) {
                            val next98: dynamic = iterator97.next()
                            if (truth(next98.done)) break
                            var x: dynamic = absent
                            x = next98.value
                            prior.add(x.id)
                        }
                    }
                }
            }
            return prior
        }
        dose = fun(e: dynamic, p: dynamic, kind: dynamic, status: dynamic, history: dynamic, date: dynamic): dynamic {
            val gentle: dynamic = (!truth((!truth(status.reduced))))
            val low: dynamic = history.strain
            val minutes: dynamic = global_Math.max(10, global_Math.min(60, jsOr(numeric(p.minutes)) { 20 }))
            val sets: dynamic = (if (truth(jsOr(jsOr((kind === "recovery")) { gentle }) { low })) 1 else (if (truth(jsAtLeast(minutes, 40))) 3 else 2))
            var target: dynamic = (if (truth((e.unit === "sec"))) (if (truth((kind === "cardio"))) 30 else 20) else (if (truth((kind === "recovery"))) 8 else (if (truth((p.goal === "muscle"))) 10 else 8)))
            if (truth(jsOr(gentle) { low })) {
                target = (if (truth((e.unit === "sec"))) 15 else 6)
            }
            if (truth(jsAnd(jsAnd((!truth(gentle))) { (!truth(low)) }) { history.canProgress })) {
                target = global_Math.min((if (truth((e.unit === "sec"))) 90 else 15), jsAdd(target, (if (truth((e.unit === "sec"))) 5 else 1)))
            }
            return jsObject { obj101 ->
                obj101["id"] = e.id
                obj101["sets"] = sets
                obj101["target"] = target
                obj101["rest"] = (if (truth((kind === "cardio"))) 45 else (if (truth((kind === "recovery"))) 30 else 90))
                obj101["dynamic381"] = true
                obj101["originDate381"] = date
                obj101["preview2D381"] = jsAdd(jsAdd("demos30/", e.id), ".svg")
                obj101["noAutoLoad"] = jsOr(gentle) { low }
                obj101["safetyNote"] = (if (truth(gentle)) "Dosis inicial conservadora; interrumpe si hay dolor nuevo o empeoramiento." else "Respeta técnica y recuperaciones.")
            }
        }
        declaredModes3880 = fun(p: dynamic): dynamic {
            val valid: dynamic = jsonValue("[\"body\",\"db\",\"gym\"]")
            if (truth(global_Array.isArray(onPresent(p) { value104 -> value104.equipmentModes3880 }))) {
                return valid.filter((fun(x: dynamic): dynamic {
                    return p.equipmentModes3880.includes(x)
                }))
            }
            return (if (truth((onPresent(p) { value105 -> value105.equipment } === "gym"))) jsonValue("[\"body\",\"db\",\"gym\"]") else (if (truth((onPresent(p) { value106 -> value106.equipment } === "db"))) jsonValue("[\"body\",\"db\"]") else jsonValue("[\"body\"]")))
        }
        matchesMode3880 = fun(exercise: dynamic, mode: dynamic): dynamic {
            if (truth((!truth(exercise)))) {
                return false
            }
            if (truth((mode === "gym"))) {
                return jsOr((exercise.gymOnly23 === true)) { (exercise.equipment === "gym") }
            }
            if (truth((mode === "db"))) {
                return jsAnd((exercise.equipment === "db")) { (!truth(exercise.gymOnly23)) }
            }
            return jsAnd((exercise.equipment === "body")) { (!truth(exercise.gymOnly23)) }
        }
        chooseCombined3880 = fun(p: dynamic, kind: dynamic, seed: dynamic, logs: dynamic, date: dynamic, usedBases: dynamic, tracker: dynamic, weekIndex: dynamic): dynamic {
            val status: dynamic = gate(p)
            if (truth((!truth(status.ok)))) {
                return jsObject { obj111 ->
                    obj111["items"] = js("[]")
                    obj111["blocked"] = status.reason
                    obj111["requested"] = kind
                }
            }
            if (truth((kind === "rest"))) {
                return jsonValue("{\"items\":[],\"effective\":\"rest\",\"requested\":\"rest\"}")
            }
            val modes: dynamic = declaredModes3880(p)
            if (truth((!truth(modes.length)))) {
                return jsObject { obj112 ->
                    obj112["items"] = js("[]")
                    obj112["blocked"] = "Selecciona al menos un tipo de equipamiento en Perfil."
                    obj112["requested"] = kind
                }
            }
            val baseCount: dynamic = nullableOr(onPresent(root.HunterMaterials3814) { value113 -> run { val owner115: dynamic = value113; onPresent(owner115.sessionCap) { method114 -> invokeWith(method114, owner115, arrayOf<dynamic>(p, kind, status)) } } }) { global_Math.max(1, global_Math.min(7, jsOr(global_Number(p.dailyMissions)) { 3 })) }
            val limit: dynamic = global_Math.max(1, global_Math.min(7, nullableOr(onPresent(root.HunterIntegration3869) { value116 -> run { val owner118: dynamic = value116; onPresent(owner118.activityLimit) { method117 -> invokeWith(method117, owner118, arrayOf<dynamic>(baseCount)) } } }) { baseCount }))
            val streams: dynamic = modes.map((fun(mode: dynamic): dynamic {
                val single: dynamic = jsObject { obj119 ->
                    copyFields(obj119, p)
                    obj119["equipment"] = mode
                    obj119["__singleMode3880"] = true
                    obj119["__onlyMode3880"] = mode
                }
                val outcome: dynamic = choose(single, kind, jsAdd(jsAdd(seed, "|"), mode), logs, date, jsConstruct(global_Set, js("[]")), null, weekIndex)
                return jsObject { obj120 ->
                    obj120["mode"] = mode
                    obj120["items"] = dynamicValue(jsOr(outcome.items) { js("[]") }).filter((fun(x: dynamic): dynamic {
                        return matchesMode3880(E.byId(x.id), mode)
                    }))
                }
            }))
            val result: dynamic = js("[]")
            val seen: dynamic = jsConstruct(global_Set, js("[]"))
            val families: dynamic = jsConstruct(global_Set, js("[]"))
            if (true) {
                var round: dynamic = absent
                round = 0
                var firstIteration121 = true
                while (true) {
                    if (!firstIteration121) {
                        run {
                            val previous122: dynamic = numeric(round)
                            round = jsAdd(previous122, 1)
                        }
                    }
                    firstIteration121 = false
                    if (!truth(jsLess(round, limit))) break
                    var added: dynamic = false
                    if (true) {
                        val iterator123: dynamic = iteratorOf(streams)
                        while (true) {
                            val next124: dynamic = iterator123.next()
                            if (truth(next124.done)) break
                            var stream: dynamic = absent
                            stream = next124.value
                            if (truth(jsAtLeast(result.length, limit))) {
                                break
                            }
                            val index: dynamic = stream.items.findIndex((fun(x: dynamic): dynamic {
                                return jsAnd((!truth(seen.has(x.id)))) { (!truth(families.has(root.HunterGroups49.family(E.byId(x.id))))) }
                            }))
                            if (truth(jsLess(index, 0))) {
                                continue
                            }
                            val x: dynamic = stream.items.splice(index, 1)[0]
                            result.push(x)
                            seen.add(x.id)
                            families.add(root.HunterGroups49.family(E.byId(x.id)))
                            added = true
                        }
                    }
                    if (truth((!truth(added)))) {
                        break
                    }
                }
            }
            if (true) {
                val iterator125: dynamic = iteratorOf(streams)
                while (true) {
                    val next126: dynamic = iterator125.next()
                    if (truth(next126.done)) break
                    var stream: dynamic = absent
                    stream = next126.value
                    if (true) {
                        val iterator127: dynamic = iteratorOf(stream.items)
                        while (true) {
                            val next128: dynamic = iterator127.next()
                            if (truth(next128.done)) break
                            var x: dynamic = absent
                            x = next128.value
                            if (truth(jsAtLeast(result.length, limit))) {
                                break
                            }
                            if (truth((!truth(seen.has(x.id))))) {
                                result.push(x)
                                seen.add(x.id)
                            }
                        }
                    }
                }
            }
            if (truth((!truth(result.length)))) {
                return jsObject { obj129 ->
                    obj129["items"] = js("[]")
                    obj129["blocked"] = "No hay ejercicios compatibles con el equipamiento marcado y las restricciones declaradas."
                    obj129["requested"] = kind
                }
            }
            if (true) {
                val iterator130: dynamic = iteratorOf(result)
                while (true) {
                    val next131: dynamic = iterator130.next()
                    if (truth(next131.done)) break
                    var item: dynamic = absent
                    item = next131.value
                    val ex: dynamic = E.byId(item.id)
                    if (truth(ex)) {
                        onPresent(tracker) { value132 -> run { val owner134: dynamic = value132; onPresent(owner134.add) { method133 -> invokeWith(method133, owner134, arrayOf<dynamic>(date, ex, item.sets)) } } }
                    }
                }
            }
            return jsObject { obj135 ->
                obj135["items"] = result
                obj135["effective"] = kind
                obj135["requested"] = kind
                obj135["substituted"] = false
                obj135["count"] = result.length
                obj135["mixedEquipment3880"] = true
            }
        }
        choose = fun(p: dynamic, kind: dynamic, seed: dynamic, arg138: dynamic, arg139: dynamic, arg140: dynamic, arg141: dynamic, arg142: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg138) { js("({})") }
            var date: dynamic = absent
            date = definedOr(arg139) { E.dateKey() }
            var usedBases: dynamic = absent
            usedBases = definedOr(arg140) { jsConstruct(global_Set, js("[]")) }
            var tracker: dynamic = absent
            tracker = definedOr(arg141) { null }
            var weekIndex: dynamic = absent
            weekIndex = definedOr(arg142) { 0 }
            if (truth(jsAnd(global_Array.isArray(onPresent(p) { value143 -> value143.equipmentModes3880 })) { (!truth(p["__singleMode3880"])) })) {
                return chooseCombined3880(p, kind, seed, logs, date, usedBases, tracker, weekIndex)
            }
            val status: dynamic = gate(p)
            if (truth((!truth(status.ok)))) {
                return jsObject { obj144 ->
                    obj144["items"] = js("[]")
                    obj144["blocked"] = status.reason
                    obj144["requested"] = kind
                }
            }
            if (truth((kind === "rest"))) {
                return jsonValue("{\"items\":[],\"effective\":\"rest\",\"requested\":\"rest\"}")
            }
            val options: dynamic = arrayOf<dynamic>(kind)
            var list: dynamic = js("[]")
            var effective: dynamic = kind
            val weekday: dynamic = S.weekday(date)
            val dailyType: dynamic = onPresent(p) { value145 -> onPresent(value145.weekPlan) { value146 -> value146[weekday] } }
            val explicitFocus: dynamic = (if (truth(global_Object.prototype.hasOwnProperty.call(goalGroups, onPresent(p) { value147 -> value147.dynamicRequestedFocus3847 }))) p.dynamicRequestedFocus3847 else null)
            val dailyFocus: dynamic = (if (truth((kind === "strength"))) jsOr(explicitFocus) { (if (truth((dailyType === "train"))) (if (truth(dynamicValue(jsonValue("[\"lower\",\"core\"]")).includes(onPresent(p) { value148 -> onPresent(value148.weekFocus) { value149 -> value149[weekday] } }))) p.weekFocus[weekday] else "upper") else null) } else null)
            val allowedStrengthGroups: dynamic = (if (truth(jsAnd(dailyFocus) { (dailyFocus !== "general") })) jsConstruct(global_Set, arrayOf<dynamic>(goalGroups[dailyFocus])) else null)
            if (true) {
                val iterator150: dynamic = iteratorOf(options)
                while (true) {
                    val next151: dynamic = iterator150.next()
                    if (truth(next151.done)) break
                    var type: dynamic = absent
                    type = next151.value
                    list = pool(p, type)
                    if (truth(p["__onlyMode3880"])) {
                        list = list.filter((fun(e: dynamic): dynamic {
                            return matchesMode3880(e, p["__onlyMode3880"])
                        }))
                    }
                    if (truth(jsAnd((type === "strength")) { allowedStrengthGroups })) {
                        list = list.filter((fun(e: dynamic): dynamic {
                            return root.HunterGroups49.matches(e, dailyFocus)
                        }))
                    }
                    if (truth(list.length)) {
                        effective = type
                        break
                    }
                }
            }
            if (truth((!truth(list.length)))) {
                return jsObject { obj152 ->
                    obj152["items"] = js("[]")
                    obj152["blocked"] = "No se encontraron ejercicios demostrados compatibles con las restricciones declaradas."
                    obj152["requested"] = kind
                }
            }
            val tierCategory: dynamic = (if (truth((effective === "cardio"))) "cardio" else (if (truth((effective === "recovery"))) "mobility" else (if (truth(dynamicValue(jsonValue("[\"upper\",\"lower\",\"core\"]")).includes(dailyFocus))) dailyFocus else (if (truth(dynamicValue(jsonValue("[\"upper\",\"lower\",\"core\"]")).includes(p.focus))) p.focus else "upper"))))
            val levelContext: dynamic = jsOr(onPresent(root.HunterLevel3851) { value153 -> value153.context(p, logs, date, tierCategory, 5) }) { null }
            if (truth(onPresent(levelContext) { value154 -> value154.preview })) {
                var options: dynamic = pool(jsObject { obj155 ->
                    copyFields(obj155, p)
                    obj155["difficulty"] = "advanced"
                }, effective)
                if (truth(p["__onlyMode3880"])) {
                    options = options.filter((fun(e: dynamic): dynamic {
                        return matchesMode3880(e, p["__onlyMode3880"])
                    }))
                }
                if (truth(jsAnd((effective === "strength")) { allowedStrengthGroups })) {
                    options = options.filter((fun(e: dynamic): dynamic {
                        return root.HunterGroups49.matches(e, dailyFocus)
                    }))
                }
                val merged: dynamic = jsConstruct(global_Map, arrayOf<dynamic>(dynamicValue(jsArray { array157 ->
                    appendAll(array157, list)
                    appendAll(array157, options)
                }).map((fun(e: dynamic): dynamic {
                    return arrayOf<dynamic>(e.id, e)
                }))))
                list = jsArray { array158 ->
                    appendAll(array158, merged.values())
                }
            }
            val cap: dynamic = nullableOr(onPresent(root.HunterMaterials3814) { value159 -> run { val owner161: dynamic = value159; onPresent(owner161.sessionCap) { method160 -> invokeWith(method160, owner161, arrayOf<dynamic>(p, kind, status)) } } }) { global_Math.max(2, global_Math.min(7, jsOr(global_Number(p.dailyMissions)) { 3 })) }
            val baseCount: dynamic = (if (truth((effective === "recovery"))) global_Math.min(3, cap) else cap)
            val count: dynamic = nullableOr(onPresent(root.HunterIntegration3869) { value162 -> run { val owner164: dynamic = value162; onPresent(owner164.activityLimit) { method163 -> invokeWith(method163, owner164, arrayOf<dynamic>(baseCount)) } } }) { baseCount }
            val hist: dynamic = historyIds(logs, date)
            val rawFeedback: dynamic = feedback(logs, date)
            val fb: dynamic = jsOr(onPresent(root.HunterCategoryProgress51) { value165 -> value165.feedback(rawFeedback, logs, date) }) { rawFeedback }
            val selected: dynamic = js("[]")
            val used: dynamic = jsConstruct(global_Set, js("[]"))
            val counts: dynamic = js("({})")
            val patternCounts: dynamic = js("({})")
            val weeklyLoad: dynamic = jsOr(onPresent(root.HunterTraining387) { value166 -> run { val owner168: dynamic = value166; onPresent(owner168.load) { method167 -> invokeWith(method167, owner168, arrayOf<dynamic>(logs, date)) } } }) { jsonValue("{\"groups\":{},\"patterns\":{},\"ids\":{}}") }
            val selectionContext: dynamic = root.HunterGroups49.context(p, effective, jsOr(jsOr(dailyFocus) { p.focus }) { "general" }, jsObject { obj169 ->
                obj169["logs"] = logs
                obj169["date"] = date
                obj169["tracker"] = tracker
                obj169["status"] = status
                obj169["feedback"] = fb
            })
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration170 = true
                while (true) {
                    if (!firstIteration170) {
                        run {
                            val previous171: dynamic = numeric(i)
                            i = jsAdd(previous171, 1)
                        }
                    }
                    firstIteration170 = false
                    if (!truth(jsLess(i, count))) break
                    val candidates: dynamic = list.filter((fun(e: dynamic): dynamic {
                        return jsAnd((!truth(used.has(root.HunterGroups49.family(e))))) { (!truth(usedBases.has(root.HunterGroups49.family(e)))) }
                    }))
                    var choices: dynamic = (if (truth(candidates.length)) candidates else list.filter((fun(e: dynamic): dynamic {
                        return (!truth(used.has(root.HunterGroups49.family(e))))
                    })))
                    choices = nullableOr(onPresent(root.HunterIntegration3869) { value172 -> run { val owner174: dynamic = value172; onPresent(owner174.prefers) { method173 -> invokeWith(method173, owner174, arrayOf<dynamic>(choices)) } } }) { choices }
                    choices = root.HunterGroups49.selectionPool(choices, selected, count, selectionContext)
                    choices = jsOr(onPresent(root.HunterLevel3851) { value175 -> value175.levelPool(choices, selected, levelContext, count) }) { choices }
                    if (truth((!truth(choices.length)))) {
                        break
                    }
                    val next: dynamic = sortPool(choices, p, jsAdd(jsAdd(seed, "-"), i), effective, hist, used, counts, weeklyLoad, patternCounts, tracker, date, selected)[0].e
                    selected.push(next)
                    used.add(root.HunterGroups49.family(next))
                    counts[next.group] = jsAdd(jsOr(counts[next.group]) { 0 }, 1)
                    val pat: dynamic = onPresent(root.HunterTraining387) { value178 -> run { val owner180: dynamic = value178; onPresent(owner180.pattern) { method179 -> invokeWith(method179, owner180, arrayOf<dynamic>(next)) } } }
                    if (truth(pat)) {
                        patternCounts[pat] = jsAdd(jsOr(patternCounts[pat]) { 0 }, 1)
                    }
                }
            }
            val category51: dynamic = jsOr(onPresent(root.HunterCategoryProgress51) { value183 -> value183.categoryOfItems(selected, effective, dailyFocus) }) { null }
            val items: dynamic = selected.map((fun(e: dynamic): dynamic {
                val basic: dynamic = dose(e, p, effective, status, fb, date)
                val adjusted: dynamic = jsOr(onPresent(root.HunterTraining387) { value184 -> run { val owner186: dynamic = value184; onPresent(owner186.dose) { method185 -> invokeWith(method185, owner186, arrayOf<dynamic>(e, p, effective, status, fb, date, basic, weeklyLoad)) } } }) { basic }
                val classified: dynamic = jsOr(onPresent(root.HunterMetadata3812) { value187 -> run { val owner189: dynamic = value187; onPresent(owner189.dose) { method188 -> invokeWith(method188, owner189, arrayOf<dynamic>(e, p, effective, status, fb, date, adjusted)) } } }) { adjusted }
                val legacy: dynamic = jsOr(onPresent(root.HunterProgress3815) { value190 -> run { val owner192: dynamic = value190; onPresent(owner192.dose) { method191 -> invokeWith(method191, owner192, arrayOf<dynamic>(e, p, effective, status, fb, date, classified, weekIndex, logs)) } } }) { classified }
                val finalDose: dynamic = jsOr(onPresent(root.HunterUnified3827) { value193 -> run { val owner195: dynamic = value193; onPresent(owner195.dose) { method194 -> invokeWith(method194, owner195, arrayOf<dynamic>(e, p, effective, status, fb, date, legacy, weekIndex, logs, basic)) } } }) { legacy }
                val roleDose: dynamic = root.HunterGroups49.adjustDose(e, p, effective, finalDose, jsObject { obj196 ->
                    obj196["status"] = status
                    obj196["feedback"] = fb
                    obj196["logs"] = logs
                    obj196["date"] = date
                })
                return jsOr(onPresent(root.HunterCatalog46) { value197 -> value197.cap(e, p, roleDose, jsObject { obj198 ->
                    obj198["status"] = status
                    obj198["feedback"] = fb
                    obj198["logs"] = logs
                    obj198["date"] = date
                }) }) { roleDose }
            }))
            val calculated: dynamic = onPresent(root.HunterCategoryProgress51) { value199 -> value199.adjustItems(items, jsObject { obj200 ->
                obj200["profile"] = p
                obj200["logs"] = logs
                obj200["date"] = date
                obj200["category"] = category51
                obj200["status"] = status
                obj200["kind"] = effective
            }) }
            val resultItems: dynamic = dynamicValue(jsOr(onPresent(calculated) { value202 -> value202.items }) { items }).map((fun(item: dynamic): dynamic {
                return jsOr(onPresent(root.HunterLevel3851) { value203 -> value203.dose(item, E.byId(item.id), levelContext) }) { item }
            }))
            if (true) {
                val iterator204: dynamic = iteratorOf(resultItems)
                while (true) {
                    val next205: dynamic = iterator204.next()
                    if (truth(next205.done)) break
                    var item: dynamic = absent
                    item = next205.value
                    val e: dynamic = byId["get"](item.id)
                    if (truth(e)) {
                        onPresent(tracker) { value206 -> run { val owner208: dynamic = value206; onPresent(owner208.add) { method207 -> invokeWith(method207, owner208, arrayOf<dynamic>(date, e, item.sets)) } } }
                    }
                }
            }
            return jsObject { obj209 ->
                obj209["items"] = resultItems
                obj209["effective"] = effective
                obj209["requested"] = kind
                obj209["substituted"] = (effective !== kind)
                obj209["count"] = resultItems.length
                obj209["progression51"] = jsOr(onPresent(calculated) { value210 -> value210.progress }) { null }
                obj209["level3851"] = (if (truth(levelContext)) jsObject { obj211 ->
                    obj211["profileTier"] = levelContext.profileTier
                    obj211["ceiling"] = levelContext.ceiling
                    obj211["preview"] = levelContext.preview
                    obj211["reason"] = levelContext.failure.why
                    obj211["quota"] = root.HunterLevel3851.idealQuota(levelContext, count)
                } else null)
            }
        }
        planKey = fun(p: dynamic, logs: dynamic, today: dynamic): dynamic {
            val past: dynamic = recent(logs, today, 90).map((fun(arg214: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked215: dynamic = fromIterable(arg214)
                d = unpacked215[0]
                l = unpacked215[1]
                return arrayOf<dynamic>(d, l.complete, l.pain, l.fatigue, l.rpe, l.manual, l.kind, global_String(jsOr(l.note) { "" }).slice(0, 500), dynamicValue(jsOr(l.items) { js("[]") }).map((fun(x: dynamic): dynamic {
                    return arrayOf<dynamic>(x.id, dynamicValue(jsOr(x["actual"]) { js("[]") }).map((fun(a: dynamic): dynamic {
                        return (!truth((!truth(a.done))))
                    })))
                })))
            }))
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(VERSION, "|"), profileKey(p)), "|"), today), "|"), hash(asJSON(past))["toString"](36))
        }
        patch = fun(c: dynamic, p: dynamic, arg218: dynamic, arg219: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg218) { js("({})") }
            var today: dynamic = absent
            today = definedOr(arg219) { E.dateKey() }
            if (truth(jsOr(jsOr((!truth(onPresent(c) { value220 -> onPresent(value220.days) { value221 -> value221.length } }))) { (!truth(p)) }) { (!truth(S.valid(p))) })) {
                return 0
            }
            if (truth((onPresent(c.reviews) { value222 -> onPresent(value222.at((-numeric(1)))) { value223 -> value223.action } } === "pausar"))) {
                return 0
            }
            val key: dynamic = planKey(p, logs, today)
            if (truth(jsAnd((c.dynamicKey381 === key)) { c.days.every((fun(d: dynamic): dynamic {
                return jsOr(jsOr(jsOr(jsOr(jsLess(d.date, today)) { (d.scheduleChoice === "rest") }) { locked(d, logs) }) { d.dynamic381 }) { d.blocked381 }
            })) })) {
                return 0
            }
            var changes: dynamic = 0
            var groupUsed: dynamic = jsConstruct(global_Set, js("[]"))
            var weekSegment: dynamic = (-numeric(1))
            val risk: dynamic = injury(p)
            val tracker: dynamic = jsOr(onPresent(root.HunterPlan3810) { value224 -> run { val owner226: dynamic = value224; onPresent(owner226.tracker) { method225 -> invokeWith(method225, owner226, arrayOf<dynamic>(c, logs, today)) } } }) { null }
            if (true) {
                val iterator227: dynamic = iteratorOf(c.days.entries())
                while (true) {
                    val next228: dynamic = iterator227.next()
                    if (truth(next228.done)) break
                    var index: dynamic = absent
                    var d: dynamic = absent
                    val unpacked229: dynamic = fromIterable(next228.value)
                    index = unpacked229[0]
                    d = unpacked229[1]
                    val segment: dynamic = nullableOr(onPresent(root.HunterSchedule3813) { value230 -> run { val owner232: dynamic = value230; onPresent(owner232.weekSegment) { method231 -> invokeWith(method231, owner232, arrayOf<dynamic>(index)) } } }) { global_Math.floor(jsDivide(index, 7)) }
                    if (truth((segment !== weekSegment))) {
                        weekSegment = segment
                        groupUsed = jsConstruct(global_Set, js("[]"))
                    }
                    if (truth(jsOr(jsOr(jsLess(d.date, today)) { locked(d, logs) }) { onPresent(root.HunterStable3828) { value233 -> run { val owner235: dynamic = value233; onPresent(owner235.keep) { method234 -> invokeWith(method234, owner235, arrayOf<dynamic>(d, p, logs, today)) } } } })) {
                        continue
                    }
                    val selected: dynamic = jsOr(p.weekPlan[S.weekday(d.date)]) { "rest" }
                    val kind: dynamic = TYPES[selected]
                    if (truth((selected === "rest"))) {
                        if (truth(jsOr(jsOr((d.kind !== "rest")) { onPresent(d.items) { value236 -> value236.length } }) { d.dynamic381 })) {
                            d.kind = "rest"
                            d.items = js("[]")
                            d.title = "Descanso elegido"
                            d.tasks = js("[]")
                            d.scheduleChoice = "rest"
                            d.dynamic381 = true
                            removeKey(d, "blocked378")
                            removeKey(d, "blocked381")
                            run {
                                val previous249: dynamic = numeric(changes)
                                changes = jsAdd(previous249, 1)
                            }
                        }
                        continue
                    }
                    if (truth(jsAnd(jsAnd((d.dynamicKey381 === key)) { d.dynamic381 }) { onPresent(d.items) { value250 -> value250.length } })) {
                        continue
                    }
                    val res: dynamic = choose(p, kind, jsAdd(jsAdd(jsAdd(jsAdd(profileKey(p), "|"), d.date), "|"), c.index), logs, d.date, groupUsed, tracker, segment)
                    d.kind = kind
                    d.scheduleChoice = selected
                    d.items = res.items
                    d.tasks = js("[]")
                    d.dynamic381 = true
                    d.dynamicKey381 = key
                    d.progression51 = jsOr(res.progression51) { null }
                    val shownKind: dynamic = (if (truth((kind === "strength"))) jsOr(jsonValue("{\"lower\":\"Tren inferior\",\"core\":\"Tren central\"}")[onPresent(p) { value265 -> onPresent(value265.weekFocus) { value266 -> value266[S.weekday(d.date)] } }]) { "Tren superior" } else SPANISH[kind])
                    d.title = jsAdd(shownKind, (if (truth(res.substituted)) jsAdd(" · alternativa de ", SPANISH[res.effective]) else " · plan dinámico"))
                    if (truth(res.blocked)) {
                        d.title = jsAdd(SPANISH[kind], " · requiere revisión")
                        d.blocked381 = res.blocked
                        d.blocked378 = res.blocked
                        d.tasks = jsonValue("[{\"id\":\"review-dynamic381\",\"text\":\"Revisar restricciones para encontrar ejercicios compatibles\",\"done\":false}]")
                        d.planningNote = "Se conserva el día elegido y el historial; no hay selección automática segura."
                    } else {
                        removeKey(d, "blocked381")
                        removeKey(d, "blocked378")
                        d.planningNote = jsAdd(jsAdd((if (truth(res.substituted)) jsAdd(jsAdd("No había opciones verificadas del tipo elegido; se muestran ", SPANISH[res.effective]), ". ") else ""), "Generado bajo demanda desde demostraciones 2D existentes, sin rutinas combinatorias precargadas."), (if (truth(onPresent(res.progression51) { value281 -> value281.lastScore })) jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(" Progresión ", res.progression51.label), ": esfuerzo "), res.progression51.lastScore), "/10, "), jsOr(jsonValue("{\"repeticiones_o_tiempo\":\"pequeño ajuste de repeticiciones/tiempo\",\"una_serie\":\"una serie adicional controlada\",\"reduccion_suave\":\"reducción moderada\",\"mantener\":\"mantener dosis\"}")[res.progression51.action]) { "mantener dosis" }), ".") else ""))
                        if (truth(risk)) {
                            d.adapted378 = true
                            d.adaptation379 = "37.9"
                            d.originalCategory378 = SPANISH[kind]
                        }
                        if (true) {
                            val iterator288: dynamic = iteratorOf(res.items)
                            while (true) {
                                val next289: dynamic = iterator288.next()
                                if (truth(next289.done)) break
                                var item: dynamic = absent
                                item = next289.value
                                val ex: dynamic = byId["get"](item.id)
                                if (truth(ex)) {
                                    groupUsed.add(root.HunterGroups49.family(ex))
                                }
                            }
                        }
                    }
                    removeKey(d, "autoTemplateId")
                    run {
                        val previous290: dynamic = numeric(changes)
                        changes = jsAdd(previous290, 1)
                    }
                }
            }
            c.dynamicKey381 = key
            c.dynamicVersion381 = VERSION
            if (truth(changes)) {
                c.decision = "Generación dinámica de sesiones: calendario, objetivo, historial y restricciones. Se conservan sesiones registradas."
            }
            return changes
        }
        templates = fun(p: dynamic, arg310: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg310) { js("({})") }
            if (truth(jsOr((!truth(p))) { (!truth(gate(p).ok)) })) {
                return js("[]")
            }
            val today: dynamic = E.dateKey()
            val pastLogs: dynamic = global_Object.fromEntries(global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg311: dynamic): dynamic {
                var d: dynamic = absent
                val unpacked312: dynamic = fromIterable(arg311)
                d = unpacked312[0]
                return jsLess(d, today)
            })))
            val labels: dynamic = jsonValue("[[\"strength\",\"Fuerza\"],[\"cardio\",\"Cardio\"],[\"recovery\",\"Movilidad\"]]")
            val result: dynamic = js("[]")
            if (true) {
                val iterator313: dynamic = iteratorOf(labels)
                while (true) {
                    val next314: dynamic = iterator313.next()
                    if (truth(next314.done)) break
                    var kind: dynamic = absent
                    var name: dynamic = absent
                    val unpacked315: dynamic = fromIterable(next314.value)
                    kind = unpacked315[0]
                    name = unpacked315[1]
                    if (true) {
                        var n: dynamic = absent
                        n = 0
                        var firstIteration316 = true
                        while (true) {
                            if (!firstIteration316) {
                                run {
                                    val previous317: dynamic = numeric(n)
                                    n = jsAdd(previous317, 1)
                                }
                            }
                            firstIteration316 = false
                            if (!truth(jsLess(n, 3))) break
                            val q: dynamic = choose(p, kind, jsAdd(jsAdd(jsAdd(jsAdd(profileKey(p), "|"), today), "|template"), n), pastLogs, E["plus"](today, n))
                            if (truth((!truth(q.items.length)))) {
                                continue
                            }
                            val requirements: dynamic = jsArray { array318 ->
                                appendAll(array318, jsConstruct(global_Set, arrayOf<dynamic>(q.items.flatMap((fun(x: dynamic): dynamic {
                                    return jsOr(onPresent(byId["get"](x.id)) { value319 -> value319.requirements }) { js("[]") }
                                })))))
                            }
                            result.push(jsObject { obj320 ->
                                obj320["id"] = jsAdd(jsAdd(jsAdd("dynamic381-", kind), "-"), n)
                                obj320["name"] = jsAdd(jsAdd(name, " dinámica · "), jsAdd(n, 1))
                                obj320["category"] = CATEGORY[q.effective]
                                obj320["description"] = "Generada para este perfil a partir del catálogo 2D; se reevalúan restricciones antes de asignar."
                                obj320["items"] = q.items
                                obj320["requirements"] = requirements
                                obj320["level"] = 0
                                obj320["goalGroups"] = jsonValue("[\"fitness\",\"habit\",\"movement\",\"build\",\"athletic\"]")
                                obj320["extension"] = "standard"
                                obj320["family"] = "dynamic"
                                obj320["impact"] = (if (truth(q.items.some((fun(x: dynamic): dynamic {
                                    return root.HunterGroups49.impact(byId["get"](x.id))
                                })))) "Con saltos" else "Sin saltos")
                                obj320["minutes"] = global_Math.max(5, jsMultiply(q.items.length, 4))
                                obj320["safe381"] = true
                            })
                        }
                    }
                }
            }
            return result
        }
        E = root.Engine
        S = root.Schedule
        A = root.Adapt
        W = root.Weekly
        if (truth(jsOr(jsOr(jsOr((!truth(E))) { (!truth(S)) }) { (!truth(A)) }) { (!truth(W)) })) {
            global_console.error("Dynamic381: dependencias no disponibles")
            return absent
        }
        VERSION = "38.51-level-mix-and-feedback"
        val VERIFIED_IDS: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"new46-pullup\",\"new46-chinup\",\"new46-scapularhang\",\"new46-invertedrow\",\"new46-paralleldip\",\"new46-wallhspu\",\"new46-dbpushpress\",\"new46-bander\",\"new46-bandir\",\"new46-waitercarry\",\"new46-bandpullapart\",\"new46-banddiagonal\",\"new46-bandrow\",\"new46-kneelbandlat\",\"new46-serratusband\",\"new46-faceer\",\"new46-trxcurl\",\"new46-trxtriceps\",\"new46-trxfly\",\"new46-trxy\",\"new46-trxpress\",\"new46-trxhighrow\",\"new46-rotationalrow\",\"new46-ringsrotation\",\"new46-ringturnout\",\"new46-cubanrotation\",\"new46-scapdip\",\"new46-bottomclean\",\"new46-bottompress\",\"new46-halo\",\"new46-pushjerk\",\"new46-dbsnatch\",\"new46-hangclean\",\"new46-meadowsrow\",\"new46-ringlat\",\"new46-cableer90\",\"new46-cubanpress\",\"new46-crosscabletri\",\"new46-halfkneellandmine\",\"new46-commandopull\",\"new46-zpress\",\"new46-forearmlever\",\"new46-wristdeviation\",\"new46-wristcurl\",\"new46-wristexte\",\"new46-bandd2\",\"new46-bandd1\",\"new46-supineshoulderir\",\"new46-nordic\",\"new46-reversenordic\",\"new46-copenhagen\",\"new46-airplanefree\",\"new46-stepdown\",\"new46-hiphike\",\"new46-kbswing\",\"new46-sledpush\",\"new46-sledback\",\"new46-ankleinvert\",\"new46-ankleevert\",\"new46-shortfoot\",\"new46-toeyoga\",\"new46-toetowel\",\"new46-monsterwalk\",\"new46-lateralbandwalk\",\"new46-reverseclam\",\"new46-pronehipir\",\"new46-tke\",\"new46-spanishsquat\",\"new46-sissysquat\",\"new46-zercher\",\"new46-overheadsquat\",\"new46-jefferson\",\"new46-goodmorning\",\"new46-seatedhinge\",\"new46-trxsplit\",\"new46-trxsquat\",\"new46-slidelateral\",\"new46-slidereverse\",\"new46-ballham\",\"new46-ballhip\",\"new46-bandkick\",\"new46-walltibia\",\"new46-soleus\",\"new46-ballsqueeze\",\"new46-copenhagendyn\",\"new46-firehydrant\",\"new46-pivotlunge\",\"new46-heelwalk\",\"new46-toewalk\",\"new46-bandplantar\",\"new46-sideadduct\",\"new46-supinebandmarch\",\"new46-turkish\",\"new46-halfturkish\",\"new46-abwheel\",\"new46-lsit\",\"new46-hangingknee\",\"new46-hanginglegs\",\"new46-pallofwalk\",\"new46-suitcasecarry\",\"new46-rackcarry\",\"new46-stirpot\",\"new46-windmill\",\"new46-figureeight\",\"new46-landminetwist\",\"new46-dragonflag\",\"new46-tucklever\",\"new46-hangingwiper\",\"new46-abwheelstand\",\"new46-trxpike\",\"new46-trxtuck\",\"new46-balltuck\",\"new46-ballpike\",\"new46-banddeadbug\",\"new46-bandbird\",\"new46-sidecrow\",\"new46-sidepallof\",\"new46-seatedpallof\",\"new46-kneelchop\",\"new46-kneellift\",\"new46-doublerack\",\"new46-sliderfly\",\"new46-sitthrough\",\"new46-romanknee\",\"new46-burpee\",\"new46-boxjump\",\"new46-shuttle\",\"new46-backrun\",\"new46-ladderinout\",\"new46-singlepogo\",\"new46-mbrotation\",\"new46-mbslam\",\"new46-ropealternating\",\"new46-sprintbrake\",\"new46-doublepogo\",\"new46-forwardhop\",\"new46-crosshop\",\"new46-turnjump\",\"new46-splitjump\",\"new46-droplanding\",\"new46-depthjump\",\"new46-lateralhurdle\",\"new46-mbchest\",\"new46-mbscoop\",\"new46-mboverback\",\"new46-mbcatch\",\"new46-ropedouble\",\"new46-ropecircles\",\"new46-ropeslam\",\"new46-jumpropealt\",\"new46-jumpropeside\",\"new46-ickyshuffle\",\"new46-laddercarioca\",\"new46-tdrill\",\"new46-hexagon\",\"new46-askip\",\"new46-resistedsprint\",\"new46-sledlateral\",\"new46-sledrope\",\"new46-mountainfast\",\"new46-shouldercar\",\"new46-hipcarquad\",\"new46-hip9090lift\",\"new46-sleeper\",\"new46-thoracicroller\",\"new46-frogrock\",\"new46-wristrock\",\"new46-pelvictilt\",\"new46-deepshift\",\"new46-mediannerve\",\"new46-ulnarnerve\",\"new46-sciaticnerve\",\"new46-peronealnerve\",\"new46-quadthoracic\",\"new46-chairthoracic\",\"new46-crossshoulder\",\"new46-stickextension\",\"new46-tricepsstretch\",\"new46-benchprayer\",\"new46-doorpec\",\"new46-foampec\",\"new46-adductorrock\",\"new46-hipcapsule\",\"new46-pronehipcontrol\",\"new46-pronequad\",\"new46-couchstretch\",\"new46-seatedhipir\",\"new46-bentcalfstretch\",\"new46-bigtoestretch\",\"new46-tendonglides\",\"new46-thumbopposition\",\"new46-chinretract\",\"new46-lungerotation\",\"new46-pelvicclock\",\"new46-scapcar\",\"new46-butterfly\",\"hs379-scapular\",\"hs379-palms\",\"hs379-isobiceps\",\"hs379-shoulderset\",\"chair\",\"squat\",\"goblet\",\"wall\",\"incline\",\"pushup\",\"press\",\"neutral\",\"bridge\",\"hinge\",\"rdl\",\"row\",\"bentrow\",\"prone\",\"reverse\",\"curl\",\"hammer\",\"calf\",\"dbcalf\",\"deadbug\",\"bird\",\"plank\",\"heel\",\"sideleg\",\"march\",\"shoulder\",\"ankle\",\"cat\",\"wallslide\",\"lateral\",\"benchpress\",\"inclinebench\",\"kneepush\",\"closepush\",\"squeezepress\",\"sumo\",\"dbsumo\",\"reverselunge\",\"dblunge\",\"split\",\"lateralunge\",\"stepup\",\"wallsit\",\"singlecalf\",\"seatedcalf\",\"singlebridge\",\"dbbridge\",\"kickback\",\"clam\",\"singlehinge\",\"sumodead\",\"pullover\",\"reversefly\",\"towelrow\",\"scapular\",\"overheadpress\",\"frontlift\",\"shouldertap\",\"seatedcurl\",\"concentration\",\"tricep\",\"lyingtricep\",\"reversecurl\",\"sideplank\",\"kneelingplank\",\"reversecrunch\",\"curlup\",\"standingcore\",\"suitcase\",\"fastmarch\",\"sidestep\",\"stepjack\",\"box\",\"heelback\",\"kneeliftcardio\",\"walk\",\"skater\",\"jog\",\"jumpjack\",\"rope\",\"mountain\",\"squatstep\",\"shadowcombo\",\"hipcircle\",\"chestopen\",\"thoracic\",\"hipflex\",\"hamstretch\",\"calfstretch\",\"wrist\",\"neck\",\"breathing\",\"seatedpunch\",\"seatedmarch\",\"seatedstep\",\"heeltoe\",\"legextend\",\"seatedhip\",\"toelift\",\"seatedheel\",\"seatedside\",\"seatedturn\",\"seatedreach\",\"elbowmob\",\"forearmmob\",\"wristmob\",\"fingers\",\"wallcalf\",\"standingabduct\",\"standingextend\",\"standingcurl\",\"tandem\",\"balance\",\"linewalk\",\"doublefront\",\"suitdead\",\"dbsplit\",\"seatedpress\",\"seatedlateral\",\"seatedfly\",\"curlhold\",\"farmerhold\",\"wallplank\",\"supinearms\",\"chair_slow\",\"chair_pause\",\"squat_slow\",\"squat_pause\",\"goblet_slow\",\"goblet_pause\",\"wall_slow\",\"wall_pause\",\"incline_slow\",\"incline_pause\",\"pushup_slow\",\"pushup_pause\",\"press_slow\",\"press_pause\",\"neutral_slow\",\"neutral_pause\",\"bridge_slow\",\"bridge_pause\",\"hinge_slow\",\"hinge_pause\",\"rdl_slow\",\"rdl_pause\",\"row_slow\",\"row_pause\",\"bentrow_slow\",\"bentrow_pause\",\"prone_slow\",\"prone_pause\",\"reverse_slow\",\"reverse_pause\",\"curl_slow\",\"curl_pause\",\"hammer_slow\",\"hammer_pause\",\"calf_slow\",\"calf_pause\",\"dbcalf_slow\",\"dbcalf_pause\",\"deadbug_slow\",\"deadbug_pause\",\"bird_slow\",\"bird_pause\",\"plank_short\",\"plank_easy\",\"heel_slow\",\"heel_pause\",\"sideleg_slow\",\"sideleg_pause\",\"march_short\",\"march_easy\",\"shoulder_short\",\"shoulder_easy\",\"ankle_short\",\"ankle_easy\",\"cat_slow\",\"cat_pause\",\"wallslide_slow\",\"wallslide_pause\",\"lateral_slow\",\"lateral_pause\",\"benchpress_slow\",\"benchpress_pause\",\"inclinebench_slow\",\"inclinebench_pause\",\"kneepush_slow\",\"kneepush_pause\",\"closepush_slow\",\"closepush_pause\",\"squeezepress_slow\",\"squeezepress_pause\",\"sumo_slow\",\"sumo_pause\",\"dbsumo_slow\",\"dbsumo_pause\",\"reverselunge_slow\",\"reverselunge_pause\",\"dblunge_slow\",\"dblunge_pause\",\"split_slow\",\"split_pause\",\"lateralunge_slow\",\"lateralunge_pause\",\"stepup_slow\",\"stepup_pause\",\"wallsit_short\",\"wallsit_easy\",\"singlecalf_slow\",\"singlecalf_pause\",\"seatedcalf_slow\",\"seatedcalf_pause\",\"singlebridge_slow\",\"singlebridge_pause\",\"dbbridge_slow\",\"dbbridge_pause\",\"kickback_slow\",\"kickback_pause\",\"clam_slow\",\"clam_pause\",\"singlehinge_slow\",\"singlehinge_pause\",\"sumodead_slow\",\"sumodead_pause\",\"pullover_slow\",\"pullover_pause\",\"reversefly_slow\",\"reversefly_pause\",\"towelrow_short\",\"towelrow_easy\",\"scapular_slow\",\"scapular_pause\",\"overheadpress_slow\",\"overheadpress_pause\",\"frontlift_slow\",\"frontlift_pause\",\"shouldertap_slow\",\"shouldertap_pause\",\"seatedcurl_slow\",\"seatedcurl_pause\",\"concentration_slow\",\"concentration_pause\",\"tricep_slow\",\"tricep_pause\",\"lyingtricep_slow\",\"lyingtricep_pause\",\"reversecurl_slow\",\"reversecurl_pause\",\"sideplank_short\",\"sideplank_easy\",\"kneelingplank_short\",\"kneelingplank_easy\",\"reversecrunch_slow\",\"reversecrunch_pause\",\"curlup_slow\",\"curlup_pause\",\"standingcore_slow\",\"standingcore_pause\",\"suitcase_short\",\"suitcase_easy\",\"fastmarch_short\",\"fastmarch_easy\",\"sidestep_short\",\"sidestep_easy\",\"stepjack_short\",\"stepjack_easy\",\"box_short\",\"box_easy\",\"heelback_short\",\"heelback_easy\",\"kneeliftcardio_short\",\"kneeliftcardio_easy\",\"walk_short\",\"walk_easy\",\"skater_short\",\"skater_easy\",\"jog_short\",\"jog_easy\",\"jumpjack_short\",\"jumpjack_easy\",\"rope_short\",\"rope_easy\",\"mountain_short\",\"mountain_easy\",\"squatstep_short\",\"squatstep_easy\",\"shadowcombo_short\",\"shadowcombo_easy\",\"hipcircle_short\",\"hipcircle_easy\",\"chestopen_short\",\"chestopen_easy\",\"thoracic_slow\",\"thoracic_pause\",\"hipflex_short\",\"hipflex_easy\",\"hamstretch_short\",\"hamstretch_easy\",\"calfstretch_short\",\"calfstretch_easy\",\"wrist_short\",\"wrist_easy\",\"neck_short\",\"neck_easy\",\"breathing_short\",\"breathing_easy\",\"seatedpunch_short\",\"seatedpunch_easy\",\"seatedmarch_short\",\"seatedmarch_easy\",\"seatedstep_short\",\"seatedstep_easy\",\"heeltoe_slow\",\"heeltoe_pause\",\"legextend_slow\",\"legextend_pause\",\"seatedhip_slow\",\"seatedhip_pause\",\"toelift_slow\",\"toelift_pause\",\"seatedheel_slow\",\"seatedheel_pause\",\"seatedside_slow\",\"seatedside_pause\",\"seatedturn_slow\",\"seatedturn_pause\",\"seatedreach_slow\",\"seatedreach_pause\",\"elbowmob_slow\",\"elbowmob_pause\",\"forearmmob_slow\",\"forearmmob_pause\",\"wristmob_slow\",\"wristmob_pause\",\"fingers_slow\",\"fingers_pause\",\"wallcalf_slow\",\"wallcalf_pause\",\"standingabduct_slow\",\"standingabduct_pause\",\"standingextend_slow\",\"standingextend_pause\",\"standingcurl_slow\",\"standingcurl_pause\",\"tandem_short\",\"tandem_easy\",\"balance_short\",\"balance_easy\",\"linewalk_short\",\"linewalk_easy\",\"doublefront_slow\",\"doublefront_pause\",\"suitdead_slow\",\"suitdead_pause\",\"dbsplit_slow\",\"dbsplit_pause\",\"seatedpress_slow\",\"seatedpress_pause\",\"seatedlateral_slow\",\"seatedlateral_pause\",\"seatedfly_slow\",\"seatedfly_pause\",\"curlhold_short\",\"curlhold_easy\",\"farmerhold_short\",\"farmerhold_easy\",\"wallplank_short\",\"wallplank_easy\",\"supinearms_slow\",\"supinearms_pause\",\"bearcrawl\",\"childpose\",\"contralateral\",\"latstretch\",\"bulgarian\",\"diagonalraise\",\"hipthrust\",\"farmerwalk\",\"inclinefly\",\"dbsidelunge\",\"vstep\",\"heeldig\",\"doubletouch\",\"frontbackstep\",\"boxupper\",\"stepreach\",\"sidebendstand\",\"bearhold\",\"altcurl\",\"althammer\",\"singlelateral\",\"singlefront\",\"singlepress\",\"altfloorpress\",\"singleoverhead\",\"altseatedpress\",\"altseatedcurl\",\"singletricep\",\"squathold\",\"goblethold\",\"bridgehold\",\"calfhold\",\"rowhold\",\"splitpause\",\"slowmarch\",\"sidestepinterval\",\"boxinterval\",\"stepjackinterval\",\"seatedmarchinterval\",\"heelcontrolled\",\"birdcontrolled\",\"abductcontrolled\",\"pau-altpress\",\"pau-overtriceps\",\"pau-arnold\",\"pau-lateralcurl\",\"pau-curlcombo\",\"pau-raisecombo\",\"pau-standingfly\",\"pau-presscombo\",\"pau-kickback\",\"pau-plankup\",\"pau-pushchild\",\"pau-armworld\",\"pau-armcircle-f\",\"pau-armcircle-b\",\"pau-armupdown\",\"pau-armpulses\",\"pau-armfly\",\"pau-armsback\",\"pau-armraise\",\"pau-armcurl\",\"lowhop\",\"squatjump\",\"skaterhop\",\"linehop\",\"fastfeet\",\"shuffle\",\"highknees\",\"lungeknee\",\"stepcross\",\"skaterreach\",\"dbmarch\",\"ankledrive\",\"front-seated-reference\",\"squat-control18\",\"goblet-control18\",\"rdl-control18\",\"press-control18\",\"neutral-control18\",\"benchpress-control18\",\"overheadpress-control18\",\"incline-control18\",\"kneepush-control18\",\"curl-control18\",\"hammer-control18\",\"row-control18\",\"bentrow-control18\",\"bridge-control18\",\"calf-control18\",\"split-control18\",\"standingabduct-control18\",\"legextend-control18\",\"bird-control18\",\"deadbug-control18\",\"pushup-control19\",\"decline19\",\"pike19\",\"archer19\",\"squat-15-19\",\"goblet-15-19\",\"split-15-19\",\"row-15-19\",\"overhead-15-19\",\"single-squat19\",\"singlehinge-free19\",\"single-rdl19\",\"full-sideplank19\",\"sideplank-lift19\",\"plankreach19\",\"singlebridge-pause19\",\"singlecalf-pause19\",\"lateral-pause19\",\"curl-pause19\",\"w-t19\",\"y-t-w19\",\"shuffle-touch19\",\"skater-stick19\",\"home-diamond23\",\"home-diamond23-tempo23\",\"home-diamond23-pause23\",\"home-staggered23\",\"home-staggered23-tempo23\",\"home-staggered23-pause23\",\"home-spiderman23\",\"home-spiderman23-tempo23\",\"home-spiderman23-pause23\",\"home-handrelease23\",\"home-handrelease23-tempo23\",\"home-handrelease23-pause23\",\"home-hindu23\",\"home-hindu23-tempo23\",\"home-hindu23-pause23\",\"home-push-tap23\",\"home-push-tap23-tempo23\",\"home-push-tap23-pause23\",\"home-push-power23\",\"home-push-power23-tempo23\",\"home-push-power23-pause23\",\"home-pike-bench23\",\"home-pike-bench23-tempo23\",\"home-pike-bench23-pause23\",\"home-push-one23\",\"home-push-one23-tempo23\",\"home-push-one23-pause23\",\"home-cossack23\",\"home-cossack23-tempo23\",\"home-cossack23-pause23\",\"home-skater-squat23\",\"home-skater-squat23-tempo23\",\"home-skater-squat23-pause23\",\"home-shrimp23\",\"home-shrimp23-tempo23\",\"home-shrimp23-pause23\",\"home-pistol-box23\",\"home-pistol-box23-tempo23\",\"home-pistol-box23-pause23\",\"home-pistol-db23\",\"home-pistol-db23-tempo23\",\"home-pistol-db23-pause23\",\"home-step-lateral23\",\"home-step-lateral23-tempo23\",\"home-step-lateral23-pause23\",\"home-step-cross23\",\"home-step-cross23-tempo23\",\"home-step-cross23-pause23\",\"home-curtsy23\",\"home-curtsy23-tempo23\",\"home-curtsy23-pause23\",\"home-walking-lunge23\",\"home-walking-lunge23-tempo23\",\"home-walking-lunge23-pause23\",\"home-squat-lunge23\",\"home-squat-lunge23-tempo23\",\"home-squat-lunge23-pause23\",\"home-lunge-front23\",\"home-lunge-front23-tempo23\",\"home-lunge-front23-pause23\",\"home-ham-slide23\",\"home-ham-slide23-tempo23\",\"home-ham-slide23-pause23\",\"home-ham-slide-one23\",\"home-ham-slide-one23-tempo23\",\"home-ham-slide-one23-pause23\",\"home-bridge-walk23\",\"home-bridge-walk23-tempo23\",\"home-bridge-walk23-pause23\",\"home-bridge-long23\",\"home-bridge-long23-tempo23\",\"home-bridge-long23-pause23\",\"home-frog23\",\"home-frog23-tempo23\",\"home-frog23-pause23\",\"home-airplane23\",\"home-airplane23-tempo23\",\"home-airplane23-pause23\",\"home-reverse-plank-lift23\",\"home-reverse-plank-lift23-tempo23\",\"home-reverse-plank-lift23-pause23\",\"home-reverse-knee23\",\"home-reverse-knee23-tempo23\",\"home-reverse-knee23-pause23\",\"home-hollow-rock23\",\"home-hollow-rock23-tempo23\",\"home-hollow-rock23-pause23\",\"home-hollow-tuck23\",\"home-hollow-tuck23-tempo23\",\"home-hollow-tuck23-pause23\",\"home-vup23\",\"home-vup23-tempo23\",\"home-vup23-pause23\",\"home-legraise23\",\"home-legraise23-tempo23\",\"home-legraise23-pause23\",\"home-flutter23\",\"home-flutter23-tempo23\",\"home-flutter23-pause23\",\"home-scissors23\",\"home-scissors23-tempo23\",\"home-scissors23-pause23\",\"home-knee-tuck23\",\"home-knee-tuck23-tempo23\",\"home-knee-tuck23-pause23\",\"home-side-thread23\",\"home-side-thread23-tempo23\",\"home-side-thread23-pause23\",\"home-side-knee23\",\"home-side-knee23-tempo23\",\"home-side-knee23-pause23\",\"home-body-saw23\",\"home-body-saw23-tempo23\",\"home-body-saw23-pause23\",\"home-plank-toe23\",\"home-plank-toe23-tempo23\",\"home-plank-toe23-pause23\",\"home-bear-tap23\",\"home-bear-tap23-tempo23\",\"home-bear-tap23-pause23\",\"home-bear-return23\",\"home-bear-return23-tempo23\",\"home-bear-return23-pause23\",\"home-crab23\",\"home-crab23-tempo23\",\"home-crab23-pause23\",\"home-thruster-db23\",\"home-thruster-db23-tempo23\",\"home-thruster-db23-pause23\",\"home-clean-db23\",\"home-clean-db23-tempo23\",\"home-clean-db23-pause23\",\"home-rack-lunge23\",\"home-rack-lunge23-tempo23\",\"home-rack-lunge23-pause23\",\"home-renegade23\",\"home-renegade23-tempo23\",\"home-renegade23-pause23\",\"home-renegade-knee23\",\"home-renegade-knee23-tempo23\",\"home-renegade-knee23-pause23\",\"home-zottman23\",\"home-zottman23-tempo23\",\"home-zottman23-pause23\",\"home-hammer-cross23\",\"home-hammer-cross23-tempo23\",\"home-hammer-cross23-pause23\",\"home-floor-fly23\",\"home-floor-fly23-tempo23\",\"home-floor-fly23-pause23\",\"gym-bench-bb23\",\"gym-bench-bb23-tempo23\",\"gym-bench-bb23-pause23\",\"gym-close-bb23\",\"gym-close-bb23-tempo23\",\"gym-close-bb23-pause23\",\"gym-incline-bb23\",\"gym-incline-bb23-tempo23\",\"gym-incline-bb23-pause23\",\"gym-decline-bb23\",\"gym-decline-bb23-tempo23\",\"gym-decline-bb23-pause23\",\"gym-press-seated-bb23\",\"gym-press-seated-bb23-tempo23\",\"gym-press-seated-bb23-pause23\",\"gym-press-bb23\",\"gym-press-bb23-tempo23\",\"gym-press-bb23-pause23\",\"gym-front-bb23\",\"gym-front-bb23-tempo23\",\"gym-front-bb23-pause23\",\"gym-back-bb23\",\"gym-back-bb23-tempo23\",\"gym-back-bb23-pause23\",\"gym-box-bb23\",\"gym-box-bb23-tempo23\",\"gym-box-bb23-pause23\",\"gym-sumo-bb23\",\"gym-sumo-bb23-tempo23\",\"gym-sumo-bb23-pause23\",\"gym-dead-bb23\",\"gym-dead-bb23-tempo23\",\"gym-dead-bb23-pause23\",\"gym-rdl-bb23\",\"gym-rdl-bb23-tempo23\",\"gym-rdl-bb23-pause23\",\"gym-thrust-bb23\",\"gym-thrust-bb23-tempo23\",\"gym-thrust-bb23-pause23\",\"gym-row-bb23\",\"gym-row-bb23-tempo23\",\"gym-row-bb23-pause23\",\"gym-row-under-bb23\",\"gym-row-under-bb23-tempo23\",\"gym-row-under-bb23-pause23\",\"gym-pendlay23\",\"gym-pendlay23-tempo23\",\"gym-pendlay23-pause23\",\"gym-landmine-press23\",\"gym-landmine-press23-tempo23\",\"gym-landmine-press23-pause23\",\"gym-landmine-row23\",\"gym-landmine-row23-tempo23\",\"gym-landmine-row23-pause23\",\"gym-curl-bb23\",\"gym-curl-bb23-tempo23\",\"gym-curl-bb23-pause23\",\"gym-preacher-bb23\",\"gym-preacher-bb23-tempo23\",\"gym-preacher-bb23-pause23\",\"gym-lat-wide23\",\"gym-lat-wide23-tempo23\",\"gym-lat-wide23-pause23\",\"gym-lat-neutral23\",\"gym-lat-neutral23-tempo23\",\"gym-lat-neutral23-pause23\",\"gym-lat-under23\",\"gym-lat-under23-tempo23\",\"gym-lat-under23-pause23\",\"gym-straight-pull23\",\"gym-straight-pull23-tempo23\",\"gym-straight-pull23-pause23\",\"gym-cable-row23\",\"gym-cable-row23-tempo23\",\"gym-cable-row23-pause23\",\"gym-cable-high-row23\",\"gym-cable-high-row23-tempo23\",\"gym-cable-high-row23-pause23\",\"gym-cable-one-row23\",\"gym-cable-one-row23-tempo23\",\"gym-cable-one-row23-pause23\",\"gym-face-pull23\",\"gym-face-pull23-tempo23\",\"gym-face-pull23-pause23\",\"gym-cable-fly23\",\"gym-cable-fly23-tempo23\",\"gym-cable-fly23-pause23\",\"gym-cable-low-fly23\",\"gym-cable-low-fly23-tempo23\",\"gym-cable-low-fly23-pause23\",\"gym-cable-high-fly23\",\"gym-cable-high-fly23-tempo23\",\"gym-cable-high-fly23-pause23\",\"gym-rope-tricep23\",\"gym-rope-tricep23-tempo23\",\"gym-rope-tricep23-pause23\",\"gym-cable-one-tricep23\",\"gym-cable-one-tricep23-tempo23\",\"gym-cable-one-tricep23-pause23\",\"gym-cable-overhead23\",\"gym-cable-overhead23-tempo23\",\"gym-cable-overhead23-pause23\",\"gym-cable-curl23\",\"gym-cable-curl23-tempo23\",\"gym-cable-curl23-pause23\",\"gym-cable-lateral23\",\"gym-cable-lateral23-tempo23\",\"gym-cable-lateral23-pause23\",\"gym-wood-chop23\",\"gym-wood-chop23-tempo23\",\"gym-wood-chop23-pause23\",\"gym-pallof23\",\"gym-pallof23-tempo23\",\"gym-pallof23-pause23\",\"gym-leg-press23\",\"gym-leg-press23-tempo23\",\"gym-leg-press23-pause23\",\"gym-hack23\",\"gym-hack23-tempo23\",\"gym-hack23-pause23\",\"gym-smith-squat23\",\"gym-smith-squat23-tempo23\",\"gym-smith-squat23-pause23\",\"gym-smith-calf23\",\"gym-smith-calf23-tempo23\",\"gym-smith-calf23-pause23\",\"gym-chest-machine23\",\"gym-chest-machine23-tempo23\",\"gym-chest-machine23-pause23\",\"gym-pec-deck23\",\"gym-pec-deck23-tempo23\",\"gym-pec-deck23-pause23\",\"gym-leg-extension23\",\"gym-leg-extension23-tempo23\",\"gym-leg-extension23-pause23\",\"gym-ham-seated23\",\"gym-ham-seated23-tempo23\",\"gym-ham-seated23-pause23\",\"gym-ham-lying23\",\"gym-ham-lying23-tempo23\",\"gym-ham-lying23-pause23\",\"gym-hip-machine23\",\"gym-hip-machine23-tempo23\",\"gym-hip-machine23-pause23\",\"gym-treadmill23\",\"gym-treadmill23-tempo23\",\"gym-treadmill23-pause23\",\"gym-row-erg23\",\"gym-row-erg23-tempo23\",\"gym-row-erg23-pause23\",\"gym-press4530\",\"gym-press4530-ecc30\",\"gym-press4530-hold30\",\"gym-presssingle30\",\"gym-presssingle30-ecc30\",\"gym-presssingle30-hold30\",\"gym-pendulum30\",\"gym-pendulum30-ecc30\",\"gym-pendulum30-hold30\",\"gym-belt30\",\"gym-belt30-ecc30\",\"gym-belt30-hold30\",\"gym-vsquat30\",\"gym-vsquat30-ecc30\",\"gym-vsquat30-hold30\",\"gym-smithsplit30\",\"gym-smithsplit30-ecc30\",\"gym-smithsplit30-hold30\",\"gym-smiththrust30\",\"gym-smiththrust30-ecc30\",\"gym-smiththrust30-hold30\",\"gym-smithrdl30\",\"gym-smithrdl30-ecc30\",\"gym-smithrdl30-hold30\",\"gym-hamstanding30\",\"gym-hamstanding30-ecc30\",\"gym-hamstanding30-hold30\",\"gym-tibialis30\",\"gym-tibialis30-ecc30\",\"gym-tibialis30-hold30\",\"gym-calfseat30\",\"gym-calfseat30-ecc30\",\"gym-calfseat30-hold30\",\"gym-calfdonkey30\",\"gym-calfdonkey30-ecc30\",\"gym-calfdonkey30-hold30\",\"gym-adduct30\",\"gym-adduct30-ecc30\",\"gym-adduct30-hold30\",\"gym-kickmachine30\",\"gym-kickmachine30-ecc30\",\"gym-kickmachine30-hold30\",\"gym-thrustmachine30\",\"gym-thrustmachine30-ecc30\",\"gym-thrustmachine30-hold30\",\"gym-abmachine30\",\"gym-abmachine30-ecc30\",\"gym-abmachine30-hold30\",\"gym-backmachine30\",\"gym-backmachine30-ecc30\",\"gym-backmachine30-hold30\",\"gym-rotary30\",\"gym-rotary30-ecc30\",\"gym-rotary30-hold30\",\"gym-pullassist30\",\"gym-pullassist30-ecc30\",\"gym-pullassist30-hold30\",\"gym-dipassist30\",\"gym-dipassist30-ecc30\",\"gym-dipassist30-hold30\",\"gym-highrow30\",\"gym-highrow30-ecc30\",\"gym-highrow30-hold30\",\"gym-supportrow30\",\"gym-supportrow30-ecc30\",\"gym-supportrow30-hold30\",\"gym-pressone30\",\"gym-pressone30-ecc30\",\"gym-pressone30-hold30\",\"gym-pressincline30\",\"gym-pressincline30-ecc30\",\"gym-pressincline30-hold30\",\"gym-shoulderm30\",\"gym-shoulderm30-ecc30\",\"gym-shoulderm30-hold30\",\"gym-lateralm30\",\"gym-lateralm30-ecc30\",\"gym-lateralm30-hold30\",\"gym-reversedeck30\",\"gym-reversedeck30-ecc30\",\"gym-reversedeck30-hold30\",\"gym-preacherm30\",\"gym-preacherm30-ecc30\",\"gym-preacherm30-hold30\",\"gym-tricepsm30\",\"gym-tricepsm30-ecc30\",\"gym-tricepsm30-hold30\",\"gym-pulloverm30\",\"gym-pulloverm30-ecc30\",\"gym-pulloverm30-hold30\",\"gym-hipflexc30\",\"gym-hipflexc30-ecc30\",\"gym-hipflexc30-hold30\",\"gym-abductc30\",\"gym-abductc30-ecc30\",\"gym-abductc30-hold30\",\"gym-adductc30\",\"gym-adductc30-ecc30\",\"gym-adductc30-hold30\",\"gym-kickc30\",\"gym-kickc30-ecc30\",\"gym-kickc30-hold30\",\"gym-crunchc30\",\"gym-crunchc30-ecc30\",\"gym-crunchc30-hold30\",\"gym-pullthrough30\",\"gym-pullthrough30-ecc30\",\"gym-pullthrough30-hold30\",\"gym-stepmill30\",\"gym-stepmill30-ecc30\",\"gym-stepmill30-hold30\",\"gym-bike30\",\"gym-bike30-ecc30\",\"gym-bike30-hold30\",\"gym-elliptical30\",\"gym-elliptical30-ecc30\",\"gym-elliptical30-hold30\",\"gym-skierg30\",\"gym-skierg30-ecc30\",\"gym-skierg30-hold30\",\"gym-airbike30\",\"gym-airbike30-ecc30\",\"gym-airbike30-hold30\",\"gym-recumbent30\",\"gym-recumbent30-ecc30\",\"gym-recumbent30-hold30\",\"home-squatnarrow30\",\"home-squatnarrow30-ecc30\",\"home-squatnarrow30-hold30\",\"home-squatheels30\",\"home-squatheels30-ecc30\",\"home-squatheels30-hold30\",\"home-squatpulse30\",\"home-squatpulse30-ecc30\",\"home-squatpulse30-hold30\",\"home-cyclistdb30\",\"home-cyclistdb30-ecc30\",\"home-cyclistdb30-hold30\",\"home-lungefront30\",\"home-lungefront30-ecc30\",\"home-lungefront30-hold30\",\"home-lungeoffset30\",\"home-lungeoffset30-ecc30\",\"home-lungeoffset30-hold30\",\"home-splithigh30\",\"home-splithigh30-ecc30\",\"home-splithigh30-hold30\",\"home-rdlstagger30\",\"home-rdlstagger30-ecc30\",\"home-rdlstagger30-hold30\",\"home-rdlsuit30\",\"home-rdlsuit30-ecc30\",\"home-rdlsuit30-hold30\",\"home-bridgemarch30\",\"home-bridgemarch30-ecc30\",\"home-bridgemarch30-hold30\",\"home-bridgesqueeze30\",\"home-bridgesqueeze30-ecc30\",\"home-bridgesqueeze30-hold30\",\"home-thrustsingle30\",\"home-thrustsingle30-ecc30\",\"home-thrustsingle30-hold30\",\"home-plankjack30\",\"home-plankjack30-ecc30\",\"home-plankjack30-hold30\",\"home-plankstep30\",\"home-plankstep30-ecc30\",\"home-plankstep30-hold30\",\"home-plankdrag30\",\"home-plankdrag30-ecc30\",\"home-plankdrag30-hold30\",\"home-sideabduct30\",\"home-sideabduct30-ecc30\",\"home-sideabduct30-hold30\",\"home-sidehip30\",\"home-sidehip30-ecc30\",\"home-sidehip30-hold30\",\"home-bearreach30\",\"home-bearreach30-ecc30\",\"home-bearreach30-hold30\",\"home-deadbugpress30\",\"home-deadbugpress30-ecc30\",\"home-deadbugpress30-hold30\",\"home-reversebird30\",\"home-reversebird30-ecc30\",\"home-reversebird30-hold30\",\"home-scappush30\",\"home-scappush30-ecc30\",\"home-scappush30-hold30\",\"home-pushwide30\",\"home-pushwide30-ecc30\",\"home-pushwide30-hold30\",\"home-pushcloseknee30\",\"home-pushcloseknee30-ecc30\",\"home-pushcloseknee30-hold30\",\"home-pikepause30\",\"home-pikepause30-ecc30\",\"home-pikepause30-hold30\",\"home-lateralstepover30\",\"home-lateralstepover30-ecc30\",\"home-lateralstepover30-hold30\",\"home-hopforward30\",\"home-hopforward30-ecc30\",\"home-hopforward30-hold30\",\"home-hip909030\",\"home-hip909030-ecc30\",\"home-hip909030-hold30\",\"home-wallangel30\",\"home-wallangel30-ecc30\",\"home-wallangel30-hold30\"]")))
        TYPES = jsonValue("{\"train\":\"strength\",\"cardio\":\"cardio\",\"mobility\":\"recovery\",\"rest\":\"rest\"}")
        CATEGORY = jsonValue("{\"strength\":\"Fuerza\",\"cardio\":\"Cardio\",\"recovery\":\"Movilidad\"}")
        SPANISH = jsonValue("{\"strength\":\"Fuerza\",\"cardio\":\"Cardio\",\"recovery\":\"Movilidad\"}")
        LOWER = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"Piernas\",\"Cadera\"]")))
        UPPER = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"]")))
        byId = jsConstruct(global_Map, arrayOf<dynamic>(E.exercises.map((fun(e: dynamic): dynamic {
            return arrayOf<dynamic>(e.id, e)
        }))))
        val classicById: dynamic = E.byId
        E.byId = (fun(id: dynamic): dynamic {
            return jsOr(byId["get"](id)) { classicById(id) }
        })
        available = E.exercises.filter((fun(e: dynamic): dynamic {
            return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(VERIFIED_IDS.has(e.id)) { e.modelPath30 }) { e.demoPath30 }) { dynamicValue(jsonValue("[\"Fuerza\",\"Cardio\",\"Movilidad\"]")).includes(e.category) }) { e.group }) { global_Array.isArray(e.zones) }) { (jsTypeOf(e.level) === "number") }) { global_Array.isArray(e.requirements) }
        }))
        fieldNames = jsonValue("[\"age\",\"goal\",\"goalGroup\",\"finalGoal\",\"finalTarget\",\"focus\",\"difficulty\",\"experience\",\"equipment\",\"equipmentModes3880\",\"gymEquipment23\",\"bench\",\"step\",\"minutes\",\"frequency\",\"dailyMissions\",\"routineExercises3878\",\"trainingMode\",\"trainingScale\",\"weekPlan\",\"weekFocus\",\"injuries\",\"restrictions\",\"health\",\"limits\",\"preferredTrainer\",\"load\",\"restSeconds\",\"materials46\",\"capabilities46\",\"dynamicRequestedFocus3847\"]")
        asJSON = (fun(o: dynamic): dynamic {
            return global_JSON.stringify(nullableOr(o) { null })
        })
        hash = (fun(s: dynamic): dynamic {
            var h: dynamic = 2166136261.0
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration5 = true
                while (true) {
                    if (!firstIteration5) {
                        run {
                            val previous6: dynamic = numeric(i)
                            i = jsAdd(previous6, 1)
                        }
                    }
                    firstIteration5 = false
                    if (!truth(jsLess(i, s.length))) break
                    run {
                        h = jsBitXor(h, s.charCodeAt(i))
                    }
                    h = global_Math.imul(h, 16777619)
                }
            }
            return jsUnsignedShift(h, 0)
        })
        profileKey = (fun(p: dynamic): dynamic {
            return hash(asJSON(fieldNames.map((fun(k: dynamic): dynamic {
                return arrayOf<dynamic>(k, onPresent(p) { value8 -> value8[k] })
            }))))["toString"](36)
        })
        val mod: dynamic = (fun(a: dynamic, n: dynamic): dynamic {
            return (if (truth(n)) jsMod(jsAdd(jsMod(a, n), n), n) else 0)
        })
        injury = (fun(p: dynamic): dynamic {
            return jsOr(jsOr(jsOr((!truth((!truth(dynamicValue(jsOr(onPresent(p) { value10 -> value10.injuries }) { js("[]") }).some((fun(x: dynamic): dynamic {
                return (x.stage !== "past")
            }))))))) { (!truth((!truth(onPresent(p) { value11 -> value11.limits })))) }) { (!truth((!truth(onPresent(p) { value12 -> onPresent(value12.health) { value13 -> value13.special } })))) }) { dynamicValue(jsonValue("[\"mild\",\"moderate\",\"strong\"]")).includes(onPresent(p) { value14 -> onPresent(value14.health) { value15 -> value15.pain } }) }
        })
        poolMemo46 = jsConstruct(global_Map, js("[]"))
        goalGroups = jsonValue("{\"upper\":[\"Pecho\",\"Espalda\",\"Brazos\",\"Hombros\"],\"lower\":[\"Piernas\",\"Cadera\"],\"core\":[\"Core\",\"Abdomen\"],\"general\":[\"Piernas\",\"Pecho\",\"Espalda\",\"Cadera\",\"Core\",\"Abdomen\",\"Hombros\",\"Brazos\"]}")
        val previousMake: dynamic = E.makeCycle
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg301: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg301) { js("({})") }
            val c: dynamic = previousMake(p, start, index, last, logs)
            patch(c, p, logs, start)
            return c
        })
        val previousCurrent: dynamic = global_currentCycle
        global_currentCycle = (fun(): dynamic {
            val c: dynamic = previousCurrent()
            if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
                return c
            }
            val changes: dynamic = patch(c, global_state.profile, jsOr(global_state.logs) { js("({})") }, E.dateKey())
            val today: dynamic = c.days.find((fun(d: dynamic): dynamic {
                return (d.date === E.dateKey())
            }))
            val updated: dynamic = jsAnd(today) { onPresent(root.HunterPlanner379) { value304 -> run { val owner306: dynamic = value304; onPresent(owner306.repairEmptyLog) { method305 -> invokeWith(method305, owner306, arrayOf<dynamic>(today)) } } } }
            if (truth(jsAnd(jsAnd(jsOr(changes) { updated }) { (js("typeof save") === "function") }) { (!truth(onPresent(root.Runtime20) { value307 -> value307.initializing })) })) {
                global_save()
            }
            return c
        })
        val originalAll: dynamic = global_allRoutines
        global_allRoutines = (fun(): dynamic {
            return jsArray { array323 ->
                appendAll(array323, originalAll())
                appendAll(array323, templates(onPresent(global_state) { value324 -> value324.profile }, jsOr(onPresent(global_state) { value325 -> value325.logs }) { js("({})") }))
            }
        })
        val oldCompatible: dynamic = A.routineCompatible
        A.routineCompatible = (fun(r: dynamic, p: dynamic): dynamic {
            return (if (truth(onPresent(r) { value330 -> value330.safe381 })) jsAnd(jsAnd(gate(p).ok) { (!truth((!truth(onPresent(r.items) { value331 -> value331.length })))) }) { r.items.every((fun(x: dynamic): dynamic {
                return compatible(byId["get"](x.id), p, injury(p))
            })) } else oldCompatible(r, p))
        })
        val previousRoutines: dynamic = global_routines
        global_routines = (fun(): dynamic {
            val html: dynamic = previousRoutines()
            val count: dynamic = available.length
            return jsAdd(jsAdd(jsAdd("<div class=\"notice\" role=\"note\"><b>Generador dinámico V38.1</b><p>", count), " demostraciones 2D con metadatos disponibles para selección. Las rutinas de muestra se crean al abrir esta sección; las combinaciones no se almacenan. Los filtros del perfil se aplican antes de asignar.</p></div>"), html)
        })
        root.HunterDynamic381 = jsObject { obj336 ->
            obj336["version"] = VERSION
            obj336["catalogCount"] = available.length
            obj336["verifiedAssetCount"] = VERIFIED_IDS.size
            obj336["pool"] = pool
            obj336["choose"] = choose
            obj336["patch"] = patch
            obj336["feedback"] = feedback
            obj336["templates"] = templates
            obj336["gate"] = gate
            obj336["compatible"] = compatible
            obj336["locked"] = locked
            obj336["profileKey"] = profileKey
            obj336["integrity"] = (fun(): dynamic {
                return jsObject { obj337 ->
                    obj337["verified"] = VERIFIED_IDS.size
                    obj337["usable"] = available.length
                    obj337["missing"] = dynamicValue(jsArray { array339 ->
                        appendAll(array339, VERIFIED_IDS)
                    }).filter((fun(id: dynamic): dynamic {
                        return (!truth(byId.has(id)))
                    })).length
                }
            })
        }
        return absent
    }))(global_window)
}
