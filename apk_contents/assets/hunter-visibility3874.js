/* Hunter 38.74: DOM lifecycle aware suspension of DECORATIVE animations.
 * Visibility pauses only CSS decorative effects. It never stops exercise
 * videos, guide timers, exercise canvases, or the native workout clock.
 */
(function(root,doc){'use strict';
 if(root.HunterVisibility3874)return;
 const canObserve=typeof root.IntersectionObserver==='function';
 const selectors=[
  '#app .monarch-hero33','#app .frame-glint26','#app .window-particles28',
  '#app .ambient-particles29','#app .coach-intro','#app .coach-banner-companion',
  '#app .rest-orbit','#app .reward','#app .hero26',
  '#app .knight-body','#app .system-card26',
  '#modal .coach-intro','#modal .monarch-hero33','#modal .knight-body'
 ].join(',');
 const watched=new Set();
 const stats={observed:0,paused:0,resumed:0,observations:0,updates:0,cleanup:0,hidden:0};
 let scheduled=0,observer=null;
 function updateClass(node,paused){
  if(!node.isConnected)return;
  if(node.classList.contains('hunter-offscreen3874')===paused)return;
  node.classList.toggle('hunter-offscreen3874',paused);
  if(paused)stats.paused++;else stats.resumed++;
 }
 if(canObserve){observer=new root.IntersectionObserver((records)=>{
  stats.observations+=records.length;
  for(const record of records)updateClass(record.target,!record.isIntersecting);
 },{threshold:0,rootMargin:'64px 0px'});}
 function refresh(){
  scheduled=0;stats.updates++;
  if(!observer)return;
  for(const node of watched){
   if(node.isConnected)continue;
   observer.unobserve(node);watched.delete(node);stats.cleanup++;
  }
  for(const node of doc.querySelectorAll(selectors)){
   if(watched.has(node))continue;
   watched.add(node);observer.observe(node);
  }
  stats.observed=watched.size;
 }
 function schedule(){
  if(scheduled||doc.hidden)return;
  scheduled=root.requestAnimationFrame(refresh);
 }
 // Only DOM additions/removals are monitored; attribute/style changes do not
 // trigger another scan. Request coalescing keeps updates to one per frame.
 if(observer&&root.MutationObserver){
  const mutation=new MutationObserver(schedule);
  for(const node of [doc.getElementById('app'),doc.getElementById('modal')]){
   if(node)mutation.observe(node,{childList:true,subtree:true});
  }
 }
 function onVisibility(){
  const hidden=doc.hidden;
  doc.documentElement.classList.toggle('hunter-apphidden3874',hidden);
  if(hidden){stats.hidden++;if(scheduled){root.cancelAnimationFrame(scheduled);scheduled=0;}}
  else schedule();
 }
 doc.addEventListener('visibilitychange',onVisibility,{passive:true});
 root.addEventListener('pageshow',schedule,{passive:true});
 root.HunterVisibility3874=Object.freeze({version:'38.74',refresh:schedule,snapshot:()=>({...stats,observerSupported:!!observer})});
 onVisibility();schedule();
})(window,document);

;
/* Hunter 38.78 — perfil original como única fuente de configuración.
 * No crea catálogo, no altera registros y no prescribe incrementos físicos.
 */
