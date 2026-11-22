/* V29: estética de sombras, ilustraciones monocromáticas y partículas continuas. */
'use strict';
const V29UI={applyTheme,home,render,modal,backModal24};
UI26.corner='<svg viewBox="0 0 40 40" fill="none" aria-hidden="true"><path d="M1 39V13L13 1h26M5 31V15L15 5h16" stroke="currentColor" stroke-width="1"/><path d="M1 22V13L13 1h9" stroke="var(--accent)" stroke-width="1.6"/><path d="m8 8 3 1-2 2Z" fill="currentColor"/></svg>';
const layoutBefore29=Evolution28.layout;
Evolution28.layout=function(selection=this.current||this.selection()){
 layoutBefore29.call(this,selection);document.documentElement.dataset.sceneCharacter=selection.character;
 for(const art of document.querySelectorAll('.evolution-art28'))art.dataset.character=selection.character;
};
const Stars29={
 accent(){return document.documentElement.style.getPropertyValue('--accent').trim()||'#9caec5';},
 draw(ctx,p,x,y,alpha){
  ctx.globalAlpha=alpha;ctx.fillStyle=p.tint?this.accent():'#bdc9d8';ctx.strokeStyle=ctx.fillStyle;
  if(p.kind==='star'){
   const r=p.size;ctx.beginPath();ctx.moveTo(x,y-r*2);ctx.lineTo(x+r*.32,y-r*.32);ctx.lineTo(x+r*2,y);ctx.lineTo(x+r*.32,y+r*.32);ctx.lineTo(x,y+r*2);ctx.lineTo(x-r*.32,y+r*.32);ctx.lineTo(x-r*2,y);ctx.lineTo(x-r*.32,y-r*.32);ctx.closePath();ctx.fill();
  }else if(p.kind==='rune'){
   const r=p.size*1.7;ctx.lineWidth=.7;ctx.beginPath();ctx.moveTo(x,y-r);ctx.lineTo(x+r*.6,y);ctx.lineTo(x,y+r);ctx.lineTo(x-r*.6,y);ctx.closePath();ctx.moveTo(x,y-r);ctx.lineTo(x,y+r);ctx.stroke();
  }else{ctx.beginPath();ctx.arc(x,y,p.size,0,Math.PI*2);ctx.fill();}
 },
 type(i){return i%11===0?'rune':i%4===0?'star':'dot';}
};
/* Conserva la navegación V28; sustituye solo el dibujo de sus ráfagas. */
Particles28.duration=1300;
Particles28.burst=function(kind,rect,target){
 this.stop();if(!this.allowed()||rect.width<1||rect.height<1)return false;
 const inset=2,w=Math.max(1,rect.width-4),h=Math.max(1,rect.height-4),ratio=Math.min(1.5,devicePixelRatio||1),c=document.createElement('canvas');
 c.className='window-particles28 transition-particles29';c.setAttribute('aria-hidden','true');c.dataset.effect=kind;
 Object.assign(c.style,{left:(rect.left+inset)+'px',top:(rect.top+inset)+'px',width:w+'px',height:h+'px'});c.width=Math.round(w*ratio);c.height=Math.round(h*ratio);
 (target||document.body).append(c);const ctx=c.getContext('2d');if(!ctx){c.remove();return false;}ctx.scale(ratio,ratio);
 const count=Math.min(140,Math.max(72,Math.round((w+h)/15)));this.canvas=c;this.lastKind=kind;this.bursts++;this.started=performance.now();
 this.particles=Array.from({length:count},(_,i)=>{
  const edge=i%4,inner=i%5===0,t=.06+Math.random()*.88,x=inner?Math.random()*w:edge===0||edge===2?t*w:edge===1?w-10:10,y=inner?Math.random()*h:edge===1||edge===3?t*h:edge===0?10:h-10;
  const close=kind==='close'||kind==='back'&&i%2===0,dx=(w/2-x)/(w/2||1),dy=(h/2-y)/(h/2||1);
  return {x,y,vx:dx*(close?-58:85)+(Math.random()-.5)*20,vy:dy*(close?-48:65)-25,size:1.1+Math.random()*1.8,kind:Stars29.type(i),tint:i%4===0,delay:Math.random()*120};
 });
 const tick=time=>{
  if(this.canvas!==c||!this.allowed()){this.stop();return;}const elapsed=time-this.started;ctx.clearRect(0,0,w,h);
  for(const p of this.particles){const life=(elapsed-p.delay)/this.duration;if(life<0||life>=1)continue;Stars29.draw(ctx,p,p.x+p.vx*life,p.y+p.vy*life,Math.sin(Math.PI*life)*.68);}
  ctx.globalAlpha=1;if(elapsed<this.duration+120)this.frame=requestAnimationFrame(tick);else this.stop();
 };this.frame=requestAnimationFrame(tick);return true;
};
const Ambient29={
 canvas:null,ctx:null,frame:0,queued:0,particles:[],rect:null,last:0,drawAt:0,flow:0,frames:0,mounts:0,entryAt:0,
 allowed(){return typeof document!=='undefined'&&!!document.documentElement&&Particles28.allowed()&&document.documentElement.classList.contains('ready20');},
 suspend(){
  if(this.queued)cancelAnimationFrame(this.queued);
  if(this.frame)cancelAnimationFrame(this.frame);
  this.queued=this.frame=0;this.canvas?.remove();this.canvas=null;this.ctx=null;
  this.particles=[];this.last=0;this.drawAt=0;
 },
 // V38.18: one geometry synchronization per frame. The ambient drawing loop
 // itself and the exercise timers retain their original timing behavior.
 schedule(){
  if(!this.allowed()){this.suspend();return;}
  if(this.queued)return;
  this.queued=requestAnimationFrame(()=>{this.queued=0;this.sync();});
 },
 sync(){
  if(!this.allowed()){this.suspend();return;}
  const dialog=$('#modal'),target=dialog.open?dialog:document.body,rect=dialog.open?dialog.getBoundingClientRect():{left:0,top:0,width:innerWidth,height:innerHeight};
  if(rect.width<1||rect.height<1){this.suspend();return;}
  const ratio=Math.min(1.5,devicePixelRatio||1),w=rect.width,h=rect.height;
  if(this.canvas&&this.ctx){
   const c=this.canvas,before=this.rect;
   const reparent = c.parentElement!==target;
   if(reparent)target.append(c);
   const resized=Math.abs(before.width-w)>=1||Math.abs(before.height-h)>=1||this.ratio29!==ratio;
   if(resized){
    for(const p of this.particles){p.x*=w/before.width;p.y*=h/before.height;}
    c.width=Math.round(w*ratio);c.height=Math.round(h*ratio);this.ctx.setTransform(ratio,0,0,ratio,0,0);this.ratio29=ratio;
   }
   const relocated=before.left!==rect.left||before.top!==rect.top;
   // Avoid style invalidation while the window and viewport are unchanged.
   if(reparent||resized||relocated){
    Object.assign(c.style,{left:rect.left+'px',top:rect.top+'px',width:w+'px',height:h+'px'});
    c.dataset.layer=dialog.open?'window':'screen';
    this.rect={left:rect.left,top:rect.top,width:w,height:h};
   }
   return;
  }
  const c=document.createElement('canvas');
  c.className='ambient-particles29';c.setAttribute('aria-hidden','true');c.dataset.layer=dialog.open?'window':'screen';Object.assign(c.style,{left:rect.left+'px',top:rect.top+'px',width:w+'px',height:h+'px'});c.width=Math.round(w*ratio);c.height=Math.round(h*ratio);target.append(c);
  const ctx=c.getContext('2d');if(!ctx){c.remove();return;}ctx.scale(ratio,ratio);this.canvas=c;this.ctx=ctx;this.ratio29=ratio;this.rect={left:rect.left,top:rect.top,width:w,height:h};this.entryAt=performance.now();this.mounts++;
  const count=Math.min(100,Math.max(36,Math.round(w*h/7500)));
  this.particles=Array.from({length:count},(_,i)=>({x:Math.random()*w,y:Math.random()*h,vx:(Math.random()-.5)*4,vy:-7-Math.random()*12,size:.8+Math.random()*1.35,kind:Stars29.type(i),tint:i%5===0,phase:Math.random()*Math.PI*2,period:3+Math.random()*4}));
  const tick=time=>{
   if(this.canvas!==c)return;if(!this.allowed()){this.suspend();return;}if(!c.isConnected){this.sync();if(!c.isConnected)return;}
   if(this.drawAt&&time-this.drawAt<1000/30){this.frame=requestAnimationFrame(tick);return;}
   const dt=this.last?Math.min(.08,(time-this.last)/1000):1/30;this.last=time;this.drawAt=time;const size=this.rect;ctx.clearRect(0,0,size.width,size.height);this.flow*=Math.exp(-dt*4.5);
   for(const p of this.particles){p.x=(p.x+p.vx*dt+size.width)%size.width;p.y=(p.y+(p.vy-this.flow)*dt+size.height)%size.height;const wave=.5+.5*Math.sin(time/1000/p.period*Math.PI*2+p.phase),intro=Math.max(0,1-(time-this.entryAt)/1800);Stars29.draw(ctx,p,p.x,p.y,.12+wave*.2+intro*.12);}
   ctx.globalAlpha=1;this.frames++;this.frame=requestAnimationFrame(tick);
  };this.frame=requestAnimationFrame(tick);
 },
 scroll(delta){if(!this.allowed())return;this.flow=Math.max(-90,Math.min(90,this.flow+delta*.45));}
};
applyTheme=function(p=state.profile||{}){if(Runtime20.initializing)return;
 V29UI.applyTheme(p);const root=document.documentElement,h=ThemeHues[root.dataset.theme]??ThemeHues.blue;
 const vars={'--bg':'#070a10','--panel':'#101620','--surface':'#151d29','--input':'#090e17','--line':`hsl(${h} 11% 31%)`,'--text':'#c9d1dd','--muted':'#97a5b8','--accent':`hsl(${h} 28% 70%)`,'--cyan':`hsl(${h} 28% 70%)`,'--violet':`hsl(${h} 12% 65%)`,'--ornament26':`hsl(${h} 18% 43%)`,'--metal26':`hsl(${h} 10% 23%)`,'--glow':`hsla(${h},30%,65%,.07)`};
 Object.entries(vars).forEach(([k,v])=>root.style.setProperty(k,v));document.querySelector('meta[name=theme-color]').content=vars['--bg'];themeNative20();Ambient29.schedule();
};
home=function(){const box=document.createElement('div');box.innerHTML=V29UI.home();const s=Evolution28.selection(),art=box.querySelector('.evolution-art28');if(art)art.dataset.character=s.character;return box.innerHTML;};
modal=function(html){V29UI.modal(html);Ambient29.schedule();};
backModal24=function(){V29UI.backModal24();Ambient29.schedule();};
const closeBefore29=$('#modal').close;
$('#modal').close=function(value){const result=closeBefore29.call(this,value);Ambient29.schedule();return result;};
render=function(){if(Runtime20.initializing)return;V29UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='SISTEMA DE SOMBRAS');Ambient29.schedule();};
let entryDone29=false;
function readiness29(){if(typeof document==='undefined'||!document.documentElement)return;Ambient29.sync();if(!entryDone29&&document.documentElement.classList.contains('ready20')){entryDone29=true;Particles28.burst('boot',{left:0,top:0,width:innerWidth,height:innerHeight},document.body);}}
const readinessObserver29=new MutationObserver(readiness29);readinessObserver29.observe(document.documentElement,{attributes:true,attributeFilter:['class']});
/* V38.17: one ambient-particle movement update per drawn frame, independent
 * scroll offsets for the page and each scrollable panel. UI scrolling itself
 * remains native; this listener never calls preventDefault(). */
