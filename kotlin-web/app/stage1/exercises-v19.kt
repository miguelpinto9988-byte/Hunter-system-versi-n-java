@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installexercisesv19

import hunter.browser.*

@JsName("JSON") private external var global_JSON: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("MotionDB") private external var global_MotionDB: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installExercisesV19() {
    dynamicValue((fun(root: dynamic): dynamic {
        var clone: dynamic = absent
        var added: dynamic = absent
        var sources: dynamic = absent
        var add: dynamic = absent
        var timed: dynamic = absent
        var oneAndHalf: dynamic = absent
        var keyframes: dynamic = absent
        var shoulderPose: dynamic = absent
        add = fun(id: dynamic, parentId: dynamic, name: dynamic, level: dynamic, instructions: dynamic, arg5: dynamic, frames: dynamic): dynamic {
            var extra: dynamic = absent
            extra = definedOr(arg5) { js("({})") }
            val parent: dynamic = global_Engine.byId(parentId)
            val original: dynamic = global_MotionDB[jsOr(parent.baseId) { parentId }]
            if (truth(jsOr((!truth(parent))) { (!truth(original)) })) {
                throw global_Error(jsAdd("Falta movimiento padre: ", parentId))
            }
            val e: dynamic = jsObject { obj6 ->
                copyFields(obj6, clone(parent))
                obj6["id"] = id
                obj6["baseId"] = id
                obj6["name"] = name
                obj6["level"] = level
                obj6["variant"] = "base"
                obj6["originalVideo15"] = false
                obj6["sourceOnly"] = false
                obj6["steps"] = instructions.join(" ")
                obj6["instructions"] = instructions
                obj6["variantDescription"] = jsAdd("Variante propia con trayectoria 2D específica. ", jsOr(extra.variantDescription) { "" })
                obj6["techniqueSources19"] = arrayOf<dynamic>(sources.library)
                copyFields(obj6, extra)
            }
            removeKey(e, "media")
            removeKey(e, "demo")
            e.progressionTier19 = level
            global_Engine.exercises.push(e)
            global_MotionDB[id] = jsObject { obj11 ->
                copyFields(obj11, clone(original))
                obj11["id"] = id
                obj11["name"] = name
                obj11["instructions"] = instructions
                obj11["frames"] = jsOr(frames) { clone(original.frames) }
                obj11["hold"] = jsAnd((e.unit === "sec")) { original.hold }
            }
            added.push(id)
            return e
        }
        timed = fun(parentId: dynamic, mapper: dynamic, arg14: dynamic): dynamic {
            var hold: dynamic = absent
            hold = definedOr(arg14) { false }
            val m: dynamic = global_MotionDB[parentId]
            return global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                val phase: dynamic = mapper(jsDivide(i, 48))
                val a: dynamic = m.frames[jsMod(global_Math.floor(phase), 48)]
                val b: dynamic = m.frames[jsMod(jsAdd(global_Math.floor(phase), 1), 48)]
                val t: dynamic = jsMod(phase, 1)
                val f: dynamic = clone(a)
                f.p = a.p.map((fun(p: dynamic, j: dynamic): dynamic {
                    return p.map((fun(v: dynamic, k: dynamic): dynamic {
                        return jsAdd(v, jsMultiply(jsSubtract(b.p[j][k], v), t))
                    }))
                }))
                f.loads = a.loads.map((fun(p: dynamic, j: dynamic): dynamic {
                    return p.map((fun(v: dynamic, k: dynamic): dynamic {
                        return jsAdd(v, jsMultiply(jsSubtract(dynamicValue(jsOr(b.loads[j]) { p })[k], v), t))
                    }))
                }))
                f.detail.stage18 = (if (truth(hold)) (if (truth(jsLess(i, 16))) "SUBIDA CONTROLADA" else (if (truth(jsLess(i, 32))) "PAUSA · SIN PERDER LA POSTURA" else "RETORNO CONTROLADO")) else (if (truth(jsLess(i, 32))) "DESCENSO DE TRES SEGUNDOS" else "SUBIDA CONTROLADA"))
                return f
            }))
        }
        oneAndHalf = fun(parentId: dynamic): dynamic {
            val frames: dynamic = timed(parentId, (fun(t: dynamic): dynamic {
                return (if (truth(jsLess(t, 0.3))) jsMultiply(t, 80) else (if (truth(jsLess(t, 0.5))) jsSubtract(24, jsMultiply(jsSubtract(t, 0.3), 60)) else (if (truth(jsLess(t, 0.7))) jsAdd(12, jsMultiply(jsSubtract(t, 0.5), 60)) else jsAdd(24, jsMultiply(jsSubtract(t, 0.7), 80)))))
            }))
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration23 = true
                while (true) {
                    if (!firstIteration23) {
                        run {
                            val previous24: dynamic = numeric(i)
                            i = jsAdd(previous24, 1)
                        }
                    }
                    firstIteration23 = false
                    if (!truth(jsLess(i, 48))) break
                    frames[i].detail.stage18 = (if (truth(jsLess(i, 15))) "RECORRIDO COMPLETO" else (if (truth(jsLess(i, 24))) "REGRESO HASTA LA MITAD" else (if (truth(jsLess(i, 34))) "MEDIO RECORRIDO ADICIONAL" else "REGRESO COMPLETO")))
                }
            }
            return frames
        }
        keyframes = fun(keys: dynamic): dynamic {
            return global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                var k: dynamic = 0
                while (truth(jsLess(keys[jsAdd(k, 1)][0], i))) {
                    run {
                        val previous29: dynamic = numeric(k)
                        k = jsAdd(previous29, 1)
                    }
                }
                val a: dynamic = keys[k]
                val b: dynamic = keys[jsAdd(k, 1)]
                val t: dynamic = jsDivide(jsSubtract(i, a[0]), jsSubtract(b[0], a[0]))
                val u: dynamic = jsMultiply(jsMultiply(t, t), jsSubtract(3, jsMultiply(2, t)))
                val f: dynamic = clone(a[1])
                f.p = f.p.map((fun(p: dynamic, j: dynamic): dynamic {
                    return p.map((fun(v: dynamic, n: dynamic): dynamic {
                        return jsAdd(v, jsMultiply(jsSubtract(b[1].p[j][n], v), u))
                    }))
                }))
                f.loads = f.loads.map((fun(p: dynamic, j: dynamic): dynamic {
                    return p.map((fun(v: dynamic, n: dynamic): dynamic {
                        return jsAdd(v, jsMultiply(jsSubtract(dynamicValue(jsOr(b[1].loads[j]) { p })[n], v), u))
                    }))
                }))
                return f
            }))
        }
        shoulderPose = fun(letter: dynamic, lift: dynamic): dynamic {
            val p: dynamic = jsonValue("[[360,225],[360,295],[360,480],[260,345],[210,280],[460,345],[510,280],[333,585],[330,700],[387,585],[390,700]]")
            if (truth((letter === "T"))) {
                p[3] = jsonValue("[235,295]")
                p[4] = jsonValue("[145,295]")
                p[5] = jsonValue("[485,295]")
                p[6] = jsonValue("[575,295]")
            }
            if (truth((letter === "Y"))) {
                p[3] = jsonValue("[270,210]")
                p[4] = jsonValue("[220,145]")
                p[5] = jsonValue("[450,210]")
                p[6] = jsonValue("[500,145]")
            }
            return jsObject { obj136 ->
                obj136["p"] = p
                obj136["equipment"] = js("[]")
                obj136["loads"] = js("[]")
                obj136["detail"] = jsObject { obj137 ->
                    obj137["topShoulder19"] = true
                    obj137["letter19"] = letter
                    obj137["lift19"] = lift
                    obj137["stage18"] = (if (truth(jsMore(lift, 0.2))) "ELEVA SUAVEMENTE LOS BRAZOS" else "BRAZOS CERCA DEL SUELO")
                }
            }
        }
        clone = (fun(x: dynamic): dynamic {
            return global_JSON.parse(global_JSON.stringify(x))
        })
        added = js("[]")
        sources = jsonValue("{\"pushup\":\"https://www.nasm.org/resource-center/exercise-library/push-up\",\"decline\":\"https://www.nasm.org/resource-center/exercise-library/decline-push-up\",\"pike\":\"https://www.nasm.org/resource-center/exercise-library/pike-push-up\",\"archer\":\"https://www.nasm.org/resource-center/exercise-library/archer-push-up\",\"unilateral\":\"https://www.acefitness.org/resources/everyone/exercise-library/329/single-leg-romanian-deadlift/\",\"singleRdl\":\"https://www.acefitness.org/resources/everyone/exercise-library/127/single-arm-single-leg-romanian-dead-lift/\",\"singleSquat\":\"https://www.nasm.org/resource-center/exercise-library/single-leg-squat\",\"shoulder\":\"https://www.acefitness.org/resources/everyone/exercise-library/249/prone-scapular-shoulder-stabilization-series-i-y-t-w-o-formation/\",\"library\":\"https://www.acefitness.org/resources/everyone/exercise-library/\"}")
        if (true) {
            val iterator34: dynamic = iteratorOf(global_Engine.exercises)
            while (true) {
                val next35: dynamic = iterator34.next()
                if (truth(next35.done)) break
                var e: dynamic = absent
                e = next35.value
                if (truth((e.baseId === "pushup"))) {
                    e.level = 1
                }
            }
        }
        add("pushup-control19", "pushup", "Flexión de suelo con descenso de tres segundos", 1, jsonValue("[\"Apoya las manos bajo los hombros y las puntas de los pies; mantén cabeza, tronco y pelvis alineados.\",\"Acerca el pecho al suelo durante tres segundos, con los codos en diagonal respecto al torso.\",\"Empuja con control durante dos segundos. Termina la serie al perder la alineación.\"]"), jsObject { obj38 ->
            obj38["tempoSeconds18"] = 5
            obj38["techniqueSources19"] = arrayOf<dynamic>(sources.pushup)
        }, timed("pushup", (fun(t: dynamic): dynamic {
            return (if (truth(jsLess(t, 0.6))) jsMultiply(t, 40) else jsAdd(24, jsMultiply(jsSubtract(t, 0.6), 60)))
        })))
        val decline: dynamic = clone(global_MotionDB.pushup.frames)
        if (true) {
            val iterator39: dynamic = iteratorOf(decline)
            while (true) {
                val next40: dynamic = iterator39.next()
                if (truth(next40.done)) break
                var f: dynamic = absent
                f = next40.value
                val lift: dynamic = 100
                if (true) {
                    val iterator41: dynamic = iteratorOf(jsonValue("[2,7,9]"))
                    while (true) {
                        val next42: dynamic = iterator41.next()
                        if (truth(next42.done)) break
                        var j: dynamic = absent
                        j = next42.value
                        run {
                            val target43: dynamic = f.p[j]
                            val key44: dynamic = 1
                            target43[key44] = jsSubtract(target43[key44], jsMultiply(lift, (if (truth((j === 2))) 0.5 else 0.82)))
                        }
                    }
                }
                run {
                    val target45: dynamic = f.p[8]
                    val key46: dynamic = 1
                    target45[key46] = jsSubtract(target45[key46], lift)
                }
                run {
                    val target47: dynamic = f.p[10]
                    val key48: dynamic = 1
                    target47[key48] = jsSubtract(target47[key48], lift)
                }
                f.equipment = jsonValue("[[\"bench\",[525,570,650,710]]]")
            }
        }
        add("decline19", "pushup", "Flexión con pies elevados en banco", 2, jsonValue("[\"Coloca las puntas de los pies sobre un banco estable y las manos en el suelo bajo los hombros.\",\"Mantén el abdomen firme y baja el pecho flexionando los codos sin hundir la pelvis.\",\"Empuja para regresar. Usa primero flexiones de suelo si no puedes conservar la posición.\"]"), jsObject { obj51 ->
            obj51["requirements"] = jsonValue("[\"bench\"]")
            obj51["bench"] = true
            obj51["techniqueSources19"] = arrayOf<dynamic>(sources.decline)
            obj51["repRange19"] = jsonValue("[6,12]")
            obj51["prerequisite19"] = "Control de la flexión de suelo."
        }, decline)
        val pikeUp: dynamic = jsonValue("{\"p\":[[240,460],[285,490],[420,330],[280,570],[275,665],[260,570],[250,665],[475,480],[565,665],[450,485],[540,665]],\"equipment\":[],\"loads\":[],\"detail\":{}}")
        val pikeDown: dynamic = jsonValue("{\"p\":[[210,585],[275,560],[420,365],[325,610],[275,665],[300,620],[250,665],[475,505],[565,665],[450,510],[540,665]],\"equipment\":[],\"loads\":[],\"detail\":{}}")
        add("pike19", "pushup", "Flexión pica para hombros", 2, jsonValue("[\"Apoya manos y pies en el suelo y eleva la cadera formando una V invertida.\",\"Flexiona los codos para acercar la cabeza al suelo, conservando la cadera elevada.\",\"Empuja y regresa a la V. No apoyes el peso en la cabeza ni fuerces el cuello.\"]"), jsObject { obj52 ->
            obj52["group"] = "Hombros"
            obj52["overhead"] = true
            obj52["repRange19"] = jsonValue("[5,10]")
            obj52["techniqueSources19"] = arrayOf<dynamic>(sources.pike)
            obj52["prerequisite19"] = "Flexiones de suelo y control de hombros."
        }, keyframes(arrayOf<dynamic>(arrayOf<dynamic>(0, pikeUp), arrayOf<dynamic>(24, pikeDown), arrayOf<dynamic>(48, pikeUp))))
        val archer: dynamic = clone(global_MotionDB.pushup.frames)
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
                if (!truth(jsLess(i, 48))) break
                val a: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2))), 2)
                val f: dynamic = archer[i]
                f.p = arrayOf<dynamic>(arrayOf<dynamic>(jsSubtract(360, jsMultiply(62, a)), jsAdd(285, jsMultiply(45, a))), arrayOf<dynamic>(jsSubtract(360, jsMultiply(62, a)), jsAdd(350, jsMultiply(45, a))), arrayOf<dynamic>(jsSubtract(360, jsMultiply(40, a)), jsAdd(490, jsMultiply(20, a))), arrayOf<dynamic>(jsSubtract(275, jsMultiply(45, a)), jsAdd(410, jsMultiply(65, a))), jsonValue("[195,515]"), arrayOf<dynamic>(jsSubtract(480, jsMultiply(15, a)), jsAdd(440, jsMultiply(20, a))), jsonValue("[570,515]"), jsonValue("[325,575]"), jsonValue("[320,690]"), jsonValue("[405,575]"), jsonValue("[410,690]"))
                f.detail.front19 = true
                f.detail.stage18 = (if (truth(jsLess(i, 24))) "CARGA HACIA UN BRAZO" else "REGRESO AL CENTRO")
            }
        }
        add("archer19", "pushup", "Flexión arquero", 2, jsonValue("[\"En plancha alta, separa las manos más que en una flexión normal; apoya ambos pies.\",\"Lleva el pecho hacia una mano mientras flexionas ese codo y alargas el brazo contrario.\",\"Vuelve al centro y realiza el otro lado. Cuenta una repetición por lado y evita girar la pelvis.\"]"), jsObject { obj61 ->
            obj61["sides"] = true
            obj61["repRange19"] = jsonValue("[4,8]")
            obj61["techniqueSources19"] = arrayOf<dynamic>(sources.archer)
            obj61["prerequisite19"] = "Flexiones de suelo con buena estabilidad."
        }, archer)
        if (true) {
            val iterator62: dynamic = iteratorOf(jsonValue("[[\"squat-15-19\",\"squat\",\"Sentadilla de una repetición y media\"],[\"goblet-15-19\",\"goblet\",\"Sentadilla goblet de una repetición y media\"],[\"split-15-19\",\"split\",\"Sentadilla dividida de una repetición y media\"],[\"row-15-19\",\"row\",\"Remo unilateral de una repetición y media\"],[\"overhead-15-19\",\"overheadpress\",\"Press de hombros de una repetición y media\"]]"))
            while (true) {
                val next63: dynamic = iterator62.next()
                if (truth(next63.done)) break
                var id: dynamic = absent
                var parent: dynamic = absent
                var name: dynamic = absent
                val unpacked64: dynamic = fromIterable(next63.value)
                id = unpacked64[0]
                parent = unpacked64[1]
                name = unpacked64[2]
                val p: dynamic = global_Engine.byId(parent)
                val leg: dynamic = dynamicValue(jsonValue("[\"Piernas\",\"Cadera\"]")).includes(p.group)
                add(id, parent, name, 2, arrayOf<dynamic>(p.instructions[0], (if (truth(leg)) "Baja con control, sube hasta la mitad, vuelve a bajar y después sube por completo. Ese ciclo cuenta como una repetición." else "Completa el esfuerzo, vuelve hasta la mitad, repite la fase de esfuerzo y regresa por completo. Ese ciclo cuenta como una repetición."), "Conserva el mismo recorrido y evita rebotes. Reduce la carga si pierdes el control."), jsObject { obj65 ->
                    obj65["repRange19"] = jsonValue("[6,10]")
                    obj65["tempoSeconds18"] = 6
                    obj65["techniqueSources19"] = arrayOf<dynamic>(sources.library)
                    obj65["variantDescription"] = "Un ciclo completo y medio recorrido adicional por repetición."
                }, oneAndHalf(parent))
            }
        }
        val singleSquat: dynamic = clone(global_MotionDB.squat.frames)
        if (true) {
            val iterator66: dynamic = iteratorOf(singleSquat)
            while (true) {
                val next67: dynamic = iterator66.next()
                if (truth(next67.done)) break
                var f: dynamic = absent
                f = next67.value
                val hip: dynamic = f.p[2]
                f.p[9] = arrayOf<dynamic>(jsAdd(hip[0], 88), jsAdd(hip[1], 48))
                f.p[10] = arrayOf<dynamic>(jsAdd(hip[0], 120), jsAdd(hip[1], 118))
                f.p[8] = jsonValue("[375,700]")
            }
        }
        add("single-squat19", "squat", "Sentadilla a una pierna", 2, jsonValue("[\"Apoya todo un pie, eleva la otra pierna y extiende los brazos para equilibrarte.\",\"Flexiona cadera y rodilla de la pierna apoyada hasta una profundidad que puedas controlar.\",\"Empuja con ese pie para volver a subir. Completa un lado y después el otro.\"]"), jsObject { obj74 ->
            obj74["sides"] = true
            obj74["repRange19"] = jsonValue("[5,10]")
            obj74["prerequisite19"] = "Sentadilla y sentadilla dividida con estabilidad."
            obj74["techniqueSources19"] = arrayOf<dynamic>(sources.singleSquat)
        }, singleSquat)
        val singleHinge: dynamic = clone(global_MotionDB.rdl.frames)
        if (true) {
            var i: dynamic = absent
            i = 0
            var firstIteration75 = true
            while (true) {
                if (!firstIteration75) {
                    run {
                        val previous76: dynamic = numeric(i)
                        i = jsAdd(previous76, 1)
                    }
                }
                firstIteration75 = false
                if (!truth(jsLess(i, 48))) break
                val f: dynamic = singleHinge[i]
                val a: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2))), 2)
                val hip: dynamic = f.p[2]
                f.p[9] = arrayOf<dynamic>(jsSubtract(jsSubtract(hip[0], 10), jsMultiply(90, a)), jsSubtract(jsAdd(hip[1], jsMultiply(105, jsSubtract(1, a))), jsMultiply(20, a)))
                f.p[10] = arrayOf<dynamic>(jsSubtract(hip[0], jsMultiply(215, a)), jsSubtract(700, jsMultiply(210, a)))
                f.loads = js("[]")
            }
        }
        add("singlehinge-free19", "singlehinge", "Bisagra de cadera a una pierna sin apoyo", 2, jsonValue("[\"Apoya una pierna, deja su rodilla ligeramente flexionada y mantén la pelvis nivelada.\",\"Lleva la cadera atrás al inclinar el tronco y alargar la pierna libre hacia atrás.\",\"Vuelve a la posición inicial sin apoyar el pie libre hasta terminar la repetición. Cambia de lado.\"]"), jsObject { obj83 ->
            obj83["sides"] = true
            obj83["repRange19"] = jsonValue("[6,12]")
            obj83["techniqueSources19"] = arrayOf<dynamic>(sources.unilateral)
        }, singleHinge)
        val singleLoad: dynamic = clone(singleHinge)
        if (true) {
            val iterator84: dynamic = iteratorOf(singleLoad)
            while (true) {
                val next85: dynamic = iterator84.next()
                if (truth(next85.done)) break
                var f: dynamic = absent
                f = next85.value
                f.loads = arrayOf<dynamic>(clone(f.p[4]))
            }
        }
        add("single-rdl19", "rdl", "Peso muerto rumano a una pierna con mancuerna", 2, jsonValue("[\"Sujeta una mancuerna con la mano contraria a la pierna de apoyo; mantén esa rodilla ligeramente flexionada.\",\"Lleva la cadera atrás mientras la pierna libre se alarga y la mancuerna baja cerca de la pierna apoyada.\",\"Vuelve a ponerte de pie sin girar la pelvis. Haz todas las repeticiones y cambia de lado.\"]"), jsObject { obj88 ->
            obj88["sides"] = true
            obj88["repRange19"] = jsonValue("[6,12]")
            obj88["techniqueSources19"] = arrayOf<dynamic>(sources.singleRdl)
        }, singleLoad)
        val sideFull: dynamic = clone(global_MotionDB.sideplank.frames)
        if (true) {
            val iterator89: dynamic = iteratorOf(sideFull)
            while (true) {
                val next90: dynamic = iterator89.next()
                if (truth(next90.done)) break
                var f: dynamic = absent
                f = next90.value
                f.p[7] = jsonValue("[500,625]")
                f.p[8] = jsonValue("[615,675]")
                f.p[9] = jsonValue("[482,620]")
                f.p[10] = jsonValue("[598,670]")
            }
        }
        add("full-sideplank19", "sideplank", "Plancha lateral con piernas extendidas", 2, jsonValue("[\"Apoya un antebrazo bajo el hombro y el lateral de los pies, con las piernas extendidas.\",\"Eleva la pelvis y conserva una línea desde cabeza a pies mientras respiras.\",\"Baja con control, descansa y repite al otro lado.\"]"), jsonValue("{\"unit\":\"sec\",\"sides\":true,\"repRange19\":null}"), sideFull)
        val sideLift: dynamic = clone(sideFull)
        if (true) {
            var i: dynamic = absent
            i = 0
            var firstIteration99 = true
            while (true) {
                if (!firstIteration99) {
                    run {
                        val previous100: dynamic = numeric(i)
                        i = jsAdd(previous100, 1)
                    }
                }
                firstIteration99 = false
                if (!truth(jsLess(i, 48))) break
                val f: dynamic = sideLift[i]
                val a: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2))), 2)
                if (true) {
                    val iterator101: dynamic = iteratorOf(jsonValue("[0,1,2,3,5,6,7,9]"))
                    while (true) {
                        val next102: dynamic = iterator101.next()
                        if (truth(next102.done)) break
                        var j: dynamic = absent
                        j = next102.value
                        run {
                            val target103: dynamic = f.p[j]
                            val key104: dynamic = 1
                            target103[key104] = jsAdd(target103[key104], jsMultiply(35, jsSubtract(1, a)))
                        }
                    }
                }
                f.detail.stage18 = (if (truth(jsLess(i, 24))) "ELEVA LA PELVIS" else "BAJA CON CONTROL")
            }
        }
        add("sideplank-lift19", "full-sideplank19", "Elevación de pelvis en plancha lateral", 2, jsonValue("[\"Apoya antebrazo y lateral de los pies con piernas extendidas.\",\"Desde una pelvis ligeramente baja, eleva la cadera hasta alinear el cuerpo.\",\"Desciende unos centímetros con control sin colapsar el hombro. Completa ambos lados.\"]"), jsonValue("{\"unit\":\"reps\",\"sides\":true,\"repRange19\":[6,12]}"), sideLift)
        global_MotionDB["sideplank-lift19"].hold = false
        val reach: dynamic = clone(global_MotionDB.pushup.frames[0])
        val reachFrames: dynamic = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
            val f: dynamic = clone(reach)
            val a: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 4))), 2)
            val j: dynamic = (if (truth(jsLess(i, 24))) 3 else 5)
            val k: dynamic = (if (truth(jsLess(i, 24))) 4 else 6)
            f.p[j] = arrayOf<dynamic>(jsSubtract(f.p[1][0], jsMultiply(65, a)), jsAdd(f.p[1][1], jsMultiply(95, jsSubtract(1, a))))
            f.p[k] = arrayOf<dynamic>(jsSubtract(f.p[1][0], jsMultiply(150, a)), jsAdd(f.p[1][1], jsMultiply(190, jsSubtract(1, a))))
            f.detail.stage18 = (if (truth(jsLess(i, 24))) "ALCANCE DE UN BRAZO" else "CAMBIO DE BRAZO")
            return f
        }))
        add("plankreach19", "shouldertap", "Plancha alta con alcance alterno", 2, jsonValue("[\"En plancha alta, separa ligeramente los pies y mantén la pelvis quieta.\",\"Eleva una mano y alarga el brazo al frente sin inclinar el torso.\",\"Vuelve a apoyar esa mano antes de cambiar de brazo. Cuenta cada alcance como una repetición.\"]"), jsonValue("{\"unit\":\"reps\",\"sides\":false,\"repRange19\":[8,16],\"overhead\":true}"), reachFrames)
        if (true) {
            val iterator115: dynamic = iteratorOf(jsonValue("[[\"singlebridge-pause19\",\"singlebridge\",\"Puente a una pierna con pausa de dos segundos\",2],[\"singlecalf-pause19\",\"singlecalf\",\"Elevación de talón a una pierna con pausa\",2],[\"lateral-pause19\",\"lateral\",\"Elevación lateral con pausa y descenso controlado\",2],[\"curl-pause19\",\"curl\",\"Curl de bíceps con pausa y descenso controlado\",2]]"))
            while (true) {
                val next116: dynamic = iterator115.next()
                if (truth(next116.done)) break
                var id: dynamic = absent
                var parent: dynamic = absent
                var name: dynamic = absent
                var level: dynamic = absent
                val unpacked117: dynamic = fromIterable(next116.value)
                id = unpacked117[0]
                parent = unpacked117[1]
                name = unpacked117[2]
                level = unpacked117[3]
                val p: dynamic = global_Engine.byId(parent)
                add(id, parent, name, level, arrayOf<dynamic>(p.instructions[0], p.instructions.slice(1).join(" "), "Mantén la fase de esfuerzo durante dos segundos y regresa durante dos segundos, sin balanceo."), jsonValue("{\"tempoSeconds18\":6,\"repRange19\":[8,14]}"), timed(parent, (fun(t: dynamic): dynamic {
                    return (if (truth(jsLess(t, jsDivide(1, 3)))) jsMultiply(t, 72) else (if (truth(jsLess(t, jsDivide(2, 3)))) 24 else jsAdd(24, jsMultiply(jsSubtract(t, jsDivide(2, 3)), 72))))
                }), true))
            }
        }
        global_MotionDB.prone.frames = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
            return shoulderPose("W", jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 2))), 2))
        }))
        global_MotionDB.prone.view19 = "top"
        val w: dynamic = global_Engine.byId("prone")
        w.steps = "Túmbate boca abajo con la frente sobre una toalla y el cuello alineado. Dobla los codos formando una W, separados unos 45 grados del torso. Eleva ligeramente manos y codos al juntar suavemente los omóplatos. Baja sin levantar el pecho ni arquear la espalda."
        w.instructions = w.steps.split(". ").filter(global_Boolean)
        w.setup = "Frente apoyada y abdomen activo; vista superior de los brazos."
        w.cue = "Recorrido corto desde los hombros. Mantén el pecho apoyado."
        w.techniqueSources19 = arrayOf<dynamic>(sources.shoulder)
        if (true) {
            val iterator152: dynamic = iteratorOf(jsonValue("[[\"w-t19\",\"Secuencia escapular W–T\",[\"W\",\"T\"],1],[\"y-t-w19\",\"Secuencia escapular Y–T–W con pausas\",[\"Y\",\"T\",\"W\"],2]]"))
            while (true) {
                val next153: dynamic = iterator152.next()
                if (truth(next153.done)) break
                var id: dynamic = absent
                var name: dynamic = absent
                var letters: dynamic = absent
                var level: dynamic = absent
                val unpacked154: dynamic = fromIterable(next153.value)
                id = unpacked154[0]
                name = unpacked154[1]
                letters = unpacked154[2]
                level = unpacked154[3]
                val frames: dynamic = global_Array.from(jsonValue("{\"length\":48}"), (fun(_: dynamic, i: dynamic): dynamic {
                    val segment: dynamic = jsDivide(48, letters.length)
                    val k: dynamic = global_Math.floor(jsDivide(i, segment))
                    val t: dynamic = jsDivide(jsMod(i, segment), segment)
                    val f: dynamic = shoulderPose(letters[k], (if (truth(jsLess(t, 0.75))) global_Math.sin(jsMultiply(jsDivide(t, 0.75), global_Math.PI)) else 0))
                    if (truth(jsMore(t, 0.75))) {
                        val next: dynamic = shoulderPose(letters[jsMod(jsAdd(k, 1), letters.length)], 0)
                        val u: dynamic = jsDivide(jsSubtract(t, 0.75), 0.25)
                        f.p = f.p.map((fun(p: dynamic, j: dynamic): dynamic {
                            return p.map((fun(v: dynamic, n: dynamic): dynamic {
                                return jsAdd(v, jsMultiply(jsSubtract(next.p[j][n], v), u))
                            }))
                        }))
                    }
                    f.detail.stage18 = jsAdd(jsAdd(jsAdd("FORMA ", letters[k]), " · "), (if (truth(jsLess(t, 0.75))) "ELEVA Y CONTROLA" else "CAMBIA CON LOS BRAZOS BAJOS"))
                    return f
                }))
                add(id, "prone", name, level, arrayOf<dynamic>("Boca abajo, apoya la frente y conserva el pecho en el suelo.", jsAdd(jsAdd("Forma ", letters.join(", después ")), " con los brazos. En cada forma, eleva las manos y codos unos centímetros y mantén dos segundos."), "Baja los brazos antes de cambiar de forma. Toda la secuencia cuenta como una repetición."), jsObject { obj159 ->
                    obj159["repRange19"] = jsonValue("[5,10]")
                    obj159["tempoSeconds18"] = jsMultiply(letters.length, 4)
                    obj159["techniqueSources19"] = arrayOf<dynamic>(sources.shoulder)
                    obj159["variantDescription"] = "Más posiciones y coordinación escapular; no añade carga externa."
                }, frames)
                global_MotionDB[id].view19 = "top"
            }
        }
        val shuffle: dynamic = clone(global_MotionDB.shuffle.frames)
        if (true) {
            var i: dynamic = absent
            i = 0
            var firstIteration162 = true
            while (true) {
                if (!firstIteration162) {
                    run {
                        val previous163: dynamic = numeric(i)
                        i = jsAdd(previous163, 1)
                    }
                }
                firstIteration162 = false
                if (!truth(jsLess(i, 48))) break
                val f: dynamic = shuffle[i]
                val a: dynamic = jsDivide(jsSubtract(1, global_Math.cos(jsMultiply(jsMultiply(jsDivide(i, 48), global_Math.PI), 4))), 2)
                if (true) {
                    val iterator164: dynamic = iteratorOf(jsonValue("[0,1,2,3,4,5,6]"))
                    while (true) {
                        val next165: dynamic = iterator164.next()
                        if (truth(next165.done)) break
                        var j: dynamic = absent
                        j = next165.value
                        run {
                            val target166: dynamic = f.p[j]
                            val key167: dynamic = 1
                            target166[key167] = jsAdd(target166[key167], jsMultiply(a, 30))
                        }
                    }
                }
                f.detail.stage18 = (if (truth(jsLess(i, 24))) "DOS PASOS HACIA UN LADO" else "CAMBIA DE DIRECCIÓN")
            }
        }
        add("shuffle-touch19", "shuffle", "Desplazamiento lateral con cambio de dirección", 2, jsonValue("[\"Marca dos referencias cercanas en el suelo y adopta una posición ligeramente flexionada.\",\"Da dos pasos laterales sin cruzar los pies hacia una referencia y frena de forma controlada.\",\"Cambia hacia la otra referencia. Mantén el espacio despejado y la mirada al frente.\"]"), jsonValue("{\"unit\":\"sec\",\"family\":\"agility\",\"kneeBend\":true}"), shuffle)
        add("skater-stick19", "skaterhop", "Salto de patinador con pausa de equilibrio", 2, jsonValue("[\"Desde una pierna, impulsa un salto lateral corto hacia la otra.\",\"Aterriza flexionando ligeramente la pierna y mantén el equilibrio durante dos segundos.\",\"Repite hacia el otro lado. Cada aterrizaje cuenta como una repetición.\"]"), jsonValue("{\"family\":\"power\",\"repRange19\":[4,8],\"tempoSeconds18\":6}"), timed("skaterhop", (fun(t: dynamic): dynamic {
            return (if (truth(jsLess(t, 0.3))) jsMultiply(t, 80) else (if (truth(jsLess(t, 0.6))) 24 else jsAdd(24, jsMultiply(jsSubtract(t, 0.6), 60))))
        })))
        root.ExerciseExpansion19 = jsObject { obj172 ->
            obj172["added"] = added
            obj172["sources"] = sources
            obj172["keyframes"] = keyframes
            obj172["timed"] = timed
        }
        return absent
    }))(global_window)
}
