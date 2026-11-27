@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installruntimev373

import hunter.browser.*

@JsName("Runtime373") private external var global_Runtime373: dynamic
@JsName("DemoLoop373") private external var global_DemoLoop373: dynamic
@JsName("runtimeUI373") private external var global_runtimeUI373: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("performance") private external var global_performance: dynamic
@JsName("clearTimeout") private external var global_clearTimeout: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("staticActors14") private external var global_staticActors14: dynamic
@JsName("activeCompanion") private external var global_activeCompanion: dynamic
@JsName("homeCompanion") private external var global_homeCompanion: dynamic
@JsName("missionCompanion") private external var global_missionCompanion: dynamic
@JsName("innerHeight") private external var global_innerHeight: dynamic
@JsName("IntersectionObserver") private external var global_IntersectionObserver: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("Ambient29") private external var global_Ambient29: dynamic
@JsName("phraseTimer33") private external var global_phraseTimer33: dynamic
@JsName("schedulePhrase33") private external var global_schedulePhrase33: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("pauseGuide11") private external var global_pauseGuide11: dynamic
@JsName("timerEnd") private external var global_timerEnd: dynamic
@JsName("workEnd") private external var global_workEnd: dynamic
@JsName("TimerAlarm27") private external var global_TimerAlarm27: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("cancelAnimationFrame") private external var global_cancelAnimationFrame: dynamic
@JsName("innerWidth") private external var global_innerWidth: dynamic
@JsName("Stars34") private external var global_Stars34: dynamic
@JsName("devicePixelRatio") private external var global_devicePixelRatio: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Stars29") private external var global_Stars29: dynamic
@JsName("Infinity") private external var global_Infinity: dynamic
@JsName("vectorGeneration13") private external var global_vectorGeneration13: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("guideFrame11") private external var global_guideFrame11: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("cycleSeconds18") private external var global_cycleSeconds18: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("MotionPlayback") private external var global_MotionPlayback: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("vectorTick") private external var global_vectorTick: dynamic
@JsName("animateGuidedVector11") private external var global_animateGuidedVector11: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("backModal24") private external var global_backModal24: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Android") private external var global_Android: dynamic

