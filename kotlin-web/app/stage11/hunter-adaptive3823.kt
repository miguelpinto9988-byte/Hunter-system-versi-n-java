@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunteradaptive3823

import hunter.browser.*

@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterAdaptive3823() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var perf: dynamic = absent
        var evolution: dynamic = absent
        var RANKS: dynamic = absent
        var TICK_MS: dynamic = absent
        var current: dynamic = absent
        var lastLongTasks: dynamic = absent
        var highLoadPeriods: dynamic = absent
        var stablePeriods: dynamic = absent
        var checks: dynamic = absent
        var degrades: dynamic = absent
        var badgeAnimations: dynamic = absent
        var lastRank: dynamic = absent
        var rankObserver: dynamic = absent
        var timer: dynamic = absent
        var disposed: dynamic = absent
        var reducedMotion: dynamic = absent
        var manualEco: dynamic = absent
        var canAnimate: dynamic = absent
        var checkRank: dynamic = absent
        var sample: dynamic = absent
        var clear: dynamic = absent
        var schedule: dynamic = absent
        var visibility: dynamic = absent
        var dispose: dynamic = absent
        var `init`: dynamic = absent
        canAnimate = fun(): dynamic {
            return jsAnd(jsAnd(jsAnd(jsAnd((!truth(disposed))) { (!truth(doc.hidden)) }) { (!truth(reducedMotion())) }) { (!truth(manualEco())) }) { (onPresent(evolution) { value10 -> value10.budget } !== "eco") }
        }
        checkRank = fun(): dynamic {
            val rank: dynamic = onPresent(doc.documentElement) { value13 -> onPresent(value13.dataset) { value14 -> value14.hunterRank385 } }
            if (truth((!truth(RANKS.includes(rank))))) {
                return absent
            }
            val rising: dynamic = jsAnd((lastRank !== null)) { jsMore(RANKS.indexOf(rank), RANKS.indexOf(lastRank)) }
            lastRank = rank
            if (truth(jsOr((!truth(rising))) { (!truth(canAnimate())) })) {
                return absent
            }
            val badge: dynamic = doc.querySelector(".header-rank26 > b")
            if (truth(jsOr((!truth(badge))) { (jsTypeOf(badge.animate) !== "function") })) {
                return absent
            }
            try {
                badge.animate(jsonValue("[{\"transform\":\"scale(1)\",\"opacity\":1},{\"transform\":\"scale(1.17)\",\"opacity\":0.83,\"offset\":0.45},{\"transform\":\"scale(1)\",\"opacity\":1}]"), jsonValue("{\"duration\":760,\"iterations\":1,\"easing\":\"ease-out\"}"))
                run {
                    val previous15: dynamic = numeric(badgeAnimations)
                    badgeAnimations = jsAdd(previous15, 1)
                }
            } catch (_: dynamic) {

            }
            return absent
        }
        sample = fun(): dynamic {
            if (truth(jsOr(disposed) { doc.hidden })) {
                return absent
            }
            val longTasks: dynamic = global_Math.max(0, jsOr(global_Number(onPresent(perf) { value18 -> value18.longTasks })) { 0 })
            val delta: dynamic = global_Math.max(0, jsSubtract(longTasks, lastLongTasks))
            lastLongTasks = longTasks
            val drawMs: dynamic = jsOr(global_Number(onPresent(perf) { value19 -> value19.drawMs })) { 0 }
            val drawCount: dynamic = jsOr(global_Number(onPresent(perf) { value20 -> value20.drawCount })) { 0 }
            val slow: dynamic = jsOr(jsAtLeast(delta, 4)) { jsAnd(jsAtLeast(drawCount, 35)) { jsMore(drawMs, 5.2) } }
            run {
                val previous21: dynamic = numeric(checks)
                checks = jsAdd(previous21, 1)
            }
            if (truth(slow)) {
                run {
                    val previous22: dynamic = numeric(highLoadPeriods)
                    highLoadPeriods = jsAdd(previous22, 1)
                }
                stablePeriods = 0
            } else {
                run {
                    val previous23: dynamic = numeric(stablePeriods)
                    stablePeriods = jsAdd(previous23, 1)
                }
                highLoadPeriods = 0
            }
            if (truth(jsAnd(jsAtLeast(highLoadPeriods, 2)) { (current !== "eco") })) {
                current = "eco"
                run {
                    val previous24: dynamic = numeric(degrades)
                    degrades = jsAdd(previous24, 1)
                }
                onPresent(root.HunterEvolution385) { value25 -> run { val owner27: dynamic = value25; onPresent(owner27.refresh) { method26 -> invokeWith(method26, owner27, js("[]")) } } }
            } else {
                if (truth(jsAnd(jsAtLeast(stablePeriods, 4)) { (current !== "normal") })) {
                    current = "normal"
                    onPresent(root.HunterEvolution385) { value28 -> run { val owner30: dynamic = value28; onPresent(owner30.refresh) { method29 -> invokeWith(method29, owner30, js("[]")) } } }
                }
            }
            return absent
        }
        clear = fun(): dynamic {
            if (truth(timer)) {
                root.clearTimeout(timer)
            }
            timer = 0
            return absent
        }
        schedule = fun(): dynamic {
            clear()
            if (truth(jsOr(disposed) { doc.hidden })) {
                return absent
            }
            timer = root.setTimeout((run {
                var recuronTick37: dynamic = absent
                recuronTick37 = fun(): dynamic {
                    timer = 0
                    sample()
                    schedule()
                    return absent
                }
                recuronTick37
            }), TICK_MS)
            return absent
        }
        visibility = fun(): dynamic {
            if (truth(doc.hidden)) {
                clear()
                return absent
            }
            lastLongTasks = global_Math.max(0, jsOr(global_Number(onPresent(perf) { value40 -> value40.longTasks })) { 0 })
            stablePeriods = 0
            highLoadPeriods = 0
            schedule()
            checkRank()
            return absent
        }
        dispose = fun(): dynamic {
            disposed = true
            clear()
            onPresent(rankObserver) { value43 -> value43.disconnect() }
            doc.removeEventListener("visibilitychange", visibility)
            return absent
        }
        `init` = fun(): dynamic {
            if (truth(disposed)) {
                return absent
            }
            val NodeObserver: dynamic = root.MutationObserver
            if (truth(jsAnd((jsTypeOf(NodeObserver) === "function")) { doc.documentElement })) {
                rankObserver = jsConstruct(NodeObserver, arrayOf<dynamic>(checkRank))
                rankObserver.observe(doc.documentElement, jsonValue("{\"attributes\":true,\"attributeFilter\":[\"data-hunter-rank385\"]}"))
            }
            checkRank()
            schedule()
            doc.addEventListener("visibilitychange", visibility, jsonValue("{\"passive\":true}"))
            return absent
        }
        if (truth(root.HunterAdaptive3823)) {
            return absent
        }
        perf = root.HunterPerf380
        evolution = root.HunterEvolution385
        RANKS = jsonValue("[\"E\",\"D\",\"C\",\"B\",\"A\",\"S\"]")
        TICK_MS = 12000
        current = "normal"
        lastLongTasks = global_Math.max(0, jsOr(global_Number(onPresent(perf) { value3 -> value3.longTasks })) { 0 })
        highLoadPeriods = 0
        stablePeriods = 0
        checks = 0
        degrades = 0
        badgeAnimations = 0
        lastRank = dynamicValue(null)
        rankObserver = dynamicValue(null)
        timer = 0
        disposed = false
        reducedMotion = (fun(): dynamic {
            return (!truth((!truth(run { val owner5: dynamic = root; onPresent(owner5.matchMedia) { method4 -> invokeWith(method4, owner5, arrayOf<dynamic>("(prefers-reduced-motion: reduce)")).matches } }))))
        })
        manualEco = (fun(): dynamic {
            return (onPresent(evolution) { value7 -> value7.mode } === "ahorro")
        })
        root.HunterAdaptive3823 = global_Object.freeze(jsObject { obj46 ->
            obj46["version"] = "38.23"
            accessor(obj46, "quality", "get", fun(): dynamic {
                return current
            })
            obj46["check"] = sample
            obj46["diagnostics"] = (fun(): dynamic {
                return jsObject { obj49 ->
                    obj49["quality"] = current
                    obj49["checks"] = checks
                    obj49["degrades"] = degrades
                    obj49["badgeAnimations"] = badgeAnimations
                    obj49["note"] = "Indicadores de carga JavaScript; no equivalen a FPS, batería ni temperatura Android."
                }
            })
            obj46["dispose"] = dispose
        })
        if (truth((doc.readyState === "loading"))) {
            doc.addEventListener("DOMContentLoaded", `init`, jsonValue("{\"once\":true}"))
        } else {
            `init`()
        }
        return absent
    }))(global_window, global_document)
}
