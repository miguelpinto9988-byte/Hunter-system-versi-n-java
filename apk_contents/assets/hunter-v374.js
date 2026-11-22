/* Hunter System 37.4: revisión offline de metas y guardado verificable. */
'use strict';
const HunterImprove374=(()=>{
 const clean=s=>String(s||'').toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g,'').replace(/[’‘]/g,"'");
 const lex={
  build:[/cuerpo\s+estetic[oa]/g,/estetic[oa]/g,/tonific/g,/definicion\s+muscular/g,/definir\s+(?:mi\s+)?(?:cuerpo|musculos)/g,/ganar\s+muscul/g,/masa\s+muscular/g,/hipertrof/g,/fuerza/g,/fortalec/g,/muscul/g,/pecho|espalda|biceps|triceps|gluteos|abdomen|marcar\s+abdomen/g],
  fitness:[/resistencia\s+(?:cardiovascular|aerobica|fisica)/g,/cardio/g,/bajar\s+(?:de\s+)?peso/g,/quemar\s+grasa/g,/condicion\s+fisica/g,/aguantar\s+mas/g,/correr\s+mas/g],
  athletic:[/agilidad/g,/agil(?:es)?/g,/coordinacion/g,/potencia/g,/velocidad/g,/reaccion/g,/sprint/g,/salto/g,/explosiv/g,/deportiv/g],
  movement:[/movilidad/g,/flexibilidad/g,/estiramiento/g,/equilibrio/g,/postura/g,/control\s+motor/g,/rango\s+de\s+movimiento/g],
  habit:[/constancia/g,/disciplina/g,/habito/g,/bienestar/g,/rutina\s+(?:estable|diaria)/g,/ser\s+activo/g],
  adapted:[/actividad\s+adaptada/g,/retomar\s+(?:el\s+)?entrenamiento/g,/regresar\s+a\s+entrenar/g,/solo\s+sentado/g]
 };
 const target={build:'muscle',fitness:'endurance',athletic:'athletic',movement:'mobility',habit:'general',adapted:'returning'};
 const medicalRE=/\b(?:lesion(?:es)?|hernia|fractura|cirugia|dolor|duele|molestia|rehabilitacion)\b/;
 const negated=s=>s.replace(/\b(?:no quiero|no deseo|no busco|evitar|evito|sin interes en)\s+[^.;,]+/g,' ');
 function interpret(text,chosen='habit'){
  const raw=clean(text),parts=negated(raw),hits=[];
  for(const [group,regexes] of Object.entries(lex)){
   let idx=Infinity,matched=0;
   for(const re of regexes){re.lastIndex=0;const m=re.exec(parts);if(m){idx=Math.min(idx,m.index);matched++;}}
   if(matched)hits.push({group,index:idx,score:matched});
  }
  hits.sort((a,b)=>a.index-b.index||b.score-a.score);
  const alternatives=hits.map(x=>x.group);
  const primary=alternatives[0]||((chosen in target)?chosen:'habit');
  const noImpact=/\b(?:sin\s+(?:saltos?|impacto)|evitar\s+(?:los\s+)?saltos?|no\s+(?:quiero|puedo)\s+salt(?:ar|os?))\b/.test(raw);
  const restrictions={};if(noImpact)restrictions.noImpact=true;
  const terms={noFloor:/\b(?:sin\s+suelo|no\s+(?:puedo|quiero)\s+(?:tumbarme|arrodillarme))\b/,noOverhead:/\b(?:no\s+(?:puedo|quiero)\s+levantar\s+los\s+brazos|sin\s+elevar\s+los\s+brazos)\b/,noLoad:/\b(?:sin\s+(?:mancuernas|pesas|carga)|no\s+(?:puedo|quiero)\s+usar\s+pesas)\b/,noWrist:/\b(?:sin\s+apoyo\s+en\s+(?:las\s+)?manos|no\s+(?:puedo|quiero)\s+apoyar\s+las\s+manos)\b/,noKnee:/\b(?:sin\s+flexionar\s+(?:las\s+)?rodillas|no\s+(?:puedo|quiero)\s+doblar\s+(?:las\s+)?rodillas)\b/,seatedOnly:/\b(?:solo|unicamente)\s+(?:estar\s+)?sentado\b/};
  for(const [k,r]of Object.entries(terms))if(r.test(raw))restrictions[k]=true;
  let focus='full';if(/\b(?:piernas?|gluteos?|tren\s+inferior)\b/.test(parts))focus='lower';else if(/\b(?:brazos?|pecho|hombros?|tren\s+superior)\b/.test(parts))focus='upper';else if(/\b(?:abdomen|core|zona\s+media)\b/.test(parts))focus='core';
  const medical=medicalRE.test(raw.replace(/\b(?:sin|no\s+tengo)\s+(?:ninguna?\s+)?(?:lesiones?|dolor|molestias?)\b/g,' '));
  const count=hits.length;
  let specific=target[primary];
  if(primary==='build'){
   specific=focus==='upper'?'upper':focus==='lower'?'lower':focus==='core'?'core':(/\bfuerza\b/.test(parts)&&!/estetic|muscul|tonific|hipertrof|defin/.test(parts)?'strength':'muscle');
  }
  if(primary==='fitness'&&/bajar\s+(?:de\s+)?peso|quemar\s+grasa/.test(parts))specific='activity';
  if(primary==='movement'){if(/equilibrio/.test(parts))specific='balance';else if(/flexibil|estiramiento/.test(parts))specific='flexibility';else if(/postura/.test(parts))specific='posture';}
  if(primary==='habit'&&noImpact)specific='lowimpact';
  if(primary==='adapted'&&/solo\s+sentado/.test(parts))specific='seated';
  if(!GoalPlans.plans[specific])specific=target[primary];
  return {primary,alternatives,target:specific,focus,restrictions,noImpact,medical,recognized:count,raw,reason:GoalGroups14.groups[primary]?.why||'',confidence:count?('Se identificaron '+count+' objetivo(s) en tu descripción.'):'No se identificaron palabras clave; se conserva la familia elegida.'};
 }
 const getFormInput=f=>f?.elements?.finalGoal?.value||'';
 const names=groups=>groups.map(g=>GoalGroups14.groups[g]?.name||g).join(' · ');
 function refresh(form,apply=false){
  if(!form?.elements?.goalGroup)return null;
  const selected=form.elements.goalGroup.value,results=interpret(getFormInput(form),selected),userChoice=form.dataset.manualGroup374;
  const primary=apply&&userChoice&&userChoice in target?userChoice:results.primary;
  if(results.recognized>0&&!userChoice)form.elements.goalGroup.value=primary;
  const active=form.elements.goalGroup.value,targetCode=results.recognized&&active===results.primary?results.target:target[active]||'general';
  if(form.elements.finalTarget)form.elements.finalTarget.value=targetCode;
  if(form.elements.goal)form.elements.goal.value=targetCode;
  if(form.elements.focus){if(form.dataset.autofocus374==='true'&&results.focus==='full')form.elements.focus.value='full';if(results.focus!=='full'){form.elements.focus.value=results.focus;form.dataset.autofocus374='true';}}
  const p=state.profile||{},prev=new Set([...(p.goalAutoRestrictions374||[]),...(form.dataset.autoRestrictions374||'').split(',').filter(Boolean)]);
  for(const k of Object.keys(Adapt.restrictions)){
   const el=form.elements['r_'+k];if(!el||el.dataset.manual374==='true')continue;
   if(results.restrictions[k]){el.checked=true;el.dataset.auto374='true';}
   else if(prev.has(k)||el.dataset.auto374==='true'){el.checked=false;delete el.dataset.auto374;}
  }
  form.dataset.autoRestrictions374=Object.keys(results.restrictions).filter(k=>results.restrictions[k]).join(',');
  const msg=form.querySelector('#intentResult');if(msg){msg.innerHTML='<b>'+esc(GoalGroups14.groups[active]?.name||active)+'</b><p>'+esc((GoalPlans.plans[targetCode]||GoalPlans.plans.general).why)+'</p><small>'+esc(results.confidence)+(results.medical?' Revisa las lesiones, limitaciones y aprobación profesional.':'')+'</small>';}
  const out=form.querySelector('#intentPreview374');if(out){const list=results.alternatives.length?names(results.alternatives):GoalGroups14.groups[active].name;out.innerHTML='<span class="eyebrow">INTERPRETACIÓN LOCAL · SIN CONEXIÓN</span><b>'+esc(list)+'</b><p>'+esc(results.alternatives.length>1?'Se combinarán estas metas respetando tu prioridad principal.':'Se adaptará el plan a la prioridad mostrada.')+'</p><small>'+esc('Prioridad: '+GoalGroups14.groups[active].name+' · Objetivo: '+(GoalPlans.plans[targetCode]?.name||targetCode))+'</small>'+(results.medical?'<p class="intent-warning374">El texto menciona dolor o lesión. El generador puede bloquear el plan automático.</p>':'');}
  return {...results,primary:active,target:targetCode};
 }
 function confirmation(goal,after,verified){
  const eligible=E.eligible(after),cycles=eligible?currentCycle():null,now=E.dateKey(),days=cycles?.days?.filter(d=>d.date>=now&&!Weekly.active(state.logs[d.date]))||[];
  const goals=goal.alternatives.length?names(goal.alternatives):GoalGroups14.groups[goal.primary]?.name||'Objetivo general';
  const detail=eligible?'Se revisó el plan de la semana actual. '+days.length+' día(s) futuros sin actividad registrada pueden actualizarse según tus nuevos criterios. Las sesiones completadas se conservan.':'La configuración del perfil quedó guardada, pero las condiciones declaradas requieren revisión antes de generar automáticamente un entrenamiento.';
  modal('<section class="goal-confirm374" role="status" aria-live="polite"><span class="save-icon374">✓</span><span class="eyebrow">HUNTER SYSTEM · 37.4</span><h2>Configuración guardada</h2><p>Tu acompañante aplicó la interpretación local de tus preferencias.</p><div class="save-summary374"><b>'+esc(goals)+'</b><p>'+esc(detail)+'</p><small>Modalidad: '+esc(after.trainingMode==='routines'?'Rutinas':'Misiones')+' · Nivel: '+esc(after.difficulty||'basic')+'</small></div><p class="footnote">Se conservaron los registros ya realizados. La adaptación es basada en reglas locales, no en un modelo de IA conversacional.</p><button type="button" class="btn primary full" onclick="closeActiveModal()">Continuar</button></section>');
 }
 return {interpret,refresh,confirmation};
})();

