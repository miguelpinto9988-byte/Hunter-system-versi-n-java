@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhuntermaterials3814

import hunter.browser.*

@JsName("Object") private external var global_Object: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterMaterials3814() {
    dynamicValue((fun(root: dynamic): dynamic {
        var requiredTokens: dynamic = absent
        var gymTokens: dynamic = absent
        var requirements: dynamic = absent
        var eligible: dynamic = absent
        var sessionCap: dynamic = absent
        var coverage: dynamic = absent
        requirements = fun(exercise: dynamic): dynamic {
            if (truth(jsOr((!truth(exercise))) { (jsTypeOf(exercise.id) !== "string") })) {
                return null
            }
            val items: dynamic = jsConstruct(global_Set, js("[]"))
            if (true) {
                val iterator5: dynamic = iteratorOf((if (truth(global_Array.isArray(exercise.requirements))) exercise.requirements else js("[]")))
                while (true) {
                    val next6: dynamic = iterator5.next()
                    if (truth(next6.done)) break
                    var k: dynamic = absent
                    k = next6.value
                    if (truth(jsOr((jsTypeOf(k) !== "string")) { (!truth(requiredTokens.has(k))) })) {
                        return null
                    }
                    items.add(k)
                }
            }
            val p: dynamic = exercise.param23
            if (truth(jsAnd(p) { (jsTypeOf(p) === "object") })) {
                if (truth((p.bench === true))) {
                    items.add("bench")
                }
                if (truth((p.step === true))) {
                    items.add("step")
                }
                if (truth((p.db === true))) {
                    items.add("db")
                }
            }
            if (truth((exercise.gymOnly23 === true))) {
                if (truth((!truth(global_Array.isArray(exercise.gymRequirements23))))) {
                    return null
                }
                if (true) {
                    val iterator7: dynamic = iteratorOf(exercise.gymRequirements23)
                    while (true) {
                        val next8: dynamic = iterator7.next()
                        if (truth(next8.done)) break
                        var k: dynamic = absent
                        k = next8.value
                        if (truth(jsOr((jsTypeOf(k) !== "string")) { (!truth(gymTokens.has(k))) })) {
                            return null
                        }
                        items.add(k)
                    }
                }
            }
            return jsArray { array9 ->
                appendAll(array9, items)
            }
        }
        eligible = fun(exercise: dynamic, profile: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(exercise))) { (!truth(profile)) }) { (!truth(onPresent(root.HunterSchedule3813) { value12 -> run { val owner14: dynamic = value12; onPresent(owner14.eligible) { method13 -> invokeWith(method13, owner14, arrayOf<dynamic>(exercise, profile)) } } })) })) {
                return false
            }
            val required: dynamic = requirements(exercise)
            if (truth((!truth(required)))) {
                return false
            }
            val gear: dynamic = global_String(jsOr(profile.equipment) { "body" })
            val configured: dynamic = (if (truth(global_Array.isArray(profile.gymEquipment23))) jsConstruct(global_Set, arrayOf<dynamic>(profile.gymEquipment23)) else jsConstruct(global_Set, js("[]")))
            if (true) {
                val iterator15: dynamic = iteratorOf(required)
                while (true) {
                    val next16: dynamic = iterator15.next()
                    if (truth(next16.done)) break
                    var k: dynamic = absent
                    k = next16.value
                    if (truth(jsAnd((k === "db")) { (!truth(dynamicValue(jsonValue("[\"db\",\"gym\"]")).includes(gear))) })) {
                        return false
                    }
                    if (truth(jsAnd((k === "gym")) { (gear !== "gym") })) {
                        return false
                    }
                    if (truth(jsAnd(jsAnd((k === "bench")) { (profile.bench !== true) }) { (!truth(configured.has("bench"))) })) {
                        return false
                    }
                    if (truth(jsAnd((k === "step")) { (profile.step !== true) })) {
                        return false
                    }
                    if (truth(jsAnd(jsAnd(gymTokens.has(k)) { (k !== "bench") }) { jsOr((gear !== "gym")) { (!truth(configured.has(k))) } })) {
                        return false
                    }
                }
            }
            return true
        }
        sessionCap = fun(profile: dynamic, kind: dynamic, arg19: dynamic): dynamic {
            var status: dynamic = absent
            status = definedOr(arg19) { js("({})") }
            if (truth((kind === "rest"))) {
                return 0
            }
            val raw: dynamic = global_Number(onPresent(profile) { value20 -> value20.minutes })
            val minutes: dynamic = (if (truth(global_Number.isFinite(raw))) global_Math.min(90, global_Math.max(10, raw)) else 20)
            val unit: dynamic = (if (truth((kind === "strength"))) 5 else (if (truth((kind === "cardio"))) 4 else 3))
            var cap: dynamic = global_Math.max(1, global_Math.min(7, global_Math.floor(jsDivide(minutes, unit))))
            val preferred: dynamic = global_Number((if (truth((onPresent(profile) { value21 -> value21.trainingMode } === "routines"))) onPresent(profile) { value22 -> value22.routineExercises3878 } else onPresent(profile) { value23 -> value23.dailyMissions }))
            if (truth(jsAnd(global_Number.isInteger(preferred)) { jsAtLeast(preferred, 1) })) {
                cap = global_Math.min(cap, preferred)
            }
            if (truth(onPresent(status) { value24 -> value24.reduced })) {
                cap = global_Math.min(cap, 3)
            }
            return cap
        }
        coverage = fun(arg27: dynamic): dynamic {
            var catalog: dynamic = absent
            catalog = definedOr(arg27) { js("[]") }
            val stats: dynamic = jsonValue("{\"total\":0,\"explicitAux\":0,\"invalid\":0,\"requiresBench\":0,\"requiresStep\":0,\"requiresGymGear\":0}")
            if (true) {
                val iterator28: dynamic = iteratorOf(catalog)
                while (true) {
                    val next29: dynamic = iterator28.next()
                    if (truth(next29.done)) break
                    var exercise: dynamic = absent
                    exercise = next29.value
                    if (truth((!truth(onPresent(exercise) { value30 -> value30.id })))) {
                        continue
                    }
                    run {
                        val target31: dynamic = stats
                        val previous33: dynamic = numeric(target31.total)
                        target31.total = jsAdd(previous33, 1)
                    }
                    val req: dynamic = requirements(exercise)
                    if (truth((!truth(req)))) {
                        run {
                            val target34: dynamic = stats
                            val previous36: dynamic = numeric(target34.invalid)
                            target34.invalid = jsAdd(previous36, 1)
                        }
                        continue
                    }
                    if (truth(req.some((fun(x: dynamic): dynamic {
                        return (!truth(dynamicValue(jsonValue("[\"db\",\"gym\"]")).includes(x)))
                    })))) {
                        run {
                            val target37: dynamic = stats
                            val previous39: dynamic = numeric(target37.explicitAux)
                            target37.explicitAux = jsAdd(previous39, 1)
                        }
                    }
                    if (truth(req.includes("bench"))) {
                        run {
                            val target40: dynamic = stats
                            val previous42: dynamic = numeric(target40.requiresBench)
                            target40.requiresBench = jsAdd(previous42, 1)
                        }
                    }
                    if (truth(req.includes("step"))) {
                        run {
                            val target43: dynamic = stats
                            val previous45: dynamic = numeric(target43.requiresStep)
                            target43.requiresStep = jsAdd(previous45, 1)
                        }
                    }
                    if (truth(req.some((fun(x: dynamic): dynamic {
                        return jsAnd(gymTokens.has(x)) { (x !== "bench") }
                    })))) {
                        run {
                            val target46: dynamic = stats
                            val previous48: dynamic = numeric(target46.requiresGymGear)
                            target46.requiresGymGear = jsAdd(previous48, 1)
                        }
                    }
                }
            }
            return stats
        }
        if (truth(root.HunterMaterials3814)) {
            return absent
        }
        val own: dynamic = (fun(o: dynamic, k: dynamic): dynamic {
            return global_Object.prototype.hasOwnProperty.call(jsOr(o) { js("({})") }, k)
        })
        requiredTokens = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"db\",\"gym\",\"bench\",\"step\"]")))
        gymTokens = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"barbell\",\"cable\",\"machine\",\"smith\",\"cardio\",\"bench\"]")))
        root.HunterMaterials3814 = global_Object.freeze(jsObject { obj51 ->
            obj51["version"] = "38.14"
            obj51["requirements"] = requirements
            obj51["eligible"] = eligible
            obj51["sessionCap"] = sessionCap
            obj51["coverage"] = coverage
        })
        return absent
    }))((if (truth((js("typeof window") === "undefined"))) global_globalThis else global_window))
}
