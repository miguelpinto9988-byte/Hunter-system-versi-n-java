(function(global){
'use strict';
class IgrisCharacter {
 constructor(host,type='knight',options={}) {
  if(!IgrisArt.labels[type]) throw Error('Personaje desconocido: '+type);
  this.host=host;this.type=type;host.dataset.renderer="2d";host.dataset.character=type;this.options=options;host.innerHTML=IgrisArt.svg(type);
  this.parts={};host.querySelectorAll('[data-part]').forEach(p=>this.parts[p.dataset.part]=p);
  this.state='hidden';this.elapsed=0;this.frame=0;this.last=0;this.renderLast=0;this.paused=false;
  this.automatic=options.automatic??host.id==='animatedCompanion';this.autoTimer=0;this.previousAction='';
  this.reduced=options.reducedMotion??matchMedia('(prefers-reduced-motion: reduce)').matches;
  this.visibility=()=>document.hidden?this.pause():this.resume();document.addEventListener('visibilitychange',this.visibility);
  this.sample('hidden',0);
 }
 transform(part,value){const node=this.parts[part];if(!node)return;this._usedTransforms?.add(part);if(node.getAttribute('transform')!==value)node.setAttribute('transform',value);}
 sample(state,ms){
  const t=ms/1000,reduced=this.reduced;
  this._usedTransforms=new Set();
  const tempo={knight:1,warrior:1.16,bear:.72,insect:1.42,scientist:.86}[this.type]||1;
  const phase=t*tempo;
  let opacity=state==='hidden'?0:1,x=0,y=0,s=1,angle=0;
  if(state==='entering'){
   const p=Math.min(1,ms/(reduced?100:980)),ease=1-Math.pow(1-p,3),overshoot=Math.sin(p*Math.PI)*.055;
   opacity=Math.min(1,p*4);s=reduced?1:.28+.72*ease+overshoot;y=reduced?0:(1-ease)*70;x=reduced?0:(this.type==='insect'?-90:this.type==='warrior'?60:34)*(1-ease);angle=reduced?0:(this.type==='bear'?-6:-14)*(1-ease);
  }
  if(state==='exiting'){
   const p=Math.min(1,ms/(reduced?100:760)),ease=p*p;
   opacity=1-ease;s=reduced?1:1-.62*ease;y=reduced?0:65*ease;x=reduced?0:(this.type==='insect'?70:-24)*ease;angle=reduced?0:16*Math.sin(p*Math.PI);
  }
  if(state==='idle'&&!reduced){
   const amp={knight:5,warrior:7,bear:2.5,insect:8,scientist:3.5}[this.type]||5;
   x=Math.sin(phase*1.2)*amp*.7;y=Math.sin(phase*2.15)*amp*.45;
   if(this.type==='insect')y-=5+Math.sin(phase*3.2)*3;
   if(this.type==='bear')angle=Math.sin(phase*.8)*.7;
   if(this.type==='scientist')angle=Math.sin(phase*.9)*1.1;
  }
  this.parts.root.setAttribute('opacity',opacity);
  this.parts.aura.setAttribute('opacity',opacity*(.68+.2*Math.sin(phase*2.1)));
  this.parts.shadow.setAttribute('opacity',opacity*(this.type==='insect'?.42:.58));
  this.parts.particles.setAttribute('opacity',reduced?0:opacity*.65);
  if(!reduced){
   if(state==='walk')x+=Math.sin(phase*3)*35;
   if(state==='flip'){angle+=360*Math.min(1,t/1.4);y-=65*Math.sin(Math.min(1,t/1.4)*Math.PI);}
   if(state==='celebrate')y-=Math.abs(Math.sin(phase*5))*25;
   if(state==='bow')angle+=18*Math.sin(Math.min(1,t/2.2)*Math.PI);
   if(state==='think')angle+=4*Math.sin(phase*3);
   if(state==='look')angle+=5*Math.sin(Math.min(1,t/2.2)*Math.PI*2);
   if(state==='stretch')s+=.025*Math.sin(Math.min(1,t/2.2)*Math.PI);
  }
  const pivot=state==='flip'?270:470;
  this.transform('root',`translate(${x} ${y}) translate(200 ${pivot}) rotate(${angle}) scale(${s}) translate(-200 -${pivot})`);
  if(!reduced){
   const wave=Math.sin(phase*2),flutter=Math.sin(phase*4),breath=Math.sin(phase*1.7);
   const bodyX=this.type==='bear'?.004:.008,bodyY=this.type==='warrior'?.018:this.type==='bear'?.007:.012;
   this.transform('body',`translate(200 420) scale(${1+breath*bodyX} ${1+breath*bodyY}) translate(-200 -420)`);
   const nod=state==='entering'&&this.type==='knight'?12*Math.sin(Math.min(1,ms/980)*Math.PI):0;
   this.transform('head',`rotate(${nod+wave*(this.type==='scientist'?1.5:2.5)} 200 285)`);
   if(state==='nod')this.transform('head',`rotate(${12*Math.sin(phase*5)} 200 285)`);
   this.transform('cape',`rotate(${wave*(this.type==='knight'?4:3)} 200 310)`);
   this.transform('plume',`rotate(${flutter*6} 215 105)`);
   let left=wave*4,right=-wave*4;
   if(this.type==='bear'){left=wave*2;right=-wave*2;}
    if(this.type==='scientist'&&state==='idle'){right=-8+Math.sin(phase*1.3)*5;}
    // V38.24: subtle alternate resting gestures; not breathing, no new animation loop.
    if(state==='idle' && !document.hidden && global.HunterEvolution385?.budget!=='eco'){
      const phasePose=Math.max(0,Math.sin(phase*.22));
      const mix=phasePose*phasePose*phasePose*phasePose;
      if(this.type==='knight'){left-=mix*7;right+=mix*9;}
      else if(this.type==='warrior'){left-=mix*12;right-=mix*6;}
      else if(this.type==='bear'){left+=mix*5;right-=mix*5;}
      else if(this.type==='insect'){left+=mix*9;right-=mix*9;}
      else if(this.type==='scientist'){left-=mix*4;right-=mix*11;}
    }
   if(state==='entering'||state==='wave'||state==='celebrate')right=-25+Math.sin(phase*15)*20;
   if(state==='exiting')right=-35+Math.sin(phase*18)*20;
   if(state==='point')right=-58+Math.sin(phase*3)*5;
   if(state==='stretch'){right=-65*Math.sin(Math.min(1,t/2.2)*Math.PI);left=-right;}
   if(state==='listen'){right=-18+Math.sin(phase*2)*3;this.transform('head',`rotate(-7 200 285)`);}
   this.transform('armLeft',`rotate(${left} 160 315)`);
   this.transform('armRight',`rotate(${right} 240 315)`);
   const wingSpeed=this.type==='insect'?18:15,wingAmp=this.type==='insect'?18:13;
   this.transform('wingLeft',`rotate(${Math.sin(phase*wingSpeed)*wingAmp} 185 320)`);
   this.transform('wingRight',`rotate(${-Math.sin(phase*wingSpeed)*wingAmp} 215 320)`);
   this.transform('antennaLeft',`rotate(${flutter*5} 150 155)`);
   this.transform('antennaRight',`rotate(${-flutter*5} 250 155)`);
   this.transform('flames',`translate(200 160) scale(${1+flutter*(this.type==='warrior'?.065:.045)} ${1+wave*.04}) translate(-200 -160)`);
   const blinkWindow=this.type==='bear'?4700:this.type==='scientist'?3600:4100,blink=(ms%blinkWindow>blinkWindow-300&&ms%blinkWindow<blinkWindow-120)?.12:1;
   this.transform('eyes',`translate(200 218) scale(1 ${blink}) translate(-200 -218)`);
   this.transform('particles',`translate(${Math.sin(phase*.8)*3} ${-8*Math.sin(phase)})`);
   const stride=state==='walk'?Math.sin(phase*8)*7:0;
   this.transform('legLeft',`rotate(${stride} 177 400)`);this.transform('legRight',`rotate(${-stride} 218 400)`);
   if(this.type==='scientist')this.transform('mouth',`translate(200 264) scale(1 ${state==='think'?1+Math.sin(phase*8)*.22:1+breath*.035}) translate(-200 -264)`);
   this.transform('shadow',`translate(200 480) scale(${state==='celebrate'?1-Math.abs(Math.sin(phase*5))*.18:1+breath*.025} 1) translate(-200 -480)`);
  }
 }
  // Clear transforms only on parts not animated in this frame.
  // (Cleanup injected immediately below through a sample wrapper.)

 tick=(now)=>{this.frame=0;if(this.paused||this.state==='hidden')return;if(this.last)this.elapsed+=Math.max(0,now-this.last)*(this.reduced?1:MotionPlayback.rate);this.last=now;
  if(['walk','flip','celebrate','bow','wave','think','nod','look','stretch','point','listen'].includes(this.state)&&this.elapsed>=2200){this.state='idle';this.elapsed=0;}
  if(this.state==='entering'&&this.elapsed>=(this.reduced?100:980)){this.state='idle';this.elapsed=0;}
  if(this.state==='exiting'&&this.elapsed>=(this.reduced?100:760)){this.state='hidden';this.sample('hidden',0);this.finish?.(true);this.finish=null;return;}
  if(!this.reduced&&this.renderLast&&now-this.renderLast<(global.HunterPerf380?.actorInterval(this.state)||32)){this.frame=requestAnimationFrame(this.tick);return;}this.renderLast=now;
  const drawStart380=performance.now();this.sample(this.state,this.elapsed);global.HunterPerf380?.sampleDrawCost(performance.now()-drawStart380);this.frame=requestAnimationFrame(this.tick);
 };
 perform(kind){if(!['walk','flip','celebrate','bow','wave','think','nod','look','stretch','point','listen'].includes(kind)||this.destroyed||this.state==='exiting')return;cancelAnimationFrame(this.frame);this.state=kind;this.elapsed=0;this.last=0;this.renderLast=0;if(!this.paused)this.frame=requestAnimationFrame(this.tick);}
 clearAutomatic(){clearTimeout(this.autoTimer);this.autoTimer=0;}
 automaticStep(){
  this.clearAutomatic();
  if(!this.automatic||this.reduced||this.destroyed||this.paused||document.hidden||this.state==='hidden'||this.state==='exiting')return;
  if(this.state!=='idle'){this.autoTimer=setTimeout(()=>this.automaticStep(),500);return;}
  const kinds=['walk','wave','bow','think','nod','look','stretch','point','listen',...(this.type==='insect'?['flip']:['celebrate'])].filter(k=>k!==this.previousAction);
  const action=kinds[Math.floor(Math.random()*kinds.length)];this.previousAction=action;this.host.dataset.automaticAction=action;this.perform(action);this.scheduleAutomatic();
 }
 scheduleAutomatic(){
  this.clearAutomatic();if(!this.automatic||this.reduced||this.destroyed||this.paused||document.hidden||this.state==='hidden'||this.state==='exiting')return;
  this.autoTimer=setTimeout(()=>this.automaticStep(),6500+Math.floor(Math.random()*7500));
 }
 open(){if(this.destroyed)return;this.finish?.(false);this.finish=null;cancelAnimationFrame(this.frame);this.state='entering';this.elapsed=0;this.last=0;this.renderLast=0;this.sample(this.state,0);if(!this.paused)this.frame=requestAnimationFrame(this.tick);this.scheduleAutomatic();}
 close(){this.clearAutomatic();if(this.destroyed||this.state==='hidden')return Promise.resolve(true);if(this.state==='exiting')return this.closing;if(document.hidden){this.state='hidden';this.sample('hidden',0);return Promise.resolve(true);}if(this.paused)this.resume();this.clearAutomatic();this.state='exiting';this.elapsed=0;this.closing=new Promise(resolve=>this.finish=resolve);return this.closing;}
 pause(){this.clearAutomatic();this.paused=true;cancelAnimationFrame(this.frame);this.frame=0;this.last=0;}
 resume(){if(this.destroyed)return;this.paused=false;this.last=0;this.renderLast=0;if(!this.frame&&this.state!=='hidden')this.frame=requestAnimationFrame(this.tick);this.scheduleAutomatic();}
 destroy(){this.pause();this.destroyed=true;this.finish?.(false);this.finish=null;document.removeEventListener('visibilitychange',this.visibility);this.host.innerHTML='';}
}

const nativeSample380=IgrisCharacter.prototype.sample;
IgrisCharacter.prototype.sample=function(...args){
 const previous=this._usedTransforms;
 nativeSample380.apply(this,args);
 if(previous) for(const name of previous) if(!this._usedTransforms.has(name)){
   const node=this.parts[name];if(node?.hasAttribute('transform'))node.removeAttribute('transform');
 }
};

global.IgrisCharacter=IgrisCharacter;
})(window);

