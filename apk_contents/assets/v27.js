/* V27: diez escenas y aviso sonoro de fin de los relojes, sin conexión. */
'use strict';
const V27UI={applyTheme,home,render,modal,paintGuide11,toggleGuideClock11,pauseGuide11,startTimer,startExerciseTimer};
const Scenes27=Object.freeze(Object.fromEntries(Object.keys(ThemeHues).map(k=>[k,'ui33/scene-e-0.webp'])));
applyTheme=function(p=state.profile||{}){if(Runtime20.initializing)return;
 V27UI.applyTheme(p);const root=document.documentElement,key=root.dataset.theme,h=ThemeHues[key]??ThemeHues.blue;
 const values={'--bg':`hsl(${h} 28% 8%)`,'--panel':`hsl(${h} 28% 12%)`,'--surface':`hsl(${h} 26% 16%)`,'--input':`hsl(${h} 25% 10%)`,'--line':`hsl(${h} 22% 34%)`,'--text':`hsl(${h} 14% 84%)`,'--muted':`hsl(${h} 13% 69%)`,'--accent':`hsl(${h} 62% 73%)`,'--cyan':`hsl(${h} 62% 73%)`,'--violet':`hsl(${(h+30)%360} 35% 72%)`,'--glow':`hsla(${h},55%,60%,.09)`,'--ornament26':`hsl(${(h+20)%360} 25% 49%)`,'--metal26':`hsl(${(h+18)%360} 22% 28%)`,'--scene27':`url("${Scenes27[key]||Scenes27.blue}")`};
 Object.entries(values).forEach(([k,v])=>root.style.setProperty(k,v));document.querySelector('meta[name=theme-color]').content=values['--bg'];themeNative20();
 for(const img of document.querySelectorAll('.hero-art26 img'))img.src=Scenes27[key]||Scenes27.blue;
};
home=function(){const box=document.createElement('div');box.innerHTML=V27UI.home();const img=box.querySelector('.hero-art26 img');if(img){img.src=Scenes27[document.documentElement.dataset.theme]||Scenes27.blue;img.setAttribute('decoding','async');}return box.innerHTML;};
const TimerAlarm27={
 guide:null,rest:null,interval:null,context:null,nodes:[],audio:null,
 native(){return typeof window.Android?.playTimerAlarm==='function';},
 unlock(){if(this.native())return;try{const AC=window.AudioContext||window.webkitAudioContext;if(AC){if(!this.context||this.context.state==='closed')this.context=new AC();if(this.context.state==='suspended')this.context.resume().catch(()=>{});}else if(!this.audio){this.audio=new Audio('audio/timer-finished.wav');this.audio.preload='auto';}}catch(e){}},
 stop(){for(const n of this.nodes)try{n.stop();}catch(e){}this.nodes=[];if(this.audio){this.audio.pause();this.audio.currentTime=0;}},
 play(){
  if(document.hidden)return false;try{if(this.native()){window.Android.playTimerAlarm();return true;}}catch(e){}
  this.unlock();this.stop();
  if(this.context?.state==='running'){
   const c=this.context,t=c.currentTime+.015;
   for(const [offset,frequency]of [[0,659.25],[.26,880],[.52,659.25],[.78,880]]){
    const o=c.createOscillator(),g=c.createGain(),a=t+offset,b=a+.21;o.type='sine';o.frequency.value=frequency;
    g.gain.setValueAtTime(0,a);g.gain.linearRampToValueAtTime(.32,a+.008);g.gain.setValueAtTime(.32,b-.04);g.gain.linearRampToValueAtTime(0,b);
    o.connect(g);g.connect(c.destination);o.onended=()=>{o.disconnect();g.disconnect();this.nodes=this.nodes.filter(n=>n!==o);};this.nodes.push(o);o.start(a);o.stop(b);
   }return true;
  }
  if(this.audio){this.audio.currentTime=0;this.audio.play().catch(()=>toast('Activa el volumen multimedia para escuchar el aviso.'));return true;}
  toast('No se pudo reproducir el aviso del reloj.');return false;
 },
 emit(kind){const played=this.play();window.dispatchEvent(new CustomEvent('hunter:timer-finished',{detail:{kind,played}}));},
 finish(kind){
  const a=this[kind];if(!a||Date.now()<a.deadline)return;this[kind]=null;if(document.hidden)return;
  if(kind==='guide'&&(guide11!==a.owner||guide11.index!==a.index||guide11.remaining!==0||guide11.running||!$('#modal').open||!$('#guidedSlot')||!guideValid11()))return;
  if(kind==='interval'&&(!$('#modal').open||!$('#workClock')))return;this.emit(kind);
 },
 test(){this.unlock();this.emit('test');}
};
toggleGuideClock11=function(){TimerAlarm27.unlock();V27UI.toggleGuideClock11();const g=guide11;TimerAlarm27.guide=g?.running&&g.remaining>0?{owner:g,index:g.index,deadline:g.deadline}:null;};
pauseGuide11=function(persist=true){TimerAlarm27.guide=null;return V27UI.pauseGuide11(persist);};
startTimer=function(s){TimerAlarm27.unlock();TimerAlarm27.rest=null;V27UI.startTimer(s);if(Number(s)>0&&timerEnd>Date.now())TimerAlarm27.rest={deadline:timerEnd};};
startExerciseTimer=function(i){TimerAlarm27.unlock();TimerAlarm27.interval=null;V27UI.startExerciseTimer(i);if(workEnd>Date.now())TimerAlarm27.interval={deadline:workEnd};};
paintGuide11=function(){V27UI.paintGuide11();const clock=$('#guidedSlot .guided-clock');if(clock&&!clock.querySelector('[data-alarm-note27]'))clock.insertAdjacentHTML('beforeend','<small data-alarm-note27>Aviso sonoro al terminar · volumen multimedia.</small>');};
modal=function(html){V27UI.modal(html);if(TimerAlarm27.interval&&!$('#workClock')){TimerAlarm27.interval=null;workEnd=0;}};
render=function(){if(Runtime20.initializing)return;V27UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='SISTEMA DE SOMBRAS');};
$('#modal').addEventListener('close',()=>{if(!$('#modal').open){TimerAlarm27.guide=null;TimerAlarm27.interval=null;workEnd=0;TimerAlarm27.stop();}});
document.addEventListener('visibilitychange',()=>{if(document.hidden){TimerAlarm27.guide=null;TimerAlarm27.rest=null;TimerAlarm27.interval=null;timerEnd=0;workEnd=0;TimerAlarm27.stop();}});
window.HunterV27={Scenes27,alarm:TimerAlarm27};state.appVersion=27;applyTheme();save();render();
