/* V38.46: optional capability/material configuration and lazily decoded 2D SVG.
   No timer, global animation loop or exercise asset is preloaded at startup. */
(function(root){'use strict';
const C=root.HunterCatalog46,E=root.Engine;if(!C)throw Error('Falta catálogo 38.46');
const esc=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const previousValidation=validateBackup;validateBackup=function(data){if(!previousValidation(data))return false;return [data.profile,...(data.cycles||[]).map(c=>c.profile)].filter(Boolean).every(p=>['materials46','capabilities46'].every(k=>p[k]===undefined||Array.isArray(p[k])&&p[k].every(x=>Object.prototype.hasOwnProperty.call(k==='materials46'?C.materials:C.capabilities,x))));};
if(typeof pendingRestore46!=='undefined'&&pendingRestore46&&validateBackup(pendingRestore46)){state=pendingRestore46;storageError=false;pendingRestore46=null;}
const oldProfile=profileForm;
profileForm=function(initial=false){const html=oldProfile.apply(this,arguments),holder=document.createElement('div');holder.innerHTML=html;const form=holder.querySelector('#profileForm');if(!form||form.querySelector('[data-catalog46]'))return html;
 const p=state?.profile||{},selected=new Set(Array.isArray(p.materials46)?p.materials46:[]),capabilities=new Set(Array.isArray(p.capabilities46)?p.capabilities46:[]);
 const materials=Object.entries(C.materials).filter(([k])=>!['db','bench','step','barbell','cable'].includes(k));
 const checks=(xs,name,set)=>xs.map(([key,label])=>`<label class="check46"><input type="checkbox" name="${name}" value="${esc(key)}" ${set.has(key)?'checked':''}><span>${esc(label)}</span></label>`).join('');
 const block=document.createElement('section');block.className='card catalog-profile46';block.dataset.catalog46='true';
 block.innerHTML=`<details><summary>Material adicional y capacidades <span aria-hidden="true">⌄</span></summary><p>Marca solo lo que tienes disponible y lo que puedes realizar con control. Puedes actualizarlo desde Perfil.</p><details><summary>Material disponible</summary><div class="check-grid46">${checks(materials,'materials46',selected)}</div></details><details><summary>Capacidades para movimientos complejos</summary><p class="footnote">Son datos declarados por ti, no una evaluación clínica. Elegir «Avanzado» no activa estas capacidades.</p><div class="check-grid46">${checks(Object.entries(C.capabilities),'capabilities46',capabilities)}</div></details></details>`;
 const step=form.querySelector('[data-profile-step="2"]')||form.querySelector('.profile-block:last-of-type')||form;step.append(block);return holder.innerHTML;};
if(typeof V4==='object'&&typeof V4.submitProfile==='function'){
 const before=V4.submitProfile;V4.submitProfile=function(ev){const form=ev?.target,old=state?.profile;
 const materials=form?[...form.querySelectorAll('input[name="materials46"]:checked')].map(x=>x.value):[],capabilities=form?[...form.querySelectorAll('input[name="capabilities46"]:checked')].map(x=>x.value):[];
 const answer=before.apply(this,arguments);
 if(form?.id==='profileForm'&&state?.profile&&state.profile!==old){state.profile.materials46=materials;state.profile.capabilities46=capabilities;if(typeof save==='function')save();}return answer;};
}
// Dose safeguards also apply to exercise-specific adaptive suggestions.
if(root.AdaptiveV9?.recommend){const before=root.AdaptiveV9.recommend;root.AdaptiveV9.recommend=function(logs,item,p,now){const e=E.byId(item.id),out=before.apply(this,arguments);if(!e?.doseCap46)return out;
 const result=C.cap(e,p,{...item,target:out.target},{feedback:{canProgress:out.action==='aumentar'},logs,date:now});return {...out,target:result.target,action:result.target>item.target?'aumentar':result.target<item.target?'reducir':'mantener',reason:out.reason+' Se respeta el límite técnico específico del ejercicio.'};};}
const originalDose=OwnRoutines18.dose;
OwnRoutines18.dose=function(e,p,kind){if(!e?.doseCap46)return originalDose.apply(this,arguments);const c=e.doseCap46;return C.cap(e,p,{id:e.id,sets:2,target:c.initial,rest:c.rest});};
const intensityDose=TrainingIntensity.dose;TrainingIntensity.dose=function(e,p,kind){return e?.doseCap46?OwnRoutines18.dose(e,p,kind):intensityDose.apply(this,arguments);};
if(typeof equipmentLabel23==='function'){const label=equipmentLabel23;equipmentLabel23=function(e){return e?.materials46?.length?e.materials46.map(x=>C.materials[x]).join(' + '):label(e);};}
const cache=new Map(),pending=new Map(),sessions=new WeakMap(),placeholder={frames:Array.from({length:48},()=>({p:Array.from({length:11},()=>[360,320]),loads:[],equipment:[],detail:{}})),specific30:true,view21:{primary:'front',label:'Frontal',reason:'Preparando la vista específica de este movimiento.'}};
function touch(id,value){cache.delete(id);cache.set(id,value);while(cache.size>3)cache.delete(cache.keys().next().value);return value;}
async function load(id){if(cache.has(id))return touch(id,cache.get(id));if(pending.has(id))return pending.get(id);
 const ex=E.byId(id);if(!C.owned(id)||!ex)return null;
 const promise=Promise.all([fetch(ex.modelPath30).then(r=>{if(!r.ok)throw Error('Modelo local no disponible');return r.json();}),fetch(ex.demoPath30).then(r=>{if(!r.ok)throw Error('SVG local no disponible');return r.text();})]).then(([model,svg])=>{
  if(model.id!==id||model.frames?.length!==48||!svg.startsWith('<svg'))throw Error('Demostración local incompleta: '+id);return touch(id,{model,svg});}).finally(()=>pending.delete(id));
 pending.set(id,promise);return promise;
}
const oldGet=Motion30.get,oldPreload=Motion30.preload;
Motion30.get=function(id){if(!C.owned(id))return oldGet.apply(this,arguments);const entry=cache.get(id);if(!entry)load(id).catch(()=>{});return {e:E.byId(id),base:id,model:entry?.model||{...placeholder,view21:E.byId(id)?.view21||placeholder.view21}};};
Motion30.preload=function(id){return C.owned(id)?load(id).then(x=>!!x):oldPreload.apply(this,arguments);};
if(root.Offline20)root.Offline20.count=()=>Engine.exercises.filter(e=>e.demoPath30&&e.modelPath30).length;
const beforeSVG=Motion2D18.svg,beforeLabel=Motion2D18.label;
Motion2D18.svg=function(id,phase,mirror){if(!C.owned(id))return beforeSVG.apply(this,arguments);const value=cache.get(id);if(!value)load(id).catch(()=>{});return value?.svg||`<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 720 610"><text x="360" y="300" text-anchor="middle" fill="#b7d5de">Preparando demostración sin conexión…</text></svg>`;};
Motion2D18.label=function(id,phase){if(!C.owned(id))return beforeLabel.apply(this,arguments);const model=cache.get(id)?.model;if(!model)return'Preparando demostración sin conexión';const n=model.phase46.length-1,index=Math.floor((((Number(phase)||0)%48+48)%48)/48*n);return model.phase46[index]||'';};
function attach(entry,asset){if(!entry.host.isConnected||entry.host.dataset.exercise!==entry.id)return;
 entry.host.innerHTML=asset.svg;const svg=entry.host.firstElementChild,detail=svg.querySelector('[data-detail-layout46]');svg.setAttribute('viewBox','0 0 720 610');
 const detailSVG=svg.cloneNode(false);detailSVG.classList.add('detail-svg31');detailSVG.setAttribute('viewBox','58 620 604 320');detailSVG.append(detail);entry.host.append(detailSVG);entry.host.dataset.hasDetail31='true';
 entry.svgs=[svg,detailSVG];entry.duration=asset.model.duration46;entry.asset=asset;
 for(const s of entry.svgs)s.pauseAnimations();seek(entry,entry.phase,entry.mirror);
 // The asynchronous animation was loaded: refresh the phase label immediately.
 const phaseLabel=document.getElementById('vectorPhase');
 if(phaseLabel && typeof vectorSession!=='undefined' && vectorSession?.id===entry.id
   && entry.host.closest('#modal'))phaseLabel.textContent=Motion2D18.label(entry.id,entry.phase);
}
function seek(entry,phase,mirror){if(!entry.svgs)return;const time=(((Number(phase)||0)%48+48)%48)/48*entry.duration;
 for(const svg of entry.svgs)svg.setCurrentTime(time);
 if(entry.lastMirror!==!!mirror){entry.lastMirror=!!mirror;entry.host.querySelector('[data-body46]')?.setAttribute('transform',mirror?'translate(720 0) scale(-1 1)':'');entry.host.querySelector('[data-detail-content46]')?.setAttribute('transform',mirror?'translate(720 0) scale(-1 1)':'');}
}
const oldDraw=drawVector;
drawVector=function(canvas,id,phase=0,mirror=false){if(!C.owned(id)){const host=canvas?.nextElementSibling;if(host?.classList.contains('catalog-motion46')){host.classList.remove('catalog-motion46');host.replaceChildren();sessions.delete(canvas);}return oldDraw.apply(this,arguments);}if(!canvas)return;
 let entry=sessions.get(canvas);if(!entry||entry.id!==id||canvas.nextElementSibling!==entry.host){
  let host=canvas.nextElementSibling;if(!host?.classList.contains('motion-svg18')){host=document.createElement('div');host.className='motion-svg18';canvas.after(host);}host.classList.add('catalog-motion46');host.dataset.exercise=id;canvas.hidden=true;canvas.dataset.renderer='2d';
  entry={id,host,phase,mirror};sessions.set(canvas,entry);const asset=cache.get(id);
  if(asset)attach(entry,asset);else{host.innerHTML='<p class="footnote">Preparando demostración sin conexión…</p>';load(id).then(value=>attach(entry,value)).catch(err=>{if(host.isConnected){host.replaceChildren();const p=document.createElement('p');p.textContent=err.message;host.append(p);}});}
 }
 entry.phase=phase;entry.mirror=mirror;seek(entry,phase,mirror);return entry.asset?.model||placeholder;
};
root.HunterDemos46=Object.freeze({load,draw:drawVector,anatomicalSvg:(id,phase=0,mirror=false)=>C.owned(id)&&cache.has(id)?beforeSVG(id,phase,mirror):null,cacheStats:()=>({decoded:cache.size,pending:pending.size,maximum:3}),clear:()=>cache.clear()});
})(window);
// References are read only when the existing knowledge window is requested.
(function(){'use strict';if(typeof openBodyKnowledge!=='function')return;const before=openBodyKnowledge;openBodyKnowledge=function(){const result=before.apply(this,arguments);const host=document.querySelector('#modal');if(!host)return result;const section=document.createElement('details');section.className='card';section.innerHTML='<summary>Fuentes y alcance del catálogo ampliado</summary><div class="references46">Cargando referencias locales…</div>';host.append(section);section.addEventListener('toggle',async()=>{if(!section.open||section.dataset.loaded)return;const box=section.querySelector('.references46');try{const response=await fetch('catalog46/sources.json');if(!response.ok)throw Error();const data=await response.json();box.replaceChildren();for(const source of data.sources){const p=document.createElement('p');if(source.url){const a=document.createElement('a');a.href=source.url;a.target='_blank';a.rel='noopener';a.textContent=source.title;p.append(a);}else{const b=document.createElement('strong');b.textContent=source.title;p.append(b);}p.append(document.createElement('br'),document.createTextNode(source.scope));box.append(p);}const p=document.createElement('p');p.textContent=data.limits;box.append(p);section.dataset.loaded='true';}catch{box.textContent='No se pudieron abrir las referencias locales.';}},{passive:true});return result;};})();

