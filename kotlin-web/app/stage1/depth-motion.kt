@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installdepthmotion

import hunter.browser.*

@JsName("Number") private external var global_Number: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("guide11") private external var global_guide11: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("parseInt") private external var global_parseInt: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("interpolateMotion") private external var global_interpolateMotion: dynamic
@JsName("modelGround") private external var global_modelGround: dynamic
@JsName("getComputedStyle") private external var global_getComputedStyle: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installDepthMotion() {
    dynamicValue((fun(root: dynamic): dynamic {
        var rates: dynamic = absent
        var playback: dynamic = absent
        var setRate: dynamic = absent
        var controls: dynamic = absent
        var clamp: dynamic = absent
        var shade: dynamic = absent
        var geometry: dynamic = absent
        var draw: dynamic = absent
        setRate = fun(input5: dynamic): dynamic {
            var `value`: dynamic = input5
            `value` = global_Number(`value`)
            if (truth((!truth(rates.some((fun(r: dynamic): dynamic {
                return (r["value"] === `value`)
            })))))) {
                return false
            }
            playback = `value`
            if (truth((js("typeof state") !== "undefined"))) {
                global_state.demoSpeed = `value`
                global_save()
            }
            if (truth(jsAnd((js("typeof vectorSession") !== "undefined")) { global_vectorSession })) {
                global_vectorSession.speed = `value`
            }
            if (truth(jsAnd((js("typeof guide11") !== "undefined")) { global_guide11 })) {
                global_guide11.demoLast = 0
            }
            global_document.querySelectorAll("[data-playback-speed]").forEach((fun(b: dynamic): dynamic {
                return b.setAttribute("aria-pressed", global_String((global_Number(b.dataset.playbackSpeed) === `value`)))
            }))
            return true
        }
        controls = fun(arg14: dynamic): dynamic {
            var context: dynamic = absent
            context = definedOr(arg14) { "" }
            return ("<fieldset class=\"playback-speeds\"><legend>Velocidad de la demostración" + textOf((if (truth(context)) jsAdd(" · ", context) else "")) + "</legend><div class=\"speed-options\">" + textOf(rates.map((fun(r: dynamic): dynamic {
                return ("<button type=\"button\" data-playback-speed=\"" + textOf(r["value"]) + "\" aria-pressed=\"" + textOf((playback === r["value"])) + "\" onclick=\"MotionPlayback.set(" + textOf(r["value"]) + ")\"><b>" + textOf(r.name) + "</b><small>" + textOf(r.label) + "</small></button>")
            })).join("")) + "</div></fieldset>")
        }
        shade = fun(color: dynamic, factor: dynamic): dynamic {
            val rgb: dynamic = dynamicValue(jsRegExp("^#([a-f\\d]{6})\$", "i")).exec(jsOr(color) { "" })
            val c: dynamic = (if (truth(rgb)) dynamicValue(jsonValue("[0,2,4]")).map((fun(i: dynamic): dynamic {
                return global_parseInt(rgb[1].slice(i, jsAdd(i, 2)), 16)
            })) else dynamicValue(jsOr(global_String(color).match(jsRegExp("[\\d.]+", "g"))) { jsonValue("[87,216,242]") }).slice(0, 3).map(global_Number))
            return jsAdd("#", c.map((fun(v: dynamic): dynamic {
                return global_Math.round(clamp(jsMultiply(v, factor), 0, 255))["toString"](16).padStart(2, "0")
            })).join(""))
        }
        geometry = fun(id: dynamic, phase: dynamic): dynamic {
            val e: dynamic = global_Engine.byId(id)
            val model: dynamic = global_MotionDB[jsOr(onPresent(e) { value24 -> value24.baseId }) { id }]
            if (truth((!truth(model)))) {
                throw global_Error(jsAdd("Sin modelo de movimiento: ", id))
            }
            val f: dynamic = global_interpolateMotion(model, jsMod(jsAdd(jsMod(jsOr(global_Number(phase)) { 0 }, 48), 48), 48))
            return jsObject { obj25 ->
                obj25["e"] = e
                obj25["model"] = model
                obj25["f"] = f
                obj25["ground"] = run { val target27: dynamic = global_Math; invokeWith(target27.max, target27, jsArray { array26 ->
                    appendAll(array26, global_modelGround(id))
                }) }
            }
        }
        draw = fun(canvas: dynamic, id: dynamic, arg30: dynamic, arg31: dynamic): dynamic {
            var phase: dynamic = absent
            phase = definedOr(arg30) { 0 }
            var mirror: dynamic = absent
            mirror = definedOr(arg31) { false }
            var model: dynamic = absent
            var f: dynamic = absent
            var ground: dynamic = absent
            if (truth((!truth(canvas)))) {
                return absent
            }
            val ctx: dynamic = canvas.getContext("2d")
            if (truth((!truth(ctx)))) {
                return absent
            }
            val unpacked32: dynamic = geometry(id, phase)
            model = unpacked32["model"]
            f = unpacked32["f"]
            ground = unpacked32["ground"]
            val p: dynamic = f.p
            val d: dynamic = f.detail
            val style: dynamic = global_getComputedStyle(global_document.documentElement)
            val accent: dynamic = jsOr(style.getPropertyValue("--accent").trim()) { "#57d8f2" }
            val bg: dynamic = jsOr(style.getPropertyValue("--input").trim()) { "#152538" }
            val line: dynamic = (fun(a: dynamic, b: dynamic, w: dynamic, color: dynamic): dynamic {
                ctx.beginPath()
                ctx.strokeStyle = color
                ctx.lineWidth = w
                ctx.lineCap = "round"
                run { val target40: dynamic = ctx; invokeWith(target40.moveTo, target40, jsArray { array39 ->
                    appendAll(array39, a)
                }) }
                run { val target42: dynamic = ctx; invokeWith(target42.lineTo, target42, jsArray { array41 ->
                    appendAll(array41, b)
                }) }
                ctx.stroke()
                return absent
            })
            val sphere: dynamic = (fun(a: dynamic, r: dynamic, color: dynamic): dynamic {
                val g: dynamic = ctx.createRadialGradient(jsSubtract(a[0], jsMultiply(r, 0.3)), jsSubtract(a[1], jsMultiply(r, 0.35)), jsMultiply(r, 0.1), a[0], a[1], r)
                g.addColorStop(0, shade(color, 1.35))
                g.addColorStop(0.45, color)
                g.addColorStop(1, shade(color, 0.36))
                ctx.beginPath()
                ctx.fillStyle = g
                run { val target46: dynamic = ctx; invokeWith(target46.arc, target46, jsArray { array45 ->
                    appendAll(array45, a)
                    array45.push(r)
                    array45.push(0)
                    array45.push(jsMultiply(global_Math.PI, 2))
                }) }
                ctx.fill()
                return absent
            })
            val capsule: dynamic = (fun(a: dynamic, b: dynamic, w: dynamic, color: dynamic): dynamic {
                val dx: dynamic = jsSubtract(b[0], a[0])
                val dy: dynamic = jsSubtract(b[1], a[1])
                val length: dynamic = jsOr(global_Math.hypot(dx, dy)) { 1 }
                val nx: dynamic = jsDivide((-numeric(dy)), length)
                val ny: dynamic = jsDivide(dx, length)
                val mx: dynamic = jsDivide(jsAdd(a[0], b[0]), 2)
                val my: dynamic = jsDivide(jsAdd(a[1], b[1]), 2)
                val g: dynamic = ctx.createLinearGradient(jsSubtract(mx, jsDivide(jsMultiply(nx, w), 2)), jsSubtract(my, jsDivide(jsMultiply(ny, w), 2)), jsAdd(mx, jsDivide(jsMultiply(nx, w), 2)), jsAdd(my, jsDivide(jsMultiply(ny, w), 2)))
                g.addColorStop(0, shade(color, 0.3))
                g.addColorStop(0.32, shade(color, 1.3))
                g.addColorStop(0.6, color)
                g.addColorStop(1, shade(color, 0.4))
                line(a, b, jsAdd(w, 3), "#101923")
                line(a, b, w, g)
                return absent
            })
            ctx.clearRect(0, 0, 720, 800)
            ctx.fillStyle = bg
            ctx.fillRect(0, 0, 720, 800)
            val haze: dynamic = ctx.createRadialGradient(350, 360, 20, 350, 360, 410)
            haze.addColorStop(0, shade(accent, 0.2))
            haze.addColorStop(1, bg)
            ctx.fillStyle = haze
            ctx.fillRect(0, 0, 720, 800)
            ctx.save()
            if (truth(mirror)) {
                ctx.translate(720, 0)
                ctx.scale((-numeric(1)), 1)
            }
            ctx.globalAlpha = 0.18
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration53 = true
                while (true) {
                    if (!firstIteration53) {
                        run {
                            val previous54: dynamic = numeric(i)
                            i = jsAdd(previous54, 1)
                        }
                    }
                    firstIteration53 = false
                    if (!truth(jsLess(i, 8))) break
                    line(arrayOf<dynamic>(jsAdd(40, jsMultiply(i, 90)), jsAdd(ground, 40)), arrayOf<dynamic>(jsAdd(300, jsMultiply(i, 20)), jsSubtract(ground, 35)), 1, "#b2cbda")
                }
            }
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration55 = true
                while (true) {
                    if (!firstIteration55) {
                        run {
                            val previous56: dynamic = numeric(i)
                            i = jsAdd(previous56, 1)
                        }
                    }
                    firstIteration55 = false
                    if (!truth(jsLess(i, 4))) break
                    line(arrayOf<dynamic>(40, jsAdd(ground, jsMultiply(i, 20))), arrayOf<dynamic>(680, jsAdd(ground, jsMultiply(i, 20))), 1, "#b2cbda")
                }
            }
            ctx.globalAlpha = 1
            val sx: dynamic = jsDivide(jsAdd(jsAdd(p[8][0], p[10][0]), p[2][0]), 3)
            ctx.fillStyle = "#03070b"
            ctx.globalAlpha = 0.5
            ctx.beginPath()
            ctx.ellipse(sx, jsAdd(ground, 8), 120, 17, 0, 0, jsMultiply(global_Math.PI, 2))
            ctx.fill()
            ctx.globalAlpha = 1
            if (true) {
                val iterator65: dynamic = iteratorOf(f.equipment)
                while (true) {
                    val next66: dynamic = iterator65.next()
                    if (truth(next66.done)) break
                    var type: dynamic = absent
                    var rect: dynamic = absent
                    val unpacked67: dynamic = fromIterable(next66.value)
                    type = unpacked67[0]
                    rect = unpacked67[1]
                    var x: dynamic = absent
                    var y: dynamic = absent
                    var r: dynamic = absent
                    var b: dynamic = absent
                    val unpacked68: dynamic = fromIterable(rect)
                    x = unpacked68[0]
                    y = unpacked68[1]
                    r = unpacked68[2]
                    b = unpacked68[3]
                    ctx.fillStyle = "#243747"
                    ctx.fillRect(jsAdd(x, 10), jsAdd(y, 8), jsSubtract(r, x), jsSubtract(b, y))
                    ctx.strokeStyle = "#a4bac8"
                    ctx.lineWidth = 4
                    ctx.strokeRect(x, y, jsSubtract(r, x), jsSubtract(b, y))
                    line(arrayOf<dynamic>(x, y), arrayOf<dynamic>(jsAdd(x, 10), jsAdd(y, 8)), 3, "#526b80")
                    line(arrayOf<dynamic>(r, b), arrayOf<dynamic>(jsAdd(r, 10), jsAdd(b, 8)), 3, "#526b80")
                    if (truth((type === "incline"))) {
                        capsule(arrayOf<dynamic>(x, jsSubtract(b, 30)), arrayOf<dynamic>(r, y), 10, "#8aa3b7")
                    }
                }
            }
            if (true) {
                val iterator75: dynamic = iteratorOf(jsonValue("[[1,5],[5,6],[2,9],[9,10]]"))
                while (true) {
                    val next76: dynamic = iterator75.next()
                    if (truth(next76.done)) break
                    var a: dynamic = absent
                    var b: dynamic = absent
                    val unpacked77: dynamic = fromIterable(next76.value)
                    a = unpacked77[0]
                    b = unpacked77[1]
                    capsule(p[a], p[b], 21, shade(accent, 0.65))
                }
            }
            if (truth(d.spine)) {
                ctx.beginPath()
                ctx.strokeStyle = shade(accent, 0.45)
                ctx.lineWidth = 36
                ctx.lineCap = "round"
                run { val target85: dynamic = ctx; invokeWith(target85.moveTo, target85, jsArray { array84 ->
                    appendAll(array84, p[1])
                }) }
                run { val target87: dynamic = ctx; invokeWith(target87.quadraticCurveTo, target87, jsArray { array86 ->
                    array86.push(jsDivide(jsAdd(p[1][0], p[2][0]), 2))
                    array86.push(jsAdd(jsDivide(jsAdd(p[1][1], p[2][1]), 2), d.spine))
                    appendAll(array86, p[2])
                }) }
                ctx.stroke()
                ctx.lineWidth = 20
                ctx.strokeStyle = accent
                ctx.stroke()
            } else {
                capsule(p[1], p[2], 38, accent)
            }
            if (true) {
                val iterator92: dynamic = iteratorOf(jsonValue("[[0,1],[1,3],[3,4],[2,7],[7,8]]"))
                while (true) {
                    val next93: dynamic = iterator92.next()
                    if (truth(next93.done)) break
                    var a: dynamic = absent
                    var b: dynamic = absent
                    val unpacked94: dynamic = fromIterable(next93.value)
                    a = unpacked94[0]
                    b = unpacked94[1]
                    capsule(p[a], p[b], 24, accent)
                }
            }
            if (true) {
                val iterator95: dynamic = iteratorOf(jsonValue("[1,2,3,5,7,9]"))
                while (true) {
                    val next96: dynamic = iterator95.next()
                    if (truth(next96.done)) break
                    var i: dynamic = absent
                    i = next96.value
                    sphere(p[i], 10, "#9badc0")
                }
            }
            sphere(p[0], 32, "#e1eaf2")
            sphere(arrayOf<dynamic>(jsAdd(p[0][0], jsOr(d.face) { 10 }), jsSubtract(p[0][1], 3)), 4, "#172438")
            if (true) {
                val iterator97: dynamic = iteratorOf(jsonValue("[8,10]"))
                while (true) {
                    val next98: dynamic = iterator97.next()
                    if (truth(next98.done)) break
                    var i: dynamic = absent
                    i = next98.value
                    val q: dynamic = nullableOr(nullableOr(d.toeAmount) { d.amount }) { 0 }
                    capsule(p[i], arrayOf<dynamic>(jsAdd(p[i][0], 28), jsSubtract(p[i][1], (if (truth(jsOr((d.feet === "toelift")) { (d.feet === "heeltoe") })) jsMultiply(25, q) else (if (truth((d.feet === "ankle"))) jsMultiply(35, jsSubtract(q, 0.5)) else 0)))), 14, accent)
                }
            }
            if (true) {
                val iterator99: dynamic = iteratorOf(f.loads)
                while (true) {
                    val next100: dynamic = iterator99.next()
                    if (truth(next100.done)) break
                    var x: dynamic = absent
                    var y: dynamic = absent
                    val unpacked101: dynamic = fromIterable(next100.value)
                    x = unpacked101[0]
                    y = unpacked101[1]
                    capsule(arrayOf<dynamic>(jsSubtract(x, 18), y), arrayOf<dynamic>(jsAdd(x, 18), y), 8, "#b8c6d6")
                    capsule(arrayOf<dynamic>(jsSubtract(x, 22), jsSubtract(y, 14)), arrayOf<dynamic>(jsSubtract(x, 22), jsAdd(y, 14)), 16, "#e7c76c")
                    capsule(arrayOf<dynamic>(jsAdd(x, 22), jsSubtract(y, 14)), arrayOf<dynamic>(jsAdd(x, 22), jsAdd(y, 14)), 16, "#e7c76c")
                }
            }
            if (truth(d.towel)) {
                line(d.towel[0], d.towel[1], 5, "#e7c76c")
            }
            if (truth((d.breath !== absent))) {
                ctx.strokeStyle = accent
                ctx.lineWidth = 3
                ctx.beginPath()
                ctx.arc(p[1][0], jsAdd(p[1][1], 50), jsAdd(35, jsMultiply(15, d.breath)), 0, jsMultiply(global_Math.PI, 2))
                ctx.stroke()
            }
            ctx.restore()
            if (truth(d.detail)) {
                val q: dynamic = jsOr(d.amount) { 0 }
                ctx.fillStyle = "#152332"
                ctx.fillRect(410, 45, 280, 170)
                ctx.fillStyle = "#eff6ff"
                ctx.font = "18px sans-serif"
                ctx.fillText("Detalle de mano", 428, 74)
                capsule(jsonValue("[440,162]"), jsonValue("[525,150]"), 16, accent)
                if (truth((d.detail === "forearmmob"))) {
                    ctx.beginPath()
                    ctx.fillStyle = (if (truth(jsMore(q, 0.5))) "#e9c49f" else accent)
                    ctx.ellipse(560, 150, 30, global_Math.max(3, jsMultiply(18, global_Math.abs(global_Math.cos(jsMultiply(q, global_Math.PI))))), 0, 0, jsMultiply(global_Math.PI, 2))
                    ctx.fill()
                } else {
                    if (truth((d.detail === "fingers"))) {
                        if (true) {
                            var j: dynamic = absent
                            j = 0
                            var firstIteration114 = true
                            while (true) {
                                if (!firstIteration114) {
                                    run {
                                        val previous115: dynamic = numeric(j)
                                        j = jsAdd(previous115, 1)
                                    }
                                }
                                firstIteration114 = false
                                if (!truth(jsLess(j, 5))) break
                                capsule(jsonValue("[525,150]"), arrayOf<dynamic>(jsAdd(550, jsMultiply(35, q)), jsAdd(150, jsMultiply(jsMultiply(jsSubtract(j, 2), 15), q))), 7, accent)
                            }
                        }
                    } else {
                        capsule(jsonValue("[525,150]"), arrayOf<dynamic>(590, jsAdd(150, jsMultiply(50, jsSubtract(q, 0.5)))), 14, accent)
                    }
                }
            }
            if (truth(d.grip)) {
                ctx.fillStyle = "#edf4ff"
                ctx.font = "19px sans-serif"
                ctx.fillText(d.grip, 30, 30)
            }
            canvas.dataset.renderer = "2.5d"
            return model
        }
        rates = jsonValue("[{\"value\":0.5,\"name\":\"Lenta\",\"label\":\"0,5×\"},{\"value\":1,\"name\":\"Normal\",\"label\":\"1×\"},{\"value\":1.5,\"name\":\"Rápida\",\"label\":\"1,5×\"}]")
        playback = 1
        root.MotionPlayback = jsObject { obj17 ->
            obj17["rates"] = rates
            accessor(obj17, "rate", "get", fun(): dynamic {
                return playback
            })
            obj17["set"] = setRate
            obj17["controls"] = controls
        }
        clamp = (fun(v: dynamic, a: dynamic, b: dynamic): dynamic {
            return global_Math.max(a, global_Math.min(b, v))
        })
        root.DepthMotion = jsObject { obj124 ->
            obj124["draw"] = draw
            obj124["geometry"] = geometry
            obj124["shade"] = shade
        }
        return absent
    }))(global_window)
}
