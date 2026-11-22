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

;
/* Hunter System 38.49 | Parche de calidad de selección y progreso 1.
 * Separa la intención del entrenamiento (fuerza, cardio, movilidad), el papel
 * biomecánico (principal, accesorio, preparación) y el patrón motor.
 * No reescribe el catálogo original ni el historial existente.
 */
(function(root){
 'use strict';
 if(root.HunterQuality50)return;
 const old=root.HunterGroups49,E=root.Engine;
 if(!old||!E||!root.Adapt)throw Error('Quality50: motor 38.49 ausente');
 const version='38.49-quality1';

 // Identificadores/familias auditadas: el catálogo 38.49 trataba estos movimientos
 // de rango articular, coordinación escapular o activación como fuerza PRINCIPAL.
 // No se deduce fuerza solo de la región corporal ni de la palabra "elevación".
 const preparation=new Set([
  'prone','reverse','wallslide','scapular','contralateral',
  'pau-pushchild','pau-armworld','pau-armcircle-f','pau-armcircle-b',
  'pau-armupdown','pau-armpulses','pau-armfly','pau-armsback',
  'pau-armraise','pau-armcurl','front-seated-reference','w-t19','y-t-w19',
  'hs379-scapular','hs379-shoulderset','home-scappush30',
  'new46-scapdip',
  'new46-scapularhang','new46-bander','new46-bandir','new46-banddiagonal',
  'new46-serratusband','new46-ringsrotation','new46-ringturnout',
  'new46-cubanrotation','new46-cableer90','new46-supineshoulderir',
  'new46-halo','new46-shouldercar',
  'new46-airplanefree','new46-seatedhinge'
 ]);
 // Casos semánticos comprobables que pueden venir con nuevos ID de una variante.
 // Una elevación LATERAL CON MANCUERNAS no cae en este grupo.
 const prepNames=/\b(?:estiramiento|movilidad articular|c[ií]rculos? de hombros|c[ií]rculos? de brazos|deslizamiento (?:de brazos )?en pared|retracci[oó]n escapular|elevaci[oó]n corta de hombros|rotaci[oó]n (?:externa|interna) de hombro|rotaci[oó]n escapular)\b/i;
 const secondaryOnly=new Set(['gym-face-pull23','new46-cubanpress']);
 const excludeFromAuto=new Set(['new46-dbpushpress','new46-pushjerk',
  'new46-hangclean','new46-bottomclean','new46-dbsnatch']);
 const byId=new Map();
 const toId=e=>typeof e==='string'?e:e?.id;
 function movement(e,m){
  if(!m)return 'none';
  if(m.group==='cardio')return 'conditioning';
  if(m.group==='mobility')return 'mobility';
  if(m.power)return 'power';
  const s=(m.family||e?.id||'').toLowerCase();
  if(m.group==='upper'){
   if(/(?:row|remo|pull|chinup|lat|meadows|inverted|facepull|face-pull|backmachine|pendlay|renegade|towelrow)/.test(s)||['bentrow','pullover'].includes(s))return 'pull';
   if(/(?:press|push|bench|dip|flexion|fondos|hspu|handstand|chest-machine|handrelease)/.test(s)||
       ['wall','incline','neutral','squeezepress','kneepush','closepush','pike19','archer19','decline19','singlepress','altfloorpress','singleoverhead'].includes(s))return 'push';
   return 'isolation';
  }
  if(m.group==='lower'){
   if(/(?:squat|lunge|zancada|split|stepup|stepdown|kneebend|wallsit|quad|sledpush)/.test(s)||['chair','goblet','sumo','dbsumo','doublefront'].includes(s))return 'knee';
   if(/(?:hinge|bridge|dead|rdl|hipthrust|hip-|hip_|ham|hinge|sledback|kickback)/.test(s)||['hipthrust','sumodead','suitdead'].includes(s))return 'hip';
   return 'accessory';
  }
  if(m.group==='core'){
   if(/(?:plank|deadbug|bird|pallof|carry|hold|suitcase|farmer|brace|bear)/.test(s))return 'brace';
   return 'trunk';
  }
  return 'other';
 }
 function classify(e){
  const raw=old.classify(e);if(!raw)return null;
  const entity=typeof e==='string'?E.byId(e):e;
  const prep=preparation.has(raw.family)||preparation.has(raw.id)||
   (raw.group==='upper'&&prepNames.test(String(entity?.name||'')));
  if(prep&&['upper','lower'].includes(raw.group))return Object.freeze({...raw,
   group:'mobility',role:'activation',primary:false,pattern:'preparacion_'+raw.family,
   intent50:'preparation',originalGroup50:raw.group,movement50:'mobility'});
  const motor=movement(entity,raw);
  const main=raw.role==='primary'&&(
   raw.group==='upper'&&['push','pull'].includes(motor)||
   raw.group==='lower'&&['knee','hip'].includes(motor)||
   raw.group==='core');
  const primary=main&&!secondaryOnly.has(raw.family);
  const role=raw.role==='primary'&&!primary?'accessory':raw.role;
  return Object.freeze({...raw,role,primary,intent50:raw.group==='mobility'?'recovery':
   raw.group==='cardio'?'cardio':raw.role==='power'?'power':'strength',movement50:motor});
 }
 for(const e of E.exercises){if(byId.has(e.id))throw Error('Quality50: ID duplicado');byId.set(e.id,classify(e));}
 const meta=e=>byId.get(toId(e))||null;
 const family=e=>meta(e)?.family||old.family(e);
 const power=e=>old.power(e),impact=e=>old.impact(e);
 function matches(e,type){
  const m=meta(e);if(!m)return false;
  // Potencia y saltos no se añaden automáticamente a cardio ni a fuerza.
  return m.group===type&&!power(e)&&!impact(e)&&!excludeFromAuto.has(m.family);
 }
 function inKind(e,kind){
  const m=meta(e);if(!m||excludeFromAuto.has(m.family))return false;
  return kind==='strength'?['upper','lower','core'].includes(m.group)&&!power(e)&&!impact(e):
   kind==='cardio'?m.group==='cardio'&&m.role==='conditioning'&&!impact(e)&&!power(e):
   kind==='recovery'?m.group==='mobility'&&!impact(e)&&!power(e):false;
 }
 function choosePattern(list,selected,first,second){
  if(!selected.some(x=>meta(x)?.movement50===first)){
   const options=list.filter(x=>meta(x)?.movement50===first);if(options.length)return options;
  }
  if(!selected.some(x=>meta(x)?.movement50===second)){
   const options=list.filter(x=>meta(x)?.movement50===second);if(options.length)return options;
  }
  return null;
 }
 function selectionPool(entries,selected,count,ctx={}){
  let list=entries.filter(e=>inKind(e,ctx.kind)&&(
    ctx.kind!=='strength'||!['upper','lower','core'].includes(ctx.type)||matches(e,ctx.type)));
  if(!list.length)return [];
  if(ctx.kind==='strength'){
   // Una rutina de fuerza empieza con movimientos reales contra resistencia.
   const mains=list.filter(e=>meta(e)?.primary);
   const type=ctx.type;
   if(type==='upper'){
    const pattern=choosePattern(mains,selected,'push','pull');
    if(pattern?.length)return pattern;
   }else if(type==='lower'){
    const pattern=choosePattern(mains,selected,'knee','hip');
    if(pattern?.length)return pattern;
   }else if(type==='core'){
    const pattern=choosePattern(mains,selected,'brace','trunk');
    if(pattern?.length)return pattern;
   }else{
    // Full body: primero cubrir regiones corporales distintas.
    for(const group of ['lower','upper','core']){
     if(!selected.some(x=>meta(x)?.group===group)){
      const options=mains.filter(x=>meta(x)?.group===group);if(options.length)return options;
     }
    }
   }
   // Accesorios: máximo uno y solo al final de una sesión ya sustentada en básicos.
   const enough=selected.filter(e=>meta(e)?.primary).length>=2;
   const already=selected.some(e=>meta(e)?.role==='accessory');
   if(count>=4&&enough&&!already&&selected.length===count-1){
    const extras=list.filter(e=>meta(e)?.role==='accessory');
    if(extras.length)return extras;
   }
   return mains.length?mains:list.filter(e=>meta(e)?.role==='accessory'&&count===1);
  }
  if(ctx.kind==='cardio')return list.filter(e=>meta(e)?.role==='conditioning');
  if(ctx.kind==='recovery'){
   if(!selected.length){const mobility=list.filter(e=>meta(e)?.role==='mobility');if(mobility.length)return mobility;}
   const other=list.filter(e=>!selected.some(x=>family(x)===family(e)));
   return other.length?other:list;
  }
  return list;
 }
 function score(e,p,kind,selected=[]){
  const m=meta(e);if(!m)return -10000;
  const prior=old.score(e,p,kind,selected);
  let value=prior;
  if(kind==='strength'){
   value+=m.primary?55:-80;
   const used=selected.filter(x=>meta(x)?.movement50===m.movement50).length;
   value-=used*32;
   if(m.group==='upper'&&m.movement50==='push')value+=12;
   if(m.group==='lower'&&m.movement50==='knee')value+=10;
  }else if(kind==='cardio')value+=m.role==='conditioning'?30:-500;
  else if(kind==='recovery')value+=m.role==='mobility'?20:0;
  return value;
 }
 function evidence(e,logs={},date=E.dateKey()){
  const m=meta(e),seen=new Set();
  if(!m)return 0;
  const d0=Date.parse(date+'T12:00:00Z');
  for(const [day,l] of Object.entries(logs||{})){
   if(!/^\d{4}-\d{2}-\d{2}$/.test(day)||!l||!l.complete||l.pain||l.fatigue||l.manual)continue;
   const distance=(d0-Date.parse(day+'T12:00:00Z'))/86400000;
   if(distance<1||distance>21||!(+l.rpe>=1&&+l.rpe<=7))continue;
   const success=(l.items||[]).some(x=>{
    const xMeta=meta(x.id);return xMeta&&(xMeta.family===m.family||xMeta.group===m.group&&
     xMeta.movement50===m.movement50&&m.movement50!=='isolation')&&
     (x.actual||[]).some(s=>s?.done===true);
   });
   if(success)seen.add(day);
  }
  return seen.size;
 }
 function adjustDose(e,p,kind,item,ctx={}){
  const out={...old.adjustDose(e,p,kind,item,ctx)};
  const m=meta(e);if(!m)return out;
  // Nunca se progresa una preparación como si fuese una serie principal.
  const similar=evidence(e,ctx.logs,ctx.date||E.dateKey());
  const symptoms=Boolean(ctx.status?.reduced||ctx.feedback?.strain||ctx.logs?.[ctx.date]?.pain||ctx.logs?.[ctx.date]?.fatigue);
  const canAdvance=!symptoms&&ctx.feedback?.canProgress===true&&similar>=2&&
   !out.noAutoLoad&&Number(p?.age)>=18;
  if(kind==='strength'&&m.primary){
   const base=e.unit==='sec'?20:p?.goal==='muscle'?10:8;
   const cap=base+(canAdvance?(e.unit==='sec'?5:1):0);
   out.target=Math.max(1,Math.min(Number(out.target)||base,cap));
   if(!canAdvance)out.noAutoLoad=true;
  }else if(kind==='recovery'){
   out.noAutoLoad=true;
  }else if(kind==='cardio'&&e.unit==='sec'&&!canAdvance){
   out.target=Math.max(1,Math.min(Number(out.target)||30,30));
  }
  out.planning49={group:m.group,role:m.role,pattern:m.pattern};
  out.quality50={intent:m.intent50,movement:m.movement50,
   relatedSessions:similar,progression:canAdvance?'con_evidencia':'mantener'};
  return out;
 }
 const byGroup=k=>E.exercises.filter(e=>meta(e)?.group===k);
 const groups=Object.fromEntries(Object.keys(old.labels).map(k=>[k,byGroup(k).length]));
 const effective=Object.freeze({...old,version,classify:meta,matches,inKind,selectionPool,score,adjustDose,byGroup,
  family,inventory:()=>({total:byId.size,groups})});
 root.HunterGroups49=effective;
 if(root.HunterCatalog46)root.HunterCatalog46=Object.freeze({...root.HunterCatalog46,
  version,byGroup});
 root.HunterQuality50=Object.freeze({version,classification:meta,evidence,
  inventory:()=>({...effective.inventory(),preparatory:[...byId.values()].filter(m=>m.intent50==='preparation').length,
   upperPrimaries:[...byId.values()].filter(m=>m.group==='upper'&&m.primary).length})});
})(window);

