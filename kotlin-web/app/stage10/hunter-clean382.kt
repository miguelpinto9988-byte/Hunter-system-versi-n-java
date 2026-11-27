@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterclean382

import hunter.browser.*

@JsName("document") private external var global_document: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("UI26") private external var global_UI26: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterClean382() {
    dynamicValue((fun(): dynamic {
        val relocate: dynamic = (fun(): dynamic {
            val modal: dynamic = global_document.querySelector("#modal")
            if (truth((!truth(onPresent(modal) { value3 -> value3["open"] })))) {
                return absent
            }
            val timed: dynamic = modal.querySelector("#guidedTiming375")
            val canvas: dynamic = modal.querySelector("#guidedCanvas")
            val host: dynamic = jsOr(onPresent(canvas) { value4 -> value4.closest(".guided-demo") }) { modal.querySelector(".guided-step .guided-demo") }
            if (truth(jsAnd(jsAnd(timed) { host }) { (timed.parentNode !== host) })) {
                host.appendChild(timed)
            }
            if (truth(jsAnd(host) { timed })) {
                host.classList.add("clean-demo382")
            }
            val vector: dynamic = modal.querySelector("#vectorTiming375")
            val card: dynamic = modal.querySelector(".motion-card18")
            if (truth(jsAnd(jsAnd(card) { vector }) { (vector.parentNode !== card) })) {
                card.insertBefore(vector, jsOr(onPresent(card.firstChild) { value5 -> value5.nextSibling }) { null })
            }
            return absent
        })
        if (truth((js("typeof paintGuide11") === "function"))) {
            val prior: dynamic = global_paintGuide11
            global_paintGuide11 = (fun(): dynamic {
                val receiver6: dynamic = js("this")
                val callArgs7: dynamic = js("arguments")
                val result: dynamic = prior.apply(receiver6, callArgs7)
                relocate()
                return result
            })
        }
        if (truth(jsAnd((js("typeof UI26") !== "undefined")) { (jsTypeOf(global_UI26.decorate) === "function") })) {
            val prior: dynamic = global_UI26.decorate
            global_UI26.decorate = (fun(): dynamic {
                val receiver10: dynamic = js("this")
                val callArgs11: dynamic = js("arguments")
                val result: dynamic = prior.apply(receiver10, callArgs11)
                relocate()
                return result
            })
        }
        if (truth((js("typeof modal") === "function"))) {
            val prior: dynamic = global_modal
            global_modal = (fun(): dynamic {
                val receiver12: dynamic = js("this")
                val callArgs13: dynamic = js("arguments")
                val result: dynamic = prior.apply(receiver12, callArgs13)
                relocate()
                return result
            })
        }
        global_window.HunterClean382 = jsObject { obj16 ->
            obj16["relocate"] = relocate
        }
        return absent
    }))()
}