;
/* Motor 3D local: mallas triangulares XYZ, cámara perspectiva y luz por normales.
 * WebGL ES 2 cuando está disponible; el respaldo proyecta las mismas mallas 3D.
 * No utiliza sprites, planos de imagen ni dependencias descargadas en ejecución. */
(function(root){'use strict';
const V={add:(a,b)=>a.map((v,i)=>v+b[i]),sub:(a,b)=>a.map((v,i)=>v-b[i]),mul:(a,s)=>a.map(v=>v*s),dot:(a,b)=>a.reduce((n,v,i)=>n+v*b[i],0),cross:(a,b)=>[a[1]*b[2]-a[2]*b[1],a[2]*b[0]-a[0]*b[2],a[0]*b[1]-a[1]*b[0]],norm:a=>{const n=Math.hypot(...a)||1;return a.map(v=>v/n);}};
const clamp=(v,a,b)=>Math.max(a,Math.min(b,v));
function color(s){if(Array.isArray(s))return s;if(/^#[\da-f]{6}$/i.test(s))return [1,3,5].map(i=>parseInt(s.slice(i,i+2),16)/255);if(/^#[\da-f]{3}$/i.test(s))return s.slice(1).split('').map(v=>parseInt(v+v,16)/255);const nums=(String(s).match(/[\d.]+/g)||[]).map(Number);if(/^rgb/.test(s))return nums.slice(0,3).map(v=>v/255);if(/^hsl/.test(s)){const [h,ss,ll]=nums,sat=ss/100,l=ll/100,c=(1-Math.abs(2*l-1))*sat,x=c*(1-Math.abs((h/60)%2-1)),m=l-c/2,parts=h<60?[c,x,0]:h<120?[x,c,0]:h<180?[0,c,x]:h<240?[0,x,c]:h<300?[x,0,c]:[c,0,x];return parts.map(v=>v+m);}return [.3,.85,.95];}
function rotate(p,r=[0,0,0]){let [x,y,z]=p,[a=0,b=0,c=0]=r||[],t=y*Math.cos(a)-z*Math.sin(a);z=y*Math.sin(a)+z*Math.cos(a);y=t;t=x*Math.cos(b)+z*Math.sin(b);z=-x*Math.sin(b)+z*Math.cos(b);x=t;t=x*Math.cos(c)-y*Math.sin(c);y=x*Math.sin(c)+y*Math.cos(c);return [t,y,z];}
function transform(p,nodes=[]){for(let i=nodes.length-1;i>=0;i--){const n=nodes[i];p=rotate(p.map((v,j)=>v*(Array.isArray(n.scale)?n.scale[j]:n.scale??1)),n.rotation);p=V.add(p,n.position||[0,0,0]);}return p;}
const templates=new Map();
function sphereTemplate(rows=8,cols=12){const key=rows+':'+cols;if(templates.has(key))return templates.get(key);const t=[];const point=(r,c)=>{const a=r/rows*Math.PI,b=c/cols*Math.PI*2;return [Math.sin(a)*Math.cos(b),Math.cos(a),Math.sin(a)*Math.sin(b)];};for(let r=0;r<rows;r++)for(let c=0;c<cols;c++){const a=point(r,c),b=point(r+1,c),d=point(r,c+1),e=point(r+1,c+1);if(r)t.push([a,d,b]);if(r<rows-1)t.push([b,d,e]);}templates.set(key,t);return t;}
class Scene {
 constructor(){this.triangles=[];this.nodes=[];this.opacity=1;}
 group(node,fn){this.nodes.push(node);fn(this);this.nodes.pop();return this;}
 triangle(a,b,c,tint,emission=0,smooth){const points=[a,b,c].map(p=>transform(p,this.nodes));const normals=smooth?.map(p=>{for(let i=this.nodes.length-1;i>=0;i--){const n=this.nodes[i],scale=Array.isArray(n.scale)?n.scale:[n.scale??1,n.scale??1,n.scale??1];p=rotate(p.map((v,j)=>v/scale[j]),n.rotation);}return V.norm(p);});const reflection=this.nodes.reduce((v,n)=>v*(Array.isArray(n.scale)?n.scale.reduce((a,b)=>a*b,1):Math.pow(n.scale??1,3)),1);if(reflection<0){[points[1],points[2]]=[points[2],points[1]];if(normals)[normals[1],normals[2]]=[normals[2],normals[1]];}const normal=V.norm(V.cross(V.sub(points[1],points[0]),V.sub(points[2],points[0])));if(points.every(p=>p.every(Number.isFinite)))this.triangles.push({points,normal,normals,color:color(tint),emission});return this;}
 sphere(center,size,tint,emission=0,rotation=[0,0,0],quality=0){const sizes=Array.isArray(size)?size:[size,size,size];for(const tri of sphereTemplate(quality?10:7,quality?16:10))this.triangle(...tri.map(p=>V.add(center,rotate(p.map((v,i)=>v*sizes[i]),rotation))),tint,emission);return this;}
 cylinder(a,b,r1,tint,r2=r1,emission=0,sides=10){const axis=V.norm(V.sub(b,a)),u=V.norm(V.cross(axis,Math.abs(axis[1])>.9?[1,0,0]:[0,1,0])),v=V.cross(axis,u);const ring=(p,r,k)=>V.add(p,V.add(V.mul(u,Math.cos(k/sides*2*Math.PI)*r),V.mul(v,Math.sin(k/sides*2*Math.PI)*r)));for(let i=0;i<sides;i++){const p=ring(a,r1,i),q=ring(b,r2,i),r=ring(a,r1,i+1),s=ring(b,r2,i+1);this.triangle(p,r,q,tint,emission);this.triangle(q,r,s,tint,emission);this.triangle(a,r,p,tint,emission);this.triangle(b,q,s,tint,emission);}return this;}
 limb(a,b,r,tint){this.cylinder(a,b,r,tint);this.sphere(a,r,tint);this.sphere(b,r,tint);return this;}
 box(center,size,tint,rotation=[0,0,0],emission=0){const pts=[[-1,-1,-1],[1,-1,-1],[1,1,-1],[-1,1,-1],[-1,-1,1],[1,-1,1],[1,1,1],[-1,1,1]].map(p=>V.add(center,rotate(p.map((v,i)=>v*size[i]/2),rotation)));for(const f of [[0,3,2,1],[4,5,6,7],[0,1,5,4],[3,7,6,2],[0,4,7,3],[1,2,6,5]]){this.triangle(pts[f[0]],pts[f[1]],pts[f[2]],tint,emission);this.triangle(pts[f[0]],pts[f[2]],pts[f[3]],tint,emission);}return this;}
 prism(points,depth,tint,emission=0){const area=points.reduce((n,p,i)=>{const q=points[(i+1)%points.length];return n+p[0]*q[1]-q[0]*p[1];},0);if(area<0)points=[...points].reverse();const a=points.map(p=>[p[0],p[1],p[2]-depth/2]),b=points.map(p=>[p[0],p[1],p[2]+depth/2]);for(let i=1;i<points.length-1;i++){this.triangle(a[0],a[i+1],a[i],tint,emission);this.triangle(b[0],b[i],b[i+1],tint,emission);}for(let i=0;i<points.length;i++){const j=(i+1)%points.length;this.triangle(a[i],a[j],b[i],tint,emission);this.triangle(b[i],a[j],b[j],tint,emission);}return this;}
}
function camera({center=[0,1,0],distance=6,yaw=.55,pitch=.12,fov=38,aspect=1,near=.05,far=60}={}){const eye=V.add(center,[Math.sin(yaw)*Math.cos(pitch)*distance,Math.sin(pitch)*distance,Math.cos(yaw)*Math.cos(pitch)*distance]),forward=V.norm(V.sub(center,eye)),right=V.norm(V.cross(forward,[0,1,0])),up=V.cross(right,forward);return {center,eye,forward,right,up,focal:1/Math.tan(fov*Math.PI/360),aspect,near,far,yaw,pitch};}
function project(p,c,width,height){const v=V.sub(p,c.eye),z=V.dot(v,c.forward);return [(V.dot(v,c.right)*c.focal/(c.aspect*z)+1)*width/2,(1-V.dot(v,c.up)*c.focal/z)*height/2,z];}
const light=V.norm([-.4,.8,.6]);
function lighting(n,emission=0){return Math.min(1.35,.28+.67*Math.max(0,V.dot(n,light))+.16*Math.max(0,n[2])+emission);}
const vertexShader='attribute vec3 aPosition;attribute vec3 aNormal;attribute vec3 aColor;attribute float aEmission;uniform mediump vec3 uEye;uniform vec3 uRight;uniform vec3 uUp;uniform vec3 uForward;uniform float uFocal;uniform float uAspect;uniform vec2 uDepth;varying vec3 vColor;varying vec3 vNormal;varying vec3 vPosition;varying float vEmission;void main(){vec3 v=aPosition-uEye;float d=dot(v,uForward);gl_Position=vec4(dot(v,uRight)*uFocal/uAspect,dot(v,uUp)*uFocal,uDepth.x*d+uDepth.y,d);vColor=aColor;vNormal=aNormal;vPosition=aPosition;vEmission=aEmission;}';
const fragmentShader='precision mediump float;varying vec3 vColor;varying vec3 vNormal;varying vec3 vPosition;varying float vEmission;uniform float uOpacity;uniform mediump vec3 uEye;void main(){vec3 n=normalize(vNormal);vec3 key=normalize(vec3(.6,.85,.55));vec3 view=normalize(uEye-vPosition);float diffuse=.21+.66*max(0.0,dot(n,key))+.15*max(0.0,dot(n,normalize(vec3(-.65,.3,-.5))));float shine=.23*pow(max(0.0,dot(n,normalize(key+view))),32.0);float rim=.07*pow(1.0-max(0.0,dot(n,view)),3.0);gl_FragColor=vec4(vColor*(diffuse+vEmission)+vec3(shine+rim),uOpacity);}';
const renderers=new Map();
function shadeCSS(c,f){return 'rgb('+c.map(v=>Math.round(clamp(v*f,0,1)*255)).join(',')+')';}
function software(ctx,scene,c,width,height,bg,transparent=false){ctx.clearRect(0,0,width,height);if(!transparent){ctx.fillStyle=bg;ctx.fillRect(0,0,width,height);}const rows=scene.triangles.filter(t=>V.dot(t.normal,V.sub(c.eye,t.points[0]))>=0).map(t=>({t,pts:t.points.map(p=>project(p,c,width,height))})).filter(x=>x.pts.every(p=>p[2]>c.near)).sort((a,b)=>b.pts.reduce((n,p)=>n+p[2],0)-a.pts.reduce((n,p)=>n+p[2],0));ctx.globalAlpha=scene.opacity;for(const {t,pts} of rows){ctx.fillStyle=shadeCSS(t.color,lighting(t.normals?V.norm(t.normals.reduce((a,n)=>V.add(a,n),[0,0,0])):t.normal,t.emission));ctx.beginPath();ctx.moveTo(pts[0][0],pts[0][1]);ctx.lineTo(pts[1][0],pts[1][1]);ctx.lineTo(pts[2][0],pts[2][1]);ctx.closePath();ctx.fill();}ctx.globalAlpha=1;return rows.length;}
class Renderer {
 constructor(canvas,softwareOnly=false){this.canvas=canvas;this.gl=null;this.error='';if(!softwareOnly)try{const gl=canvas.getContext('webgl',{alpha:true,antialias:true,depth:true,preserveDrawingBuffer:false});if(gl&&typeof gl.getParameter==='function'&&gl.getParameter(gl.VERSION)){this.gl=gl;this.init();}}catch(e){this.error=String(e.message||e);this.gl=null;}if(!this.gl)this.ctx=canvas.getContext('2d');}
 init(){const gl=this.gl,shader=(kind,src)=>{const s=gl.createShader(kind);gl.shaderSource(s,src);gl.compileShader(s);if(!gl.getShaderParameter(s,gl.COMPILE_STATUS))throw Error(gl.getShaderInfoLog(s));return s;};const vs=shader(gl.VERTEX_SHADER,vertexShader),fs=shader(gl.FRAGMENT_SHADER,fragmentShader);this.program=gl.createProgram();gl.attachShader(this.program,vs);gl.attachShader(this.program,fs);gl.linkProgram(this.program);gl.deleteShader(vs);gl.deleteShader(fs);if(!gl.getProgramParameter(this.program,gl.LINK_STATUS))throw Error(gl.getProgramInfoLog(this.program));this.buffer=gl.createBuffer();this.attributes=['aPosition','aNormal','aColor','aEmission'].map(n=>gl.getAttribLocation(this.program,n));this.uniforms=Object.fromEntries(['uEye','uRight','uUp','uForward','uFocal','uAspect','uDepth','uOpacity'].map(n=>[n,gl.getUniformLocation(this.program,n)]));gl.enable(gl.DEPTH_TEST);gl.enable(gl.BLEND);gl.blendFunc(gl.SRC_ALPHA,gl.ONE_MINUS_SRC_ALPHA);this.lost=e=>{e.preventDefault();this.contextLost=true;};this.restored=()=>{this.contextLost=false;this.init();this.lastScene&&this.draw(...this.lastScene);};this.canvas.removeEventListener?.('webglcontextlost',this.lostPrevious);this.canvas.removeEventListener?.('webglcontextrestored',this.restoredPrevious);this.lostPrevious=this.lost;this.restoredPrevious=this.restored;this.canvas.addEventListener?.('webglcontextlost',this.lost);this.canvas.addEventListener?.('webglcontextrestored',this.restored);}
 draw(scene,options={}){const canvas=this.canvas,width=canvas.width||720,height=canvas.height||800,c=camera({...options,aspect:width/height}),bg=options.background||'#142437',gl=this.gl;this.lastScene=[scene,options];if(this.contextLost)return false;if(gl){gl.viewport(0,0,width,height);const b=color(bg);gl.clearColor(...b,options.transparent?0:1);gl.clear(gl.COLOR_BUFFER_BIT|gl.DEPTH_BUFFER_BIT);gl.useProgram(this.program);const data=new Float32Array(scene.triangles.length*30);let i=0;for(const t of scene.triangles)for(let j=0;j<3;j++){for(const v of [...t.points[j],...(t.normals?.[j]||t.normal),...t.color,t.emission])data[i++]=v;}gl.bindBuffer(gl.ARRAY_BUFFER,this.buffer);gl.bufferData(gl.ARRAY_BUFFER,data,gl.DYNAMIC_DRAW);for(let n=0;n<4;n++){gl.enableVertexAttribArray(this.attributes[n]);gl.vertexAttribPointer(this.attributes[n],n===3?1:3,gl.FLOAT,false,40,n*12);}for(const name of ['Eye','Right','Up','Forward'])gl.uniform3fv(this.uniforms['u'+name],c[name.toLowerCase()]);gl.uniform1f(this.uniforms.uFocal,c.focal);gl.uniform1f(this.uniforms.uAspect,c.aspect);gl.uniform2f(this.uniforms.uDepth,(c.far+c.near)/(c.far-c.near),-2*c.far*c.near/(c.far-c.near));gl.uniform1f(this.uniforms.uOpacity,scene.opacity);gl.drawArrays(gl.TRIANGLES,0,data.length/10);}else if(this.ctx)software(this.ctx,scene,c,width,height,bg,options.transparent);else return false;canvas.dataset.renderer='3d';canvas.dataset.backend=gl?'webgl':'software-3d';canvas.dataset.triangles=String(scene.triangles.length);return true;}
 destroy(){if(this.gl){this.gl.deleteBuffer(this.buffer);this.gl.deleteProgram(this.program);this.canvas.removeEventListener?.('webglcontextlost',this.lost);this.canvas.removeEventListener?.('webglcontextrestored',this.restored);if(typeof this.gl.getExtension==='function')this.gl.getExtension('WEBGL_lose_context')?.loseContext();}this.lastScene=null;renderers.delete(this.canvas);}
}
function render(canvas,scene,opts={}){let r=renderers.get(canvas);if(!r){r=new Renderer(canvas,opts.softwareOnly);renderers.set(canvas,r);}r.draw(scene,opts);return r;}
function release(canvas){renderers.get(canvas)?.destroy();}
function releaseDetached(){for(const [canvas,r] of renderers)if(canvas.isConnected===false)r.destroy();}
root.Mesh3D={V,Scene,camera,project,rotate,transform,color,software,render,release,releaseDetached,Renderer,vertexShader,fragmentShader,renderers,lighting};
})(window);

;
/* Rig volumétrico de ejercicios. Profundidad y proporciones dibujadas por código;
 * no son captura de movimiento ni reconstrucción anatómica validada. */
(function(root){'use strict';
const M=Mesh3D,V=M.V,cache=new Map();
const accent=()=>getComputedStyle(document.documentElement).getPropertyValue('--accent').trim()||'#57d8f2';
function bounds(id){const e=Engine.byId(id),key=e.baseId||id;if(cache.has(key))return cache.get(key);const m=MotionDB[key],all=m.frames.flatMap(f=>[...f.p,...f.loads,...f.equipment.flatMap(x=>[[x[1][0],x[1][1]],[x[1][2],x[1][3]]])]);const xs=all.map(p=>p[0]),ys=all.map(p=>p[1]),min=Math.min(...xs)-48,max=Math.max(...xs)+48,ground=Math.max(...ys)+35,top=Math.min(...ys)-45;const result={cx:(min+max)/2,ground,center:[0,(ground-top)/400,0],radius:Math.max((max-min)/400,(ground-top)/400,.8)+.26};cache.set(key,result);return result;}
function pose(id,phase){const e=Engine.byId(id);if(!e)throw Error('Ejercicio desconocido: '+id);const model=MotionDB[e.baseId||id];if(!model)throw Error('Falta modelo: '+id);const f=interpolateMotion(model,phase),b=bounds(id),map=(p,z=0)=>[(p[0]-b.cx)/200,(b.ground-p[1])/200,z],p=f.p.map(x=>map(x));
 // Separación izquierda/derecha y recorridos en profundidad del rig.
 const armWidth=e.seated?.19:.18,stance=/sumo|wide/.test(e.id)?.22:.13,amount=f.detail.amount??(1-Math.cos(phase/48*Math.PI*2))/2;
 for(const [j,z] of [[3,armWidth],[4,armWidth],[5,-armWidth],[6,-armWidth],[7,stance],[8,stance],[9,-stance],[10,-stance]])p[j][2]=z;
 if(/circle|armworld|shoulder/.test(e.id)){const q=phase/48*Math.PI*2;p[4][2]+=.12*Math.sin(q);p[6][2]-=.12*Math.sin(q);}
 if(/fly|lateral|armsback/.test(e.id)){p[4][2]+=.16*amount;p[6][2]-=.16*amount;}
 if(/turn|thoracic/.test(e.id)){const a=Math.sin(phase/48*Math.PI*2)*.22;p[3][2]+=a;p[4][2]+=a;p[5][2]-=a;p[6][2]-=a;}
 if(/skater|shuffle|stepcross|linehop/.test(e.id)){p[8][2]+=.06*Math.sin(phase/48*Math.PI*2);p[10][2]-=.06*Math.sin(phase/48*Math.PI*2);}
 return {id,e,model,f,p,b,map,authoredDepth:true};
}
function scene(id,phase=0,mirror=false){const data=pose(id,phase),{e,f,p,b,map}=data,s=new M.Scene(),cyan=accent(),dark='#18283e',joint='#96afc8';
 s.group({scale:mirror?[-1,1,1]:1},()=>{
  s.box([0,-.025,0],[b.radius*2.5,.045,1.4],'#1b3146');
  for(let i=-3;i<=3;i++){s.box([i*.28,0,0],[.008,.004,1.4],'#375168');s.box([0,0,i*.22],[b.radius*2.5,.004,.008],'#375168');}
  s.sphere([(p[8][0]+p[10][0])/2,.015,0],[.5,.01,.33],'#0a101c');
  for(const [type,rect] of f.equipment){const [x,y,r,d]=rect,a=map([x,y]),c=map([r,d]);const center=[(a[0]+c[0])/2,(a[1]+c[1])/2,-.04];s.box(center,[Math.max(.06,c[0]-a[0]),Math.max(.035,a[1]-c[1]),.45],'#344962');if(type==='incline')s.cylinder(map([x,d-30],0),map([r,y],0),.045,'#7996af');}
  // Pelvis, tronco y extremidades son sólidos; se articulan en cada pose.
  for(const [a,c,z] of [[1,5,-.18],[5,6,0],[2,9,-.13],[9,10,0]])s.limb(a===1?[p[a][0],p[a][1],z]:a===2?[p[a][0],p[a][1],z]:p[a],p[c],.06,dark);
  const mid=V.mul(V.add(p[1],p[2]),.5);
  if(f.detail.spine!==undefined){const control=V.add(mid,[0,-f.detail.spine/200,0]);let last=p[1];for(let j=1;j<=8;j++){const a=j/8,next=p[1].map((v,k)=>(1-a)**2*v+2*(1-a)*a*control[k]+a*a*p[2][k]);s.limb(last,next,.105,cyan);last=next;}}
  else {s.limb(p[1],p[2],.12,dark);s.sphere(mid,[.125,Math.max(.16,Math.hypot(...V.sub(p[1],p[2]))*.44),.185],cyan,0,[0,0,Math.atan2(p[2][0]-p[1][0],p[1][1]-p[2][1])]);}
  s.sphere(p[2],[.13,.095,.175],dark);
  for(const [a,c,z] of [[0,1,0],[1,3,.18],[3,4,0],[2,7,.13],[7,8,0]])s.limb(a===1?[p[a][0],p[a][1],z]:a===2?[p[a][0],p[a][1],z]:p[a],p[c],a===0?.055:.065,cyan);
  for(const j of [3,5,7,9])s.sphere(p[j],.072,joint);
  s.group({position:p[0],rotation:[0,f.detail.face===undefined?0:(f.detail.face-10)/25,-.08]},()=>{s.sphere([0,0,0],[.145,.18,.13],'#e2eef5');s.sphere([.12,-.015,0],[.052,.035,.045],'#c0d6e2');for(const z of [-.075,.075])s.sphere([.118,.05,z],[.015,.021,.018],dark);});
  for(const j of [8,10]){const angle=f.detail.feet?((f.detail.toeAmount??f.detail.amount??.5)-.5)*.85:0;s.group({position:p[j],rotation:[0,0,angle]},()=>s.box([.055,-.02,0],[.21,.085,.11],dark));}
  f.loads.forEach((xy,i)=>{const hand=i===0?4:6,q=map(xy,p[hand][2]);s.cylinder(V.add(q,[0,0,-.09]),V.add(q,[0,0,.09]),.018,'#c2cedc');for(const z of [-.11,.11])s.cylinder(V.add(q,[0,0,z-.025]),V.add(q,[0,0,z+.025]),.07,'#e9c96e');});
  if(f.detail.towel)s.cylinder(map(f.detail.towel[0],.18),map(f.detail.towel[1],-.18),.018,'#e9c96e');
  if(f.detail.breath!==undefined)s.sphere(V.add(mid,[.03,0,.2]),.025+.025*f.detail.breath,cyan,.6);
  if(f.detail.detail){const q=f.detail.amount||0,hand=p[4],rotation=f.detail.detail==='forearmmob'?[(q-.5)*Math.PI,0,0]:f.detail.detail==='fingers'?[0,0,0]:[0,0,(q-.5)*.9];s.group({position:hand,rotation,scale:1.6},()=>{s.sphere([.025,0,0],[.048,.018,.050],cyan);for(let j=0;j<5;j++){const a=[.045,0,(j-2)*.018],curl=f.detail.detail==='fingers'?(1-q)*.06:0,c=V.add(a,[.05,-curl,0]);s.cylinder(a,c,.007,cyan);s.cylinder(c,V.add(c,[.025-curl/3,-curl/2,0]),.006,cyan);}});}
 });
 return {scene:s,...data};
}
function options(id,yaw){const b=bounds(id);return {center:b.center,distance:b.radius/Math.sin(19*Math.PI/180)*1.24,yaw:Number.isFinite(yaw)?yaw:.45,pitch:.10,background:getComputedStyle(document.documentElement).getPropertyValue('--input').trim()||'#142437'};}
function draw(canvas,id,phase=0,mirror=false){if(!canvas)return;const result=scene(id,phase,mirror),yaw=Number(canvas.dataset.yaw);M.render(canvas,result.scene,{...options(id,Number.isFinite(yaw)&&canvas.dataset.yaw!==undefined?yaw:undefined),softwareOnly:canvas.dataset.thumbnail==='true'});canvas.dataset.exercise=id;return result.model;}
root.Exercise3D={pose,scene,bounds,options,draw};
})(window);

