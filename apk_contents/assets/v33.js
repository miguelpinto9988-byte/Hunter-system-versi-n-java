/* Monarca: paletas completas, ilustraciones por rango y diseño adaptable. */
'use strict';
const V33UI={applyTheme,profileForm,submitProfile,render,home,openCoach,modal,validateBackup,openDiagnostics20};
Visual33.installFrame();
state.appearance33=Visual33.preferences();
validateBackup=function(data){if(!V33UI.validateBackup(data))return false;const a=data.appearance33;if(a===undefined)return true;return a&&typeof a==='object'&&!Array.isArray(a)&&Object.hasOwn(Visual33.palettes,a.palette)&&Object.hasOwn(Visual33.collections,a.collection)&&['auto','sanctuary','journey'].includes(a.scene);};
Evolution28.stages=Visual33.ranks;
Evolution28.selection=function(p=state.profile||{}){return Visual33.selection(p);};
const layout33=Evolution28.layout;
Evolution28.layout=function(s=this.current||this.selection()){
 layout33.call(this,s);document.documentElement.dataset.rank33=s.stage.key;
 for(const art of document.querySelectorAll('.evolution-art28')){art.dataset.rank33=s.stage.key;const hero=art.closest('.hero26');if(hero){const img=hero.querySelector('.hero-figure33');if(img&&img.getAttribute('src')!==s.figure)img.src=s.figure;}}
};
applyTheme=function(p=state.profile||{},prefs=Visual33.preferences()){
 V33UI.applyTheme(p);const root=document.documentElement,colors=Visual33.colors(root.dataset.theme,prefs.palette);
 colors['--glow']='hsla('+(ThemeHues[root.dataset.theme]||202)+',35%,60%,.12)';
 for(const [key,value]of Object.entries(colors))root.style.setProperty(key,value);
 root.dataset.palette33=prefs.palette;root.dataset.frame33='monarca';
 document.querySelector('meta[name=theme-color]').content=colors['--bg'];themeNative20();
 Evolution28.layout(Visual33.selection(p,undefined,prefs));
};
previewTheme=function(form){
 const value=(name,fallback)=>form.elements.namedItem(name)?.value||fallback;
 const p={...(state.profile||{}),gender:value('gender','unspecified'),theme:value('theme','auto'),animate:form.elements.namedItem('animate')?.checked!==false};
 const prefs=Visual33.preferences({palette:value('palette33','bruma'),collection:value('collection33','hunter'),scene:value('scene33','auto')});
 applyTheme(p,prefs);form.querySelectorAll('[data-theme33]').forEach(b=>b.setAttribute('aria-pressed',String(b.dataset.theme33===document.documentElement.dataset.theme)));
 const image=form.querySelector('.appearance-preview33 img');const s=Visual33.selection(p,undefined,prefs);if(image)image.src=s.figure;
 const box=form.querySelector('.appearance-preview33');if(box)box.style.setProperty('--preview-scene33','url("'+s.asset.path+'")');
};
function selectTheme33(button){const form=button.closest('form'),select=form.elements.namedItem('theme');select.value=button.dataset.theme33;previewTheme(form);}
profileForm=function(initial=false){
 const box=document.createElement('div');box.innerHTML=V33UI.profileForm(initial);const form=box.querySelector('form');if(!form)return box.innerHTML;
 const a=Visual33.preferences(),s=Visual33.selection();
 box.querySelectorAll('.evolution-note28').forEach(n=>n.remove());
 const theme=form.querySelector('[name=theme]');const label=theme?.closest('label');if(label){label.classList.add('theme-select33');label.insertAdjacentHTML('beforeend','<span class="theme-swatches33">'+Object.entries(Visual33.themes).map(([key,name])=>'<button class="theme-swatch33" type="button" data-theme33="'+key+'" style="--swatch33:hsl('+ThemeHues[key]+' 33% 66%)" aria-label="'+name+'" aria-pressed="'+(document.documentElement.dataset.theme===key)+'" onclick="selectTheme33(this)"><i></i><span>'+name+'</span></button>').join('')+'</span>');}
 const field=document.createElement('fieldset');field.className='appearance-settings33';field.dataset.companionStatic='true';field.innerHTML='<legend>Estética Monarca</legend><p>Diez temas, tres combinaciones por tema. El color se aplica también a fondos, tarjetas y opciones seleccionadas.</p><div class="palette-options33">'+Object.entries(Visual33.palettes).map(([key,name])=>'<label style="--palette-hue33:'+(key==='umbral'?267:key==='lunar'?151:202)+'"><input type="radio" name="palette33" value="'+key+'" '+(a.palette===key?'checked':'')+' onchange="previewTheme(this.form)"><span><i></i><b>'+name+'</b><small>'+(key==='bruma'?'Niebla y acento suave':key==='umbral'?'Tinta y matiz amatista':'Matiz mineral y luz lunar')+'</small></span></label>').join('')+'</div><div class="appearance-layout33"><div class="appearance-preview33" style="--preview-scene33:url(&quot;'+s.asset.path+'&quot;)">'+Visual33.figureHTML(s,'appearance-figure33')+'<b>RANGO '+s.stage.key+'</b></div><div><label>Colección visual<select name="collection33" onchange="previewTheme(this.form)">'+options(Visual33.collections,a.collection)+'</select></label><label>Escenario<select name="scene33" onchange="previewTheme(this.form)">'+options({auto:'Alternar cada día',sanctuary:'Santuarios y portales',journey:'Viajes y constelaciones'},a.scene)+'</select></label><p class="footnote">El cazador sigue el género de tu perfil. Las colecciones de Kim Dokja y Junghyeok Yu mantienen la apariencia de esos personajes. La colección visual no cambia tu compañero de IA.</p><button class="btn ghost" type="button" onclick="openEvolution33()">Ver las seis evoluciones →</button></div></div><p class="footnote">E · 1–5 / D · 6–10 / C · 11–15 / B · 16–20 / A · 21–25 / S · 26+. Es una evolución artística ligada a tus registros.</p>';
 const identity=form.querySelector('[name=difficulty]')?.closest('.profile-block')||form.firstElementChild;identity?.append(field);
 for(const opt of box.querySelectorAll('.companion-option')){const type=opt.querySelector('input')?.value,art=opt.querySelector('.companion-option-art');if(art)art.innerHTML='<img src="'+Visual33.companionPath(type)+'" alt="" loading="lazy">';}
 const summary=box.querySelector('.companion-picker-summary');if(summary)summary.querySelector('svg')?.replaceWith(Object.assign(document.createElement('img'),{src:Visual33.companionPath(),alt:''}));
 return box.innerHTML;
};
submitProfile=function(event){
 const f=event.target,previous=state.profile,previousAppearance=state.appearance33,appearance=Visual33.preferences({palette:f.elements.namedItem('palette33')?.value,collection:f.elements.namedItem('collection33')?.value,scene:f.elements.namedItem('scene33')?.value});
 state.appearance33=appearance;V33UI.submitProfile(event);
 if(state.profile===previous)state.appearance33=previousAppearance;
};
home=function(){
 const box=document.createElement('div');box.innerHTML=V33UI.home();const hero=box.querySelector('.hero26'),s=Visual33.selection();
 if(hero){hero.classList.add('monarch-hero33');hero.insertAdjacentHTML('afterbegin',Visual33.figureHTML(s));const stage=hero.querySelector('.scene-stage28');if(stage)stage.textContent='RANGO '+s.stage.key+' · '+s.stage.name.toUpperCase();hero.insertAdjacentHTML('beforeend','<button type="button" class="hero-evolution33" onclick="openEvolution33()" aria-label="Ver evolución visual por rango">'+s.stage.key+' <span>EVOLUCIÓN</span> ⟶</button>');}
 const sigil=box.querySelector('.cycle-sigil26');if(sigil)sigil.innerHTML='<img src="'+Visual33.companionPath()+'" alt="" loading="lazy">';
 const chip=box.querySelector('.adaptation-chip'),aside=box.querySelector('.dashboard26>aside');if(chip&&aside)aside.append(chip);
 return box.innerHTML;
};
class ShadowActor33{
 constructor(host,type){this.host=host;this.type=type;this.alive=true;this.paused=false;this.timer=0;this.motionTimer=0;this.sequence=0;this.pending=new Map();host.classList.add('shadow-actor33');host.dataset.character=type;host.innerHTML='<span class="actor-aura33" aria-hidden="true"></span><img src="'+Visual33.companionPath(type)+'" alt="" decoding="async">';this.mode('idle');}
 allowed(){return this.alive&&!this.paused&&!document.hidden&&state.profile?.animate!==false&&!matchMedia('(prefers-reduced-motion: reduce)').matches;}
 mode(name){if(this.host?.isConnected)this.host.dataset.action33=name;}
 delay(ms){return new Promise(resolve=>{const id=setTimeout(()=>{this.pending.delete(id);resolve(this.alive);},ms);this.pending.set(id,resolve);});}
 async open(){this.mode(this.allowed()?'enter':'idle');const done=await this.delay(this.allowed()?620:0);if(done){this.mode('idle');this.schedule();}return done;}
 schedule(){clearTimeout(this.timer);if(!this.allowed())return;this.timer=setTimeout(()=>{if(!this.allowed())return;const gestures=['look','bow','wave','float','celebrate'];this.perform(gestures[Math.floor(Math.random()*gestures.length)]);this.schedule();},6500+Math.random()*8000);}
 perform(name){if(!this.allowed())return;clearTimeout(this.motionTimer);const map={think:'look',train:'float',flip:'float',power:'celebrate',walk:'look'};this.mode(map[name]||name);this.motionTimer=setTimeout(()=>{if(this.alive)this.mode('idle');},1600);}
 pause(){this.paused=true;clearTimeout(this.timer);clearTimeout(this.motionTimer);this.host?.classList.add('actor-paused33');}
 resume(){if(!this.alive)return;this.paused=false;this.host?.classList.remove('actor-paused33');this.mode('idle');this.schedule();}
 async close(){clearTimeout(this.timer);clearTimeout(this.motionTimer);this.mode(this.allowed()?'exit':'idle');return this.delay(this.allowed()?380:0);}
 destroy(){this.alive=false;clearTimeout(this.timer);clearTimeout(this.motionTimer);for(const [id,resolve]of this.pending){clearTimeout(id);resolve(false);}this.pending.clear();if(this.host)this.host.innerHTML='';}
}
mountPersistentCompanion=function(host,type){if(!host)return null;const actor=new ShadowActor33(host,type);actor.schedule();return actor;};
mountPanelCompanion=function(type){releaseCompanion();const host=$('#animatedCompanion');if(host){activeCompanion=new ShadowActor33(host,type);activeCompanion.open();}const name=$('#companionName');if(name)name.textContent=CompanionNames[type];};
let phraseTimer33=0;
const phrases33={knight:['Tu siguiente paso cuenta.','La constancia forja tu rango.','Una serie. Un avance.'],warrior:['Firme. Controla cada repetición.','Tu técnica sostiene tu fuerza.','Volvamos a la misión.'],bear:['Respira. También puedes descansar.','Paso a paso, seguimos contigo.','Recuperar también es avanzar.'],insect:['A tus órdenes. Comencemos.','Un nuevo desafío te espera.','Cada misión cuenta, cazador.'],scientist:['Tu historia avanza una página.','Escribe hoy tu siguiente logro.','Cada decisión construye tu camino.']};
function updatePhrase33(){const bubble=$('#shadowPhrase33');if(!bubble)return;const type=selectedCompanion(),list=phrases33[type]||phrases33.knight;let index=(Number(bubble.dataset.index)||0)+1;bubble.dataset.index=String(index);bubble.textContent=list[index%list.length];}
function schedulePhrase33(){clearTimeout(phraseTimer33);if(!$('#modal').open||!$('#shadowPhrase33')||document.hidden||state.profile?.animate===false||matchMedia('(prefers-reduced-motion: reduce)').matches)return;phraseTimer33=setTimeout(()=>{updatePhrase33();schedulePhrase33();},20000+Math.random()*12000);}
openCoach=function(){
 V33UI.openCoach();const intro=$('#modal .coach-intro');if(!intro)return;intro.classList.add('shadow-showcase33');
 $('#modal').querySelectorAll('.playback-speeds').forEach(node=>node.remove());
 const host=$('#animatedCompanion');host?.removeAttribute('style');
 const heading=$('#companionName');if(heading){const previous=heading.parentElement,copy=document.createElement('div');copy.className='shadow-name33';copy.append(heading);intro.append(copy);if(previous!==intro)previous.remove();copy.insertAdjacentHTML('afterbegin','<span class="eyebrow">TU COMPAÑERO DE MISIÓN</span>');copy.insertAdjacentHTML('beforeend','<span class="tag">SOMBRA VINCULADA</span>');}
 intro.insertAdjacentHTML('beforeend','<p id="shadowPhrase33" class="shadow-phrase33">'+esc(phrases33[selectedCompanion()][0])+'</p>');
 const section=intro.closest('.coach-panel');if(section){section.classList.add('coach-layout33');const left=document.createElement('div'),right=document.createElement('div');left.className='shadow-column33';right.className='coach-content33';for(const node of [...section.children]){if(node===intro)left.append(node);else if(!node.matches(':scope>.eyebrow')&&!node.classList.contains('eyebrow'))right.append(node);}section.append(left,right);}
 const oldDescription=intro.querySelector(':scope>p:not(.shadow-phrase33)');oldDescription?.remove();
 $('#modal').querySelector('.automatic-note19')?.remove();
 const s=Visual33.selection();intro.style.setProperty('--shadow-scene33','url("'+s.asset.path+'")');
 decorate33();schedulePhrase33();ModalViewport.fit();UI26.schedule();
};
function decorate33(){
 if(!document.documentElement.dataset.frame33)return;
 for(const frame of document.querySelectorAll('.ornament26:not(.monarch-frame33),.window-frame26:not(.monarch-frame33)'))frame.outerHTML=UI26.frame(frame.classList.contains('window-frame26'));
 const modal=$('#modal');modal?.classList.toggle('coach-window33',!!modal.querySelector('.shadow-showcase33'));
}
const decorateBefore33=UI26.decorate;
UI26.decorate=function(root){decorateBefore33.call(this,root);decorate33();};
render=function(){if(Runtime20.initializing)return;V33UI.render();document.querySelectorAll('.brand small').forEach(n=>n.textContent='SISTEMA DE SOMBRAS');applyTheme();Visual33.preload();decorate33();};
modal=function(html){clearTimeout(phraseTimer33);V33UI.modal(html);decorate33();};
$('#modal').addEventListener('close',()=>{clearTimeout(phraseTimer33);$('#modal').classList.remove('coach-window33');});
document.addEventListener('visibilitychange',()=>{for(const actor of [activeCompanion,homeCompanion,missionCompanion]){if(document.hidden)actor?.pause();else actor?.resume();}schedulePhrase33();});
matchMedia('(prefers-reduced-motion: reduce)').addEventListener?.('change',()=>{for(const actor of [activeCompanion,homeCompanion,missionCompanion]){if(matchMedia('(prefers-reduced-motion: reduce)').matches)actor?.pause();else actor?.resume();}schedulePhrase33();});
window.addEventListener('pagehide',()=>{clearTimeout(phraseTimer33);for(const actor of [activeCompanion,homeCompanion,missionCompanion])actor?.pause();});
window.addEventListener('pageshow',()=>{if(!document.hidden){for(const actor of [activeCompanion,homeCompanion,missionCompanion])actor?.resume();schedulePhrase33();}});
let evolutionPreview33={rank:'E',collection:'hunter',gender:'male',scene:0};
function openEvolution33(){const s=Visual33.selection();evolutionPreview33={rank:s.stage.key,collection:s.collection,gender:s.gender,scene:s.scene};paintEvolution33();}
function previewEvolution33(key,value){if(key==='rank'&&!Visual33.ranks.some(r=>r.key===value)||key==='collection'&&!['hunter','reader','regressor'].includes(value)||key==='gender'&&!['male','female'].includes(value)||key==='scene'&&![0,1].includes(Number(value)))return;evolutionPreview33[key]=key==='scene'?Number(value):value;paintEvolution33();}
function paintEvolution33(){
 const v=evolutionPreview33,rank=Visual33.ranks.find(r=>r.key===v.rank)||Visual33.ranks[0],s=Visual33.selection({gender:v.gender},rank.min,{collection:v.collection,palette:Visual33.preferences().palette,scene:v.scene?'journey':'sanctuary'}),live=Visual33.rank();
 modal('<section class="evolution-gallery33" data-companion-static="true"><span class="eyebrow">GALERÍA · EVOLUCIÓN VISUAL</span><h2>Tu ascenso, seis capítulos.</h2><p>Vista de las ilustraciones. Tu rango actual es '+live.key+'; esta galería no cambia tus registros.</p><div class="rank-tabs33" role="group" aria-label="Rango de muestra">'+Visual33.ranks.map(r=>'<button class="btn '+(r.key===rank.key?'selected':'ghost')+'" aria-pressed="'+(r.key===rank.key)+'" onclick="previewEvolution33(\'rank\',\''+r.key+'\')">'+r.key+'<small>'+r.min+(Number.isFinite(r.max)?'–'+r.max:'+')+'</small></button>').join('')+'</div><div class="gallery-layout33"><div class="evolution-stage33" style="--preview-scene33:url(&quot;'+s.asset.path+'&quot;)">'+Visual33.figureHTML(s,'gallery-figure33')+'<b>'+rank.key+'</b></div><div><h3>'+rank.name+'</h3><p>'+rank.detail+'</p><label>Colección<select onchange="previewEvolution33(\'collection\',this.value)">'+options({hunter:Visual33.collections.hunter,reader:Visual33.collections.reader,regressor:Visual33.collections.regressor},v.collection)+'</select></label>'+(v.collection==='hunter'?'<label>Cazador de muestra<select onchange="previewEvolution33(\'gender\',this.value)">'+options({male:'Masculino',female:'Femenino'},v.gender)+'</select></label>':'')+'<label>Escenario<select onchange="previewEvolution33(\'scene\',this.value)">'+options({0:'Santuario',1:'Viaje'},String(v.scene))+'</select></label><div class="notice">'+rank.xp.toLocaleString('es')+' XP acumulados · nivel '+rank.min+(Number.isFinite(rank.max)?' a '+rank.max:' en adelante')+'.</div><p class="footnote">La ilustración representa logros dentro de la app. No predice cambios del cuerpo. Puedes guardar tu colección y escenario desde Perfil.</p></div></div></section>');
}
openDiagnostics20=function(){V33UI.openDiagnostics20();for(const p of $('#modal').querySelectorAll('p'))p.textContent=p.textContent.replace(/Versión (30|32)\.0\./,'Versión 33.0.');};
window.HunterV33={Visual33,ShadowActor33,phrases33,decorate33};state.appVersion=33;save();applyTheme();render();
