@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installanatomymodel

import hunter.browser.*

@JsName("Mesh3D") private external var global_Mesh3D: dynamic
@JsName("Exercise3D") private external var global_Exercise3D: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("AnatomySurface15") private external var global_AnatomySurface15: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installAnatomyModel() {
    dynamicValue((fun(root: dynamic): dynamic {
        var M: dynamic = absent
        var V: dynamic = absent
        var old: dynamic = absent
        var skin: dynamic = absent
        var focus: dynamic = absent
        var dark: dynamic = absent
        var templates: dynamic = absent
        var cache: dynamic = absent
        var emit: dynamic = absent
        var ellipsoid: dynamic = absent
        var bone: dynamic = absent
        var targets: dynamic = absent
        var scene: dynamic = absent
        var options: dynamic = absent
        var draw: dynamic = absent
        emit = fun(s: dynamic, vertices: dynamic, normals: dynamic, faces: dynamic, tint: dynamic): dynamic {
            val points: dynamic = vertices.map((fun(p: dynamic): dynamic {
                return M.transform(p, s.nodes)
            }))
            val ns: dynamic = normals.map((fun(input5: dynamic): dynamic {
                var p: dynamic = input5
                if (true) {
                    var i: dynamic = absent
                    i = jsSubtract(s.nodes.length, 1)
                    var firstIteration6 = true
                    while (true) {
                        if (!firstIteration6) {
                            run {
                                val previous7: dynamic = numeric(i)
                                i = jsSubtract(previous7, 1)
                            }
                        }
                        firstIteration6 = false
                        if (!truth(jsAtLeast(i, 0))) break
                        val n: dynamic = s.nodes[i]
                        val scale: dynamic = (if (truth(global_Array.isArray(n.scale))) n.scale else arrayOf<dynamic>(nullableOr(n.scale) { 1 }, nullableOr(n.scale) { 1 }, nullableOr(n.scale) { 1 }))
                        p = M.rotate(p.map((fun(v: dynamic, j: dynamic): dynamic {
                            return jsDivide(v, scale[j])
                        })), n.rotation)
                    }
                }
                return V.norm(p)
            }))
            val rgb: dynamic = (if (truth((jsTypeOf(tint) === "function"))) null else M.color(tint))
            val reflected: dynamic = jsLess(s.nodes.reduce((fun(v: dynamic, n: dynamic): dynamic {
                return jsMultiply(v, (if (truth(global_Array.isArray(n.scale))) n.scale.reduce((fun(a: dynamic, b: dynamic): dynamic {
                    return jsMultiply(a, b)
                }), 1) else global_Math.pow(nullableOr(n.scale) { 1 }, 3)))
            }), 1), 0)
            if (true) {
                val iterator8: dynamic = iteratorOf(faces)
                while (true) {
                    val next9: dynamic = iterator8.next()
                    if (truth(next9.done)) break
                    var face: dynamic = absent
                    face = next9.value
                    val ids: dynamic = (if (truth(reflected)) arrayOf<dynamic>(face[0], face[2], face[1]) else face)
                    val pts: dynamic = ids.map((fun(i: dynamic): dynamic {
                        return points[i]
                    }))
                    val nn: dynamic = ids.map((fun(i: dynamic): dynamic {
                        return ns[i]
                    }))
                    s.triangles.push(jsObject { obj10 ->
                        obj10["points"] = pts
                        obj10["normals"] = nn
                        obj10["normal"] = V.norm(V.cross(V.sub(pts[1], pts[0]), V.sub(pts[2], pts[0])))
                        obj10["color"] = jsOr(rgb) { M.color(tint(face)) }
                        obj10["emission"] = 0
                    })
                }
            }
            return absent
        }
        ellipsoid = fun(s: dynamic, c: dynamic, size: dynamic, tint: dynamic, arg13: dynamic, arg14: dynamic): dynamic {
            var rotation: dynamic = absent
            rotation = definedOr(arg13) { jsonValue("[0,0,0]") }
            var quality: dynamic = absent
            quality = definedOr(arg14) { 1 }
            val rows: dynamic = (if (truth(quality)) 10 else 7)
            val cols: dynamic = (if (truth(quality)) 16 else 10)
            val key: dynamic = jsAdd(jsAdd(rows, ":"), cols)
            val rot: dynamic = (if (truth((jsTypeOf(rotation) === "function"))) rotation else (fun(p: dynamic): dynamic {
                return M.rotate(p, rotation)
            }))
            var mesh: dynamic = templates["get"](key)
            if (truth((!truth(mesh)))) {
                val vertices: dynamic = js("[]")
                val faces: dynamic = js("[]")
                if (true) {
                    var r: dynamic = absent
                    r = 0
                    var firstIteration15 = true
                    while (true) {
                        if (!firstIteration15) {
                            run {
                                val previous16: dynamic = numeric(r)
                                r = jsAdd(previous16, 1)
                            }
                        }
                        firstIteration15 = false
                        if (!truth(jsAtMost(r, rows))) break
                        if (true) {
                            var k: dynamic = absent
                            k = 0
                            var firstIteration17 = true
                            while (true) {
                                if (!firstIteration17) {
                                    run {
                                        val previous18: dynamic = numeric(k)
                                        k = jsAdd(previous18, 1)
                                    }
                                }
                                firstIteration17 = false
                                if (!truth(jsAtMost(k, cols))) break
                                val a: dynamic = jsMultiply(jsDivide(r, rows), global_Math.PI)
                                val b: dynamic = jsMultiply(jsMultiply(jsDivide(k, cols), global_Math.PI), 2)
                                vertices.push(arrayOf<dynamic>(jsMultiply(global_Math.sin(a), global_Math.cos(b)), global_Math.cos(a), jsMultiply(global_Math.sin(a), global_Math.sin(b))))
                            }
                        }
                    }
                }
                if (true) {
                    var r: dynamic = absent
                    r = 0
                    var firstIteration19 = true
                    while (true) {
                        if (!firstIteration19) {
                            run {
                                val previous20: dynamic = numeric(r)
                                r = jsAdd(previous20, 1)
                            }
                        }
                        firstIteration19 = false
                        if (!truth(jsLess(r, rows))) break
                        if (true) {
                            var k: dynamic = absent
                            k = 0
                            var firstIteration21 = true
                            while (true) {
                                if (!firstIteration21) {
                                    run {
                                        val previous22: dynamic = numeric(k)
                                        k = jsAdd(previous22, 1)
                                    }
                                }
                                firstIteration21 = false
                                if (!truth(jsLess(k, cols))) break
                                val a: dynamic = jsAdd(jsMultiply(r, jsAdd(cols, 1)), k)
                                val b: dynamic = jsAdd(jsMultiply(jsAdd(r, 1), jsAdd(cols, 1)), k)
                                val d: dynamic = jsAdd(a, 1)
                                val e: dynamic = jsAdd(b, 1)
                                if (truth(r)) {
                                    faces.push(arrayOf<dynamic>(a, d, b))
                                }
                                if (truth(jsLess(r, jsSubtract(rows, 1)))) {
                                    faces.push(arrayOf<dynamic>(b, d, e))
                                }
                            }
                        }
                    }
                }
                mesh = jsObject { obj23 ->
                    obj23["vertices"] = vertices
                    obj23["faces"] = faces
                }
                templates["set"](key, mesh)
            }
            val vertices: dynamic = mesh.vertices.map((fun(p: dynamic): dynamic {
                return V.add(c, rot(p.map((fun(v: dynamic, i: dynamic): dynamic {
                    return jsMultiply(v, size[i])
                }))))
            }))
            val normals: dynamic = mesh.vertices.map((fun(p: dynamic): dynamic {
                return V.norm(rot(p.map((fun(v: dynamic, i: dynamic): dynamic {
                    return jsDivide(v, size[i])
                }))))
            }))
            emit(s, vertices, normals, mesh.faces, tint)
            return absent
        }
        bone = fun(s: dynamic, a: dynamic, b: dynamic, width: dynamic, depth: dynamic, tint: dynamic, arg26: dynamic, arg27: dynamic): dynamic {
            var offset: dynamic = absent
            offset = definedOr(arg26) { jsonValue("[0,0,0]") }
            var quality: dynamic = absent
            quality = definedOr(arg27) { 1 }
            val d: dynamic = V.sub(b, a)
            val len: dynamic = global_Math.max(0.0001, run { val target29: dynamic = global_Math; invokeWith(target29.hypot, target29, jsArray { array28 ->
                appendAll(array28, d)
            }) })
            val axis: dynamic = V.norm(d)
            val u: dynamic = V.norm(V.cross(axis, (if (truth(jsMore(global_Math.abs(axis[2]), 0.9))) jsonValue("[1,0,0]") else jsonValue("[0,0,1]"))))
            val v: dynamic = V.cross(u, axis)
            val rows: dynamic = (if (truth(quality)) 14 else 7)
            val cols: dynamic = (if (truth(quality)) 20 else 10)
            val vertices: dynamic = js("[]")
            val normals: dynamic = js("[]")
            val faces: dynamic = js("[]")
            val radius: dynamic = (fun(t: dynamic): dynamic {
                return jsSubtract(jsAdd(0.72, jsMultiply(0.3, global_Math.sin(jsMultiply(global_Math.PI, t)))), jsMultiply(0.19, t))
            })
            val derivative: dynamic = (fun(t: dynamic): dynamic {
                return jsSubtract(jsMultiply(jsMultiply(0.3, global_Math.PI), global_Math.cos(jsMultiply(global_Math.PI, t))), 0.19)
            })
            if (true) {
                var r: dynamic = absent
                r = 0
                var firstIteration30 = true
                while (true) {
                    if (!firstIteration30) {
                        run {
                            val previous31: dynamic = numeric(r)
                            r = jsAdd(previous31, 1)
                        }
                    }
                    firstIteration30 = false
                    if (!truth(jsAtMost(r, rows))) break
                    val t: dynamic = jsDivide(r, rows)
                    val q: dynamic = radius(t)
                    val slope: dynamic = derivative(t)
                    if (true) {
                        var k: dynamic = absent
                        k = 0
                        var firstIteration32 = true
                        while (true) {
                            if (!firstIteration32) {
                                run {
                                    val previous33: dynamic = numeric(k)
                                    k = jsAdd(previous33, 1)
                                }
                            }
                            firstIteration32 = false
                            if (!truth(jsAtMost(k, cols))) break
                            val angle: dynamic = jsMultiply(jsMultiply(jsDivide(k, cols), global_Math.PI), 2)
                            val c: dynamic = global_Math.cos(angle)
                            val z: dynamic = global_Math.sin(angle)
                            vertices.push(V.add(V.add(a, V.mul(axis, jsMultiply(t, len))), V.add(offset, V.add(V.mul(u, jsMultiply(jsMultiply(c, width), q)), V.mul(v, jsMultiply(jsMultiply(z, depth), q))))))
                            normals.push(V.norm(V.add(V.add(V.mul(u, jsDivide(c, width)), V.mul(v, jsDivide(z, depth))), V.mul(axis, jsDivide((-numeric(slope)), len)))))
                        }
                    }
                }
            }
            if (true) {
                var r: dynamic = absent
                r = 0
                var firstIteration34 = true
                while (true) {
                    if (!firstIteration34) {
                        run {
                            val previous35: dynamic = numeric(r)
                            r = jsAdd(previous35, 1)
                        }
                    }
                    firstIteration34 = false
                    if (!truth(jsLess(r, rows))) break
                    if (true) {
                        var k: dynamic = absent
                        k = 0
                        var firstIteration36 = true
                        while (true) {
                            if (!firstIteration36) {
                                run {
                                    val previous37: dynamic = numeric(k)
                                    k = jsAdd(previous37, 1)
                                }
                            }
                            firstIteration36 = false
                            if (!truth(jsLess(k, cols))) break
                            val a: dynamic = jsAdd(jsMultiply(r, jsAdd(cols, 1)), k)
                            val b: dynamic = jsAdd(jsMultiply(jsAdd(r, 1), jsAdd(cols, 1)), k)
                            val c: dynamic = jsAdd(a, 1)
                            val d: dynamic = jsAdd(b, 1)
                            faces.push(arrayOf<dynamic>(a, b, c), arrayOf<dynamic>(b, d, c))
                        }
                    }
                }
            }
            if (true) {
                val iterator38: dynamic = iteratorOf(arrayOf<dynamic>(jsonValue("[0,-1]"), arrayOf<dynamic>(rows, 1)))
                while (true) {
                    val next39: dynamic = iterator38.next()
                    if (truth(next39.done)) break
                    var r: dynamic = absent
                    var sign: dynamic = absent
                    val unpacked40: dynamic = fromIterable(next39.value)
                    r = unpacked40[0]
                    sign = unpacked40[1]
                    val n: dynamic = vertices.length
                    vertices.push(V.add(V.add(a, V.mul(axis, jsMultiply(jsDivide(r, rows), len))), offset))
                    normals.push(V.mul(axis, sign))
                    if (true) {
                        var k: dynamic = absent
                        k = 0
                        var firstIteration41 = true
                        while (true) {
                            if (!firstIteration41) {
                                run {
                                    val previous42: dynamic = numeric(k)
                                    k = jsAdd(previous42, 1)
                                }
                            }
                            firstIteration41 = false
                            if (!truth(jsLess(k, cols))) break
                            val x: dynamic = jsAdd(jsMultiply(r, jsAdd(cols, 1)), k)
                            faces.push((if (truth((sign === 1))) arrayOf<dynamic>(n, jsAdd(x, 1), x) else arrayOf<dynamic>(n, x, jsAdd(x, 1))))
                        }
                    }
                }
            }
            emit(s, vertices, normals, faces, tint)
            return absent
        }
        targets = fun(e: dynamic): dynamic {
            val text: dynamic = dynamicValue(jsAdd(jsAdd(jsAdd(jsAdd(e.name, " "), e.group), " "), jsOr(e.muscles) { "" })).toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "")
            val ids: dynamic = jsOr(e.baseId) { e.id }
            val `out`: dynamic = jsConstruct(global_Set, js("[]"))
            if (truth(dynamicValue(jsRegExp("curl|biceps", "")).test(jsAdd(jsAdd(ids, " "), text)))) {
                `out`.add("biceps")
            }
            if (truth(dynamicValue(jsRegExp("tricep|kickback", "")).test(jsAdd(jsAdd(ids, " "), text)))) {
                `out`.add("triceps")
            }
            if (truth(dynamicValue(jsRegExp("hombro|deltoid|lateral|frontlift|overhead|presscombo", "")).test(jsAdd(jsAdd(ids, " "), text)))) {
                `out`.add("shoulder")
            }
            if (truth(dynamicValue(jsRegExp("pecho|pectoral|flexiones|press de pecho", "")).test(text))) {
                `out`.add("chest")
            }
            if (truth(dynamicValue(jsRegExp("espalda|remo|dorsal", "")).test(text))) {
                `out`.add("back")
            }
            if (truth(dynamicValue(jsRegExp("abdomen|abdominal|zona media|planch|core", "")).test(text))) {
                `out`.add("core")
            }
            if (truth(dynamicValue(jsRegExp("glute|sentadilla|puente de cadera|zancada", "")).test(text))) {
                `out`.add("hip")
            }
            if (truth(dynamicValue(jsRegExp("cuadricep|muslo|sentadilla|zancada|pierna", "")).test(text))) {
                `out`.add("thigh")
            }
            if (truth(dynamicValue(jsRegExp("pantorr|talon|tobillo|salto", "")).test(text))) {
                `out`.add("calf")
            }
            return `out`
        }
        scene = fun(id: dynamic, arg47: dynamic, arg48: dynamic, arg49: dynamic): dynamic {
            var phase: dynamic = absent
            phase = definedOr(arg47) { 0 }
            var mirror: dynamic = absent
            mirror = definedOr(arg48) { false }
            var thumbnail: dynamic = absent
            thumbnail = definedOr(arg49) { false }
            var e: dynamic = absent
            var f: dynamic = absent
            var b: dynamic = absent
            var map: dynamic = absent
            val `data`: dynamic = old.pose(id, phase)
            val unpacked50: dynamic = `data`
            e = unpacked50["e"]
            f = unpacked50["f"]
            b = unpacked50["b"]
            map = unpacked50["map"]
            val p: dynamic = `data`.p.map((fun(q: dynamic): dynamic {
                return jsArray { array51 ->
                    appendAll(array51, q)
                }
            }))
            val s: dynamic = jsConstruct(M.Scene, js("[]"))
            val active: dynamic = targets(e)
            val color: dynamic = (fun(k: dynamic): dynamic {
                return (if (truth(active.has(k))) focus else skin)
            })
            val quality: dynamic = (if (truth(thumbnail)) 0 else 1)
            val ell: dynamic = (fun(c: dynamic, size: dynamic, tint: dynamic, rotation: dynamic): dynamic {
                return ellipsoid(s, c, size, tint, rotation, quality)
            })
            val seg: dynamic = (fun(a: dynamic, c: dynamic, w: dynamic, d: dynamic, tint: dynamic, offset: dynamic): dynamic {
                return bone(s, a, c, w, d, tint, offset, quality)
            })
            s.group(jsObject { obj52 ->
                obj52["scale"] = (if (truth(mirror)) jsonValue("[-1,1,1]") else 1)
            }, (fun(): dynamic {
                s.box(jsonValue("[0,-0.035,0]"), arrayOf<dynamic>(jsMultiply(b.radius, 2.4), 0.04, 1.5), "#242529")
                ell(arrayOf<dynamic>(jsDivide(jsAdd(p[8][0], p[10][0]), 2), 0.003, 0), jsonValue("[0.52,0.014,0.36]"), "#111214")
                if (true) {
                    val iterator53: dynamic = iteratorOf(f.equipment)
                    while (true) {
                        val next54: dynamic = iterator53.next()
                        if (truth(next54.done)) break
                        var kind: dynamic = absent
                        var rect: dynamic = absent
                        val unpacked55: dynamic = fromIterable(next54.value)
                        kind = unpacked55[0]
                        rect = unpacked55[1]
                        val a: dynamic = map(arrayOf<dynamic>(rect[0], rect[1]))
                        val d: dynamic = map(arrayOf<dynamic>(rect[2], rect[3]))
                        val w: dynamic = global_Math.max(0.12, jsSubtract(d[0], a[0]))
                        val h: dynamic = global_Math.max(0.08, jsSubtract(a[1], d[1]))
                        val center: dynamic = arrayOf<dynamic>(jsDivide(jsAdd(a[0], d[0]), 2), a[1], 0)
                        s.box(center, arrayOf<dynamic>(w, 0.075, 0.48), "#161719")
                        if (true) {
                            val iterator56: dynamic = iteratorOf(jsonValue("[-1,1]"))
                            while (true) {
                                val next57: dynamic = iterator56.next()
                                if (truth(next57.done)) break
                                var side: dynamic = absent
                                side = next57.value
                                if (true) {
                                    val iterator58: dynamic = iteratorOf(jsonValue("[-1,1]"))
                                    while (true) {
                                        val next59: dynamic = iterator58.next()
                                        if (truth(next59.done)) break
                                        var end: dynamic = absent
                                        end = next59.value
                                        s.cylinder(V.add(center, arrayOf<dynamic>(jsMultiply(jsMultiply(end, w), 0.38), (-numeric(0.045)), jsMultiply(side, 0.18))), V.add(center, arrayOf<dynamic>(jsMultiply(jsMultiply(end, w), 0.38), (-numeric(h)), jsMultiply(side, 0.18))), 0.018, "#5c5d61")
                                    }
                                }
                            }
                        }
                        if (truth((kind === "incline"))) {
                            s.cylinder(map(arrayOf<dynamic>(rect[0], jsSubtract(rect[3], 30)), 0), map(arrayOf<dynamic>(rect[2], rect[1]), 0), 0.065, "#17181a")
                        }
                    }
                }
                val up: dynamic = V.sub(p[1], p[2])
                val height: dynamic = run { val target61: dynamic = global_Math; invokeWith(target61.hypot, target61, jsArray { array60 ->
                    appendAll(array60, up)
                }) }
                val angle: dynamic = (-numeric(global_Math.atan2(up[0], up[1])))
                s.group(jsObject { obj62 ->
                    obj62["position"] = p[2]
                    obj62["rotation"] = arrayOf<dynamic>(0, 0, angle)
                }, (fun(): dynamic {
                    val surface: dynamic = global_AnatomySurface15
                    val vertices: dynamic = surface.vertices.map((fun(p: dynamic): dynamic {
                        return arrayOf<dynamic>(p[0], jsSubtract(jsMultiply(p[1], height), jsMultiply(jsDivide(jsOr(f.detail.spine) { 0 }, 200), global_Math.sin(jsMultiply(p[1], global_Math.PI)))), jsMultiply(p[2], jsAdd(1, jsMultiply(jsOr(f.detail.breath) { 0 }, 0.025))))
                    }))
                    val normals: dynamic = surface.normals.map((fun(p: dynamic): dynamic {
                        return V.norm(arrayOf<dynamic>(p[0], jsDivide(p[1], global_Math.max(0.1, height)), p[2]))
                    }))
                    emit(s, vertices, normals, surface.faces, (fun(face: dynamic): dynamic {
                        val r: dynamic = surface.regions[face[0]]
                        return color(r)
                    }))
                    ell(jsonValue("[0,0.035,0]"), jsonValue("[0.155,0.145,0.225]"), dark)
                    return absent
                }))
                if (true) {
                    val iterator63: dynamic = iteratorOf(jsonValue("[1,-1]"))
                    while (true) {
                        val next64: dynamic = iterator63.next()
                        if (truth(next64.done)) break
                        var side: dynamic = absent
                        side = next64.value
                        val elbow: dynamic = p[(if (truth((side === 1))) 3 else 5)]
                        val hand: dynamic = p[(if (truth((side === 1))) 4 else 6)]
                        val shoulder: dynamic = arrayOf<dynamic>(p[1][0], jsSubtract(p[1][1], 0.025), jsMultiply(side, 0.265))
                        run {
                            val target65: dynamic = elbow
                            val key66: dynamic = 2
                            target65[key66] = jsAdd(target65[key66], jsMultiply(side, 0.055))
                        }
                        run {
                            val target67: dynamic = hand
                            val key68: dynamic = 2
                            target67[key68] = jsAdd(target67[key68], jsMultiply(side, 0.055))
                        }
                        seg(shoulder, elbow, 0.093, 0.098, (if (truth(active.has("biceps"))) color("biceps") else (if (truth(active.has("triceps"))) color("triceps") else skin)))
                        ell(elbow, jsonValue("[0.064,0.07,0.068]"), skin)
                        seg(elbow, hand, 0.069, 0.074, skin)
                        val hAngle: dynamic = (-numeric(global_Math.atan2(jsSubtract(hand[0], elbow[0]), jsSubtract(hand[1], elbow[1]))))
                        val amount: dynamic = nullableOr(f.detail.amount) { 0.5 }
                        val loaded: dynamic = jsMore(f.loads.length, 0)
                        val curl: dynamic = (if (truth((f.detail.detail === "fingers"))) jsMultiply(jsSubtract(1, amount), 1.2) else (if (truth(loaded)) 0.9 else 0.08))
                        s.group(jsObject { obj69 ->
                            obj69["position"] = hand
                            obj69["rotation"] = arrayOf<dynamic>((if (truth((f.detail.detail === "forearmmob"))) jsMultiply(jsSubtract(amount, 0.5), global_Math.PI) else 0), 0, jsAdd(hAngle, (if (truth((f.detail.detail === "wristmob"))) jsMultiply(jsSubtract(amount, 0.5), 0.9) else 0)))
                        }, (fun(): dynamic {
                            ell(jsonValue("[0,0.011,0]"), jsonValue("[0.042,0.067,0.049]"), skin)
                            if (truth((!truth(thumbnail)))) {
                                if (true) {
                                    var finger: dynamic = absent
                                    finger = 0
                                    var firstIteration70 = true
                                    while (true) {
                                        if (!firstIteration70) {
                                            run {
                                                val previous71: dynamic = numeric(finger)
                                                finger = jsAdd(previous71, 1)
                                            }
                                        }
                                        firstIteration70 = false
                                        if (!truth(jsLess(finger, 4))) break
                                        val z: dynamic = jsMultiply(jsSubtract(finger, 1.5), 0.021)
                                        val a: dynamic = arrayOf<dynamic>(0, 0.066, z)
                                        val c: dynamic = arrayOf<dynamic>(jsMultiply(global_Math.sin(curl), 0.032), 0.098, z)
                                        val d: dynamic = arrayOf<dynamic>(jsAdd(c[0], jsMultiply(global_Math.sin(jsMultiply(curl, 1.7)), 0.026)), jsAdd(c[1], jsMultiply(global_Math.cos(jsMultiply(curl, 1.7)), 0.026)), z)
                                        seg(a, c, 0.01, 0.009, skin)
                                        seg(c, d, 0.008, 0.008, skin)
                                    }
                                }
                                seg(arrayOf<dynamic>(0.002, 0.026, jsMultiply(side, 0.042)), arrayOf<dynamic>(0.027, 0.062, jsMultiply(side, 0.054)), 0.014, 0.012, skin)
                            }
                            return absent
                        }))
                        val knee: dynamic = p[(if (truth((side === 1))) 7 else 9)]
                        val foot: dynamic = p[(if (truth((side === 1))) 8 else 10)]
                        val hip: dynamic = arrayOf<dynamic>(p[2][0], jsSubtract(p[2][1], 0.025), jsMultiply(side, 0.15))
                        seg(hip, knee, 0.116, 0.122, color("thigh"))
                        ell(knee, jsonValue("[0.073,0.08,0.079]"), skin)
                        seg(knee, foot, 0.072, 0.078, color("calf"))
                        seg(hip, V.add(hip, V.mul(V.sub(knee, hip), 0.32)), 0.13, 0.144, dark)
                        val footAngle: dynamic = (if (truth(f.detail.feet)) jsMultiply(jsSubtract(nullableOr(f.detail.toeAmount) { amount }, 0.5), 0.8) else 0)
                        s.group(jsObject { obj72 ->
                            obj72["position"] = foot
                            obj72["rotation"] = arrayOf<dynamic>(0, 0, footAngle)
                        }, (fun(): dynamic {
                            ell(jsonValue("[0.054,-0.014,0]"), jsonValue("[0.127,0.049,0.073]"), skin)
                            if (truth((!truth(thumbnail)))) {
                                if (true) {
                                    var toe: dynamic = absent
                                    toe = 0
                                    var firstIteration73 = true
                                    while (true) {
                                        if (!firstIteration73) {
                                            run {
                                                val previous74: dynamic = numeric(toe)
                                                toe = jsAdd(previous74, 1)
                                            }
                                        }
                                        firstIteration73 = false
                                        if (!truth(jsLess(toe, 5))) break
                                        ell(arrayOf<dynamic>(jsSubtract(0.153, jsMultiply(toe, 0.007)), (-numeric(0.015)), jsMultiply(jsSubtract(toe, 2), 0.021)), jsonValue("[0.03,0.024,0.014]"), skin)
                                    }
                                }
                            }
                            return absent
                        }))
                    }
                }
                val neckTop: dynamic = V.add(p[0], jsonValue("[0,-0.13,0]"))
                seg(p[1], neckTop, 0.056, 0.062, skin)
                if (true) {
                    val iterator75: dynamic = iteratorOf(jsonValue("[-1,1]"))
                    while (true) {
                        val next76: dynamic = iterator75.next()
                        if (truth(next76.done)) break
                        var side: dynamic = absent
                        side = next76.value
                        seg(V.add(p[1], arrayOf<dynamic>(0, 0.025, jsMultiply(side, 0.22))), V.add(neckTop, arrayOf<dynamic>((-numeric(0.005)), (-numeric(0.08)), jsMultiply(side, 0.035))), 0.055, 0.062, skin)
                    }
                }
                s.group(jsObject { obj77 ->
                    obj77["position"] = p[0]
                    obj77["rotation"] = arrayOf<dynamic>(0, jsSubtract(jsDivide(nullableOr(f.detail.face) { 10 }, 40), 0.25), (-numeric(0.055)))
                }, (fun(): dynamic {
                    ell(jsonValue("[0,0.005,0]"), jsonValue("[0.135,0.188,0.14]"), skin)
                    ell(jsonValue("[0.03,-0.1,0]"), jsonValue("[0.116,0.077,0.101]"), skin)
                    return absent
                }))
                f.loads.forEach((fun(xy: dynamic, i: dynamic): dynamic {
                    val hand: dynamic = p[(if (truth((i === 0))) 4 else 6)]
                    val q: dynamic = map(xy, hand[2])
                    s.cylinder(V.add(q, jsonValue("[0,0,-0.1]")), V.add(q, jsonValue("[0,0,0.1]")), 0.024, "#acadb0", 0.024, 0, 16)
                    if (true) {
                        val iterator78: dynamic = iteratorOf(jsonValue("[-1,1]"))
                        while (true) {
                            val next79: dynamic = iterator78.next()
                            if (truth(next79.done)) break
                            var side: dynamic = absent
                            side = next79.value
                            if (true) {
                                var plate: dynamic = absent
                                plate = 0
                                var firstIteration80 = true
                                while (true) {
                                    if (!firstIteration80) {
                                        run {
                                            val previous81: dynamic = numeric(plate)
                                            plate = jsAdd(previous81, 1)
                                        }
                                    }
                                    firstIteration80 = false
                                    if (!truth(jsLess(plate, 3))) break
                                    val z: dynamic = jsMultiply(side, jsAdd(0.09, jsMultiply(plate, 0.03)))
                                    s.cylinder(V.add(q, arrayOf<dynamic>(0, 0, jsSubtract(z, 0.014))), V.add(q, arrayOf<dynamic>(0, 0, jsAdd(z, 0.014))), jsSubtract(0.084, jsMultiply(plate, 0.012)), "#3b3c40", jsSubtract(0.084, jsMultiply(plate, 0.012)), 0, 16)
                                }
                            }
                        }
                    }
                    if (true) {
                        val iterator82: dynamic = iteratorOf(jsonValue("[-1,1]"))
                        while (true) {
                            val next83: dynamic = iterator82.next()
                            if (truth(next83.done)) break
                            var side: dynamic = absent
                            side = next83.value
                            ell(V.add(q, arrayOf<dynamic>(0, 0, jsMultiply(side, 0.178))), jsonValue("[0.027,0.027,0.012]"), "#c2c3c6")
                        }
                    }
                    return absent
                }))
                if (truth(f.detail.towel)) {
                    s.cylinder(map(f.detail.towel[0], 0.21), map(f.detail.towel[1], (-numeric(0.21))), 0.018, "#dfdfdb")
                }
                return absent
            }))
            return jsObject { obj84 ->
                obj84["scene"] = s
                copyFields(obj84, `data`)
                obj84["style"] = "anatomical-reference-recreation"
                obj84["highlighted"] = jsArray { array85 ->
                    appendAll(array85, active)
                }
                obj84["validatedAnatomy"] = false
            }
        }
        options = fun(id: dynamic, yaw: dynamic): dynamic {
            val b: dynamic = old.bounds(id)
            return jsObject { obj88 ->
                obj88["center"] = b.center
                obj88["distance"] = jsMultiply(jsDivide(b.radius, global_Math.sin(jsDivide(jsMultiply(19, global_Math.PI), 180))), 1.1)
                obj88["yaw"] = (if (truth(global_Number.isFinite(yaw))) yaw else 1.12)
                obj88["pitch"] = 0.07
                obj88["background"] = "#252527"
            }
        }
        draw = fun(canvas: dynamic, id: dynamic, arg91: dynamic, arg92: dynamic): dynamic {
            var phase: dynamic = absent
            phase = definedOr(arg91) { 0 }
            var mirror: dynamic = absent
            mirror = definedOr(arg92) { false }
            if (truth((!truth(canvas)))) {
                return absent
            }
            val thumb: dynamic = (canvas.dataset.thumbnail === "true")
            val q: dynamic = jsDivide(global_Math.round(jsMultiply(phase, 4)), 4)
            val key: dynamic = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(id, ":"), q), ":"), mirror), ":"), thumb)
            var result: dynamic = cache["get"](key)
            if (truth((!truth(result)))) {
                result = scene(id, q, mirror, thumb)
                cache["set"](key, result)
                if (truth(jsMore(cache.size, 4))) {
                    cache.delete(cache.keys().next()["value"])
                }
            }
            val yaw: dynamic = (if (truth((canvas.dataset.yaw === absent))) absent else global_Number(canvas.dataset.yaw))
            M.render(canvas, result.scene, jsObject { obj93 ->
                copyFields(obj93, options(id, yaw))
                obj93["softwareOnly"] = thumb
            })
            canvas.dataset.exercise = id
            canvas.dataset.visualStyle = result.style
            return result.model
        }
        M = global_Mesh3D
        V = M.V
        old = global_Exercise3D
        skin = "#d8d9d7"
        focus = "#f5cc68"
        dark = "#18191c"
        templates = jsConstruct(global_Map, js("[]"))
        cache = jsConstruct(global_Map, js("[]"))
        root.Exercise3D = jsObject { obj100 ->
            copyFields(obj100, old)
            obj100["scene"] = scene
            obj100["options"] = options
            obj100["draw"] = draw
            obj100["targets"] = targets
            obj100["ellipsoid"] = ellipsoid
        }
        root.AnatomyStyle15 = jsObject { obj103 ->
            obj103["skin"] = skin
            obj103["focus"] = focus
            obj103["dark"] = dark
            obj103["sourceModelRecovered"] = false
        }
        return absent
    }))(global_window)
}