;
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

;
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

;
/* Weekly central-body integration 38.47. */
/* Hunter System V38.1 - generador de sesiones bajo demanda.
 * No enumera ni almacena combinaciones; selecciona de demostraciones 2D existentes.
 * Las restricciones clínicas continúan en HunterPlanner379/Adapt.
 */
(function(root){
'use strict';
const E=root.Engine,S=root.Schedule,A=root.Adapt,W=root.Weekly;
if(!E||!S||!A||!W){console.error('Dynamic381: dependencias no disponibles');return;}
const VERSION='38.51-level-mix-and-feedback';
// Inyectado por la herramienta de compilación tras comprobar nombres y archivos en APK.
const VERIFIED_IDS=new Set(["new46-pullup","new46-chinup","new46-scapularhang","new46-invertedrow","new46-paralleldip","new46-wallhspu","new46-dbpushpress","new46-bander","new46-bandir","new46-waitercarry","new46-bandpullapart","new46-banddiagonal","new46-bandrow","new46-kneelbandlat","new46-serratusband","new46-faceer","new46-trxcurl","new46-trxtriceps","new46-trxfly","new46-trxy","new46-trxpress","new46-trxhighrow","new46-rotationalrow","new46-ringsrotation","new46-ringturnout","new46-cubanrotation","new46-scapdip","new46-bottomclean","new46-bottompress","new46-halo","new46-pushjerk","new46-dbsnatch","new46-hangclean","new46-meadowsrow","new46-ringlat","new46-cableer90","new46-cubanpress","new46-crosscabletri","new46-halfkneellandmine","new46-commandopull","new46-zpress","new46-forearmlever","new46-wristdeviation","new46-wristcurl","new46-wristexte","new46-bandd2","new46-bandd1","new46-supineshoulderir","new46-nordic","new46-reversenordic","new46-copenhagen","new46-airplanefree","new46-stepdown","new46-hiphike","new46-kbswing","new46-sledpush","new46-sledback","new46-ankleinvert","new46-ankleevert","new46-shortfoot","new46-toeyoga","new46-toetowel","new46-monsterwalk","new46-lateralbandwalk","new46-reverseclam","new46-pronehipir","new46-tke","new46-spanishsquat","new46-sissysquat","new46-zercher","new46-overheadsquat","new46-jefferson","new46-goodmorning","new46-seatedhinge","new46-trxsplit","new46-trxsquat","new46-slidelateral","new46-slidereverse","new46-ballham","new46-ballhip","new46-bandkick","new46-walltibia","new46-soleus","new46-ballsqueeze","new46-copenhagendyn","new46-firehydrant","new46-pivotlunge","new46-heelwalk","new46-toewalk","new46-bandplantar","new46-sideadduct","new46-supinebandmarch","new46-turkish","new46-halfturkish","new46-abwheel","new46-lsit","new46-hangingknee","new46-hanginglegs","new46-pallofwalk","new46-suitcasecarry","new46-rackcarry","new46-stirpot","new46-windmill","new46-figureeight","new46-landminetwist","new46-dragonflag","new46-tucklever","new46-hangingwiper","new46-abwheelstand","new46-trxpike","new46-trxtuck","new46-balltuck","new46-ballpike","new46-banddeadbug","new46-bandbird","new46-sidecrow","new46-sidepallof","new46-seatedpallof","new46-kneelchop","new46-kneellift","new46-doublerack","new46-sliderfly","new46-sitthrough","new46-romanknee","new46-burpee","new46-boxjump","new46-shuttle","new46-backrun","new46-ladderinout","new46-singlepogo","new46-mbrotation","new46-mbslam","new46-ropealternating","new46-sprintbrake","new46-doublepogo","new46-forwardhop","new46-crosshop","new46-turnjump","new46-splitjump","new46-droplanding","new46-depthjump","new46-lateralhurdle","new46-mbchest","new46-mbscoop","new46-mboverback","new46-mbcatch","new46-ropedouble","new46-ropecircles","new46-ropeslam","new46-jumpropealt","new46-jumpropeside","new46-ickyshuffle","new46-laddercarioca","new46-tdrill","new46-hexagon","new46-askip","new46-resistedsprint","new46-sledlateral","new46-sledrope","new46-mountainfast","new46-shouldercar","new46-hipcarquad","new46-hip9090lift","new46-sleeper","new46-thoracicroller","new46-frogrock","new46-wristrock","new46-pelvictilt","new46-deepshift","new46-mediannerve","new46-ulnarnerve","new46-sciaticnerve","new46-peronealnerve","new46-quadthoracic","new46-chairthoracic","new46-crossshoulder","new46-stickextension","new46-tricepsstretch","new46-benchprayer","new46-doorpec","new46-foampec","new46-adductorrock","new46-hipcapsule","new46-pronehipcontrol","new46-pronequad","new46-couchstretch","new46-seatedhipir","new46-bentcalfstretch","new46-bigtoestretch","new46-tendonglides","new46-thumbopposition","new46-chinretract","new46-lungerotation","new46-pelvicclock","new46-scapcar","new46-butterfly","hs379-scapular","hs379-palms","hs379-isobiceps","hs379-shoulderset","chair","squat","goblet","wall","incline","pushup","press","neutral","bridge","hinge","rdl","row","bentrow","prone","reverse","curl","hammer","calf","dbcalf","deadbug","bird","plank","heel","sideleg","march","shoulder","ankle","cat","wallslide","lateral","benchpress","inclinebench","kneepush","closepush","squeezepress","sumo","dbsumo","reverselunge","dblunge","split","lateralunge","stepup","wallsit","singlecalf","seatedcalf","singlebridge","dbbridge","kickback","clam","singlehinge","sumodead","pullover","reversefly","towelrow","scapular","overheadpress","frontlift","shouldertap","seatedcurl","concentration","tricep","lyingtricep","reversecurl","sideplank","kneelingplank","reversecrunch","curlup","standingcore","suitcase","fastmarch","sidestep","stepjack","box","heelback","kneeliftcardio","walk","skater","jog","jumpjack","rope","mountain","squatstep","shadowcombo","hipcircle","chestopen","thoracic","hipflex","hamstretch","calfstretch","wrist","neck","breathing","seatedpunch","seatedmarch","seatedstep","heeltoe","legextend","seatedhip","toelift","seatedheel","seatedside","seatedturn","seatedreach","elbowmob","forearmmob","wristmob","fingers","wallcalf","standingabduct","standingextend","standingcurl","tandem","balance","linewalk","doublefront","suitdead","dbsplit","seatedpress","seatedlateral","seatedfly","curlhold","farmerhold","wallplank","supinearms","chair_slow","chair_pause","squat_slow","squat_pause","goblet_slow","goblet_pause","wall_slow","wall_pause","incline_slow","incline_pause","pushup_slow","pushup_pause","press_slow","press_pause","neutral_slow","neutral_pause","bridge_slow","bridge_pause","hinge_slow","hinge_pause","rdl_slow","rdl_pause","row_slow","row_pause","bentrow_slow","bentrow_pause","prone_slow","prone_pause","reverse_slow","reverse_pause","curl_slow","curl_pause","hammer_slow","hammer_pause","calf_slow","calf_pause","dbcalf_slow","dbcalf_pause","deadbug_slow","deadbug_pause","bird_slow","bird_pause","plank_short","plank_easy","heel_slow","heel_pause","sideleg_slow","sideleg_pause","march_short","march_easy","shoulder_short","shoulder_easy","ankle_short","ankle_easy","cat_slow","cat_pause","wallslide_slow","wallslide_pause","lateral_slow","lateral_pause","benchpress_slow","benchpress_pause","inclinebench_slow","inclinebench_pause","kneepush_slow","kneepush_pause","closepush_slow","closepush_pause","squeezepress_slow","squeezepress_pause","sumo_slow","sumo_pause","dbsumo_slow","dbsumo_pause","reverselunge_slow","reverselunge_pause","dblunge_slow","dblunge_pause","split_slow","split_pause","lateralunge_slow","lateralunge_pause","stepup_slow","stepup_pause","wallsit_short","wallsit_easy","singlecalf_slow","singlecalf_pause","seatedcalf_slow","seatedcalf_pause","singlebridge_slow","singlebridge_pause","dbbridge_slow","dbbridge_pause","kickback_slow","kickback_pause","clam_slow","clam_pause","singlehinge_slow","singlehinge_pause","sumodead_slow","sumodead_pause","pullover_slow","pullover_pause","reversefly_slow","reversefly_pause","towelrow_short","towelrow_easy","scapular_slow","scapular_pause","overheadpress_slow","overheadpress_pause","frontlift_slow","frontlift_pause","shouldertap_slow","shouldertap_pause","seatedcurl_slow","seatedcurl_pause","concentration_slow","concentration_pause","tricep_slow","tricep_pause","lyingtricep_slow","lyingtricep_pause","reversecurl_slow","reversecurl_pause","sideplank_short","sideplank_easy","kneelingplank_short","kneelingplank_easy","reversecrunch_slow","reversecrunch_pause","curlup_slow","curlup_pause","standingcore_slow","standingcore_pause","suitcase_short","suitcase_easy","fastmarch_short","fastmarch_easy","sidestep_short","sidestep_easy","stepjack_short","stepjack_easy","box_short","box_easy","heelback_short","heelback_easy","kneeliftcardio_short","kneeliftcardio_easy","walk_short","walk_easy","skater_short","skater_easy","jog_short","jog_easy","jumpjack_short","jumpjack_easy","rope_short","rope_easy","mountain_short","mountain_easy","squatstep_short","squatstep_easy","shadowcombo_short","shadowcombo_easy","hipcircle_short","hipcircle_easy","chestopen_short","chestopen_easy","thoracic_slow","thoracic_pause","hipflex_short","hipflex_easy","hamstretch_short","hamstretch_easy","calfstretch_short","calfstretch_easy","wrist_short","wrist_easy","neck_short","neck_easy","breathing_short","breathing_easy","seatedpunch_short","seatedpunch_easy","seatedmarch_short","seatedmarch_easy","seatedstep_short","seatedstep_easy","heeltoe_slow","heeltoe_pause","legextend_slow","legextend_pause","seatedhip_slow","seatedhip_pause","toelift_slow","toelift_pause","seatedheel_slow","seatedheel_pause","seatedside_slow","seatedside_pause","seatedturn_slow","seatedturn_pause","seatedreach_slow","seatedreach_pause","elbowmob_slow","elbowmob_pause","forearmmob_slow","forearmmob_pause","wristmob_slow","wristmob_pause","fingers_slow","fingers_pause","wallcalf_slow","wallcalf_pause","standingabduct_slow","standingabduct_pause","standingextend_slow","standingextend_pause","standingcurl_slow","standingcurl_pause","tandem_short","tandem_easy","balance_short","balance_easy","linewalk_short","linewalk_easy","doublefront_slow","doublefront_pause","suitdead_slow","suitdead_pause","dbsplit_slow","dbsplit_pause","seatedpress_slow","seatedpress_pause","seatedlateral_slow","seatedlateral_pause","seatedfly_slow","seatedfly_pause","curlhold_short","curlhold_easy","farmerhold_short","farmerhold_easy","wallplank_short","wallplank_easy","supinearms_slow","supinearms_pause","bearcrawl","childpose","contralateral","latstretch","bulgarian","diagonalraise","hipthrust","farmerwalk","inclinefly","dbsidelunge","vstep","heeldig","doubletouch","frontbackstep","boxupper","stepreach","sidebendstand","bearhold","altcurl","althammer","singlelateral","singlefront","singlepress","altfloorpress","singleoverhead","altseatedpress","altseatedcurl","singletricep","squathold","goblethold","bridgehold","calfhold","rowhold","splitpause","slowmarch","sidestepinterval","boxinterval","stepjackinterval","seatedmarchinterval","heelcontrolled","birdcontrolled","abductcontrolled","pau-altpress","pau-overtriceps","pau-arnold","pau-lateralcurl","pau-curlcombo","pau-raisecombo","pau-standingfly","pau-presscombo","pau-kickback","pau-plankup","pau-pushchild","pau-armworld","pau-armcircle-f","pau-armcircle-b","pau-armupdown","pau-armpulses","pau-armfly","pau-armsback","pau-armraise","pau-armcurl","lowhop","squatjump","skaterhop","linehop","fastfeet","shuffle","highknees","lungeknee","stepcross","skaterreach","dbmarch","ankledrive","front-seated-reference","squat-control18","goblet-control18","rdl-control18","press-control18","neutral-control18","benchpress-control18","overheadpress-control18","incline-control18","kneepush-control18","curl-control18","hammer-control18","row-control18","bentrow-control18","bridge-control18","calf-control18","split-control18","standingabduct-control18","legextend-control18","bird-control18","deadbug-control18","pushup-control19","decline19","pike19","archer19","squat-15-19","goblet-15-19","split-15-19","row-15-19","overhead-15-19","single-squat19","singlehinge-free19","single-rdl19","full-sideplank19","sideplank-lift19","plankreach19","singlebridge-pause19","singlecalf-pause19","lateral-pause19","curl-pause19","w-t19","y-t-w19","shuffle-touch19","skater-stick19","home-diamond23","home-diamond23-tempo23","home-diamond23-pause23","home-staggered23","home-staggered23-tempo23","home-staggered23-pause23","home-spiderman23","home-spiderman23-tempo23","home-spiderman23-pause23","home-handrelease23","home-handrelease23-tempo23","home-handrelease23-pause23","home-hindu23","home-hindu23-tempo23","home-hindu23-pause23","home-push-tap23","home-push-tap23-tempo23","home-push-tap23-pause23","home-push-power23","home-push-power23-tempo23","home-push-power23-pause23","home-pike-bench23","home-pike-bench23-tempo23","home-pike-bench23-pause23","home-push-one23","home-push-one23-tempo23","home-push-one23-pause23","home-cossack23","home-cossack23-tempo23","home-cossack23-pause23","home-skater-squat23","home-skater-squat23-tempo23","home-skater-squat23-pause23","home-shrimp23","home-shrimp23-tempo23","home-shrimp23-pause23","home-pistol-box23","home-pistol-box23-tempo23","home-pistol-box23-pause23","home-pistol-db23","home-pistol-db23-tempo23","home-pistol-db23-pause23","home-step-lateral23","home-step-lateral23-tempo23","home-step-lateral23-pause23","home-step-cross23","home-step-cross23-tempo23","home-step-cross23-pause23","home-curtsy23","home-curtsy23-tempo23","home-curtsy23-pause23","home-walking-lunge23","home-walking-lunge23-tempo23","home-walking-lunge23-pause23","home-squat-lunge23","home-squat-lunge23-tempo23","home-squat-lunge23-pause23","home-lunge-front23","home-lunge-front23-tempo23","home-lunge-front23-pause23","home-ham-slide23","home-ham-slide23-tempo23","home-ham-slide23-pause23","home-ham-slide-one23","home-ham-slide-one23-tempo23","home-ham-slide-one23-pause23","home-bridge-walk23","home-bridge-walk23-tempo23","home-bridge-walk23-pause23","home-bridge-long23","home-bridge-long23-tempo23","home-bridge-long23-pause23","home-frog23","home-frog23-tempo23","home-frog23-pause23","home-airplane23","home-airplane23-tempo23","home-airplane23-pause23","home-reverse-plank-lift23","home-reverse-plank-lift23-tempo23","home-reverse-plank-lift23-pause23","home-reverse-knee23","home-reverse-knee23-tempo23","home-reverse-knee23-pause23","home-hollow-rock23","home-hollow-rock23-tempo23","home-hollow-rock23-pause23","home-hollow-tuck23","home-hollow-tuck23-tempo23","home-hollow-tuck23-pause23","home-vup23","home-vup23-tempo23","home-vup23-pause23","home-legraise23","home-legraise23-tempo23","home-legraise23-pause23","home-flutter23","home-flutter23-tempo23","home-flutter23-pause23","home-scissors23","home-scissors23-tempo23","home-scissors23-pause23","home-knee-tuck23","home-knee-tuck23-tempo23","home-knee-tuck23-pause23","home-side-thread23","home-side-thread23-tempo23","home-side-thread23-pause23","home-side-knee23","home-side-knee23-tempo23","home-side-knee23-pause23","home-body-saw23","home-body-saw23-tempo23","home-body-saw23-pause23","home-plank-toe23","home-plank-toe23-tempo23","home-plank-toe23-pause23","home-bear-tap23","home-bear-tap23-tempo23","home-bear-tap23-pause23","home-bear-return23","home-bear-return23-tempo23","home-bear-return23-pause23","home-crab23","home-crab23-tempo23","home-crab23-pause23","home-thruster-db23","home-thruster-db23-tempo23","home-thruster-db23-pause23","home-clean-db23","home-clean-db23-tempo23","home-clean-db23-pause23","home-rack-lunge23","home-rack-lunge23-tempo23","home-rack-lunge23-pause23","home-renegade23","home-renegade23-tempo23","home-renegade23-pause23","home-renegade-knee23","home-renegade-knee23-tempo23","home-renegade-knee23-pause23","home-zottman23","home-zottman23-tempo23","home-zottman23-pause23","home-hammer-cross23","home-hammer-cross23-tempo23","home-hammer-cross23-pause23","home-floor-fly23","home-floor-fly23-tempo23","home-floor-fly23-pause23","gym-bench-bb23","gym-bench-bb23-tempo23","gym-bench-bb23-pause23","gym-close-bb23","gym-close-bb23-tempo23","gym-close-bb23-pause23","gym-incline-bb23","gym-incline-bb23-tempo23","gym-incline-bb23-pause23","gym-decline-bb23","gym-decline-bb23-tempo23","gym-decline-bb23-pause23","gym-press-seated-bb23","gym-press-seated-bb23-tempo23","gym-press-seated-bb23-pause23","gym-press-bb23","gym-press-bb23-tempo23","gym-press-bb23-pause23","gym-front-bb23","gym-front-bb23-tempo23","gym-front-bb23-pause23","gym-back-bb23","gym-back-bb23-tempo23","gym-back-bb23-pause23","gym-box-bb23","gym-box-bb23-tempo23","gym-box-bb23-pause23","gym-sumo-bb23","gym-sumo-bb23-tempo23","gym-sumo-bb23-pause23","gym-dead-bb23","gym-dead-bb23-tempo23","gym-dead-bb23-pause23","gym-rdl-bb23","gym-rdl-bb23-tempo23","gym-rdl-bb23-pause23","gym-thrust-bb23","gym-thrust-bb23-tempo23","gym-thrust-bb23-pause23","gym-row-bb23","gym-row-bb23-tempo23","gym-row-bb23-pause23","gym-row-under-bb23","gym-row-under-bb23-tempo23","gym-row-under-bb23-pause23","gym-pendlay23","gym-pendlay23-tempo23","gym-pendlay23-pause23","gym-landmine-press23","gym-landmine-press23-tempo23","gym-landmine-press23-pause23","gym-landmine-row23","gym-landmine-row23-tempo23","gym-landmine-row23-pause23","gym-curl-bb23","gym-curl-bb23-tempo23","gym-curl-bb23-pause23","gym-preacher-bb23","gym-preacher-bb23-tempo23","gym-preacher-bb23-pause23","gym-lat-wide23","gym-lat-wide23-tempo23","gym-lat-wide23-pause23","gym-lat-neutral23","gym-lat-neutral23-tempo23","gym-lat-neutral23-pause23","gym-lat-under23","gym-lat-under23-tempo23","gym-lat-under23-pause23","gym-straight-pull23","gym-straight-pull23-tempo23","gym-straight-pull23-pause23","gym-cable-row23","gym-cable-row23-tempo23","gym-cable-row23-pause23","gym-cable-high-row23","gym-cable-high-row23-tempo23","gym-cable-high-row23-pause23","gym-cable-one-row23","gym-cable-one-row23-tempo23","gym-cable-one-row23-pause23","gym-face-pull23","gym-face-pull23-tempo23","gym-face-pull23-pause23","gym-cable-fly23","gym-cable-fly23-tempo23","gym-cable-fly23-pause23","gym-cable-low-fly23","gym-cable-low-fly23-tempo23","gym-cable-low-fly23-pause23","gym-cable-high-fly23","gym-cable-high-fly23-tempo23","gym-cable-high-fly23-pause23","gym-rope-tricep23","gym-rope-tricep23-tempo23","gym-rope-tricep23-pause23","gym-cable-one-tricep23","gym-cable-one-tricep23-tempo23","gym-cable-one-tricep23-pause23","gym-cable-overhead23","gym-cable-overhead23-tempo23","gym-cable-overhead23-pause23","gym-cable-curl23","gym-cable-curl23-tempo23","gym-cable-curl23-pause23","gym-cable-lateral23","gym-cable-lateral23-tempo23","gym-cable-lateral23-pause23","gym-wood-chop23","gym-wood-chop23-tempo23","gym-wood-chop23-pause23","gym-pallof23","gym-pallof23-tempo23","gym-pallof23-pause23","gym-leg-press23","gym-leg-press23-tempo23","gym-leg-press23-pause23","gym-hack23","gym-hack23-tempo23","gym-hack23-pause23","gym-smith-squat23","gym-smith-squat23-tempo23","gym-smith-squat23-pause23","gym-smith-calf23","gym-smith-calf23-tempo23","gym-smith-calf23-pause23","gym-chest-machine23","gym-chest-machine23-tempo23","gym-chest-machine23-pause23","gym-pec-deck23","gym-pec-deck23-tempo23","gym-pec-deck23-pause23","gym-leg-extension23","gym-leg-extension23-tempo23","gym-leg-extension23-pause23","gym-ham-seated23","gym-ham-seated23-tempo23","gym-ham-seated23-pause23","gym-ham-lying23","gym-ham-lying23-tempo23","gym-ham-lying23-pause23","gym-hip-machine23","gym-hip-machine23-tempo23","gym-hip-machine23-pause23","gym-treadmill23","gym-treadmill23-tempo23","gym-treadmill23-pause23","gym-row-erg23","gym-row-erg23-tempo23","gym-row-erg23-pause23","gym-press4530","gym-press4530-ecc30","gym-press4530-hold30","gym-presssingle30","gym-presssingle30-ecc30","gym-presssingle30-hold30","gym-pendulum30","gym-pendulum30-ecc30","gym-pendulum30-hold30","gym-belt30","gym-belt30-ecc30","gym-belt30-hold30","gym-vsquat30","gym-vsquat30-ecc30","gym-vsquat30-hold30","gym-smithsplit30","gym-smithsplit30-ecc30","gym-smithsplit30-hold30","gym-smiththrust30","gym-smiththrust30-ecc30","gym-smiththrust30-hold30","gym-smithrdl30","gym-smithrdl30-ecc30","gym-smithrdl30-hold30","gym-hamstanding30","gym-hamstanding30-ecc30","gym-hamstanding30-hold30","gym-tibialis30","gym-tibialis30-ecc30","gym-tibialis30-hold30","gym-calfseat30","gym-calfseat30-ecc30","gym-calfseat30-hold30","gym-calfdonkey30","gym-calfdonkey30-ecc30","gym-calfdonkey30-hold30","gym-adduct30","gym-adduct30-ecc30","gym-adduct30-hold30","gym-kickmachine30","gym-kickmachine30-ecc30","gym-kickmachine30-hold30","gym-thrustmachine30","gym-thrustmachine30-ecc30","gym-thrustmachine30-hold30","gym-abmachine30","gym-abmachine30-ecc30","gym-abmachine30-hold30","gym-backmachine30","gym-backmachine30-ecc30","gym-backmachine30-hold30","gym-rotary30","gym-rotary30-ecc30","gym-rotary30-hold30","gym-pullassist30","gym-pullassist30-ecc30","gym-pullassist30-hold30","gym-dipassist30","gym-dipassist30-ecc30","gym-dipassist30-hold30","gym-highrow30","gym-highrow30-ecc30","gym-highrow30-hold30","gym-supportrow30","gym-supportrow30-ecc30","gym-supportrow30-hold30","gym-pressone30","gym-pressone30-ecc30","gym-pressone30-hold30","gym-pressincline30","gym-pressincline30-ecc30","gym-pressincline30-hold30","gym-shoulderm30","gym-shoulderm30-ecc30","gym-shoulderm30-hold30","gym-lateralm30","gym-lateralm30-ecc30","gym-lateralm30-hold30","gym-reversedeck30","gym-reversedeck30-ecc30","gym-reversedeck30-hold30","gym-preacherm30","gym-preacherm30-ecc30","gym-preacherm30-hold30","gym-tricepsm30","gym-tricepsm30-ecc30","gym-tricepsm30-hold30","gym-pulloverm30","gym-pulloverm30-ecc30","gym-pulloverm30-hold30","gym-hipflexc30","gym-hipflexc30-ecc30","gym-hipflexc30-hold30","gym-abductc30","gym-abductc30-ecc30","gym-abductc30-hold30","gym-adductc30","gym-adductc30-ecc30","gym-adductc30-hold30","gym-kickc30","gym-kickc30-ecc30","gym-kickc30-hold30","gym-crunchc30","gym-crunchc30-ecc30","gym-crunchc30-hold30","gym-pullthrough30","gym-pullthrough30-ecc30","gym-pullthrough30-hold30","gym-stepmill30","gym-stepmill30-ecc30","gym-stepmill30-hold30","gym-bike30","gym-bike30-ecc30","gym-bike30-hold30","gym-elliptical30","gym-elliptical30-ecc30","gym-elliptical30-hold30","gym-skierg30","gym-skierg30-ecc30","gym-skierg30-hold30","gym-airbike30","gym-airbike30-ecc30","gym-airbike30-hold30","gym-recumbent30","gym-recumbent30-ecc30","gym-recumbent30-hold30","home-squatnarrow30","home-squatnarrow30-ecc30","home-squatnarrow30-hold30","home-squatheels30","home-squatheels30-ecc30","home-squatheels30-hold30","home-squatpulse30","home-squatpulse30-ecc30","home-squatpulse30-hold30","home-cyclistdb30","home-cyclistdb30-ecc30","home-cyclistdb30-hold30","home-lungefront30","home-lungefront30-ecc30","home-lungefront30-hold30","home-lungeoffset30","home-lungeoffset30-ecc30","home-lungeoffset30-hold30","home-splithigh30","home-splithigh30-ecc30","home-splithigh30-hold30","home-rdlstagger30","home-rdlstagger30-ecc30","home-rdlstagger30-hold30","home-rdlsuit30","home-rdlsuit30-ecc30","home-rdlsuit30-hold30","home-bridgemarch30","home-bridgemarch30-ecc30","home-bridgemarch30-hold30","home-bridgesqueeze30","home-bridgesqueeze30-ecc30","home-bridgesqueeze30-hold30","home-thrustsingle30","home-thrustsingle30-ecc30","home-thrustsingle30-hold30","home-plankjack30","home-plankjack30-ecc30","home-plankjack30-hold30","home-plankstep30","home-plankstep30-ecc30","home-plankstep30-hold30","home-plankdrag30","home-plankdrag30-ecc30","home-plankdrag30-hold30","home-sideabduct30","home-sideabduct30-ecc30","home-sideabduct30-hold30","home-sidehip30","home-sidehip30-ecc30","home-sidehip30-hold30","home-bearreach30","home-bearreach30-ecc30","home-bearreach30-hold30","home-deadbugpress30","home-deadbugpress30-ecc30","home-deadbugpress30-hold30","home-reversebird30","home-reversebird30-ecc30","home-reversebird30-hold30","home-scappush30","home-scappush30-ecc30","home-scappush30-hold30","home-pushwide30","home-pushwide30-ecc30","home-pushwide30-hold30","home-pushcloseknee30","home-pushcloseknee30-ecc30","home-pushcloseknee30-hold30","home-pikepause30","home-pikepause30-ecc30","home-pikepause30-hold30","home-lateralstepover30","home-lateralstepover30-ecc30","home-lateralstepover30-hold30","home-hopforward30","home-hopforward30-ecc30","home-hopforward30-hold30","home-hip909030","home-hip909030-ecc30","home-hip909030-hold30","home-wallangel30","home-wallangel30-ecc30","home-wallangel30-hold30"]);
const TYPES={train:'strength',cardio:'cardio',mobility:'recovery',rest:'rest'};
const CATEGORY={strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'};
const SPANISH={strength:'Fuerza',cardio:'Cardio',recovery:'Movilidad'};
const LOWER=new Set(['Piernas','Cadera']),UPPER=new Set(['Pecho','Espalda','Hombros','Brazos']);
const byId=new Map(E.exercises.map(e=>[e.id,e]));
const classicById=E.byId;
E.byId=id=>byId.get(id)||classicById(id);
const available=E.exercises.filter(e=>VERIFIED_IDS.has(e.id)&&e.modelPath30&&e.demoPath30&&
  ['Fuerza','Cardio','Movilidad'].includes(e.category)&&e.group&&Array.isArray(e.zones)&&
  typeof e.level==='number'&&Array.isArray(e.requirements));
const fieldNames=['age','goal','goalGroup','finalGoal','finalTarget','focus','difficulty','experience','equipment',
 'gymEquipment23','bench','step','minutes','frequency','dailyMissions','routineExercises3878','trainingMode','trainingScale',
 'weekPlan','weekFocus','injuries','restrictions','health','limits','preferredTrainer','load','restSeconds','materials46','capabilities46','dynamicRequestedFocus3847'];
const asJSON=o=>JSON.stringify(o??null);
const hash=s=>{let h=2166136261;for(let i=0;i<s.length;i++){h^=s.charCodeAt(i);h=Math.imul(h,16777619);}return h>>>0;};
const profileKey=p=>hash(asJSON(fieldNames.map(k=>[k,p?.[k]]))).toString(36);
const mod=(a,n)=>n?((a%n)+n)%n:0;
const injury=p=>!!(p?.injuries||[]).some(x=>x.stage!=='past')||!!p?.limits||!!p?.health?.special||['mild','moderate','strong'].includes(p?.health?.pain);
function gate(p){
 if(!p)return {ok:false,reason:'Falta completar el perfil.'};
 if(injury(p)&&root.HunterPlanner379?.status){const info=root.HunterPlanner379.status(p);return {...info,ok:!!info.ok,reduced:info.reduced!==false};}
 const m=A.mode(p);return {ok:!!m.auto,reduced:!!m.reduced,reason:m.reason||''};
}
function activeLog(l){return !!(l&&(W.active(l)||l.complete||l.manual||l.pain));}
function locked(day,logs){const l=logs?.[day.date];return !!(day.customized||day.routineId||day.creatorAssigned||day.manual||activeLog(l)||day.tasks?.some(t=>t.done));}
function recent(logs,date,days=14){return Object.entries(logs||{}).filter(([day,l])=>day<date&&day>=E.plus(date,-days)&&l);}
function feedback(logs,date){
 const rows=recent(logs,date,7),done=rows.map(([,l])=>l).filter(l=>l.complete&&!l.pain&&!l.manual);
 const efforts=done.map(l=>+l.rpe).filter(x=>x>=1&&x<=10),avg=efforts.length?efforts.reduce((a,b)=>a+b,0)/efforts.length:0;
 const strain=rows.some(([,l])=>l.pain||l.fatigue||l.rpe>=8)||
  !!root.HunterConstraints3811?.symptomTrend?.(logs,date)?.recentSymptom;
 const adherence=rows.length?done.length/rows.length:0;
 return {strain,avg,adherence,completed:done.length,canProgress:done.length>=2&&adherence>=.8&&avg>0&&avg<=7&&!strain};
}
function compatible(e,p,risk){
 if(!e||!byId.has(e.id)||!root.HunterGroups49.eligible(e,p))return false;
 // Comprobar equipo y nivel incluso cuando el perfil registra una lesión.
 if(!root.HunterMaterials3814?.eligible?.(e,p))return false;
 if(risk){return !!(root.HunterConstraints3811?.allowed?.(e,p) ?? root.HunterPlanner379?.approved?.(e,p));}
 try{if(!A.allowed(e,p))return false;}catch(_){return false;}
 if(e.equipment==='db'&&!['db','gym'].includes(p.equipment))return false;
 if(e.gymOnly23&&p.equipment!=='gym')return false;
 if(e.equipment==='gym'&&p.equipment!=='gym')return false;
 const difficulty=['basic','intermediate','advanced'].indexOf(p.difficulty);
 if(root.HunterGroups49.level(e)>Math.max(0,difficulty<0?(p.experience==='regular'?1:0):difficulty))return false;
 return true;
}
const poolMemo46=new Map();
function pool(p,kind){const key=kind+'|'+asJSON([...fieldNames,'physicalLimits','preferences','goalAutoRestrictions374'].map(k=>[k,p?.[k]]));if(poolMemo46.has(key))return poolMemo46.get(key).slice();const status=gate(p);if(!status.ok)return [];const result=available.filter(e=>root.HunterGroups49.inKind(e,kind)&&compatible(e,p,injury(p)));poolMemo46.set(key,result);if(poolMemo46.size>12)poolMemo46.delete(poolMemo46.keys().next().value);return result.slice();}

const goalGroups={upper:['Pecho','Espalda','Brazos','Hombros'],lower:['Piernas','Cadera'],core:['Core','Abdomen'],
 general:['Piernas','Pecho','Espalda','Cadera','Core','Abdomen','Hombros','Brazos']};
function sortPool(xs,p,seed,kind,previous,used,counts,weekLoad,patternCounts,tracker,date,selected=[]){
 const focus=p.focus||'general',order=goalGroups[focus]||goalGroups.general;
 return xs.map(e=>{
  const was=previous.has(e.id)?45:0,distinct=counts[e.group]||0;
  let priority=order.includes(e.group)?26-order.indexOf(e.group)*2:0;
  if(kind==='recovery')priority=10;
  if(kind==='cardio'&&p.goal==='endurance')priority+=12;
  if(p.goal==='muscle'&&UPPER.has(e.group))priority+=8;
  if(p.goal==='strength'&&LOWER.has(e.group))priority+=8;
  const hasGear=e.equipment==='body'?2:0;
  const weeklyScore=root.HunterTraining387?.score?.(e,{weekly:weekLoad,dayCounts:counts,dayPatterns:patternCounts})||0;
  const upcomingScore=tracker?.score?.(e,date)||0;
   const supportScore=root.HunterConstraints3811?.rank?.(e,p)||0;
   const metadataScore=root.HunterMetadata3812?.score?.(e,p,kind)||0;
   const rank=root.HunterGroups49.score(e,p,kind,selected)+priority+(p.dynamicGymPreferred3827&&(e.gymOnly23||e.equipment==='gym')?45:0)+hasGear-was-distinct*24-(used.has(root.HunterGroups49.family(e))?100:0)-e.level*2+weeklyScore+upcomingScore+supportScore+metadataScore;
  return {e,rank,lottery:hash(seed+'|'+e.id)};
 }).sort((a,b)=>b.rank-a.rank||a.lottery-b.lottery||a.e.id.localeCompare(b.e.id));
}
function historyIds(logs,date){const prior=new Set();for(const [,l]of recent(logs,date,8)){
  if(!l.complete)continue;for(const x of l.items||[])prior.add(x.id);
 }return prior;}
function dose(e,p,kind,status,history,date){
 const gentle=!!status.reduced,low=history.strain,minutes=Math.max(10,Math.min(60,+p.minutes||20));
 let sets=kind==='recovery'||gentle||low?1:minutes>=40?3:2;
 let target=e.unit==='sec'?(kind==='cardio'?30:20):(kind==='recovery'?8:p.goal==='muscle'?10:8);
 if(gentle||low){target=e.unit==='sec'?15:6;}
 // Sólo registros favorables permiten progresión; se preservan las series previamente completadas.
 if(!gentle&&!low&&history.canProgress)target=Math.min(e.unit==='sec'?90:15,target+(e.unit==='sec'?5:1));
 return {id:e.id,sets,target,rest:kind==='cardio'?45:kind==='recovery'?30:90,
  dynamic381:true,originDate381:date,preview2D381:'demos30/'+e.id+'.svg',
  noAutoLoad:gentle||low,safetyNote:gentle?'Dosis inicial conservadora; interrumpe si hay dolor nuevo o empeoramiento.':'Respeta técnica y recuperaciones.'};
}
function choose(p,kind,seed,logs={},date=E.dateKey(),usedBases=new Set(),tracker=null,weekIndex=0){
 const status=gate(p);if(!status.ok)return {items:[],blocked:status.reason,requested:kind};
 if(kind==='rest')return {items:[],effective:'rest',requested:'rest'};
 // Mantener el tipo elegido en el calendario. Ante catálogo incompatible, pedir revisión en vez de cambiar fuerza por cardio/movilidad.
 const options=[kind];
 let list=[],effective=kind;
 // Días de fuerza: la zona elegida en Perfil tiene prioridad sobre el foco general.
 const weekday=S.weekday(date),dailyType=p?.weekPlan?.[weekday];
 const explicitFocus=Object.prototype.hasOwnProperty.call(goalGroups,p?.dynamicRequestedFocus3847)?p.dynamicRequestedFocus3847:null;
 const dailyFocus=kind==='strength'?(explicitFocus||(dailyType==='train'?(['lower','core'].includes(p?.weekFocus?.[weekday])?p.weekFocus[weekday]:'upper'):null)):null;
 const allowedStrengthGroups=dailyFocus&&dailyFocus!=='general'?new Set(goalGroups[dailyFocus]):null;
 for(const type of options){
  list=pool(p,type);
  if(type==='strength'&&allowedStrengthGroups)list=list.filter(e=>root.HunterGroups49.matches(e,dailyFocus));
  if(list.length){effective=type;break;}
 }
 if(!list.length)return {items:[],blocked:'No se encontraron ejercicios demostrados compatibles con las restricciones declaradas.',requested:kind};
 const tierCategory=effective==='cardio'?'cardio':effective==='recovery'?'mobility':
   ['upper','lower','core'].includes(dailyFocus)?dailyFocus:
   ['upper','lower','core'].includes(p.focus)?p.focus:'upper';
 const levelContext=root.HunterLevel3851?.context(p,logs,date,tierCategory,5)||null;
 // Permitir UNA variante de prueba del siguiente nivel sólo con registros
 // suficientes; no cambiar p.difficulty ni habilitar saltos/potencia.
 if(levelContext?.preview){
   let options=pool({...p,difficulty:'advanced'},effective);
   if(effective==='strength'&&allowedStrengthGroups)options=options.filter(e=>root.HunterGroups49.matches(e,dailyFocus));
   const merged=new Map([...list,...options].map(e=>[e.id,e]));list=[...merged.values()];
 }
 const cap=root.HunterMaterials3814?.sessionCap?.(p,kind,status)??Math.max(2,Math.min(7,Number(p.dailyMissions)||3));
 const baseCount=effective==='recovery'?Math.min(3,cap):cap;
  const count=root.HunterIntegration3869?.activityLimit?.(baseCount)??baseCount;
 const hist=historyIds(logs,date),rawFeedback=feedback(logs,date),fb=root.HunterCategoryProgress51?.feedback(rawFeedback,logs,date)||rawFeedback,selected=[],used=new Set();const counts={},patternCounts={};
  const weeklyLoad=root.HunterTraining387?.load?.(logs,date)||{groups:{},patterns:{},ids:{}};
 const selectionContext=root.HunterGroups49.context(p,effective,dailyFocus||p.focus||'general',{logs,date,tracker,status,feedback:fb});
 for(let i=0;i<count;i++){
  const candidates=list.filter(e=>!used.has(root.HunterGroups49.family(e))&&!usedBases.has(root.HunterGroups49.family(e)));
  let choices=candidates.length?candidates:list.filter(e=>!used.has(root.HunterGroups49.family(e)));
  choices=root.HunterIntegration3869?.prefers?.(choices)??choices;
   choices=root.HunterGroups49.selectionPool(choices,selected,count,selectionContext);
  choices=root.HunterLevel3851?.levelPool(choices,selected,levelContext,count)||choices;
  if(!choices.length)break;
  const next=sortPool(choices,p,seed+'-'+i,effective,hist,used,counts,weeklyLoad,patternCounts,tracker,date,selected)[0].e;
  selected.push(next);used.add(root.HunterGroups49.family(next));counts[next.group]=(counts[next.group]||0)+1;
   const pat=root.HunterTraining387?.pattern?.(next);if(pat)patternCounts[pat]=(patternCounts[pat]||0)+1;
 }
 const category51=root.HunterCategoryProgress51?.categoryOfItems(selected,effective,dailyFocus)||null;
 const items=selected.map(e=>{const basic=dose(e,p,effective,status,fb,date);
   const adjusted=root.HunterTraining387?.dose?.(e,p,effective,status,fb,date,basic,weeklyLoad)||basic;
   const classified=root.HunterMetadata3812?.dose?.(e,p,effective,status,fb,date,adjusted)||adjusted;
   const legacy=root.HunterProgress3815?.dose?.(e,p,effective,status,fb,date,classified,weekIndex,logs)||classified;
   const finalDose=root.HunterUnified3827?.dose?.(e,p,effective,status,fb,date,legacy,weekIndex,logs,basic)||legacy;const roleDose=root.HunterGroups49.adjustDose(e,p,effective,finalDose,{status,feedback:fb,logs,date});return root.HunterCatalog46?.cap(e,p,roleDose,{status,feedback:fb,logs,date})||roleDose;});
 const calculated=root.HunterCategoryProgress51?.adjustItems(items,{profile:p,logs,date,category:category51,status,kind:effective});
 const resultItems=(calculated?.items||items).map(item=>root.HunterLevel3851?.dose(item,E.byId(item.id),levelContext)||item);
 for(const item of resultItems){const e=byId.get(item.id);if(e)tracker?.add?.(date,e,item.sets);}
 return {items:resultItems,effective,requested:kind,substituted:effective!==kind,count:resultItems.length,progression51:calculated?.progress||null,level3851:levelContext?{profileTier:levelContext.profileTier,ceiling:levelContext.ceiling,preview:levelContext.preview,reason:levelContext.failure.why,quota:root.HunterLevel3851.idealQuota(levelContext,count)}:null};
}
function planKey(p,logs,today){const past=recent(logs,today,90).map(([d,l])=>[d,l.complete,l.pain,l.fatigue,l.rpe,l.manual,l.kind,String(l.note||'').slice(0,500),(l.items||[]).map(x=>[x.id,(x.actual||[]).map(a=>!!a.done)])]);
 return VERSION+'|'+profileKey(p)+'|'+today+'|'+hash(asJSON(past)).toString(36);
}
function patch(c,p,logs={},today=E.dateKey()){
 if(!c?.days?.length||!p||!S.valid(p))return 0;
 // No reanudar un programa que el revisor semanal detuvo expresamente.
 if(c.reviews?.at(-1)?.action==='pausar')return 0;
 const key=planKey(p,logs,today);if(c.dynamicKey381===key&&c.days.every(d=>d.date<today||d.scheduleChoice==='rest'||locked(d,logs)||d.dynamic381||d.blocked381))return 0;
 let changes=0;let groupUsed=new Set(),weekSegment=-1;const risk=injury(p);
 const tracker=root.HunterPlan3810?.tracker?.(c,logs,today)||null;
 for(const [index,d]of c.days.entries()){
  const segment=root.HunterSchedule3813?.weekSegment?.(index) ?? Math.floor(index/7);
  if(segment!==weekSegment){weekSegment=segment;groupUsed=new Set();}
  if(d.date<today||locked(d,logs)||root.HunterStable3828?.keep?.(d,p,logs,today))continue;
  const selected=p.weekPlan[S.weekday(d.date)]||'rest',kind=TYPES[selected];
  if(selected==='rest'){
   if(d.kind!=='rest'||d.items?.length||d.dynamic381){d.kind='rest';d.items=[];d.title='Descanso elegido';d.tasks=[];d.scheduleChoice='rest';d.dynamic381=true;delete d.blocked378;delete d.blocked381;changes++;}
   continue;
  }
  if(d.dynamicKey381===key&&d.dynamic381&&d.items?.length)continue;
  const res=choose(p,kind,profileKey(p)+'|'+d.date+'|'+c.index,logs,d.date,groupUsed,tracker,segment);
  d.kind=kind;d.scheduleChoice=selected;d.items=res.items;d.tasks=[];d.dynamic381=true;d.dynamicKey381=key;d.progression51=res.progression51||null;
  const shownKind=kind==='strength'?({lower:'Tren inferior',core:'Tren central'}[p?.weekFocus?.[S.weekday(d.date)]]||'Tren superior'):SPANISH[kind];
  d.title=shownKind+(res.substituted?' · alternativa de '+SPANISH[res.effective]:' · plan dinámico');
  if(res.blocked){d.title=SPANISH[kind]+' · requiere revisión';d.blocked381=res.blocked;d.blocked378=res.blocked;
   d.tasks=[{id:'review-dynamic381',text:'Revisar restricciones para encontrar ejercicios compatibles',done:false}];
   d.planningNote='Se conserva el día elegido y el historial; no hay selección automática segura.';
  }else{delete d.blocked381;delete d.blocked378;
   d.planningNote=(res.substituted?'No había opciones verificadas del tipo elegido; se muestran '+SPANISH[res.effective]+'. ':'')+
   'Generado bajo demanda desde demostraciones 2D existentes, sin rutinas combinatorias precargadas.'+
   (res.progression51?.lastScore?(' Progresión '+res.progression51.label+': esfuerzo '+res.progression51.lastScore+'/10, '+
    ({repeticiones_o_tiempo:'pequeño ajuste de repeticiciones/tiempo',una_serie:'una serie adicional controlada',reduccion_suave:'reducción moderada',mantener:'mantener dosis'}[res.progression51.action]||'mantener dosis')+'.'):'');
   if(risk){d.adapted378=true;d.adaptation379='37.9';d.originalCategory378=SPANISH[kind];}
   for(const item of res.items){const ex=byId.get(item.id);if(ex)groupUsed.add(root.HunterGroups49.family(ex));}
  }
  delete d.autoTemplateId;
  changes++;
 }
 c.dynamicKey381=key;c.dynamicVersion381=VERSION;
 if(changes)c.decision='Generación dinámica de sesiones: calendario, objetivo, historial y restricciones. Se conservan sesiones registradas.';
 return changes;
}
const previousMake=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){const c=previousMake(p,start,index,last,logs);patch(c,p,logs,start);return c;};
const previousCurrent=currentCycle;
currentCycle=function(){const c=previousCurrent();if(!c||!state.profile)return c;
 const changes=patch(c,state.profile,state.logs||{},E.dateKey());
 const today=c.days.find(d=>d.date===E.dateKey());
 const updated=today&&root.HunterPlanner379?.repairEmptyLog?.(today);
 if((changes||updated)&&typeof save==='function'&&!root.Runtime20?.initializing)save();
 return c;};
// Rutinas de muestra ligeras, calculadas sólo cuando se consulta la biblioteca.
const originalAll=allRoutines;
function templates(p,logs={}){
 if(!p||!gate(p).ok)return [];
 const today=E.dateKey(),pastLogs=Object.fromEntries(Object.entries(logs||{}).filter(([d])=>d<today)),labels=[['strength','Fuerza'],['cardio','Cardio'],['recovery','Movilidad']];
 const result=[];for(const [kind,name]of labels)for(let n=0;n<3;n++){
  const q=choose(p,kind,profileKey(p)+'|'+today+'|template'+n,pastLogs,E.plus(today,n));
  if(!q.items.length)continue;
  const requirements=[...new Set(q.items.flatMap(x=>byId.get(x.id)?.requirements||[]))];
  result.push({id:'dynamic381-'+kind+'-'+n,name:name+' dinámica · '+(n+1),
   category:CATEGORY[q.effective],description:'Generada para este perfil a partir del catálogo 2D; se reevalúan restricciones antes de asignar.',
   items:q.items,requirements,level:0,goalGroups:['fitness','habit','movement','build','athletic'],
   extension:'standard',family:'dynamic',impact:q.items.some(x=>root.HunterGroups49.impact(byId.get(x.id)))?'Con saltos':'Sin saltos',minutes:Math.max(5,q.items.length*4),safe381:true});
 }return result;
}
allRoutines=function(){return [...originalAll(),...templates(state?.profile,state?.logs||{})];};
const oldCompatible=A.routineCompatible;
A.routineCompatible=function(r,p){return r?.safe381?gate(p).ok&&!!r.items?.length&&r.items.every(x=>compatible(byId.get(x.id),p,injury(p))):oldCompatible(r,p);};
const previousRoutines=routines;
routines=function(){const html=previousRoutines();const count=available.length;
 return '<div class="notice" role="note"><b>Generador dinámico V38.1</b><p>'+count+' demostraciones 2D con metadatos disponibles para selección. Las rutinas de muestra se crean al abrir esta sección; las combinaciones no se almacenan. Los filtros del perfil se aplican antes de asignar.</p></div>'+html;
};
root.HunterDynamic381={version:VERSION,catalogCount:available.length,verifiedAssetCount:VERIFIED_IDS.size,
 pool,choose,patch,feedback,templates,gate,compatible,locked,profileKey,integrity:()=>({verified:VERIFIED_IDS.size,usable:available.length,
  missing:[...VERIFIED_IDS].filter(id=>!byId.has(id)).length})};
})(window);

