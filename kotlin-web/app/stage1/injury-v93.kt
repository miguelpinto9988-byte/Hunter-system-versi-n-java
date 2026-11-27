@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installinjuryv93

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installInjuryV93() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var Adapt: dynamic = absent
        var previousItems: dynamic = absent
        var avoid: dynamic = absent
        var prefer: dynamic = absent
        var zones: dynamic = absent
        var priorityGroups: dynamic = absent
        var rotate: dynamic = absent
        var items: dynamic = absent
        var summary: dynamic = absent
        priorityGroups = fun(p: dynamic, pool: dynamic): dynamic {
            val activeZones: dynamic = zones(p)
            val groups: dynamic = jsArray { array6 ->
                appendAll(array6, jsConstruct(global_Set, arrayOf<dynamic>(pool.map((fun(e: dynamic): dynamic {
                    return e.group
                })))))
            }
            return groups.map((fun(group: dynamic): dynamic {
                var score: dynamic = 0
                if (true) {
                    val iterator28: dynamic = iteratorOf(activeZones)
                    while (true) {
                        val next29: dynamic = iterator28.next()
                        if (truth(next29.done)) break
                        var z: dynamic = absent
                        z = next29.value
                        if (truth(dynamicValue(jsOr(avoid[z]) { js("[]") }).includes(group))) {
                            run {
                                score = jsSubtract(score, 20)
                            }
                        }
                        if (truth(dynamicValue(jsOr(prefer[z]) { js("[]") }).includes(group))) {
                            run {
                                score = jsAdd(score, 4)
                            }
                        }
                    }
                }
                if (truth(jsAnd((p.focus === "upper")) { dynamicValue(jsonValue("[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"]")).includes(group) })) {
                    run {
                        score = jsAdd(score, 2)
                    }
                }
                if (truth(jsAnd((p.focus === "lower")) { dynamicValue(jsonValue("[\"Piernas\",\"Cadera\"]")).includes(group) })) {
                    run {
                        score = jsAdd(score, 2)
                    }
                }
                if (truth(jsAnd((p.focus === "core")) { (group === "Core") })) {
                    run {
                        score = jsAdd(score, 2)
                    }
                }
                return jsObject { obj30 ->
                    obj30["group"] = group
                    obj30["score"] = score
                }
            })).filter((fun(x: dynamic): dynamic {
                return jsMore(x.score, (-numeric(20)))
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(jsSubtract(b.score, a.score)) { a.group.localeCompare(b.group) }
            })).map((fun(x: dynamic): dynamic {
                return x.group
            }))
        }
        rotate = fun(list: dynamic, n: dynamic): dynamic {
            if (truth((!truth(list.length)))) {
                return js("[]")
            }
            val start: dynamic = jsMod(jsAdd(jsMod(n, list.length), list.length), list.length)
            return jsArray { array33 ->
                appendAll(array33, list.slice(start))
                appendAll(array33, list.slice(0, start))
            }
        }
        items = fun(p: dynamic, kind: dynamic, rotation: dynamic, carry: dynamic): dynamic {
            val active: dynamic = Adapt.active(p)
            val mode: dynamic = Adapt.mode(p)
            if (truth(jsOr((!truth(active.length))) { (!truth(mode.auto)) })) {
                return previousItems(p, kind, rotation, carry)
            }
            val category: dynamic = (if (truth((kind === "strength"))) "Fuerza" else (if (truth((kind === "cardio"))) "Cardio" else "Movilidad"))
            val pool: dynamic = E.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd((e.variant === "base")) { (e.category === category) }) { Adapt.allowed(e, p) }
            }))
            if (truth((!truth(pool.length)))) {
                return js("[]")
            }
            val cap: dynamic = (if (truth((p.minutes === 10))) 2 else (if (truth((p.minutes === 20))) 3 else 5))
            val count: dynamic = (if (truth((kind === "recovery"))) global_Math.min(3, pool.length) else global_Math.min(cap, pool.length))
            var chosen: dynamic = js("[]")
            if (truth((kind === "strength"))) {
                val ranked: dynamic = priorityGroups(p, pool)
                val groups: dynamic = rotate(ranked, rotation)
                val safePool: dynamic = (if (truth(ranked.length)) pool.filter((fun(e: dynamic): dynamic {
                    return ranked.includes(e.group)
                })) else pool)
                val used: dynamic = jsConstruct(global_Set, js("[]"))
                if (true) {
                    val iterator36: dynamic = iteratorOf(groups)
                    while (true) {
                        val next37: dynamic = iterator36.next()
                        if (truth(next37.done)) break
                        var group: dynamic = absent
                        group = next37.value
                        val options: dynamic = safePool.filter((fun(e: dynamic): dynamic {
                            return jsAnd((e.group === group)) { (!truth(used.has(e.id))) }
                        }))
                        if (truth((!truth(options.length)))) {
                            continue
                        }
                        val pick: dynamic = options[jsMod(global_Math.abs(jsAdd(rotation, chosen.length)), options.length)]
                        chosen.push(pick)
                        used.add(pick.id)
                        if (truth(jsAtLeast(chosen.length, count))) {
                            break
                        }
                    }
                }
                if (truth(jsLess(chosen.length, count))) {
                    if (true) {
                        val iterator38: dynamic = iteratorOf(rotate(safePool, rotation))
                        while (true) {
                            val next39: dynamic = iterator38.next()
                            if (truth(next39.done)) break
                            var e: dynamic = absent
                            e = next39.value
                            if (truth(used.has(e.id))) {
                                continue
                            }
                            chosen.push(e)
                            used.add(e.id)
                            if (truth(jsAtLeast(chosen.length, count))) {
                                break
                            }
                        }
                    }
                }
            } else {
                chosen = rotate(pool, rotation).slice(0, count)
            }
            return chosen.map((fun(e: dynamic): dynamic {
                return jsObject { obj40 ->
                    obj40["id"] = e.id
                    obj40["sets"] = 1
                    obj40["target"] = (if (truth((e.unit === "sec"))) (if (truth((kind === "cardio"))) 20 else 15) else (if (truth((kind === "recovery"))) 6 else global_Math.min(8, jsOr(carry) { 6 })))
                    obj40["rest"] = (if (truth((kind === "recovery"))) 25 else (if (truth((kind === "cardio"))) 45 else global_Math.max(75, jsOr(p.restSeconds) { 90 })))
                }
            }))
        }
        summary = fun(p: dynamic): dynamic {
            val active: dynamic = Adapt.active(p)
            val mode: dynamic = Adapt.mode(p)
            if (truth((!truth(active.length)))) {
                return null
            }
            val names: dynamic = zones(p).map((fun(z: dynamic): dynamic {
                return jsOr(Adapt.zones[z]) { z }
            }))
            if (truth((!truth(mode.auto)))) {
                return jsObject { obj43 ->
                    obj43["auto"] = false
                    obj43["zones"] = names
                    obj43["groups"] = js("[]")
                    obj43["text"] = mode.reason
                }
            }
            val pool: dynamic = E.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd((e.variant === "base")) { (e.category === "Fuerza") }) { Adapt.allowed(e, p) }
            }))
            val groups: dynamic = priorityGroups(p, pool).slice(0, 5)
            return jsObject { obj44 ->
                obj44["auto"] = true
                obj44["zones"] = names
                obj44["groups"] = groups
                obj44["text"] = (if (truth(groups.length)) ("Zona declarada: " + textOf(names.join(", ")) + ". Se priorizan grupos compatibles distintos: " + textOf(groups.join(", ")) + ".") else ("Zona declarada: " + textOf(names.join(", ")) + ". No hay suficientes grupos de fuerza compatibles; el sistema buscará movilidad, cardio o descanso."))
            }
        }
        E = root.Engine
        Adapt = root.Adapt
        val Schedule: dynamic = root.Schedule
        previousItems = Schedule.items
        avoid = jsonValue("{\"neck\":[\"Hombros\",\"Espalda\"],\"shoulder\":[\"Hombros\",\"Pecho\",\"Espalda\",\"Brazos\"],\"elbow\":[\"Pecho\",\"Espalda\",\"Brazos\",\"Hombros\"],\"wrist\":[\"Pecho\",\"Espalda\",\"Brazos\",\"Hombros\"],\"back\":[\"Espalda\",\"Core\",\"Cadera\"],\"hip\":[\"Cadera\",\"Piernas\"],\"knee\":[\"Piernas\",\"Cadera\"],\"ankle\":[\"Piernas\",\"Cadera\"],\"other\":[]}")
        prefer = jsonValue("{\"neck\":[\"Piernas\",\"Cadera\",\"Core\"],\"shoulder\":[\"Piernas\",\"Cadera\",\"Core\"],\"elbow\":[\"Piernas\",\"Cadera\",\"Core\"],\"wrist\":[\"Piernas\",\"Cadera\",\"Core\"],\"back\":[\"Pecho\",\"Brazos\",\"Hombros\",\"Piernas\"],\"hip\":[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\",\"Core\"],\"knee\":[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\",\"Core\"],\"ankle\":[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\",\"Core\"],\"other\":[]}")
        zones = (fun(p: dynamic): dynamic {
            return jsArray { array3 ->
                appendAll(array3, jsConstruct(global_Set, arrayOf<dynamic>(Adapt.active(p).map((fun(x: dynamic): dynamic {
                    return x.zone
                })))))
            }
        })
        Schedule.items = items
        root.InjuryAwareV93 = jsObject { obj49 ->
            obj49["items"] = items
            obj49["summary"] = summary
            obj49["priorityGroups"] = priorityGroups
            obj49["zones"] = zones
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
