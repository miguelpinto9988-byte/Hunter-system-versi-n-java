@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv5

import hunter.browser.*

@JsName("V4") private external var global_V4: dynamic
@JsName("render5") private external var global_render5: dynamic
@JsName("demoPlaying") private external var global_demoPlaying: dynamic
@JsName("demoIndex") private external var global_demoIndex: dynamic
@JsName("toggleDemo") private external var global_toggleDemo: dynamic
@JsName("demoStep") private external var global_demoStep: dynamic
@JsName("sourceLink") private external var global_sourceLink: dynamic
@JsName("openAcademy") private external var global_openAcademy: dynamic
@JsName("oldOpenCoach") private external var global_oldOpenCoach: dynamic
@JsName("motionTimeout") private external var global_motionTimeout: dynamic
@JsName("coachMotion") private external var global_coachMotion: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("book") private external var global_book: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("gesture") private external var global_gesture: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("DemoLibrary") private external var global_DemoLibrary: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("TrainingGuides") private external var global_TrainingGuides: dynamic
@JsName("TrainingSources") private external var global_TrainingSources: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("clearTimeout") private external var global_clearTimeout: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic

internal fun installV5() {
    global_toggleDemo = fun(`file`: dynamic): dynamic {
        val img: dynamic = global_Dollar("#exerciseDemo")
        global_demoPlaying = (!truth(img.src.endsWith(jsAdd(`file`, ".webp"))))
        img.src = jsAdd(jsAdd(jsAdd("media/", `file`), (if (truth(global_demoPlaying)) "" else "-poster")), ".webp")
        global_Dollar("#demoToggle").textContent = (if (truth(global_demoPlaying)) "Ⅱ Pausar" else "▶ Reproducir")
        return absent
    }
    global_demoStep = fun(`file`: dynamic): dynamic {
        global_demoIndex = jsMod(jsAdd(global_demoIndex, 1), 3)
        global_Dollar("#exerciseDemo").src = ("media/" + textOf(`file`) + "-step" + textOf(global_demoIndex) + ".webp")
        global_Dollar("#demoToggle").textContent = "▶ Reproducir"
        return absent
    }
    global_sourceLink = fun(key: dynamic): dynamic {
        val s: dynamic = global_TrainingSources[key]
        return (if (truth(s)) ("<a href=\"" + textOf(s.url) + "\" target=\"_blank\" rel=\"noopener\">" + textOf(global_esc(s.name)) + " ↗</a>") else "")
    }
    global_openAcademy = fun(): dynamic {
        global_modal(("<span class=\"eyebrow\">ACADEMIA DEL CAZADOR</span><h2>Aprende a entrenar.</h2><p class=\"muted\">Guías y demostraciones disponibles sin conexión. Los enlaces de consulta se abren con Internet.</p><div class=\"academy-grid\">" + textOf(global_TrainingGuides.map((fun(g: dynamic): dynamic {
            return ("<details class=\"card\"><summary>" + textOf(global_esc(g.title)) + "</summary><p>" + textOf(global_esc(g.body)) + "</p>" + textOf(g.points.map((fun(t: dynamic): dynamic {
                return ("<p>• " + textOf(global_esc(t)) + "</p>")
            })).join("")) + "<small>" + textOf(g.sources.map(global_sourceLink).join(" · ")) + "</small></details>")
        })).join("")) + "</div><h3>Galería de movimientos</h3><div class=\"demo-gallery\">" + textOf(global_Object.entries(global_DemoLibrary).map((fun(arg82: dynamic): dynamic {
            var id: dynamic = absent
            var d: dynamic = absent
            val unpacked83: dynamic = fromIterable(arg82)
            id = unpacked83[0]
            d = unpacked83[1]
            return ("<button class=\"demo-thumb\" onclick=\"technique('" + textOf(id) + "')\"><img loading=\"lazy\" src=\"media/" + textOf(d["file"]) + "-poster.webp\" alt=\"\"><b>" + textOf(global_esc(global_E.byId(id).name)) + "</b></button>")
        })).join("")) + "</div><p class=\"footnote\">Consultado el 4 de octubre de 2026. Recursos visuales originales; no se redistribuyen vídeos de terceros.</p>"))
        return absent
    }
    global_coachMotion = fun(type: dynamic): dynamic {
        global_clearTimeout(global_motionTimeout)
        global_document.querySelectorAll(".coach-intro,.companion").forEach((fun(e: dynamic): dynamic {
            return run {
                val target103: dynamic = e.dataset
                val assigned105: dynamic = type
                target103.motion = assigned105
                assigned105
            }
        }))
        global_motionTimeout = global_setTimeout((fun(): dynamic {
            return global_document.querySelectorAll(".coach-intro,.companion").forEach((fun(e: dynamic): dynamic {
                return run {
                    val target106: dynamic = e.dataset
                    val assigned108: dynamic = "idle"
                    target106.motion = assigned108
                    assigned108
                }
            }))
        }), 2200)
        return absent
    }
    global_V4 = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["technique"] = global_technique
        obj1["book"] = global_book
        obj1["home"] = global_home
        obj1["render"] = global_render
        obj1["validateBackup"] = global_validateBackup
        obj1["answerCoach"] = global_answerCoach
        obj1["gesture"] = global_gesture
    }
    global_profileForm = (fun(arg4: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg4) { false }
        val p: dynamic = jsOr(global_state.profile) { js("({})") }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V4.profileForm(initial)
        val form: dynamic = box.querySelector("form")
        val blocks: dynamic = jsArray { array7 ->
            appendAll(array7, form.querySelectorAll(".profile-block"))
        }
        val injury: dynamic = blocks.find((fun(b: dynamic): dynamic {
            return b.textContent.includes("Lesiones y molestias")
        }))
        val restrictions: dynamic = blocks.find((fun(b: dynamic): dynamic {
            return b.textContent.includes("Restricciones e indicaciones recibidas")
        }))
        val compact: dynamic = global_document.createElement("details")
        compact.className = "card health-compact"
        compact.id = "healthDetails"
        compact.innerHTML = ("<summary><span><b>Lesiones y salud</b><small>" + textOf((if (truth(onPresent(p.injuries) { value14 -> value14.length })) ("" + textOf(p.injuries.length) + " zona(s) registrada(s)") else "Sin lesiones registradas")) + " · tocar para editar</small></span><span>＋</span></summary><p class=\"muted\">Añade el tipo y la zona de tu lesión. Las indicaciones adicionales están agrupadas debajo.</p>")
        injury.before(compact)
        onPresent(injury.querySelector(".step-number")) { value15 -> value15.remove() }
        onPresent(injury.querySelector("h3")) { value16 -> value16.remove() }
        injury.className = "health-content"
        compact.append(injury)
        val advanced: dynamic = global_document.createElement("details")
        advanced.className = "health-extra"
        advanced.innerHTML = "<summary>Restricciones e indicaciones adicionales</summary>"
        onPresent(restrictions.querySelector(".step-number")) { value23 -> value23.remove() }
        onPresent(restrictions.querySelector("h3")) { value24 -> value24.remove() }
        restrictions.className = "health-content"
        advanced.append(restrictions)
        compact.append(advanced)
        val week: dynamic = global_Schedule.defaults(p)
        val schedule: dynamic = global_document.createElement("section")
        schedule.className = "card profile-block week-block"
        schedule.innerHTML = ("<span class=\"eyebrow\">TU SEMANA, A TU RITMO</span><h3>¿Qué días quieres entrenar?</h3><p class=\"muted\">Elige entrenamiento, descanso completo o movilidad para cada día.</p><div class=\"week-editor\">" + textOf(dynamicValue(jsonValue("[1,2,3,4,5,6,0]")).map((fun(n: dynamic): dynamic {
            return ("<label class=\"week-day\"><span>" + textOf(global_Schedule.dayNames[n]) + "</span><select name=\"day" + textOf(n) + "\" aria-label=\"Actividad del " + textOf(global_Schedule.dayNames[n].toLowerCase()) + "\">" + textOf(global_options(jsonValue("{\"train\":\"Entrenamiento\",\"rest\":\"Descanso\",\"mobility\":\"Movilidad\"}"), week[n])) + "</select></label>")
        })).join("")) + "</div><div class=\"fields\"><label>Enfoque de fuerza<select name=\"focus\">" + textOf(global_options(jsonValue("{\"full\":\"Cuerpo completo\",\"upper\":\"Tren superior\",\"lower\":\"Piernas y glúteos\",\"core\":\"Zona media\"}"), jsOr(p.focus) { "full" })) + "</select></label><label>Descanso entre series<select name=\"restSeconds\">" + textOf(global_options(jsonValue("{\"60\":\"60 segundos\",\"90\":\"90 segundos\",\"120\":\"120 segundos\"}"), global_String(jsOr(p.restSeconds) { 90 }))) + "</select></label></div><p class=\"footnote\">Si eliges días consecutivos, se alterna fuerza con cardio. Los días de descanso quedan libres de ejercicios automáticos. Las misiones personalizadas se conservan.</p>")
        compact.before(schedule)
        form.querySelector("#frequency").closest(".field").hidden = true
        form.querySelector("#experience").closest(".field").hidden = true
        form.querySelector("#trainingGoal").closest(".field").hidden = true
        form.elements.finalTarget["value"] = jsOr(p.goal) { "general" }
        return box.innerHTML
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val form: dynamic = ev.target
        val week: dynamic = global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, n: dynamic): dynamic {
            return form.elements[jsAdd("day", n)]["value"]
        }))
        form.querySelector("#trainingGoal")["value"] = form.elements.finalTarget["value"]
        form.querySelector("#experience")["value"] = (if (truth((form.elements.difficulty["value"] === "basic"))) "beginner" else "regular")
        if (truth(jsAnd(jsAnd((!truth(week.includes("train")))) { (!truth(week.includes("cardio"))) }) { (!truth(week.includes("mobility"))) })) {
            ev.preventDefault()
            return global_toast("Elige al menos un día de entrenamiento o movilidad.")
        }
        form.querySelector("#frequency")["value"] = (if (truth(jsAtLeast(week.filter((fun(x: dynamic): dynamic {
            return jsOr((x === "train")) { (x === "cardio") }
        })).length, 3))) "3" else "2")
        global_V4.submitProfile(ev)
        return absent
    })
    global_render5 = global_render
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_render5()
        global_document.querySelectorAll(".brand small").forEach((fun(e: dynamic): dynamic {
            return run {
                val target49: dynamic = e
                val assigned51: dynamic = "EL DESPERTAR · V5"
                target49.textContent = assigned51
                assigned51
            }
        }))
        return absent
    })
    global_technique = (fun(id: dynamic): dynamic {
        global_V4.technique(id)
        val e: dynamic = global_E.byId(id)
        val baseId: dynamic = jsOr(e.baseId) { id }
        val demo: dynamic = global_DemoLibrary[baseId]
        if (truth(demo)) {
            val container: dynamic = global_document.createElement("section")
            container.className = "demo-card"
            container.innerHTML = ("<div class=\"row between\"><span class=\"eyebrow\">DEMOSTRACIÓN SIN CONEXIÓN</span><span class=\"badge\">2D · ESQUEMA</span></div><img id=\"exerciseDemo\" class=\"exercise-demo\" src=\"media/" + textOf(demo["file"]) + "-poster.webp\" alt=\"Esquema de " + textOf(global_esc(e.name)) + "\"><div class=\"action-pair\"><button class=\"btn primary\" id=\"demoToggle\" onclick=\"toggleDemo('" + textOf(demo["file"]) + "')\">▶ Reproducir</button><button class=\"btn ghost\" onclick=\"demoStep('" + textOf(demo["file"]) + "')\">Ver posiciones</button></div><p class=\"footnote\">Ilustración esquemática original del movimiento base; no evalúa tu postura. " + textOf((if (truth(e.sides)) "Se muestra un lado; realiza también el otro según las instrucciones." else "")) + " " + textOf((if (truth((e.variant !== "base"))) "La variante cambia el ritmo o la duración: consulta sus instrucciones." else "")) + "</p>")
            global_Dollar("#modal").querySelector(".tech-tags").after(container)
        }
        val more: dynamic = global_document.createElement("section")
        more.className = "learning-extra"
        val lesson: dynamic = jsOr(global_TrainingGuides.find((fun(g: dynamic): dynamic {
            return (g.group === e.group)
        }))) { global_TrainingGuides[0] }
        more.innerHTML = ("<details><summary>Más información y fuentes</summary><h3>" + textOf(global_esc(lesson.title)) + "</h3><p>" + textOf(global_esc(lesson.body)) + "</p><p class=\"footnote\">" + textOf((if (truth(jsAnd(e.source) { (e.sourceScope !== "general") })) "Referencia técnica consultada:" else "Fuentes generales de consulta; la secuencia es una propuesta editorial:")) + "</p>" + textOf(dynamicValue((if (truth(e.source)) arrayOf<dynamic>(e.source) else jsonValue("[\"mayo\",\"nhs\"]"))).map((fun(k: dynamic): dynamic {
            return global_sourceLink(k)
        })).join("<br>")) + "<p class=\"footnote\">Fichas y esquemas elaborados para esta app. Las fuentes no han revisado ni avalado el generador de rutinas.</p></details>")
        global_Dollar("#modal").append(more)
        global_Dollar("#modal").scrollTop = 0
        return absent
    })
    global_demoPlaying = false
    global_demoIndex = 0
    global_book = (fun(): dynamic {
        return jsAdd(("<button class=\"academy-banner\" onclick=\"openAcademy()\"><span>◇ ACADEMIA SIN CONEXIÓN</span><b>Guías, técnica y animaciones ↗</b><small>" + textOf(global_Object.keys(global_DemoLibrary).length) + " demostraciones · " + textOf(global_TrainingGuides.length) + " guías</small></button>"), global_V4.book())
    })
    global_home = (fun(): dynamic {
        return global_V4.home()
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(jsAnd(dynamicValue(jsRegExp("dias|días|descanso|calendario|semana elegida", "i")).test(q)) { global_Schedule.valid(global_state.profile) })) {
            return jsAdd(jsAdd("Tu calendario: ", dynamicValue(jsonValue("[1,2,3,4,5,6,0]")).map((fun(n: dynamic): dynamic {
                return jsAdd(jsAdd(global_Schedule.dayNames[n], ": "), jsonValue("{\"train\":\"fuerza\",\"cardio\":\"cardio\",\"rest\":\"descanso\",\"mobility\":\"movilidad\"}")[global_state.profile.weekPlan[n]])
            })).join("; ")), ". Puedes cambiarlo en Perfil. Las misiones iniciadas o personalizadas se conservan.")
        }
        var response: dynamic = global_V4.answerCoach(q)
        if (truth(global_Schedule.valid(global_state.profile))) {
            response = response.replace(jsAdd(global_state.profile.frequency, " sesiones principales por semana"), jsAdd(global_state.profile.weekPlan.filter((fun(x: dynamic): dynamic {
                return jsOr((x === "train")) { (x === "cardio") }
            })).length, " días de entrenamiento elegidos por semana"))
        }
        return response
    })
    global_validateBackup = (fun(s: dynamic): dynamic {
        if (truth((!truth(global_V4.validateBackup(s))))) {
            return false
        }
        val p: dynamic = s.profile
        if (truth(jsAnd(onPresent(p) { value92 -> value92.weekPlan }) { (!truth(global_Schedule.valid(p))) })) {
            return false
        }
        if (truth(jsAnd(onPresent(p) { value93 -> value93.focus }) { (!truth(dynamicValue(jsonValue("[\"full\",\"upper\",\"lower\",\"core\"]")).includes(p.focus))) })) {
            return false
        }
        if (truth(jsAnd((onPresent(p) { value94 -> value94.restSeconds } !== absent)) { (!truth(dynamicValue(jsonValue("[30,60,90,120]")).includes(p.restSeconds))) })) {
            return false
        }
        if (truth(jsAnd((onPresent(p) { value95 -> value95.animate } !== absent)) { (jsTypeOf(p.animate) !== "boolean") })) {
            return false
        }
        if (true) {
            val iterator96: dynamic = iteratorOf(s.cycles)
            while (true) {
                val next97: dynamic = iterator96.next()
                if (truth(next97.done)) break
                var c: dynamic = absent
                c = next97.value
                if (truth(jsAnd((c.reviews !== absent)) { jsOr(jsOr((!truth(global_Array.isArray(c.reviews)))) { jsMore(c.reviews.length, 3) }) { c.reviews.some((fun(r: dynamic): dynamic {
                    return jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(r))) { (!truth(global_Number.isInteger(r.week))) }) { jsLess(r.week, 1) }) { jsMore(r.week, 3) }) { (!truth(dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(r.date))) }) { (!truth(dynamicValue(jsonValue("[\"mantener\",\"aumentar\",\"reducir\",\"pausar\"]")).includes(r.action))) }) { (jsTypeOf(r.reason) !== "string") }) { jsMore(r.reason.length, 1000) }) { (!truth(global_Number.isFinite(r.adherence))) }) { jsLess(r.adherence, 0) }) { jsMore(r.adherence, 1) }) { (!truth(global_Number.isFinite(r.effort))) }) { jsLess(r.effort, 0) }) { jsMore(r.effort, 10) }) { (!truth(global_Number.isInteger(r.changed))) }) { jsLess(r.changed, 0) }) { jsMore(r.changed, 21) }
                })) } })) {
                    return false
                }
            }
        }
        return true
    })
    global_oldOpenCoach = global_openCoach
    global_openCoach = (fun(): dynamic {
        global_oldOpenCoach()
        onPresent(global_Dollar("#modal").querySelector(".coach-intro")) { value100 -> value100.insertAdjacentHTML("afterend", "<div class=\"companion-actions\"><button class=\"btn ghost\" onclick=\"coachMotion('wave')\">Saludar</button><button class=\"btn ghost\" onclick=\"coachMotion('flip')\">Pirueta</button><button class=\"btn ghost\" onclick=\"coachMotion('train')\">Activarse</button></div>") }
        global_coachMotion("wave")
        return absent
    })
    global_gesture = (fun(type: dynamic): dynamic {
        global_V4.gesture(type)
        global_coachMotion(type)
        return absent
    })
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        return global_document.documentElement.classList.toggle("app-hidden", global_document.hidden)
    }))
}
