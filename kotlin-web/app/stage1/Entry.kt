package hunter.entry.stage1

import hunter.app.installexercisesv19.installExercisesV19
import hunter.app.installexercisepackv23.installExercisePackV23
import hunter.app.installcataloguev30.installCatalogueV30
import hunter.app.installinjuryv93.installInjuryV93
import hunter.app.installadaptivev9.installAdaptiveV9
import hunter.app.installtrainingintensity.installTrainingIntensity
import hunter.app.installweekly.installWeekly
import hunter.app.installcreatorfeed.installCreatorFeed
import hunter.app.installsourcesequences.installSourceSequences
import hunter.app.installsourcesequencesv13.installSourceSequencesV13
import hunter.app.installroutineplanner.installRoutinePlanner
import hunter.app.installroutinesv14.installRoutinesV14
import hunter.app.installplanningv14.installPlanningV14
import hunter.app.installcharacters.installCharacters
import hunter.app.installdepthmotion.installDepthMotion
import hunter.app.installcharacterdepth.installCharacterDepth
import hunter.app.installanimation.installAnimation

fun main() {
    val registry: dynamic = js("window.HunterKotlinModules || (window.HunterKotlinModules = Object.create(null))")
    registry["exercises-v19.js"] = ::installExercisesV19
    registry["exercise-pack-v23.js"] = ::installExercisePackV23
    registry["catalogue-v30.js"] = ::installCatalogueV30
    registry["injury-v93.js"] = ::installInjuryV93
    registry["adaptive-v9.js"] = ::installAdaptiveV9
    registry["training-intensity.js"] = ::installTrainingIntensity
    registry["weekly.js"] = ::installWeekly
    registry["creator-feed.js"] = ::installCreatorFeed
    registry["source-sequences.js"] = ::installSourceSequences
    registry["source-sequences-v13.js"] = ::installSourceSequencesV13
    registry["routine-planner.js"] = ::installRoutinePlanner
    registry["routines-v14.js"] = ::installRoutinesV14
    registry["planning-v14.js"] = ::installPlanningV14
    registry["characters.js"] = ::installCharacters
    registry["depth-motion.js"] = ::installDepthMotion
    registry["character-depth.js"] = ::installCharacterDepth
    registry["animation.js"] = ::installAnimation
}
