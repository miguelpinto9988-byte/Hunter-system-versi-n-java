package hunter.entry.stage2

import hunter.app.installmesh3d.installMesh3d
import hunter.app.installexercise3d.installExercise3d
import hunter.app.installanatomymodel.installAnatomyModel
import hunter.app.installcharacter3d.installCharacter3d
import hunter.app.installapp.installApp
import hunter.app.installv2.installV2

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["mesh3d.js"] = ::installMesh3d
    registry["exercise-3d.js"] = ::installExercise3d
    registry["anatomy-model.js"] = ::installAnatomyModel
    registry["character-3d.js"] = ::installCharacter3d
    registry["app.js"] = ::installApp
    registry["v2.js"] = ::installV2
}
