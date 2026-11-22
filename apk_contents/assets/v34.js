/* V34: composición estable, pensamiento diario y renderizado sin límite de 30 FPS. */
'use strict';
const V34UI={render,home,openCoach,applyTheme,modal,header,openDiagnostics20};
const FramePacer34={
 target:90,next:0,reset(){this.next=0;},
 shouldDraw(time){const interval=1000/this.target;if(!this.next){this.next=time+interval;return true;}if(time+.35<this.next)return false;this.next+=interval;if(time-this.next>interval*3)this.next=time+interval;return true;}
};
const Performance34={
 target:90,routeChanging:false,surfaceOpening:false,lastTab:null,profileReady:false,intervals:[],lastFrame:0,nativeTheme:null,themeFrame:0,draws:0,
 sample(time){if(this.lastFrame){const dt=time-this.lastFrame;if(dt>=3&&dt<=100){this.intervals.push(dt);if(this.intervals.length>180)this.intervals.shift();}}this.lastFrame=time;},
 observed(){if(this.intervals.length<30)return null;return Math.round(1000/(this.intervals.reduce((a,b)=>a+b,0)/this.intervals.length));},
 reset(){this.lastFrame=0;this.intervals=[];FramePacer34.reset();}
};
const hdPaths34=new Set(['reader-e','reader-d','reader-c','reader-b','reader-a','reader-s','hunter-male-e','hunter-female-e','regressor-e']);
const visualSelectionBefore34=Visual33.selection;
Visual33.selection=function(...args){const s=visualSelectionBefore34.apply(this,args),key=s.figure.split('/').pop().replace('.webp','');return {...s,originalFigure:s.figure,figure:hdPaths34.has(key)?'ui34/'+key+'.webp':s.figure};};
const Hero34={
 cached:new Map(),
 decode(path){if(this.cached.has(path)){const hit=this.cached.get(path);this.cached.delete(path);this.cached.set(path,hit);return hit.promise;}const image=new Image();image.src=path;const promise=image.decode?image.decode().catch(()=>false):Promise.resolve(true);this.cached.set(path,{image,promise});while(this.cached.size>3)this.cached.delete(this.cached.keys().next().value);return promise;},
 preserve(node){const fresh=$('#app .hero-figure33');if(node&&fresh&&node.getAttribute('src')===fresh.getAttribute('src'))fresh.replaceWith(node);const img=$('#app .hero-figure33');if(img){img.decoding='async';img.fetchPriority='high';img.onerror=()=>{img.onerror=null;img.src=Visual33.selection().originalFigure;};this.decode(img.getAttribute('src'));}}
};
header=function(){const box=document.createElement('div');box.innerHTML=V34UI.header();const label=box.querySelector('.brand small');if(label)label.textContent='SISTEMA DE SOMBRAS';return box.innerHTML;};
themeNative20=function(){if(Performance34.themeFrame)return;Performance34.themeFrame=requestAnimationFrame(()=>{Performance34.themeFrame=0;if(!window.Android?.setThemeBackground)return;const c=getComputedStyle(document.body).backgroundColor.match(/\d+/g);if(!c||c.length<3)return;const color='#'+c.slice(0,3).map(x=>Number(x).toString(16).padStart(2,'0')).join('');if(color===Performance34.nativeTheme)return;Performance34.nativeTheme=color;HunterNative3875.setThemeBackground(color);});};
const Stars34={accent:'#a9bacf',ink:'#aebdcc',refresh(){this.accent=document.documentElement.style.getPropertyValue('--accent').trim()||'#a9bacf';},draw(ctx,p,x,y,alpha){ctx.globalAlpha=alpha;ctx.fillStyle=p.tint?this.accent:this.ink;ctx.strokeStyle=ctx.fillStyle;if(p.kind==='star'){const r=p.size;ctx.beginPath();ctx.moveTo(x,y-r*1.8);ctx.lineTo(x+r*.28,y-r*.28);ctx.lineTo(x+r*1.8,y);ctx.lineTo(x+r*.28,y+r*.28);ctx.lineTo(x,y+r*1.8);ctx.lineTo(x-r*.28,y+r*.28);ctx.lineTo(x-r*1.8,y);ctx.lineTo(x-r*.28,y-r*.28);ctx.closePath();ctx.fill();}else if(p.kind==='rune'){const r=p.size*1.5;ctx.lineWidth=.65;ctx.beginPath();ctx.moveTo(x,y-r);ctx.lineTo(x+r*.6,y);ctx.lineTo(x,y+r);ctx.lineTo(x-r*.6,y);ctx.closePath();ctx.stroke();}else{ctx.beginPath();ctx.arc(x,y,p.size,0,Math.PI*2);ctx.fill();}}};
Stars29.draw=function(...args){Stars34.draw(...args);};
const burstBefore34=Particles28.burst;
Particles28.burst=function(kind,rect,target){
 const result=burstBefore34.call(this,kind,rect,target);if(!result)return result;
 const w=Math.max(1,rect.width-4),h=Math.max(1,rect.height-4),safeTop=target?.id==='modal'?64:10;
 // Dispersión en profundidad, sin una hilera que dibuje el borde del recuadro.
 for(const p of this.particles){p.x=12+Math.random()*Math.max(1,w-24);p.y=safeTop+Math.random()*Math.max(1,h-safeTop-12);p.vx=(Math.random()-.5)*30;p.vy=-12-Math.random()*22;}
 return result;
};
/* Reutiliza la única capa ambiental y sus posiciones al navegar o rotar. */
Ambient29.suspend();
Ambient29.geometryKey34='';
Ambient29.sync=function(){
 if(!this.allowed()){this.suspend();this.geometryKey34='';Performance34.reset();return;}
 const dialog=$('#modal'),target=dialog.open?dialog:document.body;
 const key=dialog.open?'d:'+dialog.style.getPropertyValue('--modal-top')+':'+dialog.style.getPropertyValue('--modal-left')+':'+dialog.style.getPropertyValue('--modal-width')+':'+dialog.style.getPropertyValue('--window-height26'):'b:'+innerWidth+':'+innerHeight;
 if(this.canvas?.isConnected&&this.canvas.parentElement===target&&this.geometryKey34===key)return;
 const rect=dialog.open?dialog.getBoundingClientRect():{left:0,top:0,width:innerWidth,height:innerHeight};if(rect.width<1||rect.height<1)return;
 const ratio=Math.min(1.5,devicePixelRatio||1),w=rect.width,h=rect.height;
 if(this.canvas&&this.ctx){const previous=this.rect;if(this.canvas.parentElement!==target)target.append(this.canvas);if(!previous||Math.abs(previous.width-w)>=1||Math.abs(previous.height-h)>=1||this.ratio29!==ratio){if(previous)for(const p of this.particles){p.x*=w/previous.width;p.y*=h/previous.height;}this.canvas.width=Math.round(w*ratio);this.canvas.height=Math.round(h*ratio);this.ctx.setTransform(ratio,0,0,ratio,0,0);this.ratio29=ratio;}Object.assign(this.canvas.style,{left:rect.left+'px',top:rect.top+'px',width:w+'px',height:h+'px'});this.canvas.dataset.layer=dialog.open?'window':'screen';this.rect={left:rect.left,top:rect.top,width:w,height:h};this.geometryKey34=key;return;}
 const canvas=document.createElement('canvas');canvas.className='ambient-particles29';canvas.setAttribute('aria-hidden','true');canvas.dataset.layer=dialog.open?'window':'screen';Object.assign(canvas.style,{left:rect.left+'px',top:rect.top+'px',width:w+'px',height:h+'px'});canvas.width=Math.round(w*ratio);canvas.height=Math.round(h*ratio);target.append(canvas);const ctx=canvas.getContext('2d');if(!ctx){canvas.remove();return;}ctx.setTransform(ratio,0,0,ratio,0,0);this.canvas=canvas;this.ctx=ctx;this.ratio29=ratio;this.rect={left:rect.left,top:rect.top,width:w,height:h};this.geometryKey34=key;this.mounts++;this.last=0;this.entryAt=performance.now();FramePacer34.reset();Stars34.refresh();
 const count=Math.min(68,Math.max(24,Math.round(w*h/11000)));this.particles=Array.from({length:count},(_,i)=>({x:Math.random()*w,y:Math.random()*h,vx:(Math.random()-.5)*3,vy:-6-Math.random()*10,size:.6+Math.random()*1.2,kind:Stars29.type(i),tint:i%5===0,phase:Math.random()*Math.PI*2,period:3+Math.random()*4}));
 const tick=time=>{if(this.canvas!==canvas)return;if(!this.allowed()){this.suspend();Performance34.reset();return;}if(!canvas.isConnected||canvas.parentElement!==($('#modal').open?$('#modal'):document.body))this.sync();Performance34.sample(time);if(FramePacer34.shouldDraw(time)){const dt=this.last?Math.min(.08,(time-this.last)/1000):1/90;this.last=time;const r=this.rect;ctx.clearRect(0,0,r.width,r.height);this.flow*=Math.exp(-dt*4.5);for(const p of this.particles){p.x=(p.x+p.vx*dt+r.width)%r.width;p.y=(p.y+(p.vy-this.flow)*dt+r.height)%r.height;const wave=.5+.5*Math.sin(time/1000/p.period*Math.PI*2+p.phase);Stars34.draw(ctx,p,p.x,p.y,.09+wave*.13);}ctx.globalAlpha=1;this.frames++;Performance34.draws++;}this.frame=requestAnimationFrame(tick);};this.frame=requestAnimationFrame(tick);
};
applyTheme=function(...args){if(Runtime20.initializing)return;V34UI.applyTheme(...args);Stars34.refresh();};
const shortPhrases34={
 knight:['La constancia forja tu rango.','Una serie. Un nuevo avance.','Controla el movimiento y respira.','Tu próxima misión te espera.','Fuerza con técnica, paso a paso.','Hoy también cuenta.','Descansa entre tus series.','Sigamos con tu siguiente paso.'],
 warrior:['Firme. Controla cada repetición.','Tu técnica sostiene tu fuerza.','Volvamos a la misión.','Respira y prepara tu serie.','Cada registro cuenta.','Avanza con un ritmo controlado.','La constancia construye fuerza.','Una serie más en tu historia.'],
 bear:['Paso a paso, seguimos contigo.','Respira. También puedes descansar.','Recuperar forma parte del camino.','Prepara tu siguiente movimiento.','Cuida el ritmo de cada serie.','Hoy avanzamos juntos.','Descansa y vuelve con control.','Tu constancia tiene valor.'],
 insect:['A tus órdenes. Comencemos.','Cada misión cuenta, cazador.','Un nuevo desafío te espera.','Observa la técnica y respira.','Hoy seguimos tu camino.','La siguiente serie te espera.','Completa tu paso con control.','Tu avance merece constancia.'],
 scientist:['Tu historia avanza una página.','Escribe hoy tu siguiente logro.','Cada serie es un nuevo capítulo.','Sigamos leyendo tu progreso.','Tu próximo paso también cuenta.','Una página más, con constancia.','Respira antes del siguiente paso.','Construye tu historia, día a día.']
};
function dailyPhrase34(type=selectedCompanion(),date=E.dateKey()){const day=E.diff(date,'2026-01-01'),hash=[...type].reduce((a,c)=>a+c.charCodeAt(0),0),list=shortPhrases34[type]||shortPhrases34.knight;return ((day+hash)%list.length+list.length)%list.length;}
function thoughtMarkup34(text){return '<div class="shadow-thought34 thought-pop34"><svg class="thought-shape34" viewBox="0 0 300 190" preserveAspectRatio="none" aria-hidden="true"><path class="thought-cloud34" d="M34 73C8 56 25 22 57 27C63 5 99 4 115 20C137 0 170 2 185 20C211 5 242 16 245 38C279 28 300 57 282 80C309 102 291 136 267 136C269 163 234 180 210 164C191 187 156 183 142 169C117 186 88 176 78 164C45 178 21 156 28 134C0 124 7 87 34 73Z"/><path class="thought-inner34" d="M38 73C17 56 31 30 59 34C67 12 100 12 115 27C138 10 168 10 184 28C210 14 235 24 239 45C268 36 290 58 274 81C300 103 284 130 260 130C264 155 235 170 211 157C190 179 159 176 142 162C119 178 91 169 82 156C53 171 29 151 36 129C10 121 17 91 38 73Z"/></svg><p id="shadowPhrase33" class="thought-copy34">'+esc(text)+'</p><i class="thought-trail34" aria-hidden="true"></i><i class="thought-trail34 small34" aria-hidden="true"></i><i class="thought-trail34 tiny34" aria-hidden="true"></i></div>';}
updatePhrase33=function(){const copy=$('#shadowPhrase33');if(!copy)return;const type=selectedCompanion(),list=shortPhrases34[type]||shortPhrases34.knight,index=((Number(copy.dataset.index34)||0)+1)%list.length;copy.dataset.index34=String(index);copy.textContent=list[index];const cloud=copy.closest('.shadow-thought34');if(cloud&&Particles28.allowed()){cloud.classList.remove('thought-pop34');requestAnimationFrame(()=>{if(cloud.isConnected)cloud.classList.add('thought-pop34');});}};
schedulePhrase33=function(){clearTimeout(phraseTimer33);if(!$('#modal').open||!$('#shadowPhrase33')||!Particles28.allowed())return;phraseTimer33=setTimeout(()=>{updatePhrase33();schedulePhrase33();},30000+Math.random()*15000);};
openCoach=function(){V34UI.openCoach();const type=selectedCompanion(),index=dailyPhrase34(type),old=$('#shadowPhrase33');if(old){old.outerHTML=thoughtMarkup34(shortPhrases34[type][index]);$('#shadowPhrase33').dataset.index34=String(index);}schedulePhrase33();ModalViewport.fit();UI26.schedule();};
const mountPanelBefore34=mountPanelCompanion;
mountPanelCompanion=function(type){mountPanelBefore34(type);if($('#modal .shadow-thought34'))schedulePhrase33();};
/* Se omiten fundidos repetidos por repintados de la misma pantalla. */
Transition32.play=function(nodes){if(!(Performance34.routeChanging||Performance34.surfaceOpening)||!Back25.enabled()||!document.documentElement.classList.contains('ready20')||document.hidden)return;for(const node of nodes){if(!node||typeof node.animate!=='function')continue;const a=node.animate([{opacity:.995},{opacity:1}],{duration:150,easing:'ease-out'});this.active.add(a);a.finished.then(()=>this.active.delete(a),()=>this.active.delete(a));}};
render=function(){if(Runtime20.initializing)return;const oldHero=$('#app .hero-figure33');Performance34.routeChanging=Performance34.lastTab!==tab||Performance34.profileReady!==!!state.profile;V34UI.render();Performance34.lastTab=tab;Performance34.profileReady=!!state.profile;Performance34.routeChanging=false;document.querySelectorAll('.brand small').forEach(n=>n.textContent='SISTEMA DE SOMBRAS');Hero34.preserve(oldHero);};
modal=function(html){Performance34.surfaceOpening=true;try{V34UI.modal(html);}finally{Performance34.surfaceOpening=false;}};
const completeBefore34=Boot30.complete.bind(Boot30);
Boot30.complete=async function(){if(state.profile){const s=Visual33.selection();await Promise.race([Hero34.decode(s.figure),new Promise(resolve=>setTimeout(resolve,2000))]);}return completeBefore34();};
openDiagnostics20=function(){V34UI.openDiagnostics20();for(const p of $('#modal').querySelectorAll('p'))p.textContent=p.textContent.replace('Versión 33.0.','Versión 34.0.');const observed=Performance34.observed();$('#modal section')?.insertAdjacentHTML('beforeend','<p>Objetivo de renderizado: 90 FPS. Medición reciente de callbacks en esta apertura: '+(observed===null?'todavía insuficiente':observed+' por segundo')+'. La frecuencia de pantalla y el ritmo de callbacks no verifican los fotogramas presentados por el dispositivo.</p>');};
document.addEventListener('visibilitychange',()=>{if(document.hidden)Performance34.reset();});
window.HunterV34={FramePacer34,Performance34,Hero34,shortPhrases34,dailyPhrase34,hdPaths34};state.appVersion=34;save();render();Ambient29.sync();
