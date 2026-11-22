/* Hunter System 38.49: shared weekly group routing across legacy entry points.
 * The common generator retains goal, equipment, capacity and clinical filters.
 * Existing exercises, animations, completed logs and manual workouts are retained.
 */
(function(root){'use strict';
 if(root.HunterWeek3847)return;
 const E=root.Engine,S=root.Schedule,D=root.HunterDynamic381;
 const central=e=>Boolean(e?.category==='Fuerza'&&['Core','Abdomen'].includes(e.group));
 function type(p,date=E.dateKey()){
  if(!p||!S.valid(p))return null;const i=S.weekday(date),day=p.weekPlan[i];
  return day==='train'?(['lower','core'].includes(p.weekFocus?.[i])?p.weekFocus[i]:'upper'):day;
 }
 function isScheduledKind(p,kind,date){const t=type(p,date);return kind===({upper:'strength',lower:'strength',core:'strength',cardio:'cardio',mobility:'recovery'}[t]);}
 function scheduledPlan(p,kind,seed,logs={},date=E.dateKey()){
  return D.choose(p,kind,'week47|'+seed,logs,date);
 }
 // Compatibility entry points used by older coach previews and schedule callers.
 // Their private historical planners still run inside the existing cycle pipeline;
 // Dynamic381 and Strict3838 perform final per-day reconciliation there.
 for(const object of [root.Training30,root.Training23,S]){
  const name=object===S?'items':'choose',before=object?.[name];if(typeof before!=='function')continue;
  object[name]=function(p,kind,rotation=0){
   if(isScheduledKind(p,kind,E.dateKey()))return scheduledPlan(p,kind,'legacy|'+rotation,typeof state!=='undefined'?state.logs:{},E.dateKey()).items;
   return before.apply(this,arguments);
  };
 }
 for(const [object,oldKind]of [[root.HunterPlanner379,false],[root.HunterInjuryPlanner378,true]]){
  if(typeof object?.choose!=='function')continue;const before=object.choose;
  object.choose=function(p,kind,rotation=0,logs={},date=E.dateKey()){
   if(!isScheduledKind(p,kind,date))return before.apply(this,arguments);
   const result=scheduledPlan(p,kind,'restricted|'+rotation,logs,date);
   return oldKind?{...result,kind:result.effective||kind,originalKind:kind}:result;
  };
 }
 const previousValidation=validateBackup;
 validateBackup=function(data){
  if(!previousValidation(data))return false;
  return [data.profile,...(data.cycles||[]).map(c=>c.profile)].filter(Boolean).every(p=>p.weekFocus===undefined||
   Array.isArray(p.weekFocus)&&p.weekFocus.length===7&&p.weekFocus.every(x=>['none','upper','lower','core'].includes(x)));
 };
 root.HunterWeek3847=Object.freeze({version:'38.47',type,isCentral:central,
  pool:p=>D.pool(p,'strength').filter(central),
  choices:['rest','upper','lower','core','cardio','mobility']});
})(window);
