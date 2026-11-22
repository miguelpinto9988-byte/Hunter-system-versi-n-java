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
