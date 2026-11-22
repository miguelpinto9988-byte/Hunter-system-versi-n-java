/* Hunter System V37.7 · Botón único para guardar e interpretar el perfil completo. */
(function(root){
'use strict';
const UI_NAME = 'Guardado de perfil creado';
const FIELDS = {
  identidad:['name','age','height','weight','gender'],
  metas:['goalGroup','finalTarget','finalGoal','goal','focus','difficulty','trainingScale'],
  calendario:['weekPlan','frequency','dailyMissions'],
  entrenamiento:['experience','equipment','load','bench','step','restSeconds','minutes'],
  modalidad:['trainingMode','preferredTrainer'],
  salud:['injuries','restrictions','recommendations','health','limits','goalAutoRestrictions374'],
  acompanante:['companionCharacter','companionStyle','animate'],
  apariencia:['theme']
};
const TITLES={identidad:'Datos personales',metas:'Objetivos e intensidad',calendario:'Días de fuerza, cardio, movilidad y descanso',entrenamiento:'Equipo y parámetros de entrenamiento',modalidad:'Rutinas y misiones',salud:'Lesiones, restricciones e indicaciones',acompanante:'Acompañante y animaciones',apariencia:'Colores, tema y presentación',otros:'Otros criterios del perfil'};
const ESC=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const STABLE=x=>JSON.stringify(x??null);
function digest377(input){
 const txt=String(input??'');let hash=2166136261;
 for(let i=0;i<txt.length;i++){hash^=txt.charCodeAt(i);hash=Math.imul(hash,16777619);}
 return ('00000000'+(hash>>>0).toString(16)).slice(-8);
}
const known = new Set(Object.values(FIELDS).flat());
const INTERNAL = new Set(['interpretation374','profileInterpretation377','profileRevision377']);
const WEEK = {train:'fuerza',cardio:'cardio',mobility:'movilidad',rest:'descanso'};
const NAMED = {basic:'Básico',intermediate:'Intermedio',advanced:'Avanzado'};
function diffProfile(before,after,oldLook,newLook){
 const prior=before||{},next=after||{},groups=[],keys=new Set([...Object.keys(prior),...Object.keys(next)]);
 for(const [group,fields]of Object.entries(FIELDS)){
  if(fields.some(k=>STABLE(prior[k])!==STABLE(next[k])))groups.push(group);
 }
 if([...keys].some(k=>!known.has(k)&&!INTERNAL.has(k)&&STABLE(prior[k])!==STABLE(next[k])))groups.push('otros');
 if(STABLE(oldLook)!==STABLE(newLook)&&!groups.includes('apariencia'))groups.push('apariencia');
 return groups;
}
function snapshot(p,appearance,avatar){
 // A fingerprint detects changed configuration; it is not a cryptographic credential.
 const cfg=JSON.stringify({profile:Object.fromEntries(Object.entries(p||{}).filter(([k])=>!INTERNAL.has(k))),appearance:appearance||null,photoFingerprint:digest377(avatar)});
 return digest377(cfg);
}
function weekSummary(p){
 const week=Array.isArray(p?.weekPlan)?p.weekPlan:[];
 const c={train:0,cardio:0,mobility:0,rest:0};for(const x of week)if(Object.prototype.hasOwnProperty.call(c,x))c[x]++;
 return `${c.train} fuerza · ${c.cardio} cardio · ${c.mobility} movilidad · ${c.rest} descanso`;
}
function reportFormNames(form){
 // Includes every named form input for diagnostics, without storing health text in a log.
 return [...new Set(Array.from(form.elements).map(e=>e.name).filter(Boolean))].sort();
}
const previousProfileForm = profileForm;
profileForm=function(initial=false){
 const doc=document.createElement('div');doc.innerHTML=previousProfileForm(initial);
 const form=doc.querySelector('#profileForm');if(!form)return doc.innerHTML;
 // Remove the separate interpretation action. The goal preview remains non-destructive.
 form.querySelectorAll('.goal-control374 button.goal-apply374, button[onclick*="interpretGoal("]').forEach(b=>b.remove());
 const note=form.querySelector('.goal-control374 > .footnote');
 if(note)note.textContent='La vista previa orienta tu objetivo. Al guardar el perfil se interpretan también tus días, equipo, preferencias, modalidades y restricciones declaradas.';
 // Replace the previous submit button at the END, not create a second action.
 const old=form.querySelector('button[type="submit"]');
 if(old){const button=document.createElement('button');button.type='submit';button.className='btn primary full profile-save377';button.textContent=UI_NAME;button.setAttribute('aria-label','Guardar el perfil e interpretar todos los criterios de entrenamiento');button.setAttribute('aria-describedby','profile-save-explain377');old.replaceWith(button);
  const explain=document.createElement('p');explain.id='profile-save-explain377';explain.className='footnote profile-save-help377';explain.textContent='Acción única: guardar datos, interpretar objetivos y revisar sesiones futuras compatibles. Se conservan los registros realizados.';button.after(explain);
 }
 return doc.innerHTML;
};
function dialog377({initial,groups,profile,goal,updated,validKeys,planStatus}){
 const labels=groups.length?groups.map(x=>TITLES[x]||x):['Sin cambios en los criterios'];
 const goals=Array.isArray(goal?.alternatives)&&goal.alternatives.length?goal.alternatives.map(k=>GoalGroups14.groups[k]?.name||k).join(' · '):(GoalPlans.plans[profile.finalTarget]?.name||'Objetivo seleccionado');
 const mode=profile.trainingMode==='routines'?'Rutinas completas':'Misiones diarias';
 const health=(profile.injuries||[]).some(x=>x.stage!=='past')||profile.limits||profile.health?.pain&&profile.health.pain!=='none';
 const status=planStatus==='reviewed'?'Se revisaron las sesiones futuras elegibles respetando los registros existentes.':planStatus==='restricted'?'Tu perfil está guardado. Según las limitaciones indicadas, puede requerirse revisión antes de proponer ejercicio automático.':'Perfil guardado. No se pudo comprobar la actualización inmediata del plan; revisa Misiones.';
 modal(`<section class="profile-confirm377" role="status" aria-live="polite">
 <span class="eyebrow">HUNTER SYSTEM · PERFIL UNIFICADO</span><h2>${initial?'Perfil creado y guardado':'Perfil actualizado y guardado'}</h2>
 <p>El acompañante revisó los criterios guardados de tu perfil mediante reglas locales.</p>
 <div class="profile-confirm-box377"><b>Preferencias analizadas</b><p>${ESC(labels.join(' · '))}</p><p><strong>Objetivo:</strong> ${ESC(goals)}</p><p><strong>Semana:</strong> ${ESC(weekSummary(profile))}</p><p><strong>Modalidad:</strong> ${ESC(mode)} · <strong>Nivel:</strong> ${ESC(NAMED[profile.difficulty]||profile.difficulty||'Básico')}</p><p><strong>Equipo:</strong> ${ESC(profile.equipment==='db'?'Mancuernas y peso corporal':'Peso corporal')} · <strong>Acompañante:</strong> ${ESC((typeof CompanionNames!=='undefined'&&CompanionNames[profile.companionCharacter])||profile.companionCharacter||'Elegido')}</p></div>
 <p class="notice">${ESC(status)}</p>
 ${health?'<p class="footnote">Hay condiciones o restricciones declaradas. Estas reglas no realizan una valoración médica ni verifican tu postura.</p>':''}
 <p class="footnote">${validKeys} campos del formulario fueron revisados para identificar los ajustes. Se comparó la configuración guardada. Los datos permanecen en el almacenamiento local de la aplicación.</p>
 <button class="btn primary full" type="button" onclick="closeActiveModal()">Continuar</button></section>`);
}
const previousSubmit=submitProfile;
submitProfile=function(event){
 const form=event?.target;
 if(!form||form.id!=='profileForm')return previousSubmit(event);
 // Do not save through the old interpretation button: submit is the single commit path.
 const before=state.profile,priorLook={appearance:state.appearance33,photo:digest377(state.avatar)};
 const wasInitial=!before;
 if(!form.reportValidity())return;
 let interp=null;
 try{interp=HunterImprove374.refresh(form,true);}catch(e){console.warn('Vista previa de objetivo no disponible',e);}
 const names=reportFormNames(form);
 try{previousSubmit(event);}catch(e){console.error('No se pudo guardar el perfil',e);toast('No fue posible guardar el perfil. Revisa los campos.');return;}
 if(!state.profile||state.profile===before)return; // Validation failed upstream.
 const after=state.profile,look={appearance:state.appearance33,photo:digest377(state.avatar)};
 const changed=diffProfile(before,after,priorLook,look);
 const fingerprint=snapshot(after,state.appearance33,state.avatar);
 const savedAt=new Date().toISOString();
 const previousConfirmation=state.profileSave377;
 state.profileSave377={schema:'37.7',savedAt,fingerprint,changedGroups:changed,inspectedFormFields:names.length};
 if(!save()){state.profileSave377=previousConfirmation;return;}
 let persisted=false;
 try{const onDisk=JSON.parse(localStorage.getItem(KEY));persisted=onDisk?.profileSave377?.fingerprint===fingerprint&&onDisk?.profile?.name===after.name;}catch(e){}
 if(!persisted){toast('No se pudo comprobar el guardado del perfil.');return;}
 let planStatus='not-checked';
 if(E.eligible(after)){
  try{currentCycle();planStatus='reviewed';}catch(e){console.warn('Revisión de sesiones pendientes',e);}
 }else planStatus='restricted';
 try{dialog377({initial:wasInitial,groups:changed,profile:after,goal:interp,updated:savedAt,validKeys:names.length,planStatus});}
 catch(e){console.warn('Confirmación visual',e);toast('Perfil guardado y preferencias analizadas.');}
};
const previousAnswer=answerCoach;
answerCoach=function(input){
 const q=String(input||'');if(state.profile&&/(?:guardar\s+perfil|actualizar\s+perfil|cambiar\s+objetivos|interpretaci[oó]n\s+del\s+perfil)/i.test(q)){
  return companionDisplayName()+': Tus objetivos, días, equipo, intensidad, modalidad, restricciones, apariencia y elección del acompañante se guardan juntos desde el botón «'+UI_NAME+'» al final del perfil. Los registros completados se mantienen.';
 }
 return previousAnswer(input);
};
root.HunterV377={version:'37.7',getFormFields:reportFormNames,changes:diffProfile,fingerprint:snapshot,summary:weekSummary};
try{render();}catch(e){console.warn('V37.7 render:',e);}
})(window);
