/* Hunter/Asterion 38.73 — single local, deterministic category-session engine.
 * Does NOT contain exercises; requires an audited catalog provider.
 * Technical progression is a reversible offer, never a mandatory prescription.
 */
(function(factory){
 const R=typeof module==='object'&&module.exports?require('./asterion-rules3872.js'):
  (typeof window!=='undefined'?window.AsterionRules3872:null);
 const api=factory(R);
 if(typeof module==='object'&&module.exports)module.exports=api;
 if(typeof window!=='undefined')window.AsterionUnified3873=api;
})(function(R){'use strict';
 if(!R)throw Error('Falta motor Asterion 38.72');
 const SCALE=Object.freeze({1:5,2:3,3:1,4:0,5:0,6:0,7:0,8:-1,9:-2,10:-3});
 const ISO=/^\d{4}-\d{2}-\d{2}$/;
 const safeDate=d=>typeof d==='string'&&ISO.test(d)&&!Number.isNaN(Date.parse(d+'T12:00:00Z'));
 const validRating=x=>Number.isInteger(x)&&x>=1&&x<=10;
 const clean=x=>JSON.parse(JSON.stringify(x));
 function reference(rating){return validRating(rating)?SCALE[rating]:null;}
 function completedCategories(log,categoryForId){
  const list=(log?.items||[]).filter(item=>item?.actual?.some?.(set=>set?.done===true&&Number(set.value)>0));
  return [...new Set(list.map(item=>categoryForId(item.id)).filter(c=>R.categories.includes(c)))].sort();
 }
 // Exactly one RPE for a completed session. Mixed sessions receive global RPE only.
 function recordSession(data,{date,log,categoryForId}){
  if(!safeDate(date)||!log?.complete||!validRating(Number(log.rpe))||typeof categoryForId!=='function')return {recorded:false,why:'No hay sesión completada y valorada.'};
  const cats=completedCategories(log,categoryForId);
  const value=Number(log.rpe);
  if(cats.length===1)R.effort(data,{date,category:cats[0],value,completed:true,global:true});
  else R.globalEffort(data,{date,value});
  if(!data.categoryFeedback3873)data.categoryFeedback3873=[];
  if(cats.length===1){const q=log.afterFeedback3870||{};
   const entry={date,category:cats[0],value,hasPain:q.symptom==='pain'||q.symptom==='discomfort'||Boolean(log.pain),
    fatigue:q.readiness==='tired'||q.symptom==='tired'||Boolean(log.fatigue),
    recovered:q.readiness==='recovered'&&q.symptom==='none',completed:true};
   const k=data.categoryFeedback3873.findIndex(x=>x.date===date&&x.category===cats[0]);
   if(k>=0)data.categoryFeedback3873[k]=entry;else data.categoryFeedback3873.push(entry);
  }
  return {recorded:true,category:cats.length===1?cats[0]:null,globalOnly:cats.length!==1,why:cats.length===1?'Valoración general guardada para una categoría.':'Valoración global; sesión mixta o sin registros cuantificados.'};
 }
 function recentCategory(data,category,today,lookback=7){
  return (data?.categoryFeedback3873||[]).filter(x=>x.category===category&&x.date<today&&x.date>=R.addDays(today,-lookback)).sort((a,b)=>a.date.localeCompare(b.date));
 }
 // Numeric reference only. Never commit a higher load solely on RPE.
 function volumeReference({rating,unit='REPETITIONS',base,min=1,max=null,completed=false,interrupted=false,recovered=false,pain=false,approved=false}={}){
  const delta=reference(rating);
  if(delta===null)return {status:'sin_datos',delta:null,next:null,why:'Sin valoración válida.'};
  if(unit!=='REPETITIONS')return {status:'unidad_separada',delta:null,next:null,why:'Los segundos requieren un protocolo distinto.'};
  if(!Number.isInteger(base)||base<1||!Number.isInteger(min)||min<1||max!==null&&(!Number.isInteger(max)||max<min))return {status:'sin_limites',delta,next:null,why:'Faltan datos válidos de referencia y límites.'};
  const floor=Math.min(min,base);
  if(delta>0&&max===null)return {status:'sin_maximo',delta,next:null,why:'No existe un máximo certificado para esta variante.'};
  const next=Math.max(floor,Math.min(max??Number.MAX_SAFE_INTEGER,base+delta));
  if(delta>0&&(!completed||interrupted||!recovered||pain))return {status:'mantener',delta,next:base,why:'Una valoración fácil sin recuperación y registros completos no autoriza un aumento.'};
  if(delta>0)return {status:approved?'aceptada_para_revision':'sugerencia',delta,next,why:'Referencia numérica revisable; no cambia automáticamente el plan ni es obligatoria.'};
  return {status:delta<0?'reduccion_orientativa':'mantener',delta,next,why:delta<0?'Referencia reducida dentro de los límites de la variante.':'No corresponde variar el volumen.'};
 }
 function cycleDay(data,date){const start=data?.initialExperienceDate;
  if(!safeDate(start)||!safeDate(date)||R.distance(date,start)<0)return null;
  return R.distance(date,start)+1;
 }
 function milestone(data,category,today){
  if(!R.categories.includes(category)||!safeDate(today))throw Error('Categoría o fecha inválida');
  const d=cycleDay(data,today),checkpoint=d!==null&&[7,14,21].includes(d)?d:null;
  const last=recentCategory(data,category,today,7),low=[...new Set(last.filter(x=>x.completed&&x.value<=3&&x.recovered&&!x.hasPain&&!x.fatigue).map(x=>x.date))];
  const blocked=last.some(x=>x.hasPain||x.fatigue||x.value>=8);
  const eligible=low.length>=2&&!blocked;
  const requested=checkpoint===21?2:checkpoint===7||checkpoint===14?1:0;
  return {day:d,checkpoint,requested,eligible:eligible&&Boolean(checkpoint),lowSessions:low.length,blocked,
   action:!checkpoint?'esperar':blocked?'recuperacion':!eligible?'mantener':'evaluar_propuesta',
   why:!checkpoint?'Las revisiones ocurren en los días 7, 14 y 21.':blocked?'Existe una señal reciente de fatiga, molestias o esfuerzo alto.':
   !eligible?'No hay dos sesiones fáciles y recuperadas en días diferentes dentro de la ventana.':
   `Revisión día ${checkpoint}: hay información para evaluar hasta ${requested} variante(s) superiores, opcionales.`};
 }
 // No inferred biomechanics: explicit progression metadata must identify predecessor.
 function higherCandidates({catalog,profile,data,category,today,currentIds=[]}){
  const m=milestone(data,category,today);
  if(!m.eligible||!catalog||!Array.isArray(currentIds)||!currentIds.length)return {...m,proposals:[],why:m.eligible?'Falta catálogo o variante anterior certificada.':m.why};
  const all=catalog.all();const proposals=[];
  for(const id of currentIds){
   const prior=catalog.byId(id);
   if(!prior||prior.category!==category||prior.level>=3)continue;
   const next=all.filter(v=>v.type===prior.type&&v.level===prior.level+1&&v.level<=3&&
    Array.isArray(v.progressionFromIds)&&v.progressionFromIds.includes(prior.id)&&R.compatible(v,profile).ok)
     .sort((a,b)=>a.id.localeCompare(b.id))[0];
   if(next&&!proposals.some(p=>p.id===next.id)){
    const safeIntro=next.introDose&&Number.isInteger(next.introDose.sets)&&next.introDose.sets>=1&&next.introDose.sets<=2&&
     Number.isInteger(next.introDose.perSet)&&next.introDose.perSet>=1&&next.introDose.unit==='REPETITIONS';
    proposals.push({id:next.id,from:prior.id,level:next.level,category,kind:'propuesta_sin_asignar',
     dose:safeIntro?clean(next.introDose):null,
     why:safeIntro?'Variante superior explícitamente vinculada; dosis de introducción declarada por catálogo y sujeta a aceptación.':
      'Variante superior vinculada, pero sin dosis de introducción validada. No se asignan repeticiones.'});
   }
   if(proposals.length>=m.requested)break;
  }
  return {...m,proposals,why:proposals.length?m.why:'No hay variantes superiores compatibles y expresamente relacionadas. Se mantienen las actuales.'};
 }
 // Generator changeover only with audited metadata AND an explicit, compatible provider.
 function generate(options){const basic=R.generate(options);if(!options?.catalog||!options?.profile||!options?.today||!options?.state)return basic;
  if(basic.category==='descanso')return {...basic,review:null,source:'AsterionUnified3873'};
  const review=milestone(options.state,basic.category,options.today);
  const proposals=basic.items?.length?higherCandidates({catalog:options.catalog,profile:options.profile,data:options.state,
   category:basic.category,today:options.today,currentIds:basic.items.map(v=>v.id)}):{...review,proposals:[]};
  return {...basic,review:proposals,source:'AsterionUnified3873'};
 }
 return Object.freeze({version:'38.73',SCALE,reference,completedCategories,recordSession,recentCategory,volumeReference,cycleDay,milestone,higherCandidates,generate});
});
