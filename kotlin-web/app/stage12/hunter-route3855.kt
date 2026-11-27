@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterroute3855

import hunter.browser.*

@JsName("String") private external var global_String: dynamic
@JsName("HunterKotlinScreens") private external var global_HunterKotlinScreens: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("shell3855") private external var global_shell3855: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterRoute3855() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var calendarView: dynamic = absent
        calendarView = fun(): dynamic {
            return global_HunterKotlinScreens.calendarScreen()
        }
        if (truth(root.HunterRoute3855)) {
            return absent
        }
        val escText: dynamic = (fun(`value`: dynamic): dynamic {
            return global_String(nullableOr(`value`) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        val entry: dynamic = jsObject { obj5 ->
            obj5["version"] = "38.56"
            obj5["calendarView"] = calendarView
            obj5["diagnostics"] = (fun(): dynamic {
                return jsObject { obj6 ->
                    obj6["tab"] = global_tab
                    obj6["route"] = jsOr(onPresent(doc.querySelector("#app main .scene")) { value7 -> value7.getAttribute("data-route3855") }) { null }
                    obj6["shellExists"] = (!truth((!truth(doc.querySelector("#app > main.app")))))
                    obj6["navItems"] = dynamicValue(jsArray { array9 ->
                        appendAll(array9, doc.querySelectorAll("#app > nav.nav button[data-route3855]"))
                    }).map((fun(x: dynamic): dynamic {
                        return x.dataset.route3855
                    }))
                    obj6["profileAvatar"] = (!truth((!truth(doc.querySelector(".topbar .avatar-button")))))
                    obj6["fullMounts"] = (if (truth((js("typeof shell3855") === "object"))) global_shell3855.fullMounts else 0)
                    obj6["sceneReplacements"] = (if (truth((js("typeof shell3855") === "object"))) global_shell3855.sceneReplacements else 0)
                    obj6["headerReplacements"] = (if (truth((js("typeof shell3855") === "object"))) global_shell3855.headerReplacements else 0)
                    obj6["navReplacements"] = (if (truth((js("typeof shell3855") === "object"))) global_shell3855.navReplacements else 0)
                }
            })
        }
        root.HunterRoute3855 = global_Object.freeze(entry)
        return absent
    }))(global_window, global_document)
}
