@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installmotionv37

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("Motion30") private external var global_Motion30: dynamic
@JsName("WeakMap") private external var global_WeakMap: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Rig2D23") private external var global_Rig2D23: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Motion2D18") private external var global_Motion2D18: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installMotionV37() {
    dynamicValue((fun(root: dynamic): dynamic {
        var ids: dynamic = absent
        var cache: dynamic = absent
        var copy: dynamic = absent
        var add: dynamic = absent
        var mix: dynamic = absent
        var ik: dynamic = absent
        var ease: dynamic = absent
        var track: dynamic = absent
        var head: dynamic = absent
        var rack: dynamic = absent
        var correct: dynamic = absent
        var loads: dynamic = absent
        var inset: dynamic = absent
        track = fun(t: dynamic, keys: dynamic): dynamic {
            val i: dynamic = keys.findIndex((fun(k: dynamic): dynamic {
                return jsAtLeast(k[0], t)
            }))
            if (truth(jsAtMost(i, 0))) {
                return keys[0][1]
            }
            val lo: dynamic = keys[jsSubtract(i, 1)]
            val hi: dynamic = keys[i]
            return mix(lo[1], hi[1], ease(jsDivide(jsSubtract(t, lo[0]), jsSubtract(hi[0], lo[0]))))
        }
        head = fun(p: dynamic): dynamic {
            val dx: dynamic = jsSubtract(p[1][0], p[2][0])
            val dy: dynamic = jsSubtract(p[1][1], p[2][1])
            val d: dynamic = global_Math.hypot(dx, dy)
            p[0] = arrayOf<dynamic>(jsAdd(p[1][0], jsDivide(jsMultiply(60, dx), d)), jsAdd(p[1][1], jsDivide(jsMultiply(60, dy), d)))
            return absent
        }
        rack = fun(f: dynamic, arg11: dynamic): dynamic {
            var u: dynamic = absent
            u = definedOr(arg11) { 0 }
            if (true) {
                val iterator12: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-13]]"))
                while (true) {
                    val next13: dynamic = iterator12.next()
                    if (truth(next13.done)) break
                    var el: dynamic = absent
                    var hand: dynamic = absent
                    var o: dynamic = absent
                    val unpacked14: dynamic = fromIterable(next13.value)
                    el = unpacked14[0]
                    hand = unpacked14[1]
                    o = unpacked14[2]
                    val s: dynamic = add(f.p[1], arrayOf<dynamic>(o, o))
                    f.p[hand] = add(s, arrayOf<dynamic>(jsSubtract(42, jsMultiply(22, u)), jsSubtract((-numeric(7)), jsMultiply(181, u))))
                    f.p[el] = ik(s, f.p[hand], 100, (-numeric(1)))
                }
            }
            f.loads = arrayOf<dynamic>(copy(f.p[4]), copy(f.p[6]))
            return absent
        }
        correct = fun(e: dynamic, source: dynamic): dynamic {
            if (truth(jsOr((!truth(ids.has(e.id)))) { (source.motionRevision37 === 37) })) {
                return source
            }
            if (truth(cache.has(source))) {
                return cache["get"](source)
            }
            val m: dynamic = copy(source)
            val id: dynamic = e.id
            if (truth((id === "plankreach19"))) {
                m.view21 = jsObject { obj25 ->
                    copyFields(obj25, m.view21)
                    obj25["primary"] = "side"
                    obj25["label"] = "Lateral"
                    obj25["project"] = dynamicValue(null)
                    obj25["inset"] = "reach37"
                    obj25["reason"] = "Mano bajo el hombro y un brazo libre cada vez."
                }
                m.posture23 = "prone"
                m.facing23 = "left"
            }
            if (truth((id === "gym-pullassist30"))) {
                m.view21.inset = "pull37"
            }
            if (truth((id === "gym-landmine-press23"))) {
                m.view21.inset = "landmine37"
            }
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
                    if (!truth(jsLess(i, m.frames.length))) break
                    val f: dynamic = m.frames[i]
                    val p: dynamic = f.p
                    val t: dynamic = jsDivide(i, 48)
                    val a: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(t, 2), global_Math.PI))), 2)
                    f.detail.correction37 = id
                    f.detail.cycle37 = t
                    if (truth((id === "dbbridge"))) {
                        p[0] = jsonValue("[140,684]")
                        p[1] = jsonValue("[200,684]")
                        p[2] = arrayOf<dynamic>(jsSubtract(365, jsMultiply(10, a)), jsSubtract(665, jsMultiply(59, a)))
                        p[8] = jsonValue("[515,700]")
                        p[10] = jsonValue("[502,687]")
                        if (true) {
                            val iterator50: dynamic = iteratorOf(jsonValue("[[3,4,7,8,0],[5,6,9,10,-13]]"))
                            while (true) {
                                val next51: dynamic = iterator50.next()
                                if (truth(next51.done)) break
                                var el: dynamic = absent
                                var ha: dynamic = absent
                                var knee: dynamic = absent
                                var ankle: dynamic = absent
                                var o: dynamic = absent
                                val unpacked52: dynamic = fromIterable(next51.value)
                                el = unpacked52[0]
                                ha = unpacked52[1]
                                knee = unpacked52[2]
                                ankle = unpacked52[3]
                                o = unpacked52[4]
                                val shoulder: dynamic = add(p[1], arrayOf<dynamic>(o, o))
                                p[ha] = add(p[2], arrayOf<dynamic>(jsAdd((-numeric(12)), o), jsAdd((-numeric(20)), o)))
                                p[el] = ik(shoulder, p[ha], 88, (-numeric(1)))
                                p[knee] = ik(add(p[2], arrayOf<dynamic>(o, o)), p[ankle], 135, 1)
                            }
                        }
                        f.loads = arrayOf<dynamic>(add(p[2], jsonValue("[-7,-18]")))
                        f.detail.floor23 = 715
                        f.detail.feet30 = jsonValue("{\"8\":[1,0],\"10\":[1,0]}")
                    } else {
                        if (truth((id === "plankreach19"))) {
                            p[1] = jsonValue("[210,490]")
                            p[2] = jsonValue("[380,562]")
                            head(p)
                            p[8] = jsonValue("[640,672]")
                            p[10] = jsonValue("[627,659]")
                            p[7] = mix(p[2], p[8], 0.5)
                            p[9] = mix(add(p[2], jsonValue("[-13,-13]")), p[10], 0.5)
                            val near: dynamic = (if (truth(jsLess(t, 0.5))) jsPower(global_Math.sin(jsMultiply(jsMultiply(t, 2), global_Math.PI)), 2) else 0)
                            val far: dynamic = (if (truth(jsAtLeast(t, 0.5))) jsPower(global_Math.sin(jsMultiply(jsMultiply(t, 2), global_Math.PI)), 2) else 0)
                            if (true) {
                                val iterator77: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(3, 4, near, 0), arrayOf<dynamic>(5, 6, far, (-numeric(13)))))
                                while (true) {
                                    val next78: dynamic = iterator77.next()
                                    if (truth(next78.done)) break
                                    var el: dynamic = absent
                                    var ha: dynamic = absent
                                    var u: dynamic = absent
                                    var o: dynamic = absent
                                    val unpacked79: dynamic = fromIterable(next78.value)
                                    el = unpacked79[0]
                                    ha = unpacked79[1]
                                    u = unpacked79[2]
                                    o = unpacked79[3]
                                    val s: dynamic = add(p[1], arrayOf<dynamic>(o, o))
                                    val angle: dynamic = jsAdd(jsDivide(global_Math.PI, 2), jsMultiply(2.05, u))
                                    p[el] = add(s, arrayOf<dynamic>(jsMultiply(95, global_Math.cos(angle)), jsMultiply(95, global_Math.sin(angle))))
                                    p[ha] = add(s, arrayOf<dynamic>(jsMultiply(190, global_Math.cos(angle)), jsMultiply(190, global_Math.sin(angle))))
                                }
                            }
                            f.loads = js("[]")
                            f.equipment = js("[]")
                            f.detail.front19 = false
                            f.detail.topShoulder19 = false
                            f.detail.support23 = false
                            f.detail.floor23 = 688
                            f.detail.feet30 = jsonValue("{\"8\":[-0.3,0.95],\"10\":[-0.3,0.95]}")
                            f.detail.reach37 = jsAdd(near, far)
                            f.detail.stage18 = (if (truth(jsMore(near, 0.15))) "ALCANZA · APOYO EN LA OTRA MANO" else (if (truth(jsMore(far, 0.15))) "ALTERNA · PELVIS ESTABLE" else "DOS MANOS BAJO LOS HOMBROS"))
                        } else {
                            if (truth((id === "home-thruster-db23"))) {
                                val depth: dynamic = track(t, jsonValue("[[0,[0]],[0.3,[1]],[0.55,[0]],[1,[0]]]"))[0]
                                p[2] = arrayOf<dynamic>(jsSubtract(350, jsMultiply(70, depth)), jsAdd(420, jsMultiply(130, depth)))
                                p[1] = add(p[2], arrayOf<dynamic>(jsMultiply(170, global_Math.sin(jsMultiply(0.55, depth))), jsMultiply((-numeric(170)), global_Math.cos(jsMultiply(0.55, depth)))))
                                head(p)
                                p[8] = jsonValue("[365,700]")
                                p[10] = jsonValue("[352,687]")
                                p[7] = ik(p[2], p[8], 140, 1)
                                p[9] = ik(add(p[2], jsonValue("[-13,-13]")), p[10], 140, 1)
                                val u: dynamic = track(t, jsonValue("[[0,[0]],[0.55,[0]],[0.78,[1]],[1,[0]]]"))[0]
                                rack(f, u)
                                f.detail.stage18 = (if (truth(jsLess(t, 0.5))) "SENTADILLA · CARGA EN LOS HOMBROS" else (if (truth(jsMore(u, 0.1))) "EXTIENDE Y EMPUJA SOBRE LA CABEZA" else "REGRESA LA CARGA A LOS HOMBROS"))
                            } else {
                                if (truth((id === "home-rack-lunge23"))) {
                                    rack(f)
                                    f.detail.stage18 = "ZANCADA · MANCUERNAS EN LOS HOMBROS"
                                } else {
                                    if (truth((id === "home-clean-db23"))) {
                                        val v: dynamic = track(t, jsonValue("[[0,[315,500,0.58,0]],[0.28,[350,420,0,0]],[0.48,[350,468,0,1]],[0.65,[350,420,0,1]],[0.8,[350,420,0,1]],[1,[315,500,0.58,0]]]"))
                                        p[2] = arrayOf<dynamic>(v[0], v[1])
                                        p[1] = add(p[2], arrayOf<dynamic>(jsMultiply(170, global_Math.sin(v[2])), jsMultiply((-numeric(170)), global_Math.cos(v[2]))))
                                        head(p)
                                        p[8] = jsonValue("[375,700]")
                                        p[10] = jsonValue("[362,687]")
                                        p[7] = ik(p[2], p[8], 140, 1)
                                        p[9] = ik(add(p[2], jsonValue("[-13,-13]")), p[10], 140, 1)
                                        if (true) {
                                            val iterator130: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-13]]"))
                                            while (true) {
                                                val next131: dynamic = iterator130.next()
                                                if (truth(next131.done)) break
                                                var el: dynamic = absent
                                                var ha: dynamic = absent
                                                var o: dynamic = absent
                                                val unpacked132: dynamic = fromIterable(next131.value)
                                                el = unpacked132[0]
                                                ha = unpacked132[1]
                                                o = unpacked132[2]
                                                val s: dynamic = add(p[1], arrayOf<dynamic>(o, o))
                                                p[ha] = add(s, mix(jsonValue("[8,192]"), jsonValue("[42,-7]"), v[3]))
                                                p[el] = ik(s, p[ha], 100, (-numeric(1)))
                                            }
                                        }
                                        f.loads = arrayOf<dynamic>(copy(p[4]), copy(p[6]))
                                        f.detail.floor23 = 715
                                        f.detail.feet30 = jsonValue("{\"8\":[1,0],\"10\":[1,0]}")
                                        f.detail.stage18 = (if (truth(jsMore(v[3], 0.9))) "RECIBE EN LOS HOMBROS · CODOS DELANTE" else (if (truth(jsLess(t, 0.4))) "EXTIENDE CADERAS · CARGA CERCA DEL CUERPO" else "DESCIENDE CON CONTROL"))
                                    } else {
                                        if (truth((id === "gym-landmine-press23"))) {
                                            p[1] = jsonValue("[350,250]")
                                            p[2] = jsonValue("[350,420]")
                                            head(p)
                                            p[8] = jsonValue("[375,700]")
                                            p[10] = jsonValue("[292,687]")
                                            p[7] = ik(p[2], p[8], 140, 1)
                                            p[9] = ik(add(p[2], jsonValue("[-13,-13]")), p[10], 140, 1)
                                            val angle: dynamic = jsAdd(1.06, jsMultiply(0.28, a))
                                            p[4] = arrayOf<dynamic>(jsSubtract(650, jsMultiply(530, global_Math.cos(angle))), jsSubtract(715, jsMultiply(530, global_Math.sin(angle))))
                                            p[3] = ik(p[1], p[4], 105, (-numeric(1)))
                                            p[5] = jsonValue("[325,392]")
                                            p[6] = jsonValue("[330,480]")
                                            f.loads = js("[]")
                                            f.equipment = jsonValue("[[\"bounds23\",[95,95,690,735]]]")
                                            f.detail.floor23 = 715
                                            f.detail.feet30 = jsonValue("{\"8\":[1,0],\"10\":[1,0]}")
                                            f.detail.stage18 = "EMPUJA EN ARCO · SUJETA EL EXTREMO"
                                        } else {
                                            if (truth((id === "gym-pullassist30"))) {
                                                val rise: dynamic = jsMultiply(160, a)
                                                p[1] = arrayOf<dynamic>(360, jsSubtract(290, rise))
                                                p[2] = arrayOf<dynamic>(360, jsSubtract(460, rise))
                                                head(p)
                                                p[4] = jsonValue("[250,105]")
                                                p[6] = jsonValue("[470,105]")
                                                p[3] = ik(arrayOf<dynamic>(318, p[1][1]), p[4], 100, 1)
                                                p[5] = ik(arrayOf<dynamic>(402, p[1][1]), p[6], 100, (-numeric(1)))
                                                p[7] = arrayOf<dynamic>(330, jsSubtract(595, rise))
                                                p[9] = arrayOf<dynamic>(390, jsSubtract(595, rise))
                                                p[8] = arrayOf<dynamic>(334, jsSubtract(637, rise))
                                                p[10] = arrayOf<dynamic>(394, jsSubtract(637, rise))
                                                f.loads = js("[]")
                                                f.detail.padY30 = jsSubtract(608, rise)
                                                f.detail.feet30 = jsonValue("{\"8\":[0,1],\"10\":[0,1]}")
                                                f.detail.stage18 = "MANOS FIJAS · RODILLAS EN LA PLATAFORMA"
                                            } else {
                                                if (truth((id === "home-hopforward30"))) {
                                                    val v: dynamic = track(t, jsonValue("[[0,[0,500,0,0.2,1.8]],[0.18,[0,548,0,0.3,2.4]],[0.34,[30,420,0,0.05,-0.4]],[0.5,[75,360,85,0.08,-0.2]],[0.64,[110,545,0,0.3,0.6]],[0.8,[110,420,0,0,1.65]],[1,[0,500,0,0.2,1.8]]]"))
                                                    p[2] = arrayOf<dynamic>(jsAdd(320, v[0]), v[1])
                                                    p[1] = add(p[2], arrayOf<dynamic>(jsMultiply(170, global_Math.sin(v[3])), jsMultiply((-numeric(170)), global_Math.cos(v[3]))))
                                                    head(p)
                                                    val walk: dynamic = (if (truth(jsMore(t, 0.8))) global_Math.sin(jsMultiply(jsMultiply(jsDivide(jsSubtract(t, 0.8), 0.2), 2), global_Math.PI)) else 0)
                                                    p[8] = arrayOf<dynamic>(jsAdd(jsAdd(365, v[0]), jsMultiply(10, walk)), jsSubtract(jsSubtract(700, v[2]), jsMultiply(12, global_Math.max(0, walk))))
                                                    p[10] = arrayOf<dynamic>(jsSubtract(jsAdd(352, v[0]), jsMultiply(10, walk)), jsSubtract(jsSubtract(687, v[2]), jsMultiply(12, global_Math.max(0, (-numeric(walk))))))
                                                    p[7] = ik(p[2], p[8], 140, 1)
                                                    p[9] = ik(add(p[2], jsonValue("[-13,-13]")), p[10], 140, 1)
                                                    if (true) {
                                                        val iterator215: dynamic = iteratorOf(jsonValue("[[3,4,0],[5,6,-13]]"))
                                                        while (true) {
                                                            val next216: dynamic = iterator215.next()
                                                            if (truth(next216.done)) break
                                                            var el: dynamic = absent
                                                            var ha: dynamic = absent
                                                            var o: dynamic = absent
                                                            val unpacked217: dynamic = fromIterable(next216.value)
                                                            el = unpacked217[0]
                                                            ha = unpacked217[1]
                                                            o = unpacked217[2]
                                                            val s: dynamic = add(p[1], arrayOf<dynamic>(o, o))
                                                            val angle: dynamic = v[4]
                                                            p[el] = add(s, arrayOf<dynamic>(jsMultiply(95, global_Math.cos(angle)), jsMultiply(95, global_Math.sin(angle))))
                                                            p[ha] = add(s, arrayOf<dynamic>(jsMultiply(185, global_Math.cos(angle)), jsMultiply(185, global_Math.sin(angle))))
                                                        }
                                                    }
                                                    f.loads = js("[]")
                                                    f.detail.floor23 = 715
                                                    f.detail.feet30 = jsonValue("{\"8\":[1,0],\"10\":[1,0]}")
                                                    f.detail.stage18 = (if (truth(jsLess(t, 0.25))) "PREPARA · CADERAS Y RODILLAS" else (if (truth(jsLess(t, 0.4))) "IMPULSO HACIA DELANTE" else (if (truth(jsLess(t, 0.6))) "VUELO · BRAZOS AL FRENTE" else (if (truth(jsLess(t, 0.8))) "ATERRIZA SUAVE · FLEXIONA" else "REGRESO CAMINANDO"))))
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
            m.motionRevision37 = 37
            cache["set"](source, m)
            return m
        }
        ids = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"dbbridge\",\"plankreach19\",\"home-thruster-db23\",\"home-clean-db23\",\"home-rack-lunge23\",\"gym-landmine-press23\",\"gym-pullassist30\",\"home-hopforward30\"]")))
        val before: dynamic = global_Motion30["get"]
        cache = jsConstruct(global_WeakMap, js("[]"))
        copy = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        add = (fun(a: dynamic, b: dynamic): dynamic {
            return a.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsAdd(v, b[i])
            }))
        })
        mix = (fun(a: dynamic, b: dynamic, t: dynamic): dynamic {
            return a.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsAdd(v, jsMultiply(jsSubtract(b[i], v), t))
            }))
        })
        ik = global_Rig2D23.ik
        ease = (fun(x: dynamic): dynamic {
            return jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(global_Math.PI, global_Math.max(0, global_Math.min(1, x))))), 2)
        })
        global_Motion30["get"] = (fun(id: dynamic): dynamic {
            val x: dynamic = before(id)
            return jsObject { obj236 ->
                copyFields(obj236, x)
                obj236["model"] = correct(x.e, x.model)
            }
        })
        val extras: dynamic = global_Motion30.extras
        loads = global_Rig2D23.loads
        inset = global_Motion30.inset
        val line: dynamic = (fun(a: dynamic, b: dynamic, arg237: dynamic): dynamic {
            var w: dynamic = absent
            w = definedOr(arg237) { 6 }
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M", a.join(",")), "L"), b.join(",")), "\" fill=\"none\" stroke=\"#7a9aa7\" stroke-width=\""), w), "\" stroke-linecap=\"round\"/>")
        })
        global_Motion30.extras = (fun(f: dynamic, accent: dynamic): dynamic {
            if (truth((f.detail.correction37 === "gym-landmine-press23"))) {
                val q: dynamic = f.p[4]
                val plate: dynamic = mix(q, jsonValue("[650,715]"), 0.06)
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-apparatus37=\"landmine\">", line(jsonValue("[650,715]"), q, 10)), "<circle cx=\""), plate[0]), "\" cy=\""), plate[1]), "\" r=\"24\" fill=\"#243d50\" stroke=\""), accent), "\" stroke-width=\"3\"/><circle cx=\"650\" cy=\"715\" r=\"13\" fill=\"#243d50\" stroke=\"#7a9aa7\" stroke-width=\"4\"/></g>")
            }
            if (truth((f.detail.correction37 === "gym-pullassist30"))) {
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-apparatus37=\"assisted-pullup\">", line(jsonValue("[130,725]"), jsonValue("[600,725]"), 8)), line(jsonValue("[180,725]"), jsonValue("[180,100]"), 7)), line(jsonValue("[540,725]"), jsonValue("[540,100]"), 7)), line(jsonValue("[180,105]"), jsonValue("[540,105]"), 10)), line(arrayOf<dynamic>(298, f.detail.padY30), arrayOf<dynamic>(422, f.detail.padY30), 18)), line(arrayOf<dynamic>(500, f.detail.padY30), jsonValue("[500,720]"), 5)), "</g>")
            }
            return extras(f, accent)
        })
        global_Rig2D23.loads = (fun(f: dynamic, arg246: dynamic): dynamic {
            val callArgs245: dynamic = js("arguments")
            var args: dynamic = absent
            args = argsAfter(callArgs245, 1)
            if (truth((f.detail.correction37 === "gym-landmine-press23"))) {
                return ""
            }
            return invokeWith(loads, absent, jsArray { array247 ->
                array247.push(f)
                appendAll(array247, args)
            })
        })
        global_Motion30.inset = (fun(kind: dynamic, f: dynamic, phase: dynamic, accent: dynamic): dynamic {
            var title: dynamic = absent
            var note: dynamic = absent
            var art: dynamic = absent
            val id: dynamic = f.detail.correction37
            if (truth(jsOr((!truth(ids.has(id)))) { (!truth(dynamicValue(jsonValue("[\"reach37\",\"pull37\",\"landmine37\"]")).includes(kind))) })) {
                return inset(kind, f, phase, accent)
            }
            if (truth((kind === "reach37"))) {
                title = "VISTA SUPERIOR · ALTERNA UN BRAZO"
                note = "Una mano sostiene el cuerpo; alcanza con la otra sin girar la pelvis."
                val t: dynamic = f.detail.cycle37
                val near: dynamic = (if (truth(jsLess(t, 0.5))) jsPower(global_Math.sin(jsMultiply(jsMultiply(t, 2), global_Math.PI)), 2) else 0)
                val far: dynamic = (if (truth(jsAtLeast(t, 0.5))) jsPower(global_Math.sin(jsMultiply(jsMultiply(t, 2), global_Math.PI)), 2) else 0)
                art = jsAdd(jsAdd("<path d=\"M155 664V699M145 701L137 717M165 701L173 717\" stroke=\"#dceaf0\" stroke-width=\"9\" fill=\"none\"/><circle cx=\"155\" cy=\"651\" r=\"9\" fill=\"#243d50\"/>", line(jsonValue("[140,667]"), arrayOf<dynamic>(jsSubtract(140, jsMultiply(9, near)), jsSubtract(667, jsMultiply(30, near))), 7)), line(jsonValue("[170,667]"), arrayOf<dynamic>(jsAdd(170, jsMultiply(9, far)), jsSubtract(667, jsMultiply(30, far))), 7))
            } else {
                if (truth((kind === "pull37"))) {
                    title = "DE LADO · RODILLAS SOBRE EL APOYO"
                    note = "El apoyo acompaña el ascenso. Los pies quedan libres detrás de las rodillas."
                    val rise: dynamic = jsDivide(jsMultiply(12, jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(phase, 48), 2), global_Math.PI)))), 2)
                    art = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<path d=\"M95 643H220M117 648V718\" stroke=\"#7a9aa7\" stroke-width=\"4\" fill=\"none\"/><circle cx=\"157\" cy=\"", jsSubtract(660, rise)), "\" r=\"8\" fill=\"#edc5ad\"/><path d=\"M157 "), jsSubtract(671, rise)), "V"), jsSubtract(690, rise)), "L180 "), jsSubtract(701, rise)), "H198M180 "), jsSubtract(701, rise)), "L166 "), jsSubtract(715, rise)), "\" stroke=\"#dceaf0\" stroke-width=\"8\" fill=\"none\"/><path d=\"M171 "), jsSubtract(708, rise)), "H202\" stroke=\""), accent), "\" stroke-width=\"6\"/>")
                } else {
                    title = "BARRA ANCLADA · AGARRE CONTINUO"
                    note = "La mano sigue el arco de la barra. El anclaje permanece fijo en el suelo."
                    val a: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(phase, 48), 2), global_Math.PI))), 2)
                    val q: dynamic = arrayOf<dynamic>(jsAdd(154, jsMultiply(22, a)), jsSubtract(679, jsMultiply(14, a)))
                    art = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(line(jsonValue("[215,716]"), q, 5), "<circle cx=\"215\" cy=\"716\" r=\"6\" fill=\""), accent), "\"/><circle cx=\""), q[0]), "\" cy=\""), q[1]), "\" r=\"7\" fill=\"#edc5ad\"/>")
                }
            }
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<g data-detail37=\"", kind), "\"><rect x=\"58\" y=\"620\" width=\"604\" height=\"113\" rx=\"14\" fill=\"#102431\" stroke=\"#47606e\"/><text x=\"74\" y=\"642\" font-size=\"13\" fill=\""), accent), "\" font-family=\"sans-serif\">"), title), "</text>"), art), "<text x=\"74\" y=\"720\" font-size=\"12\" fill=\"#b0c6d1\" font-family=\"sans-serif\">"), note), "</text></g>")
        })
        root.Motion37 = jsObject { obj254 ->
            obj254["ids"] = ids
            obj254["correct"] = correct
            obj254["version"] = 37
        }
        global_Motion2D18.clearBounds()
        return absent
    }))(global_window)
}
