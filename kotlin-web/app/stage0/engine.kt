@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installengine

import hunter.browser.*

@JsName("Map") private external var global_Map: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("module") private external var global_module: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installEngine() {
    dynamicValue((fun(root: dynamic): dynamic {
        var exercises: dynamic = absent
        var exerciseIndex48: dynamic = absent
        var indexed48: dynamic = absent
        var byId: dynamic = absent
        var dateKey: dynamic = absent
        var plus: dynamic = absent
        var diff: dynamic = absent
        var eligible: dynamic = absent
        var makeCycle: dynamic = absent
        var unitOf: dynamic = absent
        var sidesOf: dynamic = absent
        var stats: dynamic = absent
        byId = fun(id: dynamic): dynamic {
            if (truth(jsMore(indexed48, exercises.length))) {
                exerciseIndex48.clear()
                indexed48 = 0
            }
            if (true) {
                var firstIteration8 = true
                while (true) {
                    if (!firstIteration8) {
                        run {
                            val previous9: dynamic = numeric(indexed48)
                            indexed48 = jsAdd(previous9, 1)
                        }
                    }
                    firstIteration8 = false
                    if (!truth(jsLess(indexed48, exercises.length))) break
                    val e: dynamic = exercises[indexed48]
                    if (truth((!truth(exerciseIndex48.has(e.id))))) {
                        exerciseIndex48["set"](e.id, e)
                    }
                }
            }
            return exerciseIndex48["get"](id)
        }
        dateKey = fun(arg12: dynamic): dynamic {
            var d: dynamic = absent
            d = definedOr(arg12) { jsConstruct(global_Date, js("[]")) }
            return ("" + textOf(d.getFullYear()) + "-" + textOf(global_String(jsAdd(d.getMonth(), 1)).padStart(2, "0")) + "-" + textOf(global_String(d.getDate()).padStart(2, "0")) + "")
        }
        plus = fun(date: dynamic, n: dynamic): dynamic {
            val d: dynamic = jsConstruct(global_Date, arrayOf<dynamic>(jsAdd(date, "T12:00:00")))
            d.setDate(jsAdd(d.getDate(), n))
            return dateKey(d)
        }
        diff = fun(a: dynamic, b: dynamic): dynamic {
            return global_Math.round(jsDivide(jsSubtract(global_Date.parse(jsAdd(a, "T12:00:00Z")), global_Date.parse(jsAdd(b, "T12:00:00Z"))), 86400000))
        }
        eligible = fun(p: dynamic): dynamic {
            return jsAnd(jsAnd(jsAtLeast(p.age, 18)) { jsAtMost(p.age, 64) }) { (!truth(p.limits)) }
        }
        makeCycle = fun(p: dynamic, start: dynamic, index: dynamic, previous: dynamic, logs: dynamic): dynamic {
            var reps: dynamic = 8
            var sets: dynamic = (if (truth((p.experience === "beginner"))) 2 else 3)
            var decision: dynamic = "Adaptación inicial: aprende los movimientos con cargas cómodas."
            if (truth(previous)) {
                val sessions: dynamic = previous.days.filter((fun(d: dynamic): dynamic {
                    return (d.kind === "strength")
                }))
                val done: dynamic = sessions.map((fun(d: dynamic): dynamic {
                    return logs[d.date]
                })).filter((fun(l: dynamic): dynamic {
                    return jsAnd(jsAnd(l) { l.complete }) { (!truth(l.manual)) }
                }))
                val painful: dynamic = previous.days.some((fun(d: dynamic): dynamic {
                    return onPresent(logs[d.date]) { value21 -> value21.pain }
                }))
                val effort: dynamic = (if (truth(done.length)) jsDivide(done.reduce((fun(a: dynamic, l: dynamic): dynamic {
                    return jsAdd(a, l.rpe)
                }), 0), done.length) else 0)
                reps = previous.reps
                sets = previous.sets
                if (truth(jsOr(painful) { jsAtLeast(effort, 9) })) {
                    reps = global_Math.max(6, jsSubtract(reps, 2))
                    decision = "Menor volumen por molestias o esfuerzo alto. Si hubo dolor, consulta antes de retomar."
                } else {
                    if (truth(jsAnd(jsAnd(jsAnd(jsMore(sessions.length, 0)) { jsAtLeast(done.length, global_Math.ceil(jsMultiply(sessions.length, 0.8))) }) { jsAtMost(effort, 7) }) { done.every((fun(l: dynamic): dynamic {
                        return jsMore(l.rpe, 0)
                    })) })) {
                        reps = global_Math.min(12, jsAdd(reps, 1))
                        decision = (if (truth(jsMore(reps, previous.reps))) "Progresión: +1 repetición por serie, según tu registro." else "Consolidación: mantén 12 repeticiones. Puedes revisar la carga con un profesional.")
                    } else {
                        decision = "Consolidación: conserva las repeticiones y mejora la técnica y la constancia."
                    }
                }
            }
            val db: dynamic = (p.equipment === "db")
            val easy: dynamic = (p.experience === "beginner")
            val rot: dynamic = jsMod(index, 2)
            var A: dynamic = (if (truth(db)) arrayOf<dynamic>((if (truth(easy)) "squat" else "goblet"), "press", "row", "bridge", "deadbug") else jsonValue("[\"squat\",\"wall\",\"prone\",\"bridge\",\"deadbug\"]"))
            var B: dynamic = (if (truth(db)) jsonValue("[\"rdl\",\"neutral\",\"row\",\"calf\",\"bird\"]") else jsonValue("[\"hinge\",\"incline\",\"reverse\",\"calf\",\"bird\"]"))
            if (truth(jsAnd((!truth(easy))) { (!truth(db)) })) {
                A = jsonValue("[\"squat\",\"incline\",\"reverse\",\"bridge\",\"deadbug\"]")
                B = jsonValue("[\"squat\",\"pushup\",\"prone\",\"sideleg\",\"bird\"]")
            }
            if (truth(rot)) {
                A[jsSubtract(A.length, 1)] = "heel"
                B[jsSubtract(B.length, 1)] = "deadbug"
                if (truth(db)) {
                    A[1] = "neutral"
                    B[1] = "press"
                }
            }
            val strengthDays: dynamic = (if (truth((p.frequency === 2))) jsonValue("[0,3]") else jsonValue("[0,2,4]"))
            val days: dynamic = global_Array.from(jsonValue("{\"length\":21}"), (fun(_: dynamic, i: dynamic): dynamic {
                val kind: dynamic = (if (truth(strengthDays.includes(jsMod(i, 7)))) "strength" else (if (truth((jsMod(i, 7) === 6))) "rest" else "recovery"))
                val ids: dynamic = (if (truth((kind === "strength"))) (if (truth(jsMod(jsAdd(jsMultiply(global_Math.floor(jsDivide(i, 7)), strengthDays.length), strengthDays.indexOf(jsMod(i, 7))), 2))) B else A) else (if (truth((kind === "recovery"))) jsonValue("[\"march\",\"shoulder\",\"ankle\"]") else js("[]")))
                return jsObject { obj30 ->
                    obj30["date"] = plus(start, i)
                    obj30["kind"] = kind
                    obj30["items"] = ids.map((fun(id: dynamic): dynamic {
                        val e: dynamic = byId(id)
                        return jsObject { obj31 ->
                            obj31["id"] = id
                            obj31["sets"] = (if (truth((kind === "strength"))) sets else 1)
                            obj31["target"] = (if (truth((e.unit === "sec"))) (if (truth((id === "march"))) 180 else 30) else reps)
                        }
                    }))
                }
            }))
            return jsObject { obj32 ->
                obj32["index"] = index
                obj32["start"] = start
                obj32["reps"] = reps
                obj32["sets"] = sets
                obj32["decision"] = decision
                obj32["days"] = days
                obj32["profile"] = global_JSON.parse(global_JSON.stringify(p))
            }
        }
        stats = fun(logs: dynamic, arg35: dynamic): dynamic {
            var today: dynamic = absent
            today = definedOr(arg35) { dateKey() }
            val entries: dynamic = global_Object.entries(logs).filter((fun(arg36: dynamic): dynamic {
                var d: dynamic = absent
                val unpacked37: dynamic = fromIterable(arg36)
                d = unpacked37[0]
                return jsAtMost(d, today)
            }))
            val completed: dynamic = entries.filter((fun(arg38: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked39: dynamic = fromIterable(arg38)
                l = unpacked39[1]
                return l.complete
            }))
            var series: dynamic = 0
            var reps: dynamic = 0
            var seconds: dynamic = 0
            var volume: dynamic = 0
            var exerciseCount: dynamic = 0
            if (true) {
                val iterator40: dynamic = iteratorOf(entries)
                while (true) {
                    val next41: dynamic = iterator40.next()
                    if (truth(next41.done)) break
                    var l: dynamic = absent
                    val unpacked42: dynamic = fromIterable(next41.value)
                    l = unpacked42[1]
                    if (true) {
                        val iterator43: dynamic = iteratorOf(jsOr(l.items) { js("[]") })
                        while (true) {
                            val next44: dynamic = iterator43.next()
                            if (truth(next44.done)) break
                            var item: dynamic = absent
                            item = next44.value
                            var count: dynamic = 0
                            if (true) {
                                val iterator45: dynamic = iteratorOf(jsOr(item["actual"]) { js("[]") })
                                while (true) {
                                    val next46: dynamic = iterator45.next()
                                    if (truth(next46.done)) break
                                    var s: dynamic = absent
                                    s = next46.value
                                    if (truth(s.done)) {
                                        run {
                                            val previous47: dynamic = numeric(series)
                                            series = jsAdd(previous47, 1)
                                        }
                                        run {
                                            val previous48: dynamic = numeric(count)
                                            count = jsAdd(previous48, 1)
                                        }
                                        val n: dynamic = jsOr(global_Number(s["value"])) { 0 }
                                        if (truth((unitOf(item) === "sec"))) {
                                            run {
                                                seconds = jsAdd(seconds, jsMultiply(n, (if (truth(sidesOf(item))) 2 else 1)))
                                            }
                                        } else {
                                            val n2: dynamic = jsMultiply(n, (if (truth(sidesOf(item))) 2 else 1))
                                            run {
                                                reps = jsAdd(reps, n2)
                                            }
                                            run {
                                                volume = jsAdd(volume, jsMultiply(n2, jsOr(global_Number(s.kg)) { 0 }))
                                            }
                                        }
                                    }
                                }
                            }
                            if (truth(count)) {
                                run {
                                    val previous49: dynamic = numeric(exerciseCount)
                                    exerciseCount = jsAdd(previous49, 1)
                                }
                            }
                        }
                    }
                }
            }
            var cursor: dynamic = (if (truth(completed.some((fun(arg50: dynamic): dynamic {
                var d: dynamic = absent
                val unpacked51: dynamic = fromIterable(arg50)
                d = unpacked51[0]
                return (d === today)
            })))) today else plus(today, (-numeric(1))))
            var streak: dynamic = 0
            val dates: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(completed.map((fun(arg52: dynamic): dynamic {
                var d: dynamic = absent
                val unpacked53: dynamic = fromIterable(arg52)
                d = unpacked53[0]
                return d
            }))))
            while (truth(dates.has(cursor))) {
                run {
                    val previous54: dynamic = numeric(streak)
                    streak = jsAdd(previous54, 1)
                }
                cursor = plus(cursor, (-numeric(1)))
            }
            val xp: dynamic = completed.reduce((fun(a: dynamic, arg55: dynamic): dynamic {
                var l: dynamic = absent
                val unpacked56: dynamic = fromIterable(arg55)
                l = unpacked56[1]
                return jsAdd(a, (if (truth((l.kind === "strength"))) 100 else (if (truth((l.kind === "cardio"))) 70 else (if (truth((l.kind === "mixed"))) 100 else (if (truth((l.kind === "recovery"))) 40 else 20)))))
            }), 0)
            return jsObject { obj57 ->
                obj57["days"] = completed.length
                obj57["strength"] = completed.filter((fun(arg58: dynamic): dynamic {
                    var l: dynamic = absent
                    val unpacked59: dynamic = fromIterable(arg58)
                    l = unpacked59[1]
                    return (l.kind === "strength")
                })).length
                obj57["series"] = series
                obj57["reps"] = reps
                obj57["seconds"] = seconds
                obj57["volume"] = volume
                obj57["exerciseCount"] = exerciseCount
                obj57["streak"] = streak
                obj57["xp"] = xp
                obj57["level"] = jsAdd(1, global_Math.floor(jsDivide(xp, 300)))
            }
        }
        val ex: dynamic = (fun(id: dynamic, name: dynamic, group: dynamic, equipment: dynamic, level: dynamic, steps: dynamic, cue: dynamic, arg3: dynamic, arg4: dynamic): dynamic {
            var unit: dynamic = absent
            unit = definedOr(arg3) { "reps" }
            var sides: dynamic = absent
            sides = definedOr(arg4) { false }
            return jsObject { obj5 ->
                obj5["id"] = id
                obj5["name"] = name
                obj5["group"] = group
                obj5["equipment"] = equipment
                obj5["level"] = level
                obj5["steps"] = steps
                obj5["cue"] = cue
                obj5["unit"] = unit
                obj5["sides"] = sides
            }
        })
        exercises = arrayOf<dynamic>(ex("chair", "Sentarse y levantarse", "Piernas", "body", 0, "Coloca una silla firme contra la pared. Apoya los pies, inclina suavemente el torso y levántate. Baja con control.", "Usa una altura que permita moverte sin dolor."), ex("squat", "Sentadilla libre", "Piernas", "body", 1, "Pies a una distancia cómoda. Lleva las caderas atrás y flexiona las rodillas. Vuelve a subir con control.", "Mantén los pies apoyados y un recorrido cómodo."), ex("goblet", "Sentadilla goblet", "Piernas", "db", 1, "Sujeta una mancuerna junto al pecho. Flexiona caderas y rodillas, y vuelve a subir.", "Empieza con una carga que puedas controlar."), ex("wall", "Flexión en pared", "Pecho", "body", 0, "Apoya las manos en una pared a la altura del pecho. Flexiona los codos y acerca el torso; empuja para volver.", "Mantén el cuerpo alineado."), ex("incline", "Flexión inclinada", "Pecho", "body", 1, "Apoya las manos en una superficie alta, firme y fija. Baja el pecho y empuja con control.", "No uses muebles que se deslicen o vuelquen."), ex("pushup", "Flexión de suelo", "Pecho", "body", 2, "Manos debajo de los hombros. Baja el torso de forma controlada y vuelve a subir.", "Evita hundir la zona lumbar."), ex("press", "Press de suelo", "Pecho", "db", 1, "Túmbate boca arriba con rodillas flexionadas. Empuja las mancuernas desde el pecho y baja hasta apoyar suavemente los brazos.", "Muñecas alineadas; no golpees el suelo."), ex("neutral", "Press de suelo neutro", "Pecho", "db", 1, "En el suelo, palmas enfrentadas. Empuja las mancuernas hacia arriba y baja lentamente.", "Mantén los codos cerca del torso."), ex("bridge", "Puente de glúteos", "Cadera", "body", 0, "Boca arriba, pies apoyados y rodillas flexionadas. Eleva la pelvis y baja lentamente.", "No arquees la espalda al subir."), ex("hinge", "Bisagra de cadera", "Cadera", "body", 0, "Rodillas ligeramente flexionadas. Lleva las caderas atrás con espalda neutra y vuelve a erguirte.", "El movimiento parte de la cadera."), ex("rdl", "Peso muerto rumano", "Cadera", "db", 1, "Mancuernas cerca de los muslos. Lleva las caderas atrás y baja hasta donde controles la espalda; vuelve a subir.", "Mantén las cargas cerca del cuerpo."), ex("row", "Remo con apoyo", "Espalda", "db", 0, "Apoya una mano en una superficie firme. Con la otra, lleva la mancuerna hacia la cadera y baja con control.", "Completa ambos lados. No gires el torso.", "reps", true), ex("bentrow", "Remo bilateral", "Espalda", "db", 1, "Inclina el torso con una bisagra de cadera. Lleva ambas mancuernas hacia las caderas y bájalas.", "Sostén la espalda en una posición cómoda y estable."), ex("prone", "Elevación en W", "Espalda", "body", 0, "Boca abajo, brazos en W. Eleva suavemente los brazos juntando los omóplatos, y baja.", "Recorrido corto; no fuerces cuello ni espalda."), ex("reverse", "Ángel invertido corto", "Espalda", "body", 1, "Boca abajo, frente sobre una toalla. Desliza los brazos en un arco corto y controlado.", "Mantén el cuello relajado."), ex("curl", "Curl de bíceps", "Brazos", "db", 0, "De pie, brazos junto al cuerpo. Flexiona los codos para subir las mancuernas y bájalas lentamente.", "Evita balancear el torso."), ex("hammer", "Curl martillo", "Brazos", "db", 0, "Palmas enfrentadas, codos al costado. Sube las mancuernas y baja con control.", "No levantes los hombros."), ex("calf", "Elevación de talones", "Piernas", "body", 0, "De pie junto a un apoyo estable. Eleva los talones y desciende despacio.", "Usa el apoyo para el equilibrio."), ex("dbcalf", "Talones con mancuernas", "Piernas", "db", 1, "De pie, sujeta cargas ligeras a los lados. Eleva los talones y baja despacio.", "Si pierdes estabilidad, usa la versión sin carga."), ex("deadbug", "Dead bug", "Core", "body", 0, "Boca arriba, rodillas elevadas. Extiende un brazo y la pierna contraria, vuelve y alterna.", "Cada lado cuenta una repetición. No arquees la espalda."), ex("bird", "Bird dog", "Core", "body", 0, "A cuatro apoyos, extiende un brazo y la pierna contraria. Regresa y alterna.", "Cada lado cuenta una repetición; pelvis estable."), ex("plank", "Plancha de antebrazos", "Core", "body", 2, "Apoya antebrazos y puntas de pies. Mantén el cuerpo alineado mientras respiras.", "Termina la serie si pierdes la posición.", "sec"), ex("heel", "Deslizamiento de talón", "Core", "body", 0, "Boca arriba con rodillas flexionadas. Desliza un talón hacia delante y vuelve; alterna.", "Cada lado cuenta una repetición; respira con normalidad."), ex("sideleg", "Abducción lateral", "Cadera", "body", 0, "Túmbate de lado. Eleva la pierna superior con un recorrido corto y baja lentamente.", "Completa ambos lados sin girar la pelvis.", "reps", true), ex("march", "Marcha suave en casa", "Movilidad", "body", 0, "Camina o marcha a un ritmo cómodo, con espacio libre alrededor.", "Reduce el ritmo si no puedes hablar con comodidad.", "sec"), ex("shoulder", "Círculos de hombros", "Movilidad", "body", 0, "De pie o sentado, dibuja círculos pequeños con los hombros hacia atrás y hacia delante.", "Movimiento suave, sin forzar.", "sec"), ex("ankle", "Movilidad de tobillos", "Movilidad", "body", 0, "Sentado, mueve los tobillos lentamente arriba, abajo y en círculos.", "Alterna los dos tobillos.", "sec"), ex("cat", "Movilidad de columna", "Movilidad", "body", 0, "A cuatro apoyos, redondea y extiende suavemente la espalda acompañando la respiración.", "Usa un rango pequeño y cómodo."), ex("wallslide", "Deslizamiento en pared", "Hombros", "body", 0, "De pie junto a una pared, desliza los brazos lentamente hacia arriba y abajo.", "No fuerces el recorrido por encima de la cabeza."), ex("lateral", "Elevación lateral ligera", "Hombros", "db", 1, "Con cargas ligeras, eleva los brazos a los lados hasta una altura cómoda y baja.", "Codos suaves; no encojas los hombros."))
        exerciseIndex48 = jsConstruct(global_Map, js("[]"))
        indexed48 = 0
        unitOf = (fun(item: dynamic): dynamic {
            return jsOr(item.unit) { byId(item.id).unit }
        })
        sidesOf = (fun(item: dynamic): dynamic {
            return jsAnd(byId(item.id).sides) { jsOr((!truth(item.side))) { (item.side === "both") } }
        })
        val api: dynamic = jsObject { obj60 ->
            obj60["exercises"] = exercises
            obj60["byId"] = byId
            obj60["unitOf"] = unitOf
            obj60["sidesOf"] = sidesOf
            obj60["dateKey"] = dateKey
            obj60["plus"] = plus
            obj60["diff"] = diff
            obj60["eligible"] = eligible
            obj60["makeCycle"] = makeCycle
            obj60["stats"] = stats
        }
        if (truth((js("typeof module") !== "undefined"))) {
            global_module.exports = api
        }
        root.Engine = api
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
