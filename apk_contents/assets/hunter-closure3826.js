/* Hunter System V38.26 -- cierre funcional localizado.
   Aceptadas: 35 (indicador de fase), 58 (sustitución ANTES de comenzar),
   75 (desafío opcional de registro sin XP). NO implementa 66 (cambio en sesión).
   Sin diálogos adicionales, ni imagen/video/catálogo nuevo.
*/
(function(root,doc){
 'use strict';
 if(root.HunterClosure3826) return;
 const stats={phaseUpdates:0,missions:0,swaps:0,blockedSwaps:0};
 const escText=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const isActive=l=>!!(l&&(l.complete||l.pain||l.manual||Number(l.rpe)>0||String(l.note||'').trim().length>0||l.missionTasks?.some(t=>t.done)||l.items?.some(x=>x?.selfReported||x?.missionState||x?.actual?.some(a=>a.done))));
 const isRestricted=p=>!!(p?.injuries?.some(x=>x.stage!=='past')||p?.health?.pain&&p.health.pain!=='none'||p?.health?.acute||p?.limits||p?.health?.special);
 const eligible=(e,p)=>{
   if(!e||!p||!root.HunterMaterials3814?.eligible?.(e,p))return false;
   if(isRestricted(p))return root.HunterConstraints3811?.allowed?.(e,p)===true;
   try {return !!root.Adapt?.allowed?.(e,p);} catch(_){return false;}
 };
 function markPhase(){
   const element=doc.querySelector('#vectorPhase');
   if(!element||!element.textContent)return;
   let content=element.textContent.replace(/^[\u2190-\u21ff\u2794]\s+/u,'');
   const arrow=/\b(regreso|retorno|volver|vuelta)\b/i.test(content)?'↩':
     /\b(movimiento|avance|ejecuci[oó]n|desplazamiento)\b/i.test(content)?'➜':'';
   const updated=arrow?arrow+' '+content:content;
   if(element.textContent!==updated){element.textContent=updated;stats.phaseUpdates++;}
 }
 if(typeof vectorPaint==='function') {
   const original=vectorPaint;
   vectorPaint=function(){const result=original.apply(this,arguments);markPhase();return result;};
 }
 function prepareChallenge(d){
   if(!d||d.kind==='rest'||d.date!==root.Engine?.dateKey?.())return '';
   const record=state?.logs?.[d.date];
   if(record?.pain || isRestricted(state?.profile))return '';
   const completed=record?.complete===true&&Number(record.rpe)>=1&&Number(record.rpe)<=7;
   // No prescribe ejercicios ni genera XP; solo reconoce seguimiento ya realizado.
   stats.missions++;
   return '<small class="hunter-challenge3826" aria-live="off">'+(completed?
     '◇ Desafío opcional: registro de esfuerzo completado ✓':
     '◇ Desafío opcional: registra tu esfuerzo al terminar · sin ejercicios extra')+'</small>';
 }
 function availableSwaps(d,index){
   const current=d?.items?.[index];
   const p=state?.profile;
   const catalog=root.Engine?.exercises;
   if(!p||!Array.isArray(catalog)||!current)return [];
   const initial=root.Engine.byId(current.id);
   if(!initial)return [];
   return catalog.filter(e=>e&&e.id!==initial.id&&e.category===initial.category&&e.group===initial.group&&e.unit===initial.unit&&
     !d.items.some((x,j)=>j!==index&&x.id===e.id)&&!e.sourceOnly&&eligible(e,p)).slice(0,12);
 }
 function futureSwaps(d){
   if(!d||!Array.isArray(d.items)||!d.items.length||!/^\d{4}-\d\d-\d\d$/.test(d.date)||
       d.date<=root.Engine?.dateKey?.()||d.routineId||
       (d.customized&&!d.prePlanSwap3826)||isActive(state?.logs?.[d.date])) return '';
   if(!root.Adapt?.mode?.(state?.profile)?.auto)return '';
   const rows=d.items.map((item,index)=>{
     const alts=availableSwaps(d,index);
     if(!alts.length)return '';
     const e=root.Engine.byId(item.id);
     return '<label class="hunter-swaprow3826">'+escText(e.name)+' <span> · cambiar antes de empezar</span><select data-swap-index="'+index+'" aria-label="Alternativa para '+escText(e.name)+'">'+
       '<option value="">Conservar ejercicio</option>'+alts.map(a=>'<option value="'+escText(a.id)+'">'+escText(a.name)+'</option>').join('')+
       '</select></label>';
   }).filter(Boolean);
   if(!rows.length)return '';
   return '<div class="hunter-preswap3826" data-swap-date="'+d.date+'">'+rows.join('')+
     '<button type="button" class="btn ghost" onclick="HunterClosure3826.swapPlanned(this)">Aplicar cambios futuros</button></div>';
 }
 if(typeof missionWindow==='function') {
   const original=missionWindow;
   missionWindow=function(d){
     let markup=original.apply(this,arguments);
     if(typeof markup!=='string')return markup;
     const challenge=prepareChallenge(d);
     const swap=futureSwaps(d);
     if(!challenge&&!swap)return markup;
     // Reutilizar la ventana original. No se añade modal, ni altera el reproductor.
     const close=markup.lastIndexOf('</section>');
     if(close>=0)markup=markup.slice(0,close)+challenge+swap+markup.slice(close);
     return markup;
   };
 }
 function swapPlanned(button){
   const container=button?.closest?.('[data-swap-date]');
   const date=container?.dataset?.swapDate;
   const day=typeof dayByDate==='function'?dayByDate(date):null;
   if(!day||date<=root.Engine?.dateKey?.()||day.routineId||
       (day.customized&&!day.prePlanSwap3826)||isActive(state?.logs?.[date])||
       !root.Adapt?.mode?.(state?.profile)?.auto){stats.blockedSwaps++;return false;}
   const next=day.items.map(x=>({...x}));let changed=0;
   for(const select of container.querySelectorAll('select[data-swap-index]')){
     const index=Number(select.dataset.swapIndex);
     if(!Number.isSafeInteger(index)||index<0||index>=next.length){stats.blockedSwaps++;return false;}
     const id=select.value;
     if(!id)continue;
     const allow=availableSwaps(day,index).some(e=>e.id===id);
     if(!allow){stats.blockedSwaps++;return false;}
     next[index]={...next[index],id,plannedSwap3826:true};changed++;
   }
   if(!changed)return false;
   const original={items:day.items,customized:day.customized,prePlanSwap3826:day.prePlanSwap3826};
   day.items=next;day.customized=true;day.prePlanSwap3826=true;
   let saved=false;
   try{saved=typeof save==='function'&&save()===true;}catch(_){}
   if(!saved){Object.assign(day,original);stats.blockedSwaps++;return false;}
   stats.swaps+=changed;
   if(typeof showMission==='function')showMission(date);
   if(typeof toast==='function')toast('Próxima sesión actualizada. Las sesiones iniciadas no cambian.');
   return true;
 }
 root.HunterClosure3826=Object.freeze({version:'38.26',swapPlanned,
   diagnostics:()=>({...stats,note:'Indicadores de fase, cambio previo y reconocimiento opcional; no verifica posturas clínicas.'})});
})(window,document);
