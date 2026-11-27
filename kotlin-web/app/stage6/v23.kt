@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv23

import hunter.browser.*

@JsName("V23UI") private external var global_V23UI: dynamic
@JsName("gymBrowse23") private external var global_gymBrowse23: dynamic
@JsName("gymLevel23") private external var global_gymLevel23: dynamic
@JsName("gymFamily23") private external var global_gymFamily23: dynamic
@JsName("gymFields23") private external var global_gymFields23: dynamic
@JsName("openGym23") private external var global_openGym23: dynamic
@JsName("gymView23") private external var global_gymView23: dynamic
@JsName("listBefore23") private external var global_listBefore23: dynamic
@JsName("equipmentLabel23") private external var global_equipmentLabel23: dynamic
@JsName("profileForm") private external var global_profileForm: dynamic
@JsName("submitProfile") private external var global_submitProfile: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("allRoutines") private external var global_allRoutines: dynamic
@JsName("routinesIndex11") private external var global_routinesIndex11: dynamic
@JsName("routines") private external var global_routines: dynamic
@JsName("book") private external var global_book: dynamic
@JsName("exerciseCards") private external var global_exerciseCards: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("validateBackup") private external var global_validateBackup: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("answerCoach") private external var global_answerCoach: dynamic
@JsName("TrainingReference20") private external var global_TrainingReference20: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("Object") private external var global_Object: dynamic
@JsName("Training23") private external var global_Training23: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("E") private external var global_E: dynamic
@JsName("OwnRoutines18") private external var global_OwnRoutines18: dynamic
@JsName("ownLibrary11") private external var global_ownLibrary11: dynamic
@JsName("trainerPage11") private external var global_trainerPage11: dynamic
@JsName("routineMode") private external var global_routineMode: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("TrainingProgression19") private external var global_TrainingProgression19: dynamic
@JsName("options") private external var global_options: dynamic
@JsName("Adapt") private external var global_Adapt: dynamic
@JsName("GoalPlans") private external var global_GoalPlans: dynamic
@JsName("esc") private external var global_esc: dynamic
@JsName("exerciseList") private external var global_exerciseList: dynamic
@JsName("exerciseEquip") private external var global_exerciseEquip: dynamic
@JsName("Catalog") private external var global_Catalog: dynamic
@JsName("\$") private external var global_Dollar: dynamic
@JsName("Boolean") private external var global_Boolean: dynamic
@JsName("Array") private external var global_Array: dynamic
@JsName("companionDisplayName") private external var global_companionDisplayName: dynamic
@JsName("Runtime20") private external var global_Runtime20: dynamic
@JsName("openDiagnostics20") private external var global_openDiagnostics20: dynamic
@JsName("OfflineManifest20") private external var global_OfflineManifest20: dynamic
@JsName("modal") private external var global_modal: dynamic
@JsName("Offline20") private external var global_Offline20: dynamic
@JsName("ExercisePack23") private external var global_ExercisePack23: dynamic

