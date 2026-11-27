@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv93

import hunter.browser.*

@JsName("V92Runtime") private external var global_V92Runtime: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("CompanionMentionSource") private external var global_CompanionMentionSource: dynamic
@JsName("CompanionMentionPattern") private external var global_CompanionMentionPattern: dynamic
@JsName("CompanionMentionCheck") private external var global_CompanionMentionCheck: dynamic
@JsName("personalizeCompanionString") private external var global_personalizeCompanionString: dynamic
@JsName("syncKnownCompanionUI") private external var global_syncKnownCompanionUI: dynamic
@JsName("personalizeCompanionText") private external var global_personalizeCompanionText: dynamic
@JsName("previewCompanionChoice") private external var global_previewCompanionChoice: dynamic
@JsName("closeActiveModal") private external var global_closeActiveModal: dynamic
@JsName("fitActiveModal") private external var global_fitActiveModal: dynamic
@JsName("lockModalViewport") private external var global_lockModalViewport: dynamic
@JsName("unlockModalViewport") private external var global_unlockModalViewport: dynamic
@JsName("decorateModal") private external var global_decorateModal: dynamic
@JsName("modalDialog") private external var global_modalDialog: dynamic
@JsName("injuryFocusSummary") private external var global_injuryFocusSummary: dynamic
@JsName("addCompanionAndInjuryContext") private external var global_addCompanionAndInjuryContext: dynamic
@JsName("openAnimationGallery") private external var global_openAnimationGallery: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("plan") private external var global_plan: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("refreshMission") private external var global_refreshMission: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("bodyResults") private external var global_bodyResults: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("CompanionNames") private external var global_CompanionNames: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("RegExp") private external var global_RegExp: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("NodeFilter") private external var global_NodeFilter: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("closeCoach") private external var global_closeCoach: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("InjuryAwareV93") private external var global_InjuryAwareV93: dynamic
@JsName("IgrisArt") private external var global_IgrisArt: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic

internal fun installV93() {
    global_companionDisplayName = fun(arg4: dynamic): dynamic {
        var type: dynamic = absent
        type = definedOr(arg4) { global_selectedCompanion() }
        return jsOr(global_CompanionNames[type]) { global_CompanionNames.knight }
    }
    global_personalizeCompanionString = fun(`value`: dynamic, arg7: dynamic): dynamic {
        var type: dynamic = absent
        type = definedOr(arg7) { global_selectedCompanion() }
        return global_String(nullableOr(`value`) { "" }).replace(global_CompanionMentionPattern, global_companionDisplayName(type))
    }
    global_syncKnownCompanionUI = fun(root: dynamic, arg10: dynamic): dynamic {
        var type: dynamic = absent
        type = definedOr(arg10) { global_selectedCompanion() }
        if (truth((!truth(root)))) {
            return root
        }
        val name: dynamic = global_companionDisplayName(type)
        run { val owner12: dynamic = root; onPresent(owner12.querySelectorAll) { method11 -> invokeWith(method11, owner12, arrayOf<dynamic>("[data-selected-companion-label]")).forEach((fun(el: dynamic): dynamic {
            return run {
                val target14: dynamic = el
                val assigned16: dynamic = name
                target14.textContent = assigned16
                assigned16
            }
        })) } }
        val bannerTitle: dynamic = run { val owner18: dynamic = root; onPresent(owner18.querySelector) { method17 -> invokeWith(method17, owner18, arrayOf<dynamic>(".coach-banner h2")) } }
        if (truth(bannerTitle)) {
            bannerTitle.textContent = ("" + textOf(name) + " está contigo.")
        }
        val panelName: dynamic = run { val owner22: dynamic = root; onPresent(owner22.querySelector) { method21 -> invokeWith(method21, owner22, arrayOf<dynamic>("#companionName")) } }
        if (truth(panelName)) {
            panelName.textContent = name
        }
        run { val owner26: dynamic = root; onPresent(owner26.querySelectorAll) { method25 -> invokeWith(method25, owner26, arrayOf<dynamic>(".mission-ai-copy b")).forEach((fun(el: dynamic): dynamic {
            return run {
                val target28: dynamic = el
                val assigned30: dynamic = name
                target28.textContent = assigned30
                assigned30
            }
        })) } }
        val floating: dynamic = run { val owner32: dynamic = root; onPresent(owner32.querySelector) { method31 -> invokeWith(method31, owner32, arrayOf<dynamic>("#companion")) } }
        if (truth(floating)) {
            floating.setAttribute("aria-label", ("Abrir asistente " + textOf(name) + ""))
            val label: dynamic = floating.querySelector("span")
            if (truth(label)) {
                label.textContent = ("" + textOf(name) + " · IA")
            }
        }
        return root
    }
    global_personalizeCompanionText = fun(root: dynamic, arg37: dynamic): dynamic {
        var type: dynamic = absent
        type = definedOr(arg37) { global_selectedCompanion() }
        if (truth((!truth(root)))) {
            return root
        }
        val skip: dynamic = (fun(node: dynamic): dynamic {
            return onPresent(node) { value38 -> onPresent(value38.parentElement) { value39 -> run { val owner41: dynamic = value39; onPresent(owner41.closest) { method40 -> invokeWith(method40, owner41, arrayOf<dynamic>("[data-companion-static=\"true\"]")) } } } }
        })
        global_syncKnownCompanionUI(root, type)
        val walker: dynamic = global_document.createTreeWalker(root, global_NodeFilter.SHOW_TEXT)
        val nodes: dynamic = js("[]")
        while (truth(walker.nextNode())) {
            nodes.push(walker.currentNode)
        }
        nodes.forEach((fun(node: dynamic): dynamic {
            if (truth(jsOr(skip(node)) { (!truth(global_CompanionMentionCheck.test(jsOr(node.nodeValue) { "" }))) })) {
                return absent
            }
            node.nodeValue = global_personalizeCompanionString(node.nodeValue, type)
            return absent
        }))
        run { val owner45: dynamic = root; onPresent(owner45.querySelectorAll) { method44 -> invokeWith(method44, owner45, arrayOf<dynamic>("[aria-label],[title],[placeholder]")).forEach((fun(el: dynamic): dynamic {
            if (truth(el.closest("[data-companion-static=\"true\"]"))) {
                return absent
            }
            if (true) {
                val iterator47: dynamic = iteratorOf(jsonValue("[\"aria-label\",\"title\",\"placeholder\"]"))
                while (true) {
                    val next48: dynamic = iterator47.next()
                    if (truth(next48.done)) break
                    var a: dynamic = absent
                    a = next48.value
                    if (truth(jsAnd(el.hasAttribute(a)) { global_CompanionMentionCheck.test(jsOr(el.getAttribute(a)) { "" }) })) {
                        el.setAttribute(a, global_personalizeCompanionString(el.getAttribute(a), type))
                    }
                }
            }
            return absent
        })) } }
        return root
    }
    global_previewCompanionChoice = fun(input: dynamic): dynamic {
        if (truth(jsOr((!truth(onPresent(input) { value51 -> value51.form }))) { (!truth(global_CompanionNames[input["value"]])) })) {
            return absent
        }
        val form: dynamic = input.form
        form.querySelectorAll(".companion-option").forEach((fun(card: dynamic): dynamic {
            val radio: dynamic = card.querySelector("input")
            val small: dynamic = card.querySelector("small")
            card.classList.toggle("selected", radio.checked)
            if (truth(small)) {
                small.textContent = (if (truth(radio.checked)) "IA seleccionada" else "Tocar para seleccionar")
            }
            return absent
        }))
        global_syncKnownCompanionUI(form, input["value"])
        global_personalizeCompanionText(form, input["value"])
        return absent
    }
    global_closeActiveModal = fun(): dynamic {
        val d: dynamic = global_Dollar("#modal")
        if (truth((!truth(onPresent(d) { value66 -> value66["open"] })))) {
            return absent
        }
        if (truth(d.querySelector(".coach-panel"))) {
            run { global_closeCoach(); absent }
            return absent
        }
        d.close()
        return absent
    }
    global_fitActiveModal = fun(): dynamic {
        global_ModalViewport.fit()
        return absent
    }
    global_lockModalViewport = fun(): dynamic {
        global_ModalViewport.lock()
        return absent
    }
    global_unlockModalViewport = fun(): dynamic {
        global_ModalViewport.release()
        return absent
    }
    global_decorateModal = fun(arg75: dynamic): dynamic {
        var type: dynamic = absent
        type = definedOr(arg75) { global_selectedCompanion() }
        val d: dynamic = global_Dollar("#modal")
        if (truth((!truth(d)))) {
            return absent
        }
        val legacy: dynamic = global_Array.from(d.children).find((fun(el: dynamic): dynamic {
            return jsAnd(jsAnd((el.tagName === "BUTTON")) { (el.textContent.trim() === "Cerrar") }) { el.classList.contains("full") }
        }))
        if (truth(legacy)) {
            legacy.classList.add("legacy-modal-close")
            legacy.setAttribute("aria-hidden", "true")
            legacy.tabIndex = (-numeric(1))
        }
        var x: dynamic = d.querySelector(".modal-close-x")
        if (truth((!truth(x)))) {
            x = global_document.createElement("button")
            x.type = "button"
            x.className = "modal-close-x"
            x.setAttribute("aria-label", "Cerrar ventana")
            x.setAttribute("title", "Cerrar")
            x.innerHTML = "&times;"
            x.onclick = global_closeActiveModal
        }
        d.prepend(x)
        global_personalizeCompanionText(d, type)
        global_lockModalViewport()
        return absent
    }
    global_injuryFocusSummary = fun(arg101: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg101) { global_state.profile }
        return global_InjuryAwareV93.summary(p)
    }
    global_addCompanionAndInjuryContext = fun(box: dynamic, arg104: dynamic): dynamic {
        var type: dynamic = absent
        type = definedOr(arg104) { global_selectedCompanion() }
        val name: dynamic = global_companionDisplayName(type)
        val banner: dynamic = box.querySelector(".coach-banner")
        if (truth(banner)) {
            val title: dynamic = banner.querySelector("h2")
            if (truth(title)) {
                title.textContent = ("" + textOf(name) + " está contigo.")
            }
            if (truth((!truth(banner.querySelector(".coach-banner-companion"))))) {
                banner.insertAdjacentHTML("afterbegin", ("<div class=\"coach-banner-companion\" data-companion-static=\"true\">" + textOf(global_IgrisArt.svg(type)) + "</div>"))
            }
        }
        val summary: dynamic = global_injuryFocusSummary()
        if (truth(onPresent(summary) { value107 -> value107.auto })) {
            val target: dynamic = jsOr(jsOr(box.querySelector(".mission-ai-presence")) { box.querySelector(".mission-launch .mission-window-head") }) { box.querySelector(".mission-launch") }
            if (truth(jsAnd(target) { (!truth(box.querySelector(".injury-focus-note"))) })) {
                target.insertAdjacentHTML("afterend", ("<div class=\"injury-focus-note\"><b>Enfoque con lesión declarada</b><span>" + textOf(global_esc(summary.text)) + "</span><small>Regla conservadora basada en las zonas y restricciones que registraste; no es rehabilitación ni diagnóstico.</small></div>"))
            }
        }
        return box
    }
    global_openAnimationGallery = fun(): dynamic {
        global_modal(("<section data-companion-static=\"true\"><h2>Cinco compañeros · muestras 2D</h2><p>Cada muestra conserva su propio nombre y reproduce entrada, espera y salida. El compañero activo solo se cambia desde Perfil.</p>" + textOf(global_Object.entries(global_CompanionNames).map((fun(arg123: dynamic): dynamic {
            var key: dynamic = absent
            var name: dynamic = absent
            val unpacked124: dynamic = fromIterable(arg123)
            key = unpacked124[0]
            name = unpacked124[1]
            return ("<details class=\"card\" ontoggle=\"HunterActions.togglePreview(this)\"><summary>" + textOf(name) + "</summary><img loading=\"lazy\" data-src=\"media/companion-" + textOf(name.toLowerCase().replace(" ", "-")) + ".gif\" alt=\"Entrada, espera y salida de " + textOf(name) + "\" style=\"max-height:520px;max-width:100%\"></details>")
        })).join("")) + "</section>"))
        return absent
    }
    global_V92Runtime = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["render"] = global_render
        obj1["home"] = global_home
        obj1["plan"] = global_plan
        obj1["modal"] = global_modal
        obj1["openCoach"] = global_openCoach
        obj1["answerCoach"] = global_answerCoach
        obj1["refreshMission"] = global_refreshMission
        obj1["technique"] = global_technique
        obj1["bodyResults"] = global_bodyResults
    }
    global_CompanionMentionSource = global_Object.values(global_CompanionNames).sort((fun(a: dynamic, b: dynamic): dynamic {
        return jsSubtract(b.length, a.length)
    })).map((fun(x: dynamic): dynamic {
        return x.replace(jsRegExp("[.*+?^\${}()|[\\]\\\\]", "g"), "\\\$&")
    })).join("|")
    global_CompanionMentionPattern = jsConstruct(global_RegExp, arrayOf<dynamic>(global_CompanionMentionSource, "gi"))
    global_CompanionMentionCheck = jsConstruct(global_RegExp, arrayOf<dynamic>(global_CompanionMentionSource, "i"))
    global_profileForm = (fun(arg56: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg56) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V92Runtime.profileForm(initial)
        val current: dynamic = jsOr(onPresent(global_state.profile) { value59 -> value59.companionCharacter }) { "knight" }
        val grid: dynamic = box.querySelector(".companion-picker")
        val `field`: dynamic = box.querySelector(".companion-picker-block")
        if (truth(grid)) {
            grid.dataset.companionStatic = "true"
            grid.querySelectorAll("input[name=companionCharacter]").forEach((fun(input: dynamic): dynamic {
                return input.setAttribute("onchange", "previewCompanionChoice(this)")
            }))
            onPresent(`field`) { value62 -> onPresent(value62.querySelector(".footnote")) { value63 -> value63.insertAdjacentHTML("afterend", ("<div class=\"companion-picker-summary\"><span class=\"eyebrow\">COMPAÑERO SELECCIONADO</span><strong data-selected-companion-label>" + textOf(global_companionDisplayName(current)) + "</strong><small>Solo puede cambiarse desde Perfil. El nombre y el avatar se sincronizan con Inicio, Misiones y el asistente.</small></div>")) } }
        }
        global_personalizeCompanionText(box, current)
        return box.innerHTML
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_V92Runtime.modal(html)
        global_decorateModal()
        return absent
    })
    global_refreshMission = (fun(): dynamic {
        global_V92Runtime.refreshMission()
        if (truth(onPresent(global_Dollar("#modal")) { value90 -> value90["open"] })) {
            global_decorateModal()
        }
        return absent
    })
    global_technique = (fun(id: dynamic): dynamic {
        global_V92Runtime.technique(id)
        global_decorateModal()
        return absent
    })
    global_bodyResults = (fun(arg95: dynamic): dynamic {
        var q: dynamic = absent
        q = definedOr(arg95) { "" }
        return global_personalizeCompanionString(global_V92Runtime.bodyResults(q))
    })
    global_modalDialog = global_Dollar("#modal")
    onPresent(global_modalDialog) { value96 -> value96.addEventListener("close", global_unlockModalViewport) }
    onPresent(global_modalDialog) { value97 -> value97.addEventListener("cancel", (fun(event: dynamic): dynamic {
        event.preventDefault()
        global_closeActiveModal()
        return absent
    })) }
    onPresent(global_window.visualViewport) { value98 -> value98.addEventListener("resize", global_fitActiveModal, jsonValue("{\"passive\":true}")) }
    global_window.addEventListener("resize", global_fitActiveModal, jsonValue("{\"passive\":true}"))
    global_home = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V92Runtime.home()
        return global_addCompanionAndInjuryContext(box).innerHTML
    })
    global_plan = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V92Runtime.plan()
        return global_addCompanionAndInjuryContext(box).innerHTML
    })
    global_openCoach = (fun(): dynamic {
        global_V92Runtime.openCoach()
        global_decorateModal()
        val row: dynamic = onPresent(global_Dollar("#modal")) { value118 -> value118.querySelector(".row.wrap") }
        val summary: dynamic = global_injuryFocusSummary()
        if (truth(jsAnd(jsAnd(row) { summary }) { (!truth(row.querySelector("[data-injury-routine]"))) })) {
            row.insertAdjacentHTML("beforeend", "<button class=\"btn ghost\" data-injury-routine onclick=\"askCoach('crear rutina según mis lesiones')\">Rutina según mis restricciones</button>")
        }
        global_personalizeCompanionText(global_Dollar("#modal"))
        global_fitActiveModal()
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        val p: dynamic = global_state.profile
        val plain: dynamic = global_String(jsOr(q) { "" }).toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "")
        val active: dynamic = global_Adapt.active(p)
        if (truth(jsAnd(active.length) { dynamicValue(jsRegExp("(rutina|plan|entren|lesion|dolor|molest|restric|grupo muscular)", "")).test(plain) })) {
            val summary: dynamic = global_injuryFocusSummary(p)
            val name: dynamic = global_companionDisplayName()
            if (truth((!truth(summary.auto)))) {
                return ("" + textOf(name) + ": " + textOf(summary.text) + " No asignaré ejercicios automáticamente mientras ese estado siga activo. Puedes conservar tus misiones personales y registrar las indicaciones recibidas.")
            }
            val kinds: dynamic = jsonValue("[\"strength\",\"cardio\",\"recovery\"]")
            val rotation: dynamic = jsAdd(jsMultiply(jsOr(onPresent(global_currentCycle()) { value127 -> value127.index }) { 0 }, 7), global_Math.max(0, global_E.diff(global_E.dateKey(), jsOr(onPresent(global_currentCycle()) { value128 -> value128.start }) { global_E.dateKey() })))
            val previews: dynamic = kinds.map((fun(kind: dynamic, i: dynamic): dynamic {
                return jsObject { obj130 ->
                    obj130["kind"] = kind
                    obj130["items"] = global_Schedule.items(global_GoalPlans.adjusted(p), kind, jsAdd(rotation, i))
                }
            })).filter((fun(x: dynamic): dynamic {
                return x.items.length
            }))
            val labels: dynamic = jsonValue("{\"strength\":\"fuerza\",\"cardio\":\"cardio\",\"recovery\":\"movilidad\"}")
            val proposal: dynamic = previews.map((fun(x: dynamic): dynamic {
                return ("" + textOf(labels[x.kind]) + ": " + textOf(x.items.map((fun(y: dynamic): dynamic {
                    return global_E.byId(y.id).name
                })).join(", ")) + "")
            })).join("\n")
            return ("" + textOf(name) + ": " + textOf(summary.text) + "\n" + textOf(jsOr(proposal) { "No encontré ejercicios compatibles suficientes con los filtros actuales." }) + "\nLas sesiones rotan ejercicios compatibles para no repetir siempre la misma combinación. Si aparece dolor o empeoran los síntomas, detén esa sesión y actualiza tus restricciones. Esto no crea un tratamiento de rehabilitación.")
        }
        return global_personalizeCompanionString(global_V92Runtime.answerCoach(q))
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V92Runtime.render()
        global_personalizeCompanionText(global_Dollar("#app"))
        global_document.querySelectorAll(".brand small").forEach((fun(el: dynamic): dynamic {
            return run {
                val target133: dynamic = el
                val assigned135: dynamic = "EL DESPERTAR · V9.6"
                target133.textContent = assigned135
                assigned135
            }
        }))
        return absent
    })
    global_state.appVersion = 9
    global_render()
    global_syncNative()
}
