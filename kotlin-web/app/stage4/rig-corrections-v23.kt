@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installrigcorrectionsv23

import hunter.browser.*

@JsName("JSON") private external var global_JSON: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installRigCorrectionsV23() {
    dynamicValue((fun(root: dynamic): dynamic {
        var cp: dynamic = absent
        var mix: dynamic = absent
        var add: dynamic = absent
        var sub: dynamic = absent
        var wave: dynamic = absent
        var ik: dynamic = absent
        var standing: dynamic = absent
        var amplitude: dynamic = absent
        var legsStraight: dynamic = absent
        var march: dynamic = absent
        var heelBack: dynamic = absent
        var reverseCrunch: dynamic = absent
        var climber: dynamic = absent
        var lunge: dynamic = absent
        var sideLeg: dynamic = absent
        var sidePlank: dynamic = absent
        var towel: dynamic = absent
        var boxing: dynamic = absent
        var cat: dynamic = absent
        var topTap: dynamic = absent
        var contralateral: dynamic = absent
        var reviews: dynamic = absent
        var install: dynamic = absent
        var inset: dynamic = absent
        ik = fun(a: dynamic, b: dynamic, arg7: dynamic, arg8: dynamic): dynamic {
            var length: dynamic = absent
            length = definedOr(arg7) { 155 }
            var side: dynamic = absent
            side = definedOr(arg8) { 1 }
            val v: dynamic = sub(b, a)
            val d: dynamic = global_Math.max(1, run { val target10: dynamic = global_Math; invokeWith(target10.hypot, target10, jsArray { array9 ->
                appendAll(array9, v)
            }) })
            val mid: dynamic = mix(a, b, 0.5)
            val height: dynamic = global_Math.sqrt(global_Math.max(0, jsSubtract(jsMultiply(length, length), jsDivide(jsPower(global_Math.min(d, jsMultiply(length, 2)), 2), 4))))
            return arrayOf<dynamic>(jsAdd(mid[0], jsMultiply(jsDivide(jsMultiply(side, v[1]), d), height)), jsSubtract(mid[1], jsMultiply(jsDivide(jsMultiply(side, v[0]), d), height)))
        }
        standing = fun(): dynamic {
            return jsonValue("{\"p\":[[350,200],[350,260],[350,425],[356,370],[360,465],[338,370],[340,465],[357,562],[357,704],[342,562],[342,704]],\"loads\":[],\"equipment\":[],\"detail\":{\"floor23\":715}}")
        }
        amplitude = fun(frames: dynamic, index: dynamic, axis: dynamic): dynamic {
            val values: dynamic = frames.map((fun(f: dynamic): dynamic {
                return f.p[index][axis]
            }))
            val lo: dynamic = run { val target16: dynamic = global_Math; invokeWith(target16.min, target16, jsArray { array15 ->
                appendAll(array15, values)
            }) }
            val hi: dynamic = run { val target18: dynamic = global_Math; invokeWith(target18.max, target18, jsArray { array17 ->
                appendAll(array17, values)
            }) }
            return (fun(f: dynamic, i: dynamic): dynamic {
                return (if (truth(jsMore(jsSubtract(hi, lo), 3))) jsDivide(jsSubtract(f.p[index][axis], lo), jsSubtract(hi, lo)) else wave(jsDivide(i, 48)))
            })
        }
        legsStraight = fun(f: dynamic): dynamic {
            if (true) {
                val iterator21: dynamic = iteratorOf(jsonValue("[[7,8],[9,10]]"))
                while (true) {
                    val next22: dynamic = iterator21.next()
                    if (truth(next22.done)) break
                    var k: dynamic = absent
                    var a: dynamic = absent
                    val unpacked23: dynamic = fromIterable(next22.value)
                    k = unpacked23[0]
                    a = unpacked23[1]
                    f.p[k] = mix(f.p[2], f.p[a], 0.5)
                }
            }
            return f
        }
        march = fun(a: dynamic, arg28: dynamic): dynamic {
            var b: dynamic = absent
            b = definedOr(arg28) { 0 }
            val f: dynamic = standing()
            val p: dynamic = f.p
            if (true) {
                val iterator29: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(7, 8, a, 7), arrayOf<dynamic>(9, 10, b, (-numeric(8)))))
                while (true) {
                    val next30: dynamic = iterator29.next()
                    if (truth(next30.done)) break
                    var k: dynamic = absent
                    var z: dynamic = absent
                    var u: dynamic = absent
                    var offset: dynamic = absent
                    val unpacked31: dynamic = fromIterable(next30.value)
                    k = unpacked31[0]
                    z = unpacked31[1]
                    u = unpacked31[2]
                    offset = unpacked31[3]
                    val hip: dynamic = arrayOf<dynamic>(jsAdd(350, offset), 425)
                    val angle: dynamic = jsMultiply(u, 1.42)
                    p[k] = arrayOf<dynamic>(jsAdd(hip[0], jsMultiply(145, global_Math.sin(angle))), jsAdd(hip[1], jsMultiply(145, global_Math.cos(angle))))
                    p[z] = arrayOf<dynamic>(jsAdd(p[k][0], jsMultiply(10, u)), jsAdd(p[k][1], 140))
                }
            }
            p[3] = arrayOf<dynamic>(jsSubtract(jsAdd(350, jsMultiply(42, b)), jsMultiply(25, a)), 365)
            p[4] = arrayOf<dynamic>(jsSubtract(jsAdd(350, jsMultiply(78, b)), jsMultiply(35, a)), jsSubtract(450, jsMultiply(50, b)))
            p[5] = arrayOf<dynamic>(jsSubtract(jsAdd(338, jsMultiply(42, a)), jsMultiply(25, b)), 367)
            p[6] = arrayOf<dynamic>(jsSubtract(jsAdd(338, jsMultiply(78, a)), jsMultiply(35, b)), jsSubtract(452, jsMultiply(50, a)))
            return f
        }
        heelBack = fun(t: dynamic): dynamic {
            val a: dynamic = global_Math.max(0, global_Math.sin(jsMultiply(t, 6.283)))
            val b: dynamic = global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(t, 6.283)))))
            val f: dynamic = march(0, 0)
            if (true) {
                val iterator46: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(7, 8, a), arrayOf<dynamic>(9, 10, b)))
                while (true) {
                    val next47: dynamic = iterator46.next()
                    if (truth(next47.done)) break
                    var k: dynamic = absent
                    var z: dynamic = absent
                    var u: dynamic = absent
                    val unpacked48: dynamic = fromIterable(next47.value)
                    k = unpacked48[0]
                    z = unpacked48[1]
                    u = unpacked48[2]
                    f.p[z] = arrayOf<dynamic>(jsSubtract(f.p[k][0], jsMultiply(100, u)), jsSubtract(jsAdd(f.p[k][1], 140), jsMultiply(110, u)))
                }
            }
            return f
        }
        reverseCrunch = fun(a: dynamic): dynamic {
            val f: dynamic = standing()
            f.p = arrayOf<dynamic>(jsonValue("[160,555]"), jsonValue("[220,570]"), arrayOf<dynamic>(jsSubtract(410, jsMultiply(30, a)), jsSubtract(565, jsMultiply(35, a))), jsonValue("[270,600]"), jsonValue("[335,620]"), jsonValue("[257,587]"), jsonValue("[322,607]"), arrayOf<dynamic>(jsSubtract(450, jsMultiply(75, a)), jsSubtract(430, jsMultiply(30, a))), arrayOf<dynamic>(jsSubtract(575, jsMultiply(95, a)), jsSubtract(435, jsMultiply(30, a))), arrayOf<dynamic>(jsSubtract(438, jsMultiply(75, a)), jsSubtract(418, jsMultiply(30, a))), arrayOf<dynamic>(jsSubtract(563, jsMultiply(95, a)), jsSubtract(423, jsMultiply(30, a))))
            f.detail.floor23 = 640
            return f
        }
        climber = fun(t: dynamic): dynamic {
            val f: dynamic = standing()
            val p: dynamic = f.p
            p[0] = jsonValue("[195,420]")
            p[1] = jsonValue("[250,440]")
            p[2] = jsonValue("[430,495]")
            p[3] = jsonValue("[250,530]")
            p[4] = jsonValue("[250,620]")
            p[5] = jsonValue("[237,518]")
            p[6] = jsonValue("[237,608]")
            p[8] = jsonValue("[690,605]")
            p[10] = jsonValue("[677,593]")
            p[7] = mix(p[2], p[8], 0.5)
            p[9] = mix(p[2], p[10], 0.5)
            if (true) {
                val iterator81: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(7, 8, global_Math.max(0, global_Math.sin(jsMultiply(t, 6.283))), 0), arrayOf<dynamic>(9, 10, global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(t, 6.283))))), (-numeric(12)))))
                while (true) {
                    val next82: dynamic = iterator81.next()
                    if (truth(next82.done)) break
                    var k: dynamic = absent
                    var z: dynamic = absent
                    var u: dynamic = absent
                    var o: dynamic = absent
                    val unpacked83: dynamic = fromIterable(next82.value)
                    k = unpacked83[0]
                    z = unpacked83[1]
                    u = unpacked83[2]
                    o = unpacked83[3]
                    p[k] = mix(p[k], arrayOf<dynamic>(330, jsAdd(540, o)), u)
                    p[z] = mix(p[z], arrayOf<dynamic>(450, jsAdd(590, o)), u)
                }
            }
            f.detail.floor23 = 637
            return f
        }
        lunge = fun(a: dynamic, arg92: dynamic, arg93: dynamic): dynamic {
            var reverse: dynamic = absent
            reverse = definedOr(arg92) { false }
            var weighted: dynamic = absent
            weighted = definedOr(arg93) { false }
            val f: dynamic = standing()
            val p: dynamic = f.p
            val hip: dynamic = arrayOf<dynamic>(360, jsAdd(425, jsMultiply(135, a)))
            val front: dynamic = jsonValue("[445,704]")
            val rear: dynamic = arrayOf<dynamic>((if (truth(reverse)) jsSubtract(350, jsMultiply(145, a)) else 205), 692)
            p[2] = hip
            p[1] = arrayOf<dynamic>(350, jsSubtract(hip[1], 167))
            p[0] = arrayOf<dynamic>(350, jsSubtract(hip[1], 227))
            p[7] = ik(hip, front, 158, 1)
            p[8] = front
            p[9] = ik(hip, rear, 158, 1)
            p[10] = rear
            p[3] = arrayOf<dynamic>(350, jsSubtract(hip[1], 62))
            p[4] = arrayOf<dynamic>(350, jsAdd(hip[1], 38))
            p[5] = arrayOf<dynamic>(338, jsSubtract(hip[1], 60))
            p[6] = arrayOf<dynamic>(338, jsAdd(hip[1], 40))
            f.detail.rearToe23 = 10
            f.detail.floor23 = 715
            if (truth(weighted)) {
                f.loads = arrayOf<dynamic>(cp(p[4]), cp(p[6]))
            }
            return f
        }
        sideLeg = fun(a: dynamic, arg124: dynamic): dynamic {
            var clam: dynamic = absent
            clam = definedOr(arg124) { false }
            val f: dynamic = standing()
            f.p = arrayOf<dynamic>(jsonValue("[150,515]"), jsonValue("[215,515]"), jsonValue("[395,515]"), jsonValue("[195,555]"), jsonValue("[155,535]"), jsonValue("[295,495]"), jsonValue("[389,509]"), jsonValue("[550,540]"), jsonValue("[705,540]"), arrayOf<dynamic>(jsAdd(395, jsMultiply(155, global_Math.cos(jsMultiply(a, 0.65)))), jsSubtract(490, jsMultiply(155, global_Math.sin(jsMultiply(a, 0.65))))), arrayOf<dynamic>(jsAdd(395, jsMultiply(310, global_Math.cos(jsMultiply(a, 0.65)))), jsSubtract(490, jsMultiply(310, global_Math.sin(jsMultiply(a, 0.65))))))
            if (truth(clam)) {
                f.p[7] = jsonValue("[530,590]")
                f.p[8] = jsonValue("[650,560]")
                f.p[9] = arrayOf<dynamic>(530, jsSubtract(575, jsMultiply(75, a)))
                f.p[10] = jsonValue("[650,560]")
            }
            f.detail.floor23 = 567
            return f
        }
        sidePlank = fun(arg139: dynamic, arg140: dynamic, arg141: dynamic): dynamic {
            var full: dynamic = absent
            full = definedOr(arg139) { false }
            var a: dynamic = absent
            a = definedOr(arg140) { 1 }
            var lift: dynamic = absent
            lift = definedOr(arg141) { false }
            val f: dynamic = sideLeg(0)
            val hip: dynamic = arrayOf<dynamic>(405, jsAdd(520, (if (truth(lift)) jsMultiply(55, jsSubtract(1, a)) else 0)))
            val shoulder: dynamic = jsonValue("[235,465]")
            val foot: dynamic = jsonValue("[705,595]")
            f.p[0] = jsonValue("[175,445]")
            f.p[1] = shoulder
            f.p[2] = hip
            f.p[3] = jsonValue("[225,595]")
            f.p[4] = jsonValue("[130,595]")
            f.p[5] = jsonValue("[315,480]")
            f.p[6] = jsonValue("[405,514]")
            if (truth(full)) {
                f.p[7] = mix(hip, foot, 0.53)
                f.p[8] = foot
                f.p[9] = add(f.p[7], jsonValue("[0,-12]"))
                f.p[10] = add(foot, jsonValue("[0,-12]"))
            } else {
                f.p[7] = jsonValue("[545,595]")
                f.p[8] = jsonValue("[635,525]")
                f.p[9] = jsonValue("[545,582]")
                f.p[10] = jsonValue("[635,512]")
            }
            f.detail.floor23 = 611
            return f
        }
        towel = fun(): dynamic {
            val f: dynamic = standing()
            f.p = jsonValue("[[230,260],[240,325],[270,510],[305,370],[360,435],[293,378],[349,444],[422,515],[575,515],[422,502],[575,502]]")
            f.detail.floor23 = 535
            f.detail.towel23 = true
            return f
        }
        boxing = fun(t: dynamic): dynamic {
            val f: dynamic = standing()
            val p: dynamic = f.p
            val a: dynamic = wave(t)
            val other: dynamic = wave(jsAdd(t, 0.5))
            p[7] = jsonValue("[395,565]")
            p[8] = jsonValue("[425,704]")
            p[9] = jsonValue("[305,565]")
            p[10] = jsonValue("[290,704]")
            p[3] = arrayOf<dynamic>(jsAdd(410, jsMultiply(50, a)), jsSubtract(315, jsMultiply(20, a)))
            p[4] = arrayOf<dynamic>(jsAdd(385, jsMultiply(180, a)), 282)
            p[5] = arrayOf<dynamic>(jsAdd(395, jsMultiply(50, other)), jsSubtract(320, jsMultiply(20, other)))
            p[6] = arrayOf<dynamic>(jsAdd(374, jsMultiply(180, other)), 295)
            f.detail.floor23 = 715
            return f
        }
        cat = fun(a: dynamic): dynamic {
            val f: dynamic = standing()
            f.p = arrayOf<dynamic>(arrayOf<dynamic>(185, jsAdd(390, jsMultiply(22, a))), jsonValue("[245,420]"), jsonValue("[435,420]"), jsonValue("[245,510]"), jsonValue("[245,620]"), jsonValue("[232,510]"), jsonValue("[232,620]"), jsonValue("[435,620]"), jsonValue("[555,620]"), jsonValue("[422,608]"), jsonValue("[542,608]"))
            f.detail.spineCurve23 = jsMultiply(38, jsSubtract(jsMultiply(2, a), 1))
            f.detail.floor23 = 635
            return f
        }
        topTap = fun(t: dynamic, arg212: dynamic): dynamic {
            var reach: dynamic = absent
            reach = definedOr(arg212) { false }
            val f: dynamic = standing()
            val a: dynamic = global_Math.max(0, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2)))
            val b: dynamic = global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2)))))
            f.p = arrayOf<dynamic>(jsonValue("[360,195]"), jsonValue("[360,263]"), jsonValue("[360,450]"), arrayOf<dynamic>(jsAdd(303, jsMultiply(35, a)), jsSubtract(267, jsMultiply(45, a))), arrayOf<dynamic>(jsAdd(293, jsMultiply((if (truth(reach)) (-numeric(12)) else 102), a)), jsAdd(220, jsMultiply((if (truth(reach)) (-numeric(80)) else 45), a))), arrayOf<dynamic>(jsSubtract(417, jsMultiply(35, b)), jsSubtract(267, jsMultiply(45, b))), arrayOf<dynamic>(jsSubtract(427, jsMultiply((if (truth(reach)) (-numeric(12)) else 102), b)), jsAdd(220, jsMultiply((if (truth(reach)) (-numeric(80)) else 45), b))), jsonValue("[334,565]"), jsonValue("[319,705]"), jsonValue("[386,565]"), jsonValue("[401,705]"))
            f.detail.front19 = true
            f.detail.support23 = true
            f.detail.stage18 = (if (truth(reach)) "ALCANZA · MANTÉN EL APOYO" else "TOCA EL HOMBRO CONTRARIO")
            return f
        }
        contralateral = fun(t: dynamic): dynamic {
            val f: dynamic = standing()
            val a: dynamic = global_Math.max(0, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2)))
            val b: dynamic = global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2)))))
            f.p = jsonValue("[[360,210],[360,280],[360,460],[305,203],[293,110],[415,203],[427,110],[333,580],[328,700],[387,580],[392,700]]")
            f.detail.topShoulder19 = true
            f.detail.letter19 = "BRAZO Y PIERNA CONTRARIOS"
            f.detail.lift19 = global_Math.max(a, b)
            f.detail.opposite23 = arrayOf<dynamic>(a, b)
            f.detail.stage18 = (if (truth(jsMore(a, 0.25))) "BRAZO IZQUIERDO · PIERNA DERECHA" else (if (truth(jsMore(b, 0.25))) "BRAZO DERECHO · PIERNA IZQUIERDA" else "REGRESA AL SUELO CON CONTROL"))
            return f
        }
        install = fun(): dynamic {
            if (true) {
                val iterator237: dynamic = iteratorOf(global_Engine.exercises.filter((fun(e: dynamic): dynamic {
                    return jsAnd((e.variant === "base")) { (!truth(e.pack23)) }
                })))
                while (true) {
                    val next238: dynamic = iterator237.next()
                    if (truth(next238.done)) break
                    var e: dynamic = absent
                    e = next238.value
                    val m: dynamic = global_MotionDB[e.id]
                    val v: dynamic = m.view21
                    val old: dynamic = cp(m.frames)
                    val family: dynamic = v.family
                    val range: dynamic = amplitude(old, 2, 1)
                    var action: dynamic = "Perspectiva y cuatro fases conservadas; se aplican los ejes compartidos."
                    var changed: dynamic = false
                    val lunges: dynamic = jsonValue("[\"split\",\"reverselunge\",\"dblunge\",\"dbsplit\",\"splitpause\"]")
                    val marches: dynamic = jsonValue("[\"march\",\"fastmarch\",\"slowmarch\",\"kneeliftcardio\",\"standingcore\",\"highknees\",\"dbmarch\",\"walk\",\"farmerwalk\",\"linewalk\",\"frontbackstep\",\"fastfeet\",\"jog\",\"heeldig\",\"heelback\"]")
                    val boxes: dynamic = jsonValue("[\"box\",\"boxinterval\",\"boxupper\",\"shadowcombo\"]")
                    m.frames = old.map((fun(raw: dynamic, i: dynamic): dynamic {
                        var f: dynamic = cp(raw)
                        val t: dynamic = jsDivide(i, 48)
                        val a: dynamic = range(raw, i)
                        if (truth(lunges.includes(family))) {
                            f = lunge(a, dynamicValue(jsonValue("[\"reverselunge\",\"dblunge\"]")).includes(family), e.requirements.includes("db"))
                            action = "Zancada en perfil con longitud de muslo y pierna, rodilla anterior y apoyo de puntas posterior."
                            changed = true
                        } else {
                            if (truth(marches.includes(family))) {
                                val high: dynamic = dynamicValue(jsonValue("[\"standingcore\",\"kneeliftcardio\",\"highknees\",\"dbmarch\"]")).includes(family)
                                val amplitude23: dynamic = (if (truth(high)) 1 else 0.45)
                                f = (if (truth((family === "heelback"))) heelBack(t) else march(jsMultiply(global_Math.max(0, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))), amplitude23), jsMultiply(global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))))), amplitude23)))
                                if (truth(e.requirements.includes("db"))) {
                                    f.loads = arrayOf<dynamic>(cp(f.p[4]), cp(f.p[6]))
                                }
                                action = "Rodilla hacia delante o talón hacia atrás según el gesto; extremidades en el plano lateral."
                                changed = true
                            } else {
                                if (truth(boxes.includes(family))) {
                                    f = boxing(t)
                                    action = "Guardia y puños delante del rostro, apoyo estable sin cruce imposible de las piernas."
                                    changed = true
                                } else {
                                    if (truth(dynamicValue(jsonValue("[\"sideleg\",\"clam\"]")).includes(family))) {
                                        val getter: dynamic = amplitude(old, 10, 1)
                                        f = sideLeg(getter(raw, i), (family === "clam"))
                                        action = "Piernas superiores e inferiores separadas, pelvis estable y cabeza apoyada."
                                        changed = true
                                    } else {
                                        if (truth(jsOr((family === "sideplank")) { dynamicValue(jsRegExp("sideplank", "")).test(e.id) })) {
                                            f = sidePlank((e.id !== "sideplank"), a, (e.id === "sideplank-lift19"))
                                            action = "Apoyo de codo, pelvis y piernas alineados; rodillas solo en la variante con rodillas."
                                            changed = true
                                        } else {
                                            if (truth((family === "towelrow"))) {
                                                f = towel()
                                                action = "Remo con toalla sentado en el suelo, dos piernas extendidas y toalla alrededor de los pies."
                                                changed = true
                                            } else {
                                                if (truth((family === "cat"))) {
                                                    f = cat(wave(t))
                                                    action = "Curvatura dorsal visible, muñecas bajo hombros y rodillas bajo caderas."
                                                    changed = true
                                                } else {
                                                    if (truth((family === "reversecrunch"))) {
                                                        f = reverseCrunch(wave(t))
                                                        action = "Elevación corta de pelvis con rodillas flexionadas y brazos apoyados en el suelo."
                                                        changed = true
                                                    } else {
                                                        if (truth((family === "mountain"))) {
                                                            f = climber(t)
                                                            action = "Escalador con manos fijas y recogida alterna de rodilla, manteniendo el tronco estable."
                                                            changed = true
                                                        } else {
                                                            if (truth((family === "seatedpunch"))) {
                                                                f = boxing(t)
                                                                f.p[2] = jsonValue("[350,490]")
                                                                f.p[7] = jsonValue("[460,490]")
                                                                f.p[8] = jsonValue("[460,704]")
                                                                f.p[9] = jsonValue("[445,478]")
                                                                f.p[10] = jsonValue("[445,692]")
                                                                f.equipment = jsonValue("[[\"bench\",[285,515,440,715]]]")
                                                                v.primary = "side"
                                                                v.label = "Lateral"
                                                                action = "Golpe sentado hacia delante en perfil con rodillas flexionadas y pies apoyados."
                                                                changed = true
                                                            } else {
                                                                if (truth((family === "chestopen"))) {
                                                                    f = standing()
                                                                    f.p[0] = jsonValue("[360,205]")
                                                                    f.p[1] = jsonValue("[360,270]")
                                                                    f.p[2] = jsonValue("[360,435]")
                                                                    if (true) {
                                                                        val iterator265: dynamic = iteratorOf(jsonValue("[[3,4,-1],[5,6,1]]"))
                                                                        while (true) {
                                                                            val next266: dynamic = iterator265.next()
                                                                            if (truth(next266.done)) break
                                                                            var el: dynamic = absent
                                                                            var hand: dynamic = absent
                                                                            var sign: dynamic = absent
                                                                            val unpacked267: dynamic = fromIterable(next266.value)
                                                                            el = unpacked267[0]
                                                                            hand = unpacked267[1]
                                                                            sign = unpacked267[2]
                                                                            f.p[el] = arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, jsAdd(52, jsMultiply(65, wave(t))))), jsSubtract(373, jsMultiply(60, wave(t))))
                                                                            f.p[hand] = arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, jsAdd(52, jsMultiply(140, wave(t))))), jsSubtract(478, jsMultiply(160, wave(t))))
                                                                        }
                                                                    }
                                                                    f.p[7] = jsonValue("[330,565]")
                                                                    f.p[8] = jsonValue("[330,704]")
                                                                    f.p[9] = jsonValue("[390,565]")
                                                                    f.p[10] = jsonValue("[390,704]")
                                                                    action = "Apertura frontal visible y simétrica de brazos, con el tronco estable."
                                                                    changed = true
                                                                } else {
                                                                    if (truth((family === "lungeknee"))) {
                                                                        if (truth(jsLess(t, 0.5))) {
                                                                            val u: dynamic = global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))
                                                                            f = lunge(u, true)
                                                                            f.p[8] = jsonValue("[357,704]")
                                                                            f.p[7] = ik(f.p[2], f.p[8], 158, 1)
                                                                            f.p[10] = arrayOf<dynamic>(jsSubtract(342, jsMultiply(145, u)), 692)
                                                                            f.p[9] = ik(f.p[2], f.p[10], 158, 1)
                                                                        } else {
                                                                            f = march(0, global_Math.sin(jsMultiply(jsMultiply(jsSubtract(t, 0.5), global_Math.PI), 2)))
                                                                        }
                                                                        action = "Zancada posterior seguida por elevación de la misma rodilla al frente."
                                                                        changed = true
                                                                    } else {
                                                                        if (truth((family === "kneelingplank"))) {
                                                                            f.p[7] = arrayOf<dynamic>(jsAdd(f.p[2][0], 115), 620)
                                                                            f.p[8] = arrayOf<dynamic>(jsAdd(f.p[7][0], 110), 565)
                                                                            f.p[9] = arrayOf<dynamic>(jsSubtract(f.p[7][0], 12), 608)
                                                                            f.p[10] = arrayOf<dynamic>(jsSubtract(f.p[8][0], 12), 553)
                                                                            f.detail.floor23 = 637
                                                                            action = "Plancha con apoyo de rodillas visible y pies elevados, sin confundirla con plancha completa."
                                                                            changed = true
                                                                        } else {
                                                                            if (truth(dynamicValue(jsonValue("[\"shouldertap\",\"plankreach19\"]")).includes(family))) {
                                                                                f = topTap(t, (family === "plankreach19"))
                                                                                action = "Palmas delante de los hombros y brazo móvil distinguible; detalle lateral de los apoyos."
                                                                                changed = true
                                                                            } else {
                                                                                if (truth((family === "contralateral"))) {
                                                                                    f = contralateral(t)
                                                                                    action = "Se alterna brazo y pierna contrarios con detalle lateral sincronizado."
                                                                                    changed = true
                                                                                } else {
                                                                                    if (truth(jsOr(dynamicValue(jsonValue("[\"plank\",\"pushup\",\"closepush\",\"decline19\"]")).includes(family)) { (e.id === "pushup-control19") })) {
                                                                                        legsStraight(f)
                                                                                        action = "Rodillas extendidas entre cadera y tobillos, sin quiebro de la plancha."
                                                                                        changed = true
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
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (truth(changed)) {
                            f.detail = jsObject { obj300 ->
                                copyFields(obj300, raw.detail)
                                copyFields(obj300, f.detail)
                            }
                        }
                        if (truth(jsAnd(raw.detail.stage18) { (family !== "contralateral") })) {
                            f.detail.stage18 = raw.detail.stage18
                        }
                        f.detail.reviewed23 = true
                        f.detail.family23 = family
                        return f
                    }))
                    if (truth(dynamicValue(jsonValue("[\"shoulder\",\"hipcircle\",\"scapular\",\"neck\",\"wrist\",\"wristmob\",\"forearmmob\",\"fingers\"]")).includes(family))) {
                        m.hold = false
                    }
                    if (truth((family === "shoulder"))) {
                        v.inset = "shoulder23"
                        action = "Detalle ampliado de elevación y círculos de hombro."
                        changed = true
                    }
                    if (truth((family === "cat"))) {
                        v.inset = "spine23"
                        changed = true
                    }
                    if (truth((family === "towelrow"))) {
                        v.inset = "towel23"
                        changed = true
                    }
                    if (truth((family === "contralateral"))) {
                        v.inset = "opposite23"
                    }
                    if (truth((family === "seatedturn"))) {
                        v.inset = "rotation23"
                    }
                    if (truth(dynamicValue(jsonValue("[\"seatedcurl\",\"altseatedcurl\",\"front-seated-reference\"]")).includes(family))) {
                        v.primary = "front"
                        v.label = "Frontal"
                        v.mirror = false
                        action = "Rostro y torso frontales para la postura sentada con piernas separadas."
                        changed = true
                    }
                    if (truth(dynamicValue(jsonValue("[\"shouldertap\",\"plankreach19\"]")).includes(family))) {
                        v.inset = "support23"
                    }
                    if (truth(jsAnd(m.hold) { (!truth(v.inset)) })) {
                        v.inset = "hold23"
                    }
                    if (truth(jsAnd(dynamicValue(jsonValue("[\"hipcircle\",\"scapular\",\"neck\"]")).includes(family)) { (!truth(v.inset)) })) {
                        v.inset = "mobility23"
                    }
                    if (truth(jsAnd((v.primary === "side")) { dynamicValue(jsonValue("[\"chair\",\"squat\",\"goblet\",\"hinge\",\"rdl\",\"row\",\"bentrow\",\"sumodead\",\"singlehinge\",\"tricep\",\"latstretch\",\"suitdead\",\"single-squat19\",\"single-rdl19\",\"singlehinge-free19\"]")).includes(family) })) {
                        val bent: dynamic = m.frames.slice().sort((fun(a: dynamic, b: dynamic): dynamic {
                            return jsSubtract(global_Math.abs(jsSubtract(b.p[2][0], b.p[1][0])), global_Math.abs(jsSubtract(a.p[2][0], a.p[1][0])))
                        }))[0]
                        m.facing23 = (if (truth(jsMore(bent.p[2][0], bent.p[1][0]))) "left" else "right")
                    }
                    if (true) {
                        val iterator333: dynamic = iteratorOf(m.frames)
                        while (true) {
                            val next334: dynamic = iterator333.next()
                            if (truth(next334.done)) break
                            var f: dynamic = absent
                            f = next334.value
                            f.detail.view21 = v.primary
                        }
                    }
                    reviews.push(jsObject { obj337 ->
                        obj337["id"] = e.id
                        obj337["name"] = e.name
                        obj337["action"] = action
                        obj337["changed"] = changed
                        obj337["view"] = v.primary
                        obj337["inset"] = v.inset
                        obj337["hold"] = (!truth((!truth(m.hold))))
                    })
                }
            }
            return absent
        }
        inset = fun(kind: dynamic, f: dynamic, phase: dynamic, accent: dynamic): dynamic {
            val t: dynamic = jsDivide(jsMod(jsAdd(jsMod(phase, 48), 48), 48), 48)
            val a: dynamic = wave(t)
            val ink: dynamic = "#dceaf0"
            val red: dynamic = "#fb927d"
            var art: dynamic = ""
            var title: dynamic = ""
            var note: dynamic = ""
            if (truth((kind === "shoulder23"))) {
                title = "AMPLIACIÓN · CÍRCULO DE HOMBROS"
                note = "Sube, lleva atrás y baja · los brazos permanecen relajados"
                art = ("<circle cx=\"151\" cy=\"679\" r=\"23\" fill=\"none\" stroke=\"#47606e\" stroke-width=\"2\"/><path d=\"M112 698V665Q151 " + textOf(jsSubtract(644, jsMultiply(8, a))) + " 191 665V698\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"11\"/><circle cx=\"" + textOf(jsAdd(151, jsMultiply(23, global_Math.cos(jsMultiply(jsMultiply(t, global_Math.PI), 2))))) + "\" cy=\"" + textOf(jsAdd(679, jsMultiply(23, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))))) + "\" r=\"7\" fill=\"" + textOf(red) + "\"/>")
            } else {
                if (truth((kind === "spine23"))) {
                    title = "DE LADO · REDONDEA Y REGRESA"
                    note = "Movimiento suave de columna · muñecas y rodillas permanecen apoyadas"
                    art = ("<path d=\"M110 683Q160 " + textOf(jsSubtract(676, jsMultiply(38, jsSubtract(jsMultiply(2, a), 1)))) + " 211 683M110 683V703M211 683V703\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\"/><circle cx=\"93\" cy=\"" + textOf(jsAdd(679, jsMultiply(7, a))) + "\" r=\"11\" fill=\"#edc5ad\"/>")
                } else {
                    if (truth((kind === "towel23"))) {
                        title = "AMPLIACIÓN · TENSIÓN DE LA TOALLA"
                        note = "Tira hacia ti sin inclinar el tronco ni soltar los pies"
                        art = ("<path d=\"M95 683H151L192 697L202 678L151 682\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"4\"/><path d=\"M102 668L89 676l12 4M196 675l12 6-10 4\" fill=\"none\" stroke=\"" + textOf(red) + "\" stroke-width=\"3\"/>")
                    } else {
                        if (truth((kind === "opposite23"))) {
                            var left: dynamic = absent
                            var right: dynamic = absent
                            val unpacked340: dynamic = fromIterable(jsOr(f.detail.opposite23) { arrayOf<dynamic>(a, jsSubtract(1, a)) })
                            left = unpacked340[0]
                            right = unpacked340[1]
                            title = "DE LADO · BRAZO Y PIERNA OPUESTOS"
                            note = (if (truth(jsMore(left, 0.25))) "Eleva brazo izquierdo y pierna derecha" else (if (truth(jsMore(right, 0.25))) "Eleva brazo derecho y pierna izquierda" else "Vuelve con control antes de alternar"))
                            art = ("<circle cx=\"93\" cy=\"676\" r=\"9\" fill=\"#edc5ad\"/><path d=\"M110 681H182\" stroke=\"" + textOf(ink) + "\" stroke-width=\"12\"/><path d=\"M113 684L77 " + textOf(jsSubtract(684, jsMultiply(18, global_Math.max(left, right)))) + "M176 684L224 " + textOf(jsSubtract(684, jsMultiply(18, global_Math.max(left, right)))) + "\" stroke=\"" + textOf(red) + "\" stroke-width=\"7\"/><path d=\"M75 704H228\" stroke=\"#688594\"/>")
                        } else {
                            if (truth((kind === "support23"))) {
                                title = "DE LADO · APOYOS Y PELVIS"
                                note = "Mano de apoyo bajo el hombro · evita girar la cadera"
                                art = ("<circle cx=\"91\" cy=\"667\" r=\"8\" fill=\"#edc5ad\"/><path d=\"M104 673L165 685L219 698M110 676L110 702L124 702\" stroke=\"" + textOf(ink) + "\" stroke-width=\"8\" fill=\"none\"/><path d=\"M145 678L" + textOf(jsSubtract(130, jsMultiply(18, a))) + " " + textOf(jsSubtract(670, jsMultiply(18, a))) + "\" stroke=\"" + textOf(red) + "\" stroke-width=\"6\"/>")
                            } else {
                                if (truth((kind === "pushDepth23"))) {
                                    val amount: dynamic = nullableOr(f.detail.pushDepth23) { a }
                                    title = "DE LADO · DESCENSO Y APOYO"
                                    note = "La vista superior muestra las manos · aquí se ve el descenso del pecho"
                                    art = ("<circle cx=\"99\" cy=\"" + textOf(jsAdd(663, jsMultiply(18, amount))) + "\" r=\"8\" fill=\"#edc5ad\"/><path d=\"M110 " + textOf(jsAdd(669, jsMultiply(18, amount))) + "L166 " + textOf(jsAdd(681, jsMultiply(9, amount))) + "L218 699M119 " + textOf(jsAdd(672, jsMultiply(18, amount))) + "L" + textOf(jsSubtract(119, jsMultiply(13, amount))) + " 691L137 702\" stroke=\"" + textOf(ink) + "\" stroke-width=\"8\" fill=\"none\"/><path d=\"M82 705H231\" stroke=\"#688594\"/>")
                                } else {
                                    if (truth((kind === "floorFly23"))) {
                                        title = "DE LADO · RESPALDO Y CODO"
                                        note = "Espalda apoyada · conserva los codos suaves y limita la apertura al suelo"
                                        art = ("<path d=\"M85 704H230\" stroke=\"#688594\"/><circle cx=\"97\" cy=\"694\" r=\"8\" fill=\"#edc5ad\"/><path d=\"M110 698H170L194 677L214 700M123 696L" + textOf(jsSubtract(140, jsMultiply(8, a))) + " " + textOf(jsSubtract(690, jsMultiply(10, a))) + "L" + textOf(jsAdd(148, jsMultiply(2, a))) + " " + textOf(jsSubtract(678, jsMultiply(20, a))) + "\" stroke=\"" + textOf(ink) + "\" stroke-width=\"8\" fill=\"none\"/>")
                                    } else {
                                        if (truth((kind === "frog23"))) {
                                            title = "PIERNAS · PLANTAS JUNTAS"
                                            note = "Rodillas abiertas en un recorrido cómodo · eleva la pelvis sin arquear la espalda"
                                            art = ("<path d=\"M156 655L" + textOf(jsSubtract(112, jsMultiply(5, a))) + " 681L156 699M156 655L" + textOf(jsAdd(200, jsMultiply(5, a))) + " 681L156 699\" fill=\"none\" stroke=\"" + textOf(ink) + "\" stroke-width=\"10\"/><ellipse cx=\"150\" cy=\"699\" rx=\"5\" ry=\"10\" fill=\"" + textOf(accent) + "\"/><ellipse cx=\"162\" cy=\"699\" rx=\"5\" ry=\"10\" fill=\"" + textOf(accent) + "\"/>")
                                        } else {
                                            if (truth((kind === "grip23"))) {
                                                val `param`: dynamic = jsOr(f.detail.param23) { js("({})") }
                                                val turn: dynamic = (if (truth(`param`.pronate)) global_Math.cos(jsMultiply(jsMultiply(t, global_Math.PI), 2)) else (if (truth(`param`.supinate)) 1 else (if (truth(`param`.neutral)) 0 else (-numeric(1)))))
                                                val spread: dynamic = (if (truth(`param`.close)) 25 else (if (truth(`param`.wide)) 58 else 40))
                                                title = (if (truth(`param`.pronate)) "AGARRE · GIRA ANTES DEL RETORNO" else "AGARRE · POSICIÓN DE LAS MANOS")
                                                note = (if (truth(`param`.pronate)) "Sube con palmas arriba · baja con palmas abajo" else (if (truth(`param`.supinate)) "Palmas hacia ti · conserva las muñecas alineadas" else (if (truth(`param`.neutral)) "Palmas enfrentadas · agarre neutro" else (if (truth(`param`.close)) "Agarre cercano · controla los codos" else "Agarre indicado · no dobles las muñecas para sostener la carga"))))
                                                art = ("<path d=\"M" + textOf(jsSubtract(154, spread)) + " 703V678M" + textOf(jsAdd(154, spread)) + " 703V678\" stroke=\"" + textOf(ink) + "\" stroke-width=\"11\"/><path d=\"M85 667H225\" stroke=\"" + textOf(accent) + "\" stroke-width=\"5\"/><ellipse cx=\"" + textOf(jsSubtract(154, spread)) + "\" cy=\"674\" rx=\"10\" ry=\"" + textOf(jsAdd(5, jsMultiply(6, global_Math.abs(turn)))) + "\" fill=\"" + textOf((if (truth(jsMore(turn, 0))) "#edc5ad" else "#d7ab92")) + "\"/><ellipse cx=\"" + textOf(jsAdd(154, spread)) + "\" cy=\"674\" rx=\"10\" ry=\"" + textOf(jsAdd(5, jsMultiply(6, global_Math.abs(turn)))) + "\" fill=\"" + textOf((if (truth(jsMore(turn, 0))) "#edc5ad" else "#d7ab92")) + "\"/>")
                                            } else {
                                                if (truth((kind === "rotation23"))) {
                                                    title = "DESDE ARRIBA · GIRO PEQUEÑO"
                                                    note = "Pelvis estable · gira el pecho en un recorrido cómodo, sin forzarlo"
                                                    val th: dynamic = jsMultiply(0.38, global_Math.sin(jsMultiply(t, 6.283)))
                                                    art = ("<path d=\"M132 701H177\" stroke=\"#47606e\" stroke-width=\"9\"/><path d=\"M" + textOf(jsSubtract(154, jsMultiply(32, global_Math.cos(th)))) + " " + textOf(jsSubtract(675, jsMultiply(32, global_Math.sin(th)))) + "L" + textOf(jsAdd(154, jsMultiply(32, global_Math.cos(th)))) + " " + textOf(jsAdd(675, jsMultiply(32, global_Math.sin(th)))) + "\" stroke=\"" + textOf(ink) + "\" stroke-width=\"10\"/><circle cx=\"154\" cy=\"675\" r=\"8\" fill=\"" + textOf(red) + "\"/>")
                                                } else {
                                                    if (truth((kind === "pallof23"))) {
                                                        title = "DESDE ARRIBA · RESISTE EL GIRO"
                                                        note = "Extiende al frente · la polea tira desde un lado y el torso permanece estable"
                                                        art = ("<path d=\"M124 691H184M154 691V704M135 691L154 " + textOf(jsSubtract(677, jsMultiply(19, a))) + "M174 691L154 " + textOf(jsSubtract(677, jsMultiply(19, a))) + "\" stroke=\"" + textOf(ink) + "\" stroke-width=\"8\" fill=\"none\"/><path d=\"M80 671L154 " + textOf(jsSubtract(677, jsMultiply(19, a))) + "\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/><path d=\"M104 665l-12 6 12 6\" stroke=\"" + textOf(red) + "\" stroke-width=\"3\" fill=\"none\"/>")
                                                    } else {
                                                        if (truth((kind === "hip23"))) {
                                                            title = "PELVIS · APERTURA PEQUEÑA"
                                                            note = "Usa el apoyo · abre y regresa sin girar la rodilla que soporta el peso"
                                                            art = ("<path d=\"M153 654V680L148 703M153 680L" + textOf(jsAdd(201, jsMultiply(8, a))) + " " + textOf(jsSubtract(684, jsMultiply(8, a))) + "\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\" fill=\"none\"/><ellipse cx=\"154\" cy=\"680\" rx=\"" + textOf(jsSubtract(22, jsMultiply(6, a))) + "\" ry=\"9\" fill=\"none\" stroke=\"" + textOf(red) + "\" stroke-width=\"4\"/>")
                                                        } else {
                                                            if (truth((kind === "scissors23"))) {
                                                                val s: dynamic = global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))
                                                                title = "VISTA FRONTAL · CRUCE ALTERNADO"
                                                                note = "Piernas alargadas · cambia qué pierna pasa arriba sin mover la pelvis"
                                                                art = ("<path d=\"M147 701L" + textOf(jsAdd(147, jsMultiply(27, s))) + " 654M165 701L" + textOf(jsSubtract(165, jsMultiply(27, s))) + " 654\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\" fill=\"none\"/><circle cx=\"147\" cy=\"701\" r=\"5\" fill=\"" + textOf(red) + "\"/><circle cx=\"165\" cy=\"701\" r=\"5\" fill=\"" + textOf(accent) + "\"/>")
                                                            } else {
                                                                if (truth((kind === "mobility23"))) {
                                                                    val family: dynamic = f.detail.family23
                                                                    title = "AMPLIACIÓN · RECORRIDO PEQUEÑO"
                                                                    note = "Recorrido cómodo y controlado · observa la articulación señalada"
                                                                    if (truth((family === "neck"))) {
                                                                        title = "CUELLO · GIRO SUAVE"
                                                                        note = "Gira sin llevar la cabeza atrás · hombros quietos"
                                                                        art = ("<path d=\"M113 702L125 684H183L195 702M154 685V676\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\" fill=\"none\"/><ellipse cx=\"154\" cy=\"660\" rx=\"17\" ry=\"20\" fill=\"#edc5ad\"/><circle cx=\"" + textOf(jsAdd(154, jsMultiply(11, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))))) + "\" cy=\"660\" r=\"3\" fill=\"#17344a\"/>")
                                                                    } else {
                                                                        if (truth((family === "hipcircle"))) {
                                                                            title = "PELVIS · CÍRCULO CONTROLADO"
                                                                            note = "Traza un círculo pequeño sin perder los apoyos"
                                                                            art = ("<ellipse cx=\"156\" cy=\"674\" rx=\"35\" ry=\"16\" fill=\"none\" stroke=\"#47606e\"/><path d=\"M" + textOf(jsAdd(139, jsMultiply(8, global_Math.cos(jsMultiply(t, 6.283))))) + " " + textOf(jsAdd(672, jsMultiply(5, global_Math.sin(jsMultiply(t, 6.283))))) + "L132 703M" + textOf(jsAdd(173, jsMultiply(8, global_Math.cos(jsMultiply(t, 6.283))))) + " " + textOf(jsAdd(672, jsMultiply(5, global_Math.sin(jsMultiply(t, 6.283))))) + "L183 703\" stroke=\"" + textOf(ink) + "\" stroke-width=\"9\"/><circle cx=\"" + textOf(jsAdd(156, jsMultiply(35, global_Math.cos(jsMultiply(t, 6.283))))) + "\" cy=\"" + textOf(jsAdd(674, jsMultiply(16, global_Math.sin(jsMultiply(t, 6.283))))) + "\" r=\"6\" fill=\"" + textOf(red) + "\"/>")
                                                                        } else {
                                                                            title = "ESCÁPULAS · ACERCA Y SUELTA"
                                                                            note = "Aproxima los omóplatos sin levantar los hombros"
                                                                            art = ("<path d=\"M156 653V703\" stroke=\"#47606e\" stroke-width=\"4\"/><path d=\"M" + textOf(jsAdd(130, jsMultiply(12, a))) + " 660L" + textOf(jsAdd(133, jsMultiply(12, a))) + " 688M" + textOf(jsSubtract(182, jsMultiply(12, a))) + " 660L" + textOf(jsSubtract(179, jsMultiply(12, a))) + " 688\" stroke=\"" + textOf(ink) + "\" stroke-width=\"10\"/><path d=\"M117 676h18m-5-5 5 5-5 5M195 676h-18m5-5-5 5 5 5\" stroke=\"" + textOf(red) + "\" stroke-width=\"3\" fill=\"none\"/>")
                                                                        }
                                                                    }
                                                                } else {
                                                                    if (truth((kind === "hold23"))) {
                                                                        title = "ISOMÉTRICO · SOSTÉN, NO SON REPETICIONES"
                                                                        note = "La postura se mantiene · respira y finaliza si pierdes la alineación"
                                                                        val points: dynamic = f.p
                                                                        val xs: dynamic = points.map((fun(p: dynamic): dynamic {
                                                                            return p[0]
                                                                        }))
                                                                        val ys: dynamic = points.map((fun(p: dynamic): dynamic {
                                                                            return p[1]
                                                                        }))
                                                                        val minX: dynamic = jsSubtract(run { val target342: dynamic = global_Math; invokeWith(target342.min, target342, jsArray { array341 ->
                                                                            appendAll(array341, xs)
                                                                        }) }, 10)
                                                                        val minY: dynamic = jsSubtract(run { val target344: dynamic = global_Math; invokeWith(target344.min, target344, jsArray { array343 ->
                                                                            appendAll(array343, ys)
                                                                        }) }, 20)
                                                                        val scale: dynamic = global_Math.min(jsDivide(128, jsAdd(jsSubtract(run { val target346: dynamic = global_Math; invokeWith(target346.max, target346, jsArray { array345 ->
                                                                            appendAll(array345, xs)
                                                                        }) }, minX), 20)), jsDivide(49, jsAdd(jsSubtract(run { val target348: dynamic = global_Math; invokeWith(target348.max, target348, jsArray { array347 ->
                                                                            appendAll(array347, ys)
                                                                        }) }, minY), 15)))
                                                                        val q: dynamic = (fun(p: dynamic): dynamic {
                                                                            return arrayOf<dynamic>(jsAdd(90, jsMultiply(jsSubtract(p[0], minX), scale)), jsAdd(650, jsMultiply(jsSubtract(p[1], minY), scale)))
                                                                        })
                                                                        val edges: dynamic = jsonValue("[[0,1],[1,2],[1,3],[3,4],[1,5],[5,6],[2,7],[7,8],[2,9],[9,10]]")
                                                                        val path: dynamic = edges.map((fun(arg351: dynamic): dynamic {
                                                                            var i: dynamic = absent
                                                                            var j: dynamic = absent
                                                                            val unpacked352: dynamic = fromIterable(arg351)
                                                                            i = unpacked352[0]
                                                                            j = unpacked352[1]
                                                                            return jsAdd(jsAdd(jsAdd("M", q(points[i]).join(",")), "L"), q(points[j]).join(","))
                                                                        })).join("")
                                                                        art = ("<path d=\"" + textOf(path) + "\" stroke=\"" + textOf(ink) + "\" stroke-width=\"4\" stroke-linecap=\"round\" fill=\"none\"/><circle cx=\"" + textOf(q(points[0])[0]) + "\" cy=\"" + textOf(q(points[0])[1]) + "\" r=\"5\" fill=\"#edc5ad\"/><circle cx=\"251\" cy=\"680\" r=\"" + textOf(jsAdd(11, jsMultiply(7, a))) + "\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/><text x=\"280\" y=\"685\" font-size=\"13\" fill=\"" + textOf(ink) + "\">RESPIRA Y SOSTÉN</text>")
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
                            }
                        }
                    }
                }
            }
            return ("<g data-view=\"side-inset\" data-detail23=\"" + textOf(kind) + "\"><rect x=\"58\" y=\"620\" width=\"604\" height=\"113\" rx=\"14\" fill=\"#102431\" stroke=\"#47606e\"/><text x=\"74\" y=\"642\" font-size=\"13\" fill=\"" + textOf(accent) + "\" font-family=\"sans-serif\">" + textOf(title) + "</text>" + textOf(art) + "<text x=\"74\" y=\"720\" font-size=\"12\" fill=\"#b0c6d1\" font-family=\"sans-serif\">" + textOf(note) + "</text></g>")
        }
        cp = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        mix = (fun(a: dynamic, b: dynamic, t: dynamic): dynamic {
            return a.map((fun(x: dynamic, i: dynamic): dynamic {
                return jsAdd(x, jsMultiply(jsSubtract(b[i], x), t))
            }))
        })
        add = (fun(a: dynamic, b: dynamic): dynamic {
            return a.map((fun(x: dynamic, i: dynamic): dynamic {
                return jsAdd(x, b[i])
            }))
        })
        val unit: dynamic = (fun(v: dynamic): dynamic {
            val n: dynamic = jsOr(run { val target4: dynamic = global_Math; invokeWith(target4.hypot, target4, jsArray { array3 ->
                appendAll(array3, v)
            }) }) { 1 }
            return v.map((fun(x: dynamic): dynamic {
                return jsDivide(x, n)
            }))
        })
        sub = (fun(a: dynamic, b: dynamic): dynamic {
            return a.map((fun(x: dynamic, i: dynamic): dynamic {
                return jsSubtract(x, b[i])
            }))
        })
        wave = (fun(t: dynamic): dynamic {
            return jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(t, global_Math.PI), 2))), 2)
        })
        reviews = js("[]")
        val aliases: dynamic = jsonValue("{\"splitpause\":\"split\",\"split\":\"split\",\"reverselunge\":\"reverselunge\",\"dblunge\":\"reverselunge\",\"dbsplit\":\"split\",\"sideleg\":\"sideleg\",\"clam\":\"clam\",\"sideplank\":\"sideplank\",\"contralateral\":\"contralateral\"}")
        install()
        root.Rig2D23 = jsObject { obj355 ->
            obj355["reviews"] = reviews
            obj355["ik"] = ik
            obj355["standing"] = standing
            obj355["march"] = march
            obj355["lunge"] = lunge
            obj355["sideLeg"] = sideLeg
            obj355["sidePlank"] = sidePlank
            obj355["towel"] = towel
            obj355["boxing"] = boxing
            obj355["cat"] = cat
            obj355["topTap"] = topTap
            obj355["contralateral"] = contralateral
            obj355["inset"] = inset
            obj355["wave"] = wave
            obj355["cp"] = cp
            obj355["mix"] = mix
        }
        return absent
    }))(global_window)
}
