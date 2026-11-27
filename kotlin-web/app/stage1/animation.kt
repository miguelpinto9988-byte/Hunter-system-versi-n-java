@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installanimation

import hunter.browser.*

@JsName("Math") private external var global_Math: dynamic
@JsName("MotionPlayback") private external var global_MotionPlayback: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("performance") private external var global_performance: dynamic
@JsName("IgrisArt") private external var global_IgrisArt: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("cancelAnimationFrame") private external var global_cancelAnimationFrame: dynamic
@JsName("clearTimeout") private external var global_clearTimeout: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("Promise") private external var global_Promise: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installAnimation() {
    dynamicValue((fun(global: dynamic): dynamic {
        var IgrisCharacter: dynamic = absent
        IgrisCharacter = fun(host: dynamic, arg5: dynamic, arg6: dynamic): dynamic {
            val receiver3: dynamic = js("this")
            var type: dynamic = absent
            type = definedOr(arg5) { "knight" }
            var options: dynamic = absent
            options = definedOr(arg6) { js("({})") }
            receiver3.tick = (fun(now: dynamic): dynamic {
                receiver3.frame = 0
                if (truth(jsOr(receiver3.paused) { (receiver3.state === "hidden") })) {
                    return absent
                }
                if (truth(receiver3.last)) {
                    run {
                        val target11: dynamic = receiver3
                        target11.elapsed = jsAdd(target11.elapsed, jsMultiply(global_Math.max(0, jsSubtract(now, receiver3.last)), (if (truth(receiver3.reduced)) 1 else global_MotionPlayback.rate)))
                    }
                }
                receiver3.last = now
                if (truth(jsAnd(dynamicValue(jsonValue("[\"walk\",\"flip\",\"celebrate\",\"bow\",\"wave\",\"think\",\"nod\",\"look\",\"stretch\",\"point\",\"listen\"]")).includes(receiver3.state)) { jsAtLeast(receiver3.elapsed, 2200) })) {
                    receiver3.state = "idle"
                    receiver3.elapsed = 0
                }
                if (truth(jsAnd((receiver3.state === "entering")) { jsAtLeast(receiver3.elapsed, (if (truth(receiver3.reduced)) 100 else 980)) })) {
                    receiver3.state = "idle"
                    receiver3.elapsed = 0
                }
                if (truth(jsAnd((receiver3.state === "exiting")) { jsAtLeast(receiver3.elapsed, (if (truth(receiver3.reduced)) 100 else 760)) })) {
                    receiver3.state = "hidden"
                    receiver3.sample("hidden", 0)
                    run { val owner26: dynamic = receiver3; onPresent(owner26.finish) { method25 -> invokeWith(method25, owner26, arrayOf<dynamic>(true)) } }
                    receiver3.finish = dynamicValue(null)
                    return absent
                }
                if (truth(jsAnd(jsAnd((!truth(receiver3.reduced))) { receiver3.renderLast }) { jsLess(jsSubtract(now, receiver3.renderLast), jsOr(onPresent(global.HunterPerf380) { value29 -> value29.actorInterval(receiver3.state) }) { 32 }) })) {
                    receiver3.frame = global_requestAnimationFrame(receiver3.tick)
                    return absent
                }
                receiver3.renderLast = now
                val drawStart380: dynamic = global_performance.now()
                receiver3.sample(receiver3.state, receiver3.elapsed)
                onPresent(global.HunterPerf380) { value34 -> value34.sampleDrawCost(jsSubtract(global_performance.now(), drawStart380)) }
                receiver3.frame = global_requestAnimationFrame(receiver3.tick)
                return absent
            })
            if (truth((!truth(global_IgrisArt.labels[type])))) {
                throw global_Error(jsAdd("Personaje desconocido: ", type))
            }
            receiver3.host = host
            receiver3.type = type
            host.dataset.renderer = "2d"
            host.dataset.character = type
            receiver3.options = options
            host.innerHTML = global_IgrisArt.svg(type)
            receiver3.parts = js("({})")
            host.querySelectorAll("[data-part]").forEach((fun(p: dynamic): dynamic {
                return run {
                    val target51: dynamic = receiver3.parts
                    val key52: dynamic = p.dataset.part
                    val assigned53: dynamic = p
                    target51[key52] = assigned53
                    assigned53
                }
            }))
            receiver3.state = "hidden"
            receiver3.elapsed = 0
            receiver3.frame = 0
            receiver3.last = 0
            receiver3.renderLast = 0
            receiver3.paused = false
            receiver3.automatic = nullableOr(options.automatic) { (host.id === "animatedCompanion") }
            receiver3.autoTimer = 0
            receiver3.previousAction = ""
            receiver3.reduced = nullableOr(options.reducedMotion) { global_matchMedia("(prefers-reduced-motion: reduce)").matches }
            receiver3.visibility = (fun(): dynamic {
                return (if (truth(global_document.hidden)) receiver3.pause() else receiver3.resume())
            })
            global_document.addEventListener("visibilitychange", receiver3.visibility)
            receiver3.sample("hidden", 0)
            return absent
        }
        IgrisCharacter.prototype["transform"] = fun(part: dynamic, `value`: dynamic): dynamic {
            val receiver76: dynamic = js("this")
            val node: dynamic = receiver76.parts[part]
            if (truth((!truth(node)))) {
                return absent
            }
            onPresent(receiver76["_usedTransforms"]) { value78 -> value78.add(part) }
            if (truth((node.getAttribute("transform") !== `value`))) {
                node.setAttribute("transform", `value`)
            }
            return absent
        }
        IgrisCharacter.prototype["sample"] = fun(state: dynamic, ms: dynamic): dynamic {
            val receiver79: dynamic = js("this")
            val t: dynamic = jsDivide(ms, 1000)
            val reduced: dynamic = receiver79.reduced
            receiver79["_usedTransforms"] = jsConstruct(global_Set, js("[]"))
            val tempo: dynamic = jsOr(jsonValue("{\"knight\":1,\"warrior\":1.16,\"bear\":0.72,\"insect\":1.42,\"scientist\":0.86}")[receiver79.type]) { 1 }
            val phase: dynamic = jsMultiply(t, tempo)
            var opacity: dynamic = (if (truth((state === "hidden"))) 0 else 1)
            var x: dynamic = 0
            var y: dynamic = 0
            var s: dynamic = 1
            var angle: dynamic = 0
            if (truth((state === "entering"))) {
                val p: dynamic = global_Math.min(1, jsDivide(ms, (if (truth(reduced)) 100 else 980)))
                val ease: dynamic = jsSubtract(1, global_Math.pow(jsSubtract(1, p), 3))
                val overshoot: dynamic = jsMultiply(global_Math.sin(jsMultiply(p, global_Math.PI)), 0.055)
                opacity = global_Math.min(1, jsMultiply(p, 4))
                s = (if (truth(reduced)) 1 else jsAdd(jsAdd(0.28, jsMultiply(0.72, ease)), overshoot))
                y = (if (truth(reduced)) 0 else jsMultiply(jsSubtract(1, ease), 70))
                x = (if (truth(reduced)) 0 else jsMultiply((if (truth((receiver79.type === "insect"))) (-numeric(90)) else (if (truth((receiver79.type === "warrior"))) 60 else 34)), jsSubtract(1, ease)))
                angle = (if (truth(reduced)) 0 else jsMultiply((if (truth((receiver79.type === "bear"))) (-numeric(6)) else (-numeric(14))), jsSubtract(1, ease)))
            }
            if (truth((state === "exiting"))) {
                val p: dynamic = global_Math.min(1, jsDivide(ms, (if (truth(reduced)) 100 else 760)))
                val ease: dynamic = jsMultiply(p, p)
                opacity = jsSubtract(1, ease)
                s = (if (truth(reduced)) 1 else jsSubtract(1, jsMultiply(0.62, ease)))
                y = (if (truth(reduced)) 0 else jsMultiply(65, ease))
                x = (if (truth(reduced)) 0 else jsMultiply((if (truth((receiver79.type === "insect"))) 70 else (-numeric(24))), ease))
                angle = (if (truth(reduced)) 0 else jsMultiply(16, global_Math.sin(jsMultiply(p, global_Math.PI))))
            }
            if (truth(jsAnd((state === "idle")) { (!truth(reduced)) })) {
                val amp: dynamic = jsOr(jsonValue("{\"knight\":5,\"warrior\":7,\"bear\":2.5,\"insect\":8,\"scientist\":3.5}")[receiver79.type]) { 5 }
                x = jsMultiply(jsMultiply(global_Math.sin(jsMultiply(phase, 1.2)), amp), 0.7)
                y = jsMultiply(jsMultiply(global_Math.sin(jsMultiply(phase, 2.15)), amp), 0.45)
                if (truth((receiver79.type === "insect"))) {
                    run {
                        y = jsSubtract(y, jsAdd(5, jsMultiply(global_Math.sin(jsMultiply(phase, 3.2)), 3)))
                    }
                }
                if (truth((receiver79.type === "bear"))) {
                    angle = jsMultiply(global_Math.sin(jsMultiply(phase, 0.8)), 0.7)
                }
                if (truth((receiver79.type === "scientist"))) {
                    angle = jsMultiply(global_Math.sin(jsMultiply(phase, 0.9)), 1.1)
                }
            }
            receiver79.parts.root.setAttribute("opacity", opacity)
            receiver79.parts.aura.setAttribute("opacity", jsMultiply(opacity, jsAdd(0.68, jsMultiply(0.2, global_Math.sin(jsMultiply(phase, 2.1))))))
            receiver79.parts.shadow.setAttribute("opacity", jsMultiply(opacity, (if (truth((receiver79.type === "insect"))) 0.42 else 0.58)))
            receiver79.parts.particles.setAttribute("opacity", (if (truth(reduced)) 0 else jsMultiply(opacity, 0.65)))
            if (truth((!truth(reduced)))) {
                if (truth((state === "walk"))) {
                    run {
                        x = jsAdd(x, jsMultiply(global_Math.sin(jsMultiply(phase, 3)), 35))
                    }
                }
                if (truth((state === "flip"))) {
                    run {
                        angle = jsAdd(angle, jsMultiply(360, global_Math.min(1, jsDivide(t, 1.4))))
                    }
                    run {
                        y = jsSubtract(y, jsMultiply(65, global_Math.sin(jsMultiply(global_Math.min(1, jsDivide(t, 1.4)), global_Math.PI))))
                    }
                }
                if (truth((state === "celebrate"))) {
                    run {
                        y = jsSubtract(y, jsMultiply(global_Math.abs(global_Math.sin(jsMultiply(phase, 5))), 25))
                    }
                }
                if (truth((state === "bow"))) {
                    run {
                        angle = jsAdd(angle, jsMultiply(18, global_Math.sin(jsMultiply(global_Math.min(1, jsDivide(t, 2.2)), global_Math.PI))))
                    }
                }
                if (truth((state === "think"))) {
                    run {
                        angle = jsAdd(angle, jsMultiply(4, global_Math.sin(jsMultiply(phase, 3))))
                    }
                }
                if (truth((state === "look"))) {
                    run {
                        angle = jsAdd(angle, jsMultiply(5, global_Math.sin(jsMultiply(jsMultiply(global_Math.min(1, jsDivide(t, 2.2)), global_Math.PI), 2))))
                    }
                }
                if (truth((state === "stretch"))) {
                    run {
                        s = jsAdd(s, jsMultiply(0.025, global_Math.sin(jsMultiply(global_Math.min(1, jsDivide(t, 2.2)), global_Math.PI))))
                    }
                }
            }
            val pivot: dynamic = (if (truth((state === "flip"))) 270 else 470)
            receiver79.transform("root", ("translate(" + textOf(x) + " " + textOf(y) + ") translate(200 " + textOf(pivot) + ") rotate(" + textOf(angle) + ") scale(" + textOf(s) + ") translate(-200 -" + textOf(pivot) + ")"))
            if (truth((!truth(reduced)))) {
                val wave: dynamic = global_Math.sin(jsMultiply(phase, 2))
                val flutter: dynamic = global_Math.sin(jsMultiply(phase, 4))
                val breath: dynamic = global_Math.sin(jsMultiply(phase, 1.7))
                val bodyX: dynamic = (if (truth((receiver79.type === "bear"))) 0.004 else 0.008)
                val bodyY: dynamic = (if (truth((receiver79.type === "warrior"))) 0.018 else (if (truth((receiver79.type === "bear"))) 0.007 else 0.012))
                receiver79.transform("body", ("translate(200 420) scale(" + textOf(jsAdd(1, jsMultiply(breath, bodyX))) + " " + textOf(jsAdd(1, jsMultiply(breath, bodyY))) + ") translate(-200 -420)"))
                val nod: dynamic = (if (truth(jsAnd((state === "entering")) { (receiver79.type === "knight") })) jsMultiply(12, global_Math.sin(jsMultiply(global_Math.min(1, jsDivide(ms, 980)), global_Math.PI))) else 0)
                receiver79.transform("head", ("rotate(" + textOf(jsAdd(nod, jsMultiply(wave, (if (truth((receiver79.type === "scientist"))) 1.5 else 2.5)))) + " 200 285)"))
                if (truth((state === "nod"))) {
                    receiver79.transform("head", ("rotate(" + textOf(jsMultiply(12, global_Math.sin(jsMultiply(phase, 5)))) + " 200 285)"))
                }
                receiver79.transform("cape", ("rotate(" + textOf(jsMultiply(wave, (if (truth((receiver79.type === "knight"))) 4 else 3))) + " 200 310)"))
                receiver79.transform("plume", ("rotate(" + textOf(jsMultiply(flutter, 6)) + " 215 105)"))
                var left: dynamic = jsMultiply(wave, 4)
                var right: dynamic = jsMultiply((-numeric(wave)), 4)
                if (truth((receiver79.type === "bear"))) {
                    left = jsMultiply(wave, 2)
                    right = jsMultiply((-numeric(wave)), 2)
                }
                if (truth(jsAnd((receiver79.type === "scientist")) { (state === "idle") })) {
                    right = jsAdd((-numeric(8)), jsMultiply(global_Math.sin(jsMultiply(phase, 1.3)), 5))
                }
                if (truth(jsAnd(jsAnd((state === "idle")) { (!truth(global_document.hidden)) }) { (onPresent(global.HunterEvolution385) { value83 -> value83.budget } !== "eco") })) {
                    val phasePose: dynamic = global_Math.max(0, global_Math.sin(jsMultiply(phase, 0.22)))
                    val mix: dynamic = jsMultiply(jsMultiply(jsMultiply(phasePose, phasePose), phasePose), phasePose)
                    if (truth((receiver79.type === "knight"))) {
                        run {
                            left = jsSubtract(left, jsMultiply(mix, 7))
                        }
                        run {
                            right = jsAdd(right, jsMultiply(mix, 9))
                        }
                    } else {
                        if (truth((receiver79.type === "warrior"))) {
                            run {
                                left = jsSubtract(left, jsMultiply(mix, 12))
                            }
                            run {
                                right = jsSubtract(right, jsMultiply(mix, 6))
                            }
                        } else {
                            if (truth((receiver79.type === "bear"))) {
                                run {
                                    left = jsAdd(left, jsMultiply(mix, 5))
                                }
                                run {
                                    right = jsSubtract(right, jsMultiply(mix, 5))
                                }
                            } else {
                                if (truth((receiver79.type === "insect"))) {
                                    run {
                                        left = jsAdd(left, jsMultiply(mix, 9))
                                    }
                                    run {
                                        right = jsSubtract(right, jsMultiply(mix, 9))
                                    }
                                } else {
                                    if (truth((receiver79.type === "scientist"))) {
                                        run {
                                            left = jsSubtract(left, jsMultiply(mix, 4))
                                        }
                                        run {
                                            right = jsSubtract(right, jsMultiply(mix, 11))
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (truth(jsOr(jsOr((state === "entering")) { (state === "wave") }) { (state === "celebrate") })) {
                    right = jsAdd((-numeric(25)), jsMultiply(global_Math.sin(jsMultiply(phase, 15)), 20))
                }
                if (truth((state === "exiting"))) {
                    right = jsAdd((-numeric(35)), jsMultiply(global_Math.sin(jsMultiply(phase, 18)), 20))
                }
                if (truth((state === "point"))) {
                    right = jsAdd((-numeric(58)), jsMultiply(global_Math.sin(jsMultiply(phase, 3)), 5))
                }
                if (truth((state === "stretch"))) {
                    right = jsMultiply((-numeric(65)), global_Math.sin(jsMultiply(global_Math.min(1, jsDivide(t, 2.2)), global_Math.PI)))
                    left = (-numeric(right))
                }
                if (truth((state === "listen"))) {
                    right = jsAdd((-numeric(18)), jsMultiply(global_Math.sin(jsMultiply(phase, 2)), 3))
                    receiver79.transform("head", ("rotate(-7 200 285)"))
                }
                receiver79.transform("armLeft", ("rotate(" + textOf(left) + " 160 315)"))
                receiver79.transform("armRight", ("rotate(" + textOf(right) + " 240 315)"))
                val wingSpeed: dynamic = (if (truth((receiver79.type === "insect"))) 18 else 15)
                val wingAmp: dynamic = (if (truth((receiver79.type === "insect"))) 18 else 13)
                receiver79.transform("wingLeft", ("rotate(" + textOf(jsMultiply(global_Math.sin(jsMultiply(phase, wingSpeed)), wingAmp)) + " 185 320)"))
                receiver79.transform("wingRight", ("rotate(" + textOf(jsMultiply((-numeric(global_Math.sin(jsMultiply(phase, wingSpeed)))), wingAmp)) + " 215 320)"))
                receiver79.transform("antennaLeft", ("rotate(" + textOf(jsMultiply(flutter, 5)) + " 150 155)"))
                receiver79.transform("antennaRight", ("rotate(" + textOf(jsMultiply((-numeric(flutter)), 5)) + " 250 155)"))
                receiver79.transform("flames", ("translate(200 160) scale(" + textOf(jsAdd(1, jsMultiply(flutter, (if (truth((receiver79.type === "warrior"))) 0.065 else 0.045)))) + " " + textOf(jsAdd(1, jsMultiply(wave, 0.04))) + ") translate(-200 -160)"))
                val blinkWindow: dynamic = (if (truth((receiver79.type === "bear"))) 4700 else (if (truth((receiver79.type === "scientist"))) 3600 else 4100))
                val blink: dynamic = (if (truth(jsAnd(jsMore(jsMod(ms, blinkWindow), jsSubtract(blinkWindow, 300))) { jsLess(jsMod(ms, blinkWindow), jsSubtract(blinkWindow, 120)) })) 0.12 else 1)
                receiver79.transform("eyes", ("translate(200 218) scale(1 " + textOf(blink) + ") translate(-200 -218)"))
                receiver79.transform("particles", ("translate(" + textOf(jsMultiply(global_Math.sin(jsMultiply(phase, 0.8)), 3)) + " " + textOf(jsMultiply((-numeric(8)), global_Math.sin(phase))) + ")"))
                val stride: dynamic = (if (truth((state === "walk"))) jsMultiply(global_Math.sin(jsMultiply(phase, 8)), 7) else 0)
                receiver79.transform("legLeft", ("rotate(" + textOf(stride) + " 177 400)"))
                receiver79.transform("legRight", ("rotate(" + textOf((-numeric(stride))) + " 218 400)"))
                if (truth((receiver79.type === "scientist"))) {
                    receiver79.transform("mouth", ("translate(200 264) scale(1 " + textOf((if (truth((state === "think"))) jsAdd(1, jsMultiply(global_Math.sin(jsMultiply(phase, 8)), 0.22)) else jsAdd(1, jsMultiply(breath, 0.035)))) + ") translate(-200 -264)"))
                }
                receiver79.transform("shadow", ("translate(200 480) scale(" + textOf((if (truth((state === "celebrate"))) jsSubtract(1, jsMultiply(global_Math.abs(global_Math.sin(jsMultiply(phase, 5))), 0.18)) else jsAdd(1, jsMultiply(breath, 0.025)))) + " 1) translate(-200 -480)"))
            }
            return absent
        }
        IgrisCharacter.prototype["perform"] = fun(kind: dynamic): dynamic {
            val receiver84: dynamic = js("this")
            if (truth(jsOr(jsOr((!truth(dynamicValue(jsonValue("[\"walk\",\"flip\",\"celebrate\",\"bow\",\"wave\",\"think\",\"nod\",\"look\",\"stretch\",\"point\",\"listen\"]")).includes(kind)))) { receiver84.destroyed }) { (receiver84.state === "exiting") })) {
                return absent
            }
            global_cancelAnimationFrame(receiver84.frame)
            receiver84.state = kind
            receiver84.elapsed = 0
            receiver84.last = 0
            receiver84.renderLast = 0
            if (truth((!truth(receiver84.paused)))) {
                receiver84.frame = global_requestAnimationFrame(receiver84.tick)
            }
            return absent
        }
        IgrisCharacter.prototype["clearAutomatic"] = fun(): dynamic {
            val receiver96: dynamic = js("this")
            global_clearTimeout(receiver96.autoTimer)
            receiver96.autoTimer = 0
            return absent
        }
        IgrisCharacter.prototype["automaticStep"] = fun(): dynamic {
            val receiver100: dynamic = js("this")
            receiver100.clearAutomatic()
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(receiver100.automatic))) { receiver100.reduced }) { receiver100.destroyed }) { receiver100.paused }) { global_document.hidden }) { (receiver100.state === "hidden") }) { (receiver100.state === "exiting") })) {
                return absent
            }
            if (truth((receiver100.state !== "idle"))) {
                receiver100.autoTimer = global_setTimeout((fun(): dynamic {
                    return receiver100.automaticStep()
                }), 500)
                return absent
            }
            val kinds: dynamic = dynamicValue(jsArray { array105 ->
                array105.push("walk")
                array105.push("wave")
                array105.push("bow")
                array105.push("think")
                array105.push("nod")
                array105.push("look")
                array105.push("stretch")
                array105.push("point")
                array105.push("listen")
                appendAll(array105, (if (truth((receiver100.type === "insect"))) jsonValue("[\"flip\"]") else jsonValue("[\"celebrate\"]")))
            }).filter((fun(k: dynamic): dynamic {
                return (k !== receiver100.previousAction)
            }))
            val action: dynamic = kinds[global_Math.floor(jsMultiply(global_Math.random(), kinds.length))]
            receiver100.previousAction = action
            receiver100.host.dataset.automaticAction = action
            receiver100.perform(action)
            receiver100.scheduleAutomatic()
            return absent
        }
        IgrisCharacter.prototype["scheduleAutomatic"] = fun(): dynamic {
            val receiver110: dynamic = js("this")
            receiver110.clearAutomatic()
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(receiver110.automatic))) { receiver110.reduced }) { receiver110.destroyed }) { receiver110.paused }) { global_document.hidden }) { (receiver110.state === "hidden") }) { (receiver110.state === "exiting") })) {
                return absent
            }
            receiver110.autoTimer = global_setTimeout((fun(): dynamic {
                return receiver110.automaticStep()
            }), jsAdd(6500, global_Math.floor(jsMultiply(global_Math.random(), 7500))))
            return absent
        }
        IgrisCharacter.prototype["open"] = fun(): dynamic {
            val receiver114: dynamic = js("this")
            if (truth(receiver114.destroyed)) {
                return absent
            }
            run { val owner117: dynamic = receiver114; onPresent(owner117.finish) { method116 -> invokeWith(method116, owner117, arrayOf<dynamic>(false)) } }
            receiver114.finish = dynamicValue(null)
            global_cancelAnimationFrame(receiver114.frame)
            receiver114.state = "entering"
            receiver114.elapsed = 0
            receiver114.last = 0
            receiver114.renderLast = 0
            receiver114.sample(receiver114.state, 0)
            if (truth((!truth(receiver114.paused)))) {
                receiver114.frame = global_requestAnimationFrame(receiver114.tick)
            }
            receiver114.scheduleAutomatic()
            return absent
        }
        IgrisCharacter.prototype["close"] = fun(): dynamic {
            val receiver130: dynamic = js("this")
            receiver130.clearAutomatic()
            if (truth(jsOr(receiver130.destroyed) { (receiver130.state === "hidden") })) {
                return global_Promise.resolve(true)
            }
            if (truth((receiver130.state === "exiting"))) {
                return receiver130.closing
            }
            if (truth(global_document.hidden)) {
                receiver130.state = "hidden"
                receiver130.sample("hidden", 0)
                return global_Promise.resolve(true)
            }
            if (truth(receiver130.paused)) {
                receiver130.resume()
            }
            receiver130.clearAutomatic()
            receiver130.state = "exiting"
            receiver130.elapsed = 0
            receiver130.closing = jsConstruct(global_Promise, arrayOf<dynamic>((fun(resolve: dynamic): dynamic {
                return run {
                    val target140: dynamic = receiver130
                    val assigned142: dynamic = resolve
                    target140.finish = assigned142
                    assigned142
                }
            })))
            return receiver130.closing
        }
        IgrisCharacter.prototype["pause"] = fun(): dynamic {
            val receiver143: dynamic = js("this")
            receiver143.clearAutomatic()
            receiver143.paused = true
            global_cancelAnimationFrame(receiver143.frame)
            receiver143.frame = 0
            receiver143.last = 0
            return absent
        }
        IgrisCharacter.prototype["resume"] = fun(): dynamic {
            val receiver151: dynamic = js("this")
            if (truth(receiver151.destroyed)) {
                return absent
            }
            receiver151.paused = false
            receiver151.last = 0
            receiver151.renderLast = 0
            if (truth(jsAnd((!truth(receiver151.frame))) { (receiver151.state !== "hidden") })) {
                receiver151.frame = global_requestAnimationFrame(receiver151.tick)
            }
            receiver151.scheduleAutomatic()
            return absent
        }
        IgrisCharacter.prototype["destroy"] = fun(): dynamic {
            val receiver161: dynamic = js("this")
            receiver161.pause()
            receiver161.destroyed = true
            run { val owner166: dynamic = receiver161; onPresent(owner166.finish) { method165 -> invokeWith(method165, owner166, arrayOf<dynamic>(false)) } }
            receiver161.finish = dynamicValue(null)
            global_document.removeEventListener("visibilitychange", receiver161.visibility)
            receiver161.host.innerHTML = ""
            return absent
        }
        val nativeSample380: dynamic = IgrisCharacter.prototype.sample
        IgrisCharacter.prototype.sample = (fun(arg175: dynamic): dynamic {
            val receiver173: dynamic = js("this")
            val callArgs174: dynamic = js("arguments")
            var args: dynamic = absent
            args = argsAfter(callArgs174, 0)
            val previous: dynamic = receiver173["_usedTransforms"]
            nativeSample380.apply(receiver173, args)
            if (truth(previous)) {
                if (true) {
                    val iterator176: dynamic = iteratorOf(previous)
                    while (true) {
                        val next177: dynamic = iterator176.next()
                        if (truth(next177.done)) break
                        var name: dynamic = absent
                        name = next177.value
                        if (truth((!truth(receiver173["_usedTransforms"].has(name))))) {
                            val node: dynamic = receiver173.parts[name]
                            if (truth(onPresent(node) { value178 -> value178.hasAttribute("transform") })) {
                                node.removeAttribute("transform")
                            }
                        }
                    }
                }
            }
            return absent
        })
        global.IgrisCharacter = IgrisCharacter
        return absent
    }))(global_window)
}
