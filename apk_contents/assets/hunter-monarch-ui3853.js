/* Hunter System 38.53 — final home + navigation integration.
 * Loaded after legacy home/render replacements and before the startup finalizer.
 * The mission engine remains independent from the daily routine engine.
 */
(function(root, doc){
 'use strict';
 if(root.HunterMonarchUI3853)return;
 if(!root.HunterMonarch3852||typeof home!=='function'||typeof render!=='function'||typeof panelTabs!=='function'){
   throw Error('HunterMonarchUI3853: faltan el motor o el Panel principal');
 }
 const originalHome=home, originalRender=render, originalTabs=panelTabs;
 const esc=s=>String(s??'').replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
 const currentStatus=()=>{
   root.HunterMonarch3852.review();
   const store=state?.monarch3852;
   const active=store?.active;
   const rows=active?root.HunterMonarch3852.progress(active,state.logs||{}):[];
   const finished=rows.filter(x=>x.done>=x.target).length;
   return {active,finished,total:5,xp:Math.max(0,Number(store?.xp)||0)};
 };
 const homeCard=()=>{
   const s=currentStatus();
   const isActive=!!s.active;
   const pct=isActive?Math.max(0,Math.min(100,Math.round(s.finished/5*100))):0;
   const days=isActive?Math.max(0,Math.ceil((s.active.expiresAt-Date.now())/86400000)):5;
   const difficulty=({basic:'BÁSICO',intermediate:'INTERMEDIO',advanced:'AVANZADO'})[state.profile?.difficulty]||'SEGÚN PERFIL';
   return `<section class="monarch-entry3853" aria-labelledby="monarch-entry-title3853">
    <div class="monarch-entry3853__symbol" aria-hidden="true">♛</div>
    <div class="monarch-entry3853__info">
      <div class="monarch-entry3853__eyebrow">DESAFÍO ESPECIAL <span>· CICLO DE 5 DÍAS</span></div>
      <h2 id="monarch-entry-title3853">MISIONES <strong>MONARCA</strong></h2>
      <p>${isActive?'Tu desafío está en marcha. Los cinco objetivos se acumulan durante el ciclo.':'Cinco pruebas de fuerza y resistencia adaptadas a tu nivel. Cada avance cuenta durante cinco días.'}</p>
      <div class="monarch-entry3853__tags"><span>NIVEL ${esc(difficulty)}</span><span>${isActive?days+' DÍAS RESTANTES':'5 OBJETIVOS'}</span><span>${s.xp} XP MONARCA</span></div>
    </div>
    <div class="monarch-entry3853__action">
      ${isActive?`<div class="monarch-entry3853__meter" role="progressbar" aria-label="Objetivos Monarca completados" aria-valuemin="0" aria-valuemax="5" aria-valuenow="${s.finished}"><i style="width:${pct}%"></i></div><small>${s.finished} / 5 completados</small>`:''}
      <button type="button" class="monarch-entry3853__button" onclick="go('monarch')">${isActive?'CONTINUAR MISIÓN':'ABRIR MISIONES'} <span aria-hidden="true">→</span></button>
    </div>
   </section>`;
 };
 // The last home() definition (V3 and later upgrades) is the source of the user's Panel.
 home=function(){
   const markup=originalHome.apply(this,arguments);
   if(!state.profile||typeof markup!=='string'||markup.includes('monarch-entry3853'))return markup;
   return homeCard()+markup;
 };
 panelTabs=function(){
   const markup=originalTabs.apply(this,arguments);
   if(!markup.includes('segmented')||markup.includes('data-monarch-tab3853'))return markup;
   return markup.replace(/<\/div>\s*$/,`<button data-monarch-tab3853="1" class="${tab==='monarch'?'selected':''}" onclick="go('monarch')" aria-label="Ir a Misiones Monarca">♛ Monarca</button></div>`);
 };
 // V2 replaced the base render() and does not know about the 'monarch' route.
 // Reuse its actual header, bottom navigation and layout before replacing the scene.
 render=function(){
   if(tab!=='monarch')return originalRender.apply(this,arguments);
   if(!state.profile){tab='profile';return originalRender.apply(this,arguments);}
   let result;
   tab='home';
   try{result=originalRender.apply(this,arguments);}
   finally{tab='monarch';}
   const scene=doc.querySelector('#app .scene');
   if(!scene){
     throw Error('HunterMonarchUI3853: no se encontró el contenedor de la pantalla');
   }
   scene.dataset.route3855='monarch';scene.innerHTML=`<div class="monarch-page3853"><button type="button" class="monarch-page3853__back" onclick="go('home')">← VOLVER AL PANEL</button>${root.HunterMonarch3852.view()}</div>`;
   const tabs=doc.querySelector('#app .segmented');
   if(tabs){
     tabs.querySelectorAll('button').forEach(btn=>{const selected=btn.hasAttribute('data-monarch-tab3853');btn.classList.toggle('selected',selected);if(selected)btn.setAttribute('aria-current','page');else btn.removeAttribute('aria-current');});
   }
   const navHome=doc.querySelector('#app nav.nav button[onclick="go(\'home\')"]');
   if(navHome)navHome.classList.add('active');
   return result;
 };
 root.HunterMonarchUI3853=Object.freeze({version:'38.54',homeCard,diagnostics:()=>({tab,cardPresent:!!doc.querySelector('.monarch-entry3853'),viewPresent:!!doc.querySelector('.monarch-page3853'),tabPresent:!!doc.querySelector('[data-monarch-tab3853]')})});
})(window,document);
