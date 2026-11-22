/* Recreación geométrica del estilo visual del clip de referencia.
 * Figura gris, pantalón negro, músculos resaltados. No es el modelo del video
 * ni una reconstrucción anatómica clínicamente validada. */
(function(root){'use strict';
const M=Mesh3D,V=M.V,old=Exercise3D,skin='#d8d9d7',focus='#f5cc68',dark='#18191c',templates=new Map(),cache=new Map();
function emit(s,vertices,normals,faces,tint){const points=vertices.map(p=>M.transform(p,s.nodes)),ns=normals.map(p=>{for(let i=s.nodes.length-1;i>=0;i--){const n=s.nodes[i],scale=Array.isArray(n.scale)?n.scale:[n.scale??1,n.scale??1,n.scale??1];p=M.rotate(p.map((v,j)=>v/scale[j]),n.rotation);}return V.norm(p);}),rgb=typeof tint==='function'?null:M.color(tint),reflected=s.nodes.reduce((v,n)=>v*(Array.isArray(n.scale)?n.scale.reduce((a,b)=>a*b,1):Math.pow(n.scale??1,3)),1)<0;for(const face of faces){const ids=reflected?[face[0],face[2],face[1]]:face,pts=ids.map(i=>points[i]),nn=ids.map(i=>ns[i]);s.triangles.push({points:pts,normals:nn,normal:V.norm(V.cross(V.sub(pts[1],pts[0]),V.sub(pts[2],pts[0]))),color:rgb||M.color(tint(face)),emission:0});}}
function ellipsoid(s,c,size,tint,rotation=[0,0,0],quality=1){const rows=quality?10:7,cols=quality?16:10,key=rows+':'+cols,rot=typeof rotation==='function'?rotation:p=>M.rotate(p,rotation);let mesh=templates.get(key);if(!mesh){const vertices=[],faces=[];for(let r=0;r<=rows;r++)for(let k=0;k<=cols;k++){const a=r/rows*Math.PI,b=k/cols*Math.PI*2;vertices.push([Math.sin(a)*Math.cos(b),Math.cos(a),Math.sin(a)*Math.sin(b)]);}for(let r=0;r<rows;r++)for(let k=0;k<cols;k++){const a=r*(cols+1)+k,b=(r+1)*(cols+1)+k,d=a+1,e=b+1;if(r)faces.push([a,d,b]);if(r<rows-1)faces.push([b,d,e]);}mesh={vertices,faces};templates.set(key,mesh);}const vertices=mesh.vertices.map(p=>V.add(c,rot(p.map((v,i)=>v*size[i])))),normals=mesh.vertices.map(p=>V.norm(rot(p.map((v,i)=>v/size[i]))));emit(s,vertices,normals,mesh.faces,tint);}

function bone(s,a,b,width,depth,tint,offset=[0,0,0],quality=1){const d=V.sub(b,a),len=Math.max(.0001,Math.hypot(...d)),axis=V.norm(d),u=V.norm(V.cross(axis,Math.abs(axis[2])>.9?[1,0,0]:[0,0,1])),v=V.cross(u,axis),rows=quality?14:7,cols=quality?20:10,vertices=[],normals=[],faces=[];
 // Sección continua: hombro/muslo amplios, transición suave y extremo distal fino.
 const radius=t=>.72+.30*Math.sin(Math.PI*t)-.19*t,derivative=t=>.30*Math.PI*Math.cos(Math.PI*t)-.19;
 for(let r=0;r<=rows;r++){const t=r/rows,q=radius(t),slope=derivative(t);for(let k=0;k<=cols;k++){const angle=k/cols*Math.PI*2,c=Math.cos(angle),z=Math.sin(angle);vertices.push(V.add(V.add(a,V.mul(axis,t*len)),V.add(offset,V.add(V.mul(u,c*width*q),V.mul(v,z*depth*q)))));normals.push(V.norm(V.add(V.add(V.mul(u,c/width),V.mul(v,z/depth)),V.mul(axis,-slope/len))));}}
 for(let r=0;r<rows;r++)for(let k=0;k<cols;k++){const a=r*(cols+1)+k,b=(r+1)*(cols+1)+k,c=a+1,d=b+1;faces.push([a,b,c],[b,d,c]);}
 for(const [r,sign] of [[0,-1],[rows,1]]){const n=vertices.length;vertices.push(V.add(V.add(a,V.mul(axis,r/rows*len)),offset));normals.push(V.mul(axis,sign));for(let k=0;k<cols;k++){const x=r*(cols+1)+k;faces.push(sign===1?[n,x+1,x]:[n,x,x+1]);}}
 emit(s,vertices,normals,faces,tint);
}
function targets(e){const text=(e.name+' '+e.group+' '+(e.muscles||'')).toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g,'');const ids=e.baseId||e.id,out=new Set();if(/curl|biceps/.test(ids+' '+text))out.add('biceps');if(/tricep|kickback/.test(ids+' '+text))out.add('triceps');if(/hombro|deltoid|lateral|frontlift|overhead|presscombo/.test(ids+' '+text))out.add('shoulder');if(/pecho|pectoral|flexiones|press de pecho/.test(text))out.add('chest');if(/espalda|remo|dorsal/.test(text))out.add('back');if(/abdomen|abdominal|zona media|planch|core/.test(text))out.add('core');if(/glute|sentadilla|puente de cadera|zancada/.test(text))out.add('hip');if(/cuadricep|muslo|sentadilla|zancada|pierna/.test(text))out.add('thigh');if(/pantorr|talon|tobillo|salto/.test(text))out.add('calf');return out;}
function scene(id,phase=0,mirror=false,thumbnail=false){const data=old.pose(id,phase),{e,f,b,map}=data,p=data.p.map(q=>[...q]),s=new M.Scene(),active=targets(e),color=k=>active.has(k)?focus:skin,quality=thumbnail?0:1;
 const ell=(c,size,tint,rotation)=>ellipsoid(s,c,size,tint,rotation,quality),seg=(a,c,w,d,tint,offset)=>bone(s,a,c,w,d,tint,offset,quality);
 s.group({scale:mirror?[-1,1,1]:1},()=>{
  s.box([0,-.035,0],[b.radius*2.4,.04,1.50],'#242529');ell([(p[8][0]+p[10][0])/2,.003,0],[.52,.014,.36],'#111214');
  for(const [kind,rect] of f.equipment){const a=map([rect[0],rect[1]]),d=map([rect[2],rect[3]]),w=Math.max(.12,d[0]-a[0]),h=Math.max(.08,a[1]-d[1]),center=[(a[0]+d[0])/2,a[1],0];s.box(center,[w,.075,.48],'#161719');for(const side of [-1,1])for(const end of [-1,1])s.cylinder(V.add(center,[end*w*.38,-.045,side*.18]),V.add(center,[end*w*.38,-h,side*.18]),.018,'#5c5d61');if(kind==='incline')s.cylinder(map([rect[0],rect[3]-30],0),map([rect[2],rect[1]],0),.065,'#17181a');}
  const up=V.sub(p[1],p[2]),height=Math.hypot(...up),angle=-Math.atan2(up[0],up[1]);
  s.group({position:p[2],rotation:[0,0,angle]},()=>{
   const surface=AnatomySurface15,vertices=surface.vertices.map(p=>[p[0],p[1]*height-(f.detail.spine||0)/200*Math.sin(p[1]*Math.PI),p[2]*(1+(f.detail.breath||0)*.025)]),normals=surface.normals.map(p=>V.norm([p[0],p[1]/Math.max(.1,height),p[2]]));emit(s,vertices,normals,surface.faces,face=>{const r=surface.regions[face[0]];return color(r);});
   ell([0,.035,0],[.155,.145,.225],dark);
  });
  for(const side of [1,-1]){const elbow=p[side===1?3:5],hand=p[side===1?4:6],shoulder=[p[1][0],p[1][1]-.025,side*.265];elbow[2]+=side*.055;hand[2]+=side*.055;
   seg(shoulder,elbow,.093,.098,active.has('biceps')?color('biceps'):active.has('triceps')?color('triceps'):skin);ell(elbow,[.064,.070,.068],skin);seg(elbow,hand,.069,.074,skin);
   const hAngle=-Math.atan2(hand[0]-elbow[0],hand[1]-elbow[1]),amount=f.detail.amount??.5,loaded=f.loads.length>0,curl=f.detail.detail==='fingers'?(1-amount)*1.2:loaded?.90:.08;
   s.group({position:hand,rotation:[f.detail.detail==='forearmmob'?(amount-.5)*Math.PI:0,0,hAngle+(f.detail.detail==='wristmob'?(amount-.5)*.9:0)]},()=>{ell([0,.011,0],[.042,.067,.049],skin);if(!thumbnail){for(let finger=0;finger<4;finger++){const z=(finger-1.5)*.021,a=[0,.066,z],c=[Math.sin(curl)*.032,.098,z],d=[c[0]+Math.sin(curl*1.7)*.026,c[1]+Math.cos(curl*1.7)*.026,z];seg(a,c,.010,.009,skin);seg(c,d,.008,.008,skin);}seg([.002,.026,side*.042],[.027,.062,side*.054],.014,.012,skin);}});
   const knee=p[side===1?7:9],foot=p[side===1?8:10],hip=[p[2][0],p[2][1]-.025,side*.15];seg(hip,knee,.116,.122,color('thigh'));ell(knee,[.073,.080,.079],skin);seg(knee,foot,.072,.078,color('calf'));
   seg(hip,V.add(hip,V.mul(V.sub(knee,hip),.32)),.130,.144,dark);
   const footAngle=f.detail.feet?((f.detail.toeAmount??amount)-.5)*.8:0;s.group({position:foot,rotation:[0,0,footAngle]},()=>{ell([.054,-.014,0],[.127,.049,.073],skin);if(!thumbnail)for(let toe=0;toe<5;toe++)ell([.153-(toe*.007),-.015,(toe-2)*.021],[.030,.024,.014],skin);});
  }
  // Cuello, trapecios y cabeza sin rasgos, como referencia visual.
  const neckTop=V.add(p[0],[0,-.13,0]);seg(p[1],neckTop,.056,.062,skin);for(const side of [-1,1])seg(V.add(p[1],[0,.025,side*.22]),V.add(neckTop,[-.005,-.08,side*.035]),.055,.062,skin);
  s.group({position:p[0],rotation:[0,(f.detail.face??10)/40-.25,-.055]},()=>{ell([0,.005,0],[.135,.188,.140],skin);ell([.03,-.10,0],[.116,.077,.101],skin);});
  f.loads.forEach((xy,i)=>{const hand=p[i===0?4:6],q=map(xy,hand[2]);s.cylinder(V.add(q,[0,0,-.10]),V.add(q,[0,0,.10]),.024,'#acadb0',.024,0,16);for(const side of [-1,1])for(let plate=0;plate<3;plate++){const z=side*(.09+plate*.030);s.cylinder(V.add(q,[0,0,z-.014]),V.add(q,[0,0,z+.014]),.084-plate*.012,'#3b3c40',.084-plate*.012,0,16);}for(const side of [-1,1])ell(V.add(q,[0,0,side*.178]),[.027,.027,.012],'#c2c3c6');});
  if(f.detail.towel)s.cylinder(map(f.detail.towel[0],.21),map(f.detail.towel[1],-.21),.018,'#dfdfdb');
 });
 return {scene:s,...data,style:'anatomical-reference-recreation',highlighted:[...active],validatedAnatomy:false};
}
function options(id,yaw){const b=old.bounds(id);return {center:b.center,distance:b.radius/Math.sin(19*Math.PI/180)*1.10,yaw:Number.isFinite(yaw)?yaw:1.12,pitch:.07,background:'#252527'};}
function draw(canvas,id,phase=0,mirror=false){if(!canvas)return;const thumb=canvas.dataset.thumbnail==='true',q=Math.round(phase*4)/4,key=id+':'+q+':'+mirror+':'+thumb;let result=cache.get(key);if(!result){result=scene(id,q,mirror,thumb);cache.set(key,result);if(cache.size>4)cache.delete(cache.keys().next().value);}const yaw=canvas.dataset.yaw===undefined?undefined:Number(canvas.dataset.yaw);M.render(canvas,result.scene,{...options(id,yaw),softwareOnly:thumb});canvas.dataset.exercise=id;canvas.dataset.visualStyle=result.style;return result.model;}
root.Exercise3D={...old,scene,options,draw,targets,ellipsoid};root.AnatomyStyle15={skin,focus,dark,sourceModelRecovered:false};
})(window);
