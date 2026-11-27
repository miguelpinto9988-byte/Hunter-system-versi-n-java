@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterv379

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("getLog") private external var global_getLog: dynamic
@JsName("todayDay") private external var global_todayDay: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("routineList") private external var global_routineList: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("routineDetail") private external var global_routineDetail: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("prepareRoutine") private external var global_prepareRoutine: dynamic
@JsName("routineNotice372") private external var global_routineNotice372: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("console") private external var global_console: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterV379() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var A: dynamic = absent
        var S: dynamic = absent
        var W: dynamic = absent
        var VER: dynamic = absent
        var TYPES: dynamic = absent
        var LABELS: dynamic = absent
        var NAMES: dynamic = absent
        var ZONES: dynamic = absent
        var GROUPS: dynamic = absent
        var active: dynamic = absent
        var impactStatus: dynamic = absent
        var hasEquipment: dynamic = absent
        var approved: dynamic = absent
        var list: dynamic = absent
        var rotation: dynamic = absent
        var mildDose: dynamic = absent
        var choose: dynamic = absent
        var keyFor: dynamic = absent
        var locked: dynamic = absent
        var patchCycle: dynamic = absent
        var repairEmptyLog: dynamic = absent
        var templates: dynamic = absent
        active = fun(p: dynamic): dynamic {
            return A.active(jsOr(p) { js("({})") })
        }
        impactStatus = fun(p: dynamic): dynamic {
            val a: dynamic = active(p)
            val h: dynamic = jsOr(onPresent(p) { value10 -> value10.health }) { js("({})") }
            val zones: dynamic = a.map((fun(i: dynamic): dynamic {
                return i.zone
            }))
            val known: dynamic = jsAnd(zones.length) { zones.every((fun(z: dynamic): dynamic {
                return ZONES.has(z)
            })) }
            val serious: dynamic = jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth((!truth(h.acute))))) { (h.pain === "strong") }) { a.some((fun(i: dynamic): dynamic {
                return jsOr((i.type === "bone")) { (i.type === "surgery") }
            })) }) { (!truth((!truth(h.emergency)))) }) { (!truth((!truth(h.neurological)))) }) { (!truth((!truth(h.unstable)))) }) { (!truth((!truth(h.redFlags)))) }
            if (truth(jsOr((!truth(p))) { jsLess(p.age, 18) })) {
                return jsonValue("{\"ok\":false,\"reason\":\"Se requiere una planificación individual apropiada para la edad.\"}")
            }
            if (truth(serious)) {
                return jsonValue("{\"ok\":false,\"reason\":\"Se registró dolor intenso, una lesión reciente grave o una condición que requiere valoración. No es seguro asignar automáticamente ejercicios, incluso para otras zonas.\"}")
            }
            if (truth(jsAnd(a.length) { (!truth(known)) })) {
                return jsonValue("{\"ok\":false,\"reason\":\"Indica qué zona está afectada para excluirla de todos los movimientos. Una zona desconocida no puede filtrarse de forma fiable.\"}")
            }
            if (truth(jsAnd(h.special) { (!truth(h.approved)) })) {
                return jsonValue("{\"ok\":false,\"reason\":\"Existe una condición especial que necesita indicaciones individuales antes de programar ejercicio.\"}")
            }
            if (truth(jsAnd(p.limits) { (!truth(a.length)) })) {
                return jsonValue("{\"ok\":false,\"reason\":\"Marca la zona afectada en Perfil para encontrar grupos musculares alternativos.\"}")
            }
            if (truth(jsAnd(jsOr((h.pain === "moderate")) { (h.pain === "mild") }) { (!truth(a.length)) })) {
                return jsonValue("{\"ok\":false,\"reason\":\"Selecciona la ubicación del dolor para evitar ejercicios que involucren esa zona.\"}")
            }
            return jsObject { obj11 ->
                obj11["ok"] = true
                obj11["reason"] = (if (truth(a.length)) "Alternativas de baja exigencia para grupos alejados de las zonas declaradas. El dolor puede limitar también movimientos de otras zonas." else "Planificación general.")
                obj11["affected"] = zones
                obj11["reduced"] = jsOr(jsOr(jsMore(a.length, 0)) { (h.pain === "mild") }) { (h.pain === "moderate") }
            }
        }
        hasEquipment = fun(e: dynamic, p: dynamic): dynamic {
            if (truth(jsAnd((e.equipment === "db")) { (!truth(dynamicValue(jsonValue("[\"db\",\"gym\"]")).includes(p.equipment))) })) {
                return false
            }
            if (truth(jsAnd(e.gymOnly23) { (p.equipment !== "gym") })) {
                return false
            }
            if (true) {
                val iterator14: dynamic = iteratorOf(jsOr(e.requirements) { js("[]") })
                while (true) {
                    val next15: dynamic = iterator14.next()
                    if (truth(next15.done)) break
                    var x: dynamic = absent
                    x = next15.value
                    if (truth(jsAnd((x === "db")) { (!truth(dynamicValue(jsonValue("[\"db\",\"gym\"]")).includes(p.equipment))) })) {
                        return false
                    }
                    if (truth(jsAnd((x !== "db")) { (!truth(p[x])) })) {
                        return false
                    }
                }
            }
            return true
        }
        approved = fun(e: dynamic, p: dynamic): dynamic {
            if (truth(onPresent(root.HunterCatalog46) { value18 -> value18.reasons(e, p).length })) {
                return false
            }
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(e))) { (!truth(e.id)) }) { (!truth(e.name)) }) { (!truth(global_Array.isArray(e.zones))) }) { (!truth(e.category)) }) { e.sourceOnly }) { (e.variant !== "base") })) {
                return false
            }
            if (truth(jsOr(jsOr((!truth(hasEquipment(e, p)))) { jsMore(e.level, 1) }) { (e.impact === "Con saltos") })) {
                return false
            }
            val affected: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(active(p).map((fun(i: dynamic): dynamic {
                return i.zone
            }))))
            val r: dynamic = jsOr(p.restrictions) { js("({})") }
            val txt: dynamic = dynamicValue(jsAdd(jsAdd(jsAdd(jsAdd(e.id, " "), e.name), " "), jsOr(e.steps) { "" })).toLowerCase()
            if (truth(e.zones.some((fun(z: dynamic): dynamic {
                return affected.has(z)
            })))) {
                return false
            }
            if (truth(jsAnd(affected.has("knee")) { jsOr(jsOr(e.kneeBend) { jsAnd((!truth(e.seated))) { e.floor } }) { dynamicValue(jsRegExp("rodillas apoyadas|cuatro apoyos|cuadrupedia|arrodillad", "i")).test(txt) } })) {
                return false
            }
            if (truth(jsAnd(affected.has("ankle")) { jsOr(dynamicValue(jsRegExp("puente de gl[uú]teos|elevaci[oó]n de talones|heel raise|calf raise", "i")).test(txt)) { jsAnd((!truth(e.seated))) { (!truth(e.floor)) } } })) {
                return false
            }
            if (truth(jsAnd(jsAnd(affected.has("hip")) { (!truth(e.seated)) }) { dynamicValue(jsRegExp("sentadilla|zancada|estocada|peso muerto|bisagra", "i")).test(txt) })) {
                return false
            }
            if (truth(jsAnd(affected.has("wrist")) { jsOr(e.wristLoad) { dynamicValue(jsRegExp("boxeo|golpes|puños|punch", "i")).test(txt) } })) {
                return false
            }
            if (truth(jsAnd(affected.has("shoulder")) { jsOr(jsOr(e.overhead) { e.wristLoad }) { dynamicValue(jsRegExp("plancha|flexiones|push.?up|brazos elevados", "i")).test(txt) } })) {
                return false
            }
            if (truth(jsAnd(affected.has("neck")) { jsOr(e.overhead) { e.spineMove } })) {
                return false
            }
            if (truth(jsAnd(affected.has("back")) { jsOr(e.spineMove) { jsAnd(e.floor) { dynamicValue(jsonValue("[\"Core\",\"Abdomen\"]")).includes(e.group) } } })) {
                return false
            }
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsAnd(r.noImpact) { (e.impact === "Con saltos") }) { jsAnd(r.noFloor) { e.floor } }) { jsAnd(r.noOverhead) { e.overhead } }) { jsAnd(r.noKnee) { e.kneeBend } }) { jsAnd(r.noWrist) { e.wristLoad } }) { jsAnd(r.noLoad) { (e.equipment === "db") } }) { jsAnd(r.seatedOnly) { (!truth(e.seated)) } }) { jsAnd(r.noSpine) { e.spineMove } })) {
                return false
            }
            return true
        }
        list = fun(p: dynamic, kind: dynamic): dynamic {
            val category: dynamic = NAMES[kind]
            return E.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd((e.category === category)) { approved(e, p) }
            }))
        }
        rotation = fun(p: dynamic, items: dynamic, n: dynamic, logs: dynamic, day: dynamic): dynamic {
            val injury: dynamic = onPresent(active(p)[0]) { value38 -> value38.zone }
            val priority: dynamic = jsOr(GROUPS.preferred[injury]) { dynamicValue(js("[]")).concat(GROUPS.upper, GROUPS.lower, GROUPS.other) }
            val hist: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg41: dynamic): dynamic {
                var date: dynamic = absent
                var l: dynamic = absent
                val unpacked42: dynamic = fromIterable(arg41)
                date = unpacked42[0]
                l = unpacked42[1]
                return jsAnd(jsLess(date, day)) { l.complete }
            })).flatMap((fun(arg43: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked44: dynamic = fromIterable(arg43)
                l = unpacked44[1]
                return dynamicValue(jsOr(l.items) { js("[]") }).map((fun(x: dynamic): dynamic {
                    return jsOr(onPresent(E.byId(x.id)) { value45 -> value45.baseId }) { x.id }
                }))
            }))))
            val unique: dynamic = jsConstruct(global_Map, js("[]"))
            if (true) {
                val iterator46: dynamic = iteratorOf(items)
                while (true) {
                    val next47: dynamic = iterator46.next()
                    if (truth(next47.done)) break
                    var e: dynamic = absent
                    e = next47.value
                    if (truth((!truth(unique.has(jsOr(e.baseId) { e.id }))))) {
                        unique["set"](jsOr(e.baseId) { e.id }, e)
                    }
                }
            }
            val options: dynamic = jsArray { array48 ->
                appendAll(array48, unique.values())
            }
            val pivot: dynamic = (if (truth(options.length)) jsMod(global_Math.abs(n), options.length) else 0)
            return options.sort((fun(a: dynamic, b: dynamic): dynamic {
                val rank: dynamic = (fun(e: dynamic): dynamic {
                    var score: dynamic = 0
                    val ix: dynamic = priority.indexOf(e.group)
                    if (truth(jsAtLeast(ix, 0))) {
                        run {
                            score = jsAdd(score, jsSubtract(120, jsMultiply(ix, 13)))
                        }
                    }
                    if (truth((e.level === 0))) {
                        run {
                            score = jsAdd(score, 15)
                        }
                    }
                    if (truth((e.equipment === "body"))) {
                        run {
                            score = jsAdd(score, 2)
                        }
                    }
                    if (truth(hist.has(jsOr(e.baseId) { e.id }))) {
                        run {
                            score = jsSubtract(score, 15)
                        }
                    }
                    return score
                })
                return jsOr(jsOr(jsSubtract(rank(b), rank(a))) { jsSubtract(jsMod(jsAdd(a.id.charCodeAt(0), pivot), 17), jsMod(jsAdd(b.id.charCodeAt(0), pivot), 17)) }) { a.id.localeCompare(b.id) }
            }))
        }
        mildDose = fun(e: dynamic, logs: dynamic, date: dynamic): dynamic {
            val recent: dynamic = global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg51: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked52: dynamic = fromIterable(arg51)
                d = unpacked52[0]
                l = unpacked52[1]
                return jsAnd(jsAnd(jsLess(d, date)) { jsAtLeast(d, E["plus"](date, (-numeric(7)))) }) { l }
            }))
            val tiring: dynamic = recent.some((fun(arg53: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked54: dynamic = fromIterable(arg53)
                l = unpacked54[1]
                return jsOr(jsOr(l.pain) { l.fatigue }) { jsAtLeast(l.rpe, 7) }
            }))
            return jsObject { obj55 ->
                obj55["id"] = e.id
                obj55["sets"] = 1
                obj55["target"] = (if (truth((e.unit === "sec"))) (if (truth(tiring)) 10 else 15) else (if (truth(tiring)) 4 else 6))
                obj55["rest"] = 90
                obj55["adaptation379"] = true
                obj55["noAutoLoad"] = true
                obj55["safetyNote"] = "Ejercicio orientativo de baja exigencia; detente si produce síntomas o aumenta las molestias."
            }
        }
        choose = fun(p: dynamic, kind: dynamic, arg58: dynamic, arg59: dynamic, arg60: dynamic): dynamic {
            var n: dynamic = absent
            n = definedOr(arg58) { 0 }
            var logs: dynamic = absent
            logs = definedOr(arg59) { js("({})") }
            var date: dynamic = absent
            date = definedOr(arg60) { E.dateKey() }
            val status: dynamic = impactStatus(p)
            if (truth((!truth(status.ok)))) {
                return jsObject { obj61 ->
                    obj61["items"] = js("[]")
                    obj61["blocked"] = status.reason
                    obj61["requested"] = kind
                }
            }
            if (truth((kind === "rest"))) {
                return jsonValue("{\"items\":[],\"effective\":\"rest\",\"requested\":\"rest\"}")
            }
            val primary: dynamic = list(p, kind)
            var effective: dynamic = kind
            var candidates: dynamic = primary
            if (truth((!truth(candidates.length)))) {
                val fallbacks: dynamic = (if (truth((kind === "cardio"))) jsonValue("[\"strength\",\"recovery\"]") else (if (truth((kind === "strength"))) jsonValue("[\"recovery\",\"cardio\"]") else jsonValue("[\"strength\",\"cardio\"]")))
                if (true) {
                    val iterator62: dynamic = iteratorOf(fallbacks)
                    while (true) {
                        val next63: dynamic = iterator62.next()
                        if (truth(next63.done)) break
                        var other: dynamic = absent
                        other = next63.value
                        val pool: dynamic = list(p, other)
                        if (truth(pool.length)) {
                            effective = other
                            candidates = pool
                            break
                        }
                    }
                }
            }
            if (truth((!truth(candidates.length)))) {
                return jsObject { obj64 ->
                    obj64["items"] = js("[]")
                    obj64["blocked"] = "No hay ejercicios de otros grupos que superen los filtros de zona, postura y equipo. Es necesario ajustar las restricciones o el catálogo, no convertir el día en descanso."
                    obj64["requested"] = kind
                }
            }
            val arranged: dynamic = rotation(p, candidates, n, logs, date)
            val duration: dynamic = global_Math.max(10, global_Math.min(45, jsOr(global_Number(p.minutes)) { 20 }))
            val amount: dynamic = global_Math.min((if (truth(jsAtMost(duration, 10))) 2 else (if (truth(jsAtMost(duration, 20))) 3 else 4)), arranged.length)
            val selected: dynamic = js("[]")
            val seen: dynamic = jsConstruct(global_Set, js("[]"))
            if (true) {
                val iterator65: dynamic = iteratorOf(arranged)
                while (true) {
                    val next66: dynamic = iterator65.next()
                    if (truth(next66.done)) break
                    var e: dynamic = absent
                    e = next66.value
                    if (truth((!truth(seen.has(e.group))))) {
                        selected.push(e)
                        seen.add(e.group)
                    }
                    if (truth(jsAtLeast(selected.length, amount))) {
                        break
                    }
                }
            }
            if (true) {
                val iterator67: dynamic = iteratorOf(arranged)
                while (true) {
                    val next68: dynamic = iterator67.next()
                    if (truth(next68.done)) break
                    var e: dynamic = absent
                    e = next68.value
                    if (truth(jsAtLeast(selected.length, amount))) {
                        break
                    }
                    if (truth((!truth(selected.includes(e))))) {
                        selected.push(e)
                    }
                }
            }
            return jsObject { obj69 ->
                obj69["items"] = selected.map((fun(e: dynamic): dynamic {
                    return jsOr(onPresent(root.HunterCatalog46) { value70 -> value70.cap(e, p, mildDose(e, logs, date), jsObject { obj71 ->
                        obj71["status"] = jsonValue("{\"reduced\":true}")
                        obj71["logs"] = logs
                        obj71["date"] = date
                    }) }) { mildDose(e, logs, date) }
                }))
                obj69["requested"] = kind
                obj69["effective"] = effective
                obj69["substituted"] = (effective !== kind)
                obj69["status"] = status
            }
        }
        keyFor = fun(p: dynamic, logs: dynamic, day: dynamic): dynamic {
            val s: dynamic = global_JSON.stringify(jsObject { obj74 ->
                obj74["injuries"] = p.injuries
                obj74["health"] = p.health
                obj74["restrictions"] = p.restrictions
                obj74["weekPlan"] = p.weekPlan
                obj74["minutes"] = p.minutes
                obj74["equipment"] = p.equipment
                obj74["experience"] = p.experience
                obj74["trainingMode"] = p.trainingMode
                obj74["goal"] = p.goal
                obj74["difficulty"] = p.difficulty
                obj74["focus"] = p.focus
            })
            val past: dynamic = global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg77: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked78: dynamic = fromIterable(arg77)
                d = unpacked78[0]
                l = unpacked78[1]
                return jsAnd(jsAnd(jsAtMost(d, day)) { jsAtLeast(d, E["plus"](day, (-numeric(7)))) }) { l }
            })).map((fun(arg79: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked80: dynamic = fromIterable(arg79)
                d = unpacked80[0]
                l = unpacked80[1]
                return arrayOf<dynamic>(d, l.pain, l.fatigue, l.rpe, l.complete)
            }))
            var hash: dynamic = 2166136261.0
            if (true) {
                val iterator81: dynamic = iteratorOf(jsAdd(s, global_JSON.stringify(past)))
                while (true) {
                    val next82: dynamic = iterator81.next()
                    if (truth(next82.done)) break
                    var c: dynamic = absent
                    c = next82.value
                    run {
                        hash = jsBitXor(hash, c.charCodeAt(0))
                    }
                    hash = global_Math.imul(hash, 16777619)
                }
            }
            return dynamicValue(jsUnsignedShift(hash, 0))["toString"](36)
        }
        locked = fun(d: dynamic, logs: dynamic): dynamic {
            val log: dynamic = onPresent(logs) { value85 -> value85[d.date] }
            return (!truth((!truth(jsOr(jsOr(jsOr(jsOr(d.customized) { d.routineId }) { d.creatorAssigned }) { W.active(log) }) { onPresent(d.tasks) { value86 -> value86.some((fun(t: dynamic): dynamic {
                return t.done
            })) } }))))
        }
        patchCycle = fun(c: dynamic, p: dynamic, arg89: dynamic, arg90: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg89) { js("({})") }
            var today: dynamic = absent
            today = definedOr(arg90) { E.dateKey() }
            if (truth(jsOr(jsOr(jsOr((!truth(onPresent(c) { value91 -> value91.days }))) { (!truth(p)) }) { (!truth(S.valid(p))) }) { jsAnd(jsAnd(jsAnd((!truth(active(p).length))) { (!truth(p.limits)) }) { (!truth(onPresent(p.health) { value92 -> value92.special })) }) { (!truth(dynamicValue(jsonValue("[\"mild\",\"moderate\",\"strong\"]")).includes(onPresent(p.health) { value93 -> value93.pain }))) } })) {
                return 0
            }
            var edits: dynamic = 0
            if (true) {
                val iterator94: dynamic = iteratorOf(c.days.entries())
                while (true) {
                    val next95: dynamic = iterator94.next()
                    if (truth(next95.done)) break
                    var index: dynamic = absent
                    var d: dynamic = absent
                    val unpacked96: dynamic = fromIterable(next95.value)
                    index = unpacked96[0]
                    d = unpacked96[1]
                    if (truth(jsOr(jsOr(jsLess(d.date, today)) { locked(d, logs) }) { onPresent(root.HunterStable3828) { value97 -> run { val owner99: dynamic = value97; onPresent(owner99.keep) { method98 -> invokeWith(method98, owner99, arrayOf<dynamic>(d, p, logs, today)) } } } })) {
                        continue
                    }
                    val choice: dynamic = p.weekPlan[S.weekday(d.date)]
                    if (truth((!truth(choice)))) {
                        continue
                    }
                    if (truth((choice === "rest"))) {
                        if (truth(jsOr(jsOr((d.kind !== "rest")) { onPresent(d.items) { value100 -> value100.length } }) { d.blocked378 })) {
                            d.kind = "rest"
                            d.items = js("[]")
                            d.title = "Descanso elegido"
                            d.scheduleChoice = "rest"
                            d.tasks = js("[]")
                            removeKey(d, "blocked378")
                            removeKey(d, "adapted378")
                            run {
                                val previous111: dynamic = numeric(edits)
                                edits = jsAdd(previous111, 1)
                            }
                        }
                        continue
                    }
                    val key: dynamic = keyFor(p, logs, d.date)
                    val needs: dynamic = jsOr(jsOr(jsOr((d.planningKey379 !== key)) { (!truth(onPresent(d.items) { value112 -> value112.length })) }) { (!truth((!truth(d.blocked378)))) }) { (!truth(d.adaptation379)) }
                    if (truth((!truth(needs)))) {
                        continue
                    }
                    val target: dynamic = TYPES[choice]
                    val proposal: dynamic = choose(p, target, jsAdd(jsMultiply(c.index, 7), index), logs, d.date)
                    d.kind = target
                    d.scheduleChoice = choice
                    d.originalCategory378 = LABELS[choice]
                    d.title = (if (truth(proposal.blocked)) jsAdd(LABELS[choice], " · requiere revisión") else jsAdd(LABELS[choice], (if (truth(proposal.substituted)) jsAdd(" · alternativa de ", NAMES[proposal.effective]) else " · otros grupos musculares")))
                    d.items = proposal.items
                    d.tasks = js("[]")
                    d.adapted378 = true
                    d.adaptation379 = VER
                    d.planningKey379 = key
                    if (truth(proposal.blocked)) {
                        d.blocked378 = proposal.blocked
                        d.planningNote = "Sesión programada, sin selección automática por las restricciones indicadas."
                    } else {
                        removeKey(d, "blocked378")
                        d.planningNote = jsAdd((if (truth(proposal.substituted)) jsAdd(jsAdd("No se encontraron alternativas del mismo tipo; se ofrecen ejercicios de ", NAMES[proposal.effective].toLowerCase()), ". ") else ""), "Selección de baja intensidad, priorizando grupos fuera de las zonas afectadas. No indica rehabilitación ni comprueba individualmente la seguridad clínica.")
                    }
                    run {
                        val previous137: dynamic = numeric(edits)
                        edits = jsAdd(previous137, 1)
                    }
                }
            }
            if (truth(edits)) {
                c.injuryPlannerVersion379 = VER
            }
            return edits
        }
        repairEmptyLog = fun(d: dynamic): dynamic {
            if (truth(jsOr((!truth(d))) { (!truth(onPresent(d.items) { value142 -> value142.length })) })) {
                return false
            }
            val l: dynamic = onPresent(global_state) { value143 -> onPresent(value143.logs) { value144 -> value144[d.date] } }
            if (truth(jsOr(jsOr(jsOr(jsOr((!truth(l))) { W.active(l) }) { l.complete }) { l.pain }) { l.manual })) {
                return false
            }
            if (truth(jsAnd(onPresent(l.items) { value145 -> value145.length }) { l.items.every((fun(x: dynamic): dynamic {
                return d.items.some((fun(y: dynamic): dynamic {
                    return jsAnd(jsAnd((y.id === x.id)) { (y.sets === x.sets) }) { (y.target === x.target) }
                }))
            })) })) {
                return false
            }
            l.items = d.items.map((fun(x: dynamic): dynamic {
                return jsObject { obj148 ->
                    copyFields(obj148, x)
                    obj148["actual"] = global_Array.from(jsObject { obj149 ->
                        obj149["length"] = x.sets
                    }, (fun(): dynamic {
                        return jsObject { obj150 ->
                            obj150["done"] = false
                            obj150["value"] = x.target
                            obj150["kg"] = 0
                        }
                    }))
                }
            }))
            l.kind = d.kind
            return true
        }
        templates = fun(p: dynamic, arg166: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg166) { js("({})") }
            if (truth(jsOr((!truth(p))) { jsAnd((!truth(active(p).length))) { (!truth(p.limits)) } })) {
                return js("[]")
            }
            val status: dynamic = impactStatus(p)
            if (truth((!truth(status.ok)))) {
                return js("[]")
            }
            val generated: dynamic = js("[]")
            val ids: dynamic = jsConstruct(global_Set, js("[]"))
            if (true) {
                val iterator167: dynamic = iteratorOf(jsonValue("[[\"strength\",\"Fuerza\"],[\"cardio\",\"Cardio\"],[\"recovery\",\"Movilidad\"]]"))
                while (true) {
                    val next168: dynamic = iterator167.next()
                    if (truth(next168.done)) break
                    var kind: dynamic = absent
                    var category: dynamic = absent
                    val unpacked169: dynamic = fromIterable(next168.value)
                    kind = unpacked169[0]
                    category = unpacked169[1]
                    if (true) {
                        var n: dynamic = absent
                        n = 0
                        var firstIteration170 = true
                        while (true) {
                            if (!firstIteration170) {
                                run {
                                    val previous171: dynamic = numeric(n)
                                    n = jsAdd(previous171, 1)
                                }
                            }
                            firstIteration170 = false
                            if (!truth(jsLess(n, 4))) break
                            val c: dynamic = choose(p, kind, jsMultiply(n, 5), logs)
                            val items: dynamic = c.items
                            if (truth((!truth(onPresent(items) { value172 -> value172.length })))) {
                                continue
                            }
                            val sig: dynamic = items.map((fun(x: dynamic): dynamic {
                                return x.id
                            })).sort().join("/")
                            if (truth(ids.has(sig))) {
                                continue
                            }
                            ids.add(sig)
                            generated.push(jsObject { obj173 ->
                                obj173["id"] = jsAdd(jsAdd(jsAdd("injury379-", kind), "-"), n)
                                obj173["name"] = jsAdd(jsAdd(category, " · otros grupos "), jsAdd(n, 1))
                                obj173["category"] = NAMES[c.effective]
                                obj173["description"] = "Movimientos seleccionados fuera de las zonas declaradas, intensidad reducida; sin validez médica individual."
                                obj173["items"] = items
                                obj173["requirements"] = js("[]")
                                obj173["level"] = 0
                                obj173["goalGroups"] = jsonValue("[\"fitness\",\"habit\",\"movement\",\"build\",\"athletic\"]")
                                obj173["extension"] = "standard"
                                obj173["family"] = "adapted"
                                obj173["impact"] = "Sin saltos"
                                obj173["minutes"] = global_Math.max(8, jsMultiply(items.length, 4))
                                obj173["safe379"] = true
                            })
                        }
                    }
                }
            }
            return generated
        }
        E = root.Engine
        A = root.Adapt
        S = root.Schedule
        W = root.Weekly
        if (truth(jsOr(jsOr(jsOr(jsOr((!truth(E))) { (!truth(A)) }) { (!truth(S)) }) { (!truth(W)) }) { (!truth(root.HunterInjuryPlanner378)) })) {
            return absent
        }
        VER = "37.9"
        val ADDITIONS: dynamic = jsonValue("[{\"id\":\"hs379-scapular\",\"name\":\"Juntar omóplatos sentado\",\"group\":\"Espalda\",\"category\":\"Fuerza\",\"zones\":[\"shoulder\",\"back\"],\"unit\":\"reps\",\"instructions\":[\"Sentarse con la espalda apoyada cómodamente y los brazos relajados.\",\"Acercar suavemente los omóplatos sin elevar los hombros.\",\"Volver a la postura neutra sin echar el cuerpo hacia atrás.\"],\"cue\":\"Movimiento pequeño y cómodo; sin mover las piernas.\"},{\"id\":\"hs379-palms\",\"name\":\"Presión suave de palmas sentado\",\"group\":\"Pecho\",\"category\":\"Fuerza\",\"zones\":[\"shoulder\",\"elbow\",\"wrist\",\"back\"],\"unit\":\"sec\",\"instructions\":[\"Sentarse en un apoyo firme con piernas cómodas.\",\"Juntar las palmas frente al pecho sin elevar los hombros.\",\"Presionar suavemente durante el intervalo y soltar.\"],\"cue\":\"Esfuerzo bajo y respiración continua; no usar si la mano, muñeca o hombro está lesionado.\"},{\"id\":\"hs379-isobiceps\",\"name\":\"Flexión de codo con resistencia propia sentado\",\"group\":\"Brazos\",\"category\":\"Fuerza\",\"zones\":[\"shoulder\",\"elbow\",\"wrist\"],\"unit\":\"sec\",\"instructions\":[\"Sentarse con espalda apoyada y hombros relajados.\",\"Flexionar un codo aproximadamente a mitad de recorrido y oponer suavemente la mano contraria.\",\"Mantener una contracción leve y cambiar de brazo; evitar tensión en cuello y muñecas.\"],\"cue\":\"Sin tirones ni esfuerzos máximos; no usar con lesión de brazo, codo o muñeca.\"},{\"id\":\"hs379-shoulderset\",\"name\":\"Elevación corta de hombros sentado\",\"group\":\"Hombros\",\"category\":\"Fuerza\",\"zones\":[\"shoulder\",\"neck\"],\"unit\":\"reps\",\"instructions\":[\"Sentarse con la columna apoyada y las piernas cómodas.\",\"Elevar los hombros solo unos centímetros sin levantar los brazos.\",\"Soltar suavemente hasta la postura neutra.\"],\"cue\":\"Movimiento pequeño sin bloquear el cuello.\"}]")
        if (true) {
            val iterator3: dynamic = iteratorOf(ADDITIONS)
            while (true) {
                val next4: dynamic = iterator3.next()
                if (truth(next4.done)) break
                var x: dynamic = absent
                x = next4.value
                if (truth(E.byId(x.id))) {
                    continue
                }
                val e: dynamic = jsObject { obj5 ->
                    copyFields(obj5, x)
                    obj5["equipment"] = "body"
                    obj5["level"] = 0
                    obj5["steps"] = x.instructions.join(" ")
                    obj5["setup"] = x.instructions[0]
                    obj5["impact"] = "Bajo"
                    obj5["seated"] = true
                    obj5["floor"] = false
                    obj5["overhead"] = false
                    obj5["spineMove"] = false
                    obj5["wristLoad"] = false
                    obj5["kneeBend"] = false
                    obj5["requirements"] = js("[]")
                    obj5["variant"] = "base"
                    obj5["baseId"] = x.id
                    obj5["sourceOnly"] = false
                    obj5["tempo"] = "Lento y controlado."
                    obj5["breath"] = "Respiración natural sin aguantar el aire."
                    obj5["control"] = "Detente si aparece o empeora el dolor."
                    obj5["noVector379"] = true
                }
                E.exercises.push(e)
            }
        }
        TYPES = jsonValue("{\"train\":\"strength\",\"cardio\":\"cardio\",\"mobility\":\"recovery\",\"rest\":\"rest\"}")
        LABELS = jsonValue("{\"train\":\"Fuerza\",\"cardio\":\"Cardio\",\"mobility\":\"Movilidad\",\"rest\":\"Descanso\"}")
        NAMES = jsonValue("{\"strength\":\"Fuerza\",\"cardio\":\"Cardio\",\"recovery\":\"Movilidad\"}")
        ZONES = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"neck\",\"shoulder\",\"elbow\",\"wrist\",\"back\",\"hip\",\"knee\",\"ankle\"]")))
        GROUPS = jsonValue("{\"upper\":[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"],\"lower\":[\"Piernas\",\"Cadera\"],\"other\":[\"Core\",\"Abdomen\"],\"preferred\":{\"knee\":[\"Pecho\",\"Espalda\",\"Brazos\",\"Hombros\",\"Core\",\"Abdomen\",\"Piernas\",\"Cadera\"],\"ankle\":[\"Pecho\",\"Espalda\",\"Brazos\",\"Hombros\",\"Core\",\"Abdomen\",\"Piernas\",\"Cadera\"],\"hip\":[\"Pecho\",\"Espalda\",\"Brazos\",\"Hombros\",\"Core\",\"Abdomen\",\"Piernas\",\"Cadera\"],\"shoulder\":[\"Piernas\",\"Cadera\",\"Core\",\"Abdomen\",\"Brazos\",\"Espalda\",\"Pecho\"],\"elbow\":[\"Piernas\",\"Cadera\",\"Core\",\"Abdomen\",\"Pecho\",\"Espalda\",\"Brazos\"],\"wrist\":[\"Piernas\",\"Cadera\",\"Core\",\"Abdomen\",\"Pecho\",\"Espalda\",\"Brazos\"],\"back\":[\"Piernas\",\"Brazos\",\"Pecho\",\"Cadera\",\"Hombros\",\"Core\",\"Abdomen\",\"Espalda\"],\"neck\":[\"Piernas\",\"Cadera\",\"Core\",\"Abdomen\",\"Brazos\",\"Pecho\",\"Espalda\"]}}")
        val priorMode: dynamic = A.mode
        val priorReasons: dynamic = A.reasons
        val priorAllowed: dynamic = A.allowed
        val localCase: dynamic = (fun(p: dynamic): dynamic {
            return jsAnd((!truth((!truth(p))))) { jsOr(jsOr(active(p).length) { p.limits }) { jsAnd(onPresent(p.health) { value20 -> value20.pain }) { (p.health.pain !== "none") } } }
        })
        A.mode = (fun(p: dynamic): dynamic {
            if (truth(localCase(p))) {
                val s: dynamic = impactStatus(p)
                if (truth(s.ok)) {
                    return jsonValue("{\"auto\":true,\"reduced\":true,\"reason\":\"Entrenamiento limitado a otros grupos musculares. Ejercicios orientativos de intensidad reducida; no representan autorización médica.\"}")
                }
            }
            return priorMode(p)
        })
        A.reasons = (fun(e: dynamic, p: dynamic, arg29: dynamic): dynamic {
            var strict: dynamic = absent
            strict = definedOr(arg29) { true }
            if (truth(jsAnd(localCase(p)) { impactStatus(p).ok })) {
                return (if (truth(approved(e, p))) js("[]") else jsonValue("[\"Ejercicio fuera de los filtros de las zonas afectadas o del equipo disponible\"]"))
            }
            return priorReasons(e, p, strict)
        })
        A.allowed = (fun(e: dynamic, p: dynamic): dynamic {
            if (truth(jsAnd(localCase(p)) { impactStatus(p).ok })) {
                return approved(e, p)
            }
            return priorAllowed(e, p)
        })
        val originalCycle: dynamic = E.makeCycle
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg157: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg157) { js("({})") }
            val c: dynamic = originalCycle(p, start, index, last, logs)
            patchCycle(c, p, logs, start)
            return c
        })
        val previousCurrent: dynamic = global_currentCycle
        global_currentCycle = (fun(): dynamic {
            val c: dynamic = previousCurrent()
            if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
                return c
            }
            val changes: dynamic = patchCycle(c, global_state.profile, jsOr(global_state.logs) { js("({})") }, E.dateKey())
            val d: dynamic = c.days.find((fun(x: dynamic): dynamic {
                return (x.date === E.dateKey())
            }))
            val repaired: dynamic = repairEmptyLog(d)
            if (truth(jsAnd(jsOr(changes) { repaired }) { (js("typeof save") === "function") })) {
                global_save()
            }
            return c
        })
        val beforeLog: dynamic = global_getLog
        global_getLog = (fun(): dynamic {
            val l: dynamic = beforeLog()
            val d: dynamic = (if (truth((js("typeof todayDay") === "function"))) global_todayDay() else null)
            if (truth(jsAnd(repairEmptyLog(d)) { (js("typeof save") === "function") })) {
                global_save()
            }
            return jsOr(onPresent(global_state.logs) { value163 -> value163[onPresent(d) { value162 -> value162.date }] }) { l }
        })
        val prevAll: dynamic = global_allRoutines
        global_allRoutines = (fun(): dynamic {
            return jsArray { array176 ->
                appendAll(array176, prevAll())
                appendAll(array176, templates(global_state.profile, jsOr(global_state.logs) { js("({})") }))
            }
        })
        val oldRoutineCompatible: dynamic = A.routineCompatible
        A.routineCompatible = (fun(r: dynamic, p: dynamic): dynamic {
            return (if (truth(onPresent(r) { value181 -> value181.safe379 })) jsAnd(jsAnd((!truth((!truth(impactStatus(p).ok))))) { (!truth((!truth(onPresent(r.items) { value182 -> value182.length })))) }) { r.items.every((fun(x: dynamic): dynamic {
                return approved(E.byId(x.id), p)
            })) } else oldRoutineCompatible(r, p))
        })
        val oldRoutineList: dynamic = global_routineList
        global_routineList = (fun(): dynamic {
            val old: dynamic = oldRoutineList()
            val p: dynamic = global_state.profile
            if (truth(jsOr((!truth(p))) { jsAnd((!truth(active(p).length))) { (!truth(p.limits)) } })) {
                return old
            }
            val safe: dynamic = templates(p, jsOr(global_state.logs) { js("({})") }).filter((fun(r: dynamic): dynamic {
                return A.routineCompatible(r, p)
            }))
            val used: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(old.map((fun(r: dynamic): dynamic {
                return r.id
            }))))
            return jsArray { array185 ->
                appendAll(array185, old)
                appendAll(array185, safe.filter((fun(r: dynamic): dynamic {
                    return (!truth(used.has(r.id)))
                })))
            }
        })
        val beforeTechnique: dynamic = global_technique
        global_technique = (fun(id: dynamic): dynamic {
            val e: dynamic = E.byId(id)
            if (truth((!truth(onPresent(e) { value188 -> value188.noVector379 })))) {
                return beforeTechnique(id)
            }
            val esc: dynamic = (fun(s: dynamic): dynamic {
                return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\\\"']", "g"), (fun(c: dynamic): dynamic {
                    return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
                }))
            })
            global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<span class=\"eyebrow\">", esc(e.group)), " · MOVIMIENTO SIN EQUIPO</span><h2>"), esc(e.name)), "</h2><div class=\"notice\">No existe una demostración animada validada para esta ficha nueva. Sigue únicamente las instrucciones textuales y evita el ejercicio si produce molestias.</div><ol class=\"tech-steps\">"), e.instructions.map((fun(x: dynamic): dynamic {
                return jsAdd(jsAdd("<li>", esc(x)), "</li>")
            })).join("")), "</ol><p>"), esc(e.cue)), "</p>"))
            return absent
        })
        val oldRoutineView: dynamic = global_routines
        global_routines = (fun(): dynamic {
            var html: dynamic = oldRoutineView()
            if (truth(jsOr((!truth(global_state.profile))) { (!truth(active(global_state.profile).length)) })) {
                return html
            }
            html = html.replace(jsRegExp("^<div class=\"notice injury-banner378\" role=\"note\">[\\s\\S]*?<\\/div>", ""), "")
            val status: dynamic = impactStatus(global_state.profile)
            val num: dynamic = templates(global_state.profile, jsOr(global_state.logs) { js("({})") }).length
            val shown: dynamic = (if (truth(status.ok)) templates(global_state.profile, jsOr(global_state.logs) { js("({})") }) else js("[]"))
            val esc: dynamic = (fun(s: dynamic): dynamic {
                return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\\\"']", "g"), (fun(c: dynamic): dynamic {
                    return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
                }))
            })
            val recommended: dynamic = (if (truth(shown.length)) jsAdd(jsAdd("<section class=\"card\"><span class=\"eyebrow\">RUTINAS ADAPTADAS A TU PERFIL</span><h2>Otros grupos musculares</h2><p>Selecciona una sesión con movimientos fuera de las zonas limitadas.</p><div class=\"routine-grid\">", shown.map((fun(r: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<button class=\"btn ghost\" type=\"button\" onclick=\"routineDetail('", r.id), "')\"><b>"), esc(r.name)), "</b><small> · "), r.items.length), " movimientos · intensidad reducida</small></button>")
            })).join("")), "</div></section>") else "")
            return jsAdd(jsAdd(jsAdd(jsAdd("<div class=\"notice injury-banner378\" role=\"note\">", (if (truth(status.ok)) jsAdd(jsAdd("Alternativas disponibles para grupos no afectados: ", num), " plantillas. Baja exigencia; detén cualquier movimiento que agrave los síntomas.") else jsAdd("No se han asignado ejercicios automáticos: ", global_String(status.reason).replace(jsRegExp("[<>&\"]", "g"), "")))), "</div>"), recommended), html)
        })
        val beforeDetail: dynamic = global_routineDetail
        global_routineDetail = (fun(id: dynamic): dynamic {
            beforeDetail(id)
            if (truth((!truth(onPresent(id) { value193 -> value193.startsWith("injury379-") })))) {
                return absent
            }
            val select: dynamic = global_document.querySelector("#routineDay")
            if (truth(select)) {
                val c: dynamic = global_currentCycle()
                if (true) {
                    val iterator194: dynamic = iteratorOf(jsArray { array196 ->
                        appendAll(array196, select.options)
                    })
                    while (true) {
                        val next195: dynamic = iterator194.next()
                        if (truth(next195.done)) break
                        var option: dynamic = absent
                        option = next195.value
                        val d: dynamic = onPresent(c) { value197 -> onPresent(value197.days) { value198 -> value198.find((fun(x: dynamic): dynamic {
                            return (x.date === option["value"])
                        })) } }
                        if (truth(jsOr((!truth(d))) { jsAnd(S.valid(global_state.profile)) { (global_state.profile.weekPlan[S.weekday(d.date)] === "rest") } })) {
                            option.remove()
                        }
                    }
                }
            }
            return absent
        })
        val beforePrepare: dynamic = global_prepareRoutine
        global_prepareRoutine = (fun(id: dynamic): dynamic {
            if (truth(onPresent(id) { value201 -> value201.startsWith("injury379-") })) {
                val date: dynamic = onPresent(global_document.querySelector("#routineDay")) { value202 -> value202["value"] }
                if (truth(jsOr((!truth(date))) { (onPresent(global_state.profile) { value203 -> value203.weekPlan[S.weekday(date)] } === "rest") })) {
                    return global_routineNotice372("Elige un día de entrenamiento, no uno de descanso.")
                }
            }
            return beforePrepare(id)
        })
        root.HunterPlanner379 = jsObject { obj207 ->
            obj207["version"] = VER
            obj207["status"] = impactStatus
            obj207["approved"] = approved
            obj207["choose"] = choose
            obj207["patchCycle"] = patchCycle
            obj207["repairEmptyLog"] = repairEmptyLog
            obj207["templates"] = templates
        }
        if (truth((!truth(global_Runtime20.initializing)))) {
            try {
                global_currentCycle()
                global_render()
            } catch (err: dynamic) {
                global_console.warn("Motor V37.9", err)
            }
        }
        return absent
    }))(global_window)
}
