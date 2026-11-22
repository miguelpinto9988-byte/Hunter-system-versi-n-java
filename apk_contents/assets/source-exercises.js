/* Modelos propios de los movimientos específicos de la secuencia documentada. */
(function(root){
 'use strict';
 const E=root.Engine,clone=x=>JSON.parse(JSON.stringify(x)),mix=(a,b,q)=>a.map((v,i)=>v+(b[i]-v)*q),cycle=t=>(1-Math.cos(t*Math.PI*2))/2;
 function standing(){return {p:[[360,190],[360,250],[360,420],[285,335],[280,440],[435,335],[440,440],[320,550],[320,680],[400,550],[400,680]],equipment:[],loads:[],detail:{}};}
 function arm(f,side,elbow,hand){f.p[side?5:3]=elbow;f.p[side?6:4]=hand;}
 function press(f,q,front=false,side=0){const sign=side?1:-1,base=front?35:85;arm(f,side,mix([360+sign*base,275],[360+sign*50,160],q),mix([360+sign*base,205],[360+sign*55,70],q));}
 function curl(f,q,lateral=false){for(let side=0;side<2;side++){const sign=side?1:-1;arm(f,side,[360+sign*(lateral?85:65),340],mix([360+sign*(lateral?110:65),440],[360+sign*(lateral?95:65),265],q));}}
 function plank(left,right){const q=(left+right)/2,f=standing();
  f.p[0]=mix([220,350],[230,445],q);f.p[1]=mix([270,380],[280,470],q);f.p[2]=mix([460,488],[470,530],q);
  f.p[7]=mix([550,540],[550,555],q);f.p[8]=[625,580];f.p[9]=mix([545,535],[545,550],q);f.p[10]=[615,570];
  arm(f,0,mix([270,480],[285,580],left),mix([270,580],[200,580],left));arm(f,1,mix([285,475],[305,570],right),mix([285,570],[220,570],right));return f;
 }
 function frame(kind,i){const t=i/48,q=cycle(t),half=i<24,sub=(i%24)/24,s=cycle(sub);let f=standing();
  if(kind==='alternate-press'){press(f,half?s:0,false,0);press(f,half?0:s,false,1);f.detail.grip='Alterna un brazo y luego el otro';}
  if(kind==='overhead-triceps'){for(let side=0;side<2;side++){const sign=side?1:-1;arm(f,side,mix([360+sign*40,125],[360+sign*22,135],q),mix([360+sign*5,200],[360+sign*5,55],q));}f.loads=[[360,(f.p[4][1]+f.p[6][1])/2]];f.detail.grip='Dos manos · una mancuerna';}
  if(kind==='arnold'){for(let side=0;side<2;side++){const sign=side?1:-1,start=[[360+sign*35,300],[360+sign*40,220]],middle=[[360+sign*85,260],[360+sign*85,190]],end=[[360+sign*50,160],[360+sign*55,70]],a=q<.45?start:middle,b=q<.45?middle:end,k=q<.45?q/.45:(q-.45)/.55;arm(f,side,mix(a[0],b[0],k),mix(a[1],b[1],k));}f.detail.grip=q<.35?'Palmas hacia ti':'Palmas al frente';}
  if(kind==='lateral-curl'||kind==='curl-combo'){curl(f,kind==='curl-combo'?s:q,kind==='lateral-curl'||half);f.detail.grip=kind==='curl-combo'&&!half?'Agarre martillo':'Curl con brazos abiertos';}
  if(kind==='raise-combo'){for(let side=0;side<2;side++){const sign=side?1:-1;if(half)arm(f,side,mix([360+sign*65,340],[360+sign*125,265],s),mix([360+sign*80,440],[360+sign*205,275],s));else arm(f,side,mix([360+sign*65,340],[420+sign*55,265],s),mix([360+sign*80,440],[500+sign*55,245],s));}f.detail.grip=half?'Elevación lateral':'Elevación frontal · vista oblicua';}
  if(kind==='standing-fly'){for(let side=0;side<2;side++){const sign=side?1:-1,x=360+sign*(120-100*q);arm(f,side,[x,260],[x,155]);}f.detail.grip='Acerca y separa los codos';}
  if(kind==='press-combo'){press(f,s,half,0);press(f,s,half,1);f.detail.grip=half?'Press con codos al frente':'Press con codos a los lados';}
  if(kind==='kickback'){f.p[0]=[435,270];f.p[1]=[410,320];f.p[2]=[320,450];f.p[7]=[300,570];f.p[8]=[295,700];f.p[9]=[360,570];f.p[10]=[365,700];arm(f,0,[355,345],[360-85*q,425-43*q]);arm(f,1,[340,353],[345-85*q,433-43*q]);f.detail.grip='Extiende ambos codos hacia atrás';}
  if(kind==='plank-up-down'){const a=Math.min(1,s*2),b=Math.max(0,s*2-1);f=plank(half?a:b,half?b:a);f.detail.grip=half?'Comienza con el brazo izquierdo':'Comienza con el brazo derecho';}
  if(kind==='knee-push-child'){const a=MotionDB.kneepush,b=MotionDB.childpose;if(i<20)f=clone(a.frames[Math.floor(i*48/20)%48]);else if(i<24){const p=a.frames[0],z=b.frames[0];f=clone(p);f.p=p.p.map((x,j)=>mix(x,z.p[j],(i-20)/4));}else if(i<44)f=clone(b.frames[Math.floor((i-24)*48/20)%48]);else{const p=b.frames[0],z=a.frames[0];f=clone(p);f.p=p.p.map((x,j)=>mix(x,z.p[j],(i-44)/4));}f.detail.grip=i<24?'Flexión con apoyo en rodillas':'Postura del niño';}
  if(!['plank-up-down','knee-push-child','overhead-triceps'].includes(kind))f.loads=[f.p[4].slice(),f.p[6].slice()];return f;
 }
 const rows=[
  ['pau-altpress','overheadpress','Press de hombros alterno','alternate-press','Eleva una mancuerna mientras mantienes la otra cerca del hombro. Regresa y alterna el brazo.'],
  ['pau-overtriceps','lyingtricep','Extensión de tríceps sobre la cabeza','overhead-triceps','Sujeta una mancuerna con ambas manos por encima de la cabeza. Dobla los codos para descenderla detrás de la cabeza y vuelve a extenderlos.'],
  ['pau-arnold','overheadpress','Press Arnold','arnold','Empieza con las palmas hacia ti y los codos flexionados. Gira las manos al elevar las cargas; baja invirtiendo el movimiento.'],
  ['pau-lateralcurl','curl','Curl de bíceps lateral','lateral-curl','Orienta las palmas hacia afuera. Flexiona los codos con los brazos ligeramente abiertos y baja con control.'],
  ['pau-curlcombo','curl','Curl lateral y curl martillo','curl-combo','Realiza un curl lateral completo. Cambia al agarre martillo y realiza otro curl; continúa alternando.'],
  ['pau-raisecombo','lateral','Elevación lateral y frontal','raise-combo','Eleva las cargas a los lados y regresa. Después elévalas al frente y regresa; alterna ambas acciones.'],
  ['pau-standingfly','chestopen','Apertura y cierre de pecho de pie con mancuernas','standing-fly','Mantén los codos a la altura de los hombros y los antebrazos hacia arriba. Acerca los codos al frente y vuelve a abrirlos.'],
  ['pau-presscombo','overheadpress','Press frontal y press lateral','press-combo','Haz un press con los codos al frente y vuelve al inicio. Abre los codos a los lados, haz otro press y vuelve a empezar.'],
  ['pau-kickback','tricep','Patada de tríceps con ambas mancuernas','kickback','Inclina el torso con las rodillas ligeramente flexionadas. Mantén los brazos junto al torso y extiende ambos codos hacia atrás; regresa.'],
  ['pau-plankup','plank','Plancha alta a plancha baja','plank-up-down','Desde plancha sobre las manos, apoya un antebrazo y después el otro. Vuelve apoyando una mano y luego la otra; alterna el brazo que inicia.'],
  ['pau-pushchild','kneepush','Flexión con rodillas y postura del niño','knee-push-child','Haz una flexión con las rodillas apoyadas. Lleva después la cadera hacia atrás con los brazos extendidos para pasar a postura del niño y vuelve al inicio.']
 ];
 for(const [id,base,name,kind,steps] of rows){
  const e={...clone(E.byId(base)),id,baseId:id,name,variant:'base',sourceOnly:true,family:base,steps,instructions:[steps],unit:'sec',sides:false,variantDescription:'Movimiento específico de la secuencia documentada de Pau Fit. Esquema propio; revisión visual del video pendiente.',cue:'Utiliza un movimiento controlado. El esquema no observa tu postura.',control:'Mantén un recorrido que controles.',setup:'Consulta la preparación y ejecución de este movimiento.',breath:'Respira durante el movimiento sin retener el aire.'};
  if(kind==='overhead-triceps'){e.floor=false;e.overhead=true;e.zones=[...E.byId('overheadpress').zones];}
  if(kind==='standing-fly'){e.category='Fuerza';e.group='Pecho';e.equipment='db';e.requirements=['db'];e.level=1;e.muscles='Pecho y hombros';e.zones=[...E.byId('overheadpress').zones];}
  if(kind==='knee-push-child'){e.kneeBend=true;e.zones=[...new Set([...e.zones,...E.byId('childpose').zones])];}
  E.exercises.push(e);MotionDB[id]={name,hold:false,frames:Array.from({length:48},(_,i)=>frame(kind,i))};
 }
 root.SourceExercises12=rows.map(([id])=>id);Catalog.baseExercises=E.exercises.filter(e=>e.variant==='base').length;
})(typeof window!=='undefined'?window:globalThis);
