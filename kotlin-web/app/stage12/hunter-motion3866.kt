@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntermotion3866

import hunter.browser.*

@JsName("technique") private external var global_technique: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterMotion3866() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var stats: dynamic = absent
        var SVGNS: dynamic = absent
        var make: dynamic = absent
        var moveSet: dynamic = absent
        var motionChart: dynamic = absent
        var attach: dynamic = absent
        motionChart = fun(`data`: dynamic): dynamic {
            val svg: dynamic = doc.createElementNS(SVGNS, "svg")
            svg.setAttribute("viewBox", "0 0 300 54")
            svg.setAttribute("role", "img")
            svg.setAttribute("aria-label", "Variación relativa del punto móvil en la trayectoria vectorial del modelo")
            val p: dynamic = `data`.samples
            val orig: dynamic = p[0]
            val vals: dynamic = p.map((fun(q: dynamic): dynamic {
                return global_Math.hypot(jsSubtract(q[0], orig[0]), jsSubtract(q[1], orig[1]))
            }))
            val max: dynamic = run { val target36: dynamic = global_Math; invokeWith(target36.max, target36, jsArray { array35 ->
                array35.push(1)
                appendAll(array35, vals)
            }) }
            val d: dynamic = vals.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd((if (truth(i)) "L" else "M"), dynamicValue(jsAdd(8, jsMultiply(i, jsDivide(284, jsSubtract(vals.length, 1))))).toFixed(1)), ","), dynamicValue(jsSubtract(46, jsDivide(jsMultiply(36, v), max))).toFixed(1))
            })).join(" ")
            val base: dynamic = doc.createElementNS(SVGNS, "path")
            base.setAttribute("d", "M8 46H292")
            base.setAttribute("class", "hunter-motion-chart-base3866")
            val line: dynamic = doc.createElementNS(SVGNS, "path")
            line.setAttribute("d", d)
            line.setAttribute("class", "hunter-motion-chart-line3866")
            svg.append(base, line)
            return svg
        }
        attach = fun(id: dynamic): dynamic {
            var host: dynamic = absent
            var focus: dynamic = absent
            var reset: dynamic = absent
            var original: dynamic = absent
            var active: dynamic = absent
            var getMain: dynamic = absent
            var restore: dynamic = absent
            getMain = fun(): dynamic {
                return host.querySelector("svg:not(.detail-svg31)")
            }
            restore = fun(): dynamic {
                val svg: dynamic = getMain()
                if (truth(jsAnd(svg) { original })) {
                    svg.setAttribute("viewBox", original)
                }
                active = false
                focus.setAttribute("aria-pressed", "false")
                focus.textContent = "Ampliar la zona de movimiento"
                reset.disabled = true
                return absent
            }
            val panel: dynamic = doc.querySelector("#modal .motion-card18.hunter-premium3865")
            if (truth(jsOr((!truth(panel))) { (panel.dataset.hunter3866 === id) })) {
                return absent
            }
            panel.dataset.hunter3866 = id
            run {
                val target41: dynamic = stats
                val previous43: dynamic = numeric(target41.opened)
                target41.opened = jsAdd(previous43, 1)
            }
            val warning: dynamic = panel.querySelector(".hunter-premium-underreview3865")
            if (truth((!truth(warning)))) {
                return absent
            }
            run {
                val target44: dynamic = stats
                val previous46: dynamic = numeric(target44.flaggedOpened)
                target44.flaggedOpened = jsAdd(previous46, 1)
            }
            val canvas: dynamic = panel.querySelector("#vectorCanvas")
            host = panel.querySelector(".catalog-motion46")
            if (truth(jsOr((!truth(canvas))) { (!truth(host)) })) {
                return absent
            }
            warning.textContent = "Representación técnica en revisión: no se ha confirmado la postura ni el recorrido. Puedes inspeccionar el desplazamiento original sin modificar la animación."
            val wrapper: dynamic = make("section", "hunter-motion-focus3866")
            wrapper.setAttribute("aria-label", "Análisis del movimiento disponible")
            val info: dynamic = make("div", "hunter-motion-focus-info3866")
            val main: dynamic = make("strong", "", "Inspección del movimiento")
            val detail: dynamic = make("p", "hunter-motion-focus-detail3866", "Analizando los fotogramas y las muestras vectoriales disponibles…")
            detail.setAttribute("role", "status")
            detail.setAttribute("aria-live", "polite")
            info.append(main, detail)
            val controls: dynamic = make("div", "hunter-motion-focus-actions3866")
            focus = make("button", "btn ghost", "Ampliar la zona de movimiento")
            focus.type = "button"
            focus.disabled = true
            focus.setAttribute("aria-pressed", "false")
            reset = make("button", "btn ghost", "Restaurar vista completa")
            reset.type = "button"
            reset.disabled = true
            controls.append(focus, reset)
            wrapper.append(info, controls)
            panel.insertBefore(wrapper, canvas)
            original = dynamicValue(null)
            active = false
            var viewBox: dynamic = dynamicValue(null)
            focus.addEventListener("click", (fun(): dynamic {
                val svg: dynamic = getMain()
                if (truth(jsOr((!truth(svg))) { (!truth(viewBox)) })) {
                    return absent
                }
                if (truth((!truth(original)))) {
                    original = jsOr(svg.getAttribute("viewBox")) { "0 0 720 610" }
                }
                active = (!truth(active))
                svg.setAttribute("viewBox", (if (truth(active)) viewBox else original))
                focus.setAttribute("aria-pressed", global_String(active))
                focus.textContent = (if (truth(active)) "Volver al encuadre completo" else "Ampliar la zona de movimiento")
                reset.disabled = (!truth(active))
                if (truth(active)) {
                    run {
                        val target69: dynamic = stats
                        val previous71: dynamic = numeric(target69.focused)
                        target69.focused = jsAdd(previous71, 1)
                    }
                }
                return absent
            }))
            reset.addEventListener("click", restore)
            root.HunterDemos46.load(id)["then"]((fun(asset: dynamic): dynamic {
                if (truth(jsOr(jsOr((!truth(panel.isConnected))) { (panel.dataset.hunter3866 !== id) }) { (!truth(onPresent(doc.getElementById("modal")) { value89 -> value89.contains(panel) })) })) {
                    return absent
                }
                val model: dynamic = onPresent(asset) { value90 -> value90.model }
                val move: dynamic = moveSet(model)
                val node: dynamic = getMain()
                if (truth(jsAnd(node) { (!truth(original)) })) {
                    original = jsOr(node.getAttribute("viewBox")) { "0 0 720 610" }
                }
                if (truth((!truth(move)))) {
                    detail.textContent = "No se pudo calcular el área de movimiento. Consulta el SVG original."
                    return absent
                }
                viewBox = (if (truth(move.zoomAvailable)) move.viewBox else null)
                val category: dynamic = (if (truth(move.minimal)) "Desplazamiento mínimo o postura sostenida" else "Movimiento registrado en los fotogramas")
                detail.textContent = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(category, " · Punto destacado: "), move.name), " · "), move.samples.length), " muestras · Variación relativa máxima: "), move.span), " unidades gráficas. No equivale a una evaluación técnica.")
                wrapper.append(motionChart(move))
                if (truth(move.minimal)) {
                    run {
                        val target95: dynamic = stats
                        val previous97: dynamic = numeric(target95.minimalMotion)
                        target95.minimalMotion = jsAdd(previous97, 1)
                    }
                    val n: dynamic = make("p", "hunter-motion-static3866", "La figura puede parecer inmóvil aunque el archivo tenga animaciones. No se añaden repeticiones ni recorridos artificiales.")
                    wrapper.append(n)
                }
                if (truth(jsAnd(node) { viewBox })) {
                    focus.disabled = false
                    reset.disabled = true
                } else {
                    if (truth((!truth(viewBox)))) {
                        run {
                            val target102: dynamic = detail
                            target102.textContent = jsAdd(target102.textContent, " Se conserva la vista completa para no recortar los apoyos y las extremidades.")
                        }
                    } else {
                        run {
                            val target104: dynamic = detail
                            target104.textContent = jsAdd(target104.textContent, " El SVG original todavía no está disponible.")
                        }
                    }
                }
                return absent
            }))["catch"]((fun(): dynamic {
                run {
                    val target106: dynamic = stats
                    val previous108: dynamic = numeric(target106.loadFailures)
                    target106.loadFailures = jsAdd(previous108, 1)
                }
                detail.textContent = "No se pudo cargar el recurso local. Comprueba que la APK tenga completos los archivos de demostración."
                return absent
            }))
            return absent
        }
        if (truth(jsOr(jsOr(jsOr(root.HunterMotion3866) { (!truth(root.HunterMotion3865)) }) { (!truth(root.HunterDemos46)) }) { (js("typeof technique") !== "function") })) {
            return absent
        }
        val oldTechnique: dynamic = global_technique
        stats = jsonValue("{\"opened\":0,\"focused\":0,\"loadFailures\":0,\"flaggedOpened\":0,\"minimalMotion\":0}")
        SVGNS = "http://www.w3.org/2000/svg"
        val clamp: dynamic = (fun(n: dynamic, a: dynamic, b: dynamic): dynamic {
            return global_Math.min(b, global_Math.max(a, n))
        })
        val validPoint: dynamic = (fun(p: dynamic): dynamic {
            return jsAnd(jsAnd(jsAnd(global_Array.isArray(p)) { jsAtLeast(p.length, 2) }) { global_Number.isFinite(p[0]) }) { global_Number.isFinite(p[1]) }
        })
        make = (fun(tag: dynamic, cls: dynamic, text: dynamic): dynamic {
            val e: dynamic = doc.createElement(tag)
            if (truth(cls)) {
                e.className = cls
            }
            if (truth((text !== absent))) {
                e.textContent = text
            }
            return e
        })
        val extended: dynamic = (fun(id: dynamic): dynamic {
            return jsAnd((jsTypeOf(id) === "string")) { dynamicValue(jsRegExp("^(?:new46-|hs379-)", "")).test(id) }
        })
        moveSet = (fun(model: dynamic): dynamic {
            val frames: dynamic = (if (truth(global_Array.isArray(onPresent(model) { value7 -> value7.vectorFrames46 }))) model.vectorFrames46 else js("[]"))
            if (truth(jsOr(jsLess(frames.length, 48)) { jsMore(frames.length, 512) })) {
                return null
            }
            val joints: dynamic = frames.map((fun(x: dynamic): dynamic {
                return jsOr(onPresent(x) { value8 -> value8.j }) { js("({})") }
            }))
            val anchorName: dynamic = (if (truth(joints.every((fun(j: dynamic): dynamic {
                return validPoint(j.pelvis)
            })))) "pelvis" else (if (truth(joints.every((fun(j: dynamic): dynamic {
                return validPoint(j.midspine)
            })))) "midspine" else null))
            if (truth((!truth(anchorName)))) {
                return null
            }
            val names: dynamic = jsonValue("[\"wristL\",\"wristR\",\"handL\",\"handR\",\"thumbL\",\"thumbR\",\"elbowL\",\"elbowR\",\"shoulderL\",\"shoulderR\",\"hipL\",\"hipR\",\"kneeL\",\"kneeR\",\"ankleL\",\"ankleR\",\"toeL\",\"toeR\",\"heelL\",\"heelR\",\"head\",\"neck\"]")
            val ranked: dynamic = js("[]")
            if (true) {
                val iterator9: dynamic = iteratorOf(names)
                while (true) {
                    val next10: dynamic = iterator9.next()
                    if (truth(next10.done)) break
                    var name: dynamic = absent
                    name = next10.value
                    if (truth((!truth(joints.every((fun(j: dynamic): dynamic {
                        return validPoint(j[name])
                    })))))) {
                        continue
                    }
                    val relative: dynamic = joints.map((fun(j: dynamic): dynamic {
                        return arrayOf<dynamic>(jsSubtract(j[name][0], j[anchorName][0]), jsSubtract(j[name][1], j[anchorName][1]))
                    }))
                    val baseline: dynamic = relative[0]
                    val range: dynamic = run { val target12: dynamic = global_Math; invokeWith(target12.max, target12, jsArray { array11 ->
                        appendAll(array11, relative.map((fun(p: dynamic): dynamic {
                            return global_Math.hypot(jsSubtract(p[0], baseline[0]), jsSubtract(p[1], baseline[1]))
                        })))
                    }) }
                    if (truth((!truth(global_Number.isFinite(range))))) {
                        continue
                    }
                    ranked.push(jsObject { obj13 ->
                        obj13["name"] = name
                        obj13["range"] = range
                        obj13["relative"] = relative
                    })
                }
            }
            ranked.sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract(b.range, a.range)
            }))
            if (truth((!truth(ranked.length)))) {
                return null
            }
            val most: dynamic = ranked[0]
            val all: dynamic = js("[]")
            if (true) {
                val iterator14: dynamic = iteratorOf(joints)
                while (true) {
                    val next15: dynamic = iterator14.next()
                    if (truth(next15.done)) break
                    var j: dynamic = absent
                    j = next15.value
                    if (true) {
                        val iterator16: dynamic = iteratorOf(global_Object.values(j))
                        while (true) {
                            val next17: dynamic = iterator16.next()
                            if (truth(next17.done)) break
                            var p: dynamic = absent
                            p = next17.value
                            if (truth(validPoint(p))) {
                                all.push(p)
                            }
                        }
                    }
                }
            }
            if (true) {
                val iterator18: dynamic = iteratorOf((if (truth(global_Array.isArray(model.frames))) model.frames else js("[]")))
                while (true) {
                    val next19: dynamic = iterator18.next()
                    if (truth(next19.done)) break
                    var f: dynamic = absent
                    f = next19.value
                    if (truth(global_Array.isArray(f.p))) {
                        if (true) {
                            val iterator20: dynamic = iteratorOf(f.p)
                            while (true) {
                                val next21: dynamic = iterator20.next()
                                if (truth(next21.done)) break
                                var p: dynamic = absent
                                p = next21.value
                                if (truth(validPoint(p))) {
                                    all.push(p)
                                }
                            }
                        }
                    }
                    if (truth(global_Array.isArray(f.loads))) {
                        if (true) {
                            val iterator22: dynamic = iteratorOf(f.loads)
                            while (true) {
                                val next23: dynamic = iterator22.next()
                                if (truth(next23.done)) break
                                var p: dynamic = absent
                                p = next23.value
                                if (truth(validPoint(p))) {
                                    all.push(p)
                                }
                            }
                        }
                    }
                }
            }
            val loX: dynamic = run { val target25: dynamic = global_Math; invokeWith(target25.min, target25, jsArray { array24 ->
                appendAll(array24, all.map((fun(p: dynamic): dynamic {
                    return p[0]
                })))
            }) }
            val hiX: dynamic = run { val target27: dynamic = global_Math; invokeWith(target27.max, target27, jsArray { array26 ->
                appendAll(array26, all.map((fun(p: dynamic): dynamic {
                    return p[0]
                })))
            }) }
            val loY: dynamic = run { val target29: dynamic = global_Math; invokeWith(target29.min, target29, jsArray { array28 ->
                appendAll(array28, all.map((fun(p: dynamic): dynamic {
                    return p[1]
                })))
            }) }
            val hiY: dynamic = run { val target31: dynamic = global_Math; invokeWith(target31.max, target31, jsArray { array30 ->
                appendAll(array30, all.map((fun(p: dynamic): dynamic {
                    return p[1]
                })))
            }) }
            var w: dynamic = global_Math.max(510, jsAdd(jsSubtract(hiX, loX), 90), jsMultiply(jsAdd(jsSubtract(hiY, loY), 90), jsDivide(720, 610)))
            w = global_Math.min(720, w)
            val h: dynamic = jsMultiply(w, jsDivide(610, 720))
            val cx: dynamic = clamp(jsDivide(jsAdd(loX, hiX), 2), jsDivide(w, 2), jsSubtract(720, jsDivide(w, 2)))
            val cy: dynamic = clamp(jsDivide(jsAdd(loY, hiY), 2), jsDivide(h, 2), jsSubtract(610, jsDivide(h, 2)))
            val viewBox: dynamic = dynamicValue(arrayOf<dynamic>(jsSubtract(cx, jsDivide(w, 2)), jsSubtract(cy, jsDivide(h, 2)), w, h)).map((fun(v: dynamic): dynamic {
                return global_Number(v.toFixed(2))
            })).join(" ")
            val zoomAvailable: dynamic = jsLess(w, 704)
            return jsObject { obj32 ->
                obj32["name"] = most.name
                obj32["span"] = global_Number(most.range.toFixed(1))
                obj32["viewBox"] = viewBox
                obj32["zoomAvailable"] = zoomAvailable
                obj32["samples"] = most.relative
                obj32["minimal"] = jsLess(most.range, 12)
                obj32["consistent"] = ranked.length
            }
        })
        global_technique = (fun(id: dynamic): dynamic {
            val receiver111: dynamic = js("this")
            val callArgs112: dynamic = js("arguments")
            val result: dynamic = oldTechnique.apply(receiver111, callArgs112)
            if (truth(extended(id))) {
                attach(id)
            }
            return result
        })
        root.HunterMotion3866 = global_Object.freeze(jsObject { obj115 ->
            obj115["version"] = "38.67"
            obj115["motionPlan"] = moveSet
            obj115["stats"] = (fun(): dynamic {
                return jsObject { obj116 ->
                    copyFields(obj116, stats)
                }
            })
            obj115["relativeChart"] = true
            obj115["techniqueValidated"] = false
        })
        return absent
    }))(global_window, global_document)
}
