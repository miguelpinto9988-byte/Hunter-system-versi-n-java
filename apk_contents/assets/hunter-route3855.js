/* Hunter System V38.55: separate calendar route, shared stable shell diagnostics.
   The avatar in the upper right is the only permanent Profile navigation entry.
   Never caches forms or copies training logs across screens. */
(function(root,doc){'use strict';
 if(root.HunterRoute3855)return;
 const escText=value=>String(value??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 function calendarView(){
   const cycle=currentCycle();
   if(!cycle)return blocked();
   const today=E.dateKey();
   const upcoming=(cycle.days||[]).filter(d=>d.date>=today).slice(0,7);
   return `<section class="hunter-calendar3855" aria-labelledby="hunter-calendar-title3855" data-screen3855="calendar">
     <span class="eyebrow">PLANIFICACIÓN INDEPENDIENTE</span>
     <h1 id="hunter-calendar-title3855">Calendario</h1>
     <p class="muted">Revisa tus próximos entrenamientos sin salir de esta pantalla. Tus sesiones registradas se conservan.</p>
     <div class="card hunter-calendar3855__main">
       <div class="row between"><h2>Ciclo ${cycle.index+1}</h2><span class="badge">${escText(cycle.start)} · 7 días visibles</span></div>
       ${calendar(cycle)}
     </div>
     <div class="section-title"><h2>Próximas sesiones</h2></div>
     <div class="card">${upcoming.length?upcoming.map(d=>`<div class="quest"><div class="glyph">${escText(d.date.slice(8))}</div><div class="grow"><h3>${escText(kindLabel(d.kind))}</h3><small>${escText(d.date)}</small></div><button class="btn ghost" type="button" aria-label="Ver sesión del ${escText(d.date)}" onclick="dayDetail('${d.date}')">Ver →</button></div>`).join(''):'<p class="muted">No quedan sesiones pendientes en este ciclo.</p>'}</div>
   </section>`;
 }
 const entry={version:'38.56',calendarView,diagnostics:()=>({tab,route:doc.querySelector('#app main .scene')?.getAttribute('data-route3855')||null,shellExists:!!doc.querySelector('#app > main.app'),navItems:[...doc.querySelectorAll('#app > nav.nav button[data-route3855]')].map(x=>x.dataset.route3855),profileAvatar:!!doc.querySelector('.topbar .avatar-button'),fullMounts:typeof shell3855==='object'?shell3855.fullMounts:0,sceneReplacements:typeof shell3855==='object'?shell3855.sceneReplacements:0,headerReplacements:typeof shell3855==='object'?shell3855.headerReplacements:0,navReplacements:typeof shell3855==='object'?shell3855.navReplacements:0})};
 root.HunterRoute3855=Object.freeze(entry);
})(window,document);
