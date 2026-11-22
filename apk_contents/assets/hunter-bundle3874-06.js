/* Vistas dibujadas en 2D: se eligen por plano del gesto, no al azar. */
(function(root){'use strict';
const copy=x=>JSON.parse(JSON.stringify(x)),profiles={};
const names={front:'Frontal',rear:'Posterior',top:'Superior',side:'Lateral',lying:'Frontal · de lado'};
function family(id){const key=id.replace(/-(?:control18|control19|pause19|15-19)$/,'').replace(/(?:controlled|interval|hold|pause)$/,'');return key==='abduct'?'standingabduct':key;}
function choose(e,m){
 const id=family(e.id),text=e.name.toLowerCase(),d=m.frames[0].detail;
 let primary='side',reason='Ver apoyos, alineación del tronco y flexión de articulaciones.',inset=null,project=null,mirror=false;
 if(d.topShoulder19||id==='reverse'||id==='contralateral'){
  primary='top';reason='Ver la apertura de ambos brazos y su relación con el tronco.';inset='prone';
  project=id==='reverse'?'prone-t':id==='contralateral'?'prone-alternate':null;
 }else if(d.front19||['shouldertap','plankreach19'].includes(id)){
  primary='top';reason='Ver los apoyos y distinguir el brazo que se mueve.';project=d.front19?null:'plank-top';inset=d.front19?'pushup':null;
 }else if(['wallslide','scapular'].includes(id)||/apertura posterior/.test(text)){
  primary='rear';reason='Ver omóplatos y apertura simétrica sin ocultar un brazo.';
  project=/apertura posterior/.test(text)?'rear-fly':null;inset=id==='wallslide'?'wall':project?'hinge':null;
 }else if(/plancha lateral|abducción lateral|apertura de cadera de lado|rotación torácica de lado/.test(text)){
  primary='lying';reason='Ver el brazo y la pierna superiores, la pelvis y el apoyo inferior.';
 }else if(/elevación lateral|press (?:de hombros|de hombro|alterno sentado|arnold)|press frontal y press lateral|elevación diagonal|sujeción (?:unilateral|bilateral)|separación de rodillas|pasos laterales|paso lateral|pasos cruzados|desplazamiento lateral|patinador|apertura.*(?:sin salto|piernas)|saltos con apertura|sentadilla.*(?:amplia)|zancada lateral|abducción de cadera|inclinación lateral|paso en v|retracción|curl.*lateral|círculos.*brazos|pulsos.*brazos|aperturas de brazos|sostén lateral/.test(text)||['lateralunge','standingabduct','chestopen','hipcircle','seatedpunch','seatedmarch','seatedreach','breathing','neck','wrist','forearmmob','wristmob','fingers','elbowmob','seatedturn','diagonalraise','doubletouch'].includes(id)){
  primary='front';reason='Ver separación entre lados, simetría y desplazamiento lateral.';
 }else if(/(?:flexión de bíceps|curl de bíceps|curl martillo|curl alterno)/.test(text)&&!['concentration','pau-lateralcurl','pau-curlcombo'].includes(id)){
  project='curl-side';reason='Ver el codo fijo y el recorrido del antebrazo.';
 }
 if(primary==='side'){
  mirror=/sentadilla|zancada|bisagra|peso muerto|remo|extensión de tríceps inclinada|pantorrilla en pared|flexores de cadera/.test(text);
 }
 if(['wallcalf','calfstretch','wall','wallplank'].includes(id))mirror=false;
 if(['sumo','dbsumo','squatstep'].includes(id)){primary='front';project='wide-squat';reason='Ver anchura de la postura, rodillas y separación de los pies.';}
 if(id==='pau-standingfly'){primary='front';reason='Ver separación y acercamiento de ambos codos.';}
 if(['pau-raisecombo','pau-armpulses','pau-armfly','pau-armcurl'].includes(id))inset='arms';
 if(['wrist','wristmob','forearmmob','fingers'].includes(id)){inset='hand';reason='La ampliación muestra el pequeño recorrido de la mano y del antebrazo.';}
 if(varyForward(id))inset='reach';
 return {primary,mirror,project,inset,reason,label:names[primary],family:id};
}
function varyForward(id){return id==='seatedreach'||id==='stepreach';}
function install(){
 for(const e of Engine.exercises.filter(e=>e.variant==='base')){
  const m=MotionDB[e.id],v=choose(e,m);profiles[e.id]=v;m.view21=v;
  const frames=m.frames;
  if(['hinge','arms','reach'].includes(v.inset)){
   const pts=frames.flatMap(f=>f.p),xs=pts.map(p=>p[0]),ys=pts.map(p=>p[1]);
   v.insetBounds={minX:Math.min(...xs)-35,maxX:Math.max(...xs)+35,minY:Math.min(...ys)-35,maxY:Math.max(...ys)+30};
  }
  const value=(f,j,k)=>{const all=frames.map(x=>x.p[j][k]),min=Math.min(...all),max=Math.max(...all);return max-min>.01?(f.p[j][k]-min)/(max-min):0;};
  // Se conservan los tiempos y fases originales al reorientar las poses.
  m.frames=frames.map((source,i)=>{
   const f=copy(source),p=f.p;f.detail.view21=v.primary;
   if(['hinge','arms','reach'].includes(v.inset)){f.detail.sidePose21=copy(source.p);f.detail.sideEquipment21=copy(source.equipment);}
   if(v.family==='wallslide')f.equipment=[['wall',[170,120,550,715]]];
   if(v.project==='prone-t'){
    const a=1-value(source,3,0);f.p=[[360,225],[360,295],[360,480],[250,295],[145,295],[470,295],[575,295],[333,585],[330,700],[387,585],[390,700]];
    f.detail={...f.detail,topShoulder19:true,letter19:'T',lift19:a,stage18:a>.5?'ELEVA LOS BRAZOS · FORMA T':'REGRESA CON CONTROL · FORMA T'};
   }else if(v.project==='prone-alternate'){
    const a=value(source,4,1),lift=1-a;
    f.p=[[360,225],[360,295],[360,480],[285,215],[220,125],[430,355],[490,395],[333,585],[315,700],[410,575],[470,680]];
    f.detail.topShoulder19=true;f.detail.letter19='BRAZO Y PIERNA CONTRARIOS';f.detail.lift19=lift;f.detail.alternate21=true;
   }else if(v.project==='rear-fly'){
    const separation=frames.map(x=>Math.abs(x.p[6][0]-x.p[4][0])),lo=Math.min(...separation),hi=Math.max(...separation),a=hi-lo>.01?(Math.abs(p[6][0]-p[4][0])-lo)/(hi-lo):0;
    f.p=[[360,225],[360,295],[360,475],[310-70*a,380-70*a],[315-155*a,460-140*a],[410+70*a,380-70*a],[405+155*a,460-140*a],[330,585],[325,700],[390,585],[395,700]];
    f.detail.lift21=a;
    if(e.seated){f.p[7]=[305,535];f.p[8]=[305,700];f.p[9]=[415,535];f.p[10]=[415,700];f.equipment=[['bench',[275,500,445,705]]];}else f.equipment=[];
   }else if(v.project==='wide-squat'){
    const a=value(source,2,1),shift=v.family==='squatstep'?55*a:0,spread=v.family==='squatstep'?60:115;
    f.p=[[360,270+95*a],[360,320+95*a],[360,480+95*a],[315,397+95*a],[348,400+95*a],[405,397+95*a],[372,400+95*a],[360-spread-shift,585+20*a],[360-spread-shift,700],[360+spread,585+20*a],[360+spread,700]];
    if(v.family==='dbsumo'){f.p[3]=[323,405+95*a];f.p[4]=[360,535+95*a];f.p[5]=[397,405+95*a];f.p[6]=[360,535+95*a];f.loads=[copy(f.p[4])];}
   }else if(v.project==='curl-side'){
    const a=1-value(source,4,1),c=1-value(source,6,1),x=p[1][0];
    p[3]=[x+8,420];p[4]=[x+8+58*a,514-152*a];p[5]=[x-15,417];p[6]=[x-15+58*c,511-152*c];
    if(!e.seated){p[7]=[x+6,585];p[8]=[x+12,700];p[9]=[x-12,585];p[10]=[x-12,700];}
   }else if(v.project==='plank-top'){
    const a=1-value(source,4,1),c=1-value(source,6,1),reach=v.family==='plankreach19';
    f.p=[[360,210],[360,280],[360,450],[310+35*a,355-(reach?110:50)*a],[305+(reach?0:95)*a,425-(reach?275:130)*a],[410-35*c,355-(reach?110:50)*c],[415-(reach?0:95)*c,425-(reach?275:130)*c],[330,565],[315,700],[390,565],[405,700]];
    f.detail.front19=true;f.detail.stage18=reach?'ALCANZA UN BRAZO · APOYO ESTABLE':'TOCA EL HOMBRO · REPITE AL OTRO LADO';
   }
   // La carga se dibuja donde está la mano que la sujeta, también en el banco inclinado.
   if(source.loads.length&&e.id!=='dbbridge'&&!/goblet|goblethold|sumodead|dbsumo/.test(v.family)){
    if(source.loads.length===2)f.loads=[copy(f.p[4]),copy(f.p[6])];
    else if(/singleoverhead/.test(v.family))f.loads=[copy(f.p[4])];
    else if(!/doublefront|singlebridge|hipthrust/.test(v.family)){
     const q=source.loads[0],distance=j=>Math.hypot(q[0]-source.p[j][0],q[1]-source.p[j][1]);f.loads=[copy(f.p[distance(4)<=distance(6)?4:6])];
    }
   }
   return f;
  });
 }
 for(const e of Engine.exercises){const base=e.baseId||e.id;e.view21=profiles[base];}
}
function get(id){const e=Engine.byId(id);return profiles[e.baseId||id];}
install();root.Viewpoints21={get,profiles,choose,names};
})(window);

