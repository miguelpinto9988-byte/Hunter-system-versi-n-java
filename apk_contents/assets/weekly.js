/* Semanas civiles, migración sin perder registros y revisión por ejercicio. */
(function(root){
'use strict';
const E=root.Engine;
const startOfWeek=date=>E.plus(date,-((new Date(date+'T12:00:00').getDay()+6)%7));
const active=l=>!!l&&(l.complete||l.pain||l.manual||l.items?.some(x=>x.missionState||x.selfReported||x.actual?.some(a=>a.done)));
function month(year,number){const first=new Date(year,number,1,12),count=new Date(year,number+1,0,12).getDate();return {year,number,count,offset:(first.getDay()+6)%7,dates:Array.from({length:count},(_,i)=>E.dateKey(new Date(year,number,i+1,12)))};}
const previousMake=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){const c=previousMake({...p,minutes:30},start,index,last,logs);c.days=c.days.slice(0,7);c.profile=JSON.parse(JSON.stringify(p));c.periodDays=7;c.weeklyVersion=10;c.activeFrom=start;c.reviews=[];return c;};
function migrate(s,today){if(s.weeklyVersion===10&&s.cycles.every(c=>c.days.length===7))return false;const groups=new Map();for(const c of s.cycles){for(const d of c.days){const start=startOfWeek(d.date);let w=groups.get(start);if(!w){w={...c,start,days:[],reviews:[],legacyReviews:c.reviews||[],periodDays:7,weeklyVersion:10,activated:start<startOfWeek(today),activeFrom:c.activeFrom||c.start};groups.set(start,w);}const old=w.days.findIndex(x=>x.date===d.date);if(old<0)w.days.push(d);else if(active(s.logs[d.date])||d.customized||d.routineId)w.days[old]=d;}}
s.cycles=[...groups.values()].sort((a,b)=>a.start.localeCompare(b.start)).map((w,i)=>({...w,index:i,days:Array.from({length:7},(_,n)=>w.days.find(d=>d.date===E.plus(w.start,n))||{date:E.plus(w.start,n),kind:'rest',items:[],tasks:[],title:'Fuera del plan anterior'})}));s.weeklyVersion=10;return true;}
function review(last,next,logs,p,now,feedback={}){if(!last||last.start!==E.plus(next.start,-7))return null;const prior=last.days.filter(d=>d.date>=(last.activeFrom||last.start)&&d.items.length),records=prior.map(d=>logs[d.date]).filter(Boolean),done=records.filter(l=>l.complete&&!l.pain),adherence=prior.length?done.length/prior.length:0,efforts=done.map(l=>l.rpe).filter(x=>x>=1&&x<=10),effort=efforts.length?efforts.reduce((a,b)=>a+b,0)/efforts.length:0,mode=Adapt.mode(p),personalReduced=TrainingIntensity.scale(p)<1;
let action='mantener',reason='Semana nueva: mantén la dosis mientras reúnes registros comparables.',changed=0,details=[];
const pain=feedback.pain||records.some(l=>l.pain),fatigue=feedback.fatigue||records.some(l=>l.fatigue||l.rpe>=8);
if(pain||!mode.auto){action='pausar';reason='Dolor registrado o indicaciones pendientes: revisa tu estado antes de volver a entrenar.';}
else if(fatigue){action='reducir';reason='Esfuerzo alto o fatiga: reduce la dosis de la siguiente semana.';}
else if(mode.reduced)reason='Semana adaptada: mantén la dosis reducida y las restricciones registradas.';
else if(personalReduced)reason='Mantén la reducción personal de carga. Puedes cambiarla desde Perfil; dolor y fatiga conservan prioridad.';
else if(adherence<.8)reason='Mantén la dosis: completa al menos el 80 % de los días de ejercicio antes de progresar.';
for(const d of next.days){if(d.date<now||d.customized||d.routineId||active(logs[d.date])||d.tasks?.some(t=>t.done))continue;let edited=false;
 if(action==='pausar'){if(d.items.length){d.items=[];d.kind='rest';d.tasks=[{id:'health-review',text:'Revisar síntomas e indicaciones antes de entrenar',done:false}];edited=true;}}
 else for(const x of d.items){if(action==='reducir'){x.target=Math.max(E.unitOf(x)==='sec'?10:4,Math.floor(x.target*.8));edited=true;}else if(!mode.reduced&&!personalReduced&&adherence>=.8){const r=AdaptiveV9.recommend(logs,x,p,now);details.push({fromId:x.id,...r});if(r.variantChanged&&root.TrainingProgression19){Object.assign(x,root.TrainingProgression19.applyRecommendation(x,r,p));edited=true;}else if(r.target>x.target){x.target=r.target;edited=true;}}}
 if(edited)changed++;
}
if(action==='mantener'&&changed){action='aumentar';reason=details.some(x=>x.variantChanged)?'Registros completos y adherencia suficiente: se avanza a una variante compatible, recalibrando repeticiones y series.':'Tres registros completos del mismo ejercicio y adherencia suficiente: +1 repetición o hasta 10 % de tiempo.';}
const r={week:1,date:now,from:last.start,action,reason,adherence,effort,changed,details};if(root.ProgressMonitor20)r.analysis20=root.ProgressMonitor20.summary(logs,next.days,p,now);next.reviews=[r];next.decision=reason;return r;}
function current(s,today=E.dateKey()){migrate(s,today);const start=startOfWeek(today);
// La carga de capas antiguas puede consultar el plan antes de que el motor
// actual esté disponible. Solo se crea una vista previa durante ese arranque.
if(root.Runtime20?.initializing){const existing=s.cycles.find(c=>c.start===start);if(existing?.activated)return existing;const last=s.cycles.filter(c=>c.start<start).at(-1),preview=E.makeCycle(s.profile,start,(last?.index??-1)+1,last,s.logs);preview.activeFrom=today;preview.previewBoot20=true;return preview;}
let c=s.cycles.find(c=>c.start===start);if(c?.activated)return c;const last=s.cycles.filter(c=>c.start<start).at(-1),index=c?.index??((s.cycles.at(-1)?.index??-1)+1),fresh=E.makeCycle(s.profile,start,index,last,s.logs);fresh.activeFrom=last?.start===E.plus(start,-7)?start:today;
if(c)fresh.days=fresh.days.map(d=>{const old=c.days.find(x=>x.date===d.date);return old&&(old.customized||old.routineId||active(s.logs[d.date])||old.tasks?.some(t=>t.done))?old:d;});
review(last,fresh,s.logs,s.profile,today,s.weekFeedback?.[today]||{});fresh.activated=true;if(c)s.cycles[s.cycles.indexOf(c)]=fresh;else s.cycles.push(fresh);s.cycles.sort((a,b)=>a.start.localeCompare(b.start));return fresh;}
const dailyXP=kind=>kind==='strength'||kind==='mixed'?100:kind==='cardio'?70:kind==='recovery'?40:20;
function progress(l){if(!l||l.pain)return {done:0,total:0,fraction:0,xp:0};const tasks=l.missionTasks||[],total=(l.items||[]).length+tasks.length;let done=tasks.filter(t=>t.done).length;for(const x of l.items||[]){done+=x.selfReported||x.missionState?1:Math.min(1,(x.actual||[]).filter(a=>a.done).length/Math.max(1,x.sets));}const fraction=l.complete?1:total?Math.min(1,done/total):0;return {done,total,fraction,xp:Math.floor(dailyXP(l.kind)*fraction)};}
const oldStats=E.stats;E.stats=function(logs,today=E.dateKey()){const s=oldStats(logs,today);s.xp=Object.entries(logs).filter(([d])=>d<=today).reduce((a,[,l])=>a+progress(l).xp,0);s.level=1+Math.floor(s.xp/300);return s;};
root.Weekly={startOfWeek,month,migrate,review,current,active,progress,dailyXP};
})(typeof window!=='undefined'?window:globalThis);
