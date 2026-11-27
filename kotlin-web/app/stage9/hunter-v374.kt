@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterv374

import hunter.browser.*

@JsName("HunterImprove374") private external var global_HunterImprove374: dynamic
@JsName("interpretBefore374") private external var global_interpretBefore374: dynamic
@JsName("profileBefore374") private external var global_profileBefore374: dynamic
@JsName("goalSelectionBefore374") private external var global_goalSelectionBefore374: dynamic
@JsName("previewGoal374") private external var global_previewGoal374: dynamic
@JsName("applyGoal374") private external var global_applyGoal374: dynamic
@JsName("submitBefore374") private external var global_submitBefore374: dynamic
@JsName("getSafeText374") private external var global_getSafeText374: dynamic
@JsName("answerBefore374") private external var global_answerBefore374: dynamic
@JsName("namesSafe374") private external var global_namesSafe374: dynamic
@JsName("diagnosticsBefore374") private external var global_diagnosticsBefore374: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Infinity") private external var global_Infinity: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("goalSelection14") private external var global_goalSelection14: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("injuryDraft") private external var global_injuryDraft: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("localStorage") private external var global_localStorage: dynamic
@JsName("KEY") private external var global_KEY: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("console") private external var global_console: dynamic

internal fun installHunterV374() {
    global_previewGoal374 = fun(form: dynamic): dynamic {
        return global_HunterImprove374.refresh(form, false)
    }
    global_applyGoal374 = fun(form: dynamic): dynamic {
        if (truth((!truth(onPresent(form) { value95 -> run { val owner97: dynamic = value95; onPresent(owner97.reportValidity) { method96 -> invokeWith(method96, owner97, js("[]")) } } })))) {
            return global_toast("Completa los datos obligatorios antes de guardar.")
        }
        val r: dynamic = global_HunterImprove374.refresh(form, true)
        if (truth(jsAnd(onPresent(r) { value98 -> value98.medical }) { (!truth(global_injuryDraft.some((fun(i: dynamic): dynamic {
            return (i.stage !== "past")
        })))) })) {
            val details: dynamic = form.querySelector("#healthDetails")
            if (truth(details)) {
                details["open"] = true
                details.scrollIntoView(jsonValue("{\"block\":\"nearest\"}"))
            }
            return global_toast("Tu meta menciona dolor o lesión. Completa Lesiones y salud antes de guardar el plan.")
        }
        form.dataset.confirmGoal374 = "true"
        form.requestSubmit()
        return absent
    }
    global_getSafeText374 = fun(text: dynamic): dynamic {
        return global_String(jsOr(text) { "" }).slice(0, 300)
    }
    global_namesSafe374 = fun(groups: dynamic): dynamic {
        return groups.map((fun(g: dynamic): dynamic {
            return jsOr(onPresent(global_GoalGroups14.groups[g]) { value123 -> value123.name }) { g }
        })).join(" y ")
    }
    global_HunterImprove374 = dynamicValue((fun(): dynamic {
        var clean: dynamic = absent
        var lex: dynamic = absent
        var target: dynamic = absent
        var medicalRE: dynamic = absent
        var negated: dynamic = absent
        var interpret: dynamic = absent
        var getFormInput: dynamic = absent
        var names: dynamic = absent
        var refresh: dynamic = absent
        var confirmation: dynamic = absent
        interpret = fun(text: dynamic, arg4: dynamic): dynamic {
            var chosen: dynamic = absent
            chosen = definedOr(arg4) { "habit" }
            val raw: dynamic = clean(text)
            val parts: dynamic = negated(raw)
            val hits: dynamic = js("[]")
            if (true) {
                val iterator5: dynamic = iteratorOf(global_Object.entries(lex))
                while (true) {
                    val next6: dynamic = iterator5.next()
                    if (truth(next6.done)) break
                    var group: dynamic = absent
                    var regexes: dynamic = absent
                    val unpacked7: dynamic = fromIterable(next6.value)
                    group = unpacked7[0]
                    regexes = unpacked7[1]
                    var idx: dynamic = global_Infinity
                    var matched: dynamic = 0
                    if (true) {
                        val iterator8: dynamic = iteratorOf(regexes)
                        while (true) {
                            val next9: dynamic = iterator8.next()
                            if (truth(next9.done)) break
                            var re: dynamic = absent
                            re = next9.value
                            re.lastIndex = 0
                            val m: dynamic = re.exec(parts)
                            if (truth(m)) {
                                idx = global_Math.min(idx, m.index)
                                run {
                                    val previous12: dynamic = numeric(matched)
                                    matched = jsAdd(previous12, 1)
                                }
                            }
                        }
                    }
                    if (truth(matched)) {
                        hits.push(jsObject { obj13 ->
                            obj13["group"] = group
                            obj13["index"] = idx
                            obj13["score"] = matched
                        })
                    }
                }
            }
            hits.sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(jsSubtract(a.index, b.index)) { jsSubtract(b.score, a.score) }
            }))
            val alternatives: dynamic = hits.map((fun(x: dynamic): dynamic {
                return x.group
            }))
            val primary: dynamic = jsOr(alternatives[0]) { (if (truth(hasKey(chosen, target))) chosen else "habit") }
            val noImpact: dynamic = dynamicValue(jsRegExp("\\b(?:sin\\s+(?:saltos?|impacto)|evitar\\s+(?:los\\s+)?saltos?|no\\s+(?:quiero|puedo)\\s+salt(?:ar|os?))\\b", "")).test(raw)
            val restrictions: dynamic = js("({})")
            if (truth(noImpact)) {
                restrictions.noImpact = true
            }
            val terms: dynamic = jsObject { obj16 ->
                obj16["noFloor"] = jsRegExp("\\b(?:sin\\s+suelo|no\\s+(?:puedo|quiero)\\s+(?:tumbarme|arrodillarme))\\b", "")
                obj16["noOverhead"] = jsRegExp("\\b(?:no\\s+(?:puedo|quiero)\\s+levantar\\s+los\\s+brazos|sin\\s+elevar\\s+los\\s+brazos)\\b", "")
                obj16["noLoad"] = jsRegExp("\\b(?:sin\\s+(?:mancuernas|pesas|carga)|no\\s+(?:puedo|quiero)\\s+usar\\s+pesas)\\b", "")
                obj16["noWrist"] = jsRegExp("\\b(?:sin\\s+apoyo\\s+en\\s+(?:las\\s+)?manos|no\\s+(?:puedo|quiero)\\s+apoyar\\s+las\\s+manos)\\b", "")
                obj16["noKnee"] = jsRegExp("\\b(?:sin\\s+flexionar\\s+(?:las\\s+)?rodillas|no\\s+(?:puedo|quiero)\\s+doblar\\s+(?:las\\s+)?rodillas)\\b", "")
                obj16["seatedOnly"] = jsRegExp("\\b(?:solo|unicamente)\\s+(?:estar\\s+)?sentado\\b", "")
            }
            if (true) {
                val iterator17: dynamic = iteratorOf(global_Object.entries(terms))
                while (true) {
                    val next18: dynamic = iterator17.next()
                    if (truth(next18.done)) break
                    var k: dynamic = absent
                    var r: dynamic = absent
                    val unpacked19: dynamic = fromIterable(next18.value)
                    k = unpacked19[0]
                    r = unpacked19[1]
                    if (truth(r.test(raw))) {
                        restrictions[k] = true
                    }
                }
            }
            var focus: dynamic = "full"
            if (truth(dynamicValue(jsRegExp("\\b(?:piernas?|gluteos?|tren\\s+inferior)\\b", "")).test(parts))) {
                focus = "lower"
            } else {
                if (truth(dynamicValue(jsRegExp("\\b(?:brazos?|pecho|hombros?|tren\\s+superior)\\b", "")).test(parts))) {
                    focus = "upper"
                } else {
                    if (truth(dynamicValue(jsRegExp("\\b(?:abdomen|core|zona\\s+media)\\b", "")).test(parts))) {
                        focus = "core"
                    }
                }
            }
            val medical: dynamic = medicalRE.test(raw.replace(jsRegExp("\\b(?:sin|no\\s+tengo)\\s+(?:ninguna?\\s+)?(?:lesiones?|dolor|molestias?)\\b", "g"), " "))
            val count: dynamic = hits.length
            var specific: dynamic = target[primary]
            if (truth((primary === "build"))) {
                specific = (if (truth((focus === "upper"))) "upper" else (if (truth((focus === "lower"))) "lower" else (if (truth((focus === "core"))) "core" else (if (truth(jsAnd(dynamicValue(jsRegExp("\\bfuerza\\b", "")).test(parts)) { (!truth(dynamicValue(jsRegExp("estetic|muscul|tonific|hipertrof|defin", "")).test(parts))) })) "strength" else "muscle"))))
            }
            if (truth(jsAnd((primary === "fitness")) { dynamicValue(jsRegExp("bajar\\s+(?:de\\s+)?peso|quemar\\s+grasa", "")).test(parts) })) {
                specific = "activity"
            }
            if (truth((primary === "movement"))) {
                if (truth(dynamicValue(jsRegExp("equilibrio", "")).test(parts))) {
                    specific = "balance"
                } else {
                    if (truth(dynamicValue(jsRegExp("flexibil|estiramiento", "")).test(parts))) {
                        specific = "flexibility"
                    } else {
                        if (truth(dynamicValue(jsRegExp("postura", "")).test(parts))) {
                            specific = "posture"
                        }
                    }
                }
            }
            if (truth(jsAnd((primary === "habit")) { noImpact })) {
                specific = "lowimpact"
            }
            if (truth(jsAnd((primary === "adapted")) { dynamicValue(jsRegExp("solo\\s+sentado", "")).test(parts) })) {
                specific = "seated"
            }
            if (truth((!truth(global_GoalPlans.plans[specific])))) {
                specific = target[primary]
            }
            return jsObject { obj22 ->
                obj22["primary"] = primary
                obj22["alternatives"] = alternatives
                obj22["target"] = specific
                obj22["focus"] = focus
                obj22["restrictions"] = restrictions
                obj22["noImpact"] = noImpact
                obj22["medical"] = medical
                obj22["recognized"] = count
                obj22["raw"] = raw
                obj22["reason"] = jsOr(onPresent(global_GoalGroups14.groups[primary]) { value23 -> value23.why }) { "" }
                obj22["confidence"] = (if (truth(count)) jsAdd(jsAdd("Se identificaron ", count), " objetivo(s) en tu descripción.") else "No se identificaron palabras clave; se conserva la familia elegida.")
            }
        }
        refresh = fun(form: dynamic, arg31: dynamic): dynamic {
            var apply: dynamic = absent
            apply = definedOr(arg31) { false }
            if (truth((!truth(onPresent(form) { value32 -> onPresent(value32.elements) { value33 -> value33.goalGroup } })))) {
                return null
            }
            val selected: dynamic = form.elements.goalGroup["value"]
            val results: dynamic = interpret(getFormInput(form), selected)
            val userChoice: dynamic = form.dataset.manualGroup374
            val primary: dynamic = (if (truth(jsAnd(jsAnd(apply) { userChoice }) { hasKey(userChoice, target) })) userChoice else results.primary)
            if (truth(jsAnd(jsMore(results.recognized, 0)) { (!truth(userChoice)) })) {
                form.elements.goalGroup["value"] = primary
            }
            val active: dynamic = form.elements.goalGroup["value"]
            val targetCode: dynamic = (if (truth(jsAnd(results.recognized) { (active === results.primary) })) results.target else jsOr(target[active]) { "general" })
            if (truth(form.elements.finalTarget)) {
                form.elements.finalTarget["value"] = targetCode
            }
            if (truth(form.elements.goal)) {
                form.elements.goal["value"] = targetCode
            }
            if (truth(form.elements.focus)) {
                if (truth(jsAnd((form.dataset.autofocus374 === "true")) { (results.focus === "full") })) {
                    form.elements.focus["value"] = "full"
                }
                if (truth((results.focus !== "full"))) {
                    form.elements.focus["value"] = results.focus
                    form.dataset.autofocus374 = "true"
                }
            }
            val p: dynamic = jsOr(global_state.profile) { js("({})") }
            val prev: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsArray { array46 ->
                appendAll(array46, jsOr(p.goalAutoRestrictions374) { js("[]") })
                appendAll(array46, dynamicValue(jsOr(form.dataset.autoRestrictions374) { "" }).split(",").filter(global_Boolean))
            }))
            if (true) {
                val iterator47: dynamic = iteratorOf(global_Object.keys(global_Adapt.restrictions))
                while (true) {
                    val next48: dynamic = iterator47.next()
                    if (truth(next48.done)) break
                    var k: dynamic = absent
                    k = next48.value
                    val el: dynamic = form.elements[jsAdd("r_", k)]
                    if (truth(jsOr((!truth(el))) { (el.dataset.manual374 === "true") })) {
                        continue
                    }
                    if (truth(results.restrictions[k])) {
                        el.checked = true
                        el.dataset.auto374 = "true"
                    } else {
                        if (truth(jsOr(prev.has(k)) { (el.dataset.auto374 === "true") })) {
                            el.checked = false
                            removeKey(el.dataset, "auto374")
                        }
                    }
                }
            }
            form.dataset.autoRestrictions374 = global_Object.keys(results.restrictions).filter((fun(k: dynamic): dynamic {
                return results.restrictions[k]
            })).join(",")
            val msg: dynamic = form.querySelector("#intentResult")
            if (truth(msg)) {
                msg.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<b>", global_esc(jsOr(onPresent(global_GoalGroups14.groups[active]) { value59 -> value59.name }) { active })), "</b><p>"), global_esc(dynamicValue(jsOr(global_GoalPlans.plans[targetCode]) { global_GoalPlans.plans.general }).why)), "</p><small>"), global_esc(results.confidence)), (if (truth(results.medical)) " Revisa las lesiones, limitaciones y aprobación profesional." else "")), "</small>")
            }
            val `out`: dynamic = form.querySelector("#intentPreview374")
            if (truth(`out`)) {
                val list: dynamic = (if (truth(results.alternatives.length)) names(results.alternatives) else global_GoalGroups14.groups[active].name)
                `out`.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<span class=\"eyebrow\">INTERPRETACIÓN LOCAL · SIN CONEXIÓN</span><b>", global_esc(list)), "</b><p>"), global_esc((if (truth(jsMore(results.alternatives.length, 1))) "Se combinarán estas metas respetando tu prioridad principal." else "Se adaptará el plan a la prioridad mostrada."))), "</p><small>"), global_esc(jsAdd(jsAdd(jsAdd("Prioridad: ", global_GoalGroups14.groups[active].name), " · Objetivo: "), jsOr(onPresent(global_GoalPlans.plans[targetCode]) { value62 -> value62.name }) { targetCode }))), "</small>"), (if (truth(results.medical)) "<p class=\"intent-warning374\">El texto menciona dolor o lesión. El generador puede bloquear el plan automático.</p>" else ""))
            }
            return jsObject { obj63 ->
                copyFields(obj63, results)
                obj63["primary"] = active
                obj63["target"] = targetCode
            }
        }
        confirmation = fun(goal: dynamic, after: dynamic, verified: dynamic): dynamic {
            val eligible: dynamic = global_E.eligible(after)
            val cycles: dynamic = (if (truth(eligible)) global_currentCycle() else null)
            val now: dynamic = global_E.dateKey()
            val days: dynamic = jsOr(onPresent(cycles) { value66 -> onPresent(value66.days) { value67 -> value67.filter((fun(d: dynamic): dynamic {
                return jsAnd(jsAtLeast(d.date, now)) { (!truth(global_Weekly.active(global_state.logs[d.date]))) }
            })) } }) { js("[]") }
            val goals: dynamic = (if (truth(goal.alternatives.length)) names(goal.alternatives) else jsOr(onPresent(global_GoalGroups14.groups[goal.primary]) { value68 -> value68.name }) { "Objetivo general" })
            val detail: dynamic = (if (truth(eligible)) jsAdd(jsAdd("Se revisó el plan de la semana actual. ", days.length), " día(s) futuros sin actividad registrada pueden actualizarse según tus nuevos criterios. Las sesiones completadas se conservan.") else "La configuración del perfil quedó guardada, pero las condiciones declaradas requieren revisión antes de generar automáticamente un entrenamiento.")
            global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section class=\"goal-confirm374\" role=\"status\" aria-live=\"polite\"><span class=\"save-icon374\">✓</span><span class=\"eyebrow\">HUNTER SYSTEM · 37.4</span><h2>Configuración guardada</h2><p>Tu acompañante aplicó la interpretación local de tus preferencias.</p><div class=\"save-summary374\"><b>", global_esc(goals)), "</b><p>"), global_esc(detail)), "</p><small>Modalidad: "), global_esc((if (truth((after.trainingMode === "routines"))) "Rutinas" else "Misiones"))), " · Nivel: "), global_esc(jsOr(after.difficulty) { "basic" })), "</small></div><p class=\"footnote\">Se conservaron los registros ya realizados. La adaptación es basada en reglas locales, no en un modelo de IA conversacional.</p><button type=\"button\" class=\"btn primary full\" onclick=\"closeActiveModal()\">Continuar</button></section>"))
            return absent
        }
        clean = (fun(s: dynamic): dynamic {
            return global_String(jsOr(s) { "" }).toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "").replace(jsRegExp("[’‘]", "g"), "'")
        })
        lex = jsObject { obj1 ->
            obj1["build"] = arrayOf<dynamic>(jsRegExp("cuerpo\\s+estetic[oa]", "g"), jsRegExp("estetic[oa]", "g"), jsRegExp("tonific", "g"), jsRegExp("definicion\\s+muscular", "g"), jsRegExp("definir\\s+(?:mi\\s+)?(?:cuerpo|musculos)", "g"), jsRegExp("ganar\\s+muscul", "g"), jsRegExp("masa\\s+muscular", "g"), jsRegExp("hipertrof", "g"), jsRegExp("fuerza", "g"), jsRegExp("fortalec", "g"), jsRegExp("muscul", "g"), jsRegExp("pecho|espalda|biceps|triceps|gluteos|abdomen|marcar\\s+abdomen", "g"))
            obj1["fitness"] = arrayOf<dynamic>(jsRegExp("resistencia\\s+(?:cardiovascular|aerobica|fisica)", "g"), jsRegExp("cardio", "g"), jsRegExp("bajar\\s+(?:de\\s+)?peso", "g"), jsRegExp("quemar\\s+grasa", "g"), jsRegExp("condicion\\s+fisica", "g"), jsRegExp("aguantar\\s+mas", "g"), jsRegExp("correr\\s+mas", "g"))
            obj1["athletic"] = arrayOf<dynamic>(jsRegExp("agilidad", "g"), jsRegExp("agil(?:es)?", "g"), jsRegExp("coordinacion", "g"), jsRegExp("potencia", "g"), jsRegExp("velocidad", "g"), jsRegExp("reaccion", "g"), jsRegExp("sprint", "g"), jsRegExp("salto", "g"), jsRegExp("explosiv", "g"), jsRegExp("deportiv", "g"))
            obj1["movement"] = arrayOf<dynamic>(jsRegExp("movilidad", "g"), jsRegExp("flexibilidad", "g"), jsRegExp("estiramiento", "g"), jsRegExp("equilibrio", "g"), jsRegExp("postura", "g"), jsRegExp("control\\s+motor", "g"), jsRegExp("rango\\s+de\\s+movimiento", "g"))
            obj1["habit"] = arrayOf<dynamic>(jsRegExp("constancia", "g"), jsRegExp("disciplina", "g"), jsRegExp("habito", "g"), jsRegExp("bienestar", "g"), jsRegExp("rutina\\s+(?:estable|diaria)", "g"), jsRegExp("ser\\s+activo", "g"))
            obj1["adapted"] = arrayOf<dynamic>(jsRegExp("actividad\\s+adaptada", "g"), jsRegExp("retomar\\s+(?:el\\s+)?entrenamiento", "g"), jsRegExp("regresar\\s+a\\s+entrenar", "g"), jsRegExp("solo\\s+sentado", "g"))
        }
        target = jsonValue("{\"build\":\"muscle\",\"fitness\":\"endurance\",\"athletic\":\"athletic\",\"movement\":\"mobility\",\"habit\":\"general\",\"adapted\":\"returning\"}")
        medicalRE = jsRegExp("\\b(?:lesion(?:es)?|hernia|fractura|cirugia|dolor|duele|molestia|rehabilitacion)\\b", "")
        negated = (fun(s: dynamic): dynamic {
            return s.replace(jsRegExp("\\b(?:no quiero|no deseo|no busco|evitar|evito|sin interes en)\\s+[^.;,]+", "g"), " ")
        })
        getFormInput = (fun(f: dynamic): dynamic {
            return jsOr(onPresent(f) { value24 -> onPresent(value24.elements) { value25 -> onPresent(value25.finalGoal) { value26 -> value26["value"] } } }) { "" }
        })
        names = (fun(groups: dynamic): dynamic {
            return groups.map((fun(g: dynamic): dynamic {
                return jsOr(onPresent(global_GoalGroups14.groups[g]) { value28 -> value28.name }) { g }
            })).join(" · ")
        })
        return jsObject { obj69 ->
            obj69["interpret"] = interpret
            obj69["refresh"] = refresh
            obj69["confirmation"] = confirmation
        }
    }))()
    global_interpretBefore374 = global_GoalGroups14.interpret
    global_GoalGroups14.interpret = (fun(text: dynamic, chosen: dynamic): dynamic {
        val r: dynamic = global_HunterImprove374.interpret(text, chosen)
        val g: dynamic = global_GoalGroups14.groups[r.primary]
        return jsObject { obj74 ->
            obj74["group"] = r.primary
            obj74["target"] = r.target
            obj74["focus"] = r.focus
            obj74["medical"] = r.medical
            obj74["noImpact"] = r.noImpact
            obj74["alternatives"] = r.alternatives
            obj74["ambiguous"] = false
            obj74["reason"] = jsOr(onPresent(g) { value75 -> value75.why }) { "" }
            obj74["measure"] = jsOr(onPresent(g) { value76 -> value76.measure }) { "" }
        }
    })
    global_profileBefore374 = global_profileForm
    global_profileForm = (fun(arg79: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg79) { false }
        val holder: dynamic = global_document.createElement("div")
        holder.innerHTML = global_profileBefore374(initial)
        val form: dynamic = holder.querySelector("form")
        if (truth((!truth(form)))) {
            return holder.innerHTML
        }
        val f: dynamic = form.elements.finalGoal
        if (truth(f)) {
            f.setAttribute("oninput", "previewGoal374(this.form)")
            f.setAttribute("onchange", "previewGoal374(this.form)")
            f.setAttribute("aria-describedby", "intentPreview374")
            val row: dynamic = global_document.createElement("div")
            row.className = "goal-control374"
            row.innerHTML = "<div id=\"intentPreview374\" class=\"goal-preview374\" aria-live=\"polite\"></div><button class=\"btn primary full goal-apply374\" type=\"button\" onclick=\"applyGoal374(this.form)\">✦ Interpretar con el acompañante y guardar</button><p class=\"footnote\">Al pulsar el botón se reinterpreta el texto actual y se guardan sus preferencias. Los objetivos que elimines de este campo dejan de formar parte de esa descripción. Las metas personales se administran por separado. Puedes escribir «cuerpo estético, agilidad» o dejar solo «cuerpo estético».</p>"
            dynamicValue(jsOr(form.querySelector("#intentResult")) { f }).after(row)
        }
        val sel: dynamic = form.elements.goalGroup
        if (truth(sel)) {
            sel.setAttribute("onchange", "this.form.dataset.manualGroup374=this.value;previewGoal374(this.form)")
        }
        if (truth(form.elements.finalGoal)) {
            if (true) {
                val iterator86: dynamic = iteratorOf(form.querySelectorAll("[name^=\"r_\"]"))
                while (true) {
                    val next87: dynamic = iterator86.next()
                    if (truth(next87.done)) break
                    var e: dynamic = absent
                    e = next87.value
                    e.setAttribute("onchange", "this.dataset.manual374=\"true\"")
                }
            }
        }
        onPresent(form.querySelector("[type=submit]")) { value88 -> value88.setAttribute("aria-label", "Guardar todos los cambios del perfil") }
        global_HunterImprove374.refresh(form, false)
        return holder.innerHTML
    })
    global_goalSelectionBefore374 = global_goalSelection14
    global_goalSelection14 = (fun(form: dynamic): dynamic {
        return global_HunterImprove374.refresh(form, true)
    })
    global_submitBefore374 = global_submitProfile
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val form: dynamic = ev.target
        val was: dynamic = global_state.profile
        val requested: dynamic = (form.dataset.confirmGoal374 === "true")
        val goal: dynamic = global_HunterImprove374.refresh(form, true)
        val desc: dynamic = global_getSafeText374(onPresent(form.elements.finalGoal) { value105 -> value105["value"] })
        global_submitBefore374(ev)
        if (truth(jsOr(jsOr((!truth(global_state.profile))) { (global_state.profile === was) }) { (global_state.profile.finalGoal !== desc) })) {
            return absent
        }
        global_state.profile.goalAutoRestrictions374 = dynamicValue(jsOr(form.dataset.autoRestrictions374) { "" }).split(",").filter(global_Boolean)
        global_state.profile.interpretation374 = jsObject { obj110 ->
            obj110["text"] = desc
            obj110["groups"] = goal.alternatives
            obj110["primary"] = goal.primary
            obj110["target"] = global_state.profile.finalTarget
            obj110["updatedAt"] = jsConstruct(global_Date, js("[]")).toISOString()
        }
        if (truth((!truth(global_save())))) {
            return absent
        }
        var valid: dynamic = false
        try {
            val stored: dynamic = global_JSON.parse(global_localStorage.getItem(global_KEY))
            valid = (onPresent(stored) { value111 -> onPresent(value111.profile) { value112 -> onPresent(value112.interpretation374) { value113 -> value113.text } } } === desc)
        } catch (e: dynamic) {

        }
        if (truth((!truth(valid)))) {
            return absent
        }
        if (truth(requested)) {
            try {
                global_HunterImprove374.confirmation(goal, global_state.profile, valid)
            } catch (e: dynamic) {
                global_toast("Configuración guardada. Revisa tu plan actualizado en Misiones.")
            }
        } else {
            global_toast("Perfil y criterios de entrenamiento guardados.")
        }
        return absent
    })
    global_answerBefore374 = global_answerCoach
    global_answerCoach = (fun(input: dynamic): dynamic {
        val s: dynamic = global_String(jsOr(input) { "" })
        if (truth(jsAnd(dynamicValue(jsRegExp("(?:cuerpo\\s+est[eé]tico|tonificar|definir|marcar\\s+abdomen|objetivos?\\s+combinados|agilidad\\s+y\\s+fuerza|mi\\s+meta|interpretar\\s+objetivo)", "i")).test(s)) { global_state.profile })) {
            val goal: dynamic = global_HunterImprove374.interpret(jsOr(global_state.profile.finalGoal) { "" }, global_state.profile.goalGroup)
            val label: dynamic = (if (truth(goal.alternatives.length)) global_namesSafe374(goal.alternatives) else onPresent(global_GoalGroups14.groups[goal.primary]) { value118 -> value118.name })
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(global_companionDisplayName(), ": Según tu último perfil guardado, estás priorizando "), label), ". "), jsOr(onPresent(global_GoalPlans.plans[global_state.profile.finalTarget]) { value119 -> value119.why }) { goal.reason }), " Cambia el texto en Cazador y pulsa «Interpretar con el acompañante y guardar» para actualizar el plan. Los registros finalizados se conservan.")
        }
        return global_answerBefore374(input)
    })
    global_diagnosticsBefore374 = global_openDiagnostics20
    global_openDiagnostics20 = (fun(): dynamic {
        val total: dynamic = global_E.exercises.length
        val routines: dynamic = jsOr(onPresent(global_Catalog.routines) { value126 -> value126.length }) { 0 }
        val errors: dynamic = jsOr(onPresent(global_Runtime20.errors) { value127 -> value127.length }) { 0 }
        global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section><h2>Diagnóstico local · V37.4</h2><p>", total), " ejercicios disponibles · "), routines), " rutinas de catálogo.</p><p>Errores de interfaz registrados en esta sesión: "), errors), ".</p><p>Interpretación: reglas locales por palabras y objetivos, sin enviar el texto a servidores.</p><p>Los planes se recalculan para días elegibles; los registros completados se mantienen.</p><p class=\"footnote\">Si necesitas cambiar de APK con firma diferente, exporta antes tu copia de seguridad desde Cazador.</p></section>"))
        return absent
    })
    global_window.HunterV374 = jsObject { obj130 ->
        obj130["version"] = "37.4"
        obj130["intention"] = global_HunterImprove374
    }
    try {
        global_render()
    } catch (e: dynamic) {
        global_console.error("V37.4 UI", e)
    }
}
