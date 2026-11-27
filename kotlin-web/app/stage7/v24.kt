@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv24

import hunter.browser.*

@JsName("V24UI") private external var global_V24UI: dynamic
@JsName("Nav24") private external var global_Nav24: dynamic
@JsName("snapshotModal24") private external var global_snapshotModal24: dynamic
@JsName("modalButtons24") private external var global_modalButtons24: dynamic
@JsName("decorateBefore24") private external var global_decorateBefore24: dynamic
@JsName("backModal24") private external var global_backModal24: dynamic
@JsName("goalSelection14") private external var global_goalSelection14: dynamic
@JsName("Warm24") private external var global_Warm24: dynamic
@JsName("expandBlocks24") private external var global_expandBlocks24: dynamic
@JsName("migrateBefore24") private external var global_migrateBefore24: dynamic
@JsName("expandSequence24") private external var global_expandSequence24: dynamic
@JsName("toggleDemo24") private external var global_toggleDemo24: dynamic
@JsName("adjustRoutine24") private external var global_adjustRoutine24: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("routineDetail") private external var global_routineDetail: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("goalsView") private external var global_goalsView: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("startGuide11") private external var global_startGuide11: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("closeCoach") private external var global_closeCoach: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("decorateModal") private external var global_decorateModal: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("RoutineSequence") private external var global_RoutineSequence: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("pauseGuide11") private external var global_pauseGuide11: dynamic
@JsName("closeActiveModal") private external var global_closeActiveModal: dynamic
@JsName("go") private external var global_go: dynamic
@JsName("releaseCompanion") private external var global_releaseCompanion: dynamic
@JsName("guideFrame11") private external var global_guideFrame11: dynamic
@JsName("staticActors14") private external var global_staticActors14: dynamic
@JsName("clearGallery13") private external var global_clearGallery13: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("mountPanelCompanion") private external var global_mountPanelCompanion: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("GoalContext24") private external var global_GoalContext24: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("interpretGoal") private external var global_interpretGoal: dynamic
@JsName("levelHint19") private external var global_levelHint19: dynamic
@JsName("TrainingProgression19") private external var global_TrainingProgression19: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Training23") private external var global_Training23: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("BlockSession22") private external var global_BlockSession22: dynamic
@JsName("migrateGuide22") private external var global_migrateGuide22: dynamic
@JsName("anchorStep22") private external var global_anchorStep22: dynamic
@JsName("paintBlocks22") private external var global_paintBlocks22: dynamic
@JsName("persistGuide11") private external var global_persistGuide11: dynamic
@JsName("toggleGuidedDemo11") private external var global_toggleGuidedDemo11: dynamic
@JsName("todayDay") private external var global_todayDay: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("refreshReminderStatus") private external var global_refreshReminderStatus: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("reminderStatus") private external var global_reminderStatus: dynamic
@JsName("Android") private external var global_Android: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("Offline20") private external var global_Offline20: dynamic
@JsName("OfflineManifest20") private external var global_OfflineManifest20: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic

