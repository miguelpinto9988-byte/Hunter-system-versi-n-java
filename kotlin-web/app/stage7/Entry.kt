package hunter.entry.stage7

import hunter.app.installv24.installV24
import hunter.app.installv25.installV25
import hunter.app.installv26.installV26
import hunter.app.installv27.installV27
import hunter.app.installv28.installV28
import hunter.app.installv29.installV29
import hunter.app.installmotionv30.installMotionV30

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["v24.js"] = ::installV24
    registry["v25.js"] = ::installV25
    registry["v26.js"] = ::installV26
    registry["v27.js"] = ::installV27
    registry["v28.js"] = ::installV28
    registry["v29.js"] = ::installV29
    registry["motion-v30.js"] = ::installMotionV30
}
