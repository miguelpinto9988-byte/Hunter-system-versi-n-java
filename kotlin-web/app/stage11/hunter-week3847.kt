@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterweek3847

import hunter.browser.*

@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic

internal fun installHunterWeek3847() {
    dynamicValue((fun(root: dynamic): dynamic {
        var E: dynamic = absent
        var S: dynamic = absent
        var D: dynamic = absent
        var type: dynamic = absent
        var isScheduledKind: dynamic = absent
        var scheduledPlan: dynamic = absent
        type = fun(p: dynamic, arg6: dynamic): dynamic {
            var date: dynamic = absent
            date = definedOr(arg6) { E.dateKey() }
            if (truth(jsOr((!truth(p))) { (!truth(S.valid(p))) })) {
                return null
            }
            val i: dynamic = S.weekday(date)
            val day: dynamic = p.weekPlan[i]
            return (if (truth((day === "train"))) (if (truth(dynamicValue(jsonValue("[\"lower\",\"core\"]")).includes(onPresent(p.weekFocus) { value7 -> value7[i] }))) p.weekFocus[i] else "upper") else day)
        }
        isScheduledKind = fun(p: dynamic, kind: dynamic, date: dynamic): dynamic {
            val t: dynamic = type(p, date)
            return (kind === jsonValue("{\"upper\":\"strength\",\"lower\":\"strength\",\"core\":\"strength\",\"cardio\":\"cardio\",\"mobility\":\"recovery\"}")[t])
        }
        scheduledPlan = fun(p: dynamic, kind: dynamic, seed: dynamic, arg12: dynamic, arg13: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg12) { js("({})") }
            var date: dynamic = absent
            date = definedOr(arg13) { E.dateKey() }
            return D.choose(p, kind, jsAdd("week47|", seed), logs, date)
        }
        if (truth(root.HunterWeek3847)) {
            return absent
        }
        E = root.Engine
        S = root.Schedule
        D = root.HunterDynamic381
        val central: dynamic = (fun(e: dynamic): dynamic {
            return global_Boolean(jsAnd((onPresent(e) { value3 -> value3.category } === "Fuerza")) { dynamicValue(jsonValue("[\"Core\",\"Abdomen\"]")).includes(e.group) })
        })
        if (true) {
            val iterator14: dynamic = iteratorOf(arrayOf<dynamic>(root.Training30, root.Training23, S))
            while (true) {
                val next15: dynamic = iterator14.next()
                if (truth(next15.done)) break
                var `object`: dynamic = absent
                `object` = next15.value
                val name: dynamic = (if (truth((`object` === S))) "items" else "choose")
                val before: dynamic = onPresent(`object`) { value16 -> value16[name] }
                if (truth((jsTypeOf(before) !== "function"))) {
                    continue
                }
                `object`[name] = (fun(p: dynamic, kind: dynamic, arg21: dynamic): dynamic {
                    val receiver19: dynamic = js("this")
                    val callArgs20: dynamic = js("arguments")
                    var rotation: dynamic = absent
                    rotation = definedOr(arg21) { 0 }
                    if (truth(isScheduledKind(p, kind, E.dateKey()))) {
                        return scheduledPlan(p, kind, jsAdd("legacy|", rotation), (if (truth((js("typeof state") !== "undefined"))) global_state.logs else js("({})")), E.dateKey()).items
                    }
                    return before.apply(receiver19, callArgs20)
                })
            }
        }
        if (true) {
            val iterator22: dynamic = iteratorOf(arrayOf<dynamic>(arrayOf<dynamic>(root.HunterPlanner379, false), arrayOf<dynamic>(root.HunterInjuryPlanner378, true)))
            while (true) {
                val next23: dynamic = iterator22.next()
                if (truth(next23.done)) break
                var `object`: dynamic = absent
                var oldKind: dynamic = absent
                val unpacked24: dynamic = fromIterable(next23.value)
                `object` = unpacked24[0]
                oldKind = unpacked24[1]
                if (truth((jsTypeOf(onPresent(`object`) { value25 -> value25.choose }) !== "function"))) {
                    continue
                }
                val before: dynamic = `object`.choose
                `object`.choose = (fun(p: dynamic, kind: dynamic, arg30: dynamic, arg31: dynamic, arg32: dynamic): dynamic {
                    val receiver28: dynamic = js("this")
                    val callArgs29: dynamic = js("arguments")
                    var rotation: dynamic = absent
                    rotation = definedOr(arg30) { 0 }
                    var logs: dynamic = absent
                    logs = definedOr(arg31) { js("({})") }
                    var date: dynamic = absent
                    date = definedOr(arg32) { E.dateKey() }
                    if (truth((!truth(isScheduledKind(p, kind, date))))) {
                        return before.apply(receiver28, callArgs29)
                    }
                    val result: dynamic = scheduledPlan(p, kind, jsAdd("restricted|", rotation), logs, date)
                    return (if (truth(oldKind)) jsObject { obj33 ->
                        copyFields(obj33, result)
                        obj33["kind"] = jsOr(result.effective) { kind }
                        obj33["originalKind"] = kind
                    } else result)
                })
            }
        }
        val previousValidation: dynamic = global_validateBackup
        global_validateBackup = (fun(`data`: dynamic): dynamic {
            if (truth((!truth(previousValidation(`data`))))) {
                return false
            }
            return dynamicValue(jsArray { array39 ->
                array39.push(`data`.profile)
                appendAll(array39, dynamicValue(jsOr(`data`.cycles) { js("[]") }).map((fun(c: dynamic): dynamic {
                    return c.profile
                })))
            }).filter(global_Boolean).every((fun(p: dynamic): dynamic {
                return jsOr((p.weekFocus === absent)) { jsAnd(jsAnd(global_Array.isArray(p.weekFocus)) { (p.weekFocus.length === 7) }) { p.weekFocus.every((fun(x: dynamic): dynamic {
                    return dynamicValue(jsonValue("[\"none\",\"upper\",\"lower\",\"core\"]")).includes(x)
                })) } }
            }))
        })
        root.HunterWeek3847 = global_Object.freeze(jsObject { obj42 ->
            obj42["version"] = "38.47"
            obj42["type"] = type
            obj42["isCentral"] = central
            obj42["pool"] = (fun(p: dynamic): dynamic {
                return D.pool(p, "strength").filter(central)
            })
            obj42["choices"] = jsonValue("[\"rest\",\"upper\",\"lower\",\"core\",\"cardio\",\"mobility\"]")
        })
        return absent
    }))(global_window)
}
