@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv31

import hunter.browser.*

@JsName("V31UI") private external var global_V31UI: dynamic
@JsName("SessionLayout31") private external var global_SessionLayout31: dynamic
@JsName("compactGuide31") private external var global_compactGuide31: dynamic
@JsName("shadowAura31") private external var global_shadowAura31: dynamic
@JsName("resizeCompanion31") private external var global_resizeCompanion31: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("setGuideMode22") private external var global_setGuideMode22: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("positionCompanion30") private external var global_positionCompanion30: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV31() {
    global_compactGuide31 = fun(): dynamic {
        val g: dynamic = global_guide11
        val slot: dynamic = global_Dollar("#guidedSlot")
        val article: dynamic = onPresent(slot) { value30 -> value30.querySelector(".guided-step") }
        val session: dynamic = global_Dollar("#modal .guided-session")
        if (truth(jsOr((!truth(g))) { (!truth(session)) })) {
            return absent
        }
        session.classList.add("compact-session31")
        session.dataset.sessionKey31 = global_SessionLayout31.key()
        val order: dynamic = global_Dollar("#blockSlot22 .block-layout22")
        if (truth(jsAnd(order) { (!truth(order.closest("[data-section31=\"order\"]"))) })) {
            val place: dynamic = order.parentElement
            val d: dynamic = global_SessionLayout31.wrap(arrayOf<dynamic>(order), "Orden de sesión", "order", place)
            val label: dynamic = order.querySelector(":scope>label")
            if (truth((onPresent(label) { value33 -> onPresent(value33.firstChild) { value34 -> value34.nodeType } } === 3))) {
                label.firstChild.textContent = "Organizar bloques"
            }
        }
        if (truth((!truth(article)))) {
            return absent
        }
        val step: dynamic = g.steps[g.index]
        article.dataset.stepKind31 = jsOr(onPresent(step) { value39 -> value39.kind }) { "end" }
        val prose: dynamic = dynamicValue(jsArray { array41 ->
            appendAll(array41, article.children)
        }).filter((fun(n: dynamic): dynamic {
            return jsOr(jsAnd((n.tagName === "P")) { (!truth(n.classList.contains("warn"))) }) { (n.tagName === "DETAILS") }
        }))
        val instructions: dynamic = global_SessionLayout31.wrap(prose, "Cómo realizar el ejercicio", "instructions", article)
        if (truth((!truth(prose.length)))) {
            instructions.remove()
        }
        val demo: dynamic = article.querySelector(":scope>.guided-demo")
        val dose: dynamic = article.querySelector(":scope>.guided-dose")
        val clock: dynamic = article.querySelector(":scope>.guided-clock")
        if (truth(jsOr(jsOr(demo) { dose }) { clock })) {
            val work: dynamic = global_document.createElement("div")
            work.className = "session-work31"
            val stats: dynamic = global_document.createElement("div")
            stats.className = "session-stats31"
            if (truth(demo)) {
                work.append(demo)
            }
            if (truth(dose)) {
                stats.append(dose)
            }
            if (truth(clock)) {
                stats.append(clock)
            }
            work.append(stats)
            val title: dynamic = article.querySelector(":scope>h3")
            if (truth(title)) {
                title.after(work)
            } else {
                article.prepend(work)
            }
            if (truth((!truth(demo)))) {
                work.classList.add("clock-only31")
            }
        }
        val toggle: dynamic = article.querySelector(":scope>#guidedDemoToggle")
        val speeds: dynamic = article.querySelector(":scope>.playback-speeds,:scope>.motion-speed18,:scope>.playback-controls,:scope>.speed-controls")
        val controls: dynamic = dynamicValue(arrayOf<dynamic>(toggle, speeds)).filter(global_Boolean)
        if (true) {
            val iterator46: dynamic = iteratorOf(jsArray { array48 ->
                appendAll(array48, article.children)
            })
            while (true) {
                val next47: dynamic = iterator46.next()
                if (truth(next47.done)) break
                var n: dynamic = absent
                n = next47.value
                if (truth(jsAnd(n.classList.contains("model-speed")) { (!truth(controls.includes(n))) })) {
                    controls.push(n)
                }
            }
        }
        if (truth(controls.length)) {
            val d: dynamic = global_SessionLayout31.wrap(controls, "Velocidad de demostración", "playback", article)
            d.classList.add("session-playback31")
        }
        if (truth(instructions.isConnected)) {
            article.append(instructions)
        }
        val status: dynamic = global_Dollar("#guideCheckStatus19")
        if (truth(status)) {
            status.classList.add("sr-only31")
        }
        val check: dynamic = article.querySelector(".guide-check19 small")
        if (truth(check)) {
            check.textContent = "Marca solo lo que realizaste."
        }
        val progress: dynamic = slot.querySelector(".guided-progress")
        onPresent(progress) { value51 -> onPresent(value51.querySelector(":scope>small")) { value52 -> value52.classList.add("sr-only31") } }
        val alarm: dynamic = onPresent(clock) { value54 -> value54.querySelector("[data-alarm-note27]") }
        if (truth(alarm)) {
            alarm.classList.add("sr-only31")
        }
        onPresent(clock) { value55 -> onPresent(value55.querySelector(".guide-alarm-test27")) { value56 -> value56.remove() } }
        return absent
    }
    global_shadowAura31 = fun(): dynamic {
        val c: dynamic = global_Dollar("#companion")
        if (truth((!truth(c)))) {
            return absent
        }
        c.classList.add("companion-shadow31")
        onPresent(c.querySelector(":scope>.ornament26")) { value63 -> value63.remove() }
        if (truth((!truth(c.querySelector(".shadow-aura31"))))) {
            val aura: dynamic = global_document.createElement("span")
            aura.className = "shadow-aura31"
            aura.setAttribute("aria-hidden", "true")
            aura.innerHTML = "<i></i><i></i><i></i><i></i><i></i><i></i><i></i>"
            c.prepend(aura)
        }
        c.setAttribute("title", jsAdd("Abrir ", global_companionDisplayName()))
        c.setAttribute("aria-label", jsAdd("Abrir asistente ", global_companionDisplayName()))
        c.querySelectorAll(":scope>span:not(.shadow-aura31)").forEach((fun(n: dynamic): dynamic {
            return n.classList.add("sr-only31")
        }))
        return absent
    }
    global_V31UI = jsObject { obj1 ->
        obj1["render"] = global_render
        obj1["paintGuide11"] = global_paintGuide11
        obj1["openCoach"] = global_openCoach
        obj1["setGuideMode22"] = global_setGuideMode22
    }
    global_SessionLayout31 = jsObject { obj2 ->
        obj2["state"] = jsConstruct(global_Map, js("[]"))
        obj2["key"] = fun(): dynamic {
            return jsAdd(jsAdd(onPresent(global_guide11) { value5 -> value5.day.date }, ":"), (!truth((!truth(onPresent(global_guide11) { value7 -> value7.record })))))
        }
        obj2["capture"] = fun(): dynamic {
            val receiver8: dynamic = js("this")
            val session: dynamic = global_Dollar("#modal .guided-session")
            if (truth((!truth(onPresent(session) { value10 -> value10.dataset.sessionKey31 })))) {
                return absent
            }
            if (true) {
                val iterator12: dynamic = iteratorOf(session.querySelectorAll(".session-fold31"))
                while (true) {
                    val next13: dynamic = iterator12.next()
                    if (truth(next13.done)) break
                    var detail: dynamic = absent
                    detail = next13.value
                    receiver8.state["set"](jsAdd(jsAdd(session.dataset.sessionKey31, ":"), detail.dataset.section31), detail["open"])
                }
            }
            return absent
        }
        obj2["remember"] = fun(detail: dynamic, name: dynamic): dynamic {
            val receiver14: dynamic = js("this")
            val key: dynamic = jsAdd(jsAdd(receiver14.key(), ":"), name)
            detail["open"] = jsOr(receiver14.state["get"](key)) { false }
            detail.addEventListener("toggle", (fun(): dynamic {
                if (truth(detail.isConnected)) {
                    receiver14.state["set"](key, detail["open"])
                }
                return absent
            }))
            return absent
        }
        obj2["wrap"] = fun(nodes: dynamic, title: dynamic, name: dynamic, root: dynamic): dynamic {
            val receiver18: dynamic = js("this")
            val d: dynamic = global_document.createElement("details")
            d.className = "session-fold31"
            d.dataset.section31 = name
            val summary: dynamic = global_document.createElement("summary")
            summary.textContent = title
            d.append(summary)
            val content: dynamic = global_document.createElement("div")
            content.className = "session-fold-body31"
            d.append(content)
            nodes.forEach((fun(n: dynamic): dynamic {
                return content.append(n)
            }))
            root.append(d)
            receiver18.remember(d, name)
            return d
        }
    }
    global_paintGuide11 = (fun(): dynamic {
        global_SessionLayout31.capture()
        global_V31UI.paintGuide11()
        global_compactGuide31()
        return absent
    })
    global_setGuideMode22 = (fun(mode: dynamic): dynamic {
        global_SessionLayout31.capture()
        global_V31UI.setGuideMode22(mode)
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V31UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target70: dynamic = n
                val assigned72: dynamic = "SISTEMA DE SOMBRAS"
                target70.textContent = assigned72
                assigned72
            }
        }))
        if (truth((global_tab === "home"))) {
            onPresent(global_Dollar("#app .coach-banner")) { value73 -> value73.remove() }
        }
        global_shadowAura31()
        return absent
    })
    global_openCoach = (fun(): dynamic {
        global_V31UI.openCoach()
        global_shadowAura31()
        return absent
    })
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        return global_document.documentElement.classList.toggle("aura-paused31", global_document.hidden)
    }))
    global_resizeCompanion31 = global_positionCompanion30
    global_positionCompanion30 = (fun(): dynamic {
        global_resizeCompanion31()
        global_shadowAura31()
        return absent
    })
    global_state.appVersion = 31
    global_save()
    global_render()
}
