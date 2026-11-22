/* V28: evolución visual con el nivel existente; partículas de las ventanas. */
'use strict';
const V28UI={applyTheme,home,render,modal,backModal24,profileForm};
const Evolution28={
 stages:Object.freeze([{key:'awakening',name:'Despertar',min:1,max:10},{key:'rising',name:'Ascenso',min:11,max:20},{key:'elite',name:'Élite',min:21,max:35},{key:'monarch',name:'Monarca',min:36,max:Infinity}]),
 selection(){return {theme:document.documentElement.dataset.theme||'blue',gender:'default',row:0,column:0,level:1,stage:this.stages[0],asset:{path:'ui33/scene-e-0.webp',width:512,height:512,columns:1,rows:1}};},
 crop(width,height,selection){
  const a=selection.asset,cw=a.width/a.columns,ch=a.height/a.rows,inset=1.25,iw=cw-inset*2,ih=ch-inset*2,scale=Math.max(width/iw,height/ih);
  const x=(selection.column*cw+inset)*scale+(iw*scale-width)*.6,y=(selection.row*ch+inset)*scale+(ih*scale-height)*.12;
  return {size:(a.width*scale)+'px '+(a.height*scale)+'px',position:(-x)+'px '+(-y)+'px'};
 },
 current:null,
 layout(selection=this.current||this.selection()){
  this.current=selection;
  const root=document.documentElement,body=this.crop(window.innerWidth,window.innerHeight,selection);
  const set=(style,key,value)=>{if(style.getPropertyValue(key)!==value)style.setProperty(key,value);};
  set(root.style,'--scene28','url("'+selection.asset.path+'")');set(root.style,'--scene-body-size28',body.size);set(root.style,'--scene-body-position28',body.position);
  // Read geometry as a batch, then write. Avoid alternating layout reads and
  // style writes for every surface, especially on portrait/landscape changes.
  const arts=[...document.querySelectorAll('.evolution-art28')].map(art=>({art,rect:art.getBoundingClientRect()}));
  const d=$('#modal'),dialogRect=d.open?d.getBoundingClientRect():null;
  for(const {art,rect:r} of arts){
   if(!r.width||!r.height)continue;const crop=this.crop(r.width,r.height,selection);
   if(art.style.backgroundSize!==crop.size)art.style.backgroundSize=crop.size;if(art.style.backgroundPosition!==crop.position)art.style.backgroundPosition=crop.position;
   for(const [key,value]of Object.entries({theme:selection.theme,gender:selection.gender,stage:selection.stage.key,level:String(selection.level),column:String(selection.column),row:String(selection.row)}))if(art.dataset[key]!==value)art.dataset[key]=value;
  }
  if(dialogRect){const crop=this.crop(dialogRect.width,dialogRect.height,selection);set(d.style,'--scene-window-size28',crop.size);set(d.style,'--scene-window-position28',crop.position);}
 },
 pending:0,
 schedule(){if(this.pending)return;this.pending=requestAnimationFrame(()=>{this.pending=0;this.layout();});}
};
const Particles28={
 canvas:null,frame:0,particles:[],started:0,duration:800,lastKind:null,bursts:0,
 allowed(){return state.profile?.animate!==false&&!document.documentElement.classList.contains('still')&&!matchMedia('(prefers-reduced-motion: reduce)').matches&&!document.hidden;},
 stop(){if(this.frame)cancelAnimationFrame(this.frame);this.frame=0;this.canvas?.remove();this.canvas=null;this.particles=[];},
 burst(kind,rect,target){
  this.stop();if(!this.allowed()||rect.width<1||rect.height<1)return false;
  const inset=2,w=Math.max(1,rect.width-inset*2),h=Math.max(1,rect.height-inset*2),ratio=Math.min(2,window.devicePixelRatio||1),c=document.createElement('canvas');
  c.className='window-particles28';c.setAttribute('aria-hidden','true');c.dataset.effect=kind;c.style.left=(rect.left+inset)+'px';c.style.top=(rect.top+inset)+'px';c.style.width=w+'px';c.style.height=h+'px';c.width=Math.round(w*ratio);c.height=Math.round(h*ratio);
  (target||document.body).append(c);const context=c.getContext('2d');if(!context){c.remove();return false;}context.scale(ratio,ratio);
  const probe=document.createElement('span');probe.style.color='var(--accent)';probe.style.display='none';document.body.append(probe);const color=getComputedStyle(probe).color;probe.remove();
  const count=Math.min(36,Math.max(18,Math.round((w+h)/40)));this.canvas=c;this.lastKind=kind;this.bursts++;this.started=performance.now();
  this.particles=Array.from({length:count},(_,i)=>{
   const edge=i%4,t=.12+Math.random()*.76,x=edge===0?t*w:edge===1?w-6:edge===2?t*w:6,y=edge===0?6:edge===1?t*h:edge===2?h-6:t*h;
   const closing=kind==='close'||kind==='back'&&i%2===0,dx=(w/2-x)/(w/2||1),dy=(h/2-y)/(h/2||1);
   return {x,y,vx:dx*(closing?-9:18)+(Math.random()-.5)*10,vy:dy*(closing?-9:18)-10-Math.random()*10,size:.8+Math.random()*1.6,rune:i%7===0,delay:Math.random()*100};
  });
  const tick=time=>{
   if(this.canvas!==c||!this.allowed()){this.stop();return;}const elapsed=time-this.started;context.clearRect(0,0,w,h);context.fillStyle=color;context.strokeStyle=color;
   for(const p of this.particles){const life=Math.max(0,(elapsed-p.delay)/this.duration);if(life>=1||elapsed<p.delay)continue;const alpha=Math.sin(Math.PI*life)*.42,x=p.x+p.vx*life,y=p.y+p.vy*life;
    context.globalAlpha=alpha;if(p.rune){const size=p.size*2.2;context.lineWidth=.8;context.beginPath();context.moveTo(x,y-size);context.lineTo(x+size,y);context.lineTo(x,y+size);context.lineTo(x-size,y);context.closePath();context.stroke();}
    else{context.beginPath();context.arc(x,y,p.size,0,Math.PI*2);context.fill();}
   }
   context.globalAlpha=1;if(elapsed<this.duration+100)this.frame=requestAnimationFrame(tick);else this.stop();
  };
  this.frame=requestAnimationFrame(tick);return true;
 }
};
applyTheme=function(p=state.profile||{}){if(Runtime20.initializing)return;V28UI.applyTheme(p);Evolution28.layout(Evolution28.selection(p));if(!Particles28.allowed())Particles28.stop();};
home=function(){
 const box=document.createElement('div');box.innerHTML=V28UI.home();const art=box.querySelector('.hero-art26'),s=Evolution28.selection();
 if(art){art.classList.add('evolution-art28');art.querySelector('img')?.remove();art.dataset.theme=s.theme;art.dataset.gender=s.gender;art.dataset.stage=s.stage.key;art.dataset.level=String(s.level);}
 const level=box.querySelector('.hero-footer strong');if(level)level.insertAdjacentHTML('beforeend','<span class="scene-stage28">'+s.stage.name.toUpperCase()+'</span>');
 return box.innerHTML;
};
profileForm=function(initial=false){
 const box=document.createElement('div');box.innerHTML=V28UI.profileForm(initial);const label=box.querySelector('[name=gender]')?.closest('label');
 label?.closest('.fields')?.insertAdjacentHTML('afterend','<p class="evolution-note28">La ilustración acompaña tu nivel: Despertar (1–10), Ascenso (11–20), Élite (21–35) y Monarca (36+). El género y el tema cambian su apariencia.</p>');return box.innerHTML;
};
modal=function(html){Particles28.stop();V28UI.modal(html);Evolution28.layout();const d=$('#modal');if(d.open)Particles28.burst('open',d.getBoundingClientRect(),d);Evolution28.schedule();};
const closeBefore28=$('#modal').close;
$('#modal').close=function(value){
 if(!this.open)return closeBefore28.call(this,value);const rect=this.getBoundingClientRect();Particles28.stop();const result=closeBefore28.call(this,value);if(!this.open)Particles28.burst('close',rect,document.body);return result;
};
backModal24=function(){
 const count=Nav24.stack.length;Particles28.stop();V28UI.backModal24();const d=$('#modal');Evolution28.layout();if(d.open&&Nav24.stack.length<count)Particles28.burst('back',d.getBoundingClientRect(),d);Evolution28.schedule();
};
render=function(){if(Runtime20.initializing)return;V28UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='SISTEMA DE SOMBRAS');Evolution28.layout(Evolution28.selection());Evolution28.schedule();};
window.addEventListener('resize',()=>{Particles28.stop();Evolution28.schedule();},{passive:true});window.visualViewport?.addEventListener('resize',()=>Evolution28.schedule(),{passive:true});
document.addEventListener('visibilitychange',()=>{if(document.hidden)Particles28.stop();});matchMedia('(prefers-reduced-motion: reduce)').addEventListener?.('change',e=>{if(e.matches)Particles28.stop();});
window.HunterV28={Evolution28,Particles28};state.appVersion=28;applyTheme();save();render();
