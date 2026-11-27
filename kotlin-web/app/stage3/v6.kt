@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv6

import hunter.browser.*

@JsName("Palette") private external var global_Palette: dynamic
@JsName("V5") private external var global_V5: dynamic
@JsName("motionTimer") private external var global_motionTimer: dynamic
@JsName("closeTimer") private external var global_closeTimer: dynamic
@JsName("oldModal") private external var global_oldModal: dynamic
@JsName("closeCoach") private external var global_closeCoach: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("knight") private external var global_knight: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("coachMotion") private external var global_coachMotion: dynamic
@JsName("clearTimeout") private external var global_clearTimeout: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("Learning") private external var global_Learning: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("TrainingSources") private external var global_TrainingSources: dynamic
@JsName("TrainingGuides") private external var global_TrainingGuides: dynamic

internal fun installV6() {
    global_closeCoach = fun(): dynamic {
        val d: dynamic = global_Dollar("#modal")
        if (truth((!truth(d["open"])))) {
            return absent
        }
        if (truth((!truth(d.querySelector(".coach-panel"))))) {
            d.close()
            return absent
        }
        if (truth(d.classList.contains("coach-closing"))) {
            return absent
        }
        global_clearTimeout(global_motionTimer)
        d.classList.add("coach-closing")
        d.querySelector(".coach-intro").dataset.motion = "exit"
        val wait: dynamic = (if (truth(jsOr((onPresent(global_state.profile) { value42 -> value42.animate } === false)) { global_matchMedia("(prefers-reduced-motion: reduce)").matches })) 0 else 450)
        global_closeTimer = global_setTimeout((fun(): dynamic {
            d.close()
            d.classList.remove("coach-closing")
            onPresent(global_Dollar("#companion")) { value43 -> value43.focus(jsonValue("{\"preventScroll\":true}")) }
            return absent
        }), wait)
        return absent
    }
    global_Palette = jsonValue("{\"blue\":[\"Azul\",\"#6ee7fa\"],\"pink\":[\"Rosado\",\"#ffa4d4\"],\"violet\":[\"Violeta\",\"#c2a7ff\"],\"emerald\":[\"Esmeralda\",\"#7ce2b0\"],\"amber\":[\"Ámbar\",\"#ffd280\"],\"coral\":[\"Coral\",\"#ffb59f\"],\"red\":[\"Carmesí\",\"#ff9fab\"],\"teal\":[\"Turquesa\",\"#71e5d5\"],\"ice\":[\"Hielo\",\"#d5edff\"],\"gold\":[\"Oro\",\"#eee18c\"]}")
    global_V5 = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["render"] = global_render
        obj1["knight"] = global_knight
        obj1["openCoach"] = global_openCoach
        obj1["validateBackup"] = global_validateBackup
        obj1["answerCoach"] = global_answerCoach
    }
    global_applyTheme = (fun(arg4: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg4) { jsOr(global_state.profile) { js("({})") } }
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        val key: dynamic = (if (truth(global_Palette[p.theme])) p.theme else (if (truth((p.gender === "female"))) "pink" else "blue"))
        global_document.documentElement.dataset.theme = key
        global_document.documentElement.style.setProperty("--cyan", global_Palette[key][1])
        global_document.documentElement.style.setProperty("--accent", global_Palette[key][1])
        global_document.documentElement.classList.toggle("still", (p.animate === false))
        return absent
    })
    global_profileForm = (fun(arg9: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg9) { false }
        val holder: dynamic = global_document.createElement("div")
        holder.innerHTML = global_V5.profileForm(initial)
        holder.querySelector("[name=theme]").innerHTML = global_options(jsObject { obj14 ->
            obj14["auto"] = "Automático"
            copyFields(obj14, global_Object.fromEntries(global_Object.entries(global_Palette).map((fun(arg15: dynamic): dynamic {
                var k: dynamic = absent
                var v: dynamic = absent
                val unpacked16: dynamic = fromIterable(arg15)
                k = unpacked16[0]
                v = unpacked16[1]
                return arrayOf<dynamic>(k, v[0])
            }))))
        }, jsOr(onPresent(global_state.profile) { value17 -> value17.theme }) { "auto" })
        val `field`: dynamic = global_document.createElement("label")
        `field`.innerHTML = ("Apariencia de Igris<select name=\"companionStyle\">" + textOf(global_options(jsonValue("{\"knight\":\"Caballero de sombras\",\"mage\":\"Mago astral\",\"guardian\":\"Guardián de cristal\"}"), jsOr(onPresent(global_state.profile) { value20 -> value20.companionStyle }) { "knight" })) + "</select>")
        holder.querySelector("[name=theme]").closest(".fields").append(`field`)
        return holder.innerHTML
    })
    global_knight = (fun(): dynamic {
        val skin: dynamic = jsOr(onPresent(global_state.profile) { value23 -> value23.companionStyle }) { "knight" }
        var svg: dynamic = global_V5.knight()
        if (truth((skin === "mage"))) {
            svg = svg.replace("<g class=\"knight-head\">", "<g class=\"knight-head\"><path d=\"M38 47 L89 0 L137 48Z\" fill=\"#352854\" stroke=\"#c2a7ff\" stroke-width=\"3\"/>").replace("#c93042", "#7352b0")
        }
        if (truth((skin === "guardian"))) {
            svg = svg.replace("<g class=\"knight-head\">", "<g class=\"knight-head\"><path d=\"M52 45 L58 8 L81 30 L92 2 L107 32 L128 10 L130 49Z\" fill=\"#204854\" stroke=\"#71e5d5\" stroke-width=\"3\"/>").replace("#c93042", "#338b86")
        }
        return svg.replace("class=\"knight\"", ("class=\"knight skin-" + textOf(skin) + "\""))
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V5.render()
        val brand: dynamic = global_Dollar(".brand small")
        if (truth(brand)) {
            brand.textContent = "SISTEMA DE ENTRENAMIENTO · V6"
        }
        return absent
    })
    global_coachMotion = (fun(kind: dynamic): dynamic {
        global_clearTimeout(global_motionTimer)
        global_document.querySelectorAll(".coach-intro,.companion").forEach((fun(el: dynamic): dynamic {
            return run {
                val target30: dynamic = el.dataset
                val assigned32: dynamic = kind
                target30.motion = assigned32
                assigned32
            }
        }))
        global_motionTimer = global_setTimeout((fun(): dynamic {
            return global_document.querySelectorAll(".coach-intro,.companion").forEach((fun(el: dynamic): dynamic {
                return run {
                    val target33: dynamic = el.dataset
                    val assigned35: dynamic = "idle"
                    target33.motion = assigned35
                    assigned35
                }
            }))
        }), 2200)
        return absent
    })
    global_oldModal = global_modal
    global_modal = (fun(html: dynamic): dynamic {
        global_clearTimeout(global_closeTimer)
        global_oldModal(html)
        global_Dollar("#modal").classList.remove("coach-closing")
        return absent
    })
    global_Dollar("#modal").addEventListener("cancel", (fun(e: dynamic): dynamic {
        if (truth(global_Dollar("#modal").querySelector(".coach-panel"))) {
            e.preventDefault()
            global_closeCoach()
        }
        return absent
    }))
    global_openCoach = (fun(): dynamic {
        global_V5.openCoach()
        val d: dynamic = global_Dollar("#modal")
        val intro: dynamic = d.querySelector(".coach-intro")
        intro.dataset.motion = "enter"
        val records: dynamic = global_Learning.samples(global_state.logs, global_E.dateKey())
        val plan: dynamic = global_currentCycle()
        val next: dynamic = plan.days.find((fun(x: dynamic): dynamic {
            return jsAnd(jsAtLeast(x.date, global_E.dateKey())) { x.items.length }
        }))
        val prediction: dynamic = (if (truth(next)) global_Learning.predict(records, next.items) else null)
        val status: dynamic = (if (truth(prediction)) jsAdd(jsAdd("Modelo activo · ", prediction.sessions), " sesiones comparables") else jsAdd(jsAdd("Recopilando experiencia · ", records.length), " sesiones guardadas"))
        d.querySelector(".notice").outerHTML = ("<section class=\"learning-card\"><span class=\"eyebrow\">IGRIS · APRENDIZAJE LOCAL</span><h3>" + textOf(status) + "</h3><p>" + textOf((if (truth(prediction)) jsAdd(jsAdd("Esfuerzo estimado de la próxima sesión: <b>", prediction.effort.toFixed(1)), "/10</b>.") else "Completa sesiones y registra tu esfuerzo. Cada estimación necesita al menos cinco sesiones con los mismos ejercicios.")) + "</p><details><summary>Cómo aprendo de ti</summary><p>Regresión por cinco vecinos cercanos (kNN), entrenada con tus últimas 180 sesiones completadas sin dolor. Compara ejercicios, series, repeticiones, segundos y descansos declarados; aprende del esfuerzo de 1 a 10 que registras.</p><p>La revisión semanal permite un aumento pequeño solo con adherencia suficiente y estimaciones de esfuerzo de 7/10 o menos en los días pendientes. Con datos insuficientes mantiene el volumen. Dolor, fatiga y restricciones tienen prioridad. Las estimaciones no están validadas clínicamente y no observan tu técnica.</p><p>Las preguntas usan búsqueda en el catálogo local. Este modelo predice esfuerzo; no genera conversación libre. Tus datos permanecen en el dispositivo y en las copias que tú exportes.</p></details></section>")
        val actions: dynamic = global_document.createElement("div")
        actions.className = "row wrap motion-controls"
        actions.innerHTML = "<button class=\"btn ghost\" onclick=\"coachMotion('walk')\">Pasear</button><button class=\"btn ghost\" onclick=\"coachMotion('celebrate')\">Celebrar</button><button class=\"btn ghost\" onclick=\"coachMotion('bow')\">Reverencia</button>"
        intro.after(actions)
        val close: dynamic = global_Array.from(d.querySelectorAll("button")).find((fun(b: dynamic): dynamic {
            return (b.textContent === "Cerrar")
        }))
        if (truth(close)) {
            close.setAttribute("onclick", "closeCoach()")
        }
        global_clearTimeout(global_motionTimer)
        global_motionTimer = global_setTimeout((fun(): dynamic {
            if (truth(intro.isConnected)) {
                intro.dataset.motion = "idle"
            }
            return absent
        }), 900)
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("aprend|modelo|inteligencia", "i")).test(q))) {
            val n: dynamic = global_Learning.samples(global_state.logs, global_E.dateKey()).length
            return ("Tengo " + textOf(n) + " sesiones de aprendizaje registradas. Comparo cinco sesiones con los mismos ejercicios para estimar esfuerzo y moderar la evolución semanal. Registra cómo te sentiste al terminar cada sesión; los descansos no son ejemplos de entrenamiento.")
        }
        return global_V5.answerCoach(q)
    })
    global_validateBackup = (fun(`data`: dynamic): dynamic {
        return jsAnd(global_V5.validateBackup(`data`)) { jsOr((!truth(onPresent(`data`.profile) { value60 -> value60.companionStyle }))) { dynamicValue(jsonValue("[\"knight\",\"mage\",\"guardian\"]")).includes(`data`.profile.companionStyle) } }
    })
    global_TrainingSources.knn = jsonValue("{\"name\":\"scikit-learn · Regresión por vecinos cercanos\",\"url\":\"https://scikit-learn.org/stable/modules/neighbors.html\"}")
    global_TrainingSources.intensity = jsonValue("{\"name\":\"CDC · Medir la intensidad (español)\",\"url\":\"https://www.cdc.gov/physical-activity-basics/es/measuring/como-medir-la-intensidad-de-la-actividad-fisica.html\"}")
    global_TrainingGuides[15] = jsonValue("{\"title\":\"16 · Cómo aprende Igris\",\"group\":\"Seguimiento\",\"body\":\"Igris incorpora un modelo kNN de esfuerzo con memoria local. Usa sesiones completadas y el esfuerzo que declaras. No envía tus registros a un servidor.\",\"points\":[\"Necesita cinco sesiones con los mismos ejercicios para cada estimación. Con información insuficiente mantiene el volumen.\",\"Las reglas de dolor, fatiga y restricciones prevalecen sobre el aprendizaje. La estimación no es un diagnóstico ni una validación clínica.\",\"El chat consulta el catálogo y el plan; el aprendizaje automático interviene en la progresión, no en conversación generativa.\"],\"sources\":[\"knn\"]}")
    global_TrainingGuides.push(jsonValue("{\"title\":\"17 · Intensidad y prueba del habla\",\"group\":\"Seguimiento\",\"body\":\"Durante cardio moderado normalmente puedes hablar, pero no cantar; durante actividad vigorosa solo puedes decir pocas palabras antes de pausar para respirar.\",\"points\":[\"Es una referencia general de intensidad relativa, no una medición de tu condición física.\",\"Registra tu esfuerzo personal al terminar. No confundas el dolor con un esfuerzo normal.\",\"La referencia de CDC no valida el modelo predictivo ni sus umbrales de progresión.\"],\"sources\":[\"intensity\"]}"))
}
