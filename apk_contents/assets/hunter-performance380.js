/* Hunter System V38.0 - per-device performance management (does not alter exercise timers). */
(function(w,d){'use strict';
  const perf={
    version:'38.0',refreshHz:null, callbackHz:null, longTasks:0,drawMs:0,drawCount:0,
    samples:[],lastRaf:0, calibrationDone:false, initialTime:performance.now(),
    powerSaver:false, level:'auto',
    sampleDrawCost(ms){if(!(ms>=0&&ms<300))return;
      this.drawMs=this.drawCount?this.drawMs*.94+ms*.06:ms;
      this.drawCount++;},
    actorInterval(state){
      if(d.hidden||this.powerSaver|| (w.HunterAdaptive3823?.quality==='eco' && state==='idle'))return 1000/24;
      if(w.matchMedia?.('(prefers-reduced-motion: reduce)').matches)return 1000/24;
      const hz=this.refreshHz||60;
      // Cost budget protects the UI from expensive SVG work on slower devices.
      const busy=this.drawCount>20&&this.drawMs>2.7;
      const transitional=state!=='idle'&&state!=='hidden';
      const target=busy?Math.min(45,hz):transitional?Math.min(120,hz):Math.min(60,hz);
      return 1000/Math.max(24,target)-.45;
    },
    observe(){
      if(d.hidden||this.calibrationDone)return;
      const that=this;let n=0,prev=0,frames=[];
      function tick(t){if(d.hidden)return;
        if(prev){const dt=t-prev;if(dt>3&&dt<80)frames.push(dt);}
        prev=t;if(++n<120){w.requestAnimationFrame(tick);return;}
        if(frames.length>=50){
          frames.sort((a,b)=>a-b);let med=frames[Math.floor(frames.length/2)];
          const nominal=[60,75,90,120,144,165];
          const raw=1000/med;
          let guess=nominal.reduce((a,b)=>Math.abs(b-raw)<Math.abs(a-raw)?b:a,nominal[0]);
          // Do not infer high-refresh panel unless measured callbacks support it.
          that.refreshHz=raw>=guess*.9?guess:Math.round(raw);
          that.callbackHz=Math.round(raw);
          that.calibrationDone=true;
          d.documentElement.dataset.fpsCalibrated380='true';
        }
      }
      w.requestAnimationFrame(tick);
    },
    snapshot(){return {refreshEstimateHz:this.refreshHz,callbackEstimateHz:this.callbackHz,
      actorDrawMsAverage:Number(this.drawMs.toFixed(3)), actorSamples:this.drawCount,
      longTasks:this.longTasks,note:'No son FPS reales de pantalla; medir Android con FrameTimeline/Perfetto.'};}
  };
  w.HunterPerf380=perf;
  if(typeof PerformanceObserver==='function')try{
    const observer=new PerformanceObserver(list=>{perf.longTasks+=list.getEntries().length;});
    observer.observe({entryTypes:['longtask']});
    w.addEventListener('pagehide',()=>observer.disconnect(),{once:true});
  }catch(e){}
  if(d.readyState==='loading')d.addEventListener('DOMContentLoaded',()=>perf.observe(),{once:true});else perf.observe();
  d.addEventListener('visibilitychange',()=>{if(!d.hidden){perf.calibrationDone=false;perf.observe();}});
  try{
    if(typeof w.HunterV373?.power==='function'){
      const old=w.HunterV373.power;
      w.HunterV373.power=function(state){perf.powerSaver=state?.powerSave===true;return old(state);};
    }
  }catch(e){}
  // Display only diagnostics already requested by the user; no telemetry or network requests.
  if(typeof w.openDiagnostics20==='function'){
    const old=w.openDiagnostics20;
    w.openDiagnostics20=function(...args){let ret=old.apply(this,args);
      const section=d.querySelector('#modal section');
      if(section){const p=d.createElement('p');p.className='fps-metrics380';p.textContent=
        'Fluidez V38.0: estimación del ritmo de callbacks '+(perf.callbackHz===null?'pendiente':perf.callbackHz+' Hz')+
        '. FPS reales en Android: no medidos. Las animaciones se adaptan a la carga.';
        section.append(p);}
      return ret;
    };
  }
})(window,document);
