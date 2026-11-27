@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installsourceexercisesv13

import hunter.browser.*

@JsName("Engine") private external var global_Engine: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installSourceExercisesV13() {
    dynamicValue((fun(root: dynamic): dynamic {
        var cycle: dynamic = absent
        var standing: dynamic = absent
        var arm: dynamic = absent
        var frame: dynamic = absent
        standing = fun(): dynamic {
            return jsonValue("{\"p\":[[360,190],[360,250],[360,420],[285,335],[280,440],[435,335],[440,440],[320,550],[320,680],[400,550],[400,680]],\"equipment\":[],\"loads\":[],\"detail\":{}}")
        }
        arm = fun(f: dynamic, side: dynamic, x: dynamic, y: dynamic): dynamic {
            val elbow: dynamic = (if (truth(side)) 5 else 3)
            val hand: dynamic = (if (truth(side)) 6 else 4)
            f.p[hand] = arrayOf<dynamic>(x, y)
            f.p[elbow] = arrayOf<dynamic>(jsDivide(jsAdd(360, x), 2), jsDivide(jsAdd(250, y), 2))
            return absent
        }
        frame = fun(kind: dynamic, i: dynamic): dynamic {
            val f: dynamic = standing()
            val t: dynamic = jsDivide(i, 48)
            val q: dynamic = cycle(t)
            val theta: dynamic = jsMultiply(jsMultiply(t, global_Math.PI), 2)
            if (true) {
                var side: dynamic = absent
                side = 0
                var firstIteration13 = true
                while (true) {
                    if (!firstIteration13) {
                        run {
                            val previous14: dynamic = numeric(side)
                            side = jsAdd(previous14, 1)
                        }
                    }
                    firstIteration13 = false
                    if (!truth(jsLess(side, 2))) break
                    val sign: dynamic = (if (truth(side)) 1 else (-numeric(1)))
                    if (truth((kind === "world"))) {
                        arm(f, side, jsAdd(360, jsMultiply(jsMultiply(sign, 190), global_Math.sin(jsMultiply(q, global_Math.PI)))), jsSubtract(440, jsMultiply(365, q)))
                    }
                    if (truth(jsOr((kind === "forward")) { (kind === "backward") })) {
                        val a: dynamic = jsMultiply(theta, (if (truth((kind === "forward"))) 1 else (-numeric(1))))
                        arm(f, side, jsAdd(360, jsMultiply(sign, jsAdd(185, jsMultiply(8, global_Math.cos(a))))), jsAdd(250, jsMultiply(16, global_Math.sin(a))))
                    }
                    if (truth((kind === "updown"))) {
                        f.p[(if (truth(side)) 5 else 3)] = arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, jsSubtract(85, jsMultiply(30, q)))), jsSubtract(300, jsMultiply(140, q)))
                        f.p[(if (truth(side)) 6 else 4)] = arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 65)), jsSubtract(225, jsMultiply(150, q)))
                    }
                    if (truth((kind === "pulses"))) {
                        val front: dynamic = jsAtLeast(i, 24)
                        val x: dynamic = (if (truth(front)) jsAdd(470, jsMultiply(sign, 35)) else jsAdd(360, jsMultiply(sign, 180)))
                        val y: dynamic = jsSubtract(260, jsMultiply(10, cycle(jsMultiply(t, 4))))
                        arm(f, side, x, y)
                    }
                    if (truth((kind === "fly"))) {
                        arm(f, side, jsAdd(jsAdd(360, jsMultiply(sign, jsMultiply(190, jsSubtract(1, q)))), jsMultiply(110, q)), jsAdd(250, jsMultiply(jsMultiply(5, sign), q)))
                    }
                    if (truth((kind === "backswing"))) {
                        arm(f, side, jsAdd(jsAdd(360, jsMultiply(sign, 65)), jsMultiply(100, global_Math.sin(theta))), jsSubtract(430, jsMultiply(20, global_Math.abs(global_Math.sin(theta)))))
                    }
                    if (truth((kind === "raise"))) {
                        if (truth((side === 0))) {
                            arm(f, side, 185, 250)
                        } else {
                            f.p[5] = arrayOf<dynamic>(jsSubtract(445, jsMultiply(25, q)), jsSubtract(250, jsMultiply(100, q)))
                            f.p[6] = arrayOf<dynamic>(jsSubtract(445, jsMultiply(20, q)), jsSubtract(165, jsMultiply(100, q)))
                        }
                    }
                    if (truth((kind === "curl"))) {
                        val front: dynamic = jsAtLeast(i, 24)
                        val s: dynamic = cycle(jsDivide(jsMod(i, 24), 24))
                        val x: dynamic = (if (truth(front)) jsAdd(460, jsMultiply(sign, 30)) else jsAdd(360, jsMultiply(sign, 175)))
                        f.p[(if (truth(side)) 5 else 3)] = arrayOf<dynamic>(x, 260)
                        f.p[(if (truth(side)) 6 else 4)] = arrayOf<dynamic>(jsAdd(x, jsMultiply(jsMultiply(sign, 35), jsSubtract(1, s))), jsSubtract(350, jsMultiply(170, s)))
                    }
                }
            }
            f.detail.grip = jsObject { obj29 ->
                obj29["world"] = "Eleva y baja ambos brazos"
                obj29["forward"] = "Círculos pequeños hacia delante"
                obj29["backward"] = "Círculos pequeños hacia atrás"
                obj29["updown"] = "Eleva los brazos y baja los codos"
                obj29["pulses"] = (if (truth(jsLess(i, 24))) "Dos pulsos laterales" else "Dos pulsos al frente")
                obj29["fly"] = "Abre y cierra los brazos"
                obj29["backswing"] = "Brazos extendidos hacia atrás"
                obj29["raise"] = "Un brazo lateral y otro se eleva"
                obj29["curl"] = (if (truth(jsLess(i, 24))) "Curl con brazos a los lados" else "Curl con brazos al frente")
            }[kind]
            return f
        }
        val E: dynamic = global_Engine
        val clone: dynamic = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        cycle = (fun(t: dynamic): dynamic {
            return jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(t, global_Math.PI), 2))), 2)
        })
        val rows: dynamic = jsonValue("[[\"pau-armworld\",\"world\",\"Círculos amplios de brazos\",\"Eleva ambos brazos y bájalos por los lados describiendo un círculo controlado.\"],[\"pau-armcircle-f\",\"forward\",\"Círculos de brazos hacia delante\",\"Extiende los brazos lateralmente a la altura de los hombros y realiza círculos pequeños hacia delante.\"],[\"pau-armcircle-b\",\"backward\",\"Círculos de brazos hacia atrás\",\"Mantén los brazos a la altura de los hombros y realiza círculos pequeños hacia atrás.\"],[\"pau-armupdown\",\"updown\",\"Elevación de brazos y descenso de codos\",\"Eleva los brazos. Baja los codos hacia los lados y hacia atrás dentro de un recorrido cómodo.\"],[\"pau-armpulses\",\"pulses\",\"Pulsos laterales y frontales de brazos\",\"Haz dos pulsos pequeños con los brazos a los lados; lleva los brazos al frente y haz dos pulsos más.\"],[\"pau-armfly\",\"fly\",\"Aperturas de brazos sin carga\",\"Lleva los brazos al frente a la altura de los hombros, abre hacia los lados y regresa con control.\"],[\"pau-armsback\",\"backswing\",\"Extensión de brazos hacia atrás sin carga\",\"Con los brazos extendidos hacia abajo, llévalos ligeramente al frente y después hacia atrás sin arquear la espalda.\"],[\"pau-armraise\",\"raise\",\"Elevación de un brazo con sostén lateral\",\"Mantén un brazo extendido lateralmente y eleva el otro sobre la cabeza. Desciende el codo a la altura del hombro; utiliza el lado indicado en el bloque.\"],[\"pau-armcurl\",\"curl\",\"Curl de bíceps lateral y frontal sin carga\",\"Con los brazos abiertos, flexiona y extiende los codos. Luego lleva los brazos al frente y repite la flexión; alterna ambas posiciones.\"]]")
        if (true) {
            val iterator30: dynamic = iteratorOf(rows)
            while (true) {
                val next31: dynamic = iterator30.next()
                if (truth(next31.done)) break
                var id: dynamic = absent
                var kind: dynamic = absent
                var name: dynamic = absent
                var steps: dynamic = absent
                val unpacked32: dynamic = fromIterable(next31.value)
                id = unpacked32[0]
                kind = unpacked32[1]
                name = unpacked32[2]
                steps = unpacked32[3]
                val e: dynamic = jsObject { obj33 ->
                    copyFields(obj33, clone(E.byId("chestopen")))
                    obj33["id"] = id
                    obj33["baseId"] = id
                    obj33["name"] = name
                    obj33["variant"] = "base"
                    obj33["family"] = "chestopen"
                    obj33["sourceOnly"] = true
                    obj33["category"] = "Fuerza"
                    obj33["group"] = "Hombros"
                    obj33["equipment"] = "body"
                    obj33["requirements"] = js("[]")
                    obj33["unit"] = "sec"
                    obj33["sides"] = false
                    obj33["level"] = 1
                    obj33["floor"] = false
                    obj33["overhead"] = dynamicValue(jsonValue("[\"world\",\"updown\",\"raise\"]")).includes(kind)
                    obj33["spineMove"] = false
                    obj33["wristLoad"] = false
                    obj33["kneeBend"] = false
                    obj33["zones"] = jsonValue("[\"shoulder\",\"elbow\",\"back\",\"hip\",\"knee\",\"ankle\"]")
                    obj33["steps"] = steps
                    obj33["instructions"] = arrayOf<dynamic>(steps)
                    obj33["cue"] = "Movimiento controlado dentro de un recorrido cómodo; detente si aparece dolor."
                    obj33["variantDescription"] = "Ilustración original con volumen 2.5D. Los lados se reflejan para la guía; la revisión visual del video está pendiente."
                }
                E.exercises.push(e)
                global_MotionDB[id] = jsObject { obj36 ->
                    obj36["name"] = name
                    obj36["hold"] = false
                    obj36["frames"] = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                        return frame(kind, i)
                    }))
                }
            }
        }
        root.SourceExercises13 = rows.map((fun(r: dynamic): dynamic {
            return r[0]
        }))
        global_Catalog.baseExercises = E.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).length
        return absent
    }))(global_window)
}
