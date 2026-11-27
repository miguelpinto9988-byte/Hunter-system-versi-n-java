@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunternavigation3825

import hunter.browser.*

@JsName("tab") private external var global_tab: dynamic
@JsName("go") private external var global_go: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterNavigation3825() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var reduced: dynamic = absent
        var eco: dynamic = absent
        var supported: dynamic = absent
        var canTransition: dynamic = absent
        supported = fun(): dynamic {
            return (jsTypeOf(doc.startViewTransition) === "function")
        }
        canTransition = fun(next: dynamic): dynamic {
            if (truth(doc.querySelector("#app > nav.nav-routes3855"))) {
                return false
            }
            return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(supported()) { (!truth(doc.hidden)) }) { (!truth(reduced())) }) { (!truth(eco())) }) { (!truth(doc.querySelector("#modal[open]"))) }) { (js("typeof tab") !== "undefined") }) { (global_tab !== next) }
        }
        if (truth(root.HunterNavigation3825)) {
            return absent
        }
        reduced = (fun(): dynamic {
            return (!truth((!truth(run { val owner4: dynamic = root; onPresent(owner4.matchMedia) { method3 -> onPresent(invokeWith(method3, owner4, arrayOf<dynamic>("(prefers-reduced-motion: reduce)"))) { value5 -> value5.matches } } }))))
        })
        eco = (fun(): dynamic {
            return jsOr((onPresent(root.HunterEvolution385) { value6 -> value6.budget } === "eco")) { (onPresent(root.HunterEvolution385) { value7 -> value7.mode } === "ahorro") }
        })
        var started: dynamic = 0
        var skipped: dynamic = 0
        var failed: dynamic = 0
        var sequence48: dynamic = 0
        var active48: dynamic = dynamicValue(null)
        if (truth((js("typeof go") !== "function"))) {
            return absent
        }
        val navigate: dynamic = global_go
        global_go = (fun(target: dynamic): dynamic {
            val receiver12: dynamic = js("this")
            val callArgs13: dynamic = js("arguments")
            val ticket48: dynamic = run {
                val previous14: dynamic = numeric(sequence48)
                val assigned15: dynamic = jsAdd(previous14, 1)
                sequence48 = assigned15
                assigned15
            }
            if (truth(active48)) {
                try {
                    active48.skipTransition()
                } catch (_: dynamic) {

                }
                active48 = dynamicValue(null)
            }
            if (truth((!truth(canTransition(target))))) {
                run {
                    val previous16: dynamic = numeric(skipped)
                    skipped = jsAdd(previous16, 1)
                }
                return navigate.apply(receiver12, callArgs13)
            }
            val self: dynamic = receiver12
            val args: dynamic = callArgs13
            try {
                val effect: dynamic = doc.startViewTransition((fun(): dynamic {
                    if (truth((ticket48 === sequence48))) {
                        return navigate.apply(self, args)
                    }
                    return absent
                }))
                active48 = effect
                run {
                    val previous17: dynamic = numeric(started)
                    started = jsAdd(previous17, 1)
                }
                if (truth(onPresent(effect) { value18 -> onPresent(value18.finished) { value19 -> value19["then"] } })) {
                    effect.finished["then"]((fun(): dynamic {
                        if (truth((active48 === effect))) {
                            active48 = dynamicValue(null)
                        }
                        return absent
                    }), (fun(): dynamic {
                        run {
                            val previous20: dynamic = numeric(failed)
                            failed = jsAdd(previous20, 1)
                        }
                        if (truth((active48 === effect))) {
                            active48 = dynamicValue(null)
                        }
                        return absent
                    }))
                }
                onPresent(effect) { value21 -> onPresent(value21.ready) { value22 -> run { val owner24: dynamic = value22; onPresent(owner24["catch"]) { method23 -> invokeWith(method23, owner24, arrayOf<dynamic>((fun(): dynamic {

                    return absent
                }))) } } } }
                onPresent(effect) { value25 -> onPresent(value25.updateCallbackDone) { value26 -> run { val owner28: dynamic = value26; onPresent(owner28["catch"]) { method27 -> invokeWith(method27, owner28, arrayOf<dynamic>((fun(): dynamic {

                    return absent
                }))) } } } }
                return effect
            } catch (_: dynamic) {
                run {
                    val previous29: dynamic = numeric(failed)
                    failed = jsAdd(previous29, 1)
                }
                return navigate.apply(receiver12, callArgs13)
            }
            return absent
        })
        root.HunterNavigation3825 = global_Object.freeze(jsObject { obj32 ->
            obj32["version"] = "38.25"
            obj32["diagnostics"] = (fun(): dynamic {
                return jsObject { obj33 ->
                    obj33["started"] = started
                    obj33["skipped"] = skipped
                    obj33["failed"] = failed
                    obj33["supported"] = supported()
                    obj33["note"] = "Ninguna cifra es una medición de FPS ni de rendimiento Android."
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
