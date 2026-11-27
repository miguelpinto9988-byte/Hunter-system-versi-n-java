@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv3

import hunter.browser.*

@JsName("V2") private external var global_V2: dynamic
@JsName("detailedMode") private external var global_detailedMode: dynamic
@JsName("missionDate") private external var global_missionDate: dynamic
@JsName("draftAvatar") private external var global_draftAvatar: dynamic
@JsName("injuryDraft") private external var global_injuryDraft: dynamic
@JsName("showVariants") private external var global_showVariants: dynamic
@JsName("profileFilter") private external var global_profileFilter: dynamic
@JsName("routineProfileFilter") private external var global_routineProfileFilter: dynamic
@JsName("photoValid") private external var global_photoValid: dynamic
@JsName("hasActivity") private external var global_hasActivity: dynamic
@JsName("migratePending") private external var global_migratePending: dynamic
@JsName("avatarHtml") private external var global_avatarHtml: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("injuryRowsHtml") private external var global_injuryRowsHtml: dynamic
@JsName("drawInjuries") private external var global_drawInjuries: dynamic
@JsName("addInjuryRow") private external var global_addInjuryRow: dynamic
@JsName("pickAvatar") private external var global_pickAvatar: dynamic
@JsName("receiveAvatar") private external var global_receiveAvatar: dynamic
@JsName("readAvatar") private external var global_readAvatar: dynamic
@JsName("dayByDate") private external var global_dayByDate: dynamic
@JsName("ensureTasks") private external var global_ensureTasks: dynamic
@JsName("ensureDayLog") private external var global_ensureDayLog: dynamic
@JsName("itemFinished") private external var global_itemFinished: dynamic
@JsName("missionSummary") private external var global_missionSummary: dynamic
@JsName("recomputeMission") private external var global_recomputeMission: dynamic
@JsName("showMission") private external var global_showMission: dynamic
@JsName("missionWindow") private external var global_missionWindow: dynamic
@JsName("refreshMission") private external var global_refreshMission: dynamic
@JsName("toggleMissionItem") private external var global_toggleMissionItem: dynamic
@JsName("toggleMissionTask") private external var global_toggleMissionTask: dynamic
@JsName("saveMissionNote") private external var global_saveMissionNote: dynamic
@JsName("editMission") private external var global_editMission: dynamic
@JsName("saveMissionEdit") private external var global_saveMissionEdit: dynamic
@JsName("oldTechnique") private external var global_oldTechnique: dynamic
@JsName("historyV2") private external var global_historyV2: dynamic
@JsName("validateV2") private external var global_validateV2: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("profile") private external var global_profile: dynamic
@JsName("workout") private external var global_workout: dynamic
@JsName("book") private external var global_book: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("routineList") private external var global_routineList: dynamic
@JsName("exerciseList") private external var global_exerciseList: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("prepareRoutine") private external var global_prepareRoutine: dynamic
@JsName("applyRoutine") private external var global_applyRoutine: dynamic
@JsName("finish") private external var global_finish: dynamic
@JsName("confirmQuickFinish") private external var global_confirmQuickFinish: dynamic
@JsName("confirmImport") private external var global_confirmImport: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("editExercise") private external var global_editExercise: dynamic
@JsName("applyExercise") private external var global_applyExercise: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("header") private external var global_header: dynamic
@JsName("icon2") private external var global_icon2: dynamic
@JsName("nav") private external var global_nav: dynamic
@JsName("HunterKotlinScreens") private external var global_HunterKotlinScreens: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("FormData") private external var global_FormData: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("go") private external var global_go: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Android") private external var global_Android: dynamic
@JsName("FileReader") private external var global_FileReader: dynamic
@JsName("Image") private external var global_Image: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("todayDay") private external var global_todayDay: dynamic
@JsName("calendar") private external var global_calendar: dynamic
@JsName("dayDetail") private external var global_dayDetail: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("routineNotice372") private external var global_routineNotice372: dynamic
@JsName("pendingRoutine") private external var global_pendingRoutine: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("getLog") private external var global_getLog: dynamic
@JsName("history") private external var global_history: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("kindLabel") private external var global_kindLabel: dynamic
@JsName("HunterNative3875") private external var global_HunterNative3875: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic

internal fun installV3() {
    global_hasActivity = fun(l: dynamic): dynamic {
        return jsAnd((!truth((!truth(l))))) { jsOr(jsOr(l.complete) { l.pain }) { l.items.some((fun(x: dynamic): dynamic {
            return jsOr(jsOr(x.missionState) { x.selfReported }) { x["actual"].some((fun(a: dynamic): dynamic {
                return a.done
            })) }
        })) } }
    }
    global_migratePending = fun(): dynamic {
        val c: dynamic = global_state.cycles.at((-numeric(1)))
        if (truth(jsOr(jsOr((!truth(global_state.profile))) { (!truth(c)) }) { (c.adaptVersion === 3) })) {
            return absent
        }
        val updated: dynamic = global_E.makeCycle(global_state.profile, c.start, c.index, null, global_state.logs)
        if (true) {
            var i: dynamic = absent
            i = 0
            var firstIteration16 = true
            while (true) {
                if (!firstIteration16) {
                    run {
                        val previous17: dynamic = numeric(i)
                        i = jsAdd(previous17, 1)
                    }
                }
                firstIteration16 = false
                if (!truth(jsLess(i, c.days.length))) break
                val d: dynamic = c.days[i]
                if (truth(jsAnd(jsAnd(jsAnd(jsAtLeast(d.date, global_E.dateKey())) { (!truth(d.customized)) }) { (!truth(d.routineId)) }) { (!truth(global_hasActivity(global_state.logs[d.date]))) })) {
                    c.days[i] = updated.days[i]
                    removeKey(global_state.logs, d.date)
                }
            }
        }
        c.adaptVersion = 3
        c.profile = global_JSON.parse(global_JSON.stringify(global_state.profile))
        c.decision = updated.decision
        return absent
    }
    global_avatarHtml = fun(arg28: dynamic): dynamic {
        var cls: dynamic = absent
        cls = definedOr(arg28) { "avatar" }
        return (if (truth(global_state.avatar)) ("<img class=\"" + textOf(cls) + "\" src=\"" + textOf(global_state.avatar) + "\" alt=\"Foto de perfil\">") else ("<span class=\"" + textOf(cls) + " avatar-empty\">" + textOf(global_esc(dynamicValue(jsOr(onPresent(global_state.profile) { value32 -> value32.name }) { "H" }).slice(0, 1).toUpperCase())) + "</span>"))
    }
    global_profileForm = fun(arg39: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg39) { false }
        val p: dynamic = (if (truth(global_state.profile)) global_Adapt.normalize(global_state.profile) else jsonValue("{\"goal\":\"general\",\"minutes\":20,\"experience\":\"beginner\",\"equipment\":\"body\",\"frequency\":2,\"restrictions\":{},\"health\":{\"pain\":\"none\"},\"injuries\":[]}"))
        global_draftAvatar = jsOr(global_state.avatar) { "" }
        global_injuryDraft = global_JSON.parse(global_JSON.stringify(jsOr(p.injuries) { js("[]") }))
        if (truth(jsAnd(jsAnd(p.limits) { (!truth(p.health)) }) { (global_injuryDraft.length === 0) })) {
            global_injuryDraft = jsonValue("[{\"zone\":\"other\",\"type\":\"unknown\",\"stage\":\"recovering\"}]")
        }
        val h: dynamic = jsOr(p.health) { js("({})") }
        return ("<section class=\"intro profile-v3\"><span class=\"eyebrow\">" + textOf((if (truth(initial)) "CONFIGURACIÓN INICIAL" else "CONFIGURACIÓN DEL CAZADOR")) + "</span><h1>" + textOf((if (truth(initial)) "Tu sistema.<br>Tu propio camino." else "Tu perfil, a tu medida.")) + "</h1><p class=\"muted\">Configura objetivos, equipo y restricciones. Puedes volver a editar estos datos.</p><form id=\"profileForm\" onsubmit=\"submitProfile(event)\"><div class=\"card profile-block\"><div class=\"profile-photo-row\"><div id=\"avatarPreview\">" + textOf((if (truth(global_draftAvatar)) ("<img class=\"avatar-large\" src=\"" + textOf(global_draftAvatar) + "\" alt=\"Foto seleccionada\">") else "<span class=\"avatar-large avatar-empty\">⟡</span>")) + "</div><div><h3>Foto de perfil</h3><button class=\"btn ghost\" type=\"button\" onclick=\"pickAvatar()\">Elegir desde archivos</button><button class=\"text-button\" type=\"button\" onclick=\"receiveAvatar('')\">Quitar foto</button><small>Vista previa · se aplica al guardar</small></div></div><input hidden id=\"avatarFile\" type=\"file\" accept=\"image/jpeg,image/png,image/webp\" onchange=\"readAvatar(this)\"><div class=\"field\"><label class=\"label\" for=\"name\">Nombre de cazador</label><input id=\"name\" name=\"name\" value=\"" + textOf(global_esc(jsOr(p.name) { "" })) + "\" required maxlength=\"35\" autocomplete=\"nickname\"></div><div class=\"fields three\"><div class=\"field\"><label class=\"label\" for=\"age\">Edad</label><input id=\"age\" name=\"age\" type=\"number\" required min=\"13\" max=\"100\" step=\"1\" value=\"" + textOf(jsOr(p.age) { "" }) + "\"></div><div class=\"field\"><label class=\"label\" for=\"height\">Estatura · cm</label><input id=\"height\" name=\"height\" type=\"number\" required min=\"100\" max=\"250\" step=\"0.1\" value=\"" + textOf(jsOr(p.height) { "" }) + "\"></div><div class=\"field\"><label class=\"label\" for=\"weight\">Peso · kg</label><input id=\"weight\" name=\"weight\" type=\"number\" required min=\"30\" max=\"350\" step=\"0.1\" value=\"" + textOf(jsOr(p.weight) { "" }) + "\"></div></div></div>\n<div class=\"card profile-block\"><span class=\"step-number\">01</span><h3>Objetivo y disponibilidad</h3><div class=\"field\"><label class=\"label\" for=\"trainingGoal\">Objetivo principal</label><select id=\"trainingGoal\" name=\"goal\">" + textOf(global_Object.entries(global_Adapt.goals).map((fun(arg42: dynamic): dynamic {
            var v: dynamic = absent
            var n: dynamic = absent
            val unpacked43: dynamic = fromIterable(arg42)
            v = unpacked43[0]
            n = unpacked43[1]
            return ("<option value=\"" + textOf(v) + "\" " + textOf((if (truth((p.goal === v))) "selected" else "")) + ">" + textOf(n) + "</option>")
        })).join("")) + "</select></div><div class=\"fields\"><div class=\"field\"><label class=\"label\" for=\"experience\">Experiencia</label><select id=\"experience\" name=\"experience\"><option value=\"beginner\" " + textOf((if (truth((p.experience === "beginner"))) "selected" else "")) + ">Estoy empezando</option><option value=\"regular\" " + textOf((if (truth((p.experience === "regular"))) "selected" else "")) + ">Entreno regularmente</option></select></div><div class=\"field\"><label class=\"label\" for=\"minutes\">Tiempo disponible</label><select id=\"minutes\" name=\"minutes\">" + textOf(dynamicValue(jsonValue("[10,20,30,45]")).map((fun(n: dynamic): dynamic {
            return ("<option value=\"" + textOf(n) + "\" " + textOf((if (truth((p.minutes === n))) "selected" else "")) + ">" + textOf(n) + " minutos aprox.</option>")
        })).join("")) + "</select></div></div><div class=\"fields\"><div class=\"field\"><label class=\"label\" for=\"frequency\">Sesiones principales por semana</label><select id=\"frequency\" name=\"frequency\"><option value=\"2\" " + textOf((if (truth((p.frequency === 2))) "selected" else "")) + ">2 sesiones</option><option value=\"3\" " + textOf((if (truth((p.frequency === 3))) "selected" else "")) + ">3 sesiones</option></select></div><div class=\"field\"><label class=\"label\" for=\"equipment\">Equipo</label><select id=\"equipment\" name=\"equipment\"><option value=\"body\" " + textOf((if (truth((p.equipment === "body"))) "selected" else "")) + ">Peso corporal</option><option value=\"db\" " + textOf((if (truth((p.equipment === "db"))) "selected" else "")) + ">Mancuernas</option></select></div></div><div class=\"field\"><label class=\"label\" for=\"load\">Peso disponible de una mancuerna · kg</label><input id=\"load\" name=\"load\" type=\"number\" min=\"0\" max=\"100\" step=\"0.25\" value=\"" + textOf(jsOr(p.load) { 0 }) + "\"></div><div class=\"equipment-options\"><label><input type=\"checkbox\" name=\"bench\" " + textOf((if (truth(p.bench)) "checked" else "")) + "> Banco de entrenamiento ajustable</label><label><input type=\"checkbox\" name=\"step\" " + textOf((if (truth(p.step)) "checked" else "")) + "> Plataforma baja de ejercicio</label></div></div>\n<div class=\"card profile-block\"><span class=\"step-number\">02</span><h3>Lesiones y molestias</h3><p class=\"muted\">Añade cada zona afectada. Si no tienes lesiones, deja la lista vacía.</p><div id=\"injuryRows\">" + textOf(global_injuryRowsHtml()) + "</div><button class=\"btn ghost full\" type=\"button\" onclick=\"addInjuryRow()\">+ Añadir zona o lesión</button><div class=\"field\"><label class=\"label\" for=\"painLevel\">¿Tienes dolor actualmente?</label><select id=\"painLevel\" name=\"pain\"><option value=\"none\" " + textOf((if (truth((h.pain === "none"))) "selected" else "")) + ">No</option><option value=\"mild\" " + textOf((if (truth((h.pain === "mild"))) "selected" else "")) + ">Molestia leve</option><option value=\"moderate\" " + textOf((if (truth((h.pain === "moderate"))) "selected" else "")) + ">Dolor moderado</option><option value=\"strong\" " + textOf((if (truth((h.pain === "strong"))) "selected" else "")) + ">Dolor fuerte</option></select></div><div class=\"equipment-options\"><label><input type=\"checkbox\" name=\"acute\" " + textOf((if (truth(h.acute)) "checked" else "")) + "> Lesión reciente, inflamación importante o movimiento muy limitado</label><label><input type=\"checkbox\" name=\"special\" " + textOf((if (truth(h.special)) "checked" else "")) + "> Otra condición que requiere indicaciones individuales (por ejemplo, embarazo o enfermedad)</label></div></div>\n<div class=\"card profile-block\"><span class=\"step-number\">03</span><h3>Restricciones e indicaciones recibidas</h3><p class=\"muted\">Marca lo que debes evitar o necesitas adaptar.</p><div class=\"restriction-grid\">" + textOf(global_Object.entries(global_Adapt.restrictions).map((fun(arg46: dynamic): dynamic {
            var id: dynamic = absent
            var n: dynamic = absent
            val unpacked47: dynamic = fromIterable(arg46)
            id = unpacked47[0]
            n = unpacked47[1]
            return ("<label><input type=\"checkbox\" name=\"r_" + textOf(id) + "\" " + textOf((if (truth(p.restrictions[id])) "checked" else "")) + "><span>" + textOf(n) + "</span></label>")
        })).join("")) + "</div><div class=\"field\"><label class=\"label\" for=\"recommendations\">Recomendaciones de tu profesional · opcional</label><textarea id=\"recommendations\" name=\"recommendations\" maxlength=\"1000\" rows=\"3\" placeholder=\"Puedes anotar las indicaciones que recibiste.\">" + textOf(global_esc(jsOr(p.recommendations) { "" })) + "</textarea></div><p class=\"footnote\">El texto se guarda como referencia: la app no interpreta indicaciones clínicas escritas. Usa las casillas para trasladar las restricciones.</p><div class=\"equipment-options\"><label><input type=\"checkbox\" name=\"approved\" " + textOf((if (truth(h.approved)) "checked" else "")) + "> Con mis lesiones o condición, un profesional me ha permitido actividad física dentro de estas restricciones</label><label><input type=\"checkbox\" name=\"translated\" " + textOf((if (truth(h.translated)) "checked" else "")) + "> He trasladado las indicaciones recibidas a las casillas anteriores</label></div><div class=\"notice\">La selección excluye movimientos relacionados con zonas y restricciones declaradas; eso no confirma que los restantes sean adecuados para tu lesión. No genera tratamientos de rehabilitación.</div></div>\n<div class=\"card profile-block\"><p class=\"footnote\">Peso y estatura se guardan como datos de seguimiento; no determinan por sí solos una carga adecuada. Al guardar, se actualizan los días pendientes. Los días con actividad y las misiones personalizadas se conservan; revisa los avisos si cambias tus restricciones.</p><button class=\"btn primary full\" type=\"submit\">" + textOf((if (truth(initial)) "ACTIVAR MI SISTEMA" else "Guardar perfil y adaptar pendientes")) + "</button></div></form></section>")
    }
    global_injuryRowsHtml = fun(): dynamic {
        return jsOr(global_injuryDraft.map((fun(i: dynamic, n: dynamic): dynamic {
            return ("<div class=\"injury-row\"><div class=\"row between\"><b>Zona " + textOf(jsAdd(n, 1)) + "</b><button type=\"button\" class=\"text-button\" onclick=\"HunterActions.removeInjury(" + textOf(n) + ")\" aria-label=\"Quitar zona " + textOf(jsAdd(n, 1)) + "\">Quitar</button></div><div class=\"field\"><label class=\"label\" for=\"zone-" + textOf(n) + "\">Zona afectada</label><select id=\"zone-" + textOf(n) + "\" onchange=\"HunterActions.setInjuryZone(this," + textOf(n) + ")\">" + textOf(global_Object.entries(global_Adapt.zones).map((fun(arg60: dynamic): dynamic {
                var v: dynamic = absent
                var t: dynamic = absent
                val unpacked61: dynamic = fromIterable(arg60)
                v = unpacked61[0]
                t = unpacked61[1]
                return ("<option value=\"" + textOf(v) + "\" " + textOf((if (truth((i.zone === v))) "selected" else "")) + ">" + textOf(t) + "</option>")
            })).join("")) + "</select></div><div class=\"fields\"><label><span class=\"label\">Tipo, si lo conoces</span><select aria-label=\"Tipo de lesión " + textOf(jsAdd(n, 1)) + "\" onchange=\"HunterActions.setInjuryType(this," + textOf(n) + ")\">" + textOf(global_Object.entries(global_Adapt.types).map((fun(arg64: dynamic): dynamic {
                var v: dynamic = absent
                var t: dynamic = absent
                val unpacked65: dynamic = fromIterable(arg64)
                v = unpacked65[0]
                t = unpacked65[1]
                return ("<option value=\"" + textOf(v) + "\" " + textOf((if (truth((i.type === v))) "selected" else "")) + ">" + textOf(t) + "</option>")
            })).join("")) + "</select></label><label><span class=\"label\">Situación</span><select aria-label=\"Situación de lesión " + textOf(jsAdd(n, 1)) + "\" onchange=\"HunterActions.setInjuryStage(this," + textOf(n) + ")\"><option value=\"active\" " + textOf((if (truth((i.stage === "active"))) "selected" else "")) + ">Actual</option><option value=\"recovering\" " + textOf((if (truth((i.stage === "recovering"))) "selected" else "")) + ">En recuperación</option><option value=\"past\" " + textOf((if (truth((i.stage === "past"))) "selected" else "")) + ">Anterior, resuelta</option></select></label></div></div>")
        })).join("")) { "<p class=\"empty small-empty\">Sin zonas registradas.</p>" }
    }
    global_drawInjuries = fun(): dynamic {
        global_Dollar("#injuryRows").innerHTML = global_injuryRowsHtml()
        return absent
    }
    global_addInjuryRow = fun(): dynamic {
        if (truth(jsAtLeast(global_injuryDraft.length, 8))) {
            return global_toast("Puedes registrar hasta 8 zonas.")
        }
        global_injuryDraft.push(jsonValue("{\"zone\":\"other\",\"type\":\"unknown\",\"stage\":\"active\"}"))
        global_drawInjuries()
        return absent
    }
    global_pickAvatar = fun(): dynamic {
        if (truth(onPresent(global_window.Android) { value99 -> value99.pickAvatar })) {
            global_Android.pickAvatar()
        } else {
            global_Dollar("#avatarFile").click()
        }
        return absent
    }
    global_receiveAvatar = fun(`data`: dynamic): dynamic {
        if (truth(jsAnd(`data`) { (!truth(global_photoValid(`data`))) })) {
            return global_toast("No se pudo usar esa foto. Elige otra imagen.")
        }
        global_draftAvatar = `data`
        val el: dynamic = global_Dollar("#avatarPreview")
        if (truth(el)) {
            el.innerHTML = (if (truth(`data`)) ("<img class=\"avatar-large\" src=\"" + textOf(`data`) + "\" alt=\"Foto seleccionada\">") else "<span class=\"avatar-large avatar-empty\">⟡</span>")
        }
        global_toast("Foto preparada. Guarda el perfil para aplicarla.")
        return absent
    }
    global_readAvatar = fun(input: dynamic): dynamic {
        val f: dynamic = input.files[0]
        if (truth((!truth(f)))) {
            return absent
        }
        if (truth(jsOr((!truth(dynamicValue(jsonValue("[\"image/jpeg\",\"image/png\",\"image/webp\"]")).includes(f.type)))) { jsMore(f.size, jsMultiply(jsMultiply(8, 1024), 1024)) })) {
            return global_toast("Elige JPG, PNG o WebP de hasta 8 MB.")
        }
        val reader: dynamic = jsConstruct(global_FileReader, js("[]"))
        reader.onload = (fun(): dynamic {
            val img: dynamic = jsConstruct(global_Image, js("[]"))
            img.onload = (fun(): dynamic {
                val size: dynamic = global_Math.min(img.naturalWidth, img.naturalHeight)
                val canvas: dynamic = global_document.createElement("canvas")
                canvas.width = 384
                canvas.height = 384
                canvas.getContext("2d").drawImage(img, jsDivide(jsSubtract(img.naturalWidth, size), 2), jsDivide(jsSubtract(img.naturalHeight, size), 2), size, size, 0, 0, 384, 384)
                global_receiveAvatar(canvas.toDataURL("image/jpeg", 0.8))
                return absent
            })
            img.onerror = (fun(): dynamic {
                return global_toast("No se pudo leer la imagen.")
            })
            img.src = reader.result
            return absent
        })
        reader.onerror = (fun(): dynamic {
            return global_toast("No se pudo abrir la imagen.")
        })
        reader.readAsDataURL(f)
        return absent
    }
    global_dayByDate = fun(date: dynamic): dynamic {
        return global_HunterKotlinScreens.dayByDate(date)
    }
    global_ensureTasks = fun(d: dynamic): dynamic {
        return global_HunterKotlinScreens.ensureTasks(d)
    }
    global_ensureDayLog = fun(d: dynamic): dynamic {
        return global_HunterKotlinScreens.ensureDayLog(d)
    }
    global_itemFinished = fun(x: dynamic, l: dynamic): dynamic {
        return global_HunterKotlinScreens.itemFinished(x, l)
    }
    global_missionSummary = fun(d: dynamic): dynamic {
        return global_HunterKotlinScreens.missionSummary(d)
    }
    global_recomputeMission = fun(d: dynamic): dynamic {
        return global_HunterKotlinScreens.recomputeMission(d)
    }
    global_showMission = fun(date: dynamic): dynamic {
        return global_HunterKotlinScreens.showMission(date, (fun(`value`: dynamic): dynamic {
            global_missionDate = `value`
            return absent
        }))
    }
    global_missionWindow = fun(d: dynamic, arg136: dynamic): dynamic {
        var inModal: dynamic = absent
        inModal = definedOr(arg136) { false }
        return global_HunterKotlinScreens.missionWindow(d, inModal)
    }
    global_refreshMission = fun(): dynamic {
        return global_HunterKotlinScreens.refreshMission()
    }
    global_toggleMissionItem = fun(date: dynamic, i: dynamic, checked: dynamic): dynamic {
        return global_HunterKotlinScreens.toggleMissionItem(date, i, checked)
    }
    global_toggleMissionTask = fun(date: dynamic, i: dynamic, checked: dynamic): dynamic {
        return global_HunterKotlinScreens.toggleMissionTask(date, i, checked)
    }
    global_saveMissionNote = fun(date: dynamic, note: dynamic): dynamic {
        return global_HunterKotlinScreens.saveMissionNote(date, note)
    }
    global_editMission = fun(date: dynamic): dynamic {
        global_HunterKotlinScreens.editMission(date, (fun(`value`: dynamic): dynamic {
            global_missionDate = `value`
            return absent
        }))
        return absent
    }
    global_saveMissionEdit = fun(ev: dynamic): dynamic {
        return global_HunterKotlinScreens.saveMissionEdit(ev, global_missionDate)
    }
    global_V2 = jsObject { obj1 ->
        obj1["home"] = global_home
        obj1["profile"] = global_profile
        obj1["workout"] = global_workout
        obj1["book"] = global_book
        obj1["routines"] = global_routines
        obj1["routineList"] = global_routineList
        obj1["exerciseList"] = global_exerciseList
        obj1["technique"] = global_technique
        obj1["prepareRoutine"] = global_prepareRoutine
        obj1["applyRoutine"] = global_applyRoutine
        obj1["finish"] = global_finish
        obj1["confirmQuickFinish"] = global_confirmQuickFinish
        obj1["confirmImport"] = global_confirmImport
        obj1["validateBackup"] = global_validateBackup
        obj1["editExercise"] = global_editExercise
        obj1["applyExercise"] = global_applyExercise
        obj1["save"] = global_save
    }
    global_detailedMode = false
    global_missionDate = global_E.dateKey()
    global_draftAvatar = jsOr(global_state.avatar) { "" }
    global_injuryDraft = js("[]")
    global_showVariants = false
    global_profileFilter = false
    global_routineProfileFilter = false
    global_photoValid = (fun(s: dynamic): dynamic {
        return jsAnd(jsAnd((jsTypeOf(s) === "string")) { jsAtMost(s.length, 250000) }) { dynamicValue(jsRegExp("^data:image\\/(jpeg|png|webp);base64,[A-Za-z0-9+/=]+\$", "")).test(s) }
    })
    if (truth((!truth(global_photoValid(jsOr(global_state.avatar) { "" }))))) {
        global_state.avatar = ""
    }
    global_state.appVersion = 3
    if (truth(global_state.profile)) {
        global_state.profile = global_Adapt.normalize(global_state.profile)
    }
    if (true) {
        val iterator8: dynamic = iteratorOf(global_Object.values(global_state.logs))
        while (true) {
            val next9: dynamic = iterator8.next()
            if (truth(next9.done)) break
            var l: dynamic = absent
            l = next9.value
            if (truth(l.complete)) {
                l.items.forEach((fun(x: dynamic): dynamic {
                    if (truth((x.missionState === absent))) {
                        x.missionState = true
                    }
                    return absent
                }))
            }
        }
    }
    global_migratePending()
    global_header = (fun(): dynamic {
        return ("<header class=\"topbar\"><button class=\"brand brand-button\" onclick=\"go('home')\"><div class=\"logo\">⟡</div><div>HUNTER<span>SYSTEM</span><small>EL DESPERTAR · V4</small></div></button><div class=\"row\"><button class=\"icon-button\" onclick=\"go('reminders')\" aria-label=\"Recordatorios\">" + textOf(global_icon2("bell")) + "</button><button class=\"avatar-button\" onclick=\"HunterActions.openOrScrollProfile()\" aria-label=\"Editar perfil\">" + textOf(global_avatarHtml("avatar-small")) + "</button></div></header>")
    })
    global_nav = (fun(): dynamic {
        return global_HunterKotlinScreens.navigation()
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        ev.preventDefault()
        if (truth((!truth(ev.target.reportValidity())))) {
            return absent
        }
        val f: dynamic = jsConstruct(global_FormData, arrayOf<dynamic>(ev.target))
        val name: dynamic = f["get"]("name").trim()
        if (truth((!truth(name)))) {
            return global_toast("Escribe tu nombre.")
        }
        if (truth(jsAnd((f["get"]("equipment") === "db")) { jsAtMost(numeric(f["get"]("load")), 0) })) {
            return global_toast("Indica el peso de una mancuerna disponible.")
        }
        if (truth(jsAnd((f["get"]("pain") !== "none")) { (!truth(global_injuryDraft.some((fun(x: dynamic): dynamic {
            return (x.stage !== "past")
        })))) })) {
            return global_toast("Añade la zona donde tienes dolor o elige «Otra zona».")
        }
        val p: dynamic = jsObject { obj74 ->
            obj74["name"] = name
            obj74["goalGroup"] = jsOr(f["get"]("goalGroup")) { absent }
            obj74["trainingScale"] = global_Math.max(0.4, global_Math.min(1, jsOr(global_Number(f["get"]("trainingScale"))) { 1 }))
            obj74["trainingMode"] = jsOr(f["get"]("trainingMode")) { "challenges" }
            obj74["preferredTrainer"] = jsOr(f["get"]("preferredTrainer")) { "all" }
            obj74["dailyMissions"] = global_Math.max(1, global_Math.min(7, jsOr(jsOr(global_Number(f["get"]("dailyMissions"))) { global_Number(onPresent(global_state.profile) { value75 -> value75.dailyMissions }) }) { 3 }))
            obj74["routineExercises3878"] = global_Math.max(1, global_Math.min(7, jsOr(jsOr(global_Number(f["get"]("routineExercises3878"))) { global_Number(onPresent(global_state.profile) { value76 -> value76.routineExercises3878 }) }) { 4 }))
            obj74["weekPlan"] = global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, n: dynamic): dynamic {
                return jsOr(f["get"](jsAdd("day", n))) { "rest" }
            }))
            obj74["focus"] = jsOr(f["get"]("focus")) { "full" }
            obj74["restSeconds"] = jsOr(numeric(f["get"]("restSeconds"))) { 90 }
            obj74["gender"] = jsOr(f["get"]("gender")) { "unspecified" }
            obj74["companionStyle"] = jsOr(f["get"]("companionStyle")) { "knight" }
            obj74["companionCharacter"] = jsOr(f["get"]("companionCharacter")) { "knight" }
            obj74["theme"] = jsOr(f["get"]("theme")) { "auto" }
            obj74["difficulty"] = jsOr(f["get"]("difficulty")) { "basic" }
            obj74["finalTarget"] = jsOr(f["get"]("finalTarget")) { f["get"]("goal") }
            obj74["finalGoal"] = dynamicValue(jsOr(f["get"]("finalGoal")) { "" }).slice(0, 300)
            obj74["animate"] = f.has("animate")
            obj74["age"] = numeric(f["get"]("age"))
            obj74["height"] = numeric(f["get"]("height"))
            obj74["weight"] = numeric(f["get"]("weight"))
            obj74["experience"] = f["get"]("experience")
            obj74["equipment"] = f["get"]("equipment")
            obj74["load"] = numeric(f["get"]("load"))
            obj74["frequency"] = numeric(f["get"]("frequency"))
            obj74["bench"] = f.has("bench")
            obj74["step"] = f.has("step")
            obj74["goal"] = f["get"]("goal")
            obj74["minutes"] = (if (truth((f["get"]("trainingMode") === "routines"))) (if (truth(dynamicValue(jsonValue("[10,20,30,45]")).includes(global_Number(f["get"]("routineMinutes3878"))))) global_Number(f["get"]("routineMinutes3878")) else (if (truth(dynamicValue(jsonValue("[10,20,30,45]")).includes(global_Number(onPresent(global_state.profile) { value77 -> value77.minutes })))) global_Number(global_state.profile.minutes) else 30)) else 30)
            obj74["timeZone"] = jsOr(jsOr(jsOr(f["get"]("timeZone")) { onPresent(global_state.profile) { value78 -> value78.timeZone } }) { onPresent(global_state.portable3868) { value79 -> value79.timeZone } }) { "system" }
            obj74["injuries"] = global_JSON.parse(global_JSON.stringify(global_injuryDraft))
            obj74["restrictions"] = global_Object.fromEntries(global_Object.keys(global_Adapt.restrictions).map((fun(k: dynamic): dynamic {
                return arrayOf<dynamic>(k, f.has(jsAdd("r_", k)))
            })))
            obj74["recommendations"] = f["get"]("recommendations").slice(0, 1000)
            obj74["health"] = jsObject { obj80 ->
                obj80["pain"] = f["get"]("pain")
                obj80["acute"] = f.has("acute")
                obj80["special"] = f.has("special")
                obj80["approved"] = f.has("approved")
                obj80["translated"] = f.has("translated")
            }
        }
        p.limits = jsOr(jsOr(jsOr((!truth((!truth(p.injuries.some((fun(i: dynamic): dynamic {
            return (i.stage !== "past")
        }))))))) { p.health.special }) { (p.health.pain !== "none") }) { p.health.acute }
        global_state.profile = p
        global_state.avatar = jsOr(global_draftAvatar) { "" }
        val c: dynamic = global_state.cycles.find((fun(c: dynamic): dynamic {
            return (c.start === global_Weekly.startOfWeek(global_E.dateKey()))
        }))
        if (truth(jsAnd(jsAnd(c) { jsAtLeast(global_E.diff(global_E.dateKey(), c.start), 0) }) { jsLess(global_E.diff(global_E.dateKey(), c.start), 7) })) {
            val updated: dynamic = global_E.makeCycle(p, c.start, c.index, null, global_state.logs)
            c.days = c.days.map((fun(d: dynamic, i: dynamic): dynamic {
                if (truth(jsAnd(jsAnd(jsAnd(jsAtLeast(d.date, global_E.dateKey())) { (!truth(d.customized)) }) { (!truth(d.routineId)) }) { (!truth(global_hasActivity(global_state.logs[d.date]))) })) {
                    removeKey(global_state.logs, d.date)
                    return updated.days[i]
                }
                return d
            }))
            c.profile = global_JSON.parse(global_JSON.stringify(p))
            c.decision = updated.decision
            c.adaptVersion = 3
        }
        if (truth(global_save())) {
            global_go("home")
            global_toast("Perfil guardado. Se actualizaron los días sin actividad registrada.")
        }
        return absent
    })
    global_profile = (fun(): dynamic {
        return jsAdd(global_profileForm(false), ("<section class=\"intro stack\"><div class=\"card\"><h3>Tus datos y recordatorios</h3><button class=\"btn full\" onclick=\"go('reminders')\">Configurar notificaciones</button><button class=\"btn ghost full\" onclick=\"exportBackup()\">Exportar copia de seguridad</button><button class=\"btn ghost full\" onclick=\"importBackup()\">Restaurar copia</button><input type=\"file\" id=\"importFile\" accept=\"application/json,.json\" hidden onchange=\"readFile(this)\"></div><div class=\"card\"><h3>Alcance de la adaptación</h3><p class=\"footnote\">Las restricciones son filtros generales, no un diagnóstico. Con dolor moderado o fuerte, lesión reciente, cirugía, fractura o zonas sin identificar, se mantiene el organizador de misiones y se evita asignar ejercicio automáticamente. Puedes anotar y seguir las indicaciones individuales que recibas.</p><p class=\"footnote\">El catálogo tiene " + textOf(global_Catalog.baseExercises) + " movimientos base y " + textOf(jsSubtract(global_E.exercises.length, global_Catalog.baseExercises)) + " variantes explicadas; " + textOf(global_Catalog.baseRoutines) + " rutinas base y sus variantes de volumen. No todos los movimientos son apropiados para todos los perfiles.</p><p class=\"footnote\"><a href=\"https://www.niddk.nih.gov/health-information/weight-management/staying-active-at-any-size\" target=\"_blank\" rel=\"noopener\">NIDDK · Actividad física y limitaciones</a><br><a href=\"https://www.nhs.uk/live-well/exercise/\" target=\"_blank\" rel=\"noopener\">NHS · Ejercicio y movilidad</a></p></div></section>"))
    })
    global_plan = (fun(): dynamic {
        val c: dynamic = global_currentCycle()
        if (truth((!truth(c)))) {
            return ""
        }
        val d: dynamic = global_todayDay()
        return ("<div class=\"page-heading\"><span class=\"eyebrow\">VENTANA DEL SISTEMA</span><h1>Tus misiones diarias</h1><p class=\"muted\">Marca lo que terminaste. Modifica los objetivos a tu gusto.</p></div>" + textOf(global_missionWindow(d)) + "<div class=\"card calendar-panel\"><div class=\"row between\"><h3>Ciclo de 21 días</h3><span class=\"badge\">" + textOf(jsAdd(c.index, 1)) + "</span></div>" + textOf(global_calendar(c)) + "<p class=\"footnote\">Toca una fecha para abrir su ventana. Las fechas futuras se pueden editar; la finalización se registra solo hoy.</p></div>")
    })
    global_dayDetail = (fun(date: dynamic): dynamic {
        global_showMission(date)
        return absent
    })
    global_workout = (fun(): dynamic {
        if (truth((!truth(global_detailedMode)))) {
            return global_missionWindow(global_todayDay())
        }
        val d: dynamic = global_todayDay()
        val m: dynamic = global_Adapt.mode(global_state.profile)
        if (truth(jsOr((!truth(m.auto))) { d.items.some((fun(x: dynamic): dynamic {
            return (!truth(global_Adapt.allowed(global_E.byId(x.id), global_state.profile)))
        })) })) {
            return ("<div class=\"notice\">Esta sesión contiene movimientos que deben revisarse con tu perfil actual. Sus registros se conservan.</div>" + textOf(global_missionWindow(d)) + "")
        }
        return jsAdd("<button class=\"btn ghost\" onclick=\"HunterActions.closeDetailedMode()\">← Volver a las casillas de misión</button>", global_V2.workout())
    })
    global_home = (fun(): dynamic {
        val html: dynamic = global_V2.home()
        val container: dynamic = global_document.createElement("div")
        container.innerHTML = html
        val d: dynamic = global_todayDay()
        if (truth((!truth(d)))) {
            return html
        }
        val feature: dynamic = container.querySelector(".mission-feature")
        if (truth(feature)) {
            feature.outerHTML = ("<section class=\"card mission-launch\"><div class=\"row between\"><span class=\"eyebrow\">MISIÓN DIARIA DISPONIBLE</span><span class=\"badge\">" + textOf(global_missionSummary(d).done) + "/" + textOf(global_missionSummary(d).total) + "</span></div><h2>" + textOf(global_esc(jsOr(d.title) { "Tu misión del día" })) + "</h2><p>Una ventana. Tus objetivos. Cada casilla cuenta.</p><button class=\"btn primary full\" onclick=\"showMission()\">ABRIR VENTANA DE MISIONES →</button><button class=\"text-button\" onclick=\"editMission('" + textOf(d.date) + "')\">✎ Personalizar la misión</button></section>")
        }
        val hero: dynamic = container.querySelector(".command-hero")
        if (truth(hero)) {
            hero.querySelector(".greeting").innerHTML = ("" + textOf(global_avatarHtml("avatar-inline")) + " " + textOf(global_esc(global_state.profile.name)) + " · " + textOf(global_esc(jsOr(global_Adapt.goals[global_state.profile.goal]) { global_Adapt.goals.general })) + "")
        }
        return jsAdd(("<div class=\"adaptation-chip\"><span>◇</span><div>" + textOf(global_esc(global_Adapt.mode(global_state.profile).reason)) + "</div></div>"), container.innerHTML)
    })
    global_oldTechnique = global_technique
    global_technique = (fun(id: dynamic): dynamic {
        val returnDate: dynamic = global_Dollar("#modal").dataset.mission
        global_oldTechnique(id)
        removeKey(global_Dollar("#modal").dataset, "mission")
        val e: dynamic = global_E.byId(id)
        val reasons: dynamic = global_Adapt.reasons(e, global_state.profile)
        val info: dynamic = global_document.createElement("div")
        info.className = jsAdd("notice", (if (truth(reasons.length)) " warn" else ""))
        info.innerHTML = jsAdd((if (truth((e.variant !== "base"))) ("<b>Variante de " + textOf(global_esc(global_E.byId(e.baseId).name)) + "</b><br>" + textOf(global_esc(e.variantDescription)) + "<br>") else ""), (if (truth(reasons.length)) jsAdd("No incluida en tu selección actual: ", global_esc(reasons.join(" · "))) else "Coincide con los filtros declarados. Esto no sustituye una evaluación individual."))
        if (truth(reasons.length)) {
            info.innerHTML = jsAdd("Revisa las restricciones de este ejercicio: ", global_esc(reasons.join(" · ")))
            global_Dollar("#modal").prepend(info)
        }
        if (truth(returnDate)) {
            val back: dynamic = global_document.createElement("button")
            back.className = "btn ghost full"
            back.textContent = "← Volver a la ventana de misiones"
            back.onclick = (fun(): dynamic {
                return global_showMission(returnDate)
            })
            global_Dollar("#modal").prepend(back)
        }
        global_Dollar("#modal").scrollTop = 0
        return absent
    })
    global_exerciseList = (fun(): dynamic {
        return global_V2.exerciseList().filter((fun(e: dynamic): dynamic {
            return jsAnd(jsOr(global_showVariants) { (e.variant === "base") }) { jsOr((!truth(global_profileFilter))) { global_Adapt.allowed(e, global_state.profile) } }
        }))
    })
    global_book = (fun(): dynamic {
        var h: dynamic = global_V2.book()
        h = h.replace(("" + textOf(global_E.exercises.length) + " movimientos · fichas detalladas en español"), ("" + textOf(global_E.exercises.length) + " fichas: " + textOf(global_Catalog.baseExercises) + " movimientos base + variantes explicadas"))
        h = h.replace("<p class=\"result-count\"", ("<div class=\"catalog-options\"><label><input type=\"checkbox\" " + textOf((if (truth(global_showVariants)) "checked" else "")) + " onchange=\"HunterActions.setVariants(this)\"> Incluir variantes de técnica y ritmo</label><label><input type=\"checkbox\" " + textOf((if (truth(global_profileFilter)) "checked" else "")) + " onchange=\"HunterActions.filterExercisesByProfile(this)\"> Aplicar mis restricciones</label></div><p class=\"result-count\""))
        return h
    })
    global_routineList = (fun(): dynamic {
        return global_V2.routineList().filter((fun(r: dynamic): dynamic {
            return jsOr((!truth(global_routineProfileFilter))) { global_Adapt.routineCompatible(r, global_state.profile) }
        }))
    })
    global_routines = (fun(): dynamic {
        val h: dynamic = global_V2.routines()
        return h.replace(("" + textOf(global_Catalog.routines.length) + " plantillas + tus propias sesiones"), ("" + textOf(global_Catalog.routines.length) + " plantillas · " + textOf(global_Catalog.baseRoutines) + " bases con variantes de volumen")).replace("<p class=\"result-count\"", ("<label class=\"row\" style=\"margin-top:12px\"><input type=\"checkbox\" " + textOf((if (truth(global_routineProfileFilter)) "checked" else "")) + " onchange=\"HunterActions.filterRoutinesByProfile(this)\"> Solo rutinas que pasan mis filtros</label><p class=\"result-count\""))
    })
    global_prepareRoutine = (fun(id: dynamic): dynamic {
        val r: dynamic = global_allRoutines().find((fun(r: dynamic): dynamic {
            return (r.id === id)
        }))
        if (truth(jsOr((!truth(r))) { (!truth(global_Adapt.routineCompatible(r, global_GoalPlans.adjusted(global_state.profile)))) })) {
            return global_routineNotice372("Esta rutina no coincide con las restricciones de tu perfil. Revisa los filtros o elige una rutina compatible.")
        }
        global_V2.prepareRoutine(id)
        return absent
    })
    global_applyRoutine = (fun(): dynamic {
        val date: dynamic = onPresent(global_pendingRoutine) { value191 -> value191.date }
        val tasks: dynamic = (if (truth(date)) global_JSON.parse(global_JSON.stringify(jsOr(onPresent(global_dayByDate(date)) { value193 -> value193.tasks }) { js("[]") })).filter((fun(t: dynamic): dynamic {
            return (!truth(dynamicValue(jsonValue("[\"rest\",\"review\",\"notes\"]")).includes(t.id)))
        })) else js("[]"))
        global_V2.applyRoutine()
        if (truth(date)) {
            val d: dynamic = global_dayByDate(date)
            if (truth(d)) {
                d.tasks = tasks
                d.customized = true
                global_save()
            }
        }
        global_detailedMode = false
        global_render()
        return absent
    })
    global_editExercise = (fun(i: dynamic): dynamic {
        if (truth((!truth(global_Adapt.mode(global_state.profile).auto)))) {
            return global_toast("Revisa primero tus indicaciones en el perfil.")
        }
        global_V2.editExercise(i)
        val select: dynamic = global_Dollar("#swap")
        if (truth(select)) {
            dynamicValue(jsArray { array201 ->
                appendAll(array201, select.options)
            }).forEach((fun(o: dynamic): dynamic {
                if (truth((!truth(global_Adapt.allowed(global_E.byId(o["value"]), global_state.profile))))) {
                    o.remove()
                }
                return absent
            }))
        }
        return absent
    })
    global_applyExercise = (fun(i: dynamic): dynamic {
        if (truth(jsOr((!truth(global_Dollar("#swap")["value"]))) { (!truth(global_Adapt.allowed(global_E.byId(global_Dollar("#swap")["value"]), global_state.profile))) })) {
            return global_toast("No hay una alternativa compatible con esos filtros.")
        }
        global_V2.applyExercise(i)
        return absent
    })
    global_finish = (fun(): dynamic {
        global_V2.finish()
        val l: dynamic = global_getLog()
        if (truth(onPresent(l) { value206 -> value206.complete })) {
            l.items.forEach((fun(x: dynamic): dynamic {
                return run {
                    val target207: dynamic = x
                    val assigned209: dynamic = true
                    target207.missionState = assigned209
                    assigned209
                }
            }))
            val d: dynamic = global_todayDay()
            if (truth(global_ensureTasks(d).some((fun(t: dynamic): dynamic {
                return (!truth(t.done))
            })))) {
                global_recomputeMission(d)
            } else {
                global_save()
            }
        }
        return absent
    })
    global_confirmQuickFinish = (fun(): dynamic {
        global_V2.confirmQuickFinish()
        val l: dynamic = global_getLog()
        if (truth(onPresent(l) { value212 -> value212.complete })) {
            l.items.forEach((fun(x: dynamic): dynamic {
                return run {
                    val target213: dynamic = x
                    val assigned215: dynamic = true
                    target213.missionState = assigned215
                    assigned215
                }
            }))
            global_ensureTasks(global_todayDay()).forEach((fun(t: dynamic): dynamic {
                return run {
                    val target216: dynamic = t
                    val assigned218: dynamic = true
                    target216.done = assigned218
                    assigned218
                }
            }))
            global_save()
        }
        return absent
    })
    global_historyV2 = global_history
    global_history = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_historyV2()
        val entries: dynamic = global_Object.entries(global_state.logs).sort((fun(a: dynamic, b: dynamic): dynamic {
            return b[0].localeCompare(a[0])
        }))
        box.querySelectorAll(".history-item").forEach((fun(node: dynamic, i: dynamic): dynamic {
            var date: dynamic = absent
            var l: dynamic = absent
            val unpacked223: dynamic = fromIterable(entries[i])
            date = unpacked223[0]
            l = unpacked223[1]
            val d: dynamic = global_dayByDate(date)
            if (truth(onPresent(d) { value224 -> value224.title })) {
                val title: dynamic = global_document.createElement("p")
                title.innerHTML = jsAdd(jsAdd("<b>", global_esc(d.title)), "</b>")
                node.append(title)
            }
            val tasks: dynamic = jsOr(jsOr(l.missionTasks) { onPresent(d) { value227 -> value227.tasks } }) { js("[]") }
            if (truth(tasks.length)) {
                val list: dynamic = global_document.createElement("div")
                list.className = "history-missions"
                list.innerHTML = jsAdd("<h3>Misiones personales</h3>", tasks.map((fun(t: dynamic): dynamic {
                    return jsAdd(jsAdd(jsAdd("<p>", (if (truth(t.done)) "✓ " else "◇ ")), global_esc(t.text)), "</p>")
                })).join(""))
                node.append(list)
            }
            return absent
        }))
        return box.innerHTML
    })
    global_syncNative = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        if (truth((!truth(onPresent(global_window.Android) { value234 -> value234.syncPlan })))) {
            return absent
        }
        try {
            val days: dynamic = global_state.cycles.flatMap((fun(c: dynamic): dynamic {
                return c.days
            })).filter((fun(d: dynamic): dynamic {
                return jsAtLeast(d.date, global_E.dateKey())
            })).slice((-numeric(42))).map((fun(d: dynamic): dynamic {
                return jsObject { obj235 ->
                    obj235["date"] = d.date
                    obj235["title"] = jsOr(d.title) { global_kindLabel(d.kind) }
                    obj235["description"] = d.items.map((fun(x: dynamic): dynamic {
                        return jsOr(x.missionTitle) { global_E.byId(x.id).name }
                    })).concat(global_ensureTasks(d).map((fun(t: dynamic): dynamic {
                        return t.text
                    }))).join(" · ").slice(0, 400)
                    obj235["complete"] = (!truth((!truth(onPresent(global_state.logs[d.date]) { value236 -> value236.complete }))))
                    obj235["stopped"] = (!truth((!truth(onPresent(global_state.logs[d.date]) { value237 -> value237.pain }))))
                }
            }))
            global_HunterNative3875.syncPlan(jsObject { obj238 ->
                obj238["eligible"] = (!truth((!truth(global_state.profile))))
                obj238["days"] = days
            })
        } catch (e: dynamic) {

        }
        return absent
    })
    global_validateV2 = global_validateBackup
    global_validateBackup = (fun(s: dynamic): dynamic {
        if (truth((!truth(global_validateV2(s))))) {
            return false
        }
        if (truth(jsAnd(s.avatar) { (!truth(global_photoValid(s.avatar))) })) {
            return false
        }
        val profiles: dynamic = dynamicValue(jsArray { array242 ->
            array242.push(s.profile)
            appendAll(array242, s.cycles.map((fun(c: dynamic): dynamic {
                return c.profile
            })))
        }).filter(global_Boolean)
        if (true) {
            val iterator243: dynamic = iteratorOf(profiles)
            while (true) {
                val next244: dynamic = iterator243.next()
                if (truth(next244.done)) break
                var p: dynamic = absent
                p = next244.value
                if (truth(jsAnd(p.goal) { (!truth(global_Adapt.goals[p.goal])) })) {
                    return false
                }
                if (truth(jsAnd((p.minutes !== absent)) { (!truth(dynamicValue(jsonValue("[10,20,30,45]")).includes(p.minutes))) })) {
                    return false
                }
                if (truth(jsAnd((p.injuries !== absent)) { jsOr(jsOr((!truth(global_Array.isArray(p.injuries)))) { jsMore(p.injuries.length, 8) }) { p.injuries.some((fun(i: dynamic): dynamic {
                    return jsOr(jsOr(jsOr((!truth(i))) { (!truth(global_Adapt.zones[i.zone])) }) { (!truth(global_Adapt.types[i.type])) }) { (!truth(dynamicValue(jsonValue("[\"active\",\"recovering\",\"past\"]")).includes(i.stage))) }
                })) } })) {
                    return false
                }
                if (truth(jsAnd((p.recommendations !== absent)) { jsOr((jsTypeOf(p.recommendations) !== "string")) { jsMore(p.recommendations.length, 1000) } })) {
                    return false
                }
                if (truth(jsAnd(p.health) { jsOr((!truth(dynamicValue(jsonValue("[\"none\",\"mild\",\"moderate\",\"strong\"]")).includes(p.health.pain)))) { dynamicValue(jsonValue("[\"approved\",\"translated\",\"acute\",\"special\"]")).some((fun(k: dynamic): dynamic {
                    return (jsTypeOf(p.health[k]) !== "boolean")
                })) } })) {
                    return false
                }
                if (truth(jsAnd(p.restrictions) { global_Object.values(p.restrictions).some((fun(v: dynamic): dynamic {
                    return (jsTypeOf(v) !== "boolean")
                })) })) {
                    return false
                }
            }
        }
        if (true) {
            val iterator245: dynamic = iteratorOf(s.cycles)
            while (true) {
                val next246: dynamic = iterator245.next()
                if (truth(next246.done)) break
                var c: dynamic = absent
                c = next246.value
                if (true) {
                    val iterator247: dynamic = iteratorOf(c.days)
                    while (true) {
                        val next248: dynamic = iterator247.next()
                        if (truth(next248.done)) break
                        var d: dynamic = absent
                        d = next248.value
                        if (truth(jsAnd((d.title !== absent)) { jsOr((jsTypeOf(d.title) !== "string")) { jsMore(d.title.length, 100) } })) {
                            return false
                        }
                        if (truth(jsAnd((d.tasks !== absent)) { jsOr(jsOr((!truth(global_Array.isArray(d.tasks)))) { jsMore(d.tasks.length, 12) }) { d.tasks.some((fun(t: dynamic): dynamic {
                            return jsOr(jsOr(jsOr((!truth(t))) { (jsTypeOf(t.text) !== "string") }) { jsMore(t.text.length, 120) }) { (jsTypeOf(t.done) !== "boolean") }
                        })) } })) {
                            return false
                        }
                        if (true) {
                            val iterator249: dynamic = iteratorOf(d.items)
                            while (true) {
                                val next250: dynamic = iterator249.next()
                                if (truth(next250.done)) break
                                var x: dynamic = absent
                                x = next250.value
                                if (truth(jsAnd((x.missionTitle !== absent)) { jsOr((jsTypeOf(x.missionTitle) !== "string")) { jsMore(x.missionTitle.length, 100) } })) {
                                    return false
                                }
                            }
                        }
                    }
                }
            }
        }
        if (true) {
            val iterator251: dynamic = iteratorOf(global_Object.values(s.logs))
            while (true) {
                val next252: dynamic = iterator251.next()
                if (truth(next252.done)) break
                var l: dynamic = absent
                l = next252.value
                if (true) {
                    val iterator253: dynamic = iteratorOf(l.items)
                    while (true) {
                        val next254: dynamic = iterator253.next()
                        if (truth(next254.done)) break
                        var x: dynamic = absent
                        x = next254.value
                        if (truth(jsAnd((x.missionState !== absent)) { (jsTypeOf(x.missionState) !== "boolean") })) {
                            return false
                        }
                    }
                }
            }
        }
        return true
    })
    global_confirmImport = (fun(): dynamic {
        global_V2.confirmImport()
        if (truth(global_state.profile)) {
            global_state.profile = global_Adapt.normalize(global_state.profile)
        }
        global_draftAvatar = jsOr(global_state.avatar) { "" }
        global_migratePending()
        global_render()
        return absent
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        removeKey(global_Dollar("#modal").dataset, "mission")
        return absent
    }))
}
