@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv34

import hunter.browser.*

@JsName("V34UI") private external var global_V34UI: dynamic
@JsName("FramePacer34") private external var global_FramePacer34: dynamic
@JsName("Performance34") private external var global_Performance34: dynamic
@JsName("hdPaths34") private external var global_hdPaths34: dynamic
@JsName("visualSelectionBefore34") private external var global_visualSelectionBefore34: dynamic
@JsName("Hero34") private external var global_Hero34: dynamic
@JsName("Stars34") private external var global_Stars34: dynamic
@JsName("burstBefore34") private external var global_burstBefore34: dynamic
@JsName("shortPhrases34") private external var global_shortPhrases34: dynamic
@JsName("dailyPhrase34") private external var global_dailyPhrase34: dynamic
@JsName("thoughtMarkup34") private external var global_thoughtMarkup34: dynamic
@JsName("mountPanelBefore34") private external var global_mountPanelBefore34: dynamic
@JsName("completeBefore34") private external var global_completeBefore34: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("header") private external var global_header: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Visual33") private external var global_Visual33: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Image") private external var global_Image: dynamic
@JsName("Promise") private external var global_Promise: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("themeNative20") private external var global_themeNative20: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("getComputedStyle") private external var global_getComputedStyle: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("HunterNative3875") private external var global_HunterNative3875: dynamic
@JsName("Stars29") private external var global_Stars29: dynamic
@JsName("Particles28") private external var global_Particles28: dynamic
@JsName("Ambient29") private external var global_Ambient29: dynamic
@JsName("innerWidth") private external var global_innerWidth: dynamic
@JsName("innerHeight") private external var global_innerHeight: dynamic
@JsName("devicePixelRatio") private external var global_devicePixelRatio: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("performance") private external var global_performance: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("updatePhrase33") private external var global_updatePhrase33: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("schedulePhrase33") private external var global_schedulePhrase33: dynamic
@JsName("clearTimeout") private external var global_clearTimeout: dynamic
@JsName("phraseTimer33") private external var global_phraseTimer33: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("UI26") private external var global_UI26: dynamic
@JsName("mountPanelCompanion") private external var global_mountPanelCompanion: dynamic
@JsName("Transition32") private external var global_Transition32: dynamic
@JsName("Back25") private external var global_Back25: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Boot30") private external var global_Boot30: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV34() {
    global_dailyPhrase34 = fun(arg194: dynamic, arg195: dynamic): dynamic {
        var type: dynamic = absent
        type = definedOr(arg194) { global_selectedCompanion() }
        var date: dynamic = absent
        date = definedOr(arg195) { global_E.dateKey() }
        val day: dynamic = global_E.diff(date, "2026-01-01")
        val hash: dynamic = dynamicValue(jsArray { array197 ->
            appendAll(array197, type)
        }).reduce((fun(a: dynamic, c: dynamic): dynamic {
            return jsAdd(a, c.charCodeAt(0))
        }), 0)
        val list: dynamic = jsOr(global_shortPhrases34[type]) { global_shortPhrases34.knight }
        return jsMod(jsAdd(jsMod(jsAdd(day, hash), list.length), list.length), list.length)
    }
    global_thoughtMarkup34 = fun(text: dynamic): dynamic {
        return jsAdd(jsAdd("<div class=\"shadow-thought34 thought-pop34\"><svg class=\"thought-shape34\" viewBox=\"0 0 300 190\" preserveAspectRatio=\"none\" aria-hidden=\"true\"><path class=\"thought-cloud34\" d=\"M34 73C8 56 25 22 57 27C63 5 99 4 115 20C137 0 170 2 185 20C211 5 242 16 245 38C279 28 300 57 282 80C309 102 291 136 267 136C269 163 234 180 210 164C191 187 156 183 142 169C117 186 88 176 78 164C45 178 21 156 28 134C0 124 7 87 34 73Z\"/><path class=\"thought-inner34\" d=\"M38 73C17 56 31 30 59 34C67 12 100 12 115 27C138 10 168 10 184 28C210 14 235 24 239 45C268 36 290 58 274 81C300 103 284 130 260 130C264 155 235 170 211 157C190 179 159 176 142 162C119 178 91 169 82 156C53 171 29 151 36 129C10 121 17 91 38 73Z\"/></svg><p id=\"shadowPhrase33\" class=\"thought-copy34\">", global_esc(text)), "</p><i class=\"thought-trail34\" aria-hidden=\"true\"></i><i class=\"thought-trail34 small34\" aria-hidden=\"true\"></i><i class=\"thought-trail34 tiny34\" aria-hidden=\"true\"></i></div>")
    }
    global_V34UI = jsObject { obj1 ->
        obj1["render"] = global_render
        obj1["home"] = global_home
        obj1["openCoach"] = global_openCoach
        obj1["applyTheme"] = global_applyTheme
        obj1["modal"] = global_modal
        obj1["header"] = global_header
        obj1["openDiagnostics20"] = global_openDiagnostics20
    }
    global_FramePacer34 = jsObject { obj2 ->
        obj2["target"] = 90
        obj2["next"] = 0
        obj2["reset"] = fun(): dynamic {
            val receiver3: dynamic = js("this")
            receiver3.next = 0
            return absent
        }
        obj2["shouldDraw"] = fun(time: dynamic): dynamic {
            val receiver7: dynamic = js("this")
            val interval: dynamic = jsDivide(1000, receiver7.target)
            if (truth((!truth(receiver7.next)))) {
                receiver7.next = jsAdd(time, interval)
                return true
            }
            if (truth(jsLess(jsAdd(time, 0.35), receiver7.next))) {
                return false
            }
            run {
                val target11: dynamic = receiver7
                target11.next = jsAdd(target11.next, interval)
            }
            if (truth(jsMore(jsSubtract(time, receiver7.next), jsMultiply(interval, 3)))) {
                receiver7.next = jsAdd(time, interval)
            }
            return true
        }
    }
    global_Performance34 = jsObject { obj15 ->
        obj15["target"] = 90
        obj15["routeChanging"] = false
        obj15["surfaceOpening"] = false
        obj15["lastTab"] = dynamicValue(null)
        obj15["profileReady"] = false
        obj15["intervals"] = js("[]")
        obj15["lastFrame"] = 0
        obj15["nativeTheme"] = dynamicValue(null)
        obj15["themeFrame"] = 0
        obj15["draws"] = 0
        obj15["sample"] = fun(time: dynamic): dynamic {
            val receiver16: dynamic = js("this")
            if (truth(receiver16.lastFrame)) {
                val dt: dynamic = jsSubtract(time, receiver16.lastFrame)
                if (truth(jsAnd(jsAtLeast(dt, 3)) { jsAtMost(dt, 100) })) {
                    receiver16.intervals.push(dt)
                    if (truth(jsMore(receiver16.intervals.length, 180))) {
                        receiver16.intervals.shift()
                    }
                }
            }
            receiver16.lastFrame = time
            return absent
        }
        obj15["observed"] = fun(): dynamic {
            val receiver20: dynamic = js("this")
            if (truth(jsLess(receiver20.intervals.length, 30))) {
                return null
            }
            return global_Math.round(jsDivide(1000, jsDivide(receiver20.intervals.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b)
            }), 0), receiver20.intervals.length)))
        }
        obj15["reset"] = fun(): dynamic {
            val receiver22: dynamic = js("this")
            receiver22.lastFrame = 0
            receiver22.intervals = js("[]")
            global_FramePacer34.reset()
            return absent
        }
    }
    global_hdPaths34 = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"reader-e\",\"reader-d\",\"reader-c\",\"reader-b\",\"reader-a\",\"reader-s\",\"hunter-male-e\",\"hunter-female-e\",\"regressor-e\"]")))
    global_visualSelectionBefore34 = global_Visual33.selection
    global_Visual33.selection = (fun(arg32: dynamic): dynamic {
        val receiver30: dynamic = js("this")
        val callArgs31: dynamic = js("arguments")
        var args: dynamic = absent
        args = argsAfter(callArgs31, 0)
        val s: dynamic = global_visualSelectionBefore34.apply(receiver30, args)
        val key: dynamic = s.figure.split("/").pop().replace(".webp", "")
        return jsObject { obj33 ->
            copyFields(obj33, s)
            obj33["originalFigure"] = s.figure
            obj33["figure"] = (if (truth(global_hdPaths34.has(key))) jsAdd(jsAdd("ui34/", key), ".webp") else s.figure)
        }
    })
    global_Hero34 = jsObject { obj34 ->
        obj34["cached"] = jsConstruct(global_Map, js("[]"))
        obj34["decode"] = fun(path: dynamic): dynamic {
            val receiver35: dynamic = js("this")
            if (truth(receiver35.cached.has(path))) {
                val hit: dynamic = receiver35.cached["get"](path)
                receiver35.cached.delete(path)
                receiver35.cached["set"](path, hit)
                return hit.promise
            }
            val image: dynamic = jsConstruct(global_Image, js("[]"))
            image.src = path
            val promise: dynamic = (if (truth(image.decode)) image.decode()["catch"]((fun(): dynamic {
                return false
            })) else global_Promise.resolve(true))
            receiver35.cached["set"](path, jsObject { obj39 ->
                obj39["image"] = image
                obj39["promise"] = promise
            })
            while (truth(jsMore(receiver35.cached.size, 3))) {
                receiver35.cached.delete(receiver35.cached.keys().next()["value"])
            }
            return promise
        }
        obj34["preserve"] = fun(node: dynamic): dynamic {
            val receiver40: dynamic = js("this")
            val fresh: dynamic = global_Dollar("#app .hero-figure33")
            if (truth(jsAnd(jsAnd(node) { fresh }) { (node.getAttribute("src") === fresh.getAttribute("src")) })) {
                fresh.replaceWith(node)
            }
            val img: dynamic = global_Dollar("#app .hero-figure33")
            if (truth(img)) {
                img.decoding = "async"
                img.fetchPriority = "high"
                img.onerror = (fun(): dynamic {
                    img.onerror = dynamicValue(null)
                    img.src = global_Visual33.selection().originalFigure
                    return absent
                })
                receiver40.decode(img.getAttribute("src"))
            }
            return absent
        }
    }
    global_header = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V34UI.header()
        val label: dynamic = box.querySelector(".brand small")
        if (truth(label)) {
            label.textContent = "SISTEMA DE SOMBRAS"
        }
        return box.innerHTML
    })
    global_themeNative20 = (fun(): dynamic {
        if (truth(global_Performance34.themeFrame)) {
            return absent
        }
        global_Performance34.themeFrame = global_requestAnimationFrame((fun(): dynamic {
            global_Performance34.themeFrame = 0
            if (truth((!truth(onPresent(global_window.Android) { value64 -> value64.setThemeBackground })))) {
                return absent
            }
            val c: dynamic = global_getComputedStyle(global_document.body).backgroundColor.match(jsRegExp("\\d+", "g"))
            if (truth(jsOr((!truth(c))) { jsLess(c.length, 3) })) {
                return absent
            }
            val color: dynamic = jsAdd("#", c.slice(0, 3).map((fun(x: dynamic): dynamic {
                return global_Number(x)["toString"](16).padStart(2, "0")
            })).join(""))
            if (truth((color === global_Performance34.nativeTheme))) {
                return absent
            }
            global_Performance34.nativeTheme = color
            global_HunterNative3875.setThemeBackground(color)
            return absent
        }))
        return absent
    })
    global_Stars34 = jsObject { obj67 ->
        obj67["accent"] = "#a9bacf"
        obj67["ink"] = "#aebdcc"
        obj67["refresh"] = fun(): dynamic {
            val receiver68: dynamic = js("this")
            receiver68.accent = jsOr(global_document.documentElement.style.getPropertyValue("--accent").trim()) { "#a9bacf" }
            return absent
        }
        obj67["draw"] = fun(ctx: dynamic, p: dynamic, x: dynamic, y: dynamic, alpha: dynamic): dynamic {
            val receiver72: dynamic = js("this")
            ctx.globalAlpha = alpha
            ctx.fillStyle = (if (truth(p.tint)) receiver72.accent else receiver72.ink)
            ctx.strokeStyle = ctx.fillStyle
            if (truth((p.kind === "star"))) {
                val r: dynamic = p.size
                ctx.beginPath()
                ctx.moveTo(x, jsSubtract(y, jsMultiply(r, 1.8)))
                ctx.lineTo(jsAdd(x, jsMultiply(r, 0.28)), jsSubtract(y, jsMultiply(r, 0.28)))
                ctx.lineTo(jsAdd(x, jsMultiply(r, 1.8)), y)
                ctx.lineTo(jsAdd(x, jsMultiply(r, 0.28)), jsAdd(y, jsMultiply(r, 0.28)))
                ctx.lineTo(x, jsAdd(y, jsMultiply(r, 1.8)))
                ctx.lineTo(jsSubtract(x, jsMultiply(r, 0.28)), jsAdd(y, jsMultiply(r, 0.28)))
                ctx.lineTo(jsSubtract(x, jsMultiply(r, 1.8)), y)
                ctx.lineTo(jsSubtract(x, jsMultiply(r, 0.28)), jsSubtract(y, jsMultiply(r, 0.28)))
                ctx.closePath()
                ctx.fill()
            } else {
                if (truth((p.kind === "rune"))) {
                    val r: dynamic = jsMultiply(p.size, 1.5)
                    ctx.lineWidth = 0.65
                    ctx.beginPath()
                    ctx.moveTo(x, jsSubtract(y, r))
                    ctx.lineTo(jsAdd(x, jsMultiply(r, 0.6)), y)
                    ctx.lineTo(x, jsAdd(y, r))
                    ctx.lineTo(jsSubtract(x, jsMultiply(r, 0.6)), y)
                    ctx.closePath()
                    ctx.stroke()
                } else {
                    ctx.beginPath()
                    ctx.arc(x, y, p.size, 0, jsMultiply(global_Math.PI, 2))
                    ctx.fill()
                }
            }
            return absent
        }
    }
    global_Stars29.draw = (fun(arg86: dynamic): dynamic {
        val callArgs85: dynamic = js("arguments")
        var args: dynamic = absent
        args = argsAfter(callArgs85, 0)
        run { val target88: dynamic = global_Stars34; invokeWith(target88.draw, target88, jsArray { array87 ->
            appendAll(array87, args)
        }) }
        return absent
    })
    global_burstBefore34 = global_Particles28.burst
    global_Particles28.burst = (fun(kind: dynamic, rect: dynamic, target: dynamic): dynamic {
        val receiver91: dynamic = js("this")
        val result: dynamic = global_burstBefore34.call(receiver91, kind, rect, target)
        if (truth((!truth(result)))) {
            return result
        }
        val w: dynamic = global_Math.max(1, jsSubtract(rect.width, 4))
        val h: dynamic = global_Math.max(1, jsSubtract(rect.height, 4))
        val safeTop: dynamic = (if (truth((onPresent(target) { value93 -> value93.id } === "modal"))) 64 else 10)
        if (true) {
            val iterator94: dynamic = iteratorOf(receiver91.particles)
            while (true) {
                val next95: dynamic = iterator94.next()
                if (truth(next95.done)) break
                var p: dynamic = absent
                p = next95.value
                p.x = jsAdd(12, jsMultiply(global_Math.random(), global_Math.max(1, jsSubtract(w, 24))))
                p.y = jsAdd(safeTop, jsMultiply(global_Math.random(), global_Math.max(1, jsSubtract(jsSubtract(h, safeTop), 12))))
                p.vx = jsMultiply(jsSubtract(global_Math.random(), 0.5), 30)
                p.vy = jsSubtract((-numeric(12)), jsMultiply(global_Math.random(), 22))
            }
        }
        return result
    })
    global_Ambient29["suspend"]()
    global_Ambient29.geometryKey34 = ""
    global_Ambient29.sync = (fun(): dynamic {
        val receiver108: dynamic = js("this")
        var tick: dynamic = absent
        if (truth((!truth(receiver108.allowed())))) {
            receiver108["suspend"]()
            receiver108.geometryKey34 = ""
            global_Performance34.reset()
            return absent
        }
        val dialog: dynamic = global_Dollar("#modal")
        val target: dynamic = (if (truth(dialog["open"])) dialog else global_document.body)
        val key: dynamic = (if (truth(dialog["open"])) jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("d:", dialog.style.getPropertyValue("--modal-top")), ":"), dialog.style.getPropertyValue("--modal-left")), ":"), dialog.style.getPropertyValue("--modal-width")), ":"), dialog.style.getPropertyValue("--window-height26")) else jsAdd(jsAdd(jsAdd("b:", global_innerWidth), ":"), global_innerHeight))
        if (truth(jsAnd(jsAnd(onPresent(receiver108.canvas) { value112 -> value112.isConnected }) { (receiver108.canvas.parentElement === target) }) { (receiver108.geometryKey34 === key) })) {
            return absent
        }
        val rect: dynamic = (if (truth(dialog["open"])) dialog.getBoundingClientRect() else jsObject { obj113 ->
            obj113["left"] = 0
            obj113["top"] = 0
            obj113["width"] = global_innerWidth
            obj113["height"] = global_innerHeight
        })
        if (truth(jsOr(jsLess(rect.width, 1)) { jsLess(rect.height, 1) })) {
            return absent
        }
        val ratio: dynamic = global_Math.min(1.5, jsOr(global_devicePixelRatio) { 1 })
        val w: dynamic = rect.width
        val h: dynamic = rect.height
        if (truth(jsAnd(receiver108.canvas) { receiver108.ctx })) {
            val previous: dynamic = receiver108.rect
            if (truth((receiver108.canvas.parentElement !== target))) {
                target.append(receiver108.canvas)
            }
            if (truth(jsOr(jsOr(jsOr((!truth(previous))) { jsAtLeast(global_Math.abs(jsSubtract(previous.width, w)), 1) }) { jsAtLeast(global_Math.abs(jsSubtract(previous.height, h)), 1) }) { (receiver108.ratio29 !== ratio) })) {
                if (truth(previous)) {
                    if (true) {
                        val iterator114: dynamic = iteratorOf(receiver108.particles)
                        while (true) {
                            val next115: dynamic = iterator114.next()
                            if (truth(next115.done)) break
                            var p: dynamic = absent
                            p = next115.value
                            run {
                                val target116: dynamic = p
                                target116.x = jsMultiply(target116.x, jsDivide(w, previous.width))
                            }
                            run {
                                val target118: dynamic = p
                                target118.y = jsMultiply(target118.y, jsDivide(h, previous.height))
                            }
                        }
                    }
                }
                receiver108.canvas.width = global_Math.round(jsMultiply(w, ratio))
                receiver108.canvas.height = global_Math.round(jsMultiply(h, ratio))
                receiver108.ctx.setTransform(ratio, 0, 0, ratio, 0, 0)
                receiver108.ratio29 = ratio
            }
            global_Object.assign(receiver108.canvas.style, jsObject { obj126 ->
                obj126["left"] = jsAdd(rect.left, "px")
                obj126["top"] = jsAdd(rect.top, "px")
                obj126["width"] = jsAdd(w, "px")
                obj126["height"] = jsAdd(h, "px")
            })
            receiver108.canvas.dataset.layer = (if (truth(dialog["open"])) "window" else "screen")
            receiver108.rect = jsObject { obj131 ->
                obj131["left"] = rect.left
                obj131["top"] = rect.top
                obj131["width"] = w
                obj131["height"] = h
            }
            receiver108.geometryKey34 = key
            return absent
        }
        val canvas: dynamic = global_document.createElement("canvas")
        canvas.className = "ambient-particles29"
        canvas.setAttribute("aria-hidden", "true")
        canvas.dataset.layer = (if (truth(dialog["open"])) "window" else "screen")
        global_Object.assign(canvas.style, jsObject { obj138 ->
            obj138["left"] = jsAdd(rect.left, "px")
            obj138["top"] = jsAdd(rect.top, "px")
            obj138["width"] = jsAdd(w, "px")
            obj138["height"] = jsAdd(h, "px")
        })
        canvas.width = global_Math.round(jsMultiply(w, ratio))
        canvas.height = global_Math.round(jsMultiply(h, ratio))
        target.append(canvas)
        val ctx: dynamic = canvas.getContext("2d")
        if (truth((!truth(ctx)))) {
            canvas.remove()
            return absent
        }
        ctx.setTransform(ratio, 0, 0, ratio, 0, 0)
        receiver108.canvas = canvas
        receiver108.ctx = ctx
        receiver108.ratio29 = ratio
        receiver108.rect = jsObject { obj151 ->
            obj151["left"] = rect.left
            obj151["top"] = rect.top
            obj151["width"] = w
            obj151["height"] = h
        }
        receiver108.geometryKey34 = key
        run {
            val target154: dynamic = receiver108
            val previous156: dynamic = numeric(target154.mounts)
            target154.mounts = jsAdd(previous156, 1)
        }
        receiver108.last = 0
        receiver108.entryAt = global_performance.now()
        global_FramePacer34.reset()
        global_Stars34.refresh()
        val count: dynamic = global_Math.min(68, global_Math.max(24, global_Math.round(jsDivide(jsMultiply(w, h), 11000))))
        receiver108.particles = global_Array.from(jsObject { obj163 ->
            obj163["length"] = count
        }, (fun(_: dynamic, i: dynamic): dynamic {
            return jsObject { obj164 ->
                obj164["x"] = jsMultiply(global_Math.random(), w)
                obj164["y"] = jsMultiply(global_Math.random(), h)
                obj164["vx"] = jsMultiply(jsSubtract(global_Math.random(), 0.5), 3)
                obj164["vy"] = jsSubtract((-numeric(6)), jsMultiply(global_Math.random(), 10))
                obj164["size"] = jsAdd(0.6, jsMultiply(global_Math.random(), 1.2))
                obj164["kind"] = global_Stars29.type(i)
                obj164["tint"] = (jsMod(i, 5) === 0)
                obj164["phase"] = jsMultiply(jsMultiply(global_Math.random(), global_Math.PI), 2)
                obj164["period"] = jsAdd(3, jsMultiply(global_Math.random(), 4))
            }
        }))
        tick = (fun(time: dynamic): dynamic {
            if (truth((receiver108.canvas !== canvas))) {
                return absent
            }
            if (truth((!truth(receiver108.allowed())))) {
                receiver108["suspend"]()
                global_Performance34.reset()
                return absent
            }
            if (truth(jsOr((!truth(canvas.isConnected))) { (canvas.parentElement !== (if (truth(global_Dollar("#modal")["open"])) global_Dollar("#modal") else global_document.body)) })) {
                receiver108.sync()
            }
            global_Performance34.sample(time)
            if (truth(global_FramePacer34.shouldDraw(time))) {
                val dt: dynamic = (if (truth(receiver108.last)) global_Math.min(0.08, jsDivide(jsSubtract(time, receiver108.last), 1000)) else jsDivide(1, 90))
                receiver108.last = time
                val r: dynamic = receiver108.rect
                ctx.clearRect(0, 0, r.width, r.height)
                run {
                    val target167: dynamic = receiver108
                    target167.flow = jsMultiply(target167.flow, global_Math.exp(jsMultiply((-numeric(dt)), 4.5)))
                }
                if (true) {
                    val iterator169: dynamic = iteratorOf(receiver108.particles)
                    while (true) {
                        val next170: dynamic = iterator169.next()
                        if (truth(next170.done)) break
                        var p: dynamic = absent
                        p = next170.value
                        p.x = jsMod(jsAdd(jsAdd(p.x, jsMultiply(p.vx, dt)), r.width), r.width)
                        p.y = jsMod(jsAdd(jsAdd(p.y, jsMultiply(jsSubtract(p.vy, receiver108.flow), dt)), r.height), r.height)
                        val wave: dynamic = jsAdd(0.5, jsMultiply(0.5, global_Math.sin(jsAdd(jsMultiply(jsMultiply(jsDivide(jsDivide(time, 1000), p.period), global_Math.PI), 2), p.phase))))
                        global_Stars34.draw(ctx, p, p.x, p.y, jsAdd(0.09, jsMultiply(wave, 0.13)))
                    }
                }
                ctx.globalAlpha = 1
                run {
                    val target177: dynamic = receiver108
                    val previous179: dynamic = numeric(target177.frames)
                    target177.frames = jsAdd(previous179, 1)
                }
                run {
                    val target180: dynamic = global_Performance34
                    val previous182: dynamic = numeric(target180.draws)
                    target180.draws = jsAdd(previous182, 1)
                }
            }
            receiver108.frame = global_requestAnimationFrame(tick)
            return absent
        })
        receiver108.frame = global_requestAnimationFrame(tick)
        return absent
    })
    global_applyTheme = (fun(arg189: dynamic): dynamic {
        val callArgs188: dynamic = js("arguments")
        var args: dynamic = absent
        args = argsAfter(callArgs188, 0)
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        run { val target191: dynamic = global_V34UI; invokeWith(target191.applyTheme, target191, jsArray { array190 ->
            appendAll(array190, args)
        }) }
        global_Stars34.refresh()
        return absent
    })
    global_shortPhrases34 = jsonValue("{\"knight\":[\"La constancia forja tu rango.\",\"Una serie. Un nuevo avance.\",\"Controla el movimiento y respira.\",\"Tu próxima misión te espera.\",\"Fuerza con técnica, paso a paso.\",\"Hoy también cuenta.\",\"Descansa entre tus series.\",\"Sigamos con tu siguiente paso.\"],\"warrior\":[\"Firme. Controla cada repetición.\",\"Tu técnica sostiene tu fuerza.\",\"Volvamos a la misión.\",\"Respira y prepara tu serie.\",\"Cada registro cuenta.\",\"Avanza con un ritmo controlado.\",\"La constancia construye fuerza.\",\"Una serie más en tu historia.\"],\"bear\":[\"Paso a paso, seguimos contigo.\",\"Respira. También puedes descansar.\",\"Recuperar forma parte del camino.\",\"Prepara tu siguiente movimiento.\",\"Cuida el ritmo de cada serie.\",\"Hoy avanzamos juntos.\",\"Descansa y vuelve con control.\",\"Tu constancia tiene valor.\"],\"insect\":[\"A tus órdenes. Comencemos.\",\"Cada misión cuenta, cazador.\",\"Un nuevo desafío te espera.\",\"Observa la técnica y respira.\",\"Hoy seguimos tu camino.\",\"La siguiente serie te espera.\",\"Completa tu paso con control.\",\"Tu avance merece constancia.\"],\"scientist\":[\"Tu historia avanza una página.\",\"Escribe hoy tu siguiente logro.\",\"Cada serie es un nuevo capítulo.\",\"Sigamos leyendo tu progreso.\",\"Tu próximo paso también cuenta.\",\"Una página más, con constancia.\",\"Respira antes del siguiente paso.\",\"Construye tu historia, día a día.\"]}")
    global_updatePhrase33 = (fun(): dynamic {
        val copy: dynamic = global_Dollar("#shadowPhrase33")
        if (truth((!truth(copy)))) {
            return absent
        }
        val type: dynamic = global_selectedCompanion()
        val list: dynamic = jsOr(global_shortPhrases34[type]) { global_shortPhrases34.knight }
        val index: dynamic = jsMod(jsAdd(jsOr(global_Number(copy.dataset.index34)) { 0 }, 1), list.length)
        copy.dataset.index34 = global_String(index)
        copy.textContent = list[index]
        val cloud: dynamic = copy.closest(".shadow-thought34")
        if (truth(jsAnd(cloud) { global_Particles28.allowed() })) {
            cloud.classList.remove("thought-pop34")
            global_requestAnimationFrame((fun(): dynamic {
                if (truth(cloud.isConnected)) {
                    cloud.classList.add("thought-pop34")
                }
                return absent
            }))
        }
        return absent
    })
    global_schedulePhrase33 = (fun(): dynamic {
        global_clearTimeout(global_phraseTimer33)
        if (truth(jsOr(jsOr((!truth(global_Dollar("#modal")["open"]))) { (!truth(global_Dollar("#shadowPhrase33"))) }) { (!truth(global_Particles28.allowed())) })) {
            return absent
        }
        global_phraseTimer33 = global_setTimeout((fun(): dynamic {
            global_updatePhrase33()
            global_schedulePhrase33()
            return absent
        }), jsAdd(30000, jsMultiply(global_Math.random(), 15000)))
        return absent
    })
    global_openCoach = (fun(): dynamic {
        global_V34UI.openCoach()
        val type: dynamic = global_selectedCompanion()
        val index: dynamic = global_dailyPhrase34(type)
        val old: dynamic = global_Dollar("#shadowPhrase33")
        if (truth(old)) {
            old.outerHTML = global_thoughtMarkup34(global_shortPhrases34[type][index])
            global_Dollar("#shadowPhrase33").dataset.index34 = global_String(index)
        }
        global_schedulePhrase33()
        global_ModalViewport.fit()
        global_UI26.schedule()
        return absent
    })
    global_mountPanelBefore34 = global_mountPanelCompanion
    global_mountPanelCompanion = (fun(type: dynamic): dynamic {
        global_mountPanelBefore34(type)
        if (truth(global_Dollar("#modal .shadow-thought34"))) {
            global_schedulePhrase33()
        }
        return absent
    })
    global_Transition32.play = (fun(nodes: dynamic): dynamic {
        val receiver218: dynamic = js("this")
        if (truth(jsOr(jsOr(jsOr((!truth(jsOr(global_Performance34.routeChanging) { global_Performance34.surfaceOpening }))) { (!truth(global_Back25.enabled())) }) { (!truth(global_document.documentElement.classList.contains("ready20"))) }) { global_document.hidden })) {
            return absent
        }
        if (true) {
            val iterator220: dynamic = iteratorOf(nodes)
            while (true) {
                val next221: dynamic = iterator220.next()
                if (truth(next221.done)) break
                var node: dynamic = absent
                node = next221.value
                if (truth(jsOr((!truth(node))) { (jsTypeOf(node.animate) !== "function") })) {
                    continue
                }
                val a: dynamic = node.animate(jsonValue("[{\"opacity\":0.995},{\"opacity\":1}]"), jsonValue("{\"duration\":150,\"easing\":\"ease-out\"}"))
                receiver218.active.add(a)
                a.finished["then"]((fun(): dynamic {
                    return receiver218.active.delete(a)
                }), (fun(): dynamic {
                    return receiver218.active.delete(a)
                }))
            }
        }
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        val oldHero: dynamic = global_Dollar("#app .hero-figure33")
        global_Performance34.routeChanging = jsOr((global_Performance34.lastTab !== global_tab)) { (global_Performance34.profileReady !== (!truth((!truth(global_state.profile))))) }
        global_V34UI.render()
        global_Performance34.lastTab = global_tab
        global_Performance34.profileReady = (!truth((!truth(global_state.profile))))
        global_Performance34.routeChanging = false
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target232: dynamic = n
                val assigned234: dynamic = "SISTEMA DE SOMBRAS"
                target232.textContent = assigned234
                assigned234
            }
        }))
        global_Hero34.preserve(oldHero)
        return absent
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_Performance34.surfaceOpening = true
        try {
            global_V34UI.modal(html)
        } finally {
            global_Performance34.surfaceOpening = false
        }
        return absent
    })
    global_completeBefore34 = global_Boot30.complete.bind(global_Boot30)
    global_Boot30.complete = (fun(): dynamic {
        return asyncAction asyncBody243@ {
            if (truth(global_state.profile)) {
                val s: dynamic = global_Visual33.selection()
                awaitValue(global_Promise.race(arrayOf<dynamic>(global_Hero34.decode(s.figure), jsConstruct(global_Promise, arrayOf<dynamic>((fun(resolve: dynamic): dynamic {
                    return global_setTimeout(resolve, 2000)
                }))))))
            }
            return@asyncBody243 global_completeBefore34()
            absent
        }
    })
    global_openDiagnostics20 = (fun(): dynamic {
        global_V34UI.openDiagnostics20()
        if (true) {
            val iterator248: dynamic = iteratorOf(global_Dollar("#modal").querySelectorAll("p"))
            while (true) {
                val next249: dynamic = iterator248.next()
                if (truth(next249.done)) break
                var p: dynamic = absent
                p = next249.value
                p.textContent = p.textContent.replace("Versión 33.0.", "Versión 34.0.")
            }
        }
        val observed: dynamic = global_Performance34.observed()
        onPresent(global_Dollar("#modal section")) { value252 -> value252.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<p>Objetivo de renderizado: 90 FPS. Medición reciente de callbacks en esta apertura: ", (if (truth((observed === null))) "todavía insuficiente" else jsAdd(observed, " por segundo"))), ". La frecuencia de pantalla y el ritmo de callbacks no verifican los fotogramas presentados por el dispositivo.</p>")) }
        return absent
    })
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        if (truth(global_document.hidden)) {
            global_Performance34.reset()
        }
        return absent
    }))
    global_window.HunterV34 = jsObject { obj255 ->
        obj255["FramePacer34"] = global_FramePacer34
        obj255["Performance34"] = global_Performance34
        obj255["Hero34"] = global_Hero34
        obj255["shortPhrases34"] = global_shortPhrases34
        obj255["dailyPhrase34"] = global_dailyPhrase34
        obj255["hdPaths34"] = global_hdPaths34
    }
    global_state.appVersion = 34
    global_save()
    global_render()
    global_Ambient29.sync()
}
