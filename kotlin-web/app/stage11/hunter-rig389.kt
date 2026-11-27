@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterrig389

import hunter.browser.*

@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterRig389() {
    dynamicValue((fun(win: dynamic, doc: dynamic): dynamic {
        var limbs: dynamic = absent
        var RE: dynamic = absent
        var decode: dynamic = absent
        var pose: dynamic = absent
        var blendAngle: dynamic = absent
        var safe: dynamic = absent
        decode = fun(`value`: dynamic): dynamic {
            val hit: dynamic = jsAnd((jsTypeOf(`value`) === "string")) { `value`.match(RE) }
            if (truth((!truth(hit)))) {
                return null
            }
            val a: dynamic = numeric(hit[1])
            val x: dynamic = (if (truth((hit[2] === absent))) 0 else numeric(hit[2]))
            val y: dynamic = (if (truth((hit[3] === absent))) 0 else numeric(hit[3]))
            return (if (truth(dynamicValue(arrayOf<dynamic>(a, x, y)).every(global_Number.isFinite))) jsObject { obj5 ->
                obj5["a"] = a
                obj5["x"] = x
                obj5["y"] = y
            } else null)
        }
        pose = fun(actor: dynamic): dynamic {
            val `out`: dynamic = jsConstruct(global_Map, js("[]"))
            if (true) {
                val iterator8: dynamic = iteratorOf(limbs)
                while (true) {
                    val next9: dynamic = iterator8.next()
                    if (truth(next9.done)) break
                    var part: dynamic = absent
                    part = next9.value
                    val node: dynamic = onPresent(actor.parts) { value10 -> value10[part] }
                    if (truth(jsOr((!truth(node))) { (jsTypeOf(node.getAttribute) !== "function") })) {
                        continue
                    }
                    val parsed: dynamic = decode(node.getAttribute("transform"))
                    if (truth(parsed)) {
                        `out`["set"](part, parsed)
                    }
                }
            }
            return `out`
        }
        blendAngle = fun(start: dynamic, finish: dynamic, t: dynamic): dynamic {
            val diff: dynamic = jsSubtract(jsMod(jsAdd(jsSubtract(finish, start), 540), 360), 180)
            return jsAdd(start, jsMultiply(diff, t))
        }
        safe = fun(actor: dynamic): dynamic {
            return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd((!truth(doc.hidden))) { (!truth(actor.paused)) }) { (!truth(actor.destroyed)) }) { (!truth(actor.reduced)) }) { (!truth(run { val owner16: dynamic = win; onPresent(owner16.matchMedia) { method15 -> onPresent(invokeWith(method15, owner16, arrayOf<dynamic>("(prefers-reduced-motion: reduce)"))) { value17 -> value17.matches } } })) }) { (onPresent(win.HunterEvolution385) { value18 -> value18.budget } !== "eco") }
        }
        if (truth(win.HunterRig389)) {
            return absent
        }
        val Actor: dynamic = win.IgrisCharacter
        if (truth(jsOr((!truth(Actor))) { (jsTypeOf(Actor.prototype.sample) !== "function") })) {
            return absent
        }
        val originalSample: dynamic = Actor.prototype.sample
        val caches: dynamic = jsConstruct(global_WeakMap, js("[]"))
        limbs = jsonValue("[\"head\",\"armLeft\",\"armRight\",\"legLeft\",\"legRight\",\"wingLeft\",\"wingRight\",\"antennaLeft\",\"antennaRight\"]")
        RE = jsRegExp("^\\s*rotate\\(\\s*(-?(?:\\d+(?:\\.\\d*)?|\\.\\d+))(?:\\s+(-?(?:\\d+(?:\\.\\d*)?|\\.\\d+))\\s+(-?(?:\\d+(?:\\.\\d*)?|\\.\\d+)))?\\s*\\)\\s*\$", "")
        val DURATION: dynamic = global_Object.freeze(jsonValue("{\"idle\":240,\"entering\":220,\"exiting\":180,\"celebrate\":165,\"bow\":165,\"wave\":165,\"point\":165,\"think\":165,\"nod\":165,\"look\":165,\"stretch\":165,\"listen\":165,\"walk\":165,\"flip\":140}"))
        var blended: dynamic = 0
        var skipped: dynamic = 0
        var changes: dynamic = 0
        val curve: dynamic = (fun(t: dynamic): dynamic {
            return jsMultiply(jsMultiply(t, t), jsSubtract(3, jsMultiply(2, t)))
        })
        Actor.prototype.sample = (fun(state: dynamic, ms: dynamic): dynamic {
            val receiver21: dynamic = js("this")
            val callArgs22: dynamic = js("arguments")
            var cache: dynamic = caches["get"](receiver21)
            if (truth((!truth(cache)))) {
                cache = jsObject { obj23 ->
                    obj23["state"] = dynamicValue(null)
                    obj23["from"] = jsConstruct(global_Map, js("[]"))
                    obj23["until"] = 0
                }
                caches["set"](receiver21, cache)
            }
            val switched: dynamic = (cache.state !== state)
            if (truth(switched)) {
                cache.from = pose(receiver21)
                cache.state = state
                cache.until = global_Number(jsOr(DURATION[state]) { 150 })
                run {
                    val previous30: dynamic = numeric(changes)
                    changes = jsAdd(previous30, 1)
                }
            }
            val `out`: dynamic = originalSample.apply(receiver21, callArgs22)
            if (truth(jsOr(jsOr((!truth(safe(receiver21)))) { (state === "hidden") }) { (!truth(cache.from.size)) })) {
                run {
                    val previous31: dynamic = numeric(skipped)
                    skipped = jsAdd(previous31, 1)
                }
                return `out`
            }
            val elapsed: dynamic = global_Number(ms)
            if (truth(jsOr(jsOr((!truth(global_Number.isFinite(elapsed)))) { jsLess(elapsed, 0) }) { jsAtLeast(elapsed, cache.until) })) {
                return `out`
            }
            val current: dynamic = pose(receiver21)
            val t: dynamic = curve(global_Math.min(1, jsDivide(elapsed, cache.until)))
            if (true) {
                val iterator32: dynamic = iteratorOf(current)
                while (true) {
                    val next33: dynamic = iterator32.next()
                    if (truth(next33.done)) break
                    var part: dynamic = absent
                    var target: dynamic = absent
                    val unpacked34: dynamic = fromIterable(next33.value)
                    part = unpacked34[0]
                    target = unpacked34[1]
                    val previous: dynamic = cache.from["get"](part)
                    if (truth((!truth(previous)))) {
                        continue
                    }
                    val angle: dynamic = blendAngle(previous.a, target.a, t)
                    if (truth(jsLess(global_Math.abs(jsSubtract(angle, target.a)), 0.005))) {
                        continue
                    }
                    val node: dynamic = receiver21.parts[part]
                    val `val`: dynamic = ("rotate(" + textOf(angle.toFixed(3)) + " " + textOf(target.x) + " " + textOf(target.y) + ")")
                    if (truth((node.getAttribute("transform") !== `val`))) {
                        node.setAttribute("transform", `val`)
                        run {
                            val previous35: dynamic = numeric(blended)
                            blended = jsAdd(previous35, 1)
                        }
                    }
                }
            }
            return `out`
        })
        win.HunterRig389 = global_Object.freeze(jsObject { obj38 ->
            obj38["version"] = "38.9"
            obj38["diagnostics"] = (fun(): dynamic {
                return jsObject { obj39 ->
                    obj39["transitions"] = changes
                    obj39["blendedJointUpdates"] = blended
                    obj39["skipped"] = skipped
                    obj39["note"] = "Conteo interno. No constituye una medición de FPS o batería."
                }
            })
        })
        return absent
    }))(global_window, global_document)
}