;
/* Correcciones del rig por patrón. Coordenadas y apoyos en un único plano. */
(function(root){'use strict';
const cp=x=>JSON.parse(JSON.stringify(x)),mix=(a,b,t)=>a.map((x,i)=>x+(b[i]-x)*t),add=(a,b)=>a.map((x,i)=>x+b[i]),unit=v=>{const n=Math.hypot(...v)||1;return v.map(x=>x/n);},sub=(a,b)=>a.map((x,i)=>x-b[i]);
const wave=t=>(1-Math.cos(t*Math.PI*2))/2;
function ik(a,b,length=155,side=1){const v=sub(b,a),d=Math.max(1,Math.hypot(...v)),mid=mix(a,b,.5),height=Math.sqrt(Math.max(0,length*length-Math.min(d,length*2)**2/4));return [mid[0]+side*v[1]/d*height,mid[1]-side*v[0]/d*height];}
function standing(){return {p:[[350,200],[350,260],[350,425],[356,370],[360,465],[338,370],[340,465],[357,562],[357,704],[342,562],[342,704]],loads:[],equipment:[],detail:{floor23:715}};}
function amplitude(frames,index,axis){const values=frames.map(f=>f.p[index][axis]),lo=Math.min(...values),hi=Math.max(...values);return (f,i)=>hi-lo>3?(f.p[index][axis]-lo)/(hi-lo):wave(i/48);}
function legsStraight(f){for(const [k,a] of [[7,8],[9,10]])f.p[k]=mix(f.p[2],f.p[a],.5);return f;}
function march(a,b=0){const f=standing(),p=f.p;for(const [k,z,u,offset]of [[7,8,a,7],[9,10,b,-8]]){const hip=[350+offset,425],angle=u*1.42;p[k]=[hip[0]+145*Math.sin(angle),hip[1]+145*Math.cos(angle)];p[z]=[p[k][0]+10*u,p[k][1]+140];}p[3]=[350+42*b-25*a,365];p[4]=[350+78*b-35*a,450-50*b];p[5]=[338+42*a-25*b,367];p[6]=[338+78*a-35*b,452-50*a];return f;}
function heelBack(t){const a=Math.max(0,Math.sin(t*6.283)),b=Math.max(0,-Math.sin(t*6.283)),f=march(0,0);for(const [k,z,u]of [[7,8,a],[9,10,b]]){f.p[z]=[f.p[k][0]-100*u,f.p[k][1]+140-110*u];}return f;}
function reverseCrunch(a){const f=standing();f.p=[[160,555],[220,570],[410-30*a,565-35*a],[270,600],[335,620],[257,587],[322,607],[450-75*a,430-30*a],[575-95*a,435-30*a],[438-75*a,418-30*a],[563-95*a,423-30*a]];f.detail.floor23=640;return f;}
function climber(t){const f=standing(),p=f.p;p[0]=[195,420];p[1]=[250,440];p[2]=[430,495];p[3]=[250,530];p[4]=[250,620];p[5]=[237,518];p[6]=[237,608];p[8]=[690,605];p[10]=[677,593];p[7]=mix(p[2],p[8],.5);p[9]=mix(p[2],p[10],.5);for(const [k,z,u,o]of [[7,8,Math.max(0,Math.sin(t*6.283)),0],[9,10,Math.max(0,-Math.sin(t*6.283)),-12]]){p[k]=mix(p[k],[330,540+o],u);p[z]=mix(p[z],[450,590+o],u);}f.detail.floor23=637;return f;}
function lunge(a,reverse=false,weighted=false){const f=standing(),p=f.p,hip=[360,425+135*a],front=[445,704],rear=[reverse?350-145*a:205,692];p[2]=hip;p[1]=[350,hip[1]-167];p[0]=[350,hip[1]-227];p[7]=ik(hip,front,158,1);p[8]=front;p[9]=ik(hip,rear,158,1);p[10]=rear;p[3]=[350,hip[1]-62];p[4]=[350,hip[1]+38];p[5]=[338,hip[1]-60];p[6]=[338,hip[1]+40];f.detail.rearToe23=10;f.detail.floor23=715;if(weighted)f.loads=[cp(p[4]),cp(p[6])];return f;}
function sideLeg(a,clam=false){const f=standing();f.p=[[150,515],[215,515],[395,515],[195,555],[155,535],[295,495],[389,509],[550,540],[705,540],[395+155*Math.cos(a*.65),490-155*Math.sin(a*.65)],[395+310*Math.cos(a*.65),490-310*Math.sin(a*.65)]];
 if(clam){f.p[7]=[530,590];f.p[8]=[650,560];f.p[9]=[530,575-75*a];f.p[10]=[650,560];}
 f.detail.floor23=567;return f;}
function sidePlank(full=false,a=1,lift=false){const f=sideLeg(0),hip=[405,520+(lift?55*(1-a):0)],shoulder=[235,465],foot=[705,595];f.p[0]=[175,445];f.p[1]=shoulder;f.p[2]=hip;f.p[3]=[225,595];f.p[4]=[130,595];f.p[5]=[315,480];f.p[6]=[405,514];
 if(full){f.p[7]=mix(hip,foot,.53);f.p[8]=foot;f.p[9]=add(f.p[7],[0,-12]);f.p[10]=add(foot,[0,-12]);}
 else{f.p[7]=[545,595];f.p[8]=[635,525];f.p[9]=[545,582];f.p[10]=[635,512];}
 f.detail.floor23=611;return f;}
function towel(){const f=standing();f.p=[[230,260],[240,325],[270,510],[305,370],[360,435],[293,378],[349,444],[422,515],[575,515],[422,502],[575,502]];f.detail.floor23=535;f.detail.towel23=true;return f;}
function boxing(t){const f=standing(),p=f.p,a=wave(t),other=wave(t+.5);p[7]=[395,565];p[8]=[425,704];p[9]=[305,565];p[10]=[290,704];p[3]=[410+50*a,315-20*a];p[4]=[385+180*a,282];p[5]=[395+50*other,320-20*other];p[6]=[374+180*other,295];f.detail.floor23=715;return f;}
function cat(a){const f=standing();f.p=[[185,390+22*a],[245,420],[435,420],[245,510],[245,620],[232,510],[232,620],[435,620],[555,620],[422,608],[542,608]];f.detail.spineCurve23=38*(2*a-1);f.detail.floor23=635;return f;}
function topTap(t,reach=false){const f=standing(),a=Math.max(0,Math.sin(t*Math.PI*2)),b=Math.max(0,-Math.sin(t*Math.PI*2));f.p=[[360,195],[360,263],[360,450],[303+35*a,267-45*a],[293+(reach?-12:102)*a,220+(reach?-80:45)*a],[417-35*b,267-45*b],[427-(reach?-12:102)*b,220+(reach?-80:45)*b],[334,565],[319,705],[386,565],[401,705]];f.detail.front19=true;f.detail.support23=true;f.detail.stage18=reach?'ALCANZA · MANTÉN EL APOYO':'TOCA EL HOMBRO CONTRARIO';return f;}
function contralateral(t){const f=standing(),a=Math.max(0,Math.sin(t*Math.PI*2)),b=Math.max(0,-Math.sin(t*Math.PI*2));f.p=[[360,210],[360,280],[360,460],[305,203],[293,110],[415,203],[427,110],[333,580],[328,700],[387,580],[392,700]];f.detail.topShoulder19=true;f.detail.letter19='BRAZO Y PIERNA CONTRARIOS';f.detail.lift19=Math.max(a,b);f.detail.opposite23=[a,b];f.detail.stage18=a>.25?'BRAZO IZQUIERDO · PIERNA DERECHA':b>.25?'BRAZO DERECHO · PIERNA IZQUIERDA':'REGRESA AL SUELO CON CONTROL';return f;}
const reviews=[],aliases={splitpause:'split',split:'split',reverselunge:'reverselunge',dblunge:'reverselunge',dbsplit:'split',sideleg:'sideleg',clam:'clam',sideplank:'sideplank',contralateral:'contralateral'};
function install(){
 for(const e of Engine.exercises.filter(e=>e.variant==='base'&&!e.pack23)){
  const m=MotionDB[e.id],v=m.view21,old=cp(m.frames),family=v.family,range=amplitude(old,2,1);let action='Perspectiva y cuatro fases conservadas; se aplican los ejes compartidos.',changed=false;
  const lunges=['split','reverselunge','dblunge','dbsplit','splitpause'];
  const marches=['march','fastmarch','slowmarch','kneeliftcardio','standingcore','highknees','dbmarch','walk','farmerwalk','linewalk','frontbackstep','fastfeet','jog','heeldig','heelback'];
  const boxes=['box','boxinterval','boxupper','shadowcombo'];
  m.frames=old.map((raw,i)=>{let f=cp(raw),t=i/48,a=range(raw,i);
   if(lunges.includes(family)){f=lunge(a,['reverselunge','dblunge'].includes(family),e.requirements.includes('db'));action='Zancada en perfil con longitud de muslo y pierna, rodilla anterior y apoyo de puntas posterior.';changed=true;}
   else if(marches.includes(family)){const high=['standingcore','kneeliftcardio','highknees','dbmarch'].includes(family),amplitude23=high?1:.45;f=family==='heelback'?heelBack(t):march(Math.max(0,Math.sin(t*Math.PI*2))*amplitude23,Math.max(0,-Math.sin(t*Math.PI*2))*amplitude23);if(e.requirements.includes('db'))f.loads=[cp(f.p[4]),cp(f.p[6])];action='Rodilla hacia delante o talón hacia atrás según el gesto; extremidades en el plano lateral.';changed=true;}
   else if(boxes.includes(family)){f=boxing(t);action='Guardia y puños delante del rostro, apoyo estable sin cruce imposible de las piernas.';changed=true;}
   else if(['sideleg','clam'].includes(family)){const getter=amplitude(old,10,1);f=sideLeg(getter(raw,i),family==='clam');action='Piernas superiores e inferiores separadas, pelvis estable y cabeza apoyada.';changed=true;}
   else if(family==='sideplank'||/sideplank/.test(e.id)){f=sidePlank(e.id!=='sideplank',a,e.id==='sideplank-lift19');action='Apoyo de codo, pelvis y piernas alineados; rodillas solo en la variante con rodillas.';changed=true;}
   else if(family==='towelrow'){f=towel();action='Remo con toalla sentado en el suelo, dos piernas extendidas y toalla alrededor de los pies.';changed=true;}
   else if(family==='cat'){f=cat(wave(t));action='Curvatura dorsal visible, muñecas bajo hombros y rodillas bajo caderas.';changed=true;}
   else if(family==='reversecrunch'){f=reverseCrunch(wave(t));action='Elevación corta de pelvis con rodillas flexionadas y brazos apoyados en el suelo.';changed=true;}
   else if(family==='mountain'){f=climber(t);action='Escalador con manos fijas y recogida alterna de rodilla, manteniendo el tronco estable.';changed=true;}
   else if(family==='seatedpunch'){f=boxing(t);f.p[2]=[350,490];f.p[7]=[460,490];f.p[8]=[460,704];f.p[9]=[445,478];f.p[10]=[445,692];f.equipment=[['bench',[285,515,440,715]]];v.primary='side';v.label='Lateral';action='Golpe sentado hacia delante en perfil con rodillas flexionadas y pies apoyados.';changed=true;}
   else if(family==='chestopen'){f=standing();f.p[0]=[360,205];f.p[1]=[360,270];f.p[2]=[360,435];for(const [el,hand,sign]of [[3,4,-1],[5,6,1]]){f.p[el]=[360+sign*(52+65*wave(t)),373-60*wave(t)];f.p[hand]=[360+sign*(52+140*wave(t)),478-160*wave(t)];}f.p[7]=[330,565];f.p[8]=[330,704];f.p[9]=[390,565];f.p[10]=[390,704];action='Apertura frontal visible y simétrica de brazos, con el tronco estable.';changed=true;}
   else if(family==='lungeknee'){if(t<.5){const u=Math.sin(t*Math.PI*2);f=lunge(u,true);f.p[8]=[357,704];f.p[7]=ik(f.p[2],f.p[8],158,1);f.p[10]=[342-145*u,692];f.p[9]=ik(f.p[2],f.p[10],158,1);}else{f=march(0,Math.sin((t-.5)*Math.PI*2));}action='Zancada posterior seguida por elevación de la misma rodilla al frente.';changed=true;}
   else if(family==='kneelingplank'){f.p[7]=[f.p[2][0]+115,620];f.p[8]=[f.p[7][0]+110,565];f.p[9]=[f.p[7][0]-12,608];f.p[10]=[f.p[8][0]-12,553];f.detail.floor23=637;action='Plancha con apoyo de rodillas visible y pies elevados, sin confundirla con plancha completa.';changed=true;}
   else if(['shouldertap','plankreach19'].includes(family)){f=topTap(t,family==='plankreach19');action='Palmas delante de los hombros y brazo móvil distinguible; detalle lateral de los apoyos.';changed=true;}
   else if(family==='contralateral'){f=contralateral(t);action='Se alterna brazo y pierna contrarios con detalle lateral sincronizado.';changed=true;}
   else if(['plank','pushup','closepush','decline19'].includes(family)||e.id==='pushup-control19'){legsStraight(f);action='Rodillas extendidas entre cadera y tobillos, sin quiebro de la plancha.';changed=true;}
   if(changed)f.detail={...raw.detail,...f.detail};
   // La fase de las variantes originales se conserva cuando había pausa o cadencia.
   if(raw.detail.stage18&&family!=='contralateral')f.detail.stage18=raw.detail.stage18;
   f.detail.reviewed23=true;f.detail.family23=family;return f;
  });
  if(['shoulder','hipcircle','scapular','neck','wrist','wristmob','forearmmob','fingers'].includes(family))m.hold=false;
  if(family==='shoulder'){v.inset='shoulder23';action='Detalle ampliado de elevación y círculos de hombro.';changed=true;}
  if(family==='cat'){v.inset='spine23';changed=true;}
  if(family==='towelrow'){v.inset='towel23';changed=true;}
  if(family==='contralateral')v.inset='opposite23';
  if(family==='seatedturn')v.inset='rotation23';
  if(['seatedcurl','altseatedcurl','front-seated-reference'].includes(family)){v.primary='front';v.label='Frontal';v.mirror=false;action='Rostro y torso frontales para la postura sentada con piernas separadas.';changed=true;}
  if(['shouldertap','plankreach19'].includes(family))v.inset='support23';
  if(m.hold&&!v.inset)v.inset='hold23';
  if(['hipcircle','scapular','neck'].includes(family)&&!v.inset)v.inset='mobility23';
  if(v.primary==='side'&&['chair','squat','goblet','hinge','rdl','row','bentrow','sumodead','singlehinge','tricep','latstretch','suitdead','single-squat19','single-rdl19','singlehinge-free19'].includes(family)){const bent=m.frames.slice().sort((a,b)=>Math.abs(b.p[2][0]-b.p[1][0])-Math.abs(a.p[2][0]-a.p[1][0]))[0];m.facing23=bent.p[2][0]>bent.p[1][0]?'left':'right';}
  for(const f of m.frames)f.detail.view21=v.primary;
  reviews.push({id:e.id,name:e.name,action,changed,view:v.primary,inset:v.inset,hold:!!m.hold});
 }
}
function inset(kind,f,phase,accent){
 const t=((phase%48)+48)%48/48,a=wave(t),ink='#dceaf0',red='#fb927d';let art='',title='',note='';
 if(kind==='shoulder23'){title='AMPLIACIÓN · CÍRCULO DE HOMBROS';note='Sube, lleva atrás y baja · los brazos permanecen relajados';art=`<circle cx="151" cy="679" r="23" fill="none" stroke="#47606e" stroke-width="2"/><path d="M112 698V665Q151 ${644-8*a} 191 665V698" fill="none" stroke="${ink}" stroke-width="11"/><circle cx="${151+23*Math.cos(t*Math.PI*2)}" cy="${679+23*Math.sin(t*Math.PI*2)}" r="7" fill="${red}"/>`;}
 else if(kind==='spine23'){title='DE LADO · REDONDEA Y REGRESA';note='Movimiento suave de columna · muñecas y rodillas permanecen apoyadas';art=`<path d="M110 683Q160 ${676-38*(2*a-1)} 211 683M110 683V703M211 683V703" fill="none" stroke="${ink}" stroke-width="9"/><circle cx="93" cy="${679+7*a}" r="11" fill="#edc5ad"/>`;}
 else if(kind==='towel23'){title='AMPLIACIÓN · TENSIÓN DE LA TOALLA';note='Tira hacia ti sin inclinar el tronco ni soltar los pies';art=`<path d="M95 683H151L192 697L202 678L151 682" fill="none" stroke="${accent}" stroke-width="4"/><path d="M102 668L89 676l12 4M196 675l12 6-10 4" fill="none" stroke="${red}" stroke-width="3"/>`;}
 else if(kind==='opposite23'){const [left,right]=f.detail.opposite23||[a,1-a];title='DE LADO · BRAZO Y PIERNA OPUESTOS';note=left>.25?'Eleva brazo izquierdo y pierna derecha':right>.25?'Eleva brazo derecho y pierna izquierda':'Vuelve con control antes de alternar';art=`<circle cx="93" cy="676" r="9" fill="#edc5ad"/><path d="M110 681H182" stroke="${ink}" stroke-width="12"/><path d="M113 684L77 ${684-18*Math.max(left,right)}M176 684L224 ${684-18*Math.max(left,right)}" stroke="${red}" stroke-width="7"/><path d="M75 704H228" stroke="#688594"/>`;}
 else if(kind==='support23'){title='DE LADO · APOYOS Y PELVIS';note='Mano de apoyo bajo el hombro · evita girar la cadera';art=`<circle cx="91" cy="667" r="8" fill="#edc5ad"/><path d="M104 673L165 685L219 698M110 676L110 702L124 702" stroke="${ink}" stroke-width="8" fill="none"/><path d="M145 678L${130-18*a} ${670-18*a}" stroke="${red}" stroke-width="6"/>`;}
 else if(kind==='pushDepth23'){const amount=f.detail.pushDepth23??a;title='DE LADO · DESCENSO Y APOYO';note='La vista superior muestra las manos · aquí se ve el descenso del pecho';art=`<circle cx="99" cy="${663+18*amount}" r="8" fill="#edc5ad"/><path d="M110 ${669+18*amount}L166 ${681+9*amount}L218 699M119 ${672+18*amount}L${119-13*amount} 691L137 702" stroke="${ink}" stroke-width="8" fill="none"/><path d="M82 705H231" stroke="#688594"/>`;}
 else if(kind==='floorFly23'){title='DE LADO · RESPALDO Y CODO';note='Espalda apoyada · conserva los codos suaves y limita la apertura al suelo';art=`<path d="M85 704H230" stroke="#688594"/><circle cx="97" cy="694" r="8" fill="#edc5ad"/><path d="M110 698H170L194 677L214 700M123 696L${140-8*a} ${690-10*a}L${148+2*a} ${678-20*a}" stroke="${ink}" stroke-width="8" fill="none"/>`;}
 else if(kind==='frog23'){title='PIERNAS · PLANTAS JUNTAS';note='Rodillas abiertas en un recorrido cómodo · eleva la pelvis sin arquear la espalda';art=`<path d="M156 655L${112-5*a} 681L156 699M156 655L${200+5*a} 681L156 699" fill="none" stroke="${ink}" stroke-width="10"/><ellipse cx="150" cy="699" rx="5" ry="10" fill="${accent}"/><ellipse cx="162" cy="699" rx="5" ry="10" fill="${accent}"/>`;}
 else if(kind==='grip23'){const param=f.detail.param23||{},turn=param.pronate?Math.cos(t*Math.PI*2):param.supinate?1:param.neutral?0:-1,spread=param.close?25:param.wide?58:40;title=param.pronate?'AGARRE · GIRA ANTES DEL RETORNO':'AGARRE · POSICIÓN DE LAS MANOS';note=param.pronate?'Sube con palmas arriba · baja con palmas abajo':param.supinate?'Palmas hacia ti · conserva las muñecas alineadas':param.neutral?'Palmas enfrentadas · agarre neutro':param.close?'Agarre cercano · controla los codos':'Agarre indicado · no dobles las muñecas para sostener la carga';art=`<path d="M${154-spread} 703V678M${154+spread} 703V678" stroke="${ink}" stroke-width="11"/><path d="M85 667H225" stroke="${accent}" stroke-width="5"/><ellipse cx="${154-spread}" cy="674" rx="10" ry="${5+6*Math.abs(turn)}" fill="${turn>0?'#edc5ad':'#d7ab92'}"/><ellipse cx="${154+spread}" cy="674" rx="10" ry="${5+6*Math.abs(turn)}" fill="${turn>0?'#edc5ad':'#d7ab92'}"/>`;}
 else if(kind==='rotation23'){title='DESDE ARRIBA · GIRO PEQUEÑO';note='Pelvis estable · gira el pecho en un recorrido cómodo, sin forzarlo';const th=.38*Math.sin(t*6.283);art=`<path d="M132 701H177" stroke="#47606e" stroke-width="9"/><path d="M${154-32*Math.cos(th)} ${675-32*Math.sin(th)}L${154+32*Math.cos(th)} ${675+32*Math.sin(th)}" stroke="${ink}" stroke-width="10"/><circle cx="154" cy="675" r="8" fill="${red}"/>`;}
 else if(kind==='pallof23'){title='DESDE ARRIBA · RESISTE EL GIRO';note='Extiende al frente · la polea tira desde un lado y el torso permanece estable';art=`<path d="M124 691H184M154 691V704M135 691L154 ${677-19*a}M174 691L154 ${677-19*a}" stroke="${ink}" stroke-width="8" fill="none"/><path d="M80 671L154 ${677-19*a}" stroke="${accent}" stroke-width="3"/><path d="M104 665l-12 6 12 6" stroke="${red}" stroke-width="3" fill="none"/>`;}
 else if(kind==='hip23'){title='PELVIS · APERTURA PEQUEÑA';note='Usa el apoyo · abre y regresa sin girar la rodilla que soporta el peso';art=`<path d="M153 654V680L148 703M153 680L${201+8*a} ${684-8*a}" stroke="${ink}" stroke-width="9" fill="none"/><ellipse cx="154" cy="680" rx="${22-6*a}" ry="9" fill="none" stroke="${red}" stroke-width="4"/>`;}
 else if(kind==='scissors23'){const s=Math.sin(t*Math.PI*2);title='VISTA FRONTAL · CRUCE ALTERNADO';note='Piernas alargadas · cambia qué pierna pasa arriba sin mover la pelvis';art=`<path d="M147 701L${147+27*s} 654M165 701L${165-27*s} 654" stroke="${ink}" stroke-width="9" fill="none"/><circle cx="147" cy="701" r="5" fill="${red}"/><circle cx="165" cy="701" r="5" fill="${accent}"/>`;}
 else if(kind==='mobility23'){
  const family=f.detail.family23;title='AMPLIACIÓN · RECORRIDO PEQUEÑO';note='Recorrido cómodo y controlado · observa la articulación señalada';
  if(family==='neck'){title='CUELLO · GIRO SUAVE';note='Gira sin llevar la cabeza atrás · hombros quietos';art=`<path d="M113 702L125 684H183L195 702M154 685V676" stroke="${ink}" stroke-width="9" fill="none"/><ellipse cx="154" cy="660" rx="17" ry="20" fill="#edc5ad"/><circle cx="${154+11*Math.sin(t*Math.PI*2)}" cy="660" r="3" fill="#17344a"/>`;}
  else if(family==='hipcircle'){title='PELVIS · CÍRCULO CONTROLADO';note='Traza un círculo pequeño sin perder los apoyos';art=`<ellipse cx="156" cy="674" rx="35" ry="16" fill="none" stroke="#47606e"/><path d="M${139+8*Math.cos(t*6.283)} ${672+5*Math.sin(t*6.283)}L132 703M${173+8*Math.cos(t*6.283)} ${672+5*Math.sin(t*6.283)}L183 703" stroke="${ink}" stroke-width="9"/><circle cx="${156+35*Math.cos(t*6.283)}" cy="${674+16*Math.sin(t*6.283)}" r="6" fill="${red}"/>`;}
  else{title='ESCÁPULAS · ACERCA Y SUELTA';note='Aproxima los omóplatos sin levantar los hombros';art=`<path d="M156 653V703" stroke="#47606e" stroke-width="4"/><path d="M${130+12*a} 660L${133+12*a} 688M${182-12*a} 660L${179-12*a} 688" stroke="${ink}" stroke-width="10"/><path d="M117 676h18m-5-5 5 5-5 5M195 676h-18m5-5-5 5 5 5" stroke="${red}" stroke-width="3" fill="none"/>`;}
 }
 else if(kind==='hold23'){
  title='ISOMÉTRICO · SOSTÉN, NO SON REPETICIONES';note='La postura se mantiene · respira y finaliza si pierdes la alineación';
  const points=f.p,xs=points.map(p=>p[0]),ys=points.map(p=>p[1]),minX=Math.min(...xs)-10,minY=Math.min(...ys)-20,scale=Math.min(128/(Math.max(...xs)-minX+20),49/(Math.max(...ys)-minY+15)),q=p=>[90+(p[0]-minX)*scale,650+(p[1]-minY)*scale],edges=[[0,1],[1,2],[1,3],[3,4],[1,5],[5,6],[2,7],[7,8],[2,9],[9,10]],path=edges.map(([i,j])=>'M'+q(points[i]).join(',')+'L'+q(points[j]).join(',')).join('');
  art=`<path d="${path}" stroke="${ink}" stroke-width="4" stroke-linecap="round" fill="none"/><circle cx="${q(points[0])[0]}" cy="${q(points[0])[1]}" r="5" fill="#edc5ad"/><circle cx="251" cy="680" r="${11+7*a}" fill="none" stroke="${accent}" stroke-width="3"/><text x="280" y="685" font-size="13" fill="${ink}">RESPIRA Y SOSTÉN</text>`;
 }
 else return null;
 return `<g data-view="side-inset" data-detail23="${kind}"><rect x="58" y="620" width="604" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="74" y="642" font-size="13" fill="${accent}" font-family="sans-serif">${title}</text>${art}<text x="74" y="720" font-size="12" fill="#b0c6d1" font-family="sans-serif">${note}</text></g>`;
}
install();root.Rig2D23={reviews,ik,standing,march,lunge,sideLeg,sidePlank,towel,boxing,cat,topTap,contralateral,inset,wave,cp,mix};
})(window);

