@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installplanningv14

import hunter.browser.*

@JsName("Schedule") private external var global_Schedule: dynamic
@JsName("GoalGroups14") private external var global_GoalGroups14: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("TrainingIntensity") private external var global_TrainingIntensity: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installPlanningV14() {
    dynamicValue((fun(root: dynamic): dynamic {
        val oldItems: dynamic = global_Schedule.items
        global_Schedule.items = (fun(p: dynamic, kind: dynamic, rotation: dynamic, carry: dynamic): dynamic {
            val base: dynamic = oldItems(p, kind, rotation, carry)
            val group: dynamic = global_GoalGroups14.of(p)
            if (truth(jsOr(jsOr((!truth(global_Adapt.mode(p).auto))) { global_Adapt.mode(p).reduced }) { onPresent(global_GoalPlans.plans[p.finalTarget]) { value7 -> value7.gentle } })) {
                return base
            }
            val specific: dynamic = (if (truth(jsAnd((group === "athletic")) { (kind === "cardio") })) jsonValue("[\"fastfeet\",\"shuffle\",\"stepcross\",\"lowhop\",\"skaterreach\",\"highknees\",\"squatjump\"]") else (if (truth(jsAnd((group === "movement")) { (kind === "recovery") })) jsonValue("[\"ankledrive\",\"hipcircle\",\"wallslide\",\"tandem\",\"singlehinge\",\"thoracic\",\"balance\"]") else null))
            if (truth((!truth(specific)))) {
                return base
            }
            val pool: dynamic = specific.map((fun(id: dynamic): dynamic {
                return global_Engine.byId(id)
            })).filter((fun(e: dynamic): dynamic {
                return global_Adapt.allowed(e, p)
            }))
            val count: dynamic = (if (truth((p.trainingMode === "routines"))) 4 else global_Math.min(jsOr(p.dailyMissions) { 3 }, pool.length))
            return (if (truth(pool.length)) global_Array.from(jsObject { obj8 ->
                obj8["length"] = global_Math.min(count, pool.length)
            }, (fun(_: dynamic, i: dynamic): dynamic {
                return global_TrainingIntensity.dose(pool[jsMod(jsAdd(rotation, i), pool.length)], p, kind)
            })) else base)
        })
        val make: dynamic = global_Engine.makeCycle
        global_Engine.makeCycle = (fun(p: dynamic, start: dynamic, index: dynamic, last: dynamic, logs: dynamic): dynamic {
            val c: dynamic = make(p, start, index, last, logs)
            val plan: dynamic = global_GoalGroups14.plan(p)
            c.planVersion14 = 14
            c.goalPlan14 = plan
            c.decision = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(plan.name, ". "), plan.reason), " "), plan.healthReason), " Revisión semanal según sesiones registradas, esfuerzo y dolor.")
            return c
        })
        return absent
    }))(global_window)
}
