@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installschedule

import hunter.browser.*

@JsName("Date") private external var global_Date: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installSchedule() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var valid: dynamic = absent
        var defaults: dynamic = absent
        var pool: dynamic = absent
        var items: dynamic = absent
        valid = fun(p: dynamic): dynamic {
            return jsAnd(jsAnd(global_Array.isArray(p.weekPlan)) { (p.weekPlan.length === 7) }) { p.weekPlan.every((fun(x: dynamic): dynamic {
                return dynamicValue(jsonValue("[\"train\",\"cardio\",\"rest\",\"mobility\"]")).includes(x)
            })) }
        }
        defaults = fun(p: dynamic): dynamic {
            return (if (truth(jsAnd(onPresent(p) { value7 -> value7.weekPlan }) { valid(p) })) p.weekPlan else arrayOf<dynamic>("rest", "train", "rest", "train", "rest", (if (truth((onPresent(p) { value8 -> value8.frequency } === 3))) "train" else "rest"), "rest"))
        }
        pool = fun(p: dynamic, category: dynamic): dynamic {
            return E.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd((e.variant === "base")) { (!truth(e.sourceOnly)) }) { (e.category === category) }) { global_Adapt.allowed(e, p) }
            }))
        }
        items = fun(p: dynamic, kind: dynamic, rotation: dynamic, carry: dynamic): dynamic {
            var list: dynamic = pool(p, (if (truth((kind === "strength"))) "Fuerza" else (if (truth((kind === "cardio"))) "Cardio" else "Movilidad")))
            if (truth((kind === "strength"))) {
                val groups: dynamic = (if (truth((p.focus === "upper"))) jsonValue("[[\"Pecho\"],[\"Espalda\"],[\"Hombros\"],[\"Brazos\"]]") else (if (truth((p.focus === "lower"))) jsonValue("[[\"Piernas\"],[\"Cadera\"],[\"Piernas\"],[\"Core\"]]") else (if (truth((p.focus === "core"))) jsonValue("[[\"Core\"],[\"Cadera\"],[\"Espalda\"]]") else jsonValue("[[\"Piernas\"],[\"Pecho\"],[\"Espalda\"],[\"Cadera\"],[\"Core\",\"Brazos\"]]"))))
                val used: dynamic = jsConstruct(global_Set, js("[]"))
                list = groups.map((fun(g: dynamic, i: dynamic): dynamic {
                    var a: dynamic = list.filter((fun(e: dynamic): dynamic {
                        return jsAnd(g.includes(e.group)) { (!truth(used.has(e.id))) }
                    }))
                    if (truth(jsAnd(g.includes("Piernas")) { (i === 0) })) {
                        val compound: dynamic = a.filter((fun(e: dynamic): dynamic {
                            return e.kneeBend
                        }))
                        if (truth(compound.length)) {
                            a = compound
                        }
                    }
                    if (truth((p.equipment === "db"))) {
                        val db: dynamic = a.filter((fun(e: dynamic): dynamic {
                            return (e.equipment === "db")
                        }))
                        if (truth(db.length)) {
                            a = db
                        }
                    }
                    val e: dynamic = a[jsMod(jsAdd(rotation, i), global_Math.max(1, a.length))]
                    if (truth(e)) {
                        used.add(e.id)
                    }
                    return e
                })).filter(global_Boolean)
            } else {
                list = (if (truth(list.length)) global_Array.from(jsObject { obj13 ->
                    obj13["length"] = global_Math.min(4, list.length)
                }, (fun(_: dynamic, i: dynamic): dynamic {
                    return list[jsMod(jsAdd(rotation, i), list.length)]
                })) else js("[]"))
            }
            val reduced: dynamic = global_Adapt.mode(p).reduced
            val cap: dynamic = (if (truth((p.minutes === 10))) 2 else (if (truth((p.minutes === 20))) 3 else 5))
            val count: dynamic = (if (truth((kind === "recovery"))) 3 else cap)
            return list.slice(0, count).map((fun(e: dynamic): dynamic {
                return jsObject { obj14 ->
                    obj14["id"] = e.id
                    obj14["sets"] = (if (truth(jsOr((kind === "recovery")) { reduced })) 1 else (if (truth(jsAnd((p.difficulty === "advanced")) { jsAtLeast(p.minutes, 30) })) 3 else 2))
                    obj14["target"] = (if (truth((e.unit === "sec"))) (if (truth(reduced)) 15 else (if (truth((kind === "cardio"))) 30 else 20)) else (if (truth(reduced)) 6 else (if (truth((kind === "recovery"))) 6 else global_Math.min(15, jsOr(carry) { (if (truth((p.goal === "muscle"))) 10 else 8) }))))
                    obj14["rest"] = (if (truth((kind === "recovery"))) 20 else global_Math.max((if (truth((p.difficulty === "advanced"))) 60 else 45), jsOr(p.restSeconds) { 90 }))
                }
            }))
        }
        E = root.Engine
        val previous: dynamic = E.makeCycle
        val dayNames: dynamic = jsonValue("[\"Domingo\",\"Lunes\",\"Martes\",\"Miércoles\",\"Jueves\",\"Viernes\",\"Sábado\"]")
        val weekday: dynamic = (fun(date: dynamic): dynamic {
            return jsConstruct(global_Date, arrayOf<dynamic>(jsAdd(date, "T12:00:00"))).getDay()
        })
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, logs: dynamic): dynamic {
            val c: dynamic = previous(p, start, index, last, logs)
            if (truth((!truth(valid(p))))) {
                return c
            }
            val mode: dynamic = global_Adapt.mode(p)
            if (truth((!truth(mode.auto)))) {
                c.scheduleVersion = 376
                return c
            }
            val plan: dynamic = p.weekPlan
            var lastStrength: dynamic = jsAnd(jsAnd((!truth((!truth(last))))) { (onPresent(last.days) { value21 -> onPresent(value21.at((-numeric(1)))) { value22 -> value22.kind } } === "strength") }) { (E.diff(start, last.days.at((-numeric(1))).date) === 1) }
            val carry: dynamic = onPresent(last) { value23 -> onPresent(value23.days) { value24 -> onPresent(value24.filter((fun(d: dynamic): dynamic {
                return (d.kind === "strength")
            })).at((-numeric(1)))) { value26 -> onPresent(value26.items) { value27 -> onPresent(value27.find((fun(x: dynamic): dynamic {
                return (onPresent(E.byId(x.id)) { value28 -> value28.unit } === "reps")
            }))) { value29 -> value29.target } } } } }
            c.days = c.days.map((fun(d: dynamic, i: dynamic): dynamic {
                val chosen: dynamic = plan[weekday(d.date)]
                val kind: dynamic = (if (truth((chosen === "rest"))) "rest" else (if (truth((chosen === "mobility"))) "recovery" else (if (truth((chosen === "cardio"))) "cardio" else "strength")))
                lastStrength = (kind === "strength")
                val exerciseItems: dynamic = (if (truth((kind === "rest"))) js("[]") else items(p, kind, jsAdd(jsAdd(jsMultiply(index, 7), jsMultiply(global_Math.floor(jsDivide(i, 7)), 3)), i), carry))
                val title: dynamic = (if (truth((chosen === "rest"))) "Descanso elegido" else (if (truth((chosen === "mobility"))) "Movilidad elegida" else (if (truth((kind === "cardio"))) "Cardio elegido" else (if (truth((kind === "strength"))) "Fuerza elegida" else global_Adapt.goals[p.goal]))))
                return jsObject { obj32 ->
                    copyFields(obj32, d)
                    obj32["kind"] = (if (truth(exerciseItems.length)) kind else "rest")
                    obj32["title"] = title
                    obj32["items"] = exerciseItems
                    obj32["tasks"] = (if (truth(exerciseItems.length)) js("[]") else arrayOf<dynamic>(jsObject { obj33 ->
                        obj33["id"] = (if (truth((chosen === "rest"))) "rest" else "review")
                        obj33["text"] = (if (truth((chosen === "rest"))) "Completar mi día de descanso" else "Revisar alternativas compatibles")
                        obj33["done"] = false
                    }))
                    obj32["scheduleChoice"] = chosen
                }
            }))
            c.scheduleVersion = 376
            c.profile = global_JSON.parse(global_JSON.stringify(p))
            run {
                val target38: dynamic = c
                target38.decision = jsAdd(target38.decision, " Calendario personal: fuerza, cardio, movilidad y descanso elegidos por el usuario. Las sesiones de fuerza consecutivas usan reducción de volumen.")
            }
            return c
        })
        root.Schedule = jsObject { obj42 ->
            obj42["weekday"] = weekday
            obj42["dayNames"] = dayNames
            obj42["valid"] = valid
            obj42["defaults"] = defaults
            obj42["items"] = items
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
