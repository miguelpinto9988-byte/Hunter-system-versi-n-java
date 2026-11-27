@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installgoalsv14

import hunter.browser.*

@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installGoalsV14() {
    dynamicValue((fun(root: dynamic): dynamic {
        var groups: dynamic = absent
        var clean: dynamic = absent
        var of: dynamic = absent
        var interpret: dynamic = absent
        var plan: dynamic = absent
        of = fun(arg9: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg9) { js("({})") }
            if (truth(groups[p.goalGroup])) {
                return p.goalGroup
            }
            return jsOr(global_Object.keys(groups).find((fun(k: dynamic): dynamic {
                return groups[k].legacy.includes(jsOr(p.finalTarget) { p.goal })
            }))) { "habit" }
        }
        interpret = fun(text: dynamic, chosen: dynamic): dynamic {
            val raw: dynamic = clean(text)
            val healthText: dynamic = raw.replace(jsRegExp("\\b(?:no tengo|sin)\\s+(?:ningun[ao]?\\s+)?(?:lesion(?:es)?|dolor)[^,.;]*", "g"), "")
            val medical: dynamic = dynamicValue(jsRegExp("lesion|hernia|fractura|cirug|dolor|duele|molestia|rehabil", "")).test(healthText)
            val noImpact: dynamic = dynamicValue(jsRegExp("sin salt|sin impacto|evitar salt|no (?:quiero |puedo )?salt", "")).test(raw)
            val positive: dynamic = raw.replace(jsRegExp("(?:no quiero|no deseo|no busco|evitar)\\s+[^,.;]+", "g"), "")
            val lexicon: dynamic = jsObject { obj12 ->
                obj12["build"] = arrayOf<dynamic>(jsRegExp("fuerza|fuerte|muscul|hipertrof|esculp|mancuerna|pesas|biceps|gluteo|abdomen|pecho", "g"))
                obj12["fitness"] = arrayOf<dynamic>(jsRegExp("resist|cardio|grasa|adelgaz|peso|aguantar|cansarme menos", "g"))
                obj12["athletic"] = arrayOf<dynamic>(jsRegExp("agilidad|agil|saltos?|potencia|explosiv|velocidad|deport|coordinacion", "g"))
                obj12["movement"] = arrayOf<dynamic>(jsRegExp("movilidad|equilibrio|flexibilidad|postur|control motor|estirar", "g"))
                obj12["habit"] = arrayOf<dynamic>(jsRegExp("constan|habito|rutina estable|bienestar|salud|regularidad|diari|breve", "g"))
                obj12["adapted"] = arrayOf<dynamic>(jsRegExp("retomar|volver|recuperacion autorizada|sentado|recuperar habito", "g"))
            }
            val scores: dynamic = global_Object.entries(lexicon).map((fun(arg25: dynamic): dynamic {
                var group: dynamic = absent
                var patterns: dynamic = absent
                val unpacked26: dynamic = fromIterable(arg25)
                group = unpacked26[0]
                patterns = unpacked26[1]
                return jsObject { obj27 ->
                    obj27["group"] = group
                    obj27["score"] = patterns.reduce((fun(n: dynamic, regex: dynamic): dynamic {
                        return jsAdd(n, dynamicValue(jsArray { array28 ->
                            appendAll(array28, positive.matchAll(regex))
                        }).length)
                    }), 0)
                }
            })).filter((fun(x: dynamic): dynamic {
                return x.score
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract(b.score, a.score)
            }))
            val ambiguous: dynamic = jsAnd(jsMore(scores.length, 1)) { (scores[0].score === scores[1].score) }
            val primary: dynamic = (if (truth(ambiguous)) (if (truth(scores.some((fun(x: dynamic): dynamic {
                return (x.group === chosen)
            })))) chosen else null) else jsOr(onPresent(scores[0]) { value29 -> value29.group }) { null })
            var target: dynamic = (if (truth(primary)) groups[primary].target else null)
            var focus: dynamic = "full"
            if (truth((primary === "build"))) {
                if (truth(jsAnd(dynamicValue(jsRegExp("fuerza|fuerte", "")).test(positive)) { (!truth(dynamicValue(jsRegExp("muscul|hipertrof|esculp", "")).test(positive))) })) {
                    target = "strength"
                }
                if (truth(dynamicValue(jsRegExp("braz|pecho|hombro|tren superior", "")).test(positive))) {
                    focus = "upper"
                    target = "upper"
                } else {
                    if (truth(dynamicValue(jsRegExp("pierna|gluteo|tren inferior", "")).test(positive))) {
                        focus = "lower"
                        target = "lower"
                    } else {
                        if (truth(dynamicValue(jsRegExp("abdomen|core|zona media", "")).test(positive))) {
                            focus = "core"
                            target = "core"
                        }
                    }
                }
            }
            if (truth(jsAnd((primary === "fitness")) { dynamicValue(jsRegExp("grasa|adelgaz|bajar peso", "")).test(positive) })) {
                target = "activity"
            }
            if (truth((primary === "movement"))) {
                if (truth(dynamicValue(jsRegExp("equilibr", "")).test(positive))) {
                    target = "balance"
                } else {
                    if (truth(dynamicValue(jsRegExp("flexibil|estirar", "")).test(positive))) {
                        target = "flexibility"
                    } else {
                        if (truth(dynamicValue(jsRegExp("postur", "")).test(positive))) {
                            target = "posture"
                        }
                    }
                }
            }
            if (truth(jsAnd((primary === "habit")) { noImpact })) {
                target = "lowimpact"
            }
            if (truth(jsAnd((primary === "adapted")) { dynamicValue(jsRegExp("sentado", "")).test(positive) })) {
                target = "seated"
            }
            return jsObject { obj30 ->
                obj30["group"] = primary
                obj30["target"] = target
                obj30["focus"] = focus
                obj30["medical"] = medical
                obj30["noImpact"] = noImpact
                obj30["alternatives"] = scores.map((fun(x: dynamic): dynamic {
                    return x.group
                }))
                obj30["ambiguous"] = ambiguous
                obj30["reason"] = (if (truth(primary)) groups[primary].why else "No distingo una prioridad única. Elige una de las seis familias y revisa el plan propuesto.")
                obj30["measure"] = (if (truth(primary)) groups[primary].measure else "")
            }
        }
        plan = fun(p: dynamic): dynamic {
            val key: dynamic = of(p)
            val group: dynamic = groups[key]
            val specific: dynamic = jsOr(global_GoalPlans.plans[p.finalTarget]) { global_GoalPlans.plans[group.target] }
            val mode: dynamic = global_Adapt.mode(global_GoalPlans.adjusted(p))
            return jsObject { obj33 ->
                obj33["group"] = key
                obj33["name"] = group.name
                obj33["specific"] = specific.name
                obj33["reason"] = specific.why
                obj33["measure"] = group.measure
                obj33["days"] = global_Schedule.defaults(p).filter((fun(x: dynamic): dynamic {
                    return (x !== "rest")
                })).length
                obj33["mode"] = (if (truth((p.trainingMode === "routines"))) "Rutinas completas" else "Desafíos diarios")
                obj33["level"] = jsOr(jsonValue("{\"basic\":\"Básico\",\"intermediate\":\"Intermedio\",\"advanced\":\"Avanzado\"}")[p.difficulty]) { "Básico" }
                obj33["compatible"] = mode.auto
                obj33["reduced"] = jsOr(mode.reduced) { (!truth((!truth(specific.gentle)))) }
                obj33["healthReason"] = mode.reason
            }
        }
        global_GoalPlans.plans.athletic = jsonValue("{\"name\":\"Agilidad, saltos y potencia\",\"base\":\"endurance\",\"pattern\":[\"cardio\",\"strength\",\"cardio\"],\"why\":\"Alterna precisión de pasos, saltos de pocas repeticiones y fuerza complementaria. Descansa entre bloques de potencia.\"}")
        global_Adapt.goals.athletic = global_GoalPlans.plans.athletic.name
        groups = jsonValue("{\"build\":{\"name\":\"Fuerza y masa muscular\",\"target\":\"muscle\",\"legacy\":[\"strength\",\"muscle\",\"upper\",\"lower\",\"core\",\"dumbbells\"],\"families\":[\"strength\",\"hypertrophy\",\"core\",\"mixed\"],\"why\":\"Trabaja fuerza y musculatura con peso corporal o mancuernas. El texto puede dar prioridad a fuerza, zona media, brazos o piernas.\",\"measure\":\"Registra repeticiones, carga y esfuerzo en los mismos movimientos.\"},\"fitness\":{\"name\":\"Resistencia y condición física\",\"target\":\"endurance\",\"legacy\":[\"endurance\",\"activity\"],\"families\":[\"cardio\",\"mixed\",\"strength\"],\"why\":\"Combina intervalos de cardio y fuerza complementaria. La actividad puede acompañar el control de peso.\",\"measure\":\"Registra bloques completados y esfuerzo; compara sesiones equivalentes.\"},\"athletic\":{\"name\":\"Agilidad, saltos y potencia\",\"target\":\"athletic\",\"legacy\":[\"athletic\",\"coordination\"],\"families\":[\"agility\",\"power\",\"mixed\",\"strength\"],\"why\":\"Practica pasos precisos, cambios de apoyo, saltos controlados y fuerza. Las restricciones de impacto sustituyen los saltos.\",\"measure\":\"Anota equilibrio, aterrizaje y calidad además de cantidad.\"},\"movement\":{\"name\":\"Movilidad, equilibrio y control\",\"target\":\"mobility\",\"legacy\":[\"mobility\",\"balance\",\"flexibility\",\"posture\"],\"families\":[\"mobility\",\"balance\",\"core\"],\"why\":\"Combina movilidad cómoda, estabilidad y control del movimiento. Más recorrido solo cuando se conserva el control.\",\"measure\":\"Registra comodidad y estabilidad; no fuerces el recorrido.\"},\"habit\":{\"name\":\"Constancia y bienestar\",\"target\":\"general\",\"legacy\":[\"general\",\"functional\",\"short\",\"lowimpact\"],\"families\":[\"mixed\",\"strength\",\"cardio\",\"mobility\"],\"why\":\"Alterna sesiones completas y descanso según tus días. Incluye opciones breves y sin saltos.\",\"measure\":\"Mide semanas cumplidas y continuidad, junto con el esfuerzo.\"},\"adapted\":{\"name\":\"Actividad adaptada y retorno\",\"target\":\"returning\",\"legacy\":[\"returning\",\"seated\",\"comfortable\"],\"families\":[\"mobility\",\"balance\",\"strength\",\"cardio\"],\"why\":\"Busca movimientos compatibles y volumen reducido según restricciones e indicaciones. No asigna un tratamiento por el nombre de la lesión.\",\"measure\":\"Registra síntomas, tolerancia y las indicaciones recibidas.\"}}")
        clean = (fun(s: dynamic): dynamic {
            return global_String(jsOr(s) { "" }).toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "")
        })
        root.GoalGroups14 = jsObject { obj36 ->
            obj36["groups"] = groups
            obj36["of"] = of
            obj36["interpret"] = interpret
            obj36["plan"] = plan
        }
        return absent
    }))(global_window)
}
