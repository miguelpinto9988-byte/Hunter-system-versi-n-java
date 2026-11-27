@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterbundle3874135

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("IntersectionObserver") private external var global_IntersectionObserver: dynamic
@JsName("Evolution28") private external var global_Evolution28: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Visual33") private external var global_Visual33: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("Warm25") private external var global_Warm25: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("BlockSession22") private external var global_BlockSession22: dynamic
@JsName("TrainingProgression19") private external var global_TrainingProgression19: dynamic
@JsName("Warm24") private external var global_Warm24: dynamic
@JsName("ambientScroll3817") private external var global_ambientScroll3817: dynamic
@JsName("scrollFrame3817") private external var global_scrollFrame3817: dynamic
@JsName("cancelAnimationFrame") private external var global_cancelAnimationFrame: dynamic
@JsName("pendingScroll3817") private external var global_pendingScroll3817: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("Boot37") private external var global_Boot37: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterBundle3874135() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var decorations: dynamic = absent
        var decorationObserver: dynamic = absent
        var syncDecorations: dynamic = absent
        syncDecorations = fun(): dynamic {
            if (truth((!truth(decorationObserver)))) {
                return absent
            }
            if (true) {
                val iterator10: dynamic = iteratorOf(decorations)
                while (true) {
                    val next11: dynamic = iterator10.next()
                    if (truth(next11.done)) break
                    var node: dynamic = absent
                    node = next11.value
                    if (truth((!truth(node.isConnected)))) {
                        decorationObserver.unobserve(node)
                        decorations.delete(node)
                    }
                }
            }
            if (true) {
                val iterator12: dynamic = iteratorOf(doc.querySelectorAll("#app .monarch-hero33"))
                while (true) {
                    val next13: dynamic = iterator12.next()
                    if (truth(next13.done)) break
                    var node: dynamic = absent
                    node = next13.value
                    if (truth((!truth(decorations.has(node))))) {
                        decorations.add(node)
                        decorationObserver.observe(node)
                    }
                }
            }
            return absent
        }
        if (truth(root.HunterOptimize48)) {
            return absent
        }
        val stats: dynamic = jsonValue("{\"minuteQueries\":0,\"minuteFallbacks\":0,\"coalescedLayouts\":0,\"coalescedThemes\":0,\"renders\":0,\"offscreenPauses\":0}")
        decorations = jsConstruct(global_Set, js("[]"))
        decorationObserver = (if (truth((js("typeof IntersectionObserver") === "function"))) jsConstruct(global_IntersectionObserver, arrayOf<dynamic>((fun(rows: dynamic): dynamic {
            if (true) {
                val iterator3: dynamic = iteratorOf(rows)
                while (true) {
                    val next4: dynamic = iterator3.next()
                    if (truth(next4.done)) break
                    var row: dynamic = absent
                    row = next4.value
                    val pause: dynamic = (!truth(row.isIntersecting))
                    row.target.classList.toggle("hunter-offscreen48", pause)
                    if (truth(pause)) {
                        run {
                            val target5: dynamic = stats
                            val previous7: dynamic = numeric(target5.offscreenPauses)
                            target5.offscreenPauses = jsAdd(previous7, 1)
                        }
                    }
                }
            }
            return absent
        }), jsonValue("{\"rootMargin\":\"100px\"}"))) else null)
        var renderDepth: dynamic = 0
        var pendingSelection: dynamic = dynamicValue(null)
        var lastTheme: dynamic = dynamicValue(null)
        val legacyLayout: dynamic = global_Evolution28.layout
        val legacyTheme: dynamic = global_applyTheme
        val legacyRender: dynamic = global_render
        global_Evolution28.layout = (fun(arg18: dynamic): dynamic {
            val receiver16: dynamic = js("this")
            var selection: dynamic = absent
            selection = definedOr(arg18) { jsOr(receiver16.current) { receiver16.selection() } }
            if (truth(renderDepth)) {
                receiver16.current = selection
                pendingSelection = selection
                run {
                    val target21: dynamic = stats
                    val previous23: dynamic = numeric(target21.coalescedLayouts)
                    target21.coalescedLayouts = jsAdd(previous23, 1)
                }
                return absent
            }
            return legacyLayout.call(receiver16, selection)
        })
        global_applyTheme = (fun(arg26: dynamic, arg27: dynamic): dynamic {
            val receiver24: dynamic = js("this")
            val callArgs25: dynamic = js("arguments")
            var p: dynamic = absent
            p = definedOr(arg26) { jsOr(global_state.profile) { js("({})") } }
            var prefs: dynamic = absent
            prefs = definedOr(arg27) { global_Visual33.preferences() }
            val key: dynamic = (if (truth(renderDepth)) global_JSON.stringify(arrayOf<dynamic>(p, prefs)) else null)
            if (truth(jsAnd(key) { (key === lastTheme) })) {
                run {
                    val target28: dynamic = stats
                    val previous30: dynamic = numeric(target28.coalescedThemes)
                    target28.coalescedThemes = jsAdd(previous30, 1)
                }
                return absent
            }
            if (truth(renderDepth)) {
                lastTheme = key
            }
            return legacyTheme.apply(receiver24, callArgs25)
        })
        global_render = (fun(): dynamic {
            val receiver31: dynamic = js("this")
            val callArgs32: dynamic = js("arguments")
            if (truth(global_Runtime20.initializing)) {
                return absent
            }
            if (truth((!truth(renderDepth)))) {
                lastTheme = dynamicValue(null)
                pendingSelection = dynamicValue(null)
            }
            run {
                val previous33: dynamic = numeric(renderDepth)
                renderDepth = jsAdd(previous33, 1)
            }
            try {
                return legacyRender.apply(receiver31, callArgs32)
            } finally {
                if (truth((!truth(run {
                    val previous34: dynamic = numeric(renderDepth)
                    val assigned35: dynamic = jsSubtract(previous34, 1)
                    renderDepth = assigned35
                    assigned35
                })))) {
                    if (truth(pendingSelection)) {
                        val selection: dynamic = pendingSelection
                        pendingSelection = dynamicValue(null)
                        legacyLayout.call(global_Evolution28, selection)
                    }
                    syncDecorations()
                    run {
                        val target36: dynamic = stats
                        val previous38: dynamic = numeric(target36.renders)
                        target36.renders = jsAdd(previous38, 1)
                    }
                }
            }
            return absent
        })
        val legacyMinutes: dynamic = global_Warm25.minutes
        global_Warm25.minutes = (fun(r: dynamic, p: dynamic): dynamic {
            val receiver41: dynamic = js("this")
            val callArgs42: dynamic = js("arguments")
            run {
                val target43: dynamic = stats
                val previous45: dynamic = numeric(target43.minuteQueries)
                target43.minuteQueries = jsAdd(previous45, 1)
            }
            if (truth(jsOr(jsOr(r.sourceSequence) { (!truth(global_Array.isArray(r.items))) }) { r.items.some((fun(x: dynamic): dynamic {
                return jsOr(jsOr((!truth(global_Number.isInteger(x.sets)))) { jsLess(x.sets, 1) }) { (!truth(global_Engine.byId(x.id))) }
            })) })) {
                run {
                    val target46: dynamic = stats
                    val previous48: dynamic = numeric(target46.minuteFallbacks)
                    target46.minuteFallbacks = jsAdd(previous48, 1)
                }
                return legacyMinutes.apply(receiver41, callArgs42)
            }
            if (truth((!truth(r.items.length)))) {
                return 0
            }
            var seconds: dynamic = 0
            var sets: dynamic = 0
            var rounds: dynamic = 0
            if (true) {
                val iterator49: dynamic = iteratorOf(r.items)
                while (true) {
                    val next50: dynamic = iterator49.next()
                    if (truth(next50.done)) break
                    var x: dynamic = absent
                    x = next50.value
                    run {
                        sets = jsAdd(sets, x.sets)
                    }
                    rounds = global_Math.max(rounds, x.sets)
                    run {
                        seconds = jsAdd(seconds, jsMultiply(x.sets, (if (truth((global_Engine.unitOf(x) === "sec"))) x.target else global_Math.ceil(global_OwnRoutines18.workSeconds(x)))))
                    }
                }
            }
            run {
                seconds = jsAdd(seconds, jsMultiply(global_Math.max(0, jsSubtract((if (truth((global_BlockSession22.mode(p) === "rounds"))) rounds else sets), 1)), global_TrainingProgression19.rest(jsOr(p) { js("({})") })))
            }
            val warm: dynamic = global_Warm24.select(jsObject { obj51 ->
                obj51["title"] = r.name
                obj51["items"] = r.items
                obj51["sourceSequence"] = r.sourceSequence
            }, jsOr(p) { js("({})") })
            run {
                seconds = jsAdd(seconds, (if (truth(jsAnd(warm) { (!truth(warm.blocked)) })) jsAdd(global_Warm24.step(warm.warm, "preparation", r.items.length).seconds, global_Warm24.step(warm.cool, "cooldown", r.items.length).seconds) else (if (truth(onPresent(warm) { value52 -> value52.blocked })) 60 else 120)))
            }
            return global_Math.ceil(jsDivide(seconds, 60))
        })
        doc.removeEventListener("scroll", global_ambientScroll3817, true)
        if (truth(global_scrollFrame3817)) {
            global_cancelAnimationFrame(global_scrollFrame3817)
        }
        global_scrollFrame3817 = run {
            val assigned53: dynamic = 0
            global_pendingScroll3817 = assigned53
            assigned53
        }
        root.HunterOptimize48 = global_Object.freeze(jsObject { obj56 ->
            obj56["version"] = "38.48"
            obj56["compositedAmbient"] = true
            obj56["legacyMinutes"] = legacyMinutes
            obj56["snapshot"] = (fun(): dynamic {
                return jsObject { obj57 ->
                    copyFields(obj57, stats)
                    obj57["observedDecorations"] = decorations.size
                    obj57["svg"] = onPresent(root.SvgRenderer373) { value58 -> run { val owner60: dynamic = value58; onPresent(owner60.snapshot48) { method59 -> invokeWith(method59, owner60, js("[]")) } } }
                    obj57["startup"] = global_Runtime20.startup37
                }
            })
        })
        global_Runtime20.initializing = false
        global_state.appVersion = 38.76
        global_applyTheme()
        global_render()
        global_save()
        global_Boot37.commit()
        if (truth((doc.readyState !== "loading"))) {
            global_Boot37.complete()
        }
        return absent
    }))(global_window, global_document)
}
