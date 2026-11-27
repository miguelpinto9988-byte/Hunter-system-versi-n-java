@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installvectorplayer

import hunter.browser.*

@JsName("interpolateMotion") private external var global_interpolateMotion: dynamic
@JsName("groundCache13") private external var global_groundCache13: dynamic
@JsName("modelGround") private external var global_modelGround: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("vectorGeneration13") private external var global_vectorGeneration13: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("startVector") private external var global_startVector: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("vectorTick") private external var global_vectorTick: dynamic
@JsName("vectorToggle") private external var global_vectorToggle: dynamic
@JsName("vectorSeek") private external var global_vectorSeek: dynamic
@JsName("vectorPanel") private external var global_vectorPanel: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("DepthMotion") private external var global_DepthMotion: dynamic
@JsName("MotionPlayback") private external var global_MotionPlayback: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("esc") private external var global_esc: dynamic

internal fun installVectorPlayer() {
    global_interpolateMotion = fun(model: dynamic, input3: dynamic): dynamic {
        var phase: dynamic = input3
        phase = jsMod(jsAdd(jsMod(phase, 48), 48), 48)
        val i: dynamic = global_Math.floor(phase)
        val a: dynamic = model.frames[i]
        val b: dynamic = model.frames[jsMod(jsAdd(i, 1), 48)]
        val t: dynamic = jsMod(phase, 1)
        val blend: dynamic = (fun(p: dynamic, q: dynamic): dynamic {
            return p.map((fun(v: dynamic, k: dynamic): dynamic {
                return jsAdd(v, jsMultiply(jsSubtract(q[k], v), t))
            }))
        })
        val detail: dynamic = jsObject { obj4 ->
            copyFields(obj4, a.detail)
        }
        if (true) {
            val iterator5: dynamic = iteratorOf(objectKeys(detail))
            while (true) {
                val next6: dynamic = iterator5.next()
                if (truth(next6.done)) break
                var k: dynamic = absent
                k = next6.value
                if (truth(jsAnd((jsTypeOf(detail[k]) === "number")) { (jsTypeOf(b.detail[k]) === "number") })) {
                    run {
                        val target7: dynamic = detail
                        val key8: dynamic = k
                        target7[key8] = jsAdd(target7[key8], jsMultiply(jsSubtract(b.detail[k], detail[k]), t))
                    }
                }
            }
        }
        return jsObject { obj9 ->
            copyFields(obj9, a)
            obj9["p"] = a.p.map((fun(p: dynamic, j: dynamic): dynamic {
                return blend(p, b.p[j])
            }))
            obj9["loads"] = a.loads.map((fun(p: dynamic, j: dynamic): dynamic {
                return (if (truth(b.loads[j])) blend(p, b.loads[j]) else p)
            }))
            obj9["detail"] = detail
        }
    }
    global_modelGround = fun(id: dynamic): dynamic {
        val base: dynamic = jsOr(global_E.byId(id).baseId) { id }
        if (truth((!truth(global_groundCache13.has(base))))) {
            val m: dynamic = global_MotionDB[base]
            global_groundCache13["set"](base, m.frames.flatMap((fun(f: dynamic): dynamic {
                return jsArray { array12 ->
                    array12.push(jsAdd(f.p[8][1], 10))
                    array12.push(jsAdd(f.p[10][1], 10))
                    appendAll(array12, f.equipment.map((fun(x: dynamic): dynamic {
                        return x[1][3]
                    })))
                }
            })))
        }
        return global_groundCache13["get"](base)
    }
    global_drawVector = fun(canvas: dynamic, id: dynamic, arg15: dynamic, arg16: dynamic): dynamic {
        var phase: dynamic = absent
        phase = definedOr(arg15) { 0 }
        var mirror: dynamic = absent
        mirror = definedOr(arg16) { false }
        return global_DepthMotion.draw(canvas, id, phase, mirror)
    }
    global_startVector = fun(id: dynamic): dynamic {
        run {
            val previous19: dynamic = numeric(global_vectorGeneration13)
            global_vectorGeneration13 = jsAdd(previous19, 1)
        }
        global_vectorSession = jsObject { obj20 ->
            obj20["id"] = id
            obj20["frame"] = 0
            obj20["playing"] = false
            obj20["speed"] = global_MotionPlayback.rate
            obj20["mirror"] = false
            obj20["last"] = 0
        }
        global_vectorPaint()
        return absent
    }
    global_vectorPaint = fun(): dynamic {
        val s: dynamic = global_vectorSession
        val c: dynamic = global_Dollar("#vectorCanvas")
        if (truth(jsOr((!truth(s))) { (!truth(c)) })) {
            return absent
        }
        global_drawVector(c, s.id, s.frame, s.mirror)
        global_Dollar("#vectorRange")["value"] = jsMod(global_Math.floor(s.frame), 48)
        global_Dollar("#vectorPhase").textContent = (if (truth(global_MotionDB[jsOr(global_E.byId(s.id).baseId) { s.id }].hold)) "Mantén sin forzar · respira" else (if (truth(jsLess(jsMod(s.frame, 48), 24))) "Movimiento controlado" else "Regreso controlado"))
        return absent
    }
    global_vectorTick = fun(time: dynamic, arg29: dynamic): dynamic {
        var token: dynamic = absent
        token = definedOr(arg29) { global_vectorGeneration13 }
        val s: dynamic = global_vectorSession
        if (truth(jsOr(jsOr(jsOr((token !== global_vectorGeneration13)) { (!truth(onPresent(s) { value30 -> value30.playing })) }) { (!truth(global_Dollar("#vectorCanvas"))) }) { (!truth(global_Dollar("#modal")["open"])) })) {
            return absent
        }
        if (truth(jsAnd((!truth(global_document.hidden))) { s.last })) {
            s.frame = jsMod(jsAdd(s.frame, jsMultiply(jsMultiply(global_Math.min(100, global_Math.max(0, jsSubtract(time, s.last))), 0.006), s.speed)), 48)
        }
        s.last = (if (truth(global_document.hidden)) 0 else time)
        global_vectorPaint()
        global_requestAnimationFrame((fun(t: dynamic): dynamic {
            return global_vectorTick(t, token)
        }))
        return absent
    }
    global_vectorToggle = fun(): dynamic {
        val s: dynamic = global_vectorSession
        if (truth((!truth(s)))) {
            return absent
        }
        run {
            val previous37: dynamic = numeric(global_vectorGeneration13)
            global_vectorGeneration13 = jsAdd(previous37, 1)
        }
        s.playing = (!truth(s.playing))
        s.last = 0
        global_Dollar("#vectorPlay").textContent = (if (truth(s.playing)) "Pausar" else "Reproducir")
        if (truth(s.playing)) {
            val token: dynamic = global_vectorGeneration13
            global_requestAnimationFrame((fun(t: dynamic): dynamic {
                return global_vectorTick(t, token)
            }))
        }
        return absent
    }
    global_vectorSeek = fun(`value`: dynamic): dynamic {
        if (truth((!truth(global_vectorSession)))) {
            return absent
        }
        run {
            val previous46: dynamic = numeric(global_vectorGeneration13)
            global_vectorGeneration13 = jsAdd(previous46, 1)
        }
        global_vectorSession.playing = false
        global_vectorSession.frame = global_Math.max(0, global_Math.min(47, jsOr(global_Number(`value`)) { 0 }))
        global_Dollar("#vectorPlay").textContent = "Reproducir"
        global_vectorPaint()
        return absent
    }
    global_vectorPanel = fun(e: dynamic): dynamic {
        return ("<section class=\"demo-card\"><span class=\"eyebrow\">VISOR 2.5D · SIN CONEXIÓN</span><canvas id=\"vectorCanvas\" width=\"720\" height=\"800\" aria-label=\"Movimiento con volumen ilustrado: " + textOf(global_esc(e.name)) + "\"></canvas><p id=\"vectorPhase\">Preparación</p><label>Posición del movimiento<input id=\"vectorRange\" type=\"range\" min=\"0\" max=\"47\" value=\"0\" oninput=\"vectorSeek(this.value)\"></label><div class=\"row wrap\"><button class=\"btn primary\" id=\"vectorPlay\" onclick=\"vectorToggle()\">Reproducir</button><button class=\"btn\" onclick=\"HunterActions.mirrorVector()\">Ver otro lado</button></div>" + textOf(global_MotionPlayback.controls()) + "<p class=\"footnote\">La velocidad cambia la demostración. Conserva el tiempo y las cantidades de tu sesión.</p><p>" + textOf(global_esc(jsOr(jsOr(e.variantDescription) { e.cue }) { "Sigue las instrucciones de preparación y ejecución." })) + "</p><details><summary>Cómo interpretar el modelo</summary><p>Volumen, iluminación y sombras sobre un modelo simplificado. Las articulaciones son referencias visuales; las variantes comparten trayectorias base. No mide tu postura ni sustituye las instrucciones de la ficha. En posiciones sostenidas se muestra la postura sin inventar repeticiones.</p></details></section>")
    }
    global_groundCache13 = jsConstruct(global_Map, js("[]"))
    global_vectorSession = dynamicValue(null)
    global_vectorGeneration13 = 0
}
