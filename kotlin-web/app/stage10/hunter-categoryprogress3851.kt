@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntercategoryprogress3851

import hunter.browser.*

@JsName("Error") private external var global_Error: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterCategoryprogress3851() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var CATS: dynamic = absent
        var LABELS: dynamic = absent
        var DAY: dynamic = absent
        var limit: dynamic = absent
        var rpe: dynamic = absent
        var isoDays: dynamic = absent
        var meta: dynamic = absent
        var riskyProfile: dynamic = absent
        var categoryOfLog: dynamic = absent
        var categoryOfItems: dynamic = absent
        var categoryHistory: dynamic = absent
        var resolve: dynamic = absent
        var globalSymptoms: dynamic = absent
        var feedback: dynamic = absent
        var allowedItem: dynamic = absent
        var targetCap: dynamic = absent
        var adjustItems: dynamic = absent
        categoryOfLog = fun(l: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(l))) { (!truth(global_Array.isArray(l.items))) }) { (!truth(l.items.length)) })) {
                return null
            }
            if (truth((l.kind === "cardio"))) {
                return "cardio"
            }
            if (truth((l.kind === "recovery"))) {
                return "mobility"
            }
            val tally: dynamic = jsonValue("{\"upper\":0,\"lower\":0,\"core\":0,\"cardio\":0,\"mobility\":0}")
            if (true) {
                val iterator17: dynamic = iteratorOf(l.items)
                while (true) {
                    val next18: dynamic = iterator17.next()
                    if (truth(next18.done)) break
                    var x: dynamic = absent
                    x = next18.value
                    val group: dynamic = onPresent(meta(onPresent(x) { value19 -> value19.id })) { value20 -> value20.group }
                    if (truth(global_Object.prototype.hasOwnProperty.call(tally, group))) {
                        run {
                            val target21: dynamic = tally
                            val key22: dynamic = group
                            val previous23: dynamic = numeric(target21[key22])
                            target21[key22] = jsAdd(previous23, 1)
                        }
                    }
                }
            }
            val winner: dynamic = global_Object.entries(tally).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract(b[1], a[1])
            }))
            return (if (truth(jsAnd(jsMore(winner[0][1], 0)) { jsMore(winner[0][1], winner[1][1]) })) winner[0][0] else null)
        }
        categoryOfItems = fun(items: dynamic, kind: dynamic, focus: dynamic): dynamic {
            if (truth((kind === "cardio"))) {
                return "cardio"
            }
            if (truth((kind === "recovery"))) {
                return "mobility"
            }
            if (truth(dynamicValue(jsonValue("[\"upper\",\"lower\",\"core\"]")).includes(focus))) {
                return focus
            }
            return categoryOfLog(jsObject { obj26 ->
                obj26["kind"] = kind
                obj26["items"] = items
            })
        }
        categoryHistory = fun(logs: dynamic, date: dynamic, category: dynamic): dynamic {
            if (truth(jsOr((!truth(CATS.includes(category)))) { (!truth(DAY.test(date))) })) {
                return js("[]")
            }
            return global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg55: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked56: dynamic = fromIterable(arg55)
                d = unpacked56[0]
                l = unpacked56[1]
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(DAY.test(d)) { jsLess(d, date) }) { jsAtMost(isoDays(date, d), 90) }) { l }) { (categoryOfLog(l) === category) }) { jsOr((l.complete === true)) { (l.pain === true) } }
            })).sort((fun(arg57: dynamic, arg59: dynamic): dynamic {
                var a: dynamic = absent
                val unpacked58: dynamic = fromIterable(arg57)
                a = unpacked58[0]
                var b: dynamic = absent
                val unpacked60: dynamic = fromIterable(arg59)
                b = unpacked60[0]
                return a.localeCompare(b)
            })).slice((-numeric(12))).map((fun(arg61: dynamic): dynamic {
                var date: dynamic = absent
                var log: dynamic = absent
                val unpacked62: dynamic = fromIterable(arg61)
                date = unpacked62[0]
                log = unpacked62[1]
                return jsObject { obj63 ->
                    obj63["date"] = date
                    obj63["effort"] = rpe(log.rpe)
                    obj63["pain"] = (log.pain === true)
                    obj63["fatigue"] = (log.fatigue === true)
                    obj63["complete"] = (log.complete === true)
                    obj63["manual"] = (log.manual === true)
                }
            }))
        }
        resolve = fun(logs: dynamic, date: dynamic, category: dynamic): dynamic {
            val entries: dynamic = categoryHistory(logs, date, category)
            var level: dynamic = 0
            var counted: dynamic = 0
            if (true) {
                val iterator66: dynamic = iteratorOf(entries)
                while (true) {
                    val next67: dynamic = iterator66.next()
                    if (truth(next67.done)) break
                    var entry: dynamic = absent
                    entry = next67.value
                    if (truth(jsOr(entry.pain) { entry.fatigue })) {
                        level = global_Math.max((-numeric(2)), jsSubtract(level, 2))
                        run {
                            val previous68: dynamic = numeric(counted)
                            counted = jsAdd(previous68, 1)
                        }
                        continue
                    }
                    if (truth(jsOr((!truth(entry.complete))) { (!truth(entry.effort)) })) {
                        continue
                    }
                    if (truth(jsAtMost(entry.effort, 4))) {
                        level = global_Math.min(4, jsAdd(level, 1))
                    } else {
                        if (truth(jsAtLeast(entry.effort, 8))) {
                            level = global_Math.max((-numeric(2)), jsSubtract(level, 1))
                        }
                    }
                    run {
                        val previous69: dynamic = numeric(counted)
                        counted = jsAdd(previous69, 1)
                    }
                }
            }
            val latest: dynamic = jsOr(entries.at((-numeric(1)))) { null }
            val last: dynamic = (if (truth(onPresent(latest) { value70 -> value70.pain })) "dolor" else (if (truth(onPresent(latest) { value71 -> value71.fatigue })) "fatiga" else (if (truth((!truth(onPresent(latest) { value72 -> value72.effort })))) "sin_respuesta" else (if (truth(jsAtMost(latest.effort, 4))) "facil" else (if (truth(jsAtLeast(latest.effort, 8))) "exigente" else "adecuado")))))
            return jsObject { obj73 ->
                obj73["category"] = category
                obj73["label"] = jsOr(LABELS[category]) { "" }
                obj73["level"] = level
                obj73["history"] = counted
                obj73["lastScore"] = jsOr(onPresent(latest) { value74 -> value74.effort }) { null }
                obj73["lastDate"] = jsOr(onPresent(latest) { value75 -> value75.date }) { null }
                obj73["last"] = last
                obj73["reason"] = (if (truth((!truth(counted)))) "Sin cuestionarios válidos de esta categoría" else (if (truth(jsMore(level, 0))) "Progresión gradual según respuestas anteriores de esta categoría" else (if (truth(jsLess(level, 0))) "Dosis moderada por esfuerzo alto o molestias anteriores" else "Mantener dosis de referencia")))
            }
        }
        globalSymptoms = fun(logs: dynamic, date: dynamic): dynamic {
            return jsOr(global_Object.entries(jsOr(logs) { js("({})") }).some((fun(arg78: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked79: dynamic = fromIterable(arg78)
                d = unpacked79[0]
                l = unpacked79[1]
                return jsAnd(jsAnd(jsAnd(DAY.test(d)) { jsLess(d, date) }) { jsAtMost(isoDays(date, d), 7) }) { jsOr((onPresent(l) { value80 -> value80.pain } === true)) { (onPresent(l) { value81 -> value81.fatigue } === true) } }
            }))) { global_Boolean(onPresent(root.HunterConstraints3811) { value82 -> run { val owner84: dynamic = value82; onPresent(owner84.symptomTrend) { method83 -> onPresent(invokeWith(method83, owner84, arrayOf<dynamic>(logs, date))) { value85 -> value85.recentSymptom } } } }) }
        }
        feedback = fun(raw: dynamic, logs: dynamic, date: dynamic): dynamic {
            return jsObject { obj88 ->
                copyFields(obj88, raw)
                obj88["strain"] = globalSymptoms(logs, date)
                obj88["canProgress"] = false
            }
        }
        allowedItem = fun(e: dynamic, item: dynamic, category: dynamic): dynamic {
            if (truth(jsOr(jsOr(jsOr((!truth(e))) { (!truth(item)) }) { (!truth(meta(e))) }) { (meta(e).group !== category) })) {
                return false
            }
            val m: dynamic = meta(e)
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(m.power) { m.impact }) { (m.role === "activation") }) { (m.intent50 === "preparation") }) { e.highSkill46 }) { onPresent(e.doseCap46) { value91 -> value91.highSkill } }) { (e.impact === "Con saltos") })) {
                return false
            }
            return (if (truth(jsOr((category === "upper")) { (category === "lower") })) (m.primary === true) else (if (truth((category === "core"))) (m.primary === true) else (if (truth((category === "cardio"))) (m.role === "conditioning") else (category === "mobility"))))
        }
        targetCap = fun(e: dynamic, item: dynamic, p: dynamic): dynamic {
            val seconds: dynamic = (e.unit === "sec")
            var max: dynamic = (if (truth(seconds)) (if (truth((e.category === "Movilidad"))) 60 else 90) else 16)
            if (truth(jsAnd(e.doseCap46) { global_Number.isFinite(global_Number(e.doseCap46.target)) })) {
                val scale: dynamic = limit(jsOr(global_Number(onPresent(p) { value94 -> value94.trainingScale })) { 1 }, 0.25, 1)
                max = global_Math.min(max, global_Math.max(1, global_Math.floor(jsMultiply(e.doseCap46.target, scale))))
            }
            return global_Math.max(1, max)
        }
        adjustItems = fun(items: dynamic, ctx: dynamic): dynamic {
            var p: dynamic = absent
            var logs: dynamic = absent
            var date: dynamic = absent
            var category: dynamic = absent
            var status: dynamic = absent
            var kind: dynamic = absent
            val unpacked97: dynamic = jsOr(ctx) { js("({})") }
            p = unpacked97["profile"]
            logs = definedOr(unpacked97["logs"]) { js("({})") }
            date = unpacked97["date"]
            category = unpacked97["category"]
            status = unpacked97["status"]
            kind = unpacked97["kind"]
            val records: dynamic = resolve(logs, date, category)
            if (truth((!truth(global_Array.isArray(items))))) {
                return jsObject { obj98 ->
                    obj98["items"] = js("[]")
                    obj98["progress"] = records
                }
            }
            val `out`: dynamic = items.map((fun(x: dynamic): dynamic {
                return jsObject { obj99 ->
                    copyFields(obj99, x)
                }
            }))
            val blocked: dynamic = jsOr(jsOr(jsOr((!truth((!truth(onPresent(status) { value100 -> value100.reduced }))))) { riskyProfile(p) }) { globalSymptoms(logs, date) }) { jsAnd(jsMore(global_Number(onPresent(p) { value101 -> value101.trainingScale }), 0)) { jsLess(global_Number(p.trainingScale), 1) } }
            val slots: dynamic = `out`.map((fun(x: dynamic, i: dynamic): dynamic {
                return jsObject { obj103 ->
                    obj103["x"] = x
                    obj103["i"] = i
                    obj103["e"] = E.byId(x.id)
                }
            })).filter((fun(arg104: dynamic): dynamic {
                var x: dynamic = absent
                var e: dynamic = absent
                val unpacked105: dynamic = arg104
                x = unpacked105["x"]
                e = unpacked105["e"]
                return allowedItem(e, x, category)
            }))
            val maximum: dynamic = (if (truth(jsLess(global_Number(onPresent(p) { value106 -> value106.age }), 18))) 1 else 4)
            val requested: dynamic = (if (truth(blocked)) global_Math.min(0, records.level) else limit(records.level, (-numeric(2)), maximum))
            var change: dynamic = "mantener"
            var affected: dynamic = 0
            if (truth(jsAnd(slots.length) { jsMore(requested, 0) })) {
                if (truth(jsOr(jsOr(jsAtMost(requested, 2)) { jsLess(global_Number(onPresent(p) { value107 -> value107.age }), 18) }) { (kind !== "strength") })) {
                    val quantity: dynamic = (if (truth((requested === 1))) 1 else global_Math.min(2, slots.length))
                    if (true) {
                        val iterator108: dynamic = iteratorOf(slots.slice(0, quantity))
                        while (true) {
                            val next109: dynamic = iterator108.next()
                            if (truth(next109.done)) break
                            var x: dynamic = absent
                            var e: dynamic = absent
                            val unpacked110: dynamic = next109.value
                            x = unpacked110["x"]
                            e = unpacked110["e"]
                            val increment: dynamic = (if (truth((e.unit === "sec"))) (if (truth((category === "mobility"))) 2 else 5) else 1)
                            val ceiling: dynamic = targetCap(e, x, p)
                            val next: dynamic = global_Math.min(ceiling, jsAdd(global_Number(x.target), increment))
                            if (truth(jsMore(next, global_Number(x.target)))) {
                                x.target = next
                                run {
                                    val previous113: dynamic = numeric(affected)
                                    affected = jsAdd(previous113, 1)
                                }
                            }
                        }
                    }
                    if (truth(affected)) {
                        change = "repeticiones_o_tiempo"
                    }
                } else {
                    val first: dynamic = slots.find((fun(arg114: dynamic): dynamic {
                        var x: dynamic = absent
                        val unpacked115: dynamic = arg114
                        x = unpacked115["x"]
                        return jsAnd(jsAtLeast(global_Number(x.sets), 1)) { jsLess(global_Number(x.sets), 3) }
                    }))
                    if (truth(jsAnd(first) { jsAtLeast(global_Number(onPresent(p) { value116 -> value116.minutes }), 30) })) {
                        run {
                            val target117: dynamic = first.x
                            val previous119: dynamic = numeric(target117.sets)
                            target117.sets = jsAdd(previous119, 1)
                        }
                        affected = 1
                        change = "una_serie"
                    } else {
                        var x: dynamic = absent
                        var e: dynamic = absent
                        val unpacked120: dynamic = slots[0]
                        x = unpacked120["x"]
                        e = unpacked120["e"]
                        val next: dynamic = global_Math.min(targetCap(e, x, p), jsAdd(global_Number(x.target), (if (truth((e.unit === "sec"))) 5 else 1)))
                        if (truth(jsMore(next, global_Number(x.target)))) {
                            x.target = next
                            affected = 1
                            change = "repeticiones_o_tiempo"
                        }
                    }
                }
            } else {
                if (truth(jsAnd(slots.length) { jsLess(requested, 0) })) {
                    if (true) {
                        val iterator123: dynamic = iteratorOf(slots.slice(0, (if (truth((requested === (-numeric(2))))) 2 else 1)))
                        while (true) {
                            val next124: dynamic = iterator123.next()
                            if (truth(next124.done)) break
                            var x: dynamic = absent
                            var e: dynamic = absent
                            val unpacked125: dynamic = next124.value
                            x = unpacked125["x"]
                            e = unpacked125["e"]
                            val delta: dynamic = (if (truth((e.unit === "sec"))) (if (truth((category === "mobility"))) 2 else 5) else 1)
                            val floor: dynamic = (if (truth((e.unit === "sec"))) global_Math.min(global_Number(x.target), (if (truth((category === "mobility"))) 8 else 15)) else global_Math.min(global_Number(x.target), 4))
                            val next: dynamic = global_Math.max(floor, jsSubtract(global_Number(x.target), delta))
                            if (truth(jsLess(next, global_Number(x.target)))) {
                                x.target = next
                                run {
                                    val previous128: dynamic = numeric(affected)
                                    affected = jsAdd(previous128, 1)
                                }
                            }
                        }
                    }
                    if (truth(affected)) {
                        change = "reduccion_suave"
                    }
                }
            }
            if (true) {
                val iterator129: dynamic = iteratorOf(`out`)
                while (true) {
                    val next130: dynamic = iterator129.next()
                    if (truth(next130.done)) break
                    var x: dynamic = absent
                    x = next130.value
                    x.progression51 = jsObject { obj133 ->
                        obj133["category"] = category
                        obj133["level"] = requested
                        obj133["action"] = change
                        obj133["lastScore"] = records.lastScore
                        obj133["lastDate"] = records.lastDate
                    }
                }
            }
            return jsObject { obj134 ->
                obj134["items"] = `out`
                obj134["progress"] = jsObject { obj135 ->
                    copyFields(obj135, records)
                    obj135["level"] = requested
                    obj135["action"] = change
                    obj135["affected"] = affected
                    obj135["blockedIncrease"] = blocked
                }
            }
        }
        if (truth(root.HunterCategoryProgress51)) {
            return absent
        }
        E = root.Engine
        val G: dynamic = root.HunterGroups49
        if (truth(jsOr((!truth(E))) { (!truth(G)) })) {
            throw global_Error("CategoryProgress51: faltan Engine / HunterGroups49")
        }
        CATS = global_Object.freeze(jsonValue("[\"upper\",\"lower\",\"core\",\"cardio\",\"mobility\"]"))
        LABELS = global_Object.freeze(jsonValue("{\"upper\":\"Tren superior\",\"lower\":\"Tren inferior\",\"core\":\"Zona media\",\"cardio\":\"Cardio\",\"mobility\":\"Movilidad\"}"))
        DAY = jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")
        limit = (fun(v: dynamic, lo: dynamic, hi: dynamic): dynamic {
            return global_Math.min(hi, global_Math.max(lo, v))
        })
        rpe = (fun(v: dynamic): dynamic {
            return (if (truth(jsAnd(jsAnd(global_Number.isInteger(global_Number(v))) { jsAtLeast(global_Number(v), 1) }) { jsAtMost(global_Number(v), 10) })) global_Number(v) else 0)
        })
        isoDays = (fun(a: dynamic, b: dynamic): dynamic {
            return global_Math.round(jsDivide(jsSubtract(global_Date.parse(jsAdd(a, "T12:00:00Z")), global_Date.parse(jsAdd(b, "T12:00:00Z"))), 86400000))
        })
        meta = (fun(id: dynamic): dynamic {
            return G.classify((if (truth((jsTypeOf(id) === "object"))) onPresent(id) { value3 -> value3.id } else id))
        })
        riskyProfile = (fun(p: dynamic): dynamic {
            return global_Boolean(jsOr(jsOr(jsOr(jsOr(jsOr(onPresent(p) { value4 -> value4.limits }) { onPresent(p) { value5 -> onPresent(value5.health) { value6 -> value6.special } } }) { onPresent(p) { value7 -> onPresent(value7.health) { value8 -> value8.acute } } }) { onPresent(p) { value9 -> onPresent(value9.health) { value10 -> value10.redFlags } } }) { jsAnd(onPresent(p) { value11 -> onPresent(value11.health) { value12 -> value12.pain } }) { (p.health.pain !== "none") } }) { dynamicValue(jsOr(onPresent(p) { value14 -> value14.injuries }) { js("[]") }).some((fun(i: dynamic): dynamic {
                return jsAnd(i) { (i.stage !== "past") }
            })) })
        })
        root.HunterCategoryProgress51 = global_Object.freeze(jsObject { obj138 ->
            obj138["version"] = "38.50"
            obj138["categories"] = CATS
            obj138["labels"] = LABELS
            obj138["categoryOfLog"] = categoryOfLog
            obj138["categoryOfItems"] = categoryOfItems
            obj138["categoryHistory"] = categoryHistory
            obj138["resolve"] = resolve
            obj138["globalSymptoms"] = globalSymptoms
            obj138["feedback"] = feedback
            obj138["adjustItems"] = adjustItems
        })
        return absent
    }))(global_window)
}
