@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterdemocoverage3862

import hunter.browser.*

@JsName("technique") private external var global_technique: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("fetch") private external var global_fetch: dynamic
@JsName("DOMParser") private external var global_DOMParser: dynamic
@JsName("parseFloat") private external var global_parseFloat: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterDemoCoverage3862() {
    dynamicValue((fun(root: dynamic, document: dynamic): dynamic {
        var selectElement: dynamic = absent
        var reduced: dynamic = absent
        var active: dynamic = absent
        var counters: dynamic = absent
        var stop: dynamic = absent
        var seek: dynamic = absent
        var tick: dynamic = absent
        var play: dynamic = absent
        var validPath: dynamic = absent
        var load: dynamic = absent
        var addOption: dynamic = absent
        stop = fun(session: dynamic): dynamic {
            if (truth((!truth(session)))) {
                return absent
            }
            session.playing = false
            if (truth(session.raf)) {
                root.cancelAnimationFrame(session.raf)
            }
            session.raf = 0
            if (truth(onPresent(session.svg) { value13 -> value13.pauseAnimations })) {
                session.svg.pauseAnimations()
            }
            if (truth((active === session))) {
                active = dynamicValue(null)
            }
            return absent
        }
        seek = fun(session: dynamic, next: dynamic): dynamic {
            if (truth((!truth(onPresent(session) { value16 -> value16.svg })))) {
                return absent
            }
            session.time = global_Math.min(session.duration, global_Math.max(0, jsOr(global_Number(next)) { 0 }))
            run { val owner20: dynamic = session.svg; onPresent(owner20.setCurrentTime) { method19 -> invokeWith(method19, owner20, arrayOf<dynamic>((if (truth((session.time === session.duration))) jsSubtract(session.duration, 0.001) else session.time))) } }
            if (truth(session.range)) {
                session.range["value"] = global_String(global_Math.round(jsMultiply(jsDivide(session.time, session.duration), 1000)))
            }
            if (truth(session.position)) {
                session.position.textContent = jsAdd(global_Math.round(jsMultiply(jsDivide(session.time, session.duration), 100)), " %")
            }
            return absent
        }
        tick = fun(session: dynamic, now: dynamic): dynamic {
            if (truth(jsOr(jsOr(jsOr((!truth(session.playing))) { (active !== session) }) { (!truth(session.host.isConnected)) }) { document.hidden })) {
                stop(session)
                return absent
            }
            if (truth(session.last)) {
                val delta: dynamic = global_Math.min(0.12, global_Math.max(0, jsDivide(jsSubtract(now, session.last), 1000)))
                seek(session, jsMod(jsAdd(session.time, jsMultiply(delta, session.rate)), session.duration))
            }
            session.last = now
            session.raf = root.requestAnimationFrame((fun(time: dynamic): dynamic {
                return tick(session, time)
            }))
            return absent
        }
        play = fun(session: dynamic, button: dynamic): dynamic {
            if (truth((!truth(onPresent(session) { value33 -> value33.svg })))) {
                return absent
            }
            if (truth(session.playing)) {
                stop(session)
                button.textContent = "Reproducir"
                return absent
            }
            if (truth(jsAnd(active) { (active !== session) })) {
                stop(active)
            }
            active = session
            session.playing = true
            session.last = 0
            button.textContent = "Pausar"
            session.raf = root.requestAnimationFrame((fun(now: dynamic): dynamic {
                return tick(session, now)
            }))
            return absent
        }
        validPath = fun(ex: dynamic): dynamic {
            val id: dynamic = onPresent(ex) { value46 -> value46.id }
            if (truth((!truth(dynamicValue(jsRegExp("^[A-Za-z0-9_-]{1,90}\$", "")).test(jsOr(id) { "" }))))) {
                return null
            }
            val path: dynamic = jsOr(ex.demoPath30) { jsAdd(jsAdd("demos30/", id), ".svg") }
            return (if (truth(dynamicValue(jsRegExp("^demos30\\/[A-Za-z0-9_-]{1,90}\\.svg\$", "")).test(path))) path else null)
        }
        load = fun(session: dynamic, ex: dynamic): dynamic {
            return asyncAction asyncBody47@ {
                if (truth(jsOr(session.started) { (!truth(session.host.isConnected)) })) {
                    return@asyncBody47 absent
                }
                session.started = true
                val status: dynamic = selectElement(session.host, ".svg-status3862")
                status.textContent = "Cargando animación local…"
                try {
                    val path: dynamic = validPath(ex)
                    if (truth((!truth(path)))) {
                        throw global_Error("Ruta no válida")
                    }
                    val response: dynamic = awaitValue(global_fetch(path))
                    if (truth((!truth(response.ok)))) {
                        throw global_Error("No se encontró el SVG")
                    }
                    val source: dynamic = awaitValue(response.text())
                    val parser: dynamic = jsConstruct(global_DOMParser, js("[]")).parseFromString(source, "image/svg+xml")
                    if (truth(jsOr(parser.querySelector("parsererror")) { (parser.documentElement.localName !== "svg") })) {
                        throw global_Error("El archivo SVG no tiene una estructura válida")
                    }
                    val svg: dynamic = document.importNode(parser.documentElement, true)
                    svg.querySelectorAll("script,foreignObject").forEach((fun(node: dynamic): dynamic {
                        return node.remove()
                    }))
                    dynamicValue(jsArray { array55 ->
                        array55.push(svg)
                        appendAll(array55, svg.querySelectorAll("*"))
                    }).forEach((fun(node: dynamic): dynamic {
                        if (true) {
                            val iterator56: dynamic = iteratorOf(jsArray { array58 ->
                                appendAll(array58, node.attributes)
                            })
                            while (true) {
                                val next57: dynamic = iterator56.next()
                                if (truth(next57.done)) break
                                var attr: dynamic = absent
                                attr = next57.value
                                if (truth(jsOr(dynamicValue(jsRegExp("^on", "i")).test(attr.name)) { jsAnd(dynamicValue(jsRegExp("(?:href|src)\$", "i")).test(attr.name)) { dynamicValue(jsRegExp("^(?:https?:|javascript:|data:)", "i")).test(attr["value"]) } })) {
                                    node.removeAttribute(attr.name)
                                }
                            }
                        }
                        return absent
                    }))
                    svg.setAttribute("role", "img")
                    svg.setAttribute("aria-label", jsAdd("Demostración animada de ", ex.name))
                    val holder: dynamic = selectElement(session.host, ".svg-view3862")
                    holder.replaceChildren(svg)
                    run { val owner60: dynamic = svg; onPresent(owner60.pauseAnimations) { method59 -> invokeWith(method59, owner60, js("[]")) } }
                    session.svg = svg
                    val durText: dynamic = jsOr(onPresent(svg.querySelector("animate,animateTransform,animateMotion")) { value63 -> value63.getAttribute("dur") }) { "4s" }
                    val seconds: dynamic = jsOr(global_parseFloat(durText)) { 4 }
                    session.duration = (if (truth(dynamicValue(jsRegExp("ms\$", "")).test(durText))) jsDivide(seconds, 1000) else seconds)
                    session.duration = global_Math.max(0.1, global_Math.min(120, session.duration))
                    session.range = selectElement(session.host, ".svg-seek3862")
                    session.position = selectElement(session.host, ".svg-position3862")
                    val btn: dynamic = selectElement(session.host, ".svg-play3862")
                    btn.disabled = false
                    session.range.disabled = false
                    seek(session, 0)
                    btn.addEventListener("click", (fun(): dynamic {
                        return play(session, btn)
                    }))
                    session.range.addEventListener("input", (fun(): dynamic {
                        val playing: dynamic = session.playing
                        if (truth(playing)) {
                            stop(session)
                            btn.textContent = "Reproducir"
                        }
                        seek(session, jsDivide(jsMultiply(global_Number(session.range["value"]), session.duration), 1000))
                        return absent
                    }))
                    selectElement(session.host, ".svg-speed3862").addEventListener("change", (fun(event: dynamic): dynamic {
                        session.rate = global_Math.max(0.5, global_Math.min(1.5, jsOr(global_Number(event.target["value"])) { 1 }))
                        return absent
                    }))
                    status.textContent = "Demostración local lista · reproducción controlada"
                    run {
                        val target82: dynamic = counters
                        val previous84: dynamic = numeric(target82.loaded)
                        target82.loaded = jsAdd(previous84, 1)
                    }
                    if (truth(jsAnd(jsAnd((!truth(reduced()))) { session.host["open"] }) { session.host.isConnected })) {
                        play(session, btn)
                    }
                } catch (error: dynamic) {
                    run {
                        val target85: dynamic = counters
                        val previous87: dynamic = numeric(target85.failures)
                        target85.failures = jsAdd(previous87, 1)
                    }
                    status.textContent = "No se pudo mostrar esta animación. Conserva las instrucciones de la ficha."
                    session.host.dataset.error = global_String(jsOr(onPresent(error) { value92 -> value92.message }) { "desconocido" })
                }
                absent
            }
        }
        addOption = fun(id: dynamic): dynamic {
            val modal: dynamic = document.querySelector("#modal")
            val ex: dynamic = onPresent(root.Engine) { value95 -> run { val owner97: dynamic = value95; onPresent(owner97.byId) { method96 -> invokeWith(method96, owner97, arrayOf<dynamic>(id)) } } }
            if (truth(jsOr(jsOr((!truth(onPresent(modal) { value98 -> value98["open"] }))) { (!truth(ex)) }) { (!truth(validPath(ex))) })) {
                return absent
            }
            val panel: dynamic = selectElement(modal, ".motion-card18")
            val fallback: dynamic = document.createElement("details")
            fallback.className = "svg-alt3862 card"
            fallback.dataset.exercise = id
            fallback.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<summary>Ver animación SVG original · sin conexión</summary>", "<div class=\"svg-view3862\" aria-live=\"off\"></div>"), "<p class=\"svg-status3862\" role=\"status\">El SVG se abrirá únicamente cuando lo solicites.</p>"), "<label>Posición de la animación <input class=\"svg-seek3862\" type=\"range\" min=\"0\" max=\"1000\" value=\"0\" disabled></label>"), "<div class=\"svg-buttons3862\"><button type=\"button\" class=\"btn primary svg-play3862\" disabled>Reproducir</button>"), "<label>Velocidad <select class=\"svg-speed3862\"><option value=\"0.5\">Lenta</option><option value=\"1\" selected>Normal</option><option value=\"1.5\">Rápida</option></select></label>"), "<output class=\"svg-position3862\">0 %</output></div>")
            val session: dynamic = jsObject { obj105 ->
                obj105["host"] = fallback
                obj105["svg"] = dynamicValue(null)
                obj105["duration"] = 4
                obj105["time"] = 0
                obj105["rate"] = 1
                obj105["raf"] = 0
                obj105["playing"] = false
                obj105["last"] = 0
                obj105["started"] = false
            }
            if (truth(active)) {
                stop(active)
            }
            if (truth(panel)) {
                panel.insertAdjacentElement("afterend", fallback)
            } else {
                modal.append(fallback)
            }
            fallback.addEventListener("toggle", (fun(): dynamic {
                if (truth(fallback["open"])) {
                    run {
                        val target106: dynamic = counters
                        val previous108: dynamic = numeric(target106.opened)
                        target106.opened = jsAdd(previous108, 1)
                    }
                    load(session, ex)
                } else {
                    stop(session)
                    val btn: dynamic = selectElement(fallback, ".svg-play3862")
                    if (truth(btn)) {
                        btn.textContent = "Reproducir"
                    }
                }
                return absent
            }))
            if (truth(jsOr((!truth(panel))) { (!truth(selectElement(panel, "#vectorCanvas"))) })) {
                fallback["open"] = true
            }
            return absent
        }
        if (truth(jsOr(root.HunterDemoCoverage3862) { (js("typeof technique") !== "function") })) {
            return absent
        }
        val baseTechnique: dynamic = global_technique
        selectElement = (fun(parent: dynamic, selector: dynamic): dynamic {
            return jsOr(onPresent(parent) { value3 -> value3.querySelector(selector) }) { null }
        })
        reduced = (fun(): dynamic {
            return (run { val owner5: dynamic = root; onPresent(owner5.matchMedia) { method4 -> onPresent(invokeWith(method4, owner5, arrayOf<dynamic>("(prefers-reduced-motion: reduce)"))) { value6 -> value6.matches } } } === true)
        })
        active = dynamicValue(null)
        counters = jsonValue("{\"opened\":0,\"loaded\":0,\"failures\":0}")
        global_technique = (fun(id: dynamic): dynamic {
            val receiver113: dynamic = js("this")
            val callArgs114: dynamic = js("arguments")
            val result: dynamic = baseTechnique.apply(receiver113, callArgs114)
            addOption(id)
            return result
        })
        onPresent(document.querySelector("#modal")) { value115 -> value115.addEventListener("close", (fun(): dynamic {
            return stop(active)
        })) }
        document.addEventListener("visibilitychange", (fun(): dynamic {
            if (truth(document.hidden)) {
                stop(active)
            }
            return absent
        }), jsonValue("{\"passive\":true}"))
        root.HunterDemoCoverage3862 = global_Object.freeze(jsObject { obj118 ->
            obj118["version"] = "38.62"
            obj118["stats"] = (fun(): dynamic {
                return jsObject { obj119 ->
                    copyFields(obj119, counters)
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
