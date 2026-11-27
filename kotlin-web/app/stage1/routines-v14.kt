@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installroutinesv14

import hunter.browser.*

@JsName("Engine") private external var global_Engine: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installRoutinesV14() {
    dynamicValue((fun(root: dynamic): dynamic {
        var target: dynamic = absent
        var estimate: dynamic = absent
        var adjust: dynamic = absent
        target = fun(e: dynamic, level: dynamic, family: dynamic): dynamic {
            if (truth(jsAnd((family === "power")) { (e.family === "power") })) {
                return dynamicValue(jsonValue("[4,5,6]"))[level]
            }
            if (truth((e.unit === "sec"))) {
                return (if (truth(jsOr((family === "mobility")) { (family === "balance") })) dynamicValue(jsonValue("[20,25,30]"))[level] else dynamicValue(jsonValue("[45,55,65]"))[level])
            }
            return (if (truth(jsOr((family === "mobility")) { (family === "balance") })) dynamicValue(jsonValue("[8,10,12]"))[level] else dynamicValue(jsonValue("[14,14,16]"))[level])
        }
        estimate = fun(items: dynamic): dynamic {
            var seconds: dynamic = 0
            if (true) {
                val iterator7: dynamic = iteratorOf(items.entries())
                while (true) {
                    val next8: dynamic = iterator7.next()
                    if (truth(next8.done)) break
                    var i: dynamic = absent
                    var x: dynamic = absent
                    val unpacked9: dynamic = fromIterable(next8.value)
                    i = unpacked9[0]
                    x = unpacked9[1]
                    val e: dynamic = global_Engine.byId(x.id)
                    val work: dynamic = (if (truth((e.unit === "sec"))) x.target else jsMultiply(x.target, 3))
                    run {
                        seconds = jsAdd(seconds, jsAdd(jsAdd(jsMultiply(jsMultiply(x.sets, work), (if (truth(e.sides)) 2 else 1)), jsMultiply(global_Math.max(0, jsSubtract(x.sets, 1)), x.rest)), (if (truth(jsLess(i, jsSubtract(items.length, 1)))) x.rest else 0)))
                    }
                }
            }
            return global_Math.ceil(jsDivide(seconds, 60))
        }
        adjust = fun(r: dynamic, p: dynamic): dynamic {
            return r.items.map((fun(x: dynamic): dynamic {
                val e: dynamic = global_Engine.byId(x.id)
                val gentle: dynamic = jsOr(global_Adapt.mode(p).reduced) { onPresent(global_GoalPlans.plans[p.finalTarget]) { value27 -> value27.gentle } }
                return jsObject { obj28 ->
                    copyFields(obj28, x)
                    obj28["sets"] = (if (truth(gentle)) 1 else x.sets)
                    obj28["target"] = (if (truth(gentle)) global_Math.min(x.target, (if (truth((e.unit === "sec"))) 15 else 6)) else global_Math.max((if (truth((e.family === "power"))) 2 else (if (truth((e.unit === "sec"))) 10 else 4)), global_Math.floor(jsMultiply(x.target, global_TrainingIntensity.scale(p)))))
                }
            }))
        }
        val families: dynamic = jsonValue("{\"strength\":{\"name\":\"Fuerza\",\"category\":\"Fuerza\",\"goals\":[\"build\",\"fitness\",\"athletic\",\"habit\"],\"body\":[\"squat\",\"kneepush\",\"bridge\",\"prone\",\"deadbug\",\"split\",\"pushup\"],\"db\":[\"goblet\",\"press\",\"row\",\"rdl\",\"curl\",\"dbsplit\",\"overheadpress\"]},\"hypertrophy\":{\"name\":\"Musculatura\",\"category\":\"Fuerza\",\"goals\":[\"build\"],\"body\":[\"squathold\",\"kneepush\",\"bridgehold\",\"scapular\",\"curlup\",\"singlebridge\",\"closepush\"],\"db\":[\"goblethold\",\"neutral\",\"bentrow\",\"dbbridge\",\"hammer\",\"lateral\",\"lyingtricep\"]},\"core\":{\"name\":\"Zona media\",\"category\":\"Fuerza\",\"goals\":[\"build\",\"movement\",\"habit\"],\"body\":[\"deadbug\",\"bird\",\"kneelingplank\",\"bridge\",\"sideplank\",\"plank\",\"bearhold\"],\"db\":[\"suitcase\",\"farmerhold\",\"dbbridge\",\"singlehinge\",\"farmerwalk\",\"dbmarch\"]},\"cardio\":{\"name\":\"Cardio\",\"category\":\"Cardio\",\"goals\":[\"fitness\",\"habit\",\"athletic\"],\"body\":[\"fastmarch\",\"stepjack\",\"boxinterval\",\"kneeliftcardio\",\"shadowcombo\",\"jog\",\"jumpjack\"]},\"agility\":{\"name\":\"Agilidad y coordinación\",\"category\":\"Cardio\",\"goals\":[\"athletic\",\"fitness\"],\"body\":[\"fastfeet\",\"shuffle\",\"stepcross\",\"skaterreach\",\"frontbackstep\",\"doubletouch\",\"highknees\"]},\"power\":{\"name\":\"Saltos y potencia\",\"category\":\"Cardio\",\"goals\":[\"athletic\"],\"body\":[\"lowhop\",\"linehop\",\"calf\",\"squat\",\"skaterhop\",\"squatjump\"]},\"mobility\":{\"name\":\"Movilidad\",\"category\":\"Movilidad\",\"goals\":[\"movement\",\"habit\",\"adapted\"],\"body\":[\"ankledrive\",\"hipcircle\",\"shoulder\",\"wallslide\",\"hipflex\",\"thoracic\",\"wrist\"]},\"balance\":{\"name\":\"Equilibrio y estabilidad\",\"category\":\"Movilidad\",\"goals\":[\"movement\",\"athletic\",\"adapted\"],\"body\":[\"tandem\",\"linewalk\",\"wallcalf\",\"standingabduct\",\"singlehinge\",\"balance\",\"birdcontrolled\"]},\"mixed\":{\"name\":\"Cuerpo completo\",\"category\":\"Mixta\",\"goals\":[\"habit\",\"fitness\",\"build\",\"athletic\"],\"body\":[\"squat\",\"kneepush\",\"bridge\",\"deadbug\",\"fastmarch\",\"skater\",\"pushup\"],\"db\":[\"goblet\",\"press\",\"row\",\"dbbridge\",\"dbmarch\",\"hammer\",\"farmerwalk\"]}}")
        val extensions: dynamic = jsonValue("{\"brief\":{\"name\":\"Breve\",\"count\":3,\"sets\":[2,2,3]},\"standard\":{\"name\":\"Completa\",\"count\":4,\"sets\":[3,3,4]},\"long\":{\"name\":\"Extendida\",\"count\":5,\"sets\":[3,4,4]}}")
        val names: dynamic = jsonValue("[\"Básico\",\"Intermedio\",\"Avanzado\"]")
        val keys: dynamic = jsonValue("[\"basic\",\"intermediate\",\"advanced\"]")
        val routines: dynamic = js("[]")
        if (true) {
            val iterator10: dynamic = iteratorOf(global_Object.entries(families))
            while (true) {
                val next11: dynamic = iterator10.next()
                if (truth(next11.done)) break
                var family: dynamic = absent
                var spec: dynamic = absent
                val unpacked12: dynamic = fromIterable(next11.value)
                family = unpacked12[0]
                spec = unpacked12[1]
                if (true) {
                    var level: dynamic = absent
                    level = 0
                    var firstIteration13 = true
                    while (true) {
                        if (!firstIteration13) {
                            run {
                                val previous14: dynamic = numeric(level)
                                level = jsAdd(previous14, 1)
                            }
                        }
                        firstIteration13 = false
                        if (!truth(jsLess(level, 3))) break
                        if (true) {
                            val iterator15: dynamic = iteratorOf(global_Object.entries(extensions))
                            while (true) {
                                val next16: dynamic = iterator15.next()
                                if (truth(next16.done)) break
                                var extension: dynamic = absent
                                var ex: dynamic = absent
                                val unpacked17: dynamic = fromIterable(next16.value)
                                extension = unpacked17[0]
                                ex = unpacked17[1]
                                if (true) {
                                    val iterator18: dynamic = iteratorOf((if (truth(spec.db)) jsonValue("[\"body\",\"db\"]") else jsonValue("[\"body\"]")))
                                    while (true) {
                                        val next19: dynamic = iterator18.next()
                                        if (truth(next19.done)) break
                                        var equipment: dynamic = absent
                                        equipment = next19.value
                                        val pool: dynamic = spec[equipment].map((fun(id: dynamic): dynamic {
                                            return global_Engine.byId(id)
                                        })).filter((fun(e: dynamic): dynamic {
                                            return jsAtMost(e.level, (if (truth((level === 0))) 1 else 2))
                                        }))
                                        val selected: dynamic = global_Array.from(jsObject { obj20 ->
                                            obj20["length"] = global_Math.min(ex.count, pool.length)
                                        }, (fun(_: dynamic, j: dynamic): dynamic {
                                            return pool[jsMod(jsAdd(jsAdd(j, (if (truth((extension === "long"))) 1 else 0)), level), pool.length)]
                                        }))
                                        val items: dynamic = selected.map((fun(e: dynamic): dynamic {
                                            return jsObject { obj21 ->
                                                obj21["id"] = e.id
                                                obj21["sets"] = (if (truth(jsOr((family === "mobility")) { (family === "balance") })) dynamicValue(jsonValue("[1,2,2]"))[level] else (if (truth(jsAnd((family === "power")) { (e.family === "power") })) dynamicValue(jsonValue("[2,3,4]"))[level] else ex.sets[level]))
                                                obj21["target"] = target(e, level, family)
                                                obj21["rest"] = (if (truth((family === "power"))) 90 else (if (truth(jsOr((family === "mobility")) { (family === "balance") })) 30 else (if (truth(jsOr((family === "cardio")) { (family === "agility") })) 60 else 90)))
                                            }
                                        }))
                                        val r: dynamic = jsObject { obj22 ->
                                            obj22["id"] = ("own14-" + textOf(family) + "-" + textOf(level) + "-" + textOf(extension) + "-" + textOf(equipment) + "")
                                            obj22["name"] = ("" + textOf(spec.name) + " · " + textOf(names[level]) + " · " + textOf(ex.name) + "" + textOf((if (truth((equipment === "db"))) " · mancuernas" else "")) + "")
                                            obj22["category"] = spec.category
                                            obj22["level"] = level
                                            obj22["items"] = items
                                            obj22["requirements"] = (if (truth((equipment === "db"))) jsonValue("[\"db\"]") else js("[]"))
                                            obj22["impact"] = (if (truth(items.some((fun(x: dynamic): dynamic {
                                                return (global_Engine.byId(x.id).impact === "Con saltos")
                                            })))) "Con saltos" else "Sin saltos")
                                            obj22["family"] = family
                                            obj22["goalGroups"] = spec.goals
                                            obj22["extension"] = extension
                                            obj22["planVersion"] = 14
                                            obj22["variant"] = "base"
                                            obj22["baseRoutineId"] = jsAdd("own14-", family)
                                            obj22["description"] = jsAdd("Rutina propia de Hunter System. ", (if (truth((family === "power"))) "Saltos de calidad con pausas largas; sustituye por una opción sin impacto si hay restricciones." else "Registra cada serie y el esfuerzo final; las revisiones son semanales."))
                                            obj22["minutes"] = estimate(items)
                                        }
                                        routines.push(r)
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        run { val target24: dynamic = global_Catalog.routines; invokeWith(target24.push, target24, jsArray { array23 ->
            appendAll(array23, routines)
        }) }
        root.OwnRoutines14 = jsObject { obj31 ->
            obj31["routines"] = routines
            obj31["families"] = families
            obj31["extensions"] = extensions
            obj31["estimate"] = estimate
            obj31["adjust"] = adjust
            obj31["names"] = names
            obj31["keys"] = keys
        }
        return absent
    }))(global_window)
}
