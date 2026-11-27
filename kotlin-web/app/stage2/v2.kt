@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv2

import hunter.browser.*

@JsName("base") private external var global_base: dynamic
@JsName("routineCategory") private external var global_routineCategory: dynamic
@JsName("routineQuery") private external var global_routineQuery: dynamic
@JsName("routineAvailable") private external var global_routineAvailable: dynamic
@JsName("exerciseCategory") private external var global_exerciseCategory: dynamic
@JsName("exerciseLevel") private external var global_exerciseLevel: dynamic
@JsName("exerciseMuscle") private external var global_exerciseMuscle: dynamic
@JsName("exerciseEquip") private external var global_exerciseEquip: dynamic
@JsName("pendingRoutine") private external var global_pendingRoutine: dynamic
@JsName("reminderStatus") private external var global_reminderStatus: dynamic
@JsName("workEnd") private external var global_workEnd: dynamic
@JsName("workLabel") private external var global_workLabel: dynamic
@JsName("builderItems") private external var global_builderItems: dynamic
@JsName("defaultReminders") private external var global_defaultReminders: dynamic
@JsName("normalizeV2") private external var global_normalizeV2: dynamic
@JsName("decorateRoutine") private external var global_decorateRoutine: dynamic
@JsName("baseModal") private external var global_baseModal: dynamic
@JsName("pulse") private external var global_pulse: dynamic
@JsName("icon2") private external var global_icon2: dynamic
@JsName("baseGo") private external var global_baseGo: dynamic
@JsName("panelTabs") private external var global_panelTabs: dynamic
@JsName("shell3855") private external var global_shell3855: dynamic
@JsName("lastSceneMarkup3874") private external var global_lastSceneMarkup3874: dynamic
@JsName("canonicalScene3874") private external var global_canonicalScene3874: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic
@JsName("goalDone") private external var global_goalDone: dynamic
@JsName("goalMini") private external var global_goalMini: dynamic
@JsName("goalsView") private external var global_goalsView: dynamic
@JsName("newGoal") private external var global_newGoal: dynamic
@JsName("addGoal") private external var global_addGoal: dynamic
@JsName("toggleGoal") private external var global_toggleGoal: dynamic
@JsName("deleteGoal") private external var global_deleteGoal: dynamic
@JsName("quickFinish") private external var global_quickFinish: dynamic
@JsName("confirmQuickFinish") private external var global_confirmQuickFinish: dynamic
@JsName("completeExercise") private external var global_completeExercise: dynamic
@JsName("startExerciseTimer") private external var global_startExerciseTimer: dynamic
@JsName("exerciseList") private external var global_exerciseList: dynamic
@JsName("refreshExercises") private external var global_refreshExercises: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("routineList") private external var global_routineList: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("routineCards") private external var global_routineCards: dynamic
@JsName("refreshRoutines") private external var global_refreshRoutines: dynamic
@JsName("routineDetail") private external var global_routineDetail: dynamic
@JsName("routineHasActivity372") private external var global_routineHasActivity372: dynamic
@JsName("routineNotice372") private external var global_routineNotice372: dynamic
@JsName("prepareRoutine") private external var global_prepareRoutine: dynamic
@JsName("applyRoutine") private external var global_applyRoutine: dynamic
@JsName("routineBuilder") private external var global_routineBuilder: dynamic
@JsName("addBuilderItem") private external var global_addBuilderItem: dynamic
@JsName("builderRows") private external var global_builderRows: dynamic
@JsName("saveCustomRoutine") private external var global_saveCustomRoutine: dynamic
@JsName("deleteRoutine") private external var global_deleteRoutine: dynamic
@JsName("remindersView") private external var global_remindersView: dynamic
@JsName("updateReminderFields") private external var global_updateReminderFields: dynamic
@JsName("saveReminders") private external var global_saveReminders: dynamic
@JsName("refreshReminderStatus") private external var global_refreshReminderStatus: dynamic
@JsName("testNotification") private external var global_testNotification: dynamic
@JsName("openNotificationSettings") private external var global_openNotificationSettings: dynamic
@JsName("oldValidate") private external var global_oldValidate: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("profile") private external var global_profile: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("workout") private external var global_workout: dynamic
@JsName("history") private external var global_history: dynamic
@JsName("finish") private external var global_finish: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("confirmImport") private external var global_confirmImport: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("header") private external var global_header: dynamic
@JsName("nav") private external var global_nav: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("icon") private external var global_icon: dynamic
@JsName("go") private external var global_go: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("storageError") private external var global_storageError: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("book") private external var global_book: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("kindLabel") private external var global_kindLabel: dynamic
@JsName("HunterNative3875") private external var global_HunterNative3875: dynamic
@JsName("FormData") private external var global_FormData: dynamic
@JsName("blocked") private external var global_blocked: dynamic
@JsName("todayDay") private external var global_todayDay: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("calendar") private external var global_calendar: dynamic
@JsName("HunterKotlinScreens") private external var global_HunterKotlinScreens: dynamic
@JsName("getLog") private external var global_getLog: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("timerEnd") private external var global_timerEnd: dynamic
@JsName("setInterval") private external var global_setInterval: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("query") private external var global_query: dynamic
@JsName("exerciseCards") private external var global_exerciseCards: dynamic
@JsName("searchExercises") private external var global_searchExercises: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("dayByDate") private external var global_dayByDate: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("pendingImport") private external var global_pendingImport: dynamic
@JsName("Android") private external var global_Android: dynamic
@JsName("JSON") private external var global_JSON: dynamic

