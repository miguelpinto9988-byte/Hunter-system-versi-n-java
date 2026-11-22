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
