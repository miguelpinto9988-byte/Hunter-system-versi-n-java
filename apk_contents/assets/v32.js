/* V32: equipo coherente, marcos adaptables y transiciones de interfaz. */
'use strict';
const V32UI={profileForm,render,modal,openDiagnostics20};
const Transition32={
 active:new Set(),
 cancel(){for(const a of this.active)a.cancel();this.active.clear();},
 play(nodes){
  if(!Back25.enabled()||!document.documentElement.classList.contains('ready20')||document.hidden)return;
  for(const node of nodes){
   if(!node||typeof node.animate!=='function')continue;
   // Opacity leaves every frame, fixed layer and demonstration at its real position.
   const a=node.animate([{opacity:.96},{opacity:1}],{duration:160,easing:'ease-out'});
   this.active.add(a);a.finished.then(()=>this.active.delete(a),()=>this.active.delete(a));
  }
 }
};
profileForm=function(initial=false){
 const box=document.createElement('div');box.innerHTML=V32UI.profileForm(initial);
 for(const block of box.querySelectorAll('.profile-block'))if(block.querySelector('h3')?.textContent.trim()==='Objetivo y equipo')block.querySelector(':scope > .step-number')?.remove();
 const form=box.querySelector('#profileForm');if(form)gymFields23(form);
 return box.innerHTML;
};
render=function(){if(Runtime20.initializing)return;
 Transition32.cancel();V32UI.render();
 document.querySelectorAll('.brand small').forEach(n=>n.textContent='SISTEMA DE SOMBRAS');
 if(!$('#modal').open)Transition32.play([$('#app .scene')||$('#app main')]);
};
modal=function(html){
 Transition32.cancel();V32UI.modal(html);ModalViewport.fit();
 Transition32.play([...$('#modal').children].filter(n=>!n.matches('button,.window-actions26,.window-frame26,.window-particles28,.ambient-particles29')));
};
openDiagnostics20=function(){V32UI.openDiagnostics20();for(const p of $('#modal').querySelectorAll('p'))p.textContent=p.textContent.replace('Versión 30.0.','Versión 32.0.');};
$('#modal').addEventListener('hunter:modal-fit',()=>{Evolution28.layout();Ambient29.sync();});
$('#modal').addEventListener('close',()=>Transition32.cancel());
document.addEventListener('visibilitychange',()=>{if(document.hidden)Transition32.cancel();});
matchMedia('(prefers-reduced-motion: reduce)').addEventListener?.('change',e=>{if(e.matches)Transition32.cancel();});
window.HunterV32={Transition32};state.appVersion=32;save();render();
