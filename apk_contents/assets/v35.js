/* V35: distribución horizontal aprobada, con los nodos y acciones de la sesión. */
'use strict';
const V35UI={paintGuide11,modal,backModal24,openDiagnostics20};
const SessionLandscape35={
 current:null,pending:0,
 enabled(){return matchMedia('(orientation: landscape) and (min-width: 600px)').matches;},
 move(entry,node,parent){if(!node)return;const marker=document.createComment('posición de sesión');node.before(marker);entry.moves.push({node,marker});parent.append(node);},
 restore(){
  const entry=this.current;if(!entry)return;this.current=null;
  for(const {node,marker}of entry.moves)if(marker.parentNode)marker.replaceWith(node);
  entry.work?.classList.remove('session-body35');entry.created.forEach(n=>n.remove());
 },
 heading(session,step){
  const header=session.querySelector('.guided-header'),title=header?.querySelector('h2');if(!title)return;
  let landscape=title.querySelector('.session-title-landscape35');
  if(!landscape){const original=document.createElement('span');original.className='session-title-original35';while(title.firstChild)original.append(title.firstChild);landscape=document.createElement('span');landscape.className='session-title-landscape35';landscape.textContent='Sesión paso a paso';title.append(original,landscape);}
  let meta=header.querySelector('.session-meta35');if(!meta){meta=document.createElement('small');meta.className='session-meta35';header.append(meta);}
  const block=session.querySelector('.block-heading22');
  meta.textContent=block?[...block.children].map(n=>n.textContent.trim()).join(' · '):step?.cardio24?(step.kind==='preparation'?'Preparación · Inicio':'Cardio suave · Final'):step?.kind==='rest'?'Recuperación':step?.kind==='exercise'?'Serie '+(step.setIndex+1)+' de '+step.sets:'';
 },
 sync(){
  const dialog=$('#modal'),session=dialog.querySelector('.guided-session'),article=session?.querySelector('.guided-step'),work=article?.querySelector('.session-work31'),active=!!(dialog.open&&session&&article&&work&&this.enabled());
  if(this.current&&(!active||this.current.article!==article))this.restore();
  dialog.classList.toggle('session-landscape35',active);session?.classList.toggle('wide-session35',active);
  if(!active){if(dialog.open)ModalViewport.fit();return;}
  this.heading(session,guide11?.steps[guide11.index]);
  if(!this.current){
   const entry={article,work,moves:[],created:[]};this.current=entry;
   const create=(tag,cls,parent)=>{const n=document.createElement(tag);n.className=cls;parent.append(n);entry.created.push(n);return n;};
   const visual=create('div','session-visual35',work),controls=create('div','session-controls35',work);work.classList.add('session-body35');
   this.move(entry,article.querySelector(':scope>h3'),visual);
   const demo=work.querySelector(':scope>.guided-demo');
   if(demo)this.move(entry,demo,visual);
   else{const rest=article.querySelector(':scope>.rest-orbit');if(rest){const host=create('div','session-rest35',visual);this.move(entry,rest,host);}}
   this.move(entry,work.querySelector(':scope>.session-stats31'),controls);
   for(const n of [...article.children])if(n.matches('#guideRecord,.action-pair,button.btn.primary,p.warn'))this.move(entry,n,controls);
   const folds=create('div','session-folds35',article);
   this.move(entry,session.querySelector('#blockSlot22>.session-fold31'),folds);
   for(const selector of [':scope>.session-fold31[data-section31=instructions]',':scope>.session-fold31[data-section31=playback]',':scope>.btn.danger'])this.move(entry,article.querySelector(selector),folds);
  }
  ModalViewport.fit();UI26.schedule();
 },
 schedule(){if(this.pending)return;this.pending=requestAnimationFrame(()=>{this.pending=0;this.sync();});}
};
paintGuide11=function(){V35UI.paintGuide11();SessionLandscape35.sync();};
modal=function(html){SessionLandscape35.restore();$('#modal').classList.remove('session-landscape35');V35UI.modal(html);SessionLandscape35.sync();};
backModal24=function(){V35UI.backModal24();SessionLandscape35.sync();};
openDiagnostics20=function(){V35UI.openDiagnostics20();$('#modal').querySelectorAll('p').forEach(n=>n.textContent=n.textContent.replace('Versión 34.0.','Versión 35.0.'));};
window.addEventListener('resize',()=>SessionLandscape35.schedule(),{passive:true});
window.addEventListener('orientationchange',()=>SessionLandscape35.schedule(),{passive:true});
window.visualViewport?.addEventListener('resize',()=>SessionLandscape35.schedule(),{passive:true});
$('#modal').addEventListener('close',()=>{if(!$('#modal').open){SessionLandscape35.restore();$('#modal').classList.remove('session-landscape35');}});
window.HunterV35={SessionLandscape35};state.appVersion=35;save();SessionLandscape35.sync();
