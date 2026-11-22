/* Hunter System V38.28: estabilidad de la misión diaria.
 * La selección no se guarda en un archivo adicional: utiliza el ciclo que la
 * aplicación ya mantiene para registrar progreso. La semilla es por fecha,
 * perfil y ciclo. Los cambios ordinarios del perfil rigen para días futuros.
 * Una nueva restricción física requiere replanificación solo si no se inició
 * la sesión; las series registradas no se sobrescriben.
 */
(function(root){
 'use strict';
 if(root.HunterStable3828)return;
 const E=root.Engine;
 const clone=x=>JSON.parse(JSON.stringify(x));
 const today=()=>E?.dateKey?.()||new Date().toLocaleDateString('sv-SE');
 // Solo campos que afectan elegibilidad física de los ejercicios.
 const safetyKey=p=>JSON.stringify([
   p?.age,p?.equipment,p?.gymEquipment23,p?.bench,p?.step,
   p?.injuries||[],p?.restrictions||[],p?.limits||null,p?.health||{}
 ]);
 const started=l=>Boolean(l&&(l.complete||l.manual||l.pain||l.fatigue||
   l.items?.some(i=>i.actual?.some(a=>a.done===true||a.selfReported))||
   l.missionTasks?.some(t=>t.done===true)));
 // Las restricciones recién declaradas requieren conservar registros existentes.
 function keep(day,p,logs={},date=today()){
   if(!day||day.date!==date||day.dailyFixed3828!==date)return false;
   return day.dailySafety3828===safetyKey(p)||started(logs?.[date]);
 }
 function existing(date){
   return state?.cycles?.flatMap(c=>c.days||[]).find(d=>d.date===date)||null;
 }
 function eligibleToPin(day,p,date){
   if(!day||!p||!Array.isArray(day.items))return false;
   const choice=p.weekPlan?.[root.Schedule?.weekday?.(date)];
   if(choice==='rest')return day.kind==='rest';
   if(choice && choice!=='rest' && day.kind==='rest')return false;
   return day.items.length>0 || Boolean(day.blocked381||day.blocked378);
 }
 function pin(day,p,date){
   if(!day||day.date!==date)return false;
   const changed=day.dailyFixed3828!==date;
   day.dailyFixed3828=date;
   day.dailySafety3828=safetyKey(p);
   return changed;
 }
 // Primer arranque tras la actualización: recuperar el plan de hoy existente
 // antes de que los planificadores heredados puedan recalcularlo.
 try{
   const d=existing(today());
   if(d&&state?.profile&&eligibleToPin(d,state.profile,today())&&!d.dailyFixed3828){
     pin(d,state.profile,today());
   }
 }catch(_){/* Perfil aún no inicializado. */}
 if(typeof currentCycle==='function'){
   const original=currentCycle;
   let inside=false;
   currentCycle=function(){
     if(inside)return original();
     inside=true;
     try{
       const date=today(),profile=state?.profile,prior=profile&&existing(date);
       // Congelar solo la rutina de hoy. Los días futuros siguen siendo editables.
       if(prior&&!prior.dailyFixed3828&&eligibleToPin(prior,profile,date))pin(prior,profile,date);
       const frozen=prior&&keep(prior,profile,state?.logs||{},date)?clone(prior):null;
       const c=original();
       if(!c||!profile)return c;
       const at=c.days?.findIndex(d=>d.date===date)??-1;
       if(at<0)return c;
       let changed=false;
       if(frozen){
         const after=c.days[at];
         // Recupera contenidos incluso si otro planificador anterior rehízo todo el día.
         if(JSON.stringify(after)!==JSON.stringify(frozen)){
           c.days[at]=frozen;changed=true;
         }
       }else{
         const d=c.days[at];
         if(!d.dailyFixed3828&&eligibleToPin(d,profile,date))changed=pin(d,profile,date);
       }
       // Si cambian las restricciones, no alterar registros de una sesión comenzada.
       const actual=c.days[at];
       const newSafety=safetyKey(profile);
       if(actual?.dailyFixed3828===date && actual.dailySafety3828!==newSafety &&
          started(state?.logs?.[date])){
         if(!actual.dailyReview3828){
           actual.dailyReview3828=true;changed=true;
         }
       }
       if(changed&&typeof save==='function'&&!root.Runtime20?.initializing)save();
       return c;
     }finally{inside=false;}
   };
 }
 // Aviso dentro de la vista existente; no crea modal ni otra pestaña.
 if(typeof workout==='function'){
   const prevWorkout=workout;
   workout=function(){
     const view=prevWorkout(),d=existing(today());
     return d?.dailyReview3828?
       '<div class="notice warn" role="alert">Has cambiado restricciones de salud o equipamiento durante una misión iniciada. Detén el entrenamiento y revisa la sesión antes de continuar; tus registros se han conservado.</div>'+view:view;
   };
 }
 root.HunterStable3828=Object.freeze({version:'38.28',today,safetyKey,started,keep,pin});
})(window);
