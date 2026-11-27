@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterstable3828

import hunter.browser.*

@JsName("JSON") private external var global_JSON: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("workout") private external var global_workout: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterStable3828() {
    dynamicValue((fun(root: dynamic): dynamic {
        var today: dynamic = absent
        var safetyKey: dynamic = absent
        var started: dynamic = absent
        var keep: dynamic = absent
        var existing: dynamic = absent
        var eligibleToPin: dynamic = absent
        var pin: dynamic = absent
        keep = fun(day: dynamic, p: dynamic, arg20: dynamic, arg21: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg20) { js("({})") }
            var date: dynamic = absent
            date = definedOr(arg21) { today() }
            if (truth(jsOr(jsOr((!truth(day))) { (day.date !== date) }) { (day.dailyFixed3828 !== date) })) {
                return false
            }
            return jsOr((day.dailySafety3828 === safetyKey(p))) { started(onPresent(logs) { value22 -> value22[date] }) }
        }
        existing = fun(date: dynamic): dynamic {
            return jsOr(onPresent(global_state) { value25 -> onPresent(value25.cycles) { value26 -> value26.flatMap((fun(c: dynamic): dynamic {
                return jsOr(c.days) { js("[]") }
            })).find((fun(d: dynamic): dynamic {
                return (d.date === date)
            })) } }) { null }
        }
        eligibleToPin = fun(day: dynamic, p: dynamic, date: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(day))) { (!truth(p)) }) { (!truth(global_Array.isArray(day.items))) })) {
                return false
            }
            val choice: dynamic = onPresent(p.weekPlan) { value33 -> value33[onPresent(root.Schedule) { value30 -> run { val owner32: dynamic = value30; onPresent(owner32.weekday) { method31 -> invokeWith(method31, owner32, arrayOf<dynamic>(date)) } } }] }
            if (truth((choice === "rest"))) {
                return (day.kind === "rest")
            }
            if (truth(jsAnd(jsAnd(choice) { (choice !== "rest") }) { (day.kind === "rest") })) {
                return false
            }
            return jsOr(jsMore(day.items.length, 0)) { global_Boolean(jsOr(day.blocked381) { day.blocked378 }) }
        }
        pin = fun(day: dynamic, p: dynamic, date: dynamic): dynamic {
            if (truth(jsOr((!truth(day))) { (day.date !== date) })) {
                return false
            }
            val changed: dynamic = (day.dailyFixed3828 !== date)
            day.dailyFixed3828 = date
            day.dailySafety3828 = safetyKey(p)
            return changed
        }
        if (truth(root.HunterStable3828)) {
            return absent
        }
        val E: dynamic = root.Engine
        val clone: dynamic = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        today = (fun(): dynamic {
            return jsOr(onPresent(E) { value3 -> run { val owner5: dynamic = value3; onPresent(owner5.dateKey) { method4 -> invokeWith(method4, owner5, js("[]")) } } }) { jsConstruct(global_Date, js("[]")).toLocaleDateString("sv-SE") }
        })
        safetyKey = (fun(p: dynamic): dynamic {
            return global_JSON.stringify(arrayOf<dynamic>(onPresent(p) { value6 -> value6.age }, onPresent(p) { value7 -> value7.equipment }, onPresent(p) { value8 -> value8.gymEquipment23 }, onPresent(p) { value9 -> value9.bench }, onPresent(p) { value10 -> value10.step }, jsOr(onPresent(p) { value11 -> value11.injuries }) { js("[]") }, jsOr(onPresent(p) { value12 -> value12.restrictions }) { js("[]") }, jsOr(onPresent(p) { value13 -> value13.limits }) { null }, jsOr(onPresent(p) { value14 -> value14.health }) { js("({})") }))
        })
        started = (fun(l: dynamic): dynamic {
            return global_Boolean(jsAnd(l) { jsOr(jsOr(jsOr(jsOr(jsOr(l.complete) { l.manual }) { l.pain }) { l.fatigue }) { onPresent(l.items) { value15 -> value15.some((fun(i: dynamic): dynamic {
                return onPresent(i["actual"]) { value16 -> value16.some((fun(a: dynamic): dynamic {
                    return jsOr((a.done === true)) { a.selfReported }
                })) }
            })) } }) { onPresent(l.missionTasks) { value17 -> value17.some((fun(t: dynamic): dynamic {
                return (t.done === true)
            })) } } })
        })
        try {
            val d: dynamic = existing(today())
            if (truth(jsAnd(jsAnd(jsAnd(d) { onPresent(global_state) { value40 -> value40.profile } }) { eligibleToPin(d, global_state.profile, today()) }) { (!truth(d.dailyFixed3828)) })) {
                pin(d, global_state.profile, today())
            }
        } catch (_: dynamic) {

        }
        if (truth((js("typeof currentCycle") === "function"))) {
            val original: dynamic = global_currentCycle
            var inside: dynamic = false
            global_currentCycle = (fun(): dynamic {
                if (truth(inside)) {
                    return original()
                }
                inside = true
                try {
                    val date: dynamic = today()
                    val profile: dynamic = onPresent(global_state) { value43 -> value43.profile }
                    val prior: dynamic = jsAnd(profile) { existing(date) }
                    if (truth(jsAnd(jsAnd(prior) { (!truth(prior.dailyFixed3828)) }) { eligibleToPin(prior, profile, date) })) {
                        pin(prior, profile, date)
                    }
                    val frozen: dynamic = (if (truth(jsAnd(prior) { keep(prior, profile, jsOr(onPresent(global_state) { value44 -> value44.logs }) { js("({})") }, date) })) clone(prior) else null)
                    val c: dynamic = original()
                    if (truth(jsOr((!truth(c))) { (!truth(profile)) })) {
                        return c
                    }
                    val at: dynamic = nullableOr(onPresent(c.days) { value45 -> value45.findIndex((fun(d: dynamic): dynamic {
                        return (d.date === date)
                    })) }) { (-numeric(1)) }
                    if (truth(jsLess(at, 0))) {
                        return c
                    }
                    var changed: dynamic = false
                    if (truth(frozen)) {
                        val after: dynamic = c.days[at]
                        if (truth((global_JSON.stringify(after) !== global_JSON.stringify(frozen)))) {
                            c.days[at] = frozen
                            changed = true
                        }
                    } else {
                        val d: dynamic = c.days[at]
                        if (truth(jsAnd((!truth(d.dailyFixed3828))) { eligibleToPin(d, profile, date) })) {
                            changed = pin(d, profile, date)
                        }
                    }
                    val `actual`: dynamic = c.days[at]
                    val newSafety: dynamic = safetyKey(profile)
                    if (truth(jsAnd(jsAnd((onPresent(`actual`) { value48 -> value48.dailyFixed3828 } === date)) { (`actual`.dailySafety3828 !== newSafety) }) { started(onPresent(global_state) { value49 -> onPresent(value49.logs) { value50 -> value50[date] } }) })) {
                        if (truth((!truth(`actual`.dailyReview3828)))) {
                            `actual`.dailyReview3828 = true
                            changed = true
                        }
                    }
                    if (truth(jsAnd(jsAnd(changed) { (js("typeof save") === "function") }) { (!truth(onPresent(root.Runtime20) { value53 -> value53.initializing })) })) {
                        global_save()
                    }
                    return c
                } finally {
                    inside = false
                }
                return absent
            })
        }
        if (truth((js("typeof workout") === "function"))) {
            val prevWorkout: dynamic = global_workout
            global_workout = (fun(): dynamic {
                val view: dynamic = prevWorkout()
                val d: dynamic = existing(today())
                return (if (truth(onPresent(d) { value56 -> value56.dailyReview3828 })) jsAdd("<div class=\"notice warn\" role=\"alert\">Has cambiado restricciones de salud o equipamiento durante una misión iniciada. Detén el entrenamiento y revisa la sesión antes de continuar; tus registros se han conservado.</div>", view) else view)
            })
        }
        root.HunterStable3828 = global_Object.freeze(jsObject { obj59 ->
            obj59["version"] = "38.28"
            obj59["today"] = today
            obj59["safetyKey"] = safetyKey
            obj59["started"] = started
            obj59["keep"] = keep
            obj59["pin"] = pin
        })
        return absent
    }))(global_window)
}
