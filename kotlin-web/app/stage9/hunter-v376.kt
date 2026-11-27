@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterv376

import hunter.browser.*

@JsName("String") private external var global_String: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Training30") private external var global_Training30: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("profile") private external var global_profile: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("console") private external var global_console: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterV376() {
    dynamicValue((fun(root: dynamic): dynamic {
        var TYPES: dynamic = absent
        var LABELS: dynamic = absent
        var SHORT: dynamic = absent
        var ORDER: dynamic = absent
        var calendar: dynamic = absent
        var isActive: dynamic = absent
        var canAuto: dynamic = absent
        var countPlan: dynamic = absent
        var dayPreview376: dynamic = absent
        var swapDay376: dynamic = absent
        var adaptDay376: dynamic = absent
        var harmonize376: dynamic = absent
        var reviewData376: dynamic = absent
        var message376: dynamic = absent
        countPlan = fun(week: dynamic): dynamic {
            val n: dynamic = jsonValue("{\"train\":0,\"cardio\":0,\"mobility\":0,\"rest\":0}")
            if (true) {
                val iterator7: dynamic = iteratorOf(week)
                while (true) {
                    val next8: dynamic = iterator7.next()
                    if (truth(next8.done)) break
                    var type: dynamic = absent
                    type = next8.value
                    run {
                        val target9: dynamic = n
                        val key10: dynamic = type
                        val previous11: dynamic = numeric(target9[key10])
                        target9[key10] = jsAdd(previous11, 1)
                    }
                }
            }
            return n
        }
        dayPreview376 = fun(form: dynamic): dynamic {
            val week: dynamic = global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, n: dynamic): dynamic {
                return jsOr(onPresent(form) { value14 -> onPresent(value14.elements) { value15 -> onPresent(value15[jsAdd("day", n)]) { value16 -> value16["value"] } } }) { "rest" }
            }))
            val count: dynamic = countPlan(week)
            val `out`: dynamic = onPresent(form) { value17 -> value17.querySelector("#day-summary376") }
            if (truth((!truth(`out`)))) {
                return absent
            }
            val backToBack: dynamic = ORDER.some((fun(day: dynamic, i: dynamic): dynamic {
                return jsAnd((week[day] === "train")) { (week[ORDER[jsMod(jsAdd(i, 1), 7)]] === "train") }
            }))
            `out`.textContent = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(count.train, " fuerza · "), count.cardio), " cardio · "), count.mobility), " movilidad · "), count.rest), " descanso."), (if (truth(backToBack)) " Hay fuerza en días consecutivos: se reducirá el volumen de la segunda sesión." else ""))
            return absent
        }
        swapDay376 = fun(button: dynamic): dynamic {
            if (truth((!truth(onPresent(button) { value22 -> value22.form })))) {
                return absent
            }
            val day: dynamic = global_Number(button.dataset.day)
            val `field`: dynamic = button.form.elements[jsAdd("day", day)]
            if (truth((!truth(`field`)))) {
                return absent
            }
            val old: dynamic = (if (truth(TYPES.includes(`field`["value"]))) `field`["value"] else "rest")
            val next: dynamic = TYPES[jsMod(jsAdd(TYPES.indexOf(old), 1), TYPES.length)]
            `field`["value"] = next
            button.dataset.kind = next
            button.setAttribute("aria-label", jsAdd(jsAdd(jsAdd(global_Schedule.dayNames[day], ": "), LABELS[next]), ". Pulsar para cambiar el tipo de día"))
            button.setAttribute("aria-pressed", global_String((next !== "rest")))
            button.querySelector(".day-type376").textContent = SHORT[next]
            dayPreview376(button.form)
            return absent
        }
        adaptDay376 = fun(d: dynamic, p: dynamic, rotation: dynamic, priorStrength: dynamic, logs: dynamic, now: dynamic): dynamic {
            val choice: dynamic = calendar(p)[global_Schedule.weekday(d.date)]
            if (truth(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, now)) { d.customized }) { d.routineId }) { isActive(logs[d.date]) }) { onPresent(d.tasks) { value53 -> value53.some((fun(t: dynamic): dynamic {
                return t.done
            })) } })) {
                return false
            }
            val kind: dynamic = jsonValue("{\"rest\":\"rest\",\"train\":\"strength\",\"cardio\":\"cardio\",\"mobility\":\"recovery\"}")[choice]
            var changed: dynamic = false
            if (truth((choice === "rest"))) {
                if (truth(jsOr((d.kind !== "rest")) { onPresent(d.items) { value54 -> value54.length } })) {
                    d.kind = "rest"
                    d.items = js("[]")
                    d.tasks = jsonValue("[{\"id\":\"rest\",\"text\":\"Respetar mi día de descanso\",\"done\":false}]")
                    d.title = "Descanso elegido"
                    changed = true
                }
            } else {
                if (truth(jsAnd(canAuto(p)) { (d.kind !== kind) })) {
                    val planned: dynamic = global_Training30.choose(p, kind, rotation).filter((fun(x: dynamic): dynamic {
                        return global_Adapt.allowed(global_Engine.byId(x.id), global_GoalPlans.adjusted(p))
                    }))
                    if (truth(planned.length)) {
                        d.kind = kind
                        d.items = planned
                        d.tasks = js("[]")
                        d.title = jsAdd(LABELS[choice], " · sesión personalizada")
                        removeKey(d, "autoTemplateId")
                        changed = true
                    } else {
                        if (truth((!truth(onPresent(d.items) { value71 -> value71.length })))) {
                            d.kind = "rest"
                            d.title = "Revisar opciones compatibles"
                            d.tasks = arrayOf<dynamic>(jsObject { obj78 ->
                                obj78["id"] = "review"
                                obj78["text"] = jsAdd(jsAdd("No se hallaron ejercicios compatibles de ", LABELS[choice].toLowerCase()), ". Revisa el perfil y las restricciones.")
                                obj78["done"] = false
                            })
                        }
                    }
                }
            }
            if (truth(jsAnd(jsAnd(jsAnd((d.kind === "strength")) { priorStrength }) { onPresent(d.items) { value79 -> value79.length } }) { (!truth(d.loadReduced376)) })) {
                d.items = d.items.map((fun(x: dynamic): dynamic {
                    return jsObject { obj82 ->
                        copyFields(obj82, x)
                        obj82["sets"] = global_Math.max(1, global_Math.floor(jsMultiply(x.sets, 0.75)))
                        obj82["rest"] = global_Math.max(jsOr(global_Number(x.rest)) { 60 }, 90)
                    }
                }))
                d.planningNote = jsAdd(jsOr(d.planningNote) { "" }, " Fuerza consecutiva: volumen de esta sesión reducido para favorecer la recuperación.")
                d.loadReduced376 = true
                changed = true
            }
            if (truth(changed)) {
                d.schedulePolicy376 = 376
            }
            return changed
        }
        harmonize376 = fun(c: dynamic, p: dynamic, arg91: dynamic, arg92: dynamic, arg93: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg91) { js("({})") }
            var today: dynamic = absent
            today = definedOr(arg92) { global_Engine.dateKey() }
            var last: dynamic = absent
            last = definedOr(arg93) { null }
            if (truth(jsOr(jsOr(jsOr((!truth(onPresent(c) { value94 -> onPresent(value94.days) { value95 -> value95.length } }))) { (!truth(global_Schedule.valid(p))) }) { (!truth(canAuto(p))) }) { onPresent(c.reviews) { value96 -> value96.some((fun(r: dynamic): dynamic {
                return (r.action === "pausar")
            })) } })) {
                return 0
            }
            var changed: dynamic = 0
            var prev: dynamic = jsAnd((onPresent(last) { value97 -> onPresent(value97.days) { value98 -> onPresent(value98.at((-numeric(1)))) { value99 -> value99.kind } } } === "strength")) { (global_Engine.diff(c.days[0].date, last.days.at((-numeric(1))).date) === 1) }
            c.days.forEach((fun(d: dynamic, i: dynamic): dynamic {
                val was: dynamic = prev
                val oldKind: dynamic = d.kind
                if (truth(adaptDay376(d, p, jsAdd(jsMultiply(c.index, 7), i), was, logs, today))) {
                    run {
                        val previous100: dynamic = numeric(changed)
                        changed = jsAdd(previous100, 1)
                    }
                }
                prev = (d.kind === "strength")
                return absent
            }))
            return changed
        }
        reviewData376 = fun(arg110: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg110) { global_state.profile }
            if (truth((!truth(p)))) {
                return null
            }
            val now: dynamic = global_Engine.dateKey()
            val week: dynamic = calendar(p)
            val stats: dynamic = countPlan(week)
            val last7: dynamic = global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, i: dynamic): dynamic {
                return global_Engine["plus"](now, jsSubtract(i, 6))
            }))
            val logs: dynamic = last7.map((fun(date: dynamic): dynamic {
                return jsObject { obj113 ->
                    obj113["date"] = date
                    obj113["log"] = onPresent(global_state.logs) { value114 -> value114[date] }
                }
            })).filter((fun(x: dynamic): dynamic {
                return x.log
            }))
            val completed: dynamic = logs.filter((fun(x: dynamic): dynamic {
                return jsAnd(x.log.complete) { (!truth(x.log.pain)) }
            }))
            val effort: dynamic = completed.map((fun(x: dynamic): dynamic {
                return x.log.rpe
            })).filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAnd(global_Number.isFinite(x)) { jsAtLeast(x, 1) }) { jsAtMost(x, 10) }
            }))
            val rpe: dynamic = (if (truth(effort.length)) global_Number(dynamicValue(jsDivide(effort.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b)
            }), 0), effort.length)).toFixed(1)) else null)
            val pain: dynamic = logs.some((fun(x: dynamic): dynamic {
                return x.log.pain
            }))
            val fatigue: dynamic = logs.some((fun(x: dynamic): dynamic {
                return jsOr(x.log.fatigue) { jsAtLeast(x.log.rpe, 8) }
            }))
            val counts: dynamic = jsonValue("{\"train\":0,\"cardio\":0,\"mobility\":0}")
            if (true) {
                val iterator115: dynamic = iteratorOf(completed)
                while (true) {
                    val next116: dynamic = iterator115.next()
                    if (truth(next116.done)) break
                    var x: dynamic = absent
                    x = next116.value
                    val k: dynamic = (if (truth(jsOr((x.log.kind === "strength")) { (x.log.kind === "mixed") })) "train" else (if (truth((x.log.kind === "cardio"))) "cardio" else (if (truth((x.log.kind === "recovery"))) "mobility" else null)))
                    if (truth(k)) {
                        run {
                            val target117: dynamic = counts
                            val key118: dynamic = k
                            val previous119: dynamic = numeric(target117[key118])
                            target117[key118] = jsAdd(previous119, 1)
                        }
                    }
                }
            }
            val planned: dynamic = last7.filter((fun(date: dynamic): dynamic {
                return (week[global_Schedule.weekday(date)] !== "rest")
            })).length
            val adherence: dynamic = (if (truth(planned)) global_Math.round(jsDivide(jsMultiply(100, completed.filter((fun(x: dynamic): dynamic {
                return (week[global_Schedule.weekday(x.date)] !== "rest")
            })).length), planned)) else null)
            val review: dynamic = onPresent(global_state.cycles) { value120 -> value120.filter((fun(c: dynamic): dynamic {
                return jsAnd(global_Array.isArray(c.reviews)) { c.reviews.length }
            })).flatMap((fun(c: dynamic): dynamic {
                return c.reviews.map((fun(r: dynamic): dynamic {
                    return jsObject { obj122 ->
                        copyFields(obj122, r)
                        obj122["start"] = c.start
                    }
                }))
            })).at((-numeric(1))) }
            return jsObject { obj124 ->
                obj124["stats"] = stats
                obj124["counts"] = counts
                obj124["rpe"] = rpe
                obj124["pain"] = pain
                obj124["fatigue"] = fatigue
                obj124["adherence"] = adherence
                obj124["review"] = review
                obj124["completed"] = completed.length
            }
        }
        message376 = fun(): dynamic {
            val s: dynamic = reviewData376()
            if (truth((!truth(s)))) {
                return "Configura primero tu perfil."
            }
            var reason: dynamic = "Aún no hay suficientes registros comparables para aumentar la dosis de forma automática."
            if (truth(s.pain)) {
                reason = "Se registró dolor. El progreso se mantiene en pausa y conviene revisar las indicaciones antes de retomar el esfuerzo."
            } else {
                if (truth(s.fatigue)) {
                    reason = "Se registró fatiga o esfuerzo alto (8/10 o más). La revisión del plan prioriza recuperar la tolerancia."
                } else {
                    if (truth(s.review)) {
                        reason = jsAdd(jsAdd(jsAdd("Última revisión semanal: ", s.review.action), ". "), s.review.reason)
                    } else {
                        if (truth(jsAtLeast(s.completed, 3))) {
                            reason = "Se dispone de actividad registrada. La progresión semanal compara ejercicios, series completas y esfuerzo; no aumenta los kg de forma automática."
                        }
                    }
                }
            }
            val avg: dynamic = (if (truth((s.rpe === null))) "sin esfuerzo medio registrado" else jsAdd(jsAdd("esfuerzo medio ", s.rpe), "/10"))
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("Calendario: ", s.stats.train), " días de fuerza, "), s.stats.cardio), " de cardio, "), s.stats.mobility), " de movilidad y "), s.stats.rest), " de descanso. Últimos 7 días: "), s.counts.train), " sesiones de fuerza y "), s.counts.cardio), " de cardio finalizadas, "), avg), ". "), reason), " Se conservan los entrenamientos ya realizados y las sesiones personalizadas.")
        }
        TYPES = jsonValue("[\"rest\",\"train\",\"cardio\",\"mobility\"]")
        LABELS = jsonValue("{\"rest\":\"Descanso\",\"train\":\"Fuerza\",\"cardio\":\"Cardio\",\"mobility\":\"Movilidad\"}")
        SHORT = jsonValue("{\"rest\":\"DESC\",\"train\":\"FUERZA\",\"cardio\":\"CARDIO\",\"mobility\":\"MOVIL\"}")
        ORDER = jsonValue("[1,2,3,4,5,6,0]")
        val DAYS: dynamic = jsonValue("[\"D\",\"L\",\"M\",\"X\",\"J\",\"V\",\"S\"]")
        val escapeHTML: dynamic = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        calendar = (fun(p: dynamic): dynamic {
            return global_Schedule.defaults(jsOr(p) { js("({})") }).map((fun(x: dynamic): dynamic {
                return (if (truth(TYPES.includes(x))) x else "rest")
            }))
        })
        isActive = (fun(l: dynamic): dynamic {
            return jsAnd((!truth((!truth(l))))) { jsOr(jsOr(jsOr(l.complete) { l.pain }) { l.manual }) { onPresent(l.items) { value3 -> value3.some((fun(x: dynamic): dynamic {
                return jsOr(jsOr(x.missionState) { x.selfReported }) { onPresent(x["actual"]) { value4 -> value4.some((fun(a: dynamic): dynamic {
                    return a.done
                })) } }
            })) } } }
        })
        canAuto = (fun(p: dynamic): dynamic {
            return jsAnd((!truth((!truth(p))))) { global_Adapt.mode(global_GoalPlans.adjusted(p)).auto }
        })
        root.swapDay376 = swapDay376
        val earlierProfile: dynamic = global_profileForm
        global_profileForm = (fun(arg33: dynamic): dynamic {
            var initial: dynamic = absent
            initial = definedOr(arg33) { false }
            val doc: dynamic = global_document.createElement("div")
            doc.innerHTML = earlierProfile(initial)
            val form: dynamic = doc.querySelector("#profileForm")
            if (truth((!truth(form)))) {
                return doc.innerHTML
            }
            if (true) {
                val iterator36: dynamic = iteratorOf(form.querySelectorAll("button[onclick*=\"interpretGoal(\"]"))
                while (true) {
                    val next37: dynamic = iterator36.next()
                    if (truth(next37.done)) break
                    var button: dynamic = absent
                    button = next37.value
                    button.remove()
                }
            }
            val legacyExplanation: dynamic = form.querySelector("#intentResult")
            if (truth(legacyExplanation)) {
                legacyExplanation.hidden = true
                legacyExplanation.setAttribute("aria-hidden", "true")
            }
            val `run`: dynamic = form.querySelector(".goal-control374 .goal-apply374")
            if (truth(`run`)) {
                `run`.textContent = "✦ Interpretar y guardar objetivos"
                `run`.setAttribute("aria-label", "Interpretar y guardar tus objetivos con el acompañante")
            }
            val msg: dynamic = form.querySelector(".goal-control374 > .footnote")
            if (truth(msg)) {
                msg.textContent = "Puedes modificar tu meta. Al interpretar, se guardan los cambios y se actualizan las sesiones pendientes compatibles."
            }
            val editor: dynamic = form.querySelector(".week-editor")
            if (truth(editor)) {
                val week: dynamic = calendar(global_state.profile)
                val cards: dynamic = ORDER.map((fun(day: dynamic): dynamic {
                    val t: dynamic = week[day]
                    return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<div class=\"day-item376\"><button class=\"day-choice376\" type=\"button\" data-day=\"", day), "\" data-kind=\""), t), "\" aria-label=\""), global_Schedule.dayNames[day]), ": "), LABELS[t]), ". Pulsar para cambiar el tipo de día\" aria-pressed=\""), global_String((t !== "rest"))), "\" onclick=\"swapDay376(this)\"><span class=\"day-letter376\">"), DAYS[day]), "</span><small class=\"day-type376\">"), SHORT[t]), "</small></button><input name=\"day"), day), "\" value=\""), t), "\" type=\"hidden\"></div>")
                })).join("")
                editor.innerHTML = jsAdd(jsAdd("<div class=\"day-guide376\"><b>Tu semana de entrenamiento</b><p>Toca cada día para alternar entre <strong>Descanso → Fuerza → Cardio → Movilidad</strong>.</p></div><div class=\"day-week376\" role=\"group\" aria-label=\"Días y tipos de actividad\">", cards), "</div><div id=\"day-summary376\" class=\"day-summary376\" role=\"status\" aria-live=\"polite\"></div><p class=\"footnote\">Fuerza y cardio se asignan en los días elegidos. Los registros ya realizados no se modifican. La movilidad es opcional.</p>")
                val parent: dynamic = editor.closest(".week-block")
                if (truth(parent)) {
                    onPresent(parent.querySelector("h3")) { value46 -> value46.replaceChildren(global_document.createTextNode("¿Qué quieres entrenar cada día?")) }
                    if (true) {
                        val iterator47: dynamic = iteratorOf(parent.querySelectorAll(":scope > .footnote"))
                        while (true) {
                            val next48: dynamic = iterator47.next()
                            if (truth(next48.done)) break
                            var note: dynamic = absent
                            note = next48.value
                            if (truth(dynamicValue(jsRegExp("días consecutivos|alternan fuerza|semana nueva", "i")).test(jsOr(note.textContent) { "" }))) {
                                note.remove()
                            }
                        }
                    }
                    val desc: dynamic = parent.querySelector(":scope > .muted")
                    if (truth(desc)) {
                        desc.textContent = "Organiza fuerza, cardio, movilidad y descanso según tus preferencias."
                    }
                }
                dayPreview376(form)
            }
            return doc.innerHTML
        })
        val oldMake: dynamic = global_Engine.makeCycle
        global_Engine.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg105: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg105) { js("({})") }
            val c: dynamic = oldMake(p, start, index, last, logs)
            harmonize376(c, p, logs, start, last)
            return c
        })
        val oldCurrent: dynamic = global_currentCycle
        global_currentCycle = (fun(): dynamic {
            val c: dynamic = oldCurrent()
            if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
                return c
            }
            val last: dynamic = global_state.cycles.filter((fun(x: dynamic): dynamic {
                return jsLess(x.start, c.start)
            })).at((-numeric(1)))
            if (truth(harmonize376(c, global_state.profile, global_state.logs, global_Engine.dateKey(), last))) {
                global_save()
            }
            return c
        })
        val earlierAnswer: dynamic = global_answerCoach
        global_answerCoach = (fun(question: dynamic): dynamic {
            val q: dynamic = global_String(jsOr(question) { "" })
            if (truth(jsAnd(dynamicValue(jsRegExp("(?:progres(?:o|ión)|adaptaci[oó]n|rendimiento|fatiga|esfuerzo|calendario|semana|descanso|d[ií]as de (?:fuerza|cardio)|por qu[eé] (?:no|aument))", "i")).test(q)) { global_state.profile })) {
                return jsAdd(jsAdd(global_companionDisplayName(), ": "), message376())
            }
            return earlierAnswer(question)
        })
        val earlierProfileView: dynamic = global_profile
        global_profile = (fun(): dynamic {
            val original: dynamic = earlierProfileView()
            val s: dynamic = reviewData376()
            if (truth((!truth(s)))) {
                return original
            }
            val review: dynamic = jsAdd(jsAdd("<section class=\"card recovery-review376\" aria-label=\"Progreso y adaptación\"><span class=\"eyebrow\">TU COMPAÑERO · INFORME SEMANAL</span><h3>Adaptación según tus registros</h3><p>", escapeHTML(message376())), "</p><p class=\"footnote\">La interpretación es local y basada en reglas. La postura, las lesiones y la capacidad física requieren valoración individual; no se deducen de los registros.</p></section>")
            return jsAdd(original, review)
        })
        root.HunterV376 = jsObject { obj133 ->
            obj133["version"] = "37.6"
            obj133["dayPreview"] = dayPreview376
            obj133["review"] = reviewData376
            obj133["harmonize"] = harmonize376
            obj133["types"] = TYPES
        }
        try {
            global_render()
        } catch (e: dynamic) {
            global_console.warn("V37.6 render", e)
        }
        return absent
    }))(global_window)
}
