@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterunified3827

import hunter.browser.*

@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterUnified3827() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var D: dynamic = absent
        var categories: dynamic = absent
        var toNumber: dynamic = absent
        var risk: dynamic = absent
        var activeLog: dynamic = absent
        var isDay: dynamic = absent
        var records: dynamic = absent
        var dose: dynamic = absent
        var gymTemplates: dynamic = absent
        var unify: dynamic = absent
        records = fun(logs: dynamic, date: dynamic): dynamic {
            val start: dynamic = E["plus"](date, (-numeric(14)))
            val recent: dynamic = global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg19: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked20: dynamic = fromIterable(arg19)
                d = unpacked20[0]
                l = unpacked20[1]
                return jsAnd(jsAnd(jsAnd(isDay(d)) { jsLess(d, date) }) { jsAtLeast(d, start) }) { l }
            }))
            val completed: dynamic = recent.filter((fun(arg25: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked26: dynamic = fromIterable(arg25)
                l = unpacked26[1]
                return jsAnd(jsAnd(jsAnd(jsAnd((l.complete === true)) { (!truth(l.manual)) }) { (!truth(l.pain)) }) { onPresent(l.items) { value27 -> value27.some((fun(x: dynamic): dynamic {
                    return onPresent(x["actual"]) { value28 -> value28.some((fun(a: dynamic): dynamic {
                        return (a.done === true)
                    })) }
                })) } }) { jsMore(global_Number(l.rpe), 0) }
            })).map((fun(arg29: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked30: dynamic = fromIterable(arg29)
                l = unpacked30[1]
                return l
            }))
            val avg: dynamic = (if (truth(completed.length)) jsDivide(completed.reduce((fun(s: dynamic, l: dynamic): dynamic {
                return jsAdd(s, global_Number(l.rpe))
            }), 0), completed.length) else 0)
            val symptoms: dynamic = jsOr(recent.some((fun(arg31: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked32: dynamic = fromIterable(arg31)
                l = unpacked32[1]
                return jsOr(jsOr(l.pain) { l.fatigue }) { jsAtLeast(global_Number(l.rpe), 8) }
            }))) { (!truth((!truth(onPresent(root.HunterConstraints3811) { value33 -> run { val owner35: dynamic = value33; onPresent(owner35.symptomTrend) { method34 -> onPresent(invokeWith(method34, owner35, arrayOf<dynamic>(logs, date))) { value36 -> value36.recentSymptom } } } })))) }
            return jsObject { obj37 ->
                obj37["completed"] = completed.length
                obj37["average"] = avg
                obj37["symptoms"] = symptoms
                obj37["ready"] = jsAnd(jsAnd(jsAnd(jsAtLeast(completed.length, 2)) { jsAtLeast(avg, 3) }) { jsAtMost(avg, 7) }) { (!truth(symptoms)) }
            }
        }
        dose = fun(e: dynamic, p: dynamic, kind: dynamic, status: dynamic, feedback: dynamic, date: dynamic, legacy: dynamic, weekIndex: dynamic, logs: dynamic, base: dynamic): dynamic {
            val `out`: dynamic = jsObject { obj40 ->
                copyFields(obj40, legacy)
            }
            val b: dynamic = jsOr(base) { legacy }
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(e))) { (!truth(p)) }) { jsLess(p.age, 18) }) { jsLess(weekIndex, 1) }) { jsMore(weekIndex, 2) }) { (!truth(dynamicValue(jsonValue("[\"strength\",\"cardio\"]")).includes(kind))) }) { risk(p) }) { onPresent(status) { value41 -> value41.reduced } }) { onPresent(feedback) { value42 -> value42.strain } }) { `out`.noAutoLoad }) { jsAnd(jsLess(global_Number(p.trainingScale), 1)) { (p.trainingScale !== absent) } })) {
                return `out`
            }
            val `data`: dynamic = records(logs, date)
            if (truth(jsOr((!truth(`data`.ready))) { (onPresent(feedback) { value43 -> value43.canProgress } !== true) })) {
                return `out`
            }
            val seconds: dynamic = (e.unit === "sec")
            val initial: dynamic = toNumber(b.target, 1, 120)
            val current: dynamic = toNumber(`out`.target, 1, 120)
            val aggressive: dynamic = (if (truth((weekIndex === 1))) 0.1 else 0.18)
            val limit: dynamic = (if (truth(seconds)) 90 else (if (truth((p.difficulty === "advanced"))) 24 else (if (truth((p.difficulty === "intermediate"))) 20 else 16)))
            val wanted: dynamic = global_Math.min(limit, global_Math.ceil(jsMultiply(initial, jsAdd(1, aggressive))))
            `out`.target = global_Math.max(current, wanted)
            `out`.sets = toNumber(`out`.sets, 1, 4)
            `out`.progression3827 = jsObject { obj50 ->
                obj50["mode"] = "exigente"
                obj50["week"] = jsAdd(weekIndex, 1)
                obj50["evidence"] = `data`.completed
                obj50["reason"] = "Mejora condicionada a sesiones completadas, RPE 3–7 y ausencia de molestias."
            }
            return `out`
        }
        gymTemplates = fun(p: dynamic, arg53: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg53) { js("({})") }
            if (truth(jsOr((onPresent(p) { value54 -> value54.equipment } !== "gym")) { (!truth(D.gate(p).ok)) })) {
                return js("[]")
            }
            val level: dynamic = (if (truth((jsTypeOf(onPresent(root.TrainingProgression19) { value55 -> value55.level }) === "function"))) root.TrainingProgression19.level(p) else jsOr(jsonValue("{\"basic\":0,\"intermediate\":1,\"advanced\":2}")[p.difficulty]) { 0 })
            val focus: dynamic = jsonValue("[[\"full\",\"general\"],[\"upper\",\"upper\"],[\"lower\",\"lower\"],[\"core\",\"core\"],[\"push\",\"upper\"],[\"pull\",\"upper\"]]")
            val date: dynamic = E.dateKey()
            val output: dynamic = js("[]")
            val pastLogs: dynamic = global_Object.fromEntries(global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg56: dynamic): dynamic {
                var d: dynamic = absent
                val unpacked57: dynamic = fromIterable(arg56)
                d = unpacked57[0]
                return jsLess(d, date)
            })))
            if (true) {
                val iterator58: dynamic = iteratorOf(focus)
                while (true) {
                    val next59: dynamic = iterator58.next()
                    if (truth(next59.done)) break
                    var family: dynamic = absent
                    var body: dynamic = absent
                    val unpacked60: dynamic = fromIterable(next59.value)
                    family = unpacked60[0]
                    body = unpacked60[1]
                    if (true) {
                        val iterator61: dynamic = iteratorOf(categories)
                        while (true) {
                            val next62: dynamic = iterator61.next()
                            if (truth(next62.done)) break
                            var kind: dynamic = absent
                            var label: dynamic = absent
                            val unpacked63: dynamic = fromIterable(next62.value)
                            kind = unpacked63[0]
                            label = unpacked63[1]
                            if (truth(jsAnd((family === "core")) { (kind !== "strength") })) {
                                continue
                            }
                            val candidate: dynamic = jsObject { obj64 ->
                                copyFields(obj64, p)
                                obj64["focus"] = body
                                obj64["dynamicRequestedFocus3847"] = body
                                obj64["dynamicGymPreferred3827"] = true
                            }
                            val chosen: dynamic = D.choose(candidate, kind, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("38.27|", date), "|gym|"), family), "|"), kind), pastLogs, date, jsConstruct(global_Set, js("[]")), null, 0)
                            if (truth((!truth(onPresent(chosen.items) { value65 -> value65.length })))) {
                                continue
                            }
                            if (truth((!truth(chosen.items.every((fun(item: dynamic): dynamic {
                                val e: dynamic = E.byId(item.id)
                                return jsAnd(e) { D.compatible(e, p, risk(p)) }
                            })))))) {
                                continue
                            }
                            val all: dynamic = chosen.items.map((fun(item: dynamic): dynamic {
                                return E.byId(item.id)
                            }))
                            val known: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(global_Object.keys(jsOr(onPresent(root.Training23) { value66 -> value66.gymEquipment }) { js("({})") })))
                            val gymNeeds: dynamic = jsArray { array67 ->
                                appendAll(array67, jsConstruct(global_Set, arrayOf<dynamic>(all.flatMap((fun(e: dynamic): dynamic {
                                    return jsOr(e.gymRequirements23) { js("[]") }
                                })).filter((fun(x: dynamic): dynamic {
                                    return known.has(x)
                                })))))
                            }
                            val requirements: dynamic = jsArray { array68 ->
                                appendAll(array68, jsConstruct(global_Set, arrayOf<dynamic>(all.flatMap((fun(e: dynamic): dynamic {
                                    return (if (truth(global_Array.isArray(e.requirements))) e.requirements else js("[]"))
                                })))))
                            }
                            output.push(jsObject { obj69 ->
                                obj69["id"] = jsAdd(jsAdd(jsAdd("dyn3827-gym-", family), "-"), kind)
                                obj69["name"] = jsAdd(jsAdd(jsAdd("Gimnasio dinámico · ", label), " · "), jsonValue("{\"full\":\"Completo\",\"upper\":\"Superior\",\"lower\":\"Inferior\",\"core\":\"Tren central\",\"push\":\"Empuje\",\"pull\":\"Tracción\"}")[family])
                                obj69["category"] = (if (truth((chosen.effective === "cardio"))) "Cardio" else (if (truth((chosen.effective === "recovery"))) "Movilidad" else "Fuerza"))
                                obj69["level"] = level
                                obj69["items"] = chosen.items
                                obj69["requirements"] = requirements
                                obj69["goalGroups"] = jsonValue("[\"fitness\",\"habit\",\"movement\",\"build\",\"athletic\"]")
                                obj69["family"] = jsAdd("gym-", family)
                                obj69["gymOnly23"] = true
                                obj69["gymRequirements23"] = gymNeeds
                                obj69["extension"] = "standard"
                                obj69["minutes"] = global_Math.max(10, global_Math.min(90, jsOr(global_Number(p.minutes)) { 20 }))
                                obj69["safe381"] = true
                                obj69["dynamic3827"] = true
                                obj69["description"] = "Rutina local calculada a partir de las demostraciones 2D y las preferencias del perfil."
                            })
                        }
                    }
                }
            }
            return output
        }
        unify = fun(c: dynamic, p: dynamic, logs: dynamic, today: dynamic): dynamic {
            if (truth(jsOr(jsOr(jsOr((!truth(onPresent(c) { value77 -> value77.days }))) { (!truth(p)) }) { (!truth(D.patch)) }) { (onPresent(c.reviews) { value78 -> onPresent(value78.at((-numeric(1)))) { value79 -> value79.action } } === "pausar") })) {
                return 0
            }
            var released: dynamic = 0
            if (true) {
                val iterator80: dynamic = iteratorOf(c.days)
                while (true) {
                    val next81: dynamic = iterator80.next()
                    if (truth(next81.done)) break
                    var d: dynamic = absent
                    d = next81.value
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, today)) { d.customized }) { activeLog(onPresent(logs) { value82 -> value82[d.date] }) }) { onPresent(d.tasks) { value83 -> value83.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) } }) { onPresent(root.HunterStable3828) { value84 -> run { val owner86: dynamic = value84; onPresent(owner86.keep) { method85 -> invokeWith(method85, owner86, arrayOf<dynamic>(d, p, logs, today)) } } } })) {
                        continue
                    }
                    if (truth((d.creatorAssigned === true))) {
                        removeKey(d, "routineId")
                        removeKey(d, "creatorAssigned")
                        removeKey(d, "sourceSequence")
                        removeKey(d, "autoTemplateId")
                        removeKey(d, "dynamicKey381")
                        run {
                            val previous87: dynamic = numeric(released)
                            released = jsAdd(previous87, 1)
                        }
                    }
                }
            }
            if (truth(released)) {
                removeKey(c, "dynamicKey381")
            }
            return jsAdd(released, D.patch(c, p, logs, today))
        }
        if (truth(root.HunterUnified3827)) {
            return absent
        }
        E = root.Engine
        D = root.HunterDynamic381
        val A: dynamic = root.Adapt
        if (truth(jsOr(jsOr((!truth(E))) { (!truth(D)) }) { (!truth(A)) })) {
            return absent
        }
        categories = jsonValue("[[\"strength\",\"Fuerza\"],[\"cardio\",\"Cardio\"],[\"recovery\",\"Movilidad\"]]")
        toNumber = (fun(x: dynamic, min: dynamic, max: dynamic): dynamic {
            val n: dynamic = global_Number(x)
            return (if (truth(global_Number.isFinite(n))) global_Math.max(min, global_Math.min(max, n)) else min)
        })
        risk = (fun(p: dynamic): dynamic {
            return global_Boolean(jsOr(jsOr(jsOr(jsOr(jsOr(onPresent(p) { value3 -> value3.limits }) { onPresent(p) { value4 -> onPresent(value4.health) { value5 -> value5.special } } }) { onPresent(p) { value6 -> onPresent(value6.health) { value7 -> value7.acute } } }) { onPresent(p) { value8 -> onPresent(value8.health) { value9 -> value9.redFlags } } }) { jsAnd(onPresent(p) { value10 -> onPresent(value10.health) { value11 -> value11.pain } }) { (p.health.pain !== "none") } }) { dynamicValue(jsOr(onPresent(p) { value13 -> value13.injuries }) { js("[]") }).some((fun(x: dynamic): dynamic {
                return jsAnd(x) { (x.stage !== "past") }
            })) })
        })
        activeLog = (fun(l: dynamic): dynamic {
            return global_Boolean(jsAnd(l) { jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(l.complete) { l.manual }) { l.pain }) { l.fatigue }) { l.selfReported }) { onPresent(l.missionTasks) { value14 -> value14.some((fun(t: dynamic): dynamic {
                return t.done
            })) } }) { onPresent(l.items) { value15 -> value15.some((fun(x: dynamic): dynamic {
                return jsOr(jsOr(x.selfReported) { x.missionState }) { onPresent(x["actual"]) { value16 -> value16.some((fun(a: dynamic): dynamic {
                    return a.done
                })) } }
            })) } } })
        })
        isDay = (fun(s: dynamic): dynamic {
            return dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(s)
        })
        if (truth((js("typeof allRoutines") === "function"))) {
            val old: dynamic = global_allRoutines
            global_allRoutines = (fun(): dynamic {
                return jsArray { array72 ->
                    appendAll(array72, old())
                    appendAll(array72, gymTemplates(onPresent(global_state) { value73 -> value73.profile }, jsOr(onPresent(global_state) { value74 -> value74.logs }) { js("({})") }))
                }
            })
        }
        if (truth((js("typeof currentCycle") === "function"))) {
            val previous: dynamic = global_currentCycle
            global_currentCycle = (fun(): dynamic {
                val c: dynamic = previous()
                if (truth(jsOr((!truth(c))) { (!truth(onPresent(global_state) { value90 -> value90.profile })) })) {
                    return c
                }
                val changed: dynamic = unify(c, global_state.profile, jsOr(global_state.logs) { js("({})") }, E.dateKey())
                if (truth(jsAnd(jsAnd(changed) { (js("typeof save") === "function") }) { (!truth(onPresent(root.Runtime20) { value91 -> value91.initializing })) })) {
                    global_save()
                }
                return c
            })
        }
        root.HunterUnified3827 = global_Object.freeze(jsObject { obj94 ->
            obj94["version"] = "38.27"
            obj94["records"] = records
            obj94["dose"] = dose
            obj94["gymTemplates"] = gymTemplates
            obj94["unify"] = unify
            obj94["risk"] = risk
            obj94["diagnostics"] = (fun(): dynamic {
                return jsonValue("{\"engine\":\"HunterDynamic381\",\"sources\":[\"misiones\",\"diario\",\"gimnasio\",\"casa\"],\"offline\":true,\"progression\":\"exigente, basada en rendimiento; no incremento ante riesgo\"}")
            })
        })
        return absent
    }))((if (truth((js("typeof window") === "undefined"))) global_globalThis else global_window))
}
