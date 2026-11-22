/* V19: perfil, navegación independiente del registro y compañero automático. */
'use strict';
const V19UI={profileForm,submitProfile,paintGuide11,openCoach,render,answerCoach,allRoutines};
function levelHint19(form){
 const n=TrainingProgression19.level({difficulty:form.elements.difficulty.value}),node=form.querySelector('#levelHint19');
 if(node)node.textContent=[
  'Básico: sentadillas, flexiones de apoyo y patrones bilaterales. Con experiencia, cuatro series de fuerza.',
  'Intermedio: flexión de suelo, pausas, cadencia lenta y mayor control unilateral.',
  'Avanzado: flexiones arquero o pica, trabajo a una pierna y repeticiones de un ciclo y medio. Revisa los requisitos de cada movimiento.'
 ][n];
 if(form.dataset.restTouched19!=='true')form.elements.restSeconds.value=String([30,60,90][n]);
}
profileForm=function(initial=false){
 const box=document.createElement('div');box.innerHTML=V19UI.profileForm(initial);const f=box.querySelector('form'),p=state.profile||{};
 const rest=f.elements.restSeconds;rest.innerHTML=options({30:'30 segundos',60:'60 segundos',90:'90 segundos · máximo'},String(TrainingProgression19.rest({...p,difficulty:f.elements.difficulty.value})));
 rest.setAttribute('onchange','this.form.dataset.restTouched19="true"');
 const level=f.elements.difficulty;level.setAttribute('onchange','levelHint19(this.form)');
 const hint=document.createElement('p');hint.id='levelHint19';hint.className='notice level-hint19';level.closest('label').after(hint);
 const intensity=f.querySelector('.intensity-block');
 intensity.querySelector('p').textContent='Los niveles cambian movimientos, apoyos y cadencia. La revisión semanal aumenta una variable y, al completar su rango, propone una variante compatible. La experiencia inicial, el esfuerzo y las restricciones ajustan la dosis.';
 intensity.querySelector('.footnote').textContent='Si estás empezando, la calibración utiliza dos series y menos repeticiones. Las lesiones y recomendaciones recibidas conservan prioridad sobre la dificultad. Descansos disponibles: 30, 60 y 90 segundos.';
 const picker=f.querySelector('.companion-picker-block');if(picker){picker.querySelectorAll('[name=companionCharacter]').forEach(input=>input.setAttribute('onchange','previewCompanionChoice(this)'));if(initial&&picker.tagName==='DETAILS')picker.open=true;}
 f.dataset.restTouched19=p.restSeconds!==undefined?'true':'false';levelHint19(f);
 return box.innerHTML;
};
submitProfile=function(ev){
 const f=ev.target,n=Number(f.elements.restSeconds.value);if(![30,60,90].includes(n)){ev.preventDefault();return toast('Elige un descanso de 30, 60 o 90 segundos.');}
 V19UI.submitProfile(ev);
};
allRoutines=function(){
 return [...OwnRoutines18.routines.map(r=>{
  const p={...state.profile,difficulty:OwnRoutines18.keys[r.level],restSeconds:r.level===TrainingProgression19.level(state.profile||{})?state.profile?.restSeconds:[30,60,90][r.level]};
  const items=state.profile?OwnRoutines18.adjust(r,p):r.items;return {...r,items,minutes:OwnRoutines18.estimate(items)};
 }),...state.customRoutines.map(r=>{
  const items=r.items.map(x=>({...x,rest:Math.min(90,Math.max(30,Number(x.rest)||30))}));
  return {...r,items};
 })];
};
currentCycle=function(){
 if(!state.profile||!E.eligible(state.profile))return null;
 const c=Weekly.current(state),key=JSON.stringify([19,Object.fromEntries(Object.entries(state.profile).filter(([k])=>k!=='blockMode22'))]),ap=GoalPlans.adjusted(state.profile),now=E.dateKey();
 if(c.planningKey19===key)return c;
 const review=c.reviews?.at(-1)?.action,hasOld=c.engineVersion19!==19||c.planningKey19!==undefined;
 if(review!=='pausar'){
  const fresh=hasOld?E.makeCycle(state.profile,c.start,c.index,null,state.logs):null;
  c.days=c.days.map((d,i)=>{
   if(d.date<now||Weekly.active(state.logs[d.date])||d.tasks?.some(t=>t.done))return d;
   // Las sesiones personalizadas se conservan; las incompatibles sin registro
   // se sustituyen por una selección que vuelve a pasar los filtros.
   const incompatible=d.items.length&&!Adapt.routineCompatible({items:d.items},ap);
   const generated=!d.customized&&!d.routineId||d.sourceSequence||d.creatorAssigned;
   let next=(fresh&&generated||incompatible)?(fresh||E.makeCycle(state.profile,c.start,c.index,null,state.logs)).days[i]:d;
   if(next!==d&&review==='reducir')next.items.forEach(x=>x.target=Math.max(E.unitOf(x)==='sec'?10:2,Math.floor(x.target*.8)));
   if(next!==d||c.engineVersion19!==19)next.items.forEach(x=>x.rest=TrainingProgression19.rest(ap));
   return next;
  });
  RoutinePlanner.organize(c,state.profile,[],OwnRoutines18.routines,state.logs,now);
 }
 c.engineVersion19=19;c.profile=JSON.parse(JSON.stringify(state.profile));c.planningKey19=key;save();return c;
};
function jumpGuide19(index){
 const g=guide11;if(!g||!Number.isInteger(index))return;
 pauseGuide11();guideFrame11++;g.index=Math.max(0,Math.min(g.steps.length,index));g.remaining=g.steps[g.index]?.seconds||0;g.running=false;paintGuide11();
}
moveGuide11=function(delta){if(guide11)jumpGuide19(guide11.index+delta);};
advanceGuide11=function(){moveGuide11(1);};
function storeGuideCheck19(form,completed){
 const g=guide11,s=g?.steps[g.index];if(!g?.record||s?.kind!=='exercise'||!guideValid11())return false;
 if(completed&&!form.reportValidity())return false;
 const e=E.byId(s.id),value=Number(form.elements.value.value),kg=['db','gym'].includes(e.equipment)?Number(form.elements.kg.value):0;
 if(completed&&(!Number.isInteger(value)||value<1||value>(s.unit==='sec'?3600:300)||!Number.isFinite(kg)||kg<0||kg>200))return false;
 const l=getLog(),x=l.items[s.itemIndex];if(x?.id!==s.id||!x.actual[s.setIndex])return false;
 x.actual[s.setIndex]={done:completed,value:completed?value:0,kg:completed?kg:0};x.selfReported=false;x.missionState=x.actual.every(a=>a.done);save();return true;
}
function guideCheck19(input){
 if(!storeGuideCheck19(input.form,input.checked)){input.checked=false;toast('Revisa la cantidad realizada y la disponibilidad de esta sesión.');return;}
 const msg=$('#guideCheckStatus19');if(msg)msg.textContent=input.checked?'Serie marcada como realizada.':'Serie pendiente.';
}
function pendingGuide19(){
 const g=guide11;if(!g)return;
 const f=$('#guideRecord');if(f){f.elements.completed.checked=false;storeGuideCheck19(f,false);}
 const next=g.steps.findIndex((s,i)=>i>g.index&&s.kind==='exercise');jumpGuide19(next<0?g.steps.length:next);
}
confirmGuideSet11=function(ev){
 ev.preventDefault();const f=ev.target;
 if(!f.elements.completed?.checked)return pendingGuide19();
 if(!storeGuideCheck19(f,true))return toast('Revisa la cantidad realizada antes de guardar la serie.');
 advanceGuide11();
};
paintGuide11=function(){
 V19UI.paintGuide11();const g=guide11,s=g?.steps[g.index],f=$('#guideRecord');if(!g)return;
 if(f&&s?.kind==='exercise'){
  const e=E.byId(s.id),a=state.logs[g.day.date]?.items[s.itemIndex]?.actual[s.setIndex];
  f.noValidate=true;f.innerHTML='<label class="guide-check19"><input name="completed" type="checkbox" '+(a?.done?'checked':'')+' onchange="guideCheck19(this)"><span><b>Realicé esta serie</b><small>Solo esta casilla registra la serie como completada.</small></span></label>'+
   '<div class="fields"><label>'+(s.unit==='sec'?'Segundos realizados':'Repeticiones realizadas')+'<input name="value" type="number" min="1" max="'+(s.unit==='sec'?3600:300)+'" step="1" required value="'+(a?.done?a.value:s.target)+'"></label>'+
   '<label>Carga simultánea · kg<input name="kg" type="number" min="0" max="200" step="0.5" value="'+(a?.kg||0)+'" '+(!['db','gym'].includes(e.equipment)?'readonly':'')+'></label></div>'+
   '<p id="guideCheckStatus19" class="footnote" aria-live="polite">'+(a?.done?'Serie marcada como realizada.':'Serie pendiente.')+'</p><button type="submit" class="btn primary full">Guardar y continuar</button>';
  const pending=Array.from($('#guidedSlot').querySelectorAll('button')).find(b=>b.getAttribute('onclick')==='advanceGuide11()');
  if(pending){pending.setAttribute('onclick','pendingGuide19()');pending.textContent='Dejar pendiente y siguiente serie →';}
 }
 $('#guidedSlot')?.querySelectorAll('button:not([type])').forEach(b=>b.type='button');
 if(s?.kind==='rest')s.label='Descanso · '+s.seconds+' segundos';
 if(!s&&g.record){
  const pending=g.steps.map((step,index)=>({step,index})).filter(({step})=>step.kind==='exercise'&&!state.logs[g.day.date]?.items[step.itemIndex]?.actual[step.setIndex]?.done);
  if(pending.length)$('#guidedSlot .card').insertAdjacentHTML('beforeend','<section class="pending-review19"><h4>'+pending.length+' series pendientes</h4>'+pending.map(({step,index})=>'<button type="button" class="btn ghost full" onclick="jumpGuide19('+index+')">'+esc(step.label)+' · serie '+(step.setIndex+1)+' →</button>').join('')+'</section>');
 }
 if($('#guideClockMessage')&&g.remaining<=0)$('#guideClockMessage').textContent='Puedes continuar o repetir el reloj. El recorrido no marca ejercicios como realizados.';
 ModalViewport.reset();
};
const expand19=RoutineSequence.expand;
RoutineSequence.expand=function(day){
 const steps=expand19(day);for(const s of steps)if(s.kind==='rest'){s.seconds=Math.min(90,Math.max(30,Number(s.seconds)||30));s.label='Descanso · '+s.seconds+' segundos';}return steps;
};
openCoach=function(){
 V19UI.openCoach();$('#modal').querySelectorAll('.companion-actions,.motion-controls,button[onclick="openAnimationGallery()"]').forEach(node=>node.remove());
 $('#animatedCompanion')?.setAttribute('aria-label',companionDisplayName()+' · compañero 2D con gestos automáticos');
 $('.coach-intro')?.insertAdjacentHTML('afterend','<p class="footnote automatic-note19">Tu compañero saluda, observa y cambia de gesto automáticamente durante la espera.</p>');
 const learning=$('#modal .learning-card');if(learning)learning.insertAdjacentHTML('beforeend','<p><b>Escalones de movimiento:</b> al completar el rango de un ejercicio, tres registros comparables y un esfuerzo de hasta 7/10 permiten revisar una variante del mismo nivel. Dolor, fatiga, indicaciones y reducción personal tienen prioridad.</p>');
};
openAnimationGallery=function(){
 clearGallery13();modal('<section data-companion-static="true"><h2>Cinco compañeros · animaciones automáticas</h2><p>Entrada y gestos de espera. El compañero del perfil se conserva.</p>'+Object.entries(CompanionNames).map(([key,name])=>'<details class="card" ontoggle="galleryCompanion19(this)"><summary>'+name+'</summary><div class="depth-character-host" data-sample-character="'+key+'"></div></details>').join('')+'</section>');
};
function galleryCompanion19(details){
 const host=details.querySelector('[data-sample-character]'),key=host.dataset.sampleCharacter;
 if(!details.open){galleryActors13.get(key)?.pause();return;}
 let actor=galleryActors13.get(key);if(!actor){actor=new IgrisCharacter(host,key,{automatic:true,reducedMotion:state.profile?.animate===false||matchMedia('(prefers-reduced-motion: reduce)').matches});galleryActors13.set(key,actor);actor.open();}else actor.resume();
}
answerCoach=function(q){
 if(/progre|nivel|dif[ií]cil|exig|evolu|series|repetici/i.test(q)&&!/dolor|lesi[oó]n/i.test(q)){
  const d=todayDay(),review=currentCycle()?.reviews?.at(-1);
  return companionDisplayName()+': Tu nivel '+OwnRoutines18.names[TrainingProgression19.level(state.profile)]+' cambia las variantes del movimiento, además de series y repeticiones. '+(review?.reason||'Reúne tres sesiones completas y comparables con esfuerzo de hasta 7/10 para revisar aumentos semanales.')+' Al alcanzar el rango, se busca una siguiente variante compatible dentro de tu nivel, recalibrando su dosis. Tu sesión incluye: '+(d?.items.map(x=>E.byId(x.id).name).join(', ')||'descanso')+'.';
 }
 return V19UI.answerCoach(q);
};
render=function(){if(Runtime20.initializing)return;V19UI.render();document.querySelectorAll('.brand small').forEach(node=>node.textContent='EL DESPERTAR · V19');};
// Cambio solicitado de descansos para el plan activo; los registros anteriores
// y las copias de perfil de semanas ya realizadas se conservan.
if(state.profile&&state.restVersion19!==19)state.profile.restSeconds=[30,60,90][TrainingProgression19.level(state.profile)];
state.restVersion19=19;state.appVersion=19;currentCycle();save();render();syncNative();
