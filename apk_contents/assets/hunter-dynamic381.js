/* Weekly central-body integration 38.47. */
/* Hunter System V38.1 - generador de sesiones bajo demanda.
 * No enumera ni almacena combinaciones; selecciona de demostraciones 2D existentes.
 * Las restricciones clínicas continúan en HunterPlanner379/Adapt.
 */
(function(root){
'use strict';
const E=root.Engine,S=root.Schedule,A=root.Adapt,W=root.Weekly;
if(!E||!S||!A||!W){console.error('Dynamic381: dependencias no disponibles');return;}
const VERSION='38.51-level-mix-and-feedback';
// Inyectado por la herramienta de compilación tras comprobar nombres y archivos en APK.
const VERIFIED_IDS=new Set(["new46-pullup","new46-chinup","new46-scapularhang","new46-invertedrow","new46-paralleldip","new46-wallhspu","new46-dbpushpress","new46-bander","new46-bandir","new46-waitercarry","new46-bandpullapart","new46-banddiagonal","new46-bandrow","new46-kneelbandlat","new46-serratusband","new46-faceer","new46-trxcurl","new46-trxtriceps","new46-trxfly","new46-trxy","new46-trxpress","new46-trxhighrow","new46-rotationalrow","new46-ringsrotation","new46-ringturnout","new46-cubanrotation","new46-scapdip","new46-bottomclean","new46-bottompress","new46-halo","new46-pushjerk","new46-dbsnatch","new46-hangclean","new46-meadowsrow","new46-ringlat","new46-cableer90","new46-cubanpress","new46-crosscabletri","new46-halfkneellandmine","new46-commandopull","new46-zpress","new46-forearmlever","new46-wristdeviation","new46-wristcurl","new46-wristexte","new46-bandd2","new46-bandd1","new46-supineshoulderir","new46-nordic","new46-reversenordic","new46-copenhagen","new46-airplanefree","new46-stepdown","new46-hiphike","new46-kbswing","new46-sledpush","new46-sledback","new46-ankleinvert","new46-ankleevert","new46-shortfoot","new46-toeyoga","new46-toetowel","new46-monsterwalk","new46-lateralbandwalk","new46-reverseclam","new46-pronehipir","new46-tke","new46-spanishsquat","new46-sissysquat","new46-zercher","new46-overheadsquat","new46-jefferson","new46-goodmorning","new46-seatedhinge","new46-trxsplit","new46-trxsquat","new46-slidelateral","new46-slidereverse","new46-ballham","new46-ballhip","new46-bandkick","new46-walltibia","new46-soleus","new46-ballsqueeze","new46-copenhagendyn","new46-firehydrant","new46-pivotlunge","new46-heelwalk","new46-toewalk","new46-bandplantar","new46-sideadduct","new46-supinebandmarch","new46-turkish","new46-halfturkish","new46-abwheel","new46-lsit","new46-hangingknee","new46-hanginglegs","new46-pallofwalk","new46-suitcasecarry","new46-rackcarry","new46-stirpot","new46-windmill","new46-figureeight","new46-landminetwist","new46-dragonflag","new46-tucklever","new46-hangingwiper","new46-abwheelstand","new46-trxpike","new46-trxtuck","new46-balltuck","new46-ballpike","new46-banddeadbug","new46-bandbird","new46-sidecrow","new46-sidepallof","new46-seatedpallof","new46-kneelchop","new46-kneellift","new46-doublerack","new46-sliderfly","new46-sitthrough","new46-romanknee","new46-burpee","new46-boxjump","new46-shuttle","new46-backrun","new46-ladderinout","new46-singlepogo","new46-mbrotation","new46-mbslam","new46-ropealternating","new46-sprintbrake","new46-doublepogo","new46-forwardhop","new46-crosshop","new46-turnjump","new46-splitjump","new46-droplanding","new46-depthjump","new46-lateralhurdle","new46-mbchest","new46-mbscoop","new46-mboverback","new46-mbcatch","new46-ropedouble","new46-ropecircles","new46-ropeslam","new46-jumpropealt","new46-jumpropeside","new46-ickyshuffle","new46-laddercarioca","new46-tdrill","new46-hexagon","new46-askip","new46-resistedsprint","new46-sledlateral","new46-sledrope","new46-mountainfast","new46-shouldercar","new46-hipcarquad","new46-hip9090lift","new46-sleeper","new46-thoracicroller","new46-frogrock","new46-wristrock","new46-pelvictilt","new46-deepshift","new46-mediannerve","new46-ulnarnerve","new46-sciaticnerve","new46-peronealnerve","new46-quadthoracic","new46-chairthoracic","new46-crossshoulder","new46-stickextension","new46-tricepsstretch","new46-benchprayer","new46-doorpec","new46-foampec","new46-adductorrock","new46-hipcapsule","new46-pronehipcontrol","new46-pronequad","new46-couchstretch","new46-seatedhipir","new46-bentcalfstretch","new46-bigtoestretch","new46-tendonglides","new46-thumbopposition","new46-chinretract","new46-lungerotation","new46-pelvicclock","new46-scapcar","new46-butterfly","hs379-scapular","hs379-palms","hs379-isobiceps","hs379-shoulderset","chair","squat","goblet","wall","incline","pushup","press","neutral","bridge","hinge","rdl","row","bentrow","prone","reverse","curl","hammer","calf","dbcalf","deadbug","bird","plank","heel","sideleg","march","shoulder","ankle","cat","wallslide","lateral","benchpress","inclinebench","kneepush","closepush","squeezepress","sumo","dbsumo","reverselunge","dblunge","split","lateralunge","stepup","wallsit","singlecalf","seatedcalf","singlebridge","dbbridge","kickback","clam","singlehinge","sumodead","pullover","reversefly","towelrow","scapular","overheadpress","frontlift","shouldertap","seatedcurl","concentration","tricep","lyingtricep","reversecurl","sideplank","kneelingplank","reversecrunch","curlup","standingcore","suitcase","fastmarch","sidestep","stepjack","box","heelback","kneeliftcardio","walk","skater","jog","jumpjack","rope","mountain","squatstep","shadowcombo","hipcircle","chestopen","thoracic","hipflex","hamstretch","calfstretch","wrist","neck","breathing","seatedpunch","seatedmarch","seatedstep","heeltoe","legextend","seatedhip","toelift","seatedheel","seatedside","seatedturn","seatedreach","elbowmob","forearmmob","wristmob","fingers","wallcalf","standingabduct","standingextend","standingcurl","tandem","balance","linewalk","doublefront","suitdead","dbsplit","seatedpress","seatedlateral","seatedfly","curlhold","farmerhold","wallplank","supinearms","chair_slow","chair_pause","squat_slow","squat_pause","goblet_slow","goblet_pause","wall_slow","wall_pause","incline_slow","incline_pause","pushup_slow","pushup_pause","press_slow","press_pause","neutral_slow","neutral_pause","bridge_slow","bridge_pause","hinge_slow","hinge_pause","rdl_slow","rdl_pause","row_slow","row_pause","bentrow_slow","bentrow_pause","prone_slow","prone_pause","reverse_slow","reverse_pause","curl_slow","curl_pause","hammer_slow","hammer_pause","calf_slow","calf_pause","dbcalf_slow","dbcalf_pause","deadbug_slow","deadbug_pause","bird_slow","bird_pause","plank_short","plank_easy","heel_slow","heel_pause","sideleg_slow","sideleg_pause","march_short","march_easy","shoulder_short","shoulder_easy","ankle_short","ankle_easy","cat_slow","cat_pause","wallslide_slow","wallslide_pause","lateral_slow","lateral_pause","benchpress_slow","benchpress_pause","inclinebench_slow","inclinebench_pause","kneepush_slow","kneepush_pause","closepush_slow","closepush_pause","squeezepress_slow","squeezepress_pause","sumo_slow","sumo_pause","dbsumo_slow","dbsumo_pause","reverselunge_slow","reverselunge_pause","dblunge_slow","dblunge_pause","split_slow","split_pause","lateralunge_slow","lateralunge_pause","stepup_slow","stepup_pause","wallsit_short","wallsit_easy","singlecalf_slow","singlecalf_pause","seatedcalf_slow","seatedcalf_pause","singlebridge_slow","singlebridge_pause","dbbridge_slow","dbbridge_pause","kickback_slow","kickback_pause","clam_slow","clam_pause","singlehinge_slow","singlehinge_pause","sumodead_slow","sumodead_pause","pullover_slow","pullover_pause","reversefly_slow","reversefly_pause","towelrow_short","towelrow_easy","scapular_slow","scapular_pause","overheadpress_slow","overheadpress_pause","frontlift_slow","frontlift_pause","shouldertap_slow","shouldertap_pause","seatedcurl_slow","seatedcurl_pause","concentration_slow","concentration_pause","tricep_slow","tricep_pause","lyingtricep_slow","lyingtricep_pause","reversecurl_slow","reversecurl_pause","sideplank_short","sideplank_easy","kneelingplank_short","kneelingplank_easy","reversecrunch_slow","reversecrunch_pause","curlup_slow","curlup_pause","standingcore_slow","standingcore_pause","suitcase_short","suitcase_easy","fastmarch_short","fastmarch_easy","sidestep_short","sidestep_easy","stepjack_short","stepjack_easy","box_short","box_easy","heelback_short","heelback_easy","kneeliftcardio_short","kneeliftcardio_easy","walk_short","walk_easy","skater_short","skater_easy","jog_short","jog_easy","jumpjack_short","jumpjack_easy","rope_short","rope_easy","mountain_short","mountain_easy","squatstep_short","squatstep_easy","shadowcombo_short","shadowcombo_easy","hipcircle_short","hipcircle_easy","chestopen_short","chestopen_easy","thoracic_slow","thoracic_pause","hipflex_short","hipflex_easy","hamstretch_short","hamstretch_easy","calfstretch_short","calfstretch_easy","wrist_short","wrist_easy","neck_short","neck_easy","breathing_short","breathing_easy","seatedpunch_short","seatedpunch_easy","seatedmarch_short","seatedmarch_easy","seatedstep_short","seatedstep_easy","heeltoe_slow","heeltoe_pause","legextend_slow","legextend_pause","seatedhip_slow","seatedhip_pause","toelift_slow","toelift_pause","seatedheel_slow","seatedheel_pause","seatedside_slow","seatedside_pause","seatedturn_slow","seatedturn_pause","seatedreach_slow","seatedreach_pause","elbowmob_slow","elbowmob_pause","forearmmob_slow","forearmmob_pause","wristmob_slow","wristmob_pause","fingers_slow","fingers_pause","wallcalf_slow","wallcalf_pause","standingabduct_slow","standingabduct_pause","standingextend_slow","standingextend_pause","standingcurl_slow","standingcurl_pause","tandem_short","tandem_easy","balance_short","balance_easy","linewalk_short","linewalk_easy","doublefront_slow","doublefront_pause","suitdead_slow","suitdead_pause","dbsplit_slow","dbsplit_pause","seatedpress_slow","seatedpress_pause","seatedlateral_slow","seatedlateral_pause","seatedfly_slow","seatedfly_pause","curlhold_short","curlhold_easy","farmerhold_short","farmerhold_easy","wallplank_short","wallplank_easy","supinearms_slow","supinearms_pause","bearcrawl","childpose","contralateral","latstretch","bulgarian","diagonalraise","hipthrust","farmerwalk","inclinefly","dbsidelunge","vstep","heeldig","doubletouch","frontbackstep","boxupper","stepreach","sidebendstand","bearhold","altcurl","althammer","singlelateral","singlefront","singlepress","altfloorpress","singleoverhead","altseatedpress","altseatedcurl","singletricep","squathold","goblethold","bridgehold","calfhold","rowhold","splitpause","slowmarch","sidestepinterval","boxinterval","stepjackinterval","seatedmarchinterval","heelcontrolled","birdcontrolled","abductcontrolled","pau-altpress","pau-overtriceps","pau-arnold","pau-lateralcurl","pau-curlcombo","pau-raisecombo","pau-standingfly","pau-presscombo","pau-kickback","pau-plankup","pau-pushchild","pau-armworld","pau-armcircle-f","pau-armcircle-b","pau-armupdown","pau-armpulses","pau-armfly","pau-armsback","pau-armraise","pau-armcurl","lowhop","squatjump","skaterhop","linehop","fastfeet","shuffle","highknees","lungeknee","stepcross","skaterreach","dbmarch","ankledrive","front-seated-reference","squat-control18","goblet-control18","rdl-control18","press-control18","neutral-control18","benchpress-control18","overheadpress-control18","incline-control18","kneepush-control18","curl-control18","hammer-control18","row-control18","bentrow-control18","bridge-control18","calf-control18","split-control18","standingabduct-control18","legextend-control18","bird-control18","deadbug-control18","pushup-control19","decline19","pike19","archer19","squat-15-19","goblet-15-19","split-15-19","row-15-19","overhead-15-19","single-squat19","singlehinge-free19","single-rdl19","full-sideplank19","sideplank-lift19","plankreach19","singlebridge-pause19","singlecalf-pause19","lateral-pause19","curl-pause19","w-t19","y-t-w19","shuffle-touch19","skater-stick19","home-diamond23","home-diamond23-tempo23","home-diamond23-pause23","home-staggered23","home-staggered23-tempo23","home-staggered23-pause23","home-spiderman23","home-spiderman23-tempo23","home-spiderman23-pause23","home-handrelease23","home-handrelease23-tempo23","home-handrelease23-pause23","home-hindu23","home-hindu23-tempo23","home-hindu23-pause23","home-push-tap23","home-push-tap23-tempo23","home-push-tap23-pause23","home-push-power23","home-push-power23-tempo23","home-push-power23-pause23","home-pike-bench23","home-pike-bench23-tempo23","home-pike-bench23-pause23","home-push-one23","home-push-one23-tempo23","home-push-one23-pause23","home-cossack23","home-cossack23-tempo23","home-cossack23-pause23","home-skater-squat23","home-skater-squat23-tempo23","home-skater-squat23-pause23","home-shrimp23","home-shrimp23-tempo23","home-shrimp23-pause23","home-pistol-box23","home-pistol-box23-tempo23","home-pistol-box23-pause23","home-pistol-db23","home-pistol-db23-tempo23","home-pistol-db23-pause23","home-step-lateral23","home-step-lateral23-tempo23","home-step-lateral23-pause23","home-step-cross23","home-step-cross23-tempo23","home-step-cross23-pause23","home-curtsy23","home-curtsy23-tempo23","home-curtsy23-pause23","home-walking-lunge23","home-walking-lunge23-tempo23","home-walking-lunge23-pause23","home-squat-lunge23","home-squat-lunge23-tempo23","home-squat-lunge23-pause23","home-lunge-front23","home-lunge-front23-tempo23","home-lunge-front23-pause23","home-ham-slide23","home-ham-slide23-tempo23","home-ham-slide23-pause23","home-ham-slide-one23","home-ham-slide-one23-tempo23","home-ham-slide-one23-pause23","home-bridge-walk23","home-bridge-walk23-tempo23","home-bridge-walk23-pause23","home-bridge-long23","home-bridge-long23-tempo23","home-bridge-long23-pause23","home-frog23","home-frog23-tempo23","home-frog23-pause23","home-airplane23","home-airplane23-tempo23","home-airplane23-pause23","home-reverse-plank-lift23","home-reverse-plank-lift23-tempo23","home-reverse-plank-lift23-pause23","home-reverse-knee23","home-reverse-knee23-tempo23","home-reverse-knee23-pause23","home-hollow-rock23","home-hollow-rock23-tempo23","home-hollow-rock23-pause23","home-hollow-tuck23","home-hollow-tuck23-tempo23","home-hollow-tuck23-pause23","home-vup23","home-vup23-tempo23","home-vup23-pause23","home-legraise23","home-legraise23-tempo23","home-legraise23-pause23","home-flutter23","home-flutter23-tempo23","home-flutter23-pause23","home-scissors23","home-scissors23-tempo23","home-scissors23-pause23","home-knee-tuck23","home-knee-tuck23-tempo23","home-knee-tuck23-pause23","home-side-thread23","home-side-thread23-tempo23","home-side-thread23-pause23","home-side-knee23","home-side-knee23-tempo23","home-side-knee23-pause23","home-body-saw23","home-body-saw23-tempo23","home-body-saw23-pause23","home-plank-toe23","home-plank-toe23-tempo23","home-plank-toe23-pause23","home-bear-tap23","home-bear-tap23-tempo23","home-bear-tap23-pause23","home-bear-return23","home-bear-return23-tempo23","home-bear-return23-pause23","home-crab23","home-crab23-tempo23","home-crab23-pause23","home-thruster-db23","home-thruster-db23-tempo23","home-thruster-db23-pause23","home-clean-db23","home-clean-db23-tempo23","home-clean-db23-pause23","home-rack-lunge23","home-rack-lunge23-tempo23","home-rack-lunge23-pause23","home-renegade23","home-renegade23-tempo23","home-renegade23-pause23","home-renegade-knee23","home-renegade-knee23-tempo23","home-renegade-knee23-pause23","home-zottman23","home-zottman23-tempo23","home-zottman23-pause23","home-hammer-cross23","home-hammer-cross23-tempo23","home-hammer-cross23-pause23","home-floor-fly23","home-floor-fly23-tempo23","home-floor-fly23-pause23","gym-bench-bb23","gym-bench-bb23-tempo23","gym-bench-bb23-pause23","gym-close-bb23","gym-close-bb23-tempo23","gym-close-bb23-pause23","gym-incline-bb23","gym-incline-bb23-tempo23","gym-incline-bb23-pause23","gym-decline-bb23","gym-decline-bb23-tempo23","gym-decline-bb23-pause23","gym-press-seated-bb23","gym-press-seated-bb23-tempo23","gym-press-seated-bb23-pause23","gym-press-bb23","gym-press-bb23-tempo23","gym-press-bb23-pause23","gym-front-bb23","gym-front-bb23-tempo23","gym-front-bb23-pause23","gym-back-bb23","gym-back-bb23-tempo23","gym-back-bb23-pause23","gym-box-bb23","gym-box-bb23-tempo23","gym-box-bb23-pause23","gym-sumo-bb23","gym-sumo-bb23-tempo23","gym-sumo-bb23-pause23","gym-dead-bb23","gym-dead-bb23-tempo23","gym-dead-bb23-pause23","gym-rdl-bb23","gym-rdl-bb23-tempo23","gym-rdl-bb23-pause23","gym-thrust-bb23","gym-thrust-bb23-tempo23","gym-thrust-bb23-pause23","gym-row-bb23","gym-row-bb23-tempo23","gym-row-bb23-pause23","gym-row-under-bb23","gym-row-under-bb23-tempo23","gym-row-under-bb23-pause23","gym-pendlay23","gym-pendlay23-tempo23","gym-pendlay23-pause23","gym-landmine-press23","gym-landmine-press23-tempo23","gym-landmine-press23-pause23","gym-landmine-row23","gym-landmine-row23-tempo23","gym-landmine-row23-pause23","gym-curl-bb23","gym-curl-bb23-tempo23","gym-curl-bb23-pause23","gym-preacher-bb23","gym-preacher-bb23-tempo23","gym-preacher-bb23-pause23","gym-lat-wide23","gym-lat-wide23-tempo23","gym-lat-wide23-pause23","gym-lat-neutral23","gym-lat-neutral23-tempo23","gym-lat-neutral23-pause23","gym-lat-under23","gym-lat-under23-tempo23","gym-lat-under23-pause23","gym-straight-pull23","gym-straight-pull23-tempo23","gym-straight-pull23-pause23","gym-cable-row23","gym-cable-row23-tempo23","gym-cable-row23-pause23","gym-cable-high-row23","gym-cable-high-row23-tempo23","gym-cable-high-row23-pause23","gym-cable-one-row23","gym-cable-one-row23-tempo23","gym-cable-one-row23-pause23","gym-face-pull23","gym-face-pull23-tempo23","gym-face-pull23-pause23","gym-cable-fly23","gym-cable-fly23-tempo23","gym-cable-fly23-pause23","gym-cable-low-fly23","gym-cable-low-fly23-tempo23","gym-cable-low-fly23-pause23","gym-cable-high-fly23","gym-cable-high-fly23-tempo23","gym-cable-high-fly23-pause23","gym-rope-tricep23","gym-rope-tricep23-tempo23","gym-rope-tricep23-pause23","gym-cable-one-tricep23","gym-cable-one-tricep23-tempo23","gym-cable-one-tricep23-pause23","gym-cable-overhead23","gym-cable-overhead23-tempo23","gym-cable-overhead23-pause23","gym-cable-curl23","gym-cable-curl23-tempo23","gym-cable-curl23-pause23","gym-cable-lateral23","gym-cable-lateral23-tempo23","gym-cable-lateral23-pause23","gym-wood-chop23","gym-wood-chop23-tempo23","gym-wood-chop23-pause23","gym-pallof23","gym-pallof23-tempo23","gym-pallof23-pause23","gym-leg-press23","gym-leg-press23-tempo23","gym-leg-press23-pause23","gym-hack23","gym-hack23-tempo23","gym-hack23-pause23","gym-smith-squat23","gym-smith-squat23-tempo23","gym-smith-squat23-pause23","gym-smith-calf23","gym-smith-calf23-tempo23","gym-smith-calf23-pause23","gym-chest-machine23","gym-chest-machine23-tempo23","gym-chest-machine23-pause23","gym-pec-deck23","gym-pec-deck23-tempo23","gym-pec-deck23-pause23","gym-leg-extension23","gym-leg-extension23-tempo23","gym-leg-extension23-pause23","gym-ham-seated23","gym-ham-seated23-tempo23","gym-ham-seated23-pause23","gym-ham-lying23","gym-ham-lying23-tempo23","gym-ham-lying23-pause23","gym-hip-machine23","gym-hip-machine23-tempo23","gym-hip-machine23-pause23","gym-treadmill23","gym-treadmill23-tempo23","gym-treadmill23-pause23","gym-row-erg23","gym-row-erg23-tempo23","gym-row-erg23-pause23","gym-press4530","gym-press4530-ecc30","gym-press4530-hold30","gym-presssingle30","gym-presssingle30-ecc30","gym-presssingle30-hold30","gym-pendulum30","gym-pendulum30-ecc30","gym-pendulum30-hold30","gym-belt30","gym-belt30-ecc30","gym-belt30-hold30","gym-vsquat30","gym-vsquat30-ecc30","gym-vsquat30-hold30","gym-smithsplit30","gym-smithsplit30-ecc30","gym-smithsplit30-hold30","gym-smiththrust30","gym-smiththrust30-ecc30","gym-smiththrust30-hold30","gym-smithrdl30","gym-smithrdl30-ecc30","gym-smithrdl30-hold30","gym-hamstanding30","gym-hamstanding30-ecc30","gym-hamstanding30-hold30","gym-tibialis30","gym-tibialis30-ecc30","gym-tibialis30-hold30","gym-calfseat30","gym-calfseat30-ecc30","gym-calfseat30-hold30","gym-calfdonkey30","gym-calfdonkey30-ecc30","gym-calfdonkey30-hold30","gym-adduct30","gym-adduct30-ecc30","gym-adduct30-hold30","gym-kickmachine30","gym-kickmachine30-ecc30","gym-kickmachine30-hold30","gym-thrustmachine30","gym-thrustmachine30-ecc30","gym-thrustmachine30-hold30","gym-abmachine30","gym-abmachine30-ecc30","gym-abmachine30-hold30","gym-backmachine30","gym-backmachine30-ecc30","gym-backmachine30-hold30","gym-rotary30","gym-rotary30-ecc30","gym-rotary30-hold30","gym-pullassist30","gym-pullassist30-ecc30","gym-pullassist30-hold30","gym-dipassist30","gym-dipassist30-ecc30","gym-dipassist30-hold30","gym-highrow30","gym-highrow30-ecc30","gym-highrow30-hold30","gym-supportrow30","gym-supportrow30-ecc30","gym-supportrow30-hold30","gym-pressone30","gym-pressone30-ecc30","gym-pressone30-hold30","gym-pressincline30","gym-pressincline30-ecc30","gym-pressincline30-hold30","gym-shoulderm30","gym-shoulderm30-ecc30","gym-shoulderm30-hold30","gym-lateralm30","gym-lateralm30-ecc30","gym-lateralm30-hold30","gym-reversedeck30","gym-reversedeck30-ecc30","gym-reversedeck30-hold30","gym-preacherm30","gym-preacherm30-ecc30","gym-preacherm30-hold30","gym-tricepsm30","gym-tricepsm30-ecc30","gym-tricepsm30-hold30","gym-pulloverm30","gym-pulloverm30-ecc30","gym-pulloverm30-hold30","gym-hipflexc30","gym-hipflexc30-ecc30","gym-hipflexc30-hold30","gym-abductc30","gym-abductc30-ecc30","gym-abductc30-hold30","gym-adductc30","gym-adductc30-ecc30","gym-adductc30-hold30","gym-kickc30","gym-kickc30-ecc30","gym-kickc30-hold30","gym-crunchc30","gym-crunchc30-ecc30","gym-crunchc30-hold30","gym-pullthrough30","gym-pullthrough30-ecc30","gym-pullthrough30-hold30","gym-stepmill30","gym-stepmill30-ecc30","gym-stepmill30-hold30","gym-bike30","gym-bike30-ecc30","gym-bike30-hold30","gym-elliptical30","gym-elliptical30-ecc30","gym-elliptical30-hold30","gym-skierg30","gym-skierg30-ecc30","gym-skierg30-hold30","gym-airbike30","gym-airbike30-ecc30","gym-airbike30-hold30","gym-recumbent30","gym-recumbent30-ecc30","gym-recumbent30-hold30","home-squatnarrow30","home-squatnarrow30-ecc30","home-squatnarrow30-hold30","home-squatheels30","home-squatheels30-ecc30","home-squatheels30-hold30","home-squatpulse30","home-squatpulse30-ecc30","home-squatpulse30-hold30","home-cyclistdb30","home-cyclistdb30-ecc30","home-cyclistdb30-hold30","home-lungefront30","home-lungefront30-ecc30","home-lungefront30-hold30","home-lungeoffset30","home-lungeoffset30-ecc30","home-lungeoffset30-hold30","home-splithigh30","home-splithigh30-ecc30","home-splithigh30-hold30","home-rdlstagger30","home-rdlstagger30-ecc30","home-rdlstagger30-hold30","home-rdlsuit30","home-rdlsuit30-ecc30","home-rdlsuit30-hold30","home-bridgemarch30","home-bridgemarch30-ecc30","home-bridgemarch30-hold30","home-bridgesqueeze30","home-bridgesqueeze30-ecc30","home-bridgesqueeze30-hold30","home-thrustsingle30","home-thrustsingle30-ecc30","home-thrustsingle30-hold30","home-plankjack30","home-plankjack30-ecc30","home-plankjack30-hold30","home-plankstep30","home-plankstep30-ecc30","home-plankstep30-hold30","home-plankdrag30","home-plankdrag30-ecc30","home-plankdrag30-hold30","home-sideabduct30","home-sideabduct30-ecc30","home-sideabduct30-hold30","home-sidehip30","home-sidehip30-ecc30","home-sidehip30-hold30","home-bearreach30","home-bearreach30-ecc30","home-bearreach30-hold30","home-deadbugpress30","home-deadbugpress30-ecc30","home-deadbugpress30-hold30","home-reversebird30","home-reversebird30-ecc30","home-reversebird30-hold30","home-scappush30","home-scappush30-ecc30","home-scappush30-hold30","home-pushwide30","home-pushwide30-ecc30","home-pushwide30-hold30","home-pushcloseknee30","home-pushcloseknee30-ecc30","home-pushcloseknee30-hold30","home-pikepause30","home-pikepause30-ecc30","home-pikepause30-hold30","home-lateralstepover30","home-lateralstepover30-ecc30","home-lateralstepover30-hold30","home-hopforward30","home-hopforward30-ecc30","home-hopforward30-hold30","home-hip909030","home-hip909030-ecc30","home-hip909030-hold30","home-wallangel30","home-wallangel30-ecc30","home-wallangel30-hold30"]);
const TYPES={train:'strength',cardio:'cardio',mobility:'recovery',rest:'rest'};
const CATEGORY={strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'};
const SPANISH={strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'};
const LOWER=new Set(['Piernas','Cadera']),UPPER=new Set(['Pecho','Espalda','Hombros','Brazos']);
const byId=new Map(E.exercises.map(e=>[e.id,e]));
const classicById=E.byId;
E.byId=id=>byId.get(id)||classicById(id);
const available=E.exercises.filter(e=>VERIFIED_IDS.has(e.id)&&e.modelPath30&&e.demoPath30&&
  ['Fuerza','Cardio','Movilidad'].includes(e.category)&&e.group&&Array.isArray(e.zones)&&
  typeof e.level==='number'&&Array.isArray(e.requirements));
const fieldNames=['age','goal','goalGroup','finalGoal','finalTarget','focus','difficulty','experience','equipment',
 'gymEquipment23','bench','step','minutes','frequency','dailyMissions','routineExercises3878','trainingMode','trainingScale',
 'weekPlan','weekFocus','injuries','restrictions','health','limits','preferredTrainer','load','restSeconds','materials46','capabilities46','dynamicRequestedFocus3847'];
const asJSON=o=>JSON.stringify(o??null);
const hash=s=>{let h=2166136261;for(let i=0;i<s.length;i++){h^=s.charCodeAt(i);h=Math.imul(h,16777619);}return h>>>0;};
const profileKey=p=>hash(asJSON(fieldNames.map(k=>[k,p?.[k]]))).toString(36);
const mod=(a,n)=>n?((a%n)+n)%n:0;
const injury=p=>!!(p?.injuries||[]).some(x=>x.stage!=='past')||!!p?.limits||!!p?.health?.special||['mild','moderate','strong'].includes(p?.health?.pain);
function gate(p){
 if(!p)return {ok:false,reason:'Falta completar el perfil.'};
 if(injury(p)&&root.HunterPlanner379?.status){const info=root.HunterPlanner379.status(p);return {...info,ok:!!info.ok,reduced:info.reduced!==false};}
 const m=A.mode(p);return {ok:!!m.auto,reduced:!!m.reduced,reason:m.reason||''};
}
function activeLog(l){return !!(l&&(W.active(l)||l.complete||l.manual||l.pain));}
function locked(day,logs){const l=logs?.[day.date];return !!(day.customized||day.routineId||day.creatorAssigned||day.manual||activeLog(l)||day.tasks?.some(t=>t.done));}
function recent(logs,date,days=14){return Object.entries(logs||{}).filter(([day,l])=>day<date&&day>=E.plus(date,-days)&&l);}
function feedback(logs,date){
 const rows=recent(logs,date,7),done=rows.map(([,l])=>l).filter(l=>l.complete&&!l.pain&&!l.manual);
 const efforts=done.map(l=>+l.rpe).filter(x=>x>=1&&x<=10),avg=efforts.length?efforts.reduce((a,b)=>a+b,0)/efforts.length:0;
 const strain=rows.some(([,l])=>l.pain||l.fatigue||l.rpe>=8)||
  !!root.HunterConstraints3811?.symptomTrend?.(logs,date)?.recentSymptom;
 const adherence=rows.length?done.length/rows.length:0;
 return {strain,avg,adherence,completed:done.length,canProgress:done.length>=2&&adherence>=.8&&avg>0&&avg<=7&&!strain};
}
function compatible(e,p,risk){
 if(!e||!byId.has(e.id)||!root.HunterGroups49.eligible(e,p))return false;
 // Comprobar equipo y nivel incluso cuando el perfil registra una lesión.
 if(!root.HunterMaterials3814?.eligible?.(e,p))return false;
 if(risk){return !!(root.HunterConstraints3811?.allowed?.(e,p) ?? root.HunterPlanner379?.approved?.(e,p));}
 try{if(!A.allowed(e,p))return false;}catch(_){return false;}
 if(e.equipment==='db'&&!['db','gym'].includes(p.equipment))return false;
 if(e.gymOnly23&&p.equipment!=='gym')return false;
 if(e.equipment==='gym'&&p.equipment!=='gym')return false;
 const difficulty=['basic','intermediate','advanced'].indexOf(p.difficulty);
 if(root.HunterGroups49.level(e)>Math.max(0,difficulty<0?(p.experience==='regular'?1:0):difficulty))return false;
 return true;
}
const poolMemo46=new Map();
function pool(p,kind){const key=kind+'|'+asJSON([...fieldNames,'physicalLimits','preferences','goalAutoRestrictions374'].map(k=>[k,p?.[k]]));if(poolMemo46.has(key))return poolMemo46.get(key).slice();const status=gate(p);if(!status.ok)return [];const result=available.filter(e=>root.HunterGroups49.inKind(e,kind)&&compatible(e,p,injury(p)));poolMemo46.set(key,result);if(poolMemo46.size>12)poolMemo46.delete(poolMemo46.keys().next().value);return result.slice();}

const goalGroups={upper:['Pecho','Espalda','Brazos','Hombros'],lower:['Piernas','Cadera'],core:['Core','Abdomen'],
 general:['Piernas','Pecho','Espalda','Cadera','Core','Abdomen','Hombros','Brazos']};
function sortPool(xs,p,seed,kind,previous,used,counts,weekLoad,patternCounts,tracker,date,selected=[]){
 const focus=p.focus||'general',order=goalGroups[focus]||goalGroups.general;
 return xs.map(e=>{
  const was=previous.has(e.id)?45:0,distinct=counts[e.group]||0;
  let priority=order.includes(e.group)?26-order.indexOf(e.group)*2:0;
  if(kind==='recovery')priority=10;
  if(kind==='cardio'&&p.goal==='endurance')priority+=12;
  if(p.goal==='muscle'&&UPPER.has(e.group))priority+=8;
  if(p.goal==='strength'&&LOWER.has(e.group))priority+=8;
  const hasGear=e.equipment==='body'?2:0;
  const weeklyScore=root.HunterTraining387?.score?.(e,{weekly:weekLoad,dayCounts:counts,dayPatterns:patternCounts})||0;
  const upcomingScore=tracker?.score?.(e,date)||0;
   const supportScore=root.HunterConstraints3811?.rank?.(e,p)||0;
   const metadataScore=root.HunterMetadata3812?.score?.(e,p,kind)||0;
   const rank=root.HunterGroups49.score(e,p,kind,selected)+priority+(p.dynamicGymPreferred3827&&(e.gymOnly23||e.equipment==='gym')?45:0)+hasGear-was-distinct*24-(used.has(root.HunterGroups49.family(e))?100:0)-e.level*2+weeklyScore+upcomingScore+supportScore+metadataScore;
  return {e,rank,lottery:hash(seed+'|'+e.id)};
 }).sort((a,b)=>b.rank-a.rank||a.lottery-b.lottery||a.e.id.localeCompare(b.e.id));
}
function historyIds(logs,date){const prior=new Set();for(const [,l]of recent(logs,date,8)){
  if(!l.complete)continue;for(const x of l.items||[])prior.add(x.id);
 }return prior;}
function dose(e,p,kind,status,history,date){
 const gentle=!!status.reduced,low=history.strain,minutes=Math.max(10,Math.min(60,+p.minutes||20));
 let sets=kind==='recovery'||gentle||low?1:minutes>=40?3:2;
 let target=e.unit==='sec'?(kind==='cardio'?30:20):(kind==='recovery'?8:p.goal==='muscle'?10:8);
 if(gentle||low){target=e.unit==='sec'?15:6;}
 // Sólo registros favorables permiten progresión; se preservan las series previamente completadas.
 if(!gentle&&!low&&history.canProgress)target=Math.min(e.unit==='sec'?90:15,target+(e.unit==='sec'?5:1));
 return {id:e.id,sets,target,rest:kind==='cardio'?45:kind==='recovery'?30:90,
  dynamic381:true,originDate381:date,preview2D381:'demos30/'+e.id+'.svg',
  noAutoLoad:gentle||low,safetyNote:gentle?'Dosis inicial conservadora; interrumpe si hay dolor nuevo o empeoramiento.':'Respeta técnica y recuperaciones.'};
}
function choose(p,kind,seed,logs={},date=E.dateKey(),usedBases=new Set(),tracker=null,weekIndex=0){
 const status=gate(p);if(!status.ok)return {items:[],blocked:status.reason,requested:kind};
 if(kind==='rest')return {items:[],effective:'rest',requested:'rest'};
 // Mantener el tipo elegido en el calendario. Ante catálogo incompatible, pedir revisión en vez de cambiar fuerza por cardio/movilidad.
 const options=[kind];
 let list=[],effective=kind;
 // Días de fuerza: la zona elegida en Perfil tiene prioridad sobre el foco general.
 const weekday=S.weekday(date),dailyType=p?.weekPlan?.[weekday];
 const explicitFocus=Object.prototype.hasOwnProperty.call(goalGroups,p?.dynamicRequestedFocus3847)?p.dynamicRequestedFocus3847:null;
 const dailyFocus=kind==='strength'?(explicitFocus||(dailyType==='train'?(['lower','core'].includes(p?.weekFocus?.[weekday])?p.weekFocus[weekday]:'upper'):null)):null;
 const allowedStrengthGroups=dailyFocus&&dailyFocus!=='general'?new Set(goalGroups[dailyFocus]):null;
 for(const type of options){
  list=pool(p,type);
  if(type==='strength'&&allowedStrengthGroups)list=list.filter(e=>root.HunterGroups49.matches(e,dailyFocus));
  if(list.length){effective=type;break;}
 }
 if(!list.length)return {items:[],blocked:'No se encontraron ejercicios demostrados compatibles con las restricciones declaradas.',requested:kind};
 const tierCategory=effective==='cardio'?'cardio':effective==='recovery'?'mobility':
   ['upper','lower','core'].includes(dailyFocus)?dailyFocus:
   ['upper','lower','core'].includes(p.focus)?p.focus:'upper';
 const levelContext=root.HunterLevel3851?.context(p,logs,date,tierCategory,5)||null;
 // Permitir UNA variante de prueba del siguiente nivel sólo con registros
 // suficientes; no cambiar p.difficulty ni habilitar saltos/potencia.
 if(levelContext?.preview){
   let options=pool({...p,difficulty:'advanced'},effective);
   if(effective==='strength'&&allowedStrengthGroups)options=options.filter(e=>root.HunterGroups49.matches(e,dailyFocus));
   const merged=new Map([...list,...options].map(e=>[e.id,e]));list=[...merged.values()];
 }
 const cap=root.HunterMaterials3814?.sessionCap?.(p,kind,status)??Math.max(2,Math.min(7,Number(p.dailyMissions)||3));
 const baseCount=effective==='recovery'?Math.min(3,cap):cap;
  const count=root.HunterIntegration3869?.activityLimit?.(baseCount)??baseCount;
 const hist=historyIds(logs,date),rawFeedback=feedback(logs,date),fb=root.HunterCategoryProgress51?.feedback(rawFeedback,logs,date)||rawFeedback,selected=[],used=new Set();const counts={},patternCounts={};
  const weeklyLoad=root.HunterTraining387?.load?.(logs,date)||{groups:{},patterns:{},ids:{}};
 const selectionContext=root.HunterGroups49.context(p,effective,dailyFocus||p.focus||'general',{logs,date,tracker,status,feedback:fb});
 for(let i=0;i<count;i++){
  const candidates=list.filter(e=>!used.has(root.HunterGroups49.family(e))&&!usedBases.has(root.HunterGroups49.family(e)));
  let choices=candidates.length?candidates:list.filter(e=>!used.has(root.HunterGroups49.family(e)));
  choices=root.HunterIntegration3869?.prefers?.(choices)??choices;
   choices=root.HunterGroups49.selectionPool(choices,selected,count,selectionContext);
  choices=root.HunterLevel3851?.levelPool(choices,selected,levelContext,count)||choices;
  if(!choices.length)break;
  const next=sortPool(choices,p,seed+'-'+i,effective,hist,used,counts,weeklyLoad,patternCounts,tracker,date,selected)[0].e;
  selected.push(next);used.add(root.HunterGroups49.family(next));counts[next.group]=(counts[next.group]||0)+1;
   const pat=root.HunterTraining387?.pattern?.(next);if(pat)patternCounts[pat]=(patternCounts[pat]||0)+1;
 }
 const category51=root.HunterCategoryProgress51?.categoryOfItems(selected,effective,dailyFocus)||null;
 const items=selected.map(e=>{const basic=dose(e,p,effective,status,fb,date);
   const adjusted=root.HunterTraining387?.dose?.(e,p,effective,status,fb,date,basic,weeklyLoad)||basic;
   const classified=root.HunterMetadata3812?.dose?.(e,p,effective,status,fb,date,adjusted)||adjusted;
   const legacy=root.HunterProgress3815?.dose?.(e,p,effective,status,fb,date,classified,weekIndex,logs)||classified;
   const finalDose=root.HunterUnified3827?.dose?.(e,p,effective,status,fb,date,legacy,weekIndex,logs,basic)||legacy;const roleDose=root.HunterGroups49.adjustDose(e,p,effective,finalDose,{status,feedback:fb,logs,date});return root.HunterCatalog46?.cap(e,p,roleDose,{status,feedback:fb,logs,date})||roleDose;});
 const calculated=root.HunterCategoryProgress51?.adjustItems(items,{profile:p,logs,date,category:category51,status,kind:effective});
 const resultItems=(calculated?.items||items).map(item=>root.HunterLevel3851?.dose(item,E.byId(item.id),levelContext)||item);
 for(const item of resultItems){const e=byId.get(item.id);if(e)tracker?.add?.(date,e,item.sets);}
 return {items:resultItems,effective,requested:kind,substituted:effective!==kind,count:resultItems.length,progression51:calculated?.progress||null,level3851:levelContext?{profileTier:levelContext.profileTier,ceiling:levelContext.ceiling,preview:levelContext.preview,reason:levelContext.failure.why,quota:root.HunterLevel3851.idealQuota(levelContext,count)}:null};
}
function planKey(p,logs,today){const past=recent(logs,today,90).map(([d,l])=>[d,l.complete,l.pain,l.fatigue,l.rpe,l.manual,l.kind,String(l.note||'').slice(0,500),(l.items||[]).map(x=>[x.id,(x.actual||[]).map(a=>!!a.done)])]);
 return VERSION+'|'+profileKey(p)+'|'+today+'|'+hash(asJSON(past)).toString(36);
}
function patch(c,p,logs={},today=E.dateKey()){
 if(!c?.days?.length||!p||!S.valid(p))return 0;
 // No reanudar un programa que el revisor semanal detuvo expresamente.
 if(c.reviews?.at(-1)?.action==='pausar')return 0;
 const key=planKey(p,logs,today);if(c.dynamicKey381===key&&c.days.every(d=>d.date<today||d.scheduleChoice==='rest'||locked(d,logs)||d.dynamic381||d.blocked381))return 0;
 let changes=0;let groupUsed=new Set(),weekSegment=-1;const risk=injury(p);
 const tracker=root.HunterPlan3810?.tracker?.(c,logs,today)||null;
 for(const [index,d]of c.days.entries()){
  const segment=root.HunterSchedule3813?.weekSegment?.(index) ?? Math.floor(index/7);
  if(segment!==weekSegment){weekSegment=segment;groupUsed=new Set();}
  if(d.date<today||locked(d,logs)||root.HunterStable3828?.keep?.(d,p,logs,today))continue;
  const selected=p.weekPlan[S.weekday(d.date)]||'rest',kind=TYPES[selected];
  if(selected==='rest'){
   if(d.kind!=='rest'||d.items?.length||d.dynamic381){d.kind='rest';d.items=[];d.title='Descanso elegido';d.tasks=[];d.scheduleChoice='rest';d.dynamic381=true;delete d.blocked378;delete d.blocked381;changes++;}
   continue;
  }
  if(d.dynamicKey381===key&&d.dynamic381&&d.items?.length)continue;
  const res=choose(p,kind,profileKey(p)+'|'+d.date+'|'+c.index,logs,d.date,groupUsed,tracker,segment);
  d.kind=kind;d.scheduleChoice=selected;d.items=res.items;d.tasks=[];d.dynamic381=true;d.dynamicKey381=key;d.progression51=res.progression51||null;
  const shownKind=kind==='strength'?({lower:'Tren inferior',core:'Tren central'}[p?.weekFocus?.[S.weekday(d.date)]]||'Tren superior'):SPANISH[kind];
  d.title=shownKind+(res.substituted?' · alternativa de '+SPANISH[res.effective]:' · plan dinámico');
  if(res.blocked){d.title=SPANISH[kind]+' · requiere revisión';d.blocked381=res.blocked;d.blocked378=res.blocked;
   d.tasks=[{id:'review-dynamic381',text:'Revisar restricciones para encontrar ejercicios compatibles',done:false}];
   d.planningNote='Se conserva el día elegido y el historial; no hay selección automática segura.';
  }else{delete d.blocked381;delete d.blocked378;
   d.planningNote=(res.substituted?'No había opciones verificadas del tipo elegido; se muestran '+SPANISH[res.effective]+'. ':'')+
   'Generado bajo demanda desde demostraciones 2D existentes, sin rutinas combinatorias precargadas.'+
   (res.progression51?.lastScore?(' Progresión '+res.progression51.label+': esfuerzo '+res.progression51.lastScore+'/10, '+
    ({repeticiones_o_tiempo:'pequeño ajuste de repeticiciones/tiempo',una_serie:'una serie adicional controlada',reduccion_suave:'reducción moderada',mantener:'mantener dosis'}[res.progression51.action]||'mantener dosis')+'.'):'');
   if(risk){d.adapted378=true;d.adaptation379='37.9';d.originalCategory378=SPANISH[kind];}
   for(const item of res.items){const ex=byId.get(item.id);if(ex)groupUsed.add(root.HunterGroups49.family(ex));}
  }
  delete d.autoTemplateId;
  changes++;
 }
 c.dynamicKey381=key;c.dynamicVersion381=VERSION;
 if(changes)c.decision='Generación dinámica de sesiones: calendario, objetivo, historial y restricciones. Se conservan sesiones registradas.';
 return changes;
}
const previousMake=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){const c=previousMake(p,start,index,last,logs);patch(c,p,logs,start);return c;};
const previousCurrent=currentCycle;
currentCycle=function(){const c=previousCurrent();if(!c||!state.profile)return c;
 const changes=patch(c,state.profile,state.logs||{},E.dateKey());
 const today=c.days.find(d=>d.date===E.dateKey());
 const updated=today&&root.HunterPlanner379?.repairEmptyLog?.(today);
 if((changes||updated)&&typeof save==='function'&&!root.Runtime20?.initializing)save();
 return c;};
// Rutinas de muestra ligeras, calculadas sólo cuando se consulta la biblioteca.
const originalAll=allRoutines;
function templates(p,logs={}){
 if(!p||!gate(p).ok)return [];
 const today=E.dateKey(),pastLogs=Object.fromEntries(Object.entries(logs||{}).filter(([d])=>d<today)),labels=[['strength','Fuerza'],['cardio','Cardio'],['recovery','Movilidad']];
 const result=[];for(const [kind,name]of labels)for(let n=0;n<3;n++){
  const q=choose(p,kind,profileKey(p)+'|'+today+'|template'+n,pastLogs,E.plus(today,n));
  if(!q.items.length)continue;
  const requirements=[...new Set(q.items.flatMap(x=>byId.get(x.id)?.requirements||[]))];
  result.push({id:'dynamic381-'+kind+'-'+n,name:name+' dinámica · '+(n+1),
   category:CATEGORY[q.effective],description:'Generada para este perfil a partir del catálogo 2D; se reevalúan restricciones antes de asignar.',
   items:q.items,requirements,level:0,goalGroups:['fitness','habit','movement','build','athletic'],
   extension:'standard',family:'dynamic',impact:q.items.some(x=>root.HunterGroups49.impact(byId.get(x.id)))?'Con saltos':'Sin saltos',minutes:Math.max(5,q.items.length*4),safe381:true});
 }return result;
}
allRoutines=function(){return [...originalAll(),...templates(state?.profile,state?.logs||{})];};
const oldCompatible=A.routineCompatible;
A.routineCompatible=function(r,p){return r?.safe381?gate(p).ok&&!!r.items?.length&&r.items.every(x=>compatible(byId.get(x.id),p,injury(p))):oldCompatible(r,p);};
const previousRoutines=routines;
routines=function(){const html=previousRoutines();const count=available.length;
 return '<div class="notice" role="note"><b>Generador dinámico V38.1</b><p>'+count+' demostraciones 2D con metadatos disponibles para selección. Las rutinas de muestra se crean al abrir esta sección; las combinaciones no se almacenan. Los filtros del perfil se aplican antes de asignar.</p></div>'+html;
};
root.HunterDynamic381={version:VERSION,catalogCount:available.length,verifiedAssetCount:VERIFIED_IDS.size,
 pool,choose,patch,feedback,templates,gate,compatible,locked,profileKey,integrity:()=>({verified:VERIFIED_IDS.size,usable:available.length,
  missing:[...VERIFIED_IDS].filter(id=>!byId.has(id)).length})};
})(window);
