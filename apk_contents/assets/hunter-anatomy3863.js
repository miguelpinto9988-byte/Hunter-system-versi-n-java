/* Hunter System 38.63 — comparative anatomical motion, never a technique validator.
 * Uses original 48-frame joint data through Motion2D18's existing renderer.
 * Technical source SVG is preserved as default and available at all times.
 */
(function(root,document){'use strict';
 if(root.HunterComparador3863 || typeof technique!=='function' || !root.HunterDemos46)return;
 const originalTechnique=technique;
 const originalPaint=vectorPaint;
 const requiresReview=new Set(["hs379-isobiceps", "hs379-palms", "new46-abwheel", "new46-airplanefree", "new46-ankleevert", "new46-ankleinvert", "new46-bandd1", "new46-banddiagonal", "new46-bander", "new46-bandir", "new46-bandplantar", "new46-bandpullapart", "new46-bigtoestretch", "new46-bottomclean", "new46-cableer90", "new46-chinup", "new46-commandopull", "new46-copenhagen", "new46-crosscabletri", "new46-crossshoulder", "new46-cubanpress", "new46-cubanrotation", "new46-deepshift", "new46-faceer", "new46-forearmlever", "new46-goodmorning", "new46-halo", "new46-hangclean", "new46-hangingwiper", "new46-hipcarquad", "new46-jefferson", "new46-kneelchop", "new46-kneellift", "new46-laddercarioca", "new46-landminetwist", "new46-lateralhurdle", "new46-lsit", "new46-mbcatch", "new46-mbrotation", "new46-meadowsrow", "new46-mediannerve", "new46-overheadsquat", "new46-pallofwalk", "new46-pelvictilt", "new46-peronealnerve", "new46-pivotlunge", "new46-pronehipir", "new46-reverseclam", "new46-ringsrotation", "new46-ringturnout", "new46-ropealternating", "new46-ropecircles", "new46-rotationalrow", "new46-seatedhinge", "new46-seatedpallof", "new46-shortfoot", "new46-shouldercar", "new46-shuttle", "new46-sidecrow", "new46-sidepallof", "new46-sitthrough", "new46-supineshoulderir", "new46-tdrill", "new46-tendonglides", "new46-thumbopposition", "new46-toetowel", "new46-toeyoga", "new46-tricepsstretch", "new46-trxfly", "new46-trxhighrow", "new46-tucklever", "new46-turkish", "new46-turnjump", "new46-ulnarnerve", "new46-wristcurl", "new46-wristdeviation", "new46-wristexte", "new46-zercher"]);
 const warnings=new Set(["hs379-isobiceps", "hs379-palms", "new46-ankleevert", "new46-ankleinvert", "new46-bandplantar", "new46-bigtoestretch", "new46-copenhagen", "new46-forearmlever", "new46-lsit", "new46-pelvictilt", "new46-peronealnerve", "new46-ringturnout", "new46-shortfoot", "new46-tendonglides", "new46-thumbopposition", "new46-toetowel", "new46-toeyoga", "new46-tricepsstretch", "new46-tucklever", "new46-wristcurl", "new46-wristdeviation", "new46-wristexte"]);
 let active=null;
 const stats={opened:0,frames:0,failures:0};
 function update(force){
  const session=active;
  if(!session || !session.panel.isConnected || !session.on)return;
  const player=typeof vectorSession!=='undefined'?vectorSession:null;
  if(!player || player.id!==session.id)return;
  const frame=Number(player.frame)||0;
  const now=performance.now();
  if(!force && now-session.lastAt<85 && Math.abs(frame-session.lastFrame)<1.1)return;
  session.lastFrame=frame;session.lastAt=now;
  try{
   const markup=root.HunterDemos46.anatomicalSvg(session.id,frame,!!player.mirror);
   if(!markup){session.status.textContent='Cargando el modelo local…';return;}
   session.preview.innerHTML=markup;
   session.status.textContent=warnings.has(session.id)
    ?'Modelo con articulaciones principales estáticas; los movimientos pequeños y las contracciones sostenidas requieren consultar el esquema original.'
    :'Vista ilustrativa basada en los fotogramas originales. El equipo y los apoyos deben comprobarse en el esquema técnico.';
   stats.frames++;
  }catch(e){
   session.status.textContent='La vista anatómica no está disponible para este modelo. Se conserva el esquema técnico.';
   stats.failures++;
   session.toggle(false);
  }
 }
 vectorPaint=function(){const result=originalPaint.apply(this,arguments);update(false);return result;};
 technique=function(id){
  if(active){active=null;}
  const response=originalTechnique.apply(this,arguments);
  if(!/^(?:new46-|hs379-)/.test(id))return response;
  const panel=document.querySelector('#modal .motion-card18');
  const canvas=panel?.querySelector('#vectorCanvas');
  if(!canvas || typeof root.HunterDemos46.anatomicalSvg!=='function')return response;
  if(requiresReview.has(id)){
   const note=document.createElement('p');note.className='footnote hunter-quality-warning3863';
   note.textContent=warnings.has(id)
    ?'Demostración con articulaciones principales estáticas o movimiento localizado: conserva el esquema técnico y revisa las instrucciones. No se simulan movimientos inexistentes.'
    :'La reconstrucción anatómica de este ejercicio necesita revisión de postura, apoyos o geometría. Se conserva la demostración técnica original.';
   panel.insertBefore(note,canvas);return response;
  }
  const toolbar=document.createElement('div');toolbar.className='hunter-compare3863';
  const button=document.createElement('button');button.type='button';button.className='btn hunter-compare-button3863';
  button.textContent='Ver figura anatómica detallada';button.setAttribute('aria-pressed','false');
  const description=document.createElement('p');description.className='footnote';
  description.textContent='Comparación opcional. El esquema original es la referencia para los apoyos, el equipo y el movimiento localizado.';
  const preview=document.createElement('div');preview.className='motion-svg18 hunter-anatomy3863';preview.hidden=true;
  preview.setAttribute('aria-label','Vista anatómica ilustrativa del movimiento');
  const status=document.createElement('p');status.className='footnote hunter-compare-status3863';status.hidden=true;
  toolbar.append(button,description,preview,status);panel.insertBefore(toolbar,canvas);
  const session={id,panel,preview,status,button,on:false,lastAt:0,lastFrame:-2,toggle:null};
  const toggle=(on)=>{
   if(active!==session)return;
   session.on=Boolean(on);
   panel.classList.toggle('hunter-anatomy-on3863',session.on);
   preview.hidden=!session.on;status.hidden=!session.on;
   button.setAttribute('aria-pressed',String(session.on));
   button.textContent=session.on?'Volver al esquema técnico original':'Ver figura anatómica detallada';
   if(session.on){
    root.HunterDemos46.load(id).then(()=>{if(active===session&&session.on)update(true);})
      .catch(()=>{if(active===session){status.textContent='No se pudo cargar la vista comparativa.';toggle(false);}});
    stats.opened++;update(true);
   }else{
    preview.replaceChildren();
   }
  };
  session.toggle=toggle;active=session;
  button.addEventListener('click',()=>toggle(!session.on));
  return response;
 };
 root.HunterComparador3863=Object.freeze({stats:()=>({...stats}),audit:()=>({extended:200,noMainJointDisplacement:warnings.size,compareAvailable:200-requiresReview.size,underReview:requiresReview.size}),reset:()=>{if(active)active.toggle(false);}});
})(window,document);