;
/* Hunter System 38.49: shared weekly group routing across legacy entry points.
 * The common generator retains goal, equipment, capacity and clinical filters.
 * Existing exercises, animations, completed logs and manual workouts are retained.
 */
(function(root){'use strict';
 if(root.HunterWeek3847)return;
 const E=root.Engine,S=root.Schedule,D=root.HunterDynamic381;
 const central=e=>Boolean(e?.category==='Fuerza'&&['Core','Abdomen'].includes(e.group));
 function type(p,date=E.dateKey()){
  if(!p||!S.valid(p))return null;const i=S.weekday(date),day=p.weekPlan[i];
  return day==='train'?(['lower','core'].includes(p.weekFocus?.[i])?p.weekFocus[i]:'upper'):day;
 }
 function isScheduledKind(p,kind,date){const t=type(p,date);return kind===({upper:'strength',lower:'strength',core:'strength',cardio:'cardio',mobility:'recovery'}[t]);}
 function scheduledPlan(p,kind,seed,logs={},date=E.dateKey()){
  return D.choose(p,kind,'week47|'+seed,logs,date);
 }
 // Compatibility entry points used by older coach previews and schedule callers.
 // Their private historical planners still run inside the existing cycle pipeline;
 // Dynamic381 and Strict3838 perform final per-day reconciliation there.
 for(const object of [root.Training30,root.Training23,S]){
  const name=object===S?'items':'choose',before=object?.[name];if(typeof before!=='function')continue;
  object[name]=function(p,kind,rotation=0){
   if(isScheduledKind(p,kind,E.dateKey()))return scheduledPlan(p,kind,'legacy|'+rotation,typeof state!=='undefined'?state.logs:{},E.dateKey()).items;
   return before.apply(this,arguments);
  };
 }
 for(const [object,oldKind]of [[root.HunterPlanner379,false],[root.HunterInjuryPlanner378,true]]){
  if(typeof object?.choose!=='function')continue;const before=object.choose;
  object.choose=function(p,kind,rotation=0,logs={},date=E.dateKey()){
   if(!isScheduledKind(p,kind,date))return before.apply(this,arguments);
   const result=scheduledPlan(p,kind,'restricted|'+rotation,logs,date);
   return oldKind?{...result,kind:result.effective||kind,originalKind:kind}:result;
  };
 }
 const previousValidation=validateBackup;
 validateBackup=function(data){
  if(!previousValidation(data))return false;
  return [data.profile,...(data.cycles||[]).map(c=>c.profile)].filter(Boolean).every(p=>p.weekFocus===undefined||
   Array.isArray(p.weekFocus)&&p.weekFocus.length===7&&p.weekFocus.every(x=>['none','upper','lower','core'].includes(x)));
 };
 root.HunterWeek3847=Object.freeze({version:'38.47',type,isCentral:central,
  pool:p=>D.pool(p,'strength').filter(central),
  choices:['rest','upper','lower','core','cardio','mobility']});
})(window);

