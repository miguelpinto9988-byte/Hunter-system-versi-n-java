@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterv377

import hunter.browser.*

@JsName("String") private external var global_String: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("CompanionNames") private external var global_CompanionNames: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("HunterImprove374") private external var global_HunterImprove374: dynamic
@JsName("console") private external var global_console: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("localStorage") private external var global_localStorage: dynamic
@JsName("KEY") private external var global_KEY: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterV377() {
    dynamicValue((fun(root: dynamic): dynamic {
        var FIELDS: dynamic = absent
        var TITLES: dynamic = absent
        var ESC: dynamic = absent
        var STABLE: dynamic = absent
        var digest377: dynamic = absent
        var known: dynamic = absent
        var INTERNAL: dynamic = absent
        var NAMED: dynamic = absent
        var diffProfile: dynamic = absent
        var snapshot: dynamic = absent
        var weekSummary: dynamic = absent
        var reportFormNames: dynamic = absent
        var dialog377: dynamic = absent
        digest377 = fun(input: dynamic): dynamic {
            val txt: dynamic = global_String(nullableOr(input) { "" })
            var hash: dynamic = 2166136261.0
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration5 = true
                while (true) {
                    if (!firstIteration5) {
                        run {
                            val previous6: dynamic = numeric(i)
                            i = jsAdd(previous6, 1)
                        }
                    }
                    firstIteration5 = false
                    if (!truth(jsLess(i, txt.length))) break
                    run {
                        hash = jsBitXor(hash, txt.charCodeAt(i))
                    }
                    hash = global_Math.imul(hash, 16777619)
                }
            }
            return dynamicValue(jsAdd("00000000", dynamicValue(jsUnsignedShift(hash, 0))["toString"](16))).slice((-numeric(8)))
        }
        diffProfile = fun(before: dynamic, after: dynamic, oldLook: dynamic, newLook: dynamic): dynamic {
            val prior: dynamic = jsOr(before) { js("({})") }
            val next: dynamic = jsOr(after) { js("({})") }
            val groups: dynamic = js("[]")
            val keys: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsArray { array9 ->
                appendAll(array9, global_Object.keys(prior))
                appendAll(array9, global_Object.keys(next))
            }))
            if (true) {
                val iterator10: dynamic = iteratorOf(global_Object.entries(FIELDS))
                while (true) {
                    val next11: dynamic = iterator10.next()
                    if (truth(next11.done)) break
                    var group: dynamic = absent
                    var fields: dynamic = absent
                    val unpacked12: dynamic = fromIterable(next11.value)
                    group = unpacked12[0]
                    fields = unpacked12[1]
                    if (truth(fields.some((fun(k: dynamic): dynamic {
                        return (STABLE(prior[k]) !== STABLE(next[k]))
                    })))) {
                        groups.push(group)
                    }
                }
            }
            if (truth(dynamicValue(jsArray { array14 ->
                appendAll(array14, keys)
            }).some((fun(k: dynamic): dynamic {
                return jsAnd(jsAnd((!truth(known.has(k)))) { (!truth(INTERNAL.has(k))) }) { (STABLE(prior[k]) !== STABLE(next[k])) }
            })))) {
                groups.push("otros")
            }
            if (truth(jsAnd((STABLE(oldLook) !== STABLE(newLook))) { (!truth(groups.includes("apariencia"))) })) {
                groups.push("apariencia")
            }
            return groups
        }
        snapshot = fun(p: dynamic, appearance: dynamic, avatar: dynamic): dynamic {
            val cfg: dynamic = global_JSON.stringify(jsObject { obj17 ->
                obj17["profile"] = global_Object.fromEntries(global_Object.entries(jsOr(p) { js("({})") }).filter((fun(arg18: dynamic): dynamic {
                    var k: dynamic = absent
                    val unpacked19: dynamic = fromIterable(arg18)
                    k = unpacked19[0]
                    return (!truth(INTERNAL.has(k)))
                })))
                obj17["appearance"] = jsOr(appearance) { null }
                obj17["photoFingerprint"] = digest377(avatar)
            })
            return digest377(cfg)
        }
        weekSummary = fun(p: dynamic): dynamic {
            val week: dynamic = (if (truth(global_Array.isArray(onPresent(p) { value22 -> value22.weekPlan }))) p.weekPlan else js("[]"))
            val c: dynamic = jsonValue("{\"train\":0,\"cardio\":0,\"mobility\":0,\"rest\":0}")
            if (true) {
                val iterator23: dynamic = iteratorOf(week)
                while (true) {
                    val next24: dynamic = iterator23.next()
                    if (truth(next24.done)) break
                    var x: dynamic = absent
                    x = next24.value
                    if (truth(global_Object.prototype.hasOwnProperty.call(c, x))) {
                        run {
                            val target25: dynamic = c
                            val key26: dynamic = x
                            val previous27: dynamic = numeric(target25[key26])
                            target25[key26] = jsAdd(previous27, 1)
                        }
                    }
                }
            }
            return ("" + textOf(c.train) + " fuerza · " + textOf(c.cardio) + " cardio · " + textOf(c.mobility) + " movilidad · " + textOf(c.rest) + " descanso")
        }
        reportFormNames = fun(form: dynamic): dynamic {
            return dynamicValue(jsArray { array31 ->
                appendAll(array31, jsConstruct(global_Set, arrayOf<dynamic>(global_Array.from(form.elements).map((fun(e: dynamic): dynamic {
                    return e.name
                })).filter(global_Boolean))))
            }).sort()
        }
        dialog377 = fun(arg53: dynamic): dynamic {
            var initial: dynamic = absent
            var groups: dynamic = absent
            var profile: dynamic = absent
            var goal: dynamic = absent
            var updated: dynamic = absent
            var validKeys: dynamic = absent
            var planStatus: dynamic = absent
            val unpacked54: dynamic = arg53
            initial = unpacked54["initial"]
            groups = unpacked54["groups"]
            profile = unpacked54["profile"]
            goal = unpacked54["goal"]
            updated = unpacked54["updated"]
            validKeys = unpacked54["validKeys"]
            planStatus = unpacked54["planStatus"]
            val labels: dynamic = (if (truth(groups.length)) groups.map((fun(x: dynamic): dynamic {
                return jsOr(TITLES[x]) { x }
            })) else jsonValue("[\"Sin cambios en los criterios\"]"))
            val goals: dynamic = (if (truth(jsAnd(global_Array.isArray(onPresent(goal) { value55 -> value55.alternatives })) { goal.alternatives.length })) goal.alternatives.map((fun(k: dynamic): dynamic {
                return jsOr(onPresent(global_GoalGroups14.groups[k]) { value57 -> value57.name }) { k }
            })).join(" · ") else jsOr(onPresent(global_GoalPlans.plans[profile.finalTarget]) { value58 -> value58.name }) { "Objetivo seleccionado" })
            val mode: dynamic = (if (truth((profile.trainingMode === "routines"))) "Rutinas completas" else "Misiones diarias")
            val health: dynamic = jsOr(jsOr(dynamicValue(jsOr(profile.injuries) { js("[]") }).some((fun(x: dynamic): dynamic {
                return (x.stage !== "past")
            }))) { profile.limits }) { jsAnd(onPresent(profile.health) { value59 -> value59.pain }) { (profile.health.pain !== "none") } }
            val status: dynamic = (if (truth((planStatus === "reviewed"))) "Se revisaron las sesiones futuras elegibles respetando los registros existentes." else (if (truth((planStatus === "restricted"))) "Tu perfil está guardado. Según las limitaciones indicadas, puede requerirse revisión antes de proponer ejercicio automático." else "Perfil guardado. No se pudo comprobar la actualización inmediata del plan; revisa Misiones."))
            global_modal(("<section class=\"profile-confirm377\" role=\"status\" aria-live=\"polite\">\n <span class=\"eyebrow\">HUNTER SYSTEM · PERFIL UNIFICADO</span><h2>" + textOf((if (truth(initial)) "Perfil creado y guardado" else "Perfil actualizado y guardado")) + "</h2>\n <p>El acompañante revisó los criterios guardados de tu perfil mediante reglas locales.</p>\n <div class=\"profile-confirm-box377\"><b>Preferencias analizadas</b><p>" + textOf(ESC(labels.join(" · "))) + "</p><p><strong>Objetivo:</strong> " + textOf(ESC(goals)) + "</p><p><strong>Semana:</strong> " + textOf(ESC(weekSummary(profile))) + "</p><p><strong>Modalidad:</strong> " + textOf(ESC(mode)) + " · <strong>Nivel:</strong> " + textOf(ESC(jsOr(jsOr(NAMED[profile.difficulty]) { profile.difficulty }) { "Básico" })) + "</p><p><strong>Equipo:</strong> " + textOf(ESC(dynamicValue((if (truth(global_Array.isArray(profile.equipmentModes3880))) profile.equipmentModes3880 else (if (truth((profile.equipment === "gym"))) jsonValue("[\"body\",\"db\",\"gym\"]") else (if (truth((profile.equipment === "db"))) jsonValue("[\"body\",\"db\"]") else jsonValue("[\"body\"]"))))).map((fun(k: dynamic): dynamic {
                return jsOr(jsonValue("{\"body\":\"Peso corporal\",\"db\":\"Mancuernas\",\"gym\":\"Gimnasio\"}")[k]) { k }
            })).join(" + "))) + " · <strong>Acompañante:</strong> " + textOf(ESC(jsOr(jsOr(jsAnd((js("typeof CompanionNames") !== "undefined")) { global_CompanionNames[profile.companionCharacter] }) { profile.companionCharacter }) { "Elegido" })) + "</p></div>\n <p class=\"notice\">" + textOf(ESC(status)) + "</p>\n " + textOf((if (truth(health)) "<p class=\"footnote\">Hay condiciones o restricciones declaradas. Estas reglas no realizan una valoración médica ni verifican tu postura.</p>" else "")) + "\n <p class=\"footnote\">" + textOf(validKeys) + " campos del formulario fueron revisados para identificar los ajustes. Se comparó la configuración guardada. Los datos permanecen en el almacenamiento local de la aplicación.</p>\n <button class=\"btn primary full\" type=\"button\" onclick=\"closeActiveModal()\">Continuar</button></section>"))
            return absent
        }
        val UI_NAME: dynamic = "Guardado de perfil creado"
        FIELDS = jsonValue("{\"identidad\":[\"name\",\"age\",\"height\",\"weight\",\"gender\"],\"metas\":[\"goalGroup\",\"finalTarget\",\"finalGoal\",\"goal\",\"focus\",\"difficulty\",\"trainingScale\"],\"calendario\":[\"weekPlan\",\"frequency\",\"dailyMissions\"],\"entrenamiento\":[\"experience\",\"equipment\",\"equipmentModes3880\",\"gymEquipment23\",\"load\",\"bench\",\"step\",\"restSeconds\",\"minutes\"],\"modalidad\":[\"trainingMode\",\"preferredTrainer\"],\"salud\":[\"injuries\",\"restrictions\",\"recommendations\",\"health\",\"limits\",\"goalAutoRestrictions374\"],\"acompanante\":[\"companionCharacter\",\"companionStyle\",\"animate\"],\"apariencia\":[\"theme\"]}")
        TITLES = jsonValue("{\"identidad\":\"Datos personales\",\"metas\":\"Objetivos e intensidad\",\"calendario\":\"Días de fuerza, cardio, movilidad y descanso\",\"entrenamiento\":\"Equipo y parámetros de entrenamiento\",\"modalidad\":\"Rutinas y misiones\",\"salud\":\"Lesiones, restricciones e indicaciones\",\"acompanante\":\"Acompañante y animaciones\",\"apariencia\":\"Colores, tema y presentación\",\"otros\":\"Otros criterios del perfil\"}")
        ESC = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        STABLE = (fun(x: dynamic): dynamic {
            return global_JSON.stringify(nullableOr(x) { null })
        })
        known = jsConstruct(global_Set, arrayOf<dynamic>(global_Object.values(FIELDS).flat()))
        INTERNAL = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"interpretation374\",\"profileInterpretation377\",\"profileRevision377\"]")))
        val WEEK: dynamic = jsonValue("{\"train\":\"fuerza\",\"cardio\":\"cardio\",\"mobility\":\"movilidad\",\"rest\":\"descanso\"}")
        NAMED = jsonValue("{\"basic\":\"Básico\",\"intermediate\":\"Intermedio\",\"advanced\":\"Avanzado\"}")
        val previousProfileForm: dynamic = global_profileForm
        global_profileForm = (fun(arg34: dynamic): dynamic {
            var initial: dynamic = absent
            initial = definedOr(arg34) { false }
            val doc: dynamic = global_document.createElement("div")
            doc.innerHTML = previousProfileForm(initial)
            val form: dynamic = doc.querySelector("#profileForm")
            if (truth((!truth(form)))) {
                return doc.innerHTML
            }
            form.querySelectorAll(".goal-control374 button.goal-apply374, button[onclick*=\"interpretGoal(\"]").forEach((fun(b: dynamic): dynamic {
                return b.remove()
            }))
            val note: dynamic = form.querySelector(".goal-control374 > .footnote")
            if (truth(note)) {
                note.textContent = "La vista previa orienta tu objetivo. Al guardar el perfil se interpretan también tus días, equipo, preferencias, modalidades y restricciones declaradas."
            }
            val old: dynamic = form.querySelector("button[type=\"submit\"]")
            if (truth(old)) {
                val button: dynamic = global_document.createElement("button")
                button.type = "submit"
                button.className = "btn primary full profile-save377"
                button.textContent = UI_NAME
                button.setAttribute("aria-label", "Guardar el perfil e interpretar todos los criterios de entrenamiento")
                button.setAttribute("aria-describedby", "profile-save-explain377")
                old.replaceWith(button)
                val explain: dynamic = global_document.createElement("p")
                explain.id = "profile-save-explain377"
                explain.className = "footnote profile-save-help377"
                explain.textContent = "Acción única: guardar datos, interpretar objetivos y revisar sesiones futuras compatibles. Se conservan los registros realizados."
                button.after(explain)
            }
            return doc.innerHTML
        })
        val previousSubmit: dynamic = global_submitProfile
        global_submitProfile = (fun(event: dynamic): dynamic {
            val form: dynamic = onPresent(event) { value62 -> value62.target }
            if (truth(jsOr((!truth(form))) { (form.id !== "profileForm") })) {
                return previousSubmit(event)
            }
            val before: dynamic = global_state.profile
            val priorLook: dynamic = jsObject { obj63 ->
                obj63["appearance"] = global_state.appearance33
                obj63["photo"] = digest377(global_state.avatar)
            }
            val wasInitial: dynamic = (!truth(before))
            if (truth((!truth(form.reportValidity())))) {
                return absent
            }
            var interp: dynamic = dynamicValue(null)
            try {
                interp = global_HunterImprove374.refresh(form, true)
            } catch (e: dynamic) {
                global_console.warn("Vista previa de objetivo no disponible", e)
            }
            val names: dynamic = reportFormNames(form)
            try {
                previousSubmit(event)
            } catch (e: dynamic) {
                global_console.error("No se pudo guardar el perfil", e)
                global_toast("No fue posible guardar el perfil. Revisa los campos.")
                return absent
            }
            if (truth(jsOr((!truth(global_state.profile))) { (global_state.profile === before) })) {
                return absent
            }
            val after: dynamic = global_state.profile
            val look: dynamic = jsObject { obj64 ->
                obj64["appearance"] = global_state.appearance33
                obj64["photo"] = digest377(global_state.avatar)
            }
            val changed: dynamic = diffProfile(before, after, priorLook, look)
            val fingerprint: dynamic = snapshot(after, global_state.appearance33, global_state.avatar)
            val savedAt: dynamic = jsConstruct(global_Date, js("[]")).toISOString()
            val previousConfirmation: dynamic = global_state.profileSave377
            global_state.profileSave377 = jsObject { obj67 ->
                obj67["schema"] = "37.7"
                obj67["savedAt"] = savedAt
                obj67["fingerprint"] = fingerprint
                obj67["changedGroups"] = changed
                obj67["inspectedFormFields"] = names.length
            }
            if (truth((!truth(global_save())))) {
                global_state.profileSave377 = previousConfirmation
                return absent
            }
            var persisted: dynamic = false
            try {
                val onDisk: dynamic = global_JSON.parse(global_localStorage.getItem(global_KEY))
                persisted = jsAnd((onPresent(onDisk) { value70 -> onPresent(value70.profileSave377) { value71 -> value71.fingerprint } } === fingerprint)) { (onPresent(onDisk) { value72 -> onPresent(value72.profile) { value73 -> value73.name } } === after.name) }
            } catch (e: dynamic) {

            }
            if (truth((!truth(persisted)))) {
                global_toast("No se pudo comprobar el guardado del perfil.")
                return absent
            }
            var planStatus: dynamic = "not-checked"
            if (truth(global_E.eligible(after))) {
                try {
                    global_currentCycle()
                    planStatus = "reviewed"
                } catch (e: dynamic) {
                    global_console.warn("Revisión de sesiones pendientes", e)
                }
            } else {
                planStatus = "restricted"
            }
            try {
                dialog377(jsObject { obj74 ->
                    obj74["initial"] = wasInitial
                    obj74["groups"] = changed
                    obj74["profile"] = after
                    obj74["goal"] = interp
                    obj74["updated"] = savedAt
                    obj74["validKeys"] = names.length
                    obj74["planStatus"] = planStatus
                })
            } catch (e: dynamic) {
                global_console.warn("Confirmación visual", e)
                global_toast("Perfil guardado y preferencias analizadas.")
            }
            return absent
        })
        val previousAnswer: dynamic = global_answerCoach
        global_answerCoach = (fun(input: dynamic): dynamic {
            val q: dynamic = global_String(jsOr(input) { "" })
            if (truth(jsAnd(global_state.profile) { dynamicValue(jsRegExp("(?:guardar\\s+perfil|actualizar\\s+perfil|cambiar\\s+objetivos|interpretaci[oó]n\\s+del\\s+perfil)", "i")).test(q) })) {
                return jsAdd(jsAdd(jsAdd(global_companionDisplayName(), ": Tus objetivos, días, equipo, intensidad, modalidad, restricciones, apariencia y elección del acompañante se guardan juntos desde el botón «"), UI_NAME), "» al final del perfil. Los registros completados se mantienen.")
            }
            return previousAnswer(input)
        })
        root.HunterV377 = jsObject { obj79 ->
            obj79["version"] = "37.7"
            obj79["getFormFields"] = reportFormNames
            obj79["changes"] = diffProfile
            obj79["fingerprint"] = snapshot
            obj79["summary"] = weekSummary
        }
        try {
            global_render()
        } catch (e: dynamic) {
            global_console.warn("V37.7 render:", e)
        }
        return absent
    }))(global_window)
}
