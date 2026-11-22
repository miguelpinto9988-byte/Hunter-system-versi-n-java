/* V24: regreso entre ventanas, cardio guiado e intención conjunta del perfil. */
'use strict';
const V24UI={profileForm,submitProfile,currentCycle,allRoutines,routineDetail,paintGuide11,openCoach,render,answerCoach,goalsView,modal,startGuide11,technique,closeCoach};
const Nav24={stack:[],restoring:false,guide:null,vector:null};
function snapshotModal24(){const d=$('#modal');return {nodes:[...d.childNodes],scroll:d.scrollTop,guide:d.querySelector('#guidedSlot')?(Nav24.guide||guide11):null,vector:d.querySelector('#vectorCanvas')?vectorSession:null,coach:!!d.querySelector('.coach-panel')};}
function modalButtons24(){const d=$('#modal');if(!d.querySelector('.modal-back24')){const b=document.createElement('button');b.className='modal-back24';b.type='button';b.setAttribute('aria-label','Regresar a la ventana anterior');b.title='Regresar';b.textContent='←';b.onclick=backModal24;const x=d.querySelector('.modal-close-x');if(x)x.after(b);else d.prepend(b);}}
const decorateBefore24=decorateModal;decorateModal=function(type){decorateBefore24(type);modalButtons24();};
modal=function(html){const d=$('#modal');if(d.open&&!Nav24.restoring)Nav24.stack.push(snapshotModal24());else if(!d.open)Nav24.stack=[];V24UI.modal(html);modalButtons24();};
startGuide11=function(day,record){Nav24.guide=guide11;const saved=record?state.guidedSessions[day.date]:null;try{
 V24UI.startGuide11(day,record);
 if(day.sourceSequence){const steps=RoutineSequence.expand(day),signature=RoutineSequence.signature(steps),usable=saved?.signature===signature;guide11.steps=steps;guide11.signature=signature;guide11.index=usable?Math.min(saved.index,steps.length):0;guide11.remaining=usable?Math.min(saved.remaining||0,steps[guide11.index]?.seconds||0):steps[0]?.seconds||0;paintGuide11();}
}finally{Nav24.guide=guide11;}};
function backModal24(){const d=$('#modal');if(!d.open)return;pauseGuide11();
 const previous=Nav24.stack.pop();if(!previous){closeActiveModal();go('home');return;}
 // Preserve the actual DOM nodes, field values and event listeners of the parent.
 releaseCompanion();guideFrame11++;for(const a of staticActors14.values())a.pause();clearGallery13();Nav24.restoring=true;
 try{d.replaceChildren(...previous.nodes);delete d.dataset.guide;modalButtons24();ModalViewport.begin();
  if(previous.guide){guide11=previous.guide;Nav24.guide=guide11;guide11.running=false;d.dataset.guide='true';paintGuide11();}
  if(previous.vector){vectorSession=previous.vector;vectorSession.playing=false;vectorPaint();}
  if(previous.coach)mountPanelCompanion(selectedCompanion());
  ModalViewport.fit();d.scrollTop=previous.scroll;
 }finally{Nav24.restoring=false;}
}
$('#modal').addEventListener('close',()=>{if(!$('#modal').open){Nav24.stack=[];Nav24.guide=null;}});
$('#modal').addEventListener('cancel',event=>{event.preventDefault();event.stopImmediatePropagation();backModal24();},true);

