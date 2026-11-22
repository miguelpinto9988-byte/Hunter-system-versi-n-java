/* Orientación compartida del rig 2D: cabeza, pecho, rótulas y calzado. */
(function(root){'use strict';
const minus=a=>a.map(x=>-x),sub=(a,b)=>a.map((x,i)=>x-b[i]),normal=a=>[-a[1],a[0]],dot=(a,b)=>a[0]*b[0]+a[1]*b[1],unit=a=>{const len=Math.hypot(...a)||1;return a.map(x=>x/len);},add=(a,b,n=1)=>a.map((x,i)=>x+b[i]*n);
const supine=new Set(['press','neutral','bridge','benchpress','inclinebench','squeezepress','singlebridge','dbbridge','pullover','lyingtricep','singletricep','deadbug','heel','reversecrunch','curlup','supinearms','hipthrust','singlepress','altfloorpress']);
const prone=new Set(['wall','incline','pushup','kneepush','closepush','bird','plank','cat','kickback','kneelingplank','mountain','wallplank','bearcrawl','bearhold','childpose','pau-plankup','pau-pushchild','decline19','pike19']);
const family=m=>m.view21?.family||'';
const floors=new WeakMap();
// Estos gestos parten de una plantilla frontal: mostrar el rostro de perfil
// dejaba el torso y las extremidades en otro plano.
for(const id of ['shoulder','concentration','pau-armupdown']){
 const model=MotionDB[id],view=model?.view21;if(!view)continue;
 Object.assign(view,{primary:'front',mirror:false,label:'Frontal',reason:'Ver torso, rostro y ambos lados del gesto en el mismo plano.'});
}
function posture(model){if(model.posture23)return model.posture23;const id=family(model),view=model.view21?.primary||'side';return view==='top'?'prone':view==='lying'?'side-lying':supine.has(id)?'supine':prone.has(id)?'prone':'upright';}
function handedness(model){if(model.facing23)return model.facing23==='left'?1:-1;const p=model.frames[0].p;return posture(model)==='prone'&&p[2][0]>p[1][0]?1:-1;}
function align(f,model){
 if(['calf','dbcalf','singlecalf'].includes(family(model))){
  const first=model.frames[0].p,center=first[2][0];
  for(const [knee,ankle,offset] of [[7,8,5],[9,10,-12]]){const shift=first[ankle][0]-(center+offset);f.p[knee][0]-=shift;f.p[ankle][0]-=shift;}
  if(family(model)!=='singlecalf'){
   for(const [elbow,hand,offset] of [[3,4,10],[5,6,-10]]){const shift=first[hand][0]-(center+offset);f.p[elbow][0]-=shift;f.p[hand][0]-=shift;}
   if(f.loads.length===2)f.loads=[f.p[4].slice(),f.p[6].slice()];
  }
 }
 if(model.view21?.primary==='side'&&posture(model)==='prone'&&!['cat','childpose','pau-pushchild'].includes(family(model))){
  const axis=unit(sub(f.p[2],f.p[1])),length=Math.min(62,Math.max(52,Math.hypot(...sub(f.p[0],f.p[1]))));
  f.p[0]=add(f.p[1],axis,-length);
 }
 return f;
}
function describe(e,f,model){
 const p=f.p,view=model.view21?.primary||'side',kind=posture(model),axis=unit(sub(p[2],p[1])),neck=unit(sub(p[1],p[0])),handed=handedness(model),ventral=normal(axis).map(x=>x*handed);
 const face=view==='side'?normal(neck).map(x=>x*handed):null;
 if(!floors.has(model))floors.set(model,Math.max(...model.frames.flatMap(x=>[x.p[8][1],x.p[10][1]])));
 const floor=f.detail.floor23??floors.get(model);
 const id=family(model),wide=['sumo','dbsumo','squatstep','lateralunge','dbsidelunge','lateral-lunge'].includes(id),standingSupport=['wall','wallplank','incline'].includes(id),calf=/^(?:calf|dbcalf|singlecalf|seatedcalf|seatedheel|wallcalf)$/.test(id);
 const feet=[],knees=[];
 for(const [kneeIndex,ankleIndex,side] of [[7,8,-1],[9,10,1]]){
  const ankle=p[ankleIndex],knee=p[kneeIndex],lower=unit(sub(ankle,knee));let direction,grounded=false;
  if(view==='side'){
   direction=normal(lower).map(x=>x*handed);
   grounded=(kind!=='prone'||standingSupport)&&ankle[1]>=floor-30&&ankle[1]-knee[1]>45;
   if(grounded)direction=[Math.sign(direction[0])||1,0];
   if(['toelift','heeltoe','ankle'].includes(f.detail.feet)){
    const amount=f.detail.toeAmount??f.detail.amount??0,angle=f.detail.feet==='ankle'?(amount-.5)*.8:amount*-.62;
    direction=[Math.cos(angle),Math.sin(angle)];
   }
  }else if(view==='top')direction=f.detail.front19?minus(axis):axis;
  else if(view==='rear')direction=minus(axis);
  else if(view==='lying')direction=normal(lower);
  else direction=unit(add(axis,normal(axis),-side*(wide?.55:.12)));
  if(f.detail.footAxis23)direction=unit(f.detail.footAxis23);
  let heel=add(ankle,direction,-7),toe=add(ankle,direction,28);
  if(f.detail.rearToe23===ankleIndex){direction=unit([1,.45]);toe=[ankle[0]+28,floor];heel=add(ankle,direction,-7);}
  if(calf&&grounded){toe=[ankle[0]+28,floor+4];direction=unit(sub(toe,ankle));heel=add(ankle,direction,-7);}
  feet.push({index:ankleIndex,side,origin:ankle,axis:direction,heel,toe,grounded,view});
  const upper=unit(sub(knee,p[2])),patella=view==='side'?normal(upper).map(x=>x*handed):direction;
  knees.push({index:kneeIndex,origin:knee,axis:patella,view});
 }
 return {view,posture:kind,handedness:handed,torsoAxis:axis,chestAxis:ventral,faceAxis:face,neckAxis:neck,feet,knees};
}
root.Anatomy2D22={align,describe,posture,dot};
})(window);
