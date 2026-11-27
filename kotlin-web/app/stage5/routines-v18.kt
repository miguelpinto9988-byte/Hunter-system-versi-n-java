@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installroutinesv18

import hunter.browser.*

@JsName("Object") private external var global_Object: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("HunterCatalog46") private external var global_HunterCatalog46: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("AdaptiveV9") private external var global_AdaptiveV9: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("RoutinePlanner") private external var global_RoutinePlanner: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("RoutineSequence") private external var global_RoutineSequence: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installRoutinesV18() {
    dynamicValue((fun(root: dynamic): dynamic {
        var keys: dynamic = absent
        var lengths: dynamic = absent
        var gentle: dynamic = absent
        var dose: dynamic = absent
        var cap: dynamic = absent
        var workSeconds: dynamic = absent
        var estimate: dynamic = absent
        var adjust: dynamic = absent
        dose = fun(e: dynamic, p: dynamic, kind: dynamic): dynamic {
            if (truth(onPresent(e) { value6 -> value6.doseCap46 })) {
                val c: dynamic = e.doseCap46
                return global_HunterCatalog46.cap(e, p, jsObject { obj7 ->
                    obj7["id"] = e.id
                    obj7["sets"] = 2
                    obj7["target"] = c.initial
                    obj7["rest"] = c.rest
                })
            }
            val v: dynamic = jsOr(global_TrainingIntensity.levels[p.difficulty]) { global_TrainingIntensity.levels.basic }
            val scale: dynamic = global_TrainingIntensity.scale(p)
            val beginner: dynamic = (p.experience === "beginner")
            if (truth(jsOr(gentle(p)) { (kind === "recovery") })) {
                return jsObject { obj8 ->
                    obj8["id"] = e.id
                    obj8["sets"] = 1
                    obj8["target"] = (if (truth((e.unit === "sec"))) 15 else 6)
                    obj8["rest"] = (if (truth((kind === "recovery"))) 30 else 90)
                }
            }
            if (truth((e.family === "power"))) {
                return jsObject { obj9 ->
                    obj9["id"] = e.id
                    obj9["sets"] = (if (truth(beginner)) 2 else jsOr(dynamicValue(jsonValue("[2,3,4]"))[keys.indexOf(p.difficulty)]) { 2 })
                    obj9["target"] = global_Math.max(2, global_Math.floor(jsMultiply((if (truth(beginner)) 3 else jsOr(dynamicValue(jsonValue("[4,5,6]"))[keys.indexOf(p.difficulty)]) { 4 }), scale)))
                    obj9["rest"] = global_Math.max(90, jsOr(p.restSeconds) { 90 })
                }
            }
            val cardio: dynamic = (kind === "cardio")
            val sets: dynamic = (if (truth(beginner)) 2 else (if (truth(cardio)) v.cardioSets else v.sets))
            val target: dynamic = (if (truth(beginner)) (if (truth((e.unit === "sec"))) 25 else 10) else (if (truth((e.unit === "sec"))) (if (truth(cardio)) v.cardioSeconds else v.seconds) else v.reps))
            return jsObject { obj10 ->
                obj10["id"] = e.id
                obj10["sets"] = sets
                obj10["target"] = global_Math.max((if (truth((e.unit === "sec"))) 10 else 4), global_Math.floor(jsMultiply(target, scale)))
                obj10["rest"] = global_Math.max((if (truth(cardio)) 45 else 90), jsOr(p.restSeconds) { 90 })
            }
        }
        cap = fun(e: dynamic, p: dynamic): dynamic {
            return (if (truth((e.family === "power"))) 8 else (if (truth((e.unit === "sec"))) 90 else (if (truth((p.experience === "beginner"))) 14 else dynamicValue(jsonValue("[24,30,36]"))[global_Math.max(0, keys.indexOf(p.difficulty))])))
        }
        workSeconds = fun(x: dynamic): dynamic {
            val e: dynamic = global_Engine.byId(x.id)
            return jsMultiply((if (truth((global_Engine.unitOf(x) === "sec"))) x.target else jsMultiply(x.target, jsOr(e.tempoSeconds18) { 4 })), (if (truth(jsAnd(global_Engine.sidesOf(x)) { (!truth(x.side)) })) 2 else 1))
        }
        estimate = fun(items: dynamic): dynamic {
            return global_Math.ceil(jsDivide(jsAdd(120, items.reduce((fun(n: dynamic, x: dynamic, i: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd(n, jsMultiply(x.sets, workSeconds(x))), jsMultiply(global_Math.max(0, jsSubtract(x.sets, 1)), x.rest)), (if (truth(jsLess(i, jsSubtract(items.length, 1)))) x.rest else 0))
            }), 0)), 60))
        }
        adjust = fun(r: dynamic, p: dynamic): dynamic {
            return r.items.map((fun(x: dynamic): dynamic {
                val e: dynamic = global_Engine.byId(x.id)
                val v: dynamic = dose(e, p, (if (truth((r.category === "Cardio"))) "cardio" else (if (truth((r.category === "Movilidad"))) "recovery" else "strength")))
                return jsObject { obj49 ->
                    copyFields(obj49, x)
                    copyFields(obj49, v)
                    obj49["sets"] = (if (truth(gentle(p))) 1 else global_Math.max(1, global_Math.ceil(jsMultiply(v.sets, lengths[r.extension].ratio))))
                }
            }))
        }
        val names: dynamic = jsonValue("[\"Básico\",\"Intermedio\",\"Avanzado\"]")
        keys = jsonValue("[\"basic\",\"intermediate\",\"advanced\"]")
        val families: dynamic = jsonValue("{\"full\":{\"name\":\"Cuerpo completo\",\"category\":\"Mixta\",\"goals\":[\"build\",\"fitness\",\"habit\"],\"body\":[\"squat\",\"kneepush\",\"bridge\",\"deadbug\",\"fastmarch\",\"split\"],\"db\":[\"goblet\",\"press\",\"row\",\"dbbridge\",\"dbmarch\",\"hammer\"]},\"push\":{\"name\":\"Empuje y hombros\",\"category\":\"Fuerza\",\"goals\":[\"build\"],\"body\":[\"incline\",\"kneepush\",\"wallslide\",\"scapular\",\"closepush\"],\"db\":[\"overheadpress\",\"benchpress\",\"neutral\",\"lyingtricep\",\"lateral\",\"press\"]},\"pull\":{\"name\":\"Espalda y brazos\",\"category\":\"Fuerza\",\"goals\":[\"build\"],\"body\":[\"prone\",\"reverse\",\"scapular\",\"towelrow\",\"bird\"],\"db\":[\"row\",\"curl\",\"bentrow\",\"hammer\",\"reversefly\",\"concentration\"]},\"legs\":{\"name\":\"Piernas y glúteos\",\"category\":\"Fuerza\",\"goals\":[\"build\",\"athletic\"],\"body\":[\"squat\",\"split\",\"bridge\",\"calf\",\"reverselunge\",\"singlebridge\"],\"db\":[\"goblet\",\"rdl\",\"dbbridge\",\"dbcalf\",\"dbsumo\",\"dblunge\"]},\"core\":{\"name\":\"Zona media\",\"category\":\"Fuerza\",\"goals\":[\"build\",\"movement\",\"habit\"],\"body\":[\"deadbug\",\"bird\",\"kneelingplank\",\"sideplank\",\"curlup\",\"plank\"],\"db\":[\"suitcase\",\"farmerhold\",\"dbbridge\",\"farmerwalk\",\"dbmarch\"]},\"control\":{\"name\":\"Cadencia y control\",\"category\":\"Fuerza\",\"goals\":[\"build\",\"movement\",\"habit\"],\"body\":[\"squat-control18\",\"kneepush-control18\",\"bridge-control18\",\"deadbug-control18\",\"calf-control18\"],\"db\":[\"goblet-control18\",\"press-control18\",\"row-control18\",\"rdl-control18\",\"curl-control18\",\"hammer-control18\"]},\"cardio\":{\"name\":\"Cardio sin saltos\",\"category\":\"Cardio\",\"goals\":[\"fitness\",\"habit\"],\"body\":[\"fastmarch\",\"stepjack\",\"kneeliftcardio\",\"box\",\"sidestep\",\"heelback\"]},\"intervals\":{\"name\":\"Intervalos de cardio\",\"category\":\"Cardio\",\"goals\":[\"fitness\",\"athletic\"],\"body\":[\"stepjack\",\"shadowcombo\",\"fastfeet\",\"skater\",\"jog\",\"highknees\"]},\"agility\":{\"name\":\"Agilidad y coordinación\",\"category\":\"Cardio\",\"goals\":[\"athletic\",\"fitness\"],\"body\":[\"shuffle\",\"stepcross\",\"frontbackstep\",\"skaterreach\",\"doubletouch\",\"fastfeet\"]},\"power\":{\"name\":\"Saltos y potencia\",\"category\":\"Cardio\",\"goals\":[\"athletic\"],\"body\":[\"lowhop\",\"linehop\",\"calf\",\"squat\",\"skaterhop\",\"squatjump\"]},\"mobility\":{\"name\":\"Movilidad de cuerpo completo\",\"category\":\"Movilidad\",\"goals\":[\"movement\",\"adapted\",\"habit\"],\"body\":[\"ankledrive\",\"hipcircle\",\"shoulder\",\"wallslide\",\"hipflex\",\"thoracic\"]},\"balance\":{\"name\":\"Equilibrio y estabilidad\",\"category\":\"Movilidad\",\"goals\":[\"movement\",\"athletic\",\"adapted\"],\"body\":[\"tandem\",\"linewalk\",\"standingabduct\",\"singlehinge\",\"wallcalf\",\"balance\"]},\"seated\":{\"name\":\"Movimiento sentado\",\"category\":\"Movilidad\",\"goals\":[\"adapted\",\"movement\",\"habit\"],\"body\":[\"seatedmarch\",\"seatedcalf\",\"legextend\",\"ankle\",\"shoulder\",\"scapular\"]}}")
        lengths = jsonValue("{\"brief\":{\"name\":\"Breve\",\"count\":3,\"ratio\":0.5},\"standard\":{\"name\":\"Completa\",\"count\":4,\"ratio\":1},\"long\":{\"name\":\"Extendida\",\"count\":5,\"ratio\":1}}")
        global_Object.assign(global_TrainingIntensity.levels, jsonValue("{\"basic\":{\"sets\":4,\"reps\":16,\"seconds\":40,\"cardioSets\":3,\"cardioSeconds\":40},\"intermediate\":{\"sets\":4,\"reps\":20,\"seconds\":50,\"cardioSets\":4,\"cardioSeconds\":45},\"advanced\":{\"sets\":4,\"reps\":24,\"seconds\":60,\"cardioSets\":4,\"cardioSeconds\":50}}"))
        gentle = (fun(p: dynamic): dynamic {
            return jsOr(global_Adapt.mode(p).reduced) { onPresent(global_GoalPlans.plans[p.finalTarget]) { value3 -> value3.gentle } }
        })
        global_TrainingIntensity.dose = dose
        global_AdaptiveV9.recommend = (fun(logs: dynamic, item: dynamic, p: dynamic, now: dynamic): dynamic {
            val e: dynamic = global_Engine.byId(item.id)
            val rows: dynamic = global_AdaptiveV9.evidence(logs, item.id, now).filter((fun(r: dynamic): dynamic {
                return jsAnd((global_Engine.unitOf(r.item) === global_Engine.unitOf(item))) { (jsOr(r.item.side) { "both" } === jsOr(item.side) { "both" }) }
            }))
            val recent: dynamic = rows.slice(0, 3)
            val full: dynamic = jsAnd((recent.length === 3)) { recent.every((fun(r: dynamic): dynamic {
                return jsAnd(jsAtLeast(onPresent(r.item["actual"]) { value19 -> value19.length }, global_Math.max(r.item.sets, item.sets))) { r.item["actual"].every((fun(a: dynamic): dynamic {
                    return jsAnd(a.done) { jsAtLeast(global_Number(a["value"]), global_Math.max(r.item.target, item.target)) }
                })) }
            })) }
            var target: dynamic = item.target
            var action: dynamic = "mantener"
            var reason: dynamic = "Reúne tres registros completos y comparables de este ejercicio."
            if (truth(jsOr(jsOr((!truth(global_Adapt.mode(p).auto))) { gentle(p) }) { jsLess(global_TrainingIntensity.scale(p), 1) })) {
                reason = "Se conserva la adaptación y la reducción elegida."
            } else {
                if (truth(jsAnd(full) { recent.every((fun(r: dynamic): dynamic {
                    return jsAtMost(r.rpe, 7)
                })) })) {
                    val delta: dynamic = (if (truth((e.unit === "sec"))) global_Math.min(5, global_Math.max(1, global_Math.floor(jsMultiply(target, 0.1)))) else 1)
                    target = global_Math.max(item.target, global_Math.min(cap(e, p), jsAdd(target, delta)))
                    action = (if (truth(jsMore(target, item.target))) "aumentar" else "mantener")
                    reason = (if (truth((action === "aumentar"))) "Tres registros completos con esfuerzo de 7/10 o menos: aumenta una sola variable." else "Tope de este nivel: conserva la dosis y revisa la técnica o una variante desde Perfil.")
                }
            }
            return jsObject { obj20 ->
                obj20["target"] = target
                obj20["action"] = action
                obj20["reason"] = reason
                obj20["samples"] = rows.length
            }
        })
        val oldItems: dynamic = global_Schedule.items
        global_Schedule.items = (fun(p: dynamic, kind: dynamic, rotation: dynamic, carry: dynamic): dynamic {
            val items: dynamic = oldItems(p, kind, rotation, carry)
            if (truth((!truth(global_Adapt.mode(p).auto)))) {
                return items
            }
            return items.map((fun(x: dynamic): dynamic {
                return jsObject { obj25 ->
                    copyFields(obj25, x)
                    copyFields(obj25, dose(global_Engine.byId(x.id), p, kind))
                }
            }))
        })
        val make: dynamic = global_Engine.makeCycle
        global_Engine.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg30: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg30) { js("({})") }
            val c: dynamic = make(p, start, index, last, logs)
            c.engineVersion18 = 18
            if (truth((!truth(global_Adapt.mode(p).auto)))) {
                return c
            }
            if (true) {
                val iterator33: dynamic = iteratorOf(c.days)
                while (true) {
                    val next34: dynamic = iterator33.next()
                    if (truth(next34.done)) break
                    var d: dynamic = absent
                    d = next34.value
                    if (true) {
                        val iterator35: dynamic = iteratorOf(d.items)
                        while (true) {
                            val next36: dynamic = iterator35.next()
                            if (truth(next36.done)) break
                            var x: dynamic = absent
                            x = next36.value
                            val e: dynamic = global_Engine.byId(x.id)
                            val v: dynamic = dose(e, p, d.kind)
                            val prior: dynamic = onPresent(last) { value37 -> value37.days.flatMap((fun(y: dynamic): dynamic {
                                return y.items
                            })).filter((fun(y: dynamic): dynamic {
                                return jsAnd((y.id === x.id)) { (global_Engine.unitOf(y) === global_Engine.unitOf(x)) }
                            })).at((-numeric(1))) }
                            if (truth(jsAnd(jsAnd(prior) { (!truth(gentle(p))) }) { (global_TrainingIntensity.scale(p) === 1) })) {
                                v.target = global_Math.max(v.target, global_Math.min(prior.target, cap(e, p)))
                            }
                            global_Object.assign(x, v)
                        }
                    }
                }
            }
            return c
        })
        val routines: dynamic = js("[]")
        if (true) {
            val iterator50: dynamic = iteratorOf(global_Object.entries(families))
            while (true) {
                val next51: dynamic = iterator50.next()
                if (truth(next51.done)) break
                var family: dynamic = absent
                var spec: dynamic = absent
                val unpacked52: dynamic = fromIterable(next51.value)
                family = unpacked52[0]
                spec = unpacked52[1]
                if (true) {
                    var level: dynamic = absent
                    level = 0
                    var firstIteration53 = true
                    while (true) {
                        if (!firstIteration53) {
                            run {
                                val previous54: dynamic = numeric(level)
                                level = jsAdd(previous54, 1)
                            }
                        }
                        firstIteration53 = false
                        if (!truth(jsLess(level, 3))) break
                        if (true) {
                            val iterator55: dynamic = iteratorOf(global_Object.entries(lengths))
                            while (true) {
                                val next56: dynamic = iterator55.next()
                                if (truth(next56.done)) break
                                var extension: dynamic = absent
                                var ex: dynamic = absent
                                val unpacked57: dynamic = fromIterable(next56.value)
                                extension = unpacked57[0]
                                ex = unpacked57[1]
                                if (true) {
                                    val iterator58: dynamic = iteratorOf((if (truth(spec.db)) jsonValue("[\"body\",\"db\"]") else jsonValue("[\"body\"]")))
                                    while (true) {
                                        val next59: dynamic = iterator58.next()
                                        if (truth(next59.done)) break
                                        var equipment: dynamic = absent
                                        equipment = next59.value
                                        val pool: dynamic = spec[equipment].map((fun(id: dynamic): dynamic {
                                            return global_Engine.byId(id)
                                        })).filter(global_Boolean).filter((fun(e: dynamic): dynamic {
                                            return jsAtMost(e.level, (if (truth((level === 0))) 1 else 2))
                                        }))
                                        val selected: dynamic = pool.slice(0, ex.count)
                                        val p: dynamic = jsObject { obj60 ->
                                            obj60["difficulty"] = keys[level]
                                            obj60["experience"] = "regular"
                                            obj60["health"] = jsonValue("{\"pain\":\"none\"}")
                                            obj60["injuries"] = js("[]")
                                            obj60["restrictions"] = js("({})")
                                            obj60["trainingScale"] = 1
                                        }
                                        val items: dynamic = selected.map((fun(e: dynamic): dynamic {
                                            val x: dynamic = dose(e, p, (if (truth((spec.category === "Cardio"))) "cardio" else (if (truth((spec.category === "Movilidad"))) "recovery" else "strength")))
                                            x.sets = global_Math.max(1, global_Math.ceil(jsMultiply(x.sets, ex.ratio)))
                                            return x
                                        }))
                                        val requirements: dynamic = jsArray { array63 ->
                                            appendAll(array63, jsConstruct(global_Set, arrayOf<dynamic>(selected.flatMap((fun(e: dynamic): dynamic {
                                                return jsOr(e.requirements) { js("[]") }
                                            })).concat((if (truth((equipment === "db"))) jsonValue("[\"db\"]") else js("[]"))))))
                                        }
                                        val r: dynamic = jsObject { obj64 ->
                                            obj64["id"] = ("own18-" + textOf(family) + "-" + textOf(level) + "-" + textOf(extension) + "-" + textOf(equipment) + "")
                                            obj64["name"] = ("" + textOf(spec.name) + " · " + textOf(names[level]) + " · " + textOf(ex.name) + "" + textOf((if (truth((equipment === "db"))) " · mancuernas" else "")) + "")
                                            obj64["category"] = spec.category
                                            obj64["level"] = level
                                            obj64["items"] = items
                                            obj64["requirements"] = requirements
                                            obj64["impact"] = (if (truth(selected.some((fun(e: dynamic): dynamic {
                                                return (e.impact === "Con saltos")
                                            })))) "Con saltos" else "Sin saltos")
                                            obj64["family"] = family
                                            obj64["goalGroups"] = spec.goals
                                            obj64["extension"] = extension
                                            obj64["planVersion"] = 18
                                            obj64["own18"] = true
                                            obj64["description"] = "Sesión original de Hunter System. Preparación, series, descansos y vuelta a la calma con reloj. Cantidades adaptadas al perfil y revisión semanal."
                                            obj64["minutes"] = estimate(items)
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
        global_Catalog.routines = routines
        global_Catalog.baseRoutines = routines.length
        global_Catalog.baseExercises = global_Engine.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).length
        global_RoutinePlanner.organize = (fun(c: dynamic, p: dynamic, source: dynamic, own: dynamic, logs: dynamic, now: dynamic): dynamic {
            if (truth(jsOr(jsOr((p.trainingMode !== "routines")) { (!truth(global_Adapt.mode(p).auto)) }) { (onPresent(c.reviews) { value75 -> onPresent(value75.at((-numeric(1)))) { value76 -> value76.action } } === "pausar") })) {
                return 0
            }
            var count: dynamic = 0
            val adjusted: dynamic = global_GoalPlans.adjusted(p)
            val level: dynamic = global_Math.max(0, keys.indexOf(p.difficulty))
            val group: dynamic = global_GoalGroups14.of(p)
            if (true) {
                val iterator77: dynamic = iteratorOf(c.days.entries())
                while (true) {
                    val next78: dynamic = iterator77.next()
                    if (truth(next78.done)) break
                    var i: dynamic = absent
                    var d: dynamic = absent
                    val unpacked79: dynamic = fromIterable(next78.value)
                    i = unpacked79[0]
                    d = unpacked79[1]
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, now)) { (!truth(d.items.length)) }) { d.customized }) { d.routineId }) { global_Weekly.active(logs[d.date]) }) { onPresent(d.tasks) { value80 -> value80.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) } })) {
                        continue
                    }
                    val category: dynamic = (if (truth((d.kind === "cardio"))) "Cardio" else (if (truth((d.kind === "recovery"))) "Movilidad" else "Fuerza"))
                    var list: dynamic = routines.filter((fun(r: dynamic): dynamic {
                        return jsAnd(jsAnd(jsAnd(jsAnd((r.level === level)) { (r.extension === "standard") }) { jsOr((r.category === category)) { jsAnd((category === "Fuerza")) { (r.category === "Mixta") } } }) { r.goalGroups.includes(group) }) { global_Adapt.routineCompatible(r, adjusted) }
                    }))
                    if (truth((!truth(list.length)))) {
                        list = routines.filter((fun(r: dynamic): dynamic {
                            return jsAnd(jsAnd(jsAnd((r.level === level)) { (r.extension === "brief") }) { jsOr((r.category === category)) { jsAnd((category === "Fuerza")) { (r.category === "Mixta") } } }) { global_Adapt.routineCompatible(r, adjusted) }
                        }))
                    }
                    if (truth((!truth(list.length)))) {
                        d.own18 = true
                        d.title = (if (truth(gentle(p))) "Rutina adaptada · movimientos compatibles" else jsAdd("Rutina personal · ", category))
                        d.planningNote = "Secuencia propia con los movimientos que pasan tus filtros e indicaciones."
                        run {
                            val previous87: dynamic = numeric(count)
                            count = jsAdd(previous87, 1)
                        }
                        continue
                    }
                    val focus: dynamic = adjusted.focus
                    val targets: dynamic = (if (truth((focus === "upper"))) jsonValue("[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"]") else (if (truth((focus === "lower"))) jsonValue("[\"Piernas\",\"Cadera\"]") else (if (truth((focus === "core"))) jsonValue("[\"Core\",\"Abdomen\"]") else null)))
                    if (truth(jsAnd(targets) { (category === "Fuerza") })) {
                        val focused: dynamic = list.filter((fun(r: dynamic): dynamic {
                            return r.items.some((fun(x: dynamic): dynamic {
                                return targets.includes(global_Engine.byId(x.id).group)
                            }))
                        }))
                        if (truth(focused.length)) {
                            list = focused
                        }
                    }
                    if (truth((p.equipment === "db"))) {
                        val weighted: dynamic = list.filter((fun(r: dynamic): dynamic {
                            return r.requirements.includes("db")
                        }))
                        if (truth(weighted.length)) {
                            list = weighted
                        }
                    }
                    val r: dynamic = list[jsMod(i, list.length)]
                    val previousItems: dynamic = d.items
                    d.items = adjust(r, p)
                    if (truth(jsAnd((!truth(gentle(p)))) { (global_TrainingIntensity.scale(p) === 1) })) {
                        if (true) {
                            val iterator90: dynamic = iteratorOf(d.items)
                            while (true) {
                                val next91: dynamic = iterator90.next()
                                if (truth(next91.done)) break
                                var x: dynamic = absent
                                x = next91.value
                                val prior: dynamic = previousItems.find((fun(y: dynamic): dynamic {
                                    return (y.id === x.id)
                                }))
                                if (truth(prior)) {
                                    x.target = global_Math.max(x.target, global_Math.min(prior.target, cap(global_Engine.byId(x.id), p)))
                                }
                                val next: dynamic = global_AdaptiveV9.recommend(logs, x, p, now)
                                x.target = global_Math.max(x.target, next.target)
                            }
                        }
                    }
                    if (truth((onPresent(c.reviews) { value96 -> onPresent(value96.at((-numeric(1)))) { value97 -> value97.action } } === "reducir"))) {
                        if (true) {
                            val iterator98: dynamic = iteratorOf(d.items)
                            while (true) {
                                val next99: dynamic = iterator98.next()
                                if (truth(next99.done)) break
                                var x: dynamic = absent
                                x = next99.value
                                x.target = global_Math.max((if (truth((global_Engine.unitOf(x) === "sec"))) 10 else 4), global_Math.floor(jsMultiply(x.target, 0.8)))
                            }
                        }
                    }
                    d.kind = (if (truth((r.category === "Mixta"))) "mixed" else d.kind)
                    d.title = r.name
                    d.own18 = true
                    d.autoTemplateId = r.id
                    d.tasks = js("[]")
                    d.planningNote = (if (truth(gentle(p))) "Rutina propia con dosis reducida y movimientos compatibles con tus restricciones." else "Rutina propia según tu objetivo, equipo y nivel. Revisión semanal con tus registros.")
                    run {
                        val previous114: dynamic = numeric(count)
                        count = jsAdd(previous114, 1)
                    }
                }
            }
            return count
        })
        val expand: dynamic = global_RoutineSequence.expand
        global_RoutineSequence.expand = (fun(day: dynamic): dynamic {
            val steps: dynamic = expand(day)
            if (true) {
                val iterator119: dynamic = iteratorOf(steps)
                while (true) {
                    val next120: dynamic = iterator119.next()
                    if (truth(next120.done)) break
                    var s: dynamic = absent
                    s = next120.value
                    if (truth(jsAnd((s.kind === "exercise")) { (s.unit !== "sec") })) {
                        s.seconds = global_Math.ceil(workSeconds(s))
                    }
                }
            }
            if (truth(jsAnd((!truth(day.sourceSequence))) { steps.length })) {
                steps.unshift(jsonValue("{\"kind\":\"preparation\",\"label\":\"Preparación · revisa espacio, equipo y técnica\",\"seconds\":60,\"blockIndex\":-1}"))
                steps.push(jsObject { obj123 ->
                    obj123["kind"] = "cooldown"
                    obj123["label"] = "Vuelta a la calma · baja el ritmo y respira"
                    obj123["seconds"] = 60
                    obj123["blockIndex"] = day.items.length
                })
            }
            return steps
        })
        root.OwnRoutines18 = jsObject { obj126 ->
            obj126["routines"] = routines
            obj126["families"] = families
            obj126["lengths"] = lengths
            obj126["names"] = names
            obj126["keys"] = keys
            obj126["dose"] = dose
            obj126["adjust"] = adjust
            obj126["estimate"] = estimate
            obj126["cap"] = cap
            obj126["workSeconds"] = workSeconds
            obj126["gentle"] = gentle
        }
        return absent
    }))(global_window)
}
