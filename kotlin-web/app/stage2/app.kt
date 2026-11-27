@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installapp

import hunter.browser.*

@JsName("E") private external var global_E: dynamic
@JsName("KEY") private external var global_KEY: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("filter") private external var global_filter: dynamic
@JsName("query") private external var global_query: dynamic
@JsName("timerEnd") private external var global_timerEnd: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("storageError") private external var global_storageError: dynamic
@JsName("pendingRestore46") private external var global_pendingRestore46: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("icons") private external var global_icons: dynamic
@JsName("icon") private external var global_icon: dynamic
@JsName("header") private external var global_header: dynamic
@JsName("nav") private external var global_nav: dynamic
@JsName("go") private external var global_go: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("kindLabel") private external var global_kindLabel: dynamic
@JsName("todayDay") private external var global_todayDay: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("blocked") private external var global_blocked: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("calendar") private external var global_calendar: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("dayDetail") private external var global_dayDetail: dynamic
@JsName("book") private external var global_book: dynamic
@JsName("exerciseCards") private external var global_exerciseCards: dynamic
@JsName("searchExercises") private external var global_searchExercises: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("getLog") private external var global_getLog: dynamic
@JsName("workout") private external var global_workout: dynamic
@JsName("changeSet") private external var global_changeSet: dynamic
@JsName("toggleSet") private external var global_toggleSet: dynamic
@JsName("feedback") private external var global_feedback: dynamic
@JsName("finish") private external var global_finish: dynamic
@JsName("stopPain") private external var global_stopPain: dynamic
@JsName("confirmPain") private external var global_confirmPain: dynamic
@JsName("editExercise") private external var global_editExercise: dynamic
@JsName("applyExercise") private external var global_applyExercise: dynamic
@JsName("startTimer") private external var global_startTimer: dynamic
@JsName("timerText") private external var global_timerText: dynamic
@JsName("updateClock") private external var global_updateClock: dynamic
@JsName("history") private external var global_history: dynamic
@JsName("profile") private external var global_profile: dynamic
@JsName("exportBackup") private external var global_exportBackup: dynamic
@JsName("importBackup") private external var global_importBackup: dynamic
@JsName("readFile") private external var global_readFile: dynamic
@JsName("pendingImport") private external var global_pendingImport: dynamic
@JsName("receiveBackup") private external var global_receiveBackup: dynamic
@JsName("confirmImport") private external var global_confirmImport: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("lastDate") private external var global_lastDate: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("localStorage") private external var global_localStorage: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("HunterKotlinScreens") private external var global_HunterKotlinScreens: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("FormData") private external var global_FormData: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Event") private external var global_Event: dynamic
@JsName("TrainingProgression19") private external var global_TrainingProgression19: dynamic
@JsName("setInterval") private external var global_setInterval: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Android") private external var global_Android: dynamic
@JsName("URL") private external var global_URL: dynamic
@JsName("Blob") private external var global_Blob: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Number") private external var global_Number: dynamic

