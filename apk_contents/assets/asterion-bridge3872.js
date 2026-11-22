/* Hunter System 38.72 — additive, opt-in bridge to the catalog-free Asterion rules.
 * The original Hunter exercise catalogue and completed logs are not copied or rewritten.
 * An audited provider can be injected as window.AsterionCatalogProvider3872.
 */
(function(root){'use strict';
 const R=root.AsterionRules3872;
 if(!R||root.AsterionBridge3872)return;
 const E=root.Engine,portable=root.HunterPortable3868;
 const escape=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const W=['Lunes','Martes','Miércoles','Jueves','Viernes','Sábado','Domingo'];
 function getStore(){
  if(typeof state==='undefined'||!state)return null;
  if(!state.asterion3872||state.asterion3872.version!==1)state.asterion3872={version:1,profile:R.defaultProfile(),rules:R.stateEmpty()};
  return state.asterion3872;
 }
 function saveState(){try{if(typeof save==='function')return save();}catch(_){return false;}return false;}
 function day(){if(portable?.settings){const zone=portable.settings().timeZone;if(zone&&zone!=='system'){
   try{const ps=new Intl.DateTimeFormat('en-US',{timeZone:zone,year:'numeric',month:'2-digit',day:'2-digit'}).formatToParts(new Date());const a=key=>ps.find(x=>x.type===key)?.value;return `${a('year')}-${a('month')}-${a('day')}`;}catch(_){}}
  }return E?.dateKey?.()||new Date().toISOString().slice(0,10);}
 function config(){const s=getStore();if(typeof state!=='undefined'&&state?.profile&&root.HunterProfileUnified3878?.derive){s.profile=root.HunterProfileUnified3878.derive(state.profile);if(!s.rules.initialExperienceDate)s.rules.initialExperienceDate=E?.dateKey?.()||new Date().toISOString().slice(0,10);}return s?.profile||R.defaultProfile();}
 function categoryToday(dt=day()){const p=config();return p.weekPlan[R.weekday(dt)]===5?'descanso':R.categories[p.weekPlan[R.weekday(dt)]];}
 function provider(){
  // Do not instantiate the pre-existing Hunter catalogue as if it had audited
  // equipment/supervision/eligibility data. A validated external provider is required.
  try{const injected=root.AsterionCatalogProvider3872;if(!injected)return {ref:null,error:'Catálogo externo no conectado. No se inventan actividades.'};
   const raw=typeof injected==='function'?injected():injected;
   return {ref:R.catalog(raw),error:null};
  }catch(e){return {ref:null,error:'El proveedor externo no pasó la validación: '+String(e?.message||e)};}
 }
 function change(field,value){const store=getStore();if(!store)return false;
  const p=store.profile;
  if(field==='active'){p.active=value==='true';if(p.active&&!store.rules.initialExperienceDate)store.rules.initialExperienceDate=day();}
  else if(field==='experience'&&['principiante','intermedio','avanzado'].includes(value)){p.experience=value;store.rules.initialExperienceDate=day();store.rules.phases={};}
  else if(field==='custom_goal')p.custom_goal=String(value).slice(0,500);
  else if(field==='goal')p.goal=String(value).slice(0,100);
  else if(field==='modality'&&['misiones','rutinas'].includes(value))p.modality=value;
  else if(field==='intensity'&&['baja','moderada'].includes(value))p.intensity=value;
  else if(field==='dailyLimit'&&Number.isInteger(Number(value))&&Number(value)>=1&&Number(value)<=12)p.dailyLimit=Number(value);
  else if(field==='minutes'&&Number.isInteger(Number(value))&&Number(value)>=5&&Number(value)<=240)p.minutes=Number(value);
  else if(field==='restSeconds'&&Number.isInteger(Number(value))&&Number(value)>=0&&Number(value)<=1800)p.restSeconds=Number(value);
  else if(field==='organization'&&['series','rondas'].includes(value))p.organization=value;
  else if(field.startsWith('weekday:')){
   const idx=Number(field.split(':')[1]);if(!Number.isInteger(idx)||idx<0||idx>6||!Number.isInteger(Number(value))||Number(value)<0||Number(value)>5)return false;
   p.weekPlan[idx]=Number(value);
  }else return false;
  saveState();return true;
 }
 function equipmentFromFields(){const p=config();return Array.isArray(p.equipment)?p.equipment:[];}
 function addEquipment(tag){const clean=String(tag||'').trim().slice(0,70);if(!/^[\wÀ-ÿ\- :.]{2,70}$/.test(clean))return false;return toggleEquipment(clean,true);}
 function toggleEquipment(tag,checked){const s=getStore();if(!s||!tag||tag.length>70)return false;
  const set=new Set(equipmentFromFields());if(checked)set.add(tag);else set.delete(tag);s.profile.equipment=[...set].sort();saveState();return true;}
 function settingsMarkup(){const p=config(),goal=R.parseGoal(p.custom_goal),connected=provider();
  const choice=(current,opts)=>opts.map(([value,label])=>`<option value="${escape(value)}" ${String(current)===String(value)?'selected':''}>${escape(label)}</option>`).join('');
  const field=(name,title,options)=>`<label>${title}<select onchange="AsterionBridge3872.change('${name}',this.value)">${choice(p[name],options)}</select></label>`;
  const schedule=p.weekPlan.map((code,i)=>`<label>${W[i]}<select aria-label="Actividad de ${W[i]}" onchange="AsterionBridge3872.change('weekday:${i}',this.value)">${choice(code,R.labels.map((x,k)=>[k,x]))}</select></label>`).join('');
  const equipment=['body','mat','wall','chair','dumbbells','gym'];const eqName=['Peso corporal','Colchoneta','Pared/apoyo estable','Silla estable','Mancuernas (declaradas)','Equipamiento de gimnasio (declarado)'];
  return `<section class="card ar-settings3872" data-profile-pane3857="3" aria-label="Motor Asterion de reglas y calendario">
   <span class="eyebrow">MOTOR LOCAL · REGLAS 38.73</span><h3>Calendario y progreso por categoría</h3>
   <p class="muted">Configura de lunes a domingo. Descanso no genera actividades. No modifica las misiones ni los registros ya guardados.</p>
   <label class="ar-line3872"><input type="checkbox" ${p.active?'checked':''} onchange="AsterionBridge3872.change('active',this.checked?'true':'false')"> Activar calendario Asterion (al conectar catálogo validado)</label>
   <div class="ar-schedule3872">${schedule}</div>
   <div class="ar-fields3872">${field('experience','Experiencia (límite técnico)',[['principiante','Principiante'],['intermedio','Intermedio'],['avanzado','Avanzado']])}
    ${field('goal','Objetivo principal',[['','Sin preferencia'],['bienestar','Bienestar general'],['fuerza','Fuerza funcional'],['movilidad','Movilidad'],['resistencia','Resistencia'],['equilibrio','Equilibrio'],['coordinacion','Coordinación']])}
    ${field('modality','Modalidad', [['misiones','Misiones'],['rutinas','Rutinas']])}
    ${field('intensity','Intensidad orientativa', [['baja','Baja'],['moderada','Moderada']])}
    ${field('dailyLimit','Máximo de propuestas',Array.from({length:7},(_,i)=>[i+1,String(i+1)]))}
    ${field('minutes','Minutos disponibles',[[10,'10'],[20,'20'],[30,'30'],[45,'45'],[60,'60']])}
    ${field('restSeconds','Descanso entre bloques (s)',[[30,'30'],[60,'60'],[90,'90'],[120,'120'],[180,'180']])}
    ${field('organization','Organización',[['series','Series'],['rondas','Rondas']])}</div>
   <label>Objetivo personal escrito (opcional)<textarea maxlength="500" rows="2" onchange="AsterionBridge3872.change('custom_goal',this.value)" placeholder="Ejemplo: mejorar mi equilibrio">${escape(p.custom_goal)}</textarea></label>
   <p class="ar-notice3872" aria-live="polite">${goal.recognizedAny?`Intenciones detectadas: ${escape(goal.recognized.join(', ')||'ninguna positiva')}. Exclusiones: ${escape(goal.excluded.join(', ')||'ninguna')}.`:'Objetivo guardado como texto; ninguna intención reconocida todavía.'}</p>
   <fieldset><legend>Equipamiento declarado para este motor</legend><div class="ar-equipment3872">${equipment.map((t,i)=>`<label><input type="checkbox" ${p.equipment.includes(t)?'checked':''} onchange="AsterionBridge3872.toggleEquipment('${t}',this.checked)"> ${eqName[i]}</label>`).join('')}</div><label>Equipo o peso disponible, identificado por ti<input id="ar-equipment-new3872" placeholder="Ej.: dumbbells:4kg o gym:machine_name" maxlength="70"></label><button type="button" class="btn ghost" onclick="if(AsterionBridge3872.addEquipment(document.getElementById('ar-equipment-new3872').value))render()">Añadir equipo declarado</button></fieldset>
   <p class="ar-notice3872">${escape(connected.error||'Contrato del catálogo conectado y verificado.')} No se importan perfiles numéricos ni se crean ejercicios.</p>
  </section>`;
 }
 function planPreview(){const p=config(),s=getStore();if(!p.active)return `<p class="ar-notice3872">El calendario Asterion está desactivado; se conserva la planificación original.</p>`;
  const d=day(),cat=categoryToday(d),src=provider(),dt=R.validProfile(p);
  if(!dt.ok)return `<p class="ar-notice3872">Configuración incompleta: ${escape(dt.errors.join(', '))}</p>`;
  if(cat==='descanso')return `<p class="ar-notice3872">Hoy es día de descanso en el calendario Asterion. Sin propuestas.</p>`;
  if(!src.ref)return `<p class="ar-notice3872">${escape(src.error)}</p>`;
  if(state?.profile?.limits||state?.profile?.health?.pain&&state.profile.health.pain!=='none'||state?.profile?.health?.acute||state?.profile?.health?.special)return '<p class="ar-notice3872">Restricciones del perfil Hunter: se bloquea esta propuesta automática hasta revisión.</p>'; 
  const pinnedBefore=JSON.stringify(s.rules.pins[d]||null);const out=(root.AsterionUnified3873||R).generate({catalog:src.ref,profile:p,state:s.rules,today:d});if(JSON.stringify(s.rules.pins[d]||null)!==pinnedBefore)saveState();
  return `<p class="ar-notice3872">${escape(out.reason)} ${escape(R.labels[R.categories.indexOf(cat)])} · ${escape(d)}</p>
   ${out.items.map(v=>{const key=d+'|'+v.id,st=s.rules.outcomes[key]?.status||'PENDING';return `<div class="ar-entry3872"><span><b>${escape(v.label||v.id)}</b><small>${escape(v.id)} · nivel ${v.level} · ${escape(st)}</small></span><div><button type="button" class="btn ghost" onclick="AsterionBridge3872.result('${escape(v.id)}','COMPLETED')">Completada</button> <button type="button" class="btn ghost" onclick="AsterionBridge3872.result('${escape(v.id)}','SKIPPED')">Omitida</button></div></div>`;}).join('')}
   ${out.review?.checkpoint?`<section class="ar-offer3873"><b>Revisión técnica · día ${out.review.checkpoint}</b><p>${escape(out.review.why)}</p>${(out.review.proposals||[]).map(x=>`<div class="ar-offer3873"><strong>${escape(src.ref.byId(x.id)?.label||x.id)}</strong><small>De nivel ${escape(src.ref.byId(x.from)?.level)} a nivel ${escape(x.level)} · Propuesta, no asignada</small><p>${escape(x.why)}</p></div>`).join('')}</section>`:''}`;
 }
 function result(id,status){const d=day(),s=getStore(),catalog=provider();if(!s||!catalog.ref)return false;
  const preview=(root.AsterionUnified3873||R).generate({catalog:catalog.ref,profile:config(),state:s.rules,today:d});if(!preview.items.some(x=>x.id===id))return false;
  try{R.recordOutcome(s.rules,{date:d,variantId:id,category:preview.category,status});saveState();if(typeof render==='function')render();return true;}catch(e){if(typeof toast==='function')toast(e.message);return false;}
 }
 function report(){const s=getStore(),d=day(),c=categoryToday(d),sum=R.completionSummary(s.rules),g=R.globalReview(s.rules,d);
  return `<section class="card ar-summary3872"><span class="eyebrow">ASTERION · SISTEMA DETERMINISTA</span><h3>Calendario y revisión por categoría</h3>
   <p>Hoy: <b>${escape(R.labels[R.categories.indexOf(c)]||'Descanso')}</b>. Propuestas guardadas por día; descanso sin actividades.</p>
   ${planPreview()}
   ${root.AsterionUnified3873?`<p class="ar-note3873">Motor 38.73: valoración única por sesión y categoría; las revisiones de días 7, 14 y 21 muestran propuestas que requieren aceptación y metadatos certificados.</p>`:''}
   <p>Participación: ${sum.percent===null?'Sin datos suficientes':sum.percent+' %'} · ${sum.completed} completadas · ${sum.skipped} omitidas · ${sum.pending} pendientes.</p>
   <div class="ar-review3872">${R.categories.map((category,i)=>{const v=R.decision(s.rules,category,d),u=root.AsterionUnified3873?.milestone(s.rules,category,d);
    return `<details><summary>${R.labels[i]} · ${escape(v.action)}</summary><p>${escape(v.why)}</p><small>${u?`Revisión unificada: ${escape(u.action)} · ${escape(u.why)}`:"Motor de revisión no disponible."}</small>${u?.checkpoint?`<p class="ar-note3873"><b>Día ${u.checkpoint} · ${escape(u.action)}</b>. ${escape(u.why)} La variante más compleja no se asigna automáticamente.</p>`:''}</details>`;}).join('')}</div>
   <p class="muted">Revisión global: ${g.count} valoraciones, esfuerzo medio ${g.average??'—'}; ${escape(g.reason)}</p>
  </section>`;
 }
 // Connected to a newly submitted legacy general questionnaire only. Historical
 // global ratings are NEVER retroactively assigned to categories.
 function syncNewQuestionnaire(dt){const s=getStore(),log=state?.logs?.[dt];if(!s||!log?.complete||!(Number.isInteger(Number(log.rpe))&&Number(log.rpe)>=1&&Number(log.rpe)<=10))return false;
  const actual=(log.items||[]).filter(x=>Array.isArray(x.actual)&&x.actual.some(y=>y?.done===true));
  const cats=[...new Set(actual.map(x=>portable?.exerciseMeta(x.id)?.category).filter(Boolean))];
  const value=Number(log.rpe);
  const unified=root.AsterionUnified3873;
  if(unified)unified.recordSession(s.rules,{date:dt,log,categoryForId:id=>portable?.exerciseMeta(id)?.category});
  else if(cats.length===1)R.effort(s.rules,{date:dt,category:cats[0],value,completed:true,global:true});
  else R.globalEffort(s.rules,{date:dt,value});
  saveState();return true;
 }
 if(root.HunterDailyProgress3870?.submit){const prev=root.HunterDailyProgress3870;
  root.HunterDailyProgress3870=Object.freeze({...prev,submit:function(ev,dt){const ok=prev.submit(ev,dt);if(ok)syncNewQuestionnaire(dt);return ok;}});
 }
 if(typeof profileForm==='function'){const prev=profileForm;profileForm=function(){let html=prev.apply(this,arguments);const at=html.lastIndexOf('</form>');
   return at<0?html:html.slice(0,at)+settingsMarkup()+html.slice(at);
  };}
 if(typeof plan==='function'){const prev=plan;plan=function(){const s=getStore();const ready=Boolean(config().active&&provider().ref);return ready?report():report()+prev.apply(this,arguments);};}
 // Keep the original Hunter missions intact until an external catalog has been validated.
 root.AsterionBridge3872=Object.freeze({version:'38.73',change,toggleEquipment,addEquipment,config,provider,categoryToday,planPreview,report,result,syncNewQuestionnaire,
  audit:()=>({catalogConnected:Boolean(provider().ref),categories:R.categories.length,configured:config().active,pins:Object.keys(getStore()?.rules?.pins||{}).length})});
})(window);