;
/* Hunter System 38.53 — final home + navigation integration.
 * Loaded after legacy home/render replacements and before the startup finalizer.
 * The mission engine remains independent from the daily routine engine.
 */
(function(root, doc){
 'use strict';
 if(root.HunterMonarchUI3853)return;
 if(!root.HunterMonarch3852||typeof home!=='function'||typeof render!=='function'||typeof panelTabs!=='function'){
   throw Error('HunterMonarchUI3853: faltan el motor o el Panel principal');
 }
 const originalHome=home, originalRender=render, originalTabs=panelTabs;
 const esc=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const currentStatus=()=>{
   root.HunterMonarch3852.review();
   const store=state?.monarch3852;
   const active=store?.active;
   const rows=active?root.HunterMonarch3852.progress(active,state.logs||{}):[];
   const finished=rows.filter(x=>x.done>=x.target).length;
   return {active,finished,total:5,xp:Math.max(0,Number(store?.xp)||0)};
 };
 const homeCard=()=>{
   const s=currentStatus();
   const isActive=!!s.active;
   const pct=isActive?Math.max(0,Math.min(100,Math.round(s.finished/5*100))):0;
   const days=isActive?Math.max(0,Math.ceil((s.active.expiresAt-Date.now())/86400000)):5;
   const difficulty=({basic:'BÁSICO',intermediate:'INTERMEDIO',advanced:'AVANZADO'})[state.profile?.difficulty]||'SEGÚN PERFIL';
   return `<section class="monarch-entry3853" aria-labelledby="monarch-entry-title3853">
    <div class="monarch-entry3853__symbol" aria-hidden="true">♛</div>
    <div class="monarch-entry3853__info">
      <div class="monarch-entry3853__eyebrow">DESAFÍO ESPECIAL <span>· CICLO DE 5 DÍAS</span></div>
      <h2 id="monarch-entry-title3853">MISIONES <strong>MONARCA</strong></h2>
      <p>${isActive?'Tu desafío está en marcha. Los cinco objetivos se acumulan durante el ciclo.':'Cinco pruebas de fuerza y resistencia adaptadas a tu nivel. Cada avance cuenta durante cinco días.'}</p>
      <div class="monarch-entry3853__tags"><span>NIVEL ${esc(difficulty)}</span><span>${isActive?days+' DÍAS RESTANTES':'5 OBJETIVOS'}</span><span>${s.xp} XP MONARCA</span></div>
    </div>
    <div class="monarch-entry3853__action">
      ${isActive?`<div class="monarch-entry3853__meter" role="progressbar" aria-label="Objetivos Monarca completados" aria-valuemin="0" aria-valuemax="5" aria-valuenow="${s.finished}"><i style="width:${pct}%"></i></div><small>${s.finished} / 5 completados</small>`:''}
      <button type="button" class="monarch-entry3853__button" onclick="go('monarch')">${isActive?'CONTINUAR MISIÓN':'ABRIR MISIONES'} <span aria-hidden="true">→</span></button>
    </div>
   </section>`;
 };
 // The last home() definition (V3 and later upgrades) is the source of the user's Panel.
 home=function(){
   const markup=originalHome.apply(this,arguments);
   if(!state.profile||typeof markup!=='string'||markup.includes('monarch-entry3853'))return markup;
   return homeCard()+markup;
 };
 panelTabs=function(){
   const markup=originalTabs.apply(this,arguments);
   if(!markup.includes('segmented')||markup.includes('data-monarch-tab3853'))return markup;
   return markup.replace(/<\/div>\s*$/,`<button data-monarch-tab3853="1" class="${tab==='monarch'?'selected':''}" onclick="go('monarch')" aria-label="Ir a Misiones Monarca">♛ Monarca</button></div>`);
 };
 // V2 replaced the base render() and does not know about the 'monarch' route.
 // Reuse its actual header, bottom navigation and layout before replacing the scene.
 render=function(){
   if(tab!=='monarch')return originalRender.apply(this,arguments);
   if(!state.profile){tab='profile';return originalRender.apply(this,arguments);}
   let result;
   tab='home';
   try{result=originalRender.apply(this,arguments);}
   finally{tab='monarch';}
   const scene=doc.querySelector('#app .scene');
   if(!scene){
     throw Error('HunterMonarchUI3853: no se encontró el contenedor de la pantalla');
   }
   scene.dataset.route3855='monarch';scene.innerHTML=`<div class="monarch-page3853"><button type="button" class="monarch-page3853__back" onclick="go('home')">← VOLVER AL PANEL</button>${root.HunterMonarch3852.view()}</div>`;
   const tabs=doc.querySelector('#app .segmented');
   if(tabs){
     tabs.querySelectorAll('button').forEach(btn=>{const selected=btn.hasAttribute('data-monarch-tab3853');btn.classList.toggle('selected',selected);if(selected)btn.setAttribute('aria-current','page');else btn.removeAttribute('aria-current');});
   }
   const navHome=doc.querySelector('#app nav.nav button[onclick="go(\'home\')"]');
   if(navHome)navHome.classList.add('active');
   return result;
 };
 root.HunterMonarchUI3853=Object.freeze({version:'38.54',homeCard,diagnostics:()=>({tab,cardPresent:!!doc.querySelector('.monarch-entry3853'),viewPresent:!!doc.querySelector('.monarch-page3853'),tabPresent:!!doc.querySelector('[data-monarch-tab3853]')})});
})(window,document);

