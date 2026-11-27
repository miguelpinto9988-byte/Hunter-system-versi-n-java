@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterprogression3815

import hunter.browser.*

@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installHunterProgression3815() {
    dynamicValue((fun(root: dynamic): dynamic {
        var num: dynamic = absent
        var validDay: dynamic = absent
        var previousDay: dynamic = absent
        var records: dynamic = absent
        var restrictions: dynamic = absent
        var dose: dynamic = absent
        var cycleReport: dynamic = absent
        records = fun(logs: dynamic, date: dynamic, arg5: dynamic): dynamic {
            var days: dynamic = absent
            days = definedOr(arg5) { 14 }
            val min: dynamic = previousDay(date, days)
            val sessions: dynamic = js("[]")
            if (true) {
                val iterator6: dynamic = iteratorOf(global_Object.entries(jsOr(logs) { js("({})") }))
                while (true) {
                    val next7: dynamic = iterator6.next()
                    if (truth(next7.done)) break
                    var key: dynamic = absent
                    var l: dynamic = absent
                    val unpacked8: dynamic = fromIterable(next7.value)
                    key = unpacked8[0]
                    l = unpacked8[1]
                    if (truth(jsAnd(jsAnd(jsAnd(jsAnd(validDay(key)) { jsLess(key, date) }) { jsAtLeast(key, min) }) { l }) { (jsTypeOf(l) === "object") })) {
                        sessions.push(l)
                    }
                }
            }
            val done: dynamic = sessions.filter((fun(l: dynamic): dynamic {
                return jsAnd(jsAnd(jsAnd((l.complete === true)) { (!truth(l.manual)) }) { (!truth(l.pain)) }) { jsMore(num(l.rpe, 0, 10), 0) }
            }))
            val efforts: dynamic = done.map((fun(l: dynamic): dynamic {
                return num(l.rpe, 0, 10)
            }))
            val average: dynamic = (if (truth(efforts.length)) jsDivide(efforts.reduce((fun(a: dynamic, b: dynamic): dynamic {
                return jsAdd(a, b)
            }), 0), efforts.length) else 0)
            return jsObject { obj9 ->
                obj9["observed"] = sessions.length
                obj9["completed"] = done.length
                obj9["average"] = average
                obj9["symptoms"] = sessions.some((fun(l: dynamic): dynamic {
                    return jsOr(jsOr((!truth((!truth(l.pain))))) { (!truth((!truth(l.fatigue)))) }) { jsAtLeast(num(l.rpe, 0, 10), 8) }
                }))
                obj9["adequate"] = jsAnd(jsAnd(jsAnd(jsAtLeast(done.length, 2)) { jsMore(average, 0) }) { jsAtMost(average, 7) }) { (!truth(sessions.some((fun(l: dynamic): dynamic {
                    return jsOr((!truth((!truth(l.pain))))) { (!truth((!truth(l.fatigue)))) }
                })))) }
            }
        }
        restrictions = fun(p: dynamic, status: dynamic): dynamic {
            return (!truth((!truth(jsOr(jsOr(jsOr(jsOr(jsOr(onPresent(status) { value12 -> value12.reduced }) { onPresent(p) { value13 -> value13.limits } }) { onPresent(p) { value14 -> onPresent(value14.health) { value15 -> value15.special } } }) { onPresent(p) { value16 -> onPresent(value16.health) { value17 -> value17.acute } } }) { jsAnd(onPresent(p) { value18 -> onPresent(value18.health) { value19 -> value19.pain } }) { (p.health.pain !== "none") } }) { dynamicValue(jsOr(onPresent(p) { value21 -> value21.injuries }) { js("[]") }).some((fun(i: dynamic): dynamic {
                return jsAnd(i) { (i.stage !== "past") }
            })) }))))
        }
        dose = fun(ex: dynamic, p: dynamic, kind: dynamic, status: dynamic, feedback: dynamic, date: dynamic, current: dynamic, arg24: dynamic, arg25: dynamic): dynamic {
            var weekIndex: dynamic = absent
            weekIndex = definedOr(arg24) { 0 }
            var logs: dynamic = absent
            logs = definedOr(arg25) { js("({})") }
            val `out`: dynamic = jsObject { obj26 ->
                copyFields(obj26, current)
            }
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(`out`))) { (!truth(ex)) }) { (!truth(p)) }) { (!truth(global_Number.isInteger(weekIndex))) }) { jsLess(weekIndex, 1) }) { jsMore(weekIndex, 2) })) {
                return `out`
            }
            if (truth(jsOr(jsOr(restrictions(p, status)) { onPresent(feedback) { value27 -> value27.strain } }) { (onPresent(feedback) { value28 -> value28.canProgress } !== true) })) {
                return `out`
            }
            if (truth(jsOr(jsOr(`out`.noAutoLoad) { jsLess(`out`.sets, 1) }) { jsLess(`out`.target, 1) })) {
                return `out`
            }
            val evidence: dynamic = records(logs, date)
            if (truth(jsOr((!truth(evidence.adequate))) { evidence.symptoms })) {
                return `out`
            }
            if (truth(jsAnd((kind !== "strength")) { (kind !== "cardio") })) {
                return `out`
            }
            if (truth((!truth(dynamicValue(jsonValue("[\"strength\",\"muscle\",\"endurance\",\"athletic\"]")).includes(p.goal))))) {
                return `out`
            }
            val unit: dynamic = (if (truth((ex.unit === "sec"))) "sec" else "reps")
            val cap: dynamic = (if (truth((unit === "sec"))) global_Math.min(60, jsAdd(num(`out`.target, 1, 90), 5)) else global_Math.min(16, jsAdd(num(`out`.target, 1, 30), 1)))
            `out`.target = cap
            `out`.progression3815 = jsObject { obj33 ->
                obj33["week"] = jsAdd(weekIndex, 1)
                obj33["reason"] = "Dos o más sesiones anteriores con esfuerzo registrado y sin molestias."
                obj33["evidence"] = evidence.completed
            }
            return `out`
        }
        cycleReport = fun(cycle: dynamic, arg36: dynamic): dynamic {
            var logs: dynamic = absent
            logs = definedOr(arg36) { js("({})") }
            val weeks: dynamic = js("[]")
            if (true) {
                var w: dynamic = absent
                w = 0
                var firstIteration37 = true
                while (true) {
                    if (!firstIteration37) {
                        run {
                            val previous38: dynamic = numeric(w)
                            w = jsAdd(previous38, 1)
                        }
                    }
                    firstIteration37 = false
                    if (!truth(jsLess(w, 3))) break
                    val slice: dynamic = dynamicValue(jsOr(onPresent(cycle) { value40 -> value40.days }) { js("[]") }).slice(jsMultiply(w, 7), jsMultiply(jsAdd(w, 1), 7))
                    var scheduled: dynamic = 0
                    var done: dynamic = 0
                    var rest: dynamic = 0
                    var protectedDays: dynamic = 0
                    if (true) {
                        val iterator41: dynamic = iteratorOf(slice)
                        while (true) {
                            val next42: dynamic = iterator41.next()
                            if (truth(next42.done)) break
                            var d: dynamic = absent
                            d = next42.value
                            if (truth((!truth(d)))) {
                                continue
                            }
                            if (truth((d.scheduleChoice === "rest"))) {
                                run {
                                    val previous43: dynamic = numeric(rest)
                                    rest = jsAdd(previous43, 1)
                                }
                            } else {
                                if (truth(d.scheduleChoice)) {
                                    run {
                                        val previous44: dynamic = numeric(scheduled)
                                        scheduled = jsAdd(previous44, 1)
                                    }
                                    if (truth((onPresent(logs[d.date]) { value45 -> value45.complete } === true))) {
                                        run {
                                            val previous46: dynamic = numeric(done)
                                            done = jsAdd(previous46, 1)
                                        }
                                    }
                                }
                            }
                            if (truth(jsOr(jsOr(d.customized) { d.manual }) { onPresent(logs[d.date]) { value47 -> value47.complete } })) {
                                run {
                                    val previous48: dynamic = numeric(protectedDays)
                                    protectedDays = jsAdd(previous48, 1)
                                }
                            }
                        }
                    }
                    weeks.push(jsObject { obj49 ->
                        obj49["number"] = jsAdd(w, 1)
                        obj49["scheduled"] = scheduled
                        obj49["done"] = done
                        obj49["rest"] = rest
                        obj49["protectedDays"] = protectedDays
                    })
                }
            }
            return weeks
        }
        if (truth(root.HunterProgress3815)) {
            return absent
        }
        num = (fun(v: dynamic, lo: dynamic, hi: dynamic): dynamic {
            val n: dynamic = global_Number(v)
            return (if (truth(global_Number.isFinite(n))) global_Math.max(lo, global_Math.min(hi, n)) else 0)
        })
        validDay = (fun(k: dynamic): dynamic {
            return dynamicValue(jsRegExp("^\\d{4}-\\d{2}-\\d{2}\$", "")).test(k)
        })
        previousDay = (fun(iso: dynamic, n: dynamic): dynamic {
            val d: dynamic = jsConstruct(global_Date, arrayOf<dynamic>(jsAdd(iso, "T12:00:00Z")))
            if (truth((!truth(global_Number.isFinite(d.getTime()))))) {
                return ""
            }
            d.setUTCDate(jsSubtract(d.getUTCDate(), n))
            return d.toISOString().slice(0, 10)
        })
        root.HunterProgress3815 = global_Object.freeze(jsObject { obj52 ->
            obj52["version"] = "38.15"
            obj52["dose"] = dose
            obj52["records"] = records
            obj52["cycleReport"] = cycleReport
        })
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
