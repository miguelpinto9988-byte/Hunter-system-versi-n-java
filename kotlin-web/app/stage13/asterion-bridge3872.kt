@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installasterionbridge3872

import hunter.browser.*

@JsName("String") private external var global_String: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("Intl") private external var global_Intl: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installAsterionBridge3872() {
    dynamicValue((fun(root: dynamic): dynamic {
        var R: dynamic = absent
        var E: dynamic = absent
        var portable: dynamic = absent
        var escape: dynamic = absent
        var W: dynamic = absent
        var getStore: dynamic = absent
        var saveState: dynamic = absent
        var day: dynamic = absent
        var config: dynamic = absent
        var categoryToday: dynamic = absent
        var provider: dynamic = absent
        var change: dynamic = absent
        var equipmentFromFields: dynamic = absent
        var addEquipment: dynamic = absent
        var toggleEquipment: dynamic = absent
        var settingsMarkup: dynamic = absent
        var planPreview: dynamic = absent
        var result: dynamic = absent
        var report: dynamic = absent
        var syncNewQuestionnaire: dynamic = absent
        getStore = fun(): dynamic {
            if (truth(jsOr((js("typeof state") === "undefined")) { (!truth(global_state)) })) {
                return null
            }
            if (truth(jsOr((!truth(global_state.asterion3872))) { (global_state.asterion3872.version !== 1) })) {
                global_state.asterion3872 = jsObject { obj7 ->
                    obj7["version"] = 1
                    obj7["profile"] = R.defaultProfile()
                    obj7["rules"] = R.stateEmpty()
                }
            }
            return global_state.asterion3872
        }
        saveState = fun(): dynamic {
            try {
                if (truth((js("typeof save") === "function"))) {
                    return global_save()
                }
            } catch (_: dynamic) {
                return false
            }
            return false
        }
        day = fun(): dynamic {
            if (truth(onPresent(portable) { value12 -> value12.settings })) {
                val zone: dynamic = portable.settings().timeZone
                if (truth(jsAnd(zone) { (zone !== "system") })) {
                    try {
                        val ps: dynamic = jsConstruct(global_Intl.DateTimeFormat, arrayOf<dynamic>("en-US", jsObject { obj14 ->
                            obj14["timeZone"] = zone
                            obj14["year"] = "numeric"
                            obj14["month"] = "2-digit"
                            obj14["day"] = "2-digit"
                        })).formatToParts(jsConstruct(global_Date, js("[]")))
                        val a: dynamic = (fun(key: dynamic): dynamic {
                            return onPresent(ps.find((fun(x: dynamic): dynamic {
                                return (x.type === key)
                            }))) { value15 -> value15["value"] }
                        })
                        return ("" + textOf(a("year")) + "-" + textOf(a("month")) + "-" + textOf(a("day")) + "")
                    } catch (_: dynamic) {

                    }
                }
            }
            return jsOr(onPresent(E) { value16 -> run { val owner18: dynamic = value16; onPresent(owner18.dateKey) { method17 -> invokeWith(method17, owner18, js("[]")) } } }) { jsConstruct(global_Date, js("[]")).toISOString().slice(0, 10) }
        }
        config = fun(): dynamic {
            val s: dynamic = getStore()
            if (truth(jsAnd(jsAnd((js("typeof state") !== "undefined")) { onPresent(global_state) { value21 -> value21.profile } }) { onPresent(root.HunterProfileUnified3878) { value22 -> value22.derive } })) {
                s.profile = root.HunterProfileUnified3878.derive(global_state.profile)
                if (truth((!truth(s.rules.initialExperienceDate)))) {
                    s.rules.initialExperienceDate = jsOr(onPresent(E) { value27 -> run { val owner29: dynamic = value27; onPresent(owner29.dateKey) { method28 -> invokeWith(method28, owner29, js("[]")) } } }) { jsConstruct(global_Date, js("[]")).toISOString().slice(0, 10) }
                }
            }
            return jsOr(onPresent(s) { value30 -> value30.profile }) { R.defaultProfile() }
        }
        categoryToday = fun(arg33: dynamic): dynamic {
            var dt: dynamic = absent
            dt = definedOr(arg33) { day() }
            val p: dynamic = config()
            return (if (truth((p.weekPlan[R.weekday(dt)] === 5))) "descanso" else R.categories[p.weekPlan[R.weekday(dt)]])
        }
        provider = fun(): dynamic {
            try {
                val injected: dynamic = root.AsterionCatalogProvider3872
                if (truth((!truth(injected)))) {
                    return jsonValue("{\"ref\":null,\"error\":\"Catálogo externo no conectado. No se inventan actividades.\"}")
                }
                val raw: dynamic = (if (truth((jsTypeOf(injected) === "function"))) injected() else injected)
                return jsObject { obj36 ->
                    obj36["ref"] = R.catalog(raw)
                    obj36["error"] = dynamicValue(null)
                }
            } catch (e: dynamic) {
                return jsObject { obj37 ->
                    obj37["ref"] = dynamicValue(null)
                    obj37["error"] = jsAdd("El proveedor externo no pasó la validación: ", global_String(jsOr(onPresent(e) { value38 -> value38.message }) { e }))
                }
            }
            return absent
        }
        change = fun(`field`: dynamic, `value`: dynamic): dynamic {
            val store: dynamic = getStore()
            if (truth((!truth(store)))) {
                return false
            }
            val p: dynamic = store.profile
            if (truth((`field` === "active"))) {
                p.active = (`value` === "true")
                if (truth(jsAnd(p.active) { (!truth(store.rules.initialExperienceDate)) })) {
                    store.rules.initialExperienceDate = day()
                }
            } else {
                if (truth(jsAnd((`field` === "experience")) { dynamicValue(jsonValue("[\"principiante\",\"intermedio\",\"avanzado\"]")).includes(`value`) })) {
                    p.experience = `value`
                    store.rules.initialExperienceDate = day()
                    store.rules.phases = js("({})")
                } else {
                    if (truth((`field` === "custom_goal"))) {
                        p.custom_goal = global_String(`value`).slice(0, 500)
                    } else {
                        if (truth((`field` === "goal"))) {
                            p.goal = global_String(`value`).slice(0, 100)
                        } else {
                            if (truth(jsAnd((`field` === "modality")) { dynamicValue(jsonValue("[\"misiones\",\"rutinas\"]")).includes(`value`) })) {
                                p.modality = `value`
                            } else {
                                if (truth(jsAnd((`field` === "intensity")) { dynamicValue(jsonValue("[\"baja\",\"moderada\"]")).includes(`value`) })) {
                                    p.intensity = `value`
                                } else {
                                    if (truth(jsAnd(jsAnd(jsAnd((`field` === "dailyLimit")) { global_Number.isInteger(global_Number(`value`)) }) { jsAtLeast(global_Number(`value`), 1) }) { jsAtMost(global_Number(`value`), 12) })) {
                                        p.dailyLimit = global_Number(`value`)
                                    } else {
                                        if (truth(jsAnd(jsAnd(jsAnd((`field` === "minutes")) { global_Number.isInteger(global_Number(`value`)) }) { jsAtLeast(global_Number(`value`), 5) }) { jsAtMost(global_Number(`value`), 240) })) {
                                            p.minutes = global_Number(`value`)
                                        } else {
                                            if (truth(jsAnd(jsAnd(jsAnd((`field` === "restSeconds")) { global_Number.isInteger(global_Number(`value`)) }) { jsAtLeast(global_Number(`value`), 0) }) { jsAtMost(global_Number(`value`), 1800) })) {
                                                p.restSeconds = global_Number(`value`)
                                            } else {
                                                if (truth(jsAnd((`field` === "organization")) { dynamicValue(jsonValue("[\"series\",\"rondas\"]")).includes(`value`) })) {
                                                    p.organization = `value`
                                                } else {
                                                    if (truth(`field`.startsWith("weekday:"))) {
                                                        val idx: dynamic = global_Number(`field`.split(":")[1])
                                                        if (truth(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(global_Number.isInteger(idx)))) { jsLess(idx, 0) }) { jsMore(idx, 6) }) { (!truth(global_Number.isInteger(global_Number(`value`)))) }) { jsLess(global_Number(`value`), 0) }) { jsMore(global_Number(`value`), 5) })) {
                                                            return false
                                                        }
                                                        p.weekPlan[idx] = global_Number(`value`)
                                                    } else {
                                                        return false
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            saveState()
            return true
        }
        equipmentFromFields = fun(): dynamic {
            val p: dynamic = config()
            return (if (truth(global_Array.isArray(p.equipment))) p.equipment else js("[]"))
        }
        addEquipment = fun(tag: dynamic): dynamic {
            val clean: dynamic = global_String(jsOr(tag) { "" }).trim().slice(0, 70)
            if (truth((!truth(dynamicValue(jsRegExp("^[\\wÀ-ÿ\\- :.]{2,70}\$", "")).test(clean))))) {
                return false
            }
            return toggleEquipment(clean, true)
        }
        toggleEquipment = fun(tag: dynamic, checked: dynamic): dynamic {
            val s: dynamic = getStore()
            if (truth(jsOr(jsOr((!truth(s))) { (!truth(tag)) }) { jsMore(tag.length, 70) })) {
                return false
            }
            val `set`: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(equipmentFromFields()))
            if (truth(checked)) {
                `set`.add(tag)
            } else {
                `set`.delete(tag)
            }
            s.profile.equipment = dynamicValue(jsArray { array78 ->
                appendAll(array78, `set`)
            }).sort()
            saveState()
            return true
        }
        settingsMarkup = fun(): dynamic {
            val p: dynamic = config()
            val goal: dynamic = R.parseGoal(p.custom_goal)
            val connected: dynamic = provider()
            val choice: dynamic = (fun(current: dynamic, opts: dynamic): dynamic {
                return opts.map((fun(arg83: dynamic): dynamic {
                    var `value`: dynamic = absent
                    var label: dynamic = absent
                    val unpacked84: dynamic = fromIterable(arg83)
                    `value` = unpacked84[0]
                    label = unpacked84[1]
                    return ("<option value=\"" + textOf(escape(`value`)) + "\" " + textOf((if (truth((global_String(current) === global_String(`value`)))) "selected" else "")) + ">" + textOf(escape(label)) + "</option>")
                })).join("")
            })
            val `field`: dynamic = (fun(name: dynamic, title: dynamic, options: dynamic): dynamic {
                return ("<label>" + textOf(title) + "<select onchange=\"AsterionBridge3872.change('" + textOf(name) + "',this.value)\">" + textOf(choice(p[name], options)) + "</select></label>")
            })
            val schedule: dynamic = p.weekPlan.map((fun(code: dynamic, i: dynamic): dynamic {
                return ("<label>" + textOf(W[i]) + "<select aria-label=\"Actividad de " + textOf(W[i]) + "\" onchange=\"AsterionBridge3872.change('weekday:" + textOf(i) + "',this.value)\">" + textOf(choice(code, R.labels.map((fun(x: dynamic, k: dynamic): dynamic {
                    return arrayOf<dynamic>(k, x)
                })))) + "</select></label>")
            })).join("")
            val equipment: dynamic = jsonValue("[\"body\",\"mat\",\"wall\",\"chair\",\"dumbbells\",\"gym\"]")
            val eqName: dynamic = jsonValue("[\"Peso corporal\",\"Colchoneta\",\"Pared/apoyo estable\",\"Silla estable\",\"Mancuernas (declaradas)\",\"Equipamiento de gimnasio (declarado)\"]")
            return ("<section class=\"card ar-settings3872\" data-profile-pane3857=\"3\" aria-label=\"Motor Asterion de reglas y calendario\">\n   <span class=\"eyebrow\">MOTOR LOCAL · REGLAS 38.73</span><h3>Calendario y progreso por categoría</h3>\n   <p class=\"muted\">Configura de lunes a domingo. Descanso no genera actividades. No modifica las misiones ni los registros ya guardados.</p>\n   <label class=\"ar-line3872\"><input type=\"checkbox\" " + textOf((if (truth(p.active)) "checked" else "")) + " onchange=\"HunterActions.setAsterionActive(this)\"> Activar calendario Asterion (al conectar catálogo validado)</label>\n   <div class=\"ar-schedule3872\">" + textOf(schedule) + "</div>\n   <div class=\"ar-fields3872\">" + textOf(`field`("experience", "Experiencia (límite técnico)", jsonValue("[[\"principiante\",\"Principiante\"],[\"intermedio\",\"Intermedio\"],[\"avanzado\",\"Avanzado\"]]"))) + "\n    " + textOf(`field`("goal", "Objetivo principal", jsonValue("[[\"\",\"Sin preferencia\"],[\"bienestar\",\"Bienestar general\"],[\"fuerza\",\"Fuerza funcional\"],[\"movilidad\",\"Movilidad\"],[\"resistencia\",\"Resistencia\"],[\"equilibrio\",\"Equilibrio\"],[\"coordinacion\",\"Coordinación\"]]"))) + "\n    " + textOf(`field`("modality", "Modalidad", jsonValue("[[\"misiones\",\"Misiones\"],[\"rutinas\",\"Rutinas\"]]"))) + "\n    " + textOf(`field`("intensity", "Intensidad orientativa", jsonValue("[[\"baja\",\"Baja\"],[\"moderada\",\"Moderada\"]]"))) + "\n    " + textOf(`field`("dailyLimit", "Máximo de propuestas", global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, i: dynamic): dynamic {
                return arrayOf<dynamic>(jsAdd(i, 1), global_String(jsAdd(i, 1)))
            })))) + "\n    " + textOf(`field`("minutes", "Minutos disponibles", jsonValue("[[10,\"10\"],[20,\"20\"],[30,\"30\"],[45,\"45\"],[60,\"60\"]]"))) + "\n    " + textOf(`field`("restSeconds", "Descanso entre bloques (s)", jsonValue("[[30,\"30\"],[60,\"60\"],[90,\"90\"],[120,\"120\"],[180,\"180\"]]"))) + "\n    " + textOf(`field`("organization", "Organización", jsonValue("[[\"series\",\"Series\"],[\"rondas\",\"Rondas\"]]"))) + "</div>\n   <label>Objetivo personal escrito (opcional)<textarea maxlength=\"500\" rows=\"2\" onchange=\"AsterionBridge3872.change('custom_goal',this.value)\" placeholder=\"Ejemplo: mejorar mi equilibrio\">" + textOf(escape(p.custom_goal)) + "</textarea></label>\n   <p class=\"ar-notice3872\" aria-live=\"polite\">" + textOf((if (truth(goal.recognizedAny)) ("Intenciones detectadas: " + textOf(escape(jsOr(goal.recognized.join(", ")) { "ninguna positiva" })) + ". Exclusiones: " + textOf(escape(jsOr(goal.excluded.join(", ")) { "ninguna" })) + ".") else "Objetivo guardado como texto; ninguna intención reconocida todavía.")) + "</p>\n   <fieldset><legend>Equipamiento declarado para este motor</legend><div class=\"ar-equipment3872\">" + textOf(equipment.map((fun(t: dynamic, i: dynamic): dynamic {
                return ("<label><input type=\"checkbox\" " + textOf((if (truth(p.equipment.includes(t))) "checked" else "")) + " onchange=\"AsterionBridge3872.toggleEquipment('" + textOf(t) + "',this.checked)\"> " + textOf(eqName[i]) + "</label>")
            })).join("")) + "</div><label>Equipo o peso disponible, identificado por ti<input id=\"ar-equipment-new3872\" placeholder=\"Ej.: dumbbells:4kg o gym:machine_name\" maxlength=\"70\"></label><button type=\"button\" class=\"btn ghost\" onclick=\"if(AsterionBridge3872.addEquipment(document.getElementById('ar-equipment-new3872').value))render()\">Añadir equipo declarado</button></fieldset>\n   <p class=\"ar-notice3872\">" + textOf(escape(jsOr(connected.error) { "Contrato del catálogo conectado y verificado." })) + " No se importan perfiles numéricos ni se crean ejercicios.</p>\n  </section>")
        }
        planPreview = fun(): dynamic {
            val p: dynamic = config()
            val s: dynamic = getStore()
            if (truth((!truth(p.active)))) {
                return ("<p class=\"ar-notice3872\">El calendario Asterion está desactivado; se conserva la planificación original.</p>")
            }
            val d: dynamic = day()
            val cat: dynamic = categoryToday(d)
            val src: dynamic = provider()
            val dt: dynamic = R.validProfile(p)
            if (truth((!truth(dt.ok)))) {
                return ("<p class=\"ar-notice3872\">Configuración incompleta: " + textOf(escape(dt.errors.join(", "))) + "</p>")
            }
            if (truth((cat === "descanso"))) {
                return ("<p class=\"ar-notice3872\">Hoy es día de descanso en el calendario Asterion. Sin propuestas.</p>")
            }
            if (truth((!truth(src.ref)))) {
                return ("<p class=\"ar-notice3872\">" + textOf(escape(src.error)) + "</p>")
            }
            if (truth(jsOr(jsOr(jsOr(onPresent(global_state) { value87 -> onPresent(value87.profile) { value88 -> value88.limits } }) { jsAnd(onPresent(global_state) { value89 -> onPresent(value89.profile) { value90 -> onPresent(value90.health) { value91 -> value91.pain } } }) { (global_state.profile.health.pain !== "none") } }) { onPresent(global_state) { value92 -> onPresent(value92.profile) { value93 -> onPresent(value93.health) { value94 -> value94.acute } } } }) { onPresent(global_state) { value95 -> onPresent(value95.profile) { value96 -> onPresent(value96.health) { value97 -> value97.special } } } })) {
                return "<p class=\"ar-notice3872\">Restricciones del perfil Hunter: se bloquea esta propuesta automática hasta revisión.</p>"
            }
            val pinnedBefore: dynamic = global_JSON.stringify(jsOr(s.rules.pins[d]) { null })
            val `out`: dynamic = dynamicValue(jsOr(root.AsterionUnified3873) { R }).generate(jsObject { obj98 ->
                obj98["catalog"] = src.ref
                obj98["profile"] = p
                obj98["state"] = s.rules
                obj98["today"] = d
            })
            if (truth((global_JSON.stringify(jsOr(s.rules.pins[d]) { null }) !== pinnedBefore))) {
                saveState()
            }
            return ("<p class=\"ar-notice3872\">" + textOf(escape(`out`.reason)) + " " + textOf(escape(R.labels[R.categories.indexOf(cat)])) + " · " + textOf(escape(d)) + "</p>\n   " + textOf(`out`.items.map((fun(v: dynamic): dynamic {
                val key: dynamic = jsAdd(jsAdd(d, "|"), v.id)
                val st: dynamic = jsOr(onPresent(s.rules.outcomes[key]) { value100 -> value100.status }) { "PENDING" }
                return ("<div class=\"ar-entry3872\"><span><b>" + textOf(escape(jsOr(v.label) { v.id })) + "</b><small>" + textOf(escape(v.id)) + " · nivel " + textOf(v.level) + " · " + textOf(escape(st)) + "</small></span><div><button type=\"button\" class=\"btn ghost\" onclick=\"AsterionBridge3872.result('" + textOf(escape(v.id)) + "','COMPLETED')\">Completada</button> <button type=\"button\" class=\"btn ghost\" onclick=\"AsterionBridge3872.result('" + textOf(escape(v.id)) + "','SKIPPED')\">Omitida</button></div></div>")
            })).join("")) + "\n   " + textOf((if (truth(onPresent(`out`.review) { value101 -> value101.checkpoint })) ("<section class=\"ar-offer3873\"><b>Revisión técnica · día " + textOf(`out`.review.checkpoint) + "</b><p>" + textOf(escape(`out`.review.why)) + "</p>" + textOf(dynamicValue(jsOr(`out`.review.proposals) { js("[]") }).map((fun(x: dynamic): dynamic {
                return ("<div class=\"ar-offer3873\"><strong>" + textOf(escape(jsOr(onPresent(src.ref.byId(x.id)) { value104 -> value104.label }) { x.id })) + "</strong><small>De nivel " + textOf(escape(onPresent(src.ref.byId(x.from)) { value105 -> value105.level })) + " a nivel " + textOf(escape(x.level)) + " · Propuesta, no asignada</small><p>" + textOf(escape(x.why)) + "</p></div>")
            })).join("")) + "</section>") else "")) + "")
        }
        result = fun(id: dynamic, status: dynamic): dynamic {
            val d: dynamic = day()
            val s: dynamic = getStore()
            val catalog: dynamic = provider()
            if (truth(jsOr((!truth(s))) { (!truth(catalog.ref)) })) {
                return false
            }
            val preview: dynamic = dynamicValue(jsOr(root.AsterionUnified3873) { R }).generate(jsObject { obj108 ->
                obj108["catalog"] = catalog.ref
                obj108["profile"] = config()
                obj108["state"] = s.rules
                obj108["today"] = d
            })
            if (truth((!truth(preview.items.some((fun(x: dynamic): dynamic {
                return (x.id === id)
            })))))) {
                return false
            }
            try {
                R.recordOutcome(s.rules, jsObject { obj109 ->
                    obj109["date"] = d
                    obj109["variantId"] = id
                    obj109["category"] = preview.category
                    obj109["status"] = status
                })
                saveState()
                if (truth((js("typeof render") === "function"))) {
                    global_render()
                }
                return true
            } catch (e: dynamic) {
                if (truth((js("typeof toast") === "function"))) {
                    global_toast(e.message)
                }
                return false
            }
            return absent
        }
        report = fun(): dynamic {
            val s: dynamic = getStore()
            val d: dynamic = day()
            val c: dynamic = categoryToday(d)
            val sum: dynamic = R.completionSummary(s.rules)
            val g: dynamic = R.globalReview(s.rules, d)
            return ("<section class=\"card ar-summary3872\"><span class=\"eyebrow\">ASTERION · SISTEMA DETERMINISTA</span><h3>Calendario y revisión por categoría</h3>\n   <p>Hoy: <b>" + textOf(escape(jsOr(R.labels[R.categories.indexOf(c)]) { "Descanso" })) + "</b>. Propuestas guardadas por día; descanso sin actividades.</p>\n   " + textOf(planPreview()) + "\n   " + textOf((if (truth(root.AsterionUnified3873)) ("<p class=\"ar-note3873\">Motor 38.73: valoración única por sesión y categoría; las revisiones de días 7, 14 y 21 muestran propuestas que requieren aceptación y metadatos certificados.</p>") else "")) + "\n   <p>Participación: " + textOf((if (truth((sum.percent === null))) "Sin datos suficientes" else jsAdd(sum.percent, " %"))) + " · " + textOf(sum.completed) + " completadas · " + textOf(sum.skipped) + " omitidas · " + textOf(sum.pending) + " pendientes.</p>\n   <div class=\"ar-review3872\">" + textOf(R.categories.map((fun(category: dynamic, i: dynamic): dynamic {
                val v: dynamic = R.decision(s.rules, category, d)
                val u: dynamic = onPresent(root.AsterionUnified3873) { value114 -> value114.milestone(s.rules, category, d) }
                return ("<details><summary>" + textOf(R.labels[i]) + " · " + textOf(escape(v.action)) + "</summary><p>" + textOf(escape(v.why)) + "</p><small>" + textOf((if (truth(u)) ("Revisión unificada: " + textOf(escape(u.action)) + " · " + textOf(escape(u.why)) + "") else "Motor de revisión no disponible.")) + "</small>" + textOf((if (truth(onPresent(u) { value115 -> value115.checkpoint })) ("<p class=\"ar-note3873\"><b>Día " + textOf(u.checkpoint) + " · " + textOf(escape(u.action)) + "</b>. " + textOf(escape(u.why)) + " La variante más compleja no se asigna automáticamente.</p>") else "")) + "</details>")
            })).join("")) + "</div>\n   <p class=\"muted\">Revisión global: " + textOf(g.count) + " valoraciones, esfuerzo medio " + textOf(nullableOr(g.average) { "—" }) + "; " + textOf(escape(g.reason)) + "</p>\n  </section>")
        }
        syncNewQuestionnaire = fun(dt: dynamic): dynamic {
            val s: dynamic = getStore()
            val log: dynamic = onPresent(global_state) { value118 -> onPresent(value118.logs) { value119 -> value119[dt] } }
            if (truth(jsOr(jsOr((!truth(s))) { (!truth(onPresent(log) { value120 -> value120.complete })) }) { (!truth(jsAnd(jsAnd(global_Number.isInteger(global_Number(log.rpe))) { jsAtLeast(global_Number(log.rpe), 1) }) { jsAtMost(global_Number(log.rpe), 10) })) })) {
                return false
            }
            val `actual`: dynamic = dynamicValue(jsOr(log.items) { js("[]") }).filter((fun(x: dynamic): dynamic {
                return jsAnd(global_Array.isArray(x["actual"])) { x["actual"].some((fun(y: dynamic): dynamic {
                    return (onPresent(y) { value121 -> value121.done } === true)
                })) }
            }))
            val cats: dynamic = jsArray { array122 ->
                appendAll(array122, jsConstruct(global_Set, arrayOf<dynamic>(`actual`.map((fun(x: dynamic): dynamic {
                    return onPresent(portable) { value125 -> onPresent(value125.exerciseMeta(x.id)) { value126 -> value126.category } }
                })).filter(global_Boolean))))
            }
            val `value`: dynamic = global_Number(log.rpe)
            val unified: dynamic = root.AsterionUnified3873
            if (truth(unified)) {
                unified.recordSession(s.rules, jsObject { obj127 ->
                    obj127["date"] = dt
                    obj127["log"] = log
                    obj127["categoryForId"] = (fun(id: dynamic): dynamic {
                        return onPresent(portable) { value128 -> onPresent(value128.exerciseMeta(id)) { value129 -> value129.category } }
                    })
                })
            } else {
                if (truth((cats.length === 1))) {
                    R.effort(s.rules, jsObject { obj130 ->
                        obj130["date"] = dt
                        obj130["category"] = cats[0]
                        obj130["value"] = `value`
                        obj130["completed"] = true
                        obj130["global"] = true
                    })
                } else {
                    R.globalEffort(s.rules, jsObject { obj131 ->
                        obj131["date"] = dt
                        obj131["value"] = `value`
                    })
                }
            }
            saveState()
            return true
        }
        R = root.AsterionRules3872
        if (truth(jsOr((!truth(R))) { root.AsterionBridge3872 })) {
            return absent
        }
        E = root.Engine
        portable = root.HunterPortable3868
        escape = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        W = jsonValue("[\"Lunes\",\"Martes\",\"Miércoles\",\"Jueves\",\"Viernes\",\"Sábado\",\"Domingo\"]")
        if (truth(onPresent(root.HunterDailyProgress3870) { value132 -> value132.submit })) {
            val prev: dynamic = root.HunterDailyProgress3870
            root.HunterDailyProgress3870 = global_Object.freeze(jsObject { obj135 ->
                copyFields(obj135, prev)
                obj135["submit"] = (fun(ev: dynamic, dt: dynamic): dynamic {
                    val ok: dynamic = prev.submit(ev, dt)
                    if (truth(ok)) {
                        syncNewQuestionnaire(dt)
                    }
                    return ok
                })
            })
        }
        if (truth((js("typeof profileForm") === "function"))) {
            val prev: dynamic = global_profileForm
            global_profileForm = (fun(): dynamic {
                val receiver138: dynamic = js("this")
                val callArgs139: dynamic = js("arguments")
                val html: dynamic = prev.apply(receiver138, callArgs139)
                val at: dynamic = html.lastIndexOf("</form>")
                return (if (truth(jsLess(at, 0))) html else jsAdd(jsAdd(html.slice(0, at), settingsMarkup()), html.slice(at)))
            })
        }
        if (truth((js("typeof plan") === "function"))) {
            val prev: dynamic = global_plan
            global_plan = (fun(): dynamic {
                val receiver140: dynamic = js("this")
                val callArgs141: dynamic = js("arguments")
                val s: dynamic = getStore()
                val ready: dynamic = global_Boolean(jsAnd(config().active) { provider().ref })
                return (if (truth(ready)) report() else jsAdd(report(), prev.apply(receiver140, callArgs141)))
            })
        }
        root.AsterionBridge3872 = global_Object.freeze(jsObject { obj144 ->
            obj144["version"] = "38.73"
            obj144["change"] = change
            obj144["toggleEquipment"] = toggleEquipment
            obj144["addEquipment"] = addEquipment
            obj144["config"] = config
            obj144["provider"] = provider
            obj144["categoryToday"] = categoryToday
            obj144["planPreview"] = planPreview
            obj144["report"] = report
            obj144["result"] = result
            obj144["syncNewQuestionnaire"] = syncNewQuestionnaire
            obj144["audit"] = (fun(): dynamic {
                return jsObject { obj145 ->
                    obj145["catalogConnected"] = global_Boolean(provider().ref)
                    obj145["categories"] = R.categories.length
                    obj145["configured"] = config().active
                    obj145["pins"] = global_Object.keys(jsOr(onPresent(getStore()) { value146 -> onPresent(value146.rules) { value147 -> value147.pins } }) { js("({})") }).length
                }
            })
        })
        return absent
    }))(global_window)
}
