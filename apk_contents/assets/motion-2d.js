/* Ilustración vectorial 2D. Coordenadas del catálogo, sin mallas ni WebGL. */
(function(root){'use strict';
const bounds=new Map(),esc=s=>String(s).replace(/[&<>"']/g,c=>({'&':'&amp;','<':'&lt;','>':'&gt;','"':'&quot;',"'":'&#39;'}[c]));
const n=v=>Number(v).toFixed(2),point=p=>p.map(n).join(','),add=(a,b,k=1)=>[a[0]+b[0]*k,a[1]+b[1]*k],sub=(a,b)=>[a[0]-b[0],a[1]-b[1]],unit=(a,b)=>{const d=sub(b,a),l=Math.hypot(...d)||1;return d.map(x=>x/l);},normal=v=>[-v[1],v[0]];
function get(id){if(root.Motion30)return root.Motion30.get(id);const e=Engine.byId(id),base=e?.baseId||id,model=MotionDB[base];if(!model)throw Error('Falta demostración 2D: '+id);return {e,base,model};}
function dimensions(base,model){if(bounds.has(base))return bounds.get(base);const pts=model.frames.flatMap(f=>[...f.p,...f.loads,...f.equipment.flatMap(x=>[[x[1][0],x[1][1]],[x[1][2],x[1][3]]])]),xs=pts.map(p=>p[0]),ys=pts.map(p=>p[1]);const b={minX:Math.min(...xs)-62,maxX:Math.max(...xs)+65,minY:Math.min(...ys)-55,maxY:Math.max(...ys)+38};const inset=!!model.view21?.inset,scale=Math.min(570/(b.maxX-b.minX),(inset?470:620)/(b.maxY-b.minY));b.scale=scale;b.tx=360-(b.minX+b.maxX)*scale/2;b.ty=(inset?338:398)-(b.minY+b.maxY)*scale/2;bounds.set(base,b);return b;}
/* V37.5: continuous bounded cubic pose interpolation. No changed exercise definitions. */
function pose(model,phase){
 const frames=model.frames, ph=((Number(phase)||0)%48+48)%48,i=Math.floor(ph),t=ph-i;
 const a=frames[i], b=frames[(i+1)%48],prev=frames[(i+47)%48],next=frames[(i+2)%48];
 const linear=(p,q)=>p.map((v,k)=>v+(((q||p)[k]??v)-v)*t);
 const bounded=(left,a,b,right)=>{
  if(![left,a,b,right].every(Number.isFinite))return a+(b-a)*t;
  const difference=b-a;
  if(Math.abs(difference)<.00001)return a;
  const min=Math.min(a,b),max=Math.max(a,b),sign=Math.sign(difference);
  const tangent=(x)=>Math.sign(x)===sign?sign*Math.min(Math.abs(x),2*Math.abs(difference)):0;
  const m1=tangent((b-left)/2),m2=tangent((right-a)/2),t2=t*t,t3=t2*t;
  const value=(2*t3-3*t2+1)*a+(t3-2*t2+t)*m1+(-2*t3+3*t2)*b+(t3-t2)*m2;
  return Math.max(min,Math.min(max,value));
 };
 const interpolate=(first,second,earlier,later)=>first.map((v,k)=>bounded(earlier?.[k]??v,v,second?.[k]??v,later?.[k]??second?.[k]??v));
 const detail={...a.detail};
 for(const k in detail){
  if(typeof detail[k]==='number'&&typeof b.detail?.[k]==='number')detail[k]+=(b.detail[k]-detail[k])*t;
 }
 for(const k of ['plate23','roller30','barCenter31'])if(Array.isArray(detail[k])&&Array.isArray(b.detail?.[k]))detail[k]=linear(a.detail[k],b.detail[k]);
 if(detail.feet30){detail.feet30={...detail.feet30};for(const k of [8,10])if(a.detail.feet30[k]&&b.detail.feet30?.[k])detail.feet30[k]=linear(a.detail.feet30[k],b.detail.feet30[k]);}
 if(detail.sidePose21&&b.detail.sidePose21)detail.sidePose21=a.detail.sidePose21.map((p,j)=>linear(p,b.detail.sidePose21[j]));
 const p=a.p.map((joint,j)=>interpolate(joint,b.p[j],prev.p[j],next.p[j]));
 const loads=a.loads.map((item,j)=>interpolate(item,b.loads[j]||item,prev.loads[j]||item,next.loads[j]||item));
 const f={...a,p,loads,detail};
 return model.specific30?f:Anatomy2D22.align(f,model);
}
function shape(a,b,wa,wb,fill,outline='#142d40'){
 const u=unit(a,b),v=normal(u),a1=add(a,v,wa/2),a2=add(a,v,-wa/2),b1=add(b,v,wb/2),b2=add(b,v,-wb/2);
 return `<path d="M${point(a1)} Q${point(add(a,u,-wa*.32))} ${point(a2)} L${point(b2)} Q${point(add(b,u,wb*.28))} ${point(b1)} Z" fill="${fill}" stroke="${outline}" stroke-width="2.4" stroke-linejoin="round"/>`;
}
function shoes22(anatomy,accent){
 return anatomy.feet.map(foot=>{
  const v=normal(foot.axis),sole=add(foot.toe,foot.axis,2),a=add(sole,v,-8),b=add(sole,v,8);
  return `<g data-foot22="${foot.index}" data-foot-view22="${foot.view}" data-axis22="${point(foot.axis)}" data-heel22="${point(foot.heel)}" data-toe22="${point(foot.toe)}">${shape(foot.heel,foot.toe,foot.view==='side'?17:21,foot.view==='side'?12:18,'#243d50')}<path d="M${point(a)}L${point(b)}" fill="none" stroke="${accent}" stroke-width="3" stroke-linecap="round"/></g>`;
 }).join('');
}
function knees22(anatomy,accent){
 return anatomy.knees.map(knee=>{const q=knee.origin,end=add(q,knee.axis,9);return `<g data-knee22="${knee.index}" data-axis22="${point(knee.axis)}"><circle cx="${n(q[0])}" cy="${n(q[1])}" r="6.2" fill="#243d50"/><path d="M${point(q)}L${point(end)}" stroke="${accent}" stroke-width="3.2" stroke-linecap="round"/></g>`;}).join('');
}
function bilateral19(e,f,accent,anatomy){
 const p=f.p,ink='#243d50',cloth='#dceaf0',active='#fb927d',top=!!(f.detail.topShoulder19||f.detail.front19||f.detail.supineTop23),headTop=top&&anatomy.posture!=='supine',back=f.detail.view21==='rear',t=unit(p[1],p[2]),v=normal(t);
 const armActive=['Pecho','Brazos','Hombros','Espalda'].includes(e.group),legActive=['Piernas','Cadera'].includes(e.group);
 const left=add(p[1],v,42),right=add(p[1],v,-42),hips=[add(p[2],v,25),add(p[2],v,-25)];
 let body=shape(left,hips[0],43,30,cloth)+shape(right,hips[1],43,30,cloth);
 body+='<path d="M'+left.join(',')+'Q'+p[1][0]+','+(p[1][1]-12)+' '+right.join(',')+'L'+hips[1].join(',')+'Q'+p[2][0]+','+(p[2][1]+16)+' '+hips[0].join(',')+'Z" fill="'+cloth+'" stroke="'+ink+'" stroke-width="3"/>';
 for(const [a,elbow,hand] of [[left,p[3],p[4]],[right,p[5],p[6]]]){
  body+=shape(a,elbow,28,20,cloth)+shape(elbow,hand,20,13,cloth)+(armActive?shape(a,elbow,12,8,active,'none'):'');
  body+='<circle cx="'+elbow[0]+'" cy="'+elbow[1]+'" r="6" fill="'+ink+'"/><circle cx="'+elbow[0]+'" cy="'+elbow[1]+'" r="2.5" fill="'+accent+'"/><ellipse cx="'+hand[0]+'" cy="'+hand[1]+'" rx="9" ry="11" fill="#edc5ad" stroke="'+ink+'" stroke-width="2"/>';
 }
 for(const [hip,knee,foot] of [[hips[0],p[7],p[8]],[hips[1],p[9],p[10]]])body+=shape(hip,knee,34,25,cloth)+shape(knee,foot,25,17,cloth)+(legActive?shape(hip,knee,13,9,active,'none'):'');
 body+=knees22(anatomy,accent)+shoes22(anatomy,accent);
 if(['Abdomen','Core'].includes(e.group))for(const k of [.35,.5,.65]){const c=add(p[1],t,Math.hypot(...sub(p[2],p[1]))*k);body+='<path d="M'+point(add(c,v,-16))+'L'+point(add(c,v,16))+'" stroke="'+active+'" stroke-width="10" stroke-linecap="round"/>';}
 body+=shape(add(p[1],t,-7),add(p[0],t,22),18,18,'#ddad95');
 const angle=Math.atan2(t[1],t[0])*180/Math.PI-90,h=p[0];
 body+='<g data-head-view="'+(headTop?'top':back?'rear':'front')+'" transform="rotate('+n(angle)+' '+point(h).replace(',',' ')+')"><ellipse cx="'+h[0]+'" cy="'+h[1]+'" rx="28" ry="32" fill="'+(headTop||back?ink:'#edc5ad')+'" stroke="'+ink+'" stroke-width="3"/>';
 if(!headTop&&!back)body+='<path d="M'+(h[0]-26)+','+(h[1]-5)+'q0,-37 52,-8l-9,-8q-19,10 -43,16" fill="'+ink+'"/><circle cx="'+(h[0]-10)+'" cy="'+(h[1]-2)+'" r="2.6" fill="'+ink+'"/><circle cx="'+(h[0]+10)+'" cy="'+(h[1]-2)+'" r="2.6" fill="'+ink+'"/><path d="M'+(h[0]-5)+','+(h[1]+15)+'q5,3 10,0" fill="none" stroke="'+ink+'" stroke-width="2"/>';
 body+='</g>';
 if(f.detail.topShoulder19||back){body+='<path d="M'+(p[1][0]-25)+','+(p[1][1]+23)+'q25,25 50,0M'+p[1][0]+','+(p[1][1]+35)+'V'+(p[2][1]-12)+'" fill="none" stroke="'+active+'" stroke-width="9" stroke-linecap="round"/>';}

 if(f.detail.opposite23){const [a,b]=f.detail.opposite23;for(const [j,u]of [[4,a],[10,a],[6,b],[8,b]])body+='<circle data-opposite23="'+j+'" cx="'+p[j][0]+'" cy="'+p[j][1]+'" r="'+(7+7*u)+'" opacity="'+(.2+.8*u)+'" fill="none" stroke="'+accent+'" stroke-width="4"/>';}
 return body;
}
function shoulderInset19(f,accent){
 const lift=14*(f.detail.lift19||0),y=682-lift;
 return '<g data-view="side-inset"><rect x="58" y="620" width="282" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="75" y="642" font-size="13" fill="'+accent+'" font-family="sans-serif">DE LADO · RECORRIDO CORTO</text><path d="M80 701H317" stroke="#688594" stroke-width="2"/><ellipse cx="103" cy="686" rx="13" ry="9" fill="#edc5ad"/><path d="M121 685H257" stroke="#dceaf0" stroke-width="16" stroke-linecap="round"/><path d="M149 682L174 '+y+'L207 '+y+'" fill="none" stroke="#fb927d" stroke-width="9" stroke-linecap="round"/><path d="M278 696V660m-5 8 5-8 5 8" fill="none" stroke="'+accent+'" stroke-width="2"/><text x="75" y="719" font-size="12" fill="#b0c6d1" font-family="sans-serif">Pecho apoyado · eleva manos y codos</text></g>';
}
function contextInset21(kind,f,phase,accent,view){
 const extra=root.Motion30?.inset?.(kind,f,phase,accent)||root.Rig2D23?.inset(kind,f,phase,accent);if(extra)return extra;
 let drawing='',label='',note='',a=(1-Math.cos(phase/48*Math.PI*2))/2;
 if(kind==='wall'){
  label='DE LADO · CONTACTO CON LA PARED';note='Espalda apoyada · pared en transparencia en la vista principal';
  drawing='<path d="M101 650V703" stroke="#688594" stroke-width="7"/><circle cx="117" cy="654" r="9" fill="#edc5ad"/><path d="M118 666L120 685L124 700" stroke="#dceaf0" stroke-width="11" stroke-linecap="round"/><path d="M121 670L137 '+(679-17*a)+'L110 '+(653-12*a)+'" fill="none" stroke="#fb927d" stroke-width="5"/>';
 }else if(['hinge','arms','reach'].includes(kind)){
  label=kind==='hinge'?'DE LADO · TRONCO Y APOYO':'DE LADO · ALCANCE HACIA DELANTE';
  note=kind==='hinge'?'La vista posterior muestra la apertura · esta vista conserva el apoyo':'Compara apertura lateral y recorrido hacia delante en las dos vistas';
  const q=f.detail.sidePose21,b=view.insetBounds,scale=Math.min(150/(b.maxX-b.minX),52/(b.maxY-b.minY)),tx=155-(b.maxX+b.minX)*scale/2,ty=675-(b.maxY+b.minY)*scale/2;
  let art='';
  for(const [type,r] of f.detail.sideEquipment21){
   if(type==='incline'){const t=unit(q[1],q[2]),v=normal(t),x=add(q[1],v,-44),y=add(q[2],v,-44);art+='<path d="M'+point(x)+'L'+point(y)+'M'+point(x)+'V710M'+point(y)+'V710" stroke="#7a9aa7" stroke-width="15" fill="none"/>';}
   else art+='<path d="M'+r[0]+','+r[1]+'H'+r[2]+'M'+r[0]+','+r[1]+'V'+r[3]+'M'+r[2]+','+r[1]+'V'+r[3]+'" stroke="#7a9aa7" stroke-width="15" fill="none"/>';
  }
  for(const [from,to] of [[1,2],[2,7],[7,8],[2,9],[9,10],[1,3],[3,4],[1,5],[5,6]])art+='<path d="M'+point(q[from])+'L'+point(q[to])+'" stroke="'+(from===1&&to!==2?'#fb927d':'#dceaf0')+'" stroke-width="'+(from===1&&to===2?32:17)+'" fill="none" stroke-linecap="round"/>';
  art+='<circle cx="'+q[0][0]+'" cy="'+q[0][1]+'" r="26" fill="#edc5ad"/>';
  drawing='<g data-source-pose="true" transform="translate('+tx+' '+ty+') scale('+scale+')">'+art+'</g>';
 }else if(kind==='hand'){
  const detail=f.detail.detail||view.family,amount=f.detail.amount??a;
  label=detail==='forearmmob'?'AMPLIACIÓN · GIRO DEL ANTEBRAZO':detail==='fingers'?'AMPLIACIÓN · PALMA Y DEDOS':'DE LADO · FLEXIÓN Y EXTENSIÓN DE MUÑECA';
  note='Recorrido pequeño · la ampliación permite distinguir el movimiento';
  if(detail==='fingers'){
   drawing='<path d="M138 696V678" stroke="#dceaf0" stroke-width="13"/><ellipse cx="139" cy="674" rx="12" ry="15" fill="#edc5ad" stroke="#243d50" stroke-width="2"/>';
   for(let i=0;i<4;i++){const x=131+i*5,angle=(i-1.5)*14*amount,y=660-(5+2*i)*amount;drawing+='<path d="M'+x+' 667L'+(x+angle*.4)+' '+y+'" stroke="#edc5ad" stroke-width="4" stroke-linecap="round"/>';}
   drawing+='<path d="M128 677L'+(125-10*amount)+' '+(675-6*amount)+'" stroke="#edc5ad" stroke-width="5" stroke-linecap="round"/>';
  }else if(detail==='forearmmob'){
   const turn=Math.cos(Math.PI*amount),height=3+7*Math.abs(turn);drawing='<path d="M105 685H146" stroke="#dceaf0" stroke-width="12" stroke-linecap="round"/><ellipse cx="163" cy="685" rx="18" ry="'+height+'" fill="'+(turn>=0?'#edc5ad':'#d7ab92')+'" stroke="#243d50" stroke-width="2"/><path d="M151 685l8 '+(12*turn)+'" stroke="#edc5ad" stroke-width="5" stroke-linecap="round"/><path d="M167 '+(685-height+2)+'v'+(height*2-4)+'" stroke="#a97060" stroke-width="2"/><path d="M135 659q24-13 45 3m-8-2 8 2-5-7" fill="none" stroke="'+accent+'" stroke-width="2"/>';
  }else{
   const angle=-35+70*amount;drawing='<path d="M99 684H149" stroke="#dceaf0" stroke-width="13" stroke-linecap="round"/><circle cx="149" cy="684" r="4" fill="'+accent+'"/><g transform="rotate('+angle+' 149 684)"><path d="M149 684H183" stroke="#edc5ad" stroke-width="10" stroke-linecap="round"/><path d="M160 684l10 7" stroke="#edc5ad" stroke-width="4" stroke-linecap="round"/></g>';
  }
 }else{
  label='DE LADO · DISTANCIA AL SUELO';note='La vista superior distingue brazos · esta vista muestra el descenso';
  drawing='<circle cx="101" cy="665" r="9" fill="#edc5ad"/><path d="M114 '+(665+14*a)+'L167 681L208 697" stroke="#dceaf0" stroke-width="10" stroke-linecap="round"/><path d="M123 '+(669+14*a)+'L130 694L146 700" stroke="#fb927d" stroke-width="6" fill="none"/>';
 }
 return '<g data-view="side-inset"><rect x="58" y="620" width="604" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="75" y="642" font-size="13" fill="'+accent+'" font-family="sans-serif">'+label+'</text><path d="M80 703H230" stroke="#688594" stroke-width="2"/>'+drawing+'<text x="249" y="674" font-size="12" fill="#b0c6d1" font-family="sans-serif">VISTA COMPLEMENTARIA</text><text x="75" y="719" font-size="12" fill="#b0c6d1" font-family="sans-serif">'+note+'</text></g>';
}
function svg(id,phase=0,mirror=false,accent='#7ce2e9',options={}){
 const {e,base,model}=get(id),f=pose(model,model.specific30?(Number(phase)||0):(root.Rig2D23?.phase(e,Number(phase)||0)??(Number(phase)||0))),p=f.p,d=f.detail,b=dimensions(base,model),anatomy=Anatomy2D22.describe(e,f,model),active='#fb927d',cloth='#dceaf0',rear='#94aeba',ink='#243d50';let body='';
 const limb=(a,c,w1,w2,color,muscle=false)=>shape(a,c,w1,w2,color)+(muscle?shape(add(a,unit(a,c),9),add(c,unit(a,c),-9),w1*.48,w2*.5,active,'none'):'');
 const armActive=['Pecho','Brazos','Hombros','Espalda'].includes(e.group),legActive=['Piernas','Cadera'].includes(e.group),coreActive=['Abdomen','Core'].includes(e.group);
 for(const [type,r] of f.equipment){if(type==='bounds23')continue;if(type==='incline'){const axis=unit(p[1],p[2]),v=normal(axis),a=add(p[1],v,44),c=add(p[2],v,44);body+=`<g data-support="inclined-bench" fill="none" stroke="#7a9aa7" stroke-width="10" stroke-linecap="round"><path d="M${point(a)}L${point(c)}"/><path d="M${point(a)}V710M${point(c)}V710" stroke-width="5"/></g>`;continue;}const [x,y,right,bottom]=r,seat=['chair','bench','table'].includes(type);body+=`<g fill="#1d3b4c" stroke="#7a9aa7" stroke-width="3"><rect x="${n(x)}" y="${n(y)}" width="${n(right-x)}" height="${n(seat?12:Math.max(8,bottom-y))}" rx="6"/>${seat?`<path d="M${x+8},${y+12}V${bottom}M${right-8},${y+12}V${bottom}" fill="none"/>`:''}${type==='chair'?`<path d="M${x},${y-85}v90" fill="none" stroke-width="6"/>`:''}</g>`;}
 const apparatus21=body+(root.Motion30?.extras(f,accent)||root.Rig2D23?.extras(f,accent)||'');body=apparatus21;
 // Extremidades posteriores y pelvis, luego torso y extremidades anteriores.
 body+=limb(p[2],p[9],37,26,rear,legActive)+limb(p[9],p[10],25,17,rear,legActive)+limb(p[1],p[5],29,20,rear,armActive)+limb(p[5],p[6],20,14,rear,armActive);
 const torso=unit(p[1],p[2]),v=normal(torso),s1=add(p[1],v,30),s2=add(p[1],v,-30),h1=add(p[2],v,20),h2=add(p[2],v,-20),mid=add(p[1],torso,Math.hypot(...sub(p[2],p[1]))*.42);
 body+=`<path d="M${point(s1)} Q${point(add(add(mid,v,26),[0,-(d.spineCurve23||0)]))} ${point(h1)} Q${point(add(p[2],torso,20))} ${point(h2)} Q${point(add(add(mid,v,-26),[0,-(d.spineCurve23||0)]))} ${point(s2)} Q${point(add(p[1],torso,-13))} ${point(s1)} Z" fill="${cloth}" stroke="${ink}" stroke-width="3"/>`;
 
 body+=`<path data-chest22="true" data-axis22="${point(anatomy.chestAxis)}" d="M${point(add(add(p[1],torso,28),anatomy.chestAxis,31))}L${point(add(mid,anatomy.chestAxis,31))}" fill="none" stroke="#afc6d2" stroke-width="3" stroke-linecap="round"/>`;
 if(coreActive){for(const k of [.36,.51,.66]){const c=add(p[1],torso,Math.hypot(...sub(p[2],p[1]))*k);body+=`<path d="M${point(add(c,v,-17))}L${point(add(c,v,17))}" stroke="${active}" stroke-width="13" stroke-linecap="round"/>`;}}
 if(armActive&&['Pecho','Hombros','Espalda'].includes(e.group)){body+=shape(add(p[1],torso,20),add(mid,torso,-9),55,43,active,'none');}
 body+=`<path d="M${point(add(p[2],v,-26))}L${point(add(p[2],v,26))}" stroke="${ink}" stroke-width="11" stroke-linecap="round"/>`;
 body+=limb(p[2],p[7],39,27,cloth,legActive)+limb(p[7],p[8],27,17,cloth,legActive)+limb(p[1],p[3],30,20,cloth,armActive)+limb(p[3],p[4],21,14,cloth,armActive);
 for(const i of [3,5])body+=`<circle cx="${n(p[i][0])}" cy="${n(p[i][1])}" r="6.8" fill="${ink}"/><circle cx="${n(p[i][0])}" cy="${n(p[i][1])}" r="2.4" fill="${accent}"/>`;
 body+=knees22(anatomy,accent);
 const head=p[0],sign=-anatomy.handedness,headAngle=Math.atan2(p[1][1]-head[1],p[1][0]-head[0])*180/Math.PI-90;body+=shape(add(p[1],torso,-9),add(head,torso,20),18,18,'#ddad95');
 body+=`<g data-head-view="side" data-axis22="${point(anatomy.faceAxis||anatomy.chestAxis)}" transform="rotate(${n(headAngle)} ${n(head[0])} ${n(head[1])})"><ellipse cx="${n(head[0])}" cy="${n(head[1])}" rx="26" ry="31" fill="#edc5ad" stroke="${ink}" stroke-width="2.5"/><g transform="${sign<0?'translate('+n(head[0]*2)+' 0) scale(-1 1)':''}"><path d="M${n(head[0]-26)},${n(head[1]-3)}Q${n(head[0]-30)},${n(head[1]-35)} ${n(head[0]+2)},${n(head[1]-33)}Q${n(head[0]+27)},${n(head[1]-31)} ${n(head[0]+26)},${n(head[1]-11)}L${n(head[0]+9)},${n(head[1]-19)}Q${n(head[0]-8)},${n(head[1]-10)} ${n(head[0]-26)},${n(head[1]-3)}" fill="${ink}"/><path d="M${n(head[0]+17)},${n(head[1]-2)}l10,8l-8,3" fill="#edc5ad" stroke="${ink}" stroke-width="1.7"/><circle cx="${n(head[0]+10)}" cy="${n(head[1]-3)}" r="2.5" fill="${ink}"/></g></g>`;
 for(const i of [4,6]){const q=p[i];body+=`<ellipse cx="${n(q[0])}" cy="${n(q[1]+3)}" rx="9" ry="12" fill="#edc5ad" stroke="${ink}" stroke-width="2"/>`;}
 body+=shoes22(anatomy,accent);
 if(d.topShoulder19||d.front19||d.supineTop23||['front','rear','lying'].includes(model.view21?.primary))body=apparatus21+bilateral19(e,f,accent,anatomy);
 for(const q of f.loads){body+=`<g transform="translate(${point(q)})" fill="${ink}" stroke="#7298aa" stroke-width="2"><rect x="-20" y="-4" width="40" height="8" rx="4"/><rect x="-28" y="-16" width="14" height="32" rx="4"/><rect x="14" y="-16" width="14" height="32" rx="4"/><path d="M-21 -10v20M21 -10v20" stroke="${accent}" stroke-width="3"/></g>`;}
 body+=root.Rig2D23?.loads(f,model.view21.primary,accent)||'';
 const phaseName=label(id,phase),ground=Math.min(745,(d.floor23??(b.maxY-26))*b.scale+b.ty),transform=`translate(${n(b.tx)} ${n(b.ty)}) scale(${n(b.scale)})`;
 const breath30=model.hold?`<g data-hold30="breathing"><text x="510" y="593" font-family="sans-serif" font-size="12" fill="#9db7c4">RESPIRA</text><circle cx="617" cy="588" r="${n(9+6*(1-Math.cos(phase/48*Math.PI*2))/2)}" fill="none" stroke="${accent}" stroke-width="2"/></g>`:'';
 const mir=(!!mirror!==!!model.view21?.mirror)?'translate(720 0) scale(-1 1)':'';
 const view=model.view21;const caption=view?`<text x="360" y="86" text-anchor="middle" fill="#b0c6d1" font-size="13" font-family="sans-serif">VISTA ${esc(view.label.toUpperCase())}${d.topShoulder19?' · '+esc(d.letter19):''}</text>`:'';
 const insetArt=view?.inset==='prone'?shoulderInset19(f,accent):view?.inset?contextInset21(view.inset,f,phase,accent,view):'';
 const detailArt=root.Motion31?.reflowInset(insetArt)??insetArt,height=view?.inset&&root.Motion31?1000:800;
 return `<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 720 ${height}" role="img" aria-label="${esc(e.name)} · demostración 2D" data-renderer="2d" data-base="${esc(base)}" data-view="${view?.primary||'side'}" data-posture22="${anatomy.posture}" data-default-mirror="${!!view?.mirror}"><title>${esc(e.name)}</title><rect width="720" height="${height}" rx="30" fill="#101f2a"/><circle cx="360" cy="370" r="285" fill="#152e3d"/><circle cx="360" cy="370" r="230" fill="none" stroke="#284352" stroke-width="1"/><path d="M62 115v-42h42M616 73h42v42M62 685v42h42M616 727h42v-42" fill="none" stroke="${accent}" stroke-width="3" opacity=".7"/>${caption}${view?.primary==='top'?'':`<path d="M100 ${n(ground)}H620" stroke="#47606e" stroke-width="2"/>`}<g transform="${mir}"><g data-anatomy22="true" transform="${transform}">${body}</g></g>${breath30}${detailArt}${options.thumbnail?'':`<text x="360" y="50" text-anchor="middle" fill="${accent}" font-size="15" letter-spacing="3" font-family="sans-serif">${phaseName}</text><text x="360" y="${height-28}" text-anchor="middle" fill="#b0c6d1" font-size="15" font-family="sans-serif">ILUSTRACIÓN 2D · ${esc(e.group.toUpperCase())}</text>`}</svg>`;
}
/* V37.5: stage labels reflect the real cycle allocation, not the wrong half. */
function label(id,phase){
 const {e,model}=get(id),t=((Number(phase)||0)%48+48)%48/48;
 const c=root.Motion30?.cadence(e),f=pose(model,model.specific30?phase:(root.Rig2D23?.phase(e,phase)??phase));
 if(c?.pause&&t>=c.pauseStart&&t<c.pauseEnd){const hold=(c.pauseEnd-c.pauseStart)*c.seconds;return 'PAUSA · '+Number(hold.toFixed(1)).toLocaleString('es-PE')+' SEGUNDOS';}
 if(c?.slow){
  const descent=/descenso|bajada/.test(e.name.toLowerCase());
  if(descent&&t<c.slowSplit){return 'DESCENSO · '+Number((c.slowSplit*c.seconds).toFixed(1)).toLocaleString('es-PE')+' SEGUNDOS';}
  if(!descent&&t>=c.slowSplit){return 'REGRESO · '+Number(((1-c.slowSplit)*c.seconds).toFixed(1)).toLocaleString('es-PE')+' SEGUNDOS';}
  return descent?'REGRESO CONTROLADO':'EJECUCIÓN CONTROLADA';
 }
 if(model.view21?.inset==='hand')return model.view21.family==='fingers'?'ABRE Y CIERRA CON SUAVIDAD':model.view21.family==='forearmmob'?'GIRO CONTROLADO DEL ANTEBRAZO':'FLEXIÓN Y EXTENSIÓN SUAVES';
 return model.hold?'POSTURA SOSTENIDA · RESPIRA':f.detail.stage18||f.detail.jumpPhase18?.toUpperCase()||(t<.5?'EJECUCIÓN CONTROLADA':'REGRESO CONTROLADO');
}
function patchSVG20(current,next){
 const names=new Set(next.getAttributeNames());for(const name of current.getAttributeNames())if(!names.has(name))current.removeAttribute(name);
 for(const name of names)if(current.getAttribute(name)!==next.getAttribute(name))current.setAttribute(name,next.getAttribute(name));
 const old=Array.from(current.children),children=Array.from(next.children);
 if(!children.length){if(current.textContent!==next.textContent)current.textContent=next.textContent;return;}
 for(let i=0;i<children.length;i++){const child=children[i];if(!old[i])current.append(child.cloneNode(true));else if(old[i].tagName!==child.tagName)old[i].replaceWith(child.cloneNode(true));else patchSVG20(old[i],child);}
 for(let i=children.length;i<old.length;i++)old[i].remove();
}
function draw(canvas,id,phase=0,mirror=false){
 if(!canvas)return;const {model,base}=get(id);canvas.hidden=true;canvas.dataset.renderer='2d';canvas.dataset.base=base;
 let host=canvas.nextElementSibling;if(!host?.classList.contains('motion-svg18')){host=document.createElement('div');host.className='motion-svg18';canvas.after(host);}
 host.dataset.exercise=id;host.dataset.phase=String(phase);const accent=getComputedStyle(document.documentElement).getPropertyValue('--accent').trim()||'#7ce2e9';
 const template=document.createElement('template');template.innerHTML=svg(id,phase,mirror,accent);const next=template.content.firstElementChild,current=host.firstElementChild;
 const detail=next.querySelector('[data-detail-layout31]');let detailSVG=null;
 if(detail){
  detailSVG=next.cloneNode(false);detailSVG.setAttribute('viewBox','58 620 604 288');detailSVG.setAttribute('class','detail-svg31');detailSVG.setAttribute('aria-label','Detalle animado de '+Engine.byId(id).name);detailSVG.append(detail.cloneNode(true));
  detail.remove();next.setAttribute('viewBox','0 0 720 610');next.querySelector('text[y="972"]')?.remove();
 }
 host.dataset.hasDetail31=String(!!detailSVG);
 if(current?.tagName===next?.tagName)patchSVG20(current,next);else host.replaceChildren(next);
 if(detailSVG){const currentDetail=host.children[1];if(currentDetail)patchSVG20(currentDetail,detailSVG);else host.append(detailSVG);}else host.children[1]?.remove();
 return model;
}

root.Motion2D18={svg,draw,pose,get,dimensions,label,clearBounds:()=>bounds.clear()};
})(typeof window!=='undefined'?window:globalThis);
