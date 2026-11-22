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
