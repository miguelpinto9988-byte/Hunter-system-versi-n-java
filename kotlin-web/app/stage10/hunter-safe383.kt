@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntersafe383

import hunter.browser.*

@JsName("document") private external var global_document: dynamic
@JsName("navigator") private external var global_navigator: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("getComputedStyle") private external var global_getComputedStyle: dynamic
@JsName("parseFloat") private external var global_parseFloat: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic

internal fun installHunterSafe383() {
    dynamicValue((fun(): dynamic {
        var root: dynamic = absent
        var isAndroid: dynamic = absent
        var raf: dynamic = absent
        var confirmedEdgeToEdge: dynamic = absent
        var px: dynamic = absent
        var asPixels: dynamic = absent
        var cssSafeAreas: dynamic = absent
        var occupiesSystemArea: dynamic = absent
        var refresh: dynamic = absent
        var schedule: dynamic = absent
        cssSafeAreas = fun(): dynamic {
            var probe: dynamic = global_document.getElementById("hunter-safe-probe383")
            if (truth((!truth(probe)))) {
                probe = global_document.createElement("div")
                probe.id = "hunter-safe-probe383"
                probe.setAttribute("aria-hidden", "true")
                probe.style.cssText = "position:fixed!important;inset:auto!important;left:-2000px!important;top:-2000px!important;width:0!important;height:0!important;visibility:hidden!important;pointer-events:none!important;padding:env(safe-area-inset-top, 0px) env(safe-area-inset-right, 0px) env(safe-area-inset-bottom, 0px) env(safe-area-inset-left, 0px)!important;"
                global_document.body.appendChild(probe)
            }
            val c: dynamic = global_getComputedStyle(probe)
            return jsObject { obj9 ->
                obj9["top"] = px(global_parseFloat(c.paddingTop))
                obj9["right"] = px(global_parseFloat(c.paddingRight))
                obj9["bottom"] = px(global_parseFloat(c.paddingBottom))
                obj9["left"] = px(global_parseFloat(c.paddingLeft))
            }
        }
        occupiesSystemArea = fun(): dynamic {
            if (truth((!truth(isAndroid)))) {
                return false
            }
            val screenH: dynamic = jsOr(global_Number(onPresent(global_window.screen) { value12 -> value12.height })) { 0 }
            val docH: dynamic = jsOr(global_Number(global_window.innerHeight)) { 0 }
            if (truth(jsOr((!truth(screenH))) { (!truth(docH)) })) {
                return false
            }
            val difference: dynamic = jsSubtract(screenH, docH)
            if (truth(jsAnd(jsAtLeast(difference, (-numeric(12)))) { jsLess(difference, 42) })) {
                confirmedEdgeToEdge = true
            }
            return confirmedEdgeToEdge
        }
        refresh = fun(arg15: dynamic): dynamic {
            var forceFit: dynamic = absent
            forceFit = definedOr(arg15) { true }
            if (truth((!truth(global_document.body)))) {
                return absent
            }
            val area: dynamic = cssSafeAreas()
            val fullScreen: dynamic = occupiesSystemArea()
            val landscape: dynamic = jsMore(jsOr(global_window.innerWidth) { 0 }, jsOr(global_window.innerHeight) { 0 })
            val top: dynamic = global_Math.max(area.top, (if (truth(fullScreen)) (if (truth(landscape)) 24 else 30) else 0))
            val bottom: dynamic = global_Math.max(area.bottom, (if (truth(fullScreen)) (if (truth(landscape)) 12 else 18) else 0))
            val sides: dynamic = jsObject { obj16 ->
                obj16["top"] = top
                obj16["right"] = area.right
                obj16["bottom"] = bottom
                obj16["left"] = area.left
            }
            var changed: dynamic = false
            global_Object.entries(sides).forEach((fun(arg17: dynamic): dynamic {
                var side: dynamic = absent
                var `value`: dynamic = absent
                val unpacked18: dynamic = fromIterable(arg17)
                side = unpacked18[0]
                `value` = unpacked18[1]
                val `property`: dynamic = jsAdd("--modal-safe-", side)
                val `numeric`: dynamic = asPixels(`value`)
                if (truth((root.style.getPropertyValue(`property`) !== `numeric`))) {
                    root.style.setProperty(`property`, `numeric`)
                    changed = true
                }
                return absent
            }))
            if (true) {
                val iterator19: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>("--hunter-top-inset383", top), arrayOf<dynamic>("--hunter-bottom-inset383", bottom)))
                while (true) {
                    val next20: dynamic = iterator19.next()
                    if (truth(next20.done)) break
                    var `property`: dynamic = absent
                    var `value`: dynamic = absent
                    val unpacked21: dynamic = fromIterable(next20.value)
                    `property` = unpacked21[0]
                    `value` = unpacked21[1]
                    val `numeric`: dynamic = asPixels(`value`)
                    if (truth((root.style.getPropertyValue(`property`) !== `numeric`))) {
                        root.style.setProperty(`property`, `numeric`)
                        changed = true
                    }
                }
            }
            root.classList.add("hunter-insets383")
            if (truth(jsAnd(jsAnd(forceFit) { changed }) { onPresent(global_document.getElementById("modal")) { value22 -> value22["open"] } })) {
                onPresent(global_window.ModalViewport) { value23 -> run { val owner25: dynamic = value23; onPresent(owner25.fit) { method24 -> invokeWith(method24, owner25, js("[]")) } } }
            }
            return absent
        }
        schedule = fun(): dynamic {
            if (truth(raf)) {
                return absent
            }
            raf = global_requestAnimationFrame((fun(): dynamic {
                raf = 0
                refresh()
                return absent
            }))
            return absent
        }
        root = global_document.documentElement
        isAndroid = dynamicValue(jsRegExp("Android", "i")).test(jsOr(global_navigator.userAgent) { "" })
        raf = 0
        confirmedEdgeToEdge = false
        px = (fun(n: dynamic): dynamic {
            return global_Math.max(0, (if (truth(global_Number.isFinite(n))) n else 0))
        })
        asPixels = (fun(n: dynamic): dynamic {
            return jsAdd(jsDivide(global_Math.round(jsMultiply(px(n), 100)), 100), "px")
        })
        if (truth((global_document.readyState === "loading"))) {
            global_document.addEventListener("DOMContentLoaded", (fun(): dynamic {
                return refresh()
            }), jsonValue("{\"once\":true}"))
        } else {
            refresh()
        }
        global_window.addEventListener("resize", schedule, jsonValue("{\"passive\":true}"))
        global_window.addEventListener("orientationchange", schedule, jsonValue("{\"passive\":true}"))
        onPresent(global_window.visualViewport) { value28 -> value28.addEventListener("resize", schedule, jsonValue("{\"passive\":true}")) }
        onPresent(global_window.visualViewport) { value29 -> value29.addEventListener("scroll", schedule, jsonValue("{\"passive\":true}")) }
        global_window.HunterInsets383 = jsObject { obj32 ->
            obj32["refresh"] = refresh
            obj32["occupiesSystemArea"] = occupiesSystemArea
            obj32["cssSafeAreas"] = cssSafeAreas
        }
        return absent
    }))()
}