internal fun installV24() {
    global_snapshotModal24 = fun(): dynamic {
        val d: dynamic = global_Dollar("#modal")
        return jsObject { obj4 ->
            obj4["nodes"] = jsArray { array5 ->
                appendAll(array5, d.childNodes)
            }
            obj4["scroll"] = d.scrollTop
            obj4["guide"] = (if (truth(d.querySelector("#guidedSlot"))) jsOr(global_Nav24.guide) { global_guide11 } else null)
            obj4["vector"] = (if (truth(d.querySelector("#vectorCanvas"))) global_vectorSession else null)
            obj4["coach"] = (!truth((!truth(d.querySelector(".coach-panel")))))
        }
    }
    global_modalButtons24 = fun(): dynamic {
        val d: dynamic = global_Dollar("#modal")
        if (truth((!truth(d.querySelector(".modal-back24"))))) {
            val b: dynamic = global_document.createElement("button")
            b.className = "modal-back24"
            b.type = "button"
            b.setAttribute("aria-label", "Regresar a la ventana anterior")
            b.title = "Regresar"
            b.textContent = "←"
            b.onclick = global_backModal24
            val x: dynamic = d.querySelector(".modal-close-x")
            if (truth(x)) {
                x.after(b)
            } else {
                d.prepend(b)
            }
        }
        return absent
    }
    global_backModal24 = fun(): dynamic {
        val d: dynamic = global_Dollar("#modal")
        if (truth((!truth(d["open"])))) {
            return absent
        }
        global_pauseGuide11()
        val previous: dynamic = global_Nav24.stack.pop()
        if (truth((!truth(previous)))) {
            global_closeActiveModal()
            global_go("home")
            return absent
        }
        global_releaseCompanion()
        run {
            val previous43: dynamic = numeric(global_guideFrame11)
            global_guideFrame11 = jsAdd(previous43, 1)
        }
        if (true) {
            val iterator44: dynamic = iteratorOf(global_staticActors14.values())
            while (true) {
                val next45: dynamic = iterator44.next()
                if (truth(next45.done)) break
                var a: dynamic = absent
                a = next45.value
                a.pause()
            }
        }
        global_clearGallery13()
        global_Nav24.restoring = true
        try {
            run { val target49: dynamic = d; invokeWith(target49.replaceChildren, target49, jsArray { array48 ->
                appendAll(array48, previous.nodes)
            }) }
            removeKey(d.dataset, "guide")
            global_modalButtons24()
            global_ModalViewport.begin()
            if (truth(previous.guide)) {
                global_guide11 = previous.guide
                global_Nav24.guide = global_guide11
                global_guide11.running = false
                d.dataset.guide = "true"
                global_paintGuide11()
            }
            if (truth(previous.vector)) {
                global_vectorSession = previous.vector
                global_vectorSession.playing = false
                global_vectorPaint()
            }
            if (truth(previous.coach)) {
                global_mountPanelCompanion(global_selectedCompanion())
            }
            global_ModalViewport.fit()
            d.scrollTop = previous.scroll
        } finally {
            global_Nav24.restoring = false
        }
        return absent
    }
    global_goalSelection14 = fun(form: dynamic, arg82: dynamic): dynamic {
        var fromText: dynamic = absent
        fromText = definedOr(arg82) { false }
        val p: dynamic = jsObject { obj83 ->
            copyFields(obj83, global_state.profile)
            obj83["goalGroup"] = form.elements.goalGroup["value"]
            obj83["finalGoal"] = form.elements.finalGoal["value"]
            obj83["focus"] = form.elements.focus["value"]
        }
        val i: dynamic = global_GoalContext24.interpret(p)
        val target: dynamic = i.target
        form.elements.finalTarget["value"] = target
        form.elements.goal["value"] = target
        if (truth((i.focus !== "full"))) {
            form.elements.focus["value"] = i.focus
        }
        if (true) {
            val iterator90: dynamic = iteratorOf(global_Object.keys(i.inferred))
            while (true) {
                val next91: dynamic = iterator90.next()
                if (truth(next91.done)) break
                var k: dynamic = absent
                k = next91.value
                if (truth(form.elements[jsAdd("r_", k)])) {
                    form.elements[jsAdd("r_", k)].checked = true
                }
            }
        }
        val explain: dynamic = form.querySelector("#objectiveExplanation")
        if (truth(explain)) {
            explain.textContent = global_GoalPlans.plans[target].why
        }
        val `out`: dynamic = form.querySelector("#intentResult")
        if (truth(`out`)) {
            `out`.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<b>", global_esc(global_GoalGroups14.groups[i.chosen].name)), "</b><p>"), global_esc(i.reason)), "</p><p><b>Medición:</b> "), global_esc(global_GoalGroups14.groups[i.chosen].measure)), "</p>"), (if (truth(i.personalTitles.length)) jsAdd(jsAdd("<small>También considera ", i.personalTitles.length), " objetivos personales activos.</small>") else "")), (if (truth(i.medical)) "<p>El texto menciona salud. Completa las zonas e indicaciones en Lesiones y salud.</p>" else ""))
        }
        return absent
    }
    global_V24UI = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["currentCycle"] = global_currentCycle
        obj1["allRoutines"] = global_allRoutines
        obj1["routineDetail"] = global_routineDetail
        obj1["paintGuide11"] = global_paintGuide11
        obj1["openCoach"] = global_openCoach
        obj1["render"] = global_render
        obj1["answerCoach"] = global_answerCoach
        obj1["goalsView"] = global_goalsView
        obj1["modal"] = global_modal
        obj1["startGuide11"] = global_startGuide11
        obj1["technique"] = global_technique
        obj1["closeCoach"] = global_closeCoach
    }
    global_Nav24 = jsonValue("{\"stack\":[],\"restoring\":false,\"guide\":null,\"vector\":null}")
    global_decorateBefore24 = global_decorateModal
    global_decorateModal = (fun(type: dynamic): dynamic {
        global_decorateBefore24(type)
        global_modalButtons24()
        return absent
    })
    global_modal = (fun(html: dynamic): dynamic {
        val d: dynamic = global_Dollar("#modal")
        if (truth(jsAnd(d["open"]) { (!truth(global_Nav24.restoring)) })) {
            global_Nav24.stack.push(global_snapshotModal24())
        } else {
            if (truth((!truth(d["open"])))) {
                global_Nav24.stack = js("[]")
            }
        }
        global_V24UI.modal(html)
        global_modalButtons24()
        return absent
    })
    global_startGuide11 = (fun(day: dynamic, record: dynamic): dynamic {
        global_Nav24.guide = global_guide11
        val saved: dynamic = (if (truth(record)) global_state.guidedSessions[day.date] else null)
        try {
            global_V24UI.startGuide11(day, record)
            if (truth(day.sourceSequence)) {
                val steps: dynamic = global_RoutineSequence.expand(day)
                val signature: dynamic = global_RoutineSequence.signature(steps)
                val usable: dynamic = (onPresent(saved) { value28 -> value28.signature } === signature)
                global_guide11.steps = steps
                global_guide11.signature = signature
                global_guide11.index = (if (truth(usable)) global_Math.min(saved.index, steps.length) else 0)
                global_guide11.remaining = (if (truth(usable)) global_Math.min(jsOr(saved.remaining) { 0 }, jsOr(onPresent(steps[global_guide11.index]) { value37 -> value37.seconds }) { 0 }) else jsOr(onPresent(steps[0]) { value38 -> value38.seconds }) { 0 })
                global_paintGuide11()
            }
        } finally {
            global_Nav24.guide = global_guide11
        }
        return absent
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth((!truth(global_Dollar("#modal")["open"])))) {
            global_Nav24.stack = js("[]")
            global_Nav24.guide = dynamicValue(null)
        }
        return absent
    }))
    global_Dollar("#modal").addEventListener("cancel", (fun(event: dynamic): dynamic {
        event.preventDefault()
        event.stopImmediatePropagation()
        global_backModal24()
        return absent
    }), true)
    global_profileForm = (fun(arg68: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg68) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V24UI.profileForm(initial)
        val f: dynamic = box.querySelector("form")
        val select: dynamic = f.elements.experience
        val `value`: dynamic = jsOr(onPresent(global_state.profile) { value71 -> value71.experience }) { (if (truth((f.elements.difficulty["value"] === "basic"))) "beginner" else "regular") }
        val label: dynamic = jsOr(select.closest(".field")) { select.closest("label") }
        val hidden: dynamic = global_document.createElement("input")
        hidden.type = "hidden"
        hidden.name = "experience"
        hidden.id = "experience"
        hidden["value"] = `value`
        if (truth(label)) {
            label.replaceWith(hidden)
        } else {
            select.replaceWith(hidden)
        }
        f.elements.finalGoal.setAttribute("oninput", "goalSelection14(this.form,true)")
        f.elements.goalGroup.setAttribute("onchange", "goalSelection14(this.form,true)")
        global_goalSelection14(f, true)
        return box.innerHTML
    })
    global_interpretGoal = (fun(form: dynamic): dynamic {
        global_goalSelection14(form, true)
        return absent
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val f: dynamic = ev.target
        f.elements.experience["value"] = (if (truth((f.elements.difficulty["value"] === "basic"))) "beginner" else "regular")
        global_V24UI.submitProfile(ev)
        return absent
    })
    global_levelHint19 = (fun(form: dynamic): dynamic {
        val n: dynamic = global_TrainingProgression19.level(jsObject { obj106 ->
            obj106["difficulty"] = form.elements.difficulty["value"]
        })
        val node: dynamic = form.querySelector("#levelHint19")
        if (truth(node)) {
            node.textContent = dynamicValue(jsonValue("[\"Básico: patrones de apoyo y bilaterales, con calibración inicial.\",\"Intermedio: movimientos de suelo, control unilateral y cadencia.\",\"Avanzado: palancas más exigentes, variantes a una pierna y control de la carga.\"]"))[n]
        }
        if (truth((form.dataset.restTouched19 !== "true"))) {
            form.elements.restSeconds["value"] = global_String(dynamicValue(jsonValue("[30,60,90]"))[n])
        }
        if (truth((onPresent(form.elements.experience) { value111 -> value111.type } === "hidden"))) {
            form.elements.experience["value"] = (if (truth((n === 0))) "beginner" else "regular")
        }
        return absent
    })
    global_currentCycle = (fun(): dynamic {
        val c: dynamic = global_V24UI.currentCycle()
        if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
            return c
        }
        val key: dynamic = global_JSON.stringify(arrayOf<dynamic>(24, global_state.profile, global_GoalContext24.personal()))
        if (truth((c.planningKey24 !== key))) {
            global_Training23.assign(c, global_state.profile, global_state.logs, global_E.dateKey(), global_state.cycles.filter((fun(x: dynamic): dynamic {
                return jsLess(x.start, c.start)
            })).at((-numeric(1))))
            c.planningKey24 = key
            c.goalContext24 = global_GoalContext24.interpret(global_state.profile)
            global_save()
        }
        return c
    })
    global_goalsView = (fun(): dynamic {
        return global_V24UI.goalsView().replace("Los objetivos son tu lista personal. No suman repeticiones ni modifican automáticamente la intensidad de las rutinas.", "Los objetivos pendientes complementan tu meta del perfil al organizar las próximas sesiones. No suman repeticiones ni aumentan la carga por sí solos. Las sesiones iniciadas conservan su registro.")
    })
    global_Warm24 = jsObject { obj122 ->
        obj122["key"] = fun(e: dynamic): dynamic {
            val id: dynamic = jsOr(e.baseId) { e.id }
            if (truth(dynamicValue(jsRegExp("box|shadow|punch", "")).test(id))) {
                return "boxing"
            }
            if (truth(dynamicValue(jsRegExp("^(jog|heelback|highknees)\$", "")).test(id))) {
                return "running"
            }
            if (truth(dynamicValue(jsRegExp("march", "")).test(id))) {
                return "marching"
            }
            return id
        }
        obj122["select"] = fun(day: dynamic, arg127: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg127) { global_state.profile }
            if (truth(jsOr((!truth(onPresent(day.items) { value128 -> value128.length }))) { (!truth(p)) })) {
                return null
            }
            val ap: dynamic = global_GoalPlans.adjusted(p)
            val used: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(day.items.map((fun(x: dynamic): dynamic {
                return jsOr(onPresent(global_E.byId(x.id)) { value129 -> value129.baseId }) { x.id }
            }))))
            val usedPatterns: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(day.items.map((fun(x: dynamic): dynamic {
                return global_E.byId(x.id)
            })).filter((fun(e: dynamic): dynamic {
                return (onPresent(e) { value131 -> value131.category } === "Cardio")
            })).map((fun(e: dynamic): dynamic {
                return global_Warm24.key(e)
            }))))
            var candidates: dynamic = dynamicValue(jsonValue("[\"jog\",\"rope\",\"heelback\",\"box\",\"fastmarch\",\"heeldig\",\"sidestep\",\"seatedpunch\",\"seatedmarch\",\"seatedstep\"]")).map((fun(id: dynamic): dynamic {
                return global_E.byId(id)
            })).filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(e) { (!truth(used.has(e.baseId))) }) { (!truth(usedPatterns.has(global_Warm24.key(e)))) }) { global_Adapt.allowed(e, ap) }) { jsOr((!truth(global_OwnRoutines18.gentle(ap)))) { (e.impact !== "Con saltos") } }
            }))
            val standing: dynamic = candidates.filter((fun(e: dynamic): dynamic {
                return (!truth(e.seated))
            }))
            if (truth(standing.length)) {
                candidates = standing
            }
            if (truth((!truth(candidates.length)))) {
                return jsonValue("{\"blocked\":true,\"reason\":\"No hay cardio compatible con estas restricciones. Revisa las indicaciones antes de entrenar.\"}")
            }
            val seed: dynamic = dynamicValue(jsArray { array133 ->
                appendAll(array133, jsOr(jsOr(day.title) { day.id }) { "" })
            }).reduce((fun(n: dynamic, c: dynamic): dynamic {
                return jsAdd(n, c.charCodeAt(0))
            }), 0)
            val e: dynamic = candidates[jsMod(seed, candidates.length)]
            val cool: dynamic = global_E.byId((if (truth(jsAnd(jsAnd(dynamicValue(jsonValue("[\"jog\",\"rope\",\"heelback\"]")).includes(e.id)) { (!truth(used.has("walk"))) }) { global_Adapt.allowed(global_E.byId("walk"), ap) })) "walk" else e.id))
            return jsObject { obj134 ->
                obj134["warm"] = jsObject { obj135 ->
                    obj135["id"] = e.id
                    obj135["seconds"] = 300
                    obj135["label"] = jsAdd("Calentamiento cardiovascular · ", e.name)
                }
                obj134["cool"] = jsObject { obj136 ->
                    obj136["id"] = cool.id
                    obj136["seconds"] = 300
                    obj136["label"] = jsAdd("Cardio suave final · ", cool.name)
                }
                obj134["reason"] = "Cinco minutos progresivos al inicio y cinco minutos bajando el ritmo al final. Mantén un ritmo que te permita hablar."
            }
        }
        obj122["step"] = fun(x: dynamic, kind: dynamic, blocks: dynamic): dynamic {
            return jsObject { obj139 ->
                obj139["kind"] = kind
                obj139["id"] = x.id
                obj139["label"] = x.label
                obj139["unit"] = "sec"
                obj139["target"] = x.seconds
                obj139["seconds"] = x.seconds
                obj139["blockIndex"] = (if (truth((kind === "preparation"))) (-numeric(1)) else blocks)
                obj139["blockCount22"] = blocks
                obj139["cardio24"] = true
            }
        }
    }
    global_expandBlocks24 = global_BlockSession22.expand
    global_migrateBefore24 = global_migrateGuide22
    global_migrateGuide22 = (fun(day: dynamic, steps: dynamic, saved: dynamic): dynamic {
        val migrated: dynamic = global_migrateBefore24(day, steps, saved)
        if (truth(migrated)) {
            return migrated
        }
        if (truth((!truth(saved)))) {
            return null
        }
        val old: dynamic = onPresent(global_expandBlocks24(day, global_BlockSession22.mode(global_state.profile), jsOr(global_state.profile) { js("({})") })) { value142 -> value142.steps }
        if (truth(jsOr((!truth(old))) { (saved.signature !== global_RoutineSequence.signature(old)) })) {
            return null
        }
        var index: dynamic = global_anchorStep22(old, saved.index, steps)
        if (truth(jsLess(index, 0))) {
            index = 0
        }
        val previous: dynamic = old[saved.index]
        val same: dynamic = (onPresent(previous) { value143 -> value143.kind } === onPresent(steps[index]) { value144 -> value144.kind })
        return jsObject { obj145 ->
            copyFields(obj145, saved)
            obj145["signature"] = global_RoutineSequence.signature(steps)
            obj145["index"] = index
            obj145["remaining"] = (if (truth(same)) global_Math.min(jsOr(saved.remaining) { 0 }, jsOr(onPresent(steps[index]) { value146 -> value146.seconds }) { 0 }) else jsOr(onPresent(steps[index]) { value147 -> value147.seconds }) { 0 })
        }
    })
    global_BlockSession22.expand = (fun(day: dynamic, mode: dynamic, p: dynamic): dynamic {
        val layout: dynamic = global_expandBlocks24(day, mode, p)
        if (truth((!truth(onPresent(layout) { value152 -> value152.steps.length })))) {
            return layout
        }
        val warm: dynamic = global_Warm24.select(day, p)
        layout.cardio24 = warm
        if (truth(jsAnd(warm) { (!truth(warm.blocked)) })) {
            layout.steps[0] = global_Warm24.step(warm.warm, "preparation", layout.blocks.length)
            layout.steps[jsSubtract(layout.steps.length, 1)] = global_Warm24.step(warm.cool, "cooldown", layout.blocks.length)
        } else {
            if (truth(onPresent(warm) { value160 -> value160.blocked })) {
                layout.steps[0].label = warm.reason
                layout.steps[0].seconds = 0
            }
        }
        return layout
    })
    global_expandSequence24 = global_RoutineSequence.expand
    global_RoutineSequence.expand = (fun(day: dynamic): dynamic {
        val steps: dynamic = global_expandSequence24(day)
        if (truth(jsAnd(day.sourceSequence) { steps.length })) {
            val w: dynamic = global_Warm24.select(day)
            if (truth(jsAnd(w) { (!truth(w.blocked)) })) {
                steps.unshift(global_Warm24.step(w.warm, "preparation", day.items.length))
                steps.push(global_Warm24.step(w.cool, "cooldown", day.items.length))
            }
        }
        return steps
    })
    global_paintGuide11 = (fun(): dynamic {
        val g: dynamic = global_guide11
        val s: dynamic = onPresent(g) { value171 -> value171.steps[g.index] }
        val kind: dynamic = onPresent(s) { value173 -> value173.kind }
        val record: dynamic = onPresent(g) { value174 -> value174.record }
        val layout: dynamic = onPresent(g) { value175 -> value175.layout22 }
        if (truth(onPresent(s) { value176 -> value176.cardio24 })) {
            s.kind = "exercise"
            s.setIndex = 0
            s.sets = 1
            g.record = false
            g.layout22 = dynamicValue(null)
        }
        try {
            global_V24UI.paintGuide11()
        } finally {
            if (truth(onPresent(s) { value187 -> value187.cardio24 })) {
                s.kind = kind
                removeKey(s, "setIndex")
                removeKey(s, "sets")
                g.record = record
                g.layout22 = layout
                global_paintBlocks22()
            }
        }
        onPresent(global_Dollar("#guidedSlot")) { value194 -> value194.querySelectorAll(".offline-demo20").forEach((fun(n: dynamic): dynamic {
            return n.remove()
        })) }
        val dose: dynamic = global_Dollar("#guidedSlot .guided-dose")
        if (truth(dose)) {
            dose.setAttribute("aria-label", "Cantidad para este paso")
            dose.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<strong>", s.target), "</strong><span>"), (if (truth((s.unit === "sec"))) "segundos" else "repeticiones")), (if (truth(global_E.sidesOf(s))) " por lado" else "")), "</span>")
        }
        if (truth(onPresent(s) { value198 -> value198.cardio24 })) {
            val article: dynamic = global_Dollar("#guidedSlot .guided-step")
            onPresent(article.querySelector(".block-heading22")) { value199 -> value199.remove() }
            article.querySelector(".eyebrow").textContent = (if (truth((kind === "preparation"))) "CARDIO · CALENTAMIENTO" else "CARDIO SUAVE · FINAL")
            article.querySelectorAll("details").forEach((fun(n: dynamic): dynamic {
                return n.remove()
            }))
            article.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<p class=\"notice\">", (if (truth((kind === "preparation"))) "Comienza despacio y aumenta gradualmente, sin llegar a fatigarte." else "Reduce el ritmo progresivamente. Si estás trotando o saltando, pasa a caminar al final.")), " El cardio no marca las series principales.</p>"))
        }
        global_Nav24.guide = g
        global_persistGuide11()
        return absent
    })
    global_toggleDemo24 = global_toggleGuidedDemo11
    global_toggleGuidedDemo11 = (fun(): dynamic {
        val s: dynamic = onPresent(global_guide11) { value206 -> value206.steps[global_guide11.index] }
        val kind: dynamic = onPresent(s) { value208 -> value208.kind }
        if (truth(onPresent(s) { value209 -> value209.cardio24 })) {
            s.kind = "exercise"
        }
        try {
            global_toggleDemo24()
        } finally {
            if (truth(onPresent(s) { value212 -> value212.cardio24 })) {
                s.kind = kind
            }
        }
        return absent
    })
    global_routineDetail = (fun(id: dynamic): dynamic {
        global_V24UI.routineDetail(id)
        val r: dynamic = global_allRoutines().find((fun(x: dynamic): dynamic {
            return (x.id === id)
        }))
        val w: dynamic = jsAnd(r) { global_Warm24.select(jsObject { obj217 ->
            obj217["title"] = r.name
            obj217["items"] = r.items
        }) }
        val quests: dynamic = onPresent(global_Dollar("#modal .routine-items")) { value218 -> value218.querySelectorAll(".quest") }
        if (truth(jsOr((!truth(onPresent(quests) { value219 -> value219.length }))) { (!truth(w)) })) {
            return absent
        }
        if (truth(w.blocked)) {
            quests[0].textContent = w.reason
            return absent
        }
        if (true) {
            val iterator222: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(quests[0], w.warm), arrayOf<dynamic>(quests[jsSubtract(quests.length, 1)], w.cool)))
            while (true) {
                val next223: dynamic = iterator222.next()
                if (truth(next223.done)) break
                var node: dynamic = absent
                var x: dynamic = absent
                val unpacked224: dynamic = fromIterable(next223.value)
                node = unpacked224[0]
                x = unpacked224[1]
                node.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<div class=\"grow\"><b>", global_esc(x.label)), "</b><small>"), x.seconds), " s · intensidad suave y progresiva</small></div><button type=\"button\" class=\"btn ghost\" onclick=\"technique('"), x.id), "')\" aria-label=\"Ver demostración del cardio\">↗</button>")
            }
        }
        return absent
    })
    global_adjustRoutine24 = global_Training23.adjust
    global_Training23.adjust = (fun(r: dynamic, p: dynamic): dynamic {
        val ap: dynamic = global_GoalPlans.adjusted(p)
        val used: dynamic = jsConstruct(global_Set, js("[]"))
        val items: dynamic = global_adjustRoutine24(r, ap)
        return items.map((fun(x: dynamic): dynamic {
            var e: dynamic = global_E.byId(x.id)
            if (truth(jsAnd((!truth(global_Adapt.allowed(e, ap)))) { jsOr((!truth(r.gymOnly23))) { (p.equipment === "gym") } })) {
                val options: dynamic = global_Training23.pool(ap, (if (truth(dynamicValue(jsonValue("[\"Cardio\",\"Movilidad\"]")).includes(e.category))) (if (truth((e.category === "Cardio"))) "cardio" else "mobility") else (if (truth((ap.focus === "lower"))) "legs" else (if (truth((ap.focus === "core"))) "core" else "full")))).filter((fun(v: dynamic): dynamic {
                    return (!truth(used.has(v.baseId)))
                }))
                val same: dynamic = options.filter((fun(v: dynamic): dynamic {
                    return (v.group === e.group)
                }))
                if (truth(dynamicValue((if (truth(same.length)) same else options)).length)) {
                    e = dynamicValue((if (truth(same.length)) same else options))[0]
                }
            }
            used.add(e.baseId)
            return (if (truth((e.id === x.id))) x else jsObject { obj231 ->
                copyFields(obj231, global_Training23.dose(e, ap, (if (truth((r.category === "Cardio"))) "cardio" else (if (truth((r.category === "Movilidad"))) "recovery" else "strength"))))
                copyFields(obj231, (if (truth(x.side)) jsObject { obj232 ->
                    obj232["side"] = x.side
                } else js("({})")))
            })
        }))
    })
    global_allRoutines = (fun(): dynamic {
        return global_V24UI.allRoutines().map((fun(r: dynamic): dynamic {
            if (truth((!truth(global_state.profile)))) {
                return r
            }
            val p: dynamic = jsObject { obj235 ->
                copyFields(obj235, global_state.profile)
                obj235["difficulty"] = jsOr(global_OwnRoutines18.keys[r.level]) { global_state.profile.difficulty }
            }
            val items: dynamic = global_Training23.adjust(r, p)
            return jsObject { obj236 ->
                copyFields(obj236, r)
                obj236["items"] = items
                obj236["minutes"] = jsAdd(global_OwnRoutines18.estimate(items), 8)
                obj236["goalContext24"] = global_GoalContext24.interpret(p).reason
            }
        }))
    })
    global_openCoach = (fun(): dynamic {
        global_V24UI.openCoach()
        onPresent(global_Dollar("#modal .progress-panel20")) { value239 -> value239.remove() }
        val panel: dynamic = global_Dollar("#modal .learning-card")
        val p: dynamic = global_state.profile
        val i: dynamic = global_GoalContext24.interpret(p)
        val next: dynamic = global_todayDay()
        if (truth(panel)) {
            panel.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<span class=\"eyebrow\">GUÍA PERSONAL</span><h3>", global_esc(global_GoalGroups14.groups[i.chosen].name)), "</h3><p>"), global_esc(i.reason)), "</p><p>"), global_esc(global_Adapt.mode(global_GoalPlans.adjusted(p)).reason)), "</p>"), (if (truth(onPresent(next) { value242 -> value242.items.length })) jsAdd(jsAdd(jsAdd(jsAdd("<p><b>Hoy:</b> ", global_esc(next.items.map((fun(x: dynamic): dynamic {
                return global_E.byId(x.id).name
            })).join(" · "))), "</p><button type=\"button\" class=\"btn primary\" onclick=\"openGuidedSession11('"), next.date), "')\">Abrir mi sesión de hoy</button>") else "")), "<button type=\"button\" class=\"btn ghost\" onclick=\"openReference20()\">Consultar técnica y fuentes</button>")
        }
        global_modalButtons24()
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("objetivo|meta|intenci[oó]n|calent|cardio.*final", "i")).test(q))) {
            val i: dynamic = global_GoalContext24.interpret(global_state.profile)
            return jsAdd(jsAdd(jsAdd(global_companionDisplayName(), ": "), i.reason), " La sesión incluye cardio al inicio y cardio suave al final, con selección compatible. Los registros confirmados y el esfuerzo guían la revisión semanal.")
        }
        return global_V24UI.answerCoach(q)
    })
    global_refreshReminderStatus = (fun(): dynamic {
        if (truth(jsOr((!truth(global_Dollar("#nativeStatus")))) { (!truth(onPresent(global_window.Android) { value248 -> value248.notificationStatus })) })) {
            return absent
        }
        try {
            global_reminderStatus = global_JSON.parse(global_Android.notificationStatus())
            global_Dollar("#nativeStatus").textContent = jsOr(global_reminderStatus.error) { (if (truth((!truth(global_reminderStatus.allowed)))) "Las notificaciones están bloqueadas o pendientes de permiso. Abre Permisos de Android para habilitarlas." else (if (truth((!truth(global_reminderStatus.enabled)))) "Permiso concedido. Los recordatorios están desactivados." else jsAdd("Avisos activados. Próximo aviso aproximado: ", jsOr(global_reminderStatus.next) { "pendiente de programación" }))) }
        } catch (error: dynamic) {
            global_Dollar("#nativeStatus").textContent = "No se pudo consultar el estado de las notificaciones."
        }
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V24UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target255: dynamic = n
                val assigned257: dynamic = "EL DESPERTAR · V24"
                target255.textContent = assigned257
                assigned257
            }
        }))
        return absent
    })
    global_openDiagnostics20 = (fun(): dynamic {
        global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section><h2>Estado del contenido local · V24</h2><p>", global_E.exercises.length), " fichas · "), global_Catalog.routines.length), " rutinas · "), global_Offline20.count()), " clips 2D sin conexión.</p><p>Medios locales: "), dynamicValue(jsDivide(jsOr(global_OfflineManifest20.bytes) { 0 }, 1000000)).toFixed(1)), " MB. Cardio guiado, bloques y descansos de 30, 60 o 90 segundos.</p><p>"), global_Runtime20.errors.length), " errores de interfaz registrados en esta apertura.</p><p>El intérprete local combina la elección del perfil, el objetivo escrito y los objetivos personales activos. Los registros confirmados y el esfuerzo alimentan la revisión semanal.</p><p class=\"footnote\">El filtro usa lesiones e indicaciones declaradas; no diagnostica ni prescribe un tratamiento.</p></section>"))
        return absent
    })
    global_window.HunterV24 = jsObject { obj262 ->
        obj262["Nav24"] = global_Nav24
        obj262["Warm24"] = global_Warm24
    }
    global_state.appVersion = 24
    global_currentCycle()
    global_save()
    global_render()
    global_syncNative()
}
