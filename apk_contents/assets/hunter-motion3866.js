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
