/* Hunter System V37.5 · Open-ended, explicit animation cycle timing. */
(function(root){'use strict';
 const clamp=(n,a,b)=>Math.max(a,Math.min(b,n));
 function duration(id){const sec=Number(root.E?.byId(id)?.tempoSeconds18);return Number.isFinite(sec)&&sec>=.1&&sec<=120?sec:4;}
 function at(startPhase,startTime,now,seconds,rate){
  if(![startPhase,startTime,now,seconds,rate].every(Number.isFinite)||seconds<=0)return 0;
  return ((startPhase+(Math.max(0,now-startTime)/1000)*48/seconds*rate)%48+48)%48;
 }
 function format(sec){return Number(sec.toFixed(2)).toLocaleString('es-PE',{maximumFractionDigits:2})+' s';}
 const timing={duration,at,format};root.HunterMotion375=timing;
 // DemoLoop373 previously capped elapsed time at 100ms per frame; on slower
 // tablets that stretched a "3 s" repetition. Clock elapsed uses real active time.
 const loop=root.DemoLoop373; // top-level 'const DemoLoop373' is also window-visible in this runtime.
 if(typeof DemoLoop373!=='undefined'){
  const engine=DemoLoop373,olderSync=engine.sync.bind(engine);
   engine.sync=function(){if(this.entry&&(root.HunterV373?.runtime.hidden||!this.entry.visible))this.entry.clock375=null;return olderSync();};
  engine.tick=function(now){
   this.frame=0;const entry=this.entry;
   if(!this.valid()){this.stop();return;}
   if(root.HunterV373?.runtime.hidden||!entry.visible){entry.clock375=null;return;}
   if(entry.kind==='vector'){
    const s=vectorSession,seconds=duration(s.id),rate=s.speed;
    if(!entry.clock375||entry.clock375.seconds!==seconds||entry.clock375.rate!==rate){entry.clock375={time:now,phase:s.frame,seconds,rate};}
    s.frame=at(entry.clock375.phase,entry.clock375.time,now,seconds,rate);
    s.last=now;vectorPaint();
   }else{
    const g=guide11,step=g.steps[g.index],seconds=duration(step.id),rate=MotionPlayback.rate;
    if(!entry.clock375||entry.clock375.seconds!==seconds||entry.clock375.rate!==rate||entry.clock375.id!==step.id){entry.clock375={time:now,phase:g.demoFrame,seconds,rate,id:step.id};}
    g.demoFrame=at(entry.clock375.phase,entry.clock375.time,now,seconds,rate);
    g.demoLast=now;drawVector(entry.canvas,step.id,g.demoFrame,step.side==='left');
   }
   this.draws++;this.sync();
  };
 }
 // Cycle durations still drive the animation clock, but the separate
 // "segundos por ciclo" badges are deliberately removed from ALL demos.
 // Keep HunterMotion375.duration/at available to the existing controllers.
})(window);

