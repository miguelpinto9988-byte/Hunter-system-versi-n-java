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

;
/* Hunter System 38.64 | Quality control for existing exercise animations.
   Does not change joint positions or create biomechanics.
   Retains original technical illustration visible when comparative mode is on.
 */
(function(root,doc){'use strict';
 if(root.HunterQuality3864 || typeof technique!=='function' || typeof vectorSeek!=='function')return;
 const flags={"hs379-isobiceps":1,"hs379-palms":1,"new46-abwheel":2,"new46-airplanefree":2,"new46-ankleevert":1,"new46-ankleinvert":1,"new46-bandd1":2,"new46-banddiagonal":2,"new46-bander":2,"new46-bandir":2,"new46-bandplantar":1,"new46-bandpullapart":2,"new46-bigtoestretch":1,"new46-bottomclean":2,"new46-cableer90":2,"new46-chinup":2,"new46-commandopull":2,"new46-copenhagen":1,"new46-crosscabletri":2,"new46-crossshoulder":2,"new46-cubanpress":2,"new46-cubanrotation":2,"new46-deepshift":2,"new46-faceer":2,"new46-forearmlever":1,"new46-goodmorning":2,"new46-halo":2,"new46-hangclean":2,"new46-hangingwiper":2,"new46-hipcarquad":2,"new46-jefferson":2,"new46-kneelchop":2,"new46-kneellift":2,"new46-laddercarioca":2,"new46-landminetwist":2,"new46-lateralhurdle":2,"new46-lsit":1,"new46-mbcatch":2,"new46-mbrotation":2,"new46-meadowsrow":2,"new46-mediannerve":2,"new46-overheadsquat":2,"new46-pallofwalk":2,"new46-pelvictilt":1,"new46-peronealnerve":1,"new46-pivotlunge":2,"new46-pronehipir":2,"new46-reverseclam":2,"new46-ringsrotation":2,"new46-ringturnout":1,"new46-ropealternating":2,"new46-ropecircles":2,"new46-rotationalrow":2,"new46-seatedhinge":2,"new46-seatedpallof":2,"new46-shortfoot":1,"new46-shouldercar":2,"new46-shuttle":2,"new46-sidecrow":2,"new46-sidepallof":2,"new46-sitthrough":2,"new46-supineshoulderir":2,"new46-tdrill":2,"new46-tendonglides":1,"new46-thumbopposition":1,"new46-toetowel":1,"new46-toeyoga":1,"new46-tricepsstretch":1,"new46-trxfly":2,"new46-trxhighrow":2,"new46-tucklever":1,"new46-turkish":2,"new46-turnjump":2,"new46-ulnarnerve":2,"new46-wristcurl":1,"new46-wristdeviation":1,"new46-wristexte":1,"new46-zercher":2};
 const STORAGE='hunter-motion-incidents-3864';
 const toolStats={views:0,steps:0,reports:0};
 const originalTechnique=technique, originalPaint=vectorPaint;
 let activeId=null, activeOutput=null;
 function label(id){const e=typeof E!=='undefined'&&E.byId?E.byId(id):null;return e?.name||id;}
 function numberFrame(){return typeof vectorSession!=='undefined' && vectorSession?Math.max(0,Math.min(47,Math.floor(Number(vectorSession.frame)||0))):0;}
 function refresh(){if(activeOutput&&activeOutput.isConnected)activeOutput.textContent='Fotograma '+(numberFrame()+1)+' de 48';}
 vectorPaint=function(){const value=originalPaint.apply(this,arguments);refresh();return value;};
 function readReports(){try{const v=JSON.parse(root.localStorage.getItem(STORAGE)||'[]');return Array.isArray(v)?v.filter(x=>x&&typeof x==='object').slice(-200):[];}catch{return [];}}
 function persistReports(data){try{root.localStorage.setItem(STORAGE,JSON.stringify(data));return true;}catch{return false;}}
 function make(tag,cls,text){const x=doc.createElement(tag);if(cls)x.className=cls;if(text!==undefined)x.textContent=text;return x;}
 function activateControls(id,panel){
  const target=panel.querySelector('#vectorCanvas');if(!target)return;
  activeId=id;toolStats.views++;
  const flag=flags[id]||0;
  const note=make('div','hunter-quality-badge3864');
  const heading=make('strong','', 'Calidad de la demostración');
  const message=make('span','',flag===3
   ?'Revisión pendiente: modelo con articulaciones principales estáticas y una variación importante de geometría en 2D.'
   :flag===1?'Movimiento principal mínimo o estático. Puede ser una postura sostenida o un gesto localizado; consulta la explicación técnica.'
   :flag===2?'Revisión pendiente: variación importante de la longitud aparente de un segmento en el modelo 2D.'
   :'Animación vectorial disponible. El dibujo es orientativo; su precisión técnica no está certificada.');
  note.append(heading,message);
  const toolbar=make('div','hunter-controls3864');
  const back=make('button','btn ghost hunter-step3864','◀ Fotograma');back.type='button';back.title='Fotograma anterior';
  const index=make('output','hunter-frame3864','Fotograma 1 de 48');index.setAttribute('aria-live','off');
  const next=make('button','btn ghost hunter-step3864','Fotograma ▶');next.type='button';next.title='Fotograma siguiente';
  const reset=make('button','btn ghost hunter-reset3864','Volver al inicio');reset.type='button';
  function step(n){if(typeof vectorSession==='undefined'||!vectorSession||vectorSession.id!==id)return;vectorSeek((numberFrame()+n+48)%48);toolStats.steps++;refresh();}
  back.addEventListener('click',()=>step(-1));next.addEventListener('click',()=>step(1));reset.addEventListener('click',()=>{if(typeof vectorSession!=='undefined'&&vectorSession?.id===id)vectorSeek(0);});
  toolbar.append(back,index,next,reset);panel.insertBefore(note,target);panel.insertBefore(toolbar,target);
  activeOutput=index;refresh();
  const schematic=panel.querySelector('.catalog-motion46');
  if(schematic){
   const technical=make('p','hunter-technical3864','Demostración técnica original · postura, apoyos y equipo');
   schematic.parentElement.insertBefore(technical,schematic);
  }
  const compare=panel.querySelector('.hunter-compare3863');
  if(compare){
   const description=compare.querySelector('.footnote');
   if(description)description.textContent='Comparación ilustrativa basada en los mismos fotogramas. Se mantiene visible el esquema técnico original para comprobar apoyos, equipos y detalles.';
   const status=compare.querySelector('.hunter-compare-status3863');
   if(status)status.setAttribute('role','status');
  }
  const disclosure=make('details','hunter-report3864');
  const summary=make('summary','', '¿Detectaste un movimiento incorrecto? Registrar incidencia');
  const form=make('div','hunter-report-body3864');
  const title=make('p','', 'La incidencia se guarda solo en este dispositivo. Puedes exportar el informe para revisar cada ejercicio; no modifica su técnica.');
  const select=make('select','',undefined);select.setAttribute('aria-label','Tipo de incidencia');
  [['movimiento','El movimiento no coincide'],['postura','Postura o apoyo incorrecto'],['material','Equipo o accesorio ausente'],['estatica','Animación parece estática'],['otro','Otro problema visual']].forEach(([v,t])=>{const opt=make('option','',t);opt.value=v;select.append(opt);});
  const description=make('textarea','',undefined);description.rows=2;description.maxLength=300;description.placeholder='Detalles opcionales para identificar el problema';description.setAttribute('aria-label','Detalles de la incidencia');
  const msg=make('p','hunter-report-status3864','');msg.setAttribute('role','status');
  const save=make('button','btn','Guardar incidencia');save.type='button';
  const exportBtn=make('button','btn ghost','Exportar informes');exportBtn.type='button';
  save.addEventListener('click',()=>{
   const reports=readReports();const entry={id,name:label(id),type:select.value,notes:description.value.trim(),createdAt:new Date().toISOString(),appVersion:'38.64'};
   const idx=reports.findIndex(x=>x.id===id && x.type===entry.type);
   if(idx>=0)reports.splice(idx,1);
   reports.push(entry);
   if(persistReports(reports)){msg.textContent='Incidencia guardada en el dispositivo.';toolStats.reports++;}else msg.textContent='No se pudo guardar en este navegador. Prueba a exportar o copiar el reporte.';
  });
  exportBtn.addEventListener('click',()=>{
   const reports=readReports();if(!reports.length){msg.textContent='Aún no hay incidencias guardadas.';return;}
   const contents=JSON.stringify({project:'Hunter System',version:'38.64',items:reports},null,2);
   try{
    const blob=new Blob([contents],{type:'application/json'}),url=URL.createObjectURL(blob),a=doc.createElement('a');
    a.href=url;a.download='Hunter_incidencias_animaciones.json';doc.body.append(a);a.click();a.remove();root.setTimeout(()=>URL.revokeObjectURL(url),2500);
    msg.textContent='Descarga solicitada. Si tu navegador no la admite, utiliza Copiar informe.';
   }catch{msg.textContent='Descarga no disponible. Usa Copiar informe.';}
  });
  const copy=make('button','btn ghost','Copiar informe');copy.type='button';
  copy.addEventListener('click',async()=>{const contents=JSON.stringify({project:'Hunter System',items:readReports()},null,2);try{await navigator.clipboard.writeText(contents);msg.textContent='Informe copiado al portapapeles.';}catch{msg.textContent='No se pudo copiar. Utiliza Exportar informes.';}});
  const actions=make('div','hunter-report-buttons3864');actions.append(save,exportBtn,copy);
  form.append(title,select,description,actions,msg);disclosure.append(summary,form);panel.append(disclosure);
 }
 technique=function(id){const result=originalTechnique.apply(this,arguments);
  if(typeof id!=='string'||!/^(new46-|hs379-)/.test(id))return result;
  const panel=doc.querySelector('#modal .motion-card18');if(panel)activateControls(id,panel);
  return result;
 };
 root.HunterQuality3864=Object.freeze({audit:()=>({modelsExtended:200,flagsStatic:22,flagsGeometry:56,flagged:78,techniqueVerified:false}),stats:()=>({...toolStats}),saved:()=>readReports().length});
})(window,document);