internal fun installV2() {
    global_normalizeV2 = fun(s: dynamic): dynamic {
        s.goals = (if (truth(global_Array.isArray(s.goals))) s.goals.filter((fun(g: dynamic): dynamic {
            return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(g) { dynamicValue(jsRegExp("^g[a-z0-9]+\$", "")).test(g.id) }) { (jsTypeOf(g.title) === "string") }) { jsAtMost(g.title.length, 100) }) { dynamicValue(jsonValue("[\"daily\",\"once\"]")).includes(g.repeat) }) { global_Array.isArray(g.dates) }) { g.dates.every((fun(d: dynamic): dynamic {
                return dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(d)
            })) }
        })).slice(0, 100) else js("[]"))
        s.customRoutines = (if (truth(global_Array.isArray(s.customRoutines))) s.customRoutines.filter((fun(r: dynamic): dynamic {
            return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(r) { dynamicValue(jsRegExp("^custom[a-z0-9]+\$", "")).test(r.id) }) { (jsTypeOf(r.name) === "string") }) { onPresent(r.items) { value11 -> value11.length } }) { jsAtMost(r.items.length, 10) }) { r.items.every((fun(x: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(global_E.byId(x.id)) { global_Number.isInteger(x.sets) }) { jsAtLeast(x.sets, 1) }) { jsAtMost(x.sets, 4) }) { global_Number.isInteger(x.target) }) { jsAtLeast(x.target, 1) }) { jsAtMost(x.target, 600) }
            })) }
        })).slice(0, 50).map((fun(r: dynamic): dynamic {
            return global_decorateRoutine(r)
        })) else js("[]"))
        s.reminders = jsObject { obj14 ->
            copyFields(obj14, global_defaultReminders())
            copyFields(obj14, jsOr(s.reminders) { js("({})") })
        }
        s.appVersion = 2
        return s
    }
    global_decorateRoutine = fun(r: dynamic): dynamic {
        val items: dynamic = r.items.map((fun(x: dynamic): dynamic {
            return jsObject { obj19 ->
                obj19["id"] = x.id
                obj19["sets"] = x.sets
                obj19["target"] = x.target
                obj19["rest"] = global_Math.max(15, global_Math.min(180, jsOr(x.rest) { 90 }))
            }
        }))
        return jsObject { obj20 ->
            copyFields(obj20, r)
            obj20["name"] = r.name.slice(0, 60)
            obj20["description"] = dynamicValue(jsOr(r.description) { "Rutina creada por ti." }).slice(0, 250)
            obj20["items"] = items
            obj20["requirements"] = jsArray { array21 ->
                appendAll(array21, jsConstruct(global_Set, arrayOf<dynamic>(items.flatMap((fun(x: dynamic): dynamic {
                    return global_E.byId(x.id).requirements
                })))))
            }
            obj20["level"] = run { val target23: dynamic = global_Math; invokeWith(target23.max, target23, jsArray { array22 ->
                appendAll(array22, items.map((fun(x: dynamic): dynamic {
                    return global_E.byId(x.id).level
                })))
            }) }
            obj20["minutes"] = jsAdd(global_Math.ceil(jsDivide(items.reduce((fun(n: dynamic, x: dynamic): dynamic {
                return jsAdd(n, jsMultiply(x.sets, jsAdd(jsMultiply(jsMultiply(x.target, (if (truth((global_E.unitOf(x) === "sec"))) 1 else 4)), (if (truth(global_E.sidesOf(x))) 2 else 1)), x.rest)))
            }), 0), 60)), 5)
            obj20["category"] = jsOr(r.category) { "Personalizada" }
            obj20["impact"] = (if (truth(items.some((fun(x: dynamic): dynamic {
                return (global_E.byId(x.id).impact === "Con saltos")
            })))) "Con saltos" else "Sin saltos")
            obj20["custom"] = true
        }
    }
    global_pulse = fun(): dynamic {
        global_document.body.classList.remove("reward")
        run { global_document.body.offsetWidth; absent }
        global_document.body.classList.add("reward")
        global_setTimeout((fun(): dynamic {
            return global_document.body.classList.remove("reward")
        }), 1200)
        return absent
    }
    global_icon2 = fun(id: dynamic): dynamic {
        val paths: dynamic = jsonValue("{\"bolt\":\"<path d=\\\"M14 2 4 14h7l-1 8 10-13h-7z\\\"/>\",\"target\":\"<circle cx=\\\"12\\\" cy=\\\"12\\\" r=\\\"9\\\"/><circle cx=\\\"12\\\" cy=\\\"12\\\" r=\\\"5\\\"/><circle cx=\\\"12\\\" cy=\\\"12\\\" r=\\\"1\\\"/>\",\"bell\":\"<path d=\\\"M18 8a6 6 0 0 0-12 0c0 7-3 7-3 9h18c0-2-3-2-3-9M9 21h6\\\"/>\",\"dumbbell\":\"<path d=\\\"M7 8v8m10-8v8M3 6v12m18-12v12M3 12h18\\\"/>\",\"heart\":\"<path d=\\\"M20 4c-3-2-6 0-8 2-2-2-5-4-8-2-5 4 0 10 8 17 8-7 13-13 8-17Z\\\"/><path d=\\\"M3 12h5l2-4 4 8 2-4h5\\\"/>\",\"spark\":\"<path d=\\\"m12 2 3 7 7 3-7 3-3 7-3-7-7-3 7-3z\\\"/>\",\"check\":\"<path d=\\\"m4 12 5 5L20 6\\\"/>\",\"arrow\":\"<path d=\\\"M4 12h16m-6-6 6 6-6 6\\\"/>\"}")
        return ("<svg viewBox=\"0 0 24 24\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"1.5\" stroke-linecap=\"round\" stroke-linejoin=\"round\" aria-hidden=\"true\">" + textOf(jsOr(paths[id]) { paths.spark }) + "</svg>")
    }
    global_panelTabs = fun(): dynamic {
        return ""
    }
    global_syncNative = fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        if (truth((!truth(onPresent(global_window.Android) { value99 -> value99.syncPlan })))) {
            return absent
        }
        try {
            val days: dynamic = global_state.cycles.flatMap((fun(c: dynamic): dynamic {
                return c.days
            })).filter((fun(d: dynamic): dynamic {
                return jsAtLeast(d.date, global_E.dateKey())
            })).slice((-numeric(42))).map((fun(d: dynamic): dynamic {
                return jsObject { obj100 ->
                    obj100["date"] = d.date
                    obj100["title"] = jsOr(d.title) { global_kindLabel(d.kind) }
                    obj100["description"] = d.items.map((fun(x: dynamic): dynamic {
                        return global_E.byId(x.id).name
                    })).join(" · ").slice(0, 400)
                    obj100["complete"] = (!truth((!truth(onPresent(global_state.logs[d.date]) { value101 -> value101.complete }))))
                    obj100["stopped"] = (!truth((!truth(onPresent(global_state.logs[d.date]) { value102 -> value102.pain }))))
                }
            }))
            global_HunterNative3875.syncPlan(jsObject { obj103 ->
                obj103["eligible"] = jsAnd((!truth((!truth(global_state.profile))))) { global_E.eligible(global_state.profile) }
                obj103["days"] = days
            })
        } catch (e: dynamic) {

        }
        return absent
    }
    global_goalDone = fun(g: dynamic): dynamic {
        return global_HunterKotlinScreens.goalDone(g)
    }
    global_goalMini = fun(): dynamic {
        return global_HunterKotlinScreens.goalMini()
    }
    global_goalsView = fun(): dynamic {
        return global_HunterKotlinScreens.goalsView()
    }
    global_newGoal = fun(): dynamic {
        return global_HunterKotlinScreens.newGoal()
    }
    global_addGoal = fun(ev: dynamic): dynamic {
        return global_HunterKotlinScreens.addGoal(ev)
    }
    global_toggleGoal = fun(id: dynamic): dynamic {
        return global_HunterKotlinScreens.toggleGoal(id)
    }
    global_deleteGoal = fun(id: dynamic): dynamic {
        return global_HunterKotlinScreens.deleteGoal(id)
    }
    global_quickFinish = fun(): dynamic {
        if (truth((!truth(global_todayDay())))) {
            return absent
        }
        val l: dynamic = global_getLog()
        if (truth(jsOr(l.complete) { l.pain })) {
            return absent
        }
        global_modal(("<span class=\"eyebrow\">FINALIZACIÓN RÁPIDA</span><h2>¿Ya terminaste la misión?</h2><p>Se guardará como completada por tu confirmación. Las series, repeticiones y pesos que no registraste permanecerán sin contabilizar.</p><div class=\"field\"><label class=\"label\" for=\"quickRpe\">Esfuerzo percibido</label><select id=\"quickRpe\"><option value=\"0\">Selecciona tu esfuerzo</option>" + textOf(global_Array.from(jsonValue("{\"length\":10}"), (fun(_: dynamic, i: dynamic): dynamic {
            return ("<option value=\"" + textOf(jsAdd(i, 1)) + "\">" + textOf(jsAdd(i, 1)) + " / 10</option>")
        })).join("")) + "</select></div><div class=\"field\"><label class=\"label\" for=\"quickNote\">Nota opcional</label><textarea id=\"quickNote\" maxlength=\"500\">" + textOf(global_esc(l.note)) + "</textarea></div><button class=\"btn primary full\" onclick=\"confirmQuickFinish()\">Sí, misión finalizada</button>"))
        return absent
    }
    global_confirmQuickFinish = fun(): dynamic {
        val l: dynamic = global_getLog()
        if (truth(jsOr(jsOr((!truth(l))) { l.complete }) { l.pain })) {
            return absent
        }
        val effort: dynamic = numeric(global_Dollar("#quickRpe")["value"])
        if (truth(jsAnd((l.kind !== "rest")) { (!truth(effort)) })) {
            return global_toast("Indica el esfuerzo que sentiste.")
        }
        l.rpe = effort
        l.note = global_Dollar("#quickNote")["value"].slice(0, 500)
        l.complete = true
        l.manual = true
        l.completedAt = jsConstruct(global_Date, js("[]")).toISOString()
        global_save()
        global_Dollar("#modal").close()
        global_workEnd = 0
        global_timerEnd = 0
        global_render()
        global_pulse()
        global_toast("MISIÓN FINALIZADA · Confirmación guardada")
        return absent
    }
    global_completeExercise = fun(i: dynamic): dynamic {
        val l: dynamic = global_getLog()
        if (truth(jsOr(l.complete) { l.pain })) {
            return absent
        }
        l.items[i].selfReported = (!truth(l.items[i].selfReported))
        global_save()
        global_render()
        global_toast((if (truth(l.items[i].selfReported)) "Ejercicio finalizado por confirmación. No se añadieron repeticiones." else "Ejercicio reabierto."))
        return absent
    }
    global_startExerciseTimer = fun(i: dynamic): dynamic {
        val x: dynamic = global_getLog().items[i]
        val e: dynamic = global_E.byId(x.id)
        global_workEnd = jsAdd(global_Date.now(), jsMultiply(x.target, 1000))
        global_workLabel = e.name
        global_modal(("<span class=\"eyebrow\">INTERVALO ACTIVO</span><h2>" + textOf(e.name) + "</h2><div class=\"interval-clock\" id=\"workClock\">" + textOf(x.target) + "</div><p class=\"muted\">" + textOf((if (truth(e.sides)) "Este intervalo corresponde a un lado; repítelo para el otro. " else "")) + "El temporizador no registra repeticiones ni confirma que hayas realizado el ejercicio.</p><button class=\"btn danger full\" onclick=\"HunterActions.closeWorkTimer()\">Detener intervalo</button>"))
        return absent
    }
    global_exerciseList = fun(): dynamic {
        return global_E.exercises.filter((fun(e: dynamic): dynamic {
            return jsAnd(jsAnd(jsAnd(jsAnd(jsOr((global_exerciseCategory === "Todas")) { (e.category === global_exerciseCategory) }) { jsOr((global_exerciseLevel === "all")) { (e.level === numeric(global_exerciseLevel)) } }) { jsOr((global_exerciseMuscle === "Todos")) { (e.group === global_exerciseMuscle) } }) { jsOr((global_exerciseEquip === "all")) { (if (truth((global_exerciseEquip === "body"))) (e.requirements.length === 0) else e.requirements.includes(global_exerciseEquip)) } }) { dynamicValue(jsAdd(jsAdd(e.name, " "), e.group)).toLowerCase().includes(global_query.toLowerCase()) }
        }))
    }
    global_refreshExercises = fun(): dynamic {
        val list: dynamic = global_exerciseList()
        global_Dollar("#exerciseResults").innerHTML = global_exerciseCards(list)
        global_Dollar("#exerciseCount").textContent = jsAdd(list.length, " resultados")
        return absent
    }
    global_allRoutines = fun(): dynamic {
        return jsArray { array217 ->
            appendAll(array217, global_Catalog.routines)
            appendAll(array217, global_state.customRoutines)
        }
    }
    global_routineList = fun(): dynamic {
        return global_allRoutines().filter((fun(r: dynamic): dynamic {
            return jsAnd(jsAnd(jsOr((global_routineCategory === "Todas")) { (r.category === global_routineCategory) }) { jsOr((!truth(global_routineAvailable))) { global_Catalog.available(r, global_state.profile) } }) { dynamicValue(jsAdd(jsAdd(r.name, " "), r.description)).toLowerCase().includes(global_routineQuery.toLowerCase()) }
        }))
    }
    global_routines = fun(): dynamic {
        return ("<div class=\"page-heading\"><span class=\"eyebrow\">ELIGE TU PRÓXIMA MISIÓN</span><h1>Centro de rutinas</h1><p class=\"muted\">" + textOf(global_Catalog.routines.length) + " plantillas + tus propias sesiones</p></div><button class=\"btn primary\" onclick=\"routineBuilder()\">+ Crear mi rutina</button><input class=\"library-search\" aria-label=\"Buscar rutina\" placeholder=\"Busca piernas, cardio, mancuernas…\" value=\"" + textOf(global_esc(global_routineQuery)) + "\" oninput=\"HunterActions.searchRoutines(this)\"><div class=\"filter\">" + textOf(dynamicValue(jsonValue("[\"Todas\",\"Fuerza\",\"Cardio\",\"Mixta\",\"Movilidad\",\"Personalizada\"]")).map((fun(c: dynamic): dynamic {
            return ("<button class=\"btn ghost " + textOf((if (truth((global_routineCategory === c))) "selected" else "")) + "\" onclick=\"HunterActions.setRoutineCategory('" + textOf(c) + "')\">" + textOf(c) + "</button>")
        })).join("")) + "</div><label class=\"row\"><input type=\"checkbox\" " + textOf((if (truth(global_routineAvailable)) "checked" else "")) + " onchange=\"HunterActions.filterAvailableRoutines(this)\"> Solo con mi equipo disponible</label><p class=\"result-count\" id=\"routineCount\">" + textOf(global_routineList().length) + " rutinas</p><div class=\"routine-grid\" id=\"routineResults\">" + textOf(global_routineCards(global_routineList())) + "</div>")
    }
    global_routineCards = fun(list: dynamic): dynamic {
        return jsOr(list.map((fun(r: dynamic): dynamic {
            return ("<button class=\"routine-card " + textOf((if (truth((r.category === "Cardio"))) "pink" else (if (truth((r.category === "Movilidad"))) "purple" else ""))) + "\" onclick=\"routineDetail('" + textOf(r.id) + "')\"><div class=\"routine-cover\"><span class=\"routine-emblem\">" + textOf(global_icon2((if (truth((r.category === "Cardio"))) "heart" else (if (truth((r.category === "Movilidad"))) "spark" else "dumbbell")))) + "</span><span class=\"badge\">" + textOf(global_Catalog.levels[r.level]) + "</span><span class=\"routine-time\">≈ " + textOf(r.minutes) + " min</span></div><div class=\"routine-body\"><span class=\"tag\">" + textOf(r.category) + " · " + textOf(r.impact) + "</span><h3>" + textOf(global_esc(r.name)) + "</h3><p>" + textOf(global_esc(r.description)) + "</p><div class=\"row between\"><small>" + textOf(r.items.length) + " ejercicios</small><span>" + textOf((if (truth(global_Catalog.available(r, global_state.profile))) "Equipo disponible" else "Requiere equipo")) + " ↗</span></div></div></button>")
        })).join("")) { "<div class=\"empty\">Sin rutinas con estos filtros.</div>" }
    }
    global_refreshRoutines = fun(): dynamic {
        global_Dollar("#routineResults").innerHTML = global_routineCards(global_routineList())
        global_Dollar("#routineCount").textContent = jsAdd(global_routineList().length, " rutinas")
        return absent
    }
    global_routineDetail = fun(id: dynamic): dynamic {
        val r: dynamic = global_allRoutines().find((fun(r: dynamic): dynamic {
            return (r.id === id)
        }))
        if (truth((!truth(r)))) {
            return absent
        }
        val c: dynamic = global_currentCycle()
        val options: dynamic = jsOr(onPresent(c) { value232 -> value232.days.filter((fun(d: dynamic): dynamic {
            return jsAtLeast(d.date, global_E.dateKey())
        })) }) { js("[]") }
        global_modal(("<span class=\"eyebrow\">" + textOf(r.category) + " · " + textOf(global_Catalog.levels[r.level]) + "</span><h2>" + textOf(global_esc(r.name)) + "</h2><p>" + textOf(global_esc(r.description)) + "</p><div class=\"tech-tags\"><span>≈ " + textOf(r.minutes) + " min · estimación con pausas</span><span>" + textOf(r.impact) + "</span></div>" + textOf((if (truth(r.requirements.length)) ("<div class=\"notice\">Equipo: " + textOf(r.requirements.map((fun(x: dynamic): dynamic {
            return global_Catalog.equipment[x]
        })).join(", ")) + ".</div>") else "")) + "<div class=\"routine-items\">" + textOf(r.items.map((fun(x: dynamic): dynamic {
            val e: dynamic = global_E.byId(x.id)
            return ("<div class=\"quest\"><div class=\"grow\"><b>" + textOf(e.name) + "</b><small>" + textOf(x.sets) + " × " + textOf(x.target) + " " + textOf((if (truth((global_E.unitOf(x) === "sec"))) "s" else "reps")) + "" + textOf((if (truth(global_E.sidesOf(x))) " por lado" else "")) + " · descanso " + textOf(x.rest) + "s</small></div><button class=\"btn ghost\" onclick=\"HunterActions.showTechnique('" + textOf(e.id) + "')\" aria-label=\"Ver técnica de " + textOf(e.name) + "\">↗</button></div>")
        })).join("")) + "</div>" + textOf((if (truth((r.level === 2))) "<div class=\"notice warn\">Rutina avanzada: revisa cada movimiento. Usa una variante inicial si todavía no lo dominas.</div>" else "")) + "" + textOf((if (truth(options.length)) ("<div class=\"field\"><label class=\"label\" for=\"routineDay\">Asignar a una misión</label><select id=\"routineDay\">" + textOf(options.map((fun(d: dynamic): dynamic {
            return ("<option value=\"" + textOf(d.date) + "\">" + textOf((if (truth((d.date === global_E.dateKey()))) "Hoy" else d.date)) + " · " + textOf(global_kindLabel(d.kind)) + "</option>")
        })).join("")) + "</select></div><button class=\"btn primary full\" onclick=\"prepareRoutine('" + textOf(id) + "')\">Usar esta rutina</button>") else "<div class=\"notice\">Tu perfil necesita un plan individual antes de asignar rutinas.</div>")) + "" + textOf((if (truth(r.custom)) ("<button class=\"btn danger full\" onclick=\"deleteRoutine('" + textOf(id) + "')\">Eliminar mi rutina</button>") else "")) + ""))
        return absent
    }
    global_routineHasActivity372 = fun(date: dynamic): dynamic {
        return jsOr((!truth((!truth(global_Weekly.active(global_state.logs[date])))))) { (!truth((!truth(onPresent(global_dayByDate(date)) { value236 -> onPresent(value236.tasks) { value237 -> value237.some((fun(t: dynamic): dynamic {
            return t.done
        })) } })))) }
    }
    global_routineNotice372 = fun(message: dynamic): dynamic {
        val dialog: dynamic = global_Dollar("#modal")
        if (truth((!truth(dialog["open"])))) {
            return global_toast(message)
        }
        var notice: dynamic = dialog.querySelector("#routineNotice372")
        if (truth((!truth(notice)))) {
            notice = global_document.createElement("div")
            notice.id = "routineNotice372"
            notice.className = "notice warn routine-notice372"
            notice.setAttribute("role", "alert")
            notice.tabIndex = (-numeric(1))
            val day: dynamic = dialog.querySelector("#routineDay")
            if (truth(day)) {
                dynamicValue(jsOr(day.closest("label")) { day }).after(notice)
            } else {
                dialog.append(notice)
            }
        }
        notice.textContent = message
        notice.focus(jsonValue("{\"preventScroll\":true}"))
        run { val owner249: dynamic = notice; onPresent(owner249.scrollIntoView) { method248 -> invokeWith(method248, owner249, arrayOf<dynamic>(jsonValue("{\"block\":\"nearest\",\"behavior\":\"auto\"}"))) } }
        global_ModalViewport.fit()
        return absent
    }
    global_prepareRoutine = fun(id: dynamic): dynamic {
        global_pendingRoutine = dynamicValue(null)
        val r: dynamic = global_allRoutines().find((fun(r: dynamic): dynamic {
            return (r.id === id)
        }))
        val date: dynamic = onPresent(global_Dollar("#routineDay")) { value252 -> value252["value"] }
        val profile: dynamic = global_GoalPlans.adjusted(global_state.profile)
        if (truth((!truth(r)))) {
            return global_routineNotice372("La rutina no está disponible. Vuelve a abrir su ficha.")
        }
        if (truth((!truth(global_Catalog.available(r, profile))))) {
            return global_routineNotice372("Falta equipo para esta rutina. Revisa tu perfil o elige otra.")
        }
        val c: dynamic = global_currentCycle()
        val day: dynamic = onPresent(c) { value253 -> value253.days.find((fun(d: dynamic): dynamic {
            return (d.date === date)
        })) }
        if (truth(jsOr((!truth(day))) { jsLess(date, global_E.dateKey()) })) {
            return global_routineNotice372("Selecciona un día disponible para asignar esta rutina.")
        }
        if (truth(global_routineHasActivity372(date))) {
            return global_routineNotice372("Este día ya tiene actividad registrada. Elige otro día en «Día de entrenamiento» para conservar tu avance.")
        }
        val idx: dynamic = c.days.indexOf(day)
        val strenuous: dynamic = dynamicValue(jsonValue("[\"Fuerza\",\"Mixta\",\"Personalizada\"]")).includes(r.category)
        val adjacent: dynamic = c.days.filter((fun(d: dynamic, i: dynamic): dynamic {
            return jsAnd((global_Math.abs(jsSubtract(i, idx)) === 1)) { dynamicValue(jsonValue("[\"strength\",\"mixed\"]")).includes(d.kind) }
        }))
        global_pendingRoutine = jsObject { obj255 ->
            obj255["id"] = id
            obj255["date"] = date
        }
        global_modal(("<h2>Asignar rutina</h2><p><b>" + textOf(global_esc(r.name)) + "</b> reemplazará la misión del " + textOf(date) + ".</p>" + textOf((if (truth(jsAnd(strenuous) { adjacent.length })) "<div class=\"notice warn\">Hay una sesión de fuerza en un día contiguo. Elige preferiblemente otro día para recuperar entre sesiones.</div>" else "")) + "<p class=\"footnote\">La rutina solo cambia esta fecha. Las demás misiones e historial se conservan.</p><button type=\"button\" aria-label=\"Confirmar rutina\" class=\"btn primary full\" onclick=\"applyRoutine()\">Confirmar rutina</button>"))
        return absent
    }
    global_applyRoutine = fun(): dynamic {
        var id: dynamic = absent
        var date: dynamic = absent
        if (truth((!truth(global_pendingRoutine)))) {
            return global_routineNotice372("Vuelve a elegir una rutina y un día antes de confirmar.")
        }
        val unpacked258: dynamic = global_pendingRoutine
        id = unpacked258["id"]
        date = unpacked258["date"]
        val r: dynamic = global_allRoutines().find((fun(r: dynamic): dynamic {
            return (r.id === id)
        }))
        val d: dynamic = onPresent(global_currentCycle()) { value259 -> value259.days.find((fun(d: dynamic): dynamic {
            return (d.date === date)
        })) }
        if (truth(jsOr((!truth(r))) { (!truth(d)) })) {
            return global_routineNotice372("La rutina o el día ya no están disponibles. Vuelve a abrir la ficha.")
        }
        if (truth(jsOr((!truth(global_Adapt.routineCompatible(r, global_GoalPlans.adjusted(global_state.profile))))) { (!truth(global_Catalog.available(r, global_GoalPlans.adjusted(global_state.profile)))) })) {
            return global_routineNotice372("Tu perfil ha cambiado. Revisa una rutina compatible antes de confirmar.")
        }
        if (truth(global_routineHasActivity372(date))) {
            return global_routineNotice372("Este día ya tiene actividad registrada. Regresa a la ficha y elige otro día.")
        }
        d.items = r.items.map((fun(x: dynamic): dynamic {
            return jsObject { obj263 ->
                copyFields(obj263, x)
            }
        }))
        d.kind = (if (truth((r.category === "Cardio"))) "cardio" else (if (truth((r.category === "Movilidad"))) "recovery" else (if (truth(jsOr((r.category === "Mixta")) { (r.category === "Personalizada") })) "mixed" else "strength")))
        d.title = r.name
        d.routineId = r.id
        removeKey(global_state.logs, date)
        global_pendingRoutine = dynamicValue(null)
        global_save()
        global_Dollar("#modal").close()
        global_go((if (truth((date === global_E.dateKey()))) "workout" else "plan"))
        global_toast("Rutina asignada.")
        return absent
    }
    global_routineBuilder = fun(): dynamic {
        global_builderItems = js("[]")
        global_modal(("<span class=\"eyebrow\">TU PROPIO ENTRENAMIENTO</span><h2>Crear rutina</h2><label class=\"label\" for=\"customName\">Nombre</label><input id=\"customName\" maxlength=\"60\" placeholder=\"Mi rutina de casa\"><div class=\"field\"><label class=\"label\" for=\"builderSelect\">Añadir ejercicio</label><select id=\"builderSelect\">" + textOf(global_E.exercises.map((fun(e: dynamic): dynamic {
            return ("<option value=\"" + textOf(e.id) + "\">" + textOf(e.name) + " · " + textOf(e.group) + "</option>")
        })).join("")) + "</select><button class=\"btn ghost full\" onclick=\"addBuilderItem()\">+ Añadir ejercicio</button></div><div id=\"builderRows\"></div><button class=\"btn primary full\" onclick=\"saveCustomRoutine()\">Guardar mi rutina</button>"))
        return absent
    }
    global_addBuilderItem = fun(): dynamic {
        if (truth(jsAtLeast(global_builderItems.length, 10))) {
            return global_toast("Máximo 10 ejercicios por rutina.")
        }
        val id: dynamic = global_Dollar("#builderSelect")["value"]
        val e: dynamic = global_E.byId(id)
        global_builderItems.push(jsObject { obj274 ->
            obj274["id"] = id
            obj274["sets"] = (if (truth((e.category === "Movilidad"))) 1 else 2)
            obj274["target"] = (if (truth((e.unit === "sec"))) 30 else 8)
            obj274["rest"] = 90
        })
        global_builderRows()
        return absent
    }
    global_builderRows = fun(): dynamic {
        global_Dollar("#builderRows").innerHTML = global_builderItems.map((fun(x: dynamic, i: dynamic): dynamic {
            return ("<div class=\"builder-row\"><div class=\"row between\"><b>" + textOf(global_E.byId(x.id).name) + "</b><button class=\"text-button\" onclick=\"HunterActions.removeBuilderItem(" + textOf(i) + ")\" aria-label=\"Quitar ejercicio\">×</button></div><div class=\"fields\"><label>Series<input aria-label=\"Series " + textOf(jsAdd(i, 1)) + "\" type=\"number\" required min=\"1\" max=\"5\" value=\"" + textOf(x.sets) + "\" onchange=\"HunterActions.setBuilderSets(this," + textOf(i) + ")\"></label><label>" + textOf((if (truth((global_E.unitOf(x) === "sec"))) "Segundos" else "Repeticiones")) + "<input aria-label=\"Objetivo " + textOf(jsAdd(i, 1)) + "\" type=\"number\" required min=\"1\" max=\"" + textOf((if (truth((global_E.unitOf(x) === "sec"))) 600 else 50)) + "\" value=\"" + textOf(x.target) + "\" onchange=\"HunterActions.setBuilderTarget(this," + textOf(i) + ")\"></label></div></div>")
        })).join("")
        return absent
    }
    global_saveCustomRoutine = fun(): dynamic {
        val name: dynamic = global_Dollar("#customName")["value"].trim()
        if (truth(jsOr((!truth(name))) { (!truth(global_builderItems.length)) })) {
            return global_toast("Escribe un nombre y añade al menos un ejercicio.")
        }
        if (truth(global_builderItems.some((fun(x: dynamic): dynamic {
            return jsOr(jsOr(jsOr(jsOr(jsOr((!truth(global_Number.isInteger(x.sets)))) { jsLess(x.sets, 1) }) { jsMore(x.sets, 5) }) { (!truth(global_Number.isInteger(x.target))) }) { jsLess(x.target, 1) }) { jsMore(x.target, (if (truth((global_E.unitOf(x) === "sec"))) 600 else 50)) }
        })))) {
            return global_toast("Revisa las series y cantidades.")
        }
        if (truth(jsAtLeast(global_state.customRoutines.length, 50))) {
            return global_toast("Límite de 50 rutinas personales.")
        }
        global_state.customRoutines.push(global_decorateRoutine(jsObject { obj281 ->
            obj281["id"] = jsAdd("custom", global_Date.now()["toString"](36))
            obj281["name"] = name
            obj281["items"] = global_builderItems
            obj281["category"] = "Personalizada"
        }))
        global_save()
        global_Dollar("#modal").close()
        global_routineCategory = "Personalizada"
        global_go("routines")
        return absent
    }
    global_deleteRoutine = fun(id: dynamic): dynamic {
        global_modal(("<h2>Eliminar rutina personal</h2><p>Las sesiones que ya asignaste conservarán sus ejercicios.</p><button class=\"btn danger full\" onclick=\"HunterActions.deleteRoutine('" + textOf(global_esc(id)) + "')\">Eliminar rutina</button>"))
        return absent
    }
    global_remindersView = fun(): dynamic {
        return global_HunterKotlinScreens.reminders()
    }
    global_updateReminderFields = fun(): dynamic {
        global_HunterKotlinScreens.updateReminderFields()
        return absent
    }
    global_saveReminders = fun(ev: dynamic): dynamic {
        global_HunterKotlinScreens.saveReminders(ev)
        return absent
    }
    global_refreshReminderStatus = fun(): dynamic {
        global_HunterKotlinScreens.refreshReminderStatus((fun(status: dynamic): dynamic {
            global_reminderStatus = status
            return absent
        }))
        return absent
    }
    global_testNotification = fun(): dynamic {
        global_HunterKotlinScreens.testNotification()
        return absent
    }
    global_openNotificationSettings = fun(): dynamic {
        global_HunterKotlinScreens.openNotificationSettings()
        return absent
    }
    global_base = jsObject { obj1 ->
        obj1["render"] = global_render
        obj1["home"] = global_home
        obj1["profile"] = global_profile
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["workout"] = global_workout
        obj1["history"] = global_history
        obj1["finish"] = global_finish
        obj1["save"] = global_save
        obj1["validateBackup"] = global_validateBackup
        obj1["confirmImport"] = global_confirmImport
    }
    global_routineCategory = "Todas"
    global_routineQuery = ""
    global_routineAvailable = false
    global_exerciseCategory = "Todas"
    global_exerciseLevel = "all"
    global_exerciseMuscle = "Todos"
    global_exerciseEquip = "all"
    global_pendingRoutine = dynamicValue(null)
    global_reminderStatus = dynamicValue(null)
    global_workEnd = 0
    global_workLabel = ""
    global_builderItems = js("[]")
    global_defaultReminders = (fun(): dynamic {
        return jsonValue("{\"enabled\":false,\"mode\":\"daily\",\"time\":\"18:00\",\"start\":\"09:00\",\"end\":\"20:00\",\"interval\":3,\"days\":[1,2,3,4,5,6,7]}")
    })
    global_normalizeV2(global_state)
    global_E.exercises.forEach((fun(e: dynamic): dynamic {
        if (truth((e.group === "Core"))) {
            e.group = "Abdomen"
        }
        return absent
    }))
    global_baseModal = global_modal
    global_modal = (fun(html: dynamic): dynamic {
        global_baseModal(html)
        val d: dynamic = global_Dollar("#modal")
        d.setAttribute("tabindex", "-1")
        d.setAttribute("aria-label", jsOr(onPresent(d.querySelector("h2")) { value28 -> value28.textContent }) { "Detalle" })
        d.focus(jsonValue("{\"preventScroll\":true}"))
        d.scrollTop = 0
        val generation: dynamic = global_ModalViewport.generation
        global_requestAnimationFrame((fun(): dynamic {
            if (truth(jsAnd(d["open"]) { (generation === global_ModalViewport.generation) })) {
                d.scrollTop = 0
            }
            return absent
        }))
        return absent
    })
    global_header = (fun(): dynamic {
        return ("<header class=\"topbar\"><button class=\"brand brand-button\" onclick=\"go('home')\"><div class=\"logo\">⟡</div><div>HUNTER<span>SYSTEM</span><small>EL DESPERTAR · V2</small></div></button><button class=\"icon-button\" onclick=\"go('reminders')\" aria-label=\"Recordatorios\">" + textOf(global_icon2("bell")) + "" + textOf((if (truth(global_state.reminders.enabled)) "<i class=\"dot\"></i>" else "")) + "</button></header>")
    })
    global_nav = (fun(): dynamic {
        val selected: dynamic = (if (truth(dynamicValue(jsonValue("[\"plan\",\"goals\",\"workout\",\"reminders\"]")).includes(global_tab))) "home" else global_tab)
        return ("<nav class=\"nav\" aria-label=\"Navegación principal\">" + textOf(dynamicValue(jsonValue("[[\"home\",\"Panel\"],[\"routines\",\"Rutinas\"],[\"book\",\"Ejercicios\"],[\"stats\",\"Progreso\"],[\"profile\",\"Perfil\"]]")).map((fun(arg43: dynamic): dynamic {
            var id: dynamic = absent
            var label: dynamic = absent
            val unpacked44: dynamic = fromIterable(arg43)
            id = unpacked44[0]
            label = unpacked44[1]
            return ("<button onclick=\"go('" + textOf(id) + "')\" class=\"" + textOf((if (truth((selected === id))) "active" else "")) + "\" " + textOf((if (truth((selected === id))) "aria-current=\"page\"" else "")) + ">" + textOf((if (truth((id === "routines"))) global_icon2("dumbbell") else global_icon((if (truth((id === "stats"))) "stats" else (if (truth((id === "profile"))) "profile" else (if (truth((id === "book"))) "book" else "home")))))) + "<span>" + textOf(label) + "</span></button>")
        })).join("")) + "</nav>")
    })
    global_baseGo = global_go
    global_go = (fun(t: dynamic): dynamic {
        global_ModalViewport.beforeNavigation()
        global_workEnd = 0
        global_tab = t
        global_render()
        global_window.scrollTo(jsonValue("{\"top\":0,\"behavior\":\"instant\"}"))
        return absent
    })
    global_shell3855 = jsonValue("{\"header\":null,\"tabs\":null,\"fullMounts\":0,\"sceneReplacements\":0,\"sceneSkips\":0,\"headerReplacements\":0,\"navReplacements\":0}")
    global_lastSceneMarkup3874 = dynamicValue(null)
    global_canonicalScene3874 = (fun(html: dynamic): dynamic {
        return html.replace(jsRegExp("aura-knightpart[0-9]+", "g"), "aura-knightpart#")
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        val app: dynamic = global_Dollar("#app")
        if (truth((!truth(global_state.profile)))) {
            global_shell3855.header = dynamicValue(null)
            global_shell3855.tabs = dynamicValue(null)
            app.innerHTML = ("<main class=\"app scene\">" + textOf(global_header()) + "" + textOf((if (truth(global_storageError)) "<div class=\"notice warn\">No se pudo leer el registro local. Conserva tu copia de seguridad antes de crear un perfil nuevo.</div>" else "")) + "" + textOf(global_profileForm(true)) + "</main>")
            return absent
        }
        global_currentCycle()
        val views: dynamic = jsObject { obj57 ->
            obj57["home"] = global_home
            obj57["plan"] = global_plan
            obj57["book"] = global_book
            obj57["stats"] = global_history
            obj57["profile"] = global_profile
            obj57["workout"] = global_workout
            obj57["routines"] = global_routines
            obj57["goals"] = global_goalsView
            obj57["reminders"] = global_remindersView
            obj57["calendar"] = (fun(): dynamic {
                return jsOr(onPresent(global_window.HunterRoute3855) { value58 -> run { val owner60: dynamic = value58; onPresent(owner60.calendarView) { method59 -> invokeWith(method59, owner60, js("[]")) } } }) { global_plan() }
            })
        }
        val view: dynamic = dynamicValue(jsOr(views[global_tab]) { global_home })()
        val headerMarkup: dynamic = global_header()
        val tabsMarkup: dynamic = (if (truth(dynamicValue(jsonValue("[\"home\",\"plan\",\"goals\"]")).includes(global_tab))) global_panelTabs() else "")
        val main: dynamic = app.querySelector(":scope > main.app")
        val scene: dynamic = onPresent(main) { value61 -> value61.querySelector(":scope > .scene") }
        if (truth(jsOr((!truth(main))) { (!truth(scene)) })) {
            app.innerHTML = ("<main class=\"app\">" + textOf(headerMarkup) + "" + textOf(tabsMarkup) + "<div class=\"scene\" data-route3855=\"" + textOf(global_tab) + "\">" + textOf(view) + "</div></main>" + textOf(global_nav()) + "")
            global_lastSceneMarkup3874 = jsObject { obj64 ->
                obj64["route"] = global_tab
                obj64["html"] = global_canonicalScene3874(view)
            }
            global_shell3855.header = headerMarkup
            global_shell3855.tabs = tabsMarkup
            run {
                val target69: dynamic = global_shell3855
                val previous71: dynamic = numeric(target69.fullMounts)
                target69.fullMounts = jsAdd(previous71, 1)
            }
        } else {
            val previousHeader: dynamic = main.querySelector(":scope > .topbar")
            if (truth(jsOr((global_shell3855.header !== headerMarkup)) { (!truth(previousHeader)) })) {
                if (truth(previousHeader)) {
                    previousHeader.outerHTML = headerMarkup
                } else {
                    main.insertAdjacentHTML("afterbegin", headerMarkup)
                }
                global_shell3855.header = headerMarkup
                run {
                    val target76: dynamic = global_shell3855
                    val previous78: dynamic = numeric(target76.headerReplacements)
                    target76.headerReplacements = jsAdd(previous78, 1)
                }
            }
            val previousTabs: dynamic = main.querySelector(":scope > .segmented")
            if (truth(jsOr((global_shell3855.tabs !== tabsMarkup)) { (global_Boolean(previousTabs) !== global_Boolean(tabsMarkup)) })) {
                if (truth(previousTabs)) {
                    previousTabs.remove()
                }
                if (truth(tabsMarkup)) {
                    scene.insertAdjacentHTML("beforebegin", tabsMarkup)
                }
                global_shell3855.tabs = tabsMarkup
            }
            if (truth(jsOr(jsOr(jsOr((scene.dataset.route3855 !== global_tab)) { (!truth(global_lastSceneMarkup3874)) }) { (global_lastSceneMarkup3874.route !== global_tab) }) { (global_lastSceneMarkup3874.html !== global_canonicalScene3874(view)) })) {
                scene.innerHTML = view
                run {
                    val target83: dynamic = global_shell3855
                    val previous85: dynamic = numeric(target83.sceneReplacements)
                    target83.sceneReplacements = jsAdd(previous85, 1)
                }
                global_lastSceneMarkup3874 = jsObject { obj86 ->
                    obj86["route"] = global_tab
                    obj86["html"] = global_canonicalScene3874(view)
                }
            } else {
                run {
                    val target87: dynamic = global_shell3855
                    val previous89: dynamic = numeric(target87.sceneSkips)
                    target87.sceneSkips = jsAdd(previous89, 1)
                }
            }
            scene.dataset.route3855 = global_tab
            var footer: dynamic = app.querySelector(":scope > nav.nav")
            if (truth((!truth(footer)))) {
                app.insertAdjacentHTML("beforeend", global_nav())
                run {
                    val target92: dynamic = global_shell3855
                    val previous94: dynamic = numeric(target92.navReplacements)
                    target92.navReplacements = jsAdd(previous94, 1)
                }
                footer = app.querySelector(":scope > nav.nav")
            }
            if (truth(footer)) {
                val selected: dynamic = (if (truth((global_tab === "workout"))) "plan" else (if (truth((global_tab === "monarch"))) "home" else (if (truth((global_tab === "goals"))) "plan" else global_tab)))
                footer.querySelectorAll("button[data-route3855]").forEach((fun(btn: dynamic): dynamic {
                    val active: dynamic = (btn.dataset.route3855 === selected)
                    btn.classList.toggle("active", active)
                    if (truth(active)) {
                        btn.setAttribute("aria-current", "page")
                    } else {
                        btn.removeAttribute("aria-current")
                    }
                    return absent
                }))
            }
        }
        if (truth((global_tab === "reminders"))) {
            global_refreshReminderStatus()
        }
        return absent
    })
    global_save = (fun(): dynamic {
        val ok: dynamic = global_base.save()
        if (truth(ok)) {
            global_syncNative()
        }
        return ok
    })
    global_profileForm = (fun(arg106: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg106) { false }
        var h: dynamic = global_base.profileForm(initial)
        h = h.replace("<div class=\"fields\"><div class=\"field\"><label class=\"label\" for=\"load\">", ("<div class=\"equipment-options\"><label><input type=\"checkbox\" name=\"bench\" " + textOf((if (truth(onPresent(global_state.profile) { value107 -> value107.bench })) "checked" else "")) + "> Banco de entrenamiento ajustable</label><label><input type=\"checkbox\" name=\"step\" " + textOf((if (truth(onPresent(global_state.profile) { value108 -> value108.step })) "checked" else "")) + "> Plataforma baja de ejercicio</label></div><div class=\"fields\"><div class=\"field\"><label class=\"label\" for=\"load\">"))
        h = h.replace("Los cambios de equipo, experiencia y frecuencia se aplican al próximo ciclo.", "Los cambios de experiencia y frecuencia se aplican al próximo ciclo. El equipo también filtra las rutinas disponibles.")
        return h
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val f: dynamic = jsConstruct(global_FormData, arrayOf<dynamic>(ev.target))
        val bench: dynamic = f.has("bench")
        val step: dynamic = f.has("step")
        global_base.submitProfile(ev)
        if (truth(global_state.profile)) {
            global_state.profile.bench = bench
            global_state.profile.step = step
            global_save()
            global_render()
        }
        return absent
    })
    global_home = (fun(): dynamic {
        val c: dynamic = global_currentCycle()
        if (truth((!truth(c)))) {
            return global_blocked()
        }
        val s: dynamic = global_E.stats(global_state.logs)
        val d: dynamic = global_todayDay()
        val l: dynamic = global_state.logs[d.date]
        val count: dynamic = jsOr(onPresent(l) { value117 -> onPresent(value117.items) { value118 -> value118.reduce((fun(n: dynamic, x: dynamic): dynamic {
            return jsAdd(n, x["actual"].filter((fun(a: dynamic): dynamic {
                return a.done
            })).length)
        }), 0) } }) { 0 }
        val total: dynamic = d.items.reduce((fun(n: dynamic, x: dynamic): dynamic {
            return jsAdd(n, x.sets)
        }), 0)
        val percent: dynamic = (if (truth(onPresent(l) { value119 -> value119.complete })) 100 else global_Math.round(jsMultiply(jsDivide(count, global_Math.max(total, 1)), 100)))
        val rank: dynamic = dynamicValue(jsonValue("[\"E\",\"D\",\"C\",\"B\",\"A\",\"S\"]"))[global_Math.min(5, global_Math.floor(jsDivide(jsSubtract(s.level, 1), 5)))]
        return ("<div class=\"dashboard-grid\"><div class=\"stack\"><section class=\"command-hero\"><div class=\"hero-lines\"></div><div class=\"row between\"><span class=\"eyebrow\">SISTEMA EN LÍNEA</span><span class=\"rank-pill\">RANGO " + textOf(rank) + "</span></div><p class=\"greeting\">Bienvenido, " + textOf(global_esc(global_state.profile.name)) + ".</p><h1>SUPERA<br><span>TUS LÍMITES.</span></h1><p class=\"muted\">Una misión a la vez. Tu próximo nivel empieza hoy.</p><div class=\"hero-footer\"><div><strong>NIVEL " + textOf(global_String(s.level).padStart(2, "0")) + "</strong><small>" + textOf(jsMod(s.xp, 300)) + " / 300 XP</small></div><div class=\"bar\"><i style=\"width:" + textOf(jsDivide(jsMod(s.xp, 300), 3)) + "%\"></i></div></div></section><div class=\"metrics\"><div class=\"metric accent-cyan\"><span class=\"mini-icon\">" + textOf(global_icon2("bolt")) + "</span><strong>" + textOf(s.streak) + "</strong><small>Días de racha</small></div><div class=\"metric accent-purple\"><span class=\"mini-icon\">" + textOf(global_icon2("target")) + "</span><strong>" + textOf(s.days) + "</strong><small>Misiones</small></div><div class=\"metric accent-green\"><span class=\"mini-icon\">" + textOf(global_icon2("dumbbell")) + "</span><strong>" + textOf(s.reps) + "</strong><small>Repeticiones</small></div></div><section class=\"mission-feature card\"><div class=\"row between\"><span class=\"eyebrow\">MISIÓN DE HOY</span><span class=\"badge\">DÍA " + textOf(jsAdd(global_E.diff(global_E.dateKey(), c.start), 1)) + " / 21</span></div><div class=\"mission-inner\"><div class=\"progress-ring\" style=\"--progress:" + textOf(jsMultiply(percent, 3.6)) + "deg\"><div><b>" + textOf(percent) + "%</b><small>" + textOf((if (truth(onPresent(l) { value120 -> value120.complete })) "FINALIZADA" else "PROGRESO")) + "</small></div></div><div><span class=\"tag\">" + textOf(global_kindLabel(d.kind)) + "</span><h2>" + textOf(global_esc(jsOr(d.title) { (if (truth((d.kind === "strength"))) "Entrenamiento de fuerza" else (if (truth((d.kind === "rest"))) "Recarga tu energía" else "Movimiento y recuperación")) })) + "</h2><p class=\"muted\">" + textOf(d.items.length) + " ejercicios · " + textOf(count) + "/" + textOf(total) + " series registradas</p></div></div><div class=\"action-pair\"><button class=\"btn primary\" onclick=\"go('workout')\">" + textOf((if (truth(onPresent(l) { value121 -> value121.complete })) "Ver registro" else "Empezar misión")) + " " + textOf(global_icon2("arrow")) + "</button>" + textOf((if (truth(jsAnd((!truth(onPresent(l) { value122 -> value122.complete }))) { (!truth(onPresent(l) { value123 -> value123.pain })) })) "<button class=\"btn ghost\" onclick=\"quickFinish()\">✓ Ya terminé</button>" else "")) + "</div>" + textOf((if (truth(onPresent(l) { value124 -> value124.pain })) "<p class=\"footnote\">Sesión detenida por dolor.</p>" else "")) + "</section></div><aside class=\"stack\"><div class=\"card\"><div class=\"row between\"><h3>Tus objetivos</h3><button class=\"text-button\" onclick=\"go('goals')\">Ver todos ↗</button></div>" + textOf(global_goalMini()) + "<button class=\"btn ghost full\" onclick=\"newGoal()\">+ Añadir objetivo</button></div><div class=\"card cycle-card\"><span class=\"eyebrow\">CICLO " + textOf(jsAdd(c.index, 1)) + "</span><h2>Tu arco de 21 días</h2>" + textOf(global_calendar(c)) + "<div class=\"legend\"><span>FUE Fuerza</span><span>CAR Cardio</span><span>REC Recuperación</span></div><p class=\"footnote\">" + textOf(global_esc(c.decision)) + "</p><button class=\"text-button\" onclick=\"go('plan')\">Ver todas las misiones →</button></div><button class=\"notification-banner\" onclick=\"go('reminders')\">" + textOf(global_icon2("bell")) + "<span><b>Recordatorios del sistema</b><small>" + textOf((if (truth(global_state.reminders.enabled)) "Horarios configurados · revisa permisos" else "Elige cuándo quieres entrenar")) + "</small></span><b>→</b></button></aside></div>")
    })
    global_calendar = (fun(c: dynamic): dynamic {
        return global_HunterKotlinScreens.calendarGrid(c)
    })
    global_workout = (fun(): dynamic {
        var h: dynamic = global_base.workout()
        val l: dynamic = global_getLog()
        if (truth((!truth(l)))) {
            return h
        }
        h = h.replace("Tu misión de hoy.", global_esc(jsOr(global_todayDay().title) { "Tu misión de hoy." }))
        if (truth(jsAnd((!truth(l.complete))) { (!truth(l.pain)) })) {
            h = h.replace("<div class=\"timer\">", ("<div class=\"quick-complete\"><span>¿Entrenaste sin registrar cada serie?</span><button class=\"btn success\" onclick=\"quickFinish()\">✓ Ya terminé</button></div><div class=\"timer\">"))
        }
        val container: dynamic = global_document.createElement("div")
        container.innerHTML = h
        val cards: dynamic = container.querySelectorAll("article.card")
        cards.forEach((fun(card: dynamic, i: dynamic): dynamic {
            val x: dynamic = l.items[i]
            val e: dynamic = global_E.byId(x.id)
            card.classList.add("exercise-session")
            if (truth(x.selfReported)) {
                card.classList.add("reported")
                onPresent(card.querySelector("[onclick^=\"editExercise\"]")) { value163 -> value163.remove() }
            }
            card.querySelector("h3").outerHTML = ("<button class=\"exercise-title\" onclick=\"technique('" + textOf(e.id) + "')\">" + textOf(e.name) + " ↗</button>")
            val actions: dynamic = global_document.createElement("div")
            actions.className = "exercise-actions"
            actions.innerHTML = ("<button class=\"btn ghost\" onclick=\"technique('" + textOf(e.id) + "')\">Técnica completa</button>" + textOf((if (truth(jsAnd((!truth(l.complete))) { (!truth(l.pain)) })) ("<button class=\"btn " + textOf((if (truth(x.selfReported)) "success" else "ghost")) + "\" onclick=\"completeExercise(" + textOf(i) + ")\">" + textOf((if (truth(x.selfReported)) "✓ Finalizado · reabrir" else "✓ Ya terminé este ejercicio")) + "</button>") else "")) + "" + textOf((if (truth(jsAnd(jsAnd((e.unit === "sec")) { (!truth(l.complete)) }) { (!truth(l.pain)) })) ("<button class=\"btn ghost\" onclick=\"startExerciseTimer(" + textOf(i) + ")\">▶ Temporizar " + textOf(x.target) + " s" + textOf((if (truth(e.sides)) " por lado" else "")) + "</button>") else "")) + "")
            if (truth(jsAnd(jsAnd((!truth(l.complete))) { (!truth(l.pain)) }) { x.rest })) {
                run {
                    val target170: dynamic = actions
                    target170.innerHTML = jsAdd(target170.innerHTML, ("<button class=\"btn ghost\" onclick=\"startTimer(" + textOf(x.rest) + ")\">Descansar " + textOf(x.rest) + " s</button>"))
                }
            }
            card.append(actions)
            if (truth(x.selfReported)) {
                val note: dynamic = global_document.createElement("p")
                note.className = "footnote"
                note.textContent = "Finalizado por confirmación. Solo se contabilizan las series marcadas individualmente."
                card.append(note)
            }
            return absent
        }))
        h = container.innerHTML
        h = h.replace("Como", "Como")
        return h
    })
    global_finish = (fun(): dynamic {
        val l: dynamic = global_getLog()
        if (truth(jsAnd(jsAnd(l) { (!truth(l.complete)) }) { l.items.some((fun(x: dynamic): dynamic {
            return jsAnd((!truth(x.selfReported))) { x["actual"].some((fun(a: dynamic): dynamic {
                return (!truth(a.done))
            })) }
        })) })) {
            return global_toast("Quedan ejercicios pendientes. Registra sus series o confirma que los terminaste.")
        }
        if (truth(onPresent(l) { value178 -> value178.items.some((fun(x: dynamic): dynamic {
            return x.selfReported
        })) })) {
            l.manual = true
        }
        global_base.finish()
        if (truth(onPresent(l) { value182 -> value182.complete })) {
            global_syncNative()
            global_pulse()
        }
        return absent
    })
    global_setInterval((fun(): dynamic {
        if (truth((!truth(global_workEnd)))) {
            return absent
        }
        val n: dynamic = global_Math.max(0, global_Math.ceil(jsDivide(jsSubtract(global_workEnd, global_Date.now()), 1000)))
        val clock: dynamic = global_Dollar("#workClock")
        if (truth(jsAnd(clock) { (clock.textContent !== global_String(n)) })) {
            clock.textContent = n
        }
        if (truth((!truth(n)))) {
            global_workEnd = 0
            onPresent(global_window.HunterV27) { value187 -> value187.alarm.finish("interval") }
            global_toast("Intervalo terminado. Descansa y registra lo que realizaste.")
            if (truth(clock)) {
                clock.textContent = "✓"
            }
        }
        return absent
    }), 250)
    global_technique = (fun(id: dynamic): dynamic {
        val e: dynamic = global_E.byId(id)
        global_modal(("<div class=\"tech-hero " + textOf((if (truth((e.category === "Cardio"))) "pink" else "")) + "\"><span>" + textOf(global_icon2((if (truth((e.category === "Cardio"))) "heart" else (if (truth((e.category === "Movilidad"))) "spark" else "dumbbell")))) + "</span><div><span class=\"eyebrow\">" + textOf(e.group) + " · " + textOf(global_Catalog.levels[e.level]) + "</span><h2>" + textOf(e.name) + "</h2></div></div><div class=\"tech-tags\"><span>" + textOf((if (truth(e.requirements.length)) e.requirements.map((fun(x: dynamic): dynamic {
            return global_Catalog.equipment[x]
        })).join(" + ") else "Sin equipo especial")) + "</span><span>" + textOf((if (truth((e.impact === "Con saltos"))) "Con saltos" else "Sin saltos")) + "</span></div><h3>1. Preparación</h3><p>" + textOf(e.setup) + "</p><h3>2. Ejecución paso a paso</h3><ol class=\"tech-steps\">" + textOf(e.instructions.map((fun(x: dynamic): dynamic {
            return ("<li>" + textOf(x.replace(jsRegExp("\\.\$", ""), "")) + ".</li>")
        })).join("")) + "</ol><h3>3. Respiración y ritmo</h3><p>" + textOf(e.breath) + " " + textOf(e.tempo) + "</p><h3>4. Control y errores frecuentes</h3><p>" + textOf(e.cue) + " " + textOf(e.control) + "</p><h3>5. Cómo registrar</h3><p>" + textOf((if (truth((e.unit === "sec"))) "Registra segundos de trabajo real." else "Registra únicamente las repeticiones realizadas con control.")) + " " + textOf((if (truth(e.sides)) "Completa ambos lados e introduce la cantidad por lado; el total sumará los dos." else "")) + " " + textOf((if (truth((e.equipment === "db"))) "En kg, introduce la carga total usada simultáneamente; si es unilateral, la de esa mano." else "")) + "</p>" + textOf((if (truth(e.alternative)) ("<button class=\"btn ghost full\" onclick=\"HunterActions.showAlternative('" + textOf(e.alternative) + "')\">Alternativa: " + textOf(global_E.byId(e.alternative).name) + " →</button>") else "")) + "<div class=\"notice\">Detén el ejercicio si aparece dolor. No completes un movimiento a costa de perder el control.</div>"))
        return absent
    })
    global_book = (fun(): dynamic {
        return ("<div class=\"page-heading\"><span class=\"eyebrow\">ARCHIVO DE ENTRENAMIENTO</span><h1>Todos los ejercicios</h1><p class=\"muted\">" + textOf(global_E.exercises.length) + " movimientos · fichas detalladas en español</p></div><input class=\"library-search\" aria-label=\"Buscar ejercicio\" placeholder=\"Busca sentadillas, press de banca, cardio…\" value=\"" + textOf(global_esc(global_query)) + "\" oninput=\"searchExercises(this.value)\"><div class=\"filter\">" + textOf(dynamicValue(jsonValue("[\"Todas\",\"Fuerza\",\"Cardio\",\"Movilidad\"]")).map((fun(c: dynamic): dynamic {
            return ("<button class=\"btn ghost " + textOf((if (truth((global_exerciseCategory === c))) "selected" else "")) + "\" onclick=\"HunterActions.filterCategory('" + textOf(c) + "')\">" + textOf(c) + "</button>")
        })).join("")) + "</div><div class=\"filter-selects\"><label><span>Grupo muscular</span><select onchange=\"HunterActions.filterMuscle(this)\"><option>Todos</option>" + textOf(dynamicValue(jsArray { array200 ->
            appendAll(array200, jsConstruct(global_Set, arrayOf<dynamic>(global_E.exercises.map((fun(e: dynamic): dynamic {
                return e.group
            })))))
        }).map((fun(g: dynamic): dynamic {
            return ("<option " + textOf((if (truth((g === global_exerciseMuscle))) "selected" else "")) + ">" + textOf(g) + "</option>")
        })).join("")) + "</select></label><label><span>Nivel</span><select onchange=\"HunterActions.filterLevel(this)\"><option value=\"all\">Todos</option>" + textOf(global_Catalog.levels.map((fun(n: dynamic, i: dynamic): dynamic {
            return ("<option value=\"" + textOf(i) + "\" " + textOf((if (truth((global_exerciseLevel === global_String(i)))) "selected" else "")) + ">" + textOf(n) + "</option>")
        })).join("")) + "</select></label><label><span>Equipo</span><select onchange=\"HunterActions.filterEquipment(this)\">" + textOf(dynamicValue(jsonValue("[[\"all\",\"Todos\"],[\"body\",\"Sin equipo\"],[\"db\",\"Mancuernas\"],[\"bench\",\"Banco\"],[\"step\",\"Plataforma\"]]")).map((fun(arg203: dynamic): dynamic {
            var v: dynamic = absent
            var n: dynamic = absent
            val unpacked204: dynamic = fromIterable(arg203)
            v = unpacked204[0]
            n = unpacked204[1]
            return ("<option value=\"" + textOf(v) + "\" " + textOf((if (truth((global_exerciseEquip === v))) "selected" else "")) + ">" + textOf(n) + "</option>")
        })).join("")) + "</select></label></div><p class=\"result-count\" id=\"exerciseCount\">" + textOf(global_exerciseList().length) + " resultados</p><div class=\"exercise-grid\" id=\"exerciseResults\">" + textOf(global_exerciseCards(global_exerciseList())) + "</div>")
    })
    global_exerciseCards = (fun(list: dynamic): dynamic {
        return jsOr(list.map((fun(e: dynamic): dynamic {
            return ("<button class=\"exercise-card\" onclick=\"technique('" + textOf(e.id) + "')\"><div class=\"exercise-symbol " + textOf((if (truth((e.category === "Cardio"))) "pink" else (if (truth((e.category === "Movilidad"))) "purple" else ""))) + "\">" + textOf(global_icon2((if (truth((e.category === "Cardio"))) "heart" else (if (truth((e.category === "Movilidad"))) "spark" else "dumbbell")))) + "</div><div><span class=\"tag\">" + textOf(e.group) + " · " + textOf(global_Catalog.levels[e.level]) + "</span><h3>" + textOf(e.name) + "</h3><p>" + textOf((if (truth(e.requirements.length)) e.requirements.map((fun(x: dynamic): dynamic {
                return global_Catalog.equipment[x]
            })).join(" + ") else "Sin equipo especial")) + "</p><small>" + textOf((if (truth((e.impact === "Con saltos"))) "Con saltos" else "Sin saltos")) + " · " + textOf((if (truth((e.unit === "sec"))) "Por tiempo" else "Por repeticiones")) + "</small></div><b>↗</b></button>")
        })).join("")) { "<div class=\"empty\">No hay ejercicios con esos filtros.</div>" }
    })
    global_searchExercises = (fun(v: dynamic): dynamic {
        global_query = v
        global_refreshExercises()
        return absent
    })
    global_history = (fun(): dynamic {
        var h: dynamic = global_base.history()
        val manual: dynamic = global_Object.values(global_state.logs).filter((fun(l: dynamic): dynamic {
            return jsAnd(l.complete) { l.manual }
        })).length
        h = h.replace("<div class=\"section-title\"><h2>Diario de misiones", ("<div class=\"notice\">" + textOf(manual) + " misiones finalizadas por confirmación rápida. Sus cantidades no registradas no se suman a las estadísticas.</div><div class=\"section-title\"><h2>Diario de misiones"))
        h = h.replace("30 ejercicios", "92 ejercicios")
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = h
        val entries: dynamic = global_Object.entries(global_state.logs).sort((fun(a: dynamic, b: dynamic): dynamic {
            return b[0].localeCompare(a[0])
        }))
        box.querySelectorAll(".history-item").forEach((fun(node: dynamic, i: dynamic): dynamic {
            var date: dynamic = absent
            var l: dynamic = absent
            val unpacked288: dynamic = fromIterable(entries[i])
            date = unpacked288[0]
            l = unpacked288[1]
            node.innerHTML = ("<summary><div><b>" + textOf(date) + "</b><br><small>" + textOf(global_kindLabel(l.kind)) + "</small></div><span class=\"badge\">" + textOf((if (truth(l.complete)) (if (truth(l.manual)) "✓ Confirmada" else "✓ Completada") else (if (truth(l.pain)) "Detenida" else "Parcial"))) + "</span></summary>" + textOf(l.items.map((fun(x: dynamic): dynamic {
                val e: dynamic = global_E.byId(x.id)
                return ("<p><b>" + textOf(e.name) + "</b>" + textOf((if (truth(x.selfReported)) "<br><span class=\"check\">Finalizado por confirmación</span>" else "")) + "<br>" + textOf(jsOr(x["actual"].filter((fun(a: dynamic): dynamic {
                    return a.done
                })).map((fun(a: dynamic): dynamic {
                    return ("" + textOf(a["value"]) + " " + textOf((if (truth((e.unit === "sec"))) "s" else "reps")) + "" + textOf((if (truth(e.sides)) " / lado" else "")) + "" + textOf((if (truth(a.kg)) jsAdd(jsAdd(" · ", a.kg), " kg") else "")) + "")
                })).join(" | ")) { "Sin cantidades registradas" }) + "</p>")
            })).join("")) + "<p>Esfuerzo: " + textOf(jsOr(l.rpe) { "—" }) + "/10 " + textOf((if (truth(l.pain)) "· Dolor registrado" else "")) + "</p>" + textOf((if (truth(l.note)) ("<p>" + textOf(global_esc(l.note)) + "</p>") else "")) + "" + textOf((if (truth(l.manual)) "<p class=\"footnote\">Finalización rápida: solo las cantidades registradas se suman al total.</p>" else "")) + "")
            return absent
        }))
        return box.innerHTML
    })
    global_profile = (fun(): dynamic {
        val h: dynamic = global_base.profile().replace("Hunter System 1.0", "Hunter System 2.0").replace("No hay cuenta ni sincronización.", "No hay cuenta ni sincronización. Los recordatorios funcionan en el dispositivo.")
        return jsAdd(("<div class=\"settings-shortcut\"><button class=\"btn\" onclick=\"go('reminders')\">" + textOf(global_icon2("bell")) + " Configurar notificaciones</button></div>"), h)
    })
    global_window.onNotificationPermissionResult = (fun(): dynamic {
        global_refreshReminderStatus()
        return absent
    })
    global_oldValidate = global_validateBackup
    global_validateBackup = (fun(s: dynamic): dynamic {
        if (truth((!truth(global_oldValidate(s))))) {
            return false
        }
        if (truth(jsAnd((s.goals !== absent)) { jsOr(jsOr((!truth(global_Array.isArray(s.goals)))) { jsMore(s.goals.length, 100) }) { s.goals.some((fun(g: dynamic): dynamic {
            return jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(g))) { (!truth(dynamicValue(jsRegExp("^g[a-z0-9]+\$", "")).test(g.id))) }) { (jsTypeOf(g.title) !== "string") }) { jsMore(g.title.length, 100) }) { (!truth(dynamicValue(jsonValue("[\"daily\",\"once\"]")).includes(g.repeat))) }) { (!truth(global_Array.isArray(g.dates))) }) { g.dates.some((fun(d: dynamic): dynamic {
                return (!truth(dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(d)))
            })) }
        })) } })) {
            return false
        }
        if (truth(jsAnd((s.customRoutines !== absent)) { jsOr(jsOr((!truth(global_Array.isArray(s.customRoutines)))) { jsMore(s.customRoutines.length, 50) }) { s.customRoutines.some((fun(r: dynamic): dynamic {
            return jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(r))) { (!truth(dynamicValue(jsRegExp("^custom[a-z0-9]+\$", "")).test(r.id))) }) { (jsTypeOf(r.name) !== "string") }) { jsMore(r.name.length, 60) }) { (!truth(global_Array.isArray(r.items))) }) { jsLess(r.items.length, 1) }) { jsMore(r.items.length, 10) }) { r.items.some((fun(x: dynamic): dynamic {
                return jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(global_E.byId(x.id)))) { (!truth(global_Number.isInteger(x.sets))) }) { jsLess(x.sets, 1) }) { jsMore(x.sets, 5) }) { (!truth(global_Number.isInteger(x.target))) }) { jsLess(x.target, 1) }) { jsMore(x.target, (if (truth((global_E.unitOf(x) === "sec"))) 600 else 50)) }
            })) }
        })) } })) {
            return false
        }
        if (truth(s.reminders)) {
            val r: dynamic = s.reminders
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr((jsTypeOf(r.enabled) !== "boolean")) { (!truth(dynamicValue(jsonValue("[\"daily\",\"interval\"]")).includes(r.mode))) }) { (!truth(dynamicValue(arrayOf<dynamic>(r.time, r.start, r.end)).every((fun(t: dynamic): dynamic {
                return dynamicValue(jsRegExp("^([01]\\d|2[0-3]):[0-5]\\d\$", "")).test(t)
            })))) }) { (!truth(dynamicValue(jsonValue("[1,2,3,4,6]")).includes(r.interval))) }) { (!truth(global_Array.isArray(r.days))) }) { r.days.some((fun(x: dynamic): dynamic {
                return jsOr(jsOr((!truth(global_Number.isInteger(x)))) { jsLess(x, 1) }) { jsMore(x, 7) }
            })) })) {
                return false
            }
        }
        return true
    })
    global_confirmImport = (fun(): dynamic {
        if (truth((!truth(global_pendingImport)))) {
            return absent
        }
        global_normalizeV2(global_pendingImport)
        global_base.confirmImport()
        if (truth(onPresent(global_window.Android) { value313 -> value313.setReminders })) {
            global_state.reminders.enabled = false
            global_save()
            global_Android.setReminders(global_JSON.stringify(global_state.reminders))
            global_toast("Copia restaurada. Activa los recordatorios en este dispositivo si lo deseas.")
        }
        return absent
    })
}
