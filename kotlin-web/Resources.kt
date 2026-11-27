@OptIn(ExperimentalJsExport::class)
@JsExport
fun decodeResource(payload: String, frozen: Boolean): dynamic {
    val json: dynamic = js("JSON")
    val value = json.parse(payload)
    val objectType: dynamic = js("Object")
    return if (frozen) objectType.freeze(value) else value
}
