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
