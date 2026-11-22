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
