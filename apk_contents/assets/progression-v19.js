/* V19: escalones de movimiento, dosis por patrón y progresión semanal verificable. */
(function(root){
'use strict';
const E=Engine,O=OwnRoutines18,keys=O.keys,level=p=>Math.max(0,keys.indexOf(p.difficulty));
const tierPools={
 full:{body:[
  ['squat','incline','bridge','deadbug','prone','split'],
  ['squat-control18','pushup','singlebridge','plank','reverse','reverselunge'],
  ['split-15-19','archer19','singlebridge-pause19','plankreach19','y-t-w19','single-squat19']
 ],db:[
  ['goblet','press','row','dbbridge','hammer','suitcase'],
  ['goblet-control18','press-control18','bentrow','rdl-control18','altfloorpress','dbsplit'],
  ['goblet-15-19','singlepress','row-15-19','single-rdl19','doublefront','overhead-15-19']
 ]},
 push:{body:[
  ['incline','kneepush','wallslide','incline-control18','kneepush-control18'],
  ['pushup','pushup-control19','closepush','shouldertap','bearhold'],
  ['archer19','pike19','closepush','decline19','plankreach19']
 ],db:[
  ['press','overheadpress','neutral','lateral','lyingtricep','benchpress'],
  ['press-control18','overheadpress-control18','singlepress','neutral-control18','singleoverhead','singletricep'],
  ['singlepress','overhead-15-19','squeezepress','lateral-pause19','singletricep','benchpress-control18']
 ]},
 pull:{body:[
  ['prone','towelrow','scapular','bird','reverse'],
  ['w-t19','reverse','contralateral','bird-control18','towelrow'],
  ['y-t-w19','contralateral','w-t19','bird-control18','reverse']
 ],db:[
  ['row','curl','hammer','reversefly','bentrow','concentration'],
  ['row-control18','bentrow-control18','curl-control18','hammer-control18','reversefly','pullover'],
  ['row-15-19','bentrow-control18','curl-pause19','reversefly','pullover','hammer-control18']
 ]},
 legs:{body:[
  ['squat','split','bridge','calf','reverselunge','wallsit'],
  ['squat-control18','split-control18','singlebridge','singlecalf','lungeknee','lateralunge'],
  ['single-squat19','split-15-19','singlebridge-pause19','singlecalf-pause19','singlehinge-free19','squat-15-19']
 ],db:[
  ['goblet','rdl','dbbridge','dbcalf','dbsumo','sumodead'],
  ['goblet-control18','rdl-control18','dbsplit','dblunge','dbsidelunge','goblethold'],
  ['goblet-15-19','single-rdl19','dbsplit','bulgarian','dbsidelunge','doublefront']
 ]},
 core:{body:[
  ['deadbug','bird','kneelingplank','sideplank','curlup'],
  ['plank','bearhold','reversecrunch','shouldertap','sideplank'],
  ['full-sideplank19','plankreach19','pau-plankup','sideplank-lift19','mountain']
 ],db:[
  ['suitcase','farmerhold','dbbridge','deadbug','farmerwalk'],
  ['farmerwalk','dbmarch','singlepress','suitcase','dbsplit'],
  ['dbmarch','single-rdl19','singleoverhead','singlepress','farmerwalk']
 ]},
 control:{body:[
  ['squat-control18','kneepush-control18','bridge-control18','deadbug-control18','calf-control18'],
  ['pushup-control19','split-control18','bird-control18','squat-control18','singlebridge'],
  ['squat-15-19','split-15-19','singlebridge-pause19','singlecalf-pause19','y-t-w19']
 ],db:[
  ['goblet','press','row','rdl','curl'],
  ['goblet-control18','press-control18','row-control18','rdl-control18','curl-control18'],
  ['goblet-15-19','row-15-19','overhead-15-19','single-rdl19','curl-pause19','lateral-pause19']
 ]},
 cardio:{body:[
  ['fastmarch','stepjack','kneeliftcardio','box','sidestep','heelback'],
  ['shadowcombo','skater','squatstep','boxupper','stepreach','doubletouch'],
  ['shuffle-touch19','shadowcombo','squatstep','skaterreach','boxinterval','fastfeet']
 ]},
 intervals:{body:[
  ['stepjackinterval','boxinterval','fastmarch','sidestepinterval','stepreach'],
  ['shadowcombo','fastfeet','skater','jog','squatstep'],
  ['highknees','mountain','jumpjack','shuffle-touch19','rope','skater-stick19']
 ]},
 agility:{body:[
  ['frontbackstep','doubletouch','vstep','stepreach','skater'],
  ['shuffle','stepcross','skaterreach','fastfeet','shadowcombo'],
  ['shuffle-touch19','stepcross','skater-stick19','highknees','mountain']
 ]},
 power:{body:[
  ['lowhop','linehop','calf','squat','lowhop'],
  ['linehop','squatjump','lowhop','skaterhop','split'],
  ['skater-stick19','squatjump','skaterhop','linehop','singlecalf-pause19']
 ]},
 mobility:{body:[
  ['ankledrive','hipcircle','shoulder','wallslide','hipflex','thoracic'],
  ['hipflex','thoracic','hamstretch','ankledrive','chestopen','latstretch'],
  ['thoracic','latstretch','hipflex','hamstretch','chestopen','ankledrive']
 ]},
 balance:{body:[
  ['tandem','linewalk','standingabduct','wallcalf','balance'],
  ['balance','singlehinge','lungeknee','singlecalf','skaterreach'],
  ['singlehinge-free19','single-squat19','singlecalf-pause19','skater-stick19','lungeknee']
 ]},
 seated:{body:[
  ['seatedmarch','seatedcalf','legextend','ankle','shoulder','scapular'],
  ['seatedpunch','seatedmarchinterval','legextend-control18','heeltoe','seatedreach'],
  ['seatedmarchinterval','legextend-control18','seatedpunch','seatedstep','forearmmob']
 ]}
};
const previousGentle=O.gentle,gentle=p=>previousGentle(p),tierMap=new Map();
for(const spec of Object.values(tierPools))for(const pools of Object.values(spec))for(const [n,ids] of pools.entries())for(const id of ids)tierMap.set(id,Math.min(tierMap.get(id)??n,n));
for(const e of E.exercises)if(e.progressionTier19!==undefined)tierMap.set(e.id,e.progressionTier19);
const tier=id=>tierMap.get(id)??E.byId(id)?.level??0;
const oldReasons=Adapt.reasons;
Adapt.reasons=function(e,p,strict=true){
 if(!e)return ['Ejercicio desconocido'];
 const out=oldReasons(e,p,strict).filter(x=>x!=='Nivel superior al seleccionado');
 if(p){const ceiling=Adapt.mode(p).reduced?0:level(p)===0?1:2;if(e.level>ceiling)out.push('Nivel superior al seleccionado');}
 return out;
};
// Adapt.allowed cerraba sobre la versión original de reasons.
Adapt.allowed=(e,p)=>!Adapt.reasons(e,p).length;
Adapt.routineCompatible=(r,p)=>!!p&&Adapt.mode(p).auto&&r.items.every(x=>Adapt.allowed(E.byId(x.id),p));
const rest=p=>[30,60,90].includes(Number(p.restSeconds))?Number(p.restSeconds):[30,60,90][level(p)];
function dose(e,p,kind){
 const n=level(p),scale=TrainingIntensity.scale(p),novice=p.experience==='beginner';
 let sets,target;
 if(gentle(p)||kind==='recovery')return {id:e.id,sets:1,target:e.unit==='sec'?15:6,rest:30};
 if(e.family==='power'){sets=novice?2:[2,3,4][n];target=[4,5,6][n];}
 else if(e.unit==='sec'){sets=novice?2:[3,4,4][n];target=kind==='cardio'?[35,45,50][n]:[25,35,40][n];}
 else{
  sets=novice?2:[4,4,5][n];
  target=e.repRange19?e.repRange19[0]:[14,12,10][n];
  if(novice)target=Math.min(target,[10,8,6][n]);
 }
 return {id:e.id,sets,target:Math.max(e.unit==='sec'?10:2,Math.floor(target*scale)),rest:rest(p)};
}
function cap(e,p){return e.repRange19?.[1]||(e.family==='power'?8:e.unit==='sec'?75:[20,18,16][level(p)]);}
TrainingIntensity.dose=dose;Object.assign(TrainingIntensity.levels,{
 basic:{sets:4,reps:14,seconds:25,cardioSets:3,cardioSeconds:35},
 intermediate:{sets:4,reps:12,seconds:35,cardioSets:4,cardioSeconds:45},
 advanced:{sets:5,reps:10,seconds:40,cardioSets:4,cardioSeconds:50}
});
function pool(family,p,equipment){
 const spec=tierPools[family],ids=spec?.[equipment|| (p.equipment==='db'&&spec.db?'db':'body')]?.[level(p)]||[];
 return [...new Set(ids)].map(id=>E.byId(id)).filter(e=>e&&Adapt.allowed(e,p));
}
function choose(p,kind,rotation=0){
 if(!Adapt.mode(p).auto)return [];
 if(gentle(p)){
  return InjuryAwareV93.items(p,kind,rotation).filter(x=>Adapt.allowed(E.byId(x.id),p)).map(x=>({...x,...dose(E.byId(x.id),p,kind)}));
 }
 const group=GoalGroups14.of(p);let ids=[];
 const focus=p.focus,requested=Math.min(7,p.dailyMissions|| (p.trainingMode==='routines'?5:4));
 if(kind==='strength'||kind==='mixed'){
  const families=focus==='upper'?['push','pull']:focus==='lower'?['legs']:focus==='core'?['core']:['full'];
  for(const family of families)ids.push(...pool(family,p));
  const primary=ids.slice(0,requested);
  if(primary.length<requested){
   const extra=['legs','push','pull','core'].flatMap(f=>pool(f,p));ids=[...primary,...extra];
  }
 }else if(kind==='cardio'){
  const family=group==='athletic'?(Math.abs(rotation)%2?'agility':'power'):p.restrictions?.noImpact?'cardio':'intervals';
  ids=pool(family,p);
  if(ids.length<requested)ids.push(...pool('cardio',p));
 }else ids=pool('mobility',p);
 let chosen=[...new Map(ids.map(e=>[e.id,e])).values()];
 const n=kind==='recovery'?Math.min(3,requested):requested;
 if(!chosen.length){
  const category=kind==='cardio'?'Cardio':kind==='recovery'?'Movilidad':'Fuerza';
  chosen=E.exercises.filter(e=>e.variant==='base'&&!e.sourceOnly&&e.category===category&&tier(e.id)<=level(p)&&Adapt.allowed(e,p))
   .sort((a,b)=>Math.abs(level(p)-tier(a.id))-Math.abs(level(p)-tier(b.id)));
 }
 return chosen.slice(0,n).map(e=>dose(e,p,kind));
}
Schedule.items=choose;
const chains=[
 ['incline','incline-control18','pushup','pushup-control19','archer19'],
 ['kneepush','kneepush-control18','pushup','pushup-control19','pike19'],
 ['squat','squat-control18','squat-15-19','single-squat19'],
 ['split','split-control18','split-15-19'],
 ['goblet','goblet-control18','goblet-15-19'],
 ['rdl','rdl-control18','single-rdl19'],
 ['press','press-control18','singlepress'],
 ['row','row-control18','row-15-19'],
 ['overheadpress','overheadpress-control18','overhead-15-19'],
 ['curl','curl-control18','curl-pause19'],
 ['bridge','bridge-control18','singlebridge','singlebridge-pause19'],
 ['calf','calf-control18','singlecalf','singlecalf-pause19'],
 ['kneelingplank','plank','plankreach19'],
 ['sideplank','full-sideplank19','sideplank-lift19'],
 ['prone','w-t19','y-t-w19'],
 ['singlehinge','singlehinge-free19'],
 ['shuffle','shuffle-touch19'],
 ['skaterhop','skater-stick19']
];
function successor(id,p){
 for(const chain of chains){const i=chain.indexOf(id);if(i<0)continue;for(const next of chain.slice(i+1)){const e=E.byId(next);if(tier(next)<=level(p)&&Adapt.allowed(e,p))return e;}}
 return null;
}
function ruleRecommendation19(logs,item,p,now){
 const e=E.byId(item.id),rows=AdaptiveV9.evidence(logs,item.id,now).filter(r=>E.unitOf(r.item)===E.unitOf(item)&&(r.item.side||'both')===(item.side||'both')),recent=rows.slice(0,3);
 const full=recent.length===3&&recent.every(r=>r.rpe>=1&&r.rpe<=7&&r.item.sets>=item.sets&&r.item.target>=item.target&&r.item.actual?.length>=r.item.sets&&r.item.actual.every(a=>a.done&&Number(a.value)>=r.item.target));
 let out={id:item.id,target:item.target,action:'mantener',reason:'Reúne tres registros completos y comparables, con esfuerzo de 7/10 o menos.',samples:rows.length,variantChanged:false};
 if(!Adapt.mode(p).auto||gentle(p)||TrainingIntensity.scale(p)<1)return {...out,reason:'Se conserva la adaptación y la reducción elegida.'};
 if(!full)return out;
 if(item.target<cap(e,p)){
  const delta=e.unit==='sec'?Math.min(5,Math.max(1,Math.floor(item.target*.1))):1;
  return {...out,target:Math.min(cap(e,p),item.target+delta),action:'aumentar',reason:'Tres registros comparables: aumenta una sola variable dentro del rango de este movimiento.'};
 }
 const next=successor(item.id,p);
 if(next){
  const v=dose(next,p,next.category==='Cardio'?'cardio':'strength');
  return {...out,id:next.id,target:v.target,sets:v.sets,rest:v.rest,variantChanged:true,action:'aumentar',reason:'Rango completado: siguiente variante compatible, con repeticiones recalibradas. '+e.name+' → '+next.name+'.'};
 }
 return {...out,reason:'Rango completado. Conserva la técnica y la dosis de este nivel; cambia tu nivel o carga desde Perfil cuando corresponda.'};
}
AdaptiveV9.recommend=recommend;
function recommend(logs,item,p,now){
 const result=ruleRecommendation19(logs,item,p,now),candidate={...item,id:result.id||item.id,target:result.target},estimate=root.ProgressMonitor20?.estimate(logs,candidate,now);
 if(result.action==='aumentar'&&estimate&&estimate.effort>7)return {id:item.id,target:item.target,action:'mantener',variantChanged:false,samples:result.samples,estimate20:estimate,reason:'Se mantiene la dosis: esfuerzo estimado '+estimate.effort+'/10 a partir de '+estimate.neighbors+' registros comparables.'};
 return {...result,estimate20:estimate};
}
function applyRecommendation(item,r,p){
 if(r.variantChanged&&r.id!==item.id){const e=E.byId(r.id);if(e&&Adapt.allowed(e,p)&&tier(e.id)<=level(p)){const next={...item,...dose(e,p,e.category==='Cardio'?'cardio':'strength'),id:r.id,target:r.target,progressedFrom19:item.id};delete next.unit;if(!e.sides)delete next.side;return next;}}
 return {...item,target:Math.min(cap(E.byId(item.id),p),Math.max(item.target,r.target)),rest:rest(p)};
}
function carry(item,last,p){
 if(!last||gentle(p)||TrainingIntensity.scale(p)<1)return item;
 const old=last.days.flatMap(d=>d.items).filter(x=>x.id===item.id||chains.some(c=>c.includes(item.id)&&c.includes(x.id)&&c.indexOf(x.id)>c.indexOf(item.id))).at(-1);
 if(!old||!Adapt.allowed(E.byId(old.id),p)||tier(old.id)>level(p))return item;
 const v=dose(E.byId(old.id),p,E.byId(old.id).category==='Cardio'?'cardio':'strength');
 const next={...item,...v,target:Math.max(v.target,Math.min(cap(E.byId(old.id),p),old.target))};if(old.id!==item.id){delete next.unit;if(!E.byId(old.id).sides)delete next.side;}return next;
}
const make=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){
 const c=make(p,start,index,last,logs),ap=GoalPlans.adjusted(p);c.engineVersion19=19;
 if(!Adapt.mode(ap).auto)return c;
 for(const d of c.days){
  if(!d.items.length||d.kind==='rest')continue;
  const selected=choose(ap,d.kind,Schedule.weekday(d.date));
  if(selected.length)d.items=selected.map(x=>carry(x,last,ap));
  else d.items=d.items.filter(x=>Adapt.allowed(E.byId(x.id),ap)).map(x=>({...x,...dose(E.byId(x.id),ap,d.kind)}));
  d.planningNote=gentle(ap)?'Selección compatible con tus restricciones, con dosis reducida.':'Escalón '+O.names[level(p)]+': variantes, control y volumen propios de este nivel.';
  d.progressionLevel19=level(p);
 }
 return c;
};
function adjust(r,p){return r.items.map(x=>{const v=dose(E.byId(x.id),p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength');return {...x,...v,sets:gentle(p)?1:Math.max(1,Math.ceil(v.sets*O.lengths[r.extension].ratio))};});}
const routines=[];
for(const [family,spec] of Object.entries(O.families))for(let n=0;n<3;n++)for(const [extension,length] of Object.entries(O.lengths))for(const equipment of spec.db?['body','db']:['body']){
 const ids=[...new Set(tierPools[family][equipment][n])].slice(0,length.count),selected=ids.map(id=>E.byId(id));
 if(selected.some(e=>!e))throw Error('Plantilla incompleta: '+family);
 const p={difficulty:keys[n],experience:'regular',health:{pain:'none'},injuries:[],restrictions:{},trainingScale:1};
 const items=selected.map(e=>{const v=dose(e,p,spec.category==='Cardio'?'cardio':spec.category==='Movilidad'?'recovery':'strength');v.sets=Math.max(1,Math.ceil(v.sets*length.ratio));return v;});
 const requirements=[...new Set(selected.flatMap(e=>e.requirements||[]).concat(equipment==='db'?['db']:[]))];
 routines.push({id:'own18-'+family+'-'+n+'-'+extension+'-'+equipment,name:spec.name+' · '+O.names[n]+' · '+length.name+(equipment==='db'?' · mancuernas':''),
  category:spec.category,level:n,items,requirements,impact:selected.some(e=>e.impact==='Con saltos')?'Con saltos':'Sin saltos',family,goalGroups:spec.goals,extension,planVersion:19,own18:true,own19:true,
  description:'Sesión original de Hunter System. '+(family==='pull'&&equipment==='body'?'Trabajo escapular y de control sin carga externa. ':'')+'Los tres niveles cambian las variantes y la coordinación, además del volumen. Movilidad y adaptación conservan dosis propias.',
  minutes:O.estimate(items)});
}
O.routines.splice(0,O.routines.length,...routines);Object.assign(O,{dose,adjust,cap,gentle});
Catalog.routines=O.routines;Catalog.baseRoutines=routines.length;Catalog.baseExercises=E.exercises.filter(e=>e.variant==='base').length;
RoutinePlanner.organize=function(c,p,source,own,logs,now){
 if(p.trainingMode!=='routines'||!Adapt.mode(p).auto||c.reviews?.at(-1)?.action==='pausar')return 0;
 const ap=GoalPlans.adjusted(p),n=level(p),group=GoalGroups14.of(p);let count=0;
 for(const [i,d] of c.days.entries()){
  if(d.date<now||!d.items.length||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;
  const category=d.kind==='cardio'?'Cardio':d.kind==='recovery'?'Movilidad':'Fuerza';
  let list=routines.filter(r=>r.level===n&&r.extension==='standard'&&(r.category===category||category==='Fuerza'&&r.category==='Mixta')&&r.goalGroups.includes(group)&&Adapt.routineCompatible(r,ap));
  if(!list.length)list=routines.filter(r=>r.level===n&&r.extension==='brief'&&(r.category===category||category==='Fuerza'&&r.category==='Mixta')&&Adapt.routineCompatible(r,ap));
  if(ap.focus==='upper'||ap.focus==='lower'||ap.focus==='core'){
   const families=ap.focus==='upper'?['push','pull']:ap.focus==='lower'?['legs']:['core'],focused=list.filter(r=>families.includes(r.family));if(focused.length)list=focused;
  }
  if(p.equipment==='db'){const weighted=list.filter(r=>r.requirements.includes('db'));if(weighted.length)list=weighted;}
  if(!list.length){d.own18=true;d.own19=true;d.title='Rutina personal · '+category;d.planningNote='Movimientos compatibles con tu perfil; la adaptación conserva prioridad sobre el nivel.';count++;continue;}
  const r=list[i%list.length],previous=d.items,items=adjust(r,ap);
  for(let j=0;j<items.length;j++){
   let x=carry(items[j],{days:[{items:previous}]},ap);
   if(!gentle(ap)&&TrainingIntensity.scale(ap)===1&&c.reviews?.at(-1)?.adherence>=.8)x=applyRecommendation(x,recommend(logs,x,ap,now),ap);
   if(c.reviews?.at(-1)?.action==='reducir')x.target=Math.max(E.unitOf(x)==='sec'?10:2,Math.floor(x.target*.8));
   items[j]=x;
  }
  d.items=items;d.kind=r.category==='Mixta'?'mixed':d.kind;d.title=r.name;d.own18=true;d.own19=true;d.autoTemplateId=r.id;d.tasks=[];
  d.planningNote=gentle(ap)?'Rutina adaptada con dosis reducida y filtros de lesión.':'Variantes del nivel '+O.names[n]+'. Revisión semanal por repeticiones, esfuerzo y cambio de variante.';count++;
 }
 return count;
};
root.TrainingProgression19={tierPools,tier,level,pool,choose,dose,cap,rest,chains,successor,recommend,applyRecommendation,carry,routines,adjust};
})(window);
