@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv22

import hunter.browser.*

@JsName("V22UI") private external var global_V22UI: dynamic
@JsName("expandBefore22") private external var global_expandBefore22: dynamic
@JsName("anchorStep22") private external var global_anchorStep22: dynamic
@JsName("migrateGuide22") private external var global_migrateGuide22: dynamic
@JsName("blockMarked22") private external var global_blockMarked22: dynamic
@JsName("paintBlocks22") private external var global_paintBlocks22: dynamic
@JsName("setGuideMode22") private external var global_setGuideMode22: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("RoutineSequence") private external var global_RoutineSequence: dynamic
@JsName("BlockSession22") private external var global_BlockSession22: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("persistGuide11") private external var global_persistGuide11: dynamic
@JsName("HunterGuided") private external var global_HunterGuided: dynamic
@JsName("startGuide11") private external var global_startGuide11: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("pauseGuide11") private external var global_pauseGuide11: dynamic
@JsName("jumpGuide19") private external var global_jumpGuide19: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("updateGuideClock11") private external var global_updateGuideClock11: dynamic
@JsName("pendingGuide19") private external var global_pendingGuide19: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("todayDay") private external var global_todayDay: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic

internal fun installV22() {
    global_anchorStep22 = fun(previousSteps: dynamic, previousIndex: dynamic, steps: dynamic): dynamic {
        return global_HunterGuided.anchor(previousSteps, previousIndex, steps)
    }
    global_migrateGuide22 = fun(day: dynamic, steps: dynamic, saved: dynamic): dynamic {
        return global_HunterGuided.migrate(day, steps, saved)
    }
    global_blockMarked22 = fun(s: dynamic): dynamic {
        val g: dynamic = global_guide11
        return (!truth((!truth(jsAnd(jsAnd(onPresent(g) { value27 -> value27.record }) { (onPresent(global_state.logs[g.day.date]) { value28 -> onPresent(value28.items[s.itemIndex]) { value30 -> value30.id } } === s.id) }) { onPresent(global_state.logs[g.day.date]) { value31 -> onPresent(value31.items[s.itemIndex]) { value33 -> onPresent(value33["actual"][s.setIndex]) { value35 -> value35.done } } } }))))
    }
    global_paintBlocks22 = fun(): dynamic {
        val g: dynamic = global_guide11
        val slot: dynamic = global_Dollar("#blockSlot22")
        val s: dynamic = onPresent(g) { value38 -> value38.steps[g.index] }
        if (truth(jsOr((!truth(g))) { (!truth(slot)) })) {
            return absent
        }
        if (truth((!truth(g.layout22)))) {
            slot.innerHTML = "<p class=\"footnote\">La secuencia documentada conserva sus bloques y su orden original.</p>"
            return absent
        }
        val blocks: dynamic = g.layout22.blocks
        val total: dynamic = blocks.reduce((fun(n: dynamic, b: dynamic): dynamic {
            return jsAdd(n, b.series.length)
        }), 0)
        val marked: dynamic = blocks.reduce((fun(n: dynamic, b: dynamic): dynamic {
            return jsAdd(n, b.series.filter(global_blockMarked22).length)
        }), 0)
        val active: dynamic = (if (truth(jsAnd((onPresent(s) { value42 -> value42.kind } === "rest")) { (s.restScope22 === "block") })) s.nextBlockIndex22 else onPresent(s) { value43 -> value43.blockIndex })
        val `open`: dynamic = jsOr(onPresent(slot.querySelector("details")) { value44 -> value44["open"] }) { false }
        slot.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section class=\"block-layout22\"><label>Orden de la sesión<select name=\"guideBlockMode22\" onchange=\"setGuideMode22(this.value)\">", global_options(jsonValue("{\"exercise\":\"Por ejercicio\",\"rounds\":\"Por rondas\"}"), g.blockMode22)), "</select></label><p class=\"footnote\">"), (if (truth((g.blockMode22 === "rounds"))) jsAdd(jsAdd("Una serie de cada ejercicio por ronda. Descanso de ", g.layout22.restSeconds), " s entre rondas. Puedes pausar el reloj cuando lo necesites.") else jsAdd(jsAdd("Todas las series del ejercicio en un bloque. Descanso de ", g.layout22.restSeconds), " s entre series y entre bloques."))), "</p><details "), (if (truth(`open`)) "open" else "")), "><summary>Mapa de "), blocks.length), " bloques · "), marked), " de "), total), " series marcadas</summary><ol class=\"block-map22\">"), blocks.map((fun(b: dynamic): dynamic {
            val done: dynamic = b.series.filter(global_blockMarked22).length
            val selected: dynamic = (b.index === active)
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<li class=\"", (if (truth(selected)) "active22 " else "")), (if (truth((done === b.series.length))) "marked22" else "")), "\" data-block22=\""), b.index), "\"><div class=\"row between\"><b>"), (if (truth((g.blockMode22 === "rounds"))) b.label else jsAdd("Bloque ", jsAdd(b.index, 1)))), "</b><small>"), done), "/"), b.series.length), " marcadas</small></div><span>"), global_esc((if (truth((g.blockMode22 === "rounds"))) b.series.map((fun(x: dynamic): dynamic {
                return x.label
            })).join(" · ") else b.label))), "</span><button type=\"button\" class=\"btn ghost\" onclick=\"jumpGuide19("), b.stepIndices[0]), ")\">Abrir bloque "), jsAdd(b.index, 1)), "</button></li>")
        })).join("")), "</ol></details></section>")
        val article: dynamic = global_Dollar("#guidedSlot .guided-step")
        if (truth((onPresent(s) { value47 -> value47.kind } === "exercise"))) {
            onPresent(article) { value48 -> value48.insertAdjacentHTML("afterbegin", jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<div class=\"block-heading22\"><span>", (if (truth((g.blockMode22 === "rounds"))) "RONDA" else "BLOQUE")), " "), jsAdd(s.blockIndex, 1)), " DE "), blocks.length), "</span><small>"), (if (truth((g.blockMode22 === "rounds"))) jsAdd(jsAdd(jsAdd("Ejercicio ", jsAdd(blocks[s.blockIndex].series.indexOf(s), 1)), " de "), blocks[s.blockIndex].series.length) else jsAdd(jsAdd(jsAdd("Serie ", jsAdd(s.setIndex, 1)), " de "), s.sets))), "</small></div>")) }
        }
        if (truth((onPresent(s) { value49 -> value49.kind } === "rest"))) {
            article.querySelector("h3").textContent = jsAdd(jsAdd(jsAdd((if (truth((s.restScope22 === "block"))) jsAdd("Descanso entre ", (if (truth((g.blockMode22 === "rounds"))) "rondas" else "bloques")) else "Descanso entre series"), " · "), s.seconds), " segundos")
            article.querySelector(".eyebrow").textContent = (if (truth((s.restScope22 === "block"))) jsAdd("INTERMEDIO · SIGUIENTE ", (if (truth((g.blockMode22 === "rounds"))) "RONDA" else "BLOQUE")) else "RECUPERACIÓN ENTRE SERIES")
            val p: dynamic = article.querySelector(".rest-orbit + p")
            if (truth(p)) {
                p.textContent = (if (truth((s.restScope22 === "block"))) jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("Después del descanso comienza ", (if (truth((g.blockMode22 === "rounds"))) "la ronda " else "el bloque ")), jsAdd(s.nextBlockIndex22, 1)), " de "), blocks.length), ".") else "Recupera antes de la siguiente serie del mismo ejercicio.")
            }
        }
        return absent
    }
    global_setGuideMode22 = fun(mode: dynamic): dynamic {
        val g: dynamic = global_guide11
        if (truth(jsOr(jsOr(jsOr((!truth(g))) { (!truth(g.layout22)) }) { (!truth(global_BlockSession22.modes.includes(mode))) }) { (g.blockMode22 === mode) })) {
            return absent
        }
        global_pauseGuide11()
        val anchor: dynamic = g.steps[g.index]
        val remaining: dynamic = g.remaining
        val day: dynamic = g.day
        val record: dynamic = g.record
        global_state.profile.blockMode22 = mode
        global_save()
        global_startGuide11(day, record)
        var index: dynamic = global_anchorStep22(g.steps, g.index, global_guide11.steps)
        if (truth(jsLess(index, 0))) {
            index = 0
        }
        global_jumpGuide19(index)
        global_guide11.remaining = (if (truth((onPresent(anchor) { value64 -> value64.kind } === onPresent(global_guide11.steps[index]) { value65 -> value65.kind }))) global_Math.min(remaining, jsOr(onPresent(global_guide11.steps[index]) { value66 -> value66.seconds }) { 0 }) else jsOr(onPresent(global_guide11.steps[index]) { value67 -> value67.seconds }) { 0 })
        global_updateGuideClock11()
        global_persistGuide11()
        return absent
    }
    global_V22UI = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["paintGuide11"] = global_paintGuide11
        obj1["validateBackup"] = global_validateBackup
        obj1["render"] = global_render
        obj1["answerCoach"] = global_answerCoach
    }
    global_expandBefore22 = global_RoutineSequence.expand
    global_RoutineSequence.expand = (fun(day: dynamic): dynamic {
        return jsOr(onPresent(global_BlockSession22.expand(day, global_BlockSession22.mode(global_state.profile), jsOr(global_state.profile) { js("({})") })) { value6 -> value6.steps }) { global_expandBefore22(day) }
    })
    global_profileForm = (fun(arg9: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg9) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V22UI.profileForm(initial)
        val f: dynamic = box.querySelector("form")
        val rest: dynamic = f.elements.restSeconds
        rest.closest("label").insertAdjacentHTML("afterend", jsAdd(jsAdd("<label>Orden de los bloques<select name=\"blockMode22\">", global_options(jsonValue("{\"exercise\":\"Por ejercicio · todas sus series juntas\",\"rounds\":\"Por rondas · una serie de cada ejercicio\"}"), global_BlockSession22.mode(global_state.profile))), "</select><small>Cinco ejercicios forman cinco bloques en el primer modo. Por rondas, haces una serie de cada uno y descansas al terminar la ronda.</small></label>"))
        return box.innerHTML
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val selected: dynamic = jsOr(onPresent(ev.target.elements.blockMode22) { value14 -> value14["value"] }) { "exercise" }
        val before: dynamic = global_state.profile
        if (truth((!truth(global_BlockSession22.modes.includes(selected))))) {
            ev.preventDefault()
            return global_toast("Selecciona el orden de los bloques.")
        }
        global_V22UI.submitProfile(ev)
        if (truth(jsAnd(global_state.profile) { (global_state.profile !== before) })) {
            global_state.profile.blockMode22 = selected
            global_save()
            global_render()
        }
        return absent
    })
    global_persistGuide11 = (fun(): dynamic {
        return global_HunterGuided.persistGuide(true)
    })
    global_startGuide11 = (fun(day: dynamic, record: dynamic): dynamic {
        return global_HunterGuided.start(day, record, true)
    })
    global_paintGuide11 = (fun(): dynamic {
        global_V22UI.paintGuide11()
        global_paintBlocks22()
        return absent
    })
    global_pendingGuide19 = (fun(): dynamic {
        return global_HunterGuided.pending(true)
    })
    global_validateBackup = (fun(`data`: dynamic): dynamic {
        if (truth((!truth(global_V22UI.validateBackup(`data`))))) {
            return false
        }
        return jsAnd(dynamicValue(jsArray { array75 ->
            array75.push(`data`.profile)
            appendAll(array75, `data`.cycles.map((fun(c: dynamic): dynamic {
                return c.profile
            })))
        }).filter(global_Boolean).every((fun(p: dynamic): dynamic {
            return jsOr((p.blockMode22 === absent)) { global_BlockSession22.modes.includes(p.blockMode22) }
        }))) { global_Object.values(jsOr(`data`.guidedSessions) { js("({})") }).every((fun(g: dynamic): dynamic {
            return jsOr((g.mode22 === absent)) { global_BlockSession22.modes.includes(g.mode22) }
        })) }
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("bloque|rondas?|circuito|orden de.*serie", "i")).test(q))) {
            val mode: dynamic = global_BlockSession22.mode(global_state.profile)
            val day: dynamic = global_todayDay()
            val layout: dynamic = (if (truth(day)) global_BlockSession22.expand(day, mode, global_state.profile) else null)
            return jsAdd(jsAdd(jsAdd(jsAdd(global_companionDisplayName(), ": "), (if (truth((mode === "rounds"))) "Por rondas, haces una serie de cada ejercicio y repites las rondas hasta recorrer todas las series." else "Por ejercicio, cada ejercicio con todas sus series forma un bloque.")), (if (truth(layout)) jsAdd(jsAdd(jsAdd(jsAdd(" Hoy tienes ", layout.blocks.length), " bloques, con descansos de "), layout.restSeconds), " segundos.") else "")), " Cambia el orden en Perfil o en la guía. Solo las casillas de las series registran lo realizado.")
        }
        return global_V22UI.answerCoach(q)
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V22UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target80: dynamic = n
                val assigned82: dynamic = "EL DESPERTAR · V22"
                target80.textContent = assigned82
                assigned82
            }
        }))
        return absent
    })
    global_state.appVersion = 22
    global_save()
    global_render()
}