// En 37.4 el texto se vuelve a analizar íntegramente después de cada edición.
const interpretBefore374=GoalGroups14.interpret;
GoalGroups14.interpret=function(text,chosen){
 const r=HunterImprove374.interpret(text,chosen),g=GoalGroups14.groups[r.primary];
 return {group:r.primary,target:r.target,focus:r.focus,medical:r.medical,noImpact:r.noImpact,alternatives:r.alternatives,ambiguous:false,reason:g?.why||'',measure:g?.measure||''};
};
const profileBefore374=profileForm;
profileForm=function(initial=false){
 const holder=document.createElement('div');holder.innerHTML=profileBefore374(initial);const form=holder.querySelector('form');if(!form)return holder.innerHTML;
 const f=form.elements.finalGoal;if(f){f.setAttribute('oninput','previewGoal374(this.form)');f.setAttribute('onchange','previewGoal374(this.form)');f.setAttribute('aria-describedby','intentPreview374');const row=document.createElement('div');row.className='goal-control374';row.innerHTML='<div id="intentPreview374" class="goal-preview374" aria-live="polite"></div><button class="btn primary full goal-apply374" type="button" onclick="applyGoal374(this.form)">✦ Interpretar con el acompañante y guardar</button><p class="footnote">Al pulsar el botón se reinterpreta el texto actual y se guardan sus preferencias. Los objetivos que elimines de este campo dejan de formar parte de esa descripción. Las metas personales se administran por separado. Puedes escribir «cuerpo estético, agilidad» o dejar solo «cuerpo estético».</p>';
 (form.querySelector('#intentResult')||f).after(row);}
 const sel=form.elements.goalGroup;if(sel)sel.setAttribute('onchange','this.form.dataset.manualGroup374=this.value;previewGoal374(this.form)');
 if(form.elements.finalGoal){for(const e of form.querySelectorAll('[name^="r_"]'))e.setAttribute('onchange','this.dataset.manual374="true"');}
 form.querySelector('[type=submit]')?.setAttribute('aria-label','Guardar todos los cambios del perfil');
 HunterImprove374.refresh(form,false);
 return holder.innerHTML;
};
const goalSelectionBefore374=goalSelection14;
goalSelection14=function(form){return HunterImprove374.refresh(form,true);};
function previewGoal374(form){return HunterImprove374.refresh(form,false);}
function applyGoal374(form){
 if(!form?.reportValidity?.())return toast('Completa los datos obligatorios antes de guardar.');
 const r=HunterImprove374.refresh(form,true);if(r?.medical&&!injuryDraft.some(i=>i.stage!=='past')){const details=form.querySelector('#healthDetails');if(details){details.open=true;details.scrollIntoView({block:'nearest'});}return toast('Tu meta menciona dolor o lesión. Completa Lesiones y salud antes de guardar el plan.');}
 form.dataset.confirmGoal374='true';form.requestSubmit();
}
const submitBefore374=submitProfile;
submitProfile=function(ev){
 const form=ev.target,was=state.profile,requested=form.dataset.confirmGoal374==='true',goal=HunterImprove374.refresh(form,true),desc=getSafeText374(form.elements.finalGoal?.value);
 submitBefore374(ev);
 if(!state.profile||state.profile===was||state.profile.finalGoal!==desc)return;
 state.profile.goalAutoRestrictions374=(form.dataset.autoRestrictions374||'').split(',').filter(Boolean);
 state.profile.interpretation374={text:desc,groups:goal.alternatives,primary:goal.primary,target:state.profile.finalTarget,updatedAt:new Date().toISOString()};
 if(!save())return;
 let valid=false;try{const stored=JSON.parse(localStorage.getItem(KEY));valid=stored?.profile?.interpretation374?.text===desc;}catch(e){}
 if(!valid)return;
 if(requested)try{HunterImprove374.confirmation(goal,state.profile,valid);}catch(e){toast('Configuración guardada. Revisa tu plan actualizado en Misiones.');}
 else toast('Perfil y criterios de entrenamiento guardados.');
};
function getSafeText374(text){return String(text||'').slice(0,300);}
const answerBefore374=answerCoach;
answerCoach=function(input){
 const s=String(input||'');
 if(/(?:cuerpo\s+est[eé]tico|tonificar|definir|marcar\s+abdomen|objetivos?\s+combinados|agilidad\s+y\s+fuerza|mi\s+meta|interpretar\s+objetivo)/i.test(s)&&state.profile){
  const goal=HunterImprove374.interpret(state.profile.finalGoal||'',state.profile.goalGroup);const label=goal.alternatives.length?namesSafe374(goal.alternatives):GoalGroups14.groups[goal.primary]?.name;
  return companionDisplayName()+': Según tu último perfil guardado, estás priorizando '+label+'. '+(GoalPlans.plans[state.profile.finalTarget]?.why||goal.reason)+' Cambia el texto en Cazador y pulsa «Interpretar con el acompañante y guardar» para actualizar el plan. Los registros finalizados se conservan.';
 }
 return answerBefore374(input);
};
function namesSafe374(groups){return groups.map(g=>GoalGroups14.groups[g]?.name||g).join(' y ');}
const diagnosticsBefore374=openDiagnostics20;
openDiagnostics20=function(){
 const total=E.exercises.length,routines=Catalog.routines?.length||0,errors=Runtime20.errors?.length||0;
 modal('<section><h2>Diagnóstico local · V37.4</h2><p>'+total+' ejercicios disponibles · '+routines+' rutinas de catálogo.</p><p>Errores de interfaz registrados en esta sesión: '+errors+'.</p><p>Interpretación: reglas locales por palabras y objetivos, sin enviar el texto a servidores.</p><p>Los planes se recalculan para días elegibles; los registros completados se mantienen.</p><p class="footnote">Si necesitas cambiar de APK con firma diferente, exporta antes tu copia de seguridad desde Cazador.</p></section>');
};
window.HunterV374={version:'37.4',intention:HunterImprove374};

try { render(); } catch(e) { console.error("V37.4 UI",e); }
