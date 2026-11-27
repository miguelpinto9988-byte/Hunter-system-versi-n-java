@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterclosure3826

import hunter.browser.*

@JsName("String") private external var global_String: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("missionWindow") private external var global_missionWindow: dynamic
@JsName("dayByDate") private external var global_dayByDate: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("showMission") private external var global_showMission: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterClosure3826() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var stats: dynamic = absent
        var escText: dynamic = absent
        var isActive: dynamic = absent
        var isRestricted: dynamic = absent
        var eligible: dynamic = absent
        var markPhase: dynamic = absent
        var prepareChallenge: dynamic = absent
        var availableSwaps: dynamic = absent
        var futureSwaps: dynamic = absent
        var swapPlanned: dynamic = absent
        markPhase = fun(): dynamic {
            val element: dynamic = doc.querySelector("#vectorPhase")
            if (truth(jsOr((!truth(element))) { (!truth(element.textContent)) })) {
                return absent
            }
            val content: dynamic = element.textContent.replace(jsRegExp("^[\\u2190-\\u21ff\\u2794]\\s+", "u"), "")
            val arrow: dynamic = (if (truth(dynamicValue(jsRegExp("\\b(regreso|retorno|volver|vuelta)\\b", "i")).test(content))) "↩" else (if (truth(dynamicValue(jsRegExp("\\b(movimiento|avance|ejecuci[oó]n|desplazamiento)\\b", "i")).test(content))) "➜" else ""))
            val updated: dynamic = (if (truth(arrow)) jsAdd(jsAdd(arrow, " "), content) else content)
            if (truth((element.textContent !== updated))) {
                element.textContent = updated
                run {
                    val target31: dynamic = stats
                    val previous33: dynamic = numeric(target31.phaseUpdates)
                    target31.phaseUpdates = jsAdd(previous33, 1)
                }
            }
            return absent
        }
        prepareChallenge = fun(d: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(d))) { (d.kind === "rest") }) { (d.date !== onPresent(root.Engine) { value38 -> run { val owner40: dynamic = value38; onPresent(owner40.dateKey) { method39 -> invokeWith(method39, owner40, js("[]")) } } }) })) {
                return ""
            }
            val record: dynamic = onPresent(global_state) { value41 -> onPresent(value41.logs) { value42 -> value42[d.date] } }
            if (truth(jsOr(onPresent(record) { value43 -> value43.pain }) { isRestricted(onPresent(global_state) { value44 -> value44.profile }) })) {
                return ""
            }
            val completed: dynamic = jsAnd(jsAnd((onPresent(record) { value45 -> value45.complete } === true)) { jsAtLeast(global_Number(record.rpe), 1) }) { jsAtMost(global_Number(record.rpe), 7) }
            run {
                val target46: dynamic = stats
                val previous48: dynamic = numeric(target46.missions)
                target46.missions = jsAdd(previous48, 1)
            }
            return jsAdd(jsAdd("<small class=\"hunter-challenge3826\" aria-live=\"off\">", (if (truth(completed)) "◇ Desafío opcional: registro de esfuerzo completado ✓" else "◇ Desafío opcional: registra tu esfuerzo al terminar · sin ejercicios extra")), "</small>")
        }
        availableSwaps = fun(d: dynamic, index: dynamic): dynamic {
            val current: dynamic = onPresent(d) { value51 -> onPresent(value51.items) { value52 -> value52[index] } }
            val p: dynamic = onPresent(global_state) { value53 -> value53.profile }
            val catalog: dynamic = onPresent(root.Engine) { value54 -> value54.exercises }
            if (truth(jsOr(jsOr((!truth(p))) { (!truth(global_Array.isArray(catalog))) }) { (!truth(current)) })) {
                return js("[]")
            }
            val initial: dynamic = root.Engine.byId(current.id)
            if (truth((!truth(initial)))) {
                return js("[]")
            }
            return catalog.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(e) { (e.id !== initial.id) }) { (e.category === initial.category) }) { (e.group === initial.group) }) { (e.unit === initial.unit) }) { (!truth(d.items.some((fun(x: dynamic, j: dynamic): dynamic {
                    return jsAnd((j !== index)) { (x.id === e.id) }
                })))) }) { (!truth(e.sourceOnly)) }) { eligible(e, p) }
            })).slice(0, 12)
        }
        futureSwaps = fun(d: dynamic): dynamic {
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(d))) { (!truth(global_Array.isArray(d.items))) }) { (!truth(d.items.length)) }) { (!truth(dynamicValue(jsRegExp("^\\d{4}-\\d\\d-\\d\\d\$", "")).test(d.date))) }) { jsAtMost(d.date, onPresent(root.Engine) { value57 -> run { val owner59: dynamic = value57; onPresent(owner59.dateKey) { method58 -> invokeWith(method58, owner59, js("[]")) } } }) }) { d.routineId }) { jsAnd(d.customized) { (!truth(d.prePlanSwap3826)) } }) { isActive(onPresent(global_state) { value60 -> onPresent(value60.logs) { value61 -> value61[d.date] } }) })) {
                return ""
            }
            if (truth((!truth(onPresent(root.Adapt) { value62 -> run { val owner64: dynamic = value62; onPresent(owner64.mode) { method63 -> onPresent(invokeWith(method63, owner64, arrayOf<dynamic>(onPresent(global_state) { value65 -> value65.profile }))) { value66 -> value66.auto } } } })))) {
                return ""
            }
            val rows: dynamic = d.items.map((fun(item: dynamic, index: dynamic): dynamic {
                val alts: dynamic = availableSwaps(d, index)
                if (truth((!truth(alts.length)))) {
                    return ""
                }
                val e: dynamic = root.Engine.byId(item.id)
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<label class=\"hunter-swaprow3826\">", escText(e.name)), " <span> · cambiar antes de empezar</span><select data-swap-index=\""), index), "\" aria-label=\"Alternativa para "), escText(e.name)), "\">"), "<option value=\"\">Conservar ejercicio</option>"), alts.map((fun(a: dynamic): dynamic {
                    return jsAdd(jsAdd(jsAdd(jsAdd("<option value=\"", escText(a.id)), "\">"), escText(a.name)), "</option>")
                })).join("")), "</select></label>")
            })).filter(global_Boolean)
            if (truth((!truth(rows.length)))) {
                return ""
            }
            return jsAdd(jsAdd(jsAdd(jsAdd("<div class=\"hunter-preswap3826\" data-swap-date=\"", d.date), "\">"), rows.join("")), "<button type=\"button\" class=\"btn ghost\" onclick=\"HunterClosure3826.swapPlanned(this)\">Aplicar cambios futuros</button></div>")
        }
        swapPlanned = fun(button: dynamic): dynamic {
            val container: dynamic = onPresent(button) { value71 -> run { val owner73: dynamic = value71; onPresent(owner73.closest) { method72 -> invokeWith(method72, owner73, arrayOf<dynamic>("[data-swap-date]")) } } }
            val date: dynamic = onPresent(container) { value74 -> onPresent(value74.dataset) { value75 -> value75.swapDate } }
            val day: dynamic = (if (truth((js("typeof dayByDate") === "function"))) global_dayByDate(date) else null)
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(day))) { jsAtMost(date, onPresent(root.Engine) { value76 -> run { val owner78: dynamic = value76; onPresent(owner78.dateKey) { method77 -> invokeWith(method77, owner78, js("[]")) } } }) }) { day.routineId }) { jsAnd(day.customized) { (!truth(day.prePlanSwap3826)) } }) { isActive(onPresent(global_state) { value79 -> onPresent(value79.logs) { value80 -> value80[date] } }) }) { (!truth(onPresent(root.Adapt) { value81 -> run { val owner83: dynamic = value81; onPresent(owner83.mode) { method82 -> onPresent(invokeWith(method82, owner83, arrayOf<dynamic>(onPresent(global_state) { value84 -> value84.profile }))) { value85 -> value85.auto } } } })) })) {
                run {
                    val target86: dynamic = stats
                    val previous88: dynamic = numeric(target86.blockedSwaps)
                    target86.blockedSwaps = jsAdd(previous88, 1)
                }
                return false
            }
            val next: dynamic = day.items.map((fun(x: dynamic): dynamic {
                return jsObject { obj89 ->
                    copyFields(obj89, x)
                }
            }))
            var changed: dynamic = 0
            if (true) {
                val iterator90: dynamic = iteratorOf(container.querySelectorAll("select[data-swap-index]"))
                while (true) {
                    val next91: dynamic = iterator90.next()
                    if (truth(next91.done)) break
                    var select: dynamic = absent
                    select = next91.value
                    val index: dynamic = global_Number(select.dataset.swapIndex)
                    if (truth(jsOr(jsOr((!truth(global_Number.isSafeInteger(index)))) { jsLess(index, 0) }) { jsAtLeast(index, next.length) })) {
                        run {
                            val target92: dynamic = stats
                            val previous94: dynamic = numeric(target92.blockedSwaps)
                            target92.blockedSwaps = jsAdd(previous94, 1)
                        }
                        return false
                    }
                    val id: dynamic = select["value"]
                    if (truth((!truth(id)))) {
                        continue
                    }
                    val allow: dynamic = availableSwaps(day, index).some((fun(e: dynamic): dynamic {
                        return (e.id === id)
                    }))
                    if (truth((!truth(allow)))) {
                        run {
                            val target95: dynamic = stats
                            val previous97: dynamic = numeric(target95.blockedSwaps)
                            target95.blockedSwaps = jsAdd(previous97, 1)
                        }
                        return false
                    }
                    next[index] = jsObject { obj100 ->
                        copyFields(obj100, next[index])
                        obj100["id"] = id
                        obj100["plannedSwap3826"] = true
                    }
                    run {
                        val previous101: dynamic = numeric(changed)
                        changed = jsAdd(previous101, 1)
                    }
                }
            }
            if (truth((!truth(changed)))) {
                return false
            }
            val original: dynamic = jsObject { obj102 ->
                obj102["items"] = day.items
                obj102["customized"] = day.customized
                obj102["prePlanSwap3826"] = day.prePlanSwap3826
            }
            day.items = next
            day.customized = true
            day.prePlanSwap3826 = true
            var saved: dynamic = false
            try {
                saved = jsAnd((js("typeof save") === "function")) { (global_save() === true) }
            } catch (_: dynamic) {

            }
            if (truth((!truth(saved)))) {
                global_Object.assign(day, original)
                run {
                    val target109: dynamic = stats
                    val previous111: dynamic = numeric(target109.blockedSwaps)
                    target109.blockedSwaps = jsAdd(previous111, 1)
                }
                return false
            }
            run {
                val target112: dynamic = stats
                target112.swaps = jsAdd(target112.swaps, changed)
            }
            if (truth((js("typeof showMission") === "function"))) {
                global_showMission(date)
            }
            if (truth((js("typeof toast") === "function"))) {
                global_toast("Próxima sesión actualizada. Las sesiones iniciadas no cambian.")
            }
            return true
        }
        if (truth(root.HunterClosure3826)) {
            return absent
        }
        stats = jsonValue("{\"phaseUpdates\":0,\"missions\":0,\"swaps\":0,\"blockedSwaps\":0}")
        escText = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        isActive = (fun(l: dynamic): dynamic {
            return (!truth((!truth(jsAnd(l) { jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(l.complete) { l.pain }) { l.manual }) { jsMore(global_Number(l.rpe), 0) }) { jsMore(global_String(jsOr(l.note) { "" }).trim().length, 0) }) { onPresent(l.missionTasks) { value3 -> value3.some((fun(t: dynamic): dynamic {
                return t.done
            })) } }) { onPresent(l.items) { value4 -> value4.some((fun(x: dynamic): dynamic {
                return jsOr(jsOr(onPresent(x) { value5 -> value5.selfReported }) { onPresent(x) { value6 -> value6.missionState } }) { onPresent(x) { value7 -> onPresent(value7["actual"]) { value8 -> value8.some((fun(a: dynamic): dynamic {
                    return a.done
                })) } } }
            })) } } }))))
        })
        isRestricted = (fun(p: dynamic): dynamic {
            return (!truth((!truth(jsOr(jsOr(jsOr(jsOr(onPresent(p) { value9 -> onPresent(value9.injuries) { value10 -> value10.some((fun(x: dynamic): dynamic {
                return (x.stage !== "past")
            })) } }) { jsAnd(onPresent(p) { value11 -> onPresent(value11.health) { value12 -> value12.pain } }) { (p.health.pain !== "none") } }) { onPresent(p) { value13 -> onPresent(value13.health) { value14 -> value14.acute } } }) { onPresent(p) { value15 -> value15.limits } }) { onPresent(p) { value16 -> onPresent(value16.health) { value17 -> value17.special } } }))))
        })
        eligible = (fun(e: dynamic, p: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(e))) { (!truth(p)) }) { (!truth(onPresent(root.HunterMaterials3814) { value18 -> run { val owner20: dynamic = value18; onPresent(owner20.eligible) { method19 -> invokeWith(method19, owner20, arrayOf<dynamic>(e, p)) } } })) })) {
                return false
            }
            if (truth(isRestricted(p))) {
                return (onPresent(root.HunterConstraints3811) { value21 -> run { val owner23: dynamic = value21; onPresent(owner23.allowed) { method22 -> invokeWith(method22, owner23, arrayOf<dynamic>(e, p)) } } } === true)
            }
            try {
                return (!truth((!truth(onPresent(root.Adapt) { value24 -> run { val owner26: dynamic = value24; onPresent(owner26.allowed) { method25 -> invokeWith(method25, owner26, arrayOf<dynamic>(e, p)) } } }))))
            } catch (_: dynamic) {
                return false
            }
            return absent
        })
        if (truth((js("typeof vectorPaint") === "function"))) {
            val original: dynamic = global_vectorPaint
            global_vectorPaint = (fun(): dynamic {
                val receiver34: dynamic = js("this")
                val callArgs35: dynamic = js("arguments")
                val result: dynamic = original.apply(receiver34, callArgs35)
                markPhase()
                return result
            })
        }
        if (truth((js("typeof missionWindow") === "function"))) {
            val original: dynamic = global_missionWindow
            global_missionWindow = (fun(d: dynamic): dynamic {
                val receiver67: dynamic = js("this")
                val callArgs68: dynamic = js("arguments")
                var markup: dynamic = original.apply(receiver67, callArgs68)
                if (truth((jsTypeOf(markup) !== "string"))) {
                    return markup
                }
                val challenge: dynamic = prepareChallenge(d)
                val swap: dynamic = futureSwaps(d)
                if (truth(jsAnd((!truth(challenge))) { (!truth(swap)) })) {
                    return markup
                }
                val close: dynamic = markup.lastIndexOf("</section>")
                if (truth(jsAtLeast(close, 0))) {
                    markup = jsAdd(jsAdd(jsAdd(markup.slice(0, close), challenge), swap), markup.slice(close))
                }
                return markup
            })
        }
        root.HunterClosure3826 = global_Object.freeze(jsObject { obj116 ->
            obj116["version"] = "38.26"
            obj116["swapPlanned"] = swapPlanned
            obj116["diagnostics"] = (fun(): dynamic {
                return jsObject { obj117 ->
                    copyFields(obj117, stats)
                    obj117["note"] = "Indicadores de fase, cambio previo y reconocimiento opcional; no verifica posturas clínicas."
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
