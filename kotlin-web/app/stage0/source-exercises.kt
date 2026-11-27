@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installsourceexercises

import hunter.browser.*

@JsName("JSON") private external var global_JSON: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installSourceExercises() {
    dynamicValue((fun(root: dynamic): dynamic {
        var clone: dynamic = absent
        var mix: dynamic = absent
        var cycle: dynamic = absent
        var standing: dynamic = absent
        var arm: dynamic = absent
        var press: dynamic = absent
        var curl: dynamic = absent
        var plank: dynamic = absent
        var frame: dynamic = absent
        standing = fun(): dynamic {
            return jsonValue("{\"p\":[[360,190],[360,250],[360,420],[285,335],[280,440],[435,335],[440,440],[320,550],[320,680],[400,550],[400,680]],\"equipment\":[],\"loads\":[],\"detail\":{}}")
        }
        arm = fun(f: dynamic, side: dynamic, elbow: dynamic, hand: dynamic): dynamic {
            f.p[(if (truth(side)) 5 else 3)] = elbow
            f.p[(if (truth(side)) 6 else 4)] = hand
            return absent
        }
        press = fun(f: dynamic, q: dynamic, arg13: dynamic, arg14: dynamic): dynamic {
            var front: dynamic = absent
            front = definedOr(arg13) { false }
            var side: dynamic = absent
            side = definedOr(arg14) { 0 }
            val sign: dynamic = (if (truth(side)) 1 else (-numeric(1)))
            val base: dynamic = (if (truth(front)) 35 else 85)
            arm(f, side, mix(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, base)), 275), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 50)), 160), q), mix(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, base)), 205), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 55)), 70), q))
            return absent
        }
        curl = fun(f: dynamic, q: dynamic, arg17: dynamic): dynamic {
            var lateral: dynamic = absent
            lateral = definedOr(arg17) { false }
            if (true) {
                var side: dynamic = absent
                side = 0
                var firstIteration18 = true
                while (true) {
                    if (!firstIteration18) {
                        run {
                            val previous19: dynamic = numeric(side)
                            side = jsAdd(previous19, 1)
                        }
                    }
                    firstIteration18 = false
                    if (!truth(jsLess(side, 2))) break
                    val sign: dynamic = (if (truth(side)) 1 else (-numeric(1)))
                    arm(f, side, arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, (if (truth(lateral)) 85 else 65))), 340), mix(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, (if (truth(lateral)) 110 else 65))), 440), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, (if (truth(lateral)) 95 else 65))), 265), q))
                }
            }
            return absent
        }
        plank = fun(left: dynamic, right: dynamic): dynamic {
            val q: dynamic = jsDivide(jsAdd(left, right), 2)
            val f: dynamic = standing()
            f.p[0] = mix(jsonValue("[220,350]"), jsonValue("[230,445]"), q)
            f.p[1] = mix(jsonValue("[270,380]"), jsonValue("[280,470]"), q)
            f.p[2] = mix(jsonValue("[460,488]"), jsonValue("[470,530]"), q)
            f.p[7] = mix(jsonValue("[550,540]"), jsonValue("[550,555]"), q)
            f.p[8] = jsonValue("[625,580]")
            f.p[9] = mix(jsonValue("[545,535]"), jsonValue("[545,550]"), q)
            f.p[10] = jsonValue("[615,570]")
            arm(f, 0, mix(jsonValue("[270,480]"), jsonValue("[285,580]"), left), mix(jsonValue("[270,580]"), jsonValue("[200,580]"), left))
            arm(f, 1, mix(jsonValue("[285,475]"), jsonValue("[305,570]"), right), mix(jsonValue("[285,570]"), jsonValue("[220,570]"), right))
            return f
        }
        frame = fun(kind: dynamic, i: dynamic): dynamic {
            val t: dynamic = jsDivide(i, 48)
            val q: dynamic = cycle(t)
            val half: dynamic = jsLess(i, 24)
            val sub: dynamic = jsDivide(jsMod(i, 24), 24)
            val s: dynamic = cycle(sub)
            var f: dynamic = standing()
            if (truth((kind === "alternate-press"))) {
                press(f, (if (truth(half)) s else 0), false, 0)
                press(f, (if (truth(half)) 0 else s), false, 1)
                f.detail.grip = "Alterna un brazo y luego el otro"
            }
            if (truth((kind === "overhead-triceps"))) {
                if (true) {
                    var side: dynamic = absent
                    side = 0
                    var firstIteration40 = true
                    while (true) {
                        if (!firstIteration40) {
                            run {
                                val previous41: dynamic = numeric(side)
                                side = jsAdd(previous41, 1)
                            }
                        }
                        firstIteration40 = false
                        if (!truth(jsLess(side, 2))) break
                        val sign: dynamic = (if (truth(side)) 1 else (-numeric(1)))
                        arm(f, side, mix(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 40)), 125), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 22)), 135), q), mix(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 5)), 200), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 5)), 55), q))
                    }
                }
                f.loads = arrayOf<dynamic>(arrayOf<dynamic>(360, jsDivide(jsAdd(f.p[4][1], f.p[6][1]), 2)))
                f.detail.grip = "Dos manos · una mancuerna"
            }
            if (truth((kind === "arnold"))) {
                if (true) {
                    var side: dynamic = absent
                    side = 0
                    var firstIteration46 = true
                    while (true) {
                        if (!firstIteration46) {
                            run {
                                val previous47: dynamic = numeric(side)
                                side = jsAdd(previous47, 1)
                            }
                        }
                        firstIteration46 = false
                        if (!truth(jsLess(side, 2))) break
                        val sign: dynamic = (if (truth(side)) 1 else (-numeric(1)))
                        val start: dynamic = arrayOf<dynamic>(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 35)), 300), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 40)), 220))
                        val middle: dynamic = arrayOf<dynamic>(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 85)), 260), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 85)), 190))
                        val end: dynamic = arrayOf<dynamic>(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 50)), 160), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 55)), 70))
                        val a: dynamic = (if (truth(jsLess(q, 0.45))) start else middle)
                        val b: dynamic = (if (truth(jsLess(q, 0.45))) middle else end)
                        val k: dynamic = (if (truth(jsLess(q, 0.45))) jsDivide(q, 0.45) else jsDivide(jsSubtract(q, 0.45), 0.55))
                        arm(f, side, mix(a[0], b[0], k), mix(a[1], b[1], k))
                    }
                }
                f.detail.grip = (if (truth(jsLess(q, 0.35))) "Palmas hacia ti" else "Palmas al frente")
            }
            if (truth(jsOr((kind === "lateral-curl")) { (kind === "curl-combo") })) {
                curl(f, (if (truth((kind === "curl-combo"))) s else q), jsOr((kind === "lateral-curl")) { half })
                f.detail.grip = (if (truth(jsAnd((kind === "curl-combo")) { (!truth(half)) })) "Agarre martillo" else "Curl con brazos abiertos")
            }
            if (truth((kind === "raise-combo"))) {
                if (true) {
                    var side: dynamic = absent
                    side = 0
                    var firstIteration52 = true
                    while (true) {
                        if (!firstIteration52) {
                            run {
                                val previous53: dynamic = numeric(side)
                                side = jsAdd(previous53, 1)
                            }
                        }
                        firstIteration52 = false
                        if (!truth(jsLess(side, 2))) break
                        val sign: dynamic = (if (truth(side)) 1 else (-numeric(1)))
                        if (truth(half)) {
                            arm(f, side, mix(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 65)), 340), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 125)), 265), s), mix(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 80)), 440), arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 205)), 275), s))
                        } else {
                            arm(f, side, mix(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 65)), 340), arrayOf<dynamic>(jsAdd(420, jsMultiply(sign, 55)), 265), s), mix(arrayOf<dynamic>(jsAdd(360, jsMultiply(sign, 80)), 440), arrayOf<dynamic>(jsAdd(500, jsMultiply(sign, 55)), 245), s))
                        }
                    }
                }
                f.detail.grip = (if (truth(half)) "Elevación lateral" else "Elevación frontal · vista oblicua")
            }
            if (truth((kind === "standing-fly"))) {
                if (true) {
                    var side: dynamic = absent
                    side = 0
                    var firstIteration56 = true
                    while (true) {
                        if (!firstIteration56) {
                            run {
                                val previous57: dynamic = numeric(side)
                                side = jsAdd(previous57, 1)
                            }
                        }
                        firstIteration56 = false
                        if (!truth(jsLess(side, 2))) break
                        val sign: dynamic = (if (truth(side)) 1 else (-numeric(1)))
                        val x: dynamic = jsAdd(360, jsMultiply(sign, jsSubtract(120, jsMultiply(100, q))))
                        arm(f, side, arrayOf<dynamic>(x, 260), arrayOf<dynamic>(x, 155))
                    }
                }
                f.detail.grip = "Acerca y separa los codos"
            }
            if (truth((kind === "press-combo"))) {
                press(f, s, half, 0)
                press(f, s, half, 1)
                f.detail.grip = (if (truth(half)) "Press con codos al frente" else "Press con codos a los lados")
            }
            if (truth((kind === "kickback"))) {
                f.p[0] = jsonValue("[435,270]")
                f.p[1] = jsonValue("[410,320]")
                f.p[2] = jsonValue("[320,450]")
                f.p[7] = jsonValue("[300,570]")
                f.p[8] = jsonValue("[295,700]")
                f.p[9] = jsonValue("[360,570]")
                f.p[10] = jsonValue("[365,700]")
                arm(f, 0, jsonValue("[355,345]"), arrayOf<dynamic>(jsSubtract(360, jsMultiply(85, q)), jsSubtract(425, jsMultiply(43, q))))
                arm(f, 1, jsonValue("[340,353]"), arrayOf<dynamic>(jsSubtract(345, jsMultiply(85, q)), jsSubtract(433, jsMultiply(43, q))))
                f.detail.grip = "Extiende ambos codos hacia atrás"
            }
            if (truth((kind === "plank-up-down"))) {
                val a: dynamic = global_Math.min(1, jsMultiply(s, 2))
                val b: dynamic = global_Math.max(0, jsSubtract(jsMultiply(s, 2), 1))
                f = plank((if (truth(half)) a else b), (if (truth(half)) b else a))
                f.detail.grip = (if (truth(half)) "Comienza con el brazo izquierdo" else "Comienza con el brazo derecho")
            }
            if (truth((kind === "knee-push-child"))) {
                val a: dynamic = global_MotionDB.kneepush
                val b: dynamic = global_MotionDB.childpose
                if (truth(jsLess(i, 20))) {
                    f = clone(a.frames[jsMod(global_Math.floor(jsDivide(jsMultiply(i, 48), 20)), 48)])
                } else {
                    if (truth(jsLess(i, 24))) {
                        val p: dynamic = a.frames[0]
                        val z: dynamic = b.frames[0]
                        f = clone(p)
                        f.p = p.p.map((fun(x: dynamic, j: dynamic): dynamic {
                            return mix(x, z.p[j], jsDivide(jsSubtract(i, 20), 4))
                        }))
                    } else {
                        if (truth(jsLess(i, 44))) {
                            f = clone(b.frames[jsMod(global_Math.floor(jsDivide(jsMultiply(jsSubtract(i, 24), 48), 20)), 48)])
                        } else {
                            val p: dynamic = b.frames[0]
                            val z: dynamic = a.frames[0]
                            f = clone(p)
                            f.p = p.p.map((fun(x: dynamic, j: dynamic): dynamic {
                                return mix(x, z.p[j], jsDivide(jsSubtract(i, 44), 4))
                            }))
                        }
                    }
                }
                f.detail.grip = (if (truth(jsLess(i, 24))) "Flexión con apoyo en rodillas" else "Postura del niño")
            }
            if (truth((!truth(dynamicValue(jsonValue("[\"plank-up-down\",\"knee-push-child\",\"overhead-triceps\"]")).includes(kind))))) {
                f.loads = arrayOf<dynamic>(f.p[4].slice(), f.p[6].slice())
            }
            return f
        }
        val E: dynamic = root.Engine
        clone = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        mix = (fun(a: dynamic, b: dynamic, q: dynamic): dynamic {
            return a.map((fun(v: dynamic, i: dynamic): dynamic {
                return jsAdd(v, jsMultiply(jsSubtract(b[i], v), q))
            }))
        })
        cycle = (fun(t: dynamic): dynamic {
            return jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(t, global_Math.PI), 2))), 2)
        })
        val rows: dynamic = jsonValue("[[\"pau-altpress\",\"overheadpress\",\"Press de hombros alterno\",\"alternate-press\",\"Eleva una mancuerna mientras mantienes la otra cerca del hombro. Regresa y alterna el brazo.\"],[\"pau-overtriceps\",\"lyingtricep\",\"Extensión de tríceps sobre la cabeza\",\"overhead-triceps\",\"Sujeta una mancuerna con ambas manos por encima de la cabeza. Dobla los codos para descenderla detrás de la cabeza y vuelve a extenderlos.\"],[\"pau-arnold\",\"overheadpress\",\"Press Arnold\",\"arnold\",\"Empieza con las palmas hacia ti y los codos flexionados. Gira las manos al elevar las cargas; baja invirtiendo el movimiento.\"],[\"pau-lateralcurl\",\"curl\",\"Curl de bíceps lateral\",\"lateral-curl\",\"Orienta las palmas hacia afuera. Flexiona los codos con los brazos ligeramente abiertos y baja con control.\"],[\"pau-curlcombo\",\"curl\",\"Curl lateral y curl martillo\",\"curl-combo\",\"Realiza un curl lateral completo. Cambia al agarre martillo y realiza otro curl; continúa alternando.\"],[\"pau-raisecombo\",\"lateral\",\"Elevación lateral y frontal\",\"raise-combo\",\"Eleva las cargas a los lados y regresa. Después elévalas al frente y regresa; alterna ambas acciones.\"],[\"pau-standingfly\",\"chestopen\",\"Apertura y cierre de pecho de pie con mancuernas\",\"standing-fly\",\"Mantén los codos a la altura de los hombros y los antebrazos hacia arriba. Acerca los codos al frente y vuelve a abrirlos.\"],[\"pau-presscombo\",\"overheadpress\",\"Press frontal y press lateral\",\"press-combo\",\"Haz un press con los codos al frente y vuelve al inicio. Abre los codos a los lados, haz otro press y vuelve a empezar.\"],[\"pau-kickback\",\"tricep\",\"Patada de tríceps con ambas mancuernas\",\"kickback\",\"Inclina el torso con las rodillas ligeramente flexionadas. Mantén los brazos junto al torso y extiende ambos codos hacia atrás; regresa.\"],[\"pau-plankup\",\"plank\",\"Plancha alta a plancha baja\",\"plank-up-down\",\"Desde plancha sobre las manos, apoya un antebrazo y después el otro. Vuelve apoyando una mano y luego la otra; alterna el brazo que inicia.\"],[\"pau-pushchild\",\"kneepush\",\"Flexión con rodillas y postura del niño\",\"knee-push-child\",\"Haz una flexión con las rodillas apoyadas. Lleva después la cadera hacia atrás con los brazos extendidos para pasar a postura del niño y vuelve al inicio.\"]]")
        if (true) {
            val iterator88: dynamic = iteratorOf(rows)
            while (true) {
                val next89: dynamic = iterator88.next()
                if (truth(next89.done)) break
                var id: dynamic = absent
                var base: dynamic = absent
                var name: dynamic = absent
                var kind: dynamic = absent
                var steps: dynamic = absent
                val unpacked90: dynamic = fromIterable(next89.value)
                id = unpacked90[0]
                base = unpacked90[1]
                name = unpacked90[2]
                kind = unpacked90[3]
                steps = unpacked90[4]
                val e: dynamic = jsObject { obj91 ->
                    copyFields(obj91, clone(E.byId(base)))
                    obj91["id"] = id
                    obj91["baseId"] = id
                    obj91["name"] = name
                    obj91["variant"] = "base"
                    obj91["sourceOnly"] = true
                    obj91["family"] = base
                    obj91["steps"] = steps
                    obj91["instructions"] = arrayOf<dynamic>(steps)
                    obj91["unit"] = "sec"
                    obj91["sides"] = false
                    obj91["variantDescription"] = "Movimiento específico de la secuencia documentada de Pau Fit. Esquema propio; revisión visual del video pendiente."
                    obj91["cue"] = "Utiliza un movimiento controlado. El esquema no observa tu postura."
                    obj91["control"] = "Mantén un recorrido que controles."
                    obj91["setup"] = "Consulta la preparación y ejecución de este movimiento."
                    obj91["breath"] = "Respira durante el movimiento sin retener el aire."
                }
                if (truth((kind === "overhead-triceps"))) {
                    e.floor = false
                    e.overhead = true
                    e.zones = jsArray { array98 ->
                        appendAll(array98, E.byId("overheadpress").zones)
                    }
                }
                if (truth((kind === "standing-fly"))) {
                    e.category = "Fuerza"
                    e.group = "Pecho"
                    e.equipment = "db"
                    e.requirements = jsonValue("[\"db\"]")
                    e.level = 1
                    e.muscles = "Pecho y hombros"
                    e.zones = jsArray { array113 ->
                        appendAll(array113, E.byId("overheadpress").zones)
                    }
                }
                if (truth((kind === "knee-push-child"))) {
                    e.kneeBend = true
                    e.zones = jsArray { array118 ->
                        appendAll(array118, jsConstruct(global_Set, arrayOf<dynamic>(jsArray { array119 ->
                            appendAll(array119, e.zones)
                            appendAll(array119, E.byId("childpose").zones)
                        })))
                    }
                }
                E.exercises.push(e)
                global_MotionDB[id] = jsObject { obj122 ->
                    obj122["name"] = name
                    obj122["hold"] = false
                    obj122["frames"] = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                        return frame(kind, i)
                    }))
                }
            }
        }
        root.SourceExercises12 = rows.map((fun(arg125: dynamic): dynamic {
            var id: dynamic = absent
            val unpacked126: dynamic = fromIterable(arg125)
            id = unpacked126[0]
            return id
        }))
        global_Catalog.baseExercises = E.exercises.filter((fun(e: dynamic): dynamic {
            return (e.variant === "base")
        })).length
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