;
/* Rigs de los patrones nuevos; máquinas y cargas siguen sus puntos de contacto. */
(function(root){'use strict';
const R=Rig2D23,cp=R.cp,mix=R.mix,ik=R.ik,wave=R.wave;
const move=(f,indices,x,y)=>{for(const k of indices){f.p[k][0]+=x;f.p[k][1]+=y;}return f;};
const n=v=>Number(v).toFixed(2),pt=p=>p.map(n).join(','),stage=(f,a)=>{f.detail.stage18=a>.85?'PUNTO DE CONTROL':a<.15?'POSICIÓN INICIAL':'RECORRIDO CONTROLADO';return f;};
function front(){const f=R.standing();f.p=[[360,205],[360,270],[360,435],[308,373],[308,478],[412,373],[412,478],[330,568],[330,704],[390,568],[390,704]];return f;}
function topPush(a,param){const f=R.topTap(0);f.p[0][1]+=10*a;f.p[4]=param.grip==='diamond'?[350,290]:[288,265];f.p[6]=param.grip==='diamond'?[370,290]:param.stagger?[432,340]:[510,270];f.p[3]=[param.grip==='diamond'?295:255,335+15*a];f.p[5]=[param.grip==='diamond'?425:465,335+15*a];f.detail.pushDepth23=a;return f;}
function curtsy(a){const f=front(),p=f.p;p[2]=[350,435+120*a];p[1]=[350,270+120*a];p[0]=[350,205+120*a];p[8]=[300,704];p[10]=[425-185*a,690];p[7]=[300,565+35*a];p[9]=[395-75*a,560+45*a];for(const [el,ha,x]of [[3,4,308],[5,6,405]]){p[el]=[x,373+120*a];p[ha]=[x,478+120*a];}return f;}
function stepFront(a,param){const f=front(),p=f.p;p[2]=[350,460-110*a];p[1]=[350,295-110*a];p[0]=[350,230-110*a];p[8]=[param.cross?450:310,580];p[7]=ik(p[2],p[8],153,-1);p[10]=[470,704-124*a];p[9]=ik(p[2],p[10],153,1);p[3]=[290,400-110*a];p[4]=[290,510-110*a];p[5]=[410,400-110*a];p[6]=[410,510-110*a];f.equipment=[['step',[param.cross?390:255,606,param.cross?510:370,715]]];return f;}
function squat(a,param={}){const f=R.standing(),p=f.p;p[2]=[350-75*a,425+135*a];p[1]=[350+20*a,260+155*a];p[0]=[350+40*a,200+152*a];p[8]=[355,704];p[10]=[340,704];p[7]=ik(p[2],p[8],155,1);p[9]=ik(p[2],p[10],155,1);p[3]=[p[1][0]+45,p[1][1]+58];p[4]=[p[1][0]+20,p[1][1]+25];p[5]=[p[1][0]+32,p[1][1]+64];p[6]=[p[1][0]+8,p[1][1]+32];if(param.bench)f.equipment=[['bench',[430,570,600,715]]];return f;}
function hinge(a){const f=R.standing(),p=f.p;p[2]=[350-60*a,435+35*a];p[1]=[350+90*a,270+120*a];p[0]=[350+135*a,210+140*a];p[7]=[365,570];p[9]=[350,570];p[8]=[335,704];p[10]=[320,704];for(const [elbow,hand,x]of [[3,4,0],[5,6,-15]]){p[elbow]=[p[1][0]+x,p[1][1]+108];p[hand]=[p[1][0]+x,p[1][1]+208];}return f;}
function push(a,param={}){const f=R.standing(),p=f.p,shift=param.dive?40*a:0;p[1]=[245+shift,435+100*a];p[2]=[430+shift,495+50*a];p[8]=[690,585];p[10]=[677,573];p[7]=mix(p[2],p[8],.52);p[9]=mix(p[2],p[10],.52);p[0]=[190+shift,415+100*a];const palms=[[245,610],[230+(param.stagger?65:0),600]];p[4]=palms[0];p[6]=palms[1];p[3]=ik(p[1],p[4],95,-1);p[5]=ik(p[1],p[6],95,-1);f.detail.floor23=627;
 if(param.kneeDrive){p[7]=mix(p[7],[280,550],a);p[8]=[440,600];}
 if(param.dive){p[1]=[245+135*a,435+55*Math.sin(a*Math.PI)];p[2]=[430+40*a,350+185*a];p[0]=[p[1][0]-55,p[1][1]-20];p[7]=mix(p[2],p[8],.5);p[9]=mix(p[2],p[10],.5);p[3]=ik(p[1],p[4],115,-1);p[5]=ik(p[1],p[6],115,-1);}
 if(param.release){p[4][1]-=14*a;p[6][1]-=14*a;}
 return f;}
function bridge(a,param={}){const f=R.standing();f.p=[[160,550],[220,565],[420,565-115*a],[272,590],[335,615],[263,577],[323,603],[545,485],[600,625],[532,472],[587,613]];f.detail.floor23=644;
 if(param.long){f.p[7]=[590,540];f.p[8]=[705,625];f.p[9]=[577,527];f.p[10]=[692,613];}
 return f;}
function reversePlank(a,param={}){const f=bridge(.7),p=f.p;p[1]=[240,435+55*(1-a)];p[2]=[440,515+45*(1-a)];p[0]=[183,410+55*(1-a)];p[3]=[230,540];p[4]=[210,640];p[5]=[217,527];p[6]=[197,627];p[8]=[710,640];p[10]=[697,627];p[7]=mix(p[2],p[8],.52);p[9]=mix(p[2],p[10],.52);f.detail.floor23=655;if(param.knee){p[7]=[555,455];p[8]=[565,600];}if(param.walk){p[4][0]+=30*a;p[8][0]-=35*a;}return f;}
function hollow(a,param={}){const f=bridge(0),p=f.p;f.p[1]=[235,545-45*a];f.p[0]=[175,525-50*a];f.p[2]=[405,590];p[3]=[205,485-40*a];p[4]=[140,435-25*a];p[5]=[200,475-40*a];p[6]=[130,425-25*a];p[7]=[535,565-85*a];p[8]=[665,540-130*a];p[9]=[524,553-85*a];p[10]=[654,528-130*a];
 if(param.tuck){p[7]=mix([475,470],[535,540],a);p[8]=mix([590,550],[665,490],a);p[9]=add23(p[7],[-12,-12]);p[10]=add23(p[8],[-12,-12]);}
 if(param.vup){p[1]=[300,540-115*a];p[0]=[245,520-115*a];p[4]=[520,455-70*a];p[6]=[509,443-70*a];p[3]=ik(p[1],p[4],120,-1);p[5]=ik(p[1],p[6],120,-1);}
 if(param.rock)move(f,[0,1,2,3,4,5,6,7,8,9,10],0,10*Math.sin(a*Math.PI*2));return f;}
function legraise(a,param={},t=0){const f=bridge(0),p=f.p,hip=p[2],angle=-.1-a*1.35;for(const [k,z,offset,amount]of [[7,8,0,param.flutter?wave(t):a],[9,10,-12,param.flutter?wave(t+.5):a]]){const th=param.flutter?-.12-.35*amount:angle;p[k]=[hip[0]+140*Math.cos(th),hip[1]+140*Math.sin(th)+offset];p[z]=[hip[0]+280*Math.cos(th),hip[1]+280*Math.sin(th)+offset];}if(param.scissors){p[8][1]+=30*Math.sin(t*Math.PI*2);p[10][1]-=30*Math.sin(t*Math.PI*2);}return f;}
function bench(a,param={}){const f=bridge(0),p=f.p;p[0]=[185,param.incline?310:param.decline?460:420];p[1]=[250,param.incline?345:param.decline?445:420];p[2]=[445,param.incline?465:param.decline?400:430];p[7]=[530,520];p[8]=[570,704];p[9]=[517,507];p[10]=[557,692];p[4]=[275,p[1][1]-40-155*a];p[6]=[261,p[1][1]-28-155*a];p[3]=ik(p[1],p[4],108,-1);p[5]=ik(p[1],p[6],108,-1);f.equipment=[[param.incline||param.decline?'incline':'bench',[150,450,460,715]]];f.detail.floor23=720;return f;}
function overhead(a,param={}){const f=front(),p=f.p;if(param.seated){p[2][1]=460;p[7]=[295,565];p[8]=[295,704];p[9]=[425,565];p[10]=[425,704];f.equipment=[['bench',[260,488,460,715]]];}p[4]=[300,290-165*a];p[6]=[420,290-165*a];p[3]=[255+35*a,340-120*a];p[5]=[465-35*a,340-120*a];return f;}
function curl(a,param={}){const f=R.standing(),p=f.p;p[3]=[357,370];p[5]=[342,370];p[4]=[357+85*a,478-175*a];p[6]=[342+85*a,466-175*a];if(param.cross)p[4][0]-=45*a;if(param.preacher){p[2]=[360,490];p[7]=[410,585];p[8]=[445,704];p[9]=[395,573];p[10]=[430,692];f.equipment=[['bench',[300,510,435,715]],['table',[370,385,525,715]]];p[3]=[390,365];p[5]=[377,354];p[4]=[480-40*a,452-92*a];p[6]=[466-40*a,440-92*a];}return f;}
function row(a,param={}){const f=hinge(1),p=f.p;p[4]=mix([p[1][0],p[1][1]+205],[p[2][0]+15,p[2][1]+10],a);p[6]=add23(p[4],[-15,-12]);p[3]=ik(p[1],p[4],112,-1);p[5]=ik(p[1],p[6],112,-1);return f;}
function cableRow(a,param={}){const f=R.standing(),p=f.p;if(param.seated){p[2]=[320,515];p[1]=[315,340];p[0]=[315,280];p[7]=[435,575];p[8]=[555,620];p[9]=[422,562];p[10]=[542,607];f.equipment=[['bench',[260,545,370,715]]];}p[4]=[p[1][0]+195-135*a,p[1][1]+(param.high?40:105)];p[6]=[p[4][0]-12,p[4][1]-12];p[3]=ik(p[1],p[4],115,-1);p[5]=ik(p[1],p[6],115,-1);return f;}
function pulldown(a,param={}){const f=front(),p=f.p;p[2]=[360,465];p[7]=[300,565];p[8]=[300,704];p[9]=[420,565];p[10]=[420,704];p[4]=[param.wide?215:300,125+210*a];p[6]=[param.wide?505:420,125+210*a];p[3]=[270-30*a,220+150*a];p[5]=[450+30*a,220+150*a];f.equipment=[['bench',[260,495,460,715]]];return f;}
function fly(a,param={},gym=true){let f;if(!gym){f=front();f.p[0]=[360,220];f.p[1]=[360,285];f.p[2]=[360,465];f.p[7]=[320,555];f.p[8]=[320,690];f.p[9]=[400,555];f.p[10]=[400,690];f.p[3]=[255+70*a,290];f.p[4]=[165+180*a,280];f.p[5]=[465-70*a,290];f.p[6]=[555-180*a,280];f.detail.supineTop23=true;return f;}
 f=front();const p=f.p,y=param.low?370-85*a:param.high?250+105*a:310;if(param.seated){p[2][1]=475;p[7]=[300,565];p[8]=[300,704];p[9]=[420,565];p[10]=[420,704];f.equipment=[['bench',[260,510,460,715]]];}p[3]=[255+65*a,y+15];p[4]=[180+168*a,y];p[5]=[465-65*a,y+15];p[6]=[540-168*a,y];return f;}
function tricep(a,param={}){const f=R.standing(),p=f.p;p[3]=[365,370];p[5]=[352,358];p[4]=[430-35*a,340+120*a];p[6]=[417-35*a,328+120*a];if(param.overhead){p[3]=[360,208];p[5]=[347,196];p[4]=[320+35*a,285-180*a];p[6]=[307+35*a,273-180*a];}return f;}
function seatedLeg(a,param={},ham=false){const f=R.standing(),p=f.p;p[0]=[280,275];p[1]=[285,338];p[2]=[305,510];p[7]=[460,510];p[9]=[450,497];let angle=ham?-.15+1.35*a:1.45-1.4*a;p[8]=[460+145*Math.cos(angle),510+145*Math.sin(angle)];p[10]=[450+145*Math.cos(angle),497+145*Math.sin(angle)];p[3]=[300,440];p[4]=[330,525];p[5]=[287,428];p[6]=[317,513];f.equipment=[['chair',[220,535,465,715]]];return f;}
function legpress(a){const f=R.standing(),p=f.p;p[0]=[210,350];p[1]=[245,400];p[2]=[320,535];const ankle=[585+85*a,490];p[7]=ik(p[2],ankle,190,1);p[8]=ankle;p[9]=add23(p[7],[-12,-12]);p[10]=add23(ankle,[-12,-12]);p[3]=[245,465];p[4]=[287,548];p[5]=[232,453];p[6]=[274,536];f.equipment=[['incline',[150,435,355,710]]];f.detail.footAxis23=[0,-1];f.detail.plate23=ankle;return f;}
function add23(p,delta){return p.map((v,i)=>v+delta[i]);}
function modelFrame(e,t){const a=wave(t),k=e.recipe23,param=e.param23;let f;
 if(k==='push'){
  if(param.tap){const flex=t<.6?wave(t/.6):0;f=push(flex,param);const tap=t>=.6?wave((t-.6)/.4):0;f.p[6]=mix(f.p[6],[260,440],tap);f.p[5]=ik(f.p[1],f.p[6],105,-1);}
  else if(param.power){const flex=t<.7?wave(t/.7):0;f=push(flex,param);const flight=t>=.7?wave((t-.7)/.3):0;move(f,[0,1,2,3,4,5,6,7,9],0,-35*flight);}
  else f=param.grip==='diamond'||param.stagger||param.assistedOne?topPush(a,param):push(a,param);
 }
 else if(k==='pike'){f=push(a,param);f.p[2]=[425,345+28*a];f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=mix(f.p[2],f.p[10],.5);if(param.bench){f.p[8][1]-=95;f.p[10][1]-=95;f.equipment=[['bench',[620,480,735,715]]];}}
 else if(k==='lunge')f=param.cross?curtsy(a):R.lunge(a,!param.forward,!!param.db);
 else if(k==='squat-lunge')f=t<.5?squat(wave(t*2)):R.lunge(wave((t-.5)*2));
 else if(k==='squat'){f=squat(a,param);if(param.hack){f.p[0]=[275-45*a,245+105*a];f.p[1]=[305-45*a,305+105*a];f.p[2]=[365-45*a,455+105*a];f.p[8]=[535,704];f.p[10]=[520,692];f.p[7]=ik(f.p[2],f.p[8],165,1);f.p[9]=ik(f.p[2],f.p[10],165,1);f.p[4]=[f.p[1][0]+28,f.p[1][1]+30];f.p[6]=add23(f.p[4],[-13,12]);f.p[3]=ik(f.p[1],f.p[4],100,1);f.p[5]=ik(f.p[1],f.p[6],100,1);f.detail.hack23=true;f.equipment=[['incline',[150,300,365,715]]];}if(param.back||param.front){f.p[4]=[f.p[1][0]+24,f.p[1][1]+10];f.p[6]=add23(f.p[4],[-15,12]);}}
 else if(k==='single-squat'){f=squat(a,param);if(param.rear){f.p[9]=[f.p[2][0]-95,f.p[2][1]+80];f.p[10]=[f.p[9][0]-40,f.p[9][1]+80];}else{f.p[10]=[f.p[2][0]+295,f.p[2][1]+30];f.p[9]=mix(f.p[2],f.p[10],.5);}if(param.support){f.equipment.push(['wall',[520,270,530,715]]);f.p[6]=[520,400+30*a];f.p[5]=ik(f.p[1],f.p[6],112,-1);}if(param.db){f.p[4]=[f.p[1][0]+100,f.p[1][1]+30];f.p[3]=ik(f.p[1],f.p[4],100,-1);}}
 else if(k==='lateral-lunge'){f=front();move(f,[0,1,2,3,4,5,6],-80*a,110*a);f.p[8]=[180,704];f.p[10]=[560,704];f.p[7]=ik(f.p[2],f.p[8],165,-1);f.p[9]=mix(f.p[2],f.p[10],.5);}
 else if(k==='step')f=stepFront(a,param);
 else if(k==='bridge'||k==='hipthrust'){f=bridge(a,param);if(k==='hipthrust'){f.p[0]=[185,425];f.p[1]=[250,450];f.p[2]=[420,565-110*a];f.p[3]=[300,490];f.p[4]=[f.p[2][0]-10,f.p[2][1]];f.p[5]=[288,478];f.p[6]=add23(f.p[4],[-12,-12]);f.equipment=[['bench',[150,490,300,644]]];}}
 else if(k==='ham-slide'){f=bridge(.75,param);f.p[8][0]+=110*(1-a);f.p[10][0]+=110*(1-a);f.p[7]=ik(f.p[2],f.p[8],155,-1);f.p[9]=ik(f.p[2],f.p[10],155,-1);if(param.single){f.p[9]=[530,400];f.p[10]=[650,350];}}
 else if(k==='reverse-plank'){f=reversePlank(param.knee?1:a,param.knee?{}:param);if(param.knee){for(const [knee,foot,u,o]of [[7,8,Math.max(0,Math.sin(t*6.283)),0],[9,10,Math.max(0,-Math.sin(t*6.283)),-12]]){f.p[knee]=mix(f.p[knee],[f.p[2][0]+105,f.p[2][1]-75+o],u);f.p[foot]=mix(f.p[foot],[f.p[2][0]+150,f.p[2][1]+70+o],u);}}}
 else if(k==='hollow'){f=hollow(param.rock?.65:a,param.rock?{}:param);if(param.rock){const origin=f.p[2],theta=.12*(2*a-1);f.p=f.p.map(p=>{const x=p[0]-origin[0],y=p[1]-origin[1];return [origin[0]+x*Math.cos(theta)-y*Math.sin(theta),origin[1]+x*Math.sin(theta)+y*Math.cos(theta)];});}}
 else if(k==='legraise')f=legraise(param.scissors?.35:a,param,t);
 else if(k==='knee-tuck'){f=seatedLeg(a);f.equipment=[];f.p[2]=[305,590];f.p[1]=[270,430];f.p[0]=[245,370];f.p[4]=[255,610];f.p[6]=[242,598];f.p[3]=[265,525];f.p[5]=[252,513];f.p[7]=[450-55*a,570-95*a];f.p[8]=[585-120*a,602-90*a];f.p[9]=add23(f.p[7],[-12,-12]);f.p[10]=add23(f.p[8],[-12,-12]);f.detail.floor23=628;}
 else if(k==='side-plank'){f=R.sidePlank(true,1);if(param.knee){f.p[9]=mix(f.p[9],[475,405],a);f.p[10]=mix(f.p[10],[575,450],a);}if(param.thread){f.p[6]=mix(f.p[6],[175,555],a);f.p[5]=ik(f.p[1],f.p[6],112,-1);}}
 else if(k==='plank'||k==='renegade'){f=push(0);if(k==='renegade'){const left=Math.max(0,Math.sin(t*6.283)),right=Math.max(0,-Math.sin(t*6.283));for(const [elbow,hand,u,offset]of [[3,4,left,0],[5,6,right,-12]]){f.p[hand]=mix(f.p[hand],[405,430+offset],u);f.p[elbow]=ik(f.p[1],f.p[hand],108,-1);}if(param.knees){f.p[7]=[495,610];f.p[8]=[620,585];f.p[9]=[482,598];f.p[10]=[607,573];}}if(param.saw){f.p[1]=[245+28*a,490];f.p[0]=[190+28*a,470];f.p[2]=[430+28*a,535];f.p[3]=[260,620];f.p[4]=[160,620];f.p[5]=[245,608];f.p[6]=[145,608];f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=mix(f.p[2],f.p[10],.5);f.detail.floor23=638;}if(param.toe)f.p[10][0]+=45*a;}
 else if(k==='bear'){f=R.cat(.5);f.p[7][1]-=18;f.p[9][1]-=18;delete f.detail.spineCurve23;if(param.tap){f.p[6]=mix(f.p[6],[250,430],a);f.p[5]=ik(f.p[1],f.p[6],105,-1);}if(param.walk){f.p[4][0]+=35*Math.sin(t*Math.PI*2);f.p[8][0]-=35*Math.sin(t*Math.PI*2);}}
 else if(k==='thruster'){const down=t<1/3?wave(t*1.5):t<2/3?1-wave((t-1/3)*1.5):0,press=t<1/3?0:t<2/3?wave((t-1/3)*1.5):1-wave((t-2/3)*1.5);f=squat(down);f.p[4]=[f.p[1][0]+22,f.p[1][1]+20-170*press];f.p[6]=add23(f.p[4],[-15,12]);f.p[3]=ik(f.p[1],f.p[4],110,-1);f.p[5]=ik(f.p[1],f.p[6],110,-1);}
 else if(k==='clean'){f=hinge(1-a);f.p[4]=mix(f.p[4],[350,275],a);f.p[6]=mix(f.p[6],[338,287],a);f.p[3]=ik(f.p[1],f.p[4],110,1);f.p[5]=ik(f.p[1],f.p[6],110,1);}
 else if(k==='hinge'||k==='deadlift'){f=hinge(a);if(param.airplane){f.p[10]=[f.p[2][0]-160*a,704-255*a];f.p[9]=mix(f.p[2],f.p[10],.5);f.p[6]=[515,450];f.p[5]=ik(f.p[1],f.p[6],110,1);f.equipment=[['wall',[540,300,550,715]]];}if(param.wide){const side=hinge(a);f=front();f.p[0][1]+=135*a;f.p[1][1]+=120*a;f.p[2][1]+=40*a;f.p[8]=[215,704];f.p[10]=[505,704];f.p[7]=[230,560+25*a];f.p[9]=[490,560+25*a];f.p[4]=[305,470+155*a];f.p[6]=[415,470+155*a];f.p[3]=mix(f.p[1],f.p[4],.5);f.p[5]=mix(f.p[1],f.p[6],.5);}if(param.floorStart&&!param.wide){f.p[2][1]+=35*a;f.p[4][1]+=55*a;f.p[6][1]+=55*a;f.p[7]=ik(f.p[2],f.p[8],155,1);f.p[9]=ik(f.p[2],f.p[10],155,1);}}
 else if(k==='row'){f=row(a,param);if(param.floorStart){f.p[2]=[300,445];f.p[1]=[455,445];f.p[0]=[510,420];f.p[4]=mix([445,675],[320,450],a);f.p[6]=add23(f.p[4],[-12,-12]);f.p[3]=ik(f.p[1],f.p[4],122,-1);f.p[5]=ik(f.p[1],f.p[6],122,-1);}}
 else if(k==='bench')f=bench(a,param);
 else if(k==='overhead')f=overhead(a,param);
 else if(k==='curl')f=curl(a,param);
 else if(k==='cable-row')f=cableRow(a,param);
 else if(k==='erg-row'){f=R.standing();const hip=[375-125*a,535],pull=Math.max(0,(a-.55)/.45);f.p[2]=hip;f.p[1]=[hip[0]+10-35*pull,370];f.p[0]=[f.p[1][0],310];f.p[8]=[575,580];f.p[10]=[563,568];f.p[7]=ik(hip,f.p[8],172,1);f.p[9]=ik(hip,f.p[10],172,1);f.p[4]=[f.p[1][0]+208-100*pull,430+15*pull];f.p[6]=add23(f.p[4],[-12,-12]);f.p[3]=ik(f.p[1],f.p[4],112,-1);f.p[5]=ik(f.p[1],f.p[6],112,-1);f.equipment=[['bench',[hip[0]-30,567,hip[0]+40,715]]];}
 else if(k==='pulldown')f=pulldown(a,param);
 else if(k==='fly')f=fly(a,param,e.gymOnly23);
 else if(k==='tricep')f=tricep(a,param);
 else if(k==='hamcurl'&&param.lying){f=push(0);f.p[1]=[245,430];f.p[2]=[430,435];f.p[7]=[570,440];f.p[8]=[570+140*Math.cos(a*1.4),440-140*Math.sin(a*1.4)];f.p[9]=[557,427];f.p[10]=[557+140*Math.cos(a*1.4),427-140*Math.sin(a*1.4)];f.p[4]=[185,455];f.p[6]=[174,444];f.p[3]=[230,485];f.p[5]=[219,474];f.equipment=[['bench',[150,472,610,715]]];}
 else if(k==='leg-extension'||k==='hamcurl')f=seatedLeg(a,param,k==='hamcurl');
 else if(k==='legpress')f=legpress(a);
 else if(k==='calf'){f=R.standing();move(f,[0,1,2,3,4,5,6,7,9],0,-25*a);f.p[8][1]-=18*a;f.p[10][1]-=18*a;}
 else if(k==='hip-machine'){f=front();f.p[2][1]=465;f.p[7]=[315-65*a,570];f.p[8]=[305-65*a,704];f.p[9]=[405+65*a,570];f.p[10]=[415+65*a,704];f.equipment=[['bench',[260,500,460,715]]];}
 else if(k==='machine-press'){f=cableRow(1-a,{seated:true});f.p[7]=[425,515];f.p[8]=[425,704];f.p[9]=[412,503];f.p[10]=[412,692];f.p[4][1]-=50;f.p[6][1]-=50;f.p[3]=ik(f.p[1],f.p[4],110,1);f.p[5]=ik(f.p[1],f.p[6],110,1);}
 else if(k==='lateral'){f=front();f.p[3]=[300-75*a,370-75*a];f.p[4]=[285-145*a,475-175*a];f.p[5]=[420,370];f.p[6]=[435,475];}
 else if(k==='pallof'){f=R.standing();f.p[4]=[395+150*a,350];f.p[6]=add23(f.p[4],[-12,-12]);f.p[3]=ik(f.p[1],f.p[4],110,-1);f.p[5]=ik(f.p[1],f.p[6],110,-1);}
 else if(k==='woodchop'){f=front();f.p[4]=[285+180*a,220+245*a];f.p[6]=add23(f.p[4],[20,0]);f.p[3]=ik([315,270],f.p[4],125,1);f.p[5]=ik([405,270],f.p[6],125,-1);f.detail.footAxis23=[.55*a,1];}
 else if(k==='face-pull'){f=front();f.p[4]=[335-80*a,350-100*a];f.p[6]=[385+80*a,350-100*a];f.p[3]=[290-55*a,345];f.p[5]=[430+55*a,345];}
 else if(k==='straight-pull'){f=R.standing();f.p[4]=[480-70*a,235+225*a];f.p[6]=add23(f.p[4],[-12,-12]);f.p[3]=mix(f.p[1],f.p[4],.52);f.p[5]=mix(f.p[1],f.p[6],.52);}
 else if(k==='landmine'){f=R.standing();f.p[4]=[420+105*a,310-110*a];f.p[3]=ik(f.p[1],f.p[4],112,1);}
 else if(k==='treadmill')f=R.march(Math.max(0,Math.sin(t*Math.PI*2))*.6,Math.max(0,-Math.sin(t*Math.PI*2))*.6);
 else throw Error('No existe rig para '+k);
 if(param.rack){f.p[4]=[f.p[1][0]+20,f.p[1][1]+20];f.p[6]=[f.p[1][0]+7,f.p[1][1]+32];}
 if(param.single&&['cable-row','tricep'].includes(k)){f.p[5]=[f.p[1][0]-15,f.p[1][1]+105];f.p[6]=[f.p[1][0]-15,f.p[1][1]+202];}
 if(e.gymOnly23){f.detail.gear23=param.gear;f.detail.recipe23=k;f.detail.param23=param;f.equipment.push(['bounds23',[80,80,730,735]]);if(param.gear==='cable')f.detail.cable23=k==='pulldown'?[360,90]:param.overhead?[150,690]:['curl','lateral'].includes(k)?[650,690]:k==='cable-row'?[650,param.high?380:445]:[650,240];}
 if(param.db)f.loads=[cp(f.p[4]),cp(f.p[6])];
 if(e.gymOnly23)f.loads=[];
 f.detail.param23=param;f.detail.phase23=t;f.detail.family23=k;f.detail.reviewed23=true;return stage(f,a);
}
const frontRecipes=new Set(['overhead','pulldown','lateral','hip-machine','woodchop','face-pull','lateral-lunge']);
for(const e of Engine.exercises.filter(e=>e.variant==='base'&&e.pack23&&!e.pack30)){
 const m=MotionDB[e.id],v=m.view21,param=e.param23,k=e.recipe23;
 const top=k==='push'&&(param.grip==='diamond'||param.stagger||param.assistedOne),floorFly=k==='fly'&&!e.gymOnly23,primary=top||floorFly?'top':frontRecipes.has(k)||k==='step'||param.cross&&k==='lunge'||param.wide&&k==='deadlift'||k==='fly'&&e.gymOnly23?'front':k==='side-plank'?'lying':'side';
 Object.assign(v,{primary,label:{top:'Superior',front:'Frontal',side:'Lateral',lying:'Frontal · de lado'}[primary],family:k,mirror:false,project:null,inset:top?'pushDepth23':floorFly?'floorFly23':k==='pallof'?'pallof23':param.frog?'frog23':param.pronate?'grip23':param.airplane?'hip23':param.scissors?'scissors23':param.close||param.supinate||param.neutral||param.wide?'grip23':null,reason:primary==='front'?'Observa los dos lados y el recorrido de la carga.':'Observa el apoyo y la relación entre cabeza, pelvis y articulaciones.'});
 m.posture23=k==='hamcurl'&&param.lying?'prone':['push','pike','plank','bear','renegade'].includes(k)?'prone':['bridge','ham-slide','hipthrust','hollow','legraise','bench','reverse-plank'].includes(k)||k==='fly'&&!e.gymOnly23?'supine':k==='side-plank'?'side-lying':'upright';
 m.frames=Array.from({length:48},(_,i)=>{const f=modelFrame(e,i/48);f.detail.view21=primary;return f;});m.hold=false;if(m.posture23==='upright')m.facing23='right';
 Rig2D23.reviews.push({id:e.id,name:e.name,action:'Rig y equipo específicos del patrón '+k,changed:true,view:primary,inset:v.inset,hold:false});
}
function extras(f,accent){let out='',d=f.detail,k=d.recipe23,p=f.p;
 if(d.towel23)out+=`<path data-apparatus23="towel" d="M${pt(p[4])}L${pt(p[8])}L${pt(p[6])}" fill="none" stroke="${accent}" stroke-width="5" stroke-linejoin="round"/>`;
 if(d.gear23==='cable'){
  const anchor=d.cable23,handle=d.param23.single?p[4]:mix(p[4],p[6],.5);
  if(k==='fly'){const y=d.param23.low?690:d.param23.high?120:285;out+=`<g data-apparatus23="dual-cable" fill="none" stroke="#7a9aa7" stroke-width="5"><path d="M80 735V120M650 735V120"/><circle cx="80" cy="${y}" r="12"/><circle cx="650" cy="${y}" r="12"/><path d="M80 ${y}L${pt(p[4])}M650 ${y}L${pt(p[6])}" stroke="${accent}" stroke-width="3"/></g>`;}
  else out+=`<g data-apparatus23="cable" fill="none" stroke="#7a9aa7" stroke-width="5"><path d="M650 735V120H${anchor[0]}V${anchor[1]}"/><circle cx="${anchor[0]}" cy="${anchor[1]}" r="12"/><path d="M${pt(anchor)}L${pt(handle)}" stroke="${accent}" stroke-width="3"/><rect x="665" y="440" width="42" height="190" rx="5"/><path d="${d.param23.single?'M'+(handle[0]-12)+' '+handle[1]+'h24':'M'+pt(p[4])+'L'+pt(p[6])}" stroke-width="8"/></g>`;
 }
 if(['machine','smith'].includes(d.gear23)){out+=`<g data-apparatus23="${d.gear23}" fill="none" stroke="#7a9aa7" stroke-width="7"><path d="M115 735V170M700 735V170M115 170H700M100 735H715"/><rect x="640" y="420" width="42" height="200" rx="5"/></g>`;if(d.plate23)out+=`<path d="M${d.plate23[0]+12} ${d.plate23[1]-55}V${d.plate23[1]+28}" stroke="${accent}" stroke-width="12"/>`;if(d.hack23)out+=`<path d="M230 680L400 210M${p[1][0]-30} ${p[1][1]-10}l60-24" stroke="${accent}" stroke-width="8" fill="none"/>`;if(['leg-extension','hamcurl'].includes(k))out+=`<path d="M${pt(p[8])}L${pt(p[10])}" stroke="#fb927d" stroke-width="18" stroke-linecap="round"/>`;}
 if(d.gear23==='cardio'){out+=`<g data-apparatus23="cardio" stroke="#7a9aa7" stroke-width="8" fill="none"><path d="M150 724H665M645 724V480H560"/><rect x="553" y="440" width="55" height="36" rx="6"/>${k==='erg-row'?`<path d="M170 590H610M595 532V615M630 550L${pt(mix(p[4],p[6],.5))}" stroke="${accent}" stroke-width="3"/>`:`<path d="M155 725H632" stroke="${accent}" stroke-width="3" stroke-dasharray="18 24" stroke-dashoffset="${(d.phase23||0)*126}"/>`}</g>`;}
 return out;
}
function loads(f,view,accent){const d=f.detail,p=f.p;if(!['barbell','smith'].includes(d.gear23))return '';
 let q=mix(p[4],p[6],.5);if(d.param23.back)q=[p[1][0]-8,p[1][1]-14];if(d.param23.front)q=[p[1][0]+20,p[1][1]+20];if(d.recipe23==='hipthrust')q=cp(p[2]);if(d.barCenter31)q=cp(d.barCenter31);
 if(d.param23.landmine)return `<g data-load23="landmine" stroke="#7a9aa7" stroke-width="12"><path d="M650 724L${pt(q)}"/><circle cx="${q[0]}" cy="${q[1]}" r="27" fill="#243d50" stroke="${accent}" stroke-width="3"/></g>`;
 const x1=view==='front'?q[0]-175:q[0]-25,x2=view==='front'?q[0]+175:q[0]+25;
 return `<g data-load23="barbell" stroke="#7a9aa7" fill="#243d50"><path d="M${x1} ${q[1]}H${x2}" stroke-width="9"/><${view==='front'?'rect':'ellipse'} ${view==='front'?`x="${x1}" y="${q[1]-35}" width="15" height="70"`:`cx="${x1}" cy="${q[1]}" rx="16" ry="32"`} stroke="${accent}" stroke-width="3"/><${view==='front'?'rect':'ellipse'} ${view==='front'?`x="${x2-15}" y="${q[1]-35}" width="15" height="70"`:`cx="${x2}" cy="${q[1]}" rx="16" ry="32"`} stroke="${accent}" stroke-width="3"/></g>`;
}
Rig2D23.extras=extras;Rig2D23.loads=loads;Rig2D23.phase=(e,phase)=>{const t=((phase%48)+48)%48/48;return e.renderWarp23==='slow'?(t<.4?t/.4*24:24+(t-.4)/.6*24):e.renderWarp23==='pause'?(t<1/3?t*3*24:t<2/3?24:24+(t-2/3)*3*24):phase;};
})(window);

