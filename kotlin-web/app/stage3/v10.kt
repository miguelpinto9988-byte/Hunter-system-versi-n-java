@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv10

import hunter.browser.*

@JsName("V10UI") private external var global_V10UI: dynamic
@JsName("calendarMonth") private external var global_calendarMonth: dynamic
@JsName("calendarYear") private external var global_calendarYear: dynamic
@JsName("routineMode") private external var global_routineMode: dynamic
@JsName("creatorFilter") private external var global_creatorFilter: dynamic
@JsName("feedBusy") private external var global_feedBusy: dynamic
@JsName("cycleBeforeCreatorPlanning") private external var global_cycleBeforeCreatorPlanning: dynamic
@JsName("setCreatorPlanning") private external var global_setCreatorPlanning: dynamic
@JsName("weeklyLanguage") private external var global_weeklyLanguage: dynamic
@JsName("monthPanel") private external var global_monthPanel: dynamic
@JsName("changeMonth") private external var global_changeMonth: dynamic
@JsName("openMonthlyCalendar") private external var global_openMonthlyCalendar: dynamic
@JsName("monthDayDetail") private external var global_monthDayDetail: dynamic
@JsName("weeklyProgressCard") private external var global_weeklyProgressCard: dynamic
@JsName("openCompleteRoutines") private external var global_openCompleteRoutines: dynamic
@JsName("routineModes") private external var global_routineModes: dynamic
@JsName("allRoutines10") private external var global_allRoutines10: dynamic
@JsName("creatorVideoCards") private external var global_creatorVideoCards: dynamic
@JsName("openCreatorSources") private external var global_openCreatorSources: dynamic
@JsName("feedStatusText") private external var global_feedStatusText: dynamic
@JsName("loadCreatorCache") private external var global_loadCreatorCache: dynamic
@JsName("setCreatorSync") private external var global_setCreatorSync: dynamic
@JsName("refreshCreatorFeed") private external var global_refreshCreatorFeed: dynamic
@JsName("creatorVideoDetail") private external var global_creatorVideoDetail: dynamic
@JsName("pendingCreatorRoutine") private external var global_pendingCreatorRoutine: dynamic
@JsName("readCreatorRoutine") private external var global_readCreatorRoutine: dynamic
@JsName("confirmCreatorRoutine") private external var global_confirmCreatorRoutine: dynamic
@JsName("sourceSequenceCard") private external var global_sourceSequenceCard: dynamic
@JsName("sourceTimer") private external var global_sourceTimer: dynamic
@JsName("updateSourceClock") private external var global_updateSourceClock: dynamic
@JsName("validate10") private external var global_validate10: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("routineDetail") private external var global_routineDetail: dynamic
@JsName("applyRoutine") private external var global_applyRoutine: dynamic
@JsName("workout") private external var global_workout: dynamic
@JsName("missionWindow") private external var global_missionWindow: dynamic
@JsName("editMission") private external var global_editMission: dynamic
@JsName("editExercise") private external var global_editExercise: dynamic
@JsName("toggleSet") private external var global_toggleSet: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("CreatorFeed") private external var global_CreatorFeed: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("NodeFilter") private external var global_NodeFilter: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("dayByDate") private external var global_dayByDate: dynamic
@JsName("dayDetail") private external var global_dayDetail: dynamic
@JsName("kindLabel") private external var global_kindLabel: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("todayDay") private external var global_todayDay: dynamic
@JsName("blocked") private external var global_blocked: dynamic
@JsName("missionCompanionCard") private external var global_missionCompanionCard: dynamic
@JsName("addCompanionAndInjuryContext") private external var global_addCompanionAndInjuryContext: dynamic
@JsName("go") private external var global_go: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Android") private external var global_Android: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("console") private external var global_console: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("pendingRoutine") private external var global_pendingRoutine: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("routineNotice372") private external var global_routineNotice372: dynamic
@JsName("timerText") private external var global_timerText: dynamic
@JsName("startTimer") private external var global_startTimer: dynamic
@JsName("setInterval") private external var global_setInterval: dynamic
@JsName("detailedMode") private external var global_detailedMode: dynamic
@JsName("getLog") private external var global_getLog: dynamic
@JsName("timerEnd") private external var global_timerEnd: dynamic
@JsName("updateClock") private external var global_updateClock: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic

