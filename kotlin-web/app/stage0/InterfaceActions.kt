@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installactions

import hunter.browser.*

@JsName("window") private external var global_window: dynamic
@JsName("AsterionBridge3872") private external var global_AsterionBridge3872: dynamic
@JsName("builderItems") private external var global_builderItems: dynamic
@JsName("builderRows") private external var global_builderRows: dynamic
@JsName("creatorFilter") private external var global_creatorFilter: dynamic
@JsName("document") private external var global_document: dynamic
@JsName("creatorVideoCards") private external var global_creatorVideoCards: dynamic
@JsName("detailedMode") private external var global_detailedMode: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("bodyResults") private external var global_bodyResults: dynamic
@JsName("go") private external var global_go: dynamic
@JsName("technique") private external var global_technique: dynamic
@JsName("askCoach") private external var global_askCoach: dynamic
@JsName("saveCheckin") private external var global_saveCheckin: dynamic
@JsName("exerciseCategory") private external var global_exerciseCategory: dynamic
@JsName("exerciseEquip") private external var global_exerciseEquip: dynamic
@JsName("refreshExercises") private external var global_refreshExercises: dynamic
@JsName("exerciseLevel") private external var global_exerciseLevel: dynamic
@JsName("exerciseMuscle") private external var global_exerciseMuscle: dynamic
@JsName("feedback") private external var global_feedback: dynamic
@JsName("toast") private external var global_toast: dynamic
@JsName("filter") private external var global_filter: dynamic
@JsName("gymBrowse23") private external var global_gymBrowse23: dynamic
@JsName("gymFamily23") private external var global_gymFamily23: dynamic
@JsName("gymLevel23") private external var global_gymLevel23: dynamic
@JsName("sampleCompanion13") private external var global_sampleCompanion13: dynamic
@JsName("galleryActors13") private external var global_galleryActors13: dynamic
@JsName("injuryDraft") private external var global_injuryDraft: dynamic
@JsName("drawInjuries") private external var global_drawInjuries: dynamic
@JsName("openCompleteRoutines") private external var global_openCompleteRoutines: dynamic
@JsName("ownEquipment14") private external var global_ownEquipment14: dynamic
@JsName("ownFamily14") private external var global_ownFamily14: dynamic
@JsName("ownLength14") private external var global_ownLength14: dynamic
@JsName("ownLevel14") private external var global_ownLevel14: dynamic
@JsName("ownLibrary11") private external var global_ownLibrary11: dynamic
@JsName("trainerPage11") private external var global_trainerPage11: dynamic
@JsName("profileFilter") private external var global_profileFilter: dynamic
@JsName("routineAvailable") private external var global_routineAvailable: dynamic
@JsName("refreshRoutines") private external var global_refreshRoutines: dynamic
@JsName("routineCategory") private external var global_routineCategory: dynamic
@JsName("routineMode") private external var global_routineMode: dynamic
@JsName("routineProfileFilter") private external var global_routineProfileFilter: dynamic
@JsName("routineQuery") private external var global_routineQuery: dynamic
@JsName("showVariants") private external var global_showVariants: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("tab") private external var global_tab: dynamic
@JsName("Number") private external var global_Number: dynamic
@JsName("Math") private external var global_Math: dynamic
@JsName("timerEnd") private external var global_timerEnd: dynamic
@JsName("updateClock") private external var global_updateClock: dynamic
@JsName("updateSourceClock") private external var global_updateSourceClock: dynamic
@JsName("vectorSession") private external var global_vectorSession: dynamic
@JsName("vectorPaint") private external var global_vectorPaint: dynamic
@JsName("workEnd") private external var global_workEnd: dynamic
@JsName("recomputeMission") private external var global_recomputeMission: dynamic
@JsName("dayByDate") private external var global_dayByDate: dynamic
@JsName("refreshMission") private external var global_refreshMission: dynamic

