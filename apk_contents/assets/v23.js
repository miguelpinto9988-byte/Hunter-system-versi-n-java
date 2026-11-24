/* V23: gimnasio como entorno de entrenamiento, sesiones completas y panel amplio. */
'use strict';
const V23UI={profileForm,submitProfile,currentCycle,allRoutines,routinesIndex11,routines,book,exerciseCards,technique,validateBackup,render,answerCoach};
TrainingReference20.chapters.push(
 {title:'Gimnasio: equipo, asiento y seguros',text:'La selección exige el equipo que marques. Antes de una máquina, comprueba sus apoyos y el recorrido sin carga; en barras, verifica soportes y seguros. La carga que registres en kg corresponde a lo usado en esa serie, no a una estimación de la app.',source:'https://www.acefitness.org/resources/everyone/exercise-library/equipment/weight-machines-selectorized/'},
 {title:'Prensa: espalda apoyada y rodillas controladas',text:'Mantén espalda y pelvis apoyadas, talones en la plataforma y un recorrido que no despegue la pelvis. La extensión termina con rodillas relajadas; evita bloquearlas y devuelve la plataforma con control.',source:'https://www.acefitness.org/resources/everyone/exercise-library/154/seated-leg-press/'},
 {title:'Abducción lateral: dos piernas sin cruces',text:'Alinea cabeza y columna y conserva la pelvis de lado. Eleva solo la pierna superior con rodilla alargada; vuelve despacio antes de repetir. La ilustración diferencia ambos lados y mantiene el recorrido limitado.',source:'https://www.acefitness.org/resources/everyone/exercise-library/38/side-lying-hip-abduction/'},
 {title:'Progresión: más control antes de más cantidad',text:'Una variante lenta o con pausa cambia la demanda aunque tenga menos repeticiones. El sistema compara series completas con esfuerzo registrado y revisa la semana. Las series pendientes no permiten aumentar la dosis, y una reducción elegida se conserva.',source:'https://www.acsm.org/wp-content/uploads/2026/03/Resistance-Training-Position-Stand-infographic.pdf'},
 {title:'Tres niveles y adaptación individual',text:'Básico usa patrones bilaterales y apoyos; intermedio incorpora control y trabajo unilateral; avanzado añade palancas, coordinación y cadencias más exigentes. Las indicaciones y restricciones del perfil se aplican antes de la dificultad. Las fuentes educativas no sustituyen una evaluación individual de una lesión.',source:'https://www.acefitness.org/resources/everyone/exercise-library/'}
);
let gymBrowse23=false,gymLevel23=null,gymFamily23='all';
function gymFields23(form){
 const equipment=form.elements.equipment.value,block=form.querySelector('.gym-equipment23'),load=form.elements.load;
 if(block)block.hidden=equipment!=='gym';
 // The load belongs to a dumbbell. Bodyweight and gym profiles do not submit it.
 if(load){load.disabled=equipment!=='db';load.closest('.field').hidden=equipment!=='db';}
}
profileForm=function(initial=false){
 const box=document.createElement('div');box.innerHTML=V23UI.profileForm(initial);const f=box.querySelector('form'),p=state.profile||{};
 f.elements.equipment.insertAdjacentHTML('beforeend','<option value="gym" '+(p.equipment==='gym'?'selected':'')+'>Gimnasio · barras, poleas y máquinas</option>');f.elements.equipment.value=p.equipment||'body';f.elements.equipment.setAttribute('onchange','gymFields23(this.form)');
 f.elements.equipment.closest('.fields').insertAdjacentHTML('afterend','<fieldset class="gym-equipment23 card"><legend>Equipo disponible en tu gimnasio</legend><p class="footnote">Marca el material que puedes utilizar. Las rutinas se filtran por esta selección.</p><div class="gym-gear-grid23">'+Object.entries(Training23.gymEquipment).map(([key,name])=>'<label><input type="checkbox" name="gym_'+key+'" '+((p.gymEquipment23||[]).includes(key)?'checked':'')+'><span>'+name+'</span></label>').join('')+'</div></fieldset>');
 gymFields23(f);return box.innerHTML;
};
submitProfile=function(ev){
 const f=ev.target,gear=Object.keys(Training23.gymEquipment).filter(k=>f.elements['gym_'+k].checked),gym=f.elements.equipment.value==='gym',before=state.profile;
 if(gym&&!gear.length){ev.preventDefault();return toast('Marca el equipo disponible en tu gimnasio.');}
 V23UI.submitProfile(ev);
 if(state.profile&&state.profile!==before){state.profile.gymEquipment23=gear;if(gym){if(!state.profile.equipmentModes3880?.includes('db'))state.profile.load=0;state.profile.bench=gear.includes('bench');}currentCycle();save();render();}
};
currentCycle=function(){
 const c=V23UI.currentCycle();if(!c||!state.profile)return c;
 const {blockMode22,...settings}=state.profile,key=JSON.stringify([23,settings]);
 if(c.planningKey23!==key){const previous=state.cycles.filter(x=>x.start<c.start).at(-1);Training23.assign(c,state.profile,state.logs,E.dateKey(),previous);c.planningKey23=key;c.profile=JSON.parse(JSON.stringify(state.profile));save();}
 return c;
};
allRoutines=function(){
 const homes=V23UI.allRoutines();return [...homes,...Training23.gymRoutines.map(r=>{const p={...state.profile,difficulty:OwnRoutines18.keys[r.level]},items=state.profile?Training23.adjust(r,p):r.items;return {...r,items,minutes:OwnRoutines18.estimate(items)};})];
};
function openGym23(){gymBrowse23=true;ownLibrary11=false;trainerPage11=null;routineMode='complete';tab='routines';render();}
function gymView23(){
 const n=gymLevel23??TrainingProgression19.level(state.profile||{}),all=allRoutines().filter(r=>r.gymOnly23);let list=all.filter(r=>r.level===n);
 if(gymFamily23!=='all')list=list.filter(r=>r.family==='gym-'+gymFamily23);
 return '<button class="text-button" onclick="gymBrowse23=false;render()">← Centro de entrenamiento</button><div class="page-heading"><span class="eyebrow">GIMNASIO · RUTINAS COMPLETAS</span><h1>Rutinas especializadas en gimnasio</h1><p class="muted">Barras, poleas, máquinas y sesiones secuenciales con preparación y descansos.</p></div><div class="routine-level-tabs" role="tablist" aria-label="Niveles de gimnasio">'+OwnRoutines18.names.map((name,i)=>'<button role="tab" aria-selected="'+(i===n)+'" class="'+(i===n?'active':'')+'" onclick="gymLevel23='+i+';render()"><b>'+name+'</b><small>'+all.filter(r=>r.level===i).length+' sesiones</small></button>').join('')+'</div><label>Especialidad<select onchange="gymFamily23=this.value;render()">'+options({all:'Todas las especialidades',...Object.fromEntries(Object.entries(Training23.gymFamilies).map(([k,v])=>[k,v.name]))},gymFamily23)+'</select></label>'+(state.profile?.equipment!=='gym'?'<div class="notice"><p>Configura el gimnasio y su equipo en Perfil para asignar estas rutinas a tus días.</p><button type="button" class="btn" onclick="gymBrowse23=false;go(\'profile\')">Configurar gimnasio</button></div>':'')+'<div class="stack gym-routines23">'+list.map(r=>{
  const compatible=Adapt.routineCompatible(r,GoalPlans.adjusted(state.profile));
  return '<article class="card routine-card18"><span class="eyebrow">'+esc(OwnRoutines18.names[r.level])+' · '+r.items.length+' ejercicios</span><h3>'+esc(r.name)+'</h3><p class="footnote">'+r.gymRequirements23.map(k=>Training23.gymEquipment[k]).join(' · ')+'</p><ul class="routine-preview18">'+r.items.slice(0,4).map(x=>'<li><b>'+x.sets+' × '+x.target+(E.unitOf(x)==='sec'?' s':'')+'</b><span>'+esc(E.byId(x.id).name)+'</span></li>').join('')+'</ul><p>'+r.items.reduce((sum,x)=>sum+x.sets,0)+' series · ≈ '+r.minutes+' min · '+(compatible?'Coincide con tu perfil':'Consulta la ficha y revisa los filtros del perfil')+'</p><button type="button" class="btn primary full" onclick="routineDetail(\''+r.id+'\')">Abrir rutina completa</button></article>';
 }).join('')+'</div>';
}
routinesIndex11=function(){return V23UI.routinesIndex11().replace('<div class="section-title"><h2>Entrenadores de referencia', '<button type="button" class="academy-banner gym-banner23" onclick="openGym23()"><span>EQUIPAMIENTO DE GIMNASIO</span><b>Rutinas especializadas en gimnasio</b><small>'+Training23.gymRoutines.length+' sesiones completas · básico, intermedio y avanzado →</small></button><div class="section-title"><h2>Entrenadores de referencia');};
routines=function(){return gymBrowse23?gymView23():V23UI.routines();};
const listBefore23=exerciseList;
exerciseList=function(){
 // Los requisitos de gimnasio se mantienen en una propiedad independiente.
 const equip=exerciseEquip;if(equip==='gym'||['barbell','cable','machine','smith','cardio'].includes(equip)){exerciseEquip='all';const list=listBefore23();exerciseEquip=equip;return list.filter(e=>e.gymOnly23&&(equip==='gym'||e.gymRequirements23.includes(equip)));}
 return listBefore23().filter(e=>equip!=='body'||!e.gymOnly23);
};
book=function(){const box=document.createElement('div');box.innerHTML=V23UI.book();const select=box.querySelector('select[onchange*="exerciseEquip"]');select?.insertAdjacentHTML('beforeend',options({gym:'Gimnasio · todo el equipo',barbell:'Barras',cable:'Poleas',machine:'Máquinas',smith:'Smith',cardio:'Cinta o ergómetro'},exerciseEquip));return box.innerHTML;};
function equipmentLabel23(e){return e.gymOnly23?e.gymRequirements23.map(k=>Training23.gymEquipment[k]).join(' + '):e.requirements.length?e.requirements.map(k=>Catalog.equipment[k]).join(' + '):'Sin equipo especial';}
exerciseCards=function(list){const box=document.createElement('div');box.innerHTML=V23UI.exerciseCards(list);for(const [i,card]of [...box.querySelectorAll('.exercise-card')].entries()){const p=card.querySelector('p');if(p)p.textContent=equipmentLabel23(list[i]);}return box.innerHTML;};
technique=function(id){V23UI.technique(id);const e=E.byId(id),tag=$('#modal .tech-tags span');if(tag)tag.textContent=equipmentLabel23(e);if(e.equipment==='gym')$('#modal').insertAdjacentHTML('beforeend','<p class="footnote">Registra en kg la carga total usada en esta serie. Ajusta asiento, apoyos y seguros antes de comenzar.</p>');};
validateBackup=function(data){
 if(!V23UI.validateBackup(data))return false;
 return [data.profile,...data.cycles.map(c=>c.profile)].filter(Boolean).every(p=>p.gymEquipment23===undefined||Array.isArray(p.gymEquipment23)&&p.gymEquipment23.length<=6&&p.gymEquipment23.every(k=>Object.hasOwn(Training23.gymEquipment,k)));
};
answerCoach=function(q){if(/gimnasio|máquina|maquina|polea|barra/i.test(q))return companionDisplayName()+': Hay '+Training23.gymRoutines.length+' rutinas completas de gimnasio en tres niveles. La selección usa tu equipo, objetivo, nivel y restricciones. La revisión semanal necesita series confirmadas y esfuerzo registrado antes de aumentar la dosis o cambiar de variante. Configura el equipo en Perfil y abre Rutinas especializadas en gimnasio.';return V23UI.answerCoach(q);};
render=function(){if(Runtime20.initializing)return;V23UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='EL DESPERTAR · V23');};
openDiagnostics20=function(){const bytes=OfflineManifest20.bytes||0;modal('<section><h2>Estado del contenido local · V23</h2><p>'+E.exercises.length+' fichas · '+Catalog.routines.length+' rutinas completas · '+Offline20.count()+' demostraciones HD.</p><p>'+ExercisePack23.baseIds.length+' movimientos nuevos y 200 variantes de cadencia. '+Training23.gymRoutines.length+' rutinas de gimnasio en tres niveles.</p><p>Medios locales: '+(bytes/1000000).toFixed(1)+' MB. La biblioteca, los vectores, las reglas y el estimador de esfuerzo funcionan sin conexión.</p><p>'+Runtime20.errors.length+' errores de interfaz registrados durante esta apertura.</p>'+Runtime20.errors.map(x=>'<p>'+esc(x.source)+' · '+esc(x.message)+'</p>').join('')+'<p class="footnote">La progresión requiere registros confirmados. El filtro de lesiones usa tus restricciones e indicaciones declaradas; no diagnostica ni prescribe rehabilitación.</p></section>');};
state.appVersion=23;currentCycle();save();render();
