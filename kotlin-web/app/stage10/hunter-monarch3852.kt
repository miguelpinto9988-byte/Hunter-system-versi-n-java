@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntermonarch3852

import hunter.browser.*

@JsName("Error") private external var global_Error: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("go") private external var global_go: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterMonarch3852() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var G: dynamic = absent
        var L: dynamic = absent
        var A: dynamic = absent
        var M: dynamic = absent
        var categories: dynamic = absent
        var display: dynamic = absent
        var tiers: dynamic = absent
        var xss: dynamic = absent
        var now: dynamic = absent
        var DAY: dynamic = absent
        var CYCLE_DAYS: dynamic = absent
        var getStore: dynamic = absent
        var signedDate: dynamic = absent
        var dateWithin: dynamic = absent
        var screening: dynamic = absent
        var eligible: dynamic = absent
        var rank: dynamic = absent
        var preferred: dynamic = absent
        var favorablePreviousCycle: dynamic = absent
        var loadTotal: dynamic = absent
        var generate: dynamic = absent
        var totalOnDay: dynamic = absent
        var totalFor: dynamic = absent
        var progress: dynamic = absent
        var register: dynamic = absent
        var review: dynamic = absent
        var activate: dynamic = absent
        var cancel: dynamic = absent
        var requiresReview: dynamic = absent
        var meter: dynamic = absent
        var tile: dynamic = absent
        var view: dynamic = absent
        screening = fun(p: dynamic): dynamic {
            val mode: dynamic = A.mode(p)
            val gate: dynamic = onPresent(root.HunterDynamic381) { value7 -> run { val owner9: dynamic = value7; onPresent(owner9.gate) { method8 -> invokeWith(method8, owner9, arrayOf<dynamic>(p)) } } }
            if (truth(jsOr((!truth(mode.auto))) { (onPresent(gate) { value10 -> value10.ok } === false) })) {
                return jsObject { obj11 ->
                    obj11["ok"] = false
                    obj11["reason"] = jsOr(jsOr(mode.reason) { gate.reason }) { "Completa y revisa tu perfil." }
                }
            }
            if (truth(jsOr(jsOr(jsOr(jsAnd(onPresent(p) { value12 -> onPresent(value12.health) { value13 -> value13.pain } }) { (p.health.pain !== "none") }) { onPresent(p) { value14 -> value14.limits } }) { onPresent(p) { value15 -> onPresent(value15.health) { value16 -> value16.acute } } }) { onPresent(p) { value17 -> onPresent(value17.health) { value18 -> value18.redFlags } } })) {
                return jsonValue("{\"ok\":false,\"reason\":\"Revisa las restricciones de tu perfil antes de activar un desafío.\"}")
            }
            return jsonValue("{\"ok\":true,\"reason\":\"\"}")
        }
        eligible = fun(e: dynamic, p: dynamic, logs: dynamic, date: dynamic, cat: dynamic): dynamic {
            val m: dynamic = G.classify(e)
            val tier: dynamic = L.tier(e)
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(m))) { (m.group !== cat) }) { jsLess(tier, 0) }) { jsMore(tier, 2) }) { m.power }) { m.impact }) { e.highSkill46 }) { onPresent(e.doseCap46) { value21 -> value21.highSkill } })) {
                return false
            }
            if (truth((if (truth((cat === "cardio"))) (m.role !== "conditioning") else (m.role !== "primary")))) {
                return false
            }
            if (truth(jsOr(jsOr((M.eligible(e, p) === false)) { (G.eligible(e, p) === false) }) { (A.allowed(e, p) === false) })) {
                return false
            }
            val context: dynamic = L.context(p, logs, date, cat, 5)
            if (truth(jsMore(tier, context.maxTier))) {
                return false
            }
            if (truth(onPresent(context.failure) { value22 -> onPresent(value22.failedFamilies) { value23 -> value23.includes(G.family(e)) } })) {
                return false
            }
            return true
        }
        rank = fun(p: dynamic): dynamic {
            return global_Math.max(0, dynamicValue(jsonValue("[\"basic\",\"intermediate\",\"advanced\"]")).indexOf(onPresent(p) { value26 -> value26.difficulty }))
        }
        preferred = fun(p: dynamic, logs: dynamic, date: dynamic): dynamic {
            val r: dynamic = rank(p)
            if (truth((r === 0))) {
                return jsonValue("[0,0,0,0,0]")
            }
            if (truth((r === 2))) {
                return jsonValue("[1,2,2,1,0]")
            }
            val ctx: dynamic = L.context(p, logs, date, "upper", 5)
            return (if (truth(ctx.preview)) jsonValue("[0,2,0,1,0]") else jsonValue("[0,1,0,1,0]"))
        }
        favorablePreviousCycle = fun(logs: dynamic, cat: dynamic): dynamic {
            val previous: dynamic = getStore().history[0]
            if (truth(jsOr((!truth(previous))) { (previous.status !== "completed") })) {
                return null
            }
            val CP: dynamic = root.HunterCategoryProgress51
            val feedback: dynamic = global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg31: dynamic): dynamic {
                var day: dynamic = absent
                var log: dynamic = absent
                val unpacked32: dynamic = fromIterable(arg31)
                day = unpacked32[0]
                log = unpacked32[1]
                return jsAnd(jsAnd(dateWithin(day, previous.startedAt, previous.expiresAt)) { log }) { (onPresent(CP) { value33 -> run { val owner35: dynamic = value33; onPresent(owner35.categoryOfLog) { method34 -> invokeWith(method34, owner35, arrayOf<dynamic>(log)) } } } === cat) }
            }))
            if (truth((!truth(feedback.length)))) {
                return null
            }
            if (truth(feedback.some((fun(arg36: dynamic): dynamic {
                var log: dynamic = absent
                val unpacked37: dynamic = fromIterable(arg36)
                log = unpacked37[1]
                return jsOr(jsOr(jsOr((log.pain === true)) { (log.fatigue === true) }) { (!truth(log.complete)) }) { (!truth(jsAnd(jsAtLeast(global_Number(log.rpe), 1)) { jsAtMost(global_Number(log.rpe), 4) })) }
            })))) {
                return null
            }
            return previous
        }
        loadTotal = fun(e: dynamic, p: dynamic, logs: dynamic, date: dynamic): dynamic {
            val group: dynamic = G.classify(e).group
            val tier: dynamic = L.tier(e)
            val seconds: dynamic = (e.unit === "sec")
            val difficulty: dynamic = rank(p)
            val progress: dynamic = onPresent(root.HunterCategoryProgress51) { value40 -> run { val owner42: dynamic = value40; onPresent(owner42.resolve) { method41 -> invokeWith(method41, owner42, arrayOf<dynamic>(logs, date, group)) } } }
            if (truth(seconds)) {
                val base: dynamic = dynamicValue(jsonValue("[30,40,50]"))[difficulty]
                return global_Math.max(15, global_Math.min(90, jsAdd(base, (if (truth(jsMore(onPresent(progress) { value43 -> value43.level }, 0))) 5 else 0))))
            }
            val simple: dynamic = jsAnd(jsAnd(jsAtMost(tier, 1)) { (!truth(e.highSkill46)) }) { (!truth(onPresent(e.doseCap46) { value44 -> value44.highSkill })) }
            val initial: dynamic = (if (truth(simple)) dynamicValue(jsonValue("[70,75,80]"))[difficulty] else dynamicValue(jsonValue("[8,10,12]"))[difficulty])
            val cap: dynamic = (if (truth(simple)) 100 else 20)
            val previous: dynamic = favorablePreviousCycle(logs, group)
            val last: dynamic = jsOr(onPresent(previous) { value45 -> value45.objectives.find((fun(o: dynamic): dynamic {
                return (o.id === e.id)
            })) }) { onPresent(previous) { value47 -> value47.objectives.find((fun(o: dynamic): dynamic {
                return jsAnd(jsAnd((o.category === group)) { (o.tier === tier) }) { (o.unit === "reps") }
            })) } }
            val next: dynamic = (if (truth(last)) global_Math.max(initial, jsAdd(global_Math.min(cap, jsOr(global_Number(last.target)) { initial }), 2)) else initial)
            return global_Math.min(cap, next)
        }
        generate = fun(p: dynamic, logs: dynamic, arg51: dynamic): dynamic {
            var date: dynamic = absent
            date = definedOr(arg51) { E.dateKey() }
            val guard: dynamic = screening(p)
            if (truth((!truth(guard.ok)))) {
                return jsObject { obj52 ->
                    obj52["ok"] = false
                    obj52["reason"] = guard.reason
                    obj52["objectives"] = js("[]")
                }
            }
            val choices: dynamic = preferred(p, logs, date)
            val takenIds: dynamic = jsConstruct(global_Set, js("[]"))
            val takenFamilies: dynamic = jsConstruct(global_Set, js("[]"))
            val objectives: dynamic = js("[]")
            if (true) {
                var slot: dynamic = absent
                slot = 0
                var firstIteration53 = true
                while (true) {
                    if (!firstIteration53) {
                        run {
                            val previous54: dynamic = numeric(slot)
                            slot = jsAdd(previous54, 1)
                        }
                    }
                    firstIteration53 = false
                    if (!truth(jsLess(slot, categories.length))) break
                    val cat: dynamic = categories[slot]
                    val desired: dynamic = (if (truth(jsAnd((cat === "cardio")) { jsMore(rank(p), 0) })) 1 else choices[slot])
                    val pool: dynamic = L.monarchCandidates(p, logs, date, cat).filter((fun(e: dynamic): dynamic {
                        return jsAnd(jsAnd(eligible(e, p, logs, date, cat)) { (!truth(takenIds.has(e.id))) }) { (!truth(takenFamilies.has(G.family(e)))) }
                    }))
                    val available: dynamic = pool.filter((fun(e: dynamic): dynamic {
                        return jsAtMost(L.tier(e), desired)
                    }))
                    available.sort((fun(a: dynamic, b: dynamic): dynamic {
                        return jsOr(jsOr(jsOr(jsSubtract(global_Math.abs(jsSubtract(L.tier(a), desired)), global_Math.abs(jsSubtract(L.tier(b), desired)))) { jsSubtract((if (truth((a.unit === "sec"))) 1 else 0), (if (truth((b.unit === "sec"))) 1 else 0)) }) { jsSubtract((if (truth(G.classify(b).primary)) 1 else 0), (if (truth(G.classify(a).primary)) 1 else 0)) }) { a.id.localeCompare(b.id) }
                    }))
                    val exercise: dynamic = available[0]
                    if (truth((!truth(exercise)))) {
                        return jsonValue("{\"ok\":false,\"reason\":\"No hay cinco ejercicios compatibles con el nivel y equipamiento. Ajusta el perfil o consulta alternativas.\",\"objectives\":[]}")
                    }
                    takenIds.add(exercise.id)
                    takenFamilies.add(G.family(exercise))
                    objectives.push(jsObject { obj55 ->
                        obj55["id"] = exercise.id
                        obj55["category"] = cat
                        obj55["tier"] = L.tier(exercise)
                        obj55["unit"] = (if (truth((exercise.unit === "sec"))) "sec" else "reps")
                        obj55["target"] = loadTotal(exercise, p, logs, date)
                    })
                }
            }
            return jsObject { obj56 ->
                obj56["ok"] = true
                obj56["reason"] = ""
                obj56["objectives"] = objectives
            }
        }
        totalOnDay = fun(logs: dynamic, id: dynamic, date: dynamic): dynamic {
            val log: dynamic = onPresent(logs) { value59 -> value59[date] }
            if (truth(jsOr((!truth(log))) { (log.pain === true) })) {
                return 0
            }
            var sum: dynamic = 0
            if (true) {
                val iterator60: dynamic = iteratorOf(jsOr(log.items) { js("[]") })
                while (true) {
                    val next61: dynamic = iterator60.next()
                    if (truth(next61.done)) break
                    var item: dynamic = absent
                    item = next61.value
                    if (truth((item.id !== id))) {
                        continue
                    }
                    if (true) {
                        val iterator62: dynamic = iteratorOf(jsOr(item["actual"]) { js("[]") })
                        while (true) {
                            val next63: dynamic = iterator62.next()
                            if (truth(next63.done)) break
                            var row: dynamic = absent
                            row = next63.value
                            if (truth(jsAnd(jsAnd((row.done === true)) { global_Number.isFinite(global_Number(row["value"])) }) { jsMore(global_Number(row["value"]), 0) })) {
                                run {
                                    sum = jsAdd(sum, jsMultiply(global_Number(row["value"]), (if (truth(E.sidesOf(item))) 2 else 1)))
                                }
                            }
                        }
                    }
                }
            }
            return sum
        }
        totalFor = fun(logs: dynamic, id: dynamic, start: dynamic, end: dynamic): dynamic {
            return global_Object.keys(jsOr(logs) { js("({})") }).filter((fun(date: dynamic): dynamic {
                return dateWithin(date, start, end)
            })).reduce((fun(count: dynamic, date: dynamic): dynamic {
                return jsAdd(count, totalOnDay(logs, id, date))
            }), 0)
        }
        progress = fun(m: dynamic, logs: dynamic): dynamic {
            val start: dynamic = signedDate(m.startedAt)
            val end: dynamic = signedDate(jsSubtract(m.expiresAt, 1))
            return m.objectives.map((fun(o: dynamic): dynamic {
                var `actual`: dynamic = 0
                if (true) {
                    var day: dynamic = absent
                    day = start
                    var firstIteration68 = true
                    while (true) {
                        if (!firstIteration68) {
                            day = E["plus"](day, 1)
                        }
                        firstIteration68 = false
                        if (!truth(jsAtMost(day, end))) break
                        val log: dynamic = onPresent(logs) { value69 -> value69[day] }
                        val closingDay: dynamic = jsAnd((day === end)) { (end !== start) }
                        val finishingTime: dynamic = global_Date.parse(jsOr(onPresent(log) { value70 -> value70.completedAt }) { "" })
                        val inWindow: dynamic = jsOr((!truth(closingDay))) { jsAnd(jsAnd(global_Number.isFinite(finishingTime)) { jsAtLeast(finishingTime, m.startedAt) }) { jsAtMost(finishingTime, m.expiresAt) } }
                        val fromRoutine: dynamic = (if (truth(inWindow)) global_Math.max(0, jsSubtract(totalOnDay(logs, o.id, day), (if (truth((day === start))) jsOr(m.baselines[o.id]) { 0 } else 0))) else 0)
                        val fromMonarch: dynamic = global_Math.max(0, jsOr(global_Number(onPresent(m.manual) { value71 -> onPresent(value71[day]) { value72 -> value72[o.id] } })) { 0 })
                        run {
                            `actual` = jsAdd(`actual`, global_Math.max(fromRoutine, fromMonarch))
                        }
                    }
                }
                return jsObject { obj73 ->
                    copyFields(obj73, o)
                    obj73["done"] = global_Math.min(`actual`, o.target)
                }
            }))
        }
        register = fun(exerciseId: dynamic): dynamic {
            review()
            val st: dynamic = getStore()
            val m: dynamic = st.active
            if (truth(jsOr(jsOr((!truth(m))) { jsAtLeast(now(), m.expiresAt) }) { requiresReview(m) })) {
                global_toast("Esta misión no admite nuevos registros. Revisa el perfil o el plazo.")
                return absent
            }
            val objective: dynamic = m.objectives.find((fun(o: dynamic): dynamic {
                return (o.id === exerciseId)
            }))
            if (truth((!truth(objective)))) {
                return absent
            }
            val `field`: dynamic = global_document.getElementById(jsAdd("monarch-entry-", exerciseId))
            val `value`: dynamic = global_Number(onPresent(`field`) { value76 -> value76["value"] })
            if (truth(jsOr(jsOr((!truth(global_Number.isInteger(`value`)))) { jsLess(`value`, 1) }) { jsMore(`value`, objective.target) })) {
                global_toast("Registra una cantidad válida que hayas realizado.")
                return absent
            }
            val date: dynamic = E.dateKey()
            if (truth((onPresent(global_state.logs) { value77 -> onPresent(value77[date]) { value78 -> value78.pain } } === true))) {
                global_toast("Hay dolor registrado. No añadas más actividad.")
                return absent
            }
            m.manual = jsOr(m.manual) { js("({})") }
            m.manual[date] = jsOr(m.manual[date]) { js("({})") }
            m.manual[date][exerciseId] = global_Math.min(objective.target, jsAdd(jsOr(m.manual[date][exerciseId]) { 0 }, `value`))
            global_save()
            review()
            global_go("monarch")
            return absent
        }
        review = fun(): dynamic {
            val st: dynamic = getStore()
            val m: dynamic = st.active
            if (truth((!truth(m)))) {
                return absent
            }
            val p: dynamic = progress(m, global_state.logs)
            val done: dynamic = p.every((fun(x: dynamic): dynamic {
                return jsAtLeast(x.done, x.target)
            }))
            if (truth(jsOr(done) { jsAtLeast(now(), m.expiresAt) })) {
                val success: dynamic = jsAnd(done) { jsAtMost(now(), m.expiresAt) }
                m.status = (if (truth(success)) "completed" else "expired")
                m["final"] = p
                m.finishedAt = now()
                if (truth(success)) {
                    m.reward = jsAdd(50, m.objectives.reduce((fun(points: dynamic, o: dynamic): dynamic {
                        return jsAdd(jsAdd(points, 10), jsMultiply(10, o.tier))
                    }), 0))
                    run {
                        val target95: dynamic = st
                        target95.xp = jsAdd(target95.xp, m.reward)
                    }
                } else {
                    m.reward = 0
                    val symptoms: dynamic = global_Object.entries(jsOr(global_state.logs) { js("({})") }).some((fun(arg99: dynamic): dynamic {
                        var day: dynamic = absent
                        var log: dynamic = absent
                        val unpacked100: dynamic = fromIterable(arg99)
                        day = unpacked100[0]
                        log = unpacked100[1]
                        return jsAnd(dateWithin(day, m.startedAt, m.expiresAt)) { jsOr((onPresent(log) { value101 -> value101.pain } === true)) { (onPresent(log) { value102 -> value102.fatigue } === true) } }
                    }))
                    if (truth((!truth(symptoms)))) {
                        st.judgementUntil = jsAdd(now(), jsMultiply(7, DAY))
                    }
                }
                st.history.unshift(m)
                st.history = st.history.slice(0, 24)
                st.active = dynamicValue(null)
                global_save()
            }
            return absent
        }
        activate = fun(): dynamic {
            review()
            val st: dynamic = getStore()
            if (truth(st.active)) {
                global_toast("Ya existe una Misión Monarca activa.")
                return absent
            }
            val previous: dynamic = st.history[0]
            if (truth(jsAnd(previous) { jsMore(previous.expiresAt, now()) })) {
                global_toast("El próximo ciclo estará disponible cuando finalicen los cinco días.")
                return absent
            }
            val generated: dynamic = generate(global_state.profile, global_state.logs)
            if (truth((!truth(generated.ok)))) {
                global_toast(generated.reason)
                return absent
            }
            val t: dynamic = now()
            val end: dynamic = jsAdd(t, jsMultiply(CYCLE_DAYS, DAY))
            val baselines: dynamic = global_Object.fromEntries(generated.objectives.map((fun(o: dynamic): dynamic {
                return arrayOf<dynamic>(o.id, totalFor(global_state.logs, o.id, t, end))
            })))
            st.active = jsObject { obj113 ->
                obj113["version"] = 1
                obj113["status"] = "active"
                obj113["startedAt"] = t
                obj113["expiresAt"] = end
                obj113["objectives"] = generated.objectives
                obj113["baselines"] = baselines
                obj113["manual"] = js("({})")
                obj113["profileSnapshot"] = jsObject { obj114 ->
                    obj114["difficulty"] = global_state.profile.difficulty
                    obj114["equipment"] = global_state.profile.equipment
                }
                obj113["reward"] = 0
            }
            if (truth(global_save())) {
                global_toast("Misión Monarca activada: metas acumulativas de cinco días.")
                global_go("monarch")
            }
            return absent
        }
        cancel = fun(): dynamic {
            val st: dynamic = getStore()
            if (truth((!truth(st.active)))) {
                return absent
            }
            st.active.status = "cancelled"
            st.active.finishedAt = now()
            st.active["final"] = progress(st.active, global_state.logs)
            st.history.unshift(st.active)
            st.history = st.history.slice(0, 24)
            st.active = dynamicValue(null)
            global_save()
            global_go("monarch")
            global_toast("Misión cancelada. Sin aumento obligatorio de carga.")
            return absent
        }
        requiresReview = fun(m: dynamic): dynamic {
            val p: dynamic = global_state.profile
            if (truth((!truth(screening(p).ok)))) {
                return true
            }
            return m.objectives.some((fun(o: dynamic): dynamic {
                return jsOr((!truth(eligible(E.byId(o.id), p, global_state.logs, E.dateKey(), o.category)))) { jsMore(L.tier(E.byId(o.id)), rank(p)) }
            }))
        }
        tile = fun(): dynamic {
            val st: dynamic = getStore()
            val m: dynamic = st.active
            val txt: dynamic = (if (truth(m)) ("Misión activa · termina " + textOf(jsConstruct(global_Date, arrayOf<dynamic>(m.expiresAt)).toLocaleDateString("es")) + "") else "Cinco objetivos adaptados al perfil · ciclo de cinco días")
            return ("<div class=\"section-title\"><h2>👑 Misiones Monarca</h2><span class=\"badge\">5 días</span></div><div class=\"card\"><h3>Prueba del Monarca</h3><p class=\"muted\" style=\"margin:8px 0 14px\">" + textOf(xss(txt)) + "</p><button class=\"btn primary\" onclick=\"go('monarch')\">" + textOf((if (truth(m)) "VER PROGRESO MONARCA" else "ABRIR MISIONES MONARCA")) + " →</button></div>")
        }
        view = fun(): dynamic {
            review()
            val st: dynamic = getStore()
            val m: dynamic = st.active
            val guard: dynamic = screening(global_state.profile)
            val historical: dynamic = st.history.slice(0, 5).map((fun(x: dynamic): dynamic {
                return ("<div class=\"quest\"><div class=\"grow\"><h3>" + textOf(xss((if (truth((x.status === "completed"))) "Completada" else (if (truth((x.status === "cancelled"))) "Cancelada" else "Vencida")))) + "</h3><small>" + textOf(jsConstruct(global_Date, arrayOf<dynamic>(x.startedAt)).toLocaleDateString("es")) + " · " + textOf(x.objectives.length) + " ejercicios</small></div><span class=\"tag\">" + textOf(jsOr(global_Number(x.reward)) { 0 }) + " XP</span></div>")
            })).join("")
            val judgement: dynamic = (if (truth(jsMore(st.judgementUntil, now()))) ("<div class=\"notice\">Juicio del Monarca hasta " + textOf(jsConstruct(global_Date, arrayOf<dynamic>(st.judgementUntil)).toLocaleDateString("es")) + ": estado narrativo, sin cambios en las cargas de las rutinas.</div>") else "")
            var main: dynamic = ""
            if (truth(m)) {
                val rows: dynamic = progress(m, global_state.logs)
                val caution: dynamic = (if (truth(requiresReview(m))) "<div class=\"notice warn\">El perfil cambió o requiere revisión. No continúes esta misión sin actualizar el plan; puedes cancelarla sin penalización física.</div>" else "")
                main = ("<div class=\"card\"><div class=\"row between\"><h3>Ciclo en curso</h3><span class=\"badge\">" + textOf(global_Math.max(0, global_Math.ceil(jsDivide(jsSubtract(m.expiresAt, now()), DAY)))) + " días restantes</span></div><p class=\"muted\">Los objetivos se acumulan durante cinco días, a partir de las series registradas en tus misiones normales. No necesitas repetir una serie para contabilizarla dos veces.</p>" + textOf(caution) + "" + textOf(rows.map((fun(o: dynamic): dynamic {
                    val e: dynamic = E.byId(o.id)
                    return ("<div class=\"quest\"><div class=\"grow\"><h3>" + textOf(xss(jsOr(onPresent(e) { value134 -> value134.name }) { o.id })) + "</h3><small>" + textOf(xss(display[o.category])) + " · " + textOf(tiers[o.tier]) + " · " + textOf(o.done) + "/" + textOf(o.target) + " " + textOf((if (truth((o.unit === "sec"))) "segundos" else "repeticiones")) + "</small>" + textOf(meter(o.done, o.target)) + "<label class=\"label\" for=\"monarch-entry-" + textOf(xss(o.id)) + "\">Registrar avance realizado</label><div class=\"row\" style=\"gap:8px\"><input style=\"max-width:92px\" id=\"monarch-entry-" + textOf(xss(o.id)) + "\" type=\"number\" inputmode=\"numeric\" min=\"1\" max=\"" + textOf(o.target) + "\" value=\"1\"><button class=\"btn ghost\" onclick=\"HunterMonarch3852.register('" + textOf(xss(o.id)) + "')\">Guardar</button></div></div></div>")
                })).join("")) + "<button class=\"btn ghost\" onclick=\"HunterMonarch3852.cancel()\">Cancelar misión</button></div>")
            } else {
                val previous: dynamic = st.history[0]
                val cooldown: dynamic = jsAnd(previous) { jsMore(previous.expiresAt, now()) }
                val selected: dynamic = (if (truth(cooldown)) jsObject { obj135 ->
                    obj135["ok"] = false
                    obj135["reason"] = jsAdd(jsAdd("El próximo ciclo estará disponible el ", jsConstruct(global_Date, arrayOf<dynamic>(previous.expiresAt)).toLocaleString("es")), ".")
                } else generate(global_state.profile, global_state.logs))
                main = (if (truth(selected.ok)) ("<div class=\"card\"><h3>Próxima misión · vista previa</h3><p class=\"muted\">Se crearán cinco objetivos acumulativos que respetan la dificultad, el equipo y los registros de tu perfil.</p>" + textOf(selected.objectives.map((fun(o: dynamic): dynamic {
                    return ("<div class=\"quest\"><div class=\"grow\"><h3>" + textOf(xss(jsOr(onPresent(E.byId(o.id)) { value137 -> value137.name }) { o.id })) + "</h3><small>" + textOf(xss(display[o.category])) + " · " + textOf(tiers[o.tier]) + " · " + textOf(o.target) + " " + textOf((if (truth((o.unit === "sec"))) "segundos" else "repeticiones")) + " en cinco días</small></div></div>")
                })).join("")) + "<p class=\"footnote\">Activación voluntaria. Revisa la técnica, la recuperación y detén cualquier actividad que provoque dolor. No hay penalizaciones físicas por cancelar.</p><button class=\"btn primary\" onclick=\"HunterMonarch3852.activate()\">AUTORIZAR Y ACTIVAR MISIÓN</button></div>") else ("<div class=\"notice warn\">" + textOf(xss(jsOr(selected.reason) { guard.reason })) + "</div>"))
            }
            return ("<section class=\"intro\"><span class=\"eyebrow\">HUNTER SYSTEM · DESAFÍOS ESPECIALES</span><h1>👑 Misiones Monarca</h1><p class=\"muted\">Cinco ejercicios personalizados. Cada progreso cuenta en un ciclo de cinco días.</p></section><div class=\"metrics\"><div class=\"metric\"><strong>" + textOf(st.xp) + "</strong><small>XP Monarca</small></div><div class=\"metric\"><strong>" + textOf(st.history.filter((fun(x: dynamic): dynamic {
                return (x.status === "completed")
            })).length) + "</strong><small>Misiones completadas</small></div></div>" + textOf(judgement) + "" + textOf(main) + "<div class=\"section-title\"><h2>Historial Monarca</h2></div><div class=\"card\">" + textOf(jsOr(historical) { "<p class=\"muted\">Aún no has realizado ninguna misión Monarca.</p>" }) + "</div>")
        }
        if (truth(root.HunterMonarch3852)) {
            return absent
        }
        E = root.Engine
        G = root.HunterGroups49
        L = root.HunterLevel3851
        A = root.Adapt
        M = root.HunterMaterials3814
        if (truth(jsOr(jsOr(jsOr(jsOr((!truth(E))) { (!truth(G)) }) { (!truth(L)) }) { (!truth(A)) }) { (!truth(M)) })) {
            throw global_Error("Monarch3852: dependencias ausentes")
        }
        categories = jsonValue("[\"upper\",\"upper\",\"lower\",\"core\",\"cardio\"]")
        display = jsonValue("{\"upper\":\"Tren superior\",\"lower\":\"Tren inferior\",\"core\":\"Zona central\",\"cardio\":\"Cardio\"}")
        tiers = jsonValue("[\"Básico\",\"Intermedio\",\"Avanzado\"]")
        xss = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        now = (fun(): dynamic {
            return global_Date.now()
        })
        DAY = 86400000
        CYCLE_DAYS = 5
        getStore = (fun(): dynamic {
            if (truth(jsOr((!truth(global_state.monarch3852))) { (!truth(global_Array.isArray(global_state.monarch3852.history))) })) {
                global_state.monarch3852 = jsonValue("{\"active\":null,\"history\":[],\"xp\":0,\"judgementUntil\":0}")
            }
            return global_state.monarch3852
        })
        signedDate = (fun(t: dynamic): dynamic {
            return E.dateKey(jsConstruct(global_Date, arrayOf<dynamic>(t)))
        })
        dateWithin = (fun(date: dynamic, start: dynamic, end: dynamic): dynamic {
            return jsAnd(jsAtLeast(date, signedDate(start))) { jsAtMost(date, signedDate(jsSubtract(end, 1))) }
        })
        meter = (fun(v: dynamic, max: dynamic): dynamic {
            return ("<div class=\"bar\" style=\"margin:7px 0 12px\"><i style=\"width:" + textOf(global_Math.max(0, global_Math.min(100, jsDivide(jsMultiply(100, v), max)))) + "%\"></i></div>")
        })
        root.HunterMonarch3852 = global_Object.freeze(jsObject { obj140 ->
            obj140["version"] = "38.54"
            obj140["generate"] = generate
            obj140["eligible"] = eligible
            obj140["screening"] = screening
            obj140["progress"] = progress
            obj140["activate"] = activate
            obj140["cancel"] = cancel
            obj140["register"] = register
            obj140["view"] = view
            obj140["tile"] = tile
            obj140["review"] = review
            obj140["requiresReview"] = requiresReview
        })
        return absent
    }))(global_window)
}
