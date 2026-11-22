/* Apariencia V33. Independiente del plan de entrenamiento y de sus registros. */
'use strict';
const Visual33={
 ranks:Object.freeze([
  {key:'E',name:'Despertar',min:1,max:5,xp:0,detail:'Ropa de entrenamiento · patio y sala de práctica'},
  {key:'D',name:'Primer portal',min:6,max:10,xp:1500,detail:'Chaqueta y guantes · corredor y ciudad bajo la lluvia'},
  {key:'C',name:'Cazador',min:11,max:15,xp:3000,detail:'Abrigo de combate · ruinas y plaza lunar'},
  {key:'B',name:'Ascenso',min:16,max:20,xp:4500,detail:'Armadura ligera · escalinata y azotea'},
  {key:'A',name:'Élite',min:21,max:25,xp:6000,detail:'Armadura ornamentada · gran salón y biblioteca'},
  {key:'S',name:'Monarca',min:26,max:Infinity,xp:7500,detail:'Manto, aura y equipo ceremonial · portal del monarca'}
 ]),
 themes:{blue:'Azul de portal',pink:'Rosa lunar',violet:'Amatista',emerald:'Jade',amber:'Ámbar',coral:'Coral de ocaso',red:'Carmesí',teal:'Marea',ice:'Hielo',gold:'Oro antiguo'},
 palettes:{bruma:'Bruma',umbral:'Umbral',lunar:'Lunar'},
 collections:{hunter:'El despertar del cazador',reader:'Crónica del lector · Kim Dokja',regressor:'Camino del regresor · Junghyeok Yu',alternate:'Alternar las tres colecciones'},
 companions:{knight:'igris',warrior:'iron',bear:'tank',insect:'beru',scientist:'dokja'},
 preferences(raw=state.appearance33){return {palette:Object.hasOwn(this.palettes,raw?.palette)?raw.palette:'bruma',collection:Object.hasOwn(this.collections,raw?.collection)?raw.collection:'hunter',scene:['auto','sanctuary','journey'].includes(raw?.scene)?raw.scene:'auto'};},
 rank(level=E.stats(state.logs||{}).level){const n=Math.max(1,Math.floor(Number(level)||1));return this.ranks[Math.min(5,Math.floor((n-1)/5))];},
 selection(p=state.profile||{},level=E.stats(state.logs||{}).level,prefs=this.preferences()){
  const stage=this.rank(level),day=Math.max(0,E.diff(E.dateKey(),'2026-01-01'));
  const collection=prefs.collection==='alternate'?['hunter','reader','regressor'][day%3]:prefs.collection;
  const scene=prefs.scene==='auto'?day%2:prefs.scene==='journey'?1:0;
  const gender=p.gender==='female'?'female':'male',figure=collection==='hunter'?'hunter-'+gender:collection;
  const background='ui33/scene-'+stage.key.toLowerCase()+'-'+scene+'.webp';
  return {theme:document.documentElement.dataset.theme||'blue',gender,row:0,column:0,level,stage,collection,scene,character:figure,figure:'ui33/'+figure+'-'+stage.key.toLowerCase()+'.webp',asset:{path:background,width:512,height:512,columns:1,rows:1}};
 },
 colors(theme='blue',palette='bruma'){
  const h=ThemeHues[theme]??ThemeHues.blue,shift=palette==='umbral'?56:palette==='lunar'?-35:38,secondary=(h+shift+360)%360,base=(h+(palette==='umbral'?24:palette==='lunar'?-22:0)+360)%360;
  const saturation=palette==='umbral'?27:palette==='lunar'?22:24;
  const hsl=(h,s,l)=>'hsl('+h+' '+s+'% '+l+'%)';
  return {'--bg':hsl(base,saturation,8),'--panel':hsl(base,25,15),'--surface':hsl(base,25,21),'--input':hsl(base,26,10),'--line':hsl(h,19,38),'--text':hsl(base,22,86),'--muted':hsl(base,19,73),'--accent':hsl(h,43,77),'--cyan':hsl(h,43,77),'--violet':hsl(secondary,38,74),'--ornament26':hsl(secondary,27,60),'--metal26':hsl(secondary,22,31),'--glow':'hsla('+h+',35%,60%,.12)','--tone33':hsl(secondary,27,18),'--selected33':hsl(h,32,29),'--secondary33':hsl(secondary,37,73),'--theme-hue26':String(h)};
 },
 companionPath(type=selectedCompanion()){return 'ui33/companion-'+(this.companions[type]||'igris')+'.webp';},
 figureHTML(s,cls='hero-figure33'){return '<img class="'+cls+'" src="'+s.figure+'" alt="" aria-hidden="true" decoding="async">';},
 cache:new Set(),
 preload(s=this.selection()){for(const path of [s.figure,s.asset.path,this.companionPath()]){if(this.cache.has(path))continue;this.cache.add(path);const img=new Image();img.src=path;img.decode?.().catch(()=>{});}},
 installFrame(){
  UI26.corner='<svg viewBox="0 0 58 58" fill="none" aria-hidden="true"><path d="M2 56V18L18 2h38l-9 7H22L9 22v25Z" fill="var(--metal26)" stroke="currentColor" stroke-width="1.2"/><path d="M6 47V21L21 6h26M13 38V24l11-11h14M2 34V18L18 2h16" stroke="var(--accent)" stroke-width=".8"/><path d="m15 15 13 2-11 11Z" fill="currentColor" fill-opacity=".7"/><path d="M2 56 9 47M47 9l9-7M4 7l3-3 5 1-7 7Z" stroke="currentColor"/><path d="m10 28 5 2-5 9Z" fill="var(--accent)" fill-opacity=".4"/></svg>';
  UI26.frame=function(windowFrame=false){return '<span class="'+(windowFrame?'window-frame26':'ornament26')+' monarch-frame33" aria-hidden="true">'+['tl','tr','bl','br'].map(c=>'<span class="corner26 '+c+'">'+this.corner+'</span>').join('')+'<span class="monarch-crest33"><svg viewBox="0 0 72 22" fill="none"><path d="M0 11h20l5-5 6 6 5-10 5 10 6-6 5 5h20M20 16h32M31 19h10" stroke="currentColor"/><path d="m36 8 4 6-4 5-4-5Z" fill="var(--accent)" fill-opacity=".5"/></svg></span><span class="monarch-jewel33">◇</span></span>';};
 }
};
window.HunterVisual33=Visual33;
