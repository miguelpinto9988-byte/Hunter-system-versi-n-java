/* Weekly central-body integration 38.47. */
/* Shared group integration 38.49 – strict day category, all mission paths.
 * Planning roles distinguish main strength, accessories, activation and power.
 * An intentional lower-body power block may retain its original Cardio record.
 * Existing completed/manual sessions remain historical, never overwritten.
 */
(function(root){
 'use strict';
 if(root.HunterStrict3838)return;
 const E=root.Engine,S=root.Schedule,D=root.HunterDynamic381;
 if(!E||!S||!D)return;
 const UPPER=new Set(['Pecho','Espalda','Hombros','Brazos']);
 const LOWER=new Set(['Piernas','Cadera']);
 const CORE=new Set(['Core','Abdomen']);
 const CATEGORY={upper:'Fuerza',lower:'Fuerza',core:'Fuerza',cardio:'Cardio',mobility:'Movilidad'};
 const KIND={upper:'strength',lower:'strength',core:'strength',cardio:'cardio',mobility:'recovery',rest:'rest'};
 const LABEL={upper:'Tren superior',lower:'Tren inferior',core:'Tren central',cardio:'Cardio',mobility:'Movilidad',rest:'Descanso'};
 const clone=x=>JSON.parse(JSON.stringify(x));
 const own=(o,k)=>Object.prototype.hasOwnProperty.call(o||{},k);
 function type(p,date){
  if(!p?.weekPlan||!S.valid(p))return null;
  const wd=S.weekday(date),choice=p.weekPlan[wd];
  if(choice==='train')return ['lower','core'].includes(p.weekFocus?.[wd])?p.weekFocus[wd]:'upper';
  return own(KIND,choice)?choice:null;
 }
 function matches(item,t){
  if(t==='rest')return false;
  const ex=E.byId(item?.id);
  return !!ex&&root.HunterGroups49.matches(ex,t);
 }
 function accepted(items,t){return Array.isArray(items)&&items.length>0&&items.every(x=>matches(x,t));}
 function fingerprint(p,t){return JSON.stringify({groupsVersion:root.HunterGroups49.version,t,goal:p?.goal,difficulty:p?.difficulty,
  equipment:p?.equipment,gymEquipment23:p?.gymEquipment23,age:p?.age,materials46:p?.materials46,capabilities46:p?.capabilities46,
  minutes:p?.minutes,injuries:p?.injuries,restrictions:p?.restrictions,
  health:p?.health,limits:p?.limits,trainingScale:p?.trainingScale});}
 function past(logs,date){return Object.fromEntries(Object.entries(logs||{}).filter(([d])=>d<date));}
 function archiveOld(log,newItems,date){
  if(!log||log.complete||log.manual)return false;
  const old=Array.isArray(log.items)?log.items:[];
  const prior=Array.isArray(log.priorCompleted3838)?log.priorCompleted3838:[];
  const remaining=old.slice();
  const output=newItems.map(template=>{
    const row=clone(template),idx=remaining.findIndex(x=>x&&x.id===template.id&&!x.archivedProfile3829);
    const sets=Math.max(1,Math.min(12,Number(template.sets)||1));
    const actual=Array.from({length:sets},()=>({done:false,value:template.target,kg:0}));
    if(idx>=0){
      const item=remaining.splice(idx,1)[0];
      const done=(item.actual||[]).filter(a=>a?.done===true).map(clone);
      for(let i=0;i<Math.min(sets,done.length);i++)actual[i]=done[i];
      if(done.length>sets)prior.push({id:item.id,actual:done.slice(sets),reason:'series anteriores'});
    }
    row.actual=actual;return row;
  });
  for(const x of remaining){
    const done=(x?.actual||[]).filter(a=>a?.done===true);
    if(done.length)prior.push({id:x.id,actual:clone(done),reason:'categoría cambiada'});
  }
  log.items=output;
  if(prior.length)log.priorCompleted3838=prior;
  log.profileChanged3829=true;
  log.strict3838=date;
  return true;
 }
 function strictDay(c,d,p,logs,date,tracker){
  if(!d||d.date<date)return false;
  const t=type(p,d.date),log=logs?.[d.date];if(!t)return false;
  if(d.customized||d.manual||log?.manual||log?.complete||log?.items?.some(x=>x.actual?.some(a=>a.done))||d.routineId&&!d.creatorAssigned)return false;
  const sig=fingerprint(p,t);
  const coherent=(t==='rest'?(d.kind==='rest'&&(!d.items||d.items.length===0)):
     d.kind===KIND[t]&&accepted(d.items,t)&&d.scheduleChoice===p.weekPlan[S.weekday(d.date)]);
  const noWork=t!=='rest'&&d.strictBlocked3838===sig&&!(d.items||[]).length;
  const staleLog=!!(d.date===date&&log&&!log.complete&&!log.manual&&Array.isArray(log.items)&&
    log.items.some(x=>!d.items?.some(y=>y.id===x.id)||!matches(x,t)));
  if(d.strictSignature3838===sig&&(coherent||noWork)){
    if(staleLog){archiveOld(log,d.items||[],date);return true;}
    return false;
  }
  // First run can adopt a matching deterministic generated plan without changing
  // today's existing doses. Inconsistent plans are repaired once then locked.
  if(coherent&&d.strictSignature3838==null){
   d.strictSignature3838=sig;d.strictType3838=t;
   if(staleLog)archiveOld(log,d.items||[],date);
   return true;
  }
  let result={items:[],blocked:null};
  if(t!=='rest'){
    result=D.choose(p,KIND[t],D.profileKey(p)+'|'+d.date+'|'+(c?.index||0),past(logs,d.date),d.date,new Set(),tracker);
    if(!accepted(result.items,t)){
      // This fail-closed check also catches other planners' fallback results.
      result={items:[],blocked:result.blocked||'No se encontraron ejercicios compatibles de '+LABEL[t]+'; revisa el perfil o las restricciones.'};
    }
  }
  const next=t==='rest'?[]:clone(result.items||[]);
  d.kind=KIND[t];d.scheduleChoice=p.weekPlan[S.weekday(d.date)];d.title=t==='rest'?'Descanso elegido':
    result.blocked?LABEL[t]+' · requiere revisión':LABEL[t]+' · sesión del día';
  d.items=next;d.tasks=result.blocked?[{id:'review-strict3838',text:'Revisar restricciones para '+LABEL[t],done:false}]:[];
  d.dynamic381=true;d.strictType3838=t;d.strictSignature3838=sig;
  d.strictBlocked3838=result.blocked?sig:null;
  if(result.blocked){d.blocked381=result.blocked;d.blocked378=result.blocked;}
  else{delete d.blocked381;delete d.blocked378;delete d.blocked379;}
  delete d.creatorAssigned;delete d.autoTemplateId;delete d.sourceSequence;
  if(t==='rest')delete d.routineId;
  if(d.date===date){
    d.dailyFixed3828=date;
    if(root.HunterStable3828?.safetyKey)d.dailySafety3828=root.HunterStable3828.safetyKey(p);
    if(log){archiveOld(log,next,date);log.kind=d.kind;}
  }
  return true;
 }
 function apply(c,p,logs={},date=E.dateKey()){
  if(!c?.days?.length||!p||!S.valid(p)||c.reviews?.at(-1)?.action==='pausar')return 0;
  let changed=0;
  const tracker=root.HunterPlan3810.tracker(c,logs,date),tracked=new Set(tracker.snapshot().map(x=>x.date)),add=tracker.add;
  tracker.add=function(day,ex,sets){tracked.add(day);add(day,ex,sets);};
  for(const d of c.days){
   if(strictDay(c,d,p,logs,date,tracker))changed++;
   if(d.date>=date&&!tracked.has(d.date))for(const item of d.items||[]){const ex=E.byId(item.id);if(ex)tracker.add(d.date,ex,item.sets);}
  }
  return changed;
 }
 // A power block must never become a continuous jump warm-up. This also
 // covers generated library previews so the estimate matches the step player.
 if(typeof Warm24!=='undefined'&&typeof Warm24.select==='function'){
  const originalWarm=Warm24.select;
  Warm24.select=function(day,p=state?.profile){
   if(!day?.items?.length||!p)return originalWarm.apply(this,arguments);
   return root.HunterGroups49.phase(day,p);
  };
 }
 const prevCycle=typeof currentCycle==='function'?currentCycle:null;
 if(prevCycle){let busy=false;
  currentCycle=function(){
   const c=prevCycle.apply(this,arguments);
   if(busy||!c||!state?.profile)return c;
   busy=true;
   try{if(apply(c,state.profile,state.logs||{},E.dateKey())>0&&typeof save==='function'&&!root.Runtime20?.initializing)save();}
   finally{busy=false;}
   return c;
  };
 }
 const prevSubmit=typeof submitProfile==='function'?submitProfile:null;
 if(prevSubmit){submitProfile=function(ev){
  const answer=prevSubmit.apply(this,arguments);
  if(ev?.target?.id==='profileForm'&&state?.profile){
   const disk=(()=>{try{return JSON.parse(localStorage.getItem(KEY));}catch(_){return null;}})();
   if(disk?.profile){
    const c=state?.cycles?.find(x=>x.days?.some(d=>d.date===E.dateKey()));
    if(c&&apply(c,state.profile,state.logs||{},E.dateKey())>0&&typeof save==='function')save();
   }
  }
  return answer;
 };}
 root.HunterStrict3838=Object.freeze({version:'38.38',type,matches,accepted,apply,archiveOld});
})(window);
