/* Hunter System 38.51 — niveles graduales por categoría.
 * Clasifica los 1.200 ejercicios existentes; interpreta feedback local y
 * propone mezcla de niveles para planes NUEVOS. No altera el historial.
 */
(function(root){
 'use strict';
 if(root.HunterLevel3851)return;
 const E=root.Engine,G=root.HunterGroups49,CP=root.HunterCategoryProgress51;
 if(!E||!G)throw Error('HunterLevel3851: requiere Engine y HunterGroups49');
 const tierNames=['basic','intermediate','advanced'];
 const catNames=['upper','lower','core','cardio','mobility'];
 const norm=s=>String(s||'').toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g,'').replace(/[^a-z0-9\s-]/g,' ').replace(/\s+/g,' ').trim();
 const tier=e=>Math.max(0,Math.min(2,Number(G.classify(e)?.level??e?.level??0)));
 const category=e=>G.classify(e)?.group||null;
 const family=e=>G.family(e);
 const byId=new Map(E.exercises.map(e=>[e.id,e]));
 if(byId.size!==E.exercises.length)throw Error('HunterLevel3851: catálogo con ID duplicado');
 const inventory=Object.freeze(Object.fromEntries(catNames.map(k=>[k,Object.freeze([0,1,2].map(n=>E.exercises.filter(e=>category(e)===k&&tier(e)===n).length))])));
 if(Object.values(inventory).flat().reduce((a,b)=>a+b,0)!==E.exercises.length)throw Error('HunterLevel3851: catálogo sin clasificar');
 const keywords={
  archer19:['flexiones arqueras','flexiones arqueadas','flexiones tipo arquero','flexion arquera'],
  'home-diamond23':['flexiones diamante','flexiones en diamante','flexion diamante'],
  squatjump:['sentadillas con salto','sentadilla con salto','sentadillas saltando'],
  kneepush:['flexiones con rodillas','flexiones de rodillas'],
  pushup:['flexiones normales','flexiones convencionales','flexiones estandar'],
  chair:['sentadillas con silla','sentadillas con apoyo','sentadillas en silla']
 };
 const synonymsByFamily=new Map();
 for(const [id,synonyms]of Object.entries(keywords)){
  const e=byId.get(id);if(e) synonymsByFamily.set(family(e),synonyms.map(norm));
 }
 const negative=/(?:\bno\s+(?:pude|logre|consegui|termine|complete|aguante|soy capaz)\b|\b(?:imposible|falle|fall[eé]|fracase|demasiado dificil|muy dificil|me costo mucho|no alcance|dolor al hacer|me dolio al hacer|no me salio)\b)/;
 const misleading=/(?:\bno\s+(?:fue|es|estuvo|me parecio)\s+(?:muy\s+)?dificil\b|\bno\s+tuve\s+(?:problemas|dificultad)\b|\bsi\s+pude\b)/;
 const logCategory=l=>CP?.categoryOfLog?.(l)||null;
 function failedExerciseIds(l){
  if(!l||typeof l.note!=='string')return [];
  const ids=new Set();
  const segments=String(l.note).split(/[.;\n]|\bpero\b/i).map(norm).filter(Boolean);
  const present=(l.items||[]).map(x=>byId.get(x.id)).filter(Boolean);
  for(const text of segments){
   if(!negative.test(text)||misleading.test(text))continue;
   for(const e of present){
    const label=norm(e.name);
    const aliases=synonymsByFamily.get(family(e))||[];
    // Sólo se interpreta un movimiento suficientemente específico registrado
    // en esa sesión: se evita atribuir un comentario a ejercicios ajenos.
    const matched=(label.length>=9&&text.includes(label))||aliases.some(a=>text.includes(a));
    if(matched)ids.add(e.id);
   }
  }
  return [...ids];
 }
 function lastCategoryLog(logs,date,cat){
  return Object.entries(logs||{}).filter(([d,l])=>/^\d{4}-\d\d-\d\d$/.test(d)&&d<date&&
    d>=E.plus(date,-90)&&logCategory(l)===cat).sort((a,b)=>b[0].localeCompare(a[0]))[0]||null;
 }
 function assess(logs,date,cat){
  const last=lastCategoryLog(logs,date,cat);
  if(!last)return {action:'none',failedIds:[],failedFamilies:[],lastDate:null,why:''};
  const [lastDate,l]=last,failedIds=failedExerciseIds(l);
  // También permite identificar una serie iniciada pero no terminada.
  for(const x of l.items||[]){if(x.actual?.some(y=>y.done)&&x.actual.some(y=>!y.done)&&!failedIds.includes(x.id))failedIds.push(x.id);}
  const high=Number(l.rpe)>=8 || l.pain===true || l.fatigue===true;
  const action=failedIds.length||high?'down':'none';
  const failedFamilies=[...new Set(failedIds.map(id=>family(byId.get(id))))];
  return {action,failedIds,failedFamilies,lastDate,why:failedIds.length?'ejercicio_no_completado':high?'esfuerzo_alto':'',score:Number(l.rpe)||0};
 }
 function readiness(logs,date,cat){
  const rows=Object.entries(logs||{}).filter(([d,l])=>d<date&&d>=E.plus(date,-90)&&logCategory(l)===cat)
   .sort((a,b)=>b[0].localeCompare(a[0])).slice(0,5);
  if(rows.length<3)return false;
  return rows.slice(0,3).every(([,l])=>l.complete&&!l.pain&&!l.fatigue&&
   Number(l.rpe)>=1&&Number(l.rpe)<=5&&!failedExerciseIds(l).length);
 }
 function context(p,logs={},date=E.dateKey(),cat='upper',count=5){
  const base=Math.max(0,tierNames.indexOf(p?.difficulty));
  const failure=assess(logs,date,cat);
  const ceiling=Math.max(0,base-(failure.action==='down'?1:0));
  const preview=base===1&&ceiling===1&&Number(p?.age)>=18&&
   !p?.limits&&!p?.health?.pain&&readiness(logs,date,cat);
  const maxTier=Math.min(2,ceiling+(preview?1:0));
  return {profileTier:base,ceiling,maxTier,preview,category:cat,count,failure};
 }
 function idealQuota(ctx,count){
  const ratios=ctx.preview?[.6,.2,.2]:ctx.ceiling===0?[1,0,0]:ctx.ceiling===1?[.6,.4,0]:[.2,.4,.4];
  const q=ratios.map(x=>Math.floor(x*count));
  while(q.reduce((a,b)=>a+b,0)<count){const k=ratios.map((x,i)=>x*count-q[i]).indexOf(Math.max(...ratios.map((x,i)=>x*count-q[i])));q[k]++;}
  return q;
 }
 function levelPool(candidates,selected,ctx,count){
  if(!ctx)return candidates;
  const blocked=new Set(ctx.failure.failedFamilies||[]);
  let xs=candidates.filter(e=>tier(e)<=ctx.maxTier&&!blocked.has(family(e)));
  if(!xs.length)xs=candidates.filter(e=>tier(e)<=ctx.ceiling);
  if(!xs.length)return [];
  const quota=idealQuota(ctx,count),used=[0,0,0];
  for(const e of selected)used[tier(e)]++;
  let best=-1,need=-Infinity;
  for(let i=0;i<3;i++){
   const rest=quota[i]-used[i];
   if(rest>0&&xs.some(e=>tier(e)===i)&&rest>need){best=i;need=rest;}
  }
  if(best<0){
   // Se permite una sustitución de nivel cercano cuando no hay opciones
   // suficientes del nivel preferido para el patrón requerido.
   const possible=[...new Set(xs.map(tier))];best=possible.sort((a,b)=>(quota[b]-used[b])-(quota[a]-used[a])||a-b)[0];
  }
  return xs.filter(e=>tier(e)===best);
 }
 function dose(item,e,ctx){
  if(!ctx||!item)return item;
  const t=tier(e),trial=ctx.preview&&t===2&&ctx.profileTier===1;
  const out={...item,level3851:{tier:t,label:tierNames[t],profile:tierNames[ctx.profileTier],
    category:ctx.category,preview:trial,limitedByFeedback:ctx.failure.action==='down',
    reason:ctx.failure.why||'mezcla_por_nivel'}};
  // Prueba avanzada opcional y puntual; no añade series de dificultad.
  if(trial){out.sets=1;out.target=Math.min(Number(out.target)||4,e.unit==='sec'?10:4);out.noAutoLoad=true;}
  return out;
 }
 function monarchCandidates(p,logs,date,cat){
  const ctx=context(p,logs,date,cat,5),blocked=new Set(ctx.failure.failedFamilies);
  return E.exercises.filter(e=>category(e)===cat&&tier(e)<=ctx.maxTier&&
   G.inKind(e,cat==='cardio'?'cardio':cat==='mobility'?'recovery':'strength')&&
   !G.power(e)&&!G.impact(e)&&G.eligible(e,p)&&
   !blocked.has(family(e))&&root.HunterMaterials3814?.eligible?.(e,p)!==false&&
   root.Adapt?.allowed?.(e,p)!==false);
 }
 root.HunterLevel3851=Object.freeze({version:'38.51',tier,category,inventory,catalogSize:E.exercises.length,
  failedExerciseIds,assess,readiness,context,idealQuota,levelPool,dose,monarchCandidates});
})(window);
