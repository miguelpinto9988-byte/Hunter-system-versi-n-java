@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installprogressionv19

import hunter.browser.*

@JsName("Engine") private external var global_Engine: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("InjuryAwareV93") private external var global_InjuryAwareV93: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("AdaptiveV9") private external var global_AdaptiveV9: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("RoutinePlanner") private external var global_RoutinePlanner: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installProgressionV19() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var O: dynamic = absent
        var level: dynamic = absent
        var tierPools: dynamic = absent
        var gentle: dynamic = absent
        var tier: dynamic = absent
        var rest: dynamic = absent
        var dose: dynamic = absent
        var cap: dynamic = absent
        var pool: dynamic = absent
        var choose: dynamic = absent
        var chains: dynamic = absent
        var successor: dynamic = absent
        var ruleRecommendation19: dynamic = absent
        var recommend: dynamic = absent
        var applyRecommendation: dynamic = absent
        var carry: dynamic = absent
        var adjust: dynamic = absent
        dose = fun(e: dynamic, p: dynamic, kind: dynamic): dynamic {
            var sets: dynamic = absent
            var target: dynamic = absent
            val n: dynamic = level(p)
            val scale: dynamic = global_TrainingIntensity.scale(p)
            val novice: dynamic = (p.experience === "beginner")
            if (truth(jsOr(gentle(p)) { (kind === "recovery") })) {
                return jsObject { obj26 ->
                    obj26["id"] = e.id
                    obj26["sets"] = 1
                    obj26["target"] = (if (truth((e.unit === "sec"))) 15 else 6)
                    obj26["rest"] = 30
                }
            }
            if (truth((e.family === "power"))) {
                sets = (if (truth(novice)) 2 else dynamicValue(jsonValue("[2,3,4]"))[n])
                target = dynamicValue(jsonValue("[4,5,6]"))[n]
            } else {
                if (truth((e.unit === "sec"))) {
                    sets = (if (truth(novice)) 2 else dynamicValue(jsonValue("[3,4,4]"))[n])
                    target = (if (truth((kind === "cardio"))) dynamicValue(jsonValue("[35,45,50]"))[n] else dynamicValue(jsonValue("[25,35,40]"))[n])
                } else {
                    sets = (if (truth(novice)) 2 else dynamicValue(jsonValue("[4,4,5]"))[n])
                    target = (if (truth(e.repRange19)) e.repRange19[0] else dynamicValue(jsonValue("[14,12,10]"))[n])
                    if (truth(novice)) {
                        target = global_Math.min(target, dynamicValue(jsonValue("[10,8,6]"))[n])
                    }
                }
            }
            return jsObject { obj27 ->
                obj27["id"] = e.id
                obj27["sets"] = sets
                obj27["target"] = global_Math.max((if (truth((e.unit === "sec"))) 10 else 2), global_Math.floor(jsMultiply(target, scale)))
                obj27["rest"] = rest(p)
            }
        }
        cap = fun(e: dynamic, p: dynamic): dynamic {
            return jsOr(onPresent(e.repRange19) { value30 -> value30[1] }) { (if (truth((e.family === "power"))) 8 else (if (truth((e.unit === "sec"))) 75 else dynamicValue(jsonValue("[20,18,16]"))[level(p)])) }
        }
        pool = fun(family: dynamic, p: dynamic, equipment: dynamic): dynamic {
            val spec: dynamic = tierPools[family]
            val ids: dynamic = jsOr(onPresent(spec) { value35 -> onPresent(value35[jsOr(equipment) { (if (truth(jsAnd((p.equipment === "db")) { spec.db })) "db" else "body") }]) { value36 -> value36[level(p)] } }) { js("[]") }
            return dynamicValue(jsArray { array40 ->
                appendAll(array40, jsConstruct(global_Set, arrayOf<dynamic>(ids)))
            }).map((fun(id: dynamic): dynamic {
                return E.byId(id)
            })).filter((fun(e: dynamic): dynamic {
                return jsAnd(e) { global_Adapt.allowed(e, p) }
            }))
        }
        choose = fun(p: dynamic, kind: dynamic, arg43: dynamic): dynamic {
            var rotation: dynamic = absent
            rotation = definedOr(arg43) { 0 }
            if (truth((!truth(global_Adapt.mode(p).auto)))) {
                return js("[]")
            }
            if (truth(gentle(p))) {
                return global_InjuryAwareV93.items(p, kind, rotation).filter((fun(x: dynamic): dynamic {
                    return global_Adapt.allowed(E.byId(x.id), p)
                })).map((fun(x: dynamic): dynamic {
                    return jsObject { obj44 ->
                        copyFields(obj44, x)
                        copyFields(obj44, dose(E.byId(x.id), p, kind))
                    }
                }))
            }
            val group: dynamic = global_GoalGroups14.of(p)
            var ids: dynamic = js("[]")
            val focus: dynamic = p.focus
            val requested: dynamic = global_Math.min(7, jsOr(p.dailyMissions) { (if (truth((p.trainingMode === "routines"))) 5 else 4) })
            if (truth(jsOr((kind === "strength")) { (kind === "mixed") })) {
                val families: dynamic = (if (truth((focus === "upper"))) jsonValue("[\"push\",\"pull\"]") else (if (truth((focus === "lower"))) jsonValue("[\"legs\"]") else (if (truth((focus === "core"))) jsonValue("[\"core\"]") else jsonValue("[\"full\"]"))))
                if (true) {
                    val iterator45: dynamic = iteratorOf(families)
                    while (true) {
                        val next46: dynamic = iterator45.next()
                        if (truth(next46.done)) break
                        var family: dynamic = absent
                        family = next46.value
                        run { val target48: dynamic = ids; invokeWith(target48.push, target48, jsArray { array47 ->
                            appendAll(array47, pool(family, p))
                        }) }
                    }
                }
                val primary: dynamic = ids.slice(0, requested)
                if (truth(jsLess(primary.length, requested))) {
                    val extra: dynamic = dynamicValue(jsonValue("[\"legs\",\"push\",\"pull\",\"core\"]")).flatMap((fun(f: dynamic): dynamic {
                        return pool(f, p)
                    }))
                    ids = jsArray { array49 ->
                        appendAll(array49, primary)
                        appendAll(array49, extra)
                    }
                }
            } else {
                if (truth((kind === "cardio"))) {
                    val family: dynamic = (if (truth((group === "athletic"))) (if (truth(jsMod(global_Math.abs(rotation), 2))) "agility" else "power") else (if (truth(onPresent(p.restrictions) { value50 -> value50.noImpact })) "cardio" else "intervals"))
                    ids = pool(family, p)
                    if (truth(jsLess(ids.length, requested))) {
                        run { val target52: dynamic = ids; invokeWith(target52.push, target52, jsArray { array51 ->
                            appendAll(array51, pool("cardio", p))
                        }) }
                    }
                } else {
                    ids = pool("mobility", p)
                }
            }
            var chosen: dynamic = jsArray { array53 ->
                appendAll(array53, jsConstruct(global_Map, arrayOf<dynamic>(ids.map((fun(e: dynamic): dynamic {
                    return arrayOf<dynamic>(e.id, e)
                })))).values())
            }
            val n: dynamic = (if (truth((kind === "recovery"))) global_Math.min(3, requested) else requested)
            if (truth((!truth(chosen.length)))) {
                val category: dynamic = (if (truth((kind === "cardio"))) "Cardio" else (if (truth((kind === "recovery"))) "Movilidad" else "Fuerza"))
                chosen = E.exercises.filter((fun(e: dynamic): dynamic {
                    return jsAnd(jsAnd(jsAnd(jsAnd((e.variant === "base")) { (!truth(e.sourceOnly)) }) { (e.category === category) }) { jsAtMost(tier(e.id), level(p)) }) { global_Adapt.allowed(e, p) }
                })).sort((fun(a: dynamic, b: dynamic): dynamic {
                    return jsSubtract(global_Math.abs(jsSubtract(level(p), tier(a.id))), global_Math.abs(jsSubtract(level(p), tier(b.id))))
                }))
            }
            return chosen.slice(0, n).map((fun(e: dynamic): dynamic {
                return dose(e, p, kind)
            }))
        }
        successor = fun(id: dynamic, p: dynamic): dynamic {
            if (true) {
                val iterator58: dynamic = iteratorOf(chains)
                while (true) {
                    val next59: dynamic = iterator58.next()
                    if (truth(next59.done)) break
                    var chain: dynamic = absent
                    chain = next59.value
                    val i: dynamic = chain.indexOf(id)
                    if (truth(jsLess(i, 0))) {
                        continue
                    }
                    if (true) {
                        val iterator60: dynamic = iteratorOf(chain.slice(jsAdd(i, 1)))
                        while (true) {
                            val next61: dynamic = iterator60.next()
                            if (truth(next61.done)) break
                            var next: dynamic = absent
                            next = next61.value
                            val e: dynamic = E.byId(next)
                            if (truth(jsAnd(jsAtMost(tier(next), level(p))) { global_Adapt.allowed(e, p) })) {
                                return e
                            }
                        }
                    }
                }
            }
            return null
        }
        ruleRecommendation19 = fun(logs: dynamic, item: dynamic, p: dynamic, now: dynamic): dynamic {
            val e: dynamic = E.byId(item.id)
            val rows: dynamic = global_AdaptiveV9.evidence(logs, item.id, now).filter((fun(r: dynamic): dynamic {
                return jsAnd((E.unitOf(r.item) === E.unitOf(item))) { (jsOr(r.item.side) { "both" } === jsOr(item.side) { "both" }) }
            }))
            val recent: dynamic = rows.slice(0, 3)
            val full: dynamic = jsAnd((recent.length === 3)) { recent.every((fun(r: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAtLeast(r.rpe, 1)) { jsAtMost(r.rpe, 7) }) { jsAtLeast(r.item.sets, item.sets) }) { jsAtLeast(r.item.target, item.target) }) { jsAtLeast(onPresent(r.item["actual"]) { value64 -> value64.length }, r.item.sets) }) { r.item["actual"].every((fun(a: dynamic): dynamic {
                    return jsAnd(a.done) { jsAtLeast(global_Number(a["value"]), r.item.target) }
                })) }
            })) }
            val `out`: dynamic = jsObject { obj65 ->
                obj65["id"] = item.id
                obj65["target"] = item.target
                obj65["action"] = "mantener"
                obj65["reason"] = "Reúne tres registros completos y comparables, con esfuerzo de 7/10 o menos."
                obj65["samples"] = rows.length
                obj65["variantChanged"] = false
            }
            if (truth(jsOr(jsOr((!truth(global_Adapt.mode(p).auto))) { gentle(p) }) { jsLess(global_TrainingIntensity.scale(p), 1) })) {
                return jsObject { obj66 ->
                    copyFields(obj66, `out`)
                    obj66["reason"] = "Se conserva la adaptación y la reducción elegida."
                }
            }
            if (truth((!truth(full)))) {
                return `out`
            }
            if (truth(jsLess(item.target, cap(e, p)))) {
                val delta: dynamic = (if (truth((e.unit === "sec"))) global_Math.min(5, global_Math.max(1, global_Math.floor(jsMultiply(item.target, 0.1)))) else 1)
                return jsObject { obj67 ->
                    copyFields(obj67, `out`)
                    obj67["target"] = global_Math.min(cap(e, p), jsAdd(item.target, delta))
                    obj67["action"] = "aumentar"
                    obj67["reason"] = "Tres registros comparables: aumenta una sola variable dentro del rango de este movimiento."
                }
            }
            val next: dynamic = successor(item.id, p)
            if (truth(next)) {
                val v: dynamic = dose(next, p, (if (truth((next.category === "Cardio"))) "cardio" else "strength"))
                return jsObject { obj68 ->
                    copyFields(obj68, `out`)
                    obj68["id"] = next.id
                    obj68["target"] = v.target
                    obj68["sets"] = v.sets
                    obj68["rest"] = v.rest
                    obj68["variantChanged"] = true
                    obj68["action"] = "aumentar"
                    obj68["reason"] = jsAdd(jsAdd(jsAdd(jsAdd("Rango completado: siguiente variante compatible, con repeticiones recalibradas. ", e.name), " → "), next.name), ".")
                }
            }
            return jsObject { obj69 ->
                copyFields(obj69, `out`)
                obj69["reason"] = "Rango completado. Conserva la técnica y la dosis de este nivel; cambia tu nivel o carga desde Perfil cuando corresponda."
            }
        }
        recommend = fun(logs: dynamic, item: dynamic, p: dynamic, now: dynamic): dynamic {
            val result: dynamic = ruleRecommendation19(logs, item, p, now)
            val candidate: dynamic = jsObject { obj74 ->
                copyFields(obj74, item)
                obj74["id"] = jsOr(result.id) { item.id }
                obj74["target"] = result.target
            }
            val estimate: dynamic = onPresent(root.ProgressMonitor20) { value75 -> value75.estimate(logs, candidate, now) }
            if (truth(jsAnd(jsAnd((result.action === "aumentar")) { estimate }) { jsMore(estimate.effort, 7) })) {
                return jsObject { obj76 ->
                    obj76["id"] = item.id
                    obj76["target"] = item.target
                    obj76["action"] = "mantener"
                    obj76["variantChanged"] = false
                    obj76["samples"] = result.samples
                    obj76["estimate20"] = estimate
                    obj76["reason"] = jsAdd(jsAdd(jsAdd(jsAdd("Se mantiene la dosis: esfuerzo estimado ", estimate.effort), "/10 a partir de "), estimate.neighbors), " registros comparables.")
                }
            }
            return jsObject { obj77 ->
                copyFields(obj77, result)
                obj77["estimate20"] = estimate
            }
        }
        applyRecommendation = fun(item: dynamic, r: dynamic, p: dynamic): dynamic {
            if (truth(jsAnd(r.variantChanged) { (r.id !== item.id) })) {
                val e: dynamic = E.byId(r.id)
                if (truth(jsAnd(jsAnd(e) { global_Adapt.allowed(e, p) }) { jsAtMost(tier(e.id), level(p)) })) {
                    val next: dynamic = jsObject { obj80 ->
                        copyFields(obj80, item)
                        copyFields(obj80, dose(e, p, (if (truth((e.category === "Cardio"))) "cardio" else "strength")))
                        obj80["id"] = r.id
                        obj80["target"] = r.target
                        obj80["progressedFrom19"] = item.id
                    }
                    removeKey(next, "unit")
                    if (truth((!truth(e.sides)))) {
                        removeKey(next, "side")
                    }
                    return next
                }
            }
            return jsObject { obj81 ->
                copyFields(obj81, item)
                obj81["target"] = global_Math.min(cap(E.byId(item.id), p), global_Math.max(item.target, r.target))
                obj81["rest"] = rest(p)
            }
        }
        carry = fun(item: dynamic, last: dynamic, p: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(last))) { gentle(p) }) { jsLess(global_TrainingIntensity.scale(p), 1) })) {
                return item
            }
            val old: dynamic = last.days.flatMap((fun(d: dynamic): dynamic {
                return d.items
            })).filter((fun(x: dynamic): dynamic {
                return jsOr((x.id === item.id)) { chains.some((fun(c: dynamic): dynamic {
                    return jsAnd(jsAnd(c.includes(item.id)) { c.includes(x.id) }) { jsMore(c.indexOf(x.id), c.indexOf(item.id)) }
                })) }
            })).at((-numeric(1)))
            if (truth(jsOr(jsOr((!truth(old))) { (!truth(global_Adapt.allowed(E.byId(old.id), p))) }) { jsMore(tier(old.id), level(p)) })) {
                return item
            }
            val v: dynamic = dose(E.byId(old.id), p, (if (truth((E.byId(old.id).category === "Cardio"))) "cardio" else "strength"))
            val next: dynamic = jsObject { obj84 ->
                copyFields(obj84, item)
                copyFields(obj84, v)
                obj84["target"] = global_Math.max(v.target, global_Math.min(cap(E.byId(old.id), p), old.target))
            }
            if (truth((old.id !== item.id))) {
                removeKey(next, "unit")
                if (truth((!truth(E.byId(old.id).sides)))) {
                    removeKey(next, "side")
                }
            }
            return next
        }
        adjust = fun(r: dynamic, p: dynamic): dynamic {
            return r.items.map((fun(x: dynamic): dynamic {
                val v: dynamic = dose(E.byId(x.id), p, (if (truth((r.category === "Cardio"))) "cardio" else (if (truth((r.category === "Movilidad"))) "recovery" else "strength")))
                return jsObject { obj105 ->
                    copyFields(obj105, x)
                    copyFields(obj105, v)
                    obj105["sets"] = (if (truth(gentle(p))) 1 else global_Math.max(1, global_Math.ceil(jsMultiply(v.sets, O.lengths[r.extension].ratio))))
                }
            }))
        }
        E = global_Engine
        O = global_OwnRoutines18
        val keys: dynamic = O.keys
        level = (fun(p: dynamic): dynamic {
            return global_Math.max(0, keys.indexOf(p.difficulty))
        })
        tierPools = jsonValue("{\"full\":{\"body\":[[\"squat\",\"incline\",\"bridge\",\"deadbug\",\"prone\",\"split\"],[\"squat-control18\",\"pushup\",\"singlebridge\",\"plank\",\"reverse\",\"reverselunge\"],[\"split-15-19\",\"archer19\",\"singlebridge-pause19\",\"plankreach19\",\"y-t-w19\",\"single-squat19\"]],\"db\":[[\"goblet\",\"press\",\"row\",\"dbbridge\",\"hammer\",\"suitcase\"],[\"goblet-control18\",\"press-control18\",\"bentrow\",\"rdl-control18\",\"altfloorpress\",\"dbsplit\"],[\"goblet-15-19\",\"singlepress\",\"row-15-19\",\"single-rdl19\",\"doublefront\",\"overhead-15-19\"]]},\"push\":{\"body\":[[\"incline\",\"kneepush\",\"wallslide\",\"incline-control18\",\"kneepush-control18\"],[\"pushup\",\"pushup-control19\",\"closepush\",\"shouldertap\",\"bearhold\"],[\"archer19\",\"pike19\",\"closepush\",\"decline19\",\"plankreach19\"]],\"db\":[[\"press\",\"overheadpress\",\"neutral\",\"lateral\",\"lyingtricep\",\"benchpress\"],[\"press-control18\",\"overheadpress-control18\",\"singlepress\",\"neutral-control18\",\"singleoverhead\",\"singletricep\"],[\"singlepress\",\"overhead-15-19\",\"squeezepress\",\"lateral-pause19\",\"singletricep\",\"benchpress-control18\"]]},\"pull\":{\"body\":[[\"prone\",\"towelrow\",\"scapular\",\"bird\",\"reverse\"],[\"w-t19\",\"reverse\",\"contralateral\",\"bird-control18\",\"towelrow\"],[\"y-t-w19\",\"contralateral\",\"w-t19\",\"bird-control18\",\"reverse\"]],\"db\":[[\"row\",\"curl\",\"hammer\",\"reversefly\",\"bentrow\",\"concentration\"],[\"row-control18\",\"bentrow-control18\",\"curl-control18\",\"hammer-control18\",\"reversefly\",\"pullover\"],[\"row-15-19\",\"bentrow-control18\",\"curl-pause19\",\"reversefly\",\"pullover\",\"hammer-control18\"]]},\"legs\":{\"body\":[[\"squat\",\"split\",\"bridge\",\"calf\",\"reverselunge\",\"wallsit\"],[\"squat-control18\",\"split-control18\",\"singlebridge\",\"singlecalf\",\"lungeknee\",\"lateralunge\"],[\"single-squat19\",\"split-15-19\",\"singlebridge-pause19\",\"singlecalf-pause19\",\"singlehinge-free19\",\"squat-15-19\"]],\"db\":[[\"goblet\",\"rdl\",\"dbbridge\",\"dbcalf\",\"dbsumo\",\"sumodead\"],[\"goblet-control18\",\"rdl-control18\",\"dbsplit\",\"dblunge\",\"dbsidelunge\",\"goblethold\"],[\"goblet-15-19\",\"single-rdl19\",\"dbsplit\",\"bulgarian\",\"dbsidelunge\",\"doublefront\"]]},\"core\":{\"body\":[[\"deadbug\",\"bird\",\"kneelingplank\",\"sideplank\",\"curlup\"],[\"plank\",\"bearhold\",\"reversecrunch\",\"shouldertap\",\"sideplank\"],[\"full-sideplank19\",\"plankreach19\",\"pau-plankup\",\"sideplank-lift19\",\"mountain\"]],\"db\":[[\"suitcase\",\"farmerhold\",\"dbbridge\",\"deadbug\",\"farmerwalk\"],[\"farmerwalk\",\"dbmarch\",\"singlepress\",\"suitcase\",\"dbsplit\"],[\"dbmarch\",\"single-rdl19\",\"singleoverhead\",\"singlepress\",\"farmerwalk\"]]},\"control\":{\"body\":[[\"squat-control18\",\"kneepush-control18\",\"bridge-control18\",\"deadbug-control18\",\"calf-control18\"],[\"pushup-control19\",\"split-control18\",\"bird-control18\",\"squat-control18\",\"singlebridge\"],[\"squat-15-19\",\"split-15-19\",\"singlebridge-pause19\",\"singlecalf-pause19\",\"y-t-w19\"]],\"db\":[[\"goblet\",\"press\",\"row\",\"rdl\",\"curl\"],[\"goblet-control18\",\"press-control18\",\"row-control18\",\"rdl-control18\",\"curl-control18\"],[\"goblet-15-19\",\"row-15-19\",\"overhead-15-19\",\"single-rdl19\",\"curl-pause19\",\"lateral-pause19\"]]},\"cardio\":{\"body\":[[\"fastmarch\",\"stepjack\",\"kneeliftcardio\",\"box\",\"sidestep\",\"heelback\"],[\"shadowcombo\",\"skater\",\"squatstep\",\"boxupper\",\"stepreach\",\"doubletouch\"],[\"shuffle-touch19\",\"shadowcombo\",\"squatstep\",\"skaterreach\",\"boxinterval\",\"fastfeet\"]]},\"intervals\":{\"body\":[[\"stepjackinterval\",\"boxinterval\",\"fastmarch\",\"sidestepinterval\",\"stepreach\"],[\"shadowcombo\",\"fastfeet\",\"skater\",\"jog\",\"squatstep\"],[\"highknees\",\"mountain\",\"jumpjack\",\"shuffle-touch19\",\"rope\",\"skater-stick19\"]]},\"agility\":{\"body\":[[\"frontbackstep\",\"doubletouch\",\"vstep\",\"stepreach\",\"skater\"],[\"shuffle\",\"stepcross\",\"skaterreach\",\"fastfeet\",\"shadowcombo\"],[\"shuffle-touch19\",\"stepcross\",\"skater-stick19\",\"highknees\",\"mountain\"]]},\"power\":{\"body\":[[\"lowhop\",\"linehop\",\"calf\",\"squat\",\"lowhop\"],[\"linehop\",\"squatjump\",\"lowhop\",\"skaterhop\",\"split\"],[\"skater-stick19\",\"squatjump\",\"skaterhop\",\"linehop\",\"singlecalf-pause19\"]]},\"mobility\":{\"body\":[[\"ankledrive\",\"hipcircle\",\"shoulder\",\"wallslide\",\"hipflex\",\"thoracic\"],[\"hipflex\",\"thoracic\",\"hamstretch\",\"ankledrive\",\"chestopen\",\"latstretch\"],[\"thoracic\",\"latstretch\",\"hipflex\",\"hamstretch\",\"chestopen\",\"ankledrive\"]]},\"balance\":{\"body\":[[\"tandem\",\"linewalk\",\"standingabduct\",\"wallcalf\",\"balance\"],[\"balance\",\"singlehinge\",\"lungeknee\",\"singlecalf\",\"skaterreach\"],[\"singlehinge-free19\",\"single-squat19\",\"singlecalf-pause19\",\"skater-stick19\",\"lungeknee\"]]},\"seated\":{\"body\":[[\"seatedmarch\",\"seatedcalf\",\"legextend\",\"ankle\",\"shoulder\",\"scapular\"],[\"seatedpunch\",\"seatedmarchinterval\",\"legextend-control18\",\"heeltoe\",\"seatedreach\"],[\"seatedmarchinterval\",\"legextend-control18\",\"seatedpunch\",\"seatedstep\",\"forearmmob\"]]}}")
        val previousGentle: dynamic = O.gentle
        gentle = (fun(p: dynamic): dynamic {
            return previousGentle(p)
        })
        val tierMap: dynamic = jsConstruct(global_Map, js("[]"))
        if (true) {
            val iterator3: dynamic = iteratorOf(global_Object.values(tierPools))
            while (true) {
                val next4: dynamic = iterator3.next()
                if (truth(next4.done)) break
                var spec: dynamic = absent
                spec = next4.value
                if (true) {
                    val iterator5: dynamic = iteratorOf(global_Object.values(spec))
                    while (true) {
                        val next6: dynamic = iterator5.next()
                        if (truth(next6.done)) break
                        var pools: dynamic = absent
                        pools = next6.value
                        if (true) {
                            val iterator7: dynamic = iteratorOf(pools.entries())
                            while (true) {
                                val next8: dynamic = iterator7.next()
                                if (truth(next8.done)) break
                                var n: dynamic = absent
                                var ids: dynamic = absent
                                val unpacked9: dynamic = fromIterable(next8.value)
                                n = unpacked9[0]
                                ids = unpacked9[1]
                                if (true) {
                                    val iterator10: dynamic = iteratorOf(ids)
                                    while (true) {
                                        val next11: dynamic = iterator10.next()
                                        if (truth(next11.done)) break
                                        var id: dynamic = absent
                                        id = next11.value
                                        tierMap["set"](id, global_Math.min(nullableOr(tierMap["get"](id)) { n }, n))
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (true) {
            val iterator12: dynamic = iteratorOf(E.exercises)
            while (true) {
                val next13: dynamic = iterator12.next()
                if (truth(next13.done)) break
                var e: dynamic = absent
                e = next13.value
                if (truth((e.progressionTier19 !== absent))) {
                    tierMap["set"](e.id, e.progressionTier19)
                }
            }
        }
        tier = (fun(id: dynamic): dynamic {
            return nullableOr(nullableOr(tierMap["get"](id)) { onPresent(E.byId(id)) { value14 -> value14.level } }) { 0 }
        })
        val oldReasons: dynamic = global_Adapt.reasons
        global_Adapt.reasons = (fun(e: dynamic, p: dynamic, arg19: dynamic): dynamic {
            var strict: dynamic = absent
            strict = definedOr(arg19) { true }
            if (truth((!truth(e)))) {
                return jsonValue("[\"Ejercicio desconocido\"]")
            }
            val `out`: dynamic = oldReasons(e, p, strict).filter((fun(x: dynamic): dynamic {
                return (x !== "Nivel superior al seleccionado")
            }))
            if (truth(p)) {
                val ceiling: dynamic = (if (truth(global_Adapt.mode(p).reduced)) 0 else (if (truth((level(p) === 0))) 1 else 2))
                if (truth(jsMore(e.level, ceiling))) {
                    `out`.push("Nivel superior al seleccionado")
                }
            }
            return `out`
        })
        global_Adapt.allowed = (fun(e: dynamic, p: dynamic): dynamic {
            return (!truth(global_Adapt.reasons(e, p).length))
        })
        global_Adapt.routineCompatible = (fun(r: dynamic, p: dynamic): dynamic {
            return jsAnd(jsAnd((!truth((!truth(p))))) { global_Adapt.mode(p).auto }) { r.items.every((fun(x: dynamic): dynamic {
                return global_Adapt.allowed(E.byId(x.id), p)
            })) }
        })
        rest = (fun(p: dynamic): dynamic {
            return (if (truth(dynamicValue(jsonValue("[30,60,90]")).includes(global_Number(p.restSeconds)))) global_Number(p.restSeconds) else dynamicValue(jsonValue("[30,60,90]"))[level(p)])
        })
        global_TrainingIntensity.dose = dose
        global_Object.assign(global_TrainingIntensity.levels, jsonValue("{\"basic\":{\"sets\":4,\"reps\":14,\"seconds\":25,\"cardioSets\":3,\"cardioSeconds\":35},\"intermediate\":{\"sets\":4,\"reps\":12,\"seconds\":35,\"cardioSets\":4,\"cardioSeconds\":45},\"advanced\":{\"sets\":5,\"reps\":10,\"seconds\":40,\"cardioSets\":4,\"cardioSeconds\":50}}"))
        global_Schedule.items = choose
        chains = jsonValue("[[\"incline\",\"incline-control18\",\"pushup\",\"pushup-control19\",\"archer19\"],[\"kneepush\",\"kneepush-control18\",\"pushup\",\"pushup-control19\",\"pike19\"],[\"squat\",\"squat-control18\",\"squat-15-19\",\"single-squat19\"],[\"split\",\"split-control18\",\"split-15-19\"],[\"goblet\",\"goblet-control18\",\"goblet-15-19\"],[\"rdl\",\"rdl-control18\",\"single-rdl19\"],[\"press\",\"press-control18\",\"singlepress\"],[\"row\",\"row-control18\",\"row-15-19\"],[\"overheadpress\",\"overheadpress-control18\",\"overhead-15-19\"],[\"curl\",\"curl-control18\",\"curl-pause19\"],[\"bridge\",\"bridge-control18\",\"singlebridge\",\"singlebridge-pause19\"],[\"calf\",\"calf-control18\",\"singlecalf\",\"singlecalf-pause19\"],[\"kneelingplank\",\"plank\",\"plankreach19\"],[\"sideplank\",\"full-sideplank19\",\"sideplank-lift19\"],[\"prone\",\"w-t19\",\"y-t-w19\"],[\"singlehinge\",\"singlehinge-free19\"],[\"shuffle\",\"shuffle-touch19\"],[\"skaterhop\",\"skater-stick19\"]]")
        global_AdaptiveV9.recommend = recommend
        val make: dynamic = E.makeCycle
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg89: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg89) { js("({})") }
            val c: dynamic = make(p, start, index, last, logs)
            val ap: dynamic = global_GoalPlans.adjusted(p)
            c.engineVersion19 = 19
            if (truth((!truth(global_Adapt.mode(ap).auto)))) {
                return c
            }
            if (true) {
                val iterator92: dynamic = iteratorOf(c.days)
                while (true) {
                    val next93: dynamic = iterator92.next()
                    if (truth(next93.done)) break
                    var d: dynamic = absent
                    d = next93.value
                    if (truth(jsOr((!truth(d.items.length))) { (d.kind === "rest") })) {
                        continue
                    }
                    val selected: dynamic = choose(ap, d.kind, global_Schedule.weekday(d.date))
                    if (truth(selected.length)) {
                        d.items = selected.map((fun(x: dynamic): dynamic {
                            return carry(x, last, ap)
                        }))
                    } else {
                        d.items = d.items.filter((fun(x: dynamic): dynamic {
                            return global_Adapt.allowed(E.byId(x.id), ap)
                        })).map((fun(x: dynamic): dynamic {
                            return jsObject { obj98 ->
                                copyFields(obj98, x)
                                copyFields(obj98, dose(E.byId(x.id), ap, d.kind))
                            }
                        }))
                    }
                    d.planningNote = (if (truth(gentle(ap))) "Selección compatible con tus restricciones, con dosis reducida." else jsAdd(jsAdd("Escalón ", O.names[level(p)]), ": variantes, control y volumen propios de este nivel."))
                    d.progressionLevel19 = level(p)
                }
            }
            return c
        })
        val routines: dynamic = js("[]")
        if (true) {
            val iterator106: dynamic = iteratorOf(global_Object.entries(O.families))
            while (true) {
                val next107: dynamic = iterator106.next()
                if (truth(next107.done)) break
                var family: dynamic = absent
                var spec: dynamic = absent
                val unpacked108: dynamic = fromIterable(next107.value)
                family = unpacked108[0]
                spec = unpacked108[1]
                if (true) {
                    var n: dynamic = absent
                    n = 0
                    var firstIteration109 = true
                    while (true) {
                        if (!firstIteration109) {
                            run {
                                val previous110: dynamic = numeric(n)
                                n = jsAdd(previous110, 1)
                            }
                        }
                        firstIteration109 = false
                        if (!truth(jsLess(n, 3))) break
                        if (true) {
                            val iterator111: dynamic = iteratorOf(global_Object.entries(O.lengths))
                            while (true) {
                                val next112: dynamic = iterator111.next()
                                if (truth(next112.done)) break
                                var extension: dynamic = absent
                                var length: dynamic = absent
                                val unpacked113: dynamic = fromIterable(next112.value)
                                extension = unpacked113[0]
                                length = unpacked113[1]
                                if (true) {
                                    val iterator114: dynamic = iteratorOf((if (truth(spec.db)) jsonValue("[\"body\",\"db\"]") else jsonValue("[\"body\"]")))
                                    while (true) {
                                        val next115: dynamic = iterator114.next()
                                        if (truth(next115.done)) break
                                        var equipment: dynamic = absent
                                        equipment = next115.value
                                        val ids: dynamic = dynamicValue(jsArray { array117 ->
                                            appendAll(array117, jsConstruct(global_Set, arrayOf<dynamic>(tierPools[family][equipment][n])))
                                        }).slice(0, length.count)
                                        val selected: dynamic = ids.map((fun(id: dynamic): dynamic {
                                            return E.byId(id)
                                        }))
                                        if (truth(selected.some((fun(e: dynamic): dynamic {
                                            return (!truth(e))
                                        })))) {
                                            throw global_Error(jsAdd("Plantilla incompleta: ", family))
                                        }
                                        val p: dynamic = jsObject { obj118 ->
                                            obj118["difficulty"] = keys[n]
                                            obj118["experience"] = "regular"
                                            obj118["health"] = jsonValue("{\"pain\":\"none\"}")
                                            obj118["injuries"] = js("[]")
                                            obj118["restrictions"] = js("({})")
                                            obj118["trainingScale"] = 1
                                        }
                                        val items: dynamic = selected.map((fun(e: dynamic): dynamic {
                                            val v: dynamic = dose(e, p, (if (truth((spec.category === "Cardio"))) "cardio" else (if (truth((spec.category === "Movilidad"))) "recovery" else "strength")))
                                            v.sets = global_Math.max(1, global_Math.ceil(jsMultiply(v.sets, length.ratio)))
                                            return v
                                        }))
                                        val requirements: dynamic = jsArray { array121 ->
                                            appendAll(array121, jsConstruct(global_Set, arrayOf<dynamic>(selected.flatMap((fun(e: dynamic): dynamic {
                                                return jsOr(e.requirements) { js("[]") }
                                            })).concat((if (truth((equipment === "db"))) jsonValue("[\"db\"]") else js("[]"))))))
                                        }
                                        routines.push(jsObject { obj122 ->
                                            obj122["id"] = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("own18-", family), "-"), n), "-"), extension), "-"), equipment)
                                            obj122["name"] = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(spec.name, " · "), O.names[n]), " · "), length.name), (if (truth((equipment === "db"))) " · mancuernas" else ""))
                                            obj122["category"] = spec.category
                                            obj122["level"] = n
                                            obj122["items"] = items
                                            obj122["requirements"] = requirements
                                            obj122["impact"] = (if (truth(selected.some((fun(e: dynamic): dynamic {
                                                return (e.impact === "Con saltos")
                                            })))) "Con saltos" else "Sin saltos")
                                            obj122["family"] = family
                                            obj122["goalGroups"] = spec.goals
                                            obj122["extension"] = extension
                                            obj122["planVersion"] = 19
                                            obj122["own18"] = true
                                            obj122["own19"] = true
                                            obj122["description"] = jsAdd(jsAdd("Sesión original de Hunter System. ", (if (truth(jsAnd((family === "pull")) { (equipment === "body") })) "Trabajo escapular y de control sin carga externa. " else "")), "Los tres niveles cambian las variantes y la coordinación, además del volumen. Movilidad y adaptación conservan dosis propias.")
                                            obj122["minutes"] = O.estimate(items)
                                        })
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        run { val target124: dynamic = O.routines; invokeWith(target124.splice, target124, jsArray { array123 ->
            array123.push(0)
            array123.push(O.routines.length)
            appendAll(array123, routines)
        }) }
        global_Object.assign(O, jsObject { obj125 ->
            obj125["dose"] = dose
            obj125["adjust"] = adjust
            obj125["cap"] = cap
            obj125["gentle"] = gentle
        })
        global_Catalog.routines = O.routines
        global_Catalog.baseRoutines = routines.length
        global_Catalog.baseExercises = E.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).length
        global_RoutinePlanner.organize = (fun(c: dynamic, p: dynamic, source: dynamic, own: dynamic, logs: dynamic, now: dynamic): dynamic {
            if (truth(jsOr(jsOr((p.trainingMode !== "routines")) { (!truth(global_Adapt.mode(p).auto)) }) { (onPresent(c.reviews) { value136 -> onPresent(value136.at((-numeric(1)))) { value137 -> value137.action } } === "pausar") })) {
                return 0
            }
            val ap: dynamic = global_GoalPlans.adjusted(p)
            val n: dynamic = level(p)
            val group: dynamic = global_GoalGroups14.of(p)
            var count: dynamic = 0
            if (true) {
                val iterator138: dynamic = iteratorOf(c.days.entries())
                while (true) {
                    val next139: dynamic = iterator138.next()
                    if (truth(next139.done)) break
                    var i: dynamic = absent
                    var d: dynamic = absent
                    val unpacked140: dynamic = fromIterable(next139.value)
                    i = unpacked140[0]
                    d = unpacked140[1]
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, now)) { (!truth(d.items.length)) }) { d.customized }) { d.routineId }) { global_Weekly.active(logs[d.date]) }) { onPresent(d.tasks) { value141 -> value141.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) } })) {
                        continue
                    }
                    val category: dynamic = (if (truth((d.kind === "cardio"))) "Cardio" else (if (truth((d.kind === "recovery"))) "Movilidad" else "Fuerza"))
                    var list: dynamic = routines.filter((fun(r: dynamic): dynamic {
                        return jsAnd(jsAnd(jsAnd(jsAnd((r.level === n)) { (r.extension === "standard") }) { jsOr((r.category === category)) { jsAnd((category === "Fuerza")) { (r.category === "Mixta") } } }) { r.goalGroups.includes(group) }) { global_Adapt.routineCompatible(r, ap) }
                    }))
                    if (truth((!truth(list.length)))) {
                        list = routines.filter((fun(r: dynamic): dynamic {
                            return jsAnd(jsAnd(jsAnd((r.level === n)) { (r.extension === "brief") }) { jsOr((r.category === category)) { jsAnd((category === "Fuerza")) { (r.category === "Mixta") } } }) { global_Adapt.routineCompatible(r, ap) }
                        }))
                    }
                    if (truth(jsOr(jsOr((ap.focus === "upper")) { (ap.focus === "lower") }) { (ap.focus === "core") })) {
                        val families: dynamic = (if (truth((ap.focus === "upper"))) jsonValue("[\"push\",\"pull\"]") else (if (truth((ap.focus === "lower"))) jsonValue("[\"legs\"]") else jsonValue("[\"core\"]")))
                        val focused: dynamic = list.filter((fun(r: dynamic): dynamic {
                            return families.includes(r.family)
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
                    if (truth((!truth(list.length)))) {
                        d.own18 = true
                        d.own19 = true
                        d.title = jsAdd("Rutina personal · ", category)
                        d.planningNote = "Movimientos compatibles con tu perfil; la adaptación conserva prioridad sobre el nivel."
                        run {
                            val previous150: dynamic = numeric(count)
                            count = jsAdd(previous150, 1)
                        }
                        continue
                    }
                    val r: dynamic = list[jsMod(i, list.length)]
                    val previous: dynamic = d.items
                    val items: dynamic = adjust(r, ap)
                    if (true) {
                        var j: dynamic = absent
                        j = 0
                        var firstIteration151 = true
                        while (true) {
                            if (!firstIteration151) {
                                run {
                                    val previous152: dynamic = numeric(j)
                                    j = jsAdd(previous152, 1)
                                }
                            }
                            firstIteration151 = false
                            if (!truth(jsLess(j, items.length))) break
                            var x: dynamic = carry(items[j], jsObject { obj153 ->
                                obj153["days"] = arrayOf<dynamic>(jsObject { obj154 ->
                                    obj154["items"] = previous
                                })
                            }, ap)
                            if (truth(jsAnd(jsAnd((!truth(gentle(ap)))) { (global_TrainingIntensity.scale(ap) === 1) }) { jsAtLeast(onPresent(c.reviews) { value155 -> onPresent(value155.at((-numeric(1)))) { value156 -> value156.adherence } }, 0.8) })) {
                                x = applyRecommendation(x, recommend(logs, x, ap, now), ap)
                            }
                            if (truth((onPresent(c.reviews) { value157 -> onPresent(value157.at((-numeric(1)))) { value158 -> value158.action } } === "reducir"))) {
                                x.target = global_Math.max((if (truth((E.unitOf(x) === "sec"))) 10 else 2), global_Math.floor(jsMultiply(x.target, 0.8)))
                            }
                            items[j] = x
                        }
                    }
                    d.items = items
                    d.kind = (if (truth((r.category === "Mixta"))) "mixed" else d.kind)
                    d.title = r.name
                    d.own18 = true
                    d.own19 = true
                    d.autoTemplateId = r.id
                    d.tasks = js("[]")
                    d.planningNote = (if (truth(gentle(ap))) "Rutina adaptada con dosis reducida y filtros de lesión." else jsAdd(jsAdd("Variantes del nivel ", O.names[n]), ". Revisión semanal por repeticiones, esfuerzo y cambio de variante."))
                    run {
                        val previous179: dynamic = numeric(count)
                        count = jsAdd(previous179, 1)
                    }
                }
            }
            return count
        })
        root.TrainingProgression19 = jsObject { obj182 ->
            obj182["tierPools"] = tierPools
            obj182["tier"] = tier
            obj182["level"] = level
            obj182["pool"] = pool
            obj182["choose"] = choose
            obj182["dose"] = dose
            obj182["cap"] = cap
            obj182["rest"] = rest
            obj182["chains"] = chains
            obj182["successor"] = successor
            obj182["recommend"] = recommend
            obj182["applyRecommendation"] = applyRecommendation
            obj182["carry"] = carry
            obj182["routines"] = routines
            obj182["adjust"] = adjust
        }
        return absent
    }))(global_window)
}
