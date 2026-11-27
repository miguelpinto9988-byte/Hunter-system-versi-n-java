@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv20

import hunter.browser.*

@JsName("V20UI") private external var global_V20UI: dynamic
@JsName("Offline20") private external var global_Offline20: dynamic
@JsName("offlineCard20") private external var global_offlineCard20: dynamic
@JsName("toggleOffline20") private external var global_toggleOffline20: dynamic
@JsName("playOffline20") private external var global_playOffline20: dynamic
@JsName("cleanupOffline20") private external var global_cleanupOffline20: dynamic
@JsName("bindGuide20") private external var global_bindGuide20: dynamic
@JsName("progressPanel20") private external var global_progressPanel20: dynamic
@JsName("openReference20") private external var global_openReference20: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("themeNative20") private external var global_themeNative20: dynamic
@JsName("normalizeButtons20") private external var global_normalizeButtons20: dynamic
@JsName("reveal20") private external var global_reveal20: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Motion2D18") private external var global_Motion2D18: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("MutationObserver") private external var global_MutationObserver: dynamic
@JsName("confirmGuideSet11") private external var global_confirmGuideSet11: dynamic
@JsName("finishGuide11") private external var global_finishGuide11: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("ProgressMonitor20") private external var global_ProgressMonitor20: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("TrainingReference20") private external var global_TrainingReference20: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("getComputedStyle") private external var global_getComputedStyle: dynamic
@JsName("HunterNative3875") private external var global_HunterNative3875: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("Boot30") private external var global_Boot30: dynamic

