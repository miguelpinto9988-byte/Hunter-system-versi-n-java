/* Hunter System 38.52 — Misiones Monarca: perfil y equipamiento coherentes.
 * Cinco metas registradas durante un ciclo voluntario de cinco días.
 * No elimina controles existentes para menores ni sustituye una evaluación profesional.
 * No penaliza físicamente la cancelación o el incumplimiento.
 */
(function(root){
 'use strict';
 if(root.HunterMonarch3852)return;
 const E=root.Engine,G=root.HunterGroups49,L=root.HunterLevel3851;
 const A=root.Adapt,M=root.HunterMaterials3814;
 if(!E||!G||!L||!A||!M)throw Error('Monarch3852: dependencias ausentes');
 const categories=['upper','upper','lower','core','cardio'];
 const display={upper:'Tren superior',lower:'Tren inferior',core:'Zona central',cardio:'Cardio'};
 const tiers=['Básico','Intermedio','Avanzado'];
 const xss=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const now=()=>Date.now();
 const DAY=86400000;
 const CYCLE_DAYS=5;
 const getStore=()=>{
  if(!state.monarch3852||!Array.isArray(state.monarch3852.history)){
   state.monarch3852={active:null,history:[],xp:0,judgementUntil:0};
  }
  return state.monarch3852;
 };
 const signedDate=t=>E.dateKey(new Date(t));
 const dateWithin=(date,start,end)=>date>=signedDate(start)&&date<=signedDate(end-1);
 function screening(p){
  const mode=A.mode(p),gate=root.HunterDynamic381?.gate?.(p);
  if(!mode.auto||gate?.ok===false)return {ok:false,reason:mode.reason||gate.reason||'Completa y revisa tu perfil.'};
  if(p?.health?.pain&&p.health.pain!=='none'||p?.limits||p?.health?.acute||p?.health?.redFlags){
   return {ok:false,reason:'Revisa las restricciones de tu perfil antes de activar un desafío.'};
  }
  return {ok:true,reason:''};
 }
 function eligible(e,p,logs,date,cat){
  const m=G.classify(e),tier=L.tier(e);
  if(!m||m.group!==cat||tier<0||tier>2||m.power||m.impact||e.highSkill46||e.doseCap46?.highSkill)return false;
  if(cat==='cardio'?m.role!=='conditioning':m.role!=='primary')return false;
  if(M.eligible(e,p)===false||G.eligible(e,p)===false||A.allowed(e,p)===false)return false;
  const context=L.context(p,logs,date,cat,5);
  if(tier>context.maxTier)return false;
  if(context.failure?.failedFamilies?.includes(G.family(e)))return false;
  return true;
 }
 function rank(p){return Math.max(0,['basic','intermediate','advanced'].indexOf(p?.difficulty));}
 function preferred(p,logs,date){
  const r=rank(p);
  if(r===0)return [0,0,0,0,0];
  if(r===2)return [1,2,2,1,0];
  // Una variante avanzada de prueba sólo con historial favorable y permiso del motor.
  const ctx=L.context(p,logs,date,'upper',5);
  return ctx.preview?[0,2,0,1,0]:[0,1,0,1,0];
 }
 // Cada meta es un total acumulativo por ciclo, nunca una cuota diaria.
 // Sólo los movimientos repetitivos de baja complejidad tienen el rango 70-100.
 // Las variantes técnicas, los ejercicios temporizados y los perfiles sin base
 // suficiente mantienen dosis menores; no se incrementan por incumplimiento.
 function favorablePreviousCycle(logs,cat){
   const previous=getStore().history[0];
   if(!previous||previous.status!=='completed')return null;
   const CP=root.HunterCategoryProgress51;
   const feedback=Object.entries(logs||{}).filter(([day,log])=>
     dateWithin(day,previous.startedAt,previous.expiresAt)&&
     log&&(CP?.categoryOfLog?.(log)===cat));
   if(!feedback.length)return null;
   if(feedback.some(([,log])=>log.pain===true||log.fatigue===true||
       !log.complete||!(Number(log.rpe)>=1&&Number(log.rpe)<=4)))return null;
   return previous;
 }
 function loadTotal(e,p,logs,date){
   const group=G.classify(e).group,tier=L.tier(e),seconds=e.unit==='sec';
   const difficulty=rank(p);
   const progress=root.HunterCategoryProgress51?.resolve?.(logs,date,group);
   if(seconds){
     // Objetivos de duración con su propia unidad; nunca etiquetarlos como repeticiones.
     const base=[30,40,50][difficulty];
     return Math.max(15,Math.min(90,base+(progress?.level>0?5:0)));
   }
   const simple=tier<=1&&!e.highSkill46&&!e.doseCap46?.highSkill;
   const initial=simple?[70,75,80][difficulty]:[8,10,12][difficulty];
   const cap=simple?100:20;
   const previous=favorablePreviousCycle(logs,group);
   const last=previous?.objectives.find(o=>o.id===e.id) ||
     previous?.objectives.find(o=>o.category===group&&o.tier===tier&&o.unit==='reps');
   // Un incremento máximo de 2 repeticiones por nuevo ciclo con evidencia favorable.
   const next=last?Math.max(initial,Math.min(cap,Number(last.target)||initial)+2):initial;
   return Math.min(cap,next);
 }
 function generate(p,logs,date=E.dateKey()){
  const guard=screening(p);if(!guard.ok)return {ok:false,reason:guard.reason,objectives:[]};
  const choices=preferred(p,logs,date),takenIds=new Set(),takenFamilies=new Set(),objectives=[];
  for(let slot=0;slot<categories.length;slot++){
   const cat=categories[slot],desired=cat==='cardio'&&rank(p)>0?1:choices[slot];
   const pool=L.monarchCandidates(p,logs,date,cat).filter(e=>eligible(e,p,logs,date,cat)&&
     !takenIds.has(e.id)&&!takenFamilies.has(G.family(e)));
   // Priorizar el nivel previsto; ante la ausencia de equipo no elevar dificultad.
   const available=pool.filter(e=>L.tier(e)<=desired);
   available.sort((a,b)=>Math.abs(L.tier(a)-desired)-Math.abs(L.tier(b)-desired)||
     (a.unit==='sec'?1:0)-(b.unit==='sec'?1:0)||
     (G.classify(b).primary?1:0)-(G.classify(a).primary?1:0)||a.id.localeCompare(b.id));
   const exercise=available[0];
   if(!exercise)return {ok:false,reason:'No hay cinco ejercicios compatibles con el nivel y equipamiento. Ajusta el perfil o consulta alternativas.',objectives:[]};
   takenIds.add(exercise.id);takenFamilies.add(G.family(exercise));
   objectives.push({id:exercise.id,category:cat,tier:L.tier(exercise),unit:exercise.unit==='sec'?'sec':'reps',target:loadTotal(exercise,p,logs,date)});
  }
  return {ok:true,reason:'',objectives};
 }
 function totalOnDay(logs,id,date){
  const log=logs?.[date];
  if(!log||log.pain===true)return 0;
  let sum=0;
  for(const item of log.items||[]){
   if(item.id!==id)continue;
   for(const row of item.actual||[]){
    if(row.done===true&&Number.isFinite(Number(row.value))&&Number(row.value)>0)
     sum+=Number(row.value)*(E.sidesOf(item)?2:1);
   }
  }
  return sum;
 }
 function totalFor(logs,id,start,end){
  return Object.keys(logs||{}).filter(date=>dateWithin(date,start,end))
   .reduce((count,date)=>count+totalOnDay(logs,id,date),0);
 }
 function progress(m,logs){
  const start=signedDate(m.startedAt),end=signedDate(m.expiresAt-1);
  return m.objectives.map(o=>{
   let actual=0;
   for(let day=start;day<=end;day=E.plus(day,1)){
    const log=logs?.[day];
    const closingDay=day===end&&end!==start;
    const finishingTime=Date.parse(log?.completedAt||'');
    // En el último día parcial, sólo contabilizar rutinas con hora verificada
    // dentro de la ventana de 96 horas. La entrada manual se registra antes del cierre.
    const inWindow=!closingDay||(Number.isFinite(finishingTime)&&finishingTime>=m.startedAt&&finishingTime<=m.expiresAt);
    const fromRoutine=inWindow?Math.max(0,totalOnDay(logs,o.id,day)-(day===start?(m.baselines[o.id]||0):0)):0;
    const fromMonarch=Math.max(0,Number(m.manual?.[day]?.[o.id])||0);
    // Una serie en el registro diario y Monarca sólo cuenta una vez.
    actual+=Math.max(fromRoutine,fromMonarch);
   }
   return {...o,done:Math.min(actual,o.target)};
  });
 }
 function register(exerciseId){
  review();const st=getStore(),m=st.active;
  if(!m||now()>=m.expiresAt||requiresReview(m)){
   toast('Esta misión no admite nuevos registros. Revisa el perfil o el plazo.');return;
  }
  const objective=m.objectives.find(o=>o.id===exerciseId);
  if(!objective)return;
  const field=document.getElementById('monarch-entry-'+exerciseId);
  const value=Number(field?.value);
  if(!Number.isInteger(value)||value<1||value>objective.target){
   toast('Registra una cantidad válida que hayas realizado.');return;
  }
  const date=E.dateKey();
  if(state.logs?.[date]?.pain===true){toast('Hay dolor registrado. No añadas más actividad.');return;}
  m.manual=m.manual||{};m.manual[date]=m.manual[date]||{};
  m.manual[date][exerciseId]=Math.min(objective.target,(m.manual[date][exerciseId]||0)+value);
  save();review();go('monarch');
 }
 function review(){
  const st=getStore(),m=st.active;
  if(!m)return;
  const p=progress(m,state.logs),done=p.every(x=>x.done>=x.target);
  if(done||now()>=m.expiresAt){
   const success=done&&now()<=m.expiresAt;
   m.status=success?'completed':'expired';m.final=p;m.finishedAt=now();
   if(success){m.reward=50+m.objectives.reduce((points,o)=>points+10+10*o.tier,0);st.xp+=m.reward;}
   else {
    m.reward=0;
    const symptoms=Object.entries(state.logs||{}).some(([day,log])=>
     dateWithin(day,m.startedAt,m.expiresAt)&&(log?.pain===true||log?.fatigue===true));
    if(!symptoms)st.judgementUntil=now()+7*DAY;
   }
   st.history.unshift(m);st.history=st.history.slice(0,24);st.active=null;
   save();
  }
 }
 function activate(){
  review();const st=getStore();if(st.active){toast('Ya existe una Misión Monarca activa.');return;}
  const previous=st.history[0];
  if(previous&&previous.expiresAt>now()){toast('El próximo ciclo estará disponible cuando finalicen los cinco días.');return;}
  const generated=generate(state.profile,state.logs);
  if(!generated.ok){toast(generated.reason);return;}
  const t=now(),end=t+CYCLE_DAYS*DAY;
  const baselines=Object.fromEntries(generated.objectives.map(o=>[o.id,totalFor(state.logs,o.id,t,end)]));
  st.active={version:1,status:'active',startedAt:t,expiresAt:end,objectives:generated.objectives,baselines,manual:{},
   profileSnapshot:{difficulty:state.profile.difficulty,equipment:state.profile.equipment},reward:0};
  if(save()){toast('Misión Monarca activada: metas acumulativas de cinco días.');go('monarch');}
 }
 function cancel(){
  const st=getStore();if(!st.active)return;
  st.active.status='cancelled';st.active.finishedAt=now();st.active.final=progress(st.active,state.logs);
  st.history.unshift(st.active);st.history=st.history.slice(0,24);st.active=null;
  save();go('monarch');toast('Misión cancelada. Sin aumento obligatorio de carga.');
 }
 function requiresReview(m){
  const p=state.profile;
  if(!screening(p).ok)return true;
  return m.objectives.some(o=>!eligible(E.byId(o.id),p,state.logs,E.dateKey(),o.category)||
   L.tier(E.byId(o.id))>rank(p));
 }
 const meter=(v,max)=>`<div class="bar" style="margin:7px 0 12px"><i style="width:${Math.max(0,Math.min(100,100*v/max))}%"></i></div>`;
 function tile(){
  const st=getStore(),m=st.active;
  const txt=m?`Misión activa · termina ${new Date(m.expiresAt).toLocaleDateString('es')}`:'Cinco objetivos adaptados al perfil · ciclo de cinco días';
  return `<div class="section-title"><h2>👑 Misiones Monarca</h2><span class="badge">5 días</span></div><div class="card"><h3>Prueba del Monarca</h3><p class="muted" style="margin:8px 0 14px">${xss(txt)}</p><button class="btn primary" onclick="go('monarch')">${m?'VER PROGRESO MONARCA':'ABRIR MISIONES MONARCA'} →</button></div>`;
 }
 function view(){
  review();const st=getStore(),m=st.active;
  const guard=screening(state.profile);
  const historical=st.history.slice(0,5).map(x=>`<div class="quest"><div class="grow"><h3>${xss(x.status==='completed'?'Completada':x.status==='cancelled'?'Cancelada':'Vencida')}</h3><small>${new Date(x.startedAt).toLocaleDateString('es')} · ${x.objectives.length} ejercicios</small></div><span class="tag">${Number(x.reward)||0} XP</span></div>`).join('');
  const judgement=st.judgementUntil>now()?`<div class="notice">Juicio del Monarca hasta ${new Date(st.judgementUntil).toLocaleDateString('es')}: estado narrativo, sin cambios en las cargas de las rutinas.</div>`:'';
  let main='';
  if(m){
   const rows=progress(m,state.logs);
   const caution=requiresReview(m)?'<div class="notice warn">El perfil cambió o requiere revisión. No continúes esta misión sin actualizar el plan; puedes cancelarla sin penalización física.</div>':'';
   main=`<div class="card"><div class="row between"><h3>Ciclo en curso</h3><span class="badge">${Math.max(0,Math.ceil((m.expiresAt-now())/DAY))} días restantes</span></div><p class="muted">Los objetivos se acumulan durante cinco días, a partir de las series registradas en tus misiones normales. No necesitas repetir una serie para contabilizarla dos veces.</p>${caution}${rows.map(o=>{const e=E.byId(o.id);return `<div class="quest"><div class="grow"><h3>${xss(e?.name||o.id)}</h3><small>${xss(display[o.category])} · ${tiers[o.tier]} · ${o.done}/${o.target} ${o.unit==='sec'?'segundos':'repeticiones'}</small>${meter(o.done,o.target)}<label class="label" for="monarch-entry-${xss(o.id)}">Registrar avance realizado</label><div class="row" style="gap:8px"><input style="max-width:92px" id="monarch-entry-${xss(o.id)}" type="number" inputmode="numeric" min="1" max="${o.target}" value="1"><button class="btn ghost" onclick="HunterMonarch3852.register('${xss(o.id)}')">Guardar</button></div></div></div>`;}).join('')}<button class="btn ghost" onclick="HunterMonarch3852.cancel()">Cancelar misión</button></div>`;
  }else{
   const previous=st.history[0];
   const cooldown=previous&&previous.expiresAt>now();
   const selected=cooldown?{ok:false,reason:'El próximo ciclo estará disponible el '+new Date(previous.expiresAt).toLocaleString('es')+'.'}:generate(state.profile,state.logs);
   main=selected.ok?`<div class="card"><h3>Próxima misión · vista previa</h3><p class="muted">Se crearán cinco objetivos acumulativos que respetan la dificultad, el equipo y los registros de tu perfil.</p>${selected.objectives.map(o=>`<div class="quest"><div class="grow"><h3>${xss(E.byId(o.id)?.name||o.id)}</h3><small>${xss(display[o.category])} · ${tiers[o.tier]} · ${o.target} ${o.unit==='sec'?'segundos':'repeticiones'} en cinco días</small></div></div>`).join('')}<p class="footnote">Activación voluntaria. Revisa la técnica, la recuperación y detén cualquier actividad que provoque dolor. No hay penalizaciones físicas por cancelar.</p><button class="btn primary" onclick="HunterMonarch3852.activate()">AUTORIZAR Y ACTIVAR MISIÓN</button></div>`:
    `<div class="notice warn">${xss(selected.reason||guard.reason)}</div>`;
  }
  return `<section class="intro"><span class="eyebrow">HUNTER SYSTEM · DESAFÍOS ESPECIALES</span><h1>👑 Misiones Monarca</h1><p class="muted">Cinco ejercicios personalizados. Cada progreso cuenta en un ciclo de cinco días.</p></section><div class="metrics"><div class="metric"><strong>${st.xp}</strong><small>XP Monarca</small></div><div class="metric"><strong>${st.history.filter(x=>x.status==='completed').length}</strong><small>Misiones completadas</small></div></div>${judgement}${main}<div class="section-title"><h2>Historial Monarca</h2></div><div class="card">${historical||'<p class="muted">Aún no has realizado ninguna misión Monarca.</p>'}</div>`;
 }
 root.HunterMonarch3852=Object.freeze({version:'38.54',generate,eligible,screening,progress,activate,cancel,register,view,tile,review,requiresReview});
})(window);
