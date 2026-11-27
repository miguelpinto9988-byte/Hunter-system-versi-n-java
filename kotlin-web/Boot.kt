private class Boot(private val window: dynamic, private val document: dynamic) {
    private val promises: dynamic = js("Promise")
    private val math: dynamic = js("Math")
    private val boolean: dynamic = js("Boolean")
    private val scripts: dynamic = js("new Set()")
    private var committed = false
    private var finished = false
    private var failed = false
    private var inflight: dynamic = null

    private fun stage(message: dynamic, ratio: dynamic) {
        val shell = document.getElementById("boot-shell20") ?: return
        val text = shell.querySelector(".boot-message30")
        val bar = shell.querySelector("progress")
        if (text != null) text.textContent = message
        if (bar != null) bar.value = math.max(0, math.min(1, ratio))
    }

    private fun fail() {
        failed = true
        stage("No se pudo preparar el sistema. Tus registros guardados se conservan.", 0)
        val retry = document.querySelector(".boot-retry30")
        if (retry != null) retry.hidden = false
    }

    private fun promise(executor: (dynamic, dynamic) -> Unit): dynamic =
        js("Reflect").construct(promises, arrayOf(executor))

    private fun bounded(work: dynamic): dynamic {
        val timeout = promise { resolve, _ -> window.setTimeout(resolve, 2000) }
        return promises.race(arrayOf<Any>(promises.resolve(work), timeout))
    }

    private fun reveal() {
        if (failed) return
        finished = true
        stage("Sistema preparado", 1)
        document.documentElement.classList.remove("booting20")
        document.documentElement.classList.add("ready20")
        val shell = document.getElementById("boot-shell20")
        if (shell != null) {
            shell.classList.add("boot-complete30")
            shell.setAttribute("aria-hidden", "true")
            val remove: () -> Unit = { shell.remove() }
            val delay = if (window.matchMedia("(prefers-reduced-motion: reduce)").matches as Boolean) 0 else 220
            window.setTimeout(remove, delay)
        }
        val runtime: dynamic = js("Runtime20")
        runtime.startup37.reveals++
        runtime.startup37.readyAt = window.performance.now()
    }

    private fun prepare(): dynamic {
        if (window.Motion30 == null || !(window.Motion30.count() >= 1000) ||
            !(boolean(document.querySelector("#app")?.children?.length) as Boolean)) {
            throw IllegalStateException("Inicio incompleto")
        }
        stage("Preparando el diseño de tu perfil…", .93)
        val fonts = document.fonts
        val faces = listOf("400 24px \"Hunter Display\"", "400 18px \"Hunter Text\"", "600 18px \"Hunter Text\"")
        val loading = js("[]")
        if (boolean(fonts?.load) as Boolean) for (face in faces) loading.push(fonts.load(face))
        val fontWork = if (boolean(fonts?.load) as Boolean) promises.all(loading) else promises.resolve()
        val visual: dynamic = js("HunterV33.Visual33")
        val scene = visual.selection()
        val paths = js("[]")
        paths.push("ui34/awakening30.png", scene.asset.path)
        val state: dynamic = js("state")
        if (boolean(state.profile) as Boolean) paths.push(scene.figure, visual.companionPath())
        val hero: dynamic = js("HunterV34.Hero34")
        val decode: (dynamic) -> dynamic = { path -> hero.decode(path) }
        val art = promises.all(paths.map(decode))
        val ready: () -> dynamic = {
            val evolution: dynamic = js("HunterV28.Evolution28")
            val ui: dynamic = js("HunterV26.UI26")
            evolution.layout(scene)
            ui.decorate(document)
            promise { resolve, _ ->
                val frame: () -> Unit = { window.requestAnimationFrame(resolve) }
                window.requestAnimationFrame(frame)
            }
        }
        return promises.all(arrayOf<Any>(bounded(fontWork), bounded(art))).then(ready).then(::reveal)
    }

    private fun complete(): dynamic {
        if (!committed || finished || failed) return promises.resolve()
        if (inflight != null) return inflight
        val recover: (dynamic) -> Unit = { fail() }
        val clear: () -> Unit = { inflight = null }
        inflight = try {
            prepare().catch(recover)["finally"](clear)
        } catch (_: dynamic) {
            fail()
            promises.resolve()
        }
        return inflight
    }

    fun install() {
        val loaded: (dynamic) -> Unit = { event ->
            if (event.target.tagName === "SCRIPT") {
                scripts.add(event.target.src)
                val count = document.querySelectorAll("script[defer][src]").length
                stage("Preparando tu sistema…", 0.85 * (scripts.size as Int) / maxOf(1, count as Int))
            }
        }
        val error: (dynamic) -> Unit = { event ->
            if (!finished && (event.target?.tagName === "SCRIPT" || boolean(event.message) as Boolean)) fail()
        }
        document.addEventListener("load", loaded, true)
        window.addEventListener("error", error, true)
        val api = js("({})")
        api.stage = ::stage
        api.complete = ::complete
        val retry: () -> Unit = { window.location.reload() }
        val commit: () -> Unit = { committed = true }
        api.retry = retry
        api.commit = commit
        api.version = 37
        val objectType: dynamic = js("Object")
        val committedDescriptor = js("({enumerable:true,configurable:true})")
        val readCommitted: () -> Boolean = { committed }
        committedDescriptor.get = readCommitted
        objectType.defineProperty(api, "committed", committedDescriptor)
        val finishedDescriptor = js("({enumerable:true,configurable:true})")
        val readFinished: () -> Boolean = { finished }
        finishedDescriptor.get = readFinished
        objectType.defineProperty(api, "finished", finishedDescriptor)
        window.Boot30 = api
        window.Boot37 = api
    }
}

fun main() = Boot(js("window"), js("document")).install()
