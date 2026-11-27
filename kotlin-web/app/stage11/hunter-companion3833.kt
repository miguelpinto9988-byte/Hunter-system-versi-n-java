@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntercompanion3833

import hunter.browser.*

@JsName("document") private external var global_document: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("clearTimeout") private external var global_clearTimeout: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("ShadowActor33") private external var global_ShadowActor33: dynamic

internal fun installHunterCompanion3833() {
    dynamicValue((fun(): dynamic {
        var modal: dynamic = absent
        var phrases: dynamic = absent
        var names: dynamic = absent
        var active: dynamic = absent
        var phraseTimer: dynamic = absent
        var stopPhraseTimer: dynamic = absent
        var schedulePhrase: dynamic = absent
        var isTabletGuide: dynamic = absent
        var destroy: dynamic = absent
        var typeNow: dynamic = absent
        var stepNow: dynamic = absent
        var refreshPhrase: dynamic = absent
        var sync: dynamic = absent
        stopPhraseTimer = fun(): dynamic {
            global_clearTimeout(phraseTimer)
            phraseTimer = 0
            return absent
        }
        schedulePhrase = fun(): dynamic {
            stopPhraseTimer()
            if (truth(jsOr(jsOr(jsOr(jsOr((!truth(active))) { global_document.hidden }) { global_window.matchMedia("(prefers-reduced-motion: reduce)").matches }) { global_document.documentElement.classList.contains("still") }) { global_document.documentElement.classList.contains("hunter-hidden385") })) {
                return absent
            }
            phraseTimer = global_setTimeout((fun(): dynamic {
                phraseTimer = 0
                if (truth(jsOr((!truth(active))) { (!truth(active.node.isConnected)) })) {
                    return absent
                }
                val list: dynamic = phrases[active.type]
                active.phraseCycle = jsAdd(jsOr(active.phraseCycle) { 0 }, 1)
                active.phrase.textContent = list[jsMod(jsAdd(active.step, active.phraseCycle), list.length)]
                schedulePhrase()
                return absent
            }), 28000)
            return absent
        }
        isTabletGuide = fun(): dynamic {
            return jsAnd(jsAnd(jsAnd(modal["open"]) { modal.classList.contains("hunter-tablet-guide3830") }) { modal.classList.contains("session-landscape35") }) { global_window.matchMedia("(orientation: landscape) and (min-width: 820px) and (min-height: 520px)").matches }
        }
        destroy = fun(): dynamic {
            stopPhraseTimer()
            if (truth((!truth(active)))) {
                return absent
            }
            val old: dynamic = active
            active = dynamicValue(null)
            onPresent(old.actor) { value16 -> value16.destroy() }
            old.node.remove()
            return absent
        }
        typeNow = fun(): dynamic {
            val type: dynamic = (if (truth((js("typeof selectedCompanion") === "function"))) global_selectedCompanion() else "knight")
            return (if (truth(global_Object.hasOwn(phrases, type))) type else "knight")
        }
        stepNow = fun(): dynamic {
            return (if (truth(jsAnd((js("typeof guide11") !== "undefined")) { global_guide11 })) jsOr(global_Number(global_guide11.index)) { 0 } else 0)
        }
        refreshPhrase = fun(): dynamic {
            if (truth((!truth(active)))) {
                return absent
            }
            val step: dynamic = stepNow()
            if (truth((active.step === step))) {
                return absent
            }
            active.step = step
            active.phraseCycle = 0
            val list: dynamic = phrases[active.type]
            active.phrase.textContent = list[jsMod(jsAdd(jsMod(step, list.length), list.length), list.length)]
            return absent
        }
        sync = fun(): dynamic {
            val controls: dynamic = modal.querySelector(".wide-session35 .session-body35 > .session-controls35")
            if (truth(jsOr((!truth(isTabletGuide()))) { (!truth(onPresent(controls) { value31 -> value31.isConnected })) })) {
                destroy()
                return absent
            }
            val type: dynamic = typeNow()
            if (truth(jsAnd(active) { jsOr(jsOr((active.parent !== controls)) { (active.type !== type) }) { (!truth(active.node.isConnected)) } })) {
                destroy()
            }
            if (truth((!truth(active)))) {
                val card: dynamic = global_document.createElement("section")
                card.className = "hunter-guide-companion3833"
                card.setAttribute("aria-label", "Compañero de entrenamiento")
                val figure: dynamic = global_document.createElement("div")
                figure.className = "hunter-guide-actor3833"
                figure.setAttribute("aria-hidden", "true")
                val message: dynamic = global_document.createElement("div")
                message.className = "hunter-guide-message3833"
                val eyebrow: dynamic = global_document.createElement("small")
                eyebrow.textContent = "TU COMPAÑERO"
                val name: dynamic = global_document.createElement("strong")
                name.textContent = names[type]
                val phrase: dynamic = global_document.createElement("p")
                phrase.className = "hunter-guide-phrase3833"
                phrase.setAttribute("role", "note")
                message.append(eyebrow, name, phrase)
                card.append(figure, message)
                controls.prepend(card)
                val actor: dynamic = jsConstruct(global_ShadowActor33, arrayOf<dynamic>(figure, type))
                active = jsObject { obj44 ->
                    obj44["parent"] = controls
                    obj44["node"] = card
                    obj44["actor"] = actor
                    obj44["phrase"] = phrase
                    obj44["step"] = dynamicValue(null)
                    obj44["type"] = type
                }
                refreshPhrase()
                if (truth(jsOr(global_document.hidden) { global_document.documentElement.classList.contains("still") })) {
                    actor.pause()
                } else {
                    run { actor["open"](); absent }
                }
                schedulePhrase()
            } else {
                refreshPhrase()
            }
            return absent
        }
        modal = global_document.getElementById("modal")
        val landscape: dynamic = onPresent(global_window.HunterV35) { value3 -> value3.SessionLandscape35 }
        if (truth(jsOr(jsOr((!truth(modal))) { (!truth(landscape)) }) { (js("typeof ShadowActor33") !== "function") })) {
            return absent
        }
        phrases = jsonValue("{\"knight\":[\"Un paso a la vez. Sigue firme.\",\"Tu constancia construye tu fuerza.\",\"Respira y controla el movimiento.\",\"La técnica importa más que la velocidad.\"],\"warrior\":[\"Firme. Controla cada repetición.\",\"Vas bien. Mantén tu técnica.\",\"Una serie más, a tu ritmo.\",\"Puedes descansar cuando lo necesites.\"],\"bear\":[\"Respira, estamos avanzando.\",\"Descansar también es entrenar.\",\"Sigue a tu ritmo, con calma.\",\"Tu esfuerzo cuenta.\"],\"insect\":[\"¡Adelante! Una misión a la vez.\",\"Tu próximo paso te espera.\",\"Mantén la concentración.\",\"Buen trabajo. Sigue con control.\"],\"scientist\":[\"Cada paso suma a tu progreso.\",\"Aprende el movimiento sin prisa.\",\"Avanza con técnica y atención.\",\"Una repetición controlada vale mucho.\"]}")
        names = jsonValue("{\"knight\":\"IGRIS\",\"warrior\":\"IRON\",\"bear\":\"TANK\",\"insect\":\"BERU\",\"scientist\":\"DOKJA KIM\"}")
        active = dynamicValue(null)
        phraseTimer = 0
        val previousSync: dynamic = landscape.sync.bind(landscape)
        landscape.sync = (fun(): dynamic {
            val result: dynamic = previousSync()
            sync()
            return result
        })
        modal.addEventListener("close", destroy)
        global_window.addEventListener("pagehide", destroy)
        global_document.addEventListener("visibilitychange", (fun(): dynamic {
            if (truth((!truth(active)))) {
                return absent
            }
            if (truth(global_document.hidden)) {
                active.actor.pause()
                stopPhraseTimer()
            } else {
                active.actor.resume()
                schedulePhrase()
            }
            return absent
        }))
        val motionSetting: dynamic = global_window.matchMedia("(prefers-reduced-motion: reduce)")
        run { val owner52: dynamic = motionSetting; onPresent(owner52.addEventListener) { method51 -> invokeWith(method51, owner52, arrayOf<dynamic>("change", (fun(): dynamic {
            if (truth((!truth(active)))) {
                return absent
            }
            if (truth(motionSetting.matches)) {
                active.actor.pause()
                stopPhraseTimer()
            } else {
                active.actor.resume()
                schedulePhrase()
            }
            return absent
        }))) } }
        global_window.addEventListener("pageshow", (fun(): dynamic {
            if (truth(modal["open"])) {
                landscape.schedule()
            }
            return absent
        }))
        landscape.schedule()
        global_window.HunterGuideCompanion3833 = jsObject { obj57 ->
            obj57["sync"] = sync
            obj57["destroy"] = destroy
            accessor(obj57, "mounted", "get", fun(): dynamic {
                return (!truth((!truth(active))))
            })
        }
        return absent
    }))()
}