(function(root,doc){'use strict';
 if(root.HunterProfileUnified3878)return;
 const categories=['tren_superior','tren_central','tren_inferior','movilidad','cardio'];
 const safe=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const clamp=(x,lo,hi,fallback)=>Number.isInteger(Number(x))&&Number(x)>=lo&&Number(x)<=hi?Number(x):fallback;
 const validZone=zone=>{if(zone==='system')return true;try{return typeof zone==='string'&&zone.length<=60&&Boolean(new Intl.DateTimeFormat('es',{timeZone:zone}));}catch(_){return false;}};
 const weekdayOrder=[1,2,3,4,5,6,0];
 function schedule(profile){
  return weekdayOrder.map(i=>{
   const day=profile?.weekPlan?.[i];
   if(day==='cardio')return 4;
   if(day==='mobility')return 3;
   if(day!=='train')return 5;
   return {upper:0,core:1,lower:2}[profile?.weekFocus?.[i]]??0;
  });
 }
 function preferred(profile){
  const counts=categories.map((_,i)=>schedule(profile).filter(c=>c===i).length);
  const highest=Math.max(...counts);
  return highest?categories.filter((_,i)=>counts[i]===highest):categories.slice();
 }
 function derive(profile){
  if(!profile)return root.AsterionRules3872?.defaultProfile?.()||null;
  const equip=new Set(['body']);
  if(profile.equipment==='db'){equip.add('db');equip.add('dumbbells');}
  if(profile.equipment==='gym'){equip.add('gym');equip.add('dumbbells');}
  if(profile.bench)equip.add('bench');
  if(profile.step)equip.add('step');
  for(const item of Array.isArray(profile.materials46)?profile.materials46:[]){
   if(typeof item==='string'&&item.length<=70)equip.add(item);
  }
  return {
   active:true,
   experience:{basic:'principiante',intermediate:'intermedio',advanced:'avanzado'}[profile.difficulty]||'principiante',
   goal:String(profile.goal||''),custom_goal:String(profile.finalGoal||'').slice(0,500),
   weekPlan:schedule(profile),equipment:[...equip].sort(),
   modality:profile.trainingMode==='routines'?'rutinas':'misiones',
   intensity:Number(profile.trainingScale)>0.7?'moderada':'baja',
   dailyLimit:clamp(profile.trainingMode==='routines'?profile.routineExercises3878:profile.dailyMissions,1,7,3),
   minutes:clamp(profile.minutes,10,90,30),restSeconds:clamp(profile.restSeconds,0,1800,90),
   organization:profile.blockMode22==='rounds'?'rondas':'series',
   restrictions:Object.keys(profile.restrictions||{}).filter(k=>profile.restrictions[k]===true),
  };
 }
 function modeFields(form){
  if(!form)return;
  const routine=form.querySelector('input[name="trainingMode"]:checked')?.value==='routines';
  const ownR=form.querySelector('.unified-routines3878'),ownM=form.querySelector('.unified-missions3878');
  if(ownR){ownR.hidden=!routine;ownR.querySelectorAll('select').forEach(el=>el.disabled=!routine);}
  if(ownM)ownM.hidden=routine;
 }
 function options(values,current){return values.map(([id,label])=>`<option value="${safe(id)}" ${String(id)===String(current)?'selected':''}>${safe(label)}</option>`).join('');}
 function enhanceProfile(html){
  const wrap=doc.createElement('div');wrap.innerHTML=html;
  const form=wrap.querySelector('#profileForm');if(!form)return html;
  form.querySelectorAll('.portable-settings3868,.ar-settings3872').forEach(x=>x.remove());
  for(const note of wrap.querySelectorAll('.portable-plan3869'))if(/Configuración inicial de Asterion/i.test(note.textContent))note.remove();
  // El nivel, el equipo y la intensidad siguen siendo los originales, ahora juntos en Entrenamiento.
  const block=form.querySelector('.training-mode-block'),choices=block?.querySelector('.mode-choices');
  if(block&&choices){
   const difficulty=form.querySelector('[name="difficulty"]');
   const field=difficulty?.closest('label')||difficulty?.closest('.field');
   if(field){
    const title=field.querySelector('label');
    if(title&&title.childNodes.length&&title.firstChild?.nodeType===3)title.firstChild.textContent='Nivel de experiencia y dificultad';
    else if(field.tagName==='LABEL'){
     for(const child of field.childNodes)if(child.nodeType===3&&child.textContent.trim()){child.textContent='Nivel de experiencia y dificultad';break;}
    }
    field.classList.add('unified-experience3878');choices.before(field);
   }
   const missionLabel=form.querySelector('select[name="dailyMissions"]')?.closest('label');
   const missions=doc.createElement('div');missions.className='unified-missions3878';
   missions.innerHTML='<h4>Misiones diarias</h4><p class="footnote">Elige de 1 a 7 misiones. Esta cantidad no limita las rutinas.</p>';
   if(missionLabel)missions.append(missionLabel);
   choices.after(missions);
   const p=state?.profile||{};
   const minutes=[10,20,30,45].map(n=>[n,n+' minutos']);
   const lengths=Array.from({length:7},(_,i)=>[i+1,(i+1)+' ejercicio'+(i?'s':'')]);
   const routines=doc.createElement('div');routines.className='unified-routines3878';
   routines.innerHTML=`<h4>Configuración de Rutinas</h4><div class="fields"><label>Duración aproximada por rutina<select name="routineMinutes3878">${options(minutes,[10,20,30,45].includes(Number(p.minutes))?Number(p.minutes):30)}</select></label><label>Cantidad objetivo de ejercicios por rutina<select name="routineExercises3878">${options(lengths,clamp(p.routineExercises3878,1,7,4))}</select></label></div><p class="footnote">El generador dinámico respeta el tiempo, los ejercicios compatibles y los límites del perfil. Las rutinas predefinidas conservan su estructura.</p>`;
   missions.after(routines);
  }
  const first=form.querySelector('[name="name"]')?.closest('.profile-block')||form.querySelector('.profile-block');
  if(first&&!form.querySelector('select[name="timeZone"]')){
   const p=state?.profile||{};
   const old=state?.portable3868?.timeZone;
   const zone=validZone(p.timeZone)?p.timeZone:validZone(old)?old:'system';
   const choices=[['system','Automática (dispositivo)'],['America/Lima','América / Lima'],['UTC','UTC'],['Europe/Madrid','Europa / Madrid']];
   if(!choices.some(x=>x[0]===zone))choices.push([zone,zone]);
   const field=doc.createElement('div');field.className='field unified-timezone3878';
   field.innerHTML=`<label class="label" for="profileTimeZone3878">Zona horaria<select id="profileTimeZone3878" name="timeZone">${options(choices,zone)}</select></label><p class="footnote">Define los días del calendario y las revisiones. El valor automático utiliza la zona horaria del teléfono.</p>`;
   const name=form.querySelector('[name="name"]')?.closest('.field');
   if(name)name.after(field);else first.append(field);
  }
  modeFields(form);
  return wrap.innerHTML;
 }
 // Al abrir el formulario inicial o la edición, nunca se muestran dos motores de configuración.
 if(typeof profileForm==='function'){
  const previous=profileForm;
  profileForm=function(){return enhanceProfile(previous.apply(this,arguments));};
 }
 if(typeof trainingModeFields11==='function'){
  const previous=trainingModeFields11;
  trainingModeFields11=function(form){const result=previous.apply(this,arguments);modeFields(form);return result;};
 }
 if(typeof submitProfile==='function'){
  const previous=submitProfile;
  submitProfile=function(event){
   const prior=state?.profile;
   const result=previous.apply(this,arguments);
   if(state?.profile&&state.profile!==prior){
    const rules=state.asterion3872;
    if(rules?.rules&&rules.version===1){
     rules.profile=derive(state.profile);
     if(!rules.rules.initialExperienceDate)rules.rules.initialExperienceDate=root.Engine?.dateKey?.()||new Date().toISOString().slice(0,10);
    }
    root.HunterIntegration3869?.preferenceChanged?.();
    if(typeof save==='function')save();
   }
   return result;
  };
 }
 root.HunterProfileUnified3878=Object.freeze({version:'38.78',schedule,preferred,derive,enhanceProfile,modeFields,
  audit:()=>({source:'state.profile',favorites:preferred(state?.profile),configurationPanels:0,automaticGuidance:true,automaticReviews:true})});
 // Durante el arranque el perfil inicial puede haberse dibujado antes de cargar este módulo.
 // Se reconstruye una sola vez para que los nuevos campos existan antes del primer guardado.
 if(doc.getElementById('profileForm')&&typeof render==='function'){
  root.requestAnimationFrame(()=>{if(doc.getElementById('profileForm')&&!root.Runtime20?.initializing)render();});
 }
})(window,document);


