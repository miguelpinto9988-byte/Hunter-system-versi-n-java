/* Weekly central-body integration 38.47. */
/* Hunter System V38.36 — fuerza superior/inferior por día.
 * El calendario mantiene weekPlan:'train' para compatibilidad y añade
 * weekFocus:[upper|lower|core|none] como preferencia; sólo se aplica al guardar.
 */
(function(root){
 'use strict';
 if(root.HunterWeek3836)return;
 const STATES=['rest','upper','lower','core','cardio','mobility'];
 const LABEL={rest:'Descanso',upper:'Tren superior',lower:'Tren inferior',core:'Tren central',cardio:'Cardio',mobility:'Movilidad'};
 const SHORT={rest:'DESC',upper:'SUPERIOR',lower:'INFERIOR',core:'CENTRAL',cardio:'CARDIO',mobility:'MOVIL'};
 const DAYNAMES=['Domingo','Lunes','Martes','Miércoles','Jueves','Viernes','Sábado'];
 const safeDays=p=>root.Schedule?.defaults?.(p)||['rest','train','rest','train','rest','rest','rest'];
 const fromStored=(p,day)=>{
  const week=safeDays(p),kind=week[day];
  if(kind!=='train')return STATES.includes(kind)?kind:'rest';
  return ['lower','core'].includes(p?.weekFocus?.[day])?p.weekFocus[day]:'upper'; // legados: fuerza azul=superior
 };
 function updatePreview(form){
  const days=Array.from({length:7},(_,d)=>form.querySelector('[data-day="'+d+'"].day-choice376')?.dataset.kind||'rest');
  const count=Object.fromEntries(STATES.map(k=>[k,days.filter(v=>v===k).length]));
  const line=form.querySelector('#day-summary376');
  if(line)line.textContent=count.upper+' superior · '+count.lower+' inferior · '+count.core+' central · '+count.cardio+' cardio · '+count.mobility+' movilidad · '+count.rest+' descanso.';
 }
 function setDay(button,kind){
  const day=Number(button?.dataset.day),form=button?.form;
  if(!form||!Number.isInteger(day)||day<0||day>6||!STATES.includes(kind))return;
  const hidden=form.elements['day'+day],focus=form.elements['weekFocus'+day];
  if(!hidden||!focus)return;
  hidden.value=['upper','lower','core'].includes(kind)?'train':kind;
  focus.value=['upper','lower','core'].includes(kind)?kind:'none';
  button.dataset.kind=kind;
  button.setAttribute('aria-label',DAYNAMES[day]+': '+LABEL[kind]+'. Pulsar para cambiar.');
  button.setAttribute('aria-pressed',String(kind!=='rest'));
  const label=button.querySelector('.day-type376');if(label)label.textContent=SHORT[kind];
  updatePreview(form);
 }
 function swapDay3836(button){
  const index=STATES.indexOf(button?.dataset.kind||'rest');
  setDay(button,STATES[(index+1)%STATES.length]);
 }
 root.swapDay3836=swapDay3836;
 const oldForm=profileForm;
 profileForm=function(initial=false){
  const holder=document.createElement('div');holder.innerHTML=oldForm(initial);
  const form=holder.querySelector('#profileForm');if(!form)return holder.innerHTML;
  const week=form.querySelector('.week-editor .day-week376');if(!week)return holder.innerHTML;
  const profile=state?.profile||{};
  for(const button of week.querySelectorAll('.day-choice376')){
   const day=Number(button.dataset.day);if(!Number.isInteger(day)||day<0||day>6)continue;
   const hidden=form.elements['day'+day];if(!hidden)continue;
   const focus=document.createElement('input');focus.type='hidden';focus.name='weekFocus'+day;
   hidden.insertAdjacentElement('afterend',focus);
   button.setAttribute('onclick','swapDay3836(this)');
   setDay(button,fromStored(profile,day));
  }
  const guide=form.querySelector('.day-guide376 p');if(guide){
   guide.innerHTML='Toca cada día para elegir: <strong>Descanso → Tren superior → Tren inferior → Tren central → Cardio → Movilidad</strong>.';
  }
  const legend=document.createElement('div');legend.className='week-legend3836';
  legend.setAttribute('aria-label','Leyenda de tipos de entrenamiento');
  legend.innerHTML='<strong class="week-legend-title3836">Leyenda de actividades</strong>'+[
   ['upper','Tren superior','Azul · pecho, espalda, hombros y brazos. Ej.: flexiones y remo.'],
   ['lower','Tren inferior','Rosa · piernas y glúteos. Ej.: sentadillas y puente de glúteos.'],
   ['core','Tren central','Lavanda · abdomen, oblicuos y estabilidad del tronco. Ej.: dead bug, planchas y Pallof.'],
   ['cardio','Cardio','Naranja · resistencia cardiovascular. Ej.: marcha y pasos activos.'],
   ['mobility','Movilidad','Verde · movilidad articular y amplitud cómoda de movimiento.'],
   ['rest','Descanso','Gris · recuperación y descanso programado.']
  ].map(([kind,name,description])=>'<div class="week-legend-item3836"><i class="week-legend-swatch3836 '+kind+'" aria-hidden="true"></i><span><b>'+name+'</b><small>'+description+'</small></span></div>').join('');
  (form.querySelector('#day-summary376')||week).insertAdjacentElement('afterend',legend);
  const foot=week.parentElement?.querySelector('.footnote');
  if(foot)foot.textContent='El tipo elegido se aplica a la misión al guardar el perfil. Los ejercicios completados y las sesiones personalizadas conservan sus registros.';
  updatePreview(form);
  return holder.innerHTML;
 };
 // V5 delega el guardado real a V4.submitProfile. Ajustar la ficha ANTES de que
 // el observador V38.29 detecte el cambio y replanifique la misión de hoy.
 if(typeof V4==='object'&&typeof V4.submitProfile==='function'){
  const before=V4.submitProfile;
  V4.submitProfile=function(event){
   const form=event?.target;
   const focuses=Array.from({length:7},(_,d)=>{
    const type=form?.elements?.['day'+d]?.value;
    const focus=form?.elements?.['weekFocus'+d]?.value;
    return type==='train'?(['lower','core'].includes(focus)?focus:'upper'):'none';
   });
   const result=before(event);
   if(form?.id==='profileForm'&&state?.profile){
    state.profile.weekFocus=focuses;
    if(typeof save==='function'&&!root.Runtime20?.initializing)save();
   }
   return result;
  };
 }
 root.HunterWeek3836=Object.freeze({version:'38.36',fromStored,states:[...STATES],setDay,updatePreview});
})(window);

