/* Hunter System V37.6 · calendario explícito, simplificación del perfil, seguimiento basado en registros. */
(function(root){
'use strict';
const TYPES=['rest','train','cardio','mobility'];
const LABELS={rest:'Descanso',train:'Fuerza',cardio:'Cardio',mobility:'Movilidad'};
const SHORT={rest:'DESC',train:'FUERZA',cardio:'CARDIO',mobility:'MOVIL'};
const ORDER=[1,2,3,4,5,6,0];
const DAYS=['D','L','M','X','J','V','S'];
const escapeHTML=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const calendar=p=>Schedule.defaults(p||{}).map(x=>TYPES.includes(x)?x:'rest');
const isActive=l=>!!l&&(l.complete||l.pain||l.manual||l.items?.some(x=>x.missionState||x.selfReported||x.actual?.some(a=>a.done)));
const canAuto=p=>!!p&&Adapt.mode(GoalPlans.adjusted(p)).auto;
function countPlan(week){const n={train:0,cardio:0,mobility:0,rest:0};for(const type of week)n[type]++;return n;}
function dayPreview376(form){
 const week=Array.from({length:7},(_,n)=>form?.elements?.['day'+n]?.value||'rest'),count=countPlan(week),out=form?.querySelector('#day-summary376');
 if(!out)return;
 const backToBack=ORDER.some((day,i)=>week[day]==='train'&&week[ORDER[(i+1)%7]]==='train');
 out.textContent=count.train+' fuerza · '+count.cardio+' cardio · '+count.mobility+' movilidad · '+count.rest+' descanso.'+(backToBack?' Hay fuerza en días consecutivos: se reducirá el volumen de la segunda sesión.':'');
}
function swapDay376(button){
 if(!button?.form)return;
 const day=Number(button.dataset.day),field=button.form.elements['day'+day];if(!field)return;
 const old=TYPES.includes(field.value)?field.value:'rest',next=TYPES[(TYPES.indexOf(old)+1)%TYPES.length];
 field.value=next;button.dataset.kind=next;button.setAttribute('aria-label',Schedule.dayNames[day]+': '+LABELS[next]+'. Pulsar para cambiar el tipo de día');
 button.setAttribute('aria-pressed',String(next!=='rest'));
 button.querySelector('.day-type376').textContent=SHORT[next];
 dayPreview376(button.form);
}
root.swapDay376=swapDay376;
const earlierProfile=profileForm;
profileForm=function(initial=false){
 const doc=document.createElement('div');doc.innerHTML=earlierProfile(initial);
 const form=doc.querySelector('#profileForm');if(!form)return doc.innerHTML;
 // Botón duplicado agregado por v8; conservar únicamente el botón de interpretación y guardado de v37.4.
 for(const button of form.querySelectorAll('button[onclick*="interpretGoal("]'))button.remove();
 const legacyExplanation=form.querySelector('#intentResult');if(legacyExplanation){legacyExplanation.hidden=true;legacyExplanation.setAttribute('aria-hidden','true');}
 const run=form.querySelector('.goal-control374 .goal-apply374');if(run){run.textContent='✦ Interpretar y guardar objetivos';run.setAttribute('aria-label','Interpretar y guardar tus objetivos con el acompañante');}
 const msg=form.querySelector('.goal-control374 > .footnote');if(msg)msg.textContent='Puedes modificar tu meta. Al interpretar, se guardan los cambios y se actualizan las sesiones pendientes compatibles.';
 const editor=form.querySelector('.week-editor');
 if(editor){
  const week=calendar(state.profile),cards=ORDER.map(day=>{
   const t=week[day];return '<div class="day-item376"><button class="day-choice376" type="button" data-day="'+day+'" data-kind="'+t+'" aria-label="'+Schedule.dayNames[day]+': '+LABELS[t]+'. Pulsar para cambiar el tipo de día" aria-pressed="'+String(t!=='rest')+'" onclick="swapDay376(this)"><span class="day-letter376">'+DAYS[day]+'</span><small class="day-type376">'+SHORT[t]+'</small></button><input name="day'+day+'" value="'+t+'" type="hidden"></div>';
  }).join('');
  editor.innerHTML='<div class="day-guide376"><b>Tu semana de entrenamiento</b><p>Toca cada día para alternar entre <strong>Descanso → Fuerza → Cardio → Movilidad</strong>.</p></div><div class="day-week376" role="group" aria-label="Días y tipos de actividad">'+cards+'</div><div id="day-summary376" class="day-summary376" role="status" aria-live="polite"></div><p class="footnote">Fuerza y cardio se asignan en los días elegidos. Los registros ya realizados no se modifican. La movilidad es opcional.</p>';
  const parent=editor.closest('.week-block');
  if(parent){parent.querySelector('h3')?.replaceChildren(document.createTextNode('¿Qué quieres entrenar cada día?'));
   for(const note of parent.querySelectorAll(':scope > .footnote'))if(/días consecutivos|alternan fuerza|semana nueva/i.test(note.textContent||''))note.remove();
   const desc=parent.querySelector(':scope > .muted');if(desc)desc.textContent='Organiza fuerza, cardio, movilidad y descanso según tus preferencias.';
  }
  dayPreview376(form);
 }
 return doc.innerHTML;
};
// Alinea únicamente sesiones NO personalizadas y sin registros a la categoría elegida.
const oldMake=Engine.makeCycle;
function adaptDay376(d,p,rotation,priorStrength,logs,now){
 const choice=calendar(p)[Schedule.weekday(d.date)];if(d.date<now||d.customized||d.routineId||isActive(logs[d.date])||d.tasks?.some(t=>t.done))return false;
 const kind={rest:'rest',train:'strength',cardio:'cardio',mobility:'recovery'}[choice];let changed=false;
 if(choice==='rest'){
  if(d.kind!=='rest'||d.items?.length){d.kind='rest';d.items=[];d.tasks=[{id:'rest',text:'Respetar mi día de descanso',done:false}];d.title='Descanso elegido';changed=true;}
 }else if(canAuto(p)&&d.kind!==kind){
  const planned=Training30.choose(p,kind,rotation).filter(x=>Adapt.allowed(Engine.byId(x.id),GoalPlans.adjusted(p)));
  if(planned.length){d.kind=kind;d.items=planned;d.tasks=[];d.title=LABELS[choice]+' · sesión personalizada';delete d.autoTemplateId;changed=true;}
  else if(!d.items?.length){d.kind='rest';d.title='Revisar opciones compatibles';d.tasks=[{id:'review',text:'No se hallaron ejercicios compatibles de '+LABELS[choice].toLowerCase()+'. Revisa el perfil y las restricciones.',done:false}];}
 }
 if(d.kind==='strength'&&priorStrength&&d.items?.length&&!d.loadReduced376){
  // No modificar el tipo elegido por el usuario. Limitar volumen del segundo día consecutivo.
  d.items=d.items.map(x=>({...x,sets:Math.max(1,Math.floor(x.sets*.75)),rest:Math.max(Number(x.rest)||60,90)}));
  d.planningNote=(d.planningNote||'')+' Fuerza consecutiva: volumen de esta sesión reducido para favorecer la recuperación.';
  d.loadReduced376=true;changed=true;
 }
 if(changed)d.schedulePolicy376=376;
 return changed;
}
function harmonize376(c,p,logs={},today=Engine.dateKey(),last=null){
 if(!c?.days?.length||!Schedule.valid(p)||!canAuto(p)||c.reviews?.some(r=>r.action==='pausar'))return 0;
 let changed=0,prev=last?.days?.at(-1)?.kind==='strength'&&Engine.diff(c.days[0].date,last.days.at(-1).date)===1;
 c.days.forEach((d,i)=>{
  const was=prev,oldKind=d.kind;
  if(adaptDay376(d,p,c.index*7+i,was,logs,today))changed++;
  prev=d.kind==='strength';
 });
 return changed;
}
Engine.makeCycle=function(p,start,index,last,logs={}){
 const c=oldMake(p,start,index,last,logs);harmonize376(c,p,logs,start,last);return c;
};
const oldCurrent=currentCycle;
currentCycle=function(){
 const c=oldCurrent();if(!c||!state.profile)return c;
 const last=state.cycles.filter(x=>x.start<c.start).at(-1);
 if(harmonize376(c,state.profile,state.logs,Engine.dateKey(),last))save();
 return c;
};
function reviewData376(p=state.profile){
 if(!p)return null;
 const now=Engine.dateKey(),week=calendar(p),stats=countPlan(week);
 const last7=Array.from({length:7},(_,i)=>Engine.plus(now,i-6));
 const logs=last7.map(date=>({date,log:state.logs?.[date]})).filter(x=>x.log);
 const completed=logs.filter(x=>x.log.complete&&!x.log.pain);
 const effort=completed.map(x=>x.log.rpe).filter(x=>Number.isFinite(x)&&x>=1&&x<=10);
 const rpe=effort.length?Number((effort.reduce((a,b)=>a+b,0)/effort.length).toFixed(1)):null;
 const pain=logs.some(x=>x.log.pain),fatigue=logs.some(x=>x.log.fatigue||x.log.rpe>=8),counts={train:0,cardio:0,mobility:0};
 for(const x of completed){const k=x.log.kind==='strength'||x.log.kind==='mixed'?'train':x.log.kind==='cardio'?'cardio':x.log.kind==='recovery'?'mobility':null;if(k)counts[k]++;}
 const planned=last7.filter(date=>week[Schedule.weekday(date)]!=='rest').length;
 const adherence=planned?Math.round(100*completed.filter(x=>week[Schedule.weekday(x.date)]!=='rest').length/planned):null;
 const review=state.cycles?.filter(c=>Array.isArray(c.reviews)&&c.reviews.length).flatMap(c=>c.reviews.map(r=>({...r,start:c.start}))).at(-1);
 return {stats,counts,rpe,pain,fatigue,adherence,review,completed:completed.length};
}
function message376(){const s=reviewData376();if(!s)return 'Configura primero tu perfil.';
 let reason='Aún no hay suficientes registros comparables para aumentar la dosis de forma automática.';
 if(s.pain)reason='Se registró dolor. El progreso se mantiene en pausa y conviene revisar las indicaciones antes de retomar el esfuerzo.';
 else if(s.fatigue)reason='Se registró fatiga o esfuerzo alto (8/10 o más). La revisión del plan prioriza recuperar la tolerancia.';
 else if(s.review){reason='Última revisión semanal: '+s.review.action+'. '+s.review.reason;}
 else if(s.completed>=3)reason='Se dispone de actividad registrada. La progresión semanal compara ejercicios, series completas y esfuerzo; no aumenta los kg de forma automática.';
 const avg=s.rpe===null?'sin esfuerzo medio registrado':('esfuerzo medio '+s.rpe+'/10');
 return 'Calendario: '+s.stats.train+' días de fuerza, '+s.stats.cardio+' de cardio, '+s.stats.mobility+' de movilidad y '+s.stats.rest+' de descanso. Últimos 7 días: '+s.counts.train+' sesiones de fuerza y '+s.counts.cardio+' de cardio finalizadas, '+avg+'. '+reason+' Se conservan los entrenamientos ya realizados y las sesiones personalizadas.';
}
const earlierAnswer=answerCoach;
answerCoach=function(question){
 const q=String(question||'');
 if(/(?:progres(?:o|ión)|adaptaci[oó]n|rendimiento|fatiga|esfuerzo|calendario|semana|descanso|d[ií]as de (?:fuerza|cardio)|por qu[eé] (?:no|aument))/i.test(q)&&state.profile)
  return companionDisplayName()+': '+message376();
 return earlierAnswer(question);
};
const earlierProfileView=profile;
profile=function(){const original=earlierProfileView(),s=reviewData376();if(!s)return original;
 const review='<section class="card recovery-review376" aria-label="Progreso y adaptación"><span class="eyebrow">TU COMPAÑERO · INFORME SEMANAL</span><h3>Adaptación según tus registros</h3><p>'+escapeHTML(message376())+'</p><p class="footnote">La interpretación es local y basada en reglas. La postura, las lesiones y la capacidad física requieren valoración individual; no se deducen de los registros.</p></section>';
 return original+review;
};
root.HunterV376={version:'37.6',dayPreview:dayPreview376,review:reviewData376,harmonize:harmonize376,types:TYPES};
try{render();}catch(e){console.warn('V37.6 render',e);}
})(window);
