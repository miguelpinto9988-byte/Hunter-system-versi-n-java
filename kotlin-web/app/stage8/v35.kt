@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv35

import hunter.browser.*

@JsName("V35UI") private external var global_V35UI: dynamic
@JsName("SessionLandscape35") private external var global_SessionLandscape35: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("backModal24") private external var global_backModal24: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("UI26") private external var global_UI26: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV35() {
    global_V35UI = jsObject { obj1 ->
        obj1["paintGuide11"] = global_paintGuide11
        obj1["modal"] = global_modal
        obj1["backModal24"] = global_backModal24
        obj1["openDiagnostics20"] = global_openDiagnostics20
    }
    global_SessionLandscape35 = jsObject { obj2 ->
        obj2["current"] = dynamicValue(null)
        obj2["pending"] = 0
        obj2["enabled"] = fun(): dynamic {
            return global_matchMedia("(orientation: landscape) and (min-width: 600px)").matches
        }
        obj2["move"] = fun(entry: dynamic, node: dynamic, parent: dynamic): dynamic {
            if (truth((!truth(node)))) {
                return absent
            }
            val marker: dynamic = global_document.createComment("posición de sesión")
            node.before(marker)
            entry.moves.push(jsObject { obj7 ->
                obj7["node"] = node
                obj7["marker"] = marker
            })
            parent.append(node)
            return absent
        }
        obj2["restore"] = fun(): dynamic {
            val receiver8: dynamic = js("this")
            val entry: dynamic = receiver8.current
            if (truth((!truth(entry)))) {
                return absent
            }
            receiver8.current = dynamicValue(null)
            if (true) {
                val iterator12: dynamic = iteratorOf(entry.moves)
                while (true) {
                    val next13: dynamic = iterator12.next()
                    if (truth(next13.done)) break
                    var node: dynamic = absent
                    var marker: dynamic = absent
                    val unpacked14: dynamic = next13.value
                    node = unpacked14["node"]
                    marker = unpacked14["marker"]
                    if (truth(marker.parentNode)) {
                        marker.replaceWith(node)
                    }
                }
            }
            onPresent(entry.work) { value15 -> value15.classList.remove("session-body35") }
            entry.created.forEach((fun(n: dynamic): dynamic {
                return n.remove()
            }))
            return absent
        }
        obj2["heading"] = fun(session: dynamic, step: dynamic): dynamic {
            val header: dynamic = session.querySelector(".guided-header")
            val title: dynamic = onPresent(header) { value19 -> value19.querySelector("h2") }
            if (truth((!truth(title)))) {
                return absent
            }
            var landscape: dynamic = title.querySelector(".session-title-landscape35")
            if (truth((!truth(landscape)))) {
                val original: dynamic = global_document.createElement("span")
                original.className = "session-title-original35"
                while (truth(title.firstChild)) {
                    original.append(title.firstChild)
                }
                landscape = global_document.createElement("span")
                landscape.className = "session-title-landscape35"
                landscape.textContent = "Sesión paso a paso"
                title.append(original, landscape)
            }
            var meta: dynamic = header.querySelector(".session-meta35")
            if (truth((!truth(meta)))) {
                meta = global_document.createElement("small")
                meta.className = "session-meta35"
                header.append(meta)
            }
            val block: dynamic = session.querySelector(".block-heading22")
            meta.textContent = (if (truth(block)) dynamicValue(jsArray { array33 ->
                appendAll(array33, block.children)
            }).map((fun(n: dynamic): dynamic {
                return n.textContent.trim()
            })).join(" · ") else (if (truth(onPresent(step) { value34 -> value34.cardio24 })) (if (truth((step.kind === "preparation"))) "Preparación · Inicio" else "Cardio suave · Final") else (if (truth((onPresent(step) { value35 -> value35.kind } === "rest"))) "Recuperación" else (if (truth((onPresent(step) { value36 -> value36.kind } === "exercise"))) jsAdd(jsAdd(jsAdd("Serie ", jsAdd(step.setIndex, 1)), " de "), step.sets) else ""))))
            return absent
        }
        obj2["sync"] = fun(): dynamic {
            val receiver37: dynamic = js("this")
            val dialog: dynamic = global_Dollar("#modal")
            val session: dynamic = dialog.querySelector(".guided-session")
            val article: dynamic = onPresent(session) { value39 -> value39.querySelector(".guided-step") }
            val work: dynamic = onPresent(article) { value40 -> value40.querySelector(".session-work31") }
            val active: dynamic = (!truth((!truth(jsAnd(jsAnd(jsAnd(jsAnd(dialog["open"]) { session }) { article }) { work }) { receiver37.enabled() }))))
            if (truth(jsAnd(receiver37.current) { jsOr((!truth(active))) { (receiver37.current.article !== article) } })) {
                receiver37.restore()
            }
            dialog.classList.toggle("session-landscape35", active)
            onPresent(session) { value41 -> value41.classList.toggle("wide-session35", active) }
            if (truth((!truth(active)))) {
                if (truth(dialog["open"])) {
                    global_ModalViewport.fit()
                }
                return absent
            }
            receiver37.heading(session, onPresent(global_guide11) { value43 -> value43.steps[global_guide11.index] })
            if (truth((!truth(receiver37.current)))) {
                val entry: dynamic = jsObject { obj45 ->
                    obj45["article"] = article
                    obj45["work"] = work
                    obj45["moves"] = js("[]")
                    obj45["created"] = js("[]")
                }
                receiver37.current = entry
                val create: dynamic = (fun(tag: dynamic, cls: dynamic, parent: dynamic): dynamic {
                    val n: dynamic = global_document.createElement(tag)
                    n.className = cls
                    parent.append(n)
                    entry.created.push(n)
                    return n
                })
                val visual: dynamic = create("div", "session-visual35", work)
                val controls: dynamic = create("div", "session-controls35", work)
                work.classList.add("session-body35")
                receiver37.move(entry, article.querySelector(":scope>h3"), visual)
                val demo: dynamic = work.querySelector(":scope>.guided-demo")
                if (truth(demo)) {
                    receiver37.move(entry, demo, visual)
                } else {
                    val rest: dynamic = article.querySelector(":scope>.rest-orbit")
                    if (truth(rest)) {
                        val host: dynamic = create("div", "session-rest35", visual)
                        receiver37.move(entry, rest, host)
                    }
                }
                receiver37.move(entry, work.querySelector(":scope>.session-stats31"), controls)
                if (true) {
                    val iterator50: dynamic = iteratorOf(jsArray { array52 ->
                        appendAll(array52, article.children)
                    })
                    while (true) {
                        val next51: dynamic = iterator50.next()
                        if (truth(next51.done)) break
                        var n: dynamic = absent
                        n = next51.value
                        if (truth(n.matches("#guideRecord,.action-pair,button.btn.primary,p.warn"))) {
                            receiver37.move(entry, n, controls)
                        }
                    }
                }
                val folds: dynamic = create("div", "session-folds35", article)
                receiver37.move(entry, session.querySelector("#blockSlot22>.session-fold31"), folds)
                if (true) {
                    val iterator53: dynamic = iteratorOf(jsonValue("[\":scope>.session-fold31[data-section31=instructions]\",\":scope>.session-fold31[data-section31=playback]\",\":scope>.btn.danger\"]"))
                    while (true) {
                        val next54: dynamic = iterator53.next()
                        if (truth(next54.done)) break
                        var selector: dynamic = absent
                        selector = next54.value
                        receiver37.move(entry, article.querySelector(selector), folds)
                    }
                }
            }
            global_ModalViewport.fit()
            global_UI26.schedule()
            return absent
        }
        obj2["schedule"] = fun(): dynamic {
            val receiver55: dynamic = js("this")
            if (truth(receiver55.pending)) {
                return absent
            }
            receiver55.pending = global_requestAnimationFrame((fun(): dynamic {
                receiver55.pending = 0
                receiver55.sync()
                return absent
            }))
            return absent
        }
    }
    global_paintGuide11 = (fun(): dynamic {
        global_V35UI.paintGuide11()
        global_SessionLandscape35.sync()
        return absent
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_SessionLandscape35.restore()
        global_Dollar("#modal").classList.remove("session-landscape35")
        global_V35UI.modal(html)
        global_SessionLandscape35.sync()
        return absent
    })
    global_backModal24 = (fun(): dynamic {
        global_V35UI.backModal24()
        global_SessionLandscape35.sync()
        return absent
    })
    global_openDiagnostics20 = (fun(): dynamic {
        global_V35UI.openDiagnostics20()
        global_Dollar("#modal").querySelectorAll("p").forEach((fun(n: dynamic): dynamic {
            return run {
                val target69: dynamic = n
                val assigned71: dynamic = n.textContent.replace("Versión 34.0.", "Versión 35.0.")
                target69.textContent = assigned71
                assigned71
            }
        }))
        return absent
    })
    global_window.addEventListener("resize", (fun(): dynamic {
        return global_SessionLandscape35.schedule()
    }), jsonValue("{\"passive\":true}"))
    global_window.addEventListener("orientationchange", (fun(): dynamic {
        return global_SessionLandscape35.schedule()
    }), jsonValue("{\"passive\":true}"))
    onPresent(global_window.visualViewport) { value72 -> value72.addEventListener("resize", (fun(): dynamic {
        return global_SessionLandscape35.schedule()
    }), jsonValue("{\"passive\":true}")) }
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth((!truth(global_Dollar("#modal")["open"])))) {
            global_SessionLandscape35.restore()
            global_Dollar("#modal").classList.remove("session-landscape35")
        }
        return absent
    }))
    global_window.HunterV35 = jsObject { obj75 ->
        obj75["SessionLandscape35"] = global_SessionLandscape35
    }
    global_state.appVersion = 35
    global_save()
    global_SessionLandscape35.sync()
}
