@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntercoach3834

import hunter.browser.*

@JsName("document") private external var global_document: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterCoach3834() {
    dynamicValue((fun(): dynamic {
        var modal: dynamic = absent
        var tablet: dynamic = absent
        var managed: dynamic = absent
        var restore: dynamic = absent
        var sync: dynamic = absent
        restore = fun(): dynamic {
            var card: dynamic = absent
            var anchor: dynamic = absent
            if (truth((!truth(managed)))) {
                return absent
            }
            val unpacked5: dynamic = managed
            card = unpacked5["card"]
            anchor = unpacked5["anchor"]
            if (truth(jsAnd(anchor.isConnected) { card.isConnected })) {
                anchor.after(card)
            }
            anchor.remove()
            managed = dynamicValue(null)
            return absent
        }
        sync = fun(): dynamic {
            if (truth(jsOr(jsOr((!truth(tablet.matches))) { (!truth(modal["open"])) }) { (!truth(modal.classList.contains("coach-window33"))) })) {
                restore()
                return absent
            }
            val panel: dynamic = modal.querySelector(".coach-layout33")
            val left: dynamic = onPresent(panel) { value8 -> value8.querySelector(":scope > .shadow-column33") }
            val right: dynamic = onPresent(panel) { value9 -> value9.querySelector(":scope > .coach-content33") }
            if (truth(jsOr((!truth(left))) { (!truth(right)) })) {
                restore()
                return absent
            }
            if (truth(jsAnd(jsAnd(managed) { (managed.card.parentElement === left) }) { (managed.anchor.parentElement === right) })) {
                return absent
            }
            restore()
            val card: dynamic = right.querySelector(":scope > .learning-card")
            if (truth((!truth(card)))) {
                return absent
            }
            val anchor: dynamic = global_document.createComment("V38.34 original Guía personal position")
            card.before(anchor)
            left.append(card)
            managed = jsObject { obj10 ->
                obj10["card"] = card
                obj10["anchor"] = anchor
            }
            return absent
        }
        modal = global_document.getElementById("modal")
        if (truth(jsOr((!truth(modal))) { (jsTypeOf(global_window.openCoach) !== "function") })) {
            return absent
        }
        tablet = global_window.matchMedia("(orientation: landscape) and (min-width: 820px) and (min-height: 520px)")
        managed = dynamicValue(null)
        val beforeOpen: dynamic = global_window.openCoach
        global_window.openCoach = (fun(arg15: dynamic): dynamic {
            val receiver13: dynamic = js("this")
            val callArgs14: dynamic = js("arguments")
            var args: dynamic = absent
            args = argsAfter(callArgs14, 0)
            restore()
            val result: dynamic = beforeOpen.apply(receiver13, args)
            sync()
            return result
        })
        modal.addEventListener("close", restore)
        val onViewportChange: dynamic = (fun(): dynamic {
            return sync()
        })
        run { val owner17: dynamic = tablet; onPresent(owner17.addEventListener) { method16 -> invokeWith(method16, owner17, arrayOf<dynamic>("change", onViewportChange)) } }
        global_window.addEventListener("resize", onViewportChange, jsonValue("{\"passive\":true}"))
        global_window.HunterCoachLayout3834 = jsObject { obj20 ->
            obj20["sync"] = sync
            obj20["restore"] = restore
            accessor(obj20, "rearranged", "get", fun(): dynamic {
                return (!truth((!truth(managed))))
            })
        }
        return absent
    }))()
}
