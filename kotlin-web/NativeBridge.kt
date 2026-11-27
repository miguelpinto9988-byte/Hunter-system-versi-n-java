private class NativeBridge(private val window: dynamic) {
    private val json: dynamic = js("JSON")
    private val objectType: dynamic = js("Object")
    private val arrayType: dynamic = js("Array")
    private val booleanType: dynamic = js("Boolean")
    private val colorPattern: dynamic = js("/^#[0-9a-fA-F]{6}$/")
    private val stats: dynamic = js("({sent:0,skipped:0,failed:0,messageSent:0,legacySent:0})")
    private var lastPlan: String? = null
    private var lastTheme = ""

    private fun messageReady(): Boolean =
        window.__HUNTER_WEB_MESSAGES_READY_V1__ === true &&
            jsTypeOf(window.HunterMessages?.postMessage) == "function"

    private fun send(type: String, payload: dynamic): Boolean {
        try {
            if (messageReady()) {
                val envelope = js("({schema:1})")
                envelope.type = type
                envelope.payload = payload
                window.HunterMessages.postMessage(json.stringify(envelope))
                stats.messageSent++
            } else {
                val bridge = window.Android
                if (jsTypeOf(if (bridge == null) null else bridge[type]) != "function") return false
                bridge[type](if (type == "syncPlan") json.stringify(payload) else payload)
                stats.legacySent++
            }
            stats.sent++
            return true
        } catch (_: dynamic) {
            stats.failed++
            return false
        }
    }

    private fun syncPlan(data: dynamic): Boolean {
        if (data == null || jsTypeOf(data) != "object" || !(arrayType.isArray(data.days) as Boolean)) return false
        val serialized: dynamic = try {
            json.stringify(data)
        } catch (_: dynamic) {
            return false
        }
        if (serialized === lastPlan) {
            stats.skipped++
            return true
        }
        if (!send("syncPlan", data)) return false
        lastPlan = serialized as String?
        return true
    }

    private fun setThemeBackground(color: dynamic): Boolean {
        if (jsTypeOf(color) != "string" || !(colorPattern.test(color) as Boolean)) return false
        val normalized = (color as String).lowercase()
        if (normalized == lastTheme) {
            stats.skipped++
            return true
        }
        if (!send("setThemeBackground", normalized)) return false
        lastTheme = normalized
        return true
    }

    private fun snapshot(): dynamic {
        val result = objectType.assign(js("({})"), stats)
        result.messageReady = messageReady()
        result.legacyReady = booleanType(window.Android)
        return result
    }

    fun install() {
        val document = window.document
        val onVisibility: (dynamic) -> Unit = {
            if (document.hidden as Boolean) {
                lastPlan = null
                lastTheme = ""
            }
        }
        document.addEventListener("visibilitychange", onVisibility, js("({passive:true})"))
        val api = js("({version:'38.75'})")
        api.syncPlan = ::syncPlan
        api.setThemeBackground = ::setThemeBackground
        api.snapshot = ::snapshot
        window.HunterNative3875 = objectType.freeze(api)
    }
}

fun main() {
    val window: dynamic = js("window")
    if (window.HunterNative3875 == null) NativeBridge(window).install()
}
