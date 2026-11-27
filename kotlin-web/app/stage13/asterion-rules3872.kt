@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installasterionrules3872

import hunter.browser.*

@JsName("module") private external var global_module: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("RegExp") private external var global_RegExp: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Infinity") private external var global_Infinity: dynamic

internal fun installAsterionRules3872() {
    dynamicValue((fun(factory: dynamic): dynamic {
        val api: dynamic = factory()
        if (truth(jsAnd((js("typeof module") === "object")) { global_module.exports })) {
            global_module.exports = api
        }
        if (truth((js("typeof window") !== "undefined"))) {
            global_window.AsterionRules3872 = api
        }
        return absent
    }))((fun(): dynamic {
        var CATS: dynamic = absent
        var ALIASES: dynamic = absent
        var STATES: dynamic = absent
        var INTENTS: dynamic = absent
        var ISO: dynamic = absent
        var normalized: dynamic = absent
        var cat: dynamic = absent
        var date: dynamic = absent
        var addDays: dynamic = absent
        var distance: dynamic = absent
        var weekday: dynamic = absent
        var canonical: dynamic = absent
        var fingerprint: dynamic = absent
        var parseGoal: dynamic = absent
        var validProfile: dynamic = absent
        var defaultProfile: dynamic = absent
        var strictList: dynamic = absent
        var catalog: dynamic = absent
        var compatible: dynamic = absent
        var safeItems: dynamic = absent
        var stateEmpty: dynamic = absent
        var dataState: dynamic = absent
        var recordOutcome: dynamic = absent
        var correctOutcome: dynamic = absent
        var completionSummary: dynamic = absent
        var globalEffort: dynamic = absent
        var effort: dynamic = absent
        var lastEffort: dynamic = absent
        var weekly: dynamic = absent
        var decision: dynamic = absent
        var phaseMix: dynamic = absent
        var phaseReview: dynamic = absent
        var approvePhase: dynamic = absent
        var globalReview: dynamic = absent
        var volume: dynamic = absent
        var doseHistory: dynamic = absent
        var rank: dynamic = absent
        var generate: dynamic = absent
        cat = fun(v: dynamic): dynamic {
            val s: dynamic = normalized(v)
            return (if (truth(CATS.includes(s))) s else jsOr(ALIASES[s]) { null })
        }
        date = fun(day: dynamic): dynamic {
            if (truth(jsOr((jsTypeOf(day) !== "string")) { (!truth(ISO.test(day))) })) {
                return null
            }
            val d: dynamic = jsConstruct(global_Date, arrayOf<dynamic>(jsAdd(day, "T12:00:00Z")))
            return (if (truth(jsAnd((!truth(global_Number.isNaN(d.getTime())))) { (d.toISOString().slice(0, 10) === day) })) d else null)
        }
        addDays = fun(day: dynamic, delta: dynamic): dynamic {
            val d: dynamic = date(day)
            if (truth((!truth(d)))) {
                throw global_Error("Fecha inválida")
            }
            d.setUTCDate(jsAdd(d.getUTCDate(), delta))
            return d.toISOString().slice(0, 10)
        }
        distance = fun(a: dynamic, b: dynamic): dynamic {
            val x: dynamic = date(a)
            val y: dynamic = date(b)
            if (truth(jsOr((!truth(x))) { (!truth(y)) })) {
                throw global_Error("Fecha inválida")
            }
            return global_Math.round(jsDivide(jsSubtract(x, y), 86400000))
        }
        weekday = fun(day: dynamic): dynamic {
            val d: dynamic = date(day)
            if (truth((!truth(d)))) {
                throw global_Error("Fecha inválida")
            }
            return jsMod(jsAdd(d.getUTCDay(), 6), 7)
        }
        canonical = fun(v: dynamic): dynamic {
            if (truth(global_Array.isArray(v))) {
                return jsAdd(jsAdd("[", v.map(canonical).join(",")), "]")
            }
            if (truth(jsAnd(v) { (jsTypeOf(v) === "object") })) {
                return jsAdd(jsAdd("{", global_Object.keys(v).sort().map((fun(k: dynamic): dynamic {
                    return jsAdd(jsAdd(global_JSON.stringify(k), ":"), canonical(v[k]))
                })).join(",")), "}")
            }
            return global_JSON.stringify(v)
        }
        fingerprint = fun(x: dynamic): dynamic {
            var h: dynamic = 2166136261.0
            val s: dynamic = canonical(x)
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration23 = true
                while (true) {
                    if (!firstIteration23) {
                        run {
                            val previous24: dynamic = numeric(i)
                            i = jsAdd(previous24, 1)
                        }
                    }
                    firstIteration23 = false
                    if (!truth(jsLess(i, s.length))) break
                    run {
                        h = jsBitXor(h, s.charCodeAt(i))
                    }
                    h = jsUnsignedShift(global_Math.imul(h, 16777619), 0)
                }
            }
            return h["toString"](16).padStart(8, "0")
        }
        parseGoal = fun(raw: dynamic): dynamic {
            val text: dynamic = normalized(raw).slice(0, 500)
            val found: dynamic = js("[]")
            val excluded: dynamic = js("[]")
            if (true) {
                val iterator27: dynamic = iteratorOf(global_Object.entries(INTENTS))
                while (true) {
                    val next28: dynamic = iterator27.next()
                    if (truth(next28.done)) break
                    var tag: dynamic = absent
                    var terms: dynamic = absent
                    val unpacked29: dynamic = fromIterable(next28.value)
                    tag = unpacked29[0]
                    terms = unpacked29[1]
                    if (true) {
                        val iterator30: dynamic = iteratorOf(terms)
                        while (true) {
                            val next31: dynamic = iterator30.next()
                            if (truth(next31.done)) break
                            var term: dynamic = absent
                            term = next31.value
                            val at: dynamic = text.indexOf(term)
                            if (truth(jsOr(jsLess(at, 0)) { (!truth(jsConstruct(global_RegExp, arrayOf<dynamic>(jsAdd(jsAdd("(^|\\s)", term.replace(jsRegExp("\\s+", "g"), "\\s+")), "(\\s|\$)"))).test(text))) })) {
                                continue
                            }
                            val before: dynamic = jsOr(text.slice(global_Math.max(0, jsSubtract(at, 55)), at).split(jsRegExp("\\bpero\\b|\\by\\b|[.;]", "")).at((-numeric(1)))) { "" }
                            val neg: dynamic = dynamicValue(jsRegExp("(?:\\bno\\s+(?:quiero|deseo|puedo|hacer|practicar)?\\s*\$|\\bevitar\\s*\$|\\bsin\\s*\$|\\bno\\s+me\\s+interesa\\s*\$|\\bdejar\\s+de\\s*\$)", "")).test(jsAdd(before.trim(), " "))
                            dynamicValue((if (truth(neg)) excluded else found)).push(tag)
                            break
                        }
                    }
                }
            }
            return jsObject { obj32 ->
                obj32["recognized"] = dynamicValue(jsArray { array34 ->
                    appendAll(array34, jsConstruct(global_Set, arrayOf<dynamic>(found)))
                }).filter((fun(t: dynamic): dynamic {
                    return (!truth(excluded.includes(t)))
                }))
                obj32["excluded"] = jsArray { array35 ->
                    appendAll(array35, jsConstruct(global_Set, arrayOf<dynamic>(excluded)))
                }
                obj32["recognizedAny"] = jsMore(jsAdd(found.length, excluded.length), 0)
                obj32["savedText"] = global_String(jsOr(raw) { "" }).slice(0, 500)
            }
        }
        validProfile = fun(p: dynamic): dynamic {
            val errors: dynamic = js("[]")
            if (truth(jsOr((!truth(p))) { (jsTypeOf(p) !== "object") })) {
                return jsonValue("{\"ok\":false,\"errors\":[\"No hay perfil activado\"]}")
            }
            if (truth((!truth(dynamicValue(jsonValue("[\"principiante\",\"intermedio\",\"avanzado\"]")).includes(p.experience))))) {
                errors.push("Experiencia inválida")
            }
            if (truth(jsOr(jsOr((!truth(global_Array.isArray(p.weekPlan)))) { (p.weekPlan.length !== 7) }) { (!truth(p.weekPlan.every((fun(x: dynamic): dynamic {
                return jsAnd(jsAnd(global_Number.isInteger(x)) { jsAtLeast(x, 0) }) { jsAtMost(x, 5) }
            })))) })) {
                errors.push("Calendario de siete días inválido")
            }
            if (truth(jsOr((!truth(global_Array.isArray(p.equipment)))) { (!truth(p.equipment.every((fun(x: dynamic): dynamic {
                return jsAnd((jsTypeOf(x) === "string")) { jsLess(x.length, 80) }
            })))) })) {
                errors.push("Equipamiento inválido")
            }
            if (truth((!truth(dynamicValue(jsonValue("[\"misiones\",\"rutinas\"]")).includes(p.modality))))) {
                errors.push("Modalidad inválida")
            }
            if (truth(jsOr(jsOr((!truth(global_Number.isInteger(p.dailyLimit)))) { jsLess(p.dailyLimit, 1) }) { jsMore(p.dailyLimit, 12) })) {
                errors.push("Límite diario inválido")
            }
            if (truth((!truth(dynamicValue(jsonValue("[\"baja\",\"moderada\"]")).includes(p.intensity))))) {
                errors.push("Intensidad inválida")
            }
            if (truth(jsOr((jsTypeOf(p.custom_goal) !== "string")) { jsMore(p.custom_goal.length, 500) })) {
                errors.push("Objetivo escrito inválido")
            }
            if (truth(jsOr(jsOr((!truth(global_Number.isInteger(p.minutes)))) { jsLess(p.minutes, 5) }) { jsMore(p.minutes, 240) })) {
                errors.push("Minutos inválidos")
            }
            if (truth(jsOr(jsOr((!truth(global_Number.isInteger(p.restSeconds)))) { jsLess(p.restSeconds, 0) }) { jsMore(p.restSeconds, 1800) })) {
                errors.push("Descanso entre bloques inválido")
            }
            if (truth((!truth(dynamicValue(jsonValue("[\"series\",\"rondas\"]")).includes(p.organization))))) {
                errors.push("Organización inválida")
            }
            if (truth((!truth(global_Array.isArray(p.restrictions))))) {
                errors.push("Restricciones inválidas")
            }
            return jsObject { obj38 ->
                obj38["ok"] = (!truth(errors.length))
                obj38["errors"] = errors
            }
        }
        defaultProfile = fun(): dynamic {
            return jsonValue("{\"active\":false,\"experience\":\"principiante\",\"goal\":\"\",\"custom_goal\":\"\",\"weekPlan\":[5,5,5,5,5,5,5],\"equipment\":[],\"modality\":\"misiones\",\"dailyLimit\":3,\"intensity\":\"baja\",\"restSeconds\":90,\"organization\":\"series\",\"minutes\":20,\"restrictions\":[]}")
        }
        strictList = fun(x: dynamic, name: dynamic): dynamic {
            if (truth((!truth(global_Array.isArray(x))))) {
                throw global_Error(jsAdd(name, " no es una lista"))
            }
            return x
        }
        catalog = fun(provider: dynamic): dynamic {
            if (truth(jsOr((!truth(provider))) { (jsTypeOf(provider) !== "object") })) {
                throw global_Error("Debe inyectarse un catálogo externo")
            }
            val cats: dynamic = strictList(run { val owner46: dynamic = provider; onPresent(owner46.categories) { method45 -> invokeWith(method45, owner46, js("[]")) } }, "categories")
            val families: dynamic = strictList(run { val owner48: dynamic = provider; onPresent(owner48.families) { method47 -> invokeWith(method47, owner48, js("[]")) } }, "families")
            val types: dynamic = strictList(run { val owner50: dynamic = provider; onPresent(owner50.types) { method49 -> invokeWith(method49, owner50, js("[]")) } }, "types")
            val variants: dynamic = strictList(run { val owner52: dynamic = provider; onPresent(owner52.variants) { method51 -> invokeWith(method51, owner52, js("[]")) } }, "variants")
            val assertUnique: dynamic = (fun(xs: dynamic, label: dynamic): dynamic {
                val ids: dynamic = jsConstruct(global_Set, js("[]"))
                if (true) {
                    val iterator53: dynamic = iteratorOf(xs)
                    while (true) {
                        val next54: dynamic = iterator53.next()
                        if (truth(next54.done)) break
                        var v: dynamic = absent
                        v = next54.value
                        if (truth(jsOr(jsOr(jsOr((!truth(v))) { (jsTypeOf(v.id) !== "string") }) { (!truth(dynamicValue(jsRegExp("^[A-Za-z0-9][A-Za-z0-9_.:-]{0,127}\$", "")).test(v.id))) }) { ids.has(v.id) })) {
                            throw global_Error(jsAdd("ID duplicado o vacío: ", label))
                        }
                        ids.add(v.id)
                    }
                }
                return ids
            })
            val categoryIds: dynamic = assertUnique(cats, "categoria")
            val familyIds: dynamic = assertUnique(families, "familia")
            val typeIds: dynamic = assertUnique(types, "tipo")
            val variantIds: dynamic = assertUnique(variants, "variante")
            if (truth(jsOr((cats.length !== 5)) { (!truth(CATS.every((fun(x: dynamic): dynamic {
                return categoryIds.has(x)
            })))) })) {
                throw global_Error("El catálogo debe declarar las cinco categorías exactas")
            }
            val fm: dynamic = jsConstruct(global_Map, arrayOf<dynamic>(families.map((fun(x: dynamic): dynamic {
                return arrayOf<dynamic>(x.id, x)
            }))))
            val tm: dynamic = jsConstruct(global_Map, arrayOf<dynamic>(types.map((fun(x: dynamic): dynamic {
                return arrayOf<dynamic>(x.id, x)
            }))))
            val vm: dynamic = jsConstruct(global_Map, js("[]"))
            if (true) {
                val iterator55: dynamic = iteratorOf(families)
                while (true) {
                    val next56: dynamic = iterator55.next()
                    if (truth(next56.done)) break
                    var f: dynamic = absent
                    f = next56.value
                    if (truth((!truth(categoryIds.has(cat(f.category)))))) {
                        throw global_Error(jsAdd("Familia con categoría inexistente: ", f.id))
                    }
                }
            }
            if (true) {
                val iterator57: dynamic = iteratorOf(types)
                while (true) {
                    val next58: dynamic = iterator57.next()
                    if (truth(next58.done)) break
                    var t: dynamic = absent
                    t = next58.value
                    if (truth((!truth(familyIds.has(t.family))))) {
                        throw global_Error(jsAdd("Tipo con familia inexistente: ", t.id))
                    }
                }
            }
            if (true) {
                val iterator59: dynamic = iteratorOf(variants)
                while (true) {
                    val next60: dynamic = iterator59.next()
                    if (truth(next60.done)) break
                    var v: dynamic = absent
                    v = next60.value
                    if (truth((!truth(typeIds.has(v.type))))) {
                        throw global_Error(jsAdd("Variante con tipo inexistente: ", v.id))
                    }
                    val t: dynamic = tm["get"](v.type)
                    val f: dynamic = fm["get"](t.family)
                    val c: dynamic = cat(f.category)
                    if (truth(jsOr(jsAnd((v.family !== absent)) { (v.family !== f.id) }) { jsAnd((v.category !== absent)) { (cat(v.category) !== c) } })) {
                        throw global_Error(jsAdd("Relación inconsistente: ", v.id))
                    }
                    if (truth(jsOr(jsOr((!truth(global_Number.isInteger(v.level)))) { jsLess(v.level, 0) }) { jsMore(v.level, 5) })) {
                        throw global_Error(jsAdd("Nivel técnico inválido: ", v.id))
                    }
                    if (truth(jsOr((!truth(global_Array.isArray(v.equipment)))) { v.equipment.some((fun(x: dynamic): dynamic {
                        return (jsTypeOf(x) !== "string")
                    })) })) {
                        throw global_Error(jsAdd("Equipamiento inválido: ", v.id))
                    }
                    if (truth(jsOr((jsTypeOf(v.supervisionRequired) !== "boolean")) { (jsTypeOf(v.automaticEligible) !== "boolean") })) {
                        throw global_Error(jsAdd("Elegibilidad sin validar: ", v.id))
                    }
                    if (truth((!truth(dynamicValue(jsonValue("[\"baja\",\"moderada\",\"alta\"]")).includes(v.intensity))))) {
                        throw global_Error(jsAdd("Intensidad inválida: ", v.id))
                    }
                    if (truth(jsOr((jsTypeOf(v.modality) !== "string")) { (!truth(v.modality)) })) {
                        throw global_Error(jsAdd("Modalidad sin validar: ", v.id))
                    }
                    if (truth(jsOr((!truth(global_Array.isArray(v.restrictions)))) { (!truth(global_Array.isArray(v.tags))) })) {
                        throw global_Error(jsAdd("Etiquetas/restricciones inválidas: ", v.id))
                    }
                    vm["set"](v.id, global_Object.freeze(jsObject { obj61 ->
                        copyFields(obj61, v)
                        obj61["category"] = c
                        obj61["family"] = f.id
                        obj61["type"] = t.id
                    }))
                }
            }
            val frozen: dynamic = global_Object.freeze(dynamicValue(jsArray { array63 ->
                appendAll(array63, vm.values())
            }).sort((fun(a: dynamic, b: dynamic): dynamic {
                return a.id.localeCompare(b.id)
            })))
            val revision: dynamic = global_String(jsOr(provider.revision) { "sin-version" })
            val api: dynamic = jsObject { obj64 ->
                obj64["revision"] = revision
                obj64["all"] = (fun(): dynamic {
                    return frozen.slice()
                })
                obj64["byId"] = (fun(id: dynamic): dynamic {
                    return jsOr(vm["get"](id)) { null }
                })
                obj64["categories"] = (fun(): dynamic {
                    return CATS.slice()
                })
                obj64["families"] = (fun(c: dynamic): dynamic {
                    return families.filter((fun(f: dynamic): dynamic {
                        return (cat(f.category) === cat(c))
                    })).map((fun(f: dynamic): dynamic {
                        return jsObject { obj65 ->
                            copyFields(obj65, f)
                        }
                    }))
                })
                obj64["types"] = (fun(family: dynamic): dynamic {
                    return types.filter((fun(t: dynamic): dynamic {
                        return (t.family === family)
                    })).map((fun(t: dynamic): dynamic {
                        return jsObject { obj66 ->
                            copyFields(obj66, t)
                        }
                    }))
                })
                obj64["variants"] = (fun(type: dynamic): dynamic {
                    return frozen.filter((fun(v: dynamic): dynamic {
                        return (v.type === type)
                    }))
                })
                obj64["categoryOf"] = (fun(id: dynamic): dynamic {
                    return jsOr(onPresent(vm["get"](id)) { value67 -> value67.category }) { null }
                })
                obj64["familyOf"] = (fun(id: dynamic): dynamic {
                    return jsOr(onPresent(vm["get"](id)) { value68 -> value68.family }) { null }
                })
                obj64["find"] = (fun(query: dynamic): dynamic {
                    return frozen.filter((fun(v: dynamic): dynamic {
                        return normalized(jsOr(v.label) { v.id }).includes(normalized(query))
                    }))
                })
                obj64["filter"] = (fun(fn: dynamic): dynamic {
                    return frozen.filter(fn)
                })
                obj64["check"] = (fun(v: dynamic): dynamic {
                    return vm.has(v.id)
                })
            }
            return global_Object.freeze(api)
        }
        compatible = fun(v: dynamic, p: dynamic): dynamic {
            val reasons: dynamic = js("[]")
            if (truth(jsOr(jsOr(jsOr((v.level === 0)) { jsAtLeast(v.level, 4) }) { v.supervisionRequired }) { (!truth(v.automaticEligible)) })) {
                reasons.push("No apta para asignación automática")
            }
            if (truth(jsAnd((p.experience === "principiante")) { jsMore(v.level, 2) })) {
                reasons.push("Complejidad técnica fuera del perfil")
            }
            if (truth(jsAnd((p.experience === "avanzado")) { (v.level === 1) })) {
                reasons.push("Se priorizan niveles 2 y 3")
            }
            if (truth(jsOr((v.intensity === "alta")) { jsAnd((p.intensity === "baja")) { (v.intensity !== "baja") } })) {
                reasons.push("Intensidad no compatible")
            }
            if (truth(v.equipment.some((fun(x: dynamic): dynamic {
                return (!truth(p.equipment.includes(x)))
            })))) {
                reasons.push("Equipo no declarado")
            }
            if (truth(v.restrictions.some((fun(x: dynamic): dynamic {
                return p.restrictions.includes(x)
            })))) {
                reasons.push("Restricción del perfil")
            }
            if (truth(onPresent(p.blockedVariantIds) { value71 -> value71.includes(v.id) })) {
                reasons.push("Variante excluida por el perfil")
            }
            return jsObject { obj72 ->
                obj72["ok"] = (!truth(reasons.length))
                obj72["reasons"] = reasons
            }
        }
        safeItems = fun(vs: dynamic, p: dynamic): dynamic {
            return vs.filter((fun(v: dynamic): dynamic {
                return compatible(v, p).ok
            }))
        }
        stateEmpty = fun(): dynamic {
            return jsonValue("{\"version\":1,\"pins\":{},\"outcomes\":{},\"categoryEfforts\":[],\"globalEfforts\":[],\"volumes\":[],\"phases\":{},\"initialExperienceDate\":null,\"revisionEvents\":[]}")
        }
        dataState = fun(s: dynamic): dynamic {
            return (if (truth(jsAnd(s) { (s.version === 1) })) s else stateEmpty())
        }
        recordOutcome = fun(s: dynamic, o: dynamic): dynamic {
            val `data`: dynamic = dataState(s)
            val dt: dynamic = o.date
            val id: dynamic = o.variantId
            if (truth(jsOr(jsOr(jsOr(jsOr((!truth(date(dt)))) { (jsTypeOf(id) !== "string") }) { (!truth(id)) }) { (!truth(STATES.includes(o.status))) }) { (!truth(CATS.includes(cat(o.category)))) })) {
                throw global_Error("Resultado inválido")
            }
            val key: dynamic = jsAdd(jsAdd(dt, "|"), id)
            val prev: dynamic = `data`.outcomes[key]
            if (truth(jsAnd(jsAnd(prev) { (prev.status === o.status) }) { (global_String(jsOr(prev.note) { "" }) === global_String(jsOr(o.note) { "" }).slice(0, 400)) })) {
                return jsObject { obj81 ->
                    obj81["changed"] = false
                    obj81["state"] = `data`
                }
            }
            if (truth(jsAnd(jsAnd(prev) { (prev.status !== "PENDING") }) { (prev.status !== o.status) })) {
                throw global_Error("Resultado ya evaluado: no se cambia sin corrección explícita")
            }
            `data`.outcomes[key] = jsObject { obj84 ->
                obj84["date"] = dt
                obj84["variantId"] = id
                obj84["category"] = cat(o.category)
                obj84["status"] = o.status
                obj84["note"] = global_String(jsOr(o.note) { "" }).slice(0, 400)
            }
            return jsObject { obj85 ->
                obj85["changed"] = true
                obj85["state"] = `data`
            }
        }
        correctOutcome = fun(s: dynamic, o: dynamic, reason: dynamic): dynamic {
            val `data`: dynamic = dataState(s)
            val key: dynamic = jsAdd(jsAdd(onPresent(o) { value88 -> value88.date }, "|"), onPresent(o) { value89 -> value89.variantId })
            val old: dynamic = `data`.outcomes[key]
            if (truth(jsOr(jsOr((!truth(old))) { (!truth(STATES.includes(o.status))) }) { (!truth(global_String(jsOr(reason) { "" }).trim())) })) {
                throw global_Error("Corrección explícita y motivo requeridos")
            }
            `data`.outcomes[key] = jsObject { obj92 ->
                copyFields(obj92, old)
                obj92["status"] = o.status
                obj92["note"] = global_String(jsOr(jsOr(o.note) { old.note }) { "" }).slice(0, 400)
            }
            `data`.revisionEvents.push(jsObject { obj93 ->
                obj93["date"] = o.date
                obj93["variantId"] = o.variantId
                obj93["from"] = old.status
                obj93["to"] = o.status
                obj93["action"] = "correccion_explicita"
                obj93["reason"] = global_String(reason).slice(0, 250)
            })
            return `data`
        }
        completionSummary = fun(s: dynamic, from: dynamic, to: dynamic): dynamic {
            val rows: dynamic = global_Object.values(dataState(s).outcomes).filter((fun(x: dynamic): dynamic {
                return jsAnd(jsOr((!truth(from))) { jsAtLeast(x.date, from) }) { jsOr((!truth(to))) { jsAtMost(x.date, to) } }
            }))
            val counts: dynamic = jsObject { obj96 ->
                obj96["planned"] = rows.length
                obj96["completed"] = rows.filter((fun(x: dynamic): dynamic {
                    return (x.status === "COMPLETED")
                })).length
                obj96["skipped"] = rows.filter((fun(x: dynamic): dynamic {
                    return (x.status === "SKIPPED")
                })).length
                obj96["pending"] = rows.filter((fun(x: dynamic): dynamic {
                    return (x.status === "PENDING")
                })).length
            }
            val assessed: dynamic = jsAdd(counts.completed, counts.skipped)
            return jsObject { obj97 ->
                copyFields(obj97, counts)
                obj97["assessed"] = assessed
                obj97["percent"] = (if (truth(assessed)) global_Math.round(jsDivide(jsMultiply(100, counts.completed), assessed)) else null)
                obj97["label"] = (if (truth(assessed)) "Participación evaluada" else "Sin datos suficientes")
            }
        }
        globalEffort = fun(s: dynamic, arg100: dynamic): dynamic {
            var dt: dynamic = absent
            var `value`: dynamic = absent
            val unpacked101: dynamic = arg100
            dt = unpacked101["date"]
            `value` = unpacked101["value"]
            val `data`: dynamic = dataState(s)
            if (truth(jsOr(jsOr(jsOr((!truth(date(dt)))) { (!truth(global_Number.isInteger(`value`))) }) { jsLess(`value`, 1) }) { jsMore(`value`, 10) })) {
                throw global_Error("Valoración global inválida")
            }
            val j: dynamic = `data`.globalEfforts.findIndex((fun(x: dynamic): dynamic {
                return (x.date === dt)
            }))
            val g: dynamic = jsObject { obj102 ->
                obj102["date"] = dt
                obj102["value"] = `value`
            }
            if (truth(jsAtLeast(j, 0))) {
                `data`.globalEfforts[j] = g
            } else {
                `data`.globalEfforts.push(g)
            }
            return `data`
        }
        effort = fun(s: dynamic, arg107: dynamic): dynamic {
            var dt: dynamic = absent
            var code: dynamic = absent
            var `value`: dynamic = absent
            var completed: dynamic = absent
            var global: dynamic = absent
            val unpacked108: dynamic = arg107
            dt = unpacked108["date"]
            code = unpacked108["category"]
            `value` = unpacked108["value"]
            completed = unpacked108["completed"]
            global = definedOr(unpacked108["global"]) { true }
            val `data`: dynamic = dataState(s)
            val c: dynamic = cat(code)
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(date(dt)))) { (!truth(c)) }) { (!truth(global_Number.isInteger(`value`))) }) { jsLess(`value`, 1) }) { jsMore(`value`, 10) }) { (jsTypeOf(completed) !== "boolean") })) {
                throw global_Error("Valoración inválida")
            }
            val item: dynamic = jsObject { obj109 ->
                obj109["date"] = dt
                obj109["category"] = c
                obj109["value"] = `value`
                obj109["completed"] = completed
            }
            val idx: dynamic = `data`.categoryEfforts.findIndex((fun(x: dynamic): dynamic {
                return jsAnd((x.date === dt)) { (x.category === c) }
            }))
            if (truth(jsAtLeast(idx, 0))) {
                `data`.categoryEfforts[idx] = item
            } else {
                `data`.categoryEfforts.push(item)
            }
            if (truth((global === true))) {
                globalEffort(`data`, jsObject { obj112 ->
                    obj112["date"] = dt
                    obj112["value"] = `value`
                })
            }
            return `data`
        }
        lastEffort = fun(s: dynamic, category: dynamic, today: dynamic): dynamic {
            val start: dynamic = addDays(today, (-numeric(21)))
            return jsOr(dataState(s).categoryEfforts.filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd((x.category === category)) { jsLess(x.date, today) }) { jsAtLeast(x.date, start) }) { x.completed }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return b.date.localeCompare(a.date)
            }))[0]) { null }
        }
        weekly = fun(s: dynamic, category: dynamic, today: dynamic): dynamic {
            val rows: dynamic = dataState(s).categoryEfforts.filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAnd((x.category === category)) { x.completed }) { jsLess(x.date, today) }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return a.date.localeCompare(b.date)
            }))
            if (truth((!truth(rows.length)))) {
                return jsonValue("{\"status\":\"sin_datos\",\"reason\":\"Aún no existe una valoración completada en esta categoría.\"}")
            }
            val first: dynamic = rows[0].date
            if (truth(jsLess(distance(today, first), 7))) {
                return jsonValue("{\"status\":\"espera\",\"reason\":\"Todavía no se cumple la primera revisión de siete días.\"}")
            }
            val recent: dynamic = rows.filter((fun(x: dynamic): dynamic {
                return jsAtLeast(x.date, addDays(today, (-numeric(7))))
            }))
            if (truth(recent.some((fun(x: dynamic): dynamic {
                return jsAtLeast(x["value"], 8)
            })))) {
                return jsonValue("{\"status\":\"recuperacion\",\"reason\":\"Se informó esfuerzo alto; prioridad de recuperación.\"}")
            }
            val lowDays: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(recent.filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAtLeast(x["value"], 1)) { jsAtMost(x["value"], 3) }
            })).map((fun(x: dynamic): dynamic {
                return x.date
            }))))
            if (truth(jsLess(lowDays.size, 2))) {
                return jsonValue("{\"status\":\"insuficiente\",\"reason\":\"Se necesitan dos días distintos completados y valorados con esfuerzo bajo.\"}")
            }
            return jsonValue("{\"status\":\"variedad\",\"reason\":\"Dos sesiones comparables y valoradas permiten revisar una variante equivalente, sin aumentar cantidad ni complejidad.\"}")
        }
        decision = fun(s: dynamic, category: dynamic, today: dynamic): dynamic {
            val prev: dynamic = lastEffort(s, category, today)
            val w: dynamic = weekly(s, category, today)
            if (truth((!truth(prev)))) {
                return jsObject { obj119 ->
                    obj119["action"] = "mantener"
                    obj119["max"] = global_Infinity
                    obj119["why"] = "Sin valoración anterior de esta categoría."
                    obj119["weekly"] = w
                }
            }
            if (truth(jsAtLeast(prev["value"], 9))) {
                return jsObject { obj120 ->
                    obj120["action"] = "recuperacion"
                    obj120["max"] = 1
                    obj120["why"] = "Esfuerzo 9–10/10: máximo una actividad sencilla y recuperación prioritaria."
                    obj120["weekly"] = w
                }
            }
            if (truth((prev["value"] === 8))) {
                return jsObject { obj121 ->
                    obj121["action"] = "suavizar"
                    obj121["max"] = 2
                    obj121["why"] = "Esfuerzo 8/10: máximo dos actividades y alternativas sencillas."
                    obj121["weekly"] = w
                }
            }
            if (truth(jsAtLeast(prev["value"], 4))) {
                return jsObject { obj122 ->
                    obj122["action"] = "mantener"
                    obj122["max"] = global_Infinity
                    obj122["why"] = "Esfuerzo 4–7/10: mantener propuesta."
                    obj122["weekly"] = w
                }
            }
            return jsObject { obj123 ->
                obj123["action"] = (if (truth((w.status === "variedad"))) "equivalente" else "mantener")
                obj123["max"] = global_Infinity
                obj123["why"] = (if (truth((w.status === "variedad"))) "Se permite revisar variedad del mismo tipo y nivel técnico." else "Esfuerzo bajo registrado; mantener hasta disponer de evidencia suficiente.")
                obj123["weekly"] = w
            }
        }
        phaseMix = fun(experience: dynamic, phase: dynamic): dynamic {
            if (truth((experience === "principiante"))) {
                return 0
            }
            val table: dynamic = (if (truth((experience === "avanzado"))) jsonValue("[0.33,0.55,0.75]") else jsonValue("[0.25,0.4,0.6]"))
            return table[global_Math.max(0, global_Math.min(2, jsOr(phase) { 0 }))]
        }
        phaseReview = fun(s: dynamic, category: dynamic, today: dynamic, experience: dynamic): dynamic {
            val `data`: dynamic = dataState(s)
            val base: dynamic = jsOr(`data`.initialExperienceDate) { today }
            val elapsed: dynamic = distance(today, base)
            val milestone: dynamic = jsOr(dynamicValue(jsonValue("[21,14,7]")).find((fun(n: dynamic): dynamic {
                return jsAtLeast(elapsed, n)
            }))) { 0 }
            val previous: dynamic = jsOr(`data`.phases[category]) { jsonValue("{\"phase\":0,\"lastChange\":null}") }
            val w: dynamic = weekly(`data`, category, today)
            if (truth((!truth(milestone)))) {
                return jsObject { obj128 ->
                    obj128["milestone"] = 0
                    obj128["phase"] = previous.phase
                    obj128["action"] = "esperar"
                    obj128["why"] = "Primera revisión técnica en día 7."
                }
            }
            if (truth((w.status === "recuperacion"))) {
                return jsObject { obj129 ->
                    obj129["milestone"] = milestone
                    obj129["phase"] = previous.phase
                    obj129["action"] = "recuperacion"
                    obj129["why"] = w.reason
                }
            }
            if (truth(jsOr(jsOr(jsOr((experience === "principiante")) { jsAtLeast(previous.phase, 2) }) { (w.status !== "variedad") }) { jsAnd(previous.lastChange) { jsLess(distance(today, previous.lastChange), 7) } })) {
                return jsObject { obj130 ->
                    obj130["milestone"] = milestone
                    obj130["phase"] = previous.phase
                    obj130["action"] = "mantener"
                    obj130["why"] = "No se cumplen todas las condiciones para avanzar la fase."
                }
            }
            return jsObject { obj131 ->
                obj131["milestone"] = milestone
                obj131["phase"] = previous.phase
                obj131["action"] = "revisar_fase"
                obj131["suggestedPhase"] = jsAdd(previous.phase, 1)
                obj131["why"] = "Revisión opcional de fase por categoría. No habilita niveles 4–5."
            }
        }
        approvePhase = fun(s: dynamic, category: dynamic, today: dynamic, experience: dynamic): dynamic {
            val `data`: dynamic = dataState(s)
            if (truth((!truth(CATS.includes(category))))) {
                throw global_Error("Categoría inválida")
            }
            val review: dynamic = phaseReview(`data`, category, today, experience)
            if (truth((review.action !== "revisar_fase"))) {
                throw global_Error("Falta evidencia para avanzar la fase")
            }
            val old: dynamic = jsOr(`data`.phases[category]) { jsonValue("{\"phase\":0,\"lastChange\":null}") }
            if (truth(jsOr(jsAtLeast(old.phase, 2)) { jsAnd(old.lastChange) { jsLess(distance(today, old.lastChange), 7) } })) {
                throw global_Error("Intervalo o fase no permitidos")
            }
            `data`.phases[category] = jsObject { obj136 ->
                obj136["phase"] = jsAdd(old.phase, 1)
                obj136["lastChange"] = today
            }
            `data`.revisionEvents.push(jsObject { obj137 ->
                obj137["date"] = today
                obj137["category"] = category
                obj137["action"] = "fase_aprobada"
                obj137["from"] = old.phase
                obj137["to"] = jsAdd(old.phase, 1)
            })
            return `data`
        }
        globalReview = fun(s: dynamic, today: dynamic): dynamic {
            val `data`: dynamic = dataState(s)
            val rows: dynamic = `data`.globalEfforts.filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAtMost(x.date, today)) { jsAtLeast(x.date, addDays(today, (-numeric(20)))) }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return a.date.localeCompare(b.date)
            }))
            val first: dynamic = onPresent(dynamicValue(jsArray { array141 ->
                appendAll(array141, `data`.globalEfforts)
            }).sort((fun(a: dynamic, b: dynamic): dynamic {
                return a.date.localeCompare(b.date)
            }))[0]) { value144 -> value144.date }
            if (truth((!truth(first)))) {
                return jsonValue("{\"cycleDay\":null,\"lastMilestone\":null,\"nextMilestone\":7,\"count\":0,\"average\":null,\"reason\":\"Sin valoraciones suficientes para interpretar tendencia.\"}")
            }
            val elapsed: dynamic = global_Math.max(0, distance(today, first))
            val cycleDay: dynamic = jsAdd(jsMod(elapsed, 21), 1)
            val passed: dynamic = jsOr(dynamicValue(jsonValue("[21,14,7]")).filter((fun(n: dynamic): dynamic {
                return jsAtLeast(cycleDay, n)
            })).at((-numeric(1)))) { 0 }
            val next: dynamic = jsOr(dynamicValue(jsonValue("[7,14,21]")).find((fun(n: dynamic): dynamic {
                return jsMore(n, passed)
            }))) { 21 }
            val avg: dynamic = (if (truth(rows.length)) jsDivide(global_Math.round(jsMultiply(jsDivide(rows.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b["value"])
            }), 0), rows.length), 10)), 10) else null)
            return jsObject { obj145 ->
                obj145["cycleDay"] = cycleDay
                obj145["lastMilestone"] = jsOr(passed) { null }
                obj145["nextMilestone"] = next
                obj145["count"] = rows.length
                obj145["average"] = avg
                obj145["reason"] = (if (truth(jsLess(rows.length, 2))) "Observaciones insuficientes para una tendencia." else "Tendencia descriptiva; no modifica decisiones de categorías.")
            }
        }
        volume = fun(s: dynamic, r: dynamic, catalogRef: dynamic): dynamic {
            val `data`: dynamic = dataState(s)
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(date(r.date)))) { (!truth(CATS.includes(cat(r.category)))) }) { (!truth(dynamicValue(jsonValue("[\"REPETITIONS\",\"SECONDS\"]")).includes(r.unit))) }) { (!truth(dynamicValue(jsonValue("[\"COMPLETED\",\"SKIPPED\"]")).includes(r.status))) }) { (!truth(global_Number.isInteger(r.sets))) }) { jsLess(r.sets, 0) }) { (!truth(global_Number.isFinite(r.perSet))) }) { jsLess(r.perSet, 0) }) { jsAnd((r.unit === "REPETITIONS")) { (!truth(global_Number.isInteger(r.perSet))) } }) { jsAnd(!jsEqual(r.effort, null)) { jsOr(jsOr((!truth(global_Number.isInteger(r.effort)))) { jsLess(r.effort, 1) }) { jsMore(r.effort, 10) } } })) {
                throw global_Error("Registro de volumen o unidad inválida")
            }
            val v: dynamic = onPresent(catalogRef) { value148 -> value148.byId(r.variantId) }
            if (truth(jsOr(jsOr(jsOr((!truth(v))) { (v.category !== cat(r.category)) }) { (v.type !== r.typeId) }) { (v.level !== r.level) })) {
                throw global_Error("Variante no certificada por catálogo externo")
            }
            val interrupted: dynamic = (r.interrupted === true)
            if (truth(jsAnd(jsAnd((r.status === "COMPLETED")) { (!truth(interrupted)) }) { jsOr((!truth(r.sets))) { (!truth(r.perSet)) } })) {
                throw global_Error("Faltan cantidades realizadas")
            }
            val key: dynamic = dynamicValue(arrayOf<dynamic>(r.date, r.variantId, r.unit)).join("|")
            val rec: dynamic = jsObject { obj149 ->
                obj149["date"] = r.date
                obj149["category"] = v.category
                obj149["variantId"] = v.id
                obj149["typeId"] = v.type
                obj149["level"] = v.level
                obj149["sets"] = r.sets
                obj149["perSet"] = r.perSet
                obj149["unit"] = r.unit
                obj149["effort"] = nullableOr(r.effort) { null }
                obj149["status"] = r.status
                obj149["interrupted"] = interrupted
            }
            val i: dynamic = `data`.volumes.findIndex((fun(x: dynamic): dynamic {
                return (dynamicValue(arrayOf<dynamic>(x.date, x.variantId, x.unit)).join("|") === key)
            }))
            if (truth(jsAtLeast(i, 0))) {
                `data`.volumes[i] = rec
            } else {
                `data`.volumes.push(rec)
            }
            return `data`
        }
        doseHistory = fun(s: dynamic, id: dynamic, unit: dynamic): dynamic {
            return dataState(s).volumes.filter((fun(v: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd((v.variantId === id)) { (v.unit === unit) }) { (v.status === "COMPLETED") }) { (!truth(v.interrupted)) }) { jsMore(v.sets, 0) }) { jsMore(v.perSet, 0) }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return a.date.localeCompare(b.date)
            }))
        }
        rank = fun(vs: dynamic, intent: dynamic, seed: dynamic, state: dynamic, category: dynamic, p: dynamic): dynamic {
            val h: dynamic = dataState(state)
            val scored: dynamic = vs.map((fun(v: dynamic): dynamic {
                val texts: dynamic = dynamicValue(jsArray { array159 ->
                    appendAll(array159, v.tags)
                    array159.push(v.modality)
                    array159.push(jsOr(v.label) { "" })
                }).map(normalized).join(" ")
                val affinity: dynamic = intent.recognized.filter((fun(x: dynamic): dynamic {
                    return jsOr(texts.includes(normalized(x).replace(jsRegExp("_", "g"), " "))) { texts.includes(x) }
                })).length
                val recent: dynamic = global_Object.values(h.outcomes).filter((fun(o: dynamic): dynamic {
                    return jsAnd(jsAnd((o.category === category)) { (o.variantId === v.id) }) { (o.status === "COMPLETED") }
                })).length
                return jsObject { obj160 ->
                    obj160["v"] = v
                    obj160["affinity"] = affinity
                    obj160["recent"] = recent
                    obj160["order"] = fingerprint(jsAdd(jsAdd(seed, "|"), v.id))
                }
            }))
            return scored.sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(jsOr(jsOr(jsSubtract(b.affinity, a.affinity)) { jsSubtract(a.recent, b.recent) }) { a.order.localeCompare(b.order) }) { a.v.id.localeCompare(b.v.id) }
            })).map((fun(x: dynamic): dynamic {
                return x.v
            }))
        }
        generate = fun(arg163: dynamic): dynamic {
            var ref: dynamic = absent
            var p: dynamic = absent
            var s: dynamic = absent
            var today: dynamic = absent
            var pin: dynamic = absent
            val unpacked164: dynamic = arg163
            ref = unpacked164["catalog"]
            p = unpacked164["profile"]
            s = unpacked164["state"]
            today = unpacked164["today"]
            pin = definedOr(unpacked164["pin"]) { true }
            val errors: dynamic = validProfile(p)
            if (truth((!truth(errors.ok)))) {
                return jsObject { obj165 ->
                    obj165["status"] = "perfil_invalido"
                    obj165["items"] = js("[]")
                    obj165["reason"] = errors.errors.join("; ")
                }
            }
            if (truth(jsOr(jsOr((!truth(ref))) { (jsTypeOf(ref.all) !== "function") }) { (!truth(date(today))) })) {
                return jsonValue("{\"status\":\"entrada_invalida\",\"items\":[],\"reason\":\"Fecha o catálogo externo no disponible.\"}")
            }
            val `data`: dynamic = dataState(s)
            val idx: dynamic = p.weekPlan[weekday(today)]
            val category: dynamic = (if (truth((idx === 5))) "descanso" else CATS[idx])
            val settings: dynamic = jsObject { obj166 ->
                obj166["experience"] = p.experience
                obj166["weekPlan"] = p.weekPlan
                obj166["equipment"] = p.equipment.slice().sort()
                obj166["modality"] = p.modality
                obj166["dailyLimit"] = p.dailyLimit
                obj166["intensity"] = p.intensity
                obj166["restSeconds"] = p.restSeconds
                obj166["organization"] = p.organization
                obj166["minutes"] = p.minutes
                obj166["goal"] = p.goal
                obj166["goalTextHash"] = fingerprint(p.custom_goal)
                obj166["restrictions"] = p.restrictions.slice().sort()
                obj166["blockedVariantIds"] = jsOr(p.blockedVariantIds) { js("[]") }
                obj166["catalogRevision"] = ref.revision
            }
            val signature: dynamic = fingerprint(settings)
            val old: dynamic = `data`.pins[today]
            if (truth((category === "descanso"))) {
                return jsObject { obj167 ->
                    obj167["status"] = "descanso"
                    obj167["category"] = category
                    obj167["items"] = js("[]")
                    obj167["reason"] = "Descanso programado: no se generan actividades."
                    obj167["signature"] = signature
                }
            }
            val filtered: dynamic = safeItems(ref.all().filter((fun(v: dynamic): dynamic {
                return (v.category === category)
            })), p)
            if (truth(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(pin) { old }) { (old.signature === signature) }) { (old.category === category) }) { (old.revision === ref.revision) }) { old.ids.every((fun(id: dynamic): dynamic {
                return filtered.some((fun(x: dynamic): dynamic {
                    return (x.id === id)
                }))
            })) })) {
                return jsObject { obj168 ->
                    obj168["status"] = "fijada"
                    obj168["category"] = category
                    obj168["items"] = old.ids.map((fun(id: dynamic): dynamic {
                        return ref.byId(id)
                    }))
                    obj168["reason"] = "Propuesta fijada para esta fecha local."
                    obj168["signature"] = signature
                }
            }
            val d: dynamic = decision(`data`, category, today)
            val safe: dynamic = (if (truth(jsOr((d.action === "recuperacion")) { (d.action === "suavizar") })) filtered.filter((fun(v: dynamic): dynamic {
                return jsAtMost(v.level, 2)
            })) else filtered)
            if (truth((!truth(safe.length)))) {
                return jsObject { obj169 ->
                    obj169["status"] = "sin_compatibles"
                    obj169["category"] = category
                    obj169["items"] = js("[]")
                    obj169["reason"] = "No hay actividades compatibles verificadas con tu perfil y equipo."
                    obj169["signature"] = signature
                }
            }
            val intent: dynamic = parseGoal(p.custom_goal)
            val mainGoal: dynamic = parseGoal(p.goal)
            intent.recognized = dynamicValue(jsArray { array174 ->
                appendAll(array174, jsConstruct(global_Set, arrayOf<dynamic>(jsArray { array175 ->
                    appendAll(array175, mainGoal.recognized)
                    appendAll(array175, intent.recognized)
                })))
            }).filter((fun(x: dynamic): dynamic {
                return (!truth(intent.excluded.includes(x)))
            }))
            val phase: dynamic = jsOr(onPresent(`data`.phases[category]) { value176 -> value176.phase }) { 0 }
            val ratio: dynamic = phaseMix(p.experience, phase)
            var ordered: dynamic = rank(safe, intent, jsAdd(jsAdd(today, "|"), category), `data`, category, p)
            val result: dynamic = js("[]")
            val usedTypes: dynamic = jsConstruct(global_Set, js("[]"))
            val usedFamilies: dynamic = jsConstruct(global_Set, js("[]"))
            val previous: dynamic = global_Object.values(`data`.outcomes).filter((fun(o: dynamic): dynamic {
                return jsAnd(jsAnd((o.category === category)) { jsLess(o.date, today) }) { (o.status === "COMPLETED") }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(b.date.localeCompare(a.date)) { a.variantId.localeCompare(b.variantId) }
            }))
            val recentDate: dynamic = onPresent(previous[0]) { value177 -> value177.date }
            val previousIds: dynamic = jsArray { array178 ->
                appendAll(array178, jsConstruct(global_Set, arrayOf<dynamic>(previous.filter((fun(o: dynamic): dynamic {
                    return (o.date === recentDate)
                })).map((fun(o: dynamic): dynamic {
                    return o.variantId
                })))))
            }
            val preferred: dynamic = js("[]")
            if (truth((d.action === "mantener"))) {
                if (true) {
                    val iterator179: dynamic = iteratorOf(previousIds)
                    while (true) {
                        val next180: dynamic = iterator179.next()
                        if (truth(next180.done)) break
                        var id: dynamic = absent
                        id = next180.value
                        val v: dynamic = safe.find((fun(x: dynamic): dynamic {
                            return (x.id === id)
                        }))
                        if (truth(v)) {
                            preferred.push(v)
                        }
                    }
                }
            }
            if (truth((d.action === "equivalente"))) {
                if (true) {
                    val iterator181: dynamic = iteratorOf(previousIds)
                    while (true) {
                        val next182: dynamic = iterator181.next()
                        if (truth(next182.done)) break
                        var id: dynamic = absent
                        id = next182.value
                        val old: dynamic = ref.byId(id)
                        if (truth((!truth(old)))) {
                            continue
                        }
                        val equivalent: dynamic = safe.find((fun(v: dynamic): dynamic {
                            return jsAnd(jsAnd((v.id !== id)) { (v.type === old.type) }) { (v.level === old.level) }
                        }))
                        val choice: dynamic = jsOr(equivalent) { safe.find((fun(v: dynamic): dynamic {
                            return (v.id === id)
                        })) }
                        if (truth(jsAnd(choice) { (!truth(preferred.some((fun(x: dynamic): dynamic {
                            return (x.id === choice.id)
                        })))) })) {
                            preferred.push(choice)
                        }
                    }
                }
            }
            if (truth(preferred.length)) {
                ordered = jsArray { array183 ->
                    appendAll(array183, preferred)
                    appendAll(array183, ordered.filter((fun(v: dynamic): dynamic {
                        return (!truth(preferred.some((fun(x: dynamic): dynamic {
                            return (x.id === v.id)
                        }))))
                    })))
                }
            }
            val max: dynamic = global_Math.min(p.dailyLimit, d.max, ordered.length)
            if (true) {
                var j: dynamic = absent
                j = 0
                var firstIteration184 = true
                while (true) {
                    if (!firstIteration184) {
                        run {
                            val previous185: dynamic = numeric(j)
                            j = jsAdd(previous185, 1)
                        }
                    }
                    firstIteration184 = false
                    if (!truth(jsLess(j, max))) break
                    val desired3: dynamic = jsAnd((p.experience !== "principiante")) { jsLess(result.filter((fun(v: dynamic): dynamic {
                        return (v.level === 3)
                    })).length, global_Math.floor(jsAdd(jsMultiply(jsAdd(j, 1), ratio), 1e-8))) }
                    val options: dynamic = ordered.filter((fun(v: dynamic): dynamic {
                        return (!truth(result.some((fun(r: dynamic): dynamic {
                            return (r.id === v.id)
                        }))))
                    }))
                    if (truth((!truth(options.length)))) {
                        break
                    }
                    options.sort((fun(a: dynamic, b: dynamic): dynamic {
                        val score: dynamic = (fun(v: dynamic): dynamic {
                            return jsAdd(jsAdd(jsAdd((if (truth(jsAnd((v.level === 3)) { desired3 })) 10 else 0), (if (truth(jsAnd((v.level !== 3)) { (!truth(desired3)) })) 5 else 0)), (if (truth(usedFamilies.has(v.family))) 0 else 3)), (if (truth(usedTypes.has(v.type))) 0 else 2))
                        })
                        return jsOr(jsOr(jsSubtract((if (truth(preferred.includes(b))) 1 else 0), (if (truth(preferred.includes(a))) 1 else 0))) { jsSubtract(score(b), score(a)) }) { jsSubtract(ordered.indexOf(a), ordered.indexOf(b)) }
                    }))
                    val next: dynamic = options[0]
                    result.push(next)
                    usedTypes.add(next.type)
                    usedFamilies.add(next.family)
                }
            }
            if (truth(pin)) {
                `data`.pins[today] = jsObject { obj188 ->
                    obj188["signature"] = signature
                    obj188["revision"] = ref.revision
                    obj188["category"] = category
                    obj188["ids"] = result.map((fun(v: dynamic): dynamic {
                        return v.id
                    }))
                }
            }
            return jsObject { obj189 ->
                obj189["status"] = (if (truth(result.length)) "generada" else "sin_compatibles")
                obj189["category"] = category
                obj189["items"] = result
                obj189["reason"] = d.why
                obj189["signature"] = signature
                obj189["goalRecognized"] = intent.recognized
                obj189["goalExcluded"] = intent.excluded
                obj189["adaptation"] = d
            }
        }
        CATS = global_Object.freeze(jsonValue("[\"tren_superior\",\"tren_central\",\"tren_inferior\",\"movilidad\",\"cardio\"]"))
        val LABELS: dynamic = global_Object.freeze(jsonValue("[\"Tren superior\",\"Tren central\",\"Tren inferior\",\"Movilidad\",\"Cardio\",\"Descanso\"]"))
        ALIASES = global_Object.freeze(jsonValue("{\"tronco\":\"tren_central\",\"integrado\":\"tren_central\",\"piernas\":\"tren_inferior\",\"coordinacion\":\"movilidad\"}"))
        STATES = global_Object.freeze(jsonValue("[\"PENDING\",\"COMPLETED\",\"SKIPPED\"]"))
        val STOPWORDS: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"de\",\"del\",\"para\",\"la\",\"el\",\"en\",\"por\",\"una\",\"un\",\"me\",\"mi\",\"que\",\"quiero\",\"hacer\",\"a\",\"mas\",\"mejorar\",\"poder\"]")))
        INTENTS = global_Object.freeze(jsonValue("{\"caminar\":[\"caminar\",\"caminata\",\"andar\",\"pasear\"],\"bicicleta\":[\"bicicleta\",\"ciclismo\",\"pedalear\",\"bici\"],\"correr\":[\"correr\",\"carrera\",\"trotar\"],\"danza\":[\"danza\",\"bailar\",\"baile\"],\"equilibrio\":[\"equilibrio\",\"balance\"],\"movilidad\":[\"movilidad\",\"moverme\"],\"flexibilidad\":[\"flexibilidad\",\"estiramiento\",\"estirar\"],\"postura\":[\"postura\",\"postural\"],\"fuerza_funcional\":[\"fuerza funcional\",\"fuerza\"],\"coordinacion\":[\"coordinacion\",\"coordinado\"],\"resistencia\":[\"resistencia\",\"aguante\"]}"))
        ISO = jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")
        val clone: dynamic = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        normalized = (fun(s: dynamic): dynamic {
            return global_String(jsOr(s) { "" }).toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "").replace(jsRegExp("[^a-z0-9_\\s]", "g"), " ").replace(jsRegExp("\\s+", "g"), " ").trim()
        })
        val dayIndex: dynamic = (fun(code: dynamic): dynamic {
            return (if (truth((code === "descanso"))) 5 else CATS.indexOf(code))
        })
        return global_Object.freeze(jsObject { obj190 ->
            obj190["version"] = "38.72"
            obj190["categories"] = CATS
            obj190["labels"] = LABELS
            obj190["aliases"] = ALIASES
            obj190["states"] = STATES
            obj190["normalized"] = normalized
            obj190["category"] = cat
            obj190["addDays"] = addDays
            obj190["distance"] = distance
            obj190["weekday"] = weekday
            obj190["parseGoal"] = parseGoal
            obj190["validProfile"] = validProfile
            obj190["defaultProfile"] = defaultProfile
            obj190["catalog"] = catalog
            obj190["compatible"] = compatible
            obj190["stateEmpty"] = stateEmpty
            obj190["dataState"] = dataState
            obj190["recordOutcome"] = recordOutcome
            obj190["correctOutcome"] = correctOutcome
            obj190["completionSummary"] = completionSummary
            obj190["effort"] = effort
            obj190["globalEffort"] = globalEffort
            obj190["lastEffort"] = lastEffort
            obj190["weekly"] = weekly
            obj190["decision"] = decision
            obj190["phaseMix"] = phaseMix
            obj190["phaseReview"] = phaseReview
            obj190["approvePhase"] = approvePhase
            obj190["globalReview"] = globalReview
            obj190["volume"] = volume
            obj190["doseHistory"] = doseHistory
            obj190["generate"] = generate
            obj190["fingerprint"] = fingerprint
        })
    }))
}
