@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterui3846

import hunter.browser.*

@JsName("Error") private external var global_Error: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("pendingRestore46") private external var global_pendingRestore46: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("storageError") private external var global_storageError: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("V4") private external var global_V4: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("equipmentLabel23") private external var global_equipmentLabel23: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("Promise") private external var global_Promise: dynamic
@JsName("fetch") private external var global_fetch: dynamic
@JsName("Motion30") private external var global_Motion30: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Motion2D18") private external var global_Motion2D18: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("openBodyKnowledge") private external var global_openBodyKnowledge: dynamic

internal fun installHunterUi3846() {
    dynamicValue((fun(root: dynamic): dynamic {
        var C: dynamic = absent
        var E: dynamic = absent
        var cache: dynamic = absent
        var pending: dynamic = absent
        var touch: dynamic = absent
        var load: dynamic = absent
        var attach: dynamic = absent
        var seek: dynamic = absent
        touch = fun(id: dynamic, `value`: dynamic): dynamic {
            cache.delete(id)
            cache["set"](id, `value`)
            while (truth(jsMore(cache.size, 3))) {
                cache.delete(cache.keys().next()["value"])
            }
            return `value`
        }
        load = fun(id: dynamic): dynamic {
            return asyncAction asyncBody72@ {
                if (truth(cache.has(id))) {
                    return@asyncBody72 touch(id, cache["get"](id))
                }
                if (truth(pending.has(id))) {
                    return@asyncBody72 pending["get"](id)
                }
                val ex: dynamic = E.byId(id)
                if (truth(jsOr((!truth(C.owned(id)))) { (!truth(ex)) })) {
                    return@asyncBody72 null
                }
                val promise: dynamic = global_Promise.all(arrayOf<dynamic>(global_fetch(ex.modelPath30)["then"]((fun(r: dynamic): dynamic {
                    if (truth((!truth(r.ok)))) {
                        throw global_Error("Modelo local no disponible")
                    }
                    return r.json()
                })), global_fetch(ex.demoPath30)["then"]((fun(r: dynamic): dynamic {
                    if (truth((!truth(r.ok)))) {
                        throw global_Error("SVG local no disponible")
                    }
                    return r.text()
                }))))["then"]((fun(arg79: dynamic): dynamic {
                    var model: dynamic = absent
                    var svg: dynamic = absent
                    val unpacked80: dynamic = fromIterable(arg79)
                    model = unpacked80[0]
                    svg = unpacked80[1]
                    if (truth(jsOr(jsOr((model.id !== id)) { (onPresent(model.frames) { value81 -> value81.length } !== 48) }) { (!truth(svg.startsWith("<svg"))) })) {
                        throw global_Error(jsAdd("Demostración local incompleta: ", id))
                    }
                    return touch(id, jsObject { obj82 ->
                        obj82["model"] = model
                        obj82["svg"] = svg
                    })
                }))["finally"]((fun(): dynamic {
                    return pending.delete(id)
                }))
                pending["set"](id, promise)
                return@asyncBody72 promise
                absent
            }
        }
        attach = fun(entry: dynamic, asset: dynamic): dynamic {
            if (truth(jsOr((!truth(entry.host.isConnected))) { (entry.host.dataset.exercise !== entry.id) })) {
                return absent
            }
            entry.host.innerHTML = asset.svg
            val svg: dynamic = entry.host.firstElementChild
            val detail: dynamic = svg.querySelector("[data-detail-layout46]")
            svg.setAttribute("viewBox", "0 0 720 610")
            val detailSVG: dynamic = svg.cloneNode(false)
            detailSVG.classList.add("detail-svg31")
            detailSVG.setAttribute("viewBox", "58 620 604 320")
            detailSVG.append(detail)
            entry.host.append(detailSVG)
            entry.host.dataset.hasDetail31 = "true"
            entry.svgs = arrayOf<dynamic>(svg, detailSVG)
            entry.duration = asset.model.duration46
            entry.asset = asset
            if (true) {
                val iterator119: dynamic = iteratorOf(entry.svgs)
                while (true) {
                    val next120: dynamic = iterator119.next()
                    if (truth(next120.done)) break
                    var s: dynamic = absent
                    s = next120.value
                    s.pauseAnimations()
                }
            }
            seek(entry, entry.phase, entry.mirror)
            val phaseLabel: dynamic = global_document.getElementById("vectorPhase")
            if (truth(jsAnd(jsAnd(jsAnd(phaseLabel) { (js("typeof vectorSession") !== "undefined") }) { (onPresent(global_vectorSession) { value121 -> value121.id } === entry.id) }) { entry.host.closest("#modal") })) {
                phaseLabel.textContent = global_Motion2D18.label(entry.id, entry.phase)
            }
            return absent
        }
        seek = fun(entry: dynamic, phase: dynamic, mirror: dynamic): dynamic {
            if (truth((!truth(entry.svgs)))) {
                return absent
            }
            val time: dynamic = jsMultiply(jsDivide(jsMod(jsAdd(jsMod(jsOr(global_Number(phase)) { 0 }, 48), 48), 48), 48), entry.duration)
            if (true) {
                val iterator126: dynamic = iteratorOf(entry.svgs)
                while (true) {
                    val next127: dynamic = iterator126.next()
                    if (truth(next127.done)) break
                    var svg: dynamic = absent
                    svg = next127.value
                    svg.setCurrentTime(time)
                }
            }
            if (truth((entry.lastMirror !== (!truth((!truth(mirror))))))) {
                entry.lastMirror = (!truth((!truth(mirror))))
                onPresent(entry.host.querySelector("[data-body46]")) { value130 -> value130.setAttribute("transform", (if (truth(mirror)) "translate(720 0) scale(-1 1)" else "")) }
                onPresent(entry.host.querySelector("[data-detail-content46]")) { value131 -> value131.setAttribute("transform", (if (truth(mirror)) "translate(720 0) scale(-1 1)" else "")) }
            }
            return absent
        }
        C = root.HunterCatalog46
        E = root.Engine
        if (truth((!truth(C)))) {
            throw global_Error("Falta catálogo 38.46")
        }
        val esc: dynamic = (fun(s: dynamic): dynamic {
            return global_String(nullableOr(s) { "" }).replace(jsRegExp("[&<>\"']", "g"), (fun(c: dynamic): dynamic {
                return jsonValue("{\"&\":\"&amp;\",\"<\":\"&lt;\",\">\":\"&gt;\",\"\\\"\":\"&quot;\",\"'\":\"&#39;\"}")[c]
            }))
        })
        val previousValidation: dynamic = global_validateBackup
        global_validateBackup = (fun(`data`: dynamic): dynamic {
            if (truth((!truth(previousValidation(`data`))))) {
                return false
            }
            return dynamicValue(jsArray { array8 ->
                array8.push(`data`.profile)
                appendAll(array8, dynamicValue(jsOr(`data`.cycles) { js("[]") }).map((fun(c: dynamic): dynamic {
                    return c.profile
                })))
            }).filter(global_Boolean).every((fun(p: dynamic): dynamic {
                return dynamicValue(jsonValue("[\"materials46\",\"capabilities46\"]")).every((fun(k: dynamic): dynamic {
                    return jsOr((p[k] === absent)) { jsAnd(global_Array.isArray(p[k])) { p[k].every((fun(x: dynamic): dynamic {
                        return global_Object.prototype.hasOwnProperty.call((if (truth((k === "materials46"))) C.materials else C.capabilities), x)
                    })) } }
                }))
            }))
        })
        if (truth(jsAnd(jsAnd((js("typeof pendingRestore46") !== "undefined")) { global_pendingRestore46 }) { global_validateBackup(global_pendingRestore46) })) {
            global_state = global_pendingRestore46
            global_storageError = false
            global_pendingRestore46 = dynamicValue(null)
        }
        val oldProfile: dynamic = global_profileForm
        global_profileForm = (fun(arg11: dynamic): dynamic {
            val receiver9: dynamic = js("this")
            val callArgs10: dynamic = js("arguments")
            var initial: dynamic = absent
            initial = definedOr(arg11) { false }
            val html: dynamic = oldProfile.apply(receiver9, callArgs10)
            val holder: dynamic = global_document.createElement("div")
            holder.innerHTML = html
            val form: dynamic = holder.querySelector("#profileForm")
            if (truth(jsOr((!truth(form))) { form.querySelector("[data-catalog46]") })) {
                return html
            }
            val p: dynamic = jsOr(onPresent(global_state) { value14 -> value14.profile }) { js("({})") }
            val selected: dynamic = jsConstruct(global_Set, arrayOf<dynamic>((if (truth(global_Array.isArray(p.materials46))) p.materials46 else js("[]"))))
            val capabilities: dynamic = jsConstruct(global_Set, arrayOf<dynamic>((if (truth(global_Array.isArray(p.capabilities46))) p.capabilities46 else js("[]"))))
            val materials: dynamic = global_Object.entries(C.materials).filter((fun(arg15: dynamic): dynamic {
                var k: dynamic = absent
                val unpacked16: dynamic = fromIterable(arg15)
                k = unpacked16[0]
                return (!truth(dynamicValue(jsonValue("[\"db\",\"bench\",\"step\",\"barbell\",\"cable\"]")).includes(k)))
            }))
            val checks: dynamic = (fun(xs: dynamic, name: dynamic, `set`: dynamic): dynamic {
                return xs.map((fun(arg19: dynamic): dynamic {
                    var key: dynamic = absent
                    var label: dynamic = absent
                    val unpacked20: dynamic = fromIterable(arg19)
                    key = unpacked20[0]
                    label = unpacked20[1]
                    return ("<label class=\"check46\"><input type=\"checkbox\" name=\"" + textOf(name) + "\" value=\"" + textOf(esc(key)) + "\" " + textOf((if (truth(`set`.has(key))) "checked" else "")) + "><span>" + textOf(esc(label)) + "</span></label>")
                })).join("")
            })
            val block: dynamic = global_document.createElement("section")
            block.className = "card catalog-profile46"
            block.dataset.catalog46 = "true"
            block.innerHTML = ("<details><summary>Material adicional y capacidades <span aria-hidden=\"true\">⌄</span></summary><p>Marca solo lo que tienes disponible y lo que puedes realizar con control. Puedes actualizarlo desde Perfil.</p><details><summary>Material disponible</summary><div class=\"check-grid46\">" + textOf(checks(materials, "materials46", selected)) + "</div></details><details><summary>Capacidades para movimientos complejos</summary><p class=\"footnote\">Son datos declarados por ti, no una evaluación clínica. Elegir «Avanzado» no activa estas capacidades.</p><div class=\"check-grid46\">" + textOf(checks(global_Object.entries(C.capabilities), "capabilities46", capabilities)) + "</div></details></details>")
            val step: dynamic = jsOr(jsOr(form.querySelector("[data-profile-step=\"2\"]")) { form.querySelector(".profile-block:last-of-type") }) { form }
            step.append(block)
            return holder.innerHTML
        })
        if (truth(jsAnd((js("typeof V4") === "object")) { (jsTypeOf(global_V4.submitProfile) === "function") })) {
            val before: dynamic = global_V4.submitProfile
            global_V4.submitProfile = (fun(ev: dynamic): dynamic {
                val receiver29: dynamic = js("this")
                val callArgs30: dynamic = js("arguments")
                val form: dynamic = onPresent(ev) { value31 -> value31.target }
                val old: dynamic = onPresent(global_state) { value32 -> value32.profile }
                val materials: dynamic = (if (truth(form)) dynamicValue(jsArray { array34 ->
                    appendAll(array34, form.querySelectorAll("input[name=\"materials46\"]:checked"))
                }).map((fun(x: dynamic): dynamic {
                    return x["value"]
                })) else js("[]"))
                val capabilities: dynamic = (if (truth(form)) dynamicValue(jsArray { array36 ->
                    appendAll(array36, form.querySelectorAll("input[name=\"capabilities46\"]:checked"))
                }).map((fun(x: dynamic): dynamic {
                    return x["value"]
                })) else js("[]"))
                val answer: dynamic = before.apply(receiver29, callArgs30)
                if (truth(jsAnd(jsAnd((onPresent(form) { value37 -> value37.id } === "profileForm")) { onPresent(global_state) { value38 -> value38.profile } }) { (global_state.profile !== old) })) {
                    global_state.profile.materials46 = materials
                    global_state.profile.capabilities46 = capabilities
                    if (truth((js("typeof save") === "function"))) {
                        global_save()
                    }
                }
                return answer
            })
        }
        if (truth(onPresent(root.AdaptiveV9) { value43 -> value43.recommend })) {
            val before: dynamic = root.AdaptiveV9.recommend
            root.AdaptiveV9.recommend = (fun(logs: dynamic, item: dynamic, p: dynamic, now: dynamic): dynamic {
                val receiver46: dynamic = js("this")
                val callArgs47: dynamic = js("arguments")
                val e: dynamic = E.byId(item.id)
                val `out`: dynamic = before.apply(receiver46, callArgs47)
                if (truth((!truth(onPresent(e) { value48 -> value48.doseCap46 })))) {
                    return `out`
                }
                val result: dynamic = C.cap(e, p, jsObject { obj49 ->
                    copyFields(obj49, item)
                    obj49["target"] = `out`.target
                }, jsObject { obj50 ->
                    obj50["feedback"] = jsObject { obj51 ->
                        obj51["canProgress"] = (`out`.action === "aumentar")
                    }
                    obj50["logs"] = logs
                    obj50["date"] = now
                })
                return jsObject { obj52 ->
                    copyFields(obj52, `out`)
                    obj52["target"] = result.target
                    obj52["action"] = (if (truth(jsMore(result.target, item.target))) "aumentar" else (if (truth(jsLess(result.target, item.target))) "reducir" else "mantener"))
                    obj52["reason"] = jsAdd(`out`.reason, " Se respeta el límite técnico específico del ejercicio.")
                }
            })
        }
        val originalDose: dynamic = global_OwnRoutines18.dose
        global_OwnRoutines18.dose = (fun(e: dynamic, p: dynamic, kind: dynamic): dynamic {
            val receiver55: dynamic = js("this")
            val callArgs56: dynamic = js("arguments")
            if (truth((!truth(onPresent(e) { value57 -> value57.doseCap46 })))) {
                return originalDose.apply(receiver55, callArgs56)
            }
            val c: dynamic = e.doseCap46
            return C.cap(e, p, jsObject { obj58 ->
                obj58["id"] = e.id
                obj58["sets"] = 2
                obj58["target"] = c.initial
                obj58["rest"] = c.rest
            })
        })
        val intensityDose: dynamic = global_TrainingIntensity.dose
        global_TrainingIntensity.dose = (fun(e: dynamic, p: dynamic, kind: dynamic): dynamic {
            val receiver61: dynamic = js("this")
            val callArgs62: dynamic = js("arguments")
            return (if (truth(onPresent(e) { value63 -> value63.doseCap46 })) global_OwnRoutines18.dose(e, p, kind) else intensityDose.apply(receiver61, callArgs62))
        })
        if (truth((js("typeof equipmentLabel23") === "function"))) {
            val label: dynamic = global_equipmentLabel23
            global_equipmentLabel23 = (fun(e: dynamic): dynamic {
                return (if (truth(onPresent(e) { value66 -> onPresent(value66.materials46) { value67 -> value67.length } })) e.materials46.map((fun(x: dynamic): dynamic {
                    return C.materials[x]
                })).join(" + ") else label(e))
            })
        }
        cache = jsConstruct(global_Map, js("[]"))
        pending = jsConstruct(global_Map, js("[]"))
        val sessions: dynamic = jsConstruct(global_WeakMap, js("[]"))
        val placeholder: dynamic = jsObject { obj68 ->
            obj68["frames"] = global_Array.from(jsonValue("{\"length\":48}"), (fun(): dynamic {
                return jsObject { obj69 ->
                    obj69["p"] = global_Array.from(jsonValue("{\"length\":11}"), (fun(): dynamic {
                        return jsonValue("[360,320]")
                    }))
                    obj69["loads"] = js("[]")
                    obj69["equipment"] = js("[]")
                    obj69["detail"] = js("({})")
                }
            }))
            obj68["specific30"] = true
            obj68["view21"] = jsonValue("{\"primary\":\"front\",\"label\":\"Frontal\",\"reason\":\"Preparando la vista específica de este movimiento.\"}")
        }
        val oldGet: dynamic = global_Motion30["get"]
        val oldPreload: dynamic = global_Motion30.preload
        global_Motion30["get"] = (fun(id: dynamic): dynamic {
            val receiver85: dynamic = js("this")
            val callArgs86: dynamic = js("arguments")
            if (truth((!truth(C.owned(id))))) {
                return oldGet.apply(receiver85, callArgs86)
            }
            val entry: dynamic = cache["get"](id)
            if (truth((!truth(entry)))) {
                load(id)["catch"]((fun(): dynamic {

                    return absent
                }))
            }
            return jsObject { obj87 ->
                obj87["e"] = E.byId(id)
                obj87["base"] = id
                obj87["model"] = jsOr(onPresent(entry) { value88 -> value88.model }) { jsObject { obj89 ->
                    copyFields(obj89, placeholder)
                    obj89["view21"] = jsOr(onPresent(E.byId(id)) { value90 -> value90.view21 }) { placeholder.view21 }
                } }
            }
        })
        global_Motion30.preload = (fun(id: dynamic): dynamic {
            val receiver93: dynamic = js("this")
            val callArgs94: dynamic = js("arguments")
            return (if (truth(C.owned(id))) load(id)["then"]((fun(x: dynamic): dynamic {
                return (!truth((!truth(x))))
            })) else oldPreload.apply(receiver93, callArgs94))
        })
        if (truth(root.Offline20)) {
            root.Offline20.count = (fun(): dynamic {
                return global_Engine.exercises.filter((fun(e: dynamic): dynamic {
                    return jsAnd(e.demoPath30) { e.modelPath30 }
                })).length
            })
        }
        val beforeSVG: dynamic = global_Motion2D18.svg
        val beforeLabel: dynamic = global_Motion2D18.label
        global_Motion2D18.svg = (fun(id: dynamic, phase: dynamic, mirror: dynamic): dynamic {
            val receiver99: dynamic = js("this")
            val callArgs100: dynamic = js("arguments")
            if (truth((!truth(C.owned(id))))) {
                return beforeSVG.apply(receiver99, callArgs100)
            }
            val `value`: dynamic = cache["get"](id)
            if (truth((!truth(`value`)))) {
                load(id)["catch"]((fun(): dynamic {

                    return absent
                }))
            }
            return jsOr(onPresent(`value`) { value101 -> value101.svg }) { ("<svg xmlns=\"http://www.w3.org/2000/svg\" viewBox=\"0 0 720 610\"><text x=\"360\" y=\"300\" text-anchor=\"middle\" fill=\"#b7d5de\">Preparando demostración sin conexión…</text></svg>") }
        })
        global_Motion2D18.label = (fun(id: dynamic, phase: dynamic): dynamic {
            val receiver104: dynamic = js("this")
            val callArgs105: dynamic = js("arguments")
            if (truth((!truth(C.owned(id))))) {
                return beforeLabel.apply(receiver104, callArgs105)
            }
            val model: dynamic = onPresent(cache["get"](id)) { value106 -> value106.model }
            if (truth((!truth(model)))) {
                return "Preparando demostración sin conexión"
            }
            val n: dynamic = jsSubtract(model.phase46.length, 1)
            val index: dynamic = global_Math.floor(jsMultiply(jsDivide(jsMod(jsAdd(jsMod(jsOr(global_Number(phase)) { 0 }, 48), 48), 48), 48), n))
            return jsOr(model.phase46[index]) { "" }
        })
        val oldDraw: dynamic = global_drawVector
        global_drawVector = (fun(canvas: dynamic, id: dynamic, arg134: dynamic, arg135: dynamic): dynamic {
            val receiver132: dynamic = js("this")
            val callArgs133: dynamic = js("arguments")
            var phase: dynamic = absent
            phase = definedOr(arg134) { 0 }
            var mirror: dynamic = absent
            mirror = definedOr(arg135) { false }
            if (truth((!truth(C.owned(id))))) {
                val host: dynamic = onPresent(canvas) { value136 -> value136.nextElementSibling }
                if (truth(onPresent(host) { value137 -> value137.classList.contains("catalog-motion46") })) {
                    host.classList.remove("catalog-motion46")
                    host.replaceChildren()
                    sessions.delete(canvas)
                }
                return oldDraw.apply(receiver132, callArgs133)
            }
            if (truth((!truth(canvas)))) {
                return absent
            }
            var entry: dynamic = sessions["get"](canvas)
            if (truth(jsOr(jsOr((!truth(entry))) { (entry.id !== id) }) { (canvas.nextElementSibling !== entry.host) })) {
                var host: dynamic = canvas.nextElementSibling
                if (truth((!truth(onPresent(host) { value139 -> value139.classList.contains("motion-svg18") })))) {
                    host = global_document.createElement("div")
                    host.className = "motion-svg18"
                    canvas.after(host)
                }
                host.classList.add("catalog-motion46")
                host.dataset.exercise = id
                canvas.hidden = true
                canvas.dataset.renderer = "2d"
                entry = jsObject { obj149 ->
                    obj149["id"] = id
                    obj149["host"] = host
                    obj149["phase"] = phase
                    obj149["mirror"] = mirror
                }
                sessions["set"](canvas, entry)
                val asset: dynamic = cache["get"](id)
                if (truth(asset)) {
                    attach(entry, asset)
                } else {
                    host.innerHTML = "<p class=\"footnote\">Preparando demostración sin conexión…</p>"
                    load(id)["then"]((fun(`value`: dynamic): dynamic {
                        return attach(entry, `value`)
                    }))["catch"]((fun(err: dynamic): dynamic {
                        if (truth(host.isConnected)) {
                            host.replaceChildren()
                            val p: dynamic = global_document.createElement("p")
                            p.textContent = err.message
                            host.append(p)
                        }
                        return absent
                    }))
                }
            }
            entry.phase = phase
            entry.mirror = mirror
            seek(entry, phase, mirror)
            return jsOr(onPresent(entry.asset) { value158 -> value158.model }) { placeholder }
        })
        root.HunterDemos46 = global_Object.freeze(jsObject { obj161 ->
            obj161["load"] = load
            obj161["draw"] = global_drawVector
            obj161["anatomicalSvg"] = (fun(id: dynamic, arg162: dynamic, arg163: dynamic): dynamic {
                var phase: dynamic = absent
                phase = definedOr(arg162) { 0 }
                var mirror: dynamic = absent
                mirror = definedOr(arg163) { false }
                return (if (truth(jsAnd(C.owned(id)) { cache.has(id) })) beforeSVG(id, phase, mirror) else null)
            })
            obj161["cacheStats"] = (fun(): dynamic {
                return jsObject { obj164 ->
                    obj164["decoded"] = cache.size
                    obj164["pending"] = pending.size
                    obj164["maximum"] = 3
                }
            })
            obj161["clear"] = (fun(): dynamic {
                return cache.clear()
            })
        })
        return absent
    }))(global_window)
    dynamicValue((fun(): dynamic {
        if (truth((js("typeof openBodyKnowledge") !== "function"))) {
            return absent
        }
        val before: dynamic = global_openBodyKnowledge
        global_openBodyKnowledge = (fun(): dynamic {
            val receiver167: dynamic = js("this")
            val callArgs168: dynamic = js("arguments")
            val result: dynamic = before.apply(receiver167, callArgs168)
            val host: dynamic = global_document.querySelector("#modal")
            if (truth((!truth(host)))) {
                return result
            }
            val section: dynamic = global_document.createElement("details")
            section.className = "card"
            section.innerHTML = "<summary>Fuentes y alcance del catálogo ampliado</summary><div class=\"references46\">Cargando referencias locales…</div>"
            host.append(section)
            section.addEventListener("toggle", (fun(): dynamic {
                return asyncAction asyncBody173@ {
                    if (truth(jsOr((!truth(section["open"]))) { section.dataset.loaded })) {
                        return@asyncBody173 absent
                    }
                    val box: dynamic = section.querySelector(".references46")
                    try {
                        val response: dynamic = awaitValue(global_fetch("catalog46/sources.json"))
                        if (truth((!truth(response.ok)))) {
                            throw global_Error()
                        }
                        val `data`: dynamic = awaitValue(response.json())
                        box.replaceChildren()
                        if (true) {
                            val iterator174: dynamic = iteratorOf(`data`.sources)
                            while (true) {
                                val next175: dynamic = iterator174.next()
                                if (truth(next175.done)) break
                                var source: dynamic = absent
                                source = next175.value
                                val p: dynamic = global_document.createElement("p")
                                if (truth(source.url)) {
                                    val a: dynamic = global_document.createElement("a")
                                    a.href = source.url
                                    a.target = "_blank"
                                    a.rel = "noopener"
                                    a.textContent = source.title
                                    p.append(a)
                                } else {
                                    val b: dynamic = global_document.createElement("strong")
                                    b.textContent = source.title
                                    p.append(b)
                                }
                                p.append(global_document.createElement("br"), global_document.createTextNode(source.scope))
                                box.append(p)
                            }
                        }
                        val p: dynamic = global_document.createElement("p")
                        p.textContent = `data`.limits
                        box.append(p)
                        section.dataset.loaded = "true"
                    } catch (unusedError: dynamic) {
                        box.textContent = "No se pudieron abrir las referencias locales."
                    }
                    absent
                }
            }), jsonValue("{\"passive\":true}"))
            return result
        })
        return absent
    }))()
}
