@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterperformance380

import hunter.browser.*

@JsName("performance") private external var global_performance: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("PerformanceObserver") private external var global_PerformanceObserver: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterPerformance380() {
    dynamicValue((fun(w: dynamic, d: dynamic): dynamic {
        val perf: dynamic = jsObject { obj3 ->
            obj3["version"] = "38.0"
            obj3["refreshHz"] = dynamicValue(null)
            obj3["callbackHz"] = dynamicValue(null)
            obj3["longTasks"] = 0
            obj3["drawMs"] = 0
            obj3["drawCount"] = 0
            obj3["samples"] = js("[]")
            obj3["lastRaf"] = 0
            obj3["calibrationDone"] = false
            obj3["initialTime"] = global_performance.now()
            obj3["powerSaver"] = false
            obj3["level"] = "auto"
            obj3["sampleDrawCost"] = fun(ms: dynamic): dynamic {
                val receiver4: dynamic = js("this")
                if (truth((!truth(jsAnd(jsAtLeast(ms, 0)) { jsLess(ms, 300) })))) {
                    return absent
                }
                receiver4.drawMs = (if (truth(receiver4.drawCount)) jsAdd(jsMultiply(receiver4.drawMs, 0.94), jsMultiply(ms, 0.06)) else ms)
                run {
                    val target8: dynamic = receiver4
                    val previous10: dynamic = numeric(target8.drawCount)
                    target8.drawCount = jsAdd(previous10, 1)
                }
                return absent
            }
            obj3["actorInterval"] = fun(state: dynamic): dynamic {
                val receiver11: dynamic = js("this")
                if (truth(jsOr(jsOr(d.hidden) { receiver11.powerSaver }) { jsAnd((onPresent(w.HunterAdaptive3823) { value13 -> value13.quality } === "eco")) { (state === "idle") } })) {
                    return jsDivide(1000, 24)
                }
                if (truth(run { val owner15: dynamic = w; onPresent(owner15.matchMedia) { method14 -> invokeWith(method14, owner15, arrayOf<dynamic>("(prefers-reduced-motion: reduce)")).matches } })) {
                    return jsDivide(1000, 24)
                }
                val hz: dynamic = jsOr(receiver11.refreshHz) { 60 }
                val busy: dynamic = jsAnd(jsMore(receiver11.drawCount, 20)) { jsMore(receiver11.drawMs, 2.7) }
                val transitional: dynamic = jsAnd((state !== "idle")) { (state !== "hidden") }
                val target: dynamic = (if (truth(busy)) global_Math.min(45, hz) else (if (truth(transitional)) global_Math.min(120, hz) else global_Math.min(60, hz)))
                return jsSubtract(jsDivide(1000, global_Math.max(24, target)), 0.45)
            }
            obj3["observe"] = fun(): dynamic {
                val receiver17: dynamic = js("this")
                var that: dynamic = absent
                var n: dynamic = absent
                var prev: dynamic = absent
                var frames: dynamic = absent
                var tick: dynamic = absent
                tick = fun(t: dynamic): dynamic {
                    if (truth(d.hidden)) {
                        return absent
                    }
                    if (truth(prev)) {
                        val dt: dynamic = jsSubtract(t, prev)
                        if (truth(jsAnd(jsMore(dt, 3)) { jsLess(dt, 80) })) {
                            frames.push(dt)
                        }
                    }
                    prev = t
                    if (truth(jsLess(run {
                        val previous21: dynamic = numeric(n)
                        val assigned22: dynamic = jsAdd(previous21, 1)
                        n = assigned22
                        assigned22
                    }, 120))) {
                        w.requestAnimationFrame(tick)
                        return absent
                    }
                    if (truth(jsAtLeast(frames.length, 50))) {
                        frames.sort((fun(a: dynamic, b: dynamic): dynamic {
                            return jsSubtract(a, b)
                        }))
                        val med: dynamic = frames[global_Math.floor(jsDivide(frames.length, 2))]
                        val nominal: dynamic = jsonValue("[60,75,90,120,144,165]")
                        val raw: dynamic = jsDivide(1000, med)
                        val guess: dynamic = nominal.reduce((fun(a: dynamic, b: dynamic): dynamic {
                            return (if (truth(jsLess(global_Math.abs(jsSubtract(b, raw)), global_Math.abs(jsSubtract(a, raw))))) b else a)
                        }), nominal[0])
                        that.refreshHz = (if (truth(jsAtLeast(raw, jsMultiply(guess, 0.9)))) guess else global_Math.round(raw))
                        that.callbackHz = global_Math.round(raw)
                        that.calibrationDone = true
                        d.documentElement.dataset.fpsCalibrated380 = "true"
                    }
                    return absent
                }
                if (truth(jsOr(d.hidden) { receiver17.calibrationDone })) {
                    return absent
                }
                that = receiver17
                n = 0
                prev = 0
                frames = js("[]")
                w.requestAnimationFrame(tick)
                return absent
            }
            obj3["snapshot"] = fun(): dynamic {
                val receiver31: dynamic = js("this")
                return jsObject { obj33 ->
                    obj33["refreshEstimateHz"] = receiver31.refreshHz
                    obj33["callbackEstimateHz"] = receiver31.callbackHz
                    obj33["actorDrawMsAverage"] = global_Number(receiver31.drawMs.toFixed(3))
                    obj33["actorSamples"] = receiver31.drawCount
                    obj33["longTasks"] = receiver31.longTasks
                    obj33["note"] = "No son FPS reales de pantalla; medir Android con FrameTimeline/Perfetto."
                }
            }
        }
        w.HunterPerf380 = perf
        if (truth((js("typeof PerformanceObserver") === "function"))) {
            try {
                val observer: dynamic = jsConstruct(global_PerformanceObserver, arrayOf<dynamic>((fun(list: dynamic): dynamic {
                    run {
                        val target36: dynamic = perf
                        target36.longTasks = jsAdd(target36.longTasks, list.getEntries().length)
                    }
                    return absent
                })))
                observer.observe(jsonValue("{\"entryTypes\":[\"longtask\"]}"))
                w.addEventListener("pagehide", (fun(): dynamic {
                    return observer.disconnect()
                }), jsonValue("{\"once\":true}"))
            } catch (e: dynamic) {

            }
        }
        if (truth((d.readyState === "loading"))) {
            d.addEventListener("DOMContentLoaded", (fun(): dynamic {
                return perf.observe()
            }), jsonValue("{\"once\":true}"))
        } else {
            perf.observe()
        }
        d.addEventListener("visibilitychange", (fun(): dynamic {
            if (truth((!truth(d.hidden)))) {
                perf.calibrationDone = false
                perf.observe()
            }
            return absent
        }))
        try {
            if (truth((jsTypeOf(onPresent(w.HunterV373) { value40 -> value40.power }) === "function"))) {
                val old: dynamic = w.HunterV373.power
                w.HunterV373.power = (fun(state: dynamic): dynamic {
                    perf.powerSaver = (onPresent(state) { value47 -> value47.powerSave } === true)
                    return old(state)
                })
            }
        } catch (e: dynamic) {

        }
        if (truth((jsTypeOf(w.openDiagnostics20) === "function"))) {
            val old: dynamic = w.openDiagnostics20
            w.openDiagnostics20 = (fun(arg52: dynamic): dynamic {
                val receiver50: dynamic = js("this")
                val callArgs51: dynamic = js("arguments")
                var args: dynamic = absent
                args = argsAfter(callArgs51, 0)
                val ret: dynamic = old.apply(receiver50, args)
                val section: dynamic = d.querySelector("#modal section")
                if (truth(section)) {
                    val p: dynamic = d.createElement("p")
                    p.className = "fps-metrics380"
                    p.textContent = jsAdd(jsAdd("Fluidez V38.0: estimación del ritmo de callbacks ", (if (truth((perf.callbackHz === null))) "pendiente" else jsAdd(perf.callbackHz, " Hz"))), ". FPS reales en Android: no medidos. Las animaciones se adaptan a la carga.")
                    section.append(p)
                }
                return ret
            })
        }
        return absent
    }))(global_window, global_document)
}