profileForm=function(initial=false){const box=document.createElement('div');box.innerHTML=V24UI.profileForm(initial);const f=box.querySelector('form'),select=f.elements.experience,value=state.profile?.experience||(f.elements.difficulty.value==='basic'?'beginner':'regular');
 const label=select.closest('.field')||select.closest('label'),hidden=document.createElement('input');hidden.type='hidden';hidden.name='experience';hidden.id='experience';hidden.value=value;
 if(label){label.replaceWith(hidden);}else select.replaceWith(hidden);
 f.elements.finalGoal.setAttribute('oninput','goalSelection14(this.form,true)');f.elements.goalGroup.setAttribute('onchange','goalSelection14(this.form,true)');
 goalSelection14(f,true);return box.innerHTML;
};
function goalSelection14(form,fromText=false){const p={...state.profile,goalGroup:form.elements.goalGroup.value,finalGoal:form.elements.finalGoal.value,focus:form.elements.focus.value},i=GoalContext24.interpret(p),target=i.target;
 form.elements.finalTarget.value=target;form.elements.goal.value=target;
 if(i.focus!=='full')form.elements.focus.value=i.focus;
 for(const k of Object.keys(i.inferred))if(form.elements['r_'+k])form.elements['r_'+k].checked=true;
 const explain=form.querySelector('#objectiveExplanation');if(explain)explain.textContent=GoalPlans.plans[target].why;
 const out=form.querySelector('#intentResult');if(out)out.innerHTML='<b>'+esc(GoalGroups14.groups[i.chosen].name)+'</b><p>'+esc(i.reason)+'</p><p><b>Medición:</b> '+esc(GoalGroups14.groups[i.chosen].measure)+'</p>'+(i.personalTitles.length?'<small>También considera '+i.personalTitles.length+' objetivos personales activos.</small>':'')+(i.medical?'<p>El texto menciona salud. Completa las zonas e indicaciones en Lesiones y salud.</p>':'');
}
interpretGoal=function(form){goalSelection14(form,true);};
submitProfile=function(ev){const f=ev.target;f.elements.experience.value=f.elements.difficulty.value==='basic'?'beginner':'regular';V24UI.submitProfile(ev);};
levelHint19=function(form){const n=TrainingProgression19.level({difficulty:form.elements.difficulty.value}),node=form.querySelector('#levelHint19');if(node)node.textContent=['Básico: patrones de apoyo y bilaterales, con calibración inicial.','Intermedio: movimientos de suelo, control unilateral y cadencia.','Avanzado: palancas más exigentes, variantes a una pierna y control de la carga.'][n];if(form.dataset.restTouched19!=='true')form.elements.restSeconds.value=String([30,60,90][n]);if(form.elements.experience?.type==='hidden')form.elements.experience.value=n===0?'beginner':'regular';};
currentCycle=function(){const c=V24UI.currentCycle();if(!c||!state.profile)return c;const key=JSON.stringify([24,state.profile,GoalContext24.personal()]);if(c.planningKey24!==key){Training23.assign(c,state.profile,state.logs,E.dateKey(),state.cycles.filter(x=>x.start<c.start).at(-1));c.planningKey24=key;c.goalContext24=GoalContext24.interpret(state.profile);save();}return c;};
goalsView=function(){return V24UI.goalsView().replace('Los objetivos son tu lista personal. No suman repeticiones ni modifican automáticamente la intensidad de las rutinas.','Los objetivos pendientes complementan tu meta del perfil al organizar las próximas sesiones. No suman repeticiones ni aumentan la carga por sí solos. Las sesiones iniciadas conservan su registro.');};

