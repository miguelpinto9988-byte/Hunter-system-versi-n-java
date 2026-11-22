/* Programas originales: nueve materias, tres niveles y tres extensiones.
 * Las dosis son decisiones de diseño, no transcripciones ni ensayos clínicos. */
(function(root){'use strict';
const families={
 strength:{name:'Fuerza',category:'Fuerza',goals:['build','fitness','athletic','habit'],body:['squat','kneepush','bridge','prone','deadbug','split','pushup'],db:['goblet','press','row','rdl','curl','dbsplit','overheadpress']},
 hypertrophy:{name:'Musculatura',category:'Fuerza',goals:['build'],body:['squathold','kneepush','bridgehold','scapular','curlup','singlebridge','closepush'],db:['goblethold','neutral','bentrow','dbbridge','hammer','lateral','lyingtricep']},
 core:{name:'Zona media',category:'Fuerza',goals:['build','movement','habit'],body:['deadbug','bird','kneelingplank','bridge','sideplank','plank','bearhold'],db:['suitcase','farmerhold','dbbridge','singlehinge','farmerwalk','dbmarch']},
 cardio:{name:'Cardio',category:'Cardio',goals:['fitness','habit','athletic'],body:['fastmarch','stepjack','boxinterval','kneeliftcardio','shadowcombo','jog','jumpjack']},
 agility:{name:'Agilidad y coordinación',category:'Cardio',goals:['athletic','fitness'],body:['fastfeet','shuffle','stepcross','skaterreach','frontbackstep','doubletouch','highknees']},
 power:{name:'Saltos y potencia',category:'Cardio',goals:['athletic'],body:['lowhop','linehop','calf','squat','skaterhop','squatjump']},
 mobility:{name:'Movilidad',category:'Movilidad',goals:['movement','habit','adapted'],body:['ankledrive','hipcircle','shoulder','wallslide','hipflex','thoracic','wrist']},
 balance:{name:'Equilibrio y estabilidad',category:'Movilidad',goals:['movement','athletic','adapted'],body:['tandem','linewalk','wallcalf','standingabduct','singlehinge','balance','birdcontrolled']},
 mixed:{name:'Cuerpo completo',category:'Mixta',goals:['habit','fitness','build','athletic'],body:['squat','kneepush','bridge','deadbug','fastmarch','skater','pushup'],db:['goblet','press','row','dbbridge','dbmarch','hammer','farmerwalk']}
};
const extensions={brief:{name:'Breve',count:3,sets:[2,2,3]},standard:{name:'Completa',count:4,sets:[3,3,4]},long:{name:'Extendida',count:5,sets:[3,4,4]}};
const names=['Básico','Intermedio','Avanzado'],keys=['basic','intermediate','advanced'],routines=[];
function target(e,level,family){if(family==='power'&&e.family==='power')return [4,5,6][level];if(e.unit==='sec')return family==='mobility'||family==='balance'?[20,25,30][level]:[45,55,65][level];return family==='mobility'||family==='balance'?[8,10,12][level]:[14,14,16][level];}
function estimate(items){let seconds=0;for(const [i,x] of items.entries()){const e=Engine.byId(x.id),work=e.unit==='sec'?x.target:x.target*3;seconds+=x.sets*work*(e.sides?2:1)+Math.max(0,x.sets-1)*x.rest+(i<items.length-1?x.rest:0);}return Math.ceil(seconds/60);}
for(const [family,spec] of Object.entries(families))for(let level=0;level<3;level++)for(const [extension,ex] of Object.entries(extensions))for(const equipment of spec.db?['body','db']:['body']){
 const pool=spec[equipment].map(id=>Engine.byId(id)).filter(e=>e.level<=(level===0?1:2)),selected=Array.from({length:Math.min(ex.count,pool.length)},(_,j)=>pool[(j+(extension==='long'?1:0)+level)%pool.length]);
 const items=selected.map(e=>({id:e.id,sets:family==='mobility'||family==='balance'?[1,2,2][level]:family==='power'&&e.family==='power'?[2,3,4][level]:ex.sets[level],target:target(e,level,family),rest:family==='power'?90:family==='mobility'||family==='balance'?30:family==='cardio'||family==='agility'?60:90}));
 const r={id:`own14-${family}-${level}-${extension}-${equipment}`,name:`${spec.name} · ${names[level]} · ${ex.name}${equipment==='db'?' · mancuernas':''}`,category:spec.category,level,items,requirements:equipment==='db'?['db']:[],impact:items.some(x=>Engine.byId(x.id).impact==='Con saltos')?'Con saltos':'Sin saltos',family,goalGroups:spec.goals,extension,planVersion:14,variant:'base',baseRoutineId:'own14-'+family,description:'Rutina propia de Hunter System. '+(family==='power'?'Saltos de calidad con pausas largas; sustituye por una opción sin impacto si hay restricciones.':'Registra cada serie y el esfuerzo final; las revisiones son semanales.'),minutes:estimate(items)};
 routines.push(r);
}
Catalog.routines.push(...routines);
function adjust(r,p){return r.items.map(x=>{const e=Engine.byId(x.id),gentle=Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle;return {...x,sets:gentle?1:x.sets,target:gentle?Math.min(x.target,e.unit==='sec'?15:6):Math.max(e.family==='power'?2:e.unit==='sec'?10:4,Math.floor(x.target*TrainingIntensity.scale(p)))};});}
root.OwnRoutines14={routines,families,extensions,estimate,adjust,names,keys};
})(window);
