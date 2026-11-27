@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installviewsv21

import hunter.browser.*

@JsName("JSON") private external var global_JSON: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installViewsV21() {
    dynamicValue((fun(root: dynamic): dynamic {
        var copy: dynamic = absent
        var profiles: dynamic = absent
        var names: dynamic = absent
        var family: dynamic = absent
        var choose: dynamic = absent
        var varyForward: dynamic = absent
        var install: dynamic = absent
        var `get`: dynamic = absent
        family = fun(id: dynamic): dynamic {
            val key: dynamic = id.replace(jsRegExp("-(?:control18|control19|pause19|15-19)\$", ""), "").replace(jsRegExp("(?:controlled|interval|hold|pause)\$", ""), "")
            return (if (truth((key === "abduct"))) "standingabduct" else key)
        }
        choose = fun(e: dynamic, m: dynamic): dynamic {
            val id: dynamic = family(e.id)
            val text: dynamic = e.name.toLowerCase()
            val d: dynamic = m.frames[0].detail
            var primary: dynamic = "side"
            var reason: dynamic = "Ver apoyos, alineación del tronco y flexión de articulaciones."
            var inset: dynamic = dynamicValue(null)
            var project: dynamic = dynamicValue(null)
            var mirror: dynamic = false
            if (truth(jsOr(jsOr(d.topShoulder19) { (id === "reverse") }) { (id === "contralateral") })) {
                primary = "top"
                reason = "Ver la apertura de ambos brazos y su relación con el tronco."
                inset = "prone"
                project = (if (truth((id === "reverse"))) "prone-t" else (if (truth((id === "contralateral"))) "prone-alternate" else null))
            } else {
                if (truth(jsOr(d.front19) { dynamicValue(jsonValue("[\"shouldertap\",\"plankreach19\"]")).includes(id) })) {
                    primary = "top"
                    reason = "Ver los apoyos y distinguir el brazo que se mueve."
                    project = (if (truth(d.front19)) null else "plank-top")
                    inset = (if (truth(d.front19)) "pushup" else null)
                } else {
                    if (truth(jsOr(dynamicValue(jsonValue("[\"wallslide\",\"scapular\"]")).includes(id)) { dynamicValue(jsRegExp("apertura posterior", "")).test(text) })) {
                        primary = "rear"
                        reason = "Ver omóplatos y apertura simétrica sin ocultar un brazo."
                        project = (if (truth(dynamicValue(jsRegExp("apertura posterior", "")).test(text))) "rear-fly" else null)
                        inset = (if (truth((id === "wallslide"))) "wall" else (if (truth(project)) "hinge" else null))
                    } else {
                        if (truth(dynamicValue(jsRegExp("plancha lateral|abducción lateral|apertura de cadera de lado|rotación torácica de lado", "")).test(text))) {
                            primary = "lying"
                            reason = "Ver el brazo y la pierna superiores, la pelvis y el apoyo inferior."
                        } else {
                            if (truth(jsOr(dynamicValue(jsRegExp("elevación lateral|press (?:de hombros|de hombro|alterno sentado|arnold)|press frontal y press lateral|elevación diagonal|sujeción (?:unilateral|bilateral)|separación de rodillas|pasos laterales|paso lateral|pasos cruzados|desplazamiento lateral|patinador|apertura.*(?:sin salto|piernas)|saltos con apertura|sentadilla.*(?:amplia)|zancada lateral|abducción de cadera|inclinación lateral|paso en v|retracción|curl.*lateral|círculos.*brazos|pulsos.*brazos|aperturas de brazos|sostén lateral", "")).test(text)) { dynamicValue(jsonValue("[\"lateralunge\",\"standingabduct\",\"chestopen\",\"hipcircle\",\"seatedpunch\",\"seatedmarch\",\"seatedreach\",\"breathing\",\"neck\",\"wrist\",\"forearmmob\",\"wristmob\",\"fingers\",\"elbowmob\",\"seatedturn\",\"diagonalraise\",\"doubletouch\"]")).includes(id) })) {
                                primary = "front"
                                reason = "Ver separación entre lados, simetría y desplazamiento lateral."
                            } else {
                                if (truth(jsAnd(dynamicValue(jsRegExp("(?:flexión de bíceps|curl de bíceps|curl martillo|curl alterno)", "")).test(text)) { (!truth(dynamicValue(jsonValue("[\"concentration\",\"pau-lateralcurl\",\"pau-curlcombo\"]")).includes(id))) })) {
                                    project = "curl-side"
                                    reason = "Ver el codo fijo y el recorrido del antebrazo."
                                }
                            }
                        }
                    }
                }
            }
            if (truth((primary === "side"))) {
                mirror = dynamicValue(jsRegExp("sentadilla|zancada|bisagra|peso muerto|remo|extensión de tríceps inclinada|pantorrilla en pared|flexores de cadera", "")).test(text)
            }
            if (truth(dynamicValue(jsonValue("[\"wallcalf\",\"calfstretch\",\"wall\",\"wallplank\"]")).includes(id))) {
                mirror = false
            }
            if (truth(dynamicValue(jsonValue("[\"sumo\",\"dbsumo\",\"squatstep\"]")).includes(id))) {
                primary = "front"
                project = "wide-squat"
                reason = "Ver anchura de la postura, rodillas y separación de los pies."
            }
            if (truth((id === "pau-standingfly"))) {
                primary = "front"
                reason = "Ver separación y acercamiento de ambos codos."
            }
            if (truth(dynamicValue(jsonValue("[\"pau-raisecombo\",\"pau-armpulses\",\"pau-armfly\",\"pau-armcurl\"]")).includes(id))) {
                inset = "arms"
            }
            if (truth(dynamicValue(jsonValue("[\"wrist\",\"wristmob\",\"forearmmob\",\"fingers\"]")).includes(id))) {
                inset = "hand"
                reason = "La ampliación muestra el pequeño recorrido de la mano y del antebrazo."
            }
            if (truth(varyForward(id))) {
                inset = "reach"
            }
            return jsObject { obj7 ->
                obj7["primary"] = primary
                obj7["mirror"] = mirror
                obj7["project"] = project
                obj7["inset"] = inset
                obj7["reason"] = reason
                obj7["label"] = names[primary]
                obj7["family"] = id
            }
        }
        varyForward = fun(id: dynamic): dynamic {
            return jsOr((id === "seatedreach")) { (id === "stepreach") }
        }
        install = fun(): dynamic {
            if (true) {
                val iterator12: dynamic = iteratorOf(global_Engine.exercises.filter((fun(e: dynamic): dynamic {
                    return (e.variant === "base")
                })))
                while (true) {
                    val next13: dynamic = iterator12.next()
                    if (truth(next13.done)) break
                    var e: dynamic = absent
                    e = next13.value
                    val m: dynamic = global_MotionDB[e.id]
                    val v: dynamic = choose(e, m)
                    profiles[e.id] = v
                    m.view21 = v
                    val frames: dynamic = m.frames
                    if (truth(dynamicValue(jsonValue("[\"hinge\",\"arms\",\"reach\"]")).includes(v.inset))) {
                        val pts: dynamic = frames.flatMap((fun(f: dynamic): dynamic {
                            return f.p
                        }))
                        val xs: dynamic = pts.map((fun(p: dynamic): dynamic {
                            return p[0]
                        }))
                        val ys: dynamic = pts.map((fun(p: dynamic): dynamic {
                            return p[1]
                        }))
                        v.insetBounds = jsObject { obj20 ->
                            obj20["minX"] = jsSubtract(run { val target22: dynamic = global_Math; invokeWith(target22.min, target22, jsArray { array21 ->
                                appendAll(array21, xs)
                            }) }, 35)
                            obj20["maxX"] = jsAdd(run { val target24: dynamic = global_Math; invokeWith(target24.max, target24, jsArray { array23 ->
                                appendAll(array23, xs)
                            }) }, 35)
                            obj20["minY"] = jsSubtract(run { val target26: dynamic = global_Math; invokeWith(target26.min, target26, jsArray { array25 ->
                                appendAll(array25, ys)
                            }) }, 35)
                            obj20["maxY"] = jsAdd(run { val target28: dynamic = global_Math; invokeWith(target28.max, target28, jsArray { array27 ->
                                appendAll(array27, ys)
                            }) }, 30)
                        }
                    }
                    val `value`: dynamic = (fun(f: dynamic, j: dynamic, k: dynamic): dynamic {
                        val all: dynamic = frames.map((fun(x: dynamic): dynamic {
                            return x.p[j][k]
                        }))
                        val min: dynamic = run { val target30: dynamic = global_Math; invokeWith(target30.min, target30, jsArray { array29 ->
                            appendAll(array29, all)
                        }) }
                        val max: dynamic = run { val target32: dynamic = global_Math; invokeWith(target32.max, target32, jsArray { array31 ->
                            appendAll(array31, all)
                        }) }
                        return (if (truth(jsMore(jsSubtract(max, min), 0.01))) jsDivide(jsSubtract(f.p[j][k], min), jsSubtract(max, min)) else 0)
                    })
                    m.frames = frames.map((fun(source: dynamic, i: dynamic): dynamic {
                        val f: dynamic = copy(source)
                        val p: dynamic = f.p
                        f.detail.view21 = v.primary
                        if (truth(dynamicValue(jsonValue("[\"hinge\",\"arms\",\"reach\"]")).includes(v.inset))) {
                            f.detail.sidePose21 = copy(source.p)
                            f.detail.sideEquipment21 = copy(source.equipment)
                        }
                        if (truth((v.family === "wallslide"))) {
                            f.equipment = jsonValue("[[\"wall\",[170,120,550,715]]]")
                        }
                        if (truth((v.project === "prone-t"))) {
                            val a: dynamic = jsSubtract(1, `value`(source, 3, 0))
                            f.p = jsonValue("[[360,225],[360,295],[360,480],[250,295],[145,295],[470,295],[575,295],[333,585],[330,700],[387,585],[390,700]]")
                            f.detail = jsObject { obj47 ->
                                copyFields(obj47, f.detail)
                                obj47["topShoulder19"] = true
                                obj47["letter19"] = "T"
                                obj47["lift19"] = a
                                obj47["stage18"] = (if (truth(jsMore(a, 0.5))) "ELEVA LOS BRAZOS · FORMA T" else "REGRESA CON CONTROL · FORMA T")
                            }
                        } else {
                            if (truth((v.project === "prone-alternate"))) {
                                val a: dynamic = `value`(source, 4, 1)
                                val lift: dynamic = jsSubtract(1, a)
                                f.p = jsonValue("[[360,225],[360,295],[360,480],[285,215],[220,125],[430,355],[490,395],[333,585],[315,700],[410,575],[470,680]]")
                                f.detail.topShoulder19 = true
                                f.detail.letter19 = "BRAZO Y PIERNA CONTRARIOS"
                                f.detail.lift19 = lift
                                f.detail.alternate21 = true
                            } else {
                                if (truth((v.project === "rear-fly"))) {
                                    val separation: dynamic = frames.map((fun(x: dynamic): dynamic {
                                        return global_Math.abs(jsSubtract(x.p[6][0], x.p[4][0]))
                                    }))
                                    val lo: dynamic = run { val target59: dynamic = global_Math; invokeWith(target59.min, target59, jsArray { array58 ->
                                        appendAll(array58, separation)
                                    }) }
                                    val hi: dynamic = run { val target61: dynamic = global_Math; invokeWith(target61.max, target61, jsArray { array60 ->
                                        appendAll(array60, separation)
                                    }) }
                                    val a: dynamic = (if (truth(jsMore(jsSubtract(hi, lo), 0.01))) jsDivide(jsSubtract(global_Math.abs(jsSubtract(p[6][0], p[4][0])), lo), jsSubtract(hi, lo)) else 0)
                                    f.p = arrayOf<dynamic>(jsonValue("[360,225]"), jsonValue("[360,295]"), jsonValue("[360,475]"), arrayOf<dynamic>(jsSubtract(310, jsMultiply(70, a)), jsSubtract(380, jsMultiply(70, a))), arrayOf<dynamic>(jsSubtract(315, jsMultiply(155, a)), jsSubtract(460, jsMultiply(140, a))), arrayOf<dynamic>(jsAdd(410, jsMultiply(70, a)), jsSubtract(380, jsMultiply(70, a))), arrayOf<dynamic>(jsAdd(405, jsMultiply(155, a)), jsSubtract(460, jsMultiply(140, a))), jsonValue("[330,585]"), jsonValue("[325,700]"), jsonValue("[390,585]"), jsonValue("[395,700]"))
                                    f.detail.lift21 = a
                                    if (truth(e.seated)) {
                                        f.p[7] = jsonValue("[305,535]")
                                        f.p[8] = jsonValue("[305,700]")
                                        f.p[9] = jsonValue("[415,535]")
                                        f.p[10] = jsonValue("[415,700]")
                                        f.equipment = jsonValue("[[\"bench\",[275,500,445,705]]]")
                                    } else {
                                        f.equipment = js("[]")
                                    }
                                } else {
                                    if (truth((v.project === "wide-squat"))) {
                                        val a: dynamic = `value`(source, 2, 1)
                                        val shift: dynamic = (if (truth((v.family === "squatstep"))) jsMultiply(55, a) else 0)
                                        val spread: dynamic = (if (truth((v.family === "squatstep"))) 60 else 115)
                                        f.p = arrayOf<dynamic>(arrayOf<dynamic>(360, jsAdd(270, jsMultiply(95, a))), arrayOf<dynamic>(360, jsAdd(320, jsMultiply(95, a))), arrayOf<dynamic>(360, jsAdd(480, jsMultiply(95, a))), arrayOf<dynamic>(315, jsAdd(397, jsMultiply(95, a))), arrayOf<dynamic>(348, jsAdd(400, jsMultiply(95, a))), arrayOf<dynamic>(405, jsAdd(397, jsMultiply(95, a))), arrayOf<dynamic>(372, jsAdd(400, jsMultiply(95, a))), arrayOf<dynamic>(jsSubtract(jsSubtract(360, spread), shift), jsAdd(585, jsMultiply(20, a))), arrayOf<dynamic>(jsSubtract(jsSubtract(360, spread), shift), 700), arrayOf<dynamic>(jsAdd(360, spread), jsAdd(585, jsMultiply(20, a))), arrayOf<dynamic>(jsAdd(360, spread), 700))
                                        if (truth((v.family === "dbsumo"))) {
                                            f.p[3] = arrayOf<dynamic>(323, jsAdd(405, jsMultiply(95, a)))
                                            f.p[4] = arrayOf<dynamic>(360, jsAdd(535, jsMultiply(95, a)))
                                            f.p[5] = arrayOf<dynamic>(397, jsAdd(405, jsMultiply(95, a)))
                                            f.p[6] = arrayOf<dynamic>(360, jsAdd(535, jsMultiply(95, a)))
                                            f.loads = arrayOf<dynamic>(copy(f.p[4]))
                                        }
                                    } else {
                                        if (truth((v.project === "curl-side"))) {
                                            val a: dynamic = jsSubtract(1, `value`(source, 4, 1))
                                            val c: dynamic = jsSubtract(1, `value`(source, 6, 1))
                                            val x: dynamic = p[1][0]
                                            p[3] = arrayOf<dynamic>(jsAdd(x, 8), 420)
                                            p[4] = arrayOf<dynamic>(jsAdd(jsAdd(x, 8), jsMultiply(58, a)), jsSubtract(514, jsMultiply(152, a)))
                                            p[5] = arrayOf<dynamic>(jsSubtract(x, 15), 417)
                                            p[6] = arrayOf<dynamic>(jsAdd(jsSubtract(x, 15), jsMultiply(58, c)), jsSubtract(511, jsMultiply(152, c)))
                                            if (truth((!truth(e.seated)))) {
                                                p[7] = arrayOf<dynamic>(jsAdd(x, 6), 585)
                                                p[8] = arrayOf<dynamic>(jsAdd(x, 12), 700)
                                                p[9] = arrayOf<dynamic>(jsSubtract(x, 12), 585)
                                                p[10] = arrayOf<dynamic>(jsSubtract(x, 12), 700)
                                            }
                                        } else {
                                            if (truth((v.project === "plank-top"))) {
                                                val a: dynamic = jsSubtract(1, `value`(source, 4, 1))
                                                val c: dynamic = jsSubtract(1, `value`(source, 6, 1))
                                                val reach: dynamic = (v.family === "plankreach19")
                                                f.p = arrayOf<dynamic>(jsonValue("[360,210]"), jsonValue("[360,280]"), jsonValue("[360,450]"), arrayOf<dynamic>(jsAdd(310, jsMultiply(35, a)), jsSubtract(355, jsMultiply((if (truth(reach)) 110 else 50), a))), arrayOf<dynamic>(jsAdd(305, jsMultiply((if (truth(reach)) 0 else 95), a)), jsSubtract(425, jsMultiply((if (truth(reach)) 275 else 130), a))), arrayOf<dynamic>(jsSubtract(410, jsMultiply(35, c)), jsSubtract(355, jsMultiply((if (truth(reach)) 110 else 50), c))), arrayOf<dynamic>(jsSubtract(415, jsMultiply((if (truth(reach)) 0 else 95), c)), jsSubtract(425, jsMultiply((if (truth(reach)) 275 else 130), c))), jsonValue("[330,565]"), jsonValue("[315,700]"), jsonValue("[390,565]"), jsonValue("[405,700]"))
                                                f.detail.front19 = true
                                                f.detail.stage18 = (if (truth(reach)) "ALCANZA UN BRAZO · APOYO ESTABLE" else "TOCA EL HOMBRO · REPITE AL OTRO LADO")
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        if (truth(jsAnd(jsAnd(source.loads.length) { (e.id !== "dbbridge") }) { (!truth(dynamicValue(jsRegExp("goblet|goblethold|sumodead|dbsumo", "")).test(v.family))) })) {
                            if (truth((source.loads.length === 2))) {
                                f.loads = arrayOf<dynamic>(copy(f.p[4]), copy(f.p[6]))
                            } else {
                                if (truth(dynamicValue(jsRegExp("singleoverhead", "")).test(v.family))) {
                                    f.loads = arrayOf<dynamic>(copy(f.p[4]))
                                } else {
                                    if (truth((!truth(dynamicValue(jsRegExp("doublefront|singlebridge|hipthrust", "")).test(v.family))))) {
                                        val q: dynamic = source.loads[0]
                                        val distance: dynamic = (fun(j: dynamic): dynamic {
                                            return global_Math.hypot(jsSubtract(q[0], source.p[j][0]), jsSubtract(q[1], source.p[j][1]))
                                        })
                                        f.loads = arrayOf<dynamic>(copy(f.p[(if (truth(jsAtMost(distance(4), distance(6)))) 4 else 6)]))
                                    }
                                }
                            }
                        }
                        return f
                    }))
                }
            }
            if (true) {
                val iterator118: dynamic = iteratorOf(global_Engine.exercises)
                while (true) {
                    val next119: dynamic = iterator118.next()
                    if (truth(next119.done)) break
                    var e: dynamic = absent
                    e = next119.value
                    val base: dynamic = jsOr(e.baseId) { e.id }
                    e.view21 = profiles[base]
                }
            }
            return absent
        }
        `get` = fun(id: dynamic): dynamic {
            val e: dynamic = global_Engine.byId(id)
            return profiles[jsOr(e.baseId) { id }]
        }
        copy = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        profiles = js("({})")
        names = jsonValue("{\"front\":\"Frontal\",\"rear\":\"Posterior\",\"top\":\"Superior\",\"side\":\"Lateral\",\"lying\":\"Frontal · de lado\"}")
        install()
        root.Viewpoints21 = jsObject { obj126 ->
            obj126["get"] = `get`
            obj126["profiles"] = profiles
            obj126["choose"] = choose
            obj126["names"] = names
        }
        return absent
    }))(global_window)
}
