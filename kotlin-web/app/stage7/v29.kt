@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv29

import hunter.browser.*

@JsName("V29UI") private external var global_V29UI: dynamic
@JsName("layoutBefore29") private external var global_layoutBefore29: dynamic
@JsName("Stars29") private external var global_Stars29: dynamic
@JsName("Ambient29") private external var global_Ambient29: dynamic
@JsName("closeBefore29") private external var global_closeBefore29: dynamic
@JsName("entryDone29") private external var global_entryDone29: dynamic
@JsName("readiness29") private external var global_readiness29: dynamic
@JsName("readinessObserver29") private external var global_readinessObserver29: dynamic
@JsName("scrollPositions3817") private external var global_scrollPositions3817: dynamic
@JsName("pendingScroll3817") private external var global_pendingScroll3817: dynamic
@JsName("scrollFrame3817") private external var global_scrollFrame3817: dynamic
@JsName("viewportFrame3817") private external var global_viewportFrame3817: dynamic
@JsName("finiteScroll3817") private external var global_finiteScroll3817: dynamic
@JsName("cancelMotionFrames3817") private external var global_cancelMotionFrames3817: dynamic
@JsName("ambientScroll3817") private external var global_ambientScroll3817: dynamic
@JsName("ambientViewport3817") private external var global_ambientViewport3817: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("backModal24") private external var global_backModal24: dynamic
@JsName("UI26") private external var global_UI26: dynamic
@JsName("Evolution28") private external var global_Evolution28: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Particles28") private external var global_Particles28: dynamic
@JsName("devicePixelRatio") private external var global_devicePixelRatio: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("performance") private external var global_performance: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("cancelAnimationFrame") private external var global_cancelAnimationFrame: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("innerWidth") private external var global_innerWidth: dynamic
@JsName("innerHeight") private external var global_innerHeight: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("ThemeHues") private external var global_ThemeHues: dynamic
@JsName("themeNative20") private external var global_themeNative20: dynamic
@JsName("MutationObserver") private external var global_MutationObserver: dynamic
@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV29() {
    global_readiness29 = fun(): dynamic {
        if (truth(jsOr((js("typeof document") === "undefined")) { (!truth(global_document.documentElement)) })) {
            return absent
        }
        global_Ambient29.sync()
        if (truth(jsAnd((!truth(global_entryDone29))) { global_document.documentElement.classList.contains("ready20") })) {
            global_entryDone29 = true
            global_Particles28.burst("boot", jsObject { obj197 ->
                obj197["left"] = 0
                obj197["top"] = 0
                obj197["width"] = global_innerWidth
                obj197["height"] = global_innerHeight
            }, global_document.body)
        }
        return absent
    }
    global_cancelMotionFrames3817 = fun(): dynamic {
        if (truth(global_scrollFrame3817)) {
            global_cancelAnimationFrame(global_scrollFrame3817)
        }
        if (truth(global_viewportFrame3817)) {
            global_cancelAnimationFrame(global_viewportFrame3817)
        }
        global_scrollFrame3817 = run {
            val assigned201: dynamic = run {
                val assigned200: dynamic = 0
                global_pendingScroll3817 = assigned200
                assigned200
            }
            global_viewportFrame3817 = assigned201
            assigned201
        }
        return absent
    }
    global_ambientScroll3817 = fun(event: dynamic): dynamic {
        if (truth(jsOr(jsOr(global_document.hidden) { (!truth(event.target)) }) { onPresent(global_window.HunterOptimize48) { value204 -> value204.compositedAmbient } })) {
            return absent
        }
        val target: dynamic = event.target
        val top: dynamic = (if (truth((target === global_document))) global_finiteScroll3817(global_window.scrollY) else global_finiteScroll3817(global_Number(target.scrollTop)))
        val before: dynamic = nullableOr(global_scrollPositions3817["get"](target)) { 0 }
        global_scrollPositions3817["set"](target, top)
        global_pendingScroll3817 = global_Math.max((-numeric(240)), global_Math.min(240, jsAdd(global_pendingScroll3817, jsSubtract(top, before))))
        if (truth(global_scrollFrame3817)) {
            return absent
        }
        global_scrollFrame3817 = global_requestAnimationFrame((fun(): dynamic {
            global_scrollFrame3817 = 0
            val delta: dynamic = global_pendingScroll3817
            global_pendingScroll3817 = 0
            if (truth(jsAnd((!truth(global_document.hidden))) { delta })) {
                global_Ambient29.scroll(delta)
            }
            return absent
        }))
        return absent
    }
    global_ambientViewport3817 = fun(): dynamic {
        if (truth(jsOr(global_viewportFrame3817) { global_document.hidden })) {
            return absent
        }
        global_viewportFrame3817 = global_requestAnimationFrame((fun(): dynamic {
            global_viewportFrame3817 = 0
            if (truth((!truth(global_document.hidden)))) {
                global_Ambient29.sync()
            }
            return absent
        }))
        return absent
    }
    global_V29UI = jsObject { obj1 ->
        obj1["applyTheme"] = global_applyTheme
        obj1["home"] = global_home
        obj1["render"] = global_render
        obj1["modal"] = global_modal
        obj1["backModal24"] = global_backModal24
    }
    global_UI26.corner = "<svg viewBox=\"0 0 40 40\" fill=\"none\" aria-hidden=\"true\"><path d=\"M1 39V13L13 1h26M5 31V15L15 5h16\" stroke=\"currentColor\" stroke-width=\"1\"/><path d=\"M1 22V13L13 1h9\" stroke=\"var(--accent)\" stroke-width=\"1.6\"/><path d=\"m8 8 3 1-2 2Z\" fill=\"currentColor\"/></svg>"
    global_layoutBefore29 = global_Evolution28.layout
    global_Evolution28.layout = (fun(arg8: dynamic): dynamic {
        val receiver6: dynamic = js("this")
        var selection: dynamic = absent
        selection = definedOr(arg8) { jsOr(receiver6.current) { receiver6.selection() } }
        global_layoutBefore29.call(receiver6, selection)
        global_document.documentElement.dataset.sceneCharacter = selection.character
        if (true) {
            val iterator11: dynamic = iteratorOf(global_document.querySelectorAll(".evolution-art28"))
            while (true) {
                val next12: dynamic = iterator11.next()
                if (truth(next12.done)) break
                var art: dynamic = absent
                art = next12.value
                art.dataset.character = selection.character
            }
        }
        return absent
    })
    global_Stars29 = jsObject { obj15 ->
        obj15["accent"] = fun(): dynamic {
            return jsOr(global_document.documentElement.style.getPropertyValue("--accent").trim()) { "#9caec5" }
        }
        obj15["draw"] = fun(ctx: dynamic, p: dynamic, x: dynamic, y: dynamic, alpha: dynamic): dynamic {
            val receiver18: dynamic = js("this")
            ctx.globalAlpha = alpha
            ctx.fillStyle = (if (truth(p.tint)) receiver18.accent() else "#bdc9d8")
            ctx.strokeStyle = ctx.fillStyle
            if (truth((p.kind === "star"))) {
                val r: dynamic = p.size
                ctx.beginPath()
                ctx.moveTo(x, jsSubtract(y, jsMultiply(r, 2)))
                ctx.lineTo(jsAdd(x, jsMultiply(r, 0.32)), jsSubtract(y, jsMultiply(r, 0.32)))
                ctx.lineTo(jsAdd(x, jsMultiply(r, 2)), y)
                ctx.lineTo(jsAdd(x, jsMultiply(r, 0.32)), jsAdd(y, jsMultiply(r, 0.32)))
                ctx.lineTo(x, jsAdd(y, jsMultiply(r, 2)))
                ctx.lineTo(jsSubtract(x, jsMultiply(r, 0.32)), jsAdd(y, jsMultiply(r, 0.32)))
                ctx.lineTo(jsSubtract(x, jsMultiply(r, 2)), y)
                ctx.lineTo(jsSubtract(x, jsMultiply(r, 0.32)), jsSubtract(y, jsMultiply(r, 0.32)))
                ctx.closePath()
                ctx.fill()
            } else {
                if (truth((p.kind === "rune"))) {
                    val r: dynamic = jsMultiply(p.size, 1.7)
                    ctx.lineWidth = 0.7
                    ctx.beginPath()
                    ctx.moveTo(x, jsSubtract(y, r))
                    ctx.lineTo(jsAdd(x, jsMultiply(r, 0.6)), y)
                    ctx.lineTo(x, jsAdd(y, r))
                    ctx.lineTo(jsSubtract(x, jsMultiply(r, 0.6)), y)
                    ctx.closePath()
                    ctx.moveTo(x, jsSubtract(y, r))
                    ctx.lineTo(x, jsAdd(y, r))
                    ctx.stroke()
                } else {
                    ctx.beginPath()
                    ctx.arc(x, y, p.size, 0, jsMultiply(global_Math.PI, 2))
                    ctx.fill()
                }
            }
            return absent
        }
        obj15["type"] = fun(i: dynamic): dynamic {
            return (if (truth((jsMod(i, 11) === 0))) "rune" else (if (truth((jsMod(i, 4) === 0))) "star" else "dot"))
        }
    }
    global_Particles28.duration = 1300
    global_Particles28.burst = (fun(kind: dynamic, rect: dynamic, target: dynamic): dynamic {
        val receiver34: dynamic = js("this")
        var tick: dynamic = absent
        receiver34.stop()
        if (truth(jsOr(jsOr((!truth(receiver34.allowed()))) { jsLess(rect.width, 1) }) { jsLess(rect.height, 1) })) {
            return false
        }
        val inset: dynamic = 2
        val w: dynamic = global_Math.max(1, jsSubtract(rect.width, 4))
        val h: dynamic = global_Math.max(1, jsSubtract(rect.height, 4))
        val ratio: dynamic = global_Math.min(1.5, jsOr(global_devicePixelRatio) { 1 })
        val c: dynamic = global_document.createElement("canvas")
        c.className = "window-particles28 transition-particles29"
        c.setAttribute("aria-hidden", "true")
        c.dataset.effect = kind
        global_Object.assign(c.style, jsObject { obj40 ->
            obj40["left"] = jsAdd(jsAdd(rect.left, inset), "px")
            obj40["top"] = jsAdd(jsAdd(rect.top, inset), "px")
            obj40["width"] = jsAdd(w, "px")
            obj40["height"] = jsAdd(h, "px")
        })
        c.width = global_Math.round(jsMultiply(w, ratio))
        c.height = global_Math.round(jsMultiply(h, ratio))
        dynamicValue(jsOr(target) { global_document.body }).append(c)
        val ctx: dynamic = c.getContext("2d")
        if (truth((!truth(ctx)))) {
            c.remove()
            return false
        }
        ctx.scale(ratio, ratio)
        val count: dynamic = global_Math.min(140, global_Math.max(72, global_Math.round(jsDivide(jsAdd(w, h), 15))))
        receiver34.canvas = c
        receiver34.lastKind = kind
        run {
            val target49: dynamic = receiver34
            val previous51: dynamic = numeric(target49.bursts)
            target49.bursts = jsAdd(previous51, 1)
        }
        receiver34.started = global_performance.now()
        receiver34.particles = global_Array.from(jsObject { obj56 ->
            obj56["length"] = count
        }, (fun(_: dynamic, i: dynamic): dynamic {
            val edge: dynamic = jsMod(i, 4)
            val `inner`: dynamic = (jsMod(i, 5) === 0)
            val t: dynamic = jsAdd(0.06, jsMultiply(global_Math.random(), 0.88))
            val x: dynamic = (if (truth(`inner`)) jsMultiply(global_Math.random(), w) else (if (truth(jsOr((edge === 0)) { (edge === 2) })) jsMultiply(t, w) else (if (truth((edge === 1))) jsSubtract(w, 10) else 10)))
            val y: dynamic = (if (truth(`inner`)) jsMultiply(global_Math.random(), h) else (if (truth(jsOr((edge === 1)) { (edge === 3) })) jsMultiply(t, h) else (if (truth((edge === 0))) 10 else jsSubtract(h, 10))))
            val close: dynamic = jsOr((kind === "close")) { jsAnd((kind === "back")) { (jsMod(i, 2) === 0) } }
            val dx: dynamic = jsDivide(jsSubtract(jsDivide(w, 2), x), jsOr(jsDivide(w, 2)) { 1 })
            val dy: dynamic = jsDivide(jsSubtract(jsDivide(h, 2), y), jsOr(jsDivide(h, 2)) { 1 })
            return jsObject { obj57 ->
                obj57["x"] = x
                obj57["y"] = y
                obj57["vx"] = jsAdd(jsMultiply(dx, (if (truth(close)) (-numeric(58)) else 85)), jsMultiply(jsSubtract(global_Math.random(), 0.5), 20))
                obj57["vy"] = jsSubtract(jsMultiply(dy, (if (truth(close)) (-numeric(48)) else 65)), 25)
                obj57["size"] = jsAdd(1.1, jsMultiply(global_Math.random(), 1.8))
                obj57["kind"] = global_Stars29.type(i)
                obj57["tint"] = (jsMod(i, 4) === 0)
                obj57["delay"] = jsMultiply(global_Math.random(), 120)
            }
        }))
        tick = (fun(time: dynamic): dynamic {
            if (truth(jsOr((receiver34.canvas !== c)) { (!truth(receiver34.allowed())) })) {
                receiver34.stop()
                return absent
            }
            val elapsed: dynamic = jsSubtract(time, receiver34.started)
            ctx.clearRect(0, 0, w, h)
            if (true) {
                val iterator58: dynamic = iteratorOf(receiver34.particles)
                while (true) {
                    val next59: dynamic = iterator58.next()
                    if (truth(next59.done)) break
                    var p: dynamic = absent
                    p = next59.value
                    val life: dynamic = jsDivide(jsSubtract(elapsed, p.delay), receiver34.duration)
                    if (truth(jsOr(jsLess(life, 0)) { jsAtLeast(life, 1) })) {
                        continue
                    }
                    global_Stars29.draw(ctx, p, jsAdd(p.x, jsMultiply(p.vx, life)), jsAdd(p.y, jsMultiply(p.vy, life)), jsMultiply(global_Math.sin(jsMultiply(global_Math.PI, life)), 0.68))
                }
            }
            ctx.globalAlpha = 1
            if (truth(jsLess(elapsed, jsAdd(receiver34.duration, 120)))) {
                receiver34.frame = global_requestAnimationFrame(tick)
            } else {
                receiver34.stop()
            }
            return absent
        })
        receiver34.frame = global_requestAnimationFrame(tick)
        return true
    })
    global_Ambient29 = jsObject { obj66 ->
        obj66["canvas"] = dynamicValue(null)
        obj66["ctx"] = dynamicValue(null)
        obj66["frame"] = 0
        obj66["queued"] = 0
        obj66["particles"] = js("[]")
        obj66["rect"] = dynamicValue(null)
        obj66["last"] = 0
        obj66["drawAt"] = 0
        obj66["flow"] = 0
        obj66["frames"] = 0
        obj66["mounts"] = 0
        obj66["entryAt"] = 0
        obj66["allowed"] = fun(): dynamic {
            return jsAnd(jsAnd(jsAnd((js("typeof document") !== "undefined")) { (!truth((!truth(global_document.documentElement)))) }) { global_Particles28.allowed() }) { global_document.documentElement.classList.contains("ready20") }
        }
        obj66["suspend"] = fun(): dynamic {
            val receiver69: dynamic = js("this")
            if (truth(receiver69.queued)) {
                global_cancelAnimationFrame(receiver69.queued)
            }
            if (truth(receiver69.frame)) {
                global_cancelAnimationFrame(receiver69.frame)
            }
            receiver69.queued = run {
                val target73: dynamic = receiver69
                val assigned75: dynamic = 0
                target73.frame = assigned75
                assigned75
            }
            onPresent(receiver69.canvas) { value76 -> value76.remove() }
            receiver69.canvas = dynamicValue(null)
            receiver69.ctx = dynamicValue(null)
            receiver69.particles = js("[]")
            receiver69.last = 0
            receiver69.drawAt = 0
            return absent
        }
        obj66["schedule"] = fun(): dynamic {
            val receiver87: dynamic = js("this")
            if (truth((!truth(receiver87.allowed())))) {
                receiver87["suspend"]()
                return absent
            }
            if (truth(receiver87.queued)) {
                return absent
            }
            receiver87.queued = global_requestAnimationFrame((fun(): dynamic {
                receiver87.queued = 0
                receiver87.sync()
                return absent
            }))
            return absent
        }
        obj66["sync"] = fun(): dynamic {
            val receiver93: dynamic = js("this")
            var tick: dynamic = absent
            if (truth((!truth(receiver93.allowed())))) {
                receiver93["suspend"]()
                return absent
            }
            val dialog: dynamic = global_Dollar("#modal")
            val target: dynamic = (if (truth(dialog["open"])) dialog else global_document.body)
            val rect: dynamic = (if (truth(dialog["open"])) dialog.getBoundingClientRect() else jsObject { obj95 ->
                obj95["left"] = 0
                obj95["top"] = 0
                obj95["width"] = global_innerWidth
                obj95["height"] = global_innerHeight
            })
            if (truth(jsOr(jsLess(rect.width, 1)) { jsLess(rect.height, 1) })) {
                receiver93["suspend"]()
                return absent
            }
            val ratio: dynamic = global_Math.min(1.5, jsOr(global_devicePixelRatio) { 1 })
            val w: dynamic = rect.width
            val h: dynamic = rect.height
            if (truth(jsAnd(receiver93.canvas) { receiver93.ctx })) {
                val c: dynamic = receiver93.canvas
                val before: dynamic = receiver93.rect
                val reparent: dynamic = (c.parentElement !== target)
                if (truth(reparent)) {
                    target.append(c)
                }
                val resized: dynamic = jsOr(jsOr(jsAtLeast(global_Math.abs(jsSubtract(before.width, w)), 1)) { jsAtLeast(global_Math.abs(jsSubtract(before.height, h)), 1) }) { (receiver93.ratio29 !== ratio) }
                if (truth(resized)) {
                    if (true) {
                        val iterator96: dynamic = iteratorOf(receiver93.particles)
                        while (true) {
                            val next97: dynamic = iterator96.next()
                            if (truth(next97.done)) break
                            var p: dynamic = absent
                            p = next97.value
                            run {
                                val target98: dynamic = p
                                target98.x = jsMultiply(target98.x, jsDivide(w, before.width))
                            }
                            run {
                                val target100: dynamic = p
                                target100.y = jsMultiply(target100.y, jsDivide(h, before.height))
                            }
                        }
                    }
                    c.width = global_Math.round(jsMultiply(w, ratio))
                    c.height = global_Math.round(jsMultiply(h, ratio))
                    receiver93.ctx.setTransform(ratio, 0, 0, ratio, 0, 0)
                    receiver93.ratio29 = ratio
                }
                val relocated: dynamic = jsOr((before.left !== rect.left)) { (before.top !== rect.top) }
                if (truth(jsOr(jsOr(reparent) { resized }) { relocated })) {
                    global_Object.assign(c.style, jsObject { obj108 ->
                        obj108["left"] = jsAdd(rect.left, "px")
                        obj108["top"] = jsAdd(rect.top, "px")
                        obj108["width"] = jsAdd(w, "px")
                        obj108["height"] = jsAdd(h, "px")
                    })
                    c.dataset.layer = (if (truth(dialog["open"])) "window" else "screen")
                    receiver93.rect = jsObject { obj113 ->
                        obj113["left"] = rect.left
                        obj113["top"] = rect.top
                        obj113["width"] = w
                        obj113["height"] = h
                    }
                }
                return absent
            }
            val c: dynamic = global_document.createElement("canvas")
            c.className = "ambient-particles29"
            c.setAttribute("aria-hidden", "true")
            c.dataset.layer = (if (truth(dialog["open"])) "window" else "screen")
            global_Object.assign(c.style, jsObject { obj118 ->
                obj118["left"] = jsAdd(rect.left, "px")
                obj118["top"] = jsAdd(rect.top, "px")
                obj118["width"] = jsAdd(w, "px")
                obj118["height"] = jsAdd(h, "px")
            })
            c.width = global_Math.round(jsMultiply(w, ratio))
            c.height = global_Math.round(jsMultiply(h, ratio))
            target.append(c)
            val ctx: dynamic = c.getContext("2d")
            if (truth((!truth(ctx)))) {
                c.remove()
                return absent
            }
            ctx.scale(ratio, ratio)
            receiver93.canvas = c
            receiver93.ctx = ctx
            receiver93.ratio29 = ratio
            receiver93.rect = jsObject { obj131 ->
                obj131["left"] = rect.left
                obj131["top"] = rect.top
                obj131["width"] = w
                obj131["height"] = h
            }
            receiver93.entryAt = global_performance.now()
            run {
                val target134: dynamic = receiver93
                val previous136: dynamic = numeric(target134.mounts)
                target134.mounts = jsAdd(previous136, 1)
            }
            val count: dynamic = global_Math.min(100, global_Math.max(36, global_Math.round(jsDivide(jsMultiply(w, h), 7500))))
            receiver93.particles = global_Array.from(jsObject { obj139 ->
                obj139["length"] = count
            }, (fun(_: dynamic, i: dynamic): dynamic {
                return jsObject { obj140 ->
                    obj140["x"] = jsMultiply(global_Math.random(), w)
                    obj140["y"] = jsMultiply(global_Math.random(), h)
                    obj140["vx"] = jsMultiply(jsSubtract(global_Math.random(), 0.5), 4)
                    obj140["vy"] = jsSubtract((-numeric(7)), jsMultiply(global_Math.random(), 12))
                    obj140["size"] = jsAdd(0.8, jsMultiply(global_Math.random(), 1.35))
                    obj140["kind"] = global_Stars29.type(i)
                    obj140["tint"] = (jsMod(i, 5) === 0)
                    obj140["phase"] = jsMultiply(jsMultiply(global_Math.random(), global_Math.PI), 2)
                    obj140["period"] = jsAdd(3, jsMultiply(global_Math.random(), 4))
                }
            }))
            tick = (fun(time: dynamic): dynamic {
                if (truth((receiver93.canvas !== c))) {
                    return absent
                }
                if (truth((!truth(receiver93.allowed())))) {
                    receiver93["suspend"]()
                    return absent
                }
                if (truth((!truth(c.isConnected)))) {
                    receiver93.sync()
                    if (truth((!truth(c.isConnected)))) {
                        return absent
                    }
                }
                if (truth(jsAnd(receiver93.drawAt) { jsLess(jsSubtract(time, receiver93.drawAt), jsDivide(1000, 30)) })) {
                    receiver93.frame = global_requestAnimationFrame(tick)
                    return absent
                }
                val dt: dynamic = (if (truth(receiver93.last)) global_Math.min(0.08, jsDivide(jsSubtract(time, receiver93.last), 1000)) else jsDivide(1, 30))
                receiver93.last = time
                receiver93.drawAt = time
                val size: dynamic = receiver93.rect
                ctx.clearRect(0, 0, size.width, size.height)
                run {
                    val target147: dynamic = receiver93
                    target147.flow = jsMultiply(target147.flow, global_Math.exp(jsMultiply((-numeric(dt)), 4.5)))
                }
                if (true) {
                    val iterator149: dynamic = iteratorOf(receiver93.particles)
                    while (true) {
                        val next150: dynamic = iterator149.next()
                        if (truth(next150.done)) break
                        var p: dynamic = absent
                        p = next150.value
                        p.x = jsMod(jsAdd(jsAdd(p.x, jsMultiply(p.vx, dt)), size.width), size.width)
                        p.y = jsMod(jsAdd(jsAdd(p.y, jsMultiply(jsSubtract(p.vy, receiver93.flow), dt)), size.height), size.height)
                        val wave: dynamic = jsAdd(0.5, jsMultiply(0.5, global_Math.sin(jsAdd(jsMultiply(jsMultiply(jsDivide(jsDivide(time, 1000), p.period), global_Math.PI), 2), p.phase))))
                        val intro: dynamic = global_Math.max(0, jsSubtract(1, jsDivide(jsSubtract(time, receiver93.entryAt), 1800)))
                        global_Stars29.draw(ctx, p, p.x, p.y, jsAdd(jsAdd(0.12, jsMultiply(wave, 0.2)), jsMultiply(intro, 0.12)))
                    }
                }
                ctx.globalAlpha = 1
                run {
                    val target157: dynamic = receiver93
                    val previous159: dynamic = numeric(target157.frames)
                    target157.frames = jsAdd(previous159, 1)
                }
                receiver93.frame = global_requestAnimationFrame(tick)
                return absent
            })
            receiver93.frame = global_requestAnimationFrame(tick)
            return absent
        }
        obj66["scroll"] = fun(delta: dynamic): dynamic {
            val receiver164: dynamic = js("this")
            if (truth((!truth(receiver164.allowed())))) {
                return absent
            }
            receiver164.flow = global_Math.max((-numeric(90)), global_Math.min(90, jsAdd(receiver164.flow, jsMultiply(delta, 0.45))))
            return absent
        }
    }
    global_applyTheme = (fun(arg170: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg170) { jsOr(global_state.profile) { js("({})") } }
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V29UI.applyTheme(p)
        val root: dynamic = global_document.documentElement
        val h: dynamic = nullableOr(global_ThemeHues[root.dataset.theme]) { global_ThemeHues.blue }
        val vars: dynamic = jsObject { obj171 ->
            obj171["--bg"] = "#070a10"
            obj171["--panel"] = "#101620"
            obj171["--surface"] = "#151d29"
            obj171["--input"] = "#090e17"
            obj171["--line"] = ("hsl(" + textOf(h) + " 11% 31%)")
            obj171["--text"] = "#c9d1dd"
            obj171["--muted"] = "#97a5b8"
            obj171["--accent"] = ("hsl(" + textOf(h) + " 28% 70%)")
            obj171["--cyan"] = ("hsl(" + textOf(h) + " 28% 70%)")
            obj171["--violet"] = ("hsl(" + textOf(h) + " 12% 65%)")
            obj171["--ornament26"] = ("hsl(" + textOf(h) + " 18% 43%)")
            obj171["--metal26"] = ("hsl(" + textOf(h) + " 10% 23%)")
            obj171["--glow"] = ("hsla(" + textOf(h) + ",30%,65%,.07)")
        }
        global_Object.entries(vars).forEach((fun(arg172: dynamic): dynamic {
            var k: dynamic = absent
            var v: dynamic = absent
            val unpacked173: dynamic = fromIterable(arg172)
            k = unpacked173[0]
            v = unpacked173[1]
            return root.style.setProperty(k, v)
        }))
        global_document.querySelector("meta[name=theme-color]").content = vars["--bg"]
        global_themeNative20()
        global_Ambient29.schedule()
        return absent
    })
    global_home = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V29UI.home()
        val s: dynamic = global_Evolution28.selection()
        val art: dynamic = box.querySelector(".evolution-art28")
        if (truth(art)) {
            art.dataset.character = s.character
        }
        return box.innerHTML
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_V29UI.modal(html)
        global_Ambient29.schedule()
        return absent
    })
    global_backModal24 = (fun(): dynamic {
        global_V29UI.backModal24()
        global_Ambient29.schedule()
        return absent
    })
    global_closeBefore29 = global_Dollar("#modal").close
    global_Dollar("#modal").close = (fun(`value`: dynamic): dynamic {
        val receiver188: dynamic = js("this")
        val result: dynamic = global_closeBefore29.call(receiver188, `value`)
        global_Ambient29.schedule()
        return result
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V29UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target192: dynamic = n
                val assigned194: dynamic = "SISTEMA DE SOMBRAS"
                target192.textContent = assigned194
                assigned194
            }
        }))
        global_Ambient29.schedule()
        return absent
    })
    global_entryDone29 = false
    global_readinessObserver29 = jsConstruct(global_MutationObserver, arrayOf<dynamic>(global_readiness29))
    global_readinessObserver29.observe(global_document.documentElement, jsonValue("{\"attributes\":true,\"attributeFilter\":[\"class\"]}"))
    global_scrollPositions3817 = jsConstruct(global_WeakMap, js("[]"))
    global_pendingScroll3817 = 0
    global_scrollFrame3817 = 0
    global_viewportFrame3817 = 0
    global_finiteScroll3817 = (fun(n: dynamic): dynamic {
        return (if (truth(global_Number.isFinite(n))) n else 0)
    })
    global_document.addEventListener("scroll", global_ambientScroll3817, jsonValue("{\"capture\":true,\"passive\":true}"))
    global_window.addEventListener("resize", global_ambientViewport3817, jsonValue("{\"passive\":true}"))
    onPresent(global_window.visualViewport) { value207 -> value207.addEventListener("resize", global_ambientViewport3817, jsonValue("{\"passive\":true}")) }
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        if (truth(global_document.hidden)) {
            global_cancelMotionFrames3817()
        }
        global_Ambient29.sync()
        return absent
    }))
    run { val owner209: dynamic = global_matchMedia("(prefers-reduced-motion: reduce)"); onPresent(owner209.addEventListener) { method208 -> invokeWith(method208, owner209, arrayOf<dynamic>("change", global_ambientViewport3817)) } }
    global_window.addEventListener("pagehide", (fun(): dynamic {
        global_cancelMotionFrames3817()
        global_readinessObserver29.disconnect()
        global_Ambient29["suspend"]()
        global_Particles28.stop()
        return absent
    }))
    global_window.addEventListener("pageshow", (fun(): dynamic {
        global_readinessObserver29.observe(global_document.documentElement, jsonValue("{\"attributes\":true,\"attributeFilter\":[\"class\"]}"))
        global_readiness29()
        return absent
    }))
    global_window.HunterScroll3817 = global_Object.freeze(jsObject { obj212 ->
        obj212["version"] = "38.17"
        obj212["status"] = (fun(): dynamic {
            return jsObject { obj213 ->
                obj213["queued"] = (!truth((!truth(global_scrollFrame3817))))
                obj213["queuedViewport"] = (!truth((!truth(global_viewportFrame3817))))
            }
        })
    })
    global_window.HunterV29 = jsObject { obj216 ->
        obj216["Ambient29"] = global_Ambient29
        obj216["Stars29"] = global_Stars29
        obj216["transitions"] = global_Particles28
    }
    global_state.appVersion = 29
    global_applyTheme()
    global_save()
    global_render()
    global_readiness29()
}