internal fun installApp() {
    global_save = fun(): dynamic {
        return global_HunterKotlinScreens.persistState()
    }
    global_toast = fun(s: dynamic): dynamic {
        onPresent(global_Dollar(".toast")) { value5 -> value5.remove() }
        val t: dynamic = global_document.createElement("div")
        t.className = "toast"
        t.setAttribute("role", "status")
        t.textContent = s
        val host: dynamic = jsOr(global_Dollar("#modal[open]")) { global_document.body }
        if (truth((host.id === "modal"))) {
            t.classList.add("modal-toast372")
        }
        host.append(t)
        global_setTimeout((fun(): dynamic {
            return t.remove()
        }), 4200)
        return absent
    }
    global_icon = fun(n: dynamic): dynamic {
        return ("<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"1.6\" stroke-linecap=\"round\" stroke-linejoin=\"round\" aria-hidden=\"true\">" + textOf(global_icons[n]) + "</svg>")
    }
    global_header = fun(): dynamic {
        return "<header class=\"topbar\"><div class=\"brand\"><div class=\"logo\">⟡</div>HUNTER<span>SYSTEM</span></div><span class=\"badge\">● LOCAL</span></header>"
    }
    global_nav = fun(): dynamic {
        return ("<nav class=\"nav\" aria-label=\"Navegación principal\">" + textOf(dynamicValue(jsonValue("[[\"home\",\"Sistema\"],[\"plan\",\"Misiones\"],[\"book\",\"Ejercicios\"],[\"stats\",\"Registro\"],[\"profile\",\"Cazador\"]]")).map((fun(arg18: dynamic): dynamic {
            var id: dynamic = absent
            var label: dynamic = absent
            val unpacked19: dynamic = fromIterable(arg18)
            id = unpacked19[0]
            label = unpacked19[1]
            return ("<button onclick=\"go('" + textOf(id) + "')\" class=\"" + textOf((if (truth((global_tab === id))) "active" else "")) + "\" " + textOf((if (truth((global_tab === id))) "aria-current=\"page\"" else "")) + ">" + textOf(global_icon(id)) + "" + textOf(label) + "</button>")
        })).join("")) + "</nav>")
    }
    global_go = fun(t: dynamic): dynamic {
        global_tab = t
        global_render()
        global_window.scrollTo(0, 0)
        return absent
    }
    global_currentCycle = fun(): dynamic {
        if (truth(jsOr((!truth(global_state.profile))) { (!truth(global_E.eligible(global_state.profile))) })) {
            return null
        }
        val existing: dynamic = jsAnd((global_state.weeklyVersion === 10)) { global_state.cycles.some((fun(c: dynamic): dynamic {
            return jsAnd((c.start === global_Weekly.startOfWeek(global_E.dateKey()))) { c.activated }
        })) }
        val c: dynamic = global_Weekly.current(global_state)
        if (truth((!truth(existing)))) {
            global_save()
        }
        return c
    }
    global_todayDay = fun(): dynamic {
        return onPresent(global_currentCycle()) { value26 -> value26.days.find((fun(d: dynamic): dynamic {
            return (d.date === global_E.dateKey())
        })) }
    }
    global_render = fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        val app: dynamic = global_Dollar("#app")
        if (truth((!truth(global_state.profile)))) {
            app.innerHTML = ("<main class=\"app\">" + textOf(global_header()) + "" + textOf(global_profileForm(true)) + "</main>")
            return absent
        }
        global_currentCycle()
        val content: dynamic = dynamicValue(jsOr(jsObject { obj32 ->
            obj32["home"] = global_home
            obj32["plan"] = global_plan
            obj32["book"] = global_book
            obj32["stats"] = global_history
            obj32["profile"] = global_profile
            obj32["workout"] = global_workout
            obj32["monarch"] = (fun(): dynamic {
                return jsOr(onPresent(global_window.HunterMonarch3852) { value33 -> value33.view() }) { global_blocked() }
            })
        }[global_tab]) { global_home })()
        app.innerHTML = ("<main class=\"app\">" + textOf(global_header()) + "" + textOf((if (truth(global_storageError)) "<div class=\"notice warn\">No se pudo leer un registro local válido. Restaura tu copia desde Cazador antes de guardar nuevos datos.</div>" else "")) + "" + textOf(content) + "</main>" + textOf(global_nav()) + "")
        return absent
    }
    global_profileForm = fun(arg38: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg38) { false }
        val p: dynamic = jsOr(global_state.profile) { js("({})") }
        return ("<section class=\"intro\"><span class=\"eyebrow\">" + textOf((if (truth(initial)) "INICIALIZACIÓN DEL SISTEMA" else "FICHA DEL CAZADOR")) + "</span><h1>" + textOf((if (truth(initial)) "Tu próxima misión<br>empieza contigo." else "Configura tu camino.")) + "</h1><p class=\"muted\">Entrena en casa. Registra cada avance. Construye tu propio nivel.</p><form id=\"profileForm\" class=\"card\" onsubmit=\"submitProfile(event)\"><h3>" + textOf((if (truth(initial)) "Crea tu perfil" else "Datos y preferencias")) + "</h3>\n<div class=\"field\"><label class=\"label\" for=\"name\">Nombre de cazador</label><input id=\"name\" name=\"name\" maxlength=\"35\" required autocomplete=\"nickname\" value=\"" + textOf(global_esc(jsOr(p.name) { "" })) + "\" placeholder=\"Tu nombre\"></div>\n<div class=\"fields\"><div class=\"field\"><label class=\"label\" for=\"age\">Edad</label><input id=\"age\" name=\"age\" type=\"number\" required min=\"13\" max=\"100\" value=\"" + textOf(jsOr(p.age) { "" }) + "\" inputmode=\"numeric\" placeholder=\"Años\"></div><div class=\"field\"><label class=\"label\" for=\"height\">Estatura · cm</label><input id=\"height\" name=\"height\" type=\"number\" required min=\"100\" max=\"250\" value=\"" + textOf(jsOr(p.height) { "" }) + "\" inputmode=\"decimal\" step=\"0.1\" placeholder=\"cm\"></div></div>\n<div class=\"fields\"><div class=\"field\"><label class=\"label\" for=\"weight\">Peso corporal · kg</label><input id=\"weight\" name=\"weight\" type=\"number\" required min=\"30\" max=\"350\" step=\"0.1\" value=\"" + textOf(jsOr(p.weight) { "" }) + "\" inputmode=\"decimal\" placeholder=\"kg\"></div><div class=\"field\"><label class=\"label\" for=\"experience\">Experiencia</label><select id=\"experience\" name=\"experience\"><option value=\"beginner\" " + textOf((if (truth((p.experience === "beginner"))) "selected" else "")) + ">Estoy empezando</option><option value=\"regular\" " + textOf((if (truth((p.experience === "regular"))) "selected" else "")) + ">Ya entreno regularmente</option></select></div></div>\n<div class=\"field\"><label class=\"label\" for=\"equipment\">Equipo disponible</label><select id=\"equipment\" name=\"equipment\"><option value=\"body\" " + textOf((if (truth((p.equipment === "body"))) "selected" else "")) + ">Peso corporal y silla firme</option><option value=\"db\" " + textOf((if (truth((p.equipment === "db"))) "selected" else "")) + ">Mancuernas + peso corporal</option></select></div>\n<div class=\"fields\"><div class=\"field\"><label class=\"label\" for=\"load\">Mancuerna disponible · kg/unidad</label><input id=\"load\" name=\"load\" type=\"number\" min=\"0\" max=\"100\" step=\"0.25\" value=\"" + textOf(nullableOr(p.load) { 0 }) + "\" inputmode=\"decimal\"></div><div class=\"field\"><label class=\"label\" for=\"frequency\">Días de fuerza / semana</label><select id=\"frequency\" name=\"frequency\"><option value=\"2\" " + textOf((if (truth((p.frequency === 2))) "selected" else "")) + ">2 días</option><option value=\"3\" " + textOf((if (truth((p.frequency === 3))) "selected" else "")) + ">3 días</option></select></div></div>\n<div class=\"field\"><label class=\"row\"><input type=\"checkbox\" name=\"limits\" " + textOf((if (truth(p.limits)) "checked" else "")) + "><span>Tengo una lesión, dolor persistente, embarazo o una condición que requiere adaptar el ejercicio.</span></label></div>\n<div class=\"notice\">Peso y estatura quedan en tu ficha: no determinan por sí solos la carga adecuada. La rutina usa tu experiencia, equipo, frecuencia y respuesta a las sesiones. La carga la eliges tú, con control del movimiento.</div>\n<p class=\"footnote\">El generador de esta versión está destinado a personas de 18 a 64 años sin limitaciones declaradas. Para otros casos conserva la ficha, pero no prescribe una rutina automática. Si aparece dolor, detén la sesión.</p>\n" + textOf((if (truth(initial)) "" else "<div class=\"notice\">Al guardar cambios de entrenamiento, la misión pendiente de hoy se adapta. Las series realizadas permanecen registradas y las misiones completadas no se reabren.</div>")) + "\n<button class=\"btn primary full\" style=\"margin-top:20px\" type=\"submit\">" + textOf((if (truth(initial)) "DESPERTAR MI SISTEMA →" else "Guardar perfil")) + "</button></form><p class=\"footnote\">Diseño independiente inspirado en interfaces de fantasía y cazadores. Sin afiliación oficial a Solo Leveling. Datos guardados en este dispositivo.</p></section>")
    }
    global_submitProfile = fun(event: dynamic): dynamic {
        event.preventDefault()
        val f: dynamic = jsConstruct(global_FormData, arrayOf<dynamic>(event.target))
        val p: dynamic = jsObject { obj41 ->
            obj41["name"] = f["get"]("name").trim()
            obj41["age"] = numeric(f["get"]("age"))
            obj41["height"] = numeric(f["get"]("height"))
            obj41["weight"] = numeric(f["get"]("weight"))
            obj41["experience"] = f["get"]("experience")
            obj41["equipment"] = f["get"]("equipment")
            obj41["load"] = numeric(f["get"]("load"))
            obj41["frequency"] = numeric(f["get"]("frequency"))
            obj41["limits"] = f.has("limits")
        }
        if (truth((!truth(p.name)))) {
            return global_toast("Escribe tu nombre de cazador.")
        }
        if (truth(jsAnd((p.equipment === "db")) { jsAtMost(p.load, 0) })) {
            return global_toast("Indica el peso de una mancuerna disponible.")
        }
        global_state.profile = p
        if (truth(global_save())) {
            global_tab = "home"
            global_render()
        }
        return absent
    }
    global_blocked = fun(): dynamic {
        return "<div class=\"card\"><span class=\"eyebrow\">ADAPTACIÓN INDIVIDUAL</span><h2 style=\"margin-top:12px\">Tu perfil necesita una rutina personalizada.</h2><p class=\"muted\" style=\"margin-top:12px\">Esta versión no genera un plan automático para la edad o las limitaciones que indicaste. Consulta con un profesional que pueda evaluar tu caso. Puedes conservar tu ficha y consultar el registro anterior.</p><button class=\"btn\" style=\"margin-top:18px\" onclick=\"go('profile')\">Ver mi perfil</button></div>"
    }
    global_home = fun(): dynamic {
        val c: dynamic = global_currentCycle()
        val s: dynamic = global_E.stats(global_state.logs)
        if (truth((!truth(c)))) {
            return global_blocked()
        }
        val d: dynamic = global_todayDay()
        val l: dynamic = global_state.logs[onPresent(d) { value48 -> value48.date }]
        val day: dynamic = jsAdd(global_E.diff(global_E.dateKey(), c.start), 1)
        val rank: dynamic = dynamicValue(jsonValue("[\"E\",\"D\",\"C\",\"B\",\"A\",\"S\"]"))[global_Math.min(5, global_Math.floor(jsDivide(jsSubtract(s.level, 1), 5)))]
        return ("<div class=\"grid\"><section><div class=\"card hero\"><div class=\"portal\"></div><span class=\"eyebrow\">SISTEMA ACTIVADO · RANGO " + textOf(rank) + "</span><h1>Un día más.<br>Un nivel más.</h1><p>" + textOf(global_esc(global_state.profile.name)) + ", tu misión de hoy está lista. La constancia también incluye descansar.</p><button class=\"btn primary\" onclick=\"go('workout')\">" + textOf((if (truth(onPresent(l) { value49 -> value49.complete })) "VER MISIÓN COMPLETADA ✓" else "ABRIR MISIÓN DE HOY →")) + "</button></div><div class=\"metrics\"><div class=\"metric\"><strong>" + textOf(s.streak) + "<span style=\"font-size:16px;color:var(--cyan)\"> ↗</span></strong><small>Días de racha</small></div><div class=\"metric\"><strong>" + textOf(s.days) + "</strong><small>Misiones</small></div><div class=\"metric\"><strong>" + textOf(s.reps) + "</strong><small>Repeticiones</small></div></div><div class=\"section-title\"><h2>Misión diaria</h2><span class=\"badge\">" + textOf(global_kindLabel(d.kind)) + "</span></div><div class=\"card\"><div class=\"row between\"><span class=\"eyebrow\">DÍA " + textOf(day) + " / 21</span><small>" + textOf((if (truth((d.kind === "strength"))) "+100" else (if (truth((d.kind === "recovery"))) "+40" else "+20"))) + " XP al completar</small></div>" + textOf(jsOr(d.items.map((fun(item: dynamic): dynamic {
            val e: dynamic = global_E.byId(item.id)
            return ("<div class=\"quest\"><div class=\"glyph\">" + textOf((if (truth((e.equipment === "db"))) "⌁" else "◇")) + "</div><div class=\"grow\"><h3>" + textOf(e.name) + "</h3><small>" + textOf(item.sets) + " series × " + textOf(item.target) + " " + textOf((if (truth((global_E.unitOf(item) === "sec"))) "segundos" else "repeticiones")) + "" + textOf((if (truth(global_E.sidesOf(item))) " por lado" else "")) + "</small></div><span class=\"tag\">" + textOf(e.group) + "</span></div>")
        })).join("")) { "<div class=\"empty\">Hoy tu misión es recuperar.<br>El descanso también suma.</div>" }) + "</div>" + textOf(jsOr(onPresent(global_window.HunterMonarch3852) { value50 -> value50.tile() }) { "" }) + "</section><aside class=\"side stack\"><div class=\"card\"><div class=\"row between\"><span class=\"eyebrow\">ESTADO DEL CAZADOR</span><span class=\"badge\">RANGO " + textOf(rank) + "</span></div><div class=\"row\" style=\"margin-top:20px\"><div class=\"rank\">" + textOf(rank) + "</div><div><small>NIVEL</small><div class=\"level-big\">" + textOf(global_String(s.level).padStart(2, "0")) + "</div></div></div><div class=\"bar\"><i style=\"width:" + textOf(jsDivide(jsMod(s.xp, 300), 3)) + "%\"></i></div><small>" + textOf(jsMod(s.xp, 300)) + " / 300 XP al siguiente nivel</small><p class=\"footnote\">El nivel representa tus registros de constancia, no una evaluación física.</p></div><div class=\"card\"><span class=\"eyebrow\">ARCO " + textOf(jsAdd(c.index, 1)) + " · TRES SEMANAS</span><h2 style=\"margin-top:10px\">" + textOf(dynamicValue(jsonValue("[\"Despertar\",\"Consolidación\",\"Ascenso\"]"))[global_Math.min(2, c.index)]) + "</h2><p class=\"muted\" style=\"font-size:13px;margin-top:8px\">" + textOf(global_esc(c.decision)) + "</p>" + textOf(global_calendar(c, true)) + "<p class=\"footnote\">Borde claro: fuerza · ✓: misión completada</p></div></aside></div>")
    }
    global_calendar = fun(c: dynamic, arg53: dynamic): dynamic {
        var compact: dynamic = absent
        compact = definedOr(arg53) { false }
        return ("<div class=\"calendar\">" + textOf(c.days.map((fun(d: dynamic, i: dynamic): dynamic {
            return ("<button class=\"day " + textOf(d.kind) + " " + textOf((if (truth(onPresent(global_state.logs[d.date]) { value56 -> value56.complete })) "complete" else "")) + " " + textOf((if (truth((d.date === global_E.dateKey()))) "today" else "")) + "\" onclick=\"dayDetail('" + textOf(d.date) + "')\" aria-label=\"Día " + textOf(jsAdd(i, 1)) + ", " + textOf(d.date) + ", " + textOf(global_kindLabel(d.kind)) + "\"><b>" + textOf((if (truth(onPresent(global_state.logs[d.date]) { value57 -> value57.complete })) "✓" else jsAdd(i, 1))) + "</b><small>" + textOf((if (truth((d.kind === "strength"))) "FUE" else (if (truth((d.kind === "rest"))) "DES" else "REC"))) + "</small></button>")
        })).join("")) + "</div>")
    }
    global_plan = fun(): dynamic {
        val c: dynamic = global_currentCycle()
        if (truth((!truth(c)))) {
            return global_blocked()
        }
        return ("<span class=\"eyebrow\">TU CAMINO · CICLO " + textOf(jsAdd(c.index, 1)) + "</span><h1 style=\"margin:10px 0 20px\">21 días. Una misión a la vez.</h1><div class=\"card\"><div class=\"row between\"><h3>" + textOf(c.start) + " → " + textOf(global_E["plus"](c.start, 20)) + "</h3><span class=\"badge\">" + textOf(c.profile.frequency) + " días de fuerza</span></div>" + textOf(global_calendar(c)) + "<p class=\"footnote\">Toca un día para ver la rutina. Solo puedes registrar la fecha actual.</p></div><div class=\"notice\">" + textOf(global_esc(c.decision)) + " El ciclo cambia al abrir la app después de 21 días. No hay aumento por calendario: se evalúan las sesiones de fuerza completadas, el esfuerzo y el dolor registrados.</div>" + textOf(dynamicValue(jsonValue("[0,1,2]")).map((fun(w: dynamic): dynamic {
            return ("<div class=\"section-title\"><h2>Semana " + textOf(jsAdd(w, 1)) + "</h2><small>" + textOf((if (truth((w === 0))) "Practica" else (if (truth((w === 1))) "Repite con control" else "Consolida"))) + "</small></div><div class=\"card\">" + textOf(c.days.slice(jsMultiply(w, 7), jsAdd(jsMultiply(w, 7), 7)).map((fun(d: dynamic, i: dynamic): dynamic {
                return ("<div class=\"quest\"><div class=\"glyph\">" + textOf(jsAdd(jsAdd(jsMultiply(w, 7), i), 1)) + "</div><div class=\"grow\"><h3>" + textOf(global_kindLabel(d.kind)) + "</h3><small>" + textOf(d.date) + " · " + textOf((if (truth(d.items.length)) d.items.map((fun(x: dynamic): dynamic {
                    return global_E.byId(x.id).name
                })).join(" · ") else "Sin ejercicios programados")) + "</small></div><button class=\"btn ghost\" aria-label=\"Ver día " + textOf(jsAdd(jsAdd(jsMultiply(w, 7), i), 1)) + "\" onclick=\"dayDetail('" + textOf(d.date) + "')\">→</button></div>")
            })).join("")) + "</div>")
        })).join("")) + "<div class=\"notice\">La rotación entre ciclos cambia algunas variantes y mantiene patrones de movimiento. Las reglas de 21 días, XP y progresión son decisiones de diseño de esta app; no son una prescripción clínica.</div>")
    }
    global_modal = fun(html: dynamic): dynamic {
        global_ModalViewport.begin()
        val d: dynamic = global_Dollar("#modal")
        d.innerHTML = jsAdd(html, "<button class=\"btn ghost full\" onclick=\"document.querySelector('#modal').close()\">Cerrar</button>")
        if (truth((!truth(d["open"])))) {
            d.showModal()
        }
        global_ModalViewport.reset()
        return absent
    }
    global_dayDetail = fun(date: dynamic): dynamic {
        val d: dynamic = global_state.cycles.flatMap((fun(c: dynamic): dynamic {
            return c.days
        })).find((fun(d: dynamic): dynamic {
            return (d.date === date)
        }))
        if (truth((!truth(d)))) {
            return absent
        }
        global_modal(("<span class=\"eyebrow\">" + textOf(date) + "</span><h2>" + textOf(global_kindLabel(d.kind)) + "</h2>" + textOf(jsOr(d.items.map((fun(x: dynamic): dynamic {
            return ("<p style=\"margin:12px 0\">" + textOf(global_E.byId(x.id).name) + "<br><small>" + textOf(x.sets) + " × " + textOf(x.target) + " " + textOf((if (truth((global_E.unitOf(x) === "sec"))) "s" else "reps")) + "" + textOf((if (truth(global_E.sidesOf(x))) " por lado" else "")) + "</small></p>")
        })).join("")) { "<p>Descanso programado. Sin carga ni repeticiones.</p>" }) + "" + textOf((if (truth((date === global_E.dateKey()))) "<button class=\"btn primary full\" onclick=\"HunterActions.openWorkout()\">Abrir misión</button>" else "")) + ""))
        return absent
    }
    global_book = fun(): dynamic {
        val available: dynamic = global_E.exercises.filter((fun(e: dynamic): dynamic {
            return jsAnd(jsOr((global_filter === "all")) { (e.equipment === global_filter) }) { dynamicValue(jsAdd(jsAdd(e.name, " "), e.group)).toLowerCase().includes(global_query.toLowerCase()) }
        }))
        return ("<span class=\"eyebrow\">ARSENAL DE MOVIMIENTOS</span><h1 style=\"margin:10px 0\">Base de ejercicios</h1><p class=\"muted\">30 movimientos · técnica, equipo y alternativas</p><input class=\"library-search\" aria-label=\"Buscar ejercicio\" placeholder=\"Buscar por ejercicio o grupo muscular…\" value=\"" + textOf(global_esc(global_query)) + "\" oninput=\"searchExercises(this.value)\"><div class=\"filter\">" + textOf(dynamicValue(jsonValue("[[\"all\",\"Todos\"],[\"body\",\"Sin mancuernas\"],[\"db\",\"Mancuernas\"]]")).map((fun(arg70: dynamic): dynamic {
            var v: dynamic = absent
            var t: dynamic = absent
            val unpacked71: dynamic = fromIterable(arg70)
            v = unpacked71[0]
            t = unpacked71[1]
            return ("<button class=\"btn ghost " + textOf((if (truth((global_filter === v))) "selected" else "")) + "\" onclick=\"HunterActions.setFilter('" + textOf(v) + "')\">" + textOf(t) + "</button>")
        })).join("")) + "</div><div class=\"exercise-grid\" id=\"exerciseResults\">" + textOf(global_exerciseCards(available)) + "</div>")
    }
    global_exerciseCards = fun(list: dynamic): dynamic {
        return jsOr(list.map((fun(e: dynamic): dynamic {
            return ("<article class=\"card\"><span class=\"tag\">" + textOf(e.group) + " · " + textOf((if (truth((e.equipment === "db"))) "Mancuernas" else "Peso corporal / movilidad")) + "</span><h3>" + textOf(e.name) + "</h3><p>" + textOf(e.cue) + "</p><button class=\"btn ghost\" onclick=\"technique('" + textOf(e.id) + "')\">Ver técnica ↗</button></article>")
        })).join("")) { "<div class=\"empty\">Sin resultados.</div>" }
    }
    global_searchExercises = fun(v: dynamic): dynamic {
        global_query = v
        global_Dollar("#exerciseResults").innerHTML = global_exerciseCards(global_E.exercises.filter((fun(e: dynamic): dynamic {
            return jsAnd(jsOr((global_filter === "all")) { (e.equipment === global_filter) }) { dynamicValue(jsAdd(jsAdd(e.name, " "), e.group)).toLowerCase().includes(v.toLowerCase()) }
        })))
        return absent
    }
    global_technique = fun(id: dynamic): dynamic {
        val e: dynamic = global_E.byId(id)
        global_modal(("<span class=\"eyebrow\">" + textOf(e.group) + "</span><h2>" + textOf(e.name) + "</h2><p>" + textOf(e.steps) + "</p><div class=\"notice\">" + textOf(e.cue) + "</div><p class=\"muted\">Respira durante el movimiento. Elige un esfuerzo controlable y termina si aparece dolor.</p>" + textOf((if (truth(e.sides)) "<p class=\"footnote\">Una serie se registra después de completar ambos lados. Introduce las repeticiones por lado; el total del registro suma los dos.</p>" else "")) + ""))
        return absent
    }
    global_getLog = fun(): dynamic {
        val d: dynamic = global_todayDay()
        if (truth((!truth(d)))) {
            return null
        }
        if (truth((!truth(global_state.logs[d.date])))) {
            global_state.logs[d.date] = jsObject { obj84 ->
                obj84["kind"] = d.kind
                obj84["complete"] = false
                obj84["rpe"] = 0
                obj84["pain"] = false
                obj84["note"] = ""
                obj84["items"] = d.items.map((fun(x: dynamic): dynamic {
                    return jsObject { obj85 ->
                        copyFields(obj85, x)
                        obj85["actual"] = global_Array.from(jsObject { obj86 ->
                            obj86["length"] = x.sets
                        }, (fun(): dynamic {
                            return jsObject { obj87 ->
                                obj87["done"] = false
                                obj87["value"] = x.target
                                obj87["kg"] = 0
                            }
                        }))
                    }
                }))
            }
        }
        return global_state.logs[d.date]
    }
    global_workout = fun(): dynamic {
        val d: dynamic = global_todayDay()
        if (truth((!truth(d)))) {
            return global_blocked()
        }
        val l: dynamic = global_getLog()
        val done: dynamic = l.items.flatMap((fun(x: dynamic): dynamic {
            return x["actual"]
        })).filter((fun(x: dynamic): dynamic {
            return x.done
        })).length
        val total: dynamic = l.items.flatMap((fun(x: dynamic): dynamic {
            return x["actual"]
        })).length
        return ("<section class=\"workout\"><span class=\"eyebrow\">" + textOf(global_E.dateKey()) + " · " + textOf(global_kindLabel(d.kind)) + "</span><div class=\"section-title\"><h1>" + textOf((if (truth(l.complete)) "Misión completada." else "Tu misión de hoy.")) + "</h1><span class=\"badge\">" + textOf(done) + "/" + textOf(total) + " series</span></div>" + textOf((if (truth((d.kind === "strength"))) "<div class=\"notice\">Empieza con unos minutos de movimiento suave. Usa una carga cómoda, deja margen antes de agotarte y descansa entre series. El temporizador comienza en 90 segundos y puedes ajustarlo. En «kg» registra la carga total: suma ambas mancuernas si las usas a la vez.</div>" else "")) + "" + textOf((if (truth(l.pain)) "<div class=\"notice warn\">Has registrado dolor: sesión detenida. No continúes hoy. Si persiste o es intenso, busca valoración profesional.</div>" else "")) + "\n" + textOf((if (truth(l.complete)) "<div class=\"notice\">Tu progreso ya está guardado. Esta misión solo otorga XP una vez.</div>" else "")) + "\n<div class=\"timer\"><div><small>DESCANSO</small><br><b id=\"clock\">" + textOf(global_timerText()) + "</b></div><div class=\"row\"><button class=\"btn ghost\" onclick=\"startTimer(90)\">90 s</button><button class=\"btn ghost\" onclick=\"startTimer(120)\">120 s</button><button class=\"btn ghost\" onclick=\"HunterActions.clearTimer()\" aria-label=\"Detener temporizador\">×</button></div></div>\n" + textOf(l.items.map((fun(x: dynamic, idx: dynamic): dynamic {
            val e: dynamic = global_E.byId(x.id)
            return ("<article class=\"card\" " + textOf((if (truth(x.archivedProfile3829)) "data-profile-archived=\"true\"" else "")) + "><div class=\"subhead\"><div><span class=\"tag\">" + textOf(e.group) + "</span><h3>" + textOf(e.name) + "</h3></div><button class=\"btn ghost\" onclick=\"technique('" + textOf(e.id) + "')\" aria-label=\"Técnica de " + textOf(e.name) + "\">?</button></div><p class=\"muted\" style=\"font-size:12px\">Objetivo: " + textOf(x.sets) + " × " + textOf(x.target) + " " + textOf((if (truth((global_E.unitOf(x) === "sec"))) "segundos" else "repeticiones")) + "" + textOf((if (truth(global_E.sidesOf(x))) " por lado" else "")) + "</p><div class=\"setrow\"><small>#</small><small>" + textOf((if (truth((global_E.unitOf(x) === "sec"))) "Segundos" else jsAdd("Reps", (if (truth(global_E.sidesOf(x))) " / lado" else "")))) + "</small><small>Kg totales</small><small>Hecha</small></div>" + textOf(x["actual"].map((fun(a: dynamic, j: dynamic): dynamic {
                return ("<div class=\"setrow " + textOf((if (truth(a.done)) "done" else "")) + "\"><small>" + textOf(jsAdd(j, 1)) + "</small><input aria-label=\"" + textOf(e.name) + ", serie " + textOf(jsAdd(j, 1)) + ", cantidad\" id=\"v-" + textOf(idx) + "-" + textOf(j) + "\" type=\"number\" required min=\"1\" max=\"" + textOf((if (truth((global_E.unitOf(x) === "sec"))) 3600 else 200)) + "\" step=\"1\" value=\"" + textOf(a["value"]) + "\" onchange=\"changeSet(" + textOf(idx) + "," + textOf(j) + ")\" " + textOf((if (truth(jsOr(jsOr(l.complete) { l.pain }) { x.archivedProfile3829 })) "disabled" else "")) + "><input aria-label=\"" + textOf(e.name) + ", serie " + textOf(jsAdd(j, 1)) + ", kg\" id=\"k-" + textOf(idx) + "-" + textOf(j) + "\" type=\"number\" required min=\"0\" max=\"200\" step=\"0.25\" value=\"" + textOf(a.kg) + "\" onchange=\"changeSet(" + textOf(idx) + "," + textOf(j) + ")\" " + textOf((if (truth(jsOr(jsOr((e.equipment === "body")) { l.complete }) { l.pain })) "disabled" else "")) + "><button class=\"btn " + textOf((if (truth(a.done)) "" else "ghost")) + "\" aria-label=\"" + textOf((if (truth(a.done)) "Desmarcar" else "Completar")) + " " + textOf(e.name) + ", serie " + textOf(jsAdd(j, 1)) + "\" onclick=\"toggleSet(" + textOf(idx) + "," + textOf(j) + ")\" " + textOf((if (truth(jsOr(jsOr(l.complete) { l.pain }) { x.archivedProfile3829 })) "disabled" else "")) + ">" + textOf((if (truth(a.done)) "✓" else "○")) + "</button></div>")
            })).join("")) + "" + textOf((if (truth(jsAnd(jsAnd((!truth(l.complete))) { (!truth(l.pain)) }) { (!truth(x["actual"].some((fun(a: dynamic): dynamic {
                return a.done
            })))) })) ("<button class=\"btn ghost\" onclick=\"editExercise(" + textOf(idx) + ")\">Cambiar / ajustar</button>") else "")) + "</article>")
        })).join("")) + "\n" + textOf((if (truth((d.kind === "rest"))) "<div class=\"card\"><h2>Recuperar es parte del camino.</h2><p class=\"muted\" style=\"margin-top:10px\">Hoy no hay ejercicios programados. Confirma tu descanso para registrar la misión.</p></div>" else "")) + "\n<div class=\"card\"><h3>Cómo te fue</h3><label class=\"label\" style=\"margin-top:16px\" for=\"rpe\">Esfuerzo global (1 muy fácil · 10 máximo)</label><select id=\"rpe\" onchange=\"feedback()\" " + textOf((if (truth(jsOr(l.complete) { l.pain })) "disabled" else "")) + "><option value=\"0\">Selecciona tu esfuerzo</option>" + textOf(global_Array.from(jsonValue("{\"length\":10}"), (fun(_: dynamic, i: dynamic): dynamic {
            return ("<option value=\"" + textOf(jsAdd(i, 1)) + "\" " + textOf((if (truth((l.rpe === jsAdd(i, 1)))) "selected" else "")) + ">" + textOf(jsAdd(i, 1)) + " / 10</option>")
        })).join("")) + "</select><label class=\"label\" style=\"margin-top:14px\" for=\"note\">Notas de la sesión</label><textarea id=\"note\" rows=\"2\" maxlength=\"500\" onchange=\"feedback()\" placeholder=\"Ej.: No pude completar las flexiones arqueras. Me resultó difícil.\" " + textOf((if (truth(jsOr(l.complete) { l.pain })) "disabled" else "")) + ">" + textOf(global_esc(l.note)) + "</textarea>" + textOf((if (truth(jsAnd((!truth(l.complete))) { (!truth(l.pain)) })) ("<button class=\"btn primary full\" onclick=\"finish()\">" + textOf((if (truth((d.kind === "rest"))) "Registrar descanso" else "Completar misión")) + "</button><button class=\"btn ghost full\" onclick=\"HunterActions.reportProgress()\">Guardar avance parcial</button><button class=\"btn danger full\" onclick=\"stopPain()\">Sentí dolor · detener sesión</button>") else "")) + "</div><p class=\"footnote\">Las series se guardan al marcarlas. No se otorgan misiones retroactivas ni futuras.</p></section>")
    }
    global_changeSet = fun(i: dynamic, j: dynamic): dynamic {
        val l: dynamic = global_getLog()
        if (truth(jsOr(jsOr(l.complete) { l.pain }) { onPresent(l.items) { value92 -> onPresent(value92[i]) { value93 -> value93.archivedProfile3829 } } })) {
            return false
        }
        val a: dynamic = l.items[i]["actual"][j]
        val v: dynamic = global_Dollar(("#v-" + textOf(i) + "-" + textOf(j) + ""))
        val k: dynamic = global_Dollar(("#k-" + textOf(i) + "-" + textOf(j) + ""))
        if (truth(jsOr((!truth(v.checkValidity()))) { (!truth(k.checkValidity())) })) {
            global_toast("Revisa la cantidad y el peso: deben estar dentro del rango indicado.")
            return false
        }
        a["value"] = numeric(v["value"])
        a.kg = (if (truth((global_E.byId(l.items[i].id).equipment === "body"))) 0 else numeric(k["value"]))
        global_save()
        return true
    }
    global_toggleSet = fun(i: dynamic, j: dynamic): dynamic {
        if (truth((!truth(global_changeSet(i, j))))) {
            return absent
        }
        val l: dynamic = global_getLog()
        l.items[i]["actual"][j].done = (!truth(l.items[i]["actual"][j].done))
        global_save()
        if (truth(l.items[i]["actual"][j].done)) {
            global_startTimer(90)
        }
        global_render()
        return absent
    }
    global_feedback = fun(): dynamic {
        val l: dynamic = global_getLog()
        if (truth(l.complete)) {
            return absent
        }
        l.rpe = numeric(global_Dollar("#rpe")["value"])
        l.note = global_Dollar("#note")["value"].slice(0, 500)
        global_save()
        return absent
    }
    global_finish = fun(): dynamic {
        val l: dynamic = global_getLog()
        if (truth(jsOr(l.complete) { l.pain })) {
            return absent
        }
        global_feedback()
        if (truth(jsAnd((!truth(l.manual))) { l.items.some((fun(x: dynamic): dynamic {
            return jsAnd((!truth(x.selfReported))) { x["actual"].some((fun(a: dynamic): dynamic {
                return (!truth(a.done))
            })) }
        })) })) {
            return global_toast("Quedan series pendientes. Puedes guardar el avance parcial.")
        }
        if (truth(jsAnd((l.kind !== "rest")) { (!truth(l.rpe)) })) {
            return global_toast("Selecciona cómo sentiste el esfuerzo.")
        }
        l.complete = true
        l.completedAt = jsConstruct(global_Date, js("[]")).toISOString()
        if (truth(global_save())) {
            global_timerEnd = 0
            global_render()
            global_toast("MISIÓN COMPLETADA · Tu progreso está guardado.")
            global_window.dispatchEvent(jsConstruct(global_Event, arrayOf<dynamic>("hunter:mission-completed")))
        }
        return absent
    }
    global_stopPain = fun(): dynamic {
        global_modal("<h2>Detener la sesión</h2><p>Se conservarán las series realizadas y se registrará dolor para la revisión del siguiente ciclo. Hoy no se podrá continuar esta sesión.</p><button class=\"btn danger full\" onclick=\"confirmPain()\">Detener y guardar</button>")
        return absent
    }
    global_confirmPain = fun(): dynamic {
        global_feedback()
        val l: dynamic = global_getLog()
        l.pain = true
        global_save()
        global_timerEnd = 0
        global_Dollar("#modal").close()
        global_render()
        return absent
    }
    global_editExercise = fun(i: dynamic): dynamic {
        val l: dynamic = global_getLog()
        val x: dynamic = l.items[i]
        val e: dynamic = global_E.byId(x.id)
        val p: dynamic = global_currentCycle().profile
        val alternatives: dynamic = global_E.exercises.filter((fun(a: dynamic): dynamic {
            return jsAnd(jsAnd((a.group === e.group)) { jsOr((p.equipment === "db")) { (a.equipment === "body") } }) { jsAtMost(a.level, (if (truth((p.experience === "beginner"))) 1 else 2)) }
        }))
        global_modal(("<h2>Ajustar ejercicio</h2><p class=\"muted\">Solo cambia la misión de hoy. El historial anterior se conserva.</p><div class=\"field\"><label class=\"label\" for=\"swap\">Movimiento</label><select id=\"swap\">" + textOf(alternatives.map((fun(a: dynamic): dynamic {
            return ("<option value=\"" + textOf(a.id) + "\" " + textOf((if (truth((a.id === x.id))) "selected" else "")) + ">" + textOf(a.name) + "</option>")
        })).join("")) + "</select></div><div class=\"fields\"><div class=\"field\"><label class=\"label\" for=\"sets\">Series · 1 a 5</label><input id=\"sets\" required type=\"number\" value=\"" + textOf(x.sets) + "\" min=\"1\" max=\"5\" step=\"1\"></div><div class=\"field\"><label class=\"label\" for=\"target\">Reps o segundos · 1 a 200</label><input id=\"target\" required type=\"number\" value=\"" + textOf(x.target) + "\" min=\"1\" max=\"200\" step=\"1\"></div></div><button class=\"btn primary full\" onclick=\"applyExercise(" + textOf(i) + ")\">Aplicar a hoy</button>"))
        return absent
    }
    global_applyExercise = fun(i: dynamic): dynamic {
        if (truth(jsOr((!truth(global_Dollar("#sets").checkValidity()))) { (!truth(global_Dollar("#target").checkValidity())) })) {
            return global_toast("Revisa las series y repeticiones.")
        }
        val id: dynamic = global_Dollar("#swap")["value"]
        val sets: dynamic = numeric(global_Dollar("#sets")["value"])
        val target: dynamic = numeric(global_Dollar("#target")["value"])
        val x: dynamic = jsObject { obj124 ->
            obj124["id"] = id
            obj124["sets"] = sets
            obj124["target"] = target
            obj124["rest"] = (if (truth(global_window.TrainingProgression19)) global_TrainingProgression19.rest(global_state.profile) else 90)
        }
        global_todayDay().items[i] = x
        global_getLog().items[i] = jsObject { obj129 ->
            copyFields(obj129, x)
            obj129["actual"] = global_Array.from(jsObject { obj130 ->
                obj130["length"] = sets
            }, (fun(): dynamic {
                return jsObject { obj131 ->
                    obj131["done"] = false
                    obj131["value"] = target
                    obj131["kg"] = 0
                }
            }))
        }
        global_save()
        global_Dollar("#modal").close()
        global_render()
        return absent
    }
    global_startTimer = fun(sec: dynamic): dynamic {
        global_timerEnd = jsAdd(global_Date.now(), jsMultiply(sec, 1000))
        global_updateClock()
        return absent
    }
    global_timerText = fun(): dynamic {
        val s: dynamic = global_Math.max(0, global_Math.ceil(jsDivide(jsSubtract(global_timerEnd, global_Date.now()), 1000)))
        return ("" + textOf(global_String(global_Math.floor(jsDivide(s, 60))).padStart(2, "0")) + ":" + textOf(global_String(jsMod(s, 60)).padStart(2, "0")) + "")
    }
    global_updateClock = fun(): dynamic {
        if (truth(jsAnd(global_timerEnd) { jsAtLeast(global_Date.now(), global_timerEnd) })) {
            onPresent(global_window.HunterV27) { value138 -> value138.alarm.finish("rest") }
            global_timerEnd = 0
        }
        val clock: dynamic = global_Dollar("#clock")
        if (truth(clock)) {
            val `value`: dynamic = global_timerText()
            if (truth((clock.textContent !== `value`))) {
                clock.textContent = `value`
            }
        }
        return absent
    }
    global_history = fun(): dynamic {
        val s: dynamic = global_E.stats(global_state.logs)
        val entries: dynamic = global_Object.entries(global_state.logs).sort((fun(a: dynamic, b: dynamic): dynamic {
            return b[0].localeCompare(a[0])
        }))
        return ("<span class=\"eyebrow\">CADA AVANCE CUENTA</span><h1 style=\"margin:10px 0 20px\">Tu registro</h1><div class=\"metrics\"><div class=\"metric\"><strong>" + textOf(s.days) + "</strong><small>Días completados</small></div><div class=\"metric\"><strong>" + textOf(s.exerciseCount) + "</strong><small>Ejercicios registrados</small></div><div class=\"metric\"><strong>" + textOf(s.series) + "</strong><small>Series realizadas</small></div></div><div class=\"metrics\"><div class=\"metric\"><strong>" + textOf(s.reps) + "</strong><small>Repeticiones</small></div><div class=\"metric\"><strong>" + textOf(global_Math.floor(jsDivide(s.seconds, 60))) + "</strong><small>Minutos registrados</small></div><div class=\"metric\"><strong>" + textOf(s.strength) + "</strong><small>Días de fuerza</small></div></div><div class=\"card\"><h3>Últimos 7 días · series realizadas</h3><div class=\"chart\">" + textOf(global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, i: dynamic): dynamic {
            val d: dynamic = global_E["plus"](global_E.dateKey(), jsSubtract(i, 6))
            val l: dynamic = global_state.logs[d]
            val n: dynamic = jsOr(onPresent(l) { value146 -> value146.items.reduce((fun(a: dynamic, x: dynamic): dynamic {
                return jsAdd(a, x["actual"].filter((fun(s: dynamic): dynamic {
                    return s.done
                })).length)
            }), 0) }) { 0 }
            return ("<div><span>" + textOf(n) + "</span><i style=\"height:" + textOf(global_Math.min(65, jsMultiply(n, 4))) + "px\"></i>" + textOf(d.slice(8)) + "</div>")
        })).join("")) + "</div></div><div class=\"section-title\"><h2>Diario de misiones</h2><span class=\"badge\">" + textOf(s.xp) + " XP</span></div>" + textOf(jsOr(entries.map((fun(arg150: dynamic): dynamic {
            var date: dynamic = absent
            var l: dynamic = absent
            val unpacked151: dynamic = fromIterable(arg150)
            date = unpacked151[0]
            l = unpacked151[1]
            return ("<details class=\"card history-item\"><summary><div><b>" + textOf(date) + "</b><br><small>" + textOf(global_kindLabel(l.kind)) + "</small></div><span class=\"badge\">" + textOf((if (truth(l.complete)) "✓ Completada" else (if (truth(l.pain)) "Detenida" else "Parcial"))) + "</span></summary>" + textOf(l.items.map((fun(x: dynamic): dynamic {
                return ("<p><b>" + textOf(global_E.byId(x.id).name) + "</b><br>" + textOf(jsOr(x["actual"].filter((fun(a: dynamic): dynamic {
                    return a.done
                })).map((fun(a: dynamic): dynamic {
                    return ("" + textOf(a["value"]) + " " + textOf((if (truth((global_E.unitOf(x) === "sec"))) "s" else "reps")) + "" + textOf((if (truth(global_E.sidesOf(x))) " / lado" else "")) + "" + textOf((if (truth(a.kg)) jsAdd(jsAdd(" · ", a.kg), " kg") else "")) + "")
                })).join(" | ")) { "Sin series registradas" }) + "</p>")
            })).join("")) + "<p>Esfuerzo: " + textOf(jsOr(l.rpe) { "—" }) + "/10 " + textOf((if (truth(l.pain)) "· Dolor registrado" else "")) + "</p>" + textOf((if (truth(l.note)) ("<p>" + textOf(global_esc(l.note)) + "</p>") else "")) + "</details>")
        })).join("")) { "<div class=\"card empty\">Tu historia empieza con la primera serie.<br>Abre la misión de hoy para comenzar.</div>" }) + "<p class=\"footnote\">Los minutos suman únicamente los ejercicios medidos en segundos; no representan la duración total de la sesión. Un ejercicio registrado tiene al menos una serie marcada.</p>")
    }
    global_profile = fun(): dynamic {
        return ("" + textOf(global_profileForm()) + "<div class=\"intro\"><div class=\"card\"><h3>Tus datos, contigo</h3><p class=\"muted\" style=\"font-size:13px;margin-top:8px\">No hay cuenta ni sincronización. Exporta una copia para conservar tu progreso antes de desinstalar o cambiar de celular.</p><button class=\"btn full\" style=\"margin-top:15px\" onclick=\"exportBackup()\">Exportar copia de seguridad</button><button class=\"btn ghost full\" style=\"margin-top:10px\" onclick=\"importBackup()\">Restaurar copia</button><input type=\"file\" id=\"importFile\" accept=\"application/json,.json\" hidden onchange=\"readFile(this)\"></div><div class=\"card\"><h3>Criterios del sistema</h3><p class=\"footnote\">Rutinas generales de fuerza con recuperación intercalada. Las guías del CDC recomiendan actividad de fuerza al menos dos días a la semana; el NIDDK recomienda empezar de forma gradual. Este plan no cubre por sí solo toda la actividad aeróbica recomendada.</p><p class=\"footnote\">La regla propia de progresión requiere completar al menos el 80% de sesiones de fuerza y registrar un esfuerzo medio ≤7/10 sin dolor. Añade una repetición por ciclo hasta 12; con esfuerzo alto reduce el objetivo. No incrementa automáticamente los kg. Si faltan registros, mantiene la carga de trabajo.</p><p class=\"footnote\"><a href=\"https://www.cdc.gov/physical-activity-basics/guidelines/adults.html\" target=\"_blank\" rel=\"noopener\">CDC · Actividad física en adultos</a><br><a href=\"https://www.niddk.nih.gov/health-information/weight-management/staying-active-at-any-size\" target=\"_blank\" rel=\"noopener\">NIDDK · Empezar de forma gradual</a></p><p class=\"footnote\">Hunter System 1.0 · Propuesta general de entrenamiento, no evaluación médica. Peso y estatura no se convierten automáticamente en cargas o repeticiones.</p></div></div>")
    }
    global_exportBackup = fun(): dynamic {
        val json: dynamic = global_JSON.stringify(global_state, null, 2)
        if (truth(global_window.Android)) {
            global_Android.exportBackup(json)
            return absent
        }
        val a: dynamic = global_document.createElement("a")
        a.href = global_URL.createObjectURL(jsConstruct(global_Blob, arrayOf<dynamic>(arrayOf<dynamic>(json), jsonValue("{\"type\":\"application/json\"}"))))
        a.download = jsAdd(jsAdd("hunter-backup-", global_E.dateKey()), ".json")
        a.click()
        global_setTimeout((fun(): dynamic {
            return global_URL.revokeObjectURL(a.href)
        }), 3000)
        return absent
    }
    global_importBackup = fun(): dynamic {
        if (truth(global_window.Android)) {
            global_Android.importBackup()
            return absent
        }
        global_Dollar("#importFile").click()
        return absent
    }
    global_readFile = fun(input: dynamic): dynamic {
        val `file`: dynamic = input.files[0]
        if (truth((!truth(`file`)))) {
            return absent
        }
        if (truth(jsMore(`file`.size, 5000000))) {
            return global_toast("Copia demasiado grande. Máximo 5 MB.")
        }
        `file`.text()["then"](global_receiveBackup)["catch"]((fun(): dynamic {
            return global_toast("No se pudo leer el archivo.")
        }))
        return absent
    }
    global_receiveBackup = fun(raw: dynamic): dynamic {
        try {
            val candidate: dynamic = global_JSON.parse(raw)
            if (truth((!truth(global_validateBackup(candidate))))) {
                throw global_Error("schema")
            }
            global_pendingImport = candidate
            global_modal(("<h2>Restaurar progreso</h2><p>Copia de " + textOf(global_esc(jsOr(onPresent(candidate.profile) { value166 -> value166.name }) { "sin perfil" })) + ", con " + textOf(global_Object.keys(candidate.logs).length) + " días registrados. Reemplazará los datos actuales de este dispositivo.</p><button class=\"btn primary full\" onclick=\"confirmImport()\">Restaurar esta copia</button>"))
        } catch (e: dynamic) {
            global_toast("La copia no tiene un formato válido de Hunter System.")
        }
        return absent
    }
    global_confirmImport = fun(): dynamic {
        if (truth((!truth(global_pendingImport)))) {
            return absent
        }
        global_state = global_pendingImport
        global_pendingImport = dynamicValue(null)
        global_storageError = false
        global_save()
        global_Dollar("#modal").close()
        global_go("home")
        global_toast("Copia restaurada.")
        return absent
    }
    global_validateBackup = fun(s: dynamic): dynamic {
        try {
            var num: dynamic = absent
            var integer: dynamic = absent
            var prof: dynamic = absent
            prof = fun(p: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(p) { (jsTypeOf(p.name) === "string") }) { jsAtMost(p.name.length, 35) }) { integer(p.age, 13, 100) }) { num(p.height, 100, 250) }) { num(p.weight, 30, 350) }) { dynamicValue(jsonValue("[\"beginner\",\"regular\"]")).includes(p.experience) }) { dynamicValue(jsonValue("[\"body\",\"db\",\"gym\"]")).includes(p.equipment) }) { dynamicValue(jsonValue("[2,3]")).includes(p.frequency) }) { (jsTypeOf(p.limits) === "boolean") }) { num(p.load, 0, 100) }
            }
            num = (fun(v: dynamic, a: dynamic, b: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd((jsTypeOf(v) === "number")) { global_Number.isFinite(v) }) { jsAtLeast(v, a) }) { jsAtMost(v, b) }
            })
            integer = (fun(v: dynamic, a: dynamic, b: dynamic): dynamic {
                return jsAnd(global_Number.isInteger(v)) { num(v, a, b) }
            })
            val date: dynamic = (fun(d: dynamic): dynamic {
                return jsAnd(jsAnd((jsTypeOf(d) === "string")) { dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(d) }) { (global_E.dateKey(jsConstruct(global_Date, arrayOf<dynamic>(jsAdd(d, "T12:00:00")))) === d) }
            })
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(s))) { (s.version !== 1) }) { (!truth(s.logs)) }) { global_Array.isArray(s.logs) }) { (jsTypeOf(s.logs) !== "object") }) { (!truth(global_Array.isArray(s.cycles))) }) { jsMore(s.cycles.length, 1000) })) {
                return false
            }
            if (truth(jsAnd((s.profile !== null)) { (!truth(prof(s.profile))) })) {
                return false
            }
            val item: dynamic = (fun(x: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(x) { global_E.byId(x.id) }) { jsOr(jsOr((x.unit === absent)) { (x.unit === "sec") }) { (x.unit === global_E.byId(x.id).unit) } }) { jsOr((x.side === absent)) { dynamicValue(jsonValue("[\"left\",\"right\",\"both\"]")).includes(x.side) } }) { integer(x.sets, 1, 5) }) { integer(x.target, 1, (if (truth((global_E.unitOf(x) === "sec"))) 3600 else 300)) }
            })
            if (true) {
                val iterator173: dynamic = iteratorOf(s.cycles)
                while (true) {
                    val next174: dynamic = iterator173.next()
                    if (truth(next174.done)) break
                    var c: dynamic = absent
                    c = next174.value
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(integer(c.index, 0, 10000)))) { (!truth(date(c.start))) }) { (!truth(integer(c.reps, 6, 12))) }) { (!truth(integer(c.sets, 1, 5))) }) { (jsTypeOf(c.decision) !== "string") }) { jsMore(c.decision.length, 500) }) { (!truth(prof(c.profile))) }) { (!truth(global_Array.isArray(c.days))) }) { (!truth(dynamicValue(jsonValue("[7,21]")).includes(c.days.length))) })) {
                        return false
                    }
                    if (true) {
                        var i: dynamic = absent
                        i = 0
                        var firstIteration175 = true
                        while (true) {
                            if (!firstIteration175) {
                                run {
                                    val previous176: dynamic = numeric(i)
                                    i = jsAdd(previous176, 1)
                                }
                            }
                            firstIteration175 = false
                            if (!truth(jsLess(i, c.days.length))) break
                            val d: dynamic = c.days[i]
                            if (truth(jsOr(jsOr(jsOr(jsOr((d.date !== global_E["plus"](c.start, i))) { (!truth(dynamicValue(jsonValue("[\"strength\",\"recovery\",\"rest\",\"cardio\",\"mixed\"]")).includes(d.kind))) }) { (!truth(global_Array.isArray(d.items))) }) { jsMore(d.items.length, 96) }) { (!truth(d.items.every(item))) })) {
                                return false
                            }
                        }
                    }
                }
            }
            if (true) {
                val iterator177: dynamic = iteratorOf(global_Object.entries(s.logs))
                while (true) {
                    val next178: dynamic = iterator177.next()
                    if (truth(next178.done)) break
                    var k: dynamic = absent
                    var l: dynamic = absent
                    val unpacked179: dynamic = fromIterable(next178.value)
                    k = unpacked179[0]
                    l = unpacked179[1]
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(date(k)))) { (!truth(l)) }) { (!truth(dynamicValue(jsonValue("[\"strength\",\"recovery\",\"rest\",\"cardio\",\"mixed\"]")).includes(l.kind))) }) { (jsTypeOf(l.complete) !== "boolean") }) { (jsTypeOf(l.pain) !== "boolean") }) { (!truth(integer(l.rpe, 0, 10))) }) { (jsTypeOf(l.note) !== "string") }) { jsMore(l.note.length, 500) }) { (!truth(global_Array.isArray(l.items))) }) { jsMore(l.items.length, 96) })) {
                        return false
                    }
                    if (true) {
                        val iterator180: dynamic = iteratorOf(l.items)
                        while (true) {
                            val next181: dynamic = iterator180.next()
                            if (truth(next181.done)) break
                            var x: dynamic = absent
                            x = next181.value
                            if (truth(jsOr(jsOr((!truth(item(x)))) { (!truth(global_Array.isArray(x["actual"]))) }) { (x["actual"].length !== x.sets) })) {
                                return false
                            }
                            if (true) {
                                val iterator182: dynamic = iteratorOf(x["actual"])
                                while (true) {
                                    val next183: dynamic = iterator182.next()
                                    if (truth(next183.done)) break
                                    var a: dynamic = absent
                                    a = next183.value
                                    if (truth(jsOr(jsOr((jsTypeOf(a.done) !== "boolean")) { (!truth(integer(a["value"], (if (truth(a.done)) 1 else 0), (if (truth((global_E.unitOf(x) === "sec"))) 3600 else 300)))) }) { (!truth(num(a.kg, 0, 200))) })) {
                                        return false
                                    }
                                }
                            }
                        }
                    }
                    if (truth(jsAnd(l.complete) { jsOr(l.pain) { jsAnd((!truth(l.manual))) { l.items.some((fun(x: dynamic): dynamic {
                        return jsAnd((!truth(x.selfReported))) { x["actual"].some((fun(a: dynamic): dynamic {
                            return (!truth(a.done))
                        })) }
                    })) } } })) {
                        return false
                    }
                }
            }
            return true
        } catch (e: dynamic) {
            return false
        }
        return absent
    }
    global_E = global_Engine
    global_KEY = "hunter.system.v1"
    global_state = jsonValue("{\"version\":1,\"profile\":null,\"cycles\":[],\"logs\":{}}")
    global_tab = "home"
    global_filter = "all"
    global_query = ""
    global_timerEnd = 0
    global_Dollar = (fun(s: dynamic): dynamic {
        return global_document.querySelector(s)
    })
    global_esc = (fun(s: dynamic): dynamic {
        return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
            return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
        }))
    })
    global_storageError = false
    global_pendingRestore46 = dynamicValue(null)
    try {
        val raw: dynamic = global_localStorage.getItem(global_KEY)
        if (truth(raw)) {
            val parsed: dynamic = global_JSON.parse(raw)
            if (truth(global_validateBackup(parsed))) {
                global_state = parsed
            } else {
                global_pendingRestore46 = parsed
                global_storageError = true
            }
        }
    } catch (e: dynamic) {
        global_storageError = true
    }
    global_icons = jsonValue("{\"home\":\"<path d=\\\"m3 11 9-8 9 8v10h-6v-7H9v7H3z\\\"/>\",\"plan\":\"<rect x=\\\"3\\\" y=\\\"5\\\" width=\\\"18\\\" height=\\\"16\\\" rx=\\\"2\\\"/><path d=\\\"M7 3v4m10-4v4M3 11h18m-12 5h3m3 0h3\\\"/>\",\"book\":\"<path d=\\\"M12 5c-3-3-7-2-10-1v16c3-1 7-2 10 1 3-3 7-2 10-1V4c-3-1-7-2-10 1zm0 0v16\\\"/>\",\"stats\":\"<path d=\\\"M4 20V10m8 10V4m8 16v-7M2 22h20\\\"/>\",\"profile\":\"<circle cx=\\\"12\\\" cy=\\\"7\\\" r=\\\"4\\\"/><path d=\\\"M4 22v-3a8 8 0 0 1 16 0v3\\\"/>\"}")
    global_kindLabel = (fun(k: dynamic): dynamic {
        return jsonValue("{\"strength\":\"Fuerza\",\"recovery\":\"Recuperación activa\",\"rest\":\"Descanso\",\"cardio\":\"Cardio\",\"mixed\":\"Fuerza y cardio\"}")[k]
    })
    global_setInterval(global_updateClock, 500)
    global_pendingImport = dynamicValue(null)
    global_lastDate = global_E.dateKey()
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        if (truth(jsAnd((!truth(global_document.hidden))) { (global_E.dateKey() !== global_lastDate) })) {
            global_lastDate = global_E.dateKey()
            global_render()
        }
        return absent
    }))
    global_setInterval((fun(): dynamic {
        if (truth((global_E.dateKey() !== global_lastDate))) {
            global_lastDate = global_E.dateKey()
            global_render()
        }
        return absent
    }), 30000)
}
