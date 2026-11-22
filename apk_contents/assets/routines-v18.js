/* Sesiones originales. Cantidades de diseño; las restricciones prevalecen. */
(function(root){'use strict';
const names=['Básico','Intermedio','Avanzado'],keys=['basic','intermediate','advanced'];
const families={
 full:{name:'Cuerpo completo',category:'Mixta',goals:['build','fitness','habit'],body:['squat','kneepush','bridge','deadbug','fastmarch','split'],db:['goblet','press','row','dbbridge','dbmarch','hammer']},
 push:{name:'Empuje y hombros',category:'Fuerza',goals:['build'],body:['incline','kneepush','wallslide','scapular','closepush'],db:['overheadpress','benchpress','neutral','lyingtricep','lateral','press']},
 pull:{name:'Espalda y brazos',category:'Fuerza',goals:['build'],body:['prone','reverse','scapular','towelrow','bird'],db:['row','curl','bentrow','hammer','reversefly','concentration']},
 legs:{name:'Piernas y glúteos',category:'Fuerza',goals:['build','athletic'],body:['squat','split','bridge','calf','reverselunge','singlebridge'],db:['goblet','rdl','dbbridge','dbcalf','dbsumo','dblunge']},
 core:{name:'Zona media',category:'Fuerza',goals:['build','movement','habit'],body:['deadbug','bird','kneelingplank','sideplank','curlup','plank'],db:['suitcase','farmerhold','dbbridge','farmerwalk','dbmarch']},
 control:{name:'Cadencia y control',category:'Fuerza',goals:['build','movement','habit'],body:['squat-control18','kneepush-control18','bridge-control18','deadbug-control18','calf-control18'],db:['goblet-control18','press-control18','row-control18','rdl-control18','curl-control18','hammer-control18']},
 cardio:{name:'Cardio sin saltos',category:'Cardio',goals:['fitness','habit'],body:['fastmarch','stepjack','kneeliftcardio','box','sidestep','heelback']},
 intervals:{name:'Intervalos de cardio',category:'Cardio',goals:['fitness','athletic'],body:['stepjack','shadowcombo','fastfeet','skater','jog','highknees']},
 agility:{name:'Agilidad y coordinación',category:'Cardio',goals:['athletic','fitness'],body:['shuffle','stepcross','frontbackstep','skaterreach','doubletouch','fastfeet']},
 power:{name:'Saltos y potencia',category:'Cardio',goals:['athletic'],body:['lowhop','linehop','calf','squat','skaterhop','squatjump']},
 mobility:{name:'Movilidad de cuerpo completo',category:'Movilidad',goals:['movement','adapted','habit'],body:['ankledrive','hipcircle','shoulder','wallslide','hipflex','thoracic']},
 balance:{name:'Equilibrio y estabilidad',category:'Movilidad',goals:['movement','athletic','adapted'],body:['tandem','linewalk','standingabduct','singlehinge','wallcalf','balance']},
 seated:{name:'Movimiento sentado',category:'Movilidad',goals:['adapted','movement','habit'],body:['seatedmarch','seatedcalf','legextend','ankle','shoulder','scapular']}
};
const lengths={brief:{name:'Breve',count:3,ratio:.5},standard:{name:'Completa',count:4,ratio:1},long:{name:'Extendida',count:5,ratio:1}};
Object.assign(TrainingIntensity.levels,{basic:{sets:4,reps:16,seconds:40,cardioSets:3,cardioSeconds:40},intermediate:{sets:4,reps:20,seconds:50,cardioSets:4,cardioSeconds:45},advanced:{sets:4,reps:24,seconds:60,cardioSets:4,cardioSeconds:50}});
const gentle=p=>Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle;
function dose(e,p,kind){
 if(e?.doseCap46){const c=e.doseCap46;return HunterCatalog46.cap(e,p,{id:e.id,sets:2,target:c.initial,rest:c.rest});}
 const v=TrainingIntensity.levels[p.difficulty]||TrainingIntensity.levels.basic,scale=TrainingIntensity.scale(p),beginner=p.experience==='beginner';
 if(gentle(p)||kind==='recovery')return {id:e.id,sets:1,target:e.unit==='sec'?15:6,rest:kind==='recovery'?30:90};
 if(e.family==='power')return {id:e.id,sets:beginner?2:[2,3,4][keys.indexOf(p.difficulty)]||2,target:Math.max(2,Math.floor((beginner?3:[4,5,6][keys.indexOf(p.difficulty)]||4)*scale)),rest:Math.max(90,p.restSeconds||90)};
 const cardio=kind==='cardio',sets=beginner?2:cardio?v.cardioSets:v.sets,target=beginner?(e.unit==='sec'?25:10):e.unit==='sec'?(cardio?v.cardioSeconds:v.seconds):v.reps;
 return {id:e.id,sets,target:Math.max(e.unit==='sec'?10:4,Math.floor(target*scale)),rest:Math.max(cardio?45:90,p.restSeconds||90)};
}
TrainingIntensity.dose=dose;
function cap(e,p){return e.family==='power'?8:e.unit==='sec'?90:p.experience==='beginner'?14:[24,30,36][Math.max(0,keys.indexOf(p.difficulty))];}
AdaptiveV9.recommend=function(logs,item,p,now){
 const e=Engine.byId(item.id),rows=AdaptiveV9.evidence(logs,item.id,now).filter(r=>Engine.unitOf(r.item)===Engine.unitOf(item)&&(r.item.side||'both')===(item.side||'both')),recent=rows.slice(0,3),full=recent.length===3&&recent.every(r=>r.item.actual?.length>=Math.max(r.item.sets,item.sets)&&r.item.actual.every(a=>a.done&&Number(a.value)>=Math.max(r.item.target,item.target)));
 let target=item.target,action='mantener',reason='Reúne tres registros completos y comparables de este ejercicio.';
 if(!Adapt.mode(p).auto||gentle(p)||TrainingIntensity.scale(p)<1)reason='Se conserva la adaptación y la reducción elegida.';
 else if(full&&recent.every(r=>r.rpe<=7)){
  const delta=e.unit==='sec'?Math.min(5,Math.max(1,Math.floor(target*.1))):1;
  target=Math.max(item.target,Math.min(cap(e,p),target+delta));action=target>item.target?'aumentar':'mantener';reason=action==='aumentar'?'Tres registros completos con esfuerzo de 7/10 o menos: aumenta una sola variable.':'Tope de este nivel: conserva la dosis y revisa la técnica o una variante desde Perfil.';
 }
 return {target,action,reason,samples:rows.length};
};
const oldItems=Schedule.items;
Schedule.items=function(p,kind,rotation,carry){const items=oldItems(p,kind,rotation,carry);if(!Adapt.mode(p).auto)return items;return items.map(x=>({...x,...dose(Engine.byId(x.id),p,kind)}));};
const make=Engine.makeCycle;
Engine.makeCycle=function(p,start,index,last,logs={}){const c=make(p,start,index,last,logs);c.engineVersion18=18;if(!Adapt.mode(p).auto)return c;for(const d of c.days)for(const x of d.items){const e=Engine.byId(x.id),v=dose(e,p,d.kind),prior=last?.days.flatMap(y=>y.items).filter(y=>y.id===x.id&&Engine.unitOf(y)===Engine.unitOf(x)).at(-1);if(prior&&!gentle(p)&&TrainingIntensity.scale(p)===1)v.target=Math.max(v.target,Math.min(prior.target,cap(e,p)));Object.assign(x,v);}return c;};
function workSeconds(x){const e=Engine.byId(x.id);return (Engine.unitOf(x)==='sec'?x.target:x.target*(e.tempoSeconds18||4))*(Engine.sidesOf(x)&&!x.side?2:1);}
function estimate(items){return Math.ceil((120+items.reduce((n,x,i)=>n+x.sets*workSeconds(x)+Math.max(0,x.sets-1)*x.rest+(i<items.length-1?x.rest:0),0))/60);}
function adjust(r,p){return r.items.map(x=>{const e=Engine.byId(x.id),v=dose(e,p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength');return {...x,...v,sets:gentle(p)?1:Math.max(1,Math.ceil(v.sets*lengths[r.extension].ratio))};});}
const routines=[];
for(const [family,spec] of Object.entries(families))for(let level=0;level<3;level++)for(const [extension,ex] of Object.entries(lengths))for(const equipment of spec.db?['body','db']:['body']){
 const pool=spec[equipment].map(id=>Engine.byId(id)).filter(Boolean).filter(e=>e.level<=(level===0?1:2)),selected=pool.slice(0,ex.count),p={difficulty:keys[level],experience:'regular',health:{pain:'none'},injuries:[],restrictions:{},trainingScale:1};
 const items=selected.map(e=>{const x=dose(e,p,spec.category==='Cardio'?'cardio':spec.category==='Movilidad'?'recovery':'strength');x.sets=Math.max(1,Math.ceil(x.sets*ex.ratio));return x;});
 const requirements=[...new Set(selected.flatMap(e=>e.requirements||[]).concat(equipment==='db'?['db']:[]))];
 const r={id:`own18-${family}-${level}-${extension}-${equipment}`,name:`${spec.name} · ${names[level]} · ${ex.name}${equipment==='db'?' · mancuernas':''}`,category:spec.category,level,items,requirements,impact:selected.some(e=>e.impact==='Con saltos')?'Con saltos':'Sin saltos',family,goalGroups:spec.goals,extension,planVersion:18,own18:true,description:'Sesión original de Hunter System. Preparación, series, descansos y vuelta a la calma con reloj. Cantidades adaptadas al perfil y revisión semanal.',minutes:estimate(items)};
 routines.push(r);
}
Catalog.routines=routines;
Catalog.baseRoutines=routines.length;Catalog.baseExercises=Engine.exercises.filter(e=>e.variant==='base').length;
RoutinePlanner.organize=function(c,p,source,own,logs,now){
 if(p.trainingMode!=='routines'||!Adapt.mode(p).auto||c.reviews?.at(-1)?.action==='pausar')return 0;
 let count=0;const adjusted=GoalPlans.adjusted(p),level=Math.max(0,keys.indexOf(p.difficulty)),group=GoalGroups14.of(p);
 for(const [i,d] of c.days.entries()){
  if(d.date<now||!d.items.length||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;
  const category=d.kind==='cardio'?'Cardio':d.kind==='recovery'?'Movilidad':'Fuerza';
  let list=routines.filter(r=>r.level===level&&r.extension==='standard'&&(r.category===category||category==='Fuerza'&&r.category==='Mixta')&&r.goalGroups.includes(group)&&Adapt.routineCompatible(r,adjusted));
  if(!list.length)list=routines.filter(r=>r.level===level&&r.extension==='brief'&&(r.category===category||category==='Fuerza'&&r.category==='Mixta')&&Adapt.routineCompatible(r,adjusted));
  // Si una plantilla completa no coincide, conserva los movimientos que ya
  // eligió el motor de restricciones en lugar de rellenar con incompatibles.
  if(!list.length){d.own18=true;d.title=gentle(p)?'Rutina adaptada · movimientos compatibles':'Rutina personal · '+category;d.planningNote='Secuencia propia con los movimientos que pasan tus filtros e indicaciones.';count++;continue;}
  const focus=adjusted.focus,targets=focus==='upper'?['Pecho','Espalda','Hombros','Brazos']:focus==='lower'?['Piernas','Cadera']:focus==='core'?['Core','Abdomen']:null;
  if(targets&&category==='Fuerza'){const focused=list.filter(r=>r.items.some(x=>targets.includes(Engine.byId(x.id).group)));if(focused.length)list=focused;}
  if(p.equipment==='db'){const weighted=list.filter(r=>r.requirements.includes('db'));if(weighted.length)list=weighted;}
  const r=list[i%list.length],previousItems=d.items;d.items=adjust(r,p);
  if(!gentle(p)&&TrainingIntensity.scale(p)===1)for(const x of d.items){const prior=previousItems.find(y=>y.id===x.id);if(prior)x.target=Math.max(x.target,Math.min(prior.target,cap(Engine.byId(x.id),p)));const next=AdaptiveV9.recommend(logs,x,p,now);x.target=Math.max(x.target,next.target);}
  if(c.reviews?.at(-1)?.action==='reducir')for(const x of d.items)x.target=Math.max(Engine.unitOf(x)==='sec'?10:4,Math.floor(x.target*.8));
  d.kind=r.category==='Mixta'?'mixed':d.kind;d.title=r.name;d.own18=true;d.autoTemplateId=r.id;d.tasks=[];d.planningNote=gentle(p)?'Rutina propia con dosis reducida y movimientos compatibles con tus restricciones.':'Rutina propia según tu objetivo, equipo y nivel. Revisión semanal con tus registros.';count++;
 }
 return count;
};
const expand=RoutineSequence.expand;
RoutineSequence.expand=function(day){const steps=expand(day);for(const s of steps)if(s.kind==='exercise'&&s.unit!=='sec')s.seconds=Math.ceil(workSeconds(s));if(!day.sourceSequence&&steps.length){steps.unshift({kind:'preparation',label:'Preparación · revisa espacio, equipo y técnica',seconds:60,blockIndex:-1});steps.push({kind:'cooldown',label:'Vuelta a la calma · baja el ritmo y respira',seconds:60,blockIndex:day.items.length});}return steps;};
root.OwnRoutines18={routines,families,lengths,names,keys,dose,adjust,estimate,cap,workSeconds,gentle};
})(window);