internal fun installV10() {
    global_setCreatorPlanning = fun(enabled: dynamic): dynamic {
        global_state.creatorAuto = (!truth((!truth(enabled))))
        val c: dynamic = global_cycleBeforeCreatorPlanning()
        if (truth(c)) {
            c.creatorOrganized = false
        }
        global_save()
        global_currentCycle()
        global_openCreatorSources()
        return absent
    }
    global_weeklyLanguage = fun(root: dynamic): dynamic {
        val w: dynamic = global_document.createTreeWalker(root, global_NodeFilter.SHOW_TEXT)
        while (truth(w.nextNode())) {
            w.currentNode.nodeValue = w.currentNode.nodeValue.replace(jsRegExp("Ciclo de 21 días", "g"), "Semana de 7 días").replace(jsRegExp("cada 21 días", "g"), "cada semana").replace(jsRegExp("después de 21 días", "g"), "al comenzar la siguiente semana").replace(jsRegExp("21 días", "g"), "7 días").replace(jsRegExp("TRES SEMANAS", "g"), "UNA SEMANA").replace(jsRegExp("tres semanas", "g"), "una semana").replace(jsRegExp("\\/ 21\\b", "g"), "/ 7")
        }
        return root
    }
    global_monthPanel = fun(): dynamic {
        val m: dynamic = global_Weekly.month(global_calendarYear, global_calendarMonth)
        val title: dynamic = jsConstruct(global_Date, arrayOf<dynamic>(global_calendarYear, global_calendarMonth, 1)).toLocaleDateString("es", jsonValue("{\"month\":\"long\",\"year\":\"numeric\"}"))
        val days: dynamic = global_state.cycles.flatMap((fun(c: dynamic): dynamic {
            return c.days
        }))
        val today: dynamic = global_E.dateKey()
        val week: dynamic = global_Schedule.defaults(global_state.profile)
        return ("<section class=\"card month-panel\"><div class=\"row between\"><button class=\"calendar-arrow\" onclick=\"changeMonth(-1)\" aria-label=\"Mes anterior\">‹</button><div><h3>" + textOf(global_esc(title)) + "</h3><small>" + textOf(m.count) + " días · revisión cada semana</small></div><button class=\"calendar-arrow\" onclick=\"changeMonth(1)\" aria-label=\"Mes siguiente\">›</button></div><div class=\"month-grid\">" + textOf(dynamicValue(jsonValue("[\"L\",\"M\",\"X\",\"J\",\"V\",\"S\",\"D\"]")).map((fun(n: dynamic, i: dynamic): dynamic {
            return ("<span class=\"month-weekday\" aria-label=\"" + textOf(global_Schedule.dayNames[jsMod(jsAdd(i, 1), 7)]) + "\">" + textOf(n) + "</span>")
        })).join("")) + "" + textOf(global_Array.from(jsObject { obj51 ->
            obj51["length"] = m.offset
        }, (fun(): dynamic {
            return "<span aria-hidden=\"true\"></span>"
        })).join("")) + "" + textOf(m.dates.map((fun(date: dynamic): dynamic {
            val d: dynamic = days.find((fun(d: dynamic): dynamic {
                return (d.date === date)
            }))
            val complete: dynamic = onPresent(global_state.logs[date]) { value53 -> value53.complete }
            val rest: dynamic = (if (truth(d)) (d.kind === "rest") else (week[global_Schedule.weekday(date)] === "rest"))
            return ("<button class=\"month-day " + textOf((if (truth((date === today))) "today" else "")) + " " + textOf((if (truth(complete)) "complete" else "")) + " " + textOf((if (truth(rest)) "rest" else "train")) + "\" data-date=\"" + textOf(date) + "\" onclick=\"monthDayDetail('" + textOf(date) + "')\" aria-label=\"" + textOf(date) + ", " + textOf((if (truth(complete)) "completado" else (if (truth(rest)) "descanso" else "entrenamiento"))) + "\" " + textOf((if (truth((date === today))) "aria-current=\"date\"" else "")) + "><b>" + textOf(global_Number(date.slice((-numeric(2))))) + "</b><small>" + textOf((if (truth(complete)) "✓" else (if (truth(rest)) "—" else "•"))) + "</small></button>")
        })).join("")) + "</div><p class=\"footnote\">• entrenamiento · — descanso · ✓ completado. Las semanas futuras son previsiones y se revisan con tus registros.</p></section>")
    }
    global_changeMonth = fun(delta: dynamic): dynamic {
        val d: dynamic = jsConstruct(global_Date, arrayOf<dynamic>(global_calendarYear, jsAdd(global_calendarMonth, delta), 1))
        global_calendarMonth = d.getMonth()
        global_calendarYear = d.getFullYear()
        if (truth((global_tab === "plan"))) {
            global_render()
        } else {
            global_openMonthlyCalendar()
        }
        return absent
    }
    global_openMonthlyCalendar = fun(): dynamic {
        global_modal(global_monthPanel())
        return absent
    }
    global_monthDayDetail = fun(date: dynamic): dynamic {
        val existing: dynamic = global_dayByDate(date)
        if (truth(existing)) {
            global_dayDetail(date)
            return absent
        }
        val start: dynamic = global_Weekly.startOfWeek(date)
        val c: dynamic = global_E.makeCycle(global_state.profile, start, jsAdd(global_currentCycle().index, 1), null, global_state.logs)
        val d: dynamic = c.days.find((fun(d: dynamic): dynamic {
            return (d.date === date)
        }))
        global_modal(("<span class=\"eyebrow\">" + textOf(date) + " · PREVISIÓN</span><h2>" + textOf(global_esc(jsOr(d.title) { global_kindLabel(d.kind) })) + "</h2><p>La selección se revisará al comenzar esa semana. Esta vista no crea registros.</p>" + textOf(jsOr(d.items.map((fun(x: dynamic): dynamic {
            return ("<div class=\"quest\"><div class=\"grow\"><b>" + textOf(global_esc(global_E.byId(x.id).name)) + "</b><small>" + textOf(x.sets) + " × " + textOf(x.target) + " " + textOf((if (truth((global_E.unitOf(x) === "sec"))) "s" else "reps")) + " · descanso " + textOf(x.rest) + "s</small></div><button class=\"text-button\" onclick=\"technique('" + textOf(x.id) + "')\" aria-label=\"Ver técnica\">↗</button></div>")
        })).join("")) { "<p>Descanso o seguimiento según tus restricciones.</p>" }) + ""))
        return absent
    }
    global_weeklyProgressCard = fun(): dynamic {
        val c: dynamic = global_currentCycle()
        val days: dynamic = c.days.filter((fun(d: dynamic): dynamic {
            return jsAnd(d.items.length) { jsAtLeast(d.date, jsOr(c.activeFrom) { c.start }) }
        }))
        val complete: dynamic = days.filter((fun(d: dynamic): dynamic {
            return onPresent(global_state.logs[d.date]) { value62 -> value62.complete }
        })).length
        val pct: dynamic = (if (truth(days.length)) global_Math.round(jsMultiply(jsDivide(complete, days.length), 100)) else 0)
        val s: dynamic = global_E.stats(global_state.logs)
        return ("<section class=\"card weekly-progression\"><div class=\"row between\"><span class=\"eyebrow\">PROGRESO DE LA SEMANA</span><span class=\"badge\">" + textOf(pct) + "%</span></div><h3>" + textOf(complete) + " de " + textOf(days.length) + " días de ejercicio</h3><div class=\"bar\"><i style=\"width:" + textOf(pct) + "%\"></i></div><p>" + textOf(global_esc(jsOr(onPresent(c.reviews) { value63 -> onPresent(value63.at((-numeric(1)))) { value64 -> value64.reason } }) { "Completa tus misiones y registra el esfuerzo. La siguiente revisión será el lunes." })) + "</p><div class=\"row between\"><small>" + textOf(s.xp) + " XP acumulada</small><small>Nivel " + textOf(s.level) + " · " + textOf(jsMod(s.xp, 300)) + "/300 XP</small></div><button class=\"btn ghost\" onclick=\"openMonthlyCalendar()\">Ver calendario mensual</button></section>")
    }
    global_openCompleteRoutines = fun(): dynamic {
        global_routineMode = "complete"
        global_go("routines")
        return absent
    }
    global_routineModes = fun(): dynamic {
        return ("<div class=\"segmented routine-mode\"><button class=\"" + textOf((if (truth((global_routineMode === "challenges"))) "selected" else "")) + "\" aria-pressed=\"" + textOf((global_routineMode === "challenges")) + "\" onclick=\"HunterActions.showChallenges()\">Desafíos diarios</button><button class=\"" + textOf((if (truth((global_routineMode === "complete"))) "selected" else "")) + "\" aria-pressed=\"" + textOf((global_routineMode === "complete")) + "\" onclick=\"HunterActions.showCompleteRoutines()\">Rutinas completas</button></div>")
    }
    global_creatorVideoCards = fun(): dynamic {
        val sources: dynamic = global_state.creatorData.channels.filter((fun(c: dynamic): dynamic {
            return jsOr((global_creatorFilter === "all")) { (c.key === global_creatorFilter) }
        }))
        val videos: dynamic = sources.flatMap((fun(c: dynamic): dynamic {
            return c.videos.map((fun(v: dynamic): dynamic {
                return jsObject { obj90 ->
                    copyFields(obj90, v)
                    obj90["creator"] = c.name
                }
            }))
        })).sort((fun(a: dynamic, b: dynamic): dynamic {
            return b.published.localeCompare(a.published)
        }))
        return jsOr(videos.map((fun(v: dynamic): dynamic {
            return ("<article class=\"card source-video\"><span class=\"eyebrow\">" + textOf(global_esc(v.creator)) + "</span><h3>" + textOf(global_esc(v.title)) + "</h3><small>" + textOf((if (truth(v.published)) jsConstruct(global_Date, arrayOf<dynamic>(v.published)).toLocaleDateString("es") else "Fecha no disponible")) + " · " + textOf((if (truth(v.chapters.length)) ("" + textOf(v.chapters.length) + " capítulos publicados") else "Sin secuencia verificada")) + "</small><div class=\"action-pair\"><a class=\"btn\" href=\"" + textOf(v.url) + "\" target=\"_blank\" rel=\"noopener\">Ver video original ↗</a><button class=\"btn ghost\" onclick=\"creatorVideoDetail('" + textOf(v.id) + "')\">Ver información</button></div></article>")
        })).join("")) { "<div class=\"empty\">Todavía no hay publicaciones disponibles. Puedes abrir los canales originales o volver a sincronizar.</div>" }
    }
    global_openCreatorSources = fun(): dynamic {
        global_modal(("<section data-companion-static=\"true\"><span class=\"eyebrow\">RUTINAS DE ENTRENADORES</span><h2>Entrena con tus referentes.</h2><p>Se conserva el video original. Las secuencias con ejercicios, cantidades y descansos verificados se pueden asignar sin modificarlas; las demás quedan como referencias.</p><div class=\"row wrap\"><button class=\"btn primary\" id=\"feedRefresh\" onclick=\"refreshCreatorFeed(true)\">" + textOf((if (truth(global_feedBusy)) "Buscando…" else "Buscar nuevas publicaciones")) + "</button><label class=\"row\"><input type=\"checkbox\" " + textOf((if (truth(global_state.creatorSync)) "checked" else "")) + " onchange=\"setCreatorSync(this.checked)\"> Actualización automática</label></div><p id=\"feedStatus\" class=\"footnote\">" + textOf(global_feedStatusText()) + "</p><label class=\"row\"><input type=\"checkbox\" " + textOf((if (truth(global_state.creatorAuto)) "checked" else "")) + " onchange=\"setCreatorPlanning(this.checked)\"> Organizar mis secuencias verificadas cada semana</label><p class=\"footnote\">" + textOf(global_state.creatorRoutines.length) + " secuencias importadas. Se usan solo días pendientes compatibles con el tipo de entrenamiento. Ante dolor, fatiga o perfiles de dosis reducida se conserva el generador adaptado. No se alteran las cantidades de la fuente.</p><div class=\"creator-channels\">" + textOf(global_state.creatorData.channels.map((fun(c: dynamic): dynamic {
            return ("<article class=\"source-channel\"><b>" + textOf(global_esc(c.name)) + "</b><small>" + textOf(c.videos.length) + " referencias guardadas · " + textOf((if (truth((c.status === "ok"))) "fuente consultada" else "sin conexión reciente")) + "</small><a href=\"" + textOf(c.url) + "\" target=\"_blank\" rel=\"noopener\">Abrir canal ↗</a></article>")
        })).join("")) + "</div><label>Filtrar entrenador<select onchange=\"HunterActions.filterCreators(this)\">" + textOf(global_options(jsObject { obj93 ->
            obj93["all"] = "Todos"
            copyFields(obj93, global_Object.fromEntries(global_CreatorFeed.channels.map((fun(c: dynamic): dynamic {
                return arrayOf<dynamic>(c.key, c.name)
            }))))
        }, global_creatorFilter)) + "</select></label><div class=\"stack\" id=\"creatorVideos\">" + textOf(global_creatorVideoCards()) + "</div><details class=\"card\"><summary>Añadir una secuencia verificada</summary><p>Importa un archivo JSON con el orden original, los ejercicios del catálogo, sus cantidades y descansos. Revisa la fuente antes de confirmar. Un título o un capítulo no certifica una rutina completa.</p><input type=\"file\" accept=\"application/json,.json\" onchange=\"readCreatorRoutine(this)\"></details></section>"))
        return absent
    }
    global_feedStatusText = fun(): dynamic {
        return (if (truth(global_feedBusy)) "Buscando en los canales públicos…" else (if (truth(onPresent(global_window.Android) { value96 -> value96.syncCreators })) "Búsqueda al abrir la app y aproximadamente cada 24 horas; Android puede retrasarla. Los videos requieren Internet." else "Vista previa: referencias guardadas. La actualización automática se ejecuta en el APK."))
    }
    global_loadCreatorCache = fun(): dynamic {
        if (truth((!truth(onPresent(global_window.Android) { value99 -> value99.creatorCache })))) {
            return absent
        }
        try {
            global_state.creatorSync = global_Android.creatorSyncEnabled()
            global_state.creatorData = global_CreatorFeed.merge(global_state.creatorData, global_JSON.parse(global_Android.creatorCache()))
            global_save()
            global_refreshCreatorFeed(false)
        } catch (e: dynamic) {
            global_console.warn("No se pudo leer la caché de fuentes")
        }
        return absent
    }
    global_setCreatorSync = fun(enabled: dynamic): dynamic {
        global_state.creatorSync = (!truth((!truth(enabled))))
        onPresent(global_window.Android) { value108 -> run { val owner110: dynamic = value108; onPresent(owner110.setCreatorSync) { method109 -> invokeWith(method109, owner110, arrayOf<dynamic>(global_state.creatorSync)) } } }
        global_save()
        if (truth(enabled)) {
            global_refreshCreatorFeed(false)
        }
        return absent
    }
    global_refreshCreatorFeed = fun(arg113: dynamic): dynamic {
        var force: dynamic = absent
        force = definedOr(arg113) { false }
        if (truth((!truth(global_state.creatorSync)))) {
            return global_toast("Activa la actualización automática para consultar los canales.")
        }
        if (truth((!truth(onPresent(global_window.Android) { value114 -> value114.syncCreators })))) {
            return global_toast("La sincronización está disponible en el APK de Android.")
        }
        if (truth(global_feedBusy)) {
            return absent
        }
        global_feedBusy = true
        if (truth(global_Dollar("#feedStatus"))) {
            global_Dollar("#feedStatus").textContent = global_feedStatusText()
        }
        global_Android.syncCreators(force)
        global_setTimeout((fun(): dynamic {
            if (truth(global_feedBusy)) {
                global_feedBusy = false
                if (truth(global_Dollar("#feedStatus"))) {
                    global_Dollar("#feedStatus").textContent = "Consulta pendiente o sin conexión. Las referencias guardadas siguen disponibles."
                }
            }
            return absent
        }), 90000)
        return absent
    }
    global_creatorVideoDetail = fun(id: dynamic): dynamic {
        val v: dynamic = global_state.creatorData.channels.flatMap((fun(c: dynamic): dynamic {
            return c.videos
        })).find((fun(v: dynamic): dynamic {
            return (v.id === id)
        }))
        if (truth((!truth(v)))) {
            return absent
        }
        global_modal(("<section data-companion-static=\"true\"><h2>" + textOf(global_esc(v.title)) + "</h2><a class=\"btn primary\" href=\"" + textOf(v.url) + "\" target=\"_blank\" rel=\"noopener\">Abrir el entrenamiento original ↗</a>" + textOf((if (truth(v.chapters.length)) jsAdd("<h3>Capítulos que publicó el entrenador</h3>", v.chapters.map((fun(c: dynamic): dynamic {
            return ("<p><b>" + textOf(global_Math.floor(jsDivide(c.at, 60))) + ":" + textOf(global_String(jsMod(c.at, 60)).padStart(2, "0")) + "</b> " + textOf(global_esc(c.label)) + "</p>")
        })).join("")) else "<p>El canal no aporta aquí una secuencia completa de ejercicios y descansos.</p>")) + "<p class=\"footnote\">Los capítulos no se convierten automáticamente en dosis de entrenamiento. Revisa la secuencia original para importarla.</p></section>"))
        return absent
    }
    global_readCreatorRoutine = fun(input: dynamic): dynamic {
        return asyncAction asyncBody129@ {
            val f: dynamic = onPresent(input.files) { value132 -> value132[0] }
            if (truth((!truth(f)))) {
                return@asyncBody129 absent
            }
            if (truth(jsMore(f.size, 1000000))) {
                return@asyncBody129 global_toast("Archivo demasiado grande.")
            }
            try {
                val r: dynamic = global_JSON.parse(awaitValue(f.text()))
                if (truth((!truth(global_CreatorFeed.validateRoutine(r))))) {
                    throw global_Error()
                }
                global_pendingCreatorRoutine = r
                global_modal(("<h2>Revisar secuencia original</h2><p>" + textOf(global_esc(r.name)) + "</p><p>" + textOf(global_esc(r.verificationNote)) + "</p><a href=\"https://www.youtube.com/watch?v=" + textOf(r.videoId) + "\" target=\"_blank\" rel=\"noopener\">Consultar fuente ↗</a><div class=\"stack\">" + textOf(r.steps.map((fun(s: dynamic): dynamic {
                    return ("<div class=\"quest\"><b>" + textOf((if (truth((s.kind === "exercise"))) global_esc(global_E.byId(s.exerciseId).name) else global_esc(s.label))) + "</b><small>" + textOf((if (truth((s.kind === "exercise"))) ("" + textOf(s.sets) + " × " + textOf(s.target) + " · descanso " + textOf(s.restSeconds) + "s") else ("" + textOf(s.seconds) + "s"))) + "</small></div>")
                })).join("")) + "</div><button class=\"btn primary full\" onclick=\"confirmCreatorRoutine()\">Confirmar que reproduce la fuente</button>"))
            } catch (e: dynamic) {
                global_toast("Secuencia no válida: faltan fuente, verificación, ejercicios o descansos.")
            }
            absent
        }
    }
    global_confirmCreatorRoutine = fun(): dynamic {
        if (truth((!truth(global_pendingCreatorRoutine)))) {
            return absent
        }
        if (truth(jsAtLeast(global_state.creatorRoutines.length, 500))) {
            return global_toast("Máximo 500 secuencias importadas.")
        }
        global_state.creatorRoutines = global_state.creatorRoutines.filter((fun(r: dynamic): dynamic {
            return (r.videoId !== global_pendingCreatorRoutine.videoId)
        }))
        global_state.creatorRoutines.push(global_pendingCreatorRoutine)
        global_pendingCreatorRoutine = dynamicValue(null)
        val c: dynamic = global_cycleBeforeCreatorPlanning()
        if (truth(c)) {
            c.creatorOrganized = false
        }
        global_save()
        global_currentCycle()
        global_Dollar("#modal").close()
        global_openCompleteRoutines()
        return absent
    }
    global_sourceSequenceCard = fun(r: dynamic, arg155: dynamic): dynamic {
        var assigned: dynamic = absent
        assigned = definedOr(arg155) { false }
        if (truth((!truth(global_CreatorFeed.validateRoutine(r))))) {
            return ""
        }
        return ("<section class=\"card source-sequence\" data-companion-static=\"true\"><span class=\"eyebrow\">ORDEN DOCUMENTADO · CANTIDADES CONSERVADAS</span><h3>" + textOf(global_esc(r.name)) + "</h3><p>" + textOf(global_esc(r.verificationNote)) + "</p><ol>" + textOf(r.steps.map((fun(s: dynamic): dynamic {
            if (truth((s.kind === "exercise"))) {
                val e: dynamic = global_E.byId(s.exerciseId)
                return ("<li><b>" + textOf(global_esc(e.name)) + "</b><small>" + textOf(s.sets) + " × " + textOf(s.target) + " " + textOf((if (truth((jsOr(s.unit) { e.unit } === "sec"))) "segundos" else "repeticiones")) + "" + textOf((if (truth((s.side === "left"))) " · lado izquierdo" else (if (truth((s.side === "right"))) " · lado derecho" else (if (truth(e.sides)) " por lado" else "")))) + " · " + textOf((if (truth(s.restSeconds)) jsAdd(jsAdd("descanso ", s.restSeconds), " s entre series") else "sin pausa entre series")) + "</small><button class=\"btn ghost\" onclick=\"technique('" + textOf(e.id) + "')\">Demostración con volumen</button>" + textOf((if (truth(jsAnd(assigned) { s.restSeconds })) ("<button class=\"btn ghost\" onclick=\"sourceTimer(" + textOf(s.restSeconds) + ")\">Descansar " + textOf(s.restSeconds) + " s</button>") else "")) + "</li>")
            }
            return ("<li><b>" + textOf(global_esc(s.label)) + "</b><small>" + textOf(s.seconds) + " segundos · " + textOf(jsonValue("{\"rest\":\"Descanso\",\"preparation\":\"Preparación\",\"cooldown\":\"Vuelta a la calma\"}")[s.kind]) + "</small>" + textOf((if (truth(assigned)) ("<button class=\"btn ghost\" onclick=\"sourceTimer(" + textOf(s.seconds) + ")\">Temporizar " + textOf(s.seconds) + " s</button>") else "")) + "</li>")
        })).join("")) + "</ol>" + textOf((if (truth(assigned)) jsAdd(jsAdd("<div class=\"row between\"><output id=\"sourceCountdown\" aria-live=\"off\">", global_timerText()), "</output><button class=\"btn ghost\" onclick=\"HunterActions.clearSourceTimer()\">Detener reloj</button></div><p class=\"footnote\">El reloj no marca ejercicios como realizados. Confirma lo que terminaste en las casillas de misión.</p>") else "")) + "<a href=\"https://www.youtube.com/watch?v=" + textOf(r.videoId) + "\" target=\"_blank\" rel=\"noopener\">Video original ↗</a></section>")
    }
    global_sourceTimer = fun(seconds: dynamic): dynamic {
        val d: dynamic = global_todayDay()
        if (truth(jsOr(jsOr((!truth(d))) { onPresent(global_state.logs[d.date]) { value158 -> value158.pain } }) { (!truth(global_Adapt.mode(global_state.profile).auto)) })) {
            return global_toast("Revisa tu estado antes de continuar la sesión.")
        }
        global_startTimer(seconds)
        global_updateSourceClock()
        return absent
    }
    global_updateSourceClock = fun(): dynamic {
        if (truth(global_Dollar("#sourceCountdown"))) {
            global_Dollar("#sourceCountdown").textContent = global_timerText()
        }
        return absent
    }
    global_V10UI = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["home"] = global_home
        obj1["plan"] = global_plan
        obj1["routines"] = global_routines
        obj1["render"] = global_render
        obj1["openCoach"] = global_openCoach
        obj1["answerCoach"] = global_answerCoach
        obj1["validateBackup"] = global_validateBackup
        obj1["routineDetail"] = global_routineDetail
        obj1["applyRoutine"] = global_applyRoutine
        obj1["workout"] = global_workout
        obj1["missionWindow"] = global_missionWindow
        obj1["editMission"] = global_editMission
        obj1["editExercise"] = global_editExercise
        obj1["toggleSet"] = global_toggleSet
    }
    global_calendarMonth = jsConstruct(global_Date, js("[]")).getMonth()
    global_calendarYear = jsConstruct(global_Date, js("[]")).getFullYear()
    global_routineMode = "complete"
    global_creatorFilter = "all"
    global_feedBusy = false
    global_state.creatorData = global_CreatorFeed.normalize(jsOr(jsOr(global_state.creatorData) { global_window.CreatorSeed }) { js("({})") })
    global_state.creatorRoutines = (if (truth(global_Array.isArray(global_state.creatorRoutines))) global_state.creatorRoutines.filter(global_CreatorFeed.validateRoutine) else js("[]"))
    global_state.creatorSync = (global_state.creatorSync !== false)
    global_state.creatorAuto = (global_state.creatorAuto === true)
    global_cycleBeforeCreatorPlanning = global_currentCycle
    global_currentCycle = (fun(): dynamic {
        val c: dynamic = global_cycleBeforeCreatorPlanning()
        if (truth(jsAnd(jsAnd(c) { global_state.creatorAuto }) { (!truth(c.creatorOrganized)) })) {
            val count: dynamic = global_CreatorFeed.assignWeek(c, global_state.profile, global_state.creatorRoutines, global_state.logs, global_E.dateKey())
            c.creatorOrganized = true
            c.creatorAssignments = count
            global_save()
        }
        return c
    })
    global_profileForm = (fun(arg28: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg28) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V10UI.profileForm(initial)
        val minutes: dynamic = box.querySelector("[name=minutes]")
        onPresent(minutes) { value31 -> onPresent(value31.closest(".field")) { value32 -> value32.remove() } }
        box.querySelectorAll("h3").forEach((fun(x: dynamic): dynamic {
            if (truth((x.textContent === "Objetivo y disponibilidad"))) {
                x.textContent = "Objetivo y equipo"
            }
            return absent
        }))
        val `field`: dynamic = box.querySelector(".companion-picker-block")
        val fields: dynamic = onPresent(`field`) { value35 -> value35.parentElement }
        if (truth(jsAnd(`field`) { onPresent(fields) { value36 -> value36.classList.contains("fields") } })) {
            fields.after(`field`)
        }
        if (truth(`field`)) {
            `field`.querySelector("legend").textContent = "Tu compañero"
            `field`.querySelector(".footnote").textContent = "Elige aquí el personaje de Inicio, Misiones y el asistente. Guarda el perfil para aplicar el cambio."
            `field`.querySelectorAll(".companion-option").forEach((fun(el: dynamic): dynamic {
                el.dataset.companionStatic = "true"
                val key: dynamic = el.querySelector("input")["value"]
                el.querySelector("small").textContent = (if (truth((key === global_selectedCompanion()))) "Seleccionado" else jsonValue("{\"knight\":\"Caballero · Igris\",\"insect\":\"Insecto · Beru\",\"bear\":\"Oso · Tank\",\"warrior\":\"Hacha y escudo · Iron\",\"scientist\":\"Chaqueta blanca · Dokja Kim\"}")[key])
                return absent
            }))
        }
        onPresent(box.querySelector(".week-block")) { value46 -> value46.insertAdjacentHTML("beforeend", "<p class=\"footnote\">Tu plan se renueva cada lunes. El calendario muestra todos los días del mes, incluidos los de descanso.</p>") }
        onPresent(box.querySelector("form")) { value47 -> value47.insertAdjacentHTML("beforeend", ("<details class=\"card creator-settings\"><summary>Actualizaciones de entrenadores</summary><p>Consulta publicaciones públicas de los cinco canales. Tu perfil y tus lesiones no se envían.</p><label class=\"row\"><input type=\"checkbox\" " + textOf((if (truth(global_state.creatorSync)) "checked" else "")) + " onchange=\"setCreatorSync(this.checked)\"> Buscar nuevas publicaciones automáticamente</label><button class=\"btn ghost\" type=\"button\" onclick=\"openCreatorSources()\">Ver canales y rutinas</button></details>")) }
        return global_weeklyLanguage(box).innerHTML
    })
    global_home = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V10UI.home()
        return global_weeklyLanguage(box).innerHTML
    })
    global_plan = (fun(): dynamic {
        val c: dynamic = global_currentCycle()
        val d: dynamic = global_todayDay()
        if (truth(jsOr((!truth(c))) { (!truth(d)) })) {
            return global_blocked()
        }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = ("<div class=\"page-heading\"><span class=\"eyebrow\">TU SISTEMA · SEMANA " + textOf(jsAdd(c.index, 1)) + "</span><h1>Una misión a la vez.</h1><p class=\"muted\">" + textOf(c.start) + " → " + textOf(global_E["plus"](c.start, 6)) + "</p></div><div class=\"segmented routine-mode\"><button class=\"selected\" aria-pressed=\"true\">Desafíos diarios</button><button onclick=\"openCompleteRoutines()\" aria-pressed=\"false\">Rutinas completas</button></div>" + textOf(global_missionWindow(d)) + "" + textOf(global_weeklyProgressCard()) + "" + textOf(global_monthPanel()) + "")
        onPresent(box.querySelector(".mission-window")) { value73 -> value73.insertAdjacentHTML("afterbegin", global_missionCompanionCard()) }
        global_addCompanionAndInjuryContext(box)
        return box.innerHTML
    })
    global_allRoutines10 = global_allRoutines
    global_allRoutines = (fun(): dynamic {
        return jsArray { array80 ->
            appendAll(array80, global_allRoutines10())
            appendAll(array80, global_state.creatorRoutines.filter(global_CreatorFeed.validateRoutine).map(global_CreatorFeed.asRoutine))
        }
    })
    global_routines = (fun(): dynamic {
        if (truth((global_routineMode === "challenges"))) {
            return ("<div class=\"page-heading\"><span class=\"eyebrow\">ENTRENAMIENTO DIARIO</span><h1>Centro de rutinas</h1></div>" + textOf(global_routineModes()) + "" + textOf(global_missionWindow(global_todayDay())) + "<button class=\"btn ghost full\" onclick=\"go('plan')\">Ver toda mi semana</button>")
        }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V10UI.routines()
        onPresent(box.querySelector(".page-heading")) { value85 -> value85.after(global_document.createRange().createContextualFragment(global_routineModes())) }
        onPresent(box.querySelector(".page-heading")) { value86 -> value86.insertAdjacentHTML("afterend", "<button class=\"academy-banner creator-link\" onclick=\"openCreatorSources()\"><span>ENTRENADORES · YOUTUBE</span><b>Videos originales y nuevas publicaciones ↗</b><small>Cinco canales · secuencias verificadas sin cambios</small></button>") }
        return box.innerHTML
    })
    global_window.onCreatorFeed = (fun(raw: dynamic): dynamic {
        global_feedBusy = false
        try {
            global_state.creatorData = global_CreatorFeed.merge(global_state.creatorData, global_JSON.parse(raw))
            global_save()
            if (truth(global_Dollar("#creatorVideos"))) {
                val scroll: dynamic = global_Dollar("#modal").scrollTop
                global_openCreatorSources()
                global_Dollar("#modal").scrollTop = scroll
            }
        } catch (e: dynamic) {
            global_toast("No se pudo actualizar la fuente. Se conserva lo guardado.")
        }
        return absent
    })
    global_pendingCreatorRoutine = dynamicValue(null)
    global_routineDetail = (fun(id: dynamic): dynamic {
        val r: dynamic = global_allRoutines().find((fun(r: dynamic): dynamic {
            return (r.id === id)
        }))
        global_V10UI.routineDetail(id)
        if (truth(onPresent(r) { value141 -> value141.locked })) {
            global_Dollar("#modal").insertAdjacentHTML("beforeend", jsAdd(global_sourceSequenceCard(r.sourceSequence), "<p class=\"footnote\">La rutina se excluye si contradice tus restricciones. Conserva ejercicios, cantidades, descansos y orden; no recibe aumentos automáticos.</p>"))
        }
        return absent
    })
    global_applyRoutine = (fun(): dynamic {
        val r: dynamic = jsAnd(global_pendingRoutine) { global_allRoutines().find((fun(r: dynamic): dynamic {
            return (r.id === global_pendingRoutine.id)
        })) }
        val date: dynamic = onPresent(global_pendingRoutine) { value144 -> value144.date }
        if (truth(jsAnd(r) { (!truth(global_Adapt.routineCompatible(r, global_GoalPlans.adjusted(global_state.profile)))) })) {
            return global_routineNotice372("Esta rutina no es compatible con las restricciones actuales.")
        }
        if (truth(jsAnd(date) { jsOr(global_Weekly.active(global_state.logs[date])) { onPresent(global_dayByDate(date)) { value145 -> onPresent(value145.tasks) { value146 -> value146.some((fun(t: dynamic): dynamic {
            return t.done
        })) } } } })) {
            return global_routineNotice372("Esta misión ya tiene actividad. Regresa a la ficha y elige otro día. Se conserva su registro.")
        }
        global_V10UI.applyRoutine()
        if (truth(jsAnd(onPresent(r) { value147 -> value147.locked }) { (!truth(global_pendingRoutine)) })) {
            val d: dynamic = global_dayByDate(date)
            if (truth((onPresent(d) { value148 -> value148.routineId } === r.id))) {
                d.sourceSequence = global_JSON.parse(global_JSON.stringify(r.sourceSequence))
                d.tasks = js("[]")
                global_save()
                global_render()
            }
        }
        return absent
    })
    global_setInterval(global_updateSourceClock, 1000)
    global_missionWindow = (fun(d: dynamic, arg165: dynamic): dynamic {
        var inModal: dynamic = absent
        inModal = definedOr(arg165) { false }
        return jsAdd(global_V10UI.missionWindow(d, inModal), (if (truth(d.sourceSequence)) global_sourceSequenceCard(d.sourceSequence, jsAnd(jsAnd((d.date === global_E.dateKey())) { (!truth(onPresent(global_state.logs[d.date]) { value166 -> value166.complete })) }) { (!truth(onPresent(global_state.logs[d.date]) { value167 -> value167.pain })) }) else ""))
    })
    global_workout = (fun(): dynamic {
        val d: dynamic = global_todayDay()
        val html: dynamic = global_V10UI.workout()
        return (if (truth(jsAnd(onPresent(d) { value170 -> value170.sourceSequence }) { global_detailedMode })) jsAdd(html, global_sourceSequenceCard(d.sourceSequence, jsAnd((!truth(onPresent(global_state.logs[d.date]) { value171 -> value171.complete }))) { (!truth(onPresent(global_state.logs[d.date]) { value172 -> value172.pain })) })) else html)
    })
    global_editMission = (fun(date: dynamic): dynamic {
        if (truth(onPresent(global_dayByDate(date)) { value175 -> value175.sourceSequence })) {
            return global_toast("Esta secuencia original está protegida. Puedes asignar otra rutina para cambiarla.")
        }
        global_V10UI.editMission(date)
        return absent
    })
    global_editExercise = (fun(i: dynamic): dynamic {
        if (truth(onPresent(global_todayDay()) { value178 -> value178.sourceSequence })) {
            return global_toast("La secuencia original conserva sus ejercicios y cantidades.")
        }
        global_V10UI.editExercise(i)
        return absent
    })
    global_toggleSet = (fun(i: dynamic, j: dynamic): dynamic {
        val l: dynamic = global_getLog()
        if (truth(jsOr(jsOr((!truth(l))) { l.complete }) { l.pain })) {
            return absent
        }
        global_V10UI.toggleSet(i, j)
        val x: dynamic = l.items[i]
        if (truth(onPresent(x) { value181 -> onPresent(value181["actual"][j]) { value183 -> value183.done } })) {
            if (truth(x.rest)) {
                global_startTimer(x.rest)
            } else {
                global_timerEnd = 0
                if (truth(global_window.HunterV27)) {
                    global_window.HunterV27.alarm.rest = dynamicValue(null)
                }
            }
            global_updateClock()
            global_updateSourceClock()
        }
        return absent
    })
    global_openCoach = (fun(): dynamic {
        global_V10UI.openCoach()
        global_weeklyLanguage(global_Dollar("#modal"))
        val c: dynamic = global_currentCycle()
        val cards: dynamic = onPresent(global_Dollar("#modal")) { value188 -> value188.querySelector(".learning-card") }
        if (truth(cards)) {
            cards.insertAdjacentHTML("beforeend", ("<p>Semana actual: " + textOf(c.start) + " → " + textOf(global_E["plus"](c.start, 6)) + ". Próxima revisión: " + textOf(global_E["plus"](c.start, 7)) + ".</p><button class=\"btn ghost\" onclick=\"HunterActions.openRoutineLibrary()\">Explorar rutinas completas</button>"))
        }
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("youtube|entrenador|sergio|paufit|naty|metal|chuy", "i")).test(q))) {
            return ("" + textOf(global_companionDisplayName()) + ": En Rutinas encontrarás los cinco canales. Organizo sesiones compatibles con tus restricciones y objetivos; las secuencias importadas se mantienen sin cambios. Las referencias sin ejercicios y descansos verificados no se asignan como entrenamiento.")
        }
        if (truth(dynamicValue(jsRegExp("semana|evolu|progre", "i")).test(q))) {
            val c: dynamic = global_currentCycle()
            return ("" + textOf(global_companionDisplayName()) + ": " + textOf(jsOr(onPresent(c.reviews) { value191 -> onPresent(value191.at((-numeric(1)))) { value192 -> value192.reason } }) { "La revisión ocurre al comenzar la próxima semana." }) + " Próxima revisión: " + textOf(global_E["plus"](c.start, 7)) + ". Cada ejercicio necesita tres registros completos comparables para aumentar su dosis. Las restricciones y el dolor tienen prioridad.")
        }
        return global_V10UI.answerCoach(q).replace(jsRegExp(" y \\d+ minutos aproximados", "g"), "").replace(jsRegExp("cada 21 días", "g"), "cada semana")
    })
    global_validate10 = global_validateBackup
    global_validateBackup = (fun(`data`: dynamic): dynamic {
        if (truth((!truth(global_validate10(`data`))))) {
            return false
        }
        if (truth(jsAnd((`data`.creatorRoutines !== absent)) { jsOr(jsOr((!truth(global_Array.isArray(`data`.creatorRoutines)))) { jsMore(`data`.creatorRoutines.length, 500) }) { (!truth(`data`.creatorRoutines.every(global_CreatorFeed.validateRoutine))) } })) {
            return false
        }
        if (truth(jsAnd((`data`.creatorSync !== absent)) { (jsTypeOf(`data`.creatorSync) !== "boolean") })) {
            return false
        }
        if (truth(jsAnd((`data`.creatorAuto !== absent)) { (jsTypeOf(`data`.creatorAuto) !== "boolean") })) {
            return false
        }
        if (true) {
            val iterator195: dynamic = iteratorOf(`data`.cycles)
            while (true) {
                val next196: dynamic = iterator195.next()
                if (truth(next196.done)) break
                var c: dynamic = absent
                c = next196.value
                if (true) {
                    val iterator197: dynamic = iteratorOf(c.days)
                    while (true) {
                        val next198: dynamic = iterator197.next()
                        if (truth(next198.done)) break
                        var d: dynamic = absent
                        d = next198.value
                        if (truth(jsAnd((d.sourceSequence !== absent)) { (!truth(global_CreatorFeed.validateRoutine(d.sourceSequence))) })) {
                            return false
                        }
                    }
                }
            }
        }
        return true
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V10UI.render()
        global_weeklyLanguage(global_Dollar("#app"))
        global_document.querySelectorAll(".brand small").forEach((fun(el: dynamic): dynamic {
            return run {
                val target201: dynamic = el
                val assigned203: dynamic = "EL DESPERTAR · V10"
                target201.textContent = assigned203
                assigned203
            }
        }))
        return absent
    })
    global_state.appVersion = 10
    global_render()
    global_syncNative()
}
