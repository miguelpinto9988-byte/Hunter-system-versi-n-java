@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterlist3819

import hunter.browser.*

@JsName("Math") private external var global_Math: dynamic
@JsName("performance") private external var global_performance: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterList3819() {
    dynamicValue((fun(root: dynamic, document: dynamic): dynamic {
        var cards: dynamic = absent
        var BATCH: dynamic = absent
        var MORE: dynamic = absent
        var canDefer: dynamic = absent
        var generation: dynamic = absent
        var visible: dynamic = absent
        var pending: dynamic = absent
        var entries: dynamic = absent
        var marker: dynamic = absent
        var watching: dynamic = absent
        var observer: dynamic = absent
        var appended: dynamic = absent
        var aborted: dynamic = absent
        var maxAppendMs48: dynamic = absent
        var attachFrame: dynamic = absent
        var attachChecks: dynamic = absent
        var coalescedMutations: dynamic = absent
        var cancelPending: dynamic = absent
        var abandon: dynamic = absent
        var activeMarker: dynamic = absent
        var appendBatch: dynamic = absent
        var queueBatch: dynamic = absent
        var markerNearViewport: dynamic = absent
        var attach: dynamic = absent
        var scheduleAttach: dynamic = absent
        cancelPending = fun(): dynamic {
            if (truth(pending)) {
                root.cancelAnimationFrame(pending)
            }
            pending = 0
            return absent
        }
        abandon = fun(): dynamic {
            run {
                val previous7: dynamic = numeric(generation)
                generation = jsAdd(previous7, 1)
            }
            cancelPending()
            if (truth(jsAnd(observer) { watching })) {
                observer.unobserve(watching)
            }
            watching = dynamicValue(null)
            marker = dynamicValue(null)
            entries = js("[]")
            visible = 0
            return absent
        }
        activeMarker = fun(): dynamic {
            val grid: dynamic = document.getElementById("exerciseResults")
            return jsAnd(jsAnd(jsAnd(jsAnd(grid) { marker }) { marker.isConnected }) { (marker.parentElement === grid) }) { onPresent(marker.classList) { value10 -> value10.contains(MORE) } }
        }
        appendBatch = fun(token: dynamic): dynamic {
            pending = 0
            if (truth(jsOr(jsOr((token !== generation)) { document.hidden }) { (!truth(activeMarker())) })) {
                run {
                    val previous13: dynamic = numeric(aborted)
                    aborted = jsAdd(previous13, 1)
                }
                return absent
            }
            val end: dynamic = global_Math.min(entries.length, jsAdd(visible, BATCH))
            val group: dynamic = entries.slice(visible, end)
            val began: dynamic = global_performance.now()
            if (truth(group.length)) {
                marker.insertAdjacentHTML("beforebegin", cards(group))
            }
            maxAppendMs48 = global_Math.max(maxAppendMs48, jsSubtract(global_performance.now(), began))
            visible = end
            run {
                appended = jsAdd(appended, group.length)
            }
            if (truth(jsAtLeast(visible, entries.length))) {
                if (truth(observer)) {
                    observer.unobserve(marker)
                }
                marker.remove()
                marker = run {
                    val assigned14: dynamic = dynamicValue(null)
                    watching = assigned14
                    assigned14
                }
                entries = js("[]")
            } else {
                if (truth(observer)) {
                    observer.unobserve(marker)
                    observer.observe(marker)
                }
            }
            return absent
        }
        queueBatch = fun(): dynamic {
            if (truth(jsOr(jsOr(pending) { document.hidden }) { (!truth(activeMarker())) })) {
                return absent
            }
            val token: dynamic = generation
            pending = root.requestAnimationFrame((fun(): dynamic {
                return appendBatch(token)
            }))
            return absent
        }
        markerNearViewport = fun(): dynamic {
            if (truth(jsOr((!truth(activeMarker()))) { (jsTypeOf(marker.getBoundingClientRect) !== "function") })) {
                return false
            }
            val r: dynamic = marker.getBoundingClientRect()
            return jsAnd(jsLess(r.top, jsAdd(jsOr(root.innerHeight) { 640 }, 560))) { jsAtLeast(r.bottom, (-numeric(560))) }
        }
        attach = fun(): dynamic {
            run {
                val previous21: dynamic = numeric(attachChecks)
                attachChecks = jsAdd(previous21, 1)
            }
            val grid: dynamic = document.getElementById("exerciseResults")
            val target: dynamic = onPresent(grid) { value22 -> value22.querySelector(jsAdd(".", MORE)) }
            if (truth(jsOr((!truth(target))) { (!truth(entries.length)) })) {
                if (truth(jsAnd(observer) { watching })) {
                    observer.unobserve(watching)
                }
                watching = dynamicValue(null)
                marker = dynamicValue(null)
                if (truth(jsAnd((!truth(grid))) { entries.length })) {
                    abandon()
                }
                return absent
            }
            if (truth((target === watching))) {
                return absent
            }
            if (truth(jsAnd(observer) { watching })) {
                observer.unobserve(watching)
            }
            marker = run {
                val assigned23: dynamic = target
                watching = assigned23
                assigned23
            }
            observer.observe(target)
            return absent
        }
        scheduleAttach = fun(): dynamic {
            if (truth(jsOr(document.hidden) { (!truth(canDefer)) })) {
                return absent
            }
            if (truth(attachFrame)) {
                run {
                    val previous26: dynamic = numeric(coalescedMutations)
                    coalescedMutations = jsAdd(previous26, 1)
                }
                return absent
            }
            attachFrame = root.requestAnimationFrame((fun(): dynamic {
                attachFrame = 0
                if (truth((!truth(document.hidden)))) {
                    attach()
                }
                return absent
            }))
            return absent
        }
        if (truth(root.HunterList3819)) {
            return absent
        }
        cards = root.exerciseCards
        if (truth((jsTypeOf(cards) !== "function"))) {
            return absent
        }
        BATCH = 12
        val LIMIT: dynamic = 72
        MORE = "hunter-more3819"
        canDefer = jsAnd(jsAnd((jsTypeOf(root.IntersectionObserver) === "function")) { (jsTypeOf(root.MutationObserver) === "function") }) { (jsTypeOf(root.requestAnimationFrame) === "function") }
        generation = 0
        visible = 0
        pending = 0
        entries = js("[]")
        marker = dynamicValue(null)
        watching = dynamicValue(null)
        observer = dynamicValue(null)
        var mountObserver: dynamic = dynamicValue(null)
        appended = 0
        var renderCalls: dynamic = 0
        aborted = 0
        maxAppendMs48 = 0
        attachFrame = 0
        attachChecks = 0
        coalescedMutations = 0
        root.exerciseCards = (fun(list: dynamic): dynamic {
            val receiver29: dynamic = js("this")
            val callArgs30: dynamic = js("arguments")
            run {
                val previous31: dynamic = numeric(renderCalls)
                renderCalls = jsAdd(previous31, 1)
            }
            if (truth(jsOr(jsOr((!truth(canDefer))) { (!truth(global_Array.isArray(list))) }) { jsAtMost(list.length, LIMIT) })) {
                abandon()
                return cards.apply(receiver29, callArgs30)
            }
            abandon()
            entries = list.slice()
            visible = BATCH
            val first: dynamic = cards(entries.slice(0, BATCH))
            return jsAdd(jsAdd(jsAdd(first, "<div class=\""), MORE), "\" aria-hidden=\"true\"></div>")
        })
        if (truth(canDefer)) {
            observer = jsConstruct(root.IntersectionObserver, arrayOf<dynamic>((fun(intersections: dynamic): dynamic {
                if (truth(intersections.some((fun(e: dynamic): dynamic {
                    return jsAnd((e.target === watching)) { e.isIntersecting }
                })))) {
                    queueBatch()
                }
                return absent
            }), jsonValue("{\"root\":null,\"rootMargin\":\"560px 0px\",\"threshold\":0}")))
            val app: dynamic = document.getElementById("app")
            if (truth(app)) {
                mountObserver = jsConstruct(root.MutationObserver, arrayOf<dynamic>(scheduleAttach))
                mountObserver.observe(app, jsonValue("{\"childList\":true,\"subtree\":true}"))
            }
            document.addEventListener("visibilitychange", (fun(): dynamic {
                if (truth(document.hidden)) {
                    cancelPending()
                } else {
                    scheduleAttach()
                    if (truth(markerNearViewport())) {
                        queueBatch()
                    }
                }
                return absent
            }), jsonValue("{\"passive\":true}"))
            root.addEventListener("pagehide", (fun(): dynamic {
                cancelPending()
                if (truth(attachFrame)) {
                    root.cancelAnimationFrame(attachFrame)
                }
                attachFrame = 0
                return absent
            }), jsonValue("{\"passive\":true}"))
        }
        root.HunterList3819 = global_Object.freeze(jsObject { obj34 ->
            obj34["version"] = "38.19"
            obj34["refresh"] = scheduleAttach
            obj34["diagnostics"] = (fun(): dynamic {
                return global_Object.freeze(jsObject { obj35 ->
                    obj35["renderCalls"] = renderCalls
                    obj35["appended"] = appended
                    obj35["aborted"] = aborted
                    obj35["deferred"] = canDefer
                    obj35["pendingCount"] = (if (truth(entries.length)) jsSubtract(entries.length, visible) else 0)
                    obj35["hasSentinel"] = (!truth((!truth(watching))))
                    obj35["attachChecks"] = attachChecks
                    obj35["coalescedMutations"] = coalescedMutations
                    obj35["attachQueued"] = (!truth((!truth(attachFrame))))
                    obj35["batchSize"] = BATCH
                    obj35["maxAppendMs"] = maxAppendMs48
                })
            })
        })
        return absent
    }))(global_window, global_document)
}
