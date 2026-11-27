@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv9

import hunter.browser.*

@JsName("V8UI") private external var global_V8UI: dynamic
@JsName("activeCompanion") private external var global_activeCompanion: dynamic
@JsName("homeCompanion") private external var global_homeCompanion: dynamic
@JsName("missionCompanion") private external var global_missionCompanion: dynamic
@JsName("companionToken") private external var global_companionToken: dynamic
@JsName("coachContext") private external var global_coachContext: dynamic
@JsName("CompanionNames") private external var global_CompanionNames: dynamic
@JsName("releaseCompanion") private external var global_releaseCompanion: dynamic
@JsName("mountPanelCompanion") private external var global_mountPanelCompanion: dynamic
@JsName("openAnimationGallery") private external var global_openAnimationGallery: dynamic
@JsName("tokenize") private external var global_tokenize: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("missionCompanionCard") private external var global_missionCompanionCard: dynamic
@JsName("V9Home") private external var global_V9Home: dynamic
@JsName("V9Plan") private external var global_V9Plan: dynamic
@JsName("mountPersistentCompanion") private external var global_mountPersistentCompanion: dynamic
@JsName("validate9") private external var global_validate9: dynamic
@JsName("NewExercises") private external var global_NewExercises: dynamic
@JsName("DemoLibrary") private external var global_DemoLibrary: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("IgrisArt") private external var global_IgrisArt: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("IgrisCharacter") private external var global_IgrisCharacter: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("closeCoach") private external var global_closeCoach: dynamic
@JsName("coachMotion") private external var global_coachMotion: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("AdaptiveV9") private external var global_AdaptiveV9: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("companion") private external var global_companion: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("TrainingSources") private external var global_TrainingSources: dynamic
@JsName("TrainingGuides") private external var global_TrainingGuides: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic

internal fun installV9() {
    global_releaseCompanion = fun(): dynamic {
        run {
            val previous21: dynamic = numeric(global_companionToken)
            global_companionToken = jsAdd(previous21, 1)
        }
        onPresent(global_activeCompanion) { value22 -> value22.destroy() }
        global_activeCompanion = dynamicValue(null)
        return absent
    }
    global_mountPanelCompanion = fun(type: dynamic): dynamic {
        if (truth((!truth(global_CompanionNames[type])))) {
            return absent
        }
        global_releaseCompanion()
        val host: dynamic = global_Dollar("#animatedCompanion")
        if (truth(host)) {
            global_activeCompanion = jsConstruct(global_IgrisCharacter, arrayOf<dynamic>(host, type, jsObject { obj29 ->
                obj29["reducedMotion"] = jsOr((global_state.profile.animate === false)) { global_matchMedia("(prefers-reduced-motion: reduce)").matches }
            }))
            global_activeCompanion["open"]()
        }
        global_Dollar("#companionName").textContent = global_CompanionNames[type]
        return absent
    }
    global_openAnimationGallery = fun(): dynamic {
        global_modal(("<h2>Cinco compañeros · muestras 2D</h2><p>Cada muestra reproduce entrada, espera y salida. En el panel, la espera continúa hasta cerrar.</p>" + textOf(global_Object.entries(global_CompanionNames).map((fun(arg57: dynamic): dynamic {
            var key: dynamic = absent
            var name: dynamic = absent
            val unpacked58: dynamic = fromIterable(arg57)
            key = unpacked58[0]
            name = unpacked58[1]
            return ("<details class=\"card\" ontoggle=\"HunterActions.togglePreview(this)\"><summary>" + textOf(name) + "</summary><img loading=\"lazy\" data-src=\"media/companion-" + textOf(name.toLowerCase().replace(" ", "-")) + ".gif\" alt=\"Entrada, espera y salida de " + textOf(name) + "\" style=\"max-height:520px;max-width:100%\"></details>")
        })).join("")) + ""))
        return absent
    }
    global_selectedCompanion = fun(): dynamic {
        return (if (truth(global_CompanionNames[onPresent(global_state.profile) { value71 -> value71.companionCharacter }])) global_state.profile.companionCharacter else "knight")
    }
    global_missionCompanionCard = fun(): dynamic {
        val type: dynamic = global_selectedCompanion()
        return ("<div class=\"mission-ai-presence\"><div id=\"missionCompanionArt\" class=\"mission-companion-host\" aria-hidden=\"true\"></div><div class=\"mission-ai-copy\"><span class=\"eyebrow\">IA DE MISIÓN</span><b>" + textOf(global_CompanionNames[type]) + "</b><small>La misma IA que elegiste en Perfil</small></div></div>")
    }
    global_mountPersistentCompanion = fun(host: dynamic, type: dynamic): dynamic {
        if (truth((!truth(host)))) {
            return null
        }
        val actor: dynamic = jsConstruct(global_IgrisCharacter, arrayOf<dynamic>(host, type, jsObject { obj86 ->
            obj86["reducedMotion"] = jsOr((global_state.profile.animate === false)) { global_matchMedia("(prefers-reduced-motion: reduce)").matches }
        }))
        actor["open"]()
        if (truth(global_Dollar("#modal")["open"])) {
            actor.pause()
        }
        return actor
    }
    global_NewExercises.forEach((fun(e: dynamic): dynamic {
        return run {
            val target1: dynamic = global_DemoLibrary
            val key2: dynamic = e.id
            val assigned4: dynamic = jsObject { obj3 ->
                obj3["file"] = e.id
            }
            target1[key2] = assigned4
            assigned4
        }
    }))
    global_V8UI = jsObject { obj5 ->
        obj5["profileForm"] = global_profileForm
        obj5["render"] = global_render
        obj5["openCoach"] = global_openCoach
        obj5["answerCoach"] = global_answerCoach
        obj5["modal"] = global_modal
        obj5["technique"] = global_technique
    }
    global_activeCompanion = dynamicValue(null)
    global_homeCompanion = dynamicValue(null)
    global_missionCompanion = dynamicValue(null)
    global_companionToken = 0
    global_coachContext = dynamicValue(null)
    global_CompanionNames = jsonValue("{\"knight\":\"IGRIS\",\"warrior\":\"IRON\",\"bear\":\"TANK\",\"insect\":\"BERU\",\"scientist\":\"DOKJA KIM\"}")
    global_profileForm = (fun(arg8: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg8) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V8UI.profileForm(initial)
        val current: dynamic = jsOr(onPresent(global_state.profile) { value11 -> value11.companionCharacter }) { "knight" }
        val legacy: dynamic = box.querySelector("[name=companionStyle]")
        val old: dynamic = onPresent(legacy) { value12 -> value12.closest("label") }
        if (truth(old)) {
            old.outerHTML = ("<fieldset class=\"companion-picker-block\"><legend>Inteligencia artificial acompañante</legend><p class=\"footnote\">Esta es la única sección donde puedes cambiar tu IA. La elección se aplica automáticamente al botón inferior, al panel de ayuda y a las misiones.</p><div class=\"companion-picker\">" + textOf(global_Object.entries(global_CompanionNames).map((fun(arg17: dynamic): dynamic {
                var key: dynamic = absent
                var name: dynamic = absent
                val unpacked18: dynamic = fromIterable(arg17)
                key = unpacked18[0]
                name = unpacked18[1]
                return ("<label class=\"companion-option\"><input type=\"radio\" name=\"companionCharacter\" value=\"" + textOf(key) + "\" " + textOf((if (truth((key === current))) "checked" else "")) + "><span class=\"companion-option-art\">" + textOf(global_IgrisArt.svg(key)) + "</span><b>" + textOf(name) + "</b><small>" + textOf((if (truth((key === current))) "IA seleccionada" else "Tocar para seleccionar")) + "</small></label>")
            })).join("")) + "</div><input type=\"hidden\" name=\"companionStyle\" value=\"knight\"></fieldset>")
        }
        return box.innerHTML
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_releaseCompanion()
        onPresent(global_homeCompanion) { value25 -> value25.pause() }
        onPresent(global_missionCompanion) { value26 -> value26.pause() }
        global_V8UI.modal(html)
        return absent
    })
    global_openCoach = (fun(): dynamic {
        global_V8UI.openCoach()
        val intro: dynamic = global_Dollar(".coach-intro")
        intro.querySelector("svg").remove()
        intro.insertAdjacentHTML("afterbegin", "<div id=\"animatedCompanion\" style=\"width:150px;height:220px;flex-shrink:0\"></div>")
        intro.style.animation = "none"
        val type: dynamic = global_selectedCompanion()
        intro.querySelector("h2").outerHTML = jsAdd(jsAdd("<h2 id=\"companionName\">", global_CompanionNames[type]), "</h2>")
        intro.insertAdjacentHTML("afterend", ("<p class=\"footnote\">Puedes cambiar de compañero desde Perfil.</p><button class=\"btn\" onclick=\"openAnimationGallery()\">Muestras animadas sin conexión</button>"))
        global_mountPanelCompanion(type)
        global_Dollar(".learning-card").innerHTML = ("<span class=\"eyebrow\">EVOLUCIÓN PERSONAL · V9</span><h3>Progresión por ejercicio</h3><p>La revisión semanal utiliza tres sesiones completas del mismo ejercicio y tu esfuerzo registrado. Conserva las sesiones iniciadas y los días de descanso.</p><p>" + textOf(global_esc(jsOr(onPresent(global_currentCycle().reviews) { value40 -> onPresent(value40.at((-numeric(1)))) { value41 -> value41.reason } }) { "Registra las series y el esfuerzo para empezar el seguimiento." })) + "</p><details><summary>Cómo funciona</summary><p>Clasificador local para objetivos escritos, estimador kNN de sesiones y reglas de progresión por ejercicio. El chat consulta tus datos y las fichas incluidas. No es un modelo de lenguaje generativo ni un diagnóstico médico.</p><p>Los umbrales son decisiones de diseño de la aplicación, no una validación clínica. No observa tu técnica. Dolor, fatiga y restricciones tienen prioridad.</p></details><button class=\"btn\" onclick=\"openBodyKnowledge()\">Biblioteca corporal</button>")
        return absent
    })
    global_closeCoach = (fun(): dynamic {
        return asyncAction asyncBody42@ {
            val token: dynamic = global_companionToken
            val actor: dynamic = global_activeCompanion
            if (truth(actor)) {
                val done: dynamic = awaitValue(actor.close())
                if (truth(jsOr((!truth(done))) { (token !== global_companionToken) })) {
                    return@asyncBody42 absent
                }
                global_releaseCompanion()
            }
            global_Dollar("#modal").close()
            onPresent(global_homeCompanion) { value45 -> value45.resume() }
            onPresent(global_missionCompanion) { value46 -> value46.resume() }
            onPresent(global_Dollar("#companion")) { value47 -> value47.focus(jsonValue("{\"preventScroll\":true}")) }
            absent
        }
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        if (truth(global_Dollar("#modal")["open"])) {
            return absent
        }
        global_releaseCompanion()
        onPresent(global_homeCompanion) { value48 -> value48.resume() }
        onPresent(global_missionCompanion) { value49 -> value49.resume() }
        return absent
    }))
    global_coachMotion = (fun(kind: dynamic): dynamic {
        onPresent(global_activeCompanion) { value52 -> value52.perform((if (truth((kind === "power"))) "celebrate" else kind)) }
        return absent
    })
    global_tokenize = (fun(s: dynamic): dynamic {
        return s.toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "").split(jsRegExp("\\W+", "")).filter((fun(w: dynamic): dynamic {
            return jsAnd(jsMore(w.length, 2)) { (!truth(dynamicValue(jsonValue("[\"como\",\"para\",\"hacer\",\"hago\",\"quiero\",\"una\",\"los\",\"las\",\"del\",\"que\",\"con\",\"ejercicio\"]")).includes(w))) }
        }))
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        val words: dynamic = global_tokenize(q)
        val plain: dynamic = words.join(" ")
        if (truth(dynamicValue(jsRegExp("dolor|lesion|molestia", "")).test(plain))) {
            return global_V8UI.answerCoach(q)
        }
        if (truth(dynamicValue(jsRegExp("progre|evolu|retador|intensi|aprend|modelo", "")).test(plain))) {
            val c: dynamic = global_currentCycle()
            val next: dynamic = c.days.find((fun(d: dynamic): dynamic {
                return jsAnd(jsAtLeast(d.date, global_E.dateKey())) { d.items.length }
            }))
            return jsAdd(jsAdd(jsOr(onPresent(c.reviews) { value61 -> onPresent(value61.at((-numeric(1)))) { value62 -> value62.reason } }) { "La primera revisión llega tras siete días." }, " "), (if (truth(next)) next.items.map((fun(x: dynamic): dynamic {
                val r: dynamic = global_AdaptiveV9.recommend(global_state.logs, x, global_state.profile, global_E.dateKey())
                return jsAdd(jsAdd(jsAdd(jsAdd(global_E.byId(x.id).name, ": "), r.samples), " registros comparables. "), r.reason)
            })).join("\n") else "No hay entrenamiento pendiente en este bloque."))
        }
        if (truth(dynamicValue(jsRegExp("hoy|siguiente", "")).test(plain))) {
            val d: dynamic = global_currentCycle().days.find((fun(d: dynamic): dynamic {
                return jsAnd(jsAtLeast(d.date, global_E.dateKey())) { d.items.length }
            }))
            return (if (truth(d)) ("Próxima sesión: " + textOf(d.date) + ". " + textOf(d.items.map((fun(x: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd(jsAdd(global_E.byId(x.id).name, " · "), x.sets), " × "), x.target)
            })).join("; ")) + ". Objetivo: " + textOf(jsOr(onPresent(global_GoalPlans.plans[global_state.profile.finalTarget]) { value63 -> value63.name }) { global_state.profile.goal }) + ".") else "No hay sesión pendiente.")
        }
        if (truth(jsAnd(global_coachContext) { dynamicValue(jsRegExp("alternativa|sustituir|otro", "")).test(plain) })) {
            val e: dynamic = global_E.byId(global_coachContext)
            val found: dynamic = global_E.exercises.filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd((x.variant === "base")) { (x.id !== e.id) }) { (x.group === e.group) }) { global_Adapt.allowed(x, global_state.profile) }
            })).slice(0, 4)
            return (if (truth(found.length)) jsAdd(jsAdd("Alternativas compatibles según tus restricciones: ", found.map((fun(x: dynamic): dynamic {
                return x.name
            })).join(", ")), ". Puedes elegirlas al editar la misión.") else "No encontré una alternativa compatible para esa zona con tus restricciones actuales.")
        }
        if (truth(dynamicValue(jsRegExp("objetivo|meta|plan|rutina|quiero", "")).test(q.toLowerCase()))) {
            return global_V8UI.answerCoach(q)
        }
        val candidates: dynamic = global_E.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).map((fun(e: dynamic): dynamic {
            return jsObject { obj67 ->
                obj67["e"] = e
                obj67["score"] = words.reduce((fun(sum: dynamic, w: dynamic): dynamic {
                    return jsAdd(sum, (if (truth(global_tokenize(e.name).some((fun(t: dynamic): dynamic {
                        return jsOr(t.startsWith(w)) { w.startsWith(t) }
                    })))) 2 else 0))
                }), 0)
            }
        })).filter((fun(r: dynamic): dynamic {
            return r.score
        })).sort((fun(a: dynamic, b: dynamic): dynamic {
            return jsSubtract(b.score, a.score)
        }))
        if (truth(candidates.length)) {
            if (truth((onPresent(candidates[1]) { value68 -> value68.score } === candidates[0].score))) {
                return jsAdd(jsAdd("¿Cuál de estos ejercicios quieres consultar? ", candidates.slice(0, 4).map((fun(r: dynamic): dynamic {
                    return r.e.name
                })).join(" · ")), ". Escribe el nombre completo.")
            }
            val e: dynamic = candidates[0].e
            global_coachContext = e.id
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(e.name, "\n"), e.instructions.map((fun(s: dynamic, i: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd(i, 1), ". "), s)
            })).join("\n")), "\n"), e.cue), "\n"), (if (truth(global_Adapt.allowed(e, global_state.profile))) "Compatible con los filtros actuales de tu perfil." else jsAdd("Restricciones: ", global_Adapt.reasons(e, global_state.profile).join(", ")))), ". Puedes preguntar por una alternativa.")
        }
        return global_V8UI.answerCoach(q)
    })
    global_V9Home = global_home
    global_V9Plan = global_plan
    global_home = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V9Home()
        val target: dynamic = box.querySelector(".mission-launch,.mission-feature")
        if (truth(jsAnd(target) { (!truth(target.querySelector("#missionCompanionArt"))) })) {
            target.insertAdjacentHTML("afterbegin", global_missionCompanionCard())
        }
        return box.innerHTML
    })
    global_plan = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V9Plan()
        val target: dynamic = box.querySelector(".mission-window")
        if (truth(jsAnd(target) { (!truth(target.querySelector("#missionCompanionArt"))) })) {
            target.insertAdjacentHTML("afterbegin", global_missionCompanionCard())
        }
        return box.innerHTML
    })
    global_companion = (fun(): dynamic {
        val type: dynamic = global_selectedCompanion()
        return ("<button id=\"companion\" class=\"companion\" onclick=\"openCoach()\" aria-label=\"Abrir asistente " + textOf(global_CompanionNames[type]) + "\"><div id=\"homeCompanionArt\"></div><span>" + textOf(global_CompanionNames[type]) + " · IA</span></button>")
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        onPresent(global_homeCompanion) { value89 -> value89.destroy() }
        onPresent(global_missionCompanion) { value90 -> value90.destroy() }
        global_homeCompanion = dynamicValue(null)
        global_missionCompanion = dynamicValue(null)
        global_V8UI.render()
        val type: dynamic = global_selectedCompanion()
        global_homeCompanion = global_mountPersistentCompanion(global_Dollar("#homeCompanionArt"), type)
        global_missionCompanion = global_mountPersistentCompanion(global_Dollar("#missionCompanionArt"), type)
        global_document.querySelectorAll(".brand small").forEach((fun(el: dynamic): dynamic {
            return run {
                val target91: dynamic = el
                val assigned93: dynamic = "EL DESPERTAR · V9.2"
                target91.textContent = assigned93
                assigned93
            }
        }))
        return absent
    })
    global_validate9 = global_validateBackup
    global_validateBackup = (fun(`data`: dynamic): dynamic {
        return jsAnd(global_validate9(`data`)) { dynamicValue(jsArray { array99 ->
            array99.push(`data`.profile)
            appendAll(array99, `data`.cycles.map((fun(c: dynamic): dynamic {
                return c.profile
            })))
        }).filter(global_Boolean).every((fun(p: dynamic): dynamic {
            return jsOr((p.companionCharacter === absent)) { global_Object.hasOwn(global_CompanionNames, p.companionCharacter) }
        })) }
    })
    global_TrainingSources.acsm2026 = jsonValue("{\"name\":\"ACSM · entrenamiento de fuerza, 2026\",\"url\":\"https://acsm.org/resistance-training-guidelines-update-2026/\"}")
    global_TrainingGuides.push(jsonValue("{\"title\":\"Progresar con registros, no solo con el calendario\",\"group\":\"Seguimiento\",\"body\":\"La revisión compara la ejecución declarada del mismo ejercicio. El aumento se aplica a repeticiones o tiempo, conservando las demás variables.\",\"points\":[\"Tres registros completos y esfuerzo de 7/10 o menos habilitan un incremento pequeño. Este umbral es una regla del producto.\",\"Las series marcadas sin cantidades reales no cuentan para esta progresión.\",\"Las indicaciones para adultos sanos no sustituyen la valoración de una lesión.\"],\"sources\":[\"acsm2026\"]}"))
    global_state.appVersion = 9
    global_render()
    global_syncNative()
}
