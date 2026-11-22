/* Correcciones V31 compartidas por todas las fichas de cada familia. */
(function(root){'use strict';
const cp=x=>JSON.parse(JSON.stringify(x)),mix=Rig2D23.mix,wave=Rig2D23.wave;
const add=(a,b,k=1)=>a.map((v,i)=>v+b[i]*k),sub=(a,b)=>a.map((v,i)=>v-b[i]),unit=v=>{const d=Math.hypot(...v)||1;return v.map(x=>x/d);};
const ik=Rig2D23.ik;
function head(f){f.p[0]=add(f.p[1],unit(sub(f.p[1],f.p[2])),60);}
function arm(f,end,other=null,length=95,bend=-1){
 for(const [el,ha,offset,tip]of [[3,4,[0,0],end],[5,6,[-13,-13],other||add(end,[-13,-13])]]){
  const origin=add(f.p[1],offset);f.p[ha]=tip;f.p[el]=ik(origin,tip,length,bend);
 }
}
function feet(f,a=[1,0],b=a){f.detail.feet30={8:a,10:b};}
function blank(){return {p:cp(Rig2D23.standing().p),loads:[],equipment:[],detail:{floor23:715}};}
const kindOf=(e,raw)=>e.recipe30||e.recipe23||raw.view21.family;
const squatKinds=new Set(['squat','chair','goblet','doublefront']);
function squatArms(f,p,kind,a){
 const s=f.p[1];
 if(p.back){
  for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){f.p[el]=[s[0]-55+o,s[1]+70+o];f.p[ha]=[s[0]+8+o,s[1]+12+o];}
  f.loads=[];f.detail.barY30=s[1]+12;f.detail.barCenter31=[s[0]+8,s[1]+12];
 }else if(p.front||p.rack||kind==='goblet'||kind==='doublefront'){
  arm(f,add(s,[48,42]),null,95,-1);
  f.loads=kind==='goblet'?[mix(f.p[4],f.p[6],.5)]:p.gear==='barbell'?[]:[cp(f.p[4]),cp(f.p[6])];
 }else if(p.db||p.offset||p.belt||p.hack||p.pendulum||p.vlever){
  arm(f,add(s,[15,185]),null,95,-1);
  if(p.db||p.offset)f.loads=p.offset?[cp(f.p[4])]:[cp(f.p[4]),cp(f.p[6])];
 }else{
  // De brazos relajados a alcance frontal; nunca se doblan hacia la nuca.
  const th=.08+1.42*a;
  for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){
   f.p[el]=[s[0]+95*Math.sin(th)+o,s[1]+95*Math.cos(th)+o];
   f.p[ha]=[s[0]+185*Math.sin(th)+o,s[1]+185*Math.cos(th)+o];
  }
  f.loads=[];
 }
 f.detail.squatArms31=true;f.detail.squatAmount31=a;
}
function wallPush(a,p={}){
 const f=blank(),ankle=[345,700],s=[432+65*a,335];
 f.p[1]=s;f.p[2]=mix(s,ankle,.39);head(f);
 f.p[8]=ankle;f.p[10]=add(ankle,[-13,-13]);f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=mix(add(f.p[2],[-13,-13]),f.p[10],.5);
 arm(f,[548,350],[548,337],100,-1);f.equipment=[['wall',[559,220,571,720]]];feet(f);return f;
}
function calfStretch(){
 const f=blank();f.p[2]=[350,490];f.p[1]=[395,330];head(f);
 f.p[8]=[465,700];f.p[10]=[230,687];f.p[7]=ik(f.p[2],f.p[8],140,1);f.p[9]=mix(add(f.p[2],[-13,-13]),f.p[10],.5);
 arm(f,[548,365],[548,352],100,-1);f.equipment=[['wall',[559,220,571,720]]];feet(f);return f;
}
function towel(){
 const f=blank();f.p=[[215,286],[225,345],[252,515],[276,435],[342,421],[263,422],[329,408],[414,515],[570,515],[401,502],[557,502]];
 f.detail.floor23=538;f.detail.towel31=true;f.detail.towel23=false;feet(f,[0,-1]);return f;
}
function openBook(a){
 const f=blank();f.p=[[175,600],[235,600],[420,615],[238,688],[241,776],[245,688],[245,776],[510,680],[598,653],[497,667],[585,640]];
 const th=Math.PI*a;f.p[5]=[245+88*Math.sin(th),600+88*Math.cos(th)];f.p[6]=[245+176*Math.sin(th),600+176*Math.cos(th)];
 f.detail.openBook31=a;f.detail.floor23=802;feet(f,[1,0]);return f;
}
function crossStep(a){
 const f=blank(),rise=130*a,x=350+64*a;f.p[2]=[x,480-rise];f.p[1]=[x,310-rise];head(f);
 // La pierna que cruza se ve acortada por la perspectiva frontal.
 f.p[8]=[435,602];f.p[7]=[365+60*a,535-55*a];
 f.p[10]=[300+80*a,700-98*a];f.p[9]=[335+32*a,580-80*a];
 f.p[3]=[x-56,405-rise];f.p[4]=[x-56,493-rise];f.p[5]=[x+56,405-rise];f.p[6]=[x+56,493-rise];
 f.equipment=[['step',[390,616,510,715]]];f.detail.cross31=a;feet(f,[-.08,1],[.08,1]);return f;
}
function standingHam(a){
 const f=blank(),k=[337,567],th=Math.PI/2+1.4*a;
 f.p[10]=[k[0]+140*Math.cos(th),k[1]+140*Math.sin(th)];f.p[9]=k;f.p[8]=[357,700];f.p[7]=[357,563];head(f);
 f.p[4]=[535,362];f.p[3]=ik(f.p[1],f.p[4],105,-1);f.p[5]=[337,370];f.p[6]=[337,465];
 f.equipment=[['wall',[548,260,560,715]]];f.detail.roller30=add(f.p[10],[-12,-8]);f.detail.machine30='ham-standing';
 feet(f,[1,0],[Math.sin(th),-Math.cos(th)]);return f;
}
function kneelingCrunch(a){
 const f=blank();f.p[2]=[340,550];f.p[1]=[323+68*a,388+45*a];head(f);
 f.p[7]=[405,700];f.p[8]=[245,700];f.p[9]=[392,687];f.p[10]=[232,687];
 arm(f,add(f.p[1],[28,-24]),null,95,-1);f.detail.spineCurve23=18*a;feet(f,[1,0]);return f;
}
function resolve(e,t,raw,previous){
 const old=previous(e,t,raw),p=e.param30||e.param23||{},kind=kindOf(e,raw),a=wave(t);let f=old.frame,view=old.view,posture=old.posture,changed=false;
 if(squatKinds.has(kind)){
  if(p.gear==='smith'){
   f.p[1][0]=355;head(f);f.detail.smith31=true;
  }
  squatArms(f,p,kind,p.pulses?.7+.3*wave(t*2):a);changed=true;
 }
 // Conserva apoyos unilaterales, salto y aparato de estas variantes.
 if(['single-squat19','single-squat-support19','squatjump'].includes(kind)&&view==='side'){
  squatArms(f,p,kind,a);changed=true;
 }
 if(kind==='wall'||kind==='wallplank'){
  f=wallPush(kind==='wallplank'?0:a,p);view='side';posture='upright';changed=true;
 }
 if(kind==='calfstretch'){f=calfStretch();view='side';posture='upright';changed=true;}
 if(kind==='towelrow'){f=towel();view='side';posture='upright';changed=true;}
 if(kind==='thoracic'){f=openBook(a);view='lying';posture='side-lying';changed=true;}
 if(kind==='step'&&p.cross){f=crossStep(a);view='front';posture='upright';changed=true;}
 if(kind==='hamcurl'&&p.standing){f=standingHam(a);view='side';posture='upright';changed=true;}
 if(['bridge','dbbridge','singlebridge'].includes(kind)){
  feet(f,[-1,0]);
  if(kind==='dbbridge'||p.db||e.requirements?.includes('db')){
   arm(f,add(f.p[2],[-7,-21]),null,100,-1);f.loads=[add(f.p[2],[-7,-21])];f.detail.pelvisLoad31=true;
  }
  changed=true;
 }
 if(kind==='legpress'){
  // Planta contra la plataforma; el pie libre se mantiene apartado.
  f.detail.plate23=add(f.p[8],[8,0]);if(p.single){f.p[9]=[365,641];f.p[10]=[425,700];feet(f,[0,-1],[1,0]);}
  f.detail.legPress31=true;changed=true;
 }
 if(kind==='back-extension'||kind==='crunch'&&p.seated){arm(f,add(f.p[1],[38,64]),null,95,-1);changed=true;}
 if(kind==='crunch'&&p.kneeling){f=kneelingCrunch(a);view='side';posture='upright';changed=true;}
 if(kind==='machine-press'){
  // La segunda mano queda en el asa de apoyo de la variante unilateral.
  if(p.single){f.p[5]=[267,414];f.p[6]=[295,489];}
  f.detail.press31=true;changed=true;
 }
 if(kind==='side-plank'&&p.thread){f.detail.thread31=a;changed=true;}
 if(kind==='plank'&&(p.toe||p.jack)){f.detail.toeSide31=a;changed=true;}
 // Recupera detalles de carga/aparato después de reemplazar una pose.
 if(e.gymOnly23){f.detail.gear23=p.gear||'machine';f.detail.recipe23=kind;f.detail.param23=p;
  if(!f.equipment.some(x=>x[0]==='bounds23'))f.equipment.push(['bounds23',[95,95,690,735]]);
 }
 f.detail.view21=view;f.detail.revision31=31;f.detail.cycle31=t;f.detail.amount31=a;
 if(changed)f.detail.corrected31=kind;
 return {frame:f,view,posture,corrected:old.corrected||changed};
}
function configureModel(e,m){
 const kind=kindOf(e,m),p=e.param30||e.param23||{},f=m.frames[0];m.motionRevision31=31;
 if(kind==='wall'||kind==='wallplank'||kind==='calfstretch')m.facing23='right';
 if(kind==='thoracic')m.view21.inset='openbook31';
 if(kind==='towelrow')m.view21.inset='towel31';
 if(kind==='plank'&&(p.toe||p.jack))m.view21.inset='toes31';
 if(kind==='step'&&p.cross)m.view21.inset='cross31';
 if(kind==='side-plank'&&p.thread)m.view21.inset='thread31';
 if(kind==='machine-press')m.view21.inset='press31';
 if(kind==='crunch'&&p.kneeling)m.view21.inset='crunch31';
 if(squatKinds.has(kind))m.view21.inset='squat31';
 if(kind==='dbbridge'||f.detail.pelvisLoad31)m.view21.inset='pelvis31';
 if(kind==='hamcurl'&&p.standing)m.view21.inset='ham31';
 if(kind==='calfstretch')m.view21.inset='calf31';
}
function extras(f,accent){
 const d=f.detail,p=f.p;let s='';
 if(d.towel31){const q=[p[8][0],p[8][1]-16];s+=`<g data-apparatus31="towel"><path d="M${p[4]}L${q}L${p[6]}" fill="none" stroke="${accent}" stroke-width="6" stroke-linecap="round"/><path d="M${q[0]} ${q[1]-10}q17 12 0 23" fill="none" stroke="${accent}" stroke-width="5"/></g>`;}
 // La barra se dibuja una sola vez por el renderizador de cargas.
 if(d.press31)s+=`<path data-apparatus31="backrest" d="M${p[1][0]-37} ${p[1][1]-20}L${p[2][0]-30} ${p[2][1]-10}" stroke="#527585" stroke-width="21" stroke-linecap="round"/>`;
 return s;
}
function panel(title,note,art,accent,kind){return `<g data-detail31="${kind}" data-view="side-inset"><rect x="58" y="620" width="604" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="74" y="642" font-size="13" fill="${accent}" font-family="sans-serif">${title}</text>${art}<text x="74" y="720" font-size="12" fill="#b0c6d1" font-family="sans-serif">${note}</text></g>`;}
function inset(kind,f,phase,accent){
 const d=f.detail,a=d.amount31??wave(phase/48),ink='#dceaf0',red='#fb927d';let title,note,art;
 if(kind==='towel31'){
  title='TOALLA · TENSIÓN SIN MOVER EL TRONCO';note='Tira hacia el torso y respira; los pies y la espalda quedan estables.';
  const breath=wave(phase/48),u=6*breath;art=`<path d="M96 682H182L202 673V700L182 682" fill="none" stroke="${accent}" stroke-width="5"/><path d="M${123-u} 665L${108-u} 675l15 7M${188+u} 665l15 10-15 7" fill="none" stroke="${red}" stroke-width="4"/><circle cx="269" cy="680" r="${10+8*breath}" fill="none" stroke="${accent}" stroke-width="3"/><text x="298" y="686" fill="${ink}" font-size="17">RESPIRA · SOSTÉN</text>`;
 }else if(kind==='openbook31'||kind==='thread31'){
  title=kind==='openbook31'?'VISTA SUPERIOR · ABRE EL PECHO':'VISTA SUPERIOR · PASA EL BRAZO Y ABRE';
  note=kind==='openbook31'?'Las rodillas siguen juntas; gira el pecho sin forzar el cuello.':'Sostén la pelvis; el brazo libre pasa bajo el torso y vuelve a abrir.';
  const u=kind==='openbook31'?d.openBook31:d.thread31,th=Math.PI*(u??a);
  art=`<path d="M155 657V698" stroke="${ink}" stroke-width="20" stroke-linecap="round"/><circle cx="155" cy="648" r="9" fill="#edc5ad"/><path d="M155 665L211 665M155 666L${155+56*Math.cos(th)} ${666-20*Math.sin(th)}" stroke="${red}" stroke-width="8" stroke-linecap="round"/><path d="M112 653Q155 625 207 653" fill="none" stroke="${accent}" stroke-width="3" stroke-dasharray="5 4"/><path d="M142 702L137 717M168 702L173 717" stroke="${ink}" stroke-width="8"/><text x="258" y="682" font-size="17" fill="${ink}">PELVIS ESTABLE</text>`;
 }else if(kind==='toes31'){
  title='PIERNAS · PUNTA LATERAL SIN MOVER LAS MANOS';note='La punta sale al lado y vuelve; mantén la cadera a la misma altura.';
  const u=d.toeSide31??a;art=`<circle cx="94" cy="665" r="9" fill="#edc5ad"/><path d="M107 672L164 685L220 697M116 674V701H130" fill="none" stroke="${ink}" stroke-width="9"/><path d="M164 685L${197+25*u} ${692-8*u}L${220+31*u} 699" fill="none" stroke="${red}" stroke-width="7"/><path d="M260 656V692M290 656L${290+38*u} 692" fill="none" stroke="${accent}" stroke-width="5"/><text x="353" y="684" font-size="17" fill="${ink}">IDA Y VUELTA</text>`;
 }else if(kind==='cross31'){
  title='APOYO CRUZADO · VISTA DE LAS HUELLAS';note='Apoya todo el pie; sube con esa pierna y baja sin impulso.';
  const u=d.cross31??a;art=`<rect x="92" y="651" width="154" height="49" fill="#173746" stroke="#648491"/><ellipse cx="203" cy="676" rx="10" ry="19" fill="${red}"/><ellipse cx="${129+54*u}" cy="${710-35*u}" rx="10" ry="19" fill="${ink}"/><path d="M134 706Q147 680 188 676" fill="none" stroke="${accent}" stroke-width="3" stroke-dasharray="4 3"/><text x="286" y="682" font-size="17" fill="${ink}">PIE ENTERO EN LA PLATAFORMA</text>`;
 }else if(kind==='squat31'){
  title='DE LADO · RODILLAS Y TALONES';note='Baja caderas y rodillas juntas; conserva el talón y el apoyo de la carga.';
  const u=d.squatAmount31??a;art=`<path d="M99 704H230" stroke="#638592" stroke-width="3"/><path d="M136 ${654+14*u}L${160-23*u} ${676+9*u}L186 679L180 700H200" fill="none" stroke="${ink}" stroke-width="8" stroke-linecap="round"/><path d="M213 660V693m-5-6 5 6 5-6" stroke="${accent}" stroke-width="3" fill="none"/><text x="277" y="683" font-size="17" fill="${ink}">TALÓN APOYADO</text>`;
 }else if(kind==='pelvis31'){
  title='PUENTE · LA CARGA SE SUJETA SOBRE LA PELVIS';note='Sube la pelvis con el abdomen activo; la cabeza permanece apoyada.';
  art=`<circle cx="100" cy="692" r="8" fill="#edc5ad"/><path d="M110 692L165 ${695-24*a}L191 669L211 701H196" fill="none" stroke="${ink}" stroke-width="9"/><path d="M155 ${686-24*a}H175" stroke="${red}" stroke-width="11"/><path d="M238 698V664m-6 8 6-8 6 8" stroke="${accent}" stroke-width="3" fill="none"/>`;
 }else if(kind==='ham31'){
  title='RODILLA · EL MUSLO PERMANECE ESTABLE';note='Acerca el talón hacia atrás; no balancees la pelvis ni arquees la espalda.';
  const th=Math.PI/2+1.4*a;art=`<path d="M160 654V678L${160+32*Math.cos(th)} ${678+32*Math.sin(th)}" fill="none" stroke="${ink}" stroke-width="9" stroke-linecap="round"/><circle cx="160" cy="678" r="5" fill="${red}"/><path d="M197 686Q187 667 170 653" fill="none" stroke="${accent}" stroke-width="3"/>`;
 }else if(kind==='calf31'){
  title='ESTIRAMIENTO · TALÓN TRASERO APOYADO';note='Sostén sin rebotes; la pierna posterior queda larga y las manos al frente.';
  art=`<path d="M99 705H232M195 650V700" stroke="#648491" stroke-width="3"/><path d="M153 652L149 674L113 701H130M149 674L176 681L176 701H194M156 655L190 661" fill="none" stroke="${ink}" stroke-width="7"/><circle cx="155" cy="642" r="8" fill="#edc5ad"/><circle cx="118" cy="701" r="${8+4*a}" fill="none" stroke="${accent}" stroke-width="3"/>`;
 }else if(kind==='press31'){
  title='PRESS · ASA A LA ALTURA DEL PECHO';note='Empuja y regresa sin despegar el tronco; evita encoger los hombros.';
  art=`<path d="M116 652V698H148V718M102 652V700" fill="none" stroke="${ink}" stroke-width="9"/><circle cx="116" cy="642" r="9" fill="#edc5ad"/><path d="M118 659L${127+18*a} ${682-18*a}L${139+33*a} 663" fill="none" stroke="${red}" stroke-width="7"/><path d="M192 664h37m-6-5 6 5-6 5" fill="none" stroke="${accent}" stroke-width="3"/>`;
 }else if(kind==='crunch31'){
  title='ABDOMEN · ACERCA COSTILLAS Y PELVIS';note='La pelvis queda estable; la cuerda acompaña la flexión, sin tirar del cuello.';
  art=`<path d="M${128+21*a} ${650+14*a}Q${129+25*a} 674 137 685L150 705H112" fill="none" stroke="${ink}" stroke-width="9"/><circle cx="${122+21*a}" cy="${639+14*a}" r="8" fill="#edc5ad"/><path d="M180 647Q205 667 183 684m-1-7 1 7 7-3" fill="none" stroke="${accent}" stroke-width="3"/>`;
 }else return null;
 return panel(title,note,art,accent,kind);
}
function wrapped(text,max=44,count=3){const words=text.split(/\s+/),rows=[''];for(const word of words){const i=rows.length-1;if(rows[i]&&(rows[i]+' '+word).length>max)rows.push(word);else rows[i]+=(rows[i]?' ':'')+word;}while(rows.length<count)rows.push('');if(rows.length>count)throw Error('Explicación demasiado larga para el detalle: '+text);return rows;}
function reflowInset(markup){
 if(!markup)return '';const textMatches=[...markup.matchAll(/<text\b([^>]*)>([^<]*)<\/text>/g)],title=textMatches.find(m=>/y="642"/.test(m[1])),note=textMatches.find(m=>/y="71[9]|y="720"/.test(m[1]));
 if(!title||!note)return markup;
 let art=markup.replace(/^<g\b[^>]*>/,'').replace(/<\/g>$/,'').replace(/<rect\b[^>]*\/?>/,'').replace(title[0],'').replace(note[0],'');
 art=art.replace(/font-size="1[23]"/g,'font-size="20"');
 const text=(match,y,size,max,count)=>{const fill=match[1].match(/fill="([^"]+)"/)?.[1]||'#b0c6d1';return `<text x="76" y="${y}" font-size="${size}" fill="${fill}" font-family="sans-serif">`+wrapped(match[2],max,count).map((row,i)=>`<tspan x="76" dy="${i?size+3:0}">${row}</tspan>`).join('')+'</text>';};
 return '<g data-detail-layout31="complete"><rect x="58" y="620" width="604" height="288" rx="14" fill="#102431" stroke="#47606e"/>'+text(title,655,23,40,2)+'<g transform="translate(0 50)">'+art+'</g>'+text(note,818,24,44,3)+'</g>';
}
root.Motion31={resolve,configureModel,extras,inset,reflowInset,wrapped,kindOf,version:31};
Motion30.clear();Motion2D18.clearBounds();
})(window);
