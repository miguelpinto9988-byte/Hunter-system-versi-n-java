@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installmotion2d

import hunter.browser.*

@JsName("Map") private external var global_Map: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Anatomy2D22") private external var global_Anatomy2D22: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("getComputedStyle") private external var global_getComputedStyle: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installMotion2d() {
    dynamicValue((fun(root: dynamic): dynamic {
        var bounds: dynamic = absent
        var esc: dynamic = absent
        var n: dynamic = absent
        var point: dynamic = absent
        var add: dynamic = absent
        var sub: dynamic = absent
        var unit: dynamic = absent
        var normal: dynamic = absent
        var `get`: dynamic = absent
        var dimensions: dynamic = absent
        var pose: dynamic = absent
        var shape: dynamic = absent
        var shoes22: dynamic = absent
        var knees22: dynamic = absent
        var bilateral19: dynamic = absent
        var shoulderInset19: dynamic = absent
        var contextInset21: dynamic = absent
        var svg: dynamic = absent
        var label: dynamic = absent
        var patchSVG20: dynamic = absent
        var draw: dynamic = absent
        `get` = fun(id: dynamic): dynamic {
            if (truth(root.Motion30)) {
                return root.Motion30["get"](id)
            }
            val e: dynamic = global_Engine.byId(id)
            val base: dynamic = jsOr(onPresent(e) { value8 -> value8.baseId }) { id }
            val model: dynamic = global_MotionDB[base]
            if (truth((!truth(model)))) {
                throw global_Error(jsAdd("Falta demostración 2D: ", id))
            }
            return jsObject { obj9 ->
                obj9["e"] = e
                obj9["base"] = base
                obj9["model"] = model
            }
        }
        dimensions = fun(base: dynamic, model: dynamic): dynamic {
            if (truth(bounds.has(base))) {
                return bounds["get"](base)
            }
            val pts: dynamic = model.frames.flatMap((fun(f: dynamic): dynamic {
                return jsArray { array12 ->
                    appendAll(array12, f.p)
                    appendAll(array12, f.loads)
                    appendAll(array12, f.equipment.flatMap((fun(x: dynamic): dynamic {
                        return arrayOf<dynamic>(arrayOf<dynamic>(x[1][0], x[1][1]), arrayOf<dynamic>(x[1][2], x[1][3]))
                    })))
                }
            }))
            val xs: dynamic = pts.map((fun(p: dynamic): dynamic {
                return p[0]
            }))
            val ys: dynamic = pts.map((fun(p: dynamic): dynamic {
                return p[1]
            }))
            val b: dynamic = jsObject { obj13 ->
                obj13["minX"] = jsSubtract(run { val target15: dynamic = global_Math; invokeWith(target15.min, target15, jsArray { array14 ->
                    appendAll(array14, xs)
                }) }, 62)
                obj13["maxX"] = jsAdd(run { val target17: dynamic = global_Math; invokeWith(target17.max, target17, jsArray { array16 ->
                    appendAll(array16, xs)
                }) }, 65)
                obj13["minY"] = jsSubtract(run { val target19: dynamic = global_Math; invokeWith(target19.min, target19, jsArray { array18 ->
                    appendAll(array18, ys)
                }) }, 55)
                obj13["maxY"] = jsAdd(run { val target21: dynamic = global_Math; invokeWith(target21.max, target21, jsArray { array20 ->
                    appendAll(array20, ys)
                }) }, 38)
            }
            val inset: dynamic = (!truth((!truth(onPresent(model.view21) { value22 -> value22.inset }))))
            val scale: dynamic = global_Math.min(jsDivide(570, jsSubtract(b.maxX, b.minX)), jsDivide((if (truth(inset)) 470 else 620), jsSubtract(b.maxY, b.minY)))
            b.scale = scale
            b.tx = jsSubtract(360, jsDivide(jsMultiply(jsAdd(b.minX, b.maxX), scale), 2))
            b.ty = jsSubtract((if (truth(inset)) 338 else 398), jsDivide(jsMultiply(jsAdd(b.minY, b.maxY), scale), 2))
            bounds["set"](base, b)
            return b
        }
        pose = fun(model: dynamic, phase: dynamic): dynamic {
            val frames: dynamic = model.frames
            val ph: dynamic = jsMod(jsAdd(jsMod(jsOr(global_Number(phase)) { 0 }, 48), 48), 48)
            val i: dynamic = global_Math.floor(ph)
            val t: dynamic = jsSubtract(ph, i)
            val a: dynamic = frames[i]
            val b: dynamic = frames[jsMod(jsAdd(i, 1), 48)]
            val prev: dynamic = frames[jsMod(jsAdd(i, 47), 48)]
            val next: dynamic = frames[jsMod(jsAdd(i, 2), 48)]
            val linear: dynamic = (fun(p: dynamic, q: dynamic): dynamic {
                return p.map((fun(v: dynamic, k: dynamic): dynamic {
                    return jsAdd(v, jsMultiply(jsSubtract(nullableOr(dynamicValue(jsOr(q) { p })[k]) { v }, v), t))
                }))
            })
            val bounded: dynamic = (fun(left: dynamic, a: dynamic, b: dynamic, right: dynamic): dynamic {
                if (truth((!truth(dynamicValue(arrayOf<dynamic>(left, a, b, right)).every(global_Number.isFinite))))) {
                    return jsAdd(a, jsMultiply(jsSubtract(b, a), t))
                }
                val difference: dynamic = jsSubtract(b, a)
                if (truth(jsLess(global_Math.abs(difference), 0.00001))) {
                    return a
                }
                val min: dynamic = global_Math.min(a, b)
                val max: dynamic = global_Math.max(a, b)
                val sign: dynamic = global_Math.sign(difference)
                val tangent: dynamic = (fun(x: dynamic): dynamic {
                    return (if (truth((global_Math.sign(x) === sign))) jsMultiply(sign, global_Math.min(global_Math.abs(x), jsMultiply(2, global_Math.abs(difference)))) else 0)
                })
                val m1: dynamic = tangent(jsDivide(jsSubtract(b, left), 2))
                val m2: dynamic = tangent(jsDivide(jsSubtract(right, a), 2))
                val t2: dynamic = jsMultiply(t, t)
                val t3: dynamic = jsMultiply(t2, t)
                val `value`: dynamic = jsAdd(jsAdd(jsAdd(jsMultiply(jsAdd(jsSubtract(jsMultiply(2, t3), jsMultiply(3, t2)), 1), a), jsMultiply(jsAdd(jsSubtract(t3, jsMultiply(2, t2)), t), m1)), jsMultiply(jsAdd(jsMultiply((-numeric(2)), t3), jsMultiply(3, t2)), b)), jsMultiply(jsSubtract(t3, t2), m2))
                return global_Math.max(min, global_Math.min(max, `value`))
            })
            val interpolate: dynamic = (fun(first: dynamic, second: dynamic, earlier: dynamic, later: dynamic): dynamic {
                return first.map((fun(v: dynamic, k: dynamic): dynamic {
                    return bounded(nullableOr(onPresent(earlier) { value31 -> value31[k] }) { v }, v, nullableOr(onPresent(second) { value32 -> value32[k] }) { v }, nullableOr(nullableOr(onPresent(later) { value33 -> value33[k] }) { onPresent(second) { value34 -> value34[k] } }) { v })
                }))
            })
            val detail: dynamic = jsObject { obj35 ->
                copyFields(obj35, a.detail)
            }
            if (true) {
                val iterator36: dynamic = iteratorOf(objectKeys(detail))
                while (true) {
                    val next37: dynamic = iterator36.next()
                    if (truth(next37.done)) break
                    var k: dynamic = absent
                    k = next37.value
                    if (truth(jsAnd((jsTypeOf(detail[k]) === "number")) { (jsTypeOf(onPresent(b.detail) { value38 -> value38[k] }) === "number") })) {
                        run {
                            val target39: dynamic = detail
                            val key40: dynamic = k
                            target39[key40] = jsAdd(target39[key40], jsMultiply(jsSubtract(b.detail[k], detail[k]), t))
                        }
                    }
                }
            }
            if (true) {
                val iterator41: dynamic = iteratorOf(jsonValue("[\"plate23\",\"roller30\",\"barCenter31\"]"))
                while (true) {
                    val next42: dynamic = iterator41.next()
                    if (truth(next42.done)) break
                    var k: dynamic = absent
                    k = next42.value
                    if (truth(jsAnd(global_Array.isArray(detail[k])) { global_Array.isArray(onPresent(b.detail) { value43 -> value43[k] }) })) {
                        detail[k] = linear(a.detail[k], b.detail[k])
                    }
                }
            }
            if (truth(detail.feet30)) {
                detail.feet30 = jsObject { obj48 ->
                    copyFields(obj48, detail.feet30)
                }
                if (true) {
                    val iterator49: dynamic = iteratorOf(jsonValue("[8,10]"))
                    while (true) {
                        val next50: dynamic = iterator49.next()
                        if (truth(next50.done)) break
                        var k: dynamic = absent
                        k = next50.value
                        if (truth(jsAnd(a.detail.feet30[k]) { onPresent(b.detail.feet30) { value51 -> value51[k] } })) {
                            detail.feet30[k] = linear(a.detail.feet30[k], b.detail.feet30[k])
                        }
                    }
                }
            }
            if (truth(jsAnd(detail.sidePose21) { b.detail.sidePose21 })) {
                detail.sidePose21 = a.detail.sidePose21.map((fun(p: dynamic, j: dynamic): dynamic {
                    return linear(p, b.detail.sidePose21[j])
                }))
            }
            val p: dynamic = a.p.map((fun(joint: dynamic, j: dynamic): dynamic {
                return interpolate(joint, b.p[j], prev.p[j], next.p[j])
            }))
            val loads: dynamic = a.loads.map((fun(item: dynamic, j: dynamic): dynamic {
                return interpolate(item, jsOr(b.loads[j]) { item }, jsOr(prev.loads[j]) { item }, jsOr(next.loads[j]) { item })
            }))
            val f: dynamic = jsObject { obj56 ->
                copyFields(obj56, a)
                obj56["p"] = p
                obj56["loads"] = loads
                obj56["detail"] = detail
            }
            return (if (truth(model.specific30)) f else global_Anatomy2D22.align(f, model))
        }
        shape = fun(a: dynamic, b: dynamic, wa: dynamic, wb: dynamic, fill: dynamic, arg59: dynamic): dynamic {
            var outline: dynamic = absent
            outline = definedOr(arg59) { "#142d40" }
            val u: dynamic = unit(a, b)
            val v: dynamic = normal(u)
            val a1: dynamic = add(a, v, jsDivide(wa, 2))
            val a2: dynamic = add(a, v, jsDivide((-numeric(wa)), 2))
            val b1: dynamic = add(b, v, jsDivide(wb, 2))
            val b2: dynamic = add(b, v, jsDivide((-numeric(wb)), 2))
            return ("<path d=\"M" + textOf(point(a1)) + " Q" + textOf(point(add(a, u, jsMultiply((-numeric(wa)), 0.32)))) + " " + textOf(point(a2)) + " L" + textOf(point(b2)) + " Q" + textOf(point(add(b, u, jsMultiply(wb, 0.28)))) + " " + textOf(point(b1)) + " Z\" fill=\"" + textOf(fill) + "\" stroke=\"" + textOf(outline) + "\" stroke-width=\"2.4\" stroke-linejoin=\"round\"/>")
        }
        shoes22 = fun(anatomy: dynamic, accent: dynamic): dynamic {
            return anatomy.feet.map((fun(foot: dynamic): dynamic {
                val v: dynamic = normal(foot.axis)
                val sole: dynamic = add(foot.toe, foot.axis, 2)
                val a: dynamic = add(sole, v, (-numeric(8)))
                val b: dynamic = add(sole, v, 8)
                return ("<g data-foot22=\"" + textOf(foot.index) + "\" data-foot-view22=\"" + textOf(foot.view) + "\" data-axis22=\"" + textOf(point(foot.axis)) + "\" data-heel22=\"" + textOf(point(foot.heel)) + "\" data-toe22=\"" + textOf(point(foot.toe)) + "\">" + textOf(shape(foot.heel, foot.toe, (if (truth((foot.view === "side"))) 17 else 21), (if (truth((foot.view === "side"))) 12 else 18), "#243d50")) + "<path d=\"M" + textOf(point(a)) + "L" + textOf(point(b)) + "\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\" stroke-linecap=\"round\"/></g>")
            })).join("")
        }
        knees22 = fun(anatomy: dynamic, accent: dynamic): dynamic {
            return anatomy.knees.map((fun(knee: dynamic): dynamic {
                val q: dynamic = knee.origin
                val end: dynamic = add(q, knee.axis, 9)
                return ("<g data-knee22=\"" + textOf(knee.index) + "\" data-axis22=\"" + textOf(point(knee.axis)) + "\"><circle cx=\"" + textOf(n(q[0])) + "\" cy=\"" + textOf(n(q[1])) + "\" r=\"6.2\" fill=\"#243d50\"/><path d=\"M" + textOf(point(q)) + "L" + textOf(point(end)) + "\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3.2\" stroke-linecap=\"round\"/></g>")
            })).join("")
        }
        bilateral19 = fun(e: dynamic, f: dynamic, accent: dynamic, anatomy: dynamic): dynamic {
            val p: dynamic = f.p
            val ink: dynamic = "#243d50"
            val cloth: dynamic = "#dceaf0"
            val active: dynamic = "#fb927d"
            val top: dynamic = (!truth((!truth(jsOr(jsOr(f.detail.topShoulder19) { f.detail.front19 }) { f.detail.supineTop23 }))))
            val headTop: dynamic = jsAnd(top) { (anatomy.posture !== "supine") }
            val back: dynamic = (f.detail.view21 === "rear")
            val t: dynamic = unit(p[1], p[2])
            val v: dynamic = normal(t)
            val armActive: dynamic = dynamicValue(jsonValue("[\"Pecho\",\"Brazos\",\"Hombros\",\"Espalda\"]")).includes(e.group)
            val legActive: dynamic = dynamicValue(jsonValue("[\"Piernas\",\"Cadera\"]")).includes(e.group)
            val left: dynamic = add(p[1], v, 42)
            val right: dynamic = add(p[1], v, (-numeric(42)))
            val hips: dynamic = arrayOf<dynamic>(add(p[2], v, 25), add(p[2], v, (-numeric(25))))
            var body: dynamic = jsAdd(shape(left, hips[0], 43, 30, cloth), shape(right, hips[1], 43, 30, cloth))
            run {
                body = jsAdd(body, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", left.join(",")), "Q"), p[1][0]), ","), jsSubtract(p[1][1], 12)), " "), right.join(",")), "L"), hips[1].join(",")), "Q"), p[2][0]), ","), jsAdd(p[2][1], 16)), " "), hips[0].join(",")), "Z\" fill=\""), cloth), "\" stroke=\""), ink), "\" stroke-width=\"3\"/>"))
            }
            if (true) {
                val iterator66: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(left, p[3], p[4]), arrayOf<dynamic>(right, p[5], p[6])))
                while (true) {
                    val next67: dynamic = iterator66.next()
                    if (truth(next67.done)) break
                    var a: dynamic = absent
                    var elbow: dynamic = absent
                    var hand: dynamic = absent
                    val unpacked68: dynamic = fromIterable(next67.value)
                    a = unpacked68[0]
                    elbow = unpacked68[1]
                    hand = unpacked68[2]
                    run {
                        body = jsAdd(body, jsAdd(jsAdd(shape(a, elbow, 28, 20, cloth), shape(elbow, hand, 20, 13, cloth)), (if (truth(armActive)) shape(a, elbow, 12, 8, active, "none") else "")))
                    }
                    run {
                        body = jsAdd(body, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<circle cx=\"", elbow[0]), "\" cy=\""), elbow[1]), "\" r=\"6\" fill=\""), ink), "\"/><circle cx=\""), elbow[0]), "\" cy=\""), elbow[1]), "\" r=\"2.5\" fill=\""), accent), "\"/><ellipse cx=\""), hand[0]), "\" cy=\""), hand[1]), "\" rx=\"9\" ry=\"11\" fill=\"#edc5ad\" stroke=\""), ink), "\" stroke-width=\"2\"/>"))
                    }
                }
            }
            if (true) {
                val iterator69: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(hips[0], p[7], p[8]), arrayOf<dynamic>(hips[1], p[9], p[10])))
                while (true) {
                    val next70: dynamic = iterator69.next()
                    if (truth(next70.done)) break
                    var hip: dynamic = absent
                    var knee: dynamic = absent
                    var foot: dynamic = absent
                    val unpacked71: dynamic = fromIterable(next70.value)
                    hip = unpacked71[0]
                    knee = unpacked71[1]
                    foot = unpacked71[2]
                    run {
                        body = jsAdd(body, jsAdd(jsAdd(shape(hip, knee, 34, 25, cloth), shape(knee, foot, 25, 17, cloth)), (if (truth(legActive)) shape(hip, knee, 13, 9, active, "none") else "")))
                    }
                }
            }
            run {
                body = jsAdd(body, jsAdd(knees22(anatomy, accent), shoes22(anatomy, accent)))
            }
            if (truth(dynamicValue(jsonValue("[\"Abdomen\",\"Core\"]")).includes(e.group))) {
                if (true) {
                    val iterator72: dynamic = iteratorOf(jsonValue("[0.35,0.5,0.65]"))
                    while (true) {
                        val next73: dynamic = iterator72.next()
                        if (truth(next73.done)) break
                        var k: dynamic = absent
                        k = next73.value
                        val c: dynamic = add(p[1], t, jsMultiply(run { val target75: dynamic = global_Math; invokeWith(target75.hypot, target75, jsArray { array74 ->
                            appendAll(array74, sub(p[2], p[1]))
                        }) }, k))
                        run {
                            body = jsAdd(body, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", point(add(c, v, (-numeric(16))))), "L"), point(add(c, v, 16))), "\" stroke=\""), active), "\" stroke-width=\"10\" stroke-linecap=\"round\"/>"))
                        }
                    }
                }
            }
            run {
                body = jsAdd(body, shape(add(p[1], t, (-numeric(7))), add(p[0], t, 22), 18, 18, "#ddad95"))
            }
            val angle: dynamic = jsSubtract(jsDivide(jsMultiply(global_Math.atan2(t[1], t[0]), 180), global_Math.PI), 90)
            val h: dynamic = p[0]
            run {
                body = jsAdd(body, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-head-view=\"", (if (truth(headTop)) "top" else (if (truth(back)) "rear" else "front"))), "\" transform=\"rotate("), n(angle)), " "), point(h).replace(",", " ")), ")\"><ellipse cx=\""), h[0]), "\" cy=\""), h[1]), "\" rx=\"28\" ry=\"32\" fill=\""), (if (truth(jsOr(headTop) { back })) ink else "#edc5ad")), "\" stroke=\""), ink), "\" stroke-width=\"3\"/>"))
            }
            if (truth(jsAnd((!truth(headTop))) { (!truth(back)) })) {
                run {
                    body = jsAdd(body, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", jsSubtract(h[0], 26)), ","), jsSubtract(h[1], 5)), "q0,-37 52,-8l-9,-8q-19,10 -43,16\" fill=\""), ink), "\"/><circle cx=\""), jsSubtract(h[0], 10)), "\" cy=\""), jsSubtract(h[1], 2)), "\" r=\"2.6\" fill=\""), ink), "\"/><circle cx=\""), jsAdd(h[0], 10)), "\" cy=\""), jsSubtract(h[1], 2)), "\" r=\"2.6\" fill=\""), ink), "\"/><path d=\"M"), jsSubtract(h[0], 5)), ","), jsAdd(h[1], 15)), "q5,3 10,0\" fill=\"none\" stroke=\""), ink), "\" stroke-width=\"2\"/>"))
                }
            }
            run {
                body = jsAdd(body, "</g>")
            }
            if (truth(jsOr(f.detail.topShoulder19) { back })) {
                run {
                    body = jsAdd(body, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", jsSubtract(p[1][0], 25)), ","), jsAdd(p[1][1], 23)), "q25,25 50,0M"), p[1][0]), ","), jsAdd(p[1][1], 35)), "V"), jsSubtract(p[2][1], 12)), "\" fill=\"none\" stroke=\""), active), "\" stroke-width=\"9\" stroke-linecap=\"round\"/>"))
                }
            }
            if (truth(f.detail.opposite23)) {
                var a: dynamic = absent
                var b: dynamic = absent
                val unpacked76: dynamic = fromIterable(f.detail.opposite23)
                a = unpacked76[0]
                b = unpacked76[1]
                if (true) {
                    val iterator77: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(4, a), arrayOf<dynamic>(10, a), arrayOf<dynamic>(6, b), arrayOf<dynamic>(8, b)))
                    while (true) {
                        val next78: dynamic = iterator77.next()
                        if (truth(next78.done)) break
                        var j: dynamic = absent
                        var u: dynamic = absent
                        val unpacked79: dynamic = fromIterable(next78.value)
                        j = unpacked79[0]
                        u = unpacked79[1]
                        run {
                            body = jsAdd(body, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<circle data-opposite23=\"", j), "\" cx=\""), p[j][0]), "\" cy=\""), p[j][1]), "\" r=\""), jsAdd(7, jsMultiply(7, u))), "\" opacity=\""), jsAdd(0.2, jsMultiply(0.8, u))), "\" fill=\"none\" stroke=\""), accent), "\" stroke-width=\"4\"/>"))
                        }
                    }
                }
            }
            return body
        }
        shoulderInset19 = fun(f: dynamic, accent: dynamic): dynamic {
            val lift: dynamic = jsMultiply(14, jsOr(f.detail.lift19) { 0 })
            val y: dynamic = jsSubtract(682, lift)
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-view=\"side-inset\"><rect x=\"58\" y=\"620\" width=\"282\" height=\"113\" rx=\"14\" fill=\"#102431\" stroke=\"#47606e\"/><text x=\"75\" y=\"642\" font-size=\"13\" fill=\"", accent), "\" font-family=\"sans-serif\">DE LADO · RECORRIDO CORTO</text><path d=\"M80 701H317\" stroke=\"#688594\" stroke-width=\"2\"/><ellipse cx=\"103\" cy=\"686\" rx=\"13\" ry=\"9\" fill=\"#edc5ad\"/><path d=\"M121 685H257\" stroke=\"#dceaf0\" stroke-width=\"16\" stroke-linecap=\"round\"/><path d=\"M149 682L174 "), y), "L207 "), y), "\" fill=\"none\" stroke=\"#fb927d\" stroke-width=\"9\" stroke-linecap=\"round\"/><path d=\"M278 696V660m-5 8 5-8 5 8\" fill=\"none\" stroke=\""), accent), "\" stroke-width=\"2\"/><text x=\"75\" y=\"719\" font-size=\"12\" fill=\"#b0c6d1\" font-family=\"sans-serif\">Pecho apoyado · eleva manos y codos</text></g>")
        }
        contextInset21 = fun(kind: dynamic, f: dynamic, phase: dynamic, accent: dynamic, view: dynamic): dynamic {
            val extra: dynamic = jsOr(onPresent(root.Motion30) { value84 -> run { val owner86: dynamic = value84; onPresent(owner86.inset) { method85 -> invokeWith(method85, owner86, arrayOf<dynamic>(kind, f, phase, accent)) } } }) { onPresent(root.Rig2D23) { value87 -> value87.inset(kind, f, phase, accent) } }
            if (truth(extra)) {
                return extra
            }
            var drawing: dynamic = ""
            var label: dynamic = ""
            var note: dynamic = ""
            val a: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(phase, 48), global_Math.PI), 2))), 2)
            if (truth((kind === "wall"))) {
                label = "DE LADO · CONTACTO CON LA PARED"
                note = "Espalda apoyada · pared en transparencia en la vista principal"
                drawing = jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M101 650V703\" stroke=\"#688594\" stroke-width=\"7\"/><circle cx=\"117\" cy=\"654\" r=\"9\" fill=\"#edc5ad\"/><path d=\"M118 666L120 685L124 700\" stroke=\"#dceaf0\" stroke-width=\"11\" stroke-linecap=\"round\"/><path d=\"M121 670L137 ", jsSubtract(679, jsMultiply(17, a))), "L110 "), jsSubtract(653, jsMultiply(12, a))), "\" fill=\"none\" stroke=\"#fb927d\" stroke-width=\"5\"/>")
            } else {
                if (truth(dynamicValue(jsonValue("[\"hinge\",\"arms\",\"reach\"]")).includes(kind))) {
                    label = (if (truth((kind === "hinge"))) "DE LADO · TRONCO Y APOYO" else "DE LADO · ALCANCE HACIA DELANTE")
                    note = (if (truth((kind === "hinge"))) "La vista posterior muestra la apertura · esta vista conserva el apoyo" else "Compara apertura lateral y recorrido hacia delante en las dos vistas")
                    val q: dynamic = f.detail.sidePose21
                    val b: dynamic = view.insetBounds
                    val scale: dynamic = global_Math.min(jsDivide(150, jsSubtract(b.maxX, b.minX)), jsDivide(52, jsSubtract(b.maxY, b.minY)))
                    val tx: dynamic = jsSubtract(155, jsDivide(jsMultiply(jsAdd(b.maxX, b.minX), scale), 2))
                    val ty: dynamic = jsSubtract(675, jsDivide(jsMultiply(jsAdd(b.maxY, b.minY), scale), 2))
                    var art: dynamic = ""
                    if (true) {
                        val iterator88: dynamic = iteratorOf(f.detail.sideEquipment21)
                        while (true) {
                            val next89: dynamic = iterator88.next()
                            if (truth(next89.done)) break
                            var type: dynamic = absent
                            var r: dynamic = absent
                            val unpacked90: dynamic = fromIterable(next89.value)
                            type = unpacked90[0]
                            r = unpacked90[1]
                            if (truth((type === "incline"))) {
                                val t: dynamic = unit(q[1], q[2])
                                val v: dynamic = normal(t)
                                val x: dynamic = add(q[1], v, (-numeric(44)))
                                val y: dynamic = add(q[2], v, (-numeric(44)))
                                run {
                                    art = jsAdd(art, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", point(x)), "L"), point(y)), "M"), point(x)), "V710M"), point(y)), "V710\" stroke=\"#7a9aa7\" stroke-width=\"15\" fill=\"none\"/>"))
                                }
                            } else {
                                run {
                                    art = jsAdd(art, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", r[0]), ","), r[1]), "H"), r[2]), "M"), r[0]), ","), r[1]), "V"), r[3]), "M"), r[2]), ","), r[1]), "V"), r[3]), "\" stroke=\"#7a9aa7\" stroke-width=\"15\" fill=\"none\"/>"))
                                }
                            }
                        }
                    }
                    if (true) {
                        val iterator91: dynamic = iteratorOf(jsonValue("[[1,2],[2,7],[7,8],[2,9],[9,10],[1,3],[3,4],[1,5],[5,6]]"))
                        while (true) {
                            val next92: dynamic = iterator91.next()
                            if (truth(next92.done)) break
                            var from: dynamic = absent
                            var to: dynamic = absent
                            val unpacked93: dynamic = fromIterable(next92.value)
                            from = unpacked93[0]
                            to = unpacked93[1]
                            run {
                                art = jsAdd(art, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", point(q[from])), "L"), point(q[to])), "\" stroke=\""), (if (truth(jsAnd((from === 1)) { (to !== 2) })) "#fb927d" else "#dceaf0")), "\" stroke-width=\""), (if (truth(jsAnd((from === 1)) { (to === 2) })) 32 else 17)), "\" fill=\"none\" stroke-linecap=\"round\"/>"))
                            }
                        }
                    }
                    run {
                        art = jsAdd(art, jsAdd(jsAdd(jsAdd(jsAdd("<circle cx=\"", q[0][0]), "\" cy=\""), q[0][1]), "\" r=\"26\" fill=\"#edc5ad\"/>"))
                    }
                    drawing = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-source-pose=\"true\" transform=\"translate(", tx), " "), ty), ") scale("), scale), ")\">"), art), "</g>")
                } else {
                    if (truth((kind === "hand"))) {
                        val detail: dynamic = jsOr(f.detail.detail) { view.family }
                        val amount: dynamic = nullableOr(f.detail.amount) { a }
                        label = (if (truth((detail === "forearmmob"))) "AMPLIACIÓN · GIRO DEL ANTEBRAZO" else (if (truth((detail === "fingers"))) "AMPLIACIÓN · PALMA Y DEDOS" else "DE LADO · FLEXIÓN Y EXTENSIÓN DE MUÑECA"))
                        note = "Recorrido pequeño · la ampliación permite distinguir el movimiento"
                        if (truth((detail === "fingers"))) {
                            drawing = "<path d=\"M138 696V678\" stroke=\"#dceaf0\" stroke-width=\"13\"/><ellipse cx=\"139\" cy=\"674\" rx=\"12\" ry=\"15\" fill=\"#edc5ad\" stroke=\"#243d50\" stroke-width=\"2\"/>"
                            if (true) {
                                var i: dynamic = absent
                                i = 0
                                var firstIteration94 = true
                                while (true) {
                                    if (!firstIteration94) {
                                        run {
                                            val previous95: dynamic = numeric(i)
                                            i = jsAdd(previous95, 1)
                                        }
                                    }
                                    firstIteration94 = false
                                    if (!truth(jsLess(i, 4))) break
                                    val x: dynamic = jsAdd(131, jsMultiply(i, 5))
                                    val angle: dynamic = jsMultiply(jsMultiply(jsSubtract(i, 1.5), 14), amount)
                                    val y: dynamic = jsSubtract(660, jsMultiply(jsAdd(5, jsMultiply(2, i)), amount))
                                    run {
                                        drawing = jsAdd(drawing, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", x), " 667L"), jsAdd(x, jsMultiply(angle, 0.4))), " "), y), "\" stroke=\"#edc5ad\" stroke-width=\"4\" stroke-linecap=\"round\"/>"))
                                    }
                                }
                            }
                            run {
                                drawing = jsAdd(drawing, jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M128 677L", jsSubtract(125, jsMultiply(10, amount))), " "), jsSubtract(675, jsMultiply(6, amount))), "\" stroke=\"#edc5ad\" stroke-width=\"5\" stroke-linecap=\"round\"/>"))
                            }
                        } else {
                            if (truth((detail === "forearmmob"))) {
                                val turn: dynamic = global_Math.cos(jsMultiply(global_Math.PI, amount))
                                val height: dynamic = jsAdd(3, jsMultiply(7, global_Math.abs(turn)))
                                drawing = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M105 685H146\" stroke=\"#dceaf0\" stroke-width=\"12\" stroke-linecap=\"round\"/><ellipse cx=\"163\" cy=\"685\" rx=\"18\" ry=\"", height), "\" fill=\""), (if (truth(jsAtLeast(turn, 0))) "#edc5ad" else "#d7ab92")), "\" stroke=\"#243d50\" stroke-width=\"2\"/><path d=\"M151 685l8 "), jsMultiply(12, turn)), "\" stroke=\"#edc5ad\" stroke-width=\"5\" stroke-linecap=\"round\"/><path d=\"M167 "), jsAdd(jsSubtract(685, height), 2)), "v"), jsSubtract(jsMultiply(height, 2), 4)), "\" stroke=\"#a97060\" stroke-width=\"2\"/><path d=\"M135 659q24-13 45 3m-8-2 8 2-5-7\" fill=\"none\" stroke=\""), accent), "\" stroke-width=\"2\"/>")
                            } else {
                                val angle: dynamic = jsAdd((-numeric(35)), jsMultiply(70, amount))
                                drawing = jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M99 684H149\" stroke=\"#dceaf0\" stroke-width=\"13\" stroke-linecap=\"round\"/><circle cx=\"149\" cy=\"684\" r=\"4\" fill=\"", accent), "\"/><g transform=\"rotate("), angle), " 149 684)\"><path d=\"M149 684H183\" stroke=\"#edc5ad\" stroke-width=\"10\" stroke-linecap=\"round\"/><path d=\"M160 684l10 7\" stroke=\"#edc5ad\" stroke-width=\"4\" stroke-linecap=\"round\"/></g>")
                            }
                        }
                    } else {
                        label = "DE LADO · DISTANCIA AL SUELO"
                        note = "La vista superior distingue brazos · esta vista muestra el descenso"
                        drawing = jsAdd(jsAdd(jsAdd(jsAdd("<circle cx=\"101\" cy=\"665\" r=\"9\" fill=\"#edc5ad\"/><path d=\"M114 ", jsAdd(665, jsMultiply(14, a))), "L167 681L208 697\" stroke=\"#dceaf0\" stroke-width=\"10\" stroke-linecap=\"round\"/><path d=\"M123 "), jsAdd(669, jsMultiply(14, a))), "L130 694L146 700\" stroke=\"#fb927d\" stroke-width=\"6\" fill=\"none\"/>")
                    }
                }
            }
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-view=\"side-inset\"><rect x=\"58\" y=\"620\" width=\"604\" height=\"113\" rx=\"14\" fill=\"#102431\" stroke=\"#47606e\"/><text x=\"75\" y=\"642\" font-size=\"13\" fill=\"", accent), "\" font-family=\"sans-serif\">"), label), "</text><path d=\"M80 703H230\" stroke=\"#688594\" stroke-width=\"2\"/>"), drawing), "<text x=\"249\" y=\"674\" font-size=\"12\" fill=\"#b0c6d1\" font-family=\"sans-serif\">VISTA COMPLEMENTARIA</text><text x=\"75\" y=\"719\" font-size=\"12\" fill=\"#b0c6d1\" font-family=\"sans-serif\">"), note), "</text></g>")
        }
        svg = fun(id: dynamic, arg98: dynamic, arg99: dynamic, arg100: dynamic, arg101: dynamic): dynamic {
            var phase: dynamic = absent
            phase = definedOr(arg98) { 0 }
            var mirror: dynamic = absent
            mirror = definedOr(arg99) { false }
            var accent: dynamic = absent
            accent = definedOr(arg100) { "#7ce2e9" }
            var options: dynamic = absent
            options = definedOr(arg101) { js("({})") }
            var e: dynamic = absent
            var base: dynamic = absent
            var model: dynamic = absent
            val unpacked102: dynamic = `get`(id)
            e = unpacked102["e"]
            base = unpacked102["base"]
            model = unpacked102["model"]
            val f: dynamic = pose(model, (if (truth(model.specific30)) jsOr(global_Number(phase)) { 0 } else nullableOr(onPresent(root.Rig2D23) { value103 -> value103.phase(e, jsOr(global_Number(phase)) { 0 }) }) { jsOr(global_Number(phase)) { 0 } }))
            val p: dynamic = f.p
            val d: dynamic = f.detail
            val b: dynamic = dimensions(base, model)
            val anatomy: dynamic = global_Anatomy2D22.describe(e, f, model)
            val active: dynamic = "#fb927d"
            val cloth: dynamic = "#dceaf0"
            val rear: dynamic = "#94aeba"
            val ink: dynamic = "#243d50"
            var body: dynamic = ""
            val limb: dynamic = (fun(a: dynamic, c: dynamic, w1: dynamic, w2: dynamic, color: dynamic, arg104: dynamic): dynamic {
                var muscle: dynamic = absent
                muscle = definedOr(arg104) { false }
                return jsAdd(shape(a, c, w1, w2, color), (if (truth(muscle)) shape(add(a, unit(a, c), 9), add(c, unit(a, c), (-numeric(9))), jsMultiply(w1, 0.48), jsMultiply(w2, 0.5), active, "none") else ""))
            })
            val armActive: dynamic = dynamicValue(jsonValue("[\"Pecho\",\"Brazos\",\"Hombros\",\"Espalda\"]")).includes(e.group)
            val legActive: dynamic = dynamicValue(jsonValue("[\"Piernas\",\"Cadera\"]")).includes(e.group)
            val coreActive: dynamic = dynamicValue(jsonValue("[\"Abdomen\",\"Core\"]")).includes(e.group)
            if (true) {
                val iterator105: dynamic = iteratorOf(f.equipment)
                while (true) {
                    val next106: dynamic = iterator105.next()
                    if (truth(next106.done)) break
                    var type: dynamic = absent
                    var r: dynamic = absent
                    val unpacked107: dynamic = fromIterable(next106.value)
                    type = unpacked107[0]
                    r = unpacked107[1]
                    var x: dynamic = absent
                    var y: dynamic = absent
                    var right: dynamic = absent
                    var bottom: dynamic = absent
                    if (truth((type === "bounds23"))) {
                        continue
                    }
                    if (truth((type === "incline"))) {
                        val axis: dynamic = unit(p[1], p[2])
                        val v: dynamic = normal(axis)
                        val a: dynamic = add(p[1], v, 44)
                        val c: dynamic = add(p[2], v, 44)
                        run {
                            body = jsAdd(body, ("<g data-support=\"inclined-bench\" fill=\"none\" stroke=\"#7a9aa7\" stroke-width=\"10\" stroke-linecap=\"round\"><path d=\"M" + textOf(point(a)) + "L" + textOf(point(c)) + "\"/><path d=\"M" + textOf(point(a)) + "V710M" + textOf(point(c)) + "V710\" stroke-width=\"5\"/></g>"))
                        }
                        continue
                    }
                    val unpacked108: dynamic = fromIterable(r)
                    x = unpacked108[0]
                    y = unpacked108[1]
                    right = unpacked108[2]
                    bottom = unpacked108[3]
                    val seat: dynamic = dynamicValue(jsonValue("[\"chair\",\"bench\",\"table\"]")).includes(type)
                    run {
                        body = jsAdd(body, ("<g fill=\"#1d3b4c\" stroke=\"#7a9aa7\" stroke-width=\"3\"><rect x=\"" + textOf(n(x)) + "\" y=\"" + textOf(n(y)) + "\" width=\"" + textOf(n(jsSubtract(right, x))) + "\" height=\"" + textOf(n((if (truth(seat)) 12 else global_Math.max(8, jsSubtract(bottom, y))))) + "\" rx=\"6\"/>" + textOf((if (truth(seat)) ("<path d=\"M" + textOf(jsAdd(x, 8)) + "," + textOf(jsAdd(y, 12)) + "V" + textOf(bottom) + "M" + textOf(jsSubtract(right, 8)) + "," + textOf(jsAdd(y, 12)) + "V" + textOf(bottom) + "\" fill=\"none\"/>") else "")) + "" + textOf((if (truth((type === "chair"))) ("<path d=\"M" + textOf(x) + "," + textOf(jsSubtract(y, 85)) + "v90\" fill=\"none\" stroke-width=\"6\"/>") else "")) + "</g>"))
                    }
                }
            }
            val apparatus21: dynamic = jsAdd(body, jsOr(jsOr(onPresent(root.Motion30) { value109 -> value109.extras(f, accent) }) { onPresent(root.Rig2D23) { value110 -> value110.extras(f, accent) } }) { "" })
            body = apparatus21
            run {
                body = jsAdd(body, jsAdd(jsAdd(jsAdd(limb(p[2], p[9], 37, 26, rear, legActive), limb(p[9], p[10], 25, 17, rear, legActive)), limb(p[1], p[5], 29, 20, rear, armActive)), limb(p[5], p[6], 20, 14, rear, armActive)))
            }
            val torso: dynamic = unit(p[1], p[2])
            val v: dynamic = normal(torso)
            val s1: dynamic = add(p[1], v, 30)
            val s2: dynamic = add(p[1], v, (-numeric(30)))
            val h1: dynamic = add(p[2], v, 20)
            val h2: dynamic = add(p[2], v, (-numeric(20)))
            val mid: dynamic = add(p[1], torso, jsMultiply(run { val target112: dynamic = global_Math; invokeWith(target112.hypot, target112, jsArray { array111 ->
                appendAll(array111, sub(p[2], p[1]))
            }) }, 0.42))
            run {
                body = jsAdd(body, ("<path d=\"M" + textOf(point(s1)) + " Q" + textOf(point(add(add(mid, v, 26), arrayOf<dynamic>(0, (-numeric(jsOr(d.spineCurve23) { 0 })))))) + " " + textOf(point(h1)) + " Q" + textOf(point(add(p[2], torso, 20))) + " " + textOf(point(h2)) + " Q" + textOf(point(add(add(mid, v, (-numeric(26))), arrayOf<dynamic>(0, (-numeric(jsOr(d.spineCurve23) { 0 })))))) + " " + textOf(point(s2)) + " Q" + textOf(point(add(p[1], torso, (-numeric(13))))) + " " + textOf(point(s1)) + " Z\" fill=\"" + textOf(cloth) + "\" stroke=\"" + textOf(ink) + "\" stroke-width=\"3\"/>"))
            }
            run {
                body = jsAdd(body, ("<path data-chest22=\"true\" data-axis22=\"" + textOf(point(anatomy.chestAxis)) + "\" d=\"M" + textOf(point(add(add(p[1], torso, 28), anatomy.chestAxis, 31))) + "L" + textOf(point(add(mid, anatomy.chestAxis, 31))) + "\" fill=\"none\" stroke=\"#afc6d2\" stroke-width=\"3\" stroke-linecap=\"round\"/>"))
            }
            if (truth(coreActive)) {
                if (true) {
                    val iterator113: dynamic = iteratorOf(jsonValue("[0.36,0.51,0.66]"))
                    while (true) {
                        val next114: dynamic = iterator113.next()
                        if (truth(next114.done)) break
                        var k: dynamic = absent
                        k = next114.value
                        val c: dynamic = add(p[1], torso, jsMultiply(run { val target116: dynamic = global_Math; invokeWith(target116.hypot, target116, jsArray { array115 ->
                            appendAll(array115, sub(p[2], p[1]))
                        }) }, k))
                        run {
                            body = jsAdd(body, ("<path d=\"M" + textOf(point(add(c, v, (-numeric(17))))) + "L" + textOf(point(add(c, v, 17))) + "\" stroke=\"" + textOf(active) + "\" stroke-width=\"13\" stroke-linecap=\"round\"/>"))
                        }
                    }
                }
            }
            if (truth(jsAnd(armActive) { dynamicValue(jsonValue("[\"Pecho\",\"Hombros\",\"Espalda\"]")).includes(e.group) })) {
                run {
                    body = jsAdd(body, shape(add(p[1], torso, 20), add(mid, torso, (-numeric(9))), 55, 43, active, "none"))
                }
            }
            run {
                body = jsAdd(body, ("<path d=\"M" + textOf(point(add(p[2], v, (-numeric(26))))) + "L" + textOf(point(add(p[2], v, 26))) + "\" stroke=\"" + textOf(ink) + "\" stroke-width=\"11\" stroke-linecap=\"round\"/>"))
            }
            run {
                body = jsAdd(body, jsAdd(jsAdd(jsAdd(limb(p[2], p[7], 39, 27, cloth, legActive), limb(p[7], p[8], 27, 17, cloth, legActive)), limb(p[1], p[3], 30, 20, cloth, armActive)), limb(p[3], p[4], 21, 14, cloth, armActive)))
            }
            if (true) {
                val iterator117: dynamic = iteratorOf(jsonValue("[3,5]"))
                while (true) {
                    val next118: dynamic = iterator117.next()
                    if (truth(next118.done)) break
                    var i: dynamic = absent
                    i = next118.value
                    run {
                        body = jsAdd(body, ("<circle cx=\"" + textOf(n(p[i][0])) + "\" cy=\"" + textOf(n(p[i][1])) + "\" r=\"6.8\" fill=\"" + textOf(ink) + "\"/><circle cx=\"" + textOf(n(p[i][0])) + "\" cy=\"" + textOf(n(p[i][1])) + "\" r=\"2.4\" fill=\"" + textOf(accent) + "\"/>"))
                    }
                }
            }
            run {
                body = jsAdd(body, knees22(anatomy, accent))
            }
            val head: dynamic = p[0]
            val sign: dynamic = (-numeric(anatomy.handedness))
            val headAngle: dynamic = jsSubtract(jsDivide(jsMultiply(global_Math.atan2(jsSubtract(p[1][1], head[1]), jsSubtract(p[1][0], head[0])), 180), global_Math.PI), 90)
            run {
                body = jsAdd(body, shape(add(p[1], torso, (-numeric(9))), add(head, torso, 20), 18, 18, "#ddad95"))
            }
            run {
                body = jsAdd(body, ("<g data-head-view=\"side\" data-axis22=\"" + textOf(point(jsOr(anatomy.faceAxis) { anatomy.chestAxis })) + "\" transform=\"rotate(" + textOf(n(headAngle)) + " " + textOf(n(head[0])) + " " + textOf(n(head[1])) + ")\"><ellipse cx=\"" + textOf(n(head[0])) + "\" cy=\"" + textOf(n(head[1])) + "\" rx=\"26\" ry=\"31\" fill=\"#edc5ad\" stroke=\"" + textOf(ink) + "\" stroke-width=\"2.5\"/><g transform=\"" + textOf((if (truth(jsLess(sign, 0))) jsAdd(jsAdd("translate(", n(jsMultiply(head[0], 2))), " 0) scale(-1 1)") else "")) + "\"><path d=\"M" + textOf(n(jsSubtract(head[0], 26))) + "," + textOf(n(jsSubtract(head[1], 3))) + "Q" + textOf(n(jsSubtract(head[0], 30))) + "," + textOf(n(jsSubtract(head[1], 35))) + " " + textOf(n(jsAdd(head[0], 2))) + "," + textOf(n(jsSubtract(head[1], 33))) + "Q" + textOf(n(jsAdd(head[0], 27))) + "," + textOf(n(jsSubtract(head[1], 31))) + " " + textOf(n(jsAdd(head[0], 26))) + "," + textOf(n(jsSubtract(head[1], 11))) + "L" + textOf(n(jsAdd(head[0], 9))) + "," + textOf(n(jsSubtract(head[1], 19))) + "Q" + textOf(n(jsSubtract(head[0], 8))) + "," + textOf(n(jsSubtract(head[1], 10))) + " " + textOf(n(jsSubtract(head[0], 26))) + "," + textOf(n(jsSubtract(head[1], 3))) + "\" fill=\"" + textOf(ink) + "\"/><path d=\"M" + textOf(n(jsAdd(head[0], 17))) + "," + textOf(n(jsSubtract(head[1], 2))) + "l10,8l-8,3\" fill=\"#edc5ad\" stroke=\"" + textOf(ink) + "\" stroke-width=\"1.7\"/><circle cx=\"" + textOf(n(jsAdd(head[0], 10))) + "\" cy=\"" + textOf(n(jsSubtract(head[1], 3))) + "\" r=\"2.5\" fill=\"" + textOf(ink) + "\"/></g></g>"))
            }
            if (true) {
                val iterator119: dynamic = iteratorOf(jsonValue("[4,6]"))
                while (true) {
                    val next120: dynamic = iterator119.next()
                    if (truth(next120.done)) break
                    var i: dynamic = absent
                    i = next120.value
                    val q: dynamic = p[i]
                    run {
                        body = jsAdd(body, ("<ellipse cx=\"" + textOf(n(q[0])) + "\" cy=\"" + textOf(n(jsAdd(q[1], 3))) + "\" rx=\"9\" ry=\"12\" fill=\"#edc5ad\" stroke=\"" + textOf(ink) + "\" stroke-width=\"2\"/>"))
                    }
                }
            }
            run {
                body = jsAdd(body, shoes22(anatomy, accent))
            }
            if (truth(jsOr(jsOr(jsOr(d.topShoulder19) { d.front19 }) { d.supineTop23 }) { dynamicValue(jsonValue("[\"front\",\"rear\",\"lying\"]")).includes(onPresent(model.view21) { value121 -> value121.primary }) })) {
                body = jsAdd(apparatus21, bilateral19(e, f, accent, anatomy))
            }
            if (true) {
                val iterator122: dynamic = iteratorOf(f.loads)
                while (true) {
                    val next123: dynamic = iterator122.next()
                    if (truth(next123.done)) break
                    var q: dynamic = absent
                    q = next123.value
                    run {
                        body = jsAdd(body, ("<g transform=\"translate(" + textOf(point(q)) + ")\" fill=\"" + textOf(ink) + "\" stroke=\"#7298aa\" stroke-width=\"2\"><rect x=\"-20\" y=\"-4\" width=\"40\" height=\"8\" rx=\"4\"/><rect x=\"-28\" y=\"-16\" width=\"14\" height=\"32\" rx=\"4\"/><rect x=\"14\" y=\"-16\" width=\"14\" height=\"32\" rx=\"4\"/><path d=\"M-21 -10v20M21 -10v20\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/></g>"))
                    }
                }
            }
            run {
                body = jsAdd(body, jsOr(onPresent(root.Rig2D23) { value124 -> value124.loads(f, model.view21.primary, accent) }) { "" })
            }
            val phaseName: dynamic = label(id, phase)
            val ground: dynamic = global_Math.min(745, jsAdd(jsMultiply(nullableOr(d.floor23) { jsSubtract(b.maxY, 26) }, b.scale), b.ty))
            val transform: dynamic = ("translate(" + textOf(n(b.tx)) + " " + textOf(n(b.ty)) + ") scale(" + textOf(n(b.scale)) + ")")
            val breath30: dynamic = (if (truth(model.hold)) ("<g data-hold30=\"breathing\"><text x=\"510\" y=\"593\" font-family=\"sans-serif\" font-size=\"12\" fill=\"#9db7c4\">RESPIRA</text><circle cx=\"617\" cy=\"588\" r=\"" + textOf(n(jsAdd(9, jsDivide(jsMultiply(6, jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(phase, 48), global_Math.PI), 2)))), 2)))) + "\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"2\"/></g>") else "")
            val mir: dynamic = (if (truth(((!truth((!truth(mirror)))) !== (!truth((!truth(onPresent(model.view21) { value125 -> value125.mirror }))))))) "translate(720 0) scale(-1 1)" else "")
            val view: dynamic = model.view21
            val caption: dynamic = (if (truth(view)) ("<text x=\"360\" y=\"86\" text-anchor=\"middle\" fill=\"#b0c6d1\" font-size=\"13\" font-family=\"sans-serif\">VISTA " + textOf(esc(view.label.toUpperCase())) + "" + textOf((if (truth(d.topShoulder19)) jsAdd(" · ", esc(d.letter19)) else "")) + "</text>") else "")
            val insetArt: dynamic = (if (truth((onPresent(view) { value126 -> value126.inset } === "prone"))) shoulderInset19(f, accent) else (if (truth(onPresent(view) { value127 -> value127.inset })) contextInset21(view.inset, f, phase, accent, view) else ""))
            val detailArt: dynamic = nullableOr(onPresent(root.Motion31) { value128 -> value128.reflowInset(insetArt) }) { insetArt }
            val height: dynamic = (if (truth(jsAnd(onPresent(view) { value129 -> value129.inset }) { root.Motion31 })) 1000 else 800)
            return ("<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 720 " + textOf(height) + "\" role=\"img\" aria-label=\"" + textOf(esc(e.name)) + " · demostración 2D\" data-renderer=\"2d\" data-base=\"" + textOf(esc(base)) + "\" data-view=\"" + textOf(jsOr(onPresent(view) { value130 -> value130.primary }) { "side" }) + "\" data-posture22=\"" + textOf(anatomy.posture) + "\" data-default-mirror=\"" + textOf((!truth((!truth(onPresent(view) { value131 -> value131.mirror }))))) + "\"><title>" + textOf(esc(e.name)) + "</title><rect width=\"720\" height=\"" + textOf(height) + "\" rx=\"30\" fill=\"#101f2a\"/><circle cx=\"360\" cy=\"370\" r=\"285\" fill=\"#152e3d\"/><circle cx=\"360\" cy=\"370\" r=\"230\" fill=\"none\" stroke=\"#284352\" stroke-width=\"1\"/><path d=\"M62 115v-42h42M616 73h42v42M62 685v42h42M616 727h42v-42\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\" opacity=\".7\"/>" + textOf(caption) + "" + textOf((if (truth((onPresent(view) { value132 -> value132.primary } === "top"))) "" else ("<path d=\"M100 " + textOf(n(ground)) + "H620\" stroke=\"#47606e\" stroke-width=\"2\"/>"))) + "<g transform=\"" + textOf(mir) + "\"><g data-anatomy22=\"true\" transform=\"" + textOf(transform) + "\">" + textOf(body) + "</g></g>" + textOf(breath30) + "" + textOf(detailArt) + "" + textOf((if (truth(options.thumbnail)) "" else ("<text x=\"360\" y=\"50\" text-anchor=\"middle\" fill=\"" + textOf(accent) + "\" font-size=\"15\" letter-spacing=\"3\" font-family=\"sans-serif\">" + textOf(phaseName) + "</text><text x=\"360\" y=\"" + textOf(jsSubtract(height, 28)) + "\" text-anchor=\"middle\" fill=\"#b0c6d1\" font-size=\"15\" font-family=\"sans-serif\">ILUSTRACIÓN 2D · " + textOf(esc(e.group.toUpperCase())) + "</text>"))) + "</svg>")
        }
        label = fun(id: dynamic, phase: dynamic): dynamic {
            var e: dynamic = absent
            var model: dynamic = absent
            val unpacked135: dynamic = `get`(id)
            e = unpacked135["e"]
            model = unpacked135["model"]
            val t: dynamic = jsDivide(jsMod(jsAdd(jsMod(jsOr(global_Number(phase)) { 0 }, 48), 48), 48), 48)
            val c: dynamic = onPresent(root.Motion30) { value136 -> value136.cadence(e) }
            val f: dynamic = pose(model, (if (truth(model.specific30)) phase else nullableOr(onPresent(root.Rig2D23) { value137 -> value137.phase(e, phase) }) { phase }))
            if (truth(jsAnd(jsAnd(onPresent(c) { value138 -> value138.pause }) { jsAtLeast(t, c.pauseStart) }) { jsLess(t, c.pauseEnd) })) {
                val hold: dynamic = jsMultiply(jsSubtract(c.pauseEnd, c.pauseStart), c.seconds)
                return jsAdd(jsAdd("PAUSA · ", global_Number(hold.toFixed(1)).toLocaleString("es-PE")), " SEGUNDOS")
            }
            if (truth(onPresent(c) { value139 -> value139.slow })) {
                val descent: dynamic = dynamicValue(jsRegExp("descenso|bajada", "")).test(e.name.toLowerCase())
                if (truth(jsAnd(descent) { jsLess(t, c.slowSplit) })) {
                    return jsAdd(jsAdd("DESCENSO · ", global_Number(dynamicValue(jsMultiply(c.slowSplit, c.seconds)).toFixed(1)).toLocaleString("es-PE")), " SEGUNDOS")
                }
                if (truth(jsAnd((!truth(descent))) { jsAtLeast(t, c.slowSplit) })) {
                    return jsAdd(jsAdd("REGRESO · ", global_Number(dynamicValue(jsMultiply(jsSubtract(1, c.slowSplit), c.seconds)).toFixed(1)).toLocaleString("es-PE")), " SEGUNDOS")
                }
                return (if (truth(descent)) "REGRESO CONTROLADO" else "EJECUCIÓN CONTROLADA")
            }
            if (truth((onPresent(model.view21) { value140 -> value140.inset } === "hand"))) {
                return (if (truth((model.view21.family === "fingers"))) "ABRE Y CIERRA CON SUAVIDAD" else (if (truth((model.view21.family === "forearmmob"))) "GIRO CONTROLADO DEL ANTEBRAZO" else "FLEXIÓN Y EXTENSIÓN SUAVES"))
            }
            return (if (truth(model.hold)) "POSTURA SOSTENIDA · RESPIRA" else jsOr(jsOr(f.detail.stage18) { onPresent(f.detail.jumpPhase18) { value141 -> value141.toUpperCase() } }) { (if (truth(jsLess(t, 0.5))) "EJECUCIÓN CONTROLADA" else "REGRESO CONTROLADO") })
        }
        patchSVG20 = fun(current: dynamic, next: dynamic): dynamic {
            val names: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(next.getAttributeNames()))
            if (true) {
                val iterator144: dynamic = iteratorOf(current.getAttributeNames())
                while (true) {
                    val next145: dynamic = iterator144.next()
                    if (truth(next145.done)) break
                    var name: dynamic = absent
                    name = next145.value
                    if (truth((!truth(names.has(name))))) {
                        current.removeAttribute(name)
                    }
                }
            }
            if (true) {
                val iterator146: dynamic = iteratorOf(names)
                while (true) {
                    val next147: dynamic = iterator146.next()
                    if (truth(next147.done)) break
                    var name: dynamic = absent
                    name = next147.value
                    if (truth((current.getAttribute(name) !== next.getAttribute(name)))) {
                        current.setAttribute(name, next.getAttribute(name))
                    }
                }
            }
            val old: dynamic = global_Array.from(current.children)
            val children: dynamic = global_Array.from(next.children)
            if (truth((!truth(children.length)))) {
                if (truth((current.textContent !== next.textContent))) {
                    current.textContent = next.textContent
                }
                return absent
            }
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration150 = true
                while (true) {
                    if (!firstIteration150) {
                        run {
                            val previous151: dynamic = numeric(i)
                            i = jsAdd(previous151, 1)
                        }
                    }
                    firstIteration150 = false
                    if (!truth(jsLess(i, children.length))) break
                    val child: dynamic = children[i]
                    if (truth((!truth(old[i])))) {
                        current.append(child.cloneNode(true))
                    } else {
                        if (truth((old[i].tagName !== child.tagName))) {
                            old[i].replaceWith(child.cloneNode(true))
                        } else {
                            patchSVG20(old[i], child)
                        }
                    }
                }
            }
            if (true) {
                var i: dynamic = absent
                i = children.length
                var firstIteration152 = true
                while (true) {
                    if (!firstIteration152) {
                        run {
                            val previous153: dynamic = numeric(i)
                            i = jsAdd(previous153, 1)
                        }
                    }
                    firstIteration152 = false
                    if (!truth(jsLess(i, old.length))) break
                    old[i].remove()
                }
            }
            return absent
        }
        draw = fun(canvas: dynamic, id: dynamic, arg156: dynamic, arg157: dynamic): dynamic {
            var phase: dynamic = absent
            phase = definedOr(arg156) { 0 }
            var mirror: dynamic = absent
            mirror = definedOr(arg157) { false }
            var model: dynamic = absent
            var base: dynamic = absent
            if (truth((!truth(canvas)))) {
                return absent
            }
            val unpacked158: dynamic = `get`(id)
            model = unpacked158["model"]
            base = unpacked158["base"]
            canvas.hidden = true
            canvas.dataset.renderer = "2d"
            canvas.dataset.base = base
            var host: dynamic = canvas.nextElementSibling
            if (truth((!truth(onPresent(host) { value165 -> value165.classList.contains("motion-svg18") })))) {
                host = global_document.createElement("div")
                host.className = "motion-svg18"
                canvas.after(host)
            }
            host.dataset.exercise = id
            host.dataset.phase = global_String(phase)
            val accent: dynamic = jsOr(global_getComputedStyle(global_document.documentElement).getPropertyValue("--accent").trim()) { "#7ce2e9" }
            val template: dynamic = global_document.createElement("template")
            template.innerHTML = svg(id, phase, mirror, accent)
            val next: dynamic = template.content.firstElementChild
            val current: dynamic = host.firstElementChild
            val detail: dynamic = next.querySelector("[data-detail-layout31]")
            var detailSVG: dynamic = dynamicValue(null)
            if (truth(detail)) {
                detailSVG = next.cloneNode(false)
                detailSVG.setAttribute("viewBox", "58 620 604 288")
                detailSVG.setAttribute("class", "detail-svg31")
                detailSVG.setAttribute("aria-label", jsAdd("Detalle animado de ", global_Engine.byId(id).name))
                detailSVG.append(detail.cloneNode(true))
                detail.remove()
                next.setAttribute("viewBox", "0 0 720 610")
                onPresent(next.querySelector("text[y=\"972\"]")) { value175 -> value175.remove() }
            }
            host.dataset.hasDetail31 = global_String((!truth((!truth(detailSVG)))))
            if (truth((onPresent(current) { value178 -> value178.tagName } === onPresent(next) { value179 -> value179.tagName }))) {
                patchSVG20(current, next)
            } else {
                host.replaceChildren(next)
            }
            if (truth(detailSVG)) {
                val currentDetail: dynamic = host.children[1]
                if (truth(currentDetail)) {
                    patchSVG20(currentDetail, detailSVG)
                } else {
                    host.append(detailSVG)
                }
            } else {
                onPresent(host.children[1]) { value180 -> value180.remove() }
            }
            return model
        }
        bounds = jsConstruct(global_Map, js("[]"))
        esc = (fun(s: dynamic): dynamic {
            return global_String(s).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        n = (fun(v: dynamic): dynamic {
            return global_Number(v).toFixed(2)
        })
        point = (fun(p: dynamic): dynamic {
            return p.map(n).join(",")
        })
        add = (fun(a: dynamic, b: dynamic, arg3: dynamic): dynamic {
            var k: dynamic = absent
            k = definedOr(arg3) { 1 }
            return arrayOf<dynamic>(jsAdd(a[0], jsMultiply(b[0], k)), jsAdd(a[1], jsMultiply(b[1], k)))
        })
        sub = (fun(a: dynamic, b: dynamic): dynamic {
            return arrayOf<dynamic>(jsSubtract(a[0], b[0]), jsSubtract(a[1], b[1]))
        })
        unit = (fun(a: dynamic, b: dynamic): dynamic {
            val d: dynamic = sub(b, a)
            val l: dynamic = jsOr(run { val target5: dynamic = global_Math; invokeWith(target5.hypot, target5, jsArray { array4 ->
                appendAll(array4, d)
            }) }) { 1 }
            return d.map((fun(x: dynamic): dynamic {
                return jsDivide(x, l)
            }))
        })
        normal = (fun(v: dynamic): dynamic {
            return arrayOf<dynamic>((-numeric(v[1])), v[0])
        })
        root.Motion2D18 = jsObject { obj183 ->
            obj183["svg"] = svg
            obj183["draw"] = draw
            obj183["pose"] = pose
            obj183["get"] = `get`
            obj183["dimensions"] = dimensions
            obj183["label"] = label
            obj183["clearBounds"] = (fun(): dynamic {
                return bounds.clear()
            })
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
