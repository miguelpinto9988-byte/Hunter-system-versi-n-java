/* Hunter System V38.15 — progressive training based on completed sessions.
 * No additional panels, no changes to exercise models, no medical assessment.
 * Uses only recorded training, never guessed adaptations.
 */
(function(root){
 'use strict';
 if(root.HunterProgress3815)return;
 const num=(v,lo,hi)=>{const n=Number(v);return Number.isFinite(n)?Math.max(lo,Math.min(hi,n)):0;};
 const validDay=k=>/^\d{4}-\d{2}-\d{2}$/.test(k);
 const previousDay=(iso,n)=>{const d=new Date(iso+'T12:00:00Z');if(!Number.isFinite(d.getTime()))return '';d.setUTCDate(d.getUTCDate()-n);return d.toISOString().slice(0,10);};
 function records(logs,date,days=14){
  const min=previousDay(date,days),sessions=[];
  for(const [key,l] of Object.entries(logs||{}))if(validDay(key)&&key<date&&key>=min&&l&&typeof l==='object')sessions.push(l);
  const done=sessions.filter(l=>l.complete===true&&!l.manual&&!l.pain&&num(l.rpe,0,10)>0);
  const efforts=done.map(l=>num(l.rpe,0,10));
  const average=efforts.length?efforts.reduce((a,b)=>a+b,0)/efforts.length:0;
  return {observed:sessions.length,completed:done.length,average,
   symptoms:sessions.some(l=>!!l.pain||!!l.fatigue||num(l.rpe,0,10)>=8),
   // Missing feedback does not count as favourable evidence.
   adequate:done.length>=2&&average>0&&average<=7&&!sessions.some(l=>!!l.pain||!!l.fatigue)};
 }
 function restrictions(p,status){return !!(status?.reduced||p?.limits||p?.health?.special||p?.health?.acute||
   (p?.health?.pain&&p.health.pain!=='none')||(p?.injuries||[]).some(i=>i&&i.stage!=='past'));}
 function dose(ex,p,kind,status,feedback,date,current,weekIndex=0,logs={}){
  const out={...current};
  if(!out||!ex||!p||!Number.isInteger(weekIndex)||weekIndex<1||weekIndex>2)return out;
  if(restrictions(p,status)||feedback?.strain||feedback?.canProgress!==true)return out;
  if(out.noAutoLoad||out.sets<1||out.target<1)return out;
  const evidence=records(logs,date);
  if(!evidence.adequate||evidence.symptoms)return out;
  if(kind!=='strength'&&kind!=='cardio')return out;
  if(!['strength','muscle','endurance','athletic'].includes(p.goal))return out;
  // At most one extra increment on favourable evidence, regardless of the week.
  // Other planners may already have increased the target; avoid multiplying sets.
  const unit=ex.unit==='sec'?'sec':'reps';
  const cap=unit==='sec'?Math.min(60,num(out.target,1,90)+5):Math.min(16,num(out.target,1,30)+1);
  out.target=cap;
  out.progression3815={week:weekIndex+1,reason:'Dos o más sesiones anteriores con esfuerzo registrado y sin molestias.',evidence:evidence.completed};
  return out;
 }
 function cycleReport(cycle,logs={}){
  const weeks=[];
  for(let w=0;w<3;w++){
   const slice=(cycle?.days||[]).slice(w*7,(w+1)*7);
   let scheduled=0,done=0,rest=0,protectedDays=0;
   for(const d of slice){if(!d)continue;
    if(d.scheduleChoice==='rest')rest++;
    else if(d.scheduleChoice){scheduled++;if(logs[d.date]?.complete===true)done++;}
    if(d.customized||d.manual||logs[d.date]?.complete)protectedDays++;
   }
   weeks.push({number:w+1,scheduled,done,rest,protectedDays});
  }
  return weeks;
 }
 root.HunterProgress3815=Object.freeze({version:'38.15',dose,records,cycleReport});
})(typeof window!=='undefined'?window:globalThis);