internal fun installRuntimeV373() {
    global_Runtime373 = jsObject { obj1 ->
        obj1["nativeActive"] = true
        obj1["saver"] = false
        obj1["thermal"] = 0
        obj1["idle"] = false
        obj1["idleTimer"] = 0
        obj1["lastInput"] = 0
        obj1["actors"] = jsConstruct(global_Map, js("[]"))
        accessor(obj1, "hidden", "get", fun(): dynamic {
            val receiver2: dynamic = js("this")
            return jsOr((!truth(receiver2.nativeActive))) { global_document.hidden }
        })
        accessor(obj1, "quiet", "get", fun(): dynamic {
            val receiver4: dynamic = js("this")
            return jsOr(jsOr(jsOr(receiver4.hidden) { receiver4.saver }) { jsAtLeast(receiver4.thermal, 2) }) { receiver4.idle }
        })
        obj1["activity"] = fun(): dynamic {
            val receiver6: dynamic = js("this")
            if (truth(receiver6.hidden)) {
                return absent
            }
            val now: dynamic = global_performance.now()
            if (truth(jsAnd(jsAnd((!truth(receiver6.idle))) { receiver6.idleTimer }) { jsLess(jsSubtract(now, receiver6.lastInput), 500) })) {
                return absent
            }
            receiver6.lastInput = now
            if (truth(receiver6.idle)) {
                receiver6.idle = false
                receiver6.sync()
            }
            global_clearTimeout(receiver6.idleTimer)
            receiver6.idleTimer = global_setTimeout((fun(): dynamic {
                receiver6.idleTimer = 0
                receiver6.idle = true
                receiver6.sync()
                return absent
            }), 30000)
            return absent
        }
        obj1["prune"] = fun(): dynamic {
            val receiver18: dynamic = js("this")
            if (true) {
                val iterator20: dynamic = iteratorOf(global_staticActors14)
                while (true) {
                    val next21: dynamic = iterator20.next()
                    if (truth(next21.done)) break
                    var host: dynamic = absent
                    var actor: dynamic = absent
                    val unpacked22: dynamic = fromIterable(next21.value)
                    host = unpacked22[0]
                    actor = unpacked22[1]
                    if (truth((!truth(host.isConnected)))) {
                        actor.destroy()
                        global_staticActors14.delete(host)
                    }
                }
            }
            if (true) {
                val iterator23: dynamic = iteratorOf(receiver18.actors)
                while (true) {
                    val next24: dynamic = iterator23.next()
                    if (truth(next24.done)) break
                    var host: dynamic = absent
                    var entry: dynamic = absent
                    val unpacked25: dynamic = fromIterable(next24.value)
                    host = unpacked25[0]
                    entry = unpacked25[1]
                    if (truth(jsOr(jsOr((!truth(host.isConnected))) { entry.actor.destroyed }) { (entry.actor.alive === false) })) {
                        onPresent(entry.observer) { value26 -> value26.disconnect() }
                        if (truth((!truth(host.isConnected)))) {
                            entry.actor.destroy()
                        }
                        receiver18.actors.delete(host)
                    }
                }
            }
            return absent
        }
        obj1["watchActors"] = fun(): dynamic {
            val receiver27: dynamic = js("this")
            receiver27.prune()
            if (true) {
                val iterator29: dynamic = iteratorOf(jsArray { array31 ->
                    array31.push(global_activeCompanion)
                    array31.push(global_homeCompanion)
                    array31.push(global_missionCompanion)
                    appendAll(array31, global_staticActors14.values())
                })
                while (true) {
                    val next30: dynamic = iterator29.next()
                    if (truth(next30.done)) break
                    var actor: dynamic = absent
                    actor = next30.value
                    val host: dynamic = onPresent(actor) { value32 -> value32.host }
                    if (truth(jsOr((!truth(onPresent(host) { value33 -> value33.isConnected }))) { receiver27.actors.has(host) })) {
                        continue
                    }
                    val rect: dynamic = host.getBoundingClientRect()
                    val entry: dynamic = jsObject { obj34 ->
                        obj34["actor"] = actor
                        obj34["visible"] = jsAnd(jsMore(rect.bottom, 0)) { jsLess(rect.top, global_innerHeight) }
                        obj34["observer"] = dynamicValue(null)
                    }
                    if (truth((js("typeof IntersectionObserver") === "function"))) {
                        entry.observer = jsConstruct(global_IntersectionObserver, arrayOf<dynamic>((fun(rows: dynamic): dynamic {
                            entry.visible = rows.some((fun(x: dynamic): dynamic {
                                return x.isIntersecting
                            }))
                            receiver27.syncActor(host, entry)
                            return absent
                        })))
                        entry.observer.observe(host)
                    }
                    receiver27.actors["set"](host, entry)
                }
            }
            if (true) {
                val iterator39: dynamic = iteratorOf(receiver27.actors)
                while (true) {
                    val next40: dynamic = iterator39.next()
                    if (truth(next40.done)) break
                    var host: dynamic = absent
                    var entry: dynamic = absent
                    val unpacked41: dynamic = fromIterable(next40.value)
                    host = unpacked41[0]
                    entry = unpacked41[1]
                    receiver27.syncActor(host, entry)
                }
            }
            return absent
        }
        obj1["syncActor"] = fun(host: dynamic, entry: dynamic): dynamic {
            val receiver42: dynamic = js("this")
            if (truth((!truth(host.isConnected)))) {
                return absent
            }
            val covered: dynamic = jsAnd(global_Dollar("#modal")["open"]) { (!truth(global_Dollar("#modal").contains(host))) }
            val pause: dynamic = jsOr(jsOr(receiver42.quiet) { covered }) { (!truth(entry.visible)) }
            if (truth((entry.actor.paused !== pause))) {
                if (truth(pause)) {
                    entry.actor.pause()
                } else {
                    entry.actor.resume()
                }
            }
            host.classList.toggle("runtime-paused373", pause)
            return absent
        }
        obj1["sync"] = fun(): dynamic {
            val receiver44: dynamic = js("this")
            global_document.documentElement.classList.toggle("runtime-hidden373", receiver44.hidden)
            global_document.documentElement.classList.toggle("effects-idle373", receiver44.quiet)
            global_document.documentElement.classList.toggle("surface-open373", global_Dollar("#modal")["open"])
            receiver44.watchActors()
            global_Ambient29.sync()
            global_DemoLoop373.sync()
            if (truth(receiver44.quiet)) {
                global_clearTimeout(global_phraseTimer33)
                global_phraseTimer33 = 0
            } else {
                if (truth(jsAnd(global_Dollar("#shadowPhrase33")) { (!truth(global_phraseTimer33)) })) {
                    global_schedulePhrase33()
                }
            }
            return absent
        }
        obj1["power"] = fun(state: dynamic): dynamic {
            val receiver46: dynamic = js("this")
            receiver46.saver = (onPresent(state) { value50 -> value50.powerSave } === true)
            receiver46.thermal = global_Math.max(0, jsOr(global_Number(onPresent(state) { value53 -> value53.thermalStatus })) { 0 })
            receiver46.sync()
            return absent
        }
        obj1["lifecycle"] = fun(active: dynamic): dynamic {
            val receiver54: dynamic = js("this")
            receiver54.nativeActive = (active === true)
            if (truth((!truth(active)))) {
                global_clearTimeout(receiver54.idleTimer)
                receiver54.idleTimer = 0
                global_pauseGuide11()
                global_timerEnd = 0
                global_workEnd = 0
                global_TimerAlarm27.guide = dynamicValue(null)
                global_TimerAlarm27.rest = dynamicValue(null)
                global_TimerAlarm27.interval = dynamicValue(null)
                global_TimerAlarm27.stop()
                if (true) {
                    val iterator66: dynamic = iteratorOf(global_document.querySelectorAll("video"))
                    while (true) {
                        val next67: dynamic = iterator66.next()
                        if (truth(next67.done)) break
                        var v: dynamic = absent
                        v = next67.value
                        v.pause()
                    }
                }
            } else {
                receiver54.idle = false
                receiver54.activity()
            }
            receiver54.sync()
            return absent
        }
    }
    global_Ambient29["suspend"]()
    global_Object.assign(global_Ambient29, jsObject { obj70 ->
        obj70["animations373"] = js("[]")
        obj70["geometryKey373"] = ""
        obj70["paints373"] = 0
        obj70["suspend"] = fun(): dynamic {
            val receiver71: dynamic = js("this")
            if (truth(receiver71.frame)) {
                global_cancelAnimationFrame(receiver71.frame)
            }
            receiver71.frame = 0
            if (true) {
                val iterator75: dynamic = iteratorOf(receiver71.animations373)
                while (true) {
                    val next76: dynamic = iterator75.next()
                    if (truth(next76.done)) break
                    var a: dynamic = absent
                    a = next76.value
                    a.cancel()
                }
            }
            receiver71.animations373 = js("[]")
            onPresent(receiver71.canvas) { value79 -> value79.remove() }
            receiver71.canvas = dynamicValue(null)
            receiver71.ctx = dynamicValue(null)
            receiver71.particles = js("[]")
            receiver71.rect = dynamicValue(null)
            receiver71.geometryKey373 = ""
            return absent
        }
        obj70["sync"] = fun(): dynamic {
            val receiver90: dynamic = js("this")
            if (truth(jsOr((!truth(receiver90.allowed()))) { global_Runtime373.hidden })) {
                receiver90["suspend"]()
                return absent
            }
            val dialog: dynamic = global_Dollar("#modal")
            val target: dynamic = (if (truth(dialog["open"])) dialog else global_document.body)
            val key: dynamic = jsAdd(jsAdd((if (truth(dialog["open"])) jsAdd("d:", dynamicValue(jsonValue("[\"--modal-top\",\"--modal-left\",\"--modal-width\",\"--window-height26\"]")).map((fun(k: dynamic): dynamic {
                return dialog.style.getPropertyValue(k)
            })).join(":")) else jsAdd(jsAdd(jsAdd("b:", global_innerWidth), ":"), global_innerHeight)), ":"), global_Stars34.accent)
            if (truth(jsAnd(jsAnd(onPresent(receiver90.canvas) { value92 -> value92.isConnected }) { (receiver90.canvas.parentElement === target) }) { (key === receiver90.geometryKey373) })) {
                receiver90.settle373()
                return absent
            }
            val rect: dynamic = (if (truth(dialog["open"])) dialog.getBoundingClientRect() else jsObject { obj93 ->
                obj93["left"] = 0
                obj93["top"] = 0
                obj93["width"] = global_innerWidth
                obj93["height"] = global_innerHeight
            })
            if (truth(jsOr(jsLess(rect.width, 1)) { jsLess(rect.height, 1) })) {
                return absent
            }
            val w: dynamic = rect.width
            val h: dynamic = rect.height
            val particlePixelBudget384: dynamic = 850000
            val ratio: dynamic = global_Math.min(1.5, jsOr(global_devicePixelRatio) { 1 }, global_Math.max(0.25, global_Math.sqrt(jsDivide(particlePixelBudget384, global_Math.max(1, jsMultiply(jsAdd(w, 48), jsAdd(h, 48)))))))
            var host: dynamic = receiver90.canvas
            if (truth((!truth(host)))) {
                host = global_document.createElement("div")
                host.className = "ambient-particles29 ambient-composited373"
                host.setAttribute("aria-hidden", "true")
                receiver90.canvas = host
                run {
                    val target98: dynamic = receiver90
                    val previous100: dynamic = numeric(target98.mounts)
                    target98.mounts = jsAdd(previous100, 1)
                }
            }
            if (truth((host.parentElement !== target))) {
                target.append(host)
            }
            global_Object.assign(host.style, jsObject { obj101 ->
                obj101["left"] = jsAdd(rect.left, "px")
                obj101["top"] = jsAdd(rect.top, "px")
                obj101["width"] = jsAdd(w, "px")
                obj101["height"] = jsAdd(h, "px")
            })
            host.dataset.layer = (if (truth(dialog["open"])) "window" else "screen")
            val changed: dynamic = jsOr(jsOr(jsOr((!truth(receiver90.rect))) { jsAtLeast(global_Math.abs(jsSubtract(receiver90.rect.width, w)), 1) }) { jsAtLeast(global_Math.abs(jsSubtract(receiver90.rect.height, h)), 1) }) { (receiver90.accent373 !== global_Stars34.accent) }
            receiver90.rect = jsObject { obj106 ->
                obj106["left"] = rect.left
                obj106["top"] = rect.top
                obj106["width"] = w
                obj106["height"] = h
            }
            receiver90.geometryKey373 = key
            if (truth(changed)) {
                if (true) {
                    val iterator109: dynamic = iteratorOf(receiver90.animations373)
                    while (true) {
                        val next110: dynamic = iterator109.next()
                        if (truth(next110.done)) break
                        var a: dynamic = absent
                        a = next110.value
                        a.cancel()
                    }
                }
                receiver90.animations373 = js("[]")
                host.replaceChildren()
                receiver90.accent373 = global_Stars34.accent
                val count: dynamic = global_Math.min(52, global_Math.max(24, global_Math.round(jsDivide(jsMultiply(w, h), 11000))))
                receiver90.particles = global_Array.from(jsObject { obj117 ->
                    obj117["length"] = count
                }, (fun(_: dynamic, i: dynamic): dynamic {
                    return jsObject { obj118 ->
                        obj118["x"] = jsMultiply(global_Math.random(), w)
                        obj118["y"] = jsMultiply(global_Math.random(), h)
                        obj118["size"] = jsAdd(0.6, jsMultiply(global_Math.random(), 1.2))
                        obj118["kind"] = global_Stars29.type(i)
                        obj118["tint"] = (jsMod(i, 5) === 0)
                    }
                }))
                if (true) {
                    var layer: dynamic = absent
                    layer = 0
                    var firstIteration119 = true
                    while (true) {
                        if (!firstIteration119) {
                            run {
                                val previous120: dynamic = numeric(layer)
                                layer = jsAdd(previous120, 1)
                            }
                        }
                        firstIteration119 = false
                        if (!truth(jsLess(layer, 2))) break
                        val canvas: dynamic = global_document.createElement("canvas")
                        canvas.width = global_Math.round(jsMultiply(jsAdd(w, 48), ratio))
                        canvas.height = global_Math.round(jsMultiply(jsAdd(h, 48), ratio))
                        global_Object.assign(canvas.style, jsObject { obj125 ->
                            obj125["width"] = jsAdd(jsAdd(w, 48), "px")
                            obj125["height"] = jsAdd(jsAdd(h, 48), "px")
                        })
                        host.append(canvas)
                        val ctx: dynamic = canvas.getContext("2d")
                        if (truth((!truth(ctx)))) {
                            continue
                        }
                        ctx.setTransform(ratio, 0, 0, ratio, jsMultiply(24, ratio), jsMultiply(24, ratio))
                        receiver90.ctx = ctx
                        receiver90.particles.forEach((fun(p: dynamic, i: dynamic): dynamic {
                            if (truth((jsMod(i, 2) === layer))) {
                                global_Stars34.draw(ctx, p, p.x, p.y, jsAdd(0.1, jsMultiply(jsMod(i, 5), 0.025)))
                            }
                            return absent
                        }))
                        ctx.globalAlpha = 1
                        run {
                            val target130: dynamic = receiver90
                            val previous132: dynamic = numeric(target130.frames)
                            target130.frames = jsAdd(previous132, 1)
                        }
                        run {
                            val target133: dynamic = receiver90
                            val previous135: dynamic = numeric(target133.paints373)
                            target133.paints373 = jsAdd(previous135, 1)
                        }
                        if (truth((jsTypeOf(canvas.animate) === "function"))) {
                            receiver90.animations373.push(canvas.animate(jsonValue("[{\"transform\":\"translate3d(-4px,20px,0)\",\"opacity\":0.65},{\"transform\":\"translate3d(5px,-20px,0)\",\"opacity\":1}]"), jsObject { obj136 ->
                                obj136["duration"] = (if (truth(layer)) 21000 else 16000)
                                obj136["iterations"] = global_Infinity
                                obj136["direction"] = "alternate"
                                obj136["easing"] = "ease-in-out"
                                obj136["delay"] = (if (truth(layer)) (-numeric(11000)) else 0)
                            }))
                        }
                    }
                }
            }
            receiver90.settle373()
            return absent
        }
        obj70["settle373"] = fun(): dynamic {
            val receiver137: dynamic = js("this")
            if (true) {
                val iterator139: dynamic = iteratorOf(receiver137.animations373)
                while (true) {
                    val next140: dynamic = iterator139.next()
                    if (truth(next140.done)) break
                    var a: dynamic = absent
                    a = next140.value
                    if (truth(jsAnd(global_Runtime373.quiet) { (a.playState !== "paused") })) {
                        a.pause()
                    } else {
                        if (truth(jsAnd((!truth(global_Runtime373.quiet))) { (a.playState === "paused") })) {
                            a.play()
                        }
                    }
                }
            }
            return absent
        }
        obj70["scroll"] = fun(): dynamic {

            return absent
        }
    })
    global_DemoLoop373 = jsObject { obj143 ->
        obj143["entry"] = dynamicValue(null)
        obj143["frame"] = 0
        obj143["draws"] = 0
        obj143["stop"] = fun(): dynamic {
            val receiver144: dynamic = js("this")
            if (truth(receiver144.frame)) {
                global_cancelAnimationFrame(receiver144.frame)
            }
            receiver144.frame = 0
            onPresent(receiver144.entry) { value148 -> onPresent(value148.observer) { value149 -> value149.disconnect() } }
            receiver144.entry = dynamicValue(null)
            return absent
        }
        obj143["valid"] = fun(arg154: dynamic): dynamic {
            val receiver152: dynamic = js("this")
            var e: dynamic = absent
            e = definedOr(arg154) { receiver152.entry }
            return (!truth((!truth(jsAnd(jsAnd(jsAnd(e) { e.canvas.isConnected }) { global_Dollar("#modal")["open"] }) { (if (truth((e.kind === "vector"))) jsAnd((e.token === global_vectorGeneration13)) { onPresent(global_vectorSession) { value155 -> value155.playing } } else jsAnd((e.token === global_guideFrame11)) { onPresent(global_guide11) { value156 -> value156.demoPlaying } }) }))))
        }
        obj143["start"] = fun(kind: dynamic, token: dynamic): dynamic {
            val receiver157: dynamic = js("this")
            val canvas: dynamic = global_Dollar((if (truth((kind === "vector"))) "#vectorCanvas" else "#guidedCanvas"))
            if (truth((!truth(canvas)))) {
                return absent
            }
            if (truth(jsAnd(jsAnd((onPresent(receiver157.entry) { value159 -> value159.canvas } === canvas)) { (receiver157.entry.kind === kind) }) { (receiver157.entry.token === token) })) {
                receiver157.sync()
                return absent
            }
            receiver157.stop()
            val host: dynamic = (if (truth(onPresent(canvas.nextElementSibling) { value160 -> value160.classList.contains("motion-svg18") })) canvas.nextElementSibling else canvas)
            val b: dynamic = host.getBoundingClientRect()
            val e: dynamic = jsObject { obj162 ->
                obj162["kind"] = kind
                obj162["token"] = token
                obj162["canvas"] = canvas
                obj162["host"] = host
                obj162["visible"] = jsAnd(jsAnd(jsMore(b.bottom, 0)) { jsLess(b.top, global_innerHeight) }) { jsMore(b.width, 0) }
                obj162["observer"] = dynamicValue(null)
                obj162["last"] = 0
            }
            receiver157.entry = e
            if (truth((js("typeof IntersectionObserver") === "function"))) {
                e.observer = jsConstruct(global_IntersectionObserver, arrayOf<dynamic>((fun(rows: dynamic): dynamic {
                    if (truth((receiver157.entry !== e))) {
                        return absent
                    }
                    e.visible = rows.some((fun(x: dynamic): dynamic {
                        return x.isIntersecting
                    }))
                    receiver157.sync()
                    return absent
                })))
                e.observer.observe(host)
            }
            receiver157.sync()
            return absent
        }
        obj143["sync"] = fun(): dynamic {
            val receiver169: dynamic = js("this")
            val e: dynamic = receiver169.entry
            if (truth((!truth(receiver169.valid())))) {
                receiver169.stop()
                return absent
            }
            if (truth(jsOr(global_Runtime373.hidden) { (!truth(e.visible)) })) {
                if (truth(receiver169.frame)) {
                    global_cancelAnimationFrame(receiver169.frame)
                }
                receiver169.frame = 0
                e.last = 0
                return absent
            }
            if (truth((!truth(receiver169.frame)))) {
                receiver169.frame = global_requestAnimationFrame((fun(t: dynamic): dynamic {
                    return receiver169.tick(t)
                }))
            }
            return absent
        }
        obj143["tick"] = fun(time: dynamic): dynamic {
            val receiver177: dynamic = js("this")
            receiver177.frame = 0
            val e: dynamic = receiver177.entry
            if (truth((!truth(receiver177.valid())))) {
                receiver177.stop()
                return absent
            }
            if (truth(jsOr(global_Runtime373.hidden) { (!truth(e.visible)) })) {
                e.last = 0
                return absent
            }
            val dt: dynamic = (if (truth(e.last)) global_Math.min(100, global_Math.max(0, jsSubtract(time, e.last))) else 0)
            e.last = time
            if (truth((e.kind === "vector"))) {
                val s: dynamic = global_vectorSession
                s.frame = jsMod(jsAdd(s.frame, jsMultiply(jsDivide(jsMultiply(dt, 48), jsMultiply(global_cycleSeconds18(s.id), 1000)), s.speed)), 48)
                s.last = time
                global_vectorPaint()
            } else {
                val g: dynamic = global_guide11
                val step: dynamic = g.steps[g.index]
                g.demoFrame = jsMod(jsAdd(g.demoFrame, jsMultiply(jsDivide(jsMultiply(dt, 48), jsMultiply(global_cycleSeconds18(step.id), 1000)), global_MotionPlayback.rate)), 48)
                g.demoLast = time
                global_drawVector(e.canvas, step.id, g.demoFrame, (step.side === "left"))
            }
            run {
                val target193: dynamic = receiver177
                val previous195: dynamic = numeric(target193.draws)
                target193.draws = jsAdd(previous195, 1)
            }
            receiver177.sync()
            return absent
        }
    }
    global_vectorTick = (fun(time: dynamic, arg198: dynamic): dynamic {
        var token: dynamic = absent
        token = definedOr(arg198) { global_vectorGeneration13 }
        global_DemoLoop373.start("vector", token)
        return absent
    })
    global_animateGuidedVector11 = (fun(token: dynamic): dynamic {
        global_DemoLoop373.start("guided", token)
        return absent
    })
    global_runtimeUI373 = jsObject { obj201 ->
        obj201["render"] = global_render
        obj201["modal"] = global_modal
        obj201["backModal24"] = global_backModal24
        obj201["paintGuide11"] = global_paintGuide11
        obj201["openCoach"] = global_openCoach
    }
    global_render = (fun(arg204: dynamic): dynamic {
        val callArgs203: dynamic = js("arguments")
        var args: dynamic = absent
        args = argsAfter(callArgs203, 0)
        global_DemoLoop373.stop()
        val v: dynamic = run { val target206: dynamic = global_runtimeUI373; invokeWith(target206.render, target206, jsArray { array205 ->
            appendAll(array205, args)
        }) }
        global_Runtime373.sync()
        return v
    })
    global_modal = (fun(arg209: dynamic): dynamic {
        val callArgs208: dynamic = js("arguments")
        var args: dynamic = absent
        args = argsAfter(callArgs208, 0)
        global_DemoLoop373.stop()
        val v: dynamic = run { val target211: dynamic = global_runtimeUI373; invokeWith(target211.modal, target211, jsArray { array210 ->
            appendAll(array210, args)
        }) }
        global_Runtime373.sync()
        return v
    })
    global_backModal24 = (fun(arg214: dynamic): dynamic {
        val callArgs213: dynamic = js("arguments")
        var args: dynamic = absent
        args = argsAfter(callArgs213, 0)
        global_DemoLoop373.stop()
        val v: dynamic = run { val target216: dynamic = global_runtimeUI373; invokeWith(target216.backModal24, target216, jsArray { array215 ->
            appendAll(array215, args)
        }) }
        global_Runtime373.sync()
        return v
    })
    global_paintGuide11 = (fun(arg219: dynamic): dynamic {
        val callArgs218: dynamic = js("arguments")
        var args: dynamic = absent
        args = argsAfter(callArgs218, 0)
        val v: dynamic = run { val target221: dynamic = global_runtimeUI373; invokeWith(target221.paintGuide11, target221, jsArray { array220 ->
            appendAll(array220, args)
        }) }
        global_Runtime373.sync()
        return v
    })
    global_openCoach = (fun(arg224: dynamic): dynamic {
        val callArgs223: dynamic = js("arguments")
        var args: dynamic = absent
        args = argsAfter(callArgs223, 0)
        val v: dynamic = run { val target226: dynamic = global_runtimeUI373; invokeWith(target226.openCoach, target226, jsArray { array225 ->
            appendAll(array225, args)
        }) }
        global_Runtime373.sync()
        return v
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth((!truth(global_Dollar("#modal")["open"])))) {
            global_DemoLoop373.stop()
        }
        global_Runtime373.sync()
        return absent
    }))
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        return global_Runtime373.lifecycle((!truth(global_document.hidden)))
    }))
    global_window.addEventListener("pagehide", (fun(): dynamic {
        return global_Runtime373.lifecycle(false)
    }))
    global_window.addEventListener("pageshow", (fun(): dynamic {
        return global_Runtime373.lifecycle(true)
    }))
    if (true) {
        val iterator227: dynamic = iteratorOf(jsonValue("[\"pointerdown\",\"keydown\",\"scroll\"]"))
        while (true) {
            val next228: dynamic = iterator227.next()
            if (truth(next228.done)) break
            var name: dynamic = absent
            name = next228.value
            global_document.addEventListener(name, (fun(): dynamic {
                return global_Runtime373.activity()
            }), jsObject { obj229 ->
                obj229["passive"] = true
                obj229["capture"] = (name === "scroll")
            })
        }
    }
    global_window.HunterV373 = jsObject { obj232 ->
        obj232["runtime"] = global_Runtime373
        obj232["demos"] = global_DemoLoop373
        obj232["power"] = (fun(state: dynamic): dynamic {
            return global_Runtime373.power(state)
        })
        obj232["lifecycle"] = (fun(active: dynamic): dynamic {
            return global_Runtime373.lifecycle(active)
        })
    }
    try {
        if (truth(onPresent(global_window.Android) { value233 -> value233.runtimePowerState })) {
            global_Runtime373.power(global_JSON.parse(global_Android.runtimePowerState()))
        }
    } catch (e: dynamic) {

    }
    global_Runtime373.activity()
    global_Runtime373.sync()
}
