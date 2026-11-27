@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv7

import hunter.browser.*

@JsName("ThemeHues") private external var global_ThemeHues: dynamic
@JsName("V6") private external var global_V6: dynamic
@JsName("updateObjective") private external var global_updateObjective: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("Palette") private external var global_Palette: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("TrainingSources") private external var global_TrainingSources: dynamic
@JsName("TrainingGuides") private external var global_TrainingGuides: dynamic

internal fun installV7() {
    global_updateObjective = fun(select: dynamic): dynamic {
        val plan: dynamic = global_GoalPlans.plans[select["value"]]
        select.form.querySelector("#trainingGoal")["value"] = select["value"]
        select.form.querySelector("#objectiveExplanation").textContent = plan.why
        return absent
    }
    global_ThemeHues = jsonValue("{\"blue\":202,\"pink\":330,\"violet\":267,\"emerald\":151,\"amber\":36,\"coral\":15,\"red\":348,\"teal\":175,\"ice\":215,\"gold\":51}")
    global_V6 = jsObject { obj1 ->
        obj1["render"] = global_render
        obj1["profileForm"] = global_profileForm
        obj1["home"] = global_home
        obj1["openCoach"] = global_openCoach
        obj1["answerCoach"] = global_answerCoach
        obj1["validateBackup"] = global_validateBackup
    }
    global_applyTheme = (fun(arg4: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg4) { jsOr(global_state.profile) { js("({})") } }
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        val key: dynamic = (if (truth(global_Palette[p.theme])) p.theme else (if (truth((p.gender === "female"))) "pink" else "blue"))
        val h: dynamic = global_ThemeHues[key]
        val r: dynamic = global_document.documentElement
        r.dataset.theme = key
        val values: dynamic = jsObject { obj7 ->
            obj7["--bg"] = ("hsl(" + textOf(h) + " 42% 7%)")
            obj7["--panel"] = ("hsl(" + textOf(h) + " 34% 18%)")
            obj7["--surface"] = ("hsl(" + textOf(h) + " 37% 23%)")
            obj7["--input"] = ("hsl(" + textOf(h) + " 35% 14%)")
            obj7["--line"] = ("hsl(" + textOf(h) + " 27% 39%)")
            obj7["--text"] = ("hsl(" + textOf(h) + " 60% 95%)")
            obj7["--muted"] = ("hsl(" + textOf(h) + " 27% 79%)")
            obj7["--accent"] = global_Palette[key][1]
            obj7["--cyan"] = global_Palette[key][1]
            obj7["--violet"] = ("hsl(" + textOf(jsMod(jsAdd(h, 30), 360)) + " 60% 80%)")
            obj7["--glow"] = ("hsla(" + textOf(h) + ",80%,65%,.14)")
        }
        global_Object.entries(values).forEach((fun(arg8: dynamic): dynamic {
            var k: dynamic = absent
            var v: dynamic = absent
            val unpacked9: dynamic = fromIterable(arg8)
            k = unpacked9[0]
            v = unpacked9[1]
            return r.style.setProperty(k, v)
        }))
        r.classList.toggle("still", (p.animate === false))
        global_document.querySelector("meta[name=theme-color]").content = values["--bg"]
        return absent
    })
    global_profileForm = (fun(arg20: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg20) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V6.profileForm(initial)
        val select: dynamic = box.querySelector("[name=finalTarget]")
        select.setAttribute("onchange", "updateObjective(this)")
        select.insertAdjacentHTML("afterend", ("<p id=\"objectiveExplanation\" class=\"objective-explanation\">" + textOf(global_esc(dynamicValue(jsOr(global_GoalPlans.plans[select["value"]]) { global_GoalPlans.plans.general }).why)) + "</p>"))
        box.querySelector("[name=finalGoal]").placeholder = "Mi meta personal y cómo quiero medirla"
        box.querySelector("[name=animate]").parentElement.lastChild.textContent = " Animaciones de la interfaz y de Igris"
        val health: dynamic = box.querySelector("#healthDetails")
        health.insertAdjacentHTML("beforeend", "<p class=\"footnote\">La zona y las restricciones guían la búsqueda de alternativas. Cuando la actividad está autorizada, Igris también busca movilidad o cardio compatibles si no hay fuerza disponible. Una lesión de espalda o pierna no identifica por sí sola un tratamiento. Para ejercicios de rehabilitación utiliza las indicaciones de tu profesional.</p>")
        box.querySelectorAll(".profile-block,.health-compact").forEach((fun(el: dynamic, i: dynamic): dynamic {
            return el.style.setProperty("--entry-delay", jsAdd(global_Math.min(jsMultiply(i, 65), 390), "ms"))
        }))
        return box.innerHTML
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V6.render()
        global_document.querySelectorAll(".brand small").forEach((fun(el: dynamic): dynamic {
            return run {
                val target29: dynamic = el
                val assigned31: dynamic = "EL DESPERTAR · V7"
                target29.textContent = assigned31
                assigned31
            }
        }))
        return absent
    })
    global_home = (fun(): dynamic {
        return global_V6.home()
    })
    global_openCoach = (fun(): dynamic {
        global_V6.openCoach()
        val p: dynamic = global_state.profile
        val spec: dynamic = jsOr(jsOr(global_GoalPlans.plans[p.finalTarget]) { global_GoalPlans.plans[p.goal] }) { global_GoalPlans.plans.general }
        global_Dollar(".learning-card").insertAdjacentHTML("beforeend", ("<p><b>Destino: " + textOf(global_esc(spec.name)) + "</b><br>" + textOf(global_esc(spec.why)) + "</p>"))
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("objetivo|meta|destino|adaptar|lesion|lesión", "i")).test(q))) {
            val p: dynamic = global_state.profile
            val spec: dynamic = jsOr(jsOr(global_GoalPlans.plans[p.finalTarget]) { global_GoalPlans.plans[p.goal] }) { global_GoalPlans.plans.general }
            return ("Tu objetivo es: " + textOf(spec.name) + ". " + textOf(spec.why) + " " + textOf(global_Adapt.mode(global_GoalPlans.adjusted(p)).reason) + " Si una categoría no tiene opciones, busco otra compatible. El aprendizaje estima esfuerzo a partir de registros similares; no diagnostica la lesión ni inventa tratamientos. Puedes revisar las alternativas en Ejercicios y registrar tus indicaciones en Perfil.")
        }
        return global_V6.answerCoach(q)
    })
    global_validateBackup = (fun(s: dynamic): dynamic {
        if (truth((!truth(global_V6.validateBackup(s))))) {
            return false
        }
        return dynamicValue(jsArray { array43 ->
            array43.push(s.profile)
            appendAll(array43, s.cycles.map((fun(c: dynamic): dynamic {
                return c.profile
            })))
        }).filter(global_Boolean).every((fun(p: dynamic): dynamic {
            return jsOr((!truth(p.finalTarget))) { (!truth((!truth(global_GoalPlans.plans[p.finalTarget])))) }
        }))
    })
    global_TrainingSources.backcare = jsonValue("{\"name\":\"NHS · Dolor de espalda y actividad\",\"url\":\"https://www.nhs.uk/conditions/back-pain/\"}")
    global_TrainingGuides.push(jsonValue("{\"title\":\"18 · Actividad compatible y recuperación\",\"group\":\"Seguimiento\",\"body\":\"Mantener actividad compatible no equivale a recibir una rehabilitación personalizada. Una misma zona puede tener causas de dolor diferentes.\",\"points\":[\"NHS recomienda mantenerse activo ante dolor de espalda, dentro de lo tolerable, y detener los ejercicios si empeoran el dolor.\",\"La app busca alternativas entre fuerza, cardio y movilidad tras confirmar las indicaciones y restricciones; no asigna tratamientos por el nombre de una lesión.\",\"Dolor intenso, lesión reciente, fractura o cirugía necesitan indicaciones individuales antes de asignar ejercicios automáticamente.\"],\"sources\":[\"backcare\"]}"))
}
