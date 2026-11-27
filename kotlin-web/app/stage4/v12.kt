@file:Suppress("UNUSED_VARIABLE", "UNUSED_PARAMETER", "VARIABLE_WITH_REDUNDANT_INITIALIZER", "UNREACHABLE_CODE", "NAME_SHADOWING")
package hunter.app.installv12

import hunter.browser.*

@JsName("SourceSequences12") private external var global_SourceSequences12: dynamic
@JsName("state") private external var global_state: dynamic
@JsName("CreatorFeed") private external var global_CreatorFeed: dynamic
@JsName("JSON") private external var global_JSON: dynamic
@JsName("save") private external var global_save: dynamic
@JsName("currentCycle") private external var global_currentCycle: dynamic
@JsName("render") private external var global_render: dynamic
@JsName("syncNative") private external var global_syncNative: dynamic

internal fun installV12() {
    if (true) {
        val iterator1: dynamic = iteratorOf(global_SourceSequences12)
        while (true) {
            val next2: dynamic = iterator1.next()
            if (truth(next2.done)) break
            var source: dynamic = absent
            source = next2.value
            if (truth(jsAnd((!truth(global_state.creatorRoutines.some((fun(r: dynamic): dynamic {
                return (r.videoId === source.videoId)
            }))))) { global_CreatorFeed.validateRoutine(source) })) {
                global_state.creatorRoutines.push(global_JSON.parse(global_JSON.stringify(source)))
            }
        }
    }
    global_state.appVersion = 12
    global_save()
    global_currentCycle()
    global_render()
    global_syncNative()
}