;
/* Hunter System 38.52 — Misiones Monarca: perfil y equipamiento coherentes.
 * Cinco metas registradas durante un ciclo voluntario de cinco días.
 * No elimina controles existentes para menores ni sustituye una evaluación profesional.
 * No penaliza físicamente la cancelación o el incumplimiento.
 */
(function(root){
 'use strict';
 if(root.HunterMonarch3852)return;
 const E=root.Engine,G=root.HunterGroups49,L=root.HunterLevel3851;
 const A=root.Adapt,M=root.HunterMaterials3814;
 if(!E||!G||!L||!A||!M)throw Error('Monarch3852: dependencias ausentes');
 const categories=['upper','upper','lower','core','cardio'];
 const display={upper:'Tren superior',lower:'Tren inferior',core:'Zona central',cardio:'Cardio'};
 const tiers=['Básico','Intermedio','Avanzado'];
 const xss=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const now=()=>Date.now();
 const DAY=86400000;
 const CYCLE_DAYS=5;
 const getStore=()=>{
  if(!state.monarch3852||!Array.isArray(state.monarch3852.history)){
   state.monarch3852={active:null,history:[],xp:0,judgementUntil:0};
  }
  return state.monarch3852;
 };
 const signedDate=t=>E.dateKey(new Date(t));
 const dateWithin=(date,start,end)=>date>=signedDate(start)&&date<=signedDate(end-1);
 function screening(p){
  const mode=A.mode(p),gate=root.HunterDynamic381?.gate?.(p);
  if(!mode.auto||gate?.ok===false)return {ok:false,reason:mode.reason||gate.reason||'Completa y revisa tu perfil.'};
  if(p?.health?.pain&&p.health.pain!=='none'||p?.limits||p?.health?.acute||p?.health?.redFlags){
   return {ok:false,reason:'Revisa las restricciones de tu perfil antes de activar un desafío.'};
  }
  return {ok:true,reason:''};
 }
 function eligible(e,p,logs,date,cat){
  const m=G.classify(e),tier=L.tier(e);
  if(!m||m.group!==cat||tier<0||tier>2||m.power||m.impact||e.highSkill46||e.doseCap46?.highSkill)return false;
  if(cat==='cardio'?m.role!=='conditioning':m.role!=='primary')return false;
  if(M.eligible(e,p)===false||G.eligible(e,p)===false||A.allowed(e,p)===false)return false;
  const context=L.context(p,logs,date,cat,5);
  if(tier>context.maxTier)return false;
  if(context.failure?.failedFamilies?.includes(G.family(e)))return false;
  return true;
 }
 function rank(p){return Math.max(0,['basic','intermediate','advanced'].indexOf(p?.difficulty));}
 function preferred(p,logs,date){
  const r=rank(p);
  if(r===0)return [0,0,0,0,0];
  if(r===2)return [1,2,2,1,0];
  // Una variante avanzada de prueba sólo con historial favorable y permiso del motor.
  const ctx=L.context(p,logs,date,'upper',5);
  return ctx.preview?[0,2,0,1,0]:[0,1,0,1,0];
 }
 // Cada meta es un total acumulativo por ciclo, nunca una cuota diaria.
 // Sólo los movimientos repetitivos de baja complejidad tienen el rango 70-100.
 // Las variantes técnicas, los ejercicios temporizados y los perfiles sin base
 // suficiente mantienen dosis menores; no se incrementan por incumplimiento.
 function favorablePreviousCycle(logs,cat){
   const previous=getStore().history[0];
   if(!previous||previous.status!=='completed')return null;
   const CP=root.HunterCategoryProgress51;
   const feedback=Object.entries(logs||{}).filter(([day,log])=>
     dateWithin(day,previous.startedAt,previous.expiresAt)&&
     log&&(CP?.categoryOfLog?.(log)===cat));
   if(!feedback.length)return null;
   if(feedback.some(([,log])=>log.pain===true||log.fatigue===true||
       !log.complete||!(Number(log.rpe)>=1&&Number(log.rpe)<=4)))return null;
   return previous;
 }
 function loadTotal(e,p,logs,date){
   const group=G.classify(e).group,tier=L.tier(e),seconds=e.unit==='sec';
   const difficulty=rank(p);
   const progress=root.HunterCategoryProgress51?.resolve?.(logs,date,group);
   if(seconds){
     // Objetivos de duración con su propia unidad; nunca etiquetarlos como repeticiones.
     const base=[30,40,50][difficulty];
     return Math.max(15,Math.min(90,base+(progress?.level>0?5:0)));
   }
   const simple=tier<=1&&!e.highSkill46&&!e.doseCap46?.highSkill;
   const initial=simple?[70,75,80][difficulty]:[8,10,12][difficulty];
   const cap=simple?100:20;
   const previous=favorablePreviousCycle(logs,group);
   const last=previous?.objectives.find(o=>o.id===e.id) ||
     previous?.objectives.find(o=>o.category===group&&o.tier===tier&&o.unit==='reps');
   // Un incremento máximo de 2 repeticiones por nuevo ciclo con evidencia favorable.
   const next=last?Math.max(initial,Math.min(cap,Number(last.target)||initial)+2):initial;
   return Math.min(cap,next);
 }
 function generate(p,logs,date=E.dateKey()){
  const guard=screening(p);if(!guard.ok)return {ok:false,reason:guard.reason,objectives:[]};
  const choices=preferred(p,logs,date),takenIds=new Set(),takenFamilies=new Set(),objectives=[];
  for(let slot=0;slot<categories.length;slot++){
   const cat=categories[slot],desired=cat==='cardio'&&rank(p)>0?1:choices[slot];
   const pool=L.monarchCandidates(p,logs,date,cat).filter(e=>eligible(e,p,logs,date,cat)&&
     !takenIds.has(e.id)&&!takenFamilies.has(G.family(e)));
   // Priorizar el nivel previsto; ante la ausencia de equipo no elevar dificultad.
   const available=pool.filter(e=>L.tier(e)<=desired);
   available.sort((a,b)=>Math.abs(L.tier(a)-desired)-Math.abs(L.tier(b)-desired)||
     (a.unit==='sec'?1:0)-(b.unit==='sec'?1:0)||
     (G.classify(b).primary?1:0)-(G.classify(a).primary?1:0)||a.id.localeCompare(b.id));
   const exercise=available[0];
   if(!exercise)return {ok:false,reason:'No hay cinco ejercicios compatibles con el nivel y equipamiento. Ajusta el perfil o consulta alternativas.',objectives:[]};
   takenIds.add(exercise.id);takenFamilies.add(G.family(exercise));
   objectives.push({id:exercise.id,category:cat,tier:L.tier(exercise),unit:exercise.unit==='sec'?'sec':'reps',target:loadTotal(exercise,p,logs,date)});
  }
  return {ok:true,reason:'',objectives};
 }
 function totalOnDay(logs,id,date){
  const log=logs?.[date];
  if(!log||log.pain===true)return 0;
  let sum=0;
  for(const item of log.items||[]){
   if(item.id!==id)continue;
   for(const row of item.actual||[]){
    if(row.done===true&&Number.isFinite(Number(row.value))&&Number(row.value)>0)
     sum+=Number(row.value)*(E.sidesOf(item)?2:1);
   }
  }
  return sum;
 }
 function totalFor(logs,id,start,end){
  return Object.keys(logs||{}).filter(date=>dateWithin(date,start,end))
   .reduce((count,date)=>count+totalOnDay(logs,id,date),0);
 }
 function progress(m,logs){
  const start=signedDate(m.startedAt),end=signedDate(m.expiresAt-1);
  return m.objectives.map(o=>{
   let actual=0;
   for(let day=start;day<=end;day=E.plus(day,1)){
    const log=logs?.[day];
    const closingDay=day===end&&end!==start;
    const finishingTime=Date.parse(log?.completedAt||'');
    // En el último día parcial, sólo contabilizar rutinas con hora verificada
    // dentro de la ventana de 96 horas. La entrada manual se registra antes del cierre.
    const inWindow=!closingDay||(Number.isFinite(finishingTime)&&finishingTime>=m.startedAt&&finishingTime<=m.expiresAt);
    const fromRoutine=inWindow?Math.max(0,totalOnDay(logs,o.id,day)-(day===start?(m.baselines[o.id]||0):0)):0;
    const fromMonarch=Math.max(0,Number(m.manual?.[day]?.[o.id])||0);
    // Una serie en el registro diario y Monarca sólo cuenta una vez.
    actual+=Math.max(fromRoutine,fromMonarch);
   }
   return {...o,done:Math.min(actual,o.target)};
  });
 }
 function register(exerciseId){
  review();const st=getStore(),m=st.active;
  if(!m||now()>=m.expiresAt||requiresReview(m)){
   toast('Esta misión no admite nuevos registros. Revisa el perfil o el plazo.');return;
  }
  const objective=m.objectives.find(o=>o.id===exerciseId);
  if(!objective)return;
  const field=document.getElementById('monarch-entry-'+exerciseId);
  const value=Number(field?.value);
  if(!Number.isInteger(value)||value<1||value>objective.target){
   toast('Registra una cantidad válida que hayas realizado.');return;
  }
  const date=E.dateKey();
  if(state.logs?.[date]?.pain===true){toast('Hay dolor registrado. No añadas más actividad.');return;}
  m.manual=m.manual||{};m.manual[date]=m.manual[date]||{};
  m.manual[date][exerciseId]=Math.min(objective.target,(m.manual[date][exerciseId]||0)+value);
  save();review();go('monarch');
 }
 function review(){
  const st=getStore(),m=st.active;
  if(!m)return;
  const p=progress(m,state.logs),done=p.every(x=>x.done>=x.target);
  if(done||now()>=m.expiresAt){
   const success=done&&now()<=m.expiresAt;
   m.status=success?'completed':'expired';m.final=p;m.finishedAt=now();
   if(success){m.reward=50+m.objectives.reduce((points,o)=>points+10+10*o.tier,0);st.xp+=m.reward;}
   else {
    m.reward=0;
    const symptoms=Object.entries(state.logs||{}).some(([day,log])=>
     dateWithin(day,m.startedAt,m.expiresAt)&&(log?.pain===true||log?.fatigue===true));
    if(!symptoms)st.judgementUntil=now()+7*DAY;
   }
   st.history.unshift(m);st.history=st.history.slice(0,24);st.active=null;
   save();
  }
 }
 function activate(){
  review();const st=getStore();if(st.active){toast('Ya existe una Misión Monarca activa.');return;}
  const previous=st.history[0];
  if(previous&&previous.expiresAt>now()){toast('El próximo ciclo estará disponible cuando finalicen los cinco días.');return;}
  const generated=generate(state.profile,state.logs);
  if(!generated.ok){toast(generated.reason);return;}
  const t=now(),end=t+CYCLE_DAYS*DAY;
  const baselines=Object.fromEntries(generated.objectives.map(o=>[o.id,totalFor(state.logs,o.id,t,end)]));
  st.active={version:1,status:'active',startedAt:t,expiresAt:end,objectives:generated.objectives,baselines,manual:{},
   profileSnapshot:{difficulty:state.profile.difficulty,equipment:state.profile.equipment},reward:0};
  if(save()){toast('Misión Monarca activada: metas acumulativas de cinco días.');go('monarch');}
 }
 function cancel(){
  const st=getStore();if(!st.active)return;
  st.active.status='cancelled';st.active.finishedAt=now();st.active.final=progress(st.active,state.logs);
  st.history.unshift(st.active);st.history=st.history.slice(0,24);st.active=null;
  save();go('monarch');toast('Misión cancelada. Sin aumento obligatorio de carga.');
 }
 function requiresReview(m){
  const p=state.profile;
  if(!screening(p).ok)return true;
  return m.objectives.some(o=>!eligible(E.byId(o.id),p,state.logs,E.dateKey(),o.category)||
   L.tier(E.byId(o.id))>rank(p));
 }
 const meter=(v,max)=>`<div class="bar" style="margin:7px 0 12px"><i style="width:${Math.max(0,Math.min(100,100*v/max))}%"></i></div>`;
 function tile(){
  const st=getStore(),m=st.active;
  const txt=m?`Misión activa · termina ${new Date(m.expiresAt).toLocaleDateString('es')}`:'Cinco objetivos adaptados al perfil · ciclo de cinco días';
  return `<div class="section-title"><h2>👑 Misiones Monarca</h2><span class="badge">5 días</span></div><div class="card"><h3>Prueba del Monarca</h3><p class="muted" style="margin:8px 0 14px">${xss(txt)}</p><button class="btn primary" onclick="go('monarch')">${m?'VER PROGRESO MONARCA':'ABRIR MISIONES MONARCA'} →</button></div>`;
 }
 function view(){
  review();const st=getStore(),m=st.active;
  const guard=screening(state.profile);
  const historical=st.history.slice(0,5).map(x=>`<div class="quest"><div class="grow"><h3>${xss(x.status==='completed'?'Completada':x.status==='cancelled'?'Cancelada':'Vencida')}</h3><small>${new Date(x.startedAt).toLocaleDateString('es')} · ${x.objectives.length} ejercicios</small></div><span class="tag">${Number(x.reward)||0} XP</span></div>`).join('');
  const judgement=st.judgementUntil>now()?`<div class="notice">Juicio del Monarca hasta ${new Date(st.judgementUntil).toLocaleDateString('es')}: estado narrativo, sin cambios en las cargas de las rutinas.</div>`:'';
  let main='';
  if(m){
   const rows=progress(m,state.logs);
   const caution=requiresReview(m)?'<div class="notice warn">El perfil cambió o requiere revisión. No continúes esta misión sin actualizar el plan; puedes cancelarla sin penalización física.</div>':'';
   main=`<div class="card"><div class="row between"><h3>Ciclo en curso</h3><span class="badge">${Math.max(0,Math.ceil((m.expiresAt-now())/DAY))} días restantes</span></div><p class="muted">Los objetivos se acumulan durante cinco días, a partir de las series registradas en tus misiones normales. No necesitas repetir una serie para contabilizarla dos veces.</p>${caution}${rows.map(o=>{const e=E.byId(o.id);return `<div class="quest"><div class="grow"><h3>${xss(e?.name||o.id)}</h3><small>${xss(display[o.category])} · ${tiers[o.tier]} · ${o.done}/${o.target} ${o.unit==='sec'?'segundos':'repeticiones'}</small>${meter(o.done,o.target)}<label class="label" for="monarch-entry-${xss(o.id)}">Registrar avance realizado</label><div class="row" style="gap:8px"><input style="max-width:92px" id="monarch-entry-${xss(o.id)}" type="number" inputmode="numeric" min="1" max="${o.target}" value="1"><button class="btn ghost" onclick="HunterMonarch3852.register('${xss(o.id)}')">Guardar</button></div></div></div>`;}).join('')}<button class="btn ghost" onclick="HunterMonarch3852.cancel()">Cancelar misión</button></div>`;
  }else{
   const previous=st.history[0];
   const cooldown=previous&&previous.expiresAt>now();
   const selected=cooldown?{ok:false,reason:'El próximo ciclo estará disponible el '+new Date(previous.expiresAt).toLocaleString('es')+'.'}:generate(state.profile,state.logs);
   main=selected.ok?`<div class="card"><h3>Próxima misión · vista previa</h3><p class="muted">Se crearán cinco objetivos acumulativos que respetan la dificultad, el equipo y los registros de tu perfil.</p>${selected.objectives.map(o=>`<div class="quest"><div class="grow"><h3>${xss(E.byId(o.id)?.name||o.id)}</h3><small>${xss(display[o.category])} · ${tiers[o.tier]} · ${o.target} ${o.unit==='sec'?'segundos':'repeticiones'} en cinco días</small></div></div>`).join('')}<p class="footnote">Activación voluntaria. Revisa la técnica, la recuperación y detén cualquier actividad que provoque dolor. No hay penalizaciones físicas por cancelar.</p><button class="btn primary" onclick="HunterMonarch3852.activate()">AUTORIZAR Y ACTIVAR MISIÓN</button></div>`:
    `<div class="notice warn">${xss(selected.reason||guard.reason)}</div>`;
  }
  return `<section class="intro"><span class="eyebrow">HUNTER SYSTEM · DESAFÍOS ESPECIALES</span><h1>👑 Misiones Monarca</h1><p class="muted">Cinco ejercicios personalizados. Cada progreso cuenta en un ciclo de cinco días.</p></section><div class="metrics"><div class="metric"><strong>${st.xp}</strong><small>XP Monarca</small></div><div class="metric"><strong>${st.history.filter(x=>x.status==='completed').length}</strong><small>Misiones completadas</small></div></div>${judgement}${main}<div class="section-title"><h2>Historial Monarca</h2></div><div class="card">${historical||'<p class="muted">Aún no has realizado ninguna misión Monarca.</p>'}</div>`;
 }
 root.HunterMonarch3852=Object.freeze({version:'38.54',generate,eligible,screening,progress,activate,cancel,register,view,tile,review,requiresReview});
})(window);

