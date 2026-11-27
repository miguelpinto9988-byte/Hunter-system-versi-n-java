@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installexercisesv14

import hunter.browser.*

@JsName("Engine") private external var global_Engine: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installExercisesV14() {
    dynamicValue((fun(root: dynamic): dynamic {
        val specs: dynamic = jsonValue("[[\"lowhop\",\"Saltos bajos con aterrizaje controlado\",\"rope\",1,\"power\",\"reps\",\"Pies paralelos, rodillas ligeramente flexionadas. Haz un salto de poca altura y aterriza suave sobre ambos pies. Estabiliza antes de repetir.\",\"Haz 4 saltos de calidad; la altura no es el objetivo.\"],[\"squatjump\",\"Sentadilla con salto y pausa de aterrizaje\",\"squat\",2,\"power\",\"reps\",\"Haz una sentadilla de recorrido cómodo. Impúlsate en un salto corto, aterriza con caderas y rodillas flexionadas y haz una pausa antes de repetir.\",\"Detén la serie si pierdes equilibrio o el aterrizaje deja de ser suave.\"],[\"skaterhop\",\"Salto lateral de patinador\",\"skater\",2,\"power\",\"reps\",\"Impúlsate lateralmente y aterriza sobre el pie contrario con la rodilla flexible. Mantén la posición antes de cambiar de lado.\",\"Recorrido corto; cada salto cuenta como una repetición.\"],[\"linehop\",\"Saltos cortos sobre una línea\",\"rope\",1,\"power\",\"reps\",\"Imagina una línea en el suelo. Salta de un lado al otro con ambos pies y aterriza con control. Mantén la amplitud pequeña.\",\"Despeja el suelo y usa una superficie estable.\"],[\"fastfeet\",\"Pasos rápidos en el lugar\",\"fastmarch\",1,\"agility\",\"sec\",\"Alterna apoyos cortos y rápidos en el lugar, sin saltar alto. Mantén rodillas suaves, tronco estable y brazos acompañando.\",\"Prioriza precisión antes de aumentar la cadencia.\"],[\"shuffle\",\"Desplazamiento lateral de agilidad\",\"sidestep\",1,\"agility\",\"sec\",\"Da dos pasos laterales cortos sin cruzar los pies y vuelve. Flexiona ligeramente las rodillas y mantén el centro de gravedad estable.\",\"Necesitas un espacio despejado de dos pasos.\"],[\"highknees\",\"Carrera en el lugar con rodillas altas\",\"jog\",2,\"agility\",\"sec\",\"Corre en el lugar alternando las rodillas hasta una altura que controles. Acompaña con el brazo contrario y aterriza suave.\",\"No aceleres si necesitas inclinarte hacia atrás.\"],[\"lungeknee\",\"Zancada atrás y elevación de rodilla\",\"reverselunge\",1,\"strength\",\"reps\",\"Da una zancada hacia atrás. Regresa al apoyo inicial y eleva la rodilla libre con una pausa de equilibrio. Repite y cambia de lado.\",\"Apoyo estable; puedes tocar una pared si lo necesitas.\"],[\"stepcross\",\"Pasos cruzados de coordinación\",\"frontbackstep\",1,\"agility\",\"sec\",\"Da un paso diagonal corto, vuelve al centro y cambia de lado. Acompaña con el brazo contrario, sin girar bruscamente la rodilla.\",\"Los pasos son cortos y deliberados.\"],[\"skaterreach\",\"Paso lateral con alcance contralateral\",\"skater\",1,\"agility\",\"sec\",\"Da un paso lateral sin salto. Lleva la mano contraria hacia el muslo del lado de apoyo y vuelve al centro. Alterna.\",\"Flexiona desde cadera y rodilla, sin forzar el giro del tronco.\"],[\"dbmarch\",\"Marcha del granjero con mancuernas\",\"farmerwalk\",1,\"strength\",\"sec\",\"Sujeta las mancuernas a los lados. Alterna pasos cortos en el lugar, manteniendo hombros relajados y tronco estable.\",\"Usa una carga controlable; no balancees las mancuernas.\"],[\"ankledrive\",\"Balanceo controlado de tobillo de pie\",\"calfstretch\",0,\"mobility\",\"reps\",\"Frente a una pared y con las manos apoyadas, acerca suavemente una rodilla hacia la pared sin levantar el talón. Vuelve y cambia de lado.\",\"Recorrido cómodo; no empujes a través del dolor.\"]]")
        val ids: dynamic = js("[]")
        if (true) {
            val iterator3: dynamic = iteratorOf(specs)
            while (true) {
                val next4: dynamic = iterator3.next()
                if (truth(next4.done)) break
                var id: dynamic = absent
                var name: dynamic = absent
                var base: dynamic = absent
                var level: dynamic = absent
                var family: dynamic = absent
                var unit: dynamic = absent
                var steps: dynamic = absent
                var cue: dynamic = absent
                val unpacked5: dynamic = fromIterable(next4.value)
                id = unpacked5[0]
                name = unpacked5[1]
                base = unpacked5[2]
                level = unpacked5[3]
                family = unpacked5[4]
                unit = unpacked5[5]
                steps = unpacked5[6]
                cue = unpacked5[7]
                val template: dynamic = global_Engine.byId(base)
                val e: dynamic = jsObject { obj6 ->
                    copyFields(obj6, global_JSON.parse(global_JSON.stringify(template)))
                    obj6["id"] = id
                    obj6["baseId"] = id
                    obj6["variant"] = "base"
                    obj6["name"] = name
                    obj6["level"] = level
                    obj6["family"] = family
                    obj6["unit"] = unit
                    obj6["steps"] = steps
                    obj6["cue"] = cue
                    obj6["sourceOnly"] = false
                    obj6["variantDescription"] = "Movimiento propio del catálogo. Revisa preparación, ejecución y compatibilidad."
                    obj6["category"] = (if (truth((family === "strength"))) "Fuerza" else (if (truth((family === "mobility"))) "Movilidad" else "Cardio"))
                    obj6["impact"] = (if (truth(jsOr((family === "power")) { (id === "highknees") })) "Con saltos" else "Sin saltos")
                }
                if (truth((family === "power"))) {
                    global_Object.assign(e, jsonValue("{\"kneeBend\":true,\"zones\":[\"back\",\"hip\",\"knee\",\"ankle\"],\"requirements\":[],\"floor\":false,\"wristLoad\":false,\"equipment\":\"body\",\"sides\":false}"))
                }
                if (truth((id === "lungeknee"))) {
                    e.sides = true
                }
                if (truth((id === "ankledrive"))) {
                    e.sides = true
                }
                e.setup = jsAdd(steps.split(". ")[0], ".")
                e.instructions = steps.split(jsRegExp("\\.\\s+", "")).filter(global_Boolean)
                e.breath = "Respira con regularidad, sin mantener el aire."
                e.tempo = (if (truth((family === "power"))) "Haz una pausa estable entre saltos." else "Controla el movimiento antes de acelerar.")
                e.control = cue
                global_Engine.exercises.push(e)
                ids.push(id)
                val m: dynamic = global_JSON.parse(global_JSON.stringify(global_MotionDB[base]))
                m.name = name
                m.id = id
                if (truth((id === "squatjump"))) {
                    m.frames = m.frames.map((fun(f: dynamic, i: dynamic): dynamic {
                        val a: dynamic = jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2)
                        val jump: dynamic = jsMultiply(global_Math.max(0, (-numeric(global_Math.sin(a)))), 70)
                        f.p = f.p.map((fun(p: dynamic): dynamic {
                            return arrayOf<dynamic>(p[0], jsSubtract(p[1], jump))
                        }))
                        return f
                    }))
                }
                if (truth((id === "linehop"))) {
                    m.frames = m.frames.map((fun(f: dynamic, i: dynamic): dynamic {
                        val dx: dynamic = jsMultiply(global_Math.sin(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2)), 40)
                        f.p = f.p.map((fun(p: dynamic): dynamic {
                            return arrayOf<dynamic>(jsAdd(p[0], dx), p[1])
                        }))
                        return f
                    }))
                }
                if (truth((id === "skaterhop"))) {
                    m.frames = m.frames.map((fun(f: dynamic, i: dynamic): dynamic {
                        val lift: dynamic = jsMultiply(global_Math.abs(global_Math.sin(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2))), 35)
                        f.p = f.p.map((fun(p: dynamic): dynamic {
                            return arrayOf<dynamic>(p[0], jsSubtract(p[1], lift))
                        }))
                        return f
                    }))
                }
                if (truth((id === "highknees"))) {
                    m.frames = m.frames.map((fun(f: dynamic, i: dynamic): dynamic {
                        val q: dynamic = global_Math.sin(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2))
                        if (true) {
                            val iterator39: dynamic = iteratorOf(jsonValue("[7,8]"))
                            while (true) {
                                val next40: dynamic = iterator39.next()
                                if (truth(next40.done)) break
                                var k: dynamic = absent
                                k = next40.value
                                run {
                                    val target41: dynamic = f.p[k]
                                    val key42: dynamic = 1
                                    target41[key42] = jsSubtract(target41[key42], jsMultiply(global_Math.max(0, q), 65))
                                }
                            }
                        }
                        if (true) {
                            val iterator43: dynamic = iteratorOf(jsonValue("[9,10]"))
                            while (true) {
                                val next44: dynamic = iterator43.next()
                                if (truth(next44.done)) break
                                var k: dynamic = absent
                                k = next44.value
                                run {
                                    val target45: dynamic = f.p[k]
                                    val key46: dynamic = 1
                                    target45[key46] = jsSubtract(target45[key46], jsMultiply(global_Math.max(0, (-numeric(q))), 65))
                                }
                            }
                        }
                        return f
                    }))
                }
                if (truth((id === "lungeknee"))) {
                    m.frames = m.frames.map((fun(f: dynamic, i: dynamic): dynamic {
                        val q: dynamic = global_Math.max(0, global_Math.cos(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2)))
                        run {
                            val target49: dynamic = f.p[7]
                            val key50: dynamic = 1
                            target49[key50] = jsSubtract(target49[key50], jsMultiply(q, 85))
                        }
                        run {
                            val target51: dynamic = f.p[8]
                            val key52: dynamic = 1
                            target51[key52] = jsSubtract(target51[key52], jsMultiply(q, 80))
                        }
                        return f
                    }))
                }
                if (truth((id === "stepcross"))) {
                    m.frames = m.frames.map((fun(f: dynamic, i: dynamic): dynamic {
                        run {
                            val target55: dynamic = f.p[8]
                            val key56: dynamic = 0
                            target55[key56] = jsAdd(target55[key56], jsMultiply(global_Math.sin(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2)), 25))
                        }
                        return f
                    }))
                }
                if (truth((id === "ankledrive"))) {
                    m.hold = false
                    m.frames = m.frames.map((fun(f: dynamic, i: dynamic): dynamic {
                        val a: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2))), 2)
                        run {
                            val target61: dynamic = f.p[7]
                            val key62: dynamic = 0
                            target61[key62] = jsAdd(target61[key62], jsMultiply(a, 24))
                        }
                        run {
                            val target63: dynamic = f.p[7]
                            val key64: dynamic = 1
                            target63[key64] = jsSubtract(target63[key64], jsMultiply(a, 10))
                        }
                        return f
                    }))
                }
                global_MotionDB[id] = m
            }
        }
        root.NewExercises14 = ids
        return absent
    }))(global_window)
}
