package hunter.entry.stage5

import hunter.app.installrigpackv23.installRigPackV23
import hunter.app.installanatomy2dv22.installAnatomy2dV22
import hunter.app.installrigcorrectionsv24.installRigCorrectionsV24
import hunter.app.installmotion2d.installMotion2d
import hunter.app.installroutinesv18.installRoutinesV18

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["rig-pack-v23.js"] = ::installRigPackV23
    registry["anatomy-2d-v22.js"] = ::installAnatomy2dV22
    registry["rig-corrections-v24.js"] = ::installRigCorrectionsV24
    registry["motion-2d.js"] = ::installMotion2d
    registry["routines-v18.js"] = ::installRoutinesV18
}
