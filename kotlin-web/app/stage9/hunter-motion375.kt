@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntermotion375

import hunter.browser.*

@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("DemoLoop373") private external var global_DemoLoop373: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("MotionPlayback") private external var global_MotionPlayback: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterMotion375() {
    dynamicValue((fun(root: dynamic): dynamic {
        var duration: dynamic = absent
        var at: dynamic = absent
        var format: dynamic = absent
        duration = fun(id: dynamic): dynamic {
            val sec: dynamic = global_Number(onPresent(root.E) { value5 -> onPresent(value5.byId(id)) { value6 -> value6.tempoSeconds18 } })
            return (if (truth(jsAnd(jsAnd(global_Number.isFinite(sec)) { jsAtLeast(sec, 0.1) }) { jsAtMost(sec, 120) })) sec else 4)
        }
        at = fun(startPhase: dynamic, startTime: dynamic, now: dynamic, seconds: dynamic, rate: dynamic): dynamic {
            if (truth(jsOr((!truth(dynamicValue(arrayOf<dynamic>(startPhase, startTime, now, seconds, rate)).every(global_Number.isFinite)))) { jsAtMost(seconds, 0) })) {
                return 0
            }
            return jsMod(jsAdd(jsMod(jsAdd(startPhase, jsMultiply(jsDivide(jsMultiply(jsDivide(global_Math.max(0, jsSubtract(now, startTime)), 1000), 48), seconds), rate)), 48), 48), 48)
        }
        format = fun(sec: dynamic): dynamic {
            return jsAdd(global_Number(sec.toFixed(2)).toLocaleString("es-PE", jsonValue("{\"maximumFractionDigits\":2}")), " s")
        }
        val clamp: dynamic = (fun(n: dynamic, a: dynamic, b: dynamic): dynamic {
            return global_Math.max(a, global_Math.min(b, n))
        })
        val timing: dynamic = jsObject { obj11 ->
            obj11["duration"] = duration
            obj11["at"] = at
            obj11["format"] = format
        }
        root.HunterMotion375 = timing
        val loop: dynamic = root.DemoLoop373
        if (truth((js("typeof DemoLoop373") !== "undefined"))) {
            val engine: dynamic = global_DemoLoop373
            val olderSync: dynamic = engine.sync.bind(engine)
            engine.sync = (fun(): dynamic {
                val receiver16: dynamic = js("this")
                if (truth(jsAnd(receiver16.entry) { jsOr(onPresent(root.HunterV373) { value18 -> value18.runtime.hidden }) { (!truth(receiver16.entry.visible)) } })) {
                    receiver16.entry.clock375 = dynamicValue(null)
                }
                return olderSync()
            })
            engine.tick = (fun(now: dynamic): dynamic {
                val receiver24: dynamic = js("this")
                receiver24.frame = 0
                val entry: dynamic = receiver24.entry
                if (truth((!truth(receiver24.valid())))) {
                    receiver24.stop()
                    return absent
                }
                if (truth(jsOr(onPresent(root.HunterV373) { value28 -> value28.runtime.hidden }) { (!truth(entry.visible)) })) {
                    entry.clock375 = dynamicValue(null)
                    return absent
                }
                if (truth((entry.kind === "vector"))) {
                    val s: dynamic = global_vectorSession
                    val seconds: dynamic = duration(s.id)
                    val rate: dynamic = s.speed
                    if (truth(jsOr(jsOr((!truth(entry.clock375))) { (entry.clock375.seconds !== seconds) }) { (entry.clock375.rate !== rate) })) {
                        entry.clock375 = jsObject { obj34 ->
                            obj34["time"] = now
                            obj34["phase"] = s.frame
                            obj34["seconds"] = seconds
                            obj34["rate"] = rate
                        }
                    }
                    s.frame = at(entry.clock375.phase, entry.clock375.time, now, seconds, rate)
                    s.last = now
                    global_vectorPaint()
                } else {
                    val g: dynamic = global_guide11
                    val step: dynamic = g.steps[g.index]
                    val seconds: dynamic = duration(step.id)
                    val rate: dynamic = global_MotionPlayback.rate
                    if (truth(jsOr(jsOr(jsOr((!truth(entry.clock375))) { (entry.clock375.seconds !== seconds) }) { (entry.clock375.rate !== rate) }) { (entry.clock375.id !== step.id) })) {
                        entry.clock375 = jsObject { obj41 ->
                            obj41["time"] = now
                            obj41["phase"] = g.demoFrame
                            obj41["seconds"] = seconds
                            obj41["rate"] = rate
                            obj41["id"] = step.id
                        }
                    }
                    g.demoFrame = at(entry.clock375.phase, entry.clock375.time, now, seconds, rate)
                    g.demoLast = now
                    global_drawVector(entry.canvas, step.id, g.demoFrame, (step.side === "left"))
                }
                run {
                    val target46: dynamic = receiver24
                    val previous48: dynamic = numeric(target46.draws)
                    target46.draws = jsAdd(previous48, 1)
                }
                receiver24.sync()
                return absent
            })
        }
        return absent
    }))(global_window)
}
