/* Hunter System 38.68: non-destructive import of Asterion taxonomy and volume tracking.
 * Asterion variant doses are preserved as data, NOT applied automatically.
 * Hunter exercise IDs, original planning and histories always remain the source of truth.
 */
(function(root){
 'use strict';
 if(root.HunterPortable3868)return;
 const D=root.HunterPortableData3868;
 const E=root.Engine;
 if(!D||!E||!root.HunterGroups49||D.hunterRows?.length!==1200){
   console.error('HunterPortable3868: catálogos incompletos; integracion omitida');
   return;
 }
 const CAT_LABELS=Object.freeze(Object.fromEntries(D.categories.map(c=>[c.code,c.label])));
 const CODE=['tren_superior','tren_central','tren_inferior','movilidad','cardio'];
 const DEFAULT=Object.freeze({categories:CODE,experience:'principiante',dailyActivities:3,volumeSuggestions:false,timeZone:'system',language:'es'});
 const map=new Map(D.hunterRows.map(([id,category,family,type,asterion])=>[id,Object.freeze({id,category,family,type,asterion})]));
 const names=D.hunterNames;
 const astFamilies=new Map(D.asterionFamilies.map(a=>[a[0],a]));
 const astTypes=new Map(D.asterionTypes.map(a=>[a[0],a]));
 const astVariants=new Map(D.asterionVariants.map(a=>[a[0],a]));
 const astProfiles=new Map(D.asterionProfiles.map(a=>[a[0],a]));
 const available=new Set(E.exercises.map(e=>e.id));
 if(map.size!==1200||[...available].some(id=>!map.has(id))){
  console.error('HunterPortable3868: id de ejercicio no coincidente; integración omitida');return;
 }
 const escSafe=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const titleCase=s=>String(s||'').split(/[_-]+/).join(' ').replace(/^./,c=>c.toUpperCase());
 const CATEGORY_KEYS=new Set(CODE);
 const exerciseMeta=id=>map.get(id)||null;
 function defaults(){return {categories:CODE.slice(),experience:'principiante',dailyActivities:3,volumeSuggestions:false,timeZone:'system',language:'es'};}
 function settings(){
  const raw=(typeof state!=='undefined'&&state&&typeof state.portable3868==='object'&&!Array.isArray(state.portable3868))?state.portable3868:{};
  const profile=(typeof state!=='undefined'&&state&&state.profile&&typeof state.profile==='object')?state.profile:null;
  if(!profile){return {categories:CODE.slice(),experience:'principiante',dailyActivities:3,volumeSuggestions:true,timeZone:validZone(raw.timeZone)?raw.timeZone:'system',language:'es'};}
  const counts=Object.fromEntries(CODE.map(x=>[x,0]));
  const days=Array.isArray(profile.weekPlan)?profile.weekPlan:[];
  for(let i=0;i<7;i++){
   const kind=days[i];
   const code=kind==='train'?({upper:'tren_superior',lower:'tren_inferior',core:'tren_central'}[profile.weekFocus?.[i]]||'tren_superior'):
    kind==='cardio'?'cardio':kind==='mobility'?'movilidad':null;
   if(code)counts[code]++;
  }
  const largest=Math.max(...Object.values(counts));
  const categories=largest>0?CODE.filter(code=>counts[code]===largest):CODE.slice();
  const experience={basic:'principiante',intermediate:'intermedio',advanced:'avanzado'}[profile.difficulty]||'principiante';
  const desired=profile.trainingMode==='routines'?profile.routineExercises3878:profile.dailyMissions;
  const dailyActivities=Number.isInteger(Number(desired))?Math.max(1,Math.min(7,Number(desired))):3;
  return {categories,experience,dailyActivities,volumeSuggestions:true,
   timeZone:validZone(profile.timeZone)?profile.timeZone:validZone(raw.timeZone)?raw.timeZone:'system',language:'es'};
 }
 function validZone(s){if(s==='system')return true;if(typeof s!=='string'||s.length>60)return false;try{new Intl.DateTimeFormat('es',{timeZone:s});return true;}catch(_){return false;}}
 function persist(s){
  if(typeof state==='undefined'||!state)return false;
  state.portable3868={...s,categories:s.categories.slice()};
  const ok=typeof save==='function'?save():false;
  root.HunterIntegration3869?.preferenceChanged?.();
  return ok;
 }
 function setField(field,value){
  const s=settings();
  if(field==='experience'&&['principiante','intermedio','avanzado'].includes(value))s.experience=value;
  else if(field==='dailyActivities'&&Number.isInteger(Number(value))&&Number(value)>=1&&Number(value)<=7)s.dailyActivities=Number(value);
  else if(field==='volumeSuggestions')s.volumeSuggestions=value===true;
  else if(field==='timeZone'&&validZone(value))s.timeZone=value;
  else if(field==='language')s.language='es';
  else return false;
  return persist(s);
 }
 function toggleCategory(code,enabled,node){
  if(!CATEGORY_KEYS.has(code))return false;
  const s=settings(),curr=new Set(s.categories);
  if(enabled)curr.add(code);else curr.delete(code);
  if(!curr.size){if(node)node.checked=true;if(typeof toast==='function')toast('Mantén al menos una categoría.');return false;}
  s.categories=CODE.filter(c=>curr.has(c));return persist(s);
 }
 const taxonomy={category:'all',family:'all',type:'all'};
 function familyLabel(key){
  const raw=key?.split(':')[1]||'';
  if(raw.startsWith('ast_'))return astFamilies.get(raw.slice(4))?.[1]||titleCase(raw.slice(4));
  const name=names[raw];
  return name?name.replace(/\s*[·–]\s*(retorno lento|pausa de control|ritmo suave|intervalos breves)$/i,''):titleCase(raw);
 }
 function typeLabel(key){const raw=key?.split(':').slice(2).join(':')||'';return raw.startsWith('ast_')?(astTypes.get(raw.slice(4))?.[2]||titleCase(raw.slice(4))):titleCase(raw);}
 function allFamilyIds(category){return [...new Set([...map.values()].filter(r=>category==='all'||r.category===category).map(r=>r.family))].sort((a,b)=>familyLabel(a).localeCompare(familyLabel(b),'es'));}
 function allTypeIds(category,family){return [...new Set([...map.values()].filter(r=>(category==='all'||r.category===category)&&(family==='all'||r.family===family)).map(r=>r.type))].sort((a,b)=>typeLabel(a).localeCompare(typeLabel(b),'es'));}
 function filterExercise(e){
  const m=exerciseMeta(e?.id);return !!m&&(taxonomy.category==='all'||m.category===taxonomy.category)&&
   (taxonomy.family==='all'||m.family===taxonomy.family)&&(taxonomy.type==='all'||m.type===taxonomy.type);
 }
 function selectCategory(code){
  taxonomy.category=code==='all'||CATEGORY_KEYS.has(code)?code:'all';taxonomy.family='all';taxonomy.type='all';
  if(typeof render==='function')render();
 }
 function selectFamily(f){
  taxonomy.family=f==='all'||allFamilyIds(taxonomy.category).includes(f)?f:'all';taxonomy.type='all';
  if(typeof render==='function')render();
 }
 function selectType(t){
  taxonomy.type=t==='all'||allTypeIds(taxonomy.category,taxonomy.family).includes(t)?t:'all';
  if(typeof render==='function')render();
 }
 function filtersMarkup(){
  const s=settings();
  const categories=[['all','Todas las categorías'],...D.categories.map(c=>[c.code,c.label+(s.categories.includes(c.code)?'':' · no priorizada')])];
  const opt=(xs,value)=>xs.map(([key,label])=>`<option value="${escSafe(key)}" ${key===value?'selected':''}>${escSafe(label)}</option>`).join('');
  const fam=[['all','Todas las familias'],...allFamilyIds(taxonomy.category).map(f=>[f,familyLabel(f)])];
  const typ=[['all','Todos los tipos'],...allTypeIds(taxonomy.category,taxonomy.family).map(t=>[t,typeLabel(t)])];
  return `<section class="portable-taxonomy3868 card" aria-label="Clasificación de ejercicios">
    <div class="portable-heading3868"><strong>Categorías · Familias · Tipos</strong><small>Asterion adaptado a ${map.size} ejercicios Hunter. Filtros combinables con equipo y nivel.</small></div>
    <div class="portable-filtergrid3868">
     <label>Categoría<select aria-label="Categoría de entrenamiento" onchange="HunterPortable3868.selectCategory(this.value)">${opt(categories,taxonomy.category)}</select></label>
     <label>Familia<select aria-label="Familia de movimiento" onchange="HunterPortable3868.selectFamily(this.value)">${opt(fam,taxonomy.family)}</select></label>
     <label>Tipo<select aria-label="Tipo de movimiento" onchange="HunterPortable3868.selectType(this.value)">${opt(typ,taxonomy.type)}</select></label>
    </div>
    <p class="portable-foot3868">Las equivalencias entre ejercicios de Asterion y Hunter se conservan separadas; no se importan cantidades automáticamente.</p>
  </section>`;
 }
 function settingsMarkup(){
  const s=settings();
  const checks=D.categories.map(c=>`<label class="portable-option3868"><input type="checkbox" ${s.categories.includes(c.code)?'checked':''} onchange="HunterPortable3868.toggleCategory('${c.code}',this.checked,this)"><span>${escSafe(c.label)}</span></label>`).join('');
  const choices=[['principiante','Principiante'],['intermedio','Intermedio'],['avanzado','Avanzado']].map(([v,n])=>`<option value="${v}" ${s.experience===v?'selected':''}>${n}</option>`).join('');
  const count=[1,2,3,4,5,6,7].map(n=>`<option value="${n}" ${s.dailyActivities===n?'selected':''}>${n}</option>`).join('');
  const zones=[['system','La del dispositivo'],['America/Lima','América / Lima'],['UTC','UTC'],['Europe/Madrid','Europa / Madrid']];
  if(!zones.some(z=>z[0]===s.timeZone))zones.push([s.timeZone,s.timeZone]);
  const tz=zones.map(([v,n])=>`<option value="${escSafe(v)}" ${s.timeZone===v?'selected':''}>${escSafe(n)}</option>`).join('');
  return `<section class="card portable-settings3868" data-profile-pane3857="3" aria-label="Configuración de categorías y volumen">
   <h3>Motor de categorías y progreso de volumen</h3>
   <p class="muted">Preferencias independientes de Hunter. No sustituyen tus misiones ni tu historial existente.</p>
   <fieldset class="portable-fieldset3868"><legend>Categorías de interés</legend><div class="portable-categories3868">${checks}</div></fieldset>
   <div class="portable-filtergrid3868">
    <label>Experiencia orientativa<select onchange="HunterPortable3868.setField('experience',this.value)">${choices}</select></label>
    <label>Actividades por día (preferencia)<select onchange="HunterPortable3868.setField('dailyActivities',this.value)">${count}</select></label>
    <label>Zona horaria de consulta<select onchange="HunterPortable3868.setField('timeZone',this.value)">${tz}</select></label>
    <label>Idioma del catálogo<select disabled><option>Español</option></select></label>
   </div>
   <label class="portable-option3868"><input type="checkbox" ${s.volumeSuggestions?'checked':''} onchange="HunterPortable3868.setField('volumeSuggestions',this.checked)"><span>Mostrar orientaciones opcionales basadas en esfuerzo registrado</span></label>
   <p class="portable-foot3868">Desactivadas por defecto. No aumenta repeticiones, series, cargas ni actividades automáticamente. Las preferencias se guardan junto con la copia de seguridad de Hunter.</p>
  </section>`;
 }
 function categoryOf(id){return exerciseMeta(id)?.category||null;}
 function volumeRecords(logs){
  const records=[];
  for(const [day,log] of Object.entries(logs||{})){
   if(!/^\d{4}-\d{2}-\d{2}$/.test(day)||!log||!Array.isArray(log.items))continue;
   for(const item of log.items){
    const meta=exerciseMeta(item?.id),ex=E.byId(item?.id);
    if(!meta||!ex||!Array.isArray(item.actual))continue;
    const unit=ex.unit==='sec'?'SECONDS':'REPETITIONS';
    const completed=item.actual.filter(a=>a&&a.done===true&&Number.isFinite(Number(a.value))&&Number(a.value)>0).map(a=>Number(a.value));
    if(!completed.length)continue;
    records.push({date:day,id:ex.id,category:meta.category,unit,sets:completed.length,quantity:completed.reduce((a,b)=>a+b,0)*(E.sidesOf(item)?2:1),effort:Number.isInteger(+log.rpe)?+log.rpe:0,interrupted:log.pain===true});
   }
  }
  return records.sort((a,b)=>a.date.localeCompare(b.date));
 }
 function trend(logs,from,to){
  const result=Object.fromEntries(CODE.map(c=>[c,{reps:0,seconds:0,sets:0,sessions:new Set(),lastEffort:0,pain:false,ids:new Set()}]));
  for(const r of volumeRecords(logs)){
   if(from&&r.date<from||to&&r.date>to)continue;
   const obj=result[r.category];if(!obj)continue;
   if(r.unit==='SECONDS')obj.seconds+=r.quantity;else obj.reps+=r.quantity;
   obj.sets+=r.sets;obj.sessions.add(r.date);obj.ids.add(r.id);
   if(r.effort)obj.lastEffort=r.effort;
   if(r.interrupted)obj.pain=true;
  }
  return result;
 }
 function asDate(day){return new Date(day+'T12:00:00');}
 function dateOffset(day,delta){const t=asDate(day);t.setDate(t.getDate()+delta);const y=t.getFullYear(),m=String(t.getMonth()+1).padStart(2,'0'),d=String(t.getDate()).padStart(2,'0');return `${y}-${m}-${d}`;}
 function checkpoints(logs,category,now){
  const dates=volumeRecords(logs).filter(r=>r.category===category).map(r=>r.date);
  if(!dates.length)return null;
  const first=dates[0],elapsed=Math.max(0,Math.round((asDate(now)-asDate(first))/864e5));
  return {first,elapsed,checkpoint:elapsed>=21?21:elapsed>=14?14:elapsed>=7?7:0};
 }
 function effortGuidance(logs,category,now){
  const rows=volumeRecords(logs).filter(r=>r.category===category&&r.date<now&&r.date>=dateOffset(now,-7));
  if(!rows.length)return 'Aún no hay un registro reciente de esfuerzo en esta categoría.';
  const last=rows[rows.length-1];
  if(last.interrupted)return 'La última sesión registró una interrupción. Conviene priorizar descanso y revisar la actividad.';
  if(last.effort>=8)return 'Se registró esfuerzo alto. Prioriza recuperación y una variante cómoda; no se aumentará el volumen.';
  if(last.effort>=4)return 'Se registró esfuerzo moderado. Mantén una rutina cómoda, sin cambios automáticos.';
  if(last.effort>=1)return 'Se registró esfuerzo ligero. Puedes revisar tu técnica y opciones al mismo nivel, sin obligación de aumentar volumen.';
  return 'Sin valoración de esfuerzo suficiente para proponer cambios.';
 }
 function volumeMarkup(){
  const s=settings();
  const today=typeof E.dateKey==='function'?E.dateKey():new Date().toISOString().slice(0,10);
  const from=dateOffset(today,-27);const recent=trend(typeof state!=='undefined'?state.logs:{},from,today);
  const cards=D.categories.map(c=>{const r=recent[c.code],checkpoint=checkpoints(state.logs,c.code,today);return `<div class="portable-volumeitem3868"><strong>${escSafe(c.label)}</strong>
   <div><span>${r.reps.toLocaleString('es')} rep</span><span>${r.seconds.toLocaleString('es')} s</span></div>
   <small>${r.sets} series registradas · ${r.sessions.size} días con registro</small>
   ${checkpoint?.checkpoint?`<small>Revisión informativa: día ${checkpoint.checkpoint} desde el primer registro de esta categoría</small>`:''}
   ${s.volumeSuggestions?`<p>${escSafe(effortGuidance(state.logs,c.code,today))}</p>`:''}</div>`;}).join('');
  const exact=D.matchedCount;
  return `<section class="portable-volume3868 card" aria-labelledby="portable-vol-title3868">
    <div class="portable-heading3868"><strong id="portable-vol-title3868">Volumen registrado por categoría</strong><small>Últimos 28 días · series completadas con cantidad real · no incluye confirmaciones sin datos</small></div>
    <div class="portable-volumegrid3868">${cards}</div>
    <p class="portable-foot3868">Repeticiones y segundos se contabilizan por separado. Es un registro descriptivo, no una meta. Las ${exact} equivalencias de nombre y categoría con Asterion no activan automáticamente sus dosis; las demás variantes conservan únicamente el historial de Hunter.</p>
   </section>`;
 }
 function matchedProfile(id){
  const meta=exerciseMeta(id);if(!meta?.asterion)return null;
  const variant=astVariants.get(meta.asterion),profile=astProfiles.get(meta.asterion);
  if(!variant||!profile)return null;
  return {hunterId:id,asterionId:variant[0],typeId:variant[1],name:variant[2],unit:profile[1],original:profile.slice(2),source:'coincidencia exacta única de nombre, NO equivalencia técnica certificada'};
 }
 // Integrate without replacing the old catalogue, its filters, or the current volume engine.
 if(typeof exerciseList==='function'){
  const previous=exerciseList;
  exerciseList=function(){return previous.apply(this,arguments).filter(filterExercise);};
 }
 if(typeof book==='function'){
  const previous=book;
  book=function(){const html=previous.apply(this,arguments);if(html.includes('portable-taxonomy3868'))return html;
   const marker='<p class="result-count" id="exerciseCount"';
   return html.includes(marker)?html.replace(marker,filtersMarkup()+marker):filtersMarkup()+html;
  };
 }
 if(typeof profileForm==='function'){
  const previous=profileForm;
  profileForm=function(){const html=previous.apply(this,arguments);
   if(html.includes('portable-settings3868'))return html;
   const idx=html.lastIndexOf('</form>');
   return idx>=0?html.slice(0,idx)+settingsMarkup()+html.slice(idx):html;
  };
 }
 if(typeof history==='function'){
  const previous=history;
  history=function(){const html=previous.apply(this,arguments);
   return html.includes('portable-volume3868')?html:volumeMarkup()+html;
  };
 }
 root.HunterPortable3868=Object.freeze({categories:D.categories.slice(),exerciseMeta,settings,setField,toggleCategory,
  selectCategory,selectFamily,selectType,filterExercise,matchedProfile,volumeRecords,trend,checkpoints,effortGuidance,
  audit:Object.freeze({hunterExercises:map.size,asterionFamilies:D.asterionFamilies.length,asterionTypes:D.asterionTypes.length,asterionVariants:D.asterionVariants.length,profileRows:D.asterionProfiles.length,exactMatches:D.matchedCount,mode:'non-destructive'})});
})(window);

