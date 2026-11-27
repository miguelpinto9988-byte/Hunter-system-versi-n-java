@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installtrainingv23

import hunter.browser.*

@JsName("Engine") private external var global_Engine: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("TrainingProgression19") private external var global_TrainingProgression19: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("RoutinePlanner") private external var global_RoutinePlanner: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("ExercisePack23") private external var global_ExercisePack23: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("GoalContext24") private external var global_GoalContext24: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("AdaptiveV9") private external var global_AdaptiveV9: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installTrainingV23() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var O: dynamic = absent
        var T: dynamic = absent
        var oldReasons: dynamic = absent
        var oldDose: dynamic = absent
        var oldChoose: dynamic = absent
        var gymEquipment: dynamic = absent
        var reasons: dynamic = absent
        var dose: dynamic = absent
        var familyGroups48: dynamic = absent
        var familyMatches: dynamic = absent
        var constructing48: dynamic = absent
        var startupPools48: dynamic = absent
        var pool: dynamic = absent
        var balanced: dynamic = absent
        var choose: dynamic = absent
        var gyms: dynamic = absent
        var homes: dynamic = absent
        var adjust: dynamic = absent
        var assign: dynamic = absent
        reasons = fun(e: dynamic, p: dynamic, arg5: dynamic): dynamic {
            var strict: dynamic = absent
            strict = definedOr(arg5) { true }
            if (truth((!truth(e)))) {
                return jsonValue("[\"Ejercicio desconocido\"]")
            }
            val normalized: dynamic = (if (truth((onPresent(p) { value6 -> value6.equipment } === "gym"))) jsObject { obj7 ->
                copyFields(obj7, p)
                obj7["equipment"] = "db"
                obj7["bench"] = dynamicValue(jsOr(p.gymEquipment23) { js("[]") }).includes("bench")
            } else p)
            val list: dynamic = oldReasons(e, normalized, strict)
            if (truth(e.gymOnly23)) {
                if (truth((onPresent(p) { value8 -> value8.equipment } !== "gym"))) {
                    list.push("Requiere perfil de gimnasio")
                }
                if (true) {
                    val iterator9: dynamic = iteratorOf(jsOr(e.gymRequirements23) { js("[]") })
                    while (true) {
                        val next10: dynamic = iterator9.next()
                        if (truth(next10.done)) break
                        var key: dynamic = absent
                        key = next10.value
                        if (truth((!truth(dynamicValue(jsOr(onPresent(p) { value12 -> value12.gymEquipment23 }) { js("[]") }).includes(key))))) {
                            list.push(jsAdd("Falta ", gymEquipment[key]))
                        }
                    }
                }
            }
            if (truth(jsAnd(jsAnd(onPresent(p) { value13 -> onPresent(value13.restrictions) { value14 -> value14.noLoad } }) { e.loaded23 }) { (!truth(list.includes("Carga externa excluida"))) })) {
                list.push("Carga externa excluida")
            }
            if (truth(jsAnd(jsAnd(jsAnd(e.pack23) { p }) { jsMore(T.tier(e.id), T.level(p)) }) { (!truth(global_Adapt.mode(p).reduced)) })) {
                list.push("Variante fuera del escalón seleccionado")
            }
            return jsArray { array15 ->
                appendAll(array15, jsConstruct(global_Set, arrayOf<dynamic>(list)))
            }
        }
        dose = fun(e: dynamic, p: dynamic, kind: dynamic): dynamic {
            val v: dynamic = oldDose(e, p, kind)
            if (truth((!truth(e.pack23)))) {
                return v
            }
            if (truth(jsOr(O.gentle(p)) { (kind === "recovery") })) {
                return jsObject { obj24 ->
                    copyFields(obj24, v)
                    obj24["sets"] = 1
                    obj24["target"] = (if (truth((e.unit === "sec"))) 15 else 5)
                    obj24["rest"] = T.rest(p)
                }
            }
            val n: dynamic = T.level(p)
            val beginner: dynamic = (p.experience === "beginner")
            val range: dynamic = jsOr(e.repRange19) { jsonValue("[8,16]") }
            val scale: dynamic = global_TrainingIntensity.scale(p)
            val sets: dynamic = (if (truth(beginner)) 2 else dynamicValue(jsonValue("[4,4,5]"))[n])
            val target: dynamic = (if (truth((e.unit === "sec"))) dynamicValue(jsonValue("[30,40,50]"))[n] else global_Math.min(range[1], jsAdd(range[0], dynamicValue(jsonValue("[4,2,0]"))[n])))
            return jsObject { obj25 ->
                copyFields(obj25, v)
                obj25["sets"] = sets
                obj25["target"] = global_Math.max((if (truth((e.unit === "sec"))) 10 else 3), global_Math.floor(jsMultiply(target, scale)))
                obj25["rest"] = T.rest(p)
            }
        }
        familyMatches = fun(e: dynamic, family: dynamic): dynamic {
            return dynamicValue(jsOr(familyGroups48[family]) { js("[]") }).includes(e.group)
        }
        pool = fun(p: dynamic, arg36: dynamic, arg37: dynamic): dynamic {
            var family: dynamic = absent
            family = definedOr(arg36) { "full" }
            var includeVariants: dynamic = absent
            includeVariants = definedOr(arg37) { true }
            val key: dynamic = (if (truth(constructing48)) global_JSON.stringify(arrayOf<dynamic>(p, family, includeVariants)) else null)
            if (truth(jsAnd(key) { startupPools48.has(key) })) {
                return startupPools48["get"](key).slice()
            }
            val n: dynamic = T.level(p)
            var list: dynamic = E.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd((!truth(e.sourceOnly))) { familyMatches(e, family) }) { jsAtMost(T.tier(e.id), n) }) { global_Adapt.allowed(e, p) }) { jsOr(includeVariants) { (e.variant === "base") } }
            }))
            if (truth((p.equipment === "gym"))) {
                list = list.filter((fun(e: dynamic): dynamic {
                    return e.gymOnly23
                }))
            } else {
                list = list.filter((fun(e: dynamic): dynamic {
                    return (!truth(e.gymOnly23))
                }))
            }
            list.sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(jsSubtract(global_Math.abs(jsSubtract(n, T.tier(a.id))), global_Math.abs(jsSubtract(n, T.tier(b.id))))) { jsSubtract(global_Number((!truth(a.pack23))), global_Number((!truth(b.pack23)))) }
            }))
            if (truth(key)) {
                startupPools48["set"](key, list.slice())
            }
            return list
        }
        balanced = fun(list: dynamic, count: dynamic, arg40: dynamic, arg41: dynamic): dynamic {
            var rotation: dynamic = absent
            rotation = definedOr(arg40) { 0 }
            var p: dynamic = absent
            p = definedOr(arg41) { null }
            if (truth((!truth(list.length)))) {
                return js("[]")
            }
            val groups: dynamic = jsArray { array42 ->
                appendAll(array42, jsConstruct(global_Set, arrayOf<dynamic>(list.map((fun(e: dynamic): dynamic {
                    return e.group
                })))))
            }
            val used: dynamic = jsConstruct(global_Set, js("[]"))
            val `out`: dynamic = js("[]")
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration43 = true
                while (true) {
                    if (!firstIteration43) {
                        run {
                            val previous44: dynamic = numeric(i)
                            i = jsAdd(previous44, 1)
                        }
                    }
                    firstIteration43 = false
                    if (!truth(jsLess(i, count))) break
                    val group: dynamic = groups[jsMod(i, groups.length)]
                    val same: dynamic = list.filter((fun(e: dynamic): dynamic {
                        return jsAnd((e.group === group)) { (!truth(used.has(e.baseId))) }
                    }))
                    var candidates: dynamic = (if (truth(same.length)) same else list.filter((fun(e: dynamic): dynamic {
                        return (!truth(used.has(e.baseId)))
                    })))
                    if (truth((!truth(candidates.length)))) {
                        break
                    }
                    val highest: dynamic = run { val target46: dynamic = global_Math; invokeWith(target46.max, target46, jsArray { array45 ->
                        appendAll(array45, candidates.map((fun(e: dynamic): dynamic {
                            return T.tier(e.id)
                        })))
                    }) }
                    candidates = candidates.filter((fun(e: dynamic): dynamic {
                        return (T.tier(e.id) === highest)
                    }))
                    if (truth(p)) {
                        val best: dynamic = run { val target48: dynamic = global_Math; invokeWith(target48.max, target48, jsArray { array47 ->
                            appendAll(array47, candidates.map((fun(e: dynamic): dynamic {
                                return global_GoalContext24.score(e, p)
                            })))
                        }) }
                        candidates = candidates.filter((fun(e: dynamic): dynamic {
                            return (global_GoalContext24.score(e, p) === best)
                        }))
                    }
                    val e: dynamic = candidates[jsMod(jsAdd(global_Math.abs(rotation), global_Math.floor(jsDivide(i, groups.length))), candidates.length)]
                    `out`.push(e)
                    used.add(e.baseId)
                }
            }
            return `out`
        }
        choose = fun(input51: dynamic, kind: dynamic, arg52: dynamic): dynamic {
            var p: dynamic = input51
            var rotation: dynamic = absent
            rotation = definedOr(arg52) { 0 }
            p = global_GoalContext24.adjusted(p)
            if (truth((!truth(global_Adapt.mode(p).auto)))) {
                return js("[]")
            }
            val count: dynamic = global_Math.min(7, (if (truth((p.trainingMode === "routines"))) 5 else jsOr(p.dailyMissions) { 4 }))
            val focus: dynamic = (if (truth((p.focus === "upper"))) "push" else (if (truth((p.focus === "lower"))) "legs" else (if (truth((p.focus === "core"))) "core" else "full")))
            if (truth(jsAnd(O.gentle(p)) { (p.equipment !== "gym") })) {
                return oldChoose(p, kind, rotation)
            }
            var list: dynamic = pool(p, (if (truth((kind === "cardio"))) "cardio" else (if (truth((kind === "recovery"))) "mobility" else focus)))
            if (truth(jsAnd(jsAnd((p.focus === "upper")) { (kind !== "cardio") }) { (kind !== "recovery") })) {
                list = jsArray { array53 ->
                    appendAll(array53, pool(p, "push"))
                    appendAll(array53, pool(p, "pull"))
                }
            }
            if (truth(jsAnd(jsAnd((!truth(list.length))) { (p.equipment === "gym") }) { (kind === "cardio") })) {
                list = pool(p, "full")
            }
            if (truth((!truth(list.length)))) {
                return oldChoose(p, kind, rotation).filter((fun(x: dynamic): dynamic {
                    return global_Adapt.allowed(E.byId(x.id), p)
                }))
            }
            return balanced(list, (if (truth((kind === "recovery"))) global_Math.min(3, count) else count), rotation, p).map((fun(e: dynamic): dynamic {
                return dose(e, p, kind)
            }))
        }
        adjust = fun(r: dynamic, p: dynamic): dynamic {
            return r.items.map((fun(x: dynamic): dynamic {
                val e: dynamic = E.byId(x.id)
                val v: dynamic = dose(e, p, (if (truth((r.category === "Cardio"))) "cardio" else (if (truth((r.category === "Movilidad"))) "recovery" else "strength")))
                return jsObject { obj81 ->
                    copyFields(obj81, x)
                    copyFields(obj81, v)
                    obj81["sets"] = (if (truth(O.gentle(p))) 1 else (if (truth((r.extension === "brief"))) global_Math.max(1, global_Math.ceil(jsMultiply(v.sets, 0.5))) else v.sets))
                }
            }))
        }
        assign = fun(c: dynamic, p: dynamic, arg86: dynamic, arg87: dynamic, arg88: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg86) { js("({})") }
            var now: dynamic = absent
            now = definedOr(arg87) { E.dateKey() }
            var last: dynamic = absent
            last = definedOr(arg88) { null }
            if (truth(jsOr((!truth(global_Adapt.mode(p).auto))) { (onPresent(c.reviews) { value89 -> onPresent(value89.at((-numeric(1)))) { value90 -> value90.action } } === "pausar") })) {
                return 0
            }
            var changed: dynamic = 0
            val ap: dynamic = global_GoalPlans.adjusted(p)
            val n: dynamic = T.level(p)
            val group: dynamic = global_GoalGroups14.of(p)
            val intent: dynamic = global_GoalContext24.interpret(p)
            val pattern: dynamic = jsArray { array91 ->
                appendAll(array91, dynamicValue(jsOr(global_GoalPlans.plans[intent.target]) { global_GoalPlans.plans.general }).pattern)
            }
            val ordinal: dynamic = 0
            var lastStrength: dynamic = false
            if (truth(jsMore(intent.priorities.length, 1))) {
                val extra: dynamic = intent.priorities[1]
                pattern[1] = (if (truth(dynamicValue(jsonValue("[\"fitness\",\"athletic\"]")).includes(extra))) "cardio" else (if (truth((extra === "movement"))) "recovery" else "strength"))
            }
            if (true) {
                val iterator94: dynamic = iteratorOf(c.days.entries())
                while (true) {
                    val next95: dynamic = iterator94.next()
                    if (truth(next95.done)) break
                    var i: dynamic = absent
                    var d: dynamic = absent
                    val unpacked96: dynamic = fromIterable(next95.value)
                    i = unpacked96[0]
                    d = unpacked96[1]
                    var items: dynamic = absent
                    val choice: dynamic = onPresent(p.weekPlan) { value97 -> value97[global_Schedule.weekday(d.date)] }
                    val desired: dynamic = (if (truth((choice === "rest"))) "rest" else (if (truth((choice === "mobility"))) "recovery" else (if (truth((choice === "cardio"))) "cardio" else "strength")))
                    lastStrength = (desired === "strength")
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, now)) { (d.kind === "rest") }) { d.customized }) { d.routineId }) { global_Weekly.active(logs[d.date]) }) { onPresent(d.tasks) { value98 -> value98.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) } })) {
                        continue
                    }
                    d.kind = desired
                    if (truth((p.trainingMode === "routines"))) {
                        var list: dynamic = dynamicValue((if (truth((p.equipment === "gym"))) gyms else homes)).filter((fun(r: dynamic): dynamic {
                            return jsAnd(jsAnd(jsAnd((r.level === n)) { (r.extension === "standard") }) { r.goalGroups.some((fun(g: dynamic): dynamic {
                                return intent.priorities.includes(g)
                            })) }) { global_Adapt.routineCompatible(r, ap) }
                        }))
                        if (truth((ap.focus === "upper"))) {
                            list = list.filter((fun(r: dynamic): dynamic {
                                return (if (truth((p.equipment === "gym"))) dynamicValue(jsonValue("[\"gym-upper\",\"gym-push\",\"gym-pull\",\"gym-arms\"]")).includes(r.family) else dynamicValue(jsonValue("[\"push\",\"pull\"]")).includes(r.family))
                            }))
                        }
                        if (truth((ap.focus === "lower"))) {
                            list = list.filter((fun(r: dynamic): dynamic {
                                return (if (truth((p.equipment === "gym"))) dynamicValue(jsonValue("[\"gym-lower\",\"gym-glutes\"]")).includes(r.family) else (r.family === "legs"))
                            }))
                        }
                        if (truth((ap.focus === "core"))) {
                            list = list.filter((fun(r: dynamic): dynamic {
                                return (if (truth((p.equipment === "gym"))) (r.family === "gym-core") else (r.family === "core"))
                            }))
                        }
                        val appropriate: dynamic = list.filter((fun(r: dynamic): dynamic {
                            return (if (truth((d.kind === "recovery"))) (r.category === "Movilidad") else (if (truth((d.kind === "cardio"))) (r.category === "Cardio") else (!truth(dynamicValue(jsonValue("[\"Cardio\",\"Movilidad\"]")).includes(r.category)))))
                        }))
                        list = (if (truth(jsOr((d.kind === "recovery")) { (d.kind === "cardio") })) appropriate else (if (truth(appropriate.length)) appropriate else list))
                        if (truth(list.length)) {
                            val score: dynamic = (fun(r: dynamic): dynamic {
                                return jsDivide(r.items.reduce((fun(sum: dynamic, x: dynamic): dynamic {
                                    return jsAdd(sum, global_GoalContext24.score(E.byId(x.id), ap))
                                }), 0), r.items.length)
                            })
                            val best: dynamic = run { val target102: dynamic = global_Math; invokeWith(target102.max, target102, jsArray { array101 ->
                                appendAll(array101, list.map(score))
                            }) }
                            list = list.filter((fun(r: dynamic): dynamic {
                                return jsAtLeast(score(r), jsSubtract(best, 0.01))
                            }))
                        }
                        if (truth(list.length)) {
                            val r: dynamic = list[jsMod(jsAdd(i, c.index), list.length)]
                            items = adjust(r, ap)
                            d.title = r.name
                            d.autoTemplateId = r.id
                        }
                    }
                    if (truth((!truth(onPresent(items) { value107 -> value107.length })))) {
                        items = choose(ap, d.kind, jsAdd(i, jsMultiply(c.index, 7)))
                    }
                    if (truth((!truth(items.length)))) {
                        continue
                    }
                    val existing: dynamic = jsObject { obj108 ->
                        obj108["days"] = arrayOf<dynamic>(jsObject { obj109 ->
                            obj109["items"] = jsOr(d.items) { js("[]") }
                        })
                    }
                    items = items.map((fun(x: dynamic): dynamic {
                        var next: dynamic = T.carry(T.carry(x, last, ap), existing, ap)
                        if (truth(jsAnd(jsAnd((c.engineVersion23 !== 23)) { jsAtLeast(onPresent(c.reviews) { value110 -> onPresent(value110.at((-numeric(1)))) { value111 -> value111.adherence } }, 0.8) }) { (!truth(O.gentle(ap))) })) {
                            next = T.applyRecommendation(next, global_AdaptiveV9.recommend(logs, next, ap, now), ap)
                        }
                        return next
                    }))
                    if (truth((onPresent(c.reviews) { value112 -> onPresent(value112.at((-numeric(1)))) { value113 -> value113.action } } === "reducir"))) {
                        items = items.map((fun(x: dynamic): dynamic {
                            return jsObject { obj114 ->
                                copyFields(obj114, x)
                                obj114["target"] = global_Math.max((if (truth((E.unitOf(x) === "sec"))) 10 else 3), global_Math.floor(jsMultiply(x.target, 0.8)))
                            }
                        }))
                    }
                    d.items = items
                    d.gym23 = (p.equipment === "gym")
                    d.own18 = true
                    d.own19 = true
                    d.tasks = js("[]")
                    if (truth((p.trainingMode !== "routines"))) {
                        d.title = jsAdd(jsAdd((if (truth((p.equipment === "gym"))) "Misiones de gimnasio" else "Misiones personales"), " · "), O.names[n])
                    }
                    d.goalIntent24 = intent.reason
                    d.planningNote = (if (truth(O.gentle(ap))) "Dosis reducida y movimientos compatibles con las restricciones declaradas." else jsAdd(jsAdd("Biblioteca ampliada · ", O.names[n]), ": patrones, apoyos, cadencia y revisión semanal por registros confirmados."))
                    run {
                        val previous131: dynamic = numeric(changed)
                        changed = jsAdd(previous131, 1)
                    }
                }
            }
            c.engineVersion23 = 23
            return changed
        }
        E = global_Engine
        O = global_OwnRoutines18
        T = global_TrainingProgression19
        oldReasons = global_Adapt.reasons
        oldDose = O.dose
        oldChoose = T.choose
        val oldMake: dynamic = E.makeCycle
        val oldOrganize: dynamic = global_RoutinePlanner.organize
        gymEquipment = jsonValue("{\"barbell\":\"Barra y discos\",\"cable\":\"Poleas\",\"machine\":\"Máquinas de fuerza\",\"smith\":\"Máquina Smith\",\"cardio\":\"Cinta o ergómetro\",\"bench\":\"Banco y soportes\"}")
        global_Adapt.reasons = reasons
        global_Adapt.allowed = (fun(e: dynamic, p: dynamic): dynamic {
            return (!truth(reasons(e, p).length))
        })
        global_Adapt.routineCompatible = (fun(r: dynamic, p: dynamic): dynamic {
            return jsAnd(jsAnd((!truth((!truth(p))))) { global_Adapt.mode(p).auto }) { r.items.every((fun(x: dynamic): dynamic {
                return global_Adapt.allowed(E.byId(x.id), p)
            })) }
        })
        O.dose = dose
        global_TrainingIntensity.dose = dose
        val newBases: dynamic = global_ExercisePack23.baseIds.map((fun(id: dynamic): dynamic {
            return E.byId(id)
        }))
        if (true) {
            val iterator30: dynamic = iteratorOf(newBases)
            while (true) {
                val next31: dynamic = iterator30.next()
                if (truth(next31.done)) break
                var e: dynamic = absent
                e = next31.value
                T.chains.push(arrayOf<dynamic>(e.id, jsAdd(e.id, "-tempo23"), jsAdd(e.id, "-pause23")))
            }
        }
        T.chains.push(jsonValue("[\"pushup-control19\",\"home-handrelease23\",\"home-staggered23\",\"home-diamond23\"]"))
        T.chains.push(jsonValue("[\"single-squat19\",\"home-pistol-box23\",\"home-skater-squat23\",\"home-shrimp23\"]"))
        T.chains.push(jsonValue("[\"plankreach19\",\"home-body-saw23\",\"home-bear-tap23\"]"))
        T.chains.push(jsonValue("[\"singlebridge-pause19\",\"home-ham-slide23\",\"home-ham-slide-one23\"]"))
        familyGroups48 = jsonValue("{\"push\":[\"Pecho\",\"Hombros\"],\"pull\":[\"Espalda\",\"Brazos\"],\"legs\":[\"Piernas\",\"Cadera\"],\"core\":[\"Abdomen\",\"Core\"],\"full\":[\"Piernas\",\"Cadera\",\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\",\"Abdomen\"],\"control\":[\"Piernas\",\"Cadera\",\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\",\"Abdomen\"],\"cardio\":[\"Cardio\"],\"intervals\":[\"Cardio\"],\"agility\":[\"Cardio\"],\"power\":[\"Cardio\"],\"mobility\":[\"Movilidad\"],\"balance\":[\"Movilidad\"],\"seated\":[\"Movilidad\"]}")
        constructing48 = true
        startupPools48 = jsConstruct(global_Map, js("[]"))
        global_Schedule.items = choose
        val gymFamilies: dynamic = jsonValue("{\"full\":{\"name\":\"Cuerpo completo en gimnasio\",\"groups\":[\"Piernas\",\"Pecho\",\"Espalda\",\"Cadera\",\"Abdomen\"],\"goals\":[\"build\",\"fitness\",\"habit\"]},\"upper\":{\"name\":\"Tren superior en gimnasio\",\"groups\":[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"],\"goals\":[\"build\",\"fitness\"]},\"lower\":{\"name\":\"Piernas y glúteos en gimnasio\",\"groups\":[\"Piernas\",\"Cadera\"],\"goals\":[\"build\",\"athletic\"]},\"push\":{\"name\":\"Empuje: pecho, hombros y tríceps\",\"groups\":[\"Pecho\",\"Hombros\",\"Brazos\"],\"goals\":[\"build\"]},\"pull\":{\"name\":\"Tirón: espalda y bíceps\",\"groups\":[\"Espalda\",\"Brazos\"],\"goals\":[\"build\"]},\"glutes\":{\"name\":\"Cadena posterior en gimnasio\",\"groups\":[\"Cadera\",\"Piernas\",\"Espalda\"],\"goals\":[\"build\",\"athletic\"]},\"arms\":{\"name\":\"Brazos y hombros en gimnasio\",\"groups\":[\"Brazos\",\"Hombros\"],\"goals\":[\"build\"]},\"core\":{\"name\":\"Zona media con poleas y máquinas\",\"groups\":[\"Abdomen\",\"Espalda\"],\"goals\":[\"build\",\"movement\",\"habit\"]},\"conditioning\":{\"name\":\"Acondicionamiento en gimnasio\",\"groups\":[\"Cardio\",\"Piernas\",\"Espalda\"],\"goals\":[\"fitness\",\"athletic\",\"habit\"]}}")
        gyms = js("[]")
        if (true) {
            val iterator56: dynamic = iteratorOf(global_Object.entries(gymFamilies))
            while (true) {
                val next57: dynamic = iterator56.next()
                if (truth(next57.done)) break
                var family: dynamic = absent
                var spec: dynamic = absent
                val unpacked58: dynamic = fromIterable(next57.value)
                family = unpacked58[0]
                spec = unpacked58[1]
                if (true) {
                    var n: dynamic = absent
                    n = 0
                    var firstIteration59 = true
                    while (true) {
                        if (!firstIteration59) {
                            run {
                                val previous60: dynamic = numeric(n)
                                n = jsAdd(previous60, 1)
                            }
                        }
                        firstIteration59 = false
                        if (!truth(jsLess(n, 3))) break
                        if (true) {
                            val iterator61: dynamic = iteratorOf(jsonValue("[\"standard\",\"long\"]"))
                            while (true) {
                                val next62: dynamic = iterator61.next()
                                if (truth(next62.done)) break
                                var extension: dynamic = absent
                                extension = next62.value
                                val p: dynamic = jsObject { obj63 ->
                                    obj63["equipment"] = "gym"
                                    obj63["gymEquipment23"] = global_Object.keys(gymEquipment)
                                    obj63["difficulty"] = O.keys[n]
                                    obj63["experience"] = "regular"
                                    obj63["age"] = 30
                                    obj63["health"] = jsonValue("{\"pain\":\"none\"}")
                                    obj63["injuries"] = js("[]")
                                    obj63["restrictions"] = js("({})")
                                    obj63["trainingScale"] = 1
                                    obj63["bench"] = true
                                }
                                var available: dynamic = E.exercises.filter((fun(e: dynamic): dynamic {
                                    return jsAnd(jsAnd(jsAnd(e.gymOnly23) { spec.groups.includes(e.group) }) { jsAtMost(T.tier(e.id), n) }) { global_Adapt.allowed(e, p) }
                                }))
                                if (truth((family === "push"))) {
                                    available = available.filter((fun(e: dynamic): dynamic {
                                        return jsOr((e.group !== "Brazos")) { (e.recipe23 === "tricep") }
                                    }))
                                }
                                if (truth((family === "pull"))) {
                                    available = available.filter((fun(e: dynamic): dynamic {
                                        return jsOr((e.group !== "Brazos")) { (e.recipe23 === "curl") }
                                    }))
                                }
                                available.sort((fun(a: dynamic, b: dynamic): dynamic {
                                    return jsSubtract(global_Math.abs(jsSubtract(n, T.tier(a.id))), global_Math.abs(jsSubtract(n, T.tier(b.id))))
                                }))
                                val selected: dynamic = balanced(available, (if (truth((extension === "long"))) 6 else 5), n)
                                val items: dynamic = selected.map((fun(e: dynamic): dynamic {
                                    return dose(e, p, (if (truth((family === "conditioning"))) "cardio" else "strength"))
                                }))
                                if (truth((!truth(items.length)))) {
                                    throw global_Error(jsAdd(jsAdd(jsAdd("Rutina de gimnasio vacía: ", family), " "), n))
                                }
                                val r: dynamic = jsObject { obj64 ->
                                    obj64["id"] = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("gym23-", family), "-"), n), "-"), extension)
                                    obj64["name"] = jsAdd(jsAdd(jsAdd(spec.name, " · "), O.names[n]), (if (truth((extension === "long"))) " · Extendida" else ""))
                                    obj64["category"] = (if (truth((family === "conditioning"))) "Cardio" else "Fuerza")
                                    obj64["level"] = n
                                    obj64["items"] = items
                                    obj64["requirements"] = js("[]")
                                    obj64["gymOnly23"] = true
                                    obj64["gymRequirements23"] = jsArray { array65 ->
                                        appendAll(array65, jsConstruct(global_Set, arrayOf<dynamic>(selected.flatMap((fun(e: dynamic): dynamic {
                                            return e.gymRequirements23
                                        })))))
                                    }
                                    obj64["family"] = jsAdd("gym-", family)
                                    obj64["goalGroups"] = spec.goals
                                    obj64["extension"] = extension
                                    obj64["impact"] = "Sin saltos"
                                    obj64["planVersion"] = 23
                                    obj64["description"] = "Rutina original completa de Hunter System: preparación, orden de ejercicios, series, descansos y vuelta a la calma. Usa resistencia controlable y ajusta la sesión a tus registros."
                                    obj64["minutes"] = O.estimate(items)
                                }
                                gyms.push(r)
                            }
                        }
                    }
                }
            }
        }
        homes = O.routines.map((fun(r: dynamic): dynamic {
            val n: dynamic = r.level
            val material: dynamic = (if (truth(r.requirements.includes("db"))) "db" else "body")
            val p: dynamic = jsObject { obj66 ->
                obj66["equipment"] = material
                obj66["difficulty"] = O.keys[n]
                obj66["experience"] = "regular"
                obj66["age"] = 30
                obj66["health"] = jsonValue("{\"pain\":\"none\"}")
                obj66["injuries"] = js("[]")
                obj66["restrictions"] = js("({})")
                obj66["trainingScale"] = 1
                obj66["bench"] = r.requirements.includes("bench")
                obj66["step"] = r.requirements.includes("step")
            }
            if (truth(dynamicValue(jsonValue("[\"cardio\",\"intervals\",\"agility\",\"power\",\"mobility\",\"balance\",\"seated\"]")).includes(r.family))) {
                return jsObject { obj67 ->
                    copyFields(obj67, r)
                    obj67["planVersion"] = 23
                }
            }
            val list: dynamic = pool(p, r.family)
            if (truth((!truth(list.length)))) {
                return jsObject { obj68 ->
                    copyFields(obj68, r)
                    obj68["planVersion"] = 23
                }
            }
            val selected: dynamic = balanced(list, O.lengths[r.extension].count, n)
            val items: dynamic = selected.map((fun(e: dynamic): dynamic {
                val x: dynamic = dose(e, p, (if (truth((r.category === "Cardio"))) "cardio" else (if (truth((r.category === "Movilidad"))) "recovery" else "strength")))
                return jsObject { obj69 ->
                    copyFields(obj69, x)
                    obj69["sets"] = global_Math.max(1, global_Math.ceil(jsMultiply(x.sets, O.lengths[r.extension].ratio)))
                }
            }))
            return jsObject { obj70 ->
                copyFields(obj70, r)
                obj70["items"] = items
                obj70["requirements"] = jsArray { array71 ->
                    appendAll(array71, jsConstruct(global_Set, arrayOf<dynamic>(selected.flatMap((fun(e: dynamic): dynamic {
                        return e.requirements
                    })))))
                }
                obj70["minutes"] = O.estimate(items)
                obj70["planVersion"] = 23
            }
        }))
        constructing48 = false
        startupPools48.clear()
        run { val target73: dynamic = O.routines; invokeWith(target73.splice, target73, jsArray { array72 ->
            array72.push(0)
            array72.push(O.routines.length)
            appendAll(array72, homes)
        }) }
        global_Catalog.routines = jsArray { array76 ->
            appendAll(array76, homes)
            appendAll(array76, gyms)
        }
        global_Catalog.baseRoutines = global_Catalog.routines.length
        O.adjust = adjust
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg138: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg138) { js("({})") }
            val c: dynamic = oldMake(p, start, index, last, logs)
            assign(c, p, logs, start, last)
            return c
        })
        global_RoutinePlanner.organize = (fun(c: dynamic, p: dynamic, source: dynamic, own: dynamic, logs: dynamic, now: dynamic): dynamic {
            if (truth((c.engineVersion23 === 23))) {
                return 0
            }
            return assign(c, p, logs, now)
        })
        root.Training23 = jsObject { obj145 ->
            obj145["gymEquipment"] = gymEquipment
            obj145["gymFamilies"] = gymFamilies
            obj145["gymRoutines"] = gyms
            obj145["homeRoutines"] = homes
            obj145["pool"] = pool
            obj145["choose"] = choose
            obj145["dose"] = dose
            obj145["adjust"] = adjust
            obj145["assign"] = assign
            obj145["reasons"] = reasons
        }
        return absent
    }))(global_window)
}
