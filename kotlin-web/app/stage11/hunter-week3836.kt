@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterweek3836

import hunter.browser.*

@JsName("Array") private external var global_Array: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("V4") private external var global_V4: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterWeek3836() {
    dynamicValue((fun(root: dynamic): dynamic {
        var STATES: dynamic = absent
        var LABEL: dynamic = absent
        var SHORT: dynamic = absent
        var DAYNAMES: dynamic = absent
        var updatePreview: dynamic = absent
        var setDay: dynamic = absent
        var swapDay3836: dynamic = absent
        updatePreview = fun(form: dynamic): dynamic {
            val days: dynamic = global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, d: dynamic): dynamic {
                return jsOr(onPresent(form.querySelector(jsAdd(jsAdd("[data-day=\"", d), "\"].day-choice376"))) { value10 -> value10.dataset.kind }) { "rest" }
            }))
            val count: dynamic = global_Object.fromEntries(STATES.map((fun(k: dynamic): dynamic {
                return arrayOf<dynamic>(k, days.filter((fun(v: dynamic): dynamic {
                    return (v === k)
                })).length)
            })))
            val line: dynamic = form.querySelector("#day-summary376")
            if (truth(line)) {
                line.textContent = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(count.upper, " superior · "), count.lower), " inferior · "), count.core), " central · "), count.cardio), " cardio · "), count.mobility), " movilidad · "), count.rest), " descanso.")
            }
            return absent
        }
        setDay = fun(button: dynamic, kind: dynamic): dynamic {
            val day: dynamic = global_Number(onPresent(button) { value16 -> value16.dataset.day })
            val form: dynamic = onPresent(button) { value18 -> value18.form }
            if (truth(jsOr(jsOr(jsOr(jsOr((!truth(form))) { (!truth(global_Number.isInteger(day))) }) { jsLess(day, 0) }) { jsMore(day, 6) }) { (!truth(STATES.includes(kind))) })) {
                return absent
            }
            val hidden: dynamic = form.elements[jsAdd("day", day)]
            val focus: dynamic = form.elements[jsAdd("weekFocus", day)]
            if (truth(jsOr((!truth(hidden))) { (!truth(focus)) })) {
                return absent
            }
            hidden["value"] = (if (truth(dynamicValue(jsonValue("[\"upper\",\"lower\",\"core\"]")).includes(kind))) "train" else kind)
            focus["value"] = (if (truth(dynamicValue(jsonValue("[\"upper\",\"lower\",\"core\"]")).includes(kind))) kind else "none")
            button.dataset.kind = kind
            button.setAttribute("aria-label", jsAdd(jsAdd(jsAdd(DAYNAMES[day], ": "), LABEL[kind]), ". Pulsar para cambiar."))
            button.setAttribute("aria-pressed", global_String((kind !== "rest")))
            val label: dynamic = button.querySelector(".day-type376")
            if (truth(label)) {
                label.textContent = SHORT[kind]
            }
            updatePreview(form)
            return absent
        }
        swapDay3836 = fun(button: dynamic): dynamic {
            val index: dynamic = STATES.indexOf(jsOr(onPresent(button) { value29 -> value29.dataset.kind }) { "rest" })
            setDay(button, STATES[jsMod(jsAdd(index, 1), STATES.length)])
            return absent
        }
        if (truth(root.HunterWeek3836)) {
            return absent
        }
        STATES = jsonValue("[\"rest\",\"upper\",\"lower\",\"core\",\"cardio\",\"mobility\"]")
        LABEL = jsonValue("{\"rest\":\"Descanso\",\"upper\":\"Tren superior\",\"lower\":\"Tren inferior\",\"core\":\"Tren central\",\"cardio\":\"Cardio\",\"mobility\":\"Movilidad\"}")
        SHORT = jsonValue("{\"rest\":\"DESC\",\"upper\":\"SUPERIOR\",\"lower\":\"INFERIOR\",\"core\":\"CENTRAL\",\"cardio\":\"CARDIO\",\"mobility\":\"MOVIL\"}")
        DAYNAMES = jsonValue("[\"Domingo\",\"Lunes\",\"Martes\",\"Miércoles\",\"Jueves\",\"Viernes\",\"Sábado\"]")
        val safeDays: dynamic = (fun(p: dynamic): dynamic {
            return jsOr(onPresent(root.Schedule) { value3 -> run { val owner5: dynamic = value3; onPresent(owner5.defaults) { method4 -> invokeWith(method4, owner5, arrayOf<dynamic>(p)) } } }) { jsonValue("[\"rest\",\"train\",\"rest\",\"train\",\"rest\",\"rest\",\"rest\"]") }
        })
        val fromStored: dynamic = (fun(p: dynamic, day: dynamic): dynamic {
            val week: dynamic = safeDays(p)
            val kind: dynamic = week[day]
            if (truth((kind !== "train"))) {
                return (if (truth(STATES.includes(kind))) kind else "rest")
            }
            return (if (truth(dynamicValue(jsonValue("[\"lower\",\"core\"]")).includes(onPresent(p) { value6 -> onPresent(value6.weekFocus) { value7 -> value7[day] } }))) p.weekFocus[day] else "upper")
        })
        root.swapDay3836 = swapDay3836
        val oldForm: dynamic = global_profileForm
        global_profileForm = (fun(arg35: dynamic): dynamic {
            var initial: dynamic = absent
            initial = definedOr(arg35) { false }
            val holder: dynamic = global_document.createElement("div")
            holder.innerHTML = oldForm(initial)
            val form: dynamic = holder.querySelector("#profileForm")
            if (truth((!truth(form)))) {
                return holder.innerHTML
            }
            val week: dynamic = form.querySelector(".week-editor .day-week376")
            if (truth((!truth(week)))) {
                return holder.innerHTML
            }
            val profile: dynamic = jsOr(onPresent(global_state) { value38 -> value38.profile }) { js("({})") }
            if (true) {
                val iterator39: dynamic = iteratorOf(week.querySelectorAll(".day-choice376"))
                while (true) {
                    val next40: dynamic = iterator39.next()
                    if (truth(next40.done)) break
                    var button: dynamic = absent
                    button = next40.value
                    val day: dynamic = global_Number(button.dataset.day)
                    if (truth(jsOr(jsOr((!truth(global_Number.isInteger(day)))) { jsLess(day, 0) }) { jsMore(day, 6) })) {
                        continue
                    }
                    val hidden: dynamic = form.elements[jsAdd("day", day)]
                    if (truth((!truth(hidden)))) {
                        continue
                    }
                    val focus: dynamic = global_document.createElement("input")
                    focus.type = "hidden"
                    focus.name = jsAdd("weekFocus", day)
                    hidden.insertAdjacentElement("afterend", focus)
                    button.setAttribute("onclick", "swapDay3836(this)")
                    setDay(button, fromStored(profile, day))
                }
            }
            val guide: dynamic = form.querySelector(".day-guide376 p")
            if (truth(guide)) {
                guide.innerHTML = "Toca cada día para elegir: <strong>Descanso → Tren superior → Tren inferior → Tren central → Cardio → Movilidad</strong>."
            }
            val legend: dynamic = global_document.createElement("div")
            legend.className = "week-legend3836"
            legend.setAttribute("aria-label", "Leyenda de tipos de entrenamiento")
            legend.innerHTML = jsAdd("<strong class=\"week-legend-title3836\">Leyenda de actividades</strong>", dynamicValue(jsonValue("[[\"upper\",\"Tren superior\",\"Azul · pecho, espalda, hombros y brazos. Ej.: flexiones y remo.\"],[\"lower\",\"Tren inferior\",\"Rosa · piernas y glúteos. Ej.: sentadillas y puente de glúteos.\"],[\"core\",\"Tren central\",\"Lavanda · abdomen, oblicuos y estabilidad del tronco. Ej.: dead bug, planchas y Pallof.\"],[\"cardio\",\"Cardio\",\"Naranja · resistencia cardiovascular. Ej.: marcha y pasos activos.\"],[\"mobility\",\"Movilidad\",\"Verde · movilidad articular y amplitud cómoda de movimiento.\"],[\"rest\",\"Descanso\",\"Gris · recuperación y descanso programado.\"]]")).map((fun(arg53: dynamic): dynamic {
                var kind: dynamic = absent
                var name: dynamic = absent
                var description: dynamic = absent
                val unpacked54: dynamic = fromIterable(arg53)
                kind = unpacked54[0]
                name = unpacked54[1]
                description = unpacked54[2]
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<div class=\"week-legend-item3836\"><i class=\"week-legend-swatch3836 ", kind), "\" aria-hidden=\"true\"></i><span><b>"), name), "</b><small>"), description), "</small></span></div>")
            })).join(""))
            dynamicValue(jsOr(form.querySelector("#day-summary376")) { week }).insertAdjacentElement("afterend", legend)
            val foot: dynamic = onPresent(week.parentElement) { value55 -> value55.querySelector(".footnote") }
            if (truth(foot)) {
                foot.textContent = "El tipo elegido se aplica a la misión al guardar el perfil. Los ejercicios completados y las sesiones personalizadas conservan sus registros."
            }
            updatePreview(form)
            return holder.innerHTML
        })
        if (truth(jsAnd((js("typeof V4") === "object")) { (jsTypeOf(global_V4.submitProfile) === "function") })) {
            val before: dynamic = global_V4.submitProfile
            global_V4.submitProfile = (fun(event: dynamic): dynamic {
                val form: dynamic = onPresent(event) { value62 -> value62.target }
                val focuses: dynamic = global_Array.from(jsonValue("{\"length\":7}"), (fun(_: dynamic, d: dynamic): dynamic {
                    val type: dynamic = onPresent(form) { value63 -> onPresent(value63.elements) { value64 -> onPresent(value64[jsAdd("day", d)]) { value65 -> value65["value"] } } }
                    val focus: dynamic = onPresent(form) { value66 -> onPresent(value66.elements) { value67 -> onPresent(value67[jsAdd("weekFocus", d)]) { value68 -> value68["value"] } } }
                    return (if (truth((type === "train"))) (if (truth(dynamicValue(jsonValue("[\"lower\",\"core\"]")).includes(focus))) focus else "upper") else "none")
                }))
                val result: dynamic = before(event)
                if (truth(jsAnd((onPresent(form) { value69 -> value69.id } === "profileForm")) { onPresent(global_state) { value70 -> value70.profile } })) {
                    global_state.profile.weekFocus = focuses
                    if (truth(jsAnd((js("typeof save") === "function")) { (!truth(onPresent(root.Runtime20) { value73 -> value73.initializing })) })) {
                        global_save()
                    }
                }
                return result
            })
        }
        root.HunterWeek3836 = global_Object.freeze(jsObject { obj76 ->
            obj76["version"] = "38.36"
            obj76["fromStored"] = fromStored
            obj76["states"] = jsArray { array77 ->
                appendAll(array77, STATES)
            }
            obj76["setDay"] = setDay
            obj76["updatePreview"] = updatePreview
        })
        return absent
    }))(global_window)
}
