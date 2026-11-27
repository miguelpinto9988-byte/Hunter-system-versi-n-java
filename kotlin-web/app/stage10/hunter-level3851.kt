@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterlevel3851

import hunter.browser.*

@JsName("Error") private external var global_Error: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Infinity") private external var global_Infinity: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterLevel3851() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var G: dynamic = absent
        var tierNames: dynamic = absent
        var norm: dynamic = absent
        var tier: dynamic = absent
        var category: dynamic = absent
        var family: dynamic = absent
        var byId: dynamic = absent
        var synonymsByFamily: dynamic = absent
        var negative: dynamic = absent
        var misleading: dynamic = absent
        var logCategory: dynamic = absent
        var failedExerciseIds: dynamic = absent
        var lastCategoryLog: dynamic = absent
        var assess: dynamic = absent
        var readiness: dynamic = absent
        var context: dynamic = absent
        var idealQuota: dynamic = absent
        var levelPool: dynamic = absent
        var dose: dynamic = absent
        var monarchCandidates: dynamic = absent
        failedExerciseIds = fun(l: dynamic): dynamic {
            if (truth(jsOr((!truth(l))) { (jsTypeOf(l.note) !== "string") })) {
                return js("[]")
            }
            val ids: dynamic = jsConstruct(global_Set, js("[]"))
            val segments: dynamic = global_String(l.note).split(jsRegExp("[.;\\n]|\\bpero\\b", "i")).map(norm).filter(global_Boolean)
            val present: dynamic = dynamicValue(jsOr(l.items) { js("[]") }).map((fun(x: dynamic): dynamic {
                return byId["get"](x.id)
            })).filter(global_Boolean)
            if (true) {
                val iterator14: dynamic = iteratorOf(segments)
                while (true) {
                    val next15: dynamic = iterator14.next()
                    if (truth(next15.done)) break
                    var text: dynamic = absent
                    text = next15.value
                    if (truth(jsOr((!truth(negative.test(text)))) { misleading.test(text) })) {
                        continue
                    }
                    if (true) {
                        val iterator16: dynamic = iteratorOf(present)
                        while (true) {
                            val next17: dynamic = iterator16.next()
                            if (truth(next17.done)) break
                            var e: dynamic = absent
                            e = next17.value
                            val label: dynamic = norm(e.name)
                            val aliases: dynamic = jsOr(synonymsByFamily["get"](family(e))) { js("[]") }
                            val matched: dynamic = jsOr(jsAnd(jsAtLeast(label.length, 9)) { text.includes(label) }) { aliases.some((fun(a: dynamic): dynamic {
                                return text.includes(a)
                            })) }
                            if (truth(matched)) {
                                ids.add(e.id)
                            }
                        }
                    }
                }
            }
            return jsArray { array18 ->
                appendAll(array18, ids)
            }
        }
        lastCategoryLog = fun(logs: dynamic, date: dynamic, cat: dynamic): dynamic {
            return jsOr(global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg23: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked24: dynamic = fromIterable(arg23)
                d = unpacked24[0]
                l = unpacked24[1]
                return jsAnd(jsAnd(jsAnd(dynamicValue(jsRegExp("^\\d{4}-\\d\\d-\\d\\d\$", "")).test(d)) { jsLess(d, date) }) { jsAtLeast(d, E["plus"](date, (-numeric(90)))) }) { (logCategory(l) === cat) }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return b[0].localeCompare(a[0])
            }))[0]) { null }
        }
        assess = fun(logs: dynamic, date: dynamic, cat: dynamic): dynamic {
            var lastDate: dynamic = absent
            var l: dynamic = absent
            val last: dynamic = lastCategoryLog(logs, date, cat)
            if (truth((!truth(last)))) {
                return jsonValue("{\"action\":\"none\",\"failedIds\":[],\"failedFamilies\":[],\"lastDate\":null,\"why\":\"\"}")
            }
            val unpacked27: dynamic = fromIterable(last)
            lastDate = unpacked27[0]
            l = unpacked27[1]
            val failedIds: dynamic = failedExerciseIds(l)
            if (true) {
                val iterator28: dynamic = iteratorOf(jsOr(l.items) { js("[]") })
                while (true) {
                    val next29: dynamic = iterator28.next()
                    if (truth(next29.done)) break
                    var x: dynamic = absent
                    x = next29.value
                    if (truth(jsAnd(jsAnd(onPresent(x["actual"]) { value30 -> value30.some((fun(y: dynamic): dynamic {
                        return y.done
                    })) }) { x["actual"].some((fun(y: dynamic): dynamic {
                        return (!truth(y.done))
                    })) }) { (!truth(failedIds.includes(x.id))) })) {
                        failedIds.push(x.id)
                    }
                }
            }
            val high: dynamic = jsOr(jsOr(jsAtLeast(global_Number(l.rpe), 8)) { (l.pain === true) }) { (l.fatigue === true) }
            val action: dynamic = (if (truth(jsOr(failedIds.length) { high })) "down" else "none")
            val failedFamilies: dynamic = jsArray { array31 ->
                appendAll(array31, jsConstruct(global_Set, arrayOf<dynamic>(failedIds.map((fun(id: dynamic): dynamic {
                    return family(byId["get"](id))
                })))))
            }
            return jsObject { obj32 ->
                obj32["action"] = action
                obj32["failedIds"] = failedIds
                obj32["failedFamilies"] = failedFamilies
                obj32["lastDate"] = lastDate
                obj32["why"] = (if (truth(failedIds.length)) "ejercicio_no_completado" else (if (truth(high)) "esfuerzo_alto" else ""))
                obj32["score"] = jsOr(global_Number(l.rpe)) { 0 }
            }
        }
        readiness = fun(logs: dynamic, date: dynamic, cat: dynamic): dynamic {
            val rows: dynamic = global_Object.entries(jsOr(logs) { js("({})") }).filter((fun(arg41: dynamic): dynamic {
                var d: dynamic = absent
                var l: dynamic = absent
                val unpacked42: dynamic = fromIterable(arg41)
                d = unpacked42[0]
                l = unpacked42[1]
                return jsAnd(jsAnd(jsLess(d, date)) { jsAtLeast(d, E["plus"](date, (-numeric(90)))) }) { (logCategory(l) === cat) }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return b[0].localeCompare(a[0])
            })).slice(0, 5)
            if (truth(jsLess(rows.length, 3))) {
                return false
            }
            return rows.slice(0, 3).every((fun(arg43: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked44: dynamic = fromIterable(arg43)
                l = unpacked44[1]
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(l.complete) { (!truth(l.pain)) }) { (!truth(l.fatigue)) }) { jsAtLeast(global_Number(l.rpe), 1) }) { jsAtMost(global_Number(l.rpe), 5) }) { (!truth(failedExerciseIds(l).length)) }
            }))
        }
        context = fun(p: dynamic, arg47: dynamic, arg48: dynamic, arg49: dynamic, arg50: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg47) { js("({})") }
            var date: dynamic = absent
            date = definedOr(arg48) { E.dateKey() }
            var cat: dynamic = absent
            cat = definedOr(arg49) { "upper" }
            var count: dynamic = absent
            count = definedOr(arg50) { 5 }
            val base: dynamic = global_Math.max(0, tierNames.indexOf(onPresent(p) { value51 -> value51.difficulty }))
            val failure: dynamic = assess(logs, date, cat)
            val ceiling: dynamic = global_Math.max(0, jsSubtract(base, (if (truth((failure.action === "down"))) 1 else 0)))
            val preview: dynamic = jsAnd(jsAnd(jsAnd(jsAnd(jsAnd((base === 1)) { (ceiling === 1) }) { jsAtLeast(global_Number(onPresent(p) { value52 -> value52.age }), 18) }) { (!truth(onPresent(p) { value53 -> value53.limits })) }) { (!truth(onPresent(p) { value54 -> onPresent(value54.health) { value55 -> value55.pain } })) }) { readiness(logs, date, cat) }
            val maxTier: dynamic = global_Math.min(2, jsAdd(ceiling, (if (truth(preview)) 1 else 0)))
            return jsObject { obj56 ->
                obj56["profileTier"] = base
                obj56["ceiling"] = ceiling
                obj56["maxTier"] = maxTier
                obj56["preview"] = preview
                obj56["category"] = cat
                obj56["count"] = count
                obj56["failure"] = failure
            }
        }
        idealQuota = fun(ctx: dynamic, count: dynamic): dynamic {
            val ratios: dynamic = (if (truth(ctx.preview)) jsonValue("[0.6,0.2,0.2]") else (if (truth((ctx.ceiling === 0))) jsonValue("[1,0,0]") else (if (truth((ctx.ceiling === 1))) jsonValue("[0.6,0.4,0]") else jsonValue("[0.2,0.4,0.4]"))))
            val q: dynamic = ratios.map((fun(x: dynamic): dynamic {
                return global_Math.floor(jsMultiply(x, count))
            }))
            while (truth(jsLess(q.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b)
            }), 0), count))) {
                val k: dynamic = ratios.map((fun(x: dynamic, i: dynamic): dynamic {
                    return jsSubtract(jsMultiply(x, count), q[i])
                })).indexOf(run { val target60: dynamic = global_Math; invokeWith(target60.max, target60, jsArray { array59 ->
                    appendAll(array59, ratios.map((fun(x: dynamic, i: dynamic): dynamic {
                        return jsSubtract(jsMultiply(x, count), q[i])
                    })))
                }) })
                run {
                    val target61: dynamic = q
                    val key62: dynamic = k
                    val previous63: dynamic = numeric(target61[key62])
                    target61[key62] = jsAdd(previous63, 1)
                }
            }
            return q
        }
        levelPool = fun(candidates: dynamic, selected: dynamic, ctx: dynamic, count: dynamic): dynamic {
            if (truth((!truth(ctx)))) {
                return candidates
            }
            val blocked: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsOr(ctx.failure.failedFamilies) { js("[]") }))
            var xs: dynamic = candidates.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAtMost(tier(e), ctx.maxTier)) { (!truth(blocked.has(family(e)))) }
            }))
            if (truth((!truth(xs.length)))) {
                xs = candidates.filter((fun(e: dynamic): dynamic {
                    return jsAtMost(tier(e), ctx.ceiling)
                }))
            }
            if (truth((!truth(xs.length)))) {
                return js("[]")
            }
            val quota: dynamic = idealQuota(ctx, count)
            val used: dynamic = jsonValue("[0,0,0]")
            if (true) {
                val iterator66: dynamic = iteratorOf(selected)
                while (true) {
                    val next67: dynamic = iterator66.next()
                    if (truth(next67.done)) break
                    var e: dynamic = absent
                    e = next67.value
                    run {
                        val target68: dynamic = used
                        val key69: dynamic = tier(e)
                        val previous70: dynamic = numeric(target68[key69])
                        target68[key69] = jsAdd(previous70, 1)
                    }
                }
            }
            var best: dynamic = (-numeric(1))
            var need: dynamic = (-numeric(global_Infinity))
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration71 = true
                while (true) {
                    if (!firstIteration71) {
                        run {
                            val previous72: dynamic = numeric(i)
                            i = jsAdd(previous72, 1)
                        }
                    }
                    firstIteration71 = false
                    if (!truth(jsLess(i, 3))) break
                    val rest: dynamic = jsSubtract(quota[i], used[i])
                    if (truth(jsAnd(jsAnd(jsMore(rest, 0)) { xs.some((fun(e: dynamic): dynamic {
                        return (tier(e) === i)
                    })) }) { jsMore(rest, need) })) {
                        best = i
                        need = rest
                    }
                }
            }
            if (truth(jsLess(best, 0))) {
                val possible: dynamic = jsArray { array73 ->
                    appendAll(array73, jsConstruct(global_Set, arrayOf<dynamic>(xs.map(tier))))
                }
                best = possible.sort((fun(a: dynamic, b: dynamic): dynamic {
                    return jsOr(jsSubtract(jsSubtract(quota[b], used[b]), jsSubtract(quota[a], used[a]))) { jsSubtract(a, b) }
                }))[0]
            }
            return xs.filter((fun(e: dynamic): dynamic {
                return (tier(e) === best)
            }))
        }
        dose = fun(item: dynamic, e: dynamic, ctx: dynamic): dynamic {
            if (truth(jsOr((!truth(ctx))) { (!truth(item)) })) {
                return item
            }
            val t: dynamic = tier(e)
            val trial: dynamic = jsAnd(jsAnd(ctx.preview) { (t === 2) }) { (ctx.profileTier === 1) }
            val `out`: dynamic = jsObject { obj76 ->
                copyFields(obj76, item)
                obj76["level3851"] = jsObject { obj77 ->
                    obj77["tier"] = t
                    obj77["label"] = tierNames[t]
                    obj77["profile"] = tierNames[ctx.profileTier]
                    obj77["category"] = ctx.category
                    obj77["preview"] = trial
                    obj77["limitedByFeedback"] = (ctx.failure.action === "down")
                    obj77["reason"] = jsOr(ctx.failure.why) { "mezcla_por_nivel" }
                }
            }
            if (truth(trial)) {
                `out`.sets = 1
                `out`.target = global_Math.min(jsOr(global_Number(`out`.target)) { 4 }, (if (truth((e.unit === "sec"))) 10 else 4))
                `out`.noAutoLoad = true
            }
            return `out`
        }
        monarchCandidates = fun(p: dynamic, logs: dynamic, date: dynamic, cat: dynamic): dynamic {
            val ctx: dynamic = context(p, logs, date, cat, 5)
            val blocked: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(ctx.failure.failedFamilies))
            return E.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd((category(e) === cat)) { jsAtMost(tier(e), ctx.maxTier) }) { G.inKind(e, (if (truth((cat === "cardio"))) "cardio" else (if (truth((cat === "mobility"))) "recovery" else "strength"))) }) { (!truth(G.power(e))) }) { (!truth(G.impact(e))) }) { G.eligible(e, p) }) { (!truth(blocked.has(family(e)))) }) { (onPresent(root.HunterMaterials3814) { value86 -> run { val owner88: dynamic = value86; onPresent(owner88.eligible) { method87 -> invokeWith(method87, owner88, arrayOf<dynamic>(e, p)) } } } !== false) }) { (onPresent(root.Adapt) { value89 -> run { val owner91: dynamic = value89; onPresent(owner91.allowed) { method90 -> invokeWith(method90, owner91, arrayOf<dynamic>(e, p)) } } } !== false) }
            }))
        }
        if (truth(root.HunterLevel3851)) {
            return absent
        }
        E = root.Engine
        G = root.HunterGroups49
        val CP: dynamic = root.HunterCategoryProgress51
        if (truth(jsOr((!truth(E))) { (!truth(G)) })) {
            throw global_Error("HunterLevel3851: requiere Engine y HunterGroups49")
        }
        tierNames = jsonValue("[\"basic\",\"intermediate\",\"advanced\"]")
        val catNames: dynamic = jsonValue("[\"upper\",\"lower\",\"core\",\"cardio\",\"mobility\"]")
        norm = (fun(s: dynamic): dynamic {
            return global_String(jsOr(s) { "" }).toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "").replace(jsRegExp("[^a-z0-9\\s-]", "g"), " ").replace(jsRegExp("\\s+", "g"), " ").trim()
        })
        tier = (fun(e: dynamic): dynamic {
            return global_Math.max(0, global_Math.min(2, global_Number(nullableOr(nullableOr(onPresent(G.classify(e)) { value3 -> value3.level }) { onPresent(e) { value4 -> value4.level } }) { 0 })))
        })
        category = (fun(e: dynamic): dynamic {
            return jsOr(onPresent(G.classify(e)) { value5 -> value5.group }) { null }
        })
        family = (fun(e: dynamic): dynamic {
            return G.family(e)
        })
        byId = jsConstruct(global_Map, arrayOf<dynamic>(E.exercises.map((fun(e: dynamic): dynamic {
            return arrayOf<dynamic>(e.id, e)
        }))))
        if (truth((byId.size !== E.exercises.length))) {
            throw global_Error("HunterLevel3851: catálogo con ID duplicado")
        }
        val inventory: dynamic = global_Object.freeze(global_Object.fromEntries(catNames.map((fun(k: dynamic): dynamic {
            return arrayOf<dynamic>(k, global_Object.freeze(dynamicValue(jsonValue("[0,1,2]")).map((fun(n: dynamic): dynamic {
                return E.exercises.filter((fun(e: dynamic): dynamic {
                    return jsAnd((category(e) === k)) { (tier(e) === n) }
                })).length
            }))))
        }))))
        if (truth((global_Object.values(inventory).flat().reduce((fun(a: dynamic, b: dynamic): dynamic {
            return jsAdd(a, b)
        }), 0) !== E.exercises.length))) {
            throw global_Error("HunterLevel3851: catálogo sin clasificar")
        }
        val keywords: dynamic = jsonValue("{\"archer19\":[\"flexiones arqueras\",\"flexiones arqueadas\",\"flexiones tipo arquero\",\"flexion arquera\"],\"home-diamond23\":[\"flexiones diamante\",\"flexiones en diamante\",\"flexion diamante\"],\"squatjump\":[\"sentadillas con salto\",\"sentadilla con salto\",\"sentadillas saltando\"],\"kneepush\":[\"flexiones con rodillas\",\"flexiones de rodillas\"],\"pushup\":[\"flexiones normales\",\"flexiones convencionales\",\"flexiones estandar\"],\"chair\":[\"sentadillas con silla\",\"sentadillas con apoyo\",\"sentadillas en silla\"]}")
        synonymsByFamily = jsConstruct(global_Map, js("[]"))
        if (true) {
            val iterator6: dynamic = iteratorOf(global_Object.entries(keywords))
            while (true) {
                val next7: dynamic = iterator6.next()
                if (truth(next7.done)) break
                var id: dynamic = absent
                var synonyms: dynamic = absent
                val unpacked8: dynamic = fromIterable(next7.value)
                id = unpacked8[0]
                synonyms = unpacked8[1]
                val e: dynamic = byId["get"](id)
                if (truth(e)) {
                    synonymsByFamily["set"](family(e), synonyms.map(norm))
                }
            }
        }
        negative = jsRegExp("(?:\\bno\\s+(?:pude|logre|consegui|termine|complete|aguante|soy capaz)\\b|\\b(?:imposible|falle|fall[eé]|fracase|demasiado dificil|muy dificil|me costo mucho|no alcance|dolor al hacer|me dolio al hacer|no me salio)\\b)", "")
        misleading = jsRegExp("(?:\\bno\\s+(?:fue|es|estuvo|me parecio)\\s+(?:muy\\s+)?dificil\\b|\\bno\\s+tuve\\s+(?:problemas|dificultad)\\b|\\bsi\\s+pude\\b)", "")
        logCategory = (fun(l: dynamic): dynamic {
            return jsOr(onPresent(CP) { value9 -> run { val owner11: dynamic = value9; onPresent(owner11.categoryOfLog) { method10 -> invokeWith(method10, owner11, arrayOf<dynamic>(l)) } } }) { null }
        })
        root.HunterLevel3851 = global_Object.freeze(jsObject { obj94 ->
            obj94["version"] = "38.51"
            obj94["tier"] = tier
            obj94["category"] = category
            obj94["inventory"] = inventory
            obj94["catalogSize"] = E.exercises.length
            obj94["failedExerciseIds"] = failedExerciseIds
            obj94["assess"] = assess
            obj94["readiness"] = readiness
            obj94["context"] = context
            obj94["idealQuota"] = idealQuota
            obj94["levelPool"] = levelPool
            obj94["dose"] = dose
            obj94["monarchCandidates"] = monarchCandidates
        })
        return absent
    }))(global_window)
}
