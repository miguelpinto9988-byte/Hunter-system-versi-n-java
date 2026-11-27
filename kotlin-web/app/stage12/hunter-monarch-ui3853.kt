@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntermonarchui3853

import hunter.browser.*

@JsName("Error") private external var global_Error: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("panelTabs") private external var global_panelTabs: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterMonarchUi3853() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        if (truth(root.HunterMonarchUI3853)) {
            return absent
        }
        if (truth(jsOr(jsOr(jsOr((!truth(root.HunterMonarch3852))) { (js("typeof home") !== "function") }) { (js("typeof render") !== "function") }) { (js("typeof panelTabs") !== "function") })) {
            throw global_Error("HunterMonarchUI3853: faltan el motor o el Panel principal")
        }
        val originalHome: dynamic = global_home
        val originalRender: dynamic = global_render
        val originalTabs: dynamic = global_panelTabs
        val esc: dynamic = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        val currentStatus: dynamic = (fun(): dynamic {
            root.HunterMonarch3852.review()
            val store: dynamic = onPresent(global_state) { value3 -> value3.monarch3852 }
            val active: dynamic = onPresent(store) { value4 -> value4.active }
            val rows: dynamic = (if (truth(active)) root.HunterMonarch3852.progress(active, jsOr(global_state.logs) { js("({})") }) else js("[]"))
            val finished: dynamic = rows.filter((fun(x: dynamic): dynamic {
                return jsAtLeast(x.done, x.target)
            })).length
            return jsObject { obj5 ->
                obj5["active"] = active
                obj5["finished"] = finished
                obj5["total"] = 5
                obj5["xp"] = global_Math.max(0, jsOr(global_Number(onPresent(store) { value6 -> value6.xp })) { 0 })
            }
        })
        val homeCard: dynamic = (fun(): dynamic {
            val s: dynamic = currentStatus()
            val isActive: dynamic = (!truth((!truth(s.active))))
            val pct: dynamic = (if (truth(isActive)) global_Math.max(0, global_Math.min(100, global_Math.round(jsMultiply(jsDivide(s.finished, 5), 100)))) else 0)
            val days: dynamic = (if (truth(isActive)) global_Math.max(0, global_Math.ceil(jsDivide(jsSubtract(s.active.expiresAt, global_Date.now()), 86400000))) else 5)
            val difficulty: dynamic = jsOr(jsonValue("{\"basic\":\"BÁSICO\",\"intermediate\":\"INTERMEDIO\",\"advanced\":\"AVANZADO\"}")[onPresent(global_state.profile) { value7 -> value7.difficulty }]) { "SEGÚN PERFIL" }
            return ("<section class=\"monarch-entry3853\" aria-labelledby=\"monarch-entry-title3853\">\n    <div class=\"monarch-entry3853__symbol\" aria-hidden=\"true\">♛</div>\n    <div class=\"monarch-entry3853__info\">\n      <div class=\"monarch-entry3853__eyebrow\">DESAFÍO ESPECIAL <span>· CICLO DE 5 DÍAS</span></div>\n      <h2 id=\"monarch-entry-title3853\">MISIONES <strong>MONARCA</strong></h2>\n      <p>" + textOf((if (truth(isActive)) "Tu desafío está en marcha. Los cinco objetivos se acumulan durante el ciclo." else "Cinco pruebas de fuerza y resistencia adaptadas a tu nivel. Cada avance cuenta durante cinco días.")) + "</p>\n      <div class=\"monarch-entry3853__tags\"><span>NIVEL " + textOf(esc(difficulty)) + "</span><span>" + textOf((if (truth(isActive)) jsAdd(days, " DÍAS RESTANTES") else "5 OBJETIVOS")) + "</span><span>" + textOf(s.xp) + " XP MONARCA</span></div>\n    </div>\n    <div class=\"monarch-entry3853__action\">\n      " + textOf((if (truth(isActive)) ("<div class=\"monarch-entry3853__meter\" role=\"progressbar\" aria-label=\"Objetivos Monarca completados\" aria-valuemin=\"0\" aria-valuemax=\"5\" aria-valuenow=\"" + textOf(s.finished) + "\"><i style=\"width:" + textOf(pct) + "%\"></i></div><small>" + textOf(s.finished) + " / 5 completados</small>") else "")) + "\n      <button type=\"button\" class=\"monarch-entry3853__button\" onclick=\"go('monarch')\">" + textOf((if (truth(isActive)) "CONTINUAR MISIÓN" else "ABRIR MISIONES")) + " <span aria-hidden=\"true\">→</span></button>\n    </div>\n   </section>")
        })
        global_home = (fun(): dynamic {
            val receiver8: dynamic = js("this")
            val callArgs9: dynamic = js("arguments")
            val markup: dynamic = originalHome.apply(receiver8, callArgs9)
            if (truth(jsOr(jsOr((!truth(global_state.profile))) { (jsTypeOf(markup) !== "string") }) { markup.includes("monarch-entry3853") })) {
                return markup
            }
            return jsAdd(homeCard(), markup)
        })
        global_panelTabs = (fun(): dynamic {
            val receiver10: dynamic = js("this")
            val callArgs11: dynamic = js("arguments")
            val markup: dynamic = originalTabs.apply(receiver10, callArgs11)
            if (truth(jsOr((!truth(markup.includes("segmented")))) { markup.includes("data-monarch-tab3853") })) {
                return markup
            }
            return markup.replace(jsRegExp("<\\/div>\\s*\$", ""), ("<button data-monarch-tab3853=\"1\" class=\"" + textOf((if (truth((global_tab === "monarch"))) "selected" else "")) + "\" onclick=\"go('monarch')\" aria-label=\"Ir a Misiones Monarca\">♛ Monarca</button></div>"))
        })
        global_render = (fun(): dynamic {
            val receiver12: dynamic = js("this")
            val callArgs13: dynamic = js("arguments")
            var result: dynamic = absent
            if (truth((global_tab !== "monarch"))) {
                return originalRender.apply(receiver12, callArgs13)
            }
            if (truth((!truth(global_state.profile)))) {
                global_tab = "profile"
                return originalRender.apply(receiver12, callArgs13)
            }
            global_tab = "home"
            try {
                result = originalRender.apply(receiver12, callArgs13)
            } finally {
                global_tab = "monarch"
            }
            val scene: dynamic = doc.querySelector("#app .scene")
            if (truth((!truth(scene)))) {
                throw global_Error("HunterMonarchUI3853: no se encontró el contenedor de la pantalla")
            }
            scene.dataset.route3855 = "monarch"
            scene.innerHTML = ("<div class=\"monarch-page3853\"><button type=\"button\" class=\"monarch-page3853__back\" onclick=\"go('home')\">← VOLVER AL PANEL</button>" + textOf(root.HunterMonarch3852.view()) + "</div>")
            val tabs: dynamic = doc.querySelector("#app .segmented")
            if (truth(tabs)) {
                tabs.querySelectorAll("button").forEach((fun(btn: dynamic): dynamic {
                    val selected: dynamic = btn.hasAttribute("data-monarch-tab3853")
                    btn.classList.toggle("selected", selected)
                    if (truth(selected)) {
                        btn.setAttribute("aria-current", "page")
                    } else {
                        btn.removeAttribute("aria-current")
                    }
                    return absent
                }))
            }
            val navHome: dynamic = doc.querySelector("#app nav.nav button[onclick=\"go('home')\"]")
            if (truth(navHome)) {
                navHome.classList.add("active")
            }
            return result
        })
        root.HunterMonarchUI3853 = global_Object.freeze(jsObject { obj20 ->
            obj20["version"] = "38.54"
            obj20["homeCard"] = homeCard
            obj20["diagnostics"] = (fun(): dynamic {
                return jsObject { obj21 ->
                    obj21["tab"] = global_tab
                    obj21["cardPresent"] = (!truth((!truth(doc.querySelector(".monarch-entry3853")))))
                    obj21["viewPresent"] = (!truth((!truth(doc.querySelector(".monarch-page3853")))))
                    obj21["tabPresent"] = (!truth((!truth(doc.querySelector("[data-monarch-tab3853]")))))
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
