@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterweek3837

import hunter.browser.*

@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterWeek3837() {
    dynamicValue((fun(root: dynamic): dynamic {
        if (truth(jsOr(root.HunterWeek3837) { (js("typeof profileForm") !== "function") })) {
            return absent
        }
        val previousForm: dynamic = global_profileForm
        val TYPES: dynamic = jsonValue("[[\"upper\",\"Tren superior\",\"Pecho, espalda, hombros y brazos. Ejercicios: flexiones, curl de bíceps, remo con mancuernas y elevaciones laterales.\"],[\"lower\",\"Tren inferior\",\"Piernas y glúteos. Ejercicios: sentadillas, zancadas, puente de glúteos y elevaciones de talones.\"],[\"core\",\"Tren central\",\"Lavanda · abdomen, oblicuos y estabilidad del tronco. Ejercicios: dead bug, planchas, Pallof y transportes; según nivel, equipo y restricciones.\"],[\"cardio\",\"Cardio\",\"Resistencia cardiovascular. Ejercicios: marcha activa, pasos laterales y elevaciones alternas de rodillas.\"],[\"mobility\",\"Movilidad\",\"Movilidad articular controlada. Ejercicios: círculos de hombros, movilidad de tobillos y movilidad de columna.\"],[\"rest\",\"Descanso\",\"Día reservado para recuperación; el generador no programa una rutina automática de entrenamiento.\"]]")
        global_profileForm = (fun(arg5: dynamic): dynamic {
            var initial: dynamic = absent
            initial = definedOr(arg5) { false }
            val container: dynamic = global_document.createElement("div")
            container.innerHTML = previousForm(initial)
            val form: dynamic = container.querySelector("#profileForm")
            if (truth((!truth(form)))) {
                return container.innerHTML
            }
            val weekly: dynamic = jsOr(jsOr(form.closest(".week-block")) { form.querySelector(".week-block") }) { form }
            if (truth((!truth(weekly)))) {
                return container.innerHTML
            }
            val globalFocus: dynamic = weekly.querySelector("select[name=\"focus\"]")
            if (truth(globalFocus)) {
                val hidden: dynamic = global_document.createElement("input")
                hidden.type = "hidden"
                hidden.name = "focus"
                hidden["value"] = globalFocus["value"]
                val label: dynamic = globalFocus.closest("label")
                if (truth(jsAnd(label) { weekly.contains(label) })) {
                    label.replaceWith(hidden)
                } else {
                    globalFocus.replaceWith(hidden)
                }
            }
            val legend: dynamic = weekly.querySelector(".week-legend3836")
            if (truth(legend)) {
                legend.replaceChildren()
                val heading: dynamic = global_document.createElement("strong")
                heading.className = "week-legend-title3836"
                heading.textContent = "Leyenda de actividades y ejercicios"
                legend.append(heading)
                if (true) {
                    val iterator18: dynamic = iteratorOf(TYPES)
                    while (true) {
                        val next19: dynamic = iterator18.next()
                        if (truth(next19.done)) break
                        var type: dynamic = absent
                        var name: dynamic = absent
                        var example: dynamic = absent
                        val unpacked20: dynamic = fromIterable(next19.value)
                        type = unpacked20[0]
                        name = unpacked20[1]
                        example = unpacked20[2]
                        val item: dynamic = global_document.createElement("div")
                        item.className = "week-legend-item3836"
                        val color: dynamic = global_document.createElement("i")
                        color.className = jsAdd("week-legend-swatch3836 ", type)
                        color.setAttribute("aria-hidden", "true")
                        val detail: dynamic = global_document.createElement("span")
                        val label: dynamic = global_document.createElement("b")
                        label.textContent = jsAdd(name, ":")
                        val text: dynamic = global_document.createElement("small")
                        text.textContent = example
                        detail.append(label, text)
                        item.append(color, detail)
                        legend.append(item)
                    }
                }
                val summary: dynamic = form.querySelector("#day-summary376")
                if (truth(jsAnd(summary) { (summary.nextElementSibling !== legend) })) {
                    summary.insertAdjacentElement("afterend", legend)
                }
            }
            val oldGuide: dynamic = weekly.querySelector(".day-guide376 p")
            if (truth(oldGuide)) {
                oldGuide.textContent = "Selecciona en cada día tren superior (azul), tren inferior (rosa), tren central (lavanda), cardio, movilidad o descanso. Toca el día para cambiar de opción."
            }
            return container.innerHTML
        })
        root.HunterWeek3837 = global_Object.freeze(jsObject { obj33 ->
            obj33["version"] = "38.37"
            obj33["types"] = TYPES.map((fun(t: dynamic): dynamic {
                return t[0]
            }))
        })
        return absent
    }))(global_window)
}
