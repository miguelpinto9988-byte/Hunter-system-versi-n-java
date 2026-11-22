/* Hunter System 38.48: measured work reduction, unchanged exercise motions.
 * Runtime counters are local diagnostics, not Android power/FPS measurements.
 */
(function(root,doc){'use strict';
 if(root.HunterOptimize48)return;
 const stats={minuteQueries:0,minuteFallbacks:0,coalescedLayouts:0,coalescedThemes:0,renders:0,offscreenPauses:0};
 const decorations=new Set();
 const decorationObserver=typeof IntersectionObserver==='function'?new IntersectionObserver(rows=>{
  for(const row of rows){const pause=!row.isIntersecting;row.target.classList.toggle('hunter-offscreen48',pause);if(pause)stats.offscreenPauses++;}
 },{rootMargin:'100px'}):null;
 function syncDecorations(){
  if(!decorationObserver)return;
  for(const node of decorations)if(!node.isConnected){decorationObserver.unobserve(node);decorations.delete(node);}
  for(const node of doc.querySelectorAll('#app .monarch-hero33'))if(!decorations.has(node)){decorations.add(node);decorationObserver.observe(node);}
 }
 let renderDepth=0,pendingSelection=null,lastTheme=null;
 const legacyLayout=Evolution28.layout,legacyTheme=applyTheme,legacyRender=render;
 Evolution28.layout=function(selection=this.current||this.selection()){
  if(renderDepth){this.current=selection;pendingSelection=selection;stats.coalescedLayouts++;return;}
  return legacyLayout.call(this,selection);
 };
 applyTheme=function(p=state.profile||{},prefs=Visual33.preferences()){
  const key=renderDepth?JSON.stringify([p,prefs]):null;
  if(key&&key===lastTheme){stats.coalescedThemes++;return;}
  if(renderDepth)lastTheme=key;
  return legacyTheme.apply(this,arguments);
 };
 render=function(){
  if(Runtime20.initializing)return;
  if(!renderDepth){lastTheme=null;pendingSelection=null;}
  renderDepth++;
  try{return legacyRender.apply(this,arguments);}
  finally{
   if(!--renderDepth){
    if(pendingSelection){const selection=pendingSelection;pendingSelection=null;legacyLayout.call(Evolution28,selection);}
    syncDecorations();
    stats.renders++;
   }
  }
 };

 // Duration is an aggregate, not a reason to allocate every session block and
 // every set. Keep the legacy expander for source sequences and malformed data.
 const legacyMinutes=Warm25.minutes;
 Warm25.minutes=function(r,p){
  stats.minuteQueries++;
  if(r.sourceSequence||!Array.isArray(r.items)||r.items.some(x=>!Number.isInteger(x.sets)||x.sets<1||!Engine.byId(x.id))){stats.minuteFallbacks++;return legacyMinutes.apply(this,arguments);}
  if(!r.items.length)return 0;
  let seconds=0,sets=0,rounds=0;
  for(const x of r.items){
   sets+=x.sets;rounds=Math.max(rounds,x.sets);
   seconds+=x.sets*(Engine.unitOf(x)==='sec'?x.target:Math.ceil(OwnRoutines18.workSeconds(x)));
  }
  seconds+=Math.max(0,(BlockSession22.mode(p)==='rounds'?rounds:sets)-1)*TrainingProgression19.rest(p||{});
  const warm=Warm24.select({title:r.name,items:r.items,sourceSequence:r.sourceSequence},p||{});
  seconds+=warm&&!warm.blocked?Warm24.step(warm.warm,'preparation',r.items.length).seconds+Warm24.step(warm.cool,'cooldown',r.items.length).seconds:warm?.blocked?60:120;
  return Math.ceil(seconds/60);
 };

 // Runtime373 already implements ambient scroll motion with compositor layers;
 // its old per-scroll numeric impulse is an empty compatibility handler.
 doc.removeEventListener('scroll',ambientScroll3817,true);
 if(scrollFrame3817)cancelAnimationFrame(scrollFrame3817);
 scrollFrame3817=pendingScroll3817=0;

 root.HunterOptimize48=Object.freeze({version:'38.48',compositedAmbient:true,
  legacyMinutes,snapshot:()=>({...stats,observedDecorations:decorations.size,svg:root.SvgRenderer373?.snapshot48?.(),startup:Runtime20.startup37})});

 // All migrations, 1,200 records, safety filters and final UI hooks now exist.
 // Earlier version modules may initialize data but do not build hidden screens.
 Runtime20.initializing=false;
 state.appVersion=38.78;
 applyTheme();render();save();Boot37.commit();
 if(doc.readyState!=='loading')Boot37.complete();
})(window,document);
