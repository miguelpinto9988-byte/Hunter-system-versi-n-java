/* Hunter System 38.2: presentación sencilla, sin alterar el motor ni el historial. */
(function(){'use strict';
 const relocate=()=>{
   const modal=document.querySelector('#modal');if(!modal?.open)return;
   const timed=modal.querySelector('#guidedTiming375');
   const canvas=modal.querySelector('#guidedCanvas');
   const host=canvas?.closest('.guided-demo')||modal.querySelector('.guided-step .guided-demo');
   if(timed&&host&&timed.parentNode!==host)host.appendChild(timed);
   if(host&&timed)host.classList.add('clean-demo382');
   const vector=modal.querySelector('#vectorTiming375');
   const card=modal.querySelector('.motion-card18');
   if(card&&vector&&vector.parentNode!==card)card.insertBefore(vector,card.firstChild?.nextSibling||null);
 };
 // The guided screen re-renders for each phase; apply to the new DOM, not a global layout.
 if(typeof paintGuide11==='function'){
   const prior=paintGuide11;
   paintGuide11=function(){const result=prior.apply(this,arguments);relocate();return result;};
 }
 if(typeof UI26!=='undefined'&&typeof UI26.decorate==='function'){
   const prior=UI26.decorate;
   UI26.decorate=function(){const result=prior.apply(this,arguments);relocate();return result;};
 }
 if(typeof modal==='function'){
   const prior=modal;
   modal=function(){const result=prior.apply(this,arguments);relocate();return result;};
 }
 window.HunterClean382={relocate};
})();