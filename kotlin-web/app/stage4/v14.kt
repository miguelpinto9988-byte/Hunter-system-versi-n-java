@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv14

import hunter.browser.*

@JsName("V14UI") private external var global_V14UI: dynamic
@JsName("ownLevel14") private external var global_ownLevel14: dynamic
@JsName("ownFamily14") private external var global_ownFamily14: dynamic
@JsName("ownEquipment14") private external var global_ownEquipment14: dynamic
@JsName("ownLength14") private external var global_ownLength14: dynamic
@JsName("goalSelection14") private external var global_goalSelection14: dynamic
@JsName("setCamera14") private external var global_setCamera14: dynamic
@JsName("cameraControls14") private external var global_cameraControls14: dynamic
@JsName("planCard14") private external var global_planCard14: dynamic
@JsName("ownLibraryView14") private external var global_ownLibraryView14: dynamic
@JsName("staticActors14") private external var global_staticActors14: dynamic
@JsName("releaseStatic14") private external var global_releaseStatic14: dynamic
@JsName("previewModel14") private external var global_previewModel14: dynamic
@JsName("profileModels14") private external var global_profileModels14: dynamic
@JsName("mountStatic14") private external var global_mountStatic14: dynamic
@JsName("gallery14") private external var global_gallery14: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("routineDetail") private external var global_routineDetail: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("previewCompanionChoice") private external var global_previewCompanionChoice: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("interpretGoal") private external var global_interpretGoal: dynamic
@JsName("trainingModeFields11") private external var global_trainingModeFields11: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("injuryDraft") private external var global_injuryDraft: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("rebuildPending13") private external var global_rebuildPending13: dynamic
@JsName("RoutinePlanner") private external var global_RoutinePlanner: dynamic
@JsName("allRoutines10") private external var global_allRoutines10: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("Exercise3D") private external var global_Exercise3D: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("vectorPanel") private external var global_vectorPanel: dynamic
@JsName("MotionPlayback") private external var global_MotionPlayback: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("OwnRoutines14") private external var global_OwnRoutines14: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("routineProfileFilter") private external var global_routineProfileFilter: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("ownLibrary11") private external var global_ownLibrary11: dynamic
@JsName("routineMode") private external var global_routineMode: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("CompanionNames") private external var global_CompanionNames: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("Mesh3D") private external var global_Mesh3D: dynamic
@JsName("IgrisCharacter") private external var global_IgrisCharacter: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("openAnimationGallery") private external var global_openAnimationGallery: dynamic
@JsName("clearGallery13") private external var global_clearGallery13: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic

