/* V25: regreso con respuesta visual y fases de cardio de hasta un minuto. */
'use strict';
const V25UI={modal,render,allRoutines,answerCoach,startGuide11,migrateGuide22,backModal24,modalButtons24};
const warmSelectBefore25=Warm24.select;
const Warm25={
 seconds(p=state.profile){
  if(!p)return 30;
  const adjusted=GoalPlans.adjusted(p),reduced=OwnRoutines18.gentle(adjusted);
  const base=reduced?30:[30,45,60][TrainingProgression19.level(p)];
  return Math.max(20,Math.min(60,Math.round(base*TrainingIntensity.scale(p)/5)*5));
 },
 legacy(day,p,mode){
  const warm=warmSelectBefore25(day,p);
  if(day.sourceSequence){
   const steps=expandBefore22(day);
   if(warm&&!warm.blocked){steps.unshift(Warm24.step(warm.warm,'preparation',day.items.length));steps.push(Warm24.step(warm.cool,'cooldown',day.items.length));}
   return steps;
  }
  const layout=expandBlocks24(day,mode,p||{});if(!layout?.steps.length)return [];
  if(warm&&!warm.blocked){layout.steps[0]=Warm24.step(warm.warm,'preparation',layout.blocks.length);layout.steps[layout.steps.length-1]=Warm24.step(warm.cool,'cooldown',layout.blocks.length);}
  else if(warm?.blocked){layout.steps[0].label=warm.reason;layout.steps[0].seconds=0;}
  return layout.steps;
 },
 migrate(day,steps,saved){
  if(!saved)return null;
  if(saved.signature===RoutineSequence.signature(steps))return saved;
  const old=Warm25.legacy(day,state.profile,saved.mode22||BlockSession22.mode(state.profile));
  if(saved.signature!==RoutineSequence.signature(old))return null;
  if(!Number.isInteger(saved.index)||saved.index<0||saved.index>old.length)return null;
  const index=anchorStep22(old,saved.index,steps);if(index<0)return null;
  const previous=old[saved.index],next=steps[index];
  let remaining=previous?.kind===next?.kind?Math.min(saved.remaining||0,next?.seconds||0):next?.seconds||0;
  // Account for cardio already elapsed rather than restarting the shorter phase.
  if(previous?.cardio24&&next?.cardio24){const elapsed=Math.max(0,previous.seconds-(saved.remaining||0));remaining=Math.max(0,next.seconds-elapsed);}
  return {...saved,signature:RoutineSequence.signature(steps),index,remaining};
 },
 minutes(r,p){
  const day={title:r.name,items:r.items,sourceSequence:r.sourceSequence};
  const steps=BlockSession22.expand(day,BlockSession22.mode(p),p||{})?.steps||RoutineSequence.expand(day);
  return Math.ceil(steps.reduce((n,s)=>n+(s.kind==='exercise'&&s.unit!=='sec'&&!s.seconds?OwnRoutines18.workSeconds(s):s.seconds||0),0)/60);
 }
};
Warm24.select=function(day,p=state.profile){
 const selected=warmSelectBefore25(day,p);if(!selected||selected.blocked)return selected;
 const seconds=Warm25.seconds(p);
 return {...selected,warm:{...selected.warm,seconds},cool:{...selected.cool,seconds},reason:seconds+' segundos al inicio y '+seconds+' segundos de cardio suave al final. Aumenta y reduce el ritmo gradualmente, sin forzarlo.'};
};
migrateGuide22=function(day,steps,saved){return Warm25.migrate(day,steps,saved)||V25UI.migrateGuide22(day,steps,saved);};
startGuide11=function(day,record){
 const saved=record?state.guidedSessions[day.date]:null;
 V25UI.startGuide11(day,record);
 // The legacy source route replaces its guide after the regular migration.
 if(day.sourceSequence&&saved&&saved.signature!==guide11.signature){
  const migrated=migrateGuide22(day,guide11.steps,saved);
  if(migrated?.signature===guide11.signature){guide11.index=migrated.index;guide11.remaining=migrated.remaining;paintGuide11();}
 }
};
allRoutines=function(){return V25UI.allRoutines().map(r=>({...r,minutes:Warm25.minutes(r,state.profile)}));};

