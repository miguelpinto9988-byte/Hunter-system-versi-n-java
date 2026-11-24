/* Hunter System V38.26 -- cierre funcional localizado.
   Aceptadas: 35 (indicador de fase), 58 (sustitución ANTES de comenzar),
   75 (desafío opcional de registro sin XP). NO implementa 66 (cambio en sesión).
   Sin diálogos adicionales, ni imagen/video/catálogo nuevo.
*/
(function(root,doc){
 'use strict';
 if(root.HunterClosure3826) return;
 const stats={phaseUpdates:0,missions:0,swaps:0,blockedSwaps:0};
 const escText=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const isActive=l=>!!(l&&(l.complete||l.pain||l.manual||Number(l.rpe)>0||String(l.note||'').trim().length>0||l.missionTasks?.some(t=>t.done)||l.items?.some(x=>x?.selfReported||x?.missionState||x?.actual?.some(a=>a.done))));
 const isRestricted=p=>!!(p?.injuries?.some(x=>x.stage!=='past')||p?.health?.pain&&p.health.pain!=='none'||p?.health?.acute||p?.limits||p?.health?.special);
 const eligible=(e,p)=>{
   if(!e||!p||!root.HunterMaterials3814?.eligible?.(e,p))return false;
   if(isRestricted(p))return root.HunterConstraints3811?.allowed?.(e,p)===true;
   try {return !!root.Adapt?.allowed?.(e,p);} catch(_){return false;}
 };
 function markPhase(){
   const element=doc.querySelector('#vectorPhase');
   if(!element||!element.textContent)return;
   let content=element.textContent.replace(/^[\u2190-\u21ff\u2794]\s+/u,'');
   const arrow=/\b(regreso|retorno|volver|vuelta)\b/i.test(content)?'↩':
     /\b(movimiento|avance|ejecuci[oó]n|desplazamiento)\b/i.test(content)?'➜':'';
   const updated=arrow?arrow+' '+content:content;
   if(element.textContent!==updated){element.textContent=updated;stats.phaseUpdates++;}
 }
 if(typeof vectorPaint==='function') {
   const original=vectorPaint;
   vectorPaint=function(){const result=original.apply(this,arguments);markPhase();return result;};
 }
 function prepareChallenge(d){
   if(!d||d.kind==='rest'||d.date!==root.Engine?.dateKey?.())return '';
   const record=state?.logs?.[d.date];
   if(record?.pain || isRestricted(state?.profile))return '';
   const completed=record?.complete===true&&Number(record.rpe)>=1&&Number(record.rpe)<=7;
   // No prescribe ejercicios ni genera XP; solo reconoce seguimiento ya realizado.
   stats.missions++;
   return '<small class="hunter-challenge3826" aria-live="off">'+(completed?
     '◇ Desafío opcional: registro de esfuerzo completado ✓':
     '◇ Desafío opcional: registra tu esfuerzo al terminar · sin ejercicios extra')+'</small>';
 }
 function availableSwaps(d,index){
   const current=d?.items?.[index];
   const p=state?.profile;
   const catalog=root.Engine?.exercises;
   if(!p||!Array.isArray(catalog)||!current)return [];
   const initial=root.Engine.byId(current.id);
   if(!initial)return [];
   return catalog.filter(e=>e&&e.id!==initial.id&&e.category===initial.category&&e.group===initial.group&&e.unit===initial.unit&&
     !d.items.some((x,j)=>j!==index&&x.id===e.id)&&!e.sourceOnly&&eligible(e,p)).slice(0,12);
 }
 function futureSwaps(d){
   if(!d||!Array.isArray(d.items)||!d.items.length||!/^\d{4}-\d\d-\d\d$/.test(d.date)||
       d.date<=root.Engine?.dateKey?.()||d.routineId||
       (d.customized&&!d.prePlanSwap3826)||isActive(state?.logs?.[d.date])) return '';
   if(!root.Adapt?.mode?.(state?.profile)?.auto)return '';
   const rows=d.items.map((item,index)=>{
     const alts=availableSwaps(d,index);
     if(!alts.length)return '';
     const e=root.Engine.byId(item.id);
     return '<label class="hunter-swaprow3826">'+escText(e.name)+' <span> · cambiar antes de empezar</span><select data-swap-index="'+index+'" aria-label="Alternativa para '+escText(e.name)+'">'+
       '<option value="">Conservar ejercicio</option>'+alts.map(a=>'<option value="'+escText(a.id)+'">'+escText(a.name)+'</option>').join('')+
       '</select></label>';
   }).filter(Boolean);
   if(!rows.length)return '';
   return '<div class="hunter-preswap3826" data-swap-date="'+d.date+'">'+rows.join('')+
     '<button type="button" class="btn ghost" onclick="HunterClosure3826.swapPlanned(this)">Aplicar cambios futuros</button></div>';
 }
 if(typeof missionWindow==='function') {
   const original=missionWindow;
   missionWindow=function(d){
     let markup=original.apply(this,arguments);
     if(typeof markup!=='string')return markup;
     const challenge=prepareChallenge(d);
     const swap=futureSwaps(d);
     if(!challenge&&!swap)return markup;
     // Reutilizar la ventana original. No se añade modal, ni altera el reproductor.
     const close=markup.lastIndexOf('</section>');
     if(close>=0)markup=markup.slice(0,close)+challenge+swap+markup.slice(close);
     return markup;
   };
 }
 function swapPlanned(button){
   const container=button?.closest?.('[data-swap-date]');
   const date=container?.dataset?.swapDate;
   const day=typeof dayByDate==='function'?dayByDate(date):null;
   if(!day||date<=root.Engine?.dateKey?.()||day.routineId||
       (day.customized&&!day.prePlanSwap3826)||isActive(state?.logs?.[date])||
       !root.Adapt?.mode?.(state?.profile)?.auto){stats.blockedSwaps++;return false;}
   const next=day.items.map(x=>({...x}));let changed=0;
   for(const select of container.querySelectorAll('select[data-swap-index]')){
     const index=Number(select.dataset.swapIndex);
     if(!Number.isSafeInteger(index)||index<0||index>=next.length){stats.blockedSwaps++;return false;}
     const id=select.value;
     if(!id)continue;
     const allow=availableSwaps(day,index).some(e=>e.id===id);
     if(!allow){stats.blockedSwaps++;return false;}
     next[index]={...next[index],id,plannedSwap3826:true};changed++;
   }
   if(!changed)return false;
   const original={items:day.items,customized:day.customized,prePlanSwap3826:day.prePlanSwap3826};
   day.items=next;day.customized=true;day.prePlanSwap3826=true;
   let saved=false;
   try{saved=typeof save==='function'&&save()===true;}catch(_){}
   if(!saved){Object.assign(day,original);stats.blockedSwaps++;return false;}
   stats.swaps+=changed;
   if(typeof showMission==='function')showMission(date);
   if(typeof toast==='function')toast('Próxima sesión actualizada. Las sesiones iniciadas no cambian.');
   return true;
 }
 root.HunterClosure3826=Object.freeze({version:'38.26',swapPlanned,
   diagnostics:()=>({...stats,note:'Indicadores de fase, cambio previo y reconocimiento opcional; no verifica posturas clínicas.'})});
})(window,document);