;
/* Hunter System 38.79 — sincronización de la misión actual al guardar el perfil.
 * Siempre compara el perfil final, después de TODOS los adaptadores de guardado.
 * No recalcula al navegar, ni al guardar un formulario sin modificaciones.
 * Respeta sesiones completadas, rutinas elegidas manualmente y progreso real.
 */
(function(root){
 'use strict';
 if(root.HunterProfileSync3879 || typeof submitProfile!=='function')return;
 const clone=x=>x==null?x:JSON.parse(JSON.stringify(x));
 const canonical=x=>{
  if(Array.isArray(x))return x.map(canonical);
  if(x && typeof x==='object')return Object.fromEntries(Object.keys(x).sort().map(k=>[k,canonical(x[k])]));
  return x;
 };
 const signature=x=>JSON.stringify(canonical(x??null));
 const today=()=>root.Engine.dateKey();
 const findDay=date=>{
  for(const c of state?.cycles||[]){
   const index=c.days?.findIndex(d=>d.date===date)??-1;
   if(index>=0)return {cycle:c,index,day:c.days[index]};
  }
  return null;
 };
 const protectedDay=d=>!!(d?.customized||d?.routineId||d?.creatorAssigned||d?.manual);
 const previous=submitProfile;
 let busy=false;
 const telemetry={version:'38.79',changed:0,unchanged:0,updated:0,preserved:0,failed:0,last:'none'};
 submitProfile=function(event){
  if(busy||event?.target?.id!=='profileForm')return previous.apply(this,arguments);
  const before=clone(state?.profile);
  const oldSignature=signature(before);
  const date=today();
  const originalDay=clone(findDay(date)?.day);
  const originalLog=clone(state?.logs?.[date]);
  let answer;
  busy=true;
  try{answer=previous.apply(this,arguments);}
  finally{busy=false;}
  if(!before||!state?.profile)return answer; // El alta inicial usa la generación ordinaria.
  const afterSignature=signature(state.profile);
  if(afterSignature===oldSignature){
   telemetry.unchanged++;telemetry.last='unchanged';
   // Algunos planificadores heredados recrean el día incluso sin cambios.
   const match=findDay(date);
   let restored=false;
   if(match&&originalDay&&signature(match.day)!==signature(originalDay)){
    match.cycle.days[match.index]=originalDay;restored=true;
   }
   if(originalLog&&signature(state.logs?.[date])!==signature(originalLog)){
    state.logs=state.logs||{};state.logs[date]=originalLog;restored=true;
   }
   if(restored&&typeof save==='function')save();
   return answer;
  }
  // Debe existir una confirmación efectiva del guardado, no solo cambios en memoria.
  try{
   const onDisk=JSON.parse(localStorage.getItem(KEY)||'null');
   if(signature(onDisk?.profile)!==afterSignature){telemetry.failed++;telemetry.last='not-persisted';return answer;}
  }catch(_){telemetry.failed++;telemetry.last='storage-error';return answer;}
  telemetry.changed++;
  if(!findDay(date)&&typeof currentCycle==='function'){
   try{currentCycle();}catch(_){/* El plan puede estar restringido. */}
  }
  const current=findDay(date);
  if(!current){telemetry.last='no-day';return answer;}
  if(originalLog?.complete||protectedDay(originalDay)){
   // El plan ya finalizado o personalizado es historial, nunca una sesión nueva.
   if(originalDay)current.cycle.days[current.index]=originalDay;
   if(originalLog){state.logs=state.logs||{};state.logs[date]=originalLog;}
   const day=findDay(date)?.day;
   if(day){day.dailyFixed3828=date;day.dailySafety3828=root.HunterStable3828?.safetyKey?.(state.profile)||day.dailySafety3828;}
   if(typeof save==='function')save();
   telemetry.preserved++;telemetry.last='completed-or-custom';
   return answer;
  }
  // Reconciliar las series ya hechas sin repetirlas al sustituir ejercicios.
  if(originalLog){state.logs=state.logs||{};state.logs[date]=originalLog;}
  const engine=root.HunterProfile3829;
  let result;
  try{
   if(typeof engine?.regenerate!=='function')throw Error('Replanificador no disponible');
   result=engine.regenerate(before,originalLog,originalDay);
   // Fijar también la categoría superior/inferior/central escogida en el perfil
   // final: la implementación antigua guardaba weekFocus demasiado tarde.
   const c=findDay(date)?.cycle;
   if(c&&root.HunterStrict3838?.apply && state.profile)
    root.HunterStrict3838.apply(c,state.profile,state.logs||{},date);
   if(typeof save==='function')save();
   if(result?.status==='updated'||result?.status==='review'){
    telemetry.updated++;telemetry.last=result.status;
    if(typeof render==='function')render();
   }else{
    telemetry.last=result?.status||'no-result';
   }
  }catch(error){
   telemetry.failed++;telemetry.last='recalculation-error';
   console.error('No se pudo sincronizar la misión actual con el perfil',error);
   if(typeof toast==='function')toast('Perfil guardado. No se pudo recalcular la misión de hoy.');
  }
  return answer;
 };
 root.HunterProfileSync3879=Object.freeze({version:'38.79',diagnostics:()=>({...telemetry})});
})(window);
