@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installmotionv36

import hunter.browser.*

@JsName("Motion30") private external var global_Motion30: dynamic
@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Motion31") private external var global_Motion31: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Rig2D23") private external var global_Rig2D23: dynamic
@JsName("Motion2D18") private external var global_Motion2D18: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installMotionV36() {
    dynamicValue((fun(root: dynamic): dynamic {
        var patched: dynamic = absent
        var copy: dynamic = absent
        var add: dynamic = absent
        var mix: dynamic = absent
        var kinds: dynamic = absent
        var corrected: dynamic = absent
        corrected = fun(e: dynamic, source: dynamic): dynamic {
            val kind: dynamic = global_Motion31.kindOf(e, source)
            if (truth(jsOr((!truth(kinds.has(kind)))) { (source.motionRevision36 === 36) })) {
                return source
            }
            if (truth(patched.has(source))) {
                return patched["get"](source)
            }
            val m: dynamic = copy(source)
            val `param`: dynamic = jsOr(jsOr(e.param30) { e.param23 }) { js("({})") }
            if (truth((kind === "incline"))) {
                m.facing23 = "right"
            }
            if (true) {
                val iterator7: dynamic = iteratorOf(m.frames)
                while (true) {
                    val next8: dynamic = iterator7.next()
                    if (truth(next8.done)) break
                    var f: dynamic = absent
                    f = next8.value
                    val p: dynamic = f.p
                    val a: dynamic = nullableOr(f.detail.amount31) { 0 }
                    val t: dynamic = nullableOr(f.detail.cycle31) { 0 }
                    if (truth((kind === "heeldig"))) {
                        val near: dynamic = global_Math.max(0, global_Math.sin(jsMultiply(jsMultiply(t, 2), global_Math.PI)))
                        val far: dynamic = global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(jsMultiply(t, 2), global_Math.PI)))))
                        p[0] = jsonValue("[350,215]")
                        p[1] = jsonValue("[350,275]")
                        p[2] = jsonValue("[350,440]")
                        if (true) {
                            val iterator15: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, 4, 7, 8, near, 0), arrayOf<dynamic>(5, 6, 9, 10, far, (-numeric(13)))))
                            while (true) {
                                val next16: dynamic = iterator15.next()
                                if (truth(next16.done)) break
                                var el: dynamic = absent
                                var hand: dynamic = absent
                                var knee: dynamic = absent
                                var ankle: dynamic = absent
                                var u: dynamic = absent
                                var o: dynamic = absent
                                val unpacked17: dynamic = fromIterable(next16.value)
                                el = unpacked17[0]
                                hand = unpacked17[1]
                                knee = unpacked17[2]
                                ankle = unpacked17[3]
                                u = unpacked17[4]
                                o = unpacked17[5]
                                val shoulder: dynamic = add(p[1], arrayOf<dynamic>(o, o))
                                val upper: dynamic = jsMultiply(1.02, jsSubtract(1, u))
                                val forearm: dynamic = jsMultiply((-numeric(1.22)), jsSubtract(1, u))
                                p[el] = add(shoulder, arrayOf<dynamic>(jsMultiply(96, global_Math.cos(upper)), jsMultiply(96, global_Math.sin(upper))))
                                p[hand] = add(p[el], arrayOf<dynamic>(jsMultiply(90, global_Math.cos(forearm)), jsMultiply(90, global_Math.sin(forearm))))
                                p[ankle] = arrayOf<dynamic>(jsAdd(jsAdd(357, o), jsMultiply(90, u)), jsAdd(700, o))
                                p[knee] = global_Rig2D23.ik(add(p[2], arrayOf<dynamic>(o, o)), p[ankle], 140, 1)
                            }
                        }
                        f.detail.floor23 = 715
                        f.detail.feet30 = jsObject { obj30 ->
                            obj30["8"] = arrayOf<dynamic>(global_Math.cos(jsMultiply((-numeric(0.65)), near)), global_Math.sin(jsMultiply((-numeric(0.65)), near)))
                            obj30["10"] = arrayOf<dynamic>(global_Math.cos(jsMultiply((-numeric(0.65)), far)), global_Math.sin(jsMultiply((-numeric(0.65)), far)))
                        }
                    } else {
                        if (truth(jsOr(jsOr((kind === "bridge")) { (kind === "dbbridge") }) { (kind === "singlebridge") })) {
                            p[0] = jsonValue("[140,674]")
                            p[1] = jsonValue("[200,674]")
                            p[2] = arrayOf<dynamic>(jsSubtract(365, jsMultiply(10, a)), jsSubtract(665, jsMultiply(59, a)))
                            if (true) {
                                val iterator37: dynamic = iteratorOf(jsonValue("[[7,8,0],[9,10,-13]]"))
                                while (true) {
                                    val next38: dynamic = iterator37.next()
                                    if (truth(next38.done)) break
                                    var knee: dynamic = absent
                                    var ankle: dynamic = absent
                                    var o: dynamic = absent
                                    val unpacked39: dynamic = fromIterable(next38.value)
                                    knee = unpacked39[0]
                                    ankle = unpacked39[1]
                                    o = unpacked39[2]
                                    val single: dynamic = jsOr((kind === "singlebridge")) { `param`.single }
                                    if (truth(jsAnd((knee === 9)) { jsOr(single) { `param`.march } })) {
                                        val u: dynamic = (if (truth(single)) 1 else a)
                                        val standingFoot: dynamic = arrayOf<dynamic>(jsAdd(jsAdd(515, o), (if (truth(`param`.long)) 85 else 0)), jsAdd(682, o))
                                        val standingKnee: dynamic = global_Rig2D23.ik(add(p[2], arrayOf<dynamic>(o, o)), standingFoot, 135, 1)
                                        p[knee] = mix(standingKnee, add(p[2], jsonValue("[85,-100]")), u)
                                        p[ankle] = mix(standingFoot, add(p[2], jsonValue("[185,-85]")), u)
                                    } else {
                                        p[knee] = global_Rig2D23.ik(add(p[2], arrayOf<dynamic>(o, o)), p[ankle], 135, 1)
                                    }
                                }
                            }
                            val loaded: dynamic = jsOr((kind === "dbbridge")) { f.detail.pelvisLoad31 }
                            if (truth(loaded)) {
                                p[4] = add(p[2], jsonValue("[-8,-20]"))
                                p[6] = add(p[2], jsonValue("[-21,-33]"))
                                p[3] = global_Rig2D23.ik(p[1], p[4], 96, 1)
                                p[5] = global_Rig2D23.ik(add(p[1], jsonValue("[-13,-13]")), p[6], 96, 1)
                                f.loads = arrayOf<dynamic>(add(p[2], jsonValue("[-7,-18]")))
                            } else {
                                p[3] = jsonValue("[265,688]")
                                p[4] = jsonValue("[335,690]")
                                p[5] = jsonValue("[252,675]")
                                p[6] = jsonValue("[322,677]")
                            }
                            f.detail.feet30 = jsObject { obj66 ->
                                obj66["8"] = jsonValue("[1,0]")
                                obj66["10"] = (if (truth(jsOr(jsOr((kind === "singlebridge")) { `param`.single }) { `param`.march })) jsonValue("[1,-0.12]") else jsonValue("[1,0]"))
                            }
                        }
                    }
                    f.detail.correction36 = kind
                }
            }
            m.motionRevision36 = 36
            patched["set"](source, m)
            return m
        }
        val original: dynamic = global_Motion30["get"]
        patched = jsConstruct(global_WeakMap, js("[]"))
        copy = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        add = (fun(p: dynamic, q: dynamic): dynamic {
            return p.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsAdd(v, q[i])
            }))
        })
        mix = (fun(a: dynamic, b: dynamic, t: dynamic): dynamic {
            return a.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsAdd(v, jsMultiply(jsSubtract(b[i], v), t))
            }))
        })
        kinds = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"incline\",\"heeldig\",\"bridge\",\"dbbridge\",\"singlebridge\"]")))
        global_Motion30["get"] = (fun(id: dynamic): dynamic {
            val result: dynamic = original(id)
            return jsObject { obj75 ->
                copyFields(obj75, result)
                obj75["model"] = corrected(result.e, result.model)
            }
        })
        val preload: dynamic = global_Motion30.preload
        global_Motion30.preload = (fun(id: dynamic): dynamic {
            val mounted: dynamic = (if (truth(global_Motion30.cache.has(id))) global_Motion30["get"](id).model else null)
            return preload(id)["then"]((fun(): dynamic {
                if (truth(jsAnd(mounted) { global_Motion30.loaded.has(id) })) {
                    global_Motion30.cache["set"](id, mounted)
                }
                return absent
            }))
        })
        root.Motion36 = jsObject { obj82 ->
            obj82["corrected"] = corrected
            obj82["kinds"] = kinds
            obj82["reference"] = 31
            obj82["version"] = 36
        }
        global_Motion2D18.clearBounds()
        return absent
    }))(global_window)
}
