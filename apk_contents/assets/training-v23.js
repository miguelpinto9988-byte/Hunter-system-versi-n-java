/* Biblioteca ampliada, filtro de gimnasio y progresión con evidencia confirmada. */
(function(root){'use strict';
const E=Engine,O=OwnRoutines18,T=TrainingProgression19,oldReasons=Adapt.reasons,oldDose=O.dose,oldChoose=T.choose,oldMake=E.makeCycle,oldOrganize=RoutinePlanner.organize;
const gymEquipment={barbell:'Barra y discos',cable:'Poleas',machine:'Máquinas de fuerza',smith:'Máquina Smith',cardio:'Cinta o ergómetro',bench:'Banco y soportes'};
function reasons(e,p,strict=true){
 if(!e)return ['Ejercicio desconocido'];const normalized=p?.equipment==='gym'?{...p,equipment:'db',bench:(p.gymEquipment23||[]).includes('bench')}:p;
 const list=oldReasons(e,normalized,strict);
 if(e.gymOnly23){if(p?.equipment!=='gym')list.push('Requiere perfil de gimnasio');for(const key of e.gymRequirements23||[])if(!(p?.gymEquipment23||[]).includes(key))list.push('Falta '+gymEquipment[key]);}
 if(p?.restrictions?.noLoad&&e.loaded23&&!list.includes('Carga externa excluida'))list.push('Carga externa excluida');
 if(e.pack23&&p&&T.tier(e.id)>T.level(p)&&!Adapt.mode(p).reduced)list.push('Variante fuera del escalón seleccionado');
 return [...new Set(list)];
}
Adapt.reasons=reasons;Adapt.allowed=(e,p)=>!reasons(e,p).length;Adapt.routineCompatible=(r,p)=>!!p&&Adapt.mode(p).auto&&r.items.every(x=>Adapt.allowed(E.byId(x.id),p));
function dose(e,p,kind){
 const v=oldDose(e,p,kind);if(!e.pack23)return v;
 if(O.gentle(p)||kind==='recovery')return {...v,sets:1,target:e.unit==='sec'?15:5,rest:T.rest(p)};
 const n=T.level(p),beginner=p.experience==='beginner',range=e.repRange19||[8,16],scale=TrainingIntensity.scale(p);
 const sets=beginner?2:[4,4,5][n],target=e.unit==='sec'?[30,40,50][n]:Math.min(range[1],range[0]+[4,2,0][n]);
 return {...v,sets,target:Math.max(e.unit==='sec'?10:3,Math.floor(target*scale)),rest:T.rest(p)};
}
O.dose=dose;TrainingIntensity.dose=dose;
const newBases=ExercisePack23.baseIds.map(id=>E.byId(id));
for(const e of newBases)T.chains.push([e.id,e.id+'-tempo23',e.id+'-pause23']);
T.chains.push(['pushup-control19','home-handrelease23','home-staggered23','home-diamond23']);
T.chains.push(['single-squat19','home-pistol-box23','home-skater-squat23','home-shrimp23']);
T.chains.push(['plankreach19','home-body-saw23','home-bear-tap23']);
T.chains.push(['singlebridge-pause19','home-ham-slide23','home-ham-slide-one23']);
const familyGroups48={push:['Pecho','Hombros'],pull:['Espalda','Brazos'],legs:['Piernas','Cadera'],core:['Abdomen','Core'],full:['Piernas','Cadera','Pecho','Espalda','Hombros','Brazos','Abdomen'],control:['Piernas','Cadera','Pecho','Espalda','Hombros','Brazos','Abdomen'],cardio:['Cardio'],intervals:['Cardio'],agility:['Cardio'],power:['Cardio'],mobility:['Movilidad'],balance:['Movilidad'],seated:['Movilidad']};
function familyMatches(e,family){return (familyGroups48[family]||[]).includes(e.group);}
// Only cache immutable startup template construction, never user eligibility.
let constructing48=true;const startupPools48=new Map();
function pool(p,family='full',includeVariants=true){
 const key=constructing48?JSON.stringify([p,family,includeVariants]):null;
 if(key&&startupPools48.has(key))return startupPools48.get(key).slice();
 const n=T.level(p);let list=E.exercises.filter(e=>!e.sourceOnly&&familyMatches(e,family)&&T.tier(e.id)<=n&&Adapt.allowed(e,p)&&(includeVariants||e.variant==='base'));
 if(p.equipment==='gym')list=list.filter(e=>e.gymOnly23);
 else list=list.filter(e=>!e.gymOnly23);
 list.sort((a,b)=>Math.abs(n-T.tier(a.id))-Math.abs(n-T.tier(b.id))||Number(!a.pack23)-Number(!b.pack23));
 if(key)startupPools48.set(key,list.slice());return list;
}
function balanced(list,count,rotation=0,p=null){
 if(!list.length)return [];const groups=[...new Set(list.map(e=>e.group))],used=new Set(),out=[];
 for(let i=0;i<count;i++){const group=groups[i%groups.length],same=list.filter(e=>e.group===group&&!used.has(e.baseId));let candidates=same.length?same:list.filter(e=>!used.has(e.baseId));if(!candidates.length)break;const highest=Math.max(...candidates.map(e=>T.tier(e.id)));candidates=candidates.filter(e=>T.tier(e.id)===highest);if(p){const best=Math.max(...candidates.map(e=>GoalContext24.score(e,p)));candidates=candidates.filter(e=>GoalContext24.score(e,p)===best);}const e=candidates[(Math.abs(rotation)+Math.floor(i/groups.length))%candidates.length];out.push(e);used.add(e.baseId);}
 return out;
}
function choose(p,kind,rotation=0){
 p=GoalContext24.adjusted(p);
 if(!Adapt.mode(p).auto)return [];
 const count=Math.min(7,p.trainingMode==='routines'?5:p.dailyMissions||4),focus=p.focus==='upper'?'push':p.focus==='lower'?'legs':p.focus==='core'?'core':'full';
 if(O.gentle(p)&&p.equipment!=='gym')return oldChoose(p,kind,rotation);
 let list=pool(p,kind==='cardio'?'cardio':kind==='recovery'?'mobility':focus);
 if(p.focus==='upper'&&kind!=='cardio'&&kind!=='recovery')list=[...pool(p,'push'),...pool(p,'pull')];
 if(!list.length&&p.equipment==='gym'&&kind==='cardio')list=pool(p,'full');
 if(!list.length)return oldChoose(p,kind,rotation).filter(x=>Adapt.allowed(E.byId(x.id),p));
 return balanced(list,kind==='recovery'?Math.min(3,count):count,rotation,p).map(e=>dose(e,p,kind));
}
Schedule.items=choose;
const gymFamilies={
 full:{name:'Cuerpo completo en gimnasio',groups:['Piernas','Pecho','Espalda','Cadera','Abdomen'],goals:['build','fitness','habit']},
 upper:{name:'Tren superior en gimnasio',groups:['Pecho','Espalda','Hombros','Brazos'],goals:['build','fitness']},
 lower:{name:'Piernas y glúteos en gimnasio',groups:['Piernas','Cadera'],goals:['build','athletic']},
 push:{name:'Empuje: pecho, hombros y tríceps',groups:['Pecho','Hombros','Brazos'],goals:['build']},
 pull:{name:'Tirón: espalda y bíceps',groups:['Espalda','Brazos'],goals:['build']},
 glutes:{name:'Cadena posterior en gimnasio',groups:['Cadera','Piernas','Espalda'],goals:['build','athletic']},
 arms:{name:'Brazos y hombros en gimnasio',groups:['Brazos','Hombros'],goals:['build']},
 core:{name:'Zona media con poleas y máquinas',groups:['Abdomen','Espalda'],goals:['build','movement','habit']},
 conditioning:{name:'Acondicionamiento en gimnasio',groups:['Cardio','Piernas','Espalda'],goals:['fitness','athletic','habit']}
};
const gyms=[];
for(const [family,spec]of Object.entries(gymFamilies))for(let n=0;n<3;n++)for(const extension of ['standard','long']){
 const p={equipment:'gym',gymEquipment23:Object.keys(gymEquipment),difficulty:O.keys[n],experience:'regular',age:30,health:{pain:'none'},injuries:[],restrictions:{},trainingScale:1,bench:true};
 let available=E.exercises.filter(e=>e.gymOnly23&&spec.groups.includes(e.group)&&T.tier(e.id)<=n&&Adapt.allowed(e,p));
 if(family==='push')available=available.filter(e=>e.group!=='Brazos'||e.recipe23==='tricep');
 if(family==='pull')available=available.filter(e=>e.group!=='Brazos'||e.recipe23==='curl');
 available.sort((a,b)=>Math.abs(n-T.tier(a.id))-Math.abs(n-T.tier(b.id)));
 const selected=balanced(available,extension==='long'?6:5,n),items=selected.map(e=>dose(e,p,family==='conditioning'?'cardio':'strength'));
 if(!items.length)throw Error('Rutina de gimnasio vacía: '+family+' '+n);
 const r={id:'gym23-'+family+'-'+n+'-'+extension,name:spec.name+' · '+O.names[n]+(extension==='long'?' · Extendida':''),category:family==='conditioning'?'Cardio':'Fuerza',level:n,items,requirements:[],gymOnly23:true,gymRequirements23:[...new Set(selected.flatMap(e=>e.gymRequirements23))],family:'gym-'+family,goalGroups:spec.goals,extension,impact:'Sin saltos',planVersion:23,description:'Rutina original completa de Hunter System: preparación, orden de ejercicios, series, descansos y vuelta a la calma. Usa resistencia controlable y ajusta la sesión a tus registros.',minutes:O.estimate(items)};
 gyms.push(r);
}
// Las mismas materias de casa incorporan los patrones nuevos y conservan sus IDs.
const homes=O.routines.map(r=>{
 const n=r.level,material=r.requirements.includes('db')?'db':'body',p={equipment:material,difficulty:O.keys[n],experience:'regular',age:30,health:{pain:'none'},injuries:[],restrictions:{},trainingScale:1,bench:r.requirements.includes('bench'),step:r.requirements.includes('step')};
 // Mantener las materias técnicas: cardio no sustituye saltos, equilibrio ni movilidad.
 if(['cardio','intervals','agility','power','mobility','balance','seated'].includes(r.family))return {...r,planVersion:23};
 let list=pool(p,r.family);if(!list.length)return {...r,planVersion:23};
 const selected=balanced(list,O.lengths[r.extension].count,n),items=selected.map(e=>{const x=dose(e,p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength');return {...x,sets:Math.max(1,Math.ceil(x.sets*O.lengths[r.extension].ratio))};});
 return {...r,items,requirements:[...new Set(selected.flatMap(e=>e.requirements))],minutes:O.estimate(items),planVersion:23};
});
constructing48=false;startupPools48.clear();
O.routines.splice(0,O.routines.length,...homes);Catalog.routines=[...homes,...gyms];Catalog.baseRoutines=Catalog.routines.length;
function adjust(r,p){return r.items.map(x=>{const e=E.byId(x.id),v=dose(e,p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength');return {...x,...v,sets:O.gentle(p)?1:r.extension==='brief'?Math.max(1,Math.ceil(v.sets*.5)):v.sets};});}
O.adjust=adjust;
function assign(c,p,logs={},now=E.dateKey(),last=null){
 if(!Adapt.mode(p).auto||c.reviews?.at(-1)?.action==='pausar')return 0;let changed=0;const ap=GoalPlans.adjusted(p),n=T.level(p),group=GoalGroups14.of(p),intent=GoalContext24.interpret(p),pattern=[...(GoalPlans.plans[intent.target]||GoalPlans.plans.general).pattern];let ordinal=0,lastStrength=false;
 if(intent.priorities.length>1){const extra=intent.priorities[1];pattern[1]=['fitness','athletic'].includes(extra)?'cardio':extra==='movement'?'recovery':'strength';}
 for(const [i,d]of c.days.entries()){
  const choice=p.weekPlan?.[Schedule.weekday(d.date)];let desired=choice==='rest'?'rest':choice==='mobility'?'recovery':choice==='cardio'?'cardio':'strength';lastStrength=desired==='strength';
  if(d.date<now||d.kind==='rest'||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;
  d.kind=desired;let items;
  if(p.trainingMode==='routines'){
   let list=(p.equipment==='gym'?gyms:homes).filter(r=>r.level===n&&r.extension==='standard'&&r.goalGroups.some(g=>intent.priorities.includes(g))&&Adapt.routineCompatible(r,ap));
   if(ap.focus==='upper')list=list.filter(r=>p.equipment==='gym'?['gym-upper','gym-push','gym-pull','gym-arms'].includes(r.family):['push','pull'].includes(r.family));
   if(ap.focus==='lower')list=list.filter(r=>p.equipment==='gym'?['gym-lower','gym-glutes'].includes(r.family):r.family==='legs');
   if(ap.focus==='core')list=list.filter(r=>p.equipment==='gym'?r.family==='gym-core':r.family==='core');
   const appropriate=list.filter(r=>d.kind==='recovery'?r.category==='Movilidad':d.kind==='cardio'?r.category==='Cardio':!['Cardio','Movilidad'].includes(r.category));
   list=(d.kind==='recovery'||d.kind==='cardio')?appropriate:appropriate.length?appropriate:list;
   if(list.length){const score=r=>r.items.reduce((sum,x)=>sum+GoalContext24.score(E.byId(x.id),ap),0)/r.items.length;const best=Math.max(...list.map(score));list=list.filter(r=>score(r)>=best-.01);}
   if(list.length){const r=list[(i+c.index)%list.length];items=adjust(r,ap);d.title=r.name;d.autoTemplateId=r.id;}
  }
  if(!items?.length)items=choose(ap,d.kind,i+c.index*7);
  if(!items.length)continue;
  const existing={days:[{items:d.items||[]}]};
  items=items.map(x=>{let next=T.carry(T.carry(x,last,ap),existing,ap);if(c.engineVersion23!==23&&c.reviews?.at(-1)?.adherence>=.8&&!O.gentle(ap))next=T.applyRecommendation(next,AdaptiveV9.recommend(logs,next,ap,now),ap);return next;});
  if(c.reviews?.at(-1)?.action==='reducir')items=items.map(x=>({...x,target:Math.max(E.unitOf(x)==='sec'?10:3,Math.floor(x.target*.8))}));
  d.items=items;d.gym23=p.equipment==='gym';d.own18=true;d.own19=true;d.tasks=[];
  if(p.trainingMode!=='routines')d.title=(p.equipment==='gym'?'Misiones de gimnasio':'Misiones personales')+' · '+O.names[n];
  d.goalIntent24=intent.reason;d.planningNote=O.gentle(ap)?'Dosis reducida y movimientos compatibles con las restricciones declaradas.':'Biblioteca ampliada · '+O.names[n]+': patrones, apoyos, cadencia y revisión semanal por registros confirmados.';changed++;
 }
 c.engineVersion23=23;return changed;
}
E.makeCycle=function(p,start,index,last,logs={}){const c=oldMake(p,start,index,last,logs);assign(c,p,logs,start,last);return c;};
RoutinePlanner.organize=function(c,p,source,own,logs,now){if(c.engineVersion23===23)return 0;return assign(c,p,logs,now);};
root.Training23={gymEquipment,gymFamilies,gymRoutines:gyms,homeRoutines:homes,pool,choose,dose,adjust,assign,reasons};
})(window);