;
/* Weekly central-body integration 38.47. */
/* Hunter System V38.37 — an explanatory legend and a single weekly strength
 * selector. The obsolete global 'Enfoque de fuerza' dropdown is removed from
 * view, retaining a hidden focus field for older goal interpreters. */
(function(root){
 'use strict';
 if(root.HunterWeek3837 || typeof profileForm!=='function')return;
 const previousForm=profileForm;
 const TYPES=[
  ['upper','Tren superior','Pecho, espalda, hombros y brazos. Ejercicios: flexiones, curl de bíceps, remo con mancuernas y elevaciones laterales.'],
  ['lower','Tren inferior','Piernas y glúteos. Ejercicios: sentadillas, zancadas, puente de glúteos y elevaciones de talones.'],
  ['core','Tren central','Lavanda · abdomen, oblicuos y estabilidad del tronco. Ejercicios: dead bug, planchas, Pallof y transportes; según nivel, equipo y restricciones.'],
  ['cardio','Cardio','Resistencia cardiovascular. Ejercicios: marcha activa, pasos laterales y elevaciones alternas de rodillas.'],
  ['mobility','Movilidad','Movilidad articular controlada. Ejercicios: círculos de hombros, movilidad de tobillos y movilidad de columna.'],
  ['rest','Descanso','Día reservado para recuperación; el generador no programa una rutina automática de entrenamiento.']
 ];
 profileForm=function(initial=false){
  const container=document.createElement('div');container.innerHTML=previousForm(initial);
  const form=container.querySelector('#profileForm');if(!form)return container.innerHTML;
  const weekly=form.closest('.week-block') || form.querySelector('.week-block') || form;
  if(!weekly)return container.innerHTML;
  // This single old dropdown conflicts with the new per-day upper/lower
  // choices. The hidden input preserves form.elements.focus, V24 and goal
  // interpretation. Other dropdowns (rest, equipment, missions) remain.
  const globalFocus=weekly.querySelector('select[name="focus"]');
  if(globalFocus){
   const hidden=document.createElement('input');
   hidden.type='hidden';hidden.name='focus';hidden.value=globalFocus.value;
   const label=globalFocus.closest('label');
   if(label&&weekly.contains(label))label.replaceWith(hidden);
   else globalFocus.replaceWith(hidden);
  }
  const legend=weekly.querySelector('.week-legend3836');
  if(legend){
   legend.replaceChildren();
   const heading=document.createElement('strong');heading.className='week-legend-title3836';
   heading.textContent='Leyenda de actividades y ejercicios';legend.append(heading);
   for(const [type,name,example] of TYPES){
    const item=document.createElement('div');item.className='week-legend-item3836';
    const color=document.createElement('i');color.className='week-legend-swatch3836 '+type;
    color.setAttribute('aria-hidden','true');
    const detail=document.createElement('span');
    const label=document.createElement('b');label.textContent=name+':';
    const text=document.createElement('small');text.textContent=example;
    detail.append(label,text);item.append(color,detail);legend.append(item);
   }
   const summary=form.querySelector('#day-summary376');
   if(summary&&summary.nextElementSibling!==legend)summary.insertAdjacentElement('afterend',legend);
  }
  const oldGuide=weekly.querySelector('.day-guide376 p');
  if(oldGuide)oldGuide.textContent='Selecciona en cada día tren superior (azul), tren inferior (rosa), tren central (lavanda), cardio, movilidad o descanso. Toca el día para cambiar de opción.';
  return container.innerHTML;
 };
 root.HunterWeek3837=Object.freeze({version:'38.37',types:TYPES.map(t=>t[0])});
})(window);