const Back25={
 animations:[],timer:0,token:0,busy:false,
 enabled(){return state.profile?.animate!==false&&!matchMedia('(prefers-reduced-motion: reduce)').matches;},
 reset(){
  this.token++;clearTimeout(this.timer);this.timer=0;this.busy=false;
  for(const animation of this.animations)animation.cancel();this.animations=[];
 },
 play(){
  if(!this.enabled())return;
  const dialog=$('#modal'),container=dialog.open?dialog:$('#app');
  const children=dialog.open?[...container.children].filter(n=>!n.matches('button,.window-frame26,.window-actions26,.window-particles28,.ambient-particles29')):[...container.querySelectorAll('.scene')];
  if(!children.some(n=>typeof n.animate==='function'))return;
  const token=this.token;this.busy=true;
  // Fixed frames and particle layers keep their viewport coordinates throughout.
  for(const node of children)this.animations.push(node.animate([{opacity:.94},{opacity:1}],{duration:180,easing:'cubic-bezier(.2,.7,.2,1)'}));
  const button=dialog.open?dialog.querySelector('.modal-back24'):null;
  if(button?.animate)this.animations.push(button.animate([{transform:'scale(.88)'},{transform:'scale(1)'}],{duration:180,easing:'ease-out'}));
  this.timer=setTimeout(()=>{if(this.token===token){this.busy=false;this.timer=0;this.animations=[];}},180);
 }
};
modalButtons24=function(){
 V25UI.modalButtons24();const button=$('#modal .modal-back24');if(!button)return;
 button.classList.add('modal-back25');
 if(!button.querySelector('svg'))button.innerHTML='<svg viewBox="0 0 24 24" aria-hidden="true" focusable="false"><path d="M14.5 5.5 8 12l6.5 6.5M8.5 12H20" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/></svg>';
 const label=Nav24.stack.length?'Regresar a la ventana anterior':'Volver a la pantalla principal';
 button.setAttribute('aria-label',label);button.title=label;button.onclick=backModal24;
};
modal=function(html){Back25.reset();V25UI.modal(html);modalButtons24();};
backModal24=function(){
 if(!$('#modal').open||Back25.busy&&Back25.enabled())return;
 Back25.reset();V25UI.backModal24();
 // Navigation is synchronous; the effect never delays restoring fields or logs.
 modalButtons24();$('#modal').open&&$('#modal .modal-back24')?.focus({preventScroll:true});Back25.play();
};
$('#modal').addEventListener('close',()=>{if(!$('#modal').open)Back25.reset();});
answerCoach=function(q){
 if(/calent|cardio.*final|duraci[oó]n.*cardio/i.test(q))return companionDisplayName()+': El calentamiento dura '+Warm25.seconds()+' segundos según tu nivel y la reducción elegida, con un máximo de 60 segundos. El cardio suave final usa la misma duración. Las series principales y sus descansos se conservan.';
 return V25UI.answerCoach(q);
};
render=function(){if(Runtime20.initializing)return;V25UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='EL DESPERTAR · V25');};
openDiagnostics20=function(){modal('<section><h2>Estado del contenido local · V25</h2><p>'+E.exercises.length+' fichas · '+Catalog.routines.length+' rutinas · '+Offline20.count()+' clips 2D sin conexión.</p><p>Calentamiento y cardio suave final: '+Warm25.seconds()+' segundos cada uno. Máximo de 60 segundos por fase; descansos entre series de 30, 60 o 90 segundos.</p><p>'+Runtime20.errors.length+' errores de interfaz registrados en esta apertura.</p><p>Medios locales: '+((OfflineManifest20.bytes||0)/1000000).toFixed(1)+' MB.</p><p class="footnote">La selección usa objetivos, nivel, equipo y restricciones declaradas. No diagnostica ni prescribe rehabilitación.</p></section>');};
window.HunterV25={Warm25,Back25};state.appVersion=25;save();render();
