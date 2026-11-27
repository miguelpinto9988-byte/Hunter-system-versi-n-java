package hunter.entry.stage0

import hunter.app.installengine.installEngine
import hunter.app.installcatalog.installCatalog
import hunter.app.installcatalogv3.installCatalogV3
import hunter.app.installknowledge.installKnowledge
import hunter.app.installadapt.installAdapt
import hunter.app.installlearning.installLearning
import hunter.app.installcoach.installCoach
import hunter.app.installschedule.installSchedule
import hunter.app.installobjectives.installObjectives
import hunter.app.installintent.installIntent
import hunter.app.installgoalsv14.installGoalsV14
import hunter.app.installbodyknowledge.installBodyKnowledge
import hunter.app.installexpansion.installExpansion
import hunter.app.installsourceexercises.installSourceExercises
import hunter.app.installsourceexercisesv13.installSourceExercisesV13
import hunter.app.installexercisesv14.installExercisesV14
import hunter.app.installexercisesv18.installExercisesV18

import hunter.app.installactions.installActions

fun main() {
    installActions()
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["engine.js"] = ::installEngine
    registry["catalog.js"] = ::installCatalog
    registry["catalog-v3.js"] = ::installCatalogV3
    registry["knowledge.js"] = ::installKnowledge
    registry["adapt.js"] = ::installAdapt
    registry["learning.js"] = ::installLearning
    registry["coach.js"] = ::installCoach
    registry["schedule.js"] = ::installSchedule
    registry["objectives.js"] = ::installObjectives
    registry["intent.js"] = ::installIntent
    registry["goals-v14.js"] = ::installGoalsV14
    registry["body-knowledge.js"] = ::installBodyKnowledge
    registry["expansion.js"] = ::installExpansion
    registry["source-exercises.js"] = ::installSourceExercises
    registry["source-exercises-v13.js"] = ::installSourceExercisesV13
    registry["exercises-v14.js"] = ::installExercisesV14
    registry["exercises-v18.js"] = ::installExercisesV18
}