;
/* Weekly central-body integration 38.47. */
/* Shared group integration 38.49 – strict day category, all mission paths.
 * Planning roles distinguish main strength, accessories, activation and power.
 * An intentional lower-body power block may retain its original Cardio record.
 * Existing completed/manual sessions remain historical, never overwritten.
 */
(function(root){
 'use strict';
 if(root.HunterStrict3838)return;
 const E=root.Engine,S=root.Schedule,D=root.HunterDynamic381;
 if(!E||!S||!D)return;
 const UPPER=new Set(['Pecho','Espalda','Hombros','Brazos']);
 const LOWER=new Set(['Piernas','Cadera']);
 const CORE=new Set(['Core','Abdomen']);
 const CATEGORY={upper:'Fuerza',lower:'Fuerza',core:'Fuerza',cardio:'Cardio',mobility:'Movilidad'};
 const KIND={upper:'strength',lower:'strength',core:'strength',cardio:'cardio',mobility:'recovery',rest:'rest'};
 const LABEL={upper:'Tren superior',lower:'Tren inferior',core:'Tren central',cardio:'Cardio',mobility:'Movilidad',rest:'Descanso'};
 const clone=x=>JSON.parse(JSON.stringify(x));
 const own=(o,k)=>Object.prototype.hasOwnProperty.call(o||{},k);
 function type(p,date){
  if(!p?.weekPlan||!S.valid(p))return null;
  const wd=S.weekday(date),choice=p.weekPlan[wd];
  if(choice==='train')return ['lower','core'].includes(p.weekFocus?.[wd])?p.weekFocus[wd]:'upper';
  return own(KIND,choice)?choice:null;
 }
 function matches(item,t){
  if(t==='rest')return false;
  const ex=E.byId(item?.id);
  return !!ex&&root.HunterGroups49.matches(ex,t);
 }
 function accepted(items,t){return Array.isArray(items)&&items.length>0&&items.every(x=>matches(x,t));}
 function fingerprint(p,t){return JSON.stringify({groupsVersion:root.HunterGroups49.version,t,goal:p?.goal,difficulty:p?.difficulty,
  equipment:p?.equipment,equipmentModes3880:p?.equipmentModes3880,gymEquipment23:p?.gymEquipment23,age:p?.age,materials46:p?.materials46,capabilities46:p?.capabilities46,
  minutes:p?.minutes,injuries:p?.injuries,restrictions:p?.restrictions,
  health:p?.health,limits:p?.limits,trainingScale:p?.trainingScale});}
 function past(logs,date){return Object.fromEntries(Object.entries(logs||{}).filter(([d])=>d<date));}
 function archiveOld(log,newItems,date){
  if(!log||log.complete||log.manual)return false;
  const old=Array.isArray(log.items)?log.items:[];
  const prior=Array.isArray(log.priorCompleted3838)?log.priorCompleted3838:[];
  const remaining=old.slice();
  const output=newItems.map(template=>{
    const row=clone(template),idx=remaining.findIndex(x=>x&&x.id===template.id&&!x.archivedProfile3829);
    const sets=Math.max(1,Math.min(12,Number(template.sets)||1));
    const actual=Array.from({length:sets},()=>({done:false,value:template.target,kg:0}));
    if(idx>=0){
      const item=remaining.splice(idx,1)[0];
      const done=(item.actual||[]).filter(a=>a?.done===true).map(clone);
      for(let i=0;i<Math.min(sets,done.length);i++)actual[i]=done[i];
      if(done.length>sets)prior.push({id:item.id,actual:done.slice(sets),reason:'series anteriores'});
    }
    row.actual=actual;return row;
  });
  for(const x of remaining){
    const done=(x?.actual||[]).filter(a=>a?.done===true);
    if(done.length)prior.push({id:x.id,actual:clone(done),reason:'categoría cambiada'});
  }
  log.items=output;
  if(prior.length)log.priorCompleted3838=prior;
  log.profileChanged3829=true;
  log.strict3838=date;
  return true;
 }
 function strictDay(c,d,p,logs,date,tracker){
  if(!d||d.date<date)return false;
  const t=type(p,d.date),log=logs?.[d.date];if(!t)return false;
  if(d.customized||d.manual||log?.manual||log?.complete||log?.items?.some(x=>x.actual?.some(a=>a.done))||d.routineId&&!d.creatorAssigned)return false;
  const sig=fingerprint(p,t);
  const coherent=(t==='rest'?(d.kind==='rest'&&(!d.items||d.items.length===0)):
     d.kind===KIND[t]&&accepted(d.items,t)&&d.scheduleChoice===p.weekPlan[S.weekday(d.date)]);
  const noWork=t!=='rest'&&d.strictBlocked3838===sig&&!(d.items||[]).length;
  const staleLog=!!(d.date===date&&log&&!log.complete&&!log.manual&&Array.isArray(log.items)&&
    log.items.some(x=>!d.items?.some(y=>y.id===x.id)||!matches(x,t)));
  if(d.strictSignature3838===sig&&(coherent||noWork)){
    if(staleLog){archiveOld(log,d.items||[],date);return true;}
    return false;
  }
  // First run can adopt a matching deterministic generated plan without changing
  // today's existing doses. Inconsistent plans are repaired once then locked.
  if(coherent&&d.strictSignature3838==null){
   d.strictSignature3838=sig;d.strictType3838=t;
   if(staleLog)archiveOld(log,d.items||[],date);
   return true;
  }
  let result={items:[],blocked:null};
  if(t!=='rest'){
    result=D.choose(p,KIND[t],D.profileKey(p)+'|'+d.date+'|'+(c?.index||0),past(logs,d.date),d.date,new Set(),tracker);
    if(!accepted(result.items,t)){
      // This fail-closed check also catches other planners' fallback results.
      result={items:[],blocked:result.blocked||'No se encontraron ejercicios compatibles de '+LABEL[t]+'; revisa el perfil o las restricciones.'};
    }
  }
  const next=t==='rest'?[]:clone(result.items||[]);
  d.kind=KIND[t];d.scheduleChoice=p.weekPlan[S.weekday(d.date)];d.title=t==='rest'?'Descanso elegido':
    result.blocked?LABEL[t]+' · requiere revisión':LABEL[t]+' · sesión del día';
  d.items=next;d.tasks=result.blocked?[{id:'review-strict3838',text:'Revisar restricciones para '+LABEL[t],done:false}]:[];
  d.dynamic381=true;d.strictType3838=t;d.strictSignature3838=sig;
  d.strictBlocked3838=result.blocked?sig:null;
  if(result.blocked){d.blocked381=result.blocked;d.blocked378=result.blocked;}
  else{delete d.blocked381;delete d.blocked378;delete d.blocked379;}
  delete d.creatorAssigned;delete d.autoTemplateId;delete d.sourceSequence;
  if(t==='rest')delete d.routineId;
  if(d.date===date){
    d.dailyFixed3828=date;
    if(root.HunterStable3828?.safetyKey)d.dailySafety3828=root.HunterStable3828.safetyKey(p);
    if(log){archiveOld(log,next,date);log.kind=d.kind;}
  }
  return true;
 }
 function apply(c,p,logs={},date=E.dateKey()){
  if(!c?.days?.length||!p||!S.valid(p)||c.reviews?.at(-1)?.action==='pausar')return 0;
  let changed=0;
  const tracker=root.HunterPlan3810.tracker(c,logs,date),tracked=new Set(tracker.snapshot().map(x=>x.date)),add=tracker.add;
  tracker.add=function(day,ex,sets){tracked.add(day);add(day,ex,sets);};
  for(const d of c.days){
   if(strictDay(c,d,p,logs,date,tracker))changed++;
   if(d.date>=date&&!tracked.has(d.date))for(const item of d.items||[]){const ex=E.byId(item.id);if(ex)tracker.add(d.date,ex,item.sets);}
  }
  return changed;
 }
 // A power block must never become a continuous jump warm-up. This also
 // covers generated library previews so the estimate matches the step player.
 if(typeof Warm24!=='undefined'&&typeof Warm24.select==='function'){
  const originalWarm=Warm24.select;
  Warm24.select=function(day,p=state?.profile){
   if(!day?.items?.length||!p)return originalWarm.apply(this,arguments);
   return root.HunterGroups49.phase(day,p);
  };
 }
 const prevCycle=typeof currentCycle==='function'?currentCycle:null;
 if(prevCycle){let busy=false;
  currentCycle=function(){
   const c=prevCycle.apply(this,arguments);
   if(busy||!c||!state?.profile)return c;
   busy=true;
   try{if(apply(c,state.profile,state.logs||{},E.dateKey())>0&&typeof save==='function'&&!root.Runtime20?.initializing)save();}
   finally{busy=false;}
   return c;
  };
 }
 const prevSubmit=typeof submitProfile==='function'?submitProfile:null;
 if(prevSubmit){submitProfile=function(ev){
  const answer=prevSubmit.apply(this,arguments);
  if(ev?.target?.id==='profileForm'&&state?.profile){
   const disk=(()=>{try{return JSON.parse(localStorage.getItem(KEY));}catch(_){return null;}})();
   if(disk?.profile){
    const c=state?.cycles?.find(x=>x.days?.some(d=>d.date===E.dateKey()));
    if(c&&apply(c,state.profile,state.logs||{},E.dateKey())>0&&typeof save==='function')save();
   }
  }
  return answer;
 };}
 root.HunterStrict3838=Object.freeze({version:'38.38',type,matches,accepted,apply,archiveOld});
})(window);

;
/* Hunter System V38.44 — guarded fallback for Android WebView multi-touch
   zoom. Independent of mission state, dialogs, screen orientation or timing.
   One-finger navigation and normal page scrolling are left untouched. */
(function() {
  'use strict';
  if (window.HunterNoZoom3844) return;
  function blockMultiTouch(event) {
    if (event.cancelable && event.touches && event.touches.length > 1) {
      event.preventDefault();
    }
  }
  function blockGesture(event) {
    if (event.cancelable) event.preventDefault();
  }
  // A single passive:false listener handles multi-touch; no polling/timers.
  document.addEventListener('touchmove', blockMultiTouch, {capture:true, passive:false});
  // Some embedded browsers expose non-standard gesture events.
  document.addEventListener('gesturestart', blockGesture, {capture:true, passive:false});
  document.addEventListener('gesturechange', blockGesture, {capture:true, passive:false});
  window.HunterNoZoom3844 = Object.freeze({version:'38.44'});
})();

