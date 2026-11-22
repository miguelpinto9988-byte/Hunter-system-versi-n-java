/* Hunter System 38.12: inventario y ajuste conservador de objetivos.
 * Sin ventanas nuevas, sin metadatos biomecánicos supuestos y sin alterar SVG.
 * Únicamente se utiliza DESPUÉS de la compatibilidad y la seguridad existentes.
 */
(function(root){
 'use strict';
 if(root.HunterMetadata3812)return;
 const E=root.Engine;
 if(!E||!Array.isArray(E.exercises))return;
 const training=root.HunterTraining387;
 const own=(o,k)=>Object.prototype.hasOwnProperty.call(o||{},k);
 const safeNumber=(value,fallback,min,max)=>{const n=Number(value);return Number.isFinite(n)?Math.max(min,Math.min(max,n)):fallback;};
 const getPattern=e=>training?.pattern?.(e)||null;
 const metadata=new Map();
 for(const e of E.exercises){
  if(!e||typeof e.id!=='string'||!e.id)continue;
  const fields={
   group:typeof e.group==='string'&&!!e.group.trim(),
   category:['Fuerza','Cardio','Movilidad'].includes(e.category),
   equipment:typeof e.equipment==='string'&&!!e.equipment,
   zones:Array.isArray(e.zones)&&e.zones.length>0,
   requirements:Array.isArray(e.requirements),
   level:Number.isFinite(e.level),
   demo:typeof e.demoPath30==='string'&&typeof e.modelPath30==='string',
   pattern:!!getPattern(e)
  };
  const usable=fields.group&&fields.category&&fields.equipment&&fields.zones&&fields.requirements&&fields.level&&fields.demo;
  metadata.set(e.id,Object.freeze({id:e.id,fields:Object.freeze(fields),usable,pattern:fields.pattern?getPattern(e):null}));
 }
 const hasRestrictions=p=>!!((p?.injuries||[]).some?.(i=>i&&i.stage!=='past')||p?.health?.special||p?.health?.acute||p?.health?.pain&&p.health.pain!=='none'||p?.limits);
 function analysis(){
  const complete=[...metadata.values()].filter(x=>x.usable);
  const missing={};for(const row of metadata.values())for(const [key,ok] of Object.entries(row.fields))if(!ok)missing[key]=(missing[key]||0)+1;
  return Object.freeze({version:'38.12',total:metadata.size,eligible:complete.length,
   patternKnown:complete.filter(x=>x.pattern).length,missing,
   note:'Metadatos del catálogo: no constituyen verificación clínica ni técnica de cada ejercicio.'});
 }
 function score(e,p,kind){
  const record=metadata.get(e?.id);if(!record?.usable)return 0;
  const pattern=record.pattern,goal=String(p?.goal||'').toLowerCase();
  // Los patrones documentados solo cambian el orden de selección: no sustituyen filtros de seguridad.
  if(kind==='strength'&&goal==='strength')return ['empuje_horizontal','traccion_horizontal','empuje_vertical','bisagra_cadera','rodilla'].includes(pattern)?6:0;
  if(kind==='strength'&&goal==='muscle')return ['flexion_codo','extension_codo','elevacion_hombro','empuje_horizontal','traccion_horizontal'].includes(pattern)?4:0;
  if(kind==='cardio'&&goal==='endurance')return record.fields.pattern?2:0;
  return 0;
 }
 function dose(e,p,kind,status,feedback,date,current){
  // El motor existente determina primero seguridad, restricciones y dosis conservadora.
  const out={...current};
  if(!metadata.get(e?.id)?.usable)return out;
  if(status?.reduced||feedback?.strain||hasRestrictions(p))return out;
  if(kind!=='strength'||e.unit==='sec')return out;
  const goal=String(p?.goal||'').toLowerCase();
  const effort=safeNumber(feedback?.avg,0,0,10);
  const progress=feedback?.canProgress===true&&effort>0&&effort<=7;
  // Cambios pequeños: solo si existe seguimiento favorable y sin restricciones activas.
  if(goal==='strength'){
   out.target=Math.max(6,Math.min(10,safeNumber(out.target,8,1,30)));
   out.rest=Math.max(90,safeNumber(out.rest,90,0,300));
  }else if(goal==='muscle'){
   out.target=Math.max(8,Math.min(12,safeNumber(out.target,8,1,30)));
   out.rest=Math.max(60,safeNumber(out.rest,90,0,300));
  }else if(goal==='endurance'&&progress){
   out.target=Math.min(15,safeNumber(out.target,8,1,30)+1);
  }
  out.sets=Math.min(3,Math.max(1,safeNumber(out.sets,1,1,6)));
  return out;
 }
 const api=Object.freeze({version:'38.12',analysis,metadata:id=>metadata.get(id)||null,score,dose});
 root.HunterMetadata3812=api;
})(typeof window!=='undefined'?window:globalThis);