const scrollPositions3817 = new WeakMap();
let pendingScroll3817 = 0, scrollFrame3817 = 0, viewportFrame3817 = 0;
const finiteScroll3817 = n => Number.isFinite(n) ? n : 0;
function cancelMotionFrames3817(){
  if (scrollFrame3817) cancelAnimationFrame(scrollFrame3817);
  if (viewportFrame3817) cancelAnimationFrame(viewportFrame3817);
  scrollFrame3817 = viewportFrame3817 = pendingScroll3817 = 0;
}
function ambientScroll3817(event){
  // Runtime373 replaces the old impulse renderer with compositor layers.
  // Avoid reading scroll offsets and queuing a frame when that handler is idle.
  if (document.hidden || !event.target || window.HunterOptimize48?.compositedAmbient) return;
  const target = event.target;
  const top = target === document ? finiteScroll3817(window.scrollY) : finiteScroll3817(Number(target.scrollTop));
  const before = scrollPositions3817.get(target) ?? 0;
  scrollPositions3817.set(target, top);
  // Clamp only the visual impulse, not the actual scroll position.
  pendingScroll3817 = Math.max(-240, Math.min(240, pendingScroll3817 + (top - before)));
  if (scrollFrame3817) return;
  scrollFrame3817 = requestAnimationFrame(() => {
    scrollFrame3817 = 0;
    const delta = pendingScroll3817;
    pendingScroll3817 = 0;
    if (!document.hidden && delta) Ambient29.scroll(delta);
  });
}
function ambientViewport3817(){
  if (viewportFrame3817 || document.hidden) return;
  viewportFrame3817 = requestAnimationFrame(() => {
    viewportFrame3817 = 0;
    if (!document.hidden) Ambient29.sync();
  });
}
document.addEventListener('scroll', ambientScroll3817, {capture:true, passive:true});
window.addEventListener('resize', ambientViewport3817, {passive:true});
window.visualViewport?.addEventListener('resize', ambientViewport3817, {passive:true});
document.addEventListener('visibilitychange', () => {
  if (document.hidden) cancelMotionFrames3817();
  Ambient29.sync();
});
matchMedia('(prefers-reduced-motion: reduce)').addEventListener?.('change', ambientViewport3817);
window.addEventListener('pagehide', () => {
  cancelMotionFrames3817();
  readinessObserver29.disconnect(); Ambient29.suspend(); Particles28.stop();
});
window.addEventListener('pageshow', () => {
  readinessObserver29.observe(document.documentElement,{attributes:true,attributeFilter:['class']});
  readiness29();
});
window.HunterScroll3817 = Object.freeze({version:'38.17', status:()=>({queued:!!scrollFrame3817, queuedViewport:!!viewportFrame3817})});
window.HunterV29={Ambient29,Stars29,transitions:Particles28};state.appVersion=29;applyTheme();save();render();readiness29();
