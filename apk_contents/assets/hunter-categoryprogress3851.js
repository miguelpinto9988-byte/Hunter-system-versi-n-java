/* Hunter System 38.50: progresión independiente por cinco categorías.
   Fuente de datos: cuestionario RPE (1-10) ya presente en las misiones.
   No altera historial, peso de mancuernas, nivel global ni ejercicios guardados.
   Las decisiones son deterministas, conservadoras y se recomputan desde logs.
*/
(function(root){
 'use strict';
 if(root.HunterCategoryProgress51)return;
 const E=root.Engine,G=root.HunterGroups49;
 if(!E||!G)throw Error('CategoryProgress51: faltan Engine / HunterGroups49');
 const CATS=Object.freeze(['upper','lower','core','cardio','mobility']);
 const LABELS=Object.freeze({upper:'Tren superior',lower:'Tren inferior',core:'Zona media',cardio:'Cardio',mobility:'Movilidad'});
 const DAY=/^\d{4}-\d{2}-\d{2}$/;
 const limit=(v,lo,hi)=>Math.min(hi,Math.max(lo,v));
 const rpe=v=>Number.isInteger(Number(v))&&Number(v)>=1&&Number(v)<=10?Number(v):0;
 const isoDays=(a,b)=>Math.round((Date.parse(a+'T12:00:00Z')-Date.parse(b+'T12:00:00Z'))/86400000);
 const meta=id=>G.classify(typeof id==='object'?id?.id:id);
 const riskyProfile=p=>Boolean(p?.limits||p?.health?.special||p?.health?.acute||p?.health?.redFlags||
  (p?.health?.pain&&p.health.pain!=='none')||(p?.injuries||[]).some(i=>i&&i.stage!=='past'));
 function categoryOfLog(l){
  if(!l||!Array.isArray(l.items)||!l.items.length)return null;
  if(l.kind==='cardio')return 'cardio';
  if(l.kind==='recovery')return 'mobility';
  const tally={upper:0,lower:0,core:0,cardio:0,mobility:0};
  for(const x of l.items){const group=meta(x?.id)?.group;if(Object.prototype.hasOwnProperty.call(tally,group))tally[group]++;}
  const winner=Object.entries(tally).sort((a,b)=>b[1]-a[1]);
  // No deducir una categoría de una sesión mixta o sin metadatos suficientes.
  return winner[0][1]>0&&winner[0][1]>winner[1][1]?winner[0][0]:null;
 }
 function categoryOfItems(items,kind,focus){
  if(kind==='cardio')return 'cardio';
  if(kind==='recovery')return 'mobility';
  if(['upper','lower','core'].includes(focus))return focus;
  return categoryOfLog({kind,items});
 }
 function categoryHistory(logs,date,category){
  if(!CATS.includes(category)||!DAY.test(date))return [];
  return Object.entries(logs||{}).filter(([d,l])=>DAY.test(d)&&d<date&&isoDays(date,d)<=90&&
   l&&categoryOfLog(l)===category&&(l.complete===true||l.pain===true))
   .sort(([a],[b])=>a.localeCompare(b)).slice(-12)
   .map(([date,log])=>({date,effort:rpe(log.rpe),pain:log.pain===true,
    fatigue:log.fatigue===true,complete:log.complete===true,
    manual:log.manual===true}));
 }
 function resolve(logs,date,category){
  const entries=categoryHistory(logs,date,category);let level=0;let counted=0;
  for(const entry of entries){
   // Informe de dolor/fatiga siempre prevalece sobre la respuesta numérica.
   if(entry.pain||entry.fatigue){level=Math.max(-2,level-2);counted++;continue;}
   if(!entry.complete||!entry.effort)continue;
   if(entry.effort<=4)level=Math.min(4,level+1);
   else if(entry.effort>=8)level=Math.max(-2,level-1);
   counted++;
  }
  const latest=entries.at(-1)||null;
  const last=latest?.pain?'dolor':latest?.fatigue?'fatiga':!latest?.effort?'sin_respuesta':
    latest.effort<=4?'facil':latest.effort>=8?'exigente':'adecuado';
  return {category,label:LABELS[category]||'',level,history:counted,
   lastScore:latest?.effort||null,lastDate:latest?.date||null,last,
   reason:!counted?'Sin cuestionarios válidos de esta categoría':
     level>0?'Progresión gradual según respuestas anteriores de esta categoría':
     level<0?'Dosis moderada por esfuerzo alto o molestias anteriores':
     'Mantener dosis de referencia'};
 }
 function globalSymptoms(logs,date){
  return Object.entries(logs||{}).some(([d,l])=>DAY.test(d)&&d<date&&isoDays(date,d)<=7&&
   (l?.pain===true||l?.fatigue===true))||
   Boolean(root.HunterConstraints3811?.symptomTrend?.(logs,date)?.recentSymptom);
 }
 function feedback(raw,logs,date){
  return {...raw,strain:globalSymptoms(logs,date),canProgress:false};
 }
 function allowedItem(e,item,category){
  if(!e||!item||!meta(e)||meta(e).group!==category)return false;
  const m=meta(e);
  if(m.power||m.impact||m.role==='activation'||m.intent50==='preparation'||
    e.highSkill46||e.doseCap46?.highSkill||e.impact==='Con saltos')return false;
  return category==='upper'||category==='lower'?m.primary===true:
   category==='core'?m.primary===true:
   category==='cardio'?m.role==='conditioning':category==='mobility';
 }
 function targetCap(e,item,p){
  const seconds=e.unit==='sec';let max=seconds?(e.category==='Movilidad'?60:90):16;
  if(e.doseCap46&&Number.isFinite(Number(e.doseCap46.target))){
   const scale=limit(Number(p?.trainingScale)||1,.25,1);
   max=Math.min(max,Math.max(1,Math.floor(e.doseCap46.target*scale)));
  }
  return Math.max(1,max);
 }
 function adjustItems(items,ctx){
  const {profile:p,logs={},date,category,status,kind}=ctx||{};
  const records=resolve(logs,date,category);
  if(!Array.isArray(items))return {items:[],progress:records};
  const out=items.map(x=>({...x}));
  const blocked=!!status?.reduced||riskyProfile(p)||globalSymptoms(logs,date)||Number(p?.trainingScale)>0&&Number(p.trainingScale)<1;
  const slots=out.map((x,i)=>({x,i,e:E.byId(x.id)})).filter(({x,e})=>allowedItem(e,x,category));
  const maximum=Number(p?.age)<18?1:4;
  // El nivel del usuario jamás se aumenta de basic a advanced por esta función.
  const requested=blocked?Math.min(0,records.level):limit(records.level,-2,maximum);
  let change='mantener',affected=0;
  if(slots.length&&requested>0){
   if(requested<=2||Number(p?.age)<18||kind!=='strength'){
    const quantity=requested===1?1:Math.min(2,slots.length);
    for(const {x,e} of slots.slice(0,quantity)){
     const increment=e.unit==='sec'?(category==='mobility'?2:5):1;
     const ceiling=targetCap(e,x,p),next=Math.min(ceiling,Number(x.target)+increment);
     if(next>Number(x.target)){x.target=next;affected++;}
    }
    if(affected)change='repeticiones_o_tiempo';
   }else{
    // Tras varias respuestas fáciles consecutivas: una sola serie adicional,
    // únicamente en fuerza de adultos con tiempo suficiente y sin restricciones.
    const first=slots.find(({x})=>Number(x.sets)>=1&&Number(x.sets)<3);
    if(first&&Number(p?.minutes)>=30){first.x.sets++;affected=1;change='una_serie';}
    else {
     const {x,e}=slots[0],next=Math.min(targetCap(e,x,p),Number(x.target)+(e.unit==='sec'?5:1));
     if(next>Number(x.target)){x.target=next;affected=1;change='repeticiones_o_tiempo';}
    }
   }
  }else if(slots.length&&requested<0){
   // RPE 8-10: bajar poco en la categoría que se calificó exigente,
   // sin modificar el número global de ejercicios ni otras categorías.
   for(const {x,e} of slots.slice(0,requested===-2?2:1)){
    const delta=e.unit==='sec'?(category==='mobility'?2:5):1;
    const floor=e.unit==='sec'?Math.min(Number(x.target),category==='mobility'?8:15):Math.min(Number(x.target),4);
    const next=Math.max(floor,Number(x.target)-delta);
    if(next<Number(x.target)){x.target=next;affected++;}
   }
   if(affected)change='reduccion_suave';
  }
  for(const x of out){x.progression51={category,level:requested,action:change,
    lastScore:records.lastScore,lastDate:records.lastDate};}
  return {items:out,progress:{...records,level:requested,action:change,affected,
   blockedIncrease:blocked}};
 }
 root.HunterCategoryProgress51=Object.freeze({version:'38.50',categories:CATS,labels:LABELS,
  categoryOfLog,categoryOfItems,categoryHistory,resolve,globalSymptoms,feedback,adjustItems});
})(window);
