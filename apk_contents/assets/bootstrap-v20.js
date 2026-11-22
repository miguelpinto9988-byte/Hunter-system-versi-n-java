/* Fondo estable y protección de formularios antes de cargar las capas de UI. */
(function(){
 'use strict';
 const root=document.documentElement;root.classList.add('booting20');
 const hues={blue:202,pink:330,violet:267,emerald:151,amber:36,coral:15,red:348,teal:175,ice:215,gold:51};
 try{const saved=JSON.parse(localStorage.getItem('hunter.system.v1')||'{}'),p=saved.profile||{},key=p.theme in hues?p.theme:p.gender==='female'?'pink':'blue',palette=saved.appearance33?.palette||'bruma',h=hues[key],base=(h+(palette==='umbral'?24:palette==='lunar'?-22:0)+360)%360,saturation=palette==='umbral'?27:palette==='lunar'?22:24;root.dataset.theme=key;root.style.setProperty('--bg',`hsl(${base} ${saturation}% 8%)`);root.style.setProperty('--text',`hsl(${base} 22% 86%)`);root.style.setProperty('--accent',`hsl(${h} 43% 77%)`);}catch(e){}
 const diagnostics={errors:[],submissions:0,blockedNavigations:0,initializing:true};window.Runtime20=diagnostics;
 window.addEventListener('error',event=>{diagnostics.errors.push({message:String(event.message||'Error de interfaz'),source:String(event.filename||'').split('/').pop(),time:new Date().toISOString()});diagnostics.errors=diagnostics.errors.slice(-20);});
 // Se cancela la navegación antes del manejador de cada formulario. El
 // manejador sigue ejecutándose para guardar, validar o avanzar en la app.
 document.addEventListener('submit',event=>{event.preventDefault();diagnostics.submissions++;},true);
 document.addEventListener('click',event=>{const a=event.target.closest?.('a[href]');if(!a)return;let url;try{url=new URL(a.href,location.href);}catch(e){return;}if(url.origin===location.origin&&url.pathname!==location.pathname&&!a.hasAttribute('download')){event.preventDefault();diagnostics.blockedNavigations++;}},true);
})();
