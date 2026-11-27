@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installlearning

import hunter.browser.*

@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installLearning() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var signature: dynamic = absent
        var features: dynamic = absent
        var samples: dynamic = absent
        var predict: dynamic = absent
        var gate: dynamic = absent
        signature = fun(items: dynamic): dynamic {
            return dynamicValue(jsOr(items) { js("[]") }).map((fun(x: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd(jsAdd(x.id, ":"), E.unitOf(x)), ":"), jsOr(x.side) { "both" })
            })).sort().join("|")
        }
        features = fun(items: dynamic, arg7: dynamic): dynamic {
            var bump: dynamic = absent
            bump = definedOr(arg7) { false }
            var reps: dynamic = 0
            var seconds: dynamic = 0
            var sets: dynamic = 0
            var rest: dynamic = 0
            if (true) {
                val iterator8: dynamic = iteratorOf(jsOr(items) { js("[]") })
                while (true) {
                    val next9: dynamic = iterator8.next()
                    if (truth(next9.done)) break
                    var x: dynamic = absent
                    x = next9.value
                    val e: dynamic = E.byId(x.id)
                    if (truth((!truth(e)))) {
                        return null
                    }
                    val n: dynamic = global_Number(x.sets)
                    val t: dynamic = jsAdd(global_Number(x.target), (if (truth(bump)) (if (truth((E.unitOf(x) === "sec"))) 5 else 1) else 0))
                    if (truth((!truth(global_Number.isFinite(jsMultiply(n, t)))))) {
                        return null
                    }
                    run {
                        sets = jsAdd(sets, n)
                    }
                    run {
                        rest = jsAdd(rest, (if (truth(global_Number.isFinite(global_Number(x.rest)))) global_Number(x.rest) else 90))
                    }
                    if (truth((E.unitOf(x) === "sec"))) {
                        run {
                            seconds = jsAdd(seconds, jsMultiply(n, t))
                        }
                    } else {
                        run {
                            reps = jsAdd(reps, jsMultiply(n, t))
                        }
                    }
                }
            }
            return (if (truth(sets)) arrayOf<dynamic>(jsDivide(reps, 100), jsDivide(seconds, 300), jsDivide(sets, 10), jsDivide(jsDivide(rest, global_Math.max(1, items.length)), 120)) else null)
        }
        samples = fun(logs: dynamic, now: dynamic): dynamic {
            return global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg88: dynamic): dynamic {
                var date: dynamic = absent
                var l: dynamic = absent
                val unpacked89: dynamic = fromIterable(arg88)
                date = unpacked89[0]
                l = unpacked89[1]
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsLess(date, now)) { l.complete }) { (!truth(l.pain)) }) { jsAtLeast(global_Number(l.rpe), 1) }) { jsAtMost(global_Number(l.rpe), 10) }) { onPresent(l.items) { value90 -> value90.length } }
            })).sort((fun(arg91: dynamic, arg93: dynamic): dynamic {
                var a: dynamic = absent
                val unpacked92: dynamic = fromIterable(arg91)
                a = unpacked92[0]
                var b: dynamic = absent
                val unpacked94: dynamic = fromIterable(arg93)
                b = unpacked94[0]
                return b.localeCompare(a)
            })).slice(0, 180).map((fun(arg95: dynamic): dynamic {
                var date: dynamic = absent
                var l: dynamic = absent
                val unpacked96: dynamic = fromIterable(arg95)
                date = unpacked96[0]
                l = unpacked96[1]
                return jsObject { obj97 ->
                    obj97["date"] = date
                    obj97["x"] = features(l.items)
                    obj97["y"] = global_Number(l.rpe)
                    obj97["signature"] = signature(l.items)
                }
            })).filter((fun(s: dynamic): dynamic {
                return s.x
            }))
        }
        predict = fun(`data`: dynamic, items: dynamic, arg100: dynamic): dynamic {
            var bump: dynamic = absent
            bump = definedOr(arg100) { false }
            val x: dynamic = features(items)
            if (truth((!truth(x)))) {
                return null
            }
            val rows: dynamic = `data`.filter((fun(s: dynamic): dynamic {
                return (s.signature === signature(items))
            }))
            if (truth(jsLess(rows.length, 5))) {
                return null
            }
            val q: dynamic = features(items, bump)
            val near: dynamic = rows.map((fun(s: dynamic): dynamic {
                return jsObject { obj110 ->
                    copyFields(obj110, s)
                    obj110["d"] = run { val target112: dynamic = global_Math; invokeWith(target112.hypot, target112, jsArray { array111 ->
                        appendAll(array111, q.map((fun(v: dynamic, i: dynamic): dynamic {
                            return jsSubtract(v, s.x[i])
                        })))
                    }) }
                }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract(a.d, b.d)
            })).slice(0, 5)
            if (truth(jsMore(near[0].d, 0.65))) {
                return null
            }
            var sum: dynamic = 0
            var w: dynamic = 0
            if (true) {
                val iterator113: dynamic = iteratorOf(near)
                while (true) {
                    val next114: dynamic = iterator113.next()
                    if (truth(next114.done)) break
                    var s: dynamic = absent
                    s = next114.value
                    val weight: dynamic = jsDivide(1, jsAdd(s.d, 0.05))
                    run {
                        sum = jsAdd(sum, jsMultiply(s.y, weight))
                    }
                    run {
                        w = jsAdd(w, weight)
                    }
                }
            }
            return jsObject { obj115 ->
                obj115["effort"] = jsDivide(global_Math.round(jsMultiply(jsDivide(sum, w), 10)), 10)
                obj115["neighbors"] = near.length
                obj115["sessions"] = rows.length
            }
        }
        gate = fun(logs: dynamic, days: dynamic, now: dynamic): dynamic {
            val `data`: dynamic = samples(logs, now)
            val pending: dynamic = days.filter((fun(d: dynamic): dynamic {
                return d.items.length
            }))
            val predictions: dynamic = pending.map((fun(d: dynamic): dynamic {
                return predict(`data`, d.items, true)
            }))
            if (truth(jsOr((!truth(pending.length))) { predictions.some((fun(x: dynamic): dynamic {
                return (!truth(x))
            })) })) {
                return jsonValue("{\"allow\":false,\"reason\":\"Aprendizaje local: faltan cinco sesiones comparables para algún movimiento pendiente. Se mantiene el volumen.\"}")
            }
            val worst: dynamic = run { val target119: dynamic = global_Math; invokeWith(target119.max, target119, jsArray { array118 ->
                appendAll(array118, predictions.map((fun(x: dynamic): dynamic {
                    return x.effort
                })))
            }) }
            return jsObject { obj120 ->
                obj120["allow"] = jsAtMost(worst, 7)
                obj120["reason"] = jsAdd(jsAdd(jsAdd("Modelo kNN: esfuerzo estimado del incremento ", worst.toFixed(1)), "/10, con cinco sesiones similares por estimación. "), (if (truth(jsAtMost(worst, 7))) "Permite un incremento pequeño dentro de los límites del plan." else "Se mantiene el volumen por esfuerzo estimado alto."))
            }
        }
        E = root.Engine
        root.Learning = jsObject { obj123 ->
            obj123["samples"] = samples
            obj123["predict"] = predict
            obj123["gate"] = gate
            obj123["features"] = features
            obj123["signature"] = signature
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
