@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterquality3850

import hunter.browser.*

@JsName("Error") private external var global_Error: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterQuality3850() {
    dynamicValue((fun(root: dynamic): dynamic {
        var old: dynamic = absent
        var E: dynamic = absent
        var preparation: dynamic = absent
        var prepNames: dynamic = absent
        var secondaryOnly: dynamic = absent
        var excludeFromAuto: dynamic = absent
        var byId: dynamic = absent
        var movement: dynamic = absent
        var classify: dynamic = absent
        var meta: dynamic = absent
        var family: dynamic = absent
        var power: dynamic = absent
        var impact: dynamic = absent
        var matches: dynamic = absent
        var inKind: dynamic = absent
        var choosePattern: dynamic = absent
        var selectionPool: dynamic = absent
        var score: dynamic = absent
        var evidence: dynamic = absent
        var adjustDose: dynamic = absent
        movement = fun(e: dynamic, m: dynamic): dynamic {
            if (truth((!truth(m)))) {
                return "none"
            }
            if (truth((m.group === "cardio"))) {
                return "conditioning"
            }
            if (truth((m.group === "mobility"))) {
                return "mobility"
            }
            if (truth(m.power)) {
                return "power"
            }
            val s: dynamic = dynamicValue(jsOr(jsOr(m.family) { onPresent(e) { value7 -> value7.id } }) { "" }).toLowerCase()
            if (truth((m.group === "upper"))) {
                if (truth(jsOr(dynamicValue(jsRegExp("(?:row|remo|pull|chinup|lat|meadows|inverted|facepull|face-pull|backmachine|pendlay|renegade|towelrow)", "")).test(s)) { dynamicValue(jsonValue("[\"bentrow\",\"pullover\"]")).includes(s) })) {
                    return "pull"
                }
                if (truth(jsOr(dynamicValue(jsRegExp("(?:press|push|bench|dip|flexion|fondos|hspu|handstand|chest-machine|handrelease)", "")).test(s)) { dynamicValue(jsonValue("[\"wall\",\"incline\",\"neutral\",\"squeezepress\",\"kneepush\",\"closepush\",\"pike19\",\"archer19\",\"decline19\",\"singlepress\",\"altfloorpress\",\"singleoverhead\"]")).includes(s) })) {
                    return "push"
                }
                return "isolation"
            }
            if (truth((m.group === "lower"))) {
                if (truth(jsOr(dynamicValue(jsRegExp("(?:squat|lunge|zancada|split|stepup|stepdown|kneebend|wallsit|quad|sledpush)", "")).test(s)) { dynamicValue(jsonValue("[\"chair\",\"goblet\",\"sumo\",\"dbsumo\",\"doublefront\"]")).includes(s) })) {
                    return "knee"
                }
                if (truth(jsOr(dynamicValue(jsRegExp("(?:hinge|bridge|dead|rdl|hipthrust|hip-|hip_|ham|hinge|sledback|kickback)", "")).test(s)) { dynamicValue(jsonValue("[\"hipthrust\",\"sumodead\",\"suitdead\"]")).includes(s) })) {
                    return "hip"
                }
                return "accessory"
            }
            if (truth((m.group === "core"))) {
                if (truth(dynamicValue(jsRegExp("(?:plank|deadbug|bird|pallof|carry|hold|suitcase|farmer|brace|bear)", "")).test(s))) {
                    return "brace"
                }
                return "trunk"
            }
            return "other"
        }
        classify = fun(e: dynamic): dynamic {
            val raw: dynamic = old.classify(e)
            if (truth((!truth(raw)))) {
                return null
            }
            val entity: dynamic = (if (truth((jsTypeOf(e) === "string"))) E.byId(e) else e)
            val prep: dynamic = jsOr(jsOr(preparation.has(raw.family)) { preparation.has(raw.id) }) { jsAnd((raw.group === "upper")) { prepNames.test(global_String(jsOr(onPresent(entity) { value10 -> value10.name }) { "" })) } }
            if (truth(jsAnd(prep) { dynamicValue(jsonValue("[\"upper\",\"lower\"]")).includes(raw.group) })) {
                return global_Object.freeze(jsObject { obj11 ->
                    copyFields(obj11, raw)
                    obj11["group"] = "mobility"
                    obj11["role"] = "activation"
                    obj11["primary"] = false
                    obj11["pattern"] = jsAdd("preparacion_", raw.family)
                    obj11["intent50"] = "preparation"
                    obj11["originalGroup50"] = raw.group
                    obj11["movement50"] = "mobility"
                })
            }
            val motor: dynamic = movement(entity, raw)
            val main: dynamic = jsAnd((raw.role === "primary")) { jsOr(jsOr(jsAnd((raw.group === "upper")) { dynamicValue(jsonValue("[\"push\",\"pull\"]")).includes(motor) }) { jsAnd((raw.group === "lower")) { dynamicValue(jsonValue("[\"knee\",\"hip\"]")).includes(motor) } }) { (raw.group === "core") } }
            val primary: dynamic = jsAnd(main) { (!truth(secondaryOnly.has(raw.family))) }
            val role: dynamic = (if (truth(jsAnd((raw.role === "primary")) { (!truth(primary)) })) "accessory" else raw.role)
            return global_Object.freeze(jsObject { obj12 ->
                copyFields(obj12, raw)
                obj12["role"] = role
                obj12["primary"] = primary
                obj12["intent50"] = (if (truth((raw.group === "mobility"))) "recovery" else (if (truth((raw.group === "cardio"))) "cardio" else (if (truth((raw.role === "power"))) "power" else "strength")))
                obj12["movement50"] = motor
            })
        }
        matches = fun(e: dynamic, type: dynamic): dynamic {
            val m: dynamic = meta(e)
            if (truth((!truth(m)))) {
                return false
            }
            return jsAnd(jsAnd(jsAnd((m.group === type)) { (!truth(power(e))) }) { (!truth(impact(e))) }) { (!truth(excludeFromAuto.has(m.family))) }
        }
        inKind = fun(e: dynamic, kind: dynamic): dynamic {
            val m: dynamic = meta(e)
            if (truth(jsOr((!truth(m))) { excludeFromAuto.has(m.family) })) {
                return false
            }
            return (if (truth((kind === "strength"))) jsAnd(jsAnd(dynamicValue(jsonValue("[\"upper\",\"lower\",\"core\"]")).includes(m.group)) { (!truth(power(e))) }) { (!truth(impact(e))) } else (if (truth((kind === "cardio"))) jsAnd(jsAnd(jsAnd((m.group === "cardio")) { (m.role === "conditioning") }) { (!truth(impact(e))) }) { (!truth(power(e))) } else (if (truth((kind === "recovery"))) jsAnd(jsAnd((m.group === "mobility")) { (!truth(impact(e))) }) { (!truth(power(e))) } else false)))
        }
        choosePattern = fun(list: dynamic, selected: dynamic, first: dynamic, second: dynamic): dynamic {
            if (truth((!truth(selected.some((fun(x: dynamic): dynamic {
                return (onPresent(meta(x)) { value22 -> value22.movement50 } === first)
            })))))) {
                val options: dynamic = list.filter((fun(x: dynamic): dynamic {
                    return (onPresent(meta(x)) { value23 -> value23.movement50 } === first)
                }))
                if (truth(options.length)) {
                    return options
                }
            }
            if (truth((!truth(selected.some((fun(x: dynamic): dynamic {
                return (onPresent(meta(x)) { value24 -> value24.movement50 } === second)
            })))))) {
                val options: dynamic = list.filter((fun(x: dynamic): dynamic {
                    return (onPresent(meta(x)) { value25 -> value25.movement50 } === second)
                }))
                if (truth(options.length)) {
                    return options
                }
            }
            return null
        }
        selectionPool = fun(entries: dynamic, selected: dynamic, count: dynamic, arg28: dynamic): dynamic {
            var ctx: dynamic = absent
            ctx = definedOr(arg28) { js("({})") }
            val list: dynamic = entries.filter((fun(e: dynamic): dynamic {
                return jsAnd(inKind(e, ctx.kind)) { jsOr(jsOr((ctx.kind !== "strength")) { (!truth(dynamicValue(jsonValue("[\"upper\",\"lower\",\"core\"]")).includes(ctx.type))) }) { matches(e, ctx.type) } }
            }))
            if (truth((!truth(list.length)))) {
                return js("[]")
            }
            if (truth((ctx.kind === "strength"))) {
                val mains: dynamic = list.filter((fun(e: dynamic): dynamic {
                    return onPresent(meta(e)) { value29 -> value29.primary }
                }))
                val type: dynamic = ctx.type
                if (truth((type === "upper"))) {
                    val pattern: dynamic = choosePattern(mains, selected, "push", "pull")
                    if (truth(onPresent(pattern) { value30 -> value30.length })) {
                        return pattern
                    }
                } else {
                    if (truth((type === "lower"))) {
                        val pattern: dynamic = choosePattern(mains, selected, "knee", "hip")
                        if (truth(onPresent(pattern) { value31 -> value31.length })) {
                            return pattern
                        }
                    } else {
                        if (truth((type === "core"))) {
                            val pattern: dynamic = choosePattern(mains, selected, "brace", "trunk")
                            if (truth(onPresent(pattern) { value32 -> value32.length })) {
                                return pattern
                            }
                        } else {
                            if (true) {
                                val iterator33: dynamic = iteratorOf(jsonValue("[\"lower\",\"upper\",\"core\"]"))
                                while (true) {
                                    val next34: dynamic = iterator33.next()
                                    if (truth(next34.done)) break
                                    var group: dynamic = absent
                                    group = next34.value
                                    if (truth((!truth(selected.some((fun(x: dynamic): dynamic {
                                        return (onPresent(meta(x)) { value35 -> value35.group } === group)
                                    })))))) {
                                        val options: dynamic = mains.filter((fun(x: dynamic): dynamic {
                                            return (onPresent(meta(x)) { value36 -> value36.group } === group)
                                        }))
                                        if (truth(options.length)) {
                                            return options
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                val enough: dynamic = jsAtLeast(selected.filter((fun(e: dynamic): dynamic {
                    return onPresent(meta(e)) { value37 -> value37.primary }
                })).length, 2)
                val already: dynamic = selected.some((fun(e: dynamic): dynamic {
                    return (onPresent(meta(e)) { value38 -> value38.role } === "accessory")
                }))
                if (truth(jsAnd(jsAnd(jsAnd(jsAtLeast(count, 4)) { enough }) { (!truth(already)) }) { (selected.length === jsSubtract(count, 1)) })) {
                    val extras: dynamic = list.filter((fun(e: dynamic): dynamic {
                        return (onPresent(meta(e)) { value39 -> value39.role } === "accessory")
                    }))
                    if (truth(extras.length)) {
                        return extras
                    }
                }
                return (if (truth(mains.length)) mains else list.filter((fun(e: dynamic): dynamic {
                    return jsAnd((onPresent(meta(e)) { value40 -> value40.role } === "accessory")) { (count === 1) }
                })))
            }
            if (truth((ctx.kind === "cardio"))) {
                return list.filter((fun(e: dynamic): dynamic {
                    return (onPresent(meta(e)) { value41 -> value41.role } === "conditioning")
                }))
            }
            if (truth((ctx.kind === "recovery"))) {
                if (truth((!truth(selected.length)))) {
                    val mobility: dynamic = list.filter((fun(e: dynamic): dynamic {
                        return (onPresent(meta(e)) { value42 -> value42.role } === "mobility")
                    }))
                    if (truth(mobility.length)) {
                        return mobility
                    }
                }
                val other: dynamic = list.filter((fun(e: dynamic): dynamic {
                    return (!truth(selected.some((fun(x: dynamic): dynamic {
                        return (family(x) === family(e))
                    }))))
                }))
                return (if (truth(other.length)) other else list)
            }
            return list
        }
        score = fun(e: dynamic, p: dynamic, kind: dynamic, arg45: dynamic): dynamic {
            var selected: dynamic = absent
            selected = definedOr(arg45) { js("[]") }
            val m: dynamic = meta(e)
            if (truth((!truth(m)))) {
                return (-numeric(10000))
            }
            val prior: dynamic = old.score(e, p, kind, selected)
            var `value`: dynamic = prior
            if (truth((kind === "strength"))) {
                run {
                    `value` = jsAdd(`value`, (if (truth(m.primary)) 55 else (-numeric(80))))
                }
                val used: dynamic = selected.filter((fun(x: dynamic): dynamic {
                    return (onPresent(meta(x)) { value46 -> value46.movement50 } === m.movement50)
                })).length
                run {
                    `value` = jsSubtract(`value`, jsMultiply(used, 32))
                }
                if (truth(jsAnd((m.group === "upper")) { (m.movement50 === "push") })) {
                    run {
                        `value` = jsAdd(`value`, 12)
                    }
                }
                if (truth(jsAnd((m.group === "lower")) { (m.movement50 === "knee") })) {
                    run {
                        `value` = jsAdd(`value`, 10)
                    }
                }
            } else {
                if (truth((kind === "cardio"))) {
                    run {
                        `value` = jsAdd(`value`, (if (truth((m.role === "conditioning"))) 30 else (-numeric(500))))
                    }
                } else {
                    if (truth((kind === "recovery"))) {
                        run {
                            `value` = jsAdd(`value`, (if (truth((m.role === "mobility"))) 20 else 0))
                        }
                    }
                }
            }
            return `value`
        }
        evidence = fun(e: dynamic, arg49: dynamic, arg50: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg49) { js("({})") }
            var date: dynamic = absent
            date = definedOr(arg50) { E.dateKey() }
            val m: dynamic = meta(e)
            val seen: dynamic = jsConstruct(global_Set, js("[]"))
            if (truth((!truth(m)))) {
                return 0
            }
            val d0: dynamic = global_Date.parse(jsAdd(date, "T12:00:00Z"))
            if (true) {
                val iterator51: dynamic = iteratorOf(global_Object.entries(jsOr(logs) { js("({})") }))
                while (true) {
                    val next52: dynamic = iterator51.next()
                    if (truth(next52.done)) break
                    var day: dynamic = absent
                    var l: dynamic = absent
                    val unpacked53: dynamic = fromIterable(next52.value)
                    day = unpacked53[0]
                    l = unpacked53[1]
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(day)))) { (!truth(l)) }) { (!truth(l.complete)) }) { l.pain }) { l.fatigue }) { l.manual })) {
                        continue
                    }
                    val distance: dynamic = jsDivide(jsSubtract(d0, global_Date.parse(jsAdd(day, "T12:00:00Z"))), 86400000)
                    if (truth(jsOr(jsOr(jsLess(distance, 1)) { jsMore(distance, 21) }) { (!truth(jsAnd(jsAtLeast(numeric(l.rpe), 1)) { jsAtMost(numeric(l.rpe), 7) })) })) {
                        continue
                    }
                    val success: dynamic = dynamicValue(jsOr(l.items) { js("[]") }).some((fun(x: dynamic): dynamic {
                        val xMeta: dynamic = meta(x.id)
                        return jsAnd(jsAnd(xMeta) { jsOr((xMeta.family === m.family)) { jsAnd(jsAnd((xMeta.group === m.group)) { (xMeta.movement50 === m.movement50) }) { (m.movement50 !== "isolation") } } }) { dynamicValue(jsOr(x["actual"]) { js("[]") }).some((fun(s: dynamic): dynamic {
                            return (onPresent(s) { value54 -> value54.done } === true)
                        })) }
                    }))
                    if (truth(success)) {
                        seen.add(day)
                    }
                }
            }
            return seen.size
        }
        adjustDose = fun(e: dynamic, p: dynamic, kind: dynamic, item: dynamic, arg57: dynamic): dynamic {
            var ctx: dynamic = absent
            ctx = definedOr(arg57) { js("({})") }
            val `out`: dynamic = jsObject { obj58 ->
                copyFields(obj58, old.adjustDose(e, p, kind, item, ctx))
            }
            val m: dynamic = meta(e)
            if (truth((!truth(m)))) {
                return `out`
            }
            val similar: dynamic = evidence(e, ctx.logs, jsOr(ctx.date) { E.dateKey() })
            val symptoms: dynamic = global_Boolean(jsOr(jsOr(jsOr(onPresent(ctx.status) { value59 -> value59.reduced }) { onPresent(ctx.feedback) { value60 -> value60.strain } }) { onPresent(ctx.logs) { value61 -> onPresent(value61[ctx.date]) { value62 -> value62.pain } } }) { onPresent(ctx.logs) { value63 -> onPresent(value63[ctx.date]) { value64 -> value64.fatigue } } })
            val canAdvance: dynamic = jsAnd(jsAnd(jsAnd(jsAnd((!truth(symptoms))) { (onPresent(ctx.feedback) { value65 -> value65.canProgress } === true) }) { jsAtLeast(similar, 2) }) { (!truth(`out`.noAutoLoad)) }) { jsAtLeast(global_Number(onPresent(p) { value66 -> value66.age }), 18) }
            if (truth(jsAnd((kind === "strength")) { m.primary })) {
                val base: dynamic = (if (truth((e.unit === "sec"))) 20 else (if (truth((onPresent(p) { value67 -> value67.goal } === "muscle"))) 10 else 8))
                val cap: dynamic = jsAdd(base, (if (truth(canAdvance)) (if (truth((e.unit === "sec"))) 5 else 1) else 0))
                `out`.target = global_Math.max(1, global_Math.min(jsOr(global_Number(`out`.target)) { base }, cap))
                if (truth((!truth(canAdvance)))) {
                    `out`.noAutoLoad = true
                }
            } else {
                if (truth((kind === "recovery"))) {
                    `out`.noAutoLoad = true
                } else {
                    if (truth(jsAnd(jsAnd((kind === "cardio")) { (e.unit === "sec") }) { (!truth(canAdvance)) })) {
                        `out`.target = global_Math.max(1, global_Math.min(jsOr(global_Number(`out`.target)) { 30 }, 30))
                    }
                }
            }
            `out`.planning49 = jsObject { obj78 ->
                obj78["group"] = m.group
                obj78["role"] = m.role
                obj78["pattern"] = m.pattern
            }
            `out`.quality50 = jsObject { obj81 ->
                obj81["intent"] = m.intent50
                obj81["movement"] = m.movement50
                obj81["relatedSessions"] = similar
                obj81["progression"] = (if (truth(canAdvance)) "con_evidencia" else "mantener")
            }
            return `out`
        }
        if (truth(root.HunterQuality50)) {
            return absent
        }
        old = root.HunterGroups49
        E = root.Engine
        if (truth(jsOr(jsOr((!truth(old))) { (!truth(E)) }) { (!truth(root.Adapt)) })) {
            throw global_Error("Quality50: motor 38.49 ausente")
        }
        val version: dynamic = "38.49-quality1"
        preparation = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"prone\",\"reverse\",\"wallslide\",\"scapular\",\"contralateral\",\"pau-pushchild\",\"pau-armworld\",\"pau-armcircle-f\",\"pau-armcircle-b\",\"pau-armupdown\",\"pau-armpulses\",\"pau-armfly\",\"pau-armsback\",\"pau-armraise\",\"pau-armcurl\",\"front-seated-reference\",\"w-t19\",\"y-t-w19\",\"hs379-scapular\",\"hs379-shoulderset\",\"home-scappush30\",\"new46-scapdip\",\"new46-scapularhang\",\"new46-bander\",\"new46-bandir\",\"new46-banddiagonal\",\"new46-serratusband\",\"new46-ringsrotation\",\"new46-ringturnout\",\"new46-cubanrotation\",\"new46-cableer90\",\"new46-supineshoulderir\",\"new46-halo\",\"new46-shouldercar\",\"new46-airplanefree\",\"new46-seatedhinge\"]")))
        prepNames = jsRegExp("\\b(?:estiramiento|movilidad articular|c[ií]rculos? de hombros|c[ií]rculos? de brazos|deslizamiento (?:de brazos )?en pared|retracci[oó]n escapular|elevaci[oó]n corta de hombros|rotaci[oó]n (?:externa|interna) de hombro|rotaci[oó]n escapular)\\b", "i")
        secondaryOnly = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"gym-face-pull23\",\"new46-cubanpress\"]")))
        excludeFromAuto = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"new46-dbpushpress\",\"new46-pushjerk\",\"new46-hangclean\",\"new46-bottomclean\",\"new46-dbsnatch\"]")))
        byId = jsConstruct(global_Map, js("[]"))
        val toId: dynamic = (fun(e: dynamic): dynamic {
            return (if (truth((jsTypeOf(e) === "string"))) e else onPresent(e) { value3 -> value3.id })
        })
        if (true) {
            val iterator13: dynamic = iteratorOf(E.exercises)
            while (true) {
                val next14: dynamic = iterator13.next()
                if (truth(next14.done)) break
                var e: dynamic = absent
                e = next14.value
                if (truth(byId.has(e.id))) {
                    throw global_Error("Quality50: ID duplicado")
                }
                byId["set"](e.id, classify(e))
            }
        }
        meta = (fun(e: dynamic): dynamic {
            return jsOr(byId["get"](toId(e))) { null }
        })
        family = (fun(e: dynamic): dynamic {
            return jsOr(onPresent(meta(e)) { value15 -> value15.family }) { old.family(e) }
        })
        power = (fun(e: dynamic): dynamic {
            return old.power(e)
        })
        impact = (fun(e: dynamic): dynamic {
            return old.impact(e)
        })
        val byGroup: dynamic = (fun(k: dynamic): dynamic {
            return E.exercises.filter((fun(e: dynamic): dynamic {
                return (onPresent(meta(e)) { value82 -> value82.group } === k)
            }))
        })
        val groups: dynamic = global_Object.fromEntries(global_Object.keys(old.labels).map((fun(k: dynamic): dynamic {
            return arrayOf<dynamic>(k, byGroup(k).length)
        })))
        val effective: dynamic = global_Object.freeze(jsObject { obj83 ->
            copyFields(obj83, old)
            obj83["version"] = version
            obj83["classify"] = meta
            obj83["matches"] = matches
            obj83["inKind"] = inKind
            obj83["selectionPool"] = selectionPool
            obj83["score"] = score
            obj83["adjustDose"] = adjustDose
            obj83["byGroup"] = byGroup
            obj83["family"] = family
            obj83["inventory"] = (fun(): dynamic {
                return jsObject { obj84 ->
                    obj84["total"] = byId.size
                    obj84["groups"] = groups
                }
            })
        })
        root.HunterGroups49 = effective
        if (truth(root.HunterCatalog46)) {
            root.HunterCatalog46 = global_Object.freeze(jsObject { obj89 ->
                copyFields(obj89, root.HunterCatalog46)
                obj89["version"] = version
                obj89["byGroup"] = byGroup
            })
        }
        root.HunterQuality50 = global_Object.freeze(jsObject { obj92 ->
            obj92["version"] = version
            obj92["classification"] = meta
            obj92["evidence"] = evidence
            obj92["inventory"] = (fun(): dynamic {
                return jsObject { obj93 ->
                    copyFields(obj93, effective.inventory())
                    obj93["preparatory"] = dynamicValue(jsArray { array95 ->
                        appendAll(array95, byId.values())
                    }).filter((fun(m: dynamic): dynamic {
                        return (m.intent50 === "preparation")
                    })).length
                    obj93["upperPrimaries"] = dynamicValue(jsArray { array97 ->
                        appendAll(array97, byId.values())
                    }).filter((fun(m: dynamic): dynamic {
                        return jsAnd((m.group === "upper")) { m.primary }
                    })).length
                }
            })
        })
        return absent
    }))(global_window)
}
