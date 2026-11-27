@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installhunterquality3864

import hunter.browser.*

@JsName("technique") private external var global_technique: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("vectorSeek") private external var global_vectorSeek: dynamic
@JsName("Date") private external var global_Date: dynamic
@JsName("Blob") private external var global_Blob: dynamic
@JsName("URL") private external var global_URL: dynamic
@JsName("navigator") private external var global_navigator: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("window") private external var global_window: dynamic
@JsName("document") private external var global_document: dynamic

internal fun installHunterQuality3864() {
    dynamicValue((fun(root: dynamic, doc: dynamic): dynamic {
        var flags: dynamic = absent
        var STORAGE: dynamic = absent
        var toolStats: dynamic = absent
        var activeId: dynamic = absent
        var activeOutput: dynamic = absent
        var label: dynamic = absent
        var numberFrame: dynamic = absent
        var refresh: dynamic = absent
        var readReports: dynamic = absent
        var persistReports: dynamic = absent
        var make: dynamic = absent
        var activateControls: dynamic = absent
        label = fun(id: dynamic): dynamic {
            val e: dynamic = (if (truth(jsAnd((js("typeof E") !== "undefined")) { global_E.byId })) global_E.byId(id) else null)
            return jsOr(onPresent(e) { value5 -> value5.name }) { id }
        }
        numberFrame = fun(): dynamic {
            return (if (truth(jsAnd((js("typeof vectorSession") !== "undefined")) { global_vectorSession })) global_Math.max(0, global_Math.min(47, global_Math.floor(jsOr(global_Number(global_vectorSession.frame)) { 0 }))) else 0)
        }
        refresh = fun(): dynamic {
            if (truth(jsAnd(activeOutput) { activeOutput.isConnected })) {
                activeOutput.textContent = jsAdd(jsAdd("Fotograma ", jsAdd(numberFrame(), 1)), " de 48")
            }
            return absent
        }
        readReports = fun(): dynamic {
            try {
                val v: dynamic = global_JSON.parse(jsOr(root.localStorage.getItem(STORAGE)) { "[]" })
                return (if (truth(global_Array.isArray(v))) v.filter((fun(x: dynamic): dynamic {
                    return jsAnd(x) { (jsTypeOf(x) === "object") }
                })).slice((-numeric(200))) else js("[]"))
            } catch (unusedError: dynamic) {
                return js("[]")
            }
            return absent
        }
        persistReports = fun(`data`: dynamic): dynamic {
            try {
                root.localStorage.setItem(STORAGE, global_JSON.stringify(`data`))
                return true
            } catch (unusedError: dynamic) {
                return false
            }
            return absent
        }
        make = fun(tag: dynamic, cls: dynamic, text: dynamic): dynamic {
            val x: dynamic = doc.createElement(tag)
            if (truth(cls)) {
                x.className = cls
            }
            if (truth((text !== absent))) {
                x.textContent = text
            }
            return x
        }
        activateControls = fun(id: dynamic, panel: dynamic): dynamic {
            var step: dynamic = absent
            step = fun(n: dynamic): dynamic {
                if (truth(jsOr(jsOr((js("typeof vectorSession") === "undefined")) { (!truth(global_vectorSession)) }) { (global_vectorSession.id !== id) })) {
                    return absent
                }
                global_vectorSeek(jsMod(jsAdd(jsAdd(numberFrame(), n), 48), 48))
                run {
                    val target41: dynamic = toolStats
                    val previous43: dynamic = numeric(target41.steps)
                    target41.steps = jsAdd(previous43, 1)
                }
                refresh()
                return absent
            }
            val target: dynamic = panel.querySelector("#vectorCanvas")
            if (truth((!truth(target)))) {
                return absent
            }
            activeId = id
            run {
                val target26: dynamic = toolStats
                val previous28: dynamic = numeric(target26.views)
                target26.views = jsAdd(previous28, 1)
            }
            val flag: dynamic = jsOr(flags[id]) { 0 }
            val note: dynamic = make("div", "hunter-quality-badge3864")
            val heading: dynamic = make("strong", "", "Calidad de la demostración")
            val message: dynamic = make("span", "", (if (truth((flag === 3))) "Revisión pendiente: modelo con articulaciones principales estáticas y una variación importante de geometría en 2D." else (if (truth((flag === 1))) "Movimiento principal mínimo o estático. Puede ser una postura sostenida o un gesto localizado; consulta la explicación técnica." else (if (truth((flag === 2))) "Revisión pendiente: variación importante de la longitud aparente de un segmento en el modelo 2D." else "Animación vectorial disponible. El dibujo es orientativo; su precisión técnica no está certificada."))))
            note.append(heading, message)
            val toolbar: dynamic = make("div", "hunter-controls3864")
            val back: dynamic = make("button", "btn ghost hunter-step3864", "◀ Fotograma")
            back.type = "button"
            back.title = "Fotograma anterior"
            val index: dynamic = make("output", "hunter-frame3864", "Fotograma 1 de 48")
            index.setAttribute("aria-live", "off")
            val next: dynamic = make("button", "btn ghost hunter-step3864", "Fotograma ▶")
            next.type = "button"
            next.title = "Fotograma siguiente"
            val reset: dynamic = make("button", "btn ghost hunter-reset3864", "Volver al inicio")
            reset.type = "button"
            back.addEventListener("click", (fun(): dynamic {
                return step((-numeric(1)))
            }))
            next.addEventListener("click", (fun(): dynamic {
                return step(1)
            }))
            reset.addEventListener("click", (fun(): dynamic {
                if (truth(jsAnd((js("typeof vectorSession") !== "undefined")) { (onPresent(global_vectorSession) { value44 -> value44.id } === id) })) {
                    global_vectorSeek(0)
                }
                return absent
            }))
            toolbar.append(back, index, next, reset)
            panel.insertBefore(note, target)
            panel.insertBefore(toolbar, target)
            activeOutput = index
            refresh()
            val schematic: dynamic = panel.querySelector(".catalog-motion46")
            if (truth(schematic)) {
                val technical: dynamic = make("p", "hunter-technical3864", "Demostración técnica original · postura, apoyos y equipo")
                schematic.parentElement.insertBefore(technical, schematic)
            }
            val compare: dynamic = panel.querySelector(".hunter-compare3863")
            if (truth(compare)) {
                val description: dynamic = compare.querySelector(".footnote")
                if (truth(description)) {
                    description.textContent = "Comparación ilustrativa basada en los mismos fotogramas. Se mantiene visible el esquema técnico original para comprobar apoyos, equipos y detalles."
                }
                val status: dynamic = compare.querySelector(".hunter-compare-status3863")
                if (truth(status)) {
                    status.setAttribute("role", "status")
                }
            }
            val disclosure: dynamic = make("details", "hunter-report3864")
            val summary: dynamic = make("summary", "", "¿Detectaste un movimiento incorrecto? Registrar incidencia")
            val form: dynamic = make("div", "hunter-report-body3864")
            val title: dynamic = make("p", "", "La incidencia se guarda solo en este dispositivo. Puedes exportar el informe para revisar cada ejercicio; no modifica su técnica.")
            val select: dynamic = make("select", "", absent)
            select.setAttribute("aria-label", "Tipo de incidencia")
            dynamicValue(jsonValue("[[\"movimiento\",\"El movimiento no coincide\"],[\"postura\",\"Postura o apoyo incorrecto\"],[\"material\",\"Equipo o accesorio ausente\"],[\"estatica\",\"Animación parece estática\"],[\"otro\",\"Otro problema visual\"]]")).forEach((fun(arg47: dynamic): dynamic {
                var v: dynamic = absent
                var t: dynamic = absent
                val unpacked48: dynamic = fromIterable(arg47)
                v = unpacked48[0]
                t = unpacked48[1]
                val opt: dynamic = make("option", "", t)
                opt["value"] = v
                select.append(opt)
                return absent
            }))
            val description: dynamic = make("textarea", "", absent)
            description.rows = 2
            description.maxLength = 300
            description.placeholder = "Detalles opcionales para identificar el problema"
            description.setAttribute("aria-label", "Detalles de la incidencia")
            val msg: dynamic = make("p", "hunter-report-status3864", "")
            msg.setAttribute("role", "status")
            val save: dynamic = make("button", "btn", "Guardar incidencia")
            save.type = "button"
            val exportBtn: dynamic = make("button", "btn ghost", "Exportar informes")
            exportBtn.type = "button"
            save.addEventListener("click", (fun(): dynamic {
                val reports: dynamic = readReports()
                val entry: dynamic = jsObject { obj61 ->
                    obj61["id"] = id
                    obj61["name"] = label(id)
                    obj61["type"] = select["value"]
                    obj61["notes"] = description["value"].trim()
                    obj61["createdAt"] = jsConstruct(global_Date, js("[]")).toISOString()
                    obj61["appVersion"] = "38.64"
                }
                val idx: dynamic = reports.findIndex((fun(x: dynamic): dynamic {
                    return jsAnd((x.id === id)) { (x.type === entry.type) }
                }))
                if (truth(jsAtLeast(idx, 0))) {
                    reports.splice(idx, 1)
                }
                reports.push(entry)
                if (truth(persistReports(reports))) {
                    msg.textContent = "Incidencia guardada en el dispositivo."
                    run {
                        val target64: dynamic = toolStats
                        val previous66: dynamic = numeric(target64.reports)
                        target64.reports = jsAdd(previous66, 1)
                    }
                } else {
                    msg.textContent = "No se pudo guardar en este navegador. Prueba a exportar o copiar el reporte."
                }
                return absent
            }))
            exportBtn.addEventListener("click", (fun(): dynamic {
                val reports: dynamic = readReports()
                if (truth((!truth(reports.length)))) {
                    msg.textContent = "Aún no hay incidencias guardadas."
                    return absent
                }
                val contents: dynamic = global_JSON.stringify(jsObject { obj71 ->
                    obj71["project"] = "Hunter System"
                    obj71["version"] = "38.64"
                    obj71["items"] = reports
                }, null, 2)
                try {
                    val blob: dynamic = jsConstruct(global_Blob, arrayOf<dynamic>(arrayOf<dynamic>(contents), jsonValue("{\"type\":\"application/json\"}")))
                    val url: dynamic = global_URL.createObjectURL(blob)
                    val a: dynamic = doc.createElement("a")
                    a.href = url
                    a.download = "Hunter_incidencias_animaciones.json"
                    doc.body.append(a)
                    a.click()
                    a.remove()
                    root.setTimeout((fun(): dynamic {
                        return global_URL.revokeObjectURL(url)
                    }), 2500)
                    msg.textContent = "Descarga solicitada. Si tu navegador no la admite, utiliza Copiar informe."
                } catch (unusedError: dynamic) {
                    msg.textContent = "Descarga no disponible. Usa Copiar informe."
                }
                return absent
            }))
            val copy: dynamic = make("button", "btn ghost", "Copiar informe")
            copy.type = "button"
            copy.addEventListener("click", (fun(): dynamic {
                return asyncAction asyncBody82@ {
                    val contents: dynamic = global_JSON.stringify(jsObject { obj83 ->
                        obj83["project"] = "Hunter System"
                        obj83["items"] = readReports()
                    }, null, 2)
                    try {
                        awaitValue(global_navigator.clipboard.writeText(contents))
                        msg.textContent = "Informe copiado al portapapeles."
                    } catch (unusedError: dynamic) {
                        msg.textContent = "No se pudo copiar. Utiliza Exportar informes."
                    }
                    absent
                }
            }))
            val actions: dynamic = make("div", "hunter-report-buttons3864")
            actions.append(save, exportBtn, copy)
            form.append(title, select, description, actions, msg)
            disclosure.append(summary, form)
            panel.append(disclosure)
            return absent
        }
        if (truth(jsOr(jsOr(root.HunterQuality3864) { (js("typeof technique") !== "function") }) { (js("typeof vectorSeek") !== "function") })) {
            return absent
        }
        flags = jsonValue("{\"hs379-isobiceps\":1,\"hs379-palms\":1,\"new46-abwheel\":2,\"new46-airplanefree\":2,\"new46-ankleevert\":1,\"new46-ankleinvert\":1,\"new46-bandd1\":2,\"new46-banddiagonal\":2,\"new46-bander\":2,\"new46-bandir\":2,\"new46-bandplantar\":1,\"new46-bandpullapart\":2,\"new46-bigtoestretch\":1,\"new46-bottomclean\":2,\"new46-cableer90\":2,\"new46-chinup\":2,\"new46-commandopull\":2,\"new46-copenhagen\":1,\"new46-crosscabletri\":2,\"new46-crossshoulder\":2,\"new46-cubanpress\":2,\"new46-cubanrotation\":2,\"new46-deepshift\":2,\"new46-faceer\":2,\"new46-forearmlever\":1,\"new46-goodmorning\":2,\"new46-halo\":2,\"new46-hangclean\":2,\"new46-hangingwiper\":2,\"new46-hipcarquad\":2,\"new46-jefferson\":2,\"new46-kneelchop\":2,\"new46-kneellift\":2,\"new46-laddercarioca\":2,\"new46-landminetwist\":2,\"new46-lateralhurdle\":2,\"new46-lsit\":1,\"new46-mbcatch\":2,\"new46-mbrotation\":2,\"new46-meadowsrow\":2,\"new46-mediannerve\":2,\"new46-overheadsquat\":2,\"new46-pallofwalk\":2,\"new46-pelvictilt\":1,\"new46-peronealnerve\":1,\"new46-pivotlunge\":2,\"new46-pronehipir\":2,\"new46-reverseclam\":2,\"new46-ringsrotation\":2,\"new46-ringturnout\":1,\"new46-ropealternating\":2,\"new46-ropecircles\":2,\"new46-rotationalrow\":2,\"new46-seatedhinge\":2,\"new46-seatedpallof\":2,\"new46-shortfoot\":1,\"new46-shouldercar\":2,\"new46-shuttle\":2,\"new46-sidecrow\":2,\"new46-sidepallof\":2,\"new46-sitthrough\":2,\"new46-supineshoulderir\":2,\"new46-tdrill\":2,\"new46-tendonglides\":1,\"new46-thumbopposition\":1,\"new46-toetowel\":1,\"new46-toeyoga\":1,\"new46-tricepsstretch\":1,\"new46-trxfly\":2,\"new46-trxhighrow\":2,\"new46-tucklever\":1,\"new46-turkish\":2,\"new46-turnjump\":2,\"new46-ulnarnerve\":2,\"new46-wristcurl\":1,\"new46-wristdeviation\":1,\"new46-wristexte\":1,\"new46-zercher\":2}")
        STORAGE = "hunter-motion-incidents-3864"
        toolStats = jsonValue("{\"views\":0,\"steps\":0,\"reports\":0}")
        val originalTechnique: dynamic = global_technique
        val originalPaint: dynamic = global_vectorPaint
        activeId = dynamicValue(null)
        activeOutput = dynamicValue(null)
        global_vectorPaint = (fun(): dynamic {
            val receiver12: dynamic = js("this")
            val callArgs13: dynamic = js("arguments")
            val `value`: dynamic = originalPaint.apply(receiver12, callArgs13)
            refresh()
            return `value`
        })
        global_technique = (fun(id: dynamic): dynamic {
            val receiver88: dynamic = js("this")
            val callArgs89: dynamic = js("arguments")
            val result: dynamic = originalTechnique.apply(receiver88, callArgs89)
            if (truth(jsOr((jsTypeOf(id) !== "string")) { (!truth(dynamicValue(jsRegExp("^(new46-|hs379-)", "")).test(id))) })) {
                return result
            }
            val panel: dynamic = doc.querySelector("#modal .motion-card18")
            if (truth(panel)) {
                activateControls(id, panel)
            }
            return result
        })
        root.HunterQuality3864 = global_Object.freeze(jsObject { obj92 ->
            obj92["audit"] = (fun(): dynamic {
                return jsonValue("{\"modelsExtended\":200,\"flagsStatic\":22,\"flagsGeometry\":56,\"flagged\":78,\"techniqueVerified\":false}")
            })
            obj92["stats"] = (fun(): dynamic {
                return jsObject { obj93 ->
                    copyFields(obj93, toolStats)
                }
            })
            obj92["saved"] = (fun(): dynamic {
                return readReports().length
            })
        })
        return absent
    }))(global_window, global_document)
}
