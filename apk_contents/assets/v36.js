/* V36: calendario vivo, pensamiento desde la cabeza y ventanas según contenido. */
'use strict';
const V36UI={openCoach,backModal24,modal,openDiagnostics20};
changeMonth=function(delta){
 if(!Number.isFinite(Number(delta)))return;
 const date=new Date(calendarYear,calendarMonth+Number(delta),1);calendarMonth=date.getMonth();calendarYear=date.getFullYear();
 const panel=$('#modal[open] .month-panel');
 if(panel){
  const box=document.createElement('template');box.innerHTML=monthPanel();
  document.querySelectorAll('.month-panel').forEach(old=>old.replaceWith(box.content.firstElementChild.cloneNode(true)));
  $('#modal').scrollTop=0;modalButtons24();UI26.schedule();ModalViewport.fit();
  $('#modal .month-panel').querySelector(Number(delta)<0?'[aria-label="Mes anterior"]':'[aria-label="Mes siguiente"]')?.focus({preventScroll:true});
 }else if(tab==='plan')render();else openMonthlyCalendar();
};
// Sin frecuencia impuesta a requestAnimationFrame. El sistema decide su ritmo.
FramePacer34.target=null;FramePacer34.shouldDraw=()=>true;Performance34.target=null;
const Thought36={
 current:null,pending:0,
 // Lateral de la cabeza en las cinco ilustraciones aprobadas.
 heads:{knight:[.54,.31],warrior:[.54,.30],bear:[.63,.34],insect:[.57,.40],scientist:[.57,.26]},
 clear(){this.current?.observer?.disconnect();this.current=null;cancelAnimationFrame(this.pending);this.pending=0;},
 attach(){
  const host=$('#modal .shadow-showcase33'),actor=host?.querySelector('#animatedCompanion'),img=actor?.querySelector('img'),cloud=host?.querySelector('.shadow-thought34');
  if(!host||!actor||!img||!cloud){this.clear();return;}
  if(this.current?.host===host){this.schedule();return;}
  this.clear();host.classList.add('thought-scene36');cloud.querySelectorAll('.thought-trail34').forEach(n=>n.remove());host.querySelectorAll('.head-trail36').forEach(n=>n.remove());
  const trail=document.createElement('div');trail.className='head-trail36';trail.setAttribute('aria-hidden','true');
  for(let i=0;i<3;i++){const dot=document.createElement('i');dot.style.setProperty('--dot36',i);trail.append(dot);}host.append(trail);
  const entry={host,actor,img,cloud,trail,observer:null};this.current=entry;
  if(typeof ResizeObserver==='function'){entry.observer=new ResizeObserver(()=>this.schedule());entry.observer.observe(host);entry.observer.observe(actor);entry.observer.observe(cloud);}
  img.addEventListener('load',()=>{if(this.current===entry)this.schedule();},{once:true});this.layout();
 },
 layout(){
  const e=this.current;if(!e?.host.isConnected)return this.clear();
  const h=e.host.getBoundingClientRect(),a=e.actor.getBoundingClientRect(),c=e.cloud.getBoundingClientRect(),style=getComputedStyle(e.img);
  // Las cajas de layout no dependen del balanceo o reverencia del avatar.
  const width=parseFloat(style.width)||a.width,height=parseFloat(style.height)||a.height;
  const nw=e.img.naturalWidth||512,nh=e.img.naturalHeight||768,scale=Math.min(width/nw,height/nh),offset=parseFloat(style.left)||0;
  const point=this.heads[selectedCompanion()]||this.heads.knight;
  const start={x:a.left-h.left+offset+(width-nw*scale)/2+point[0]*nw*scale,y:a.top-h.top+(height-nh*scale)/2+point[1]*nh*scale};
  const end={x:c.left-h.left+7,y:c.top-h.top+c.height*.54};
  e.host.style.setProperty('--head-x36',start.x+'px');e.host.style.setProperty('--head-y36',start.y+'px');
  e.trail.querySelectorAll('i').forEach((dot,i)=>{const t=[.2,.47,.75][i];dot.style.left=start.x+(end.x-start.x)*t+'px';dot.style.top=start.y+(end.y-start.y)*t+'px';});
  e.host.dataset.headLinked36='true';
 },
 schedule(){if(this.pending)return;this.pending=requestAnimationFrame(()=>{this.pending=0;this.layout();});}
};
openCoach=function(){V36UI.openCoach();Thought36.attach();};
backModal24=function(){V36UI.backModal24();Thought36.attach();};
modal=function(html){Thought36.clear();V36UI.modal(html);};
$('#modal').addEventListener('close',()=>{if(!$('#modal').open)Thought36.clear();});
window.addEventListener('resize',()=>Thought36.schedule(),{passive:true});
openDiagnostics20=function(){V36UI.openDiagnostics20();$('#modal').querySelectorAll('p').forEach(n=>{n.textContent=n.textContent.replace('Versión 35.0.','Versión 36.0.').replace(/Objetivo de renderizado: 90 FPS\./,'Renderizado sincronizado con la pantalla, sin límite interno de FPS.');});};
window.HunterV36={Thought36,version:36};state.appVersion=36;save();
