package hunter.entry.stage10

import hunter.app.installhunterprogression3815.installHunterProgression3815
import hunter.app.installhuntergroups3849.installHunterGroups3849
import hunter.app.installhunterquality3850.installHunterQuality3850
import hunter.app.installhuntercategoryprogress3851.installHunterCategoryprogress3851
import hunter.app.installhunterlevel3851.installHunterLevel3851
import hunter.app.installhunterdynamic381.installHunterDynamic381
import hunter.app.installhuntermonarch3852.installHunterMonarch3852
import hunter.app.installhunterclean382.installHunterClean382
import hunter.app.installhuntersafe383.installHunterSafe383
import hunter.app.installhunterresource384.installHunterResource384
import hunter.app.installhunterinsights385.installHunterInsights385
import hunter.app.installhunterevolution385.installHunterEvolution385

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["hunter-progression3815.js"] = ::installHunterProgression3815
    registry["hunter-groups3849.js"] = ::installHunterGroups3849
    registry["hunter-quality3850.js"] = ::installHunterQuality3850
    registry["hunter-categoryprogress3851.js"] = ::installHunterCategoryprogress3851
    registry["hunter-level3851.js"] = ::installHunterLevel3851
    registry["hunter-dynamic381.js"] = ::installHunterDynamic381
    registry["hunter-monarch3852.js"] = ::installHunterMonarch3852
    registry["hunter-clean382.js"] = ::installHunterClean382
    registry["hunter-safe383.js"] = ::installHunterSafe383
    registry["hunter-resource384.js"] = ::installHunterResource384
    registry["hunter-insights385.js"] = ::installHunterInsights385
    registry["hunter-evolution385.js"] = ::installHunterEvolution385
}
