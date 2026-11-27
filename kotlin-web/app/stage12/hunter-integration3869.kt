@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterintegration3869

import hunter.browser.*

@JsName("Error") private external var global_Error: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Intl") private external var global_Intl: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("routineList") private external var global_routineList: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("history") private external var global_history: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterIntegration3869() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var CATS: dynamic = absent
        var labels: dynamic = absent
        var safe: dynamic = absent
        var categoryOf: dynamic = absent
        var preferences: dynamic = absent
        var day: dynamic = absent
        var sum: dynamic = absent
        var effectiveDate: dynamic = absent
        var prefers: dynamic = absent
        var activityLimit: dynamic = absent
        var lastFingerprint: dynamic = absent
        var invalidated: dynamic = absent
        var preferenceChanged: dynamic = absent
        var completedSets: dynamic = absent
        var exerciseSummary: dynamic = absent
        var referenceByExercise: dynamic = absent
        var ledger: dynamic = absent
        var planNotice: dynamic = absent
        var enhanceRoutines: dynamic = absent
        prefers = fun(exercises: dynamic): dynamic {
            val allowed: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(preferences().categories))
            if (truth((allowed.size === CATS.length))) {
                return exercises
            }
            val preferred: dynamic = exercises.filter((fun(e: dynamic): dynamic {
                return allowed.has(categoryOf(e.id))
            }))
            return (if (truth(preferred.length)) preferred else exercises)
        }
        activityLimit = fun(cap: dynamic): dynamic {
            if (truth((!truth(global_Number.isFinite(global_Number(cap)))))) {
                return cap
            }
            return global_Math.max(1, global_Math.min(global_Math.floor(global_Number(cap)), preferences().dailyActivities))
        }
        preferenceChanged = fun(): dynamic {
            val pref: dynamic = preferences()
            val next: dynamic = global_JSON.stringify(arrayOf<dynamic>(pref.categories, pref.dailyActivities))
            if (truth((next === lastFingerprint))) {
                return 0
            }
            lastFingerprint = next
            val today: dynamic = day()
            var count: dynamic = 0
            if (truth(jsOr(jsOr(jsOr((js("typeof state") === "undefined")) { (!truth(global_state)) }) { (!truth(global_Array.isArray(global_state.cycles))) }) { (!truth(global_state.profile)) })) {
                return 0
            }
            if (true) {
                val iterator13: dynamic = iteratorOf(global_state.cycles)
                while (true) {
                    val next14: dynamic = iterator13.next()
                    if (truth(next14.done)) break
                    var c: dynamic = absent
                    c = next14.value
                    if (truth((!truth(global_Array.isArray(c.days))))) {
                        continue
                    }
                    var changed: dynamic = false
                    if (true) {
                        val iterator15: dynamic = iteratorOf(c.days)
                        while (true) {
                            val next16: dynamic = iterator15.next()
                            if (truth(next16.done)) break
                            var d: dynamic = absent
                            d = next16.value
                            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, today)) { d.customized }) { d.routineId }) { d.creatorAssigned }) { d.manual }) { (d.scheduleChoice === "rest") })) {
                                continue
                            }
                            if (truth(onPresent(root.HunterDynamic381) { value17 -> run { val owner19: dynamic = value17; onPresent(owner19.locked) { method18 -> invokeWith(method18, owner19, arrayOf<dynamic>(d, jsOr(global_state.logs) { js("({})") })) } } })) {
                                continue
                            }
                            removeKey(d, "dynamicKey381")
                            changed = true
                            run {
                                val previous20: dynamic = numeric(count)
                                count = jsAdd(previous20, 1)
                            }
                        }
                    }
                    if (truth(changed)) {
                        removeKey(c, "dynamicKey381")
                    }
                }
            }
            run {
                invalidated = jsAdd(invalidated, count)
            }
            if (truth(jsAnd(count) { (js("typeof save") === "function") })) {
                global_save()
            }
            return count
        }
        completedSets = fun(logs: dynamic): dynamic {
            val `out`: dynamic = js("[]")
            if (true) {
                val iterator23: dynamic = iteratorOf(global_Object.entries(jsOr(logs) { js("({})") }))
                while (true) {
                    val next24: dynamic = iterator23.next()
                    if (truth(next24.done)) break
                    var date: dynamic = absent
                    var log: dynamic = absent
                    val unpacked25: dynamic = fromIterable(next24.value)
                    date = unpacked25[0]
                    log = unpacked25[1]
                    if (truth(jsOr(jsOr((!truth(dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(date)))) { (!truth(log)) }) { (!truth(global_Array.isArray(log.items))) })) {
                        continue
                    }
                    if (true) {
                        val iterator26: dynamic = iteratorOf(log.items)
                        while (true) {
                            val next27: dynamic = iterator26.next()
                            if (truth(next27.done)) break
                            var item: dynamic = absent
                            item = next27.value
                            val e: dynamic = E.byId(onPresent(item) { value28 -> value28.id })
                            val category: dynamic = categoryOf(onPresent(item) { value29 -> value29.id })
                            if (truth(jsOr(jsOr((!truth(e))) { (!truth(category)) }) { (!truth(global_Array.isArray(item["actual"]))) })) {
                                continue
                            }
                            val unit: dynamic = (if (truth((e.unit === "sec"))) "seconds" else "reps")
                            val factor: dynamic = (if (truth(E.sidesOf(item))) 2 else 1)
                            val done: dynamic = item["actual"].filter((fun(a: dynamic): dynamic {
                                return jsAnd(jsAnd(jsAnd(jsAnd(a) { (a.done === true) }) { global_Number.isFinite(global_Number(a["value"])) }) { jsMore(global_Number(a["value"]), 0) }) { jsAtMost(global_Number(a["value"]), 10000) }
                            }))
                            if (truth((!truth(done.length)))) {
                                continue
                            }
                            val quantity: dynamic = sum(done.map((fun(a: dynamic): dynamic {
                                return jsMultiply(global_Number(a["value"]), factor)
                            })))
                            `out`.push(jsObject { obj30 ->
                                obj30["date"] = date
                                obj30["id"] = e.id
                                obj30["name"] = e.name
                                obj30["category"] = category
                                obj30["unit"] = unit
                                obj30["sets"] = done.length
                                obj30["quantity"] = quantity
                                obj30["effort"] = (if (truth(global_Number.isFinite(global_Number(log.rpe)))) global_Number(log.rpe) else null)
                                obj30["interrupted"] = (log.pain === true)
                            })
                        }
                    }
                }
            }
            return `out`.sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(a.date.localeCompare(b.date)) { a.id.localeCompare(b.id) }
            }))
        }
        exerciseSummary = fun(logs: dynamic, arg33: dynamic, arg34: dynamic): dynamic {
            var today: dynamic = absent
            today = definedOr(arg33) { effectiveDate() }
            var windowDays: dynamic = absent
            windowDays = definedOr(arg34) { 28 }
            val start: dynamic = E["plus"](today, jsSubtract(1, windowDays))
            val buckets: dynamic = jsConstruct(global_Map, js("[]"))
            if (true) {
                val iterator35: dynamic = iteratorOf(completedSets(logs))
                while (true) {
                    val next36: dynamic = iterator35.next()
                    if (truth(next36.done)) break
                    var r: dynamic = absent
                    r = next36.value
                    if (truth(jsOr(jsLess(r.date, start)) { jsMore(r.date, today) })) {
                        continue
                    }
                    val key: dynamic = jsAdd(jsAdd(r.id, "|"), r.unit)
                    var b: dynamic = buckets["get"](key)
                    if (truth((!truth(b)))) {
                        b = jsObject { obj37 ->
                            obj37["id"] = r.id
                            obj37["name"] = r.name
                            obj37["category"] = r.category
                            obj37["unit"] = r.unit
                            obj37["quantity"] = 0
                            obj37["sets"] = 0
                            obj37["days"] = jsConstruct(global_Set, js("[]"))
                            obj37["last"] = ""
                            obj37["first"] = ""
                        }
                        buckets["set"](key, b)
                    }
                    run {
                        val target38: dynamic = b
                        target38.quantity = jsAdd(target38.quantity, r.quantity)
                    }
                    run {
                        val target40: dynamic = b
                        target40.sets = jsAdd(target40.sets, r.sets)
                    }
                    b.days.add(r.date)
                    if (truth((!truth(b.first)))) {
                        b.first = r.date
                    }
                    b.last = r.date
                }
            }
            return dynamicValue(jsArray { array50 ->
                appendAll(array50, buckets.values())
            }).map((fun(b: dynamic): dynamic {
                return jsObject { obj51 ->
                    copyFields(obj51, b)
                    obj51["days"] = b.days.size
                }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(jsOr(b.last.localeCompare(a.last)) { jsSubtract(b.sets, a.sets) }) { a.name.localeCompare(b.name, "es") }
            }))
        }
        referenceByExercise = fun(logs: dynamic, id: dynamic): dynamic {
            val rows: dynamic = completedSets(logs).filter((fun(r: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd((r.id === id)) { (!truth(r.interrupted)) }) { (r.effort !== null) }) { jsAtLeast(r.effort, 1) }) { jsAtMost(r.effort, 7) }
            }))
            if (truth((!truth(rows.length)))) {
                return null
            }
            val first: dynamic = rows[0]
            return jsObject { obj54 ->
                obj54["date"] = first.date
                obj54["id"] = id
                obj54["category"] = first.category
                obj54["unit"] = first.unit
                obj54["quantity"] = first.quantity
                obj54["sets"] = first.sets
                obj54["source"] = "primera sesión registrada con esfuerzo informado, no dosis recomendada"
            }
        }
        ledger = fun(): dynamic {
            val recent: dynamic = exerciseSummary(jsOr(global_state.logs) { js("({})") })
            val counts: dynamic = recent.length
            val total: dynamic = recent.reduce((fun(t: dynamic, r: dynamic): dynamic {
                return jsAdd(t, r.sets)
            }), 0)
            val shown: dynamic = recent.slice(0, 10)
            val html: dynamic = shown.map((fun(r: dynamic): dynamic {
                val ref: dynamic = referenceByExercise(jsOr(global_state.logs) { js("({})") }, r.id)
                val base: dynamic = (if (truth(jsAnd(ref) { (ref.unit === r.unit) })) (" · referencia personal: " + textOf(ref.quantity.toLocaleString("es")) + " " + textOf((if (truth((r.unit === "seconds"))) "s" else "rep")) + " (" + textOf(ref.date) + ")") else "")
                return ("<li><span><strong>" + textOf(safe(r.name)) + "</strong><small>" + textOf(safe(labels[r.category])) + " · " + textOf(r.days) + " días · " + textOf(r.sets) + " series" + textOf(base) + "</small></span><b>" + textOf(r.quantity.toLocaleString("es")) + " " + textOf((if (truth((r.unit === "seconds"))) "s" else "rep")) + "</b></li>")
            })).join("")
            return ("<section class=\"card portable-detail3869\" aria-label=\"Progreso por ejercicio\"><div class=\"portable-detail-heading3869\">\n   <h3>Registro por ejercicio</h3><p>" + textOf(counts) + " ejercicios con cantidades en los últimos 28 días · " + textOf(total) + " series registradas.</p></div>\n   " + textOf((if (truth(counts)) ("<ul>" + textOf(html) + "</ul>" + textOf((if (truth(jsMore(counts, 10))) ("<p class=\"portable-hint3869\">Se muestran los 10 ejercicios con actividad más reciente.</p>") else "")) + "") else "<p class=\"portable-hint3869\">Registra una serie terminada para ver el seguimiento individual.</p>")) + "\n   <p class=\"portable-hint3869\">Las referencias individuales provienen de tu historial, nunca de las dosis importadas de Asterion. Repeticiones y segundos no se mezclan. No son objetivos de carga.</p>\n  </section>")
        }
        planNotice = fun(): dynamic {
            val c: dynamic = preferences()
            val names: dynamic = c.categories.map((fun(k: dynamic): dynamic {
                return labels[k]
            })).join(" · ")
            return ("<div class=\"portable-plan3869\" role=\"note\"><strong>Planificación según tus categorías</strong><p>Prioridad: " + textOf(safe(names)) + ". Máximo orientativo de " + textOf(c.dailyActivities) + " actividades por sesión, respetando las restricciones y el calendario. Si no hay ejercicios compatibles de las categorías elegidas, se conserva el tipo de entrenamiento y se utilizan alternativas seguras.</p></div>")
        }
        enhanceRoutines = fun(): dynamic {
            val names: dynamic = preferences().categories.map((fun(c: dynamic): dynamic {
                return labels[c]
            })).join(" · ")
            return ("<div class=\"portable-plan3869\" role=\"note\"><strong>Rutinas y categorías</strong><p>Las rutinas dinámicas dan prioridad a " + textOf(safe(names)) + ". Las rutinas guardadas y las plantillas anteriores permanecen disponibles y no cambian sus series.</p></div>")
        }
        if (truth(root.HunterIntegration3869)) {
            return absent
        }
        E = root.Engine
        val P: dynamic = root.HunterPortable3868
        val D: dynamic = root.HunterPortableData3868
        if (truth(jsOr(jsOr(jsOr((!truth(E))) { (!truth(P)) }) { (!truth(D)) }) { (D.hunterRows.length !== 1200) })) {
            throw global_Error("Integration3869: faltan datos base")
        }
        CATS = D.categories.map((fun(c: dynamic): dynamic {
            return c.code
        }))
        val CSET: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(CATS))
        labels = global_Object.fromEntries(D.categories.map((fun(c: dynamic): dynamic {
            return arrayOf<dynamic>(c.code, c.label)
        })))
        safe = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        categoryOf = (fun(id: dynamic): dynamic {
            return jsOr(onPresent(P.exerciseMeta(id)) { value3 -> value3.category }) { null }
        })
        preferences = (fun(): dynamic {
            return P.settings()
        })
        day = (fun(): dynamic {
            return E.dateKey()
        })
        sum = (fun(x: dynamic): dynamic {
            return x.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b)
            }), 0)
        })
        effectiveDate = (fun(): dynamic {
            val zone: dynamic = preferences().timeZone
            if (truth(jsOr((!truth(zone))) { (zone === "system") })) {
                return day()
            }
            try {
                val parts: dynamic = jsConstruct(global_Intl.DateTimeFormat, arrayOf<dynamic>("en-US", jsObject { obj5 ->
                    obj5["timeZone"] = zone
                    obj5["year"] = "numeric"
                    obj5["month"] = "2-digit"
                    obj5["day"] = "2-digit"
                })).formatToParts(jsConstruct(global_Date, js("[]")))
                val `get`: dynamic = (fun(k: dynamic): dynamic {
                    return onPresent(parts.find((fun(p: dynamic): dynamic {
                        return (p.type === k)
                    }))) { value6 -> value6["value"] }
                })
                return ("" + textOf(`get`("year")) + "-" + textOf(`get`("month")) + "-" + textOf(`get`("day")) + "")
            } catch (_: dynamic) {
                return day()
            }
            return absent
        })
        lastFingerprint = global_JSON.stringify(arrayOf<dynamic>(preferences().categories, preferences().dailyActivities))
        invalidated = 0
        if (truth((js("typeof routineList") === "function"))) {
            val previous: dynamic = global_routineList
            global_routineList = (fun(): dynamic {
                val receiver61: dynamic = js("this")
                val callArgs62: dynamic = js("arguments")
                val list: dynamic = previous.apply(receiver61, callArgs62)
                val selected: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(preferences().categories))
                if (truth((selected.size === CATS.length))) {
                    return list
                }
                val score: dynamic = (fun(r: dynamic): dynamic {
                    if (truth(jsOr((!truth(global_Array.isArray(onPresent(r) { value63 -> value63.items })))) { (!truth(r.items.length)) })) {
                        return 0
                    }
                    val cats: dynamic = r.items.map((fun(x: dynamic): dynamic {
                        return categoryOf(x.id)
                    })).filter(global_Boolean)
                    return (if (truth(cats.length)) jsDivide(cats.filter((fun(c: dynamic): dynamic {
                        return selected.has(c)
                    })).length, cats.length) else 0)
                })
                return list.map((fun(r: dynamic, i: dynamic): dynamic {
                    return jsObject { obj67 ->
                        obj67["r"] = r
                        obj67["i"] = i
                        obj67["score"] = score(r)
                    }
                })).sort((fun(a: dynamic, b: dynamic): dynamic {
                    return jsOr(jsSubtract(b.score, a.score)) { jsSubtract(a.i, b.i) }
                })).map((fun(x: dynamic): dynamic {
                    return x.r
                }))
            })
        }
        if (truth((js("typeof plan") === "function"))) {
            val original: dynamic = global_plan
            global_plan = (fun(): dynamic {
                val receiver68: dynamic = js("this")
                val callArgs69: dynamic = js("arguments")
                return jsAdd(planNotice(), original.apply(receiver68, callArgs69))
            })
        }
        if (truth((js("typeof routines") === "function"))) {
            val original: dynamic = global_routines
            global_routines = (fun(): dynamic {
                val receiver70: dynamic = js("this")
                val callArgs71: dynamic = js("arguments")
                return jsAdd(enhanceRoutines(), original.apply(receiver70, callArgs71))
            })
        }
        if (truth((js("typeof history") === "function"))) {
            val original: dynamic = global_history
            global_history = (fun(): dynamic {
                val receiver72: dynamic = js("this")
                val callArgs73: dynamic = js("arguments")
                return original.apply(receiver72, callArgs73).replace("</section>", jsAdd("</section>", ledger()))
            })
        }
        if (truth((js("typeof profileForm") === "function"))) {
            val original: dynamic = global_profileForm
            global_profileForm = (fun(initial: dynamic): dynamic {
                val receiver74: dynamic = js("this")
                val callArgs75: dynamic = js("arguments")
                val html: dynamic = original.apply(receiver74, callArgs75)
                if (truth((!truth(initial)))) {
                    return html
                }
                val note: dynamic = ("<div class=\"portable-plan3869\" role=\"note\"><strong>Configuración inicial de Asterion</strong><p>En la pestaña «Sistema» puedes elegir categorías, experiencia y cantidad orientativa de actividades. Estos ajustes afectan solo a nuevas sesiones, no al historial.</p><button class=\"btn ghost\" type=\"button\" onclick=\"HunterPanels3857.show(3)\">Configurar categorías →</button></div>")
                return html.replace("<form id=\"profileForm\"", jsAdd(note, "<form id=\"profileForm\""))
            })
        }
        root.HunterIntegration3869 = global_Object.freeze(jsObject { obj78 ->
            obj78["version"] = "38.69"
            obj78["prefers"] = prefers
            obj78["activityLimit"] = activityLimit
            obj78["preferenceChanged"] = preferenceChanged
            obj78["categoryOf"] = categoryOf
            obj78["completedSets"] = completedSets
            obj78["exerciseSummary"] = exerciseSummary
            obj78["referenceByExercise"] = referenceByExercise
            obj78["effectiveDate"] = effectiveDate
            obj78["audit"] = (fun(): dynamic {
                return jsObject { obj79 ->
                    obj79["exercises"] = D.hunterRows.length
                    obj79["matchedCandidates"] = D.matchedCount
                    obj79["invalidatedFutureDays"] = invalidated
                    obj79["categories"] = CATS.slice()
                    obj79["numericAsterionDosesApplied"] = 0
                }
            })
        })
        return absent
    }))(global_window)
}
