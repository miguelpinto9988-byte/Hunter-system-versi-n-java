@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv33

import hunter.browser.*

@JsName("V33UI") private external var global_V33UI: dynamic
@JsName("layout33") private external var global_layout33: dynamic
@JsName("selectTheme33") private external var global_selectTheme33: dynamic
@JsName("ShadowActor33") private external var global_ShadowActor33: dynamic
@JsName("phraseTimer33") private external var global_phraseTimer33: dynamic
@JsName("phrases33") private external var global_phrases33: dynamic
@JsName("updatePhrase33") private external var global_updatePhrase33: dynamic
@JsName("schedulePhrase33") private external var global_schedulePhrase33: dynamic
@JsName("decorate33") private external var global_decorate33: dynamic
@JsName("decorateBefore33") private external var global_decorateBefore33: dynamic
@JsName("evolutionPreview33") private external var global_evolutionPreview33: dynamic
@JsName("openEvolution33") private external var global_openEvolution33: dynamic
@JsName("previewEvolution33") private external var global_previewEvolution33: dynamic
@JsName("paintEvolution33") private external var global_paintEvolution33: dynamic
@JsName("applyTheme") private external var global_applyTheme: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("home") private external var global_home: dynamic
@JsName("openCoach") private external var global_openCoach: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("Visual33") private external var global_Visual33: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Evolution28") private external var global_Evolution28: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("ThemeHues") private external var global_ThemeHues: dynamic
@JsName("themeNative20") private external var global_themeNative20: dynamic
@JsName("previewTheme") private external var global_previewTheme: dynamic
@JsName("String") private external var global_String: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("Map") private external var global_Map: dynamic
@JsName("matchMedia") private external var global_matchMedia: dynamic
@JsName("Promise") private external var global_Promise: dynamic
@JsName("setTimeout") private external var global_setTimeout: dynamic
@JsName("clearTimeout") private external var global_clearTimeout: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("mountPersistentCompanion") private external var global_mountPersistentCompanion: dynamic
@JsName("mountPanelCompanion") private external var global_mountPanelCompanion: dynamic
@JsName("releaseCompanion") private external var global_releaseCompanion: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("activeCompanion") private external var global_activeCompanion: dynamic
@JsName("CompanionNames") private external var global_CompanionNames: dynamic
@JsName("selectedCompanion") private external var global_selectedCompanion: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("ModalViewport") private external var global_ModalViewport: dynamic
@JsName("UI26") private external var global_UI26: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("homeCompanion") private external var global_homeCompanion: dynamic
@JsName("missionCompanion") private external var global_missionCompanion: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("save") private external var global_save: dynamic