;
/* Hunter System 38.69 – Categories -> planning, routines and actual-volume journal.
 * Never copies Asterion numeric prescriptions or rewrites historical sessions.
 * Uses only pre-screened candidates provided by Hunter's planning engine.
 */
(function(root){
 'use strict';
 if(root.HunterIntegration3869)return;
 const E=root.Engine,P=root.HunterPortable3868,D=root.HunterPortableData3868;
 if(!E||!P||!D||D.hunterRows.length!==1200)throw Error('Integration3869: faltan datos base');
 const CATS=D.categories.map(c=>c.code),CSET=new Set(CATS),labels=Object.fromEntries(D.categories.map(c=>[c.code,c.label]));
 const safe=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const categoryOf=id=>P.exerciseMeta(id)?.category||null;
 const preferences=()=>P.settings();
 const day=()=>E.dateKey();
 const sum=x=>x.reduce((a,b)=>a+b,0);
 const effectiveDate=()=>{
  const zone=preferences().timeZone;
  if(!zone||zone==='system')return day();
  try{
   const parts=new Intl.DateTimeFormat('en-US',{timeZone:zone,year:'numeric',month:'2-digit',day:'2-digit'}).formatToParts(new Date());
   const get=k=>parts.find(p=>p.type===k)?.value;
   return `${get('year')}-${get('month')}-${get('day')}`;
  }catch(_){return day();}
 };
 function prefers(exercises){
  const allowed=new Set(preferences().categories);
  if(allowed.size===CATS.length)return exercises;
  const preferred=exercises.filter(e=>allowed.has(categoryOf(e.id)));
  // Never make an otherwise safe strength/mobility session empty just because
  // the selected categories contain no compatible exercise of that kind.
  return preferred.length?preferred:exercises;
 }
 function activityLimit(cap){
  if(!Number.isFinite(Number(cap)))return cap;
  return Math.max(1,Math.min(Math.floor(Number(cap)),preferences().dailyActivities));
 }
 let lastFingerprint=JSON.stringify([preferences().categories,preferences().dailyActivities]),invalidated=0;
 function preferenceChanged(){
  const pref=preferences();
  const next=JSON.stringify([pref.categories,pref.dailyActivities]);
  if(next===lastFingerprint)return 0;
  lastFingerprint=next;
  const today=day();let count=0;
  if(typeof state==='undefined'||!state||!Array.isArray(state.cycles)||!state.profile)return 0;
  for(const c of state.cycles){
   if(!Array.isArray(c.days))continue;
   let changed=false;
   for(const d of c.days){
    if(d.date<today||d.customized||d.routineId||d.creatorAssigned||d.manual||d.scheduleChoice==='rest')continue;
    // Check the established session and planner locks before invalidation.
    if(root.HunterDynamic381?.locked?.(d,state.logs||{}))continue;
    delete d.dynamicKey381;changed=true;count++;
   }
   if(changed)delete c.dynamicKey381;
  }
  invalidated+=count;
  if(count&&typeof save==='function')save();
  return count;
 }
 function completedSets(logs){
  const out=[];
  for(const [date,log] of Object.entries(logs||{})){
   if(!/^\d{4}-\d{2}-\d{2}$/.test(date)||!log||!Array.isArray(log.items))continue;
   for(const item of log.items){
    const e=E.byId(item?.id),category=categoryOf(item?.id);
    if(!e||!category||!Array.isArray(item.actual))continue;
    const unit=e.unit==='sec'?'seconds':'reps';
    const factor=E.sidesOf(item)?2:1;
    const done=item.actual.filter(a=>a&&a.done===true&&Number.isFinite(Number(a.value))&&Number(a.value)>0&&Number(a.value)<=10000);
    if(!done.length)continue;
    const quantity=sum(done.map(a=>Number(a.value)*factor));
    out.push({date,id:e.id,name:e.name,category,unit,sets:done.length,quantity,
      effort:Number.isFinite(Number(log.rpe))?Number(log.rpe):null,interrupted:log.pain===true});
   }
  }
  return out.sort((a,b)=>a.date.localeCompare(b.date)||a.id.localeCompare(b.id));
 }
 function exerciseSummary(logs,today=effectiveDate(),windowDays=28){
  const start=E.plus(today,1-windowDays),buckets=new Map();
  for(const r of completedSets(logs)){
   if(r.date<start||r.date>today)continue;
   const key=r.id+'|'+r.unit;
   let b=buckets.get(key);
   if(!b){b={id:r.id,name:r.name,category:r.category,unit:r.unit,quantity:0,sets:0,days:new Set(),last:'',first:''};buckets.set(key,b);}
   b.quantity+=r.quantity;b.sets+=r.sets;b.days.add(r.date);
   if(!b.first)b.first=r.date;
   b.last=r.date;
  }
  return [...buckets.values()].map(b=>({...b,days:b.days.size})).sort((a,b)=>b.last.localeCompare(a.last)||b.sets-a.sets||a.name.localeCompare(b.name,'es'));
 }
 function referenceByExercise(logs,id){
  const rows=completedSets(logs).filter(r=>r.id===id&&!r.interrupted&&r.effort!==null&&r.effort>=1&&r.effort<=7);
  if(!rows.length)return null;
  const first=rows[0];
  return {date:first.date,id,category:first.category,unit:first.unit,quantity:first.quantity,sets:first.sets,
    source:'primera sesión registrada con esfuerzo informado, no dosis recomendada'};
 }
 function ledger(){
  const recent=exerciseSummary(state.logs||{}),counts=recent.length,total=recent.reduce((t,r)=>t+r.sets,0);
  const shown=recent.slice(0,10);
  const html=shown.map(r=>{const ref=referenceByExercise(state.logs||{},r.id);
   const base=ref&&ref.unit===r.unit?` · referencia personal: ${ref.quantity.toLocaleString('es')} ${r.unit==='seconds'?'s':'rep'} (${ref.date})`:'';
   return `<li><span><strong>${safe(r.name)}</strong><small>${safe(labels[r.category])} · ${r.days} días · ${r.sets} series${base}</small></span><b>${r.quantity.toLocaleString('es')} ${r.unit==='seconds'?'s':'rep'}</b></li>`;
  }).join('');
  return `<section class="card portable-detail3869" aria-label="Progreso por ejercicio"><div class="portable-detail-heading3869">
   <h3>Registro por ejercicio</h3><p>${counts} ejercicios con cantidades en los últimos 28 días · ${total} series registradas.</p></div>
   ${counts?`<ul>${html}</ul>${counts>10?`<p class="portable-hint3869">Se muestran los 10 ejercicios con actividad más reciente.</p>`:''}`:'<p class="portable-hint3869">Registra una serie terminada para ver el seguimiento individual.</p>'}
   <p class="portable-hint3869">Las referencias individuales provienen de tu historial, nunca de las dosis importadas de Asterion. Repeticiones y segundos no se mezclan. No son objetivos de carga.</p>
  </section>`;
 }
 function planNotice(){
  const c=preferences(),names=c.categories.map(k=>labels[k]).join(' · ');
  return `<div class="portable-plan3869" role="note"><strong>Planificación según tus categorías</strong><p>Prioridad: ${safe(names)}. Máximo orientativo de ${c.dailyActivities} actividades por sesión, respetando las restricciones y el calendario. Si no hay ejercicios compatibles de las categorías elegidas, se conserva el tipo de entrenamiento y se utilizan alternativas seguras.</p></div>`;
 }
 function enhanceRoutines(){
  const names=preferences().categories.map(c=>labels[c]).join(' · ');
  return `<div class="portable-plan3869" role="note"><strong>Rutinas y categorías</strong><p>Las rutinas dinámicas dan prioridad a ${safe(names)}. Las rutinas guardadas y las plantillas anteriores permanecen disponibles y no cambian sus series.</p></div>`;
 }
 // Rank compatible routines by the selected categories, but preserve every
 // original/custom routine and their relative order when scores tie.
 if(typeof routineList==='function'){
  const previous=routineList;
  routineList=function(){
   const list=previous.apply(this,arguments);
   const selected=new Set(preferences().categories);
   if(selected.size===CATS.length)return list;
   const score=r=>{
    if(!Array.isArray(r?.items)||!r.items.length)return 0;
    const cats=r.items.map(x=>categoryOf(x.id)).filter(Boolean);
    return cats.length?cats.filter(c=>selected.has(c)).length/cats.length:0;
   };
   return list.map((r,i)=>({r,i,score:score(r)})).sort((a,b)=>b.score-a.score||a.i-b.i).map(x=>x.r);
  };
 }
 // Pages: preserve all preexisting drawers, route controls and entries.
 if(typeof plan==='function'){
  const original=plan;plan=function(){return planNotice()+original.apply(this,arguments);};
 }
 if(typeof routines==='function'){
  const original=routines;routines=function(){return enhanceRoutines()+original.apply(this,arguments);};
 }
 if(typeof history==='function'){
  const original=history;history=function(){return original.apply(this,arguments).replace('</section>', '</section>'+ledger());};
 }
 if(typeof profileForm==='function'){
  const original=profileForm;
  profileForm=function(initial){
   const html=original.apply(this,arguments);
   if(!initial)return html;
   const note=`<div class="portable-plan3869" role="note"><strong>Configuración inicial de Asterion</strong><p>En la pestaña «Sistema» puedes elegir categorías, experiencia y cantidad orientativa de actividades. Estos ajustes afectan solo a nuevas sesiones, no al historial.</p><button class="btn ghost" type="button" onclick="HunterPanels3857.show(3)">Configurar categorías →</button></div>`;
   return html.replace('<form id="profileForm"',note+'<form id="profileForm"');
  };
 }
 // The existing portable 38.68 module writes preferences into state.portable3868
 // and calls preferenceChanged; legacy backups keep their original structure.
 root.HunterIntegration3869=Object.freeze({version:'38.69',prefers,activityLimit,preferenceChanged,
  categoryOf,completedSets,exerciseSummary,referenceByExercise,effectiveDate,
  audit:()=>({exercises:D.hunterRows.length,matchedCandidates:D.matchedCount,invalidatedFutureDays:invalidated,
  categories:CATS.slice(),numericAsterionDosesApplied:0})});
})(window);

