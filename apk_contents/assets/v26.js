/* V26: diseño de fantasía compartido, con fondos y fuentes locales. */
'use strict';
const V26UI={home,header,render,modal,decorateModal,applyTheme,paintGuide11,openCoach,routineDetail};
const UI26={
 cardSelector:'.card,.metric,.command-hero,.academy-banner,.notification-banner,.adaptation-chip,.learning-card,.coach-intro,.health-compact,.exercise-card,.category-card,.routine-card,.trainer-tile,.gym-banner23',
 pending:0,
 corner:'<svg viewBox="0 0 46 46" fill="none" aria-hidden="true"><path d="M2 43V13L13 2h30l-7 6H16L8 16v20Z" fill="currentColor" fill-opacity=".13" stroke="currentColor" stroke-width="1.2"/><path d="M5 34V15L15 5h19M12 27V17l5-5h10M3 3h7L3 10Z" stroke="currentColor" stroke-width="1"/><path d="m13 13 5 1-4 4Z" fill="currentColor"/><path d="M2 43 8 36M36 8l7-6" stroke="var(--accent)" stroke-width="2"/></svg>',
 frame(windowFrame=false){return '<span class="'+(windowFrame?'window-frame26':'ornament26')+'" aria-hidden="true">'+['tl','tr','bl','br'].map(c=>'<span class="corner26 '+c+'">'+this.corner+'</span>').join('')+'<span class="frame-glint26"></span></span>';},
 decorate(root){
  if(!root)return;
  const hero=$('#app .hero26'),goals=$('#app .goals-frame26');
  if(hero&&goals)goals.style.setProperty('--hero-height26',window.innerWidth>=720?hero.getBoundingClientRect().height+'px':'280px');
  const dialog=$('#modal');
  if(dialog.open){
   // A full-width toolbar prevents the floated controls shrinking grid panels.
   let toolbar=dialog.querySelector(':scope > .window-actions26');
   if(!toolbar){toolbar=document.createElement('div');toolbar.className='window-actions26';dialog.prepend(toolbar);}
   const back=dialog.querySelector('.modal-back24'),close=dialog.querySelector('.modal-close-x');
   if(back&&back.parentElement!==toolbar)toolbar.append(back);
   if(close){if(close.parentElement!==toolbar)toolbar.append(close);if(!close.querySelector('svg'))close.innerHTML='<svg viewBox="0 0 24 24" aria-hidden="true"><path d="m6 6 12 12M18 6 6 18" fill="none" stroke="currentColor" stroke-width="1.8" stroke-linecap="round"/></svg>';}
  }
  for(const node of root.querySelectorAll(this.cardSelector)){
   if(node.closest('.motion-svg18,.companion-picker,[data-companion-static="true"] .companion-option'))continue;
   node.classList.add('system-card26');
   let ornament=node.querySelector(':scope > .ornament26,:scope > summary > .ornament26');
   if(!ornament){node.insertAdjacentHTML('beforeend',this.frame());ornament=node.querySelector(':scope > .ornament26');}
   // Content outside summary is hidden by a closed details element. Keep its
   // decorative frame in summary, positioned against the whole details card.
   if(node.tagName==='DETAILS'){const summary=node.querySelector(':scope > summary');if(summary&&ornament.parentElement!==summary)summary.append(ornament);}
  }
  if(dialog.open){
   dialog.classList.add('system-window26');
   if(!dialog.querySelector(':scope > .window-frame26'))dialog.insertAdjacentHTML('beforeend',this.frame(true));
   const height=dialog.getBoundingClientRect().height;
   if(height>0)dialog.style.setProperty('--window-height26',height+'px');
  }
 },
 schedule(){if(this.pending)return;this.pending=requestAnimationFrame(()=>{this.pending=0;if($('#modal').open)ModalViewport.fit();this.decorate(document);});}
};
applyTheme=function(p=state.profile||{}){if(Runtime20.initializing)return;
 V26UI.applyTheme(p);
 const root=document.documentElement,h=ThemeHues[root.dataset.theme]??ThemeHues.blue;
 const values={'--bg':`hsl(${h} 42% 7%)`,'--panel':`hsl(${h} 42% 10%)`,'--surface':`hsl(${h} 40% 14%)`,'--input':`hsl(${h} 38% 8%)`,'--line':`hsl(${h} 29% 33%)`,'--text':`hsl(${h} 44% 94%)`,'--muted':`hsl(${h} 24% 75%)`,'--ornament26':`hsl(${(h+42)%360} 32% 62%)`,'--metal26':`hsl(${(h+25)%360} 28% 30%)`,'--theme-hue26':h};
 Object.entries(values).forEach(([k,v])=>root.style.setProperty(k,v));
 document.querySelector('meta[name=theme-color]').content=values['--bg'];themeNative20();
};
header=function(){
 const box=document.createElement('div');box.innerHTML=V26UI.header();
 const brand=box.querySelector('.brand>div:last-child');
 if(brand)brand.innerHTML='<span class="brand-title26">HUNTER SYSTEM</span><small>SISTEMA DE SOMBRAS</small>';
 const logo=box.querySelector('.logo');if(logo)logo.innerHTML='<svg viewBox="0 0 40 40" fill="none" aria-hidden="true"><path d="m20 2 16 18-16 18L4 20Z" stroke="currentColor"/><path d="m20 9 10 11-10 11L10 20Z" fill="currentColor" fill-opacity=".12" stroke="var(--accent)" stroke-width="2"/><path d="m20 15 4 5-4 5-4-5Z" fill="var(--accent)"/></svg>';
 if(state.profile){
  const stats=E.stats(state.logs),rank=['E','D','C','B','A','S'][Math.min(5,Math.floor((stats.level-1)/5))],actions=box.querySelector('.topbar>.row');
  actions?.insertAdjacentHTML('afterbegin','<span class="header-rank26" aria-label="Rango '+rank+'"><b>'+rank+'</b><span>RANGO '+rank+'</span></span>');
 }
 return box.innerHTML;
};
home=function(){
 const box=document.createElement('div');box.innerHTML=V26UI.home();
 const hero=box.querySelector('.command-hero');
 if(hero){
  hero.classList.add('hero26');hero.querySelector('.hero-lines')?.remove();
  const runePaths=['M5 21V3l10 7-10 5M10 12l7 9','M4 20V4l10 6M4 12l10 6','M6 3v18M16 3 6 11l10 10','M5 3l11 9L5 21V3','M3 7l7-5 7 5-7 5Zm7 5v11','M4 3l12 18M16 3 4 21M10 4v16','M5 4v16l10-8Z','M3 13 10 3l7 10-7 10Zm7-10v20','M4 20V4l6 6 6-6v16','M4 3v18l12-8-12-4'];
  hero.insertAdjacentHTML('afterbegin','<div class="hero-art26" aria-hidden="true"></div><div class="hero-runes26" aria-hidden="true">'+runePaths.map(path=>'<svg viewBox="0 0 20 24" fill="none"><path d="'+path+'" stroke="currentColor" stroke-width="1.2" stroke-linecap="round" stroke-linejoin="round"/></svg>').join('')+'</div><svg class="hero-flash26" viewBox="0 0 600 400" preserveAspectRatio="none" aria-hidden="true"><path d="M6 345 10 321 6 305 13 297 7 281 11 272 6 245M594 52l-6 28 8 14-9 20 7 18-6 14 6 25M50 392h130l13-4 14 8 18-5h60" fill="none" stroke="currentColor" stroke-width="1.5"/></svg>');
  hero.querySelector('h1').innerHTML='SUPERA<br><span>TUS LÍMITES.</span>';
 }
 const dashboard=box.querySelector('.dashboard-grid'),left=dashboard?.querySelector(':scope>.stack'),aside=dashboard?.querySelector(':scope>aside');
 if(left&&aside){
  dashboard.classList.add('dashboard26');
  const goals=[...aside.children].find(n=>n.querySelector('h2,h3')?.textContent==='Tus objetivos'),cycle=aside.querySelector('.cycle-card');
  if(goals)goals.classList.add('goals-frame26');
  if(cycle){cycle.classList.add('cycle-frame26');cycle.insertAdjacentHTML('beforeend','<div class="cycle-sigil26" aria-hidden="true" data-companion-static="true">'+IgrisArt.svg(selectedCompanion())+'</div>');}
  // Auxiliary content shares the columns rather than appearing as full-width strips.
  for(const node of [...box.children]){
   if(node===dashboard||node.classList.contains('adaptation-chip'))continue;
   if(node.classList.contains('coach-banner')||node.classList.contains('academy-banner'))left.append(node);
   else aside.append(node);
  }
 }
 return box.innerHTML;
};
decorateModal=function(type){V26UI.decorateModal(type);UI26.decorate($('#modal'));UI26.schedule();};
modal=function(html){V26UI.modal(html);UI26.decorate($('#modal'));UI26.schedule();};
paintGuide11=function(){V26UI.paintGuide11();UI26.decorate($('#modal'));UI26.schedule();};
openCoach=function(){V26UI.openCoach();UI26.decorate($('#modal'));UI26.schedule();};
routineDetail=function(id){V26UI.routineDetail(id);UI26.decorate($('#modal'));UI26.schedule();};
render=function(){if(Runtime20.initializing)return;
 V26UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='SISTEMA DE SOMBRAS');
 const dock=$('.nav'),companion=$('#companion');
 if(dock&&companion){companion.classList.add('companion-dock26');dock.classList.add('nav-dock26');dock.append(companion);}
 UI26.decorate(document);UI26.schedule();
};
new MutationObserver(()=>UI26.schedule()).observe($('#modal'),{childList:true});
window.addEventListener('resize',()=>UI26.schedule(),{passive:true});
window.visualViewport?.addEventListener('resize',()=>UI26.schedule(),{passive:true});
const revealBefore26=reveal20;
reveal20=function(){
 if(!document.fonts?.load)return revealBefore26();
 Promise.race([Promise.all([document.fonts.load('400 24px "Hunter Display"'),document.fonts.load('400 18px "Hunter Text"'),document.fonts.load('600 18px "Hunter Text"')]),new Promise(resolve=>setTimeout(resolve,1500))]).catch(()=>{}).then(()=>{UI26.decorate(document);revealBefore26();});
};
window.HunterV26={UI26};state.appVersion=26;applyTheme();save();render();
