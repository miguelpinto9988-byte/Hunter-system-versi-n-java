@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installsourcesequences

import hunter.browser.*

@JsName("window") private external var global_window: dynamic
@JsName("globalThis") private external var global_globalThis: dynamic

internal fun installSourceSequences() {
    dynamicValue((fun(root: dynamic): dynamic {
        val ids: dynamic = jsonValue("[\"pau-altpress\",\"pau-overtriceps\",\"pau-arnold\",\"pau-lateralcurl\",\"hammer\",\"pau-curlcombo\",\"pau-raisecombo\",\"pau-standingfly\",\"bentrow\",\"pau-presscombo\",\"pau-kickback\",\"pau-plankup\",\"plank\",\"pau-pushchild\"]")
        val steps: dynamic = jsonValue("[{\"kind\":\"preparation\",\"label\":\"Preparar el circuito: 14 movimientos de 30 s y descansos de 10 s\",\"seconds\":10,\"sourceAt\":62}]")
        ids.forEach((fun(exerciseId: dynamic, i: dynamic): dynamic {
            steps.push(jsObject { obj3 ->
                obj3["kind"] = "exercise"
                obj3["exerciseId"] = exerciseId
                obj3["sets"] = 1
                obj3["target"] = 30
                obj3["unit"] = "sec"
                obj3["restSeconds"] = 0
                obj3["sourceAt"] = jsAdd(72, jsMultiply(i, 40))
            })
            if (truth(jsLess(i, jsSubtract(ids.length, 1)))) {
                steps.push(jsObject { obj4 ->
                    obj4["kind"] = "rest"
                    obj4["label"] = "Descanso"
                    obj4["seconds"] = 10
                    obj4["sourceAt"] = jsAdd(102, jsMultiply(i, 40))
                })
            }
            return absent
        }))
        root.SourceSequences12 = arrayOf<dynamic>(jsObject { obj7 ->
            obj7["videoId"] = "QdiX-1poVO8"
            obj7["channelId"] = "UCQ_N_uuyHbTE8qZ_GRIJmKw"
            obj7["name"] = "Circuito con mancuernas para brazos y espalda · Pau Fit"
            obj7["verified"] = true
            obj7["evidenceType"] = "captions-and-chapters"
            obj7["visualVerified"] = false
            obj7["verificationNote"] = "Secuencia textual contrastada con capítulos y subtítulos automáticos: 14 movimientos de 30 s, 13 descansos de 10 s y preparación de 10 s. La entrenadora declara usar dos mancuernas de 2,5 kg; es una referencia, no tu carga asignada. Revisión visual del video pendiente; los subtítulos automáticos pueden contener errores. Las demostraciones 2D son esquemas propios."
            obj7["steps"] = steps
        })
        return absent
    }))((if (truth((js("typeof window") !== "undefined"))) global_window else global_globalThis))
}
