/* Hunter System 38.69 – Categories -> planning, routines and actual-volume journal.
 * Never copies Asterion numeric prescriptions or rewrites historical sessions.
 * Uses only pre-screened candidates provided by Hunter's planning engine.
 */
(function(root){
 'use strict';
 if(root.HunterIntegration3869)return;
 const E=root.Engine,P=root.HunterPortable3868,D=root.HunterPortableData3868;
 if(!E||!P||!D||D.hunterRows.length!==1200)throw Error('Integration3869: faltan datos base');
 const CATS=D.categories.map(c=>c.code),CSET=new Set(CATS),labels=Object.fromEntries(D.categories.map(c=>[c.code,c.label]));
 const safe=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const categoryOf=id=>P.exerciseMeta(id)?.category||null;
 const preferences=()=>P.settings();
 const day=()=>E.dateKey();
 const sum=x=>x.reduce((a,b)=>a+b,0);
 const effectiveDate=()=>{
  const zone=preferences().timeZone;
  if(!zone||zone==='system')return day();
  try{
   const parts=new Intl.DateTimeFormat('en-US',{timeZone:zone,year:'numeric',month:'2-digit',day:'2-digit'}).formatToParts(new Date());
   const get=k=>parts.find(p=>p.type===k)?.value;
   return `${get('year')}-${get('month')}-${get('day')}`;
  }catch(_){return day();}
 };
 function prefers(exercises){
  const allowed=new Set(preferences().categories);
  if(allowed.size===CATS.length)return exercises;
  const preferred=exercises.filter(e=>allowed.has(categoryOf(e.id)));
  // Never make an otherwise safe strength/mobility session empty just because
  // the selected categories contain no compatible exercise of that kind.
  return preferred.length?preferred:exercises;
 }
 function activityLimit(cap){
  if(!Number.isFinite(Number(cap)))return cap;
  return Math.max(1,Math.min(Math.floor(Number(cap)),preferences().dailyActivities));
 }
 let lastFingerprint=JSON.stringify([preferences().categories,preferences().dailyActivities]),invalidated=0;
 function preferenceChanged(){
  const pref=preferences();
  const next=JSON.stringify([pref.categories,pref.dailyActivities]);
  if(next===lastFingerprint)return 0;
  lastFingerprint=next;
  const today=day();let count=0;
  if(typeof state==='undefined'||!state||!Array.isArray(state.cycles)||!state.profile)return 0;
  for(const c of state.cycles){
   if(!Array.isArray(c.days))continue;
   let changed=false;
   for(const d of c.days){
    if(d.date<today||d.customized||d.routineId||d.creatorAssigned||d.manual||d.scheduleChoice==='rest')continue;
    // Check the established session and planner locks before invalidation.
    if(root.HunterDynamic381?.locked?.(d,state.logs||{}))continue;
    delete d.dynamicKey381;changed=true;count++;
   }
   if(changed)delete c.dynamicKey381;
  }
  invalidated+=count;
  if(count&&typeof save==='function')save();
  return count;
 }
 function completedSets(logs){
  const out=[];
  for(const [date,log] of Object.entries(logs||{})){
   if(!/^\d{4}-\d{2}-\d{2}$/.test(date)||!log||!Array.isArray(log.items))continue;
   for(const item of log.items){
    const e=E.byId(item?.id),category=categoryOf(item?.id);
    if(!e||!category||!Array.isArray(item.actual))continue;
    const unit=e.unit==='sec'?'seconds':'reps';
    const factor=E.sidesOf(item)?2:1;
    const done=item.actual.filter(a=>a&&a.done===true&&Number.isFinite(Number(a.value))&&Number(a.value)>0&&Number(a.value)<=10000);
    if(!done.length)continue;
    const quantity=sum(done.map(a=>Number(a.value)*factor));
    out.push({date,id:e.id,name:e.name,category,unit,sets:done.length,quantity,
      effort:Number.isFinite(Number(log.rpe))?Number(log.rpe):null,interrupted:log.pain===true});
   }
  }
  return out.sort((a,b)=>a.date.localeCompare(b.date)||a.id.localeCompare(b.id));
 }
 function exerciseSummary(logs,today=effectiveDate(),windowDays=28){
  const start=E.plus(today,1-windowDays),buckets=new Map();
  for(const r of completedSets(logs)){
   if(r.date<start||r.date>today)continue;
   const key=r.id+'|'+r.unit;
   let b=buckets.get(key);
   if(!b){b={id:r.id,name:r.name,category:r.category,unit:r.unit,quantity:0,sets:0,days:new Set(),last:'',first:''};buckets.set(key,b);}
   b.quantity+=r.quantity;b.sets+=r.sets;b.days.add(r.date);
   if(!b.first)b.first=r.date;
   b.last=r.date;
  }
  return [...buckets.values()].map(b=>({...b,days:b.days.size})).sort((a,b)=>b.last.localeCompare(a.last)||b.sets-a.sets||a.name.localeCompare(b.name,'es'));
 }
 function referenceByExercise(logs,id){
  const rows=completedSets(logs).filter(r=>r.id===id&&!r.interrupted&&r.effort!==null&&r.effort>=1&&r.effort<=7);
  if(!rows.length)return null;
  const first=rows[0];
  return {date:first.date,id,category:first.category,unit:first.unit,quantity:first.quantity,sets:first.sets,
    source:'primera sesión registrada con esfuerzo informado, no dosis recomendada'};
 }
 function ledger(){
  const recent=exerciseSummary(state.logs||{}),counts=recent.length,total=recent.reduce((t,r)=>t+r.sets,0);
  const shown=recent.slice(0,10);
  const html=shown.map(r=>{const ref=referenceByExercise(state.logs||{},r.id);
   const base=ref&&ref.unit===r.unit?` · referencia personal: ${ref.quantity.toLocaleString('es')} ${r.unit==='seconds'?'s':'rep'} (${ref.date})`:'';
   return `<li><span><strong>${safe(r.name)}</strong><small>${safe(labels[r.category])} · ${r.days} días · ${r.sets} series${base}</small></span><b>${r.quantity.toLocaleString('es')} ${r.unit==='seconds'?'s':'rep'}</b></li>`;
  }).join('');
  return `<section class="card portable-detail3869" aria-label="Progreso por ejercicio"><div class="portable-detail-heading3869">
   <h3>Registro por ejercicio</h3><p>${counts} ejercicios con cantidades en los últimos 28 días · ${total} series registradas.</p></div>
   ${counts?`<ul>${html}</ul>${counts>10?`<p class="portable-hint3869">Se muestran los 10 ejercicios con actividad más reciente.</p>`:''}`:'<p class="portable-hint3869">Registra una serie terminada para ver el seguimiento individual.</p>'}
   <p class="portable-hint3869">Las referencias individuales provienen de tu historial, nunca de las dosis importadas de Asterion. Repeticiones y segundos no se mezclan. No son objetivos de carga.</p>
  </section>`;
 }
 function planNotice(){
  const c=preferences(),names=c.categories.map(k=>labels[k]).join(' · ');
  return `<div class="portable-plan3869" role="note"><strong>Planificación según tus categorías</strong><p>Prioridad: ${safe(names)}. Máximo orientativo de ${c.dailyActivities} actividades por sesión, respetando las restricciones y el calendario. Si no hay ejercicios compatibles de las categorías elegidas, se conserva el tipo de entrenamiento y se utilizan alternativas seguras.</p></div>`;
 }
 function enhanceRoutines(){
  const names=preferences().categories.map(c=>labels[c]).join(' · ');
  return `<div class="portable-plan3869" role="note"><strong>Rutinas y categorías</strong><p>Las rutinas dinámicas dan prioridad a ${safe(names)}. Las rutinas guardadas y las plantillas anteriores permanecen disponibles y no cambian sus series.</p></div>`;
 }
 // Rank compatible routines by the selected categories, but preserve every
 // original/custom routine and their relative order when scores tie.
 if(typeof routineList==='function'){
  const previous=routineList;
  routineList=function(){
   const list=previous.apply(this,arguments);
   const selected=new Set(preferences().categories);
   if(selected.size===CATS.length)return list;
   const score=r=>{
    if(!Array.isArray(r?.items)||!r.items.length)return 0;
    const cats=r.items.map(x=>categoryOf(x.id)).filter(Boolean);
    return cats.length?cats.filter(c=>selected.has(c)).length/cats.length:0;
   };
   return list.map((r,i)=>({r,i,score:score(r)})).sort((a,b)=>b.score-a.score||a.i-b.i).map(x=>x.r);
  };
 }
 // Pages: preserve all preexisting drawers, route controls and entries.
 if(typeof plan==='function'){
  const original=plan;plan=function(){return planNotice()+original.apply(this,arguments);};
 }
 if(typeof routines==='function'){
  const original=routines;routines=function(){return enhanceRoutines()+original.apply(this,arguments);};
 }
 if(typeof history==='function'){
  const original=history;history=function(){return original.apply(this,arguments).replace('</section>', '</section>'+ledger());};
 }
 if(typeof profileForm==='function'){
  const original=profileForm;
  profileForm=function(initial){
   const html=original.apply(this,arguments);
   if(!initial)return html;
   const note=`<div class="portable-plan3869" role="note"><strong>Configuración inicial de Asterion</strong><p>En la pestaña «Sistema» puedes elegir categorías, experiencia y cantidad orientativa de actividades. Estos ajustes afectan solo a nuevas sesiones, no al historial.</p><button class="btn ghost" type="button" onclick="HunterPanels3857.show(3)">Configurar categorías →</button></div>`;
   return html.replace('<form id="profileForm"',note+'<form id="profileForm"');
  };
 }
 // The existing portable 38.68 module writes preferences into state.portable3868
 // and calls preferenceChanged; legacy backups keep their original structure.
 root.HunterIntegration3869=Object.freeze({version:'38.69',prefers,activityLimit,preferenceChanged,
  categoryOf,completedSets,exerciseSummary,referenceByExercise,effectiveDate,
  audit:()=>({exercises:D.hunterRows.length,matchedCandidates:D.matchedCount,invalidatedFutureDays:invalidated,
  categories:CATS.slice(),numericAsterionDosesApplied:0})});
})(window);
