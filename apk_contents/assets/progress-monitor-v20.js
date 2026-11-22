/* Estimación local a partir de series confirmadas. No crea registros de ejemplo. */
(function(root){
 'use strict';
 const E=root.Engine,originalEvidence=AdaptiveV9.evidence;
 function completeItem(x){return Number.isInteger(x?.sets)&&x.sets>0&&Number(x.target)>0&&x.actual?.length===x.sets&&x.actual.every(a=>a.done&&Number.isFinite(Number(a.value))&&Number(a.value)>=x.target&&Number.isFinite(Number(a.kg||0))&&Number(a.kg||0)>=0);}
 AdaptiveV9.evidence=function(logs,id,now){
  const seen=new Set();return originalEvidence(logs,id,now).filter(row=>{if(seen.has(row.date)||!completeItem(row.item))return false;seen.add(row.date);return true;});
 };
 function load(x){const a=(x.actual||[]).filter(a=>a.done);return a.length?a.reduce((n,a)=>n+Number(a.kg||0),0)/a.length:Number(x.kg||0);}
 function features(x,kg){return [Number(x.target)/(E.unitOf(x)==='sec'?60:20),Number(x.sets)/5,Number(x.rest||30)/90,kg/30];}
 function estimate(logs,item,now){
  const rows=AdaptiveV9.evidence(logs,item.id,now).filter(r=>E.unitOf(r.item)===E.unitOf(item)&&(r.item.side||'both')===(item.side||'both'));
  if(rows.length<3)return null;
  const kg=item.actual?.some(a=>a.done)||item.kg!==undefined?load(item):load(rows[0].item),q=features(item,kg);
  const neighbors=rows.map(r=>({...r,d:Math.hypot(...features(r.item,load(r.item)).map((v,i)=>v-q[i]))})).filter(r=>r.d<=.75).sort((a,b)=>a.d-b.d).slice(0,5);
  if(neighbors.length<3)return null;
  let sum=0,weight=0;for(const row of neighbors){const w=1/(.1+row.d);sum+=row.rpe*w;weight+=w;}
  return {effort:Math.round(sum/weight*10)/10,neighbors:neighbors.length,sessions:rows.length,dates:neighbors.map(r=>r.date),method:'kNN local · series confirmadas',loadKg:Math.round(kg*10)/10};
 }
 function summary(logs,days,p,now){
  const completed=Object.entries(logs||{}).filter(([date,l])=>date<=now&&l.complete&&!l.pain&&l.rpe>=1&&l.rpe<=10&&l.items?.length&&l.items.every(completeItem));
  const pending=(days||[]).filter(d=>d.date>=now&&d.items?.length).flatMap(d=>d.items),unique=[...new Map(pending.map(x=>[x.id+':'+(x.side||'both'),x])).values()];
  const analysis=unique.map(item=>{const r=TrainingProgression19.recommend(logs,item,p,now);return {id:item.id,name:E.byId(item.id)?.name,sets:item.sets,target:item.target,unit:E.unitOf(item),decision:r.action,reason:r.reason,nextId:r.id,estimate:r.estimate20||estimate(logs,item,now),records:r.samples};});
  return {date:now,completedSessions:completed.length,confirmedSeries:completed.reduce((n,[,l])=>n+l.items.reduce((s,x)=>s+x.actual.length,0),0),objective:GoalGroups14.groups[GoalGroups14.of(p)]?.name||p.finalTarget,analysis};
 }
 root.ProgressMonitor20={completeItem,estimate,summary,load,features};
})(window);
