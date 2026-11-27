@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv19

import hunter.browser.*

@JsName("V19UI") private external var global_V19UI: dynamic
@JsName("levelHint19") private external var global_levelHint19: dynamic
@JsName("jumpGuide19") private external var global_jumpGuide19: dynamic
@JsName("storeGuideCheck19") private external var global_storeGuideCheck19: dynamic
@JsName("guideCheck19") private external var global_guideCheck19: dynamic
@JsName("pendingGuide19") private external var global_pendingGuide19: dynamic
@JsName("expand19") private external var global_expand19: dynamic
@JsName("galleryCompanion19") private external var global_galleryCompanion19: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("TrainingProgression19") private external var global_TrainingProgression19: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("RoutinePlanner") private external var global_RoutinePlanner: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("HunterGuided") private external var global_HunterGuided: dynamic
@JsName("moveGuide11") private external var global_moveGuide11: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("advanceGuide11") private external var global_advanceGuide11: dynamic
@JsName("confirmGuideSet11") private external var global_confirmGuideSet11: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("RoutineSequence") private external var global_RoutineSequence: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("openAnimationGallery") private external var global_openAnimationGallery: dynamic
@JsName("clearGallery13") private external var global_clearGallery13: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("CompanionNames") private external var global_CompanionNames: dynamic
@JsName("galleryActors13") private external var global_galleryActors13: dynamic
@JsName("IgrisCharacter") private external var global_IgrisCharacter: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("todayDay") private external var global_todayDay: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic

internal fun installV19() {
    global_levelHint19 = fun(form: dynamic): dynamic {
        val n: dynamic = global_TrainingProgression19.level(jsObject { obj4 ->
            obj4["difficulty"] = form.elements.difficulty["value"]
        })
        val node: dynamic = form.querySelector("#levelHint19")
        if (truth(node)) {
            node.textContent = dynamicValue(jsonValue("[\"Básico: sentadillas, flexiones de apoyo y patrones bilaterales. Con experiencia, cuatro series de fuerza.\",\"Intermedio: flexión de suelo, pausas, cadencia lenta y mayor control unilateral.\",\"Avanzado: flexiones arquero o pica, trabajo a una pierna y repeticiones de un ciclo y medio. Revisa los requisitos de cada movimiento.\"]"))[n]
        }
        if (truth((form.dataset.restTouched19 !== "true"))) {
            form.elements.restSeconds["value"] = global_String(dynamicValue(jsonValue("[30,60,90]"))[n])
        }
        return absent
    }
    global_jumpGuide19 = fun(index: dynamic): dynamic {
        return global_HunterGuided.jump(index, true)
    }
    global_storeGuideCheck19 = fun(form: dynamic, completed: dynamic): dynamic {
        return global_HunterGuided.storeSet(form, completed)
    }
    global_guideCheck19 = fun(input: dynamic): dynamic {
        return global_HunterGuided.check(input)
    }
    global_pendingGuide19 = fun(): dynamic {
        return global_HunterGuided.pending(false)
    }
    global_galleryCompanion19 = fun(details: dynamic): dynamic {
        val host: dynamic = details.querySelector("[data-sample-character]")
        val key: dynamic = host.dataset.sampleCharacter
        if (truth((!truth(details["open"])))) {
            onPresent(global_galleryActors13["get"](key)) { value139 -> value139.pause() }
            return absent
        }
        var actor: dynamic = global_galleryActors13["get"](key)
        if (truth((!truth(actor)))) {
            actor = jsConstruct(global_IgrisCharacter, arrayOf<dynamic>(host, key, jsObject { obj140 ->
                obj140["automatic"] = true
                obj140["reducedMotion"] = jsOr((onPresent(global_state.profile) { value141 -> value141.animate } === false)) { global_matchMedia("(prefers-reduced-motion: reduce)").matches }
            }))
            global_galleryActors13["set"](key, actor)
            actor["open"]()
        } else {
            actor.resume()
        }
        return absent
    }
    global_V19UI = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["paintGuide11"] = global_paintGuide11
        obj1["openCoach"] = global_openCoach
        obj1["render"] = global_render
        obj1["answerCoach"] = global_answerCoach
        obj1["allRoutines"] = global_allRoutines
    }
    global_profileForm = (fun(arg11: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg11) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V19UI.profileForm(initial)
        val f: dynamic = box.querySelector("form")
        val p: dynamic = jsOr(global_state.profile) { js("({})") }
        val rest: dynamic = f.elements.restSeconds
        rest.innerHTML = global_options(jsonValue("{\"30\":\"30 segundos\",\"60\":\"60 segundos\",\"90\":\"90 segundos · máximo\"}"), global_String(global_TrainingProgression19.rest(jsObject { obj16 ->
            copyFields(obj16, p)
            obj16["difficulty"] = f.elements.difficulty["value"]
        })))
        rest.setAttribute("onchange", "this.form.dataset.restTouched19=\"true\"")
        val level: dynamic = f.elements.difficulty
        level.setAttribute("onchange", "levelHint19(this.form)")
        val hint: dynamic = global_document.createElement("p")
        hint.id = "levelHint19"
        hint.className = "notice level-hint19"
        level.closest("label").after(hint)
        val intensity: dynamic = f.querySelector(".intensity-block")
        intensity.querySelector("p").textContent = "Los niveles cambian movimientos, apoyos y cadencia. La revisión semanal aumenta una variable y, al completar su rango, propone una variante compatible. La experiencia inicial, el esfuerzo y las restricciones ajustan la dosis."
        intensity.querySelector(".footnote").textContent = "Si estás empezando, la calibración utiliza dos series y menos repeticiones. Las lesiones y recomendaciones recibidas conservan prioridad sobre la dificultad. Descansos disponibles: 30, 60 y 90 segundos."
        val picker: dynamic = f.querySelector(".companion-picker-block")
        if (truth(picker)) {
            picker.querySelectorAll("[name=companionCharacter]").forEach((fun(input: dynamic): dynamic {
                return input.setAttribute("onchange", "previewCompanionChoice(this)")
            }))
            if (truth(jsAnd(initial) { (picker.tagName === "DETAILS") })) {
                picker["open"] = true
            }
        }
        f.dataset.restTouched19 = (if (truth((p.restSeconds !== absent))) "true" else "false")
        global_levelHint19(f)
        return box.innerHTML
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val f: dynamic = ev.target
        val n: dynamic = global_Number(f.elements.restSeconds["value"])
        if (truth((!truth(dynamicValue(jsonValue("[30,60,90]")).includes(n))))) {
            ev.preventDefault()
            return global_toast("Elige un descanso de 30, 60 o 90 segundos.")
        }
        global_V19UI.submitProfile(ev)
        return absent
    })
    global_allRoutines = (fun(): dynamic {
        return jsArray { array33 ->
            appendAll(array33, global_OwnRoutines18.routines.map((fun(r: dynamic): dynamic {
                val p: dynamic = jsObject { obj34 ->
                    copyFields(obj34, global_state.profile)
                    obj34["difficulty"] = global_OwnRoutines18.keys[r.level]
                    obj34["restSeconds"] = (if (truth((r.level === global_TrainingProgression19.level(jsOr(global_state.profile) { js("({})") })))) onPresent(global_state.profile) { value35 -> value35.restSeconds } else dynamicValue(jsonValue("[30,60,90]"))[r.level])
                }
                val items: dynamic = (if (truth(global_state.profile)) global_OwnRoutines18.adjust(r, p) else r.items)
                return jsObject { obj36 ->
                    copyFields(obj36, r)
                    obj36["items"] = items
                    obj36["minutes"] = global_OwnRoutines18.estimate(items)
                }
            })))
            appendAll(array33, global_state.customRoutines.map((fun(r: dynamic): dynamic {
                val items: dynamic = r.items.map((fun(x: dynamic): dynamic {
                    return jsObject { obj37 ->
                        copyFields(obj37, x)
                        obj37["rest"] = global_Math.min(90, global_Math.max(30, jsOr(global_Number(x.rest)) { 30 }))
                    }
                }))
                return jsObject { obj38 ->
                    copyFields(obj38, r)
                    obj38["items"] = items
                }
            })))
        }
    })
    global_currentCycle = (fun(): dynamic {
        if (truth(jsOr((!truth(global_state.profile))) { (!truth(global_E.eligible(global_state.profile))) })) {
            return null
        }
        val c: dynamic = global_Weekly.current(global_state)
        val key: dynamic = global_JSON.stringify(arrayOf<dynamic>(19, global_Object.fromEntries(global_Object.entries(global_state.profile).filter((fun(arg41: dynamic): dynamic {
            var k: dynamic = absent
            val unpacked42: dynamic = fromIterable(arg41)
            k = unpacked42[0]
            return (k !== "blockMode22")
        })))))
        val ap: dynamic = global_GoalPlans.adjusted(global_state.profile)
        val now: dynamic = global_E.dateKey()
        if (truth((c.planningKey19 === key))) {
            return c
        }
        val review: dynamic = onPresent(c.reviews) { value43 -> onPresent(value43.at((-numeric(1)))) { value44 -> value44.action } }
        val hasOld: dynamic = jsOr((c.engineVersion19 !== 19)) { (c.planningKey19 !== absent) }
        if (truth((review !== "pausar"))) {
            val fresh: dynamic = (if (truth(hasOld)) global_E.makeCycle(global_state.profile, c.start, c.index, null, global_state.logs) else null)
            c.days = c.days.map((fun(d: dynamic, i: dynamic): dynamic {
                if (truth(jsOr(jsOr(jsLess(d.date, now)) { global_Weekly.active(global_state.logs[d.date]) }) { onPresent(d.tasks) { value47 -> value47.some((fun(t: dynamic): dynamic {
                    return t.done
                })) } })) {
                    return d
                }
                val incompatible: dynamic = jsAnd(d.items.length) { (!truth(global_Adapt.routineCompatible(jsObject { obj48 ->
                    obj48["items"] = d.items
                }, ap))) }
                val generated: dynamic = jsOr(jsOr(jsAnd((!truth(d.customized))) { (!truth(d.routineId)) }) { d.sourceSequence }) { d.creatorAssigned }
                val next: dynamic = (if (truth(jsOr(jsAnd(fresh) { generated }) { incompatible })) dynamicValue(jsOr(fresh) { global_E.makeCycle(global_state.profile, c.start, c.index, null, global_state.logs) }).days[i] else d)
                if (truth(jsAnd((next !== d)) { (review === "reducir") })) {
                    next.items.forEach((fun(x: dynamic): dynamic {
                        return run {
                            val target49: dynamic = x
                            val assigned51: dynamic = global_Math.max((if (truth((global_E.unitOf(x) === "sec"))) 10 else 2), global_Math.floor(jsMultiply(x.target, 0.8)))
                            target49.target = assigned51
                            assigned51
                        }
                    }))
                }
                if (truth(jsOr((next !== d)) { (c.engineVersion19 !== 19) })) {
                    next.items.forEach((fun(x: dynamic): dynamic {
                        return run {
                            val target52: dynamic = x
                            val assigned54: dynamic = global_TrainingProgression19.rest(ap)
                            target52.rest = assigned54
                            assigned54
                        }
                    }))
                }
                return next
            }))
            global_RoutinePlanner.organize(c, global_state.profile, js("[]"), global_OwnRoutines18.routines, global_state.logs, now)
        }
        c.engineVersion19 = 19
        c.profile = global_JSON.parse(global_JSON.stringify(global_state.profile))
        c.planningKey19 = key
        global_save()
        return c
    })
    global_moveGuide11 = (fun(delta: dynamic): dynamic {
        if (truth(global_guide11)) {
            global_jumpGuide19(jsAdd(global_guide11.index, delta))
        }
        return absent
    })
    global_advanceGuide11 = (fun(): dynamic {
        global_moveGuide11(1)
        return absent
    })
    global_confirmGuideSet11 = (fun(ev: dynamic): dynamic {
        return global_HunterGuided.confirm(ev)
    })
    global_paintGuide11 = (fun(): dynamic {
        global_V19UI.paintGuide11()
        val g: dynamic = global_guide11
        val s: dynamic = onPresent(g) { value77 -> value77.steps[g.index] }
        val f: dynamic = global_Dollar("#guideRecord")
        if (truth((!truth(g)))) {
            return absent
        }
        if (truth(jsAnd(f) { (onPresent(s) { value79 -> value79.kind } === "exercise") })) {
            val e: dynamic = global_E.byId(s.id)
            val a: dynamic = onPresent(global_state.logs[g.day.date]) { value80 -> onPresent(value80.items[s.itemIndex]) { value82 -> value82["actual"][s.setIndex] } }
            f.noValidate = true
            f.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<label class=\"guide-check19\"><input name=\"completed\" type=\"checkbox\" ", (if (truth(onPresent(a) { value88 -> value88.done })) "checked" else "")), " onchange=\"guideCheck19(this)\"><span><b>Realicé esta serie</b><small>Solo esta casilla registra la serie como completada.</small></span></label>"), "<div class=\"fields\"><label>"), (if (truth((s.unit === "sec"))) "Segundos realizados" else "Repeticiones realizadas")), "<input name=\"value\" type=\"number\" min=\"1\" max=\""), (if (truth((s.unit === "sec"))) 3600 else 300)), "\" step=\"1\" required value=\""), (if (truth(onPresent(a) { value89 -> value89.done })) a["value"] else s.target)), "\"></label>"), "<label>Carga simultánea · kg<input name=\"kg\" type=\"number\" min=\"0\" max=\"200\" step=\"0.5\" value=\""), jsOr(onPresent(a) { value90 -> value90.kg }) { 0 }), "\" "), (if (truth((!truth(dynamicValue(jsonValue("[\"db\",\"gym\"]")).includes(e.equipment))))) "readonly" else "")), "></label></div>"), "<p id=\"guideCheckStatus19\" class=\"footnote\" aria-live=\"polite\">"), (if (truth(onPresent(a) { value91 -> value91.done })) "Serie marcada como realizada." else "Serie pendiente.")), "</p><button type=\"submit\" class=\"btn primary full\">Guardar y continuar</button>")
            val pending: dynamic = global_Array.from(global_Dollar("#guidedSlot").querySelectorAll("button")).find((fun(b: dynamic): dynamic {
                return (b.getAttribute("onclick") === "advanceGuide11()")
            }))
            if (truth(pending)) {
                pending.setAttribute("onclick", "pendingGuide19()")
                pending.textContent = "Dejar pendiente y siguiente serie →"
            }
        }
        onPresent(global_Dollar("#guidedSlot")) { value94 -> value94.querySelectorAll("button:not([type])").forEach((fun(b: dynamic): dynamic {
            return run {
                val target96: dynamic = b
                val assigned98: dynamic = "button"
                target96.type = assigned98
                assigned98
            }
        })) }
        if (truth((onPresent(s) { value99 -> value99.kind } === "rest"))) {
            s.label = jsAdd(jsAdd("Descanso · ", s.seconds), " segundos")
        }
        if (truth(jsAnd((!truth(s))) { g.record })) {
            val pending: dynamic = g.steps.map((fun(step: dynamic, index: dynamic): dynamic {
                return jsObject { obj103 ->
                    obj103["step"] = step
                    obj103["index"] = index
                }
            })).filter((fun(arg104: dynamic): dynamic {
                var step: dynamic = absent
                val unpacked105: dynamic = arg104
                step = unpacked105["step"]
                return jsAnd((step.kind === "exercise")) { (!truth(onPresent(global_state.logs[g.day.date]) { value106 -> onPresent(value106.items[step.itemIndex]) { value108 -> onPresent(value108["actual"][step.setIndex]) { value110 -> value110.done } } })) }
            }))
            if (truth(pending.length)) {
                global_Dollar("#guidedSlot .card").insertAdjacentHTML("beforeend", jsAdd(jsAdd(jsAdd(jsAdd("<section class=\"pending-review19\"><h4>", pending.length), " series pendientes</h4>"), pending.map((fun(arg113: dynamic): dynamic {
                    var step: dynamic = absent
                    var index: dynamic = absent
                    val unpacked114: dynamic = arg113
                    step = unpacked114["step"]
                    index = unpacked114["index"]
                    return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<button type=\"button\" class=\"btn ghost full\" onclick=\"jumpGuide19(", index), ")\">"), global_esc(step.label)), " · serie "), jsAdd(step.setIndex, 1)), " →</button>")
                })).join("")), "</section>"))
            }
        }
        if (truth(jsAnd(global_Dollar("#guideClockMessage")) { jsAtMost(g.remaining, 0) })) {
            global_Dollar("#guideClockMessage").textContent = "Puedes continuar o repetir el reloj. El recorrido no marca ejercicios como realizados."
        }
        global_ModalViewport.reset()
        return absent
    })
    global_expand19 = global_RoutineSequence.expand
    global_RoutineSequence.expand = (fun(day: dynamic): dynamic {
        val steps: dynamic = global_expand19(day)
        if (true) {
            val iterator121: dynamic = iteratorOf(steps)
            while (true) {
                val next122: dynamic = iterator121.next()
                if (truth(next122.done)) break
                var s: dynamic = absent
                s = next122.value
                if (truth((s.kind === "rest"))) {
                    s.seconds = global_Math.min(90, global_Math.max(30, jsOr(global_Number(s.seconds)) { 30 }))
                    s.label = jsAdd(jsAdd("Descanso · ", s.seconds), " segundos")
                }
            }
        }
        return steps
    })
    global_openCoach = (fun(): dynamic {
        global_V19UI.openCoach()
        global_Dollar("#modal").querySelectorAll(".companion-actions,.motion-controls,button[onclick=\"openAnimationGallery()\"]").forEach((fun(node: dynamic): dynamic {
            return node.remove()
        }))
        onPresent(global_Dollar("#animatedCompanion")) { value129 -> value129.setAttribute("aria-label", jsAdd(global_companionDisplayName(), " · compañero 2D con gestos automáticos")) }
        onPresent(global_Dollar(".coach-intro")) { value130 -> value130.insertAdjacentHTML("afterend", "<p class=\"footnote automatic-note19\">Tu compañero saluda, observa y cambia de gesto automáticamente durante la espera.</p>") }
        val learning: dynamic = global_Dollar("#modal .learning-card")
        if (truth(learning)) {
            learning.insertAdjacentHTML("beforeend", "<p><b>Escalones de movimiento:</b> al completar el rango de un ejercicio, tres registros comparables y un esfuerzo de hasta 7/10 permiten revisar una variante del mismo nivel. Dolor, fatiga, indicaciones y reducción personal tienen prioridad.</p>")
        }
        return absent
    })
    global_openAnimationGallery = (fun(): dynamic {
        global_clearGallery13()
        global_modal(jsAdd(jsAdd("<section data-companion-static=\"true\"><h2>Cinco compañeros · animaciones automáticas</h2><p>Entrada y gestos de espera. El compañero del perfil se conserva.</p>", global_Object.entries(global_CompanionNames).map((fun(arg135: dynamic): dynamic {
            var key: dynamic = absent
            var name: dynamic = absent
            val unpacked136: dynamic = fromIterable(arg135)
            key = unpacked136[0]
            name = unpacked136[1]
            return jsAdd(jsAdd(jsAdd(jsAdd("<details class=\"card\" ontoggle=\"galleryCompanion19(this)\"><summary>", name), "</summary><div class=\"depth-character-host\" data-sample-character=\""), key), "\"></div></details>")
        })).join("")), "</section>"))
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(jsAnd(dynamicValue(jsRegExp("progre|nivel|dif[ií]cil|exig|evolu|series|repetici", "i")).test(q)) { (!truth(dynamicValue(jsRegExp("dolor|lesi[oó]n", "i")).test(q))) })) {
            val d: dynamic = global_todayDay()
            val review: dynamic = onPresent(global_currentCycle()) { value144 -> onPresent(value144.reviews) { value145 -> value145.at((-numeric(1))) } }
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(global_companionDisplayName(), ": Tu nivel "), global_OwnRoutines18.names[global_TrainingProgression19.level(global_state.profile)]), " cambia las variantes del movimiento, además de series y repeticiones. "), jsOr(onPresent(review) { value146 -> value146.reason }) { "Reúne tres sesiones completas y comparables con esfuerzo de hasta 7/10 para revisar aumentos semanales." }), " Al alcanzar el rango, se busca una siguiente variante compatible dentro de tu nivel, recalibrando su dosis. Tu sesión incluye: "), jsOr(onPresent(d) { value147 -> value147.items.map((fun(x: dynamic): dynamic {
                return global_E.byId(x.id).name
            })).join(", ") }) { "descanso" }), ".")
        }
        return global_V19UI.answerCoach(q)
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V19UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(node: dynamic): dynamic {
            return run {
                val target152: dynamic = node
                val assigned154: dynamic = "EL DESPERTAR · V19"
                target152.textContent = assigned154
                assigned154
            }
        }))
        return absent
    })
    if (truth(jsAnd(global_state.profile) { (global_state.restVersion19 !== 19) })) {
        global_state.profile.restSeconds = dynamicValue(jsonValue("[30,60,90]"))[global_TrainingProgression19.level(global_state.profile)]
    }
    global_state.restVersion19 = 19
    global_state.appVersion = 19
    global_currentCycle()
    global_save()
    global_render()
    global_syncNative()
}