;
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

;
/* Asterion 38.72 — deterministic offline rules. No exercise catalogue is bundled.
 * Pure logic for an injected, validated catalogue; no PII-based capacity scoring.
 * Public UMD API supports browser script and Node.js test harness.
 */
(function(factory){
 const api=factory();
 if(typeof module==='object'&&module.exports)module.exports=api;
 if(typeof window!=='undefined')window.AsterionRules3872=api;
})(function(){'use strict';
 const CATS=Object.freeze(['tren_superior','tren_central','tren_inferior','movilidad','cardio']);
 const LABELS=Object.freeze(['Tren superior','Tren central','Tren inferior','Movilidad','Cardio','Descanso']);
 const ALIASES=Object.freeze({tronco:'tren_central',integrado:'tren_central',piernas:'tren_inferior',coordinacion:'movilidad'});
 const STATES=Object.freeze(['PENDING','COMPLETED','SKIPPED']);
 const STOPWORDS=new Set(['de','del','para','la','el','en','por','una','un','me','mi','que','quiero','hacer','a','mas','mejorar','poder']);
 const INTENTS=Object.freeze({
  caminar:['caminar','caminata','andar','pasear'],bicicleta:['bicicleta','ciclismo','pedalear','bici'],
  correr:['correr','carrera','trotar'],danza:['danza','bailar','baile'],equilibrio:['equilibrio','balance'],
  movilidad:['movilidad','moverme'],flexibilidad:['flexibilidad','estiramiento','estirar'],
  postura:['postura','postural'],fuerza_funcional:['fuerza funcional','fuerza'],
  coordinacion:['coordinacion','coordinado'],resistencia:['resistencia','aguante']
 });
 const ISO=/^\d{4}-\d{2}-\d{2}$/;
 const clone=x=>JSON.parse(JSON.stringify(x));
 const normalized=s=>String(s||'').toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g,'').replace(/[^a-z0-9_\s]/g,' ').replace(/\s+/g,' ').trim();
 function cat(v){const s=normalized(v);return CATS.includes(s)?s:(ALIASES[s]||null);}
 const dayIndex=code=>code==='descanso'?5:CATS.indexOf(code);
 function date(day){if(typeof day!=='string'||!ISO.test(day))return null;const d=new Date(day+'T12:00:00Z');return !Number.isNaN(d.getTime())&&d.toISOString().slice(0,10)===day?d:null;}
 function addDays(day,delta){const d=date(day);if(!d)throw Error('Fecha inválida');d.setUTCDate(d.getUTCDate()+delta);return d.toISOString().slice(0,10);}
 function distance(a,b){const x=date(a),y=date(b);if(!x||!y)throw Error('Fecha inválida');return Math.round((x-y)/86400000);}
 function weekday(day){const d=date(day);if(!d)throw Error('Fecha inválida');return (d.getUTCDay()+6)%7;}
 function canonical(v){if(Array.isArray(v))return '['+v.map(canonical).join(',')+']';if(v&&typeof v==='object')return '{'+Object.keys(v).sort().map(k=>JSON.stringify(k)+':'+canonical(v[k])).join(',')+'}';return JSON.stringify(v);}
 // Non-cryptographic deterministic change detector; not for password security.
 function fingerprint(x){let h=2166136261;const s=canonical(x);for(let i=0;i<s.length;i++){h^=s.charCodeAt(i);h=Math.imul(h,16777619)>>>0;}return h.toString(16).padStart(8,'0');}
 function parseGoal(raw){const text=normalized(raw).slice(0,500),found=[],excluded=[];
  for(const [tag,terms] of Object.entries(INTENTS))for(const term of terms){
   const at=text.indexOf(term);if(at<0||!new RegExp('(^|\\s)'+term.replace(/\s+/g,'\\s+')+'(\\s|$)').test(text))continue;
   // Local negation within 5 words, beginning of clause; explicit exclusion dominates.
   const before=text.slice(Math.max(0,at-55),at).split(/\bpero\b|\by\b|[.;]/).at(-1)||'';
   const neg=/(?:\bno\s+(?:quiero|deseo|puedo|hacer|practicar)?\s*$|\bevitar\s*$|\bsin\s*$|\bno\s+me\s+interesa\s*$|\bdejar\s+de\s*$)/.test(before.trim()+' ');
   (neg?excluded:found).push(tag);break;
  }
  return {recognized:[...new Set(found)].filter(t=>!excluded.includes(t)),excluded:[...new Set(excluded)],recognizedAny:found.length+excluded.length>0,savedText:String(raw||'').slice(0,500)};
 }
 function validProfile(p){const errors=[];
  if(!p||typeof p!=='object')return {ok:false,errors:['No hay perfil activado']};
  if(!['principiante','intermedio','avanzado'].includes(p.experience))errors.push('Experiencia inválida');
  if(!Array.isArray(p.weekPlan)||p.weekPlan.length!==7||!p.weekPlan.every(x=>Number.isInteger(x)&&x>=0&&x<=5))errors.push('Calendario de siete días inválido');
  if(!Array.isArray(p.equipment)||!p.equipment.every(x=>typeof x==='string'&&x.length<80))errors.push('Equipamiento inválido');
  if(!['misiones','rutinas'].includes(p.modality))errors.push('Modalidad inválida');
  if(!Number.isInteger(p.dailyLimit)||p.dailyLimit<1||p.dailyLimit>12)errors.push('Límite diario inválido');
  if(!['baja','moderada'].includes(p.intensity))errors.push('Intensidad inválida');
  if(typeof p.custom_goal!=='string'||p.custom_goal.length>500)errors.push('Objetivo escrito inválido');
  if(!Number.isInteger(p.minutes)||p.minutes<5||p.minutes>240)errors.push('Minutos inválidos');
  if(!Number.isInteger(p.restSeconds)||p.restSeconds<0||p.restSeconds>1800)errors.push('Descanso entre bloques inválido');
  if(!['series','rondas'].includes(p.organization))errors.push('Organización inválida');
  if(!Array.isArray(p.restrictions))errors.push('Restricciones inválidas');
  return {ok:!errors.length,errors};
 }
 function defaultProfile(){return {active:false,experience:'principiante',goal:'',custom_goal:'',weekPlan:[5,5,5,5,5,5,5],equipment:[],modality:'misiones',dailyLimit:3,intensity:'baja',restSeconds:90,organization:'series',minutes:20,restrictions:[]};}
 function strictList(x,name){if(!Array.isArray(x))throw Error(name+' no es una lista');return x;}
 function catalog(provider){
  if(!provider||typeof provider!=='object')throw Error('Debe inyectarse un catálogo externo');
  const cats=strictList(provider.categories?.(),'categories'),families=strictList(provider.families?.(),'families'),types=strictList(provider.types?.(),'types'),variants=strictList(provider.variants?.(),'variants');
  const assertUnique=(xs,label)=>{const ids=new Set();for(const v of xs){if(!v||typeof v.id!=='string'||!/^[A-Za-z0-9][A-Za-z0-9_.:-]{0,127}$/.test(v.id)||ids.has(v.id))throw Error('ID duplicado o vacío: '+label);ids.add(v.id);}return ids;};
  const categoryIds=assertUnique(cats,'categoria'),familyIds=assertUnique(families,'familia'),typeIds=assertUnique(types,'tipo'),variantIds=assertUnique(variants,'variante');
  if(cats.length!==5||!CATS.every(x=>categoryIds.has(x)))throw Error('El catálogo debe declarar las cinco categorías exactas');
  const fm=new Map(families.map(x=>[x.id,x])),tm=new Map(types.map(x=>[x.id,x])),vm=new Map();
  for(const f of families)if(!categoryIds.has(cat(f.category)))throw Error('Familia con categoría inexistente: '+f.id);
  for(const t of types)if(!familyIds.has(t.family))throw Error('Tipo con familia inexistente: '+t.id);
  for(const v of variants){
   if(!typeIds.has(v.type))throw Error('Variante con tipo inexistente: '+v.id);
   const t=tm.get(v.type),f=fm.get(t.family),c=cat(f.category);
   if(v.family!==undefined&&v.family!==f.id||v.category!==undefined&&cat(v.category)!==c)throw Error('Relación inconsistente: '+v.id);
   if(!Number.isInteger(v.level)||v.level<0||v.level>5)throw Error('Nivel técnico inválido: '+v.id);
   if(!Array.isArray(v.equipment)||v.equipment.some(x=>typeof x!=='string'))throw Error('Equipamiento inválido: '+v.id);
   if(typeof v.supervisionRequired!=='boolean'||typeof v.automaticEligible!=='boolean')throw Error('Elegibilidad sin validar: '+v.id);
   if(!['baja','moderada','alta'].includes(v.intensity))throw Error('Intensidad inválida: '+v.id);
   if(typeof v.modality!=='string'||!v.modality)throw Error('Modalidad sin validar: '+v.id);
   if(!Array.isArray(v.restrictions)||!Array.isArray(v.tags))throw Error('Etiquetas/restricciones inválidas: '+v.id);
   vm.set(v.id,Object.freeze({...v,category:c,family:f.id,type:t.id}));
  }
  const frozen=Object.freeze([...vm.values()].sort((a,b)=>a.id.localeCompare(b.id)));
  const revision=String(provider.revision||'sin-version');
  const api={revision,all:()=>frozen.slice(),byId:id=>vm.get(id)||null,
   categories:()=>CATS.slice(),families:c=>families.filter(f=>cat(f.category)===cat(c)).map(f=>({...f})),
   types:family=>types.filter(t=>t.family===family).map(t=>({...t})),
   variants:type=>frozen.filter(v=>v.type===type),categoryOf:id=>vm.get(id)?.category||null,
   familyOf:id=>vm.get(id)?.family||null,find:query=>frozen.filter(v=>normalized(v.label||v.id).includes(normalized(query))),
   filter:fn=>frozen.filter(fn),check:v=>vm.has(v.id)};
  return Object.freeze(api);
 }
 function compatible(v,p){const reasons=[];
  if(v.level===0||v.level>=4||v.supervisionRequired||!v.automaticEligible)reasons.push('No apta para asignación automática');
  if(p.experience==='principiante'&&v.level>2)reasons.push('Complejidad técnica fuera del perfil');
  if(p.experience==='avanzado'&&v.level===1)reasons.push('Se priorizan niveles 2 y 3');
  if(v.intensity==='alta'||p.intensity==='baja'&&v.intensity!=='baja')reasons.push('Intensidad no compatible');
  if(v.equipment.some(x=>!p.equipment.includes(x)))reasons.push('Equipo no declarado');
  if(v.restrictions.some(x=>p.restrictions.includes(x)))reasons.push('Restricción del perfil');
  if(p.blockedVariantIds?.includes(v.id))reasons.push('Variante excluida por el perfil');
  return {ok:!reasons.length,reasons};
 }
 function safeItems(vs,p){return vs.filter(v=>compatible(v,p).ok);}
 function stateEmpty(){return {version:1,pins:{},outcomes:{},categoryEfforts:[],globalEfforts:[],volumes:[],phases:{},initialExperienceDate:null,revisionEvents:[]};}
 function dataState(s){return s&&s.version===1?s:stateEmpty();}
 function recordOutcome(s,o){const data=dataState(s),dt=o.date,id=o.variantId;
  if(!date(dt)||typeof id!=='string'||!id||!STATES.includes(o.status)||!CATS.includes(cat(o.category)))throw Error('Resultado inválido');
  const key=dt+'|'+id,prev=data.outcomes[key];
  if(prev&&prev.status===o.status&&String(prev.note||'')===String(o.note||'').slice(0,400))return {changed:false,state:data};
  if(prev&&prev.status!=='PENDING'&&prev.status!==o.status)throw Error('Resultado ya evaluado: no se cambia sin corrección explícita');
  data.outcomes[key]={date:dt,variantId:id,category:cat(o.category),status:o.status,note:String(o.note||'').slice(0,400)};
  return {changed:true,state:data};
 }
 function correctOutcome(s,o,reason){const data=dataState(s);const key=o?.date+'|'+o?.variantId;const old=data.outcomes[key];if(!old||!STATES.includes(o.status)||!String(reason||'').trim())throw Error('Corrección explícita y motivo requeridos');data.outcomes[key]={...old,status:o.status,note:String(o.note||old.note||'').slice(0,400)};data.revisionEvents.push({date:o.date,variantId:o.variantId,from:old.status,to:o.status,action:'correccion_explicita',reason:String(reason).slice(0,250)});return data;}
 function completionSummary(s,from,to){const rows=Object.values(dataState(s).outcomes).filter(x=>(!from||x.date>=from)&&(!to||x.date<=to));
  const counts={planned:rows.length,completed:rows.filter(x=>x.status==='COMPLETED').length,skipped:rows.filter(x=>x.status==='SKIPPED').length,pending:rows.filter(x=>x.status==='PENDING').length};
  const assessed=counts.completed+counts.skipped;return {...counts,assessed,percent:assessed?Math.round(100*counts.completed/assessed):null,label:assessed?'Participación evaluada':'Sin datos suficientes'};
 }
 function globalEffort(s,{date:dt,value}){const data=dataState(s);if(!date(dt)||!Number.isInteger(value)||value<1||value>10)throw Error('Valoración global inválida');const j=data.globalEfforts.findIndex(x=>x.date===dt);const g={date:dt,value};if(j>=0)data.globalEfforts[j]=g;else data.globalEfforts.push(g);return data;}
 function effort(s,{date:dt,category:code,value,completed,global=true}){
  const data=dataState(s),c=cat(code);
  if(!date(dt)||!c||!Number.isInteger(value)||value<1||value>10||typeof completed!=='boolean')throw Error('Valoración inválida');
  const item={date:dt,category:c,value,completed};
  const idx=data.categoryEfforts.findIndex(x=>x.date===dt&&x.category===c);
  if(idx>=0)data.categoryEfforts[idx]=item;else data.categoryEfforts.push(item);
  if(global===true)globalEffort(data,{date:dt,value});
  return data;
 }
 function lastEffort(s,category,today){const start=addDays(today,-21);
  return dataState(s).categoryEfforts.filter(x=>x.category===category&&x.date<today&&x.date>=start&&x.completed).sort((a,b)=>b.date.localeCompare(a.date))[0]||null;
 }
 function weekly(s,category,today){const rows=dataState(s).categoryEfforts.filter(x=>x.category===category&&x.completed&&x.date<today).sort((a,b)=>a.date.localeCompare(b.date));
  if(!rows.length)return {status:'sin_datos',reason:'Aún no existe una valoración completada en esta categoría.'};
  const first=rows[0].date;if(distance(today,first)<7)return {status:'espera',reason:'Todavía no se cumple la primera revisión de siete días.'};
  const recent=rows.filter(x=>x.date>=addDays(today,-7));
  if(recent.some(x=>x.value>=8))return {status:'recuperacion',reason:'Se informó esfuerzo alto; prioridad de recuperación.'};
  const lowDays=new Set(recent.filter(x=>x.value>=1&&x.value<=3).map(x=>x.date));
  if(lowDays.size<2)return {status:'insuficiente',reason:'Se necesitan dos días distintos completados y valorados con esfuerzo bajo.'};
  return {status:'variedad',reason:'Dos sesiones comparables y valoradas permiten revisar una variante equivalente, sin aumentar cantidad ni complejidad.'};
 }
 function decision(s,category,today){const prev=lastEffort(s,category,today),w=weekly(s,category,today);
  if(!prev)return {action:'mantener',max:Infinity,why:'Sin valoración anterior de esta categoría.',weekly:w};
  if(prev.value>=9)return {action:'recuperacion',max:1,why:'Esfuerzo 9–10/10: máximo una actividad sencilla y recuperación prioritaria.',weekly:w};
  if(prev.value===8)return {action:'suavizar',max:2,why:'Esfuerzo 8/10: máximo dos actividades y alternativas sencillas.',weekly:w};
  if(prev.value>=4)return {action:'mantener',max:Infinity,why:'Esfuerzo 4–7/10: mantener propuesta.',weekly:w};
  return {action:w.status==='variedad'?'equivalente':'mantener',max:Infinity,why:w.status==='variedad'?'Se permite revisar variedad del mismo tipo y nivel técnico.':'Esfuerzo bajo registrado; mantener hasta disponer de evidencia suficiente.',weekly:w};
 }
 function phaseMix(experience,phase){if(experience==='principiante')return 0;const table=experience==='avanzado'?[.33,.55,.75]:[.25,.40,.60];return table[Math.max(0,Math.min(2,phase||0))];}
 function phaseReview(s,category,today,experience){const data=dataState(s),base=data.initialExperienceDate||today,elapsed=distance(today,base),milestone=[21,14,7].find(n=>elapsed>=n)||0;
  const previous=data.phases[category]||{phase:0,lastChange:null};const w=weekly(data,category,today);
  if(!milestone)return {milestone:0,phase:previous.phase,action:'esperar',why:'Primera revisión técnica en día 7.'};
  if(w.status==='recuperacion')return {milestone,phase:previous.phase,action:'recuperacion',why:w.reason};
  if(experience==='principiante'||previous.phase>=2||w.status!=='variedad'||previous.lastChange&&distance(today,previous.lastChange)<7)return {milestone,phase:previous.phase,action:'mantener',why:'No se cumplen todas las condiciones para avanzar la fase.'};
  // The engine makes a *review proposal*: advancement requires explicit approval.
  return {milestone,phase:previous.phase,action:'revisar_fase',suggestedPhase:previous.phase+1,why:'Revisión opcional de fase por categoría. No habilita niveles 4–5.'};
 }
 function approvePhase(s,category,today,experience){const data=dataState(s);if(!CATS.includes(category))throw Error('Categoría inválida');
  const review=phaseReview(data,category,today,experience);if(review.action!=='revisar_fase')throw Error('Falta evidencia para avanzar la fase');
  const old=data.phases[category]||{phase:0,lastChange:null};if(old.phase>=2||old.lastChange&&distance(today,old.lastChange)<7)throw Error('Intervalo o fase no permitidos');
  data.phases[category]={phase:old.phase+1,lastChange:today};data.revisionEvents.push({date:today,category,action:'fase_aprobada',from:old.phase,to:old.phase+1});return data;
 }
 function globalReview(s,today){const data=dataState(s),rows=data.globalEfforts.filter(x=>x.date<=today&&x.date>=addDays(today,-20)).sort((a,b)=>a.date.localeCompare(b.date));
  const first=[...data.globalEfforts].sort((a,b)=>a.date.localeCompare(b.date))[0]?.date;
  if(!first)return {cycleDay:null,lastMilestone:null,nextMilestone:7,count:0,average:null,reason:'Sin valoraciones suficientes para interpretar tendencia.'};
  const elapsed=Math.max(0,distance(today,first));const cycleDay=elapsed%21+1,passed=[21,14,7].filter(n=>cycleDay>=n).at(-1)||0,next=[7,14,21].find(n=>n>passed)||21;
  const avg=rows.length?Math.round(rows.reduce((a,b)=>a+b.value,0)/rows.length*10)/10:null;
  return {cycleDay,lastMilestone:passed||null,nextMilestone:next,count:rows.length,average:avg,reason:rows.length<2?'Observaciones insuficientes para una tendencia.':'Tendencia descriptiva; no modifica decisiones de categorías.'};
 }
 function volume(s,r,catalogRef){const data=dataState(s);
  if(!date(r.date)||!CATS.includes(cat(r.category))||!['REPETITIONS','SECONDS'].includes(r.unit)||!['COMPLETED','SKIPPED'].includes(r.status)||!Number.isInteger(r.sets)||r.sets<0||!Number.isFinite(r.perSet)||r.perSet<0||
   r.unit==='REPETITIONS'&&!Number.isInteger(r.perSet)||r.effort!=null&&(!Number.isInteger(r.effort)||r.effort<1||r.effort>10))throw Error('Registro de volumen o unidad inválida');
  const v=catalogRef?.byId(r.variantId);if(!v||v.category!==cat(r.category)||v.type!==r.typeId||v.level!==r.level)throw Error('Variante no certificada por catálogo externo');
  const interrupted=r.interrupted===true;
  if(r.status==='COMPLETED'&&!interrupted&&(!r.sets||!r.perSet))throw Error('Faltan cantidades realizadas');
  const key=[r.date,r.variantId,r.unit].join('|');
  const rec={date:r.date,category:v.category,variantId:v.id,typeId:v.type,level:v.level,sets:r.sets,perSet:r.perSet,unit:r.unit,effort:r.effort??null,status:r.status,interrupted};
  const i=data.volumes.findIndex(x=>[x.date,x.variantId,x.unit].join('|')===key);if(i>=0)data.volumes[i]=rec;else data.volumes.push(rec);
  return data;
 }
 function doseHistory(s,id,unit){return dataState(s).volumes.filter(v=>v.variantId===id&&v.unit===unit&&v.status==='COMPLETED'&&!v.interrupted&&v.sets>0&&v.perSet>0).sort((a,b)=>a.date.localeCompare(b.date));}
 function rank(vs,intent,seed,state,category,p){const h=dataState(state);
  const scored=vs.map(v=>{const texts=[...v.tags,v.modality,v.label||''].map(normalized).join(' ');
   const affinity=intent.recognized.filter(x=>texts.includes(normalized(x).replace(/_/g,' '))||texts.includes(x)).length;
   const recent=Object.values(h.outcomes).filter(o=>o.category===category&&o.variantId===v.id&&o.status==='COMPLETED').length;
   return {v,affinity,recent,order:fingerprint(seed+'|'+v.id)};
  });
  return scored.sort((a,b)=>b.affinity-a.affinity||a.recent-b.recent||a.order.localeCompare(b.order)||a.v.id.localeCompare(b.v.id)).map(x=>x.v);
 }
 function generate({catalog:ref,profile:p,state:s,today,pin=true}){
  const errors=validProfile(p);if(!errors.ok)return {status:'perfil_invalido',items:[],reason:errors.errors.join('; ')};
  if(!ref||typeof ref.all!=='function'||!date(today))return {status:'entrada_invalida',items:[],reason:'Fecha o catálogo externo no disponible.'};
  const data=dataState(s),idx=p.weekPlan[weekday(today)],category=idx===5?'descanso':CATS[idx];
  const settings={experience:p.experience,weekPlan:p.weekPlan,equipment:p.equipment.slice().sort(),modality:p.modality,dailyLimit:p.dailyLimit,intensity:p.intensity,restSeconds:p.restSeconds,organization:p.organization,minutes:p.minutes,goal:p.goal,goalTextHash:fingerprint(p.custom_goal),restrictions:p.restrictions.slice().sort(),blockedVariantIds:p.blockedVariantIds||[],catalogRevision:ref.revision};
  const signature=fingerprint(settings),old=data.pins[today];
  if(category==='descanso')return {status:'descanso',category,items:[],reason:'Descanso programado: no se generan actividades.',signature};
  const filtered=safeItems(ref.all().filter(v=>v.category===category),p);
  // Once pinned, retain the same order despite same-day feedback changes.
  if(pin&&old&&old.signature===signature&&old.category===category&&old.revision===ref.revision&&old.ids.every(id=>filtered.some(x=>x.id===id))){return {status:'fijada',category,items:old.ids.map(id=>ref.byId(id)),reason:'Propuesta fijada para esta fecha local.',signature};}
  const d=decision(data,category,today),safe=d.action==='recuperacion'||d.action==='suavizar'?filtered.filter(v=>v.level<=2):filtered;
  if(!safe.length)return {status:'sin_compatibles',category,items:[],reason:'No hay actividades compatibles verificadas con tu perfil y equipo.',signature};
  const intent=parseGoal(p.custom_goal),mainGoal=parseGoal(p.goal);intent.recognized=[...new Set([...mainGoal.recognized,...intent.recognized])].filter(x=>!intent.excluded.includes(x));const phase=data.phases[category]?.phase||0,ratio=phaseMix(p.experience,phase);
  let ordered=rank(safe,intent,today+'|'+category,data,category,p);const result=[],usedTypes=new Set(),usedFamilies=new Set();
  const previous=Object.values(data.outcomes).filter(o=>o.category===category&&o.date<today&&o.status==='COMPLETED')
   .sort((a,b)=>b.date.localeCompare(a.date)||a.variantId.localeCompare(b.variantId));
  const recentDate=previous[0]?.date;
  const previousIds=[...new Set(previous.filter(o=>o.date===recentDate).map(o=>o.variantId))];
  const preferred=[];
  if(d.action==='mantener')for(const id of previousIds){const v=safe.find(x=>x.id===id);if(v)preferred.push(v);}
  if(d.action==='equivalente')for(const id of previousIds){const old=ref.byId(id);if(!old)continue;
   const equivalent=safe.find(v=>v.id!==id&&v.type===old.type&&v.level===old.level);
   const choice=equivalent||safe.find(v=>v.id===id);if(choice&&!preferred.some(x=>x.id===choice.id))preferred.push(choice);
  }
  if(preferred.length)ordered=[...preferred,...ordered.filter(v=>!preferred.some(x=>x.id===v.id))];
  const max=Math.min(p.dailyLimit,d.max,ordered.length);
  for(let j=0;j<max;j++){
   const desired3=p.experience!=='principiante'&&result.filter(v=>v.level===3).length<Math.floor((j+1)*ratio+1e-8);
   const options=ordered.filter(v=>!result.some(r=>r.id===v.id));if(!options.length)break;
   options.sort((a,b)=>{
    const score=v=>(v.level===3&&desired3?10:0)+(v.level!==3&&!desired3?5:0)+(usedFamilies.has(v.family)?0:3)+(usedTypes.has(v.type)?0:2);
    return (preferred.includes(b)?1:0)-(preferred.includes(a)?1:0)||score(b)-score(a)||ordered.indexOf(a)-ordered.indexOf(b);
   });
   const next=options[0];result.push(next);usedTypes.add(next.type);usedFamilies.add(next.family);
  }
  if(pin)data.pins[today]={signature,revision:ref.revision,category,ids:result.map(v=>v.id)};
  return {status:result.length?'generada':'sin_compatibles',category,items:result,reason:d.why,signature,goalRecognized:intent.recognized,goalExcluded:intent.excluded,adaptation:d};
 }
 return Object.freeze({version:'38.72',categories:CATS,labels:LABELS,aliases:ALIASES,states:STATES,normalized,category:cat,addDays,distance,weekday,parseGoal,validProfile,defaultProfile,catalog,compatible,stateEmpty,dataState,recordOutcome,correctOutcome,completionSummary,effort,globalEffort,lastEffort,weekly,decision,phaseMix,phaseReview,approvePhase,globalReview,volume,doseHistory,generate,fingerprint});
});

