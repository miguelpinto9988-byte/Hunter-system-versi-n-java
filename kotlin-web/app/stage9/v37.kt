@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv37

import hunter.browser.*

@JsName("ExerciseLookup372") private external var global_ExerciseLookup372: dynamic
@JsName("Startup37") private external var global_Startup37: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Boot37") private external var global_Boot37: dynamic

internal fun installV37() {
    global_ExerciseLookup372 = jsObject { obj1 ->
        obj1["rows"] = dynamicValue(null)
        obj1["size"] = 0
        obj1["index"] = jsConstruct(global_Map, js("[]"))
        obj1["refresh"] = fun(): dynamic {
            val receiver2: dynamic = js("this")
            receiver2.rows = global_E.exercises
            receiver2.size = receiver2.rows.length
            receiver2.index = jsConstruct(global_Map, arrayOf<dynamic>(receiver2.rows.map((fun(e: dynamic): dynamic {
                return arrayOf<dynamic>(e.id, e)
            }))))
            return absent
        }
    }
    global_ExerciseLookup372.refresh()
    global_E.byId = (fun(id: dynamic): dynamic {
        if (truth(jsOr((global_ExerciseLookup372.rows !== global_E.exercises)) { (global_ExerciseLookup372.size !== global_E.exercises.length) })) {
            global_ExerciseLookup372.refresh()
        }
        return global_ExerciseLookup372.index["get"](id)
    })
    global_Startup37 = jsObject { obj14 ->
        obj14["render"] = global_render
        obj14["applyTheme"] = global_applyTheme
    }
    global_Runtime20.startup37 = jsonValue("{\"renders\":0,\"reveals\":0,\"readyAt\":null}")
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        val `value`: dynamic = global_Startup37.render()
        run {
            val target19: dynamic = global_Runtime20.startup37
            val previous21: dynamic = numeric(target19.renders)
            target19.renders = jsAdd(previous21, 1)
        }
        return `value`
    })
    global_openDiagnostics20 = (fun(): dynamic {
        global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section><h2>Estado del contenido local</h2><p>", global_E.exercises.length), " demostraciones 2D propias · "), global_Catalog.routines.length), " rutinas sin conexión.</p><p>Versión 37.3. "), global_Runtime20.errors.length), " errores de interfaz registrados en esta apertura.</p><p>La progresión semanal usa tus registros confirmados, esfuerzo, objetivos y restricciones declaradas.</p><p>Renderizado sincronizado con la pantalla, sin límite interno de FPS. La frecuencia efectiva depende del dispositivo.</p></section>"))
        return absent
    })
    global_window.HunterV37 = jsObject { obj26 ->
        obj26["version"] = 37
        obj26["startup"] = global_Runtime20.startup37
    }
    global_state.appVersion = 37
    if (truth((global_document.readyState === "loading"))) {
        global_document.addEventListener("DOMContentLoaded", (fun(): dynamic {
            return global_Boot37.complete()
        }), jsonValue("{\"once\":true}"))
    } else {
        global_Boot37.complete()
    }
}
