@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntermotion3865

import hunter.browser.*

@JsName("technique") private external var global_technique: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("MutationObserver") private external var global_MutationObserver: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterMotion3865() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var shown: dynamic = absent
        var review: dynamic = absent
        var loadErrors: dynamic = absent
        var special: dynamic = absent
        var make: dynamic = absent
        var improvePanel: dynamic = absent
        improvePanel = fun(id: dynamic): dynamic {
            var image: dynamic = absent
            var camera: dynamic = absent
            var zoomed: dynamic = absent
            var clamp: dynamic = absent
            var drawCamera: dynamic = absent
            var cameraObserver: dynamic = absent
            clamp = fun(v: dynamic, a: dynamic, b: dynamic): dynamic {
                return global_Math.min(b, global_Math.max(a, v))
            }
            drawCamera = fun(): dynamic {
                val svg: dynamic = image.querySelector("svg")
                if (truth((!truth(svg)))) {
                    return absent
                }
                if (truth((!truth(svg.dataset.originalView3865)))) {
                    svg.dataset.originalView3865 = jsOr(svg.getAttribute("viewBox")) { "0 0 720 1000" }
                }
                if (truth(jsAnd(zoomed) { camera })) {
                    svg.setAttribute("viewBox", camera)
                }
                return absent
            }
            val panel: dynamic = doc.querySelector("#modal .motion-card18")
            if (truth(jsOr((!truth(panel))) { (panel.dataset.exercisePremium3865 === id) })) {
                return absent
            }
            panel.dataset.exercisePremium3865 = id
            val compare: dynamic = panel.querySelector(".hunter-compare3863")
            val technical: dynamic = panel.querySelector(".catalog-motion46")
            val QA: dynamic = panel.querySelector(".hunter-quality-badge3864")
            val canvas: dynamic = panel.querySelector("#vectorCanvas")
            if (truth(jsOr((!truth(technical))) { (!truth(canvas)) })) {
                return absent
            }
            panel.classList.add("hunter-premium3865")
            val banner: dynamic = make("div", "hunter-premium-banner3865")
            val title: dynamic = make("div", "hunter-premium-banner-title3865")
            val eyebrow: dynamic = make("span", "hunter-premium-eyebrow3865", (if (truth(compare)) "DEMOSTRACIÓN ANATÓMICA 2D" else "DEMOSTRACIÓN TÉCNICA 2D"))
            val heading: dynamic = make("strong", "", (if (truth(compare)) "Vista detallada del movimiento" else "Visualización bajo revisión"))
            title.append(eyebrow, heading)
            val quality: dynamic = make("span", "hunter-premium-marker3865", (if (truth(compare)) "48 FOTOGRAMAS" else "REVISAR TÉCNICA"))
            banner.append(title, quality)
            panel.insertBefore(banner, jsOr(jsOr(compare) { QA }) { canvas })
            if (truth((!truth(compare)))) {
                if (truth((id === "hs379-isobiceps"))) {
                    run {
                        val previous14: dynamic = numeric(shown)
                        shown = jsAdd(previous14, 1)
                    }
                    panel.classList.add("hunter-premium-supported3865")
                    val hero: dynamic = make("div", "hunter-compare3863 hunter-premium-stage3865 hunter-premium-static3865")
                    val image: dynamic = make("div", "motion-svg18 hunter-anatomy3863")
                    image.setAttribute("role", "img")
                    image.setAttribute("aria-label", "Ilustración anatómica de la posición sentada con resistencia propia")
                    val description: dynamic = make("p", "footnote", "Posición sostenida o recorrido pequeño. No se añade una repetición artificial. Contrasta los puntos de contacto con el esquema original.")
                    val label: dynamic = make("div", "hunter-premium-stagecaption3865", "VISTA ANATÓMICA · POSTURA SENTADA")
                    val status: dynamic = make("p", "footnote hunter-compare-status3863", "Preparando ilustración anatómica…")
                    val disclosure: dynamic = make("button", "btn ghost hunter-premium-technicalbtn3865", "Comparar con el esquema técnico")
                    disclosure.type = "button"
                    disclosure.setAttribute("aria-expanded", "false")
                    disclosure.addEventListener("click", (fun(): dynamic {
                        val `open`: dynamic = panel.classList.toggle("hunter-premium-tech-visible3865")
                        disclosure.setAttribute("aria-expanded", global_String(`open`))
                        disclosure.textContent = (if (truth(`open`)) "Ocultar esquema técnico" else "Comparar con el esquema técnico")
                        return absent
                    }))
                    hero.append(image, label, status, description, disclosure)
                    panel.insertBefore(hero, jsOr(QA) { canvas })
                    val techLabel: dynamic = panel.querySelector(".hunter-technical3864")
                    if (truth(techLabel)) {
                        techLabel.classList.add("hunter-premium-source-label3865")
                    }
                    val session: dynamic = jsObject { obj19 ->
                        obj19["id"] = id
                        obj19["panel"] = panel
                        obj19["last"] = (-numeric(1))
                        obj19["assetReady"] = false
                        obj19["draw"] = fun(force: dynamic): dynamic {
                            val receiver20: dynamic = js("this")
                            if (truth(jsOr(jsOr(jsOr((!truth(receiver20.assetReady))) { (!truth(receiver20.panel.isConnected)) }) { (js("typeof vectorSession") === "undefined") }) { (onPresent(global_vectorSession) { value22 -> value22.id } !== id) })) {
                                return absent
                            }
                            val frame: dynamic = jsOr(global_Number(global_vectorSession.frame)) { 0 }
                            if (truth(jsAnd((!truth(force))) { jsLess(global_Math.abs(jsSubtract(frame, receiver20.last)), 0.8) })) {
                                return absent
                            }
                            receiver20.last = frame
                            try {
                                val svg: dynamic = root.HunterDemos46.anatomicalSvg(id, frame, (!truth((!truth(global_vectorSession.mirror)))))
                                if (truth((!truth(svg)))) {
                                    return absent
                                }
                                image.innerHTML = svg
                                val node: dynamic = image.firstElementChild
                                node.setAttribute("viewBox", "120 45 480 620")
                                status.textContent = "Imagen orientativa: la posición del brazo puede variar muy poco en este modelo."
                            } catch (e: dynamic) {
                                run {
                                    val previous29: dynamic = numeric(loadErrors)
                                    loadErrors = jsAdd(previous29, 1)
                                }
                                status.textContent = "No se pudo mostrar la figura. Consulta el esquema técnico."
                                panel.classList.add("hunter-premium-tech-visible3865")
                            }
                            return absent
                        }
                    }
                    special = session
                    root.HunterDemos46.load(id)["then"]((fun(): dynamic {
                        if (truth(jsAnd((special === session)) { panel.isConnected })) {
                            session.assetReady = true
                            session.draw(true)
                        }
                        return absent
                    }))["catch"]((fun(): dynamic {
                        run {
                            val previous36: dynamic = numeric(loadErrors)
                            loadErrors = jsAdd(previous36, 1)
                        }
                        status.textContent = "No se pudo cargar la ilustración anatómica. Usa el esquema técnico."
                        panel.classList.add("hunter-premium-tech-visible3865")
                        return absent
                    }))
                    return absent
                }
                run {
                    val previous39: dynamic = numeric(review)
                    review = jsAdd(previous39, 1)
                }
                val warning: dynamic = make("p", "hunter-premium-underreview3865", "Este modelo requiere revisión de postura o movimiento. Se muestra su esquema técnico sin crear una animación anatómica que pudiera ser incorrecta.")
                panel.insertBefore(warning, canvas)
                return absent
            }
            run {
                val previous40: dynamic = numeric(shown)
                shown = jsAdd(previous40, 1)
            }
            panel.classList.add("hunter-premium-supported3865")
            val btn: dynamic = compare.querySelector(".hunter-compare-button3863")
            image = compare.querySelector(".hunter-anatomy3863")
            val status: dynamic = compare.querySelector(".hunter-compare-status3863")
            if (truth(jsOr((!truth(btn))) { (!truth(image)) })) {
                return absent
            }
            image.setAttribute("role", "img")
            image.setAttribute("aria-label", "Figura anatómica ilustrativa sincronizada con los fotogramas del ejercicio")
            compare.classList.add("hunter-premium-stage3865")
            compare.insertBefore(image, compare.firstChild)
            val stageLabel: dynamic = make("div", "hunter-premium-stagecaption3865", "Vista anatómica ilustrativa · misma trayectoria del esquema técnico")
            compare.insertBefore(stageLabel, image.nextSibling)
            val caption: dynamic = compare.querySelector(".footnote")
            if (truth(caption)) {
                caption.textContent = "La figura es orientativa. Para comprobar apoyos, accesorios y posiciones exactas, despliega el esquema técnico original."
            }
            val technicalButton: dynamic = make("button", "btn ghost hunter-premium-technicalbtn3865", "Mostrar esquema técnico original")
            technicalButton.type = "button"
            technicalButton.setAttribute("aria-expanded", "false")
            technicalButton.addEventListener("click", (fun(): dynamic {
                val enabled: dynamic = panel.classList.toggle("hunter-premium-tech-visible3865")
                technicalButton.setAttribute("aria-expanded", global_String(enabled))
                technicalButton.textContent = (if (truth(enabled)) "Ocultar esquema técnico" else "Comparar con el esquema técnico")
                return absent
            }))
            val technicalLabel: dynamic = panel.querySelector(".hunter-technical3864")
            if (truth(technicalLabel)) {
                technicalLabel.textContent = "ESQUEMA TÉCNICO · APOYOS Y EQUIPO"
                technicalLabel.classList.add("hunter-premium-source-label3865")
            }
            val controls: dynamic = panel.querySelector(".hunter-controls3864")
            if (truth(controls)) {
                controls.insertAdjacentElement("afterend", technicalButton)
            } else {
                panel.insertBefore(technicalButton, canvas)
            }
            if (truth(status)) {
                status.setAttribute("aria-live", "polite")
            }
            camera = dynamicValue(null)
            zoomed = true
            cameraObserver = jsConstruct(global_MutationObserver, arrayOf<dynamic>((fun(m: dynamic): dynamic {
                if (truth((!truth(panel.isConnected)))) {
                    cameraObserver.disconnect()
                    return absent
                }
                if (truth(m.some((fun(x: dynamic): dynamic {
                    return (x.type === "childList")
                })))) {
                    drawCamera()
                }
                return absent
            })))
            cameraObserver.observe(image, jsonValue("{\"childList\":true}"))
            val framing: dynamic = make("button", "btn ghost hunter-premium-framing3865", "Mostrar encuadre completo")
            framing.type = "button"
            framing.setAttribute("aria-pressed", "true")
            framing.addEventListener("click", (fun(): dynamic {
                zoomed = (!truth(zoomed))
                framing.textContent = (if (truth(zoomed)) "Mostrar encuadre completo" else "Ampliar figura")
                framing.setAttribute("aria-pressed", global_String(zoomed))
                val svg: dynamic = image.querySelector("svg")
                if (truth(svg)) {
                    svg.setAttribute("viewBox", (if (truth(jsAnd(zoomed) { camera })) camera else svg.dataset.originalView3865))
                }
                return absent
            }))
            compare.append(framing)
            root.HunterDemos46.load(id)["then"]((fun(asset: dynamic): dynamic {
                if (truth(jsOr((!truth(onPresent(asset) { value78 -> onPresent(value78.model) { value79 -> value79.frames } }))) { (!truth(panel.isConnected)) })) {
                    return absent
                }
                val points: dynamic = js("[]")
                if (true) {
                    val iterator80: dynamic = iteratorOf(asset.model.frames)
                    while (true) {
                        val next81: dynamic = iterator80.next()
                        if (truth(next81.done)) break
                        var f: dynamic = absent
                        f = next81.value
                        if (truth(global_Array.isArray(f.p))) {
                            if (true) {
                                val iterator82: dynamic = iteratorOf(f.p)
                                while (true) {
                                    val next83: dynamic = iterator82.next()
                                    if (truth(next83.done)) break
                                    var q: dynamic = absent
                                    q = next83.value
                                    if (truth(jsAnd(jsAnd(global_Array.isArray(q)) { jsAtLeast(q.length, 2) }) { q.every(global_Number.isFinite) })) {
                                        points.push(q)
                                    }
                                }
                            }
                        }
                        if (truth(global_Array.isArray(f.loads))) {
                            if (true) {
                                val iterator84: dynamic = iteratorOf(f.loads)
                                while (true) {
                                    val next85: dynamic = iterator84.next()
                                    if (truth(next85.done)) break
                                    var q: dynamic = absent
                                    q = next85.value
                                    if (truth(jsAnd(jsAnd(global_Array.isArray(q)) { jsAtLeast(q.length, 2) }) { q.every(global_Number.isFinite) })) {
                                        points.push(q)
                                    }
                                }
                            }
                        }
                        if (truth(global_Array.isArray(f.equipment))) {
                            if (true) {
                                val iterator86: dynamic = iteratorOf(f.equipment)
                                while (true) {
                                    val next87: dynamic = iterator86.next()
                                    if (truth(next87.done)) break
                                    var item: dynamic = absent
                                    item = next87.value
                                    val r: dynamic = onPresent(item) { value88 -> value88[1] }
                                    if (truth(jsAnd(jsAnd(global_Array.isArray(r)) { (r.length === 4) }) { r.every(global_Number.isFinite) })) {
                                        points.push(arrayOf<dynamic>(r[0], r[1]), arrayOf<dynamic>(r[2], r[3]))
                                    }
                                }
                            }
                        }
                    }
                }
                if (truth((!truth(points.length)))) {
                    return absent
                }
                val loX: dynamic = run { val target90: dynamic = global_Math; invokeWith(target90.min, target90, jsArray { array89 ->
                    appendAll(array89, points.map((fun(p: dynamic): dynamic {
                        return p[0]
                    })))
                }) }
                val hiX: dynamic = run { val target92: dynamic = global_Math; invokeWith(target92.max, target92, jsArray { array91 ->
                    appendAll(array91, points.map((fun(p: dynamic): dynamic {
                        return p[0]
                    })))
                }) }
                val loY: dynamic = run { val target94: dynamic = global_Math; invokeWith(target94.min, target94, jsArray { array93 ->
                    appendAll(array93, points.map((fun(p: dynamic): dynamic {
                        return p[1]
                    })))
                }) }
                val hiY: dynamic = run { val target96: dynamic = global_Math; invokeWith(target96.max, target96, jsArray { array95 ->
                    appendAll(array95, points.map((fun(p: dynamic): dynamic {
                        return p[1]
                    })))
                }) }
                var w: dynamic = global_Math.max(285, jsAdd(jsSubtract(hiX, loX), 150))
                var h: dynamic = global_Math.max(400, jsAdd(jsSubtract(hiY, loY), 150))
                w = global_Math.max(w, jsMultiply(h, 0.84))
                h = global_Math.max(h, jsDivide(w, 0.84))
                if (truth(jsMore(w, 720))) {
                    w = 720
                    h = global_Math.max(h, jsDivide(w, 0.84))
                }
                if (truth(jsMore(h, 850))) {
                    h = 850
                    w = global_Math.min(720, global_Math.max(w, jsMultiply(h, 0.84)))
                }
                val centerX: dynamic = clamp(jsDivide(jsAdd(loX, hiX), 2), jsDivide(w, 2), jsSubtract(720, jsDivide(w, 2)))
                val centerY: dynamic = clamp(jsDivide(jsAdd(loY, hiY), 2), jsDivide(h, 2), jsSubtract(850, jsDivide(h, 2)))
                camera = dynamicValue(arrayOf<dynamic>(jsSubtract(centerX, jsDivide(w, 2)), jsSubtract(centerY, jsDivide(h, 2)), w, h)).map((fun(x: dynamic): dynamic {
                    return global_Number(x.toFixed(2))
                })).join(" ")
                drawCamera()
                return absent
            }))["catch"]((fun(): dynamic {
                run {
                    val previous97: dynamic = numeric(loadErrors)
                    loadErrors = jsAdd(previous97, 1)
                }
                panel.classList.add("hunter-premium-tech-visible3865")
                technicalButton.textContent = "Ocultar esquema técnico"
                technicalButton.setAttribute("aria-expanded", "true")
                return absent
            }))
            try {
                btn.click()
                if (truth((btn.getAttribute("aria-pressed") !== "true"))) {
                    run {
                        val previous100: dynamic = numeric(loadErrors)
                        loadErrors = jsAdd(previous100, 1)
                    }
                }
            } catch (e: dynamic) {
                run {
                    val previous101: dynamic = numeric(loadErrors)
                    loadErrors = jsAdd(previous101, 1)
                }
                panel.classList.remove("hunter-premium-supported3865")
            }
            btn.textContent = "Ver solo esquema técnico"
            btn.classList.add("hunter-premium-switch3865")
            btn.setAttribute("title", "Cambiar entre figura anatómica y esquema técnico original")
            btn.addEventListener("click", (fun(): dynamic {
                val anatomyOn: dynamic = (btn.getAttribute("aria-pressed") === "true")
                btn.textContent = (if (truth(anatomyOn)) "Ver solo esquema técnico" else "Volver a figura anatómica")
                if (truth((!truth(anatomyOn)))) {
                    panel.classList.add("hunter-premium-tech-visible3865")
                    technicalButton.textContent = "Ocultar esquema técnico"
                    technicalButton.setAttribute("aria-expanded", "true")
                } else {
                    panel.classList.remove("hunter-premium-tech-visible3865")
                    technicalButton.textContent = "Comparar con el esquema técnico"
                    technicalButton.setAttribute("aria-expanded", "false")
                }
                return absent
            }))
            technicalButton.textContent = "Comparar con el esquema técnico"
            return absent
        }
        if (truth(jsOr(jsOr(root.HunterMotion3865) { (js("typeof technique") !== "function") }) { (!truth(root.HunterComparador3863)) })) {
            return absent
        }
        val previousTechnique: dynamic = global_technique
        shown = 0
        review = 0
        loadErrors = 0
        special = dynamicValue(null)
        val previousPaint: dynamic = global_vectorPaint
        global_vectorPaint = (fun(): dynamic {
            val receiver3: dynamic = js("this")
            val callArgs4: dynamic = js("arguments")
            val result: dynamic = previousPaint.apply(receiver3, callArgs4)
            if (truth(jsAnd(jsAnd(jsAnd(special) { special.panel.isConnected }) { (js("typeof vectorSession") !== "undefined") }) { (onPresent(global_vectorSession) { value5 -> value5.id } === special.id) })) {
                special.draw(false)
            }
            return result
        })
        val isExtended: dynamic = (fun(id: dynamic): dynamic {
            return dynamicValue(jsRegExp("^(?:new46-|hs379-)", "")).test(global_String(id))
        })
        make = (fun(tag: dynamic, cls: dynamic, text: dynamic): dynamic {
            val el: dynamic = doc.createElement(tag)
            if (truth(cls)) {
                el.className = cls
            }
            if (truth((text !== absent))) {
                el.textContent = text
            }
            return el
        })
        global_technique = (fun(id: dynamic): dynamic {
            val receiver112: dynamic = js("this")
            val callArgs113: dynamic = js("arguments")
            val result: dynamic = previousTechnique.apply(receiver112, callArgs113)
            if (truth(isExtended(id))) {
                improvePanel(id)
            }
            return result
        })
        root.HunterMotion3865 = global_Object.freeze(jsObject { obj116 ->
            obj116["stats"] = (fun(): dynamic {
                return jsObject { obj117 ->
                    obj117["detailedOpened"] = shown
                    obj117["underReviewOpened"] = review
                    obj117["loadErrors"] = loadErrors
                }
            })
            obj116["version"] = "38.65"
            obj116["techniqueValidated"] = false
        })
        return absent
    }))(global_window, global_document)
}
