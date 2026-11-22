/* Hunter System V38.14: requisitos explícitos y presupuesto de tiempo.
 * No diagnostica lesiones, no agrega ventanas ni modifica demostraciones.
 * 5,7,10,17,21,34,37,38,39,40,45,66,78,79,80 son propuestas excluidas,
 * NO identificadores de ejercicios.
 */
(function(root){
'use strict';
if(root.HunterMaterials3814)return;
const own=(o,k)=>Object.prototype.hasOwnProperty.call(o||{},k);
const requiredTokens=new Set(['db','gym','bench','step']);
const gymTokens=new Set(['barbell','cable','machine','smith','cardio','bench']);
function requirements(exercise){
 if(!exercise||typeof exercise.id!=='string')return null;
 const items=new Set();
 for(const k of Array.isArray(exercise.requirements)?exercise.requirements:[]){
  if(typeof k!=='string'||!requiredTokens.has(k))return null;
  items.add(k);
 }
 const p=exercise.param23;
 if(p&&typeof p==='object'){
  if(p.bench===true)items.add('bench');
  if(p.step===true)items.add('step');
  if(p.db===true)items.add('db');
 }
 if(exercise.gymOnly23===true){
  if(!Array.isArray(exercise.gymRequirements23))return null;
  for(const k of exercise.gymRequirements23){
   if(typeof k!=='string'||!gymTokens.has(k))return null;
   items.add(k);
  }
 }
 return [...items];
}
function eligible(exercise,profile){
 if(!exercise||!profile||!root.HunterSchedule3813?.eligible?.(exercise,profile))return false;
 const required=requirements(exercise);if(!required)return false;
 const gear=String(profile.equipment||'body');
 const configured=Array.isArray(profile.gymEquipment23)?new Set(profile.gymEquipment23):new Set();
 for(const k of required){
  if(k==='db'&&!['db','gym'].includes(gear))return false;
  if(k==='gym'&&gear!=='gym')return false;
  if(k==='bench'&&profile.bench!==true&&!configured.has('bench'))return false;
  if(k==='step'&&profile.step!==true)return false;
  if(gymTokens.has(k)&&k!=='bench'&&(gear!=='gym'||!configured.has(k)))return false;
 }
 return true;
}
function sessionCap(profile,kind,status={}){
 if(kind==='rest')return 0;
 const raw=Number(profile?.minutes);
 const minutes=Number.isFinite(raw)?Math.min(90,Math.max(10,raw)):20;
 const unit=kind==='strength'?5:kind==='cardio'?4:3;
 let cap=Math.max(1,Math.min(7,Math.floor(minutes/unit)));
 const preferred=Number(profile?.trainingMode==='routines'?profile?.routineExercises3878:profile?.dailyMissions);
 if(Number.isInteger(preferred)&&preferred>=1)cap=Math.min(cap,preferred);
 if(status?.reduced)cap=Math.min(cap,3);
 return cap;
}
function coverage(catalog=[]){
 const stats={total:0,explicitAux:0,invalid:0,requiresBench:0,requiresStep:0,requiresGymGear:0};
 for(const exercise of catalog){
  if(!exercise?.id)continue;stats.total++;
  const req=requirements(exercise);
  if(!req){stats.invalid++;continue;}
  if(req.some(x=>!['db','gym'].includes(x)))stats.explicitAux++;
  if(req.includes('bench'))stats.requiresBench++;
  if(req.includes('step'))stats.requiresStep++;
  if(req.some(x=>gymTokens.has(x)&&x!=='bench'))stats.requiresGymGear++;
 }
 return stats;
}
root.HunterMaterials3814=Object.freeze({version:'38.14',requirements,eligible,sessionCap,coverage});
})(typeof window==='undefined'?globalThis:window);
