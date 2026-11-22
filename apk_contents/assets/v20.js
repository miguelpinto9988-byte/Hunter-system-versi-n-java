/* UI estable, demostraciones HD locales y decisiones de progreso visibles. */
'use strict';
const V20UI={render,openCoach,paintGuide11,technique,applyTheme,profileForm};
const Offline20={players:new Set(),count:()=>Object.keys(window.OfflineManifest20?.clips||{}).length};
function offlineCard20(id){
 const base=Motion2D18.get(id).base,clip=window.OfflineManifest20?.clips?.[base];if(!clip)return '';
 return '<details class="offline-demo20 card" ontoggle="toggleOffline20(this)"><summary>Demostración HD · disponible sin conexión</summary><div class="offline-player20"><video playsinline muted loop preload="none" poster="media2d/'+base+'.svg" data-src="offline2d/'+base+'.mp4" aria-label="Demostración 2D HD de '+esc(E.byId(id).name)+'"></video></div><div class="action-pair"><button type="button" class="btn" onclick="playOffline20(this)">Reproducir / pausar</button><label>Velocidad<select onchange="this.closest(\'.offline-demo20\').querySelector(\'video\').playbackRate=Number(this.value)"><option value="0.65">Lenta</option><option value="1" selected>Normal</option><option value="1.4">Rápida</option></select></label></div><p class="footnote">Dos ciclos del mismo movimiento 2D en alta resolución. '+(E.byId(id).variant!=='base'?'Esta variante comparte el movimiento base; sigue su cadencia e instrucciones. ':'')+'La velocidad del vídeo no cambia el reloj de la sesión.</p><p class="offline-status20" aria-live="polite"></p></details>';
}
function toggleOffline20(details){
 const video=details.querySelector('video');if(!video)return;
 if(!details.open){video.pause();return;}
 if(!video.src){video.src=video.dataset.src;video.addEventListener('error',()=>{details.querySelector('.offline-status20').textContent='El reproductor no pudo abrir este clip. La demostración vectorial y las instrucciones siguen disponibles.';});}
 Offline20.players.add(video);
 if(state.profile?.animate!==false&&!matchMedia('(prefers-reduced-motion: reduce)').matches)video.play().catch(()=>{details.querySelector('.offline-status20').textContent='Pulsa Reproducir para iniciar la demostración.';});
}
function playOffline20(button){const video=button.closest('.offline-demo20').querySelector('video');if(video.paused)video.play().catch(()=>toast('El reproductor no pudo iniciar. Consulta el dibujo vectorial.'));else video.pause();}
function cleanupOffline20(){for(const video of Offline20.players){if(!video.isConnected||!$('#modal').open){video.pause();video.removeAttribute('src');video.load();Offline20.players.delete(video);}}}
document.addEventListener('visibilitychange',()=>{if(document.hidden)for(const video of Offline20.players)video.pause();});
$('#modal').addEventListener('close',cleanupOffline20);new MutationObserver(cleanupOffline20).observe($('#modal'),{childList:true,subtree:true});
function bindGuide20(){
 for(const form of $('#guidedSlot')?.querySelectorAll('form')||[]){
  const record=form.id==='guideRecord';form.removeAttribute('onsubmit');form.addEventListener('submit',event=>{event.preventDefault();try{if(record)confirmGuideSet11(event);else finishGuide11(event);}catch(error){Runtime20.errors.push({message:error.message,source:'guided-session',time:new Date().toISOString()});toast('El paso no pudo guardarse. Tu registro se conserva; vuelve a intentarlo.');}});
  form.querySelectorAll('button:not([type])').forEach(button=>button.type='button');
 }
}
paintGuide11=function(){V20UI.paintGuide11();bindGuide20();const step=guide11?.steps[guide11.index];if(step?.kind==='exercise')$('#guidedSlot .guided-demo')?.insertAdjacentHTML('afterend',offlineCard20(step.id));};
technique=function(id){V20UI.technique(id);$('#modal .motion-card18')?.insertAdjacentHTML('afterend',offlineCard20(id));};
function progressPanel20(){
 const cycle=currentCycle(),report=ProgressMonitor20.summary(state.logs,cycle?.days||[],state.profile,E.dateKey()),review=cycle?.reviews?.at(-1);
 return '<section class="card progress-panel20"><span class="eyebrow">SEGUIMIENTO LOCAL · TUS REGISTROS</span><h3>'+report.completedSessions+' sesiones comparables · '+report.confirmedSeries+' series confirmadas</h3><p>'+esc(review?.reason||'La revisión semanal conserva el plan mientras reúne registros completos y esfuerzo.')+'</p>'+report.analysis.map(x=>'<details><summary>'+esc(x.name)+' · '+x.records+' registros</summary><p>'+x.sets+' series × '+x.target+' '+(x.unit==='sec'?'segundos':'repeticiones')+'. '+esc(x.reason)+'</p><p>'+ (x.estimate?'Esfuerzo estimado: '+x.estimate.effort+'/10 con '+x.estimate.neighbors+' vecinos comparables.':'Estimación pendiente: faltan al menos tres registros comparables.')+'</p>'+ (x.nextId!==x.id?'<p>Variante propuesta: '+esc(E.byId(x.nextId)?.name||x.nextId)+'.</p>':'')+'</details>').join('')+'<p class="footnote">Se usa un estimador kNN local y reglas de progresión. No genera experiencia ficticia ni diagnostica lesiones. Los cambios se aplican al organizar la semana; la vista muestra la evaluación actual.</p></section>';
}
openCoach=function(){V20UI.openCoach();$('#modal .learning-card')?.insertAdjacentHTML('afterend',progressPanel20());};
function openReference20(){modal('<section><span class="eyebrow">BIBLIOTECA LOCAL DE ENTRENAMIENTO</span><h2>Tu técnica y tu progreso.</h2>'+TrainingReference20.chapters.map(x=>'<article class="card"><h3>'+esc(x.title)+'</h3><p>'+esc(x.text)+'</p>'+(x.source?'<a href="'+esc(x.source)+'" target="_blank" rel="noopener">Consultar fuente ↗</a>':'')+'</article>').join('')+'</section>');}
function openDiagnostics20(){modal('<section><h2>Estado del contenido local</h2><p>'+E.exercises.length+' fichas · '+OwnRoutines18.routines.length+' rutinas · '+Offline20.count()+' clips 2D HD.</p><p>Versión 22.0. '+Runtime20.submissions+' envíos de formulario gestionados dentro de la interfaz.</p><p>'+Runtime20.errors.length+' errores de interfaz registrados durante esta apertura.</p>'+Runtime20.errors.map(x=>'<p>'+esc(x.source)+' · '+esc(x.message)+'</p>').join('')+'<p class="footnote">Este panel registra errores de interfaz durante la ejecución. La instalación y el comportamiento del dispositivo necesitan comprobación en Android.</p></section>');}
function themeNative20(){if(!window.Android?.setThemeBackground)return;const rgb=getComputedStyle(document.body).backgroundColor.match(/\d+/g);if(rgb?.length>=3)HunterNative3875.setThemeBackground('#'+rgb.slice(0,3).map(x=>Number(x).toString(16).padStart(2,'0')).join(''));}
applyTheme=function(p){if(Runtime20.initializing)return;V20UI.applyTheme(p);themeNative20();};
render=function(){if(Runtime20.initializing)return;V20UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='EL DESPERTAR · V21');if(state.profile&&tab==='profile')$('#app main')?.insertAdjacentHTML('beforeend','<section class="card"><h3>Contenido y seguimiento sin conexión</h3><p>'+Offline20.count()+' demostraciones 2D HD locales, además de los dibujos vectoriales y sus instrucciones.</p><button type="button" class="btn" onclick="openReference20()">Biblioteca de entrenamiento</button><button type="button" class="btn ghost" onclick="openDiagnostics20()">Estado de la aplicación</button></section>');themeNative20();};
// Tipos explícitos incluso en botones heredados dentro de un formulario.
function normalizeButtons20(root){root?.querySelectorAll('form button:not([type])').forEach(button=>button.type='button');}
new MutationObserver(records=>{for(const record of records)for(const node of record.addedNodes){if(node.nodeType!==1)continue;if(node.matches('button:not([type])')&&node.closest('form'))node.type='button';normalizeButtons20(node);}}).observe($('#app'),{childList:true,subtree:true});
normalizeButtons20(document);state.appVersion=21;save();render();
function reveal20(){return Boot30.complete();}
// El único arranque se confirma al terminar el último módulo de interfaz.
