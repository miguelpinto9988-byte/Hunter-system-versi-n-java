/* Ocho fichas autorizadas por captura. Las variantes conservan su modelo V36. */
(function(root){'use strict';
 const ids=new Set(['dbbridge','plankreach19','home-thruster-db23','home-clean-db23','home-rack-lunge23','gym-landmine-press23','gym-pullassist30','home-hopforward30']);
 const before=Motion30.get,cache=new WeakMap(),copy=x=>JSON.parse(JSON.stringify(x));
 const add=(a,b)=>a.map((v,i)=>v+b[i]),mix=(a,b,t)=>a.map((v,i)=>v+(b[i]-v)*t),ik=Rig2D23.ik;
 const ease=x=>(1-Math.cos(Math.PI*Math.max(0,Math.min(1,x))))/2;
 function track(t,keys){let i=keys.findIndex(k=>k[0]>=t);if(i<=0)return keys[0][1];const lo=keys[i-1],hi=keys[i];return mix(lo[1],hi[1],ease((t-lo[0])/(hi[0]-lo[0])));}
 function head(p){const dx=p[1][0]-p[2][0],dy=p[1][1]-p[2][1],d=Math.hypot(dx,dy);p[0]=[p[1][0]+60*dx/d,p[1][1]+60*dy/d];}
 function rack(f,u=0){
  for(const [el,hand,o]of [[3,4,0],[5,6,-13]]){const s=add(f.p[1],[o,o]);f.p[hand]=add(s,[42-22*u,-7-181*u]);f.p[el]=ik(s,f.p[hand],100,-1);}
  f.loads=[copy(f.p[4]),copy(f.p[6])];
 }
 function correct(e,source){
  if(!ids.has(e.id)||source.motionRevision37===37)return source;
  if(cache.has(source))return cache.get(source);
  const m=copy(source),id=e.id;
  if(id==='plankreach19'){m.view21={...m.view21,primary:'side',label:'Lateral',project:null,inset:'reach37',reason:'Mano bajo el hombro y un brazo libre cada vez.'};m.posture23='prone';m.facing23='left';}
  if(id==='gym-pullassist30')m.view21.inset='pull37';
  if(id==='gym-landmine-press23')m.view21.inset='landmine37';
  for(let i=0;i<m.frames.length;i++){
   const f=m.frames[i],p=f.p,t=i/48,a=(1-Math.cos(t*2*Math.PI))/2;
   f.detail.correction37=id;f.detail.cycle37=t;
   if(id==='dbbridge'){
    p[0]=[140,684];p[1]=[200,684];p[2]=[365-10*a,665-59*a];p[8]=[515,700];p[10]=[502,687];
    for(const [el,ha,knee,ankle,o]of [[3,4,7,8,0],[5,6,9,10,-13]]){
     const shoulder=add(p[1],[o,o]);p[ha]=add(p[2],[-12+o,-20+o]);p[el]=ik(shoulder,p[ha],88,-1);p[knee]=ik(add(p[2],[o,o]),p[ankle],135,1);
    }
    f.loads=[add(p[2],[-7,-18])];f.detail.floor23=715;f.detail.feet30={8:[1,0],10:[1,0]};
   }else if(id==='plankreach19'){
    p[1]=[210,490];p[2]=[380,562];head(p);p[8]=[640,672];p[10]=[627,659];p[7]=mix(p[2],p[8],.5);p[9]=mix(add(p[2],[-13,-13]),p[10],.5);
    const near=t<.5?Math.sin(t*2*Math.PI)**2:0,far=t>=.5?Math.sin(t*2*Math.PI)**2:0;
    for(const [el,ha,u,o]of [[3,4,near,0],[5,6,far,-13]]){const s=add(p[1],[o,o]),angle=Math.PI/2+2.05*u;p[el]=add(s,[95*Math.cos(angle),95*Math.sin(angle)]);p[ha]=add(s,[190*Math.cos(angle),190*Math.sin(angle)]);}
    f.loads=[];f.equipment=[];f.detail.front19=false;f.detail.topShoulder19=false;f.detail.support23=false;f.detail.floor23=688;f.detail.feet30={8:[-.3,.95],10:[-.3,.95]};f.detail.reach37=near+far;
    f.detail.stage18=near>.15?'ALCANZA · APOYO EN LA OTRA MANO':far>.15?'ALTERNA · PELVIS ESTABLE':'DOS MANOS BAJO LOS HOMBROS';
   }else if(id==='home-thruster-db23'){
    const depth=track(t,[[0,[0]],[.3,[1]],[.55,[0]],[1,[0]]])[0];p[2]=[350-70*depth,420+130*depth];p[1]=add(p[2],[170*Math.sin(.55*depth),-170*Math.cos(.55*depth)]);head(p);p[8]=[365,700];p[10]=[352,687];p[7]=ik(p[2],p[8],140,1);p[9]=ik(add(p[2],[-13,-13]),p[10],140,1);const u=track(t,[[0,[0]],[.55,[0]],[.78,[1]],[1,[0]]])[0];rack(f,u);
    f.detail.stage18=t<.5?'SENTADILLA · CARGA EN LOS HOMBROS':u>.1?'EXTIENDE Y EMPUJA SOBRE LA CABEZA':'REGRESA LA CARGA A LOS HOMBROS';
   }else if(id==='home-rack-lunge23'){
    rack(f);f.detail.stage18='ZANCADA · MANCUERNAS EN LOS HOMBROS';
   }else if(id==='home-clean-db23'){
    const v=track(t,[[0,[315,500,.58,0]],[.28,[350,420,0,0]],[.48,[350,468,0,1]],[.65,[350,420,0,1]],[.8,[350,420,0,1]],[1,[315,500,.58,0]]]);
    p[2]=[v[0],v[1]];p[1]=add(p[2],[170*Math.sin(v[2]),-170*Math.cos(v[2])]);head(p);
    p[8]=[375,700];p[10]=[362,687];p[7]=ik(p[2],p[8],140,1);p[9]=ik(add(p[2],[-13,-13]),p[10],140,1);
    for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){const s=add(p[1],[o,o]);p[ha]=add(s,mix([8,192],[42,-7],v[3]));p[el]=ik(s,p[ha],100,-1);}
    f.loads=[copy(p[4]),copy(p[6])];f.detail.floor23=715;f.detail.feet30={8:[1,0],10:[1,0]};f.detail.stage18=v[3]>.9?'RECIBE EN LOS HOMBROS · CODOS DELANTE':t<.4?'EXTIENDE CADERAS · CARGA CERCA DEL CUERPO':'DESCIENDE CON CONTROL';
   }else if(id==='gym-landmine-press23'){
    p[1]=[350,250];p[2]=[350,420];head(p);p[8]=[375,700];p[10]=[292,687];p[7]=ik(p[2],p[8],140,1);p[9]=ik(add(p[2],[-13,-13]),p[10],140,1);
    const angle=1.06+.28*a;p[4]=[650-530*Math.cos(angle),715-530*Math.sin(angle)];p[3]=ik(p[1],p[4],105,-1);p[5]=[325,392];p[6]=[330,480];
    f.loads=[];f.equipment=[['bounds23',[95,95,690,735]]];f.detail.floor23=715;f.detail.feet30={8:[1,0],10:[1,0]};f.detail.stage18='EMPUJA EN ARCO · SUJETA EL EXTREMO';
   }else if(id==='gym-pullassist30'){
    const rise=160*a;p[1]=[360,290-rise];p[2]=[360,460-rise];head(p);p[4]=[250,105];p[6]=[470,105];p[3]=ik([318,p[1][1]],p[4],100,1);p[5]=ik([402,p[1][1]],p[6],100,-1);
    p[7]=[330,595-rise];p[9]=[390,595-rise];p[8]=[334,637-rise];p[10]=[394,637-rise];f.loads=[];f.detail.padY30=608-rise;f.detail.feet30={8:[0,1],10:[0,1]};f.detail.stage18='MANOS FIJAS · RODILLAS EN LA PLATAFORMA';
   }else if(id==='home-hopforward30'){
    const v=track(t,[[0,[0,500,0,.2,1.8]],[.18,[0,548,0,.3,2.4]],[.34,[30,420,0,.05,-.4]],[.5,[75,360,85,.08,-.2]],[.64,[110,545,0,.3,.6]],[.8,[110,420,0,0,1.65]],[1,[0,500,0,.2,1.8]]]);
    p[2]=[320+v[0],v[1]];p[1]=add(p[2],[170*Math.sin(v[3]),-170*Math.cos(v[3])]);head(p);
    const walk=t>.8?Math.sin((t-.8)/.2*2*Math.PI):0;p[8]=[365+v[0]+10*walk,700-v[2]-12*Math.max(0,walk)];p[10]=[352+v[0]-10*walk,687-v[2]-12*Math.max(0,-walk)];
    p[7]=ik(p[2],p[8],140,1);p[9]=ik(add(p[2],[-13,-13]),p[10],140,1);
    for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){const s=add(p[1],[o,o]),angle=v[4];p[el]=add(s,[95*Math.cos(angle),95*Math.sin(angle)]);p[ha]=add(s,[185*Math.cos(angle),185*Math.sin(angle)]);}
    f.loads=[];f.detail.floor23=715;f.detail.feet30={8:[1,0],10:[1,0]};f.detail.stage18=t<.25?'PREPARA · CADERAS Y RODILLAS':t<.4?'IMPULSO HACIA DELANTE':t<.6?'VUELO · BRAZOS AL FRENTE':t<.8?'ATERRIZA SUAVE · FLEXIONA':'REGRESO CAMINANDO';
   }
  }
  m.motionRevision37=37;cache.set(source,m);return m;
 }
 Motion30.get=function(id){const x=before(id);return {...x,model:correct(x.e,x.model)};};
 const extras=Motion30.extras,loads=Rig2D23.loads,inset=Motion30.inset;
 const line=(a,b,w=6)=>'<path d="M'+a.join(',')+'L'+b.join(',')+'" fill="none" stroke="#7a9aa7" stroke-width="'+w+'" stroke-linecap="round"/>';
 Motion30.extras=function(f,accent){
  if(f.detail.correction37==='gym-landmine-press23'){
   const q=f.p[4],plate=mix(q,[650,715],.06);return '<g data-apparatus37="landmine">'+line([650,715],q,10)+'<circle cx="'+plate[0]+'" cy="'+plate[1]+'" r="24" fill="#243d50" stroke="'+accent+'" stroke-width="3"/><circle cx="650" cy="715" r="13" fill="#243d50" stroke="#7a9aa7" stroke-width="4"/></g>';
  }
  if(f.detail.correction37==='gym-pullassist30')return '<g data-apparatus37="assisted-pullup">'+line([130,725],[600,725],8)+line([180,725],[180,100],7)+line([540,725],[540,100],7)+line([180,105],[540,105],10)+line([298,f.detail.padY30],[422,f.detail.padY30],18)+line([500,f.detail.padY30],[500,720],5)+'</g>';
  return extras(f,accent);
 };
 Rig2D23.loads=function(f,...args){if(f.detail.correction37==='gym-landmine-press23')return '';return loads(f,...args);};
 Motion30.inset=function(kind,f,phase,accent){
  const id=f.detail.correction37;if(!ids.has(id)||!['reach37','pull37','landmine37'].includes(kind))return inset(kind,f,phase,accent);
  let title,note,art;
  if(kind==='reach37'){
   title='VISTA SUPERIOR · ALTERNA UN BRAZO';note='Una mano sostiene el cuerpo; alcanza con la otra sin girar la pelvis.';
   const t=f.detail.cycle37,near=t<.5?Math.sin(t*2*Math.PI)**2:0,far=t>=.5?Math.sin(t*2*Math.PI)**2:0;
   art='<path d="M155 664V699M145 701L137 717M165 701L173 717" stroke="#dceaf0" stroke-width="9" fill="none"/><circle cx="155" cy="651" r="9" fill="#243d50"/>'+line([140,667],[140-9*near,667-30*near],7)+line([170,667],[170+9*far,667-30*far],7);
  }else if(kind==='pull37'){
   title='DE LADO · RODILLAS SOBRE EL APOYO';note='El apoyo acompaña el ascenso. Los pies quedan libres detrás de las rodillas.';
   const rise=12*(1-Math.cos(phase/48*2*Math.PI))/2;art='<path d="M95 643H220M117 648V718" stroke="#7a9aa7" stroke-width="4" fill="none"/><circle cx="157" cy="'+(660-rise)+'" r="8" fill="#edc5ad"/><path d="M157 '+(671-rise)+'V'+(690-rise)+'L180 '+(701-rise)+'H198M180 '+(701-rise)+'L166 '+(715-rise)+'" stroke="#dceaf0" stroke-width="8" fill="none"/><path d="M171 '+(708-rise)+'H202" stroke="'+accent+'" stroke-width="6"/>';
  }else{
   title='BARRA ANCLADA · AGARRE CONTINUO';note='La mano sigue el arco de la barra. El anclaje permanece fijo en el suelo.';
   const a=(1-Math.cos(phase/48*2*Math.PI))/2,q=[154+22*a,679-14*a];art=line([215,716],q,5)+'<circle cx="215" cy="716" r="6" fill="'+accent+'"/><circle cx="'+q[0]+'" cy="'+q[1]+'" r="7" fill="#edc5ad"/>';
  }
  return '<g data-detail37="'+kind+'"><rect x="58" y="620" width="604" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="74" y="642" font-size="13" fill="'+accent+'" font-family="sans-serif">'+title+'</text>'+art+'<text x="74" y="720" font-size="12" fill="#b0c6d1" font-family="sans-serif">'+note+'</text></g>';
 };
 root.Motion37={ids,correct,version:37};Motion2D18.clearBounds();
})(window);
