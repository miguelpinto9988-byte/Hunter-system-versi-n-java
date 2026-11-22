/* Weekly central-body integration 38.47. */
/* HUNTER SYSTEM V38.45 — independent on-demand offline/gym routine generator.
   No changes to profile, daily missions, other routine catalogues or persistent
   state. Filtering strictly precedes item selection; each routine is one type.
*/
(function (root) {
  'use strict';
  if (root.HunterLibrary3845 || typeof routines !== 'function' ||
      typeof allRoutines !== 'function' || typeof ownLibraryView14 !== 'function' ||
      typeof gymView23 !== 'function' || !root.HunterDynamic381) return;
  const D = root.HunterDynamic381;
  const E = root.Engine;
  const G = root.HunterGroups49;
  const O = root.OwnRoutines18;
  const cats = [
    {id:'upper',name:'Tren superior',className:'upper',kind:'strength',group:['Pecho','Espalda','Hombros','Brazos'],symbol:'▲'},
    {id:'lower',name:'Tren inferior',className:'lower',kind:'strength',group:['Piernas','Cadera'],symbol:'▼'},
    {id:'core',name:'Tren central',className:'core',kind:'strength',group:['Core','Abdomen'],symbol:'◆'},
    {id:'cardio',name:'Cardio',className:'cardio',kind:'cardio',group:null,symbol:'♥'},
    {id:'mobility',name:'Movilidad',className:'mobility',kind:'recovery',group:null,symbol:'✧'}
  ];
  const windows=[{id:'short',label:'5–10 min',low:5,high:10},
    {id:'medium',label:'10–20 min',low:10,high:20},
    {id:'long',label:'Hasta 30 min',low:5,high:30}];
  const selected = new Set();
  let duration = null;
  let serial=0;
  const data={offline:null,gym:null};
  const info={offline:'',gym:''};
  const labels=['Básico','Intermedio','Avanzado'];
  const keys=['basic','intermediate','advanced'];
  const originalAll = allRoutines;
  const originalRoutines=routines;
  const originalOffline=ownLibraryView14;
  const originalGym=gymView23;
  const clone=o=>JSON.parse(JSON.stringify(o));
  function scope() {
    if (typeof tab==='undefined'||tab!=='routines') return null;
    if (typeof gymBrowse23!=='undefined'&&gymBrowse23) return 'gym';
    if (typeof ownLibrary11!=='undefined'&&ownLibrary11) return 'offline';
    return null;
  }
  function numberHash(x) {let h=2166136261;for(let i=0;i<x.length;i++){h=Math.imul(h^x.charCodeAt(i),16777619);}return h>>>0;}
  function timeSet(){return windows.find(w=>w.id===duration)||null;}
  function pool(profile,category,mode){
    const list=D.pool(profile,category.kind);
    return list.filter(e=>e && (mode==='gym'?(!!e.gymOnly23||G.power(e)):!e.gymOnly23) &&
      G.matches(e,category.id) &&
      (mode!=='gym'||(e.gymRequirements23||[]).every(k=>(profile.gymEquipment23||[]).includes(k))));
  }
  function selectExercises(entries, seed, count, ctx) {
    // Preserve distinct movement families to avoid repeated variants of one exercise.
    const scored=entries.map(e=>({e,rank:numberHash(seed+'|'+e.id)})).sort((a,b)=>a.rank-b.rank);
    const picked=[],used=new Set(),groups=new Map();
    while(picked.length<count){
      const possible=scored.filter(x=>!used.has(G.family(x.e)));
      const allowed=new Set(G.selectionPool(possible.map(x=>x.e),picked,count,ctx));
      const available=possible.filter(x=>allowed.has(x.e));
      if(!available.length)break;
      available.sort((a,b)=>G.score(b.e,ctx.p,ctx.kind,picked)-G.score(a.e,ctx.p,ctx.kind,picked)+(groups.get(a.e.group)||0)*14-(groups.get(b.e.group)||0)*14||a.rank-b.rank);
      const e=available[0].e;picked.push(e);used.add(G.family(e));
      groups.set(e.group,(groups.get(e.group)||0)+1);
    }
    return picked;
  }
  function generatePlan(mode,category,level,variant,range,epoch,p){
    const adjusted={...p,difficulty:keys[level],minutes:range.high};
    const candidates=pool(adjusted,category,mode);
    if(!candidates.length)return null;
    const seed=[epoch,mode,category.id,level,variant].join('|');
    const logs=typeof state==='undefined'?{}:state.logs||{},date=E.dateKey(),status=D.gate(adjusted),feedback=D.feedback(logs,date);
    const ctx=G.context(adjusted,category.kind,category.id,{logs,date,status,feedback});
    const high=range.id==='long'?30:range.high;
    const low=range.low;
    const maximum=Math.min(candidates.length,range.id==='short'?5:7);
    let winner=null;
    for(let count=2;count<=maximum;count++){
      const exercises=selectExercises(candidates,seed,count,ctx);
      if(exercises.length<count)break;
      if(mode==='gym'&&!exercises.some(e=>e.gymOnly23))continue;
      for(let sets=1;sets<=3;sets++){
        const items=exercises.map(e=>{
          // Existing dose engine sets the ceiling; short sessions never increase
          // repetitions or sets, and all existing injury filters are preserved.
          const base=O.dose(e,adjusted,category.kind);
          const item={id:e.id,sets:Math.max(1,Math.min(sets,base.sets||1)),
            target:Math.max(1,Math.min(base.target||8,e.unit==='sec'?50:15)),
            rest:Math.max(30,Number(base.rest)||60),libraryGen3845:true};
          const roleDose=G.adjustDose(e,adjusted,category.kind,item,ctx);
          return root.HunterCatalog46.cap(e,adjusted,roleDose,ctx);
        });
        const minutes=Warm25.minutes({items},adjusted);
        if(minutes<low||minutes>high)continue;
        const aim=range.id==='short'?8:range.id==='medium'?15:25;
        const score=Math.abs(aim-minutes)*10+Math.abs(count-(range.id==='short'?3:5)) * 2;
        if(!winner||score<winner.score)winner={items,minutes,score};
      }
    }
    if(!winner)return null; // Never mislabel duration if it cannot be met.
    const requirements=[...new Set(winner.items.flatMap(x=>E.byId(x.id)?.requirements||[]))];
    const gymRequirements23=[...new Set(winner.items.flatMap(x=>E.byId(x.id)?.gymRequirements23||[]))];
    const names=mode==='gym'?'Gimnasio':'Sin conexión';
    return {id:`hlib3845-${mode}-${epoch}-${category.id}-${level}-${variant}`,
      name:`${category.name} · ${labels[level]} · ${names} ${variant+1}`,
      category:category.kind==='strength'?'Fuerza':category.kind==='cardio'?'Cardio':'Movilidad',
      focus3845:category.id,mode3845:mode,level,items:winner.items,
      requirements,gymOnly23:mode==='gym',gymRequirements23,
      minutes:winner.minutes,impact:winner.items.some(x=>G.impact(E.byId(x.id)))?'Con saltos':'Sin saltos',
      family:'dynamic',extension:'standard',goalGroups:['fitness','habit','movement','build','athletic','adapted'],
      safe381:true,description:'Secuencia generada desde las demostraciones 2D locales, filtrada por categoría, equipo y restricciones del perfil. Estimación de tiempo con descansos. No altera las misiones del día.'};
  }
  function plans(mode, chosen, w, epoch){
    const profile=state?.profile;
    if(!profile||!D.gate(profile).ok)return {routines:[],fail:'Revisa primero los datos o restricciones del perfil.'};
    if(mode==='gym'&&profile.equipment!=='gym')return {routines:[],fail:'Para generar rutinas de gimnasio, configura primero el equipo de gimnasio en Perfil.'};
    const result=[],missing=[];
    for(let level=0;level<3;level++)for(const type of chosen){
      const cat=cats.find(c=>c.id===type);if(!cat)continue;
      let produced=0;
      for(let v=0;v<2;v++){
        const r=generatePlan(mode,cat,level,v,w,epoch,profile);
        if(r){result.push(r);produced++;}
      }
      if(!produced)missing.push(cat.name+' ('+labels[level]+')');
    }
    return {routines:result,missing};
  }
  const escText=s=>String(s).replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
  function badge(){
    const w=timeSet(),mode=scope(),generated=mode?data[mode]:null;
    const status=mode&&info[mode]?`<p class="library-status3845" role="status">${escText(info[mode])}</p>`:'';
    return `<section class="library-generator3845 card" aria-label="Generador dinámico de rutinas">
      <div class="library-header3845"><div><span class="eyebrow">HUNTER SYSTEM · GENERADOR DINÁMICO</span><h2>Diseña tu próxima rutina</h2></div><span class="library-counter3845">1200 demos 2D</span></div>
      <p class="library-hint3845">Elige los tipos de ejercicio y el tiempo. Los entrenamientos se generan por separado para cada categoría y para los tres niveles existentes.</p>
      <div class="library-label3845">01 · GRUPOS DE ENTRENAMIENTO <small>Selección múltiple</small></div>
      <div class="library-chips3845" role="group" aria-label="Seleccionar categorías">
        ${cats.map(c=>`<button type="button" aria-label="${c.name}" aria-pressed="${selected.has(c.id)}" class="library-chip3845 ${c.className} ${selected.has(c.id)?'on':''}" onclick="HunterLibrary3845.toggle('${c.id}',this)"><span class="library-orb3845" aria-hidden="true">${c.symbol}</span><span>${c.name}</span></button>`).join('')}
      </div>
      <div class="library-label3845">02 · DURACIÓN DE LA SESIÓN <small>Elige una</small></div>
      <div class="library-times3845" role="group" aria-label="Duración estimada">
      ${windows.map((o,i)=>`<button type="button" class="library-time3845 ${duration===o.id?'on':''}" aria-pressed="${duration===o.id}" onclick="HunterLibrary3845.time('${o.id}',this)"><span aria-hidden="true">◷</span><b>${o.label}</b>${i===2?'<small>20–30 min preferidos</small>':''}</button>`).join('')}</div>
      <button type="button" class="btn primary full library-generate3845" onclick="HunterLibrary3845.generate()">✧ Generar nuevas rutinas</button>
      ${generated?`<div class="library-generated3845"><b>✓ ${generated.length} rutinas nuevas</b><span>Se conservan únicamente mientras la aplicación está abierta. Los niveles se encuentran debajo.</span><button type="button" class="text-button" onclick="HunterLibrary3845.reset()">Ver biblioteca original</button></div>`:''}
      ${status}
    </section>`;
  }
  function tile(r){
    const names=r.items.slice(0,4).map(x=>E.byId(x.id)?.name||x.id);
    const compatible=Adapt.routineCompatible(r,GoalPlans.adjusted(state.profile));
    const active=compatible?'Lista para tu perfil':'Revisa compatibilidad y equipo en Perfil';
    return `<article class="card routine-card18 library-generated-card3845"><div class="row between"><span class="eyebrow">${escText(cats.find(c=>c.id===r.focus3845)?.name||r.category)} · ${escText(r.mode3845==='gym'?'GIMNASIO':'SIN CONEXIÓN')}</span><span class="badge">≈ ${r.minutes} min</span></div><h3>${escText(r.name)}</h3><ul class="routine-preview18">${names.map(name=>`<li><span>${escText(name)}</span></li>`).join('')}</ul><p class="footnote">${r.items.length} ejercicios · ${r.items.reduce((s,x)=>s+x.sets,0)} series · ${escText(active)}</p><button type="button" class="btn primary full" onclick="routineDetail('${r.id}')">Abrir secuencia 2D</button></article>`;
  }
  function fill(html,mode){
    if(!data[mode])return html;
    const box=document.createElement('div');box.innerHTML=html;
    const node=box.querySelector(mode==='gym'?'.gym-routines23':'.routine-results-14');
    if(!node)return html;
    const level=mode==='gym'?(typeof gymLevel23==='number'?gymLevel23:(root.TrainingProgression19?.level?.(state.profile||{})||0)):
       (typeof ownLevel14==='number'?ownLevel14:Math.max(0,keys.indexOf(state.profile?.difficulty)));
    const list=data[mode].filter(r=>r.level===level);
    node.innerHTML=list.map(tile).join('') || '<div class="card empty"><h3>No hay ejercicios compatibles con estos filtros</h3><p>Prueba otra categoría o duración, o revisa las restricciones y el equipo registrado en Perfil. No se sustituye una categoría por otra.</p></div>';
    const oldFilters=box.querySelector(mode==='gym'?'select[onchange*="gymFamily23"]':'.routine-filters-14');
    (mode==='gym'?oldFilters?.closest('label'):oldFilters)?.remove();
    const subtitle=box.querySelector('.page-heading p.muted');if(subtitle)subtitle.textContent='Rutinas nuevas generadas localmente; las combinaciones no se guardan. Selecciona abajo Básico, Intermedio o Avanzado.';
    box.querySelectorAll('.routine-level-tabs button').forEach((button,i)=>{
      const small=button.querySelector('small');if(small)small.textContent=data[mode].filter(r=>r.level===i).length+' nuevas';
    });
    return box.innerHTML;
  }
  allRoutines=function(){const base=originalAll.apply(this,arguments);const active=scope();return active&&data[active]?[...base,...data[active]]:base;};
  ownLibraryView14=function(){return fill(originalOffline.apply(this,arguments),'offline');};
  gymView23=function(){return fill(originalGym.apply(this,arguments),'gym');};
  routines=function(){const view=originalRoutines.apply(this,arguments),active=scope();if(!active)return view;
    const panel=badge();
    const matcher=/^<div class="notice" role="note"><b>Generador dinámico V38\.1<\/b>[\s\S]*?<\/div>/;
    return matcher.test(view)?view.replace(matcher,panel):panel+view;
  };
  function toggle(id,el){if(!cats.some(c=>c.id===id))return;
    if(selected.has(id))selected.delete(id);else selected.add(id);
    el?.setAttribute('aria-pressed',String(selected.has(id)));
    el?.classList.toggle('on',selected.has(id));
  }
  function chooseTime(id,el){if(!windows.some(w=>w.id===id))return;
    duration=id;
    const buttons=el?.closest('.library-times3845')?.querySelectorAll('button');
    buttons?.forEach(b=>{const on=b===el;b.classList.toggle('on',on);b.setAttribute('aria-pressed',String(on));});
  }
  function generate(){const mode=scope();if(!mode)return;
    if(!selected.size){info[mode]='Elige al menos un grupo de entrenamiento.';return render();}
    const w=timeSet();if(!w){info[mode]='Selecciona uno de los tres intervalos de duración.';return render();}
    const generated=plans(mode,[...selected],w,++serial);
    if(generated.fail){info[mode]=generated.fail;return render();}
    if(!generated.routines.length){info[mode]='Sin rutinas compatibles con esta selección. Revisa el equipo, las restricciones o el tiempo.';return render();}
    data[mode]=generated.routines;
    info[mode]=generated.missing?.length?'Algunas combinaciones no tienen ejercicios compatibles: '+generated.missing.join(', ')+'. No se han sustituido por otros grupos.':'Rutinas listas. Selecciona el nivel debajo para verlas.';
    render();
  }
  function reset(){const mode=scope();if(!mode)return;data[mode]=null;info[mode]='';render();}
  root.HunterLibrary3845=Object.freeze({version:'38.49',cats:cats.map(c=>c.id),windows:windows.map(w=>w.id),
    toggle,time:chooseTime,generate,reset,
    status:()=>({scope:scope(),types:[...selected],duration,offline:data.offline?.length||0,gym:data.gym?.length||0}),
    // Exposed for offline deterministic regression tests; no profile state mutation.
    classify:e=>G.classify(e)?.group||null,
    generatePlan,plans});
})(window);
