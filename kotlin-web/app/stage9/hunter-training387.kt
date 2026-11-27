@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntertraining387

import hunter.browser.*

@JsName("Object") private external var global_Object: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterTraining387() {
    dynamicValue((fun(root: dynamic): dynamic {
        var knownPatterns: dynamic = absent
        var hasOwn: dynamic = absent
        var isoDay: dynamic = absent
        var number: dynamic = absent
        var dateOffset: dynamic = absent
        var pattern: dynamic = absent
        var load: dynamic = absent
        var score: dynamic = absent
        var dose: dynamic = absent
        var catalog: dynamic = absent
        pattern = fun(e: dynamic): dynamic {
            val explicit: dynamic = onPresent(root.HunterGroups49) { value7 -> onPresent(value7.classify(e)) { value8 -> value8.pattern } }
            if (truth(explicit)) {
                return explicit
            }
            if (truth((!truth(onPresent(e) { value9 -> value9.id })))) {
                return null
            }
            if (truth(jsAnd((jsTypeOf(e.pattern) === "string")) { e.pattern.length })) {
                return e.pattern
            }
            val candidates: dynamic = dynamicValue(arrayOf<dynamic>(e.baseId, e.family, e.id)).filter((fun(x: dynamic): dynamic {
                return (jsTypeOf(x) === "string")
            }))
            if (true) {
                val iterator10: dynamic = iteratorOf(candidates)
                while (true) {
                    val next11: dynamic = iterator10.next()
                    if (truth(next11.done)) break
                    var raw: dynamic = absent
                    raw = next11.value
                    val id: dynamic = raw.replace(jsRegExp("(?:_slow|_pause|_short|_easy|-tempo23|-pause23|-control18)\$", ""), "")
                    if (truth(hasOwn(knownPatterns, id))) {
                        return knownPatterns[id]
                    }
                }
            }
            return null
        }
        load = fun(logs: dynamic, date: dynamic, arg14: dynamic): dynamic {
            var days: dynamic = absent
            days = definedOr(arg14) { 7 }
            val groups: dynamic = global_Object.create(null)
            val patterns: dynamic = global_Object.create(null)
            val ids: dynamic = global_Object.create(null)
            val engine: dynamic = root.Engine
            val min: dynamic = dateOffset(date, (-numeric(global_Math.max(1, global_Math.min(21, days)))))
            var sessions: dynamic = 0
            var finishedSets: dynamic = 0
            if (true) {
                val iterator15: dynamic = iteratorOf(global_Object.entries(jsOr(logs) { js("({})") }))
                while (true) {
                    val next16: dynamic = iterator15.next()
                    if (truth(next16.done)) break
                    var key: dynamic = absent
                    var l: dynamic = absent
                    val unpacked17: dynamic = fromIterable(next16.value)
                    key = unpacked17[0]
                    l = unpacked17[1]
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(isoDay(key)))) { jsAtLeast(key, date) }) { jsLess(key, min) }) { (!truth(l)) }) { l.pain }) { (!truth(global_Array.isArray(l.items))) })) {
                        continue
                    }
                    var counted: dynamic = false
                    if (true) {
                        val iterator18: dynamic = iteratorOf(l.items)
                        while (true) {
                            val next19: dynamic = iterator18.next()
                            if (truth(next19.done)) break
                            var item: dynamic = absent
                            item = next19.value
                            if (truth(jsOr((!truth(onPresent(item) { value20 -> value20.id }))) { (!truth(global_Array.isArray(item["actual"]))) })) {
                                continue
                            }
                            val completed: dynamic = item["actual"].filter((fun(s: dynamic): dynamic {
                                return (onPresent(s) { value21 -> value21.done } === true)
                            })).length
                            if (truth((!truth(completed)))) {
                                continue
                            }
                            val e: dynamic = onPresent(engine) { value22 -> run { val owner24: dynamic = value22; onPresent(owner24.byId) { method23 -> invokeWith(method23, owner24, arrayOf<dynamic>(item.id)) } } }
                            if (truth((!truth(e)))) {
                                continue
                            }
                            if (truth(e.group)) {
                                groups[e.group] = jsAdd(jsOr(groups[e.group]) { 0 }, completed)
                            }
                            val p: dynamic = pattern(e)
                            if (truth(p)) {
                                patterns[p] = jsAdd(jsOr(patterns[p]) { 0 }, completed)
                            }
                            val base: dynamic = jsOr(jsOr(e.baseId) { e.family }) { e.id }
                            ids[base] = jsAdd(jsOr(ids[base]) { 0 }, completed)
                            run {
                                finishedSets = jsAdd(finishedSets, completed)
                            }
                            counted = true
                        }
                    }
                    if (truth(counted)) {
                        run {
                            val previous31: dynamic = numeric(sessions)
                            sessions = jsAdd(previous31, 1)
                        }
                    }
                }
            }
            return jsObject { obj32 ->
                obj32["groups"] = groups
                obj32["patterns"] = patterns
                obj32["ids"] = ids
                obj32["sessions"] = sessions
                obj32["finishedSets"] = finishedSets
            }
        }
        score = fun(e: dynamic, arg35: dynamic): dynamic {
            var context: dynamic = absent
            context = definedOr(arg35) { js("({})") }
            val weekly: dynamic = jsOr(context.weekly) { jsonValue("{\"groups\":{},\"patterns\":{},\"ids\":{}}") }
            val counts: dynamic = jsOr(context.dayCounts) { js("({})") }
            val patternCounts: dynamic = jsOr(context.dayPatterns) { js("({})") }
            val group: dynamic = jsOr(onPresent(e) { value36 -> value36.group }) { "Sin clasificar" }
            val p: dynamic = pattern(e)
            val base: dynamic = jsOr(jsOr(onPresent(e) { value37 -> value37.baseId }) { onPresent(e) { value38 -> value38.family } }) { onPresent(e) { value39 -> value39.id } }
            val groupSets: dynamic = number(onPresent(weekly.groups) { value40 -> value40[group] }, 0, 100)
            val patternSets: dynamic = (if (truth(p)) number(onPresent(weekly.patterns) { value41 -> value41[p] }, 0, 100) else 0)
            val recent: dynamic = number(onPresent(weekly.ids) { value42 -> value42[base] }, 0, 30)
            return jsSubtract(jsSubtract(jsSubtract(jsSubtract((-numeric(global_Math.min(30, jsMultiply(groupSets, 1.7)))), global_Math.min(15, jsMultiply(patternSets, 1.2))), global_Math.min(18, jsMultiply(recent, 5))), global_Math.min(14, jsMultiply(number(counts[group], 0, 20), 7))), (if (truth(p)) global_Math.min(13, jsMultiply(number(patternCounts[p], 0, 20), 7)) else 0))
        }
        dose = fun(e: dynamic, profile: dynamic, kind: dynamic, status: dynamic, feedback: dynamic, date: dynamic, original: dynamic, weekly: dynamic): dynamic {
            val `out`: dynamic = jsObject { obj45 ->
                copyFields(obj45, original)
            }
            val group: dynamic = jsOr(onPresent(e) { value46 -> value46.group }) { "" }
            val strained: dynamic = global_Boolean(onPresent(feedback) { value47 -> value47.strain })
            val reduced: dynamic = global_Boolean(onPresent(status) { value48 -> value48.reduced })
            if (truth(jsOr(jsOr(jsOr(reduced) { strained }) { onPresent(profile) { value49 -> onPresent(value49.health) { value50 -> value50.special } } }) { onPresent(profile) { value51 -> onPresent(value51.health) { value52 -> value52.acute } } })) {
                return `out`
            }
            val g: dynamic = global_String(jsOr(onPresent(profile) { value53 -> value53.goal }) { "general" })
            if (truth((kind === "strength"))) {
                if (truth(jsAnd(jsAnd(jsAnd(jsAnd((g === "muscle")) { jsAtLeast(global_Number(onPresent(profile) { value54 -> value54.minutes }), 30) }) { onPresent(feedback) { value55 -> value55.canProgress } }) { jsLess(`out`.sets, 3) }) { jsLess(number(onPresent(weekly) { value56 -> onPresent(value56.groups) { value57 -> value57[group] } }, 0, 100), 10) })) {
                    `out`.sets = global_Math.min(3, jsAdd(`out`.sets, 1))
                }
                if (truth(jsAtLeast(number(onPresent(weekly) { value60 -> onPresent(value60.groups) { value61 -> value61[group] } }, 0, 100), 12))) {
                    `out`.sets = global_Math.min(`out`.sets, 2)
                }
                if (truth((g === "strength"))) {
                    `out`.rest = global_Math.max(jsOr(global_Number(`out`.rest)) { 90 }, 100)
                }
            } else {
                if (truth(jsAnd(jsAnd((kind === "cardio")) { (g === "endurance") }) { onPresent(feedback) { value66 -> value66.canProgress } })) {
                    `out`.target = global_Math.min((if (truth((e.unit === "sec"))) 60 else 14), jsAdd(`out`.target, (if (truth((e.unit === "sec"))) 5 else 1)))
                }
            }
            if (truth(jsOr((!truth(global_Number.isFinite(`out`.target)))) { jsAtMost(`out`.target, 0) })) {
                `out`.target = original.target
            }
            return `out`
        }
        catalog = fun(): dynamic {
            val ex: dynamic = jsOr(onPresent(root.Engine) { value73 -> value73.exercises }) { js("[]") }
            var group: dynamic = 0
            var patterns: dynamic = 0
            var zones: dynamic = 0
            var equipment: dynamic = 0
            var models: dynamic = 0
            if (true) {
                val iterator74: dynamic = iteratorOf(ex)
                while (true) {
                    val next75: dynamic = iterator74.next()
                    if (truth(next75.done)) break
                    var e: dynamic = absent
                    e = next75.value
                    if (truth(onPresent(e) { value76 -> value76.group })) {
                        run {
                            val previous77: dynamic = numeric(group)
                            group = jsAdd(previous77, 1)
                        }
                    }
                    if (truth(pattern(e))) {
                        run {
                            val previous78: dynamic = numeric(patterns)
                            patterns = jsAdd(previous78, 1)
                        }
                    }
                    if (truth(jsAnd(global_Array.isArray(onPresent(e) { value79 -> value79.zones })) { e.zones.length })) {
                        run {
                            val previous80: dynamic = numeric(zones)
                            zones = jsAdd(previous80, 1)
                        }
                    }
                    if (truth(onPresent(e) { value81 -> value81.equipment })) {
                        run {
                            val previous82: dynamic = numeric(equipment)
                            equipment = jsAdd(previous82, 1)
                        }
                    }
                    if (truth(jsAnd(onPresent(e) { value83 -> value83.modelPath30 }) { onPresent(e) { value84 -> value84.demoPath30 } })) {
                        run {
                            val previous85: dynamic = numeric(models)
                            models = jsAdd(previous85, 1)
                        }
                    }
                }
            }
            return jsObject { obj86 ->
                obj86["total"] = ex.length
                obj86["group"] = group
                obj86["patterns"] = patterns
                obj86["zones"] = zones
                obj86["equipment"] = equipment
                obj86["models"] = models
                obj86["notice"] = "Los patrones ayudan a diversificar sesiones; no prueban seguridad clínica."
            }
        }
        if (truth(root.HunterTraining387)) {
            return absent
        }
        knownPatterns = global_Object.freeze(jsonValue("{\"chair\":\"rodilla\",\"squat\":\"rodilla\",\"goblet\":\"rodilla\",\"sumo\":\"rodilla\",\"dbsumo\":\"rodilla\",\"reverselunge\":\"rodilla\",\"dblunge\":\"rodilla\",\"split\":\"rodilla\",\"lateralunge\":\"rodilla\",\"stepup\":\"rodilla\",\"wallsit\":\"rodilla\",\"wall\":\"empuje_horizontal\",\"incline\":\"empuje_horizontal\",\"pushup\":\"empuje_horizontal\",\"kneepush\":\"empuje_horizontal\",\"press\":\"empuje_horizontal\",\"neutral\":\"empuje_horizontal\",\"benchpress\":\"empuje_horizontal\",\"closepush\":\"empuje_horizontal\",\"bridge\":\"extension_cadera\",\"dbbridge\":\"extension_cadera\",\"singlebridge\":\"extension_cadera\",\"hinge\":\"bisagra_cadera\",\"rdl\":\"bisagra_cadera\",\"singlehinge\":\"bisagra_cadera\",\"sumodead\":\"bisagra_cadera\",\"row\":\"traccion_horizontal\",\"bentrow\":\"traccion_horizontal\",\"towelrow\":\"traccion_horizontal\",\"prone\":\"escapular\",\"reverse\":\"escapular\",\"scapular\":\"escapular\",\"reversefly\":\"escapular\",\"curl\":\"flexion_codo\",\"hammer\":\"flexion_codo\",\"seatedcurl\":\"flexion_codo\",\"tricep\":\"extension_codo\",\"lyingtricep\":\"extension_codo\",\"lateral\":\"elevacion_hombro\",\"seatedlateral\":\"elevacion_hombro\",\"overheadpress\":\"empuje_vertical\",\"seatedpress\":\"empuje_vertical\",\"calf\":\"elevacion_talon\",\"dbcalf\":\"elevacion_talon\",\"seatedcalf\":\"elevacion_talon\",\"deadbug\":\"estabilizacion_tronco\",\"bird\":\"estabilizacion_tronco\",\"plank\":\"estabilizacion_tronco\",\"sideplank\":\"estabilizacion_tronco\",\"heel\":\"estabilizacion_tronco\"}"))
        hasOwn = (fun(o: dynamic, k: dynamic): dynamic {
            return global_Object.prototype.hasOwnProperty.call(o, k)
        })
        isoDay = (fun(x: dynamic): dynamic {
            return dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(x)
        })
        number = (fun(v: dynamic, arg3: dynamic, arg4: dynamic): dynamic {
            var min: dynamic = absent
            min = definedOr(arg3) { 0 }
            var max: dynamic = absent
            max = definedOr(arg4) { 200 }
            val n: dynamic = global_Number(v)
            return (if (truth(global_Number.isFinite(n))) global_Math.min(max, global_Math.max(min, n)) else min)
        })
        dateOffset = (fun(date: dynamic, days: dynamic): dynamic {
            val d: dynamic = jsConstruct(global_Date, arrayOf<dynamic>(jsAdd(date, "T12:00:00Z")))
            d.setUTCDate(jsAdd(d.getUTCDate(), days))
            return d.toISOString().slice(0, 10)
        })
        root.HunterTraining387 = global_Object.freeze(jsObject { obj89 ->
            obj89["version"] = "38.7"
            obj89["pattern"] = pattern
            obj89["load"] = load
            obj89["score"] = score
            obj89["dose"] = dose
            obj89["catalog"] = catalog
        })
        return absent
    }))((if (truth((js("typeof window") === "undefined"))) global_globalThis else global_window))
}
