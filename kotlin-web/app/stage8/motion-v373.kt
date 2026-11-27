@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installmotionv373

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("Motion30") private external var global_Motion30: dynamic
@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Rig2D23") private external var global_Rig2D23: dynamic
@JsName("Motion2D18") private external var global_Motion2D18: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installMotionV373() {
    dynamicValue((fun(root: dynamic): dynamic {
        var ids: dynamic = absent
        var cache: dynamic = absent
        var copy: dynamic = absent
        var mix: dynamic = absent
        var ease: dynamic = absent
        var path: dynamic = absent
        var correct: dynamic = absent
        var inset: dynamic = absent
        path = fun(t: dynamic, keys: dynamic): dynamic {
            val i: dynamic = keys.findIndex((fun(k: dynamic): dynamic {
                return jsAtLeast(k[0], t)
            }))
            if (truth(jsAtMost(i, 0))) {
                return copy(keys[0][1])
            }
            val a: dynamic = keys[jsSubtract(i, 1)]
            val b: dynamic = keys[i]
            return mix(a[1], b[1], ease(jsDivide(jsSubtract(t, a[0]), jsSubtract(b[0], a[0]))))
        }
        correct = fun(e: dynamic, source: dynamic): dynamic {
            if (truth(jsOr((!truth(ids.has(e.id)))) { (source.motionRevision373 === 373) })) {
                return source
            }
            if (truth(cache.has(source))) {
                return cache["get"](source)
            }
            val m: dynamic = copy(source)
            val drag: dynamic = (e.id === "home-plankdrag30")
            m.view21 = jsObject { obj9 ->
                copyFields(obj9, m.view21)
                obj9["primary"] = (if (truth(drag)) "top" else "front")
                obj9["label"] = (if (truth(drag)) "Superior" else "Frontal")
                obj9["project"] = dynamicValue(null)
                obj9["mirror"] = false
                obj9["inset"] = (if (truth(drag)) "plankdrag373" else "curl373")
                obj9["reason"] = (if (truth(drag)) "Observa una mano fija y el arrastre de la carga bajo el torso. El detalle lateral muestra los apoyos." else "Los brazos quedan a los lados del torso. Alterna la flexión sin desplazar los codos.")
            }
            m.posture23 = (if (truth(drag)) "prone" else "upright")
            m.facing23 = (if (truth(drag)) "left" else null)
            m.frames.forEach((fun(f: dynamic, i: dynamic): dynamic {
                val t: dynamic = jsDivide(i, 48)
                val p: dynamic = f.p
                f.detail = jsObject { obj16 ->
                    copyFields(obj16, f.detail)
                    obj16["correction373"] = e.id
                    obj16["cycle373"] = t
                    obj16["view21"] = m.view21.primary
                }
                if (truth(drag)) {
                    val left: dynamic = jsLess(t, 0.5)
                    val local: dynamic = (if (truth(left)) jsMultiply(t, 2) else jsMultiply(jsSubtract(t, 0.5), 2))
                    val rightLoad: dynamic = jsonValue("[488,320]")
                    val leftLoad: dynamic = jsonValue("[232,320]")
                    val from: dynamic = (if (truth(left)) rightLoad else leftLoad)
                    val to: dynamic = (if (truth(left)) leftLoad else rightLoad)
                    val handRest: dynamic = (if (truth(left)) jsonValue("[258,250]") else jsonValue("[462,250]"))
                    val grip: dynamic = path(local, arrayOf<dynamic>(arrayOf<dynamic>(0, handRest), arrayOf<dynamic>(0.24, from), arrayOf<dynamic>(0.76, to), arrayOf<dynamic>(1, handRest)))
                    val load: dynamic = path(local, arrayOf<dynamic>(arrayOf<dynamic>(0, from), arrayOf<dynamic>(0.24, from), arrayOf<dynamic>(0.76, to), arrayOf<dynamic>(1, to)))
                    p[0] = jsonValue("[360,195]")
                    p[1] = jsonValue("[360,260]")
                    p[2] = jsonValue("[360,440]")
                    p[4] = (if (truth(left)) grip else jsonValue("[258,250]"))
                    p[6] = (if (truth(left)) jsonValue("[462,250]") else grip)
                    if (true) {
                        val iterator27: dynamic = iteratorOf(jsonValue("[[3,4,318,1],[5,6,402,-1]]"))
                        while (true) {
                            val next28: dynamic = iterator27.next()
                            if (truth(next28.done)) break
                            var el: dynamic = absent
                            var hand: dynamic = absent
                            var x: dynamic = absent
                            var bend: dynamic = absent
                            val unpacked29: dynamic = fromIterable(next28.value)
                            el = unpacked29[0]
                            hand = unpacked29[1]
                            x = unpacked29[2]
                            bend = unpacked29[3]
                            val origin: dynamic = arrayOf<dynamic>(x, 260)
                            val distance: dynamic = global_Math.hypot(jsSubtract(p[hand][0], x), jsSubtract(p[hand][1], 260))
                            val length: dynamic = global_Math.min(95, jsAdd(jsDivide(distance, 2), 24))
                            p[el] = global_Rig2D23.ik(origin, p[hand], length, bend)
                        }
                    }
                    p[7] = jsonValue("[335,568]")
                    p[8] = jsonValue("[310,700]")
                    p[9] = jsonValue("[385,568]")
                    p[10] = jsonValue("[410,700]")
                    f.equipment = js("[]")
                    f.loads = arrayOf<dynamic>(load)
                    f.detail.front19 = true
                    f.detail.topShoulder19 = false
                    f.detail.support23 = true
                    f.detail.floor23 = 730
                    f.detail.feet30 = jsonValue("{\"8\":[-0.08,1],\"10\":[0.08,1]}")
                    f.detail.activeHand373 = (if (truth(left)) 4 else 6)
                    f.detail.gripping373 = jsAnd(jsAtLeast(local, 0.24)) { jsAtMost(local, 0.76) }
                    f.detail.stage18 = (if (truth(jsLess(local, 0.24))) "ALCANZA · LA OTRA MANO QUEDA FIJA" else (if (truth(jsAtMost(local, 0.76))) "ARRASTRA BAJO EL TORSO · PELVIS ESTABLE" else "DEVUELVE LA MANO AL APOYO"))
                } else {
                    p[0] = jsonValue("[360,260]")
                    p[1] = jsonValue("[360,320]")
                    p[2] = jsonValue("[360,500]")
                    p[3] = jsonValue("[282,420]")
                    p[5] = jsonValue("[438,420]")
                    val a: dynamic = (if (truth(jsLess(t, 0.5))) jsPower(global_Math.sin(jsMultiply(jsMultiply(2, global_Math.PI), t)), 2) else 0)
                    val b: dynamic = (if (truth(jsAtLeast(t, 0.5))) jsPower(global_Math.sin(jsMultiply(jsMultiply(2, global_Math.PI), t)), 2) else 0)
                    if (true) {
                        val iterator70: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, 4, a, 1), arrayOf<dynamic>(5, 6, b, (-numeric(1)))))
                        while (true) {
                            val next71: dynamic = iterator70.next()
                            if (truth(next71.done)) break
                            var el: dynamic = absent
                            var hand: dynamic = absent
                            var u: dynamic = absent
                            var sign: dynamic = absent
                            val unpacked72: dynamic = fromIterable(next71.value)
                            el = unpacked72[0]
                            hand = unpacked72[1]
                            u = unpacked72[2]
                            sign = unpacked72[3]
                            p[hand] = arrayOf<dynamic>(jsAdd(p[el][0], jsMultiply(jsMultiply(sign, 38), global_Math.sin(jsMultiply(global_Math.PI, u)))), jsAdd(p[el][1], jsMultiply(86, global_Math.cos(jsMultiply(global_Math.PI, u)))))
                        }
                    }
                    p[7] = jsonValue("[310,548]")
                    p[8] = jsonValue("[293,710]")
                    p[9] = jsonValue("[410,548]")
                    p[10] = jsonValue("[427,710]")
                    f.equipment = jsonValue("[[\"bench\",[270,546,450,727]]]")
                    f.loads = arrayOf<dynamic>(copy(p[4]), copy(p[6]))
                    f.detail.floor23 = 727
                    f.detail.feet30 = jsonValue("{\"8\":[-0.08,1],\"10\":[0.08,1]}")
                    f.detail.curl373 = arrayOf<dynamic>(a, b)
                    f.detail.stage18 = (if (truth(jsMore(a, 0.02))) "FLEXIONA UN BRAZO · CODO FIJO" else (if (truth(jsMore(b, 0.02))) "ALTERNA EL BRAZO · TRONCO ESTABLE" else "BRAZOS A LOS LADOS · PIES APOYADOS"))
                }
                return absent
            }))
            m.motionRevision373 = 373
            cache["set"](source, m)
            return m
        }
        ids = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"home-plankdrag30\",\"altseatedcurl\"]")))
        val before: dynamic = global_Motion30["get"]
        cache = jsConstruct(global_WeakMap, js("[]"))
        copy = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        mix = (fun(a: dynamic, b: dynamic, t: dynamic): dynamic {
            return a.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsAdd(v, jsMultiply(jsSubtract(b[i], v), t))
            }))
        })
        ease = (fun(t: dynamic): dynamic {
            return jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(global_Math.PI, global_Math.max(0, global_Math.min(1, t))))), 2)
        })
        global_Motion30["get"] = (fun(id: dynamic): dynamic {
            val x: dynamic = before(id)
            return jsObject { obj101 ->
                copyFields(obj101, x)
                obj101["model"] = correct(x.e, x.model)
            }
        })
        inset = global_Motion30.inset
        global_Motion30.inset = (fun(kind: dynamic, f: dynamic, phase: dynamic, accent: dynamic): dynamic {
            var title: dynamic = absent
            var note: dynamic = absent
            var art: dynamic = absent
            if (truth(jsOr((!truth(dynamicValue(jsonValue("[\"plankdrag373\",\"curl373\"]")).includes(kind)))) { (!truth(ids.has(f.detail.correction373))) })) {
                return inset(kind, f, phase, accent)
            }
            val ink: dynamic = "#dceaf0"
            val red: dynamic = "#fb927d"
            if (truth((kind === "plankdrag373"))) {
                title = "DE LADO · UNA MANO SOSTIENE EL CUERPO"
                note = "Conserva hombros, caderas y talones alineados. Arrastra por el suelo, sin girar la pelvis."
                val load: dynamic = jsAdd(130, jsMultiply(jsDivide(jsSubtract(f.loads[0][0], 232), 256), 64))
                art = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M88 707H325\" stroke=\"#7a9aa7\" stroke-width=\"3\"/><circle cx=\"105\" cy=\"655\" r=\"10\" fill=\"#edc5ad\"/><path d=\"M118 662L198 681L286 701M139 667V704\" fill=\"none\" stroke=\"", ink), "\" stroke-width=\"10\" stroke-linecap=\"round\"/><path d=\"M139 672L"), load), " 698\" stroke=\""), red), "\" stroke-width=\"6\"/><path d=\"M"), jsSubtract(load, 9)), " 704H"), jsAdd(load, 9)), "\" stroke=\""), accent), "\" stroke-width=\"7\"/><text x=\"345\" y=\"683\" font-size=\"18\" fill=\""), ink), "\">APOYO ESTABLE</text>")
            } else {
                title = "DE LADO · FLEXIONA SIN MOVER EL CODO"
                note = "Alterna los brazos. El brazo superior queda estable y la muñeca sigue la línea del antebrazo."
                val amount: dynamic = run { val target107: dynamic = global_Math; invokeWith(target107.max, target107, jsArray { array106 ->
                    appendAll(array106, f.detail.curl373)
                }) }
                val angle: dynamic = jsSubtract(jsDivide(global_Math.PI, 2), jsMultiply(2.8, amount))
                val hand: dynamic = arrayOf<dynamic>(jsAdd(168, jsMultiply(43, global_Math.cos(angle))), jsAdd(676, jsMultiply(43, global_Math.sin(angle))))
                art = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M130 650V699H166V722M121 648V701\" stroke=\"#7a9aa7\" stroke-width=\"5\" fill=\"none\"/><circle cx=\"146\" cy=\"644\" r=\"10\" fill=\"#edc5ad\"/><path d=\"M146 658V692L179 702V722M151 661L168 676L", hand.join(" ")), "\" stroke=\""), ink), "\" stroke-width=\"8\" fill=\"none\" stroke-linecap=\"round\"/><circle cx=\"168\" cy=\"676\" r=\"5\" fill=\""), red), "\"/><path d=\"M"), jsSubtract(hand[0], 8)), " "), hand[1]), "H"), jsAdd(hand[0], 8)), "\" stroke=\""), accent), "\" stroke-width=\"7\"/><text x=\"300\" y=\"682\" font-size=\"18\" fill=\""), ink), "\">UN BRAZO CADA VEZ</text>")
            }
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-detail373=\"", kind), "\"><rect x=\"58\" y=\"620\" width=\"604\" height=\"113\" rx=\"14\" fill=\"#102431\" stroke=\"#47606e\"/><text x=\"74\" y=\"642\" font-size=\"13\" fill=\""), accent), "\" font-family=\"sans-serif\">"), title), "</text>"), art), "<text x=\"74\" y=\"720\" font-size=\"12\" fill=\"#b0c6d1\" font-family=\"sans-serif\">"), note), "</text></g>")
        })
        root.Motion373 = jsObject { obj110 ->
            obj110["ids"] = ids
            obj110["correct"] = correct
        }
        global_Motion2D18.clearBounds()
        return absent
    }))(global_window)
}
