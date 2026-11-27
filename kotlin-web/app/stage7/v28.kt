@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv28

import hunter.browser.*

@JsName("V28UI") private external var global_V28UI: dynamic
@JsName("Evolution28") private external var global_Evolution28: dynamic
@JsName("Particles28") private external var global_Particles28: dynamic
@JsName("closeBefore28") private external var global_closeBefore28: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("backModal24") private external var global_backModal24: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Infinity") private external var global_Infinity: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("cancelAnimationFrame") private external var global_cancelAnimationFrame: dynamic
@JsName("getComputedStyle") private external var global_getComputedStyle: dynamic
@JsName("performance") private external var global_performance: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("Nav24") private external var global_Nav24: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV28() {
    global_V28UI = jsObject { obj1 ->
        obj1["applyTheme"] = global_applyTheme
        obj1["home"] = global_home
        obj1["render"] = global_render
        obj1["modal"] = global_modal
        obj1["backModal24"] = global_backModal24
        obj1["profileForm"] = global_profileForm
    }
    global_Evolution28 = jsObject { obj2 ->
        obj2["stages"] = global_Object.freeze(arrayOf<dynamic>(jsonValue("{\"key\":\"awakening\",\"name\":\"Despertar\",\"min\":1,\"max\":10}"), jsonValue("{\"key\":\"rising\",\"name\":\"Ascenso\",\"min\":11,\"max\":20}"), jsonValue("{\"key\":\"elite\",\"name\":\"Élite\",\"min\":21,\"max\":35}"), jsObject { obj3 ->
            obj3["key"] = "monarch"
            obj3["name"] = "Monarca"
            obj3["min"] = 36
            obj3["max"] = global_Infinity
        }))
        obj2["selection"] = fun(): dynamic {
            val receiver4: dynamic = js("this")
            return jsObject { obj6 ->
                obj6["theme"] = jsOr(global_document.documentElement.dataset.theme) { "blue" }
                obj6["gender"] = "default"
                obj6["row"] = 0
                obj6["column"] = 0
                obj6["level"] = 1
                obj6["stage"] = receiver4.stages[0]
                obj6["asset"] = jsonValue("{\"path\":\"ui33/scene-e-0.webp\",\"width\":512,\"height\":512,\"columns\":1,\"rows\":1}")
            }
        }
        obj2["crop"] = fun(width: dynamic, height: dynamic, selection: dynamic): dynamic {
            val a: dynamic = selection.asset
            val cw: dynamic = jsDivide(a.width, a.columns)
            val ch: dynamic = jsDivide(a.height, a.rows)
            val inset: dynamic = 1.25
            val iw: dynamic = jsSubtract(cw, jsMultiply(inset, 2))
            val ih: dynamic = jsSubtract(ch, jsMultiply(inset, 2))
            val scale: dynamic = global_Math.max(jsDivide(width, iw), jsDivide(height, ih))
            val x: dynamic = jsAdd(jsMultiply(jsAdd(jsMultiply(selection.column, cw), inset), scale), jsMultiply(jsSubtract(jsMultiply(iw, scale), width), 0.6))
            val y: dynamic = jsAdd(jsMultiply(jsAdd(jsMultiply(selection.row, ch), inset), scale), jsMultiply(jsSubtract(jsMultiply(ih, scale), height), 0.12))
            return jsObject { obj9 ->
                obj9["size"] = jsAdd(jsAdd(jsAdd(jsMultiply(a.width, scale), "px "), jsMultiply(a.height, scale)), "px")
                obj9["position"] = jsAdd(jsAdd(jsAdd((-numeric(x)), "px "), (-numeric(y))), "px")
            }
        }
        obj2["current"] = dynamicValue(null)
        obj2["layout"] = fun(arg12: dynamic): dynamic {
            val receiver10: dynamic = js("this")
            var selection: dynamic = absent
            selection = definedOr(arg12) { jsOr(receiver10.current) { receiver10.selection() } }
            receiver10.current = selection
            val root: dynamic = global_document.documentElement
            val body: dynamic = receiver10.crop(global_window.innerWidth, global_window.innerHeight, selection)
            val `set`: dynamic = (fun(style: dynamic, key: dynamic, `value`: dynamic): dynamic {
                if (truth((style.getPropertyValue(key) !== `value`))) {
                    style.setProperty(key, `value`)
                }
                return absent
            })
            `set`(root.style, "--scene28", jsAdd(jsAdd("url(\"", selection.asset.path), "\")"))
            `set`(root.style, "--scene-body-size28", body.size)
            `set`(root.style, "--scene-body-position28", body.position)
            val arts: dynamic = dynamicValue(jsArray { array16 ->
                appendAll(array16, global_document.querySelectorAll(".evolution-art28"))
            }).map((fun(art: dynamic): dynamic {
                return jsObject { obj17 ->
                    obj17["art"] = art
                    obj17["rect"] = art.getBoundingClientRect()
                }
            }))
            val d: dynamic = global_Dollar("#modal")
            val dialogRect: dynamic = (if (truth(d["open"])) d.getBoundingClientRect() else null)
            if (true) {
                val iterator18: dynamic = iteratorOf(arts)
                while (true) {
                    val next19: dynamic = iterator18.next()
                    if (truth(next19.done)) break
                    var art: dynamic = absent
                    var r: dynamic = absent
                    val unpacked20: dynamic = next19.value
                    art = unpacked20["art"]
                    r = unpacked20["rect"]
                    if (truth(jsOr((!truth(r.width))) { (!truth(r.height)) })) {
                        continue
                    }
                    val crop: dynamic = receiver10.crop(r.width, r.height, selection)
                    if (truth((art.style.backgroundSize !== crop.size))) {
                        art.style.backgroundSize = crop.size
                    }
                    if (truth((art.style.backgroundPosition !== crop.position))) {
                        art.style.backgroundPosition = crop.position
                    }
                    if (true) {
                        val iterator25: dynamic = iteratorOf(global_Object.entries(jsObject { obj27 ->
                            obj27["theme"] = selection.theme
                            obj27["gender"] = selection.gender
                            obj27["stage"] = selection.stage.key
                            obj27["level"] = global_String(selection.level)
                            obj27["column"] = global_String(selection.column)
                            obj27["row"] = global_String(selection.row)
                        }))
                        while (true) {
                            val next26: dynamic = iterator25.next()
                            if (truth(next26.done)) break
                            var key: dynamic = absent
                            var `value`: dynamic = absent
                            val unpacked28: dynamic = fromIterable(next26.value)
                            key = unpacked28[0]
                            `value` = unpacked28[1]
                            if (truth((art.dataset[key] !== `value`))) {
                                art.dataset[key] = `value`
                            }
                        }
                    }
                }
            }
            if (truth(dialogRect)) {
                val crop: dynamic = receiver10.crop(dialogRect.width, dialogRect.height, selection)
                `set`(d.style, "--scene-window-size28", crop.size)
                `set`(d.style, "--scene-window-position28", crop.position)
            }
            return absent
        }
        obj2["pending"] = 0
        obj2["schedule"] = fun(): dynamic {
            val receiver31: dynamic = js("this")
            if (truth(receiver31.pending)) {
                return absent
            }
            receiver31.pending = global_requestAnimationFrame((fun(): dynamic {
                receiver31.pending = 0
                receiver31.layout()
                return absent
            }))
            return absent
        }
    }
    global_Particles28 = jsObject { obj37 ->
        obj37["canvas"] = dynamicValue(null)
        obj37["frame"] = 0
        obj37["particles"] = js("[]")
        obj37["started"] = 0
        obj37["duration"] = 800
        obj37["lastKind"] = dynamicValue(null)
        obj37["bursts"] = 0
        obj37["allowed"] = fun(): dynamic {
            return jsAnd(jsAnd(jsAnd((onPresent(global_state.profile) { value40 -> value40.animate } !== false)) { (!truth(global_document.documentElement.classList.contains("still"))) }) { (!truth(global_matchMedia("(prefers-reduced-motion: reduce)").matches)) }) { (!truth(global_document.hidden)) }
        }
        obj37["stop"] = fun(): dynamic {
            val receiver41: dynamic = js("this")
            if (truth(receiver41.frame)) {
                global_cancelAnimationFrame(receiver41.frame)
            }
            receiver41.frame = 0
            onPresent(receiver41.canvas) { value45 -> value45.remove() }
            receiver41.canvas = dynamicValue(null)
            receiver41.particles = js("[]")
            return absent
        }
        obj37["burst"] = fun(kind: dynamic, rect: dynamic, target: dynamic): dynamic {
            val receiver50: dynamic = js("this")
            var tick: dynamic = absent
            receiver50.stop()
            if (truth(jsOr(jsOr((!truth(receiver50.allowed()))) { jsLess(rect.width, 1) }) { jsLess(rect.height, 1) })) {
                return false
            }
            val inset: dynamic = 2
            val w: dynamic = global_Math.max(1, jsSubtract(rect.width, jsMultiply(inset, 2)))
            val h: dynamic = global_Math.max(1, jsSubtract(rect.height, jsMultiply(inset, 2)))
            val ratio: dynamic = global_Math.min(2, jsOr(global_window.devicePixelRatio) { 1 })
            val c: dynamic = global_document.createElement("canvas")
            c.className = "window-particles28"
            c.setAttribute("aria-hidden", "true")
            c.dataset.effect = kind
            c.style.left = jsAdd(jsAdd(rect.left, inset), "px")
            c.style.top = jsAdd(jsAdd(rect.top, inset), "px")
            c.style.width = jsAdd(w, "px")
            c.style.height = jsAdd(h, "px")
            c.width = global_Math.round(jsMultiply(w, ratio))
            c.height = global_Math.round(jsMultiply(h, ratio))
            dynamicValue(jsOr(target) { global_document.body }).append(c)
            val context: dynamic = c.getContext("2d")
            if (truth((!truth(context)))) {
                c.remove()
                return false
            }
            context.scale(ratio, ratio)
            val probe: dynamic = global_document.createElement("span")
            probe.style.color = "var(--accent)"
            probe.style.display = "none"
            global_document.body.append(probe)
            val color: dynamic = global_getComputedStyle(probe).color
            probe.remove()
            val count: dynamic = global_Math.min(36, global_Math.max(18, global_Math.round(jsDivide(jsAdd(w, h), 40))))
            receiver50.canvas = c
            receiver50.lastKind = kind
            run {
                val target76: dynamic = receiver50
                val previous78: dynamic = numeric(target76.bursts)
                target76.bursts = jsAdd(previous78, 1)
            }
            receiver50.started = global_performance.now()
            receiver50.particles = global_Array.from(jsObject { obj83 ->
                obj83["length"] = count
            }, (fun(_: dynamic, i: dynamic): dynamic {
                val edge: dynamic = jsMod(i, 4)
                val t: dynamic = jsAdd(0.12, jsMultiply(global_Math.random(), 0.76))
                val x: dynamic = (if (truth((edge === 0))) jsMultiply(t, w) else (if (truth((edge === 1))) jsSubtract(w, 6) else (if (truth((edge === 2))) jsMultiply(t, w) else 6)))
                val y: dynamic = (if (truth((edge === 0))) 6 else (if (truth((edge === 1))) jsMultiply(t, h) else (if (truth((edge === 2))) jsSubtract(h, 6) else jsMultiply(t, h))))
                val closing: dynamic = jsOr((kind === "close")) { jsAnd((kind === "back")) { (jsMod(i, 2) === 0) } }
                val dx: dynamic = jsDivide(jsSubtract(jsDivide(w, 2), x), jsOr(jsDivide(w, 2)) { 1 })
                val dy: dynamic = jsDivide(jsSubtract(jsDivide(h, 2), y), jsOr(jsDivide(h, 2)) { 1 })
                return jsObject { obj84 ->
                    obj84["x"] = x
                    obj84["y"] = y
                    obj84["vx"] = jsAdd(jsMultiply(dx, (if (truth(closing)) (-numeric(9)) else 18)), jsMultiply(jsSubtract(global_Math.random(), 0.5), 10))
                    obj84["vy"] = jsSubtract(jsSubtract(jsMultiply(dy, (if (truth(closing)) (-numeric(9)) else 18)), 10), jsMultiply(global_Math.random(), 10))
                    obj84["size"] = jsAdd(0.8, jsMultiply(global_Math.random(), 1.6))
                    obj84["rune"] = (jsMod(i, 7) === 0)
                    obj84["delay"] = jsMultiply(global_Math.random(), 100)
                }
            }))
            tick = (fun(time: dynamic): dynamic {
                if (truth(jsOr((receiver50.canvas !== c)) { (!truth(receiver50.allowed())) })) {
                    receiver50.stop()
                    return absent
                }
                val elapsed: dynamic = jsSubtract(time, receiver50.started)
                context.clearRect(0, 0, w, h)
                context.fillStyle = color
                context.strokeStyle = color
                if (true) {
                    val iterator89: dynamic = iteratorOf(receiver50.particles)
                    while (true) {
                        val next90: dynamic = iterator89.next()
                        if (truth(next90.done)) break
                        var p: dynamic = absent
                        p = next90.value
                        val life: dynamic = global_Math.max(0, jsDivide(jsSubtract(elapsed, p.delay), receiver50.duration))
                        if (truth(jsOr(jsAtLeast(life, 1)) { jsLess(elapsed, p.delay) })) {
                            continue
                        }
                        val alpha: dynamic = jsMultiply(global_Math.sin(jsMultiply(global_Math.PI, life)), 0.42)
                        val x: dynamic = jsAdd(p.x, jsMultiply(p.vx, life))
                        val y: dynamic = jsAdd(p.y, jsMultiply(p.vy, life))
                        context.globalAlpha = alpha
                        if (truth(p.rune)) {
                            val size: dynamic = jsMultiply(p.size, 2.2)
                            context.lineWidth = 0.8
                            context.beginPath()
                            context.moveTo(x, jsSubtract(y, size))
                            context.lineTo(jsAdd(x, size), y)
                            context.lineTo(x, jsAdd(y, size))
                            context.lineTo(jsSubtract(x, size), y)
                            context.closePath()
                            context.stroke()
                        } else {
                            context.beginPath()
                            context.arc(x, y, p.size, 0, jsMultiply(global_Math.PI, 2))
                            context.fill()
                        }
                    }
                }
                context.globalAlpha = 1
                if (truth(jsLess(elapsed, jsAdd(receiver50.duration, 100)))) {
                    receiver50.frame = global_requestAnimationFrame(tick)
                } else {
                    receiver50.stop()
                }
                return absent
            })
            receiver50.frame = global_requestAnimationFrame(tick)
            return true
        }
    }
    global_applyTheme = (fun(arg103: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg103) { jsOr(global_state.profile) { js("({})") } }
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V28UI.applyTheme(p)
        global_Evolution28.layout(global_Evolution28.selection(p))
        if (truth((!truth(global_Particles28.allowed())))) {
            global_Particles28.stop()
        }
        return absent
    })
    global_home = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V28UI.home()
        val art: dynamic = box.querySelector(".hero-art26")
        val s: dynamic = global_Evolution28.selection()
        if (truth(art)) {
            art.classList.add("evolution-art28")
            onPresent(art.querySelector("img")) { value108 -> value108.remove() }
            art.dataset.theme = s.theme
            art.dataset.gender = s.gender
            art.dataset.stage = s.stage.key
            art.dataset.level = global_String(s.level)
        }
        val level: dynamic = box.querySelector(".hero-footer strong")
        if (truth(level)) {
            level.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<span class=\"scene-stage28\">", s.stage.name.toUpperCase()), "</span>"))
        }
        return box.innerHTML
    })
    global_profileForm = (fun(arg119: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg119) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V28UI.profileForm(initial)
        val label: dynamic = onPresent(box.querySelector("[name=gender]")) { value122 -> value122.closest("label") }
        onPresent(label) { value123 -> onPresent(value123.closest(".fields")) { value124 -> value124.insertAdjacentHTML("afterend", "<p class=\"evolution-note28\">La ilustración acompaña tu nivel: Despertar (1–10), Ascenso (11–20), Élite (21–35) y Monarca (36+). El género y el tema cambian su apariencia.</p>") } }
        return box.innerHTML
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_Particles28.stop()
        global_V28UI.modal(html)
        global_Evolution28.layout()
        val d: dynamic = global_Dollar("#modal")
        if (truth(d["open"])) {
            global_Particles28.burst("open", d.getBoundingClientRect(), d)
        }
        global_Evolution28.schedule()
        return absent
    })
    global_closeBefore28 = global_Dollar("#modal").close
    global_Dollar("#modal").close = (fun(`value`: dynamic): dynamic {
        val receiver129: dynamic = js("this")
        if (truth((!truth(receiver129["open"])))) {
            return global_closeBefore28.call(receiver129, `value`)
        }
        val rect: dynamic = receiver129.getBoundingClientRect()
        global_Particles28.stop()
        val result: dynamic = global_closeBefore28.call(receiver129, `value`)
        if (truth((!truth(receiver129["open"])))) {
            global_Particles28.burst("close", rect, global_document.body)
        }
        return result
    })
    global_backModal24 = (fun(): dynamic {
        val count: dynamic = global_Nav24.stack.length
        global_Particles28.stop()
        global_V28UI.backModal24()
        val d: dynamic = global_Dollar("#modal")
        global_Evolution28.layout()
        if (truth(jsAnd(d["open"]) { jsLess(global_Nav24.stack.length, count) })) {
            global_Particles28.burst("back", d.getBoundingClientRect(), d)
        }
        global_Evolution28.schedule()
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V28UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target135: dynamic = n
                val assigned137: dynamic = "SISTEMA DE SOMBRAS"
                target135.textContent = assigned137
                assigned137
            }
        }))
        global_Evolution28.layout(global_Evolution28.selection())
        global_Evolution28.schedule()
        return absent
    })
    global_window.addEventListener("resize", (fun(): dynamic {
        global_Particles28.stop()
        global_Evolution28.schedule()
        return absent
    }), jsonValue("{\"passive\":true}"))
    onPresent(global_window.visualViewport) { value138 -> value138.addEventListener("resize", (fun(): dynamic {
        return global_Evolution28.schedule()
    }), jsonValue("{\"passive\":true}")) }
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        if (truth(global_document.hidden)) {
            global_Particles28.stop()
        }
        return absent
    }))
    run { val owner140: dynamic = global_matchMedia("(prefers-reduced-motion: reduce)"); onPresent(owner140.addEventListener) { method139 -> invokeWith(method139, owner140, arrayOf<dynamic>("change", (fun(e: dynamic): dynamic {
        if (truth(e.matches)) {
            global_Particles28.stop()
        }
        return absent
    }))) } }
    global_window.HunterV28 = jsObject { obj143 ->
        obj143["Evolution28"] = global_Evolution28
        obj143["Particles28"] = global_Particles28
    }
    global_state.appVersion = 28
    global_applyTheme()
    global_save()
    global_render()
}
