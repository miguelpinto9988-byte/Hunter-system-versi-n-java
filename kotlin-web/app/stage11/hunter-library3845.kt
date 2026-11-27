@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterlibrary3845

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("ownLibraryView14") private external var global_ownLibraryView14: dynamic
@JsName("gymView23") private external var global_gymView23: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("gymBrowse23") private external var global_gymBrowse23: dynamic
@JsName("ownLibrary11") private external var global_ownLibrary11: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Warm25") private external var global_Warm25: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("gymLevel23") private external var global_gymLevel23: dynamic
@JsName("ownLevel14") private external var global_ownLevel14: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterLibrary3845() {
    dynamicValue((fun(root: dynamic): dynamic {
        var D: dynamic = absent
        var E: dynamic = absent
        var G: dynamic = absent
        var O: dynamic = absent
        var cats: dynamic = absent
        var windows: dynamic = absent
        var selected: dynamic = absent
        var duration: dynamic = absent
        var serial: dynamic = absent
        var `data`: dynamic = absent
        var info: dynamic = absent
        var labels: dynamic = absent
        var keys: dynamic = absent
        var scope: dynamic = absent
        var numberHash: dynamic = absent
        var timeSet: dynamic = absent
        var pool: dynamic = absent
        var selectExercises: dynamic = absent
        var generatePlan: dynamic = absent
        var plans: dynamic = absent
        var escText: dynamic = absent
        var badge: dynamic = absent
        var tile: dynamic = absent
        var fill: dynamic = absent
        var toggle: dynamic = absent
        var chooseTime: dynamic = absent
        var generate: dynamic = absent
        var reset: dynamic = absent
        scope = fun(): dynamic {
            if (truth(jsOr((js("typeof tab") === "undefined")) { (global_tab !== "routines") })) {
                return null
            }
            if (truth(jsAnd((js("typeof gymBrowse23") !== "undefined")) { global_gymBrowse23 })) {
                return "gym"
            }
            if (truth(jsAnd((js("typeof ownLibrary11") !== "undefined")) { global_ownLibrary11 })) {
                return "offline"
            }
            return null
        }
        numberHash = fun(x: dynamic): dynamic {
            var h: dynamic = 2166136261.0
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration7 = true
                while (true) {
                    if (!firstIteration7) {
                        run {
                            val previous8: dynamic = numeric(i)
                            i = jsAdd(previous8, 1)
                        }
                    }
                    firstIteration7 = false
                    if (!truth(jsLess(i, x.length))) break
                    h = global_Math.imul(jsBitXor(h, x.charCodeAt(i)), 16777619)
                }
            }
            return jsUnsignedShift(h, 0)
        }
        timeSet = fun(): dynamic {
            return jsOr(windows.find((fun(w: dynamic): dynamic {
                return (w.id === duration)
            }))) { null }
        }
        pool = fun(profile: dynamic, category: dynamic, mode: dynamic): dynamic {
            val list: dynamic = D.pool(profile, category.kind)
            return list.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(e) { (if (truth((mode === "gym"))) jsOr((!truth((!truth(e.gymOnly23))))) { G.power(e) } else (!truth(e.gymOnly23))) }) { G.matches(e, category.id) }) { jsOr((mode !== "gym")) { dynamicValue(jsOr(e.gymRequirements23) { js("[]") }).every((fun(k: dynamic): dynamic {
                    return dynamicValue(jsOr(profile.gymEquipment23) { js("[]") }).includes(k)
                })) } }
            }))
        }
        selectExercises = fun(entries: dynamic, seed: dynamic, count: dynamic, ctx: dynamic): dynamic {
            val scored: dynamic = entries.map((fun(e: dynamic): dynamic {
                return jsObject { obj16 ->
                    obj16["e"] = e
                    obj16["rank"] = numberHash(jsAdd(jsAdd(seed, "|"), e.id))
                }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract(a.rank, b.rank)
            }))
            val picked: dynamic = js("[]")
            val used: dynamic = jsConstruct(global_Set, js("[]"))
            val groups: dynamic = jsConstruct(global_Map, js("[]"))
            while (truth(jsLess(picked.length, count))) {
                val possible: dynamic = scored.filter((fun(x: dynamic): dynamic {
                    return (!truth(used.has(G.family(x.e))))
                }))
                val allowed: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(G.selectionPool(possible.map((fun(x: dynamic): dynamic {
                    return x.e
                })), picked, count, ctx)))
                val available: dynamic = possible.filter((fun(x: dynamic): dynamic {
                    return allowed.has(x.e)
                }))
                if (truth((!truth(available.length)))) {
                    break
                }
                available.sort((fun(a: dynamic, b: dynamic): dynamic {
                    return jsOr(jsSubtract(jsAdd(jsSubtract(G.score(b.e, ctx.p, ctx.kind, picked), G.score(a.e, ctx.p, ctx.kind, picked)), jsMultiply(jsOr(groups["get"](a.e.group)) { 0 }, 14)), jsMultiply(jsOr(groups["get"](b.e.group)) { 0 }, 14))) { jsSubtract(a.rank, b.rank) }
                }))
                val e: dynamic = available[0].e
                picked.push(e)
                used.add(G.family(e))
                groups["set"](e.group, jsAdd(jsOr(groups["get"](e.group)) { 0 }, 1))
            }
            return picked
        }
        generatePlan = fun(mode: dynamic, category: dynamic, level: dynamic, variant: dynamic, range: dynamic, epoch: dynamic, p: dynamic): dynamic {
            val adjusted: dynamic = jsObject { obj19 ->
                copyFields(obj19, p)
                obj19["difficulty"] = keys[level]
                obj19["minutes"] = range.high
            }
            val candidates: dynamic = pool(adjusted, category, mode)
            if (truth((!truth(candidates.length)))) {
                return null
            }
            val seed: dynamic = dynamicValue(arrayOf<dynamic>(epoch, mode, category.id, level, variant)).join("|")
            val logs: dynamic = (if (truth((js("typeof state") === "undefined"))) js("({})") else jsOr(global_state.logs) { js("({})") })
            val date: dynamic = E.dateKey()
            val status: dynamic = D.gate(adjusted)
            val feedback: dynamic = D.feedback(logs, date)
            val ctx: dynamic = G.context(adjusted, category.kind, category.id, jsObject { obj20 ->
                obj20["logs"] = logs
                obj20["date"] = date
                obj20["status"] = status
                obj20["feedback"] = feedback
            })
            val high: dynamic = (if (truth((range.id === "long"))) 30 else range.high)
            val low: dynamic = range.low
            val maximum: dynamic = global_Math.min(candidates.length, (if (truth((range.id === "short"))) 5 else 7))
            var winner: dynamic = dynamicValue(null)
            if (true) {
                var count: dynamic = absent
                count = 2
                var firstIteration21 = true
                while (true) {
                    if (!firstIteration21) {
                        run {
                            val previous22: dynamic = numeric(count)
                            count = jsAdd(previous22, 1)
                        }
                    }
                    firstIteration21 = false
                    if (!truth(jsAtMost(count, maximum))) break
                    val exercises: dynamic = selectExercises(candidates, seed, count, ctx)
                    if (truth(jsLess(exercises.length, count))) {
                        break
                    }
                    if (truth(jsAnd((mode === "gym")) { (!truth(exercises.some((fun(e: dynamic): dynamic {
                        return e.gymOnly23
                    })))) })) {
                        continue
                    }
                    if (true) {
                        var sets: dynamic = absent
                        sets = 1
                        var firstIteration23 = true
                        while (true) {
                            if (!firstIteration23) {
                                run {
                                    val previous24: dynamic = numeric(sets)
                                    sets = jsAdd(previous24, 1)
                                }
                            }
                            firstIteration23 = false
                            if (!truth(jsAtMost(sets, 3))) break
                            val items: dynamic = exercises.map((fun(e: dynamic): dynamic {
                                val base: dynamic = O.dose(e, adjusted, category.kind)
                                val item: dynamic = jsObject { obj25 ->
                                    obj25["id"] = e.id
                                    obj25["sets"] = global_Math.max(1, global_Math.min(sets, jsOr(base.sets) { 1 }))
                                    obj25["target"] = global_Math.max(1, global_Math.min(jsOr(base.target) { 8 }, (if (truth((e.unit === "sec"))) 50 else 15)))
                                    obj25["rest"] = global_Math.max(30, jsOr(global_Number(base.rest)) { 60 })
                                    obj25["libraryGen3845"] = true
                                }
                                val roleDose: dynamic = G.adjustDose(e, adjusted, category.kind, item, ctx)
                                return root.HunterCatalog46.cap(e, adjusted, roleDose, ctx)
                            }))
                            val minutes: dynamic = global_Warm25.minutes(jsObject { obj26 ->
                                obj26["items"] = items
                            }, adjusted)
                            if (truth(jsOr(jsLess(minutes, low)) { jsMore(minutes, high) })) {
                                continue
                            }
                            val aim: dynamic = (if (truth((range.id === "short"))) 8 else (if (truth((range.id === "medium"))) 15 else 25))
                            val score: dynamic = jsAdd(jsMultiply(global_Math.abs(jsSubtract(aim, minutes)), 10), jsMultiply(global_Math.abs(jsSubtract(count, (if (truth((range.id === "short"))) 3 else 5))), 2))
                            if (truth(jsOr((!truth(winner))) { jsLess(score, winner.score) })) {
                                winner = jsObject { obj27 ->
                                    obj27["items"] = items
                                    obj27["minutes"] = minutes
                                    obj27["score"] = score
                                }
                            }
                        }
                    }
                }
            }
            if (truth((!truth(winner)))) {
                return null
            }
            val requirements: dynamic = jsArray { array28 ->
                appendAll(array28, jsConstruct(global_Set, arrayOf<dynamic>(winner.items.flatMap((fun(x: dynamic): dynamic {
                    return jsOr(onPresent(E.byId(x.id)) { value29 -> value29.requirements }) { js("[]") }
                })))))
            }
            val gymRequirements23: dynamic = jsArray { array30 ->
                appendAll(array30, jsConstruct(global_Set, arrayOf<dynamic>(winner.items.flatMap((fun(x: dynamic): dynamic {
                    return jsOr(onPresent(E.byId(x.id)) { value31 -> value31.gymRequirements23 }) { js("[]") }
                })))))
            }
            val names: dynamic = (if (truth((mode === "gym"))) "Gimnasio" else "Sin conexión")
            return jsObject { obj32 ->
                obj32["id"] = ("hlib3845-" + textOf(mode) + "-" + textOf(epoch) + "-" + textOf(category.id) + "-" + textOf(level) + "-" + textOf(variant) + "")
                obj32["name"] = ("" + textOf(category.name) + " · " + textOf(labels[level]) + " · " + textOf(names) + " " + textOf(jsAdd(variant, 1)) + "")
                obj32["category"] = (if (truth((category.kind === "strength"))) "Fuerza" else (if (truth((category.kind === "cardio"))) "Cardio" else "Movilidad"))
                obj32["focus3845"] = category.id
                obj32["mode3845"] = mode
                obj32["level"] = level
                obj32["items"] = winner.items
                obj32["requirements"] = requirements
                obj32["gymOnly23"] = (mode === "gym")
                obj32["gymRequirements23"] = gymRequirements23
                obj32["minutes"] = winner.minutes
                obj32["impact"] = (if (truth(winner.items.some((fun(x: dynamic): dynamic {
                    return G.impact(E.byId(x.id))
                })))) "Con saltos" else "Sin saltos")
                obj32["family"] = "dynamic"
                obj32["extension"] = "standard"
                obj32["goalGroups"] = jsonValue("[\"fitness\",\"habit\",\"movement\",\"build\",\"athletic\",\"adapted\"]")
                obj32["safe381"] = true
                obj32["description"] = "Secuencia generada desde las demostraciones 2D locales, filtrada por categoría, equipo y restricciones del perfil. Estimación de tiempo con descansos. No altera las misiones del día."
            }
        }
        plans = fun(mode: dynamic, chosen: dynamic, w: dynamic, epoch: dynamic): dynamic {
            val profile: dynamic = onPresent(global_state) { value35 -> value35.profile }
            if (truth(jsOr((!truth(profile))) { (!truth(D.gate(profile).ok)) })) {
                return jsonValue("{\"routines\":[],\"fail\":\"Revisa primero los datos o restricciones del perfil.\"}")
            }
            if (truth(jsAnd((mode === "gym")) { (profile.equipment !== "gym") })) {
                return jsonValue("{\"routines\":[],\"fail\":\"Para generar rutinas de gimnasio, configura primero el equipo de gimnasio en Perfil.\"}")
            }
            val result: dynamic = js("[]")
            val missing: dynamic = js("[]")
            if (true) {
                var level: dynamic = absent
                level = 0
                var firstIteration36 = true
                while (true) {
                    if (!firstIteration36) {
                        run {
                            val previous37: dynamic = numeric(level)
                            level = jsAdd(previous37, 1)
                        }
                    }
                    firstIteration36 = false
                    if (!truth(jsLess(level, 3))) break
                    if (true) {
                        val iterator38: dynamic = iteratorOf(chosen)
                        while (true) {
                            val next39: dynamic = iterator38.next()
                            if (truth(next39.done)) break
                            var type: dynamic = absent
                            type = next39.value
                            val cat: dynamic = cats.find((fun(c: dynamic): dynamic {
                                return (c.id === type)
                            }))
                            if (truth((!truth(cat)))) {
                                continue
                            }
                            var produced: dynamic = 0
                            if (true) {
                                var v: dynamic = absent
                                v = 0
                                var firstIteration40 = true
                                while (true) {
                                    if (!firstIteration40) {
                                        run {
                                            val previous41: dynamic = numeric(v)
                                            v = jsAdd(previous41, 1)
                                        }
                                    }
                                    firstIteration40 = false
                                    if (!truth(jsLess(v, 2))) break
                                    val r: dynamic = generatePlan(mode, cat, level, v, w, epoch, profile)
                                    if (truth(r)) {
                                        result.push(r)
                                        run {
                                            val previous42: dynamic = numeric(produced)
                                            produced = jsAdd(previous42, 1)
                                        }
                                    }
                                }
                            }
                            if (truth((!truth(produced)))) {
                                missing.push(jsAdd(jsAdd(jsAdd(cat.name, " ("), labels[level]), ")"))
                            }
                        }
                    }
                }
            }
            return jsObject { obj43 ->
                obj43["routines"] = result
                obj43["missing"] = missing
            }
        }
        badge = fun(): dynamic {
            val w: dynamic = timeSet()
            val mode: dynamic = scope()
            val generated: dynamic = (if (truth(mode)) `data`[mode] else null)
            val status: dynamic = (if (truth(jsAnd(mode) { info[mode] })) ("<p class=\"library-status3845\" role=\"status\">" + textOf(escText(info[mode])) + "</p>") else "")
            return ("<section class=\"library-generator3845 card\" aria-label=\"Generador dinámico de rutinas\">\n      <div class=\"library-header3845\"><div><span class=\"eyebrow\">HUNTER SYSTEM · GENERADOR DINÁMICO</span><h2>Diseña tu próxima rutina</h2></div><span class=\"library-counter3845\">1200 demos 2D</span></div>\n      <p class=\"library-hint3845\">Elige los tipos de ejercicio y el tiempo. Los entrenamientos se generan por separado para cada categoría y para los tres niveles existentes.</p>\n      <div class=\"library-label3845\">01 · GRUPOS DE ENTRENAMIENTO <small>Selección múltiple</small></div>\n      <div class=\"library-chips3845\" role=\"group\" aria-label=\"Seleccionar categorías\">\n        " + textOf(cats.map((fun(c: dynamic): dynamic {
                return ("<button type=\"button\" aria-label=\"" + textOf(c.name) + "\" aria-pressed=\"" + textOf(selected.has(c.id)) + "\" class=\"library-chip3845 " + textOf(c.className) + " " + textOf((if (truth(selected.has(c.id))) "on" else "")) + "\" onclick=\"HunterLibrary3845.toggle('" + textOf(c.id) + "',this)\"><span class=\"library-orb3845\" aria-hidden=\"true\">" + textOf(c.symbol) + "</span><span>" + textOf(c.name) + "</span></button>")
            })).join("")) + "\n      </div>\n      <div class=\"library-label3845\">02 · DURACIÓN DE LA SESIÓN <small>Elige una</small></div>\n      <div class=\"library-times3845\" role=\"group\" aria-label=\"Duración estimada\">\n      " + textOf(windows.map((fun(o: dynamic, i: dynamic): dynamic {
                return ("<button type=\"button\" class=\"library-time3845 " + textOf((if (truth((duration === o.id))) "on" else "")) + "\" aria-pressed=\"" + textOf((duration === o.id)) + "\" onclick=\"HunterLibrary3845.time('" + textOf(o.id) + "',this)\"><span aria-hidden=\"true\">◷</span><b>" + textOf(o.label) + "</b>" + textOf((if (truth((i === 2))) "<small>20–30 min preferidos</small>" else "")) + "</button>")
            })).join("")) + "</div>\n      <button type=\"button\" class=\"btn primary full library-generate3845\" onclick=\"HunterLibrary3845.generate()\">✧ Generar nuevas rutinas</button>\n      " + textOf((if (truth(generated)) ("<div class=\"library-generated3845\"><b>✓ " + textOf(generated.length) + " rutinas nuevas</b><span>Se conservan únicamente mientras la aplicación está abierta. Los niveles se encuentran debajo.</span><button type=\"button\" class=\"text-button\" onclick=\"HunterLibrary3845.reset()\">Ver biblioteca original</button></div>") else "")) + "\n      " + textOf(status) + "\n    </section>")
        }
        tile = fun(r: dynamic): dynamic {
            val names: dynamic = r.items.slice(0, 4).map((fun(x: dynamic): dynamic {
                return jsOr(onPresent(E.byId(x.id)) { value48 -> value48.name }) { x.id }
            }))
            val compatible: dynamic = global_Adapt.routineCompatible(r, global_GoalPlans.adjusted(global_state.profile))
            val active: dynamic = (if (truth(compatible)) "Lista para tu perfil" else "Revisa compatibilidad y equipo en Perfil")
            return ("<article class=\"card routine-card18 library-generated-card3845\"><div class=\"row between\"><span class=\"eyebrow\">" + textOf(escText(jsOr(onPresent(cats.find((fun(c: dynamic): dynamic {
                return (c.id === r.focus3845)
            }))) { value49 -> value49.name }) { r.category })) + " · " + textOf(escText((if (truth((r.mode3845 === "gym"))) "GIMNASIO" else "SIN CONEXIÓN"))) + "</span><span class=\"badge\">≈ " + textOf(r.minutes) + " min</span></div><h3>" + textOf(escText(r.name)) + "</h3><ul class=\"routine-preview18\">" + textOf(names.map((fun(name: dynamic): dynamic {
                return ("<li><span>" + textOf(escText(name)) + "</span></li>")
            })).join("")) + "</ul><p class=\"footnote\">" + textOf(r.items.length) + " ejercicios · " + textOf(r.items.reduce((fun(s: dynamic, x: dynamic): dynamic {
                return jsAdd(s, x.sets)
            }), 0)) + " series · " + textOf(escText(active)) + "</p><button type=\"button\" class=\"btn primary full\" onclick=\"routineDetail('" + textOf(r.id) + "')\">Abrir secuencia 2D</button></article>")
        }
        fill = fun(html: dynamic, mode: dynamic): dynamic {
            if (truth((!truth(`data`[mode])))) {
                return html
            }
            val box: dynamic = global_document.createElement("div")
            box.innerHTML = html
            val node: dynamic = box.querySelector((if (truth((mode === "gym"))) ".gym-routines23" else ".routine-results-14"))
            if (truth((!truth(node)))) {
                return html
            }
            val level: dynamic = (if (truth((mode === "gym"))) (if (truth((js("typeof gymLevel23") === "number"))) global_gymLevel23 else jsOr(onPresent(root.TrainingProgression19) { value54 -> run { val owner56: dynamic = value54; onPresent(owner56.level) { method55 -> invokeWith(method55, owner56, arrayOf<dynamic>(jsOr(global_state.profile) { js("({})") })) } } }) { 0 }) else (if (truth((js("typeof ownLevel14") === "number"))) global_ownLevel14 else global_Math.max(0, keys.indexOf(onPresent(global_state.profile) { value57 -> value57.difficulty }))))
            val list: dynamic = `data`[mode].filter((fun(r: dynamic): dynamic {
                return (r.level === level)
            }))
            node.innerHTML = jsOr(list.map(tile).join("")) { "<div class=\"card empty\"><h3>No hay ejercicios compatibles con estos filtros</h3><p>Prueba otra categoría o duración, o revisa las restricciones y el equipo registrado en Perfil. No se sustituye una categoría por otra.</p></div>" }
            val oldFilters: dynamic = box.querySelector((if (truth((mode === "gym"))) "select[onchange*=\"gymFamily23\"]" else ".routine-filters-14"))
            onPresent((if (truth((mode === "gym"))) onPresent(oldFilters) { value60 -> value60.closest("label") } else oldFilters)) { value61 -> value61.remove() }
            val subtitle: dynamic = box.querySelector(".page-heading p.muted")
            if (truth(subtitle)) {
                subtitle.textContent = "Rutinas nuevas generadas localmente; las combinaciones no se guardan. Selecciona abajo Básico, Intermedio o Avanzado."
            }
            box.querySelectorAll(".routine-level-tabs button").forEach((fun(button: dynamic, i: dynamic): dynamic {
                val small: dynamic = button.querySelector("small")
                if (truth(small)) {
                    small.textContent = jsAdd(`data`[mode].filter((fun(r: dynamic): dynamic {
                        return (r.level === i)
                    })).length, " nuevas")
                }
                return absent
            }))
            return box.innerHTML
        }
        toggle = fun(id: dynamic, el: dynamic): dynamic {
            if (truth((!truth(cats.some((fun(c: dynamic): dynamic {
                return (c.id === id)
            })))))) {
                return absent
            }
            if (truth(selected.has(id))) {
                selected.delete(id)
            } else {
                selected.add(id)
            }
            onPresent(el) { value77 -> value77.setAttribute("aria-pressed", global_String(selected.has(id))) }
            onPresent(el) { value78 -> value78.classList.toggle("on", selected.has(id)) }
            return absent
        }
        chooseTime = fun(id: dynamic, el: dynamic): dynamic {
            if (truth((!truth(windows.some((fun(w: dynamic): dynamic {
                return (w.id === id)
            })))))) {
                return absent
            }
            duration = id
            val buttons: dynamic = onPresent(el) { value82 -> onPresent(value82.closest(".library-times3845")) { value83 -> value83.querySelectorAll("button") } }
            onPresent(buttons) { value84 -> value84.forEach((fun(b: dynamic): dynamic {
                val on: dynamic = (b === el)
                b.classList.toggle("on", on)
                b.setAttribute("aria-pressed", global_String(on))
                return absent
            })) }
            return absent
        }
        generate = fun(): dynamic {
            val mode: dynamic = scope()
            if (truth((!truth(mode)))) {
                return absent
            }
            if (truth((!truth(selected.size)))) {
                info[mode] = "Elige al menos un grupo de entrenamiento."
                return global_render()
            }
            val w: dynamic = timeSet()
            if (truth((!truth(w)))) {
                info[mode] = "Selecciona uno de los tres intervalos de duración."
                return global_render()
            }
            val generated: dynamic = plans(mode, jsArray { array91 ->
                appendAll(array91, selected)
            }, w, run {
                val previous92: dynamic = numeric(serial)
                val assigned93: dynamic = jsAdd(previous92, 1)
                serial = assigned93
                assigned93
            })
            if (truth(generated.fail)) {
                info[mode] = generated.fail
                return global_render()
            }
            if (truth((!truth(generated.routines.length)))) {
                info[mode] = "Sin rutinas compatibles con esta selección. Revisa el equipo, las restricciones o el tiempo."
                return global_render()
            }
            `data`[mode] = generated.routines
            info[mode] = (if (truth(onPresent(generated.missing) { value102 -> value102.length })) jsAdd(jsAdd("Algunas combinaciones no tienen ejercicios compatibles: ", generated.missing.join(", ")), ". No se han sustituido por otros grupos.") else "Rutinas listas. Selecciona el nivel debajo para verlas.")
            global_render()
            return absent
        }
        reset = fun(): dynamic {
            val mode: dynamic = scope()
            if (truth((!truth(mode)))) {
                return absent
            }
            `data`[mode] = dynamicValue(null)
            info[mode] = ""
            global_render()
            return absent
        }
        if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(root.HunterLibrary3845) { (js("typeof routines") !== "function") }) { (js("typeof allRoutines") !== "function") }) { (js("typeof ownLibraryView14") !== "function") }) { (js("typeof gymView23") !== "function") }) { (!truth(root.HunterDynamic381)) })) {
            return absent
        }
        D = root.HunterDynamic381
        E = root.Engine
        G = root.HunterGroups49
        O = root.OwnRoutines18
        cats = jsonValue("[{\"id\":\"upper\",\"name\":\"Tren superior\",\"className\":\"upper\",\"kind\":\"strength\",\"group\":[\"Pecho\",\"Espalda\",\"Hombros\",\"Brazos\"],\"symbol\":\"▲\"},{\"id\":\"lower\",\"name\":\"Tren inferior\",\"className\":\"lower\",\"kind\":\"strength\",\"group\":[\"Piernas\",\"Cadera\"],\"symbol\":\"▼\"},{\"id\":\"core\",\"name\":\"Tren central\",\"className\":\"core\",\"kind\":\"strength\",\"group\":[\"Core\",\"Abdomen\"],\"symbol\":\"◆\"},{\"id\":\"cardio\",\"name\":\"Cardio\",\"className\":\"cardio\",\"kind\":\"cardio\",\"group\":null,\"symbol\":\"♥\"},{\"id\":\"mobility\",\"name\":\"Movilidad\",\"className\":\"mobility\",\"kind\":\"recovery\",\"group\":null,\"symbol\":\"✧\"}]")
        windows = jsonValue("[{\"id\":\"short\",\"label\":\"5–10 min\",\"low\":5,\"high\":10},{\"id\":\"medium\",\"label\":\"10–20 min\",\"low\":10,\"high\":20},{\"id\":\"long\",\"label\":\"Hasta 30 min\",\"low\":5,\"high\":30}]")
        selected = jsConstruct(global_Set, js("[]"))
        duration = dynamicValue(null)
        serial = 0
        `data` = jsonValue("{\"offline\":null,\"gym\":null}")
        info = jsonValue("{\"offline\":\"\",\"gym\":\"\"}")
        labels = jsonValue("[\"Básico\",\"Intermedio\",\"Avanzado\"]")
        keys = jsonValue("[\"basic\",\"intermediate\",\"advanced\"]")
        val originalAll: dynamic = global_allRoutines
        val originalRoutines: dynamic = global_routines
        val originalOffline: dynamic = global_ownLibraryView14
        val originalGym: dynamic = global_gymView23
        val clone: dynamic = (fun(o: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(o))
        })
        escText = (fun(s: dynamic): dynamic {
            return global_String(s).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        global_allRoutines = (fun(): dynamic {
            val receiver66: dynamic = js("this")
            val callArgs67: dynamic = js("arguments")
            val base: dynamic = originalAll.apply(receiver66, callArgs67)
            val active: dynamic = scope()
            return (if (truth(jsAnd(active) { `data`[active] })) jsArray { array68 ->
                appendAll(array68, base)
                appendAll(array68, `data`[active])
            } else base)
        })
        global_ownLibraryView14 = (fun(): dynamic {
            val receiver69: dynamic = js("this")
            val callArgs70: dynamic = js("arguments")
            return fill(originalOffline.apply(receiver69, callArgs70), "offline")
        })
        global_gymView23 = (fun(): dynamic {
            val receiver71: dynamic = js("this")
            val callArgs72: dynamic = js("arguments")
            return fill(originalGym.apply(receiver71, callArgs72), "gym")
        })
        global_routines = (fun(): dynamic {
            val receiver73: dynamic = js("this")
            val callArgs74: dynamic = js("arguments")
            val view: dynamic = originalRoutines.apply(receiver73, callArgs74)
            val active: dynamic = scope()
            if (truth((!truth(active)))) {
                return view
            }
            val panel: dynamic = badge()
            val matcher: dynamic = jsRegExp("^<div class=\"notice\" role=\"note\"><b>Generador dinámico V38\\.1<\\/b>[\\s\\S]*?<\\/div>", "")
            return (if (truth(matcher.test(view))) view.replace(matcher, panel) else jsAdd(panel, view))
        })
        root.HunterLibrary3845 = global_Object.freeze(jsObject { obj111 ->
            obj111["version"] = "38.49"
            obj111["cats"] = cats.map((fun(c: dynamic): dynamic {
                return c.id
            }))
            obj111["windows"] = windows.map((fun(w: dynamic): dynamic {
                return w.id
            }))
            obj111["toggle"] = toggle
            obj111["time"] = chooseTime
            obj111["generate"] = generate
            obj111["reset"] = reset
            obj111["status"] = (fun(): dynamic {
                return jsObject { obj112 ->
                    obj112["scope"] = scope()
                    obj112["types"] = jsArray { array113 ->
                        appendAll(array113, selected)
                    }
                    obj112["duration"] = duration
                    obj112["offline"] = jsOr(onPresent(`data`.offline) { value114 -> value114.length }) { 0 }
                    obj112["gym"] = jsOr(onPresent(`data`.gym) { value115 -> value115.length }) { 0 }
                }
            })
            obj111["classify"] = (fun(e: dynamic): dynamic {
                return jsOr(onPresent(G.classify(e)) { value116 -> value116.group }) { null }
            })
            obj111["generatePlan"] = generatePlan
            obj111["plans"] = plans
        })
        return absent
    }))(global_window)
}
