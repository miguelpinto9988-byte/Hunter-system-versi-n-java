@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installmesh3d

import hunter.browser.*

@JsName("Math") private external var global_Math: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("parseInt") private external var global_parseInt: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Float32Array") private external var global_Float32Array: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installMesh3d() {
    dynamicValue((fun(root: dynamic): dynamic {
        var V: dynamic = absent
        var clamp: dynamic = absent
        var color: dynamic = absent
        var rotate: dynamic = absent
        var transform: dynamic = absent
        var templates: dynamic = absent
        var sphereTemplate: dynamic = absent
        var Scene: dynamic = absent
        var camera: dynamic = absent
        var project: dynamic = absent
        var light: dynamic = absent
        var lighting: dynamic = absent
        var renderers: dynamic = absent
        var shadeCSS: dynamic = absent
        var software: dynamic = absent
        var Renderer: dynamic = absent
        var render: dynamic = absent
        var release: dynamic = absent
        var releaseDetached: dynamic = absent
        color = fun(s: dynamic): dynamic {
            if (truth(global_Array.isArray(s))) {
                return s
            }
            if (truth(dynamicValue(jsRegExp("^#[\\da-f]{6}\$", "i")).test(s))) {
                return dynamicValue(jsonValue("[1,3,5]")).map((fun(i: dynamic): dynamic {
                    return jsDivide(global_parseInt(s.slice(i, jsAdd(i, 2)), 16), 255)
                }))
            }
            if (truth(dynamicValue(jsRegExp("^#[\\da-f]{3}\$", "i")).test(s))) {
                return s.slice(1).split("").map((fun(v: dynamic): dynamic {
                    return jsDivide(global_parseInt(jsAdd(v, v), 16), 255)
                }))
            }
            val nums: dynamic = dynamicValue(jsOr(global_String(s).match(jsRegExp("[\\d.]+", "g"))) { js("[]") }).map(global_Number)
            if (truth(dynamicValue(jsRegExp("^rgb", "")).test(s))) {
                return nums.slice(0, 3).map((fun(v: dynamic): dynamic {
                    return jsDivide(v, 255)
                }))
            }
            if (truth(dynamicValue(jsRegExp("^hsl", "")).test(s))) {
                var h: dynamic = absent
                var ss: dynamic = absent
                var ll: dynamic = absent
                val unpacked8: dynamic = fromIterable(nums)
                h = unpacked8[0]
                ss = unpacked8[1]
                ll = unpacked8[2]
                val sat: dynamic = jsDivide(ss, 100)
                val l: dynamic = jsDivide(ll, 100)
                val c: dynamic = jsMultiply(jsSubtract(1, global_Math.abs(jsSubtract(jsMultiply(2, l), 1))), sat)
                val x: dynamic = jsMultiply(c, jsSubtract(1, global_Math.abs(jsSubtract(jsMod(jsDivide(h, 60), 2), 1))))
                val m: dynamic = jsSubtract(l, jsDivide(c, 2))
                val parts: dynamic = (if (truth(jsLess(h, 60))) arrayOf<dynamic>(c, x, 0) else (if (truth(jsLess(h, 120))) arrayOf<dynamic>(x, c, 0) else (if (truth(jsLess(h, 180))) arrayOf<dynamic>(0, c, x) else (if (truth(jsLess(h, 240))) arrayOf<dynamic>(0, x, c) else (if (truth(jsLess(h, 300))) arrayOf<dynamic>(x, 0, c) else arrayOf<dynamic>(c, 0, x))))))
                return parts.map((fun(v: dynamic): dynamic {
                    return jsAdd(v, m)
                }))
            }
            return jsonValue("[0.3,0.85,0.95]")
        }
        rotate = fun(p: dynamic, arg11: dynamic): dynamic {
            var r: dynamic = absent
            r = definedOr(arg11) { jsonValue("[0,0,0]") }
            var x: dynamic = absent
            var y: dynamic = absent
            var z: dynamic = absent
            var a: dynamic = absent
            var b: dynamic = absent
            var c: dynamic = absent
            val unpacked12: dynamic = fromIterable(p)
            x = unpacked12[0]
            y = unpacked12[1]
            z = unpacked12[2]
            val unpacked13: dynamic = fromIterable(jsOr(r) { js("[]") })
            a = definedOr(unpacked13[0]) { 0 }
            b = definedOr(unpacked13[1]) { 0 }
            c = definedOr(unpacked13[2]) { 0 }
            var t: dynamic = jsSubtract(jsMultiply(y, global_Math.cos(a)), jsMultiply(z, global_Math.sin(a)))
            z = jsAdd(jsMultiply(y, global_Math.sin(a)), jsMultiply(z, global_Math.cos(a)))
            y = t
            t = jsAdd(jsMultiply(x, global_Math.cos(b)), jsMultiply(z, global_Math.sin(b)))
            z = jsAdd(jsMultiply((-numeric(x)), global_Math.sin(b)), jsMultiply(z, global_Math.cos(b)))
            x = t
            t = jsSubtract(jsMultiply(x, global_Math.cos(c)), jsMultiply(y, global_Math.sin(c)))
            y = jsAdd(jsMultiply(x, global_Math.sin(c)), jsMultiply(y, global_Math.cos(c)))
            return arrayOf<dynamic>(t, y, z)
        }
        transform = fun(input16: dynamic, arg17: dynamic): dynamic {
            var p: dynamic = input16
            var nodes: dynamic = absent
            nodes = definedOr(arg17) { js("[]") }
            if (true) {
                var i: dynamic = absent
                i = jsSubtract(nodes.length, 1)
                var firstIteration18 = true
                while (true) {
                    if (!firstIteration18) {
                        run {
                            val previous19: dynamic = numeric(i)
                            i = jsSubtract(previous19, 1)
                        }
                    }
                    firstIteration18 = false
                    if (!truth(jsAtLeast(i, 0))) break
                    val n: dynamic = nodes[i]
                    p = rotate(p.map((fun(v: dynamic, j: dynamic): dynamic {
                        return jsMultiply(v, (if (truth(global_Array.isArray(n.scale))) n.scale[j] else nullableOr(n.scale) { 1 }))
                    })), n.rotation)
                    p = V.add(p, jsOr(n.position) { jsonValue("[0,0,0]") })
                }
            }
            return p
        }
        sphereTemplate = fun(arg22: dynamic, arg23: dynamic): dynamic {
            var rows: dynamic = absent
            rows = definedOr(arg22) { 8 }
            var cols: dynamic = absent
            cols = definedOr(arg23) { 12 }
            val key: dynamic = jsAdd(jsAdd(rows, ":"), cols)
            if (truth(templates.has(key))) {
                return templates["get"](key)
            }
            val t: dynamic = js("[]")
            val point: dynamic = (fun(r: dynamic, c: dynamic): dynamic {
                val a: dynamic = jsMultiply(jsDivide(r, rows), global_Math.PI)
                val b: dynamic = jsMultiply(jsMultiply(jsDivide(c, cols), global_Math.PI), 2)
                return arrayOf<dynamic>(jsMultiply(global_Math.sin(a), global_Math.cos(b)), global_Math.cos(a), jsMultiply(global_Math.sin(a), global_Math.sin(b)))
            })
            if (true) {
                var r: dynamic = absent
                r = 0
                var firstIteration24 = true
                while (true) {
                    if (!firstIteration24) {
                        run {
                            val previous25: dynamic = numeric(r)
                            r = jsAdd(previous25, 1)
                        }
                    }
                    firstIteration24 = false
                    if (!truth(jsLess(r, rows))) break
                    if (true) {
                        var c: dynamic = absent
                        c = 0
                        var firstIteration26 = true
                        while (true) {
                            if (!firstIteration26) {
                                run {
                                    val previous27: dynamic = numeric(c)
                                    c = jsAdd(previous27, 1)
                                }
                            }
                            firstIteration26 = false
                            if (!truth(jsLess(c, cols))) break
                            val a: dynamic = point(r, c)
                            val b: dynamic = point(jsAdd(r, 1), c)
                            val d: dynamic = point(r, jsAdd(c, 1))
                            val e: dynamic = point(jsAdd(r, 1), jsAdd(c, 1))
                            if (truth(r)) {
                                t.push(arrayOf<dynamic>(a, d, b))
                            }
                            if (truth(jsLess(r, jsSubtract(rows, 1)))) {
                                t.push(arrayOf<dynamic>(b, d, e))
                            }
                        }
                    }
                }
            }
            templates["set"](key, t)
            return t
        }
        camera = fun(arg84: dynamic): dynamic {
            var center: dynamic = absent
            var distance: dynamic = absent
            var yaw: dynamic = absent
            var pitch: dynamic = absent
            var fov: dynamic = absent
            var aspect: dynamic = absent
            var near: dynamic = absent
            var far: dynamic = absent
            val unpacked85: dynamic = definedOr(arg84) { js("({})") }
            center = definedOr(unpacked85["center"]) { jsonValue("[0,1,0]") }
            distance = definedOr(unpacked85["distance"]) { 6 }
            yaw = definedOr(unpacked85["yaw"]) { 0.55 }
            pitch = definedOr(unpacked85["pitch"]) { 0.12 }
            fov = definedOr(unpacked85["fov"]) { 38 }
            aspect = definedOr(unpacked85["aspect"]) { 1 }
            near = definedOr(unpacked85["near"]) { 0.05 }
            far = definedOr(unpacked85["far"]) { 60 }
            val eye: dynamic = V.add(center, arrayOf<dynamic>(jsMultiply(jsMultiply(global_Math.sin(yaw), global_Math.cos(pitch)), distance), jsMultiply(global_Math.sin(pitch), distance), jsMultiply(jsMultiply(global_Math.cos(yaw), global_Math.cos(pitch)), distance)))
            val forward: dynamic = V.norm(V.sub(center, eye))
            val right: dynamic = V.norm(V.cross(forward, jsonValue("[0,1,0]")))
            val up: dynamic = V.cross(right, forward)
            return jsObject { obj86 ->
                obj86["center"] = center
                obj86["eye"] = eye
                obj86["forward"] = forward
                obj86["right"] = right
                obj86["up"] = up
                obj86["focal"] = jsDivide(1, global_Math.tan(jsDivide(jsMultiply(fov, global_Math.PI), 360)))
                obj86["aspect"] = aspect
                obj86["near"] = near
                obj86["far"] = far
                obj86["yaw"] = yaw
                obj86["pitch"] = pitch
            }
        }
        project = fun(p: dynamic, c: dynamic, width: dynamic, height: dynamic): dynamic {
            val v: dynamic = V.sub(p, c.eye)
            val z: dynamic = V.dot(v, c.forward)
            return arrayOf<dynamic>(jsDivide(jsMultiply(jsAdd(jsDivide(jsMultiply(V.dot(v, c.right), c.focal), jsMultiply(c.aspect, z)), 1), width), 2), jsDivide(jsMultiply(jsSubtract(1, jsDivide(jsMultiply(V.dot(v, c.up), c.focal), z)), height), 2), z)
        }
        lighting = fun(n: dynamic, arg91: dynamic): dynamic {
            var emission: dynamic = absent
            emission = definedOr(arg91) { 0 }
            return global_Math.min(1.35, jsAdd(jsAdd(jsAdd(0.28, jsMultiply(0.67, global_Math.max(0, V.dot(n, light)))), jsMultiply(0.16, global_Math.max(0, n[2]))), emission))
        }
        shadeCSS = fun(c: dynamic, f: dynamic): dynamic {
            return jsAdd(jsAdd("rgb(", c.map((fun(v: dynamic): dynamic {
                return global_Math.round(jsMultiply(clamp(jsMultiply(v, f), 0, 1), 255))
            })).join(",")), ")")
        }
        software = fun(ctx: dynamic, scene: dynamic, c: dynamic, width: dynamic, height: dynamic, bg: dynamic, arg96: dynamic): dynamic {
            var transparent: dynamic = absent
            transparent = definedOr(arg96) { false }
            ctx.clearRect(0, 0, width, height)
            if (truth((!truth(transparent)))) {
                ctx.fillStyle = bg
                ctx.fillRect(0, 0, width, height)
            }
            val rows: dynamic = scene.triangles.filter((fun(t: dynamic): dynamic {
                return jsAtLeast(V.dot(t.normal, V.sub(c.eye, t.points[0])), 0)
            })).map((fun(t: dynamic): dynamic {
                return jsObject { obj102 ->
                    obj102["t"] = t
                    obj102["pts"] = t.points.map((fun(p: dynamic): dynamic {
                        return project(p, c, width, height)
                    }))
                }
            })).filter((fun(x: dynamic): dynamic {
                return x.pts.every((fun(p: dynamic): dynamic {
                    return jsMore(p[2], c.near)
                }))
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract(b.pts.reduce((fun(n: dynamic, p: dynamic): dynamic {
                    return jsAdd(n, p[2])
                }), 0), a.pts.reduce((fun(n: dynamic, p: dynamic): dynamic {
                    return jsAdd(n, p[2])
                }), 0))
            }))
            ctx.globalAlpha = scene.opacity
            if (true) {
                val iterator105: dynamic = iteratorOf(rows)
                while (true) {
                    val next106: dynamic = iterator105.next()
                    if (truth(next106.done)) break
                    var t: dynamic = absent
                    var pts: dynamic = absent
                    val unpacked107: dynamic = next106.value
                    t = unpacked107["t"]
                    pts = unpacked107["pts"]
                    ctx.fillStyle = shadeCSS(t.color, lighting((if (truth(t.normals)) V.norm(t.normals.reduce((fun(a: dynamic, n: dynamic): dynamic {
                        return V.add(a, n)
                    }), jsonValue("[0,0,0]"))) else t.normal), t.emission))
                    ctx.beginPath()
                    ctx.moveTo(pts[0][0], pts[0][1])
                    ctx.lineTo(pts[1][0], pts[1][1])
                    ctx.lineTo(pts[2][0], pts[2][1])
                    ctx.closePath()
                    ctx.fill()
                }
            }
            ctx.globalAlpha = 1
            return rows.length
        }
        render = fun(canvas: dynamic, scene: dynamic, arg204: dynamic): dynamic {
            var opts: dynamic = absent
            opts = definedOr(arg204) { js("({})") }
            var r: dynamic = renderers["get"](canvas)
            if (truth((!truth(r)))) {
                r = jsConstruct(Renderer, arrayOf<dynamic>(canvas, opts.softwareOnly))
                renderers["set"](canvas, r)
            }
            r.draw(scene, opts)
            return r
        }
        release = fun(canvas: dynamic): dynamic {
            onPresent(renderers["get"](canvas)) { value207 -> value207.destroy() }
            return absent
        }
        releaseDetached = fun(): dynamic {
            if (true) {
                val iterator210: dynamic = iteratorOf(renderers)
                while (true) {
                    val next211: dynamic = iterator210.next()
                    if (truth(next211.done)) break
                    var canvas: dynamic = absent
                    var r: dynamic = absent
                    val unpacked212: dynamic = fromIterable(next211.value)
                    canvas = unpacked212[0]
                    r = unpacked212[1]
                    if (truth((canvas.isConnected === false))) {
                        r.destroy()
                    }
                }
            }
            return absent
        }
        V = jsObject { obj3 ->
            obj3["add"] = (fun(a: dynamic, b: dynamic): dynamic {
                return a.map((fun(v: dynamic, i: dynamic): dynamic {
                    return jsAdd(v, b[i])
                }))
            })
            obj3["sub"] = (fun(a: dynamic, b: dynamic): dynamic {
                return a.map((fun(v: dynamic, i: dynamic): dynamic {
                    return jsSubtract(v, b[i])
                }))
            })
            obj3["mul"] = (fun(a: dynamic, s: dynamic): dynamic {
                return a.map((fun(v: dynamic): dynamic {
                    return jsMultiply(v, s)
                }))
            })
            obj3["dot"] = (fun(a: dynamic, b: dynamic): dynamic {
                return a.reduce((fun(n: dynamic, v: dynamic, i: dynamic): dynamic {
                    return jsAdd(n, jsMultiply(v, b[i]))
                }), 0)
            })
            obj3["cross"] = (fun(a: dynamic, b: dynamic): dynamic {
                return arrayOf<dynamic>(jsSubtract(jsMultiply(a[1], b[2]), jsMultiply(a[2], b[1])), jsSubtract(jsMultiply(a[2], b[0]), jsMultiply(a[0], b[2])), jsSubtract(jsMultiply(a[0], b[1]), jsMultiply(a[1], b[0])))
            })
            obj3["norm"] = (fun(a: dynamic): dynamic {
                val n: dynamic = jsOr(run { val target5: dynamic = global_Math; invokeWith(target5.hypot, target5, jsArray { array4 ->
                    appendAll(array4, a)
                }) }) { 1 }
                return a.map((fun(v: dynamic): dynamic {
                    return jsDivide(v, n)
                }))
            })
        }
        clamp = (fun(v: dynamic, a: dynamic, b: dynamic): dynamic {
            return global_Math.max(a, global_Math.min(b, v))
        })
        templates = jsConstruct(global_Map, js("[]"))
        Scene = fun(): dynamic {
            val receiver28: dynamic = js("this")
            receiver28.triangles = js("[]")
            receiver28.nodes = js("[]")
            receiver28.opacity = 1
            return absent
        }
        Scene.prototype["group"] = fun(node: dynamic, fn: dynamic): dynamic {
            val receiver36: dynamic = js("this")
            receiver36.nodes.push(node)
            fn(receiver36)
            receiver36.nodes.pop()
            return receiver36
        }
        Scene.prototype["triangle"] = fun(a: dynamic, b: dynamic, c: dynamic, tint: dynamic, arg40: dynamic, smooth: dynamic): dynamic {
            val receiver38: dynamic = js("this")
            var emission: dynamic = absent
            emission = definedOr(arg40) { 0 }
            val points: dynamic = dynamicValue(arrayOf<dynamic>(a, b, c)).map((fun(p: dynamic): dynamic {
                return transform(p, receiver38.nodes)
            }))
            val normals: dynamic = onPresent(smooth) { value41 -> value41.map((fun(input42: dynamic): dynamic {
                var p: dynamic = input42
                if (true) {
                    var i: dynamic = absent
                    i = jsSubtract(receiver38.nodes.length, 1)
                    var firstIteration43 = true
                    while (true) {
                        if (!firstIteration43) {
                            run {
                                val previous44: dynamic = numeric(i)
                                i = jsSubtract(previous44, 1)
                            }
                        }
                        firstIteration43 = false
                        if (!truth(jsAtLeast(i, 0))) break
                        val n: dynamic = receiver38.nodes[i]
                        val scale: dynamic = (if (truth(global_Array.isArray(n.scale))) n.scale else arrayOf<dynamic>(nullableOr(n.scale) { 1 }, nullableOr(n.scale) { 1 }, nullableOr(n.scale) { 1 }))
                        p = rotate(p.map((fun(v: dynamic, j: dynamic): dynamic {
                            return jsDivide(v, scale[j])
                        })), n.rotation)
                    }
                }
                return V.norm(p)
            })) }
            val reflection: dynamic = receiver38.nodes.reduce((fun(v: dynamic, n: dynamic): dynamic {
                return jsMultiply(v, (if (truth(global_Array.isArray(n.scale))) n.scale.reduce((fun(a: dynamic, b: dynamic): dynamic {
                    return jsMultiply(a, b)
                }), 1) else global_Math.pow(nullableOr(n.scale) { 1 }, 3)))
            }), 1)
            if (truth(jsLess(reflection, 0))) {
                run {
                    val unpacked45: dynamic = fromIterable(arrayOf<dynamic>(points[2], points[1]))
                    points[1] = unpacked45[0]
                    points[2] = unpacked45[1]
                    absent
                }
                if (truth(normals)) {
                    run {
                        val unpacked46: dynamic = fromIterable(arrayOf<dynamic>(normals[2], normals[1]))
                        normals[1] = unpacked46[0]
                        normals[2] = unpacked46[1]
                        absent
                    }
                }
            }
            val normal: dynamic = V.norm(V.cross(V.sub(points[1], points[0]), V.sub(points[2], points[0])))
            if (truth(points.every((fun(p: dynamic): dynamic {
                return p.every(global_Number.isFinite)
            })))) {
                receiver38.triangles.push(jsObject { obj47 ->
                    obj47["points"] = points
                    obj47["normal"] = normal
                    obj47["normals"] = normals
                    obj47["color"] = color(tint)
                    obj47["emission"] = emission
                })
            }
            return receiver38
        }
        Scene.prototype["sphere"] = fun(center: dynamic, size: dynamic, tint: dynamic, arg50: dynamic, arg51: dynamic, arg52: dynamic): dynamic {
            val receiver48: dynamic = js("this")
            var emission: dynamic = absent
            emission = definedOr(arg50) { 0 }
            var rotation: dynamic = absent
            rotation = definedOr(arg51) { jsonValue("[0,0,0]") }
            var quality: dynamic = absent
            quality = definedOr(arg52) { 0 }
            val sizes: dynamic = (if (truth(global_Array.isArray(size))) size else arrayOf<dynamic>(size, size, size))
            if (true) {
                val iterator53: dynamic = iteratorOf(sphereTemplate((if (truth(quality)) 10 else 7), (if (truth(quality)) 16 else 10)))
                while (true) {
                    val next54: dynamic = iterator53.next()
                    if (truth(next54.done)) break
                    var tri: dynamic = absent
                    tri = next54.value
                    run { val target56: dynamic = receiver48; invokeWith(target56.triangle, target56, jsArray { array55 ->
                        appendAll(array55, tri.map((fun(p: dynamic): dynamic {
                            return V.add(center, rotate(p.map((fun(v: dynamic, i: dynamic): dynamic {
                                return jsMultiply(v, sizes[i])
                            })), rotation))
                        })))
                        array55.push(tint)
                        array55.push(emission)
                    }) }
                }
            }
            return receiver48
        }
        Scene.prototype["cylinder"] = fun(a: dynamic, b: dynamic, r1: dynamic, tint: dynamic, arg59: dynamic, arg60: dynamic, arg61: dynamic): dynamic {
            val receiver57: dynamic = js("this")
            var r2: dynamic = absent
            r2 = definedOr(arg59) { r1 }
            var emission: dynamic = absent
            emission = definedOr(arg60) { 0 }
            var sides: dynamic = absent
            sides = definedOr(arg61) { 10 }
            val axis: dynamic = V.norm(V.sub(b, a))
            val u: dynamic = V.norm(V.cross(axis, (if (truth(jsMore(global_Math.abs(axis[1]), 0.9))) jsonValue("[1,0,0]") else jsonValue("[0,1,0]"))))
            val v: dynamic = V.cross(axis, u)
            val ring: dynamic = (fun(p: dynamic, r: dynamic, k: dynamic): dynamic {
                return V.add(p, V.add(V.mul(u, jsMultiply(global_Math.cos(jsMultiply(jsMultiply(jsDivide(k, sides), 2), global_Math.PI)), r)), V.mul(v, jsMultiply(global_Math.sin(jsMultiply(jsMultiply(jsDivide(k, sides), 2), global_Math.PI)), r))))
            })
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration62 = true
                while (true) {
                    if (!firstIteration62) {
                        run {
                            val previous63: dynamic = numeric(i)
                            i = jsAdd(previous63, 1)
                        }
                    }
                    firstIteration62 = false
                    if (!truth(jsLess(i, sides))) break
                    val p: dynamic = ring(a, r1, i)
                    val q: dynamic = ring(b, r2, i)
                    val r: dynamic = ring(a, r1, jsAdd(i, 1))
                    val s: dynamic = ring(b, r2, jsAdd(i, 1))
                    receiver57.triangle(p, r, q, tint, emission)
                    receiver57.triangle(q, r, s, tint, emission)
                    receiver57.triangle(a, r, p, tint, emission)
                    receiver57.triangle(b, q, s, tint, emission)
                }
            }
            return receiver57
        }
        Scene.prototype["limb"] = fun(a: dynamic, b: dynamic, r: dynamic, tint: dynamic): dynamic {
            val receiver64: dynamic = js("this")
            receiver64.cylinder(a, b, r, tint)
            receiver64.sphere(a, r, tint)
            receiver64.sphere(b, r, tint)
            return receiver64
        }
        Scene.prototype["box"] = fun(center: dynamic, size: dynamic, tint: dynamic, arg68: dynamic, arg69: dynamic): dynamic {
            val receiver66: dynamic = js("this")
            var rotation: dynamic = absent
            rotation = definedOr(arg68) { jsonValue("[0,0,0]") }
            var emission: dynamic = absent
            emission = definedOr(arg69) { 0 }
            val pts: dynamic = dynamicValue(jsonValue("[[-1,-1,-1],[1,-1,-1],[1,1,-1],[-1,1,-1],[-1,-1,1],[1,-1,1],[1,1,1],[-1,1,1]]")).map((fun(p: dynamic): dynamic {
                return V.add(center, rotate(p.map((fun(v: dynamic, i: dynamic): dynamic {
                    return jsDivide(jsMultiply(v, size[i]), 2)
                })), rotation))
            }))
            if (true) {
                val iterator70: dynamic = iteratorOf(jsonValue("[[0,3,2,1],[4,5,6,7],[0,1,5,4],[3,7,6,2],[0,4,7,3],[1,2,6,5]]"))
                while (true) {
                    val next71: dynamic = iterator70.next()
                    if (truth(next71.done)) break
                    var f: dynamic = absent
                    f = next71.value
                    receiver66.triangle(pts[f[0]], pts[f[1]], pts[f[2]], tint, emission)
                    receiver66.triangle(pts[f[0]], pts[f[2]], pts[f[3]], tint, emission)
                }
            }
            return receiver66
        }
        Scene.prototype["prism"] = fun(input74: dynamic, depth: dynamic, tint: dynamic, arg75: dynamic): dynamic {
            val receiver72: dynamic = js("this")
            var points: dynamic = input74
            var emission: dynamic = absent
            emission = definedOr(arg75) { 0 }
            val area: dynamic = points.reduce((fun(n: dynamic, p: dynamic, i: dynamic): dynamic {
                val q: dynamic = points[jsMod(jsAdd(i, 1), points.length)]
                return jsSubtract(jsAdd(n, jsMultiply(p[0], q[1])), jsMultiply(q[0], p[1]))
            }), 0)
            if (truth(jsLess(area, 0))) {
                points = dynamicValue(jsArray { array77 ->
                    appendAll(array77, points)
                }).reverse()
            }
            val a: dynamic = points.map((fun(p: dynamic): dynamic {
                return arrayOf<dynamic>(p[0], p[1], jsSubtract(p[2], jsDivide(depth, 2)))
            }))
            val b: dynamic = points.map((fun(p: dynamic): dynamic {
                return arrayOf<dynamic>(p[0], p[1], jsAdd(p[2], jsDivide(depth, 2)))
            }))
            if (true) {
                var i: dynamic = absent
                i = 1
                var firstIteration78 = true
                while (true) {
                    if (!firstIteration78) {
                        run {
                            val previous79: dynamic = numeric(i)
                            i = jsAdd(previous79, 1)
                        }
                    }
                    firstIteration78 = false
                    if (!truth(jsLess(i, jsSubtract(points.length, 1)))) break
                    receiver72.triangle(a[0], a[jsAdd(i, 1)], a[i], tint, emission)
                    receiver72.triangle(b[0], b[i], b[jsAdd(i, 1)], tint, emission)
                }
            }
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration80 = true
                while (true) {
                    if (!firstIteration80) {
                        run {
                            val previous81: dynamic = numeric(i)
                            i = jsAdd(previous81, 1)
                        }
                    }
                    firstIteration80 = false
                    if (!truth(jsLess(i, points.length))) break
                    val j: dynamic = jsMod(jsAdd(i, 1), points.length)
                    receiver72.triangle(a[i], a[j], b[i], tint, emission)
                    receiver72.triangle(b[i], a[j], b[j], tint, emission)
                }
            }
            return receiver72
        }
        light = V.norm(jsonValue("[-0.4,0.8,0.6]"))
        val vertexShader: dynamic = "attribute vec3 aPosition;attribute vec3 aNormal;attribute vec3 aColor;attribute float aEmission;uniform mediump vec3 uEye;uniform vec3 uRight;uniform vec3 uUp;uniform vec3 uForward;uniform float uFocal;uniform float uAspect;uniform vec2 uDepth;varying vec3 vColor;varying vec3 vNormal;varying vec3 vPosition;varying float vEmission;void main(){vec3 v=aPosition-uEye;float d=dot(v,uForward);gl_Position=vec4(dot(v,uRight)*uFocal/uAspect,dot(v,uUp)*uFocal,uDepth.x*d+uDepth.y,d);vColor=aColor;vNormal=aNormal;vPosition=aPosition;vEmission=aEmission;}"
        val fragmentShader: dynamic = "precision mediump float;varying vec3 vColor;varying vec3 vNormal;varying vec3 vPosition;varying float vEmission;uniform float uOpacity;uniform mediump vec3 uEye;void main(){vec3 n=normalize(vNormal);vec3 key=normalize(vec3(.6,.85,.55));vec3 view=normalize(uEye-vPosition);float diffuse=.21+.66*max(0.0,dot(n,key))+.15*max(0.0,dot(n,normalize(vec3(-.65,.3,-.5))));float shine=.23*pow(max(0.0,dot(n,normalize(key+view))),32.0);float rim=.07*pow(1.0-max(0.0,dot(n,view)),3.0);gl_FragColor=vec4(vColor*(diffuse+vEmission)+vec3(shine+rim),uOpacity);}"
        renderers = jsConstruct(global_Map, js("[]"))
        Renderer = fun(canvas: dynamic, arg114: dynamic): dynamic {
            val receiver112: dynamic = js("this")
            var softwareOnly: dynamic = absent
            softwareOnly = definedOr(arg114) { false }
            receiver112.canvas = canvas
            receiver112.gl = dynamicValue(null)
            receiver112.error = ""
            if (truth((!truth(softwareOnly)))) {
                try {
                    val gl: dynamic = canvas.getContext("webgl", jsonValue("{\"alpha\":true,\"antialias\":true,\"depth\":true,\"preserveDrawingBuffer\":false}"))
                    if (truth(jsAnd(jsAnd(gl) { (jsTypeOf(gl.getParameter) === "function") }) { gl.getParameter(gl.VERSION) })) {
                        receiver112.gl = gl
                        receiver112["init"]()
                    }
                } catch (e: dynamic) {
                    receiver112.error = global_String(jsOr(e.message) { e })
                    receiver112.gl = dynamicValue(null)
                }
            }
            if (truth((!truth(receiver112.gl)))) {
                receiver112.ctx = canvas.getContext("2d")
            }
            return absent
        }
        Renderer.prototype["init"] = fun(): dynamic {
            val receiver129: dynamic = js("this")
            val gl: dynamic = receiver129.gl
            val shader: dynamic = (fun(kind: dynamic, src: dynamic): dynamic {
                val s: dynamic = gl.createShader(kind)
                gl.shaderSource(s, src)
                gl.compileShader(s)
                if (truth((!truth(gl.getShaderParameter(s, gl.COMPILE_STATUS))))) {
                    throw global_Error(gl.getShaderInfoLog(s))
                }
                return s
            })
            val vs: dynamic = shader(gl.VERTEX_SHADER, vertexShader)
            val fs: dynamic = shader(gl.FRAGMENT_SHADER, fragmentShader)
            receiver129.program = gl.createProgram()
            gl.attachShader(receiver129.program, vs)
            gl.attachShader(receiver129.program, fs)
            gl.linkProgram(receiver129.program)
            gl.deleteShader(vs)
            gl.deleteShader(fs)
            if (truth((!truth(gl.getProgramParameter(receiver129.program, gl.LINK_STATUS))))) {
                throw global_Error(gl.getProgramInfoLog(receiver129.program))
            }
            receiver129.buffer = gl.createBuffer()
            receiver129.attributes = dynamicValue(jsonValue("[\"aPosition\",\"aNormal\",\"aColor\",\"aEmission\"]")).map((fun(n: dynamic): dynamic {
                return gl.getAttribLocation(receiver129.program, n)
            }))
            receiver129.uniforms = global_Object.fromEntries(dynamicValue(jsonValue("[\"uEye\",\"uRight\",\"uUp\",\"uForward\",\"uFocal\",\"uAspect\",\"uDepth\",\"uOpacity\"]")).map((fun(n: dynamic): dynamic {
                return arrayOf<dynamic>(n, gl.getUniformLocation(receiver129.program, n))
            })))
            gl.enable(gl.DEPTH_TEST)
            gl.enable(gl.BLEND)
            gl.blendFunc(gl.SRC_ALPHA, gl.ONE_MINUS_SRC_ALPHA)
            receiver129.lost = (fun(e: dynamic): dynamic {
                e.preventDefault()
                receiver129.contextLost = true
                return absent
            })
            receiver129.restored = (fun(): dynamic {
                receiver129.contextLost = false
                receiver129["init"]()
                jsAnd(receiver129.lastScene) { run { val target148: dynamic = receiver129; invokeWith(target148.draw, target148, jsArray { array147 ->
                    appendAll(array147, receiver129.lastScene)
                }) } }
                return absent
            })
            run { val owner150: dynamic = receiver129.canvas; onPresent(owner150.removeEventListener) { method149 -> invokeWith(method149, owner150, arrayOf<dynamic>("webglcontextlost", receiver129.lostPrevious)) } }
            run { val owner152: dynamic = receiver129.canvas; onPresent(owner152.removeEventListener) { method151 -> invokeWith(method151, owner152, arrayOf<dynamic>("webglcontextrestored", receiver129.restoredPrevious)) } }
            receiver129.lostPrevious = receiver129.lost
            receiver129.restoredPrevious = receiver129.restored
            run { val owner158: dynamic = receiver129.canvas; onPresent(owner158.addEventListener) { method157 -> invokeWith(method157, owner158, arrayOf<dynamic>("webglcontextlost", receiver129.lost)) } }
            run { val owner160: dynamic = receiver129.canvas; onPresent(owner160.addEventListener) { method159 -> invokeWith(method159, owner160, arrayOf<dynamic>("webglcontextrestored", receiver129.restored)) } }
            return absent
        }
        Renderer.prototype["draw"] = fun(scene: dynamic, arg163: dynamic): dynamic {
            val receiver161: dynamic = js("this")
            var options: dynamic = absent
            options = definedOr(arg163) { js("({})") }
            val canvas: dynamic = receiver161.canvas
            val width: dynamic = jsOr(canvas.width) { 720 }
            val height: dynamic = jsOr(canvas.height) { 800 }
            val c: dynamic = camera(jsObject { obj164 ->
                copyFields(obj164, options)
                obj164["aspect"] = jsDivide(width, height)
            })
            val bg: dynamic = jsOr(options.background) { "#142437" }
            val gl: dynamic = receiver161.gl
            receiver161.lastScene = arrayOf<dynamic>(scene, options)
            if (truth(receiver161.contextLost)) {
                return false
            }
            if (truth(gl)) {
                gl.viewport(0, 0, width, height)
                val b: dynamic = color(bg)
                run { val target168: dynamic = gl; invokeWith(target168.clearColor, target168, jsArray { array167 ->
                    appendAll(array167, b)
                    array167.push((if (truth(options.transparent)) 0 else 1))
                }) }
                gl.clear(jsBitOr(gl.COLOR_BUFFER_BIT, gl.DEPTH_BUFFER_BIT))
                gl.useProgram(receiver161.program)
                val `data`: dynamic = jsConstruct(global_Float32Array, arrayOf<dynamic>(jsMultiply(scene.triangles.length, 30)))
                var i: dynamic = 0
                if (true) {
                    val iterator169: dynamic = iteratorOf(scene.triangles)
                    while (true) {
                        val next170: dynamic = iterator169.next()
                        if (truth(next170.done)) break
                        var t: dynamic = absent
                        t = next170.value
                        if (true) {
                            var j: dynamic = absent
                            j = 0
                            var firstIteration171 = true
                            while (true) {
                                if (!firstIteration171) {
                                    run {
                                        val previous172: dynamic = numeric(j)
                                        j = jsAdd(previous172, 1)
                                    }
                                }
                                firstIteration171 = false
                                if (!truth(jsLess(j, 3))) break
                                if (true) {
                                    val iterator173: dynamic = iteratorOf(jsArray { array175 ->
                                        appendAll(array175, t.points[j])
                                        appendAll(array175, jsOr(onPresent(t.normals) { value176 -> value176[j] }) { t.normal })
                                        appendAll(array175, t.color)
                                        array175.push(t.emission)
                                    })
                                    while (true) {
                                        val next174: dynamic = iterator173.next()
                                        if (truth(next174.done)) break
                                        var v: dynamic = absent
                                        v = next174.value
                                        `data`[run {
                                            val previous181: dynamic = numeric(i)
                                            val assigned182: dynamic = jsAdd(previous181, 1)
                                            i = assigned182
                                            previous181
                                        }] = v
                                    }
                                }
                            }
                        }
                    }
                }
                gl.bindBuffer(gl.ARRAY_BUFFER, receiver161.buffer)
                gl.bufferData(gl.ARRAY_BUFFER, `data`, gl.DYNAMIC_DRAW)
                if (true) {
                    var n: dynamic = absent
                    n = 0
                    var firstIteration183 = true
                    while (true) {
                        if (!firstIteration183) {
                            run {
                                val previous184: dynamic = numeric(n)
                                n = jsAdd(previous184, 1)
                            }
                        }
                        firstIteration183 = false
                        if (!truth(jsLess(n, 4))) break
                        gl.enableVertexAttribArray(receiver161.attributes[n])
                        gl.vertexAttribPointer(receiver161.attributes[n], (if (truth((n === 3))) 1 else 3), gl.FLOAT, false, 40, jsMultiply(n, 12))
                    }
                }
                if (true) {
                    val iterator185: dynamic = iteratorOf(jsonValue("[\"Eye\",\"Right\",\"Up\",\"Forward\"]"))
                    while (true) {
                        val next186: dynamic = iterator185.next()
                        if (truth(next186.done)) break
                        var name: dynamic = absent
                        name = next186.value
                        gl.uniform3fv(receiver161.uniforms[jsAdd("u", name)], c[name.toLowerCase()])
                    }
                }
                gl.uniform1f(receiver161.uniforms.uFocal, c.focal)
                gl.uniform1f(receiver161.uniforms.uAspect, c.aspect)
                gl.uniform2f(receiver161.uniforms.uDepth, jsDivide(jsAdd(c.far, c.near), jsSubtract(c.far, c.near)), jsDivide(jsMultiply(jsMultiply((-numeric(2)), c.far), c.near), jsSubtract(c.far, c.near)))
                gl.uniform1f(receiver161.uniforms.uOpacity, scene.opacity)
                gl.drawArrays(gl.TRIANGLES, 0, jsDivide(`data`.length, 10))
            } else {
                if (truth(receiver161.ctx)) {
                    software(receiver161.ctx, scene, c, width, height, bg, options.transparent)
                } else {
                    return false
                }
            }
            canvas.dataset.renderer = "3d"
            canvas.dataset.backend = (if (truth(gl)) "webgl" else "software-3d")
            canvas.dataset.triangles = global_String(scene.triangles.length)
            return true
        }
        Renderer.prototype["destroy"] = fun(): dynamic {
            val receiver193: dynamic = js("this")
            if (truth(receiver193.gl)) {
                receiver193.gl.deleteBuffer(receiver193.buffer)
                receiver193.gl.deleteProgram(receiver193.program)
                run { val owner196: dynamic = receiver193.canvas; onPresent(owner196.removeEventListener) { method195 -> invokeWith(method195, owner196, arrayOf<dynamic>("webglcontextlost", receiver193.lost)) } }
                run { val owner198: dynamic = receiver193.canvas; onPresent(owner198.removeEventListener) { method197 -> invokeWith(method197, owner198, arrayOf<dynamic>("webglcontextrestored", receiver193.restored)) } }
                if (truth((jsTypeOf(receiver193.gl.getExtension) === "function"))) {
                    onPresent(receiver193.gl.getExtension("WEBGL_lose_context")) { value199 -> value199.loseContext() }
                }
            }
            receiver193.lastScene = dynamicValue(null)
            renderers.delete(receiver193.canvas)
            return absent
        }
        root.Mesh3D = jsObject { obj215 ->
            obj215["V"] = V
            obj215["Scene"] = Scene
            obj215["camera"] = camera
            obj215["project"] = project
            obj215["rotate"] = rotate
            obj215["transform"] = transform
            obj215["color"] = color
            obj215["software"] = software
            obj215["render"] = render
            obj215["release"] = release
            obj215["releaseDetached"] = releaseDetached
            obj215["Renderer"] = Renderer
            obj215["vertexShader"] = vertexShader
            obj215["fragmentShader"] = fragmentShader
            obj215["renderers"] = renderers
            obj215["lighting"] = lighting
        }
        return absent
    }))(global_window)
}
