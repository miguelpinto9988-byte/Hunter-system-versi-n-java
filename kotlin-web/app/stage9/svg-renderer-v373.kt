@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installsvgrendererv373

import hunter.browser.*

@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("parseInt") private external var global_parseInt: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("RegExp") private external var global_RegExp: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Motion30") private external var global_Motion30: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("Motion2D18") private external var global_Motion2D18: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("drawVector") private external var global_drawVector: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installSvgRendererV373() {
    dynamicValue((fun(root: dynamic): dynamic {
        var sessions: dynamic = absent
        var attributes: dynamic = absent
        var texts: dynamic = absent
        var counters48: dynamic = absent
        var literal48: dynamic = absent
        var decoded48: dynamic = absent
        var compile48: dynamic = absent
        var update48: dynamic = absent
        var patch: dynamic = absent
        var draw: dynamic = absent
        decoded48 = fun(s: dynamic): dynamic {
            if (truth((!truth(s.includes("&"))))) {
                return s
            }
            return s.replace(jsRegExp("&(amp|lt|gt|quot|apos|#\\d+|#x[\\da-f]+);", "gi"), (fun(whole: dynamic, key: dynamic): dynamic {
                val named: dynamic = jsonValue("{\"amp\":\"&\",\"lt\":\"<\",\"gt\":\">\",\"quot\":\"\\\"\",\"apos\":\"'\"}")
                if (truth(hasKey(key, named))) {
                    return named[key]
                }
                val point: dynamic = (if (truth((onPresent(key[1]) { value5 -> value5.toLowerCase() } === "x"))) global_parseInt(key.slice(2), 16) else global_parseInt(key.slice(1), 10))
                return (if (truth(jsAnd(jsMore(point, 0)) { jsAtMost(point, 1114111) })) global_String.fromCodePoint(point) else "�")
            }))
        }
        compile48 = fun(markup: dynamic, svg: dynamic): dynamic {
            var match: dynamic = absent
            val nodes: dynamic = jsArray { array8 ->
                array8.push(svg)
                appendAll(array8, svg.querySelectorAll("*"))
            }
            val stack: dynamic = js("[]")
            val bindings: dynamic = js("[]")
            val parts: dynamic = js("[]")
            val token: dynamic = jsRegExp("<!--[^]*?-->|<[^>]*>|[^<]+", "g")
            var index: dynamic = 0
            var end: dynamic = 0
            val capture: dynamic = (fun(from: dynamic, to: dynamic, node: dynamic, name: dynamic, quote: dynamic): dynamic {
                parts.push(literal48(markup.slice(end, from)), (if (truth(name)) (if (truth((quote === "'"))) "([^']*)" else "([^\"]*)") else "([^<]*)"))
                end = to
                bindings.push(jsObject { obj9 ->
                    obj9["node"] = node
                    obj9["name"] = name
                    obj9["raw"] = markup.slice(from, to)
                })
                return absent
            })
            while (truth(run {
                val assigned10: dynamic = token.exec(markup)
                match = assigned10
                assigned10
            })) {
                val `value`: dynamic = match[0]
                if (truth(`value`.startsWith("<!--"))) {
                    continue
                }
                if (truth(`value`.startsWith("</"))) {
                    stack.pop()
                    continue
                }
                if (truth((`value`[0] === "<"))) {
                    var a: dynamic = absent
                    val tag: dynamic = onPresent(`value`.match(jsRegExp("^<([\\w:-]+)", ""))) { value11 -> value11[1] }
                    val node: dynamic = nodes[run {
                        val previous12: dynamic = numeric(index)
                        val assigned13: dynamic = jsAdd(previous12, 1)
                        index = assigned13
                        previous12
                    }]
                    if (truth(jsOr((!truth(node))) { (node.localName !== tag) })) {
                        return null
                    }
                    val attr: dynamic = jsRegExp("([\\w:-]+)\\s*=\\s*([\"'])(.*?)\\2", "g")
                    while (truth(run {
                        val assigned14: dynamic = attr.exec(`value`)
                        a = assigned14
                        assigned14
                    })) {
                        val from: dynamic = jsAdd(jsAdd(jsAdd(match.index, a.index), a[0].indexOf(a[2])), 1)
                        capture(from, jsAdd(from, a[3].length), node, a[1], a[2])
                    }
                    if (truth((!truth(`value`.endsWith("/>"))))) {
                        stack.push(node)
                    }
                } else {
                    if (truth(`value`.trim())) {
                        val node: dynamic = stack.at((-numeric(1)))
                        if (truth(jsOr((!truth(node))) { node.children.length })) {
                            return null
                        }
                        capture(match.index, jsAdd(match.index, `value`.length), node, null)
                    }
                }
            }
            if (truth((index !== nodes.length))) {
                return null
            }
            parts.push(literal48(markup.slice(end)))
            val pattern: dynamic = jsConstruct(global_RegExp, arrayOf<dynamic>(jsAdd(jsAdd("^", parts.join("")), "\$")))
            if (truth((!truth(pattern.test(markup))))) {
                return null
            }
            return jsObject { obj15 ->
                obj15["pattern"] = pattern
                obj15["bindings"] = bindings
                obj15["svg"] = svg
                obj15["detail"] = dynamicValue(null)
            }
        }
        update48 = fun(plan: dynamic, markup: dynamic): dynamic {
            val values: dynamic = plan.pattern.exec(markup)
            if (truth((!truth(values)))) {
                return false
            }
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration18 = true
                while (true) {
                    if (!firstIteration18) {
                        run {
                            val previous19: dynamic = numeric(i)
                            i = jsAdd(previous19, 1)
                        }
                    }
                    firstIteration18 = false
                    if (!truth(jsLess(i, plan.bindings.length))) break
                    val b: dynamic = plan.bindings[i]
                    val raw: dynamic = values[jsAdd(i, 1)]
                    if (truth((raw === b.raw))) {
                        continue
                    }
                    b.raw = raw
                    if (truth((!truth(b.node.isConnected)))) {
                        continue
                    }
                    val `value`: dynamic = decoded48(raw)
                    if (truth(b.name)) {
                        if (truth(jsOr(jsOr((b.node !== plan.svg)) { (!truth(plan.detail)) }) { (b.name !== "viewBox") })) {
                            b.node.setAttribute(b.name, `value`)
                        }
                        if (truth(jsAnd(jsAnd((b.node === plan.svg)) { plan.detail }) { (!truth(dynamicValue(jsonValue("[\"viewBox\",\"class\",\"aria-label\"]")).includes(b.name))) })) {
                            plan.detail.setAttribute(b.name, `value`)
                        }
                    } else {
                        b.node.textContent = `value`
                    }
                }
            }
            return true
        }
        patch = fun(current: dynamic, next: dynamic): dynamic {
            var old: dynamic = attributes["get"](current)
            if (truth((!truth(old)))) {
                old = jsConstruct(global_Map, arrayOf<dynamic>(global_Array.from(current.attributes, (fun(a: dynamic): dynamic {
                    return arrayOf<dynamic>(a.name, a["value"])
                }))))
                attributes["set"](current, old)
            }
            if (truth(jsOr((old.size !== next.attributes.length)) { dynamicValue(jsArray { array27 ->
                appendAll(array27, old.keys())
            }).some((fun(k: dynamic): dynamic {
                return (!truth(next.hasAttribute(k)))
            })) })) {
                if (true) {
                    val iterator28: dynamic = iteratorOf(old.keys())
                    while (true) {
                        val next29: dynamic = iterator28.next()
                        if (truth(next29.done)) break
                        var key: dynamic = absent
                        key = next29.value
                        if (truth((!truth(next.hasAttribute(key))))) {
                            current.removeAttribute(key)
                            old.delete(key)
                        }
                    }
                }
            }
            if (true) {
                val iterator30: dynamic = iteratorOf(next.attributes)
                while (true) {
                    val next31: dynamic = iterator30.next()
                    if (truth(next31.done)) break
                    var a: dynamic = absent
                    a = next31.value
                    if (truth((old["get"](a.name) !== a["value"]))) {
                        current.setAttribute(a.name, a["value"])
                        old["set"](a.name, a["value"])
                    }
                }
            }
            val n: dynamic = next.children.length
            if (truth((!truth(n)))) {
                val `value`: dynamic = next.textContent
                if (truth(((if (truth(texts.has(current))) texts["get"](current) else current.textContent) !== `value`))) {
                    current.textContent = `value`
                }
                texts["set"](current, `value`)
                return absent
            }
            texts.delete(current)
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration34 = true
                while (true) {
                    if (!firstIteration34) {
                        run {
                            val previous35: dynamic = numeric(i)
                            i = jsAdd(previous35, 1)
                        }
                    }
                    firstIteration34 = false
                    if (!truth(jsLess(i, n))) break
                    val a: dynamic = current.children[i]
                    val b: dynamic = next.children[i]
                    if (truth((!truth(a)))) {
                        current.append(b.cloneNode(true))
                    } else {
                        if (truth((a.tagName !== b.tagName))) {
                            a.replaceWith(b.cloneNode(true))
                        } else {
                            patch(a, b)
                        }
                    }
                }
            }
            while (truth(jsMore(current.children.length, n))) {
                current.lastElementChild.remove()
            }
            return absent
        }
        draw = fun(canvas: dynamic, id: dynamic, arg38: dynamic, arg39: dynamic): dynamic {
            var phase: dynamic = absent
            phase = definedOr(arg38) { 0 }
            var mirror: dynamic = absent
            mirror = definedOr(arg39) { false }
            var model: dynamic = absent
            var base: dynamic = absent
            if (truth((!truth(canvas)))) {
                return absent
            }
            val unpacked40: dynamic = global_Motion30["get"](id)
            model = unpacked40["model"]
            base = unpacked40["base"]
            var entry: dynamic = sessions["get"](canvas)
            if (truth(jsOr((!truth(entry))) { (canvas.nextElementSibling !== entry.host) })) {
                canvas.hidden = true
                canvas.dataset.renderer = "2d"
                var host: dynamic = canvas.nextElementSibling
                if (truth((!truth(onPresent(host) { value45 -> value45.classList.contains("motion-svg18") })))) {
                    host = global_document.createElement("div")
                    host.className = "motion-svg18"
                    canvas.after(host)
                }
                entry = jsObject { obj49 ->
                    obj49["host"] = host
                    obj49["template"] = global_document.createElement("template")
                }
                sessions["set"](canvas, entry)
            }
            val host: dynamic = entry.host
            if (truth((canvas.dataset.base !== base))) {
                canvas.dataset.base = base
            }
            if (truth((host.dataset.exercise !== id))) {
                host.dataset.exercise = id
            }
            host.dataset.phase = global_String(phase)
            val accent: dynamic = jsOr(global_document.documentElement.style.getPropertyValue("--accent").trim()) { "#7ce2e9" }
            val markup: dynamic = global_Motion2D18.svg(id, phase, mirror, accent)
            run {
                val target56: dynamic = counters48
                val previous58: dynamic = numeric(target56.draws)
                target56.draws = jsAdd(previous58, 1)
            }
            if (truth(jsAnd(jsAnd(jsAnd((entry.id48 === id)) { entry.plan48 }) { (entry.host.firstElementChild === entry.plan48.svg) }) { update48(entry.plan48, markup) })) {
                run {
                    val target59: dynamic = counters48
                    val previous61: dynamic = numeric(target59.fastFrames)
                    target59.fastFrames = jsAdd(previous61, 1)
                }
                return model
            }
            entry.template.innerHTML = markup
            val next: dynamic = entry.template.content.firstElementChild
            val detail: dynamic = next.querySelector("[data-detail-layout31]")
            var detailSVG: dynamic = dynamicValue(null)
            val plan: dynamic = compile48(markup, next)
            if (truth(detail)) {
                detailSVG = next.cloneNode(false)
                detailSVG.setAttribute("viewBox", "58 620 604 288")
                detailSVG.setAttribute("class", "detail-svg31")
                detailSVG.setAttribute("aria-label", jsAdd("Detalle animado de ", global_E.byId(id).name))
                detailSVG.append(detail)
                next.setAttribute("viewBox", "0 0 720 610")
                onPresent(next.querySelector("text[y=\"972\"]")) { value64 -> value64.remove() }
            }
            val hasDetail: dynamic = global_String((!truth((!truth(detailSVG)))))
            if (truth((host.dataset.hasDetail31 !== hasDetail))) {
                host.dataset.hasDetail31 = hasDetail
            }
            if (truth(plan)) {
                host.replaceChildren(next)
                if (truth(detailSVG)) {
                    host.append(detailSVG)
                }
                plan.detail = detailSVG
                entry.plan48 = plan
                entry.id48 = id
                run {
                    val target73: dynamic = counters48
                    val previous75: dynamic = numeric(target73.compiled)
                    target73.compiled = jsAdd(previous75, 1)
                }
            } else {
                entry.plan48 = dynamicValue(null)
                val current: dynamic = host.firstElementChild
                if (truth((onPresent(current) { value78 -> value78.tagName } === next.tagName))) {
                    patch(current, next)
                } else {
                    host.replaceChildren(next)
                }
                if (truth(detailSVG)) {
                    val currentDetail: dynamic = host.children[1]
                    if (truth(currentDetail)) {
                        patch(currentDetail, detailSVG)
                    } else {
                        host.append(detailSVG)
                    }
                } else {
                    onPresent(host.children[1]) { value79 -> value79.remove() }
                }
                run {
                    val target80: dynamic = counters48
                    val previous82: dynamic = numeric(target80.fallbacks)
                    target80.fallbacks = jsAdd(previous82, 1)
                }
            }
            return model
        }
        sessions = jsConstruct(global_WeakMap, js("[]"))
        attributes = jsConstruct(global_WeakMap, js("[]"))
        texts = jsConstruct(global_WeakMap, js("[]"))
        counters48 = jsonValue("{\"draws\":0,\"compiled\":0,\"fastFrames\":0,\"fallbacks\":0}")
        literal48 = (fun(s: dynamic): dynamic {
            return s.replace(jsRegExp("[.*+?^\${}()|[\\]\\\\]", "g"), "\\\$&")
        })
        root.SvgRenderer373 = jsObject { obj85 ->
            obj85["draw"] = draw
            obj85["patch"] = patch
            obj85["snapshot48"] = (fun(): dynamic {
                return jsObject { obj86 ->
                    copyFields(obj86, counters48)
                }
            })
        }
        global_drawVector = draw
        global_vectorPaint = (fun(): dynamic {
            val s: dynamic = global_vectorSession
            val c: dynamic = global_Dollar("#vectorCanvas")
            if (truth(jsOr((!truth(s))) { (!truth(c)) })) {
                return absent
            }
            global_drawVector(c, s.id, s.frame, s.mirror)
            val `value`: dynamic = global_String(jsMod(global_Math.floor(s.frame), 48))
            val range: dynamic = global_Dollar("#vectorRange")
            val phase: dynamic = global_Dollar("#vectorPhase")
            val label: dynamic = global_Motion2D18.label(s.id, s.frame)
            if (truth(jsAnd(range) { (range["value"] !== `value`) })) {
                range["value"] = `value`
            }
            if (truth(jsAnd(phase) { (phase.textContent !== label) })) {
                phase.textContent = label
            }
            return absent
        })
        return absent
    }))(global_window)
}
