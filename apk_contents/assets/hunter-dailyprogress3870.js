/* Hunter System 38.71 — explicable, per-exercise day-to-day feedback and recovery.
 * Wraps the existing post-session questionnaire and category progression; preserves all logs.
 * Positive volume changes are advisory only, not automatically prescribed.
 */
(function(root){
 'use strict';
 if(root.HunterDailyProgress3870)return;
 const E=root.Engine, portable=root.HunterPortable3868, integration=root.HunterIntegration3869;
 if(!E||!portable||!integration||!root.HunterCategoryProgress51)return;
 const CATEGORY=Object.fromEntries(portable.categories.map(c=>[c.code,c.label]));
 const escape=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const validDate=s=>/^\d{4}-\d{2}-\d{2}$/.test(s);
 const newer=(a,b)=>a>b;
 const ageUnder18=()=>Number(state?.profile?.age)<18||!Number.isFinite(Number(state?.profile?.age));
 const category=id=>portable.exerciseMeta(id)?.category||null;
 const statusSet=new Set(['comfortable','normal','hard','unfinished']);
 const symptomSet=new Set(['none','tired','discomfort','pain']);
 const readinessSet=new Set(['recovered','unsure','tired']);
 const unit=e=>e?.unit==='sec'?'segundos':'repeticiones';
 // Requested 1–10 software reference table: repetitions only, never compulsory.
 const effortTable3871=Object.freeze({1:5,2:3,3:1,4:0,5:0,6:0,7:0,8:-1,9:-2,10:-3});
 function bounds3871(ex){
  const range=Array.isArray(ex?.repRange19)?ex.repRange19:[];
  const min=Number.isFinite(Number(range[0]))&&Number(range[0])>=1?Math.ceil(Number(range[0])):1;
  const maxima=[ex?.doseCap46?.target,range[1]].map(Number).filter(n=>Number.isFinite(n)&&n>=min);
  // No invented numerical cap for variants without a verified configured cap.
  return {min,max:maxima.length?Math.floor(Math.min(...maxima)):null};
 }
 function calculate3871(ex,effort,baseline){
  const score=Number(effort),known=Number.isInteger(score)&&score>=1&&score<=10;
  const unit=ex?.unit==='sec'?'segundos':'repeticiones';
  const rawDelta=known?effortTable3871[score]:null;
  const limits=bounds3871(ex);
  const base=Number(baseline),hasBase=Number.isInteger(base)&&base>=1;
  const eligible=unit==='repeticiones'&&known&&hasBase;
  const bounded=eligible&&(limits.max!==null||rawDelta<=0);
  const floor=rawDelta<=0?Math.min(limits.min,base):limits.min;
  const next=bounded?Math.max(floor,Math.min(limits.max??Number.MAX_SAFE_INTEGER,base+rawDelta)):null;
  const appliedDelta=bounded?next-base:null;
  return {effort:known?score:null,unit,rawDelta,base:hasBase?base:null,
   min:limits.min,max:limits.max,next,appliedDelta,
   note:unit==='segundos'?'La escala de repeticiones no se aplica a ejercicios medidos en segundos.':
    !known?'Falta una valoración válida de esfuerzo.':
    !hasBase?'Falta una referencia de repeticiones registrada.':
    limits.max===null&&rawDelta>0?'Esta variante no dispone de un límite superior verificado; no se calcula un objetivo de aumento.':
    appliedDelta!==rawDelta?'La propuesta queda limitada al intervalo configurado para esta variante.':
    'Referencia calculada dentro de los límites de la variante.'};
 }
 function adjustmentLabel3871(d){
  const c=d?.reference3871;if(!c||c.effort===null)return '';
  const signed=n=>n>0?'+'+n:String(n);
  const base=`Escala ${c.effort}/10: ${signed(c.rawDelta)} ${c.unit} (referencia del software).`;
  const limit=c.next===null?' Sin objetivo calculado.':` Referencia ${c.base} → ${c.next}; ajuste dentro de límites: ${signed(c.appliedDelta)}.`;
  const guard=d.action==='sugerencia'?' Aumento opcional, nunca automático.':
    c.rawDelta>0?' No se autoriza automáticamente una subida; primero se comprueban historial y recuperación.':'';
  return `<p class="dp-effort3871">${escape(base+limit+' '+c.note+guard)}</p>`;
 }

 const strengthCategory=c=>c==='tren_superior'||c==='tren_central'||c==='tren_inferior';
 const text=s=>escape(s).slice(0,1000);
 function rating(log,id){
  const a=log?.afterFeedback3870?.exercises;
  return a&&Object.prototype.hasOwnProperty.call(a,id)?a[id]:null;
 }
 function symptoms(log){
  const q=log?.afterFeedback3870;
  return Boolean(log?.pain||log?.fatigue||q?.symptom==='pain'||q?.symptom==='discomfort'||q?.symptom==='tired'||q?.readiness==='tired'||Number(log?.rpe)>=8);
 }
 function measuredExercise(log,id){
  return Boolean(log?.items?.some(x=>x?.id===id&&!x.selfReported&&Array.isArray(x.actual)&&x.actual.some(a=>a?.done===true&&Number.isFinite(Number(a.value))&&Number(a.value)>0)));
 }
 function exerciseSessions(logs,id,beforeDate){
  return Object.entries(logs||{}).filter(([d,l])=>validDate(d)&&d<beforeDate&&l&&Array.isArray(l.items)&&l.items.some(x=>x?.id===id))
   .sort((a,b)=>a[0].localeCompare(b[0]));
 }
 function daysBetween(a,b){
  const x=Date.parse(a+'T12:00:00Z'),y=Date.parse(b+'T12:00:00Z');
  return Number.isFinite(x)&&Number.isFinite(y)?Math.round((x-y)/86400000):0;
 }
 function latestCategoryFeedback(logs,cat,beforeDate){
  return Object.entries(logs||{}).filter(([d,l])=>validDate(d)&&d<beforeDate&&l?.complete&&Array.isArray(l.items)&&l.items.some(x=>category(x.id)===cat))
   .sort((a,b)=>b[0].localeCompare(a[0]))[0]||null;
 }
 function decision(logs,id,nextDate,profile=state?.profile||{}){
  const exercise=E.byId(id),cat=category(id),catName=CATEGORY[cat]||'Sin clasificación';
  if(!exercise||!cat||!validDate(nextDate))return {id,cat,action:'sin_datos',reason:'Ejercicio o categoría no disponible.'};
  const rows=exerciseSessions(logs,id,nextDate),last=rows.at(-1),previous=rows.slice(-6);
  const recentAny=latestCategoryFeedback(logs,cat,nextDate);
  const card={id,name:exercise.name,category:cat,label:catName,action:'mantener',reason:'',sessions:rows.length,proposedDelta:0,unit:unit(exercise),lastDate:last?.[0]||null,source:'registros propios de Hunter'};
  if(profile?.limits||profile?.health?.acute||profile?.health?.special||profile?.health?.pain&&profile.health.pain!=='none'){
   card.action='revisar';card.reason='Las restricciones del perfil tienen prioridad. No se sugiere aumentar actividad.';return card;
  }
  if(!last){card.action='establecer_referencia';card.reason='Aún no hay una sesión anterior de este ejercicio. Primero registra tu esfuerzo y las series realizadas.';return card;}
  const [lastDate,log]=last,q=log.afterFeedback3870;
  const lastItem=log.items?.find(x=>x.id===id);
  const individualEffort=Number(q?.exerciseEfforts?.[id]);
  const effectiveEffort=Number.isInteger(individualEffort)&&individualEffort>=1&&individualEffort<=10?individualEffort:log.rpe;
  card.reference3871=calculate3871(exercise,effectiveEffort,Number(lastItem?.target));

  if(log.pain||q?.symptom==='pain'||q?.symptom==='discomfort'){
   card.action='revisar';card.reason='Se informaron molestias o dolor. No se propone aumentar; conviene detener el ejercicio doloroso y revisar la situación.';return card;
  }
  const exRating=rating(log,id);
  if(exRating==='unfinished'){
   card.action='variacion';card.reason='El registro anterior indica una actividad no completada. Conviene revisar una variante accesible, sin añadir volumen.';return card;
  }
  if(exRating==='hard'||Number(log.rpe)>=8||Number(card.reference3871?.effort)>=8||q?.readiness==='tired'||q?.symptom==='tired'||log.fatigue){
   const ref=card.reference3871;
   card.action='reducir';card.proposedDelta=ref?.rawDelta<0?(ref.appliedDelta??0):0;
   const severity=ref?.rawDelta<0?` Esfuerzo ${ref.effort}/10: referencia ${ref.rawDelta} repeticiones; ${ref.appliedDelta===null?'no hay ajuste validado para esta variante':`ajuste limitado ${ref.appliedDelta} por variante`}.`:'';
   card.reason='La última valoración indicó esfuerzo elevado, fatiga o dificultad. Conviene revisar o suavizar la siguiente sesión.'+severity;
   return card;
  }
  if(recentAny?.[0]&&newer(recentAny[0],lastDate)&&symptoms(recentAny[1])){
   card.action='mantener';card.reason='Existe una valoración reciente de recuperación en esta categoría. Se mantiene la dosis hasta revisarla.';return card;
  }
  if(!measuredExercise(log,id)){card.reason='Solo hay confirmación de la actividad, sin cantidad realizada de este ejercicio. No se propone aumentar sin volumen medido.';return card;}
  if(!log.complete||!(Number(log.rpe)>=1&&Number(log.rpe)<=10)){
   card.reason='Falta una valoración completa de la sesión anterior. No se aumenta sin conocer cómo resultó.';return card;
  }
  const adequate=previous.filter(([d,l])=>{
   const per=Number(l.afterFeedback3870?.exerciseEfforts?.[id]);
   const effort=Number.isInteger(per)&&per>=1&&per<=10?per:Number(l.rpe);
   return l.complete&&!symptoms(l)&&measuredExercise(l,id)&&l.afterFeedback3870?.readiness==='recovered'&&rating(l,id)==='comfortable'&&effort>=1&&effort<=3;
  });
  const spaced=adequate.length>=2&&daysBetween(adequate.at(-1)[0],adequate.at(-2)[0])>=2;
  if(!q||!exRating){card.reason='Ya hay un registro de ejercicio, pero falta la evaluación individual de dificultad. Mantener es la opción conservadora.';return card;}
  if(exRating==='normal'||Number(card.reference3871?.effort)>=4){card.reason='Última sesión adecuada: se mantiene la cantidad; puede mejorar el control y la técnica.';return card;}
  if(!spaced){card.action='consolidar';card.reason='El ejercicio se sintió cómodo, pero aún faltan dos sesiones comparables en días separados antes de revisar una subida.';return card;}
  if(daysBetween(nextDate,lastDate)<2&&strengthCategory(cat)){
   card.action='recuperacion';card.reason='La última práctica fue reciente. La prioridad de la siguiente jornada es la recuperación, no repetir una subida.';return card;
  }
  if(ageUnder18()||Number(profile?.age)<18){
   card.action='consolidar';card.reason='Ya hay dos valoraciones cómodas: prioriza técnica, continuidad y recuperación. Cualquier aumento de carga requiere supervisión apropiada.';return card;
  }
  if(!portable.settings().volumeSuggestions){card.action='consolidar';card.reason='Hay registros favorables, pero las orientaciones de volumen están desactivadas en Perfil → Sistema.';return card;}
  const ref=card.reference3871;
  if(ref.unit!=='repeticiones'){card.reason='Esta actividad se mide en segundos: no se aplica una escala de repeticiones. Mantén el tiempo actual hasta revisar las referencias de duración.';return card;}
  if(ref.next===null){card.reason='No se puede calcular una propuesta numérica sin una referencia y los límites propios de esta variante.';return card;}
  if(ref.rawDelta<=0){card.reason='La escala de esfuerzo indica mantener o reducir, no aumentar las repeticiones.';return card;}
  if(ref.appliedDelta<=0){card.reason='Se alcanzó el máximo configurado de la variante; no se aumenta la cantidad.';return card;}
  card.action='sugerencia';card.proposedDelta=ref.appliedDelta;
  card.reason=`Esfuerzo ${ref.effort}/10: referencia ${ref.rawDelta>0?'+':''}${ref.rawDelta} repeticiones. El límite de la variante permite ${ref.appliedDelta>0?'+':''}${ref.appliedDelta}. Tras varias sesiones comparables y cómodas, es una revisión opcional; no se aplica automáticamente.`;
  return card;
 }
 function categoryDecisions(logs,nextDate){
  const cats=portable.categories;
  return cats.map(c=>{
   const latest=latestCategoryFeedback(logs,c.code,nextDate);
   if(!latest)return {category:c.code,label:c.label,action:'sin_datos',reason:'Sin evaluación anterior de esta categoría.'};
   const [date,l]=latest,items=l.items.filter(x=>category(x.id)===c.code);
   const parts=items.map(x=>decision(logs,x.id,nextDate));
   const priority=parts.find(x=>['revisar','reducir','variacion'].includes(x.action))||parts.find(x=>x.action==='recuperacion')||parts.find(x=>x.action==='sugerencia')||parts[0];
   return {category:c.code,label:c.label,lastDate:date,action:priority?.action||'mantener',reason:priority?.reason||'Mantener la referencia registrada.'};
  });
 }
 function invalidateFuture(date){
  let count=0;const today=E.dateKey();
  for(const cycle of state.cycles||[]){
   for(const d of cycle.days||[]){
    if(d.date<=date||d.date<today||d.customized||d.routineId||d.creatorAssigned||d.manual||d.scheduleChoice==='rest')continue;
    if(root.HunterDynamic381?.locked?.(d,state.logs||{}))continue;
    delete d.dynamicKey381;count++;
   }
   if(count)delete cycle.dynamicKey381;
  }
  return count;
 }
 function questionnaire(date){
  const l=state.logs?.[date];if(!l?.complete)return '';
  const q=l.afterFeedback3870||{},rpe=Number(l.rpe)||0;
  const opt=(value,label,current)=>`<option value="${value}" ${value===current?'selected':''}>${escape(label)}</option>`;
  const symptom=q.symptom||'none',ready=q.readiness||'unsure';
  return `<section class="card after-training dp-form3870" id="capacityFeedback13"><span class="eyebrow">VALORACIÓN DEL ENTRENAMIENTO · ${escape(date)}</span>
   <h3>¿Cómo fue la sesión?</h3><p>Un cuestionario general al concluir Rutinas o Misiones. La valoración solo se vincula con categorías realmente completadas, sin convertir registros antiguos; no se aplica ninguna subida automática.</p>
   <form onsubmit="HunterDailyProgress3870.submit(event,'${date}')">
   <label>Esfuerzo global (1–10)<select name="effort" required><option value="">Selecciona tu esfuerzo</option>${Array.from({length:10},(_,i)=>opt(String(i+1),`${i+1} / 10`,String(rpe||''))).join('')}</select></label>
   <div class="dp-scale3871" role="note" aria-label="Escala de referencia por esfuerzo"><strong>Escala orientativa para ejercicios medidos en repeticiones</strong><div class="dp-scale-grid3871"><span>1 → +5</span><span>2 → +3</span><span>3 → +1</span><span>4–7 → 0</span><span>8 → −1</span><span>9 → −2</span><span>10 → −3</span></div><small>Son cálculos teóricos sujetos a los límites de cada variante, historial, recuperación y revisión individual. No son obligaciones ni aumentan automáticamente la próxima sesión.</small></div>
   <label>¿Tuviste molestias?<select name="symptom" required>${opt('none','Sin molestias',symptom)}${opt('tired','Fatiga notable',symptom)}${opt('discomfort','Molestias con el movimiento',symptom)}${opt('pain','Dolor',symptom)}</select></label>
   <label>¿Cómo te sientes después?<select name="readiness" required>${opt('recovered','Bien recuperado',ready)}${opt('unsure','Necesito observar cómo me recupero',ready)}${opt('tired','Necesito más recuperación',ready)}</select></label>
   <p class="footnote">La valoración es general para la sesión. Las categorías conservan historiales independientes y solo reciben el esfuerzo cuando puede identificarse una categoría única completada.</p>
   <label>Notas opcionales<textarea name="exerciseNote" rows="2" maxlength="500" placeholder="Ejercicio, molestias, técnica o alternativa">${escape(l.note||'')}</textarea></label>
   <label>Reducción global opcional para próximas sesiones: <output>0%</output><input name="reduction" type="range" min="0" max="60" step="5" value="0" oninput="this.parentElement.querySelector('output').textContent=this.value+'%'"></label>
   <p class="footnote">Si hubo dolor, no sigas forzando el movimiento. Las recomendaciones dependen de tu historial y no son una evaluación médica. No se aumenta automáticamente la carga, las series ni el peso.</p>
   <button type="submit" class="btn primary full">Guardar y ver explicación del progreso</button></form></section>`;
 }
 function submit(ev,date){
  ev.preventDefault();if(!ev.target?.reportValidity?.())return false;
  const l=state.logs?.[date];if(!validDate(date)||!l?.complete)return false;
  const f=new FormData(ev.target),effort=Number(f.get('effort')),
    symptom=String(f.get('symptom')||''),readiness=String(f.get('readiness')||''),reduction=Number(f.get('reduction'));
  if(!Number.isInteger(effort)||effort<1||effort>10||!symptomSet.has(symptom)||!readinessSet.has(readiness)||!Number.isInteger(reduction)||reduction<0||reduction>60||reduction%5!==0)return false;
  const answers={},individualEfforts={};
  for(let i=0;i<(l.items||[]).length;i++){
   const id=l.items[i].id,v=String(f.get(`ex_${i}`)||'');
   if(!E.byId(id)||(v&&!statusSet.has(v)))return false; if(!v)continue;
   // The same exercise may appear multiple times. The more cautious answer wins.
   const priority={comfortable:0,normal:1,hard:2,unfinished:3};
   if(!(id in answers)||priority[v]>priority[answers[id]])answers[id]=v;
   const raw=String(f.get(`exEff_${i}`)||'');
   if(raw!==''){
    const score=Number(raw);
    if(!Number.isInteger(score)||score<1||score>10)return false;
    if(!(id in individualEfforts)||score>individualEfforts[id])individualEfforts[id]=score;
   }
  }
  l.rpe=effort;l.note=String(f.get('exerciseNote')||'').slice(0,500);
  l.afterFeedback3870={version:1,savedAt:new Date().toISOString(),symptom,readiness,exercises:answers,exerciseEfforts:individualEfforts};
  if(symptom==='tired'||readiness==='tired')l.fatigue=true;
  const oldScale=Number(root.TrainingIntensity?.scale?.(state.profile))||1;
  const newScale=Math.max(.4,Math.round(oldScale*(1-reduction/100)*100)/100);
  if(reduction>0)state.profile.trainingScale=newScale;
  l.trainingFeedback={...(l.trainingFeedback||{}),reduction,before:oldScale,after:newScale,updatedAt:new Date().toISOString()};
  invalidateFuture(date);
  if(reduction>0&&typeof rebuildPending13==='function'){
   const active=typeof currentCycle==='function'?currentCycle():null;
   if(active)rebuildPending13(active);
  }
  if(typeof save==='function')save();
  // Do not force navigation or alter a completed session; present a stable explanation.
  const next=E.plus(E.dateKey(),1),list=[];
  const seenCategories=[...new Set((l.items||[]).filter(item=>item?.actual?.some?.(a=>a?.done===true&&Number(a.value)>0)).map(item=>category(item.id)).filter(Boolean))];
  const categoryName=seenCategories.length===1?(CATEGORY[seenCategories[0]]||seenCategories[0]):'Sesión general o mixta';
  const delta=effortTable3871[effort];
  const label=delta>0?'+'+delta:String(delta);
  const unifiedSummary=`<div class="dp-result3870"><strong>${escape(categoryName)}</strong><p>Valoración general ${effort}/10 · referencia de escala ${label} repeticiones cuando corresponda.</p><p>${effort>=8||symptom!=='none'||readiness!=='recovered'?'Prioridad: recuperación y revisión de la siguiente sesión.':delta>0?'El cálculo no aumenta automáticamente las cantidades: necesita referencia validada, límites de variante y una decisión voluntaria.':'La siguiente sesión mantiene sus cantidades orientativas.'}</p></div>`;
  const h=`<section class="dp-form3870"><span class="eyebrow">VALORACIÓN REGISTRADA</span><h3>¿Qué cambiará y por qué?</h3>
   <p>${symptom==='pain'||symptom==='discomfort'?'Se registraron molestias. No se propone aumentar actividad y es importante revisar los ejercicios que las causaron.':'La valoración se guardó para revisar la próxima sesión de la categoría, sin imponer cambios.'}</p>
   ${unifiedSummary}
   <button class="btn ghost full" onclick="closeActiveModal()">Continuar</button></section>`;
  const box=document.querySelector('#capacityFeedback13');if(box)box.outerHTML=h;else if(typeof modal==='function')modal(h);
  if(typeof toast==='function')toast('Valoración individual guardada.');
  return true;
 }
 function panel(){
  if(!state?.profile)return '';
  const next=E.plus(E.dateKey(),1),recent=integration.exerciseSummary(state.logs||{},E.dateKey(),28).slice(0,7);
  const cats=categoryDecisions(state.logs||{},next);
  const latest=Object.entries(state.logs||{}).filter(([d,l])=>validDate(d)&&d<=E.dateKey()&&l?.complete&&!l?.afterFeedback3870&&l?.items?.length).sort((a,b)=>b[0].localeCompare(a[0]))[0];
  return `<section class="card dp-panel3870" aria-label="Evolución por ejercicio y categoría">
   <span class="eyebrow">EVOLUCIÓN DE UN DÍA AL OTRO</span><h3>Qué cambia y por qué</h3>
   <p>Vista para la próxima sesión compatible, no una obligación de entrenar mañana. Se consideran esfuerzo, recuperación, dificultad individual y registros de cada categoría.</p>
   ${latest?`<button class="btn ghost" type="button" onclick="HunterDailyProgress3870.open('${latest[0]}')">Completar cuestionario de ${escape(latest[0])}</button>`:''}
   <div class="dp-categories3870">${cats.map(c=>`<div><strong>${escape(c.label)}</strong><small>${escape(c.lastDate||'Sin historial')}</small><p>${text(c.reason)}</p></div>`).join('')}</div>
   <h4>Últimos ejercicios registrados</h4>${recent.length?recent.map(r=>{
    const d=decision(state.logs,r.id,next);return `<div class="dp-result3870"><strong>${escape(r.name)}</strong><small>${escape(d.label)} · ${escape(d.action.replace(/_/g,' '))}</small>${adjustmentLabel3871(d)}<p>${text(d.reason)}</p></div>`;
   }).join(''):'<p>Registra tu primera sesión y responde el cuestionario para generar explicaciones individuales.</p>'}
   <p class="footnote">Las cantidades provienen de Hunter y sus registros; las 1.257 referencias originales de Asterion no se aplican sin una equivalencia verificada. No hay aumentos automáticos de peso ni actividad.</p>
  </section>`;
 }
 function open(date){if(!state.logs?.[date]?.complete)return false;openCapacity13(date);return true;}
 // Existing V13 completion handlers already call capacityForm13 through openCapacity13.
 if(typeof capacityForm13==='function'){
  const prior=capacityForm13;
  capacityForm13=function(date){return date&&state.logs?.[date]?.complete?questionnaire(date):prior.apply(this,arguments);};
 }
 // Harden older auto-increase logic; keep reductions, review and safety screening.
 const CP=root.HunterCategoryProgress51;
 const previousAdjust=CP.adjustItems,previousFeedback=CP.feedback;
 root.HunterCategoryProgress51=Object.freeze({...CP,
  feedback(raw,logs,date){
   const original=previousFeedback(raw,logs,date),hasSymptoms=Object.entries(logs||{}).some(([d,l])=>validDate(d)&&d<date&&daysBetween(date,d)<=7&&l?.afterFeedback3870&&symptoms(l));
   return hasSymptoms?{...original,strain:true,canProgress:false}:original;
  },
  adjustItems(items,ctx){
   const result=previousAdjust(items,ctx);
   if(!Array.isArray(items)||!Array.isArray(result?.items))return result;
   let reduced=0;const revised=result.items.map((x,i)=>{
    const base=items[i]||x,out={...x};
    // Volume increases are opt-in informational suggestions, NEVER automatic.
    if(Number(out.target)>Number(base.target))out.target=base.target;
    if(Number(out.sets)>Number(base.sets))out.sets=base.sets;
    const d=decision(ctx?.logs||{},out.id,ctx?.date||E.dateKey(),ctx?.profile);
    if(d.action==='reducir'&&E.unitOf(out)!=='sec'&&Number.isInteger(Number(base.target))&&Number(base.target)>0){
     const ref=calculate3871(E.byId(out.id),d.reference3871?.effort,Number(base.target));
     // Only downward adjustments are applied by the legacy adapter, never increases.
     if(ref.rawDelta<0&&ref.next!==null)out.target=Math.max(1,Math.min(Number(out.target),ref.next));
    }
    const decrease=Number(out.target)<Number(base.target)||Number(out.sets)<Number(base.sets);
    if(decrease)reduced++;
    if(out.progression51)out.progression51={...out.progression51,action:decrease?'reduccion_suave':'mantener'};
    out.dailyProgress3870={action:decrease?'reducir':d.action,reason:d.reason,category:d.category};
    return out;
   });
   return {items:revised,progress:{...result.progress,action:reduced?'reduccion_suave':'mantener',
    affected:reduced,dayToDay3870:true,reason:'Evaluación por ejercicio y categoría, con incremento opcional y no automático.'}};
  }
 });
 if(typeof plan==='function'){
  const prev=plan;plan=function(){return prev.apply(this,arguments);};
 }
 if(typeof history==='function'){
  const prev=history;history=function(){return prev.apply(this,arguments);};
 }
 if(typeof validateBackup==='function'){
  const prev=validateBackup;validateBackup=function(data){
   if(!prev.apply(this,arguments))return false;
   return Object.values(data.logs||{}).every(log=>{
    const q=log?.afterFeedback3870;if(q===undefined)return true;
    if(!q||q.version!==1||!symptomSet.has(q.symptom)||!readinessSet.has(q.readiness)||!Number.isFinite(Date.parse(q.savedAt))||!q.exercises||typeof q.exercises!=='object'||Array.isArray(q.exercises)||Object.keys(q.exercises).length>96)return false;
    if(!Object.entries(q.exercises).every(([id,r])=>!!E.byId(id)&&statusSet.has(r)))return false;
    if(q.exerciseEfforts===undefined)return true; // 38.70 backups remain compatible.
    return q.exerciseEfforts&&typeof q.exerciseEfforts==='object'&&!Array.isArray(q.exerciseEfforts)&&Object.keys(q.exerciseEfforts).length<=96&&Object.entries(q.exerciseEfforts).every(([id,r])=>!!E.byId(id)&&Number.isInteger(r)&&r>=1&&r<=10);
   });
  };
 }
 root.HunterDailyProgress3870=Object.freeze({version:'38.71',questionnaire,submit,decision,categoryDecisions,panel,open,invalidateFuture,effortTable3871,calculate3871,bounds3871,
  audit:()=>({exercises:E.exercises.length,categories:portable.categories.length,automaticVolumeIncreases:false,
   questionnaireLinks:['detailed','quick','guided','daily-mission'],numericAsterionProfilesApplied:0})});
})(window);
