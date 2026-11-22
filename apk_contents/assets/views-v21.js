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
