@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv36

import hunter.browser.*

@JsName("V36UI") private external var global_V36UI: dynamic
@JsName("Thought36") private external var global_Thought36: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("backModal24") private external var global_backModal24: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("changeMonth") private external var global_changeMonth: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("calendarYear") private external var global_calendarYear: dynamic
@JsName("calendarMonth") private external var global_calendarMonth: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("monthPanel") private external var global_monthPanel: dynamic
@JsName("modalButtons24") private external var global_modalButtons24: dynamic
@JsName("UI26") private external var global_UI26: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("openMonthlyCalendar") private external var global_openMonthlyCalendar: dynamic
@JsName("FramePacer34") private external var global_FramePacer34: dynamic
@JsName("Performance34") private external var global_Performance34: dynamic
@JsName("cancelAnimationFrame") private external var global_cancelAnimationFrame: dynamic
@JsName("ResizeObserver") private external var global_ResizeObserver: dynamic
@JsName("getComputedStyle") private external var global_getComputedStyle: dynamic
@JsName("parseFloat") private external var global_parseFloat: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV36() {
    global_V36UI = jsObject { obj1 ->
        obj1["openCoach"] = global_openCoach
        obj1["backModal24"] = global_backModal24
        obj1["modal"] = global_modal
        obj1["openDiagnostics20"] = global_openDiagnostics20
    }
    global_changeMonth = (fun(delta: dynamic): dynamic {
        if (truth((!truth(global_Number.isFinite(global_Number(delta)))))) {
            return absent
        }
        val date: dynamic = jsConstruct(global_Date, arrayOf<dynamic>(global_calendarYear, jsAdd(global_calendarMonth, global_Number(delta)), 1))
        global_calendarMonth = date.getMonth()
        global_calendarYear = date.getFullYear()
        val panel: dynamic = global_Dollar("#modal[open] .month-panel")
        if (truth(panel)) {
            val box: dynamic = global_document.createElement("template")
            box.innerHTML = global_monthPanel()
            global_document.querySelectorAll(".month-panel").forEach((fun(old: dynamic): dynamic {
                return old.replaceWith(box.content.firstElementChild.cloneNode(true))
            }))
            global_Dollar("#modal").scrollTop = 0
            global_modalButtons24()
            global_UI26.schedule()
            global_ModalViewport.fit()
            onPresent(global_Dollar("#modal .month-panel").querySelector((if (truth(jsLess(global_Number(delta), 0))) "[aria-label=\"Mes anterior\"]" else "[aria-label=\"Mes siguiente\"]"))) { value8 -> value8.focus(jsonValue("{\"preventScroll\":true}")) }
        } else {
            if (truth((global_tab === "plan"))) {
                global_render()
            } else {
                global_openMonthlyCalendar()
            }
        }
        return absent
    })
    global_FramePacer34.target = dynamicValue(null)
    global_FramePacer34.shouldDraw = (fun(): dynamic {
        return true
    })
    global_Performance34.target = dynamicValue(null)
    global_Thought36 = jsObject { obj15 ->
        obj15["current"] = dynamicValue(null)
        obj15["pending"] = 0
        obj15["heads"] = jsonValue("{\"knight\":[0.54,0.31],\"warrior\":[0.54,0.3],\"bear\":[0.63,0.34],\"insect\":[0.57,0.4],\"scientist\":[0.57,0.26]}")
        obj15["clear"] = fun(): dynamic {
            val receiver16: dynamic = js("this")
            onPresent(receiver16.current) { value18 -> onPresent(value18.observer) { value19 -> value19.disconnect() } }
            receiver16.current = dynamicValue(null)
            global_cancelAnimationFrame(receiver16.pending)
            receiver16.pending = 0
            return absent
        }
        obj15["attach"] = fun(): dynamic {
            val receiver24: dynamic = js("this")
            val host: dynamic = global_Dollar("#modal .shadow-showcase33")
            val actor: dynamic = onPresent(host) { value26 -> value26.querySelector("#animatedCompanion") }
            val img: dynamic = onPresent(actor) { value27 -> value27.querySelector("img") }
            val cloud: dynamic = onPresent(host) { value28 -> value28.querySelector(".shadow-thought34") }
            if (truth(jsOr(jsOr(jsOr((!truth(host))) { (!truth(actor)) }) { (!truth(img)) }) { (!truth(cloud)) })) {
                receiver24.clear()
                return absent
            }
            if (truth((onPresent(receiver24.current) { value29 -> value29.host } === host))) {
                receiver24.schedule()
                return absent
            }
            receiver24.clear()
            host.classList.add("thought-scene36")
            cloud.querySelectorAll(".thought-trail34").forEach((fun(n: dynamic): dynamic {
                return n.remove()
            }))
            host.querySelectorAll(".head-trail36").forEach((fun(n: dynamic): dynamic {
                return n.remove()
            }))
            val trail: dynamic = global_document.createElement("div")
            trail.className = "head-trail36"
            trail.setAttribute("aria-hidden", "true")
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration32 = true
                while (true) {
                    if (!firstIteration32) {
                        run {
                            val previous33: dynamic = numeric(i)
                            i = jsAdd(previous33, 1)
                        }
                    }
                    firstIteration32 = false
                    if (!truth(jsLess(i, 3))) break
                    val dot: dynamic = global_document.createElement("i")
                    dot.style.setProperty("--dot36", i)
                    trail.append(dot)
                }
            }
            host.append(trail)
            val entry: dynamic = jsObject { obj34 ->
                obj34["host"] = host
                obj34["actor"] = actor
                obj34["img"] = img
                obj34["cloud"] = cloud
                obj34["trail"] = trail
                obj34["observer"] = dynamicValue(null)
            }
            receiver24.current = entry
            if (truth((js("typeof ResizeObserver") === "function"))) {
                entry.observer = jsConstruct(global_ResizeObserver, arrayOf<dynamic>((fun(): dynamic {
                    return receiver24.schedule()
                })))
                entry.observer.observe(host)
                entry.observer.observe(actor)
                entry.observer.observe(cloud)
            }
            img.addEventListener("load", (fun(): dynamic {
                if (truth((receiver24.current === entry))) {
                    receiver24.schedule()
                }
                return absent
            }), jsonValue("{\"once\":true}"))
            receiver24.layout()
            return absent
        }
        obj15["layout"] = fun(): dynamic {
            val receiver39: dynamic = js("this")
            val e: dynamic = receiver39.current
            if (truth((!truth(onPresent(e) { value41 -> value41.host.isConnected })))) {
                return receiver39.clear()
            }
            val h: dynamic = e.host.getBoundingClientRect()
            val a: dynamic = e.actor.getBoundingClientRect()
            val c: dynamic = e.cloud.getBoundingClientRect()
            val style: dynamic = global_getComputedStyle(e.img)
            val width: dynamic = jsOr(global_parseFloat(style.width)) { a.width }
            val height: dynamic = jsOr(global_parseFloat(style.height)) { a.height }
            val nw: dynamic = jsOr(e.img.naturalWidth) { 512 }
            val nh: dynamic = jsOr(e.img.naturalHeight) { 768 }
            val scale: dynamic = global_Math.min(jsDivide(width, nw), jsDivide(height, nh))
            val offset: dynamic = jsOr(global_parseFloat(style.left)) { 0 }
            val point: dynamic = jsOr(receiver39.heads[global_selectedCompanion()]) { receiver39.heads.knight }
            val start: dynamic = jsObject { obj43 ->
                obj43["x"] = jsAdd(jsAdd(jsAdd(jsSubtract(a.left, h.left), offset), jsDivide(jsSubtract(width, jsMultiply(nw, scale)), 2)), jsMultiply(jsMultiply(point[0], nw), scale))
                obj43["y"] = jsAdd(jsAdd(jsSubtract(a.top, h.top), jsDivide(jsSubtract(height, jsMultiply(nh, scale)), 2)), jsMultiply(jsMultiply(point[1], nh), scale))
            }
            val end: dynamic = jsObject { obj44 ->
                obj44["x"] = jsAdd(jsSubtract(c.left, h.left), 7)
                obj44["y"] = jsAdd(jsSubtract(c.top, h.top), jsMultiply(c.height, 0.54))
            }
            e.host.style.setProperty("--head-x36", jsAdd(start.x, "px"))
            e.host.style.setProperty("--head-y36", jsAdd(start.y, "px"))
            e.trail.querySelectorAll("i").forEach((fun(dot: dynamic, i: dynamic): dynamic {
                val t: dynamic = dynamicValue(jsonValue("[0.2,0.47,0.75]"))[i]
                dot.style.left = jsAdd(jsAdd(start.x, jsMultiply(jsSubtract(end.x, start.x), t)), "px")
                dot.style.top = jsAdd(jsAdd(start.y, jsMultiply(jsSubtract(end.y, start.y), t)), "px")
                return absent
            }))
            e.host.dataset.headLinked36 = "true"
            return absent
        }
        obj15["schedule"] = fun(): dynamic {
            val receiver51: dynamic = js("this")
            if (truth(receiver51.pending)) {
                return absent
            }
            receiver51.pending = global_requestAnimationFrame((fun(): dynamic {
                receiver51.pending = 0
                receiver51.layout()
                return absent
            }))
            return absent
        }
    }
    global_openCoach = (fun(): dynamic {
        global_V36UI.openCoach()
        global_Thought36.attach()
        return absent
    })
    global_backModal24 = (fun(): dynamic {
        global_V36UI.backModal24()
        global_Thought36.attach()
        return absent
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_Thought36.clear()
        global_V36UI.modal(html)
        return absent
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth((!truth(global_Dollar("#modal")["open"])))) {
            global_Thought36.clear()
        }
        return absent
    }))
    global_window.addEventListener("resize", (fun(): dynamic {
        return global_Thought36.schedule()
    }), jsonValue("{\"passive\":true}"))
    global_openDiagnostics20 = (fun(): dynamic {
        global_V36UI.openDiagnostics20()
        global_Dollar("#modal").querySelectorAll("p").forEach((fun(n: dynamic): dynamic {
            n.textContent = n.textContent.replace("Versión 35.0.", "Versión 36.0.").replace(jsRegExp("Objetivo de renderizado: 90 FPS\\.", ""), "Renderizado sincronizado con la pantalla, sin límite interno de FPS.")
            return absent
        }))
        return absent
    })
    global_window.HunterV36 = jsObject { obj69 ->
        obj69["Thought36"] = global_Thought36
        obj69["version"] = 36
    }
    global_state.appVersion = 36
    global_save()
}
