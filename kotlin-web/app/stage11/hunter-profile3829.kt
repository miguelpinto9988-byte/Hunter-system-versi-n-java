@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterprofile3829

import hunter.browser.*

@JsName("JSON") private external var global_JSON: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("localStorage") private external var global_localStorage: dynamic
@JsName("KEY") private external var global_KEY: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("finish") private external var global_finish: dynamic
@JsName("workout") private external var global_workout: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterProfile3829() {
    dynamicValue((fun(root: dynamic): dynamic {
        var S: dynamic = absent
        var D: dynamic = absent
        var Stable: dynamic = absent
        var clone: dynamic = absent
        var fingerprint: dynamic = absent
        var kinds: dynamic = absent
        var dateKey: dynamic = absent
        var signature: dynamic = absent
        var freshItem: dynamic = absent
        var reconcile: dynamic = absent
        var regenerate: dynamic = absent
        reconcile = fun(log: dynamic, planned: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(log))) { log.complete }) { log.manual })) {
                return absent
            }
            val old: dynamic = (if (truth(global_Array.isArray(log.items))) log.items else js("[]"))
            val consumed: dynamic = jsConstruct(global_Set, js("[]"))
            val next: dynamic = planned.map((fun(plan: dynamic): dynamic {
                val obj: dynamic = freshItem(plan)
                val index: dynamic = old.findIndex((fun(x: dynamic, i: dynamic): dynamic {
                    return jsAnd(jsAnd((!truth(consumed.has(i)))) { (!truth(x.archivedProfile3829)) }) { (signature(x) === signature(obj)) }
                }))
                if (truth(jsLess(index, 0))) {
                    return obj
                }
                consumed.add(index)
                val prior: dynamic = old[index]
                val completed: dynamic = dynamicValue(jsOr(prior["actual"]) { js("[]") }).filter((fun(a: dynamic): dynamic {
                    return onPresent(a) { value18 -> value18.done }
                })).map(clone)
                run { val target20: dynamic = obj["actual"]; invokeWith(target20.splice, target20, jsArray { array19 ->
                    array19.push(0)
                    array19.push(global_Math.min(completed.length, obj["actual"].length))
                    appendAll(array19, completed.slice(0, obj["actual"].length))
                }) }
                return obj
            }))
            val archive: dynamic = js("[]")
            old.forEach((fun(x: dynamic, i: dynamic): dynamic {
                if (truth(consumed.has(i))) {
                    val taken: dynamic = jsOr(onPresent(next.find((fun(n: dynamic): dynamic {
                        return (n.id === x.id)
                    }))) { value21 -> value21["actual"].filter((fun(a: dynamic): dynamic {
                        return a.done
                    })).length }) { 0 }
                    val overflow: dynamic = dynamicValue(jsOr(x["actual"]) { js("[]") }).filter((fun(a: dynamic): dynamic {
                        return onPresent(a) { value25 -> value25.done }
                    })).slice(taken)
                    if (truth(overflow.length)) {
                        archive.push(jsObject { obj26 ->
                            copyFields(obj26, clone(x))
                            obj26["sets"] = overflow.length
                            obj26["actual"] = overflow
                            obj26["archivedProfile3829"] = true
                        })
                    }
                } else {
                    val completed: dynamic = dynamicValue(jsOr(x["actual"]) { js("[]") }).filter((fun(a: dynamic): dynamic {
                        return onPresent(a) { value27 -> value27.done }
                    }))
                    if (truth(completed.length)) {
                        archive.push(jsObject { obj28 ->
                            copyFields(obj28, clone(x))
                            obj28["sets"] = completed.length
                            obj28["actual"] = clone(completed)
                            obj28["archivedProfile3829"] = true
                        })
                    }
                }
                return absent
            }))
            log.items = jsArray { array31 ->
                appendAll(array31, archive)
                appendAll(array31, next)
            }
            return absent
        }
        regenerate = fun(oldProfile: dynamic, oldLog: dynamic, oldDay: dynamic): dynamic {
            val p: dynamic = onPresent(global_state) { value34 -> value34.profile }
            val date: dynamic = dateKey()
            val nowPlan: dynamic = onPresent(global_state) { value35 -> onPresent(value35.cycles) { value36 -> value36.find((fun(c: dynamic): dynamic {
                return onPresent(c.days) { value37 -> value37.some((fun(d: dynamic): dynamic {
                    return (d.date === date)
                })) }
            })) } }
            if (truth(jsOr((!truth(p))) { (!truth(nowPlan)) })) {
                return jsonValue("{\"status\":\"no-cycle\"}")
            }
            val d: dynamic = nowPlan.days.find((fun(x: dynamic): dynamic {
                return (x.date === date)
            }))
            val log: dynamic = onPresent(global_state.logs) { value38 -> value38[date] }
            if (truth(jsOr(jsOr(jsOr(jsOr(onPresent(log) { value39 -> value39.complete }) { onPresent(log) { value40 -> value40.manual } }) { onPresent(oldDay) { value41 -> value41.customized } }) { onPresent(oldDay) { value42 -> value42.routineId } }) { onPresent(oldDay) { value43 -> value43.creatorAssigned } })) {
                if (truth(oldDay)) {
                    val index: dynamic = nowPlan.days.indexOf(d)
                    nowPlan.days[index] = clone(oldDay)
                }
                val finished: dynamic = nowPlan.days.find((fun(x: dynamic): dynamic {
                    return (x.date === date)
                }))
                finished.dailySafety3828 = Stable.safetyKey(p)
                finished.dailyFixed3828 = date
                if (truth(onPresent(log) { value50 -> value50.complete })) {
                    finished.dailyReview3828 = false
                } else {
                    finished.dailyReview3828 = true
                }
                global_save()
                return jsonValue("{\"status\":\"finished-or-manual\"}")
            }
            val week: dynamic = p.weekPlan
            val choice: dynamic = (if (truth(S.valid(p))) week[S.weekday(date)] else null)
            val valid: dynamic = jsAnd((!truth((!truth(choice))))) { global_Object.prototype.hasOwnProperty.call(kinds, choice) }
            val kind: dynamic = (if (truth(valid)) kinds[choice] else null)
            val gate: dynamic = (if (truth(jsAnd(valid) { (kind !== "rest") })) D.gate(p) else jsonValue("{\"ok\":true}"))
            var result: dynamic = jsonValue("{\"items\":[],\"blocked\":null}")
            if (truth((!truth(valid)))) {
                result.blocked = "Revisa los días de entrenamiento y las restricciones del perfil."
            } else {
                if (truth((!truth(gate.ok)))) {
                    result.blocked = jsOr(gate.reason) { "Se requiere revisar el perfil antes de entrenar." }
                } else {
                    if (truth((kind !== "rest"))) {
                        val past: dynamic = global_Object.fromEntries(global_Object.entries(jsOr(global_state.logs) { js("({})") }).filter((fun(arg59: dynamic): dynamic {
                            var key: dynamic = absent
                            val unpacked60: dynamic = fromIterable(arg59)
                            key = unpacked60[0]
                            return jsLess(key, date)
                        })))
                        result = D.choose(p, kind, jsAdd(jsAdd(jsAdd(jsAdd(D.profileKey(p), "|"), date), "|"), nowPlan.index), past, date, jsConstruct(global_Set, js("[]")), null, global_Math.max(0, global_Math.floor(jsDivide(nowPlan.days.indexOf(d), 7))))
                        if (truth((!truth(onPresent(result.items) { value61 -> value61.length })))) {
                            result.blocked = jsOr(result.blocked) { "No hay ejercicios compatibles con las restricciones registradas." }
                        }
                    }
                }
            }
            if (truth(onPresent(log) { value64 -> value64.pain })) {
                result.blocked = "La sesión está detenida por dolor. Revisa tu situación antes de volver a entrenar."
            }
            d.kind = jsOr(kind) { d.kind }
            d.scheduleChoice = jsOr(choice) { d.scheduleChoice }
            d.items = (if (truth(result.blocked)) js("[]") else clone(jsOr(result.items) { js("[]") }))
            d.tasks = (if (truth(result.blocked)) jsonValue("[{\"id\":\"review-profile3829\",\"text\":\"Revisar el perfil y las restricciones\",\"done\":false}]") else js("[]"))
            d.title = (if (truth(result.blocked)) "Misión del día · requiere revisión" else (if (truth((kind === "rest"))) "Descanso elegido" else "Misión adaptada al perfil actualizado"))
            d.dynamic381 = true
            d.dailyFixed3828 = date
            d.dailySafety3828 = Stable.safetyKey(p)
            d.dailyReview3828 = (!truth((!truth(onPresent(log) { value85 -> value85.pain }))))
            d.profileSignature3829 = fingerprint(p)
            if (truth(result.blocked)) {
                d.blocked381 = result.blocked
                d.blocked378 = result.blocked
            } else {
                removeKey(d, "blocked381")
                removeKey(d, "blocked378")
                removeKey(d, "blocked379")
            }
            if (truth(log)) {
                reconcile(log, d.items)
                log.kind = d.kind
                log.profileChanged3829 = true
                if (truth(log.pain)) {
                    log.complete = false
                }
            }
            nowPlan.profile = clone(p)
            if (truth(jsAnd((js("typeof save") === "function")) { (!truth(onPresent(root.Runtime20) { value100 -> value100.initializing })) })) {
                global_save()
            }
            return jsObject { obj101 ->
                obj101["status"] = (if (truth(result.blocked)) "review" else "updated")
                obj101["kind"] = d.kind
            }
        }
        if (truth(root.HunterProfile3829)) {
            return absent
        }
        val E: dynamic = root.Engine
        S = root.Schedule
        D = root.HunterDynamic381
        Stable = root.HunterStable3828
        if (truth(jsOr(jsOr(jsOr(jsOr((!truth(E))) { (!truth(S)) }) { (!truth(D)) }) { (!truth(Stable)) }) { (js("typeof submitProfile") !== "function") })) {
            return absent
        }
        clone = (fun(`value`: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(`value`))
        })
        val changedFields: dynamic = jsonValue("[\"age\",\"limits\",\"injuries\",\"restrictions\",\"health\",\"goalAutoRestrictions374\",\"goal\",\"finalGoal\",\"finalTarget\",\"focus\",\"difficulty\",\"trainingScale\",\"trainingMode\",\"experience\",\"weekPlan\",\"weekFocus\",\"equipment\",\"equipmentModes3880\",\"gymEquipment23\",\"load\",\"minutes\",\"restSeconds\",\"dailyMissions\",\"routineExercises3878\",\"timeZone\",\"frequency\",\"preferredTrainer\",\"bench\",\"step\",\"preferences\",\"physicalLimits\",\"materials46\",\"capabilities46\"]")
        fingerprint = (fun(p: dynamic): dynamic {
            return global_JSON.stringify(changedFields.map((fun(k: dynamic): dynamic {
                return arrayOf<dynamic>(k, nullableOr(onPresent(p) { value3 -> value3[k] }) { null })
            })))
        })
        kinds = jsonValue("{\"train\":\"strength\",\"cardio\":\"cardio\",\"mobility\":\"recovery\",\"rest\":\"rest\"}")
        dateKey = (fun(): dynamic {
            return E.dateKey()
        })
        val existsDay: dynamic = (fun(date: dynamic): dynamic {
            return onPresent(global_state) { value4 -> onPresent(value4.cycles) { value5 -> value5.flatMap((fun(c: dynamic): dynamic {
                return jsOr(c.days) { js("[]") }
            })).some((fun(d: dynamic): dynamic {
                return (d.date === date)
            })) } }
        })
        val activeDone: dynamic = (fun(x: dynamic): dynamic {
            return (!truth((!truth(dynamicValue(jsOr(onPresent(x) { value8 -> value8["actual"] }) { js("[]") }).some((fun(a: dynamic): dynamic {
                return (onPresent(a) { value9 -> value9.done } === true)
            }))))))
        })
        val started: dynamic = (fun(l: dynamic): dynamic {
            return (!truth((!truth(jsAnd(l) { jsOr(jsOr(jsOr(l.complete) { l.manual }) { l.pain }) { onPresent(l.items) { value10 -> value10.some(activeDone) } } }))))
        })
        signature = (fun(x: dynamic): dynamic {
            return global_String(jsOr(onPresent(x) { value11 -> value11.id }) { "" })
        })
        freshItem = (fun(x: dynamic): dynamic {
            return jsObject { obj12 ->
                copyFields(obj12, clone(x))
                obj12["actual"] = global_Array.from(jsObject { obj13 ->
                    obj13["length"] = global_Math.max(1, global_Math.min(10, jsOr(global_Number(x.sets)) { 1 }))
                }, (fun(): dynamic {
                    return jsObject { obj14 ->
                        obj14["done"] = false
                        obj14["value"] = jsOr(global_Number(x.target)) { 1 }
                        obj14["kg"] = 0
                    }
                }))
            }
        })
        val beforeSubmit: dynamic = global_submitProfile
        global_submitProfile = (fun(event: dynamic): dynamic {
            val old: dynamic = onPresent(global_state) { value104 -> value104.profile }
            val oldFingerprint: dynamic = fingerprint(old)
            val date: dynamic = dateKey()
            val oldLog: dynamic = (if (truth(onPresent(global_state) { value105 -> onPresent(value105.logs) { value106 -> value106[date] } })) clone(global_state.logs[date]) else null)
            val oldDay: dynamic = onPresent(global_state) { value107 -> onPresent(value107.cycles) { value108 -> value108.flatMap((fun(c: dynamic): dynamic {
                return jsOr(c.days) { js("[]") }
            })).find((fun(x: dynamic): dynamic {
                return (x.date === date)
            })) } }
            val daySnapshot: dynamic = (if (truth(oldDay)) clone(oldDay) else null)
            val previousDay: dynamic = existsDay(date)
            val answer: dynamic = beforeSubmit(event)
            if (truth(jsOr(jsOr(jsOr((!truth(onPresent(event) { value110 -> value110.target }))) { (event.target.id !== "profileForm") }) { (!truth(onPresent(global_state) { value111 -> value111.profile })) }) { (!truth(old)) })) {
                return answer
            }
            if (truth((fingerprint(global_state.profile) === oldFingerprint))) {
                return answer
            }
            if (truth((js("typeof localStorage") !== "undefined"))) {
                try {
                    val disk: dynamic = global_JSON.parse(global_localStorage.getItem(global_KEY))
                    if (truth((fingerprint(onPresent(disk) { value112 -> value112.profile }) !== fingerprint(global_state.profile)))) {
                        return answer
                    }
                } catch (_: dynamic) {
                    return answer
                }
            }
            if (truth((!truth(previousDay)))) {
                try {
                    global_currentCycle()
                } catch (_: dynamic) {

                }
            }
            if (truth(oldLog)) {
                global_state.logs = jsOr(global_state.logs) { js("({})") }
                global_state.logs[date] = oldLog
            }
            val result: dynamic = regenerate(old, oldLog, daySnapshot)
            if (truth((result.status === "updated"))) {
                global_toast("Perfil guardado. Misión de hoy actualizada.")
            } else {
                if (truth((result.status === "review"))) {
                    global_toast("Perfil guardado. Revisa la misión de hoy antes de continuar.")
                } else {
                    if (truth((result.status === "finished-or-manual"))) {
                        global_toast("Perfil guardado. Se conserva tu misión finalizada o personalizada.")
                    }
                }
            }
            if (truth(jsAnd((js("typeof render") === "function")) { (result.status !== "no-cycle") })) {
                global_render()
            }
            return answer
        })
        val beforeFinish: dynamic = (if (truth((js("typeof finish") === "function"))) global_finish else null)
        if (truth(beforeFinish)) {
            global_finish = (fun(): dynamic {
                val d: dynamic = onPresent(global_state) { value119 -> onPresent(value119.cycles) { value120 -> value120.flatMap((fun(c: dynamic): dynamic {
                    return jsOr(c.days) { js("[]") }
                })).find((fun(x: dynamic): dynamic {
                    return (x.date === dateKey())
                })) } }
                if (truth(jsOr(onPresent(d) { value122 -> value122.blocked381 }) { onPresent(d) { value123 -> value123.blocked378 } })) {
                    global_toast("Primero revisa tu perfil: no hay una misión automática habilitada.")
                    return absent
                }
                return beforeFinish()
            })
        }
        val beforeWorkout: dynamic = (if (truth((js("typeof workout") === "function"))) global_workout else null)
        if (truth(beforeWorkout)) {
            global_workout = (fun(): dynamic {
                val body: dynamic = beforeWorkout()
                val log: dynamic = onPresent(global_state) { value126 -> onPresent(value126.logs) { value127 -> value127[dateKey()] } }
                val day: dynamic = onPresent(global_state) { value128 -> onPresent(value128.cycles) { value129 -> value129.flatMap((fun(c: dynamic): dynamic {
                    return jsOr(c.days) { js("[]") }
                })).find((fun(d: dynamic): dynamic {
                    return (d.date === dateKey())
                })) } }
                val extra: dynamic = (if (truth(onPresent(log) { value131 -> onPresent(value131.items) { value132 -> value132.some((fun(x: dynamic): dynamic {
                    return x.archivedProfile3829
                })) } })) "<div class=\"notice\" role=\"status\">Se conservaron las series realizadas con tu configuración anterior. Aparecen como registro y no debes repetirlas por el cambio de perfil.</div>" else "")
                val warning: dynamic = (if (truth(jsAnd(onPresent(day) { value133 -> value133.blocked381 }) { (!truth(onPresent(log) { value134 -> value134.complete })) })) jsAdd(jsAdd("<div class=\"notice warn\" role=\"alert\">", global_String(day.blocked381).replace(jsRegExp("[<>&\"']", "g"), "")), "</div>") else "")
                return jsAdd(jsAdd(warning, extra), body)
            })
        }
        root.HunterProfile3829 = global_Object.freeze(jsObject { obj137 ->
            obj137["version"] = "38.29"
            obj137["fingerprint"] = fingerprint
            obj137["reconcile"] = reconcile
            obj137["regenerate"] = regenerate
            obj137["started"] = started
        })
        return absent
    }))(global_window)
}
