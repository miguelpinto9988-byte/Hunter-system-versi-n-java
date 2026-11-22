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