internal fun installV20() {
    global_offlineCard20 = fun(id: dynamic): dynamic {
        val base: dynamic = global_Motion2D18["get"](id).base
        val clip: dynamic = onPresent(global_window.OfflineManifest20) { value6 -> onPresent(value6.clips) { value7 -> value7[base] } }
        if (truth((!truth(clip)))) {
            return ""
        }
        return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<details class=\"offline-demo20 card\" ontoggle=\"toggleOffline20(this)\"><summary>Demostración HD · disponible sin conexión</summary><div class=\"offline-player20\"><video playsinline muted loop preload=\"none\" poster=\"media2d/", base), ".svg\" data-src=\"offline2d/"), base), ".mp4\" aria-label=\"Demostración 2D HD de "), global_esc(global_E.byId(id).name)), "\"></video></div><div class=\"action-pair\"><button type=\"button\" class=\"btn\" onclick=\"playOffline20(this)\">Reproducir / pausar</button><label>Velocidad<select onchange=\"HunterActions.setPlaybackRate(this)\"><option value=\"0.65\">Lenta</option><option value=\"1\" selected>Normal</option><option value=\"1.4\">Rápida</option></select></label></div><p class=\"footnote\">Dos ciclos del mismo movimiento 2D en alta resolución. "), (if (truth((global_E.byId(id).variant !== "base"))) "Esta variante comparte el movimiento base; sigue su cadencia e instrucciones. " else "")), "La velocidad del vídeo no cambia el reloj de la sesión.</p><p class=\"offline-status20\" aria-live=\"polite\"></p></details>")
    }
    global_toggleOffline20 = fun(details: dynamic): dynamic {
        val video: dynamic = details.querySelector("video")
        if (truth((!truth(video)))) {
            return absent
        }
        if (truth((!truth(details["open"])))) {
            video.pause()
            return absent
        }
        if (truth((!truth(video.src)))) {
            video.src = video.dataset.src
            video.addEventListener("error", (fun(): dynamic {
                details.querySelector(".offline-status20").textContent = "El reproductor no pudo abrir este clip. La demostración vectorial y las instrucciones siguen disponibles."
                return absent
            }))
        }
        global_Offline20.players.add(video)
        if (truth(jsAnd((onPresent(global_state.profile) { value14 -> value14.animate } !== false)) { (!truth(global_matchMedia("(prefers-reduced-motion: reduce)").matches)) })) {
            video.play()["catch"]((fun(): dynamic {
                details.querySelector(".offline-status20").textContent = "Pulsa Reproducir para iniciar la demostración."
                return absent
            }))
        }
        return absent
    }
    global_playOffline20 = fun(button: dynamic): dynamic {
        val video: dynamic = button.closest(".offline-demo20").querySelector("video")
        if (truth(video.paused)) {
            video.play()["catch"]((fun(): dynamic {
                return global_toast("El reproductor no pudo iniciar. Consulta el dibujo vectorial.")
            }))
        } else {
            video.pause()
        }
        return absent
    }
    global_cleanupOffline20 = fun(): dynamic {
        if (true) {
            val iterator21: dynamic = iteratorOf(global_Offline20.players)
            while (true) {
                val next22: dynamic = iterator21.next()
                if (truth(next22.done)) break
                var video: dynamic = absent
                video = next22.value
                if (truth(jsOr((!truth(video.isConnected))) { (!truth(global_Dollar("#modal")["open"])) })) {
                    video.pause()
                    video.removeAttribute("src")
                    video.load()
                    global_Offline20.players.delete(video)
                }
            }
        }
        return absent
    }
    global_bindGuide20 = fun(): dynamic {
        if (true) {
            val iterator27: dynamic = iteratorOf(jsOr(onPresent(global_Dollar("#guidedSlot")) { value29 -> value29.querySelectorAll("form") }) { js("[]") })
            while (true) {
                val next28: dynamic = iterator27.next()
                if (truth(next28.done)) break
                var form: dynamic = absent
                form = next28.value
                val record: dynamic = (form.id === "guideRecord")
                form.removeAttribute("onsubmit")
                form.addEventListener("submit", (fun(event: dynamic): dynamic {
                    event.preventDefault()
                    try {
                        if (truth(record)) {
                            global_confirmGuideSet11(event)
                        } else {
                            global_finishGuide11(event)
                        }
                    } catch (error: dynamic) {
                        global_Runtime20.errors.push(jsObject { obj30 ->
                            obj30["message"] = error.message
                            obj30["source"] = "guided-session"
                            obj30["time"] = jsConstruct(global_Date, js("[]")).toISOString()
                        })
                        global_toast("El paso no pudo guardarse. Tu registro se conserva; vuelve a intentarlo.")
                    }
                    return absent
                }))
                form.querySelectorAll("button:not([type])").forEach((fun(button: dynamic): dynamic {
                    return run {
                        val target31: dynamic = button
                        val assigned33: dynamic = "button"
                        target31.type = assigned33
                        assigned33
                    }
                }))
            }
        }
        return absent
    }
    global_progressPanel20 = fun(): dynamic {
        val cycle: dynamic = global_currentCycle()
        val report: dynamic = global_ProgressMonitor20.summary(global_state.logs, jsOr(onPresent(cycle) { value45 -> value45.days }) { js("[]") }, global_state.profile, global_E.dateKey())
        val review: dynamic = onPresent(cycle) { value46 -> onPresent(value46.reviews) { value47 -> value47.at((-numeric(1))) } }
        return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section class=\"card progress-panel20\"><span class=\"eyebrow\">SEGUIMIENTO LOCAL · TUS REGISTROS</span><h3>", report.completedSessions), " sesiones comparables · "), report.confirmedSeries), " series confirmadas</h3><p>"), global_esc(jsOr(onPresent(review) { value48 -> value48.reason }) { "La revisión semanal conserva el plan mientras reúne registros completos y esfuerzo." })), "</p>"), report.analysis.map((fun(x: dynamic): dynamic {
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<details><summary>", global_esc(x.name)), " · "), x.records), " registros</summary><p>"), x.sets), " series × "), x.target), " "), (if (truth((x.unit === "sec"))) "segundos" else "repeticiones")), ". "), global_esc(x.reason)), "</p><p>"), (if (truth(x.estimate)) jsAdd(jsAdd(jsAdd(jsAdd("Esfuerzo estimado: ", x.estimate.effort), "/10 con "), x.estimate.neighbors), " vecinos comparables.") else "Estimación pendiente: faltan al menos tres registros comparables.")), "</p>"), (if (truth((x.nextId !== x.id))) jsAdd(jsAdd("<p>Variante propuesta: ", global_esc(jsOr(onPresent(global_E.byId(x.nextId)) { value50 -> value50.name }) { x.nextId })), ".</p>") else "")), "</details>")
        })).join("")), "<p class=\"footnote\">Se usa un estimador kNN local y reglas de progresión. No genera experiencia ficticia ni diagnostica lesiones. Los cambios se aplican al organizar la semana; la vista muestra la evaluación actual.</p></section>")
    }
    global_openReference20 = fun(): dynamic {
        global_modal(jsAdd(jsAdd("<section><span class=\"eyebrow\">BIBLIOTECA LOCAL DE ENTRENAMIENTO</span><h2>Tu técnica y tu progreso.</h2>", global_TrainingReference20.chapters.map((fun(x: dynamic): dynamic {
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<article class=\"card\"><h3>", global_esc(x.title)), "</h3><p>"), global_esc(x.text)), "</p>"), (if (truth(x.source)) jsAdd(jsAdd("<a href=\"", global_esc(x.source)), "\" target=\"_blank\" rel=\"noopener\">Consultar fuente ↗</a>") else "")), "</article>")
        })).join("")), "</section>"))
        return absent
    }
    global_openDiagnostics20 = fun(): dynamic {
        global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section><h2>Estado del contenido local</h2><p>", global_E.exercises.length), " fichas · "), global_OwnRoutines18.routines.length), " rutinas · "), global_Offline20.count()), " clips 2D HD.</p><p>Versión 22.0. "), global_Runtime20.submissions), " envíos de formulario gestionados dentro de la interfaz.</p><p>"), global_Runtime20.errors.length), " errores de interfaz registrados durante esta apertura.</p>"), global_Runtime20.errors.map((fun(x: dynamic): dynamic {
            return jsAdd(jsAdd(jsAdd(jsAdd("<p>", global_esc(x.source)), " · "), global_esc(x.message)), "</p>")
        })).join("")), "<p class=\"footnote\">Este panel registra errores de interfaz durante la ejecución. La instalación y el comportamiento del dispositivo necesitan comprobación en Android.</p></section>"))
        return absent
    }
    global_themeNative20 = fun(): dynamic {
        if (truth((!truth(onPresent(global_window.Android) { value60 -> value60.setThemeBackground })))) {
            return absent
        }
        val rgb: dynamic = global_getComputedStyle(global_document.body).backgroundColor.match(jsRegExp("\\d+", "g"))
        if (truth(jsAtLeast(onPresent(rgb) { value61 -> value61.length }, 3))) {
            global_HunterNative3875.setThemeBackground(jsAdd("#", rgb.slice(0, 3).map((fun(x: dynamic): dynamic {
                return global_Number(x)["toString"](16).padStart(2, "0")
            })).join("")))
        }
        return absent
    }
    global_normalizeButtons20 = fun(root: dynamic): dynamic {
        onPresent(root) { value72 -> value72.querySelectorAll("form button:not([type])").forEach((fun(button: dynamic): dynamic {
            return run {
                val target74: dynamic = button
                val assigned76: dynamic = "button"
                target74.type = assigned76
                assigned76
            }
        })) }
        return absent
    }
    global_reveal20 = fun(): dynamic {
        return global_Boot30.complete()
    }
    global_V20UI = jsObject { obj1 ->
        obj1["render"] = global_render
        obj1["openCoach"] = global_openCoach
        obj1["paintGuide11"] = global_paintGuide11
        obj1["technique"] = global_technique
        obj1["applyTheme"] = global_applyTheme
        obj1["profileForm"] = global_profileForm
    }
    global_Offline20 = jsObject { obj2 ->
        obj2["players"] = jsConstruct(global_Set, js("[]"))
        obj2["count"] = (fun(): dynamic {
            return global_Object.keys(jsOr(onPresent(global_window.OfflineManifest20) { value3 -> value3.clips }) { js("({})") }).length
        })
    }
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        if (truth(global_document.hidden)) {
            if (true) {
                val iterator23: dynamic = iteratorOf(global_Offline20.players)
                while (true) {
                    val next24: dynamic = iterator23.next()
                    if (truth(next24.done)) break
                    var video: dynamic = absent
                    video = next24.value
                    video.pause()
                }
            }
        }
        return absent
    }))
    global_Dollar("#modal").addEventListener("close", global_cleanupOffline20)
    jsConstruct(global_MutationObserver, arrayOf<dynamic>(global_cleanupOffline20)).observe(global_Dollar("#modal"), jsonValue("{\"childList\":true,\"subtree\":true}"))
    global_paintGuide11 = (fun(): dynamic {
        global_V20UI.paintGuide11()
        global_bindGuide20()
        val step: dynamic = onPresent(global_guide11) { value36 -> value36.steps[global_guide11.index] }
        if (truth((onPresent(step) { value38 -> value38.kind } === "exercise"))) {
            onPresent(global_Dollar("#guidedSlot .guided-demo")) { value39 -> value39.insertAdjacentHTML("afterend", global_offlineCard20(step.id)) }
        }
        return absent
    })
    global_technique = (fun(id: dynamic): dynamic {
        global_V20UI.technique(id)
        onPresent(global_Dollar("#modal .motion-card18")) { value42 -> value42.insertAdjacentHTML("afterend", global_offlineCard20(id)) }
        return absent
    })
    global_openCoach = (fun(): dynamic {
        global_V20UI.openCoach()
        onPresent(global_Dollar("#modal .learning-card")) { value53 -> value53.insertAdjacentHTML("afterend", global_progressPanel20()) }
        return absent
    })
    global_applyTheme = (fun(p: dynamic): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V20UI.applyTheme(p)
        global_themeNative20()
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V20UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target66: dynamic = n
                val assigned68: dynamic = "EL DESPERTAR · V21"
                target66.textContent = assigned68
                assigned68
            }
        }))
        if (truth(jsAnd(global_state.profile) { (global_tab === "profile") })) {
            onPresent(global_Dollar("#app main")) { value69 -> value69.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<section class=\"card\"><h3>Contenido y seguimiento sin conexión</h3><p>", global_Offline20.count()), " demostraciones 2D HD locales, además de los dibujos vectoriales y sus instrucciones.</p><button type=\"button\" class=\"btn\" onclick=\"openReference20()\">Biblioteca de entrenamiento</button><button type=\"button\" class=\"btn ghost\" onclick=\"openDiagnostics20()\">Estado de la aplicación</button></section>")) }
        }
        global_themeNative20()
        return absent
    })
    jsConstruct(global_MutationObserver, arrayOf<dynamic>((fun(records: dynamic): dynamic {
        if (true) {
            val iterator83: dynamic = iteratorOf(records)
            while (true) {
                val next84: dynamic = iterator83.next()
                if (truth(next84.done)) break
                var record: dynamic = absent
                record = next84.value
                if (true) {
                    val iterator85: dynamic = iteratorOf(record.addedNodes)
                    while (true) {
                        val next86: dynamic = iterator85.next()
                        if (truth(next86.done)) break
                        var node: dynamic = absent
                        node = next86.value
                        if (truth((node.nodeType !== 1))) {
                            continue
                        }
                        if (truth(jsAnd(node.matches("button:not([type])")) { node.closest("form") })) {
                            node.type = "button"
                        }
                        global_normalizeButtons20(node)
                    }
                }
            }
        }
        return absent
    }))).observe(global_Dollar("#app"), jsonValue("{\"childList\":true,\"subtree\":true}"))
    global_normalizeButtons20(global_document)
    global_state.appVersion = 21
    global_save()
    global_render()
}
