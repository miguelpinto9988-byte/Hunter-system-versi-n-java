/* Hunter System 38.57: perfiles en tres paneles y actualizaciones parciales.
 * No cambia catálogos, dosis ni registros de entrenamiento.
 * Carga antes del finalizador hunter-optimize3848.js.
 */
(function(root, doc){
 'use strict';
 if(root.HunterPanels3857)return;
 if(typeof profileForm!=='function'||typeof submitProfile!=='function'||
    typeof go!=='function'||typeof render!=='function')throw Error('Panels3857: faltan dependencias de la interfaz');
 const STAGES=[{title:'Personal',hint:'Foto y datos generales'},
  {title:'Entrenamiento',hint:'Objetivos y equipo'},
  {title:'Sistema',hint:'Salud, preferencias y guardado'}];
 let stage=1, onFrame=false, lastTileKey='',lastStatsKey='';
 const telemetry={profiles:0,stageChanges:0,preventedRebuilds:0,partialHomeUpdates:0,
  partialMonarchUpdates:0,monarchFallbacks:0,validationReveals:0,calendarRestores:0,routineRefreshes:0,lazyImages:0};
 const previousProfileForm=profileForm;
 profileForm=function(initial){
  let html=previousProfileForm.apply(this,arguments);
  let index=0;
  const assignments=[1,1,2,2,2,2,3];
  html=html.replace(/<(div|section|details)(\s+class="([^"]*)")([^>]*)>/g,
   (markup,tag,classPart,classes,rest)=>{
     const names=classes.split(/\s+/);
     let pane=null;
     if(names.includes('profile-block'))pane=assignments[index++];
     else if(names.includes('health-compact')||names.includes('creator-settings'))pane=3;
     if(!pane)return markup;
     return `<${tag}${classPart}${rest} data-profile-pane3857="${pane}">`;
   });
  if(index!==assignments.length){
   console.warn('Panels3857: los bloques de Perfil cambiaron',index);
   return previousProfileForm.apply(this,arguments);
  }
  const navigation=`<div class="profile-panels3857" role="tablist" aria-label="Secciones de perfil">${STAGES.map((s,i)=>
   `<button type="button" role="tab" id="profile-tab3857-${i+1}" aria-controls="profileForm" aria-selected="${stage===i+1}" class="profile-panel-tab3857${stage===i+1?' active':''}" onclick="HunterPanels3857.show(${i+1})"><strong>${String(i+1).padStart(2,'0')} · ${s.title}</strong><small>${s.hint}</small></button>`).join('')}</div>`;
  html=html.replace('class="intro profile-v3"',`class="intro profile-v3" data-active-pane3857="${stage}"`)
   .replace(/<form id="profileForm"([^>]*)>/,
     (full,attrs)=>`<form id="profileForm"${attrs} novalidate>${navigation}`)
   .replace('</form></section>',`<div class="profile-panel-actions3857">
     <button class="btn ghost" id="profile-prev3857" type="button" onclick="HunterPanels3857.show(Math.max(1,HunterPanels3857.stage()-1))">← Anterior</button>
     <span class="profile-panel-counter3857" aria-live="polite">Panel ${stage} / 3</span>
     <button class="btn primary" id="profile-next3857" type="button" onclick="HunterPanels3857.show(Math.min(3,HunterPanels3857.stage()+1))">Siguiente →</button>
   </div></form></section>`);
  return html;
 };
 function show(next){
  stage=Math.max(1,Math.min(3,Number(next)||1));
  const form=doc.querySelector('#profileForm');
  if(!form)return false;
  const host=form.closest('.profile-v3');if(!host)return false;
  host.dataset.activePane3857=String(stage);
  const scene=form.closest('.scene');if(scene)scene.dataset.profilePane3857=String(stage);
  form.noValidate=true;
  form.querySelectorAll('[role="tab"][id^="profile-tab3857-"]').forEach((button,i)=>{
   const active=i+1===stage;
   button.classList.toggle('active',active);
   button.setAttribute('aria-selected',String(active));
   button.tabIndex=active?0:-1;
  });
  const counter=form.querySelector('.profile-panel-counter3857');if(counter)counter.textContent=`Panel ${stage} / 3`;
  const prev=form.querySelector('#profile-prev3857');if(prev)prev.disabled=stage===1;
  const nextBtn=form.querySelector('#profile-next3857');if(nextBtn)nextBtn.hidden=stage===3;
  telemetry.stageChanges++;
  if(doc.activeElement?.closest('.profile-panels3857, .profile-panel-actions3857')){
   try{host.scrollIntoView({behavior:'instant',block:'start'});}catch(_){}
  }
  return true;
 }
 const formerSubmit=submitProfile;
 submitProfile=function(event){
  const form=event?.target;
  if(form?.id==='profileForm'){
   const firstInvalid=[...form.elements].find(field=>
    typeof field.checkValidity==='function'&&!field.checkValidity());
   if(firstInvalid){
    event.preventDefault();
    const pane=Number(firstInvalid.closest('[data-profile-pane3857]')?.dataset.profilePane3857)||1;
    show(pane);telemetry.validationReveals++;
    for(let detail=firstInvalid.closest('details');detail;detail=detail.parentElement?.closest('details'))detail.open=true;
    firstInvalid.focus({preventScroll:true});
    firstInvalid.reportValidity();
    return false;
   }
  }
  return formerSubmit.apply(this,arguments);
 };
 const originalRender=render;
 render=function(){
  const outcome=originalRender.apply(this,arguments);
  if(doc.querySelector('#profileForm')){
   show(stage);telemetry.profiles++;
  }
  scheduleMedia();
  return outcome;
 };
 // Actualización selectiva: registra una dosis Monarca sin desmontar campos ni la escena.
 // Al iniciar, completar, cancelar o cambiar perfil, conserva el render completo del motor.
 function refreshMonarch(){
  const m=state.monarch3852?.active;
  const page=doc.querySelector('.scene[data-route3855="monarch"] .monarch-page3853');
  if(!page||!m||!root.HunterMonarch3852)return false;
  const inputs=[...page.querySelectorAll('input[id^="monarch-entry-"]')];
  if(inputs.length!==m.objectives.length)return false;
  const rows=root.HunterMonarch3852.progress(m,state.logs||{});
  if(rows.length!==inputs.length)return false;
  const byId=new Map(rows.map(o=>[o.id,o]));
  for(const input of inputs){
   const id=input.id.slice('monarch-entry-'.length),o=byId.get(id);
   if(!o)return false;
   const quest=input.closest('.quest'),small=quest?.querySelector('.grow > small');
   if(!small)return false;
   small.textContent=small.textContent.replace(/\d+\s*\/\s*\d+\s*(?:segundos|repeticiones)/,
    `${o.done}/${o.target} ${o.unit==='sec'?'segundos':'repeticiones'}`);
   const bar=quest.querySelector('.bar i');if(bar)bar.style.width=`${Math.max(0,Math.min(100,100*o.done/o.target))}%`;
  }
  const active=doc.activeElement;
  if(active?.tagName==='BUTTON'){
   const input=active.closest('.quest')?.querySelector('input[id^="monarch-entry-"]');
   if(input)input.value='1';
  }
  telemetry.partialMonarchUpdates++;
  return true;
 }
 const scrollPositions=Object.create(null);
 let mediaFrame=0;
 function deferOffscreenMedia(){
  mediaFrame=0;
  for(const img of doc.querySelectorAll('#app .scene img, #modal .demo-gallery img')){
   if(img.closest('.topbar,.monarch-entry3853')||img.getAttribute('loading'))continue;
   if(img.getBoundingClientRect().top>root.innerHeight+120){
    img.setAttribute('loading','lazy');img.setAttribute('decoding','async');telemetry.lazyImages++;
   }
  }
 }
 function scheduleMedia(){if(!mediaFrame)mediaFrame=root.requestAnimationFrame(deferOffscreenMedia);}
 const originalRefreshRoutines=refreshRoutines;
 refreshRoutines=function(){
  const container=doc.querySelector('#routineResults'),count=doc.querySelector('#routineCount');
  if(!container||!count)return originalRefreshRoutines.apply(this,arguments);
  const list=routineList();container.innerHTML=routineCards(list);
  count.textContent=list.length+' rutinas';telemetry.routineRefreshes++;
  scheduleMedia();
 };
 const originalGo=go;
 go=function(destination){
  const previous=tab;
  if(previous==='calendar'&&destination!=='calendar')scrollPositions.calendar=root.scrollY||0;
  if(destination==='profile'&&tab!=='profile')stage=1;
  if(destination==='monarch'&&tab==='monarch'){
   if(refreshMonarch())return;
   telemetry.monarchFallbacks++;
  }
  const outcome=originalGo.apply(this,arguments);
  scheduleMedia();
  if(destination==='calendar'&&previous!=='calendar'&&Number.isFinite(scrollPositions.calendar)){
   const pos=scrollPositions.calendar;
   root.requestAnimationFrame(()=>{if(tab==='calendar'){root.scrollTo(0,pos);telemetry.calendarRestores++;}});
  }
  return outcome;
 };
 function updateHome(){
  if(tab!=='home'||!state.profile)return;
  const scene=doc.querySelector('.scene[data-route3855="home"]');if(!scene)return;
  const totals=Engine.stats(state.logs||{});
  const key=[totals.streak,totals.days,totals.reps,totals.level,totals.xp].join('|');
  if(lastStatsKey!==key){
   const stats=scene.querySelectorAll('.dashboard26 .metrics .metric strong');
   if(stats.length>=3){
    [totals.streak,totals.days,totals.reps].forEach((value,i)=>{const text=String(value);if(stats[i].textContent!==text)stats[i].textContent=text;});
   }
   const rank=['E','D','C','B','A','S'][Math.min(5,Math.floor((totals.level-1)/5))];
   // Cambios de rango requieren la composición del personaje, no actualizar solo el número.
   if((scene.querySelector('.rank-pill')?.textContent||'').trim()===`RANGO ${rank}`){
    const hero=scene.querySelector('.hero-footer');
    const strong=hero?.querySelector('strong');
    if(strong?.firstChild?.nodeType===3)strong.firstChild.nodeValue=`NIVEL ${String(totals.level).padStart(2,'0')}`;
    const xp=hero?.querySelector('small');if(xp)xp.textContent=`${totals.xp%300} / 300 XP`;
    const bar=hero?.querySelector('.bar i');if(bar)bar.style.width=`${totals.xp%300/3}%`;
   }
   lastStatsKey=key;telemetry.partialHomeUpdates++;
  }
  const st=state.monarch3852||{},m=st.active;
  const progress=m&&root.HunterMonarch3852?root.HunterMonarch3852.progress(m,state.logs||{}):[];
  const key2=[m?.startedAt||0,m?.expiresAt||0,st.xp||0,
   state.profile?.difficulty||'',...progress.map(o=>o.done)].join('|');
  if(lastTileKey!==key2){
   const current=scene.querySelector('.monarch-entry3853');
   if(current&&root.HunterMonarchUI3853?.homeCard){
    const div=doc.createElement('div');div.innerHTML=root.HunterMonarchUI3853.homeCard();
    if(div.firstElementChild)current.replaceWith(div.firstElementChild);
    lastTileKey=key2;
   }
  }
 }
 function scheduleHome(){
  if(onFrame||tab!=='home')return;
  onFrame=true;root.requestAnimationFrame(()=>{onFrame=false;try{updateHome();}catch(error){console.warn('HunterPanels3857: parcial no aplicada',error);}});
 }
 const oldSave=save;
 save=function(){const result=oldSave.apply(this,arguments);if(result)scheduleHome();return result;};
 root.HunterPanels3857=Object.freeze({version:'38.57',show,stage:()=>stage,
  refreshHome:updateHome,refreshMonarch,diagnostics:()=>({...telemetry,tab,stage,
    activePane:doc.querySelector('.profile-v3')?.dataset.activePane3857||null})});
})(window,document);
