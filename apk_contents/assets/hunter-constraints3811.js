/* Hunter System V38.11 — revisión complementaria de restricciones y carga.
 * Reutiliza metadatos declarados del catálogo; no hace diagnósticos.
 * Evaluaciones internas; no crea UI, contenido gráfico ni sesiones nuevas.
 */
(function (root) {
 'use strict';
 if (root.HunterConstraints3811) return;
 const A = root.Adapt;
 const H = root.HunterPlanner379;
 const E = root.Engine;
 if (!A || !H || !E) return;
 const injuryZones = new Set(['neck','shoulder','elbow','wrist','back','hip','knee','ankle']);
 const lower = new Set(['hip','knee','ankle']);
 const upper = new Set(['neck','shoulder','elbow','wrist']);
 const bodyParts = new Set(['Piernas','Cadera','Brazos','Hombros','Espalda','Pecho','Core','Abdomen']);
 const has = (o,k) => Object.prototype.hasOwnProperty.call(o||{},k);
 const array = x => Array.isArray(x) ? x : [];
 function activeZones(p) {
  return [...new Set(array(p?.injuries).filter(x=>x&&x.stage!=='past').map(x=>String(x.zone||'')))];
 }
 function constraints(p) {
  const r=p?.restrictions||{};
  return {noImpact:!!r.noImpact,noFloor:!!r.noFloor,noOverhead:!!r.noOverhead,
   noKnee:!!r.noKnee,noWrist:!!r.noWrist,noLoad:!!r.noLoad,
   seatedOnly:!!r.seatedOnly,noSpine:!!r.noSpine};
 }
 function attributes(e) {
  // Las etiquetas son un inventario mecánico, NO autorizaciones médicas.
  const floor=e?.floor===true, seated=e?.seated===true, standing=e?.standing===true;
  return {floor,seated,standing,stance:seated?'sentado':floor?'suelo':standing?'de pie':'no especificada',
   handSupport:e?.wristLoad===true,overhead:e?.overhead===true,
   externalLoad:e?.equipment==='db'||e?.equipment==='gym'||array(e?.requirements).includes('db'),
   impact:e?.impact==='Con saltos',kneeBend:e?.kneeBend===true,spineMove:e?.spineMove===true,
   metadataComplete:Array.isArray(e?.zones)&&e.zones.length>0&&!!e.category&&!!e.group};
 }
 function review(e,p) {
  const flags=[];
  if(!e||!p||typeof e.id!=='string'||!e.id) return {allowed:false,flags:['sin_datos'],facts:attributes(e)};
  const affected=activeZones(p),r=constraints(p),f=attributes(e);
  if(!f.metadataComplete) flags.push('metadatos_insuficientes');
  if(affected.some(z=>!injuryZones.has(z))) flags.push('zona_desconocida');
  if(affected.some(z=>array(e.zones).includes(z))) flags.push('zona_implicada');
  if(r.seatedOnly&&!f.seated) flags.push('requiere_sentado');
  if(r.noFloor&&f.floor) flags.push('evitar_suelo');
  if(r.noLoad&&f.externalLoad) flags.push('evitar_carga');
  if(r.noImpact&&f.impact) flags.push('evitar_impacto');
  if(r.noOverhead&&f.overhead) flags.push('evitar_sobrecabeza');
  if(r.noKnee&&f.kneeBend) flags.push('evitar_flexion_rodilla');
  if(r.noSpine&&f.spineMove) flags.push('evitar_giros_tronco');
  if(r.noWrist&&(f.handSupport||(f.floor&&!f.seated&&['Pecho','Hombros','Core','Abdomen'].includes(e.group))))
   flags.push('evitar_apoyo_manos');
  // Revisión adicional de apoyos, incluso cuando el grupo muscular principal es distinto.
  if(affected.includes('wrist')&&(f.handSupport||(f.floor&&!f.seated&&['Pecho','Hombros','Core','Abdomen'].includes(e.group))))
   flags.push('muñeca_en_apoyo');
  if(affected.includes('knee')&&f.floor&&f.kneeBend) flags.push('rodilla_en_apoyo');
  if(affected.includes('ankle')&&f.standing) flags.push('tobillo_en_apoyo');
  if(affected.includes('back')&&(f.spineMove||f.externalLoad&&f.standing)) flags.push('carga_tronco');
  if(affected.includes('shoulder')&&f.overhead) flags.push('carga_hombro');
  return {allowed:flags.length===0,flags:[...new Set(flags)],facts:f};
 }
 function allowed(e,p) {
  if(!e||!p)return false;
  const s=H.status(p);
  if(!s.ok) return false;
  // El filtro existente continúa siendo obligatorio, nunca se sustituye por etiquetas nuevas.
  let baseline=false;
  try {baseline=!!H.approved(e,p);}catch(_) {return false;}
  return baseline&&review(e,p).allowed;
 }
 function rank(e,p) {
  if(!e||!p)return 0;
  const affected=activeZones(p),f=attributes(e);
  if(!affected.length)return 0;
  // Solo se ejecuta tras filtrar opciones mediante allowed() en el generador.
  // No repetir aquí las comprobaciones complejas de lesiones por cada puntuación.
  let value=0;
  if(affected.some(z=>lower.has(z))) {
   if(f.seated)value+=12;
   if(['Brazos','Pecho','Espalda','Hombros'].includes(e.group))value+=4;
  }
  if(affected.some(z=>upper.has(z)) && ['Piernas','Cadera'].includes(e.group))value+=6;
  if(affected.includes('back')&&f.seated)value+=5;
  if(f.impact||f.externalLoad)value-=4;
  return value;
 }
 function symptomTrend(logs, day) {
  // Señales declaradas por el usuario, no análisis clínico ni almacenamiento nuevo.
  const start = new Date(String(day) + 'T12:00:00Z');
  if (!Number.isFinite(start.getTime()))return {days:0,recentSymptom:false};
  let days=0, recentSymptom=false;
  for (const [date,log] of Object.entries(logs||{})) {
   if(!/^\d{4}-\d{2}-\d{2}$/.test(date)||!log)continue;
   const delta=(start-new Date(date+'T12:00:00Z'))/86400000;
   if(!Number.isFinite(delta)||delta<=0||delta>14)continue;
   const noticed=!!(log.pain||log.fatigue||Number(log.rpe)>=8);
   if (noticed) {days++;recentSymptom=true;}
  }
  return {days,recentSymptom};
 }
 function summary(p) {
  const total={examined:0,admissible:0,blocked:0,groups:{},categories:{},restrictions:{}};
  if(!p||!H.status(p).ok)return {...total,requiresReview:true};
  for(const e of array(E.exercises)) {
   if(e?.variant!=='base'||e?.sourceOnly)continue;
   total.examined++;
   const accepted=allowed(e,p);
   if(accepted){total.admissible++;if(bodyParts.has(e.group))total.groups[e.group]=(total.groups[e.group]||0)+1;
    if(e.category)total.categories[e.category]=(total.categories[e.category]||0)+1;
   } else {
    total.blocked++;
    for(const flag of review(e,p).flags)total.restrictions[flag]=(total.restrictions[flag]||0)+1;
   }
  }
  return {...total,requiresReview:false,note:'Estadísticas de etiquetas; no certifican que un ejercicio sea adecuado para una lesión.'};
 }
 root.HunterConstraints3811=Object.freeze({version:'38.11',attributes,activeZones,constraints,review,allowed,rank,summary,symptomTrend});
})(typeof window!=='undefined'?window:globalThis);
