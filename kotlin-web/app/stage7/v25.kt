@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv25

import hunter.browser.*

@JsName("V25UI") private external var global_V25UI: dynamic
@JsName("warmSelectBefore25") private external var global_warmSelectBefore25: dynamic
@JsName("Warm25") private external var global_Warm25: dynamic
@JsName("Back25") private external var global_Back25: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("startGuide11") private external var global_startGuide11: dynamic
@JsName("migrateGuide22") private external var global_migrateGuide22: dynamic
@JsName("backModal24") private external var global_backModal24: dynamic
@JsName("modalButtons24") private external var global_modalButtons24: dynamic
@JsName("Warm24") private external var global_Warm24: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("TrainingProgression19") private external var global_TrainingProgression19: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("expandBefore22") private external var global_expandBefore22: dynamic
@JsName("expandBlocks24") private external var global_expandBlocks24: dynamic
@JsName("RoutineSequence") private external var global_RoutineSequence: dynamic
@JsName("BlockSession22") private external var global_BlockSession22: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("anchorStep22") private external var global_anchorStep22: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("clearTimeout") private external var global_clearTimeout: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("Nav24") private external var global_Nav24: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("Offline20") private external var global_Offline20: dynamic
@JsName("OfflineManifest20") private external var global_OfflineManifest20: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV25() {
    global_V25UI = jsObject { obj1 ->
        obj1["modal"] = global_modal
        obj1["render"] = global_render
        obj1["allRoutines"] = global_allRoutines
        obj1["answerCoach"] = global_answerCoach
        obj1["startGuide11"] = global_startGuide11
        obj1["migrateGuide22"] = global_migrateGuide22
        obj1["backModal24"] = global_backModal24
        obj1["modalButtons24"] = global_modalButtons24
    }
    global_warmSelectBefore25 = global_Warm24.select
    global_Warm25 = jsObject { obj2 ->
        obj2["seconds"] = fun(arg5: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg5) { global_state.profile }
            if (truth((!truth(p)))) {
                return 30
            }
            val adjusted: dynamic = global_GoalPlans.adjusted(p)
            val reduced: dynamic = global_OwnRoutines18.gentle(adjusted)
            val base: dynamic = (if (truth(reduced)) 30 else dynamicValue(jsonValue("[30,45,60]"))[global_TrainingProgression19.level(p)])
            return global_Math.max(20, global_Math.min(60, jsMultiply(global_Math.round(jsDivide(jsMultiply(base, global_TrainingIntensity.scale(p)), 5)), 5)))
        }
        obj2["legacy"] = fun(day: dynamic, p: dynamic, mode: dynamic): dynamic {
            val warm: dynamic = global_warmSelectBefore25(day, p)
            if (truth(day.sourceSequence)) {
                val steps: dynamic = global_expandBefore22(day)
                if (truth(jsAnd(warm) { (!truth(warm.blocked)) })) {
                    steps.unshift(global_Warm24.step(warm.warm, "preparation", day.items.length))
                    steps.push(global_Warm24.step(warm.cool, "cooldown", day.items.length))
                }
                return steps
            }
            val layout: dynamic = global_expandBlocks24(day, mode, jsOr(p) { js("({})") })
            if (truth((!truth(onPresent(layout) { value8 -> value8.steps.length })))) {
                return js("[]")
            }
            if (truth(jsAnd(warm) { (!truth(warm.blocked)) })) {
                layout.steps[0] = global_Warm24.step(warm.warm, "preparation", layout.blocks.length)
                layout.steps[jsSubtract(layout.steps.length, 1)] = global_Warm24.step(warm.cool, "cooldown", layout.blocks.length)
            } else {
                if (truth(onPresent(warm) { value14 -> value14.blocked })) {
                    layout.steps[0].label = warm.reason
                    layout.steps[0].seconds = 0
                }
            }
            return layout.steps
        }
        obj2["migrate"] = fun(day: dynamic, steps: dynamic, saved: dynamic): dynamic {
            if (truth((!truth(saved)))) {
                return null
            }
            if (truth((saved.signature === global_RoutineSequence.signature(steps)))) {
                return saved
            }
            val old: dynamic = global_Warm25.legacy(day, global_state.profile, jsOr(saved.mode22) { global_BlockSession22.mode(global_state.profile) })
            if (truth((saved.signature !== global_RoutineSequence.signature(old)))) {
                return null
            }
            if (truth(jsOr(jsOr((!truth(global_Number.isInteger(saved.index)))) { jsLess(saved.index, 0) }) { jsMore(saved.index, old.length) })) {
                return null
            }
            val index: dynamic = global_anchorStep22(old, saved.index, steps)
            if (truth(jsLess(index, 0))) {
                return null
            }
            val previous: dynamic = old[saved.index]
            val next: dynamic = steps[index]
            var remaining: dynamic = (if (truth((onPresent(previous) { value21 -> value21.kind } === onPresent(next) { value22 -> value22.kind }))) global_Math.min(jsOr(saved.remaining) { 0 }, jsOr(onPresent(next) { value23 -> value23.seconds }) { 0 }) else jsOr(onPresent(next) { value24 -> value24.seconds }) { 0 })
            if (truth(jsAnd(onPresent(previous) { value25 -> value25.cardio24 }) { onPresent(next) { value26 -> value26.cardio24 } })) {
                val elapsed: dynamic = global_Math.max(0, jsSubtract(previous.seconds, jsOr(saved.remaining) { 0 }))
                remaining = global_Math.max(0, jsSubtract(next.seconds, elapsed))
            }
            return jsObject { obj27 ->
                copyFields(obj27, saved)
                obj27["signature"] = global_RoutineSequence.signature(steps)
                obj27["index"] = index
                obj27["remaining"] = remaining
            }
        }
        obj2["minutes"] = fun(r: dynamic, p: dynamic): dynamic {
            val day: dynamic = jsObject { obj30 ->
                obj30["title"] = r.name
                obj30["items"] = r.items
                obj30["sourceSequence"] = r.sourceSequence
            }
            val steps: dynamic = jsOr(onPresent(global_BlockSession22.expand(day, global_BlockSession22.mode(p), jsOr(p) { js("({})") })) { value31 -> value31.steps }) { global_RoutineSequence.expand(day) }
            return global_Math.ceil(jsDivide(steps.reduce((fun(n: dynamic, s: dynamic): dynamic {
                return jsAdd(n, (if (truth(jsAnd(jsAnd((s.kind === "exercise")) { (s.unit !== "sec") }) { (!truth(s.seconds)) })) global_OwnRoutines18.workSeconds(s) else jsOr(s.seconds) { 0 }))
            }), 0), 60))
        }
    }
    global_Warm24.select = (fun(day: dynamic, arg36: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg36) { global_state.profile }
        val selected: dynamic = global_warmSelectBefore25(day, p)
        if (truth(jsOr((!truth(selected))) { selected.blocked })) {
            return selected
        }
        val seconds: dynamic = global_Warm25.seconds(p)
        return jsObject { obj37 ->
            copyFields(obj37, selected)
            obj37["warm"] = jsObject { obj38 ->
                copyFields(obj38, selected.warm)
                obj38["seconds"] = seconds
            }
            obj37["cool"] = jsObject { obj39 ->
                copyFields(obj39, selected.cool)
                obj39["seconds"] = seconds
            }
            obj37["reason"] = jsAdd(jsAdd(jsAdd(seconds, " segundos al inicio y "), seconds), " segundos de cardio suave al final. Aumenta y reduce el ritmo gradualmente, sin forzarlo.")
        }
    })
    global_migrateGuide22 = (fun(day: dynamic, steps: dynamic, saved: dynamic): dynamic {
        return jsOr(global_Warm25.migrate(day, steps, saved)) { global_V25UI.migrateGuide22(day, steps, saved) }
    })
    global_startGuide11 = (fun(day: dynamic, record: dynamic): dynamic {
        val saved: dynamic = (if (truth(record)) global_state.guidedSessions[day.date] else null)
        global_V25UI.startGuide11(day, record)
        if (truth(jsAnd(jsAnd(day.sourceSequence) { saved }) { (saved.signature !== global_guide11.signature) })) {
            val migrated: dynamic = global_migrateGuide22(day, global_guide11.steps, saved)
            if (truth((onPresent(migrated) { value44 -> value44.signature } === global_guide11.signature))) {
                global_guide11.index = migrated.index
                global_guide11.remaining = migrated.remaining
                global_paintGuide11()
            }
        }
        return absent
    })
    global_allRoutines = (fun(): dynamic {
        return global_V25UI.allRoutines().map((fun(r: dynamic): dynamic {
            return jsObject { obj51 ->
                copyFields(obj51, r)
                obj51["minutes"] = global_Warm25.minutes(r, global_state.profile)
            }
        }))
    })
    global_Back25 = jsObject { obj52 ->
        obj52["animations"] = js("[]")
        obj52["timer"] = 0
        obj52["token"] = 0
        obj52["busy"] = false
        obj52["enabled"] = fun(): dynamic {
            return jsAnd((onPresent(global_state.profile) { value55 -> value55.animate } !== false)) { (!truth(global_matchMedia("(prefers-reduced-motion: reduce)").matches)) }
        }
        obj52["reset"] = fun(): dynamic {
            val receiver56: dynamic = js("this")
            run {
                val target58: dynamic = receiver56
                val previous60: dynamic = numeric(target58.token)
                target58.token = jsAdd(previous60, 1)
            }
            global_clearTimeout(receiver56.timer)
            receiver56.timer = 0
            receiver56.busy = false
            if (true) {
                val iterator65: dynamic = iteratorOf(receiver56.animations)
                while (true) {
                    val next66: dynamic = iterator65.next()
                    if (truth(next66.done)) break
                    var animation: dynamic = absent
                    animation = next66.value
                    animation.cancel()
                }
            }
            receiver56.animations = js("[]")
            return absent
        }
        obj52["play"] = fun(): dynamic {
            val receiver69: dynamic = js("this")
            if (truth((!truth(receiver69.enabled())))) {
                return absent
            }
            val dialog: dynamic = global_Dollar("#modal")
            val container: dynamic = (if (truth(dialog["open"])) dialog else global_Dollar("#app"))
            val children: dynamic = (if (truth(dialog["open"])) dynamicValue(jsArray { array72 ->
                appendAll(array72, container.children)
            }).filter((fun(n: dynamic): dynamic {
                return (!truth(n.matches("button,.window-frame26,.window-actions26,.window-particles28,.ambient-particles29")))
            })) else jsArray { array73 ->
                appendAll(array73, container.querySelectorAll(".scene"))
            })
            if (truth((!truth(children.some((fun(n: dynamic): dynamic {
                return (jsTypeOf(n.animate) === "function")
            })))))) {
                return absent
            }
            val token: dynamic = receiver69.token
            receiver69.busy = true
            if (true) {
                val iterator76: dynamic = iteratorOf(children)
                while (true) {
                    val next77: dynamic = iterator76.next()
                    if (truth(next77.done)) break
                    var node: dynamic = absent
                    node = next77.value
                    receiver69.animations.push(node.animate(jsonValue("[{\"opacity\":0.94},{\"opacity\":1}]"), jsonValue("{\"duration\":180,\"easing\":\"cubic-bezier(.2,.7,.2,1)\"}")))
                }
            }
            val button: dynamic = (if (truth(dialog["open"])) dialog.querySelector(".modal-back24") else null)
            if (truth(onPresent(button) { value78 -> value78.animate })) {
                receiver69.animations.push(button.animate(jsonValue("[{\"transform\":\"scale(.88)\"},{\"transform\":\"scale(1)\"}]"), jsonValue("{\"duration\":180,\"easing\":\"ease-out\"}")))
            }
            receiver69.timer = global_setTimeout((fun(): dynamic {
                if (truth((receiver69.token === token))) {
                    receiver69.busy = false
                    receiver69.timer = 0
                    receiver69.animations = js("[]")
                }
                return absent
            }), 180)
            return absent
        }
    }
    global_modalButtons24 = (fun(): dynamic {
        global_V25UI.modalButtons24()
        val button: dynamic = global_Dollar("#modal .modal-back24")
        if (truth((!truth(button)))) {
            return absent
        }
        button.classList.add("modal-back25")
        if (truth((!truth(button.querySelector("svg"))))) {
            button.innerHTML = "<svg viewBox=\"0 0 24 24\" aria-hidden=\"true\" focusable=\"false\"><path d=\"M14.5 5.5 8 12l6.5 6.5M8.5 12H20\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"/></svg>"
        }
        val label: dynamic = (if (truth(global_Nav24.stack.length)) "Regresar a la ventana anterior" else "Volver a la pantalla principal")
        button.setAttribute("aria-label", label)
        button.title = label
        button.onclick = global_backModal24
        return absent
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_Back25.reset()
        global_V25UI.modal(html)
        global_modalButtons24()
        return absent
    })
    global_backModal24 = (fun(): dynamic {
        if (truth(jsOr((!truth(global_Dollar("#modal")["open"]))) { jsAnd(global_Back25.busy) { global_Back25.enabled() } })) {
            return absent
        }
        global_Back25.reset()
        global_V25UI.backModal24()
        global_modalButtons24()
        jsAnd(global_Dollar("#modal")["open"]) { onPresent(global_Dollar("#modal .modal-back24")) { value99 -> value99.focus(jsonValue("{\"preventScroll\":true}")) } }
        global_Back25.play()
        return absent
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth((!truth(global_Dollar("#modal")["open"])))) {
            global_Back25.reset()
        }
        return absent
    }))
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("calent|cardio.*final|duraci[oó]n.*cardio", "i")).test(q))) {
            return jsAdd(jsAdd(jsAdd(global_companionDisplayName(), ": El calentamiento dura "), global_Warm25.seconds()), " segundos según tu nivel y la reducción elegida, con un máximo de 60 segundos. El cardio suave final usa la misma duración. Las series principales y sus descansos se conservan.")
        }
        return global_V25UI.answerCoach(q)
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V25UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target104: dynamic = n
                val assigned106: dynamic = "EL DESPERTAR · V25"
                target104.textContent = assigned106
                assigned106
            }
        }))
        return absent
    })
    global_openDiagnostics20 = (fun(): dynamic {
        global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section><h2>Estado del contenido local · V25</h2><p>", global_E.exercises.length), " fichas · "), global_Catalog.routines.length), " rutinas · "), global_Offline20.count()), " clips 2D sin conexión.</p><p>Calentamiento y cardio suave final: "), global_Warm25.seconds()), " segundos cada uno. Máximo de 60 segundos por fase; descansos entre series de 30, 60 o 90 segundos.</p><p>"), global_Runtime20.errors.length), " errores de interfaz registrados en esta apertura.</p><p>Medios locales: "), dynamicValue(jsDivide(jsOr(global_OfflineManifest20.bytes) { 0 }, 1000000)).toFixed(1)), " MB.</p><p class=\"footnote\">La selección usa objetivos, nivel, equipo y restricciones declaradas. No diagnostica ni prescribe rehabilitación.</p></section>"))
        return absent
    })
    global_window.HunterV25 = jsObject { obj111 ->
        obj111["Warm25"] = global_Warm25
        obj111["Back25"] = global_Back25
    }
    global_state.appVersion = 25
    global_save()
    global_render()
}
