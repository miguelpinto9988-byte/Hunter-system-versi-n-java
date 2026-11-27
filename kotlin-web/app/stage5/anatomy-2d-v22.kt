@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installanatomy2dv22

import hunter.browser.*

@JsName("Math") private external var global_Math: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installAnatomy2dV22() {
    dynamicValue((fun(root: dynamic): dynamic {
        var minus: dynamic = absent
        var sub: dynamic = absent
        var normal: dynamic = absent
        var unit: dynamic = absent
        var add: dynamic = absent
        var supine: dynamic = absent
        var prone: dynamic = absent
        var family: dynamic = absent
        var floors: dynamic = absent
        var posture: dynamic = absent
        var handedness: dynamic = absent
        var align: dynamic = absent
        var describe: dynamic = absent
        posture = fun(model: dynamic): dynamic {
            if (truth(model.posture23)) {
                return model.posture23
            }
            val id: dynamic = family(model)
            val view: dynamic = jsOr(onPresent(model.view21) { value12 -> value12.primary }) { "side" }
            return (if (truth((view === "top"))) "prone" else (if (truth((view === "lying"))) "side-lying" else (if (truth(supine.has(id))) "supine" else (if (truth(prone.has(id))) "prone" else "upright"))))
        }
        handedness = fun(model: dynamic): dynamic {
            if (truth(model.facing23)) {
                return (if (truth((model.facing23 === "left"))) 1 else (-numeric(1)))
            }
            val p: dynamic = model.frames[0].p
            return (if (truth(jsAnd((posture(model) === "prone")) { jsMore(p[2][0], p[1][0]) })) 1 else (-numeric(1)))
        }
        align = fun(f: dynamic, model: dynamic): dynamic {
            if (truth(dynamicValue(jsonValue("[\"calf\",\"dbcalf\",\"singlecalf\"]")).includes(family(model)))) {
                val first: dynamic = model.frames[0].p
                val center: dynamic = first[2][0]
                if (true) {
                    val iterator17: dynamic = iteratorOf(jsonValue("[[7,8,5],[9,10,-12]]"))
                    while (true) {
                        val next18: dynamic = iterator17.next()
                        if (truth(next18.done)) break
                        var knee: dynamic = absent
                        var ankle: dynamic = absent
                        var offset: dynamic = absent
                        val unpacked19: dynamic = fromIterable(next18.value)
                        knee = unpacked19[0]
                        ankle = unpacked19[1]
                        offset = unpacked19[2]
                        val shift: dynamic = jsSubtract(first[ankle][0], jsAdd(center, offset))
                        run {
                            val target20: dynamic = f.p[knee]
                            val key21: dynamic = 0
                            target20[key21] = jsSubtract(target20[key21], shift)
                        }
                        run {
                            val target22: dynamic = f.p[ankle]
                            val key23: dynamic = 0
                            target22[key23] = jsSubtract(target22[key23], shift)
                        }
                    }
                }
                if (truth((family(model) !== "singlecalf"))) {
                    if (true) {
                        val iterator24: dynamic = iteratorOf(jsonValue("[[3,4,10],[5,6,-10]]"))
                        while (true) {
                            val next25: dynamic = iterator24.next()
                            if (truth(next25.done)) break
                            var elbow: dynamic = absent
                            var hand: dynamic = absent
                            var offset: dynamic = absent
                            val unpacked26: dynamic = fromIterable(next25.value)
                            elbow = unpacked26[0]
                            hand = unpacked26[1]
                            offset = unpacked26[2]
                            val shift: dynamic = jsSubtract(first[hand][0], jsAdd(center, offset))
                            run {
                                val target27: dynamic = f.p[elbow]
                                val key28: dynamic = 0
                                target27[key28] = jsSubtract(target27[key28], shift)
                            }
                            run {
                                val target29: dynamic = f.p[hand]
                                val key30: dynamic = 0
                                target29[key30] = jsSubtract(target29[key30], shift)
                            }
                        }
                    }
                    if (truth((f.loads.length === 2))) {
                        f.loads = arrayOf<dynamic>(f.p[4].slice(), f.p[6].slice())
                    }
                }
            }
            if (truth(jsAnd(jsAnd((onPresent(model.view21) { value33 -> value33.primary } === "side")) { (posture(model) === "prone") }) { (!truth(dynamicValue(jsonValue("[\"cat\",\"childpose\",\"pau-pushchild\"]")).includes(family(model)))) })) {
                val axis: dynamic = unit(sub(f.p[2], f.p[1]))
                val length: dynamic = global_Math.min(62, global_Math.max(52, run { val target35: dynamic = global_Math; invokeWith(target35.hypot, target35, jsArray { array34 ->
                    appendAll(array34, sub(f.p[0], f.p[1]))
                }) }))
                f.p[0] = add(f.p[1], axis, (-numeric(length)))
            }
            return f
        }
        describe = fun(e: dynamic, f: dynamic, model: dynamic): dynamic {
            val p: dynamic = f.p
            val view: dynamic = jsOr(onPresent(model.view21) { value40 -> value40.primary }) { "side" }
            val kind: dynamic = posture(model)
            val axis: dynamic = unit(sub(p[2], p[1]))
            val neck: dynamic = unit(sub(p[1], p[0]))
            val handed: dynamic = handedness(model)
            val ventral: dynamic = normal(axis).map((fun(x: dynamic): dynamic {
                return jsMultiply(x, handed)
            }))
            val face: dynamic = (if (truth((view === "side"))) normal(neck).map((fun(x: dynamic): dynamic {
                return jsMultiply(x, handed)
            })) else null)
            if (truth((!truth(floors.has(model))))) {
                floors["set"](model, run { val target42: dynamic = global_Math; invokeWith(target42.max, target42, jsArray { array41 ->
                    appendAll(array41, model.frames.flatMap((fun(x: dynamic): dynamic {
                        return arrayOf<dynamic>(x.p[8][1], x.p[10][1])
                    })))
                }) })
            }
            val floor: dynamic = nullableOr(f.detail.floor23) { floors["get"](model) }
            val id: dynamic = family(model)
            val wide: dynamic = dynamicValue(jsonValue("[\"sumo\",\"dbsumo\",\"squatstep\",\"lateralunge\",\"dbsidelunge\",\"lateral-lunge\"]")).includes(id)
            val standingSupport: dynamic = dynamicValue(jsonValue("[\"wall\",\"wallplank\",\"incline\"]")).includes(id)
            val calf: dynamic = dynamicValue(jsRegExp("^(?:calf|dbcalf|singlecalf|seatedcalf|seatedheel|wallcalf)\$", "")).test(id)
            val feet: dynamic = js("[]")
            val knees: dynamic = js("[]")
            if (true) {
                val iterator43: dynamic = iteratorOf(jsonValue("[[7,8,-1],[9,10,1]]"))
                while (true) {
                    val next44: dynamic = iterator43.next()
                    if (truth(next44.done)) break
                    var kneeIndex: dynamic = absent
                    var ankleIndex: dynamic = absent
                    var side: dynamic = absent
                    val unpacked45: dynamic = fromIterable(next44.value)
                    kneeIndex = unpacked45[0]
                    ankleIndex = unpacked45[1]
                    side = unpacked45[2]
                    var direction: dynamic = absent
                    val ankle: dynamic = p[ankleIndex]
                    val knee: dynamic = p[kneeIndex]
                    val lower: dynamic = unit(sub(ankle, knee))
                    var grounded: dynamic = false
                    if (truth((view === "side"))) {
                        direction = normal(lower).map((fun(x: dynamic): dynamic {
                            return jsMultiply(x, handed)
                        }))
                        grounded = jsAnd(jsAnd(jsOr((kind !== "prone")) { standingSupport }) { jsAtLeast(ankle[1], jsSubtract(floor, 30)) }) { jsMore(jsSubtract(ankle[1], knee[1]), 45) }
                        if (truth(grounded)) {
                            direction = arrayOf<dynamic>(jsOr(global_Math.sign(direction[0])) { 1 }, 0)
                        }
                        if (truth(dynamicValue(jsonValue("[\"toelift\",\"heeltoe\",\"ankle\"]")).includes(f.detail.feet))) {
                            val amount: dynamic = nullableOr(nullableOr(f.detail.toeAmount) { f.detail.amount }) { 0 }
                            val angle: dynamic = (if (truth((f.detail.feet === "ankle"))) jsMultiply(jsSubtract(amount, 0.5), 0.8) else jsMultiply(amount, (-numeric(0.62))))
                            direction = arrayOf<dynamic>(global_Math.cos(angle), global_Math.sin(angle))
                        }
                    } else {
                        if (truth((view === "top"))) {
                            direction = (if (truth(f.detail.front19)) minus(axis) else axis)
                        } else {
                            if (truth((view === "rear"))) {
                                direction = minus(axis)
                            } else {
                                if (truth((view === "lying"))) {
                                    direction = normal(lower)
                                } else {
                                    direction = unit(add(axis, normal(axis), jsMultiply((-numeric(side)), (if (truth(wide)) 0.55 else 0.12))))
                                }
                            }
                        }
                    }
                    if (truth(f.detail.footAxis23)) {
                        direction = unit(f.detail.footAxis23)
                    }
                    var heel: dynamic = add(ankle, direction, (-numeric(7)))
                    var toe: dynamic = add(ankle, direction, 28)
                    if (truth((f.detail.rearToe23 === ankleIndex))) {
                        direction = unit(jsonValue("[1,0.45]"))
                        toe = arrayOf<dynamic>(jsAdd(ankle[0], 28), floor)
                        heel = add(ankle, direction, (-numeric(7)))
                    }
                    if (truth(jsAnd(calf) { grounded })) {
                        toe = arrayOf<dynamic>(jsAdd(ankle[0], 28), jsAdd(floor, 4))
                        direction = unit(sub(toe, ankle))
                        heel = add(ankle, direction, (-numeric(7)))
                    }
                    feet.push(jsObject { obj46 ->
                        obj46["index"] = ankleIndex
                        obj46["side"] = side
                        obj46["origin"] = ankle
                        obj46["axis"] = direction
                        obj46["heel"] = heel
                        obj46["toe"] = toe
                        obj46["grounded"] = grounded
                        obj46["view"] = view
                    })
                    val upper: dynamic = unit(sub(knee, p[2]))
                    val patella: dynamic = (if (truth((view === "side"))) normal(upper).map((fun(x: dynamic): dynamic {
                        return jsMultiply(x, handed)
                    })) else direction)
                    knees.push(jsObject { obj47 ->
                        obj47["index"] = kneeIndex
                        obj47["origin"] = knee
                        obj47["axis"] = patella
                        obj47["view"] = view
                    })
                }
            }
            return jsObject { obj48 ->
                obj48["view"] = view
                obj48["posture"] = kind
                obj48["handedness"] = handed
                obj48["torsoAxis"] = axis
                obj48["chestAxis"] = ventral
                obj48["faceAxis"] = face
                obj48["neckAxis"] = neck
                obj48["feet"] = feet
                obj48["knees"] = knees
            }
        }
        minus = (fun(a: dynamic): dynamic {
            return a.map((fun(x: dynamic): dynamic {
                return (-numeric(x))
            }))
        })
        sub = (fun(a: dynamic, b: dynamic): dynamic {
            return a.map((fun(x: dynamic, i: dynamic): dynamic {
                return jsSubtract(x, b[i])
            }))
        })
        normal = (fun(a: dynamic): dynamic {
            return arrayOf<dynamic>((-numeric(a[1])), a[0])
        })
        val dot: dynamic = (fun(a: dynamic, b: dynamic): dynamic {
            return jsAdd(jsMultiply(a[0], b[0]), jsMultiply(a[1], b[1]))
        })
        unit = (fun(a: dynamic): dynamic {
            val len: dynamic = jsOr(run { val target4: dynamic = global_Math; invokeWith(target4.hypot, target4, jsArray { array3 ->
                appendAll(array3, a)
            }) }) { 1 }
            return a.map((fun(x: dynamic): dynamic {
                return jsDivide(x, len)
            }))
        })
        add = (fun(a: dynamic, b: dynamic, arg5: dynamic): dynamic {
            var n: dynamic = absent
            n = definedOr(arg5) { 1 }
            return a.map((fun(x: dynamic, i: dynamic): dynamic {
                return jsAdd(x, jsMultiply(b[i], n))
            }))
        })
        supine = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"press\",\"neutral\",\"bridge\",\"benchpress\",\"inclinebench\",\"squeezepress\",\"singlebridge\",\"dbbridge\",\"pullover\",\"lyingtricep\",\"singletricep\",\"deadbug\",\"heel\",\"reversecrunch\",\"curlup\",\"supinearms\",\"hipthrust\",\"singlepress\",\"altfloorpress\"]")))
        prone = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"wall\",\"incline\",\"pushup\",\"kneepush\",\"closepush\",\"bird\",\"plank\",\"cat\",\"kickback\",\"kneelingplank\",\"mountain\",\"wallplank\",\"bearcrawl\",\"bearhold\",\"childpose\",\"pau-plankup\",\"pau-pushchild\",\"decline19\",\"pike19\"]")))
        family = (fun(m: dynamic): dynamic {
            return jsOr(onPresent(m.view21) { value6 -> value6.family }) { "" }
        })
        floors = jsConstruct(global_WeakMap, js("[]"))
        if (true) {
            val iterator7: dynamic = iteratorOf(jsonValue("[\"shoulder\",\"concentration\",\"pau-armupdown\"]"))
            while (true) {
                val next8: dynamic = iterator7.next()
                if (truth(next8.done)) break
                var id: dynamic = absent
                id = next8.value
                val model: dynamic = global_MotionDB[id]
                val view: dynamic = onPresent(model) { value9 -> value9.view21 }
                if (truth((!truth(view)))) {
                    continue
                }
                global_Object.assign(view, jsonValue("{\"primary\":\"front\",\"mirror\":false,\"label\":\"Frontal\",\"reason\":\"Ver torso, rostro y ambos lados del gesto en el mismo plano.\"}"))
            }
        }
        root.Anatomy2D22 = jsObject { obj51 ->
            obj51["align"] = align
            obj51["describe"] = describe
            obj51["posture"] = posture
            obj51["dot"] = dot
        }
        return absent
    }))(global_window)
}
