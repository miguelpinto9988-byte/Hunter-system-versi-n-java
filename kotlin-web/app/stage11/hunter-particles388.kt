@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterparticles388

import hunter.browser.*

@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("parseInt") private external var global_parseInt: dynamic
@JsName("Infinity") private external var global_Infinity: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterParticles388() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var positionCache: dynamic = absent
        var types: dynamic = absent
        var ranks: dynamic = absent
        var basePositions: dynamic = absent
        var updates: dynamic = absent
        var skipped: dynamic = absent
        var actors: dynamic = absent
        var clamp: dynamic = absent
        var num: dynamic = absent
        var reducedMotion: dynamic = absent
        var getBudget: dynamic = absent
        var mixColor: dynamic = absent
        var update: dynamic = absent
        update = fun(actor: dynamic, state: dynamic, milliseconds: dynamic): dynamic {
            val part: dynamic = actor.parts.particles
            var cache: dynamic = positionCache["get"](actor)
            if (truth((!truth(cache)))) {
                val children: dynamic = dynamicValue(jsArray { array16 ->
                    appendAll(array16, jsOr(part.children) { js("[]") })
                }).filter((fun(node: dynamic): dynamic {
                    return (onPresent(node.tagName) { value17 -> value17.toLowerCase() } === "circle")
                })).slice(0, 4)
                if (truth((!truth(children.length)))) {
                    return absent
                }
                cache = jsObject { obj18 ->
                    obj18["children"] = children
                    obj18["last"] = (-numeric(global_Infinity))
                    obj18["lastColor"] = ""
                    obj18["lastState"] = ""
                    obj18["done"] = 0
                }
                positionCache["set"](actor, cache)
                run {
                    val previous19: dynamic = numeric(actors)
                    actors = jsAdd(previous19, 1)
                }
            }
            if (truth(jsOr(jsOr(jsOr(jsOr(doc.hidden) { reducedMotion() }) { actor.reduced }) { actor.paused }) { (actor.state === "hidden") })) {
                run {
                    val previous20: dynamic = numeric(skipped)
                    skipped = jsAdd(previous20, 1)
                }
                return absent
            }
            val mode: dynamic = getBudget()
            val minInterval: dynamic = (if (truth((mode === "eco"))) 110 else (if (truth((mode === "alto"))) 34 else 55))
            val t: dynamic = (if (truth(global_Number.isFinite(global_Number(milliseconds)))) global_Math.max(0, global_Number(milliseconds)) else 0)
            if (truth(jsAnd(jsAnd((state === cache.lastState)) { jsAtLeast(t, cache.last) }) { jsLess(jsSubtract(t, cache.last), minInterval) })) {
                run {
                    val previous21: dynamic = numeric(skipped)
                    skipped = jsAdd(previous21, 1)
                }
                return absent
            }
            cache.last = t
            cache.lastState = state
            val rank: dynamic = onPresent(doc.documentElement) { value26 -> onPresent(value26.dataset) { value27 -> value27.hunterRank385 } }
            val base: dynamic = jsOr(types[actor.type]) { "#a5ecff" }
            val color: dynamic = (if (truth(ranks[rank])) mixColor(base, ranks[rank], 0.2) else base)
            if (truth((part.getAttribute("fill") !== color))) {
                part.setAttribute("fill", color)
                cache.lastColor = color
            }
            val phase: dynamic = jsDivide(t, 1000)
            val gestures: dynamic = jsonValue("[\"celebrate\",\"bow\",\"wave\",\"point\",\"stretch\"]")
            val event: dynamic = jsOr(jsOr(gestures.includes(state)) { (state === "entering") }) { (state === "exiting") }
            val eventLength: dynamic = (if (truth((state === "entering"))) 980 else (if (truth((state === "exiting"))) 760 else 2200))
            val p: dynamic = clamp(jsDivide(t, eventLength), 0, 1)
            val pulse: dynamic = (if (truth(event)) global_Math.sin(jsMultiply(global_Math.PI, p)) else 0)
            val intensity: dynamic = (if (truth((mode === "eco"))) 0.32 else (if (truth((mode === "alto"))) 1 else 0.72))
            cache.children.forEach((fun(circle: dynamic, index: dynamic): dynamic {
                val origin: dynamic = basePositions[index]
                val angle: dynamic = jsAdd(jsMultiply(phase, jsAdd(0.4, jsMultiply(index, 0.07))), jsDivide(jsMultiply(index, global_Math.PI), 2))
                val radius: dynamic = jsMultiply(jsAdd(3, jsMultiply(global_Math.sin(jsAdd(jsMultiply(phase, 0.7), index)), 2)), intensity)
                val attraction: dynamic = jsMultiply((if (truth((state === "think"))) 0.12 else 0.045), intensity)
                val goalX: dynamic = 200
                val goalY: dynamic = 285
                val dx: dynamic = jsAdd(jsAdd(jsMultiply(global_Math.cos(angle), radius), jsMultiply(jsSubtract(goalX, origin[0]), attraction)), jsMultiply(jsMultiply(jsMultiply(global_Math.sin(jsAdd(jsMultiply(phase, 2), index)), pulse), 8), intensity))
                val dy: dynamic = jsSubtract(jsAdd(jsSubtract(jsMultiply(global_Math.sin(angle), radius), jsMultiply(jsMultiply(global_Math.sin(jsAdd(jsMultiply(phase, 0.9), index)), 4), intensity)), jsMultiply(jsSubtract(goalY, origin[1]), attraction)), jsMultiply(jsMultiply(pulse, 8), intensity))
                val x: dynamic = clamp(jsAdd(origin[0], dx), 55, 340)
                val y: dynamic = clamp(jsAdd(origin[1], dy), 205, 392)
                val f: dynamic = (if (truth(jsAnd(event) { (mode !== "eco") })) jsAdd(1, jsMultiply(pulse, 0.45)) else 1)
                val scaleX: dynamic = (if (truth(jsAnd(event) { (mode !== "eco") })) jsAdd(1, jsMultiply(0.36, pulse)) else 1)
                val transform: dynamic = ("translate(" + textOf(num(x)) + " " + textOf(num(y)) + ") scale(" + textOf(num(scaleX)) + " " + textOf(num(f)) + ") translate(" + textOf((-numeric(origin[0]))) + " " + textOf((-numeric(origin[1]))) + ")")
                if (truth((circle.getAttribute("transform") !== transform))) {
                    circle.setAttribute("transform", transform)
                }
                return absent
            }))
            run {
                val previous30: dynamic = numeric(updates)
                updates = jsAdd(previous30, 1)
            }
            return absent
        }
        if (truth(root.HunterParticles388)) {
            return absent
        }
        val Actor: dynamic = root.IgrisCharacter
        if (truth((!truth(onPresent(Actor) { value3 -> onPresent(value3.prototype) { value4 -> value4.sample } })))) {
            return absent
        }
        val previousSample: dynamic = Actor.prototype.sample
        positionCache = jsConstruct(global_WeakMap, js("[]"))
        types = jsonValue("{\"knight\":\"#ffa09b\",\"warrior\":\"#95e7ff\",\"bear\":\"#d5eaff\",\"insect\":\"#91ffe8\",\"scientist\":\"#f0e7ff\"}")
        ranks = jsonValue("{\"E\":\"#84919e\",\"D\":\"#668ab0\",\"C\":\"#52a5bb\",\"B\":\"#9a8ce0\",\"A\":\"#d4b27b\",\"S\":\"#c9e4ff\"}")
        basePositions = jsonValue("[[86,280],[309,246],[75,350],[327,344]]")
        updates = 0
        skipped = 0
        actors = 0
        clamp = (fun(v: dynamic, min: dynamic, max: dynamic): dynamic {
            return global_Math.max(min, global_Math.min(max, v))
        })
        num = (fun(v: dynamic): dynamic {
            return global_Number(v.toFixed(2))
        })
        reducedMotion = (fun(): dynamic {
            return (!truth((!truth(run { val owner6: dynamic = root; onPresent(owner6.matchMedia) { method5 -> invokeWith(method5, owner6, arrayOf<dynamic>("(prefers-reduced-motion: reduce)")).matches } }))))
        })
        getBudget = (fun(): dynamic {
            return jsOr(onPresent(root.HunterEvolution385) { value8 -> value8.budget }) { "medio" }
        })
        val available: dynamic = (fun(actor: dynamic): dynamic {
            return jsAnd(jsAnd((!truth((!truth(actor))))) { (!truth(actor.destroyed)) }) { (!truth((!truth(onPresent(actor.parts) { value9 -> value9.particles })))) }
        })
        val rgb: dynamic = (fun(h: dynamic): dynamic {
            return dynamicValue(jsonValue("[1,3,5]")).map((fun(i: dynamic): dynamic {
                return global_parseInt(h.slice(i, jsAdd(i, 2)), 16)
            }))
        })
        mixColor = (fun(a: dynamic, b: dynamic, t: dynamic): dynamic {
            val x: dynamic = rgb(a)
            val y: dynamic = rgb(b)
            return jsAdd("#", x.map((fun(v: dynamic, i: dynamic): dynamic {
                return global_Math.round(jsAdd(jsMultiply(v, jsSubtract(1, t)), jsMultiply(y[i], t)))["toString"](16).padStart(2, "0")
            })).join(""))
        })
        Actor.prototype.sample = (fun(state: dynamic, ms: dynamic): dynamic {
            val receiver33: dynamic = js("this")
            val callArgs34: dynamic = js("arguments")
            val result: dynamic = previousSample.apply(receiver33, callArgs34)
            if (truth(available(receiver33))) {
                update(receiver33, state, ms)
            }
            return result
        })
        root.HunterParticles388 = global_Object.freeze(jsObject { obj37 ->
            obj37["version"] = "38.8"
            obj37["snapshot"] = (fun(): dynamic {
                return jsObject { obj38 ->
                    obj38["updatedFrames"] = updates
                    obj38["skippedFrames"] = skipped
                    obj38["trackedActors"] = actors
                    obj38["description"] = "Valores internos; no son mediciones de FPS, memoria ni batería."
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
