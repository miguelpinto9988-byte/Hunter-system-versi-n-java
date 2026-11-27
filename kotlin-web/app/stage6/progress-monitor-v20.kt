@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installprogressmonitorv20

import hunter.browser.*

@JsName("AdaptiveV9") private external var global_AdaptiveV9: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("TrainingProgression19") private external var global_TrainingProgression19: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installProgressMonitorV20() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var completeItem: dynamic = absent
        var load: dynamic = absent
        var features: dynamic = absent
        var estimate: dynamic = absent
        var summary: dynamic = absent
        completeItem = fun(x: dynamic): dynamic {
            return jsAnd(jsAnd(jsAnd(jsAnd(global_Number.isInteger(onPresent(x) { value5 -> value5.sets })) { jsMore(x.sets, 0) }) { jsMore(global_Number(x.target), 0) }) { (onPresent(x["actual"]) { value6 -> value6.length } === x.sets) }) { x["actual"].every((fun(a: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(a.done) { global_Number.isFinite(global_Number(a["value"])) }) { jsAtLeast(global_Number(a["value"]), x.target) }) { global_Number.isFinite(global_Number(jsOr(a.kg) { 0 })) }) { jsAtLeast(global_Number(jsOr(a.kg) { 0 }), 0) }
            })) }
        }
        load = fun(x: dynamic): dynamic {
            val a: dynamic = dynamicValue(jsOr(x["actual"]) { js("[]") }).filter((fun(a: dynamic): dynamic {
                return a.done
            }))
            return (if (truth(a.length)) jsDivide(a.reduce((fun(n: dynamic, a: dynamic): dynamic {
                return jsAdd(n, global_Number(jsOr(a.kg) { 0 }))
            }), 0), a.length) else global_Number(jsOr(x.kg) { 0 }))
        }
        features = fun(x: dynamic, kg: dynamic): dynamic {
            return arrayOf<dynamic>(jsDivide(global_Number(x.target), (if (truth((E.unitOf(x) === "sec"))) 60 else 20)), jsDivide(global_Number(x.sets), 5), jsDivide(global_Number(jsOr(x.rest) { 30 }), 90), jsDivide(kg, 30))
        }
        estimate = fun(logs: dynamic, item: dynamic, now: dynamic): dynamic {
            val rows: dynamic = global_AdaptiveV9.evidence(logs, item.id, now).filter((fun(r: dynamic): dynamic {
                return jsAnd((E.unitOf(r.item) === E.unitOf(item))) { (jsOr(r.item.side) { "both" } === jsOr(item.side) { "both" }) }
            }))
            if (truth(jsLess(rows.length, 3))) {
                return null
            }
            val kg: dynamic = (if (truth(jsOr(onPresent(item["actual"]) { value17 -> value17.some((fun(a: dynamic): dynamic {
                return a.done
            })) }) { (item.kg !== absent) })) load(item) else load(rows[0].item))
            val q: dynamic = features(item, kg)
            val neighbors: dynamic = rows.map((fun(r: dynamic): dynamic {
                return jsObject { obj39 ->
                    copyFields(obj39, r)
                    obj39["d"] = run { val target41: dynamic = global_Math; invokeWith(target41.hypot, target41, jsArray { array40 ->
                        appendAll(array40, features(r.item, load(r.item)).map((fun(v: dynamic, i: dynamic): dynamic {
                            return jsSubtract(v, q[i])
                        })))
                    }) }
                }
            })).filter((fun(r: dynamic): dynamic {
                return jsAtMost(r.d, 0.75)
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract(a.d, b.d)
            })).slice(0, 5)
            if (truth(jsLess(neighbors.length, 3))) {
                return null
            }
            var sum: dynamic = 0
            var weight: dynamic = 0
            if (true) {
                val iterator42: dynamic = iteratorOf(neighbors)
                while (true) {
                    val next43: dynamic = iterator42.next()
                    if (truth(next43.done)) break
                    var row: dynamic = absent
                    row = next43.value
                    val w: dynamic = jsDivide(1, jsAdd(0.1, row.d))
                    run {
                        sum = jsAdd(sum, jsMultiply(row.rpe, w))
                    }
                    run {
                        weight = jsAdd(weight, w)
                    }
                }
            }
            return jsObject { obj44 ->
                obj44["effort"] = jsDivide(global_Math.round(jsMultiply(jsDivide(sum, weight), 10)), 10)
                obj44["neighbors"] = neighbors.length
                obj44["sessions"] = rows.length
                obj44["dates"] = neighbors.map((fun(r: dynamic): dynamic {
                    return r.date
                }))
                obj44["method"] = "kNN local · series confirmadas"
                obj44["loadKg"] = jsDivide(global_Math.round(jsMultiply(kg, 10)), 10)
            }
        }
        summary = fun(logs: dynamic, days: dynamic, p: dynamic, now: dynamic): dynamic {
            val completed: dynamic = global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg47: dynamic): dynamic {
                var date: dynamic = absent
                var l: dynamic = absent
                val unpacked48: dynamic = fromIterable(arg47)
                date = unpacked48[0]
                l = unpacked48[1]
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAtMost(date, now)) { l.complete }) { (!truth(l.pain)) }) { jsAtLeast(l.rpe, 1) }) { jsAtMost(l.rpe, 10) }) { onPresent(l.items) { value49 -> value49.length } }) { l.items.every(completeItem) }
            }))
            val pending: dynamic = dynamicValue(jsOr(days) { js("[]") }).filter((fun(d: dynamic): dynamic {
                return jsAnd(jsAtLeast(d.date, now)) { onPresent(d.items) { value51 -> value51.length } }
            })).flatMap((fun(d: dynamic): dynamic {
                return d.items
            }))
            val unique: dynamic = jsArray { array52 ->
                appendAll(array52, jsConstruct(global_Map, arrayOf<dynamic>(pending.map((fun(x: dynamic): dynamic {
                    return arrayOf<dynamic>(jsAdd(jsAdd(x.id, ":"), jsOr(x.side) { "both" }), x)
                })))).values())
            }
            val analysis: dynamic = unique.map((fun(item: dynamic): dynamic {
                val r: dynamic = global_TrainingProgression19.recommend(logs, item, p, now)
                return jsObject { obj53 ->
                    obj53["id"] = item.id
                    obj53["name"] = onPresent(E.byId(item.id)) { value54 -> value54.name }
                    obj53["sets"] = item.sets
                    obj53["target"] = item.target
                    obj53["unit"] = E.unitOf(item)
                    obj53["decision"] = r.action
                    obj53["reason"] = r.reason
                    obj53["nextId"] = r.id
                    obj53["estimate"] = jsOr(r.estimate20) { estimate(logs, item, now) }
                    obj53["records"] = r.samples
                }
            }))
            return jsObject { obj55 ->
                obj55["date"] = now
                obj55["completedSessions"] = completed.length
                obj55["confirmedSeries"] = completed.reduce((fun(n: dynamic, arg56: dynamic): dynamic {
                    var l: dynamic = absent
                    val unpacked57: dynamic = fromIterable(arg56)
                    l = unpacked57[1]
                    return jsAdd(n, l.items.reduce((fun(s: dynamic, x: dynamic): dynamic {
                        return jsAdd(s, x["actual"].length)
                    }), 0))
                }), 0)
                obj55["objective"] = jsOr(onPresent(global_GoalGroups14.groups[global_GoalGroups14.of(p)]) { value58 -> value58.name }) { p.finalTarget }
                obj55["analysis"] = analysis
            }
        }
        E = root.Engine
        val originalEvidence: dynamic = global_AdaptiveV9.evidence
        global_AdaptiveV9.evidence = (fun(logs: dynamic, id: dynamic, now: dynamic): dynamic {
            val seen: dynamic = jsConstruct(global_Set, js("[]"))
            return originalEvidence(logs, id, now).filter((fun(row: dynamic): dynamic {
                if (truth(jsOr(seen.has(row.date)) { (!truth(completeItem(row.item))) })) {
                    return false
                }
                seen.add(row.date)
                return true
            }))
        })
        root.ProgressMonitor20 = jsObject { obj61 ->
            obj61["completeItem"] = completeItem
            obj61["estimate"] = estimate
            obj61["summary"] = summary
            obj61["load"] = load
            obj61["features"] = features
        }
        return absent
    }))(global_window)
}
