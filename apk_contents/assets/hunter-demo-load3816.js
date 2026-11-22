/* Hunter System V38.16: precarga oportunista de UNA demostración siguiente.
 * Nunca cambia el temporizador, el estado de misiones ni las imágenes.
 * No precarga todo el catálogo ni crea interfaces nuevas.
 */
(function(root, document){
 'use strict';
 if (root.HunterDemoLoad3816 || !root.Motion30 || typeof paintGuide11 !== 'function') return;
 let pending=null, idleHandle=0, timerHandle=0, inFlight=null;
 let requests=0, skipped=0, cancelled=0;
 const loaded=root.Motion30.loaded, cache=root.Motion30.cache;
 const validId=id=>typeof id==='string' && /^[a-zA-Z0-9_-]{1,90}$/.test(id);
 const modeEco=()=>!!document.hidden || !!root.HunterPerf380?.powerSaver ||
   root.matchMedia?.('(prefers-reduced-motion: reduce)')?.matches === true;
 const currentGuide=()=>typeof guide11 !== 'undefined' ? guide11 : null;
 function candidate(){
   const g=currentGuide();
   if (!g || !Array.isArray(g.steps) || !Number.isInteger(g.index)) return null;
   const raw=g.steps[g.index+1]?.id;
   if (!validId(raw)) return null;
   if (typeof root.Engine?.byId !== 'function' || !root.Engine.byId(raw)) return null;
   return raw;
 }
 function clearScheduled(){
   if(idleHandle && typeof root.cancelIdleCallback==='function')root.cancelIdleCallback(idleHandle);
   if(timerHandle)root.clearTimeout(timerHandle);
   idleHandle=0;timerHandle=0;pending=null;
 }
 function schedule(){
   clearScheduled();
   if(modeEco()){skipped++;return;}
   const id=candidate();
   if(!id || loaded?.has(id) || cache?.has(id) || inFlight===id){skipped++;return;}
   pending=id;
   const work=()=>{
     idleHandle=0;timerHandle=0;
     const target=pending;pending=null;
     if(!target||modeEco()||candidate()!==target)return;
     if(loaded?.has(target)||cache?.has(target)||inFlight===target)return;
     if(inFlight){pending=target;return;} // no concurrent speculative loads
     inFlight=target; requests++;
     Promise.resolve().then(()=>root.Motion30.preload(target)).catch(()=>{}).finally(()=>{
       inFlight=null;
       if(pending && !modeEco()) {
         const queued=pending; pending=null;
         if(candidate()===queued)schedule();
       }
     });
   };
   if(typeof root.requestIdleCallback==='function')idleHandle=root.requestIdleCallback(work,{timeout:1400});
   else timerHandle=root.setTimeout(work,220);
 }
 const oldPaint=paintGuide11;
 paintGuide11=function(){const result=oldPaint.apply(this,arguments);schedule();return result;};
 document.addEventListener('visibilitychange',()=>{
   if(document.hidden){clearScheduled();cancelled++;}
 },{passive:true});
 const modal=document.getElementById?.('modal');
 modal?.addEventListener('close',()=>{clearScheduled();cancelled++;},{passive:true});
 root.HunterDemoLoad3816=Object.freeze({version:'38.16',schedule,
   stats:()=>({requested:requests,skipped,cancelled,pending:pending!==null,inFlight:inFlight!==null})});
})(window, document);
