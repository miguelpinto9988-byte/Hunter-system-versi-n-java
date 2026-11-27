@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installgoalcontextv24

import hunter.browser.*

@JsName("String") private external var global_String: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installGoalContextV24() {
    dynamicValue((fun(root: dynamic): dynamic {
        var clean: dynamic = absent
        var oldAdjusted: dynamic = absent
        var personal: dynamic = absent
        var interpret: dynamic = absent
        var adjusted: dynamic = absent
        var score: dynamic = absent
        personal = fun(): dynamic {
            return (if (truth((js("typeof state") === "undefined"))) js("[]") else dynamicValue(jsOr(global_state.goals) { js("[]") }).filter((fun(g: dynamic): dynamic {
                return jsOr((g.repeat === "daily")) { (!truth(onPresent(g.dates) { value6 -> value6.length })) }
            })).map((fun(g: dynamic): dynamic {
                return g.title
            })))
        }
        interpret = fun(arg9: dynamic, arg10: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg9) { js("({})") }
            var titles: dynamic = absent
            titles = definedOr(arg10) { personal() }
            val chosen: dynamic = global_GoalGroups14.of(p)
            val text: dynamic = dynamicValue(jsArray { array12 ->
                array12.push(jsOr(p.finalGoal) { "" })
                appendAll(array12, titles)
            }).join(". ")
            val r: dynamic = global_GoalGroups14.interpret(text, chosen)
            val plain: dynamic = clean(text)
            val positive: dynamic = plain.replace(jsRegExp("(?:no quiero|no deseo|no busco|evitar)\\s+[^,.;]+", "g"), "")
            val priorities: dynamic = jsArray { array13 ->
                array13.push(chosen)
                appendAll(array13, r.alternatives.filter((fun(g: dynamic): dynamic {
                    return (g !== chosen)
                })))
            }
            val weights: dynamic = global_Object.fromEntries(priorities.map((fun(g: dynamic, i: dynamic): dynamic {
                return arrayOf<dynamic>(g, (if (truth((i === 0))) 2 else 1))
            })))
            val focus: dynamic = (if (truth(dynamicValue(jsRegExp("piernas?|gluteos?|tren inferior", "")).test(positive))) "lower" else (if (truth(dynamicValue(jsRegExp("brazos?|pecho|hombros?|tren superior", "")).test(positive))) "upper" else (if (truth(dynamicValue(jsRegExp("abdomen|zona media|core", "")).test(positive))) "core" else jsOr(p.focus) { "full" })))
            val inferred: dynamic = js("({})")
            if (truth(r.noImpact)) {
                inferred.noImpact = true
            }
            val constraints: dynamic = jsObject { obj16 ->
                obj16["noFloor"] = jsRegExp("sin suelo|no (?:quiero |puedo )?(?:tumbarme|arrodillarme)|evitar (?:el )?suelo", "")
                obj16["noOverhead"] = jsRegExp("sin (?:elevar|levantar)[^.;]*cabeza|(?:no|evitar) (?:quiero |puedo )?(?:levantar|elevar|hacer press)[^.;]*sobre la cabeza", "")
                obj16["noLoad"] = jsRegExp("sin (?:peso|pesas|carga)|no (?:quiero |puedo )?usar (?:peso|pesas)", "")
                obj16["noWrist"] = jsRegExp("sin apoyo[^.;]*manos|no apoyar[^.;]*manos", "")
                obj16["noKnee"] = jsRegExp("sin flexion[^.;]*rodilla|no doblar[^.;]*rodilla", "")
                obj16["seatedOnly"] = jsRegExp("solo sentado|unicamente sentado", "")
            }
            if (true) {
                val iterator17: dynamic = iteratorOf(global_Object.entries(constraints))
                while (true) {
                    val next18: dynamic = iterator17.next()
                    if (truth(next18.done)) break
                    var key: dynamic = absent
                    var re: dynamic = absent
                    val unpacked19: dynamic = fromIterable(next18.value)
                    key = unpacked19[0]
                    re = unpacked19[1]
                    if (truth(jsAnd(re.test(plain)) { (!truth(jsAnd((key === "noLoad")) { dynamicValue(jsRegExp("con y sin (?:peso|carga)", "")).test(plain) })) })) {
                        inferred[key] = true
                    }
                }
            }
            val specific: dynamic = (if (truth(jsAnd((r.group === chosen)) { r.target })) r.target else jsOr(p.finalTarget) { global_GoalGroups14.groups[chosen].target })
            return jsObject { obj22 ->
                obj22["chosen"] = chosen
                obj22["priorities"] = priorities
                obj22["weights"] = weights
                obj22["focus"] = focus
                obj22["target"] = specific
                obj22["inferred"] = inferred
                obj22["medical"] = r.medical
                obj22["text"] = text
                obj22["personalTitles"] = titles
                obj22["reason"] = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("Prioridad: ", global_GoalGroups14.groups[chosen].name), (if (truth(jsMore(priorities.length, 1))) jsAdd(". Complemento: ", priorities.slice(1).map((fun(g: dynamic): dynamic {
                    return global_GoalGroups14.groups[g].name
                })).join(", ")) else "")), ". "), (if (truth((focus !== "full"))) jsAdd(jsAdd("Enfoque: ", jsOr(jsonValue("{\"upper\":\"tren superior\",\"lower\":\"piernas y glúteos\",\"core\":\"zona media\"}")[focus]) { focus }), ". ") else "")), "Equipo, nivel, días, restricciones y registros ajustan la selección y su dosis.")
            }
        }
        adjusted = fun(p: dynamic): dynamic {
            if (truth((!truth(p)))) {
                return p
            }
            val i: dynamic = interpret(p)
            val base: dynamic = oldAdjusted(jsObject { obj25 ->
                copyFields(obj25, p)
                obj25["finalTarget"] = i.target
            })
            return jsObject { obj26 ->
                copyFields(obj26, base)
                obj26["focus"] = i.focus
                obj26["goalIntent24"] = i
                obj26["restrictions"] = jsObject { obj27 ->
                    copyFields(obj27, base.restrictions)
                    copyFields(obj27, i.inferred)
                }
            }
        }
        score = fun(e: dynamic, p: dynamic, family: dynamic): dynamic {
            val i: dynamic = jsOr(p.goalIntent24) { interpret(p) }
            val f: dynamic = clean(dynamicValue(arrayOf<dynamic>(e.name, e.family, e.group)).join(" "))
            var n: dynamic = 0
            if (true) {
                val iterator30: dynamic = iteratorOf(global_Object.entries(i.weights))
                while (true) {
                    val next31: dynamic = iterator30.next()
                    if (truth(next31.done)) break
                    var g: dynamic = absent
                    var w: dynamic = absent
                    val unpacked32: dynamic = fromIterable(next31.value)
                    g = unpacked32[0]
                    w = unpacked32[1]
                    if (truth(jsAnd((g === "build")) { (e.category === "Fuerza") })) {
                        run {
                            n = jsAdd(n, w)
                        }
                    }
                    if (truth(jsAnd((g === "fitness")) { (e.category === "Cardio") })) {
                        run {
                            n = jsAdd(n, w)
                        }
                    }
                    if (truth(jsAnd((g === "movement")) { (e.category === "Movilidad") })) {
                        run {
                            n = jsAdd(n, w)
                        }
                    }
                    if (truth(jsAnd((g === "athletic")) { dynamicValue(jsRegExp("agility|power|salt|salto|skater|shuffle|rapido|agilidad|potencia|pliomet", "")).test(f) })) {
                        run {
                            n = jsAdd(n, jsMultiply(w, 2))
                        }
                    }
                }
            }
            if (truth(jsAnd((i.focus === "lower")) { dynamicValue(jsonValue("[\"Piernas\",\"Cadera\"]")).includes(e.group) })) {
                run {
                    n = jsAdd(n, 3)
                }
            }
            if (truth(jsAnd((i.focus === "upper")) { dynamicValue(jsonValue("[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"]")).includes(e.group) })) {
                run {
                    n = jsAdd(n, 3)
                }
            }
            if (truth(jsAnd((i.focus === "core")) { dynamicValue(jsonValue("[\"Abdomen\",\"Core\"]")).includes(e.group) })) {
                run {
                    n = jsAdd(n, 3)
                }
            }
            return n
        }
        clean = (fun(s: dynamic): dynamic {
            return global_String(jsOr(s) { "" }).toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "")
        })
        oldAdjusted = global_GoalPlans.adjusted
        global_GoalPlans.adjusted = adjusted
        root.GoalContext24 = jsObject { obj37 ->
            obj37["interpret"] = interpret
            obj37["adjusted"] = adjusted
            obj37["score"] = score
            obj37["personal"] = personal
            obj37["clean"] = clean
        }
        return absent
    }))(global_window)
}
