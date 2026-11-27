package hunter.entry.stage13

import hunter.app.installasterionrules3872.installAsterionRules3872
import hunter.app.installasterionbridge3872.installAsterionBridge3872
import hunter.app.installasterionunified3873.installAsterionUnified3873

import hunter.app.installhunterequipment3880.installHunterEquipment3880

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["asterion-rules3872.js"] = ::installAsterionRules3872
    registry["asterion-bridge3872.js"] = ::installAsterionBridge3872
    registry["asterion-unified3873.js"] = ::installAsterionUnified3873
    registry["hunter-equipment3880.js"] = ::installHunterEquipment3880
}