;
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

;
/* Cuatro patrones corregidos y propagados a todos sus modelos y variantes. */
(function(root){'use strict';
const R=Rig2D23,cp=R.cp,wave=R.wave,reviews=[];
function bear(t,walking=false){const f=R.standing(),p=f.p,a=walking?Math.sin(t*6.283):0;
 f.p=[[190,395],[245,405],[435,405],[245-10*a,509],[245-22*a,613],[232+10*a,497],[232+22*a,601],[435+18*a,575],[548+18*a,609],[422-18*a,563],[535-18*a,597]];
 f.detail={floor23:627,feet24:{8:[[-1,0],true],10:[[-1,0],true]},stage18:walking?'AVANZA CON BRAZO Y PIERNA CONTRARIOS':'RODILLAS SUSPENDIDAS · RESPIRA',family23:'bear'};return f;
}
function hipflex(){const f=R.standing();f.p=[[350,245],[350,308],[350,465],[355,397],[410,460],[342,385],[397,448],[490,500],[490,650],[267,646],[147,646]];
 f.detail={floor23:665,feet24:{8:[[1,0],true],10:[[-1,0],false]},stage18:'RODILLA POSTERIOR APOYADA · PELVIS ESTABLE',family23:'hipflex'};return f;
}
function triceps(t){const f=R.standing(),a=wave(t),angle=2.4*a;f.p[3]=[346,175];f.p[4]=[346-100*Math.sin(angle),175-100*Math.cos(angle)];f.p[5]=[333,173];f.p[6]=[333-100*Math.sin(angle),173-100*Math.cos(angle)];
 f.p[7]=[357,565];f.p[8]=[357,704];f.p[9]=[340,565];f.p[10]=[340,704];f.loads=[R.mix(f.p[4],f.p[6],.5)];f.detail.stage18=a>.7?'FLEXIONA EL CODO · CARGA DETRÁS DE LA CABEZA':'EXTIENDE · BRAZOS SUPERIORES ESTABLES';return f;
}
function bridge(t,walkout=false,single=false){const f=R.standing(),u=wave(t),a=walkout?Math.floor(u*3)/3:u,p=f.p;
 p[0]=[150,621];p[1]=[210,632];p[2]=[385,525];p[3]=[265,629];p[4]=[335,638];p[5]=[251,617];p[6]=[322,626];
 p[8]=[470+145*a,639];p[10]=[458+145*(walkout?Math.min(1,a+.15*Math.sin(t*6.283)):a),627];
 p[7]=R.ik(p[2],p[8],135,1);p[9]=R.ik([p[2][0]-12,p[2][1]-12],p[10],135,1);
 if(single){p[9]=[455,460];p[10]=[568,488];}
 f.detail={floor23:655,feet24:{8:[[1,0],true],10:[[1,0],!single]},stage18:walkout?'PASOS CORTOS ALTERNOS · CONSERVA LA PELVIS':'ALEJA Y ACERCA LOS TALONES CON CONTROL',family23:'ham-slide',param23:{walkout,single}};return f;
}
for(const e of Engine.exercises.filter(e=>e.variant==='base')){
 const m=MotionDB[e.id],v=m.view21,fam=v.family;let make,note;
 if(['bear','bearcrawl','bearhold'].includes(fam)&&!e.pack23){make=t=>bear(t,e.id==='bearcrawl');m.posture23='prone';v.inset=e.id==='bearhold'?'hold23':null;note='Cuello neutro, manos bajo hombros y rodillas por encima del suelo.';}
 if(fam==='hipflex'){make=hipflex;m.posture23='upright';m.facing23='right';v.mirror=false;v.inset='hold23';note='Perfil hacia el pie anterior, rodilla posterior apoyada y pie posterior extendido.';}
 if(e.id==='pau-overtriceps'){make=triceps;m.posture23='upright';m.facing23='right';v.mirror=false;v.inset=null;note='Perfil coherente: codos quietos, mancuerna detrás de la cabeza y pies sin cruces.';}
 if(fam==='ham-slide'){make=t=>bridge(t,!!e.param23?.walkout,!!e.param23?.single);m.posture23='supine';v.mirror=false;v.inset=null;note='Rodillas por encima de los talones, pelvis elevada, apoyo real de talones.';}
 if(!make)continue;
 v.primary='side';v.label='Lateral';v.project=null;
 m.frames=Array.from({length:48},(_,i)=>{const f=make(i/48);f.detail={...f.detail,view21:'side',reviewed24:true};return f;});
 reviews.push({id:e.id,note,models:1,variants:Engine.exercises.filter(x=>x.baseId===e.id).length});
}
for(const e of Engine.exercises.filter(e=>e.baseId==='home-bridge-walk23')){
 e.instructions=['Túmbate boca arriba, dobla las rodillas y apoya los talones en el suelo.','Eleva la pelvis manteniendo hombros y cabeza apoyados.','Aleja los talones con pasos cortos alternos sin dejar caer la pelvis.','Vuelve con pasos cortos; termina la repetición al recuperar el apoyo inicial.'];e.steps=e.instructions.join(' ');e.setup=e.instructions[0];
}
for(const e of Engine.exercises.filter(e=>e.baseId==='pau-overtriceps')){e.sourceOnly=false;e.variantDescription='Vista lateral propia: los codos permanecen junto a la cabeza mientras el antebrazo baja la carga por detrás.';}
const oldDescribe=Anatomy2D22.describe;
Anatomy2D22.describe=function(e,f,m){const result=oldDescribe(e,f,m);for(const foot of result.feet){const override=f.detail.feet24?.[foot.index];if(override){foot.axis=override[0];foot.grounded=override[1];foot.heel=[foot.origin[0]-7*foot.axis[0],foot.origin[1]-7*foot.axis[1]];foot.toe=[foot.origin[0]+28*foot.axis[0],foot.origin[1]+28*foot.axis[1]];}}return result;};
root.RigCorrections24={reviews,bear,hipflex,triceps,bridge};
})(window);

