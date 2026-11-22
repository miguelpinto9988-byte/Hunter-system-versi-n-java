/* Todo el catálogo participa en grupos, bloques y rotación semanal. */
(function(root){'use strict';
const E=Engine,T=TrainingProgression19,O=OwnRoutines18,oldReasons=Adapt.reasons,oldMake=E.makeCycle,oldRecommend=AdaptiveV9.recommend;
for(const e of E.exercises){e.sourceOnly=false;e.demoPath30='demos30/'+e.id+'.svg';e.modelPath30='demos30/'+e.id+'.json';e.planFamily30=e.group==='Cardio'?'cardio':e.group==='Movilidad'?'mobility':['Piernas','Cadera'].includes(e.group)?'legs':e.group==='Abdomen'?'core':['Pecho','Hombros'].includes(e.group)?'push':'pull';}
const tier=e=>e.progressionTier23??e.progressionTier19??T.tier(e.id),groups={full:['Piernas','Cadera','Pecho','Espalda','Hombros','Brazos','Abdomen'],push:['Pecho','Hombros'],pull:['Espalda','Brazos'],legs:['Piernas','Cadera'],core:['Abdomen'],cardio:['Cardio'],mobility:['Movilidad'],control:['Piernas','Cadera','Pecho','Espalda','Hombros','Brazos','Abdomen']};
Adapt.reasons=function(e,p,strict=true){const out=oldReasons(e,p,strict);if(e&&p&&tier(e)>T.level(p)&&!O.gentle(p)&&!out.includes('Variante fuera del escalón seleccionado'))out.push('Variante fuera del escalón seleccionado');return [...new Set(out)];};
Adapt.allowed=(e,p)=>!!e&&!Adapt.reasons(e,p).length;
const inFamily=(e,f)=>f==='power'?e.family==='power'||e.impact==='Con saltos':f==='balance'?e.group==='Movilidad'||/equilibrio|a una pierna/.test(e.name.toLowerCase()):f==='agility'?e.group==='Cardio'&&/paso|agilidad|patinador|coordin|lateral|cambio/.test(e.name.toLowerCase()):f==='seated'?e.seated:f==='intervals'?e.group==='Cardio':(groups[f]||groups.full).includes(e.group);
function pool(p,f='full'){return E.exercises.filter(e=>inFamily(e,f)&&tier(e)<=T.level(p)&&Adapt.allowed(e,p)&&(p.equipment==='gym'?e.gymOnly23:!e.gymOnly23));}
function select(list,count,rotation=0,p=null){if(!list.length)return [];const buckets=[...new Set(list.map(e=>e.group))],used=new Set(),out=[];
 for(let i=0;i<count;i++){let candidates=list.filter(e=>!used.has(e.baseId)&&e.group===buckets[i%buckets.length]);if(!candidates.length)candidates=list.filter(e=>!used.has(e.baseId));if(!candidates.length)break;
  const targetTier=p?T.level(p):Math.max(...candidates.map(tier)),high=candidates.filter(e=>tier(e)===targetTier);if(high.length)candidates=high;
  if(p){const goalBest=Math.max(...candidates.map(e=>GoalContext24.score(e,p))),relevant=candidates.filter(e=>GoalContext24.score(e,p)>=goalBest-1);if(relevant.length)candidates=relevant;}
  candidates.sort((a,b)=>a.id.localeCompare(b.id));const pick=candidates[(Math.abs(rotation)+Math.floor(i/buckets.length)*3)%candidates.length];out.push(pick);used.add(pick.baseId);
 }return out;
}
function dose(e,p,kind){const base=Training23.dose(e,p,kind);return {...base,rest:T.rest(p)};}
function choose(profile,kind,rotation=0){const p=GoalContext24.adjusted(profile);if(!Adapt.mode(p).auto)return [];const f=kind==='cardio'?'cardio':kind==='recovery'?'mobility':p.focus==='lower'?'legs':p.focus==='core'?'core':p.focus==='upper'?'upper':'full';let list=f==='upper'?[...pool(p,'push'),...pool(p,'pull')]:pool(p,f);if(!list.length&&kind==='recovery'&&p.equipment==='gym')list=pool(p,'cardio');if(!list.length&&kind==='strength')list=pool(p,'full');const count=kind==='recovery'?3:p.trainingMode==='routines'?5:p.dailyMissions||4;return select(list,count,rotation,p).map(e=>dose(e,p,kind));}
const oldRoutines=Catalog.routines.map(r=>({...r}));
for(const r of oldRoutines){const p={age:30,equipment:r.gymOnly23?'gym':r.requirements.includes('db')?'db':'body',gymEquipment23:Object.keys(Training23.gymEquipment),bench:true,step:true,difficulty:O.keys[r.level],experience:'regular',injuries:[],restrictions:{},health:{pain:'none'},trainingScale:1};const f=r.gymOnly23?r.family.includes('lower')||r.family.includes('glutes')?'legs':r.family.includes('upper')?'full':r.family.includes('push')?'push':r.family.includes('pull')?'pull':r.family.includes('core')?'core':r.category==='Cardio'?'cardio':'full':r.family;const picked=select(pool(p,f),r.items.length,oldRoutines.indexOf(r)+17,p);if(picked.length>=Math.min(3,r.items.length)){r.items=picked.map(e=>dose(e,p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength'));r.requirements=[...new Set(picked.flatMap(e=>e.requirements||[]))];r.gymRequirements23=[...new Set(picked.flatMap(e=>e.gymRequirements23||[]))];r.minutes=O.estimate(r.items);}r.planVersion=30;}
// Colecciones completas, no fichas aisladas: todas las 1000 variantes quedan
// en un bloque secuencial de una rutina correspondiente al lugar y nivel.
const coverage=[];let serial=0;
for(const place of ['home','gym'])for(let n=0;n<3;n++)for(const family of ['push','pull','legs','core','cardio','mobility']){
 const unordered=E.exercises.filter(e=>!!e.gymOnly23===(place==='gym')&&tier(e)===n&&e.planFamily30===family).sort((a,b)=>a.id.localeCompare(b.id)),chunks=[];
 while(unordered.length){const used=new Set(),chunk=[];for(let k=0;k<unordered.length&&chunk.length<5;){if(used.has(unordered[k].baseId)){k++;continue;}const e=unordered.splice(k,1)[0];chunk.push(e);used.add(e.baseId);}chunks.push(chunk);}
 for(const [i,chunk]of chunks.entries()){const p={age:30,equipment:place==='gym'?'gym':chunk.some(e=>e.requirements.includes('db'))?'db':'body',gymEquipment23:Object.keys(Training23.gymEquipment),bench:true,step:true,difficulty:O.keys[n],experience:'regular',injuries:[],restrictions:{},health:{pain:'none'},trainingScale:1};if(chunk.length<3){for(const e of pool(p,family)){if(chunk.some(x=>x.baseId===e.baseId))continue;chunk.push(e);if(chunk.length>=3)break;}}const items=chunk.map(e=>dose(e,p,family==='cardio'?'cardio':family==='mobility'?'recovery':'strength'));coverage.push({id:'collection30-'+(++serial),name:({push:'Empuje y hombros',pull:'Espalda y brazos',legs:'Piernas y cadera',core:'Estabilidad abdominal',cardio:'Cardio y coordinación',mobility:'Movilidad y control'})[family]+' · '+O.names[n]+' · '+(i+1),category:family==='cardio'?'Cardio':family==='mobility'?'Movilidad':'Fuerza',level:n,items,requirements:place==='gym'?[]:[...new Set(chunk.flatMap(e=>e.requirements))],gymOnly23:place==='gym',gymRequirements23:[...new Set(chunk.flatMap(e=>e.gymRequirements23||[]))],family:place==='gym'?'gym-'+family:family,goalGroups:family==='mobility'?['movement','habit']:family==='cardio'?['fitness','athletic','habit']:['build','fitness','athletic','habit'],extension:'standard',impact:chunk.some(e=>e.impact==='Con saltos')?'Con saltos':'Sin saltos',minutes:O.estimate(items),planVersion:30,description:'Sesión original secuencial con cardio inicial y final, series y descansos. Cada bloque usa un patrón distinto dentro de esta sesión.',collection30:true});}
}
Catalog.routines=[...oldRoutines,...coverage];O.routines.splice(0,O.routines.length,...Catalog.routines.filter(r=>!r.gymOnly23));Training23.gymRoutines.splice(0,Training23.gymRoutines.length,...Catalog.routines.filter(r=>r.gymOnly23));Catalog.baseRoutines=Catalog.routines.length;
function adjust(r,p){return r.items.map(x=>({...x,...dose(E.byId(x.id),p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength')}));}
O.adjust=adjust;Training23.adjust=adjust;Training23.pool=pool;Training23.choose=choose;Schedule.items=choose;
for(const id of Catalogue30.bases)T.chains.push([id,id+'-ecc30',id+'-hold30']);
// Puentes de dificultad solo entre gestos que comparten apoyos y patrón.
T.chains.push(['gym-leg-press23','gym-presssingle30']);T.chains.push(['gym-chest-machine23','gym-pressone30']);T.chains.push(['bridge','home-bridgemarch30','home-thrustsingle30']);T.chains.push(['plank','home-plankstep30','home-plankdrag30']);
function assign(c,profile,logs={},now=E.dateKey(),last=null){const p=GoalContext24.adjusted(profile),planningKey=JSON.stringify([30,profile,GoalContext24.personal()]),review=c.reviews?.at(-1);if(!Adapt.mode(p).auto||review?.action==='pausar')return 0;if(c.engineVersion30===30&&c.planningKey30===planningKey&&!(review?.action==='reducir'&&!c.reductionApplied30))return 0;let changed=0;
 for(const [i,d]of c.days.entries()){if(d.date<now||d.kind==='rest'||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;let items=null;
  if(p.trainingMode==='routines'){const goal=GoalContext24.interpret(p),matches=Catalog.routines.filter(r=>r.level===T.level(p)&&!!r.gymOnly23===(p.equipment==='gym')&&r.extension==='standard'&&r.goalGroups.some(g=>goal.priorities.includes(g))&&Adapt.routineCompatible(r,p));let list=matches.filter(r=>d.kind==='cardio'?r.category==='Cardio':d.kind==='recovery'?r.category==='Movilidad':r.category==='Fuerza');if(!list.length&&d.kind==='strength')list=matches;if(list.length){const r=list[(i+c.index*7)%list.length];items=adjust(r,p);d.title=r.name;d.autoTemplateId=r.id;}}
  if(!items?.length){items=choose(p,d.kind,i+c.index*11);d.title='Misiones personales · '+O.names[T.level(p)];}
  if(!items.length)continue;
  // Repite hasta dos movimientos de referencia de la semana anterior. Los
  // accesorios rotan por la biblioteca; las referencias aportan registros
  // comparables para que la progresión no dependa de ejercicios siempre nuevos.
  if(last&&d.kind==='strength'&&!O.gentle(p)&&T.level(last.profile||p)===T.level(p)){
   const validGroups=new Set(items.map(x=>E.byId(x.id).group)),used=new Set(),anchors=[],referenceSets={};
   for(const x of last.days.filter(x=>x.kind==='strength').flatMap(x=>x.items||[])){const e=E.byId(x.id);if(!e||used.has(e.baseId)||!validGroups.has(e.group)||!Adapt.allowed(e,p)||tier(e)>T.level(p))continue;anchors.push(dose(e,p,'strength'));referenceSets[e.id]=x.sets;used.add(e.baseId);if(anchors.length>=Math.min(2,items.length))break;}
   if(anchors.length){items=[...anchors,...items.filter(x=>!used.has(E.byId(x.id).baseId))].slice(0,items.length);d.referenceIds30=anchors.map(x=>x.id);d.referenceSets30=referenceSets;}
  }
  const old={days:[{items:d.items||[]}]};items=items.map(x=>{let carried=T.carry(T.carry(x,last,p),old,p);if(d.referenceIds30?.includes(carried.id)&&!O.gentle(p)){carried={...carried,sets:d.referenceSets30?.[carried.id]||carried.sets};return T.applyRecommendation(carried,AdaptiveV9.recommend(logs,carried,p,now),p);}return carried;});
  if(c.reviews?.at(-1)?.action==='reducir')items=items.map(x=>({...x,target:Math.max(E.unitOf(x)==='sec'?10:3,Math.floor(x.target*.8))}));
  d.items=items;d.tasks=[];d.goalIntent24=GoalContext24.interpret(p).reason;d.planningNote='Selección del catálogo completo según objetivo, equipo, nivel y restricciones. Revisión semanal con series confirmadas.';changed++;
 }c.engineVersion30=30;c.planningKey30=planningKey;if(review?.action==='reducir')c.reductionApplied30=true;return changed;
}
E.makeCycle=function(p,start,index,last,logs={}){const c=oldMake(p,start,index,last,logs);assign(c,p,logs,start,last);return c;};
Training23.assign=assign;
const previousCurrent=currentCycle;
currentCycle=function(){const c=previousCurrent();if(!c||!state.profile)return c;const key=JSON.stringify([30,state.profile,GoalContext24.personal()]);if(c.planningKey30!==key){assign(c,state.profile,state.logs,E.dateKey(),state.cycles.filter(x=>x.start<c.start).at(-1));c.planningKey30=key;save();}return c;};
root.Training30={pool,select,choose,assign,adjust,tier,coverage,version:30};
})(window);
