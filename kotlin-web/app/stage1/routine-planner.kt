@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installroutineplanner

import hunter.browser.*

@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Learning") private external var global_Learning: dynamic
@JsName("CreatorFeed") private external var global_CreatorFeed: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("OwnRoutines14") private external var global_OwnRoutines14: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("AdaptiveV9") private external var global_AdaptiveV9: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installRoutinePlanner() {
    dynamicValue((fun(root: dynamic): dynamic {
        var category: dynamic = absent
        var fitness: dynamic = absent
        var organize: dynamic = absent
        category = fun(d: dynamic): dynamic {
            return (if (truth((d.kind === "cardio"))) "Cardio" else (if (truth((d.kind === "recovery"))) "Movilidad" else "Fuerza"))
        }
        fitness = fun(r: dynamic, p: dynamic, d: dynamic, logs: dynamic, now: dynamic): dynamic {
            if (truth(jsAnd((r.category !== category(d))) { (!truth(jsAnd((category(d) === "Fuerza")) { (r.category === "Mixta") })) })) {
                return null
            }
            if (truth((!truth(global_Adapt.routineCompatible(r, p))))) {
                return null
            }
            if (truth(jsAnd((r.planVersion === 14)) { jsOr((r.level !== dynamicValue(jsonValue("[\"basic\",\"intermediate\",\"advanced\"]")).indexOf(p.difficulty))) { (!truth(r.goalGroups.includes(global_GoalGroups14.of(p)))) } })) {
                return null
            }
            val groups: dynamic = r.items.map((fun(x: dynamic): dynamic {
                return global_Engine.byId(x.id).group
            }))
            val focus: dynamic = jsOr(onPresent(global_GoalPlans.plans[p.finalTarget]) { value7 -> value7.focus }) { p.focus }
            val target: dynamic = (if (truth((focus === "upper"))) jsonValue("[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"]") else (if (truth((focus === "lower"))) jsonValue("[\"Piernas\",\"Cadera\"]") else (if (truth((focus === "core"))) jsonValue("[\"Core\"]") else js("[]"))))
            if (truth(jsAnd(target.length) { (!truth(groups.some((fun(g: dynamic): dynamic {
                return target.includes(g)
            })))) })) {
                return null
            }
            val prediction: dynamic = global_Learning.predict(global_Learning.samples(logs, now), r.items)
            if (truth(jsAnd(prediction) { jsAtLeast(prediction.effort, 8) })) {
                return null
            }
            return (if (truth(target.length)) jsDivide(groups.filter((fun(g: dynamic): dynamic {
                return target.includes(g)
            })).length, groups.length) else 1)
        }
        organize = fun(c: dynamic, p: dynamic, sourceRoutines: dynamic, ownRoutines: dynamic, logs: dynamic, now: dynamic): dynamic {
            val mode: dynamic = global_Adapt.mode(p)
            var count: dynamic = 0
            if (truth((p.trainingMode !== "routines"))) {
                return count
            }
            if (truth(jsOr(jsOr(jsOr((!truth(mode.auto))) { mode.reduced }) { onPresent(global_GoalPlans.plans[p.finalTarget]) { value10 -> value10.gentle } }) { dynamicValue(jsonValue("[\"reducir\",\"pausar\"]")).includes(onPresent(c.reviews) { value11 -> onPresent(value11.at((-numeric(1)))) { value12 -> value12.action } }) })) {
                if (true) {
                    val iterator13: dynamic = iteratorOf(c.days)
                    while (true) {
                        val next14: dynamic = iterator13.next()
                        if (truth(next14.done)) break
                        var d: dynamic = absent
                        d = next14.value
                        if (truth(d.items.length)) {
                            d.planningNote = "Rutina adaptada con dosis reducida según tus restricciones y registros."
                        }
                    }
                }
                return count
            }
            val preferred: dynamic = global_CreatorFeed.channels.find((fun(x: dynamic): dynamic {
                return (x.key === p.preferredTrainer)
            }))
            val sources: dynamic = sourceRoutines.filter(global_CreatorFeed.validateRoutine).filter((fun(r: dynamic): dynamic {
                return jsOr((!truth(preferred))) { (r.channelId === preferred.channelId) }
            })).map(global_CreatorFeed.asRoutine)
            if (true) {
                val iterator17: dynamic = iteratorOf(c.days.entries())
                while (true) {
                    val next18: dynamic = iterator17.next()
                    if (truth(next18.done)) break
                    var i: dynamic = absent
                    var d: dynamic = absent
                    val unpacked19: dynamic = fromIterable(next18.value)
                    i = unpacked19[0]
                    d = unpacked19[1]
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, now)) { (!truth(d.items.length)) }) { d.customized }) { d.routineId }) { global_Weekly.active(logs[d.date]) }) { onPresent(d.tasks) { value20 -> value20.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) } })) {
                        continue
                    }
                    var list: dynamic = (if (truth((p.preferredTrainer === "own"))) js("[]") else sources.map((fun(r: dynamic): dynamic {
                        return jsObject { obj24 ->
                            obj24["r"] = r
                            obj24["score"] = fitness(r, p, d, logs, now)
                        }
                    })).filter((fun(x: dynamic): dynamic {
                        return (x.score !== null)
                    })).sort((fun(a: dynamic, b: dynamic): dynamic {
                        return jsSubtract(b.score, a.score)
                    })))
                    val original: dynamic = jsMore(list.length, 0)
                    if (truth((!truth(list.length)))) {
                        list = ownRoutines.filter((fun(r: dynamic): dynamic {
                            return (!truth(r.locked))
                        })).map((fun(r: dynamic): dynamic {
                            return jsObject { obj28 ->
                                obj28["r"] = r
                                obj28["score"] = fitness(r, p, d, logs, now)
                            }
                        })).filter((fun(x: dynamic): dynamic {
                            return (x.score !== null)
                        })).sort((fun(a: dynamic, b: dynamic): dynamic {
                            return jsOr(jsOr(jsSubtract((if (truth((b.r.planVersion === 14))) 1 else 0), (if (truth((a.r.planVersion === 14))) 1 else 0))) { jsSubtract(b.score, a.score) }) { jsSubtract(a.r.level, b.r.level) }
                        }))
                    }
                    if (truth((!truth(list.length)))) {
                        d.planningNote = "Se mantiene la rutina adaptada: no hay una secuencia completa compatible en la fuente elegida."
                        continue
                    }
                    val r: dynamic = list[jsMod(jsAdd(c.index, i), global_Math.min(5, list.length))].r
                    d.items = (if (truth((r.planVersion === 14))) global_OwnRoutines14.adjust(r, p) else r.items.map((fun(x: dynamic): dynamic {
                        return (if (truth(original)) jsObject { obj33 ->
                            copyFields(obj33, x)
                        } else jsObject { obj34 ->
                            copyFields(obj34, x)
                            copyFields(obj34, global_TrainingIntensity.dose(global_Engine.byId(x.id), p, d.kind))
                        })
                    })))
                    if (truth(jsAnd((!truth(original))) { (global_TrainingIntensity.scale(p) === 1) })) {
                        if (true) {
                            val iterator35: dynamic = iteratorOf(d.items)
                            while (true) {
                                val next36: dynamic = iterator35.next()
                                if (truth(next36.done)) break
                                var x: dynamic = absent
                                x = next36.value
                                val next: dynamic = global_AdaptiveV9.recommend(logs, x, p, now)
                                if (truth(jsMore(next.target, x.target))) {
                                    x.target = next.target
                                }
                            }
                        }
                    }
                    d.kind = (if (truth((r.category === "Mixta"))) "mixed" else d.kind)
                    d.title = r.name
                    d.tasks = js("[]")
                    d.autoTemplateId = r.id
                    if (truth(original)) {
                        d.routineId = r.id
                        d.sourceSequence = global_JSON.parse(global_JSON.stringify(r.sourceSequence))
                        d.creatorAssigned = true
                    }
                    d.planningNote = (if (truth(original)) "Secuencia de la fuente elegida, sin cambiar sus bloques ni cantidades." else (if (truth((p.preferredTrainer === "own"))) "Rutina del catálogo ajustada al nivel y al objetivo." else "No hay una secuencia completa comprobada y compatible del entrenador elegido. Se usa una rutina propia adaptada."))
                    removeKey(logs, d.date)
                    run {
                        val previous55: dynamic = numeric(count)
                        count = jsAdd(previous55, 1)
                    }
                }
            }
            return count
        }
        root.RoutinePlanner = jsObject { obj58 ->
            obj58["organize"] = organize
            obj58["fitness"] = fitness
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