;
/* Hunter System 38.65: premium anatomical presentation for the verified-display
   subset of the 200 extended models. This file changes presentation only:
   it NEVER modifies source joint data, phases, exercise advice, or the 48 frames.
   Models flagged by the existing audit retain their technical diagram as primary.
*/
(function(root,doc){'use strict';
 if(root.HunterMotion3865||typeof technique!=='function'||!root.HunterComparador3863)return;
 const previousTechnique=technique;
 let shown=0,review=0,loadErrors=0;
 let special=null;
 const previousPaint=vectorPaint;
 vectorPaint=function(){const result=previousPaint.apply(this,arguments);
  if(special&&special.panel.isConnected&&typeof vectorSession!=='undefined'&&vectorSession?.id===special.id)special.draw(false);
  return result;};
 const isExtended=id=>/^(?:new46-|hs379-)/.test(String(id));
 const make=(tag,cls,text)=>{const el=doc.createElement(tag);if(cls)el.className=cls;if(text!==undefined)el.textContent=text;return el;};
 function improvePanel(id){
  const panel=doc.querySelector('#modal .motion-card18');
  if(!panel||panel.dataset.exercisePremium3865===id)return;
  panel.dataset.exercisePremium3865=id;
  const compare=panel.querySelector('.hunter-compare3863');
  const technical=panel.querySelector('.catalog-motion46');
  const QA=panel.querySelector('.hunter-quality-badge3864');
  const canvas=panel.querySelector('#vectorCanvas');
  if(!technical||!canvas)return;
  panel.classList.add('hunter-premium3865');
  const banner=make('div','hunter-premium-banner3865');
  const title=make('div','hunter-premium-banner-title3865');
  const eyebrow=make('span','hunter-premium-eyebrow3865',compare?'DEMOSTRACIÓN ANATÓMICA 2D':'DEMOSTRACIÓN TÉCNICA 2D');
  const heading=make('strong','',compare?'Vista detallada del movimiento':'Visualización bajo revisión');
  title.append(eyebrow,heading);
  const quality=make('span','hunter-premium-marker3865',compare?'48 FOTOGRAMAS':'REVISAR TÉCNICA');
  banner.append(title,quality);
  panel.insertBefore(banner,compare||QA||canvas);
  if(!compare){
   if(id==='hs379-isobiceps'){
    // Curated illustration-only exception: the existing anatomical rendition
    // visually retains the seated pose. It remains an isometric/short-gesture
    // illustration and is NOT represented as a validated dynamic flexion.
    shown++;
    panel.classList.add('hunter-premium-supported3865');
    const hero=make('div','hunter-compare3863 hunter-premium-stage3865 hunter-premium-static3865');
    const image=make('div','motion-svg18 hunter-anatomy3863');image.setAttribute('role','img');
    image.setAttribute('aria-label','Ilustración anatómica de la posición sentada con resistencia propia');
    const description=make('p','footnote','Posición sostenida o recorrido pequeño. No se añade una repetición artificial. Contrasta los puntos de contacto con el esquema original.');
    const label=make('div','hunter-premium-stagecaption3865','VISTA ANATÓMICA · POSTURA SENTADA');
    const status=make('p','footnote hunter-compare-status3863','Preparando ilustración anatómica…');
    const disclosure=make('button','btn ghost hunter-premium-technicalbtn3865','Comparar con el esquema técnico');
    disclosure.type='button';disclosure.setAttribute('aria-expanded','false');
    disclosure.addEventListener('click',()=>{
     const open=panel.classList.toggle('hunter-premium-tech-visible3865');
     disclosure.setAttribute('aria-expanded',String(open));
     disclosure.textContent=open?'Ocultar esquema técnico':'Comparar con el esquema técnico';
    });
    hero.append(image,label,status,description,disclosure);
    panel.insertBefore(hero,QA||canvas);
    const techLabel=panel.querySelector('.hunter-technical3864');if(techLabel)techLabel.classList.add('hunter-premium-source-label3865');
    const session={id,panel,last:-1,assetReady:false,draw(force){
     if(!this.assetReady||!this.panel.isConnected||typeof vectorSession==='undefined'||vectorSession?.id!==id)return;
     const frame=Number(vectorSession.frame)||0;
     if(!force&&Math.abs(frame-this.last)<.8)return;
     this.last=frame;
     try{const svg=root.HunterDemos46.anatomicalSvg(id,frame,!!vectorSession.mirror);if(!svg)return;
      image.innerHTML=svg;const node=image.firstElementChild;node.setAttribute('viewBox','120 45 480 620');
      status.textContent='Imagen orientativa: la posición del brazo puede variar muy poco en este modelo.';
     }catch(e){loadErrors++;status.textContent='No se pudo mostrar la figura. Consulta el esquema técnico.';panel.classList.add('hunter-premium-tech-visible3865');}
    }};
    special=session;
    root.HunterDemos46.load(id).then(()=>{if(special===session&&panel.isConnected){session.assetReady=true;session.draw(true);}})
      .catch(()=>{loadErrors++;status.textContent='No se pudo cargar la ilustración anatómica. Usa el esquema técnico.';panel.classList.add('hunter-premium-tech-visible3865');});
    return;
   }
   review++;
   const warning=make('p','hunter-premium-underreview3865','Este modelo requiere revisión de postura o movimiento. Se muestra su esquema técnico sin crear una animación anatómica que pudiera ser incorrecta.');
   panel.insertBefore(warning,canvas);
   return;
  }
  shown++;
  panel.classList.add('hunter-premium-supported3865');
  const btn=compare.querySelector('.hunter-compare-button3863');
  const image=compare.querySelector('.hunter-anatomy3863');
  const status=compare.querySelector('.hunter-compare-status3863');
  if(!btn||!image)return;
  image.setAttribute('role','img');
  image.setAttribute('aria-label','Figura anatómica ilustrativa sincronizada con los fotogramas del ejercicio');
  // Reserve the main stage for the anatomical model. The technical source
  // remains in the DOM, directly after the canvas as required by 38.46.
  compare.classList.add('hunter-premium-stage3865');
  compare.insertBefore(image,compare.firstChild);
  const stageLabel=make('div','hunter-premium-stagecaption3865','Vista anatómica ilustrativa · misma trayectoria del esquema técnico');
  compare.insertBefore(stageLabel,image.nextSibling);
  const caption=compare.querySelector('.footnote');
  if(caption)caption.textContent='La figura es orientativa. Para comprobar apoyos, accesorios y posiciones exactas, despliega el esquema técnico original.';
  const technicalButton=make('button','btn ghost hunter-premium-technicalbtn3865','Mostrar esquema técnico original');
  technicalButton.type='button';technicalButton.setAttribute('aria-expanded','false');
  technicalButton.addEventListener('click',()=>{
   const enabled=panel.classList.toggle('hunter-premium-tech-visible3865');
   technicalButton.setAttribute('aria-expanded',String(enabled));
   technicalButton.textContent=enabled?'Ocultar esquema técnico':'Comparar con el esquema técnico';
  });
  const technicalLabel=panel.querySelector('.hunter-technical3864');
  if(technicalLabel){technicalLabel.textContent='ESQUEMA TÉCNICO · APOYOS Y EQUIPO';technicalLabel.classList.add('hunter-premium-source-label3865');}
  const controls=panel.querySelector('.hunter-controls3864');
  if(controls)controls.insertAdjacentElement('afterend',technicalButton);
  else panel.insertBefore(technicalButton,canvas);
  if(status)status.setAttribute('aria-live','polite');
  // Compute a *fixed* camera box using every original joint coordinate.
  // Unlike zooming to each frame, a fixed camera never jitters or invents poses.
  let camera=null, zoomed=true;
  function clamp(v,a,b){return Math.min(b,Math.max(a,v));}
  function drawCamera(){
   const svg=image.querySelector('svg');if(!svg)return;
   if(!svg.dataset.originalView3865)svg.dataset.originalView3865=svg.getAttribute('viewBox')||'0 0 720 1000';
   if(zoomed&&camera)svg.setAttribute('viewBox',camera);
  }
  const cameraObserver=new MutationObserver(m=>{
   if(!panel.isConnected){cameraObserver.disconnect();return;}
   if(m.some(x=>x.type==='childList'))drawCamera();
  });
  cameraObserver.observe(image,{childList:true});
  const framing=make('button','btn ghost hunter-premium-framing3865','Mostrar encuadre completo');
  framing.type='button';framing.setAttribute('aria-pressed','true');
  framing.addEventListener('click',()=>{
   zoomed=!zoomed;
   framing.textContent=zoomed?'Mostrar encuadre completo':'Ampliar figura';
   framing.setAttribute('aria-pressed',String(zoomed));
   const svg=image.querySelector('svg');if(svg)svg.setAttribute('viewBox',zoomed&&camera?camera:svg.dataset.originalView3865);
  });
  compare.append(framing);
  // Build a conservative visual crop. Padding covers head, hands, equipment,
  // and the complete range of motion; the crop is only a viewport property.
  root.HunterDemos46.load(id).then(asset=>{
   if(!asset?.model?.frames || !panel.isConnected)return;
   const points=[];
   for(const f of asset.model.frames){
    if(Array.isArray(f.p))for(const q of f.p)if(Array.isArray(q)&&q.length>=2&&q.every(Number.isFinite))points.push(q);
    if(Array.isArray(f.loads))for(const q of f.loads)if(Array.isArray(q)&&q.length>=2&&q.every(Number.isFinite))points.push(q);
    if(Array.isArray(f.equipment))for(const item of f.equipment){
     const r=item?.[1];if(Array.isArray(r)&&r.length===4&&r.every(Number.isFinite))points.push([r[0],r[1]],[r[2],r[3]]);
    }
   }
   if(!points.length)return;
   const loX=Math.min(...points.map(p=>p[0])),hiX=Math.max(...points.map(p=>p[0]));
   const loY=Math.min(...points.map(p=>p[1])),hiY=Math.max(...points.map(p=>p[1]));
   let w=Math.max(285,hiX-loX+150),h=Math.max(400,hiY-loY+150);
   w=Math.max(w,h*.84);h=Math.max(h,w/.84);
   if(w>720){w=720;h=Math.max(h,w/.84);}if(h>850){h=850;w=Math.min(720,Math.max(w,h*.84));}
   const centerX=clamp((loX+hiX)/2,w/2,720-w/2);
   const centerY=clamp((loY+hiY)/2,h/2,850-h/2);
   camera=[centerX-w/2,centerY-h/2,w,h].map(x=>Number(x.toFixed(2))).join(' ');
   drawCamera();
  }).catch(()=>{
   loadErrors++;
   panel.classList.add('hunter-premium-tech-visible3865');
   technicalButton.textContent='Ocultar esquema técnico';
   technicalButton.setAttribute('aria-expanded','true');
  });
  // The original 38.63 button activates live frame synchronization.
  // Do not rebuild, resample or interpolate the source movement.
  try{btn.click();if(btn.getAttribute('aria-pressed')!=='true')loadErrors++;}
  catch(e){loadErrors++;panel.classList.remove('hunter-premium-supported3865');}
  btn.textContent='Ver solo esquema técnico';
  btn.classList.add('hunter-premium-switch3865');
  btn.setAttribute('title','Cambiar entre figura anatómica y esquema técnico original');
  btn.addEventListener('click',()=>{
   // The original handler has already changed aria-pressed synchronously.
   const anatomyOn=btn.getAttribute('aria-pressed')==='true';
   btn.textContent=anatomyOn?'Ver solo esquema técnico':'Volver a figura anatómica';
   if(!anatomyOn){
    panel.classList.add('hunter-premium-tech-visible3865');
    technicalButton.textContent='Ocultar esquema técnico';
    technicalButton.setAttribute('aria-expanded','true');
   } else {
    panel.classList.remove('hunter-premium-tech-visible3865');
    technicalButton.textContent='Comparar con el esquema técnico';
    technicalButton.setAttribute('aria-expanded','false');
   }
  });
  technicalButton.textContent='Comparar con el esquema técnico';
 }
 technique=function(id){const result=previousTechnique.apply(this,arguments);
  if(isExtended(id))improvePanel(id);
  return result;
 };
 root.HunterMotion3865=Object.freeze({stats:()=>({detailedOpened:shown,underReviewOpened:review,loadErrors}),version:'38.65',techniqueValidated:false});
})(window,document);

