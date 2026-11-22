/* Dosis de diseño, no prescripción clínica. Las restricciones se evalúan antes. */
(function(root){
'use strict';
const levels={basic:{sets:3,reps:14,seconds:40,cardioSets:3,cardioSeconds:50},intermediate:{sets:4,reps:14,seconds:45,cardioSets:3,cardioSeconds:60},advanced:{sets:4,reps:16,seconds:50,cardioSets:4,cardioSeconds:70}};
const scale=p=>Number.isFinite(p.trainingScale)?Math.max(.4,Math.min(1,p.trainingScale)):1;
function dose(e,p,kind){
 const v=levels[p.difficulty]||levels.basic, reduced=Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle;
 if(reduced||kind==='recovery')return {id:e.id,sets:1,target:e.unit==='sec'?(reduced?15:20):6,rest:kind==='recovery'?20:90};
 if(e.family==='power')return {id:e.id,sets:p.difficulty==='advanced'?4:p.difficulty==='intermediate'?3:2,target:Math.max(2,Math.floor((p.difficulty==='advanced'?6:p.difficulty==='intermediate'?5:4)*scale(p))),rest:Math.max(90,p.restSeconds||90)};
 return {id:e.id,sets:kind==='cardio'?v.cardioSets:v.sets,target:Math.max(e.unit==='sec'?10:4,Math.floor((e.unit==='sec'?(kind==='cardio'?v.cardioSeconds:v.seconds):v.reps)*scale(p))),rest:kind==='cardio'?Math.max(45,p.restSeconds||90):Math.max(90,p.restSeconds||90)};
}
const previous=Schedule.items;
Schedule.items=function(p,kind,rotation,carry){
 const items=previous(p,kind,rotation,carry);
 if(!Adapt.mode(p).auto||Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle||kind==='recovery')return items;
 return items.map(x=>({...x,...dose(Engine.byId(x.id),p,kind),target:Engine.byId(x.id).family==='power'?dose(Engine.byId(x.id),p,kind).target:Math.max(Math.floor(x.target*scale(p)),dose(Engine.byId(x.id),p,kind).target)}));
};
const previousMake=Engine.makeCycle;
Engine.makeCycle=function(p,start,index,last,logs){const c=previousMake(p,start,index,last,logs);if(Adapt.mode(p).auto&&!Adapt.mode(p).reduced&&!GoalPlans.plans[p.finalTarget]?.gentle)c.days.forEach(d=>{if(d.kind==='recovery')return;d.items.forEach(x=>{const base=dose(Engine.byId(x.id),p,d.kind);if(Engine.byId(x.id).family==='power'||last&&scale(p)<1)x.target=base.target;else x.target=Math.max(x.target,base.target);x.sets=base.sets;x.rest=Math.max(x.rest,base.rest);});});return c;};
root.TrainingIntensity={levels,dose,scale};
})(typeof window!=='undefined'?window:globalThis);
