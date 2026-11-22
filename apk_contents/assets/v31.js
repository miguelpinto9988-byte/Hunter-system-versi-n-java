/* Sesión compacta y guía flotante sin marco. Conserva nodos, acciones y registro. */
'use strict';
const V31UI={render,paintGuide11,openCoach,setGuideMode22};
const SessionLayout31={state:new Map(),key(){return guide11?.day.date+':'+!!guide11?.record;},capture(){const session=$('#modal .guided-session');if(!session?.dataset.sessionKey31)return;for(const detail of session.querySelectorAll('.session-fold31'))this.state.set(session.dataset.sessionKey31+':'+detail.dataset.section31,detail.open);},remember(detail,name){const key=this.key()+':'+name;detail.open=this.state.get(key)||false;detail.addEventListener('toggle',()=>{if(detail.isConnected)this.state.set(key,detail.open);});},
 wrap(nodes,title,name,root){
  const d=document.createElement('details');d.className='session-fold31';d.dataset.section31=name;
  const summary=document.createElement('summary');summary.textContent=title;d.append(summary);
  const content=document.createElement('div');content.className='session-fold-body31';d.append(content);
  nodes.forEach(n=>content.append(n));root.append(d);this.remember(d,name);return d;
 }
};
function compactGuide31(){
 const g=guide11,slot=$('#guidedSlot'),article=slot?.querySelector('.guided-step'),session=$('#modal .guided-session');if(!g||!session)return;
 session.classList.add('compact-session31');session.dataset.sessionKey31=SessionLayout31.key();
 // El selector mantiene su onchange original y la elección guardada.
 const order=$('#blockSlot22 .block-layout22');if(order&&!order.closest('[data-section31="order"]')){
  const place=order.parentElement,d=SessionLayout31.wrap([order],'Orden de sesión','order',place);
  const label=order.querySelector(':scope>label');if(label?.firstChild?.nodeType===3)label.firstChild.textContent='Organizar bloques';
 }
 if(!article)return;
 const step=g.steps[g.index];article.dataset.stepKind31=step?.kind||'end';
 const prose=[...article.children].filter(n=>(n.tagName==='P'&&!n.classList.contains('warn'))||n.tagName==='DETAILS');
 const instructions=SessionLayout31.wrap(prose,'Cómo realizar el ejercicio','instructions',article);
 if(!prose.length)instructions.remove();
 // No se reconstruye el formulario: sus campos y listeners siguen intactos.
 const demo=article.querySelector(':scope>.guided-demo'),dose=article.querySelector(':scope>.guided-dose'),clock=article.querySelector(':scope>.guided-clock');
 if(demo||dose||clock){
  const work=document.createElement('div');work.className='session-work31';
  const stats=document.createElement('div');stats.className='session-stats31';
  if(demo)work.append(demo);if(dose)stats.append(dose);if(clock)stats.append(clock);work.append(stats);
  const title=article.querySelector(':scope>h3');if(title)title.after(work);else article.prepend(work);
  if(!demo)work.classList.add('clock-only31');
 }
 const toggle=article.querySelector(':scope>#guidedDemoToggle'),speeds=article.querySelector(':scope>.playback-speeds,:scope>.motion-speed18,:scope>.playback-controls,:scope>.speed-controls');
 // Las velocidades se conservan, pero pasan a los ajustes desplegables.
 const controls=[toggle,speeds].filter(Boolean);
 for(const n of [...article.children])if(n.classList.contains('model-speed')&&!controls.includes(n))controls.push(n);
 if(controls.length){const d=SessionLayout31.wrap(controls,'Velocidad de demostración','playback',article);d.classList.add('session-playback31');}
 if(instructions.isConnected)article.append(instructions);
 const status=$('#guideCheckStatus19');if(status)status.classList.add('sr-only31');
 const check=article.querySelector('.guide-check19 small');if(check)check.textContent='Marca solo lo que realizaste.';
 const progress=slot.querySelector('.guided-progress');progress?.querySelector(':scope>small')?.classList.add('sr-only31');
 const alarm=clock?.querySelector('[data-alarm-note27]');if(alarm)alarm.classList.add('sr-only31');
 clock?.querySelector('.guide-alarm-test27')?.remove();
}
paintGuide11=function(){SessionLayout31.capture();V31UI.paintGuide11();compactGuide31();};
setGuideMode22=function(mode){SessionLayout31.capture();V31UI.setGuideMode22(mode);};
function shadowAura31(){
 const c=$('#companion');if(!c)return;
 c.classList.add('companion-shadow31');
 c.querySelector(':scope>.ornament26')?.remove();
 if(!c.querySelector('.shadow-aura31')){
  const aura=document.createElement('span');aura.className='shadow-aura31';aura.setAttribute('aria-hidden','true');
  aura.innerHTML='<i></i><i></i><i></i><i></i><i></i><i></i><i></i>';c.prepend(aura);
 }
 c.setAttribute('title','Abrir '+companionDisplayName());c.setAttribute('aria-label','Abrir asistente '+companionDisplayName());
 // El nombre sigue disponible para lectores de pantalla; solo se ve el icono.
 c.querySelectorAll(':scope>span:not(.shadow-aura31)').forEach(n=>n.classList.add('sr-only31'));
}
render=function(){if(Runtime20.initializing)return;V31UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='SISTEMA DE SOMBRAS');if(tab==='home')$('#app .coach-banner')?.remove();shadowAura31();};
openCoach=function(){V31UI.openCoach();shadowAura31();};
document.addEventListener('visibilitychange',()=>document.documentElement.classList.toggle('aura-paused31',document.hidden));
const resizeCompanion31=positionCompanion30;
positionCompanion30=function(){resizeCompanion31();shadowAura31();};
state.appVersion=31;save();render();