const Warm24={
 key(e){const id=e.baseId||e.id;if(/box|shadow|punch/.test(id))return 'boxing';if(/^(jog|heelback|highknees)$/.test(id))return 'running';if(/march/.test(id))return 'marching';return id;},
 select(day,p=state.profile){
  if(!day.items?.length||!p)return null;const ap=GoalPlans.adjusted(p),used=new Set(day.items.map(x=>E.byId(x.id)?.baseId||x.id)),usedPatterns=new Set(day.items.map(x=>E.byId(x.id)).filter(e=>e?.category==='Cardio').map(e=>Warm24.key(e)));
  let candidates=['jog','rope','heelback','box','fastmarch','heeldig','sidestep','seatedpunch','seatedmarch','seatedstep'].map(id=>E.byId(id)).filter(e=>e&&!used.has(e.baseId)&&!usedPatterns.has(Warm24.key(e))&&Adapt.allowed(e,ap)&&(!OwnRoutines18.gentle(ap)||e.impact!=='Con saltos'));
  const standing=candidates.filter(e=>!e.seated);if(standing.length)candidates=standing;
  if(!candidates.length)return {blocked:true,reason:'No hay cardio compatible con estas restricciones. Revisa las indicaciones antes de entrenar.'};
  const seed=[...(day.title||day.id||'')].reduce((n,c)=>n+c.charCodeAt(0),0),e=candidates[seed%candidates.length];
  const cool=E.byId(['jog','rope','heelback'].includes(e.id)&&!used.has('walk')&&Adapt.allowed(E.byId('walk'),ap)?'walk':e.id);
  return {warm:{id:e.id,seconds:300,label:'Calentamiento cardiovascular · '+e.name},cool:{id:cool.id,seconds:300,label:'Cardio suave final · '+cool.name},reason:'Cinco minutos progresivos al inicio y cinco minutos bajando el ritmo al final. Mantén un ritmo que te permita hablar.'};
 },
 step(x,kind,blocks){return {kind,id:x.id,label:x.label,unit:'sec',target:x.seconds,seconds:x.seconds,blockIndex:kind==='preparation'?-1:blocks,blockCount22:blocks,cardio24:true};}
};
const expandBlocks24=BlockSession22.expand;
const migrateBefore24=migrateGuide22;
migrateGuide22=function(day,steps,saved){const migrated=migrateBefore24(day,steps,saved);if(migrated)return migrated;if(!saved)return null;
 const old=expandBlocks24(day,BlockSession22.mode(state.profile),state.profile||{})?.steps;if(!old||saved.signature!==RoutineSequence.signature(old))return null;
 let index=anchorStep22(old,saved.index,steps);if(index<0)index=0;const previous=old[saved.index],same=previous?.kind===steps[index]?.kind;
 return {...saved,signature:RoutineSequence.signature(steps),index,remaining:same?Math.min(saved.remaining||0,steps[index]?.seconds||0):steps[index]?.seconds||0};
};
BlockSession22.expand=function(day,mode,p){const layout=expandBlocks24(day,mode,p);if(!layout?.steps.length)return layout;const warm=Warm24.select(day,p);layout.cardio24=warm;
 if(warm&&!warm.blocked){layout.steps[0]=Warm24.step(warm.warm,'preparation',layout.blocks.length);layout.steps[layout.steps.length-1]=Warm24.step(warm.cool,'cooldown',layout.blocks.length);}
 else if(warm?.blocked){layout.steps[0].label=warm.reason;layout.steps[0].seconds=0;}
 return layout;
};
const expandSequence24=RoutineSequence.expand;
RoutineSequence.expand=function(day){const steps=expandSequence24(day);if(day.sourceSequence&&steps.length){const w=Warm24.select(day);if(w&&!w.blocked){steps.unshift(Warm24.step(w.warm,'preparation',day.items.length));steps.push(Warm24.step(w.cool,'cooldown',day.items.length));}}return steps;};
paintGuide11=function(){const g=guide11,s=g?.steps[g.index],kind=s?.kind,record=g?.record,layout=g?.layout22;
 if(s?.cardio24){s.kind='exercise';s.setIndex=0;s.sets=1;g.record=false;g.layout22=null;}
 try{V24UI.paintGuide11();}finally{if(s?.cardio24){s.kind=kind;delete s.setIndex;delete s.sets;g.record=record;g.layout22=layout;paintBlocks22();}}
 $('#guidedSlot')?.querySelectorAll('.offline-demo20').forEach(n=>n.remove());
 const dose=$('#guidedSlot .guided-dose');if(dose){dose.setAttribute('aria-label','Cantidad para este paso');dose.innerHTML='<strong>'+s.target+'</strong><span>'+(s.unit==='sec'?'segundos': 'repeticiones')+(E.sidesOf(s)?' por lado':'')+'</span>';}
 if(s?.cardio24){const article=$('#guidedSlot .guided-step');article.querySelector('.block-heading22')?.remove();article.querySelector('.eyebrow').textContent=kind==='preparation'?'CARDIO · CALENTAMIENTO':'CARDIO SUAVE · FINAL';article.querySelectorAll('details').forEach(n=>n.remove());article.insertAdjacentHTML('beforeend','<p class="notice">'+(kind==='preparation'?'Comienza despacio y aumenta gradualmente, sin llegar a fatigarte.':'Reduce el ritmo progresivamente. Si estás trotando o saltando, pasa a caminar al final.')+' El cardio no marca las series principales.</p>');}
 Nav24.guide=g;persistGuide11();
};
const toggleDemo24=toggleGuidedDemo11;
toggleGuidedDemo11=function(){const s=guide11?.steps[guide11.index],kind=s?.kind;if(s?.cardio24)s.kind='exercise';try{toggleDemo24();}finally{if(s?.cardio24)s.kind=kind;}};
routineDetail=function(id){V24UI.routineDetail(id);const r=allRoutines().find(x=>x.id===id),w=r&&Warm24.select({title:r.name,items:r.items}),quests=$('#modal .routine-items')?.querySelectorAll('.quest');if(!quests?.length||!w)return;
 if(w.blocked){quests[0].textContent=w.reason;return;}
 for(const [node,x]of [[quests[0],w.warm],[quests[quests.length-1],w.cool]])node.innerHTML='<div class="grow"><b>'+esc(x.label)+'</b><small>'+x.seconds+' s · intensidad suave y progresiva</small></div><button type="button" class="btn ghost" onclick="technique(\''+x.id+'\')" aria-label="Ver demostración del cardio">↗</button>';
};
const adjustRoutine24=Training23.adjust;
Training23.adjust=function(r,p){const ap=GoalPlans.adjusted(p),used=new Set(),items=adjustRoutine24(r,ap);return items.map(x=>{let e=E.byId(x.id);if(!Adapt.allowed(e,ap)&&(!r.gymOnly23||p.equipment==='gym')){const options=Training23.pool(ap,['Cardio','Movilidad'].includes(e.category)?e.category==='Cardio'?'cardio':'mobility':ap.focus==='lower'?'legs':ap.focus==='core'?'core': 'full').filter(v=>!used.has(v.baseId));const same=options.filter(v=>v.group===e.group);if((same.length?same:options).length)e=(same.length?same:options)[0];}used.add(e.baseId);return e.id===x.id?x:{...Training23.dose(e,ap,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength'),...(x.side?{side:x.side}:{})};});};
allRoutines=function(){return V24UI.allRoutines().map(r=>{if(!state.profile)return r;const p={...state.profile,difficulty:OwnRoutines18.keys[r.level]||state.profile.difficulty},items=Training23.adjust(r,p);return {...r,items,minutes:OwnRoutines18.estimate(items)+8,goalContext24:GoalContext24.interpret(p).reason};});};
openCoach=function(){V24UI.openCoach();$('#modal .progress-panel20')?.remove();const panel=$('#modal .learning-card'),p=state.profile,i=GoalContext24.interpret(p),next=todayDay();if(panel)panel.innerHTML='<span class="eyebrow">GUÍA PERSONAL</span><h3>'+esc(GoalGroups14.groups[i.chosen].name)+'</h3><p>'+esc(i.reason)+'</p><p>'+esc(Adapt.mode(GoalPlans.adjusted(p)).reason)+'</p>'+(next?.items.length?'<p><b>Hoy:</b> '+esc(next.items.map(x=>E.byId(x.id).name).join(' · '))+'</p><button type="button" class="btn primary" onclick="openGuidedSession11(\''+next.date+'\')">Abrir mi sesión de hoy</button>':'')+'<button type="button" class="btn ghost" onclick="openReference20()">Consultar técnica y fuentes</button>';modalButtons24();};
answerCoach=function(q){if(/objetivo|meta|intenci[oó]n|calent|cardio.*final/i.test(q)){const i=GoalContext24.interpret(state.profile);return companionDisplayName()+': '+i.reason+' La sesión incluye cardio al inicio y cardio suave al final, con selección compatible. Los registros confirmados y el esfuerzo guían la revisión semanal.';}return V24UI.answerCoach(q);};
refreshReminderStatus=function(){if(!$('#nativeStatus')||!window.Android?.notificationStatus)return;try{reminderStatus=JSON.parse(Android.notificationStatus());$('#nativeStatus').textContent=reminderStatus.error||(!reminderStatus.allowed?'Las notificaciones están bloqueadas o pendientes de permiso. Abre Permisos de Android para habilitarlas.':!reminderStatus.enabled?'Permiso concedido. Los recordatorios están desactivados.':'Avisos activados. Próximo aviso aproximado: '+(reminderStatus.next||'pendiente de programación'));}catch(error){$('#nativeStatus').textContent='No se pudo consultar el estado de las notificaciones.';}};
render=function(){if(Runtime20.initializing)return;V24UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='EL DESPERTAR · V24');};
openDiagnostics20=function(){modal('<section><h2>Estado del contenido local · V24</h2><p>'+E.exercises.length+' fichas · '+Catalog.routines.length+' rutinas · '+Offline20.count()+' clips 2D sin conexión.</p><p>Medios locales: '+((OfflineManifest20.bytes||0)/1000000).toFixed(1)+' MB. Cardio guiado, bloques y descansos de 30, 60 o 90 segundos.</p><p>'+Runtime20.errors.length+' errores de interfaz registrados en esta apertura.</p><p>El intérprete local combina la elección del perfil, el objetivo escrito y los objetivos personales activos. Los registros confirmados y el esfuerzo alimentan la revisión semanal.</p><p class="footnote">El filtro usa lesiones e indicaciones declaradas; no diagnostica ni prescribe un tratamiento.</p></section>');};
window.HunterV24={Nav24,Warm24};state.appVersion=24;currentCycle();save();render();syncNative();
