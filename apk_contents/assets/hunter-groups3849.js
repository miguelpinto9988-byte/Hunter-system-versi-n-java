/* Hunter System 38.49: one planning vocabulary for missions and routine library.
 * This is an explicit rules engine, not a diagnostic AI. Catalogue geometry and
 * exercise descriptions remain unchanged. All legacy clinical/material gates run.
 */
(function(root){'use strict';
 if(root.HunterGroups49)return;
 const E=root.Engine,A=root.Adapt,rows=root.HunterGroupRows49;
 if(!E||!A||rows?.length!==1200)throw Error('38.49: faltan metadatos de planificación');
 const labels=Object.freeze({upper:'Tren superior',lower:'Tren inferior',core:'Tren central',cardio:'Cardio',mobility:'Movilidad y activación'});
 const byId=new Map(),families=new Map();
 for(const [id,group,role,pattern,family,level,impact,conditioning]of rows){
  const m=Object.freeze({id,group,role,pattern,family,level,impact,conditioning,
   power:role==='power',jumpPower:role==='power'&&group==='lower'&&impact,
   primary:role==='primary'});
  if(byId.has(id)||!labels[group])throw Error('38.49: clasificación duplicada o desconocida');
  byId.set(id,m);if(!families.has(family)||id===family)families.set(family,m);
 }
 for(const e of E.exercises)if(!byId.has(e.id))throw Error('38.49: ejercicio sin clasificar '+e.id);
 delete root.HunterGroupRows49;
 const classify=e=>byId.get(typeof e==='string'?e:e?.id)||null;
 const level=e=>classify(e)?.level??e?.level;
 const power=e=>classify(e)?.power===true;
 const impact=e=>classify(e)?.impact===true;
 const family=e=>classify(e)?.family||e?.baseId||e?.id;
 function matches(e,type){
  const m=classify(e);if(!m)return false;
  if(m.group===type)return true;
  // A jump is lower-body power and can also be a short cardio interval.
  return type==='cardio'&&m.jumpPower;
 }
 function inKind(e,kind){return kind==='strength'?['upper','lower','core'].some(t=>matches(e,t)):
  kind==='cardio'?matches(e,'cardio'):kind==='recovery'?matches(e,'mobility'):false;}
 function reduced(p,status={}){return !!(status.reduced||p?.limits||p?.health?.special||p?.health?.acute||p?.health?.redFlags||
  p?.health?.pain&&p.health.pain!=='none'||(p?.injuries||[]).some(x=>x&&x.stage!=='past')||A.mode(p).reduced);}
 function reasons(e,p){
  const m=classify(e),out=[];if(!m||!p)return ['Faltan datos de planificación'];
  const noImpact=p.restrictions?.noImpact||p.physicalLimits?.noImpact||p.preferences?.noImpact;
  if(m.impact&&noImpact)out.push('Saltos e impactos excluidos por el perfil');
  if((m.power||m.impact)&&(reduced(p)||Number(p.age)<18))out.push('Potencia e impactos pendientes de revisión individual');
  const caps=new Set(Array.isArray(p.capabilities46)?p.capabilities46:[]);
  if(m.impact&&m.group!=='upper'&&!caps.has('landing'))out.push('Confirma primero tu control de aterrizaje en Perfil');
  if(m.power&&m.group==='upper'&&!caps.has('supporthold'))out.push('Falta confirmar control de apoyos para el empuje explosivo');
  if(m.power&&/clean/.test(m.family)&&(!caps.has('ballistichinge')||!caps.has('olympic')))out.push('Falta práctica previa de la cargada y su recepción');
  return out;
 }
 const eligible=(e,p)=>reasons(e,p).length===0;
 const originalAllowed=A.allowed,originalReasons=A.reasons;
 A.allowed=function(e,p,...args){return eligible(e,p)&&originalAllowed.call(this,e,p,...args);};
 A.reasons=function(e,p,...args){return [...new Set([...originalReasons.call(this,e,p,...args),...reasons(e,p)])];};
 const distance=(a,b)=>Math.abs((Date.parse(a+'T12:00:00Z')-Date.parse(b+'T12:00:00Z'))/86400000);
 function powerReady(p,{logs={},date=E.dateKey(),tracker,status={},feedback={}}={}){
  if(reduced(p,status)||feedback.strain||Number(p.trainingScale||1)<1)return false;
  if(logs[date]?.pain||logs[date]?.fatigue||Number(logs[date]?.rpe)>=8)return false;
  const dates=new Set();
  const isPower=id=>{const m=classify(id)||families.get(id);return m?.power||m?.impact;};
  for(const [day,log]of Object.entries(logs)){
   if(!/^\d{4}-\d{2}-\d{2}$/.test(day)||day>=date||!log)continue;
   const items=(log.items||[]).filter(x=>isPower(x.id)&&(log.complete||x.actual?.some(a=>a.done)));
   if(items.length){if(distance(day,date)<2)return false;if(distance(day,date)<7)dates.add(day);}
  }
  for(const row of tracker?.snapshot?.()||[]){
   if(!isPower(row.id))continue;
   if(distance(row.date,date)<2)return false;
   if(row.date<date&&distance(row.date,date)<7)dates.add(row.date);
  }
  return dates.size<3;
 }
 function context(p,kind,type,extras={}){
  const ctx={p,kind,type:type||(kind==='cardio'?'cardio':kind==='recovery'?'mobility':'general'),...extras};
  if(ctx.type==='full')ctx.type='general';
  ctx.ready=powerReady(p,ctx);return ctx;
 }
 function selectionPool(entries,selected,count,ctx){
  // No power/impact during reduced weeks, after recent impact, or twice in a
  // session. Never turn ankle activation into a substitute squat.
  let list=entries.filter(e=>ctx.ready||!power(e)&&!impact(e));
  if(selected.some(e=>power(e)||impact(e)))list=list.filter(e=>!power(e)&&!impact(e));
  if(!list.length)return [];
  const powerSlots=ctx.kind==='cardio'?count>=2:count>=3&&['lower','general'].includes(ctx.type);
  if(!selected.length&&powerSlots&&ctx.ready){
   const xs=list.filter(power);if(xs.length)return xs;
  }
  if(ctx.kind==='strength'){
   const needed=Math.min(count-(powerSlots?1:0),Math.max(1,Math.ceil(count*.6)));
   const mains=selected.filter(e=>classify(e)?.primary).length;
   const primaries=list.filter(e=>classify(e)?.primary);
   if(primaries.length&&mains<needed)list=primaries;
   else{
    const accessory=selected.filter(e=>classify(e)?.role==='accessory').length;
    if(accessory>=1||count<3)list=list.filter(e=>classify(e)?.role!=='accessory');
    if(selected.length===count-1&&count>=4&&accessory===0){
     const xs=list.filter(e=>classify(e)?.role==='accessory');if(xs.length)list=xs;
    }
   }
  }
  return list;
 }
 function score(e,p,kind,selected=[]){
  const m=classify(e);if(!m)return -1000;
  const n=Math.max(0,['basic','intermediate','advanced'].indexOf(p.difficulty));
  let s=0;
  // Diversity uses the mechanical pattern, not only the named muscle group.
  s-=selected.filter(x=>classify(x)?.pattern===m.pattern).length*38;
  if(kind==='strength'){
   s+=m.primary?30:m.role==='accessory'?-12:0;
   // Compare compatible variants at the selected level instead of repeatedly
   // choosing the easiest squat/hinge merely because of an old group bonus.
   if(!reduced(p))s-=Math.max(0,n-m.level)*35;
   if(p.equipment==='gym'&&e.gymOnly23&&m.primary)s+=14;
   if(m.role==='accessory'&&/tobillo|plantar/.test(m.pattern))s-=35;
  }
  if(kind==='cardio'&&!reduced(p))s+=m.conditioning*18;
  if(m.power)s-=Math.abs(n-m.level)*16;
  return s;
 }
 function adjustDose(e,p,kind,item,ctx={}){
  const m=classify(e),out={...item};if(!m)return out;
  const n=Math.max(0,['basic','intermediate','advanced'].indexOf(p.difficulty));
  const today=ctx.logs?.[ctx.date||E.dateKey()];
  const limited=reduced(p,ctx.status)||ctx.feedback?.strain||today?.pain||today?.fatigue||Number(today?.rpe)>=8;
  const scale=Math.max(.25,Math.min(1,Number(p.trainingScale)||1));
  if(m.power){
   // Quality contacts, not high-repetition endurance sets. More demanding
   // jump variants keep their existing capability/tier and catalogue caps.
   const target=e.unit==='sec'?[8,10,12][n]:[3,4,5][n];
   out.target=Math.max(1,Math.min(Number(out.target)||target,Math.floor(target*scale)));
   out.sets=Math.max(1,Math.min(Number(out.sets)||1,limited?1:2));
   out.rest=90;out.noAutoLoad=true;
   out.safetyNote=[out.safetyNote,'Potencia: repeticiones de calidad; termina si pierdes la recepción o el control.'].filter(Boolean).join(' ');
  }else if(m.impact){
   out.target=Math.max(1,Math.min(Number(out.target)||1,e.unit==='sec'?[15,20,25][n]:[4,5,6][n]));
   out.sets=Math.max(1,Math.min(Number(out.sets)||1,2));out.rest=Math.min(90,Math.max(60,Number(out.rest)||0));
  }else if(kind==='cardio'&&e.unit==='sec'&&!limited&&scale===1&&m.conditioning>=2){
   out.target=Math.max(Number(out.target)||1,[35,40,45][n]);
  }
  out.rest=Math.min(90,Math.max(30,Number(out.rest)||30));
  out.planning49={group:m.group,role:m.role,pattern:m.pattern};
  return out;
 }
 function phase(day,p){
  if(!day?.items?.length||!p)return null;
  const used=new Set(day.items.map(x=>family(E.byId(x.id))));
  const usedKeys=new Set(day.items.map(x=>E.byId(x.id)).filter(Boolean).map(e=>Warm24.key(e)));
  const options=['fastmarch','stepjack','heelback','box','heeldig','sidestep','seatedpunch','seatedmarch','seatedstep']
   .map(E.byId).filter(e=>e&&!impact(e)&&!power(e)&&!used.has(family(e))&&!usedKeys.has(Warm24.key(e))&&
    root.HunterDynamic381?.compatible(e,p,reduced(p)));
  const first=options.find(e=>!e.seated)||options[0];
  if(!first)return {blocked:true,reason:'Revisa las indicaciones del perfil: no hay cardio preparatorio compatible.'};
  const sec=Warm25.seconds(p);
  return {warm:{id:first.id,seconds:sec,label:'Calentamiento progresivo · '+first.name},
   cool:{id:first.id,seconds:sec,label:'Cierre suave · '+first.name},
   reason:'Cada fase dura como máximo un minuto. El cierre se hace a ritmo suave y no suma series principales.'};
 }
 const partitions=new Map(Object.keys(labels).map(k=>[k,E.exercises.filter(e=>classify(e).group===k)]));
 const byGroup=k=>(partitions.get(k)||[]).slice();
 root.HunterGroups49=Object.freeze({version:'38.49',labels,classify,level,matches,inKind,eligible,reasons,byGroup,
  power,impact,family,reduced,context,powerReady,selectionPool,score,adjustDose,phase,
  inventory:()=>({total:byId.size,groups:Object.fromEntries(Object.keys(labels).map(k=>[k,[...byId.values()].filter(m=>m.group===k).length]))})});
 root.HunterCatalog46=Object.freeze({...root.HunterCatalog46,version:'38.49',byGroup,groups:labels});
})(window);