internal fun installV23() {
    global_gymFields23 = fun(form: dynamic): dynamic {
        val equipment: dynamic = form.elements.equipment["value"]
        val block: dynamic = form.querySelector(".gym-equipment23")
        val load: dynamic = form.elements.load
        if (truth(block)) {
            block.hidden = (equipment !== "gym")
        }
        if (truth(load)) {
            load.disabled = (equipment !== "db")
            load.closest(".field").hidden = (equipment !== "db")
        }
        return absent
    }
    global_openGym23 = fun(): dynamic {
        global_gymBrowse23 = true
        global_ownLibrary11 = false
        global_trainerPage11 = dynamicValue(null)
        global_routineMode = "complete"
        global_tab = "routines"
        global_render()
        return absent
    }
    global_gymView23 = fun(): dynamic {
        val n: dynamic = nullableOr(global_gymLevel23) { global_TrainingProgression19.level(jsOr(global_state.profile) { js("({})") }) }
        val all: dynamic = global_allRoutines().filter((fun(r: dynamic): dynamic {
            return r.gymOnly23
        }))
        var list: dynamic = all.filter((fun(r: dynamic): dynamic {
            return (r.level === n)
        }))
        if (truth((global_gymFamily23 !== "all"))) {
            list = list.filter((fun(r: dynamic): dynamic {
                return (r.family === jsAdd("gym-", global_gymFamily23))
            }))
        }
        return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<button class=\"text-button\" onclick=\"HunterActions.exitGymBrowser()\">← Centro de entrenamiento</button><div class=\"page-heading\"><span class=\"eyebrow\">GIMNASIO · RUTINAS COMPLETAS</span><h1>Rutinas especializadas en gimnasio</h1><p class=\"muted\">Barras, poleas, máquinas y sesiones secuenciales con preparación y descansos.</p></div><div class=\"routine-level-tabs\" role=\"tablist\" aria-label=\"Niveles de gimnasio\">", global_OwnRoutines18.names.map((fun(name: dynamic, i: dynamic): dynamic {
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<button role=\"tab\" aria-selected=\"", (i === n)), "\" class=\""), (if (truth((i === n))) "active" else "")), "\" onclick=\"HunterActions.setGymLevel("), i), ")\"><b>"), name), "</b><small>"), all.filter((fun(r: dynamic): dynamic {
                return (r.level === i)
            })).length), " sesiones</small></button>")
        })).join("")), "</div><label>Especialidad<select onchange=\"HunterActions.setGymFamily(this)\">"), global_options(jsObject { obj47 ->
            obj47["all"] = "Todas las especialidades"
            copyFields(obj47, global_Object.fromEntries(global_Object.entries(global_Training23.gymFamilies).map((fun(arg48: dynamic): dynamic {
                var k: dynamic = absent
                var v: dynamic = absent
                val unpacked49: dynamic = fromIterable(arg48)
                k = unpacked49[0]
                v = unpacked49[1]
                return arrayOf<dynamic>(k, v.name)
            }))))
        }, global_gymFamily23)), "</select></label>"), (if (truth((onPresent(global_state.profile) { value50 -> value50.equipment } !== "gym"))) "<div class=\"notice\"><p>Configura el gimnasio y su equipo en Perfil para asignar estas rutinas a tus días.</p><button type=\"button\" class=\"btn\" onclick=\"HunterActions.exitGym()\">Configurar gimnasio</button></div>" else "")), "<div class=\"stack gym-routines23\">"), list.map((fun(r: dynamic): dynamic {
            val compatible: dynamic = global_Adapt.routineCompatible(r, global_GoalPlans.adjusted(global_state.profile))
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<article class=\"card routine-card18\"><span class=\"eyebrow\">", global_esc(global_OwnRoutines18.names[r.level])), " · "), r.items.length), " ejercicios</span><h3>"), global_esc(r.name)), "</h3><p class=\"footnote\">"), r.gymRequirements23.map((fun(k: dynamic): dynamic {
                return global_Training23.gymEquipment[k]
            })).join(" · ")), "</p><ul class=\"routine-preview18\">"), r.items.slice(0, 4).map((fun(x: dynamic): dynamic {
                return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<li><b>", x.sets), " × "), x.target), (if (truth((global_E.unitOf(x) === "sec"))) " s" else "")), "</b><span>"), global_esc(global_E.byId(x.id).name)), "</span></li>")
            })).join("")), "</ul><p>"), r.items.reduce((fun(sum: dynamic, x: dynamic): dynamic {
                return jsAdd(sum, x.sets)
            }), 0)), " series · ≈ "), r.minutes), " min · "), (if (truth(compatible)) "Coincide con tu perfil" else "Consulta la ficha y revisa los filtros del perfil")), "</p><button type=\"button\" class=\"btn primary full\" onclick=\"routineDetail('"), r.id), "')\">Abrir rutina completa</button></article>")
        })).join("")), "</div>")
    }
    global_equipmentLabel23 = fun(e: dynamic): dynamic {
        return (if (truth(e.gymOnly23)) e.gymRequirements23.map((fun(k: dynamic): dynamic {
            return global_Training23.gymEquipment[k]
        })).join(" + ") else (if (truth(e.requirements.length)) e.requirements.map((fun(k: dynamic): dynamic {
            return global_Catalog.equipment[k]
        })).join(" + ") else "Sin equipo especial"))
    }
    global_V23UI = jsObject { obj1 ->
        obj1["profileForm"] = global_profileForm
        obj1["submitProfile"] = global_submitProfile
        obj1["currentCycle"] = global_currentCycle
        obj1["allRoutines"] = global_allRoutines
        obj1["routinesIndex11"] = global_routinesIndex11
        obj1["routines"] = global_routines
        obj1["book"] = global_book
        obj1["exerciseCards"] = global_exerciseCards
        obj1["technique"] = global_technique
        obj1["validateBackup"] = global_validateBackup
        obj1["render"] = global_render
        obj1["answerCoach"] = global_answerCoach
    }
    global_TrainingReference20.chapters.push(jsonValue("{\"title\":\"Gimnasio: equipo, asiento y seguros\",\"text\":\"La selección exige el equipo que marques. Antes de una máquina, comprueba sus apoyos y el recorrido sin carga; en barras, verifica soportes y seguros. La carga que registres en kg corresponde a lo usado en esa serie, no a una estimación de la app.\",\"source\":\"https://www.acefitness.org/resources/everyone/exercise-library/equipment/weight-machines-selectorized/\"}"), jsonValue("{\"title\":\"Prensa: espalda apoyada y rodillas controladas\",\"text\":\"Mantén espalda y pelvis apoyadas, talones en la plataforma y un recorrido que no despegue la pelvis. La extensión termina con rodillas relajadas; evita bloquearlas y devuelve la plataforma con control.\",\"source\":\"https://www.acefitness.org/resources/everyone/exercise-library/154/seated-leg-press/\"}"), jsonValue("{\"title\":\"Abducción lateral: dos piernas sin cruces\",\"text\":\"Alinea cabeza y columna y conserva la pelvis de lado. Eleva solo la pierna superior con rodilla alargada; vuelve despacio antes de repetir. La ilustración diferencia ambos lados y mantiene el recorrido limitado.\",\"source\":\"https://www.acefitness.org/resources/everyone/exercise-library/38/side-lying-hip-abduction/\"}"), jsonValue("{\"title\":\"Progresión: más control antes de más cantidad\",\"text\":\"Una variante lenta o con pausa cambia la demanda aunque tenga menos repeticiones. El sistema compara series completas con esfuerzo registrado y revisa la semana. Las series pendientes no permiten aumentar la dosis, y una reducción elegida se conserva.\",\"source\":\"https://www.acsm.org/wp-content/uploads/2026/03/Resistance-Training-Position-Stand-infographic.pdf\"}"), jsonValue("{\"title\":\"Tres niveles y adaptación individual\",\"text\":\"Básico usa patrones bilaterales y apoyos; intermedio incorpora control y trabajo unilateral; avanzado añade palancas, coordinación y cadencias más exigentes. Las indicaciones y restricciones del perfil se aplican antes de la dificultad. Las fuentes educativas no sustituyen una evaluación individual de una lesión.\",\"source\":\"https://www.acefitness.org/resources/everyone/exercise-library/\"}"))
    global_gymBrowse23 = false
    global_gymLevel23 = dynamicValue(null)
    global_gymFamily23 = "all"
    global_profileForm = (fun(arg12: dynamic): dynamic {
        var initial: dynamic = absent
        initial = definedOr(arg12) { false }
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V23UI.profileForm(initial)
        val f: dynamic = box.querySelector("form")
        val p: dynamic = jsOr(global_state.profile) { js("({})") }
        f.elements.equipment.insertAdjacentHTML("beforeend", jsAdd(jsAdd("<option value=\"gym\" ", (if (truth((p.equipment === "gym"))) "selected" else "")), ">Gimnasio · barras, poleas y máquinas</option>"))
        f.elements.equipment["value"] = jsOr(p.equipment) { "body" }
        f.elements.equipment.setAttribute("onchange", "gymFields23(this.form)")
        f.elements.equipment.closest(".fields").insertAdjacentHTML("afterend", jsAdd(jsAdd("<fieldset class=\"gym-equipment23 card\"><legend>Equipo disponible en tu gimnasio</legend><p class=\"footnote\">Marca el material que puedes utilizar. Las rutinas se filtran por esta selección.</p><div class=\"gym-gear-grid23\">", global_Object.entries(global_Training23.gymEquipment).map((fun(arg19: dynamic): dynamic {
            var key: dynamic = absent
            var name: dynamic = absent
            val unpacked20: dynamic = fromIterable(arg19)
            key = unpacked20[0]
            name = unpacked20[1]
            return jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<label><input type=\"checkbox\" name=\"gym_", key), "\" "), (if (truth(dynamicValue(jsOr(p.gymEquipment23) { js("[]") }).includes(key))) "checked" else "")), "><span>"), name), "</span></label>")
        })).join("")), "</div></fieldset>"))
        global_gymFields23(f)
        return box.innerHTML
    })
    global_submitProfile = (fun(ev: dynamic): dynamic {
        val f: dynamic = ev.target
        val gear: dynamic = global_Object.keys(global_Training23.gymEquipment).filter((fun(k: dynamic): dynamic {
            return f.elements[jsAdd("gym_", k)].checked
        }))
        val gym: dynamic = (f.elements.equipment["value"] === "gym")
        val before: dynamic = global_state.profile
        if (truth(jsAnd(gym) { (!truth(gear.length)) })) {
            ev.preventDefault()
            return global_toast("Marca el equipo disponible en tu gimnasio.")
        }
        global_V23UI.submitProfile(ev)
        if (truth(jsAnd(global_state.profile) { (global_state.profile !== before) })) {
            global_state.profile.gymEquipment23 = gear
            if (truth(gym)) {
                if (truth((!truth(onPresent(global_state.profile.equipmentModes3880) { value25 -> value25.includes("db") })))) {
                    global_state.profile.load = 0
                }
                global_state.profile.bench = gear.includes("bench")
            }
            global_currentCycle()
            global_save()
            global_render()
        }
        return absent
    })
    global_currentCycle = (fun(): dynamic {
        var blockMode22: dynamic = absent
        var settings: dynamic = absent
        val c: dynamic = global_V23UI.currentCycle()
        if (truth(jsOr((!truth(c))) { (!truth(global_state.profile)) })) {
            return c
        }
        val unpacked32: dynamic = global_state.profile
        blockMode22 = unpacked32["blockMode22"]
        val remaining33 = copyFields(js("({})"), unpacked32)
        removeKey(remaining33, "blockMode22")
        settings = remaining33
        val key: dynamic = global_JSON.stringify(arrayOf<dynamic>(23, settings))
        if (truth((c.planningKey23 !== key))) {
            val previous: dynamic = global_state.cycles.filter((fun(x: dynamic): dynamic {
                return jsLess(x.start, c.start)
            })).at((-numeric(1)))
            global_Training23.assign(c, global_state.profile, global_state.logs, global_E.dateKey(), previous)
            c.planningKey23 = key
            c.profile = global_JSON.parse(global_JSON.stringify(global_state.profile))
            global_save()
        }
        return c
    })
    global_allRoutines = (fun(): dynamic {
        val homes: dynamic = global_V23UI.allRoutines()
        return jsArray { array40 ->
            appendAll(array40, homes)
            appendAll(array40, global_Training23.gymRoutines.map((fun(r: dynamic): dynamic {
                val p: dynamic = jsObject { obj41 ->
                    copyFields(obj41, global_state.profile)
                    obj41["difficulty"] = global_OwnRoutines18.keys[r.level]
                }
                val items: dynamic = (if (truth(global_state.profile)) global_Training23.adjust(r, p) else r.items)
                return jsObject { obj42 ->
                    copyFields(obj42, r)
                    obj42["items"] = items
                    obj42["minutes"] = global_OwnRoutines18.estimate(items)
                }
            })))
        }
    })
    global_routinesIndex11 = (fun(): dynamic {
        return global_V23UI.routinesIndex11().replace("<div class=\"section-title\"><h2>Entrenadores de referencia", jsAdd(jsAdd("<button type=\"button\" class=\"academy-banner gym-banner23\" onclick=\"openGym23()\"><span>EQUIPAMIENTO DE GIMNASIO</span><b>Rutinas especializadas en gimnasio</b><small>", global_Training23.gymRoutines.length), " sesiones completas · básico, intermedio y avanzado →</small></button><div class=\"section-title\"><h2>Entrenadores de referencia"))
    })
    global_routines = (fun(): dynamic {
        return (if (truth(global_gymBrowse23)) global_gymView23() else global_V23UI.routines())
    })
    global_listBefore23 = global_exerciseList
    global_exerciseList = (fun(): dynamic {
        val equip: dynamic = global_exerciseEquip
        if (truth(jsOr((equip === "gym")) { dynamicValue(jsonValue("[\"barbell\",\"cable\",\"machine\",\"smith\",\"cardio\"]")).includes(equip) })) {
            global_exerciseEquip = "all"
            val list: dynamic = global_listBefore23()
            global_exerciseEquip = equip
            return list.filter((fun(e: dynamic): dynamic {
                return jsAnd(e.gymOnly23) { jsOr((equip === "gym")) { e.gymRequirements23.includes(equip) } }
            }))
        }
        return global_listBefore23().filter((fun(e: dynamic): dynamic {
            return jsOr((equip !== "body")) { (!truth(e.gymOnly23)) }
        }))
    })
    global_book = (fun(): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V23UI.book()
        val select: dynamic = box.querySelector("select[onchange*=\"HunterActions.filterEquipment\"]")
        onPresent(select) { value61 -> value61.insertAdjacentHTML("beforeend", global_options(jsonValue("{\"gym\":\"Gimnasio · todo el equipo\",\"barbell\":\"Barras\",\"cable\":\"Poleas\",\"machine\":\"Máquinas\",\"smith\":\"Smith\",\"cardio\":\"Cinta o ergómetro\"}"), global_exerciseEquip)) }
        return box.innerHTML
    })
    global_exerciseCards = (fun(list: dynamic): dynamic {
        val box: dynamic = global_document.createElement("div")
        box.innerHTML = global_V23UI.exerciseCards(list)
        if (true) {
            val iterator68: dynamic = iteratorOf(dynamicValue(jsArray { array71 ->
                appendAll(array71, box.querySelectorAll(".exercise-card"))
            }).entries())
            while (true) {
                val next69: dynamic = iterator68.next()
                if (truth(next69.done)) break
                var i: dynamic = absent
                var card: dynamic = absent
                val unpacked72: dynamic = fromIterable(next69.value)
                i = unpacked72[0]
                card = unpacked72[1]
                val p: dynamic = card.querySelector("p")
                if (truth(p)) {
                    p.textContent = global_equipmentLabel23(list[i])
                }
            }
        }
        return box.innerHTML
    })
    global_technique = (fun(id: dynamic): dynamic {
        global_V23UI.technique(id)
        val e: dynamic = global_E.byId(id)
        val tag: dynamic = global_Dollar("#modal .tech-tags span")
        if (truth(tag)) {
            tag.textContent = global_equipmentLabel23(e)
        }
        if (truth((e.equipment === "gym"))) {
            global_Dollar("#modal").insertAdjacentHTML("beforeend", "<p class=\"footnote\">Registra en kg la carga total usada en esta serie. Ajusta asiento, apoyos y seguros antes de comenzar.</p>")
        }
        return absent
    })
    global_validateBackup = (fun(`data`: dynamic): dynamic {
        if (truth((!truth(global_V23UI.validateBackup(`data`))))) {
            return false
        }
        return dynamicValue(jsArray { array84 ->
            array84.push(`data`.profile)
            appendAll(array84, `data`.cycles.map((fun(c: dynamic): dynamic {
                return c.profile
            })))
        }).filter(global_Boolean).every((fun(p: dynamic): dynamic {
            return jsOr((p.gymEquipment23 === absent)) { jsAnd(jsAnd(global_Array.isArray(p.gymEquipment23)) { jsAtMost(p.gymEquipment23.length, 6) }) { p.gymEquipment23.every((fun(k: dynamic): dynamic {
                return global_Object.hasOwn(global_Training23.gymEquipment, k)
            })) } }
        }))
    })
    global_answerCoach = (fun(q: dynamic): dynamic {
        if (truth(dynamicValue(jsRegExp("gimnasio|máquina|maquina|polea|barra", "i")).test(q))) {
            return jsAdd(jsAdd(jsAdd(global_companionDisplayName(), ": Hay "), global_Training23.gymRoutines.length), " rutinas completas de gimnasio en tres niveles. La selección usa tu equipo, objetivo, nivel y restricciones. La revisión semanal necesita series confirmadas y esfuerzo registrado antes de aumentar la dosis o cambiar de variante. Configura el equipo en Perfil y abre Rutinas especializadas en gimnasio.")
        }
        return global_V23UI.answerCoach(q)
    })
    global_render = (fun(): dynamic {
        if (truth(global_Runtime20.initializing)) {
            return absent
        }
        global_V23UI.render()
        global_document.querySelectorAll(".brand small").forEach((fun(n: dynamic): dynamic {
            return run {
                val target89: dynamic = n
                val assigned91: dynamic = "EL DESPERTAR · V23"
                target89.textContent = assigned91
                assigned91
            }
        }))
        return absent
    })
    global_openDiagnostics20 = (fun(): dynamic {
        val bytes: dynamic = jsOr(global_OfflineManifest20.bytes) { 0 }
        global_modal(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd(jsAdd("<section><h2>Estado del contenido local · V23</h2><p>", global_E.exercises.length), " fichas · "), global_Catalog.routines.length), " rutinas completas · "), global_Offline20.count()), " demostraciones HD.</p><p>"), global_ExercisePack23.baseIds.length), " movimientos nuevos y 200 variantes de cadencia. "), global_Training23.gymRoutines.length), " rutinas de gimnasio en tres niveles.</p><p>Medios locales: "), dynamicValue(jsDivide(bytes, 1000000)).toFixed(1)), " MB. La biblioteca, los vectores, las reglas y el estimador de esfuerzo funcionan sin conexión.</p><p>"), global_Runtime20.errors.length), " errores de interfaz registrados durante esta apertura.</p>"), global_Runtime20.errors.map((fun(x: dynamic): dynamic {
            return jsAdd(jsAdd(jsAdd(jsAdd("<p>", global_esc(x.source)), " · "), global_esc(x.message)), "</p>")
        })).join("")), "<p class=\"footnote\">La progresión requiere registros confirmados. El filtro de lesiones usa tus restricciones e indicaciones declaradas; no diagnostica ni prescribe rehabilitación.</p></section>"))
        return absent
    })
    global_state.appVersion = 23
    global_currentCycle()
    global_save()
    global_render()
}
