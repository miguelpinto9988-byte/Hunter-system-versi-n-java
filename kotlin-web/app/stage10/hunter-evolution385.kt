@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterevolution385

import hunter.browser.*

@JsName("Set") private external var global_Set: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Engine") private external var global_Engine: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterEvolution385() {
    dynamicValue((fun(w: dynamic, d: dynamic): dynamic {
        var STORAGE: dynamic = absent
        var LEVELS: dynamic = absent
        var MODES: dynamic = absent
        var selected: dynamic = absent
        var appObserver: dynamic = absent
        var updateScheduled: dynamic = absent
        var lastRank: dynamic = absent
        var lastAction: dynamic = absent
        var animations: dynamic = absent
        var rankTransitions: dynamic = absent
        var lastKnownCompleted: dynamic = absent
        var prefersReduced: dynamic = absent
        var now: dynamic = absent
        var nativeRestricted: dynamic = absent
        var decideBudget: dynamic = absent
        var readRank: dynamic = absent
        var completedCount: dynamic = absent
        var pulse: dynamic = absent
        var setRank: dynamic = absent
        var setMode: dynamic = absent
        var profilePreference: dynamic = absent
        var refresh: dynamic = absent
        var queueRefresh: dynamic = absent
        var `init`: dynamic = absent
        nativeRestricted = fun(): dynamic {
            val runtime: dynamic = onPresent(w.HunterV373) { value9 -> value9.runtime }
            return (!truth((!truth(jsOr(jsOr(jsOr(d.hidden) { prefersReduced() }) { onPresent(runtime) { value10 -> value10.saver } }) { jsAtLeast(onPresent(runtime) { value11 -> value11.thermal }, 2) }))))
        }
        decideBudget = fun(): dynamic {
            if (truth(jsOr(jsOr(nativeRestricted()) { (selected === "ahorro") }) { (onPresent(w.HunterAdaptive3823) { value14 -> value14.quality } === "eco") })) {
                return "eco"
            }
            val estimated: dynamic = global_Number(onPresent(w.HunterPerf380) { value15 -> value15.callbackHz })
            val cost: dynamic = global_Number(onPresent(w.HunterPerf380) { value16 -> value16.drawMs })
            if (truth(jsOr(jsAnd(jsMore(estimated, 0)) { jsLess(estimated, 49) }) { jsMore(cost, 3.8) })) {
                return "eco"
            }
            if (truth(jsAnd((selected === "fluido")) { jsOr((estimated === 0)) { jsAtLeast(estimated, 59) } })) {
                return "alto"
            }
            return "medio"
        }
        readRank = fun(): dynamic {
            try {
                if (truth(jsAnd(jsAnd(jsAnd((js("typeof state") !== "undefined")) { global_state }) { (js("typeof Engine") !== "undefined") }) { onPresent(global_Engine) { value19 -> value19.stats } })) {
                    val level: dynamic = jsOr(global_Number(global_Engine.stats(jsOr(global_state.logs) { js("({})") }).level)) { 1 }
                    return LEVELS[global_Math.max(0, global_Math.min(5, global_Math.floor(jsDivide(jsSubtract(level, 1), 5))))]
                }
            } catch (_: dynamic) {

            }
            val badge: dynamic = d.querySelector(".header-rank26 b, .rank-pill, .header-rank26")
            val match: dynamic = onPresent(badge) { value20 -> onPresent(value20.textContent) { value21 -> value21.match(jsRegExp("\\b([EDCBAS])\\b", "")) } }
            return (if (truth(match)) match[1] else jsOr(lastRank) { "E" })
        }
        completedCount = fun(): dynamic {
            try {
                if (truth(jsAnd(jsAnd((js("typeof state") !== "undefined")) { global_state }) { global_state.logs })) {
                    return global_Object.values(global_state.logs).filter((fun(x: dynamic): dynamic {
                        return jsAnd(x) { (x.complete === true) }
                    })).length
                }
            } catch (_: dynamic) {

            }
            return (-numeric(1))
        }
        pulse = fun(target: dynamic, kind: dynamic): dynamic {
            if (truth(jsOr(jsOr((!truth(target))) { (!truth(target.isConnected)) }) { nativeRestricted() })) {
                return absent
            }
            if (truth((decideBudget() === "eco"))) {
                return absent
            }
            if (truth(jsLess(jsSubtract(now(), lastAction), 130))) {
                return absent
            }
            lastAction = now()
            val cls: dynamic = (if (truth((kind === "rank"))) "hunter-rank-burst385" else (if (truth((kind === "mission"))) "hunter-mission-burst385" else "hunter-ui-burst385"))
            target.classList.remove(cls)
            run { target.offsetWidth; absent }
            target.classList.add(cls)
            run {
                val previous26: dynamic = numeric(animations)
                animations = jsAdd(previous26, 1)
            }
            w.setTimeout((fun(): dynamic {
                if (truth(target.isConnected)) {
                    target.classList.remove(cls)
                }
                return absent
            }), (if (truth((kind === "rank"))) 1050 else 520))
            return absent
        }
        setRank = fun(): dynamic {
            val rank: dynamic = readRank()
            d.documentElement.dataset.hunterRank385 = rank
            if (truth(jsAnd(lastRank) { jsMore(LEVELS.indexOf(rank), LEVELS.indexOf(lastRank)) })) {
                run {
                    val previous31: dynamic = numeric(rankTransitions)
                    rankTransitions = jsAdd(previous31, 1)
                }
                pulse(d.querySelector(".hero, .command-hero, .header-rank26"), "rank")
            }
            lastRank = rank
            return absent
        }
        setMode = fun(mode: dynamic): dynamic {
            if (truth((!truth(MODES.has(mode))))) {
                return false
            }
            selected = mode
            try {
                w.localStorage.setItem(STORAGE, mode)
            } catch (_: dynamic) {

            }
            refresh()
            return true
        }
        profilePreference = fun(): dynamic {
            val form: dynamic = d.querySelector("#profileForm")
            if (truth(jsOr((!truth(form))) { form.querySelector("#hunterPowerMode385") })) {
                return absent
            }
            val submit: dynamic = form.querySelector("button[type=\"submit\"], input[type=\"submit\"]")
            if (truth((!truth(submit)))) {
                return absent
            }
            val `field`: dynamic = d.createElement("div")
            `field`.className = "field hunter-mode-field385"
            val label: dynamic = d.createElement("label")
            label.className = "label"
            label.htmlFor = "hunterPowerMode385"
            label.textContent = "Rendimiento visual"
            val select: dynamic = d.createElement("select")
            select.id = "hunterPowerMode385"
            select.setAttribute("aria-label", "Rendimiento visual")
            dynamicValue(jsonValue("[[\"equilibrado\",\"Equilibrado\"],[\"fluido\",\"Máxima fluidez\"],[\"ahorro\",\"Ahorro de energía\"]]")).forEach((fun(arg46: dynamic): dynamic {
                var id: dynamic = absent
                var title: dynamic = absent
                val unpacked47: dynamic = fromIterable(arg46)
                id = unpacked47[0]
                title = unpacked47[1]
                val option: dynamic = d.createElement("option")
                option["value"] = id
                option.textContent = title
                select.append(option)
                return absent
            }))
            select["value"] = selected
            select.addEventListener("change", (fun(): dynamic {
                return setMode(select["value"])
            }), jsonValue("{\"passive\":true}"))
            `field`.append(label, select)
            submit.before(`field`)
            return absent
        }
        refresh = fun(): dynamic {
            val html: dynamic = d.documentElement
            html.dataset.hunterFx385 = decideBudget()
            html.dataset.hunterMode385 = selected
            html.classList.toggle("hunter-reduced385", prefersReduced())
            html.classList.toggle("hunter-hidden385", (!truth((!truth(d.hidden)))))
            setRank()
            profilePreference()
            val cnt: dynamic = completedCount()
            if (truth(jsAnd(jsAtLeast(lastKnownCompleted, 0)) { jsMore(cnt, lastKnownCompleted) })) {
                pulse(d.querySelector(".hero, .command-hero, .mission-launch"), "mission")
            }
            if (truth(jsAtLeast(cnt, 0))) {
                lastKnownCompleted = cnt
            }
            return absent
        }
        queueRefresh = fun(): dynamic {
            if (truth(updateScheduled)) {
                return absent
            }
            updateScheduled = true
            val `run`: dynamic = (fun(): dynamic {
                updateScheduled = false
                refresh()
                return absent
            })
            if (truth(d.hidden)) {
                w.setTimeout(`run`, 180)
            } else {
                w.requestAnimationFrame(`run`)
            }
            return absent
        }
        `init` = fun(): dynamic {
            refresh()
            val app: dynamic = d.getElementById("app")
            if (truth(jsAnd(app) { (jsTypeOf(w.MutationObserver) === "function") })) {
                appObserver = jsConstruct(w.MutationObserver, arrayOf<dynamic>(queueRefresh))
                appObserver.observe(app, jsonValue("{\"childList\":true}"))
            }
            d.addEventListener("click", (fun(evt: dynamic): dynamic {
                val btn: dynamic = onPresent(evt.target) { value64 -> run { val owner66: dynamic = value64; onPresent(owner66.closest) { method65 -> invokeWith(method65, owner66, arrayOf<dynamic>("button")) } } }
                if (truth(jsOr((!truth(btn))) { (!truth(btn.isConnected)) })) {
                    return absent
                }
                if (truth(btn.matches(".btn.primary, .nav button, .day"))) {
                    pulse(btn, "ui")
                }
                queueRefresh()
                return absent
            }), jsonValue("{\"passive\":true}"))
            d.addEventListener("visibilitychange", refresh, jsonValue("{\"passive\":true}"))
            w.addEventListener("pageshow", queueRefresh, jsonValue("{\"passive\":true}"))
            w.addEventListener("pagehide", (fun(): dynamic {
                d.documentElement.classList.add("hunter-hidden385")
                return absent
            }), jsonValue("{\"passive\":true}"))
            if (truth(w.matchMedia)) {
                try {
                    w.matchMedia("(prefers-reduced-motion: reduce)").addEventListener("change", refresh)
                } catch (_: dynamic) {

                }
            }
            return absent
        }
        if (truth(w.HunterEvolution385)) {
            return absent
        }
        STORAGE = "hunter.visual.performance.v385"
        LEVELS = jsonValue("[\"E\",\"D\",\"C\",\"B\",\"A\",\"S\"]")
        MODES = jsConstruct(global_Set, arrayOf<dynamic>(jsonValue("[\"equilibrado\",\"fluido\",\"ahorro\"]")))
        selected = "equilibrado"
        appObserver = dynamicValue(null)
        updateScheduled = false
        lastRank = dynamicValue(null)
        lastAction = 0
        animations = 0
        rankTransitions = 0
        val ignoredInputs: dynamic = 0
        lastKnownCompleted = (-numeric(1))
        prefersReduced = (fun(): dynamic {
            return global_Boolean(run { val owner4: dynamic = w; onPresent(owner4.matchMedia) { method3 -> invokeWith(method3, owner4, arrayOf<dynamic>("(prefers-reduced-motion: reduce)")).matches } })
        })
        now = (fun(): dynamic {
            return (if (truth(onPresent(w.performance) { value6 -> value6.now })) w.performance.now() else global_Date.now())
        })
        try {
            val choice: dynamic = w.localStorage.getItem(STORAGE)
            if (truth(MODES.has(choice))) {
                selected = choice
            }
        } catch (_: dynamic) {

        }
        w.HunterEvolution385 = global_Object.freeze(jsObject { obj69 ->
            obj69["version"] = "38.5"
            accessor(obj69, "mode", "get", fun(): dynamic {
                return selected
            })
            accessor(obj69, "budget", "get", fun(): dynamic {
                return decideBudget()
            })
            obj69["setMode"] = setMode
            obj69["refresh"] = queueRefresh
            obj69["diagnostics"] = (fun(): dynamic {
                return jsObject { obj74 ->
                    obj74["mode"] = selected
                    obj74["budget"] = decideBudget()
                    obj74["rank"] = lastRank
                    obj74["transitions"] = animations
                    obj74["ascensos"] = rankTransitions
                    obj74["inputsIgnorados"] = ignoredInputs
                    obj74["visibility"] = (if (truth(d.hidden)) "oculto" else "visible")
                    obj74["notice"] = "No son mediciones de FPS ni de consumo de batería."
                }
            })
            obj69["dispose"] = (fun(): dynamic {
                onPresent(appObserver) { value75 -> value75.disconnect() }
                appObserver = dynamicValue(null)
                return absent
            })
        })
        if (truth((d.readyState === "loading"))) {
            d.addEventListener("DOMContentLoaded", `init`, jsonValue("{\"once\":true}"))
        } else {
            `init`()
        }
        return absent
    }))(global_window, global_document)
}
