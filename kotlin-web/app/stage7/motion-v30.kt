@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installmotionv30

import hunter.browser.*

@JsName("JSON") private external var global_JSON: dynamic
@JsName("Rig2D23") private external var global_Rig2D23: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("RigCorrections24") private external var global_RigCorrections24: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Anatomy2D22") private external var global_Anatomy2D22: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Promise") private external var global_Promise: dynamic
@JsName("fetch") private external var global_fetch: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installMotionV30() {
    dynamicValue((fun(root: dynamic): dynamic {
        var cp: dynamic = absent
        var R: dynamic = absent
        var mix: dynamic = absent
        var wave: dynamic = absent
        var cache: dynamic = absent
        var add: dynamic = absent
        var sub: dynamic = absent
        var unit: dynamic = absent
        var ik: dynamic = absent
        var neutralHead: dynamic = absent
        var empty: dynamic = absent
        var side: dynamic = absent
        var front: dynamic = absent
        var seated: dynamic = absent
        var feet: dynamic = absent
        var arms: dynamic = absent
        var squat: dynamic = absent
        var hinge: dynamic = absent
        var sumo: dynamic = absent
        var lunge: dynamic = absent
        var push: dynamic = absent
        var pike: dynamic = absent
        var plank: dynamic = absent
        var topPlank: dynamic = absent
        var renegade: dynamic = absent
        var sidePlank: dynamic = absent
        var ankleDrive: dynamic = absent
        var pressMachine: dynamic = absent
        var lift: dynamic = absent
        var bridge: dynamic = absent
        var thrust: dynamic = absent
        var legpress: dynamic = absent
        var ham: dynamic = absent
        var extension: dynamic = absent
        var hipMachine: dynamic = absent
        var curl: dynamic = absent
        var tricep: dynamic = absent
        var hipStanding: dynamic = absent
        var cycle: dynamic = absent
        var poseTrack: dynamic = absent
        var resolve: dynamic = absent
        var cadence: dynamic = absent
        var time: dynamic = absent
        var `get`: dynamic = absent
        var extras: dynamic = absent
        var inset: dynamic = absent
        var loaded30: dynamic = absent
        var loading30: dynamic = absent
        var preload: dynamic = absent
        ik = fun(a: dynamic, b: dynamic, arg8: dynamic, arg9: dynamic): dynamic {
            var l: dynamic = absent
            l = definedOr(arg8) { 140 }
            var side: dynamic = absent
            side = definedOr(arg9) { 1 }
            return R.ik(a, b, l, side)
        }
        neutralHead = fun(f: dynamic): dynamic {
            f.p[0] = add(f.p[1], unit(sub(f.p[1], f.p[2])), 60)
            return f
        }
        empty = fun(): dynamic {
            return jsObject { obj16 ->
                obj16["p"] = cp(R.standing().p)
                obj16["loads"] = js("[]")
                obj16["equipment"] = js("[]")
                obj16["detail"] = jsonValue("{\"floor23\":715}")
            }
        }
        side = fun(): dynamic {
            val f: dynamic = empty()
            f.p = jsonValue("[[350,205],[350,265],[350,435],[357,360],[357,455],[344,349],[344,444],[357,567],[357,700],[344,554],[344,687]]")
            return f
        }
        front = fun(arg23: dynamic): dynamic {
            var seated: dynamic = absent
            seated = definedOr(arg23) { false }
            val f: dynamic = empty()
            f.p = jsonValue("[[360,205],[360,265],[360,435],[308,360],[308,455],[412,360],[412,455],[330,568],[330,700],[390,568],[390,700]]")
            if (truth(seated)) {
                if (true) {
                    val iterator26: dynamic = iteratorOf(jsonValue("[0,1,2,3,4,5,6]"))
                    while (true) {
                        val next27: dynamic = iterator26.next()
                        if (truth(next27.done)) break
                        var i: dynamic = absent
                        i = next27.value
                        run {
                            val target28: dynamic = f.p[i]
                            val key29: dynamic = 1
                            target28[key29] = jsAdd(target28[key29], 50)
                        }
                    }
                }
                f.p[7] = jsonValue("[275,570]")
                f.p[8] = jsonValue("[275,700]")
                f.p[9] = jsonValue("[445,570]")
                f.p[10] = jsonValue("[445,700]")
                f.equipment = jsonValue("[[\"chair\",[260,514,460,715]]]")
            }
            return f
        }
        seated = fun(): dynamic {
            val f: dynamic = side()
            f.p = jsonValue("[[280,260],[280,320],[280,490],[285,415],[320,492],[272,402],[307,479],[420,490],[420,700],[407,477],[407,687]]")
            f.equipment = jsonValue("[[\"chair\",[230,515,390,715]]]")
            return f
        }
        feet = fun(f: dynamic, arg48: dynamic, arg49: dynamic): dynamic {
            var a: dynamic = absent
            a = definedOr(arg48) { jsonValue("[1,0]") }
            var b: dynamic = absent
            b = definedOr(arg49) { a }
            f.detail.feet30 = jsObject { obj52 ->
                obj52["8"] = a
                obj52["10"] = b
            }
            return f
        }
        arms = fun(f: dynamic, ends: dynamic, arg55: dynamic, arg56: dynamic): dynamic {
            var l: dynamic = absent
            l = definedOr(arg55) { 95 }
            var side: dynamic = absent
            side = definedOr(arg56) { 1 }
            if (true) {
                val iterator57: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, 4, ends[0]), arrayOf<dynamic>(5, 6, jsOr(ends[1]) { add(ends[0], jsonValue("[-13,-13]")) })))
                while (true) {
                    val next58: dynamic = iterator57.next()
                    if (truth(next58.done)) break
                    var k: dynamic = absent
                    var h: dynamic = absent
                    var end: dynamic = absent
                    val unpacked59: dynamic = fromIterable(next58.value)
                    k = unpacked59[0]
                    h = unpacked59[1]
                    end = unpacked59[2]
                    f.p[h] = end
                    f.p[k] = ik(f.p[1], end, l, side)
                }
            }
            return f
        }
        squat = fun(a: dynamic, arg66: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg66) { js("({})") }
            val f: dynamic = side()
            val hip: dynamic = arrayOf<dynamic>(jsSubtract(350, jsMultiply(70, a)), jsAdd(435, jsMultiply(115, a)))
            f.p[2] = hip
            f.p[1] = arrayOf<dynamic>(jsAdd(350, jsMultiply(20, a)), jsAdd(265, jsMultiply(135, a)))
            neutralHead(f)
            f.p[8] = jsonValue("[365,700]")
            f.p[10] = jsonValue("[352,687]")
            f.p[7] = ik(hip, f.p[8], 140, 1)
            f.p[9] = ik(add(hip, jsonValue("[-13,-13]")), f.p[10], 140, 1)
            arms(f, arrayOf<dynamic>(arrayOf<dynamic>(jsAdd(f.p[1][0], 30), jsAdd(f.p[1][1], 20))))
            if (truth(p.bench)) {
                f.equipment = jsonValue("[[\"bench\",[150,572,325,715]]]")
            }
            if (truth(p.heelWedge)) {
                f.equipment.push(jsonValue("[\"step\",[335,710,405,726]]"))
            }
            if (truth(jsOr(jsOr(p.hack) { p.pendulum }) { p.vlever })) {
                f.p[2] = arrayOf<dynamic>(jsSubtract(340, jsMultiply(50, a)), jsAdd(445, jsMultiply(110, a)))
                f.p[1] = arrayOf<dynamic>(jsSubtract(f.p[2][0], 50), jsSubtract(f.p[2][1], 163))
                neutralHead(f)
                f.p[8] = jsonValue("[485,695]")
                f.p[10] = jsonValue("[472,682]")
                f.p[7] = ik(f.p[2], f.p[8], 150, 1)
                f.p[9] = ik(add(f.p[2], jsonValue("[-13,-13]")), f.p[10], 150, 1)
                arms(f, arrayOf<dynamic>(add(f.p[1], jsonValue("[30,25]"))), 95, 1)
                f.detail.hack30 = true
            }
            if (truth(p.narrow)) {
                run {
                    val target95: dynamic = f.p[8]
                    val key96: dynamic = 0
                    target95[key96] = jsSubtract(target95[key96], 6)
                }
                run {
                    val target97: dynamic = f.p[10]
                    val key98: dynamic = 0
                    target97[key98] = jsAdd(target97[key98], 6)
                }
            }
            return feet(f)
        }
        hinge = fun(a: dynamic, arg101: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg101) { js("({})") }
            val f: dynamic = side()
            val hip: dynamic = arrayOf<dynamic>(jsSubtract(350, jsMultiply(65, a)), jsAdd(435, jsMultiply(42, a)))
            f.p[2] = hip
            val angle: dynamic = jsMultiply(1.15, a)
            f.p[1] = arrayOf<dynamic>(jsAdd(hip[0], jsMultiply(170, global_Math.sin(angle))), jsSubtract(hip[1], jsMultiply(170, global_Math.cos(angle))))
            neutralHead(f)
            f.p[8] = jsonValue("[350,700]")
            f.p[10] = arrayOf<dynamic>(jsSubtract(337, (if (truth(p.staggered)) 50 else 0)), 687)
            f.p[7] = ik(hip, f.p[8], 140, 1)
            f.p[9] = ik(add(hip, jsonValue("[-13,-13]")), f.p[10], 140, 1)
            arms(f, arrayOf<dynamic>(arrayOf<dynamic>(f.p[1][0], jsAdd(f.p[1][1], 188))), 95, (-numeric(1)))
            return feet(f)
        }
        sumo = fun(a: dynamic): dynamic {
            val f: dynamic = front()
            val p: dynamic = f.p
            run {
                val target116: dynamic = p[1]
                val key117: dynamic = 1
                target116[key117] = jsAdd(target116[key117], jsMultiply(200, a))
            }
            run {
                val target118: dynamic = p[0]
                val key119: dynamic = 1
                target118[key119] = jsAdd(target118[key119], jsMultiply(200, a))
            }
            run {
                val target120: dynamic = p[2]
                val key121: dynamic = 1
                target120[key121] = jsAdd(target120[key121], jsMultiply(115, a))
            }
            p[8] = jsonValue("[210,700]")
            p[10] = jsonValue("[510,700]")
            p[7] = ik(arrayOf<dynamic>(335, p[2][1]), p[8], 151, (-numeric(1)))
            p[9] = ik(arrayOf<dynamic>(385, p[2][1]), p[10], 151, 1)
            p[3] = arrayOf<dynamic>(315, jsAdd(p[1][1], 112))
            p[4] = arrayOf<dynamic>(315, jsAdd(p[1][1], 225))
            p[5] = arrayOf<dynamic>(405, jsAdd(p[1][1], 112))
            p[6] = arrayOf<dynamic>(405, jsAdd(p[1][1], 225))
            f.detail.floor23 = 735
            f.detail.hingeAmount30 = a
            return feet(f, jsonValue("[-0.45,0.89]"), jsonValue("[0.45,0.89]"))
        }
        lunge = fun(a: dynamic, arg144: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg144) { js("({})") }
            val f: dynamic = side()
            val hip: dynamic = arrayOf<dynamic>(350, jsAdd(455, jsMultiply(110, a)))
            f.p[2] = hip
            f.p[1] = arrayOf<dynamic>(350, jsSubtract(hip[1], 170))
            neutralHead(f)
            f.p[8] = arrayOf<dynamic>(470, (if (truth(p.frontElevated)) 620 else 700))
            f.p[10] = arrayOf<dynamic>(195, (if (truth(p.bench)) 555 else 685))
            f.p[7] = ik(hip, f.p[8], 150, 1)
            f.p[9] = ik(hip, f.p[10], 150, 1)
            arms(f, arrayOf<dynamic>(arrayOf<dynamic>(360, jsAdd(hip[1], 20))), 95, (-numeric(1)))
            if (truth(p.bench)) {
                f.equipment = jsonValue("[[\"bench\",[145,578,220,715]]]")
            }
            if (truth(p.frontElevated)) {
                f.equipment.push(jsonValue("[\"step\",[445,643,530,715]]"))
            }
            f.detail.rearToe23 = 10
            return feet(f, jsonValue("[1,0]"), jsonValue("[0.9,0.45]"))
        }
        push = fun(a: dynamic, arg163: dynamic, arg164: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg163) { js("({})") }
            var t: dynamic = absent
            t = definedOr(arg164) { 0 }
            val f: dynamic = empty()
            val ankle: dynamic = jsonValue("[660,680]")
            val angle: dynamic = jsSubtract(0.43, jsMultiply(0.34, a))
            val axis: dynamic = arrayOf<dynamic>(global_Math.cos(angle), global_Math.sin(angle))
            f.p[1] = add(ankle, axis, (-numeric(430)))
            f.p[2] = add(f.p[1], axis, 170)
            f.p[7] = add(f.p[2], axis, 130)
            f.p[8] = ankle
            f.p[9] = add(f.p[7], jsonValue("[-13,-13]"))
            f.p[10] = add(ankle, jsonValue("[-13,-13]"))
            neutralHead(f)
            val hands: dynamic = arrayOf<dynamic>(arrayOf<dynamic>(jsSubtract(jsAdd(270, (if (truth(p.close)) 12 else 0)), (if (truth(p.wide)) 12 else 0)), 700), arrayOf<dynamic>(jsAdd(257, (if (truth(p.stagger)) 45 else 0)), 687))
            arms(f, hands, 100, 1)
            f.detail.floor23 = 720
            feet(f, jsonValue("[-0.3,0.95]"), jsonValue("[-0.3,0.95]"))
            if (truth(p.knees)) {
                f.p[7] = jsonValue("[550,699]")
                f.p[9] = jsonValue("[537,686]")
                f.p[2] = mix(f.p[1], f.p[7], 0.58)
                f.p[8] = jsonValue("[665,645]")
                f.p[10] = jsonValue("[652,632]")
                neutralHead(f)
                feet(f, jsonValue("[1,0.15]"), jsonValue("[1,0.15]"))
            }
            if (truth(p.bench)) {
                val delta: dynamic = (-numeric(90))
                run {
                    val target189: dynamic = f.p[8]
                    val key190: dynamic = 1
                    target189[key190] = jsAdd(target189[key190], delta)
                }
                run {
                    val target191: dynamic = f.p[10]
                    val key192: dynamic = 1
                    target191[key192] = jsAdd(target191[key192], delta)
                }
                f.p[2] = mix(f.p[1], f.p[8], 0.4)
                f.p[7] = mix(f.p[2], f.p[8], 0.5)
                f.p[9] = add(f.p[7], jsonValue("[-13,-13]"))
                neutralHead(f)
                f.equipment = jsonValue("[[\"bench\",[620,612,710,720]]]")
                feet(f, jsonValue("[1,0]"), jsonValue("[1,0]"))
            }
            if (truth(p.power)) {
                val u: dynamic = (if (truth(jsMore(t, 0.72))) wave(jsDivide(jsSubtract(t, 0.72), 0.28)) else 0)
                if (true) {
                    val iterator201: dynamic = iteratorOf(jsonValue("[0,1,2,3,4,5,6,7,9]"))
                    while (true) {
                        val next202: dynamic = iterator201.next()
                        if (truth(next202.done)) break
                        var i: dynamic = absent
                        i = next202.value
                        run {
                            val target203: dynamic = f.p[i]
                            val key204: dynamic = 1
                            target203[key204] = jsSubtract(target203[key204], jsMultiply(30, u))
                        }
                    }
                }
            }
            if (truth(p.release)) {
                val u: dynamic = (if (truth(jsMore(a, 0.9))) jsMultiply(jsSubtract(a, 0.9), 10) else 0)
                run {
                    val target205: dynamic = f.p[4]
                    val key206: dynamic = 1
                    target205[key206] = jsSubtract(target205[key206], jsMultiply(10, u))
                }
                run {
                    val target207: dynamic = f.p[6]
                    val key208: dynamic = 1
                    target207[key208] = jsSubtract(target207[key208], jsMultiply(10, u))
                }
            }
            if (truth(p.tap)) {
                val u: dynamic = (if (truth(jsMore(t, 0.6))) wave(jsDivide(jsSubtract(t, 0.6), 0.4)) else 0)
                f.p[6] = mix(f.p[6], add(f.p[1], jsonValue("[15,12]")), u)
                f.p[5] = ik(f.p[1], f.p[6], 100, 1)
            }
            if (truth(p.kneeDrive)) {
                f.p[9] = mix(f.p[9], add(f.p[1], jsonValue("[50,75]")), a)
                f.p[10] = mix(f.p[10], add(f.p[1], jsonValue("[190,95]")), a)
            }
            if (truth(p.scapular)) {
                val stable: dynamic = push(0, jsObject { obj217 ->
                    copyFields(obj217, p)
                    obj217["scapular"] = false
                }, t)
                val pts: dynamic = stable.p
                run {
                    val target218: dynamic = pts[1]
                    val key219: dynamic = 1
                    target218[key219] = jsAdd(target218[key219], jsMultiply(15, a))
                }
                run {
                    val target220: dynamic = pts[0]
                    val key221: dynamic = 1
                    target220[key221] = jsAdd(target220[key221], jsMultiply(15, a))
                }
                run {
                    val target222: dynamic = pts[2]
                    val key223: dynamic = 1
                    target222[key223] = jsAdd(target222[key223], jsMultiply(8, a))
                }
                if (true) {
                    val iterator224: dynamic = iteratorOf(jsonValue("[[3,4,[0,0]],[5,6,[-13,-13]]]"))
                    while (true) {
                        val next225: dynamic = iterator224.next()
                        if (truth(next225.done)) break
                        var el: dynamic = absent
                        var hand: dynamic = absent
                        var delta: dynamic = absent
                        val unpacked226: dynamic = fromIterable(next225.value)
                        el = unpacked226[0]
                        hand = unpacked226[1]
                        delta = unpacked226[2]
                        val shoulder: dynamic = add(pts[1], delta)
                        pts[el] = mix(shoulder, pts[hand], 0.5)
                    }
                }
                if (true) {
                    val iterator229: dynamic = iteratorOf(jsonValue("[[7,8,[0,0]],[9,10,[-13,-13]]]"))
                    while (true) {
                        val next230: dynamic = iterator229.next()
                        if (truth(next230.done)) break
                        var knee: dynamic = absent
                        var ankle: dynamic = absent
                        var delta: dynamic = absent
                        val unpacked231: dynamic = fromIterable(next230.value)
                        knee = unpacked231[0]
                        ankle = unpacked231[1]
                        delta = unpacked231[2]
                        pts[knee] = mix(add(pts[2], delta), pts[ankle], 0.5)
                    }
                }
                stable.detail.biomechanics3839 = "scapular-only-elbows-extended"
                return stable
            }
            return f
        }
        pike = fun(a: dynamic, arg238: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg238) { js("({})") }
            val f: dynamic = empty()
            f.p[2] = arrayOf<dynamic>(jsAdd(390, jsMultiply(35, a)), jsAdd(365, jsMultiply(35, a)))
            f.p[1] = arrayOf<dynamic>(jsAdd(300, jsMultiply(14, a)), jsAdd(510, jsMultiply(35, a)))
            neutralHead(f)
            f.p[8] = arrayOf<dynamic>(640, (if (truth(p.bench)) 540 else 700))
            f.p[10] = arrayOf<dynamic>(627, (if (truth(p.bench)) 527 else 687))
            f.p[7] = mix(f.p[2], f.p[8], 0.5)
            f.p[9] = add(f.p[7], jsonValue("[-13,-13]"))
            arms(f, jsonValue("[[290,700]]"), 100, 1)
            if (truth(p.bench)) {
                f.equipment = jsonValue("[[\"bench\",[605,548,710,720]]]")
            }
            return feet(f, (if (truth(p.bench)) jsonValue("[1,0]") else jsonValue("[-0.6,0.8]")))
        }
        plank = fun(t: dynamic, arg255: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg255) { js("({})") }
            var f: dynamic = push(0, p, t)
            val a: dynamic = wave(t)
            if (truth(p.saw)) {
                f.p[3] = jsonValue("[280,700]")
                f.p[4] = jsonValue("[175,700]")
                f.p[5] = jsonValue("[267,687]")
                f.p[6] = jsonValue("[162,687]")
                f.p[1] = arrayOf<dynamic>(jsAdd(280, jsMultiply(18, a)), 590)
                f.p[2] = arrayOf<dynamic>(jsAdd(440, jsMultiply(18, a)), 625)
                neutralHead(f)
                f.p[7] = mix(f.p[2], f.p[8], 0.5)
                f.p[9] = add(f.p[7], jsonValue("[-13,-13]"))
            }
            if (truth(jsOr(p.toe) { p.jack })) {
                f = topPlank(t, p)
            }
            if (truth(p.handwalk)) {
                run {
                    val target272: dynamic = f.p[4]
                    val key273: dynamic = 0
                    target272[key273] = jsAdd(target272[key273], jsMultiply(28, a))
                }
                run {
                    val target274: dynamic = f.p[8]
                    val key275: dynamic = 0
                    target274[key275] = jsAdd(target274[key275], jsMultiply(28, a))
                }
                f.p[3] = ik(f.p[1], f.p[4], 100, 1)
            }
            if (truth(p.drag)) {
                f.p[6] = mix(f.p[6], jsonValue("[340,670]"), a)
                f.p[5] = ik(f.p[1], f.p[6], 100, 1)
                f.loads = arrayOf<dynamic>(arrayOf<dynamic>(jsAdd(340, jsMultiply(50, a)), 705))
            }
            return f
        }
        topPlank = fun(t: dynamic, arg286: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg286) { js("({})") }
            val f: dynamic = front()
            val a: dynamic = wave(t)
            f.p = arrayOf<dynamic>(jsonValue("[360,185]"), jsonValue("[360,255]"), jsonValue("[360,440]"), jsonValue("[305,260]"), jsonValue("[300,180]"), jsonValue("[415,260]"), jsonValue("[420,180]"), jsonValue("[332,567]"), jsonValue("[325,700]"), arrayOf<dynamic>(jsAdd(388, jsMultiply(45, a)), 567), arrayOf<dynamic>(jsAdd(395, jsMultiply(100, a)), 700))
            if (truth(p.jack)) {
                run {
                    val target289: dynamic = f.p[7]
                    val key290: dynamic = 0
                    target289[key290] = jsSubtract(target289[key290], jsMultiply(45, a))
                }
                run {
                    val target291: dynamic = f.p[8]
                    val key292: dynamic = 0
                    target291[key292] = jsSubtract(target291[key292], jsMultiply(100, a))
                }
            }
            f.detail.front19 = true
            f.detail.support23 = true
            return feet(f, jsonValue("[0,-1]"), jsonValue("[0,-1]"))
        }
        renegade = fun(t: dynamic, arg299: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg299) { js("({})") }
            val f: dynamic = push(0, jsObject { obj300 ->
                obj300["knees"] = p.knees
            })
            val a: dynamic = global_Math.max(0, global_Math.sin(jsMultiply(jsMultiply(t, 2), global_Math.PI)))
            val b: dynamic = global_Math.max(0, (-numeric(global_Math.sin(jsMultiply(jsMultiply(t, 2), global_Math.PI)))))
            if (true) {
                val iterator301: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, 4, a, 0), arrayOf<dynamic>(5, 6, b, (-numeric(13)))))
                while (true) {
                    val next302: dynamic = iterator301.next()
                    if (truth(next302.done)) break
                    var el: dynamic = absent
                    var ha: dynamic = absent
                    var u: dynamic = absent
                    var dy: dynamic = absent
                    val unpacked303: dynamic = fromIterable(next302.value)
                    el = unpacked303[0]
                    ha = unpacked303[1]
                    u = unpacked303[2]
                    dy = unpacked303[3]
                    val end: dynamic = mix(f.p[ha], arrayOf<dynamic>(435, jsAdd(570, dy)), u)
                    f.p[ha] = end
                    f.p[el] = ik(f.p[1], end, 100, 1)
                }
            }
            f.loads = arrayOf<dynamic>(cp(f.p[4]), cp(f.p[6]))
            return f
        }
        sidePlank = fun(t: dynamic, arg312: dynamic, arg313: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg312) { js("({})") }
            var full: dynamic = absent
            full = definedOr(arg313) { true }
            val f: dynamic = empty()
            val a: dynamic = wave(t)
            f.p = jsonValue("[[205,507],[260,520],[425,563],[260,630],[167,630],[275,420],[285,320],[555,598],[685,630],[552,585],[682,617]]")
            if (truth(jsOr((!truth(full))) { p.knees })) {
                f.p[7] = jsonValue("[550,630]")
                f.p[8] = jsonValue("[653,566]")
                f.p[9] = jsonValue("[547,617]")
                f.p[10] = jsonValue("[650,553]")
            }
            if (truth(p.hipLift)) {
                run {
                    val target324: dynamic = f.p[2]
                    val key325: dynamic = 1
                    target324[key325] = jsAdd(target324[key325], jsMultiply(35, jsSubtract(1, a)))
                }
                run {
                    val target326: dynamic = f.p[1]
                    val key327: dynamic = 1
                    target326[key327] = jsAdd(target326[key327], jsMultiply(10, jsSubtract(1, a)))
                }
                run {
                    val target328: dynamic = f.p[0]
                    val key329: dynamic = 1
                    target328[key329] = jsAdd(target328[key329], jsMultiply(10, jsSubtract(1, a)))
                }
            }
            if (truth(p.thread)) {
                f.p[6] = mix(jsonValue("[285,320]"), jsonValue("[175,597]"), a)
                f.p[5] = mix(jsonValue("[275,420]"), jsonValue("[200,548]"), a)
                f.detail.turn30 = a
            }
            if (truth(p.knee)) {
                f.p[9] = mix(f.p[9], jsonValue("[465,470]"), a)
                f.p[10] = mix(f.p[10], jsonValue("[555,538]"), a)
            }
            if (truth(p.legLift)) {
                f.p[9] = arrayOf<dynamic>(jsAdd(425, jsMultiply(130, global_Math.cos(jsMultiply(0.45, a)))), jsSubtract(563, jsMultiply(130, global_Math.sin(jsMultiply(0.45, a)))))
                f.p[10] = arrayOf<dynamic>(jsAdd(425, jsMultiply(260, global_Math.cos(jsMultiply(0.45, a)))), jsSubtract(563, jsMultiply(260, global_Math.sin(jsMultiply(0.45, a)))))
            }
            f.detail.floor23 = 646
            return feet(f, jsonValue("[1,0]"), jsonValue("[1,0]"))
        }
        ankleDrive = fun(a: dynamic): dynamic {
            val f: dynamic = side()
            f.p[2] = arrayOf<dynamic>(jsAdd(360, jsMultiply(16, a)), 465)
            f.p[1] = arrayOf<dynamic>(jsAdd(370, jsMultiply(18, a)), 295)
            neutralHead(f)
            f.p[8] = jsonValue("[470,700]")
            f.p[10] = jsonValue("[250,687]")
            f.p[7] = ik(f.p[2], f.p[8], 140, 1)
            f.p[9] = ik(add(f.p[2], jsonValue("[-13,-13]")), f.p[10], 140, 1)
            f.p[6] = jsonValue("[555,385]")
            f.p[5] = ik(f.p[1], f.p[6], 105, (-numeric(1)))
            f.p[3] = jsonValue("[380,410]")
            f.p[4] = jsonValue("[380,505]")
            f.equipment = jsonValue("[[\"wall\",[568,225,581,715]]]")
            f.detail.ankle30 = a
            return feet(f)
        }
        pressMachine = fun(a: dynamic, arg374: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg374) { js("({})") }
            val f: dynamic = seated()
            val s: dynamic = f.p[1]
            f.p[4] = arrayOf<dynamic>(jsAdd(jsAdd(s[0], 60), jsMultiply(123, a)), jsAdd(s[1], (if (truth(p.incline)) jsMultiply((-numeric(32)), a) else 28)))
            f.p[6] = add(f.p[4], jsonValue("[-13,-13]"))
            f.p[3] = ik(s, f.p[4], 96, (-numeric(1)))
            f.p[5] = ik(add(s, jsonValue("[-13,-13]")), f.p[6], 96, (-numeric(1)))
            if (truth(p.single)) {
                f.p[5] = jsonValue("[272,415]")
                f.p[6] = jsonValue("[302,492]")
            }
            f.detail.machine30 = "press"
            return feet(f)
        }
        lift = fun(a: dynamic, arg391: dynamic, arg392: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg391) { js("({})") }
            var kind: dynamic = absent
            kind = definedOr(arg392) { "lateral" }
            val f: dynamic = front(p.seated)
            val s: dynamic = f.p[1]
            val left: dynamic = arrayOf<dynamic>(jsSubtract(s[0], 42), s[1])
            val right: dynamic = arrayOf<dynamic>(jsAdd(s[0], 42), s[1])
            if (true) {
                val iterator393: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, 4, left, (-numeric(1))), arrayOf<dynamic>(5, 6, right, 1)))
                while (true) {
                    val next394: dynamic = iterator393.next()
                    if (truth(next394.done)) break
                    var el: dynamic = absent
                    var ha: dynamic = absent
                    var start: dynamic = absent
                    var sign: dynamic = absent
                    val unpacked395: dynamic = fromIterable(next394.value)
                    el = unpacked395[0]
                    ha = unpacked395[1]
                    start = unpacked395[2]
                    sign = unpacked395[3]
                    val u: dynamic = (if (truth(jsAnd(p.single) { (sign === 1) })) 0 else a)
                    val th: dynamic = jsDivide(jsMultiply(u, global_Math.PI), 2)
                    f.p[el] = arrayOf<dynamic>(jsAdd(start[0], jsMultiply(jsMultiply(sign, 95), global_Math.sin(th))), jsAdd(start[1], jsMultiply(95, global_Math.cos(th))))
                    f.p[ha] = arrayOf<dynamic>(jsAdd(start[0], jsMultiply(jsMultiply(sign, 185), global_Math.sin(th))), jsAdd(start[1], jsMultiply(185, global_Math.cos(th))))
                }
            }
            if (truth((kind === "overhead"))) {
                if (true) {
                    val iterator400: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, 4, left, (-numeric(1))), arrayOf<dynamic>(5, 6, right, 1)))
                    while (true) {
                        val next401: dynamic = iterator400.next()
                        if (truth(next401.done)) break
                        var el: dynamic = absent
                        var ha: dynamic = absent
                        var start: dynamic = absent
                        var sign: dynamic = absent
                        val unpacked402: dynamic = fromIterable(next401.value)
                        el = unpacked402[0]
                        ha = unpacked402[1]
                        start = unpacked402[2]
                        sign = unpacked402[3]
                        val u: dynamic = (if (truth(jsAnd(p.single) { (sign === 1) })) 0 else a)
                        f.p[ha] = arrayOf<dynamic>(jsAdd(start[0], jsMultiply(sign, jsSubtract(30, jsMultiply(15, u)))), jsSubtract(jsAdd(start[1], 5), jsMultiply(180, u)))
                        f.p[el] = ik(start, f.p[ha], 95, (-numeric(sign)))
                    }
                }
            }
            return feet(f, jsonValue("[-0.08,1]"), jsonValue("[0.08,1]"))
        }
        bridge = fun(a: dynamic, arg409: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg409) { js("({})") }
            val f: dynamic = empty()
            f.p = arrayOf<dynamic>(jsonValue("[140,620]"), jsonValue("[200,630]"), arrayOf<dynamic>(365, jsSubtract(620, jsMultiply(105, a))), jsonValue("[250,645]"), jsonValue("[320,650]"), jsonValue("[237,632]"), jsonValue("[307,637]"), jsonValue("[465,550]"), jsonValue("[515,682]"), jsonValue("[452,537]"), jsonValue("[502,669]"))
            if (truth(p.long)) {
                run {
                    val target412: dynamic = f.p[8]
                    val key413: dynamic = 0
                    target412[key413] = jsAdd(target412[key413], 85)
                }
                run {
                    val target414: dynamic = f.p[10]
                    val key415: dynamic = 0
                    target414[key415] = jsAdd(target414[key415], 85)
                }
            }
            if (true) {
                val iterator416: dynamic = iteratorOf(jsonValue("[[7,8,0],[9,10,-13]]"))
                while (true) {
                    val next417: dynamic = iterator416.next()
                    if (truth(next417.done)) break
                    var k: dynamic = absent
                    var z: dynamic = absent
                    var o: dynamic = absent
                    val unpacked418: dynamic = fromIterable(next417.value)
                    k = unpacked418[0]
                    z = unpacked418[1]
                    o = unpacked418[2]
                    f.p[k] = ik(add(f.p[2], arrayOf<dynamic>(o, o)), f.p[z], 135, 1)
                }
            }
            if (truth(jsOr(p.single) { p.march })) {
                val u: dynamic = (if (truth(p.single)) 1 else a)
                f.p[9] = mix(f.p[9], jsonValue("[450,445]"), u)
                f.p[10] = mix(f.p[10], jsonValue("[550,460]"), u)
            }
            if (truth(p.walkout)) {
                val u: dynamic = jsMultiply(80, a)
                run {
                    val target425: dynamic = f.p[8]
                    val key426: dynamic = 0
                    target425[key426] = jsAdd(target425[key426], u)
                }
                run {
                    val target427: dynamic = f.p[10]
                    val key428: dynamic = 0
                    target427[key428] = jsAdd(target427[key428], u)
                }
                f.p[7] = ik(f.p[2], f.p[8], 140, 1)
                f.p[9] = ik(add(f.p[2], jsonValue("[-13,-13]")), f.p[10], 140, 1)
            }
            f.detail.floor23 = 702
            if (truth(p.squeeze)) {
                f.detail.cushion30 = true
            }
            return feet(f)
        }
        thrust = fun(a: dynamic, arg439: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg439) { js("({})") }
            val f: dynamic = bridge(a, p)
            f.p[1] = jsonValue("[225,485]")
            f.p[0] = jsonValue("[168,466]")
            f.p[2] = arrayOf<dynamic>(388, jsSubtract(550, jsMultiply(75, a)))
            f.p[8] = jsonValue("[515,682]")
            f.p[10] = jsonValue("[502,669]")
            f.p[7] = ik(f.p[2], f.p[8], 130, 1)
            f.p[9] = ik(add(f.p[2], jsonValue("[-13,-13]")), f.p[10], 130, 1)
            if (truth(p.single)) {
                f.p[9] = jsonValue("[470,430]")
                f.p[10] = jsonValue("[590,465]")
            }
            f.equipment = jsonValue("[[\"bench\",[140,516,270,702]]]")
            arms(f, arrayOf<dynamic>(add(f.p[2], jsonValue("[-10,-5]"))), 105, (-numeric(1)))
            f.detail.thrust30 = true
            return f
        }
        legpress = fun(a: dynamic, arg464: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg464) { js("({})") }
            val f: dynamic = seated()
            f.p[1] = jsonValue("[245,375]")
            f.p[2] = jsonValue("[300,520]")
            neutralHead(f)
            f.p[8] = arrayOf<dynamic>(jsAdd(535, jsMultiply(90, a)), jsSubtract(490, jsMultiply(65, (if (truth(p.angle)) a else 0))))
            f.p[10] = add(f.p[8], jsonValue("[-13,-13]"))
            f.p[7] = ik(f.p[2], f.p[8], 170, 1)
            f.p[9] = ik(add(f.p[2], jsonValue("[-13,-13]")), f.p[10], 170, 1)
            f.p[3] = jsonValue("[250,467]")
            f.p[4] = jsonValue("[280,545]")
            f.p[5] = jsonValue("[237,454]")
            f.p[6] = jsonValue("[267,532]")
            if (truth(p.single)) {
                f.p[9] = jsonValue("[340,650]")
                f.p[10] = jsonValue("[430,690]")
            }
            f.equipment = jsonValue("[[\"incline\",[180,400,330,720]]]")
            f.detail.plate23 = cp(f.p[8])
            f.detail.machine30 = "legpress"
            return feet(f, jsonValue("[0,-1]"), (if (truth(p.single)) jsonValue("[1,0]") else jsonValue("[0,-1]")))
        }
        ham = fun(a: dynamic, arg497: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg497) { js("({})") }
            if (truth(p.lying)) {
                val f: dynamic = push(0)
                f.p[1] = jsonValue("[240,490]")
                f.p[2] = jsonValue("[410,495]")
                f.p[0] = jsonValue("[180,488]")
                f.p[7] = jsonValue("[545,495]")
                f.p[8] = arrayOf<dynamic>(jsAdd(545, jsMultiply(135, global_Math.cos(jsMultiply(1.3, a)))), jsSubtract(495, jsMultiply(135, global_Math.sin(jsMultiply(1.3, a)))))
                f.p[9] = jsonValue("[532,482]")
                f.p[10] = add(f.p[8], jsonValue("[-13,-13]"))
                f.p[4] = jsonValue("[170,520]")
                f.p[6] = jsonValue("[157,507]")
                f.p[3] = jsonValue("[230,565]")
                f.p[5] = jsonValue("[217,552]")
                f.equipment = jsonValue("[[\"bench\",[150,533,600,720]]]")
                f.detail.roller30 = cp(f.p[8])
                return feet(f, jsonValue("[0.7,-0.7]"), jsonValue("[0.7,-0.7]"))
            }
            if (truth(p.standing)) {
                val f: dynamic = side()
                f.p[10] = arrayOf<dynamic>(jsSubtract(344, jsMultiply(115, a)), jsSubtract(687, jsMultiply(90, a)))
                f.p[9] = jsonValue("[344,554]")
                f.detail.roller30 = cp(f.p[10])
                f.p[6] = jsonValue("[535,380]")
                f.p[5] = ik(f.p[1], f.p[6], 105, (-numeric(1)))
                f.equipment = jsonValue("[[\"wall\",[550,265,565,715]]]")
                return feet(f, jsonValue("[1,0]"), jsonValue("[0.6,-0.8]"))
            }
            val f: dynamic = seated()
            if (true) {
                val iterator536: dynamic = iteratorOf(jsonValue("[[7,8,0],[9,10,-13]]"))
                while (true) {
                    val next537: dynamic = iterator536.next()
                    if (truth(next537.done)) break
                    var k: dynamic = absent
                    var z: dynamic = absent
                    var o: dynamic = absent
                    val unpacked538: dynamic = fromIterable(next537.value)
                    k = unpacked538[0]
                    z = unpacked538[1]
                    o = unpacked538[2]
                    val angle: dynamic = jsAdd(0.2, jsMultiply(1.65, a))
                    f.p[k] = arrayOf<dynamic>(jsAdd(420, o), jsAdd(490, o))
                    f.p[z] = arrayOf<dynamic>(jsAdd(jsAdd(420, o), jsMultiply(145, global_Math.cos(angle))), jsAdd(jsAdd(490, o), jsMultiply(145, global_Math.sin(angle))))
                }
            }
            f.detail.roller30 = cp(f.p[8])
            return feet(f, jsonValue("[1,0]"))
        }
        extension = fun(a: dynamic): dynamic {
            val f: dynamic = seated()
            if (true) {
                val iterator547: dynamic = iteratorOf(jsonValue("[[7,8,0],[9,10,-13]]"))
                while (true) {
                    val next548: dynamic = iterator547.next()
                    if (truth(next548.done)) break
                    var k: dynamic = absent
                    var z: dynamic = absent
                    var o: dynamic = absent
                    val unpacked549: dynamic = fromIterable(next548.value)
                    k = unpacked549[0]
                    z = unpacked549[1]
                    o = unpacked549[2]
                    val angle: dynamic = jsSubtract(1.5, jsMultiply(1.4, a))
                    f.p[k] = arrayOf<dynamic>(jsAdd(420, o), jsAdd(490, o))
                    f.p[z] = arrayOf<dynamic>(jsAdd(jsAdd(420, o), jsMultiply(145, global_Math.cos(angle))), jsAdd(jsAdd(490, o), jsMultiply(145, global_Math.sin(angle))))
                }
            }
            f.detail.roller30 = cp(f.p[8])
            return feet(f, jsonValue("[1,0]"))
        }
        hipMachine = fun(a: dynamic, arg558: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg558) { js("({})") }
            val f: dynamic = front(true)
            val u: dynamic = (if (truth(p.adduct)) jsSubtract(1, a) else a)
            f.p[7] = arrayOf<dynamic>(jsSubtract(292, jsMultiply(45, u)), 555)
            f.p[8] = arrayOf<dynamic>(jsSubtract(285, jsMultiply(70, u)), 700)
            f.p[9] = arrayOf<dynamic>(jsAdd(428, jsMultiply(45, u)), 555)
            f.p[10] = arrayOf<dynamic>(jsAdd(435, jsMultiply(70, u)), 700)
            f.detail.pads30 = true
            return feet(f, jsonValue("[-0.2,1]"), jsonValue("[0.2,1]"))
        }
        curl = fun(a: dynamic, arg571: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg571) { js("({})") }
            val f: dynamic = (if (truth(p.seated)) seated() else side())
            if (truth(p.preacher)) {
                f.equipment.push(jsonValue("[\"table\",[350,420,505,490]]"))
            }
            if (true) {
                val iterator572: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-13]]"))
                while (true) {
                    val next573: dynamic = iterator572.next()
                    if (truth(next573.done)) break
                    var el: dynamic = absent
                    var ha: dynamic = absent
                    var o: dynamic = absent
                    val unpacked574: dynamic = fromIterable(next573.value)
                    el = unpacked574[0]
                    ha = unpacked574[1]
                    o = unpacked574[2]
                    val angle: dynamic = jsAdd(jsDivide((-numeric(global_Math.PI)), 2), jsMultiply(2.3, a))
                    f.p[el] = arrayOf<dynamic>(jsAdd(jsAdd(f.p[1][0], 10), o), jsAdd(jsAdd(f.p[1][1], 95), o))
                    f.p[ha] = arrayOf<dynamic>(jsAdd(f.p[el][0], jsMultiply(95, global_Math.cos(angle))), jsSubtract(f.p[el][1], jsMultiply(95, global_Math.sin(angle))))
                }
            }
            return feet(f)
        }
        tricep = fun(a: dynamic, arg581: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg581) { js("({})") }
            val f: dynamic = (if (truth(p.seated)) seated() else side())
            val over: dynamic = p.overhead
            if (true) {
                val iterator582: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-13]]"))
                while (true) {
                    val next583: dynamic = iterator582.next()
                    if (truth(next583.done)) break
                    var el: dynamic = absent
                    var ha: dynamic = absent
                    var o: dynamic = absent
                    val unpacked584: dynamic = fromIterable(next583.value)
                    el = unpacked584[0]
                    ha = unpacked584[1]
                    o = unpacked584[2]
                    f.p[el] = arrayOf<dynamic>(jsAdd(jsAdd(f.p[1][0], 10), o), jsAdd(jsAdd(f.p[1][1], (if (truth(over)) (-numeric(90)) else 95)), o))
                    val angle: dynamic = (if (truth(over)) jsAdd(0.1, jsMultiply(2.35, jsSubtract(1, a))) else jsAdd(0.25, jsMultiply(1.4, a)))
                    f.p[ha] = arrayOf<dynamic>(jsAdd(f.p[el][0], jsMultiply(95, global_Math.cos(angle))), jsAdd(f.p[el][1], jsMultiply(jsMultiply((if (truth(over)) (-numeric(1)) else 1), 95), global_Math.sin(angle))))
                }
            }
            return feet(f)
        }
        hipStanding = fun(a: dynamic, arg591: dynamic, arg592: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg591) { js("({})") }
            var kind: dynamic = absent
            kind = definedOr(arg592) { "hip-extension" }
            val f: dynamic = side()
            f.p[6] = jsonValue("[535,380]")
            f.p[5] = ik(f.p[1], f.p[6], 105, (-numeric(1)))
            f.equipment = jsonValue("[[\"wall\",[550,265,565,715]]]")
            val angle: dynamic = jsMultiply(jsMultiply((if (truth((kind === "hip-flexion"))) 1 else (-numeric(1))), 0.5), a)
            f.p[9] = arrayOf<dynamic>(jsAdd(344, jsMultiply(133, global_Math.sin(angle))), jsAdd(435, jsMultiply(133, global_Math.cos(angle))))
            f.p[10] = arrayOf<dynamic>(jsAdd(344, jsMultiply(266, global_Math.sin(angle))), jsAdd(435, jsMultiply(266, global_Math.cos(angle))))
            return feet(f)
        }
        cycle = fun(t: dynamic, arg605: dynamic): dynamic {
            var p: dynamic = absent
            p = definedOr(arg605) { js("({})") }
            val f: dynamic = seated()
            val th: dynamic = jsMultiply(jsMultiply(2, global_Math.PI), t)
            val center: dynamic = jsonValue("[450,650]")
            if (truth(p.reclined)) {
                f.p[1] = jsonValue("[215,340]")
                f.p[2] = jsonValue("[295,485]")
                neutralHead(f)
            }
            if (true) {
                val iterator610: dynamic = iteratorOf(arrayOf<dynamic>(jsonValue("[7,8,0]"), arrayOf<dynamic>(9, 10, global_Math.PI)))
                while (true) {
                    val next611: dynamic = iterator610.next()
                    if (truth(next611.done)) break
                    var k: dynamic = absent
                    var z: dynamic = absent
                    var offset: dynamic = absent
                    val unpacked612: dynamic = fromIterable(next611.value)
                    k = unpacked612[0]
                    z = unpacked612[1]
                    offset = unpacked612[2]
                    f.p[z] = arrayOf<dynamic>(jsAdd(center[0], jsMultiply(67, global_Math.cos(jsAdd(th, offset)))), jsAdd(center[1], jsMultiply(67, global_Math.sin(jsAdd(th, offset)))))
                    f.p[k] = ik(f.p[2], f.p[z], 142, 1)
                }
            }
            arms(f, jsonValue("[[430,425]]"), 100, (-numeric(1)))
            if (truth(p.air)) {
                run {
                    val target617: dynamic = f.p[4]
                    val key618: dynamic = 0
                    target617[key618] = jsAdd(target617[key618], jsMultiply(40, global_Math.sin(th)))
                }
                run {
                    val target619: dynamic = f.p[6]
                    val key620: dynamic = 0
                    target619[key620] = jsSubtract(target619[key620], jsMultiply(40, global_Math.sin(th)))
                }
                f.p[3] = ik(f.p[1], f.p[4], 100, (-numeric(1)))
                f.p[5] = ik(f.p[1], f.p[6], 100, (-numeric(1)))
            }
            f.detail.bike30 = jsObject { obj627 ->
                obj627["center"] = center
                obj627["air"] = p.air
            }
            return feet(f)
        }
        poseTrack = fun(t: dynamic, points: dynamic): dynamic {
            var i: dynamic = points.findIndex((fun(x: dynamic): dynamic {
                return jsAtLeast(x[0], t)
            }))
            if (truth(jsLess(i, 1))) {
                i = 1
            }
            val a: dynamic = points[jsSubtract(i, 1)]
            val b: dynamic = points[i]
            val u: dynamic = jsDivide(jsSubtract(t, a[0]), jsSubtract(b[0], a[0]))
            val w: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(global_Math.PI, u))), 2)
            val f: dynamic = cp(a[1])
            f.p = f.p.map((fun(p: dynamic, j: dynamic): dynamic {
                return mix(p, b[1].p[j], w)
            }))
            return f
        }
        resolve = fun(e: dynamic, t: dynamic, raw: dynamic): dynamic {
            val a: dynamic = wave(t)
            val p: dynamic = jsOr(jsOr(e.param30) { e.param23 }) { js("({})") }
            val kind: dynamic = jsOr(jsOr(e.recipe30) { e.recipe23 }) { raw.view21.family }
            val id: dynamic = jsOr(e.baseId) { e.id }
            var f: dynamic = dynamicValue(null)
            var view: dynamic = dynamicValue(null)
            var posture: dynamic = dynamicValue(null)
            if (truth(dynamicValue(jsonValue("[\"squat\",\"chair\",\"goblet\",\"doublefront\"]")).includes(kind))) {
                f = squat((if (truth(p.pulses)) jsAdd(0.7, jsMultiply(0.3, wave(jsMultiply(t, 2)))) else a), jsObject { obj634 ->
                    copyFields(obj634, p)
                    obj634["bench"] = jsOr(p.bench) { (kind === "chair") }
                })
                view = "side"
            }
            if (truth(dynamicValue(jsonValue("[\"hinge\",\"rdl\",\"suitdead\",\"deadlift\",\"sumodead\"]")).includes(kind))) {
                f = (if (truth(p.wide)) sumo(jsSubtract(1, a)) else hinge(a, p))
                view = (if (truth(p.wide)) "front" else "side")
            }
            if (truth(dynamicValue(jsonValue("[\"split\",\"reverselunge\",\"dblunge\",\"dbsplit\",\"splitpause\",\"bulgarian\",\"lunge\"]")).includes(kind))) {
                f = lunge(a, jsObject { obj635 ->
                    copyFields(obj635, p)
                    obj635["bench"] = jsOr(p.bench) { (kind === "bulgarian") }
                })
                view = "side"
            }
            if (truth(dynamicValue(jsonValue("[\"push\",\"pushup\",\"closepush\",\"kneepush\",\"decline19\"]")).includes(kind))) {
                f = push(a, jsObject { obj636 ->
                    copyFields(obj636, p)
                    obj636["close"] = jsOr(p.close) { (kind === "closepush") }
                    obj636["knees"] = jsOr(p.knees) { (kind === "kneepush") }
                    obj636["bench"] = jsOr(p.bench) { (kind === "decline19") }
                }, t)
                view = "side"
                posture = "prone"
                if (truth(jsOr(jsOr(jsOr((p.grip === "diamond")) { p.stagger }) { p.assistedOne }) { p.wide })) {
                    f = topPlank(0, js("({})"))
                    f.p[4] = (if (truth((p.grip === "diamond"))) jsonValue("[350,215]") else (if (truth(p.assistedOne)) jsonValue("[275,215]") else (if (truth(p.wide)) jsonValue("[230,220]") else jsonValue("[290,220]"))))
                    f.p[6] = (if (truth((p.grip === "diamond"))) jsonValue("[370,215]") else (if (truth(p.assistedOne)) jsonValue("[565,245]") else (if (truth(p.wide)) jsonValue("[490,220]") else jsonValue("[435,285]"))))
                    f.p[3] = arrayOf<dynamic>(jsSubtract(285, jsMultiply(20, a)), jsAdd(282, jsMultiply(35, a)))
                    f.p[5] = arrayOf<dynamic>(jsAdd(435, jsMultiply(20, a)), jsAdd(282, jsMultiply(35, a)))
                    f.detail.pushDepth23 = a
                    view = "top"
                }
                if (truth(p.dive)) {
                    val phase: dynamic = (if (truth(jsLess(t, 0.5))) jsMultiply(t, 2) else jsMultiply(jsSubtract(1, t), 2))
                    val q: dynamic = (if (truth(jsLess(phase, 0.6))) jsDivide(phase, 0.6) else jsDivide(jsSubtract(phase, 0.6), 0.4))
                    val from: dynamic = (if (truth(jsLess(phase, 0.6))) pike(0) else push(1))
                    val to: dynamic = (if (truth(jsLess(phase, 0.6))) push(1) else push(0))
                    f = cp(from)
                    f.p = from.p.map((fun(point: dynamic, j: dynamic): dynamic {
                        return mix(point, to.p[j], q)
                    }))
                }
            }
            if (truth(dynamicValue(jsonValue("[\"pike\",\"pike19\"]")).includes(kind))) {
                f = pike(a, p)
                view = "side"
                posture = "prone"
            }
            if (truth((kind === "plank"))) {
                f = plank(t, p)
                view = (if (truth(jsOr(p.toe) { p.jack })) "top" else "side")
                posture = "prone"
                if (truth(jsAnd((!truth(e.pack23))) { (!truth(e.pack30)) })) {
                    f.p[1] = jsonValue("[280,599]")
                    f.p[2] = jsonValue("[443,633]")
                    f.p[0] = jsonValue("[221,587]")
                    f.p[3] = jsonValue("[280,700]")
                    f.p[4] = jsonValue("[180,700]")
                    f.p[5] = jsonValue("[267,687]")
                    f.p[6] = jsonValue("[167,687]")
                    f.p[7] = mix(f.p[2], f.p[8], 0.5)
                    f.p[9] = mix(add(f.p[2], jsonValue("[-13,-13]")), f.p[10], 0.5)
                }
            }
            if (truth((kind === "renegade"))) {
                f = renegade(t, p)
                view = "side"
                posture = "prone"
            }
            if (truth(dynamicValue(jsonValue("[\"side-plank\",\"sideplank\",\"full-sideplank19\",\"sideplank-lift19\"]")).includes(kind))) {
                f = sidePlank(t, jsObject { obj667 ->
                    copyFields(obj667, p)
                    obj667["hipLift"] = jsOr(p.hipLift) { (kind === "sideplank-lift19") }
                }, (kind !== "sideplank"))
                view = "lying"
                posture = "side-lying"
            }
            if (truth((kind === "ankledrive"))) {
                f = ankleDrive(a)
                view = "side"
            }
            if (truth((kind === "machine-press"))) {
                f = pressMachine(a, p)
                view = "side"
            }
            if (truth(dynamicValue(jsonValue("[\"lateral\",\"seatedlateral\",\"singlelateral\",\"overhead\",\"overheadpress\",\"seatedpress\",\"singleoverhead\"]")).includes(kind))) {
                f = lift(a, jsObject { obj668 ->
                    copyFields(obj668, p)
                    obj668["seated"] = jsOr(p.seated) { kind.startsWith("seated") }
                    obj668["single"] = jsOr(p.single) { kind.startsWith("single") }
                }, (if (truth(jsOr(kind.includes("overhead")) { kind.includes("press") })) "overhead" else "lateral"))
                view = "front"
            }
            if (truth(dynamicValue(jsonValue("[\"bridge\",\"dbbridge\",\"singlebridge\"]")).includes(kind))) {
                f = bridge(a, jsObject { obj669 ->
                    copyFields(obj669, p)
                    obj669["single"] = jsOr(p.single) { (kind === "singlebridge") }
                })
                view = "side"
                posture = "supine"
            }
            if (truth((kind === "ham-slide"))) {
                f = bridge(a, jsObject { obj670 ->
                    copyFields(obj670, p)
                    obj670["walkout"] = true
                })
                view = "side"
                posture = "supine"
            }
            if (truth((kind === "hipthrust"))) {
                f = thrust(a, p)
                view = "side"
                posture = "supine"
            }
            if (truth((kind === "legpress"))) {
                f = legpress(a, p)
                view = "side"
            }
            if (truth((kind === "hamcurl"))) {
                f = ham(a, p)
                view = "side"
                posture = (if (truth(p.lying)) "prone" else "upright")
            }
            if (truth(dynamicValue(jsonValue("[\"leg-extension\",\"legextend\"]")).includes(kind))) {
                f = extension(a)
                view = "side"
            }
            if (truth((kind === "hip-machine"))) {
                f = hipMachine(a, p)
                view = "front"
            }
            if (truth(dynamicValue(jsonValue("[\"curl\",\"hammer\",\"seatedcurl\",\"altcurl\",\"althammer\"]")).includes(kind))) {
                f = curl(a, jsObject { obj671 ->
                    copyFields(obj671, p)
                    obj671["seated"] = jsOr(p.seated) { (kind === "seatedcurl") }
                })
                view = "side"
                if (truth(dynamicValue(jsonValue("[\"altcurl\",\"althammer\"]")).includes(kind))) {
                    val other: dynamic = curl(wave(jsAdd(t, 0.5)))
                    f.p[5] = other.p[5]
                    f.p[6] = other.p[6]
                }
                if (truth(p.cross)) {
                    run {
                        val target676: dynamic = f.p[4]
                        val key677: dynamic = 0
                        target676[key677] = jsSubtract(target676[key677], jsMultiply(35, a))
                    }
                    f.p[3] = ik(f.p[1], f.p[4], 95, (-numeric(1)))
                }
                f.detail.grip30 = (if (truth(p.pronate)) "zottman" else (if (truth(jsOr(dynamicValue(jsRegExp("hammer", "")).test(kind)) { p.cross })) "neutral" else "supinated"))
            }
            if (truth(dynamicValue(jsonValue("[\"frontlift\",\"singlefront\",\"front-seated-reference\"]")).includes(kind))) {
                f = (if (truth((kind === "front-seated-reference"))) seated() else side())
                if (true) {
                    val iterator682: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-13]]"))
                    while (true) {
                        val next683: dynamic = iterator682.next()
                        if (truth(next683.done)) break
                        var el: dynamic = absent
                        var ha: dynamic = absent
                        var o: dynamic = absent
                        val unpacked684: dynamic = fromIterable(next683.value)
                        el = unpacked684[0]
                        ha = unpacked684[1]
                        o = unpacked684[2]
                        val u: dynamic = (if (truth(jsAnd((kind === "singlefront")) { o })) 0 else a)
                        val th: dynamic = jsDivide(jsMultiply(u, global_Math.PI), 2)
                        f.p[el] = arrayOf<dynamic>(jsAdd(jsAdd(f.p[1][0], jsMultiply(95, global_Math.sin(th))), o), jsAdd(jsAdd(f.p[1][1], jsMultiply(95, global_Math.cos(th))), o))
                        f.p[ha] = arrayOf<dynamic>(jsAdd(jsAdd(f.p[1][0], jsMultiply(185, global_Math.sin(th))), o), jsAdd(jsAdd(f.p[1][1], jsMultiply(185, global_Math.cos(th))), o))
                    }
                }
                view = "side"
                feet(f)
            }
            if (truth(jsAnd((kind === "tricep")) { e.gymOnly23 })) {
                f = tricep(a, p)
                view = "side"
            }
            if (truth(dynamicValue(jsonValue("[\"hip-extension\",\"hip-flexion\"]")).includes(kind))) {
                f = hipStanding(a, p, kind)
                view = "side"
            }
            if (truth(dynamicValue(jsonValue("[\"hip-abduction\",\"hip-adduction\"]")).includes(kind))) {
                f = front()
                val u: dynamic = (if (truth((kind === "hip-adduction"))) jsSubtract(1, a) else a)
                f.p[9] = arrayOf<dynamic>(jsAdd(390, jsMultiply(120, global_Math.sin(jsMultiply(0.45, u)))), jsAdd(435, jsMultiply(120, global_Math.cos(jsMultiply(0.45, u)))))
                f.p[10] = arrayOf<dynamic>(jsAdd(390, jsMultiply(260, global_Math.sin(jsMultiply(0.45, u)))), jsAdd(435, jsMultiply(260, global_Math.cos(jsMultiply(0.45, u)))))
                view = "front"
                feet(f, jsonValue("[-0.05,1]"), jsonValue("[0.1,1]"))
            }
            if (truth((kind === "cycle"))) {
                f = cycle(t, p)
                view = "side"
            }
            if (truth((kind === "squat-lunge"))) {
                val stand: dynamic = squat(0)
                val down: dynamic = squat(1)
                val step: dynamic = lunge(0)
                val low: dynamic = lunge(1)
                f = poseTrack(t, arrayOf<dynamic>(arrayOf<dynamic>(0, stand), arrayOf<dynamic>(0.2, down), arrayOf<dynamic>(0.4, stand), arrayOf<dynamic>(0.5, step), arrayOf<dynamic>(0.7, low), arrayOf<dynamic>(0.86, step), arrayOf<dynamic>(1, stand)))
                view = "side"
            }
            if (truth((kind === "thruster"))) {
                val stand: dynamic = squat(0)
                val down: dynamic = squat(1)
                val up: dynamic = squat(0)
                if (true) {
                    val iterator693: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-13]]"))
                    while (true) {
                        val next694: dynamic = iterator693.next()
                        if (truth(next694.done)) break
                        var el: dynamic = absent
                        var ha: dynamic = absent
                        var o: dynamic = absent
                        val unpacked695: dynamic = fromIterable(next694.value)
                        el = unpacked695[0]
                        ha = unpacked695[1]
                        o = unpacked695[2]
                        up.p[ha] = arrayOf<dynamic>(jsAdd(350, o), jsAdd(115, o))
                        up.p[el] = ik(up.p[1], up.p[ha], 95, 1)
                    }
                }
                f = poseTrack(t, arrayOf<dynamic>(arrayOf<dynamic>(0, stand), arrayOf<dynamic>(0.3, down), arrayOf<dynamic>(0.55, stand), arrayOf<dynamic>(0.78, up), arrayOf<dynamic>(1, stand)))
                view = "side"
            }
            if (truth((kind === "woodchop"))) {
                f = front()
                f.p[4] = arrayOf<dynamic>(jsAdd(265, jsMultiply(205, a)), jsAdd(205, jsMultiply(265, a)))
                f.p[6] = add(f.p[4], jsonValue("[20,0]"))
                f.p[3] = mix(jsonValue("[278,223]"), jsonValue("[400,330]"), a)
                f.p[5] = mix(jsonValue("[360,215]"), jsonValue("[460,360]"), a)
                f.detail.turn30 = a
                feet(f, jsonValue("[-0.1,1]"), jsonValue("[0.1,1]"))
                view = "front"
            }
            if (truth(jsAnd((kind === "calf")) { e.pack30 })) {
                f = (if (truth(p.seated)) seated() else (if (truth(p.hinged)) hinge(0.7) else side()))
                if (true) {
                    val iterator710: dynamic = iteratorOf((if (truth(p.seated)) jsonValue("[8,10]") else jsonValue("[0,1,2,3,4,5,6,7,8,9,10]")))
                    while (true) {
                        val next711: dynamic = iterator710.next()
                        if (truth(next711.done)) break
                        var i: dynamic = absent
                        i = next711.value
                        run {
                            val target712: dynamic = f.p[i]
                            val key713: dynamic = 1
                            target712[key713] = jsSubtract(target712[key713], jsMultiply((if (truth(p.seated)) 23 else 20), a))
                        }
                    }
                }
                f.detail.calf30 = true
                f.detail.calfAmount30 = a
                view = "side"
                feet(f)
            }
            if (truth(jsAnd((kind === "bear")) { e.pack30 })) {
                f = global_RigCorrections24.bear(t)
                f.p[6] = mix(f.p[6], jsonValue("[150,415]"), a)
                f.p[5] = ik(f.p[1], f.p[6], 100, (-numeric(1)))
                view = "side"
                posture = "prone"
            }
            if (truth(jsAnd((!truth(f))) { e.pack30 })) {
                if (truth((kind === "ankle"))) {
                    f = seated()
                    f.detail.feet = "toelift"
                    f.detail.toeAmount = a
                    view = "side"
                } else {
                    if (truth(jsOr((kind === "crunch")) { (kind === "back-extension") })) {
                        f = seated()
                        val sign: dynamic = (if (truth((kind === "crunch"))) 1 else (-numeric(1)))
                        f.p[1] = arrayOf<dynamic>(jsAdd(280, jsMultiply(jsMultiply(45, sign), a)), jsAdd(320, jsMultiply(16, a)))
                        neutralHead(f)
                        arms(f, arrayOf<dynamic>(arrayOf<dynamic>(jsAdd(f.p[1][0], 35), jsAdd(f.p[1][1], 20))), 100, 1)
                        if (truth(p.kneeling)) {
                            f.equipment = js("[]")
                            f.p[7] = jsonValue("[350,700]")
                            f.p[8] = jsonValue("[240,700]")
                            f.p[9] = jsonValue("[337,687]")
                            f.p[10] = jsonValue("[227,687]")
                        }
                        view = "side"
                    } else {
                        if (truth((kind === "rotation"))) {
                            f = front(true)
                            f.detail.turn30 = a
                            run {
                                val target740: dynamic = f.p[1]
                                val key741: dynamic = 0
                                target740[key741] = jsAdd(target740[key741], jsMultiply(12, jsSubtract(jsMultiply(2, a), 1)))
                            }
                            run {
                                val target742: dynamic = f.p[0]
                                val key743: dynamic = 0
                                target742[key743] = jsAdd(target742[key743], jsMultiply(12, jsSubtract(jsMultiply(2, a), 1)))
                            }
                            view = "front"
                        } else {
                            if (truth((kind === "pullup"))) {
                                f = lift(jsSubtract(1, a), jsonValue("{\"seated\":false}"), "overhead")
                                if (true) {
                                    val iterator744: dynamic = iteratorOf(jsonValue("[0,1,2]"))
                                    while (true) {
                                        val next745: dynamic = iterator744.next()
                                        if (truth(next745.done)) break
                                        var i: dynamic = absent
                                        i = next745.value
                                        run {
                                            val target746: dynamic = f.p[i]
                                            val key747: dynamic = 1
                                            target746[key747] = jsSubtract(target746[key747], jsMultiply(80, a))
                                        }
                                    }
                                }
                                f.p[4] = jsonValue("[250,120]")
                                f.p[6] = jsonValue("[470,120]")
                                f.p[3] = ik(arrayOf<dynamic>(318, f.p[1][1]), f.p[4], 95, (-numeric(1)))
                                f.p[5] = ik(arrayOf<dynamic>(402, f.p[1][1]), f.p[6], 95, 1)
                                f.p[7] = arrayOf<dynamic>(330, jsSubtract(565, jsMultiply(80, a)))
                                f.p[9] = arrayOf<dynamic>(390, jsSubtract(565, jsMultiply(80, a)))
                                f.p[8] = arrayOf<dynamic>(355, jsSubtract(690, jsMultiply(80, a)))
                                f.p[10] = arrayOf<dynamic>(415, jsSubtract(690, jsMultiply(80, a)))
                                f.detail.assist30 = "pull"
                                f.detail.padY30 = jsSubtract(582, jsMultiply(80, a))
                                view = "front"
                            } else {
                                if (truth((kind === "dip"))) {
                                    f = side()
                                    if (true) {
                                        val iterator768: dynamic = iteratorOf(jsonValue("[0,1,2,7,8,9,10]"))
                                        while (true) {
                                            val next769: dynamic = iterator768.next()
                                            if (truth(next769.done)) break
                                            var i: dynamic = absent
                                            i = next769.value
                                            run {
                                                val target770: dynamic = f.p[i]
                                                val key771: dynamic = 1
                                                target770[key771] = jsAdd(target770[key771], jsSubtract(jsMultiply(40, a), 25))
                                            }
                                        }
                                    }
                                    arms(f, jsonValue("[[370,440]]"), 100, (-numeric(1)))
                                    f.detail.assist30 = "dip"
                                    f.detail.padY30 = jsAdd(f.p[7][1], 16)
                                    view = "side"
                                } else {
                                    if (truth((kind === "hip9090"))) {
                                        f = front()
                                        f.p[2] = jsonValue("[360,595]")
                                        f.p[1] = jsonValue("[360,425]")
                                        f.p[0] = jsonValue("[360,365]")
                                        f.p[7] = arrayOf<dynamic>(jsAdd(240, jsMultiply(80, a)), 650)
                                        f.p[8] = arrayOf<dynamic>(jsAdd(240, jsMultiply(125, a)), 545)
                                        f.p[9] = arrayOf<dynamic>(jsSubtract(480, jsMultiply(80, a)), 650)
                                        f.p[10] = arrayOf<dynamic>(jsSubtract(580, jsMultiply(125, a)), 650)
                                        f.p[3] = jsonValue("[290,505]")
                                        f.p[4] = jsonValue("[270,650]")
                                        f.p[5] = jsonValue("[430,505]")
                                        f.p[6] = jsonValue("[450,650]")
                                        f.detail.hip9090 = true
                                        view = "front"
                                    } else {
                                        if (truth((kind === "deadbug"))) {
                                            val m: dynamic = global_MotionDB.deadbug
                                            f = cp(m.frames[jsMod(global_Math.floor(jsMultiply(t, 48)), 48)])
                                            if (truth(p.heelSupport)) {
                                                f.p[8][1] = global_Math.max(f.p[8][1], 625)
                                                f.detail.floor23 = 640
                                            }
                                            if (truth(p.press)) {
                                                f.detail.opposite23 = arrayOf<dynamic>(wave(t), wave(jsAdd(t, 0.5)))
                                            }
                                            view = "side"
                                            posture = "supine"
                                        } else {
                                            if (truth((kind === "wall-angel"))) {
                                                f = lift(a, js("({})"), "overhead")
                                                f.equipment = jsonValue("[[\"wall\",[140,160,580,730]]]")
                                                f.detail.view21 = "rear"
                                                view = "rear"
                                            } else {
                                                if (truth(jsOr(jsOr((kind === "agility")) { (kind === "step") }) { (kind === "elliptical") })) {
                                                    val s: dynamic = global_Math.sin(jsMultiply(jsMultiply(t, global_Math.PI), 2))
                                                    f = R.march(jsMultiply(global_Math.max(0, s), 0.6), jsMultiply(global_Math.max(0, (-numeric(s))), 0.6))
                                                    if (truth((kind === "step"))) {
                                                        f.p[2] = arrayOf<dynamic>(350, jsSubtract(430, jsMultiply(15, global_Math.cos(jsMultiply(jsMultiply(t, global_Math.PI), 4)))))
                                                        f.p[1] = arrayOf<dynamic>(350, jsSubtract(f.p[2][1], 170))
                                                        neutralHead(f)
                                                        f.p[8] = arrayOf<dynamic>(410, jsAdd(635, jsMultiply(35, s)))
                                                        f.p[10] = arrayOf<dynamic>(325, jsSubtract(635, jsMultiply(35, s)))
                                                        f.p[7] = ik(f.p[2], f.p[8], 140, 1)
                                                        f.p[9] = ik(f.p[2], f.p[10], 140, 1)
                                                        f.detail.stairs30 = true
                                                    }
                                                    if (truth((kind === "elliptical"))) {
                                                        f.p[8] = arrayOf<dynamic>(jsAdd(385, jsMultiply(55, s)), jsAdd(675, jsMultiply(15, global_Math.cos(jsMultiply(jsMultiply(t, global_Math.PI), 2)))))
                                                        f.p[10] = arrayOf<dynamic>(jsSubtract(385, jsMultiply(55, s)), jsSubtract(675, jsMultiply(15, global_Math.cos(jsMultiply(jsMultiply(t, global_Math.PI), 2)))))
                                                        f.p[7] = ik(f.p[2], f.p[8], 140, 1)
                                                        f.p[9] = ik(f.p[2], f.p[10], 140, 1)
                                                        f.p[4] = arrayOf<dynamic>(jsSubtract(510, jsMultiply(40, s)), 375)
                                                        f.p[6] = arrayOf<dynamic>(jsAdd(510, jsMultiply(40, s)), 362)
                                                        f.p[3] = ik(f.p[1], f.p[4], 100, (-numeric(1)))
                                                        f.p[5] = ik(add(f.p[1], jsonValue("[-13,-13]")), f.p[6], 100, (-numeric(1)))
                                                        f.detail.elliptical30 = true
                                                    }
                                                    feet(f)
                                                    view = "side"
                                                } else {
                                                    if (truth((kind === "jump"))) {
                                                        f = squat((if (truth(jsLess(t, 0.55))) wave(jsDivide(t, 0.55)) else 0))
                                                        val flight: dynamic = (if (truth(jsMore(t, 0.55))) wave(jsDivide(jsSubtract(t, 0.55), 0.45)) else 0)
                                                        if (true) {
                                                            val iterator842: dynamic = iteratorOf(f.p)
                                                            while (true) {
                                                                val next843: dynamic = iterator842.next()
                                                                if (truth(next843.done)) break
                                                                var q: dynamic = absent
                                                                q = next843.value
                                                                run {
                                                                    val target844: dynamic = q
                                                                    val key845: dynamic = 0
                                                                    target844[key845] = jsAdd(target844[key845], jsMultiply(45, flight))
                                                                }
                                                                run {
                                                                    val target846: dynamic = q
                                                                    val key847: dynamic = 1
                                                                    target846[key847] = jsSubtract(target846[key847], jsMultiply(48, flight))
                                                                }
                                                            }
                                                        }
                                                        view = "side"
                                                    } else {
                                                        if (truth((kind === "ski"))) {
                                                            f = hinge(jsMultiply(0.45, a))
                                                            arms(f, arrayOf<dynamic>(arrayOf<dynamic>(jsSubtract(470, jsMultiply(35, a)), jsAdd(195, jsMultiply(280, a)))), 100, (-numeric(1)))
                                                            f.detail.ski30 = true
                                                            view = "side"
                                                        } else {
                                                            if (truth(jsOr(jsOr((kind === "cable-row")) { (kind === "straight-pull") }) { (kind === "fly") })) {
                                                                val parent: dynamic = global_Engine.byId(e.animationParent30)
                                                                val m: dynamic = jsOr(global_MotionDB[jsOr(onPresent(parent) { value850 -> value850.baseId }) { onPresent(parent) { value851 -> value851.id } }]) { global_MotionDB[e.baseId] }
                                                                f = cp(m.frames[jsMod(global_Math.floor(jsMultiply(t, 48)), 48)])
                                                                view = m.view21.primary
                                                                if (truth(jsAnd(p.seated) { (!truth(f.equipment.some((fun(x: dynamic): dynamic {
                                                                    return jsOr((x[0] === "chair")) { (x[0] === "bench") }
                                                                })))) })) {
                                                                    f = (if (truth((kind === "fly"))) lift(a, jsonValue("{\"seated\":true}")) else pressMachine(jsSubtract(1, a), js("({})")))
                                                                    view = (if (truth((kind === "fly"))) "front" else "side")
                                                                }
                                                            } else {
                                                                throw global_Error(jsAdd(jsAdd(jsAdd("Démonstration V30 manquante ", e.id), " "), kind))
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
            if (truth((!truth(f)))) {
                return jsObject { obj852 ->
                    obj852["frame"] = cp(raw.frames[jsMod(global_Math.floor(jsMultiply(t, 48)), 48)])
                    obj852["view"] = raw.view21.primary
                    obj852["posture"] = global_Anatomy2D22.posture(raw)
                    obj852["corrected"] = false
                }
            }
            if (truth(jsOr(jsOr(p.rack) { (kind === "goblet") }) { (kind === "doublefront") })) {
                arms(f, arrayOf<dynamic>(add(f.p[1], jsonValue("[28,25]"))), 95, 1)
                f.loads = (if (truth((kind === "goblet"))) arrayOf<dynamic>(mix(f.p[4], f.p[6], 0.5)) else arrayOf<dynamic>(cp(f.p[4]), cp(f.p[6])))
            }
            if (truth(p.airplane)) {
                f.p[9] = mix(f.p[2], arrayOf<dynamic>(jsSubtract(f.p[2][0], jsMultiply(140, a)), jsSubtract(500, jsMultiply(120, a))), 0.5)
                f.p[10] = arrayOf<dynamic>(jsSubtract(f.p[2][0], jsMultiply(210, a)), jsSubtract(700, jsMultiply(260, a)))
                f.p[6] = jsonValue("[555,400]")
                f.p[5] = ik(f.p[1], f.p[6], 105, (-numeric(1)))
                f.equipment = jsonValue("[[\"wall\",[570,260,582,715]]]")
                f.detail.turn30 = a
            }
            if (truth(jsAnd(jsAnd(e.loaded23) { (!truth(f.loads.length)) }) { (!truth(dynamicValue(jsonValue("[\"barbell\",\"smith\",\"machine\",\"cable\",\"cardio\"]")).includes(p.gear))) })) {
                f.loads = (if (truth(jsOr(p.singleLoad) { p.offset })) arrayOf<dynamic>(cp(f.p[4])) else arrayOf<dynamic>(cp(f.p[4]), cp(f.p[6])))
            }
            if (truth(jsAnd(jsAnd(jsAnd(jsAnd(onPresent(e.requirements) { value869 -> value869.includes("db") }) { (!truth(e.pack30)) }) { (!truth(e.pack23)) }) { (e.unit !== "sec") }) { (!truth(f.loads.length)) })) {
                f.loads = arrayOf<dynamic>(cp(f.p[4]), cp(f.p[6]))
            }
            if (truth(e.gymOnly23)) {
                f.detail.gear23 = jsOr(p.gear) { "machine" }
                f.detail.recipe23 = kind
                f.detail.param23 = p
                if (truth(jsAnd(dynamicValue(jsonValue("[\"barbell\",\"smith\"]")).includes(p.gear)) { p.back })) {
                    f.detail.barY30 = jsAdd(f.p[1][1], 10)
                }
                if (truth((p.gear === "cable"))) {
                    f.detail.cable23 = (if (truth(jsOr((kind === "hip-flexion")) { kind.startsWith("hip-") })) jsonValue("[100,700]") else (if (truth((kind === "crunch"))) jsonValue("[520,150]") else (if (truth(dynamicValue(jsonValue("[\"curl\",\"lateral\"]")).includes(kind))) jsonValue("[100,700]") else jsonValue("[650,300]"))))
                }
            }
            if (truth(jsAnd(e.gymOnly23) { (!truth(f.equipment.some((fun(x: dynamic): dynamic {
                return (x[0] === "bounds23")
            })))) })) {
                f.equipment.push(jsonValue("[\"bounds23\",[95,95,690,735]]"))
            }
            if (truth(jsAnd(p.db) { (!truth(f.loads.length)) })) {
                f.loads = (if (truth(jsOr(p.offset) { p.singleLoad })) arrayOf<dynamic>(cp(f.p[4])) else arrayOf<dynamic>(cp(f.p[4]), cp(f.p[6])))
            }
            f.detail.view21 = view
            return jsObject { obj886 ->
                obj886["frame"] = f
                obj886["view"] = view
                obj886["posture"] = jsOr(posture) { "upright" }
                obj886["corrected"] = true
            }
        }
        cadence = fun(e: dynamic): dynamic {
            val name: dynamic = e.name.toLowerCase()
            val seconds: dynamic = jsOr(e.tempoSeconds18) { 4 }
            val pause: dynamic = jsOr(jsOr((e.renderWarp23 === "pause")) { (e.variant === "pause") }) { dynamicValue(jsRegExp("pausa", "")).test(name) }
            val slow: dynamic = jsOr(jsOr((e.renderWarp23 === "slow")) { dynamicValue(jsonValue("[\"slow\",\"controlled\"]")).includes(e.variant) }) { dynamicValue(jsRegExp("tres segundos|retorno lento|descenso controlado", "")).test(name) }
            return jsObject { obj889 ->
                obj889["pause"] = pause
                obj889["slow"] = slow
                obj889["seconds"] = seconds
                obj889["pauseStart"] = jsDivide(jsSubtract(1, global_Math.min(0.75, jsDivide(2, seconds))), 2)
                obj889["pauseEnd"] = jsDivide(jsAdd(1, global_Math.min(0.75, jsDivide(2, seconds))), 2)
                obj889["slowSplit"] = (if (truth(dynamicValue(jsRegExp("descenso", "")).test(name))) global_Math.min(0.8, jsDivide(3, seconds)) else global_Math.max(0.2, jsSubtract(1, jsDivide(3, seconds))))
            }
        }
        time = fun(e: dynamic, t: dynamic): dynamic {
            val kind: dynamic = jsOr(e.variant) { "base" }
            val name: dynamic = e.name.toLowerCase()
            val c: dynamic = cadence(e)
            if (truth(c.pause)) {
                if (truth(jsLess(t, c.pauseStart))) {
                    return jsMultiply(jsDivide(t, c.pauseStart), 0.5)
                }
                if (truth(jsLess(t, c.pauseEnd))) {
                    return 0.5
                }
                return jsAdd(0.5, jsMultiply(jsDivide(jsSubtract(t, c.pauseEnd), jsSubtract(1, c.pauseEnd)), 0.5))
            }
            if (truth(dynamicValue(jsRegExp("repetición y media", "")).test(name))) {
                val points: dynamic = jsonValue("[[0,0],[0.25,0.5],[0.5,0.25],[0.7,0.5],[1,1]]")
                val i: dynamic = points.findIndex((fun(q: dynamic): dynamic {
                    return jsAtLeast(q[0], t)
                }))
                val lo: dynamic = points[global_Math.max(0, jsSubtract(i, 1))]
                val hi: dynamic = points[global_Math.max(0, i)]
                return (if (truth((hi[0] === lo[0]))) lo[1] else jsAdd(lo[1], jsDivide(jsMultiply(jsSubtract(hi[1], lo[1]), jsSubtract(t, lo[0])), jsSubtract(hi[0], lo[0]))))
            }
            if (truth(c.slow)) {
                return (if (truth(jsLess(t, c.slowSplit))) jsMultiply(jsDivide(t, c.slowSplit), 0.5) else jsAdd(0.5, jsMultiply(jsDivide(jsSubtract(t, c.slowSplit), jsSubtract(1, c.slowSplit)), 0.5)))
            }
            if (truth((kind === "easy"))) {
                return (if (truth(jsLess(t, 0.5))) jsMultiply(t, 0.7) else jsAdd(0.35, jsMultiply(jsSubtract(t, 0.5), 1.3)))
            }
            if (truth((kind === "short"))) {
                return (if (truth(jsLess(t, 0.6))) jsDivide(t, 0.6) else 0)
            }
            return t
        }
        `get` = fun(id: dynamic): dynamic {
            val e: dynamic = global_Engine.byId(id)
            if (truth((!truth(e)))) {
                throw global_Error(jsAdd("Ejercicio desconocido ", id))
            }
            if (truth((!truth(cache.has(id))))) {
                val parent: dynamic = jsOr(jsOr(e.animationParent30) { e.baseId }) { id }
                val raw: dynamic = jsOr(jsOr(global_MotionDB[parent]) { global_MotionDB[onPresent(global_Engine.byId(parent)) { value894 -> value894.baseId }] }) { global_MotionDB[id] }
                if (truth((!truth(raw)))) {
                    throw global_Error(jsAdd("Modelo fuente ausente ", id))
                }
                val samples: dynamic = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                    val t: dynamic = time(e, jsDivide(i, 48))
                    return (if (truth(root.Motion31)) root.Motion31.resolve(e, t, raw, resolve) else resolve(e, t, raw))
                }))
                val first: dynamic = samples[0]
                val m: dynamic = jsObject { obj895 ->
                    copyFields(obj895, cp(raw))
                    obj895["id"] = id
                    obj895["name"] = e.name
                    obj895["specific30"] = true
                    obj895["hold"] = jsAnd(raw.hold) { (!truth(e.pack30)) }
                    obj895["frames"] = samples.map((fun(x: dynamic): dynamic {
                        return x.frame
                    }))
                    obj895["view21"] = jsObject { obj896 ->
                        copyFields(obj896, cp(raw.view21))
                        obj896["primary"] = first.view
                        obj896["label"] = jsonValue("{\"side\":\"Lateral\",\"front\":\"Frontal\",\"rear\":\"Posterior\",\"top\":\"Superior\",\"lying\":\"Lateral del apoyo\"}")[first.view]
                        obj896["mirror"] = false
                    }
                    obj895["posture23"] = first.posture
                    obj895["facing23"] = (if (truth(jsAnd((first.view === "side")) { (first.posture === "upright") })) "right" else (if (truth(jsAnd((first.view === "side")) { (first.posture === "prone") })) "left" else raw.facing23))
                    obj895["corrected30"] = first.corrected
                }
                if (truth(first.corrected)) {
                    m.view21.project = dynamicValue(null)
                    m.view21.inset = (if (truth((first.view === "top"))) (if (truth(jsOr((e.recipe23 === "push")) { (e.recipe30 === "push") })) "pushDepth23" else "support23") else (if (truth(m.hold)) "hold23" else null))
                    if (truth(onPresent(e.param23) { value901 -> value901.frog })) {
                        m.view21.inset = "frog23"
                    }
                    if (truth(onPresent(e.param23) { value904 -> value904.pronate })) {
                        m.view21.inset = "grip23"
                    }
                    if (truth(onPresent(e.param23) { value907 -> value907.airplane })) {
                        m.view21.inset = "hip23"
                    }
                }
                if (truth(jsOr(dynamicValue(jsonValue("[\"ankle\",\"toelift\",\"seatedheel\",\"heeltoe\"]")).includes(raw.view21.family)) { (e.recipe30 === "ankle") })) {
                    m.view21.inset = "foot30"
                }
                if (truth((e.recipe30 === "rotation"))) {
                    m.view21.inset = "rotation23"
                }
                if (truth(jsAnd((e.recipe30 === "deadbug")) { e.param30.press })) {
                    m.view21.inset = "opposite23"
                }
                if (truth(jsAnd((e.recipe23 === "deadlift")) { e.param23.wide })) {
                    m.view21.inset = "hinge30"
                }
                if (true) {
                    val iterator918: dynamic = iteratorOf(m.frames)
                    while (true) {
                        val next919: dynamic = iterator918.next()
                        if (truth(next919.done)) break
                        var f: dynamic = absent
                        f = next919.value
                        f.detail.specific30 = id
                        f.detail.tempo30 = jsOr(e.tempoSeconds18) { 4 }
                        f.detail.stage18 = (if (truth(m.hold)) "POSTURA · RESPIRA SIN FORZAR" else (if (truth(jsAnd((time(e, 0) === time(e, jsDivide(1, 48)))) { (e.variant !== "base") })) "CONTROL DE CADENCIA" else f.detail.stage18))
                    }
                }
                if (truth(dynamicValue(jsonValue("[\"pau-armpulses\",\"pau-armcurl\",\"squatjump\",\"lungeknee\",\"y-t-w19\"]")).includes(raw.view21.family))) {
                    val frames: dynamic = cp(m.frames)
                    if (true) {
                        var i: dynamic = absent
                        i = 0
                        var firstIteration926 = true
                        while (true) {
                            if (!firstIteration926) {
                                run {
                                    val previous927: dynamic = numeric(i)
                                    i = jsAdd(previous927, 1)
                                }
                            }
                            firstIteration926 = false
                            if (!truth(jsLess(i, 48))) break
                            if (true) {
                                var j: dynamic = absent
                                j = 0
                                var firstIteration928 = true
                                while (true) {
                                    if (!firstIteration928) {
                                        run {
                                            val previous929: dynamic = numeric(j)
                                            j = jsAdd(previous929, 1)
                                        }
                                    }
                                    firstIteration928 = false
                                    if (!truth(jsLess(j, 11))) break
                                    if (true) {
                                        var k: dynamic = absent
                                        k = 0
                                        var firstIteration930 = true
                                        while (true) {
                                            if (!firstIteration930) {
                                                run {
                                                    val previous931: dynamic = numeric(k)
                                                    k = jsAdd(previous931, 1)
                                                }
                                            }
                                            firstIteration930 = false
                                            if (!truth(jsLess(k, 2))) break
                                            m.frames[i].p[j][k] = jsDivide(dynamicValue(jsonValue("[-2,-1,0,1,2]")).reduce((fun(sum: dynamic, o: dynamic): dynamic {
                                                return jsAdd(sum, frames[jsMod(jsAdd(jsAdd(i, o), 48), 48)].p[j][k])
                                            }), 0), 5)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (truth(root.Motion31)) {
                    root.Motion31.configureModel(e, m)
                }
                cache["set"](id, m)
            }
            return jsObject { obj934 ->
                obj934["e"] = e
                obj934["base"] = id
                obj934["model"] = cache["get"](id)
            }
        }
        extras = fun(f: dynamic, accent: dynamic): dynamic {
            if (truth((!truth(f.detail.specific30)))) {
                return ""
            }
            val p: dynamic = f.p
            val d: dynamic = f.detail
            val gear: dynamic = d.gear23
            var s: dynamic = ""
            val line: dynamic = (fun(a: dynamic, b: dynamic, arg958: dynamic): dynamic {
                var w: dynamic = absent
                w = definedOr(arg958) { 5 }
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", a.join(",")), "L"), b.join(",")), "\" fill=\"none\" stroke=\"#597584\" stroke-width=\""), w), "\" stroke-linecap=\"round\"/>")
            })
            if (truth((gear === "machine"))) {
                run {
                    s = jsAdd(s, jsAdd(line(jsonValue("[115,720]"), jsonValue("[620,720]"), 8), line(jsonValue("[135,720]"), jsonValue("[135,180]"), 8)))
                }
                run {
                    s = jsAdd(s, "<rect x=\"98\" y=\"320\" width=\"68\" height=\"280\" rx=\"9\" fill=\"#173341\" stroke=\"#6c8e9c\" stroke-width=\"3\"/>")
                }
                if (true) {
                    var i: dynamic = absent
                    i = 0
                    var firstIteration959 = true
                    while (true) {
                        if (!firstIteration959) {
                            run {
                                val previous960: dynamic = numeric(i)
                                i = jsAdd(previous960, 1)
                            }
                        }
                        firstIteration959 = false
                        if (!truth(jsLess(i, 8))) break
                        run {
                            s = jsAdd(s, line(arrayOf<dynamic>(104, jsAdd(350, jsMultiply(i, 28))), arrayOf<dynamic>(160, jsAdd(350, jsMultiply(i, 28))), 2))
                        }
                    }
                }
                if (truth((d.machine30 === "press"))) {
                    run {
                        s = jsAdd(s, jsAdd(line(jsonValue("[150,350]"), p[4], 4), line(jsonValue("[150,337]"), p[6], 3)))
                    }
                }
                if (truth(d.plate23)) {
                    run {
                        s = jsAdd(s, jsAdd(line(add(d.plate23, arrayOf<dynamic>((if (truth(d.legPress31)) 0 else 20), (-numeric(55)))), add(d.plate23, arrayOf<dynamic>((if (truth(d.legPress31)) 0 else 20), 55)), 14), line(jsonValue("[390,715]"), add(d.plate23, arrayOf<dynamic>((if (truth(d.legPress31)) 0 else 20), 55)), 7)))
                    }
                }
                if (truth(d.roller30)) {
                    run {
                        s = jsAdd(s, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<circle cx=\"", d.roller30[0]), "\" cy=\""), d.roller30[1]), "\" r=\"18\" fill=\"#233d4b\" stroke=\""), accent), "\" stroke-width=\"4\"/>"), line((if (truth((d.machine30 === "ham-standing"))) p[9] else jsonValue("[420,490]")), d.roller30, 7)))
                    }
                }
                if (truth(d.pads30)) {
                    if (true) {
                        val iterator961: dynamic = iteratorOf(jsonValue("[7,9]"))
                        while (true) {
                            val next962: dynamic = iterator961.next()
                            if (truth(next962.done)) break
                            var i: dynamic = absent
                            i = next962.value
                            run {
                                s = jsAdd(s, line(add(p[i], jsonValue("[-15,0]")), add(p[i], jsonValue("[15,0]")), 16))
                            }
                        }
                    }
                }
                if (truth(d.assist30)) {
                    run {
                        s = jsAdd(s, jsAdd(jsAdd(jsAdd(line((if (truth((d.assist30 === "pull"))) jsonValue("[230,120]") else jsonValue("[320,440]")), (if (truth((d.assist30 === "pull"))) jsonValue("[490,120]") else jsonValue("[420,440]")), 10), line(jsonValue("[235,720]"), jsonValue("[235,115]"), 6)), line(jsonValue("[490,720]"), jsonValue("[490,115]"), 6)), line(arrayOf<dynamic>(300, d.padY30), arrayOf<dynamic>(425, d.padY30), 16)))
                    }
                }
                if (truth(d.hack30)) {
                    run {
                        s = jsAdd(s, jsAdd(jsAdd(jsAdd(line(add(p[1], jsonValue("[-35,10]")), add(p[2], jsonValue("[-35,10]")), 15), line(jsonValue("[170,240]"), jsonValue("[320,690]"), 8)), line(jsonValue("[230,210]"), jsonValue("[380,675]"), 5)), line(jsonValue("[445,712]"), jsonValue("[550,675]"), 12)))
                    }
                }
                if (truth(onPresent(d.param23) { value963 -> value963.belt })) {
                    run {
                        s = jsAdd(s, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(line(add(p[2], jsonValue("[-20,0]")), add(p[2], jsonValue("[20,0]")), 15), line(p[2], add(p[2], jsonValue("[0,80]")), 3)), "<rect x=\""), jsSubtract(p[2][0], 20)), "\" y=\""), jsAdd(p[2][1], 75)), "\" width=\"40\" height=\"38\" rx=\"4\" fill=\"#2e4d5a\"/>"))
                    }
                }
                if (truth(jsAnd(d.calf30) { onPresent(d.param23) { value964 -> value964.seated } })) {
                    run {
                        s = jsAdd(s, jsAdd(line(add(p[7], jsonValue("[-30,-15]")), add(p[7], jsonValue("[25,-15]")), 18), line(jsonValue("[440,690]"), add(p[7], jsonValue("[25,-15]")), 8)))
                    }
                }
            }
            if (truth((gear === "smith"))) {
                run {
                    s = jsAdd(s, jsAdd(jsAdd(line(arrayOf<dynamic>(jsSubtract(p[1][0], 45), 130), arrayOf<dynamic>(jsSubtract(p[1][0], 45), 720), 8), line(arrayOf<dynamic>(jsAdd(p[1][0], 85), 130), arrayOf<dynamic>(jsAdd(p[1][0], 85), 720), 8)), line(arrayOf<dynamic>(jsSubtract(p[1][0], 70), 720), arrayOf<dynamic>(jsAdd(p[1][0], 110), 720), 8)))
                }
            }
            if (truth((gear === "cable"))) {
                val `param`: dynamic = jsOr(d.param23) { js("({})") }
                val k: dynamic = d.recipe23
                val anchor: dynamic = (if (truth((k === "woodchop"))) jsonValue("[100,140]") else (if (truth((k === "tricep"))) jsonValue("[550,120]") else (if (truth((k === "straight-pull"))) jsonValue("[620,110]") else jsOr(d.cable23) { jsonValue("[650,120]") })))
                if (truth((k === "fly"))) {
                    val y: dynamic = (if (truth(`param`.low)) 690 else (if (truth(`param`.high)) 130 else 285))
                    run {
                        s = jsAdd(s, jsAdd(jsAdd(jsAdd(line(jsonValue("[100,730]"), jsonValue("[100,110]"), 8), line(jsonValue("[620,730]"), jsonValue("[620,110]"), 8)), line(arrayOf<dynamic>(100, y), p[4], 2)), line(arrayOf<dynamic>(620, y), p[6], 2)))
                    }
                } else {
                    run {
                        s = jsAdd(s, line(arrayOf<dynamic>(anchor[0], 720), arrayOf<dynamic>(anchor[0], 100), 8))
                    }
                    run {
                        s = jsAdd(s, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<circle cx=\"", anchor[0]), "\" cy=\""), anchor[1]), "\" r=\"13\" fill=\"#1d3b4c\" stroke=\""), accent), "\" stroke-width=\"3\"/>"))
                    }
                    val handle: dynamic = (if (truth(onPresent(k) { value965 -> value965.startsWith("hip-") })) p[10] else (if (truth(`param`.single)) p[4] else mix(p[4], p[6], 0.5)))
                    run {
                        s = jsAdd(s, line(anchor, handle, 2))
                    }
                    if (truth(jsAnd((!truth(`param`.single))) { (!truth(onPresent(k) { value966 -> value966.startsWith("hip-") })) })) {
                        run {
                            s = jsAdd(s, line(p[4], p[6], 7))
                        }
                    }
                }
            }
            if (truth(dynamicValue(jsonValue("[\"cable-row\",\"erg-row\"]")).includes(d.recipe23))) {
                run {
                    s = jsAdd(s, jsAdd(line(add(p[8], jsonValue("[-12,10]")), add(p[8], jsonValue("[35,-8]")), 10), line(add(p[8], jsonValue("[15,2]")), arrayOf<dynamic>(jsAdd(p[8][0], 15), 715), 6)))
                }
                if (truth(onPresent(d.param23) { value967 -> value967.chestSupport })) {
                    run {
                        s = jsAdd(s, jsAdd(line(add(p[1], jsonValue("[34,35]")), add(p[1], jsonValue("[40,125]")), 15), line(add(p[1], jsonValue("[40,125]")), arrayOf<dynamic>(jsAdd(p[1][0], 40), 715), 5)))
                    }
                }
            }
            if (truth(d.bike30)) {
                val c: dynamic = d.bike30.center
                run {
                    s = jsAdd(s, jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(line(jsonValue("[230,715]"), jsonValue("[600,715]"), 8), line(jsonValue("[280,520]"), c, 7)), line(c, jsonValue("[565,470]"), 7)), "<circle cx=\""), c[0]), "\" cy=\""), c[1]), "\" r=\"85\" fill=\"none\" stroke=\"#496573\" stroke-width=\"8\"/>"), line(c, p[8], 4)), line(c, p[10], 4)))
                }
            }
            if (truth(d.stairs30)) {
                if (true) {
                    val iterator968: dynamic = iteratorOf(jsonValue("[8,10]"))
                    while (true) {
                        val next969: dynamic = iterator968.next()
                        if (truth(next969.done)) break
                        var i: dynamic = absent
                        i = next969.value
                        run {
                            s = jsAdd(s, jsAdd(line(add(p[i], jsonValue("[-25,9]")), add(p[i], jsonValue("[35,9]")), 10), line(add(p[i], jsonValue("[35,9]")), arrayOf<dynamic>(jsAdd(p[i][0], 35), 715), 5)))
                        }
                    }
                }
                run {
                    s = jsAdd(s, line(jsonValue("[230,715]"), jsonValue("[560,715]"), 9))
                }
            }
            if (truth(d.elliptical30)) {
                if (true) {
                    val iterator970: dynamic = iteratorOf(jsonValue("[8,10]"))
                    while (true) {
                        val next971: dynamic = iterator970.next()
                        if (truth(next971.done)) break
                        var i: dynamic = absent
                        i = next971.value
                        run {
                            s = jsAdd(s, line(add(p[i], jsonValue("[-15,9]")), add(p[i], jsonValue("[35,9]")), 12))
                        }
                    }
                }
                if (true) {
                    val iterator972: dynamic = iteratorOf(jsonValue("[4,6]"))
                    while (true) {
                        val next973: dynamic = iterator972.next()
                        if (truth(next973.done)) break
                        var i: dynamic = absent
                        i = next973.value
                        run {
                            s = jsAdd(s, line(p[i], arrayOf<dynamic>(jsSubtract(p[i][0], 40), 715), 7))
                        }
                    }
                }
                run {
                    s = jsAdd(s, line(jsonValue("[215,715]"), jsonValue("[610,715]"), 10))
                }
            }
            if (truth(d.ski30)) {
                run {
                    s = jsAdd(s, jsAdd(jsAdd(jsAdd(line(jsonValue("[600,110]"), jsonValue("[600,715]"), 8), line(jsonValue("[590,120]"), p[4], 2)), line(jsonValue("[610,130]"), p[6], 2)), line(jsonValue("[200,715]"), jsonValue("[640,715]"), 10)))
                }
            }
            if (truth(jsAnd(jsAnd(jsAnd(jsAnd((gear === "cardio")) { (!truth(d.bike30)) }) { (!truth(d.ski30)) }) { (!truth(d.stairs30)) }) { (!truth(d.elliptical30)) })) {
                run {
                    s = jsAdd(s, R.extras(f, accent))
                }
            }
            if (truth(d.cushion30)) {
                run {
                    s = jsAdd(s, "<ellipse cx=\"460\" cy=\"551\" rx=\"25\" ry=\"13\" fill=\"#60868e\"/>")
                }
            }
            return jsOr(jsAdd(s, jsOr(onPresent(root.Motion31) { value974 -> value974.extras(f, accent) }) { "" })) { "<g data-apparatus30=\"none\"></g>" }
        }
        inset = fun(kind: dynamic, f: dynamic, phase: dynamic, accent: dynamic): dynamic {
            val v31: dynamic = onPresent(root.Motion31) { value977 -> value977.inset(kind, f, phase, accent) }
            if (truth(v31)) {
                return v31
            }
            if (truth((kind === "hinge30"))) {
                val a: dynamic = jsOr(f.detail.hingeAmount30) { 0 }
                val g: dynamic = hinge(a)
                val origin: dynamic = g.p[2]
                val q: dynamic = (fun(p: dynamic): dynamic {
                    return arrayOf<dynamic>(jsAdd(185, jsMultiply(jsSubtract(p[0], 350), 0.18)), jsAdd(683, jsMultiply(jsSubtract(p[1], 475), 0.13)))
                })
                return jsAdd(jsAdd(jsAdd(jsAdd("<g data-detail30=\"hinge\"><rect x=\"58\" y=\"620\" width=\"604\" height=\"113\" rx=\"14\" fill=\"#102431\" stroke=\"#47606e\"/><text x=\"75\" y=\"642\" font-size=\"13\" fill=\"", accent), "\" font-family=\"sans-serif\">DE LADO · BISAGRA DE CADERA</text><path d=\""), dynamicValue(jsonValue("[[0,1],[1,2],[2,7],[7,8],[1,3],[3,4]]")).map((fun(arg980: dynamic): dynamic {
                    var i: dynamic = absent
                    var j: dynamic = absent
                    val unpacked981: dynamic = fromIterable(arg980)
                    i = unpacked981[0]
                    j = unpacked981[1]
                    return jsAdd(jsAdd(jsAdd("M", q(g.p[i]).join(",")), "L"), q(g.p[j]).join(","))
                })).join("")), "\" fill=\"none\" stroke=\"#dceaf0\" stroke-width=\"6\" stroke-linecap=\"round\"/><text x=\"75\" y=\"720\" font-size=\"12\" fill=\"#b0c6d1\" font-family=\"sans-serif\">Barra cerca de las piernas · columna estable · rodillas siguen las puntas</text></g>")
            }
            if (truth((kind !== "foot30"))) {
                return null
            }
            val a: dynamic = nullableOr(nullableOr(f.detail.toeAmount) { f.detail.amount }) { wave(jsDivide(phase, 48)) }
            val angle: dynamic = jsAdd((-numeric(0.5)), a)
            val turn: dynamic = jsDivide(jsMultiply(angle, 180), global_Math.PI)
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-detail30=\"foot\"><rect x=\"58\" y=\"620\" width=\"604\" height=\"113\" rx=\"14\" fill=\"#102431\" stroke=\"#47606e\"/><text x=\"75\" y=\"642\" font-size=\"13\" fill=\"", accent), "\" font-family=\"sans-serif\">AMPLIACIÓN · TOBILLO Y PUNTA</text><path d=\"M155 652V683\" stroke=\"#dceaf0\" stroke-width=\"13\" stroke-linecap=\"round\"/><g transform=\"rotate("), turn), " 155 683)\"><path d=\"M150 683H197\" stroke=\"#dceaf0\" stroke-width=\"14\" stroke-linecap=\"round\"/></g><circle cx=\"155\" cy=\"683\" r=\"5\" fill=\""), accent), "\"/><path d=\"M209 660q22 22 0 43m-5-6 5 6 5-5\" fill=\"none\" stroke=\""), accent), "\" stroke-width=\"3\"/><text x=\"75\" y=\"720\" font-size=\"12\" fill=\"#b0c6d1\" font-family=\"sans-serif\">La rodilla permanece estable · el movimiento se produce en el tobillo</text></g>")
        }
        preload = fun(id: dynamic): dynamic {
            if (truth(jsOr((!truth(root.fetch))) { loaded30.has(id) })) {
                return global_Promise.resolve()
            }
            if (truth(loading30.has(id))) {
                return loading30["get"](id)
            }
            val work: dynamic = global_fetch(jsAdd(jsAdd("demos30/", id), ".json"))["then"]((fun(r: dynamic): dynamic {
                if (truth((!truth(r.ok)))) {
                    throw global_Error("Modelo local ausente")
                }
                return r.json()
            }))["then"]((fun(m: dynamic): dynamic {
                if (truth(jsOr(jsOr(jsOr((m.id !== id)) { (onPresent(m.frames) { value987 -> value987.length } !== 48) }) { (!truth(m.specific30)) }) { jsAnd(root.Motion31) { (m.motionRevision31 !== 31) } })) {
                    throw global_Error("Modelo local incompleto")
                }
                cache["set"](id, m)
                loaded30.add(id)
                return absent
            }))["catch"]((fun(): dynamic {

                return absent
            }))["finally"]((fun(): dynamic {
                return loading30.delete(id)
            }))
            loading30["set"](id, work)
            return work
        }
        cp = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        R = global_Rig2D23
        mix = R.mix
        wave = R.wave
        cache = jsConstruct(global_Map, js("[]"))
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
        val oldDescribe: dynamic = global_Anatomy2D22.describe
        global_Anatomy2D22.describe = (fun(e: dynamic, f: dynamic, m: dynamic): dynamic {
            val a: dynamic = oldDescribe(e, f, m)
            if (true) {
                val iterator939: dynamic = iteratorOf(a.feet)
                while (true) {
                    val next940: dynamic = iterator939.next()
                    if (truth(next940.done)) break
                    var foot: dynamic = absent
                    foot = next940.value
                    var v: dynamic = onPresent(f.detail.feet30) { value941 -> value941[foot.index] }
                    if (truth((!truth(v)))) {
                        continue
                    }
                    if (truth(f.detail.calf30)) {
                        foot.toe = arrayOf<dynamic>(jsAdd(foot.origin[0], 28), (if (truth((foot.index === 8))) 704 else 691))
                        v = unit(sub(foot.toe, foot.origin))
                        foot.axis = v
                        foot.heel = add(foot.origin, v, (-numeric(7)))
                    } else {
                        v = unit(v)
                        foot.axis = v
                        foot.heel = add(foot.origin, v, (-numeric(7)))
                        foot.toe = add(foot.origin, v, 28)
                    }
                    val knee: dynamic = a.knees.find((fun(k: dynamic): dynamic {
                        return (k.index === (if (truth((foot.index === 8))) 7 else 9))
                    }))
                    if (truth((a.view !== "side"))) {
                        knee.axis = v
                    }
                }
            }
            return a
        })
        loaded30 = jsConstruct(global_Set, js("[]"))
        loading30 = jsConstruct(global_Map, js("[]"))
        root.Motion30 = jsObject { obj990 ->
            obj990["get"] = `get`
            obj990["resolve"] = resolve
            obj990["time"] = time
            obj990["cadence"] = cadence
            obj990["extras"] = extras
            obj990["inset"] = inset
            obj990["cache"] = cache
            obj990["preload"] = preload
            obj990["loaded"] = loaded30
            obj990["count"] = (fun(): dynamic {
                return global_Engine.exercises.length
            })
            obj990["clear"] = (fun(): dynamic {
                return cache.clear()
            })
            obj990["version"] = 30
        }
        return absent
    }))(global_window)
}
