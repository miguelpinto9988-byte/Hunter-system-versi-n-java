@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterpanels3857

import hunter.browser.*

@JsName("Error") private external var global_Error: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("console") private external var global_console: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("refreshRoutines") private external var global_refreshRoutines: dynamic
@JsName("routineList") private external var global_routineList: dynamic
@JsName("routineCards") private external var global_routineCards: dynamic
@JsName("go") private external var global_go: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterPanels3857() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var stage: dynamic = absent
        var onFrame: dynamic = absent
        var lastTileKey: dynamic = absent
        var lastStatsKey: dynamic = absent
        var telemetry: dynamic = absent
        var show: dynamic = absent
        var refreshMonarch: dynamic = absent
        var mediaFrame: dynamic = absent
        var deferOffscreenMedia: dynamic = absent
        var scheduleMedia: dynamic = absent
        var updateHome: dynamic = absent
        var scheduleHome: dynamic = absent
        show = fun(next: dynamic): dynamic {
            stage = global_Math.max(1, global_Math.min(3, jsOr(global_Number(next)) { 1 }))
            val form: dynamic = doc.querySelector("#profileForm")
            if (truth((!truth(form)))) {
                return false
            }
            val host: dynamic = form.closest(".profile-v3")
            if (truth((!truth(host)))) {
                return false
            }
            host.dataset.activePane3857 = global_String(stage)
            val scene: dynamic = form.closest(".scene")
            if (truth(scene)) {
                scene.dataset.profilePane3857 = global_String(stage)
            }
            form.noValidate = true
            form.querySelectorAll("[role=\"tab\"][id^=\"profile-tab3857-\"]").forEach((fun(button: dynamic, i: dynamic): dynamic {
                val active: dynamic = (jsAdd(i, 1) === stage)
                button.classList.toggle("active", active)
                button.setAttribute("aria-selected", global_String(active))
                button.tabIndex = (if (truth(active)) 0 else (-numeric(1)))
                return absent
            }))
            val counter: dynamic = form.querySelector(".profile-panel-counter3857")
            if (truth(counter)) {
                counter.textContent = ("Panel " + textOf(stage) + " / 3")
            }
            val prev: dynamic = form.querySelector("#profile-prev3857")
            if (truth(prev)) {
                prev.disabled = (stage === 1)
            }
            val nextBtn: dynamic = form.querySelector("#profile-next3857")
            if (truth(nextBtn)) {
                nextBtn.hidden = (stage === 3)
            }
            run {
                val target23: dynamic = telemetry
                val previous25: dynamic = numeric(target23.stageChanges)
                target23.stageChanges = jsAdd(previous25, 1)
            }
            if (truth(onPresent(doc.activeElement) { value26 -> value26.closest(".profile-panels3857, .profile-panel-actions3857") })) {
                try {
                    host.scrollIntoView(jsonValue("{\"behavior\":\"instant\",\"block\":\"start\"}"))
                } catch (_: dynamic) {

                }
            }
            return true
        }
        refreshMonarch = fun(): dynamic {
            val m: dynamic = onPresent(global_state.monarch3852) { value49 -> value49.active }
            val page: dynamic = doc.querySelector(".scene[data-route3855=\"monarch\"] .monarch-page3853")
            if (truth(jsOr(jsOr((!truth(page))) { (!truth(m)) }) { (!truth(root.HunterMonarch3852)) })) {
                return false
            }
            val inputs: dynamic = jsArray { array50 ->
                appendAll(array50, page.querySelectorAll("input[id^=\"monarch-entry-\"]"))
            }
            if (truth((inputs.length !== m.objectives.length))) {
                return false
            }
            val rows: dynamic = root.HunterMonarch3852.progress(m, jsOr(global_state.logs) { js("({})") })
            if (truth((rows.length !== inputs.length))) {
                return false
            }
            val byId: dynamic = jsConstruct(global_Map, arrayOf<dynamic>(rows.map((fun(o: dynamic): dynamic {
                return arrayOf<dynamic>(o.id, o)
            }))))
            if (true) {
                val iterator51: dynamic = iteratorOf(inputs)
                while (true) {
                    val next52: dynamic = iterator51.next()
                    if (truth(next52.done)) break
                    var input: dynamic = absent
                    input = next52.value
                    val id: dynamic = input.id.slice(dynamicValue("monarch-entry-").length)
                    val o: dynamic = byId["get"](id)
                    if (truth((!truth(o)))) {
                        return false
                    }
                    val quest: dynamic = input.closest(".quest")
                    val small: dynamic = onPresent(quest) { value53 -> value53.querySelector(".grow > small") }
                    if (truth((!truth(small)))) {
                        return false
                    }
                    small.textContent = small.textContent.replace(jsRegExp("\\d+\\s*\\/\\s*\\d+\\s*(?:segundos|repeticiones)", ""), ("" + textOf(o.done) + "/" + textOf(o.target) + " " + textOf((if (truth((o.unit === "sec"))) "segundos" else "repeticiones")) + ""))
                    val bar: dynamic = quest.querySelector(".bar i")
                    if (truth(bar)) {
                        bar.style.width = ("" + textOf(global_Math.max(0, global_Math.min(100, jsDivide(jsMultiply(100, o.done), o.target)))) + "%")
                    }
                }
            }
            val active: dynamic = doc.activeElement
            if (truth((onPresent(active) { value58 -> value58.tagName } === "BUTTON"))) {
                val input: dynamic = onPresent(active.closest(".quest")) { value59 -> value59.querySelector("input[id^=\"monarch-entry-\"]") }
                if (truth(input)) {
                    input["value"] = "1"
                }
            }
            run {
                val target62: dynamic = telemetry
                val previous64: dynamic = numeric(target62.partialMonarchUpdates)
                target62.partialMonarchUpdates = jsAdd(previous64, 1)
            }
            return true
        }
        deferOffscreenMedia = fun(): dynamic {
            mediaFrame = 0
            if (true) {
                val iterator67: dynamic = iteratorOf(doc.querySelectorAll("#app .scene img, #modal .demo-gallery img"))
                while (true) {
                    val next68: dynamic = iterator67.next()
                    if (truth(next68.done)) break
                    var img: dynamic = absent
                    img = next68.value
                    if (truth(jsOr(img.closest(".topbar,.monarch-entry3853")) { img.getAttribute("loading") })) {
                        continue
                    }
                    if (truth(jsMore(img.getBoundingClientRect().top, jsAdd(root.innerHeight, 120)))) {
                        img.setAttribute("loading", "lazy")
                        img.setAttribute("decoding", "async")
                        run {
                            val target69: dynamic = telemetry
                            val previous71: dynamic = numeric(target69.lazyImages)
                            target69.lazyImages = jsAdd(previous71, 1)
                        }
                    }
                }
            }
            return absent
        }
        scheduleMedia = fun(): dynamic {
            if (truth((!truth(mediaFrame)))) {
                mediaFrame = root.requestAnimationFrame(deferOffscreenMedia)
            }
            return absent
        }
        updateHome = fun(): dynamic {
            if (truth(jsOr((global_tab !== "home")) { (!truth(global_state.profile)) })) {
                return absent
            }
            val scene: dynamic = doc.querySelector(".scene[data-route3855=\"home\"]")
            if (truth((!truth(scene)))) {
                return absent
            }
            val totals: dynamic = global_Engine.stats(jsOr(global_state.logs) { js("({})") })
            val key: dynamic = dynamicValue(arrayOf<dynamic>(totals.streak, totals.days, totals.reps, totals.level, totals.xp)).join("|")
            if (truth((lastStatsKey !== key))) {
                val stats: dynamic = scene.querySelectorAll(".dashboard26 .metrics .metric strong")
                if (truth(jsAtLeast(stats.length, 3))) {
                    dynamicValue(arrayOf<dynamic>(totals.streak, totals.days, totals.reps)).forEach((fun(`value`: dynamic, i: dynamic): dynamic {
                        val text: dynamic = global_String(`value`)
                        if (truth((stats[i].textContent !== text))) {
                            stats[i].textContent = text
                        }
                        return absent
                    }))
                }
                val rank: dynamic = dynamicValue(jsonValue("[\"E\",\"D\",\"C\",\"B\",\"A\",\"S\"]"))[global_Math.min(5, global_Math.floor(jsDivide(jsSubtract(totals.level, 1), 5)))]
                if (truth((dynamicValue(jsOr(onPresent(scene.querySelector(".rank-pill")) { value98 -> value98.textContent }) { "" }).trim() === ("RANGO " + textOf(rank) + "")))) {
                    val hero: dynamic = scene.querySelector(".hero-footer")
                    val strong: dynamic = onPresent(hero) { value99 -> value99.querySelector("strong") }
                    if (truth((onPresent(strong) { value100 -> onPresent(value100.firstChild) { value101 -> value101.nodeType } } === 3))) {
                        strong.firstChild.nodeValue = ("NIVEL " + textOf(global_String(totals.level).padStart(2, "0")) + "")
                    }
                    val xp: dynamic = onPresent(hero) { value104 -> value104.querySelector("small") }
                    if (truth(xp)) {
                        xp.textContent = ("" + textOf(jsMod(totals.xp, 300)) + " / 300 XP")
                    }
                    val bar: dynamic = onPresent(hero) { value107 -> value107.querySelector(".bar i") }
                    if (truth(bar)) {
                        bar.style.width = ("" + textOf(jsDivide(jsMod(totals.xp, 300), 3)) + "%")
                    }
                }
                lastStatsKey = key
                run {
                    val target110: dynamic = telemetry
                    val previous112: dynamic = numeric(target110.partialHomeUpdates)
                    target110.partialHomeUpdates = jsAdd(previous112, 1)
                }
            }
            val st: dynamic = jsOr(global_state.monarch3852) { js("({})") }
            val m: dynamic = st.active
            val progress: dynamic = (if (truth(jsAnd(m) { root.HunterMonarch3852 })) root.HunterMonarch3852.progress(m, jsOr(global_state.logs) { js("({})") }) else js("[]"))
            val key2: dynamic = dynamicValue(jsArray { array117 ->
                array117.push(jsOr(onPresent(m) { value118 -> value118.startedAt }) { 0 })
                array117.push(jsOr(onPresent(m) { value119 -> value119.expiresAt }) { 0 })
                array117.push(jsOr(st.xp) { 0 })
                array117.push(jsOr(onPresent(global_state.profile) { value120 -> value120.difficulty }) { "" })
                appendAll(array117, progress.map((fun(o: dynamic): dynamic {
                    return o.done
                })))
            }).join("|")
            if (truth((lastTileKey !== key2))) {
                val current: dynamic = scene.querySelector(".monarch-entry3853")
                if (truth(jsAnd(current) { onPresent(root.HunterMonarchUI3853) { value121 -> value121.homeCard } })) {
                    val div: dynamic = doc.createElement("div")
                    div.innerHTML = root.HunterMonarchUI3853.homeCard()
                    if (truth(div.firstElementChild)) {
                        current.replaceWith(div.firstElementChild)
                    }
                    lastTileKey = key2
                }
            }
            return absent
        }
        scheduleHome = fun(): dynamic {
            if (truth(jsOr(onFrame) { (global_tab !== "home") })) {
                return absent
            }
            onFrame = true
            root.requestAnimationFrame((fun(): dynamic {
                onFrame = false
                try {
                    updateHome()
                } catch (error: dynamic) {
                    global_console.warn("HunterPanels3857: parcial no aplicada", error)
                }
                return absent
            }))
            return absent
        }
        if (truth(root.HunterPanels3857)) {
            return absent
        }
        if (truth(jsOr(jsOr(jsOr((js("typeof profileForm") !== "function")) { (js("typeof submitProfile") !== "function") }) { (js("typeof go") !== "function") }) { (js("typeof render") !== "function") })) {
            throw global_Error("Panels3857: faltan dependencias de la interfaz")
        }
        val STAGES: dynamic = jsonValue("[{\"title\":\"Personal\",\"hint\":\"Foto y datos generales\"},{\"title\":\"Entrenamiento\",\"hint\":\"Objetivos y equipo\"},{\"title\":\"Sistema\",\"hint\":\"Salud, preferencias y guardado\"}]")
        stage = 1
        onFrame = false
        lastTileKey = ""
        lastStatsKey = ""
        telemetry = jsonValue("{\"profiles\":0,\"stageChanges\":0,\"preventedRebuilds\":0,\"partialHomeUpdates\":0,\"partialMonarchUpdates\":0,\"monarchFallbacks\":0,\"validationReveals\":0,\"calendarRestores\":0,\"routineRefreshes\":0,\"lazyImages\":0}")
        val previousProfileForm: dynamic = global_profileForm
        global_profileForm = (fun(initial: dynamic): dynamic {
            val receiver3: dynamic = js("this")
            val callArgs4: dynamic = js("arguments")
            var html: dynamic = previousProfileForm.apply(receiver3, callArgs4)
            var index: dynamic = 0
            val assignments: dynamic = jsonValue("[1,1,2,2,2,2,3]")
            html = html.replace(jsRegExp("<(div|section|details)(\\s+class=\"([^\"]*)\")([^>]*)>", "g"), (fun(markup: dynamic, tag: dynamic, classPart: dynamic, classes: dynamic, rest: dynamic): dynamic {
                val names: dynamic = classes.split(jsRegExp("\\s+", ""))
                var pane: dynamic = dynamicValue(null)
                if (truth(names.includes("profile-block"))) {
                    pane = assignments[run {
                        val previous5: dynamic = numeric(index)
                        val assigned6: dynamic = jsAdd(previous5, 1)
                        index = assigned6
                        previous5
                    }]
                } else {
                    if (truth(jsOr(names.includes("health-compact")) { names.includes("creator-settings") })) {
                        pane = 3
                    }
                }
                if (truth((!truth(pane)))) {
                    return markup
                }
                return ("<" + textOf(tag) + "" + textOf(classPart) + "" + textOf(rest) + " data-profile-pane3857=\"" + textOf(pane) + "\">")
            }))
            if (truth((index !== assignments.length))) {
                global_console.warn("Panels3857: los bloques de Perfil cambiaron", index)
                return previousProfileForm.apply(receiver3, callArgs4)
            }
            val navigation: dynamic = ("<div class=\"profile-panels3857\" role=\"tablist\" aria-label=\"Secciones de perfil\">" + textOf(STAGES.map((fun(s: dynamic, i: dynamic): dynamic {
                return ("<button type=\"button\" role=\"tab\" id=\"profile-tab3857-" + textOf(jsAdd(i, 1)) + "\" aria-controls=\"profileForm\" aria-selected=\"" + textOf((stage === jsAdd(i, 1))) + "\" class=\"profile-panel-tab3857" + textOf((if (truth((stage === jsAdd(i, 1)))) " active" else "")) + "\" onclick=\"HunterPanels3857.show(" + textOf(jsAdd(i, 1)) + ")\"><strong>" + textOf(global_String(jsAdd(i, 1)).padStart(2, "0")) + " · " + textOf(s.title) + "</strong><small>" + textOf(s.hint) + "</small></button>")
            })).join("")) + "</div>")
            html = html.replace("class=\"intro profile-v3\"", ("class=\"intro profile-v3\" data-active-pane3857=\"" + textOf(stage) + "\"")).replace(jsRegExp("<form id=\"profileForm\"([^>]*)>", ""), (fun(full: dynamic, attrs: dynamic): dynamic {
                return ("<form id=\"profileForm\"" + textOf(attrs) + " novalidate>" + textOf(navigation) + "")
            })).replace("</form></section>", ("<div class=\"profile-panel-actions3857\">\n     <button class=\"btn ghost\" id=\"profile-prev3857\" type=\"button\" onclick=\"HunterPanels3857.show(Math.max(1,HunterPanels3857.stage()-1))\">← Anterior</button>\n     <span class=\"profile-panel-counter3857\" aria-live=\"polite\">Panel " + textOf(stage) + " / 3</span>\n     <button class=\"btn primary\" id=\"profile-next3857\" type=\"button\" onclick=\"HunterPanels3857.show(Math.min(3,HunterPanels3857.stage()+1))\">Siguiente →</button>\n   </div></form></section>"))
            return html
        })
        val formerSubmit: dynamic = global_submitProfile
        global_submitProfile = (fun(event: dynamic): dynamic {
            val receiver27: dynamic = js("this")
            val callArgs28: dynamic = js("arguments")
            val form: dynamic = onPresent(event) { value29 -> value29.target }
            if (truth((onPresent(form) { value30 -> value30.id } === "profileForm"))) {
                val firstInvalid: dynamic = dynamicValue(jsArray { array32 ->
                    appendAll(array32, form.elements)
                }).find((fun(`field`: dynamic): dynamic {
                    return jsAnd((jsTypeOf(`field`.checkValidity) === "function")) { (!truth(`field`.checkValidity())) }
                }))
                if (truth(firstInvalid)) {
                    event.preventDefault()
                    val pane: dynamic = jsOr(global_Number(onPresent(firstInvalid.closest("[data-profile-pane3857]")) { value33 -> value33.dataset.profilePane3857 })) { 1 }
                    show(pane)
                    run {
                        val target35: dynamic = telemetry
                        val previous37: dynamic = numeric(target35.validationReveals)
                        target35.validationReveals = jsAdd(previous37, 1)
                    }
                    if (true) {
                        var detail: dynamic = absent
                        detail = firstInvalid.closest("details")
                        var firstIteration38 = true
                        while (true) {
                            if (!firstIteration38) {
                                detail = onPresent(detail.parentElement) { value39 -> value39.closest("details") }
                            }
                            firstIteration38 = false
                            if (!truth(detail)) break
                            detail["open"] = true
                        }
                    }
                    firstInvalid.focus(jsonValue("{\"preventScroll\":true}"))
                    firstInvalid.reportValidity()
                    return false
                }
            }
            return formerSubmit.apply(receiver27, callArgs28)
        })
        val originalRender: dynamic = global_render
        global_render = (fun(): dynamic {
            val receiver42: dynamic = js("this")
            val callArgs43: dynamic = js("arguments")
            val outcome: dynamic = originalRender.apply(receiver42, callArgs43)
            if (truth(doc.querySelector("#profileForm"))) {
                show(stage)
                run {
                    val target44: dynamic = telemetry
                    val previous46: dynamic = numeric(target44.profiles)
                    target44.profiles = jsAdd(previous46, 1)
                }
            }
            scheduleMedia()
            return outcome
        })
        val scrollPositions: dynamic = global_Object.create(null)
        mediaFrame = 0
        val originalRefreshRoutines: dynamic = global_refreshRoutines
        global_refreshRoutines = (fun(): dynamic {
            val receiver74: dynamic = js("this")
            val callArgs75: dynamic = js("arguments")
            val container: dynamic = doc.querySelector("#routineResults")
            val count: dynamic = doc.querySelector("#routineCount")
            if (truth(jsOr((!truth(container))) { (!truth(count)) })) {
                return originalRefreshRoutines.apply(receiver74, callArgs75)
            }
            val list: dynamic = global_routineList()
            container.innerHTML = global_routineCards(list)
            count.textContent = jsAdd(list.length, " rutinas")
            run {
                val target80: dynamic = telemetry
                val previous82: dynamic = numeric(target80.routineRefreshes)
                target80.routineRefreshes = jsAdd(previous82, 1)
            }
            scheduleMedia()
            return absent
        })
        val originalGo: dynamic = global_go
        global_go = (fun(destination: dynamic): dynamic {
            val receiver83: dynamic = js("this")
            val callArgs84: dynamic = js("arguments")
            val previous: dynamic = global_tab
            if (truth(jsAnd((previous === "calendar")) { (destination !== "calendar") })) {
                scrollPositions.calendar = jsOr(root.scrollY) { 0 }
            }
            if (truth(jsAnd((destination === "profile")) { (global_tab !== "profile") })) {
                stage = 1
            }
            if (truth(jsAnd((destination === "monarch")) { (global_tab === "monarch") })) {
                if (truth(refreshMonarch())) {
                    return absent
                }
                run {
                    val target87: dynamic = telemetry
                    val previous89: dynamic = numeric(target87.monarchFallbacks)
                    target87.monarchFallbacks = jsAdd(previous89, 1)
                }
            }
            val outcome: dynamic = originalGo.apply(receiver83, callArgs84)
            scheduleMedia()
            if (truth(jsAnd(jsAnd((destination === "calendar")) { (previous !== "calendar") }) { global_Number.isFinite(scrollPositions.calendar) })) {
                val pos: dynamic = scrollPositions.calendar
                root.requestAnimationFrame((fun(): dynamic {
                    if (truth((global_tab === "calendar"))) {
                        root.scrollTo(0, pos)
                        run {
                            val target90: dynamic = telemetry
                            val previous92: dynamic = numeric(target90.calendarRestores)
                            target90.calendarRestores = jsAdd(previous92, 1)
                        }
                    }
                    return absent
                }))
            }
            return outcome
        })
        val oldSave: dynamic = global_save
        global_save = (fun(): dynamic {
            val receiver126: dynamic = js("this")
            val callArgs127: dynamic = js("arguments")
            val result: dynamic = oldSave.apply(receiver126, callArgs127)
            if (truth(result)) {
                scheduleHome()
            }
            return result
        })
        root.HunterPanels3857 = global_Object.freeze(jsObject { obj130 ->
            obj130["version"] = "38.57"
            obj130["show"] = show
            obj130["stage"] = (fun(): dynamic {
                return stage
            })
            obj130["refreshHome"] = updateHome
            obj130["refreshMonarch"] = refreshMonarch
            obj130["diagnostics"] = (fun(): dynamic {
                return jsObject { obj131 ->
                    copyFields(obj131, telemetry)
                    obj131["tab"] = global_tab
                    obj131["stage"] = stage
                    obj131["activePane"] = jsOr(onPresent(doc.querySelector(".profile-v3")) { value132 -> value132.dataset.activePane3857 }) { null }
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
