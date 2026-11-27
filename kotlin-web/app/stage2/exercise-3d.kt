@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installexercise3d

import hunter.browser.*

@JsName("Mesh3D") private external var global_Mesh3D: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("getComputedStyle") private external var global_getComputedStyle: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("interpolateMotion") private external var global_interpolateMotion: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installExercise3d() {
    dynamicValue((fun(root: dynamic): dynamic {
        var M: dynamic = absent
        var V: dynamic = absent
        var cache: dynamic = absent
        var accent: dynamic = absent
        var bounds: dynamic = absent
        var pose: dynamic = absent
        var scene: dynamic = absent
        var options: dynamic = absent
        var draw: dynamic = absent
        bounds = fun(id: dynamic): dynamic {
            val e: dynamic = global_Engine.byId(id)
            val key: dynamic = jsOr(e.baseId) { id }
            if (truth(cache.has(key))) {
                return cache["get"](key)
            }
            val m: dynamic = global_MotionDB[key]
            val all: dynamic = m.frames.flatMap((fun(f: dynamic): dynamic {
                return jsArray { array5 ->
                    appendAll(array5, f.p)
                    appendAll(array5, f.loads)
                    appendAll(array5, f.equipment.flatMap((fun(x: dynamic): dynamic {
                        return arrayOf<dynamic>(arrayOf<dynamic>(x[1][0], x[1][1]), arrayOf<dynamic>(x[1][2], x[1][3]))
                    })))
                }
            }))
            val xs: dynamic = all.map((fun(p: dynamic): dynamic {
                return p[0]
            }))
            val ys: dynamic = all.map((fun(p: dynamic): dynamic {
                return p[1]
            }))
            val min: dynamic = jsSubtract(run { val target7: dynamic = global_Math; invokeWith(target7.min, target7, jsArray { array6 ->
                appendAll(array6, xs)
            }) }, 48)
            val max: dynamic = jsAdd(run { val target9: dynamic = global_Math; invokeWith(target9.max, target9, jsArray { array8 ->
                appendAll(array8, xs)
            }) }, 48)
            val ground: dynamic = jsAdd(run { val target11: dynamic = global_Math; invokeWith(target11.max, target11, jsArray { array10 ->
                appendAll(array10, ys)
            }) }, 35)
            val top: dynamic = jsSubtract(run { val target13: dynamic = global_Math; invokeWith(target13.min, target13, jsArray { array12 ->
                appendAll(array12, ys)
            }) }, 45)
            val result: dynamic = jsObject { obj14 ->
                obj14["cx"] = jsDivide(jsAdd(min, max), 2)
                obj14["ground"] = ground
                obj14["center"] = arrayOf<dynamic>(0, jsDivide(jsSubtract(ground, top), 400), 0)
                obj14["radius"] = jsAdd(global_Math.max(jsDivide(jsSubtract(max, min), 400), jsDivide(jsSubtract(ground, top), 400), 0.8), 0.26)
            }
            cache["set"](key, result)
            return result
        }
        pose = fun(id: dynamic, phase: dynamic): dynamic {
            val e: dynamic = global_Engine.byId(id)
            if (truth((!truth(e)))) {
                throw global_Error(jsAdd("Ejercicio desconocido: ", id))
            }
            val model: dynamic = global_MotionDB[jsOr(e.baseId) { id }]
            if (truth((!truth(model)))) {
                throw global_Error(jsAdd("Falta modelo: ", id))
            }
            val f: dynamic = global_interpolateMotion(model, phase)
            val b: dynamic = bounds(id)
            val map: dynamic = (fun(p: dynamic, arg17: dynamic): dynamic {
                var z: dynamic = absent
                z = definedOr(arg17) { 0 }
                return arrayOf<dynamic>(jsDivide(jsSubtract(p[0], b.cx), 200), jsDivide(jsSubtract(b.ground, p[1]), 200), z)
            })
            val p: dynamic = f.p.map((fun(x: dynamic): dynamic {
                return map(x)
            }))
            val armWidth: dynamic = (if (truth(e.seated)) 0.19 else 0.18)
            val stance: dynamic = (if (truth(dynamicValue(jsRegExp("sumo|wide", "")).test(e.id))) 0.22 else 0.13)
            val amount: dynamic = nullableOr(f.detail.amount) { jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(phase, 48), global_Math.PI), 2))), 2) }
            if (true) {
                val iterator18: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, armWidth), arrayOf<dynamic>(4, armWidth), arrayOf<dynamic>(5, (-numeric(armWidth))), arrayOf<dynamic>(6, (-numeric(armWidth))), arrayOf<dynamic>(7, stance), arrayOf<dynamic>(8, stance), arrayOf<dynamic>(9, (-numeric(stance))), arrayOf<dynamic>(10, (-numeric(stance)))))
                while (true) {
                    val next19: dynamic = iterator18.next()
                    if (truth(next19.done)) break
                    var j: dynamic = absent
                    var z: dynamic = absent
                    val unpacked20: dynamic = fromIterable(next19.value)
                    j = unpacked20[0]
                    z = unpacked20[1]
                    p[j][2] = z
                }
            }
            if (truth(dynamicValue(jsRegExp("circle|armworld|shoulder", "")).test(e.id))) {
                val q: dynamic = jsMultiply(jsMultiply(jsDivide(phase, 48), global_Math.PI), 2)
                run {
                    val target23: dynamic = p[4]
                    val key24: dynamic = 2
                    target23[key24] = jsAdd(target23[key24], jsMultiply(0.12, global_Math.sin(q)))
                }
                run {
                    val target25: dynamic = p[6]
                    val key26: dynamic = 2
                    target25[key26] = jsSubtract(target25[key26], jsMultiply(0.12, global_Math.sin(q)))
                }
            }
            if (truth(dynamicValue(jsRegExp("fly|lateral|armsback", "")).test(e.id))) {
                run {
                    val target27: dynamic = p[4]
                    val key28: dynamic = 2
                    target27[key28] = jsAdd(target27[key28], jsMultiply(0.16, amount))
                }
                run {
                    val target29: dynamic = p[6]
                    val key30: dynamic = 2
                    target29[key30] = jsSubtract(target29[key30], jsMultiply(0.16, amount))
                }
            }
            if (truth(dynamicValue(jsRegExp("turn|thoracic", "")).test(e.id))) {
                val a: dynamic = jsMultiply(global_Math.sin(jsMultiply(jsMultiply(jsDivide(phase, 48), global_Math.PI), 2)), 0.22)
                run {
                    val target31: dynamic = p[3]
                    val key32: dynamic = 2
                    target31[key32] = jsAdd(target31[key32], a)
                }
                run {
                    val target33: dynamic = p[4]
                    val key34: dynamic = 2
                    target33[key34] = jsAdd(target33[key34], a)
                }
                run {
                    val target35: dynamic = p[5]
                    val key36: dynamic = 2
                    target35[key36] = jsSubtract(target35[key36], a)
                }
                run {
                    val target37: dynamic = p[6]
                    val key38: dynamic = 2
                    target37[key38] = jsSubtract(target37[key38], a)
                }
            }
            if (truth(dynamicValue(jsRegExp("skater|shuffle|stepcross|linehop", "")).test(e.id))) {
                run {
                    val target39: dynamic = p[8]
                    val key40: dynamic = 2
                    target39[key40] = jsAdd(target39[key40], jsMultiply(0.06, global_Math.sin(jsMultiply(jsMultiply(jsDivide(phase, 48), global_Math.PI), 2))))
                }
                run {
                    val target41: dynamic = p[10]
                    val key42: dynamic = 2
                    target41[key42] = jsSubtract(target41[key42], jsMultiply(0.06, global_Math.sin(jsMultiply(jsMultiply(jsDivide(phase, 48), global_Math.PI), 2))))
                }
            }
            return jsObject { obj43 ->
                obj43["id"] = id
                obj43["e"] = e
                obj43["model"] = model
                obj43["f"] = f
                obj43["p"] = p
                obj43["b"] = b
                obj43["map"] = map
                obj43["authoredDepth"] = true
            }
        }
        scene = fun(id: dynamic, arg46: dynamic, arg47: dynamic): dynamic {
            var phase: dynamic = absent
            phase = definedOr(arg46) { 0 }
            var mirror: dynamic = absent
            mirror = definedOr(arg47) { false }
            var e: dynamic = absent
            var f: dynamic = absent
            var p: dynamic = absent
            var b: dynamic = absent
            var map: dynamic = absent
            val `data`: dynamic = pose(id, phase)
            val unpacked48: dynamic = `data`
            e = unpacked48["e"]
            f = unpacked48["f"]
            p = unpacked48["p"]
            b = unpacked48["b"]
            map = unpacked48["map"]
            val s: dynamic = jsConstruct(M.Scene, js("[]"))
            val cyan: dynamic = accent()
            val dark: dynamic = "#18283e"
            val joint: dynamic = "#96afc8"
            s.group(jsObject { obj49 ->
                obj49["scale"] = (if (truth(mirror)) jsonValue("[-1,1,1]") else 1)
            }, (fun(): dynamic {
                s.box(jsonValue("[0,-0.025,0]"), arrayOf<dynamic>(jsMultiply(b.radius, 2.5), 0.045, 1.4), "#1b3146")
                if (true) {
                    var i: dynamic = absent
                    i = (-numeric(3))
                    var firstIteration50 = true
                    while (true) {
                        if (!firstIteration50) {
                            run {
                                val previous51: dynamic = numeric(i)
                                i = jsAdd(previous51, 1)
                            }
                        }
                        firstIteration50 = false
                        if (!truth(jsAtMost(i, 3))) break
                        s.box(arrayOf<dynamic>(jsMultiply(i, 0.28), 0, 0), jsonValue("[0.008,0.004,1.4]"), "#375168")
                        s.box(arrayOf<dynamic>(0, 0, jsMultiply(i, 0.22)), arrayOf<dynamic>(jsMultiply(b.radius, 2.5), 0.004, 0.008), "#375168")
                    }
                }
                s.sphere(arrayOf<dynamic>(jsDivide(jsAdd(p[8][0], p[10][0]), 2), 0.015, 0), jsonValue("[0.5,0.01,0.33]"), "#0a101c")
                if (true) {
                    val iterator52: dynamic = iteratorOf(f.equipment)
                    while (true) {
                        val next53: dynamic = iterator52.next()
                        if (truth(next53.done)) break
                        var type: dynamic = absent
                        var rect: dynamic = absent
                        val unpacked54: dynamic = fromIterable(next53.value)
                        type = unpacked54[0]
                        rect = unpacked54[1]
                        var x: dynamic = absent
                        var y: dynamic = absent
                        var r: dynamic = absent
                        var d: dynamic = absent
                        val unpacked55: dynamic = fromIterable(rect)
                        x = unpacked55[0]
                        y = unpacked55[1]
                        r = unpacked55[2]
                        d = unpacked55[3]
                        val a: dynamic = map(arrayOf<dynamic>(x, y))
                        val c: dynamic = map(arrayOf<dynamic>(r, d))
                        val center: dynamic = arrayOf<dynamic>(jsDivide(jsAdd(a[0], c[0]), 2), jsDivide(jsAdd(a[1], c[1]), 2), (-numeric(0.04)))
                        s.box(center, arrayOf<dynamic>(global_Math.max(0.06, jsSubtract(c[0], a[0])), global_Math.max(0.035, jsSubtract(a[1], c[1])), 0.45), "#344962")
                        if (truth((type === "incline"))) {
                            s.cylinder(map(arrayOf<dynamic>(x, jsSubtract(d, 30)), 0), map(arrayOf<dynamic>(r, y), 0), 0.045, "#7996af")
                        }
                    }
                }
                if (true) {
                    val iterator56: dynamic = iteratorOf(jsonValue("[[1,5,-0.18],[5,6,0],[2,9,-0.13],[9,10,0]]"))
                    while (true) {
                        val next57: dynamic = iterator56.next()
                        if (truth(next57.done)) break
                        var a: dynamic = absent
                        var c: dynamic = absent
                        var z: dynamic = absent
                        val unpacked58: dynamic = fromIterable(next57.value)
                        a = unpacked58[0]
                        c = unpacked58[1]
                        z = unpacked58[2]
                        s.limb((if (truth((a === 1))) arrayOf<dynamic>(p[a][0], p[a][1], z) else (if (truth((a === 2))) arrayOf<dynamic>(p[a][0], p[a][1], z) else p[a])), p[c], 0.06, dark)
                    }
                }
                val mid: dynamic = V.mul(V.add(p[1], p[2]), 0.5)
                if (truth((f.detail.spine !== absent))) {
                    val control: dynamic = V.add(mid, arrayOf<dynamic>(0, jsDivide((-numeric(f.detail.spine)), 200), 0))
                    var last: dynamic = p[1]
                    if (true) {
                        var j: dynamic = absent
                        j = 1
                        var firstIteration59 = true
                        while (true) {
                            if (!firstIteration59) {
                                run {
                                    val previous60: dynamic = numeric(j)
                                    j = jsAdd(previous60, 1)
                                }
                            }
                            firstIteration59 = false
                            if (!truth(jsAtMost(j, 8))) break
                            val a: dynamic = jsDivide(j, 8)
                            val next: dynamic = p[1].map((fun(v: dynamic, k: dynamic): dynamic {
                                return jsAdd(jsAdd(jsMultiply(jsPower(jsSubtract(1, a), 2), v), jsMultiply(jsMultiply(jsMultiply(2, jsSubtract(1, a)), a), control[k])), jsMultiply(jsMultiply(a, a), p[2][k]))
                            }))
                            s.limb(last, next, 0.105, cyan)
                            last = next
                        }
                    }
                } else {
                    s.limb(p[1], p[2], 0.12, dark)
                    s.sphere(mid, arrayOf<dynamic>(0.125, global_Math.max(0.16, jsMultiply(run { val target62: dynamic = global_Math; invokeWith(target62.hypot, target62, jsArray { array61 ->
                        appendAll(array61, V.sub(p[1], p[2]))
                    }) }, 0.44)), 0.185), cyan, 0, arrayOf<dynamic>(0, 0, global_Math.atan2(jsSubtract(p[2][0], p[1][0]), jsSubtract(p[1][1], p[2][1]))))
                }
                s.sphere(p[2], jsonValue("[0.13,0.095,0.175]"), dark)
                if (true) {
                    val iterator63: dynamic = iteratorOf(jsonValue("[[0,1,0],[1,3,0.18],[3,4,0],[2,7,0.13],[7,8,0]]"))
                    while (true) {
                        val next64: dynamic = iterator63.next()
                        if (truth(next64.done)) break
                        var a: dynamic = absent
                        var c: dynamic = absent
                        var z: dynamic = absent
                        val unpacked65: dynamic = fromIterable(next64.value)
                        a = unpacked65[0]
                        c = unpacked65[1]
                        z = unpacked65[2]
                        s.limb((if (truth((a === 1))) arrayOf<dynamic>(p[a][0], p[a][1], z) else (if (truth((a === 2))) arrayOf<dynamic>(p[a][0], p[a][1], z) else p[a])), p[c], (if (truth((a === 0))) 0.055 else 0.065), cyan)
                    }
                }
                if (true) {
                    val iterator66: dynamic = iteratorOf(jsonValue("[3,5,7,9]"))
                    while (true) {
                        val next67: dynamic = iterator66.next()
                        if (truth(next67.done)) break
                        var j: dynamic = absent
                        j = next67.value
                        s.sphere(p[j], 0.072, joint)
                    }
                }
                s.group(jsObject { obj68 ->
                    obj68["position"] = p[0]
                    obj68["rotation"] = arrayOf<dynamic>(0, (if (truth((f.detail.face === absent))) 0 else jsDivide(jsSubtract(f.detail.face, 10), 25)), (-numeric(0.08)))
                }, (fun(): dynamic {
                    s.sphere(jsonValue("[0,0,0]"), jsonValue("[0.145,0.18,0.13]"), "#e2eef5")
                    s.sphere(jsonValue("[0.12,-0.015,0]"), jsonValue("[0.052,0.035,0.045]"), "#c0d6e2")
                    if (true) {
                        val iterator69: dynamic = iteratorOf(jsonValue("[-0.075,0.075]"))
                        while (true) {
                            val next70: dynamic = iterator69.next()
                            if (truth(next70.done)) break
                            var z: dynamic = absent
                            z = next70.value
                            s.sphere(arrayOf<dynamic>(0.118, 0.05, z), jsonValue("[0.015,0.021,0.018]"), dark)
                        }
                    }
                    return absent
                }))
                if (true) {
                    val iterator71: dynamic = iteratorOf(jsonValue("[8,10]"))
                    while (true) {
                        val next72: dynamic = iterator71.next()
                        if (truth(next72.done)) break
                        var j: dynamic = absent
                        j = next72.value
                        val angle: dynamic = (if (truth(f.detail.feet)) jsMultiply(jsSubtract(nullableOr(nullableOr(f.detail.toeAmount) { f.detail.amount }) { 0.5 }, 0.5), 0.85) else 0)
                        s.group(jsObject { obj73 ->
                            obj73["position"] = p[j]
                            obj73["rotation"] = arrayOf<dynamic>(0, 0, angle)
                        }, (fun(): dynamic {
                            return s.box(jsonValue("[0.055,-0.02,0]"), jsonValue("[0.21,0.085,0.11]"), dark)
                        }))
                    }
                }
                f.loads.forEach((fun(xy: dynamic, i: dynamic): dynamic {
                    val hand: dynamic = (if (truth((i === 0))) 4 else 6)
                    val q: dynamic = map(xy, p[hand][2])
                    s.cylinder(V.add(q, jsonValue("[0,0,-0.09]")), V.add(q, jsonValue("[0,0,0.09]")), 0.018, "#c2cedc")
                    if (true) {
                        val iterator74: dynamic = iteratorOf(jsonValue("[-0.11,0.11]"))
                        while (true) {
                            val next75: dynamic = iterator74.next()
                            if (truth(next75.done)) break
                            var z: dynamic = absent
                            z = next75.value
                            s.cylinder(V.add(q, arrayOf<dynamic>(0, 0, jsSubtract(z, 0.025))), V.add(q, arrayOf<dynamic>(0, 0, jsAdd(z, 0.025))), 0.07, "#e9c96e")
                        }
                    }
                    return absent
                }))
                if (truth(f.detail.towel)) {
                    s.cylinder(map(f.detail.towel[0], 0.18), map(f.detail.towel[1], (-numeric(0.18))), 0.018, "#e9c96e")
                }
                if (truth((f.detail.breath !== absent))) {
                    s.sphere(V.add(mid, jsonValue("[0.03,0,0.2]")), jsAdd(0.025, jsMultiply(0.025, f.detail.breath)), cyan, 0.6)
                }
                if (truth(f.detail.detail)) {
                    val q: dynamic = jsOr(f.detail.amount) { 0 }
                    val hand: dynamic = p[4]
                    val rotation: dynamic = (if (truth((f.detail.detail === "forearmmob"))) arrayOf<dynamic>(jsMultiply(jsSubtract(q, 0.5), global_Math.PI), 0, 0) else (if (truth((f.detail.detail === "fingers"))) jsonValue("[0,0,0]") else arrayOf<dynamic>(0, 0, jsMultiply(jsSubtract(q, 0.5), 0.9))))
                    s.group(jsObject { obj76 ->
                        obj76["position"] = hand
                        obj76["rotation"] = rotation
                        obj76["scale"] = 1.6
                    }, (fun(): dynamic {
                        s.sphere(jsonValue("[0.025,0,0]"), jsonValue("[0.048,0.018,0.05]"), cyan)
                        if (true) {
                            var j: dynamic = absent
                            j = 0
                            var firstIteration77 = true
                            while (true) {
                                if (!firstIteration77) {
                                    run {
                                        val previous78: dynamic = numeric(j)
                                        j = jsAdd(previous78, 1)
                                    }
                                }
                                firstIteration77 = false
                                if (!truth(jsLess(j, 5))) break
                                val a: dynamic = arrayOf<dynamic>(0.045, 0, jsMultiply(jsSubtract(j, 2), 0.018))
                                val curl: dynamic = (if (truth((f.detail.detail === "fingers"))) jsMultiply(jsSubtract(1, q), 0.06) else 0)
                                val c: dynamic = V.add(a, arrayOf<dynamic>(0.05, (-numeric(curl)), 0))
                                s.cylinder(a, c, 0.007, cyan)
                                s.cylinder(c, V.add(c, arrayOf<dynamic>(jsSubtract(0.025, jsDivide(curl, 3)), jsDivide((-numeric(curl)), 2), 0)), 0.006, cyan)
                            }
                        }
                        return absent
                    }))
                }
                return absent
            }))
            return jsObject { obj79 ->
                obj79["scene"] = s
                copyFields(obj79, `data`)
            }
        }
        options = fun(id: dynamic, yaw: dynamic): dynamic {
            val b: dynamic = bounds(id)
            return jsObject { obj82 ->
                obj82["center"] = b.center
                obj82["distance"] = jsMultiply(jsDivide(b.radius, global_Math.sin(jsDivide(jsMultiply(19, global_Math.PI), 180))), 1.24)
                obj82["yaw"] = (if (truth(global_Number.isFinite(yaw))) yaw else 0.45)
                obj82["pitch"] = 0.1
                obj82["background"] = jsOr(global_getComputedStyle(global_document.documentElement).getPropertyValue("--input").trim()) { "#142437" }
            }
        }
        draw = fun(canvas: dynamic, id: dynamic, arg85: dynamic, arg86: dynamic): dynamic {
            var phase: dynamic = absent
            phase = definedOr(arg85) { 0 }
            var mirror: dynamic = absent
            mirror = definedOr(arg86) { false }
            if (truth((!truth(canvas)))) {
                return absent
            }
            val result: dynamic = scene(id, phase, mirror)
            val yaw: dynamic = global_Number(canvas.dataset.yaw)
            M.render(canvas, result.scene, jsObject { obj87 ->
                copyFields(obj87, options(id, (if (truth(jsAnd(global_Number.isFinite(yaw)) { (canvas.dataset.yaw !== absent) })) yaw else absent)))
                obj87["softwareOnly"] = (canvas.dataset.thumbnail === "true")
            })
            canvas.dataset.exercise = id
            return result.model
        }
        M = global_Mesh3D
        V = M.V
        cache = jsConstruct(global_Map, js("[]"))
        accent = (fun(): dynamic {
            return jsOr(global_getComputedStyle(global_document.documentElement).getPropertyValue("--accent").trim()) { "#57d8f2" }
        })
        root.Exercise3D = jsObject { obj92 ->
            obj92["pose"] = pose
            obj92["scene"] = scene
            obj92["bounds"] = bounds
            obj92["options"] = options
            obj92["draw"] = draw
        }
        return absent
    }))(global_window)
}