internal fun installActions() {
    global_window.HunterActions = jsObject { obj3 ->
        obj3["setAsterionActive"] = fun(element: dynamic): dynamic {
            global_AsterionBridge3872.change("active", (if (truth(element.checked)) "true" else "false"))
            return absent
        }
        obj3["removeBuilderItem"] = fun(selection: dynamic): dynamic {
            global_builderItems.splice(selection, 1)
            global_builderRows()
            return absent
        }
        obj3["setBuilderSets"] = fun(element: dynamic, selection: dynamic): dynamic {
            global_builderItems[selection].sets = numeric(element["value"])
            return absent
        }
        obj3["setBuilderTarget"] = fun(element: dynamic, selection: dynamic): dynamic {
            global_builderItems[selection].target = numeric(element["value"])
            return absent
        }
        obj3["filterCreators"] = fun(element: dynamic): dynamic {
            global_creatorFilter = element["value"]
            global_document.querySelector("#creatorVideos").innerHTML = global_creatorVideoCards()
            return absent
        }
        obj3["closeDetailedMode"] = fun(): dynamic {
            global_detailedMode = false
            global_render()
            return absent
        }
        obj3["searchBody"] = fun(element: dynamic): dynamic {
            global_document.querySelector("#bodyResults").innerHTML = global_bodyResults(element["value"])
            return absent
        }
        obj3["openProfile"] = fun(): dynamic {
            global_document.querySelector("#modal").close()
            global_go("profile")
            return absent
        }
        obj3["openWorkout"] = fun(): dynamic {
            global_document.querySelector("#modal").close()
            global_go("workout")
            return absent
        }
        obj3["showAlternative"] = fun(selection: dynamic): dynamic {
            global_document.querySelector("#modal").close()
            global_technique(selection)
            return absent
        }
        obj3["showTechnique"] = fun(selection: dynamic): dynamic {
            global_document.querySelector("#modal").close()
            global_technique(selection)
            return absent
        }
        obj3["updateTrainingGoal"] = fun(element: dynamic): dynamic {
            global_document.querySelector("#trainingGoal")["value"] = element["value"]
            return absent
        }
        obj3["submitCoach"] = fun(element: dynamic, event: dynamic): dynamic {
            event.preventDefault()
            global_askCoach(element.question["value"])
            return absent
        }
        obj3["submitCheckin"] = fun(element: dynamic, event: dynamic): dynamic {
            event.preventDefault()
            global_saveCheckin(element)
            return absent
        }
        obj3["filterCategory"] = fun(selection: dynamic): dynamic {
            global_exerciseCategory = selection
            global_render()
            return absent
        }
        obj3["filterEquipment"] = fun(element: dynamic): dynamic {
            global_exerciseEquip = element["value"]
            global_refreshExercises()
            return absent
        }
        obj3["filterLevel"] = fun(element: dynamic): dynamic {
            global_exerciseLevel = element["value"]
            global_refreshExercises()
            return absent
        }
        obj3["filterMuscle"] = fun(element: dynamic): dynamic {
            global_exerciseMuscle = element["value"]
            global_refreshExercises()
            return absent
        }
        obj3["reportProgress"] = fun(): dynamic {
            global_feedback()
            global_toast("Avance parcial guardado. Puedes continuar hoy.")
            return absent
        }
        obj3["setFilter"] = fun(selection: dynamic): dynamic {
            global_filter = selection
            global_render()
            return absent
        }
        obj3["exitGym"] = fun(): dynamic {
            global_gymBrowse23 = false
            global_go("profile")
            return absent
        }
        obj3["exitGymBrowser"] = fun(): dynamic {
            global_gymBrowse23 = false
            global_render()
            return absent
        }
        obj3["setGymFamily"] = fun(element: dynamic): dynamic {
            global_gymFamily23 = element["value"]
            global_render()
            return absent
        }
        obj3["setGymLevel"] = fun(selection: dynamic): dynamic {
            global_gymLevel23 = selection
            global_render()
            return absent
        }
        obj3["toggleCompanion"] = fun(element: dynamic, selection: dynamic): dynamic {
            if (truth(element["open"])) {
                global_sampleCompanion13(selection, "enter")
            } else {
                onPresent(global_galleryActors13["get"](selection)) { value64 -> value64.pause() }
            }
            return absent
        }
        obj3["togglePreview"] = fun(element: dynamic): dynamic {
            if (truth(element["open"])) {
                element.querySelector("img").src = element.querySelector("img").dataset.src
            } else {
                element.querySelector("img").removeAttribute("src")
            }
            return absent
        }
        obj3["removeInjury"] = fun(selection: dynamic): dynamic {
            global_injuryDraft.splice(selection, 1)
            global_drawInjuries()
            return absent
        }
        obj3["setInjuryStage"] = fun(element: dynamic, selection: dynamic): dynamic {
            global_injuryDraft[selection].stage = element["value"]
            return absent
        }
        obj3["setInjuryType"] = fun(element: dynamic, selection: dynamic): dynamic {
            global_injuryDraft[selection].type = element["value"]
            return absent
        }
        obj3["setInjuryZone"] = fun(element: dynamic, selection: dynamic): dynamic {
            global_injuryDraft[selection].zone = element["value"]
            return absent
        }
        obj3["openRoutineLibrary"] = fun(): dynamic {
            global_openCompleteRoutines()
            global_document.querySelector("#modal").close()
            return absent
        }
        obj3["setLibraryEquipment"] = fun(element: dynamic): dynamic {
            global_ownEquipment14 = element["value"]
            global_render()
            return absent
        }
        obj3["setLibraryFamily"] = fun(element: dynamic): dynamic {
            global_ownFamily14 = element["value"]
            global_render()
            return absent
        }
        obj3["setLibraryLength"] = fun(element: dynamic): dynamic {
            global_ownLength14 = element["value"]
            global_render()
            return absent
        }
        obj3["setLibraryLevel"] = fun(selection: dynamic): dynamic {
            global_ownLevel14 = selection
            global_render()
            return absent
        }
        obj3["closeLibrary"] = fun(): dynamic {
            global_ownLibrary11 = false
            global_render()
            return absent
        }
        obj3["openLibrary"] = fun(): dynamic {
            global_ownLibrary11 = true
            global_trainerPage11 = dynamicValue(null)
            global_render()
            return absent
        }
        obj3["filterExercisesByProfile"] = fun(element: dynamic): dynamic {
            global_profileFilter = element.checked
            global_refreshExercises()
            return absent
        }
        obj3["filterAvailableRoutines"] = fun(element: dynamic): dynamic {
            global_routineAvailable = element.checked
            global_refreshRoutines()
            return absent
        }
        obj3["setRoutineCategory"] = fun(selection: dynamic): dynamic {
            global_routineCategory = selection
            global_render()
            return absent
        }
        obj3["showChallenges"] = fun(): dynamic {
            global_routineMode = "challenges"
            global_render()
            return absent
        }
        obj3["showCompleteRoutines"] = fun(): dynamic {
            global_routineMode = "complete"
            global_render()
            return absent
        }
        obj3["filterRoutinesByProfile"] = fun(element: dynamic): dynamic {
            global_routineProfileFilter = element.checked
            global_refreshRoutines()
            return absent
        }
        obj3["setRoutineProfileFilter"] = fun(element: dynamic): dynamic {
            global_routineProfileFilter = element.checked
            global_render()
            return absent
        }
        obj3["searchRoutines"] = fun(element: dynamic): dynamic {
            global_routineQuery = element["value"]
            global_refreshRoutines()
            return absent
        }
        obj3["setVariants"] = fun(element: dynamic): dynamic {
            global_showVariants = element.checked
            global_refreshExercises()
            return absent
        }
        obj3["deleteRoutine"] = fun(selection: dynamic): dynamic {
            global_state.customRoutines = global_state.customRoutines.filter((fun(r: dynamic): dynamic {
                return (r.id !== selection)
            }))
            global_save()
            global_document.querySelector("#modal").close()
            global_go("routines")
            return absent
        }
        obj3["openOrScrollProfile"] = fun(): dynamic {
            (if (truth((global_tab === "profile"))) global_window.scrollTo(jsonValue("{\"top\":0,\"behavior\":\"instant\"}")) else global_go("profile"))
            return absent
        }
        obj3["setPlaybackRate"] = fun(element: dynamic): dynamic {
            element.closest(".offline-demo20").querySelector("video").playbackRate = global_Number(element["value"])
            return absent
        }
        obj3["showScaledPercent"] = fun(element: dynamic): dynamic {
            element.parentElement.querySelector("output").textContent = jsAdd(global_Math.round(jsMultiply(element["value"], 100)), "%")
            return absent
        }
        obj3["showPercent"] = fun(element: dynamic): dynamic {
            element.parentElement.querySelector("output").textContent = jsAdd(element["value"], "%")
            return absent
        }
        obj3["clearTimer"] = fun(): dynamic {
            global_timerEnd = 0
            global_updateClock()
            return absent
        }
        obj3["clearSourceTimer"] = fun(): dynamic {
            global_timerEnd = 0
            global_updateSourceClock()
            return absent
        }
        obj3["clearTrainer"] = fun(): dynamic {
            global_trainerPage11 = dynamicValue(null)
            global_render()
            return absent
        }
        obj3["mirrorVector"] = fun(): dynamic {
            global_vectorSession.mirror = (!truth(global_vectorSession.mirror))
            global_vectorPaint()
            return absent
        }
        obj3["closeWorkTimer"] = fun(): dynamic {
            global_workEnd = 0
            global_document.querySelector("#modal").close()
            return absent
        }
        obj3["confirmMission"] = fun(selection: dynamic): dynamic {
            global_recomputeMission(global_dayByDate(selection))
            global_refreshMission()
            return absent
        }
        obj3["openDetailedWorkout"] = fun(): dynamic {
            global_document.querySelector("#modal").close()
            global_detailedMode = true
            global_go("workout")
            return absent
        }
        obj3["selectRoute"] = fun(selection: dynamic): dynamic {
            global_detailedMode = false
            if (truth((global_tab === selection))) {
                global_window.scrollTo(jsonValue("{\"top\":0,\"behavior\":\"instant\"}"))
            } else {
                global_go(selection)
            }
            return absent
        }
    }
}
