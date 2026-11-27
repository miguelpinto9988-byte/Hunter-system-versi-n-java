@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installobjectives

import hunter.browser.*

@JsName("Object") private external var global_Object: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Learning") private external var global_Learning: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installObjectives() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var plans: dynamic = absent
        var adjusted: dynamic = absent
        var candidates: dynamic = absent
        adjusted = fun(p: dynamic): dynamic {
            val spec: dynamic = jsOr(jsOr(plans[p.finalTarget]) { plans[p.goal] }) { plans.general }
            return jsObject { obj10 ->
                copyFields(obj10, p)
                copyFields(obj10, (if (truth((p.trainingMode === "routines"))) jsObject { obj11 ->
                    obj11["dailyMissions"] = absent
                } else js("({})")))
                obj10["goal"] = spec.base
                obj10["focus"] = jsOr(spec.focus) { p.focus }
                obj10["restrictions"] = jsObject { obj12 ->
                    copyFields(obj12, p.restrictions)
                    copyFields(obj12, (if (truth(spec.noImpact)) jsonValue("{\"noImpact\":true}") else js("({})")))
                    copyFields(obj12, (if (truth(spec.seated)) jsonValue("{\"seatedOnly\":true}") else js("({})")))
                }
            }
        }
        candidates = fun(p: dynamic, kind: dynamic, rotation: dynamic, logs: dynamic, date: dynamic, carry: dynamic, gentle: dynamic): dynamic {
            val results: dynamic = js("[]")
            if (true) {
                var i: dynamic = absent
                i = 0
                var firstIteration15 = true
                while (true) {
                    if (!firstIteration15) {
                        run {
                            val previous16: dynamic = numeric(i)
                            i = jsAdd(previous16, 1)
                        }
                    }
                    firstIteration15 = false
                    if (!truth(jsLess(i, 4))) break
                    var items: dynamic = global_Schedule.items(p, kind, jsAdd(rotation, i))
                    val spec: dynamic = jsOr(plans[p.finalTarget]) { plans.general }
                    val compatible: dynamic = E.exercises.filter((fun(e: dynamic): dynamic {
                        return jsAnd((e.variant === "base")) { global_Adapt.allowed(e, p) }
                    }))
                    if (truth(spec.prefer)) {
                        val preferred: dynamic = spec.prefer.map((fun(id: dynamic): dynamic {
                            return compatible.find((fun(e: dynamic): dynamic {
                                return (e.id === id)
                            }))
                        })).filter(global_Boolean)
                        if (truth(preferred.length)) {
                            items = jsArray { array17 ->
                                appendAll(array17, preferred.map((fun(e: dynamic): dynamic {
                                    return jsObject { obj18 ->
                                        obj18["id"] = e.id
                                        obj18["sets"] = 1
                                        obj18["target"] = (if (truth((e.unit === "sec"))) 15 else 6)
                                        obj18["rest"] = 60
                                    }
                                })))
                                appendAll(array17, items.filter((fun(x: dynamic): dynamic {
                                    return (!truth(preferred.some((fun(e: dynamic): dynamic {
                                        return (e.id === x.id)
                                    }))))
                                })))
                            }
                        }
                    }
                    if (truth(p.dailyMissions)) {
                        val n: dynamic = p.dailyMissions
                        val selected: dynamic = items.map((fun(x: dynamic): dynamic {
                            return x.id
                        }))
                        val additional: dynamic = compatible.filter((fun(e: dynamic): dynamic {
                            return jsAnd((!truth(selected.includes(e.id)))) { (e.category === (if (truth((kind === "strength"))) "Fuerza" else (if (truth((kind === "cardio"))) "Cardio" else "Movilidad"))) }
                        }))
                        if (true) {
                            val iterator19: dynamic = iteratorOf(additional)
                            while (true) {
                                val next20: dynamic = iterator19.next()
                                if (truth(next20.done)) break
                                var e: dynamic = absent
                                e = next20.value
                                if (truth(jsAtLeast(items.length, n))) {
                                    break
                                }
                                items.push((if (truth(root.TrainingIntensity)) root.TrainingIntensity.dose(e, p, kind) else jsObject { obj21 ->
                                    obj21["id"] = e.id
                                    obj21["sets"] = 1
                                    obj21["target"] = (if (truth((e.unit === "sec"))) 15 else 6)
                                    obj21["rest"] = 90
                                }))
                            }
                        }
                        items = items.slice(0, n)
                        val budget: dynamic = (if (truth(jsOr(global_Adapt.mode(p).reduced) { gentle })) 7 else (if (truth((p.difficulty === "advanced"))) 28 else 21))
                        items = items.slice(0, budget)
                        items.forEach((fun(x: dynamic): dynamic {
                            return run {
                                val target22: dynamic = x
                                val assigned24: dynamic = global_Math.max(1, global_Math.min(x.sets, global_Math.floor(jsDivide(budget, items.length))))
                                target22.sets = assigned24
                                assigned24
                            }
                        }))
                    }
                    if (truth(items.length)) {
                        items.forEach((fun(x: dynamic): dynamic {
                            if (truth(gentle)) {
                                x.sets = 1
                                x.target = global_Math.min(x.target, (if (truth((E.unitOf(x) === "sec"))) 20 else 6))
                            } else {
                                if (truth(jsAnd(jsAnd(jsAnd(carry) { (!truth(global_Adapt.mode(p).reduced)) }) { (kind === "strength") }) { (E.unitOf(x) === "reps") })) {
                                    x.target = global_Math.max(x.target, global_Math.min(15, carry))
                                }
                            }
                            return absent
                        }))
                        val prediction: dynamic = global_Learning.predict(global_Learning.samples(logs, date), items)
                        results.push(jsObject { obj31 ->
                            obj31["items"] = items
                            obj31["prediction"] = prediction
                        })
                    }
                }
            }
            return results.sort((fun(a: dynamic, b: dynamic): dynamic {
                return jsSubtract((if (truth(a.prediction)) global_Math.abs(jsSubtract(a.prediction.effort, 6)) else 2), (if (truth(b.prediction)) global_Math.abs(jsSubtract(b.prediction.effort, 6)) else 2))
            }))[0]
        }
        E = root.Engine
        val previous: dynamic = E.makeCycle
        plans = jsonValue("{\"general\":{\"name\":\"Mantener una rutina estable\",\"base\":\"general\",\"pattern\":[\"strength\",\"cardio\",\"strength\"],\"why\":\"Alterna fuerza general y cardio para practicar con constancia.\"},\"strength\":{\"name\":\"Aumentar mi fuerza\",\"base\":\"strength\",\"pattern\":[\"strength\",\"strength\",\"cardio\"],\"why\":\"Prioriza fuerza, descansos amplios y ejecución controlada.\"},\"muscle\":{\"name\":\"Desarrollar masa muscular\",\"base\":\"muscle\",\"pattern\":[\"strength\",\"strength\",\"strength\"],\"why\":\"Prioriza sesiones de fuerza y volumen progresivo según tu esfuerzo.\"},\"endurance\":{\"name\":\"Mejorar mi resistencia cardiovascular\",\"base\":\"endurance\",\"pattern\":[\"cardio\",\"cardio\",\"strength\"],\"why\":\"Da mayor frecuencia al cardio e incluye fuerza complementaria.\"},\"activity\":{\"name\":\"Acompañar el control de peso con actividad\",\"base\":\"activity\",\"pattern\":[\"cardio\",\"strength\",\"cardio\"],\"why\":\"Combina actividad cardiovascular y fuerza. No promete una pérdida de peso.\"},\"mobility\":{\"name\":\"Mejorar mi movilidad\",\"base\":\"mobility\",\"pattern\":[\"recovery\",\"recovery\",\"strength\"],\"why\":\"Prioriza movilidad compatible y fuerza complementaria.\"},\"upper\":{\"name\":\"Desarrollar el tren superior\",\"base\":\"muscle\",\"focus\":\"upper\",\"pattern\":[\"strength\",\"cardio\",\"strength\"],\"why\":\"Enfatiza pecho, espalda, hombros y brazos, con cardio entre sesiones.\"},\"lower\":{\"name\":\"Fortalecer piernas y glúteos\",\"base\":\"strength\",\"focus\":\"lower\",\"pattern\":[\"strength\",\"cardio\",\"strength\"],\"why\":\"Enfatiza piernas y cadera cuando son compatibles con tu perfil.\"},\"core\":{\"name\":\"Fortalecer la zona media\",\"base\":\"strength\",\"focus\":\"core\",\"pattern\":[\"strength\",\"cardio\",\"recovery\"],\"why\":\"Prioriza zona media y control del movimiento; no trata lesiones de espalda.\"},\"lowimpact\":{\"name\":\"Entrenar sin saltos\",\"base\":\"general\",\"noImpact\":true,\"pattern\":[\"cardio\",\"strength\",\"recovery\"],\"why\":\"Combina cardio, fuerza y movilidad sin movimientos con saltos.\"},\"returning\":{\"name\":\"Retomar el hábito después de una pausa\",\"base\":\"general\",\"gentle\":true,\"pattern\":[\"strength\",\"recovery\",\"cardio\"],\"why\":\"Empieza con una serie y sesiones de menor volumen.\"},\"seated\":{\"name\":\"Mantenerme activo sentado\",\"base\":\"activity\",\"seated\":true,\"gentle\":true,\"pattern\":[\"cardio\",\"strength\",\"recovery\"],\"why\":\"Utiliza movimientos sentados compatibles con las restricciones declaradas.\"},\"functional\":{\"name\":\"Mejorar mi condición para actividades diarias\",\"base\":\"general\",\"pattern\":[\"strength\",\"recovery\",\"cardio\"],\"why\":\"Combina fuerza general, movilidad y resistencia sin prometer resultados funcionales.\"},\"comfortable\":{\"name\":\"Mantener actividad durante una recuperación autorizada\",\"base\":\"mobility\",\"gentle\":true,\"pattern\":[\"recovery\",\"strength\",\"cardio\"],\"why\":\"Busca alternativas compatibles y mantiene volumen reducido. No sustituye la rehabilitación indicada.\"}}")
        global_Object.entries(plans).forEach((fun(arg3: dynamic): dynamic {
            var key: dynamic = absent
            var spec: dynamic = absent
            val unpacked4: dynamic = fromIterable(arg3)
            key = unpacked4[0]
            spec = unpacked4[1]
            return run {
                val target5: dynamic = global_Adapt.goals
                val key6: dynamic = key
                val assigned7: dynamic = spec.name
                target5[key6] = assigned7
                assigned7
            }
        }))
        E.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, arg36: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg36) { js("({})") }
            val spec: dynamic = jsOr(jsOr(plans[p.finalTarget]) { plans[p.goal] }) { plans.general }
            val ap: dynamic = adjusted(p)
            val c: dynamic = previous(ap, start, index, last, logs)
            c.profile = global_JSON.parse(global_JSON.stringify(p))
            c.objectiveVersion = 7
            c.decision = jsAdd(jsAdd(spec.why, " "), global_Adapt.mode(ap).reason)
            if (truth((!truth(global_Schedule.valid(p))))) {
                return c
            }
            if (truth((!truth(global_Adapt.mode(ap).auto)))) {
                if (truth(p.dailyMissions)) {
                    c.days = c.days.map((fun(d: dynamic): dynamic {
                        return jsObject { obj45 ->
                            copyFields(obj45, d)
                            obj45["tasks"] = (if (truth((p.weekPlan[global_Schedule.weekday(d.date)] === "rest"))) jsonValue("[{\"id\":\"rest\",\"text\":\"Completar mi día de descanso\",\"done\":false}]") else global_Array.from(jsObject { obj46 ->
                                obj46["length"] = p.dailyMissions
                            }, (fun(_: dynamic, j: dynamic): dynamic {
                                return jsObject { obj47 ->
                                    obj47["id"] = jsAdd("review", j)
                                    obj47["text"] = dynamicValue(jsonValue("[\"Revisar mis indicaciones profesionales\",\"Registrar cómo me siento\",\"Anotar las zonas con molestias\",\"Actualizar mis restricciones\",\"Preparar preguntas para mi profesional\",\"Revisar mi descanso\",\"Revisar mi objetivo personal\"]"))[j]
                                    obj47["done"] = false
                                }
                            })))
                        }
                    }))
                }
                return c
            }
            val ordinal: dynamic = 0
            var lastStrength: dynamic = jsAnd((onPresent(last) { value48 -> onPresent(value48.days) { value49 -> onPresent(value49.at((-numeric(1)))) { value50 -> value50.kind } } } === "strength")) { (E.diff(start, last.days.at((-numeric(1))).date) === 1) }
            val carry: dynamic = onPresent(last) { value51 -> onPresent(value51.days) { value52 -> onPresent(value52.filter((fun(d: dynamic): dynamic {
                return (d.kind === "strength")
            })).at((-numeric(1)))) { value54 -> onPresent(value54.items) { value55 -> onPresent(value55.find((fun(x: dynamic): dynamic {
                return (onPresent(E.byId(x.id)) { value56 -> value56.unit } === "reps")
            }))) { value57 -> value57.target } } } } }
            c.days = c.days.map((fun(d: dynamic, i: dynamic): dynamic {
                val choice: dynamic = p.weekPlan[global_Schedule.weekday(d.date)]
                val kind: dynamic = (if (truth((choice === "rest"))) "rest" else (if (truth((choice === "mobility"))) "recovery" else (if (truth((choice === "cardio"))) "cardio" else "strength")))
                if (truth((kind === "rest"))) {
                    lastStrength = false
                    return jsObject { obj60 ->
                        copyFields(obj60, d)
                        obj60["items"] = js("[]")
                        obj60["kind"] = "rest"
                        obj60["title"] = "Descanso elegido"
                    }
                }
                val preferred: dynamic = kind
                val result: dynamic = candidates(ap, kind, jsAdd(jsMultiply(index, 7), i), logs, d.date, carry, spec.gentle)
                val items: dynamic = jsOr(onPresent(result) { value61 -> value61.items }) { js("[]") }
                lastStrength = jsAnd((kind === "strength")) { jsMore(items.length, 0) }
                val note: dynamic = (if (truth((preferred !== kind))) "Se sustituyó la categoría por una alternativa compatible con tus restricciones. " else "")
                return jsObject { obj62 ->
                    copyFields(obj62, d)
                    obj62["items"] = items
                    obj62["kind"] = (if (truth(items.length)) kind else "rest")
                    obj62["title"] = (if (truth(items.length)) spec.name else "Revisión de alternativas")
                    obj62["adaptation"] = jsAdd(note, spec.why)
                    obj62["tasks"] = (if (truth(items.length)) global_Array.from(jsObject { obj63 ->
                        obj63["length"] = global_Math.max(0, jsSubtract(jsOr(p.dailyMissions) { items.length }, items.length))
                    }, (fun(_: dynamic, j: dynamic): dynamic {
                        return jsObject { obj64 ->
                            obj64["id"] = jsAdd("care", j)
                            obj64["text"] = dynamicValue(jsonValue("[\"Registrar cómo me siento\",\"Revisar mis indicaciones\",\"Preparar el espacio de entrenamiento\",\"Revisar la técnica de hoy\",\"Anotar mi descanso\",\"Planificar la próxima sesión\",\"Revisar mi objetivo\"]"))[j]
                            obj64["done"] = false
                        }
                    })) else jsonValue("[{\"id\":\"review\",\"text\":\"No hay movimientos compatibles en el catálogo para estas restricciones; registra aquí las indicaciones recibidas.\",\"done\":false}]"))
                }
            }))
            return c
        })
        root.GoalPlans = jsObject { obj67 ->
            obj67["plans"] = plans
            obj67["adjusted"] = adjusted
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
