/* V12: ventana fija al área visible e independiente del scroll del fondo. */
(function(){
 'use strict';
 const dialog=document.getElementById('modal'),body=document.body,root=document.documentElement;
 if(!dialog)return;
 const properties=['position','top','left','width','overflow','padding-right'];
 let saved=null,generation=0,pendingFrame=0,navigating=false,lastGeometry='';
 const finite=(n,fallback)=>Number.isFinite(n)?n:fallback;
 function viewport(){const v=window.visualViewport;return {top:Math.max(0,finite(v?.offsetTop,0)),left:Math.max(0,finite(v?.offsetLeft,0)),width:Math.max(1,finite(v?.width,window.innerWidth||root.clientWidth||360)),height:Math.max(1,finite(v?.height,window.innerHeight||root.clientHeight||640))};}
 function fit(force=false){
  if(!dialog.open&&!force)return;
  const v=viewport(),css=window.getComputedStyle(root),safe=k=>Math.max(0,parseFloat(css.getPropertyValue('--modal-safe-'+k))||0);
  const topInset=safe('top'),bottomInset=safe('bottom'),leftInset=safe('left'),rightInset=safe('right');
  const availableWidth=Math.max(1,v.width-leftInset-rightInset),availableHeight=Math.max(1,v.height-topInset-bottomInset);
  const landscape=window.innerWidth>window.innerHeight&&(window.innerWidth>=700||window.innerWidth>=600&&dialog.classList.contains('session-landscape35'));
  // V38.31: all main modal windows respond to tablet-sized *layout* viewport.
  // CSS pixels and orientation are used, never the marketed physical diagonal.
  // Use layout height for tablet identification so the keyboard opening does not
  // suddenly change a tablet modal into a phone modal. v.height stays the REAL
  // visual viewport height and therefore continues to fit above the keyboard.
  const layoutW=Math.max(1,window.innerWidth||root.clientWidth||v.width);
  const layoutH=Math.max(1,window.innerHeight||root.clientHeight||v.height);
  const tabletLandscape=layoutW>=820&&layoutH>=520&&layoutW>layoutH;
  const tabletPortrait=layoutW>=600&&layoutH>=740&&layoutH>=layoutW;
  const tabletAll=tabletLandscape||tabletPortrait;
  const tabletGuided=!!dialog.querySelector('.guided-session')&&tabletAll;
  dialog.classList.toggle('hunter-tablet-all3831',tabletAll);
  dialog.classList.toggle('hunter-tablet-guide3830',tabletGuided);
  const gap=tabletAll?Math.min(10,availableWidth/10,availableHeight/10):Math.min(12,availableWidth/8,availableHeight/8);
  const maxWindowWidth=tabletAll?availableWidth:(landscape?1050:availableWidth>=720?860:580);
  const width=Math.min(maxWindowWidth,Math.max(1,availableWidth-gap*2));
  const set=(key,value)=>{if(dialog.style.getPropertyValue(key)!==value)dialog.style.setProperty(key,value);};
  set('--modal-top',(v.top+topInset+gap)+'px');
  set('--modal-left',(v.left+leftInset+(availableWidth-width)/2)+'px');
  set('--modal-width',width+'px');
  set('--modal-max-height',Math.max(1,availableHeight-gap*2)+'px');
  if(dialog.open)dialog.scrollTop=Math.max(0,Math.min(dialog.scrollTop,Math.max(0,dialog.scrollHeight-dialog.clientHeight)));
  // Measure after applying the viewport limits, including newly expanded text.
  const rect=dialog.getBoundingClientRect();
  if(dialog.open&&rect.height>0){
   set('--window-height26',rect.height+'px');
   const geometry=[rect.left,rect.top,rect.width,rect.height].join(':');
   if(geometry!==lastGeometry){lastGeometry=geometry;dialog.dispatchEvent(new CustomEvent('hunter:modal-fit'));}
  }
 }
 function lock(){
  if(!saved){
   saved={x:window.scrollX||0,y:window.scrollY||0,focus:document.activeElement,styles:properties.map(name=>[name,body.style.getPropertyValue(name),body.style.getPropertyPriority(name)]),behavior:root.style.getPropertyValue('scroll-behavior'),priority:root.style.getPropertyPriority('scroll-behavior')};
   const gutter=Math.max(0,(window.innerWidth||root.clientWidth)-root.clientWidth);
   if(gutter)body.style.setProperty('padding-right',(parseFloat(window.getComputedStyle(body).paddingRight)||0)+gutter+'px');
   body.style.setProperty('position','fixed');body.style.setProperty('top',-saved.y+'px');body.style.setProperty('left',-saved.x+'px');body.style.setProperty('width','100%');body.style.setProperty('overflow','hidden');
  }
  root.classList.add('modal-open');body.classList.add('modal-open');fit(true);
 }
 function begin(){generation++;lock();return generation;}
 function reset(){const token=generation;dialog.scrollTop=0;fit(true);requestAnimationFrame(()=>{if(dialog.open&&token===generation){dialog.scrollTop=0;fit();}});}
 function release(restore=true){
  if(dialog.open)return;
  generation++;cancelAnimationFrame(pendingFrame);pendingFrame=0;
  const snapshot=saved;saved=null;root.classList.remove('modal-open');body.classList.remove('modal-open');
  lastGeometry='';dialog.classList.remove('hunter-tablet-guide3830','hunter-tablet-all3831');
  for(const name of ['--modal-top','--modal-left','--modal-width','--modal-max-height','--window-height26'])dialog.style.removeProperty(name);
  if(!snapshot)return;
  for(const [name,value,priority] of snapshot.styles){if(value)body.style.setProperty(name,value,priority);else body.style.removeProperty(name);}
  root.style.setProperty('scroll-behavior','auto','important');
  if(restore){if(snapshot.focus?.isConnected&&snapshot.focus!==body&&snapshot.focus!==dialog)snapshot.focus.focus?.({preventScroll:true});window.scrollTo(snapshot.x,snapshot.y);}
  if(snapshot.behavior)root.style.setProperty('scroll-behavior',snapshot.behavior,snapshot.priority);else root.style.removeProperty('scroll-behavior');
 }
 function schedule(){if(!dialog.open||pendingFrame)return;pendingFrame=requestAnimationFrame(()=>{pendingFrame=0;fit();});}
 const nativeClose=dialog.close.bind(dialog);dialog.close=function(value){nativeClose(value);release(!navigating);};
 dialog.addEventListener('close',()=>release(!navigating));window.addEventListener('resize',schedule,{passive:true});window.addEventListener('orientationchange',schedule,{passive:true});
 window.visualViewport?.addEventListener('resize',schedule,{passive:true});window.visualViewport?.addEventListener('scroll',schedule,{passive:true});dialog.addEventListener('load',schedule,true);
 // A details toggle does not bubble. Observe it in capture and coalesce layout work.
 document.addEventListener('toggle',event=>{if(event.target.tagName==='DETAILS'&&dialog.contains(event.target))schedule();},true);
 const sizeObserver=typeof ResizeObserver==='function'?new ResizeObserver(schedule):null;
 sizeObserver?.observe(dialog);
 document.fonts?.ready.then(schedule).catch(()=>{});
 function beforeNavigation(){navigating=true;try{if(dialog.open)dialog.close();else release(false);}finally{navigating=false;}}
 window.ModalViewport={viewport,fit,lock,begin,reset,release,beforeNavigation,get generation(){return generation;}};
})();
