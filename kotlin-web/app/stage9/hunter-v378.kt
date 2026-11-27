@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterv378

import hunter.browser.*

@JsName("String") private external var global_String: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("workout") private external var global_workout: dynamic
@JsName("todayDay") private external var global_todayDay: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("routineList") private external var global_routineList: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("routineDetail") private external var global_routineDetail: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("prepareRoutine") private external var global_prepareRoutine: dynamic
@JsName("routineNotice372") private external var global_routineNotice372: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("console") private external var global_console: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterV378() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var A: dynamic = absent
        var S: dynamic = absent
        var VERSION: dynamic = absent
        var MAP: dynamic = absent
        var NAMES: dynamic = absent
        var CATS: dynamic = absent
        var GROUPS: dynamic = absent
        var actives: dynamic = absent
        var relevant: dynamic = absent
        var preserve: dynamic = absent
        var preference: dynamic = absent
        var simpleHash: dynamic = absent
        var planKey: dynamic = absent
        var clinicalGate: dynamic = absent
        var affectedAreas: dynamic = absent
        var approvedExercise: dynamic = absent
        var candidates: dynamic = absent
        var uniqueExercises: dynamic = absent
        var ordered: dynamic = absent
        var previousSessions: dynamic = absent
        var loadPolicy: dynamic = absent
        var choose: dynamic = absent
        var patchDay: dynamic = absent
        var patchCycle: dynamic = absent
        var adaptedTemplates: dynamic = absent
        planKey = fun(p: dynamic): dynamic {
            return simpleHash(global_JSON.stringify(jsObject { obj12 ->
                obj12["injuries"] = jsOr(onPresent(p) { value13 -> value13.injuries }) { js("[]") }
                obj12["health"] = jsOr(onPresent(p) { value14 -> value14.health }) { js("({})") }
                obj12["restrictions"] = jsOr(onPresent(p) { value15 -> value15.restrictions }) { js("({})") }
                obj12["goal"] = onPresent(p) { value16 -> value16.goal }
                obj12["finalGoal"] = onPresent(p) { value17 -> value17.finalGoal }
                obj12["focus"] = onPresent(p) { value18 -> value18.focus }
                obj12["trainingMode"] = onPresent(p) { value19 -> value19.trainingMode }
                obj12["equipment"] = onPresent(p) { value20 -> value20.equipment }
                obj12["gymEquipment23"] = onPresent(p) { value21 -> value21.gymEquipment23 }
                obj12["minutes"] = onPresent(p) { value22 -> value22.minutes }
                obj12["difficulty"] = onPresent(p) { value23 -> value23.difficulty }
                obj12["experience"] = onPresent(p) { value24 -> value24.experience }
                obj12["trainingScale"] = onPresent(p) { value25 -> value25.trainingScale }
                obj12["weekPlan"] = onPresent(p) { value26 -> value26.weekPlan }
                obj12["restSeconds"] = onPresent(p) { value27 -> value27.restSeconds }
            }))
        }
        clinicalGate = fun(p: dynamic, arg30: dynamic, arg31: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg30) { js("({})") }
            var today: dynamic = absent
            today = definedOr(arg31) { E.dateKey() }
            val mode: dynamic = A.mode(p)
            if (truth((!truth(mode.auto)))) {
                return jsObject { obj32 ->
                    obj32["ok"] = false
                    obj32["reason"] = mode.reason
                }
            }
            val recent: dynamic = global_Object.entries(logs).some((fun(arg33: dynamic): dynamic {
                var date: dynamic = absent
                var log: dynamic = absent
                val unpacked34: dynamic = fromIterable(arg33)
                date = unpacked34[0]
                log = unpacked34[1]
                return jsAnd(jsAnd(jsAtMost(date, today)) { jsAtLeast(date, E["plus"](today, (-numeric(7)))) }) { onPresent(log) { value35 -> value35.pain } }
            }))
            if (truth(recent)) {
                return jsonValue("{\"ok\":false,\"reason\":\"Se registró dolor en los últimos siete días. Se requiere revisar tu situación antes de generar nuevos ejercicios.\"}")
            }
            return jsObject { obj36 ->
                obj36["ok"] = true
                obj36["reason"] = mode.reason
            }
        }
        affectedAreas = fun(p: dynamic): dynamic {
            return jsConstruct(global_Set, arrayOf<dynamic>(actives(p).map((fun(x: dynamic): dynamic {
                return x.zone
            })).filter(global_Boolean)))
        }
        approvedExercise = fun(e: dynamic, p: dynamic): dynamic {
            if (truth(jsOr(jsOr(jsOr((!truth(e))) { (!truth(e.id)) }) { (!truth(e.name)) }) { (!truth(global_Array.isArray(e.zones))) })) {
                return false
            }
            if (truth(jsOr(jsOr(e.sourceOnly) { (e.variant !== "base") }) { jsAnd(e.gymOnly23) { (p.equipment !== "gym") } })) {
                return false
            }
            if (truth(jsMore(e.level, 1))) {
                return false
            }
            if (truth((e.impact === "Con saltos"))) {
                return false
            }
            if (truth(jsAnd(jsAnd((e.equipment === "db")) { (p.equipment !== "db") }) { (p.equipment !== "gym") })) {
                return false
            }
            val affected: dynamic = affectedAreas(p)
            if (truth(dynamicValue(jsOr(e.zones) { js("[]") }).some((fun(z: dynamic): dynamic {
                return affected.has(z)
            })))) {
                return false
            }
            if (truth(jsAnd(affected.has("knee")) { jsOr(jsAnd(e.floor) { (!truth(e.seated)) }) { dynamicValue(jsRegExp("rodillas apoyadas|cuadrupedia|cuatro apoyos", "i")).test(jsAdd(jsAdd(jsOr(e.name) { "" }, " "), jsOr(e.steps) { "" })) } })) {
                return false
            }
            if (truth(jsAnd(affected.has("ankle")) { dynamicValue(jsRegExp("bridge|puente de gl[uú]teos|talones|elevaci[oó]n de tal[oó]n", "i")).test(jsAdd(jsAdd(jsOr(e.id) { "" }, " "), jsOr(e.name) { "" })) })) {
                return false
            }
            if (truth(jsAnd(affected.has("wrist")) { dynamicValue(jsRegExp("boxeo|golpes|puños|punch|shadowbox", "i")).test(jsAdd(jsAdd(jsOr(e.name) { "" }, " "), jsOr(e.id) { "" })) })) {
                return false
            }
            if (truth(jsAnd(affected.has("neck")) { jsOr(e.overhead) { e.spineMove } })) {
                return false
            }
            if (truth(jsAnd(affected.has("back")) { jsOr(e.spineMove) { jsAnd(e.floor) { (e.group === "Core") } } })) {
                return false
            }
            if (truth(jsAnd(affected.has("knee")) { e.kneeBend })) {
                return false
            }
            if (truth(jsAnd(affected.has("wrist")) { e.wristLoad })) {
                return false
            }
            if (truth(jsAnd(affected.has("shoulder")) { e.overhead })) {
                return false
            }
            val explanations: dynamic = A.reasons(e, p).filter((fun(x: dynamic): dynamic {
                return (!truth(x.startsWith("Nivel superior al seleccionado")))
            }))
            if (truth(explanations.length)) {
                return false
            }
            return true
        }
        candidates = fun(p: dynamic, kind: dynamic): dynamic {
            val category: dynamic = CATS[kind]
            return E.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd((e.category === category)) { approvedExercise(e, p) }
            }))
        }
        uniqueExercises = fun(pool: dynamic): dynamic {
            val ids: dynamic = jsConstruct(global_Set, js("[]"))
            return pool.filter((fun(e: dynamic): dynamic {
                val base: dynamic = jsOr(e.baseId) { e.id }
                if (truth(ids.has(base))) {
                    return false
                }
                ids.add(base)
                return true
            }))
        }
        ordered = fun(p: dynamic, pool: dynamic, kind: dynamic, arg47: dynamic, arg48: dynamic): dynamic {
            var offset: dynamic = absent
            offset = definedOr(arg47) { 0 }
            var history: dynamic = absent
            history = definedOr(arg48) { js("[]") }
            val groups: dynamic = jsOr(GROUPS[p.focus]) { GROUPS.full }
            val prev: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(history.flatMap((fun(x: dynamic): dynamic {
                return dynamicValue(jsOr(x.items) { js("[]") }).map((fun(a: dynamic): dynamic {
                    return jsOr(onPresent(E.byId(a.id)) { value49 -> value49.baseId }) { a.id }
                }))
            }))))
            val grouped: dynamic = pool.map((fun(e: dynamic, i: dynamic): dynamic {
                return jsObject { obj50 ->
                    obj50["e"] = e
                    obj50["score"] = jsAdd(jsAdd(jsAdd((if (truth(groups.includes(e.group))) jsSubtract(6, jsMultiply(groups.indexOf(e.group), 0.25)) else 0), (if (truth((e.level === 0))) 5 else 0)), (if (truth(prev.has(jsOr(e.baseId) { e.id }))) (-numeric(3)) else 0)), (if (truth((e.equipment === "body"))) 1 else 0))
                    obj50["order"] = i
                }
            }))
            grouped.sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(jsSubtract(b.score, a.score)) { a.e.id.localeCompare(b.e.id) }
            }))
            val byGroup: dynamic = jsConstruct(global_Map, js("[]"))
            if (true) {
                val iterator51: dynamic = iteratorOf(grouped)
                while (true) {
                    val next52: dynamic = iterator51.next()
                    if (truth(next52.done)) break
                    var item: dynamic = absent
                    item = next52.value
                    val g: dynamic = item.e.group
                    if (truth((!truth(byGroup.has(g))))) {
                        byGroup["set"](g, js("[]"))
                    }
                    byGroup["get"](g).push(item.e)
                }
            }
            val keys: dynamic = jsArray { array53 ->
                appendAll(array53, byGroup.keys())
            }
            val rotated: dynamic = (if (truth(keys.length)) jsArray { array54 ->
                appendAll(array54, keys.slice(jsMod(offset, keys.length)))
                appendAll(array54, keys.slice(0, jsMod(offset, keys.length)))
            } else js("[]"))
            val selected: dynamic = js("[]")
            var turn: dynamic = 0
            while (truth(jsLess(selected.length, pool.length))) {
                var more: dynamic = false
                if (true) {
                    val iterator55: dynamic = iteratorOf(rotated)
                    while (true) {
                        val next56: dynamic = iterator55.next()
                        if (truth(next56.done)) break
                        var g: dynamic = absent
                        g = next56.value
                        val list: dynamic = byGroup["get"](g)
                        val e: dynamic = list[turn]
                        if (truth(e)) {
                            selected.push(e)
                            more = true
                        }
                    }
                }
                if (truth((!truth(more)))) {
                    break
                }
                run {
                    val previous57: dynamic = numeric(turn)
                    turn = jsAdd(previous57, 1)
                }
            }
            return selected
        }
        previousSessions = fun(logs: dynamic, now: dynamic): dynamic {
            return global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg63: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked64: dynamic = fromIterable(arg63)
                d = unpacked64[0]
                l = unpacked64[1]
                return jsAnd(jsAnd(jsAnd(jsLess(d, now)) { jsAtLeast(d, E["plus"](now, (-numeric(14)))) }) { onPresent(l) { value65 -> value65.complete } }) { (!truth(l.pain)) }
            })).map((fun(arg66: dynamic): dynamic {
                var date: dynamic = absent
                var l: dynamic = absent
                val unpacked67: dynamic = fromIterable(arg66)
                date = unpacked67[0]
                l = unpacked67[1]
                return jsObject { obj68 ->
                    copyFields(obj68, l)
                    obj68["date"] = date
                }
            }))
        }
        loadPolicy = fun(logs: dynamic, now: dynamic): dynamic {
            val recent: dynamic = previousSessions(logs, now)
            val effort: dynamic = recent.map((fun(l: dynamic): dynamic {
                return global_Number(l.rpe)
            })).filter((fun(n: dynamic): dynamic {
                return jsAnd(jsMore(n, 0)) { jsAtMost(n, 10) }
            }))
            val avg: dynamic = (if (truth(effort.length)) jsDivide(effort.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b)
            }), 0), effort.length) else 0)
            val fatigue: dynamic = recent.some((fun(l: dynamic): dynamic {
                return jsOr(l.fatigue) { jsAtLeast(l.rpe, 8) }
            }))
            return jsObject { obj71 ->
                obj71["fatigue"] = fatigue
                obj71["avg"] = avg
                obj71["completed"] = recent.length
                obj71["history"] = recent
                obj71["reduce"] = jsOr(fatigue) { jsMore(avg, 6) }
            }
        }
        choose = fun(p: dynamic, kind: dynamic, arg74: dynamic, arg75: dynamic, arg76: dynamic): dynamic {
            var rotation: dynamic = absent
            rotation = definedOr(arg74) { 0 }
            var logs: dynamic = absent
            logs = definedOr(arg75) { js("({})") }
            var date: dynamic = absent
            date = definedOr(arg76) { E.dateKey() }
            val gate: dynamic = clinicalGate(p, logs, E.dateKey())
            if (truth((!truth(gate.ok)))) {
                return jsObject { obj77 ->
                    obj77["items"] = js("[]")
                    obj77["blocked"] = gate.reason
                    obj77["kind"] = kind
                }
            }
            if (truth((kind === "rest"))) {
                return jsonValue("{\"items\":[],\"kind\":\"rest\"}")
            }
            val primary: dynamic = candidates(p, kind)
            val alternatives: dynamic = (if (truth((kind === "cardio"))) jsonValue("[\"recovery\",\"strength\"]") else (if (truth((kind === "strength"))) jsonValue("[\"recovery\",\"cardio\"]") else jsonValue("[\"cardio\",\"strength\"]")))
            var effectiveKind: dynamic = kind
            var pool: dynamic = primary
            if (truth((!truth(pool.length)))) {
                if (true) {
                    val iterator78: dynamic = iteratorOf(alternatives)
                    while (true) {
                        val next79: dynamic = iterator78.next()
                        if (truth(next79.done)) break
                        var other: dynamic = absent
                        other = next79.value
                        val list: dynamic = candidates(p, other)
                        if (truth(list.length)) {
                            pool = list
                            effectiveKind = other
                            break
                        }
                    }
                }
            }
            if (truth((!truth(pool.length)))) {
                return jsObject { obj80 ->
                    obj80["items"] = js("[]")
                    obj80["kind"] = kind
                    obj80["blocked"] = "No hay ejercicios con compatibilidad verificable según las zonas, el equipo y las restricciones del perfil. No se sustituye por descanso."
                }
            }
            val recent: dynamic = previousSessions(logs, date)
            val selection: dynamic = ordered(p, uniqueExercises(pool), effectiveKind, global_Math.abs(rotation), recent)
            val minutes: dynamic = global_Math.max(10, global_Math.min(45, jsOr(global_Number(p.minutes)) { 20 }))
            val cap: dynamic = (if (truth(jsAtMost(minutes, 10))) 2 else (if (truth(jsAtMost(minutes, 20))) 3 else (if (truth(jsAtMost(minutes, 30))) 4 else 5)))
            val chosen: dynamic = selection.slice(0, global_Math.min(cap, selection.length))
            val feedback: dynamic = loadPolicy(logs, date)
            val items: dynamic = chosen.map((fun(e: dynamic): dynamic {
                return jsObject { obj81 ->
                    obj81["id"] = e.id
                    obj81["sets"] = 1
                    obj81["target"] = (if (truth((e.unit === "sec"))) (if (truth(feedback.reduce)) 12 else 20) else (if (truth(feedback.reduce)) 4 else (if (truth(e.level)) 5 else 7)))
                    obj81["rest"] = (if (truth((effectiveKind === "recovery"))) 60 else 90)
                    obj81["adaptation378"] = true
                    obj81["noAutoLoad"] = true
                    obj81["safetyNote"] = "Movimiento controlado, intensidad baja y recorrido tolerable; sin dolor ni carga obligatoria."
                }
            }))
            return jsObject { obj82 ->
                obj82["items"] = items.map((fun(x: dynamic): dynamic {
                    return jsOr(onPresent(root.HunterCatalog46) { value83 -> value83.cap(E.byId(x.id), p, x, jsObject { obj84 ->
                        obj84["status"] = jsonValue("{\"reduced\":true}")
                        obj84["logs"] = logs
                        obj84["date"] = date
                    }) }) { x }
                }))
                obj82["kind"] = effectiveKind
                obj82["originalKind"] = kind
                obj82["substituted"] = (effectiveKind !== kind)
                obj82["feedback"] = feedback
            }
        }
        patchDay = fun(day: dynamic, p: dynamic, rotation: dynamic, logs: dynamic, today: dynamic, last: dynamic): dynamic {
            if (truth(jsOr(jsOr(jsOr((!truth(day))) { jsLess(day.date, today) }) { preserve(day, logs) }) { onPresent(root.HunterStable3828) { value87 -> run { val owner89: dynamic = value87; onPresent(owner89.keep) { method88 -> invokeWith(method88, owner89, arrayOf<dynamic>(day, p, logs, today)) } } } })) {
                return false
            }
            val choice: dynamic = preference(p, day.date)
            if (truth((!truth(choice)))) {
                return false
            }
            val originalKind: dynamic = MAP[choice]
            if (truth((choice === "rest"))) {
                if (truth(jsOr((day.kind !== "rest")) { onPresent(day.items) { value90 -> value90.length } })) {
                    day.kind = "rest"
                    day.items = js("[]")
                    day.tasks = jsonValue("[{\"id\":\"rest\",\"text\":\"Respetar el descanso elegido\",\"done\":false}]")
                    day.title = "Descanso elegido"
                    removeKey(day, "blocked378")
                    removeKey(day, "adapted378")
                    return true
                }
                return false
            }
            val key: dynamic = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(planKey(p), ":"), choice), ":"), day.date), ":"), global_String(global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg116: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked117: dynamic = fromIterable(arg116)
                d = unpacked117[0]
                l = unpacked117[1]
                return jsAnd(jsAnd(jsAtLeast(d, E["plus"](today, (-numeric(7))))) { jsAtMost(d, today) }) { jsOr(jsOr(onPresent(l) { value118 -> value118.pain }) { onPresent(l) { value119 -> value119.fatigue } }) { onPresent(l) { value120 -> value120.complete } } }
            })).map((fun(arg121: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked122: dynamic = fromIterable(arg121)
                d = unpacked122[0]
                l = unpacked122[1]
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(d, ":"), l.rpe), ":"), l.complete), ":"), l.pain)
            })).join("|")))
            if (truth(jsAnd((day.planningKey378 === key)) { day.adapted378 })) {
                return false
            }
            val proposed: dynamic = choose(p, originalKind, rotation, logs, day.date)
            day.kind = proposed.kind
            day.items = proposed.items
            day.scheduleChoice = choice
            day.adapted378 = true
            day.planningKey378 = key
            day.adaptation = "injury-alternatives-378"
            day.originalCategory378 = NAMES[choice]
            if (truth(proposed.blocked)) {
                day.blocked378 = proposed.blocked
                day.title = jsAdd(NAMES[choice], " · pendiente de valoración")
                day.tasks = js("[]")
                day.planningNote = "No se indican ejercicios automáticos mientras no haya compatibilidad suficiente o autorización."
            } else {
                removeKey(day, "blocked378")
                day.tasks = js("[]")
                day.title = (if (truth(proposed.substituted)) jsAdd(jsAdd(jsAdd(NAMES[choice], " → "), jsonValue("{\"strength\":\"Fuerza\",\"cardio\":\"Cardio\",\"recovery\":\"Movilidad\"}")[day.kind]), " suave") else jsAdd(NAMES[choice], " adaptada · baja intensidad"))
                day.planningNote = jsAdd((if (truth(proposed.substituted)) "La actividad elegida fue sustituida por otra categoría compatible. " else ""), "Ejercicios seleccionados mediante exclusión de zonas y movimientos restringidos, con una sola serie y esfuerzo reducido. Revisión según el esfuerzo y el dolor registrado.")
            }
            removeKey(day, "autoTemplateId")
            return true
        }
        patchCycle = fun(c: dynamic, p: dynamic, arg153: dynamic, arg154: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg153) { js("({})") }
            var today: dynamic = absent
            today = definedOr(arg154) { E.dateKey() }
            if (truth(jsOr(jsOr(jsOr((!truth(onPresent(c) { value155 -> onPresent(value155.days) { value156 -> value156.length } }))) { (!truth(p)) }) { (!truth(S.valid(p))) }) { (!truth(relevant(p))) })) {
                return 0
            }
            var changed: dynamic = 0
            c.days.forEach((fun(d: dynamic, i: dynamic): dynamic {
                if (truth(patchDay(d, p, jsAdd(i, jsMultiply(c.index, 5)), logs, today))) {
                    run {
                        val previous157: dynamic = numeric(changed)
                        changed = jsAdd(previous157, 1)
                    }
                }
                return absent
            }))
            if (truth(changed)) {
                c.injuryPlannerVersion378 = VERSION
                c.decision = "Calendario elegido respetado. Las sesiones permitidas se rellenan con alternativas de menor exigencia según zonas declaradas; no constituyen rehabilitación."
            }
            return changed
        }
        adaptedTemplates = fun(p: dynamic, arg188: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg188) { js("({})") }
            if (truth(jsOr(jsOr((!truth(p))) { (!truth(relevant(p))) }) { (!truth(clinicalGate(p, logs).ok)) })) {
                return js("[]")
            }
            val types: dynamic = jsonValue("[[\"strength\",\"Fuerza\"],[\"cardio\",\"Cardio\"],[\"recovery\",\"Movilidad\"]]")
            val templates: dynamic = js("[]")
            val seen: dynamic = jsConstruct(global_Set, js("[]"))
            if (true) {
                val iterator189: dynamic = iteratorOf(types)
                while (true) {
                    val next190: dynamic = iterator189.next()
                    if (truth(next190.done)) break
                    var type: dynamic = absent
                    var category: dynamic = absent
                    val unpacked191: dynamic = fromIterable(next190.value)
                    type = unpacked191[0]
                    category = unpacked191[1]
                    if (true) {
                        var variant: dynamic = absent
                        variant = 0
                        var firstIteration192 = true
                        while (true) {
                            if (!firstIteration192) {
                                run {
                                    val previous193: dynamic = numeric(variant)
                                    variant = jsAdd(previous193, 1)
                                }
                            }
                            firstIteration192 = false
                            if (!truth(jsLess(variant, 3))) break
                            val sample: dynamic = choose(p, type, jsAdd(jsMultiply(variant, 3), 1), logs)
                            val items: dynamic = sample.items
                            if (truth((!truth(items.length)))) {
                                continue
                            }
                            val stamp: dynamic = items.map((fun(x: dynamic): dynamic {
                                return x.id
                            })).sort().join(",")
                            if (truth(seen.has(stamp))) {
                                continue
                            }
                            seen.add(stamp)
                            val requirements: dynamic = jsArray { array194 ->
                                appendAll(array194, jsConstruct(global_Set, arrayOf<dynamic>(items.flatMap((fun(x: dynamic): dynamic {
                                    return jsOr(onPresent(E.byId(x.id)) { value195 -> value195.requirements }) { js("[]") }
                                })))))
                            }
                            templates.push(jsObject { obj196 ->
                                obj196["id"] = jsAdd(jsAdd(jsAdd("injury378-", type), "-"), variant)
                                obj196["name"] = jsAdd(jsAdd(category, " suave · alternativa "), jsAdd(variant, 1))
                                obj196["category"] = (if (truth((sample.kind === "strength"))) "Fuerza" else (if (truth((sample.kind === "cardio"))) "Cardio" else "Movilidad"))
                                obj196["description"] = "Selección reducida de movimientos que no incluyen zonas o restricciones declaradas; requiere autorización para entrenar."
                                obj196["items"] = items
                                obj196["requirements"] = requirements
                                obj196["level"] = 0
                                obj196["extension"] = "standard"
                                obj196["goalGroups"] = jsonValue("[\"fitness\",\"habit\",\"movement\",\"build\",\"athletic\"]")
                                obj196["family"] = "adapted"
                                obj196["impact"] = "Sin saltos"
                                obj196["minutes"] = global_Math.max(5, jsMultiply(items.length, 4))
                                obj196["safe378"] = true
                            })
                        }
                    }
                }
            }
            return templates
        }
        E = root.Engine
        A = root.Adapt
        S = root.Schedule
        if (truth(jsOr(jsOr(jsOr((!truth(E))) { (!truth(A)) }) { (!truth(S)) }) { (!truth(root.Weekly)) })) {
            return absent
        }
        VERSION = "37.8"
        MAP = jsonValue("{\"train\":\"strength\",\"cardio\":\"cardio\",\"mobility\":\"recovery\",\"rest\":\"rest\"}")
        NAMES = jsonValue("{\"train\":\"Fuerza\",\"cardio\":\"Cardio\",\"mobility\":\"Movilidad\",\"rest\":\"Descanso\"}")
        CATS = jsonValue("{\"strength\":\"Fuerza\",\"cardio\":\"Cardio\",\"recovery\":\"Movilidad\"}")
        GROUPS = jsonValue("{\"upper\":[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"],\"lower\":[\"Piernas\",\"Cadera\"],\"core\":[\"Core\",\"Abdomen\"],\"full\":[\"Piernas\",\"Cadera\",\"Pecho\",\"Espalda\",\"Brazos\",\"Hombros\",\"Core\",\"Abdomen\"]}")
        val DAYTYPE: dynamic = jsonValue("[\"rest\",\"train\",\"cardio\",\"mobility\"]")
        val escapeHtml: dynamic = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        actives = (fun(p: dynamic): dynamic {
            return A.active(jsOr(p) { js("({})") })
        })
        relevant = (fun(p: dynamic): dynamic {
            return jsOr(jsOr(jsMore(actives(p).length, 0)) { (!truth((!truth(onPresent(p) { value3 -> onPresent(value3.health) { value4 -> value4.special } })))) }) { (!truth((!truth(onPresent(p) { value5 -> value5.limits })))) }
        })
        preserve = (fun(d: dynamic, logs: dynamic): dynamic {
            return (!truth((!truth(jsOr(jsOr(jsOr(d.customized) { d.routineId }) { root.Weekly.active(onPresent(logs) { value6 -> value6[d.date] }) }) { onPresent(d.tasks) { value7 -> value7.some((fun(t: dynamic): dynamic {
                return t.done
            })) } }))))
        })
        preference = (fun(p: dynamic, date: dynamic): dynamic {
            return (if (truth(S.valid(p))) p.weekPlan[S.weekday(date)] else null)
        })
        simpleHash = (fun(x: dynamic): dynamic {
            var h: dynamic = 2166136261.0
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration8 = true
                while (true) {
                    if (!firstIteration8) {
                        run {
                            val previous9: dynamic = numeric(i)
                            i = jsAdd(previous9, 1)
                        }
                    }
                    firstIteration8 = false
                    if (!truth(jsLess(i, x.length))) break
                    run {
                        h = jsBitXor(h, x.charCodeAt(i))
                    }
                    h = global_Math.imul(h, 16777619)
                }
            }
            return dynamicValue(jsUnsignedShift(h, 0))["toString"](16)
        })
        val olderMake: dynamic = E.makeCycle
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg166: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg166) { js("({})") }
            val c: dynamic = olderMake(p, start, index, last, logs)
            patchCycle(c, p, logs, start)
            return c
        })
        val olderCurrent: dynamic = jsOr(root.currentCycle) { global_currentCycle }
        global_currentCycle = (fun(): dynamic {
            val c: dynamic = olderCurrent()
            if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
                return c
            }
            if (truth(jsAnd(patchCycle(c, global_state.profile, jsOr(global_state.logs) { js("({})") }, E.dateKey())) { (js("typeof save") === "function") })) {
                global_save()
            }
            return c
        })
        val olderWorkout: dynamic = global_workout
        global_workout = (fun(): dynamic {
            val d: dynamic = (if (truth((js("typeof todayDay") === "function"))) global_todayDay() else null)
            if (truth(jsOr((!truth(d))) { (!truth(relevant(global_state.profile))) })) {
                return olderWorkout()
            }
            if (truth(d.blocked378)) {
                return jsAdd(jsAdd(jsAdd(jsAdd("<section class=\"card injury-block378\" role=\"status\"><span class=\"eyebrow\">SESIÓN SELECCIONADA · ", escapeHtml(jsOr(d.originalCategory378) { "Entrenamiento" })), "</span><h2>Necesita valoración antes de asignar ejercicios</h2><p>"), escapeHtml(d.blocked378)), "</p><p class=\"footnote\">El día mantiene su tipo elegido y no se cuenta como descanso. Por seguridad, el sistema no inicia una rutina automática sin información suficiente. Puedes actualizar las restricciones y la actividad autorizada en Perfil.</p><button class=\"btn ghost full\" onclick=\"go('profile')\">Revisar perfil</button></section>")
            }
            val html: dynamic = olderWorkout()
            if (truth(jsOr((!truth(d.adapted378))) { (!truth(onPresent(d.items) { value171 -> value171.length })) })) {
                return html
            }
            return jsAdd(jsAdd(jsAdd("<div class=\"notice injury-banner378\" role=\"note\"><b>Sesión adaptada a las limitaciones declaradas</b><p>", escapeHtml(jsOr(d.planningNote) { "" })), "</p><small>1 serie por ejercicio · intensidad reducida · descansos ampliados. Interrumpe ante dolor nuevo o empeoramiento. Las reglas no verifican la seguridad clínica ni la técnica individual.</small></div>"), html)
        })
        val olderPlan: dynamic = global_plan
        global_plan = (fun(): dynamic {
            val c: dynamic = global_currentCycle()
            val html: dynamic = olderPlan()
            if (truth(jsOr((!truth(relevant(global_state.profile)))) { (!truth(c)) })) {
                return html
            }
            val days: dynamic = c.days.filter((fun(x: dynamic): dynamic {
                return x.adapted378
            }))
            val ready: dynamic = days.filter((fun(d: dynamic): dynamic {
                return onPresent(d.items) { value174 -> value174.length }
            })).length
            val blocked: dynamic = days.filter((fun(d: dynamic): dynamic {
                return d.blocked378
            })).length
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section class=\"card injury-summary378\"><span class=\"eyebrow\">ACOMPAÑANTE · SESIONES ADAPTADAS</span><h3>Planificación con restricciones</h3><p>", ready), " días con ejercicios alternativos de baja exigencia; "), blocked), " días pendientes de valoración. Los descansos que elegiste permanecen sin entrenamientos.</p><p class=\"footnote\">La selección es una comprobación técnica de zonas, no una valoración médica ni un programa de rehabilitación.</p></section>"), html)
        })
        val oldAnswer: dynamic = global_answerCoach
        global_answerCoach = (fun(q: dynamic): dynamic {
            val message: dynamic = global_String(jsOr(q) { "" })
            if (truth(jsAnd(jsAnd(global_state.profile) { relevant(global_state.profile) }) { dynamicValue(jsRegExp("(lesion|lesi[oó]n|dolor|ejercicio|rutina adaptada|entrenamiento con)", "i")).test(message) })) {
                val c: dynamic = global_currentCycle()
                val today: dynamic = onPresent(c) { value177 -> onPresent(value177.days) { value178 -> value178.find((fun(d: dynamic): dynamic {
                    return (d.date === E.dateKey())
                })) } }
                if (truth(onPresent(today) { value179 -> value179.blocked378 })) {
                    return jsAdd(jsAdd(global_companionDisplayName(), ": Tienes un día de entrenamiento planificado, pero no he asignado ejercicios automáticos: "), today.blocked378)
                }
                if (truth(jsAnd(onPresent(today) { value180 -> value180.adapted378 }) { onPresent(today.items) { value181 -> value181.length } })) {
                    return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(global_companionDisplayName(), ": "), today.title), ". Ejercicios programados: "), today.items.map((fun(x: dynamic): dynamic {
                        return onPresent(E.byId(x.id)) { value185 -> value185.name }
                    })).filter(global_Boolean).join(", ")), ". Todos están en dosis reducida y excluyen las zonas y restricciones declaradas. No sustituyen una evaluación clínica.")
                }
            }
            return oldAnswer(q)
        })
        val olderAllRoutines: dynamic = global_allRoutines
        global_allRoutines = (fun(): dynamic {
            return jsArray { array199 ->
                appendAll(array199, olderAllRoutines())
                appendAll(array199, adaptedTemplates(global_state.profile, jsOr(global_state.logs) { js("({})") }))
            }
        })
        val prevCompatible: dynamic = A.routineCompatible
        A.routineCompatible = (fun(r: dynamic, p: dynamic): dynamic {
            if (truth(onPresent(r) { value204 -> value204.safe378 })) {
                return jsAnd(jsAnd(clinicalGate(p, jsOr(global_state.logs) { js("({})") }).ok) { jsMore(r.items.length, 0) }) { r.items.every((fun(x: dynamic): dynamic {
                    return approvedExercise(E.byId(x.id), p)
                })) }
            }
            return prevCompatible(r, p)
        })
        val previousRoutineList: dynamic = global_routineList
        global_routineList = (fun(): dynamic {
            val list: dynamic = previousRoutineList()
            if (truth(jsOr((!truth(global_state.profile))) { (!truth(relevant(global_state.profile))) })) {
                return list
            }
            val p: dynamic = global_GoalPlans.adjusted(global_state.profile)
            return list.filter((fun(r: dynamic): dynamic {
                return jsAnd(A.routineCompatible(r, p)) { global_Catalog.available(r, p) }
            }))
        })
        val previousRoutinesView: dynamic = global_routines
        global_routines = (fun(): dynamic {
            val html: dynamic = previousRoutinesView()
            if (truth((!truth(relevant(global_state.profile))))) {
                return html
            }
            val gate: dynamic = clinicalGate(global_state.profile, jsOr(global_state.logs) { js("({})") })
            val note: dynamic = (if (truth(gate.ok)) "Se muestran únicamente rutinas compatibles con las zonas y restricciones registradas. Las variantes adaptadas incluyen series reducidas." else jsAdd("Se ha suspendido la asignación automática de ejercicios: ", gate.reason))
            return jsAdd(jsAdd(jsAdd("<div class=\"notice injury-banner378\" role=\"note\">", escapeHtml(note)), "</div>"), html)
        })
        val prevRoutineDetail: dynamic = global_routineDetail
        global_routineDetail = (fun(id: dynamic): dynamic {
            prevRoutineDetail(id)
            if (truth((!truth(onPresent(id) { value211 -> value211.startsWith("injury378-") })))) {
                return absent
            }
            val select: dynamic = global_document.querySelector("#routineDay")
            if (truth(select)) {
                if (true) {
                    val iterator212: dynamic = iteratorOf(jsArray { array214 ->
                        appendAll(array214, select.options)
                    })
                    while (true) {
                        val next213: dynamic = iterator212.next()
                        if (truth(next213.done)) break
                        var opt: dynamic = absent
                        opt = next213.value
                        val c: dynamic = global_currentCycle()
                        val d: dynamic = onPresent(c) { value215 -> onPresent(value215.days) { value216 -> value216.find((fun(x: dynamic): dynamic {
                            return (x.date === opt["value"])
                        })) } }
                        if (truth(jsOr((!truth(d))) { (preference(global_state.profile, d.date) === "rest") })) {
                            opt.remove()
                        }
                    }
                }
            }
            return absent
        })
        val prevPrepareRoutine: dynamic = global_prepareRoutine
        global_prepareRoutine = (fun(id: dynamic): dynamic {
            if (truth(onPresent(id) { value219 -> value219.startsWith("injury378-") })) {
                val date: dynamic = onPresent(global_document.querySelector("#routineDay")) { value220 -> value220["value"] }
                if (truth(jsOr((!truth(date))) { (preference(global_state.profile, date) === "rest") })) {
                    return global_routineNotice372("Selecciona un día programado para entrenar. No se modifican los descansos elegidos.")
                }
            }
            return prevPrepareRoutine(id)
        })
        root.HunterInjuryPlanner378 = jsObject { obj223 ->
            obj223["version"] = VERSION
            obj223["choose"] = choose
            obj223["approvedExercise"] = approvedExercise
            obj223["candidates"] = candidates
            obj223["patchCycle"] = patchCycle
            obj223["clinicalGate"] = clinicalGate
            obj223["planKey"] = planKey
            obj223["loadPolicy"] = loadPolicy
            obj223["adaptedTemplates"] = adaptedTemplates
        }
        try {
            global_render()
        } catch (e: dynamic) {
            global_console.warn("Motor de lesiones 37.8", e)
        }
        return absent
    }))(global_window)
}
