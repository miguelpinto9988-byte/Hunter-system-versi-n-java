@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installadaptivev9

import hunter.browser.*

@JsName("Object") private external var global_Object: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Coach") private external var global_Coach: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Learning") private external var global_Learning: dynamic
@JsName("AdaptiveV9") private external var global_AdaptiveV9: dynamic
@JsName("Engine") private external var global_Engine: dynamic

internal fun installAdaptiveV9() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var evidence: dynamic = absent
        var recommend: dynamic = absent
        evidence = fun(logs: dynamic, id: dynamic, now: dynamic): dynamic {
            val base: dynamic = jsOr(jsOr(onPresent(E.byId(id)) { value5 -> value5.family }) { onPresent(E.byId(id)) { value6 -> value6.baseId } }) { id }
            return global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg30: dynamic): dynamic {
                var date: dynamic = absent
                var l: dynamic = absent
                val unpacked31: dynamic = fromIterable(arg30)
                date = unpacked31[0]
                l = unpacked31[1]
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsLess(date, now)) { l.complete }) { (!truth(l.pain)) }) { (!truth(l.fatigue)) }) { jsAtLeast(l.rpe, 1) }) { jsAtMost(l.rpe, 10) }
            })).flatMap((fun(arg32: dynamic): dynamic {
                var date: dynamic = absent
                var l: dynamic = absent
                val unpacked33: dynamic = fromIterable(arg32)
                date = unpacked33[0]
                l = unpacked33[1]
                return dynamicValue(jsOr(l.items) { js("[]") }).filter((fun(x: dynamic): dynamic {
                    return (x.id === id)
                })).map((fun(x: dynamic): dynamic {
                    return jsObject { obj34 ->
                        obj34["date"] = date
                        obj34["rpe"] = global_Number(l.rpe)
                        obj34["item"] = x
                        obj34["base"] = base
                    }
                }))
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return b.date.localeCompare(a.date)
            })).slice(0, 12)
        }
        recommend = fun(logs: dynamic, item: dynamic, p: dynamic, now: dynamic): dynamic {
            val rows: dynamic = evidence(logs, item.id, now).filter((fun(r: dynamic): dynamic {
                return jsAnd((E.unitOf(r.item) === E.unitOf(item))) { (jsOr(r.item.side) { "both" } === jsOr(item.side) { "both" }) }
            }))
            val mode: dynamic = global_Adapt.mode(p)
            val e: dynamic = E.byId(item.id)
            var target: dynamic = item.target
            var reason: dynamic = "Registra al menos tres sesiones de este ejercicio con esfuerzo y series completas."
            var action: dynamic = "mantener"
            val recent: dynamic = rows.slice(0, 3)
            val full: dynamic = jsAnd((recent.length === 3)) { recent.every((fun(r: dynamic): dynamic {
                return jsAnd(jsAtLeast(onPresent(r.item["actual"]) { value37 -> value37.length }, global_Math.max(r.item.sets, item.sets))) { r.item["actual"].every((fun(a: dynamic): dynamic {
                    return jsAnd(a.done) { jsAtLeast(global_Number(a["value"]), global_Math.max(r.item.target, item.target)) }
                })) }
            })) }
            if (truth(jsOr((!truth(mode.auto))) { mode.reduced })) {
                return jsObject { obj38 ->
                    obj38["target"] = target
                    obj38["action"] = action
                    obj38["reason"] = "Se conserva la dosis por las restricciones del perfil."
                    obj38["samples"] = rows.length
                }
            }
            if (truth(jsAnd(full) { recent.every((fun(r: dynamic): dynamic {
                return jsAtMost(r.rpe, 7)
            })) })) {
                val cap: dynamic = (if (truth((E.unitOf(item) === "sec"))) 90 else (if (truth((p.finalTarget === "endurance"))) 20 else 15))
                val delta: dynamic = (if (truth((E.unitOf(item) === "sec"))) global_Math.max(1, global_Math.min(5, global_Math.floor(jsMultiply(target, 0.1)))) else 1)
                target = global_Math.min(cap, jsAdd(target, delta))
                action = (if (truth(jsMore(target, item.target))) "aumentar" else "mantener")
                reason = (if (truth((action === "aumentar"))) "Tres sesiones completas con esfuerzo de 7/10 o menos: progresión de una sola variable." else "Tope de repeticiones alcanzado. Revisa manualmente una carga que puedas controlar; no se aumenta el peso automáticamente.")
            }
            return jsObject { obj39 ->
                obj39["target"] = target
                obj39["action"] = action
                obj39["reason"] = reason
                obj39["samples"] = rows.length
            }
        }
        E = root.Engine
        val previous: dynamic = global_Coach.review
        global_Coach.review = (fun(c: dynamic, logs: dynamic, p: dynamic, now: dynamic, arg44: dynamic): dynamic {
            var feedback: dynamic = absent
            feedback = definedOr(arg44) { js("({})") }
            val week: dynamic = global_Math.min(3, global_Math.floor(jsDivide(E.diff(now, c.start), 7)))
            if (truth(jsOr(jsLess(week, 1)) { onPresent(c.reviews) { value45 -> value45.some((fun(r: dynamic): dynamic {
                return (r.week === week)
            })) } })) {
                return null
            }
            val weekDays: dynamic = c.days.slice(jsMultiply(jsSubtract(week, 1), 7), jsMultiply(week, 7)).filter((fun(d: dynamic): dynamic {
                return d.items.length
            }))
            val records: dynamic = weekDays.map((fun(d: dynamic): dynamic {
                return logs[d.date]
            })).filter(global_Boolean)
            if (truth(jsOr(jsOr(jsOr(jsOr(feedback.pain) { feedback.fatigue }) { records.some((fun(l: dynamic): dynamic {
                return jsOr(jsOr(l.pain) { l.fatigue }) { jsAtLeast(global_Number(l.rpe), 8) }
            })) }) { (!truth(global_Adapt.mode(p).auto)) }) { global_Adapt.mode(p).reduced })) {
                return previous(c, logs, p, now, feedback)
            }
            var changed: dynamic = 0
            val details: dynamic = js("[]")
            val complete: dynamic = records.filter((fun(l: dynamic): dynamic {
                return l.complete
            })).length
            val adherence: dynamic = (if (truth(weekDays.length)) jsDivide(complete, weekDays.length) else 0)
            if (truth(jsAtLeast(adherence, 0.8))) {
                c.days.filter((fun(d: dynamic): dynamic {
                    return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAtLeast(d.date, now)) { (!truth(d.customized)) }) { (!truth(d.routineId)) }) { (!truth(onPresent(d.tasks) { value51 -> value51.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) })) }) { (!truth(onPresent(logs[d.date]) { value52 -> value52.complete })) }) { (!truth(onPresent(logs[d.date]) { value53 -> onPresent(value53.items) { value54 -> value54.some((fun(x: dynamic): dynamic {
                        return jsOr(jsOr(x.missionState) { x.selfReported }) { onPresent(x["actual"]) { value55 -> value55.some((fun(a: dynamic): dynamic {
                            return a.done
                        })) } }
                    })) } })) }
                })).forEach((fun(d: dynamic): dynamic {
                    var modified: dynamic = false
                    d.items.forEach((fun(x: dynamic): dynamic {
                        val result: dynamic = recommend(logs, x, p, now)
                        details.push(jsObject { obj56 ->
                            obj56["id"] = x.id
                            copyFields(obj56, result)
                        })
                        if (truth(jsMore(result.target, x.target))) {
                            x.target = result.target
                            modified = true
                        }
                        return absent
                    }))
                    if (truth(modified)) {
                        run {
                            val previous59: dynamic = numeric(changed)
                            changed = jsAdd(previous59, 1)
                        }
                        removeKey(logs, d.date)
                    }
                    return absent
                }))
            }
            val r: dynamic = jsObject { obj60 ->
                obj60["week"] = week
                obj60["date"] = now
                obj60["action"] = (if (truth(changed)) "aumentar" else "mantener")
                obj60["reason"] = (if (truth(changed)) "Progresión por ejercicio con tres registros completos: +1 repetición o hasta 10 % de tiempo; conserva series, peso y descansos." else "Se mantiene el plan: faltan tres registros completos por ejercicio, adherencia suficiente o margen de progresión.")
                obj60["adherence"] = adherence
                obj60["changed"] = changed
                obj60["details"] = details
            }
            dynamicValue(jsOr(c.reviews) { run {
                val target64: dynamic = c
                val assigned66: dynamic = js("[]")
                target64.reviews = assigned66
                assigned66
            } }).push(r)
            return r
        })
        val make: dynamic = E.makeCycle
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, logs: dynamic): dynamic {
            val c: dynamic = make(p, start, index, last, logs)
            if (truth(jsAnd(jsAnd(jsAnd(last) { (!truth(onPresent(global_GoalPlans.plans[p.finalTarget]) { value71 -> value71.gentle })) }) { (!truth(global_Adapt.mode(p).reduced)) }) { global_Adapt.mode(p).auto })) {
                c.days.forEach((fun(d: dynamic): dynamic {
                    return d.items.forEach((fun(x: dynamic): dynamic {
                        val matches: dynamic = last.days.flatMap((fun(y: dynamic): dynamic {
                            return y.items
                        })).filter((fun(y: dynamic): dynamic {
                            return jsAnd(jsAnd((y.id === x.id)) { (E.unitOf(y) === E.unitOf(x)) }) { (jsOr(y.side) { "both" } === jsOr(x.side) { "both" }) }
                        }))
                        if (truth(matches.length)) {
                            x.target = global_Math.min((if (truth((E.unitOf(x) === "sec"))) 90 else 15), matches.at((-numeric(1))).target)
                        } else {
                            if (truth((E.unitOf(x) !== "sec"))) {
                                x.target = (if (truth((p.goal === "muscle"))) 10 else 8)
                            }
                        }
                        return absent
                    }))
                }))
            }
            return c
        })
        root.AdaptiveV9 = jsObject { obj78 ->
            obj78["evidence"] = evidence
            obj78["recommend"] = recommend
        }
        return absent
    }))(global_window)
    dynamicValue((fun(root: dynamic): dynamic {
        var predictExercise: dynamic = absent
        predictExercise = fun(logs: dynamic, item: dynamic, now: dynamic): dynamic {
            val rows: dynamic = global_AdaptiveV9.evidence(logs, item.id, now).filter((fun(r: dynamic): dynamic {
                return (global_Engine.unitOf(r.item) === global_Engine.unitOf(item))
            }))
            if (truth(jsLess(rows.length, 3))) {
                return null
            }
            val vector: dynamic = (fun(x: dynamic): dynamic {
                val `actual`: dynamic = dynamicValue(jsOr(x["actual"]) { js("[]") }).filter((fun(a: dynamic): dynamic {
                    return a.done
                }))
                val kg: dynamic = (if (truth(`actual`.length)) jsDivide(`actual`.reduce((fun(n: dynamic, a: dynamic): dynamic {
                    return jsAdd(n, jsOr(global_Number(a.kg)) { 0 })
                }), 0), `actual`.length) else 0)
                return arrayOf<dynamic>(jsDivide(global_Number(x.target), 20), jsDivide(global_Number(x.sets), 4), jsDivide((if (truth(global_Number.isFinite(global_Number(x.rest)))) global_Number(x.rest) else 90), 120), jsDivide(kg, 20))
            })
            val q: dynamic = vector(item)
            val near: dynamic = rows.map((fun(r: dynamic): dynamic {
                return jsObject { obj92 ->
                    obj92["r"] = r
                    obj92["d"] = run { val target94: dynamic = global_Math; invokeWith(target94.hypot, target94, jsArray { array93 ->
                        appendAll(array93, vector(r.item).map((fun(v: dynamic, i: dynamic): dynamic {
                            return jsSubtract(v, q[i])
                        })))
                    }) }
                }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract(a.d, b.d)
            })).slice(0, 5)
            if (truth(jsMore(near[0].d, 0.8))) {
                return null
            }
            var sum: dynamic = 0
            var weight: dynamic = 0
            if (true) {
                val iterator95: dynamic = iteratorOf(near)
                while (true) {
                    val next96: dynamic = iterator95.next()
                    if (truth(next96.done)) break
                    var n: dynamic = absent
                    n = next96.value
                    val w: dynamic = jsDivide(1, jsAdd(n.d, 0.1))
                    run {
                        sum = jsAdd(sum, jsMultiply(n.r.rpe, w))
                    }
                    run {
                        weight = jsAdd(weight, w)
                    }
                }
            }
            return jsObject { obj97 ->
                obj97["effort"] = jsDivide(global_Math.round(jsMultiply(jsDivide(sum, weight), 10)), 10)
                obj97["neighbors"] = near.length
                obj97["sessions"] = rows.length
            }
        }
        val oldPredict: dynamic = global_Learning.predict
        global_AdaptiveV9.predictExercise = predictExercise
        global_Learning.predict = (fun(`data`: dynamic, items: dynamic, arg104: dynamic): dynamic {
            var bump: dynamic = absent
            bump = definedOr(arg104) { false }
            val exact: dynamic = oldPredict(`data`, items, bump)
            if (truth(exact)) {
                return exact
            }
            val logs: dynamic = js("({})")
            if (true) {
                val iterator105: dynamic = iteratorOf(`data`)
                while (true) {
                    val next106: dynamic = iterator105.next()
                    if (truth(next106.done)) break
                    var r: dynamic = absent
                    r = next106.value
                    if (truth(r.items)) {
                        logs[r.date] = jsObject { obj109 ->
                            obj109["complete"] = true
                            obj109["rpe"] = r.y
                            obj109["items"] = r.items
                        }
                    }
                }
            }
            val estimates: dynamic = items.map((fun(x: dynamic): dynamic {
                return predictExercise(logs, jsObject { obj110 ->
                    copyFields(obj110, x)
                    obj110["target"] = jsAdd(x.target, (if (truth(bump)) (if (truth((global_Engine.unitOf(x) === "sec"))) global_Math.max(1, global_Math.floor(jsMultiply(x.target, 0.1))) else 1) else 0))
                }, "9999-12-31")
            }))
            if (truth(jsOr((!truth(estimates.length))) { estimates.some((fun(x: dynamic): dynamic {
                return (!truth(x))
            })) })) {
                return null
            }
            return jsObject { obj111 ->
                obj111["effort"] = run { val target113: dynamic = global_Math; invokeWith(target113.max, target113, jsArray { array112 ->
                    appendAll(array112, estimates.map((fun(x: dynamic): dynamic {
                        return x.effort
                    })))
                }) }
                obj111["neighbors"] = run { val target115: dynamic = global_Math; invokeWith(target115.min, target115, jsArray { array114 ->
                    appendAll(array114, estimates.map((fun(x: dynamic): dynamic {
                        return x.neighbors
                    })))
                }) }
                obj111["sessions"] = run { val target117: dynamic = global_Math; invokeWith(target117.min, target117, jsArray { array116 ->
                    appendAll(array116, estimates.map((fun(x: dynamic): dynamic {
                        return x.sessions
                    })))
                }) }
                obj111["method"] = "por ejercicio"
            }
        })
        val samples: dynamic = global_Learning.samples
        global_Learning.samples = (fun(logs: dynamic, now: dynamic): dynamic {
            return samples(logs, now).map((fun(row: dynamic): dynamic {
                return jsObject { obj122 ->
                    copyFields(obj122, row)
                    obj122["items"] = logs[row.date].items
                }
            }))
        })
        return absent
    }))(global_window)
}
