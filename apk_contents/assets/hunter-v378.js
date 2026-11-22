/* Hunter System V37.8 · planificación alternativa y prudente ante lesiones declaradas.
   Las reglas no diagnostican, no prescriben rehabilitación ni evalúan la técnica.
*/
(function(root){
'use strict';
const E=root.Engine,A=root.Adapt,S=root.Schedule;
if(!E||!A||!S||!root.Weekly)return;
const VERSION='37.8';
const MAP={train:'strength',cardio:'cardio',mobility:'recovery',rest:'rest'};
const NAMES={train:'Fuerza',cardio:'Cardio',mobility:'Movilidad',rest:'Descanso'};
const CATS={strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'};
const GROUPS={upper:['Pecho','Espalda','Hombros','Brazos'],lower:['Piernas','Cadera'],core:['Core','Abdomen'],full:['Piernas','Cadera','Pecho','Espalda','Brazos','Hombros','Core','Abdomen']};
const DAYTYPE=['rest','train','cardio','mobility'];
const escapeHtml=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const actives=p=>A.active(p||{});
const relevant=p=>actives(p).length>0||!!p?.health?.special||!!p?.limits;
const preserve=(d,logs)=>!!(d.customized||d.routineId||root.Weekly.active(logs?.[d.date])||d.tasks?.some(t=>t.done));
const preference=(p,date)=>S.valid(p)?p.weekPlan[S.weekday(date)]:null;
const simpleHash=x=>{let h=2166136261;for(let i=0;i<x.length;i++){h^=x.charCodeAt(i);h=Math.imul(h,16777619);}return (h>>>0).toString(16);};
function planKey(p){return simpleHash(JSON.stringify({injuries:p?.injuries||[],health:p?.health||{},restrictions:p?.restrictions||{},goal:p?.goal,finalGoal:p?.finalGoal,focus:p?.focus,trainingMode:p?.trainingMode,equipment:p?.equipment,gymEquipment23:p?.gymEquipment23,minutes:p?.minutes,difficulty:p?.difficulty,experience:p?.experience,trainingScale:p?.trainingScale,weekPlan:p?.weekPlan,restSeconds:p?.restSeconds}));}
function clinicalGate(p,logs={},today=E.dateKey()){
 const mode=A.mode(p);
 if(!mode.auto)return {ok:false,reason:mode.reason};
 // Un episodio de dolor reciente registrado detiene la progresión automática.
 const recent=Object.entries(logs).some(([date,log])=>date<=today&&date>=E.plus(today,-7)&&log?.pain);
 if(recent)return {ok:false,reason:'Se registró dolor en los últimos siete días. Se requiere revisar tu situación antes de generar nuevos ejercicios.'};
 return {ok:true,reason:mode.reason};
}
function affectedAreas(p){return new Set(actives(p).map(x=>x.zone).filter(Boolean));}
function approvedExercise(e,p){
 if(!e||!e.id||!e.name||!Array.isArray(e.zones))return false;
 if(e.sourceOnly||e.variant!=='base'||e.gymOnly23&&p.equipment!=='gym')return false;
 if(e.level>1)return false; // Movimientos más difíciles se excluyen mientras hay lesiones.
 if(e.impact==='Con saltos')return false;
 if(e.equipment==='db'&&p.equipment!=='db'&&p.equipment!=='gym')return false;
 const affected=affectedAreas(p);
 if((e.zones||[]).some(z=>affected.has(z)))return false;
 // Inspección adicional de apoyos que no siempre figuran entre las zonas
 // musculares de la ficha de un ejercicio (por ejemplo: flexiones de rodillas).
 if(affected.has('knee') && (e.floor&&!e.seated||/rodillas apoyadas|cuadrupedia|cuatro apoyos/i.test((e.name||'')+' '+(e.steps||''))))return false;
 if(affected.has('ankle') && /bridge|puente de gl[uú]teos|talones|elevaci[oó]n de tal[oó]n/i.test((e.id||'')+' '+(e.name||'')))return false;
 if(affected.has('wrist') && /boxeo|golpes|puños|punch|shadowbox/i.test((e.name||'')+' '+(e.id||'')))return false;
 if(affected.has('neck')&&(e.overhead||e.spineMove))return false;
 if(affected.has('back')&&(e.spineMove||e.floor&&e.group==='Core'))return false;
 if(affected.has('knee')&&e.kneeBend)return false;
 if(affected.has('wrist')&&e.wristLoad)return false;
 if(affected.has('shoulder')&&e.overhead)return false;
 // La base de datos ya clasifica equipo, apoyo, suelo, rango y carga; no
 // ignoramos esos filtros. Solamente admitimos omitir el límite global de
 // nivel cero para una variante de nivel uno sin otros motivos de exclusión.
 const explanations=A.reasons(e,p).filter(x=>!x.startsWith('Nivel superior al seleccionado'));
 if(explanations.length)return false;
 return true;
}
function candidates(p,kind){
 const category=CATS[kind];
 return E.exercises.filter(e=>e.category===category&&approvedExercise(e,p));
}
function uniqueExercises(pool){const ids=new Set();return pool.filter(e=>{const base=e.baseId||e.id;if(ids.has(base))return false;ids.add(base);return true;});}
function ordered(p,pool,kind,offset=0,history=[]){
 const groups=GROUPS[p.focus]||GROUPS.full;
 const prev=new Set(history.flatMap(x=>(x.items||[]).map(a=>E.byId(a.id)?.baseId||a.id)));
 const grouped=pool.map((e,i)=>({e,score:
  (groups.includes(e.group)?6-groups.indexOf(e.group)*.25:0)
  +(e.level===0?5:0)
  +(prev.has(e.baseId||e.id)?-3:0)
  +(e.equipment==='body'?1:0),
  order:i}));
 grouped.sort((a,b)=>b.score-a.score||a.e.id.localeCompare(b.e.id));
 const byGroup=new Map();for(const item of grouped){const g=item.e.group;if(!byGroup.has(g))byGroup.set(g,[]);byGroup.get(g).push(item.e);}
 const keys=[...byGroup.keys()],rotated=keys.length?[...keys.slice(offset%keys.length),...keys.slice(0,offset%keys.length)]:[];
 const selected=[];let turn=0;
 while(selected.length<pool.length){let more=false;for(const g of rotated){const list=byGroup.get(g);const e=list[turn];if(e){selected.push(e);more=true;}}if(!more)break;turn++;}
 return selected;
}
function previousSessions(logs,now){return Object.entries(logs||{}).filter(([d,l])=>d<now&&d>=E.plus(now,-14)&&l?.complete&&!l.pain).map(([date,l])=>({...l,date}));}
function loadPolicy(logs,now){
 const recent=previousSessions(logs,now);
 const effort=recent.map(l=>Number(l.rpe)).filter(n=>n>0&&n<=10);
 const avg=effort.length?effort.reduce((a,b)=>a+b,0)/effort.length:0;
 const fatigue=recent.some(l=>l.fatigue||l.rpe>=8);
 return {fatigue,avg,completed:recent.length,history:recent,reduce:fatigue||avg>6};
}
function choose(p,kind,rotation=0,logs={},date=E.dateKey()){
 const gate=clinicalGate(p,logs,E.dateKey());
 if(!gate.ok)return {items:[],blocked:gate.reason,kind};
 if(kind==='rest')return {items:[],kind:'rest'};
 const primary=candidates(p,kind);
 // Solo si la modalidad elegida no tiene alternativas compatibles se propone
 // un tipo diferente de ejercicio; la sustitución se declara explícitamente.
 const alternatives=kind==='cardio'?['recovery','strength']:kind==='strength'?['recovery','cardio']:['cardio','strength'];
 let effectiveKind=kind,pool=primary;
 if(!pool.length){for(const other of alternatives){const list=candidates(p,other);if(list.length){pool=list;effectiveKind=other;break;}}}
 if(!pool.length)return {items:[],kind,blocked:'No hay ejercicios con compatibilidad verificable según las zonas, el equipo y las restricciones del perfil. No se sustituye por descanso.'};
 const recent=previousSessions(logs,date),selection=ordered(p,uniqueExercises(pool),effectiveKind,Math.abs(rotation),recent);
 const minutes=Math.max(10,Math.min(45,Number(p.minutes)||20));
 const cap=minutes<=10?2:minutes<=20?3:minutes<=30?4:5;
 const chosen=selection.slice(0,Math.min(cap,selection.length));
 const feedback=loadPolicy(logs,date);
 const items=chosen.map(e=>({id:e.id,
   sets:1,
   target:e.unit==='sec'?(feedback.reduce?12:20):(feedback.reduce?4:e.level?5:7),
   rest:effectiveKind==='recovery'?60:90,
   adaptation378:true,
   noAutoLoad:true,
   safetyNote:'Movimiento controlado, intensidad baja y recorrido tolerable; sin dolor ni carga obligatoria.'
 }));
 return {items:items.map(x=>root.HunterCatalog46?.cap(E.byId(x.id),p,x,{status:{reduced:true},logs,date})||x),kind:effectiveKind,originalKind:kind,substituted:effectiveKind!==kind,feedback};
}
function patchDay(day,p,rotation,logs,today,last){
 if(!day||day.date<today||preserve(day,logs)||root.HunterStable3828?.keep?.(day,p,logs,today))return false;
 const choice=preference(p,day.date);if(!choice)return false;
 const originalKind=MAP[choice];
 if(choice==='rest'){
  if(day.kind!=='rest'||day.items?.length){day.kind='rest';day.items=[];day.tasks=[{id:'rest',text:'Respetar el descanso elegido',done:false}];day.title='Descanso elegido';delete day.blocked378;delete day.adapted378;return true;}
  return false;
 }
 const key=planKey(p)+':'+choice+':'+day.date+':'+String(Object.entries(logs||{}).filter(([d,l])=>d>=E.plus(today,-7)&&d<=today&&(l?.pain||l?.fatigue||l?.complete)).map(([d,l])=>d+':'+l.rpe+':'+l.complete+':'+l.pain).join('|'));
 if(day.planningKey378===key&&day.adapted378)return false;
 const proposed=choose(p,originalKind,rotation,logs,day.date);
 day.kind=proposed.kind;
 day.items=proposed.items;
 day.scheduleChoice=choice;
 day.adapted378=true;
 day.planningKey378=key;
 day.adaptation='injury-alternatives-378';
 day.originalCategory378=NAMES[choice];
 if(proposed.blocked){
  day.blocked378=proposed.blocked;
  day.title=NAMES[choice]+' · pendiente de valoración';
  day.tasks=[];
  day.planningNote='No se indican ejercicios automáticos mientras no haya compatibilidad suficiente o autorización.';
 }else{
  delete day.blocked378;
  day.tasks=[];
  day.title=proposed.substituted?NAMES[choice]+' → '+({strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'}[day.kind])+' suave':NAMES[choice]+' adaptada · baja intensidad';
  day.planningNote=(proposed.substituted?'La actividad elegida fue sustituida por otra categoría compatible. ':'')+'Ejercicios seleccionados mediante exclusión de zonas y movimientos restringidos, con una sola serie y esfuerzo reducido. Revisión según el esfuerzo y el dolor registrado.';
 }
 delete day.autoTemplateId;
 return true;
}
function patchCycle(c,p,logs={},today=E.dateKey()){
 if(!c?.days?.length||!p||!S.valid(p)||!relevant(p))return 0;
 let changed=0;
 c.days.forEach((d,i)=>{if(patchDay(d,p,i+c.index*5,logs,today))changed++;});
 if(changed){c.injuryPlannerVersion378=VERSION;c.decision='Calendario elegido respetado. Las sesiones permitidas se rellenan con alternativas de menor exigencia según zonas declaradas; no constituyen rehabilitación.';}
 return changed;
}
const olderMake=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){
 const c=olderMake(p,start,index,last,logs);
 patchCycle(c,p,logs,start);
 return c;
};
const olderCurrent=root.currentCycle||currentCycle;
currentCycle=function(){
 const c=olderCurrent();if(!c||!state.profile)return c;
 if(patchCycle(c,state.profile,state.logs||{},E.dateKey())&&typeof save==='function')save();
 return c;
};
const olderWorkout=workout;
workout=function(){
 const d=typeof todayDay==='function'?todayDay():null;
 if(!d||!relevant(state.profile))return olderWorkout();
 if(d.blocked378){
  return '<section class="card injury-block378" role="status"><span class="eyebrow">SESIÓN SELECCIONADA · '+escapeHtml(d.originalCategory378||'Entrenamiento')+'</span><h2>Necesita valoración antes de asignar ejercicios</h2><p>'+escapeHtml(d.blocked378)+'</p><p class="footnote">El día mantiene su tipo elegido y no se cuenta como descanso. Por seguridad, el sistema no inicia una rutina automática sin información suficiente. Puedes actualizar las restricciones y la actividad autorizada en Perfil.</p><button class="btn ghost full" onclick="go(\'profile\')">Revisar perfil</button></section>';
 }
 const html=olderWorkout();
 if(!d.adapted378||!d.items?.length)return html;
 return '<div class="notice injury-banner378" role="note"><b>Sesión adaptada a las limitaciones declaradas</b><p>'+escapeHtml(d.planningNote||'')+'</p><small>1 serie por ejercicio · intensidad reducida · descansos ampliados. Interrumpe ante dolor nuevo o empeoramiento. Las reglas no verifican la seguridad clínica ni la técnica individual.</small></div>'+html;
};
const olderPlan=plan;
plan=function(){const c=currentCycle();const html=olderPlan();if(!relevant(state.profile)||!c)return html;
 const days=c.days.filter(x=>x.adapted378),ready=days.filter(d=>d.items?.length).length,blocked=days.filter(d=>d.blocked378).length;
 return '<section class="card injury-summary378"><span class="eyebrow">ACOMPAÑANTE · SESIONES ADAPTADAS</span><h3>Planificación con restricciones</h3><p>'+ready+' días con ejercicios alternativos de baja exigencia; '+blocked+' días pendientes de valoración. Los descansos que elegiste permanecen sin entrenamientos.</p><p class="footnote">La selección es una comprobación técnica de zonas, no una valoración médica ni un programa de rehabilitación.</p></section>'+html;
};
const oldAnswer=answerCoach;
answerCoach=function(q){
 const message=String(q||'');
 if(state.profile&&relevant(state.profile)&&/(lesion|lesi[oó]n|dolor|ejercicio|rutina adaptada|entrenamiento con)/i.test(message)){
  const c=currentCycle(),today=c?.days?.find(d=>d.date===E.dateKey());
  if(today?.blocked378)return companionDisplayName()+': Tienes un día de entrenamiento planificado, pero no he asignado ejercicios automáticos: '+today.blocked378;
  if(today?.adapted378&&today.items?.length)return companionDisplayName()+': '+today.title+'. Ejercicios programados: '+today.items.map(x=>E.byId(x.id)?.name).filter(Boolean).join(', ')+'. Todos están en dosis reducida y excluyen las zonas y restricciones declaradas. No sustituyen una evaluación clínica.';
 }
 return oldAnswer(q);
};
// Centro de rutinas: alternativas realmente seleccionables, no solo notas.
function adaptedTemplates(p,logs={}){
 if(!p||!relevant(p)||!clinicalGate(p,logs).ok)return [];
 const types=[['strength','Fuerza'],['cardio','Cardio'],['recovery','Movilidad']],templates=[],seen=new Set();
 for(const [type,category]of types){
  for(let variant=0;variant<3;variant++){
   const sample=choose(p,type,variant*3+1,logs),items=sample.items;
   if(!items.length)continue;
   const stamp=items.map(x=>x.id).sort().join(',');
   if(seen.has(stamp))continue;seen.add(stamp);
   const requirements=[...new Set(items.flatMap(x=>E.byId(x.id)?.requirements||[]))];
   templates.push({id:'injury378-'+type+'-'+variant,name:category+' suave · alternativa '+(variant+1),category:sample.kind==='strength'?'Fuerza':sample.kind==='cardio'?'Cardio':'Movilidad',description:'Selección reducida de movimientos que no incluyen zonas o restricciones declaradas; requiere autorización para entrenar.',items,requirements,level:0,extension:'standard',goalGroups:['fitness','habit','movement','build','athletic'],family:'adapted',impact:'Sin saltos',minutes:Math.max(5,items.length*4),safe378:true});
  }
 }
 return templates;
}
const olderAllRoutines=allRoutines;
allRoutines=function(){return [...olderAllRoutines(),...adaptedTemplates(state.profile,state.logs||{})];};
const prevCompatible=A.routineCompatible;
A.routineCompatible=function(r,p){
 if(r?.safe378)return clinicalGate(p,state.logs||{}).ok&&r.items.length>0&&r.items.every(x=>approvedExercise(E.byId(x.id),p));
 return prevCompatible(r,p);
};
const previousRoutineList=routineList;
routineList=function(){
 const list=previousRoutineList();
 if(!state.profile||!relevant(state.profile))return list;
 const p=GoalPlans.adjusted(state.profile);
 return list.filter(r=>A.routineCompatible(r,p)&&Catalog.available(r,p));
};
const previousRoutinesView=routines;
routines=function(){
 const html=previousRoutinesView();if(!relevant(state.profile))return html;
 const gate=clinicalGate(state.profile,state.logs||{});
 const note=gate.ok?'Se muestran únicamente rutinas compatibles con las zonas y restricciones registradas. Las variantes adaptadas incluyen series reducidas.':'Se ha suspendido la asignación automática de ejercicios: '+gate.reason;
 return '<div class="notice injury-banner378" role="note">'+escapeHtml(note)+'</div>'+html;
};
const prevRoutineDetail=routineDetail;
routineDetail=function(id){
 prevRoutineDetail(id);
 if(!id?.startsWith('injury378-'))return;
 const select=document.querySelector('#routineDay');
 if(select){for(const opt of [...select.options]){const c=currentCycle();const d=c?.days?.find(x=>x.date===opt.value);if(!d||preference(state.profile,d.date)==='rest')opt.remove();}}
};
const prevPrepareRoutine=prepareRoutine;
prepareRoutine=function(id){
 if(id?.startsWith('injury378-')){
  const date=document.querySelector('#routineDay')?.value;
  if(!date||preference(state.profile,date)==='rest')return routineNotice372('Selecciona un día programado para entrenar. No se modifican los descansos elegidos.');
 }
 return prevPrepareRoutine(id);
};
root.HunterInjuryPlanner378={version:VERSION,choose,approvedExercise,candidates,patchCycle,clinicalGate,planKey,loadPolicy,adaptedTemplates};
try{render();}catch(e){console.warn('Motor de lesiones 37.8',e);}
})(window);
