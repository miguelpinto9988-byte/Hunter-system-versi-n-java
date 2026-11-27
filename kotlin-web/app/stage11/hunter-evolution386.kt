@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterevolution386

import hunter.browser.*

@JsName("Object") private external var global_Object: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("WeakSet") private external var global_WeakSet: dynamic
@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterEvolution386() {
    dynamicValue((fun(root: dynamic, document: dynamic): dynamic {
        var tracked: dynamic = absent
        var suspended: dynamic = absent
        var observed: dynamic = absent
        var observers: dynamic = absent
        var suspendedCount: dynamic = absent
        var resumedCount: dynamic = absent
        var reactions: dynamic = absent
        var lastReaction: dynamic = absent
        var previousRank: dynamic = absent
        var reduceMotion: dynamic = absent
        var now: dynamic = absent
        var active: dynamic = absent
        var inViewport: dynamic = absent
        var attach: dynamic = absent
        var findVisibleActor: dynamic = absent
        var react: dynamic = absent
        var onMissionCompleted: dynamic = absent
        var RANKS: dynamic = absent
        var checkRank: dynamic = absent
        active = fun(actor: dynamic): dynamic {
            return (!truth((!truth(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(actor) { onPresent(actor.host) { value16 -> value16.isConnected } }) { (!truth(actor.destroyed)) }) { (!truth(actor.paused)) }) { (actor.state !== "hidden") }) { (actor.state !== "exiting") }))))
        }
        inViewport = fun(actor: dynamic): dynamic {
            if (truth((!truth(onPresent(actor.host) { value19 -> value19.getBoundingClientRect })))) {
                return true
            }
            val r: dynamic = actor.host.getBoundingClientRect()
            return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsMore(r.width, 0)) { jsMore(r.height, 0) }) { jsAtLeast(r.right, 0) }) { jsAtLeast(r.bottom, 0) }) { jsLess(r.left, root.innerWidth) }) { jsLess(r.top, root.innerHeight) }
        }
        attach = fun(actor: dynamic): dynamic {
            if (truth(jsOr((!truth(onPresent(actor) { value22 -> value22.host }))) { observed.has(actor) })) {
                return absent
            }
            observed.add(actor)
            tracked.add(actor)
            if (truth((!truth(hasKey("IntersectionObserver", root))))) {
                return absent
            }
            val io: dynamic = jsConstruct(root.IntersectionObserver, arrayOf<dynamic>((fun(entries: dynamic): dynamic {
                val entry: dynamic = entries[jsSubtract(entries.length, 1)]
                if (truth(jsOr((!truth(entry))) { actor.destroyed })) {
                    return absent
                }
                val visible: dynamic = jsAnd((!truth((!truth(entry.isIntersecting))))) { (!truth(document.hidden)) }
                if (truth(jsAnd(jsAnd((!truth(visible))) { (!truth(actor.paused)) }) { (actor.state !== "hidden") })) {
                    suspended.add(actor)
                    actor.pause()
                    run {
                        val previous23: dynamic = numeric(suspendedCount)
                        suspendedCount = jsAdd(previous23, 1)
                    }
                } else {
                    if (truth(jsAnd(visible) { suspended.has(actor) })) {
                        suspended.delete(actor)
                        if (truth(jsAnd(actor.paused) { (actor.state !== "hidden") })) {
                            actor.resume()
                            run {
                                val previous24: dynamic = numeric(resumedCount)
                                resumedCount = jsAdd(previous24, 1)
                            }
                        }
                    }
                }
                return absent
            }), jsonValue("{\"threshold\":0,\"rootMargin\":\"8px\"}")))
            observers["set"](actor, io)
            io.observe(actor.host)
            return absent
        }
        findVisibleActor = fun(): dynamic {
            if (true) {
                val iterator37: dynamic = iteratorOf(tracked)
                while (true) {
                    val next38: dynamic = iterator37.next()
                    if (truth(next38.done)) break
                    var actor: dynamic = absent
                    actor = next38.value
                    if (truth(jsAnd(active(actor)) { inViewport(actor) })) {
                        return actor
                    }
                }
            }
            return null
        }
        react = fun(gesture: dynamic): dynamic {
            if (truth(jsOr(reduceMotion()) { document.hidden })) {
                return false
            }
            if (truth((onPresent(root.HunterEvolution385) { value41 -> value41.mode } === "ahorro"))) {
                return false
            }
            if (truth(jsLess(jsSubtract(now(), lastReaction), 1800))) {
                return false
            }
            val actor: dynamic = findVisibleActor()
            if (truth(jsOr((!truth(actor))) { (jsTypeOf(actor.perform) !== "function") })) {
                return false
            }
            actor.perform(gesture)
            lastReaction = now()
            run {
                val previous42: dynamic = numeric(reactions)
                reactions = jsAdd(previous42, 1)
            }
            return true
        }
        onMissionCompleted = fun(): dynamic {
            react("celebrate")
            return absent
        }
        checkRank = fun(): dynamic {
            val rank: dynamic = onPresent(document.documentElement) { value47 -> onPresent(value47.dataset) { value48 -> value48.hunterRank385 } }
            if (truth((!truth(RANKS.includes(rank))))) {
                return absent
            }
            if (truth(jsAnd(previousRank) { jsMore(RANKS.indexOf(rank), RANKS.indexOf(previousRank)) })) {
                react("bow")
            }
            previousRank = rank
            return absent
        }
        if (truth(root.HunterEvolution386)) {
            return absent
        }
        val Character: dynamic = root.IgrisCharacter
        if (truth(jsOr((!truth(Character))) { (!truth(Character.prototype)) })) {
            return absent
        }
        val proto: dynamic = Character.prototype
        val original: dynamic = jsObject { obj3 ->
            obj3["open"] = proto["open"]
            obj3["destroy"] = proto.destroy
            obj3["sample"] = proto.sample
        }
        val particleColors: dynamic = global_Object.freeze(jsonValue("{\"knight\":\"#ffa09b\",\"warrior\":\"#95e7ff\",\"bear\":\"#d5eaff\",\"insect\":\"#91ffe8\",\"scientist\":\"#f0e7ff\"}"))
        proto.sample = (fun(state: dynamic, ms: dynamic): dynamic {
            val receiver6: dynamic = js("this")
            val result: dynamic = original.sample.call(receiver6, state, ms)
            val dots: dynamic = onPresent(receiver6.parts) { value8 -> value8.particles }
            if (truth((!truth(dots)))) {
                return result
            }
            val fill: dynamic = jsOr(particleColors[receiver6.type]) { "#a5ecff" }
            if (truth((dots.getAttribute("fill") !== fill))) {
                dots.setAttribute("fill", fill)
            }
            if (truth(jsOr(jsOr(receiver6.reduced) { document.hidden }) { (onPresent(root.HunterEvolution385) { value9 -> value9.budget } === "eco") })) {
                return result
            }
            val duration: dynamic = (if (truth((state === "entering"))) 980 else (if (truth((state === "exiting"))) 760 else 1600))
            val phase: dynamic = global_Math.max(0, global_Math.min(1, jsDivide(jsOr(global_Number(ms)) { 0 }, duration)))
            if (truth(jsOr(jsOr((state === "entering")) { (state === "exiting") }) { (state === "celebrate") })) {
                val shine: dynamic = global_Math.sin(jsMultiply(global_Math.PI, phase))
                val opacity: dynamic = global_Math.min(0.9, jsAdd(0.28, jsMultiply(0.52, shine)))
                dots.setAttribute("opacity", opacity.toFixed(3))
            }
            return result
        })
        tracked = jsConstruct(global_Set, js("[]"))
        suspended = jsConstruct(global_WeakSet, js("[]"))
        observed = jsConstruct(global_WeakSet, js("[]"))
        observers = jsConstruct(global_WeakMap, js("[]"))
        suspendedCount = 0
        resumedCount = 0
        reactions = 0
        lastReaction = 0
        previousRank = dynamicValue(null)
        var rankObserver: dynamic = dynamicValue(null)
        reduceMotion = (fun(): dynamic {
            return global_Boolean(run { val owner11: dynamic = root; onPresent(owner11.matchMedia) { method10 -> invokeWith(method10, owner11, arrayOf<dynamic>("(prefers-reduced-motion: reduce)")).matches } })
        })
        now = (fun(): dynamic {
            return (if (truth(onPresent(root.performance) { value13 -> value13.now })) root.performance.now() else global_Date.now())
        })
        proto["open"] = (fun(arg29: dynamic): dynamic {
            val receiver27: dynamic = js("this")
            val callArgs28: dynamic = js("arguments")
            var args: dynamic = absent
            args = argsAfter(callArgs28, 0)
            val result: dynamic = original["open"].apply(receiver27, args)
            attach(receiver27)
            return result
        })
        proto.destroy = (fun(arg34: dynamic): dynamic {
            val receiver32: dynamic = js("this")
            val callArgs33: dynamic = js("arguments")
            var args: dynamic = absent
            args = argsAfter(callArgs33, 0)
            val io: dynamic = observers["get"](receiver32)
            if (truth(io)) {
                io.disconnect()
            }
            tracked.delete(receiver32)
            suspended.delete(receiver32)
            return original.destroy.apply(receiver32, args)
        })
        root.addEventListener("hunter:mission-completed", onMissionCompleted)
        RANKS = jsonValue("[\"E\",\"D\",\"C\",\"B\",\"A\",\"S\"]")
        if (truth(jsAnd(hasKey("MutationObserver", root)) { document.documentElement })) {
            rankObserver = jsConstruct(root.MutationObserver, arrayOf<dynamic>(checkRank))
            rankObserver.observe(document.documentElement, jsonValue("{\"attributes\":true,\"attributeFilter\":[\"data-hunter-rank385\"]}"))
            checkRank()
        }
        root.HunterEvolution386 = global_Object.freeze(jsObject { obj51 ->
            obj51["version"] = "38.6"
            obj51["diagnostics"] = (fun(): dynamic {
                return global_Object.freeze(jsObject { obj52 ->
                    obj52["actors"] = tracked.size
                    obj52["suspendedCount"] = suspendedCount
                    obj52["resumedCount"] = resumedCount
                    obj52["reactions"] = reactions
                })
            })
            obj51["notifyMissionCompleted"] = onMissionCompleted
        })
        return absent
    }))(global_window, global_document)
}
