@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterprofile3835

import hunter.browser.*

@JsName("render") private external var global_render: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterProfile3835() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var dialog: dynamic = absent
        var awaiting: dynamic = absent
        var acknowledged: dynamic = absent
        var deferredRender: dynamic = absent
        var confirmations: dynamic = absent
        var active: dynamic = absent
        var arm: dynamic = absent
        var acknowledge: dynamic = absent
        active = fun(): dynamic {
            return jsAnd(jsAnd(awaiting) { (!truth(acknowledged)) }) { (!truth((!truth(dialog.querySelector(".profile-confirm377"))))) }
        }
        arm = fun(): dynamic {
            val pane: dynamic = dialog.querySelector(".profile-confirm377")
            if (truth(jsOr(jsOr((!truth(pane))) { (!truth(dialog["open"])) }) { jsAnd(awaiting) { (!truth(acknowledged)) } })) {
                return absent
            }
            awaiting = true
            acknowledged = false
            run {
                val previous14: dynamic = numeric(confirmations)
                confirmations = jsAdd(previous14, 1)
            }
            dialog.classList.add("profile-confirm-wait3835")
            dialog.setAttribute("aria-label", "Confirmación del perfil guardado")
            val button: dynamic = pane.querySelector("button[onclick*=\"closeActiveModal\"],button.btn.primary")
            if (truth(button)) {
                button.removeAttribute("onclick")
                button.textContent = "Confirmar y continuar"
                button.setAttribute("aria-label", "Confirmar el mensaje de perfil guardado y continuar")
                button.addEventListener("click", acknowledge, jsonValue("{\"once\":true}"))
            }
            return absent
        }
        acknowledge = fun(): dynamic {
            if (truth((!truth(active())))) {
                return false
            }
            acknowledged = true
            awaiting = false
            dialog.classList.remove("profile-confirm-wait3835")
            dialog.removeAttribute("aria-label")
            dialog.close()
            if (truth(deferredRender)) {
                deferredRender = false
                root.requestAnimationFrame((fun(): dynamic {
                    return global_render()
                }))
            }
            return true
        }
        if (truth(jsOr(jsOr(jsOr(root.HunterProfileConfirmation3835) { (js("typeof submitProfile") !== "function") }) { (js("typeof render") !== "function") }) { (js("typeof modal") !== "function") })) {
            return absent
        }
        dialog = doc.getElementById("modal")
        if (truth((!truth(dialog)))) {
            return absent
        }
        var saving: dynamic = false
        awaiting = false
        acknowledged = false
        deferredRender = false
        confirmations = 0
        var rejectedCloses: dynamic = 0
        val baseClose: dynamic = dialog.close.bind(dialog)
        dialog.close = (fun(`value`: dynamic): dynamic {
            if (truth(active())) {
                run {
                    val previous9: dynamic = numeric(rejectedCloses)
                    rejectedCloses = jsAdd(previous9, 1)
                }
                return absent
            }
            return baseClose(`value`)
        })
        val renderBefore: dynamic = global_render
        global_render = (fun(): dynamic {
            val receiver10: dynamic = js("this")
            val callArgs11: dynamic = js("arguments")
            if (truth(jsAnd(saving) { active() })) {
                deferredRender = true
                return absent
            }
            return renderBefore.apply(receiver10, callArgs11)
        })
        val modalBefore: dynamic = global_modal
        global_modal = (fun(html: dynamic): dynamic {
            val receiver19: dynamic = js("this")
            val callArgs20: dynamic = js("arguments")
            val answer: dynamic = modalBefore.apply(receiver19, callArgs20)
            if (truth(jsAnd((jsTypeOf(html) === "string")) { html.includes("profile-confirm377") })) {
                arm()
            }
            return answer
        })
        val submitBefore: dynamic = global_submitProfile
        global_submitProfile = (fun(event: dynamic): dynamic {
            val receiver21: dynamic = js("this")
            val callArgs22: dynamic = js("arguments")
            if (truth((onPresent(event) { value23 -> onPresent(value23.target) { value24 -> value24.id } } !== "profileForm"))) {
                return submitBefore.apply(receiver21, callArgs22)
            }
            saving = true
            try {
                return submitBefore.apply(receiver21, callArgs22)
            } finally {
                saving = false
                if (truth(dialog["open"])) {
                    arm()
                }
            }
            return absent
        })
        root.addEventListener("cancel", (fun(event: dynamic): dynamic {
            if (truth(jsAnd((event.target === dialog)) { active() })) {
                event.preventDefault()
                event.stopImmediatePropagation()
            }
            return absent
        }), true)
        dialog.addEventListener("click", (fun(event: dynamic): dynamic {
            if (truth(jsAnd(active()) { event.target.closest(".modal-close-x,.modal-back24,.legacy-modal-close") })) {
                event.preventDefault()
                event.stopImmediatePropagation()
            }
            return absent
        }), true)
        root.HunterProfileConfirmation3835 = global_Object.freeze(jsObject { obj31 ->
            obj31["version"] = "38.35"
            obj31["acknowledge"] = acknowledge
            obj31["diagnostics"] = (fun(): dynamic {
                return jsObject { obj32 ->
                    obj32["confirmations"] = confirmations
                    obj32["rejectedCloses"] = rejectedCloses
                    obj32["awaiting"] = awaiting
                    obj32["deferredRender"] = deferredRender
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