;
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

;
/* Hunter/Asterion 38.73 — single local, deterministic category-session engine.
 * Does NOT contain exercises; requires an audited catalog provider.
 * Technical progression is a reversible offer, never a mandatory prescription.
 */
(function(factory){
 const R=typeof module==='object'&&module.exports?require('./asterion-rules3872.js'):
  (typeof window!=='undefined'?window.AsterionRules3872:null);
 const api=factory(R);
 if(typeof module==='object'&&module.exports)module.exports=api;
 if(typeof window!=='undefined')window.AsterionUnified3873=api;
})(function(R){'use strict';
 if(!R)throw Error('Falta motor Asterion 38.72');
 const SCALE=Object.freeze({1:5,2:3,3:1,4:0,5:0,6:0,7:0,8:-1,9:-2,10:-3});
 const ISO=/^\d{4}-\d{2}-\d{2}$/;
 const safeDate=d=>typeof d==='string'&&ISO.test(d)&&!Number.isNaN(Date.parse(d+'T12:00:00Z'));
 const validRating=x=>Number.isInteger(x)&&x>=1&&x<=10;
 const clean=x=>JSON.parse(JSON.stringify(x));
 function reference(rating){return validRating(rating)?SCALE[rating]:null;}
 function completedCategories(log,categoryForId){
  const list=(log?.items||[]).filter(item=>item?.actual?.some?.(set=>set?.done===true&&Number(set.value)>0));
  return [...new Set(list.map(item=>categoryForId(item.id)).filter(c=>R.categories.includes(c)))].sort();
 }
 // Exactly one RPE for a completed session. Mixed sessions receive global RPE only.
 function recordSession(data,{date,log,categoryForId}){
  if(!safeDate(date)||!log?.complete||!validRating(Number(log.rpe))||typeof categoryForId!=='function')return {recorded:false,why:'No hay sesión completada y valorada.'};
  const cats=completedCategories(log,categoryForId);
  const value=Number(log.rpe);
  if(cats.length===1)R.effort(data,{date,category:cats[0],value,completed:true,global:true});
  else R.globalEffort(data,{date,value});
  if(!data.categoryFeedback3873)data.categoryFeedback3873=[];
  if(cats.length===1){const q=log.afterFeedback3870||{};
   const entry={date,category:cats[0],value,hasPain:q.symptom==='pain'||q.symptom==='discomfort'||Boolean(log.pain),
    fatigue:q.readiness==='tired'||q.symptom==='tired'||Boolean(log.fatigue),
    recovered:q.readiness==='recovered'&&q.symptom==='none',completed:true};
   const k=data.categoryFeedback3873.findIndex(x=>x.date===date&&x.category===cats[0]);
   if(k>=0)data.categoryFeedback3873[k]=entry;else data.categoryFeedback3873.push(entry);
  }
  return {recorded:true,category:cats.length===1?cats[0]:null,globalOnly:cats.length!==1,why:cats.length===1?'Valoración general guardada para una categoría.':'Valoración global; sesión mixta o sin registros cuantificados.'};
 }
 function recentCategory(data,category,today,lookback=7){
  return (data?.categoryFeedback3873||[]).filter(x=>x.category===category&&x.date<today&&x.date>=R.addDays(today,-lookback)).sort((a,b)=>a.date.localeCompare(b.date));
 }
 // Numeric reference only. Never commit a higher load solely on RPE.
 function volumeReference({rating,unit='REPETITIONS',base,min=1,max=null,completed=false,interrupted=false,recovered=false,pain=false,approved=false}={}){
  const delta=reference(rating);
  if(delta===null)return {status:'sin_datos',delta:null,next:null,why:'Sin valoración válida.'};
  if(unit!=='REPETITIONS')return {status:'unidad_separada',delta:null,next:null,why:'Los segundos requieren un protocolo distinto.'};
  if(!Number.isInteger(base)||base<1||!Number.isInteger(min)||min<1||max!==null&&(!Number.isInteger(max)||max<min))return {status:'sin_limites',delta,next:null,why:'Faltan datos válidos de referencia y límites.'};
  const floor=Math.min(min,base);
  if(delta>0&&max===null)return {status:'sin_maximo',delta,next:null,why:'No existe un máximo certificado para esta variante.'};
  const next=Math.max(floor,Math.min(max??Number.MAX_SAFE_INTEGER,base+delta));
  if(delta>0&&(!completed||interrupted||!recovered||pain))return {status:'mantener',delta,next:base,why:'Una valoración fácil sin recuperación y registros completos no autoriza un aumento.'};
  if(delta>0)return {status:approved?'aceptada_para_revision':'sugerencia',delta,next,why:'Referencia numérica revisable; no cambia automáticamente el plan ni es obligatoria.'};
  return {status:delta<0?'reduccion_orientativa':'mantener',delta,next,why:delta<0?'Referencia reducida dentro de los límites de la variante.':'No corresponde variar el volumen.'};
 }
 function cycleDay(data,date){const start=data?.initialExperienceDate;
  if(!safeDate(start)||!safeDate(date)||R.distance(date,start)<0)return null;
  return R.distance(date,start)+1;
 }
 function milestone(data,category,today){
  if(!R.categories.includes(category)||!safeDate(today))throw Error('Categoría o fecha inválida');
  const d=cycleDay(data,today),checkpoint=d!==null&&[7,14,21].includes(d)?d:null;
  const last=recentCategory(data,category,today,7),low=[...new Set(last.filter(x=>x.completed&&x.value<=3&&x.recovered&&!x.hasPain&&!x.fatigue).map(x=>x.date))];
  const blocked=last.some(x=>x.hasPain||x.fatigue||x.value>=8);
  const eligible=low.length>=2&&!blocked;
  const requested=checkpoint===21?2:checkpoint===7||checkpoint===14?1:0;
  return {day:d,checkpoint,requested,eligible:eligible&&Boolean(checkpoint),lowSessions:low.length,blocked,
   action:!checkpoint?'esperar':blocked?'recuperacion':!eligible?'mantener':'evaluar_propuesta',
   why:!checkpoint?'Las revisiones ocurren en los días 7, 14 y 21.':blocked?'Existe una señal reciente de fatiga, molestias o esfuerzo alto.':
   !eligible?'No hay dos sesiones fáciles y recuperadas en días diferentes dentro de la ventana.':
   `Revisión día ${checkpoint}: hay información para evaluar hasta ${requested} variante(s) superiores, opcionales.`};
 }
 // No inferred biomechanics: explicit progression metadata must identify predecessor.
 function higherCandidates({catalog,profile,data,category,today,currentIds=[]}){
  const m=milestone(data,category,today);
  if(!m.eligible||!catalog||!Array.isArray(currentIds)||!currentIds.length)return {...m,proposals:[],why:m.eligible?'Falta catálogo o variante anterior certificada.':m.why};
  const all=catalog.all();const proposals=[];
  for(const id of currentIds){
   const prior=catalog.byId(id);
   if(!prior||prior.category!==category||prior.level>=3)continue;
   const next=all.filter(v=>v.type===prior.type&&v.level===prior.level+1&&v.level<=3&&
    Array.isArray(v.progressionFromIds)&&v.progressionFromIds.includes(prior.id)&&R.compatible(v,profile).ok)
     .sort((a,b)=>a.id.localeCompare(b.id))[0];
   if(next&&!proposals.some(p=>p.id===next.id)){
    const safeIntro=next.introDose&&Number.isInteger(next.introDose.sets)&&next.introDose.sets>=1&&next.introDose.sets<=2&&
     Number.isInteger(next.introDose.perSet)&&next.introDose.perSet>=1&&next.introDose.unit==='REPETITIONS';
    proposals.push({id:next.id,from:prior.id,level:next.level,category,kind:'propuesta_sin_asignar',
     dose:safeIntro?clean(next.introDose):null,
     why:safeIntro?'Variante superior explícitamente vinculada; dosis de introducción declarada por catálogo y sujeta a aceptación.':
      'Variante superior vinculada, pero sin dosis de introducción validada. No se asignan repeticiones.'});
   }
   if(proposals.length>=m.requested)break;
  }
  return {...m,proposals,why:proposals.length?m.why:'No hay variantes superiores compatibles y expresamente relacionadas. Se mantienen las actuales.'};
 }
 // Generator changeover only with audited metadata AND an explicit, compatible provider.
 function generate(options){const basic=R.generate(options);if(!options?.catalog||!options?.profile||!options?.today||!options?.state)return basic;
  if(basic.category==='descanso')return {...basic,review:null,source:'AsterionUnified3873'};
  const review=milestone(options.state,basic.category,options.today);
  const proposals=basic.items?.length?higherCandidates({catalog:options.catalog,profile:options.profile,data:options.state,
   category:basic.category,today:options.today,currentIds:basic.items.map(v=>v.id)}):{...review,proposals:[]};
  return {...basic,review:proposals,source:'AsterionUnified3873'};
 }
 return Object.freeze({version:'38.73',SCALE,reference,completedCategories,recordSession,recentCategory,volumeReference,cycleDay,milestone,higherCandidates,generate});
});