;
/* Hunter System V38.55: separate calendar route, shared stable shell diagnostics.
   The avatar in the upper right is the only permanent Profile navigation entry.
   Never caches forms or copies training logs across screens. */
(function(root,doc){'use strict';
 if(root.HunterRoute3855)return;
 const escText=value=>String(value??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 function calendarView(){
   const cycle=currentCycle();
   if(!cycle)return blocked();
   const today=E.dateKey();
   const upcoming=(cycle.days||[]).filter(d=>d.date>=today).slice(0,7);
   return `<section class="hunter-calendar3855" aria-labelledby="hunter-calendar-title3855" data-screen3855="calendar">
     <span class="eyebrow">PLANIFICACIÓN INDEPENDIENTE</span>
     <h1 id="hunter-calendar-title3855">Calendario</h1>
     <p class="muted">Revisa tus próximos entrenamientos sin salir de esta pantalla. Tus sesiones registradas se conservan.</p>
     <div class="card hunter-calendar3855__main">
       <div class="row between"><h2>Ciclo ${cycle.index+1}</h2><span class="badge">${escText(cycle.start)} · 7 días visibles</span></div>
       ${calendar(cycle)}
     </div>
     <div class="section-title"><h2>Próximas sesiones</h2></div>
     <div class="card">${upcoming.length?upcoming.map(d=>`<div class="quest"><div class="glyph">${escText(d.date.slice(8))}</div><div class="grow"><h3>${escText(kindLabel(d.kind))}</h3><small>${escText(d.date)}</small></div><button class="btn ghost" type="button" aria-label="Ver sesión del ${escText(d.date)}" onclick="dayDetail('${d.date}')">Ver →</button></div>`).join(''):'<p class="muted">No quedan sesiones pendientes en este ciclo.</p>'}</div>
   </section>`;
 }
 const entry={version:'38.56',calendarView,diagnostics:()=>({tab,route:doc.querySelector('#app main .scene')?.getAttribute('data-route3855')||null,shellExists:!!doc.querySelector('#app > main.app'),navItems:[...doc.querySelectorAll('#app > nav.nav button[data-route3855]')].map(x=>x.dataset.route3855),profileAvatar:!!doc.querySelector('.topbar .avatar-button'),fullMounts:typeof shell3855==='object'?shell3855.fullMounts:0,sceneReplacements:typeof shell3855==='object'?shell3855.sceneReplacements:0,headerReplacements:typeof shell3855==='object'?shell3855.headerReplacements:0,navReplacements:typeof shell3855==='object'?shell3855.navReplacements:0})};
 root.HunterRoute3855=Object.freeze(entry);
})(window,document);

;
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

;
/* Hunter System 38.48: measured work reduction, unchanged exercise motions.
 * Runtime counters are local diagnostics, not Android power/FPS measurements.
 */
(function(root,doc){'use strict';
 if(root.HunterOptimize48)return;
 const stats={minuteQueries:0,minuteFallbacks:0,coalescedLayouts:0,coalescedThemes:0,renders:0,offscreenPauses:0};
 const decorations=new Set();
 const decorationObserver=typeof IntersectionObserver==='function'?new IntersectionObserver(rows=>{
  for(const row of rows){const pause=!row.isIntersecting;row.target.classList.toggle('hunter-offscreen48',pause);if(pause)stats.offscreenPauses++;}
 },{rootMargin:'100px'}):null;
 function syncDecorations(){
  if(!decorationObserver)return;
  for(const node of decorations)if(!node.isConnected){decorationObserver.unobserve(node);decorations.delete(node);}
  for(const node of doc.querySelectorAll('#app .monarch-hero33'))if(!decorations.has(node)){decorations.add(node);decorationObserver.observe(node);}
 }
 let renderDepth=0,pendingSelection=null,lastTheme=null;
 const legacyLayout=Evolution28.layout,legacyTheme=applyTheme,legacyRender=render;
 Evolution28.layout=function(selection=this.current||this.selection()){
  if(renderDepth){this.current=selection;pendingSelection=selection;stats.coalescedLayouts++;return;}
  return legacyLayout.call(this,selection);
 };
 applyTheme=function(p=state.profile||{},prefs=Visual33.preferences()){
  const key=renderDepth?JSON.stringify([p,prefs]):null;
  if(key&&key===lastTheme){stats.coalescedThemes++;return;}
  if(renderDepth)lastTheme=key;
  return legacyTheme.apply(this,arguments);
 };
 render=function(){
  if(Runtime20.initializing)return;
  if(!renderDepth){lastTheme=null;pendingSelection=null;}
  renderDepth++;
  try{return legacyRender.apply(this,arguments);}
  finally{
   if(!--renderDepth){
    if(pendingSelection){const selection=pendingSelection;pendingSelection=null;legacyLayout.call(Evolution28,selection);}
    syncDecorations();
    stats.renders++;
   }
  }
 };

 // Duration is an aggregate, not a reason to allocate every session block and
 // every set. Keep the legacy expander for source sequences and malformed data.
 const legacyMinutes=Warm25.minutes;
 Warm25.minutes=function(r,p){
  stats.minuteQueries++;
  if(r.sourceSequence||!Array.isArray(r.items)||r.items.some(x=>!Number.isInteger(x.sets)||x.sets<1||!Engine.byId(x.id))){stats.minuteFallbacks++;return legacyMinutes.apply(this,arguments);}
  if(!r.items.length)return 0;
  let seconds=0,sets=0,rounds=0;
  for(const x of r.items){
   sets+=x.sets;rounds=Math.max(rounds,x.sets);
   seconds+=x.sets*(Engine.unitOf(x)==='sec'?x.target:Math.ceil(OwnRoutines18.workSeconds(x)));
  }
  seconds+=Math.max(0,(BlockSession22.mode(p)==='rounds'?rounds:sets)-1)*TrainingProgression19.rest(p||{});
  const warm=Warm24.select({title:r.name,items:r.items,sourceSequence:r.sourceSequence},p||{});
  seconds+=warm&&!warm.blocked?Warm24.step(warm.warm,'preparation',r.items.length).seconds+Warm24.step(warm.cool,'cooldown',r.items.length).seconds:warm?.blocked?60:120;
  return Math.ceil(seconds/60);
 };

 // Runtime373 already implements ambient scroll motion with compositor layers;
 // its old per-scroll numeric impulse is an empty compatibility handler.
 doc.removeEventListener('scroll',ambientScroll3817,true);
 if(scrollFrame3817)cancelAnimationFrame(scrollFrame3817);
 scrollFrame3817=pendingScroll3817=0;

 root.HunterOptimize48=Object.freeze({version:'38.48',compositedAmbient:true,
  legacyMinutes,snapshot:()=>({...stats,observedDecorations:decorations.size,svg:root.SvgRenderer373?.snapshot48?.(),startup:Runtime20.startup37})});

 // All migrations, 1,200 records, safety filters and final UI hooks now exist.
 // Earlier version modules may initialize data but do not build hidden screens.
 Runtime20.initializing=false;
 state.appVersion=38.76;
 applyTheme();render();save();Boot37.commit();
 if(doc.readyState!=='loading')Boot37.complete();
})(window,document);

