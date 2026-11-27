@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installintent

import hunter.browser.*

@JsName("Object") private external var global_Object: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installIntent() {
    dynamicValue((fun(root: dynamic): dynamic {
        var clean: dynamic = absent
        var tokens: dynamic = absent
        var vocabulary: dynamic = absent
        var models: dynamic = absent
        var interpret: dynamic = absent
        interpret = fun(text: dynamic): dynamic {
            val input: dynamic = clean(text)
            val words: dynamic = tokens(text).filter((fun(w: dynamic): dynamic {
                return vocabulary.has(w)
            }))
            if (truth(jsOr((!truth(words.length))) { dynamicValue(jsRegExp("\\b(no quiero|no deseo|evitar|no busco)\\b", "")).test(input) })) {
                return jsonValue("{\"goal\":null,\"reason\":\"Necesito que elijas el objetivo: no distingo con suficiente claridad la intención o su negación.\"}")
            }
            val scores: dynamic = global_Object.entries(models).map((fun(arg24: dynamic): dynamic {
                var goal: dynamic = absent
                var m: dynamic = absent
                val unpacked25: dynamic = fromIterable(arg24)
                goal = unpacked25[0]
                m = unpacked25[1]
                return jsObject { obj26 ->
                    obj26["goal"] = goal
                    obj26["score"] = words.reduce((fun(n: dynamic, w: dynamic): dynamic {
                        return jsAdd(n, global_Math.log(jsDivide(jsAdd(jsOr(m.counts[w]) { 0 }, 1), jsAdd(m.total, vocabulary.size))))
                    }), 0)
                }
            })).sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract(b.score, a.score)
            }))
            val margin: dynamic = jsSubtract(scores[0].score, scores[1].score)
            if (truth(jsLess(margin, 0.35))) {
                return jsonValue("{\"goal\":null,\"reason\":\"Hay varias interpretaciones posibles. Selecciona el objetivo que prefieras.\"}")
            }
            val medical: dynamic = dynamicValue(jsRegExp("lesion|hernia|dolor|cirugia|fractura|rehabil|curar", "")).test(input)
            return jsObject { obj27 ->
                obj27["goal"] = scores[0].goal
                obj27["reason"] = global_GoalPlans.plans[scores[0].goal].why
                obj27["medical"] = medical
            }
        }
        val extra: dynamic = jsonValue("{\"balance\":{\"name\":\"Practicar equilibrio con apoyo\",\"base\":\"mobility\",\"pattern\":[\"recovery\",\"strength\",\"recovery\"],\"why\":\"Prioriza equilibrio con apoyo y movilidad compatibles.\",\"prefer\":[\"balance\",\"tandem\",\"linewalk\",\"calf\"]},\"coordination\":{\"name\":\"Mejorar coordinación básica\",\"base\":\"general\",\"pattern\":[\"cardio\",\"recovery\",\"cardio\"],\"why\":\"Prioriza pasos controlados y movimientos alternos.\",\"prefer\":[\"march\",\"vstep\",\"seatedmarch\",\"seatedpunch\",\"bird\"]},\"flexibility\":{\"name\":\"Trabajar flexibilidad\",\"base\":\"mobility\",\"pattern\":[\"recovery\",\"recovery\",\"strength\"],\"why\":\"Prioriza movilidad y estiramientos cómodos.\",\"prefer\":[\"hamstretch\",\"calfstretch\",\"chestopen\",\"latstretch\"]},\"posture\":{\"name\":\"Practicar control postural\",\"base\":\"general\",\"pattern\":[\"recovery\",\"strength\",\"recovery\"],\"why\":\"Practica control del tronco y omóplatos; no corrige diagnósticos posturales.\",\"prefer\":[\"scapular\",\"bird\",\"wallslide\",\"deadbug\"]},\"dumbbells\":{\"name\":\"Progresar con mancuernas en casa\",\"base\":\"strength\",\"pattern\":[\"strength\",\"cardio\",\"strength\"],\"why\":\"Prioriza fuerza con el material disponible y registra el esfuerzo.\"},\"short\":{\"name\":\"Sostener entrenamientos breves\",\"base\":\"general\",\"gentle\":true,\"pattern\":[\"strength\",\"cardio\",\"recovery\"],\"why\":\"Una serie por misión para mantener sesiones breves.\"}}")
        global_Object.assign(global_GoalPlans.plans, extra)
        global_Object.entries(extra).forEach((fun(arg3: dynamic): dynamic {
            var k: dynamic = absent
            var v: dynamic = absent
            val unpacked4: dynamic = fromIterable(arg3)
            k = unpacked4[0]
            v = unpacked4[1]
            return run {
                val target5: dynamic = global_Adapt.goals
                val key6: dynamic = k
                val assigned7: dynamic = v.name
                target5[key6] = assigned7
                assigned7
            }
        }))
        val examples: dynamic = jsonValue("{\"general\":[\"mantener rutina constante habito semanal\",\"entrenar con regularidad ser constante\"],\"strength\":[\"ganar fuerza levantar peso ser fuerte\",\"aumentar fuerza muscular\"],\"muscle\":[\"ganar masa muscular musculos volumen cuerpo esculpido\",\"hipertrofia desarrollar musculatura\"],\"endurance\":[\"mejorar resistencia cardio cansarme menos\",\"aguantar caminar sin fatigarme\"],\"activity\":[\"bajar peso adelgazar perder grasa\",\"controlar peso actividad adelgazar\"],\"mobility\":[\"mejorar movilidad mover articulaciones\",\"moverme con comodidad movilidad\"],\"upper\":[\"fortalecer brazos pecho espalda hombros\",\"desarrollar tren superior\"],\"lower\":[\"fortalecer piernas gluteos tren inferior\",\"ganar fuerza en piernas\"],\"core\":[\"fortalecer abdomen abdominales zona media\",\"trabajar core abdomen\"],\"lowimpact\":[\"entrenar sin saltos bajo impacto\",\"ejercicios sin impacto\"],\"returning\":[\"volver entrenar despues pausa retomar\",\"retomar ejercicio principiante empezar de nuevo\"],\"seated\":[\"ejercicios sentado silla\",\"entrenar sentado actividad en silla\"],\"functional\":[\"actividades diarias cargar compras\",\"mejorar condicion vida cotidiana\"],\"comfortable\":[\"mantener actividad recuperacion autorizada\",\"actividad durante recuperacion con indicaciones\"],\"balance\":[\"mejorar equilibrio estabilidad con apoyo\",\"practicar equilibrio\"],\"coordination\":[\"mejorar coordinacion movimientos alternos\",\"coordinar brazos piernas\"],\"flexibility\":[\"ser flexible flexibilidad estirar\",\"aumentar flexibilidad estiramientos\"],\"posture\":[\"control postural postura\",\"practicar postura omoplatos\"],\"dumbbells\":[\"entrenar mancuernas fuerza con pesas\",\"progresar con mancuernas\"],\"short\":[\"rutina breve poco tiempo diez minutos\",\"sesiones cortas entrenamientos breves\"]}")
        val stop: dynamic = jsConstruct(global_Set, arrayOf<dynamic>(dynamicValue("quiero deseo necesito mi el la los las un una de del para por con y que ser en mas me a tener lograr").split(" ")))
        clean = (fun(s: dynamic): dynamic {
            return s.toLowerCase().normalize("NFD").replace(jsRegExp("[\\u0300-\\u036f]", "g"), "")
        })
        tokens = (fun(s: dynamic): dynamic {
            return clean(s).split(jsRegExp("[^a-z0-9]+", "")).filter((fun(w: dynamic): dynamic {
                return jsAnd(jsMore(w.length, 2)) { (!truth(stop.has(w))) }
            }))
        })
        vocabulary = jsConstruct(global_Set, js("[]"))
        models = js("({})")
        if (true) {
            val iterator8: dynamic = iteratorOf(global_Object.entries(examples))
            while (true) {
                val next9: dynamic = iterator8.next()
                if (truth(next9.done)) break
                var key: dynamic = absent
                var rows: dynamic = absent
                val unpacked10: dynamic = fromIterable(next9.value)
                key = unpacked10[0]
                rows = unpacked10[1]
                val counts: dynamic = js("({})")
                var total: dynamic = 0
                if (true) {
                    val iterator11: dynamic = iteratorOf(tokens(rows.join(" ")))
                    while (true) {
                        val next12: dynamic = iterator11.next()
                        if (truth(next12.done)) break
                        var word: dynamic = absent
                        word = next12.value
                        counts[word] = jsAdd(jsOr(counts[word]) { 0 }, 1)
                        vocabulary.add(word)
                        run {
                            val previous15: dynamic = numeric(total)
                            total = jsAdd(previous15, 1)
                        }
                    }
                }
                models[key] = jsObject { obj18 ->
                    obj18["counts"] = counts
                    obj18["total"] = total
                }
            }
        }
        root.Intent = jsObject { obj30 ->
            obj30["interpret"] = interpret
            obj30["examples"] = examples
            obj30["tokens"] = tokens
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
