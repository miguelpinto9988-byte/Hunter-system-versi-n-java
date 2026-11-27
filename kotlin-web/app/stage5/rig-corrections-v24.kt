@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installrigcorrectionsv24

import hunter.browser.*

@JsName("Rig2D23") private external var global_Rig2D23: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Anatomy2D22") private external var global_Anatomy2D22: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installRigCorrectionsV24() {
    dynamicValue((fun(root: dynamic): dynamic {
        var R: dynamic = absent
        var wave: dynamic = absent
        var bear: dynamic = absent
        var hipflex: dynamic = absent
        var triceps: dynamic = absent
        var bridge: dynamic = absent
        bear = fun(t: dynamic, arg5: dynamic): dynamic {
            var walking: dynamic = absent
            walking = definedOr(arg5) { false }
            val f: dynamic = R.standing()
            val p: dynamic = f.p
            val a: dynamic = (if (truth(walking)) global_Math.sin(jsMultiply(t, 6.283)) else 0)
            f.p = arrayOf<dynamic>(jsonValue("[190,395]"), jsonValue("[245,405]"), jsonValue("[435,405]"), arrayOf<dynamic>(jsSubtract(245, jsMultiply(10, a)), 509), arrayOf<dynamic>(jsSubtract(245, jsMultiply(22, a)), 613), arrayOf<dynamic>(jsAdd(232, jsMultiply(10, a)), 497), arrayOf<dynamic>(jsAdd(232, jsMultiply(22, a)), 601), arrayOf<dynamic>(jsAdd(435, jsMultiply(18, a)), 575), arrayOf<dynamic>(jsAdd(548, jsMultiply(18, a)), 609), arrayOf<dynamic>(jsSubtract(422, jsMultiply(18, a)), 563), arrayOf<dynamic>(jsSubtract(535, jsMultiply(18, a)), 597))
            f.detail = jsObject { obj10 ->
                obj10["floor23"] = 627
                obj10["feet24"] = jsonValue("{\"8\":[[-1,0],true],\"10\":[[-1,0],true]}")
                obj10["stage18"] = (if (truth(walking)) "AVANZA CON BRAZO Y PIERNA CONTRARIOS" else "RODILLAS SUSPENDIDAS · RESPIRA")
                obj10["family23"] = "bear"
            }
            return f
        }
        hipflex = fun(): dynamic {
            val f: dynamic = R.standing()
            f.p = jsonValue("[[350,245],[350,308],[350,465],[355,397],[410,460],[342,385],[397,448],[490,500],[490,650],[267,646],[147,646]]")
            f.detail = jsonValue("{\"floor23\":665,\"feet24\":{\"8\":[[1,0],true],\"10\":[[-1,0],false]},\"stage18\":\"RODILLA POSTERIOR APOYADA · PELVIS ESTABLE\",\"family23\":\"hipflex\"}")
            return f
        }
        triceps = fun(t: dynamic): dynamic {
            val f: dynamic = R.standing()
            val a: dynamic = wave(t)
            val angle: dynamic = jsMultiply(2.4, a)
            f.p[3] = jsonValue("[346,175]")
            f.p[4] = arrayOf<dynamic>(jsSubtract(346, jsMultiply(100, global_Math.sin(angle))), jsSubtract(175, jsMultiply(100, global_Math.cos(angle))))
            f.p[5] = jsonValue("[333,173]")
            f.p[6] = arrayOf<dynamic>(jsSubtract(333, jsMultiply(100, global_Math.sin(angle))), jsSubtract(173, jsMultiply(100, global_Math.cos(angle))))
            f.p[7] = jsonValue("[357,565]")
            f.p[8] = jsonValue("[357,704]")
            f.p[9] = jsonValue("[340,565]")
            f.p[10] = jsonValue("[340,704]")
            f.loads = arrayOf<dynamic>(R.mix(f.p[4], f.p[6], 0.5))
            f.detail.stage18 = (if (truth(jsMore(a, 0.7))) "FLEXIONA EL CODO · CARGA DETRÁS DE LA CABEZA" else "EXTIENDE · BRAZOS SUPERIORES ESTABLES")
            return f
        }
        bridge = fun(t: dynamic, arg41: dynamic, arg42: dynamic): dynamic {
            var walkout: dynamic = absent
            walkout = definedOr(arg41) { false }
            var single: dynamic = absent
            single = definedOr(arg42) { false }
            val f: dynamic = R.standing()
            val u: dynamic = wave(t)
            val a: dynamic = (if (truth(walkout)) jsDivide(global_Math.floor(jsMultiply(u, 3)), 3) else u)
            val p: dynamic = f.p
            p[0] = jsonValue("[150,621]")
            p[1] = jsonValue("[210,632]")
            p[2] = jsonValue("[385,525]")
            p[3] = jsonValue("[265,629]")
            p[4] = jsonValue("[335,638]")
            p[5] = jsonValue("[251,617]")
            p[6] = jsonValue("[322,626]")
            p[8] = arrayOf<dynamic>(jsAdd(470, jsMultiply(145, a)), 639)
            p[10] = arrayOf<dynamic>(jsAdd(458, jsMultiply(145, (if (truth(walkout)) global_Math.min(1, jsAdd(a, jsMultiply(0.15, global_Math.sin(jsMultiply(t, 6.283))))) else a))), 627)
            p[7] = R.ik(p[2], p[8], 135, 1)
            p[9] = R.ik(arrayOf<dynamic>(jsSubtract(p[2][0], 12), jsSubtract(p[2][1], 12)), p[10], 135, 1)
            if (truth(single)) {
                p[9] = jsonValue("[455,460]")
                p[10] = jsonValue("[568,488]")
            }
            f.detail = jsObject { obj71 ->
                obj71["floor23"] = 655
                obj71["feet24"] = jsObject { obj72 ->
                    obj72["8"] = jsonValue("[[1,0],true]")
                    obj72["10"] = arrayOf<dynamic>(jsonValue("[1,0]"), (!truth(single)))
                }
                obj71["stage18"] = (if (truth(walkout)) "PASOS CORTOS ALTERNOS · CONSERVA LA PELVIS" else "ALEJA Y ACERCA LOS TALONES CON CONTROL")
                obj71["family23"] = "ham-slide"
                obj71["param23"] = jsObject { obj73 ->
                    obj73["walkout"] = walkout
                    obj73["single"] = single
                }
            }
            return f
        }
        R = global_Rig2D23
        val cp: dynamic = R.cp
        wave = R.wave
        val reviews: dynamic = js("[]")
        if (true) {
            val iterator74: dynamic = iteratorOf(global_Engine.exercises.filter((fun(e: dynamic): dynamic {
                return (e.variant === "base")
            })))
            while (true) {
                val next75: dynamic = iterator74.next()
                if (truth(next75.done)) break
                var e: dynamic = absent
                e = next75.value
                var make: dynamic = absent
                var note: dynamic = absent
                val m: dynamic = global_MotionDB[e.id]
                val v: dynamic = m.view21
                val fam: dynamic = v.family
                if (truth(jsAnd(dynamicValue(jsonValue("[\"bear\",\"bearcrawl\",\"bearhold\"]")).includes(fam)) { (!truth(e.pack23)) })) {
                    make = (fun(t: dynamic): dynamic {
                        return bear(t, (e.id === "bearcrawl"))
                    })
                    m.posture23 = "prone"
                    v.inset = (if (truth((e.id === "bearhold"))) "hold23" else null)
                    note = "Cuello neutro, manos bajo hombros y rodillas por encima del suelo."
                }
                if (truth((fam === "hipflex"))) {
                    make = hipflex
                    m.posture23 = "upright"
                    m.facing23 = "right"
                    v.mirror = false
                    v.inset = "hold23"
                    note = "Perfil hacia el pie anterior, rodilla posterior apoyada y pie posterior extendido."
                }
                if (truth((e.id === "pau-overtriceps"))) {
                    make = triceps
                    m.posture23 = "upright"
                    m.facing23 = "right"
                    v.mirror = false
                    v.inset = dynamicValue(null)
                    note = "Perfil coherente: codos quietos, mancuerna detrás de la cabeza y pies sin cruces."
                }
                if (truth((fam === "ham-slide"))) {
                    make = (fun(t: dynamic): dynamic {
                        return bridge(t, (!truth((!truth(onPresent(e.param23) { value96 -> value96.walkout })))), (!truth((!truth(onPresent(e.param23) { value97 -> value97.single })))))
                    })
                    m.posture23 = "supine"
                    v.mirror = false
                    v.inset = dynamicValue(null)
                    note = "Rodillas por encima de los talones, pelvis elevada, apoyo real de talones."
                }
                if (truth((!truth(make)))) {
                    continue
                }
                v.primary = "side"
                v.label = "Lateral"
                v.project = dynamicValue(null)
                m.frames = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                    val f: dynamic = make(jsDivide(i, 48))
                    f.detail = jsObject { obj114 ->
                        copyFields(obj114, f.detail)
                        obj114["view21"] = "side"
                        obj114["reviewed24"] = true
                    }
                    return f
                }))
                reviews.push(jsObject { obj115 ->
                    obj115["id"] = e.id
                    obj115["note"] = note
                    obj115["models"] = 1
                    obj115["variants"] = global_Engine.exercises.filter((fun(x: dynamic): dynamic {
                        return (x.baseId === e.id)
                    })).length
                })
            }
        }
        if (true) {
            val iterator116: dynamic = iteratorOf(global_Engine.exercises.filter((fun(e: dynamic): dynamic {
                return (e.baseId === "home-bridge-walk23")
            })))
            while (true) {
                val next117: dynamic = iterator116.next()
                if (truth(next117.done)) break
                var e: dynamic = absent
                e = next117.value
                e.instructions = jsonValue("[\"Túmbate boca arriba, dobla las rodillas y apoya los talones en el suelo.\",\"Eleva la pelvis manteniendo hombros y cabeza apoyados.\",\"Aleja los talones con pasos cortos alternos sin dejar caer la pelvis.\",\"Vuelve con pasos cortos; termina la repetición al recuperar el apoyo inicial.\"]")
                e.steps = e.instructions.join(" ")
                e.setup = e.instructions[0]
            }
        }
        if (true) {
            val iterator124: dynamic = iteratorOf(global_Engine.exercises.filter((fun(e: dynamic): dynamic {
                return (e.baseId === "pau-overtriceps")
            })))
            while (true) {
                val next125: dynamic = iterator124.next()
                if (truth(next125.done)) break
                var e: dynamic = absent
                e = next125.value
                e.sourceOnly = false
                e.variantDescription = "Vista lateral propia: los codos permanecen junto a la cabeza mientras el antebrazo baja la carga por detrás."
            }
        }
        val oldDescribe: dynamic = global_Anatomy2D22.describe
        global_Anatomy2D22.describe = (fun(e: dynamic, f: dynamic, m: dynamic): dynamic {
            val result: dynamic = oldDescribe(e, f, m)
            if (true) {
                val iterator134: dynamic = iteratorOf(result.feet)
                while (true) {
                    val next135: dynamic = iterator134.next()
                    if (truth(next135.done)) break
                    var foot: dynamic = absent
                    foot = next135.value
                    val `override`: dynamic = onPresent(f.detail.feet24) { value136 -> value136[foot.index] }
                    if (truth(`override`)) {
                        foot.axis = `override`[0]
                        foot.grounded = `override`[1]
                        foot.heel = arrayOf<dynamic>(jsSubtract(foot.origin[0], jsMultiply(7, foot.axis[0])), jsSubtract(foot.origin[1], jsMultiply(7, foot.axis[1])))
                        foot.toe = arrayOf<dynamic>(jsAdd(foot.origin[0], jsMultiply(28, foot.axis[0])), jsAdd(foot.origin[1], jsMultiply(28, foot.axis[1])))
                    }
                }
            }
            return result
        })
        root.RigCorrections24 = jsObject { obj147 ->
            obj147["reviews"] = reviews
            obj147["bear"] = bear
            obj147["hipflex"] = hipflex
            obj147["triceps"] = triceps
            obj147["bridge"] = bridge
        }
        return absent
    }))(global_window)
}
