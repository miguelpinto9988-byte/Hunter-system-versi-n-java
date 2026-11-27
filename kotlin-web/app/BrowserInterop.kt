@file:Suppress("UNUSED_PARAMETER", "NOTHING_TO_INLINE")
package hunter.browser

import kotlin.coroutines.*
import kotlin.js.Promise

internal inline fun dynamicValue(value: dynamic): dynamic = value
internal val absent: dynamic = js("void 0")
internal inline fun truth(value: dynamic): Boolean = js("!!value")
internal inline fun textOf(value: dynamic): String = js("String(value)")
internal inline fun numeric(value: dynamic): dynamic = js("+value")
internal inline fun jsAdd(a: dynamic, b: dynamic): dynamic = js("a+b")
internal inline fun jsSubtract(a: dynamic, b: dynamic): dynamic = js("a-b")
internal inline fun jsMultiply(a: dynamic, b: dynamic): dynamic = js("a*b")
internal inline fun jsDivide(a: dynamic, b: dynamic): dynamic = js("a/b")
internal inline fun jsMod(a: dynamic, b: dynamic): dynamic = js("a%b")
internal inline fun jsPower(a: dynamic, b: dynamic): dynamic = js("Math.pow(a, b)")
internal inline fun jsEqual(a: dynamic, b: dynamic): Boolean = js("a==b")
internal inline fun jsLess(a: dynamic, b: dynamic): Boolean = js("a<b")
internal inline fun jsMore(a: dynamic, b: dynamic): Boolean = js("a>b")
internal inline fun jsAtMost(a: dynamic, b: dynamic): Boolean = js("a<=b")
internal inline fun jsAtLeast(a: dynamic, b: dynamic): Boolean = js("a>=b")
internal inline fun jsUnsignedShift(a: dynamic, b: dynamic): dynamic = js("a>>>b")
internal inline fun jsShiftRight(a: dynamic, b: dynamic): dynamic = js("a>>b")
internal inline fun jsShiftLeft(a: dynamic, b: dynamic): dynamic = js("a<<b")
internal inline fun jsBitAnd(a: dynamic, b: dynamic): dynamic = js("a&b")
internal inline fun jsBitOr(a: dynamic, b: dynamic): dynamic = js("a|b")
internal inline fun jsBitXor(a: dynamic, b: dynamic): dynamic = js("a^b")
internal inline fun jsInvert(value: dynamic): dynamic = js("~value")
internal inline fun instanceOf(value: dynamic, type: dynamic): Boolean = js("value instanceof type")
internal inline fun hasKey(key: dynamic, obj: dynamic): Boolean = js("key in obj")
internal inline fun removeKey(obj: dynamic, key: dynamic): Boolean = js("delete obj[key]")
internal inline fun jsOr(value: dynamic, other: () -> dynamic): dynamic = if (truth(value)) value else other()
internal inline fun jsAnd(value: dynamic, other: () -> dynamic): dynamic = if (!truth(value)) value else other()
internal inline fun definedOr(value: dynamic, other: () -> dynamic): dynamic = if (value !== absent) value else other()
internal inline fun nullableOr(value: dynamic, other: () -> dynamic): dynamic = if (value == null) other() else value
internal inline fun onPresent(value: dynamic, action: (dynamic) -> dynamic): dynamic = if (value == null) absent else action(value)
internal inline fun jsObject(build: (dynamic) -> Unit): dynamic {
    val obj = js("({})")
    build(obj)
    return obj
}
internal inline fun jsArray(build: (dynamic) -> Unit): dynamic {
    val array = js("[]")
    build(array)
    return array
}
internal inline fun copyFields(target: dynamic, source: dynamic): dynamic = js("Object.assign(target, source)")
internal fun appendAll(array: dynamic, values: dynamic) {
    val iterator = iteratorOf(values)
    var step = iterator.next()
    while (!truth(step.done)) {
        array.push(step.value)
        step = iterator.next()
    }
}
internal inline fun iteratorOf(value: dynamic): dynamic = js("value[Symbol.iterator]()")
internal inline fun fromIterable(value: dynamic): dynamic = js("Array.from(value)")
internal inline fun jsConstruct(type: dynamic, args: dynamic): dynamic = js("Reflect.construct(type, args)")
internal inline fun jsRegExp(pattern: String, flags: String): dynamic = js("new RegExp(pattern, flags)")
internal inline fun jsonValue(json: String): dynamic = js("JSON.parse(json)")
internal inline fun invokeWith(fn: dynamic, receiver: dynamic, args: dynamic): dynamic = js("Reflect.apply(fn, receiver, args)")
internal inline fun objectKeys(value: dynamic): dynamic = js("Object.keys(value)")
internal inline fun argsAfter(args: dynamic, index: Int): dynamic = js("Array.prototype.slice.call(args, index)")
internal fun asyncAction(body: suspend () -> dynamic): dynamic = Promise<dynamic> { resolve, reject ->
    body.startCoroutine(object : Continuation<dynamic> {
        override val context: CoroutineContext = EmptyCoroutineContext
        override fun resumeWith(result: Result<dynamic>) { result.fold({ resolve(it) }, { reject(it) }) }
    })
}
internal suspend fun awaitValue(value: dynamic): dynamic = suspendCoroutine { cont ->
    js("Promise").resolve(value).then({ result: dynamic -> cont.resume(result) }, { error: dynamic -> cont.resumeWithException(error) })
}
internal fun accessor(obj: dynamic, key: dynamic, kind: String, function: dynamic) {
    val descriptor = js("Object").getOwnPropertyDescriptor(obj, key) ?: js("({configurable:true, enumerable:true})")
    descriptor[kind] = function
    js("Object").defineProperty(obj, key, descriptor)
}
