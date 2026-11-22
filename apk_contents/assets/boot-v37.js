/* Arranque único: la interfaz se revela después de instalar el diseño final. */
(function(root){'use strict';
 let committed=false,finished=false,inflight=null,error=null;
 const scripts=new Set();
 function stage(message,ratio){const el=document.getElementById('boot-shell20');if(!el)return;const text=el.querySelector('.boot-message30'),bar=el.querySelector('progress');if(text)text.textContent=message;if(bar)bar.value=Math.max(0,Math.min(1,ratio));}
 function fail(){error='No se pudo preparar el sistema. Tus registros guardados se conservan.';stage(error,0);const b=document.querySelector('.boot-retry30');if(b)b.hidden=false;}
 document.addEventListener('load',event=>{if(event.target.tagName!=='SCRIPT')return;scripts.add(event.target.src);const count=document.querySelectorAll('script[defer][src]').length;stage('Preparando tu sistema…',.85*scripts.size/Math.max(1,count));},true);
 root.addEventListener('error',event=>{if(!finished&&(event.target?.tagName==='SCRIPT'||event.message))fail();},true);
 const bounded=work=>Promise.race([Promise.resolve(work),new Promise(resolve=>setTimeout(resolve,2000))]);
 async function complete(){
  if(!committed||finished||error)return;
  if(inflight)return inflight;
  inflight=(async()=>{try{
   if(!(root.Motion30?.count()>=1000)||!document.querySelector('#app')?.children.length)throw Error('Inicio incompleto');
   stage('Preparando el diseño de tu perfil…',.93);
   const fonts=document.fonts?.load?Promise.all(['400 24px "Hunter Display"','400 18px "Hunter Text"','600 18px "Hunter Text"'].map(f=>document.fonts.load(f))):Promise.resolve();
   const scene=HunterV33.Visual33.selection(),paths=['ui34/awakening30.png',scene.asset.path,...(state.profile?[scene.figure,HunterV33.Visual33.companionPath()]:[])];
   const art=Promise.all(paths.map(path=>HunterV34.Hero34.decode(path)));
   await Promise.all([bounded(fonts),bounded(art)]);
   HunterV28.Evolution28.layout(scene);HunterV26.UI26.decorate(document);
   await new Promise(resolve=>requestAnimationFrame(()=>requestAnimationFrame(resolve)));
   if(error)return;finished=true;stage('Sistema preparado',1);
   document.documentElement.classList.remove('booting20');document.documentElement.classList.add('ready20');
   const shell=document.getElementById('boot-shell20');if(shell){shell.classList.add('boot-complete30');shell.setAttribute('aria-hidden','true');setTimeout(()=>shell.remove(),matchMedia('(prefers-reduced-motion: reduce)').matches?0:220);}
   Runtime20.startup37.reveals++;Runtime20.startup37.readyAt=performance.now();
  }catch(e){fail();}finally{inflight=null;}})();return inflight;
 }
 const boot={stage,complete,retry:()=>location.reload(),commit(){committed=true;},version:37,get committed(){return committed;},get finished(){return finished;}};
 root.Boot30=boot;root.Boot37=boot;
})(window);
