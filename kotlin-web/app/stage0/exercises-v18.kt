@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installexercisesv18

import hunter.browser.*

@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installExercisesV18() {
    dynamicValue((fun(): dynamic {
        val parent: dynamic = global_Engine.byId("frontlift")
        val seated: dynamic = global_MotionDB.seatedcurl
        val standing: dynamic = global_MotionDB.frontlift
        val e: dynamic = jsObject { obj3 ->
            copyFields(obj3, parent)
            obj3["id"] = "front-seated-reference"
            obj3["baseId"] = "front-seated-reference"
            obj3["name"] = "Elevación frontal con mancuernas sentado en banco"
            obj3["variant"] = "base"
            obj3["variantDescription"] = "Variante sentada con apoyo estable y demostración 2D."
            obj3["seated"] = true
            obj3["requiresFloor"] = false
            obj3["bench"] = true
            obj3["requirements"] = jsonValue("[\"db\",\"bench\"]")
            obj3["steps"] = "Siéntate con la espalda apoyada en el banco y los pies firmes. Mantén los codos ligeramente flexionados. Eleva las mancuernas al frente hasta la altura de los hombros y baja con control."
            obj3["setup"] = "Banco estable con respaldo, mancuernas ligeras y pies apoyados."
            obj3["cue"] = "Evita el balanceo y el dolor. Usa una carga que puedas controlar."
            obj3["originalVideo15"] = false
        }
        global_Engine.exercises.push(e)
        global_MotionDB[e.id] = jsObject { obj6 ->
            copyFields(obj6, standing)
            obj6["id"] = e.id
            obj6["frames"] = standing.frames.map((fun(f: dynamic, i: dynamic): dynamic {
                val pose: dynamic = global_JSON.parse(global_JSON.stringify(seated.frames[i]))
                if (true) {
                    val iterator7: dynamic = iteratorOf(jsonValue("[3,4,5,6]"))
                    while (true) {
                        val next8: dynamic = iterator7.next()
                        if (truth(next8.done)) break
                        var j: dynamic = absent
                        j = next8.value
                        pose.p[j] = jsArray { array11 ->
                            appendAll(array11, f.p[j])
                        }
                    }
                }
                pose.loads = dynamicValue(arrayOf<dynamic>(pose.p[4], pose.p[6])).map((fun(p: dynamic): dynamic {
                    return jsArray { array14 ->
                        appendAll(array14, p)
                    }
                }))
                return pose
            }))
        }
        return absent
    }))()
    dynamicValue((fun(root: dynamic): dynamic {
        global_MotionDB.kneepush.frames.forEach((fun(f: dynamic): dynamic {
            f.p[8][1] = 610
            f.p[10][1] = 615
            return absent
        }))
        val upright: dynamic = global_MotionDB.squat.frames[0]
        val crouch: dynamic = global_MotionDB.squat.frames[24]
        val jumpKeys: dynamic = arrayOf<dynamic>(arrayOf<dynamic>(0, upright, 0), arrayOf<dynamic>(9, crouch, 0), arrayOf<dynamic>(17, upright, 0), arrayOf<dynamic>(24, upright, (-numeric(85))), arrayOf<dynamic>(31, crouch, 0), arrayOf<dynamic>(40, upright, 0), arrayOf<dynamic>(48, upright, 0))
        global_MotionDB.squatjump.frames = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
            var k: dynamic = 0
            while (truth(jsLess(jumpKeys[jsAdd(k, 1)][0], i))) {
                run {
                    val previous23: dynamic = numeric(k)
                    k = jsAdd(previous23, 1)
                }
            }
            val a: dynamic = jumpKeys[k]
            val b: dynamic = jumpKeys[jsAdd(k, 1)]
            val t: dynamic = jsDivide(jsSubtract(i, a[0]), jsSubtract(b[0], a[0]))
            val u: dynamic = jsMultiply(jsMultiply(t, t), jsSubtract(3, jsMultiply(2, t)))
            val f: dynamic = global_JSON.parse(global_JSON.stringify(a[1]))
            f.p = a[1].p.map((fun(p: dynamic, j: dynamic): dynamic {
                return p.map((fun(v: dynamic, n: dynamic): dynamic {
                    return jsAdd(jsAdd(v, jsMultiply(jsSubtract(b[1].p[j][n], v), u)), (if (truth((n === 1))) jsAdd(a[2], jsMultiply(jsSubtract(b[2], a[2]), u)) else 0))
                }))
            }))
            f.detail.jumpPhase18 = (if (truth(jsLess(i, 17))) "Preparación" else (if (truth(jsLess(i, 31))) "Despegue" else "Aterrizaje"))
            return f
        }))
        global_MotionDB.linehop.frames.forEach((fun(f: dynamic, i: dynamic): dynamic {
            val dx: dynamic = jsMultiply(35, global_Math.sin(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2)))
            f.p.forEach((fun(p: dynamic): dynamic {
                return run {
                    val target28: dynamic = p
                    val key29: dynamic = 0
                    val assigned30: dynamic = jsAdd(target28[key29], dx)
                    target28[key29] = assigned30
                    assigned30
                }
            }))
            return absent
        }))
        val specifications: dynamic = jsonValue("[[\"squat\",\"Sentadilla con descenso de tres segundos\"],[\"goblet\",\"Sentadilla goblet con descenso controlado\"],[\"rdl\",\"Peso muerto rumano con descenso de tres segundos\"],[\"press\",\"Press de suelo con retorno de tres segundos\"],[\"neutral\",\"Press neutro con retorno controlado\"],[\"benchpress\",\"Press de banca con retorno de tres segundos\"],[\"overheadpress\",\"Press de hombros con descenso controlado\"],[\"incline\",\"Flexión inclinada con descenso de tres segundos\"],[\"kneepush\",\"Flexión con rodillas y descenso controlado\"],[\"curl\",\"Curl de bíceps con descenso de tres segundos\"],[\"hammer\",\"Curl martillo con descenso controlado\"],[\"row\",\"Remo unilateral con retorno de tres segundos\"],[\"bentrow\",\"Remo bilateral con retorno controlado\"],[\"bridge\",\"Puente de glúteos con pausa de dos segundos\"],[\"calf\",\"Elevación de talones con pausa de dos segundos\"],[\"split\",\"Sentadilla dividida con descenso controlado\"],[\"standingabduct\",\"Abducción de cadera con pausa de dos segundos\"],[\"legextend\",\"Extensión de rodilla sentada con pausa\"],[\"bird\",\"Bird dog con pausa de dos segundos\"],[\"deadbug\",\"Dead bug con extensión controlada\"]]")
        val added: dynamic = js("[]")
        if (true) {
            val iterator31: dynamic = iteratorOf(specifications)
            while (true) {
                val next32: dynamic = iterator31.next()
                if (truth(next32.done)) break
                var parentId: dynamic = absent
                var name: dynamic = absent
                val unpacked33: dynamic = fromIterable(next32.value)
                parentId = unpacked33[0]
                name = unpacked33[1]
                val parent: dynamic = global_Engine.byId(parentId)
                val original: dynamic = global_MotionDB[parentId]
                val pause: dynamic = dynamicValue(jsonValue("[\"bridge\",\"calf\",\"standingabduct\",\"legextend\",\"bird\"]")).includes(parentId)
                val id: dynamic = jsAdd(parentId, "-control18")
                val cadence: dynamic = (if (truth(pause)) 6 else 5)
                val frames: dynamic = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                    val t: dynamic = jsDivide(i, 48)
                    val phase: dynamic = (if (truth(pause)) (if (truth(jsLess(t, jsDivide(1, 3)))) jsMultiply(t, 72) else (if (truth(jsLess(t, jsDivide(2, 3)))) 24 else jsAdd(24, jsMultiply(jsSubtract(t, jsDivide(2, 3)), 72)))) else (if (truth(jsLess(t, 0.4))) jsMultiply(t, 60) else jsAdd(24, jsMultiply(jsSubtract(t, 0.4), 40))))
                    val a: dynamic = original.frames[jsMod(global_Math.floor(phase), 48)]
                    val b: dynamic = original.frames[jsMod(jsAdd(global_Math.floor(phase), 1), 48)]
                    val blend: dynamic = jsMod(phase, 1)
                    val f: dynamic = jsObject { obj34 ->
                        copyFields(obj34, global_JSON.parse(global_JSON.stringify(a)))
                        obj34["p"] = a.p.map((fun(p: dynamic, j: dynamic): dynamic {
                            return p.map((fun(v: dynamic, k: dynamic): dynamic {
                                return jsAdd(v, jsMultiply(jsSubtract(b.p[j][k], v), blend))
                            }))
                        }))
                        obj34["loads"] = a.loads.map((fun(p: dynamic, j: dynamic): dynamic {
                            return p.map((fun(v: dynamic, k: dynamic): dynamic {
                                return jsAdd(v, jsMultiply(jsSubtract(dynamicValue(jsOr(b.loads[j]) { p })[k], v), blend))
                            }))
                        }))
                    }
                    f.detail.stage18 = (if (truth(pause)) (if (truth(jsLess(t, jsDivide(1, 3)))) "EJECUCIÓN CONTROLADA" else (if (truth(jsLess(t, jsDivide(2, 3)))) "PAUSA DE DOS SEGUNDOS" else "REGRESO CONTROLADO")) else (if (truth(jsLess(t, 0.4))) "EJECUCIÓN CONTROLADA" else "DESCENSO DE TRES SEGUNDOS"))
                    return f
                }))
                val description: dynamic = (if (truth(pause)) "Realiza la fase de esfuerzo en dos segundos, mantén dos segundos y regresa en dos segundos." else "Realiza la fase de esfuerzo en dos segundos y el descenso o regreso en tres segundos.")
                val e: dynamic = jsObject { obj37 ->
                    copyFields(obj37, parent)
                    obj37["id"] = id
                    obj37["baseId"] = id
                    obj37["variant"] = "base"
                    obj37["originalVideo15"] = false
                    obj37["name"] = name
                    obj37["tempo"] = description
                    obj37["tempoSeconds18"] = cadence
                    obj37["variantDescription"] = jsAdd("Variante de cadencia propia. ", description)
                    obj37["steps"] = jsAdd(jsAdd(parent.steps, " "), description)
                    obj37["instructions"] = jsArray { array38 ->
                        appendAll(array38, parent.instructions)
                        array38.push(description)
                    }
                    obj37["cue"] = jsAdd(parent.cue, " Mantén el control durante toda la repetición.")
                }
                removeKey(e, "media")
                removeKey(e, "demo")
                global_Engine.exercises.push(e)
                global_MotionDB[id] = jsObject { obj41 ->
                    copyFields(obj41, original)
                    obj41["id"] = id
                    obj41["name"] = name
                    obj41["frames"] = frames
                    obj41["hold"] = false
                }
                added.push(id)
            }
        }
        root.ExerciseExpansion18 = jsObject { obj44 ->
            obj44["added"] = added
            obj44["source"] = "https://www.acefitness.org/resources/everyone/exercise-library/"
        }
        return absent
    }))(global_window)
}
