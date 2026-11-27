@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installadapt

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installAdapt() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var C: dynamic = absent
        var goals: dynamic = absent
        var zones: dynamic = absent
        var restrictions: dynamic = absent
        var active: dynamic = absent
        var mode: dynamic = absent
        var reasons: dynamic = absent
        var allowed: dynamic = absent
        var normalize: dynamic = absent
        var trainingItems: dynamic = absent
        var routineCompatible: dynamic = absent
        active = fun(p: dynamic): dynamic {
            return dynamicValue(jsOr(onPresent(p) { value18 -> value18.injuries }) { js("[]") }).filter((fun(i: dynamic): dynamic {
                return (i.stage !== "past")
            }))
        }
        mode = fun(p: dynamic): dynamic {
            if (truth((!truth(p)))) {
                return jsonValue("{\"auto\":false,\"reason\":\"Completa tu perfil.\"}")
            }
            if (truth(jsLess(p.age, 18))) {
                return jsonValue("{\"auto\":false,\"reason\":\"Organizador disponible. Para menores, utiliza un plan indicado por un profesional.\"}")
            }
            val h: dynamic = jsOr(p.health) { js("({})") }
            val a: dynamic = active(p)
            if (truth(jsAnd(p.limits) { (!truth(p.health)) })) {
                return jsonValue("{\"auto\":false,\"reason\":\"Actualiza las zonas y restricciones de tu perfil para revisar la selección.\"}")
            }
            if (truth(jsOr(jsOr((h.pain === "strong")) { (h.pain === "moderate") }) { h.acute })) {
                return jsonValue("{\"auto\":false,\"reason\":\"Dolor actual o lesión reciente: conserva tus misiones personales y revisa con un profesional antes de generar ejercicios.\"}")
            }
            if (truth(jsOr(a.some((fun(x: dynamic): dynamic {
                return dynamicValue(jsonValue("[\"bone\",\"surgery\"]")).includes(x.type)
            }))) { a.some((fun(x: dynamic): dynamic {
                return (x.zone === "other")
            })) })) {
                return jsonValue("{\"auto\":false,\"reason\":\"Este caso requiere indicaciones individuales. Puedes organizar aquí el plan que recibas.\"}")
            }
            if (truth(jsAnd(jsOr(a.length) { h.special }) { jsOr((!truth(h.approved))) { (!truth(h.translated)) } })) {
                return jsonValue("{\"auto\":false,\"reason\":\"Completa las recomendaciones recibidas y confirma qué actividad te han permitido antes de filtrar ejercicios.\"}")
            }
            return jsObject { obj21 ->
                obj21["auto"] = true
                obj21["reduced"] = jsOr(jsOr(jsOr(jsMore(a.length, 0)) { (!truth((!truth(h.special)))) }) { jsAtLeast(p.age, 65) }) { (h.pain === "mild") }
                obj21["reason"] = (if (truth(jsOr(a.length) { h.special })) "Selección reducida: se excluyen zonas y movimientos restringidos. No es un programa de rehabilitación." else "Selección según objetivo, experiencia, equipo y calendario.")
            }
        }
        reasons = fun(e: dynamic, p: dynamic, arg24: dynamic): dynamic {
            var strict: dynamic = absent
            strict = definedOr(arg24) { true }
            if (truth((!truth(p)))) {
                return jsonValue("[\"Completa el perfil\"]")
            }
            val list: dynamic = js("[]")
            val m: dynamic = mode(p)
            val r: dynamic = jsOr(p.restrictions) { js("({})") }
            if (truth(jsAnd(strict) { (!truth(m.auto)) })) {
                list.push("Pendiente de indicaciones individuales")
            }
            if (true) {
                val iterator25: dynamic = iteratorOf(jsOr(e.requirements) { js("[]") })
                while (true) {
                    val next26: dynamic = iterator25.next()
                    if (truth(next26.done)) break
                    var req: dynamic = absent
                    req = next26.value
                    if (truth((if (truth((req === "db"))) (p.equipment !== "db") else (!truth(p[req]))))) {
                        list.push(jsAdd("Falta ", C.equipment[req]))
                    }
                }
            }
            val affected: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(active(p).map((fun(i: dynamic): dynamic {
                return i.zone
            }))))
            if (truth(dynamicValue(jsOr(e.zones) { js("[]") }).some((fun(z: dynamic): dynamic {
                return affected.has(z)
            })))) {
                list.push(jsAdd("Zona declarada: ", dynamicValue(jsArray { array34 ->
                    appendAll(array34, affected)
                }).filter((fun(z: dynamic): dynamic {
                    return e.zones.includes(z)
                })).map((fun(z: dynamic): dynamic {
                    return zones[z]
                })).join(", ")))
            }
            if (truth(jsAnd(jsOr(r.noImpact) { m.reduced }) { (e.impact === "Con saltos") })) {
                list.push("Saltos excluidos")
            }
            if (truth(jsAnd(r.noFloor) { e.floor })) {
                list.push("Trabajo en suelo excluido")
            }
            if (truth(jsAnd(r.noOverhead) { e.overhead })) {
                list.push("Elevación sobre la cabeza excluida")
            }
            if (truth(jsAnd(r.noKnee) { e.kneeBend })) {
                list.push("Flexión de rodilla excluida")
            }
            if (truth(jsAnd(r.noWrist) { e.wristLoad })) {
                list.push("Apoyo sobre manos excluido")
            }
            if (truth(jsAnd(r.noLoad) { e.requirements.includes("db") })) {
                list.push("Carga externa excluida")
            }
            if (truth(jsAnd(r.seatedOnly) { (!truth(e.seated)) })) {
                list.push("Solo movimientos sentado")
            }
            if (truth(jsAnd(r.noSpine) { e.spineMove })) {
                list.push("Flexión o giro de tronco excluido")
            }
            if (truth(jsMore(e.level, (if (truth(m.reduced)) 0 else (if (truth((p.experience === "regular"))) 2 else 1))))) {
                list.push("Nivel superior al seleccionado")
            }
            return list
        }
        allowed = fun(e: dynamic, p: dynamic): dynamic {
            return (!truth(reasons(e, p).length))
        }
        normalize = fun(p: dynamic): dynamic {
            return jsObject { obj39 ->
                copyFields(obj39, p)
                obj39["goal"] = (if (truth(goals[p.goal])) p.goal else "general")
                obj39["minutes"] = (if (truth(dynamicValue(jsonValue("[10,20,30,45]")).includes(p.minutes))) p.minutes else 20)
                obj39["injuries"] = (if (truth(global_Array.isArray(p.injuries))) p.injuries else js("[]"))
                obj39["restrictions"] = jsOr(p.restrictions) { js("({})") }
            }
        }
        trainingItems = fun(p: dynamic, category: dynamic, rotation: dynamic, reps: dynamic, sets: dynamic): dynamic {
            val m: dynamic = mode(p)
            val pool: dynamic = E.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd((e.variant === "base")) { (!truth(e.sourceOnly)) }) { allowed(e, p) }
            }))
            val `get`: dynamic = (fun(group: dynamic, preferred: dynamic): dynamic {
                val options: dynamic = pool.filter((fun(e: dynamic): dynamic {
                    return group.includes(e.group)
                }))
                val preferredFound: dynamic = preferred.map((fun(id: dynamic): dynamic {
                    return options.find((fun(e: dynamic): dynamic {
                        return (e.id === id)
                    }))
                })).filter(global_Boolean)
                return jsOr(preferredFound[jsMod(rotation, global_Math.max(preferredFound.length, 1))]) { options[jsMod(rotation, global_Math.max(options.length, 1))] }
            })
            var chosen: dynamic = js("[]")
            if (truth((category === "cardio"))) {
                val options: dynamic = pool.filter((fun(e: dynamic): dynamic {
                    return (e.category === "Cardio")
                }))
                chosen = global_Array.from(jsObject { obj42 ->
                    obj42["length"] = global_Math.min((if (truth((p.minutes === 10))) 2 else 4), options.length)
                }, (fun(_: dynamic, i: dynamic): dynamic {
                    return options[jsMod(jsAdd(rotation, i), options.length)]
                }))
            } else {
                if (truth((category === "recovery"))) {
                    val preferred: dynamic = jsonValue("[\"shoulder\",\"ankle\",\"wrist\",\"hipcircle\",\"elbowmob\",\"forearmmob\",\"breathing\"]")
                    val options: dynamic = pool.filter((fun(e: dynamic): dynamic {
                        return (e.category === "Movilidad")
                    }))
                    chosen = preferred.map((fun(id: dynamic): dynamic {
                        return options.find((fun(e: dynamic): dynamic {
                            return (e.id === id)
                        }))
                    })).filter(global_Boolean).slice(0, 3)
                    if (truth(jsLess(chosen.length, 2))) {
                        chosen = options.slice(0, 3)
                    }
                } else {
                    val lists: dynamic = arrayOf<dynamic>(`get`(jsonValue("[\"Piernas\"]"), (if (truth((p.equipment === "db"))) jsonValue("[\"goblet\",\"squat\",\"chair\"]") else jsonValue("[\"squat\",\"chair\"]"))), `get`(jsonValue("[\"Pecho\"]"), (if (truth((p.equipment === "db"))) jsonValue("[\"press\",\"neutral\"]") else jsonValue("[\"wall\",\"kneepush\"]"))), `get`(jsonValue("[\"Espalda\"]"), (if (truth((p.equipment === "db"))) jsonValue("[\"row\",\"prone\"]") else jsonValue("[\"prone\",\"scapular\"]"))), `get`(jsonValue("[\"Cadera\"]"), (if (truth((p.equipment === "db"))) jsonValue("[\"rdl\",\"bridge\"]") else jsonValue("[\"bridge\",\"hinge\"]"))), `get`(jsonValue("[\"Core\",\"Abdomen\",\"Brazos\"]"), jsonValue("[\"deadbug\",\"heel\",\"seatedcurl\",\"curl\"]")))
                    chosen = jsArray { array43 ->
                        appendAll(array43, jsConstruct(global_Map, arrayOf<dynamic>(lists.filter(global_Boolean).map((fun(e: dynamic): dynamic {
                            return arrayOf<dynamic>(e.id, e)
                        })))).values())
                    }
                    if (truth((!truth(chosen.length)))) {
                        chosen = pool.filter((fun(e: dynamic): dynamic {
                            return (e.category === "Fuerza")
                        })).slice(0, 3)
                    }
                    if (truth((p.minutes === 10))) {
                        chosen = chosen.slice(0, 3)
                    }
                }
            }
            return chosen.map((fun(e: dynamic): dynamic {
                return jsObject { obj44 ->
                    obj44["id"] = e.id
                    obj44["sets"] = (if (truth((category === "recovery"))) 1 else (if (truth(m.reduced)) 1 else (if (truth((category === "cardio"))) (if (truth(jsAtLeast(p.minutes, 30))) 3 else 2) else sets)))
                    obj44["target"] = (if (truth((e.unit === "sec"))) (if (truth((category === "cardio"))) (if (truth(m.reduced)) 20 else (if (truth(jsAtLeast(p.minutes, 30))) 45 else 30)) else 20) else (if (truth(m.reduced)) 6 else (if (truth((category === "recovery"))) 8 else reps)))
                    obj44["rest"] = (if (truth((category === "cardio"))) 40 else (if (truth((category === "recovery"))) 20 else 90))
                }
            }))
        }
        routineCompatible = fun(r: dynamic, p: dynamic): dynamic {
            return jsAnd(mode(p).auto) { r.items.every((fun(x: dynamic): dynamic {
                return allowed(E.byId(x.id), p)
            })) }
        }
        E = root.Engine
        C = root.Catalog
        val originalCycle: dynamic = E.makeCycle
        goals = jsonValue("{\"general\":\"Condición física general\",\"strength\":\"Ganar fuerza\",\"muscle\":\"Desarrollar masa muscular\",\"endurance\":\"Mejorar resistencia y cardio\",\"activity\":\"Aumentar actividad y acompañar control de peso\",\"mobility\":\"Mejorar movilidad y constancia\"}")
        zones = jsonValue("{\"neck\":\"Cuello\",\"shoulder\":\"Hombro\",\"elbow\":\"Codo\",\"wrist\":\"Muñeca o mano\",\"back\":\"Espalda\",\"hip\":\"Cadera\",\"knee\":\"Rodilla\",\"ankle\":\"Tobillo o pie\",\"other\":\"Otra zona / no sé\"}")
        val types: dynamic = jsonValue("{\"muscle\":\"Muscular\",\"tendon\":\"Tendón\",\"joint\":\"Articulación o ligamento\",\"bone\":\"Fractura / lesión ósea\",\"surgery\":\"Después de una cirugía\",\"unknown\":\"Sin diagnóstico / otra\"}")
        restrictions = jsonValue("{\"noImpact\":\"Sin saltos ni impactos\",\"noFloor\":\"Sin ejercicios en el suelo\",\"noOverhead\":\"Sin brazos sobre la cabeza\",\"noKnee\":\"Sin flexión repetida de rodilla\",\"noWrist\":\"Sin apoyo de peso en las manos\",\"noLoad\":\"Sin mancuernas ni carga externa\",\"seatedOnly\":\"Solo ejercicios sentado\",\"noSpine\":\"Sin flexiones ni giros del tronco\"}")
        if (true) {
            val iterator3: dynamic = iteratorOf(E.exercises)
            while (true) {
                val next4: dynamic = iterator3.next()
                if (truth(next4.done)) break
                var e: dynamic = absent
                e = next4.value
                if (truth((e.baseId === "breathing"))) {
                    e.seated = true
                }
                if (truth(jsAnd((!truth(e.seated))) { (!truth(e.floor)) })) {
                    e.zones = jsArray { array9 ->
                        appendAll(array9, jsConstruct(global_Set, arrayOf<dynamic>(jsArray { array10 ->
                            appendAll(array10, e.zones)
                            array10.push("hip")
                            array10.push("knee")
                            array10.push("ankle")
                        })))
                    }
                }
                if (truth(e.floor)) {
                    e.zones = jsArray { array13 ->
                        appendAll(array13, jsConstruct(global_Set, arrayOf<dynamic>(jsArray { array14 ->
                            appendAll(array14, e.zones)
                            array14.push("shoulder")
                        })))
                    }
                }
            }
        }
        E.eligible = (fun(p: dynamic): dynamic {
            return jsAnd(jsAnd((!truth((!truth(p))))) { jsAtLeast(p.age, 13) }) { jsAtMost(p.age, 100) }
        })
        E.makeCycle = (fun(profile: dynamic, start: dynamic, index: dynamic, previous: dynamic, logs: dynamic): dynamic {
            val p: dynamic = normalize(profile)
            val m: dynamic = mode(p)
            val base: dynamic = originalCycle(jsObject { obj51 ->
                copyFields(obj51, p)
                obj51["limits"] = false
            }, start, index, previous, logs)
            if (truth((!truth(previous)))) {
                base.reps = (if (truth((p.goal === "muscle"))) 10 else 8)
                base.sets = (if (truth(jsAnd((p.experience === "regular")) { jsAtLeast(p.minutes, 30) })) 3 else 2)
            }
            if (truth(m.reduced)) {
                base.reps = 6
                base.sets = 1
            }
            val schedule: dynamic = (if (truth((p.frequency === 2))) jsonValue("[0,3]") else jsonValue("[0,2,4]"))
            base.days = global_Array.from(jsonValue("{\"length\":21}"), (fun(_: dynamic, i: dynamic): dynamic {
                var kind: dynamic = (if (truth((jsMod(i, 7) === 6))) "rest" else (if (truth(schedule.includes(jsMod(i, 7)))) (if (truth((p.goal === "endurance"))) "cardio" else (if (truth((p.goal === "mobility"))) "recovery" else "strength")) else (if (truth(jsAnd((p.goal === "activity")) { (jsMod(i, 7) === 5) })) "cardio" else "recovery")))
                if (truth((!truth(m.auto)))) {
                    kind = "rest"
                }
                val items: dynamic = (if (truth((kind === "rest"))) js("[]") else trainingItems(p, kind, jsAdd(jsAdd(index, global_Math.floor(jsDivide(i, 7))), i), base.reps, base.sets))
                var tasks: dynamic = js("[]")
                if (truth((!truth(m.auto)))) {
                    tasks = jsonValue("[{\"id\":\"review\",\"text\":\"Revisar mis recomendaciones y restricciones\",\"done\":false},{\"id\":\"notes\",\"text\":\"Registrar cómo me siento hoy\",\"done\":false}]")
                } else {
                    if (truth((!truth(items.length)))) {
                        tasks = arrayOf<dynamic>(jsObject { obj62 ->
                            obj62["id"] = "rest"
                            obj62["text"] = (if (truth((kind === "rest"))) "Completar mi día de recuperación" else "Revisar alternativas con mi profesional")
                            obj62["done"] = false
                        })
                    }
                }
                return jsObject { obj63 ->
                    obj63["date"] = E["plus"](start, i)
                    obj63["kind"] = (if (truth((!truth(items.length)))) "rest" else kind)
                    obj63["items"] = items
                    obj63["tasks"] = tasks
                    obj63["title"] = (if (truth((!truth(m.auto)))) "Seguimiento personal" else (if (truth((kind === "rest"))) "Recuperación" else goals[p.goal]))
                    obj63["adaptation"] = (if (truth((!truth(m.auto)))) "organizer" else (if (truth(m.reduced)) "filtered" else "general"))
                }
            }))
            base.profile = global_JSON.parse(global_JSON.stringify(p))
            base.adaptVersion = 3
            base.decision = jsAdd(jsAdd(m.reason, " "), (if (truth(m.reduced)) "Sin aumento automático de intensidad." else (if (truth(m.auto)) base.decision else "No se asignan ejercicios automáticamente en este modo.")))
            return base
        })
        root.Adapt = jsObject { obj74 ->
            obj74["goals"] = goals
            obj74["zones"] = zones
            obj74["types"] = types
            obj74["restrictions"] = restrictions
            obj74["mode"] = mode
            obj74["reasons"] = reasons
            obj74["allowed"] = allowed
            obj74["normalize"] = normalize
            obj74["routineCompatible"] = routineCompatible
            obj74["active"] = active
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
