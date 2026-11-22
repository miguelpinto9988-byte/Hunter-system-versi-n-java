/* Objetivo -> selección -> dosis -> revisión semanal, sin saltar filtros de salud. */
(function(root){'use strict';
const oldItems=Schedule.items;
Schedule.items=function(p,kind,rotation,carry){const base=oldItems(p,kind,rotation,carry),group=GoalGroups14.of(p);if(!Adapt.mode(p).auto||Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle)return base;
 const specific=group==='athletic'&&kind==='cardio'?['fastfeet','shuffle','stepcross','lowhop','skaterreach','highknees','squatjump']:group==='movement'&&kind==='recovery'?['ankledrive','hipcircle','wallslide','tandem','singlehinge','thoracic','balance']:null;if(!specific)return base;
 const pool=specific.map(id=>Engine.byId(id)).filter(e=>Adapt.allowed(e,p)),count=p.trainingMode==='routines'?4:Math.min(p.dailyMissions||3,pool.length);return pool.length?Array.from({length:Math.min(count,pool.length)},(_,i)=>TrainingIntensity.dose(pool[(rotation+i)%pool.length],p,kind)):base;
};
const make=Engine.makeCycle;
Engine.makeCycle=function(p,start,index,last,logs){const c=make(p,start,index,last,logs),plan=GoalGroups14.plan(p);c.planVersion14=14;c.goalPlan14=plan;c.decision=plan.name+'. '+plan.reason+' '+plan.healthReason+' Revisión semanal según sesiones registradas, esfuerzo y dolor.';return c;};
})(window);
