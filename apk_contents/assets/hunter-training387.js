/* Hunter System V38.7 - motor de equilibrio semanal (sin ventanas ni paneles nuevos).
 * Solo puntúa ejercicios que pasaron los filtros de compatibilidad existentes.
 * Los patrones explícitos se usan para diversidad, NO para declarar seguridad clínica.
 */
(function(root) {
 'use strict';
 if (root.HunterTraining387) return;
 const knownPatterns=Object.freeze({
  chair:'rodilla',squat:'rodilla',goblet:'rodilla',sumo:'rodilla',dbsumo:'rodilla',reverselunge:'rodilla',dblunge:'rodilla',split:'rodilla',lateralunge:'rodilla',stepup:'rodilla',wallsit:'rodilla',
  wall:'empuje_horizontal',incline:'empuje_horizontal',pushup:'empuje_horizontal',kneepush:'empuje_horizontal',press:'empuje_horizontal',neutral:'empuje_horizontal',benchpress:'empuje_horizontal',closepush:'empuje_horizontal',
  bridge:'extension_cadera',dbbridge:'extension_cadera',singlebridge:'extension_cadera',hinge:'bisagra_cadera',rdl:'bisagra_cadera',singlehinge:'bisagra_cadera',sumodead:'bisagra_cadera',
  row:'traccion_horizontal',bentrow:'traccion_horizontal',towelrow:'traccion_horizontal',prone:'escapular',reverse:'escapular',scapular:'escapular',reversefly:'escapular',
  curl:'flexion_codo',hammer:'flexion_codo',seatedcurl:'flexion_codo',tricep:'extension_codo',lyingtricep:'extension_codo',
  lateral:'elevacion_hombro',seatedlateral:'elevacion_hombro',overheadpress:'empuje_vertical',seatedpress:'empuje_vertical',
  calf:'elevacion_talon',dbcalf:'elevacion_talon',seatedcalf:'elevacion_talon',
  deadbug:'estabilizacion_tronco',bird:'estabilizacion_tronco',plank:'estabilizacion_tronco',sideplank:'estabilizacion_tronco',heel:'estabilizacion_tronco'
 });
 const hasOwn=(o,k)=>Object.prototype.hasOwnProperty.call(o,k);
 const isoDay=x=>/^\d{4}-\d{2}-\d{2}$/.test(x);
 const number=(v,min=0,max=200)=>{const n=Number(v);return Number.isFinite(n)?Math.min(max,Math.max(min,n)):min;};
 const dateOffset=(date,days)=>{const d=new Date(date+'T12:00:00Z');d.setUTCDate(d.getUTCDate()+days);return d.toISOString().slice(0,10);};
 function pattern(e){
  const explicit=root.HunterGroups49?.classify(e)?.pattern;if(explicit)return explicit;
  if(!e?.id)return null;
  if(typeof e.pattern==='string'&&e.pattern.length)return e.pattern;
  const candidates=[e.baseId,e.family,e.id].filter(x=>typeof x==='string');
  for(const raw of candidates){
   const id=raw.replace(/(?:_slow|_pause|_short|_easy|-tempo23|-pause23|-control18)$/,'');
   if(hasOwn(knownPatterns,id))return knownPatterns[id];
  }
  return null;
 }
 function load(logs,date,days=7){
  const groups=Object.create(null),patterns=Object.create(null),ids=Object.create(null);
  const engine=root.Engine;
  const min=dateOffset(date,-Math.max(1,Math.min(21,days)));
  let sessions=0,finishedSets=0;
  for(const [key,l] of Object.entries(logs||{})){
   if(!isoDay(key)||key>=date||key<min||!l||l.pain||!Array.isArray(l.items))continue;
   let counted=false;
   for(const item of l.items){
    if(!item?.id||!Array.isArray(item.actual))continue;
    const completed=item.actual.filter(s=>s?.done===true).length;
    if(!completed)continue;
    const e=engine?.byId?.(item.id);
    if(!e)continue;
    if(e.group)groups[e.group]=(groups[e.group]||0)+completed;
    const p=pattern(e);if(p)patterns[p]=(patterns[p]||0)+completed;
    const base=e.baseId||e.family||e.id;
    ids[base]=(ids[base]||0)+completed;
    finishedSets+=completed;counted=true;
   }
   if(counted)sessions++;
  }
  return {groups,patterns,ids,sessions,finishedSets};
 }
 function score(e,context={}){
  const weekly=context.weekly||{groups:{},patterns:{},ids:{}};
  const counts=context.dayCounts||{};
  const patternCounts=context.dayPatterns||{};
  const group=e?.group||'Sin clasificar';
  const p=pattern(e);
  const base=e?.baseId||e?.family||e?.id;
  const groupSets=number(weekly.groups?.[group],0,100);
  const patternSets=p?number(weekly.patterns?.[p],0,100):0;
  const recent=number(weekly.ids?.[base],0,30);
  // Priorización suave: un mismo patrón o grupo no domina la sesión.
  return -Math.min(30,groupSets*1.7)-Math.min(15,patternSets*1.2)-
    Math.min(18,recent*5)-Math.min(14,number(counts[group],0,20)*7)-
    (p?Math.min(13,number(patternCounts[p],0,20)*7):0);
 }
 function dose(e,profile,kind,status,feedback,date,original,weekly){
  const out={...original};
  const group=e?.group||'';
  const strained=Boolean(feedback?.strain),reduced=Boolean(status?.reduced);
  // No incrementar por encima de lo seguro cuando se registran lesiones o fatiga.
  if(reduced||strained||profile?.health?.special||profile?.health?.acute)return out;
  const g=String(profile?.goal||'general');
  if(kind==='strength'){
   if(g==='muscle'&&Number(profile?.minutes)>=30&&feedback?.canProgress&&out.sets<3&&
      number(weekly?.groups?.[group],0,100)<10){out.sets=Math.min(3,out.sets+1);}
   if(number(weekly?.groups?.[group],0,100)>=12)out.sets=Math.min(out.sets,2);
   if(g==='strength')out.rest=Math.max(Number(out.rest)||90,100);
  }else if(kind==='cardio'&&g==='endurance'&&feedback?.canProgress){
   out.target=Math.min(e.unit==='sec'?60:14,out.target+(e.unit==='sec'?5:1));
  }
  if(!Number.isFinite(out.target)||out.target<=0)out.target=original.target;
  return out;
 }
 function catalog(){
  const ex=root.Engine?.exercises||[];
  let group=0,patterns=0,zones=0,equipment=0,models=0;
  for(const e of ex){
   if(e?.group)group++;
   if(pattern(e))patterns++;
   if(Array.isArray(e?.zones)&&e.zones.length)zones++;
   if(e?.equipment)equipment++;
   if(e?.modelPath30&&e?.demoPath30)models++;
  }
  return {total:ex.length,group,patterns,zones,equipment,models,
   notice:'Los patrones ayudan a diversificar sesiones; no prueban seguridad clínica.'};
 }
 root.HunterTraining387=Object.freeze({version:'38.7',pattern,load,score,dose,catalog});
})(typeof window==='undefined'?globalThis:window);
