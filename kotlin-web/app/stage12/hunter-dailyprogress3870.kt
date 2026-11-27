@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterdailyprogress3870

import hunter.browser.*

@JsName("Object") private external var global_Object: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("FormData") private external var global_FormData: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("rebuildPending13") private external var global_rebuildPending13: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("openCapacity13") private external var global_openCapacity13: dynamic
@JsName("capacityForm13") private external var global_capacityForm13: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("history") private external var global_history: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterDailyprogress3870() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var portable: dynamic = absent
        var integration: dynamic = absent
        var CATEGORY: dynamic = absent
        var escape: dynamic = absent
        var validDate: dynamic = absent
        var newer: dynamic = absent
        var ageUnder18: dynamic = absent
        var category: dynamic = absent
        var statusSet: dynamic = absent
        var symptomSet: dynamic = absent
        var readinessSet: dynamic = absent
        var unit: dynamic = absent
        var effortTable3871: dynamic = absent
        var bounds3871: dynamic = absent
        var calculate3871: dynamic = absent
        var adjustmentLabel3871: dynamic = absent
        var strengthCategory: dynamic = absent
        var text: dynamic = absent
        var rating: dynamic = absent
        var symptoms: dynamic = absent
        var measuredExercise: dynamic = absent
        var exerciseSessions: dynamic = absent
        var daysBetween: dynamic = absent
        var latestCategoryFeedback: dynamic = absent
        var decision: dynamic = absent
        var categoryDecisions: dynamic = absent
        var invalidateFuture: dynamic = absent
        var questionnaire: dynamic = absent
        var submit: dynamic = absent
        var panel: dynamic = absent
        var `open`: dynamic = absent
        bounds3871 = fun(ex: dynamic): dynamic {
            val range: dynamic = (if (truth(global_Array.isArray(onPresent(ex) { value11 -> value11.repRange19 }))) ex.repRange19 else js("[]"))
            val min: dynamic = (if (truth(jsAnd(global_Number.isFinite(global_Number(range[0]))) { jsAtLeast(global_Number(range[0]), 1) })) global_Math.ceil(global_Number(range[0])) else 1)
            val maxima: dynamic = dynamicValue(arrayOf<dynamic>(onPresent(ex) { value18 -> onPresent(value18.doseCap46) { value19 -> value19.target } }, range[1])).map(global_Number).filter((fun(n: dynamic): dynamic {
                return jsAnd(global_Number.isFinite(n)) { jsAtLeast(n, min) }
            }))
            return jsObject { obj20 ->
                obj20["min"] = min
                obj20["max"] = (if (truth(maxima.length)) global_Math.floor(run { val target22: dynamic = global_Math; invokeWith(target22.min, target22, jsArray { array21 ->
                    appendAll(array21, maxima)
                }) }) else null)
            }
        }
        calculate3871 = fun(ex: dynamic, effort: dynamic, baseline: dynamic): dynamic {
            val score: dynamic = global_Number(effort)
            val known: dynamic = jsAnd(jsAnd(global_Number.isInteger(score)) { jsAtLeast(score, 1) }) { jsAtMost(score, 10) }
            val unit: dynamic = (if (truth((onPresent(ex) { value25 -> value25.unit } === "sec"))) "segundos" else "repeticiones")
            val rawDelta: dynamic = (if (truth(known)) effortTable3871[score] else null)
            val limits: dynamic = bounds3871(ex)
            val base: dynamic = global_Number(baseline)
            val hasBase: dynamic = jsAnd(global_Number.isInteger(base)) { jsAtLeast(base, 1) }
            val eligible: dynamic = jsAnd(jsAnd((unit === "repeticiones")) { known }) { hasBase }
            val bounded: dynamic = jsAnd(eligible) { jsOr((limits.max !== null)) { jsAtMost(rawDelta, 0) } }
            val floor: dynamic = (if (truth(jsAtMost(rawDelta, 0))) global_Math.min(limits.min, base) else limits.min)
            val next: dynamic = (if (truth(bounded)) global_Math.max(floor, global_Math.min(nullableOr(limits.max) { global_Number.MAX_SAFE_INTEGER }, jsAdd(base, rawDelta))) else null)
            val appliedDelta: dynamic = (if (truth(bounded)) jsSubtract(next, base) else null)
            return jsObject { obj26 ->
                obj26["effort"] = (if (truth(known)) score else null)
                obj26["unit"] = unit
                obj26["rawDelta"] = rawDelta
                obj26["base"] = (if (truth(hasBase)) base else null)
                obj26["min"] = limits.min
                obj26["max"] = limits.max
                obj26["next"] = next
                obj26["appliedDelta"] = appliedDelta
                obj26["note"] = (if (truth((unit === "segundos"))) "La escala de repeticiones no se aplica a ejercicios medidos en segundos." else (if (truth((!truth(known)))) "Falta una valoración válida de esfuerzo." else (if (truth((!truth(hasBase)))) "Falta una referencia de repeticiones registrada." else (if (truth(jsAnd((limits.max === null)) { jsMore(rawDelta, 0) })) "Esta variante no dispone de un límite superior verificado; no se calcula un objetivo de aumento." else (if (truth((appliedDelta !== rawDelta))) "La propuesta queda limitada al intervalo configurado para esta variante." else "Referencia calculada dentro de los límites de la variante.")))))
            }
        }
        adjustmentLabel3871 = fun(d: dynamic): dynamic {
            val c: dynamic = onPresent(d) { value29 -> value29.reference3871 }
            if (truth(jsOr((!truth(c))) { (c.effort === null) })) {
                return ""
            }
            val signed: dynamic = (fun(n: dynamic): dynamic {
                return (if (truth(jsMore(n, 0))) jsAdd("+", n) else global_String(n))
            })
            val base: dynamic = ("Escala " + textOf(c.effort) + "/10: " + textOf(signed(c.rawDelta)) + " " + textOf(c.unit) + " (referencia del software).")
            val limit: dynamic = (if (truth((c.next === null))) " Sin objetivo calculado." else (" Referencia " + textOf(c.base) + " → " + textOf(c.next) + "; ajuste dentro de límites: " + textOf(signed(c.appliedDelta)) + "."))
            val guard: dynamic = (if (truth((d.action === "sugerencia"))) " Aumento opcional, nunca automático." else (if (truth(jsMore(c.rawDelta, 0))) " No se autoriza automáticamente una subida; primero se comprueban historial y recuperación." else ""))
            return ("<p class=\"dp-effort3871\">" + textOf(escape(jsAdd(jsAdd(jsAdd(jsAdd(base, limit), " "), c.note), guard))) + "</p>")
        }
        rating = fun(log: dynamic, id: dynamic): dynamic {
            val a: dynamic = onPresent(log) { value32 -> onPresent(value32.afterFeedback3870) { value33 -> value33.exercises } }
            return (if (truth(jsAnd(a) { global_Object.prototype.hasOwnProperty.call(a, id) })) a[id] else null)
        }
        symptoms = fun(log: dynamic): dynamic {
            val q: dynamic = onPresent(log) { value36 -> value36.afterFeedback3870 }
            return global_Boolean(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(onPresent(log) { value37 -> value37.pain }) { onPresent(log) { value38 -> value38.fatigue } }) { (onPresent(q) { value39 -> value39.symptom } === "pain") }) { (onPresent(q) { value40 -> value40.symptom } === "discomfort") }) { (onPresent(q) { value41 -> value41.symptom } === "tired") }) { (onPresent(q) { value42 -> value42.readiness } === "tired") }) { jsAtLeast(global_Number(onPresent(log) { value43 -> value43.rpe }), 8) })
        }
        measuredExercise = fun(log: dynamic, id: dynamic): dynamic {
            return global_Boolean(onPresent(log) { value46 -> onPresent(value46.items) { value47 -> value47.some((fun(x: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd((onPresent(x) { value48 -> value48.id } === id)) { (!truth(x.selfReported)) }) { global_Array.isArray(x["actual"]) }) { x["actual"].some((fun(a: dynamic): dynamic {
                    return jsAnd(jsAnd((onPresent(a) { value49 -> value49.done } === true)) { global_Number.isFinite(global_Number(a["value"])) }) { jsMore(global_Number(a["value"]), 0) }
                })) }
            })) } })
        }
        exerciseSessions = fun(logs: dynamic, id: dynamic, beforeDate: dynamic): dynamic {
            return global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg55: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked56: dynamic = fromIterable(arg55)
                d = unpacked56[0]
                l = unpacked56[1]
                return jsAnd(jsAnd(jsAnd(jsAnd(validDate(d)) { jsLess(d, beforeDate) }) { l }) { global_Array.isArray(l.items) }) { l.items.some((fun(x: dynamic): dynamic {
                    return (onPresent(x) { value57 -> value57.id } === id)
                })) }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return a[0].localeCompare(b[0])
            }))
        }
        daysBetween = fun(a: dynamic, b: dynamic): dynamic {
            val x: dynamic = global_Date.parse(jsAdd(a, "T12:00:00Z"))
            val y: dynamic = global_Date.parse(jsAdd(b, "T12:00:00Z"))
            return (if (truth(jsAnd(global_Number.isFinite(x)) { global_Number.isFinite(y) })) global_Math.round(jsDivide(jsSubtract(x, y), 86400000)) else 0)
        }
        latestCategoryFeedback = fun(logs: dynamic, cat: dynamic, beforeDate: dynamic): dynamic {
            return jsOr(global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg65: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked66: dynamic = fromIterable(arg65)
                d = unpacked66[0]
                l = unpacked66[1]
                return jsAnd(jsAnd(jsAnd(jsAnd(validDate(d)) { jsLess(d, beforeDate) }) { onPresent(l) { value67 -> value67.complete } }) { global_Array.isArray(l.items) }) { l.items.some((fun(x: dynamic): dynamic {
                    return (category(x.id) === cat)
                })) }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return b[0].localeCompare(a[0])
            }))[0]) { null }
        }
        decision = fun(logs: dynamic, id: dynamic, nextDate: dynamic, arg70: dynamic): dynamic {
            var profile: dynamic = absent
            profile = definedOr(arg70) { jsOr(onPresent(global_state) { value71 -> value71.profile }) { js("({})") } }
            var lastDate: dynamic = absent
            var log: dynamic = absent
            val exercise: dynamic = E.byId(id)
            val cat: dynamic = category(id)
            val catName: dynamic = jsOr(CATEGORY[cat]) { "Sin clasificación" }
            if (truth(jsOr(jsOr((!truth(exercise))) { (!truth(cat)) }) { (!truth(validDate(nextDate))) })) {
                return jsObject { obj72 ->
                    obj72["id"] = id
                    obj72["cat"] = cat
                    obj72["action"] = "sin_datos"
                    obj72["reason"] = "Ejercicio o categoría no disponible."
                }
            }
            val rows: dynamic = exerciseSessions(logs, id, nextDate)
            val last: dynamic = rows.at((-numeric(1)))
            val previous: dynamic = rows.slice((-numeric(6)))
            val recentAny: dynamic = latestCategoryFeedback(logs, cat, nextDate)
            val card: dynamic = jsObject { obj73 ->
                obj73["id"] = id
                obj73["name"] = exercise.name
                obj73["category"] = cat
                obj73["label"] = catName
                obj73["action"] = "mantener"
                obj73["reason"] = ""
                obj73["sessions"] = rows.length
                obj73["proposedDelta"] = 0
                obj73["unit"] = unit(exercise)
                obj73["lastDate"] = jsOr(onPresent(last) { value74 -> value74[0] }) { null }
                obj73["source"] = "registros propios de Hunter"
            }
            if (truth(jsOr(jsOr(jsOr(onPresent(profile) { value75 -> value75.limits }) { onPresent(profile) { value76 -> onPresent(value76.health) { value77 -> value77.acute } } }) { onPresent(profile) { value78 -> onPresent(value78.health) { value79 -> value79.special } } }) { jsAnd(onPresent(profile) { value80 -> onPresent(value80.health) { value81 -> value81.pain } }) { (profile.health.pain !== "none") } })) {
                card.action = "revisar"
                card.reason = "Las restricciones del perfil tienen prioridad. No se sugiere aumentar actividad."
                return card
            }
            if (truth((!truth(last)))) {
                card.action = "establecer_referencia"
                card.reason = "Aún no hay una sesión anterior de este ejercicio. Primero registra tu esfuerzo y las series realizadas."
                return card
            }
            val unpacked90: dynamic = fromIterable(last)
            lastDate = unpacked90[0]
            log = unpacked90[1]
            val q: dynamic = log.afterFeedback3870
            val lastItem: dynamic = onPresent(log.items) { value91 -> value91.find((fun(x: dynamic): dynamic {
                return (x.id === id)
            })) }
            val individualEffort: dynamic = global_Number(onPresent(q) { value92 -> onPresent(value92.exerciseEfforts) { value93 -> value93[id] } })
            val effectiveEffort: dynamic = (if (truth(jsAnd(jsAnd(global_Number.isInteger(individualEffort)) { jsAtLeast(individualEffort, 1) }) { jsAtMost(individualEffort, 10) })) individualEffort else log.rpe)
            card.reference3871 = calculate3871(exercise, effectiveEffort, global_Number(onPresent(lastItem) { value96 -> value96.target }))
            if (truth(jsOr(jsOr(log.pain) { (onPresent(q) { value97 -> value97.symptom } === "pain") }) { (onPresent(q) { value98 -> value98.symptom } === "discomfort") })) {
                card.action = "revisar"
                card.reason = "Se informaron molestias o dolor. No se propone aumentar; conviene detener el ejercicio doloroso y revisar la situación."
                return card
            }
            val exRating: dynamic = rating(log, id)
            if (truth((exRating === "unfinished"))) {
                card.action = "variacion"
                card.reason = "El registro anterior indica una actividad no completada. Conviene revisar una variante accesible, sin añadir volumen."
                return card
            }
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr((exRating === "hard")) { jsAtLeast(global_Number(log.rpe), 8) }) { jsAtLeast(global_Number(onPresent(card.reference3871) { value107 -> value107.effort }), 8) }) { (onPresent(q) { value108 -> value108.readiness } === "tired") }) { (onPresent(q) { value109 -> value109.symptom } === "tired") }) { log.fatigue })) {
                val ref: dynamic = card.reference3871
                card.action = "reducir"
                card.proposedDelta = (if (truth(jsLess(onPresent(ref) { value114 -> value114.rawDelta }, 0))) nullableOr(ref.appliedDelta) { 0 } else 0)
                val severity: dynamic = (if (truth(jsLess(onPresent(ref) { value115 -> value115.rawDelta }, 0))) (" Esfuerzo " + textOf(ref.effort) + "/10: referencia " + textOf(ref.rawDelta) + " repeticiones; " + textOf((if (truth((ref.appliedDelta === null))) "no hay ajuste validado para esta variante" else ("ajuste limitado " + textOf(ref.appliedDelta) + " por variante"))) + ".") else "")
                card.reason = jsAdd("La última valoración indicó esfuerzo elevado, fatiga o dificultad. Conviene revisar o suavizar la siguiente sesión.", severity)
                return card
            }
            if (truth(jsAnd(jsAnd(onPresent(recentAny) { value118 -> value118[0] }) { newer(recentAny[0], lastDate) }) { symptoms(recentAny[1]) })) {
                card.action = "mantener"
                card.reason = "Existe una valoración reciente de recuperación en esta categoría. Se mantiene la dosis hasta revisarla."
                return card
            }
            if (truth((!truth(measuredExercise(log, id))))) {
                card.reason = "Solo hay confirmación de la actividad, sin cantidad realizada de este ejercicio. No se propone aumentar sin volumen medido."
                return card
            }
            if (truth(jsOr((!truth(log.complete))) { (!truth(jsAnd(jsAtLeast(global_Number(log.rpe), 1)) { jsAtMost(global_Number(log.rpe), 10) })) })) {
                card.reason = "Falta una valoración completa de la sesión anterior. No se aumenta sin conocer cómo resultó."
                return card
            }
            val adequate: dynamic = previous.filter((fun(arg127: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked128: dynamic = fromIterable(arg127)
                d = unpacked128[0]
                l = unpacked128[1]
                val per: dynamic = global_Number(onPresent(l.afterFeedback3870) { value129 -> onPresent(value129.exerciseEfforts) { value130 -> value130[id] } })
                val effort: dynamic = (if (truth(jsAnd(jsAnd(global_Number.isInteger(per)) { jsAtLeast(per, 1) }) { jsAtMost(per, 10) })) per else global_Number(l.rpe))
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(l.complete) { (!truth(symptoms(l))) }) { measuredExercise(l, id) }) { (onPresent(l.afterFeedback3870) { value131 -> value131.readiness } === "recovered") }) { (rating(l, id) === "comfortable") }) { jsAtLeast(effort, 1) }) { jsAtMost(effort, 3) }
            }))
            val spaced: dynamic = jsAnd(jsAtLeast(adequate.length, 2)) { jsAtLeast(daysBetween(adequate.at((-numeric(1)))[0], adequate.at((-numeric(2)))[0]), 2) }
            if (truth(jsOr((!truth(q))) { (!truth(exRating)) })) {
                card.reason = "Ya hay un registro de ejercicio, pero falta la evaluación individual de dificultad. Mantener es la opción conservadora."
                return card
            }
            if (truth(jsOr((exRating === "normal")) { jsAtLeast(global_Number(onPresent(card.reference3871) { value134 -> value134.effort }), 4) })) {
                card.reason = "Última sesión adecuada: se mantiene la cantidad; puede mejorar el control y la técnica."
                return card
            }
            if (truth((!truth(spaced)))) {
                card.action = "consolidar"
                card.reason = "El ejercicio se sintió cómodo, pero aún faltan dos sesiones comparables en días separados antes de revisar una subida."
                return card
            }
            if (truth(jsAnd(jsLess(daysBetween(nextDate, lastDate), 2)) { strengthCategory(cat) })) {
                card.action = "recuperacion"
                card.reason = "La última práctica fue reciente. La prioridad de la siguiente jornada es la recuperación, no repetir una subida."
                return card
            }
            if (truth(jsOr(ageUnder18()) { jsLess(global_Number(onPresent(profile) { value145 -> value145.age }), 18) })) {
                card.action = "consolidar"
                card.reason = "Ya hay dos valoraciones cómodas: prioriza técnica, continuidad y recuperación. Cualquier aumento de carga requiere supervisión apropiada."
                return card
            }
            if (truth((!truth(portable.settings().volumeSuggestions)))) {
                card.action = "consolidar"
                card.reason = "Hay registros favorables, pero las orientaciones de volumen están desactivadas en Perfil → Sistema."
                return card
            }
            val ref: dynamic = card.reference3871
            if (truth((ref.unit !== "repeticiones"))) {
                card.reason = "Esta actividad se mide en segundos: no se aplica una escala de repeticiones. Mantén el tiempo actual hasta revisar las referencias de duración."
                return card
            }
            if (truth((ref.next === null))) {
                card.reason = "No se puede calcular una propuesta numérica sin una referencia y los límites propios de esta variante."
                return card
            }
            if (truth(jsAtMost(ref.rawDelta, 0))) {
                card.reason = "La escala de esfuerzo indica mantener o reducir, no aumentar las repeticiones."
                return card
            }
            if (truth(jsAtMost(ref.appliedDelta, 0))) {
                card.reason = "Se alcanzó el máximo configurado de la variante; no se aumenta la cantidad."
                return card
            }
            card.action = "sugerencia"
            card.proposedDelta = ref.appliedDelta
            card.reason = ("Esfuerzo " + textOf(ref.effort) + "/10: referencia " + textOf((if (truth(jsMore(ref.rawDelta, 0))) "+" else "")) + "" + textOf(ref.rawDelta) + " repeticiones. El límite de la variante permite " + textOf((if (truth(jsMore(ref.appliedDelta, 0))) "+" else "")) + "" + textOf(ref.appliedDelta) + ". Tras varias sesiones comparables y cómodas, es una revisión opcional; no se aplica automáticamente.")
            return card
        }
        categoryDecisions = fun(logs: dynamic, nextDate: dynamic): dynamic {
            val cats: dynamic = portable.categories
            return cats.map((fun(c: dynamic): dynamic {
                var date: dynamic = absent
                var l: dynamic = absent
                val latest: dynamic = latestCategoryFeedback(logs, c.code, nextDate)
                if (truth((!truth(latest)))) {
                    return jsObject { obj170 ->
                        obj170["category"] = c.code
                        obj170["label"] = c.label
                        obj170["action"] = "sin_datos"
                        obj170["reason"] = "Sin evaluación anterior de esta categoría."
                    }
                }
                val unpacked171: dynamic = fromIterable(latest)
                date = unpacked171[0]
                l = unpacked171[1]
                val items: dynamic = l.items.filter((fun(x: dynamic): dynamic {
                    return (category(x.id) === c.code)
                }))
                val parts: dynamic = items.map((fun(x: dynamic): dynamic {
                    return decision(logs, x.id, nextDate)
                }))
                val priority: dynamic = jsOr(jsOr(jsOr(parts.find((fun(x: dynamic): dynamic {
                    return dynamicValue(jsonValue("[\"revisar\",\"reducir\",\"variacion\"]")).includes(x.action)
                }))) { parts.find((fun(x: dynamic): dynamic {
                    return (x.action === "recuperacion")
                })) }) { parts.find((fun(x: dynamic): dynamic {
                    return (x.action === "sugerencia")
                })) }) { parts[0] }
                return jsObject { obj172 ->
                    obj172["category"] = c.code
                    obj172["label"] = c.label
                    obj172["lastDate"] = date
                    obj172["action"] = jsOr(onPresent(priority) { value173 -> value173.action }) { "mantener" }
                    obj172["reason"] = jsOr(onPresent(priority) { value174 -> value174.reason }) { "Mantener la referencia registrada." }
                }
            }))
        }
        invalidateFuture = fun(date: dynamic): dynamic {
            var count: dynamic = 0
            val today: dynamic = E.dateKey()
            if (true) {
                val iterator177: dynamic = iteratorOf(jsOr(global_state.cycles) { js("[]") })
                while (true) {
                    val next178: dynamic = iterator177.next()
                    if (truth(next178.done)) break
                    var cycle: dynamic = absent
                    cycle = next178.value
                    if (true) {
                        val iterator179: dynamic = iteratorOf(jsOr(cycle.days) { js("[]") })
                        while (true) {
                            val next180: dynamic = iterator179.next()
                            if (truth(next180.done)) break
                            var d: dynamic = absent
                            d = next180.value
                            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsAtMost(d.date, date)) { jsLess(d.date, today) }) { d.customized }) { d.routineId }) { d.creatorAssigned }) { d.manual }) { (d.scheduleChoice === "rest") })) {
                                continue
                            }
                            if (truth(onPresent(root.HunterDynamic381) { value181 -> run { val owner183: dynamic = value181; onPresent(owner183.locked) { method182 -> invokeWith(method182, owner183, arrayOf<dynamic>(d, jsOr(global_state.logs) { js("({})") })) } } })) {
                                continue
                            }
                            removeKey(d, "dynamicKey381")
                            run {
                                val previous184: dynamic = numeric(count)
                                count = jsAdd(previous184, 1)
                            }
                        }
                    }
                    if (truth(count)) {
                        removeKey(cycle, "dynamicKey381")
                    }
                }
            }
            return count
        }
        questionnaire = fun(date: dynamic): dynamic {
            val l: dynamic = onPresent(global_state.logs) { value187 -> value187[date] }
            if (truth((!truth(onPresent(l) { value188 -> value188.complete })))) {
                return ""
            }
            val q: dynamic = jsOr(l.afterFeedback3870) { js("({})") }
            val rpe: dynamic = jsOr(global_Number(l.rpe)) { 0 }
            val opt: dynamic = (fun(`value`: dynamic, label: dynamic, current: dynamic): dynamic {
                return ("<option value=\"" + textOf(`value`) + "\" " + textOf((if (truth((`value` === current))) "selected" else "")) + ">" + textOf(escape(label)) + "</option>")
            })
            val symptom: dynamic = jsOr(q.symptom) { "none" }
            val ready: dynamic = jsOr(q.readiness) { "unsure" }
            return ("<section class=\"card after-training dp-form3870\" id=\"capacityFeedback13\"><span class=\"eyebrow\">VALORACIÓN DEL ENTRENAMIENTO · " + textOf(escape(date)) + "</span>\n   <h3>¿Cómo fue la sesión?</h3><p>Un cuestionario general al concluir Rutinas o Misiones. La valoración solo se vincula con categorías realmente completadas, sin convertir registros antiguos; no se aplica ninguna subida automática.</p>\n   <form onsubmit=\"HunterDailyProgress3870.submit(event,'" + textOf(date) + "')\">\n   <label>Esfuerzo global (1–10)<select name=\"effort\" required><option value=\"\">Selecciona tu esfuerzo</option>" + textOf(global_Array.from(jsonValue("{\"length\":10}"), (fun(_: dynamic, i: dynamic): dynamic {
                return opt(global_String(jsAdd(i, 1)), ("" + textOf(jsAdd(i, 1)) + " / 10"), global_String(jsOr(rpe) { "" }))
            })).join("")) + "</select></label>\n   <div class=\"dp-scale3871\" role=\"note\" aria-label=\"Escala de referencia por esfuerzo\"><strong>Escala orientativa para ejercicios medidos en repeticiones</strong><div class=\"dp-scale-grid3871\"><span>1 → +5</span><span>2 → +3</span><span>3 → +1</span><span>4–7 → 0</span><span>8 → −1</span><span>9 → −2</span><span>10 → −3</span></div><small>Son cálculos teóricos sujetos a los límites de cada variante, historial, recuperación y revisión individual. No son obligaciones ni aumentan automáticamente la próxima sesión.</small></div>\n   <label>¿Tuviste molestias?<select name=\"symptom\" required>" + textOf(opt("none", "Sin molestias", symptom)) + "" + textOf(opt("tired", "Fatiga notable", symptom)) + "" + textOf(opt("discomfort", "Molestias con el movimiento", symptom)) + "" + textOf(opt("pain", "Dolor", symptom)) + "</select></label>\n   <label>¿Cómo te sientes después?<select name=\"readiness\" required>" + textOf(opt("recovered", "Bien recuperado", ready)) + "" + textOf(opt("unsure", "Necesito observar cómo me recupero", ready)) + "" + textOf(opt("tired", "Necesito más recuperación", ready)) + "</select></label>\n   <p class=\"footnote\">La valoración es general para la sesión. Las categorías conservan historiales independientes y solo reciben el esfuerzo cuando puede identificarse una categoría única completada.</p>\n   <label>Notas opcionales<textarea name=\"exerciseNote\" rows=\"2\" maxlength=\"500\" placeholder=\"Ejercicio, molestias, técnica o alternativa\">" + textOf(escape(jsOr(l.note) { "" })) + "</textarea></label>\n   <label>Reducción global opcional para próximas sesiones: <output>0%</output><input name=\"reduction\" type=\"range\" min=\"0\" max=\"60\" step=\"5\" value=\"0\" oninput=\"HunterActions.showPercent(this)\"></label>\n   <p class=\"footnote\">Si hubo dolor, no sigas forzando el movimiento. Las recomendaciones dependen de tu historial y no son una evaluación médica. No se aumenta automáticamente la carga, las series ni el peso.</p>\n   <button type=\"submit\" class=\"btn primary full\">Guardar y ver explicación del progreso</button></form></section>")
        }
        submit = fun(ev: dynamic, date: dynamic): dynamic {
            ev.preventDefault()
            if (truth((!truth(onPresent(ev.target) { value191 -> run { val owner193: dynamic = value191; onPresent(owner193.reportValidity) { method192 -> invokeWith(method192, owner193, js("[]")) } } })))) {
                return false
            }
            val l: dynamic = onPresent(global_state.logs) { value194 -> value194[date] }
            if (truth(jsOr((!truth(validDate(date)))) { (!truth(onPresent(l) { value195 -> value195.complete })) })) {
                return false
            }
            val f: dynamic = jsConstruct(global_FormData, arrayOf<dynamic>(ev.target))
            val effort: dynamic = global_Number(f["get"]("effort"))
            val symptom: dynamic = global_String(jsOr(f["get"]("symptom")) { "" })
            val readiness: dynamic = global_String(jsOr(f["get"]("readiness")) { "" })
            val reduction: dynamic = global_Number(f["get"]("reduction"))
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(global_Number.isInteger(effort)))) { jsLess(effort, 1) }) { jsMore(effort, 10) }) { (!truth(symptomSet.has(symptom))) }) { (!truth(readinessSet.has(readiness))) }) { (!truth(global_Number.isInteger(reduction))) }) { jsLess(reduction, 0) }) { jsMore(reduction, 60) }) { (jsMod(reduction, 5) !== 0) })) {
                return false
            }
            val answers: dynamic = js("({})")
            val individualEfforts: dynamic = js("({})")
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration196 = true
                while (true) {
                    if (!firstIteration196) {
                        run {
                            val previous197: dynamic = numeric(i)
                            i = jsAdd(previous197, 1)
                        }
                    }
                    firstIteration196 = false
                    if (!truth(jsLess(i, dynamicValue(jsOr(l.items) { js("[]") }).length))) break
                    val id: dynamic = l.items[i].id
                    val v: dynamic = global_String(jsOr(f["get"](("ex_" + textOf(i) + ""))) { "" })
                    if (truth(jsOr((!truth(E.byId(id)))) { jsAnd(v) { (!truth(statusSet.has(v))) } })) {
                        return false
                    }
                    if (truth((!truth(v)))) {
                        continue
                    }
                    val priority: dynamic = jsonValue("{\"comfortable\":0,\"normal\":1,\"hard\":2,\"unfinished\":3}")
                    if (truth(jsOr((!truth(hasKey(id, answers)))) { jsMore(priority[v], priority[answers[id]]) })) {
                        answers[id] = v
                    }
                    val raw: dynamic = global_String(jsOr(f["get"](("exEff_" + textOf(i) + ""))) { "" })
                    if (truth((raw !== ""))) {
                        val score: dynamic = global_Number(raw)
                        if (truth(jsOr(jsOr((!truth(global_Number.isInteger(score)))) { jsLess(score, 1) }) { jsMore(score, 10) })) {
                            return false
                        }
                        if (truth(jsOr((!truth(hasKey(id, individualEfforts)))) { jsMore(score, individualEfforts[id]) })) {
                            individualEfforts[id] = score
                        }
                    }
                }
            }
            l.rpe = effort
            l.note = global_String(jsOr(f["get"]("exerciseNote")) { "" }).slice(0, 500)
            l.afterFeedback3870 = jsObject { obj208 ->
                obj208["version"] = 1
                obj208["savedAt"] = jsConstruct(global_Date, js("[]")).toISOString()
                obj208["symptom"] = symptom
                obj208["readiness"] = readiness
                obj208["exercises"] = answers
                obj208["exerciseEfforts"] = individualEfforts
            }
            if (truth(jsOr((symptom === "tired")) { (readiness === "tired") })) {
                l.fatigue = true
            }
            val oldScale: dynamic = jsOr(global_Number(onPresent(root.TrainingIntensity) { value211 -> run { val owner213: dynamic = value211; onPresent(owner213.scale) { method212 -> invokeWith(method212, owner213, arrayOf<dynamic>(global_state.profile)) } } })) { 1 }
            val newScale: dynamic = global_Math.max(0.4, jsDivide(global_Math.round(jsMultiply(jsMultiply(oldScale, jsSubtract(1, jsDivide(reduction, 100))), 100)), 100))
            if (truth(jsMore(reduction, 0))) {
                global_state.profile.trainingScale = newScale
            }
            l.trainingFeedback = jsObject { obj218 ->
                copyFields(obj218, jsOr(l.trainingFeedback) { js("({})") })
                obj218["reduction"] = reduction
                obj218["before"] = oldScale
                obj218["after"] = newScale
                obj218["updatedAt"] = jsConstruct(global_Date, js("[]")).toISOString()
            }
            invalidateFuture(date)
            if (truth(jsAnd(jsMore(reduction, 0)) { (js("typeof rebuildPending13") === "function") })) {
                val active: dynamic = (if (truth((js("typeof currentCycle") === "function"))) global_currentCycle() else null)
                if (truth(active)) {
                    global_rebuildPending13(active)
                }
            }
            if (truth((js("typeof save") === "function"))) {
                global_save()
            }
            val next: dynamic = E["plus"](E.dateKey(), 1)
            val list: dynamic = js("[]")
            val seenCategories: dynamic = jsArray { array219 ->
                appendAll(array219, jsConstruct(global_Set, arrayOf<dynamic>(dynamicValue(jsOr(l.items) { js("[]") }).filter((fun(item: dynamic): dynamic {
                    return onPresent(item) { value235 -> onPresent(value235["actual"]) { value236 -> run { val owner238: dynamic = value236; onPresent(owner238.some) { method237 -> invokeWith(method237, owner238, arrayOf<dynamic>((fun(a: dynamic): dynamic {
                        return jsAnd((onPresent(a) { value239 -> value239.done } === true)) { jsMore(global_Number(a["value"]), 0) }
                    }))) } } } }
                })).map((fun(item: dynamic): dynamic {
                    return category(item.id)
                })).filter(global_Boolean))))
            }
            val categoryName: dynamic = (if (truth((seenCategories.length === 1))) jsOr(CATEGORY[seenCategories[0]]) { seenCategories[0] } else "Sesión general o mixta")
            val delta: dynamic = effortTable3871[effort]
            val label: dynamic = (if (truth(jsMore(delta, 0))) jsAdd("+", delta) else global_String(delta))
            val unifiedSummary: dynamic = ("<div class=\"dp-result3870\"><strong>" + textOf(escape(categoryName)) + "</strong><p>Valoración general " + textOf(effort) + "/10 · referencia de escala " + textOf(label) + " repeticiones cuando corresponda.</p><p>" + textOf((if (truth(jsOr(jsOr(jsAtLeast(effort, 8)) { (symptom !== "none") }) { (readiness !== "recovered") })) "Prioridad: recuperación y revisión de la siguiente sesión." else (if (truth(jsMore(delta, 0))) "El cálculo no aumenta automáticamente las cantidades: necesita referencia validada, límites de variante y una decisión voluntaria." else "La siguiente sesión mantiene sus cantidades orientativas."))) + "</p></div>")
            val h: dynamic = ("<section class=\"dp-form3870\"><span class=\"eyebrow\">VALORACIÓN REGISTRADA</span><h3>¿Qué cambiará y por qué?</h3>\n   <p>" + textOf((if (truth(jsOr((symptom === "pain")) { (symptom === "discomfort") })) "Se registraron molestias. No se propone aumentar actividad y es importante revisar los ejercicios que las causaron." else "La valoración se guardó para revisar la próxima sesión de la categoría, sin imponer cambios.")) + "</p>\n   " + textOf(unifiedSummary) + "\n   <button class=\"btn ghost full\" onclick=\"closeActiveModal()\">Continuar</button></section>")
            val box: dynamic = global_document.querySelector("#capacityFeedback13")
            if (truth(box)) {
                box.outerHTML = h
            } else {
                if (truth((js("typeof modal") === "function"))) {
                    global_modal(h)
                }
            }
            if (truth((js("typeof toast") === "function"))) {
                global_toast("Valoración individual guardada.")
            }
            return true
        }
        panel = fun(): dynamic {
            if (truth((!truth(onPresent(global_state) { value244 -> value244.profile })))) {
                return ""
            }
            val next: dynamic = E["plus"](E.dateKey(), 1)
            val recent: dynamic = integration.exerciseSummary(jsOr(global_state.logs) { js("({})") }, E.dateKey(), 28).slice(0, 7)
            val cats: dynamic = categoryDecisions(jsOr(global_state.logs) { js("({})") }, next)
            val latest: dynamic = global_Object.entries(jsOr(global_state.logs) { js("({})") }).filter((fun(arg251: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked252: dynamic = fromIterable(arg251)
                d = unpacked252[0]
                l = unpacked252[1]
                return jsAnd(jsAnd(jsAnd(jsAnd(validDate(d)) { jsAtMost(d, E.dateKey()) }) { onPresent(l) { value253 -> value253.complete } }) { (!truth(onPresent(l) { value254 -> value254.afterFeedback3870 })) }) { onPresent(l) { value255 -> onPresent(value255.items) { value256 -> value256.length } } }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return b[0].localeCompare(a[0])
            }))[0]
            return ("<section class=\"card dp-panel3870\" aria-label=\"Evolución por ejercicio y categoría\">\n   <span class=\"eyebrow\">EVOLUCIÓN DE UN DÍA AL OTRO</span><h3>Qué cambia y por qué</h3>\n   <p>Vista para la próxima sesión compatible, no una obligación de entrenar mañana. Se consideran esfuerzo, recuperación, dificultad individual y registros de cada categoría.</p>\n   " + textOf((if (truth(latest)) ("<button class=\"btn ghost\" type=\"button\" onclick=\"HunterDailyProgress3870.open('" + textOf(latest[0]) + "')\">Completar cuestionario de " + textOf(escape(latest[0])) + "</button>") else "")) + "\n   <div class=\"dp-categories3870\">" + textOf(cats.map((fun(c: dynamic): dynamic {
                return ("<div><strong>" + textOf(escape(c.label)) + "</strong><small>" + textOf(escape(jsOr(c.lastDate) { "Sin historial" })) + "</small><p>" + textOf(text(c.reason)) + "</p></div>")
            })).join("")) + "</div>\n   <h4>Últimos ejercicios registrados</h4>" + textOf((if (truth(recent.length)) recent.map((fun(r: dynamic): dynamic {
                val d: dynamic = decision(global_state.logs, r.id, next)
                return ("<div class=\"dp-result3870\"><strong>" + textOf(escape(r.name)) + "</strong><small>" + textOf(escape(d.label)) + " · " + textOf(escape(d.action.replace(jsRegExp("_", "g"), " "))) + "</small>" + textOf(adjustmentLabel3871(d)) + "<p>" + textOf(text(d.reason)) + "</p></div>")
            })).join("") else "<p>Registra tu primera sesión y responde el cuestionario para generar explicaciones individuales.</p>")) + "\n   <p class=\"footnote\">Las cantidades provienen de Hunter y sus registros; las 1.257 referencias originales de Asterion no se aplican sin una equivalencia verificada. No hay aumentos automáticos de peso ni actividad.</p>\n  </section>")
        }
        `open` = fun(date: dynamic): dynamic {
            if (truth((!truth(onPresent(global_state.logs) { value259 -> onPresent(value259[date]) { value260 -> value260.complete } })))) {
                return false
            }
            global_openCapacity13(date)
            return true
        }
        if (truth(root.HunterDailyProgress3870)) {
            return absent
        }
        E = root.Engine
        portable = root.HunterPortable3868
        integration = root.HunterIntegration3869
        if (truth(jsOr(jsOr(jsOr((!truth(E))) { (!truth(portable)) }) { (!truth(integration)) }) { (!truth(root.HunterCategoryProgress51)) })) {
            return absent
        }
        CATEGORY = global_Object.fromEntries(portable.categories.map((fun(c: dynamic): dynamic {
            return arrayOf<dynamic>(c.code, c.label)
        })))
        escape = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        validDate = (fun(s: dynamic): dynamic {
            return dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(s)
        })
        newer = (fun(a: dynamic, b: dynamic): dynamic {
            return jsMore(a, b)
        })
        ageUnder18 = (fun(): dynamic {
            return jsOr(jsLess(global_Number(onPresent(global_state) { value3 -> onPresent(value3.profile) { value4 -> value4.age } }), 18)) { (!truth(global_Number.isFinite(global_Number(onPresent(global_state) { value5 -> onPresent(value5.profile) { value6 -> value6.age } })))) }
        })
        category = (fun(id: dynamic): dynamic {
            return jsOr(onPresent(portable.exerciseMeta(id)) { value7 -> value7.category }) { null }
        })
        statusSet = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"comfortable\",\"normal\",\"hard\",\"unfinished\"]")))
        symptomSet = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"none\",\"tired\",\"discomfort\",\"pain\"]")))
        readinessSet = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"recovered\",\"unsure\",\"tired\"]")))
        unit = (fun(e: dynamic): dynamic {
            return (if (truth((onPresent(e) { value8 -> value8.unit } === "sec"))) "segundos" else "repeticiones")
        })
        effortTable3871 = global_Object.freeze(jsonValue("{\"1\":5,\"2\":3,\"3\":1,\"4\":0,\"5\":0,\"6\":0,\"7\":0,\"8\":-1,\"9\":-2,\"10\":-3}"))
        strengthCategory = (fun(c: dynamic): dynamic {
            return jsOr(jsOr((c === "tren_superior")) { (c === "tren_central") }) { (c === "tren_inferior") }
        })
        text = (fun(s: dynamic): dynamic {
            return escape(s).slice(0, 1000)
        })
        if (truth((js("typeof capacityForm13") === "function"))) {
            val prior: dynamic = global_capacityForm13
            global_capacityForm13 = (fun(date: dynamic): dynamic {
                val receiver261: dynamic = js("this")
                val callArgs262: dynamic = js("arguments")
                return (if (truth(jsAnd(date) { onPresent(global_state.logs) { value263 -> onPresent(value263[date]) { value264 -> value264.complete } } })) questionnaire(date) else prior.apply(receiver261, callArgs262))
            })
        }
        val CP: dynamic = root.HunterCategoryProgress51
        val previousAdjust: dynamic = CP.adjustItems
        val previousFeedback: dynamic = CP.feedback
        root.HunterCategoryProgress51 = global_Object.freeze(jsObject { obj267 ->
            copyFields(obj267, CP)
            obj267["feedback"] = fun(raw: dynamic, logs: dynamic, date: dynamic): dynamic {
                val original: dynamic = previousFeedback(raw, logs, date)
                val hasSymptoms: dynamic = global_Object.entries(jsOr(logs) { js("({})") }).some((fun(arg270: dynamic): dynamic {
                    var d: dynamic = absent
                    var l: dynamic = absent
                    val unpacked271: dynamic = fromIterable(arg270)
                    d = unpacked271[0]
                    l = unpacked271[1]
                    return jsAnd(jsAnd(jsAnd(jsAnd(validDate(d)) { jsLess(d, date) }) { jsAtMost(daysBetween(date, d), 7) }) { onPresent(l) { value272 -> value272.afterFeedback3870 } }) { symptoms(l) }
                }))
                return (if (truth(hasSymptoms)) jsObject { obj273 ->
                    copyFields(obj273, original)
                    obj273["strain"] = true
                    obj273["canProgress"] = false
                } else original)
            }
            obj267["adjustItems"] = fun(items: dynamic, ctx: dynamic): dynamic {
                val result: dynamic = previousAdjust(items, ctx)
                if (truth(jsOr((!truth(global_Array.isArray(items)))) { (!truth(global_Array.isArray(onPresent(result) { value276 -> value276.items }))) })) {
                    return result
                }
                var reduced: dynamic = 0
                val revised: dynamic = result.items.map((fun(x: dynamic, i: dynamic): dynamic {
                    val base: dynamic = jsOr(items[i]) { x }
                    val `out`: dynamic = jsObject { obj277 ->
                        copyFields(obj277, x)
                    }
                    if (truth(jsMore(global_Number(`out`.target), global_Number(base.target)))) {
                        `out`.target = base.target
                    }
                    if (truth(jsMore(global_Number(`out`.sets), global_Number(base.sets)))) {
                        `out`.sets = base.sets
                    }
                    val d: dynamic = decision(jsOr(onPresent(ctx) { value282 -> value282.logs }) { js("({})") }, `out`.id, jsOr(onPresent(ctx) { value283 -> value283.date }) { E.dateKey() }, onPresent(ctx) { value284 -> value284.profile })
                    if (truth(jsAnd(jsAnd(jsAnd((d.action === "reducir")) { (E.unitOf(`out`) !== "sec") }) { global_Number.isInteger(global_Number(base.target)) }) { jsMore(global_Number(base.target), 0) })) {
                        val ref: dynamic = calculate3871(E.byId(`out`.id), onPresent(d.reference3871) { value285 -> value285.effort }, global_Number(base.target))
                        if (truth(jsAnd(jsLess(ref.rawDelta, 0)) { (ref.next !== null) })) {
                            `out`.target = global_Math.max(1, global_Math.min(global_Number(`out`.target), ref.next))
                        }
                    }
                    val decrease: dynamic = jsOr(jsLess(global_Number(`out`.target), global_Number(base.target))) { jsLess(global_Number(`out`.sets), global_Number(base.sets)) }
                    if (truth(decrease)) {
                        run {
                            val previous288: dynamic = numeric(reduced)
                            reduced = jsAdd(previous288, 1)
                        }
                    }
                    if (truth(`out`.progression51)) {
                        `out`.progression51 = jsObject { obj291 ->
                            copyFields(obj291, `out`.progression51)
                            obj291["action"] = (if (truth(decrease)) "reduccion_suave" else "mantener")
                        }
                    }
                    `out`.dailyProgress3870 = jsObject { obj294 ->
                        obj294["action"] = (if (truth(decrease)) "reducir" else d.action)
                        obj294["reason"] = d.reason
                        obj294["category"] = d.category
                    }
                    return `out`
                }))
                return jsObject { obj295 ->
                    obj295["items"] = revised
                    obj295["progress"] = jsObject { obj296 ->
                        copyFields(obj296, result.progress)
                        obj296["action"] = (if (truth(reduced)) "reduccion_suave" else "mantener")
                        obj296["affected"] = reduced
                        obj296["dayToDay3870"] = true
                        obj296["reason"] = "Evaluación por ejercicio y categoría, con incremento opcional y no automático."
                    }
                }
            }
        })
        if (truth((js("typeof plan") === "function"))) {
            val prev: dynamic = global_plan
            global_plan = (fun(): dynamic {
                val receiver297: dynamic = js("this")
                val callArgs298: dynamic = js("arguments")
                return prev.apply(receiver297, callArgs298)
            })
        }
        if (truth((js("typeof history") === "function"))) {
            val prev: dynamic = global_history
            global_history = (fun(): dynamic {
                val receiver299: dynamic = js("this")
                val callArgs300: dynamic = js("arguments")
                return prev.apply(receiver299, callArgs300)
            })
        }
        if (truth((js("typeof validateBackup") === "function"))) {
            val prev: dynamic = global_validateBackup
            global_validateBackup = (fun(`data`: dynamic): dynamic {
                val receiver301: dynamic = js("this")
                val callArgs302: dynamic = js("arguments")
                if (truth((!truth(prev.apply(receiver301, callArgs302))))) {
                    return false
                }
                return global_Object.values(jsOr(`data`.logs) { js("({})") }).every((fun(log: dynamic): dynamic {
                    val q: dynamic = onPresent(log) { value303 -> value303.afterFeedback3870 }
                    if (truth((q === absent))) {
                        return true
                    }
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(q))) { (q.version !== 1) }) { (!truth(symptomSet.has(q.symptom))) }) { (!truth(readinessSet.has(q.readiness))) }) { (!truth(global_Number.isFinite(global_Date.parse(q.savedAt)))) }) { (!truth(q.exercises)) }) { (jsTypeOf(q.exercises) !== "object") }) { global_Array.isArray(q.exercises) }) { jsMore(global_Object.keys(q.exercises).length, 96) })) {
                        return false
                    }
                    if (truth((!truth(global_Object.entries(q.exercises).every((fun(arg304: dynamic): dynamic {
                        var id: dynamic = absent
                        var r: dynamic = absent
                        val unpacked305: dynamic = fromIterable(arg304)
                        id = unpacked305[0]
                        r = unpacked305[1]
                        return jsAnd((!truth((!truth(E.byId(id)))))) { statusSet.has(r) }
                    })))))) {
                        return false
                    }
                    if (truth((q.exerciseEfforts === absent))) {
                        return true
                    }
                    return jsAnd(jsAnd(jsAnd(jsAnd(q.exerciseEfforts) { (jsTypeOf(q.exerciseEfforts) === "object") }) { (!truth(global_Array.isArray(q.exerciseEfforts))) }) { jsAtMost(global_Object.keys(q.exerciseEfforts).length, 96) }) { global_Object.entries(q.exerciseEfforts).every((fun(arg306: dynamic): dynamic {
                        var id: dynamic = absent
                        var r: dynamic = absent
                        val unpacked307: dynamic = fromIterable(arg306)
                        id = unpacked307[0]
                        r = unpacked307[1]
                        return jsAnd(jsAnd(jsAnd((!truth((!truth(E.byId(id)))))) { global_Number.isInteger(r) }) { jsAtLeast(r, 1) }) { jsAtMost(r, 10) }
                    })) }
                }))
            })
        }
        root.HunterDailyProgress3870 = global_Object.freeze(jsObject { obj310 ->
            obj310["version"] = "38.71"
            obj310["questionnaire"] = questionnaire
            obj310["submit"] = submit
            obj310["decision"] = decision
            obj310["categoryDecisions"] = categoryDecisions
            obj310["panel"] = panel
            obj310["open"] = `open`
            obj310["invalidateFuture"] = invalidateFuture
            obj310["effortTable3871"] = effortTable3871
            obj310["calculate3871"] = calculate3871
            obj310["bounds3871"] = bounds3871
            obj310["audit"] = (fun(): dynamic {
                return jsObject { obj311 ->
                    obj311["exercises"] = E.exercises.length
                    obj311["categories"] = portable.categories.length
                    obj311["automaticVolumeIncreases"] = false
                    obj311["questionnaireLinks"] = jsonValue("[\"detailed\",\"quick\",\"guided\",\"daily-mission\"]")
                    obj311["numericAsterionProfilesApplied"] = 0
                }
            })
        })
        return absent
    }))(global_window)
}