internal fun installV14() {
    global_goalSelection14 = fun(form: dynamic, arg4: dynamic): dynamic {
        var fromText: dynamic = absent
        fromText = definedOr(arg4) { false }
        val group: dynamic = form.elements.goalGroup["value"]
        val spec: dynamic = global_GoalGroups14.groups[group]
        val result: dynamic = global_GoalGroups14.interpret(form.elements.finalGoal["value"], group)
        val old: dynamic = form.elements.finalTarget["value"]
        var target: dynamic = (if (truth(spec.legacy.includes(old))) old else spec.target)
        if (truth(jsAnd((result.group === group)) { result.target })) {
            target = result.target
        }
        form.elements.finalTarget["value"] = target
        form.elements.goal["value"] = target
        if (truth(jsAnd(jsAnd((result.group === group)) { (result.focus !== "full") }) { form.elements.focus })) {
            form.elements.focus["value"] = result.focus
        }
        if (truth(jsAnd(result.noImpact) { form.elements.r_noImpact })) {
            form.elements.r_noImpact.checked = true
        }
        form.querySelector("#objectiveExplanation").textContent = global_GoalPlans.plans[target].why
        val `out`: dynamic = form.querySelector("#intentResult")
        `out`.innerHTML = ("<b>" + textOf(global_esc(spec.name)) + "</b><p>" + textOf(global_esc(global_GoalPlans.plans[target].why)) + "</p><p><b>Cómo medirlo:</b> " + textOf(global_esc(spec.measure)) + "</p><small>" + textOf((if (truth(result.medical)) "La meta menciona salud: completa lesiones y restricciones antes de generar ejercicios. " else "")) + "" + textOf((if (truth(result.noImpact)) "Se ha marcado «Sin impacto» al mencionar evitar saltos. " else "")) + "" + textOf((if (truth(result.ambiguous)) "Hay varias prioridades. Se conserva la familia elegida; puedes cambiarla. " else "")) + "" + textOf((if (truth(fromText)) "Revisa esta propuesta antes de guardar." else "Esta prioridad se combina con tu equipo, días, dificultad e indicaciones.")) + "</small>")
        return absent
    }
    global_setCamera14 = fun(id: dynamic, `value`: dynamic): dynamic {
        val c: dynamic = global_document.getElementById(id)
        if (truth((!truth(c)))) {
            return absent
        }
        c.dataset.yaw = global_String(global_Number(`value`))
        if (truth((id === "vectorCanvas"))) {
            global_vectorPaint()
        } else {
            if (truth(jsAnd((id === "guidedCanvas")) { global_guide11 })) {
                val step: dynamic = global_guide11.steps[global_guide11.index]
                if (truth((onPresent(step) { value83 -> value83.kind } === "exercise"))) {
                    global_drawVector(c, step.id, jsOr(global_guide11.demoPhase) { 0 })
                }
            }
        }
        val range: dynamic = global_document.querySelector(jsAdd(jsAdd("[data-camera-for=\"", id), "\"]"))
        if (truth(range)) {
            range["value"] = global_Math.round(jsDivide(jsMultiply(global_Number(`value`), 180), global_Math.PI))
        }
        return absent
    }
    global_cameraControls14 = fun(id: dynamic): dynamic {
        return ("<fieldset class=\"model-camera\"><legend>Ángulo del modelo 3D</legend><div class=\"row wrap\"><button type=\"button\" class=\"btn\" onclick=\"setCamera14('" + textOf(id) + "',0)\">Lateral</button><button type=\"button\" class=\"btn\" onclick=\"setCamera14('" + textOf(id) + "',1.570796)\">Frontal</button><button type=\"button\" class=\"btn\" onclick=\"setCamera14('" + textOf(id) + "',0.785398)\">Tres cuartos</button></div><label>Girar la cámara<input aria-label=\"Girar el modelo en 3D\" data-camera-for=\"" + textOf(id) + "\" type=\"range\" min=\"-180\" max=\"180\" value=\"26\" oninput=\"setCamera14('" + textOf(id) + "',this.value*Math.PI/180)\"></label></fieldset>")
    }
    global_planCard14 = fun(arg94: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg94) { global_state.profile }
        if (truth((!truth(p)))) {
            return ""
        }
        val d: dynamic = global_GoalGroups14.plan(p)
        return ("<section class=\"card goal-plan-14\"><span class=\"eyebrow\">TU OBJETIVO → TU PLAN</span><h3>" + textOf(global_esc(d.name)) + "</h3><p>" + textOf(global_esc(d.reason)) + "</p><div class=\"plan-pills\"><span>" + textOf(d.level) + "</span><span>" + textOf(d.days) + " días elegidos</span><span>" + textOf(d.mode) + "</span><span>" + textOf(global_Math.round(jsMultiply(global_TrainingIntensity.scale(p), 100))) + " % de dosis</span></div><p><b>Seguimiento:</b> " + textOf(global_esc(d.measure)) + "</p><p class=\"footnote\">" + textOf(global_esc(d.healthReason)) + " La revisión ocurre cada semana; conserva tus registros y días de descanso.</p></section>")
    }
    global_ownLibraryView14 = fun(): dynamic {
        val level: dynamic = nullableOr(global_ownLevel14) { global_Math.max(0, global_OwnRoutines14.keys.indexOf(onPresent(global_state.profile) { value110 -> value110.difficulty })) }
        val all: dynamic = global_allRoutines().filter((fun(r: dynamic): dynamic {
            return (!truth(r.locked))
        }))
        val familyNames: dynamic = jsObject { obj111 ->
            obj111["all"] = "Todas las materias"
            copyFields(obj111, global_Object.fromEntries(global_Object.entries(global_OwnRoutines14.families).map((fun(arg112: dynamic): dynamic {
                var k: dynamic = absent
                var v: dynamic = absent
                val unpacked113: dynamic = fromIterable(arg112)
                k = unpacked113[0]
                v = unpacked113[1]
                return arrayOf<dynamic>(k, v.name)
            }))))
        }
        var list: dynamic = all.filter((fun(r: dynamic): dynamic {
            return jsAnd((r.level === level)) { (!truth(r.locked)) }
        }))
        if (truth((global_ownFamily14 !== "all"))) {
            list = list.filter((fun(r: dynamic): dynamic {
                return (r.family === global_ownFamily14)
            }))
        }
        if (truth((global_ownEquipment14 !== "all"))) {
            list = list.filter((fun(r: dynamic): dynamic {
                return ((if (truth(r.requirements.includes("db"))) "db" else "body") === global_ownEquipment14)
            }))
        }
        if (truth((global_ownLength14 !== "all"))) {
            list = list.filter((fun(r: dynamic): dynamic {
                return (r.extension === global_ownLength14)
            }))
        }
        if (truth(global_routineProfileFilter)) {
            list = list.filter((fun(r: dynamic): dynamic {
                return global_Adapt.routineCompatible(r, global_state.profile)
            }))
        }
        return ("<button class=\"text-button\" onclick=\"HunterActions.closeLibrary()\">← Centro de entrenamiento</button><div class=\"page-heading\"><span class=\"eyebrow\">BIBLIOTECA PROPIA · GUÍAS 3D</span><h1>Un reto para cada objetivo.</h1><p class=\"muted\">" + textOf(global_OwnRoutines14.routines.length) + " programas nuevos, además del catálogo anterior. Duración estimada con trabajo y descanso; depende de tu ritmo.</p></div><div class=\"routine-level-tabs\" role=\"tablist\" aria-label=\"Niveles de rutina\">" + textOf(global_OwnRoutines14.names.map((fun(name: dynamic, i: dynamic): dynamic {
            return ("<button role=\"tab\" aria-selected=\"" + textOf((i === level)) + "\" class=\"" + textOf((if (truth((i === level))) "active" else "")) + "\" onclick=\"HunterActions.setLibraryLevel(" + textOf(i) + ")\"><b>" + textOf(name) + "</b><small>" + textOf(all.filter((fun(r: dynamic): dynamic {
                return (r.level === i)
            })).length) + " opciones</small></button>")
        })).join("")) + "</div><section class=\"card routine-filters-14\"><label>Materia<select onchange=\"HunterActions.setLibraryFamily(this)\">" + textOf(global_options(familyNames, global_ownFamily14)) + "</select></label><div class=\"fields\"><label>Material<select onchange=\"HunterActions.setLibraryEquipment(this)\">" + textOf(global_options(jsonValue("{\"all\":\"Con y sin peso\",\"body\":\"Peso corporal\",\"db\":\"Mancuernas\"}"), global_ownEquipment14)) + "</select></label><label>Extensión<select onchange=\"HunterActions.setLibraryLength(this)\">" + textOf(global_options(jsonValue("{\"all\":\"Todas\",\"brief\":\"Breve\",\"standard\":\"Completa\",\"long\":\"Extendida\"}"), global_ownLength14)) + "</select></label></div><label class=\"row\"><input type=\"checkbox\" " + textOf((if (truth(global_routineProfileFilter)) "checked" else "")) + " onchange=\"HunterActions.setRoutineProfileFilter(this)\"> Solo compatibles con mi perfil</label></section><div class=\"stack routine-results-14\" role=\"tabpanel\">" + textOf(jsOr(list.map((fun(r: dynamic): dynamic {
            return ("<article class=\"card routine-card-14\"><span class=\"eyebrow\">" + textOf(global_esc((if (truth(r.family)) global_OwnRoutines14.families[r.family].name else r.category))) + " · " + textOf(r.impact) + "</span><h3>" + textOf(global_esc(r.name)) + "</h3><p>" + textOf(r.items.length) + " ejercicios · " + textOf(r.items.reduce((fun(n: dynamic, x: dynamic): dynamic {
                return jsAdd(n, x.sets)
            }), 0)) + " series · ≈ " + textOf(r.minutes) + " min</p><small>" + textOf((if (truth(r.requirements.includes("db"))) "Con mancuernas" else "Peso corporal")) + " · " + textOf((if (truth(global_Adapt.routineCompatible(r, global_state.profile))) "Coincide con tus filtros" else "Revisa nivel, equipo o restricciones")) + "</small><button class=\"btn primary full\" onclick=\"routineDetail('" + textOf(r.id) + "')\">Ver secuencia y demostraciones 3D</button></article>")
        })).join("")) { "<div class=\"card empty\"><h3>Sin coincidencias para estos filtros.</h3><p>Prueba otra materia o material. Las opciones compatibles respetan tus restricciones.</p></div>" }) + "</div>")
    }
    global_releaseStatic14 = fun(): dynamic {
        if (true) {
            val iterator121: dynamic = iteratorOf(global_staticActors14.values())
            while (true) {
                val next122: dynamic = iterator121.next()
                if (truth(next122.done)) break
                var a: dynamic = absent
                a = next122.value
                a.destroy()
            }
        }
        global_staticActors14.clear()
        return absent
    }
    global_previewModel14 = fun(svg: dynamic, type: dynamic): dynamic {
        if (truth(svg)) {
            svg.dataset.renderer = "2d"
            svg.setAttribute("aria-label", jsAdd(global_CompanionNames[type], " · ilustración 2D"))
        }
        return absent
    }
    global_profileModels14 = fun(): dynamic {
        if (true) {
            val iterator129: dynamic = iteratorOf(global_document.querySelectorAll("#profileForm .companion-option"))
            while (true) {
                val next130: dynamic = iterator129.next()
                if (truth(next130.done)) break
                var option: dynamic = absent
                option = next130.value
                global_previewModel14(option.querySelector("svg"), option.querySelector("input")["value"])
            }
        }
        val selected: dynamic = jsOr(onPresent(global_document.querySelector("#profileForm [name=companionCharacter]:checked")) { value131 -> value131["value"] }) { global_selectedCompanion() }
        global_previewModel14(global_document.querySelector("#profileForm .companion-picker-summary svg"), selected)
        return absent
    }
    global_mountStatic14 = fun(): dynamic {
        global_releaseStatic14()
        global_profileModels14()
        if (true) {
            val iterator136: dynamic = iteratorOf(global_document.querySelectorAll("#app .coach-banner-companion"))
            while (true) {
                val next137: dynamic = iterator136.next()
                if (truth(next137.done)) break
                var host: dynamic = absent
                host = next137.value
                val a: dynamic = jsConstruct(global_IgrisCharacter, arrayOf<dynamic>(host, global_selectedCompanion(), jsObject { obj138 ->
                    obj138["reducedMotion"] = (onPresent(global_state.profile) { value139 -> value139.animate } === false)
                }))
                a["open"]()
                if (truth(global_Dollar("#modal")["open"])) {
                    a.pause()
                }
                global_staticActors14["set"](host, a)
            }
        }
        return absent
    }
    global_V14UI = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["render"] = global_render
        obj1["home"] = global_home
        obj1["openCoach"] = global_openCoach
        obj1["answerCoach"] = global_answerCoach
        obj1["currentCycle"] = global_currentCycle
        obj1["routines"] = global_routines
        obj1["routineDetail"] = global_routineDetail
        obj1["paintGuide11"] = global_paintGuide11
        obj1["validateBackup"] = global_validateBackup
        obj1["allRoutines"] = global_allRoutines
        obj1["previewCompanionChoice"] = global_previewCompanionChoice
    }
    global_ownLevel14 = dynamicValue(null)
    global_ownFamily14 = "all"
    global_ownEquipment14 = "all"
    global_ownLength14 = "all"
    global_interpretGoal = (fun(form: dynamic): dynamic {
        val result: dynamic = global_GoalGroups14.interpret(form.elements.finalGoal["value"], form.elements.goalGroup["value"])
        if (truth(result.group)) {
            form.elements.goalGroup["value"] = result.group
        }
        if (truth(jsAnd((!truth(result.group))) { form.elements.finalGoal["value"].trim() })) {
            form.querySelector("#intentResult").textContent = result.reason
        } else {
            global_goalSelection14(form, true)
        }
        return absent
    })
    global_trainingModeFields11 = (fun(form: dynamic): dynamic {
        val routine: dynamic = (onPresent(form.querySelector("[name=trainingMode]:checked")) { value25 -> value25["value"] } === "routines")
        val preferred: dynamic = form.querySelector(".preferred-trainer")
        if (truth(preferred)) {
            preferred.hidden = (!truth(routine))
        }
        val missions: dynamic = form.querySelector("[name=dailyMissions]")
        if (truth(missions)) {
            missions.closest("label").hidden = routine
            missions.disabled = routine
        }
        form.dataset.trainingMode = (if (truth(routine)) "routines" else "challenges")
        return absent
    })
    global_profileForm = (fun(arg36: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg36) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V14UI.profileForm(initial)
        val p: dynamic = jsOr(global_state.profile) { js("({})") }
        val form: dynamic = box.querySelector("form")
        val select: dynamic = form.querySelector("[name=finalTarget]")
        val group: dynamic = global_GoalGroups14.of(p)
        val hidden: dynamic = global_document.createElement("input")
        hidden.type = "hidden"
        hidden.name = "finalTarget"
        hidden["value"] = jsOr(p.finalTarget) { global_GoalGroups14.groups[group].target }
        val groupSelect: dynamic = global_document.createElement("select")
        groupSelect.name = "goalGroup"
        groupSelect.id = jsOr(select.id) { "goalGroup" }
        groupSelect.setAttribute("onchange", "goalSelection14(this.form)")
        groupSelect.innerHTML = global_options(global_Object.fromEntries(global_Object.entries(global_GoalGroups14.groups).map((fun(arg51: dynamic): dynamic {
            var k: dynamic = absent
            var g: dynamic = absent
            val unpacked52: dynamic = fromIterable(arg51)
            k = unpacked52[0]
            g = unpacked52[1]
            return arrayOf<dynamic>(k, g.name)
        }))), group)
        select.replaceWith(groupSelect, hidden)
        onPresent(form.elements.goal.closest(".field")) { value53 -> value53.setAttribute("hidden", "") }
        form.elements.finalGoal.setAttribute("onchange", "interpretGoal(this.form)")
        form.elements.finalGoal.placeholder = "Ej.: ganar fuerza de piernas y practicar saltos controlados"
        val hint: dynamic = form.querySelector("#intentResult")
        hint.innerHTML = ("<b>" + textOf(global_esc(global_GoalGroups14.groups[group].name)) + "</b><p>" + textOf(global_esc(jsOr(onPresent(global_GoalPlans.plans[hidden["value"]]) { value58 -> value58.why }) { global_GoalGroups14.groups[group].why })) + "</p><small>" + textOf(global_esc(global_GoalGroups14.groups[group].measure)) + "</small>")
        val intensity: dynamic = box.querySelector(".intensity-block")
        intensity.querySelector("p").textContent = "Fuerza sin restricciones: básico, 3 series de 14; intermedio, 4 de 14; avanzado, 4 de 16. Cardio, potencia y movilidad tienen dosis propias. Los saltos usan pocas repeticiones y descansos amplios."
        intensity.querySelector("h3").textContent = "Exigencia según tu nivel"
        global_trainingModeFields11(form)
        return box.innerHTML
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val form: dynamic = ev.target
        val result: dynamic = global_GoalGroups14.interpret(form.elements.finalGoal["value"], form.elements.goalGroup["value"])
        if (truth(jsAnd(result.medical) { (!truth(global_injuryDraft.some((fun(x: dynamic): dynamic {
            return (x.stage !== "past")
        })))) })) {
            ev.preventDefault()
            val details: dynamic = form.querySelector("#healthDetails")
            if (truth(details)) {
                details["open"] = true
                run { val owner68: dynamic = details; onPresent(owner68.scrollIntoView) { method67 -> invokeWith(method67, owner68, arrayOf<dynamic>(jsonValue("{\"behavior\":\"smooth\",\"block\":\"start\"}"))) } }
            }
            return global_toast("Tu meta menciona dolor o una lesión. Registra la zona y las indicaciones en Lesiones y salud antes de generar el plan.")
        }
        global_goalSelection14(form)
        global_V14UI.submitProfile(ev)
        return absent
    })
    global_currentCycle = (fun(): dynamic {
        val c: dynamic = global_V14UI.currentCycle()
        if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
            return c
        }
        val key: dynamic = global_JSON.stringify(arrayOf<dynamic>(14, global_GoalGroups14.of(global_state.profile), global_state.profile.finalTarget, global_state.profile.difficulty, global_state.profile.trainingMode, global_state.profile.trainingScale))
        if (truth((c.migration14 !== key))) {
            global_rebuildPending13(c, true)
            global_RoutinePlanner.organize(c, global_state.profile, global_state.creatorRoutines, global_allRoutines10(), global_state.logs, global_E.dateKey())
            c.migration14 = key
            c.goalPlan14 = global_GoalGroups14.plan(global_state.profile)
            global_save()
        }
        return c
    })
    global_drawVector = (fun(canvas: dynamic, id: dynamic, arg77: dynamic, arg78: dynamic): dynamic {
        var phase: dynamic = absent
        phase = definedOr(arg77) { 0 }
        var mirror: dynamic = absent
        mirror = definedOr(arg78) { false }
        return global_Exercise3D.draw(canvas, id, phase, mirror)
    })
    global_vectorPanel = (fun(e: dynamic): dynamic {
        return ("<section class=\"demo-card model-3d-card\"><span class=\"eyebrow\">VISOR 3D · SIN CONEXIÓN</span><canvas id=\"vectorCanvas\" width=\"540\" height=\"600\" aria-label=\"Modelo 3D articulado: " + textOf(global_esc(e.name)) + "\"></canvas><p id=\"vectorPhase\">Preparación</p><label>Posición del movimiento<input id=\"vectorRange\" type=\"range\" min=\"0\" max=\"47\" value=\"0\" oninput=\"vectorSeek(this.value)\"></label><div class=\"row wrap\"><button class=\"btn primary\" id=\"vectorPlay\" onclick=\"vectorToggle()\">Reproducir</button><button class=\"btn\" onclick=\"HunterActions.mirrorVector()\">Espejo</button></div>" + textOf(global_MotionPlayback.controls()) + "" + textOf(global_cameraControls14("vectorCanvas")) + "<p>" + textOf(global_esc(jsOr(e.cue) { "Revisa preparación y ejecución." })) + "</p><details><summary>Cómo interpretar el modelo</summary><p>Modelo geométrico 3D articulado y cámara giratoria. El movimiento y la profundidad son ilustraciones propias; las variantes comparten movimientos base. No mide tu postura ni valida tu ejecución. Revisa las instrucciones de la ficha.</p><p>Las velocidades afectan a la demostración. Tu tiempo de trabajo y tus cantidades se conservan.</p></details></section>")
    })
    global_paintGuide11 = (fun(): dynamic {
        global_V14UI.paintGuide11()
        val c: dynamic = global_Dollar("#guidedCanvas")
        if (truth(jsAnd(c) { (!truth(global_Dollar("#guidedSlot .model-camera"))) })) {
            c.parentElement.insertAdjacentHTML("afterend", global_cameraControls14("guidedCanvas"))
        }
        return absent
    })
    global_allRoutines = (fun(): dynamic {
        return global_V14UI.allRoutines().map((fun(r: dynamic): dynamic {
            if (truth(jsOr(jsOr(r.locked) { r.custom }) { (!truth(global_state.profile)) })) {
                return r
            }
            val p: dynamic = jsObject { obj97 ->
                copyFields(obj97, global_state.profile)
                obj97["difficulty"] = jsOr(global_OwnRoutines14.keys[r.level]) { global_state.profile.difficulty }
            }
            val items: dynamic = (if (truth((r.planVersion === 14))) global_OwnRoutines14.adjust(r, p) else r.items.map((fun(x: dynamic): dynamic {
                return jsObject { obj98 ->
                    copyFields(obj98, x)
                    copyFields(obj98, global_TrainingIntensity.dose(global_E.byId(x.id), p, (if (truth((r.category === "Cardio"))) "cardio" else (if (truth((r.category === "Movilidad"))) "recovery" else "strength"))))
                }
            })))
            return jsObject { obj99 ->
                copyFields(obj99, r)
                obj99["items"] = items
                obj99["minutes"] = global_OwnRoutines14.estimate(items)
            }
        }))
    })
    global_home = (fun(): dynamic {
        return global_V14UI.home()
    })
    global_openCoach = (fun(): dynamic {
        global_V14UI.openCoach()
        global_Dollar(".coach-intro").insertAdjacentHTML("afterend", global_planCard14())
        val button: dynamic = global_Dollar("#modal .capacity-card button")
        if (truth(button)) {
            button.textContent = (if (truth((global_state.profile.trainingMode === "routines"))) "Ajustar próximas rutinas" else "Ajustar próximas misiones")
        }
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("animaci[oó]n|velocidad|modelo.*3d|visor|demostraci[oó]n|3d", "i")).test(q))) {
            return ("" + textOf(global_companionDisplayName()) + ": Los " + textOf(global_E.exercises.length) + " ejercicios tienen un modelo 3D local con cámara giratoria. Hay tres velocidades: 0,5×, 1× y 1,5×; no alteran el tiempo ni la dosis de la sesión. Son dibujos articulados, no una evaluación de tu técnica.")
        }
        if (truth(jsAnd(dynamicValue(jsRegExp("objetivo|mi meta|interpret|agilidad|saltos|potencia", "i")).test(q)) { (!truth(dynamicValue(jsRegExp("(dolor|lesi[oó]n)", "i")).test(q))) })) {
            val d: dynamic = global_GoalGroups14.plan(global_state.profile)
            return ("" + textOf(global_companionDisplayName()) + ": Tu prioridad es " + textOf(d.name) + ". " + textOf(d.reason) + " " + textOf(d.measure) + " Planifico " + textOf(d.mode.toLowerCase()) + " en " + textOf(d.days) + " días, con nivel " + textOf(d.level) + ". Las restricciones se aplican antes de escoger los movimientos; la revisión semanal usa tus registros. Puedes revisar la familia y tu meta escrita desde Perfil.")
        }
        return global_V14UI.answerCoach(q)
    })
    global_routines = (fun(): dynamic {
        return (if (truth(jsAnd(global_ownLibrary11) { (global_routineMode !== "challenges") })) global_ownLibraryView14() else global_V14UI.routines())
    })
    global_routineDetail = (fun(id: dynamic): dynamic {
        global_V14UI.routineDetail(id)
        val r: dynamic = global_allRoutines().find((fun(x: dynamic): dynamic {
            return (x.id === id)
        }))
        if (truth((onPresent(r) { value118 -> value118.planVersion } === 14))) {
            global_Dollar("#modal").insertAdjacentHTML("afterbegin", ("<p class=\"notice\">Programa propio · " + textOf(global_esc(global_OwnRoutines14.families[r.family].name)) + " · " + textOf(global_OwnRoutines14.names[r.level]) + ". Duración aproximada; el ritmo y los descansos pueden cambiarla.</p>"))
        }
        return absent
    })
    global_staticActors14 = jsConstruct(global_Map, js("[]"))
    global_previewCompanionChoice = (fun(input: dynamic): dynamic {
        global_V14UI.previewCompanionChoice(input)
        global_profileModels14()
        global_Mesh3D.releaseDetached()
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_releaseStatic14()
        global_V14UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(e: dynamic): dynamic {
            return run {
                val target142: dynamic = e
                val assigned144: dynamic = "EL DESPERTAR · V15"
                target142.textContent = assigned144
                assigned144
            }
        }))
        global_mountStatic14()
        global_Mesh3D.releaseDetached()
        return absent
    })
    global_gallery14 = global_modal
    global_modal = (fun(html: dynamic): dynamic {
        if (true) {
            val iterator147: dynamic = iteratorOf(global_staticActors14.values())
            while (true) {
                val next148: dynamic = iterator147.next()
                if (truth(next148.done)) break
                var a: dynamic = absent
                a = next148.value
                a.pause()
            }
        }
        global_gallery14(html)
        global_Mesh3D.releaseDetached()
        return absent
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth((!truth(global_Dollar("#modal")["open"])))) {
            global_Dollar("#modal").querySelectorAll("canvas").forEach((fun(c: dynamic): dynamic {
                return global_Mesh3D.release(c)
            }))
            if (true) {
                val iterator149: dynamic = iteratorOf(global_staticActors14.values())
                while (true) {
                    val next150: dynamic = iterator149.next()
                    if (truth(next150.done)) break
                    var a: dynamic = absent
                    a = next150.value
                    a.resume()
                }
            }
        }
        return absent
    }))
    global_Dollar("#modal").addEventListener("cancel", (fun(): dynamic {
        if (true) {
            val iterator151: dynamic = iteratorOf(global_staticActors14.values())
            while (true) {
                val next152: dynamic = iterator151.next()
                if (truth(next152.done)) break
                var a: dynamic = absent
                a = next152.value
                a.resume()
            }
        }
        return absent
    }))
    global_openAnimationGallery = (fun(): dynamic {
        global_clearGallery13()
        global_modal(("<section data-companion-static=\"true\" class=\"depth-gallery\"><h2>Cinco compañeros · animaciones 2D</h2><p>Entrada, espera, saludo, celebración, pirueta y salida. El compañero activo se cambia solo desde Perfil.</p>" + textOf(global_MotionPlayback.controls("personajes")) + "" + textOf(global_Object.entries(global_CompanionNames).map((fun(arg161: dynamic): dynamic {
            var key: dynamic = absent
            var name: dynamic = absent
            val unpacked162: dynamic = fromIterable(arg161)
            key = unpacked162[0]
            name = unpacked162[1]
            return ("<details class=\"card\" ontoggle=\"HunterActions.toggleCompanion(this,'" + textOf(key) + "')\"><summary>" + textOf(name) + "</summary><div class=\"depth-character-host\" data-sample-character=\"" + textOf(key) + "\"></div><div class=\"row wrap\">" + textOf(dynamicValue(jsonValue("[[\"enter\",\"Entrada\"],[\"wave\",\"Saludo\"],[\"celebrate\",\"Celebrar\"],[\"flip\",\"Pirueta\"],[\"think\",\"Pensar\"],[\"exit\",\"Salida\"]]")).map((fun(arg165: dynamic): dynamic {
                var action: dynamic = absent
                var label: dynamic = absent
                val unpacked166: dynamic = fromIterable(arg165)
                action = unpacked166[0]
                label = unpacked166[1]
                return ("<button class=\"btn\" onclick=\"sampleCompanion13('" + textOf(key) + "','" + textOf(action) + "')\">" + textOf(label) + "</button>")
            })).join("")) + "</div></details>")
        })).join("")) + "</section>"))
        return absent
    })
    global_validateBackup = (fun(`data`: dynamic): dynamic {
        if (truth((!truth(global_V14UI.validateBackup(`data`))))) {
            return false
        }
        return dynamicValue(jsArray { array172 ->
            array172.push(`data`.profile)
            appendAll(array172, `data`.cycles.map((fun(c: dynamic): dynamic {
                return c.profile
            })))
        }).filter(global_Boolean).every((fun(p: dynamic): dynamic {
            return jsOr((p.goalGroup === absent)) { (!truth((!truth(global_GoalGroups14.groups[p.goalGroup])))) }
        }))
    })
    if (truth(jsAnd(global_state.profile) { (!truth(global_state.profile.goalGroup)) })) {
        global_state.profile.goalGroup = global_GoalGroups14.of(global_state.profile)
    }
    global_state.appVersion = 14
    global_save()
    global_currentCycle()
    global_render()
    global_syncNative()
}
