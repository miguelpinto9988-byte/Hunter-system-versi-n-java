/* Hunter System V37.5 · Open-ended, explicit animation cycle timing. */
(function(root){'use strict';
 const clamp=(n,a,b)=>Math.max(a,Math.min(b,n));
 function duration(id){const sec=Number(root.E?.byId(id)?.tempoSeconds18);return Number.isFinite(sec)&&sec>=.1&&sec<=120?sec:4;}
 function at(startPhase,startTime,now,seconds,rate){
  if(![startPhase,startTime,now,seconds,rate].every(Number.isFinite)||seconds<=0)return 0;
  return ((startPhase+(Math.max(0,now-startTime)/1000)*48/seconds*rate)%48+48)%48;
 }
 function format(sec){return Number(sec.toFixed(2)).toLocaleString('es-PE',{maximumFractionDigits:2})+' s';}
 const timing={duration,at,format};root.HunterMotion375=timing;
 // DemoLoop373 previously capped elapsed time at 100ms per frame; on slower
 // tablets that stretched a "3 s" repetition. Clock elapsed uses real active time.
 const loop=root.DemoLoop373; // top-level 'const DemoLoop373' is also window-visible in this runtime.
 if(typeof DemoLoop373!=='undefined'){
  const engine=DemoLoop373,olderSync=engine.sync.bind(engine);
   engine.sync=function(){if(this.entry&&(root.HunterV373?.runtime.hidden||!this.entry.visible))this.entry.clock375=null;return olderSync();};
  engine.tick=function(now){
   this.frame=0;const entry=this.entry;
   if(!this.valid()){this.stop();return;}
   if(root.HunterV373?.runtime.hidden||!entry.visible){entry.clock375=null;return;}
   if(entry.kind==='vector'){
    const s=vectorSession,seconds=duration(s.id),rate=s.speed;
    if(!entry.clock375||entry.clock375.seconds!==seconds||entry.clock375.rate!==rate){entry.clock375={time:now,phase:s.frame,seconds,rate};}
    s.frame=at(entry.clock375.phase,entry.clock375.time,now,seconds,rate);
    s.last=now;vectorPaint();
   }else{
    const g=guide11,step=g.steps[g.index],seconds=duration(step.id),rate=MotionPlayback.rate;
    if(!entry.clock375||entry.clock375.seconds!==seconds||entry.clock375.rate!==rate||entry.clock375.id!==step.id){entry.clock375={time:now,phase:g.demoFrame,seconds,rate,id:step.id};}
    g.demoFrame=at(entry.clock375.phase,entry.clock375.time,now,seconds,rate);
    g.demoLast=now;drawVector(entry.canvas,step.id,g.demoFrame,step.side==='left');
   }
   this.draws++;this.sync();
  };
 }
 // Cycle durations still drive the animation clock, but the separate
 // "segundos por ciclo" badges are deliberately removed from ALL demos.
 // Keep HunterMotion375.duration/at available to the existing controllers.
})(window);
