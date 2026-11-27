private class Bootstrap(private val window: dynamic, private val document: dynamic) {
    private val boolean: dynamic = js("Boolean")
    private val string: dynamic = js("String")
    private val diagnostics = js("({errors:[],submissions:0,blockedNavigations:0,initializing:true})")
    private val hues = js("({blue:202,pink:330,violet:267,emerald:151,amber:36,coral:15,red:348,teal:175,ice:215,gold:51})")

    private fun applyAppearance() {
        val root = document.documentElement
        root.classList.add("booting20")
        try {
            val storage: dynamic = js("localStorage")
            val json: dynamic = js("JSON")
            val raw = storage.getItem("hunter.system.v1")
            val saved = json.parse(if (boolean(raw) as Boolean) raw else "{}")
            val profile = saved.profile ?: js("({})")
            val key = when {
                js("Reflect").has(hues, profile.theme) as Boolean -> profile.theme
                profile.gender === "female" -> "pink"
                else -> "blue"
            }
            val palette = saved.appearance33?.palette
            val offset = when (palette) { "umbral" -> 24; "lunar" -> -22; else -> 0 }
            val saturation = when (palette) { "umbral" -> 27; "lunar" -> 22; else -> 24 }
            val hue = hues[key]
            val base = (hue + offset + 360) % 360
            root.dataset.theme = key
            root.style.setProperty("--bg", "hsl($base $saturation% 8%)")
            root.style.setProperty("--text", "hsl($base 22% 86%)")
            root.style.setProperty("--accent", "hsl($hue 43% 77%)")
        } catch (_: dynamic) { /* El arranque también admite almacenamiento inaccesible. */ }
    }

    private fun error(event: dynamic) {
        val entry = js("({})")
        entry.message = string(if (boolean(event.message) as Boolean) event.message else "Error de interfaz")
        val filename = string(if (boolean(event.filename) as Boolean) event.filename else "") as String
        entry.source = filename.substringAfterLast('/')
        entry.time = js("new Date().toISOString()")
        diagnostics.errors.push(entry)
        diagnostics.errors = diagnostics.errors.slice(-20)
    }

    private fun click(event: dynamic) {
        val target = event.target
        val link = if (target.closest == null) null else target.closest("a[href]")
        if (link == null) return
        val location: dynamic = js("location")
        val url = try {
            js("Reflect").construct(js("URL"), arrayOf<Any>(link.href, location.href))
        } catch (_: dynamic) { return }
        if (url.origin === location.origin && url.pathname !== location.pathname && !(link.hasAttribute("download") as Boolean)) {
            event.preventDefault()
            diagnostics.blockedNavigations++
        }
    }

    fun install() {
        applyAppearance()
        window.Runtime20 = diagnostics
        window.addEventListener("error", ::error)
        val submit: (dynamic) -> Unit = { event ->
            event.preventDefault()
            diagnostics.submissions++
        }
        document.addEventListener("submit", submit, true)
        document.addEventListener("click", ::click, true)
    }
}

fun main() = Bootstrap(js("window"), js("document")).install()
