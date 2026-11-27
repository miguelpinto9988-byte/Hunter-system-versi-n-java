@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterportable3868

import hunter.browser.*

@JsName("console") private external var global_console: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Intl") private external var global_Intl: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("exerciseList") private external var global_exerciseList: dynamic
@JsName("book") private external var global_book: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("history") private external var global_history: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterPortable3868() {
    dynamicValue((fun(root: dynamic): dynamic {
        var D: dynamic = absent
        var E: dynamic = absent
        var CODE: dynamic = absent
        var map: dynamic = absent
        var names: dynamic = absent
        var astFamilies: dynamic = absent
        var astTypes: dynamic = absent
        var astVariants: dynamic = absent
        var astProfiles: dynamic = absent
        var escSafe: dynamic = absent
        var titleCase: dynamic = absent
        var CATEGORY_KEYS: dynamic = absent
        var exerciseMeta: dynamic = absent
        var defaults: dynamic = absent
        var settings: dynamic = absent
        var validZone: dynamic = absent
        var persist: dynamic = absent
        var setField: dynamic = absent
        var toggleCategory: dynamic = absent
        var taxonomy: dynamic = absent
        var familyLabel: dynamic = absent
        var typeLabel: dynamic = absent
        var allFamilyIds: dynamic = absent
        var allTypeIds: dynamic = absent
        var filterExercise: dynamic = absent
        var selectCategory: dynamic = absent
        var selectFamily: dynamic = absent
        var selectType: dynamic = absent
        var filtersMarkup: dynamic = absent
        var settingsMarkup: dynamic = absent
        var categoryOf: dynamic = absent
        var volumeRecords: dynamic = absent
        var trend: dynamic = absent
        var asDate: dynamic = absent
        var dateOffset: dynamic = absent
        var checkpoints: dynamic = absent
        var effortGuidance: dynamic = absent
        var volumeMarkup: dynamic = absent
        var matchedProfile: dynamic = absent
        defaults = fun(): dynamic {
            return jsObject { obj12 ->
                obj12["categories"] = CODE.slice()
                obj12["experience"] = "principiante"
                obj12["dailyActivities"] = 3
                obj12["volumeSuggestions"] = false
                obj12["timeZone"] = "system"
                obj12["language"] = "es"
            }
        }
        settings = fun(): dynamic {
            val raw: dynamic = (if (truth(jsAnd(jsAnd(jsAnd((js("typeof state") !== "undefined")) { global_state }) { (jsTypeOf(global_state.portable3868) === "object") }) { (!truth(global_Array.isArray(global_state.portable3868))) })) global_state.portable3868 else js("({})"))
            val profile: dynamic = (if (truth(jsAnd(jsAnd(jsAnd((js("typeof state") !== "undefined")) { global_state }) { global_state.profile }) { (jsTypeOf(global_state.profile) === "object") })) global_state.profile else null)
            if (truth((!truth(profile)))) {
                return jsObject { obj15 ->
                    obj15["categories"] = CODE.slice()
                    obj15["experience"] = "principiante"
                    obj15["dailyActivities"] = 3
                    obj15["volumeSuggestions"] = true
                    obj15["timeZone"] = (if (truth(validZone(raw.timeZone))) raw.timeZone else "system")
                    obj15["language"] = "es"
                }
            }
            val counts: dynamic = global_Object.fromEntries(CODE.map((fun(x: dynamic): dynamic {
                return arrayOf<dynamic>(x, 0)
            })))
            val days: dynamic = (if (truth(global_Array.isArray(profile.weekPlan))) profile.weekPlan else js("[]"))
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration16 = true
                while (true) {
                    if (!firstIteration16) {
                        run {
                            val previous17: dynamic = numeric(i)
                            i = jsAdd(previous17, 1)
                        }
                    }
                    firstIteration16 = false
                    if (!truth(jsLess(i, 7))) break
                    val kind: dynamic = days[i]
                    val code: dynamic = (if (truth((kind === "train"))) jsOr(jsonValue("{\"upper\":\"tren_superior\",\"lower\":\"tren_inferior\",\"core\":\"tren_central\"}")[onPresent(profile.weekFocus) { value18 -> value18[i] }]) { "tren_superior" } else (if (truth((kind === "cardio"))) "cardio" else (if (truth((kind === "mobility"))) "movilidad" else null)))
                    if (truth(code)) {
                        run {
                            val target19: dynamic = counts
                            val key20: dynamic = code
                            val previous21: dynamic = numeric(target19[key20])
                            target19[key20] = jsAdd(previous21, 1)
                        }
                    }
                }
            }
            val largest: dynamic = run { val target23: dynamic = global_Math; invokeWith(target23.max, target23, jsArray { array22 ->
                appendAll(array22, global_Object.values(counts))
            }) }
            val categories: dynamic = (if (truth(jsMore(largest, 0))) CODE.filter((fun(code: dynamic): dynamic {
                return (counts[code] === largest)
            })) else CODE.slice())
            val experience: dynamic = jsOr(jsonValue("{\"basic\":\"principiante\",\"intermediate\":\"intermedio\",\"advanced\":\"avanzado\"}")[profile.difficulty]) { "principiante" }
            val desired: dynamic = (if (truth((profile.trainingMode === "routines"))) profile.routineExercises3878 else profile.dailyMissions)
            val dailyActivities: dynamic = (if (truth(global_Number.isInteger(global_Number(desired)))) global_Math.max(1, global_Math.min(7, global_Number(desired))) else 3)
            return jsObject { obj24 ->
                obj24["categories"] = categories
                obj24["experience"] = experience
                obj24["dailyActivities"] = dailyActivities
                obj24["volumeSuggestions"] = true
                obj24["timeZone"] = (if (truth(validZone(profile.timeZone))) profile.timeZone else (if (truth(validZone(raw.timeZone))) raw.timeZone else "system"))
                obj24["language"] = "es"
            }
        }
        validZone = fun(s: dynamic): dynamic {
            if (truth((s === "system"))) {
                return true
            }
            if (truth(jsOr((jsTypeOf(s) !== "string")) { jsMore(s.length, 60) })) {
                return false
            }
            try {
                jsConstruct(global_Intl.DateTimeFormat, arrayOf<dynamic>("es", jsObject { obj27 ->
                    obj27["timeZone"] = s
                }))
                return true
            } catch (_: dynamic) {
                return false
            }
            return absent
        }
        persist = fun(s: dynamic): dynamic {
            if (truth(jsOr((js("typeof state") === "undefined")) { (!truth(global_state)) })) {
                return false
            }
            global_state.portable3868 = jsObject { obj32 ->
                copyFields(obj32, s)
                obj32["categories"] = s.categories.slice()
            }
            val ok: dynamic = (if (truth((js("typeof save") === "function"))) global_save() else false)
            onPresent(root.HunterIntegration3869) { value33 -> run { val owner35: dynamic = value33; onPresent(owner35.preferenceChanged) { method34 -> invokeWith(method34, owner35, js("[]")) } } }
            return ok
        }
        setField = fun(`field`: dynamic, `value`: dynamic): dynamic {
            val s: dynamic = settings()
            if (truth(jsAnd((`field` === "experience")) { dynamicValue(jsonValue("[\"principiante\",\"intermedio\",\"avanzado\"]")).includes(`value`) })) {
                s.experience = `value`
            } else {
                if (truth(jsAnd(jsAnd(jsAnd((`field` === "dailyActivities")) { global_Number.isInteger(global_Number(`value`)) }) { jsAtLeast(global_Number(`value`), 1) }) { jsAtMost(global_Number(`value`), 7) })) {
                    s.dailyActivities = global_Number(`value`)
                } else {
                    if (truth((`field` === "volumeSuggestions"))) {
                        s.volumeSuggestions = (`value` === true)
                    } else {
                        if (truth(jsAnd((`field` === "timeZone")) { validZone(`value`) })) {
                            s.timeZone = `value`
                        } else {
                            if (truth((`field` === "language"))) {
                                s.language = "es"
                            } else {
                                return false
                            }
                        }
                    }
                }
            }
            return persist(s)
        }
        toggleCategory = fun(code: dynamic, enabled: dynamic, node: dynamic): dynamic {
            if (truth((!truth(CATEGORY_KEYS.has(code))))) {
                return false
            }
            val s: dynamic = settings()
            val curr: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(s.categories))
            if (truth(enabled)) {
                curr.add(code)
            } else {
                curr.delete(code)
            }
            if (truth((!truth(curr.size)))) {
                if (truth(node)) {
                    node.checked = true
                }
                if (truth((js("typeof toast") === "function"))) {
                    global_toast("Mantén al menos una categoría.")
                }
                return false
            }
            s.categories = CODE.filter((fun(c: dynamic): dynamic {
                return curr.has(c)
            }))
            return persist(s)
        }
        familyLabel = fun(key: dynamic): dynamic {
            val raw: dynamic = jsOr(onPresent(key) { value56 -> value56.split(":")[1] }) { "" }
            if (truth(raw.startsWith("ast_"))) {
                return jsOr(onPresent(astFamilies["get"](raw.slice(4))) { value58 -> value58[1] }) { titleCase(raw.slice(4)) }
            }
            val name: dynamic = names[raw]
            return (if (truth(name)) name.replace(jsRegExp("\\s*[·–]\\s*(retorno lento|pausa de control|ritmo suave|intervalos breves)\$", "i"), "") else titleCase(raw))
        }
        typeLabel = fun(key: dynamic): dynamic {
            val raw: dynamic = jsOr(onPresent(key) { value61 -> value61.split(":").slice(2).join(":") }) { "" }
            return (if (truth(raw.startsWith("ast_"))) jsOr(onPresent(astTypes["get"](raw.slice(4))) { value64 -> value64[2] }) { titleCase(raw.slice(4)) } else titleCase(raw))
        }
        allFamilyIds = fun(category: dynamic): dynamic {
            return dynamicValue(jsArray { array72 ->
                appendAll(array72, jsConstruct(global_Set, arrayOf<dynamic>(dynamicValue(jsArray { array76 ->
                    appendAll(array76, map.values())
                }).filter((fun(r: dynamic): dynamic {
                    return jsOr((category === "all")) { (r.category === category) }
                })).map((fun(r: dynamic): dynamic {
                    return r.family
                })))))
            }).sort((fun(a: dynamic, b: dynamic): dynamic {
                return familyLabel(a).localeCompare(familyLabel(b), "es")
            }))
        }
        allTypeIds = fun(category: dynamic, family: dynamic): dynamic {
            return dynamicValue(jsArray { array84 ->
                appendAll(array84, jsConstruct(global_Set, arrayOf<dynamic>(dynamicValue(jsArray { array88 ->
                    appendAll(array88, map.values())
                }).filter((fun(r: dynamic): dynamic {
                    return jsAnd(jsOr((category === "all")) { (r.category === category) }) { jsOr((family === "all")) { (r.family === family) } }
                })).map((fun(r: dynamic): dynamic {
                    return r.type
                })))))
            }).sort((fun(a: dynamic, b: dynamic): dynamic {
                return typeLabel(a).localeCompare(typeLabel(b), "es")
            }))
        }
        filterExercise = fun(e: dynamic): dynamic {
            val m: dynamic = exerciseMeta(onPresent(e) { value91 -> value91.id })
            return jsAnd(jsAnd(jsAnd((!truth((!truth(m))))) { jsOr((taxonomy.category === "all")) { (m.category === taxonomy.category) } }) { jsOr((taxonomy.family === "all")) { (m.family === taxonomy.family) } }) { jsOr((taxonomy.type === "all")) { (m.type === taxonomy.type) } }
        }
        selectCategory = fun(code: dynamic): dynamic {
            taxonomy.category = (if (truth(jsOr((code === "all")) { CATEGORY_KEYS.has(code) })) code else "all")
            taxonomy.family = "all"
            taxonomy.type = "all"
            if (truth((js("typeof render") === "function"))) {
                global_render()
            }
            return absent
        }
        selectFamily = fun(f: dynamic): dynamic {
            taxonomy.family = (if (truth(jsOr((f === "all")) { allFamilyIds(taxonomy.category).includes(f) })) f else "all")
            taxonomy.type = "all"
            if (truth((js("typeof render") === "function"))) {
                global_render()
            }
            return absent
        }
        selectType = fun(t: dynamic): dynamic {
            taxonomy.type = (if (truth(jsOr((t === "all")) { allTypeIds(taxonomy.category, taxonomy.family).includes(t) })) t else "all")
            if (truth((js("typeof render") === "function"))) {
                global_render()
            }
            return absent
        }
        filtersMarkup = fun(): dynamic {
            val s: dynamic = settings()
            val categories: dynamic = jsArray { array112 ->
                array112.push(jsonValue("[\"all\",\"Todas las categorías\"]"))
                appendAll(array112, D.categories.map((fun(c: dynamic): dynamic {
                    return arrayOf<dynamic>(c.code, jsAdd(c.label, (if (truth(s.categories.includes(c.code))) "" else " · no priorizada")))
                })))
            }
            val opt: dynamic = (fun(xs: dynamic, `value`: dynamic): dynamic {
                return xs.map((fun(arg115: dynamic): dynamic {
                    var key: dynamic = absent
                    var label: dynamic = absent
                    val unpacked116: dynamic = fromIterable(arg115)
                    key = unpacked116[0]
                    label = unpacked116[1]
                    return ("<option value=\"" + textOf(escSafe(key)) + "\" " + textOf((if (truth((key === `value`))) "selected" else "")) + ">" + textOf(escSafe(label)) + "</option>")
                })).join("")
            })
            val fam: dynamic = jsArray { array117 ->
                array117.push(jsonValue("[\"all\",\"Todas las familias\"]"))
                appendAll(array117, allFamilyIds(taxonomy.category).map((fun(f: dynamic): dynamic {
                    return arrayOf<dynamic>(f, familyLabel(f))
                })))
            }
            val typ: dynamic = jsArray { array118 ->
                array118.push(jsonValue("[\"all\",\"Todos los tipos\"]"))
                appendAll(array118, allTypeIds(taxonomy.category, taxonomy.family).map((fun(t: dynamic): dynamic {
                    return arrayOf<dynamic>(t, typeLabel(t))
                })))
            }
            return ("<section class=\"portable-taxonomy3868 card\" aria-label=\"Clasificación de ejercicios\">\n    <div class=\"portable-heading3868\"><strong>Categorías · Familias · Tipos</strong><small>Asterion adaptado a " + textOf(map.size) + " ejercicios Hunter. Filtros combinables con equipo y nivel.</small></div>\n    <div class=\"portable-filtergrid3868\">\n     <label>Categoría<select aria-label=\"Categoría de entrenamiento\" onchange=\"HunterPortable3868.selectCategory(this.value)\">" + textOf(opt(categories, taxonomy.category)) + "</select></label>\n     <label>Familia<select aria-label=\"Familia de movimiento\" onchange=\"HunterPortable3868.selectFamily(this.value)\">" + textOf(opt(fam, taxonomy.family)) + "</select></label>\n     <label>Tipo<select aria-label=\"Tipo de movimiento\" onchange=\"HunterPortable3868.selectType(this.value)\">" + textOf(opt(typ, taxonomy.type)) + "</select></label>\n    </div>\n    <p class=\"portable-foot3868\">Las equivalencias entre ejercicios de Asterion y Hunter se conservan separadas; no se importan cantidades automáticamente.</p>\n  </section>")
        }
        settingsMarkup = fun(): dynamic {
            val s: dynamic = settings()
            val checks: dynamic = D.categories.map((fun(c: dynamic): dynamic {
                return ("<label class=\"portable-option3868\"><input type=\"checkbox\" " + textOf((if (truth(s.categories.includes(c.code))) "checked" else "")) + " onchange=\"HunterPortable3868.toggleCategory('" + textOf(c.code) + "',this.checked,this)\"><span>" + textOf(escSafe(c.label)) + "</span></label>")
            })).join("")
            val choices: dynamic = dynamicValue(jsonValue("[[\"principiante\",\"Principiante\"],[\"intermedio\",\"Intermedio\"],[\"avanzado\",\"Avanzado\"]]")).map((fun(arg123: dynamic): dynamic {
                var v: dynamic = absent
                var n: dynamic = absent
                val unpacked124: dynamic = fromIterable(arg123)
                v = unpacked124[0]
                n = unpacked124[1]
                return ("<option value=\"" + textOf(v) + "\" " + textOf((if (truth((s.experience === v))) "selected" else "")) + ">" + textOf(n) + "</option>")
            })).join("")
            val count: dynamic = dynamicValue(jsonValue("[1,2,3,4,5,6,7]")).map((fun(n: dynamic): dynamic {
                return ("<option value=\"" + textOf(n) + "\" " + textOf((if (truth((s.dailyActivities === n))) "selected" else "")) + ">" + textOf(n) + "</option>")
            })).join("")
            val zones: dynamic = jsonValue("[[\"system\",\"La del dispositivo\"],[\"America/Lima\",\"América / Lima\"],[\"UTC\",\"UTC\"],[\"Europe/Madrid\",\"Europa / Madrid\"]]")
            if (truth((!truth(zones.some((fun(z: dynamic): dynamic {
                return (z[0] === s.timeZone)
            })))))) {
                zones.push(arrayOf<dynamic>(s.timeZone, s.timeZone))
            }
            val tz: dynamic = zones.map((fun(arg127: dynamic): dynamic {
                var v: dynamic = absent
                var n: dynamic = absent
                val unpacked128: dynamic = fromIterable(arg127)
                v = unpacked128[0]
                n = unpacked128[1]
                return ("<option value=\"" + textOf(escSafe(v)) + "\" " + textOf((if (truth((s.timeZone === v))) "selected" else "")) + ">" + textOf(escSafe(n)) + "</option>")
            })).join("")
            return ("<section class=\"card portable-settings3868\" data-profile-pane3857=\"3\" aria-label=\"Configuración de categorías y volumen\">\n   <h3>Motor de categorías y progreso de volumen</h3>\n   <p class=\"muted\">Preferencias independientes de Hunter. No sustituyen tus misiones ni tu historial existente.</p>\n   <fieldset class=\"portable-fieldset3868\"><legend>Categorías de interés</legend><div class=\"portable-categories3868\">" + textOf(checks) + "</div></fieldset>\n   <div class=\"portable-filtergrid3868\">\n    <label>Experiencia orientativa<select onchange=\"HunterPortable3868.setField('experience',this.value)\">" + textOf(choices) + "</select></label>\n    <label>Actividades por día (preferencia)<select onchange=\"HunterPortable3868.setField('dailyActivities',this.value)\">" + textOf(count) + "</select></label>\n    <label>Zona horaria de consulta<select onchange=\"HunterPortable3868.setField('timeZone',this.value)\">" + textOf(tz) + "</select></label>\n    <label>Idioma del catálogo<select disabled><option>Español</option></select></label>\n   </div>\n   <label class=\"portable-option3868\"><input type=\"checkbox\" " + textOf((if (truth(s.volumeSuggestions)) "checked" else "")) + " onchange=\"HunterPortable3868.setField('volumeSuggestions',this.checked)\"><span>Mostrar orientaciones opcionales basadas en esfuerzo registrado</span></label>\n   <p class=\"portable-foot3868\">Desactivadas por defecto. No aumenta repeticiones, series, cargas ni actividades automáticamente. Las preferencias se guardan junto con la copia de seguridad de Hunter.</p>\n  </section>")
        }
        categoryOf = fun(id: dynamic): dynamic {
            return jsOr(onPresent(exerciseMeta(id)) { value131 -> value131.category }) { null }
        }
        volumeRecords = fun(logs: dynamic): dynamic {
            val records: dynamic = js("[]")
            if (true) {
                val iterator134: dynamic = iteratorOf(global_Object.entries(jsOr(logs) { js("({})") }))
                while (true) {
                    val next135: dynamic = iterator134.next()
                    if (truth(next135.done)) break
                    var day: dynamic = absent
                    var log: dynamic = absent
                    val unpacked136: dynamic = fromIterable(next135.value)
                    day = unpacked136[0]
                    log = unpacked136[1]
                    if (truth(jsOr(jsOr((!truth(dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(day)))) { (!truth(log)) }) { (!truth(global_Array.isArray(log.items))) })) {
                        continue
                    }
                    if (true) {
                        val iterator137: dynamic = iteratorOf(log.items)
                        while (true) {
                            val next138: dynamic = iterator137.next()
                            if (truth(next138.done)) break
                            var item: dynamic = absent
                            item = next138.value
                            val meta: dynamic = exerciseMeta(onPresent(item) { value139 -> value139.id })
                            val ex: dynamic = E.byId(onPresent(item) { value140 -> value140.id })
                            if (truth(jsOr(jsOr((!truth(meta))) { (!truth(ex)) }) { (!truth(global_Array.isArray(item["actual"]))) })) {
                                continue
                            }
                            val unit: dynamic = (if (truth((ex.unit === "sec"))) "SECONDS" else "REPETITIONS")
                            val completed: dynamic = item["actual"].filter((fun(a: dynamic): dynamic {
                                return jsAnd(jsAnd(jsAnd(a) { (a.done === true) }) { global_Number.isFinite(global_Number(a["value"])) }) { jsMore(global_Number(a["value"]), 0) }
                            })).map((fun(a: dynamic): dynamic {
                                return global_Number(a["value"])
                            }))
                            if (truth((!truth(completed.length)))) {
                                continue
                            }
                            records.push(jsObject { obj141 ->
                                obj141["date"] = day
                                obj141["id"] = ex.id
                                obj141["category"] = meta.category
                                obj141["unit"] = unit
                                obj141["sets"] = completed.length
                                obj141["quantity"] = jsMultiply(completed.reduce((fun(a: dynamic, b: dynamic): dynamic {
                                    return jsAdd(a, b)
                                }), 0), (if (truth(E.sidesOf(item))) 2 else 1))
                                obj141["effort"] = (if (truth(global_Number.isInteger(numeric(log.rpe)))) numeric(log.rpe) else 0)
                                obj141["interrupted"] = (log.pain === true)
                            })
                        }
                    }
                }
            }
            return records.sort((fun(a: dynamic, b: dynamic): dynamic {
                return a.date.localeCompare(b.date)
            }))
        }
        trend = fun(logs: dynamic, from: dynamic, to: dynamic): dynamic {
            val result: dynamic = global_Object.fromEntries(CODE.map((fun(c: dynamic): dynamic {
                return arrayOf<dynamic>(c, jsObject { obj144 ->
                    obj144["reps"] = 0
                    obj144["seconds"] = 0
                    obj144["sets"] = 0
                    obj144["sessions"] = jsConstruct(global_Set, js("[]"))
                    obj144["lastEffort"] = 0
                    obj144["pain"] = false
                    obj144["ids"] = jsConstruct(global_Set, js("[]"))
                })
            })))
            if (true) {
                val iterator145: dynamic = iteratorOf(volumeRecords(logs))
                while (true) {
                    val next146: dynamic = iterator145.next()
                    if (truth(next146.done)) break
                    var r: dynamic = absent
                    r = next146.value
                    if (truth(jsOr(jsAnd(from) { jsLess(r.date, from) }) { jsAnd(to) { jsMore(r.date, to) } })) {
                        continue
                    }
                    val obj: dynamic = result[r.category]
                    if (truth((!truth(obj)))) {
                        continue
                    }
                    if (truth((r.unit === "SECONDS"))) {
                        run {
                            val target147: dynamic = obj
                            target147.seconds = jsAdd(target147.seconds, r.quantity)
                        }
                    } else {
                        run {
                            val target149: dynamic = obj
                            target149.reps = jsAdd(target149.reps, r.quantity)
                        }
                    }
                    run {
                        val target151: dynamic = obj
                        target151.sets = jsAdd(target151.sets, r.sets)
                    }
                    obj.sessions.add(r.date)
                    obj.ids.add(r.id)
                    if (truth(r.effort)) {
                        obj.lastEffort = r.effort
                    }
                    if (truth(r.interrupted)) {
                        obj.pain = true
                    }
                }
            }
            return result
        }
        asDate = fun(day: dynamic): dynamic {
            return jsConstruct(global_Date, arrayOf<dynamic>(jsAdd(day, "T12:00:00")))
        }
        dateOffset = fun(day: dynamic, delta: dynamic): dynamic {
            val t: dynamic = asDate(day)
            t.setDate(jsAdd(t.getDate(), delta))
            val y: dynamic = t.getFullYear()
            val m: dynamic = global_String(jsAdd(t.getMonth(), 1)).padStart(2, "0")
            val d: dynamic = global_String(t.getDate()).padStart(2, "0")
            return ("" + textOf(y) + "-" + textOf(m) + "-" + textOf(d) + "")
        }
        checkpoints = fun(logs: dynamic, category: dynamic, now: dynamic): dynamic {
            val dates: dynamic = volumeRecords(logs).filter((fun(r: dynamic): dynamic {
                return (r.category === category)
            })).map((fun(r: dynamic): dynamic {
                return r.date
            }))
            if (truth((!truth(dates.length)))) {
                return null
            }
            val first: dynamic = dates[0]
            val elapsed: dynamic = global_Math.max(0, global_Math.round(jsDivide(jsSubtract(asDate(now), asDate(first)), 86400000)))
            return jsObject { obj163 ->
                obj163["first"] = first
                obj163["elapsed"] = elapsed
                obj163["checkpoint"] = (if (truth(jsAtLeast(elapsed, 21))) 21 else (if (truth(jsAtLeast(elapsed, 14))) 14 else (if (truth(jsAtLeast(elapsed, 7))) 7 else 0)))
            }
        }
        effortGuidance = fun(logs: dynamic, category: dynamic, now: dynamic): dynamic {
            val rows: dynamic = volumeRecords(logs).filter((fun(r: dynamic): dynamic {
                return jsAnd(jsAnd((r.category === category)) { jsLess(r.date, now) }) { jsAtLeast(r.date, dateOffset(now, (-numeric(7)))) }
            }))
            if (truth((!truth(rows.length)))) {
                return "Aún no hay un registro reciente de esfuerzo en esta categoría."
            }
            val last: dynamic = rows[jsSubtract(rows.length, 1)]
            if (truth(last.interrupted)) {
                return "La última sesión registró una interrupción. Conviene priorizar descanso y revisar la actividad."
            }
            if (truth(jsAtLeast(last.effort, 8))) {
                return "Se registró esfuerzo alto. Prioriza recuperación y una variante cómoda; no se aumentará el volumen."
            }
            if (truth(jsAtLeast(last.effort, 4))) {
                return "Se registró esfuerzo moderado. Mantén una rutina cómoda, sin cambios automáticos."
            }
            if (truth(jsAtLeast(last.effort, 1))) {
                return "Se registró esfuerzo ligero. Puedes revisar tu técnica y opciones al mismo nivel, sin obligación de aumentar volumen."
            }
            return "Sin valoración de esfuerzo suficiente para proponer cambios."
        }
        volumeMarkup = fun(): dynamic {
            val s: dynamic = settings()
            val today: dynamic = (if (truth((jsTypeOf(E.dateKey) === "function"))) E.dateKey() else jsConstruct(global_Date, js("[]")).toISOString().slice(0, 10))
            val from: dynamic = dateOffset(today, (-numeric(27)))
            val recent: dynamic = trend((if (truth((js("typeof state") !== "undefined"))) global_state.logs else js("({})")), from, today)
            val cards: dynamic = D.categories.map((fun(c: dynamic): dynamic {
                val r: dynamic = recent[c.code]
                val checkpoint: dynamic = checkpoints(global_state.logs, c.code, today)
                return ("<div class=\"portable-volumeitem3868\"><strong>" + textOf(escSafe(c.label)) + "</strong>\n   <div><span>" + textOf(r.reps.toLocaleString("es")) + " rep</span><span>" + textOf(r.seconds.toLocaleString("es")) + " s</span></div>\n   <small>" + textOf(r.sets) + " series registradas · " + textOf(r.sessions.size) + " días con registro</small>\n   " + textOf((if (truth(onPresent(checkpoint) { value169 -> value169.checkpoint })) ("<small>Revisión informativa: día " + textOf(checkpoint.checkpoint) + " desde el primer registro de esta categoría</small>") else "")) + "\n   " + textOf((if (truth(s.volumeSuggestions)) ("<p>" + textOf(escSafe(effortGuidance(global_state.logs, c.code, today))) + "</p>") else "")) + "</div>")
            })).join("")
            val exact: dynamic = D.matchedCount
            return ("<section class=\"portable-volume3868 card\" aria-labelledby=\"portable-vol-title3868\">\n    <div class=\"portable-heading3868\"><strong id=\"portable-vol-title3868\">Volumen registrado por categoría</strong><small>Últimos 28 días · series completadas con cantidad real · no incluye confirmaciones sin datos</small></div>\n    <div class=\"portable-volumegrid3868\">" + textOf(cards) + "</div>\n    <p class=\"portable-foot3868\">Repeticiones y segundos se contabilizan por separado. Es un registro descriptivo, no una meta. Las " + textOf(exact) + " equivalencias de nombre y categoría con Asterion no activan automáticamente sus dosis; las demás variantes conservan únicamente el historial de Hunter.</p>\n   </section>")
        }
        matchedProfile = fun(id: dynamic): dynamic {
            val meta: dynamic = exerciseMeta(id)
            if (truth((!truth(onPresent(meta) { value172 -> value172.asterion })))) {
                return null
            }
            val variant: dynamic = astVariants["get"](meta.asterion)
            val profile: dynamic = astProfiles["get"](meta.asterion)
            if (truth(jsOr((!truth(variant))) { (!truth(profile)) })) {
                return null
            }
            return jsObject { obj173 ->
                obj173["hunterId"] = id
                obj173["asterionId"] = variant[0]
                obj173["typeId"] = variant[1]
                obj173["name"] = variant[2]
                obj173["unit"] = profile[1]
                obj173["original"] = profile.slice(2)
                obj173["source"] = "coincidencia exacta única de nombre, NO equivalencia técnica certificada"
            }
        }
        if (truth(root.HunterPortable3868)) {
            return absent
        }
        D = root.HunterPortableData3868
        E = root.Engine
        if (truth(jsOr(jsOr(jsOr((!truth(D))) { (!truth(E)) }) { (!truth(root.HunterGroups49)) }) { (onPresent(D.hunterRows) { value3 -> value3.length } !== 1200) })) {
            global_console.error("HunterPortable3868: catálogos incompletos; integracion omitida")
            return absent
        }
        val CAT_LABELS: dynamic = global_Object.freeze(global_Object.fromEntries(D.categories.map((fun(c: dynamic): dynamic {
            return arrayOf<dynamic>(c.code, c.label)
        }))))
        CODE = jsonValue("[\"tren_superior\",\"tren_central\",\"tren_inferior\",\"movilidad\",\"cardio\"]")
        val DEFAULT: dynamic = global_Object.freeze(jsObject { obj4 ->
            obj4["categories"] = CODE
            obj4["experience"] = "principiante"
            obj4["dailyActivities"] = 3
            obj4["volumeSuggestions"] = false
            obj4["timeZone"] = "system"
            obj4["language"] = "es"
        })
        map = jsConstruct(global_Map, arrayOf<dynamic>(D.hunterRows.map((fun(arg5: dynamic): dynamic {
            var id: dynamic = absent
            var category: dynamic = absent
            var family: dynamic = absent
            var type: dynamic = absent
            var asterion: dynamic = absent
            val unpacked6: dynamic = fromIterable(arg5)
            id = unpacked6[0]
            category = unpacked6[1]
            family = unpacked6[2]
            type = unpacked6[3]
            asterion = unpacked6[4]
            return arrayOf<dynamic>(id, global_Object.freeze(jsObject { obj7 ->
                obj7["id"] = id
                obj7["category"] = category
                obj7["family"] = family
                obj7["type"] = type
                obj7["asterion"] = asterion
            }))
        }))))
        names = D.hunterNames
        astFamilies = jsConstruct(global_Map, arrayOf<dynamic>(D.asterionFamilies.map((fun(a: dynamic): dynamic {
            return arrayOf<dynamic>(a[0], a)
        }))))
        astTypes = jsConstruct(global_Map, arrayOf<dynamic>(D.asterionTypes.map((fun(a: dynamic): dynamic {
            return arrayOf<dynamic>(a[0], a)
        }))))
        astVariants = jsConstruct(global_Map, arrayOf<dynamic>(D.asterionVariants.map((fun(a: dynamic): dynamic {
            return arrayOf<dynamic>(a[0], a)
        }))))
        astProfiles = jsConstruct(global_Map, arrayOf<dynamic>(D.asterionProfiles.map((fun(a: dynamic): dynamic {
            return arrayOf<dynamic>(a[0], a)
        }))))
        val available: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(E.exercises.map((fun(e: dynamic): dynamic {
            return e.id
        }))))
        if (truth(jsOr((map.size !== 1200)) { dynamicValue(jsArray { array9 ->
            appendAll(array9, available)
        }).some((fun(id: dynamic): dynamic {
            return (!truth(map.has(id)))
        })) })) {
            global_console.error("HunterPortable3868: id de ejercicio no coincidente; integración omitida")
            return absent
        }
        escSafe = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        titleCase = (fun(s: dynamic): dynamic {
            return global_String(jsOr(s) { "" }).split(jsRegExp("[_-]+", "")).join(" ").replace(jsRegExp("^.", ""), (fun(c: dynamic): dynamic {
                return c.toUpperCase()
            }))
        })
        CATEGORY_KEYS = jsConstruct(global_Set, arrayOf<dynamic>(CODE))
        exerciseMeta = (fun(id: dynamic): dynamic {
            return jsOr(map["get"](id)) { null }
        })
        taxonomy = jsonValue("{\"category\":\"all\",\"family\":\"all\",\"type\":\"all\"}")
        if (truth((js("typeof exerciseList") === "function"))) {
            val previous: dynamic = global_exerciseList
            global_exerciseList = (fun(): dynamic {
                val receiver174: dynamic = js("this")
                val callArgs175: dynamic = js("arguments")
                return previous.apply(receiver174, callArgs175).filter(filterExercise)
            })
        }
        if (truth((js("typeof book") === "function"))) {
            val previous: dynamic = global_book
            global_book = (fun(): dynamic {
                val receiver176: dynamic = js("this")
                val callArgs177: dynamic = js("arguments")
                val html: dynamic = previous.apply(receiver176, callArgs177)
                if (truth(html.includes("portable-taxonomy3868"))) {
                    return html
                }
                val marker: dynamic = "<p class=\"result-count\" id=\"exerciseCount\""
                return (if (truth(html.includes(marker))) html.replace(marker, jsAdd(filtersMarkup(), marker)) else jsAdd(filtersMarkup(), html))
            })
        }
        if (truth((js("typeof profileForm") === "function"))) {
            val previous: dynamic = global_profileForm
            global_profileForm = (fun(): dynamic {
                val receiver178: dynamic = js("this")
                val callArgs179: dynamic = js("arguments")
                val html: dynamic = previous.apply(receiver178, callArgs179)
                if (truth(html.includes("portable-settings3868"))) {
                    return html
                }
                val idx: dynamic = html.lastIndexOf("</form>")
                return (if (truth(jsAtLeast(idx, 0))) jsAdd(jsAdd(html.slice(0, idx), settingsMarkup()), html.slice(idx)) else html)
            })
        }
        if (truth((js("typeof history") === "function"))) {
            val previous: dynamic = global_history
            global_history = (fun(): dynamic {
                val receiver180: dynamic = js("this")
                val callArgs181: dynamic = js("arguments")
                val html: dynamic = previous.apply(receiver180, callArgs181)
                return (if (truth(html.includes("portable-volume3868"))) html else jsAdd(volumeMarkup(), html))
            })
        }
        root.HunterPortable3868 = global_Object.freeze(jsObject { obj184 ->
            obj184["categories"] = D.categories.slice()
            obj184["exerciseMeta"] = exerciseMeta
            obj184["settings"] = settings
            obj184["setField"] = setField
            obj184["toggleCategory"] = toggleCategory
            obj184["selectCategory"] = selectCategory
            obj184["selectFamily"] = selectFamily
            obj184["selectType"] = selectType
            obj184["filterExercise"] = filterExercise
            obj184["matchedProfile"] = matchedProfile
            obj184["volumeRecords"] = volumeRecords
            obj184["trend"] = trend
            obj184["checkpoints"] = checkpoints
            obj184["effortGuidance"] = effortGuidance
            obj184["audit"] = global_Object.freeze(jsObject { obj185 ->
                obj185["hunterExercises"] = map.size
                obj185["asterionFamilies"] = D.asterionFamilies.length
                obj185["asterionTypes"] = D.asterionTypes.length
                obj185["asterionVariants"] = D.asterionVariants.length
                obj185["profileRows"] = D.asterionProfiles.length
                obj185["exactMatches"] = D.matchedCount
                obj185["mode"] = "non-destructive"
            })
        })
        return absent
    }))(global_window)
}
