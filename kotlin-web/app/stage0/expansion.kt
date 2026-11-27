@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installexpansion

import hunter.browser.*

@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("NewExercises") private external var global_NewExercises: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Set") private external var global_Set: dynamic

internal fun installExpansion() {
    dynamicValue((fun(root: dynamic): dynamic {
        val rows: dynamic = jsonValue("[[\"altcurl\",\"curl\",\"Curl alterno con mancuernas\",\"alternate\",\"Flexiona un codo manteniendo el otro brazo abajo. Baja y cambia de brazo.\"],[\"althammer\",\"hammer\",\"Curl martillo alterno\",\"alternate\",\"Mantén las palmas enfrentadas. Flexiona un brazo, baja y cambia de lado.\"],[\"singlelateral\",\"lateral\",\"Elevación lateral unilateral\",\"unilateral\",\"Eleva solamente un brazo; el otro permanece junto al cuerpo. Completa ambos lados.\"],[\"singlefront\",\"frontlift\",\"Elevación frontal unilateral\",\"unilateral\",\"Eleva un brazo al frente sin superar una altura cómoda. Baja y completa el otro lado.\"],[\"singlepress\",\"press\",\"Press de suelo unilateral\",\"unilateral\",\"Empuja una mancuerna con un brazo mientras el otro descansa. Evita girar el torso y completa ambos lados.\"],[\"altfloorpress\",\"press\",\"Press de suelo alterno\",\"alternate\",\"Empuja una carga y vuelve a bajar antes de alternar con la otra. Mantén estable el tronco.\"],[\"singleoverhead\",\"overheadpress\",\"Press de hombro unilateral\",\"unilateral\",\"Empuja una mancuerna, baja con control y completa el otro lado sin inclinar el tronco.\"],[\"altseatedpress\",\"seatedpress\",\"Press alterno sentado\",\"alternate\",\"Desde la posición sentada, eleva una carga y regresa antes de cambiar de brazo.\"],[\"altseatedcurl\",\"seatedcurl\",\"Curl alterno sentado\",\"alternate\",\"Sentado, flexiona un codo y regresa. Alterna sin balancear el cuerpo.\"],[\"singletricep\",\"lyingtricep\",\"Extensión de tríceps tumbado unilateral\",\"unilateral\",\"Mantén un brazo orientado hacia arriba. Flexiona y extiende solo ese codo; completa ambos lados.\"],[\"squathold\",\"squat\",\"Sentadilla con pausa inferior\",\"pause\",\"Haz una pausa de un segundo en la profundidad que controles. Sube sin rebote; reduce carga o repeticiones si es necesario.\"],[\"goblethold\",\"goblet\",\"Sentadilla goblet con pausa inferior\",\"pause\",\"Mantén la carga cerca del pecho y pausa un segundo al bajar. Sube con control.\"],[\"bridgehold\",\"bridge\",\"Puente de glúteos con pausa superior\",\"pause\",\"Pausa un segundo arriba, sin arquear la espalda, y baja suavemente.\"],[\"calfhold\",\"calf\",\"Elevación de talones con pausa superior\",\"pause\",\"Pausa un segundo con los talones elevados, usando apoyo si lo necesitas. Baja sin rebotes.\"],[\"rowhold\",\"row\",\"Remo unilateral con pausa junto a la cadera\",\"pause\",\"Pausa un segundo con la mancuerna junto a la cadera. Baja sin rotar el torso.\"],[\"splitpause\",\"split\",\"Sentadilla dividida con pausa inferior\",\"pause\",\"Mantén ambos pies fijos. Pausa un segundo abajo dentro de un recorrido cómodo y sube.\"],[\"slowmarch\",\"march\",\"Marcha con cambios de cadencia\",\"interval\",\"Alterna diez segundos de marcha cómoda y diez segundos ligeramente más rápida, sin saltar.\"],[\"sidestepinterval\",\"sidestep\",\"Pasos laterales por intervalos\",\"interval\",\"Alterna diez segundos de pasos cómodos y diez segundos más ágiles sin cruzar los pies.\"],[\"boxinterval\",\"box\",\"Boxeo al aire por intervalos\",\"interval\",\"Alterna diez segundos de golpes suaves y diez algo más ágiles. No bloquees los codos ni uses cargas.\"],[\"stepjackinterval\",\"stepjack\",\"Aperturas sin salto por intervalos\",\"interval\",\"Alterna diez segundos cómodos y diez algo más ágiles. Siempre conserva un pie apoyado.\"],[\"seatedmarchinterval\",\"seatedmarch\",\"Marcha sentada por intervalos\",\"interval\",\"Alterna diez segundos de marcha sentada cómoda y diez algo más ágil, sin balancear el torso.\"],[\"heelcontrolled\",\"heel\",\"Deslizamiento de talón con retorno lento\",\"slow\",\"Extiende una pierna deslizando el talón. Tarda tres segundos en recogerla y cambia de lado.\"],[\"birdcontrolled\",\"bird\",\"Extensión contralateral con retorno lento\",\"slow\",\"Extiende brazo y pierna contrarios. Regresa en tres segundos antes de cambiar de lado.\"],[\"abductcontrolled\",\"standingabduct\",\"Abducción con apoyo y retorno lento\",\"slow\",\"Separa una pierna sin inclinar el tronco y regresa en tres segundos. Completa ambos lados.\"]]")
        root.NewExercises = js("[]")
        if (true) {
            val iterator5: dynamic = iteratorOf(rows)
            while (true) {
                val next6: dynamic = iterator5.next()
                if (truth(next6.done)) break
                var id: dynamic = absent
                var base: dynamic = absent
                var name: dynamic = absent
                var motion: dynamic = absent
                var extra: dynamic = absent
                val unpacked7: dynamic = fromIterable(next6.value)
                id = unpacked7[0]
                base = unpacked7[1]
                name = unpacked7[2]
                motion = unpacked7[3]
                extra = unpacked7[4]
                val source: dynamic = global_Engine.byId(base)
                if (truth(jsOr((!truth(source))) { (!truth(global_MotionDB[base])) })) {
                    throw global_Error(jsAdd("Falta base ", base))
                }
                val e: dynamic = jsObject { obj8 ->
                    copyFields(obj8, source)
                    obj8["id"] = id
                    obj8["baseId"] = id
                    obj8["name"] = name
                    obj8["variant"] = "base"
                    obj8["family"] = base
                    obj8["variation"] = motion
                    obj8["level"] = global_Math.max(source.level, (if (truth((motion === "unilateral"))) 1 else source.level))
                    obj8["sides"] = jsOr((motion === "unilateral")) { source.sides }
                    obj8["instructions"] = jsArray { array9 ->
                        appendAll(array9, jsOr(source.instructions) { arrayOf<dynamic>(source.steps) })
                        array9.push(extra)
                    }
                    obj8["steps"] = jsAdd(jsAdd(source.steps, " "), extra)
                    obj8["cue"] = extra
                    obj8["variantDescription"] = extra
                }
                if (truth((motion === "alternate"))) {
                    run {
                        val target10: dynamic = e
                        target10.cue = jsAdd(target10.cue, " Una acción de un brazo cuenta una repetición; alterna de forma equilibrada.")
                    }
                }
                global_Engine.exercises.push(e)
                global_NewExercises.push(e)
                val original: dynamic = global_MotionDB[base]
                val frames: dynamic = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                    var phase: dynamic = i
                    if (truth((motion === "pause"))) {
                        phase = (if (truth(jsLess(i, 18))) jsDivide(jsMultiply(i, 24), 18) else (if (truth(jsLess(i, 30))) 24 else jsAdd(24, jsDivide(jsMultiply(jsSubtract(i, 30), 24), 18))))
                    }
                    if (truth((motion === "slow"))) {
                        phase = (if (truth(jsLess(i, 12))) jsMultiply(i, 2) else jsAdd(24, jsDivide(jsMultiply(jsSubtract(i, 12), 24), 36)))
                    }
                    val f: dynamic = global_JSON.parse(global_JSON.stringify(original.frames[jsMod(global_Math.floor(phase), 48)]))
                    if (truth(dynamicValue(jsonValue("[\"alternate\",\"unilateral\"]")).includes(motion))) {
                        val rest: dynamic = original.frames[0]
                        val near: dynamic = jsOr((motion === "unilateral")) { jsLess(i, 24) }
                        if (true) {
                            val iterator12: dynamic = iteratorOf((if (truth(near)) jsonValue("[5,6]") else jsonValue("[3,4]")))
                            while (true) {
                                val next13: dynamic = iterator12.next()
                                if (truth(next13.done)) break
                                var k: dynamic = absent
                                k = next13.value
                                f.p[k] = rest.p[k].slice()
                            }
                        }
                        if (truth((motion === "alternate"))) {
                            val a: dynamic = original.frames[jsMultiply(jsMod(i, 24), 2)]
                            if (true) {
                                val iterator16: dynamic = iteratorOf((if (truth(near)) jsonValue("[3,4]") else jsonValue("[5,6]")))
                                while (true) {
                                    val next17: dynamic = iterator16.next()
                                    if (truth(next17.done)) break
                                    var k: dynamic = absent
                                    k = next17.value
                                    f.p[k] = a.p[k].slice()
                                }
                            }
                        }
                        f.loads = (if (truth((motion === "unilateral"))) arrayOf<dynamic>(f.p[4]) else arrayOf<dynamic>(f.p[4], f.p[6]))
                    }
                    return f
                }))
                global_MotionDB[id] = jsObject { obj24 ->
                    copyFields(obj24, original)
                    obj24["name"] = name
                    obj24["frames"] = frames
                }
            }
        }
        global_Catalog.baseExercises = global_Engine.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).length
        return absent
    }))(global_window)
    dynamicValue((fun(): dynamic {
        val groups: dynamic = jsonValue("[[\"Brazos alternos\",[\"altcurl\",\"althammer\",\"singletricep\"]],[\"Piernas con pausa\",[\"squathold\",\"splitpause\",\"calfhold\"]],[\"Empuje unilateral\",[\"singlepress\",\"singleoverhead\",\"singlelateral\"]],[\"Cardio sin saltos\",[\"slowmarch\",\"sidestepinterval\",\"boxinterval\"]],[\"Control corporal\",[\"bridgehold\",\"birdcontrolled\",\"heelcontrolled\"]],[\"Mancuernas con control\",[\"goblethold\",\"rowhold\",\"altfloorpress\"]],[\"Trabajo sentado\",[\"altseatedcurl\",\"altseatedpress\",\"seatedmarchinterval\"]],[\"Movilidad y actividad\",[\"abductcontrolled\",\"slowmarch\",\"stepjackinterval\"]]]")
        groups.forEach((fun(arg29: dynamic, i: dynamic): dynamic {
            var name: dynamic = absent
            var ids: dynamic = absent
            val unpacked30: dynamic = fromIterable(arg29)
            name = unpacked30[0]
            ids = unpacked30[1]
            val es: dynamic = ids.map((fun(id: dynamic): dynamic {
                return global_Engine.byId(id)
            }))
            val items: dynamic = es.map((fun(e: dynamic): dynamic {
                return jsObject { obj31 ->
                    obj31["id"] = e.id
                    obj31["sets"] = 2
                    obj31["target"] = (if (truth((e.unit === "sec"))) 20 else 8)
                    obj31["rest"] = 90
                }
            }))
            global_Catalog.routines.push(jsObject { obj32 ->
                obj32["id"] = jsAdd("v9-", i)
                obj32["name"] = name
                obj32["category"] = (if (truth(es.every((fun(e: dynamic): dynamic {
                    return (e.category === "Cardio")
                })))) "Cardio" else "Fuerza")
                obj32["level"] = run { val target34: dynamic = global_Math; invokeWith(target34.max, target34, jsArray { array33 ->
                    appendAll(array33, es.map((fun(e: dynamic): dynamic {
                        return e.level
                    })))
                }) }
                obj32["items"] = items
                obj32["description"] = "Sesión con variantes técnicas. Revisa la compatibilidad con tu perfil antes de aplicarla."
                obj32["minutes"] = (if (truth(global_Catalog.duration)) global_Catalog.duration(items) else 20)
                obj32["requirements"] = jsArray { array35 ->
                    appendAll(array35, jsConstruct(global_Set, arrayOf<dynamic>(es.flatMap((fun(e: dynamic): dynamic {
                        return e.requirements
                    })))))
                }
                obj32["impact"] = "Sin saltos"
            })
            return absent
        }))
        return absent
    }))()
}
