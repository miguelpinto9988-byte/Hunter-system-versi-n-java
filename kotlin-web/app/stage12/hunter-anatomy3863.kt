@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunteranatomy3863

import hunter.browser.*

@JsName("technique") private external var global_technique: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("performance") private external var global_performance: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterAnatomy3863() {
    dynamicValue((fun(root: dynamic, document: dynamic): dynamic {
        var warnings: dynamic = absent
        var active: dynamic = absent
        var stats: dynamic = absent
        var update: dynamic = absent
        update = fun(force: dynamic): dynamic {
            val session: dynamic = active
            if (truth(jsOr(jsOr((!truth(session))) { (!truth(session.panel.isConnected)) }) { (!truth(session.on)) })) {
                return absent
            }
            val player: dynamic = (if (truth((js("typeof vectorSession") !== "undefined"))) global_vectorSession else null)
            if (truth(jsOr((!truth(player))) { (player.id !== session.id) })) {
                return absent
            }
            val frame: dynamic = jsOr(global_Number(player.frame)) { 0 }
            val now: dynamic = global_performance.now()
            if (truth(jsAnd(jsAnd((!truth(force))) { jsLess(jsSubtract(now, session.lastAt), 85) }) { jsLess(global_Math.abs(jsSubtract(frame, session.lastFrame)), 1.1) })) {
                return absent
            }
            session.lastFrame = frame
            session.lastAt = now
            try {
                val markup: dynamic = root.HunterDemos46.anatomicalSvg(session.id, frame, (!truth((!truth(player.mirror)))))
                if (truth((!truth(markup)))) {
                    session.status.textContent = "Cargando el modelo local…"
                    return absent
                }
                session.preview.innerHTML = markup
                session.status.textContent = (if (truth(warnings.has(session.id))) "Modelo con articulaciones principales estáticas; los movimientos pequeños y las contracciones sostenidas requieren consultar el esquema original." else "Vista ilustrativa basada en los fotogramas originales. El equipo y los apoyos deben comprobarse en el esquema técnico.")
                run {
                    val target15: dynamic = stats
                    val previous17: dynamic = numeric(target15.frames)
                    target15.frames = jsAdd(previous17, 1)
                }
            } catch (e: dynamic) {
                session.status.textContent = "La vista anatómica no está disponible para este modelo. Se conserva el esquema técnico."
                run {
                    val target20: dynamic = stats
                    val previous22: dynamic = numeric(target20.failures)
                    target20.failures = jsAdd(previous22, 1)
                }
                session.toggle(false)
            }
            return absent
        }
        if (truth(jsOr(jsOr(root.HunterComparador3863) { (js("typeof technique") !== "function") }) { (!truth(root.HunterDemos46)) })) {
            return absent
        }
        val originalTechnique: dynamic = global_technique
        val originalPaint: dynamic = global_vectorPaint
        val requiresReview: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"hs379-isobiceps\",\"hs379-palms\",\"new46-abwheel\",\"new46-airplanefree\",\"new46-ankleevert\",\"new46-ankleinvert\",\"new46-bandd1\",\"new46-banddiagonal\",\"new46-bander\",\"new46-bandir\",\"new46-bandplantar\",\"new46-bandpullapart\",\"new46-bigtoestretch\",\"new46-bottomclean\",\"new46-cableer90\",\"new46-chinup\",\"new46-commandopull\",\"new46-copenhagen\",\"new46-crosscabletri\",\"new46-crossshoulder\",\"new46-cubanpress\",\"new46-cubanrotation\",\"new46-deepshift\",\"new46-faceer\",\"new46-forearmlever\",\"new46-goodmorning\",\"new46-halo\",\"new46-hangclean\",\"new46-hangingwiper\",\"new46-hipcarquad\",\"new46-jefferson\",\"new46-kneelchop\",\"new46-kneellift\",\"new46-laddercarioca\",\"new46-landminetwist\",\"new46-lateralhurdle\",\"new46-lsit\",\"new46-mbcatch\",\"new46-mbrotation\",\"new46-meadowsrow\",\"new46-mediannerve\",\"new46-overheadsquat\",\"new46-pallofwalk\",\"new46-pelvictilt\",\"new46-peronealnerve\",\"new46-pivotlunge\",\"new46-pronehipir\",\"new46-reverseclam\",\"new46-ringsrotation\",\"new46-ringturnout\",\"new46-ropealternating\",\"new46-ropecircles\",\"new46-rotationalrow\",\"new46-seatedhinge\",\"new46-seatedpallof\",\"new46-shortfoot\",\"new46-shouldercar\",\"new46-shuttle\",\"new46-sidecrow\",\"new46-sidepallof\",\"new46-sitthrough\",\"new46-supineshoulderir\",\"new46-tdrill\",\"new46-tendonglides\",\"new46-thumbopposition\",\"new46-toetowel\",\"new46-toeyoga\",\"new46-tricepsstretch\",\"new46-trxfly\",\"new46-trxhighrow\",\"new46-tucklever\",\"new46-turkish\",\"new46-turnjump\",\"new46-ulnarnerve\",\"new46-wristcurl\",\"new46-wristdeviation\",\"new46-wristexte\",\"new46-zercher\"]")))
        warnings = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"hs379-isobiceps\",\"hs379-palms\",\"new46-ankleevert\",\"new46-ankleinvert\",\"new46-bandplantar\",\"new46-bigtoestretch\",\"new46-copenhagen\",\"new46-forearmlever\",\"new46-lsit\",\"new46-pelvictilt\",\"new46-peronealnerve\",\"new46-ringturnout\",\"new46-shortfoot\",\"new46-tendonglides\",\"new46-thumbopposition\",\"new46-toetowel\",\"new46-toeyoga\",\"new46-tricepsstretch\",\"new46-tucklever\",\"new46-wristcurl\",\"new46-wristdeviation\",\"new46-wristexte\"]")))
        active = dynamicValue(null)
        stats = jsonValue("{\"opened\":0,\"frames\":0,\"failures\":0}")
        global_vectorPaint = (fun(): dynamic {
            val receiver23: dynamic = js("this")
            val callArgs24: dynamic = js("arguments")
            val result: dynamic = originalPaint.apply(receiver23, callArgs24)
            update(false)
            return result
        })
        global_technique = (fun(id: dynamic): dynamic {
            val receiver25: dynamic = js("this")
            val callArgs26: dynamic = js("arguments")
            var toggle: dynamic = absent
            if (truth(active)) {
                active = dynamicValue(null)
            }
            val response: dynamic = originalTechnique.apply(receiver25, callArgs26)
            if (truth((!truth(dynamicValue(jsRegExp("^(?:new46-|hs379-)", "")).test(id))))) {
                return response
            }
            val panel: dynamic = document.querySelector("#modal .motion-card18")
            val canvas: dynamic = onPresent(panel) { value27 -> value27.querySelector("#vectorCanvas") }
            if (truth(jsOr((!truth(canvas))) { (jsTypeOf(root.HunterDemos46.anatomicalSvg) !== "function") })) {
                return response
            }
            if (truth(requiresReview.has(id))) {
                val note: dynamic = document.createElement("p")
                note.className = "footnote hunter-quality-warning3863"
                note.textContent = (if (truth(warnings.has(id))) "Demostración con articulaciones principales estáticas o movimiento localizado: conserva el esquema técnico y revisa las instrucciones. No se simulan movimientos inexistentes." else "La reconstrucción anatómica de este ejercicio necesita revisión de postura, apoyos o geometría. Se conserva la demostración técnica original.")
                panel.insertBefore(note, canvas)
                return response
            }
            val toolbar: dynamic = document.createElement("div")
            toolbar.className = "hunter-compare3863"
            val button: dynamic = document.createElement("button")
            button.type = "button"
            button.className = "btn hunter-compare-button3863"
            button.textContent = "Ver figura anatómica detallada"
            button.setAttribute("aria-pressed", "false")
            val description: dynamic = document.createElement("p")
            description.className = "footnote"
            description.textContent = "Comparación opcional. El esquema original es la referencia para los apoyos, el equipo y el movimiento localizado."
            val preview: dynamic = document.createElement("div")
            preview.className = "motion-svg18 hunter-anatomy3863"
            preview.hidden = true
            preview.setAttribute("aria-label", "Vista anatómica ilustrativa del movimiento")
            val status: dynamic = document.createElement("p")
            status.className = "footnote hunter-compare-status3863"
            status.hidden = true
            toolbar.append(button, description, preview, status)
            panel.insertBefore(toolbar, canvas)
            val session: dynamic = jsObject { obj52 ->
                obj52["id"] = id
                obj52["panel"] = panel
                obj52["preview"] = preview
                obj52["status"] = status
                obj52["button"] = button
                obj52["on"] = false
                obj52["lastAt"] = 0
                obj52["lastFrame"] = (-numeric(2))
                obj52["toggle"] = dynamicValue(null)
            }
            toggle = (fun(on: dynamic): dynamic {
                if (truth((active !== session))) {
                    return absent
                }
                session.on = global_Boolean(on)
                panel.classList.toggle("hunter-anatomy-on3863", session.on)
                preview.hidden = (!truth(session.on))
                status.hidden = (!truth(session.on))
                button.setAttribute("aria-pressed", global_String(session.on))
                button.textContent = (if (truth(session.on)) "Volver al esquema técnico original" else "Ver figura anatómica detallada")
                if (truth(session.on)) {
                    root.HunterDemos46.load(id)["then"]((fun(): dynamic {
                        if (truth(jsAnd((active === session)) { session.on })) {
                            update(true)
                        }
                        return absent
                    }))["catch"]((fun(): dynamic {
                        if (truth((active === session))) {
                            status.textContent = "No se pudo cargar la vista comparativa."
                            toggle(false)
                        }
                        return absent
                    }))
                    run {
                        val target63: dynamic = stats
                        val previous65: dynamic = numeric(target63.opened)
                        target63.opened = jsAdd(previous65, 1)
                    }
                    update(true)
                } else {
                    preview.replaceChildren()
                }
                return absent
            })
            session.toggle = toggle
            active = session
            button.addEventListener("click", (fun(): dynamic {
                return toggle((!truth(session.on)))
            }))
            return response
        })
        root.HunterComparador3863 = global_Object.freeze(jsObject { obj70 ->
            obj70["stats"] = (fun(): dynamic {
                return jsObject { obj71 ->
                    copyFields(obj71, stats)
                }
            })
            obj70["audit"] = (fun(): dynamic {
                return jsObject { obj72 ->
                    obj72["extended"] = 200
                    obj72["noMainJointDisplacement"] = warnings.size
                    obj72["compareAvailable"] = jsSubtract(200, requiresReview.size)
                    obj72["underReview"] = requiresReview.size
                }
            })
            obj70["reset"] = (fun(): dynamic {
                if (truth(active)) {
                    active.toggle(false)
                }
                return absent
            })
        })
        return absent
    }))(global_window, global_document)
}