;
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

;
/* Sesiones originales. Cantidades de diseño; las restricciones prevalecen. */
(function(root){'use strict';
const names=['Básico','Intermedio','Avanzado'],keys=['basic','intermediate','advanced'];
const families={
 full:{name:'Cuerpo completo',category:'Mixta',goals:['build','fitness','habit'],body:['squat','kneepush','bridge','deadbug','fastmarch','split'],db:['goblet','press','row','dbbridge','dbmarch','hammer']},
 push:{name:'Empuje y hombros',category:'Fuerza',goals:['build'],body:['incline','kneepush','wallslide','scapular','closepush'],db:['overheadpress','benchpress','neutral','lyingtricep','lateral','press']},
 pull:{name:'Espalda y brazos',category:'Fuerza',goals:['build'],body:['prone','reverse','scapular','towelrow','bird'],db:['row','curl','bentrow','hammer','reversefly','concentration']},
 legs:{name:'Piernas y glúteos',category:'Fuerza',goals:['build','athletic'],body:['squat','split','bridge','calf','reverselunge','singlebridge'],db:['goblet','rdl','dbbridge','dbcalf','dbsumo','dblunge']},
 core:{name:'Zona media',category:'Fuerza',goals:['build','movement','habit'],body:['deadbug','bird','kneelingplank','sideplank','curlup','plank'],db:['suitcase','farmerhold','dbbridge','farmerwalk','dbmarch']},
 control:{name:'Cadencia y control',category:'Fuerza',goals:['build','movement','habit'],body:['squat-control18','kneepush-control18','bridge-control18','deadbug-control18','calf-control18'],db:['goblet-control18','press-control18','row-control18','rdl-control18','curl-control18','hammer-control18']},
 cardio:{name:'Cardio sin saltos',category:'Cardio',goals:['fitness','habit'],body:['fastmarch','stepjack','kneeliftcardio','box','sidestep','heelback']},
 intervals:{name:'Intervalos de cardio',category:'Cardio',goals:['fitness','athletic'],body:['stepjack','shadowcombo','fastfeet','skater','jog','highknees']},
 agility:{name:'Agilidad y coordinación',category:'Cardio',goals:['athletic','fitness'],body:['shuffle','stepcross','frontbackstep','skaterreach','doubletouch','fastfeet']},
 power:{name:'Saltos y potencia',category:'Cardio',goals:['athletic'],body:['lowhop','linehop','calf','squat','skaterhop','squatjump']},
 mobility:{name:'Movilidad de cuerpo completo',category:'Movilidad',goals:['movement','adapted','habit'],body:['ankledrive','hipcircle','shoulder','wallslide','hipflex','thoracic']},
 balance:{name:'Equilibrio y estabilidad',category:'Movilidad',goals:['movement','athletic','adapted'],body:['tandem','linewalk','standingabduct','singlehinge','wallcalf','balance']},
 seated:{name:'Movimiento sentado',category:'Movilidad',goals:['adapted','movement','habit'],body:['seatedmarch','seatedcalf','legextend','ankle','shoulder','scapular']}
};
const lengths={brief:{name:'Breve',count:3,ratio:.5},standard:{name:'Completa',count:4,ratio:1},long:{name:'Extendida',count:5,ratio:1}};
Object.assign(TrainingIntensity.levels,{basic:{sets:4,reps:16,seconds:40,cardioSets:3,cardioSeconds:40},intermediate:{sets:4,reps:20,seconds:50,cardioSets:4,cardioSeconds:45},advanced:{sets:4,reps:24,seconds:60,cardioSets:4,cardioSeconds:50}});
const gentle=p=>Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle;
function dose(e,p,kind){
 if(e?.doseCap46){const c=e.doseCap46;return HunterCatalog46.cap(e,p,{id:e.id,sets:2,target:c.initial,rest:c.rest});}
 const v=TrainingIntensity.levels[p.difficulty]||TrainingIntensity.levels.basic,scale=TrainingIntensity.scale(p),beginner=p.experience==='beginner';
 if(gentle(p)||kind==='recovery')return {id:e.id,sets:1,target:e.unit==='sec'?15:6,rest:kind==='recovery'?30:90};
 if(e.family==='power')return {id:e.id,sets:beginner?2:[2,3,4][keys.indexOf(p.difficulty)]||2,target:Math.max(2,Math.floor((beginner?3:[4,5,6][keys.indexOf(p.difficulty)]||4)*scale)),rest:Math.max(90,p.restSeconds||90)};
 const cardio=kind==='cardio',sets=beginner?2:cardio?v.cardioSets:v.sets,target=beginner?(e.unit==='sec'?25:10):e.unit==='sec'?(cardio?v.cardioSeconds:v.seconds):v.reps;
 return {id:e.id,sets,target:Math.max(e.unit==='sec'?10:4,Math.floor(target*scale)),rest:Math.max(cardio?45:90,p.restSeconds||90)};
}
TrainingIntensity.dose=dose;
function cap(e,p){return e.family==='power'?8:e.unit==='sec'?90:p.experience==='beginner'?14:[24,30,36][Math.max(0,keys.indexOf(p.difficulty))];}
AdaptiveV9.recommend=function(logs,item,p,now){
 const e=Engine.byId(item.id),rows=AdaptiveV9.evidence(logs,item.id,now).filter(r=>Engine.unitOf(r.item)===Engine.unitOf(item)&&(r.item.side||'both')===(item.side||'both')),recent=rows.slice(0,3),full=recent.length===3&&recent.every(r=>r.item.actual?.length>=Math.max(r.item.sets,item.sets)&&r.item.actual.every(a=>a.done&&Number(a.value)>=Math.max(r.item.target,item.target)));
 let target=item.target,action='mantener',reason='Reúne tres registros completos y comparables de este ejercicio.';
 if(!Adapt.mode(p).auto||gentle(p)||TrainingIntensity.scale(p)<1)reason='Se conserva la adaptación y la reducción elegida.';
 else if(full&&recent.every(r=>r.rpe<=7)){
  const delta=e.unit==='sec'?Math.min(5,Math.max(1,Math.floor(target*.1))):1;
  target=Math.max(item.target,Math.min(cap(e,p),target+delta));action=target>item.target?'aumentar':'mantener';reason=action==='aumentar'?'Tres registros completos con esfuerzo de 7/10 o menos: aumenta una sola variable.':'Tope de este nivel: conserva la dosis y revisa la técnica o una variante desde Perfil.';
 }
 return {target,action,reason,samples:rows.length};
};
const oldItems=Schedule.items;
Schedule.items=function(p,kind,rotation,carry){const items=oldItems(p,kind,rotation,carry);if(!Adapt.mode(p).auto)return items;return items.map(x=>({...x,...dose(Engine.byId(x.id),p,kind)}));};
const make=Engine.makeCycle;
Engine.makeCycle=function(p,start,index,last,logs={}){const c=make(p,start,index,last,logs);c.engineVersion18=18;if(!Adapt.mode(p).auto)return c;for(const d of c.days)for(const x of d.items){const e=Engine.byId(x.id),v=dose(e,p,d.kind),prior=last?.days.flatMap(y=>y.items).filter(y=>y.id===x.id&&Engine.unitOf(y)===Engine.unitOf(x)).at(-1);if(prior&&!gentle(p)&&TrainingIntensity.scale(p)===1)v.target=Math.max(v.target,Math.min(prior.target,cap(e,p)));Object.assign(x,v);}return c;};
function workSeconds(x){const e=Engine.byId(x.id);return (Engine.unitOf(x)==='sec'?x.target:x.target*(e.tempoSeconds18||4))*(Engine.sidesOf(x)&&!x.side?2:1);}
function estimate(items){return Math.ceil((120+items.reduce((n,x,i)=>n+x.sets*workSeconds(x)+Math.max(0,x.sets-1)*x.rest+(i<items.length-1?x.rest:0),0))/60);}
function adjust(r,p){return r.items.map(x=>{const e=Engine.byId(x.id),v=dose(e,p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength');return {...x,...v,sets:gentle(p)?1:Math.max(1,Math.ceil(v.sets*lengths[r.extension].ratio))};});}
const routines=[];
for(const [family,spec] of Object.entries(families))for(let level=0;level<3;level++)for(const [extension,ex] of Object.entries(lengths))for(const equipment of spec.db?['body','db']:['body']){
 const pool=spec[equipment].map(id=>Engine.byId(id)).filter(Boolean).filter(e=>e.level<=(level===0?1:2)),selected=pool.slice(0,ex.count),p={difficulty:keys[level],experience:'regular',health:{pain:'none'},injuries:[],restrictions:{},trainingScale:1};
 const items=selected.map(e=>{const x=dose(e,p,spec.category==='Cardio'?'cardio':spec.category==='Movilidad'?'recovery':'strength');x.sets=Math.max(1,Math.ceil(x.sets*ex.ratio));return x;});
 const requirements=[...new Set(selected.flatMap(e=>e.requirements||[]).concat(equipment==='db'?['db']:[]))];
 const r={id:`own18-${family}-${level}-${extension}-${equipment}`,name:`${spec.name} · ${names[level]} · ${ex.name}${equipment==='db'?' · mancuernas':''}`,category:spec.category,level,items,requirements,impact:selected.some(e=>e.impact==='Con saltos')?'Con saltos':'Sin saltos',family,goalGroups:spec.goals,extension,planVersion:18,own18:true,description:'Sesión original de Hunter System. Preparación, series, descansos y vuelta a la calma con reloj. Cantidades adaptadas al perfil y revisión semanal.',minutes:estimate(items)};
 routines.push(r);
}
Catalog.routines=routines;
Catalog.baseRoutines=routines.length;Catalog.baseExercises=Engine.exercises.filter(e=>e.variant==='base').length;
RoutinePlanner.organize=function(c,p,source,own,logs,now){
 if(p.trainingMode!=='routines'||!Adapt.mode(p).auto||c.reviews?.at(-1)?.action==='pausar')return 0;
 let count=0;const adjusted=GoalPlans.adjusted(p),level=Math.max(0,keys.indexOf(p.difficulty)),group=GoalGroups14.of(p);
 for(const [i,d] of c.days.entries()){
  if(d.date<now||!d.items.length||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;
  const category=d.kind==='cardio'?'Cardio':d.kind==='recovery'?'Movilidad':'Fuerza';
  let list=routines.filter(r=>r.level===level&&r.extension==='standard'&&(r.category===category||category==='Fuerza'&&r.category==='Mixta')&&r.goalGroups.includes(group)&&Adapt.routineCompatible(r,adjusted));
  if(!list.length)list=routines.filter(r=>r.level===level&&r.extension==='brief'&&(r.category===category||category==='Fuerza'&&r.category==='Mixta')&&Adapt.routineCompatible(r,adjusted));
  // Si una plantilla completa no coincide, conserva los movimientos que ya
  // eligió el motor de restricciones en lugar de rellenar con incompatibles.
  if(!list.length){d.own18=true;d.title=gentle(p)?'Rutina adaptada · movimientos compatibles':'Rutina personal · '+category;d.planningNote='Secuencia propia con los movimientos que pasan tus filtros e indicaciones.';count++;continue;}
  const focus=adjusted.focus,targets=focus==='upper'?['Pecho','Espalda','Hombros','Brazos']:focus==='lower'?['Piernas','Cadera']:focus==='core'?['Core','Abdomen']:null;
  if(targets&&category==='Fuerza'){const focused=list.filter(r=>r.items.some(x=>targets.includes(Engine.byId(x.id).group)));if(focused.length)list=focused;}
  if(p.equipment==='db'){const weighted=list.filter(r=>r.requirements.includes('db'));if(weighted.length)list=weighted;}
  const r=list[i%list.length],previousItems=d.items;d.items=adjust(r,p);
  if(!gentle(p)&&TrainingIntensity.scale(p)===1)for(const x of d.items){const prior=previousItems.find(y=>y.id===x.id);if(prior)x.target=Math.max(x.target,Math.min(prior.target,cap(Engine.byId(x.id),p)));const next=AdaptiveV9.recommend(logs,x,p,now);x.target=Math.max(x.target,next.target);}
  if(c.reviews?.at(-1)?.action==='reducir')for(const x of d.items)x.target=Math.max(Engine.unitOf(x)==='sec'?10:4,Math.floor(x.target*.8));
  d.kind=r.category==='Mixta'?'mixed':d.kind;d.title=r.name;d.own18=true;d.autoTemplateId=r.id;d.tasks=[];d.planningNote=gentle(p)?'Rutina propia con dosis reducida y movimientos compatibles con tus restricciones.':'Rutina propia según tu objetivo, equipo y nivel. Revisión semanal con tus registros.';count++;
 }
 return count;
};
const expand=RoutineSequence.expand;
RoutineSequence.expand=function(day){const steps=expand(day);for(const s of steps)if(s.kind==='exercise'&&s.unit!=='sec')s.seconds=Math.ceil(workSeconds(s));if(!day.sourceSequence&&steps.length){steps.unshift({kind:'preparation',label:'Preparación · revisa espacio, equipo y técnica',seconds:60,blockIndex:-1});steps.push({kind:'cooldown',label:'Vuelta a la calma · baja el ritmo y respira',seconds:60,blockIndex:day.items.length});}return steps;};
root.OwnRoutines18={routines,families,lengths,names,keys,dose,adjust,estimate,cap,workSeconds,gentle};
})(window);

;
/* V19: escalones de movimiento, dosis por patrón y progresión semanal verificable. */
(function(root){
'use strict';
const E=Engine,O=OwnRoutines18,keys=O.keys,level=p=>Math.max(0,keys.indexOf(p.difficulty));
const tierPools={
 full:{body:[
  ['squat','incline','bridge','deadbug','prone','split'],
  ['squat-control18','pushup','singlebridge','plank','reverse','reverselunge'],
  ['split-15-19','archer19','singlebridge-pause19','plankreach19','y-t-w19','single-squat19']
 ],db:[
  ['goblet','press','row','dbbridge','hammer','suitcase'],
  ['goblet-control18','press-control18','bentrow','rdl-control18','altfloorpress','dbsplit'],
  ['goblet-15-19','singlepress','row-15-19','single-rdl19','doublefront','overhead-15-19']
 ]},
 push:{body:[
  ['incline','kneepush','wallslide','incline-control18','kneepush-control18'],
  ['pushup','pushup-control19','closepush','shouldertap','bearhold'],
  ['archer19','pike19','closepush','decline19','plankreach19']
 ],db:[
  ['press','overheadpress','neutral','lateral','lyingtricep','benchpress'],
  ['press-control18','overheadpress-control18','singlepress','neutral-control18','singleoverhead','singletricep'],
  ['singlepress','overhead-15-19','squeezepress','lateral-pause19','singletricep','benchpress-control18']
 ]},
 pull:{body:[
  ['prone','towelrow','scapular','bird','reverse'],
  ['w-t19','reverse','contralateral','bird-control18','towelrow'],
  ['y-t-w19','contralateral','w-t19','bird-control18','reverse']
 ],db:[
  ['row','curl','hammer','reversefly','bentrow','concentration'],
  ['row-control18','bentrow-control18','curl-control18','hammer-control18','reversefly','pullover'],
  ['row-15-19','bentrow-control18','curl-pause19','reversefly','pullover','hammer-control18']
 ]},
 legs:{body:[
  ['squat','split','bridge','calf','reverselunge','wallsit'],
  ['squat-control18','split-control18','singlebridge','singlecalf','lungeknee','lateralunge'],
  ['single-squat19','split-15-19','singlebridge-pause19','singlecalf-pause19','singlehinge-free19','squat-15-19']
 ],db:[
  ['goblet','rdl','dbbridge','dbcalf','dbsumo','sumodead'],
  ['goblet-control18','rdl-control18','dbsplit','dblunge','dbsidelunge','goblethold'],
  ['goblet-15-19','single-rdl19','dbsplit','bulgarian','dbsidelunge','doublefront']
 ]},
 core:{body:[
  ['deadbug','bird','kneelingplank','sideplank','curlup'],
  ['plank','bearhold','reversecrunch','shouldertap','sideplank'],
  ['full-sideplank19','plankreach19','pau-plankup','sideplank-lift19','mountain']
 ],db:[
  ['suitcase','farmerhold','dbbridge','deadbug','farmerwalk'],
  ['farmerwalk','dbmarch','singlepress','suitcase','dbsplit'],
  ['dbmarch','single-rdl19','singleoverhead','singlepress','farmerwalk']
 ]},
 control:{body:[
  ['squat-control18','kneepush-control18','bridge-control18','deadbug-control18','calf-control18'],
  ['pushup-control19','split-control18','bird-control18','squat-control18','singlebridge'],
  ['squat-15-19','split-15-19','singlebridge-pause19','singlecalf-pause19','y-t-w19']
 ],db:[
  ['goblet','press','row','rdl','curl'],
  ['goblet-control18','press-control18','row-control18','rdl-control18','curl-control18'],
  ['goblet-15-19','row-15-19','overhead-15-19','single-rdl19','curl-pause19','lateral-pause19']
 ]},
 cardio:{body:[
  ['fastmarch','stepjack','kneeliftcardio','box','sidestep','heelback'],
  ['shadowcombo','skater','squatstep','boxupper','stepreach','doubletouch'],
  ['shuffle-touch19','shadowcombo','squatstep','skaterreach','boxinterval','fastfeet']
 ]},
 intervals:{body:[
  ['stepjackinterval','boxinterval','fastmarch','sidestepinterval','stepreach'],
  ['shadowcombo','fastfeet','skater','jog','squatstep'],
  ['highknees','mountain','jumpjack','shuffle-touch19','rope','skater-stick19']
 ]},
 agility:{body:[
  ['frontbackstep','doubletouch','vstep','stepreach','skater'],
  ['shuffle','stepcross','skaterreach','fastfeet','shadowcombo'],
  ['shuffle-touch19','stepcross','skater-stick19','highknees','mountain']
 ]},
 power:{body:[
  ['lowhop','linehop','calf','squat','lowhop'],
  ['linehop','squatjump','lowhop','skaterhop','split'],
  ['skater-stick19','squatjump','skaterhop','linehop','singlecalf-pause19']
 ]},
 mobility:{body:[
  ['ankledrive','hipcircle','shoulder','wallslide','hipflex','thoracic'],
  ['hipflex','thoracic','hamstretch','ankledrive','chestopen','latstretch'],
  ['thoracic','latstretch','hipflex','hamstretch','chestopen','ankledrive']
 ]},
 balance:{body:[
  ['tandem','linewalk','standingabduct','wallcalf','balance'],
  ['balance','singlehinge','lungeknee','singlecalf','skaterreach'],
  ['singlehinge-free19','single-squat19','singlecalf-pause19','skater-stick19','lungeknee']
 ]},
 seated:{body:[
  ['seatedmarch','seatedcalf','legextend','ankle','shoulder','scapular'],
  ['seatedpunch','seatedmarchinterval','legextend-control18','heeltoe','seatedreach'],
  ['seatedmarchinterval','legextend-control18','seatedpunch','seatedstep','forearmmob']
 ]}
};
const previousGentle=O.gentle,gentle=p=>previousGentle(p),tierMap=new Map();
for(const spec of Object.values(tierPools))for(const pools of Object.values(spec))for(const [n,ids] of pools.entries())for(const id of ids)tierMap.set(id,Math.min(tierMap.get(id)??n,n));
for(const e of E.exercises)if(e.progressionTier19!==undefined)tierMap.set(e.id,e.progressionTier19);
const tier=id=>tierMap.get(id)??E.byId(id)?.level??0;
const oldReasons=Adapt.reasons;
Adapt.reasons=function(e,p,strict=true){
 if(!e)return ['Ejercicio desconocido'];
 const out=oldReasons(e,p,strict).filter(x=>x!=='Nivel superior al seleccionado');
 if(p){const ceiling=Adapt.mode(p).reduced?0:level(p)===0?1:2;if(e.level>ceiling)out.push('Nivel superior al seleccionado');}
 return out;
};
// Adapt.allowed cerraba sobre la versión original de reasons.
Adapt.allowed=(e,p)=>!Adapt.reasons(e,p).length;
Adapt.routineCompatible=(r,p)=>!!p&&Adapt.mode(p).auto&&r.items.every(x=>Adapt.allowed(E.byId(x.id),p));
const rest=p=>[30,60,90].includes(Number(p.restSeconds))?Number(p.restSeconds):[30,60,90][level(p)];
function dose(e,p,kind){
 const n=level(p),scale=TrainingIntensity.scale(p),novice=p.experience==='beginner';
 let sets,target;
 if(gentle(p)||kind==='recovery')return {id:e.id,sets:1,target:e.unit==='sec'?15:6,rest:30};
 if(e.family==='power'){sets=novice?2:[2,3,4][n];target=[4,5,6][n];}
 else if(e.unit==='sec'){sets=novice?2:[3,4,4][n];target=kind==='cardio'?[35,45,50][n]:[25,35,40][n];}
 else{
  sets=novice?2:[4,4,5][n];
  target=e.repRange19?e.repRange19[0]:[14,12,10][n];
  if(novice)target=Math.min(target,[10,8,6][n]);
 }
 return {id:e.id,sets,target:Math.max(e.unit==='sec'?10:2,Math.floor(target*scale)),rest:rest(p)};
}
function cap(e,p){return e.repRange19?.[1]||(e.family==='power'?8:e.unit==='sec'?75:[20,18,16][level(p)]);}
TrainingIntensity.dose=dose;Object.assign(TrainingIntensity.levels,{
 basic:{sets:4,reps:14,seconds:25,cardioSets:3,cardioSeconds:35},
 intermediate:{sets:4,reps:12,seconds:35,cardioSets:4,cardioSeconds:45},
 advanced:{sets:5,reps:10,seconds:40,cardioSets:4,cardioSeconds:50}
});
function pool(family,p,equipment){
 const spec=tierPools[family],ids=spec?.[equipment|| (p.equipment==='db'&&spec.db?'db':'body')]?.[level(p)]||[];
 return [...new Set(ids)].map(id=>E.byId(id)).filter(e=>e&&Adapt.allowed(e,p));
}
function choose(p,kind,rotation=0){
 if(!Adapt.mode(p).auto)return [];
 if(gentle(p)){
  return InjuryAwareV93.items(p,kind,rotation).filter(x=>Adapt.allowed(E.byId(x.id),p)).map(x=>({...x,...dose(E.byId(x.id),p,kind)}));
 }
 const group=GoalGroups14.of(p);let ids=[];
 const focus=p.focus,requested=Math.min(7,p.dailyMissions|| (p.trainingMode==='routines'?5:4));
 if(kind==='strength'||kind==='mixed'){
  const families=focus==='upper'?['push','pull']:focus==='lower'?['legs']:focus==='core'?['core']:['full'];
  for(const family of families)ids.push(...pool(family,p));
  const primary=ids.slice(0,requested);
  if(primary.length<requested){
   const extra=['legs','push','pull','core'].flatMap(f=>pool(f,p));ids=[...primary,...extra];
  }
 }else if(kind==='cardio'){
  const family=group==='athletic'?(Math.abs(rotation)%2?'agility':'power'):p.restrictions?.noImpact?'cardio':'intervals';
  ids=pool(family,p);
  if(ids.length<requested)ids.push(...pool('cardio',p));
 }else ids=pool('mobility',p);
 let chosen=[...new Map(ids.map(e=>[e.id,e])).values()];
 const n=kind==='recovery'?Math.min(3,requested):requested;
 if(!chosen.length){
  const category=kind==='cardio'?'Cardio':kind==='recovery'?'Movilidad':'Fuerza';
  chosen=E.exercises.filter(e=>e.variant==='base'&&!e.sourceOnly&&e.category===category&&tier(e.id)<=level(p)&&Adapt.allowed(e,p))
   .sort((a,b)=>Math.abs(level(p)-tier(a.id))-Math.abs(level(p)-tier(b.id)));
 }
 return chosen.slice(0,n).map(e=>dose(e,p,kind));
}
Schedule.items=choose;
const chains=[
 ['incline','incline-control18','pushup','pushup-control19','archer19'],
 ['kneepush','kneepush-control18','pushup','pushup-control19','pike19'],
 ['squat','squat-control18','squat-15-19','single-squat19'],
 ['split','split-control18','split-15-19'],
 ['goblet','goblet-control18','goblet-15-19'],
 ['rdl','rdl-control18','single-rdl19'],
 ['press','press-control18','singlepress'],
 ['row','row-control18','row-15-19'],
 ['overheadpress','overheadpress-control18','overhead-15-19'],
 ['curl','curl-control18','curl-pause19'],
 ['bridge','bridge-control18','singlebridge','singlebridge-pause19'],
 ['calf','calf-control18','singlecalf','singlecalf-pause19'],
 ['kneelingplank','plank','plankreach19'],
 ['sideplank','full-sideplank19','sideplank-lift19'],
 ['prone','w-t19','y-t-w19'],
 ['singlehinge','singlehinge-free19'],
 ['shuffle','shuffle-touch19'],
 ['skaterhop','skater-stick19']
];
function successor(id,p){
 for(const chain of chains){const i=chain.indexOf(id);if(i<0)continue;for(const next of chain.slice(i+1)){const e=E.byId(next);if(tier(next)<=level(p)&&Adapt.allowed(e,p))return e;}}
 return null;
}
function ruleRecommendation19(logs,item,p,now){
 const e=E.byId(item.id),rows=AdaptiveV9.evidence(logs,item.id,now).filter(r=>E.unitOf(r.item)===E.unitOf(item)&&(r.item.side||'both')===(item.side||'both')),recent=rows.slice(0,3);
 const full=recent.length===3&&recent.every(r=>r.rpe>=1&&r.rpe<=7&&r.item.sets>=item.sets&&r.item.target>=item.target&&r.item.actual?.length>=r.item.sets&&r.item.actual.every(a=>a.done&&Number(a.value)>=r.item.target));
 let out={id:item.id,target:item.target,action:'mantener',reason:'Reúne tres registros completos y comparables, con esfuerzo de 7/10 o menos.',samples:rows.length,variantChanged:false};
 if(!Adapt.mode(p).auto||gentle(p)||TrainingIntensity.scale(p)<1)return {...out,reason:'Se conserva la adaptación y la reducción elegida.'};
 if(!full)return out;
 if(item.target<cap(e,p)){
  const delta=e.unit==='sec'?Math.min(5,Math.max(1,Math.floor(item.target*.1))):1;
  return {...out,target:Math.min(cap(e,p),item.target+delta),action:'aumentar',reason:'Tres registros comparables: aumenta una sola variable dentro del rango de este movimiento.'};
 }
 const next=successor(item.id,p);
 if(next){
  const v=dose(next,p,next.category==='Cardio'?'cardio':'strength');
  return {...out,id:next.id,target:v.target,sets:v.sets,rest:v.rest,variantChanged:true,action:'aumentar',reason:'Rango completado: siguiente variante compatible, con repeticiones recalibradas. '+e.name+' → '+next.name+'.'};
 }
 return {...out,reason:'Rango completado. Conserva la técnica y la dosis de este nivel; cambia tu nivel o carga desde Perfil cuando corresponda.'};
}
AdaptiveV9.recommend=recommend;
function recommend(logs,item,p,now){
 const result=ruleRecommendation19(logs,item,p,now),candidate={...item,id:result.id||item.id,target:result.target},estimate=root.ProgressMonitor20?.estimate(logs,candidate,now);
 if(result.action==='aumentar'&&estimate&&estimate.effort>7)return {id:item.id,target:item.target,action:'mantener',variantChanged:false,samples:result.samples,estimate20:estimate,reason:'Se mantiene la dosis: esfuerzo estimado '+estimate.effort+'/10 a partir de '+estimate.neighbors+' registros comparables.'};
 return {...result,estimate20:estimate};
}
function applyRecommendation(item,r,p){
 if(r.variantChanged&&r.id!==item.id){const e=E.byId(r.id);if(e&&Adapt.allowed(e,p)&&tier(e.id)<=level(p)){const next={...item,...dose(e,p,e.category==='Cardio'?'cardio':'strength'),id:r.id,target:r.target,progressedFrom19:item.id};delete next.unit;if(!e.sides)delete next.side;return next;}}
 return {...item,target:Math.min(cap(E.byId(item.id),p),Math.max(item.target,r.target)),rest:rest(p)};
}
function carry(item,last,p){
 if(!last||gentle(p)||TrainingIntensity.scale(p)<1)return item;
 const old=last.days.flatMap(d=>d.items).filter(x=>x.id===item.id||chains.some(c=>c.includes(item.id)&&c.includes(x.id)&&c.indexOf(x.id)>c.indexOf(item.id))).at(-1);
 if(!old||!Adapt.allowed(E.byId(old.id),p)||tier(old.id)>level(p))return item;
 const v=dose(E.byId(old.id),p,E.byId(old.id).category==='Cardio'?'cardio':'strength');
 const next={...item,...v,target:Math.max(v.target,Math.min(cap(E.byId(old.id),p),old.target))};if(old.id!==item.id){delete next.unit;if(!E.byId(old.id).sides)delete next.side;}return next;
}
const make=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){
 const c=make(p,start,index,last,logs),ap=GoalPlans.adjusted(p);c.engineVersion19=19;
 if(!Adapt.mode(ap).auto)return c;
 for(const d of c.days){
  if(!d.items.length||d.kind==='rest')continue;
  const selected=choose(ap,d.kind,Schedule.weekday(d.date));
  if(selected.length)d.items=selected.map(x=>carry(x,last,ap));
  else d.items=d.items.filter(x=>Adapt.allowed(E.byId(x.id),ap)).map(x=>({...x,...dose(E.byId(x.id),ap,d.kind)}));
  d.planningNote=gentle(ap)?'Selección compatible con tus restricciones, con dosis reducida.':'Escalón '+O.names[level(p)]+': variantes, control y volumen propios de este nivel.';
  d.progressionLevel19=level(p);
 }
 return c;
};
function adjust(r,p){return r.items.map(x=>{const v=dose(E.byId(x.id),p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength');return {...x,...v,sets:gentle(p)?1:Math.max(1,Math.ceil(v.sets*O.lengths[r.extension].ratio))};});}
const routines=[];
for(const [family,spec] of Object.entries(O.families))for(let n=0;n<3;n++)for(const [extension,length] of Object.entries(O.lengths))for(const equipment of spec.db?['body','db']:['body']){
 const ids=[...new Set(tierPools[family][equipment][n])].slice(0,length.count),selected=ids.map(id=>E.byId(id));
 if(selected.some(e=>!e))throw Error('Plantilla incompleta: '+family);
 const p={difficulty:keys[n],experience:'regular',health:{pain:'none'},injuries:[],restrictions:{},trainingScale:1};
 const items=selected.map(e=>{const v=dose(e,p,spec.category==='Cardio'?'cardio':spec.category==='Movilidad'?'recovery':'strength');v.sets=Math.max(1,Math.ceil(v.sets*length.ratio));return v;});
 const requirements=[...new Set(selected.flatMap(e=>e.requirements||[]).concat(equipment==='db'?['db']:[]))];
 routines.push({id:'own18-'+family+'-'+n+'-'+extension+'-'+equipment,name:spec.name+' · '+O.names[n]+' · '+length.name+(equipment==='db'?' · mancuernas':''),
  category:spec.category,level:n,items,requirements,impact:selected.some(e=>e.impact==='Con saltos')?'Con saltos':'Sin saltos',family,goalGroups:spec.goals,extension,planVersion:19,own18:true,own19:true,
  description:'Sesión original de Hunter System. '+(family==='pull'&&equipment==='body'?'Trabajo escapular y de control sin carga externa. ':'')+'Los tres niveles cambian las variantes y la coordinación, además del volumen. Movilidad y adaptación conservan dosis propias.',
  minutes:O.estimate(items)});
}
O.routines.splice(0,O.routines.length,...routines);Object.assign(O,{dose,adjust,cap,gentle});
Catalog.routines=O.routines;Catalog.baseRoutines=routines.length;Catalog.baseExercises=E.exercises.filter(e=>e.variant==='base').length;
RoutinePlanner.organize=function(c,p,source,own,logs,now){
 if(p.trainingMode!=='routines'||!Adapt.mode(p).auto||c.reviews?.at(-1)?.action==='pausar')return 0;
 const ap=GoalPlans.adjusted(p),n=level(p),group=GoalGroups14.of(p);let count=0;
 for(const [i,d] of c.days.entries()){
  if(d.date<now||!d.items.length||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;
  const category=d.kind==='cardio'?'Cardio':d.kind==='recovery'?'Movilidad':'Fuerza';
  let list=routines.filter(r=>r.level===n&&r.extension==='standard'&&(r.category===category||category==='Fuerza'&&r.category==='Mixta')&&r.goalGroups.includes(group)&&Adapt.routineCompatible(r,ap));
  if(!list.length)list=routines.filter(r=>r.level===n&&r.extension==='brief'&&(r.category===category||category==='Fuerza'&&r.category==='Mixta')&&Adapt.routineCompatible(r,ap));
  if(ap.focus==='upper'||ap.focus==='lower'||ap.focus==='core'){
   const families=ap.focus==='upper'?['push','pull']:ap.focus==='lower'?['legs']:['core'],focused=list.filter(r=>families.includes(r.family));if(focused.length)list=focused;
  }
  if(p.equipment==='db'){const weighted=list.filter(r=>r.requirements.includes('db'));if(weighted.length)list=weighted;}
  if(!list.length){d.own18=true;d.own19=true;d.title='Rutina personal · '+category;d.planningNote='Movimientos compatibles con tu perfil; la adaptación conserva prioridad sobre el nivel.';count++;continue;}
  const r=list[i%list.length],previous=d.items,items=adjust(r,ap);
  for(let j=0;j<items.length;j++){
   let x=carry(items[j],{days:[{items:previous}]},ap);
   if(!gentle(ap)&&TrainingIntensity.scale(ap)===1&&c.reviews?.at(-1)?.adherence>=.8)x=applyRecommendation(x,recommend(logs,x,ap,now),ap);
   if(c.reviews?.at(-1)?.action==='reducir')x.target=Math.max(E.unitOf(x)==='sec'?10:2,Math.floor(x.target*.8));
   items[j]=x;
  }
  d.items=items;d.kind=r.category==='Mixta'?'mixed':d.kind;d.title=r.name;d.own18=true;d.own19=true;d.autoTemplateId=r.id;d.tasks=[];
  d.planningNote=gentle(ap)?'Rutina adaptada con dosis reducida y filtros de lesión.':'Variantes del nivel '+O.names[n]+'. Revisión semanal por repeticiones, esfuerzo y cambio de variante.';count++;
 }
 return count;
};
root.TrainingProgression19={tierPools,tier,level,pool,choose,dose,cap,rest,chains,successor,recommend,applyRecommendation,carry,routines,adjust};
})(window);

;
/* Interpretación local conjunta: familia elegida, texto y metas personales activas. */
(function(root){'use strict';
const clean=s=>String(s||'').toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g,''),oldAdjusted=GoalPlans.adjusted;
function personal(){return typeof state==='undefined'?[]:(state.goals||[]).filter(g=>g.repeat==='daily'||!g.dates?.length).map(g=>g.title);}
function interpret(p={},titles=personal()){
 const chosen=GoalGroups14.of(p),text=[p.finalGoal||'',...titles].join('. '),r=GoalGroups14.interpret(text,chosen),plain=clean(text),positive=plain.replace(/(?:no quiero|no deseo|no busco|evitar)\s+[^,.;]+/g,'');
 const priorities=[chosen,...r.alternatives.filter(g=>g!==chosen)],weights=Object.fromEntries(priorities.map((g,i)=>[g,i===0?2:1]));
 const focus=/piernas?|gluteos?|tren inferior/.test(positive)?'lower':/brazos?|pecho|hombros?|tren superior/.test(positive)?'upper':/abdomen|zona media|core/.test(positive)?'core':p.focus||'full';
 const inferred={};if(r.noImpact)inferred.noImpact=true;
 const constraints={noFloor:/sin suelo|no (?:quiero |puedo )?(?:tumbarme|arrodillarme)|evitar (?:el )?suelo/,noOverhead:/sin (?:elevar|levantar)[^.;]*cabeza|(?:no|evitar) (?:quiero |puedo )?(?:levantar|elevar|hacer press)[^.;]*sobre la cabeza/,noLoad:/sin (?:peso|pesas|carga)|no (?:quiero |puedo )?usar (?:peso|pesas)/,noWrist:/sin apoyo[^.;]*manos|no apoyar[^.;]*manos/,noKnee:/sin flexion[^.;]*rodilla|no doblar[^.;]*rodilla/,seatedOnly:/solo sentado|unicamente sentado/};
 for(const [key,re]of Object.entries(constraints))if(re.test(plain)&&!(key==='noLoad'&&/con y sin (?:peso|carga)/.test(plain)))inferred[key]=true;
 const specific=r.group===chosen&&r.target?r.target:p.finalTarget||GoalGroups14.groups[chosen].target;
 return {chosen,priorities,weights,focus,target:specific,inferred,medical:r.medical,text,personalTitles:titles,reason:'Prioridad: '+GoalGroups14.groups[chosen].name+(priorities.length>1?'. Complemento: '+priorities.slice(1).map(g=>GoalGroups14.groups[g].name).join(', '):'')+'. '+(focus!=='full'?'Enfoque: '+({upper:'tren superior',lower:'piernas y glúteos',core:'zona media'}[focus]||focus)+'. ':'')+'Equipo, nivel, días, restricciones y registros ajustan la selección y su dosis.'};
}
function adjusted(p){if(!p)return p;const i=interpret(p),base=oldAdjusted({...p,finalTarget:i.target});return {...base,focus:i.focus,goalIntent24:i,restrictions:{...base.restrictions,...i.inferred}};}
function score(e,p,family){const i=p.goalIntent24||interpret(p),f=clean([e.name,e.family,e.group].join(' '));let n=0;
 for(const [g,w]of Object.entries(i.weights)){
  if(g==='build'&&e.category==='Fuerza')n+=w;
  if(g==='fitness'&&e.category==='Cardio')n+=w;
  if(g==='movement'&&e.category==='Movilidad')n+=w;
  if(g==='athletic'&&/agility|power|salt|salto|skater|shuffle|rapido|agilidad|potencia|pliomet/.test(f))n+=w*2;
 }
 if(i.focus==='lower'&&['Piernas','Cadera'].includes(e.group))n+=3;
 if(i.focus==='upper'&&['Pecho','Espalda','Hombros','Brazos'].includes(e.group))n+=3;
 if(i.focus==='core'&&['Abdomen','Core'].includes(e.group))n+=3;
 return n;
}
GoalPlans.adjusted=adjusted;
root.GoalContext24={interpret,adjusted,score,personal,clean};
})(window);

;
/* Biblioteca ampliada, filtro de gimnasio y progresión con evidencia confirmada. */
(function(root){'use strict';
const E=Engine,O=OwnRoutines18,T=TrainingProgression19,oldReasons=Adapt.reasons,oldDose=O.dose,oldChoose=T.choose,oldMake=E.makeCycle,oldOrganize=RoutinePlanner.organize;
const gymEquipment={barbell:'Barra y discos',cable:'Poleas',machine:'Máquinas de fuerza',smith:'Máquina Smith',cardio:'Cinta o ergómetro',bench:'Banco y soportes'};
function reasons(e,p,strict=true){
 if(!e)return ['Ejercicio desconocido'];const normalized=p?.equipment==='gym'?{...p,equipment:'db',bench:(p.gymEquipment23||[]).includes('bench')}:p;
 const list=oldReasons(e,normalized,strict);
 if(e.gymOnly23){if(p?.equipment!=='gym')list.push('Requiere perfil de gimnasio');for(const key of e.gymRequirements23||[])if(!(p?.gymEquipment23||[]).includes(key))list.push('Falta '+gymEquipment[key]);}
 if(p?.restrictions?.noLoad&&e.loaded23&&!list.includes('Carga externa excluida'))list.push('Carga externa excluida');
 if(e.pack23&&p&&T.tier(e.id)>T.level(p)&&!Adapt.mode(p).reduced)list.push('Variante fuera del escalón seleccionado');
 return [...new Set(list)];
}
Adapt.reasons=reasons;Adapt.allowed=(e,p)=>!reasons(e,p).length;Adapt.routineCompatible=(r,p)=>!!p&&Adapt.mode(p).auto&&r.items.every(x=>Adapt.allowed(E.byId(x.id),p));
function dose(e,p,kind){
 const v=oldDose(e,p,kind);if(!e.pack23)return v;
 if(O.gentle(p)||kind==='recovery')return {...v,sets:1,target:e.unit==='sec'?15:5,rest:T.rest(p)};
 const n=T.level(p),beginner=p.experience==='beginner',range=e.repRange19||[8,16],scale=TrainingIntensity.scale(p);
 const sets=beginner?2:[4,4,5][n],target=e.unit==='sec'?[30,40,50][n]:Math.min(range[1],range[0]+[4,2,0][n]);
 return {...v,sets,target:Math.max(e.unit==='sec'?10:3,Math.floor(target*scale)),rest:T.rest(p)};
}
O.dose=dose;TrainingIntensity.dose=dose;
const newBases=ExercisePack23.baseIds.map(id=>E.byId(id));
for(const e of newBases)T.chains.push([e.id,e.id+'-tempo23',e.id+'-pause23']);
T.chains.push(['pushup-control19','home-handrelease23','home-staggered23','home-diamond23']);
T.chains.push(['single-squat19','home-pistol-box23','home-skater-squat23','home-shrimp23']);
T.chains.push(['plankreach19','home-body-saw23','home-bear-tap23']);
T.chains.push(['singlebridge-pause19','home-ham-slide23','home-ham-slide-one23']);
const familyGroups48={push:['Pecho','Hombros'],pull:['Espalda','Brazos'],legs:['Piernas','Cadera'],core:['Abdomen','Core'],full:['Piernas','Cadera','Pecho','Espalda','Hombros','Brazos','Abdomen'],control:['Piernas','Cadera','Pecho','Espalda','Hombros','Brazos','Abdomen'],cardio:['Cardio'],intervals:['Cardio'],agility:['Cardio'],power:['Cardio'],mobility:['Movilidad'],balance:['Movilidad'],seated:['Movilidad']};
function familyMatches(e,family){return (familyGroups48[family]||[]).includes(e.group);}
// Only cache immutable startup template construction, never user eligibility.
let constructing48=true;const startupPools48=new Map();
function pool(p,family='full',includeVariants=true){
 const key=constructing48?JSON.stringify([p,family,includeVariants]):null;
 if(key&&startupPools48.has(key))return startupPools48.get(key).slice();
 const n=T.level(p);let list=E.exercises.filter(e=>!e.sourceOnly&&familyMatches(e,family)&&T.tier(e.id)<=n&&Adapt.allowed(e,p)&&(includeVariants||e.variant==='base'));
 if(p.equipment==='gym')list=list.filter(e=>e.gymOnly23);
 else list=list.filter(e=>!e.gymOnly23);
 list.sort((a,b)=>Math.abs(n-T.tier(a.id))-Math.abs(n-T.tier(b.id))||Number(!a.pack23)-Number(!b.pack23));
 if(key)startupPools48.set(key,list.slice());return list;
}
function balanced(list,count,rotation=0,p=null){
 if(!list.length)return [];const groups=[...new Set(list.map(e=>e.group))],used=new Set(),out=[];
 for(let i=0;i<count;i++){const group=groups[i%groups.length],same=list.filter(e=>e.group===group&&!used.has(e.baseId));let candidates=same.length?same:list.filter(e=>!used.has(e.baseId));if(!candidates.length)break;const highest=Math.max(...candidates.map(e=>T.tier(e.id)));candidates=candidates.filter(e=>T.tier(e.id)===highest);if(p){const best=Math.max(...candidates.map(e=>GoalContext24.score(e,p)));candidates=candidates.filter(e=>GoalContext24.score(e,p)===best);}const e=candidates[(Math.abs(rotation)+Math.floor(i/groups.length))%candidates.length];out.push(e);used.add(e.baseId);}
 return out;
}
function choose(p,kind,rotation=0){
 p=GoalContext24.adjusted(p);
 if(!Adapt.mode(p).auto)return [];
 const count=Math.min(7,p.trainingMode==='routines'?5:p.dailyMissions||4),focus=p.focus==='upper'?'push':p.focus==='lower'?'legs':p.focus==='core'?'core':'full';
 if(O.gentle(p)&&p.equipment!=='gym')return oldChoose(p,kind,rotation);
 let list=pool(p,kind==='cardio'?'cardio':kind==='recovery'?'mobility':focus);
 if(p.focus==='upper'&&kind!=='cardio'&&kind!=='recovery')list=[...pool(p,'push'),...pool(p,'pull')];
 if(!list.length&&p.equipment==='gym'&&kind==='cardio')list=pool(p,'full');
 if(!list.length)return oldChoose(p,kind,rotation).filter(x=>Adapt.allowed(E.byId(x.id),p));
 return balanced(list,kind==='recovery'?Math.min(3,count):count,rotation,p).map(e=>dose(e,p,kind));
}
Schedule.items=choose;
const gymFamilies={
 full:{name:'Cuerpo completo en gimnasio',groups:['Piernas','Pecho','Espalda','Cadera','Abdomen'],goals:['build','fitness','habit']},
 upper:{name:'Tren superior en gimnasio',groups:['Pecho','Espalda','Hombros','Brazos'],goals:['build','fitness']},
 lower:{name:'Piernas y glúteos en gimnasio',groups:['Piernas','Cadera'],goals:['build','athletic']},
 push:{name:'Empuje: pecho, hombros y tríceps',groups:['Pecho','Hombros','Brazos'],goals:['build']},
 pull:{name:'Tirón: espalda y bíceps',groups:['Espalda','Brazos'],goals:['build']},
 glutes:{name:'Cadena posterior en gimnasio',groups:['Cadera','Piernas','Espalda'],goals:['build','athletic']},
 arms:{name:'Brazos y hombros en gimnasio',groups:['Brazos','Hombros'],goals:['build']},
 core:{name:'Zona media con poleas y máquinas',groups:['Abdomen','Espalda'],goals:['build','movement','habit']},
 conditioning:{name:'Acondicionamiento en gimnasio',groups:['Cardio','Piernas','Espalda'],goals:['fitness','athletic','habit']}
};
const gyms=[];
for(const [family,spec]of Object.entries(gymFamilies))for(let n=0;n<3;n++)for(const extension of ['standard','long']){
 const p={equipment:'gym',gymEquipment23:Object.keys(gymEquipment),difficulty:O.keys[n],experience:'regular',age:30,health:{pain:'none'},injuries:[],restrictions:{},trainingScale:1,bench:true};
 let available=E.exercises.filter(e=>e.gymOnly23&&spec.groups.includes(e.group)&&T.tier(e.id)<=n&&Adapt.allowed(e,p));
 if(family==='push')available=available.filter(e=>e.group!=='Brazos'||e.recipe23==='tricep');
 if(family==='pull')available=available.filter(e=>e.group!=='Brazos'||e.recipe23==='curl');
 available.sort((a,b)=>Math.abs(n-T.tier(a.id))-Math.abs(n-T.tier(b.id)));
 const selected=balanced(available,extension==='long'?6:5,n),items=selected.map(e=>dose(e,p,family==='conditioning'?'cardio':'strength'));
 if(!items.length)throw Error('Rutina de gimnasio vacía: '+family+' '+n);
 const r={id:'gym23-'+family+'-'+n+'-'+extension,name:spec.name+' · '+O.names[n]+(extension==='long'?' · Extendida':''),category:family==='conditioning'?'Cardio':'Fuerza',level:n,items,requirements:[],gymOnly23:true,gymRequirements23:[...new Set(selected.flatMap(e=>e.gymRequirements23))],family:'gym-'+family,goalGroups:spec.goals,extension,impact:'Sin saltos',planVersion:23,description:'Rutina original completa de Hunter System: preparación, orden de ejercicios, series, descansos y vuelta a la calma. Usa resistencia controlable y ajusta la sesión a tus registros.',minutes:O.estimate(items)};
 gyms.push(r);
}
// Las mismas materias de casa incorporan los patrones nuevos y conservan sus IDs.
const homes=O.routines.map(r=>{
 const n=r.level,material=r.requirements.includes('db')?'db':'body',p={equipment:material,difficulty:O.keys[n],experience:'regular',age:30,health:{pain:'none'},injuries:[],restrictions:{},trainingScale:1,bench:r.requirements.includes('bench'),step:r.requirements.includes('step')};
 // Mantener las materias técnicas: cardio no sustituye saltos, equilibrio ni movilidad.
 if(['cardio','intervals','agility','power','mobility','balance','seated'].includes(r.family))return {...r,planVersion:23};
 let list=pool(p,r.family);if(!list.length)return {...r,planVersion:23};
 const selected=balanced(list,O.lengths[r.extension].count,n),items=selected.map(e=>{const x=dose(e,p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength');return {...x,sets:Math.max(1,Math.ceil(x.sets*O.lengths[r.extension].ratio))};});
 return {...r,items,requirements:[...new Set(selected.flatMap(e=>e.requirements))],minutes:O.estimate(items),planVersion:23};
});
constructing48=false;startupPools48.clear();
O.routines.splice(0,O.routines.length,...homes);Catalog.routines=[...homes,...gyms];Catalog.baseRoutines=Catalog.routines.length;
function adjust(r,p){return r.items.map(x=>{const e=E.byId(x.id),v=dose(e,p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength');return {...x,...v,sets:O.gentle(p)?1:r.extension==='brief'?Math.max(1,Math.ceil(v.sets*.5)):v.sets};});}
O.adjust=adjust;
function assign(c,p,logs={},now=E.dateKey(),last=null){
 if(!Adapt.mode(p).auto||c.reviews?.at(-1)?.action==='pausar')return 0;let changed=0;const ap=GoalPlans.adjusted(p),n=T.level(p),group=GoalGroups14.of(p),intent=GoalContext24.interpret(p),pattern=[...(GoalPlans.plans[intent.target]||GoalPlans.plans.general).pattern];let ordinal=0,lastStrength=false;
 if(intent.priorities.length>1){const extra=intent.priorities[1];pattern[1]=['fitness','athletic'].includes(extra)?'cardio':extra==='movement'?'recovery':'strength';}
 for(const [i,d]of c.days.entries()){
  const choice=p.weekPlan?.[Schedule.weekday(d.date)];let desired=choice==='rest'?'rest':choice==='mobility'?'recovery':choice==='cardio'?'cardio':'strength';lastStrength=desired==='strength';
  if(d.date<now||d.kind==='rest'||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;
  d.kind=desired;let items;
  if(p.trainingMode==='routines'){
   let list=(p.equipment==='gym'?gyms:homes).filter(r=>r.level===n&&r.extension==='standard'&&r.goalGroups.some(g=>intent.priorities.includes(g))&&Adapt.routineCompatible(r,ap));
   if(ap.focus==='upper')list=list.filter(r=>p.equipment==='gym'?['gym-upper','gym-push','gym-pull','gym-arms'].includes(r.family):['push','pull'].includes(r.family));
   if(ap.focus==='lower')list=list.filter(r=>p.equipment==='gym'?['gym-lower','gym-glutes'].includes(r.family):r.family==='legs');
   if(ap.focus==='core')list=list.filter(r=>p.equipment==='gym'?r.family==='gym-core':r.family==='core');
   const appropriate=list.filter(r=>d.kind==='recovery'?r.category==='Movilidad':d.kind==='cardio'?r.category==='Cardio':!['Cardio','Movilidad'].includes(r.category));
   list=(d.kind==='recovery'||d.kind==='cardio')?appropriate:appropriate.length?appropriate:list;
   if(list.length){const score=r=>r.items.reduce((sum,x)=>sum+GoalContext24.score(E.byId(x.id),ap),0)/r.items.length;const best=Math.max(...list.map(score));list=list.filter(r=>score(r)>=best-.01);}
   if(list.length){const r=list[(i+c.index)%list.length];items=adjust(r,ap);d.title=r.name;d.autoTemplateId=r.id;}
  }
  if(!items?.length)items=choose(ap,d.kind,i+c.index*7);
  if(!items.length)continue;
  const existing={days:[{items:d.items||[]}]};
  items=items.map(x=>{let next=T.carry(T.carry(x,last,ap),existing,ap);if(c.engineVersion23!==23&&c.reviews?.at(-1)?.adherence>=.8&&!O.gentle(ap))next=T.applyRecommendation(next,AdaptiveV9.recommend(logs,next,ap,now),ap);return next;});
  if(c.reviews?.at(-1)?.action==='reducir')items=items.map(x=>({...x,target:Math.max(E.unitOf(x)==='sec'?10:3,Math.floor(x.target*.8))}));
  d.items=items;d.gym23=p.equipment==='gym';d.own18=true;d.own19=true;d.tasks=[];
  if(p.trainingMode!=='routines')d.title=(p.equipment==='gym'?'Misiones de gimnasio':'Misiones personales')+' · '+O.names[n];
  d.goalIntent24=intent.reason;d.planningNote=O.gentle(ap)?'Dosis reducida y movimientos compatibles con las restricciones declaradas.':'Biblioteca ampliada · '+O.names[n]+': patrones, apoyos, cadencia y revisión semanal por registros confirmados.';changed++;
 }
 c.engineVersion23=23;return changed;
}
E.makeCycle=function(p,start,index,last,logs={}){const c=oldMake(p,start,index,last,logs);assign(c,p,logs,start,last);return c;};
RoutinePlanner.organize=function(c,p,source,own,logs,now){if(c.engineVersion23===23)return 0;return assign(c,p,logs,now);};
root.Training23={gymEquipment,gymFamilies,gymRoutines:gyms,homeRoutines:homes,pool,choose,dose,adjust,assign,reasons};
})(window);

