@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv27

import hunter.browser.*

@JsName("V27UI") private external var global_V27UI: dynamic
@JsName("Scenes27") private external var global_Scenes27: dynamic
@JsName("TimerAlarm27") private external var global_TimerAlarm27: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("toggleGuideClock11") private external var global_toggleGuideClock11: dynamic
@JsName("pauseGuide11") private external var global_pauseGuide11: dynamic
@JsName("startTimer") private external var global_startTimer: dynamic
@JsName("startExerciseTimer") private external var global_startExerciseTimer: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("ThemeHues") private external var global_ThemeHues: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("themeNative20") private external var global_themeNative20: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Audio") private external var global_Audio: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("CustomEvent") private external var global_CustomEvent: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("guideValid11") private external var global_guideValid11: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("timerEnd") private external var global_timerEnd: dynamic
@JsName("workEnd") private external var global_workEnd: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV27() {
    global_V27UI = jsObject { obj1 ->
        obj1["applyTheme"] = global_applyTheme
        obj1["home"] = global_home
        obj1["render"] = global_render
        obj1["modal"] = global_modal
        obj1["paintGuide11"] = global_paintGuide11
        obj1["toggleGuideClock11"] = global_toggleGuideClock11
        obj1["pauseGuide11"] = global_pauseGuide11
        obj1["startTimer"] = global_startTimer
        obj1["startExerciseTimer"] = global_startExerciseTimer
    }
    global_Scenes27 = global_Object.freeze(global_Object.fromEntries(global_Object.keys(global_ThemeHues).map((fun(k: dynamic): dynamic {
        return arrayOf<dynamic>(k, "ui33/scene-e-0.webp")
    }))))
    global_applyTheme = (fun(arg4: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg4) { jsOr(global_state.profile) { js("({})") } }
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V27UI.applyTheme(p)
        val root: dynamic = global_document.documentElement
        val key: dynamic = root.dataset.theme
        val h: dynamic = nullableOr(global_ThemeHues[key]) { global_ThemeHues.blue }
        val values: dynamic = jsObject { obj5 ->
            obj5["--bg"] = ("hsl(" + textOf(h) + " 28% 8%)")
            obj5["--panel"] = ("hsl(" + textOf(h) + " 28% 12%)")
            obj5["--surface"] = ("hsl(" + textOf(h) + " 26% 16%)")
            obj5["--input"] = ("hsl(" + textOf(h) + " 25% 10%)")
            obj5["--line"] = ("hsl(" + textOf(h) + " 22% 34%)")
            obj5["--text"] = ("hsl(" + textOf(h) + " 14% 84%)")
            obj5["--muted"] = ("hsl(" + textOf(h) + " 13% 69%)")
            obj5["--accent"] = ("hsl(" + textOf(h) + " 62% 73%)")
            obj5["--cyan"] = ("hsl(" + textOf(h) + " 62% 73%)")
            obj5["--violet"] = ("hsl(" + textOf(jsMod(jsAdd(h, 30), 360)) + " 35% 72%)")
            obj5["--glow"] = ("hsla(" + textOf(h) + ",55%,60%,.09)")
            obj5["--ornament26"] = ("hsl(" + textOf(jsMod(jsAdd(h, 20), 360)) + " 25% 49%)")
            obj5["--metal26"] = ("hsl(" + textOf(jsMod(jsAdd(h, 18), 360)) + " 22% 28%)")
            obj5["--scene27"] = ("url(\"" + textOf(jsOr(global_Scenes27[key]) { global_Scenes27.blue }) + "\")")
        }
        global_Object.entries(values).forEach((fun(arg6: dynamic): dynamic {
            var k: dynamic = absent
            var v: dynamic = absent
            val unpacked7: dynamic = fromIterable(arg6)
            k = unpacked7[0]
            v = unpacked7[1]
            return root.style.setProperty(k, v)
        }))
        global_document.querySelector("meta[name=theme-color]").content = values["--bg"]
        global_themeNative20()
        if (true) {
            val iterator10: dynamic = iteratorOf(global_document.querySelectorAll(".hero-art26 img"))
            while (true) {
                val next11: dynamic = iterator10.next()
                if (truth(next11.done)) break
                var img: dynamic = absent
                img = next11.value
                img.src = jsOr(global_Scenes27[key]) { global_Scenes27.blue }
            }
        }
        return absent
    })
    global_home = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V27UI.home()
        val img: dynamic = box.querySelector(".hero-art26 img")
        if (truth(img)) {
            img.src = jsOr(global_Scenes27[global_document.documentElement.dataset.theme]) { global_Scenes27.blue }
            img.setAttribute("decoding", "async")
        }
        return box.innerHTML
    })
    global_TimerAlarm27 = jsObject { obj20 ->
        obj20["guide"] = dynamicValue(null)
        obj20["rest"] = dynamicValue(null)
        obj20["interval"] = dynamicValue(null)
        obj20["context"] = dynamicValue(null)
        obj20["nodes"] = js("[]")
        obj20["audio"] = dynamicValue(null)
        obj20["native"] = fun(): dynamic {
            return (jsTypeOf(onPresent(global_window.Android) { value23 -> value23.playTimerAlarm }) === "function")
        }
        obj20["unlock"] = fun(): dynamic {
            val receiver24: dynamic = js("this")
            if (truth(receiver24.native())) {
                return absent
            }
            try {
                val AC: dynamic = jsOr(global_window.AudioContext) { global_window.webkitAudioContext }
                if (truth(AC)) {
                    if (truth(jsOr((!truth(receiver24.context))) { (receiver24.context.state === "closed") })) {
                        receiver24.context = jsConstruct(AC, js("[]"))
                    }
                    if (truth((receiver24.context.state === "suspended"))) {
                        receiver24.context.resume()["catch"]((fun(): dynamic {

                            return absent
                        }))
                    }
                } else {
                    if (truth((!truth(receiver24.audio)))) {
                        receiver24.audio = jsConstruct(global_Audio, arrayOf<dynamic>("audio/timer-finished.wav"))
                        receiver24.audio.preload = "auto"
                    }
                }
            } catch (e: dynamic) {

            }
            return absent
        }
        obj20["stop"] = fun(): dynamic {
            val receiver32: dynamic = js("this")
            if (true) {
                val iterator34: dynamic = iteratorOf(receiver32.nodes)
                while (true) {
                    val next35: dynamic = iterator34.next()
                    if (truth(next35.done)) break
                    var n: dynamic = absent
                    n = next35.value
                    try {
                        n.stop()
                    } catch (e: dynamic) {

                    }
                }
            }
            receiver32.nodes = js("[]")
            if (truth(receiver32.audio)) {
                receiver32.audio.pause()
                receiver32.audio.currentTime = 0
            }
            return absent
        }
        obj20["play"] = fun(): dynamic {
            val receiver40: dynamic = js("this")
            if (truth(global_document.hidden)) {
                return false
            }
            try {
                if (truth(receiver40.native())) {
                    global_window.Android.playTimerAlarm()
                    return true
                }
            } catch (e: dynamic) {

            }
            receiver40.unlock()
            receiver40.stop()
            if (truth((onPresent(receiver40.context) { value42 -> value42.state } === "running"))) {
                val c: dynamic = receiver40.context
                val t: dynamic = jsAdd(c.currentTime, 0.015)
                if (true) {
                    val iterator43: dynamic = iteratorOf(jsonValue("[[0,659.25],[0.26,880],[0.52,659.25],[0.78,880]]"))
                    while (true) {
                        val next44: dynamic = iterator43.next()
                        if (truth(next44.done)) break
                        var offset: dynamic = absent
                        var frequency: dynamic = absent
                        val unpacked45: dynamic = fromIterable(next44.value)
                        offset = unpacked45[0]
                        frequency = unpacked45[1]
                        val o: dynamic = c.createOscillator()
                        val g: dynamic = c.createGain()
                        val a: dynamic = jsAdd(t, offset)
                        val b: dynamic = jsAdd(a, 0.21)
                        o.type = "sine"
                        o.frequency["value"] = frequency
                        g.gain.setValueAtTime(0, a)
                        g.gain.linearRampToValueAtTime(0.32, jsAdd(a, 0.008))
                        g.gain.setValueAtTime(0.32, jsSubtract(b, 0.04))
                        g.gain.linearRampToValueAtTime(0, b)
                        o.connect(g)
                        g.connect(c.destination)
                        o.onended = (fun(): dynamic {
                            o.disconnect()
                            g.disconnect()
                            receiver40.nodes = receiver40.nodes.filter((fun(n: dynamic): dynamic {
                                return (n !== o)
                            }))
                            return absent
                        })
                        receiver40.nodes.push(o)
                        o.start(a)
                        o.stop(b)
                    }
                }
                return true
            }
            if (truth(receiver40.audio)) {
                receiver40.audio.currentTime = 0
                receiver40.audio.play()["catch"]((fun(): dynamic {
                    return global_toast("Activa el volumen multimedia para escuchar el aviso.")
                }))
                return true
            }
            global_toast("No se pudo reproducir el aviso del reloj.")
            return false
        }
        obj20["emit"] = fun(kind: dynamic): dynamic {
            val receiver56: dynamic = js("this")
            val played: dynamic = receiver56.play()
            global_window.dispatchEvent(jsConstruct(global_CustomEvent, arrayOf<dynamic>("hunter:timer-finished", jsObject { obj58 ->
                obj58["detail"] = jsObject { obj59 ->
                    obj59["kind"] = kind
                    obj59["played"] = played
                }
            })))
            return absent
        }
        obj20["finish"] = fun(kind: dynamic): dynamic {
            val receiver60: dynamic = js("this")
            val a: dynamic = receiver60[kind]
            if (truth(jsOr((!truth(a))) { jsLess(global_Date.now(), a.deadline) })) {
                return absent
            }
            receiver60[kind] = dynamicValue(null)
            if (truth(global_document.hidden)) {
                return absent
            }
            if (truth(jsAnd((kind === "guide")) { jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((global_guide11 !== a.owner)) { (global_guide11.index !== a.index) }) { (global_guide11.remaining !== 0) }) { global_guide11.running }) { (!truth(global_Dollar("#modal")["open"])) }) { (!truth(global_Dollar("#guidedSlot"))) }) { (!truth(global_guideValid11())) } })) {
                return absent
            }
            if (truth(jsAnd((kind === "interval")) { jsOr((!truth(global_Dollar("#modal")["open"]))) { (!truth(global_Dollar("#workClock"))) } })) {
                return absent
            }
            receiver60.emit(kind)
            return absent
        }
        obj20["test"] = fun(): dynamic {
            val receiver64: dynamic = js("this")
            receiver64.unlock()
            receiver64.emit("test")
            return absent
        }
    }
    global_toggleGuideClock11 = (fun(): dynamic {
        global_TimerAlarm27.unlock()
        global_V27UI.toggleGuideClock11()
        val g: dynamic = global_guide11
        global_TimerAlarm27.guide = (if (truth(jsAnd(onPresent(g) { value70 -> value70.running }) { jsMore(g.remaining, 0) })) jsObject { obj71 ->
            obj71["owner"] = g
            obj71["index"] = g.index
            obj71["deadline"] = g.deadline
        } else null)
        return absent
    })
    global_pauseGuide11 = (fun(arg74: dynamic): dynamic {
        var persist: dynamic = absent
        persist = definedOr(arg74) { true }
        global_TimerAlarm27.guide = dynamicValue(null)
        return global_V27UI.pauseGuide11(persist)
    })
    global_startTimer = (fun(s: dynamic): dynamic {
        global_TimerAlarm27.unlock()
        global_TimerAlarm27.rest = dynamicValue(null)
        global_V27UI.startTimer(s)
        if (truth(jsAnd(jsMore(global_Number(s), 0)) { jsMore(global_timerEnd, global_Date.now()) })) {
            global_TimerAlarm27.rest = jsObject { obj83 ->
                obj83["deadline"] = global_timerEnd
            }
        }
        return absent
    })
    global_startExerciseTimer = (fun(i: dynamic): dynamic {
        global_TimerAlarm27.unlock()
        global_TimerAlarm27.interval = dynamicValue(null)
        global_V27UI.startExerciseTimer(i)
        if (truth(jsMore(global_workEnd, global_Date.now()))) {
            global_TimerAlarm27.interval = jsObject { obj90 ->
                obj90["deadline"] = global_workEnd
            }
        }
        return absent
    })
    global_paintGuide11 = (fun(): dynamic {
        global_V27UI.paintGuide11()
        val clock: dynamic = global_Dollar("#guidedSlot .guided-clock")
        if (truth(jsAnd(clock) { (!truth(clock.querySelector("[data-alarm-note27]"))) })) {
            clock.insertAdjacentHTML("beforeend", "<small data-alarm-note27>Aviso sonoro al terminar · volumen multimedia.</small>")
        }
        return absent
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_V27UI.modal(html)
        if (truth(jsAnd(global_TimerAlarm27.interval) { (!truth(global_Dollar("#workClock"))) })) {
            global_TimerAlarm27.interval = dynamicValue(null)
            global_workEnd = 0
        }
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V27UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target99: dynamic = n
                val assigned101: dynamic = "SISTEMA DE SOMBRAS"
                target99.textContent = assigned101
                assigned101
            }
        }))
        return absent
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth((!truth(global_Dollar("#modal")["open"])))) {
            global_TimerAlarm27.guide = dynamicValue(null)
            global_TimerAlarm27.interval = dynamicValue(null)
            global_workEnd = 0
            global_TimerAlarm27.stop()
        }
        return absent
    }))
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        if (truth(global_document.hidden)) {
            global_TimerAlarm27.guide = dynamicValue(null)
            global_TimerAlarm27.rest = dynamicValue(null)
            global_TimerAlarm27.interval = dynamicValue(null)
            global_timerEnd = 0
            global_workEnd = 0
            global_TimerAlarm27.stop()
        }
        return absent
    }))
    global_window.HunterV27 = jsObject { obj114 ->
        obj114["Scenes27"] = global_Scenes27
        obj114["alarm"] = global_TimerAlarm27
    }
    global_state.appVersion = 27
    global_applyTheme()
    global_save()
    global_render()
}
