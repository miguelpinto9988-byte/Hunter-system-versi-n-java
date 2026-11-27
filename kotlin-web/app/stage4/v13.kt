@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv13

import hunter.browser.*

@JsName("academyObserver15") private external var global_academyObserver15: dynamic
@JsName("V13UI") private external var global_V13UI: dynamic
@JsName("loadControl13") private external var global_loadControl13: dynamic
@JsName("pendingDay13") private external var global_pendingDay13: dynamic
@JsName("rebuildPending13") private external var global_rebuildPending13: dynamic
@JsName("capacityForm13") private external var global_capacityForm13: dynamic
@JsName("openCapacity13") private external var global_openCapacity13: dynamic
@JsName("applyCapacity13") private external var global_applyCapacity13: dynamic
@JsName("afterCompletion13") private external var global_afterCompletion13: dynamic
@JsName("galleryActors13") private external var global_galleryActors13: dynamic
@JsName("modal13") private external var global_modal13: dynamic
@JsName("clearGallery13") private external var global_clearGallery13: dynamic
@JsName("sampleCompanion13") private external var global_sampleCompanion13: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("openAcademy") private external var global_openAcademy: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("finish") private external var global_finish: dynamic
@JsName("confirmQuickFinish") private external var global_confirmQuickFinish: dynamic
@JsName("finishGuide11") private external var global_finishGuide11: dynamic
@JsName("toggleMissionItem") private external var global_toggleMissionItem: dynamic
@JsName("toggleMissionTask") private external var global_toggleMissionTask: dynamic
@JsName("SourceSequences13") private external var global_SourceSequences13: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("CreatorFeed") private external var global_CreatorFeed: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("MotionPlayback") private external var global_MotionPlayback: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("RoutinePlanner") private external var global_RoutinePlanner: dynamic
@JsName("allRoutines10") private external var global_allRoutines10: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("vectorPanel") private external var global_vectorPanel: dynamic
@JsName("startVector") private external var global_startVector: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("Mesh3D") private external var global_Mesh3D: dynamic
@JsName("IntersectionObserver") private external var global_IntersectionObserver: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("FormData") private external var global_FormData: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("IgrisCharacter") private external var global_IgrisCharacter: dynamic
@JsName("openAnimationGallery") private external var global_openAnimationGallery: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("CompanionNames") private external var global_CompanionNames: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("vectorGeneration13") private external var global_vectorGeneration13: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic

internal fun installV13() {
    global_loadControl13 = fun(arg8: dynamic): dynamic {
        var scale: dynamic = absent
        scale = definedOr(arg8) { 1 }
        return ("<label class=\"field\">Carga del plan adaptado <output id=\"trainingScaleLabel\">" + textOf(global_Math.round(jsMultiply(scale, 100))) + "%</output><input name=\"trainingScale\" type=\"range\" min=\"0.4\" max=\"1\" step=\"0.01\" value=\"" + textOf(scale) + "\" oninput=\"HunterActions.showScaledPercent(this)\"></label>")
    }
    global_pendingDay13 = fun(d: dynamic): dynamic {
        return jsAnd(jsAnd(jsAnd(jsAnd(jsAtLeast(d.date, global_E.dateKey())) { (!truth(d.customized)) }) { (!truth(d.routineId)) }) { (!truth(global_Weekly.active(global_state.logs[d.date]))) }) { (!truth(onPresent(d.tasks) { value21 -> value21.some((fun(t: dynamic): dynamic {
            return t.done
        })) })) }
    }
    global_rebuildPending13 = fun(c: dynamic, arg24: dynamic): dynamic {
        var preserveProgress: dynamic = absent
        preserveProgress = definedOr(arg24) { false }
        if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
            return absent
        }
        val review: dynamic = onPresent(c.reviews) { value25 -> onPresent(value25.at((-numeric(1)))) { value26 -> value26.action } }
        if (truth((review === "pausar"))) {
            return absent
        }
        val fresh: dynamic = global_E.makeCycle(global_state.profile, c.start, c.index, null, global_state.logs)
        c.days = c.days.map((fun(d: dynamic, i: dynamic): dynamic {
            if (truth((!truth(global_pendingDay13(d))))) {
                return d
            }
            val n: dynamic = fresh.days[i]
            if (truth(jsAnd(preserveProgress) { dynamicValue(jsonValue("[\"aumentar\",\"mantener\"]")).includes(review) })) {
                if (true) {
                    val iterator29: dynamic = iteratorOf(n.items)
                    while (true) {
                        val next30: dynamic = iterator29.next()
                        if (truth(next30.done)) break
                        var x: dynamic = absent
                        x = next30.value
                        val prior: dynamic = d.items.find((fun(y: dynamic): dynamic {
                            return (y.id === x.id)
                        }))
                        if (truth(prior)) {
                            x.target = global_Math.max(prior.target, x.target)
                        }
                    }
                }
            }
            if (truth((review === "reducir"))) {
                if (true) {
                    val iterator33: dynamic = iteratorOf(n.items)
                    while (true) {
                        val next34: dynamic = iterator33.next()
                        if (truth(next34.done)) break
                        var x: dynamic = absent
                        x = next34.value
                        val prior: dynamic = d.items.find((fun(y: dynamic): dynamic {
                            return (y.id === x.id)
                        }))
                        x.target = (if (truth(prior)) global_Math.min(prior.target, x.target) else global_Math.max((if (truth((global_E.unitOf(x) === "sec"))) 10 else 4), global_Math.floor(jsMultiply(x.target, 0.8))))
                    }
                }
            }
            removeKey(global_state.logs, d.date)
            return n
        }))
        c.profile = global_JSON.parse(global_JSON.stringify(global_state.profile))
        removeKey(c, "routinePlanningKey")
        return absent
    }
    global_capacityForm13 = fun(date: dynamic): dynamic {
        val l: dynamic = (if (truth(date)) global_state.logs[date] else null)
        val effort: dynamic = jsOr(onPresent(l) { value70 -> value70.rpe }) { "" }
        val suggested: dynamic = 0
        return ("<section class=\"card after-training\" id=\"capacityFeedback13\"><span class=\"eyebrow\">" + textOf((if (truth(date)) jsAdd("SESIÓN COMPLETADA · ", date) else "ADAPTAR LAS PRÓXIMAS SESIONES")) + "</span><h3>¿Cómo te resultó?</h3><p>Tu registro permanece guardado. La progresión de esta categoría se ajusta desde tu respuesta: 1–4 sube suavemente, 5–7 mantiene y 8–10 reduce. " + textOf((if (truth(date)) "Puedes indicar qué ejercicio no pudiste hacer; el generador revisará su variante al repetir la categoría." else jsAdd(jsAdd("La carga actual es ", global_Math.round(jsMultiply(global_TrainingIntensity.scale(global_state.profile), 100))), " % de la dosis del nivel."))) + "</p><form onsubmit=\"applyCapacity13(event,'" + textOf(jsOr(date) { "" }) + "')\">" + textOf((if (truth(date)) ("<label>Esfuerzo que sentiste<select name=\"effort\" required><option value=\"\">Seleccionar del 1 al 10</option>" + textOf(global_Array.from(jsonValue("{\"length\":10}"), (fun(_: dynamic, i: dynamic): dynamic {
            return ("<option value=\"" + textOf(jsAdd(i, 1)) + "\" " + textOf((if (truth((effort === jsAdd(i, 1)))) "selected" else "")) + ">" + textOf(jsAdd(i, 1)) + " / 10</option>")
        })).join("")) + "</select></label><label class=\"field\">¿Algún ejercicio resultó imposible o demasiado difícil?<textarea name=\"exerciseNote\" maxlength=\"500\" rows=\"3\" placeholder=\"Ej.: No pude hacer flexiones arqueras\">" + textOf(global_esc(jsOr(onPresent(l) { value71 -> value71.note }) { "" })) + "</textarea></label>") else "")) + "<label class=\"field\">Reducción global opcional <output>" + textOf(suggested) + "%</output><input name=\"reduction\" type=\"range\" min=\"0\" max=\"60\" step=\"5\" value=\"" + textOf(suggested) + "\" oninput=\"HunterActions.showPercent(this)\"></label><p class=\"footnote\">Este control reduce la carga GLOBAL solo si lo modificas; el ajuste por categoría funciona automáticamente al guardar tu esfuerzo. Las dosis futuras respetan el mínimo del 40 % del nivel. El historial, las misiones editadas y las rutinas originales se conservan. La revisión semanal por fatiga puede reducir más la dosis.</p><button class=\"btn primary full\" type=\"submit\">" + textOf((if (truth(date)) "Guardar esfuerzo y ajuste" else "Aplicar ajuste")) + "</button></form></section>")
    }
    global_openCapacity13 = fun(arg74: dynamic): dynamic {
        var date: dynamic = absent
        date = definedOr(arg74) { "" }
        global_openCoach()
        global_Dollar(".capacity-card").outerHTML = global_capacityForm13(date)
        return absent
    }
    global_applyCapacity13 = fun(ev: dynamic, date: dynamic): dynamic {
        ev.preventDefault()
        if (truth((!truth(ev.target.reportValidity())))) {
            return absent
        }
        val f: dynamic = jsConstruct(global_FormData, arrayOf<dynamic>(ev.target))
        val reduction: dynamic = global_Number(f["get"]("reduction"))
        if (truth(jsOr(jsOr(jsOr((!truth(global_Number.isFinite(reduction)))) { jsLess(reduction, 0) }) { jsMore(reduction, 60) }) { jsMod(reduction, 5) })) {
            return global_toast("Selecciona una reducción válida.")
        }
        val l: dynamic = (if (truth(date)) global_state.logs[date] else null)
        if (truth(jsAnd(date) { jsOr((!truth(onPresent(l) { value79 -> value79.complete }))) { l.pain } })) {
            return global_toast("No hay una sesión completada para actualizar.")
        }
        val effort: dynamic = global_Number(f["get"]("effort"))
        if (truth(jsAnd(l) { jsOr(jsOr((!truth(global_Number.isInteger(effort)))) { jsLess(effort, 1) }) { jsMore(effort, 10) } })) {
            return absent
        }
        val before: dynamic = global_TrainingIntensity.scale(global_state.profile)
        val next: dynamic = global_Math.max(0.4, jsDivide(global_Math.round(jsMultiply(jsMultiply(before, jsSubtract(1, jsDivide(reduction, 100))), 100)), 100))
        global_state.profile.trainingScale = next
        if (truth(l)) {
            l.rpe = effort
            l.note = global_String(nullableOr(nullableOr(f["get"]("exerciseNote")) { l.note }) { "" }).slice(0, 500)
            l.trainingFeedback = jsObject { obj88 ->
                obj88["reduction"] = reduction
                obj88["before"] = before
                obj88["after"] = next
                obj88["updatedAt"] = jsConstruct(global_Date, js("[]")).toISOString()
            }
        }
        val c: dynamic = global_V13UI.currentCycle()
        global_rebuildPending13(c)
        if (truth(c)) {
            removeKey(c, "intensityVersion13")
        }
        global_currentCycle()
        global_save()
        global_render()
        global_Dollar("#capacityFeedback13").innerHTML = ("<span class=\"eyebrow\">AJUSTE GUARDADO</span><h3>Carga del plan: " + textOf(global_Math.round(jsMultiply(next, 100))) + " %</h3><p>" + textOf((if (truth(reduction)) "Se actualizaron las misiones adaptadas pendientes." else "Tu dosis se conserva.")) + " Los registros ya realizados permanecen guardados.</p><button class=\"btn ghost full\" onclick=\"closeActiveModal()\">Continuar</button>")
        global_toast("Esfuerzo y ajuste guardados.")
        return absent
    }
    global_afterCompletion13 = fun(date: dynamic, wasComplete: dynamic): dynamic {
        val l: dynamic = global_state.logs[date]
        if (truth(jsOr(jsOr(jsOr(jsOr(wasComplete) { (!truth(onPresent(l) { value93 -> value93.complete })) }) { l.pain }) { (!truth(onPresent(l.items) { value94 -> value94.length })) }) { l.feedbackPrompted13 })) {
            return absent
        }
        l.feedbackPrompted13 = true
        global_save()
        global_openCapacity13(date)
        return absent
    }
    global_clearGallery13 = fun(): dynamic {
        if (true) {
            val iterator118: dynamic = iteratorOf(global_galleryActors13.values())
            while (true) {
                val next119: dynamic = iterator118.next()
                if (truth(next119.done)) break
                var actor: dynamic = absent
                actor = next119.value
                actor.destroy()
            }
        }
        global_galleryActors13.clear()
        return absent
    }
    global_sampleCompanion13 = fun(type: dynamic, action: dynamic): dynamic {
        val host: dynamic = global_document.querySelector(("[data-sample-character=\"" + textOf(type) + "\"]"))
        if (truth((!truth(host)))) {
            return absent
        }
        var actor: dynamic = global_galleryActors13["get"](type)
        if (truth(jsOr(jsOr((!truth(actor))) { (actor.host !== host) }) { actor.destroyed })) {
            onPresent(actor) { value122 -> value122.destroy() }
            actor = jsConstruct(global_IgrisCharacter, arrayOf<dynamic>(host, type, jsObject { obj123 ->
                obj123["reducedMotion"] = (onPresent(global_state.profile) { value124 -> value124.animate } === false)
            }))
            global_galleryActors13["set"](type, actor)
        }
        if (truth((action === "enter"))) {
            actor.resume()
            actor["open"]()
        } else {
            if (truth((action === "exit"))) {
                run { actor.close(); absent }
            } else {
                if (truth((actor.state === "hidden"))) {
                    actor["open"]()
                }
                actor.perform(action)
            }
        }
        return absent
    }
    global_academyObserver15 = dynamicValue(null)
    global_V13UI = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["currentCycle"] = global_currentCycle
        obj1["technique"] = global_technique
        obj1["openCoach"] = global_openCoach
        obj1["openAcademy"] = global_openAcademy
        obj1["render"] = global_render
        obj1["validateBackup"] = global_validateBackup
        obj1["answerCoach"] = global_answerCoach
        obj1["finish"] = global_finish
        obj1["confirmQuickFinish"] = global_confirmQuickFinish
        obj1["finishGuide11"] = global_finishGuide11
        obj1["toggleMissionItem"] = global_toggleMissionItem
        obj1["toggleMissionTask"] = global_toggleMissionTask
    }
    if (true) {
        val iterator2: dynamic = iteratorOf(global_SourceSequences13)
        while (true) {
            val next3: dynamic = iterator2.next()
            if (truth(next3.done)) break
            var source: dynamic = absent
            source = next3.value
            if (truth(jsAnd((!truth(global_state.creatorRoutines.some((fun(r: dynamic): dynamic {
                return (r.videoId === source.videoId)
            }))))) { global_CreatorFeed.validateRoutine(source) })) {
                global_state.creatorRoutines.push(global_JSON.parse(global_JSON.stringify(source)))
            }
        }
    }
    if (truth(global_state.profile)) {
        global_state.profile.trainingScale = global_TrainingIntensity.scale(global_state.profile)
    }
    global_MotionPlayback["set"]((if (truth(global_MotionPlayback.rates.some((fun(r: dynamic): dynamic {
        return (r["value"] === global_state.demoSpeed)
    })))) global_state.demoSpeed else 1))
    global_profileForm = (fun(arg11: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg11) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V13UI.profileForm(initial)
        val block: dynamic = global_document.createElement("section")
        block.className = "card profile-block intensity-block"
        block.innerHTML = ("<span class=\"eyebrow\">EXIGENCIA Y RECUPERACIÓN</span><h3>Un plan más retador.</h3><p>Selección por nivel: básico prioriza movimientos accesibles; intermedio combina aproximadamente 3 básicos y 2 intermedios por cada 5 ejercicios; avanzado combina 1 básico, 2 intermedios y 2 avanzados, según disponibilidad y restricciones. El historial de cada categoría puede ajustar la dificultad de la siguiente sesión.</p>" + textOf(global_loadControl13(global_TrainingIntensity.scale(jsOr(global_state.profile) { js("({})") }))) + "<p class=\"footnote\">100 % es la dosis de diseño de tu nivel, no esfuerzo máximo obligatorio. El plan cambia según objetivos, lesiones y registros. Puedes reducir hasta el 40 % de las repeticiones o del tiempo. Las cantidades de rutinas originales permanecen intactas.</p></section>")
        onPresent(box.querySelector(".training-mode-block")) { value18 -> value18.after(block) }
        return box.innerHTML
    })
    global_currentCycle = (fun(): dynamic {
        val c: dynamic = global_V13UI.currentCycle()
        if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
            return c
        }
        val key: dynamic = global_JSON.stringify(arrayOf<dynamic>(global_TrainingIntensity.scale(global_state.profile), global_state.profile.difficulty, global_state.profile.trainingMode))
        if (truth((c.intensityVersion13 !== key))) {
            global_rebuildPending13(c, (c.intensityVersion13 === absent))
            c.intensityVersion13 = key
            c.intensityVersion = 11
            val assigned: dynamic = global_RoutinePlanner.organize(c, global_state.profile, global_state.creatorRoutines, global_allRoutines10(), global_state.logs, global_E.dateKey())
            c.routineAssignments = assigned
            c.routinePlanningKey = global_JSON.stringify(arrayOf<dynamic>(global_state.profile.trainingMode, global_state.profile.preferredTrainer, global_state.profile.finalTarget, global_state.profile.difficulty, global_state.profile.injuries, global_state.profile.restrictions, global_state.profile.health, global_state.creatorRoutines.map((fun(r: dynamic): dynamic {
                return r.videoId
            }))))
            global_save()
        }
        return c
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val before: dynamic = global_state.profile
        global_V13UI.submitProfile(ev)
        if (truth(jsAnd(global_state.profile) { (global_state.profile !== before) })) {
            global_currentCycle()
            global_save()
            global_render()
        }
        return absent
    })
    global_technique = (fun(id: dynamic): dynamic {
        global_V13UI.technique(id)
        val d: dynamic = global_Dollar("#modal")
        d.querySelectorAll(".demo-card").forEach((fun(x: dynamic): dynamic {
            return x.remove()
        }))
        d.querySelector(".tech-tags").insertAdjacentHTML("afterend", global_vectorPanel(global_E.byId(id)))
        global_startVector(id)
        return absent
    })
    global_openAcademy = (fun(): dynamic {
        global_V13UI.openAcademy()
        val gallery: dynamic = global_Dollar(".demo-gallery")
        if (truth((!truth(gallery)))) {
            return absent
        }
        gallery.innerHTML = global_E.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).map((fun(e: dynamic): dynamic {
            return ("<button class=\"demo-thumb\" onclick=\"technique('" + textOf(e.id) + "')\"><canvas width=\"180\" height=\"200\" data-exercise=\"" + textOf(e.id) + "\" aria-hidden=\"true\"></canvas><b>" + textOf(global_esc(e.name)) + "</b></button>")
        })).join("")
        onPresent(global_academyObserver15) { value57 -> value57.disconnect() }
        val canvases: dynamic = jsArray { array58 ->
            appendAll(array58, gallery.querySelectorAll("canvas"))
        }
        val paint: dynamic = (fun(c: dynamic): dynamic {
            if (truth((c.dataset.ready === "true"))) {
                return absent
            }
            c.dataset.thumbnail = "true"
            global_drawVector(c, c.dataset.exercise, 12)
            c.dataset.ready = "true"
            global_Mesh3D.release(c)
            return absent
        })
        canvases.slice(0, 12).forEach(paint)
        if (truth((js("typeof IntersectionObserver") === "function"))) {
            global_academyObserver15 = jsConstruct(global_IntersectionObserver, arrayOf<dynamic>((fun(entries: dynamic): dynamic {
                if (true) {
                    val iterator63: dynamic = iteratorOf(entries)
                    while (true) {
                        val next64: dynamic = iterator63.next()
                        if (truth(next64.done)) break
                        var entry: dynamic = absent
                        entry = next64.value
                        if (truth(entry.isIntersecting)) {
                            paint(entry.target)
                            global_academyObserver15.unobserve(entry.target)
                        }
                    }
                }
                return absent
            }), jsObject { obj65 ->
                obj65["root"] = global_Dollar("#modal")
                obj65["rootMargin"] = "120px"
            }))
            canvases.slice(12).forEach((fun(c: dynamic): dynamic {
                return global_academyObserver15.observe(c)
            }))
        } else {
            canvases.slice(12).forEach(paint)
        }
        return absent
    })
    global_openCoach = (fun(): dynamic {
        global_V13UI.openCoach()
        global_Dollar(".coach-intro").insertAdjacentHTML("afterend", ("" + textOf(global_MotionPlayback.controls("personaje")) + "<section class=\"card capacity-card\"><span class=\"eyebrow\">CARGA PERSONAL</span><h3>" + textOf(global_Math.round(jsMultiply(global_TrainingIntensity.scale(global_state.profile), 100))) + " % de la dosis de tu nivel</h3><p>Tu compañero revisa el esfuerzo y las restricciones. Puedes reducir la próxima sesión sin cambiar tus registros.</p><button class=\"btn ghost full\" onclick=\"openCapacity13()\">Ajustar las próximas misiones</button></section>"))
        return absent
    })
    global_finish = (fun(): dynamic {
        val date: dynamic = global_E.dateKey()
        val was: dynamic = (!truth((!truth(onPresent(global_state.logs[date]) { value99 -> value99.complete }))))
        global_V13UI.finish()
        global_afterCompletion13(date, was)
        return absent
    })
    global_confirmQuickFinish = (fun(): dynamic {
        val date: dynamic = global_E.dateKey()
        val was: dynamic = (!truth((!truth(onPresent(global_state.logs[date]) { value102 -> value102.complete }))))
        global_V13UI.confirmQuickFinish()
        global_afterCompletion13(date, was)
        return absent
    })
    global_finishGuide11 = (fun(ev: dynamic): dynamic {
        val date: dynamic = jsOr(onPresent(global_guide11) { value105 -> value105.day.date }) { global_E.dateKey() }
        val was: dynamic = (!truth((!truth(onPresent(global_state.logs[date]) { value107 -> value107.complete }))))
        global_V13UI.finishGuide11(ev)
        global_afterCompletion13(date, was)
        return absent
    })
    global_toggleMissionItem = (fun(date: dynamic, i: dynamic, checked: dynamic): dynamic {
        val was: dynamic = (!truth((!truth(onPresent(global_state.logs[date]) { value110 -> value110.complete }))))
        global_V13UI.toggleMissionItem(date, i, checked)
        global_afterCompletion13(date, was)
        return absent
    })
    global_toggleMissionTask = (fun(date: dynamic, i: dynamic, checked: dynamic): dynamic {
        val was: dynamic = (!truth((!truth(onPresent(global_state.logs[date]) { value113 -> value113.complete }))))
        global_V13UI.toggleMissionTask(date, i, checked)
        global_afterCompletion13(date, was)
        return absent
    })
    global_galleryActors13 = jsConstruct(global_Map, js("[]"))
    global_modal13 = global_modal
    global_modal = (fun(html: dynamic): dynamic {
        global_clearGallery13()
        global_modal13(html)
        return absent
    })
    global_openAnimationGallery = (fun(): dynamic {
        global_clearGallery13()
        global_modal(("<section data-companion-static=\"true\" class=\"depth-gallery\"><h2>Cinco compañeros · volumen 2.5D</h2><p>Entrada, espera continua y salida. Solo cambias tu compañero desde Perfil.</p>" + textOf(global_MotionPlayback.controls("personajes")) + "" + textOf(global_Object.entries(global_CompanionNames).map((fun(arg129: dynamic): dynamic {
            var key: dynamic = absent
            var name: dynamic = absent
            val unpacked130: dynamic = fromIterable(arg129)
            key = unpacked130[0]
            name = unpacked130[1]
            return ("<details class=\"card\" ontoggle=\"HunterActions.toggleCompanion(this,'" + textOf(key) + "')\"><summary>" + textOf(name) + "</summary><div class=\"depth-character-host\" data-sample-character=\"" + textOf(key) + "\"></div><div class=\"row wrap\"><button class=\"btn\" onclick=\"sampleCompanion13('" + textOf(key) + "','enter')\">Entrada</button><button class=\"btn\" onclick=\"sampleCompanion13('" + textOf(key) + "','wave')\">Saludo</button><button class=\"btn\" onclick=\"sampleCompanion13('" + textOf(key) + "','celebrate')\">Celebrar</button><button class=\"btn\" onclick=\"sampleCompanion13('" + textOf(key) + "','exit')\">Salida</button></div></details>")
        })).join("")) + "</section>"))
        return absent
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth(global_Dollar("#modal")["open"])) {
            return absent
        }
        global_clearGallery13()
        if (truth(global_vectorSession)) {
            global_vectorSession.playing = false
        }
        run {
            val previous133: dynamic = numeric(global_vectorGeneration13)
            global_vectorGeneration13 = jsAdd(previous133, 1)
        }
        return absent
    }))
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("velocidad|animaci[oó]n|3d|2\\.5d", "i")).test(q))) {
            return ("" + textOf(global_companionDisplayName()) + ": Todas las fichas y guías tienen una demostración local con volumen ilustrado 2.5D. Elige Lenta (0,5×), Normal (1×) o Rápida (1,5×). La velocidad no cambia el cronómetro ni las cantidades de tu sesión.")
        }
        if (truth(dynamicValue(jsRegExp("reducir|menos intensidad|bajar la carga", "i")).test(q))) {
            return ("" + textOf(global_companionDisplayName()) + ": Tu carga actual es " + textOf(global_Math.round(jsMultiply(global_TrainingIntensity.scale(global_state.profile), 100))) + " % de la dosis de " + textOf(global_state.profile.difficulty) + ". Usa «Ajustar las próximas misiones» o el ajuste al finalizar. El esfuerzo alto y la fatiga también se revisan cada semana; las lesiones tienen prioridad.")
        }
        return global_V13UI.answerCoach(q)
    })
    global_validateBackup = (fun(`data`: dynamic): dynamic {
        if (truth((!truth(global_V13UI.validateBackup(`data`))))) {
            return false
        }
        if (truth(jsAnd((`data`.demoSpeed !== absent)) { (!truth(global_MotionPlayback.rates.some((fun(r: dynamic): dynamic {
            return (r["value"] === `data`.demoSpeed)
        })))) })) {
            return false
        }
        if (truth(dynamicValue(jsArray { array141 ->
            array141.push(`data`.profile)
            appendAll(array141, `data`.cycles.map((fun(c: dynamic): dynamic {
                return c.profile
            })))
        }).filter(global_Boolean).some((fun(p: dynamic): dynamic {
            return jsAnd((p.trainingScale !== absent)) { jsOr(jsOr((!truth(global_Number.isFinite(p.trainingScale)))) { jsLess(p.trainingScale, 0.4) }) { jsMore(p.trainingScale, 1) } }
        })))) {
            return false
        }
        return global_Object.values(`data`.logs).every((fun(l: dynamic): dynamic {
            return jsOr((l.trainingFeedback === absent)) { jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(l.trainingFeedback) { global_Number.isInteger(l.trainingFeedback.reduction) }) { jsAtLeast(l.trainingFeedback.reduction, 0) }) { jsAtMost(l.trainingFeedback.reduction, 60) }) { dynamicValue(jsonValue("[\"before\",\"after\"]")).every((fun(k: dynamic): dynamic {
                return jsAnd(jsAnd(global_Number.isFinite(l.trainingFeedback[k])) { jsAtLeast(l.trainingFeedback[k], 0.4) }) { jsAtMost(l.trainingFeedback[k], 1) }
            })) }) { (jsTypeOf(l.trainingFeedback.updatedAt) === "string") }) { global_Number.isFinite(global_Date.parse(l.trainingFeedback.updatedAt)) } }
        }))
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V13UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(el: dynamic): dynamic {
            return run {
                val target144: dynamic = el
                val assigned146: dynamic = "EL DESPERTAR · V13"
                target144.textContent = assigned146
                assigned146
            }
        }))
        return absent
    })
    global_state.appVersion = 13
    global_save()
    global_currentCycle()
    global_render()
    global_syncNative()
}
