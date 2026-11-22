(function(global){
'use strict';
class IgrisCharacter {
 constructor(host,type='knight',options={}) {
  if(!IgrisArt.labels[type]) throw Error('Personaje desconocido: '+type);
  this.host=host;this.type=type;host.dataset.renderer="2d";host.dataset.character=type;this.options=options;host.innerHTML=IgrisArt.svg(type);
  this.parts={};host.querySelectorAll('[data-part]').forEach(p=>this.parts[p.dataset.part]=p);
  this.state='hidden';this.elapsed=0;this.frame=0;this.last=0;this.renderLast=0;this.paused=false;
  this.automatic=options.automatic??host.id==='animatedCompanion';this.autoTimer=0;this.previousAction='';
  this.reduced=options.reducedMotion??matchMedia('(prefers-reduced-motion: reduce)').matches;
  this.visibility=()=>document.hidden?this.pause():this.resume();document.addEventListener('visibilitychange',this.visibility);
  this.sample('hidden',0);
 }
 transform(part,value){const node=this.parts[part];if(!node)return;this._usedTransforms?.add(part);if(node.getAttribute('transform')!==value)node.setAttribute('transform',value);}
 sample(state,ms){
  const t=ms/1000,reduced=this.reduced;
  this._usedTransforms=new Set();
  const tempo={knight:1,warrior:1.16,bear:.72,insect:1.42,scientist:.86}[this.type]||1;
  const phase=t*tempo;
  let opacity=state==='hidden'?0:1,x=0,y=0,s=1,angle=0;
  if(state==='entering'){
   const p=Math.min(1,ms/(reduced?100:980)),ease=1-Math.pow(1-p,3),overshoot=Math.sin(p*Math.PI)*.055;
   opacity=Math.min(1,p*4);s=reduced?1:.28+.72*ease+overshoot;y=reduced?0:(1-ease)*70;x=reduced?0:(this.type==='insect'?-90:this.type==='warrior'?60:34)*(1-ease);angle=reduced?0:(this.type==='bear'?-6:-14)*(1-ease);
  }
  if(state==='exiting'){
   const p=Math.min(1,ms/(reduced?100:760)),ease=p*p;
   opacity=1-ease;s=reduced?1:1-.62*ease;y=reduced?0:65*ease;x=reduced?0:(this.type==='insect'?70:-24)*ease;angle=reduced?0:16*Math.sin(p*Math.PI);
  }
  if(state==='idle'&&!reduced){
   const amp={knight:5,warrior:7,bear:2.5,insect:8,scientist:3.5}[this.type]||5;
   x=Math.sin(phase*1.2)*amp*.7;y=Math.sin(phase*2.15)*amp*.45;
   if(this.type==='insect')y-=5+Math.sin(phase*3.2)*3;
   if(this.type==='bear')angle=Math.sin(phase*.8)*.7;
   if(this.type==='scientist')angle=Math.sin(phase*.9)*1.1;
  }
  this.parts.root.setAttribute('opacity',opacity);
  this.parts.aura.setAttribute('opacity',opacity*(.68+.2*Math.sin(phase*2.1)));
  this.parts.shadow.setAttribute('opacity',opacity*(this.type==='insect'?.42:.58));
  this.parts.particles.setAttribute('opacity',reduced?0:opacity*.65);
  if(!reduced){
   if(state==='walk')x+=Math.sin(phase*3)*35;
   if(state==='flip'){angle+=360*Math.min(1,t/1.4);y-=65*Math.sin(Math.min(1,t/1.4)*Math.PI);}
   if(state==='celebrate')y-=Math.abs(Math.sin(phase*5))*25;
   if(state==='bow')angle+=18*Math.sin(Math.min(1,t/2.2)*Math.PI);
   if(state==='think')angle+=4*Math.sin(phase*3);
   if(state==='look')angle+=5*Math.sin(Math.min(1,t/2.2)*Math.PI*2);
   if(state==='stretch')s+=.025*Math.sin(Math.min(1,t/2.2)*Math.PI);
  }
  const pivot=state==='flip'?270:470;
  this.transform('root',`translate(${x} ${y}) translate(200 ${pivot}) rotate(${angle}) scale(${s}) translate(-200 -${pivot})`);
  if(!reduced){
   const wave=Math.sin(phase*2),flutter=Math.sin(phase*4),breath=Math.sin(phase*1.7);
   const bodyX=this.type==='bear'?.004:.008,bodyY=this.type==='warrior'?.018:this.type==='bear'?.007:.012;
   this.transform('body',`translate(200 420) scale(${1+breath*bodyX} ${1+breath*bodyY}) translate(-200 -420)`);
   const nod=state==='entering'&&this.type==='knight'?12*Math.sin(Math.min(1,ms/980)*Math.PI):0;
   this.transform('head',`rotate(${nod+wave*(this.type==='scientist'?1.5:2.5)} 200 285)`);
   if(state==='nod')this.transform('head',`rotate(${12*Math.sin(phase*5)} 200 285)`);
   this.transform('cape',`rotate(${wave*(this.type==='knight'?4:3)} 200 310)`);
   this.transform('plume',`rotate(${flutter*6} 215 105)`);
   let left=wave*4,right=-wave*4;
   if(this.type==='bear'){left=wave*2;right=-wave*2;}
    if(this.type==='scientist'&&state==='idle'){right=-8+Math.sin(phase*1.3)*5;}
    // V38.24: subtle alternate resting gestures; not breathing, no new animation loop.
    if(state==='idle' && !document.hidden && global.HunterEvolution385?.budget!=='eco'){
      const phasePose=Math.max(0,Math.sin(phase*.22));
      const mix=phasePose*phasePose*phasePose*phasePose;
      if(this.type==='knight'){left-=mix*7;right+=mix*9;}
      else if(this.type==='warrior'){left-=mix*12;right-=mix*6;}
      else if(this.type==='bear'){left+=mix*5;right-=mix*5;}
      else if(this.type==='insect'){left+=mix*9;right-=mix*9;}
      else if(this.type==='scientist'){left-=mix*4;right-=mix*11;}
    }
   if(state==='entering'||state==='wave'||state==='celebrate')right=-25+Math.sin(phase*15)*20;
   if(state==='exiting')right=-35+Math.sin(phase*18)*20;
   if(state==='point')right=-58+Math.sin(phase*3)*5;
   if(state==='stretch'){right=-65*Math.sin(Math.min(1,t/2.2)*Math.PI);left=-right;}
   if(state==='listen'){right=-18+Math.sin(phase*2)*3;this.transform('head',`rotate(-7 200 285)`);}
   this.transform('armLeft',`rotate(${left} 160 315)`);
   this.transform('armRight',`rotate(${right} 240 315)`);
   const wingSpeed=this.type==='insect'?18:15,wingAmp=this.type==='insect'?18:13;
   this.transform('wingLeft',`rotate(${Math.sin(phase*wingSpeed)*wingAmp} 185 320)`);
   this.transform('wingRight',`rotate(${-Math.sin(phase*wingSpeed)*wingAmp} 215 320)`);
   this.transform('antennaLeft',`rotate(${flutter*5} 150 155)`);
   this.transform('antennaRight',`rotate(${-flutter*5} 250 155)`);
   this.transform('flames',`translate(200 160) scale(${1+flutter*(this.type==='warrior'?.065:.045)} ${1+wave*.04}) translate(-200 -160)`);
   const blinkWindow=this.type==='bear'?4700:this.type==='scientist'?3600:4100,blink=(ms%blinkWindow>blinkWindow-300&&ms%blinkWindow<blinkWindow-120)?.12:1;
   this.transform('eyes',`translate(200 218) scale(1 ${blink}) translate(-200 -218)`);
   this.transform('particles',`translate(${Math.sin(phase*.8)*3} ${-8*Math.sin(phase)})`);
   const stride=state==='walk'?Math.sin(phase*8)*7:0;
   this.transform('legLeft',`rotate(${stride} 177 400)`);this.transform('legRight',`rotate(${-stride} 218 400)`);
   if(this.type==='scientist')this.transform('mouth',`translate(200 264) scale(1 ${state==='think'?1+Math.sin(phase*8)*.22:1+breath*.035}) translate(-200 -264)`);
   this.transform('shadow',`translate(200 480) scale(${state==='celebrate'?1-Math.abs(Math.sin(phase*5))*.18:1+breath*.025} 1) translate(-200 -480)`);
  }
 }
  // Clear transforms only on parts not animated in this frame.
  // (Cleanup injected immediately below through a sample wrapper.)

 tick=(now)=>{this.frame=0;if(this.paused||this.state==='hidden')return;if(this.last)this.elapsed+=Math.max(0,now-this.last)*(this.reduced?1:MotionPlayback.rate);this.last=now;
  if(['walk','flip','celebrate','bow','wave','think','nod','look','stretch','point','listen'].includes(this.state)&&this.elapsed>=2200){this.state='idle';this.elapsed=0;}
  if(this.state==='entering'&&this.elapsed>=(this.reduced?100:980)){this.state='idle';this.elapsed=0;}
  if(this.state==='exiting'&&this.elapsed>=(this.reduced?100:760)){this.state='hidden';this.sample('hidden',0);this.finish?.(true);this.finish=null;return;}
  if(!this.reduced&&this.renderLast&&now-this.renderLast<(global.HunterPerf380?.actorInterval(this.state)||32)){this.frame=requestAnimationFrame(this.tick);return;}this.renderLast=now;
  const drawStart380=performance.now();this.sample(this.state,this.elapsed);global.HunterPerf380?.sampleDrawCost(performance.now()-drawStart380);this.frame=requestAnimationFrame(this.tick);
 };
 perform(kind){if(!['walk','flip','celebrate','bow','wave','think','nod','look','stretch','point','listen'].includes(kind)||this.destroyed||this.state==='exiting')return;cancelAnimationFrame(this.frame);this.state=kind;this.elapsed=0;this.last=0;this.renderLast=0;if(!this.paused)this.frame=requestAnimationFrame(this.tick);}
 clearAutomatic(){clearTimeout(this.autoTimer);this.autoTimer=0;}
 automaticStep(){
  this.clearAutomatic();
  if(!this.automatic||this.reduced||this.destroyed||this.paused||document.hidden||this.state==='hidden'||this.state==='exiting')return;
  if(this.state!=='idle'){this.autoTimer=setTimeout(()=>this.automaticStep(),500);return;}
  const kinds=['walk','wave','bow','think','nod','look','stretch','point','listen',...(this.type==='insect'?['flip']:['celebrate'])].filter(k=>k!==this.previousAction);
  const action=kinds[Math.floor(Math.random()*kinds.length)];this.previousAction=action;this.host.dataset.automaticAction=action;this.perform(action);this.scheduleAutomatic();
 }
 scheduleAutomatic(){
  this.clearAutomatic();if(!this.automatic||this.reduced||this.destroyed||this.paused||document.hidden||this.state==='hidden'||this.state==='exiting')return;
  this.autoTimer=setTimeout(()=>this.automaticStep(),6500+Math.floor(Math.random()*7500));
 }
 open(){if(this.destroyed)return;this.finish?.(false);this.finish=null;cancelAnimationFrame(this.frame);this.state='entering';this.elapsed=0;this.last=0;this.renderLast=0;this.sample(this.state,0);if(!this.paused)this.frame=requestAnimationFrame(this.tick);this.scheduleAutomatic();}
 close(){this.clearAutomatic();if(this.destroyed||this.state==='hidden')return Promise.resolve(true);if(this.state==='exiting')return this.closing;if(document.hidden){this.state='hidden';this.sample('hidden',0);return Promise.resolve(true);}if(this.paused)this.resume();this.clearAutomatic();this.state='exiting';this.elapsed=0;this.closing=new Promise(resolve=>this.finish=resolve);return this.closing;}
 pause(){this.clearAutomatic();this.paused=true;cancelAnimationFrame(this.frame);this.frame=0;this.last=0;}
 resume(){if(this.destroyed)return;this.paused=false;this.last=0;this.renderLast=0;if(!this.frame&&this.state!=='hidden')this.frame=requestAnimationFrame(this.tick);this.scheduleAutomatic();}
 destroy(){this.pause();this.destroyed=true;this.finish?.(false);this.finish=null;document.removeEventListener('visibilitychange',this.visibility);this.host.innerHTML='';}
}

const nativeSample380=IgrisCharacter.prototype.sample;
IgrisCharacter.prototype.sample=function(...args){
 const previous=this._usedTransforms;
 nativeSample380.apply(this,args);
 if(previous) for(const name of previous) if(!this._usedTransforms.has(name)){
   const node=this.parts[name];if(node?.hasAttribute('transform'))node.removeAttribute('transform');
 }
};

global.IgrisCharacter=IgrisCharacter;
})(window);
