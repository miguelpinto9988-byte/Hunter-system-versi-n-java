@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv4

import hunter.browser.*

@JsName("V3") private external var global_V3: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("previewTheme") private external var global_previewTheme: dynamic
@JsName("knight") private external var global_knight: dynamic
@JsName("gesture") private external var global_gesture: dynamic
@JsName("companion") private external var global_companion: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("askCoach") private external var global_askCoach: dynamic
@JsName("saveCheckin") private external var global_saveCheckin: dynamic
@JsName("recomputeV3") private external var global_recomputeV3: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("Coach") private external var global_Coach: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("recomputeMission") private external var global_recomputeMission: dynamic

internal fun installV4() {
    global_applyTheme = fun(arg11: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg11) { jsOr(global_state.profile) { js("({})") } }
        global_document.documentElement.dataset.theme = (if (truth(jsOr((p.theme === "pink")) { jsAnd((p.theme !== "blue")) { (p.gender === "female") } })) "pink" else "blue")
        global_document.documentElement.classList.toggle("still", (p.animate === false))
        return absent
    }
    global_previewTheme = fun(f: dynamic): dynamic {
        global_applyTheme(jsObject { obj16 ->
            obj16["gender"] = f.gender["value"]
            obj16["theme"] = f.theme["value"]
            obj16["animate"] = f.animate.checked
        })
        return absent
    }
    global_knight = fun(): dynamic {
        return ("<svg class=\"knight\" viewBox=\"0 0 180 220\" aria-hidden=\"true\"><ellipse cx=\"90\" cy=\"207\" rx=\"48\" ry=\"7\" fill=\"#050815\" opacity=\".6\"/><g class=\"knight-body\"><path class=\"cape\" d=\"M57 102 Q36 155 31 198 Q88 214 146 196 L121 102\" fill=\"#393d53\" stroke=\"#171b2c\" stroke-width=\"3\"/><path class=\"plume\" d=\"M87 47 Q54 6 104 10 L121 34 Q134 98 151 140 L130 135 Q107 96 101 48\" fill=\"#c93042\" stroke=\"#751b32\" stroke-width=\"3\"/><g fill=\"#111827\" stroke=\"#303b52\" stroke-width=\"3\"><path d=\"M64 143 L87 145 L83 202 L60 202Z\"/><path d=\"M92 145 L115 143 L122 202 L96 202Z\"/><path d=\"M61 100 L120 100 L117 151 L91 166 L62 150Z\"/></g><path d=\"M66 112 L86 122 L91 110 L97 122 L115 111 L102 136 L91 146 L77 132Z\" fill=\"#54daf7\"/><g class=\"arm-left\"><path d=\"M62 109 L48 114 L45 158 L57 166 L68 129\" fill=\"#101727\" stroke=\"#34405a\" stroke-width=\"3\"/><path d=\"M49 128 L59 132 M48 138 L58 143\" stroke=\"#54daf7\" stroke-width=\"3\"/></g><g class=\"arm-right\"><path d=\"M119 109 L133 114 L139 152 L128 161 L116 129\" fill=\"#101727\" stroke=\"#34405a\" stroke-width=\"3\"/><path d=\"M124 127 L134 124 M126 138 L136 134\" stroke=\"#54daf7\" stroke-width=\"3\"/></g><g class=\"knight-head\"><path d=\"M54 47 L29 39 L22 16 L24 60 L43 72 M123 48 L145 34 L151 12 L158 60 L140 76\" fill=\"#101726\" stroke=\"#29324a\" stroke-width=\"3\"/><path d=\"M41 61 Q42 29 89 27 Q137 28 141 66 L135 91 Q113 115 89 117 Q56 112 42 88Z\" fill=\"#0d1424\" stroke=\"#303a53\" stroke-width=\"3\"/><path d=\"M89 29 L89 93 L73 106 M89 93 L105 106\" fill=\"none\" stroke=\"#1d2a42\" stroke-width=\"3\"/><g class=\"eyes\" fill=\"#64e5ff\"><path d=\"M51 73 L78 79 L81 87 L61 84Z\"/><path d=\"M127 73 L100 79 L97 87 L118 84Z\"/></g></g></g></svg>")
    }
    global_gesture = fun(arg21: dynamic): dynamic {
        var kind: dynamic = absent
        kind = definedOr(arg21) { "wave" }
        val el: dynamic = global_Dollar("#companion")
        if (truth((!truth(el)))) {
            return absent
        }
        el.dataset.motion = kind
        global_setTimeout((fun(): dynamic {
            if (truth(el.isConnected)) {
                el.dataset.motion = "idle"
            }
            return absent
        }), 1800)
        return absent
    }
    global_companion = fun(): dynamic {
        return ("<button id=\"companion\" data-motion=\"idle\" class=\"companion\" onclick=\"openCoach()\" aria-label=\"Abrir asistente Igris\">" + textOf(global_knight()) + "<span>IGRIS · AYUDA</span></button>")
    }
    global_openCoach = fun(): dynamic {
        global_gesture("wave")
        val c: dynamic = global_currentCycle()
        val reviews: dynamic = dynamicValue(jsOr(onPresent(c) { value42 -> value42.reviews }) { js("[]") }).slice().reverse()
        global_modal(("<section class=\"coach-panel\"><span class=\"eyebrow\">IGRIS / ASISTENTE LOCAL</span><div class=\"coach-intro\">" + textOf(global_knight()) + "<div><h2>Tu siguiente paso.</h2><p>Rutinas, técnica y seguimiento sin conexión.</p></div></div><p class=\"notice\">Aprendizaje personal sin conexión. Igris revisa tu evolución cada 7 días del ciclo y conserva tus sesiones iniciadas.</p><div class=\"row wrap\"><button class=\"btn ghost\" onclick=\"askCoach('mi plan')\">Mi plan</button><button class=\"btn ghost\" onclick=\"askCoach('progreso')\">Evolución</button><button class=\"btn ghost\" onclick=\"askCoach('lesiones')\">Restricciones</button></div><div id=\"coachAnswer\" class=\"coach-answer\" role=\"status\">Pregúntame por un ejercicio usando su nombre en español.</div><form onsubmit=\"HunterActions.submitCoach(this,event)\"><label class=\"label\" for=\"coachQuestion\">Tu pregunta</label><input id=\"coachQuestion\" name=\"question\" maxlength=\"250\" required placeholder=\"¿Cómo hago una sentadilla?\"><button class=\"btn primary full\">Consultar</button></form><h3>Cómo me siento hoy</h3><p class=\"footnote\">Guarda el esfuerzo después de completar una sesión. Sin esfuerzo registrado, el sistema no aumenta repeticiones.</p><form onsubmit=\"HunterActions.submitCheckin(this,event)\"><label>Esfuerzo de mi sesión (1–10)<input name=\"effort\" type=\"number\" min=\"1\" max=\"10\" required value=\"" + textOf(jsOr(onPresent(global_state.logs[global_E.dateKey()]) { value43 -> value43.rpe }) { "" }) + "\"></label><label><input type=\"checkbox\" name=\"fatigue\"> Fatiga alta</label><label><input type=\"checkbox\" name=\"pain\"> Dolor durante el ejercicio</label><button class=\"btn full\">Guardar seguimiento</button></form><h3>Registro de decisiones</h3>" + textOf(jsOr(reviews.map((fun(r: dynamic): dynamic {
            return ("<article class=\"card\"><b>Semana " + textOf(r.week) + " · " + textOf(global_esc(r.action)) + "</b><p>" + textOf(global_esc(r.reason)) + "</p><small>" + textOf(global_Math.round(jsMultiply(r.adherence, 100))) + " % de adherencia · " + textOf(r.changed) + " días pendientes ajustados</small></article>")
        })).join("")) { "<p class=\"muted\">La primera revisión llega después de 7 días. Los bloques se renuevan cada 21 días.</p>" }) + "<p class=\"footnote\">Las decisiones conservan días iniciados y misiones personalizadas. La carga de las mancuernas no aumenta automáticamente.</p></section>"))
        return absent
    }
    global_answerCoach = fun(q: dynamic): dynamic {
        val p: dynamic = global_state.profile
        val c: dynamic = global_currentCycle()
        val s: dynamic = q.toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "")
        if (truth(dynamicValue(jsRegExp("lesion|dolor|molestia", "")).test(s))) {
            return jsAdd(global_Adapt.mode(p).reason, " Si aparece dolor, detén la sesión y actualiza tu perfil. No puedo diagnosticar ni indicar rehabilitación.")
        }
        if (truth(dynamicValue(jsRegExp("progres|evolu|semana|intensi", "")).test(s))) {
            return jsAdd(jsOr(onPresent(c.reviews) { value46 -> onPresent(value46.at((-numeric(1)))) { value47 -> value47.reason } }) { "Aún no hay una revisión semanal." }, " Cada 7 días se revisan adherencia y esfuerzo. Sin datos suficientes se mantiene el volumen; con fatiga o esfuerzo alto se reduce. El aumento máximo por revisión es 1 repetición o 5 segundos, hasta 15 repeticiones o 60 segundos.")
        }
        if (truth(dynamicValue(jsRegExp("plan|objetivo|rutina|perfil", "")).test(s))) {
            return ("" + textOf(global_Adapt.goals[p.goal]) + ". Nivel " + textOf(jsOr(jsonValue("{\"basic\":\"básico\",\"intermediate\":\"intermedio\",\"advanced\":\"avanzado\"}")[p.difficulty]) { "configurado" }) + ", " + textOf(p.frequency) + " sesiones principales por semana y " + textOf(p.minutes) + " minutos aproximados. " + textOf(global_Adapt.mode(p).reason) + " Edad y restricciones limitan la selección; equipo, nivel y objetivo guían los ejercicios. Peso y estatura son registros, no una fórmula de carga. Género configura el tema. Tu meta personal: " + textOf(jsOr(p.finalGoal) { "sin descripción adicional" }) + ".")
        }
        val words: dynamic = s.split(jsRegExp("\\W+", "")).map((fun(w: dynamic): dynamic {
            return (if (truth(w.endsWith("s"))) w.slice(0, (-numeric(1))) else w)
        })).filter((fun(w: dynamic): dynamic {
            return jsAnd(jsMore(w.length, 3)) { (!truth(dynamicValue(jsonValue("[\"como\",\"hacer\",\"hago\",\"para\",\"ejercicio\",\"puedo\",\"quiero\"]")).includes(w))) }
        }))
        val candidates: dynamic = global_E.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).map((fun(e: dynamic): dynamic {
            return jsObject { obj51 ->
                obj51["e"] = e
                obj51["score"] = words.filter((fun(w: dynamic): dynamic {
                    return e.name.toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "").includes(w)
                })).length
            }
        })).filter((fun(x: dynamic): dynamic {
            return x.score
        })).sort((fun(a: dynamic, b: dynamic): dynamic {
            return jsSubtract(b.score, a.score)
        }))
        if (truth(candidates.length)) {
            val e: dynamic = candidates[0].e
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(e.name, ": "), e.instructions.join(" ")), " "), jsOr(e.cue) { "" }), (if (truth(global_Adapt.reasons(e, p).length)) jsAdd(jsAdd(" No está incluido para tu perfil: ", global_Adapt.reasons(e, p).join(", ")), ".") else ""))
        }
        return "No encuentro esa pregunta en mis respuestas locales. Puedo explicar ejercicios del catálogo, tu plan, las restricciones y la evolución semanal. No tengo acceso a un modelo generativo ni a consultas en Internet."
    }
    global_askCoach = fun(q: dynamic): dynamic {
        global_Dollar("#coachAnswer").textContent = global_answerCoach(global_String(q).slice(0, 250))
        global_gesture("think")
        return absent
    }
    global_saveCheckin = fun(f: dynamic): dynamic {
        val date: dynamic = global_E.dateKey()
        val l: dynamic = global_state.logs[date]
        if (truth((!truth(onPresent(l) { value58 -> value58.complete })))) {
            return global_toast("Completa la misión de hoy antes de registrar su esfuerzo.")
        }
        l.rpe = numeric(f.effort["value"])
        l.fatigue = f.fatigue.checked
        l.pain = f.pain.checked
        global_state.weekFeedback = jsOr(global_state.weekFeedback) { js("({})") }
        global_state.weekFeedback[date] = jsObject { obj69 ->
            obj69["fatigue"] = f.fatigue.checked
            obj69["pain"] = f.pain.checked
        }
        if (truth(l.pain)) {
            l.complete = false
            global_state.profile.health = jsObject { obj74 ->
                copyFields(obj74, global_state.profile.health)
                obj74["pain"] = "moderate"
            }
            global_state.profile.limits = true
            global_toast("Sesión detenida. Actualiza las zonas y restricciones en Perfil.")
        }
        global_save()
        global_render()
        global_openCoach()
        return absent
    }
    global_V3 = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["render"] = global_render
        obj1["home"] = global_home
        obj1["validateBackup"] = global_validateBackup
        obj1["currentCycle"] = global_currentCycle
    }
    global_options = (fun(values: dynamic, selected: dynamic): dynamic {
        return global_Object.entries(values).map((fun(arg4: dynamic): dynamic {
            var v: dynamic = absent
            var t: dynamic = absent
            val unpacked5: dynamic = fromIterable(arg4)
            v = unpacked5[0]
            t = unpacked5[1]
            return ("<option value=\"" + textOf(v) + "\" " + textOf((if (truth((v === selected))) "selected" else "")) + ">" + textOf(t) + "</option>")
        })).join("")
    })
    global_profileForm = (fun(arg8: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg8) { false }
        val p: dynamic = jsOr(global_state.profile) { js("({})") }
        return global_V3.profileForm(initial).replace("<div class=\"card profile-block\"><span class=\"step-number\">01</span>", ("<div class=\"card profile-block\"><span class=\"eyebrow\">IDENTIDAD DEL SISTEMA</span><div class=\"fields\"><label>Género<select name=\"gender\" onchange=\"previewTheme(this.form)\">" + textOf(global_options(jsonValue("{\"unspecified\":\"Prefiero no indicarlo\",\"male\":\"Masculino\",\"female\":\"Femenino\",\"nonbinary\":\"No binario\"}"), jsOr(p.gender) { "unspecified" })) + "</select></label><label>Tema<select name=\"theme\" onchange=\"previewTheme(this.form)\">" + textOf(global_options(jsonValue("{\"auto\":\"Automático según género\",\"blue\":\"Azul\",\"pink\":\"Rosado\"}"), jsOr(p.theme) { "auto" })) + "</select></label></div><p class=\"footnote\">Automático: rosado para femenino y azul para las demás opciones. El género cambia la estética; no determina tu capacidad física.</p><label>Nivel de entrenamiento<select name=\"difficulty\">" + textOf(global_options(jsonValue("{\"basic\":\"Básico\",\"intermediate\":\"Intermedio\",\"advanced\":\"Avanzado\"}"), jsOr(p.difficulty) { "basic" })) + "</select></label><label>Objetivo final<select name=\"finalTarget\" onchange=\"HunterActions.updateTrainingGoal(this)\">" + textOf(global_options(global_Adapt.goals, jsOr(jsOr(p.finalTarget) { p.goal }) { "general" })) + "</select></label><label>Mi meta personal<textarea name=\"finalGoal\" maxlength=\"300\" placeholder=\"Por ejemplo: entrenar tres días cada semana y mejorar mi técnica\">" + textOf(global_esc(jsOr(p.finalGoal) { "" })) + "</textarea></label><p class=\"footnote\">El objetivo seleccionado orienta el plan. IGRIS propone una interpretación local de tu descripción; revísala antes de guardar. No interpreta una prescripción ni promete resultados.</p><label><input type=\"checkbox\" name=\"animate\" " + textOf((if (truth((p.animate === false))) "" else "checked")) + "> Animaciones del acompañante</label></div><div class=\"card profile-block\"><span class=\"step-number\">01</span>"))
    })
    global_currentCycle = (fun(): dynamic {
        val c: dynamic = global_V3.currentCycle()
        if (truth(jsAnd(c) { global_state.profile })) {
            val r: dynamic = global_Coach.review(c, global_state.logs, global_state.profile, global_E.dateKey(), jsOr(onPresent(global_state.weekFeedback) { value30 -> value30[global_E.dateKey()] }) { js("({})") })
            if (truth(r)) {
                global_save()
            }
        }
        return c
    })
    global_home = (fun(): dynamic {
        return jsAdd(global_V3.home(), ("<section class=\"card coach-banner\"><span class=\"eyebrow\">COMPAÑERO DE ENTRENAMIENTO</span><h2>Igris está contigo.</h2><p>" + textOf(global_esc(jsOr(onPresent(global_state.profile) { value33 -> value33.finalGoal }) { "Construye tu constancia, una misión a la vez." })) + "</p><button class=\"btn\" onclick=\"openCoach()\">Consultar mi plan y evolución</button></section>"))
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V3.render()
        global_applyTheme()
        onPresent(global_document.querySelector("#companion")) { value36 -> value36.remove() }
        if (truth(global_state.profile)) {
            global_document.body.insertAdjacentHTML("beforeend", global_companion())
        }
        return absent
    })
    global_validateBackup = (fun(`data`: dynamic): dynamic {
        if (truth((!truth(global_V3.validateBackup(`data`))))) {
            return false
        }
        val p: dynamic = `data`.profile
        if (truth(p)) {
            if (true) {
                val iterator79: dynamic = iteratorOf(global_Object.entries(jsonValue("{\"gender\":[\"unspecified\",\"male\",\"female\",\"nonbinary\"],\"theme\":[\"auto\",\"blue\",\"pink\",\"violet\",\"emerald\",\"amber\",\"coral\",\"red\",\"teal\",\"ice\",\"gold\"],\"difficulty\":[\"basic\",\"intermediate\",\"advanced\"]}")))
                while (true) {
                    val next80: dynamic = iterator79.next()
                    if (truth(next80.done)) break
                    var k: dynamic = absent
                    var values: dynamic = absent
                    val unpacked81: dynamic = fromIterable(next80.value)
                    k = unpacked81[0]
                    values = unpacked81[1]
                    if (truth(jsAnd((p[k] !== absent)) { (!truth(values.includes(p[k]))) })) {
                        return false
                    }
                }
            }
            if (truth(jsAnd((p.finalGoal !== absent)) { jsOr((jsTypeOf(p.finalGoal) !== "string")) { jsMore(p.finalGoal.length, 300) } })) {
                return false
            }
        }
        return true
    })
    global_recomputeV3 = global_recomputeMission
    global_recomputeMission = (fun(d: dynamic): dynamic {
        val was: dynamic = onPresent(global_state.logs[d.date]) { value84 -> value84.complete }
        val result: dynamic = global_recomputeV3(d)
        if (truth(jsAnd((!truth(was))) { result })) {
            global_setTimeout((fun(): dynamic {
                return global_gesture("flip")
            }), 80)
        }
        return result
    })
    global_state.appVersion = 4
}
