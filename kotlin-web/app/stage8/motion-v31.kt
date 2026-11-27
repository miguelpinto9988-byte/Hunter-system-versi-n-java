@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installmotionv31

import hunter.browser.*

@JsName("JSON") private external var global_JSON: dynamic
@JsName("Rig2D23") private external var global_Rig2D23: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Motion30") private external var global_Motion30: dynamic
@JsName("Motion2D18") private external var global_Motion2D18: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installMotionV31() {
    dynamicValue((fun(root: dynamic): dynamic {
        var cp: dynamic = absent
        var mix: dynamic = absent
        var wave: dynamic = absent
        var add: dynamic = absent
        var sub: dynamic = absent
        var unit: dynamic = absent
        var ik: dynamic = absent
        var head: dynamic = absent
        var arm: dynamic = absent
        var feet: dynamic = absent
        var blank: dynamic = absent
        var kindOf: dynamic = absent
        var squatKinds: dynamic = absent
        var squatArms: dynamic = absent
        var wallPush: dynamic = absent
        var calfStretch: dynamic = absent
        var towel: dynamic = absent
        var openBook: dynamic = absent
        var crossStep: dynamic = absent
        var standingHam: dynamic = absent
        var kneelingCrunch: dynamic = absent
        var resolve: dynamic = absent
        var configureModel: dynamic = absent
        var extras: dynamic = absent
        var panel: dynamic = absent
        var inset: dynamic = absent
        var wrapped: dynamic = absent
        var reflowInset: dynamic = absent
        head = fun(f: dynamic): dynamic {
            f.p[0] = add(f.p[1], unit(sub(f.p[1], f.p[2])), 60)
            return absent
        }
        arm = fun(f: dynamic, end: dynamic, arg12: dynamic, arg13: dynamic, arg14: dynamic): dynamic {
            var other: dynamic = absent
            other = definedOr(arg12) { null }
            var length: dynamic = absent
            length = definedOr(arg13) { 95 }
            var bend: dynamic = absent
            bend = definedOr(arg14) { (-numeric(1)) }
            if (true) {
                val iterator15: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, 4, jsonValue("[0,0]"), end), arrayOf<dynamic>(5, 6, jsonValue("[-13,-13]"), jsOr(other) { add(end, jsonValue("[-13,-13]")) })))
                while (true) {
                    val next16: dynamic = iterator15.next()
                    if (truth(next16.done)) break
                    var el: dynamic = absent
                    var ha: dynamic = absent
                    var offset: dynamic = absent
                    var tip: dynamic = absent
                    val unpacked17: dynamic = fromIterable(next16.value)
                    el = unpacked17[0]
                    ha = unpacked17[1]
                    offset = unpacked17[2]
                    tip = unpacked17[3]
                    val origin: dynamic = add(f.p[1], offset)
                    f.p[ha] = tip
                    f.p[el] = ik(origin, tip, length, bend)
                }
            }
            return absent
        }
        feet = fun(f: dynamic, arg24: dynamic, arg25: dynamic): dynamic {
            var a: dynamic = absent
            a = definedOr(arg24) { jsonValue("[1,0]") }
            var b: dynamic = absent
            b = definedOr(arg25) { a }
            f.detail.feet30 = jsObject { obj28 ->
                obj28["8"] = a
                obj28["10"] = b
            }
            return absent
        }
        blank = fun(): dynamic {
            return jsObject { obj31 ->
                obj31["p"] = cp(global_Rig2D23.standing().p)
                obj31["loads"] = js("[]")
                obj31["equipment"] = js("[]")
                obj31["detail"] = jsonValue("{\"floor23\":715}")
            }
        }
        squatArms = fun(f: dynamic, p: dynamic, kind: dynamic, a: dynamic): dynamic {
            val s: dynamic = f.p[1]
            if (truth(p.back)) {
                if (true) {
                    val iterator34: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-13]]"))
                    while (true) {
                        val next35: dynamic = iterator34.next()
                        if (truth(next35.done)) break
                        var el: dynamic = absent
                        var ha: dynamic = absent
                        var o: dynamic = absent
                        val unpacked36: dynamic = fromIterable(next35.value)
                        el = unpacked36[0]
                        ha = unpacked36[1]
                        o = unpacked36[2]
                        f.p[el] = arrayOf<dynamic>(jsAdd(jsSubtract(s[0], 55), o), jsAdd(jsAdd(s[1], 70), o))
                        f.p[ha] = arrayOf<dynamic>(jsAdd(jsAdd(s[0], 8), o), jsAdd(jsAdd(s[1], 12), o))
                    }
                }
                f.loads = js("[]")
                f.detail.barY30 = jsAdd(s[1], 12)
                f.detail.barCenter31 = arrayOf<dynamic>(jsAdd(s[0], 8), jsAdd(s[1], 12))
            } else {
                if (truth(jsOr(jsOr(jsOr(p.front) { p.rack }) { (kind === "goblet") }) { (kind === "doublefront") })) {
                    arm(f, add(s, jsonValue("[48,42]")), null, 95, (-numeric(1)))
                    f.loads = (if (truth((kind === "goblet"))) arrayOf<dynamic>(mix(f.p[4], f.p[6], 0.5)) else (if (truth((p.gear === "barbell"))) js("[]") else arrayOf<dynamic>(cp(f.p[4]), cp(f.p[6]))))
                } else {
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(p.db) { p.offset }) { p.belt }) { p.hack }) { p.pendulum }) { p.vlever })) {
                        arm(f, add(s, jsonValue("[15,185]")), null, 95, (-numeric(1)))
                        if (truth(jsOr(p.db) { p.offset })) {
                            f.loads = (if (truth(p.offset)) arrayOf<dynamic>(cp(f.p[4])) else arrayOf<dynamic>(cp(f.p[4]), cp(f.p[6])))
                        }
                    } else {
                        val th: dynamic = jsAdd(0.08, jsMultiply(1.42, a))
                        if (true) {
                            val iterator51: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-13]]"))
                            while (true) {
                                val next52: dynamic = iterator51.next()
                                if (truth(next52.done)) break
                                var el: dynamic = absent
                                var ha: dynamic = absent
                                var o: dynamic = absent
                                val unpacked53: dynamic = fromIterable(next52.value)
                                el = unpacked53[0]
                                ha = unpacked53[1]
                                o = unpacked53[2]
                                f.p[el] = arrayOf<dynamic>(jsAdd(jsAdd(s[0], jsMultiply(95, global_Math.sin(th))), o), jsAdd(jsAdd(s[1], jsMultiply(95, global_Math.cos(th))), o))
                                f.p[ha] = arrayOf<dynamic>(jsAdd(jsAdd(s[0], jsMultiply(185, global_Math.sin(th))), o), jsAdd(jsAdd(s[1], jsMultiply(185, global_Math.cos(th))), o))
                            }
                        }
                        f.loads = js("[]")
                    }
                }
            }
            f.detail.squatArms31 = true
            f.detail.squatAmount31 = a
            return absent
        }
        wallPush = fun(a: dynamic, arg66: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg66) { js("({})") }
            val f: dynamic = blank()
            val ankle: dynamic = jsonValue("[345,700]")
            val s: dynamic = arrayOf<dynamic>(jsAdd(432, jsMultiply(65, a)), 335)
            f.p[1] = s
            f.p[2] = mix(s, ankle, 0.39)
            head(f)
            f.p[8] = ankle
            f.p[10] = add(ankle, jsonValue("[-13,-13]"))
            f.p[7] = mix(f.p[2], f.p[8], 0.5)
            f.p[9] = mix(add(f.p[2], jsonValue("[-13,-13]")), f.p[10], 0.5)
            arm(f, jsonValue("[548,350]"), jsonValue("[548,337]"), 100, (-numeric(1)))
            f.equipment = jsonValue("[[\"wall\",[559,220,571,720]]]")
            feet(f)
            return f
        }
        calfStretch = fun(): dynamic {
            val f: dynamic = blank()
            f.p[2] = jsonValue("[350,490]")
            f.p[1] = jsonValue("[395,330]")
            head(f)
            f.p[8] = jsonValue("[465,700]")
            f.p[10] = jsonValue("[230,687]")
            f.p[7] = ik(f.p[2], f.p[8], 140, 1)
            f.p[9] = mix(add(f.p[2], jsonValue("[-13,-13]")), f.p[10], 0.5)
            arm(f, jsonValue("[548,365]"), jsonValue("[548,352]"), 100, (-numeric(1)))
            f.equipment = jsonValue("[[\"wall\",[559,220,571,720]]]")
            feet(f)
            return f
        }
        towel = fun(): dynamic {
            val f: dynamic = blank()
            f.p = jsonValue("[[215,286],[225,345],[252,515],[276,435],[342,421],[263,422],[329,408],[414,515],[570,515],[401,502],[557,502]]")
            f.detail.floor23 = 538
            f.detail.towel31 = true
            f.detail.towel23 = false
            feet(f, jsonValue("[0,-1]"))
            return f
        }
        openBook = fun(a: dynamic): dynamic {
            val f: dynamic = blank()
            f.p = jsonValue("[[175,600],[235,600],[420,615],[238,688],[241,776],[245,688],[245,776],[510,680],[598,653],[497,667],[585,640]]")
            val th: dynamic = jsMultiply(global_Math.PI, a)
            f.p[5] = arrayOf<dynamic>(jsAdd(245, jsMultiply(88, global_Math.sin(th))), jsAdd(600, jsMultiply(88, global_Math.cos(th))))
            f.p[6] = arrayOf<dynamic>(jsAdd(245, jsMultiply(176, global_Math.sin(th))), jsAdd(600, jsMultiply(176, global_Math.cos(th))))
            f.detail.openBook31 = a
            f.detail.floor23 = 802
            feet(f, jsonValue("[1,0]"))
            return f
        }
        crossStep = fun(a: dynamic): dynamic {
            val f: dynamic = blank()
            val rise: dynamic = jsMultiply(130, a)
            val x: dynamic = jsAdd(350, jsMultiply(64, a))
            f.p[2] = arrayOf<dynamic>(x, jsSubtract(480, rise))
            f.p[1] = arrayOf<dynamic>(x, jsSubtract(310, rise))
            head(f)
            f.p[8] = jsonValue("[435,602]")
            f.p[7] = arrayOf<dynamic>(jsAdd(365, jsMultiply(60, a)), jsSubtract(535, jsMultiply(55, a)))
            f.p[10] = arrayOf<dynamic>(jsAdd(300, jsMultiply(80, a)), jsSubtract(700, jsMultiply(98, a)))
            f.p[9] = arrayOf<dynamic>(jsAdd(335, jsMultiply(32, a)), jsSubtract(580, jsMultiply(80, a)))
            f.p[3] = arrayOf<dynamic>(jsSubtract(x, 56), jsSubtract(405, rise))
            f.p[4] = arrayOf<dynamic>(jsSubtract(x, 56), jsSubtract(493, rise))
            f.p[5] = arrayOf<dynamic>(jsAdd(x, 56), jsSubtract(405, rise))
            f.p[6] = arrayOf<dynamic>(jsAdd(x, 56), jsSubtract(493, rise))
            f.equipment = jsonValue("[[\"step\",[390,616,510,715]]]")
            f.detail.cross31 = a
            feet(f, jsonValue("[-0.08,1]"), jsonValue("[0.08,1]"))
            return f
        }
        standingHam = fun(a: dynamic): dynamic {
            val f: dynamic = blank()
            val k: dynamic = jsonValue("[337,567]")
            val th: dynamic = jsAdd(jsDivide(global_Math.PI, 2), jsMultiply(1.4, a))
            f.p[10] = arrayOf<dynamic>(jsAdd(k[0], jsMultiply(140, global_Math.cos(th))), jsAdd(k[1], jsMultiply(140, global_Math.sin(th))))
            f.p[9] = k
            f.p[8] = jsonValue("[357,700]")
            f.p[7] = jsonValue("[357,563]")
            head(f)
            f.p[4] = jsonValue("[535,362]")
            f.p[3] = ik(f.p[1], f.p[4], 105, (-numeric(1)))
            f.p[5] = jsonValue("[337,370]")
            f.p[6] = jsonValue("[337,465]")
            f.equipment = jsonValue("[[\"wall\",[548,260,560,715]]]")
            f.detail.roller30 = add(f.p[10], jsonValue("[-12,-8]"))
            f.detail.machine30 = "ham-standing"
            feet(f, jsonValue("[1,0]"), arrayOf<dynamic>(global_Math.sin(th), (-numeric(global_Math.cos(th)))))
            return f
        }
        kneelingCrunch = fun(a: dynamic): dynamic {
            val f: dynamic = blank()
            f.p[2] = jsonValue("[340,550]")
            f.p[1] = arrayOf<dynamic>(jsAdd(323, jsMultiply(68, a)), jsAdd(388, jsMultiply(45, a)))
            head(f)
            f.p[7] = jsonValue("[405,700]")
            f.p[8] = jsonValue("[245,700]")
            f.p[9] = jsonValue("[392,687]")
            f.p[10] = jsonValue("[232,687]")
            arm(f, add(f.p[1], jsonValue("[28,-24]")), null, 95, (-numeric(1)))
            f.detail.spineCurve23 = jsMultiply(18, a)
            feet(f, jsonValue("[1,0]"))
            return f
        }
        resolve = fun(e: dynamic, t: dynamic, raw: dynamic, previous: dynamic): dynamic {
            val old: dynamic = previous(e, t, raw)
            val p: dynamic = jsOr(jsOr(e.param30) { e.param23 }) { js("({})") }
            val kind: dynamic = kindOf(e, raw)
            val a: dynamic = wave(t)
            var f: dynamic = old.frame
            var view: dynamic = old.view
            var posture: dynamic = old.posture
            var changed: dynamic = false
            if (truth(squatKinds.has(kind))) {
                if (truth((p.gear === "smith"))) {
                    f.p[1][0] = 355
                    head(f)
                    f.detail.smith31 = true
                }
                squatArms(f, p, kind, (if (truth(p.pulses)) jsAdd(0.7, jsMultiply(0.3, wave(jsMultiply(t, 2)))) else a))
                changed = true
            }
            if (truth(jsAnd(dynamicValue(jsonValue("[\"single-squat19\",\"single-squat-support19\",\"squatjump\"]")).includes(kind)) { (view === "side") })) {
                squatArms(f, p, kind, a)
                changed = true
            }
            if (truth(jsOr((kind === "wall")) { (kind === "wallplank") })) {
                f = wallPush((if (truth((kind === "wallplank"))) 0 else a), p)
                view = "side"
                posture = "upright"
                changed = true
            }
            if (truth((kind === "calfstretch"))) {
                f = calfStretch()
                view = "side"
                posture = "upright"
                changed = true
            }
            if (truth((kind === "towelrow"))) {
                f = towel()
                view = "side"
                posture = "upright"
                changed = true
            }
            if (truth((kind === "thoracic"))) {
                f = openBook(a)
                view = "lying"
                posture = "side-lying"
                changed = true
            }
            if (truth(jsAnd((kind === "step")) { p.cross })) {
                f = crossStep(a)
                view = "front"
                posture = "upright"
                changed = true
            }
            if (truth(jsAnd((kind === "hamcurl")) { p.standing })) {
                f = standingHam(a)
                view = "side"
                posture = "upright"
                changed = true
            }
            if (truth(dynamicValue(jsonValue("[\"bridge\",\"dbbridge\",\"singlebridge\"]")).includes(kind))) {
                feet(f, jsonValue("[-1,0]"))
                if (truth(jsOr(jsOr((kind === "dbbridge")) { p.db }) { onPresent(e.requirements) { value191 -> value191.includes("db") } })) {
                    arm(f, add(f.p[2], jsonValue("[-7,-21]")), null, 100, (-numeric(1)))
                    f.loads = arrayOf<dynamic>(add(f.p[2], jsonValue("[-7,-21]")))
                    f.detail.pelvisLoad31 = true
                }
                changed = true
            }
            if (truth((kind === "legpress"))) {
                f.detail.plate23 = add(f.p[8], jsonValue("[8,0]"))
                if (truth(p.single)) {
                    f.p[9] = jsonValue("[365,641]")
                    f.p[10] = jsonValue("[425,700]")
                    feet(f, jsonValue("[0,-1]"), jsonValue("[1,0]"))
                }
                f.detail.legPress31 = true
                changed = true
            }
            if (truth(jsOr((kind === "back-extension")) { jsAnd((kind === "crunch")) { p.seated } })) {
                arm(f, add(f.p[1], jsonValue("[38,64]")), null, 95, (-numeric(1)))
                changed = true
            }
            if (truth(jsAnd((kind === "crunch")) { p.kneeling })) {
                f = kneelingCrunch(a)
                view = "side"
                posture = "upright"
                changed = true
            }
            if (truth((kind === "machine-press"))) {
                if (truth(p.single)) {
                    f.p[5] = jsonValue("[267,414]")
                    f.p[6] = jsonValue("[295,489]")
                }
                f.detail.press31 = true
                changed = true
            }
            if (truth(jsAnd((kind === "side-plank")) { p.thread })) {
                f.detail.thread31 = a
                changed = true
            }
            if (truth(jsAnd((kind === "plank")) { jsOr(p.toe) { p.jack } })) {
                f.detail.toeSide31 = a
                changed = true
            }
            if (truth(e.gymOnly23)) {
                f.detail.gear23 = jsOr(p.gear) { "machine" }
                f.detail.recipe23 = kind
                f.detail.param23 = p
                if (truth((!truth(f.equipment.some((fun(x: dynamic): dynamic {
                    return (x[0] === "bounds23")
                })))))) {
                    f.equipment.push(jsonValue("[\"bounds23\",[95,95,690,735]]"))
                }
            }
            f.detail.view21 = view
            f.detail.revision31 = 31
            f.detail.cycle31 = t
            f.detail.amount31 = a
            if (truth(changed)) {
                f.detail.corrected31 = kind
            }
            return jsObject { obj230 ->
                obj230["frame"] = f
                obj230["view"] = view
                obj230["posture"] = posture
                obj230["corrected"] = jsOr(old.corrected) { changed }
            }
        }
        configureModel = fun(e: dynamic, m: dynamic): dynamic {
            val kind: dynamic = kindOf(e, m)
            val p: dynamic = jsOr(jsOr(e.param30) { e.param23 }) { js("({})") }
            val f: dynamic = m.frames[0]
            m.motionRevision31 = 31
            if (truth(jsOr(jsOr((kind === "wall")) { (kind === "wallplank") }) { (kind === "calfstretch") })) {
                m.facing23 = "right"
            }
            if (truth((kind === "thoracic"))) {
                m.view21.inset = "openbook31"
            }
            if (truth((kind === "towelrow"))) {
                m.view21.inset = "towel31"
            }
            if (truth(jsAnd((kind === "plank")) { jsOr(p.toe) { p.jack } })) {
                m.view21.inset = "toes31"
            }
            if (truth(jsAnd((kind === "step")) { p.cross })) {
                m.view21.inset = "cross31"
            }
            if (truth(jsAnd((kind === "side-plank")) { p.thread })) {
                m.view21.inset = "thread31"
            }
            if (truth((kind === "machine-press"))) {
                m.view21.inset = "press31"
            }
            if (truth(jsAnd((kind === "crunch")) { p.kneeling })) {
                m.view21.inset = "crunch31"
            }
            if (truth(squatKinds.has(kind))) {
                m.view21.inset = "squat31"
            }
            if (truth(jsOr((kind === "dbbridge")) { f.detail.pelvisLoad31 })) {
                m.view21.inset = "pelvis31"
            }
            if (truth(jsAnd((kind === "hamcurl")) { p.standing })) {
                m.view21.inset = "ham31"
            }
            if (truth((kind === "calfstretch"))) {
                m.view21.inset = "calf31"
            }
            return absent
        }
        extras = fun(f: dynamic, accent: dynamic): dynamic {
            val d: dynamic = f.detail
            val p: dynamic = f.p
            var s: dynamic = ""
            if (truth(d.towel31)) {
                val q: dynamic = arrayOf<dynamic>(p[8][0], jsSubtract(p[8][1], 16))
                run {
                    s = jsAdd(s, ("<g data-apparatus31=\"towel\"><path d=\"M" + textOf(p[4]) + "L" + textOf(q) + "L" + textOf(p[6]) + "\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"6\" stroke-linecap=\"round\"/><path d=\"M" + textOf(q[0]) + " " + textOf(jsSubtract(q[1], 10)) + "q17 12 0 23\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"5\"/></g>"))
                }
            }
            if (truth(d.press31)) {
                run {
                    s = jsAdd(s, ("<path data-apparatus31=\"backrest\" d=\"M" + textOf(jsSubtract(p[1][0], 37)) + " " + textOf(jsSubtract(p[1][1], 20)) + "L" + textOf(jsSubtract(p[2][0], 30)) + " " + textOf(jsSubtract(p[2][1], 10)) + "\" stroke=\"#527585\" stroke-width=\"21\" stroke-linecap=\"round\"/>"))
                }
            }
            return s
        }
        panel = fun(title: dynamic, note: dynamic, art: dynamic, accent: dynamic, kind: dynamic): dynamic {
            return ("<g data-detail31=\"" + textOf(kind) + "\" data-view=\"side-inset\"><rect x=\"58\" y=\"620\" width=\"604\" height=\"113\" rx=\"14\" fill=\"#102431\" stroke=\"#47606e\"/><text x=\"74\" y=\"642\" font-size=\"13\" fill=\"" + textOf(accent) + "\" font-family=\"sans-serif\">" + textOf(title) + "</text>" + textOf(art) + "<text x=\"74\" y=\"720\" font-size=\"12\" fill=\"#b0c6d1\" font-family=\"sans-serif\">" + textOf(note) + "</text></g>")
        }
        inset = fun(kind: dynamic, f: dynamic, phase: dynamic, accent: dynamic): dynamic {
            var title: dynamic = absent
            var note: dynamic = absent
            var art: dynamic = absent
            val d: dynamic = f.detail
            val a: dynamic = nullableOr(d.amount31) { wave(jsDivide(phase, 48)) }
            val ink: dynamic = "#dceaf0"
            val red: dynamic = "#fb927d"
            if (truth((kind === "towel31"))) {
                title = "TOALLA · TENSIÓN SIN MOVER EL TRONCO"
                note = "Tira hacia el torso y respira; los pies y la espalda quedan estables."
                val breath: dynamic = wave(jsDivide(phase, 48))
                val u: dynamic = jsMultiply(6, breath)
                art = ("<path d=\"M96 682H182L202 673V700L182 682\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"5\"/><path d=\"M" + textOf(jsSubtract(123, u)) + " 665L" + textOf(jsSubtract(108, u)) + " 675l15 7M" + textOf(jsAdd(188, u)) + " 665l15 10-15 7\" fill=\"none\" stroke=\"" + textOf(red) + "\" stroke-width=\"4\"/><circle cx=\"269\" cy=\"680\" r=\"" + textOf(jsAdd(10, jsMultiply(8, breath))) + "\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/><text x=\"298\" y=\"686\" fill=\"" + textOf(ink) + "\" font-size=\"17\">RESPIRA · SOSTÉN</text>")
            } else {
                if (truth(jsOr((kind === "openbook31")) { (kind === "thread31") })) {
                    title = (if (truth((kind === "openbook31"))) "VISTA SUPERIOR · ABRE EL PECHO" else "VISTA SUPERIOR · PASA EL BRAZO Y ABRE")
                    note = (if (truth((kind === "openbook31"))) "Las rodillas siguen juntas; gira el pecho sin forzar el cuello." else "Sostén la pelvis; el brazo libre pasa bajo el torso y vuelve a abrir.")
                    val u: dynamic = (if (truth((kind === "openbook31"))) d.openBook31 else d.thread31)
                    val th: dynamic = jsMultiply(global_Math.PI, nullableOr(u) { a })
                    art = ("<path d=\"M155 657V698\" stroke=\"" + textOf(ink) + "\" stroke-width=\"20\" stroke-linecap=\"round\"/><circle cx=\"155\" cy=\"648\" r=\"9\" fill=\"#edc5ad\"/><path d=\"M155 665L211 665M155 666L" + textOf(jsAdd(155, jsMultiply(56, global_Math.cos(th)))) + " " + textOf(jsSubtract(666, jsMultiply(20, global_Math.sin(th)))) + "\" stroke=\"" + textOf(red) + "\" stroke-width=\"8\" stroke-linecap=\"round\"/><path d=\"M112 653Q155 625 207 653\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\" stroke-dasharray=\"5 4\"/><path d=\"M142 702L137 717M168 702L173 717\" stroke=\"" + textOf(ink) + "\" stroke-width=\"8\"/><text x=\"258\" y=\"682\" font-size=\"17\" fill=\"" + textOf(ink) + "\">PELVIS ESTABLE</text>")
                } else {
                    if (truth((kind === "toes31"))) {
                        title = "PIERNAS · PUNTA LATERAL SIN MOVER LAS MANOS"
                        note = "La punta sale al lado y vuelve; mantén la cadera a la misma altura."
                        val u: dynamic = nullableOr(d.toeSide31) { a }
                        art = ("<circle cx=\"94\" cy=\"665\" r=\"9\" fill=\"#edc5ad\"/><path d=\"M107 672L164 685L220 697M116 674V701H130\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\"/><path d=\"M164 685L" + textOf(jsAdd(197, jsMultiply(25, u))) + " " + textOf(jsSubtract(692, jsMultiply(8, u))) + "L" + textOf(jsAdd(220, jsMultiply(31, u))) + " 699\" fill=\"none\" stroke=\"" + textOf(red) + "\" stroke-width=\"7\"/><path d=\"M260 656V692M290 656L" + textOf(jsAdd(290, jsMultiply(38, u))) + " 692\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"5\"/><text x=\"353\" y=\"684\" font-size=\"17\" fill=\"" + textOf(ink) + "\">IDA Y VUELTA</text>")
                    } else {
                        if (truth((kind === "cross31"))) {
                            title = "APOYO CRUZADO · VISTA DE LAS HUELLAS"
                            note = "Apoya todo el pie; sube con esa pierna y baja sin impulso."
                            val u: dynamic = nullableOr(d.cross31) { a }
                            art = ("<rect x=\"92\" y=\"651\" width=\"154\" height=\"49\" fill=\"#173746\" stroke=\"#648491\"/><ellipse cx=\"203\" cy=\"676\" rx=\"10\" ry=\"19\" fill=\"" + textOf(red) + "\"/><ellipse cx=\"" + textOf(jsAdd(129, jsMultiply(54, u))) + "\" cy=\"" + textOf(jsSubtract(710, jsMultiply(35, u))) + "\" rx=\"10\" ry=\"19\" fill=\"" + textOf(ink) + "\"/><path d=\"M134 706Q147 680 188 676\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\" stroke-dasharray=\"4 3\"/><text x=\"286\" y=\"682\" font-size=\"17\" fill=\"" + textOf(ink) + "\">PIE ENTERO EN LA PLATAFORMA</text>")
                        } else {
                            if (truth((kind === "squat31"))) {
                                title = "DE LADO · RODILLAS Y TALONES"
                                note = "Baja caderas y rodillas juntas; conserva el talón y el apoyo de la carga."
                                val u: dynamic = nullableOr(d.squatAmount31) { a }
                                art = ("<path d=\"M99 704H230\" stroke=\"#638592\" stroke-width=\"3\"/><path d=\"M136 " + textOf(jsAdd(654, jsMultiply(14, u))) + "L" + textOf(jsSubtract(160, jsMultiply(23, u))) + " " + textOf(jsAdd(676, jsMultiply(9, u))) + "L186 679L180 700H200\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"8\" stroke-linecap=\"round\"/><path d=\"M213 660V693m-5-6 5 6 5-6\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\" fill=\"none\"/><text x=\"277\" y=\"683\" font-size=\"17\" fill=\"" + textOf(ink) + "\">TALÓN APOYADO</text>")
                            } else {
                                if (truth((kind === "pelvis31"))) {
                                    title = "PUENTE · LA CARGA SE SUJETA SOBRE LA PELVIS"
                                    note = "Sube la pelvis con el abdomen activo; la cabeza permanece apoyada."
                                    art = ("<circle cx=\"100\" cy=\"692\" r=\"8\" fill=\"#edc5ad\"/><path d=\"M110 692L165 " + textOf(jsSubtract(695, jsMultiply(24, a))) + "L191 669L211 701H196\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\"/><path d=\"M155 " + textOf(jsSubtract(686, jsMultiply(24, a))) + "H175\" stroke=\"" + textOf(red) + "\" stroke-width=\"11\"/><path d=\"M238 698V664m-6 8 6-8 6 8\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\" fill=\"none\"/>")
                                } else {
                                    if (truth((kind === "ham31"))) {
                                        title = "RODILLA · EL MUSLO PERMANECE ESTABLE"
                                        note = "Acerca el talón hacia atrás; no balancees la pelvis ni arquees la espalda."
                                        val th: dynamic = jsAdd(jsDivide(global_Math.PI, 2), jsMultiply(1.4, a))
                                        art = ("<path d=\"M160 654V678L" + textOf(jsAdd(160, jsMultiply(32, global_Math.cos(th)))) + " " + textOf(jsAdd(678, jsMultiply(32, global_Math.sin(th)))) + "\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\" stroke-linecap=\"round\"/><circle cx=\"160\" cy=\"678\" r=\"5\" fill=\"" + textOf(red) + "\"/><path d=\"M197 686Q187 667 170 653\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/>")
                                    } else {
                                        if (truth((kind === "calf31"))) {
                                            title = "ESTIRAMIENTO · TALÓN TRASERO APOYADO"
                                            note = "Sostén sin rebotes; la pierna posterior queda larga y las manos al frente."
                                            art = ("<path d=\"M99 705H232M195 650V700\" stroke=\"#648491\" stroke-width=\"3\"/><path d=\"M153 652L149 674L113 701H130M149 674L176 681L176 701H194M156 655L190 661\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"7\"/><circle cx=\"155\" cy=\"642\" r=\"8\" fill=\"#edc5ad\"/><circle cx=\"118\" cy=\"701\" r=\"" + textOf(jsAdd(8, jsMultiply(4, a))) + "\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/>")
                                        } else {
                                            if (truth((kind === "press31"))) {
                                                title = "PRESS · ASA A LA ALTURA DEL PECHO"
                                                note = "Empuja y regresa sin despegar el tronco; evita encoger los hombros."
                                                art = ("<path d=\"M116 652V698H148V718M102 652V700\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\"/><circle cx=\"116\" cy=\"642\" r=\"9\" fill=\"#edc5ad\"/><path d=\"M118 659L" + textOf(jsAdd(127, jsMultiply(18, a))) + " " + textOf(jsSubtract(682, jsMultiply(18, a))) + "L" + textOf(jsAdd(139, jsMultiply(33, a))) + " 663\" fill=\"none\" stroke=\"" + textOf(red) + "\" stroke-width=\"7\"/><path d=\"M192 664h37m-6-5 6 5-6 5\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/>")
                                            } else {
                                                if (truth((kind === "crunch31"))) {
                                                    title = "ABDOMEN · ACERCA COSTILLAS Y PELVIS"
                                                    note = "La pelvis queda estable; la cuerda acompaña la flexión, sin tirar del cuello."
                                                    art = ("<path d=\"M" + textOf(jsAdd(128, jsMultiply(21, a))) + " " + textOf(jsAdd(650, jsMultiply(14, a))) + "Q" + textOf(jsAdd(129, jsMultiply(25, a))) + " 674 137 685L150 705H112\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\"/><circle cx=\"" + textOf(jsAdd(122, jsMultiply(21, a))) + "\" cy=\"" + textOf(jsAdd(639, jsMultiply(14, a))) + "\" r=\"8\" fill=\"#edc5ad\"/><path d=\"M180 647Q205 667 183 684m-1-7 1 7 7-3\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/>")
                                                } else {
                                                    return null
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            return panel(title, note, art, accent, kind)
        }
        wrapped = fun(text: dynamic, arg267: dynamic, arg268: dynamic): dynamic {
            var max: dynamic = absent
            max = definedOr(arg267) { 44 }
            var count: dynamic = absent
            count = definedOr(arg268) { 3 }
            val words: dynamic = text.split(jsRegExp("\\s+", ""))
            val rows: dynamic = jsonValue("[\"\"]")
            if (true) {
                val iterator269: dynamic = iteratorOf(words)
                while (true) {
                    val next270: dynamic = iterator269.next()
                    if (truth(next270.done)) break
                    var word: dynamic = absent
                    word = next270.value
                    val i: dynamic = jsSubtract(rows.length, 1)
                    if (truth(jsAnd(rows[i]) { jsMore(dynamicValue(jsAdd(jsAdd(rows[i], " "), word)).length, max) })) {
                        rows.push(word)
                    } else {
                        run {
                            val target271: dynamic = rows
                            val key272: dynamic = i
                            target271[key272] = jsAdd(target271[key272], jsAdd((if (truth(rows[i])) " " else ""), word))
                        }
                    }
                }
            }
            while (truth(jsLess(rows.length, count))) {
                rows.push("")
            }
            if (truth(jsMore(rows.length, count))) {
                throw global_Error(jsAdd("Explicación demasiado larga para el detalle: ", text))
            }
            return rows
        }
        reflowInset = fun(markup: dynamic): dynamic {
            if (truth((!truth(markup)))) {
                return ""
            }
            val textMatches: dynamic = jsArray { array275 ->
                appendAll(array275, markup.matchAll(jsRegExp("<text\\b([^>]*)>([^<]*)<\\/text>", "g")))
            }
            val title: dynamic = textMatches.find((fun(m: dynamic): dynamic {
                return dynamicValue(jsRegExp("y=\"642\"", "")).test(m[1])
            }))
            val note: dynamic = textMatches.find((fun(m: dynamic): dynamic {
                return dynamicValue(jsRegExp("y=\"71[9]|y=\"720\"", "")).test(m[1])
            }))
            if (truth(jsOr((!truth(title))) { (!truth(note)) })) {
                return markup
            }
            var art: dynamic = markup.replace(jsRegExp("^<g\\b[^>]*>", ""), "").replace(jsRegExp("<\\/g>\$", ""), "").replace(jsRegExp("<rect\\b[^>]*\\/?>", ""), "").replace(title[0], "").replace(note[0], "")
            art = art.replace(jsRegExp("font-size=\"1[23]\"", "g"), "font-size=\"20\"")
            val text: dynamic = (fun(match: dynamic, y: dynamic, size: dynamic, max: dynamic, count: dynamic): dynamic {
                val fill: dynamic = jsOr(onPresent(match[1].match(jsRegExp("fill=\"([^\"]+)\"", ""))) { value276 -> value276[1] }) { "#b0c6d1" }
                return jsAdd(jsAdd(("<text x=\"76\" y=\"" + textOf(y) + "\" font-size=\"" + textOf(size) + "\" fill=\"" + textOf(fill) + "\" font-family=\"sans-serif\">"), wrapped(match[2], max, count).map((fun(row: dynamic, i: dynamic): dynamic {
                    return ("<tspan x=\"76\" dy=\"" + textOf((if (truth(i)) jsAdd(size, 3) else 0)) + "\">" + textOf(row) + "</tspan>")
                })).join("")), "</text>")
            })
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-detail-layout31=\"complete\"><rect x=\"58\" y=\"620\" width=\"604\" height=\"288\" rx=\"14\" fill=\"#102431\" stroke=\"#47606e\"/>", text(title, 655, 23, 40, 2)), "<g transform=\"translate(0 50)\">"), art), "</g>"), text(note, 818, 24, 44, 3)), "</g>")
        }
        cp = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        mix = global_Rig2D23.mix
        wave = global_Rig2D23.wave
        add = (fun(a: dynamic, b: dynamic, arg3: dynamic): dynamic {
            var k: dynamic = absent
            k = definedOr(arg3) { 1 }
            return a.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsAdd(v, jsMultiply(b[i], k))
            }))
        })
        sub = (fun(a: dynamic, b: dynamic): dynamic {
            return a.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsSubtract(v, b[i])
            }))
        })
        unit = (fun(v: dynamic): dynamic {
            val d: dynamic = jsOr(run { val target5: dynamic = global_Math; invokeWith(target5.hypot, target5, jsArray { array4 ->
                appendAll(array4, v)
            }) }) { 1 }
            return v.map((fun(x: dynamic): dynamic {
                return jsDivide(x, d)
            }))
        })
        ik = global_Rig2D23.ik
        kindOf = (fun(e: dynamic, raw: dynamic): dynamic {
            return jsOr(jsOr(e.recipe30) { e.recipe23 }) { raw.view21.family }
        })
        squatKinds = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"squat\",\"chair\",\"goblet\",\"doublefront\"]")))
        root.Motion31 = jsObject { obj279 ->
            obj279["resolve"] = resolve
            obj279["configureModel"] = configureModel
            obj279["extras"] = extras
            obj279["inset"] = inset
            obj279["reflowInset"] = reflowInset
            obj279["wrapped"] = wrapped
            obj279["kindOf"] = kindOf
            obj279["version"] = 31
        }
        global_Motion30.clear()
        global_Motion2D18.clearBounds()
        return absent
    }))(global_window)
}
