@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installtrainingv30

import hunter.browser.*

@JsName("Engine") private external var global_Engine: dynamic
@JsName("TrainingProgression19") private external var global_TrainingProgression19: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("AdaptiveV9") private external var global_AdaptiveV9: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("GoalContext24") private external var global_GoalContext24: dynamic
@JsName("Training23") private external var global_Training23: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("Catalogue30") private external var global_Catalogue30: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installTrainingV30() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var T: dynamic = absent
        var O: dynamic = absent
        var tier: dynamic = absent
        var inFamily: dynamic = absent
        var pool: dynamic = absent
        var select: dynamic = absent
        var dose: dynamic = absent
        var choose: dynamic = absent
        var adjust: dynamic = absent
        var assign: dynamic = absent
        pool = fun(p: dynamic, arg23: dynamic): dynamic {
            var f: dynamic = absent
            f = definedOr(arg23) { "full" }
            return E.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(inFamily(e, f)) { jsAtMost(tier(e), T.level(p)) }) { global_Adapt.allowed(e, p) }) { (if (truth((p.equipment === "gym"))) e.gymOnly23 else (!truth(e.gymOnly23))) }
            }))
        }
        select = fun(list: dynamic, count: dynamic, arg26: dynamic, arg27: dynamic): dynamic {
            var rotation: dynamic = absent
            rotation = definedOr(arg26) { 0 }
            var p: dynamic = absent
            p = definedOr(arg27) { null }
            if (truth((!truth(list.length)))) {
                return js("[]")
            }
            val buckets: dynamic = jsArray { array28 ->
                appendAll(array28, jsConstruct(global_Set, arrayOf<dynamic>(list.map((fun(e: dynamic): dynamic {
                    return e.group
                })))))
            }
            val used: dynamic = jsConstruct(global_Set, js("[]"))
            val `out`: dynamic = js("[]")
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration29 = true
                while (true) {
                    if (!firstIteration29) {
                        run {
                            val previous30: dynamic = numeric(i)
                            i = jsAdd(previous30, 1)
                        }
                    }
                    firstIteration29 = false
                    if (!truth(jsLess(i, count))) break
                    var candidates: dynamic = list.filter((fun(e: dynamic): dynamic {
                        return jsAnd((!truth(used.has(e.baseId)))) { (e.group === buckets[jsMod(i, buckets.length)]) }
                    }))
                    if (truth((!truth(candidates.length)))) {
                        candidates = list.filter((fun(e: dynamic): dynamic {
                            return (!truth(used.has(e.baseId)))
                        }))
                    }
                    if (truth((!truth(candidates.length)))) {
                        break
                    }
                    val targetTier: dynamic = (if (truth(p)) T.level(p) else run { val target32: dynamic = global_Math; invokeWith(target32.max, target32, jsArray { array31 ->
                        appendAll(array31, candidates.map(tier))
                    }) })
                    val high: dynamic = candidates.filter((fun(e: dynamic): dynamic {
                        return (tier(e) === targetTier)
                    }))
                    if (truth(high.length)) {
                        candidates = high
                    }
                    if (truth(p)) {
                        val goalBest: dynamic = run { val target34: dynamic = global_Math; invokeWith(target34.max, target34, jsArray { array33 ->
                            appendAll(array33, candidates.map((fun(e: dynamic): dynamic {
                                return global_GoalContext24.score(e, p)
                            })))
                        }) }
                        val relevant: dynamic = candidates.filter((fun(e: dynamic): dynamic {
                            return jsAtLeast(global_GoalContext24.score(e, p), jsSubtract(goalBest, 1))
                        }))
                        if (truth(relevant.length)) {
                            candidates = relevant
                        }
                    }
                    candidates.sort((fun(a: dynamic, b: dynamic): dynamic {
                        return a.id.localeCompare(b.id)
                    }))
                    val pick: dynamic = candidates[jsMod(jsAdd(global_Math.abs(rotation), jsMultiply(global_Math.floor(jsDivide(i, buckets.length)), 3)), candidates.length)]
                    `out`.push(pick)
                    used.add(pick.baseId)
                }
            }
            return `out`
        }
        dose = fun(e: dynamic, p: dynamic, kind: dynamic): dynamic {
            val base: dynamic = global_Training23.dose(e, p, kind)
            return jsObject { obj37 ->
                copyFields(obj37, base)
                obj37["rest"] = T.rest(p)
            }
        }
        choose = fun(profile: dynamic, kind: dynamic, arg40: dynamic): dynamic {
            var rotation: dynamic = absent
            rotation = definedOr(arg40) { 0 }
            val p: dynamic = global_GoalContext24.adjusted(profile)
            if (truth((!truth(global_Adapt.mode(p).auto)))) {
                return js("[]")
            }
            val f: dynamic = (if (truth((kind === "cardio"))) "cardio" else (if (truth((kind === "recovery"))) "mobility" else (if (truth((p.focus === "lower"))) "legs" else (if (truth((p.focus === "core"))) "core" else (if (truth((p.focus === "upper"))) "upper" else "full")))))
            var list: dynamic = (if (truth((f === "upper"))) jsArray { array41 ->
                appendAll(array41, pool(p, "push"))
                appendAll(array41, pool(p, "pull"))
            } else pool(p, f))
            if (truth(jsAnd(jsAnd((!truth(list.length))) { (kind === "recovery") }) { (p.equipment === "gym") })) {
                list = pool(p, "cardio")
            }
            if (truth(jsAnd((!truth(list.length))) { (kind === "strength") })) {
                list = pool(p, "full")
            }
            val count: dynamic = (if (truth((kind === "recovery"))) 3 else (if (truth((p.trainingMode === "routines"))) 5 else jsOr(p.dailyMissions) { 4 }))
            return select(list, count, rotation, p).map((fun(e: dynamic): dynamic {
                return dose(e, p, kind)
            }))
        }
        adjust = fun(r: dynamic, p: dynamic): dynamic {
            return r.items.map((fun(x: dynamic): dynamic {
                return jsObject { obj88 ->
                    copyFields(obj88, x)
                    copyFields(obj88, dose(E.byId(x.id), p, (if (truth((r.category === "Cardio"))) "cardio" else (if (truth((r.category === "Movilidad"))) "recovery" else "strength"))))
                }
            }))
        }
        assign = fun(c: dynamic, profile: dynamic, arg103: dynamic, arg104: dynamic, arg105: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg103) { js("({})") }
            var now: dynamic = absent
            now = definedOr(arg104) { E.dateKey() }
            var last: dynamic = absent
            last = definedOr(arg105) { null }
            val p: dynamic = global_GoalContext24.adjusted(profile)
            val planningKey: dynamic = global_JSON.stringify(arrayOf<dynamic>(30, profile, global_GoalContext24.personal()))
            val review: dynamic = onPresent(c.reviews) { value106 -> value106.at((-numeric(1))) }
            if (truth(jsOr((!truth(global_Adapt.mode(p).auto))) { (onPresent(review) { value107 -> value107.action } === "pausar") })) {
                return 0
            }
            if (truth(jsAnd(jsAnd((c.engineVersion30 === 30)) { (c.planningKey30 === planningKey) }) { (!truth(jsAnd((onPresent(review) { value108 -> value108.action } === "reducir")) { (!truth(c.reductionApplied30)) })) })) {
                return 0
            }
            var changed: dynamic = 0
            if (true) {
                val iterator109: dynamic = iteratorOf(c.days.entries())
                while (true) {
                    val next110: dynamic = iterator109.next()
                    if (truth(next110.done)) break
                    var i: dynamic = absent
                    var d: dynamic = absent
                    val unpacked111: dynamic = fromIterable(next110.value)
                    i = unpacked111[0]
                    d = unpacked111[1]
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, now)) { (d.kind === "rest") }) { d.customized }) { d.routineId }) { global_Weekly.active(logs[d.date]) }) { onPresent(d.tasks) { value112 -> value112.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) } })) {
                        continue
                    }
                    var items: dynamic = dynamicValue(null)
                    if (truth((p.trainingMode === "routines"))) {
                        val goal: dynamic = global_GoalContext24.interpret(p)
                        val matches: dynamic = global_Catalog.routines.filter((fun(r: dynamic): dynamic {
                            return jsAnd(jsAnd(jsAnd(jsAnd((r.level === T.level(p))) { ((!truth((!truth(r.gymOnly23)))) === (p.equipment === "gym")) }) { (r.extension === "standard") }) { r.goalGroups.some((fun(g: dynamic): dynamic {
                                return goal.priorities.includes(g)
                            })) }) { global_Adapt.routineCompatible(r, p) }
                        }))
                        var list: dynamic = matches.filter((fun(r: dynamic): dynamic {
                            return (if (truth((d.kind === "cardio"))) (r.category === "Cardio") else (if (truth((d.kind === "recovery"))) (r.category === "Movilidad") else (r.category === "Fuerza")))
                        }))
                        if (truth(jsAnd((!truth(list.length))) { (d.kind === "strength") })) {
                            list = matches
                        }
                        if (truth(list.length)) {
                            val r: dynamic = list[jsMod(jsAdd(i, jsMultiply(c.index, 7)), list.length)]
                            items = adjust(r, p)
                            d.title = r.name
                            d.autoTemplateId = r.id
                        }
                    }
                    if (truth((!truth(onPresent(items) { value117 -> value117.length })))) {
                        items = choose(p, d.kind, jsAdd(i, jsMultiply(c.index, 11)))
                        d.title = jsAdd("Misiones personales · ", O.names[T.level(p)])
                    }
                    if (truth((!truth(items.length)))) {
                        continue
                    }
                    if (truth(jsAnd(jsAnd(jsAnd(last) { (d.kind === "strength") }) { (!truth(O.gentle(p))) }) { (T.level(jsOr(last.profile) { p }) === T.level(p)) })) {
                        val validGroups: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(items.map((fun(x: dynamic): dynamic {
                            return E.byId(x.id).group
                        }))))
                        val used: dynamic = jsConstruct(global_Set, js("[]"))
                        val anchors: dynamic = js("[]")
                        val referenceSets: dynamic = js("({})")
                        if (true) {
                            val iterator120: dynamic = iteratorOf(last.days.filter((fun(x: dynamic): dynamic {
                                return (x.kind === "strength")
                            })).flatMap((fun(x: dynamic): dynamic {
                                return jsOr(x.items) { js("[]") }
                            })))
                            while (true) {
                                val next121: dynamic = iterator120.next()
                                if (truth(next121.done)) break
                                var x: dynamic = absent
                                x = next121.value
                                val e: dynamic = E.byId(x.id)
                                if (truth(jsOr(jsOr(jsOr(jsOr((!truth(e))) { used.has(e.baseId) }) { (!truth(validGroups.has(e.group))) }) { (!truth(global_Adapt.allowed(e, p))) }) { jsMore(tier(e), T.level(p)) })) {
                                    continue
                                }
                                anchors.push(dose(e, p, "strength"))
                                referenceSets[e.id] = x.sets
                                used.add(e.baseId)
                                if (truth(jsAtLeast(anchors.length, global_Math.min(2, items.length)))) {
                                    break
                                }
                            }
                        }
                        if (truth(anchors.length)) {
                            items = dynamicValue(jsArray { array125 ->
                                appendAll(array125, anchors)
                                appendAll(array125, items.filter((fun(x: dynamic): dynamic {
                                    return (!truth(used.has(E.byId(x.id).baseId)))
                                })))
                            }).slice(0, items.length)
                            d.referenceIds30 = anchors.map((fun(x: dynamic): dynamic {
                                return x.id
                            }))
                            d.referenceSets30 = referenceSets
                        }
                    }
                    val old: dynamic = jsObject { obj130 ->
                        obj130["days"] = arrayOf<dynamic>(jsObject { obj131 ->
                            obj131["items"] = jsOr(d.items) { js("[]") }
                        })
                    }
                    items = items.map((fun(x: dynamic): dynamic {
                        var carried: dynamic = T.carry(T.carry(x, last, p), old, p)
                        if (truth(jsAnd(onPresent(d.referenceIds30) { value132 -> value132.includes(carried.id) }) { (!truth(O.gentle(p))) })) {
                            carried = jsObject { obj133 ->
                                copyFields(obj133, carried)
                                obj133["sets"] = jsOr(onPresent(d.referenceSets30) { value134 -> value134[carried.id] }) { carried.sets }
                            }
                            return T.applyRecommendation(carried, global_AdaptiveV9.recommend(logs, carried, p, now), p)
                        }
                        return carried
                    }))
                    if (truth((onPresent(c.reviews) { value135 -> onPresent(value135.at((-numeric(1)))) { value136 -> value136.action } } === "reducir"))) {
                        items = items.map((fun(x: dynamic): dynamic {
                            return jsObject { obj137 ->
                                copyFields(obj137, x)
                                obj137["target"] = global_Math.max((if (truth((E.unitOf(x) === "sec"))) 10 else 3), global_Math.floor(jsMultiply(x.target, 0.8)))
                            }
                        }))
                    }
                    d.items = items
                    d.tasks = js("[]")
                    d.goalIntent24 = global_GoalContext24.interpret(p).reason
                    d.planningNote = "Selección del catálogo completo según objetivo, equipo, nivel y restricciones. Revisión semanal con series confirmadas."
                    run {
                        val previous146: dynamic = numeric(changed)
                        changed = jsAdd(previous146, 1)
                    }
                }
            }
            c.engineVersion30 = 30
            c.planningKey30 = planningKey
            if (truth((onPresent(review) { value151 -> value151.action } === "reducir"))) {
                c.reductionApplied30 = true
            }
            return changed
        }
        E = global_Engine
        T = global_TrainingProgression19
        O = global_OwnRoutines18
        val oldReasons: dynamic = global_Adapt.reasons
        val oldMake: dynamic = E.makeCycle
        val oldRecommend: dynamic = global_AdaptiveV9.recommend
        if (true) {
            val iterator3: dynamic = iteratorOf(E.exercises)
            while (true) {
                val next4: dynamic = iterator3.next()
                if (truth(next4.done)) break
                var e: dynamic = absent
                e = next4.value
                e.sourceOnly = false
                e.demoPath30 = jsAdd(jsAdd("demos30/", e.id), ".svg")
                e.modelPath30 = jsAdd(jsAdd("demos30/", e.id), ".json")
                e.planFamily30 = (if (truth((e.group === "Cardio"))) "cardio" else (if (truth((e.group === "Movilidad"))) "mobility" else (if (truth(dynamicValue(jsonValue("[\"Piernas\",\"Cadera\"]")).includes(e.group))) "legs" else (if (truth((e.group === "Abdomen"))) "core" else (if (truth(dynamicValue(jsonValue("[\"Pecho\",\"Hombros\"]")).includes(e.group))) "push" else "pull")))))
            }
        }
        tier = (fun(e: dynamic): dynamic {
            return nullableOr(nullableOr(e.progressionTier23) { e.progressionTier19 }) { T.tier(e.id) }
        })
        val groups: dynamic = jsonValue("{\"full\":[\"Piernas\",\"Cadera\",\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\",\"Abdomen\"],\"push\":[\"Pecho\",\"Hombros\"],\"pull\":[\"Espalda\",\"Brazos\"],\"legs\":[\"Piernas\",\"Cadera\"],\"core\":[\"Abdomen\"],\"cardio\":[\"Cardio\"],\"mobility\":[\"Movilidad\"],\"control\":[\"Piernas\",\"Cadera\",\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\",\"Abdomen\"]}")
        global_Adapt.reasons = (fun(e: dynamic, p: dynamic, arg17: dynamic): dynamic {
            var strict: dynamic = absent
            strict = definedOr(arg17) { true }
            val `out`: dynamic = oldReasons(e, p, strict)
            if (truth(jsAnd(jsAnd(jsAnd(jsAnd(e) { p }) { jsMore(tier(e), T.level(p)) }) { (!truth(O.gentle(p))) }) { (!truth(`out`.includes("Variante fuera del escalón seleccionado"))) })) {
                `out`.push("Variante fuera del escalón seleccionado")
            }
            return jsArray { array18 ->
                appendAll(array18, jsConstruct(global_Set, arrayOf<dynamic>(`out`)))
            }
        })
        global_Adapt.allowed = (fun(e: dynamic, p: dynamic): dynamic {
            return jsAnd((!truth((!truth(e))))) { (!truth(global_Adapt.reasons(e, p).length)) }
        })
        inFamily = (fun(e: dynamic, f: dynamic): dynamic {
            return (if (truth((f === "power"))) jsOr((e.family === "power")) { (e.impact === "Con saltos") } else (if (truth((f === "balance"))) jsOr((e.group === "Movilidad")) { dynamicValue(jsRegExp("equilibrio|a una pierna", "")).test(e.name.toLowerCase()) } else (if (truth((f === "agility"))) jsAnd((e.group === "Cardio")) { dynamicValue(jsRegExp("paso|agilidad|patinador|coordin|lateral|cambio", "")).test(e.name.toLowerCase()) } else (if (truth((f === "seated"))) e.seated else (if (truth((f === "intervals"))) (e.group === "Cardio") else dynamicValue(jsOr(groups[f]) { groups.full }).includes(e.group))))))
        })
        val oldRoutines: dynamic = global_Catalog.routines.map((fun(r: dynamic): dynamic {
            return jsObject { obj42 ->
                copyFields(obj42, r)
            }
        }))
        if (true) {
            val iterator43: dynamic = iteratorOf(oldRoutines)
            while (true) {
                val next44: dynamic = iterator43.next()
                if (truth(next44.done)) break
                var r: dynamic = absent
                r = next44.value
                val p: dynamic = jsObject { obj45 ->
                    obj45["age"] = 30
                    obj45["equipment"] = (if (truth(r.gymOnly23)) "gym" else (if (truth(r.requirements.includes("db"))) "db" else "body"))
                    obj45["gymEquipment23"] = global_Object.keys(global_Training23.gymEquipment)
                    obj45["bench"] = true
                    obj45["step"] = true
                    obj45["difficulty"] = O.keys[r.level]
                    obj45["experience"] = "regular"
                    obj45["injuries"] = js("[]")
                    obj45["restrictions"] = js("({})")
                    obj45["health"] = jsonValue("{\"pain\":\"none\"}")
                    obj45["trainingScale"] = 1
                }
                val f: dynamic = (if (truth(r.gymOnly23)) (if (truth(jsOr(r.family.includes("lower")) { r.family.includes("glutes") })) "legs" else (if (truth(r.family.includes("upper"))) "full" else (if (truth(r.family.includes("push"))) "push" else (if (truth(r.family.includes("pull"))) "pull" else (if (truth(r.family.includes("core"))) "core" else (if (truth((r.category === "Cardio"))) "cardio" else "full")))))) else r.family)
                val picked: dynamic = select(pool(p, f), r.items.length, jsAdd(oldRoutines.indexOf(r), 17), p)
                if (truth(jsAtLeast(picked.length, global_Math.min(3, r.items.length)))) {
                    r.items = picked.map((fun(e: dynamic): dynamic {
                        return dose(e, p, (if (truth((r.category === "Cardio"))) "cardio" else (if (truth((r.category === "Movilidad"))) "recovery" else "strength")))
                    }))
                    r.requirements = jsArray { array50 ->
                        appendAll(array50, jsConstruct(global_Set, arrayOf<dynamic>(picked.flatMap((fun(e: dynamic): dynamic {
                            return jsOr(e.requirements) { js("[]") }
                        })))))
                    }
                    r.gymRequirements23 = jsArray { array53 ->
                        appendAll(array53, jsConstruct(global_Set, arrayOf<dynamic>(picked.flatMap((fun(e: dynamic): dynamic {
                            return jsOr(e.gymRequirements23) { js("[]") }
                        })))))
                    }
                    r.minutes = O.estimate(r.items)
                }
                r.planVersion = 30
            }
        }
        val coverage: dynamic = js("[]")
        var serial: dynamic = 0
        if (true) {
            val iterator58: dynamic = iteratorOf(jsonValue("[\"home\",\"gym\"]"))
            while (true) {
                val next59: dynamic = iterator58.next()
                if (truth(next59.done)) break
                var place: dynamic = absent
                place = next59.value
                if (true) {
                    var n: dynamic = absent
                    n = 0
                    var firstIteration60 = true
                    while (true) {
                        if (!firstIteration60) {
                            run {
                                val previous61: dynamic = numeric(n)
                                n = jsAdd(previous61, 1)
                            }
                        }
                        firstIteration60 = false
                        if (!truth(jsLess(n, 3))) break
                        if (true) {
                            val iterator62: dynamic = iteratorOf(jsonValue("[\"push\",\"pull\",\"legs\",\"core\",\"cardio\",\"mobility\"]"))
                            while (true) {
                                val next63: dynamic = iterator62.next()
                                if (truth(next63.done)) break
                                var family: dynamic = absent
                                family = next63.value
                                val unordered: dynamic = E.exercises.filter((fun(e: dynamic): dynamic {
                                    return jsAnd(jsAnd(((!truth((!truth(e.gymOnly23)))) === (place === "gym"))) { (tier(e) === n) }) { (e.planFamily30 === family) }
                                })).sort((fun(a: dynamic, b: dynamic): dynamic {
                                    return a.id.localeCompare(b.id)
                                }))
                                val chunks: dynamic = js("[]")
                                while (truth(unordered.length)) {
                                    val used: dynamic = jsConstruct(global_Set, js("[]"))
                                    val chunk: dynamic = js("[]")
                                    if (true) {
                                        var k: dynamic = absent
                                        k = 0
                                        while (true) {
                                            if (!truth(jsAnd(jsLess(k, unordered.length)) { jsLess(chunk.length, 5) })) break
                                            if (truth(used.has(unordered[k].baseId))) {
                                                run {
                                                    val previous65: dynamic = numeric(k)
                                                    k = jsAdd(previous65, 1)
                                                }
                                                continue
                                            }
                                            val e: dynamic = unordered.splice(k, 1)[0]
                                            chunk.push(e)
                                            used.add(e.baseId)
                                        }
                                    }
                                    chunks.push(chunk)
                                }
                                if (true) {
                                    val iterator66: dynamic = iteratorOf(chunks.entries())
                                    while (true) {
                                        val next67: dynamic = iterator66.next()
                                        if (truth(next67.done)) break
                                        var i: dynamic = absent
                                        var chunk: dynamic = absent
                                        val unpacked68: dynamic = fromIterable(next67.value)
                                        i = unpacked68[0]
                                        chunk = unpacked68[1]
                                        val p: dynamic = jsObject { obj69 ->
                                            obj69["age"] = 30
                                            obj69["equipment"] = (if (truth((place === "gym"))) "gym" else (if (truth(chunk.some((fun(e: dynamic): dynamic {
                                                return e.requirements.includes("db")
                                            })))) "db" else "body"))
                                            obj69["gymEquipment23"] = global_Object.keys(global_Training23.gymEquipment)
                                            obj69["bench"] = true
                                            obj69["step"] = true
                                            obj69["difficulty"] = O.keys[n]
                                            obj69["experience"] = "regular"
                                            obj69["injuries"] = js("[]")
                                            obj69["restrictions"] = js("({})")
                                            obj69["health"] = jsonValue("{\"pain\":\"none\"}")
                                            obj69["trainingScale"] = 1
                                        }
                                        if (truth(jsLess(chunk.length, 3))) {
                                            if (true) {
                                                val iterator70: dynamic = iteratorOf(pool(p, family))
                                                while (true) {
                                                    val next71: dynamic = iterator70.next()
                                                    if (truth(next71.done)) break
                                                    var e: dynamic = absent
                                                    e = next71.value
                                                    if (truth(chunk.some((fun(x: dynamic): dynamic {
                                                        return (x.baseId === e.baseId)
                                                    })))) {
                                                        continue
                                                    }
                                                    chunk.push(e)
                                                    if (truth(jsAtLeast(chunk.length, 3))) {
                                                        break
                                                    }
                                                }
                                            }
                                        }
                                        val items: dynamic = chunk.map((fun(e: dynamic): dynamic {
                                            return dose(e, p, (if (truth((family === "cardio"))) "cardio" else (if (truth((family === "mobility"))) "recovery" else "strength")))
                                        }))
                                        coverage.push(jsObject { obj72 ->
                                            obj72["id"] = jsAdd("collection30-", run {
                                                val previous73: dynamic = numeric(serial)
                                                val assigned74: dynamic = jsAdd(previous73, 1)
                                                serial = assigned74
                                                assigned74
                                            })
                                            obj72["name"] = jsAdd(jsAdd(jsAdd(jsAdd(jsonValue("{\"push\":\"Empuje y hombros\",\"pull\":\"Espalda y brazos\",\"legs\":\"Piernas y cadera\",\"core\":\"Estabilidad abdominal\",\"cardio\":\"Cardio y coordinación\",\"mobility\":\"Movilidad y control\"}")[family], " · "), O.names[n]), " · "), jsAdd(i, 1))
                                            obj72["category"] = (if (truth((family === "cardio"))) "Cardio" else (if (truth((family === "mobility"))) "Movilidad" else "Fuerza"))
                                            obj72["level"] = n
                                            obj72["items"] = items
                                            obj72["requirements"] = (if (truth((place === "gym"))) js("[]") else jsArray { array75 ->
                                                appendAll(array75, jsConstruct(global_Set, arrayOf<dynamic>(chunk.flatMap((fun(e: dynamic): dynamic {
                                                    return e.requirements
                                                })))))
                                            })
                                            obj72["gymOnly23"] = (place === "gym")
                                            obj72["gymRequirements23"] = jsArray { array76 ->
                                                appendAll(array76, jsConstruct(global_Set, arrayOf<dynamic>(chunk.flatMap((fun(e: dynamic): dynamic {
                                                    return jsOr(e.gymRequirements23) { js("[]") }
                                                })))))
                                            }
                                            obj72["family"] = (if (truth((place === "gym"))) jsAdd("gym-", family) else family)
                                            obj72["goalGroups"] = (if (truth((family === "mobility"))) jsonValue("[\"movement\",\"habit\"]") else (if (truth((family === "cardio"))) jsonValue("[\"fitness\",\"athletic\",\"habit\"]") else jsonValue("[\"build\",\"fitness\",\"athletic\",\"habit\"]")))
                                            obj72["extension"] = "standard"
                                            obj72["impact"] = (if (truth(chunk.some((fun(e: dynamic): dynamic {
                                                return (e.impact === "Con saltos")
                                            })))) "Con saltos" else "Sin saltos")
                                            obj72["minutes"] = O.estimate(items)
                                            obj72["planVersion"] = 30
                                            obj72["description"] = "Sesión original secuencial con cardio inicial y final, series y descansos. Cada bloque usa un patrón distinto dentro de esta sesión."
                                            obj72["collection30"] = true
                                        })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        global_Catalog.routines = jsArray { array79 ->
            appendAll(array79, oldRoutines)
            appendAll(array79, coverage)
        }
        run { val target81: dynamic = O.routines; invokeWith(target81.splice, target81, jsArray { array80 ->
            array80.push(0)
            array80.push(O.routines.length)
            appendAll(array80, global_Catalog.routines.filter((fun(r: dynamic): dynamic {
                return (!truth(r.gymOnly23))
            })))
        }) }
        run { val target83: dynamic = global_Training23.gymRoutines; invokeWith(target83.splice, target83, jsArray { array82 ->
            array82.push(0)
            array82.push(global_Training23.gymRoutines.length)
            appendAll(array82, global_Catalog.routines.filter((fun(r: dynamic): dynamic {
                return r.gymOnly23
            })))
        }) }
        global_Catalog.baseRoutines = global_Catalog.routines.length
        O.adjust = adjust
        global_Training23.adjust = adjust
        global_Training23.pool = pool
        global_Training23.choose = choose
        global_Schedule.items = choose
        if (true) {
            val iterator99: dynamic = iteratorOf(global_Catalogue30.bases)
            while (true) {
                val next100: dynamic = iterator99.next()
                if (truth(next100.done)) break
                var id: dynamic = absent
                id = next100.value
                T.chains.push(arrayOf<dynamic>(id, jsAdd(id, "-ecc30"), jsAdd(id, "-hold30")))
            }
        }
        T.chains.push(jsonValue("[\"gym-leg-press23\",\"gym-presssingle30\"]"))
        T.chains.push(jsonValue("[\"gym-chest-machine23\",\"gym-pressone30\"]"))
        T.chains.push(jsonValue("[\"bridge\",\"home-bridgemarch30\",\"home-thrustsingle30\"]"))
        T.chains.push(jsonValue("[\"plank\",\"home-plankstep30\",\"home-plankdrag30\"]"))
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg158: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg158) { js("({})") }
            val c: dynamic = oldMake(p, start, index, last, logs)
            assign(c, p, logs, start, last)
            return c
        })
        global_Training23.assign = assign
        val previousCurrent: dynamic = global_currentCycle
        global_currentCycle = (fun(): dynamic {
            val c: dynamic = previousCurrent()
            if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
                return c
            }
            val key: dynamic = global_JSON.stringify(arrayOf<dynamic>(30, global_state.profile, global_GoalContext24.personal()))
            if (truth((c.planningKey30 !== key))) {
                assign(c, global_state.profile, global_state.logs, E.dateKey(), global_state.cycles.filter((fun(x: dynamic): dynamic {
                    return jsLess(x.start, c.start)
                })).at((-numeric(1))))
                c.planningKey30 = key
                global_save()
            }
            return c
        })
        root.Training30 = jsObject { obj167 ->
            obj167["pool"] = pool
            obj167["select"] = select
            obj167["choose"] = choose
            obj167["assign"] = assign
            obj167["adjust"] = adjust
            obj167["tier"] = tier
            obj167["coverage"] = coverage
            obj167["version"] = 30
        }
        return absent
    }))(global_window)
}
