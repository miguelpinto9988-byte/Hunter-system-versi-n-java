@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installasterionunified3873

import hunter.browser.*

@JsName("module") private external var global_module: dynamic
@JsName("require") private external var global_require: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Array") private external var global_Array: dynamic

internal fun installAsterionUnified3873() {
    dynamicValue((fun(factory: dynamic): dynamic {
        val R: dynamic = (if (truth(jsAnd((js("typeof module") === "object")) { global_module.exports })) global_require("./asterion-rules3872.js") else (if (truth((js("typeof window") !== "undefined"))) global_window.AsterionRules3872 else null))
        val api: dynamic = factory(R)
        if (truth(jsAnd((js("typeof module") === "object")) { global_module.exports })) {
            global_module.exports = api
        }
        if (truth((js("typeof window") !== "undefined"))) {
            global_window.AsterionUnified3873 = api
        }
        return absent
    }))((fun(R: dynamic): dynamic {
        var SCALE: dynamic = absent
        var safeDate: dynamic = absent
        var validRating: dynamic = absent
        var clean: dynamic = absent
        var reference: dynamic = absent
        var completedCategories: dynamic = absent
        var recordSession: dynamic = absent
        var recentCategory: dynamic = absent
        var volumeReference: dynamic = absent
        var cycleDay: dynamic = absent
        var milestone: dynamic = absent
        var higherCandidates: dynamic = absent
        var generate: dynamic = absent
        reference = fun(rating: dynamic): dynamic {
            return (if (truth(validRating(rating))) SCALE[rating] else null)
        }
        completedCategories = fun(log: dynamic, categoryForId: dynamic): dynamic {
            val list: dynamic = dynamicValue(jsOr(onPresent(log) { value14 -> value14.items }) { js("[]") }).filter((fun(item: dynamic): dynamic {
                return onPresent(item) { value15 -> onPresent(value15["actual"]) { value16 -> run { val owner18: dynamic = value16; onPresent(owner18.some) { method17 -> invokeWith(method17, owner18, arrayOf<dynamic>((fun(`set`: dynamic): dynamic {
                    return jsAnd((onPresent(`set`) { value19 -> value19.done } === true)) { jsMore(global_Number(`set`["value"]), 0) }
                }))) } } } }
            }))
            return dynamicValue(jsArray { array21 ->
                appendAll(array21, jsConstruct(global_Set, arrayOf<dynamic>(list.map((fun(item: dynamic): dynamic {
                    return categoryForId(item.id)
                })).filter((fun(c: dynamic): dynamic {
                    return R.categories.includes(c)
                })))))
            }).sort()
        }
        recordSession = fun(`data`: dynamic, arg24: dynamic): dynamic {
            var date: dynamic = absent
            var log: dynamic = absent
            var categoryForId: dynamic = absent
            val unpacked25: dynamic = arg24
            date = unpacked25["date"]
            log = unpacked25["log"]
            categoryForId = unpacked25["categoryForId"]
            if (truth(jsOr(jsOr(jsOr((!truth(safeDate(date)))) { (!truth(onPresent(log) { value26 -> value26.complete })) }) { (!truth(validRating(global_Number(log.rpe)))) }) { (jsTypeOf(categoryForId) !== "function") })) {
                return jsonValue("{\"recorded\":false,\"why\":\"No hay sesión completada y valorada.\"}")
            }
            val cats: dynamic = completedCategories(log, categoryForId)
            val `value`: dynamic = global_Number(log.rpe)
            if (truth((cats.length === 1))) {
                R.effort(`data`, jsObject { obj27 ->
                    obj27["date"] = date
                    obj27["category"] = cats[0]
                    obj27["value"] = `value`
                    obj27["completed"] = true
                    obj27["global"] = true
                })
            } else {
                R.globalEffort(`data`, jsObject { obj28 ->
                    obj28["date"] = date
                    obj28["value"] = `value`
                })
            }
            if (truth((!truth(`data`.categoryFeedback3873)))) {
                `data`.categoryFeedback3873 = js("[]")
            }
            if (truth((cats.length === 1))) {
                val q: dynamic = jsOr(log.afterFeedback3870) { js("({})") }
                val entry: dynamic = jsObject { obj31 ->
                    obj31["date"] = date
                    obj31["category"] = cats[0]
                    obj31["value"] = `value`
                    obj31["hasPain"] = jsOr(jsOr((q.symptom === "pain")) { (q.symptom === "discomfort") }) { global_Boolean(log.pain) }
                    obj31["fatigue"] = jsOr(jsOr((q.readiness === "tired")) { (q.symptom === "tired") }) { global_Boolean(log.fatigue) }
                    obj31["recovered"] = jsAnd((q.readiness === "recovered")) { (q.symptom === "none") }
                    obj31["completed"] = true
                }
                val k: dynamic = `data`.categoryFeedback3873.findIndex((fun(x: dynamic): dynamic {
                    return jsAnd((x.date === date)) { (x.category === cats[0]) }
                }))
                if (truth(jsAtLeast(k, 0))) {
                    `data`.categoryFeedback3873[k] = entry
                } else {
                    `data`.categoryFeedback3873.push(entry)
                }
            }
            return jsObject { obj34 ->
                obj34["recorded"] = true
                obj34["category"] = (if (truth((cats.length === 1))) cats[0] else null)
                obj34["globalOnly"] = (cats.length !== 1)
                obj34["why"] = (if (truth((cats.length === 1))) "Valoración general guardada para una categoría." else "Valoración global; sesión mixta o sin registros cuantificados.")
            }
        }
        recentCategory = fun(`data`: dynamic, category: dynamic, today: dynamic, arg37: dynamic): dynamic {
            var lookback: dynamic = absent
            lookback = definedOr(arg37) { 7 }
            return dynamicValue(jsOr(onPresent(`data`) { value41 -> value41.categoryFeedback3873 }) { js("[]") }).filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAnd((x.category === category)) { jsLess(x.date, today) }) { jsAtLeast(x.date, R.addDays(today, (-numeric(lookback)))) }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return a.date.localeCompare(b.date)
            }))
        }
        volumeReference = fun(arg44: dynamic): dynamic {
            var rating: dynamic = absent
            var unit: dynamic = absent
            var base: dynamic = absent
            var min: dynamic = absent
            var max: dynamic = absent
            var completed: dynamic = absent
            var interrupted: dynamic = absent
            var recovered: dynamic = absent
            var pain: dynamic = absent
            var approved: dynamic = absent
            val unpacked45: dynamic = definedOr(arg44) { js("({})") }
            rating = unpacked45["rating"]
            unit = definedOr(unpacked45["unit"]) { "REPETITIONS" }
            base = unpacked45["base"]
            min = definedOr(unpacked45["min"]) { 1 }
            max = definedOr(unpacked45["max"]) { null }
            completed = definedOr(unpacked45["completed"]) { false }
            interrupted = definedOr(unpacked45["interrupted"]) { false }
            recovered = definedOr(unpacked45["recovered"]) { false }
            pain = definedOr(unpacked45["pain"]) { false }
            approved = definedOr(unpacked45["approved"]) { false }
            val delta: dynamic = reference(rating)
            if (truth((delta === null))) {
                return jsonValue("{\"status\":\"sin_datos\",\"delta\":null,\"next\":null,\"why\":\"Sin valoración válida.\"}")
            }
            if (truth((unit !== "REPETITIONS"))) {
                return jsonValue("{\"status\":\"unidad_separada\",\"delta\":null,\"next\":null,\"why\":\"Los segundos requieren un protocolo distinto.\"}")
            }
            if (truth(jsOr(jsOr(jsOr(jsOr((!truth(global_Number.isInteger(base)))) { jsLess(base, 1) }) { (!truth(global_Number.isInteger(min))) }) { jsLess(min, 1) }) { jsAnd((max !== null)) { jsOr((!truth(global_Number.isInteger(max)))) { jsLess(max, min) } } })) {
                return jsObject { obj46 ->
                    obj46["status"] = "sin_limites"
                    obj46["delta"] = delta
                    obj46["next"] = dynamicValue(null)
                    obj46["why"] = "Faltan datos válidos de referencia y límites."
                }
            }
            val floor: dynamic = global_Math.min(min, base)
            if (truth(jsAnd(jsMore(delta, 0)) { (max === null) })) {
                return jsObject { obj47 ->
                    obj47["status"] = "sin_maximo"
                    obj47["delta"] = delta
                    obj47["next"] = dynamicValue(null)
                    obj47["why"] = "No existe un máximo certificado para esta variante."
                }
            }
            val next: dynamic = global_Math.max(floor, global_Math.min(nullableOr(max) { global_Number.MAX_SAFE_INTEGER }, jsAdd(base, delta)))
            if (truth(jsAnd(jsMore(delta, 0)) { jsOr(jsOr(jsOr((!truth(completed))) { interrupted }) { (!truth(recovered)) }) { pain } })) {
                return jsObject { obj48 ->
                    obj48["status"] = "mantener"
                    obj48["delta"] = delta
                    obj48["next"] = base
                    obj48["why"] = "Una valoración fácil sin recuperación y registros completos no autoriza un aumento."
                }
            }
            if (truth(jsMore(delta, 0))) {
                return jsObject { obj49 ->
                    obj49["status"] = (if (truth(approved)) "aceptada_para_revision" else "sugerencia")
                    obj49["delta"] = delta
                    obj49["next"] = next
                    obj49["why"] = "Referencia numérica revisable; no cambia automáticamente el plan ni es obligatoria."
                }
            }
            return jsObject { obj50 ->
                obj50["status"] = (if (truth(jsLess(delta, 0))) "reduccion_orientativa" else "mantener")
                obj50["delta"] = delta
                obj50["next"] = next
                obj50["why"] = (if (truth(jsLess(delta, 0))) "Referencia reducida dentro de los límites de la variante." else "No corresponde variar el volumen.")
            }
        }
        cycleDay = fun(`data`: dynamic, date: dynamic): dynamic {
            val start: dynamic = onPresent(`data`) { value53 -> value53.initialExperienceDate }
            if (truth(jsOr(jsOr((!truth(safeDate(start)))) { (!truth(safeDate(date))) }) { jsLess(R.distance(date, start), 0) })) {
                return null
            }
            return jsAdd(R.distance(date, start), 1)
        }
        milestone = fun(`data`: dynamic, category: dynamic, today: dynamic): dynamic {
            if (truth(jsOr((!truth(R.categories.includes(category)))) { (!truth(safeDate(today))) })) {
                throw global_Error("Categoría o fecha inválida")
            }
            val d: dynamic = cycleDay(`data`, today)
            val checkpoint: dynamic = (if (truth(jsAnd((d !== null)) { dynamicValue(jsonValue("[7,14,21]")).includes(d) })) d else null)
            val last: dynamic = recentCategory(`data`, category, today, 7)
            val low: dynamic = jsArray { array56 ->
                appendAll(array56, jsConstruct(global_Set, arrayOf<dynamic>(last.filter((fun(x: dynamic): dynamic {
                    return jsAnd(jsAnd(jsAnd(jsAnd(x.completed) { jsAtMost(x["value"], 3) }) { x.recovered }) { (!truth(x.hasPain)) }) { (!truth(x.fatigue)) }
                })).map((fun(x: dynamic): dynamic {
                    return x.date
                })))))
            }
            val blocked: dynamic = last.some((fun(x: dynamic): dynamic {
                return jsOr(jsOr(x.hasPain) { x.fatigue }) { jsAtLeast(x["value"], 8) }
            }))
            val eligible: dynamic = jsAnd(jsAtLeast(low.length, 2)) { (!truth(blocked)) }
            val requested: dynamic = (if (truth((checkpoint === 21))) 2 else (if (truth(jsOr((checkpoint === 7)) { (checkpoint === 14) })) 1 else 0))
            return jsObject { obj57 ->
                obj57["day"] = d
                obj57["checkpoint"] = checkpoint
                obj57["requested"] = requested
                obj57["eligible"] = jsAnd(eligible) { global_Boolean(checkpoint) }
                obj57["lowSessions"] = low.length
                obj57["blocked"] = blocked
                obj57["action"] = (if (truth((!truth(checkpoint)))) "esperar" else (if (truth(blocked)) "recuperacion" else (if (truth((!truth(eligible)))) "mantener" else "evaluar_propuesta")))
                obj57["why"] = (if (truth((!truth(checkpoint)))) "Las revisiones ocurren en los días 7, 14 y 21." else (if (truth(blocked)) "Existe una señal reciente de fatiga, molestias o esfuerzo alto." else (if (truth((!truth(eligible)))) "No hay dos sesiones fáciles y recuperadas en días diferentes dentro de la ventana." else ("Revisión día " + textOf(checkpoint) + ": hay información para evaluar hasta " + textOf(requested) + " variante(s) superiores, opcionales."))))
            }
        }
        higherCandidates = fun(arg60: dynamic): dynamic {
            var catalog: dynamic = absent
            var profile: dynamic = absent
            var `data`: dynamic = absent
            var category: dynamic = absent
            var today: dynamic = absent
            var currentIds: dynamic = absent
            val unpacked61: dynamic = arg60
            catalog = unpacked61["catalog"]
            profile = unpacked61["profile"]
            `data` = unpacked61["data"]
            category = unpacked61["category"]
            today = unpacked61["today"]
            currentIds = definedOr(unpacked61["currentIds"]) { js("[]") }
            val m: dynamic = milestone(`data`, category, today)
            if (truth(jsOr(jsOr(jsOr((!truth(m.eligible))) { (!truth(catalog)) }) { (!truth(global_Array.isArray(currentIds))) }) { (!truth(currentIds.length)) })) {
                return jsObject { obj62 ->
                    copyFields(obj62, m)
                    obj62["proposals"] = js("[]")
                    obj62["why"] = (if (truth(m.eligible)) "Falta catálogo o variante anterior certificada." else m.why)
                }
            }
            val all: dynamic = catalog.all()
            val proposals: dynamic = js("[]")
            if (true) {
                val iterator63: dynamic = iteratorOf(currentIds)
                while (true) {
                    val next64: dynamic = iterator63.next()
                    if (truth(next64.done)) break
                    var id: dynamic = absent
                    id = next64.value
                    val prior: dynamic = catalog.byId(id)
                    if (truth(jsOr(jsOr((!truth(prior))) { (prior.category !== category) }) { jsAtLeast(prior.level, 3) })) {
                        continue
                    }
                    val next: dynamic = all.filter((fun(v: dynamic): dynamic {
                        return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd((v.type === prior.type)) { (v.level === jsAdd(prior.level, 1)) }) { jsAtMost(v.level, 3) }) { global_Array.isArray(v.progressionFromIds) }) { v.progressionFromIds.includes(prior.id) }) { R.compatible(v, profile).ok }
                    })).sort((fun(a: dynamic, b: dynamic): dynamic {
                        return a.id.localeCompare(b.id)
                    }))[0]
                    if (truth(jsAnd(next) { (!truth(proposals.some((fun(p: dynamic): dynamic {
                        return (p.id === next.id)
                    })))) })) {
                        val safeIntro: dynamic = jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(next.introDose) { global_Number.isInteger(next.introDose.sets) }) { jsAtLeast(next.introDose.sets, 1) }) { jsAtMost(next.introDose.sets, 2) }) { global_Number.isInteger(next.introDose.perSet) }) { jsAtLeast(next.introDose.perSet, 1) }) { (next.introDose.unit === "REPETITIONS") }
                        proposals.push(jsObject { obj65 ->
                            obj65["id"] = next.id
                            obj65["from"] = prior.id
                            obj65["level"] = next.level
                            obj65["category"] = category
                            obj65["kind"] = "propuesta_sin_asignar"
                            obj65["dose"] = (if (truth(safeIntro)) clean(next.introDose) else null)
                            obj65["why"] = (if (truth(safeIntro)) "Variante superior explícitamente vinculada; dosis de introducción declarada por catálogo y sujeta a aceptación." else "Variante superior vinculada, pero sin dosis de introducción validada. No se asignan repeticiones.")
                        })
                    }
                    if (truth(jsAtLeast(proposals.length, m.requested))) {
                        break
                    }
                }
            }
            return jsObject { obj66 ->
                copyFields(obj66, m)
                obj66["proposals"] = proposals
                obj66["why"] = (if (truth(proposals.length)) m.why else "No hay variantes superiores compatibles y expresamente relacionadas. Se mantienen las actuales.")
            }
        }
        generate = fun(options: dynamic): dynamic {
            val basic: dynamic = R.generate(options)
            if (truth(jsOr(jsOr(jsOr((!truth(onPresent(options) { value69 -> value69.catalog }))) { (!truth(onPresent(options) { value70 -> value70.profile })) }) { (!truth(onPresent(options) { value71 -> value71.today })) }) { (!truth(onPresent(options) { value72 -> value72.state })) })) {
                return basic
            }
            if (truth((basic.category === "descanso"))) {
                return jsObject { obj73 ->
                    copyFields(obj73, basic)
                    obj73["review"] = dynamicValue(null)
                    obj73["source"] = "AsterionUnified3873"
                }
            }
            val review: dynamic = milestone(options.state, basic.category, options.today)
            val proposals: dynamic = (if (truth(onPresent(basic.items) { value74 -> value74.length })) higherCandidates(jsObject { obj75 ->
                obj75["catalog"] = options.catalog
                obj75["profile"] = options.profile
                obj75["data"] = options.state
                obj75["category"] = basic.category
                obj75["today"] = options.today
                obj75["currentIds"] = basic.items.map((fun(v: dynamic): dynamic {
                    return v.id
                }))
            }) else jsObject { obj76 ->
                copyFields(obj76, review)
                obj76["proposals"] = js("[]")
            })
            return jsObject { obj77 ->
                copyFields(obj77, basic)
                obj77["review"] = proposals
                obj77["source"] = "AsterionUnified3873"
            }
        }
        if (truth((!truth(R)))) {
            throw global_Error("Falta motor Asterion 38.72")
        }
        SCALE = global_Object.freeze(jsonValue("{\"1\":5,\"2\":3,\"3\":1,\"4\":0,\"5\":0,\"6\":0,\"7\":0,\"8\":-1,\"9\":-2,\"10\":-3}"))
        val ISO: dynamic = jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")
        safeDate = (fun(d: dynamic): dynamic {
            return jsAnd(jsAnd((jsTypeOf(d) === "string")) { ISO.test(d) }) { (!truth(global_Number.isNaN(global_Date.parse(jsAdd(d, "T12:00:00Z"))))) }
        })
        validRating = (fun(x: dynamic): dynamic {
            return jsAnd(jsAnd(global_Number.isInteger(x)) { jsAtLeast(x, 1) }) { jsAtMost(x, 10) }
        })
        clean = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        return global_Object.freeze(jsObject { obj78 ->
            obj78["version"] = "38.73"
            obj78["SCALE"] = SCALE
            obj78["reference"] = reference
            obj78["completedCategories"] = completedCategories
            obj78["recordSession"] = recordSession
            obj78["recentCategory"] = recentCategory
            obj78["volumeReference"] = volumeReference
            obj78["cycleDay"] = cycleDay
            obj78["milestone"] = milestone
            obj78["higherCandidates"] = higherCandidates
            obj78["generate"] = generate
        })
    }))
}
