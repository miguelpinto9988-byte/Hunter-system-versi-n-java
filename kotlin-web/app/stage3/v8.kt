@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv8

import hunter.browser.*

@JsName("V7") private external var global_V7: dynamic
@JsName("toggleTrainingDay") private external var global_toggleTrainingDay: dynamic
@JsName("interpretGoal") private external var global_interpretGoal: dynamic
@JsName("bodyResults") private external var global_bodyResults: dynamic
@JsName("openBodyKnowledge") private external var global_openBodyKnowledge: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("openAcademy") private external var global_openAcademy: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Intent") private external var global_Intent: dynamic
@JsName("updateObjective") private external var global_updateObjective: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("injuryDraft") private external var global_injuryDraft: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("DemoLibrary") private external var global_DemoLibrary: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("vectorPanel") private external var global_vectorPanel: dynamic
@JsName("startVector") private external var global_startVector: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("bodySearch") private external var global_bodySearch: dynamic
@JsName("BodyKnowledge") private external var global_BodyKnowledge: dynamic
@JsName("BodySources") private external var global_BodySources: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic

internal fun installV8() {
    global_toggleTrainingDay = fun(button: dynamic): dynamic {
        val checked: dynamic = (button.getAttribute("aria-pressed") !== "true")
        button.setAttribute("aria-pressed", global_String(checked))
        button.form.elements[jsAdd("day", button.dataset.day)]["value"] = (if (truth(checked)) "train" else "rest")
        return absent
    }
    global_interpretGoal = fun(form: dynamic): dynamic {
        val text: dynamic = form.elements.finalGoal["value"].trim()
        val result: dynamic = global_Intent.interpret(text)
        val `out`: dynamic = form.querySelector("#intentResult")
        if (truth((!truth(text)))) {
            `out`.textContent = "Puedes seleccionar el objetivo directamente."
            return absent
        }
        if (truth(result.goal)) {
            form.elements.finalTarget["value"] = result.goal
            global_updateObjective(form.elements.finalTarget)
            `out`.textContent = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("Interpretación: ", global_GoalPlans.plans[result.goal].name), ". "), result.reason), (if (truth(result.medical)) " Mencionas salud o lesiones: configura también la sección de lesiones; no se deduce un tratamiento de este texto." else "")), " Puedes cambiar la selección antes de guardar.")
        } else {
            `out`.textContent = result.reason
        }
        return absent
    }
    global_bodyResults = fun(arg44: dynamic): dynamic {
        var q: dynamic = absent
        q = definedOr(arg44) { "" }
        val entries: dynamic = (if (truth(q)) global_bodySearch(q) else global_BodyKnowledge)
        return jsOr(entries.map((fun(x: dynamic): dynamic {
            return ("<details class=\"card\"><summary>" + textOf(global_esc(x.title)) + " · " + textOf(global_esc(x.category)) + "</summary><p>" + textOf(global_esc(x.body)) + "</p><a href=\"" + textOf(global_BodySources[x.source].url) + "\" target=\"_blank\" rel=\"noopener\">" + textOf(global_esc(global_BodySources[x.source].name)) + "</a></details>")
        })).join("")) { "<p>Sin coincidencias. Prueba un término como espalda, equilibrio o flexión.</p>" }
    }
    global_openBodyKnowledge = fun(): dynamic {
        if (truth(global_vectorSession)) {
            global_vectorSession.playing = false
        }
        global_modal(("<span class=\"eyebrow\">IGRIS · BIBLIOTECA CORPORAL</span><h2>Anatomía, motricidad y recuperación</h2><p>20 fichas educativas incluidas en el APK. Texto disponible sin Internet; las fuentes externas requieren conexión. No son una base de diagnósticos.</p><label>Buscar tema<input type=\"search\" oninput=\"HunterActions.searchBody(this)\" placeholder=\"Espalda, rodilla, coordinación…\"></label><div id=\"bodyResults\" class=\"academy-grid\">" + textOf(global_bodyResults()) + "</div>"))
        return absent
    }
    global_V7 = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["render"] = global_render
        obj1["technique"] = global_technique
        obj1["openAcademy"] = global_openAcademy
        obj1["answerCoach"] = global_answerCoach
        obj1["validateBackup"] = global_validateBackup
        obj1["openCoach"] = global_openCoach
    }
    global_profileForm = (fun(arg4: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg4) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V7.profileForm(initial)
        val p: dynamic = jsOr(global_state.profile) { js("({})") }
        val week: dynamic = global_Schedule.defaults(p)
        val editor: dynamic = box.querySelector(".week-editor")
        editor.innerHTML = jsAdd(jsAdd("<div class=\"day-circles\">", dynamicValue(jsonValue("[1,2,3,4,5,6,0]")).map((fun(n: dynamic): dynamic {
            return ("<button type=\"button\" class=\"day-circle\" data-day=\"" + textOf(n) + "\" aria-label=\"Entrenar el " + textOf(global_Schedule.dayNames[n]) + "\" aria-pressed=\"" + textOf((week[n] !== "rest")) + "\" onclick=\"toggleTrainingDay(this)\">" + textOf(dynamicValue(jsonValue("[\"D\",\"L\",\"M\",\"X\",\"J\",\"V\",\"S\"]"))[n]) + "</button><input type=\"hidden\" name=\"day" + textOf(n) + "\" value=\"" + textOf(week[n]) + "\">")
        })).join("")), "</div><p class=\"footnote\">Círculo marcado: entrenamiento. Sin marcar: descanso. Las selecciones de movilidad de tu perfil anterior se conservan hasta que cambies ese día.</p>")
        val label: dynamic = global_document.createElement("label")
        label.innerHTML = ("Misiones diarias · 1 a 7<select name=\"dailyMissions\">" + textOf(global_options(global_Object.fromEntries(global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, i: dynamic): dynamic {
            return arrayOf<dynamic>(jsAdd(i, 1), jsAdd(jsAdd(i, 1), " misiones"))
        }))), global_String(jsOr(p.dailyMissions) { 3 }))) + "</select><p class=\"footnote\">Son ejercicios o tareas marcables del día, no entrenamientos completos adicionales. El volumen se reparte y pueden añadirse tareas de seguimiento si faltan ejercicios compatibles.</p>")
        box.querySelector(".week-block").append(label)
        val text: dynamic = box.querySelector("[name=finalGoal]")
        text.setAttribute("onchange", "interpretGoal(this.form)")
        text.insertAdjacentHTML("afterend", ("<button type=\"button\" class=\"btn\" onclick=\"interpretGoal(this.form)\">Interpretar mi objetivo con IGRIS</button><div id=\"intentResult\" class=\"objective-explanation\" role=\"status\">Escribe tu meta y revisa la interpretación antes de guardar.</div>"))
        val old: dynamic = text.parentElement.nextElementSibling
        if (truth(onPresent(old) { value11 -> value11.classList.contains("footnote") })) {
            old.textContent = "IGRIS clasifica objetivos escritos en español. Revisa el objetivo seleccionado: esta interpretación local puede equivocarse y no interpreta diagnósticos."
        }
        return box.innerHTML
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val text: dynamic = ev.target.elements.finalGoal["value"].toLowerCase().replace(jsRegExp("no tengo[^.;!?]*", "g"), "")
        if (truth(jsAnd(dynamicValue(jsRegExp("tengo.{0,25}(lesi[oó]n|hernia|fractura)|lesi[oó]n de (espalda|rodilla|pierna)|me duele", "")).test(text)) { (!truth(global_injuryDraft.length)) })) {
            ev.preventDefault()
            val health: dynamic = ev.target.querySelector("#healthDetails")
            health["open"] = true
            health.scrollIntoView(jsonValue("{\"behavior\":\"smooth\"}"))
            return global_toast("Tu meta menciona una lesión o dolor. Completa Lesiones y salud antes de generar el plan.")
        }
        global_V7.submitProfile(ev)
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V7.render()
        global_document.querySelectorAll(".brand small").forEach((fun(e: dynamic): dynamic {
            return run {
                val target35: dynamic = e
                val assigned37: dynamic = "EL DESPERTAR · V8"
                target35.textContent = assigned37
                assigned37
            }
        }))
        return absent
    })
    global_technique = (fun(id: dynamic): dynamic {
        if (truth(global_vectorSession)) {
            global_vectorSession.playing = false
        }
        global_V7.technique(id)
        val e: dynamic = global_E.byId(id)
        if (truth((!truth(global_DemoLibrary[jsOr(e.baseId) { id }])))) {
            global_Dollar("#modal").querySelector(".tech-tags").insertAdjacentHTML("afterend", global_vectorPanel(e))
            global_startVector(id)
        }
        global_Dollar("#modal").insertAdjacentHTML("beforeend", ("<details class=\"learning-extra\"><summary>Anatomía y movimientos relacionados</summary><p>Grupo del catálogo: " + textOf(global_esc(e.group)) + ". Zonas implicadas según la ficha: " + textOf(global_esc(dynamicValue(jsOr(e.zones) { js("[]") }).map((fun(z: dynamic): dynamic {
            return jsOr(global_Adapt.zones[z]) { z }
        })).join(", "))) + ".</p><p>Estos campos guían los filtros; no son un diagnóstico ni una lista exhaustiva de músculos.</p><button class=\"btn\" onclick=\"openBodyKnowledge()\">Biblioteca corporal sin conexión</button></details>"))
        return absent
    })
    global_openAcademy = (fun(): dynamic {
        global_V7.openAcademy()
        global_Dollar("#modal").querySelector("h2").insertAdjacentHTML("afterend", "<button class=\"btn full\" onclick=\"openBodyKnowledge()\">Biblioteca de anatomía y motricidad</button>")
        val gallery: dynamic = global_Dollar(".demo-gallery")
        gallery.innerHTML = global_E.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).map((fun(e: dynamic): dynamic {
            return ("<button class=\"demo-thumb\" onclick=\"technique('" + textOf(e.id) + "')\">" + textOf((if (truth(global_DemoLibrary[e.id])) ("<img loading=\"lazy\" src=\"media/" + textOf(e.id) + "-poster.webp\" alt=\"\">") else "<span class=\"vector-symbol\">◉<br>2D</span>")) + "<b>" + textOf(global_esc(e.name)) + "</b></button>")
        })).join("")
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("anatom|fisioter|neuro|motric|equilibr|articul|flexion|supin|pronac", "i")).test(q))) {
            val found: dynamic = global_bodySearch(q)
            if (truth(found.length)) {
                return found.map((fun(x: dynamic): dynamic {
                    return jsAdd(jsAdd(jsAdd(jsAdd(x.title, ": "), x.body), " Fuente: "), global_BodySources[x.source].name)
                })).join("\n\n")
            }
        }
        if (truth(dynamicValue(jsRegExp("objetivo escrito|interpreta|mi meta es|quiero ", "i")).test(q))) {
            val r: dynamic = global_Intent.interpret(q)
            return (if (truth(r.goal)) jsAdd(jsAdd(jsAdd(jsAdd("Interpreto: ", global_GoalPlans.plans[r.goal].name), ". "), r.reason), " Para aplicarlo, escribe tu meta en Perfil y guarda después de revisar la selección.") else r.reason)
        }
        return global_V7.answerCoach(q)
    })
    global_openCoach = (fun(): dynamic {
        global_V7.openCoach()
        global_Dollar(".learning-card").insertAdjacentHTML("beforeend", "<button class=\"btn\" onclick=\"openBodyKnowledge()\">Consultar biblioteca corporal</button><p class=\"footnote\">Objetivo escrito: clasificador Naive Bayes local. Esfuerzo: regresión kNN con tus registros. Las respuestas educativas recuperan fichas incluidas. No es razonamiento médico ni conversación generativa libre.</p>")
        return absent
    })
    global_validateBackup = (fun(s: dynamic): dynamic {
        return jsAnd(global_V7.validateBackup(s)) { dynamicValue(jsArray { array62 ->
            array62.push(s.profile)
            appendAll(array62, s.cycles.map((fun(c: dynamic): dynamic {
                return c.profile
            })))
        }).filter(global_Boolean).every((fun(p: dynamic): dynamic {
            return jsOr((p.dailyMissions === absent)) { jsAnd(jsAnd(global_Number.isInteger(p.dailyMissions)) { jsAtLeast(p.dailyMissions, 1) }) { jsAtMost(p.dailyMissions, 7) } }
        })) }
    })
    global_state.appVersion = 8
    global_render()
    global_syncNative()
}
