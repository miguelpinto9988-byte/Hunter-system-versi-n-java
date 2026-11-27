@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installcreatorfeed

import hunter.browser.*

@JsName("Number") private external var global_Number: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Error") private external var global_Error: dynamic
@JsName("Set") private external var global_Set: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("Weekly") private external var global_Weekly: dynamic
@JsName("Learning") private external var global_Learning: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installCreatorFeed() {
    dynamicValue((fun(root: dynamic): dynamic {
        var channels: dynamic = absent
        var integer: dynamic = absent
        var chapters: dynamic = absent
        var video: dynamic = absent
        var normalize: dynamic = absent
        var merge: dynamic = absent
        var validateRoutine: dynamic = absent
        var asRoutine: dynamic = absent
        var assignWeek: dynamic = absent
        chapters = fun(description: dynamic): dynamic {
            val `out`: dynamic = js("[]")
            var previous: dynamic = (-numeric(1))
            if (true) {
                val iterator5: dynamic = iteratorOf(global_String(jsOr(description) { "" }).split("\n"))
                while (true) {
                    val next6: dynamic = iterator5.next()
                    if (truth(next6.done)) break
                    var line: dynamic = absent
                    line = next6.value
                    val m: dynamic = line.trim().match(jsRegExp("^(?:(\\d{1,2}):)?(\\d{1,2}):(\\d{2})\\s+(.+)\$", ""))
                    if (truth(jsOr(jsOr((!truth(m))) { jsAtLeast(numeric(m[3]), 60) }) { jsAnd(m[1]) { jsAtLeast(numeric(m[2]), 60) } })) {
                        continue
                    }
                    val at: dynamic = jsAdd(jsAdd(jsMultiply(global_Number(jsOr(m[1]) { 0 }), 3600), jsMultiply(global_Number(m[2]), 60)), global_Number(m[3]))
                    if (truth(jsOr(jsAtMost(at, previous)) { jsMore(at, 86400) })) {
                        continue
                    }
                    previous = at
                    `out`.push(jsObject { obj7 ->
                        obj7["at"] = at
                        obj7["label"] = m[4].trim().slice(0, 160)
                    })
                    if (truth(jsAtLeast(`out`.length, 150))) {
                        break
                    }
                }
            }
            return `out`
        }
        video = fun(v: dynamic, channelId: dynamic): dynamic {
            if (truth(jsOr(jsOr(jsOr((!truth(v))) { (!truth(dynamicValue(jsRegExp("^[-\\w]{11}\$", "")).test(v.id))) }) { (jsTypeOf(v.title) !== "string") }) { (!truth(v.title.trim())) })) {
                return null
            }
            val parsed: dynamic = global_Date.parse(v.published)
            val marks: dynamic = (if (truth(global_Array.isArray(v.chapters))) v.chapters.filter((fun(x: dynamic): dynamic {
                return jsAnd(jsAnd(x) { integer(x.at, 0, 86400) }) { (jsTypeOf(x.label) === "string") }
            })).map((fun(x: dynamic): dynamic {
                return jsObject { obj11 ->
                    obj11["at"] = x.at
                    obj11["label"] = x.label.slice(0, 160)
                }
            })).slice(0, 150) else chapters(v.description))
            return jsObject { obj12 ->
                obj12["id"] = v.id
                obj12["channelId"] = channelId
                obj12["title"] = v.title.slice(0, 160)
                obj12["published"] = (if (truth(global_Number.isFinite(parsed))) jsConstruct(global_Date, arrayOf<dynamic>(parsed)).toISOString() else "")
                obj12["chapters"] = marks
                obj12["url"] = jsAdd("https://www.youtube.com/watch?v=", v.id)
            }
        }
        normalize = fun(raw: dynamic): dynamic {
            val result: dynamic = jsObject { obj15 ->
                obj15["updated"] = (if (truth(global_Number.isFinite(onPresent(raw) { value16 -> value16.updated }))) raw.updated else 0)
                obj15["channels"] = js("[]")
            }
            val input: dynamic = (if (truth(global_Array.isArray(onPresent(raw) { value17 -> value17.channels }))) raw.channels else js("[]"))
            if (true) {
                val iterator18: dynamic = iteratorOf(channels)
                while (true) {
                    val next19: dynamic = iterator18.next()
                    if (truth(next19.done)) break
                    var c: dynamic = absent
                    c = next19.value
                    val source: dynamic = jsOr(input.find((fun(x: dynamic): dynamic {
                        return jsAnd(x) { (x.channelId === c.channelId) }
                    }))) { js("({})") }
                    val videos: dynamic = dynamicValue((if (truth(global_Array.isArray(source.videos))) source.videos else js("[]"))).slice(0, 150).map((fun(v: dynamic): dynamic {
                        return video(v, c.channelId)
                    })).filter(global_Boolean)
                    result.channels.push(jsObject { obj20 ->
                        copyFields(obj20, c)
                        obj20["status"] = (if (truth((source.status === "ok"))) "ok" else "unavailable")
                        obj20["lastSuccess"] = (if (truth(global_Number.isFinite(global_Number(source.lastSuccess)))) global_Number(source.lastSuccess) else 0)
                        obj20["videos"] = dynamicValue(jsArray { array24 ->
                            appendAll(array24, jsConstruct(global_Map, arrayOf<dynamic>(videos.map((fun(x: dynamic): dynamic {
                                return arrayOf<dynamic>(x.id, x)
                            })))).values())
                        }).sort((fun(a: dynamic, b: dynamic): dynamic {
                            return b.published.localeCompare(a.published)
                        })).slice(0, 100)
                    })
                }
            }
            return result
        }
        merge = fun(old: dynamic, raw: dynamic): dynamic {
            val next: dynamic = normalize(raw)
            val prior: dynamic = normalize(old)
            if (true) {
                val iterator27: dynamic = iteratorOf(next.channels)
                while (true) {
                    val next28: dynamic = iterator27.next()
                    if (truth(next28.done)) break
                    var c: dynamic = absent
                    c = next28.value
                    val before: dynamic = prior.channels.find((fun(x: dynamic): dynamic {
                        return (x.channelId === c.channelId)
                    }))
                    c.videos = dynamicValue(jsArray { array34 ->
                        appendAll(array34, c.videos)
                        appendAll(array34, before.videos.filter((fun(x: dynamic): dynamic {
                            return (!truth(c.videos.some((fun(v: dynamic): dynamic {
                                return (v.id === x.id)
                            }))))
                        })))
                    }).sort((fun(a: dynamic, b: dynamic): dynamic {
                        return b.published.localeCompare(a.published)
                    })).slice(0, 100)
                    if (truth((c.status !== "ok"))) {
                        c.lastSuccess = before.lastSuccess
                    }
                }
            }
            return next
        }
        validateRoutine = fun(r: dynamic): dynamic {
            if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr(jsOr((!truth(r))) { (!truth(dynamicValue(jsRegExp("^[-\\w]{11}\$", "")).test(r.videoId))) }) { (!truth(channels.some((fun(c: dynamic): dynamic {
                return (c.channelId === r.channelId)
            })))) }) { (jsTypeOf(r.name) !== "string") }) { jsMore(r.name.length, 100) }) { (!truth(r.name.trim())) }) { (r.verified !== true) }) { (jsTypeOf(r.verificationNote) !== "string") }) { (!truth(r.verificationNote.trim())) }) { jsMore(r.verificationNote.length, 500) }) { (!truth(global_Array.isArray(r.steps))) }) { jsLess(r.steps.length, 1) }) { jsMore(r.steps.length, 320) })) {
                return false
            }
            return jsAnd(jsAnd(r.steps.every((fun(s: dynamic): dynamic {
                return jsAnd(jsAnd(s) { dynamicValue(jsonValue("[\"exercise\",\"rest\",\"preparation\",\"cooldown\"]")).includes(s.kind) }) { (if (truth((s.kind === "exercise"))) jsAnd(jsAnd(jsAnd(jsAnd(jsAnd((!truth((!truth(global_Engine.byId(s.exerciseId)))))) { integer(s.sets, 1, 4) }) { jsOr(jsOr((s.unit === absent)) { (s.unit === "sec") }) { (s.unit === global_Engine.byId(s.exerciseId).unit) } }) { jsOr((s.side === absent)) { dynamicValue(jsonValue("[\"left\",\"right\",\"both\"]")).includes(s.side) } }) { integer(s.target, 1, (if (truth((jsOr(s.unit) { global_Engine.byId(s.exerciseId).unit } === "sec"))) 3600 else 300)) }) { integer(s.restSeconds, 0, 600) } else jsAnd(jsAnd(integer(s.seconds, 1, 3600)) { (jsTypeOf(s.label) === "string") }) { jsAtMost(s.label.length, 160) }) }
            }))) { r.steps.some((fun(s: dynamic): dynamic {
                return (s.kind === "exercise")
            })) }) { jsAtMost(r.steps.filter((fun(s: dynamic): dynamic {
                return (s.kind === "exercise")
            })).length, 96) }
        }
        asRoutine = fun(r: dynamic): dynamic {
            if (truth((!truth(validateRoutine(r))))) {
                throw global_Error("Secuencia incompleta o no verificada")
            }
            val items: dynamic = r.steps.filter((fun(s: dynamic): dynamic {
                return (s.kind === "exercise")
            })).map((fun(s: dynamic): dynamic {
                return jsObject { obj41 ->
                    obj41["id"] = s.exerciseId
                    obj41["sets"] = s.sets
                    obj41["target"] = s.target
                    copyFields(obj41, (if (truth(s.unit)) jsObject { obj42 ->
                        obj42["unit"] = s.unit
                    } else js("({})")))
                    copyFields(obj41, (if (truth(s.side)) jsObject { obj43 ->
                        obj43["side"] = s.side
                        obj43["missionTitle"] = jsAdd(global_Engine.byId(s.exerciseId).name, (if (truth((s.side === "left"))) " · lado izquierdo" else (if (truth((s.side === "right"))) " · lado derecho" else " · ambos lados")))
                    } else js("({})")))
                    obj41["rest"] = s.restSeconds
                }
            }))
            val categories: dynamic = jsArray { array44 ->
                appendAll(array44, jsConstruct(global_Set, arrayOf<dynamic>(items.map((fun(x: dynamic): dynamic {
                    return global_Engine.byId(x.id).category
                })))))
            }
            return jsObject { obj45 ->
                obj45["id"] = jsAdd("creator", r.videoId)
                obj45["name"] = r.name
                obj45["description"] = (if (truth((r.evidenceType === "captions-and-chapters"))) "Secuencia textual reconstruida de subtítulos y capítulos. Revisión visual pendiente. Se conservan cantidades y pausas documentadas." else "Secuencia declarada como verificada. Se conserva el orden, las cantidades y los descansos.")
                obj45["items"] = items
                obj45["category"] = (if (truth((categories.length === 1))) categories[0] else "Mixta")
                obj45["requirements"] = jsArray { array46 ->
                    appendAll(array46, jsConstruct(global_Set, arrayOf<dynamic>(items.flatMap((fun(x: dynamic): dynamic {
                        return global_Engine.byId(x.id).requirements
                    })))))
                }
                obj45["level"] = run { val target48: dynamic = global_Math; invokeWith(target48.max, target48, jsArray { array47 ->
                    appendAll(array47, items.map((fun(x: dynamic): dynamic {
                        return global_Engine.byId(x.id).level
                    })))
                }) }
                obj45["minutes"] = global_Math.ceil(jsDivide(r.steps.reduce((fun(n: dynamic, s: dynamic): dynamic {
                    return jsAdd(n, jsOr(s.seconds) { jsMultiply(s.sets, jsAdd(jsMultiply(s.target, (if (truth((jsOr(s.unit) { global_Engine.byId(s.exerciseId).unit } === "sec"))) 1 else 4)), s.restSeconds)) })
                }), 0), 60))
                obj45["impact"] = (if (truth(items.some((fun(x: dynamic): dynamic {
                    return (global_Engine.byId(x.id).impact === "Con saltos")
                })))) "Con saltos" else "Sin saltos")
                obj45["sourceSequence"] = global_JSON.parse(global_JSON.stringify(r))
                obj45["locked"] = true
            }
        }
        assignWeek = fun(c: dynamic, p: dynamic, raw: dynamic, logs: dynamic, now: dynamic): dynamic {
            val mode: dynamic = global_Adapt.mode(p)
            if (truth(jsOr(jsOr((!truth(mode.auto))) { mode.reduced }) { dynamicValue(jsonValue("[\"reducir\",\"pausar\"]")).includes(onPresent(c.reviews) { value51 -> onPresent(value51.at((-numeric(1)))) { value52 -> value52.action } }) })) {
                return 0
            }
            val routines: dynamic = raw.filter(validateRoutine).map(asRoutine).filter((fun(r: dynamic): dynamic {
                return global_Adapt.routineCompatible(r, p)
            }))
            var count: dynamic = 0
            if (true) {
                val iterator53: dynamic = iteratorOf(c.days.entries())
                while (true) {
                    val next54: dynamic = iterator53.next()
                    if (truth(next54.done)) break
                    var i: dynamic = absent
                    var d: dynamic = absent
                    val unpacked55: dynamic = fromIterable(next54.value)
                    i = unpacked55[0]
                    d = unpacked55[1]
                    if (truth(jsOr(jsOr(jsOr(jsOr(jsOr(jsLess(d.date, now)) { (!truth(d.items.length)) }) { d.customized }) { d.routineId }) { global_Weekly.active(logs[d.date]) }) { onPresent(d.tasks) { value56 -> value56.some((fun(t: dynamic): dynamic {
                        return t.done
                    })) } })) {
                        continue
                    }
                    val category: dynamic = (if (truth((d.kind === "cardio"))) "Cardio" else (if (truth((d.kind === "recovery"))) "Movilidad" else "Fuerza"))
                    val candidates: dynamic = routines.filter((fun(r: dynamic): dynamic {
                        return jsOr((r.category === category)) { jsAnd((category === "Fuerza")) { (r.category === "Mixta") } }
                    })).map((fun(r: dynamic): dynamic {
                        return jsObject { obj60 ->
                            obj60["r"] = r
                            obj60["prediction"] = global_Learning.predict(global_Learning.samples(logs, now), r.items)
                        }
                    })).filter((fun(x: dynamic): dynamic {
                        return jsOr((!truth(x.prediction))) { jsLess(x.prediction.effort, 8) }
                    })).sort((fun(a: dynamic, b: dynamic): dynamic {
                        return jsOr(jsSubtract((if (truth(a.prediction)) global_Math.abs(jsSubtract(a.prediction.effort, 6)) else 2), (if (truth(b.prediction)) global_Math.abs(jsSubtract(b.prediction.effort, 6)) else 2))) { jsSubtract(a.r.level, b.r.level) }
                    }))
                    if (truth((!truth(candidates.length)))) {
                        continue
                    }
                    val r: dynamic = candidates[jsMod(jsAdd(c.index, i), global_Math.min(3, candidates.length))].r
                    d.items = global_JSON.parse(global_JSON.stringify(r.items))
                    d.title = r.name
                    d.kind = (if (truth((r.category === "Mixta"))) "mixed" else d.kind)
                    d.routineId = r.id
                    d.sourceSequence = global_JSON.parse(global_JSON.stringify(r.sourceSequence))
                    d.tasks = js("[]")
                    d.creatorAssigned = true
                    run {
                        val previous75: dynamic = numeric(count)
                        count = jsAdd(previous75, 1)
                    }
                }
            }
            return count
        }
        channels = jsonValue("[{\"key\":\"sergio\",\"name\":\"Entrena con Sergio Peinado\",\"channelId\":\"UC9l7xbkF_n-WP61f74AjKvw\",\"url\":\"https://www.youtube.com/@EntrenaSergioPeinado\"},{\"key\":\"pau\",\"name\":\"Pau Fit\",\"channelId\":\"UCQ_N_uuyHbTE8qZ_GRIJmKw\",\"url\":\"https://www.youtube.com/@PauFitYT\"},{\"key\":\"naty\",\"name\":\"NatyGloss Gym\",\"channelId\":\"UCUqMVnhjUZyW-K5r5AJglNA\",\"url\":\"https://www.youtube.com/@NatyGlossGym\"},{\"key\":\"metal\",\"name\":\"Metal Pump\",\"channelId\":\"UCraP6R9xSfBClJbQJ_VNU0Q\",\"url\":\"https://www.youtube.com/@metal-pump\"},{\"key\":\"chuy\",\"name\":\"Chuy Almada\",\"channelId\":\"UC6Z3joUwYfM-Zr1U3bZLztQ\",\"url\":\"https://www.youtube.com/channel/UC6Z3joUwYfM-Zr1U3bZLztQ\"}]")
        integer = (fun(n: dynamic, min: dynamic, max: dynamic): dynamic {
            return jsAnd(jsAnd(global_Number.isInteger(n)) { jsAtLeast(n, min) }) { jsAtMost(n, max) }
        })
        root.CreatorFeed = jsObject { obj78 ->
            obj78["channels"] = channels
            obj78["chapters"] = chapters
            obj78["video"] = video
            obj78["normalize"] = normalize
            obj78["merge"] = merge
            obj78["validateRoutine"] = validateRoutine
            obj78["asRoutine"] = asRoutine
            obj78["assignWeek"] = assignWeek
        }
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
