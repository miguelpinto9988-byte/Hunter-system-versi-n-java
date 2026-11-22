/* Aprendizaje por ejercicio y revisión conservadora; umbrales de producto, no validación clínica. */
(function(root){
const E=root.Engine;
function evidence(logs,id,now){const base=E.byId(id)?.family||E.byId(id)?.baseId||id;return Object.entries(logs||{}).filter(([date,l])=>date<now&&l.complete&&!l.pain&&!l.fatigue&&l.rpe>=1&&l.rpe<=10).flatMap(([date,l])=>(l.items||[]).filter(x=>x.id===id).map(x=>({date,rpe:Number(l.rpe),item:x,base}))).sort((a,b)=>b.date.localeCompare(a.date)).slice(0,12);}
function recommend(logs,item,p,now){const rows=evidence(logs,item.id,now).filter(r=>E.unitOf(r.item)===E.unitOf(item)&&(r.item.side||'both')===(item.side||'both')),mode=Adapt.mode(p),e=E.byId(item.id);let target=item.target,reason='Registra al menos tres sesiones de este ejercicio con esfuerzo y series completas.',action='mantener';
const recent=rows.slice(0,3),full=recent.length===3&&recent.every(r=>r.item.actual?.length>=Math.max(r.item.sets,item.sets)&&r.item.actual.every(a=>a.done&&Number(a.value)>=Math.max(r.item.target,item.target)));
if(!mode.auto||mode.reduced)return {target,action,reason:'Se conserva la dosis por las restricciones del perfil.',samples:rows.length};
if(full&&recent.every(r=>r.rpe<=7)) {const cap=E.unitOf(item)==='sec'?90:p.finalTarget==='endurance'?20:15;const delta=E.unitOf(item)==='sec'?Math.max(1,Math.min(5,Math.floor(target*.1))):1;target=Math.min(cap,target+delta);action=target>item.target?'aumentar':'mantener';reason=action==='aumentar'?'Tres sesiones completas con esfuerzo de 7/10 o menos: progresión de una sola variable.':'Tope de repeticiones alcanzado. Revisa manualmente una carga que puedas controlar; no se aumenta el peso automáticamente.';}
return {target,action,reason,samples:rows.length};}
const previous=Coach.review;
Coach.review=function(c,logs,p,now,feedback={}){const week=Math.min(3,Math.floor(E.diff(now,c.start)/7));if(week<1||c.reviews?.some(r=>r.week===week))return null;
const weekDays=c.days.slice((week-1)*7,week*7).filter(d=>d.items.length),records=weekDays.map(d=>logs[d.date]).filter(Boolean);
// Dolor, esfuerzo alto, fatiga y restricciones conservan la ruta de reducción previa.
if(feedback.pain||feedback.fatigue||records.some(l=>l.pain||l.fatigue||Number(l.rpe)>=8)||!Adapt.mode(p).auto||Adapt.mode(p).reduced)return previous(c,logs,p,now,feedback);
let changed=0,details=[];const complete=records.filter(l=>l.complete).length,adherence=weekDays.length?complete/weekDays.length:0;
if(adherence>=.8)c.days.filter(d=>d.date>=now&&!d.customized&&!d.routineId&&!d.tasks?.some(t=>t.done)&&!logs[d.date]?.complete&&!logs[d.date]?.items?.some(x=>x.missionState||x.selfReported||x.actual?.some(a=>a.done))).forEach(d=>{let modified=false;d.items.forEach(x=>{const result=recommend(logs,x,p,now);details.push({id:x.id,...result});if(result.target>x.target){x.target=result.target;modified=true;}});if(modified){changed++;delete logs[d.date];}});
const r={week,date:now,action:changed?'aumentar':'mantener',reason:changed?'Progresión por ejercicio con tres registros completos: +1 repetición o hasta 10 % de tiempo; conserva series, peso y descansos.':'Se mantiene el plan: faltan tres registros completos por ejercicio, adherencia suficiente o margen de progresión.',adherence,changed,details};(c.reviews||(c.reviews=[])).push(r);return r;};
// Cambia el ciclo conservando progreso solo del MISMO ejercicio, no del primer ejercicio de otra sesión.
const make=E.makeCycle;E.makeCycle=function(p,start,index,last,logs){const c=make(p,start,index,last,logs);if(last&&!GoalPlans.plans[p.finalTarget]?.gentle&&!Adapt.mode(p).reduced&&Adapt.mode(p).auto)c.days.forEach(d=>d.items.forEach(x=>{const matches=last.days.flatMap(y=>y.items).filter(y=>y.id===x.id&&E.unitOf(y)===E.unitOf(x)&&(y.side||'both')===(x.side||'both'));if(matches.length)x.target=Math.min(E.unitOf(x)==='sec'?90:15,matches.at(-1).target);else if(E.unitOf(x)!=='sec')x.target=p.goal==='muscle'?10:8;}));return c;};
root.AdaptiveV9={evidence,recommend};
})(window);
/* Estimador adicional: vecinos del mismo ejercicio, usando dosis y carga declarada. */
(function(root){
const oldPredict=Learning.predict;
function predictExercise(logs,item,now){const rows=AdaptiveV9.evidence(logs,item.id,now).filter(r=>Engine.unitOf(r.item)===Engine.unitOf(item));if(rows.length<3)return null;
const vector=x=>{const actual=(x.actual||[]).filter(a=>a.done),kg=actual.length?actual.reduce((n,a)=>n+(Number(a.kg)||0),0)/actual.length:0;return [Number(x.target)/20,Number(x.sets)/4,(Number.isFinite(Number(x.rest))?Number(x.rest):90)/120,kg/20];};
const q=vector(item);const near=rows.map(r=>({r,d:Math.hypot(...vector(r.item).map((v,i)=>v-q[i]))})).sort((a,b)=>a.d-b.d).slice(0,5);if(near[0].d>.8)return null;let sum=0,weight=0;for(const n of near){let w=1/(n.d+.1);sum+=n.r.rpe*w;weight+=w;}return {effort:Math.round(sum/weight*10)/10,neighbors:near.length,sessions:rows.length};}
AdaptiveV9.predictExercise=predictExercise;
Learning.predict=function(data,items,bump=false){const exact=oldPredict(data,items,bump);if(exact)return exact;
// Convert existing training rows without generating synthetic experiences.
const logs={};for(const r of data){if(r.items)logs[r.date]={complete:true,rpe:r.y,items:r.items};}
const estimates=items.map(x=>predictExercise(logs,{...x,target:x.target+(bump?(Engine.unitOf(x)==='sec'?Math.max(1,Math.floor(x.target*.1)):1):0)},'9999-12-31'));
if(!estimates.length||estimates.some(x=>!x))return null;return {effort:Math.max(...estimates.map(x=>x.effort)),neighbors:Math.min(...estimates.map(x=>x.neighbors)),sessions:Math.min(...estimates.map(x=>x.sessions)),method:'por ejercicio'};};
const samples=Learning.samples;Learning.samples=function(logs,now){return samples(logs,now).map(row=>({...row,items:logs[row.date].items}));};
})(window);