;
/* Hunter System V37.6 · calendario explícito, simplificación del perfil, seguimiento basado en registros. */
(function(root){
'use strict';
const TYPES=['rest','train','cardio','mobility'];
const LABELS={rest:'Descanso',train:'Fuerza',cardio:'Cardio',mobility:'Movilidad'};
const SHORT={rest:'DESC',train:'FUERZA',cardio:'CARDIO',mobility:'MOVIL'};
const ORDER=[1,2,3,4,5,6,0];
const DAYS=['D','L','M','X','J','V','S'];
const escapeHTML=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const calendar=p=>Schedule.defaults(p||{}).map(x=>TYPES.includes(x)?x:'rest');
const isActive=l=>!!l&&(l.complete||l.pain||l.manual||l.items?.some(x=>x.missionState||x.selfReported||x.actual?.some(a=>a.done)));
const canAuto=p=>!!p&&Adapt.mode(GoalPlans.adjusted(p)).auto;
function countPlan(week){const n={train:0,cardio:0,mobility:0,rest:0};for(const type of week)n[type]++;return n;}
function dayPreview376(form){
 const week=Array.from({length:7},(_,n)=>form?.elements?.['day'+n]?.value||'rest'),count=countPlan(week),out=form?.querySelector('#day-summary376');
 if(!out)return;
 const backToBack=ORDER.some((day,i)=>week[day]==='train'&&week[ORDER[(i+1)%7]]==='train');
 out.textContent=count.train+' fuerza · '+count.cardio+' cardio · '+count.mobility+' movilidad · '+count.rest+' descanso.'+(backToBack?' Hay fuerza en días consecutivos: se reducirá el volumen de la segunda sesión.':'');
}
function swapDay376(button){
 if(!button?.form)return;
 const day=Number(button.dataset.day),field=button.form.elements['day'+day];if(!field)return;
 const old=TYPES.includes(field.value)?field.value:'rest',next=TYPES[(TYPES.indexOf(old)+1)%TYPES.length];
 field.value=next;button.dataset.kind=next;button.setAttribute('aria-label',Schedule.dayNames[day]+': '+LABELS[next]+'. Pulsar para cambiar el tipo de día');
 button.setAttribute('aria-pressed',String(next!=='rest'));
 button.querySelector('.day-type376').textContent=SHORT[next];
 dayPreview376(button.form);
}
root.swapDay376=swapDay376;
const earlierProfile=profileForm;
profileForm=function(initial=false){
 const doc=document.createElement('div');doc.innerHTML=earlierProfile(initial);
 const form=doc.querySelector('#profileForm');if(!form)return doc.innerHTML;
 // Botón duplicado agregado por v8; conservar únicamente el botón de interpretación y guardado de v37.4.
 for(const button of form.querySelectorAll('button[onclick*="interpretGoal("]'))button.remove();
 const legacyExplanation=form.querySelector('#intentResult');if(legacyExplanation){legacyExplanation.hidden=true;legacyExplanation.setAttribute('aria-hidden','true');}
 const run=form.querySelector('.goal-control374 .goal-apply374');if(run){run.textContent='✦ Interpretar y guardar objetivos';run.setAttribute('aria-label','Interpretar y guardar tus objetivos con el acompañante');}
 const msg=form.querySelector('.goal-control374 > .footnote');if(msg)msg.textContent='Puedes modificar tu meta. Al interpretar, se guardan los cambios y se actualizan las sesiones pendientes compatibles.';
 const editor=form.querySelector('.week-editor');
 if(editor){
  const week=calendar(state.profile),cards=ORDER.map(day=>{
   const t=week[day];return '<div class="day-item376"><button class="day-choice376" type="button" data-day="'+day+'" data-kind="'+t+'" aria-label="'+Schedule.dayNames[day]+': '+LABELS[t]+'. Pulsar para cambiar el tipo de día" aria-pressed="'+String(t!=='rest')+'" onclick="swapDay376(this)"><span class="day-letter376">'+DAYS[day]+'</span><small class="day-type376">'+SHORT[t]+'</small></button><input name="day'+day+'" value="'+t+'" type="hidden"></div>';
  }).join('');
  editor.innerHTML='<div class="day-guide376"><b>Tu semana de entrenamiento</b><p>Toca cada día para alternar entre <strong>Descanso → Fuerza → Cardio → Movilidad</strong>.</p></div><div class="day-week376" role="group" aria-label="Días y tipos de actividad">'+cards+'</div><div id="day-summary376" class="day-summary376" role="status" aria-live="polite"></div><p class="footnote">Fuerza y cardio se asignan en los días elegidos. Los registros ya realizados no se modifican. La movilidad es opcional.</p>';
  const parent=editor.closest('.week-block');
  if(parent){parent.querySelector('h3')?.replaceChildren(document.createTextNode('¿Qué quieres entrenar cada día?'));
   for(const note of parent.querySelectorAll(':scope > .footnote'))if(/días consecutivos|alternan fuerza|semana nueva/i.test(note.textContent||''))note.remove();
   const desc=parent.querySelector(':scope > .muted');if(desc)desc.textContent='Organiza fuerza, cardio, movilidad y descanso según tus preferencias.';
  }
  dayPreview376(form);
 }
 return doc.innerHTML;
};
// Alinea únicamente sesiones NO personalizadas y sin registros a la categoría elegida.
const oldMake=Engine.makeCycle;
function adaptDay376(d,p,rotation,priorStrength,logs,now){
 const choice=calendar(p)[Schedule.weekday(d.date)];if(d.date<now||d.customized||d.routineId||isActive(logs[d.date])||d.tasks?.some(t=>t.done))return false;
 const kind={rest:'rest',train:'strength',cardio:'cardio',mobility:'recovery'}[choice];let changed=false;
 if(choice==='rest'){
  if(d.kind!=='rest'||d.items?.length){d.kind='rest';d.items=[];d.tasks=[{id:'rest',text:'Respetar mi día de descanso',done:false}];d.title='Descanso elegido';changed=true;}
 }else if(canAuto(p)&&d.kind!==kind){
  const planned=Training30.choose(p,kind,rotation).filter(x=>Adapt.allowed(Engine.byId(x.id),GoalPlans.adjusted(p)));
  if(planned.length){d.kind=kind;d.items=planned;d.tasks=[];d.title=LABELS[choice]+' · sesión personalizada';delete d.autoTemplateId;changed=true;}
  else if(!d.items?.length){d.kind='rest';d.title='Revisar opciones compatibles';d.tasks=[{id:'review',text:'No se hallaron ejercicios compatibles de '+LABELS[choice].toLowerCase()+'. Revisa el perfil y las restricciones.',done:false}];}
 }
 if(d.kind==='strength'&&priorStrength&&d.items?.length&&!d.loadReduced376){
  // No modificar el tipo elegido por el usuario. Limitar volumen del segundo día consecutivo.
  d.items=d.items.map(x=>({...x,sets:Math.max(1,Math.floor(x.sets*.75)),rest:Math.max(Number(x.rest)||60,90)}));
  d.planningNote=(d.planningNote||'')+' Fuerza consecutiva: volumen de esta sesión reducido para favorecer la recuperación.';
  d.loadReduced376=true;changed=true;
 }
 if(changed)d.schedulePolicy376=376;
 return changed;
}
function harmonize376(c,p,logs={},today=Engine.dateKey(),last=null){
 if(!c?.days?.length||!Schedule.valid(p)||!canAuto(p)||c.reviews?.some(r=>r.action==='pausar'))return 0;
 let changed=0,prev=last?.days?.at(-1)?.kind==='strength'&&Engine.diff(c.days[0].date,last.days.at(-1).date)===1;
 c.days.forEach((d,i)=>{
  const was=prev,oldKind=d.kind;
  if(adaptDay376(d,p,c.index*7+i,was,logs,today))changed++;
  prev=d.kind==='strength';
 });
 return changed;
}
Engine.makeCycle=function(p,start,index,last,logs={}){
 const c=oldMake(p,start,index,last,logs);harmonize376(c,p,logs,start,last);return c;
};
const oldCurrent=currentCycle;
currentCycle=function(){
 const c=oldCurrent();if(!c||!state.profile)return c;
 const last=state.cycles.filter(x=>x.start<c.start).at(-1);
 if(harmonize376(c,state.profile,state.logs,Engine.dateKey(),last))save();
 return c;
};
function reviewData376(p=state.profile){
 if(!p)return null;
 const now=Engine.dateKey(),week=calendar(p),stats=countPlan(week);
 const last7=Array.from({length:7},(_,i)=>Engine.plus(now,i-6));
 const logs=last7.map(date=>({date,log:state.logs?.[date]})).filter(x=>x.log);
 const completed=logs.filter(x=>x.log.complete&&!x.log.pain);
 const effort=completed.map(x=>x.log.rpe).filter(x=>Number.isFinite(x)&&x>=1&&x<=10);
 const rpe=effort.length?Number((effort.reduce((a,b)=>a+b,0)/effort.length).toFixed(1)):null;
 const pain=logs.some(x=>x.log.pain),fatigue=logs.some(x=>x.log.fatigue||x.log.rpe>=8),counts={train:0,cardio:0,mobility:0};
 for(const x of completed){const k=x.log.kind==='strength'||x.log.kind==='mixed'?'train':x.log.kind==='cardio'?'cardio':x.log.kind==='recovery'?'mobility':null;if(k)counts[k]++;}
 const planned=last7.filter(date=>week[Schedule.weekday(date)]!=='rest').length;
 const adherence=planned?Math.round(100*completed.filter(x=>week[Schedule.weekday(x.date)]!=='rest').length/planned):null;
 const review=state.cycles?.filter(c=>Array.isArray(c.reviews)&&c.reviews.length).flatMap(c=>c.reviews.map(r=>({...r,start:c.start}))).at(-1);
 return {stats,counts,rpe,pain,fatigue,adherence,review,completed:completed.length};
}
function message376(){const s=reviewData376();if(!s)return 'Configura primero tu perfil.';
 let reason='Aún no hay suficientes registros comparables para aumentar la dosis de forma automática.';
 if(s.pain)reason='Se registró dolor. El progreso se mantiene en pausa y conviene revisar las indicaciones antes de retomar el esfuerzo.';
 else if(s.fatigue)reason='Se registró fatiga o esfuerzo alto (8/10 o más). La revisión del plan prioriza recuperar la tolerancia.';
 else if(s.review){reason='Última revisión semanal: '+s.review.action+'. '+s.review.reason;}
 else if(s.completed>=3)reason='Se dispone de actividad registrada. La progresión semanal compara ejercicios, series completas y esfuerzo; no aumenta los kg de forma automática.';
 const avg=s.rpe===null?'sin esfuerzo medio registrado':('esfuerzo medio '+s.rpe+'/10');
 return 'Calendario: '+s.stats.train+' días de fuerza, '+s.stats.cardio+' de cardio, '+s.stats.mobility+' de movilidad y '+s.stats.rest+' de descanso. Últimos 7 días: '+s.counts.train+' sesiones de fuerza y '+s.counts.cardio+' de cardio finalizadas, '+avg+'. '+reason+' Se conservan los entrenamientos ya realizados y las sesiones personalizadas.';
}
const earlierAnswer=answerCoach;
answerCoach=function(question){
 const q=String(question||'');
 if(/(?:progres(?:o|ión)|adaptaci[oó]n|rendimiento|fatiga|esfuerzo|calendario|semana|descanso|d[ií]as de (?:fuerza|cardio)|por qu[eé] (?:no|aument))/i.test(q)&&state.profile)
  return companionDisplayName()+': '+message376();
 return earlierAnswer(question);
};
const earlierProfileView=profile;
profile=function(){const original=earlierProfileView(),s=reviewData376();if(!s)return original;
 const review='<section class="card recovery-review376" aria-label="Progreso y adaptación"><span class="eyebrow">TU COMPAÑERO · INFORME SEMANAL</span><h3>Adaptación según tus registros</h3><p>'+escapeHTML(message376())+'</p><p class="footnote">La interpretación es local y basada en reglas. La postura, las lesiones y la capacidad física requieren valoración individual; no se deducen de los registros.</p></section>';
 return original+review;
};
root.HunterV376={version:'37.6',dayPreview:dayPreview376,review:reviewData376,harmonize:harmonize376,types:TYPES};
try{render();}catch(e){console.warn('V37.6 render',e);}
})(window);

;
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

