package hunter.entry.stage12

import hunter.app.installhuntermonarchui3853.installHunterMonarchUi3853
import hunter.app.installhunterroute3855.installHunterRoute3855
import hunter.app.installhunterpanels3857.installHunterPanels3857
import hunter.app.installhunterbundle3874135.installHunterBundle3874135
import hunter.app.installhunterdemocoverage3862.installHunterDemoCoverage3862
import hunter.app.installhunteranatomy3863.installHunterAnatomy3863
import hunter.app.installhunterquality3864.installHunterQuality3864
import hunter.app.installhuntermotion3865.installHunterMotion3865
import hunter.app.installhuntermotion3866.installHunterMotion3866
import hunter.app.installhunterfinal3867.installHunterFinal3867
import hunter.app.installhunterportable3868.installHunterPortable3868
import hunter.app.installhunterintegration3869.installHunterIntegration3869
import hunter.app.installhunterdailyprogress3870.installHunterDailyprogress3870

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["hunter-monarch-ui3853.js"] = ::installHunterMonarchUi3853
    registry["hunter-route3855.js"] = ::installHunterRoute3855
    registry["hunter-panels3857.js"] = ::installHunterPanels3857
    registry["hunter-bundle3874-13-5.js"] = ::installHunterBundle3874135
    registry["hunter-demo-coverage3862.js"] = ::installHunterDemoCoverage3862
    registry["hunter-anatomy3863.js"] = ::installHunterAnatomy3863
    registry["hunter-quality3864.js"] = ::installHunterQuality3864
    registry["hunter-motion3865.js"] = ::installHunterMotion3865
    registry["hunter-motion3866.js"] = ::installHunterMotion3866
    registry["hunter-final3867.js"] = ::installHunterFinal3867
    registry["hunter-portable3868.js"] = ::installHunterPortable3868
    registry["hunter-integration3869.js"] = ::installHunterIntegration3869
    registry["hunter-dailyprogress3870.js"] = ::installHunterDailyprogress3870
}
