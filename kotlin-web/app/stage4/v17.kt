@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv17

import hunter.browser.*

@JsName("V17UI") private external var global_V17UI: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("openAcademy") private external var global_openAcademy: dynamic
@JsName("V13UI") private external var global_V13UI: dynamic
@JsName("academyObserver15") private external var global_academyObserver15: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("demoInfo16") private external var global_demoInfo16: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("ReferenceVideo15") private external var global_ReferenceVideo15: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("HDMotion16") private external var global_HDMotion16: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV17() {
    global_V17UI = jsObject { obj1 ->
        obj1["render"] = global_render
        obj1["answerCoach"] = global_answerCoach
    }
    global_openAcademy = (fun(): dynamic {
        global_V13UI.openAcademy()
        onPresent(global_academyObserver15) { value4 -> value4.disconnect() }
        val gallery: dynamic = global_Dollar("#modal .demo-gallery")
        if (truth((!truth(gallery)))) {
            return absent
        }
        gallery.innerHTML = global_E.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).map((fun(e: dynamic): dynamic {
            val d: dynamic = global_demoInfo16(e.id)
            val poster: dynamic = jsOr(onPresent(d) { value10 -> value10.poster }) { "media/poster17_reference.webp" }
            return ("<button class=\"demo-thumb\" onclick=\"technique('" + textOf(e.id) + "')\"><img class=\"model-poster17\" src=\"" + textOf(poster) + "\" loading=\"lazy\" decoding=\"async\" width=\"" + textOf(jsOr(onPresent(d) { value11 -> value11.width }) { 720 }) + "\" height=\"" + textOf(jsOr(onPresent(d) { value12 -> value12.height }) { 1280 }) + "\" alt=\"Vista de " + textOf(global_esc(e.name)) + "\"><b>" + textOf(global_esc(e.name)) + "</b></button>")
        })).join("")
        gallery.insertAdjacentHTML("beforebegin", ("<button class=\"btn primary full\" onclick=\"technique('" + textOf(global_ReferenceVideo15.exerciseId) + "')\">Ver el video original incluido</button>"))
        val foot: dynamic = global_Dollar("#modal .footnote")
        if (truth(foot)) {
            foot.textContent = "Demostraciones locales con tu modelo. Las variantes comparten una base; lee también las instrucciones de cada ficha."
        }
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V17UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(e: dynamic): dynamic {
            return run {
                val target17: dynamic = e
                val assigned19: dynamic = "EL DESPERTAR · V17"
                target17.textContent = assigned19
                assigned19
            }
        }))
        return absent
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("animaci[oó]n|velocidad|modelo.*3d|visor|demostraci[oó]n|3d|video", "i")).test(q))) {
            return ("" + textOf(global_companionDisplayName()) + ": Las " + textOf(global_E.exercises.length) + " fichas enlazan a videos locales: " + textOf(global_Object.keys(global_HDMotion16).length) + " movimientos base con el modelo que aportaste y el clip original de elevación frontal sentada. Las variantes comparten la base. Hay 20 movimientos con vista lateral adicional. Puedes reproducir a 0,5×, 1× o 1,5×; esto no cambia la dosis ni el reloj de la sesión. Mi personaje sigue en 2D. Las demostraciones no evalúan tu ejecución ni están validadas clínicamente.")
        }
        return global_V17UI.answerCoach(q)
    })
    global_state.appVersion = 17
    global_save()
    global_render()
}
