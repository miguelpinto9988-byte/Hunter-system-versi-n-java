@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv26

import hunter.browser.*

@JsName("V26UI") private external var global_V26UI: dynamic
@JsName("UI26") private external var global_UI26: dynamic
@JsName("revealBefore26") private external var global_revealBefore26: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("header") private external var global_header: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("decorateModal") private external var global_decorateModal: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("paintGuide11") private external var global_paintGuide11: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("routineDetail") private external var global_routineDetail: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("requestAnimationFrame") private external var global_requestAnimationFrame: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("ThemeHues") private external var global_ThemeHues: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("themeNative20") private external var global_themeNative20: dynamic
@JsName("HunterKotlinScreens") private external var global_HunterKotlinScreens: dynamic
@JsName("IgrisArt") private external var global_IgrisArt: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("MutationObserver") private external var global_MutationObserver: dynamic
@JsName("reveal20") private external var global_reveal20: dynamic
@JsName("Promise") private external var global_Promise: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV26() {
    global_V26UI = jsObject { obj1 ->
        obj1["home"] = global_home
        obj1["header"] = global_header
        obj1["render"] = global_render
        obj1["modal"] = global_modal
        obj1["decorateModal"] = global_decorateModal
        obj1["applyTheme"] = global_applyTheme
        obj1["paintGuide11"] = global_paintGuide11
        obj1["openCoach"] = global_openCoach
        obj1["routineDetail"] = global_routineDetail
    }
    global_UI26 = jsObject { obj2 ->
        obj2["cardSelector"] = ".card,.metric,.command-hero,.academy-banner,.notification-banner,.adaptation-chip,.learning-card,.coach-intro,.health-compact,.exercise-card,.category-card,.routine-card,.trainer-tile,.gym-banner23"
        obj2["pending"] = 0
        obj2["corner"] = "<svg viewBox=\"0 0 46 46\" fill=\"none\" aria-hidden=\"true\"><path d=\"M2 43V13L13 2h30l-7 6H16L8 16v20Z\" fill=\"currentColor\" fill-opacity=\".13\" stroke=\"currentColor\" stroke-width=\"1.2\"/><path d=\"M5 34V15L15 5h19M12 27V17l5-5h10M3 3h7L3 10Z\" stroke=\"currentColor\" stroke-width=\"1\"/><path d=\"m13 13 5 1-4 4Z\" fill=\"currentColor\"/><path d=\"M2 43 8 36M36 8l7-6\" stroke=\"var(--accent)\" stroke-width=\"2\"/></svg>"
        obj2["frame"] = fun(arg5: dynamic): dynamic {
            val receiver3: dynamic = js("this")
            var windowFrame: dynamic = absent
            windowFrame = definedOr(arg5) { false }
            return jsAdd(jsAdd(jsAdd(jsAdd("<span class=\"", (if (truth(windowFrame)) "window-frame26" else "ornament26")), "\" aria-hidden=\"true\">"), dynamicValue(jsonValue("[\"tl\",\"tr\",\"bl\",\"br\"]")).map((fun(c: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd(jsAdd("<span class=\"corner26 ", c), "\">"), receiver3.corner), "</span>")
            })).join("")), "<span class=\"frame-glint26\"></span></span>")
        }
        obj2["decorate"] = fun(root: dynamic): dynamic {
            val receiver6: dynamic = js("this")
            if (truth((!truth(root)))) {
                return absent
            }
            val hero: dynamic = global_Dollar("#app .hero26")
            val goals: dynamic = global_Dollar("#app .goals-frame26")
            if (truth(jsAnd(hero) { goals })) {
                goals.style.setProperty("--hero-height26", (if (truth(jsAtLeast(global_window.innerWidth, 720))) jsAdd(hero.getBoundingClientRect().height, "px") else "280px"))
            }
            val dialog: dynamic = global_Dollar("#modal")
            if (truth(dialog["open"])) {
                var toolbar: dynamic = dialog.querySelector(":scope > .window-actions26")
                if (truth((!truth(toolbar)))) {
                    toolbar = global_document.createElement("div")
                    toolbar.className = "window-actions26"
                    dialog.prepend(toolbar)
                }
                val back: dynamic = dialog.querySelector(".modal-back24")
                val close: dynamic = dialog.querySelector(".modal-close-x")
                if (truth(jsAnd(back) { (back.parentElement !== toolbar) })) {
                    toolbar.append(back)
                }
                if (truth(close)) {
                    if (truth((close.parentElement !== toolbar))) {
                        toolbar.append(close)
                    }
                    if (truth((!truth(close.querySelector("svg"))))) {
                        close.innerHTML = "<svg viewBox=\"0 0 24 24\" aria-hidden=\"true\"><path d=\"m6 6 12 12M18 6 6 18\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"1.8\" stroke-linecap=\"round\"/></svg>"
                    }
                }
            }
            if (true) {
                val iterator12: dynamic = iteratorOf(root.querySelectorAll(receiver6.cardSelector))
                while (true) {
                    val next13: dynamic = iterator12.next()
                    if (truth(next13.done)) break
                    var node: dynamic = absent
                    node = next13.value
                    if (truth(node.closest(".motion-svg18,.companion-picker,[data-companion-static=\"true\"] .companion-option"))) {
                        continue
                    }
                    node.classList.add("system-card26")
                    var ornament: dynamic = node.querySelector(":scope > .ornament26,:scope > summary > .ornament26")
                    if (truth((!truth(ornament)))) {
                        node.insertAdjacentHTML("beforeend", receiver6.frame())
                        ornament = node.querySelector(":scope > .ornament26")
                    }
                    if (truth((node.tagName === "DETAILS"))) {
                        val summary: dynamic = node.querySelector(":scope > summary")
                        if (truth(jsAnd(summary) { (ornament.parentElement !== summary) })) {
                            summary.append(ornament)
                        }
                    }
                }
            }
            if (truth(dialog["open"])) {
                dialog.classList.add("system-window26")
                if (truth((!truth(dialog.querySelector(":scope > .window-frame26"))))) {
                    dialog.insertAdjacentHTML("beforeend", receiver6.frame(true))
                }
                val height: dynamic = dialog.getBoundingClientRect().height
                if (truth(jsMore(height, 0))) {
                    dialog.style.setProperty("--window-height26", jsAdd(height, "px"))
                }
            }
            return absent
        }
        obj2["schedule"] = fun(): dynamic {
            val receiver14: dynamic = js("this")
            if (truth(receiver14.pending)) {
                return absent
            }
            receiver14.pending = global_requestAnimationFrame((fun(): dynamic {
                receiver14.pending = 0
                if (truth(global_Dollar("#modal")["open"])) {
                    global_ModalViewport.fit()
                }
                receiver14.decorate(global_document)
                return absent
            }))
            return absent
        }
    }
    global_applyTheme = (fun(arg22: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg22) { jsOr(global_state.profile) { js("({})") } }
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V26UI.applyTheme(p)
        val root: dynamic = global_document.documentElement
        val h: dynamic = nullableOr(global_ThemeHues[root.dataset.theme]) { global_ThemeHues.blue }
        val values: dynamic = jsObject { obj23 ->
            obj23["--bg"] = ("hsl(" + textOf(h) + " 42% 7%)")
            obj23["--panel"] = ("hsl(" + textOf(h) + " 42% 10%)")
            obj23["--surface"] = ("hsl(" + textOf(h) + " 40% 14%)")
            obj23["--input"] = ("hsl(" + textOf(h) + " 38% 8%)")
            obj23["--line"] = ("hsl(" + textOf(h) + " 29% 33%)")
            obj23["--text"] = ("hsl(" + textOf(h) + " 44% 94%)")
            obj23["--muted"] = ("hsl(" + textOf(h) + " 24% 75%)")
            obj23["--ornament26"] = ("hsl(" + textOf(jsMod(jsAdd(h, 42), 360)) + " 32% 62%)")
            obj23["--metal26"] = ("hsl(" + textOf(jsMod(jsAdd(h, 25), 360)) + " 28% 30%)")
            obj23["--theme-hue26"] = h
        }
        global_Object.entries(values).forEach((fun(arg24: dynamic): dynamic {
            var k: dynamic = absent
            var v: dynamic = absent
            val unpacked25: dynamic = fromIterable(arg24)
            k = unpacked25[0]
            v = unpacked25[1]
            return root.style.setProperty(k, v)
        }))
        global_document.querySelector("meta[name=theme-color]").content = values["--bg"]
        global_themeNative20()
        return absent
    })
    global_header = (fun(): dynamic {
        return global_HunterKotlinScreens.header()
    })
    global_home = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V26UI.home()
        val hero: dynamic = box.querySelector(".command-hero")
        if (truth(hero)) {
            hero.classList.add("hero26")
            onPresent(hero.querySelector(".hero-lines")) { value34 -> value34.remove() }
            val runePaths: dynamic = jsonValue("[\"M5 21V3l10 7-10 5M10 12l7 9\",\"M4 20V4l10 6M4 12l10 6\",\"M6 3v18M16 3 6 11l10 10\",\"M5 3l11 9L5 21V3\",\"M3 7l7-5 7 5-7 5Zm7 5v11\",\"M4 3l12 18M16 3 4 21M10 4v16\",\"M5 4v16l10-8Z\",\"M3 13 10 3l7 10-7 10Zm7-10v20\",\"M4 20V4l6 6 6-6v16\",\"M4 3v18l12-8-12-4\"]")
            hero.insertAdjacentHTML("afterbegin", jsAdd(jsAdd("<div class=\"hero-art26\" aria-hidden=\"true\"></div><div class=\"hero-runes26\" aria-hidden=\"true\">", runePaths.map((fun(path: dynamic): dynamic {
                return jsAdd(jsAdd("<svg viewBox=\"0 0 20 24\" fill=\"none\"><path d=\"", path), "\" stroke=\"currentColor\" stroke-width=\"1.2\" stroke-linecap=\"round\" stroke-linejoin=\"round\"/></svg>")
            })).join("")), "</div><svg class=\"hero-flash26\" viewBox=\"0 0 600 400\" preserveAspectRatio=\"none\" aria-hidden=\"true\"><path d=\"M6 345 10 321 6 305 13 297 7 281 11 272 6 245M594 52l-6 28 8 14-9 20 7 18-6 14 6 25M50 392h130l13-4 14 8 18-5h60\" fill=\"none\" stroke=\"currentColor\" stroke-width=\"1.5\"/></svg>"))
            hero.querySelector("h1").innerHTML = "SUPERA<br><span>TUS LÍMITES.</span>"
        }
        val dashboard: dynamic = box.querySelector(".dashboard-grid")
        val left: dynamic = onPresent(dashboard) { value37 -> value37.querySelector(":scope>.stack") }
        val aside: dynamic = onPresent(dashboard) { value38 -> value38.querySelector(":scope>aside") }
        if (truth(jsAnd(left) { aside })) {
            dashboard.classList.add("dashboard26")
            val goals: dynamic = dynamicValue(jsArray { array40 ->
                appendAll(array40, aside.children)
            }).find((fun(n: dynamic): dynamic {
                return (onPresent(n.querySelector("h2,h3")) { value41 -> value41.textContent } === "Tus objetivos")
            }))
            val cycle: dynamic = aside.querySelector(".cycle-card")
            if (truth(goals)) {
                goals.classList.add("goals-frame26")
            }
            if (truth(cycle)) {
                cycle.classList.add("cycle-frame26")
                cycle.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<div class=\"cycle-sigil26\" aria-hidden=\"true\" data-companion-static=\"true\">", global_IgrisArt.svg(global_selectedCompanion())), "</div>"))
            }
            if (true) {
                val iterator42: dynamic = iteratorOf(jsArray { array44 ->
                    appendAll(array44, box.children)
                })
                while (true) {
                    val next43: dynamic = iterator42.next()
                    if (truth(next43.done)) break
                    var node: dynamic = absent
                    node = next43.value
                    if (truth(jsOr((node === dashboard)) { node.classList.contains("adaptation-chip") })) {
                        continue
                    }
                    if (truth(jsOr(node.classList.contains("coach-banner")) { node.classList.contains("academy-banner") })) {
                        left.append(node)
                    } else {
                        aside.append(node)
                    }
                }
            }
        }
        return box.innerHTML
    })
    global_decorateModal = (fun(type: dynamic): dynamic {
        global_V26UI.decorateModal(type)
        global_UI26.decorate(global_Dollar("#modal"))
        global_UI26.schedule()
        return absent
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_V26UI.modal(html)
        global_UI26.decorate(global_Dollar("#modal"))
        global_UI26.schedule()
        return absent
    })
    global_paintGuide11 = (fun(): dynamic {
        global_V26UI.paintGuide11()
        global_UI26.decorate(global_Dollar("#modal"))
        global_UI26.schedule()
        return absent
    })
    global_openCoach = (fun(): dynamic {
        global_V26UI.openCoach()
        global_UI26.decorate(global_Dollar("#modal"))
        global_UI26.schedule()
        return absent
    })
    global_routineDetail = (fun(id: dynamic): dynamic {
        global_V26UI.routineDetail(id)
        global_UI26.decorate(global_Dollar("#modal"))
        global_UI26.schedule()
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V26UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target57: dynamic = n
                val assigned59: dynamic = "SISTEMA DE SOMBRAS"
                target57.textContent = assigned59
                assigned59
            }
        }))
        val dock: dynamic = global_Dollar(".nav")
        val `companion`: dynamic = global_Dollar("#companion")
        if (truth(jsAnd(dock) { `companion` })) {
            `companion`.classList.add("companion-dock26")
            dock.classList.add("nav-dock26")
            dock.append(`companion`)
        }
        global_UI26.decorate(global_document)
        global_UI26.schedule()
        return absent
    })
    jsConstruct(global_MutationObserver, arrayOf<dynamic>((fun(): dynamic {
        return global_UI26.schedule()
    }))).observe(global_Dollar("#modal"), jsonValue("{\"childList\":true}"))
    global_window.addEventListener("resize", (fun(): dynamic {
        return global_UI26.schedule()
    }), jsonValue("{\"passive\":true}"))
    onPresent(global_window.visualViewport) { value60 -> value60.addEventListener("resize", (fun(): dynamic {
        return global_UI26.schedule()
    }), jsonValue("{\"passive\":true}")) }
    global_revealBefore26 = global_reveal20
    global_reveal20 = (fun(): dynamic {
        if (truth((!truth(onPresent(global_document.fonts) { value63 -> value63.load })))) {
            return global_revealBefore26()
        }
        global_Promise.race(arrayOf<dynamic>(global_Promise.all(arrayOf<dynamic>(global_document.fonts.load("400 24px \"Hunter Display\""), global_document.fonts.load("400 18px \"Hunter Text\""), global_document.fonts.load("600 18px \"Hunter Text\""))), jsConstruct(global_Promise, arrayOf<dynamic>((fun(resolve: dynamic): dynamic {
            return global_setTimeout(resolve, 1500)
        })))))["catch"]((fun(): dynamic {

            return absent
        }))["then"]((fun(): dynamic {
            global_UI26.decorate(global_document)
            global_revealBefore26()
            return absent
        }))
        return absent
    })
    global_window.HunterV26 = jsObject { obj66 ->
        obj66["UI26"] = global_UI26
    }
    global_state.appVersion = 26
    global_applyTheme()
    global_save()
    global_render()
}
