/* Volumen ilustrado 2.5D: conserva las trayectorias 2D del catálogo.
 * La profundidad es un recurso gráfico, no una medición anatómica 3D. */
(function(root){
'use strict';
const rates=[{value:.5,name:'Lenta',label:'0,5×'},{value:1,name:'Normal',label:'1×'},{value:1.5,name:'Rápida',label:'1,5×'}];
let playback=1;
function setRate(value){value=Number(value);if(!rates.some(r=>r.value===value))return false;playback=value;
 if(typeof state!=='undefined'){state.demoSpeed=value;save();}
 if(typeof vectorSession!=='undefined'&&vectorSession)vectorSession.speed=value;
 if(typeof guide11!=='undefined'&&guide11)guide11.demoLast=0;
 document.querySelectorAll('[data-playback-speed]').forEach(b=>b.setAttribute('aria-pressed',String(Number(b.dataset.playbackSpeed)===value)));
 return true;
}
function controls(context=''){return `<fieldset class="playback-speeds"><legend>Velocidad de la demostración${context?' · '+context:''}</legend><div class="speed-options">${rates.map(r=>`<button type="button" data-playback-speed="${r.value}" aria-pressed="${playback===r.value}" onclick="MotionPlayback.set(${r.value})"><b>${r.name}</b><small>${r.label}</small></button>`).join('')}</div></fieldset>`;}
root.MotionPlayback={rates,get rate(){return playback;},set:setRate,controls};
const clamp=(v,a,b)=>Math.max(a,Math.min(b,v));
function shade(color,factor){let rgb=/^#([a-f\d]{6})$/i.exec(color||'');let c=rgb?[0,2,4].map(i=>parseInt(rgb[1].slice(i,i+2),16)):(String(color).match(/[\d.]+/g)||[87,216,242]).slice(0,3).map(Number);return '#'+c.map(v=>Math.round(clamp(v*factor,0,255)).toString(16).padStart(2,'0')).join('');}
function geometry(id,phase){const e=Engine.byId(id),model=MotionDB[e?.baseId||id];if(!model)throw Error('Sin modelo de movimiento: '+id);const f=interpolateMotion(model,((Number(phase)||0)%48+48)%48);return {e,model,f,ground:Math.max(...modelGround(id))};}
function draw(canvas,id,phase=0,mirror=false){if(!canvas)return;const ctx=canvas.getContext('2d');if(!ctx)return;const {model,f,ground}=geometry(id,phase),p=f.p,d=f.detail,style=getComputedStyle(document.documentElement),accent=style.getPropertyValue('--accent').trim()||'#57d8f2',bg=style.getPropertyValue('--input').trim()||'#152538';
 const line=(a,b,w,color)=>{ctx.beginPath();ctx.strokeStyle=color;ctx.lineWidth=w;ctx.lineCap='round';ctx.moveTo(...a);ctx.lineTo(...b);ctx.stroke();};
 const sphere=(a,r,color)=>{const g=ctx.createRadialGradient(a[0]-r*.3,a[1]-r*.35,r*.1,a[0],a[1],r);g.addColorStop(0,shade(color,1.35));g.addColorStop(.45,color);g.addColorStop(1,shade(color,.36));ctx.beginPath();ctx.fillStyle=g;ctx.arc(...a,r,0,Math.PI*2);ctx.fill();};
 const capsule=(a,b,w,color)=>{const dx=b[0]-a[0],dy=b[1]-a[1],length=Math.hypot(dx,dy)||1,nx=-dy/length,ny=dx/length,mx=(a[0]+b[0])/2,my=(a[1]+b[1])/2,g=ctx.createLinearGradient(mx-nx*w/2,my-ny*w/2,mx+nx*w/2,my+ny*w/2);g.addColorStop(0,shade(color,.3));g.addColorStop(.32,shade(color,1.3));g.addColorStop(.6,color);g.addColorStop(1,shade(color,.4));line(a,b,w+3,'#101923');line(a,b,w,g);};
 ctx.clearRect(0,0,720,800);ctx.fillStyle=bg;ctx.fillRect(0,0,720,800);
 const haze=ctx.createRadialGradient(350,360,20,350,360,410);haze.addColorStop(0,shade(accent,.2));haze.addColorStop(1,bg);ctx.fillStyle=haze;ctx.fillRect(0,0,720,800);
 ctx.save();if(mirror){ctx.translate(720,0);ctx.scale(-1,1);}
 // Plano y sombra de apoyo. No cambia la posición de manos/pies en el modelo.
 ctx.globalAlpha=.18;for(let i=0;i<8;i++)line([40+i*90,ground+40],[300+i*20,ground-35],1,'#b2cbda');for(let i=0;i<4;i++)line([40,ground+i*20],[680,ground+i*20],1,'#b2cbda');ctx.globalAlpha=1;
 const sx=(p[8][0]+p[10][0]+p[2][0])/3;ctx.fillStyle='#03070b';ctx.globalAlpha=.5;ctx.beginPath();ctx.ellipse(sx,ground+8,120,17,0,0,Math.PI*2);ctx.fill();ctx.globalAlpha=1;
 for(const [type,rect] of f.equipment){const [x,y,r,b]=rect;ctx.fillStyle='#243747';ctx.fillRect(x+10,y+8,r-x,b-y);ctx.strokeStyle='#a4bac8';ctx.lineWidth=4;ctx.strokeRect(x,y,r-x,b-y);line([x,y],[x+10,y+8],3,'#526b80');line([r,b],[r+10,b+8],3,'#526b80');if(type==='incline')capsule([x,b-30],[r,y],10,'#8aa3b7');}
 for(const [a,b] of [[1,5],[5,6],[2,9],[9,10]])capsule(p[a],p[b],21,shade(accent,.65));
 if(d.spine){ctx.beginPath();ctx.strokeStyle=shade(accent,.45);ctx.lineWidth=36;ctx.lineCap='round';ctx.moveTo(...p[1]);ctx.quadraticCurveTo((p[1][0]+p[2][0])/2,(p[1][1]+p[2][1])/2+d.spine,...p[2]);ctx.stroke();ctx.lineWidth=20;ctx.strokeStyle=accent;ctx.stroke();}else capsule(p[1],p[2],38,accent);
 for(const [a,b] of [[0,1],[1,3],[3,4],[2,7],[7,8]])capsule(p[a],p[b],24,accent);
 for(const i of [1,2,3,5,7,9])sphere(p[i],10,'#9badc0');sphere(p[0],32,'#e1eaf2');sphere([p[0][0]+(d.face||10),p[0][1]-3],4,'#172438');
 for(const i of [8,10]){let q=d.toeAmount??d.amount??0;capsule(p[i],[p[i][0]+28,p[i][1]-(d.feet==='toelift'||d.feet==='heeltoe'?25*q:d.feet==='ankle'?35*(q-.5):0)],14,accent);}
 for(const [x,y] of f.loads){capsule([x-18,y],[x+18,y],8,'#b8c6d6');capsule([x-22,y-14],[x-22,y+14],16,'#e7c76c');capsule([x+22,y-14],[x+22,y+14],16,'#e7c76c');}
 if(d.towel)line(d.towel[0],d.towel[1],5,'#e7c76c');
 if(d.breath!==undefined){ctx.strokeStyle=accent;ctx.lineWidth=3;ctx.beginPath();ctx.arc(p[1][0],p[1][1]+50,35+15*d.breath,0,Math.PI*2);ctx.stroke();}
 ctx.restore();
 if(d.detail){const q=d.amount||0;ctx.fillStyle='#152332';ctx.fillRect(410,45,280,170);ctx.fillStyle='#eff6ff';ctx.font='18px sans-serif';ctx.fillText('Detalle de mano',428,74);capsule([440,162],[525,150],16,accent);if(d.detail==='forearmmob'){ctx.beginPath();ctx.fillStyle=q>.5?'#e9c49f':accent;ctx.ellipse(560,150,30,Math.max(3,18*Math.abs(Math.cos(q*Math.PI))),0,0,Math.PI*2);ctx.fill();}else if(d.detail==='fingers'){for(let j=0;j<5;j++)capsule([525,150],[550+35*q,150+(j-2)*15*q],7,accent);}else capsule([525,150],[590,150+50*(q-.5)],14,accent);}
 if(d.grip){ctx.fillStyle='#edf4ff';ctx.font='19px sans-serif';ctx.fillText(d.grip,30,30);}
 canvas.dataset.renderer='2.5d';return model;
}
root.DepthMotion={draw,geometry,shade};
})(window);
