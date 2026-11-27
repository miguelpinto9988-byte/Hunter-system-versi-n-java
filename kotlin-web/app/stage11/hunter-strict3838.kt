@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterstrict3838

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Warm24") private external var global_Warm24: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("localStorage") private external var global_localStorage: dynamic
@JsName("KEY") private external var global_KEY: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterStrict3838() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var S: dynamic = absent
        var D: dynamic = absent
        var KIND: dynamic = absent
        var LABEL: dynamic = absent
        var clone: dynamic = absent
        var own: dynamic = absent
        var type: dynamic = absent
        var matches: dynamic = absent
        var accepted: dynamic = absent
        var fingerprint: dynamic = absent
        var past: dynamic = absent
        var archiveOld: dynamic = absent
        var strictDay: dynamic = absent
        var apply: dynamic = absent
        type = fun(p: dynamic, date: dynamic): dynamic {
            if (truth(jsOr((!truth(onPresent(p) { value5 -> value5.weekPlan }))) { (!truth(S.valid(p))) })) {
                return null
            }
            val wd: dynamic = S.weekday(date)
            val choice: dynamic = p.weekPlan[wd]
            if (truth((choice === "train"))) {
                return (if (truth(dynamicValue(jsonValue("[\"lower\",\"core\"]")).includes(onPresent(p.weekFocus) { value6 -> value6[wd] }))) p.weekFocus[wd] else "upper")
            }
            return (if (truth(own(KIND, choice))) choice else null)
        }
        matches = fun(item: dynamic, t: dynamic): dynamic {
            if (truth((t === "rest"))) {
                return false
            }
            val ex: dynamic = E.byId(onPresent(item) { value9 -> value9.id })
            return jsAnd((!truth((!truth(ex))))) { root.HunterGroups49.matches(ex, t) }
        }
        accepted = fun(items: dynamic, t: dynamic): dynamic {
            return jsAnd(jsAnd(global_Array.isArray(items)) { jsMore(items.length, 0) }) { items.every((fun(x: dynamic): dynamic {
                return matches(x, t)
            })) }
        }
        fingerprint = fun(p: dynamic, t: dynamic): dynamic {
            return global_JSON.stringify(jsObject { obj14 ->
                obj14["groupsVersion"] = root.HunterGroups49.version
                obj14["t"] = t
                obj14["goal"] = onPresent(p) { value15 -> value15.goal }
                obj14["difficulty"] = onPresent(p) { value16 -> value16.difficulty }
                obj14["equipment"] = onPresent(p) { value17 -> value17.equipment }
                obj14["equipmentModes3880"] = onPresent(p) { value18 -> value18.equipmentModes3880 }
                obj14["gymEquipment23"] = onPresent(p) { value19 -> value19.gymEquipment23 }
                obj14["age"] = onPresent(p) { value20 -> value20.age }
                obj14["materials46"] = onPresent(p) { value21 -> value21.materials46 }
                obj14["capabilities46"] = onPresent(p) { value22 -> value22.capabilities46 }
                obj14["minutes"] = onPresent(p) { value23 -> value23.minutes }
                obj14["injuries"] = onPresent(p) { value24 -> value24.injuries }
                obj14["restrictions"] = onPresent(p) { value25 -> value25.restrictions }
                obj14["health"] = onPresent(p) { value26 -> value26.health }
                obj14["limits"] = onPresent(p) { value27 -> value27.limits }
                obj14["trainingScale"] = onPresent(p) { value28 -> value28.trainingScale }
            })
        }
        past = fun(logs: dynamic, date: dynamic): dynamic {
            return global_Object.fromEntries(global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg31: dynamic): dynamic {
                var d: dynamic = absent
                val unpacked32: dynamic = fromIterable(arg31)
                d = unpacked32[0]
                return jsLess(d, date)
            })))
        }
        archiveOld = fun(log: dynamic, newItems: dynamic, date: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(log))) { log.complete }) { log.manual })) {
                return false
            }
            val old: dynamic = (if (truth(global_Array.isArray(log.items))) log.items else js("[]"))
            val prior: dynamic = (if (truth(global_Array.isArray(log.priorCompleted3838))) log.priorCompleted3838 else js("[]"))
            val remaining: dynamic = old.slice()
            val output: dynamic = newItems.map((fun(template: dynamic): dynamic {
                val row: dynamic = clone(template)
                val idx: dynamic = remaining.findIndex((fun(x: dynamic): dynamic {
                    return jsAnd(jsAnd(x) { (x.id === template.id) }) { (!truth(x.archivedProfile3829)) }
                }))
                val sets: dynamic = global_Math.max(1, global_Math.min(12, jsOr(global_Number(template.sets)) { 1 }))
                val `actual`: dynamic = global_Array.from(jsObject { obj35 ->
                    obj35["length"] = sets
                }, (fun(): dynamic {
                    return jsObject { obj36 ->
                        obj36["done"] = false
                        obj36["value"] = template.target
                        obj36["kg"] = 0
                    }
                }))
                if (truth(jsAtLeast(idx, 0))) {
                    val item: dynamic = remaining.splice(idx, 1)[0]
                    val done: dynamic = dynamicValue(jsOr(item["actual"]) { js("[]") }).filter((fun(a: dynamic): dynamic {
                        return (onPresent(a) { value38 -> value38.done } === true)
                    })).map(clone)
                    if (true) {
                        var i: dynamic = absent
                        i = 0
                        var firstIteration39 = true
                        while (true) {
                            if (!firstIteration39) {
                                run {
                                    val previous40: dynamic = numeric(i)
                                    i = jsAdd(previous40, 1)
                                }
                            }
                            firstIteration39 = false
                            if (!truth(jsLess(i, global_Math.min(sets, done.length)))) break
                            `actual`[i] = done[i]
                        }
                    }
                    if (truth(jsMore(done.length, sets))) {
                        prior.push(jsObject { obj43 ->
                            obj43["id"] = item.id
                            obj43["actual"] = done.slice(sets)
                            obj43["reason"] = "series anteriores"
                        })
                    }
                }
                row["actual"] = `actual`
                return row
            }))
            if (true) {
                val iterator46: dynamic = iteratorOf(remaining)
                while (true) {
                    val next47: dynamic = iterator46.next()
                    if (truth(next47.done)) break
                    var x: dynamic = absent
                    x = next47.value
                    val done: dynamic = dynamicValue(jsOr(onPresent(x) { value49 -> value49["actual"] }) { js("[]") }).filter((fun(a: dynamic): dynamic {
                        return (onPresent(a) { value50 -> value50.done } === true)
                    }))
                    if (truth(done.length)) {
                        prior.push(jsObject { obj51 ->
                            obj51["id"] = x.id
                            obj51["actual"] = clone(done)
                            obj51["reason"] = "categoría cambiada"
                        })
                    }
                }
            }
            log.items = output
            if (truth(prior.length)) {
                log.priorCompleted3838 = prior
            }
            log.profileChanged3829 = true
            log.strict3838 = date
            return true
        }
        strictDay = fun(c: dynamic, d: dynamic, p: dynamic, logs: dynamic, date: dynamic, tracker: dynamic): dynamic {
            if (truth(jsOr((!truth(d))) { jsLess(d.date, date) })) {
                return false
            }
            val t: dynamic = type(p, d.date)
            val log: dynamic = onPresent(logs) { value62 -> value62[d.date] }
            if (truth((!truth(t)))) {
                return false
            }
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(d.customized) { d.manual }) { onPresent(log) { value63 -> value63.manual } }) { onPresent(log) { value64 -> value64.complete } }) { onPresent(log) { value65 -> onPresent(value65.items) { value66 -> value66.some((fun(x: dynamic): dynamic {
                return onPresent(x["actual"]) { value67 -> value67.some((fun(a: dynamic): dynamic {
                    return a.done
                })) }
            })) } } }) { jsAnd(d.routineId) { (!truth(d.creatorAssigned)) } })) {
                return false
            }
            val sig: dynamic = fingerprint(p, t)
            val coherent: dynamic = (if (truth((t === "rest"))) jsAnd((d.kind === "rest")) { jsOr((!truth(d.items))) { (d.items.length === 0) } } else jsAnd(jsAnd((d.kind === KIND[t])) { accepted(d.items, t) }) { (d.scheduleChoice === p.weekPlan[S.weekday(d.date)]) })
            val noWork: dynamic = jsAnd(jsAnd((t !== "rest")) { (d.strictBlocked3838 === sig) }) { (!truth(dynamicValue(jsOr(d.items) { js("[]") }).length)) }
            val staleLog: dynamic = (!truth((!truth(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd((d.date === date)) { log }) { (!truth(log.complete)) }) { (!truth(log.manual)) }) { global_Array.isArray(log.items) }) { log.items.some((fun(x: dynamic): dynamic {
                return jsOr((!truth(onPresent(d.items) { value68 -> value68.some((fun(y: dynamic): dynamic {
                    return (y.id === x.id)
                })) }))) { (!truth(matches(x, t))) }
            })) }))))
            if (truth(jsAnd((d.strictSignature3838 === sig)) { jsOr(coherent) { noWork } })) {
                if (truth(staleLog)) {
                    archiveOld(log, jsOr(d.items) { js("[]") }, date)
                    return true
                }
                return false
            }
            if (truth(jsAnd(coherent) { jsEqual(d.strictSignature3838, null) })) {
                d.strictSignature3838 = sig
                d.strictType3838 = t
                if (truth(staleLog)) {
                    archiveOld(log, jsOr(d.items) { js("[]") }, date)
                }
                return true
            }
            var result: dynamic = jsonValue("{\"items\":[],\"blocked\":null}")
            if (truth((t !== "rest"))) {
                result = D.choose(p, KIND[t], jsAdd(jsAdd(jsAdd(jsAdd(D.profileKey(p), "|"), d.date), "|"), jsOr(onPresent(c) { value73 -> value73.index }) { 0 }), past(logs, d.date), d.date, jsConstruct(global_Set, js("[]")), tracker)
                if (truth((!truth(accepted(result.items, t))))) {
                    result = jsObject { obj74 ->
                        obj74["items"] = js("[]")
                        obj74["blocked"] = jsOr(result.blocked) { jsAdd(jsAdd("No se encontraron ejercicios compatibles de ", LABEL[t]), "; revisa el perfil o las restricciones.") }
                    }
                }
            }
            val next: dynamic = (if (truth((t === "rest"))) js("[]") else clone(jsOr(result.items) { js("[]") }))
            d.kind = KIND[t]
            d.scheduleChoice = p.weekPlan[S.weekday(d.date)]
            d.title = (if (truth((t === "rest"))) "Descanso elegido" else (if (truth(result.blocked)) jsAdd(LABEL[t], " · requiere revisión") else jsAdd(LABEL[t], " · sesión del día")))
            d.items = next
            d.tasks = (if (truth(result.blocked)) arrayOf<dynamic>(jsObject { obj85 ->
                obj85["id"] = "review-strict3838"
                obj85["text"] = jsAdd("Revisar restricciones para ", LABEL[t])
                obj85["done"] = false
            }) else js("[]"))
            d.dynamic381 = true
            d.strictType3838 = t
            d.strictSignature3838 = sig
            d.strictBlocked3838 = (if (truth(result.blocked)) sig else null)
            if (truth(result.blocked)) {
                d.blocked381 = result.blocked
                d.blocked378 = result.blocked
            } else {
                removeKey(d, "blocked381")
                removeKey(d, "blocked378")
                removeKey(d, "blocked379")
            }
            removeKey(d, "creatorAssigned")
            removeKey(d, "autoTemplateId")
            removeKey(d, "sourceSequence")
            if (truth((t === "rest"))) {
                removeKey(d, "routineId")
            }
            if (truth((d.date === date))) {
                d.dailyFixed3828 = date
                if (truth(onPresent(root.HunterStable3828) { value100 -> value100.safetyKey })) {
                    d.dailySafety3828 = root.HunterStable3828.safetyKey(p)
                }
                if (truth(log)) {
                    archiveOld(log, next, date)
                    log.kind = d.kind
                }
            }
            return true
        }
        apply = fun(c: dynamic, p: dynamic, arg107: dynamic, arg108: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg107) { js("({})") }
            var date: dynamic = absent
            date = definedOr(arg108) { E.dateKey() }
            if (truth(jsOr(jsOr(jsOr((!truth(onPresent(c) { value109 -> onPresent(value109.days) { value110 -> value110.length } }))) { (!truth(p)) }) { (!truth(S.valid(p))) }) { (onPresent(c.reviews) { value111 -> onPresent(value111.at((-numeric(1)))) { value112 -> value112.action } } === "pausar") })) {
                return 0
            }
            var changed: dynamic = 0
            val tracker: dynamic = root.HunterPlan3810.tracker(c, logs, date)
            val tracked: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(tracker.snapshot().map((fun(x: dynamic): dynamic {
                return x.date
            }))))
            val add: dynamic = tracker.add
            tracker.add = (fun(day: dynamic, ex: dynamic, sets: dynamic): dynamic {
                tracked.add(day)
                add(day, ex, sets)
                return absent
            })
            if (true) {
                val iterator117: dynamic = iteratorOf(c.days)
                while (true) {
                    val next118: dynamic = iterator117.next()
                    if (truth(next118.done)) break
                    var d: dynamic = absent
                    d = next118.value
                    if (truth(strictDay(c, d, p, logs, date, tracker))) {
                        run {
                            val previous119: dynamic = numeric(changed)
                            changed = jsAdd(previous119, 1)
                        }
                    }
                    if (truth(jsAnd(jsAtLeast(d.date, date)) { (!truth(tracked.has(d.date))) })) {
                        if (true) {
                            val iterator120: dynamic = iteratorOf(jsOr(d.items) { js("[]") })
                            while (true) {
                                val next121: dynamic = iterator120.next()
                                if (truth(next121.done)) break
                                var item: dynamic = absent
                                item = next121.value
                                val ex: dynamic = E.byId(item.id)
                                if (truth(ex)) {
                                    tracker.add(d.date, ex, item.sets)
                                }
                            }
                        }
                    }
                }
            }
            return changed
        }
        if (truth(root.HunterStrict3838)) {
            return absent
        }
        E = root.Engine
        S = root.Schedule
        D = root.HunterDynamic381
        if (truth(jsOr(jsOr((!truth(E))) { (!truth(S)) }) { (!truth(D)) })) {
            return absent
        }
        val UPPER: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"]")))
        val LOWER: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"Piernas\",\"Cadera\"]")))
        val CORE: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"Core\",\"Abdomen\"]")))
        val CATEGORY: dynamic = jsonValue("{\"upper\":\"Fuerza\",\"lower\":\"Fuerza\",\"core\":\"Fuerza\",\"cardio\":\"Cardio\",\"mobility\":\"Movilidad\"}")
        KIND = jsonValue("{\"upper\":\"strength\",\"lower\":\"strength\",\"core\":\"strength\",\"cardio\":\"cardio\",\"mobility\":\"recovery\",\"rest\":\"rest\"}")
        LABEL = jsonValue("{\"upper\":\"Tren superior\",\"lower\":\"Tren inferior\",\"core\":\"Tren central\",\"cardio\":\"Cardio\",\"mobility\":\"Movilidad\",\"rest\":\"Descanso\"}")
        clone = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        own = (fun(o: dynamic, k: dynamic): dynamic {
            return global_Object.prototype.hasOwnProperty.call(jsOr(o) { js("({})") }, k)
        })
        if (truth(jsAnd((js("typeof Warm24") !== "undefined")) { (jsTypeOf(global_Warm24.select) === "function") })) {
            val originalWarm: dynamic = global_Warm24.select
            global_Warm24.select = (fun(day: dynamic, arg126: dynamic): dynamic {
                val receiver124: dynamic = js("this")
                val callArgs125: dynamic = js("arguments")
                var p: dynamic = absent
                p = definedOr(arg126) { onPresent(global_state) { value127 -> value127.profile } }
                if (truth(jsOr((!truth(onPresent(day) { value128 -> onPresent(value128.items) { value129 -> value129.length } }))) { (!truth(p)) })) {
                    return originalWarm.apply(receiver124, callArgs125)
                }
                return root.HunterGroups49.phase(day, p)
            })
        }
        val prevCycle: dynamic = (if (truth((js("typeof currentCycle") === "function"))) global_currentCycle else null)
        if (truth(prevCycle)) {
            var busy: dynamic = false
            global_currentCycle = (fun(): dynamic {
                val receiver130: dynamic = js("this")
                val callArgs131: dynamic = js("arguments")
                val c: dynamic = prevCycle.apply(receiver130, callArgs131)
                if (truth(jsOr(jsOr(busy) { (!truth(c)) }) { (!truth(onPresent(global_state) { value132 -> value132.profile })) })) {
                    return c
                }
                busy = true
                try {
                    if (truth(jsAnd(jsAnd(jsMore(apply(c, global_state.profile, jsOr(global_state.logs) { js("({})") }, E.dateKey()), 0)) { (js("typeof save") === "function") }) { (!truth(onPresent(root.Runtime20) { value133 -> value133.initializing })) })) {
                        global_save()
                    }
                } finally {
                    busy = false
                }
                return c
            })
        }
        val prevSubmit: dynamic = (if (truth((js("typeof submitProfile") === "function"))) global_submitProfile else null)
        if (truth(prevSubmit)) {
            global_submitProfile = (fun(ev: dynamic): dynamic {
                val receiver134: dynamic = js("this")
                val callArgs135: dynamic = js("arguments")
                val answer: dynamic = prevSubmit.apply(receiver134, callArgs135)
                if (truth(jsAnd((onPresent(ev) { value136 -> onPresent(value136.target) { value137 -> value137.id } } === "profileForm")) { onPresent(global_state) { value138 -> value138.profile } })) {
                    val disk: dynamic = dynamicValue((fun(): dynamic {
                        try {
                            return global_JSON.parse(global_localStorage.getItem(global_KEY))
                        } catch (_: dynamic) {
                            return null
                        }
                        return absent
                    }))()
                    if (truth(onPresent(disk) { value139 -> value139.profile })) {
                        val c: dynamic = onPresent(global_state) { value140 -> onPresent(value140.cycles) { value141 -> value141.find((fun(x: dynamic): dynamic {
                            return onPresent(x.days) { value142 -> value142.some((fun(d: dynamic): dynamic {
                                return (d.date === E.dateKey())
                            })) }
                        })) } }
                        if (truth(jsAnd(jsAnd(c) { jsMore(apply(c, global_state.profile, jsOr(global_state.logs) { js("({})") }, E.dateKey()), 0) }) { (js("typeof save") === "function") })) {
                            global_save()
                        }
                    }
                }
                return answer
            })
        }
        root.HunterStrict3838 = global_Object.freeze(jsObject { obj145 ->
            obj145["version"] = "38.38"
            obj145["type"] = type
            obj145["matches"] = matches
            obj145["accepted"] = accepted
            obj145["apply"] = apply
            obj145["archiveOld"] = archiveOld
        })
        return absent
    }))(global_window)
}
