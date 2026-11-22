/* Hunter System V9.6 — estabilidad del asistente, identidad sincronizada y paneles acotados. */
const V92Runtime={profileForm,render,home,plan,modal,openCoach,answerCoach,refreshMission,technique,bodyResults};

function companionDisplayName(type=selectedCompanion()){return CompanionNames[type]||CompanionNames.knight;}
const CompanionMentionSource=Object.values(CompanionNames).sort((a,b)=>b.length-a.length).map(x=>x.replace(/[.*+?^${}()|[\]\\]/g,'\\$&')).join('|');
const CompanionMentionPattern=new RegExp(CompanionMentionSource,'gi');
const CompanionMentionCheck=new RegExp(CompanionMentionSource,'i');
function personalizeCompanionString(value,type=selectedCompanion()){return String(value??'').replace(CompanionMentionPattern,companionDisplayName(type));}
function syncKnownCompanionUI(root,type=selectedCompanion()){
 if(!root)return root;const name=companionDisplayName(type);
 root.querySelectorAll?.('[data-selected-companion-label]').forEach(el=>el.textContent=name);
 const bannerTitle=root.querySelector?.('.coach-banner h2');if(bannerTitle)bannerTitle.textContent=`${name} está contigo.`;
 const panelName=root.querySelector?.('#companionName');if(panelName)panelName.textContent=name;
 root.querySelectorAll?.('.mission-ai-copy b').forEach(el=>el.textContent=name);
 const floating=root.querySelector?.('#companion');if(floating){floating.setAttribute('aria-label',`Abrir asistente ${name}`);const label=floating.querySelector('span');if(label)label.textContent=`${name} · IA`;}
 return root;
}
function personalizeCompanionText(root,type=selectedCompanion()){
 if(!root)return root;const skip=node=>node?.parentElement?.closest?.('[data-companion-static="true"]');
 syncKnownCompanionUI(root,type);
 const walker=document.createTreeWalker(root,NodeFilter.SHOW_TEXT);const nodes=[];while(walker.nextNode())nodes.push(walker.currentNode);
 nodes.forEach(node=>{if(skip(node)||!CompanionMentionCheck.test(node.nodeValue||''))return;node.nodeValue=personalizeCompanionString(node.nodeValue,type);});
 root.querySelectorAll?.('[aria-label],[title],[placeholder]').forEach(el=>{if(el.closest('[data-companion-static="true"]'))return;for(const a of ['aria-label','title','placeholder'])if(el.hasAttribute(a)&&CompanionMentionCheck.test(el.getAttribute(a)||''))el.setAttribute(a,personalizeCompanionString(el.getAttribute(a),type));});
 return root;
}
function previewCompanionChoice(input){if(!input?.form||!CompanionNames[input.value])return;const form=input.form;form.querySelectorAll('.companion-option').forEach(card=>{const radio=card.querySelector('input'),small=card.querySelector('small');card.classList.toggle('selected',radio.checked);if(small)small.textContent=radio.checked?'IA seleccionada':'Tocar para seleccionar';});syncKnownCompanionUI(form,input.value);personalizeCompanionText(form,input.value);}

profileForm=function(initial=false){const box=document.createElement('div');box.innerHTML=V92Runtime.profileForm(initial);const current=state.profile?.companionCharacter||'knight',grid=box.querySelector('.companion-picker'),field=box.querySelector('.companion-picker-block');if(grid){grid.dataset.companionStatic='true';grid.querySelectorAll('input[name=companionCharacter]').forEach(input=>input.setAttribute('onchange','previewCompanionChoice(this)'));field?.querySelector('.footnote')?.insertAdjacentHTML('afterend',`<div class="companion-picker-summary"><span class="eyebrow">COMPAÑERO SELECCIONADO</span><strong data-selected-companion-label>${companionDisplayName(current)}</strong><small>Solo puede cambiarse desde Perfil. El nombre y el avatar se sincronizan con Inicio, Misiones y el asistente.</small></div>`);}personalizeCompanionText(box,current);return box.innerHTML;};

function closeActiveModal(){const d=$('#modal');if(!d?.open)return;if(d.querySelector('.coach-panel')){void closeCoach();return;}d.close();}
function fitActiveModal(){ModalViewport.fit();}
function lockModalViewport(){ModalViewport.lock();}
function unlockModalViewport(){ModalViewport.release();}
function decorateModal(type=selectedCompanion()){const d=$('#modal');if(!d)return;const legacy=Array.from(d.children).find(el=>el.tagName==='BUTTON'&&el.textContent.trim()==='Cerrar'&&el.classList.contains('full'));if(legacy){legacy.classList.add('legacy-modal-close');legacy.setAttribute('aria-hidden','true');legacy.tabIndex=-1;}let x=d.querySelector('.modal-close-x');if(!x){x=document.createElement('button');x.type='button';x.className='modal-close-x';x.setAttribute('aria-label','Cerrar ventana');x.setAttribute('title','Cerrar');x.innerHTML='&times;';x.onclick=closeActiveModal;}d.prepend(x);personalizeCompanionText(d,type);lockModalViewport();}
modal=function(html){V92Runtime.modal(html);decorateModal();};
refreshMission=function(){V92Runtime.refreshMission();if($('#modal')?.open)decorateModal();};
technique=function(id){V92Runtime.technique(id);decorateModal();};
bodyResults=function(q=''){return personalizeCompanionString(V92Runtime.bodyResults(q));};

