private class ModalViewport(private val window: dynamic, private val doc: dynamic, private val dialog: dynamic) {
    private val body = doc.body
    private val root = doc.documentElement
    private val truth: dynamic = js("Boolean")
    private val number: dynamic = js("Number")
    private val stringify: dynamic = js("String")
    private val parse: dynamic = js("parseFloat")
    private val properties = listOf("position", "top", "left", "width", "overflow", "padding-right")
    private val dimensions = listOf("--modal-top", "--modal-left", "--modal-width", "--modal-max-height", "--window-height26")
    private var saved: dynamic = null
    private var generation = 0
    private var pending = 0
    private var navigating = false
    private var geometry = ""

    private fun or(value: dynamic, fallback: dynamic): dynamic = if (truth(value) as Boolean) value else fallback
    private fun finite(value: dynamic, fallback: dynamic): Double =
        if (number.isFinite(value) as Boolean) value as Double else fallback as Double
    private fun pixels(value: Double): String = (stringify(value) as String) + "px"

    private fun viewport(): dynamic {
        val visual = window.visualViewport
        val result = js("({})")
        result.top = maxOf(0.0, finite(visual?.offsetTop, 0))
        result.left = maxOf(0.0, finite(visual?.offsetLeft, 0))
        result.width = maxOf(1.0, finite(visual?.width, or(window.innerWidth, or(root.clientWidth, 360))))
        result.height = maxOf(1.0, finite(visual?.height, or(window.innerHeight, or(root.clientHeight, 640))))
        return result
    }

    private fun set(name: String, value: Double) {
        val text = pixels(value)
        if (dialog.style.getPropertyValue(name) !== text) dialog.style.setProperty(name, text)
    }

    private fun fit(force: dynamic) {
        if (!(truth(dialog.open) as Boolean) && !(truth(force) as Boolean)) return
        val v = viewport()
        val css = window.getComputedStyle(root)
        fun safe(edge: String): Double = maxOf(0.0, or(parse(css.getPropertyValue("--modal-safe-" + edge)), 0) as Double)
        val top = safe("top")
        val left = safe("left")
        val width = maxOf(1.0, (v.width as Double) - left - safe("right"))
        val height = maxOf(1.0, (v.height as Double) - top - safe("bottom"))
        val landscape = window.innerWidth > window.innerHeight && (window.innerWidth >= 700 ||
            window.innerWidth >= 600 && dialog.classList.contains("session-landscape35") as Boolean)
        val layoutWidth = maxOf(1.0, or(window.innerWidth, or(root.clientWidth, v.width)) as Double)
        val layoutHeight = maxOf(1.0, or(window.innerHeight, or(root.clientHeight, v.height)) as Double)
        val tablet = (layoutWidth >= 820 && layoutHeight >= 520 && layoutWidth > layoutHeight) ||
            (layoutWidth >= 600 && layoutHeight >= 740 && layoutHeight >= layoutWidth)
        dialog.classList.toggle("hunter-tablet-all3831", tablet)
        dialog.classList.toggle("hunter-tablet-guide3830", dialog.querySelector(".guided-session") != null && tablet)
        val gap = if (tablet) minOf(10.0, width / 10, height / 10) else minOf(12.0, width / 8, height / 8)
        val limit = when { tablet -> width; landscape -> 1050.0; width >= 720 -> 860.0; else -> 580.0 }
        val modalWidth = minOf(limit, maxOf(1.0, width - gap * 2))
        set("--modal-top", (v.top as Double) + top + gap)
        set("--modal-left", (v.left as Double) + left + (width - modalWidth) / 2)
        set("--modal-width", modalWidth)
        set("--modal-max-height", maxOf(1.0, height - gap * 2))
        if (truth(dialog.open) as Boolean) {
            val maxScroll = maxOf(0.0, (dialog.scrollHeight as Double) - (dialog.clientHeight as Double))
            dialog.scrollTop = (dialog.scrollTop as Double).coerceIn(0.0, maxScroll)
        }
        val rect = dialog.getBoundingClientRect()
        if (truth(dialog.open) as Boolean && rect.height > 0) {
            set("--window-height26", rect.height as Double)
            val next = listOf(rect.left, rect.top, rect.width, rect.height).joinToString(":") { stringify(it) as String }
            if (next != geometry) {
                geometry = next
                dialog.dispatchEvent(js("new CustomEvent('hunter:modal-fit')"))
            }
        }
    }

