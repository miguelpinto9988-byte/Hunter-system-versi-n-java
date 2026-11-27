@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installrigpackv23

import hunter.browser.*

@JsName("Rig2D23") private external var global_Rig2D23: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installRigPackV23() {
    dynamicValue((fun(root: dynamic): dynamic {
        var R: dynamic = absent
        var cp: dynamic = absent
        var mix: dynamic = absent
        var ik: dynamic = absent
        var wave: dynamic = absent
        var move: dynamic = absent
        var pt: dynamic = absent
        var stage: dynamic = absent
        var front: dynamic = absent
        var topPush: dynamic = absent
        var curtsy: dynamic = absent
        var stepFront: dynamic = absent
        var squat: dynamic = absent
        var hinge: dynamic = absent
        var push: dynamic = absent
        var bridge: dynamic = absent
        var reversePlank: dynamic = absent
        var hollow: dynamic = absent
        var legraise: dynamic = absent
        var bench: dynamic = absent
        var overhead: dynamic = absent
        var curl: dynamic = absent
        var row: dynamic = absent
        var cableRow: dynamic = absent
        var pulldown: dynamic = absent
        var fly: dynamic = absent
        var tricep: dynamic = absent
        var seatedLeg: dynamic = absent
        var legpress: dynamic = absent
        var add23: dynamic = absent
        var modelFrame: dynamic = absent
        var extras: dynamic = absent
        var loads: dynamic = absent
        front = fun(): dynamic {
            val f: dynamic = R.standing()
            f.p = jsonValue("[[360,205],[360,270],[360,435],[308,373],[308,478],[412,373],[412,478],[330,568],[330,704],[390,568],[390,704]]")
            return f
        }
        topPush = fun(a: dynamic, `param`: dynamic): dynamic {
            val f: dynamic = R.topTap(0)
            run {
                val target17: dynamic = f.p[0]
                val key18: dynamic = 1
                target17[key18] = jsAdd(target17[key18], jsMultiply(10, a))
            }
            f.p[4] = (if (truth((`param`.grip === "diamond"))) jsonValue("[350,290]") else jsonValue("[288,265]"))
            f.p[6] = (if (truth((`param`.grip === "diamond"))) jsonValue("[370,290]") else (if (truth(`param`.stagger)) jsonValue("[432,340]") else jsonValue("[510,270]")))
            f.p[3] = arrayOf<dynamic>((if (truth((`param`.grip === "diamond"))) 295 else 255), jsAdd(335, jsMultiply(15, a)))
            f.p[5] = arrayOf<dynamic>((if (truth((`param`.grip === "diamond"))) 425 else 465), jsAdd(335, jsMultiply(15, a)))
            f.detail.pushDepth23 = a
            return f
        }
        curtsy = fun(a: dynamic): dynamic {
            val f: dynamic = front()
            val p: dynamic = f.p
            p[2] = arrayOf<dynamic>(350, jsAdd(435, jsMultiply(120, a)))
            p[1] = arrayOf<dynamic>(350, jsAdd(270, jsMultiply(120, a)))
            p[0] = arrayOf<dynamic>(350, jsAdd(205, jsMultiply(120, a)))
            p[8] = jsonValue("[300,704]")
            p[10] = arrayOf<dynamic>(jsSubtract(425, jsMultiply(185, a)), 690)
            p[7] = arrayOf<dynamic>(300, jsAdd(565, jsMultiply(35, a)))
            p[9] = arrayOf<dynamic>(jsSubtract(395, jsMultiply(75, a)), jsAdd(560, jsMultiply(45, a)))
            if (true) {
                val iterator45: dynamic = iteratorOf(jsonValue("[[3,4,308],[5,6,405]]"))
                while (true) {
                    val next46: dynamic = iterator45.next()
                    if (truth(next46.done)) break
                    var el: dynamic = absent
                    var ha: dynamic = absent
                    var x: dynamic = absent
                    val unpacked47: dynamic = fromIterable(next46.value)
                    el = unpacked47[0]
                    ha = unpacked47[1]
                    x = unpacked47[2]
                    p[el] = arrayOf<dynamic>(x, jsAdd(373, jsMultiply(120, a)))
                    p[ha] = arrayOf<dynamic>(x, jsAdd(478, jsMultiply(120, a)))
                }
            }
            return f
        }
        stepFront = fun(a: dynamic, `param`: dynamic): dynamic {
            val f: dynamic = front()
            val p: dynamic = f.p
            p[2] = arrayOf<dynamic>(350, jsSubtract(460, jsMultiply(110, a)))
            p[1] = arrayOf<dynamic>(350, jsSubtract(295, jsMultiply(110, a)))
            p[0] = arrayOf<dynamic>(350, jsSubtract(230, jsMultiply(110, a)))
            p[8] = arrayOf<dynamic>((if (truth(`param`.cross)) 450 else 310), 580)
            p[7] = ik(p[2], p[8], 153, (-numeric(1)))
            p[10] = arrayOf<dynamic>(470, jsSubtract(704, jsMultiply(124, a)))
            p[9] = ik(p[2], p[10], 153, 1)
            p[3] = arrayOf<dynamic>(290, jsSubtract(400, jsMultiply(110, a)))
            p[4] = arrayOf<dynamic>(290, jsSubtract(510, jsMultiply(110, a)))
            p[5] = arrayOf<dynamic>(410, jsSubtract(400, jsMultiply(110, a)))
            p[6] = arrayOf<dynamic>(410, jsSubtract(510, jsMultiply(110, a)))
            f.equipment = arrayOf<dynamic>(arrayOf<dynamic>("step", arrayOf<dynamic>((if (truth(`param`.cross)) 390 else 255), 606, (if (truth(`param`.cross)) 510 else 370), 715)))
            return f
        }
        squat = fun(a: dynamic, arg80: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg80) { js("({})") }
            val f: dynamic = R.standing()
            val p: dynamic = f.p
            p[2] = arrayOf<dynamic>(jsSubtract(350, jsMultiply(75, a)), jsAdd(425, jsMultiply(135, a)))
            p[1] = arrayOf<dynamic>(jsAdd(350, jsMultiply(20, a)), jsAdd(260, jsMultiply(155, a)))
            p[0] = arrayOf<dynamic>(jsAdd(350, jsMultiply(40, a)), jsAdd(200, jsMultiply(152, a)))
            p[8] = jsonValue("[355,704]")
            p[10] = jsonValue("[340,704]")
            p[7] = ik(p[2], p[8], 155, 1)
            p[9] = ik(p[2], p[10], 155, 1)
            p[3] = arrayOf<dynamic>(jsAdd(p[1][0], 45), jsAdd(p[1][1], 58))
            p[4] = arrayOf<dynamic>(jsAdd(p[1][0], 20), jsAdd(p[1][1], 25))
            p[5] = arrayOf<dynamic>(jsAdd(p[1][0], 32), jsAdd(p[1][1], 64))
            p[6] = arrayOf<dynamic>(jsAdd(p[1][0], 8), jsAdd(p[1][1], 32))
            if (truth(`param`.bench)) {
                f.equipment = jsonValue("[[\"bench\",[430,570,600,715]]]")
            }
            return f
        }
        hinge = fun(a: dynamic): dynamic {
            val f: dynamic = R.standing()
            val p: dynamic = f.p
            p[2] = arrayOf<dynamic>(jsSubtract(350, jsMultiply(60, a)), jsAdd(435, jsMultiply(35, a)))
            p[1] = arrayOf<dynamic>(jsAdd(350, jsMultiply(90, a)), jsAdd(270, jsMultiply(120, a)))
            p[0] = arrayOf<dynamic>(jsAdd(350, jsMultiply(135, a)), jsAdd(210, jsMultiply(140, a)))
            p[7] = jsonValue("[365,570]")
            p[9] = jsonValue("[350,570]")
            p[8] = jsonValue("[335,704]")
            p[10] = jsonValue("[320,704]")
            if (true) {
                val iterator121: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-15]]"))
                while (true) {
                    val next122: dynamic = iterator121.next()
                    if (truth(next122.done)) break
                    var elbow: dynamic = absent
                    var hand: dynamic = absent
                    var x: dynamic = absent
                    val unpacked123: dynamic = fromIterable(next122.value)
                    elbow = unpacked123[0]
                    hand = unpacked123[1]
                    x = unpacked123[2]
                    p[elbow] = arrayOf<dynamic>(jsAdd(p[1][0], x), jsAdd(p[1][1], 108))
                    p[hand] = arrayOf<dynamic>(jsAdd(p[1][0], x), jsAdd(p[1][1], 208))
                }
            }
            return f
        }
        push = fun(a: dynamic, arg130: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg130) { js("({})") }
            val f: dynamic = R.standing()
            val p: dynamic = f.p
            val shift: dynamic = (if (truth(`param`.dive)) jsMultiply(40, a) else 0)
            p[1] = arrayOf<dynamic>(jsAdd(245, shift), jsAdd(435, jsMultiply(100, a)))
            p[2] = arrayOf<dynamic>(jsAdd(430, shift), jsAdd(495, jsMultiply(50, a)))
            p[8] = jsonValue("[690,585]")
            p[10] = jsonValue("[677,573]")
            p[7] = mix(p[2], p[8], 0.52)
            p[9] = mix(p[2], p[10], 0.52)
            p[0] = arrayOf<dynamic>(jsAdd(190, shift), jsAdd(415, jsMultiply(100, a)))
            val palms: dynamic = arrayOf<dynamic>(jsonValue("[245,610]"), arrayOf<dynamic>(jsAdd(230, (if (truth(`param`.stagger)) 65 else 0)), 600))
            p[4] = palms[0]
            p[6] = palms[1]
            p[3] = ik(p[1], p[4], 95, (-numeric(1)))
            p[5] = ik(p[1], p[6], 95, (-numeric(1)))
            f.detail.floor23 = 627
            if (truth(`param`.kneeDrive)) {
                p[7] = mix(p[7], jsonValue("[280,550]"), a)
                p[8] = jsonValue("[440,600]")
            }
            if (truth(`param`.dive)) {
                p[1] = arrayOf<dynamic>(jsAdd(245, jsMultiply(135, a)), jsAdd(435, jsMultiply(55, global_Math.sin(jsMultiply(a, global_Math.PI)))))
                p[2] = arrayOf<dynamic>(jsAdd(430, jsMultiply(40, a)), jsAdd(350, jsMultiply(185, a)))
                p[0] = arrayOf<dynamic>(jsSubtract(p[1][0], 55), jsSubtract(p[1][1], 20))
                p[7] = mix(p[2], p[8], 0.5)
                p[9] = mix(p[2], p[10], 0.5)
                p[3] = ik(p[1], p[4], 115, (-numeric(1)))
                p[5] = ik(p[1], p[6], 115, (-numeric(1)))
            }
            if (truth(`param`.release)) {
                run {
                    val target173: dynamic = p[4]
                    val key174: dynamic = 1
                    target173[key174] = jsSubtract(target173[key174], jsMultiply(14, a))
                }
                run {
                    val target175: dynamic = p[6]
                    val key176: dynamic = 1
                    target175[key176] = jsSubtract(target175[key176], jsMultiply(14, a))
                }
            }
            return f
        }
        bridge = fun(a: dynamic, arg179: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg179) { js("({})") }
            val f: dynamic = R.standing()
            f.p = arrayOf<dynamic>(jsonValue("[160,550]"), jsonValue("[220,565]"), arrayOf<dynamic>(420, jsSubtract(565, jsMultiply(115, a))), jsonValue("[272,590]"), jsonValue("[335,615]"), jsonValue("[263,577]"), jsonValue("[323,603]"), jsonValue("[545,485]"), jsonValue("[600,625]"), jsonValue("[532,472]"), jsonValue("[587,613]"))
            f.detail.floor23 = 644
            if (truth(`param`.long)) {
                f.p[7] = jsonValue("[590,540]")
                f.p[8] = jsonValue("[705,625]")
                f.p[9] = jsonValue("[577,527]")
                f.p[10] = jsonValue("[692,613]")
            }
            return f
        }
        reversePlank = fun(a: dynamic, arg194: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg194) { js("({})") }
            val f: dynamic = bridge(0.7)
            val p: dynamic = f.p
            p[1] = arrayOf<dynamic>(240, jsAdd(435, jsMultiply(55, jsSubtract(1, a))))
            p[2] = arrayOf<dynamic>(440, jsAdd(515, jsMultiply(45, jsSubtract(1, a))))
            p[0] = arrayOf<dynamic>(183, jsAdd(410, jsMultiply(55, jsSubtract(1, a))))
            p[3] = jsonValue("[230,540]")
            p[4] = jsonValue("[210,640]")
            p[5] = jsonValue("[217,527]")
            p[6] = jsonValue("[197,627]")
            p[8] = jsonValue("[710,640]")
            p[10] = jsonValue("[697,627]")
            p[7] = mix(p[2], p[8], 0.52)
            p[9] = mix(p[2], p[10], 0.52)
            f.detail.floor23 = 655
            if (truth(`param`.knee)) {
                p[7] = jsonValue("[555,455]")
                p[8] = jsonValue("[565,600]")
            }
            if (truth(`param`.walk)) {
                run {
                    val target223: dynamic = p[4]
                    val key224: dynamic = 0
                    target223[key224] = jsAdd(target223[key224], jsMultiply(30, a))
                }
                run {
                    val target225: dynamic = p[8]
                    val key226: dynamic = 0
                    target225[key226] = jsSubtract(target225[key226], jsMultiply(35, a))
                }
            }
            return f
        }
        hollow = fun(a: dynamic, arg229: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg229) { js("({})") }
            val f: dynamic = bridge(0)
            val p: dynamic = f.p
            f.p[1] = arrayOf<dynamic>(235, jsSubtract(545, jsMultiply(45, a)))
            f.p[0] = arrayOf<dynamic>(175, jsSubtract(525, jsMultiply(50, a)))
            f.p[2] = jsonValue("[405,590]")
            p[3] = arrayOf<dynamic>(205, jsSubtract(485, jsMultiply(40, a)))
            p[4] = arrayOf<dynamic>(140, jsSubtract(435, jsMultiply(25, a)))
            p[5] = arrayOf<dynamic>(200, jsSubtract(475, jsMultiply(40, a)))
            p[6] = arrayOf<dynamic>(130, jsSubtract(425, jsMultiply(25, a)))
            p[7] = arrayOf<dynamic>(535, jsSubtract(565, jsMultiply(85, a)))
            p[8] = arrayOf<dynamic>(665, jsSubtract(540, jsMultiply(130, a)))
            p[9] = arrayOf<dynamic>(524, jsSubtract(553, jsMultiply(85, a)))
            p[10] = arrayOf<dynamic>(654, jsSubtract(528, jsMultiply(130, a)))
            if (truth(`param`.tuck)) {
                p[7] = mix(jsonValue("[475,470]"), jsonValue("[535,540]"), a)
                p[8] = mix(jsonValue("[590,550]"), jsonValue("[665,490]"), a)
                p[9] = add23(p[7], jsonValue("[-12,-12]"))
                p[10] = add23(p[8], jsonValue("[-12,-12]"))
            }
            if (truth(`param`.vup)) {
                p[1] = arrayOf<dynamic>(300, jsSubtract(540, jsMultiply(115, a)))
                p[0] = arrayOf<dynamic>(245, jsSubtract(520, jsMultiply(115, a)))
                p[4] = arrayOf<dynamic>(520, jsSubtract(455, jsMultiply(70, a)))
                p[6] = arrayOf<dynamic>(509, jsSubtract(443, jsMultiply(70, a)))
                p[3] = ik(p[1], p[4], 120, (-numeric(1)))
                p[5] = ik(p[1], p[6], 120, (-numeric(1)))
            }
            if (truth(`param`.rock)) {
                move(f, jsonValue("[0,1,2,3,4,5,6,7,8,9,10]"), 0, jsMultiply(10, global_Math.sin(jsMultiply(jsMultiply(a, global_Math.PI), 2))))
            }
            return f
        }
        legraise = fun(a: dynamic, arg274: dynamic, arg275: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg274) { js("({})") }
            var t: dynamic = absent
            t = definedOr(arg275) { 0 }
            val f: dynamic = bridge(0)
            val p: dynamic = f.p
            val hip: dynamic = p[2]
            val angle: dynamic = jsSubtract((-numeric(0.1)), jsMultiply(a, 1.35))
            if (true) {
                val iterator276: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(7, 8, 0, (if (truth(`param`.flutter)) wave(t) else a)), arrayOf<dynamic>(9, 10, (-numeric(12)), (if (truth(`param`.flutter)) wave(jsAdd(t, 0.5)) else a))))
                while (true) {
                    val next277: dynamic = iterator276.next()
                    if (truth(next277.done)) break
                    var k: dynamic = absent
                    var z: dynamic = absent
                    var offset: dynamic = absent
                    var amount: dynamic = absent
                    val unpacked278: dynamic = fromIterable(next277.value)
                    k = unpacked278[0]
                    z = unpacked278[1]
                    offset = unpacked278[2]
                    amount = unpacked278[3]
                    val th: dynamic = (if (truth(`param`.flutter)) jsSubtract((-numeric(0.12)), jsMultiply(0.35, amount)) else angle)
                    p[k] = arrayOf<dynamic>(jsAdd(hip[0], jsMultiply(140, global_Math.cos(th))), jsAdd(jsAdd(hip[1], jsMultiply(140, global_Math.sin(th))), offset))
                    p[z] = arrayOf<dynamic>(jsAdd(hip[0], jsMultiply(280, global_Math.cos(th))), jsAdd(jsAdd(hip[1], jsMultiply(280, global_Math.sin(th))), offset))
                }
            }
            if (truth(`param`.scissors)) {
                run {
                    val target283: dynamic = p[8]
                    val key284: dynamic = 1
                    target283[key284] = jsAdd(target283[key284], jsMultiply(30, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))))
                }
                run {
                    val target285: dynamic = p[10]
                    val key286: dynamic = 1
                    target285[key286] = jsSubtract(target285[key286], jsMultiply(30, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))))
                }
            }
            return f
        }
        bench = fun(a: dynamic, arg289: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg289) { js("({})") }
            val f: dynamic = bridge(0)
            val p: dynamic = f.p
            p[0] = arrayOf<dynamic>(185, (if (truth(`param`.incline)) 310 else (if (truth(`param`.decline)) 460 else 420)))
            p[1] = arrayOf<dynamic>(250, (if (truth(`param`.incline)) 345 else (if (truth(`param`.decline)) 445 else 420)))
            p[2] = arrayOf<dynamic>(445, (if (truth(`param`.incline)) 465 else (if (truth(`param`.decline)) 400 else 430)))
            p[7] = jsonValue("[530,520]")
            p[8] = jsonValue("[570,704]")
            p[9] = jsonValue("[517,507]")
            p[10] = jsonValue("[557,692]")
            p[4] = arrayOf<dynamic>(275, jsSubtract(jsSubtract(p[1][1], 40), jsMultiply(155, a)))
            p[6] = arrayOf<dynamic>(261, jsSubtract(jsSubtract(p[1][1], 28), jsMultiply(155, a)))
            p[3] = ik(p[1], p[4], 108, (-numeric(1)))
            p[5] = ik(p[1], p[6], 108, (-numeric(1)))
            f.equipment = arrayOf<dynamic>(arrayOf<dynamic>((if (truth(jsOr(`param`.incline) { `param`.decline })) "incline" else "bench"), jsonValue("[150,450,460,715]")))
            f.detail.floor23 = 720
            return f
        }
        overhead = fun(a: dynamic, arg318: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg318) { js("({})") }
            val f: dynamic = front()
            val p: dynamic = f.p
            if (truth(`param`.seated)) {
                p[2][1] = 460
                p[7] = jsonValue("[295,565]")
                p[8] = jsonValue("[295,704]")
                p[9] = jsonValue("[425,565]")
                p[10] = jsonValue("[425,704]")
                f.equipment = jsonValue("[[\"bench\",[260,488,460,715]]]")
            }
            p[4] = arrayOf<dynamic>(300, jsSubtract(290, jsMultiply(165, a)))
            p[6] = arrayOf<dynamic>(420, jsSubtract(290, jsMultiply(165, a)))
            p[3] = arrayOf<dynamic>(jsAdd(255, jsMultiply(35, a)), jsSubtract(340, jsMultiply(120, a)))
            p[5] = arrayOf<dynamic>(jsSubtract(465, jsMultiply(35, a)), jsSubtract(340, jsMultiply(120, a)))
            return f
        }
        curl = fun(a: dynamic, arg341: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg341) { js("({})") }
            val f: dynamic = R.standing()
            val p: dynamic = f.p
            p[3] = jsonValue("[357,370]")
            p[5] = jsonValue("[342,370]")
            p[4] = arrayOf<dynamic>(jsAdd(357, jsMultiply(85, a)), jsSubtract(478, jsMultiply(175, a)))
            p[6] = arrayOf<dynamic>(jsAdd(342, jsMultiply(85, a)), jsSubtract(466, jsMultiply(175, a)))
            if (truth(`param`.cross)) {
                run {
                    val target350: dynamic = p[4]
                    val key351: dynamic = 0
                    target350[key351] = jsSubtract(target350[key351], jsMultiply(45, a))
                }
            }
            if (truth(`param`.preacher)) {
                p[2] = jsonValue("[360,490]")
                p[7] = jsonValue("[410,585]")
                p[8] = jsonValue("[445,704]")
                p[9] = jsonValue("[395,573]")
                p[10] = jsonValue("[430,692]")
                f.equipment = jsonValue("[[\"bench\",[300,510,435,715]],[\"table\",[370,385,525,715]]]")
                p[3] = jsonValue("[390,365]")
                p[5] = jsonValue("[377,354]")
                p[4] = arrayOf<dynamic>(jsSubtract(480, jsMultiply(40, a)), jsSubtract(452, jsMultiply(92, a)))
                p[6] = arrayOf<dynamic>(jsSubtract(466, jsMultiply(40, a)), jsSubtract(440, jsMultiply(92, a)))
            }
            return f
        }
        row = fun(a: dynamic, arg374: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg374) { js("({})") }
            val f: dynamic = hinge(1)
            val p: dynamic = f.p
            p[4] = mix(arrayOf<dynamic>(p[1][0], jsAdd(p[1][1], 205)), arrayOf<dynamic>(jsAdd(p[2][0], 15), jsAdd(p[2][1], 10)), a)
            p[6] = add23(p[4], jsonValue("[-15,-12]"))
            p[3] = ik(p[1], p[4], 112, (-numeric(1)))
            p[5] = ik(p[1], p[6], 112, (-numeric(1)))
            return f
        }
        cableRow = fun(a: dynamic, arg385: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg385) { js("({})") }
            val f: dynamic = R.standing()
            val p: dynamic = f.p
            if (truth(`param`.seated)) {
                p[2] = jsonValue("[320,515]")
                p[1] = jsonValue("[315,340]")
                p[0] = jsonValue("[315,280]")
                p[7] = jsonValue("[435,575]")
                p[8] = jsonValue("[555,620]")
                p[9] = jsonValue("[422,562]")
                p[10] = jsonValue("[542,607]")
                f.equipment = jsonValue("[[\"bench\",[260,545,370,715]]]")
            }
            p[4] = arrayOf<dynamic>(jsSubtract(jsAdd(p[1][0], 195), jsMultiply(135, a)), jsAdd(p[1][1], (if (truth(`param`.high)) 40 else 105)))
            p[6] = arrayOf<dynamic>(jsSubtract(p[4][0], 12), jsSubtract(p[4][1], 12))
            p[3] = ik(p[1], p[4], 115, (-numeric(1)))
            p[5] = ik(p[1], p[6], 115, (-numeric(1)))
            return f
        }
        pulldown = fun(a: dynamic, arg412: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg412) { js("({})") }
            val f: dynamic = front()
            val p: dynamic = f.p
            p[2] = jsonValue("[360,465]")
            p[7] = jsonValue("[300,565]")
            p[8] = jsonValue("[300,704]")
            p[9] = jsonValue("[420,565]")
            p[10] = jsonValue("[420,704]")
            p[4] = arrayOf<dynamic>((if (truth(`param`.wide)) 215 else 300), jsAdd(125, jsMultiply(210, a)))
            p[6] = arrayOf<dynamic>((if (truth(`param`.wide)) 505 else 420), jsAdd(125, jsMultiply(210, a)))
            p[3] = arrayOf<dynamic>(jsSubtract(270, jsMultiply(30, a)), jsAdd(220, jsMultiply(150, a)))
            p[5] = arrayOf<dynamic>(jsAdd(450, jsMultiply(30, a)), jsAdd(220, jsMultiply(150, a)))
            f.equipment = jsonValue("[[\"bench\",[260,495,460,715]]]")
            return f
        }
        fly = fun(a: dynamic, arg435: dynamic, arg436: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg435) { js("({})") }
            var gym: dynamic = absent
            gym = definedOr(arg436) { true }
            var f: dynamic = absent
            if (truth((!truth(gym)))) {
                f = front()
                f.p[0] = jsonValue("[360,220]")
                f.p[1] = jsonValue("[360,285]")
                f.p[2] = jsonValue("[360,465]")
                f.p[7] = jsonValue("[320,555]")
                f.p[8] = jsonValue("[320,690]")
                f.p[9] = jsonValue("[400,555]")
                f.p[10] = jsonValue("[400,690]")
                f.p[3] = arrayOf<dynamic>(jsAdd(255, jsMultiply(70, a)), 290)
                f.p[4] = arrayOf<dynamic>(jsAdd(165, jsMultiply(180, a)), 280)
                f.p[5] = arrayOf<dynamic>(jsSubtract(465, jsMultiply(70, a)), 290)
                f.p[6] = arrayOf<dynamic>(jsSubtract(555, jsMultiply(180, a)), 280)
                f.detail.supineTop23 = true
                return f
            }
            f = front()
            val p: dynamic = f.p
            val y: dynamic = (if (truth(`param`.low)) jsSubtract(370, jsMultiply(85, a)) else (if (truth(`param`.high)) jsAdd(250, jsMultiply(105, a)) else 310))
            if (truth(`param`.seated)) {
                p[2][1] = 475
                p[7] = jsonValue("[300,565]")
                p[8] = jsonValue("[300,704]")
                p[9] = jsonValue("[420,565]")
                p[10] = jsonValue("[420,704]")
                f.equipment = jsonValue("[[\"bench\",[260,510,460,715]]]")
            }
            p[3] = arrayOf<dynamic>(jsAdd(255, jsMultiply(65, a)), jsAdd(y, 15))
            p[4] = arrayOf<dynamic>(jsAdd(180, jsMultiply(168, a)), y)
            p[5] = arrayOf<dynamic>(jsSubtract(465, jsMultiply(65, a)), jsAdd(y, 15))
            p[6] = arrayOf<dynamic>(jsSubtract(540, jsMultiply(168, a)), y)
            return f
        }
        tricep = fun(a: dynamic, arg483: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg483) { js("({})") }
            val f: dynamic = R.standing()
            val p: dynamic = f.p
            p[3] = jsonValue("[365,370]")
            p[5] = jsonValue("[352,358]")
            p[4] = arrayOf<dynamic>(jsSubtract(430, jsMultiply(35, a)), jsAdd(340, jsMultiply(120, a)))
            p[6] = arrayOf<dynamic>(jsSubtract(417, jsMultiply(35, a)), jsAdd(328, jsMultiply(120, a)))
            if (truth(`param`.overhead)) {
                p[3] = jsonValue("[360,208]")
                p[5] = jsonValue("[347,196]")
                p[4] = arrayOf<dynamic>(jsAdd(320, jsMultiply(35, a)), jsSubtract(285, jsMultiply(180, a)))
                p[6] = arrayOf<dynamic>(jsAdd(307, jsMultiply(35, a)), jsSubtract(273, jsMultiply(180, a)))
            }
            return f
        }
        seatedLeg = fun(a: dynamic, arg502: dynamic, arg503: dynamic): dynamic {
            var `param`: dynamic = absent
            `param` = definedOr(arg502) { js("({})") }
            var ham: dynamic = absent
            ham = definedOr(arg503) { false }
            val f: dynamic = R.standing()
            val p: dynamic = f.p
            p[0] = jsonValue("[280,275]")
            p[1] = jsonValue("[285,338]")
            p[2] = jsonValue("[305,510]")
            p[7] = jsonValue("[460,510]")
            p[9] = jsonValue("[450,497]")
            val angle: dynamic = (if (truth(ham)) jsAdd((-numeric(0.15)), jsMultiply(1.35, a)) else jsSubtract(1.45, jsMultiply(1.4, a)))
            p[8] = arrayOf<dynamic>(jsAdd(460, jsMultiply(145, global_Math.cos(angle))), jsAdd(510, jsMultiply(145, global_Math.sin(angle))))
            p[10] = arrayOf<dynamic>(jsAdd(450, jsMultiply(145, global_Math.cos(angle))), jsAdd(497, jsMultiply(145, global_Math.sin(angle))))
            p[3] = jsonValue("[300,440]")
            p[4] = jsonValue("[330,525]")
            p[5] = jsonValue("[287,428]")
            p[6] = jsonValue("[317,513]")
            f.equipment = jsonValue("[[\"chair\",[220,535,465,715]]]")
            return f
        }
        legpress = fun(a: dynamic): dynamic {
            val f: dynamic = R.standing()
            val p: dynamic = f.p
            p[0] = jsonValue("[210,350]")
            p[1] = jsonValue("[245,400]")
            p[2] = jsonValue("[320,535]")
            val ankle: dynamic = arrayOf<dynamic>(jsAdd(585, jsMultiply(85, a)), 490)
            p[7] = ik(p[2], ankle, 190, 1)
            p[8] = ankle
            p[9] = add23(p[7], jsonValue("[-12,-12]"))
            p[10] = add23(ankle, jsonValue("[-12,-12]"))
            p[3] = jsonValue("[245,465]")
            p[4] = jsonValue("[287,548]")
            p[5] = jsonValue("[232,453]")
            p[6] = jsonValue("[274,536]")
            f.equipment = jsonValue("[[\"incline\",[150,435,355,710]]]")
            f.detail.footAxis23 = jsonValue("[0,-1]")
            f.detail.plate23 = ankle
            return f
        }
        add23 = fun(p: dynamic, delta: dynamic): dynamic {
            return p.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsAdd(v, delta[i])
            }))
        }
        modelFrame = fun(e: dynamic, t: dynamic): dynamic {
            var f: dynamic = absent
            val a: dynamic = wave(t)
            val k: dynamic = e.recipe23
            val `param`: dynamic = e.param23
            if (truth((k === "push"))) {
                if (truth(`param`.tap)) {
                    val flex: dynamic = (if (truth(jsLess(t, 0.6))) wave(jsDivide(t, 0.6)) else 0)
                    f = push(flex, `param`)
                    val tap: dynamic = (if (truth(jsAtLeast(t, 0.6))) wave(jsDivide(jsSubtract(t, 0.6), 0.4)) else 0)
                    f.p[6] = mix(f.p[6], jsonValue("[260,440]"), tap)
                    f.p[5] = ik(f.p[1], f.p[6], 105, (-numeric(1)))
                } else {
                    if (truth(`param`.power)) {
                        val flex: dynamic = (if (truth(jsLess(t, 0.7))) wave(jsDivide(t, 0.7)) else 0)
                        f = push(flex, `param`)
                        val flight: dynamic = (if (truth(jsAtLeast(t, 0.7))) wave(jsDivide(jsSubtract(t, 0.7), 0.3)) else 0)
                        move(f, jsonValue("[0,1,2,3,4,5,6,7,9]"), 0, jsMultiply((-numeric(35)), flight))
                    } else {
                        f = (if (truth(jsOr(jsOr((`param`.grip === "diamond")) { `param`.stagger }) { `param`.assistedOne })) topPush(a, `param`) else push(a, `param`))
                    }
                }
            } else {
                if (truth((k === "pike"))) {
                    f = push(a, `param`)
                    f.p[2] = arrayOf<dynamic>(425, jsAdd(345, jsMultiply(28, a)))
                    f.p[7] = mix(f.p[2], f.p[8], 0.5)
                    f.p[9] = mix(f.p[2], f.p[10], 0.5)
                    if (truth(`param`.bench)) {
                        run {
                            val target572: dynamic = f.p[8]
                            val key573: dynamic = 1
                            target572[key573] = jsSubtract(target572[key573], 95)
                        }
                        run {
                            val target574: dynamic = f.p[10]
                            val key575: dynamic = 1
                            target574[key575] = jsSubtract(target574[key575], 95)
                        }
                        f.equipment = jsonValue("[[\"bench\",[620,480,735,715]]]")
                    }
                } else {
                    if (truth((k === "lunge"))) {
                        f = (if (truth(`param`.cross)) curtsy(a) else R.lunge(a, (!truth(`param`.forward)), (!truth((!truth(`param`.db))))))
                    } else {
                        if (truth((k === "squat-lunge"))) {
                            f = (if (truth(jsLess(t, 0.5))) squat(wave(jsMultiply(t, 2))) else R.lunge(wave(jsMultiply(jsSubtract(t, 0.5), 2))))
                        } else {
                            if (truth((k === "squat"))) {
                                f = squat(a, `param`)
                                if (truth(`param`.hack)) {
                                    f.p[0] = arrayOf<dynamic>(jsSubtract(275, jsMultiply(45, a)), jsAdd(245, jsMultiply(105, a)))
                                    f.p[1] = arrayOf<dynamic>(jsSubtract(305, jsMultiply(45, a)), jsAdd(305, jsMultiply(105, a)))
                                    f.p[2] = arrayOf<dynamic>(jsSubtract(365, jsMultiply(45, a)), jsAdd(455, jsMultiply(105, a)))
                                    f.p[8] = jsonValue("[535,704]")
                                    f.p[10] = jsonValue("[520,692]")
                                    f.p[7] = ik(f.p[2], f.p[8], 165, 1)
                                    f.p[9] = ik(f.p[2], f.p[10], 165, 1)
                                    f.p[4] = arrayOf<dynamic>(jsAdd(f.p[1][0], 28), jsAdd(f.p[1][1], 30))
                                    f.p[6] = add23(f.p[4], jsonValue("[-13,12]"))
                                    f.p[3] = ik(f.p[1], f.p[4], 100, 1)
                                    f.p[5] = ik(f.p[1], f.p[6], 100, 1)
                                    f.detail.hack23 = true
                                    f.equipment = jsonValue("[[\"incline\",[150,300,365,715]]]")
                                }
                                if (truth(jsOr(`param`.back) { `param`.front })) {
                                    f.p[4] = arrayOf<dynamic>(jsAdd(f.p[1][0], 24), jsAdd(f.p[1][1], 10))
                                    f.p[6] = add23(f.p[4], jsonValue("[-15,12]"))
                                }
                            } else {
                                if (truth((k === "single-squat"))) {
                                    f = squat(a, `param`)
                                    if (truth(`param`.rear)) {
                                        f.p[9] = arrayOf<dynamic>(jsSubtract(f.p[2][0], 95), jsAdd(f.p[2][1], 80))
                                        f.p[10] = arrayOf<dynamic>(jsSubtract(f.p[9][0], 40), jsAdd(f.p[9][1], 80))
                                    } else {
                                        f.p[10] = arrayOf<dynamic>(jsAdd(f.p[2][0], 295), jsAdd(f.p[2][1], 30))
                                        f.p[9] = mix(f.p[2], f.p[10], 0.5)
                                    }
                                    if (truth(`param`.support)) {
                                        f.equipment.push(jsonValue("[\"wall\",[520,270,530,715]]"))
                                        f.p[6] = arrayOf<dynamic>(520, jsAdd(400, jsMultiply(30, a)))
                                        f.p[5] = ik(f.p[1], f.p[6], 112, (-numeric(1)))
                                    }
                                    if (truth(`param`.db)) {
                                        f.p[4] = arrayOf<dynamic>(jsAdd(f.p[1][0], 100), jsAdd(f.p[1][1], 30))
                                        f.p[3] = ik(f.p[1], f.p[4], 100, (-numeric(1)))
                                    }
                                } else {
                                    if (truth((k === "lateral-lunge"))) {
                                        f = front()
                                        move(f, jsonValue("[0,1,2,3,4,5,6]"), jsMultiply((-numeric(80)), a), jsMultiply(110, a))
                                        f.p[8] = jsonValue("[180,704]")
                                        f.p[10] = jsonValue("[560,704]")
                                        f.p[7] = ik(f.p[2], f.p[8], 165, (-numeric(1)))
                                        f.p[9] = mix(f.p[2], f.p[10], 0.5)
                                    } else {
                                        if (truth((k === "step"))) {
                                            f = stepFront(a, `param`)
                                        } else {
                                            if (truth(jsOr((k === "bridge")) { (k === "hipthrust") })) {
                                                f = bridge(a, `param`)
                                                if (truth((k === "hipthrust"))) {
                                                    f.p[0] = jsonValue("[185,425]")
                                                    f.p[1] = jsonValue("[250,450]")
                                                    f.p[2] = arrayOf<dynamic>(420, jsSubtract(565, jsMultiply(110, a)))
                                                    f.p[3] = jsonValue("[300,490]")
                                                    f.p[4] = arrayOf<dynamic>(jsSubtract(f.p[2][0], 10), f.p[2][1])
                                                    f.p[5] = jsonValue("[288,478]")
                                                    f.p[6] = add23(f.p[4], jsonValue("[-12,-12]"))
                                                    f.equipment = jsonValue("[[\"bench\",[150,490,300,644]]]")
                                                }
                                            } else {
                                                if (truth((k === "ham-slide"))) {
                                                    f = bridge(0.75, `param`)
                                                    run {
                                                        val target648: dynamic = f.p[8]
                                                        val key649: dynamic = 0
                                                        target648[key649] = jsAdd(target648[key649], jsMultiply(110, jsSubtract(1, a)))
                                                    }
                                                    run {
                                                        val target650: dynamic = f.p[10]
                                                        val key651: dynamic = 0
                                                        target650[key651] = jsAdd(target650[key651], jsMultiply(110, jsSubtract(1, a)))
                                                    }
                                                    f.p[7] = ik(f.p[2], f.p[8], 155, (-numeric(1)))
                                                    f.p[9] = ik(f.p[2], f.p[10], 155, (-numeric(1)))
                                                    if (truth(`param`.single)) {
                                                        f.p[9] = jsonValue("[530,400]")
                                                        f.p[10] = jsonValue("[650,350]")
                                                    }
                                                } else {
                                                    if (truth((k === "reverse-plank"))) {
                                                        f = reversePlank((if (truth(`param`.knee)) 1 else a), (if (truth(`param`.knee)) js("({})") else `param`))
                                                        if (truth(`param`.knee)) {
                                                            if (true) {
                                                                val iterator660: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(7, 8, global_Math.max(0, global_Math.sin(jsMultiply(t, 6.283))), 0), arrayOf<dynamic>(9, 10, global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(t, 6.283))))), (-numeric(12)))))
                                                                while (true) {
                                                                    val next661: dynamic = iterator660.next()
                                                                    if (truth(next661.done)) break
                                                                    var knee: dynamic = absent
                                                                    var foot: dynamic = absent
                                                                    var u: dynamic = absent
                                                                    var o: dynamic = absent
                                                                    val unpacked662: dynamic = fromIterable(next661.value)
                                                                    knee = unpacked662[0]
                                                                    foot = unpacked662[1]
                                                                    u = unpacked662[2]
                                                                    o = unpacked662[3]
                                                                    f.p[knee] = mix(f.p[knee], arrayOf<dynamic>(jsAdd(f.p[2][0], 105), jsAdd(jsSubtract(f.p[2][1], 75), o)), u)
                                                                    f.p[foot] = mix(f.p[foot], arrayOf<dynamic>(jsAdd(f.p[2][0], 150), jsAdd(jsAdd(f.p[2][1], 70), o)), u)
                                                                }
                                                            }
                                                        }
                                                    } else {
                                                        if (truth((k === "hollow"))) {
                                                            f = hollow((if (truth(`param`.rock)) 0.65 else a), (if (truth(`param`.rock)) js("({})") else `param`))
                                                            if (truth(`param`.rock)) {
                                                                val origin: dynamic = f.p[2]
                                                                val theta: dynamic = jsMultiply(0.12, jsSubtract(jsMultiply(2, a), 1))
                                                                f.p = f.p.map((fun(p: dynamic): dynamic {
                                                                    val x: dynamic = jsSubtract(p[0], origin[0])
                                                                    val y: dynamic = jsSubtract(p[1], origin[1])
                                                                    return arrayOf<dynamic>(jsSubtract(jsAdd(origin[0], jsMultiply(x, global_Math.cos(theta))), jsMultiply(y, global_Math.sin(theta))), jsAdd(jsAdd(origin[1], jsMultiply(x, global_Math.sin(theta))), jsMultiply(y, global_Math.cos(theta))))
                                                                }))
                                                            }
                                                        } else {
                                                            if (truth((k === "legraise"))) {
                                                                f = legraise((if (truth(`param`.scissors)) 0.35 else a), `param`, t)
                                                            } else {
                                                                if (truth((k === "knee-tuck"))) {
                                                                    f = seatedLeg(a)
                                                                    f.equipment = js("[]")
                                                                    f.p[2] = jsonValue("[305,590]")
                                                                    f.p[1] = jsonValue("[270,430]")
                                                                    f.p[0] = jsonValue("[245,370]")
                                                                    f.p[4] = jsonValue("[255,610]")
                                                                    f.p[6] = jsonValue("[242,598]")
                                                                    f.p[3] = jsonValue("[265,525]")
                                                                    f.p[5] = jsonValue("[252,513]")
                                                                    f.p[7] = arrayOf<dynamic>(jsSubtract(450, jsMultiply(55, a)), jsSubtract(570, jsMultiply(95, a)))
                                                                    f.p[8] = arrayOf<dynamic>(jsSubtract(585, jsMultiply(120, a)), jsSubtract(602, jsMultiply(90, a)))
                                                                    f.p[9] = add23(f.p[7], jsonValue("[-12,-12]"))
                                                                    f.p[10] = add23(f.p[8], jsonValue("[-12,-12]"))
                                                                    f.detail.floor23 = 628
                                                                } else {
                                                                    if (truth((k === "side-plank"))) {
                                                                        f = R.sidePlank(true, 1)
                                                                        if (truth(`param`.knee)) {
                                                                            f.p[9] = mix(f.p[9], jsonValue("[475,405]"), a)
                                                                            f.p[10] = mix(f.p[10], jsonValue("[575,450]"), a)
                                                                        }
                                                                        if (truth(`param`.thread)) {
                                                                            f.p[6] = mix(f.p[6], jsonValue("[175,555]"), a)
                                                                            f.p[5] = ik(f.p[1], f.p[6], 112, (-numeric(1)))
                                                                        }
                                                                    } else {
                                                                        if (truth(jsOr((k === "plank")) { (k === "renegade") })) {
                                                                            f = push(0)
                                                                            if (truth((k === "renegade"))) {
                                                                                val left: dynamic = global_Math.max(0, global_Math.sin(jsMultiply(t, 6.283)))
                                                                                val right: dynamic = global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(t, 6.283)))))
                                                                                if (true) {
                                                                                    val iterator703: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, 4, left, 0), arrayOf<dynamic>(5, 6, right, (-numeric(12)))))
                                                                                    while (true) {
                                                                                        val next704: dynamic = iterator703.next()
                                                                                        if (truth(next704.done)) break
                                                                                        var elbow: dynamic = absent
                                                                                        var hand: dynamic = absent
                                                                                        var u: dynamic = absent
                                                                                        var offset: dynamic = absent
                                                                                        val unpacked705: dynamic = fromIterable(next704.value)
                                                                                        elbow = unpacked705[0]
                                                                                        hand = unpacked705[1]
                                                                                        u = unpacked705[2]
                                                                                        offset = unpacked705[3]
                                                                                        f.p[hand] = mix(f.p[hand], arrayOf<dynamic>(405, jsAdd(430, offset)), u)
                                                                                        f.p[elbow] = ik(f.p[1], f.p[hand], 108, (-numeric(1)))
                                                                                    }
                                                                                }
                                                                                if (truth(`param`.knees)) {
                                                                                    f.p[7] = jsonValue("[495,610]")
                                                                                    f.p[8] = jsonValue("[620,585]")
                                                                                    f.p[9] = jsonValue("[482,598]")
                                                                                    f.p[10] = jsonValue("[607,573]")
                                                                                }
                                                                            }
                                                                            if (truth(`param`.saw)) {
                                                                                f.p[1] = arrayOf<dynamic>(jsAdd(245, jsMultiply(28, a)), 490)
                                                                                f.p[0] = arrayOf<dynamic>(jsAdd(190, jsMultiply(28, a)), 470)
                                                                                f.p[2] = arrayOf<dynamic>(jsAdd(430, jsMultiply(28, a)), 535)
                                                                                f.p[3] = jsonValue("[260,620]")
                                                                                f.p[4] = jsonValue("[160,620]")
                                                                                f.p[5] = jsonValue("[245,608]")
                                                                                f.p[6] = jsonValue("[145,608]")
                                                                                f.p[7] = mix(f.p[2], f.p[8], 0.5)
                                                                                f.p[9] = mix(f.p[2], f.p[10], 0.5)
                                                                                f.detail.floor23 = 638
                                                                            }
                                                                            if (truth(`param`.toe)) {
                                                                                run {
                                                                                    val target738: dynamic = f.p[10]
                                                                                    val key739: dynamic = 0
                                                                                    target738[key739] = jsAdd(target738[key739], jsMultiply(45, a))
                                                                                }
                                                                            }
                                                                        } else {
                                                                            if (truth((k === "bear"))) {
                                                                                f = R.cat(0.5)
                                                                                run {
                                                                                    val target740: dynamic = f.p[7]
                                                                                    val key741: dynamic = 1
                                                                                    target740[key741] = jsSubtract(target740[key741], 18)
                                                                                }
                                                                                run {
                                                                                    val target742: dynamic = f.p[9]
                                                                                    val key743: dynamic = 1
                                                                                    target742[key743] = jsSubtract(target742[key743], 18)
                                                                                }
                                                                                removeKey(f.detail, "spineCurve23")
                                                                                if (truth(`param`.tap)) {
                                                                                    f.p[6] = mix(f.p[6], jsonValue("[250,430]"), a)
                                                                                    f.p[5] = ik(f.p[1], f.p[6], 105, (-numeric(1)))
                                                                                }
                                                                                if (truth(`param`.walk)) {
                                                                                    run {
                                                                                        val target748: dynamic = f.p[4]
                                                                                        val key749: dynamic = 0
                                                                                        target748[key749] = jsAdd(target748[key749], jsMultiply(35, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))))
                                                                                    }
                                                                                    run {
                                                                                        val target750: dynamic = f.p[8]
                                                                                        val key751: dynamic = 0
                                                                                        target750[key751] = jsSubtract(target750[key751], jsMultiply(35, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))))
                                                                                    }
                                                                                }
                                                                            } else {
                                                                                if (truth((k === "thruster"))) {
                                                                                    val down: dynamic = (if (truth(jsLess(t, jsDivide(1, 3)))) wave(jsMultiply(t, 1.5)) else (if (truth(jsLess(t, jsDivide(2, 3)))) jsSubtract(1, wave(jsMultiply(jsSubtract(t, jsDivide(1, 3)), 1.5))) else 0))
                                                                                    val press: dynamic = (if (truth(jsLess(t, jsDivide(1, 3)))) 0 else (if (truth(jsLess(t, jsDivide(2, 3)))) wave(jsMultiply(jsSubtract(t, jsDivide(1, 3)), 1.5)) else jsSubtract(1, wave(jsMultiply(jsSubtract(t, jsDivide(2, 3)), 1.5)))))
                                                                                    f = squat(down)
                                                                                    f.p[4] = arrayOf<dynamic>(jsAdd(f.p[1][0], 22), jsSubtract(jsAdd(f.p[1][1], 20), jsMultiply(170, press)))
                                                                                    f.p[6] = add23(f.p[4], jsonValue("[-15,12]"))
                                                                                    f.p[3] = ik(f.p[1], f.p[4], 110, (-numeric(1)))
                                                                                    f.p[5] = ik(f.p[1], f.p[6], 110, (-numeric(1)))
                                                                                } else {
                                                                                    if (truth((k === "clean"))) {
                                                                                        f = hinge(jsSubtract(1, a))
                                                                                        f.p[4] = mix(f.p[4], jsonValue("[350,275]"), a)
                                                                                        f.p[6] = mix(f.p[6], jsonValue("[338,287]"), a)
                                                                                        f.p[3] = ik(f.p[1], f.p[4], 110, 1)
                                                                                        f.p[5] = ik(f.p[1], f.p[6], 110, 1)
                                                                                    } else {
                                                                                        if (truth(jsOr((k === "hinge")) { (k === "deadlift") })) {
                                                                                            f = hinge(a)
                                                                                            if (truth(`param`.airplane)) {
                                                                                                f.p[10] = arrayOf<dynamic>(jsSubtract(f.p[2][0], jsMultiply(160, a)), jsSubtract(704, jsMultiply(255, a)))
                                                                                                f.p[9] = mix(f.p[2], f.p[10], 0.5)
                                                                                                f.p[6] = jsonValue("[515,450]")
                                                                                                f.p[5] = ik(f.p[1], f.p[6], 110, 1)
                                                                                                f.equipment = jsonValue("[[\"wall\",[540,300,550,715]]]")
                                                                                            }
                                                                                            if (truth(`param`.wide)) {
                                                                                                val side: dynamic = hinge(a)
                                                                                                f = front()
                                                                                                run {
                                                                                                    val target778: dynamic = f.p[0]
                                                                                                    val key779: dynamic = 1
                                                                                                    target778[key779] = jsAdd(target778[key779], jsMultiply(135, a))
                                                                                                }
                                                                                                run {
                                                                                                    val target780: dynamic = f.p[1]
                                                                                                    val key781: dynamic = 1
                                                                                                    target780[key781] = jsAdd(target780[key781], jsMultiply(120, a))
                                                                                                }
                                                                                                run {
                                                                                                    val target782: dynamic = f.p[2]
                                                                                                    val key783: dynamic = 1
                                                                                                    target782[key783] = jsAdd(target782[key783], jsMultiply(40, a))
                                                                                                }
                                                                                                f.p[8] = jsonValue("[215,704]")
                                                                                                f.p[10] = jsonValue("[505,704]")
                                                                                                f.p[7] = arrayOf<dynamic>(230, jsAdd(560, jsMultiply(25, a)))
                                                                                                f.p[9] = arrayOf<dynamic>(490, jsAdd(560, jsMultiply(25, a)))
                                                                                                f.p[4] = arrayOf<dynamic>(305, jsAdd(470, jsMultiply(155, a)))
                                                                                                f.p[6] = arrayOf<dynamic>(415, jsAdd(470, jsMultiply(155, a)))
                                                                                                f.p[3] = mix(f.p[1], f.p[4], 0.5)
                                                                                                f.p[5] = mix(f.p[1], f.p[6], 0.5)
                                                                                            }
                                                                                            if (truth(jsAnd(`param`.floorStart) { (!truth(`param`.wide)) })) {
                                                                                                run {
                                                                                                    val target800: dynamic = f.p[2]
                                                                                                    val key801: dynamic = 1
                                                                                                    target800[key801] = jsAdd(target800[key801], jsMultiply(35, a))
                                                                                                }
                                                                                                run {
                                                                                                    val target802: dynamic = f.p[4]
                                                                                                    val key803: dynamic = 1
                                                                                                    target802[key803] = jsAdd(target802[key803], jsMultiply(55, a))
                                                                                                }
                                                                                                run {
                                                                                                    val target804: dynamic = f.p[6]
                                                                                                    val key805: dynamic = 1
                                                                                                    target804[key805] = jsAdd(target804[key805], jsMultiply(55, a))
                                                                                                }
                                                                                                f.p[7] = ik(f.p[2], f.p[8], 155, 1)
                                                                                                f.p[9] = ik(f.p[2], f.p[10], 155, 1)
                                                                                            }
                                                                                        } else {
                                                                                            if (truth((k === "row"))) {
                                                                                                f = row(a, `param`)
                                                                                                if (truth(`param`.floorStart)) {
                                                                                                    f.p[2] = jsonValue("[300,445]")
                                                                                                    f.p[1] = jsonValue("[455,445]")
                                                                                                    f.p[0] = jsonValue("[510,420]")
                                                                                                    f.p[4] = mix(jsonValue("[445,675]"), jsonValue("[320,450]"), a)
                                                                                                    f.p[6] = add23(f.p[4], jsonValue("[-12,-12]"))
                                                                                                    f.p[3] = ik(f.p[1], f.p[4], 122, (-numeric(1)))
                                                                                                    f.p[5] = ik(f.p[1], f.p[6], 122, (-numeric(1)))
                                                                                                }
                                                                                            } else {
                                                                                                if (truth((k === "bench"))) {
                                                                                                    f = bench(a, `param`)
                                                                                                } else {
                                                                                                    if (truth((k === "overhead"))) {
                                                                                                        f = overhead(a, `param`)
                                                                                                    } else {
                                                                                                        if (truth((k === "curl"))) {
                                                                                                            f = curl(a, `param`)
                                                                                                        } else {
                                                                                                            if (truth((k === "cable-row"))) {
                                                                                                                f = cableRow(a, `param`)
                                                                                                            } else {
                                                                                                                if (truth((k === "erg-row"))) {
                                                                                                                    f = R.standing()
                                                                                                                    val hip: dynamic = arrayOf<dynamic>(jsSubtract(375, jsMultiply(125, a)), 535)
                                                                                                                    val pull: dynamic = global_Math.max(0, jsDivide(jsSubtract(a, 0.55), 0.45))
                                                                                                                    f.p[2] = hip
                                                                                                                    f.p[1] = arrayOf<dynamic>(jsSubtract(jsAdd(hip[0], 10), jsMultiply(35, pull)), 370)
                                                                                                                    f.p[0] = arrayOf<dynamic>(f.p[1][0], 310)
                                                                                                                    f.p[8] = jsonValue("[575,580]")
                                                                                                                    f.p[10] = jsonValue("[563,568]")
                                                                                                                    f.p[7] = ik(hip, f.p[8], 172, 1)
                                                                                                                    f.p[9] = ik(hip, f.p[10], 172, 1)
                                                                                                                    f.p[4] = arrayOf<dynamic>(jsSubtract(jsAdd(f.p[1][0], 208), jsMultiply(100, pull)), jsAdd(430, jsMultiply(15, pull)))
                                                                                                                    f.p[6] = add23(f.p[4], jsonValue("[-12,-12]"))
                                                                                                                    f.p[3] = ik(f.p[1], f.p[4], 112, (-numeric(1)))
                                                                                                                    f.p[5] = ik(f.p[1], f.p[6], 112, (-numeric(1)))
                                                                                                                    f.equipment = arrayOf<dynamic>(arrayOf<dynamic>("bench", arrayOf<dynamic>(jsSubtract(hip[0], 30), 567, jsAdd(hip[0], 40), 715)))
                                                                                                                } else {
                                                                                                                    if (truth((k === "pulldown"))) {
                                                                                                                        f = pulldown(a, `param`)
                                                                                                                    } else {
                                                                                                                        if (truth((k === "fly"))) {
                                                                                                                            f = fly(a, `param`, e.gymOnly23)
                                                                                                                        } else {
                                                                                                                            if (truth((k === "tricep"))) {
                                                                                                                                f = tricep(a, `param`)
                                                                                                                            } else {
                                                                                                                                if (truth(jsAnd((k === "hamcurl")) { `param`.lying })) {
                                                                                                                                    f = push(0)
                                                                                                                                    f.p[1] = jsonValue("[245,430]")
                                                                                                                                    f.p[2] = jsonValue("[430,435]")
                                                                                                                                    f.p[7] = jsonValue("[570,440]")
                                                                                                                                    f.p[8] = arrayOf<dynamic>(jsAdd(570, jsMultiply(140, global_Math.cos(jsMultiply(a, 1.4)))), jsSubtract(440, jsMultiply(140, global_Math.sin(jsMultiply(a, 1.4)))))
                                                                                                                                    f.p[9] = jsonValue("[557,427]")
                                                                                                                                    f.p[10] = arrayOf<dynamic>(jsAdd(557, jsMultiply(140, global_Math.cos(jsMultiply(a, 1.4)))), jsSubtract(427, jsMultiply(140, global_Math.sin(jsMultiply(a, 1.4)))))
                                                                                                                                    f.p[4] = jsonValue("[185,455]")
                                                                                                                                    f.p[6] = jsonValue("[174,444]")
                                                                                                                                    f.p[3] = jsonValue("[230,485]")
                                                                                                                                    f.p[5] = jsonValue("[219,474]")
                                                                                                                                    f.equipment = jsonValue("[[\"bench\",[150,472,610,715]]]")
                                                                                                                                } else {
                                                                                                                                    if (truth(jsOr((k === "leg-extension")) { (k === "hamcurl") })) {
                                                                                                                                        f = seatedLeg(a, `param`, (k === "hamcurl"))
                                                                                                                                    } else {
                                                                                                                                        if (truth((k === "legpress"))) {
                                                                                                                                            f = legpress(a)
                                                                                                                                        } else {
                                                                                                                                            if (truth((k === "calf"))) {
                                                                                                                                                f = R.standing()
                                                                                                                                                move(f, jsonValue("[0,1,2,3,4,5,6,7,9]"), 0, jsMultiply((-numeric(25)), a))
                                                                                                                                                run {
                                                                                                                                                    val target870: dynamic = f.p[8]
                                                                                                                                                    val key871: dynamic = 1
                                                                                                                                                    target870[key871] = jsSubtract(target870[key871], jsMultiply(18, a))
                                                                                                                                                }
                                                                                                                                                run {
                                                                                                                                                    val target872: dynamic = f.p[10]
                                                                                                                                                    val key873: dynamic = 1
                                                                                                                                                    target872[key873] = jsSubtract(target872[key873], jsMultiply(18, a))
                                                                                                                                                }
                                                                                                                                            } else {
                                                                                                                                                if (truth((k === "hip-machine"))) {
                                                                                                                                                    f = front()
                                                                                                                                                    f.p[2][1] = 465
                                                                                                                                                    f.p[7] = arrayOf<dynamic>(jsSubtract(315, jsMultiply(65, a)), 570)
                                                                                                                                                    f.p[8] = arrayOf<dynamic>(jsSubtract(305, jsMultiply(65, a)), 704)
                                                                                                                                                    f.p[9] = arrayOf<dynamic>(jsAdd(405, jsMultiply(65, a)), 570)
                                                                                                                                                    f.p[10] = arrayOf<dynamic>(jsAdd(415, jsMultiply(65, a)), 704)
                                                                                                                                                    f.equipment = jsonValue("[[\"bench\",[260,500,460,715]]]")
                                                                                                                                                } else {
                                                                                                                                                    if (truth((k === "machine-press"))) {
                                                                                                                                                        f = cableRow(jsSubtract(1, a), jsonValue("{\"seated\":true}"))
                                                                                                                                                        f.p[7] = jsonValue("[425,515]")
                                                                                                                                                        f.p[8] = jsonValue("[425,704]")
                                                                                                                                                        f.p[9] = jsonValue("[412,503]")
                                                                                                                                                        f.p[10] = jsonValue("[412,692]")
                                                                                                                                                        run {
                                                                                                                                                            val target894: dynamic = f.p[4]
                                                                                                                                                            val key895: dynamic = 1
                                                                                                                                                            target894[key895] = jsSubtract(target894[key895], 50)
                                                                                                                                                        }
                                                                                                                                                        run {
                                                                                                                                                            val target896: dynamic = f.p[6]
                                                                                                                                                            val key897: dynamic = 1
                                                                                                                                                            target896[key897] = jsSubtract(target896[key897], 50)
                                                                                                                                                        }
                                                                                                                                                        f.p[3] = ik(f.p[1], f.p[4], 110, 1)
                                                                                                                                                        f.p[5] = ik(f.p[1], f.p[6], 110, 1)
                                                                                                                                                    } else {
                                                                                                                                                        if (truth((k === "lateral"))) {
                                                                                                                                                            f = front()
                                                                                                                                                            f.p[3] = arrayOf<dynamic>(jsSubtract(300, jsMultiply(75, a)), jsSubtract(370, jsMultiply(75, a)))
                                                                                                                                                            f.p[4] = arrayOf<dynamic>(jsSubtract(285, jsMultiply(145, a)), jsSubtract(475, jsMultiply(175, a)))
                                                                                                                                                            f.p[5] = jsonValue("[420,370]")
                                                                                                                                                            f.p[6] = jsonValue("[435,475]")
                                                                                                                                                        } else {
                                                                                                                                                            if (truth((k === "pallof"))) {
                                                                                                                                                                f = R.standing()
                                                                                                                                                                f.p[4] = arrayOf<dynamic>(jsAdd(395, jsMultiply(150, a)), 350)
                                                                                                                                                                f.p[6] = add23(f.p[4], jsonValue("[-12,-12]"))
                                                                                                                                                                f.p[3] = ik(f.p[1], f.p[4], 110, (-numeric(1)))
                                                                                                                                                                f.p[5] = ik(f.p[1], f.p[6], 110, (-numeric(1)))
                                                                                                                                                            } else {
                                                                                                                                                                if (truth((k === "woodchop"))) {
                                                                                                                                                                    f = front()
                                                                                                                                                                    f.p[4] = arrayOf<dynamic>(jsAdd(285, jsMultiply(180, a)), jsAdd(220, jsMultiply(245, a)))
                                                                                                                                                                    f.p[6] = add23(f.p[4], jsonValue("[20,0]"))
                                                                                                                                                                    f.p[3] = ik(jsonValue("[315,270]"), f.p[4], 125, 1)
                                                                                                                                                                    f.p[5] = ik(jsonValue("[405,270]"), f.p[6], 125, (-numeric(1)))
                                                                                                                                                                    f.detail.footAxis23 = arrayOf<dynamic>(jsMultiply(0.55, a), 1)
                                                                                                                                                                } else {
                                                                                                                                                                    if (truth((k === "face-pull"))) {
                                                                                                                                                                        f = front()
                                                                                                                                                                        f.p[4] = arrayOf<dynamic>(jsSubtract(335, jsMultiply(80, a)), jsSubtract(350, jsMultiply(100, a)))
                                                                                                                                                                        f.p[6] = arrayOf<dynamic>(jsAdd(385, jsMultiply(80, a)), jsSubtract(350, jsMultiply(100, a)))
                                                                                                                                                                        f.p[3] = arrayOf<dynamic>(jsSubtract(290, jsMultiply(55, a)), 345)
                                                                                                                                                                        f.p[5] = arrayOf<dynamic>(jsAdd(430, jsMultiply(55, a)), 345)
                                                                                                                                                                    } else {
                                                                                                                                                                        if (truth((k === "straight-pull"))) {
                                                                                                                                                                            f = R.standing()
                                                                                                                                                                            f.p[4] = arrayOf<dynamic>(jsSubtract(480, jsMultiply(70, a)), jsAdd(235, jsMultiply(225, a)))
                                                                                                                                                                            f.p[6] = add23(f.p[4], jsonValue("[-12,-12]"))
                                                                                                                                                                            f.p[3] = mix(f.p[1], f.p[4], 0.52)
                                                                                                                                                                            f.p[5] = mix(f.p[1], f.p[6], 0.52)
                                                                                                                                                                        } else {
                                                                                                                                                                            if (truth((k === "landmine"))) {
                                                                                                                                                                                f = R.standing()
                                                                                                                                                                                f.p[4] = arrayOf<dynamic>(jsAdd(420, jsMultiply(105, a)), jsSubtract(310, jsMultiply(110, a)))
                                                                                                                                                                                f.p[3] = ik(f.p[1], f.p[4], 112, 1)
                                                                                                                                                                            } else {
                                                                                                                                                                                if (truth((k === "treadmill"))) {
                                                                                                                                                                                    f = R.march(jsMultiply(global_Math.max(0, global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))), 0.6), jsMultiply(global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))))), 0.6))
                                                                                                                                                                                } else {
                                                                                                                                                                                    throw global_Error(jsAdd("No existe rig para ", k))
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
            if (truth(`param`.rack)) {
                f.p[4] = arrayOf<dynamic>(jsAdd(f.p[1][0], 20), jsAdd(f.p[1][1], 20))
                f.p[6] = arrayOf<dynamic>(jsAdd(f.p[1][0], 7), jsAdd(f.p[1][1], 32))
            }
            if (truth(jsAnd(`param`.single) { dynamicValue(jsonValue("[\"cable-row\",\"tricep\"]")).includes(k) })) {
                f.p[5] = arrayOf<dynamic>(jsSubtract(f.p[1][0], 15), jsAdd(f.p[1][1], 105))
                f.p[6] = arrayOf<dynamic>(jsSubtract(f.p[1][0], 15), jsAdd(f.p[1][1], 202))
            }
            if (truth(e.gymOnly23)) {
                f.detail.gear23 = `param`.gear
                f.detail.recipe23 = k
                f.detail.param23 = `param`
                f.equipment.push(jsonValue("[\"bounds23\",[80,80,730,735]]"))
                if (truth((`param`.gear === "cable"))) {
                    f.detail.cable23 = (if (truth((k === "pulldown"))) jsonValue("[360,90]") else (if (truth(`param`.overhead)) jsonValue("[150,690]") else (if (truth(dynamicValue(jsonValue("[\"curl\",\"lateral\"]")).includes(k))) jsonValue("[650,690]") else (if (truth((k === "cable-row"))) arrayOf<dynamic>(650, (if (truth(`param`.high)) 380 else 445)) else jsonValue("[650,240]")))))
                }
            }
            if (truth(`param`.db)) {
                f.loads = arrayOf<dynamic>(cp(f.p[4]), cp(f.p[6]))
            }
            if (truth(e.gymOnly23)) {
                f.loads = js("[]")
            }
            f.detail.param23 = `param`
            f.detail.phase23 = t
            f.detail.family23 = k
            f.detail.reviewed23 = true
            return stage(f, a)
        }
        extras = fun(f: dynamic, accent: dynamic): dynamic {
            var `out`: dynamic = ""
            val d: dynamic = f.detail
            val k: dynamic = d.recipe23
            val p: dynamic = f.p
            if (truth(d.towel23)) {
                run {
                    `out` = jsAdd(`out`, ("<path data-apparatus23=\"towel\" d=\"M" + textOf(pt(p[4])) + "L" + textOf(pt(p[8])) + "L" + textOf(pt(p[6])) + "\" fill=\"none\" stroke=\"" + textOf(accent) + "\" stroke-width=\"5\" stroke-linejoin=\"round\"/>"))
                }
            }
            if (truth((d.gear23 === "cable"))) {
                val anchor: dynamic = d.cable23
                val handle: dynamic = (if (truth(d.param23.single)) p[4] else mix(p[4], p[6], 0.5))
                if (truth((k === "fly"))) {
                    val y: dynamic = (if (truth(d.param23.low)) 690 else (if (truth(d.param23.high)) 120 else 285))
                    run {
                        `out` = jsAdd(`out`, ("<g data-apparatus23=\"dual-cable\" fill=\"none\" stroke=\"#7a9aa7\" stroke-width=\"5\"><path d=\"M80 735V120M650 735V120\"/><circle cx=\"80\" cy=\"" + textOf(y) + "\" r=\"12\"/><circle cx=\"650\" cy=\"" + textOf(y) + "\" r=\"12\"/><path d=\"M80 " + textOf(y) + "L" + textOf(pt(p[4])) + "M650 " + textOf(y) + "L" + textOf(pt(p[6])) + "\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/></g>"))
                    }
                } else {
                    run {
                        `out` = jsAdd(`out`, ("<g data-apparatus23=\"cable\" fill=\"none\" stroke=\"#7a9aa7\" stroke-width=\"5\"><path d=\"M650 735V120H" + textOf(anchor[0]) + "V" + textOf(anchor[1]) + "\"/><circle cx=\"" + textOf(anchor[0]) + "\" cy=\"" + textOf(anchor[1]) + "\" r=\"12\"/><path d=\"M" + textOf(pt(anchor)) + "L" + textOf(pt(handle)) + "\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/><rect x=\"665\" y=\"440\" width=\"42\" height=\"190\" rx=\"5\"/><path d=\"" + textOf((if (truth(d.param23.single)) jsAdd(jsAdd(jsAdd(jsAdd("M", jsSubtract(handle[0], 12)), " "), handle[1]), "h24") else jsAdd(jsAdd(jsAdd("M", pt(p[4])), "L"), pt(p[6])))) + "\" stroke-width=\"8\"/></g>"))
                    }
                }
            }
            if (truth(dynamicValue(jsonValue("[\"machine\",\"smith\"]")).includes(d.gear23))) {
                run {
                    `out` = jsAdd(`out`, ("<g data-apparatus23=\"" + textOf(d.gear23) + "\" fill=\"none\" stroke=\"#7a9aa7\" stroke-width=\"7\"><path d=\"M115 735V170M700 735V170M115 170H700M100 735H715\"/><rect x=\"640\" y=\"420\" width=\"42\" height=\"200\" rx=\"5\"/></g>"))
                }
                if (truth(d.plate23)) {
                    run {
                        `out` = jsAdd(`out`, ("<path d=\"M" + textOf(jsAdd(d.plate23[0], 12)) + " " + textOf(jsSubtract(d.plate23[1], 55)) + "V" + textOf(jsAdd(d.plate23[1], 28)) + "\" stroke=\"" + textOf(accent) + "\" stroke-width=\"12\"/>"))
                    }
                }
                if (truth(d.hack23)) {
                    run {
                        `out` = jsAdd(`out`, ("<path d=\"M230 680L400 210M" + textOf(jsSubtract(p[1][0], 30)) + " " + textOf(jsSubtract(p[1][1], 10)) + "l60-24\" stroke=\"" + textOf(accent) + "\" stroke-width=\"8\" fill=\"none\"/>"))
                    }
                }
                if (truth(dynamicValue(jsonValue("[\"leg-extension\",\"hamcurl\"]")).includes(k))) {
                    run {
                        `out` = jsAdd(`out`, ("<path d=\"M" + textOf(pt(p[8])) + "L" + textOf(pt(p[10])) + "\" stroke=\"#fb927d\" stroke-width=\"18\" stroke-linecap=\"round\"/>"))
                    }
                }
            }
            if (truth((d.gear23 === "cardio"))) {
                run {
                    `out` = jsAdd(`out`, ("<g data-apparatus23=\"cardio\" stroke=\"#7a9aa7\" stroke-width=\"8\" fill=\"none\"><path d=\"M150 724H665M645 724V480H560\"/><rect x=\"553\" y=\"440\" width=\"55\" height=\"36\" rx=\"6\"/>" + textOf((if (truth((k === "erg-row"))) ("<path d=\"M170 590H610M595 532V615M630 550L" + textOf(pt(mix(p[4], p[6], 0.5))) + "\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/>") else ("<path d=\"M155 725H632\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\" stroke-dasharray=\"18 24\" stroke-dashoffset=\"" + textOf(jsMultiply(jsOr(d.phase23) { 0 }, 126)) + "\"/>"))) + "</g>"))
                }
            }
            return `out`
        }
        loads = fun(f: dynamic, view: dynamic, accent: dynamic): dynamic {
            val d: dynamic = f.detail
            val p: dynamic = f.p
            if (truth((!truth(dynamicValue(jsonValue("[\"barbell\",\"smith\"]")).includes(d.gear23))))) {
                return ""
            }
            var q: dynamic = mix(p[4], p[6], 0.5)
            if (truth(d.param23.back)) {
                q = arrayOf<dynamic>(jsSubtract(p[1][0], 8), jsSubtract(p[1][1], 14))
            }
            if (truth(d.param23.front)) {
                q = arrayOf<dynamic>(jsAdd(p[1][0], 20), jsAdd(p[1][1], 20))
            }
            if (truth((d.recipe23 === "hipthrust"))) {
                q = cp(p[2])
            }
            if (truth(d.barCenter31)) {
                q = cp(d.barCenter31)
            }
            if (truth(d.param23.landmine)) {
                return ("<g data-load23=\"landmine\" stroke=\"#7a9aa7\" stroke-width=\"12\"><path d=\"M650 724L" + textOf(pt(q)) + "\"/><circle cx=\"" + textOf(q[0]) + "\" cy=\"" + textOf(q[1]) + "\" r=\"27\" fill=\"#243d50\" stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/></g>")
            }
            val x1: dynamic = (if (truth((view === "front"))) jsSubtract(q[0], 175) else jsSubtract(q[0], 25))
            val x2: dynamic = (if (truth((view === "front"))) jsAdd(q[0], 175) else jsAdd(q[0], 25))
            return ("<g data-load23=\"barbell\" stroke=\"#7a9aa7\" fill=\"#243d50\"><path d=\"M" + textOf(x1) + " " + textOf(q[1]) + "H" + textOf(x2) + "\" stroke-width=\"9\"/><" + textOf((if (truth((view === "front"))) "rect" else "ellipse")) + " " + textOf((if (truth((view === "front"))) ("x=\"" + textOf(x1) + "\" y=\"" + textOf(jsSubtract(q[1], 35)) + "\" width=\"15\" height=\"70\"") else ("cx=\"" + textOf(x1) + "\" cy=\"" + textOf(q[1]) + "\" rx=\"16\" ry=\"32\""))) + " stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/><" + textOf((if (truth((view === "front"))) "rect" else "ellipse")) + " " + textOf((if (truth((view === "front"))) ("x=\"" + textOf(jsSubtract(x2, 15)) + "\" y=\"" + textOf(jsSubtract(q[1], 35)) + "\" width=\"15\" height=\"70\"") else ("cx=\"" + textOf(x2) + "\" cy=\"" + textOf(q[1]) + "\" rx=\"16\" ry=\"32\""))) + " stroke=\"" + textOf(accent) + "\" stroke-width=\"3\"/></g>")
        }
        R = global_Rig2D23
        cp = R.cp
        mix = R.mix
        ik = R.ik
        wave = R.wave
        move = (fun(f: dynamic, indices: dynamic, x: dynamic, y: dynamic): dynamic {
            if (true) {
                val iterator3: dynamic = iteratorOf(indices)
                while (true) {
                    val next4: dynamic = iterator3.next()
                    if (truth(next4.done)) break
                    var k: dynamic = absent
                    k = next4.value
                    run {
                        val target5: dynamic = f.p[k]
                        val key6: dynamic = 0
                        target5[key6] = jsAdd(target5[key6], x)
                    }
                    run {
                        val target7: dynamic = f.p[k]
                        val key8: dynamic = 1
                        target7[key8] = jsAdd(target7[key8], y)
                    }
                }
            }
            return f
        })
        val n: dynamic = (fun(v: dynamic): dynamic {
            return global_Number(v).toFixed(2)
        })
        pt = (fun(p: dynamic): dynamic {
            return p.map(n).join(",")
        })
        stage = (fun(f: dynamic, a: dynamic): dynamic {
            f.detail.stage18 = (if (truth(jsMore(a, 0.85))) "PUNTO DE CONTROL" else (if (truth(jsLess(a, 0.15))) "POSICIÓN INICIAL" else "RECORRIDO CONTROLADO"))
            return f
        })
        val frontRecipes: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"overhead\",\"pulldown\",\"lateral\",\"hip-machine\",\"woodchop\",\"face-pull\",\"lateral-lunge\"]")))
        if (true) {
            val iterator976: dynamic = iteratorOf(global_Engine.exercises.filter((fun(e: dynamic): dynamic {
                return jsAnd(jsAnd((e.variant === "base")) { e.pack23 }) { (!truth(e.pack30)) }
            })))
            while (true) {
                val next977: dynamic = iterator976.next()
                if (truth(next977.done)) break
                var e: dynamic = absent
                e = next977.value
                val m: dynamic = global_MotionDB[e.id]
                val v: dynamic = m.view21
                val `param`: dynamic = e.param23
                val k: dynamic = e.recipe23
                val top: dynamic = jsAnd((k === "push")) { jsOr(jsOr((`param`.grip === "diamond")) { `param`.stagger }) { `param`.assistedOne } }
                val floorFly: dynamic = jsAnd((k === "fly")) { (!truth(e.gymOnly23)) }
                val primary: dynamic = (if (truth(jsOr(top) { floorFly })) "top" else (if (truth(jsOr(jsOr(jsOr(jsOr(frontRecipes.has(k)) { (k === "step") }) { jsAnd(`param`.cross) { (k === "lunge") } }) { jsAnd(`param`.wide) { (k === "deadlift") } }) { jsAnd((k === "fly")) { e.gymOnly23 } })) "front" else (if (truth((k === "side-plank"))) "lying" else "side")))
                global_Object.assign(v, jsObject { obj978 ->
                    obj978["primary"] = primary
                    obj978["label"] = jsonValue("{\"top\":\"Superior\",\"front\":\"Frontal\",\"side\":\"Lateral\",\"lying\":\"Frontal · de lado\"}")[primary]
                    obj978["family"] = k
                    obj978["mirror"] = false
                    obj978["project"] = dynamicValue(null)
                    obj978["inset"] = (if (truth(top)) "pushDepth23" else (if (truth(floorFly)) "floorFly23" else (if (truth((k === "pallof"))) "pallof23" else (if (truth(`param`.frog)) "frog23" else (if (truth(`param`.pronate)) "grip23" else (if (truth(`param`.airplane)) "hip23" else (if (truth(`param`.scissors)) "scissors23" else (if (truth(jsOr(jsOr(jsOr(`param`.close) { `param`.supinate }) { `param`.neutral }) { `param`.wide })) "grip23" else null))))))))
                    obj978["reason"] = (if (truth((primary === "front"))) "Observa los dos lados y el recorrido de la carga." else "Observa el apoyo y la relación entre cabeza, pelvis y articulaciones.")
                })
                m.posture23 = (if (truth(jsAnd((k === "hamcurl")) { `param`.lying })) "prone" else (if (truth(dynamicValue(jsonValue("[\"push\",\"pike\",\"plank\",\"bear\",\"renegade\"]")).includes(k))) "prone" else (if (truth(jsOr(dynamicValue(jsonValue("[\"bridge\",\"ham-slide\",\"hipthrust\",\"hollow\",\"legraise\",\"bench\",\"reverse-plank\"]")).includes(k)) { jsAnd((k === "fly")) { (!truth(e.gymOnly23)) } })) "supine" else (if (truth((k === "side-plank"))) "side-lying" else "upright"))))
                m.frames = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                    val f: dynamic = modelFrame(e, jsDivide(i, 48))
                    f.detail.view21 = primary
                    return f
                }))
                m.hold = false
                if (truth((m.posture23 === "upright"))) {
                    m.facing23 = "right"
                }
                global_Rig2D23.reviews.push(jsObject { obj989 ->
                    obj989["id"] = e.id
                    obj989["name"] = e.name
                    obj989["action"] = jsAdd("Rig y equipo específicos del patrón ", k)
                    obj989["changed"] = true
                    obj989["view"] = primary
                    obj989["inset"] = v.inset
                    obj989["hold"] = false
                })
            }
        }
        global_Rig2D23.extras = extras
        global_Rig2D23.loads = loads
        global_Rig2D23.phase = (fun(e: dynamic, phase: dynamic): dynamic {
            val t: dynamic = jsDivide(jsMod(jsAdd(jsMod(phase, 48), 48), 48), 48)
            return (if (truth((e.renderWarp23 === "slow"))) (if (truth(jsLess(t, 0.4))) jsMultiply(jsDivide(t, 0.4), 24) else jsAdd(24, jsMultiply(jsDivide(jsSubtract(t, 0.4), 0.6), 24))) else (if (truth((e.renderWarp23 === "pause"))) (if (truth(jsLess(t, jsDivide(1, 3)))) jsMultiply(jsMultiply(t, 3), 24) else (if (truth(jsLess(t, jsDivide(2, 3)))) 24 else jsAdd(24, jsMultiply(jsMultiply(jsSubtract(t, jsDivide(2, 3)), 3), 24)))) else phase))
        })
        return absent
    }))(global_window)
}