;
/* Hunter System V37.8 · planificación alternativa y prudente ante lesiones declaradas.
   Las reglas no diagnostican, no prescriben rehabilitación ni evalúan la técnica.
*/
(function(root){
'use strict';
const E=root.Engine,A=root.Adapt,S=root.Schedule;
if(!E||!A||!S||!root.Weekly)return;
const VERSION='37.8';
const MAP={train:'strength',cardio:'cardio',mobility:'recovery',rest:'rest'};
const NAMES={train:'Fuerza',cardio:'Cardio',mobility:'Movilidad',rest:'Descanso'};
const CATS={strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'};
const GROUPS={upper:['Pecho','Espalda','Hombros','Brazos'],lower:['Piernas','Cadera'],core:['Core','Abdomen'],full:['Piernas','Cadera','Pecho','Espalda','Brazos','Hombros','Core','Abdomen']};
const DAYTYPE=['rest','train','cardio','mobility'];
const escapeHtml=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const actives=p=>A.active(p||{});
const relevant=p=>actives(p).length>0||!!p?.health?.special||!!p?.limits;
const preserve=(d,logs)=>!!(d.customized||d.routineId||root.Weekly.active(logs?.[d.date])||d.tasks?.some(t=>t.done));
const preference=(p,date)=>S.valid(p)?p.weekPlan[S.weekday(date)]:null;
const simpleHash=x=>{let h=2166136261;for(let i=0;i<x.length;i++){h^=x.charCodeAt(i);h=Math.imul(h,16777619);}return (h>>>0).toString(16);};
function planKey(p){return simpleHash(JSON.stringify({injuries:p?.injuries||[],health:p?.health||{},restrictions:p?.restrictions||{},goal:p?.goal,finalGoal:p?.finalGoal,focus:p?.focus,trainingMode:p?.trainingMode,equipment:p?.equipment,gymEquipment23:p?.gymEquipment23,minutes:p?.minutes,difficulty:p?.difficulty,experience:p?.experience,trainingScale:p?.trainingScale,weekPlan:p?.weekPlan,restSeconds:p?.restSeconds}));}
function clinicalGate(p,logs={},today=E.dateKey()){
 const mode=A.mode(p);
 if(!mode.auto)return {ok:false,reason:mode.reason};
 // Un episodio de dolor reciente registrado detiene la progresión automática.
 const recent=Object.entries(logs).some(([date,log])=>date<=today&&date>=E.plus(today,-7)&&log?.pain);
 if(recent)return {ok:false,reason:'Se registró dolor en los últimos siete días. Se requiere revisar tu situación antes de generar nuevos ejercicios.'};
 return {ok:true,reason:mode.reason};
}
function affectedAreas(p){return new Set(actives(p).map(x=>x.zone).filter(Boolean));}
function approvedExercise(e,p){
 if(!e||!e.id||!e.name||!Array.isArray(e.zones))return false;
 if(e.sourceOnly||e.variant!=='base'||e.gymOnly23&&p.equipment!=='gym')return false;
 if(e.level>1)return false; // Movimientos más difíciles se excluyen mientras hay lesiones.
 if(e.impact==='Con saltos')return false;
 if(e.equipment==='db'&&p.equipment!=='db'&&p.equipment!=='gym')return false;
 const affected=affectedAreas(p);
 if((e.zones||[]).some(z=>affected.has(z)))return false;
 // Inspección adicional de apoyos que no siempre figuran entre las zonas
 // musculares de la ficha de un ejercicio (por ejemplo: flexiones de rodillas).
 if(affected.has('knee') && (e.floor&&!e.seated||/rodillas apoyadas|cuadrupedia|cuatro apoyos/i.test((e.name||'')+' '+(e.steps||''))))return false;
 if(affected.has('ankle') && /bridge|puente de gl[uú]teos|talones|elevaci[oó]n de tal[oó]n/i.test((e.id||'')+' '+(e.name||'')))return false;
 if(affected.has('wrist') && /boxeo|golpes|puños|punch|shadowbox/i.test((e.name||'')+' '+(e.id||'')))return false;
 if(affected.has('neck')&&(e.overhead||e.spineMove))return false;
 if(affected.has('back')&&(e.spineMove||e.floor&&e.group==='Core'))return false;
 if(affected.has('knee')&&e.kneeBend)return false;
 if(affected.has('wrist')&&e.wristLoad)return false;
 if(affected.has('shoulder')&&e.overhead)return false;
 // La base de datos ya clasifica equipo, apoyo, suelo, rango y carga; no
 // ignoramos esos filtros. Solamente admitimos omitir el límite global de
 // nivel cero para una variante de nivel uno sin otros motivos de exclusión.
 const explanations=A.reasons(e,p).filter(x=>!x.startsWith('Nivel superior al seleccionado'));
 if(explanations.length)return false;
 return true;
}
function candidates(p,kind){
 const category=CATS[kind];
 return E.exercises.filter(e=>e.category===category&&approvedExercise(e,p));
}
function uniqueExercises(pool){const ids=new Set();return pool.filter(e=>{const base=e.baseId||e.id;if(ids.has(base))return false;ids.add(base);return true;});}
function ordered(p,pool,kind,offset=0,history=[]){
 const groups=GROUPS[p.focus]||GROUPS.full;
 const prev=new Set(history.flatMap(x=>(x.items||[]).map(a=>E.byId(a.id)?.baseId||a.id)));
 const grouped=pool.map((e,i)=>({e,score:
  (groups.includes(e.group)?6-groups.indexOf(e.group)*.25:0)
  +(e.level===0?5:0)
  +(prev.has(e.baseId||e.id)?-3:0)
  +(e.equipment==='body'?1:0),
  order:i}));
 grouped.sort((a,b)=>b.score-a.score||a.e.id.localeCompare(b.e.id));
 const byGroup=new Map();for(const item of grouped){const g=item.e.group;if(!byGroup.has(g))byGroup.set(g,[]);byGroup.get(g).push(item.e);}
 const keys=[...byGroup.keys()],rotated=keys.length?[...keys.slice(offset%keys.length),...keys.slice(0,offset%keys.length)]:[];
 const selected=[];let turn=0;
 while(selected.length<pool.length){let more=false;for(const g of rotated){const list=byGroup.get(g);const e=list[turn];if(e){selected.push(e);more=true;}}if(!more)break;turn++;}
 return selected;
}
function previousSessions(logs,now){return Object.entries(logs||{}).filter(([d,l])=>d<now&&d>=E.plus(now,-14)&&l?.complete&&!l.pain).map(([date,l])=>({...l,date}));}
function loadPolicy(logs,now){
 const recent=previousSessions(logs,now);
 const effort=recent.map(l=>Number(l.rpe)).filter(n=>n>0&&n<=10);
 const avg=effort.length?effort.reduce((a,b)=>a+b,0)/effort.length:0;
 const fatigue=recent.some(l=>l.fatigue||l.rpe>=8);
 return {fatigue,avg,completed:recent.length,history:recent,reduce:fatigue||avg>6};
}
function choose(p,kind,rotation=0,logs={},date=E.dateKey()){
 const gate=clinicalGate(p,logs,E.dateKey());
 if(!gate.ok)return {items:[],blocked:gate.reason,kind};
 if(kind==='rest')return {items:[],kind:'rest'};
 const primary=candidates(p,kind);
 // Solo si la modalidad elegida no tiene alternativas compatibles se propone
 // un tipo diferente de ejercicio; la sustitución se declara explícitamente.
 const alternatives=kind==='cardio'?['recovery','strength']:kind==='strength'?['recovery','cardio']:['cardio','strength'];
 let effectiveKind=kind,pool=primary;
 if(!pool.length){for(const other of alternatives){const list=candidates(p,other);if(list.length){pool=list;effectiveKind=other;break;}}}
 if(!pool.length)return {items:[],kind,blocked:'No hay ejercicios con compatibilidad verificable según las zonas, el equipo y las restricciones del perfil. No se sustituye por descanso.'};
 const recent=previousSessions(logs,date),selection=ordered(p,uniqueExercises(pool),effectiveKind,Math.abs(rotation),recent);
 const minutes=Math.max(10,Math.min(45,Number(p.minutes)||20));
 const cap=minutes<=10?2:minutes<=20?3:minutes<=30?4:5;
 const chosen=selection.slice(0,Math.min(cap,selection.length));
 const feedback=loadPolicy(logs,date);
 const items=chosen.map(e=>({id:e.id,
   sets:1,
   target:e.unit==='sec'?(feedback.reduce?12:20):(feedback.reduce?4:e.level?5:7),
   rest:effectiveKind==='recovery'?60:90,
   adaptation378:true,
   noAutoLoad:true,
   safetyNote:'Movimiento controlado, intensidad baja y recorrido tolerable; sin dolor ni carga obligatoria.'
 }));
 return {items:items.map(x=>root.HunterCatalog46?.cap(E.byId(x.id),p,x,{status:{reduced:true},logs,date})||x),kind:effectiveKind,originalKind:kind,substituted:effectiveKind!==kind,feedback};
}
function patchDay(day,p,rotation,logs,today,last){
 if(!day||day.date<today||preserve(day,logs)||root.HunterStable3828?.keep?.(day,p,logs,today))return false;
 const choice=preference(p,day.date);if(!choice)return false;
 const originalKind=MAP[choice];
 if(choice==='rest'){
  if(day.kind!=='rest'||day.items?.length){day.kind='rest';day.items=[];day.tasks=[{id:'rest',text:'Respetar el descanso elegido',done:false}];day.title='Descanso elegido';delete day.blocked378;delete day.adapted378;return true;}
  return false;
 }
 const key=planKey(p)+':'+choice+':'+day.date+':'+String(Object.entries(logs||{}).filter(([d,l])=>d>=E.plus(today,-7)&&d<=today&&(l?.pain||l?.fatigue||l?.complete)).map(([d,l])=>d+':'+l.rpe+':'+l.complete+':'+l.pain).join('|'));
 if(day.planningKey378===key&&day.adapted378)return false;
 const proposed=choose(p,originalKind,rotation,logs,day.date);
 day.kind=proposed.kind;
 day.items=proposed.items;
 day.scheduleChoice=choice;
 day.adapted378=true;
 day.planningKey378=key;
 day.adaptation='injury-alternatives-378';
 day.originalCategory378=NAMES[choice];
 if(proposed.blocked){
  day.blocked378=proposed.blocked;
  day.title=NAMES[choice]+' · pendiente de valoración';
  day.tasks=[];
  day.planningNote='No se indican ejercicios automáticos mientras no haya compatibilidad suficiente o autorización.';
 }else{
  delete day.blocked378;
  day.tasks=[];
  day.title=proposed.substituted?NAMES[choice]+' → '+({strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'}[day.kind])+' suave':NAMES[choice]+' adaptada · baja intensidad';
  day.planningNote=(proposed.substituted?'La actividad elegida fue sustituida por otra categoría compatible. ':'')+'Ejercicios seleccionados mediante exclusión de zonas y movimientos restringidos, con una sola serie y esfuerzo reducido. Revisión según el esfuerzo y el dolor registrado.';
 }
 delete day.autoTemplateId;
 return true;
}
function patchCycle(c,p,logs={},today=E.dateKey()){
 if(!c?.days?.length||!p||!S.valid(p)||!relevant(p))return 0;
 let changed=0;
 c.days.forEach((d,i)=>{if(patchDay(d,p,i+c.index*5,logs,today))changed++;});
 if(changed){c.injuryPlannerVersion378=VERSION;c.decision='Calendario elegido respetado. Las sesiones permitidas se rellenan con alternativas de menor exigencia según zonas declaradas; no constituyen rehabilitación.';}
 return changed;
}
const olderMake=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){
 const c=olderMake(p,start,index,last,logs);
 patchCycle(c,p,logs,start);
 return c;
};
const olderCurrent=root.currentCycle||currentCycle;
currentCycle=function(){
 const c=olderCurrent();if(!c||!state.profile)return c;
 if(patchCycle(c,state.profile,state.logs||{},E.dateKey())&&typeof save==='function')save();
 return c;
};
const olderWorkout=workout;
workout=function(){
 const d=typeof todayDay==='function'?todayDay():null;
 if(!d||!relevant(state.profile))return olderWorkout();
 if(d.blocked378){
  return '<section class="card injury-block378" role="status"><span class="eyebrow">SESIÓN SELECCIONADA · '+escapeHtml(d.originalCategory378||'Entrenamiento')+'</span><h2>Necesita valoración antes de asignar ejercicios</h2><p>'+escapeHtml(d.blocked378)+'</p><p class="footnote">El día mantiene su tipo elegido y no se cuenta como descanso. Por seguridad, el sistema no inicia una rutina automática sin información suficiente. Puedes actualizar las restricciones y la actividad autorizada en Perfil.</p><button class="btn ghost full" onclick="go(\'profile\')">Revisar perfil</button></section>';
 }
 const html=olderWorkout();
 if(!d.adapted378||!d.items?.length)return html;
 return '<div class="notice injury-banner378" role="note"><b>Sesión adaptada a las limitaciones declaradas</b><p>'+escapeHtml(d.planningNote||'')+'</p><small>1 serie por ejercicio · intensidad reducida · descansos ampliados. Interrumpe ante dolor nuevo o empeoramiento. Las reglas no verifican la seguridad clínica ni la técnica individual.</small></div>'+html;
};
const olderPlan=plan;
plan=function(){const c=currentCycle();const html=olderPlan();if(!relevant(state.profile)||!c)return html;
 const days=c.days.filter(x=>x.adapted378),ready=days.filter(d=>d.items?.length).length,blocked=days.filter(d=>d.blocked378).length;
 return '<section class="card injury-summary378"><span class="eyebrow">ACOMPAÑANTE · SESIONES ADAPTADAS</span><h3>Planificación con restricciones</h3><p>'+ready+' días con ejercicios alternativos de baja exigencia; '+blocked+' días pendientes de valoración. Los descansos que elegiste permanecen sin entrenamientos.</p><p class="footnote">La selección es una comprobación técnica de zonas, no una valoración médica ni un programa de rehabilitación.</p></section>'+html;
};
const oldAnswer=answerCoach;
answerCoach=function(q){
 const message=String(q||'');
 if(state.profile&&relevant(state.profile)&&/(lesion|lesi[oó]n|dolor|ejercicio|rutina adaptada|entrenamiento con)/i.test(message)){
  const c=currentCycle(),today=c?.days?.find(d=>d.date===E.dateKey());
  if(today?.blocked378)return companionDisplayName()+': Tienes un día de entrenamiento planificado, pero no he asignado ejercicios automáticos: '+today.blocked378;
  if(today?.adapted378&&today.items?.length)return companionDisplayName()+': '+today.title+'. Ejercicios programados: '+today.items.map(x=>E.byId(x.id)?.name).filter(Boolean).join(', ')+'. Todos están en dosis reducida y excluyen las zonas y restricciones declaradas. No sustituyen una evaluación clínica.';
 }
 return oldAnswer(q);
};
// Centro de rutinas: alternativas realmente seleccionables, no solo notas.
function adaptedTemplates(p,logs={}){
 if(!p||!relevant(p)||!clinicalGate(p,logs).ok)return [];
 const types=[['strength','Fuerza'],['cardio','Cardio'],['recovery','Movilidad']],templates=[],seen=new Set();
 for(const [type,category]of types){
  for(let variant=0;variant<3;variant++){
   const sample=choose(p,type,variant*3+1,logs),items=sample.items;
   if(!items.length)continue;
   const stamp=items.map(x=>x.id).sort().join(',');
   if(seen.has(stamp))continue;seen.add(stamp);
   const requirements=[...new Set(items.flatMap(x=>E.byId(x.id)?.requirements||[]))];
   templates.push({id:'injury378-'+type+'-'+variant,name:category+' suave · alternativa '+(variant+1),category:sample.kind==='strength'?'Fuerza':sample.kind==='cardio'?'Cardio':'Movilidad',description:'Selección reducida de movimientos que no incluyen zonas o restricciones declaradas; requiere autorización para entrenar.',items,requirements,level:0,extension:'standard',goalGroups:['fitness','habit','movement','build','athletic'],family:'adapted',impact:'Sin saltos',minutes:Math.max(5,items.length*4),safe378:true});
  }
 }
 return templates;
}
const olderAllRoutines=allRoutines;
allRoutines=function(){return [...olderAllRoutines(),...adaptedTemplates(state.profile,state.logs||{})];};
const prevCompatible=A.routineCompatible;
A.routineCompatible=function(r,p){
 if(r?.safe378)return clinicalGate(p,state.logs||{}).ok&&r.items.length>0&&r.items.every(x=>approvedExercise(E.byId(x.id),p));
 return prevCompatible(r,p);
};
const previousRoutineList=routineList;
routineList=function(){
 const list=previousRoutineList();
 if(!state.profile||!relevant(state.profile))return list;
 const p=GoalPlans.adjusted(state.profile);
 return list.filter(r=>A.routineCompatible(r,p)&&Catalog.available(r,p));
};
const previousRoutinesView=routines;
routines=function(){
 const html=previousRoutinesView();if(!relevant(state.profile))return html;
 const gate=clinicalGate(state.profile,state.logs||{});
 const note=gate.ok?'Se muestran únicamente rutinas compatibles con las zonas y restricciones registradas. Las variantes adaptadas incluyen series reducidas.':'Se ha suspendido la asignación automática de ejercicios: '+gate.reason;
 return '<div class="notice injury-banner378" role="note">'+escapeHtml(note)+'</div>'+html;
};
const prevRoutineDetail=routineDetail;
routineDetail=function(id){
 prevRoutineDetail(id);
 if(!id?.startsWith('injury378-'))return;
 const select=document.querySelector('#routineDay');
 if(select){for(const opt of [...select.options]){const c=currentCycle();const d=c?.days?.find(x=>x.date===opt.value);if(!d||preference(state.profile,d.date)==='rest')opt.remove();}}
};
const prevPrepareRoutine=prepareRoutine;
prepareRoutine=function(id){
 if(id?.startsWith('injury378-')){
  const date=document.querySelector('#routineDay')?.value;
  if(!date||preference(state.profile,date)==='rest')return routineNotice372('Selecciona un día programado para entrenar. No se modifican los descansos elegidos.');
 }
 return prevPrepareRoutine(id);
};
root.HunterInjuryPlanner378={version:VERSION,choose,approvedExercise,candidates,patchCycle,clinicalGate,planKey,loadPolicy,adaptedTemplates};
try{render();}catch(e){console.warn('Motor de lesiones 37.8',e);}
})(window);

;
/* Hunter System V37.9 — repair of empty injury sessions.
 * Static rule-based scheduling; NOT a diagnosis, clearance, or rehabilitation prescription.
 * Important: always preserve explicitly stopped sessions, recorded work and chosen rest.
 */
(function(root){
'use strict';
const E=root.Engine,A=root.Adapt,S=root.Schedule,W=root.Weekly;
if(!E||!A||!S||!W||!root.HunterInjuryPlanner378)return;
const VER='37.9';
// Low-demand seated upper-body options when the offline catalogue has no
// suitable strength exercises for a lower-limb limitation. These are text-only
// instructions, without claiming to have a verified vector/3D demonstration.
const ADDITIONS=[
 {id:'hs379-scapular',name:'Juntar omóplatos sentado',group:'Espalda',category:'Fuerza',zones:['shoulder','back'],unit:'reps',instructions:['Sentarse con la espalda apoyada cómodamente y los brazos relajados.','Acercar suavemente los omóplatos sin elevar los hombros.','Volver a la postura neutra sin echar el cuerpo hacia atrás.'],cue:'Movimiento pequeño y cómodo; sin mover las piernas.'},
 {id:'hs379-palms',name:'Presión suave de palmas sentado',group:'Pecho',category:'Fuerza',zones:['shoulder','elbow','wrist','back'],unit:'sec',instructions:['Sentarse en un apoyo firme con piernas cómodas.','Juntar las palmas frente al pecho sin elevar los hombros.','Presionar suavemente durante el intervalo y soltar.'],cue:'Esfuerzo bajo y respiración continua; no usar si la mano, muñeca o hombro está lesionado.'},
 {id:'hs379-isobiceps',name:'Flexión de codo con resistencia propia sentado',group:'Brazos',category:'Fuerza',zones:['shoulder','elbow','wrist'],unit:'sec',instructions:['Sentarse con espalda apoyada y hombros relajados.','Flexionar un codo aproximadamente a mitad de recorrido y oponer suavemente la mano contraria.','Mantener una contracción leve y cambiar de brazo; evitar tensión en cuello y muñecas.'],cue:'Sin tirones ni esfuerzos máximos; no usar con lesión de brazo, codo o muñeca.'},
 {id:'hs379-shoulderset',name:'Elevación corta de hombros sentado',group:'Hombros',category:'Fuerza',zones:['shoulder','neck'],unit:'reps',instructions:['Sentarse con la columna apoyada y las piernas cómodas.','Elevar los hombros solo unos centímetros sin levantar los brazos.','Soltar suavemente hasta la postura neutra.'],cue:'Movimiento pequeño sin bloquear el cuello.'}
];
for(const x of ADDITIONS){if(E.byId(x.id))continue;const e={...x,equipment:'body',level:0,steps:x.instructions.join(' '),setup:x.instructions[0],impact:'Bajo',seated:true,floor:false,overhead:false,spineMove:false,wristLoad:false,kneeBend:false,requirements:[],variant:'base',baseId:x.id,sourceOnly:false,tempo:'Lento y controlado.',breath:'Respiración natural sin aguantar el aire.',control:'Detente si aparece o empeora el dolor.',noVector379:true};E.exercises.push(e);}

const TYPES={train:'strength',cardio:'cardio',mobility:'recovery',rest:'rest'};
const LABELS={train:'Fuerza',cardio:'Cardio',mobility:'Movilidad',rest:'Descanso'};
const NAMES={strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'};
const ZONES=new Set(['neck','shoulder','elbow','wrist','back','hip','knee','ankle']);
const GROUPS={
 upper:['Pecho','Espalda','Hombros','Brazos'],lower:['Piernas','Cadera'],other:['Core','Abdomen'],
 preferred:{
  knee:['Pecho','Espalda','Brazos','Hombros','Core','Abdomen','Piernas','Cadera'],
  ankle:['Pecho','Espalda','Brazos','Hombros','Core','Abdomen','Piernas','Cadera'],
  hip:['Pecho','Espalda','Brazos','Hombros','Core','Abdomen','Piernas','Cadera'],
  shoulder:['Piernas','Cadera','Core','Abdomen','Brazos','Espalda','Pecho'],
  elbow:['Piernas','Cadera','Core','Abdomen','Pecho','Espalda','Brazos'],
  wrist:['Piernas','Cadera','Core','Abdomen','Pecho','Espalda','Brazos'],
  back:['Piernas','Brazos','Pecho','Cadera','Hombros','Core','Abdomen','Espalda'],
  neck:['Piernas','Cadera','Core','Abdomen','Brazos','Pecho','Espalda']
 }
};
function active(p){return A.active(p||{});}
function impactStatus(p){
 const a=active(p),h=p?.health||{},zones=a.map(i=>i.zone),known=zones.length&&zones.every(z=>ZONES.has(z));
 const serious=!!h.acute||h.pain==='strong'||a.some(i=>i.type==='bone'||i.type==='surgery')||
  !!h.emergency||!!h.neurological||!!h.unstable||!!h.redFlags;
 if(!p||p.age<18)return {ok:false,reason:'Se requiere una planificación individual apropiada para la edad.'};
 if(serious)return {ok:false,reason:'Se registró dolor intenso, una lesión reciente grave o una condición que requiere valoración. No es seguro asignar automáticamente ejercicios, incluso para otras zonas.'};
 if(a.length&&!known)return {ok:false,reason:'Indica qué zona está afectada para excluirla de todos los movimientos. Una zona desconocida no puede filtrarse de forma fiable.'};
 if(h.special&&!h.approved)return {ok:false,reason:'Existe una condición especial que necesita indicaciones individuales antes de programar ejercicio.'};
 if(p.limits&&!a.length)return {ok:false,reason:'Marca la zona afectada en Perfil para encontrar grupos musculares alternativos.'};
 if((h.pain==='moderate'||h.pain==='mild')&&!a.length)return {ok:false,reason:'Selecciona la ubicación del dolor para evitar ejercicios que involucren esa zona.'};
 // Mild/moderate LOCALIZED discomfort or the absence of an explicit approval tick should
 // not by itself erase the programme. Exercises are conservative options, never clearance.
 return {ok:true,reason:a.length?'Alternativas de baja exigencia para grupos alejados de las zonas declaradas. El dolor puede limitar también movimientos de otras zonas.':'Planificación general.',affected:zones,reduced:a.length>0||h.pain==='mild'||h.pain==='moderate'};
}
function hasEquipment(e,p){
 if(e.equipment==='db'&&!['db','gym'].includes(p.equipment))return false;
 if(e.gymOnly23&&p.equipment!=='gym')return false;
 for(const x of e.requirements||[]){if(x==='db'&&!['db','gym'].includes(p.equipment))return false;if(x!=='db'&&!p[x])return false;}
 return true;
}
function approved(e,p){
 if(root.HunterCatalog46?.reasons(e,p).length)return false;
 if(!e||!e.id||!e.name||!Array.isArray(e.zones)||!e.category||e.sourceOnly||e.variant!=='base')return false;
 if(!hasEquipment(e,p)||e.level>1||e.impact==='Con saltos')return false;
 const affected=new Set(active(p).map(i=>i.zone)),r=p.restrictions||{},txt=(e.id+' '+e.name+' '+(e.steps||'')).toLowerCase();
 if(e.zones.some(z=>affected.has(z)))return false;
 if(affected.has('knee')&&(e.kneeBend||(!e.seated&&e.floor)||/rodillas apoyadas|cuatro apoyos|cuadrupedia|arrodillad/i.test(txt)))return false;
 if(affected.has('ankle')&&(/puente de gl[uú]teos|elevaci[oó]n de talones|heel raise|calf raise/i.test(txt)||!e.seated&&!e.floor))return false;
 if(affected.has('hip')&&!e.seated&&/sentadilla|zancada|estocada|peso muerto|bisagra/i.test(txt))return false;
 if(affected.has('wrist')&&(e.wristLoad||/boxeo|golpes|puños|punch/i.test(txt)))return false;
 if(affected.has('shoulder')&&(e.overhead||e.wristLoad||/plancha|flexiones|push.?up|brazos elevados/i.test(txt)))return false;
 if(affected.has('neck')&&(e.overhead||e.spineMove))return false;
 if(affected.has('back')&&(e.spineMove||e.floor&&['Core','Abdomen'].includes(e.group)))return false;
 if(r.noImpact&&e.impact==='Con saltos'||r.noFloor&&e.floor||r.noOverhead&&e.overhead||r.noKnee&&e.kneeBend||r.noWrist&&e.wristLoad||r.noLoad&&e.equipment==='db'||r.seatedOnly&&!e.seated||r.noSpine&&e.spineMove)return false;
 return true;
}
// Older mission windows use Adapt.mode and Adapt.reasons directly to lock
// all checkboxes, even when a later planner has populated the exercise list.
// Align the display/checkbox filter with the SAME localized-injury rules.
const priorMode=A.mode,priorReasons=A.reasons,priorAllowed=A.allowed;
const localCase=p=>!!p&&(active(p).length||p.limits||p.health?.pain&&p.health.pain!=='none');
A.mode=function(p){
 if(localCase(p)){
  const s=impactStatus(p);
  if(s.ok)return {auto:true,reduced:true,reason:'Entrenamiento limitado a otros grupos musculares. Ejercicios orientativos de intensidad reducida; no representan autorización médica.'};
 }
 return priorMode(p);
};
A.reasons=function(e,p,strict=true){
 if(localCase(p)&&impactStatus(p).ok)return approved(e,p)?[]:['Ejercicio fuera de los filtros de las zonas afectadas o del equipo disponible'];
 return priorReasons(e,p,strict);
};
A.allowed=function(e,p){
 if(localCase(p)&&impactStatus(p).ok)return approved(e,p);
 return priorAllowed(e,p);
};
function list(p,kind){const category=NAMES[kind];return E.exercises.filter(e=>e.category===category&&approved(e,p));}
function rotation(p,items,n,logs,day){
 const injury=active(p)[0]?.zone,priority=GROUPS.preferred[injury]||[].concat(GROUPS.upper,GROUPS.lower,GROUPS.other);
 const hist=new Set(Object.entries(logs||{}).filter(([date,l])=>date<day&&l.complete).flatMap(([,l])=>(l.items||[]).map(x=>E.byId(x.id)?.baseId||x.id)));
 const unique=new Map();for(const e of items)if(!unique.has(e.baseId||e.id))unique.set(e.baseId||e.id,e);
 const options=[...unique.values()];const pivot=options.length?Math.abs(n)%options.length:0;
 return options.sort((a,b)=>{
  const rank=e=>{let score=0;const ix=priority.indexOf(e.group);if(ix>=0)score+=120-ix*13;if(e.level===0)score+=15;if(e.equipment==='body')score+=2;if(hist.has(e.baseId||e.id))score-=15;return score;};
  return rank(b)-rank(a)||((a.id.charCodeAt(0)+pivot)%17)-((b.id.charCodeAt(0)+pivot)%17)||a.id.localeCompare(b.id);
 });
}
function mildDose(e,logs,date){
 const recent=Object.entries(logs||{}).filter(([d,l])=>d<date&&d>=E.plus(date,-7)&&l);
 const tiring=recent.some(([,l])=>l.pain||l.fatigue||l.rpe>=7);
 return {id:e.id,sets:1,target:e.unit==='sec'?(tiring?10:15):(tiring?4:6),rest:90,adaptation379:true,noAutoLoad:true,safetyNote:'Ejercicio orientativo de baja exigencia; detente si produce síntomas o aumenta las molestias.'};
}
function choose(p,kind,n=0,logs={},date=E.dateKey()){
 const status=impactStatus(p);if(!status.ok)return {items:[],blocked:status.reason,requested:kind};
 if(kind==='rest')return {items:[],effective:'rest',requested:'rest'};
 const primary=list(p,kind);
 let effective=kind,candidates=primary;
 if(!candidates.length){const fallbacks=kind==='cardio'?['strength','recovery']:kind==='strength'?['recovery','cardio']:['strength','cardio'];
  for(const other of fallbacks){const pool=list(p,other);if(pool.length){effective=other;candidates=pool;break;}}
 }
 if(!candidates.length)return {items:[],blocked:'No hay ejercicios de otros grupos que superen los filtros de zona, postura y equipo. Es necesario ajustar las restricciones o el catálogo, no convertir el día en descanso.',requested:kind};
 const arranged=rotation(p,candidates,n,logs,date),duration=Math.max(10,Math.min(45,Number(p.minutes)||20));
 const amount=Math.min(duration<=10?2:duration<=20?3:4,arranged.length);
 // Prefer variety across muscle groups rather than 4 variations of the same joint.
 const selected=[],seen=new Set();for(const e of arranged){if(!seen.has(e.group)){selected.push(e);seen.add(e.group);}if(selected.length>=amount)break;}
 for(const e of arranged){if(selected.length>=amount)break;if(!selected.includes(e))selected.push(e);}
 return {items:selected.map(e=>root.HunterCatalog46?.cap(e,p,mildDose(e,logs,date),{status:{reduced:true},logs,date})||mildDose(e,logs,date)),requested:kind,effective,substituted:effective!==kind,status};
}
function keyFor(p,logs,day){
 const s=JSON.stringify({injuries:p.injuries,health:p.health,restrictions:p.restrictions,weekPlan:p.weekPlan,minutes:p.minutes,equipment:p.equipment,experience:p.experience,trainingMode:p.trainingMode,goal:p.goal,difficulty:p.difficulty,focus:p.focus});
 const past=Object.entries(logs||{}).filter(([d,l])=>d<=day&&d>=E.plus(day,-7)&&l).map(([d,l])=>[d,l.pain,l.fatigue,l.rpe,l.complete]);
 let hash=2166136261;for(const c of s+JSON.stringify(past)){hash^=c.charCodeAt(0);hash=Math.imul(hash,16777619);}return (hash>>>0).toString(36);
}
function locked(d,logs){const log=logs?.[d.date];return !!(d.customized||d.routineId||d.creatorAssigned||W.active(log)||d.tasks?.some(t=>t.done));}
function patchCycle(c,p,logs={},today=E.dateKey()){
 if(!c?.days||!p||!S.valid(p)||(!active(p).length&&!p.limits&&!p.health?.special&&!['mild','moderate','strong'].includes(p.health?.pain)))return 0;
 let edits=0;
 for(const [index,d] of c.days.entries()){
  if(d.date<today||locked(d,logs)||root.HunterStable3828?.keep?.(d,p,logs,today))continue;
  const choice=p.weekPlan[S.weekday(d.date)];if(!choice)continue;
  if(choice==='rest'){if(d.kind!=='rest'||d.items?.length||d.blocked378){d.kind='rest';d.items=[];d.title='Descanso elegido';d.scheduleChoice='rest';d.tasks=[];delete d.blocked378;delete d.adapted378;edits++;}continue;}
  const key=keyFor(p,logs,d.date),needs=d.planningKey379!==key||!d.items?.length||!!d.blocked378||!d.adaptation379;
  if(!needs)continue;
  const target=TYPES[choice],proposal=choose(p,target,c.index*7+index,logs,d.date);
  d.kind=target; // The day category is the user's choice, even if a fallback muscle group is used.
  d.scheduleChoice=choice;d.originalCategory378=LABELS[choice];d.title=proposal.blocked?LABELS[choice]+' · requiere revisión':LABELS[choice]+(proposal.substituted?' · alternativa de '+NAMES[proposal.effective]:' · otros grupos musculares');
  d.items=proposal.items;d.tasks=[];d.adapted378=true;d.adaptation379=VER;d.planningKey379=key;
  if(proposal.blocked){d.blocked378=proposal.blocked;d.planningNote='Sesión programada, sin selección automática por las restricciones indicadas.';}
  else {delete d.blocked378;d.planningNote=(proposal.substituted?'No se encontraron alternativas del mismo tipo; se ofrecen ejercicios de '+NAMES[proposal.effective].toLowerCase()+'. ':'')+
    'Selección de baja intensidad, priorizando grupos fuera de las zonas afectadas. No indica rehabilitación ni comprueba individualmente la seguridad clínica.';}
  edits++;
 }
 if(edits)c.injuryPlannerVersion379=VER;
 return edits;
}
function repairEmptyLog(d){
 if(!d||!d.items?.length)return false;
 const l=state?.logs?.[d.date];if(!l||W.active(l)||l.complete||l.pain||l.manual)return false;
 if(l.items?.length&&l.items.every(x=>d.items.some(y=>y.id===x.id&&y.sets===x.sets&&y.target===x.target)))return false;
 // This is ONLY the unstarted log created by viewing a 0/0 mission.
 l.items=d.items.map(x=>({...x,actual:Array.from({length:x.sets},()=>({done:false,value:x.target,kg:0}))}));
 l.kind=d.kind;return true;
}
const originalCycle=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){
 const c=originalCycle(p,start,index,last,logs);patchCycle(c,p,logs,start);return c;
};
const previousCurrent=currentCycle;
currentCycle=function(){
 const c=previousCurrent();if(!c||!state.profile)return c;
 const changes=patchCycle(c,state.profile,state.logs||{},E.dateKey());
 const d=c.days.find(x=>x.date===E.dateKey());const repaired=repairEmptyLog(d);
 if((changes||repaired)&&typeof save==='function')save();return c;
};
const beforeLog=getLog;
getLog=function(){
 const l=beforeLog(),d=typeof todayDay==='function'?todayDay():null;
 if(repairEmptyLog(d)&&typeof save==='function')save();return state.logs?.[d?.date]||l;
};
// Center of routines: provide actual selectable templates for restricted profiles.
const prevAll=allRoutines;
function templates(p,logs={}){
 if(!p||!active(p).length&&!p.limits)return [];
 const status=impactStatus(p);if(!status.ok)return [];
 const generated=[],ids=new Set();
 for(const [kind,category] of [['strength','Fuerza'],['cardio','Cardio'],['recovery','Movilidad']]){
  for(let n=0;n<4;n++){
   const c=choose(p,kind,n*5,logs),items=c.items;if(!items?.length)continue;
   const sig=items.map(x=>x.id).sort().join('/');if(ids.has(sig))continue;ids.add(sig);
   generated.push({id:'injury379-'+kind+'-'+n,name:category+' · otros grupos '+(n+1),category:NAMES[c.effective],description:'Movimientos seleccionados fuera de las zonas declaradas, intensidad reducida; sin validez médica individual.',items,requirements:[],level:0,goalGroups:['fitness','habit','movement','build','athletic'],extension:'standard',family:'adapted',impact:'Sin saltos',minutes:Math.max(8,items.length*4),safe379:true});
  }
 }
 return generated;
}
allRoutines=function(){return [...prevAll(),...templates(state.profile,state.logs||{})];};
const oldRoutineCompatible=A.routineCompatible;
A.routineCompatible=function(r,p){return r?.safe379?!!impactStatus(p).ok&&!!r.items?.length&&r.items.every(x=>approved(E.byId(x.id),p)):oldRoutineCompatible(r,p);};
const oldRoutineList=routineList;
routineList=function(){
 const old=oldRoutineList(),p=state.profile;
 if(!p||!active(p).length&&!p.limits)return old;
 const safe=templates(p,state.logs||{}).filter(r=>A.routineCompatible(r,p));
 const used=new Set(old.map(r=>r.id));return [...old,...safe.filter(r=>!used.has(r.id))];
};
const beforeTechnique=technique;
technique=function(id){
 const e=E.byId(id);if(!e?.noVector379)return beforeTechnique(id);
 const esc=s=>String(s??'').replace(/[&<>\"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','\"':'&quot;',"'":'&#39;'}[c]));
 modal('<span class="eyebrow">'+esc(e.group)+' · MOVIMIENTO SIN EQUIPO</span><h2>'+esc(e.name)+'</h2><div class="notice">No existe una demostración animada validada para esta ficha nueva. Sigue únicamente las instrucciones textuales y evita el ejercicio si produce molestias.</div><ol class="tech-steps">'+e.instructions.map(x=>'<li>'+esc(x)+'</li>').join('')+'</ol><p>'+esc(e.cue)+'</p>');
};
const oldRoutineView=routines;
routines=function(){
 let html=oldRoutineView();if(!state.profile||!active(state.profile).length)return html;
 // Remove the legacy gating banner (which used the old blocked-only calculation).
 html=html.replace(/^<div class="notice injury-banner378" role="note">[\s\S]*?<\/div>/,'');
 const status=impactStatus(state.profile),num=templates(state.profile,state.logs||{}).length;
 const shown=status.ok?templates(state.profile,state.logs||{}):[];
 const esc=s=>String(s??'').replace(/[&<>\"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','\"':'&quot;',"'":'&#39;'}[c]));
 const recommended=shown.length?'<section class="card"><span class="eyebrow">RUTINAS ADAPTADAS A TU PERFIL</span><h2>Otros grupos musculares</h2><p>Selecciona una sesión con movimientos fuera de las zonas limitadas.</p><div class="routine-grid">'+shown.map(r=>'<button class="btn ghost" type="button" onclick="routineDetail(\''+r.id+'\')"><b>'+esc(r.name)+'</b><small> · '+r.items.length+' movimientos · intensidad reducida</small></button>').join('')+'</div></section>':'';
 return '<div class="notice injury-banner378" role="note">'+
  (status.ok?'Alternativas disponibles para grupos no afectados: '+num+' plantillas. Baja exigencia; detén cualquier movimiento que agrave los síntomas.':'No se han asignado ejercicios automáticos: '+String(status.reason).replace(/[<>&"]/g,''))+
  '</div>'+recommended+html;
};
const beforeDetail=routineDetail;
routineDetail=function(id){
 beforeDetail(id);
 if(!id?.startsWith('injury379-'))return;
 const select=document.querySelector('#routineDay');if(select){const c=currentCycle();for(const option of [...select.options]){const d=c?.days?.find(x=>x.date===option.value);if(!d||S.valid(state.profile)&&state.profile.weekPlan[S.weekday(d.date)]==='rest')option.remove();}}
};
const beforePrepare=prepareRoutine;
prepareRoutine=function(id){
 if(id?.startsWith('injury379-')){
  const date=document.querySelector('#routineDay')?.value;
  if(!date||state.profile?.weekPlan[S.weekday(date)]==='rest')return routineNotice372('Elige un día de entrenamiento, no uno de descanso.');
 }
 return beforePrepare(id);
};
root.HunterPlanner379={version:VER,status:impactStatus,approved,choose,patchCycle,repairEmptyLog,templates};
if(!Runtime20.initializing)try{currentCycle();render();}catch(err){console.warn('Motor V37.9',err);}
})(window);

;
/* Hunter System V38.0 - per-device performance management (does not alter exercise timers). */
(function(w,d){'use strict';
  const perf={
    version:'38.0',refreshHz:null, callbackHz:null, longTasks:0,drawMs:0,drawCount:0,
    samples:[],lastRaf:0, calibrationDone:false, initialTime:performance.now(),
    powerSaver:false, level:'auto',
    sampleDrawCost(ms){if(!(ms>=0&&ms<300))return;
      this.drawMs=this.drawCount?this.drawMs*.94+ms*.06:ms;
      this.drawCount++;},
    actorInterval(state){
      if(d.hidden||this.powerSaver|| (w.HunterAdaptive3823?.quality==='eco' && state==='idle'))return 1000/24;
      if(w.matchMedia?.('(prefers-reduced-motion: reduce)').matches)return 1000/24;
      const hz=this.refreshHz||60;
      // Cost budget protects the UI from expensive SVG work on slower devices.
      const busy=this.drawCount>20&&this.drawMs>2.7;
      const transitional=state!=='idle'&&state!=='hidden';
      const target=busy?Math.min(45,hz):transitional?Math.min(120,hz):Math.min(60,hz);
      return 1000/Math.max(24,target)-.45;
    },
    observe(){
      if(d.hidden||this.calibrationDone)return;
      const that=this;let n=0,prev=0,frames=[];
      function tick(t){if(d.hidden)return;
        if(prev){const dt=t-prev;if(dt>3&&dt<80)frames.push(dt);}
        prev=t;if(++n<120){w.requestAnimationFrame(tick);return;}
        if(frames.length>=50){
          frames.sort((a,b)=>a-b);let med=frames[Math.floor(frames.length/2)];
          const nominal=[60,75,90,120,144,165];
          const raw=1000/med;
          let guess=nominal.reduce((a,b)=>Math.abs(b-raw)<Math.abs(a-raw)?b:a,nominal[0]);
          // Do not infer high-refresh panel unless measured callbacks support it.
          that.refreshHz=raw>=guess*.9?guess:Math.round(raw);
          that.callbackHz=Math.round(raw);
          that.calibrationDone=true;
          d.documentElement.dataset.fpsCalibrated380='true';
        }
      }
      w.requestAnimationFrame(tick);
    },
    snapshot(){return {refreshEstimateHz:this.refreshHz,callbackEstimateHz:this.callbackHz,
      actorDrawMsAverage:Number(this.drawMs.toFixed(3)), actorSamples:this.drawCount,
      longTasks:this.longTasks,note:'No son FPS reales de pantalla; medir Android con FrameTimeline/Perfetto.'};}
  };
  w.HunterPerf380=perf;
  if(typeof PerformanceObserver==='function')try{
    const observer=new PerformanceObserver(list=>{perf.longTasks+=list.getEntries().length;});
    observer.observe({entryTypes:['longtask']});
    w.addEventListener('pagehide',()=>observer.disconnect(),{once:true});
  }catch(e){}
  if(d.readyState==='loading')d.addEventListener('DOMContentLoaded',()=>perf.observe(),{once:true});else perf.observe();
  d.addEventListener('visibilitychange',()=>{if(!d.hidden){perf.calibrationDone=false;perf.observe();}});
  try{
    if(typeof w.HunterV373?.power==='function'){
      const old=w.HunterV373.power;
      w.HunterV373.power=function(state){perf.powerSaver=state?.powerSave===true;return old(state);};
    }
  }catch(e){}
  // Display only diagnostics already requested by the user; no telemetry or network requests.
  if(typeof w.openDiagnostics20==='function'){
    const old=w.openDiagnostics20;
    w.openDiagnostics20=function(...args){let ret=old.apply(this,args);
      const section=d.querySelector('#modal section');
      if(section){const p=d.createElement('p');p.className='fps-metrics380';p.textContent=
        'Fluidez V38.0: estimación del ritmo de callbacks '+(perf.callbackHz===null?'pendiente':perf.callbackHz+' Hz')+
        '. FPS reales en Android: no medidos. Las animaciones se adaptan a la carga.';
        section.append(p);}
      return ret;
    };
  }
})(window,document);

;
/* Hunter System V38.7 - motor de equilibrio semanal (sin ventanas ni paneles nuevos).
 * Solo puntúa ejercicios que pasaron los filtros de compatibilidad existentes.
 * Los patrones explícitos se usan para diversidad, NO para declarar seguridad clínica.
 */
(function(root) {
 'use strict';
 if (root.HunterTraining387) return;
 const knownPatterns=Object.freeze({
  chair:'rodilla',squat:'rodilla',goblet:'rodilla',sumo:'rodilla',dbsumo:'rodilla',reverselunge:'rodilla',dblunge:'rodilla',split:'rodilla',lateralunge:'rodilla',stepup:'rodilla',wallsit:'rodilla',
  wall:'empuje_horizontal',incline:'empuje_horizontal',pushup:'empuje_horizontal',kneepush:'empuje_horizontal',press:'empuje_horizontal',neutral:'empuje_horizontal',benchpress:'empuje_horizontal',closepush:'empuje_horizontal',
  bridge:'extension_cadera',dbbridge:'extension_cadera',singlebridge:'extension_cadera',hinge:'bisagra_cadera',rdl:'bisagra_cadera',singlehinge:'bisagra_cadera',sumodead:'bisagra_cadera',
  row:'traccion_horizontal',bentrow:'traccion_horizontal',towelrow:'traccion_horizontal',prone:'escapular',reverse:'escapular',scapular:'escapular',reversefly:'escapular',
  curl:'flexion_codo',hammer:'flexion_codo',seatedcurl:'flexion_codo',tricep:'extension_codo',lyingtricep:'extension_codo',
  lateral:'elevacion_hombro',seatedlateral:'elevacion_hombro',overheadpress:'empuje_vertical',seatedpress:'empuje_vertical',
  calf:'elevacion_talon',dbcalf:'elevacion_talon',seatedcalf:'elevacion_talon',
  deadbug:'estabilizacion_tronco',bird:'estabilizacion_tronco',plank:'estabilizacion_tronco',sideplank:'estabilizacion_tronco',heel:'estabilizacion_tronco'
 });
 const hasOwn=(o,k)=>Object.prototype.hasOwnProperty.call(o,k);
 const isoDay=x=>/^\d{4}-\d{2}-\d{2}$/.test(x);
 const number=(v,min=0,max=200)=>{const n=Number(v);return Number.isFinite(n)?Math.min(max,Math.max(min,n)):min;};
 const dateOffset=(date,days)=>{const d=new Date(date+'T12:00:00Z');d.setUTCDate(d.getUTCDate()+days);return d.toISOString().slice(0,10);};
 function pattern(e){
  const explicit=root.HunterGroups49?.classify(e)?.pattern;if(explicit)return explicit;
  if(!e?.id)return null;
  if(typeof e.pattern==='string'&&e.pattern.length)return e.pattern;
  const candidates=[e.baseId,e.family,e.id].filter(x=>typeof x==='string');
  for(const raw of candidates){
   const id=raw.replace(/(?:_slow|_pause|_short|_easy|-tempo23|-pause23|-control18)$/,'');
   if(hasOwn(knownPatterns,id))return knownPatterns[id];
  }
  return null;
 }
 function load(logs,date,days=7){
  const groups=Object.create(null),patterns=Object.create(null),ids=Object.create(null);
  const engine=root.Engine;
  const min=dateOffset(date,-Math.max(1,Math.min(21,days)));
  let sessions=0,finishedSets=0;
  for(const [key,l] of Object.entries(logs||{})){
   if(!isoDay(key)||key>=date||key<min||!l||l.pain||!Array.isArray(l.items))continue;
   let counted=false;
   for(const item of l.items){
    if(!item?.id||!Array.isArray(item.actual))continue;
    const completed=item.actual.filter(s=>s?.done===true).length;
    if(!completed)continue;
    const e=engine?.byId?.(item.id);
    if(!e)continue;
    if(e.group)groups[e.group]=(groups[e.group]||0)+completed;
    const p=pattern(e);if(p)patterns[p]=(patterns[p]||0)+completed;
    const base=e.baseId||e.family||e.id;
    ids[base]=(ids[base]||0)+completed;
    finishedSets+=completed;counted=true;
   }
   if(counted)sessions++;
  }
  return {groups,patterns,ids,sessions,finishedSets};
 }
 function score(e,context={}){
  const weekly=context.weekly||{groups:{},patterns:{},ids:{}};
  const counts=context.dayCounts||{};
  const patternCounts=context.dayPatterns||{};
  const group=e?.group||'Sin clasificar';
  const p=pattern(e);
  const base=e?.baseId||e?.family||e?.id;
  const groupSets=number(weekly.groups?.[group],0,100);
  const patternSets=p?number(weekly.patterns?.[p],0,100):0;
  const recent=number(weekly.ids?.[base],0,30);
  // Priorización suave: un mismo patrón o grupo no domina la sesión.
  return -Math.min(30,groupSets*1.7)-Math.min(15,patternSets*1.2)-
    Math.min(18,recent*5)-Math.min(14,number(counts[group],0,20)*7)-
    (p?Math.min(13,number(patternCounts[p],0,20)*7):0);
 }
 function dose(e,profile,kind,status,feedback,date,original,weekly){
  const out={...original};
  const group=e?.group||'';
  const strained=Boolean(feedback?.strain),reduced=Boolean(status?.reduced);
  // No incrementar por encima de lo seguro cuando se registran lesiones o fatiga.
  if(reduced||strained||profile?.health?.special||profile?.health?.acute)return out;
  const g=String(profile?.goal||'general');
  if(kind==='strength'){
   if(g==='muscle'&&Number(profile?.minutes)>=30&&feedback?.canProgress&&out.sets<3&&
      number(weekly?.groups?.[group],0,100)<10){out.sets=Math.min(3,out.sets+1);}
   if(number(weekly?.groups?.[group],0,100)>=12)out.sets=Math.min(out.sets,2);
   if(g==='strength')out.rest=Math.max(Number(out.rest)||90,100);
  }else if(kind==='cardio'&&g==='endurance'&&feedback?.canProgress){
   out.target=Math.min(e.unit==='sec'?60:14,out.target+(e.unit==='sec'?5:1));
  }
  if(!Number.isFinite(out.target)||out.target<=0)out.target=original.target;
  return out;
 }
 function catalog(){
  const ex=root.Engine?.exercises||[];
  let group=0,patterns=0,zones=0,equipment=0,models=0;
  for(const e of ex){
   if(e?.group)group++;
   if(pattern(e))patterns++;
   if(Array.isArray(e?.zones)&&e.zones.length)zones++;
   if(e?.equipment)equipment++;
   if(e?.modelPath30&&e?.demoPath30)models++;
  }
  return {total:ex.length,group,patterns,zones,equipment,models,
   notice:'Los patrones ayudan a diversificar sesiones; no prueban seguridad clínica.'};
 }
 root.HunterTraining387=Object.freeze({version:'38.7',pattern,load,score,dose,catalog});
})(typeof window==='undefined'?globalThis:window);

