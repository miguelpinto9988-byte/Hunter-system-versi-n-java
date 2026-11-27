package hunter.entry.stage8

import hunter.app.installmotionv31.installMotionV31
import hunter.app.installmotionv36.installMotionV36
import hunter.app.installmotionv37.installMotionV37
import hunter.app.installmotionv373.installMotionV373
import hunter.app.installtrainingv30.installTrainingV30
import hunter.app.installv30.installV30
import hunter.app.installv31.installV31
import hunter.app.installv32.installV32
import hunter.app.installvisualsv33.installVisualsV33
import hunter.app.installv33.installV33
import hunter.app.installv34.installV34
import hunter.app.installv35.installV35
import hunter.app.installv36.installV36

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["motion-v31.js"] = ::installMotionV31
    registry["motion-v36.js"] = ::installMotionV36
    registry["motion-v37.js"] = ::installMotionV37
    registry["motion-v373.js"] = ::installMotionV373
    registry["training-v30.js"] = ::installTrainingV30
    registry["v30.js"] = ::installV30
    registry["v31.js"] = ::installV31
    registry["v32.js"] = ::installV32
    registry["visuals-v33.js"] = ::installVisualsV33
    registry["v33.js"] = ::installV33
    registry["v34.js"] = ::installV34
    registry["v35.js"] = ::installV35
    registry["v36.js"] = ::installV36
}
