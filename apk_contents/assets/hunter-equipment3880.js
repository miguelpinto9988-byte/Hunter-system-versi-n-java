/* Hunter System 38.80 — equipamiento combinable desde creación y edición de perfil.
 * No genera ejercicios nuevos, no sustituye restricciones y conserva el
 * almacenamiento anterior en profile.equipment para compatibilidad.
 */
(function (root, doc) {
 'use strict';
 if(root.HunterEquipment3880 || typeof profileForm!=='function' ||
    typeof submitProfile!=='function' || typeof V4!=='object')return;
 const keys=['body','db','gym'];
 const names={body:'Peso corporal',db:'Mancuernas',gym:'Gimnasio'};
 function modes(profile){
  if(Array.isArray(profile?.equipmentModes3880))return keys.filter(k=>profile.equipmentModes3880.includes(k));
  if(profile?.equipment==='gym')return Number(profile.load)>0?['body','db','gym']:['body','gym'];
  if(profile?.equipment==='db')return ['body','db'];
  return ['body'];
 }
 function fromForm(form){
  return keys.filter(k=>form?.querySelector('input[name="equipmentModes3880"][value="'+k+'"]:checked'));
 }
 function sync(form){
  if(!form?.querySelector('.equipment-modes3880'))return;
  const selected=fromForm(form),legacy=form.elements.namedItem('equipment');
  if(legacy)legacy.value=selected.includes('gym')?'gym':selected.includes('db')?'db':'body';
  // Se ejecuta primero la lógica V23 de mostrar/ocultar; luego corregimos
  // el caso compuesto de gimnasio + mancuernas.
  const gym=form.querySelector('.gym-equipment23');
  if(gym)gym.hidden=!selected.includes('gym');
  const load=form.elements.namedItem('load');
  if(load){
   load.disabled=!selected.includes('db');
   const field=load.closest('.field');if(field)field.hidden=!selected.includes('db');
  }
  const summary=form.querySelector('.equipment-summary3880');
  if(summary)summary.textContent=selected.length?
   'Modalidades activas: '+selected.map(x=>names[x]).join(' + ')+'. Se combinarán ejercicios compatibles.':
   'Marca al menos una modalidad antes de guardar.';
  for(const box of form.querySelectorAll('.equipment-mode3880')){
   const checkbox=box.querySelector('input');
   box.classList.toggle('selected3880',Boolean(checkbox?.checked));
  }
 }
 const oldGym=typeof gymFields23==='function'?gymFields23:null;
 if(oldGym)gymFields23=function(form){
  oldGym(form);
  if(form?.querySelector('.equipment-modes3880'))sync(form);
 };
 const oldForm=profileForm;
 profileForm=function(initial=false){
  const wrap=doc.createElement('div');wrap.innerHTML=oldForm(initial);
  const form=wrap.querySelector('#profileForm');if(!form)return wrap.innerHTML;
  const legacy=form.elements.namedItem('equipment'),field=legacy?.closest('.field');
  if(!field||form.querySelector('.equipment-modes3880'))return wrap.innerHTML;
  const panel=doc.createElement('fieldset');
  panel.className='equipment-modes3880';
  const current=modes(state?.profile||{});
  panel.innerHTML='<legend>Equipamiento para tus ejercicios</legend>'+
   '<p class="footnote">Puedes marcar varias opciones. Peso corporal y mancuernas se pueden utilizar juntos, también con gimnasio.</p>'+
   '<div class="equipment-grid3880">'+keys.map(k=>
    '<label class="equipment-mode3880 '+(current.includes(k)?'selected3880':'')+'">'+
     '<input type="checkbox" name="equipmentModes3880" value="'+k+'" '+(current.includes(k)?'checked':'')+
      ' onchange="HunterEquipment3880.change(this.form)">'+
     '<span class="equipment-mode-copy3880"><strong>'+names[k]+'</strong><small>'+({body:'Ejercicios con tu propio cuerpo',db:'Con los kilos de tus mancuernas',gym:'Con las máquinas y material declarados'}[k])+'</small></span>'+
    '</label>').join('')+'</div><p class="footnote equipment-summary3880" aria-live="polite"></p>';
  field.classList.add('legacy-equipment3880');
  const row=field.closest('.fields');
  (row||field).insertAdjacentElement('afterend',panel);
  sync(form);
  return wrap.innerHTML;
 };
 // V4 es la entrada del guardado que utiliza la capa V23 de gimnasio. Inyectar
 // los modos antes de que el observador V38.29 compare el perfil y regenere hoy.
 const oldV4=V4.submitProfile;
 V4.submitProfile=function(event){
  const form=event?.target;
  if(form?.id!=='profileForm'||!form.querySelector('.equipment-modes3880'))return oldV4.apply(this,arguments);
  const selected=fromForm(form),before=state?.profile;
  const answer=oldV4.apply(this,arguments);
  if(state?.profile&&state.profile!==before){
   state.profile.equipmentModes3880=selected;
   if(typeof save==='function'&&!root.Runtime20?.initializing)save();
  }
  return answer;
 };
 const oldSubmit=submitProfile;
 submitProfile=function(event){
  const form=event?.target;
  if(form?.id!=='profileForm'||!form.querySelector('.equipment-modes3880'))return oldSubmit.apply(this,arguments);
  const selected=fromForm(form),load=form.elements.namedItem('load');
  if(!selected.length){event.preventDefault();toast('Selecciona al menos una modalidad de equipamiento.');return;}
  if(selected.includes('db')&&(!load||!Number.isFinite(Number(load.value))||Number(load.value)<=0)){
   event.preventDefault();toast('Indica los kilos disponibles de una mancuerna.');
   load?.focus();return;
  }
  sync(form);
  return oldSubmit.apply(this,arguments);
 };
 root.HunterEquipment3880=Object.freeze({version:'38.80',modes,fromForm,change:sync});
 // La pantalla inicial puede haberse dibujado antes de cargar este último módulo.
 // Actualizarla una vez, sin interferir con diálogos o registros existentes.
 root.requestAnimationFrame?.(()=>{
  if(doc.querySelector('#profileForm')&&!doc.querySelector('#profileForm .equipment-modes3880')&&typeof render==='function')render();
 });
})(window,document);
