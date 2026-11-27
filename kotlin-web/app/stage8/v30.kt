@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv30

import hunter.browser.*

@JsName("V30UI") private external var global_V30UI: dynamic
@JsName("positionCompanion30") private external var global_positionCompanion30: dynamic
@JsName("panelBefore30") private external var global_panelBefore30: dynamic
@JsName("paintBefore30") private external var global_paintBefore30: dynamic
@JsName("migrateBefore30") private external var global_migrateBefore30: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("Motion2D18") private external var global_Motion2D18: dynamic
@JsName("vectorPanel") private external var global_vectorPanel: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("Motion30") private external var global_Motion30: dynamic
@JsName("migrateGuide22") private external var global_migrateGuide22: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("RoutineSequence") private external var global_RoutineSequence: dynamic
@JsName("anchorStep22") private external var global_anchorStep22: dynamic
@JsName("offlineCard20") private external var global_offlineCard20: dynamic
@JsName("Offline20") private external var global_Offline20: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("UI26") private external var global_UI26: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("reveal20") private external var global_reveal20: dynamic
@JsName("Boot30") private external var global_Boot30: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV30() {
    global_positionCompanion30 = fun(): dynamic {
        val c: dynamic = global_Dollar("#companion")
        val nav: dynamic = global_Dollar(".nav")
        if (truth((!truth(c)))) {
            return absent
        }
        if (truth((c.parentElement === nav))) {
            global_Dollar("#app").append(c)
        }
        c.classList.remove("companion-dock26")
        c.classList.add("companion-floating30")
        onPresent(nav) { value4 -> value4.classList.remove("nav-dock26") }
        c.setAttribute("aria-label", jsAdd("Abrir asistente ", global_companionDisplayName()))
        global_document.documentElement.style.setProperty("--nav-height30", jsAdd(global_Math.max(60, jsOr(onPresent(nav) { value6 -> value6.getBoundingClientRect().height }) { 78 }), "px"))
        return absent
    }
    global_V30UI = jsObject { obj1 ->
        obj1["render"] = global_render
        obj1["openCoach"] = global_openCoach
        obj1["profileForm"] = global_profileForm
        obj1["technique"] = global_technique
    }
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V30UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target10: dynamic = n
                val assigned12: dynamic = "SISTEMA DE SOMBRAS"
                target10.textContent = assigned12
                assigned12
            }
        }))
        global_positionCompanion30()
        if (truth(jsAnd(global_state.profile) { (global_tab === "profile") })) {
            if (true) {
                val iterator13: dynamic = iteratorOf(jsOr(onPresent(global_Dollar("#app main")) { value15 -> value15.querySelectorAll("p") }) { js("[]") })
                while (true) {
                    val next14: dynamic = iterator13.next()
                    if (truth(next14.done)) break
                    var p: dynamic = absent
                    p = next14.value
                    if (truth(dynamicValue(jsRegExp("demostraciones 2D HD locales", "")).test(p.textContent))) {
                        p.textContent = "1200 demostraciones 2D específicas sin conexión, con instrucciones, apoyos y cadencias."
                    }
                }
            }
        }
        return absent
    })
    global_window.addEventListener("resize", global_positionCompanion30, jsonValue("{\"passive\":true}"))
    global_vectorPaint = (fun(): dynamic {
        val s: dynamic = global_vectorSession
        val c: dynamic = global_Dollar("#vectorCanvas")
        if (truth(jsOr((!truth(s))) { (!truth(c)) })) {
            return absent
        }
        global_drawVector(c, s.id, s.frame, s.mirror)
        global_Dollar("#vectorRange")["value"] = jsMod(global_Math.floor(s.frame), 48)
        global_Dollar("#vectorPhase").textContent = global_Motion2D18.label(s.id, s.frame)
        return absent
    })
    global_panelBefore30 = global_vectorPanel
    global_paintBefore30 = global_paintGuide11
    global_paintGuide11 = (fun(): dynamic {
        global_paintBefore30()
        val id: dynamic = onPresent(global_guide11) { value26 -> onPresent(value26.steps[global_guide11.index]) { value28 -> value28.id } }
        if (truth(id)) {
            global_Motion30.preload(id)
        }
        return absent
    })
    global_migrateBefore30 = global_migrateGuide22
    global_migrateGuide22 = (fun(day: dynamic, steps: dynamic, saved: dynamic): dynamic {
        val old: dynamic = global_migrateBefore30(day, steps, saved)
        if (truth(jsOr(jsOr(old) { (!truth(saved)) }) { (!truth(global_Number.isInteger(saved.index))) })) {
            return old
        }
        if (truth(jsOr((!truth(onPresent(steps[0]) { value31 -> value31.cardio24 }))) { (!truth(onPresent(steps.at((-numeric(1)))) { value32 -> value32.cardio24 })) })) {
            return null
        }
        val ids: dynamic = jsonValue("[\"jog\",\"rope\",\"heelback\",\"box\",\"fastmarch\",\"heeldig\",\"sidestep\",\"seatedpunch\",\"seatedmarch\",\"seatedstep\",\"walk\"]")
        if (true) {
            val iterator33: dynamic = iteratorOf(ids)
            while (true) {
                val next34: dynamic = iterator33.next()
                if (truth(next34.done)) break
                var warm: dynamic = absent
                warm = next34.value
                if (true) {
                    val iterator35: dynamic = iteratorOf(ids)
                    while (true) {
                        val next36: dynamic = iterator35.next()
                        if (truth(next36.done)) break
                        var cool: dynamic = absent
                        cool = next36.value
                        val before: dynamic = steps.map((fun(s: dynamic): dynamic {
                            return jsObject { obj37 ->
                                copyFields(obj37, s)
                            }
                        }))
                        before[0] = jsObject { obj40 ->
                            copyFields(obj40, before[0])
                            obj40["id"] = warm
                            obj40["label"] = jsAdd("Calentamiento cardiovascular · ", global_E.byId(warm).name)
                        }
                        before[jsSubtract(before.length, 1)] = jsObject { obj43 ->
                            copyFields(obj43, before.at((-numeric(1))))
                            obj43["id"] = cool
                            obj43["label"] = jsAdd("Cardio suave final · ", global_E.byId(cool).name)
                        }
                        if (truth((saved.signature !== global_RoutineSequence.signature(before)))) {
                            continue
                        }
                        val index: dynamic = global_anchorStep22(before, saved.index, steps)
                        if (truth(jsLess(index, 0))) {
                            return null
                        }
                        return jsObject { obj44 ->
                            copyFields(obj44, saved)
                            obj44["signature"] = global_RoutineSequence.signature(steps)
                            obj44["index"] = index
                            obj44["remaining"] = global_Math.min(jsOr(saved.remaining) { 0 }, jsOr(onPresent(steps[index]) { value45 -> value45.seconds }) { 0 })
                        }
                    }
                }
            }
        }
        return null
    })
    global_vectorPanel = (fun(e: dynamic): dynamic {
        val view: dynamic = global_Motion30["get"](e.id).model.view21
        return global_panelBefore30(jsObject { obj50 ->
            copyFields(obj50, e)
            obj50["view21"] = jsObject { obj51 ->
                copyFields(obj51, view)
                obj51["reason"] = "Observa el plano principal, los apoyos y el recorrido de esta variante."
            }
        }).replace("Las variantes de ritmo comparten el movimiento base; consulta sus instrucciones.", "Cada ficha tiene una demostración con su recorrido y cadencia. Las pausas y el retorno lento se muestran dentro del ciclo.")
    })
    global_offlineCard20 = (fun(): dynamic {
        return ""
    })
    global_Offline20.count = (fun(): dynamic {
        return 1000
    })
    global_technique = (fun(id: dynamic): dynamic {
        global_V30UI.technique(id)
        global_Motion30.preload(id)
        onPresent(global_Dollar("#modal .offline-demo20")) { value58 -> value58.remove() }
        val panel: dynamic = global_Dollar("#modal .motion-card18")
        if (truth(panel)) {
            panel.dataset.demonstration30 = id
            panel.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<p class=\"demo-source30\">Demostración local de esta ficha · ", global_esc((if (truth(global_E.byId(id).sides)) "Completa ambos lados según la dosis." else "Conserva los apoyos mostrados."))), "</p>"))
        }
        global_UI26.schedule()
        return absent
    })
    global_openCoach = (fun(): dynamic {
        global_V30UI.openCoach()
        val host: dynamic = global_Dollar("#animatedCompanion")
        if (truth(host)) {
            host.style.width = ""
            host.style.height = ""
        }
        val heading: dynamic = global_Dollar("#modal .eyebrow")
        if (truth(jsAnd(heading) { dynamicValue(jsRegExp("asistente", "")).test(heading.textContent.toLowerCase()) })) {
            heading.textContent = jsAdd(global_companionDisplayName(), " / ASISTENTE LOCAL")
        }
        global_Dollar("#modal").querySelectorAll(".gesture-controls,.companion-picker").forEach((fun(n: dynamic): dynamic {
            return n.remove()
        }))
        global_UI26.schedule()
        return absent
    })
    global_openDiagnostics20 = (fun(): dynamic {
        global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section><h2>Estado del contenido local</h2><p>", global_E.exercises.length), " fichas con demostración 2D propia · "), global_Catalog.routines.length), " rutinas.</p><p>Versión 30.0. "), global_Runtime20.errors.length), " errores de interfaz registrados en esta apertura.</p><p>La ilustración explica la técnica; no observa tu postura. Las rutinas usan objetivos, equipo y restricciones declaradas; la revisión semanal usa tus series confirmadas y el esfuerzo registrado.</p></section>"))
        return absent
    })
    global_reveal20 = (fun(): dynamic {
        global_Boot30.complete()
        return absent
    })
    global_state.appVersion = 30
    global_save()
    global_render()
}
