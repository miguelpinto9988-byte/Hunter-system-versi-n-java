@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv16

import hunter.browser.*

@JsName("V16UI") private external var global_V16UI: dynamic
@JsName("demoModes16") private external var global_demoModes16: dynamic
@JsName("demoViews16") private external var global_demoViews16: dynamic
@JsName("demoInfo16") private external var global_demoInfo16: dynamic
@JsName("useHD16") private external var global_useHD16: dynamic
@JsName("videoHTML16") private external var global_videoHTML16: dynamic
@JsName("hdViewControls16") private external var global_hdViewControls16: dynamic
@JsName("viewHD16") private external var global_viewHD16: dynamic
@JsName("bindVideo16") private external var global_bindVideo16: dynamic
@JsName("setDemoMode16") private external var global_setDemoMode16: dynamic
@JsName("vectorPanel") private external var global_vectorPanel: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("vectorToggle") private external var global_vectorToggle: dynamic
@JsName("vectorSeek") private external var global_vectorSeek: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("toggleGuidedDemo11") private external var global_toggleGuidedDemo11: dynamic
@JsName("animateGuidedVector11") private external var global_animateGuidedVector11: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("pauseVideos15") private external var global_pauseVideos15: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("HDMotion16") private external var global_HDMotion16: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("MotionPlayback") private external var global_MotionPlayback: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("vectorGeneration13") private external var global_vectorGeneration13: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("guideFrame11") private external var global_guideFrame11: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV16() {
    global_demoInfo16 = fun(id: dynamic): dynamic {
        val e: dynamic = global_E.byId(id)
        return (if (truth(e)) jsOr(global_HDMotion16[jsOr(e.baseId) { id }]) { null } else null)
    }
    global_useHD16 = fun(id: dynamic): dynamic {
        return jsAnd((!truth((!truth(global_demoInfo16(id)))))) { jsOr(global_demoInfo16(id).suppliedModel) { (global_demoModes16["get"](id) !== "3d") } }
    }
    global_videoHTML16 = fun(id: dynamic, arg15: dynamic, arg16: dynamic): dynamic {
        var guide: dynamic = absent
        guide = definedOr(arg15) { false }
        var mirror: dynamic = absent
        mirror = definedOr(arg16) { false }
        val d: dynamic = global_demoInfo16(id)
        val e: dynamic = global_E.byId(id)
        val view: dynamic = (if (truth(jsAnd(d.sidePath) { (global_demoViews16["get"](id) === "side") })) "side" else "default")
        val side: dynamic = (view === "side")
        val poster: dynamic = (if (truth(side)) d.sidePoster else d.poster)
        val width: dynamic = (if (truth(side)) jsOr(d.sideWidth) { d.width } else d.width)
        val height: dynamic = (if (truth(side)) jsOr(d.sideHeight) { d.height } else d.height)
        return ("<video id=\"exerciseVideo16\" class=\"hd-exercise-video" + textOf((if (truth(mirror)) " hd-mirror" else "")) + "\" data-exercise=\"" + textOf(global_esc(id)) + "\" data-view=\"" + textOf(view) + "\" src=\"" + textOf((if (truth(side)) d.sidePath else d.path)) + "\" " + textOf((if (truth(poster)) ("poster=\"" + textOf(poster) + "\"") else "")) + " style=\"aspect-ratio:" + textOf(width) + "/" + textOf(height) + "\" preload=\"metadata\" playsinline muted loop " + textOf((if (truth(guide)) "" else "controls")) + " aria-label=\"Demostración local de " + textOf(global_esc(e.name)) + "\"></video>")
    }
    global_hdViewControls16 = fun(id: dynamic): dynamic {
        if (truth((!truth(onPresent(global_demoInfo16(id)) { value19 -> value19.sidePath })))) {
            return ""
        }
        val view: dynamic = jsOr(global_demoViews16["get"](id)) { "default" }
        return ("<fieldset class=\"playback-speeds hd-view-options\"><legend>Vista de la animación HD</legend><div class=\"row wrap\">" + textOf(dynamicValue(jsonValue("[[\"default\",\"Tres cuartos\"],[\"side\",\"Lateral\"]]")).map((fun(arg22: dynamic): dynamic {
            var key: dynamic = absent
            var label: dynamic = absent
            val unpacked23: dynamic = fromIterable(arg22)
            key = unpacked23[0]
            label = unpacked23[1]
            return ("<button class=\"btn\" data-hd-view=\"" + textOf(key) + "\" aria-pressed=\"" + textOf((view === key)) + "\" onclick=\"viewHD16('" + textOf(key) + "')\">" + textOf(label) + "</button>")
        })).join("")) + "</div></fieldset>")
    }
    global_viewHD16 = fun(view: dynamic): dynamic {
        val v: dynamic = global_Dollar("#exerciseVideo16")
        val d: dynamic = global_demoInfo16(onPresent(v) { value26 -> value26.dataset.exercise })
        if (truth(jsOr(jsOr(jsOr((!truth(v))) { (!truth(onPresent(d) { value28 -> value28.sidePath })) }) { (!truth(dynamicValue(jsonValue("[\"default\",\"side\"]")).includes(view))) }) { (v.dataset.view === view) })) {
            return absent
        }
        val id: dynamic = v.dataset.exercise
        val guide: dynamic = (!truth((!truth(v.closest("#guidedSlot")))))
        val session: dynamic = (if (truth(guide)) global_guide11 else global_vectorSession)
        val index: dynamic = (if (truth(guide)) global_guide11.index else null)
        val playing: dynamic = (!truth(v.paused))
        val phase: dynamic = (if (truth(jsAnd(global_Number.isFinite(v.duration)) { jsMore(v.duration, 0) })) jsDivide(v.currentTime, v.duration) else jsDivide((if (truth(guide)) global_guide11.demoFrame else global_vectorSession.frame), 48))
        v.pause()
        if (truth(v["__restore16"])) {
            v.removeEventListener("loadedmetadata", v["__restore16"])
        }
        val token: dynamic = run {
            val target29: dynamic = v
            val assigned31: dynamic = jsAdd(jsOr(v["__viewToken16"]) { 0 }, 1)
            target29["__viewToken16"] = assigned31
            assigned31
        }
        global_demoViews16["set"](id, view)
        v.dataset.view = view
        v.poster = jsOr((if (truth((view === "side"))) d.sidePoster else d.poster)) { "" }
        v.style.aspectRatio = jsAdd(jsAdd((if (truth((view === "side"))) jsOr(d.sideWidth) { d.width } else d.width), " / "), (if (truth((view === "side"))) jsOr(d.sideHeight) { d.height } else d.height))
        if (truth((!truth(guide)))) {
            global_vectorSession.frame = jsMultiply(phase, 48)
        }
        v["__restore16"] = (fun(): dynamic {
            if (truth(jsOr(jsOr(jsOr(jsOr((token !== v["__viewToken16"])) { (v.dataset.failed === "true") }) { (!truth(v.isConnected)) }) { (!truth(global_Dollar("#modal")["open"])) }) { (if (truth(guide)) jsOr((global_guide11 !== session)) { (global_guide11.index !== index) } else (onPresent(global_vectorSession) { value42 -> value42.id } !== id)) })) {
                return absent
            }
            if (truth(global_Number.isFinite(v.duration))) {
                v.currentTime = global_Math.max(0, global_Math.min(jsSubtract(v.duration, 0.01), jsMultiply(phase, v.duration)))
            }
            v["__restore16"] = dynamicValue(null)
            if (truth(playing)) {
                v.play()["catch"]((fun(): dynamic {
                    return global_toast("Toca Reproducir para iniciar la nueva vista.")
                }))
            }
            return absent
        })
        v.addEventListener("loadedmetadata", v["__restore16"], jsonValue("{\"once\":true}"))
        v.src = (if (truth((view === "side"))) d.sidePath else d.path)
        global_document.querySelectorAll("[data-hd-view]").forEach((fun(b: dynamic): dynamic {
            return b.setAttribute("aria-pressed", global_String((b.dataset.hdView === view)))
        }))
        return absent
    }
    global_bindVideo16 = fun(v: dynamic, arg51: dynamic): dynamic {
        var guide: dynamic = absent
        guide = definedOr(arg51) { false }
        if (truth(jsOr((!truth(v))) { (v.dataset.bound === "true") })) {
            return absent
        }
        v.dataset.bound = "true"
        v.muted = true
        v.defaultPlaybackRate = global_MotionPlayback.rate
        v.playbackRate = global_MotionPlayback.rate
        val id: dynamic = v.dataset.exercise
        val session: dynamic = (if (truth(guide)) global_guide11 else global_vectorSession)
        val index: dynamic = (if (truth(guide)) global_guide11.index else null)
        val active: dynamic = (fun(): dynamic {
            return jsAnd(v.isConnected) { (if (truth(guide)) jsAnd((global_guide11 === session)) { (global_guide11.index === index) } else (onPresent(global_vectorSession) { value60 -> value60.id } === id)) }
        })
        val paint: dynamic = (fun(): dynamic {
            if (truth((!truth(active())))) {
                return absent
            }
            if (truth(guide)) {
                global_guide11.demoFrame = (if (truth(jsAnd(global_Number.isFinite(v.duration)) { jsMore(v.duration, 0) })) jsMultiply(jsDivide(v.currentTime, v.duration), 48) else 0)
                return absent
            }
            if (truth(jsAnd(global_Number.isFinite(v.duration)) { jsMore(v.duration, 0) })) {
                global_vectorSession.frame = jsMultiply(jsDivide(v.currentTime, v.duration), 48)
            }
            val range: dynamic = global_Dollar("#vectorRange")
            if (truth(range)) {
                range["value"] = global_Math.min(47, global_Math.floor(global_vectorSession.frame))
            }
            val phase: dynamic = global_Dollar("#vectorPhase")
            val motion: dynamic = global_MotionDB[jsOr(onPresent(global_E.byId(id)) { value67 -> value67.baseId }) { id }]
            if (truth(phase)) {
                phase.textContent = (if (truth(onPresent(motion) { value70 -> value70.hold })) "Postura sostenida · respira sin forzar" else (if (truth(jsLess(global_vectorSession.frame, 24))) "Movimiento controlado" else "Regreso controlado"))
            }
            return absent
        })
        v.addEventListener("loadedmetadata", (fun(): dynamic {
            if (truth((!truth(active())))) {
                return absent
            }
            v.defaultPlaybackRate = global_MotionPlayback.rate
            v.playbackRate = global_MotionPlayback.rate
            if (truth(jsAnd((!truth(guide))) { global_Number.isFinite(v.duration) })) {
                v.currentTime = jsMultiply(jsDivide(global_vectorSession.frame, 48), v.duration)
            }
            return absent
        }))
        v.addEventListener("timeupdate", paint)
        v.addEventListener("seeked", paint)
        if (true) {
            val iterator77: dynamic = iteratorOf(jsonValue("[\"play\",\"pause\"]"))
            while (true) {
                val next78: dynamic = iterator77.next()
                if (truth(next78.done)) break
                var event: dynamic = absent
                event = next78.value
                v.addEventListener(event, (fun(): dynamic {
                    if (truth((!truth(active())))) {
                        return absent
                    }
                    val playing: dynamic = (event === "play")
                    if (truth(guide)) {
                        global_guide11.demoPlaying = playing
                        val b: dynamic = global_Dollar("#guidedDemoToggle")
                        if (truth(b)) {
                            b.textContent = (if (truth(playing)) "Pausar demostración" else "Reproducir demostración")
                        }
                    } else {
                        global_vectorSession.playing = playing
                        val b: dynamic = global_Dollar("#vectorPlay")
                        if (truth(b)) {
                            b.textContent = (if (truth(playing)) "Pausar" else "Reproducir")
                        }
                    }
                    return absent
                }))
            }
        }
        v.addEventListener("error", (fun(): dynamic {
            if (truth(jsOr((!truth(active()))) { (v.dataset.failed === "true") })) {
                return absent
            }
            v.dataset.failed = "true"
            v.pause()
            val notice: dynamic = global_document.createElement("div")
            notice.className = "notice video-error17"
            val text: dynamic = global_document.createElement("p")
            text.textContent = "No se pudo reproducir el video local. Puedes volver a intentarlo."
            notice.append(text)
            if (truth(onPresent(global_demoInfo16(id)) { value93 -> value93.suppliedModel })) {
                val retry: dynamic = global_document.createElement("button")
                retry.type = "button"
                retry.className = "btn"
                retry.textContent = "Reintentar video"
                retry.addEventListener("click", (fun(): dynamic {
                    if (truth((!truth(active())))) {
                        return absent
                    }
                    v.dataset.failed = "false"
                    notice.remove()
                    v.load()
                    return absent
                }))
                notice.append(retry)
                v.after(notice)
                return absent
            }
            v.after(notice)
            if (truth(guide)) {
                v.hidden = true
                val c: dynamic = global_Dollar("#guidedCanvas")
                if (truth(c)) {
                    c.hidden = false
                    c.dataset.fallback = "true"
                    global_V16UI.drawVector(c, id, 0, (global_guide11.steps[index].side === "left"))
                }
            } else {
                global_setDemoMode16("3d")
            }
            return absent
        }))
        return absent
    }
    global_setDemoMode16 = fun(mode: dynamic): dynamic {
        val s: dynamic = global_vectorSession
        if (truth(jsOr(jsOr(jsOr((!truth(s))) { (!truth(global_demoInfo16(s.id))) }) { (!truth(dynamicValue(jsonValue("[\"hd\",\"3d\"]")).includes(mode))) }) { jsAnd((mode === "3d")) { global_demoInfo16(s.id).suppliedModel } })) {
            return absent
        }
        val saved: dynamic = jsObject { obj110 ->
            obj110["id"] = s.id
            obj110["frame"] = s.frame
            obj110["mirror"] = s.mirror
            obj110["playing"] = s.playing
        }
        global_pauseVideos15()
        run {
            val previous111: dynamic = numeric(global_vectorGeneration13)
            global_vectorGeneration13 = jsAdd(previous111, 1)
        }
        global_demoModes16["set"](saved.id, mode)
        global_technique(saved.id)
        global_vectorSession.frame = saved.frame
        global_vectorSession.mirror = saved.mirror
        global_vectorPaint()
        if (truth(saved.playing)) {
            global_vectorToggle()
        }
        return absent
    }
    global_V16UI = jsObject { obj1 ->
        obj1["vectorPanel"] = global_vectorPanel
        obj1["vectorPaint"] = global_vectorPaint
        obj1["vectorToggle"] = global_vectorToggle
        obj1["vectorSeek"] = global_vectorSeek
        obj1["drawVector"] = global_drawVector
        obj1["paintGuide11"] = global_paintGuide11
        obj1["toggleGuidedDemo11"] = global_toggleGuidedDemo11
        obj1["animateGuidedVector11"] = global_animateGuidedVector11
        obj1["render"] = global_render
        obj1["answerCoach"] = global_answerCoach
        obj1["pauseVideos15"] = global_pauseVideos15
    }
    global_demoModes16 = jsConstruct(global_Map, js("[]"))
    global_demoViews16 = jsConstruct(global_Map, js("[]"))
    global_pauseVideos15 = (fun(arg4: dynamic): dynamic {
        var scope: dynamic = absent
        scope = definedOr(arg4) { global_document }
        scope.querySelectorAll("video").forEach((fun(v: dynamic): dynamic {
            v["__viewToken16"] = jsAdd(jsOr(v["__viewToken16"]) { 0 }, 1)
            if (truth(v["__restore16"])) {
                v.removeEventListener("loadedmetadata", v["__restore16"])
                v["__restore16"] = dynamicValue(null)
            }
            return absent
        }))
        return global_V16UI.pauseVideos15(scope)
    })
    global_vectorPanel = (fun(e: dynamic): dynamic {
        if (truth((!truth(global_demoInfo16(e.id))))) {
            return global_V16UI.vectorPanel(e)
        }
        if (truth((!truth(global_useHD16(e.id))))) {
            return jsAdd(global_V16UI.vectorPanel(e), ("<button class=\"btn full\" onclick=\"setDemoMode16('hd')\">Ver animación HD</button>"))
        }
        return ("<section class=\"demo-card model-3d-card\"><span class=\"eyebrow\">DEMOSTRACIÓN HD · " + textOf(global_demoInfo16(e.id).width) + " × " + textOf(global_demoInfo16(e.id).height) + " · 24 FPS · SIN CONEXIÓN</span>" + textOf(global_videoHTML16(e.id)) + "" + textOf(global_hdViewControls16(e.id)) + "<canvas id=\"vectorCanvas\" width=\"540\" height=\"600\" hidden aria-hidden=\"true\"></canvas><p class=\"anatomy-region-label\">" + textOf(global_esc(jsOr(e.muscles) { e.group })) + "</p><p id=\"vectorPhase\">" + textOf((if (truth(global_MotionDB[jsOr(e.baseId) { e.id }].hold)) "Postura sostenida · respira sin forzar" else "Movimiento y regreso controlados")) + "</p><label>Posición de la demostración<input id=\"vectorRange\" type=\"range\" min=\"0\" max=\"47\" value=\"0\" oninput=\"vectorSeek(this.value)\"></label><button id=\"vectorPlay\" class=\"btn primary full\" onclick=\"vectorToggle()\">Reproducir</button>" + textOf(global_MotionPlayback.controls()) + "" + textOf((if (truth(global_demoInfo16(e.id).suppliedModel)) "" else ("<button class=\"btn full\" onclick=\"setDemoMode16('3d')\">Explorar el modelo 3D y girar la cámara</button>"))) + "<p>" + textOf(global_esc(e.cue)) + "</p><details><summary>Sobre esta demostración</summary><p>" + textOf((if (truth(global_demoInfo16(e.id).suppliedModel)) "Video renderizado con el modelo que aportaste. Conserva su malla y materiales; los movimientos se han recreado para cada ejercicio." else "Animación renderizada localmente.")) + " Las variantes comparten el movimiento base. Las recreaciones no constituyen una validación clínica de la técnica.</p><p>El video y sus tres velocidades son independientes de las series, repeticiones y el reloj de la sesión.</p></details></section>")
    })
    global_drawVector = (fun(c: dynamic, id: dynamic, arg120: dynamic, arg121: dynamic): dynamic {
        var phase: dynamic = absent
        phase = definedOr(arg120) { 0 }
        var mirror: dynamic = absent
        mirror = definedOr(arg121) { false }
        if (truth(jsAnd(jsAnd(jsAnd(jsAnd(c) { (c.dataset.thumbnail !== "true") }) { global_demoInfo16(id) }) { (c.dataset.fallback !== "true") }) { jsOr(jsAnd((c.id === "vectorCanvas")) { global_useHD16(id) }) { (c.id === "guidedCanvas") } })) {
            c.dataset.renderer = "local-hd-video"
            return global_MotionDB[jsOr(global_E.byId(id).baseId) { id }]
        }
        return global_V16UI.drawVector(c, id, phase, mirror)
    })
    global_vectorPaint = (fun(): dynamic {
        if (truth((!truth(global_useHD16(onPresent(global_vectorSession) { value126 -> value126.id }))))) {
            return global_V16UI.vectorPaint()
        }
        val v: dynamic = global_Dollar("#exerciseVideo16")
        if (truth((!truth(v)))) {
            return absent
        }
        global_bindVideo16(v)
        if (truth(jsAnd(jsAnd((!truth(global_vectorSession.playing))) { global_Number.isFinite(v.duration) }) { jsMore(v.duration, 0) })) {
            v.currentTime = global_Math.min(jsSubtract(v.duration, 0.01), global_Math.max(0, jsMultiply(jsDivide(global_vectorSession.frame, 48), v.duration)))
        }
        val range: dynamic = global_Dollar("#vectorRange")
        if (truth(range)) {
            range["value"] = global_Math.floor(global_vectorSession.frame)
        }
        return absent
    })
    global_vectorToggle = (fun(): dynamic {
        if (truth((!truth(global_useHD16(onPresent(global_vectorSession) { value133 -> value133.id }))))) {
            return global_V16UI.vectorToggle()
        }
        val v: dynamic = global_Dollar("#exerciseVideo16")
        if (truth(jsOr((!truth(v))) { (v.dataset.failed === "true") })) {
            return absent
        }
        if (truth(v.paused)) {
            v.play()["catch"]((fun(): dynamic {
                global_vectorSession.playing = false
                global_toast("Toca el reproductor para iniciar la demostración.")
                return absent
            }))
        } else {
            v.pause()
        }
        return absent
    })
    global_vectorSeek = (fun(`value`: dynamic): dynamic {
        if (truth((!truth(global_useHD16(onPresent(global_vectorSession) { value138 -> value138.id }))))) {
            return global_V16UI.vectorSeek(`value`)
        }
        val v: dynamic = global_Dollar("#exerciseVideo16")
        onPresent(v) { value139 -> value139.pause() }
        run {
            val previous140: dynamic = numeric(global_vectorGeneration13)
            global_vectorGeneration13 = jsAdd(previous140, 1)
        }
        global_vectorSession.playing = false
        global_vectorSession.frame = global_Math.max(0, global_Math.min(47, jsOr(global_Number(`value`)) { 0 }))
        global_vectorPaint()
        return absent
    })
    global_paintGuide11 = (fun(): dynamic {
        global_V16UI.paintGuide11()
        val step: dynamic = onPresent(global_guide11) { value147 -> value147.steps[global_guide11.index] }
        val c: dynamic = global_Dollar("#guidedCanvas")
        if (truth(jsOr(jsOr((!truth(c))) { (onPresent(step) { value149 -> value149.kind } !== "exercise") }) { (!truth(global_demoInfo16(step.id))) })) {
            return absent
        }
        c.hidden = true
        c.insertAdjacentHTML("beforebegin", global_videoHTML16(step.id, true, (step.side === "left")))
        onPresent(global_Dollar("#guidedSlot .model-camera")) { value152 -> value152.remove() }
        onPresent(global_Dollar("#guidedSlot .guided-demo")) { value153 -> value153.insertAdjacentHTML("afterend", global_hdViewControls16(step.id)) }
        val v: dynamic = global_Dollar("#exerciseVideo16")
        global_bindVideo16(v, true)
        val details: dynamic = global_Dollar("#guidedSlot details")
        if (truth(details)) {
            details.innerHTML = ("<summary>Sobre la demostración HD</summary><p>" + textOf((if (truth(global_demoInfo16(step.id).suppliedModel)) "Tu modelo renderizado en video local" else "Demostración local")) + " a " + textOf(global_demoInfo16(step.id).width) + " × " + textOf(global_demoInfo16(step.id).height) + " y 24 fotogramas por segundo. Las variantes comparten el movimiento base. El reloj de trabajo es independiente del video. Revisa también las instrucciones y las indicaciones personales.</p>")
        }
        if (truth(global_guide11.demoPlaying)) {
            v.play()["catch"]((fun(): dynamic {
                global_guide11.demoPlaying = false
                val b: dynamic = global_Dollar("#guidedDemoToggle")
                if (truth(b)) {
                    b.textContent = "Reproducir demostración"
                }
                return absent
            }))
        }
        return absent
    })
    global_animateGuidedVector11 = (fun(token: dynamic): dynamic {
        val id: dynamic = onPresent(global_guide11) { value162 -> onPresent(value162.steps[global_guide11.index]) { value164 -> value164.id } }
        if (truth(jsAnd(global_demoInfo16(id)) { (onPresent(global_Dollar("#guidedCanvas")) { value165 -> value165.dataset.fallback } !== "true") })) {
            return absent
        }
        global_V16UI.animateGuidedVector11(token)
        return absent
    })
    global_toggleGuidedDemo11 = (fun(): dynamic {
        val step: dynamic = onPresent(global_guide11) { value169 -> value169.steps[global_guide11.index] }
        val v: dynamic = global_Dollar("#exerciseVideo16")
        if (truth(jsOr(jsOr((onPresent(step) { value171 -> value171.kind } !== "exercise")) { (!truth(global_demoInfo16(step.id))) }) { (!truth(v)) })) {
            return global_V16UI.toggleGuidedDemo11()
        }
        if (truth((v.dataset.failed === "true"))) {
            return absent
        }
        run {
            val previous172: dynamic = numeric(global_guideFrame11)
            global_guideFrame11 = jsAdd(previous172, 1)
        }
        if (truth(v.paused)) {
            v.play()["catch"]((fun(): dynamic {
                global_guide11.demoPlaying = false
                val b: dynamic = global_Dollar("#guidedDemoToggle")
                if (truth(b)) {
                    b.textContent = "Reproducir demostración"
                }
                return absent
            }))
        } else {
            v.pause()
        }
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V16UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(e: dynamic): dynamic {
            return run {
                val target179: dynamic = e
                val assigned181: dynamic = "EL DESPERTAR · V16"
                target179.textContent = assigned181
                assigned181
            }
        }))
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("animaci[oó]n|velocidad|modelo.*3d|visor|demostraci[oó]n|3d", "i")).test(q))) {
            return ("" + textOf(global_companionDisplayName()) + ": Las " + textOf(global_E.exercises.length) + " fichas enlazan a demostraciones locales: " + textOf(global_Object.keys(global_HDMotion16).length) + " movimientos base renderizados en HD y tu clip original de elevación frontal sentada. Las variantes comparten su movimiento base. Puedes usar 0,5×, 1× o 1,5× y explorar la cámara del modelo 3D desde la ficha. Estos controles no cambian la dosis ni el reloj. Mi personaje sigue en 2D. Las animaciones no evalúan tu ejecución.")
        }
        return global_V16UI.answerCoach(q)
    })
    global_state.appVersion = 16
    global_save()
    global_render()
}