internal fun installV33() {
    global_selectTheme33 = fun(button: dynamic): dynamic {
        val form: dynamic = button.closest("form")
        val select: dynamic = form.elements.namedItem("theme")
        select["value"] = button.dataset.theme33
        global_previewTheme(form)
        return absent
    }
    global_updatePhrase33 = fun(): dynamic {
        val bubble: dynamic = global_Dollar("#shadowPhrase33")
        if (truth((!truth(bubble)))) {
            return absent
        }
        val type: dynamic = global_selectedCompanion()
        val list: dynamic = jsOr(global_phrases33[type]) { global_phrases33.knight }
        val index: dynamic = jsAdd(jsOr(global_Number(bubble.dataset.index)) { 0 }, 1)
        bubble.dataset.index = global_String(index)
        bubble.textContent = list[jsMod(index, list.length)]
        return absent
    }
    global_schedulePhrase33 = fun(): dynamic {
        global_clearTimeout(global_phraseTimer33)
        if (truth(jsOr(jsOr(jsOr(jsOr((!truth(global_Dollar("#modal")["open"]))) { (!truth(global_Dollar("#shadowPhrase33"))) }) { global_document.hidden }) { (onPresent(global_state.profile) { value181 -> value181.animate } === false) }) { global_matchMedia("(prefers-reduced-motion: reduce)").matches })) {
            return absent
        }
        global_phraseTimer33 = global_setTimeout((fun(): dynamic {
            global_updatePhrase33()
            global_schedulePhrase33()
            return absent
        }), jsAdd(20000, jsMultiply(global_Math.random(), 12000)))
        return absent
    }
    global_decorate33 = fun(): dynamic {
        if (truth((!truth(global_document.documentElement.dataset.frame33)))) {
            return absent
        }
        if (true) {
            val iterator198: dynamic = iteratorOf(global_document.querySelectorAll(".ornament26:not(.monarch-frame33),.window-frame26:not(.monarch-frame33)"))
            while (true) {
                val next199: dynamic = iterator198.next()
                if (truth(next199.done)) break
                var frame: dynamic = absent
                frame = next199.value
                frame.outerHTML = global_UI26.frame(frame.classList.contains("window-frame26"))
            }
        }
        val modal: dynamic = global_Dollar("#modal")
        onPresent(modal) { value202 -> value202.classList.toggle("coach-window33", (!truth((!truth(modal.querySelector(".shadow-showcase33")))))) }
        return absent
    }
    global_openEvolution33 = fun(): dynamic {
        val s: dynamic = global_Visual33.selection()
        global_evolutionPreview33 = jsObject { obj233 ->
            obj233["rank"] = s.stage.key
            obj233["collection"] = s.collection
            obj233["gender"] = s.gender
            obj233["scene"] = s.scene
        }
        global_paintEvolution33()
        return absent
    }
    global_previewEvolution33 = fun(key: dynamic, `value`: dynamic): dynamic {
        if (truth(jsOr(jsOr(jsOr(jsAnd((key === "rank")) { (!truth(global_Visual33.ranks.some((fun(r: dynamic): dynamic {
            return (r.key === `value`)
        })))) }) { jsAnd((key === "collection")) { (!truth(dynamicValue(jsonValue("[\"hunter\",\"reader\",\"regressor\"]")).includes(`value`))) } }) { jsAnd((key === "gender")) { (!truth(dynamicValue(jsonValue("[\"male\",\"female\"]")).includes(`value`))) } }) { jsAnd((key === "scene")) { (!truth(dynamicValue(jsonValue("[0,1]")).includes(global_Number(`value`)))) } })) {
            return absent
        }
        global_evolutionPreview33[key] = (if (truth((key === "scene"))) global_Number(`value`) else `value`)
        global_paintEvolution33()
        return absent
    }
    global_paintEvolution33 = fun(): dynamic {
        val v: dynamic = global_evolutionPreview33
        val rank: dynamic = jsOr(global_Visual33.ranks.find((fun(r: dynamic): dynamic {
            return (r.key === v.rank)
        }))) { global_Visual33.ranks[0] }
        val s: dynamic = global_Visual33.selection(jsObject { obj240 ->
            obj240["gender"] = v.gender
        }, rank.min, jsObject { obj241 ->
            obj241["collection"] = v.collection
            obj241["palette"] = global_Visual33.preferences().palette
            obj241["scene"] = (if (truth(v.scene)) "journey" else "sanctuary")
        })
        val live: dynamic = global_Visual33.rank()
        global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section class=\"evolution-gallery33\" data-companion-static=\"true\"><span class=\"eyebrow\">GALERÍA · EVOLUCIÓN VISUAL</span><h2>Tu ascenso, seis capítulos.</h2><p>Vista de las ilustraciones. Tu rango actual es ", live.key), "; esta galería no cambia tus registros.</p><div class=\"rank-tabs33\" role=\"group\" aria-label=\"Rango de muestra\">"), global_Visual33.ranks.map((fun(r: dynamic): dynamic {
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<button class=\"btn ", (if (truth((r.key === rank.key))) "selected" else "ghost")), "\" aria-pressed=\""), (r.key === rank.key)), "\" onclick=\"previewEvolution33('rank','"), r.key), "')\">"), r.key), "<small>"), r.min), (if (truth(global_Number.isFinite(r.max))) jsAdd("–", r.max) else "+")), "</small></button>")
        })).join("")), "</div><div class=\"gallery-layout33\"><div class=\"evolution-stage33\" style=\"--preview-scene33:url(&quot;"), s.asset.path), "&quot;)\">"), global_Visual33.figureHTML(s, "gallery-figure33")), "<b>"), rank.key), "</b></div><div><h3>"), rank.name), "</h3><p>"), rank.detail), "</p><label>Colección<select onchange=\"previewEvolution33('collection',this.value)\">"), global_options(jsObject { obj242 ->
            obj242["hunter"] = global_Visual33.collections.hunter
            obj242["reader"] = global_Visual33.collections.reader
            obj242["regressor"] = global_Visual33.collections.regressor
        }, v.collection)), "</select></label>"), (if (truth((v.collection === "hunter"))) jsAdd(jsAdd("<label>Cazador de muestra<select onchange=\"previewEvolution33('gender',this.value)\">", global_options(jsonValue("{\"male\":\"Masculino\",\"female\":\"Femenino\"}"), v.gender)), "</select></label>") else "")), "<label>Escenario<select onchange=\"previewEvolution33('scene',this.value)\">"), global_options(jsonValue("{\"0\":\"Santuario\",\"1\":\"Viaje\"}"), global_String(v.scene))), "</select></label><div class=\"notice\">"), rank.xp.toLocaleString("es")), " XP acumulados · nivel "), rank.min), (if (truth(global_Number.isFinite(rank.max))) jsAdd(" a ", rank.max) else " en adelante")), ".</div><p class=\"footnote\">La ilustración representa logros dentro de la app. No predice cambios del cuerpo. Puedes guardar tu colección y escenario desde Perfil.</p></div></div></section>"))
        return absent
    }
    global_V33UI = jsObject { obj1 ->
        obj1["applyTheme"] = global_applyTheme
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["render"] = global_render
        obj1["home"] = global_home
        obj1["openCoach"] = global_openCoach
        obj1["modal"] = global_modal
        obj1["validateBackup"] = global_validateBackup
        obj1["openDiagnostics20"] = global_openDiagnostics20
    }
    global_Visual33.installFrame()
    global_state.appearance33 = global_Visual33.preferences()
    global_validateBackup = (fun(`data`: dynamic): dynamic {
        if (truth((!truth(global_V33UI.validateBackup(`data`))))) {
            return false
        }
        val a: dynamic = `data`.appearance33
        if (truth((a === absent))) {
            return true
        }
        return jsAnd(jsAnd(jsAnd(jsAnd(jsAnd(a) { (jsTypeOf(a) === "object") }) { (!truth(global_Array.isArray(a))) }) { global_Object.hasOwn(global_Visual33.palettes, a.palette) }) { global_Object.hasOwn(global_Visual33.collections, a.collection) }) { dynamicValue(jsonValue("[\"auto\",\"sanctuary\",\"journey\"]")).includes(a.scene) }
    })
    global_Evolution28.stages = global_Visual33.ranks
    global_Evolution28.selection = (fun(arg12: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg12) { jsOr(global_state.profile) { js("({})") } }
        return global_Visual33.selection(p)
    })
    global_layout33 = global_Evolution28.layout
    global_Evolution28.layout = (fun(arg17: dynamic): dynamic {
        val receiver15: dynamic = js("this")
        var s: dynamic = absent
        s = definedOr(arg17) { jsOr(receiver15.current) { receiver15.selection() } }
        global_layout33.call(receiver15, s)
        global_document.documentElement.dataset.rank33 = s.stage.key
        if (true) {
            val iterator20: dynamic = iteratorOf(global_document.querySelectorAll(".evolution-art28"))
            while (true) {
                val next21: dynamic = iterator20.next()
                if (truth(next21.done)) break
                var art: dynamic = absent
                art = next21.value
                art.dataset.rank33 = s.stage.key
                val hero: dynamic = art.closest(".hero26")
                if (truth(hero)) {
                    val img: dynamic = hero.querySelector(".hero-figure33")
                    if (truth(jsAnd(img) { (img.getAttribute("src") !== s.figure) })) {
                        img.src = s.figure
                    }
                }
            }
        }
        return absent
    })
    global_applyTheme = (fun(arg28: dynamic, arg29: dynamic): dynamic {
        var p: dynamic = absent
        p = definedOr(arg28) { jsOr(global_state.profile) { js("({})") } }
        var prefs: dynamic = absent
        prefs = definedOr(arg29) { global_Visual33.preferences() }
        global_V33UI.applyTheme(p)
        val root: dynamic = global_document.documentElement
        val colors: dynamic = global_Visual33.colors(root.dataset.theme, prefs.palette)
        colors["--glow"] = jsAdd(jsAdd("hsla(", jsOr(global_ThemeHues[root.dataset.theme]) { 202 }), ",35%,60%,.12)")
        if (true) {
            val iterator32: dynamic = iteratorOf(global_Object.entries(colors))
            while (true) {
                val next33: dynamic = iterator32.next()
                if (truth(next33.done)) break
                var key: dynamic = absent
                var `value`: dynamic = absent
                val unpacked34: dynamic = fromIterable(next33.value)
                key = unpacked34[0]
                `value` = unpacked34[1]
                root.style.setProperty(key, `value`)
            }
        }
        root.dataset.palette33 = prefs.palette
        root.dataset.frame33 = "monarca"
        global_document.querySelector("meta[name=theme-color]").content = colors["--bg"]
        global_themeNative20()
        global_Evolution28.layout(global_Visual33.selection(p, absent, prefs))
        return absent
    })
    global_previewTheme = (fun(form: dynamic): dynamic {
        val `value`: dynamic = (fun(name: dynamic, fallback: dynamic): dynamic {
            return jsOr(onPresent(form.elements.namedItem(name)) { value43 -> value43["value"] }) { fallback }
        })
        val p: dynamic = jsObject { obj44 ->
            copyFields(obj44, jsOr(global_state.profile) { js("({})") })
            obj44["gender"] = `value`("gender", "unspecified")
            obj44["theme"] = `value`("theme", "auto")
            obj44["animate"] = (onPresent(form.elements.namedItem("animate")) { value45 -> value45.checked } !== false)
        }
        val prefs: dynamic = global_Visual33.preferences(jsObject { obj46 ->
            obj46["palette"] = `value`("palette33", "bruma")
            obj46["collection"] = `value`("collection33", "hunter")
            obj46["scene"] = `value`("scene33", "auto")
        })
        global_applyTheme(p, prefs)
        form.querySelectorAll("[data-theme33]").forEach((fun(b: dynamic): dynamic {
            return b.setAttribute("aria-pressed", global_String((b.dataset.theme33 === global_document.documentElement.dataset.theme)))
        }))
        val image: dynamic = form.querySelector(".appearance-preview33 img")
        val s: dynamic = global_Visual33.selection(p, absent, prefs)
        if (truth(image)) {
            image.src = s.figure
        }
        val box: dynamic = form.querySelector(".appearance-preview33")
        if (truth(box)) {
            box.style.setProperty("--preview-scene33", jsAdd(jsAdd("url(\"", s.asset.path), "\")"))
        }
        return absent
    })
    global_profileForm = (fun(arg55: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg55) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V33UI.profileForm(initial)
        val form: dynamic = box.querySelector("form")
        if (truth((!truth(form)))) {
            return box.innerHTML
        }
        val a: dynamic = global_Visual33.preferences()
        val s: dynamic = global_Visual33.selection()
        box.querySelectorAll(".evolution-note28").forEach((fun(n: dynamic): dynamic {
            return n.remove()
        }))
        val theme: dynamic = form.querySelector("[name=theme]")
        val label: dynamic = onPresent(theme) { value58 -> value58.closest("label") }
        if (truth(label)) {
            label.classList.add("theme-select33")
            label.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<span class=\"theme-swatches33\">", global_Object.entries(global_Visual33.themes).map((fun(arg61: dynamic): dynamic {
                var key: dynamic = absent
                var name: dynamic = absent
                val unpacked62: dynamic = fromIterable(arg61)
                key = unpacked62[0]
                name = unpacked62[1]
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<button class=\"theme-swatch33\" type=\"button\" data-theme33=\"", key), "\" style=\"--swatch33:hsl("), global_ThemeHues[key]), " 33% 66%)\" aria-label=\""), name), "\" aria-pressed=\""), (global_document.documentElement.dataset.theme === key)), "\" onclick=\"selectTheme33(this)\"><i></i><span>"), name), "</span></button>")
            })).join("")), "</span>"))
        }
        val `field`: dynamic = global_document.createElement("fieldset")
        `field`.className = "appearance-settings33"
        `field`.dataset.companionStatic = "true"
        `field`.innerHTML = jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<legend>Estética Monarca</legend><p>Diez temas, tres combinaciones por tema. El color se aplica también a fondos, tarjetas y opciones seleccionadas.</p><div class=\"palette-options33\">", global_Object.entries(global_Visual33.palettes).map((fun(arg71: dynamic): dynamic {
            var key: dynamic = absent
            var name: dynamic = absent
            val unpacked72: dynamic = fromIterable(arg71)
            key = unpacked72[0]
            name = unpacked72[1]
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<label style=\"--palette-hue33:", (if (truth((key === "umbral"))) 267 else (if (truth((key === "lunar"))) 151 else 202))), "\"><input type=\"radio\" name=\"palette33\" value=\""), key), "\" "), (if (truth((a.palette === key))) "checked" else "")), " onchange=\"previewTheme(this.form)\"><span><i></i><b>"), name), "</b><small>"), (if (truth((key === "bruma"))) "Niebla y acento suave" else (if (truth((key === "umbral"))) "Tinta y matiz amatista" else "Matiz mineral y luz lunar"))), "</small></span></label>")
        })).join("")), "</div><div class=\"appearance-layout33\"><div class=\"appearance-preview33\" style=\"--preview-scene33:url(&quot;"), s.asset.path), "&quot;)\">"), global_Visual33.figureHTML(s, "appearance-figure33")), "<b>RANGO "), s.stage.key), "</b></div><div><label>Colección visual<select name=\"collection33\" onchange=\"previewTheme(this.form)\">"), global_options(global_Visual33.collections, a.collection)), "</select></label><label>Escenario<select name=\"scene33\" onchange=\"previewTheme(this.form)\">"), global_options(jsonValue("{\"auto\":\"Alternar cada día\",\"sanctuary\":\"Santuarios y portales\",\"journey\":\"Viajes y constelaciones\"}"), a.scene)), "</select></label><p class=\"footnote\">El cazador sigue el género de tu perfil. Las colecciones de Kim Dokja y Junghyeok Yu mantienen la apariencia de esos personajes. La colección visual no cambia tu compañero de IA.</p><button class=\"btn ghost\" type=\"button\" onclick=\"openEvolution33()\">Ver las seis evoluciones →</button></div></div><p class=\"footnote\">E · 1–5 / D · 6–10 / C · 11–15 / B · 16–20 / A · 21–25 / S · 26+. Es una evolución artística ligada a tus registros.</p>")
        val identity: dynamic = jsOr(onPresent(form.querySelector("[name=difficulty]")) { value73 -> value73.closest(".profile-block") }) { form.firstElementChild }
        onPresent(identity) { value74 -> value74.append(`field`) }
        if (true) {
            val iterator75: dynamic = iteratorOf(box.querySelectorAll(".companion-option"))
            while (true) {
                val next76: dynamic = iterator75.next()
                if (truth(next76.done)) break
                var opt: dynamic = absent
                opt = next76.value
                val type: dynamic = onPresent(opt.querySelector("input")) { value77 -> value77["value"] }
                val art: dynamic = opt.querySelector(".companion-option-art")
                if (truth(art)) {
                    art.innerHTML = jsAdd(jsAdd("<img src=\"", global_Visual33.companionPath(type)), "\" alt=\"\" loading=\"lazy\">")
                }
            }
        }
        val summary: dynamic = box.querySelector(".companion-picker-summary")
        if (truth(summary)) {
            onPresent(summary.querySelector("svg")) { value80 -> value80.replaceWith(global_Object.assign(global_document.createElement("img"), jsObject { obj81 ->
                obj81["src"] = global_Visual33.companionPath()
                obj81["alt"] = ""
            })) }
        }
        return box.innerHTML
    })
    global_submitProfile = (fun(event: dynamic): dynamic {
        val f: dynamic = event.target
        val previous: dynamic = global_state.profile
        val previousAppearance: dynamic = global_state.appearance33
        val appearance: dynamic = global_Visual33.preferences(jsObject { obj84 ->
            obj84["palette"] = onPresent(f.elements.namedItem("palette33")) { value85 -> value85["value"] }
            obj84["collection"] = onPresent(f.elements.namedItem("collection33")) { value86 -> value86["value"] }
            obj84["scene"] = onPresent(f.elements.namedItem("scene33")) { value87 -> value87["value"] }
        })
        global_state.appearance33 = appearance
        global_V33UI.submitProfile(event)
        if (truth((global_state.profile === previous))) {
            global_state.appearance33 = previousAppearance
        }
        return absent
    })
    global_home = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V33UI.home()
        val hero: dynamic = box.querySelector(".hero26")
        val s: dynamic = global_Visual33.selection()
        if (truth(hero)) {
            hero.classList.add("monarch-hero33")
            hero.insertAdjacentHTML("afterbegin", global_Visual33.figureHTML(s))
            val stage: dynamic = hero.querySelector(".scene-stage28")
            if (truth(stage)) {
                stage.textContent = jsAdd(jsAdd(jsAdd("RANGO ", s.stage.key), " · "), s.stage.name.toUpperCase())
            }
            hero.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<button type=\"button\" class=\"hero-evolution33\" onclick=\"openEvolution33()\" aria-label=\"Ver evolución visual por rango\">", s.stage.key), " <span>EVOLUCIÓN</span> ⟶</button>"))
        }
        val sigil: dynamic = box.querySelector(".cycle-sigil26")
        if (truth(sigil)) {
            sigil.innerHTML = jsAdd(jsAdd("<img src=\"", global_Visual33.companionPath()), "\" alt=\"\" loading=\"lazy\">")
        }
        val chip: dynamic = box.querySelector(".adaptation-chip")
        val aside: dynamic = box.querySelector(".dashboard26>aside")
        if (truth(jsAnd(chip) { aside })) {
            aside.append(chip)
        }
        return box.innerHTML
    })
    global_ShadowActor33 = fun(host: dynamic, type: dynamic): dynamic {
        val receiver100: dynamic = js("this")
        receiver100.host = host
        receiver100.type = type
        receiver100.alive = true
        receiver100.paused = false
        receiver100.timer = 0
        receiver100.motionTimer = 0
        receiver100.sequence = 0
        receiver100.pending = jsConstruct(global_Map, js("[]"))
        host.classList.add("shadow-actor33")
        host.dataset.character = type
        host.innerHTML = jsAdd(jsAdd("<span class=\"actor-aura33\" aria-hidden=\"true\"></span><img src=\"", global_Visual33.companionPath(type)), "\" alt=\"\" decoding=\"async\">")
        receiver100.mode("idle")
        return absent
    }
    global_ShadowActor33.prototype["allowed"] = fun(): dynamic {
        val receiver122: dynamic = js("this")
        return jsAnd(jsAnd(jsAnd(jsAnd(receiver122.alive) { (!truth(receiver122.paused)) }) { (!truth(global_document.hidden)) }) { (onPresent(global_state.profile) { value124 -> value124.animate } !== false) }) { (!truth(global_matchMedia("(prefers-reduced-motion: reduce)").matches)) }
    }
    global_ShadowActor33.prototype["mode"] = fun(name: dynamic): dynamic {
        val receiver125: dynamic = js("this")
        if (truth(onPresent(receiver125.host) { value127 -> value127.isConnected })) {
            receiver125.host.dataset.action33 = name
        }
        return absent
    }
    global_ShadowActor33.prototype["delay"] = fun(ms: dynamic): dynamic {
        val receiver130: dynamic = js("this")
        return jsConstruct(global_Promise, arrayOf<dynamic>((fun(resolve: dynamic): dynamic {
            var id: dynamic = absent
            id = global_setTimeout((fun(): dynamic {
                receiver130.pending.delete(id)
                resolve(receiver130.alive)
                return absent
            }), ms)
            receiver130.pending["set"](id, resolve)
            return absent
        })))
    }
    global_ShadowActor33.prototype["open"] = fun(): dynamic {
        val receiver133: dynamic = js("this")
        return asyncAction asyncBody132@ {
            receiver133.mode((if (truth(receiver133.allowed())) "enter" else "idle"))
            val done: dynamic = awaitValue(receiver133.delay((if (truth(receiver133.allowed())) 620 else 0)))
            if (truth(done)) {
                receiver133.mode("idle")
                receiver133.schedule()
            }
            return@asyncBody132 done
            absent
        }
    }
    global_ShadowActor33.prototype["schedule"] = fun(): dynamic {
        val receiver135: dynamic = js("this")
        global_clearTimeout(receiver135.timer)
        if (truth((!truth(receiver135.allowed())))) {
            return absent
        }
        receiver135.timer = global_setTimeout((fun(): dynamic {
            if (truth((!truth(receiver135.allowed())))) {
                return absent
            }
            val gestures: dynamic = jsonValue("[\"look\",\"bow\",\"wave\",\"float\",\"celebrate\"]")
            receiver135.perform(gestures[global_Math.floor(jsMultiply(global_Math.random(), gestures.length))])
            receiver135.schedule()
            return absent
        }), jsAdd(6500, jsMultiply(global_Math.random(), 8000)))
        return absent
    }
    global_ShadowActor33.prototype["perform"] = fun(name: dynamic): dynamic {
        val receiver139: dynamic = js("this")
        if (truth((!truth(receiver139.allowed())))) {
            return absent
        }
        global_clearTimeout(receiver139.motionTimer)
        val map: dynamic = jsonValue("{\"think\":\"look\",\"train\":\"float\",\"flip\":\"float\",\"power\":\"celebrate\",\"walk\":\"look\"}")
        receiver139.mode(jsOr(map[name]) { name })
        receiver139.motionTimer = global_setTimeout((fun(): dynamic {
            if (truth(receiver139.alive)) {
                receiver139.mode("idle")
            }
            return absent
        }), 1600)
        return absent
    }
    global_ShadowActor33.prototype["pause"] = fun(): dynamic {
        val receiver143: dynamic = js("this")
        receiver143.paused = true
        global_clearTimeout(receiver143.timer)
        global_clearTimeout(receiver143.motionTimer)
        onPresent(receiver143.host) { value147 -> value147.classList.add("actor-paused33") }
        return absent
    }
    global_ShadowActor33.prototype["resume"] = fun(): dynamic {
        val receiver149: dynamic = js("this")
        if (truth((!truth(receiver149.alive)))) {
            return absent
        }
        receiver149.paused = false
        onPresent(receiver149.host) { value153 -> value153.classList.remove("actor-paused33") }
        receiver149.mode("idle")
        receiver149.schedule()
        return absent
    }
    global_ShadowActor33.prototype["close"] = fun(): dynamic {
        val receiver156: dynamic = js("this")
        return asyncAction asyncBody155@ {
            global_clearTimeout(receiver156.timer)
            global_clearTimeout(receiver156.motionTimer)
            receiver156.mode((if (truth(receiver156.allowed())) "exit" else "idle"))
            return@asyncBody155 receiver156.delay((if (truth(receiver156.allowed())) 380 else 0))
            absent
        }
    }
    global_ShadowActor33.prototype["destroy"] = fun(): dynamic {
        val receiver158: dynamic = js("this")
        receiver158.alive = false
        global_clearTimeout(receiver158.timer)
        global_clearTimeout(receiver158.motionTimer)
        if (true) {
            val iterator162: dynamic = iteratorOf(receiver158.pending)
            while (true) {
                val next163: dynamic = iterator162.next()
                if (truth(next163.done)) break
                var id: dynamic = absent
                var resolve: dynamic = absent
                val unpacked164: dynamic = fromIterable(next163.value)
                id = unpacked164[0]
                resolve = unpacked164[1]
                global_clearTimeout(id)
                resolve(false)
            }
        }
        receiver158.pending.clear()
        if (truth(receiver158.host)) {
            receiver158.host.innerHTML = ""
        }
        return absent
    }
    global_mountPersistentCompanion = (fun(host: dynamic, type: dynamic): dynamic {
        if (truth((!truth(host)))) {
            return null
        }
        val actor: dynamic = jsConstruct(global_ShadowActor33, arrayOf<dynamic>(host, type))
        actor.schedule()
        return actor
    })
    global_mountPanelCompanion = (fun(type: dynamic): dynamic {
        global_releaseCompanion()
        val host: dynamic = global_Dollar("#animatedCompanion")
        if (truth(host)) {
            global_activeCompanion = jsConstruct(global_ShadowActor33, arrayOf<dynamic>(host, type))
            global_activeCompanion["open"]()
        }
        val name: dynamic = global_Dollar("#companionName")
        if (truth(name)) {
            name.textContent = global_CompanionNames[type]
        }
        return absent
    })
    global_phraseTimer33 = 0
    global_phrases33 = jsonValue("{\"knight\":[\"Tu siguiente paso cuenta.\",\"La constancia forja tu rango.\",\"Una serie. Un avance.\"],\"warrior\":[\"Firme. Controla cada repetición.\",\"Tu técnica sostiene tu fuerza.\",\"Volvamos a la misión.\"],\"bear\":[\"Respira. También puedes descansar.\",\"Paso a paso, seguimos contigo.\",\"Recuperar también es avanzar.\"],\"insect\":[\"A tus órdenes. Comencemos.\",\"Un nuevo desafío te espera.\",\"Cada misión cuenta, cazador.\"],\"scientist\":[\"Tu historia avanza una página.\",\"Escribe hoy tu siguiente logro.\",\"Cada decisión construye tu camino.\"]}")
    global_openCoach = (fun(): dynamic {
        global_V33UI.openCoach()
        val intro: dynamic = global_Dollar("#modal .coach-intro")
        if (truth((!truth(intro)))) {
            return absent
        }
        intro.classList.add("shadow-showcase33")
        global_Dollar("#modal").querySelectorAll(".playback-speeds").forEach((fun(node: dynamic): dynamic {
            return node.remove()
        }))
        val host: dynamic = global_Dollar("#animatedCompanion")
        onPresent(host) { value184 -> value184.removeAttribute("style") }
        val heading: dynamic = global_Dollar("#companionName")
        if (truth(heading)) {
            val previous: dynamic = heading.parentElement
            val copy: dynamic = global_document.createElement("div")
            copy.className = "shadow-name33"
            copy.append(heading)
            intro.append(copy)
            if (truth((previous !== intro))) {
                previous.remove()
            }
            copy.insertAdjacentHTML("afterbegin", "<span class=\"eyebrow\">TU COMPAÑERO DE MISIÓN</span>")
            copy.insertAdjacentHTML("beforeend", "<span class=\"tag\">SOMBRA VINCULADA</span>")
        }
        intro.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<p id=\"shadowPhrase33\" class=\"shadow-phrase33\">", global_esc(global_phrases33[global_selectedCompanion()][0])), "</p>"))
        val section: dynamic = intro.closest(".coach-panel")
        if (truth(section)) {
            section.classList.add("coach-layout33")
            val left: dynamic = global_document.createElement("div")
            val right: dynamic = global_document.createElement("div")
            left.className = "shadow-column33"
            right.className = "coach-content33"
            if (true) {
                val iterator191: dynamic = iteratorOf(jsArray { array193 ->
                    appendAll(array193, section.children)
                })
                while (true) {
                    val next192: dynamic = iterator191.next()
                    if (truth(next192.done)) break
                    var node: dynamic = absent
                    node = next192.value
                    if (truth((node === intro))) {
                        left.append(node)
                    } else {
                        if (truth(jsAnd((!truth(node.matches(":scope>.eyebrow")))) { (!truth(node.classList.contains("eyebrow"))) })) {
                            right.append(node)
                        }
                    }
                }
            }
            section.append(left, right)
        }
        val oldDescription: dynamic = intro.querySelector(":scope>p:not(.shadow-phrase33)")
        onPresent(oldDescription) { value194 -> value194.remove() }
        onPresent(global_Dollar("#modal").querySelector(".automatic-note19")) { value195 -> value195.remove() }
        val s: dynamic = global_Visual33.selection()
        intro.style.setProperty("--shadow-scene33", jsAdd(jsAdd("url(\"", s.asset.path), "\")"))
        global_decorate33()
        global_schedulePhrase33()
        global_ModalViewport.fit()
        global_UI26.schedule()
        return absent
    })
    global_decorateBefore33 = global_UI26.decorate
    global_UI26.decorate = (fun(root: dynamic): dynamic {
        val receiver206: dynamic = js("this")
        global_decorateBefore33.call(receiver206, root)
        global_decorate33()
        return absent
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V33UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target210: dynamic = n
                val assigned212: dynamic = "SISTEMA DE SOMBRAS"
                target210.textContent = assigned212
                assigned212
            }
        }))
        global_applyTheme()
        global_Visual33.preload()
        global_decorate33()
        return absent
    })
    global_modal = (fun(html: dynamic): dynamic {
        global_clearTimeout(global_phraseTimer33)
        global_V33UI.modal(html)
        global_decorate33()
        return absent
    })
    global_Dollar("#modal").addEventListener("close", (fun(): dynamic {
        global_clearTimeout(global_phraseTimer33)
        global_Dollar("#modal").classList.remove("coach-window33")
        return absent
    }))
    global_document.addEventListener("visibilitychange", (fun(): dynamic {
        if (true) {
            val iterator215: dynamic = iteratorOf(arrayOf<dynamic>(global_activeCompanion, global_homeCompanion, global_missionCompanion))
            while (true) {
                val next216: dynamic = iterator215.next()
                if (truth(next216.done)) break
                var actor: dynamic = absent
                actor = next216.value
                if (truth(global_document.hidden)) {
                    onPresent(actor) { value217 -> value217.pause() }
                } else {
                    onPresent(actor) { value218 -> value218.resume() }
                }
            }
        }
        global_schedulePhrase33()
        return absent
    }))
    run { val owner220: dynamic = global_matchMedia("(prefers-reduced-motion: reduce)"); onPresent(owner220.addEventListener) { method219 -> invokeWith(method219, owner220, arrayOf<dynamic>("change", (fun(): dynamic {
        if (true) {
            val iterator221: dynamic = iteratorOf(arrayOf<dynamic>(global_activeCompanion, global_homeCompanion, global_missionCompanion))
            while (true) {
                val next222: dynamic = iterator221.next()
                if (truth(next222.done)) break
                var actor: dynamic = absent
                actor = next222.value
                if (truth(global_matchMedia("(prefers-reduced-motion: reduce)").matches)) {
                    onPresent(actor) { value223 -> value223.pause() }
                } else {
                    onPresent(actor) { value224 -> value224.resume() }
                }
            }
        }
        global_schedulePhrase33()
        return absent
    }))) } }
    global_window.addEventListener("pagehide", (fun(): dynamic {
        global_clearTimeout(global_phraseTimer33)
        if (true) {
            val iterator225: dynamic = iteratorOf(arrayOf<dynamic>(global_activeCompanion, global_homeCompanion, global_missionCompanion))
            while (true) {
                val next226: dynamic = iterator225.next()
                if (truth(next226.done)) break
                var actor: dynamic = absent
                actor = next226.value
                onPresent(actor) { value227 -> value227.pause() }
            }
        }
        return absent
    }))
    global_window.addEventListener("pageshow", (fun(): dynamic {
        if (truth((!truth(global_document.hidden)))) {
            if (true) {
                val iterator228: dynamic = iteratorOf(arrayOf<dynamic>(global_activeCompanion, global_homeCompanion, global_missionCompanion))
                while (true) {
                    val next229: dynamic = iterator228.next()
                    if (truth(next229.done)) break
                    var actor: dynamic = absent
                    actor = next229.value
                    onPresent(actor) { value230 -> value230.resume() }
                }
            }
            global_schedulePhrase33()
        }
        return absent
    }))
    global_evolutionPreview33 = jsonValue("{\"rank\":\"E\",\"collection\":\"hunter\",\"gender\":\"male\",\"scene\":0}")
    global_openDiagnostics20 = (fun(): dynamic {
        global_V33UI.openDiagnostics20()
        if (true) {
            val iterator245: dynamic = iteratorOf(global_Dollar("#modal").querySelectorAll("p"))
            while (true) {
                val next246: dynamic = iterator245.next()
                if (truth(next246.done)) break
                var p: dynamic = absent
                p = next246.value
                p.textContent = p.textContent.replace(jsRegExp("Versión (30|32)\\.0\\.", ""), "Versión 33.0.")
            }
        }
        return absent
    })
    global_window.HunterV33 = jsObject { obj251 ->
        obj251["Visual33"] = global_Visual33
        obj251["ShadowActor33"] = global_ShadowActor33
        obj251["phrases33"] = global_phrases33
        obj251["decorate33"] = global_decorate33
    }
    global_state.appVersion = 33
    global_save()
    global_applyTheme()
    global_render()
}
