@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterequipment3880

import hunter.browser.*

@JsName("Array") private external var global_Array: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("gymFields23") private external var global_gymFields23: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("V4") private external var global_V4: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterEquipment3880() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var keys: dynamic = absent
        var names: dynamic = absent
        var modes: dynamic = absent
        var fromForm: dynamic = absent
        var sync: dynamic = absent
        modes = fun(profile: dynamic): dynamic {
            if (truth(global_Array.isArray(onPresent(profile) { value5 -> value5.equipmentModes3880 }))) {
                return keys.filter((fun(k: dynamic): dynamic {
                    return profile.equipmentModes3880.includes(k)
                }))
            }
            if (truth((onPresent(profile) { value6 -> value6.equipment } === "gym"))) {
                return (if (truth(jsMore(global_Number(profile.load), 0))) jsonValue("[\"body\",\"db\",\"gym\"]") else jsonValue("[\"body\",\"gym\"]"))
            }
            if (truth((onPresent(profile) { value7 -> value7.equipment } === "db"))) {
                return jsonValue("[\"body\",\"db\"]")
            }
            return jsonValue("[\"body\"]")
        }
        fromForm = fun(form: dynamic): dynamic {
            return keys.filter((fun(k: dynamic): dynamic {
                return onPresent(form) { value10 -> value10.querySelector(jsAdd(jsAdd("input[name=\"equipmentModes3880\"][value=\"", k), "\"]:checked")) }
            }))
        }
        sync = fun(form: dynamic): dynamic {
            if (truth((!truth(onPresent(form) { value13 -> value13.querySelector(".equipment-modes3880") })))) {
                return absent
            }
            val selected: dynamic = fromForm(form)
            val legacy: dynamic = form.elements.namedItem("equipment")
            if (truth(legacy)) {
                legacy["value"] = (if (truth(selected.includes("gym"))) "gym" else (if (truth(selected.includes("db"))) "db" else "body"))
            }
            val gym: dynamic = form.querySelector(".gym-equipment23")
            if (truth(gym)) {
                gym.hidden = (!truth(selected.includes("gym")))
            }
            val load: dynamic = form.elements.namedItem("load")
            if (truth(load)) {
                load.disabled = (!truth(selected.includes("db")))
                val `field`: dynamic = load.closest(".field")
                if (truth(`field`)) {
                    `field`.hidden = (!truth(selected.includes("db")))
                }
            }
            val summary: dynamic = form.querySelector(".equipment-summary3880")
            if (truth(summary)) {
                summary.textContent = (if (truth(selected.length)) jsAdd(jsAdd("Modalidades activas: ", selected.map((fun(x: dynamic): dynamic {
                    return names[x]
                })).join(" + ")), ". Se combinarán ejercicios compatibles.") else "Marca al menos una modalidad antes de guardar.")
            }
            if (true) {
                val iterator24: dynamic = iteratorOf(form.querySelectorAll(".equipment-mode3880"))
                while (true) {
                    val next25: dynamic = iterator24.next()
                    if (truth(next25.done)) break
                    var box: dynamic = absent
                    box = next25.value
                    val checkbox: dynamic = box.querySelector("input")
                    box.classList.toggle("selected3880", global_Boolean(onPresent(checkbox) { value26 -> value26.checked }))
                }
            }
            return absent
        }
        if (truth(jsOr(jsOr(jsOr(root.HunterEquipment3880) { (js("typeof profileForm") !== "function") }) { (js("typeof submitProfile") !== "function") }) { (js("typeof V4") !== "object") })) {
            return absent
        }
        keys = jsonValue("[\"body\",\"db\",\"gym\"]")
        names = jsonValue("{\"body\":\"Peso corporal\",\"db\":\"Mancuernas\",\"gym\":\"Gimnasio\"}")
        val oldGym: dynamic = (if (truth((js("typeof gymFields23") === "function"))) global_gymFields23 else null)
        if (truth(oldGym)) {
            global_gymFields23 = (fun(form: dynamic): dynamic {
                oldGym(form)
                if (truth(onPresent(form) { value29 -> value29.querySelector(".equipment-modes3880") })) {
                    sync(form)
                }
                return absent
            })
        }
        val oldForm: dynamic = global_profileForm
        global_profileForm = (fun(arg32: dynamic): dynamic {
            var initial: dynamic = absent
            initial = definedOr(arg32) { false }
            val wrap: dynamic = doc.createElement("div")
            wrap.innerHTML = oldForm(initial)
            val form: dynamic = wrap.querySelector("#profileForm")
            if (truth((!truth(form)))) {
                return wrap.innerHTML
            }
            val legacy: dynamic = form.elements.namedItem("equipment")
            val `field`: dynamic = onPresent(legacy) { value35 -> value35.closest(".field") }
            if (truth(jsOr((!truth(`field`))) { form.querySelector(".equipment-modes3880") })) {
                return wrap.innerHTML
            }
            val panel: dynamic = doc.createElement("fieldset")
            panel.className = "equipment-modes3880"
            val current: dynamic = modes(jsOr(onPresent(global_state) { value38 -> value38.profile }) { js("({})") })
            panel.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd("<legend>Equipamiento para tus ejercicios</legend>", "<p class=\"footnote\">Puedes marcar varias opciones. Peso corporal y mancuernas se pueden utilizar juntos, también con gimnasio.</p>"), "<div class=\"equipment-grid3880\">"), keys.map((fun(k: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<label class=\"equipment-mode3880 ", (if (truth(current.includes(k))) "selected3880" else "")), "\">"), "<input type=\"checkbox\" name=\"equipmentModes3880\" value=\""), k), "\" "), (if (truth(current.includes(k))) "checked" else "")), " onchange=\"HunterEquipment3880.change(this.form)\">"), "<span class=\"equipment-mode-copy3880\"><strong>"), names[k]), "</strong><small>"), jsonValue("{\"body\":\"Ejercicios con tu propio cuerpo\",\"db\":\"Con los kilos de tus mancuernas\",\"gym\":\"Con las máquinas y material declarados\"}")[k]), "</small></span>"), "</label>")
            })).join("")), "</div><p class=\"footnote equipment-summary3880\" aria-live=\"polite\"></p>")
            `field`.classList.add("legacy-equipment3880")
            val row: dynamic = `field`.closest(".fields")
            dynamicValue(jsOr(row) { `field` }).insertAdjacentElement("afterend", panel)
            sync(form)
            return wrap.innerHTML
        })
        val oldV4: dynamic = global_V4.submitProfile
        global_V4.submitProfile = (fun(event: dynamic): dynamic {
            val receiver43: dynamic = js("this")
            val callArgs44: dynamic = js("arguments")
            val form: dynamic = onPresent(event) { value45 -> value45.target }
            if (truth(jsOr((onPresent(form) { value46 -> value46.id } !== "profileForm")) { (!truth(form.querySelector(".equipment-modes3880"))) })) {
                return oldV4.apply(receiver43, callArgs44)
            }
            val selected: dynamic = fromForm(form)
            val before: dynamic = onPresent(global_state) { value47 -> value47.profile }
            val answer: dynamic = oldV4.apply(receiver43, callArgs44)
            if (truth(jsAnd(onPresent(global_state) { value48 -> value48.profile }) { (global_state.profile !== before) })) {
                global_state.profile.equipmentModes3880 = selected
                if (truth(jsAnd((js("typeof save") === "function")) { (!truth(onPresent(root.Runtime20) { value51 -> value51.initializing })) })) {
                    global_save()
                }
            }
            return answer
        })
        val oldSubmit: dynamic = global_submitProfile
        global_submitProfile = (fun(event: dynamic): dynamic {
            val receiver52: dynamic = js("this")
            val callArgs53: dynamic = js("arguments")
            val form: dynamic = onPresent(event) { value54 -> value54.target }
            if (truth(jsOr((onPresent(form) { value55 -> value55.id } !== "profileForm")) { (!truth(form.querySelector(".equipment-modes3880"))) })) {
                return oldSubmit.apply(receiver52, callArgs53)
            }
            val selected: dynamic = fromForm(form)
            val load: dynamic = form.elements.namedItem("load")
            if (truth((!truth(selected.length)))) {
                event.preventDefault()
                global_toast("Selecciona al menos una modalidad de equipamiento.")
                return absent
            }
            if (truth(jsAnd(selected.includes("db")) { jsOr(jsOr((!truth(load))) { (!truth(global_Number.isFinite(global_Number(load["value"])))) }) { jsAtMost(global_Number(load["value"]), 0) } })) {
                event.preventDefault()
                global_toast("Indica los kilos disponibles de una mancuerna.")
                onPresent(load) { value56 -> value56.focus() }
                return absent
            }
            sync(form)
            return oldSubmit.apply(receiver52, callArgs53)
        })
        root.HunterEquipment3880 = global_Object.freeze(jsObject { obj59 ->
            obj59["version"] = "38.80"
            obj59["modes"] = modes
            obj59["fromForm"] = fromForm
            obj59["change"] = sync
        })
        run { val owner61: dynamic = root; onPresent(owner61.requestAnimationFrame) { method60 -> invokeWith(method60, owner61, arrayOf<dynamic>((fun(): dynamic {
            if (truth(jsAnd(jsAnd(doc.querySelector("#profileForm")) { (!truth(doc.querySelector("#profileForm .equipment-modes3880"))) }) { (js("typeof render") === "function") })) {
                global_render()
            }
            return absent
        }))) } }
        return absent
    }))(global_window, global_document)
}