;
/* Hunter System 38.66 — inspection of the existing exercise motion.
 * This layer does not modify trajectories, exercise recommendations, original
 * SVG contents, or catalogue records. For review-flagged demos, it allows an
 * opt-in crop onto the moving joint of the ORIGINAL technical SVG, and it
 * reports the measurable change of pose across the supplied frames.
 */
(function(root, doc){'use strict';
 if(root.HunterMotion3866 || !root.HunterMotion3865 || !root.HunterDemos46 || typeof technique!=='function')return;
 const oldTechnique=technique;
 const stats={opened:0,focused:0,loadFailures:0,flaggedOpened:0,minimalMotion:0};
 const SVGNS='http://www.w3.org/2000/svg';
 const clamp=(n,a,b)=>Math.min(b,Math.max(a,n));
 const validPoint=p=>Array.isArray(p)&&p.length>=2&&Number.isFinite(p[0])&&Number.isFinite(p[1]);
 const make=(tag,cls,text)=>{const e=doc.createElement(tag);if(cls)e.className=cls;if(text!==undefined)e.textContent=text;return e;};
 const extended=id=>typeof id==='string'&&/^(?:new46-|hs379-)/.test(id);
 const moveSet=(model)=>{
   const frames=Array.isArray(model?.vectorFrames46)?model.vectorFrames46:[];
   if(frames.length<48||frames.length>512)return null;
   const joints=frames.map(x=>x?.j||{});
   const anchorName=joints.every(j=>validPoint(j.pelvis))?'pelvis':
     joints.every(j=>validPoint(j.midspine))?'midspine':null;
   if(!anchorName)return null;
   const names=['wristL','wristR','handL','handR','thumbL','thumbR','elbowL','elbowR',
     'shoulderL','shoulderR','hipL','hipR','kneeL','kneeR','ankleL','ankleR',
     'toeL','toeR','heelL','heelR','head','neck'];
   const ranked=[];
   for(const name of names){
    if(!joints.every(j=>validPoint(j[name])))continue;
    const relative=joints.map(j=>[j[name][0]-j[anchorName][0],j[name][1]-j[anchorName][1]]);
    const baseline=relative[0],range=Math.max(...relative.map(p=>Math.hypot(p[0]-baseline[0],p[1]-baseline[1])));
    if(!Number.isFinite(range))continue;
    ranked.push({name,range,relative});
   }
   ranked.sort((a,b)=>b.range-a.range);
   if(!ranked.length)return null;
   const most=ranked[0];
   // A conservative fixed camera contains the WHOLE animated figure,
   // including head, feet, hands and supports. Avoid framing only one joint:
   // doing so can hide technique-relevant context in full-body movements.
   const all=[];
   for(const j of joints)for(const p of Object.values(j))if(validPoint(p))all.push(p);
   for(const f of (Array.isArray(model.frames)?model.frames:[])){
    if(Array.isArray(f.p))for(const p of f.p)if(validPoint(p))all.push(p);
    if(Array.isArray(f.loads))for(const p of f.loads)if(validPoint(p))all.push(p);
   }
   const loX=Math.min(...all.map(p=>p[0])),hiX=Math.max(...all.map(p=>p[0]));
   const loY=Math.min(...all.map(p=>p[1])),hiY=Math.max(...all.map(p=>p[1]));
   let w=Math.max(510,hiX-loX+90,(hiY-loY+90)*(720/610));
   w=Math.min(720,w);
   const h=w*(610/720);
   const cx=clamp((loX+hiX)/2,w/2,720-w/2);
   const cy=clamp((loY+hiY)/2,h/2,610-h/2);
   const viewBox=[cx-w/2,cy-h/2,w,h].map(v=>Number(v.toFixed(2))).join(' ');
   // A near-full box is not an actual useful zoom.
   const zoomAvailable=w<704;
   // We expose numeric estimates, not a statement about biomechanical validity.
   return {name:most.name,span:Number(most.range.toFixed(1)),viewBox,zoomAvailable,samples:most.relative,
     minimal:most.range<12,consistent:ranked.length};
 };
 function motionChart(data){
  const svg=doc.createElementNS(SVGNS,'svg');svg.setAttribute('viewBox','0 0 300 54');
  svg.setAttribute('role','img');svg.setAttribute('aria-label','Variación relativa del punto móvil en la trayectoria vectorial del modelo');
  const p=data.samples,orig=p[0],vals=p.map(q=>Math.hypot(q[0]-orig[0],q[1]-orig[1]));
  const max=Math.max(1,...vals);
  const d=vals.map((v,i)=>(i?'L':'M')+(8+i*(284/(vals.length-1))).toFixed(1)+','+(46-36*v/max).toFixed(1)).join(' ');
  const base=doc.createElementNS(SVGNS,'path');base.setAttribute('d','M8 46H292');base.setAttribute('class','hunter-motion-chart-base3866');
  const line=doc.createElementNS(SVGNS,'path');line.setAttribute('d',d);line.setAttribute('class','hunter-motion-chart-line3866');
  svg.append(base,line);return svg;
 }
 function attach(id){
  const panel=doc.querySelector('#modal .motion-card18.hunter-premium3865');
  if(!panel||panel.dataset.hunter3866===id)return;
  panel.dataset.hunter3866=id;
  stats.opened++;
  const warning=panel.querySelector('.hunter-premium-underreview3865');
  if(!warning)return; // The 123 anatomical view models stay unchanged.
  stats.flaggedOpened++;
  const canvas=panel.querySelector('#vectorCanvas');
  const host=panel.querySelector('.catalog-motion46');
  if(!canvas||!host)return;
  warning.textContent='Representación técnica en revisión: no se ha confirmado la postura ni el recorrido. Puedes inspeccionar el desplazamiento original sin modificar la animación.';
  const wrapper=make('section','hunter-motion-focus3866');
  wrapper.setAttribute('aria-label','Análisis del movimiento disponible');
  const info=make('div','hunter-motion-focus-info3866');
  const main=make('strong','', 'Inspección del movimiento');
  const detail=make('p','hunter-motion-focus-detail3866','Analizando los fotogramas y las muestras vectoriales disponibles…');
  detail.setAttribute('role','status');detail.setAttribute('aria-live','polite');
  info.append(main,detail);
  const controls=make('div','hunter-motion-focus-actions3866');
  const focus=make('button','btn ghost','Ampliar la zona de movimiento');focus.type='button';focus.disabled=true;
  focus.setAttribute('aria-pressed','false');
  const reset=make('button','btn ghost','Restaurar vista completa');reset.type='button';reset.disabled=true;
  controls.append(focus,reset);wrapper.append(info,controls);
  panel.insertBefore(wrapper,canvas);
  let original=null,active=false,viewBox=null;
  function getMain(){return host.querySelector('svg:not(.detail-svg31)');}
  function restore(){
   const svg=getMain();if(svg&&original)svg.setAttribute('viewBox',original);
   active=false;focus.setAttribute('aria-pressed','false');focus.textContent='Ampliar la zona de movimiento';reset.disabled=true;
  }
  focus.addEventListener('click',()=>{
   const svg=getMain();if(!svg||!viewBox)return;
   if(!original)original=svg.getAttribute('viewBox')||'0 0 720 610';
   active=!active;
   svg.setAttribute('viewBox',active?viewBox:original);
   focus.setAttribute('aria-pressed',String(active));
   focus.textContent=active?'Volver al encuadre completo':'Ampliar la zona de movimiento';
   reset.disabled=!active;
   if(active)stats.focused++;
  });
  reset.addEventListener('click',restore);
  root.HunterDemos46.load(id).then(asset=>{
   if(!panel.isConnected||panel.dataset.hunter3866!==id||!doc.getElementById("modal")?.contains(panel))return;
   const model=asset?.model,move=moveSet(model);
   const node=getMain();if(node&&!original)original=node.getAttribute('viewBox')||'0 0 720 610';
   if(!move){detail.textContent='No se pudo calcular el área de movimiento. Consulta el SVG original.';return;}
   viewBox=move.zoomAvailable?move.viewBox:null;
   const category=move.minimal?'Desplazamiento mínimo o postura sostenida':'Movimiento registrado en los fotogramas';
   detail.textContent=category+' · Punto destacado: '+move.name+' · '+move.samples.length+' muestras · Variación relativa máxima: '+move.span+' unidades gráficas. No equivale a una evaluación técnica.';
   wrapper.append(motionChart(move));
   if(move.minimal){stats.minimalMotion++;const n=make('p','hunter-motion-static3866','La figura puede parecer inmóvil aunque el archivo tenga animaciones. No se añaden repeticiones ni recorridos artificiales.');wrapper.append(n);}
   if(node && viewBox){focus.disabled=false;reset.disabled=true;}
   else if(!viewBox){detail.textContent+=' Se conserva la vista completa para no recortar los apoyos y las extremidades.';}
   else{detail.textContent+=' El SVG original todavía no está disponible.';}
  }).catch(()=>{
   stats.loadFailures++;
   detail.textContent='No se pudo cargar el recurso local. Comprueba que la APK tenga completos los archivos de demostración.';
  });
 }
 technique=function(id){
  const result=oldTechnique.apply(this,arguments);
  if(extended(id))attach(id);
  return result;
 };
 root.HunterMotion3866=Object.freeze({version:'38.67',motionPlan:moveSet,stats:()=>({...stats}),relativeChart:true,techniqueValidated:false});
})(window,document);

