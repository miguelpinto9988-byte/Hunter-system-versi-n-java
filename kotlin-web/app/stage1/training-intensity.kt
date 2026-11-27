@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installtrainingintensity

import hunter.browser.*

@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installTrainingIntensity() {
    dynamicValue((fun(root: dynamic): dynamic {
        var levels: dynamic = absent
        var scale: dynamic = absent
        var dose: dynamic = absent
        dose = fun(e: dynamic, p: dynamic, kind: dynamic): dynamic {
            val v: dynamic = jsOr(levels[p.difficulty]) { levels.basic }
            val reduced: dynamic = jsOr(global_Adapt.mode(p).reduced) { onPresent(global_GoalPlans.plans[p.finalTarget]) { value5 -> value5.gentle } }
            if (truth(jsOr(reduced) { (kind === "recovery") })) {
                return jsObject { obj6 ->
                    obj6["id"] = e.id
                    obj6["sets"] = 1
                    obj6["target"] = (if (truth((e.unit === "sec"))) (if (truth(reduced)) 15 else 20) else 6)
                    obj6["rest"] = (if (truth((kind === "recovery"))) 20 else 90)
                }
            }
            if (truth((e.family === "power"))) {
                return jsObject { obj7 ->
                    obj7["id"] = e.id
                    obj7["sets"] = (if (truth((p.difficulty === "advanced"))) 4 else (if (truth((p.difficulty === "intermediate"))) 3 else 2))
                    obj7["target"] = global_Math.max(2, global_Math.floor(jsMultiply((if (truth((p.difficulty === "advanced"))) 6 else (if (truth((p.difficulty === "intermediate"))) 5 else 4)), scale(p))))
                    obj7["rest"] = global_Math.max(90, jsOr(p.restSeconds) { 90 })
                }
            }
            return jsObject { obj8 ->
                obj8["id"] = e.id
                obj8["sets"] = (if (truth((kind === "cardio"))) v.cardioSets else v.sets)
                obj8["target"] = global_Math.max((if (truth((e.unit === "sec"))) 10 else 4), global_Math.floor(jsMultiply((if (truth((e.unit === "sec"))) (if (truth((kind === "cardio"))) v.cardioSeconds else v.seconds) else v.reps), scale(p))))
                obj8["rest"] = (if (truth((kind === "cardio"))) global_Math.max(45, jsOr(p.restSeconds) { 90 }) else global_Math.max(90, jsOr(p.restSeconds) { 90 }))
            }
        }
        levels = jsonValue("{\"basic\":{\"sets\":3,\"reps\":14,\"seconds\":40,\"cardioSets\":3,\"cardioSeconds\":50},\"intermediate\":{\"sets\":4,\"reps\":14,\"seconds\":45,\"cardioSets\":3,\"cardioSeconds\":60},\"advanced\":{\"sets\":4,\"reps\":16,\"seconds\":50,\"cardioSets\":4,\"cardioSeconds\":70}}")
        scale = (fun(p: dynamic): dynamic {
            return (if (truth(global_Number.isFinite(p.trainingScale))) global_Math.max(0.4, global_Math.min(1, p.trainingScale)) else 1)
        })
        val previous: dynamic = global_Schedule.items
        global_Schedule.items = (fun(p: dynamic, kind: dynamic, rotation: dynamic, carry: dynamic): dynamic {
            val items: dynamic = previous(p, kind, rotation, carry)
            if (truth(jsOr(jsOr(jsOr((!truth(global_Adapt.mode(p).auto))) { global_Adapt.mode(p).reduced }) { onPresent(global_GoalPlans.plans[p.finalTarget]) { value13 -> value13.gentle } }) { (kind === "recovery") })) {
                return items
            }
            return items.map((fun(x: dynamic): dynamic {
                return jsObject { obj14 ->
                    copyFields(obj14, x)
                    copyFields(obj14, dose(global_Engine.byId(x.id), p, kind))
                    obj14["target"] = (if (truth((global_Engine.byId(x.id).family === "power"))) dose(global_Engine.byId(x.id), p, kind).target else global_Math.max(global_Math.floor(jsMultiply(x.target, scale(p))), dose(global_Engine.byId(x.id), p, kind).target))
                }
            }))
        })
        val previousMake: dynamic = global_Engine.makeCycle
        global_Engine.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, logs: dynamic): dynamic {
            val c: dynamic = previousMake(p, start, index, last, logs)
            if (truth(jsAnd(jsAnd(global_Adapt.mode(p).auto) { (!truth(global_Adapt.mode(p).reduced)) }) { (!truth(onPresent(global_GoalPlans.plans[p.finalTarget]) { value19 -> value19.gentle })) })) {
                c.days.forEach((fun(d: dynamic): dynamic {
                    if (truth((d.kind === "recovery"))) {
                        return absent
                    }
                    d.items.forEach((fun(x: dynamic): dynamic {
                        val base: dynamic = dose(global_Engine.byId(x.id), p, d.kind)
                        if (truth(jsOr((global_Engine.byId(x.id).family === "power")) { jsAnd(last) { jsLess(scale(p), 1) } })) {
                            x.target = base.target
                        } else {
                            x.target = global_Math.max(x.target, base.target)
                        }
                        x.sets = base.sets
                        x.rest = global_Math.max(x.rest, base.rest)
                        return absent
                    }))
                    return absent
                }))
            }
            return c
        })
        root.TrainingIntensity = jsObject { obj30 ->
            obj30["levels"] = levels
            obj30["dose"] = dose
            obj30["scale"] = scale
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