;
/* Hunter System 38.2: presentación sencilla, sin alterar el motor ni el historial. */
(function(){'use strict';
 const relocate=()=>{
   const modal=document.querySelector('#modal');if(!modal?.open)return;
   const timed=modal.querySelector('#guidedTiming375');
   const canvas=modal.querySelector('#guidedCanvas');
   const host=canvas?.closest('.guided-demo')||modal.querySelector('.guided-step .guided-demo');
   if(timed&&host&&timed.parentNode!==host)host.appendChild(timed);
   if(host&&timed)host.classList.add('clean-demo382');
   const vector=modal.querySelector('#vectorTiming375');
   const card=modal.querySelector('.motion-card18');
   if(card&&vector&&vector.parentNode!==card)card.insertBefore(vector,card.firstChild?.nextSibling||null);
 };
 // The guided screen re-renders for each phase; apply to the new DOM, not a global layout.
 if(typeof paintGuide11==='function'){
   const prior=paintGuide11;
   paintGuide11=function(){const result=prior.apply(this,arguments);relocate();return result;};
 }
 if(typeof UI26!=='undefined'&&typeof UI26.decorate==='function'){
   const prior=UI26.decorate;
   UI26.decorate=function(){const result=prior.apply(this,arguments);relocate();return result;};
 }
 if(typeof modal==='function'){
   const prior=modal;
   modal=function(){const result=prior.apply(this,arguments);relocate();return result;};
 }
 window.HunterClean382={relocate};
})();
