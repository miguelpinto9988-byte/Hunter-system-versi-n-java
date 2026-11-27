package hunter.entry.stage6

import hunter.app.installprogressionv19.installProgressionV19
import hunter.app.installgoalcontextv24.installGoalContextV24
import hunter.app.installtrainingv23.installTrainingV23
import hunter.app.installv18.installV18
import hunter.app.installv19.installV19
import hunter.app.installprogressmonitorv20.installProgressMonitorV20
import hunter.app.installreferencev20.installReferenceV20
import hunter.app.installv20.installV20
import hunter.app.installv22.installV22
import hunter.app.installv23.installV23

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["progression-v19.js"] = ::installProgressionV19
    registry["goal-context-v24.js"] = ::installGoalContextV24
    registry["training-v23.js"] = ::installTrainingV23
    registry["v18.js"] = ::installV18
    registry["v19.js"] = ::installV19
    registry["progress-monitor-v20.js"] = ::installProgressMonitorV20
    registry["reference-v20.js"] = ::installReferenceV20
    registry["v20.js"] = ::installV20
    registry["v22.js"] = ::installV22
    registry["v23.js"] = ::installV23
}
