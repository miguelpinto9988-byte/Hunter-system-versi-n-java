@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installvisualsv33

import hunter.browser.*

@JsName("Visual33") private external var global_Visual33: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Infinity") private external var global_Infinity: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("ThemeHues") private external var global_ThemeHues: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Image") private external var global_Image: dynamic
@JsName("UI26") private external var global_UI26: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installVisualsV33() {
    global_Visual33 = jsObject { obj1 ->
        obj1["ranks"] = global_Object.freeze(arrayOf<dynamic>(jsonValue("{\"key\":\"E\",\"name\":\"Despertar\",\"min\":1,\"max\":5,\"xp\":0,\"detail\":\"Ropa de entrenamiento · patio y sala de práctica\"}"), jsonValue("{\"key\":\"D\",\"name\":\"Primer portal\",\"min\":6,\"max\":10,\"xp\":1500,\"detail\":\"Chaqueta y guantes · corredor y ciudad bajo la lluvia\"}"), jsonValue("{\"key\":\"C\",\"name\":\"Cazador\",\"min\":11,\"max\":15,\"xp\":3000,\"detail\":\"Abrigo de combate · ruinas y plaza lunar\"}"), jsonValue("{\"key\":\"B\",\"name\":\"Ascenso\",\"min\":16,\"max\":20,\"xp\":4500,\"detail\":\"Armadura ligera · escalinata y azotea\"}"), jsonValue("{\"key\":\"A\",\"name\":\"Élite\",\"min\":21,\"max\":25,\"xp\":6000,\"detail\":\"Armadura ornamentada · gran salón y biblioteca\"}"), jsObject { obj2 ->
            obj2["key"] = "S"
            obj2["name"] = "Monarca"
            obj2["min"] = 26
            obj2["max"] = global_Infinity
            obj2["xp"] = 7500
            obj2["detail"] = "Manto, aura y equipo ceremonial · portal del monarca"
        }))
        obj1["themes"] = jsonValue("{\"blue\":\"Azul de portal\",\"pink\":\"Rosa lunar\",\"violet\":\"Amatista\",\"emerald\":\"Jade\",\"amber\":\"Ámbar\",\"coral\":\"Coral de ocaso\",\"red\":\"Carmesí\",\"teal\":\"Marea\",\"ice\":\"Hielo\",\"gold\":\"Oro antiguo\"}")
        obj1["palettes"] = jsonValue("{\"bruma\":\"Bruma\",\"umbral\":\"Umbral\",\"lunar\":\"Lunar\"}")
        obj1["collections"] = jsonValue("{\"hunter\":\"El despertar del cazador\",\"reader\":\"Crónica del lector · Kim Dokja\",\"regressor\":\"Camino del regresor · Junghyeok Yu\",\"alternate\":\"Alternar las tres colecciones\"}")
        obj1["companions"] = jsonValue("{\"knight\":\"igris\",\"warrior\":\"iron\",\"bear\":\"tank\",\"insect\":\"beru\",\"scientist\":\"dokja\"}")
        obj1["preferences"] = fun(arg5: dynamic): dynamic {
            val receiver3: dynamic = js("this")
            var raw: dynamic = absent
            raw = definedOr(arg5) { global_state.appearance33 }
            return jsObject { obj6 ->
                obj6["palette"] = (if (truth(global_Object.hasOwn(receiver3.palettes, onPresent(raw) { value7 -> value7.palette }))) raw.palette else "bruma")
                obj6["collection"] = (if (truth(global_Object.hasOwn(receiver3.collections, onPresent(raw) { value8 -> value8.collection }))) raw.collection else "hunter")
                obj6["scene"] = (if (truth(dynamicValue(jsonValue("[\"auto\",\"sanctuary\",\"journey\"]")).includes(onPresent(raw) { value9 -> value9.scene }))) raw.scene else "auto")
            }
        }
        obj1["rank"] = fun(arg12: dynamic): dynamic {
            val receiver10: dynamic = js("this")
            var level: dynamic = absent
            level = definedOr(arg12) { global_E.stats(jsOr(global_state.logs) { js("({})") }).level }
            val n: dynamic = global_Math.max(1, global_Math.floor(jsOr(global_Number(level)) { 1 }))
            return receiver10.ranks[global_Math.min(5, global_Math.floor(jsDivide(jsSubtract(n, 1), 5)))]
        }
        obj1["selection"] = fun(arg15: dynamic, arg16: dynamic, arg17: dynamic): dynamic {
            val receiver13: dynamic = js("this")
            var p: dynamic = absent
            p = definedOr(arg15) { jsOr(global_state.profile) { js("({})") } }
            var level: dynamic = absent
            level = definedOr(arg16) { global_E.stats(jsOr(global_state.logs) { js("({})") }).level }
            var prefs: dynamic = absent
            prefs = definedOr(arg17) { receiver13.preferences() }
            val stage: dynamic = receiver13.rank(level)
            val day: dynamic = global_Math.max(0, global_E.diff(global_E.dateKey(), "2026-01-01"))
            val collection: dynamic = (if (truth((prefs.collection === "alternate"))) dynamicValue(jsonValue("[\"hunter\",\"reader\",\"regressor\"]"))[jsMod(day, 3)] else prefs.collection)
            val scene: dynamic = (if (truth((prefs.scene === "auto"))) jsMod(day, 2) else (if (truth((prefs.scene === "journey"))) 1 else 0))
            val gender: dynamic = (if (truth((p.gender === "female"))) "female" else "male")
            val figure: dynamic = (if (truth((collection === "hunter"))) jsAdd("hunter-", gender) else collection)
            val background: dynamic = jsAdd(jsAdd(jsAdd(jsAdd("ui33/scene-", stage.key.toLowerCase()), "-"), scene), ".webp")
            return jsObject { obj18 ->
                obj18["theme"] = jsOr(global_document.documentElement.dataset.theme) { "blue" }
                obj18["gender"] = gender
                obj18["row"] = 0
                obj18["column"] = 0
                obj18["level"] = level
                obj18["stage"] = stage
                obj18["collection"] = collection
                obj18["scene"] = scene
                obj18["character"] = figure
                obj18["figure"] = jsAdd(jsAdd(jsAdd(jsAdd("ui33/", figure), "-"), stage.key.toLowerCase()), ".webp")
                obj18["asset"] = jsObject { obj19 ->
                    obj19["path"] = background
                    obj19["width"] = 512
                    obj19["height"] = 512
                    obj19["columns"] = 1
                    obj19["rows"] = 1
                }
            }
        }
        obj1["colors"] = fun(arg22: dynamic, arg23: dynamic): dynamic {
            var theme: dynamic = absent
            theme = definedOr(arg22) { "blue" }
            var palette: dynamic = absent
            palette = definedOr(arg23) { "bruma" }
            val h: dynamic = nullableOr(global_ThemeHues[theme]) { global_ThemeHues.blue }
            val shift: dynamic = (if (truth((palette === "umbral"))) 56 else (if (truth((palette === "lunar"))) (-numeric(35)) else 38))
            val secondary: dynamic = jsMod(jsAdd(jsAdd(h, shift), 360), 360)
            val base: dynamic = jsMod(jsAdd(jsAdd(h, (if (truth((palette === "umbral"))) 24 else (if (truth((palette === "lunar"))) (-numeric(22)) else 0))), 360), 360)
            val saturation: dynamic = (if (truth((palette === "umbral"))) 27 else (if (truth((palette === "lunar"))) 22 else 24))
            val hsl: dynamic = (fun(h: dynamic, s: dynamic, l: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("hsl(", h), " "), s), "% "), l), "%)")
            })
            return jsObject { obj24 ->
                obj24["--bg"] = hsl(base, saturation, 8)
                obj24["--panel"] = hsl(base, 25, 15)
                obj24["--surface"] = hsl(base, 25, 21)
                obj24["--input"] = hsl(base, 26, 10)
                obj24["--line"] = hsl(h, 19, 38)
                obj24["--text"] = hsl(base, 22, 86)
                obj24["--muted"] = hsl(base, 19, 73)
                obj24["--accent"] = hsl(h, 43, 77)
                obj24["--cyan"] = hsl(h, 43, 77)
                obj24["--violet"] = hsl(secondary, 38, 74)
                obj24["--ornament26"] = hsl(secondary, 27, 60)
                obj24["--metal26"] = hsl(secondary, 22, 31)
                obj24["--glow"] = jsAdd(jsAdd("hsla(", h), ",35%,60%,.12)")
                obj24["--tone33"] = hsl(secondary, 27, 18)
                obj24["--selected33"] = hsl(h, 32, 29)
                obj24["--secondary33"] = hsl(secondary, 37, 73)
                obj24["--theme-hue26"] = global_String(h)
            }
        }
        obj1["companionPath"] = fun(arg27: dynamic): dynamic {
            val receiver25: dynamic = js("this")
            var type: dynamic = absent
            type = definedOr(arg27) { global_selectedCompanion() }
            return jsAdd(jsAdd("ui33/companion-", jsOr(receiver25.companions[type]) { "igris" }), ".webp")
        }
        obj1["figureHTML"] = fun(s: dynamic, arg30: dynamic): dynamic {
            var cls: dynamic = absent
            cls = definedOr(arg30) { "hero-figure33" }
            return jsAdd(jsAdd(jsAdd(jsAdd("<img class=\"", cls), "\" src=\""), s.figure), "\" alt=\"\" aria-hidden=\"true\" decoding=\"async\">")
        }
        obj1["cache"] = jsConstruct(global_Set, js("[]"))
        obj1["preload"] = fun(arg33: dynamic): dynamic {
            val receiver31: dynamic = js("this")
            var s: dynamic = absent
            s = definedOr(arg33) { receiver31.selection() }
            if (true) {
                val iterator34: dynamic = iteratorOf(arrayOf<dynamic>(s.figure, s.asset.path, receiver31.companionPath()))
                while (true) {
                    val next35: dynamic = iterator34.next()
                    if (truth(next35.done)) break
                    var path: dynamic = absent
                    path = next35.value
                    if (truth(receiver31.cache.has(path))) {
                        continue
                    }
                    receiver31.cache.add(path)
                    val img: dynamic = jsConstruct(global_Image, js("[]"))
                    img.src = path
                    run { val owner39: dynamic = img; onPresent(owner39.decode) { method38 -> invokeWith(method38, owner39, js("[]"))["catch"]((fun(): dynamic {

                        return absent
                    })) } }
                }
            }
            return absent
        }
        obj1["installFrame"] = fun(): dynamic {
            global_UI26.corner = "<svg viewBox=\"0 0 58 58\" fill=\"none\" aria-hidden=\"true\"><path d=\"M2 56V18L18 2h38l-9 7H22L9 22v25Z\" fill=\"var(--metal26)\" stroke=\"currentColor\" stroke-width=\"1.2\"/><path d=\"M6 47V21L21 6h26M13 38V24l11-11h14M2 34V18L18 2h16\" stroke=\"var(--accent)\" stroke-width=\".8\"/><path d=\"m15 15 13 2-11 11Z\" fill=\"currentColor\" fill-opacity=\".7\"/><path d=\"M2 56 9 47M47 9l9-7M4 7l3-3 5 1-7 7Z\" stroke=\"currentColor\"/><path d=\"m10 28 5 2-5 9Z\" fill=\"var(--accent)\" fill-opacity=\".4\"/></svg>"
            global_UI26.frame = (fun(arg49: dynamic): dynamic {
                val receiver47: dynamic = js("this")
                var windowFrame: dynamic = absent
                windowFrame = definedOr(arg49) { false }
                return jsAdd(jsAdd(jsAdd(jsAdd("<span class=\"", (if (truth(windowFrame)) "window-frame26" else "ornament26")), " monarch-frame33\" aria-hidden=\"true\">"), dynamicValue(jsonValue("[\"tl\",\"tr\",\"bl\",\"br\"]")).map((fun(c: dynamic): dynamic {
                    return jsAdd(jsAdd(jsAdd(jsAdd("<span class=\"corner26 ", c), "\">"), receiver47.corner), "</span>")
                })).join("")), "<span class=\"monarch-crest33\"><svg viewBox=\"0 0 72 22\" fill=\"none\"><path d=\"M0 11h20l5-5 6 6 5-10 5 10 6-6 5 5h20M20 16h32M31 19h10\" stroke=\"currentColor\"/><path d=\"m36 8 4 6-4 5-4-5Z\" fill=\"var(--accent)\" fill-opacity=\".5\"/></svg></span><span class=\"monarch-jewel33\">◇</span></span>")
            })
            return absent
        }
    }
    global_window.HunterVisual33 = global_Visual33
}
