/* Selecciona sesiones completas por objetivo, equipo, nivel y restricciones; no inventa fuentes. */
(function(root){
'use strict';
function category(d){return d.kind==='cardio'?'Cardio':d.kind==='recovery'?'Movilidad':'Fuerza';}
function fitness(r,p,d,logs,now){
 if(r.category!==category(d)&&!(category(d)==='Fuerza'&&r.category==='Mixta'))return null;
 if(!Adapt.routineCompatible(r,p))return null;
 if(r.planVersion===14&&(r.level!==['basic','intermediate','advanced'].indexOf(p.difficulty)||!r.goalGroups.includes(GoalGroups14.of(p))))return null;
 const groups=r.items.map(x=>Engine.byId(x.id).group),focus=GoalPlans.plans[p.finalTarget]?.focus||p.focus;
 const target=focus==='upper'?['Pecho','Espalda','Hombros','Brazos']:focus==='lower'?['Piernas','Cadera']:focus==='core'?['Core']:[];
 if(target.length&&!groups.some(g=>target.includes(g)))return null;
 const prediction=Learning.predict(Learning.samples(logs,now),r.items);
 if(prediction&&prediction.effort>=8)return null;
 return target.length?groups.filter(g=>target.includes(g)).length/groups.length:1;
}
function organize(c,p,sourceRoutines,ownRoutines,logs,now){
 const mode=Adapt.mode(p);let count=0;
 if(p.trainingMode!=='routines')return count;
 if(!mode.auto||mode.reduced||GoalPlans.plans[p.finalTarget]?.gentle||['reducir','pausar'].includes(c.reviews?.at(-1)?.action)){
  for(const d of c.days)if(d.items.length)d.planningNote='Rutina adaptada con dosis reducida según tus restricciones y registros.';
  return count;
 }
 const preferred=CreatorFeed.channels.find(x=>x.key===p.preferredTrainer);
 const sources=sourceRoutines.filter(CreatorFeed.validateRoutine).filter(r=>!preferred||r.channelId===preferred.channelId).map(CreatorFeed.asRoutine);
 for(const [i,d] of c.days.entries()){
  if(d.date<now||!d.items.length||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;
  let list=p.preferredTrainer==='own'?[]:sources.map(r=>({r,score:fitness(r,p,d,logs,now)})).filter(x=>x.score!==null).sort((a,b)=>b.score-a.score);
  let original=list.length>0;
  if(!list.length)list=ownRoutines.filter(r=>!r.locked).map(r=>({r,score:fitness(r,p,d,logs,now)})).filter(x=>x.score!==null).sort((a,b)=>(b.r.planVersion===14?1:0)-(a.r.planVersion===14?1:0)||b.score-a.score||a.r.level-b.r.level);
  if(!list.length){d.planningNote='Se mantiene la rutina adaptada: no hay una secuencia completa compatible en la fuente elegida.';continue;}
  const r=list[(c.index+i)%Math.min(5,list.length)].r;
  d.items=r.planVersion===14?OwnRoutines14.adjust(r,p):r.items.map(x=>original?{...x}:{...x,...TrainingIntensity.dose(Engine.byId(x.id),p,d.kind)});
  if(!original&&TrainingIntensity.scale(p)===1)for(const x of d.items){const next=AdaptiveV9.recommend(logs,x,p,now);if(next.target>x.target)x.target=next.target;}
  d.kind=r.category==='Mixta'?'mixed':d.kind;d.title=r.name;d.tasks=[];d.autoTemplateId=r.id;
  if(original){d.routineId=r.id;d.sourceSequence=JSON.parse(JSON.stringify(r.sourceSequence));d.creatorAssigned=true;}
  d.planningNote=original?'Secuencia de la fuente elegida, sin cambiar sus bloques ni cantidades.':p.preferredTrainer==='own'?'Rutina del catálogo ajustada al nivel y al objetivo.':'No hay una secuencia completa comprobada y compatible del entrenador elegido. Se usa una rutina propia adaptada.';
  delete logs[d.date];count++;
 }
 return count;
}
root.RoutinePlanner={organize,fitness};
})(typeof window!=='undefined'?window:globalThis);
