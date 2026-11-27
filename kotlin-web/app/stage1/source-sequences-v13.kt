@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installsourcesequencesv13

import hunter.browser.*

@JsName("window") private external var global_window: dynamic

internal fun installSourceSequencesV13() {
    dynamicValue((fun(root: dynamic): dynamic {
        val blocks: dynamic = jsonValue("[[\"pau-armworld\",58,35],[\"pau-armcircle-f\",98,35],[\"pau-armcircle-b\",133,35],[\"pau-armupdown\",178,35],[\"pau-armpulses\",223,35],[\"pau-armfly\",268,35],[\"pau-armsback\",313,35],[\"shouldertap\",358,35],[\"pau-armraise\",403,35,\"right\"],[\"pau-armraise\",448,35,\"left\"],[\"pau-pushchild\",493,35],[\"pau-armcurl\",538,35]]")
        val steps: dynamic = js("[]")
        if (true) {
            val iterator3: dynamic = iteratorOf(blocks.entries())
            while (true) {
                val next4: dynamic = iterator3.next()
                if (truth(next4.done)) break
                var i: dynamic = absent
                var b: dynamic = absent
                val unpacked5: dynamic = fromIterable(next4.value)
                i = unpacked5[0]
                b = unpacked5[1]
                steps.push(jsObject { obj6 ->
                    obj6["kind"] = "exercise"
                    obj6["exerciseId"] = b[0]
                    obj6["sets"] = 1
                    obj6["target"] = b[2]
                    obj6["unit"] = "sec"
                    obj6["restSeconds"] = 0
                    obj6["sourceAt"] = b[1]
                    copyFields(obj6, (if (truth(b[3])) jsObject { obj7 ->
                        obj7["side"] = b[3]
                    } else js("({})")))
                })
                val next: dynamic = blocks[jsAdd(i, 1)]
                if (truth(jsAnd(next) { jsMore(next[1], jsAdd(b[1], b[2])) })) {
                    steps.push(jsObject { obj8 ->
                        obj8["kind"] = "rest"
                        obj8["label"] = "Descanso"
                        obj8["seconds"] = jsSubtract(jsSubtract(next[1], b[1]), b[2])
                        obj8["sourceAt"] = jsAdd(b[1], b[2])
                    })
                }
            }
        }
        root.SourceSequences13 = arrayOf<dynamic>(jsObject { obj11 ->
            obj11["videoId"] = "EDtz3KvMxqc"
            obj11["channelId"] = "UCQ_N_uuyHbTE8qZ_GRIJmKw"
            obj11["name"] = "Circuito de brazos y espalda sin equipo · Pau Fit"
            obj11["verified"] = true
            obj11["evidenceType"] = "captions-and-chapters"
            obj11["visualVerified"] = false
            obj11["verificationNote"] = "Capítulos oficiales y subtítulos: 12 movimientos de 35 s; primera pausa de 5 s y nueve de 10 s. Los dos círculos de brazos van seguidos. Las marcas van de 0:58 a 9:33; los tiempos de la transcripción no coinciden con todos los capítulos. Revisión visual del video pendiente. Demostraciones 2.5D propias; no se incluyen clips del entrenador."
            obj11["steps"] = steps
        })
        return absent
    }))(global_window)
}
