package hunter.entry.stage9

import hunter.app.installv37.installV37
import hunter.app.installsvgrendererv373.installSvgRendererV373
import hunter.app.installruntimev373.installRuntimeV373
import hunter.app.installhunterv374.installHunterV374
import hunter.app.installhuntermotion375.installHunterMotion375
import hunter.app.installhunterv376.installHunterV376
import hunter.app.installhunterv377.installHunterV377
import hunter.app.installhunterv378.installHunterV378
import hunter.app.installhunterv379.installHunterV379
import hunter.app.installhunterperformance380.installHunterPerformance380
import hunter.app.installhuntertraining387.installHunterTraining387
import hunter.app.installhuntermaterials3814.installHunterMaterials3814

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["v37.js"] = ::installV37
    registry["svg-renderer-v373.js"] = ::installSvgRendererV373
    registry["runtime-v373.js"] = ::installRuntimeV373
    registry["hunter-v374.js"] = ::installHunterV374
    registry["hunter-motion375.js"] = ::installHunterMotion375
    registry["hunter-v376.js"] = ::installHunterV376
    registry["hunter-v377.js"] = ::installHunterV377
    registry["hunter-v378.js"] = ::installHunterV378
    registry["hunter-v379.js"] = ::installHunterV379
    registry["hunter-performance380.js"] = ::installHunterPerformance380
    registry["hunter-training387.js"] = ::installHunterTraining387
    registry["hunter-materials3814.js"] = ::installHunterMaterials3814
}
