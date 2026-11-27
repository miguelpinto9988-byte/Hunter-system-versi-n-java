@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installweekly

import hunter.browser.*

@JsName("Date") private external var global_Date: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("AdaptiveV9") private external var global_AdaptiveV9: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installWeekly() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var startOfWeek: dynamic = absent
        var active: dynamic = absent
        var month: dynamic = absent
        var migrate: dynamic = absent
        var review: dynamic = absent
        var current: dynamic = absent
        var dailyXP: dynamic = absent
        var progress: dynamic = absent
        month = fun(year: dynamic, number: dynamic): dynamic {
            val first: dynamic = jsConstruct(global_Date, arrayOf<dynamic>(year, number, 1, 12))
            val count: dynamic = jsConstruct(global_Date, arrayOf<dynamic>(year, jsAdd(number, 1), 0, 12)).getDate()
            return jsObject { obj7 ->
                obj7["year"] = year
                obj7["number"] = number
                obj7["count"] = count
                obj7["offset"] = jsMod(jsAdd(first.getDay(), 6), 7)
                obj7["dates"] = global_Array.from(jsObject { obj8 ->
                    obj8["length"] = count
                }, (fun(_: dynamic, i: dynamic): dynamic {
                    return E.dateKey(jsConstruct(global_Date, arrayOf<dynamic>(year, number, jsAdd(i, 1), 12)))
                }))
            }
        }
        migrate = fun(s: dynamic, today: dynamic): dynamic {
            if (truth(jsAnd((s.weeklyVersion === 10)) { s.cycles.every((fun(c: dynamic): dynamic {
                return (c.days.length === 7)
            })) })) {
                return false
            }
            val groups: dynamic = jsConstruct(global_Map, js("[]"))
            if (true) {
                val iterator29: dynamic = iteratorOf(s.cycles)
                while (true) {
                    val next30: dynamic = iterator29.next()
                    if (truth(next30.done)) break
                    var c: dynamic = absent
                    c = next30.value
                    if (true) {
                        val iterator31: dynamic = iteratorOf(c.days)
                        while (true) {
                            val next32: dynamic = iterator31.next()
                            if (truth(next32.done)) break
                            var d: dynamic = absent
                            d = next32.value
                            val start: dynamic = startOfWeek(d.date)
                            var w: dynamic = groups["get"](start)
                            if (truth((!truth(w)))) {
                                w = jsObject { obj33 ->
                                    copyFields(obj33, c)
                                    obj33["start"] = start
                                    obj33["days"] = js("[]")
                                    obj33["reviews"] = js("[]")
                                    obj33["legacyReviews"] = jsOr(c.reviews) { js("[]") }
                                    obj33["periodDays"] = 7
                                    obj33["weeklyVersion"] = 10
                                    obj33["activated"] = jsLess(start, startOfWeek(today))
                                    obj33["activeFrom"] = jsOr(c.activeFrom) { c.start }
                                }
                                groups["set"](start, w)
                            }
                            val old: dynamic = w.days.findIndex((fun(x: dynamic): dynamic {
                                return (x.date === d.date)
                            }))
                            if (truth(jsLess(old, 0))) {
                                w.days.push(d)
                            } else {
                                if (truth(jsOr(jsOr(active(s.logs[d.date])) { d.customized }) { d.routineId })) {
                                    w.days[old] = d
                                }
                            }
                        }
                    }
                }
            }
            s.cycles = dynamicValue(jsArray { array41 ->
                appendAll(array41, groups.values())
            }).sort((fun(a: dynamic, b: dynamic): dynamic {
                return a.start.localeCompare(b.start)
            })).map((fun(w: dynamic, i: dynamic): dynamic {
                return jsObject { obj42 ->
                    copyFields(obj42, w)
                    obj42["index"] = i
                    obj42["days"] = global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, n: dynamic): dynamic {
                        return jsOr(w.days.find((fun(d: dynamic): dynamic {
                            return (d.date === E["plus"](w.start, n))
                        }))) { jsObject { obj43 ->
                            obj43["date"] = E["plus"](w.start, n)
                            obj43["kind"] = "rest"
                            obj43["items"] = js("[]")
                            obj43["tasks"] = js("[]")
                            obj43["title"] = "Fuera del plan anterior"
                        } }
                    }))
                }
            }))
            s.weeklyVersion = 10
            return true
        }
        review = fun(last: dynamic, next: dynamic, logs: dynamic, p: dynamic, now: dynamic, arg48: dynamic): dynamic {
            var feedback: dynamic = absent
            feedback = definedOr(arg48) { js("({})") }
            if (truth(jsOr((!truth(last))) { (last.start !== E["plus"](next.start, (-numeric(7)))) })) {
                return null
            }
            val prior: dynamic = last.days.filter((fun(d: dynamic): dynamic {
                return jsAnd(jsAtLeast(d.date, jsOr(last.activeFrom) { last.start })) { d.items.length }
            }))
            val records: dynamic = prior.map((fun(d: dynamic): dynamic {
                return logs[d.date]
            })).filter(global_Boolean)
            val done: dynamic = records.filter((fun(l: dynamic): dynamic {
                return jsAnd(l.complete) { (!truth(l.pain)) }
            }))
            val adherence: dynamic = (if (truth(prior.length)) jsDivide(done.length, prior.length) else 0)
            val efforts: dynamic = done.map((fun(l: dynamic): dynamic {
                return l.rpe
            })).filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAtLeast(x, 1)) { jsAtMost(x, 10) }
            }))
            val effort: dynamic = (if (truth(efforts.length)) jsDivide(efforts.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b)
            }), 0), efforts.length) else 0)
            val mode: dynamic = global_Adapt.mode(p)
            val personalReduced: dynamic = jsLess(global_TrainingIntensity.scale(p), 1)
            var action: dynamic = "mantener"
            var reason: dynamic = "Semana nueva: mantén la dosis mientras reúnes registros comparables."
            var changed: dynamic = 0
            val details: dynamic = js("[]")
            val pain: dynamic = jsOr(feedback.pain) { records.some((fun(l: dynamic): dynamic {
                return l.pain
            })) }
            val fatigue: dynamic = jsOr(feedback.fatigue) { records.some((fun(l: dynamic): dynamic {
                return jsOr(l.fatigue) { jsAtLeast(l.rpe, 8) }
            })) }
            if (truth(jsOr(pain) { (!truth(mode.auto)) })) {
                action = "pausar"
                reason = "Dolor registrado o indicaciones pendientes: revisa tu estado antes de volver a entrenar."
            } else {
                if (truth(fatigue)) {
                    action = "reducir"
                    reason = "Esfuerzo alto o fatiga: reduce la dosis de la siguiente semana."
                } else {
                    if (truth(mode.reduced)) {
                        reason = "Semana adaptada: mantén la dosis reducida y las restricciones registradas."
                    } else {
                        if (truth(personalReduced)) {
                            reason = "Mantén la reducción personal de carga. Puedes cambiarla desde Perfil; dolor y fatiga conservan prioridad."
                        } else {
                            if (truth(jsLess(adherence, 0.8))) {
                                reason = "Mantén la dosis: completa al menos el 80 % de los días de ejercicio antes de progresar."
                            }
                        }
                    }
                }
            }
            if (true) {
                val iterator49: dynamic = iteratorOf(next.days)
                while (true) {
                    val next50: dynamic = iterator49.next()
                    if (truth(next50.done)) break
                    var d: dynamic = absent
                    d = next50.value
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, now)) { d.customized }) { d.routineId }) { active(logs[d.date]) }) { onPresent(d.tasks) { value51 -> value51.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) } })) {
                        continue
                    }
                    var edited: dynamic = false
                    if (truth((action === "pausar"))) {
                        if (truth(d.items.length)) {
                            d.items = js("[]")
                            d.kind = "rest"
                            d.tasks = jsonValue("[{\"id\":\"health-review\",\"text\":\"Revisar síntomas e indicaciones antes de entrenar\",\"done\":false}]")
                            edited = true
                        }
                    } else {
                        if (true) {
                            val iterator58: dynamic = iteratorOf(d.items)
                            while (true) {
                                val next59: dynamic = iterator58.next()
                                if (truth(next59.done)) break
                                var x: dynamic = absent
                                x = next59.value
                                if (truth((action === "reducir"))) {
                                    x.target = global_Math.max((if (truth((E.unitOf(x) === "sec"))) 10 else 4), global_Math.floor(jsMultiply(x.target, 0.8)))
                                    edited = true
                                } else {
                                    if (truth(jsAnd(jsAnd((!truth(mode.reduced))) { (!truth(personalReduced)) }) { jsAtLeast(adherence, 0.8) })) {
                                        val r: dynamic = global_AdaptiveV9.recommend(logs, x, p, now)
                                        details.push(jsObject { obj62 ->
                                            obj62["fromId"] = x.id
                                            copyFields(obj62, r)
                                        })
                                        if (truth(jsAnd(r.variantChanged) { root.TrainingProgression19 })) {
                                            global_Object.assign(x, root.TrainingProgression19.applyRecommendation(x, r, p))
                                            edited = true
                                        } else {
                                            if (truth(jsMore(r.target, x.target))) {
                                                x.target = r.target
                                                edited = true
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    if (truth(edited)) {
                        run {
                            val previous65: dynamic = numeric(changed)
                            changed = jsAdd(previous65, 1)
                        }
                    }
                }
            }
            if (truth(jsAnd((action === "mantener")) { changed })) {
                action = "aumentar"
                reason = (if (truth(details.some((fun(x: dynamic): dynamic {
                    return x.variantChanged
                })))) "Registros completos y adherencia suficiente: se avanza a una variante compatible, recalibrando repeticiones y series." else "Tres registros completos del mismo ejercicio y adherencia suficiente: +1 repetición o hasta 10 % de tiempo.")
            }
            val r: dynamic = jsObject { obj66 ->
                obj66["week"] = 1
                obj66["date"] = now
                obj66["from"] = last.start
                obj66["action"] = action
                obj66["reason"] = reason
                obj66["adherence"] = adherence
                obj66["effort"] = effort
                obj66["changed"] = changed
                obj66["details"] = details
            }
            if (truth(root.ProgressMonitor20)) {
                r.analysis20 = root.ProgressMonitor20.summary(logs, next.days, p, now)
            }
            next.reviews = arrayOf<dynamic>(r)
            next.decision = reason
            return r
        }
        current = fun(s: dynamic, arg75: dynamic): dynamic {
            var today: dynamic = absent
            today = definedOr(arg75) { E.dateKey() }
            migrate(s, today)
            val start: dynamic = startOfWeek(today)
            if (truth(onPresent(root.Runtime20) { value76 -> value76.initializing })) {
                val existing: dynamic = s.cycles.find((fun(c: dynamic): dynamic {
                    return (c.start === start)
                }))
                if (truth(onPresent(existing) { value77 -> value77.activated })) {
                    return existing
                }
                val last: dynamic = s.cycles.filter((fun(c: dynamic): dynamic {
                    return jsLess(c.start, start)
                })).at((-numeric(1)))
                val preview: dynamic = E.makeCycle(s.profile, start, jsAdd(nullableOr(onPresent(last) { value78 -> value78.index }) { (-numeric(1)) }, 1), last, s.logs)
                preview.activeFrom = today
                preview.previewBoot20 = true
                return preview
            }
            val c: dynamic = s.cycles.find((fun(c: dynamic): dynamic {
                return (c.start === start)
            }))
            if (truth(onPresent(c) { value83 -> value83.activated })) {
                return c
            }
            val last: dynamic = s.cycles.filter((fun(c: dynamic): dynamic {
                return jsLess(c.start, start)
            })).at((-numeric(1)))
            val index: dynamic = nullableOr(onPresent(c) { value84 -> value84.index }) { jsAdd(nullableOr(onPresent(s.cycles.at((-numeric(1)))) { value85 -> value85.index }) { (-numeric(1)) }, 1) }
            val fresh: dynamic = E.makeCycle(s.profile, start, index, last, s.logs)
            fresh.activeFrom = (if (truth((onPresent(last) { value88 -> value88.start } === E["plus"](start, (-numeric(7)))))) start else today)
            if (truth(c)) {
                fresh.days = fresh.days.map((fun(d: dynamic): dynamic {
                    val old: dynamic = c.days.find((fun(x: dynamic): dynamic {
                        return (x.date === d.date)
                    }))
                    return (if (truth(jsAnd(old) { jsOr(jsOr(jsOr(old.customized) { old.routineId }) { active(s.logs[d.date]) }) { onPresent(old.tasks) { value91 -> value91.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) } } })) old else d)
                }))
            }
            review(last, fresh, s.logs, s.profile, today, jsOr(onPresent(s.weekFeedback) { value92 -> value92[today] }) { js("({})") })
            fresh.activated = true
            if (truth(c)) {
                s.cycles[s.cycles.indexOf(c)] = fresh
            } else {
                s.cycles.push(fresh)
            }
            s.cycles.sort((fun(a: dynamic, b: dynamic): dynamic {
                return a.start.localeCompare(b.start)
            }))
            return fresh
        }
        progress = fun(l: dynamic): dynamic {
            if (truth(jsOr((!truth(l))) { l.pain })) {
                return jsonValue("{\"done\":0,\"total\":0,\"fraction\":0,\"xp\":0}")
            }
            val tasks: dynamic = jsOr(l.missionTasks) { js("[]") }
            val total: dynamic = jsAdd(dynamicValue(jsOr(l.items) { js("[]") }).length, tasks.length)
            var done: dynamic = tasks.filter((fun(t: dynamic): dynamic {
                return t.done
            })).length
            if (true) {
                val iterator99: dynamic = iteratorOf(jsOr(l.items) { js("[]") })
                while (true) {
                    val next100: dynamic = iterator99.next()
                    if (truth(next100.done)) break
                    var x: dynamic = absent
                    x = next100.value
                    run {
                        done = jsAdd(done, (if (truth(jsOr(x.selfReported) { x.missionState })) 1 else global_Math.min(1, jsDivide(dynamicValue(jsOr(x["actual"]) { js("[]") }).filter((fun(a: dynamic): dynamic {
                            return a.done
                        })).length, global_Math.max(1, x.sets)))))
                    }
                }
            }
            val fraction: dynamic = (if (truth(l.complete)) 1 else (if (truth(total)) global_Math.min(1, jsDivide(done, total)) else 0))
            return jsObject { obj101 ->
                obj101["done"] = done
                obj101["total"] = total
                obj101["fraction"] = fraction
                obj101["xp"] = global_Math.floor(jsMultiply(dailyXP(l.kind), fraction))
            }
        }
        E = root.Engine
        startOfWeek = (fun(date: dynamic): dynamic {
            return E["plus"](date, (-numeric(jsMod(jsAdd(jsConstruct(global_Date, arrayOf<dynamic>(jsAdd(date, "T12:00:00"))).getDay(), 6), 7))))
        })
        active = (fun(l: dynamic): dynamic {
            return jsAnd((!truth((!truth(l))))) { jsOr(jsOr(jsOr(l.complete) { l.pain }) { l.manual }) { onPresent(l.items) { value3 -> value3.some((fun(x: dynamic): dynamic {
                return jsOr(jsOr(x.missionState) { x.selfReported }) { onPresent(x["actual"]) { value4 -> value4.some((fun(a: dynamic): dynamic {
                    return a.done
                })) } }
            })) } } }
        })
        val previousMake: dynamic = E.makeCycle
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg13: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg13) { js("({})") }
            val c: dynamic = previousMake(jsObject { obj14 ->
                copyFields(obj14, p)
                obj14["minutes"] = 30
            }, start, index, last, logs)
            c.days = c.days.slice(0, 7)
            c.profile = global_JSON.parse(global_JSON.stringify(p))
            c.periodDays = 7
            c.weeklyVersion = 10
            c.activeFrom = start
            c.reviews = js("[]")
            return c
        })
        dailyXP = (fun(kind: dynamic): dynamic {
            return (if (truth(jsOr((kind === "strength")) { (kind === "mixed") })) 100 else (if (truth((kind === "cardio"))) 70 else (if (truth((kind === "recovery"))) 40 else 20)))
        })
        val oldStats: dynamic = E.stats
        E.stats = (fun(logs: dynamic, arg106: dynamic): dynamic {
            var today: dynamic = absent
            today = definedOr(arg106) { E.dateKey() }
            val s: dynamic = oldStats(logs, today)
            s.xp = global_Object.entries(logs).filter((fun(arg111: dynamic): dynamic {
                var d: dynamic = absent
                val unpacked112: dynamic = fromIterable(arg111)
                d = unpacked112[0]
                return jsAtMost(d, today)
            })).reduce((fun(a: dynamic, arg113: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked114: dynamic = fromIterable(arg113)
                l = unpacked114[1]
                return jsAdd(a, progress(l).xp)
            }), 0)
            s.level = jsAdd(1, global_Math.floor(jsDivide(s.xp, 300)))
            return s
        })
        root.Weekly = jsObject { obj119 ->
            obj119["startOfWeek"] = startOfWeek
            obj119["month"] = month
            obj119["migrate"] = migrate
            obj119["review"] = review
            obj119["current"] = current
            obj119["active"] = active
            obj119["progress"] = progress
            obj119["dailyXP"] = dailyXP
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
