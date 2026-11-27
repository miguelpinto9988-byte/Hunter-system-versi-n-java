@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterresource384

import hunter.browser.*

@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterResource384() {
    dynamicValue((fun(root: dynamic, document: dynamic): dynamic {
        var MAX_MODELS: dynamic = absent
        var RETAIN_MODELS: dynamic = absent
        var cleanupRequest: dynamic = absent
        var cleanupTimeout: dynamic = absent
        var trims: dynamic = absent
        var removedModels: dynamic = absent
        var runningDemonstration: dynamic = absent
        var trimModels: dynamic = absent
        var scheduleTrim: dynamic = absent
        var countActiveMedia: dynamic = absent
        var optimizeThumbnails: dynamic = absent
        var initialize: dynamic = absent
        runningDemonstration = fun(): dynamic {
            val live: dynamic = onPresent(root.HunterV373) { value5 -> onPresent(value5.demos) { value6 -> value6.entry } }
            val modal: dynamic = document.getElementById("modal")
            val exerciseModal: dynamic = global_Boolean(jsAnd(onPresent(modal) { value7 -> value7["open"] }) { jsOr((modal.dataset.guide === "true")) { modal.querySelector("#guidedCanvas, #guidedSlot, #vectorCanvas, .motion-card18, .offline-demo20") } })
            return global_Boolean(jsOr(jsAnd(live) { onPresent(live.canvas) { value8 -> value8.isConnected } }) { exerciseModal })
        }
        trimModels = fun(arg11: dynamic): dynamic {
            var force: dynamic = absent
            force = definedOr(arg11) { false }
            val motion: dynamic = root.Motion30
            val cache: dynamic = onPresent(motion) { value12 -> value12.cache }
            if (truth(jsOr(jsOr(jsOr((!truth(cache))) { (!truth(hasKey("size", cache))) }) { jsAtMost(cache.size, MAX_MODELS) }) { runningDemonstration() })) {
                return 0
            }
            val pinned: dynamic = jsOr(motion.loaded) { jsConstruct(global_Set, js("[]")) }
            val protectedIds: dynamic = jsConstruct(global_Set, js("[]"))
            if (truth(onPresent(root.vectorSession) { value13 -> value13.id })) {
                protectedIds.add(root.vectorSession.id)
            }
            if (truth(jsAnd(onPresent(root.guide11) { value14 -> onPresent(value14.steps) { value15 -> value15.length } }) { global_Number.isInteger(root.guide11.index) })) {
                protectedIds.add(onPresent(root.guide11.steps[root.guide11.index]) { value16 -> value16.id })
            }
            var removed: dynamic = 0
            if (true) {
                val iterator17: dynamic = iteratorOf(cache.keys())
                while (true) {
                    val next18: dynamic = iterator17.next()
                    if (truth(next18.done)) break
                    var id: dynamic = absent
                    id = next18.value
                    if (truth(jsAtMost(cache.size, RETAIN_MODELS))) {
                        break
                    }
                    if (truth(protectedIds.has(id))) {
                        continue
                    }
                    if (truth(pinned.has(id))) {
                        pinned.delete(id)
                    }
                    cache.delete(id)
                    run {
                        val previous19: dynamic = numeric(removed)
                        removed = jsAdd(previous19, 1)
                    }
                }
            }
            if (truth(removed)) {
                run {
                    val previous20: dynamic = numeric(trims)
                    trims = jsAdd(previous20, 1)
                }
                run {
                    removedModels = jsAdd(removedModels, removed)
                }
            }
            return removed
        }
        scheduleTrim = fun(arg23: dynamic): dynamic {
            var force: dynamic = absent
            force = definedOr(arg23) { false }
            if (truth(cleanupRequest)) {
                return absent
            }
            if (truth((jsTypeOf(root.requestIdleCallback) === "function"))) {
                cleanupRequest = root.requestIdleCallback((fun(): dynamic {
                    cleanupRequest = 0
                    trimModels(force)
                    return absent
                }), jsonValue("{\"timeout\":1500}"))
            } else {
                cleanupRequest = (-numeric(1))
                cleanupTimeout = root.setTimeout((fun(): dynamic {
                    cleanupRequest = 0
                    cleanupTimeout = 0
                    trimModels(force)
                    return absent
                }), 400)
            }
            return absent
        }
        countActiveMedia = fun(): dynamic {
            return dynamicValue(jsArray { array27 ->
                appendAll(array27, document.querySelectorAll("video"))
            }).filter((fun(v: dynamic): dynamic {
                return (!truth(v.paused))
            })).length
        }
        optimizeThumbnails = fun(arg30: dynamic): dynamic {
            var scope: dynamic = absent
            scope = definedOr(arg30) { document }
            if (truth((!truth(scope.querySelectorAll)))) {
                return absent
            }
            if (true) {
                val iterator31: dynamic = iteratorOf(scope.querySelectorAll(".demo-gallery img, .routine-list img, .exercise-grid img"))
                while (true) {
                    val next32: dynamic = iterator31.next()
                    if (truth(next32.done)) break
                    var img: dynamic = absent
                    img = next32.value
                    if (truth(jsOr(img.closest(".hero-figure33, .evolution-art28")) { (img.dataset.hunterPriority === "high") })) {
                        continue
                    }
                    img.decoding = "async"
                    if (truth((!truth(img.complete)))) {
                        img.loading = "lazy"
                    }
                }
            }
            return absent
        }
        initialize = fun(): dynamic {
            optimizeThumbnails(document)
            val modal: dynamic = document.getElementById("modal")
            if (truth(modal)) {
                modal.addEventListener("close", (fun(): dynamic {
                    return scheduleTrim()
                }), jsonValue("{\"passive\":true}"))
            }
            return absent
        }
        val VERSION: dynamic = "38.4"
        MAX_MODELS = 96
        RETAIN_MODELS = 48
        cleanupRequest = 0
        cleanupTimeout = 0
        trims = 0
        removedModels = 0
        document.addEventListener("visibilitychange", (fun(): dynamic {
            if (truth(document.hidden)) {
                if (true) {
                    val iterator37: dynamic = iteratorOf(document.querySelectorAll("video"))
                    while (true) {
                        val next38: dynamic = iterator37.next()
                        if (truth(next38.done)) break
                        var video: dynamic = absent
                        video = next38.value
                        if (truth((!truth(video.paused)))) {
                            video.pause()
                        }
                    }
                }
                scheduleTrim(true)
            }
            return absent
        }))
        root.addEventListener("pagehide", (fun(): dynamic {
            if (true) {
                val iterator39: dynamic = iteratorOf(document.querySelectorAll("video"))
                while (true) {
                    val next40: dynamic = iterator39.next()
                    if (truth(next40.done)) break
                    var video: dynamic = absent
                    video = next40.value
                    if (truth((!truth(video.paused)))) {
                        video.pause()
                    }
                }
            }
            trimModels(true)
            return absent
        }), jsonValue("{\"passive\":true}"))
        if (truth((document.readyState === "loading"))) {
            document.addEventListener("DOMContentLoaded", initialize, jsonValue("{\"once\":true}"))
        } else {
            initialize()
        }
        root.HunterResource384 = global_Object.freeze(jsObject { obj45 ->
            obj45["version"] = VERSION
            obj45["trimModels"] = trimModels
            obj45["optimizeThumbnails"] = optimizeThumbnails
            obj45["snapshot"] = (fun(): dynamic {
                return jsObject { obj46 ->
                    obj46["version"] = VERSION
                    obj46["cachedMotionModels"] = nullableOr(onPresent(root.Motion30) { value47 -> onPresent(value47.cache) { value48 -> value48.size } }) { null }
                    obj46["protectedPreloadedModels"] = nullableOr(onPresent(root.Motion30) { value49 -> onPresent(value49.loaded) { value50 -> value50.size } }) { null }
                    obj46["modelCacheLimit"] = MAX_MODELS
                    obj46["totalTrimOperations"] = trims
                    obj46["generatedModelsDiscarded"] = removedModels
                    obj46["activeMedia"] = countActiveMedia()
                    obj46["particleDraws"] = nullableOr(onPresent(root.Ambient29) { value51 -> value51.paints373 }) { null }
                    obj46["note"] = "Auditoría interna; FPS, temperatura, batería y GPU deben medirse en un dispositivo Android."
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
