@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunternozoom3844

import hunter.browser.*

@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Object") private external var global_Object: dynamic

internal fun installHunterNozoom3844() {
    dynamicValue((fun(): dynamic {
        var blockMultiTouch: dynamic = absent
        var blockGesture: dynamic = absent
        blockMultiTouch = fun(event: dynamic): dynamic {
            if (truth(jsAnd(jsAnd(event.cancelable) { event.touches }) { jsMore(event.touches.length, 1) })) {
                event.preventDefault()
            }
            return absent
        }
        blockGesture = fun(event: dynamic): dynamic {
            if (truth(event.cancelable)) {
                event.preventDefault()
            }
            return absent
        }
        if (truth(global_window.HunterNoZoom3844)) {
            return absent
        }
        global_document.addEventListener("touchmove", blockMultiTouch, jsonValue("{\"capture\":true,\"passive\":false}"))
        global_document.addEventListener("gesturestart", blockGesture, jsonValue("{\"capture\":true,\"passive\":false}"))
        global_document.addEventListener("gesturechange", blockGesture, jsonValue("{\"capture\":true,\"passive\":false}"))
        global_window.HunterNoZoom3844 = global_Object.freeze(jsonValue("{\"version\":\"38.44\"}"))
        return absent
    }))()
}
