/* Weekly central-body integration 38.47. */
/* Hunter System 38.27 — unificación local de planificación y progresión exigente.
   No añade ventanas, no cambia sesiones manuales/completadas y no prescribe rehabilitación.
   Las 15 mejoras excluidas corresponden a propuestas, NO a ejercicios. */
(function(root){
'use strict';
if(root.HunterUnified3827)return;
const E=root.Engine,D=root.HunterDynamic381,A=root.Adapt;
if(!E||!D||!A)return;
const categories=[['strength','Fuerza'],['cardio','Cardio'],['recovery','Movilidad']];
const toNumber=(x,min,max)=>{const n=Number(x);return Number.isFinite(n)?Math.max(min,Math.min(max,n)):min;};
const risk=p=>Boolean(p?.limits||p?.health?.special||p?.health?.acute||p?.health?.redFlags||
 (p?.health?.pain&&p.health.pain!=='none')||(p?.injuries||[]).some(x=>x&&x.stage!=='past'));
const activeLog=l=>Boolean(l&&(l.complete||l.manual||l.pain||l.fatigue||l.selfReported||
  l.missionTasks?.some(t=>t.done)||l.items?.some(x=>x.selfReported||x.missionState||x.actual?.some(a=>a.done))));
const isDay=s=>/^\d{4}-\d{2}-\d{2}$/.test(s);
function records(logs,date){
 const start=E.plus(date,-14),recent=Object.entries(logs||{}).filter(([d,l])=>isDay(d)&&d<date&&d>=start&&l);
 const completed=recent.filter(([,l])=>l.complete===true&&!l.manual&&!l.pain&&
  l.items?.some(x=>x.actual?.some(a=>a.done===true))&&Number(l.rpe)>0).map(([,l])=>l);
 const avg=completed.length?completed.reduce((s,l)=>s+Number(l.rpe),0)/completed.length:0;
 const symptoms=recent.some(([,l])=>l.pain||l.fatigue||Number(l.rpe)>=8)||
   !!root.HunterConstraints3811?.symptomTrend?.(logs,date)?.recentSymptom;
 return {completed:completed.length,average:avg,symptoms,ready:completed.length>=2&&avg>=3&&avg<=7&&!symptoms};
}
function dose(e,p,kind,status,feedback,date,legacy,weekIndex,logs,base){
 const out={...legacy},b=base||legacy;
 if(!e||!p||p.age<18||weekIndex<1||weekIndex>2||
   !['strength','cardio'].includes(kind)||risk(p)||status?.reduced||feedback?.strain||
   out.noAutoLoad||Number(p.trainingScale)<1&&p.trainingScale!==undefined)return out;
 const data=records(logs,date);
 if(!data.ready||feedback?.canProgress!==true)return out;
 const seconds=e.unit==='sec',initial=toNumber(b.target,1,120),current=toNumber(out.target,1,120);
 const aggressive=weekIndex===1?.10:.18;
 const limit=seconds?90:(p.difficulty==='advanced'?24:p.difficulty==='intermediate'?20:16);
 const wanted=Math.min(limit,Math.ceil(initial*(1+aggressive)));
 // Supera la progresión conservadora sin duplicar los aumentos del motor anterior.
 out.target=Math.max(current,wanted);
 out.sets=toNumber(out.sets,1,4);
 // Intensificar una sola variable por revisión. Sin series extra automáticas.
 out.progression3827={mode:'exigente',week:weekIndex+1,evidence:data.completed,
  reason:'Mejora condicionada a sesiones completadas, RPE 3–7 y ausencia de molestias.'};
 return out;
}
function gymTemplates(p,logs={}){
 if(p?.equipment!=='gym'||!D.gate(p).ok)return [];
 const level=typeof root.TrainingProgression19?.level==='function'?root.TrainingProgression19.level(p):
   ({basic:0,intermediate:1,advanced:2}[p.difficulty]||0);
 const focus=[['full','general'],['upper','upper'],['lower','lower'],['core','core'],['push','upper'],['pull','upper']];
 const date=E.dateKey(),output=[];
 const pastLogs=Object.fromEntries(Object.entries(logs||{}).filter(([d])=>d<date));
 for(const [family,body]of focus){
  for(const [kind,label]of categories){
   if(family==='core'&&kind!=='strength')continue;
   const candidate={...p,focus:body,dynamicRequestedFocus3847:body,dynamicGymPreferred3827:true};
   const chosen=D.choose(candidate,kind,'38.27|'+date+'|gym|'+family+'|'+kind,pastLogs,date,new Set(),null,0);
   if(!chosen.items?.length)continue;
   // Coherencia con el mismo filtro usado para misiones y sesiones diarias.
   if(!chosen.items.every(item=>{const e=E.byId(item.id);return e&&D.compatible(e,p,risk(p));}))continue;
   const all=chosen.items.map(item=>E.byId(item.id));
   const known=new Set(Object.keys(root.Training23?.gymEquipment||{}));
   const gymNeeds=[...new Set(all.flatMap(e=>e.gymRequirements23||[]).filter(x=>known.has(x)))];
   const requirements=[...new Set(all.flatMap(e=>Array.isArray(e.requirements)?e.requirements:[]))];
   output.push({id:'dyn3827-gym-'+family+'-'+kind,name:'Gimnasio dinámico · '+label+' · '+
       ({full:'Completo',upper:'Superior',lower:'Inferior',core:'Tren central',push:'Empuje',pull:'Tracción'}[family]),
    category:chosen.effective==='cardio'?'Cardio':chosen.effective==='recovery'?'Movilidad':'Fuerza',
    level,items:chosen.items,requirements,goalGroups:['fitness','habit','movement','build','athletic'],
    family:'gym-'+family,gymOnly23:true,gymRequirements23:gymNeeds,extension:'standard',
    minutes:Math.max(10,Math.min(90,Number(p.minutes)||20)),safe381:true,dynamic3827:true,
    description:'Rutina local calculada a partir de las demostraciones 2D y las preferencias del perfil.'});
  }
 }
 return output;
}
// Reutilizar el centro de rutinas y la sección de gimnasio; no crear otro panel.
if(typeof allRoutines==='function'){
 const old=allRoutines;
 allRoutines=function(){return [...old(),...gymTemplates(state?.profile,state?.logs||{})];};
}
// Evitar que una asignación AUTOMÁTICA heredada impida el generador común.
// No se toca ninguna asignación manual por routineId, sesión personalizada o con datos.
function unify(c,p,logs,today){
 if(!c?.days||!p||!D.patch||c.reviews?.at(-1)?.action==='pausar')return 0;
 let released=0;
 for(const d of c.days){
  if(d.date<today||d.customized||activeLog(logs?.[d.date])||d.tasks?.some(t=>t.done)||root.HunterStable3828?.keep?.(d,p,logs,today))continue;
  if(d.creatorAssigned===true){
   delete d.routineId;delete d.creatorAssigned;delete d.sourceSequence;delete d.autoTemplateId;
   delete d.dynamicKey381;released++;
  }
 }
 if(released)delete c.dynamicKey381;
 return released+D.patch(c,p,logs,today);
}
if(typeof currentCycle==='function'){
 const previous=currentCycle;
 currentCycle=function(){const c=previous();if(!c||!state?.profile)return c;
  const changed=unify(c,state.profile,state.logs||{},E.dateKey());
  if(changed&&typeof save==='function'&&!root.Runtime20?.initializing)save();
  return c;
 };
}
root.HunterUnified3827=Object.freeze({version:'38.27',records,dose,gymTemplates,unify,risk,
 diagnostics:()=>({engine:'HunterDynamic381',sources:['misiones','diario','gimnasio','casa'],offline:true,
  progression:'exigente, basada en rendimiento; no incremento ante riesgo'})});
})(typeof window==='undefined'?globalThis:window);
