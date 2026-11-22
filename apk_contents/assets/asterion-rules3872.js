/* Asterion 38.72 — deterministic offline rules. No exercise catalogue is bundled.
 * Pure logic for an injected, validated catalogue; no PII-based capacity scoring.
 * Public UMD API supports browser script and Node.js test harness.
 */
(function(factory){
 const api=factory();
 if(typeof module==='object'&&module.exports)module.exports=api;
 if(typeof window!=='undefined')window.AsterionRules3872=api;
})(function(){'use strict';
 const CATS=Object.freeze(['tren_superior','tren_central','tren_inferior','movilidad','cardio']);
 const LABELS=Object.freeze(['Tren superior','Tren central','Tren inferior','Movilidad','Cardio','Descanso']);
 const ALIASES=Object.freeze({tronco:'tren_central',integrado:'tren_central',piernas:'tren_inferior',coordinacion:'movilidad'});
 const STATES=Object.freeze(['PENDING','COMPLETED','SKIPPED']);
 const STOPWORDS=new Set(['de','del','para','la','el','en','por','una','un','me','mi','que','quiero','hacer','a','mas','mejorar','poder']);
 const INTENTS=Object.freeze({
  caminar:['caminar','caminata','andar','pasear'],bicicleta:['bicicleta','ciclismo','pedalear','bici'],
  correr:['correr','carrera','trotar'],danza:['danza','bailar','baile'],equilibrio:['equilibrio','balance'],
  movilidad:['movilidad','moverme'],flexibilidad:['flexibilidad','estiramiento','estirar'],
  postura:['postura','postural'],fuerza_funcional:['fuerza funcional','fuerza'],
  coordinacion:['coordinacion','coordinado'],resistencia:['resistencia','aguante']
 });
 const ISO=/^\d{4}-\d{2}-\d{2}$/;
 const clone=x=>JSON.parse(JSON.stringify(x));
 const normalized=s=>String(s||'').toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g,'').replace(/[^a-z0-9_\s]/g,' ').replace(/\s+/g,' ').trim();
 function cat(v){const s=normalized(v);return CATS.includes(s)?s:(ALIASES[s]||null);}
 const dayIndex=code=>code==='descanso'?5:CATS.indexOf(code);
 function date(day){if(typeof day!=='string'||!ISO.test(day))return null;const d=new Date(day+'T12:00:00Z');return !Number.isNaN(d.getTime())&&d.toISOString().slice(0,10)===day?d:null;}
 function addDays(day,delta){const d=date(day);if(!d)throw Error('Fecha inválida');d.setUTCDate(d.getUTCDate()+delta);return d.toISOString().slice(0,10);}
 function distance(a,b){const x=date(a),y=date(b);if(!x||!y)throw Error('Fecha inválida');return Math.round((x-y)/86400000);}
 function weekday(day){const d=date(day);if(!d)throw Error('Fecha inválida');return (d.getUTCDay()+6)%7;}
 function canonical(v){if(Array.isArray(v))return '['+v.map(canonical).join(',')+']';if(v&&typeof v==='object')return '{'+Object.keys(v).sort().map(k=>JSON.stringify(k)+':'+canonical(v[k])).join(',')+'}';return JSON.stringify(v);}
 // Non-cryptographic deterministic change detector; not for password security.
 function fingerprint(x){let h=2166136261;const s=canonical(x);for(let i=0;i<s.length;i++){h^=s.charCodeAt(i);h=Math.imul(h,16777619)>>>0;}return h.toString(16).padStart(8,'0');}
 function parseGoal(raw){const text=normalized(raw).slice(0,500),found=[],excluded=[];
  for(const [tag,terms] of Object.entries(INTENTS))for(const term of terms){
   const at=text.indexOf(term);if(at<0||!new RegExp('(^|\\s)'+term.replace(/\s+/g,'\\s+')+'(\\s|$)').test(text))continue;
   // Local negation within 5 words, beginning of clause; explicit exclusion dominates.
   const before=text.slice(Math.max(0,at-55),at).split(/\bpero\b|\by\b|[.;]/).at(-1)||'';
   const neg=/(?:\bno\s+(?:quiero|deseo|puedo|hacer|practicar)?\s*$|\bevitar\s*$|\bsin\s*$|\bno\s+me\s+interesa\s*$|\bdejar\s+de\s*$)/.test(before.trim()+' ');
   (neg?excluded:found).push(tag);break;
  }
  return {recognized:[...new Set(found)].filter(t=>!excluded.includes(t)),excluded:[...new Set(excluded)],recognizedAny:found.length+excluded.length>0,savedText:String(raw||'').slice(0,500)};
 }
 function validProfile(p){const errors=[];
  if(!p||typeof p!=='object')return {ok:false,errors:['No hay perfil activado']};
  if(!['principiante','intermedio','avanzado'].includes(p.experience))errors.push('Experiencia inválida');
  if(!Array.isArray(p.weekPlan)||p.weekPlan.length!==7||!p.weekPlan.every(x=>Number.isInteger(x)&&x>=0&&x<=5))errors.push('Calendario de siete días inválido');
  if(!Array.isArray(p.equipment)||!p.equipment.every(x=>typeof x==='string'&&x.length<80))errors.push('Equipamiento inválido');
  if(!['misiones','rutinas'].includes(p.modality))errors.push('Modalidad inválida');
  if(!Number.isInteger(p.dailyLimit)||p.dailyLimit<1||p.dailyLimit>12)errors.push('Límite diario inválido');
  if(!['baja','moderada'].includes(p.intensity))errors.push('Intensidad inválida');
  if(typeof p.custom_goal!=='string'||p.custom_goal.length>500)errors.push('Objetivo escrito inválido');
  if(!Number.isInteger(p.minutes)||p.minutes<5||p.minutes>240)errors.push('Minutos inválidos');
  if(!Number.isInteger(p.restSeconds)||p.restSeconds<0||p.restSeconds>1800)errors.push('Descanso entre bloques inválido');
  if(!['series','rondas'].includes(p.organization))errors.push('Organización inválida');
  if(!Array.isArray(p.restrictions))errors.push('Restricciones inválidas');
  return {ok:!errors.length,errors};
 }
 function defaultProfile(){return {active:false,experience:'principiante',goal:'',custom_goal:'',weekPlan:[5,5,5,5,5,5,5],equipment:[],modality:'misiones',dailyLimit:3,intensity:'baja',restSeconds:90,organization:'series',minutes:20,restrictions:[]};}
 function strictList(x,name){if(!Array.isArray(x))throw Error(name+' no es una lista');return x;}
 function catalog(provider){
  if(!provider||typeof provider!=='object')throw Error('Debe inyectarse un catálogo externo');
  const cats=strictList(provider.categories?.(),'categories'),families=strictList(provider.families?.(),'families'),types=strictList(provider.types?.(),'types'),variants=strictList(provider.variants?.(),'variants');
  const assertUnique=(xs,label)=>{const ids=new Set();for(const v of xs){if(!v||typeof v.id!=='string'||!/^[A-Za-z0-9][A-Za-z0-9_.:-]{0,127}$/.test(v.id)||ids.has(v.id))throw Error('ID duplicado o vacío: '+label);ids.add(v.id);}return ids;};
  const categoryIds=assertUnique(cats,'categoria'),familyIds=assertUnique(families,'familia'),typeIds=assertUnique(types,'tipo'),variantIds=assertUnique(variants,'variante');
  if(cats.length!==5||!CATS.every(x=>categoryIds.has(x)))throw Error('El catálogo debe declarar las cinco categorías exactas');
  const fm=new Map(families.map(x=>[x.id,x])),tm=new Map(types.map(x=>[x.id,x])),vm=new Map();
  for(const f of families)if(!categoryIds.has(cat(f.category)))throw Error('Familia con categoría inexistente: '+f.id);
  for(const t of types)if(!familyIds.has(t.family))throw Error('Tipo con familia inexistente: '+t.id);
  for(const v of variants){
   if(!typeIds.has(v.type))throw Error('Variante con tipo inexistente: '+v.id);
   const t=tm.get(v.type),f=fm.get(t.family),c=cat(f.category);
   if(v.family!==undefined&&v.family!==f.id||v.category!==undefined&&cat(v.category)!==c)throw Error('Relación inconsistente: '+v.id);
   if(!Number.isInteger(v.level)||v.level<0||v.level>5)throw Error('Nivel técnico inválido: '+v.id);
   if(!Array.isArray(v.equipment)||v.equipment.some(x=>typeof x!=='string'))throw Error('Equipamiento inválido: '+v.id);
   if(typeof v.supervisionRequired!=='boolean'||typeof v.automaticEligible!=='boolean')throw Error('Elegibilidad sin validar: '+v.id);
   if(!['baja','moderada','alta'].includes(v.intensity))throw Error('Intensidad inválida: '+v.id);
   if(typeof v.modality!=='string'||!v.modality)throw Error('Modalidad sin validar: '+v.id);
   if(!Array.isArray(v.restrictions)||!Array.isArray(v.tags))throw Error('Etiquetas/restricciones inválidas: '+v.id);
   vm.set(v.id,Object.freeze({...v,category:c,family:f.id,type:t.id}));
  }
  const frozen=Object.freeze([...vm.values()].sort((a,b)=>a.id.localeCompare(b.id)));
  const revision=String(provider.revision||'sin-version');
  const api={revision,all:()=>frozen.slice(),byId:id=>vm.get(id)||null,
   categories:()=>CATS.slice(),families:c=>families.filter(f=>cat(f.category)===cat(c)).map(f=>({...f})),
   types:family=>types.filter(t=>t.family===family).map(t=>({...t})),
   variants:type=>frozen.filter(v=>v.type===type),categoryOf:id=>vm.get(id)?.category||null,
   familyOf:id=>vm.get(id)?.family||null,find:query=>frozen.filter(v=>normalized(v.label||v.id).includes(normalized(query))),
   filter:fn=>frozen.filter(fn),check:v=>vm.has(v.id)};
  return Object.freeze(api);
 }
 function compatible(v,p){const reasons=[];
  if(v.level===0||v.level>=4||v.supervisionRequired||!v.automaticEligible)reasons.push('No apta para asignación automática');
  if(p.experience==='principiante'&&v.level>2)reasons.push('Complejidad técnica fuera del perfil');
  if(p.experience==='avanzado'&&v.level===1)reasons.push('Se priorizan niveles 2 y 3');
  if(v.intensity==='alta'||p.intensity==='baja'&&v.intensity!=='baja')reasons.push('Intensidad no compatible');
  if(v.equipment.some(x=>!p.equipment.includes(x)))reasons.push('Equipo no declarado');
  if(v.restrictions.some(x=>p.restrictions.includes(x)))reasons.push('Restricción del perfil');
  if(p.blockedVariantIds?.includes(v.id))reasons.push('Variante excluida por el perfil');
  return {ok:!reasons.length,reasons};
 }
 function safeItems(vs,p){return vs.filter(v=>compatible(v,p).ok);}
 function stateEmpty(){return {version:1,pins:{},outcomes:{},categoryEfforts:[],globalEfforts:[],volumes:[],phases:{},initialExperienceDate:null,revisionEvents:[]};}
 function dataState(s){return s&&s.version===1?s:stateEmpty();}
 function recordOutcome(s,o){const data=dataState(s),dt=o.date,id=o.variantId;
  if(!date(dt)||typeof id!=='string'||!id||!STATES.includes(o.status)||!CATS.includes(cat(o.category)))throw Error('Resultado inválido');
  const key=dt+'|'+id,prev=data.outcomes[key];
  if(prev&&prev.status===o.status&&String(prev.note||'')===String(o.note||'').slice(0,400))return {changed:false,state:data};
  if(prev&&prev.status!=='PENDING'&&prev.status!==o.status)throw Error('Resultado ya evaluado: no se cambia sin corrección explícita');
  data.outcomes[key]={date:dt,variantId:id,category:cat(o.category),status:o.status,note:String(o.note||'').slice(0,400)};
  return {changed:true,state:data};
 }
 function correctOutcome(s,o,reason){const data=dataState(s);const key=o?.date+'|'+o?.variantId;const old=data.outcomes[key];if(!old||!STATES.includes(o.status)||!String(reason||'').trim())throw Error('Corrección explícita y motivo requeridos');data.outcomes[key]={...old,status:o.status,note:String(o.note||old.note||'').slice(0,400)};data.revisionEvents.push({date:o.date,variantId:o.variantId,from:old.status,to:o.status,action:'correccion_explicita',reason:String(reason).slice(0,250)});return data;}
 function completionSummary(s,from,to){const rows=Object.values(dataState(s).outcomes).filter(x=>(!from||x.date>=from)&&(!to||x.date<=to));
  const counts={planned:rows.length,completed:rows.filter(x=>x.status==='COMPLETED').length,skipped:rows.filter(x=>x.status==='SKIPPED').length,pending:rows.filter(x=>x.status==='PENDING').length};
  const assessed=counts.completed+counts.skipped;return {...counts,assessed,percent:assessed?Math.round(100*counts.completed/assessed):null,label:assessed?'Participación evaluada':'Sin datos suficientes'};
 }
 function globalEffort(s,{date:dt,value}){const data=dataState(s);if(!date(dt)||!Number.isInteger(value)||value<1||value>10)throw Error('Valoración global inválida');const j=data.globalEfforts.findIndex(x=>x.date===dt);const g={date:dt,value};if(j>=0)data.globalEfforts[j]=g;else data.globalEfforts.push(g);return data;}
 function effort(s,{date:dt,category:code,value,completed,global=true}){
  const data=dataState(s),c=cat(code);
  if(!date(dt)||!c||!Number.isInteger(value)||value<1||value>10||typeof completed!=='boolean')throw Error('Valoración inválida');
  const item={date:dt,category:c,value,completed};
  const idx=data.categoryEfforts.findIndex(x=>x.date===dt&&x.category===c);
  if(idx>=0)data.categoryEfforts[idx]=item;else data.categoryEfforts.push(item);
  if(global===true)globalEffort(data,{date:dt,value});
  return data;
 }
 function lastEffort(s,category,today){const start=addDays(today,-21);
  return dataState(s).categoryEfforts.filter(x=>x.category===category&&x.date<today&&x.date>=start&&x.completed).sort((a,b)=>b.date.localeCompare(a.date))[0]||null;
 }
 function weekly(s,category,today){const rows=dataState(s).categoryEfforts.filter(x=>x.category===category&&x.completed&&x.date<today).sort((a,b)=>a.date.localeCompare(b.date));
  if(!rows.length)return {status:'sin_datos',reason:'Aún no existe una valoración completada en esta categoría.'};
  const first=rows[0].date;if(distance(today,first)<7)return {status:'espera',reason:'Todavía no se cumple la primera revisión de siete días.'};
  const recent=rows.filter(x=>x.date>=addDays(today,-7));
  if(recent.some(x=>x.value>=8))return {status:'recuperacion',reason:'Se informó esfuerzo alto; prioridad de recuperación.'};
  const lowDays=new Set(recent.filter(x=>x.value>=1&&x.value<=3).map(x=>x.date));
  if(lowDays.size<2)return {status:'insuficiente',reason:'Se necesitan dos días distintos completados y valorados con esfuerzo bajo.'};
  return {status:'variedad',reason:'Dos sesiones comparables y valoradas permiten revisar una variante equivalente, sin aumentar cantidad ni complejidad.'};
 }
 function decision(s,category,today){const prev=lastEffort(s,category,today),w=weekly(s,category,today);
  if(!prev)return {action:'mantener',max:Infinity,why:'Sin valoración anterior de esta categoría.',weekly:w};
  if(prev.value>=9)return {action:'recuperacion',max:1,why:'Esfuerzo 9–10/10: máximo una actividad sencilla y recuperación prioritaria.',weekly:w};
  if(prev.value===8)return {action:'suavizar',max:2,why:'Esfuerzo 8/10: máximo dos actividades y alternativas sencillas.',weekly:w};
  if(prev.value>=4)return {action:'mantener',max:Infinity,why:'Esfuerzo 4–7/10: mantener propuesta.',weekly:w};
  return {action:w.status==='variedad'?'equivalente':'mantener',max:Infinity,why:w.status==='variedad'?'Se permite revisar variedad del mismo tipo y nivel técnico.':'Esfuerzo bajo registrado; mantener hasta disponer de evidencia suficiente.',weekly:w};
 }
 function phaseMix(experience,phase){if(experience==='principiante')return 0;const table=experience==='avanzado'?[.33,.55,.75]:[.25,.40,.60];return table[Math.max(0,Math.min(2,phase||0))];}
 function phaseReview(s,category,today,experience){const data=dataState(s),base=data.initialExperienceDate||today,elapsed=distance(today,base),milestone=[21,14,7].find(n=>elapsed>=n)||0;
  const previous=data.phases[category]||{phase:0,lastChange:null};const w=weekly(data,category,today);
  if(!milestone)return {milestone:0,phase:previous.phase,action:'esperar',why:'Primera revisión técnica en día 7.'};
  if(w.status==='recuperacion')return {milestone,phase:previous.phase,action:'recuperacion',why:w.reason};
  if(experience==='principiante'||previous.phase>=2||w.status!=='variedad'||previous.lastChange&&distance(today,previous.lastChange)<7)return {milestone,phase:previous.phase,action:'mantener',why:'No se cumplen todas las condiciones para avanzar la fase.'};
  // The engine makes a *review proposal*: advancement requires explicit approval.
  return {milestone,phase:previous.phase,action:'revisar_fase',suggestedPhase:previous.phase+1,why:'Revisión opcional de fase por categoría. No habilita niveles 4–5.'};
 }
 function approvePhase(s,category,today,experience){const data=dataState(s);if(!CATS.includes(category))throw Error('Categoría inválida');
  const review=phaseReview(data,category,today,experience);if(review.action!=='revisar_fase')throw Error('Falta evidencia para avanzar la fase');
  const old=data.phases[category]||{phase:0,lastChange:null};if(old.phase>=2||old.lastChange&&distance(today,old.lastChange)<7)throw Error('Intervalo o fase no permitidos');
  data.phases[category]={phase:old.phase+1,lastChange:today};data.revisionEvents.push({date:today,category,action:'fase_aprobada',from:old.phase,to:old.phase+1});return data;
 }
 function globalReview(s,today){const data=dataState(s),rows=data.globalEfforts.filter(x=>x.date<=today&&x.date>=addDays(today,-20)).sort((a,b)=>a.date.localeCompare(b.date));
  const first=[...data.globalEfforts].sort((a,b)=>a.date.localeCompare(b.date))[0]?.date;
  if(!first)return {cycleDay:null,lastMilestone:null,nextMilestone:7,count:0,average:null,reason:'Sin valoraciones suficientes para interpretar tendencia.'};
  const elapsed=Math.max(0,distance(today,first));const cycleDay=elapsed%21+1,passed=[21,14,7].filter(n=>cycleDay>=n).at(-1)||0,next=[7,14,21].find(n=>n>passed)||21;
  const avg=rows.length?Math.round(rows.reduce((a,b)=>a+b.value,0)/rows.length*10)/10:null;
  return {cycleDay,lastMilestone:passed||null,nextMilestone:next,count:rows.length,average:avg,reason:rows.length<2?'Observaciones insuficientes para una tendencia.':'Tendencia descriptiva; no modifica decisiones de categorías.'};
 }
 function volume(s,r,catalogRef){const data=dataState(s);
  if(!date(r.date)||!CATS.includes(cat(r.category))||!['REPETITIONS','SECONDS'].includes(r.unit)||!['COMPLETED','SKIPPED'].includes(r.status)||!Number.isInteger(r.sets)||r.sets<0||!Number.isFinite(r.perSet)||r.perSet<0||
   r.unit==='REPETITIONS'&&!Number.isInteger(r.perSet)||r.effort!=null&&(!Number.isInteger(r.effort)||r.effort<1||r.effort>10))throw Error('Registro de volumen o unidad inválida');
  const v=catalogRef?.byId(r.variantId);if(!v||v.category!==cat(r.category)||v.type!==r.typeId||v.level!==r.level)throw Error('Variante no certificada por catálogo externo');
  const interrupted=r.interrupted===true;
  if(r.status==='COMPLETED'&&!interrupted&&(!r.sets||!r.perSet))throw Error('Faltan cantidades realizadas');
  const key=[r.date,r.variantId,r.unit].join('|');
  const rec={date:r.date,category:v.category,variantId:v.id,typeId:v.type,level:v.level,sets:r.sets,perSet:r.perSet,unit:r.unit,effort:r.effort??null,status:r.status,interrupted};
  const i=data.volumes.findIndex(x=>[x.date,x.variantId,x.unit].join('|')===key);if(i>=0)data.volumes[i]=rec;else data.volumes.push(rec);
  return data;
 }
 function doseHistory(s,id,unit){return dataState(s).volumes.filter(v=>v.variantId===id&&v.unit===unit&&v.status==='COMPLETED'&&!v.interrupted&&v.sets>0&&v.perSet>0).sort((a,b)=>a.date.localeCompare(b.date));}
 function rank(vs,intent,seed,state,category,p){const h=dataState(state);
  const scored=vs.map(v=>{const texts=[...v.tags,v.modality,v.label||''].map(normalized).join(' ');
   const affinity=intent.recognized.filter(x=>texts.includes(normalized(x).replace(/_/g,' '))||texts.includes(x)).length;
   const recent=Object.values(h.outcomes).filter(o=>o.category===category&&o.variantId===v.id&&o.status==='COMPLETED').length;
   return {v,affinity,recent,order:fingerprint(seed+'|'+v.id)};
  });
  return scored.sort((a,b)=>b.affinity-a.affinity||a.recent-b.recent||a.order.localeCompare(b.order)||a.v.id.localeCompare(b.v.id)).map(x=>x.v);
 }
 function generate({catalog:ref,profile:p,state:s,today,pin=true}){
  const errors=validProfile(p);if(!errors.ok)return {status:'perfil_invalido',items:[],reason:errors.errors.join('; ')};
  if(!ref||typeof ref.all!=='function'||!date(today))return {status:'entrada_invalida',items:[],reason:'Fecha o catálogo externo no disponible.'};
  const data=dataState(s),idx=p.weekPlan[weekday(today)],category=idx===5?'descanso':CATS[idx];
  const settings={experience:p.experience,weekPlan:p.weekPlan,equipment:p.equipment.slice().sort(),modality:p.modality,dailyLimit:p.dailyLimit,intensity:p.intensity,restSeconds:p.restSeconds,organization:p.organization,minutes:p.minutes,goal:p.goal,goalTextHash:fingerprint(p.custom_goal),restrictions:p.restrictions.slice().sort(),blockedVariantIds:p.blockedVariantIds||[],catalogRevision:ref.revision};
  const signature=fingerprint(settings),old=data.pins[today];
  if(category==='descanso')return {status:'descanso',category,items:[],reason:'Descanso programado: no se generan actividades.',signature};
  const filtered=safeItems(ref.all().filter(v=>v.category===category),p);
  // Once pinned, retain the same order despite same-day feedback changes.
  if(pin&&old&&old.signature===signature&&old.category===category&&old.revision===ref.revision&&old.ids.every(id=>filtered.some(x=>x.id===id))){return {status:'fijada',category,items:old.ids.map(id=>ref.byId(id)),reason:'Propuesta fijada para esta fecha local.',signature};}
  const d=decision(data,category,today),safe=d.action==='recuperacion'||d.action==='suavizar'?filtered.filter(v=>v.level<=2):filtered;
  if(!safe.length)return {status:'sin_compatibles',category,items:[],reason:'No hay actividades compatibles verificadas con tu perfil y equipo.',signature};
  const intent=parseGoal(p.custom_goal),mainGoal=parseGoal(p.goal);intent.recognized=[...new Set([...mainGoal.recognized,...intent.recognized])].filter(x=>!intent.excluded.includes(x));const phase=data.phases[category]?.phase||0,ratio=phaseMix(p.experience,phase);
  let ordered=rank(safe,intent,today+'|'+category,data,category,p);const result=[],usedTypes=new Set(),usedFamilies=new Set();
  const previous=Object.values(data.outcomes).filter(o=>o.category===category&&o.date<today&&o.status==='COMPLETED')
   .sort((a,b)=>b.date.localeCompare(a.date)||a.variantId.localeCompare(b.variantId));
  const recentDate=previous[0]?.date;
  const previousIds=[...new Set(previous.filter(o=>o.date===recentDate).map(o=>o.variantId))];
  const preferred=[];
  if(d.action==='mantener')for(const id of previousIds){const v=safe.find(x=>x.id===id);if(v)preferred.push(v);}
  if(d.action==='equivalente')for(const id of previousIds){const old=ref.byId(id);if(!old)continue;
   const equivalent=safe.find(v=>v.id!==id&&v.type===old.type&&v.level===old.level);
   const choice=equivalent||safe.find(v=>v.id===id);if(choice&&!preferred.some(x=>x.id===choice.id))preferred.push(choice);
  }
  if(preferred.length)ordered=[...preferred,...ordered.filter(v=>!preferred.some(x=>x.id===v.id))];
  const max=Math.min(p.dailyLimit,d.max,ordered.length);
  for(let j=0;j<max;j++){
   const desired3=p.experience!=='principiante'&&result.filter(v=>v.level===3).length<Math.floor((j+1)*ratio+1e-8);
   const options=ordered.filter(v=>!result.some(r=>r.id===v.id));if(!options.length)break;
   options.sort((a,b)=>{
    const score=v=>(v.level===3&&desired3?10:0)+(v.level!==3&&!desired3?5:0)+(usedFamilies.has(v.family)?0:3)+(usedTypes.has(v.type)?0:2);
    return (preferred.includes(b)?1:0)-(preferred.includes(a)?1:0)||score(b)-score(a)||ordered.indexOf(a)-ordered.indexOf(b);
   });
   const next=options[0];result.push(next);usedTypes.add(next.type);usedFamilies.add(next.family);
  }
  if(pin)data.pins[today]={signature,revision:ref.revision,category,ids:result.map(v=>v.id)};
  return {status:result.length?'generada':'sin_compatibles',category,items:result,reason:d.why,signature,goalRecognized:intent.recognized,goalExcluded:intent.excluded,adaptation:d};
 }
 return Object.freeze({version:'38.72',categories:CATS,labels:LABELS,aliases:ALIASES,states:STATES,normalized,category:cat,addDays,distance,weekday,parseGoal,validProfile,defaultProfile,catalog,compatible,stateEmpty,dataState,recordOutcome,correctOutcome,completionSummary,effort,globalEffort,lastEffort,weekly,decision,phaseMix,phaseReview,approvePhase,globalReview,volume,doseHistory,generate,fingerprint});
});
