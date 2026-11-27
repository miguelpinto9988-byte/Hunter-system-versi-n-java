@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv15

import hunter.browser.*

@JsName("V15UI") private external var global_V15UI: dynamic
@JsName("ReferenceVideo15") private external var global_ReferenceVideo15: dynamic
@JsName("isReference15") private external var global_isReference15: dynamic
@JsName("videoHTML15") private external var global_videoHTML15: dynamic
@JsName("pauseVideos15") private external var global_pauseVideos15: dynamic
@JsName("bindVideo15") private external var global_bindVideo15: dynamic
@JsName("rate15") private external var global_rate15: dynamic
@JsName("vectorPanel") private external var global_vectorPanel: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("vectorToggle") private external var global_vectorToggle: dynamic
@JsName("vectorSeek") private external var global_vectorSeek: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("toggleGuidedDemo11") private external var global_toggleGuidedDemo11: dynamic
@JsName("animateGuidedVector11") private external var global_animateGuidedVector11: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("openAcademy") private external var global_openAcademy: dynamic
@JsName("pauseGuide11") private external var global_pauseGuide11: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("MotionPlayback") private external var global_MotionPlayback: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("academyObserver15") private external var global_academyObserver15: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV15() {
    global_isReference15 = fun(id: dynamic): dynamic {
        return (id === global_ReferenceVideo15.exerciseId)
    }
    global_videoHTML15 = fun(): dynamic {
        return ("<video id=\"referenceVideo15\" class=\"reference-exercise-video\" src=\"" + textOf(global_ReferenceVideo15.path) + "\" poster=\"media/poster17_reference.webp\" preload=\"metadata\" playsinline muted loop controls aria-label=\"Video original: elevación frontal con mancuernas sentado\"></video>")
    }
    global_pauseVideos15 = fun(arg8: dynamic): dynamic {
        var scope: dynamic = absent
        scope = definedOr(arg8) { global_document }
        scope.querySelectorAll("video").forEach((fun(v: dynamic): dynamic {
            v.pause()
            return absent
        }))
        return absent
    }
    global_bindVideo15 = fun(video: dynamic, arg11: dynamic): dynamic {
        var guide: dynamic = absent
        guide = definedOr(arg11) { false }
        if (truth(jsOr((!truth(video))) { (video.dataset.bound === "true") })) {
            return absent
        }
        video.dataset.bound = "true"
        video.muted = true
        video.defaultPlaybackRate = global_MotionPlayback.rate
        video.playbackRate = global_MotionPlayback.rate
        video.addEventListener("loadedmetadata", (fun(): dynamic {
            video.defaultPlaybackRate = global_MotionPlayback.rate
            video.playbackRate = global_MotionPlayback.rate
            if (truth(jsAnd(jsAnd((!truth(guide))) { global_vectorSession }) { global_Number.isFinite(video.duration) })) {
                video.currentTime = jsMultiply(jsDivide(global_vectorSession.frame, 48), video.duration)
            }
            return absent
        }))
        if (truth((!truth(guide)))) {
            val update: dynamic = (fun(): dynamic {
                if (truth(jsOr((!truth(global_vectorSession))) { (!truth(global_isReference15(global_vectorSession.id))) })) {
                    return absent
                }
                if (truth(jsAnd(global_Number.isFinite(video.duration)) { jsMore(video.duration, 0) })) {
                    global_vectorSession.frame = jsMultiply(jsDivide(video.currentTime, video.duration), 48)
                }
                val range: dynamic = global_Dollar("#vectorRange")
                if (truth(range)) {
                    range["value"] = global_Math.min(47, global_Math.floor(global_vectorSession.frame))
                }
                val phase: dynamic = global_Dollar("#vectorPhase")
                if (truth(phase)) {
                    phase.textContent = "Video original · elevación frontal sentado"
                }
                return absent
            })
            video.addEventListener("timeupdate", update)
            video.addEventListener("play", (fun(): dynamic {
                if (truth(global_vectorSession)) {
                    global_vectorSession.playing = true
                }
                val b: dynamic = global_Dollar("#vectorPlay")
                if (truth(b)) {
                    b.textContent = "Pausar"
                }
                return absent
            }))
            video.addEventListener("pause", (fun(): dynamic {
                if (truth(global_vectorSession)) {
                    global_vectorSession.playing = false
                }
                val b: dynamic = global_Dollar("#vectorPlay")
                if (truth(b)) {
                    b.textContent = "Reproducir"
                }
                return absent
            }))
            video.addEventListener("seeked", update)
        }
        video.addEventListener("error", (fun(): dynamic {
            val p: dynamic = global_document.createElement("p")
            p.className = "notice"
            p.textContent = "No se pudo reproducir el clip en este dispositivo. Reabre la demostración o revisa las instrucciones de la ficha."
            if (truth((!truth(onPresent(video.nextElementSibling) { value44 -> value44.classList.contains("notice") })))) {
                video.after(p)
            }
            return absent
        }))
        return absent
    }
    global_V15UI = jsObject { obj1 ->
        obj1["vectorPanel"] = global_vectorPanel
        obj1["vectorPaint"] = global_vectorPaint
        obj1["vectorToggle"] = global_vectorToggle
        obj1["vectorSeek"] = global_vectorSeek
        obj1["drawVector"] = global_drawVector
        obj1["paintGuide11"] = global_paintGuide11
        obj1["toggleGuidedDemo11"] = global_toggleGuidedDemo11
        obj1["animateGuidedVector11"] = global_animateGuidedVector11
        obj1["modal"] = global_modal
        obj1["render"] = global_render
        obj1["openAcademy"] = global_openAcademy
        obj1["pauseGuide11"] = global_pauseGuide11
        obj1["answerCoach"] = global_answerCoach
    }
    global_ReferenceVideo15 = jsonValue("{\"exerciseId\":\"front-seated-reference\",\"path\":\"media/reference_front_seated.mp4\",\"duration\":10.005,\"source\":\"archivo adjunto del usuario\",\"original\":true}")
    global_rate15 = global_MotionPlayback["set"]
    global_MotionPlayback["set"] = (fun(rate: dynamic): dynamic {
        val ok: dynamic = global_rate15(rate)
        if (truth(ok)) {
            global_document.querySelectorAll("video").forEach((fun(v: dynamic): dynamic {
                return run {
                    val target50: dynamic = v
                    val assigned52: dynamic = global_Number(rate)
                    target50.playbackRate = assigned52
                    assigned52
                }
            }))
        }
        return ok
    })
    global_vectorPanel = (fun(e: dynamic): dynamic {
        if (truth((!truth(global_isReference15(e.id))))) {
            val box: dynamic = global_document.createElement("div")
            box.innerHTML = global_V15UI.vectorPanel(e)
            box.querySelector(".eyebrow").textContent = "DEMOSTRACIÓN ANATÓMICA 3D · SIN CONEXIÓN"
            val p: dynamic = global_document.createElement("p")
            p.className = "anatomy-region-label"
            p.textContent = jsOr(e.muscles) { e.group }
            box.querySelector("canvas").before(p)
            box.querySelector("details").innerHTML = "<summary>Sobre la demostración</summary><p>Recreación propia basada en el estilo de la referencia: figura gris, pantalón negro y músculos resaltados. No es el modelo original del video ni una validación anatómica o clínica. Las variantes comparten el movimiento base.</p><p>La velocidad y la cámara solo afectan a la demostración.</p>"
            return box.innerHTML
        }
        return ("<section class=\"demo-card model-3d-card\"><span class=\"eyebrow\">TU VIDEO ORIGINAL · SIN CONEXIÓN</span>" + textOf(global_videoHTML15()) + "<canvas id=\"vectorCanvas\" width=\"540\" height=\"600\" hidden aria-hidden=\"true\"></canvas><p id=\"vectorPhase\">Video original · elevación frontal sentado</p><label>Posición del video<input id=\"vectorRange\" type=\"range\" min=\"0\" max=\"47\" value=\"0\" oninput=\"vectorSeek(this.value)\"></label><button id=\"vectorPlay\" class=\"btn primary full\" onclick=\"vectorToggle()\">Reproducir</button>" + textOf(global_MotionPlayback.controls()) + "<p>" + textOf(global_esc(e.cue)) + "</p><small>El archivo se conserva sin recortar, recolorear ni sustituir el personaje. Su audio empieza silenciado; puedes activarlo desde el reproductor.</small></section>")
    })
    global_drawVector = (fun(canvas: dynamic, id: dynamic, arg67: dynamic, arg68: dynamic): dynamic {
        var phase: dynamic = absent
        phase = definedOr(arg67) { 0 }
        var mirror: dynamic = absent
        mirror = definedOr(arg68) { false }
        if (truth(jsAnd(jsAnd(global_isReference15(id)) { (onPresent(canvas) { value69 -> value69.dataset.thumbnail } !== "true") }) { global_Dollar("#referenceVideo15") })) {
            canvas.dataset.renderer = "original-video"
            global_bindVideo15(global_Dollar("#referenceVideo15"), (canvas.id === "guidedCanvas"))
            return global_MotionDB[id]
        }
        return global_V15UI.drawVector(canvas, id, phase, mirror)
    })
    global_vectorPaint = (fun(): dynamic {
        if (truth((!truth(global_isReference15(onPresent(global_vectorSession) { value75 -> value75.id }))))) {
            return global_V15UI.vectorPaint()
        }
        val video: dynamic = global_Dollar("#referenceVideo15")
        if (truth((!truth(video)))) {
            return absent
        }
        global_bindVideo15(video)
        if (truth(jsAnd((!truth(global_vectorSession.playing))) { global_Number.isFinite(video.duration) })) {
            video.currentTime = global_Math.min(jsSubtract(video.duration, 0.05), global_Math.max(0, jsMultiply(jsDivide(global_vectorSession.frame, 48), video.duration)))
        }
        if (truth(global_Dollar("#vectorRange"))) {
            global_Dollar("#vectorRange")["value"] = global_Math.floor(global_vectorSession.frame)
        }
        return absent
    })
    global_vectorToggle = (fun(): dynamic {
        if (truth((!truth(global_isReference15(onPresent(global_vectorSession) { value82 -> value82.id }))))) {
            return global_V15UI.vectorToggle()
        }
        val v: dynamic = global_Dollar("#referenceVideo15")
        if (truth((!truth(v)))) {
            return absent
        }
        if (truth(v.paused)) {
            v.play()["catch"]((fun(): dynamic {
                return global_toast("Toca el reproductor para iniciar el video.")
            }))
        } else {
            v.pause()
        }
        return absent
    })
    global_vectorSeek = (fun(`value`: dynamic): dynamic {
        if (truth((!truth(global_isReference15(onPresent(global_vectorSession) { value85 -> value85.id }))))) {
            return global_V15UI.vectorSeek(`value`)
        }
        val v: dynamic = global_Dollar("#referenceVideo15")
        if (truth(v)) {
            v.pause()
        }
        global_vectorSession.playing = false
        global_vectorSession.frame = global_Math.max(0, global_Math.min(47, jsOr(global_Number(`value`)) { 0 }))
        global_vectorPaint()
        return absent
    })
    global_paintGuide11 = (fun(): dynamic {
        global_pauseVideos15(jsOr(global_Dollar("#guidedSlot")) { global_document })
        global_V15UI.paintGuide11()
        val c: dynamic = global_Dollar("#guidedCanvas")
        val step: dynamic = onPresent(global_guide11) { value92 -> value92.steps[global_guide11.index] }
        if (truth(jsOr((!truth(c))) { (onPresent(step) { value94 -> value94.kind } !== "exercise") })) {
            return absent
        }
        val details: dynamic = global_Dollar("#guidedSlot details")
        if (truth(details)) {
            details.innerHTML = "<summary>Sobre la demostración</summary><p>Figura 3D recreada a partir del estilo visual de tu referencia. Se conservan las trayectorias ilustrativas del catálogo; no evalúa tu postura ni diagnostica lesiones.</p>"
        }
        if (truth(global_isReference15(step.id))) {
            c.hidden = true
            c.insertAdjacentHTML("beforebegin", global_videoHTML15())
            onPresent(global_Dollar("#guidedSlot .model-camera")) { value99 -> value99.remove() }
            val v: dynamic = global_Dollar("#referenceVideo15")
            global_bindVideo15(v, true)
            if (truth(details)) {
                details.innerHTML = "<summary>Sobre la demostración</summary><p>Este es el clip original que proporcionaste, asignado a la elevación frontal sentada. El tiempo del video es independiente del reloj de la serie.</p>"
            }
            if (truth(global_guide11.demoPlaying)) {
                v.play()["catch"]((fun(): dynamic {
                    global_guide11.demoPlaying = false
                    global_Dollar("#guidedDemoToggle").textContent = "Reproducir demostración"
                    return absent
                }))
            }
        }
        return absent
    })
    global_animateGuidedVector11 = (fun(token: dynamic): dynamic {
        if (truth(global_isReference15(onPresent(global_guide11) { value108 -> onPresent(value108.steps[global_guide11.index]) { value110 -> value110.id } }))) {
            return absent
        }
        global_V15UI.animateGuidedVector11(token)
        return absent
    })
    global_toggleGuidedDemo11 = (fun(): dynamic {
        if (truth((!truth(global_isReference15(onPresent(global_guide11) { value113 -> onPresent(value113.steps[global_guide11.index]) { value115 -> value115.id } }))))) {
            return global_V15UI.toggleGuidedDemo11()
        }
        val v: dynamic = global_Dollar("#referenceVideo15")
        if (truth((!truth(v)))) {
            return absent
        }
        global_guide11.demoPlaying = (!truth(global_guide11.demoPlaying))
        if (truth(global_guide11.demoPlaying)) {
            v.play()["catch"]((fun(): dynamic {
                global_guide11.demoPlaying = false
                return absent
            }))
        } else {
            v.pause()
        }
        global_Dollar("#guidedDemoToggle").textContent = (if (truth(global_guide11.demoPlaying)) "Pausar demostración" else "Reproducir demostración")
        return absent
    })
    global_pauseGuide11 = (fun(arg124: dynamic): dynamic {
        var persist: dynamic = absent
        persist = definedOr(arg124) { true }
        global_pauseVideos15()
        return global_V15UI.pauseGuide11(persist)
    })
    global_modal = (fun(html: dynamic): dynamic {
        onPresent(global_academyObserver15) { value127 -> value127.disconnect() }
        global_pauseVideos15()
        return global_V15UI.modal(html)
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_pauseVideos15()
        return global_V15UI.render()
    })
    global_openAcademy = (fun(): dynamic {
        global_V15UI.openAcademy()
        val top: dynamic = global_Dollar("#modal .demo-gallery")
        if (truth(top)) {
            top.insertAdjacentHTML("beforebegin", ("<button class=\"btn primary full\" onclick=\"technique('" + textOf(global_ReferenceVideo15.exerciseId) + "')\">Ver el video original incluido</button>"))
        }
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("animaci[oó]n|velocidad|modelo.*3d|visor|demostraci[oó]n|3d", "i")).test(q))) {
            return ("" + textOf(global_companionDisplayName()) + ": Hay " + textOf(global_E.exercises.length) + " fichas con demostración local. La elevación frontal sentada reproduce tu clip original; las demás usan un modelo gris recreado, con músculos resaltados y cámara giratoria. Hay tres velocidades: 0,5×, 1× y 1,5×. La velocidad cambia la demostración; las series, repeticiones y el reloj de la sesión se mantienen. Mi avatar es una ilustración 2D. Estas demostraciones no evalúan tu técnica.")
        }
        return global_V15UI.answerCoach(q)
    })
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        if (truth(global_document.hidden)) {
            global_pauseVideos15()
        }
        return absent
    }))
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth((!truth(global_Dollar("#modal")["open"])))) {
            onPresent(global_academyObserver15) { value134 -> value134.disconnect() }
            global_pauseVideos15()
        }
        return absent
    }))
    global_state.appVersion = 15
    global_save()
    global_render()
}
