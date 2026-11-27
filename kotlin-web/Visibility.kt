private class Visibility(private val window: dynamic) {
    private val document: dynamic = window.document
    private val selectors = listOf(
        "#app .monarch-hero33", "#app .frame-glint26", "#app .window-particles28",
        "#app .ambient-particles29", "#app .coach-intro", "#app .coach-banner-companion",
        "#app .rest-orbit", "#app .reward", "#app .hero26", "#app .knight-body",
        "#app .system-card26", "#modal .coach-intro", "#modal .monarch-hero33", "#modal .knight-body",
    ).joinToString(",")
    private val watched: dynamic = js("new Set()")
    private val counters: dynamic = js("({observed:0,paused:0,resumed:0,observations:0,updates:0,cleanup:0,hidden:0})")
    private var scheduled = 0
    private var observer: dynamic = null

    private fun updateClass(node: dynamic, paused: Boolean) {
        if (!(node.isConnected as Boolean) || node.classList.contains("hunter-offscreen3874") === paused) return
        node.classList.toggle("hunter-offscreen3874", paused)
        if (paused) counters.paused++ else counters.resumed++
    }

    private fun onIntersection(records: dynamic) {
        val count = records.length as Int
        counters.observations += count
        for (index in 0 until count) {
            val record = records[index]
            updateClass(record.target, !(record.isIntersecting as Boolean))
        }
    }

    private fun refresh() {
        scheduled = 0
        counters.updates++
        if (observer == null) return
        val clean: (dynamic) -> Unit = { node ->
            if (!(node.isConnected as Boolean)) {
                observer.unobserve(node)
                watched.delete(node)
                counters.cleanup++
            }
        }
        watched.forEach(clean)
        val nodes = document.querySelectorAll(selectors)
        for (index in 0 until (nodes.length as Int)) {
            val node = nodes[index]
            if (watched.has(node) as Boolean) continue
            watched.add(node)
            observer.observe(node)
        }
        counters.observed = watched.size
    }

    private fun schedule() {
        if (scheduled != 0 || document.hidden as Boolean) return
        scheduled = window.requestAnimationFrame(::refresh) as Int
    }

    private fun onVisibility() {
        val hidden = document.hidden as Boolean
        document.documentElement.classList.toggle("hunter-apphidden3874", hidden)
        if (hidden) {
            counters.hidden++
            if (scheduled != 0) {
                window.cancelAnimationFrame(scheduled)
                scheduled = 0
            }
        } else {
            schedule()
        }
    }

    private fun snapshot(): dynamic {
        val objectType: dynamic = js("Object")
        val result = objectType.assign(js("({})"), counters)
        result.observerSupported = observer != null
        return result
    }

    fun install() {
        val intersectionType = window.IntersectionObserver
        val mutationType = window.MutationObserver
        val reflect: dynamic = js("Reflect")
        if (jsTypeOf(intersectionType) == "function") {
            val listener: (dynamic) -> Unit = ::onIntersection
            observer = reflect.construct(intersectionType, arrayOf<Any>(listener, js("({threshold:0,rootMargin:'64px 0px'})")))
        }
        if (observer != null && mutationType != null) {
            val mutation = reflect.construct(mutationType, arrayOf(::schedule))
            for (id in listOf("app", "modal")) {
                val node = document.getElementById(id)
                if (node != null) mutation.observe(node, js("({childList:true,subtree:true})"))
            }
        }
        val passive = js("({passive:true})")
        document.addEventListener("visibilitychange", ::onVisibility, passive)
        window.addEventListener("pageshow", ::schedule, passive)
        val api = js("({version:'38.74'})")
        api.refresh = ::schedule
        api.snapshot = ::snapshot
        val objectType: dynamic = js("Object")
        window.HunterVisibility3874 = objectType.freeze(api)
        onVisibility()
        schedule()
    }
}

fun main() {
    val window: dynamic = js("window")
    if (window.HunterVisibility3874 == null) Visibility(window).install()
}
