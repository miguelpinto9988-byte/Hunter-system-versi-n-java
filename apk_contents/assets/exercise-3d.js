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
