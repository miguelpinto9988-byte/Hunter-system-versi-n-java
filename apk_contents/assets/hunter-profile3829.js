/* Hunter System V38.29 — excepción explícita: al GUARDAR cambios de entrenamiento
 * del perfil, recalcular únicamente la sesión actual. La navegación diaria sigue
 * congelada por hunter-stable3828.js. No hay base adicional de rutinas.
 */
(function(root){
 'use strict';
 if(root.HunterProfile3829)return;
 const E=root.Engine,S=root.Schedule,D=root.HunterDynamic381,Stable=root.HunterStable3828;
 if(!E||!S||!D||!Stable||typeof submitProfile!=='function')return;
 const clone=value=>JSON.parse(JSON.stringify(value));
 const changedFields=['age','limits','injuries','restrictions','health','goalAutoRestrictions374',
  'goal','finalGoal','finalTarget','focus','difficulty','trainingScale','trainingMode','experience',
  'weekPlan','weekFocus','equipment','gymEquipment23','load','minutes','restSeconds','dailyMissions',
  'routineExercises3878','timeZone','frequency','preferredTrainer','bench','step','preferences','physicalLimits','materials46','capabilities46'];
 const fingerprint=p=>JSON.stringify(changedFields.map(k=>[k,p?.[k]??null]));
 const kinds={train:'strength',cardio:'cardio',mobility:'recovery',rest:'rest'};
 const dateKey=()=>E.dateKey();
 const existsDay=(date)=>state?.cycles?.flatMap(c=>c.days||[]).some(d=>d.date===date);
 const activeDone=x=>!!(x?.actual||[]).some(a=>a?.done===true);
 const started=l=>!!(l&&(l.complete||l.manual||l.pain||l.items?.some(activeDone)));
 const signature=x=>String(x?.id||'');
 const freshItem=x=>({...clone(x),actual:Array.from({length:Math.max(1,Math.min(10,Number(x.sets)||1))},()=>({done:false,value:Number(x.target)||1,kg:0}))});
 function reconcile(log,planned){
  if(!log||log.complete||log.manual)return;
  const old=Array.isArray(log.items)?log.items:[];
  const consumed=new Set();
  const next=planned.map(plan=>{
   const obj=freshItem(plan),index=old.findIndex((x,i)=>!consumed.has(i)&&!x.archivedProfile3829&&signature(x)===signature(obj));
   if(index<0)return obj;
   consumed.add(index);
   const prior=old[index];
   const completed=(prior.actual||[]).filter(a=>a?.done).map(clone);
   obj.actual.splice(0,Math.min(completed.length,obj.actual.length),...completed.slice(0,obj.actual.length));
   return obj;
  });
  const archive=[];
  old.forEach((x,i)=>{
   if(consumed.has(i)){
    const taken=next.find(n=>n.id===x.id)?.actual.filter(a=>a.done).length||0;
    const overflow=(x.actual||[]).filter(a=>a?.done).slice(taken);
    if(overflow.length)archive.push({...clone(x),sets:overflow.length,actual:overflow,archivedProfile3829:true});
   }else{
    const completed=(x.actual||[]).filter(a=>a?.done);
    if(completed.length)archive.push({...clone(x),sets:completed.length,actual:clone(completed),archivedProfile3829:true});
   }
  });
  log.items=[...archive,...next];
 }
 function regenerate(oldProfile,oldLog,oldDay){
  const p=state?.profile,date=dateKey(),nowPlan=state?.cycles?.find(c=>c.days?.some(d=>d.date===date));
  if(!p||!nowPlan)return {status:'no-cycle'};
  const d=nowPlan.days.find(x=>x.date===date),log=state.logs?.[date];
  if(log?.complete||log?.manual||oldDay?.customized||oldDay?.routineId||oldDay?.creatorAssigned){
   if(oldDay){const index=nowPlan.days.indexOf(d);nowPlan.days[index]=clone(oldDay);}
   const finished=nowPlan.days.find(x=>x.date===date);
   // Una misión terminada o una rutina elegida manualmente sigue siendo historial,
   // no se emite un segundo premio XP tras modificar el perfil.
   finished.dailySafety3828=Stable.safetyKey(p);
   finished.dailyFixed3828=date;
   if(log?.complete)finished.dailyReview3828=false;
   else finished.dailyReview3828=true;
   save();return {status:'finished-or-manual'};
  }
  const week=p.weekPlan,choice=S.valid(p)?week[S.weekday(date)]:null;
  // Si una nueva condición impide planificar con seguridad, retirar pendientes
  // y mostrar una solicitud de revisión, conservando todo lo completado.
  const valid=!!choice&&Object.prototype.hasOwnProperty.call(kinds,choice);
  const kind=valid?kinds[choice]:null;
  const gate=valid&&kind!=='rest'?D.gate(p):{ok:true};
  let result={items:[],blocked:null};
  if(!valid)result.blocked='Revisa los días de entrenamiento y las restricciones del perfil.';
  else if(!gate.ok)result.blocked=gate.reason||'Se requiere revisar el perfil antes de entrenar.';
  else if(kind!=='rest'){
   const past=Object.fromEntries(Object.entries(state.logs||{}).filter(([key])=>key<date));
   result=D.choose(p,kind,D.profileKey(p)+'|'+date+'|'+nowPlan.index,past,date,new Set(),null,Math.max(0,Math.floor(nowPlan.days.indexOf(d)/7)));
   if(!result.items?.length)result.blocked=result.blocked||'No hay ejercicios compatibles con las restricciones registradas.';
  }
  // Una entrada iniciada por dolor continúa detenida; el nuevo perfil no borra la alerta.
  if(log?.pain)result.blocked='La sesión está detenida por dolor. Revisa tu situación antes de volver a entrenar.';
  d.kind=kind||d.kind;
  d.scheduleChoice=choice||d.scheduleChoice;
  d.items=result.blocked?[]:clone(result.items||[]);
  d.tasks=result.blocked?[{id:'review-profile3829',text:'Revisar el perfil y las restricciones',done:false}]:[];
  d.title=result.blocked?'Misión del día · requiere revisión':
   kind==='rest'?'Descanso elegido':'Misión adaptada al perfil actualizado';
  d.dynamic381=true;
  d.dailyFixed3828=date;
  d.dailySafety3828=Stable.safetyKey(p);
  d.dailyReview3828=!!log?.pain;
  d.profileSignature3829=fingerprint(p);
  if(result.blocked){d.blocked381=result.blocked;d.blocked378=result.blocked;}
  else {delete d.blocked381;delete d.blocked378;delete d.blocked379;}
  if(log){
   reconcile(log,d.items);
   log.kind=d.kind;
   log.profileChanged3829=true;
   // Las tareas completadas de otros objetivos se conservan como registro.
   if(log.pain)log.complete=false;
  }
  nowPlan.profile=clone(p);
  if(typeof save==='function'&&!root.Runtime20?.initializing)save();
  return {status:result.blocked?'review':'updated',kind:d.kind};
 }
 const beforeSubmit=submitProfile;
 submitProfile=function(event){
  const old=state?.profile,oldFingerprint=fingerprint(old);
  const date=dateKey(),oldLog=state?.logs?.[date]?clone(state.logs[date]):null;
  const oldDay=state?.cycles?.flatMap(c=>c.days||[]).find(x=>x.date===date);
  const daySnapshot=oldDay?clone(oldDay):null;
  const previousDay=existsDay(date);
  const answer=beforeSubmit(event);
  // Solo el acto explícito de guardar el formulario autoriza replanificar hoy.
  if(!event?.target||event.target.id!=='profileForm'||!state?.profile||!old)return answer;
  if(fingerprint(state.profile)===oldFingerprint)return answer;
  // Actuar solamente después de un guardado confirmado en el almacenamiento actual.
  if(typeof localStorage!=='undefined'){
   try{const disk=JSON.parse(localStorage.getItem(KEY));
    if(fingerprint(disk?.profile)!==fingerprint(state.profile))return answer;
   }catch(_){return answer;}
  }
  if(!previousDay)try{currentCycle();}catch(_){}
  if(oldLog){ // Restaurar el avance previo si algún planificador antiguo lo ajustó.
   state.logs=state.logs||{};
   state.logs[date]=oldLog;
  }
  const result=regenerate(old,oldLog,daySnapshot);
  if(result.status==='updated')toast('Perfil guardado. Misión de hoy actualizada.');
  else if(result.status==='review')toast('Perfil guardado. Revisa la misión de hoy antes de continuar.');
  else if(result.status==='finished-or-manual')toast('Perfil guardado. Se conserva tu misión finalizada o personalizada.');
  if(typeof render==='function'&&result.status!=='no-cycle')render();
  return answer;
 };
 const beforeFinish=typeof finish==='function'?finish:null;
 if(beforeFinish)finish=function(){
  const d=state?.cycles?.flatMap(c=>c.days||[]).find(x=>x.date===dateKey());
  if(d?.blocked381||d?.blocked378){toast('Primero revisa tu perfil: no hay una misión automática habilitada.');return;}
  return beforeFinish();
 };
 const beforeWorkout=typeof workout==='function'?workout:null;
 if(beforeWorkout)workout=function(){
  const body=beforeWorkout(),log=state?.logs?.[dateKey()],day=state?.cycles?.flatMap(c=>c.days||[]).find(d=>d.date===dateKey());
  const extra=log?.items?.some(x=>x.archivedProfile3829)?
   '<div class="notice" role="status">Se conservaron las series realizadas con tu configuración anterior. Aparecen como registro y no debes repetirlas por el cambio de perfil.</div>':'';
  const warning=day?.blocked381&&!log?.complete?'<div class="notice warn" role="alert">'+String(day.blocked381).replace(/[<>&"']/g,'')+'</div>':'';
  return warning+extra+body;
 };
 root.HunterProfile3829=Object.freeze({version:'38.29',fingerprint,reconcile,regenerate,started});
})(window);
