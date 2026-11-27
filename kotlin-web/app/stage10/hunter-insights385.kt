@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterinsights385

import hunter.browser.*

@JsName("Number") private external var global_Number: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installHunterInsights385() {
    dynamicValue((fun(root: dynamic): dynamic {
        var dateIsValid: dynamic = absent
        var number: dynamic = absent
        var engine: dynamic = absent
        var groupOf: dynamic = absent
        var history: dynamic = absent
        var completedSets: dynamic = absent
        var groupVolume: dynamic = absent
        var personalBest: dynamic = absent
        var repeatedMovements: dynamic = absent
        var catalogCoverage: dynamic = absent
        groupOf = fun(id: dynamic): dynamic {
            val e: dynamic = onPresent(engine()) { value5 -> run { val owner7: dynamic = value5; onPresent(owner7.byId) { method6 -> invokeWith(method6, owner7, arrayOf<dynamic>(id)) } } }
            return (if (truth(jsAnd(onPresent(e) { value8 -> value8.group }) { (jsTypeOf(e.group) === "string") })) e.group else "Sin clasificar")
        }
        history = fun(logs: dynamic, arg11: dynamic): dynamic {
            var limit: dynamic = absent
            limit = definedOr(arg11) { 90 }
            val entries: dynamic = global_Object.entries((if (truth(jsAnd(logs) { (jsTypeOf(logs) === "object") })) logs else js("({})")))
            return entries.filter((fun(arg18: dynamic): dynamic {
                var day: dynamic = absent
                var log: dynamic = absent
                val unpacked19: dynamic = fromIterable(arg18)
                day = unpacked19[0]
                log = unpacked19[1]
                return jsAnd(jsAnd(dateIsValid(day)) { log }) { global_Array.isArray(log.items) }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return b[0].localeCompare(a[0])
            })).slice(0, global_Math.max(1, global_Math.min(365, global_Math.floor(jsOr(number(limit)) { 90 }))))
        }
        completedSets = fun(logs: dynamic, arg22: dynamic): dynamic {
            var limit: dynamic = absent
            limit = definedOr(arg22) { 90 }
            val sets: dynamic = js("[]")
            if (true) {
                val iterator23: dynamic = iteratorOf(history(logs, limit))
                while (true) {
                    val next24: dynamic = iterator23.next()
                    if (truth(next24.done)) break
                    var date: dynamic = absent
                    var log: dynamic = absent
                    val unpacked25: dynamic = fromIterable(next24.value)
                    date = unpacked25[0]
                    log = unpacked25[1]
                    if (true) {
                        val iterator26: dynamic = iteratorOf(log.items)
                        while (true) {
                            val next27: dynamic = iterator26.next()
                            if (truth(next27.done)) break
                            var item: dynamic = absent
                            item = next27.value
                            if (truth(jsOr(jsOr((!truth(item))) { (!truth(item.id)) }) { (!truth(global_Array.isArray(item["actual"]))) })) {
                                continue
                            }
                            if (true) {
                                val iterator28: dynamic = iteratorOf(item["actual"])
                                while (true) {
                                    val next29: dynamic = iterator28.next()
                                    if (truth(next29.done)) break
                                    var record: dynamic = absent
                                    record = next29.value
                                    if (truth((!truth(onPresent(record) { value30 -> value30.done })))) {
                                        continue
                                    }
                                    sets.push(jsObject { obj31 ->
                                        obj31["date"] = date
                                        obj31["id"] = global_String(item.id)
                                        obj31["group"] = groupOf(item.id)
                                        obj31["value"] = number(record["value"])
                                        obj31["kg"] = number(record.kg)
                                    })
                                }
                            }
                        }
                    }
                }
            }
            return sets
        }
        groupVolume = fun(logs: dynamic, arg34: dynamic): dynamic {
            var limit: dynamic = absent
            limit = definedOr(arg34) { 90 }
            val `out`: dynamic = js("({})")
            if (true) {
                val iterator35: dynamic = iteratorOf(completedSets(logs, limit))
                while (true) {
                    val next36: dynamic = iterator35.next()
                    if (truth(next36.done)) break
                    var `set`: dynamic = absent
                    `set` = next36.value
                    val group: dynamic = `set`.group
                    run {
                        val target37: dynamic = `out`
                        val key38: dynamic = group
                        target37[key38] = jsOr(target37[key38]) { jsonValue("{\"series\":0,\"repeticionesOSegundos\":0,\"cargaKgRegistrada\":0}") }
                    }
                    run {
                        val target39: dynamic = `out`[group]
                        val previous41: dynamic = numeric(target39.series)
                        target39.series = jsAdd(previous41, 1)
                    }
                    run {
                        val target42: dynamic = `out`[group]
                        target42.repeticionesOSegundos = jsAdd(target42.repeticionesOSegundos, `set`["value"])
                    }
                    run {
                        val target44: dynamic = `out`[group]
                        target44.cargaKgRegistrada = jsAdd(target44.cargaKgRegistrada, `set`.kg)
                    }
                }
            }
            return `out`
        }
        personalBest = fun(logs: dynamic, arg48: dynamic): dynamic {
            var limit: dynamic = absent
            limit = definedOr(arg48) { 365 }
            val `out`: dynamic = js("({})")
            if (true) {
                val iterator49: dynamic = iteratorOf(completedSets(logs, limit))
                while (true) {
                    val next50: dynamic = iterator49.next()
                    if (truth(next50.done)) break
                    var `set`: dynamic = absent
                    `set` = next50.value
                    val best: dynamic = `out`[`set`.id]
                    if (truth((!truth(best)))) {
                        `out`[`set`.id] = jsObject { obj53 ->
                            obj53["id"] = `set`.id
                            obj53["date"] = `set`.date
                            obj53["maxValue"] = `set`["value"]
                            obj53["maxKg"] = `set`.kg
                        }
                        continue
                    }
                    if (truth(jsMore(`set`["value"], best.maxValue))) {
                        best.maxValue = `set`["value"]
                        best.date = `set`.date
                    }
                    if (truth(jsMore(`set`.kg, best.maxKg))) {
                        best.maxKg = `set`.kg
                    }
                }
            }
            return `out`
        }
        repeatedMovements = fun(days: dynamic): dynamic {
            val histogram: dynamic = js("({})")
            if (true) {
                val iterator62: dynamic = iteratorOf((if (truth(global_Array.isArray(days))) days else js("[]")))
                while (true) {
                    val next63: dynamic = iterator62.next()
                    if (truth(next63.done)) break
                    var day: dynamic = absent
                    day = next63.value
                    if (true) {
                        val iterator64: dynamic = iteratorOf(jsOr(onPresent(day) { value66 -> value66.items }) { js("[]") })
                        while (true) {
                            val next65: dynamic = iterator64.next()
                            if (truth(next65.done)) break
                            var item: dynamic = absent
                            item = next65.value
                            if (truth((!truth(onPresent(item) { value67 -> value67.id })))) {
                                continue
                            }
                            histogram[item.id] = jsAdd(jsOr(histogram[item.id]) { 0 }, 1)
                        }
                    }
                }
            }
            return global_Object.entries(histogram).filter((fun(arg79: dynamic): dynamic {
                var count: dynamic = absent
                val unpacked80: dynamic = fromIterable(arg79)
                count = unpacked80[1]
                return jsAtLeast(count, 3)
            })).map((fun(arg81: dynamic): dynamic {
                var id: dynamic = absent
                var count: dynamic = absent
                val unpacked82: dynamic = fromIterable(arg81)
                id = unpacked82[0]
                count = unpacked82[1]
                return jsObject { obj83 ->
                    obj83["id"] = id
                    obj83["count"] = count
                }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsOr(jsSubtract(b.count, a.count)) { a.id.localeCompare(b.id) }
            }))
        }
        catalogCoverage = fun(): dynamic {
            val exercises: dynamic = jsOr(onPresent(engine()) { value86 -> value86.exercises }) { js("[]") }
            var groups: dynamic = 0
            var zones: dynamic = 0
            var equipment: dynamic = 0
            var annotated: dynamic = 0
            if (true) {
                val iterator87: dynamic = iteratorOf(exercises)
                while (true) {
                    val next88: dynamic = iterator87.next()
                    if (truth(next88.done)) break
                    var e: dynamic = absent
                    e = next88.value
                    if (truth(onPresent(e) { value89 -> value89.group })) {
                        run {
                            val previous90: dynamic = numeric(groups)
                            groups = jsAdd(previous90, 1)
                        }
                    }
                    if (truth(jsAnd(global_Array.isArray(onPresent(e) { value91 -> value91.zones })) { e.zones.length })) {
                        run {
                            val previous92: dynamic = numeric(zones)
                            zones = jsAdd(previous92, 1)
                        }
                    }
                    if (truth(onPresent(e) { value93 -> value93.equipment })) {
                        run {
                            val previous94: dynamic = numeric(equipment)
                            equipment = jsAdd(previous94, 1)
                        }
                    }
                    if (truth(jsAnd(jsAnd(jsAnd(jsAnd(onPresent(e) { value95 -> value95.id }) { e.group }) { e.equipment }) { global_Array.isArray(e.zones) }) { e.zones.length })) {
                        run {
                            val previous96: dynamic = numeric(annotated)
                            annotated = jsAdd(previous96, 1)
                        }
                    }
                }
            }
            return jsObject { obj97 ->
                obj97["catalogItems"] = exercises.length
                obj97["groupTags"] = groups
                obj97["zoneTags"] = zones
                obj97["equipmentTags"] = equipment
                obj97["completeForScreening"] = annotated
                obj97["note"] = "La presencia de etiquetas no demuestra seguridad biomecánica ni equivalencia entre demostración y ejercicio."
            }
        }
        if (truth(root.HunterInsights385)) {
            return absent
        }
        dateIsValid = (fun(`value`: dynamic): dynamic {
            return dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(`value`)
        })
        number = (fun(x: dynamic): dynamic {
            return (if (truth(jsAnd(global_Number.isFinite(global_Number(x))) { jsAtLeast(global_Number(x), 0) })) global_Number(x) else 0)
        })
        engine = (fun(): dynamic {
            return root.Engine
        })
        root.HunterInsights385 = global_Object.freeze(jsObject { obj100 ->
            obj100["version"] = "38.5"
            obj100["groupVolume"] = groupVolume
            obj100["personalBest"] = personalBest
            obj100["repeatedMovements"] = repeatedMovements
            obj100["catalogCoverage"] = catalogCoverage
        })
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