;
/* Weekly central-body integration 38.47. */
/* Hunter System 38.27 — unificación local de planificación y progresión exigente.
   No añade ventanas, no cambia sesiones manuales/completadas y no prescribe rehabilitación.
   Las 15 mejoras excluidas corresponden a propuestas, NO a ejercicios. */
(function(root){
'use strict';
if(root.HunterUnified3827)return;
const E=root.Engine,D=root.HunterDynamic381,A=root.Adapt;
if(!E||!D||!A)return;
const categories=[['strength','Fuerza'],['cardio','Cardio'],['recovery','Movilidad']];
const toNumber=(x,min,max)=>{const n=Number(x);return Number.isFinite(n)?Math.max(min,Math.min(max,n)):min;};
const risk=p=>Boolean(p?.limits||p?.health?.special||p?.health?.acute||p?.health?.redFlags||
 (p?.health?.pain&&p.health.pain!=='none')||(p?.injuries||[]).some(x=>x&&x.stage!=='past'));
const activeLog=l=>Boolean(l&&(l.complete||l.manual||l.pain||l.fatigue||l.selfReported||
  l.missionTasks?.some(t=>t.done)||l.items?.some(x=>x.selfReported||x.missionState||x.actual?.some(a=>a.done))));
const isDay=s=>/^\d{4}-\d{2}-\d{2}$/.test(s);
function records(logs,date){
 const start=E.plus(date,-14),recent=Object.entries(logs||{}).filter(([d,l])=>isDay(d)&&d<date&&d>=start&&l);
 const completed=recent.filter(([,l])=>l.complete===true&&!l.manual&&!l.pain&&
  l.items?.some(x=>x.actual?.some(a=>a.done===true))&&Number(l.rpe)>0).map(([,l])=>l);
 const avg=completed.length?completed.reduce((s,l)=>s+Number(l.rpe),0)/completed.length:0;
 const symptoms=recent.some(([,l])=>l.pain||l.fatigue||Number(l.rpe)>=8)||
   !!root.HunterConstraints3811?.symptomTrend?.(logs,date)?.recentSymptom;
 return {completed:completed.length,average:avg,symptoms,ready:completed.length>=2&&avg>=3&&avg<=7&&!symptoms};
}
function dose(e,p,kind,status,feedback,date,legacy,weekIndex,logs,base){
 const out={...legacy},b=base||legacy;
 if(!e||!p||p.age<18||weekIndex<1||weekIndex>2||
   !['strength','cardio'].includes(kind)||risk(p)||status?.reduced||feedback?.strain||
   out.noAutoLoad||Number(p.trainingScale)<1&&p.trainingScale!==undefined)return out;
 const data=records(logs,date);
 if(!data.ready||feedback?.canProgress!==true)return out;
 const seconds=e.unit==='sec',initial=toNumber(b.target,1,120),current=toNumber(out.target,1,120);
 const aggressive=weekIndex===1?.10:.18;
 const limit=seconds?90:(p.difficulty==='advanced'?24:p.difficulty==='intermediate'?20:16);
 const wanted=Math.min(limit,Math.ceil(initial*(1+aggressive)));
 // Supera la progresión conservadora sin duplicar los aumentos del motor anterior.
 out.target=Math.max(current,wanted);
 out.sets=toNumber(out.sets,1,4);
 // Intensificar una sola variable por revisión. Sin series extra automáticas.
 out.progression3827={mode:'exigente',week:weekIndex+1,evidence:data.completed,
  reason:'Mejora condicionada a sesiones completadas, RPE 3–7 y ausencia de molestias.'};
 return out;
}
function gymTemplates(p,logs={}){
 if(p?.equipment!=='gym'||!D.gate(p).ok)return [];
 const level=typeof root.TrainingProgression19?.level==='function'?root.TrainingProgression19.level(p):
   ({basic:0,intermediate:1,advanced:2}[p.difficulty]||0);
 const focus=[['full','general'],['upper','upper'],['lower','lower'],['core','core'],['push','upper'],['pull','upper']];
 const date=E.dateKey(),output=[];
 const pastLogs=Object.fromEntries(Object.entries(logs||{}).filter(([d])=>d<date));
 for(const [family,body]of focus){
  for(const [kind,label]of categories){
   if(family==='core'&&kind!=='strength')continue;
   const candidate={...p,focus:body,dynamicRequestedFocus3847:body,dynamicGymPreferred3827:true};
   const chosen=D.choose(candidate,kind,'38.27|'+date+'|gym|'+family+'|'+kind,pastLogs,date,new Set(),null,0);
   if(!chosen.items?.length)continue;
   // Coherencia con el mismo filtro usado para misiones y sesiones diarias.
   if(!chosen.items.every(item=>{const e=E.byId(item.id);return e&&D.compatible(e,p,risk(p));}))continue;
   const all=chosen.items.map(item=>E.byId(item.id));
   const known=new Set(Object.keys(root.Training23?.gymEquipment||{}));
   const gymNeeds=[...new Set(all.flatMap(e=>e.gymRequirements23||[]).filter(x=>known.has(x)))];
   const requirements=[...new Set(all.flatMap(e=>Array.isArray(e.requirements)?e.requirements:[]))];
   output.push({id:'dyn3827-gym-'+family+'-'+kind,name:'Gimnasio dinámico · '+label+' · '+
       ({full:'Completo',upper:'Superior',lower:'Inferior',core:'Tren central',push:'Empuje',pull:'Tracción'}[family]),
    category:chosen.effective==='cardio'?'Cardio':chosen.effective==='recovery'?'Movilidad':'Fuerza',
    level,items:chosen.items,requirements,goalGroups:['fitness','habit','movement','build','athletic'],
    family:'gym-'+family,gymOnly23:true,gymRequirements23:gymNeeds,extension:'standard',
    minutes:Math.max(10,Math.min(90,Number(p.minutes)||20)),safe381:true,dynamic3827:true,
    description:'Rutina local calculada a partir de las demostraciones 2D y las preferencias del perfil.'});
  }
 }
 return output;
}
// Reutilizar el centro de rutinas y la sección de gimnasio; no crear otro panel.
if(typeof allRoutines==='function'){
 const old=allRoutines;
 allRoutines=function(){return [...old(),...gymTemplates(state?.profile,state?.logs||{})];};
}
// Evitar que una asignación AUTOMÁTICA heredada impida el generador común.
// No se toca ninguna asignación manual por routineId, sesión personalizada o con datos.
function unify(c,p,logs,today){
 if(!c?.days||!p||!D.patch||c.reviews?.at(-1)?.action==='pausar')return 0;
 let released=0;
 for(const d of c.days){
  if(d.date<today||d.customized||activeLog(logs?.[d.date])||d.tasks?.some(t=>t.done)||root.HunterStable3828?.keep?.(d,p,logs,today))continue;
  if(d.creatorAssigned===true){
   delete d.routineId;delete d.creatorAssigned;delete d.sourceSequence;delete d.autoTemplateId;
   delete d.dynamicKey381;released++;
  }
 }
 if(released)delete c.dynamicKey381;
 return released+D.patch(c,p,logs,today);
}
if(typeof currentCycle==='function'){
 const previous=currentCycle;
 currentCycle=function(){const c=previous();if(!c||!state?.profile)return c;
  const changed=unify(c,state.profile,state.logs||{},E.dateKey());
  if(changed&&typeof save==='function'&&!root.Runtime20?.initializing)save();
  return c;
 };
}
root.HunterUnified3827=Object.freeze({version:'38.27',records,dose,gymTemplates,unify,risk,
 diagnostics:()=>({engine:'HunterDynamic381',sources:['misiones','diario','gimnasio','casa'],offline:true,
  progression:'exigente, basada en rendimiento; no incremento ante riesgo'})});
})(typeof window==='undefined'?globalThis:window);

;
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

;
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
  'weekPlan','weekFocus','equipment','equipmentModes3880','gymEquipment23','load','minutes','restSeconds','dailyMissions',
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

