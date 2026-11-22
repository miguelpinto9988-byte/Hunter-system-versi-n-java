/* Interfaz V30: mismo perfil, compañero flotante, 1000 demostraciones. */
'use strict';
const V30UI={render,openCoach,profileForm,technique};
function positionCompanion30(){const c=$('#companion'),nav=$('.nav');if(!c)return;if(c.parentElement===nav)$('#app').append(c);c.classList.remove('companion-dock26');c.classList.add('companion-floating30');nav?.classList.remove('nav-dock26');c.setAttribute('aria-label','Abrir asistente '+companionDisplayName());document.documentElement.style.setProperty('--nav-height30',Math.max(60,nav?.getBoundingClientRect().height||78)+'px');}
render=function(){if(Runtime20.initializing)return;V30UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='SISTEMA DE SOMBRAS');positionCompanion30();
 if(state.profile&&tab==='profile')for(const p of $('#app main')?.querySelectorAll('p')||[])if(/demostraciones 2D HD locales/.test(p.textContent))p.textContent='1200 demostraciones 2D específicas sin conexión, con instrucciones, apoyos y cadencias.';
};
window.addEventListener('resize',positionCompanion30,{passive:true});
vectorPaint=function(){const s=vectorSession,c=$('#vectorCanvas');if(!s||!c)return;drawVector(c,s.id,s.frame,s.mirror);$('#vectorRange').value=Math.floor(s.frame)%48;$('#vectorPhase').textContent=Motion2D18.label(s.id,s.frame);};
const panelBefore30=vectorPanel;
const paintBefore30=paintGuide11;
paintGuide11=function(){paintBefore30();const id=guide11?.steps[guide11.index]?.id;if(id)Motion30.preload(id);};
const migrateBefore30=migrateGuide22;
migrateGuide22=function(day,steps,saved){const old=migrateBefore30(day,steps,saved);if(old||!saved||!Number.isInteger(saved.index))return old;
 // V29 eligió el cardio con el filtro anterior. Reconoce su firma completa,
 // conserva el paso por itemIndex/setIndex y aplica el cardio compatible actual.
 if(!steps[0]?.cardio24||!steps.at(-1)?.cardio24)return null;
 const ids=['jog','rope','heelback','box','fastmarch','heeldig','sidestep','seatedpunch','seatedmarch','seatedstep','walk'];
 for(const warm of ids)for(const cool of ids){const before=steps.map(s=>({...s}));before[0]={...before[0],id:warm,label:'Calentamiento cardiovascular · '+E.byId(warm).name};before[before.length-1]={...before.at(-1),id:cool,label:'Cardio suave final · '+E.byId(cool).name};if(saved.signature!==RoutineSequence.signature(before))continue;const index=anchorStep22(before,saved.index,steps);if(index<0)return null;return {...saved,signature:RoutineSequence.signature(steps),index,remaining:Math.min(saved.remaining||0,steps[index]?.seconds||0)};}
 return null;
};
vectorPanel=function(e){const view=Motion30.get(e.id).model.view21;return panelBefore30({...e,view21:{...view,reason:'Observa el plano principal, los apoyos y el recorrido de esta variante.'}}).replace('Las variantes de ritmo comparten el movimiento base; consulta sus instrucciones.','Cada ficha tiene una demostración con su recorrido y cadencia. Las pausas y el retorno lento se muestran dentro del ciclo.');};
offlineCard20=function(){return '';};Offline20.count=()=>1000;
technique=function(id){V30UI.technique(id);Motion30.preload(id);$('#modal .offline-demo20')?.remove();const panel=$('#modal .motion-card18');if(panel){panel.dataset.demonstration30=id;panel.insertAdjacentHTML('beforeend','<p class="demo-source30">Demostración local de esta ficha · '+esc(E.byId(id).sides?'Completa ambos lados según la dosis.':'Conserva los apoyos mostrados.')+'</p>');}UI26.schedule();};
openCoach=function(){V30UI.openCoach();const host=$('#animatedCompanion');if(host){host.style.width='';host.style.height='';}const heading=$('#modal .eyebrow');if(heading&&/asistente/.test(heading.textContent.toLowerCase()))heading.textContent=companionDisplayName()+' / ASISTENTE LOCAL';$('#modal').querySelectorAll('.gesture-controls,.companion-picker').forEach(n=>n.remove());UI26.schedule();};
openDiagnostics20=function(){modal('<section><h2>Estado del contenido local</h2><p>'+E.exercises.length+' fichas con demostración 2D propia · '+Catalog.routines.length+' rutinas.</p><p>Versión 30.0. '+Runtime20.errors.length+' errores de interfaz registrados en esta apertura.</p><p>La ilustración explica la técnica; no observa tu postura. Las rutinas usan objetivos, equipo y restricciones declaradas; la revisión semanal usa tus series confirmadas y el esfuerzo registrado.</p></section>');};
reveal20=function(){Boot30.complete();};
state.appVersion=30;save();render();
