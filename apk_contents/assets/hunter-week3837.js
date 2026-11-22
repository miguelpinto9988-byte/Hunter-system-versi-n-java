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
