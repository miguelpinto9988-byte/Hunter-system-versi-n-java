/* Hunter System V37.9 — repair of empty injury sessions.
 * Static rule-based scheduling; NOT a diagnosis, clearance, or rehabilitation prescription.
 * Important: always preserve explicitly stopped sessions, recorded work and chosen rest.
 */
(function(root){
'use strict';
const E=root.Engine,A=root.Adapt,S=root.Schedule,W=root.Weekly;
if(!E||!A||!S||!W||!root.HunterInjuryPlanner378)return;
const VER='37.9';
// Low-demand seated upper-body options when the offline catalogue has no
// suitable strength exercises for a lower-limb limitation. These are text-only
// instructions, without claiming to have a verified vector/3D demonstration.
const ADDITIONS=[
 {id:'hs379-scapular',name:'Juntar omóplatos sentado',group:'Espalda',category:'Fuerza',zones:['shoulder','back'],unit:'reps',instructions:['Sentarse con la espalda apoyada cómodamente y los brazos relajados.','Acercar suavemente los omóplatos sin elevar los hombros.','Volver a la postura neutra sin echar el cuerpo hacia atrás.'],cue:'Movimiento pequeño y cómodo; sin mover las piernas.'},
 {id:'hs379-palms',name:'Presión suave de palmas sentado',group:'Pecho',category:'Fuerza',zones:['shoulder','elbow','wrist','back'],unit:'sec',instructions:['Sentarse en un apoyo firme con piernas cómodas.','Juntar las palmas frente al pecho sin elevar los hombros.','Presionar suavemente durante el intervalo y soltar.'],cue:'Esfuerzo bajo y respiración continua; no usar si la mano, muñeca o hombro está lesionado.'},
 {id:'hs379-isobiceps',name:'Flexión de codo con resistencia propia sentado',group:'Brazos',category:'Fuerza',zones:['shoulder','elbow','wrist'],unit:'sec',instructions:['Sentarse con espalda apoyada y hombros relajados.','Flexionar un codo aproximadamente a mitad de recorrido y oponer suavemente la mano contraria.','Mantener una contracción leve y cambiar de brazo; evitar tensión en cuello y muñecas.'],cue:'Sin tirones ni esfuerzos máximos; no usar con lesión de brazo, codo o muñeca.'},
 {id:'hs379-shoulderset',name:'Elevación corta de hombros sentado',group:'Hombros',category:'Fuerza',zones:['shoulder','neck'],unit:'reps',instructions:['Sentarse con la columna apoyada y las piernas cómodas.','Elevar los hombros solo unos centímetros sin levantar los brazos.','Soltar suavemente hasta la postura neutra.'],cue:'Movimiento pequeño sin bloquear el cuello.'}
];
for(const x of ADDITIONS){if(E.byId(x.id))continue;const e={...x,equipment:'body',level:0,steps:x.instructions.join(' '),setup:x.instructions[0],impact:'Bajo',seated:true,floor:false,overhead:false,spineMove:false,wristLoad:false,kneeBend:false,requirements:[],variant:'base',baseId:x.id,sourceOnly:false,tempo:'Lento y controlado.',breath:'Respiración natural sin aguantar el aire.',control:'Detente si aparece o empeora el dolor.',noVector379:true};E.exercises.push(e);}

const TYPES={train:'strength',cardio:'cardio',mobility:'recovery',rest:'rest'};
const LABELS={train:'Fuerza',cardio:'Cardio',mobility:'Movilidad',rest:'Descanso'};
const NAMES={strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'};
const ZONES=new Set(['neck','shoulder','elbow','wrist','back','hip','knee','ankle']);
const GROUPS={
 upper:['Pecho','Espalda','Hombros','Brazos'],lower:['Piernas','Cadera'],other:['Core','Abdomen'],
 preferred:{
  knee:['Pecho','Espalda','Brazos','Hombros','Core','Abdomen','Piernas','Cadera'],
  ankle:['Pecho','Espalda','Brazos','Hombros','Core','Abdomen','Piernas','Cadera'],
  hip:['Pecho','Espalda','Brazos','Hombros','Core','Abdomen','Piernas','Cadera'],
  shoulder:['Piernas','Cadera','Core','Abdomen','Brazos','Espalda','Pecho'],
  elbow:['Piernas','Cadera','Core','Abdomen','Pecho','Espalda','Brazos'],
  wrist:['Piernas','Cadera','Core','Abdomen','Pecho','Espalda','Brazos'],
  back:['Piernas','Brazos','Pecho','Cadera','Hombros','Core','Abdomen','Espalda'],
  neck:['Piernas','Cadera','Core','Abdomen','Brazos','Pecho','Espalda']
 }
};
function active(p){return A.active(p||{});}
function impactStatus(p){
 const a=active(p),h=p?.health||{},zones=a.map(i=>i.zone),known=zones.length&&zones.every(z=>ZONES.has(z));
 const serious=!!h.acute||h.pain==='strong'||a.some(i=>i.type==='bone'||i.type==='surgery')||
  !!h.emergency||!!h.neurological||!!h.unstable||!!h.redFlags;
 if(!p||p.age<18)return {ok:false,reason:'Se requiere una planificación individual apropiada para la edad.'};
 if(serious)return {ok:false,reason:'Se registró dolor intenso, una lesión reciente grave o una condición que requiere valoración. No es seguro asignar automáticamente ejercicios, incluso para otras zonas.'};
 if(a.length&&!known)return {ok:false,reason:'Indica qué zona está afectada para excluirla de todos los movimientos. Una zona desconocida no puede filtrarse de forma fiable.'};
 if(h.special&&!h.approved)return {ok:false,reason:'Existe una condición especial que necesita indicaciones individuales antes de programar ejercicio.'};
 if(p.limits&&!a.length)return {ok:false,reason:'Marca la zona afectada en Perfil para encontrar grupos musculares alternativos.'};
 if((h.pain==='moderate'||h.pain==='mild')&&!a.length)return {ok:false,reason:'Selecciona la ubicación del dolor para evitar ejercicios que involucren esa zona.'};
 // Mild/moderate LOCALIZED discomfort or the absence of an explicit approval tick should
 // not by itself erase the programme. Exercises are conservative options, never clearance.
 return {ok:true,reason:a.length?'Alternativas de baja exigencia para grupos alejados de las zonas declaradas. El dolor puede limitar también movimientos de otras zonas.':'Planificación general.',affected:zones,reduced:a.length>0||h.pain==='mild'||h.pain==='moderate'};
}
function hasEquipment(e,p){
 if(e.equipment==='db'&&!['db','gym'].includes(p.equipment))return false;
 if(e.gymOnly23&&p.equipment!=='gym')return false;
 for(const x of e.requirements||[]){if(x==='db'&&!['db','gym'].includes(p.equipment))return false;if(x!=='db'&&!p[x])return false;}
 return true;
}
function approved(e,p){
 if(root.HunterCatalog46?.reasons(e,p).length)return false;
 if(!e||!e.id||!e.name||!Array.isArray(e.zones)||!e.category||e.sourceOnly||e.variant!=='base')return false;
 if(!hasEquipment(e,p)||e.level>1||e.impact==='Con saltos')return false;
 const affected=new Set(active(p).map(i=>i.zone)),r=p.restrictions||{},txt=(e.id+' '+e.name+' '+(e.steps||'')).toLowerCase();
 if(e.zones.some(z=>affected.has(z)))return false;
 if(affected.has('knee')&&(e.kneeBend||(!e.seated&&e.floor)||/rodillas apoyadas|cuatro apoyos|cuadrupedia|arrodillad/i.test(txt)))return false;
 if(affected.has('ankle')&&(/puente de gl[uú]teos|elevaci[oó]n de talones|heel raise|calf raise/i.test(txt)||!e.seated&&!e.floor))return false;
 if(affected.has('hip')&&!e.seated&&/sentadilla|zancada|estocada|peso muerto|bisagra/i.test(txt))return false;
 if(affected.has('wrist')&&(e.wristLoad||/boxeo|golpes|puños|punch/i.test(txt)))return false;
 if(affected.has('shoulder')&&(e.overhead||e.wristLoad||/plancha|flexiones|push.?up|brazos elevados/i.test(txt)))return false;
 if(affected.has('neck')&&(e.overhead||e.spineMove))return false;
 if(affected.has('back')&&(e.spineMove||e.floor&&['Core','Abdomen'].includes(e.group)))return false;
 if(r.noImpact&&e.impact==='Con saltos'||r.noFloor&&e.floor||r.noOverhead&&e.overhead||r.noKnee&&e.kneeBend||r.noWrist&&e.wristLoad||r.noLoad&&e.equipment==='db'||r.seatedOnly&&!e.seated||r.noSpine&&e.spineMove)return false;
 return true;
}
// Older mission windows use Adapt.mode and Adapt.reasons directly to lock
// all checkboxes, even when a later planner has populated the exercise list.
// Align the display/checkbox filter with the SAME localized-injury rules.
const priorMode=A.mode,priorReasons=A.reasons,priorAllowed=A.allowed;
const localCase=p=>!!p&&(active(p).length||p.limits||p.health?.pain&&p.health.pain!=='none');
A.mode=function(p){
 if(localCase(p)){
  const s=impactStatus(p);
  if(s.ok)return {auto:true,reduced:true,reason:'Entrenamiento limitado a otros grupos musculares. Ejercicios orientativos de intensidad reducida; no representan autorización médica.'};
 }
 return priorMode(p);
};
A.reasons=function(e,p,strict=true){
 if(localCase(p)&&impactStatus(p).ok)return approved(e,p)?[]:['Ejercicio fuera de los filtros de las zonas afectadas o del equipo disponible'];
 return priorReasons(e,p,strict);
};
A.allowed=function(e,p){
 if(localCase(p)&&impactStatus(p).ok)return approved(e,p);
 return priorAllowed(e,p);
};
function list(p,kind){const category=NAMES[kind];return E.exercises.filter(e=>e.category===category&&approved(e,p));}
function rotation(p,items,n,logs,day){
 const injury=active(p)[0]?.zone,priority=GROUPS.preferred[injury]||[].concat(GROUPS.upper,GROUPS.lower,GROUPS.other);
 const hist=new Set(Object.entries(logs||{}).filter(([date,l])=>date<day&&l.complete).flatMap(([,l])=>(l.items||[]).map(x=>E.byId(x.id)?.baseId||x.id)));
 const unique=new Map();for(const e of items)if(!unique.has(e.baseId||e.id))unique.set(e.baseId||e.id,e);
 const options=[...unique.values()];const pivot=options.length?Math.abs(n)%options.length:0;
 return options.sort((a,b)=>{
  const rank=e=>{let score=0;const ix=priority.indexOf(e.group);if(ix>=0)score+=120-ix*13;if(e.level===0)score+=15;if(e.equipment==='body')score+=2;if(hist.has(e.baseId||e.id))score-=15;return score;};
  return rank(b)-rank(a)||((a.id.charCodeAt(0)+pivot)%17)-((b.id.charCodeAt(0)+pivot)%17)||a.id.localeCompare(b.id);
 });
}
function mildDose(e,logs,date){
 const recent=Object.entries(logs||{}).filter(([d,l])=>d<date&&d>=E.plus(date,-7)&&l);
 const tiring=recent.some(([,l])=>l.pain||l.fatigue||l.rpe>=7);
 return {id:e.id,sets:1,target:e.unit==='sec'?(tiring?10:15):(tiring?4:6),rest:90,adaptation379:true,noAutoLoad:true,safetyNote:'Ejercicio orientativo de baja exigencia; detente si produce síntomas o aumenta las molestias.'};
}
function choose(p,kind,n=0,logs={},date=E.dateKey()){
 const status=impactStatus(p);if(!status.ok)return {items:[],blocked:status.reason,requested:kind};
 if(kind==='rest')return {items:[],effective:'rest',requested:'rest'};
 const primary=list(p,kind);
 let effective=kind,candidates=primary;
 if(!candidates.length){const fallbacks=kind==='cardio'?['strength','recovery']:kind==='strength'?['recovery','cardio']:['strength','cardio'];
  for(const other of fallbacks){const pool=list(p,other);if(pool.length){effective=other;candidates=pool;break;}}
 }
 if(!candidates.length)return {items:[],blocked:'No hay ejercicios de otros grupos que superen los filtros de zona, postura y equipo. Es necesario ajustar las restricciones o el catálogo, no convertir el día en descanso.',requested:kind};
 const arranged=rotation(p,candidates,n,logs,date),duration=Math.max(10,Math.min(45,Number(p.minutes)||20));
 const amount=Math.min(duration<=10?2:duration<=20?3:4,arranged.length);
 // Prefer variety across muscle groups rather than 4 variations of the same joint.
 const selected=[],seen=new Set();for(const e of arranged){if(!seen.has(e.group)){selected.push(e);seen.add(e.group);}if(selected.length>=amount)break;}
 for(const e of arranged){if(selected.length>=amount)break;if(!selected.includes(e))selected.push(e);}
 return {items:selected.map(e=>root.HunterCatalog46?.cap(e,p,mildDose(e,logs,date),{status:{reduced:true},logs,date})||mildDose(e,logs,date)),requested:kind,effective,substituted:effective!==kind,status};
}
function keyFor(p,logs,day){
 const s=JSON.stringify({injuries:p.injuries,health:p.health,restrictions:p.restrictions,weekPlan:p.weekPlan,minutes:p.minutes,equipment:p.equipment,experience:p.experience,trainingMode:p.trainingMode,goal:p.goal,difficulty:p.difficulty,focus:p.focus});
 const past=Object.entries(logs||{}).filter(([d,l])=>d<=day&&d>=E.plus(day,-7)&&l).map(([d,l])=>[d,l.pain,l.fatigue,l.rpe,l.complete]);
 let hash=2166136261;for(const c of s+JSON.stringify(past)){hash^=c.charCodeAt(0);hash=Math.imul(hash,16777619);}return (hash>>>0).toString(36);
}
function locked(d,logs){const log=logs?.[d.date];return !!(d.customized||d.routineId||d.creatorAssigned||W.active(log)||d.tasks?.some(t=>t.done));}
function patchCycle(c,p,logs={},today=E.dateKey()){
 if(!c?.days||!p||!S.valid(p)||(!active(p).length&&!p.limits&&!p.health?.special&&!['mild','moderate','strong'].includes(p.health?.pain)))return 0;
 let edits=0;
 for(const [index,d] of c.days.entries()){
  if(d.date<today||locked(d,logs)||root.HunterStable3828?.keep?.(d,p,logs,today))continue;
  const choice=p.weekPlan[S.weekday(d.date)];if(!choice)continue;
  if(choice==='rest'){if(d.kind!=='rest'||d.items?.length||d.blocked378){d.kind='rest';d.items=[];d.title='Descanso elegido';d.scheduleChoice='rest';d.tasks=[];delete d.blocked378;delete d.adapted378;edits++;}continue;}
  const key=keyFor(p,logs,d.date),needs=d.planningKey379!==key||!d.items?.length||!!d.blocked378||!d.adaptation379;
  if(!needs)continue;
  const target=TYPES[choice],proposal=choose(p,target,c.index*7+index,logs,d.date);
  d.kind=target; // The day category is the user's choice, even if a fallback muscle group is used.
  d.scheduleChoice=choice;d.originalCategory378=LABELS[choice];d.title=proposal.blocked?LABELS[choice]+' · requiere revisión':LABELS[choice]+(proposal.substituted?' · alternativa de '+NAMES[proposal.effective]:' · otros grupos musculares');
  d.items=proposal.items;d.tasks=[];d.adapted378=true;d.adaptation379=VER;d.planningKey379=key;
  if(proposal.blocked){d.blocked378=proposal.blocked;d.planningNote='Sesión programada, sin selección automática por las restricciones indicadas.';}
  else {delete d.blocked378;d.planningNote=(proposal.substituted?'No se encontraron alternativas del mismo tipo; se ofrecen ejercicios de '+NAMES[proposal.effective].toLowerCase()+'. ':'')+
    'Selección de baja intensidad, priorizando grupos fuera de las zonas afectadas. No indica rehabilitación ni comprueba individualmente la seguridad clínica.';}
  edits++;
 }
 if(edits)c.injuryPlannerVersion379=VER;
 return edits;
}
function repairEmptyLog(d){
 if(!d||!d.items?.length)return false;
 const l=state?.logs?.[d.date];if(!l||W.active(l)||l.complete||l.pain||l.manual)return false;
 if(l.items?.length&&l.items.every(x=>d.items.some(y=>y.id===x.id&&y.sets===x.sets&&y.target===x.target)))return false;
 // This is ONLY the unstarted log created by viewing a 0/0 mission.
 l.items=d.items.map(x=>({...x,actual:Array.from({length:x.sets},()=>({done:false,value:x.target,kg:0}))}));
 l.kind=d.kind;return true;
}
const originalCycle=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){
 const c=originalCycle(p,start,index,last,logs);patchCycle(c,p,logs,start);return c;
};
const previousCurrent=currentCycle;
currentCycle=function(){
 const c=previousCurrent();if(!c||!state.profile)return c;
 const changes=patchCycle(c,state.profile,state.logs||{},E.dateKey());
 const d=c.days.find(x=>x.date===E.dateKey());const repaired=repairEmptyLog(d);
 if((changes||repaired)&&typeof save==='function')save();return c;
};
const beforeLog=getLog;
getLog=function(){
 const l=beforeLog(),d=typeof todayDay==='function'?todayDay():null;
 if(repairEmptyLog(d)&&typeof save==='function')save();return state.logs?.[d?.date]||l;
};
// Center of routines: provide actual selectable templates for restricted profiles.
const prevAll=allRoutines;
function templates(p,logs={}){
 if(!p||!active(p).length&&!p.limits)return [];
 const status=impactStatus(p);if(!status.ok)return [];
 const generated=[],ids=new Set();
 for(const [kind,category] of [['strength','Fuerza'],['cardio','Cardio'],['recovery','Movilidad']]){
  for(let n=0;n<4;n++){
   const c=choose(p,kind,n*5,logs),items=c.items;if(!items?.length)continue;
   const sig=items.map(x=>x.id).sort().join('/');if(ids.has(sig))continue;ids.add(sig);
   generated.push({id:'injury379-'+kind+'-'+n,name:category+' · otros grupos '+(n+1),category:NAMES[c.effective],description:'Movimientos seleccionados fuera de las zonas declaradas, intensidad reducida; sin validez médica individual.',items,requirements:[],level:0,goalGroups:['fitness','habit','movement','build','athletic'],extension:'standard',family:'adapted',impact:'Sin saltos',minutes:Math.max(8,items.length*4),safe379:true});
  }
 }
 return generated;
}
allRoutines=function(){return [...prevAll(),...templates(state.profile,state.logs||{})];};
const oldRoutineCompatible=A.routineCompatible;
A.routineCompatible=function(r,p){return r?.safe379?!!impactStatus(p).ok&&!!r.items?.length&&r.items.every(x=>approved(E.byId(x.id),p)):oldRoutineCompatible(r,p);};
const oldRoutineList=routineList;
routineList=function(){
 const old=oldRoutineList(),p=state.profile;
 if(!p||!active(p).length&&!p.limits)return old;
 const safe=templates(p,state.logs||{}).filter(r=>A.routineCompatible(r,p));
 const used=new Set(old.map(r=>r.id));return [...old,...safe.filter(r=>!used.has(r.id))];
};
const beforeTechnique=technique;
technique=function(id){
 const e=E.byId(id);if(!e?.noVector379)return beforeTechnique(id);
 const esc=s=>String(s??'').replace(/[&<>\"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','\"':'&quot;',"'":'&#39;'}[c]));
 modal('<span class="eyebrow">'+esc(e.group)+' · MOVIMIENTO SIN EQUIPO</span><h2>'+esc(e.name)+'</h2><div class="notice">No existe una demostración animada validada para esta ficha nueva. Sigue únicamente las instrucciones textuales y evita el ejercicio si produce molestias.</div><ol class="tech-steps">'+e.instructions.map(x=>'<li>'+esc(x)+'</li>').join('')+'</ol><p>'+esc(e.cue)+'</p>');
};
const oldRoutineView=routines;
routines=function(){
 let html=oldRoutineView();if(!state.profile||!active(state.profile).length)return html;
 // Remove the legacy gating banner (which used the old blocked-only calculation).
 html=html.replace(/^<div class="notice injury-banner378" role="note">[\s\S]*?<\/div>/,'');
 const status=impactStatus(state.profile),num=templates(state.profile,state.logs||{}).length;
 const shown=status.ok?templates(state.profile,state.logs||{}):[];
 const esc=s=>String(s??'').replace(/[&<>\"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','\"':'&quot;',"'":'&#39;'}[c]));
 const recommended=shown.length?'<section class="card"><span class="eyebrow">RUTINAS ADAPTADAS A TU PERFIL</span><h2>Otros grupos musculares</h2><p>Selecciona una sesión con movimientos fuera de las zonas limitadas.</p><div class="routine-grid">'+shown.map(r=>'<button class="btn ghost" type="button" onclick="routineDetail(\''+r.id+'\')"><b>'+esc(r.name)+'</b><small> · '+r.items.length+' movimientos · intensidad reducida</small></button>').join('')+'</div></section>':'';
 return '<div class="notice injury-banner378" role="note">'+
  (status.ok?'Alternativas disponibles para grupos no afectados: '+num+' plantillas. Baja exigencia; detén cualquier movimiento que agrave los síntomas.':'No se han asignado ejercicios automáticos: '+String(status.reason).replace(/[<>&"]/g,''))+
  '</div>'+recommended+html;
};
const beforeDetail=routineDetail;
routineDetail=function(id){
 beforeDetail(id);
 if(!id?.startsWith('injury379-'))return;
 const select=document.querySelector('#routineDay');if(select){const c=currentCycle();for(const option of [...select.options]){const d=c?.days?.find(x=>x.date===option.value);if(!d||S.valid(state.profile)&&state.profile.weekPlan[S.weekday(d.date)]==='rest')option.remove();}}
};
const beforePrepare=prepareRoutine;
prepareRoutine=function(id){
 if(id?.startsWith('injury379-')){
  const date=document.querySelector('#routineDay')?.value;
  if(!date||state.profile?.weekPlan[S.weekday(date)]==='rest')return routineNotice372('Elige un día de entrenamiento, no uno de descanso.');
 }
 return beforePrepare(id);
};
root.HunterPlanner379={version:VER,status:impactStatus,approved,choose,patchCycle,repairEmptyLog,templates};
if(!Runtime20.initializing)try{currentCycle();render();}catch(err){console.warn('Motor V37.9',err);}
})(window);
