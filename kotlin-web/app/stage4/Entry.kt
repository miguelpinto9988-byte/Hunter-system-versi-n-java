package hunter.entry.stage4

import hunter.app.installv12.installV12
import hunter.app.installv13.installV13
import hunter.app.installv14.installV14
import hunter.app.installv15.installV15
import hunter.app.installv16.installV16
import hunter.app.installv17.installV17
import hunter.app.installviewsv21.installViewsV21
import hunter.app.installrigcorrectionsv23.installRigCorrectionsV23

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["v12.js"] = ::installV12
    registry["v13.js"] = ::installV13
    registry["v14.js"] = ::installV14
    registry["v15.js"] = ::installV15
    registry["v16.js"] = ::installV16
    registry["v17.js"] = ::installV17
    registry["views-v21.js"] = ::installViewsV21
    registry["rig-corrections-v23.js"] = ::installRigCorrectionsV23
}
