@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv32

import hunter.browser.*

@JsName("V32UI") private external var global_V32UI: dynamic
@JsName("Transition32") private external var global_Transition32: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Back25") private external var global_Back25: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("gymFields23") private external var global_gymFields23: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("Evolution28") private external var global_Evolution28: dynamic
@JsName("Ambient29") private external var global_Ambient29: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV32() {
    global_V32UI = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["render"] = global_render
        obj1["modal"] = global_modal
        obj1["openDiagnostics20"] = global_openDiagnostics20
    }
    global_Transition32 = jsObject { obj2 ->
        obj2["active"] = jsConstruct(global_Set, js("[]"))
        obj2["cancel"] = fun(): dynamic {
            val receiver3: dynamic = js("this")
            if (true) {
                val iterator5: dynamic = iteratorOf(receiver3.active)
                while (true) {
                    val next6: dynamic = iterator5.next()
                    if (truth(next6.done)) break
                    var a: dynamic = absent
                    a = next6.value
                    a.cancel()
                }
            }
            receiver3.active.clear()
            return absent
        }
        obj2["play"] = fun(nodes: dynamic): dynamic {
            val receiver7: dynamic = js("this")
            if (truth(jsOr(jsOr((!truth(global_Back25.enabled()))) { (!truth(global_document.documentElement.classList.contains("ready20"))) }) { global_document.hidden })) {
                return absent
            }
            if (true) {
                val iterator9: dynamic = iteratorOf(nodes)
                while (true) {
                    val next10: dynamic = iterator9.next()
                    if (truth(next10.done)) break
                    var node: dynamic = absent
                    node = next10.value
                    if (truth(jsOr((!truth(node))) { (jsTypeOf(node.animate) !== "function") })) {
                        continue
                    }
                    val a: dynamic = node.animate(jsonValue("[{\"opacity\":0.96},{\"opacity\":1}]"), jsonValue("{\"duration\":160,\"easing\":\"ease-out\"}"))
                    receiver7.active.add(a)
                    a.finished["then"]((fun(): dynamic {
                        return receiver7.active.delete(a)
                    }), (fun(): dynamic {
                        return receiver7.active.delete(a)
                    }))
                }
            }
            return absent
        }
    }
    global_profileForm = (fun(arg13: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg13) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V32UI.profileForm(initial)
        if (true) {
            val iterator16: dynamic = iteratorOf(box.querySelectorAll(".profile-block"))
            while (true) {
                val next17: dynamic = iterator16.next()
                if (truth(next17.done)) break
                var block: dynamic = absent
                block = next17.value
                if (truth((onPresent(block.querySelector("h3")) { value18 -> value18.textContent.trim() } === "Objetivo y equipo"))) {
                    onPresent(block.querySelector(":scope > .step-number")) { value20 -> value20.remove() }
                }
            }
        }
        val form: dynamic = box.querySelector("#profileForm")
        if (truth(form)) {
            global_gymFields23(form)
        }
        return box.innerHTML
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_Transition32.cancel()
        global_V32UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target23: dynamic = n
                val assigned25: dynamic = "SISTEMA DE SOMBRAS"
                target23.textContent = assigned25
                assigned25
            }
        }))
        if (truth((!truth(global_Dollar("#modal")["open"])))) {
            global_Transition32.play(arrayOf<dynamic>(jsOr(global_Dollar("#app .scene")) { global_Dollar("#app main") }))
        }
        return absent
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_Transition32.cancel()
        global_V32UI.modal(html)
        global_ModalViewport.fit()
        global_Transition32.play(dynamicValue(jsArray { array29 ->
            appendAll(array29, global_Dollar("#modal").children)
        }).filter((fun(n: dynamic): dynamic {
            return (!truth(n.matches("button,.window-actions26,.window-frame26,.window-particles28,.ambient-particles29")))
        })))
        return absent
    })
    global_openDiagnostics20 = (fun(): dynamic {
        global_V32UI.openDiagnostics20()
        if (true) {
            val iterator32: dynamic = iteratorOf(global_Dollar("#modal").querySelectorAll("p"))
            while (true) {
                val next33: dynamic = iterator32.next()
                if (truth(next33.done)) break
                var p: dynamic = absent
                p = next33.value
                p.textContent = p.textContent.replace("Versión 30.0.", "Versión 32.0.")
            }
        }
        return absent
    })
    global_Dollar("#modal").addEventListener("hunter:modal-fit", (fun(): dynamic {
        global_Evolution28.layout()
        global_Ambient29.sync()
        return absent
    }))
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        return global_Transition32.cancel()
    }))
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        if (truth(global_document.hidden)) {
            global_Transition32.cancel()
        }
        return absent
    }))
    run { val owner37: dynamic = global_matchMedia("(prefers-reduced-motion: reduce)"); onPresent(owner37.addEventListener) { method36 -> invokeWith(method36, owner37, arrayOf<dynamic>("change", (fun(e: dynamic): dynamic {
        if (truth(e.matches)) {
            global_Transition32.cancel()
        }
        return absent
    }))) } }
    global_window.HunterV32 = jsObject { obj40 ->
        obj40["Transition32"] = global_Transition32
    }
    global_state.appVersion = 32
    global_save()
    global_render()
}