    private fun lock() {
        if (saved == null) {
            val snapshot = js("({})")
            snapshot.x = or(window.scrollX, 0)
            snapshot.y = or(window.scrollY, 0)
            snapshot.focus = doc.activeElement
            snapshot.styles = js("[]")
            for (name in properties) {
                snapshot.styles.push(arrayOf<Any>(name, body.style.getPropertyValue(name), body.style.getPropertyPriority(name)))
            }
            snapshot.behavior = root.style.getPropertyValue("scroll-behavior")
            snapshot.priority = root.style.getPropertyPriority("scroll-behavior")
            saved = snapshot
            val gutter = maxOf(0.0, (or(window.innerWidth, root.clientWidth) as Double) - (root.clientWidth as Double))
            if (gutter != 0.0) {
                val padding = or(parse(window.getComputedStyle(body).paddingRight), 0) as Double
                body.style.setProperty("padding-right", pixels(padding + gutter))
            }
            body.style.setProperty("position", "fixed")
            body.style.setProperty("top", pixels(-(snapshot.y as Double)))
            body.style.setProperty("left", pixels(-(snapshot.x as Double)))
            body.style.setProperty("width", "100%")
            body.style.setProperty("overflow", "hidden")
        }
        root.classList.add("modal-open")
        body.classList.add("modal-open")
        fit(true)
    }

    private fun begin(): Int {
        generation++
        lock()
        return generation
    }

    private fun reset() {
        val token = generation
        dialog.scrollTop = 0
        fit(true)
        val next: () -> Unit = {
            if (truth(dialog.open) as Boolean && token == generation) {
                dialog.scrollTop = 0
                fit(false)
            }
        }
        window.requestAnimationFrame(next)
    }

    private fun release(restore: dynamic) {
        if (truth(dialog.open) as Boolean) return
        generation++
        window.cancelAnimationFrame(pending)
        pending = 0
        val snapshot = saved
        saved = null
        root.classList.remove("modal-open")
        body.classList.remove("modal-open")
        geometry = ""
        dialog.classList.remove("hunter-tablet-guide3830", "hunter-tablet-all3831")
        for (name in dimensions) dialog.style.removeProperty(name)
        if (snapshot == null) return
        for (i in 0 until (snapshot.styles.length as Int)) {
            val row = snapshot.styles[i]
            if (truth(row[1]) as Boolean) body.style.setProperty(row[0], row[1], row[2])
            else body.style.removeProperty(row[0])
        }
        root.style.setProperty("scroll-behavior", "auto", "important")
        if (jsTypeOf(restore) == "undefined" || truth(restore) as Boolean) {
            val focus = snapshot.focus
            if (truth(focus?.isConnected) as Boolean && focus !== body && focus !== dialog && focus.focus != null) {
                focus.focus(js("({preventScroll:true})"))
            }
            window.scrollTo(snapshot.x, snapshot.y)
        }
        if (truth(snapshot.behavior) as Boolean) root.style.setProperty("scroll-behavior", snapshot.behavior, snapshot.priority)
        else root.style.removeProperty("scroll-behavior")
    }

    private fun schedule() {
        if (!(truth(dialog.open) as Boolean) || pending != 0) return
        val next: () -> Unit = { pending = 0; fit(false) }
        pending = window.requestAnimationFrame(next) as Int
    }

    private fun beforeNavigation() {
        navigating = true
        try { if (truth(dialog.open) as Boolean) dialog.close() else release(false) }
        finally { navigating = false }
    }

    fun install() {
        val nativeClose = dialog.close.bind(dialog)
        val close: (dynamic) -> Unit = { value -> nativeClose(value); release(!navigating) }
        dialog.close = close
        val onClose: () -> Unit = { release(!navigating) }
        dialog.addEventListener("close", onClose)
        val passive = js("({passive:true})")
        window.addEventListener("resize", ::schedule, passive)
        window.addEventListener("orientationchange", ::schedule, passive)
        window.visualViewport?.addEventListener("resize", ::schedule, passive)
        window.visualViewport?.addEventListener("scroll", ::schedule, passive)
        dialog.addEventListener("load", ::schedule, true)
        val toggle: (dynamic) -> Unit = { e ->
            if (e.target.tagName === "DETAILS" && dialog.contains(e.target) as Boolean) schedule()
        }
        doc.addEventListener("toggle", toggle, true)
        if (jsTypeOf(window.ResizeObserver) == "function") {
            val observer = js("Reflect").construct(window.ResizeObserver, arrayOf(::schedule))
            observer.observe(dialog)
        }
        if (doc.fonts != null) {
            val ignore: (dynamic) -> Unit = {}
            doc.fonts.ready.then(::schedule).catch(ignore)
        }
        val api = js("({})")
        api.viewport = ::viewport
        api.fit = ::fit
        api.lock = ::lock
        api.begin = ::begin
        api.reset = ::reset
        api.release = ::release
        api.beforeNavigation = ::beforeNavigation
        val descriptor = js("({enumerable:true,configurable:true})")
        val readGeneration: () -> Int = { generation }
        descriptor.get = readGeneration
        js("Object").defineProperty(api, "generation", descriptor)
        window.ModalViewport = api
    }
}

fun main() {
    val document: dynamic = js("document")
    val dialog = document.getElementById("modal")
    if (dialog != null) ModalViewport(js("window"), document, dialog).install()
}
