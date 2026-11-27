@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installcoach

import hunter.browser.*

@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installCoach() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var review: dynamic = absent
        review = fun(c: dynamic, logs: dynamic, p: dynamic, now: dynamic, arg41: dynamic): dynamic {
            var feedback: dynamic = absent
            feedback = definedOr(arg41) { js("({})") }
            val week: dynamic = global_Math.min(3, global_Math.floor(jsDivide(E.diff(now, c.start), 7)))
            if (truth(jsLess(week, 1))) {
                return null
            }
            c.reviews = jsOr(c.reviews) { js("[]") }
            if (truth(c.reviews.some((fun(r: dynamic): dynamic {
                return (r.week === week)
            })))) {
                return null
            }
            val days: dynamic = c.days.slice(jsMultiply(jsSubtract(week, 1), 7), jsMultiply(week, 7)).filter((fun(d: dynamic): dynamic {
                return d.items.length
            }))
            val records: dynamic = days.map((fun(d: dynamic): dynamic {
                return logs[d.date]
            })).filter(global_Boolean)
            val completed: dynamic = records.filter((fun(l: dynamic): dynamic {
                return l.complete
            }))
            val effort: dynamic = completed.map((fun(l: dynamic): dynamic {
                return global_Number(l.rpe)
            })).filter((fun(n: dynamic): dynamic {
                return jsAnd(jsAtLeast(n, 1)) { jsAtMost(n, 10) }
            }))
            val adherence: dynamic = (if (truth(days.length)) jsDivide(completed.length, days.length) else 0)
            val mean: dynamic = (if (truth(effort.length)) jsDivide(effort.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b)
            }), 0), effort.length) else 0)
            val pain: dynamic = jsOr(records.some((fun(l: dynamic): dynamic {
                return l.pain
            }))) { feedback.pain }
            val mode: dynamic = global_Adapt.mode(p)
            var action: dynamic = "mantener"
            var reason: dynamic = "Faltan sesiones completadas y datos de esfuerzo suficientes."
            if (truth(jsOr(pain) { (!truth(mode.auto)) })) {
                action = "pausar"
                reason = "Dolor registrado o perfil pendiente de indicaciones: se pausa la asignación automática."
            } else {
                if (truth(jsOr(jsOr(feedback.fatigue) { records.some((fun(l: dynamic): dynamic {
                    return l.fatigue
                })) }) { jsAtLeast(mean, 8) })) {
                    action = "reducir"
                    reason = "Esfuerzo alto o fatiga: se reduce el volumen pendiente."
                } else {
                    if (truth(mode.reduced)) {
                        reason = "Se mantiene el volumen reducido por las restricciones del perfil."
                    } else {
                        if (truth(jsAnd(jsAnd(jsAnd(jsAnd(days.length) { jsAtLeast(adherence, 0.8) }) { (effort.length === completed.length) }) { jsMore(mean, 0) }) { jsAtMost(mean, 7) })) {
                            action = "aumentar"
                            reason = "Al menos 80 % de adherencia y esfuerzo registrado de 7/10 o menos: incremento pequeño."
                        }
                    }
                }
            }
            if (truth(jsAnd((action === "aumentar")) { root.Learning })) {
                val eligible: dynamic = c.days.filter((fun(d: dynamic): dynamic {
                    return jsAnd(jsAnd(jsAnd(jsAnd(jsAtLeast(d.date, now)) { (!truth(d.customized)) }) { (!truth(d.routineId)) }) { (!truth(onPresent(logs[d.date]) { value44 -> value44.complete })) }) { (!truth(onPresent(logs[d.date]) { value45 -> onPresent(value45.items) { value46 -> value46.some((fun(x: dynamic): dynamic {
                        return jsOr(jsOr(x.missionState) { x.selfReported }) { onPresent(x["actual"]) { value47 -> value47.some((fun(a: dynamic): dynamic {
                            return a.done
                        })) } }
                    })) } })) }
                }))
                val decision: dynamic = root.Learning.gate(logs, eligible, now)
                if (truth((!truth(decision.allow)))) {
                    action = "mantener"
                }
                reason = decision.reason
            }
            var changed: dynamic = 0
            c.days.filter((fun(d: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAtLeast(d.date, now)) { (!truth(d.customized)) }) { (!truth(d.routineId)) }) { (!truth(onPresent(d.tasks) { value53 -> value53.some((fun(t: dynamic): dynamic {
                    return t.done
                })) })) }) { (!truth(onPresent(logs[d.date]) { value54 -> value54.complete })) }) { (!truth(onPresent(logs[d.date]) { value55 -> onPresent(value55.items) { value56 -> value56.some((fun(x: dynamic): dynamic {
                    return jsOr(jsOr(x.missionState) { x.selfReported }) { onPresent(x["actual"]) { value57 -> value57.some((fun(a: dynamic): dynamic {
                        return a.done
                    })) } }
                })) } })) }
            })).forEach((fun(d: dynamic): dynamic {
                if (truth((action === "pausar"))) {
                    d.items = js("[]")
                    d.kind = "rest"
                    d.tasks = jsonValue("[{\"id\":\"coach-review\",\"text\":\"Revisar el dolor y las indicaciones antes de volver a entrenar\",\"done\":false}]")
                    run {
                        val previous64: dynamic = numeric(changed)
                        changed = jsAdd(previous64, 1)
                    }
                } else {
                    if (truth(dynamicValue(jsonValue("[\"aumentar\",\"reducir\"]")).includes(action))) {
                        d.items.forEach((fun(x: dynamic): dynamic {
                            val sec: dynamic = (E.unitOf(x) === "sec")
                            x.target = (if (truth((action === "aumentar"))) global_Math.min((if (truth(sec)) 60 else 15), jsAdd(x.target, (if (truth(sec)) 5 else 1))) else global_Math.max((if (truth(sec)) 10 else 4), global_Math.floor(jsMultiply(x.target, 0.8))))
                            return absent
                        }))
                        if (truth(d.items.length)) {
                            run {
                                val previous67: dynamic = numeric(changed)
                                changed = jsAdd(previous67, 1)
                            }
                        }
                    }
                }
                if (truth(jsAnd((action !== "mantener")) { (!truth(onPresent(logs[d.date]) { value68 -> value68.complete })) })) {
                    removeKey(logs, d.date)
                }
                return absent
            }))
            val r: dynamic = jsObject { obj69 ->
                obj69["week"] = week
                obj69["date"] = now
                obj69["action"] = action
                obj69["reason"] = reason
                obj69["adherence"] = adherence
                obj69["effort"] = mean
                obj69["changed"] = changed
            }
            c.reviews.push(r)
            return r
        }
        E = root.Engine
        val old: dynamic = E.makeCycle
        val levels: dynamic = jsonValue("{\"basic\":1,\"intermediate\":1,\"advanced\":2}")
        val oldReasons: dynamic = global_Adapt.reasons
        global_Adapt.reasons = (fun(e: dynamic, p: dynamic, arg7: dynamic): dynamic {
            var strict: dynamic = absent
            strict = definedOr(arg7) { true }
            val r: dynamic = oldReasons(e, (if (truth(onPresent(p) { value8 -> value8.difficulty })) jsObject { obj9 ->
                copyFields(obj9, p)
                obj9["experience"] = (if (truth((p.difficulty === "advanced"))) "regular" else "beginner")
            } else p), strict)
            if (truth(jsAnd(jsAnd(onPresent(p) { value10 -> value10.difficulty }) { jsMore(e.level, levels[p.difficulty]) }) { (!truth(r.includes("Nivel superior al seleccionado"))) })) {
                r.push("Nivel superior al seleccionado")
            }
            return r
        })
        global_Adapt.allowed = (fun(e: dynamic, p: dynamic): dynamic {
            return (!truth(global_Adapt.reasons(e, p).length))
        })
        global_Adapt.routineCompatible = (fun(r: dynamic, p: dynamic): dynamic {
            return jsAnd(global_Adapt.mode(p).auto) { r.items.every((fun(x: dynamic): dynamic {
                return global_Adapt.allowed(E.byId(x.id), p)
            })) }
        })
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, previous: dynamic, logs: dynamic): dynamic {
            val c: dynamic = old(p, start, index, null, logs)
            if (truth((!truth(p.difficulty)))) {
                return c
            }
            val reduced: dynamic = global_Adapt.mode(p).reduced
            val carry: dynamic = onPresent(previous) { value19 -> onPresent(value19.days) { value20 -> onPresent(value20.filter((fun(d: dynamic): dynamic {
                return (d.kind === "strength")
            })).at((-numeric(1)))) { value22 -> onPresent(value22.items) { value23 -> onPresent(value23[0]) { value24 -> value24.target } } } } }
            c.days.forEach((fun(d: dynamic): dynamic {
                d.items = d.items.filter((fun(x: dynamic): dynamic {
                    return global_Adapt.allowed(E.byId(x.id), p)
                }))
                d.items.forEach((fun(x: dynamic): dynamic {
                    if (truth(jsAnd((!truth(reduced))) { (d.kind === "strength") })) {
                        x.sets = (if (truth((p.difficulty === "basic"))) 2 else (if (truth(jsAtLeast(p.minutes, 30))) 3 else 2))
                        x.target = (if (truth(global_Number.isFinite(carry))) global_Math.min(15, carry) else (if (truth((p.goal === "muscle"))) 10 else (if (truth((p.difficulty === "advanced"))) 10 else 8)))
                    }
                    return absent
                }))
                if (truth(jsAnd((!truth(d.items.length))) { (!truth(d.tasks.length)) })) {
                    d.kind = "rest"
                    d.tasks = jsonValue("[{\"id\":\"alternatives\",\"text\":\"Revisar las alternativas compatibles de mi catálogo\",\"done\":false}]")
                }
                return absent
            }))
            c.coachVersion = 4
            c.decision = jsAdd(global_Adapt.mode(p).reason, " Revisión semanal local; sin aumentos cuando faltan datos de esfuerzo.")
            return c
        })
        root.Coach = jsObject { obj72 ->
            obj72["levels"] = levels
            obj72["review"] = review
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
