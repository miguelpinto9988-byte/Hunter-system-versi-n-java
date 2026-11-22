/* Mismos SVG y coordenadas; evita lecturas de estilo y escrituras repetidas. */
(function(root){'use strict';
const sessions=new WeakMap(),attributes=new WeakMap(),texts=new WeakMap();
const counters48={draws:0,compiled:0,fastFrames:0,fallbacks:0};
const literal48=s=>s.replace(/[.*+?^${}()|[\]\\]/g,'\\$&');
function decoded48(s){
 if(!s.includes('&'))return s;
 return s.replace(/&(amp|lt|gt|quot|apos|#\d+|#x[\da-f]+);/gi,(whole,key)=>{
  const named={amp:'&',lt:'<',gt:'>',quot:'"',apos:"'"};if(key in named)return named[key];
  const point=key[1]?.toLowerCase()==='x'?parseInt(key.slice(2),16):parseInt(key.slice(1),10);
  return point>0&&point<=0x10ffff?String.fromCodePoint(point):'\ufffd';
 });
}
// Compile the existing SVG markup into bindings. Only its values change on a
// stable frame; the exact same continuous pose generator remains authoritative.
// A topology change falls back to the browser parser and builds new bindings.
function compile48(markup,svg){
 const nodes=[svg,...svg.querySelectorAll('*')],stack=[],bindings=[],parts=[];
 const token=/<!--[^]*?-->|<[^>]*>|[^<]+/g;let match,index=0,end=0;
 const capture=(from,to,node,name,quote)=>{parts.push(literal48(markup.slice(end,from)),name?(quote==="'"?"([^']*)":'([^"]*)'):'([^<]*)');end=to;bindings.push({node,name,raw:markup.slice(from,to)});};
 while((match=token.exec(markup))){const value=match[0];
  if(value.startsWith('<!--'))continue;
  if(value.startsWith('</')){stack.pop();continue;}
  if(value[0]==='<'){
   const tag=value.match(/^<([\w:-]+)/)?.[1],node=nodes[index++];
   if(!node||node.localName!==tag)return null;
   const attr=/([\w:-]+)\s*=\s*(["'])(.*?)\2/g;let a;
   while((a=attr.exec(value))){const from=match.index+a.index+a[0].indexOf(a[2])+1;capture(from,from+a[3].length,node,a[1],a[2]);}
   if(!value.endsWith('/>'))stack.push(node);
  }else if(value.trim()){
   const node=stack.at(-1);if(!node||node.children.length)return null;
   capture(match.index,match.index+value.length,node,null);
  }
 }
 if(index!==nodes.length)return null;
 parts.push(literal48(markup.slice(end)));const pattern=new RegExp('^'+parts.join('')+'$');
 if(!pattern.test(markup))return null;
 return {pattern,bindings,svg,detail:null};
}
function update48(plan,markup){
 const values=plan.pattern.exec(markup);if(!values)return false;
 for(let i=0;i<plan.bindings.length;i++){
  const b=plan.bindings[i],raw=values[i+1];if(raw===b.raw)continue;b.raw=raw;
  if(!b.node.isConnected)continue;const value=decoded48(raw);
  if(b.name){
   if(b.node!==plan.svg||!plan.detail||b.name!=='viewBox')b.node.setAttribute(b.name,value);
   if(b.node===plan.svg&&plan.detail&&!['viewBox','class','aria-label'].includes(b.name))plan.detail.setAttribute(b.name,value);
  }else b.node.textContent=value;
 }
 return true;
}
function patch(current,next){
 let old=attributes.get(current);if(!old){old=new Map(Array.from(current.attributes,a=>[a.name,a.value]));attributes.set(current,old);}
 if(old.size!==next.attributes.length||[...old.keys()].some(k=>!next.hasAttribute(k)))for(const key of old.keys())if(!next.hasAttribute(key)){current.removeAttribute(key);old.delete(key);}
 for(const a of next.attributes)if(old.get(a.name)!==a.value){current.setAttribute(a.name,a.value);old.set(a.name,a.value);}
 const n=next.children.length;if(!n){const value=next.textContent;if((texts.has(current)?texts.get(current):current.textContent)!==value)current.textContent=value;texts.set(current,value);return;}texts.delete(current);
 for(let i=0;i<n;i++){const a=current.children[i],b=next.children[i];if(!a)current.append(b.cloneNode(true));else if(a.tagName!==b.tagName)a.replaceWith(b.cloneNode(true));else patch(a,b);}while(current.children.length>n)current.lastElementChild.remove();
}
function draw(canvas,id,phase=0,mirror=false){
 if(!canvas)return;const {model,base}=Motion30.get(id);let entry=sessions.get(canvas);
 if(!entry||canvas.nextElementSibling!==entry.host){canvas.hidden=true;canvas.dataset.renderer='2d';let host=canvas.nextElementSibling;if(!host?.classList.contains('motion-svg18')){host=document.createElement('div');host.className='motion-svg18';canvas.after(host);}entry={host,template:document.createElement('template')};sessions.set(canvas,entry);}
 const host=entry.host;if(canvas.dataset.base!==base)canvas.dataset.base=base;if(host.dataset.exercise!==id)host.dataset.exercise=id;host.dataset.phase=String(phase);
 const accent=document.documentElement.style.getPropertyValue('--accent').trim()||'#7ce2e9',markup=Motion2D18.svg(id,phase,mirror,accent);counters48.draws++;
 if(entry.id48===id&&entry.plan48&&entry.host.firstElementChild===entry.plan48.svg&&update48(entry.plan48,markup)){counters48.fastFrames++;return model;}
 entry.template.innerHTML=markup;
 const next=entry.template.content.firstElementChild,detail=next.querySelector('[data-detail-layout31]');let detailSVG=null;
 const plan=compile48(markup,next);
 if(detail){detailSVG=next.cloneNode(false);detailSVG.setAttribute('viewBox','58 620 604 288');detailSVG.setAttribute('class','detail-svg31');detailSVG.setAttribute('aria-label','Detalle animado de '+E.byId(id).name);detailSVG.append(detail);next.setAttribute('viewBox','0 0 720 610');next.querySelector('text[y="972"]')?.remove();}
 const hasDetail=String(!!detailSVG);if(host.dataset.hasDetail31!==hasDetail)host.dataset.hasDetail31=hasDetail;
 if(plan){host.replaceChildren(next);if(detailSVG)host.append(detailSVG);plan.detail=detailSVG;entry.plan48=plan;entry.id48=id;counters48.compiled++;}
 else{entry.plan48=null;const current=host.firstElementChild;if(current?.tagName===next.tagName)patch(current,next);else host.replaceChildren(next);if(detailSVG){const currentDetail=host.children[1];if(currentDetail)patch(currentDetail,detailSVG);else host.append(detailSVG);}else host.children[1]?.remove();counters48.fallbacks++;}
 return model;
}
root.SvgRenderer373={draw,patch,snapshot48:()=>({...counters48})};drawVector=draw;
vectorPaint=function(){const s=vectorSession,c=$('#vectorCanvas');if(!s||!c)return;drawVector(c,s.id,s.frame,s.mirror);const value=String(Math.floor(s.frame)%48),range=$('#vectorRange'),phase=$('#vectorPhase'),label=Motion2D18.label(s.id,s.frame);if(range&&range.value!==value)range.value=value;if(phase&&phase.textContent!==label)phase.textContent=label;};
})(window);
