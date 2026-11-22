/* Guía por bloques con registro explícito, pausas y dos órdenes disponibles. */
'use strict';
const V22UI={profileForm,submitProfile,paintGuide11,validateBackup,render,answerCoach};
const expandBefore22=RoutineSequence.expand;
RoutineSequence.expand=function(day){return BlockSession22.expand(day,BlockSession22.mode(state.profile),state.profile||{})?.steps||expandBefore22(day);};
profileForm=function(initial=false){
 const box=document.createElement('div');box.innerHTML=V22UI.profileForm(initial);const f=box.querySelector('form'),rest=f.elements.restSeconds;
 rest.closest('label').insertAdjacentHTML('afterend','<label>Orden de los bloques<select name="blockMode22">'+options({exercise:'Por ejercicio · todas sus series juntas',rounds:'Por rondas · una serie de cada ejercicio'},BlockSession22.mode(state.profile))+'</select><small>Cinco ejercicios forman cinco bloques en el primer modo. Por rondas, haces una serie de cada uno y descansas al terminar la ronda.</small></label>');return box.innerHTML;
};
submitProfile=function(ev){
 const selected=ev.target.elements.blockMode22?.value||'exercise',before=state.profile;
 if(!BlockSession22.modes.includes(selected)){ev.preventDefault();return toast('Selecciona el orden de los bloques.');}
 V22UI.submitProfile(ev);
 if(state.profile&&state.profile!==before){state.profile.blockMode22=selected;save();render();}
};
persistGuide11=function(){
 const g=guide11;if(!g?.record)return;
 state.guidedSessions[g.day.date]={signature:g.signature,index:g.index,remaining:g.remaining,mode22:g.blockMode22||BlockSession22.mode(state.profile),updatedAt:new Date().toISOString()};save();
};
function anchorStep22(previousSteps,previousIndex,steps){
 const previous=previousSteps[previousIndex];if(!previous)return steps.length;
 const findExercise=s=>steps.findIndex(x=>x.kind==='exercise'&&x.itemIndex===s.itemIndex&&x.setIndex===s.setIndex);
 if(previous.kind==='exercise')return findExercise(previous);
 if(previous.kind==='rest'){
  const next=previousSteps.find((s,i)=>i>previousIndex&&s.kind==='exercise');
  if(!next)return steps.findIndex(s=>s.kind==='cooldown');
  const index=findExercise(next);return index>0&&steps[index-1].kind==='rest'?index-1:index;
 }
 return steps.findIndex(s=>s.kind===previous.kind);
}
function migrateGuide22(day,steps,saved){
 if(!saved||saved.signature===RoutineSequence.signature(steps))return saved;
 const old=expandBefore22(day);if(saved.signature!==RoutineSequence.signature(old))return null;
 const previous=old[saved.index];let index=anchorStep22(old,saved.index,steps);if(index<0)index=0;
 const remaining=previous?.kind===steps[index]?.kind?Math.min(saved.remaining||0,steps[index]?.seconds||0):steps[index]?.seconds||0;
 return {...saved,signature:RoutineSequence.signature(steps),index,remaining};
}
startGuide11=function(day,record){
 pauseGuide11();const blockMode22=BlockSession22.mode(state.profile),layout22=BlockSession22.expand(day,blockMode22,state.profile||{}),steps=layout22?.steps||expandBefore22(day),signature=RoutineSequence.signature(steps),saved=record?migrateGuide22(day,steps,state.guidedSessions[day.date]):null;
 const usable=saved?.signature===signature&&Number.isInteger(saved.index)&&saved.index>=0&&saved.index<=steps.length;
 guide11={day,record,steps,signature,blockMode22,layout22,index:usable?saved.index:0,remaining:usable?Math.min(saved.remaining||0,steps[saved.index]?.seconds||0):steps[0]?.seconds||0,running:false,deadline:0};
 modal('<section class="guided-session" data-companion-static="true"><header class="guided-header"><span class="eyebrow">'+(record?'MI SESIÓN':'CONSULTA · SIN REGISTRO')+'</span><h2>'+esc(day.title||'Rutina guiada')+'</h2></header>'+(day.sourceSequence?'<p class="notice source-evidence">'+esc(day.sourceSequence.verificationNote)+'</p>':'')+'<div id="blockSlot22"></div><div id="guidedSlot"></div>'+(day.sourceSequence?'<a class="source-footer" href="https://www.youtube.com/watch?v='+day.sourceSequence.videoId+'" target="_blank" rel="noopener">Enlace directo a la rutina original ↗</a>':'')+'</section>');
 $('#modal').dataset.guide='true';paintGuide11();
};
function blockMarked22(s){const g=guide11;return !!(g?.record&&state.logs[g.day.date]?.items[s.itemIndex]?.id===s.id&&state.logs[g.day.date]?.items[s.itemIndex]?.actual[s.setIndex]?.done);}
function paintBlocks22(){
 const g=guide11,slot=$('#blockSlot22'),s=g?.steps[g.index];if(!g||!slot)return;
 if(!g.layout22){slot.innerHTML='<p class="footnote">La secuencia documentada conserva sus bloques y su orden original.</p>';return;}
 const blocks=g.layout22.blocks,total=blocks.reduce((n,b)=>n+b.series.length,0),marked=blocks.reduce((n,b)=>n+b.series.filter(blockMarked22).length,0),active=s?.kind==='rest'&&s.restScope22==='block'?s.nextBlockIndex22:s?.blockIndex;
 const open=slot.querySelector('details')?.open||false;
 slot.innerHTML='<section class="block-layout22"><label>Orden de la sesión<select name="guideBlockMode22" onchange="setGuideMode22(this.value)">'+options({exercise:'Por ejercicio',rounds:'Por rondas'},g.blockMode22)+'</select></label><p class="footnote">'+(g.blockMode22==='rounds'?'Una serie de cada ejercicio por ronda. Descanso de '+g.layout22.restSeconds+' s entre rondas. Puedes pausar el reloj cuando lo necesites.':'Todas las series del ejercicio en un bloque. Descanso de '+g.layout22.restSeconds+' s entre series y entre bloques.')+'</p><details '+(open?'open':'')+'><summary>Mapa de '+blocks.length+' bloques · '+marked+' de '+total+' series marcadas</summary><ol class="block-map22">'+blocks.map(b=>{
  const done=b.series.filter(blockMarked22).length,selected=b.index===active;
  return '<li class="'+(selected?'active22 ':'')+(done===b.series.length?'marked22':'')+'" data-block22="'+b.index+'"><div class="row between"><b>'+(g.blockMode22==='rounds'?b.label:'Bloque '+(b.index+1))+'</b><small>'+done+'/'+b.series.length+' marcadas</small></div><span>'+esc(g.blockMode22==='rounds'?b.series.map(x=>x.label).join(' · '):b.label)+'</span><button type="button" class="btn ghost" onclick="jumpGuide19('+b.stepIndices[0]+')">Abrir bloque '+(b.index+1)+'</button></li>';
 }).join('')+'</ol></details></section>';
 const article=$('#guidedSlot .guided-step');
 if(s?.kind==='exercise')article?.insertAdjacentHTML('afterbegin','<div class="block-heading22"><span>'+(g.blockMode22==='rounds'?'RONDA':'BLOQUE')+' '+(s.blockIndex+1)+' DE '+blocks.length+'</span><small>'+(g.blockMode22==='rounds'?'Ejercicio '+(blocks[s.blockIndex].series.indexOf(s)+1)+' de '+blocks[s.blockIndex].series.length:'Serie '+(s.setIndex+1)+' de '+s.sets)+'</small></div>');
 if(s?.kind==='rest'){
  article.querySelector('h3').textContent=(s.restScope22==='block'?'Descanso entre '+(g.blockMode22==='rounds'?'rondas':'bloques'):'Descanso entre series')+' · '+s.seconds+' segundos';
  article.querySelector('.eyebrow').textContent=s.restScope22==='block'?'INTERMEDIO · SIGUIENTE '+(g.blockMode22==='rounds'?'RONDA':'BLOQUE'):'RECUPERACIÓN ENTRE SERIES';
  const p=article.querySelector('.rest-orbit + p');if(p)p.textContent=s.restScope22==='block'?'Después del descanso comienza '+(g.blockMode22==='rounds'?'la ronda ':'el bloque ')+(s.nextBlockIndex22+1)+' de '+blocks.length+'.':'Recupera antes de la siguiente serie del mismo ejercicio.';
 }
}
paintGuide11=function(){V22UI.paintGuide11();paintBlocks22();};
function setGuideMode22(mode){
 const g=guide11;if(!g||!g.layout22||!BlockSession22.modes.includes(mode)||g.blockMode22===mode)return;
 pauseGuide11();const anchor=g.steps[g.index],remaining=g.remaining,day=g.day,record=g.record;
 state.profile.blockMode22=mode;save();startGuide11(day,record);
 let index=anchorStep22(g.steps,g.index,guide11.steps);
 if(index<0)index=0;jumpGuide19(index);guide11.remaining=anchor?.kind===guide11.steps[index]?.kind?Math.min(remaining,guide11.steps[index]?.seconds||0):guide11.steps[index]?.seconds||0;updateGuideClock11();persistGuide11();
}
pendingGuide19=function(){
 const g=guide11;if(!g)return;const form=$('#guideRecord');if(form){form.elements.completed.checked=false;storeGuideCheck19(form,false);}
 const next=g.steps.findIndex((s,i)=>i>g.index&&s.kind==='exercise');
 if(next<0)return jumpGuide19(g.steps.length);
 const rest=g.steps.findIndex((s,i)=>i>g.index&&i<next&&s.kind==='rest'&&s.restScope22==='block');
 jumpGuide19(rest<0?next:rest);
};
validateBackup=function(data){
 if(!V22UI.validateBackup(data))return false;
 return [data.profile,...data.cycles.map(c=>c.profile)].filter(Boolean).every(p=>p.blockMode22===undefined||BlockSession22.modes.includes(p.blockMode22))&&Object.values(data.guidedSessions||{}).every(g=>g.mode22===undefined||BlockSession22.modes.includes(g.mode22));
};
answerCoach=function(q){
 if(/bloque|rondas?|circuito|orden de.*serie/i.test(q)){const mode=BlockSession22.mode(state.profile),day=todayDay(),layout=day?BlockSession22.expand(day,mode,state.profile):null;return companionDisplayName()+': '+(mode==='rounds'?'Por rondas, haces una serie de cada ejercicio y repites las rondas hasta recorrer todas las series.':'Por ejercicio, cada ejercicio con todas sus series forma un bloque.')+(layout?' Hoy tienes '+layout.blocks.length+' bloques, con descansos de '+layout.restSeconds+' segundos.':'')+' Cambia el orden en Perfil o en la guía. Solo las casillas de las series registran lo realizado.';}
 return V22UI.answerCoach(q);
};
render=function(){if(Runtime20.initializing)return;V22UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='EL DESPERTAR · V22');};
state.appVersion=22;save();render();
