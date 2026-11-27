@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterdemoload3816

import hunter.browser.*

@JsName("guide11") private external var global_guide11: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Promise") private external var global_Promise: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterDemoLoad3816() {
    dynamicValue((fun(root: dynamic, document: dynamic): dynamic {
        var pending: dynamic = absent
        var idleHandle: dynamic = absent
        var timerHandle: dynamic = absent
        var inFlight: dynamic = absent
        var requests: dynamic = absent
        var skipped: dynamic = absent
        var loaded: dynamic = absent
        var cache: dynamic = absent
        var validId: dynamic = absent
        var modeEco: dynamic = absent
        var currentGuide: dynamic = absent
        var candidate: dynamic = absent
        var clearScheduled: dynamic = absent
        var schedule: dynamic = absent
        candidate = fun(): dynamic {
            val g: dynamic = currentGuide()
            if (truth(jsOr(jsOr((!truth(g))) { (!truth(global_Array.isArray(g.steps))) }) { (!truth(global_Number.isInteger(g.index))) })) {
                return null
            }
            val raw: dynamic = onPresent(g.steps[jsAdd(g.index, 1)]) { value9 -> value9.id }
            if (truth((!truth(validId(raw))))) {
                return null
            }
            if (truth(jsOr((jsTypeOf(onPresent(root.Engine) { value10 -> value10.byId }) !== "function")) { (!truth(root.Engine.byId(raw))) })) {
                return null
            }
            return raw
        }
        clearScheduled = fun(): dynamic {
            if (truth(jsAnd(idleHandle) { (jsTypeOf(root.cancelIdleCallback) === "function") })) {
                root.cancelIdleCallback(idleHandle)
            }
            if (truth(timerHandle)) {
                root.clearTimeout(timerHandle)
            }
            idleHandle = 0
            timerHandle = 0
            pending = dynamicValue(null)
            return absent
        }
        schedule = fun(): dynamic {
            clearScheduled()
            if (truth(modeEco())) {
                run {
                    val previous15: dynamic = numeric(skipped)
                    skipped = jsAdd(previous15, 1)
                }
                return absent
            }
            val id: dynamic = candidate()
            if (truth(jsOr(jsOr(jsOr((!truth(id))) { onPresent(loaded) { value16 -> value16.has(id) } }) { onPresent(cache) { value17 -> value17.has(id) } }) { (inFlight === id) })) {
                run {
                    val previous18: dynamic = numeric(skipped)
                    skipped = jsAdd(previous18, 1)
                }
                return absent
            }
            pending = id
            val work: dynamic = (fun(): dynamic {
                idleHandle = 0
                timerHandle = 0
                val target: dynamic = pending
                pending = dynamicValue(null)
                if (truth(jsOr(jsOr((!truth(target))) { modeEco() }) { (candidate() !== target) })) {
                    return absent
                }
                if (truth(jsOr(jsOr(onPresent(loaded) { value19 -> value19.has(target) }) { onPresent(cache) { value20 -> value20.has(target) } }) { (inFlight === target) })) {
                    return absent
                }
                if (truth(inFlight)) {
                    pending = target
                    return absent
                }
                inFlight = target
                run {
                    val previous21: dynamic = numeric(requests)
                    requests = jsAdd(previous21, 1)
                }
                global_Promise.resolve()["then"]((fun(): dynamic {
                    return root.Motion30.preload(target)
                }))["catch"]((fun(): dynamic {

                    return absent
                }))["finally"]((fun(): dynamic {
                    inFlight = dynamicValue(null)
                    if (truth(jsAnd(pending) { (!truth(modeEco())) })) {
                        val queued: dynamic = pending
                        pending = dynamicValue(null)
                        if (truth((candidate() === queued))) {
                            schedule()
                        }
                    }
                    return absent
                }))
                return absent
            })
            if (truth((jsTypeOf(root.requestIdleCallback) === "function"))) {
                idleHandle = root.requestIdleCallback(work, jsonValue("{\"timeout\":1400}"))
            } else {
                timerHandle = root.setTimeout(work, 220)
            }
            return absent
        }
        if (truth(jsOr(jsOr(root.HunterDemoLoad3816) { (!truth(root.Motion30)) }) { (js("typeof paintGuide11") !== "function") })) {
            return absent
        }
        pending = dynamicValue(null)
        idleHandle = 0
        timerHandle = 0
        inFlight = dynamicValue(null)
        requests = 0
        skipped = 0
        var cancelled: dynamic = 0
        loaded = root.Motion30.loaded
        cache = root.Motion30.cache
        validId = (fun(id: dynamic): dynamic {
            return jsAnd((jsTypeOf(id) === "string")) { dynamicValue(jsRegExp("^[a-zA-Z0-9_-]{1,90}\$", "")).test(id) }
        })
        modeEco = (fun(): dynamic {
            return jsOr(jsOr((!truth((!truth(document.hidden))))) { (!truth((!truth(onPresent(root.HunterPerf380) { value3 -> value3.powerSaver })))) }) { (run { val owner5: dynamic = root; onPresent(owner5.matchMedia) { method4 -> onPresent(invokeWith(method4, owner5, arrayOf<dynamic>("(prefers-reduced-motion: reduce)"))) { value6 -> value6.matches } } } === true) }
        })
        currentGuide = (fun(): dynamic {
            return (if (truth((js("typeof guide11") !== "undefined"))) global_guide11 else null)
        })
        val oldPaint: dynamic = global_paintGuide11
        global_paintGuide11 = (fun(): dynamic {
            val receiver22: dynamic = js("this")
            val callArgs23: dynamic = js("arguments")
            val result: dynamic = oldPaint.apply(receiver22, callArgs23)
            schedule()
            return result
        })
        document.addEventListener("visibilitychange", (fun(): dynamic {
            if (truth(document.hidden)) {
                clearScheduled()
                run {
                    val previous24: dynamic = numeric(cancelled)
                    cancelled = jsAdd(previous24, 1)
                }
            }
            return absent
        }), jsonValue("{\"passive\":true}"))
        val modal: dynamic = run { val owner26: dynamic = document; onPresent(owner26.getElementById) { method25 -> invokeWith(method25, owner26, arrayOf<dynamic>("modal")) } }
        onPresent(modal) { value27 -> value27.addEventListener("close", (fun(): dynamic {
            clearScheduled()
            run {
                val previous28: dynamic = numeric(cancelled)
                cancelled = jsAdd(previous28, 1)
            }
            return absent
        }), jsonValue("{\"passive\":true}")) }
        root.HunterDemoLoad3816 = global_Object.freeze(jsObject { obj31 ->
            obj31["version"] = "38.16"
            obj31["schedule"] = schedule
            obj31["stats"] = (fun(): dynamic {
                return jsObject { obj32 ->
                    obj32["requested"] = requests
                    obj32["skipped"] = skipped
                    obj32["cancelled"] = cancelled
                    obj32["pending"] = (pending !== null)
                    obj32["inFlight"] = (inFlight !== null)
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