const modalDialog=$('#modal');
modalDialog?.addEventListener('close',unlockModalViewport);
modalDialog?.addEventListener('cancel',event=>{event.preventDefault();closeActiveModal();});
window.visualViewport?.addEventListener('resize',fitActiveModal,{passive:true});
window.addEventListener('resize',fitActiveModal,{passive:true});

function injuryFocusSummary(p=state.profile){return InjuryAwareV93.summary(p);}
function addCompanionAndInjuryContext(box,type=selectedCompanion()){const name=companionDisplayName(type);const banner=box.querySelector('.coach-banner');if(banner){const title=banner.querySelector('h2');if(title)title.textContent=`${name} está contigo.`;if(!banner.querySelector('.coach-banner-companion'))banner.insertAdjacentHTML('afterbegin',`<div class="coach-banner-companion" data-companion-static="true">${IgrisArt.svg(type)}</div>`);}const summary=injuryFocusSummary();if(summary?.auto){const target=box.querySelector('.mission-ai-presence')||box.querySelector('.mission-launch .mission-window-head')||box.querySelector('.mission-launch');if(target&&!box.querySelector('.injury-focus-note'))target.insertAdjacentHTML('afterend',`<div class="injury-focus-note"><b>Enfoque con lesión declarada</b><span>${esc(summary.text)}</span><small>Regla conservadora basada en las zonas y restricciones que registraste; no es rehabilitación ni diagnóstico.</small></div>`);}return box;}
home=function(){const box=document.createElement('div');box.innerHTML=V92Runtime.home();return addCompanionAndInjuryContext(box).innerHTML;};
plan=function(){const box=document.createElement('div');box.innerHTML=V92Runtime.plan();return addCompanionAndInjuryContext(box).innerHTML;};

openCoach=function(){V92Runtime.openCoach();decorateModal();const row=$('#modal')?.querySelector('.row.wrap'),summary=injuryFocusSummary();if(row&&summary&&!row.querySelector('[data-injury-routine]'))row.insertAdjacentHTML('beforeend','<button class="btn ghost" data-injury-routine onclick="askCoach(\'crear rutina según mis lesiones\')">Rutina según mis restricciones</button>');personalizeCompanionText($('#modal'));fitActiveModal();};
function openAnimationGallery(){modal(`<section data-companion-static="true"><h2>Cinco compañeros · muestras 2D</h2><p>Cada muestra conserva su propio nombre y reproduce entrada, espera y salida. El compañero activo solo se cambia desde Perfil.</p>${Object.entries(CompanionNames).map(([key,name])=>`<details class="card" ontoggle="if(this.open){this.querySelector('img').src=this.querySelector('img').dataset.src}else{this.querySelector('img').removeAttribute('src')}"><summary>${name}</summary><img loading="lazy" data-src="media/companion-${name.toLowerCase().replace(' ','-')}.gif" alt="Entrada, espera y salida de ${name}" style="max-height:520px;max-width:100%"></details>`).join('')}</section>`);}
answerCoach=function(q){const p=state.profile,plain=String(q||'').toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g,''),active=Adapt.active(p);if(active.length&&/(rutina|plan|entren|lesion|dolor|molest|restric|grupo muscular)/.test(plain)){const summary=injuryFocusSummary(p),name=companionDisplayName();if(!summary.auto)return `${name}: ${summary.text} No asignaré ejercicios automáticamente mientras ese estado siga activo. Puedes conservar tus misiones personales y registrar las indicaciones recibidas.`;const kinds=['strength','cardio','recovery'],rotation=(currentCycle()?.index||0)*7+Math.max(0,E.diff(E.dateKey(),currentCycle()?.start||E.dateKey()));const previews=kinds.map((kind,i)=>({kind,items:Schedule.items(GoalPlans.adjusted(p),kind,rotation+i)})).filter(x=>x.items.length);const labels={strength:'fuerza',cardio:'cardio',recovery:'movilidad'};const proposal=previews.map(x=>`${labels[x.kind]}: ${x.items.map(y=>E.byId(y.id).name).join(', ')}`).join('\n');return `${name}: ${summary.text}\n${proposal||'No encontré ejercicios compatibles suficientes con los filtros actuales.'}\nLas sesiones rotan ejercicios compatibles para no repetir siempre la misma combinación. Si aparece dolor o empeoran los síntomas, detén esa sesión y actualiza tus restricciones. Esto no crea un tratamiento de rehabilitación.`;}return personalizeCompanionString(V92Runtime.answerCoach(q));};

render=function(){if(Runtime20.initializing)return;V92Runtime.render();personalizeCompanionText($('#app'));document.querySelectorAll('.brand small').forEach(el=>el.textContent='EL DESPERTAR · V9.6');};
state.appVersion=9;render();syncNative();
