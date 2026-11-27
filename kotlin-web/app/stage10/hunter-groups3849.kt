@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntergroups3849

import hunter.browser.*

@JsName("Error") private external var global_Error: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Warm24") private external var global_Warm24: dynamic
@JsName("Warm25") private external var global_Warm25: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterGroups3849() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var A: dynamic = absent
        var byId: dynamic = absent
        var families: dynamic = absent
        var classify: dynamic = absent
        var level: dynamic = absent
        var power: dynamic = absent
        var impact: dynamic = absent
        var family: dynamic = absent
        var matches: dynamic = absent
        var inKind: dynamic = absent
        var reduced: dynamic = absent
        var reasons: dynamic = absent
        var distance: dynamic = absent
        var powerReady: dynamic = absent
        var context: dynamic = absent
        var selectionPool: dynamic = absent
        var score: dynamic = absent
        var adjustDose: dynamic = absent
        var phase: dynamic = absent
        matches = fun(e: dynamic, type: dynamic): dynamic {
            val m: dynamic = classify(e)
            if (truth((!truth(m)))) {
                return false
            }
            if (truth((m.group === type))) {
                return true
            }
            return jsAnd((type === "cardio")) { m.jumpPower }
        }
        inKind = fun(e: dynamic, kind: dynamic): dynamic {
            return (if (truth((kind === "strength"))) dynamicValue(jsonValue("[\"upper\",\"lower\",\"core\"]")).some((fun(t: dynamic): dynamic {
                return matches(e, t)
            })) else (if (truth((kind === "cardio"))) matches(e, "cardio") else (if (truth((kind === "recovery"))) matches(e, "mobility") else false)))
        }
        reduced = fun(p: dynamic, arg24: dynamic): dynamic {
            var status: dynamic = absent
            status = definedOr(arg24) { js("({})") }
            return (!truth((!truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(status.reduced) { onPresent(p) { value25 -> value25.limits } }) { onPresent(p) { value26 -> onPresent(value26.health) { value27 -> value27.special } } }) { onPresent(p) { value28 -> onPresent(value28.health) { value29 -> value29.acute } } }) { onPresent(p) { value30 -> onPresent(value30.health) { value31 -> value31.redFlags } } }) { jsAnd(onPresent(p) { value32 -> onPresent(value32.health) { value33 -> value33.pain } }) { (p.health.pain !== "none") } }) { dynamicValue(jsOr(onPresent(p) { value35 -> value35.injuries }) { js("[]") }).some((fun(x: dynamic): dynamic {
                return jsAnd(x) { (x.stage !== "past") }
            })) }) { A.mode(p).reduced }))))
        }
        reasons = fun(e: dynamic, p: dynamic): dynamic {
            val m: dynamic = classify(e)
            val `out`: dynamic = js("[]")
            if (truth(jsOr((!truth(m))) { (!truth(p)) })) {
                return jsonValue("[\"Faltan datos de planificación\"]")
            }
            val noImpact: dynamic = jsOr(jsOr(onPresent(p.restrictions) { value38 -> value38.noImpact }) { onPresent(p.physicalLimits) { value39 -> value39.noImpact } }) { onPresent(p.preferences) { value40 -> value40.noImpact } }
            if (truth(jsAnd(m.impact) { noImpact })) {
                `out`.push("Saltos e impactos excluidos por el perfil")
            }
            if (truth(jsAnd(jsOr(m.power) { m.impact }) { jsOr(reduced(p)) { jsLess(global_Number(p.age), 18) } })) {
                `out`.push("Potencia e impactos pendientes de revisión individual")
            }
            val caps: dynamic = jsConstruct(global_Set, arrayOf<dynamic>((if (truth(global_Array.isArray(p.capabilities46))) p.capabilities46 else js("[]"))))
            if (truth(jsAnd(jsAnd(m.impact) { (m.group !== "upper") }) { (!truth(caps.has("landing"))) })) {
                `out`.push("Confirma primero tu control de aterrizaje en Perfil")
            }
            if (truth(jsAnd(jsAnd(m.power) { (m.group === "upper") }) { (!truth(caps.has("supporthold"))) })) {
                `out`.push("Falta confirmar control de apoyos para el empuje explosivo")
            }
            if (truth(jsAnd(jsAnd(m.power) { dynamicValue(jsRegExp("clean", "")).test(m.family) }) { jsOr((!truth(caps.has("ballistichinge")))) { (!truth(caps.has("olympic"))) } })) {
                `out`.push("Falta práctica previa de la cargada y su recepción")
            }
            return `out`
        }
        powerReady = fun(p: dynamic, arg59: dynamic): dynamic {
            var logs: dynamic = absent
            var date: dynamic = absent
            var tracker: dynamic = absent
            var status: dynamic = absent
            var feedback: dynamic = absent
            val unpacked60: dynamic = definedOr(arg59) { js("({})") }
            logs = definedOr(unpacked60["logs"]) { js("({})") }
            date = definedOr(unpacked60["date"]) { E.dateKey() }
            tracker = unpacked60["tracker"]
            status = definedOr(unpacked60["status"]) { js("({})") }
            feedback = definedOr(unpacked60["feedback"]) { js("({})") }
            if (truth(jsOr(jsOr(reduced(p, status)) { feedback.strain }) { jsLess(global_Number(jsOr(p.trainingScale) { 1 }), 1) })) {
                return false
            }
            if (truth(jsOr(jsOr(onPresent(logs[date]) { value61 -> value61.pain }) { onPresent(logs[date]) { value62 -> value62.fatigue } }) { jsAtLeast(global_Number(onPresent(logs[date]) { value63 -> value63.rpe }), 8) })) {
                return false
            }
            val dates: dynamic = jsConstruct(global_Set, js("[]"))
            val isPower: dynamic = (fun(id: dynamic): dynamic {
                val m: dynamic = jsOr(classify(id)) { families["get"](id) }
                return jsOr(onPresent(m) { value64 -> value64.power }) { onPresent(m) { value65 -> value65.impact } }
            })
            if (true) {
                val iterator66: dynamic = iteratorOf(global_Object.entries(logs))
                while (true) {
                    val next67: dynamic = iterator66.next()
                    if (truth(next67.done)) break
                    var day: dynamic = absent
                    var log: dynamic = absent
                    val unpacked68: dynamic = fromIterable(next67.value)
                    day = unpacked68[0]
                    log = unpacked68[1]
                    if (truth(jsOr(jsOr((!truth(dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(day)))) { jsAtLeast(day, date) }) { (!truth(log)) })) {
                        continue
                    }
                    val items: dynamic = dynamicValue(jsOr(log.items) { js("[]") }).filter((fun(x: dynamic): dynamic {
                        return jsAnd(isPower(x.id)) { jsOr(log.complete) { onPresent(x["actual"]) { value69 -> value69.some((fun(a: dynamic): dynamic {
                            return a.done
                        })) } } }
                    }))
                    if (truth(items.length)) {
                        if (truth(jsLess(distance(day, date), 2))) {
                            return false
                        }
                        if (truth(jsLess(distance(day, date), 7))) {
                            dates.add(day)
                        }
                    }
                }
            }
            if (true) {
                val iterator70: dynamic = iteratorOf(jsOr(onPresent(tracker) { value72 -> run { val owner74: dynamic = value72; onPresent(owner74.snapshot) { method73 -> invokeWith(method73, owner74, js("[]")) } } }) { js("[]") })
                while (true) {
                    val next71: dynamic = iterator70.next()
                    if (truth(next71.done)) break
                    var row: dynamic = absent
                    row = next71.value
                    if (truth((!truth(isPower(row.id))))) {
                        continue
                    }
                    if (truth(jsLess(distance(row.date, date), 2))) {
                        return false
                    }
                    if (truth(jsAnd(jsLess(row.date, date)) { jsLess(distance(row.date, date), 7) })) {
                        dates.add(row.date)
                    }
                }
            }
            return jsLess(dates.size, 3)
        }
        context = fun(p: dynamic, kind: dynamic, type: dynamic, arg77: dynamic): dynamic {
            var extras: dynamic = absent
            extras = definedOr(arg77) { js("({})") }
            val ctx: dynamic = jsObject { obj78 ->
                obj78["p"] = p
                obj78["kind"] = kind
                obj78["type"] = jsOr(type) { (if (truth((kind === "cardio"))) "cardio" else (if (truth((kind === "recovery"))) "mobility" else "general")) }
                copyFields(obj78, extras)
            }
            if (truth((ctx.type === "full"))) {
                ctx.type = "general"
            }
            ctx.ready = powerReady(p, ctx)
            return ctx
        }
        selectionPool = fun(entries: dynamic, selected: dynamic, count: dynamic, ctx: dynamic): dynamic {
            var list: dynamic = entries.filter((fun(e: dynamic): dynamic {
                return jsOr(ctx.ready) { jsAnd((!truth(power(e)))) { (!truth(impact(e))) } }
            }))
            if (truth(selected.some((fun(e: dynamic): dynamic {
                return jsOr(power(e)) { impact(e) }
            })))) {
                list = list.filter((fun(e: dynamic): dynamic {
                    return jsAnd((!truth(power(e)))) { (!truth(impact(e))) }
                }))
            }
            if (truth((!truth(list.length)))) {
                return js("[]")
            }
            val powerSlots: dynamic = (if (truth((ctx.kind === "cardio"))) jsAtLeast(count, 2) else jsAnd(jsAtLeast(count, 3)) { dynamicValue(jsonValue("[\"lower\",\"general\"]")).includes(ctx.type) })
            if (truth(jsAnd(jsAnd((!truth(selected.length))) { powerSlots }) { ctx.ready })) {
                val xs: dynamic = list.filter(power)
                if (truth(xs.length)) {
                    return xs
                }
            }
            if (truth((ctx.kind === "strength"))) {
                val needed: dynamic = global_Math.min(jsSubtract(count, (if (truth(powerSlots)) 1 else 0)), global_Math.max(1, global_Math.ceil(jsMultiply(count, 0.6))))
                val mains: dynamic = selected.filter((fun(e: dynamic): dynamic {
                    return onPresent(classify(e)) { value85 -> value85.primary }
                })).length
                val primaries: dynamic = list.filter((fun(e: dynamic): dynamic {
                    return onPresent(classify(e)) { value86 -> value86.primary }
                }))
                if (truth(jsAnd(primaries.length) { jsLess(mains, needed) })) {
                    list = primaries
                } else {
                    val accessory: dynamic = selected.filter((fun(e: dynamic): dynamic {
                        return (onPresent(classify(e)) { value87 -> value87.role } === "accessory")
                    })).length
                    if (truth(jsOr(jsAtLeast(accessory, 1)) { jsLess(count, 3) })) {
                        list = list.filter((fun(e: dynamic): dynamic {
                            return (onPresent(classify(e)) { value88 -> value88.role } !== "accessory")
                        }))
                    }
                    if (truth(jsAnd(jsAnd((selected.length === jsSubtract(count, 1))) { jsAtLeast(count, 4) }) { (accessory === 0) })) {
                        val xs: dynamic = list.filter((fun(e: dynamic): dynamic {
                            return (onPresent(classify(e)) { value89 -> value89.role } === "accessory")
                        }))
                        if (truth(xs.length)) {
                            list = xs
                        }
                    }
                }
            }
            return list
        }
        score = fun(e: dynamic, p: dynamic, kind: dynamic, arg92: dynamic): dynamic {
            var selected: dynamic = absent
            selected = definedOr(arg92) { js("[]") }
            val m: dynamic = classify(e)
            if (truth((!truth(m)))) {
                return (-numeric(1000))
            }
            val n: dynamic = global_Math.max(0, dynamicValue(jsonValue("[\"basic\",\"intermediate\",\"advanced\"]")).indexOf(p.difficulty))
            var s: dynamic = 0
            run {
                s = jsSubtract(s, jsMultiply(selected.filter((fun(x: dynamic): dynamic {
                    return (onPresent(classify(x)) { value93 -> value93.pattern } === m.pattern)
                })).length, 38))
            }
            if (truth((kind === "strength"))) {
                run {
                    s = jsAdd(s, (if (truth(m.primary)) 30 else (if (truth((m.role === "accessory"))) (-numeric(12)) else 0)))
                }
                if (truth((!truth(reduced(p))))) {
                    run {
                        s = jsSubtract(s, jsMultiply(global_Math.max(0, jsSubtract(n, m.level)), 35))
                    }
                }
                if (truth(jsAnd(jsAnd((p.equipment === "gym")) { e.gymOnly23 }) { m.primary })) {
                    run {
                        s = jsAdd(s, 14)
                    }
                }
                if (truth(jsAnd((m.role === "accessory")) { dynamicValue(jsRegExp("tobillo|plantar", "")).test(m.pattern) })) {
                    run {
                        s = jsSubtract(s, 35)
                    }
                }
            }
            if (truth(jsAnd((kind === "cardio")) { (!truth(reduced(p))) })) {
                run {
                    s = jsAdd(s, jsMultiply(m.conditioning, 18))
                }
            }
            if (truth(m.power)) {
                run {
                    s = jsSubtract(s, jsMultiply(global_Math.abs(jsSubtract(n, m.level)), 16))
                }
            }
            return s
        }
        adjustDose = fun(e: dynamic, p: dynamic, kind: dynamic, item: dynamic, arg96: dynamic): dynamic {
            var ctx: dynamic = absent
            ctx = definedOr(arg96) { js("({})") }
            val m: dynamic = classify(e)
            val `out`: dynamic = jsObject { obj97 ->
                copyFields(obj97, item)
            }
            if (truth((!truth(m)))) {
                return `out`
            }
            val n: dynamic = global_Math.max(0, dynamicValue(jsonValue("[\"basic\",\"intermediate\",\"advanced\"]")).indexOf(p.difficulty))
            val today: dynamic = onPresent(ctx.logs) { value98 -> value98[jsOr(ctx.date) { E.dateKey() }] }
            val limited: dynamic = jsOr(jsOr(jsOr(jsOr(reduced(p, ctx.status)) { onPresent(ctx.feedback) { value99 -> value99.strain } }) { onPresent(today) { value100 -> value100.pain } }) { onPresent(today) { value101 -> value101.fatigue } }) { jsAtLeast(global_Number(onPresent(today) { value102 -> value102.rpe }), 8) }
            val scale: dynamic = global_Math.max(0.25, global_Math.min(1, jsOr(global_Number(p.trainingScale)) { 1 }))
            if (truth(m.power)) {
                val target: dynamic = (if (truth((e.unit === "sec"))) dynamicValue(jsonValue("[8,10,12]"))[n] else dynamicValue(jsonValue("[3,4,5]"))[n])
                `out`.target = global_Math.max(1, global_Math.min(jsOr(global_Number(`out`.target)) { target }, global_Math.floor(jsMultiply(target, scale))))
                `out`.sets = global_Math.max(1, global_Math.min(jsOr(global_Number(`out`.sets)) { 1 }, (if (truth(limited)) 1 else 2)))
                `out`.rest = 90
                `out`.noAutoLoad = true
                `out`.safetyNote = dynamicValue(arrayOf<dynamic>(`out`.safetyNote, "Potencia: repeticiones de calidad; termina si pierdes la recepción o el control.")).filter(global_Boolean).join(" ")
            } else {
                if (truth(m.impact)) {
                    `out`.target = global_Math.max(1, global_Math.min(jsOr(global_Number(`out`.target)) { 1 }, (if (truth((e.unit === "sec"))) dynamicValue(jsonValue("[15,20,25]"))[n] else dynamicValue(jsonValue("[4,5,6]"))[n])))
                    `out`.sets = global_Math.max(1, global_Math.min(jsOr(global_Number(`out`.sets)) { 1 }, 2))
                    `out`.rest = global_Math.min(90, global_Math.max(60, jsOr(global_Number(`out`.rest)) { 0 }))
                } else {
                    if (truth(jsAnd(jsAnd(jsAnd(jsAnd((kind === "cardio")) { (e.unit === "sec") }) { (!truth(limited)) }) { (scale === 1) }) { jsAtLeast(m.conditioning, 2) })) {
                        `out`.target = global_Math.max(jsOr(global_Number(`out`.target)) { 1 }, dynamicValue(jsonValue("[35,40,45]"))[n])
                    }
                }
            }
            `out`.rest = global_Math.min(90, global_Math.max(30, jsOr(global_Number(`out`.rest)) { 30 }))
            `out`.planning49 = jsObject { obj125 ->
                obj125["group"] = m.group
                obj125["role"] = m.role
                obj125["pattern"] = m.pattern
            }
            return `out`
        }
        phase = fun(day: dynamic, p: dynamic): dynamic {
            if (truth(jsOr((!truth(onPresent(day) { value128 -> onPresent(value128.items) { value129 -> value129.length } }))) { (!truth(p)) })) {
                return null
            }
            val used: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(day.items.map((fun(x: dynamic): dynamic {
                return family(E.byId(x.id))
            }))))
            val usedKeys: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(day.items.map((fun(x: dynamic): dynamic {
                return E.byId(x.id)
            })).filter(global_Boolean).map((fun(e: dynamic): dynamic {
                return global_Warm24.key(e)
            }))))
            val options: dynamic = dynamicValue(jsonValue("[\"fastmarch\",\"stepjack\",\"heelback\",\"box\",\"heeldig\",\"sidestep\",\"seatedpunch\",\"seatedmarch\",\"seatedstep\"]")).map(E.byId).filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(e) { (!truth(impact(e))) }) { (!truth(power(e))) }) { (!truth(used.has(family(e)))) }) { (!truth(usedKeys.has(global_Warm24.key(e)))) }) { onPresent(root.HunterDynamic381) { value130 -> value130.compatible(e, p, reduced(p)) } }
            }))
            val first: dynamic = jsOr(options.find((fun(e: dynamic): dynamic {
                return (!truth(e.seated))
            }))) { options[0] }
            if (truth((!truth(first)))) {
                return jsonValue("{\"blocked\":true,\"reason\":\"Revisa las indicaciones del perfil: no hay cardio preparatorio compatible.\"}")
            }
            val sec: dynamic = global_Warm25.seconds(p)
            return jsObject { obj131 ->
                obj131["warm"] = jsObject { obj132 ->
                    obj132["id"] = first.id
                    obj132["seconds"] = sec
                    obj132["label"] = jsAdd("Calentamiento progresivo · ", first.name)
                }
                obj131["cool"] = jsObject { obj133 ->
                    obj133["id"] = first.id
                    obj133["seconds"] = sec
                    obj133["label"] = jsAdd("Cierre suave · ", first.name)
                }
                obj131["reason"] = "Cada fase dura como máximo un minuto. El cierre se hace a ritmo suave y no suma series principales."
            }
        }
        if (truth(root.HunterGroups49)) {
            return absent
        }
        E = root.Engine
        A = root.Adapt
        val rows: dynamic = root.HunterGroupRows49
        if (truth(jsOr(jsOr((!truth(E))) { (!truth(A)) }) { (onPresent(rows) { value3 -> value3.length } !== 1200) })) {
            throw global_Error("38.49: faltan metadatos de planificación")
        }
        val labels: dynamic = global_Object.freeze(jsonValue("{\"upper\":\"Tren superior\",\"lower\":\"Tren inferior\",\"core\":\"Tren central\",\"cardio\":\"Cardio\",\"mobility\":\"Movilidad y activación\"}"))
        byId = jsConstruct(global_Map, js("[]"))
        families = jsConstruct(global_Map, js("[]"))
        if (true) {
            val iterator4: dynamic = iteratorOf(rows)
            while (true) {
                val next5: dynamic = iterator4.next()
                if (truth(next5.done)) break
                var id: dynamic = absent
                var group: dynamic = absent
                var role: dynamic = absent
                var pattern: dynamic = absent
                var family: dynamic = absent
                var level: dynamic = absent
                var impact: dynamic = absent
                var conditioning: dynamic = absent
                val unpacked6: dynamic = fromIterable(next5.value)
                id = unpacked6[0]
                group = unpacked6[1]
                role = unpacked6[2]
                pattern = unpacked6[3]
                family = unpacked6[4]
                level = unpacked6[5]
                impact = unpacked6[6]
                conditioning = unpacked6[7]
                val m: dynamic = global_Object.freeze(jsObject { obj7 ->
                    obj7["id"] = id
                    obj7["group"] = group
                    obj7["role"] = role
                    obj7["pattern"] = pattern
                    obj7["family"] = family
                    obj7["level"] = level
                    obj7["impact"] = impact
                    obj7["conditioning"] = conditioning
                    obj7["power"] = (role === "power")
                    obj7["jumpPower"] = jsAnd(jsAnd((role === "power")) { (group === "lower") }) { impact }
                    obj7["primary"] = (role === "primary")
                })
                if (truth(jsOr(byId.has(id)) { (!truth(labels[group])) })) {
                    throw global_Error("38.49: clasificación duplicada o desconocida")
                }
                byId["set"](id, m)
                if (truth(jsOr((!truth(families.has(family)))) { (id === family) })) {
                    families["set"](family, m)
                }
            }
        }
        if (true) {
            val iterator8: dynamic = iteratorOf(E.exercises)
            while (true) {
                val next9: dynamic = iterator8.next()
                if (truth(next9.done)) break
                var e: dynamic = absent
                e = next9.value
                if (truth((!truth(byId.has(e.id))))) {
                    throw global_Error(jsAdd("38.49: ejercicio sin clasificar ", e.id))
                }
            }
        }
        removeKey(root, "HunterGroupRows49")
        classify = (fun(e: dynamic): dynamic {
            return jsOr(byId["get"]((if (truth((jsTypeOf(e) === "string"))) e else onPresent(e) { value10 -> value10.id }))) { null }
        })
        level = (fun(e: dynamic): dynamic {
            return nullableOr(onPresent(classify(e)) { value11 -> value11.level }) { onPresent(e) { value12 -> value12.level } }
        })
        power = (fun(e: dynamic): dynamic {
            return (onPresent(classify(e)) { value13 -> value13.power } === true)
        })
        impact = (fun(e: dynamic): dynamic {
            return (onPresent(classify(e)) { value14 -> value14.impact } === true)
        })
        family = (fun(e: dynamic): dynamic {
            return jsOr(jsOr(onPresent(classify(e)) { value15 -> value15.family }) { onPresent(e) { value16 -> value16.baseId } }) { onPresent(e) { value17 -> value17.id } }
        })
        val eligible: dynamic = (fun(e: dynamic, p: dynamic): dynamic {
            return (reasons(e, p).length === 0)
        })
        val originalAllowed: dynamic = A.allowed
        val originalReasons: dynamic = A.reasons
        A.allowed = (fun(e: dynamic, p: dynamic, arg45: dynamic): dynamic {
            val receiver43: dynamic = js("this")
            val callArgs44: dynamic = js("arguments")
            var args: dynamic = absent
            args = argsAfter(callArgs44, 2)
            return jsAnd(eligible(e, p)) { run { val target47: dynamic = originalAllowed; invokeWith(target47.call, target47, jsArray { array46 ->
                array46.push(receiver43)
                array46.push(e)
                array46.push(p)
                appendAll(array46, args)
            }) } }
        })
        A.reasons = (fun(e: dynamic, p: dynamic, arg52: dynamic): dynamic {
            val receiver50: dynamic = js("this")
            val callArgs51: dynamic = js("arguments")
            var args: dynamic = absent
            args = argsAfter(callArgs51, 2)
            return jsArray { array53 ->
                appendAll(array53, jsConstruct(global_Set, arrayOf<dynamic>(jsArray { array54 ->
                    appendAll(array54, run { val target56: dynamic = originalReasons; invokeWith(target56.call, target56, jsArray { array55 ->
                        array55.push(receiver50)
                        array55.push(e)
                        array55.push(p)
                        appendAll(array55, args)
                    }) })
                    appendAll(array54, reasons(e, p))
                })))
            }
        })
        distance = (fun(a: dynamic, b: dynamic): dynamic {
            return global_Math.abs(jsDivide(jsSubtract(global_Date.parse(jsAdd(a, "T12:00:00Z")), global_Date.parse(jsAdd(b, "T12:00:00Z"))), 86400000))
        })
        val partitions: dynamic = jsConstruct(global_Map, arrayOf<dynamic>(global_Object.keys(labels).map((fun(k: dynamic): dynamic {
            return arrayOf<dynamic>(k, E.exercises.filter((fun(e: dynamic): dynamic {
                return (classify(e).group === k)
            })))
        }))))
        val byGroup: dynamic = (fun(k: dynamic): dynamic {
            return dynamicValue(jsOr(partitions["get"](k)) { js("[]") }).slice()
        })
        root.HunterGroups49 = global_Object.freeze(jsObject { obj136 ->
            obj136["version"] = "38.49"
            obj136["labels"] = labels
            obj136["classify"] = classify
            obj136["level"] = level
            obj136["matches"] = matches
            obj136["inKind"] = inKind
            obj136["eligible"] = eligible
            obj136["reasons"] = reasons
            obj136["byGroup"] = byGroup
            obj136["power"] = power
            obj136["impact"] = impact
            obj136["family"] = family
            obj136["reduced"] = reduced
            obj136["context"] = context
            obj136["powerReady"] = powerReady
            obj136["selectionPool"] = selectionPool
            obj136["score"] = score
            obj136["adjustDose"] = adjustDose
            obj136["phase"] = phase
            obj136["inventory"] = (fun(): dynamic {
                return jsObject { obj137 ->
                    obj137["total"] = byId.size
                    obj137["groups"] = global_Object.fromEntries(global_Object.keys(labels).map((fun(k: dynamic): dynamic {
                        return arrayOf<dynamic>(k, dynamicValue(jsArray { array139 ->
                            appendAll(array139, byId.values())
                        }).filter((fun(m: dynamic): dynamic {
                            return (m.group === k)
                        })).length)
                    })))
                }
            })
        })
        root.HunterCatalog46 = global_Object.freeze(jsObject { obj142 ->
            copyFields(obj142, root.HunterCatalog46)
            obj142["version"] = "38.49"
            obj142["byGroup"] = byGroup
            obj142["groups"] = labels
        })
        return absent
    }))(global_window)
}
