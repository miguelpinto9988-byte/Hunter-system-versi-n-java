/* Variaciones técnicas explícitas: cada ficha comparte la anatomía y las restricciones del movimiento de origen. */
(function(root){
const rows=[
['altcurl','curl','Curl alterno con mancuernas','alternate','Flexiona un codo manteniendo el otro brazo abajo. Baja y cambia de brazo.'],
['althammer','hammer','Curl martillo alterno','alternate','Mantén las palmas enfrentadas. Flexiona un brazo, baja y cambia de lado.'],
['singlelateral','lateral','Elevación lateral unilateral','unilateral','Eleva solamente un brazo; el otro permanece junto al cuerpo. Completa ambos lados.'],
['singlefront','frontlift','Elevación frontal unilateral','unilateral','Eleva un brazo al frente sin superar una altura cómoda. Baja y completa el otro lado.'],
['singlepress','press','Press de suelo unilateral','unilateral','Empuja una mancuerna con un brazo mientras el otro descansa. Evita girar el torso y completa ambos lados.'],
['altfloorpress','press','Press de suelo alterno','alternate','Empuja una carga y vuelve a bajar antes de alternar con la otra. Mantén estable el tronco.'],
['singleoverhead','overheadpress','Press de hombro unilateral','unilateral','Empuja una mancuerna, baja con control y completa el otro lado sin inclinar el tronco.'],
['altseatedpress','seatedpress','Press alterno sentado','alternate','Desde la posición sentada, eleva una carga y regresa antes de cambiar de brazo.'],
['altseatedcurl','seatedcurl','Curl alterno sentado','alternate','Sentado, flexiona un codo y regresa. Alterna sin balancear el cuerpo.'],
['singletricep','lyingtricep','Extensión de tríceps tumbado unilateral','unilateral','Mantén un brazo orientado hacia arriba. Flexiona y extiende solo ese codo; completa ambos lados.'],
['squathold','squat','Sentadilla con pausa inferior','pause','Haz una pausa de un segundo en la profundidad que controles. Sube sin rebote; reduce carga o repeticiones si es necesario.'],
['goblethold','goblet','Sentadilla goblet con pausa inferior','pause','Mantén la carga cerca del pecho y pausa un segundo al bajar. Sube con control.'],
['bridgehold','bridge','Puente de glúteos con pausa superior','pause','Pausa un segundo arriba, sin arquear la espalda, y baja suavemente.'],
['calfhold','calf','Elevación de talones con pausa superior','pause','Pausa un segundo con los talones elevados, usando apoyo si lo necesitas. Baja sin rebotes.'],
['rowhold','row','Remo unilateral con pausa junto a la cadera','pause','Pausa un segundo con la mancuerna junto a la cadera. Baja sin rotar el torso.'],
['splitpause','split','Sentadilla dividida con pausa inferior','pause','Mantén ambos pies fijos. Pausa un segundo abajo dentro de un recorrido cómodo y sube.'],
['slowmarch','march','Marcha con cambios de cadencia','interval','Alterna diez segundos de marcha cómoda y diez segundos ligeramente más rápida, sin saltar.'],
['sidestepinterval','sidestep','Pasos laterales por intervalos','interval','Alterna diez segundos de pasos cómodos y diez segundos más ágiles sin cruzar los pies.'],
['boxinterval','box','Boxeo al aire por intervalos','interval','Alterna diez segundos de golpes suaves y diez algo más ágiles. No bloquees los codos ni uses cargas.'],
['stepjackinterval','stepjack','Aperturas sin salto por intervalos','interval','Alterna diez segundos cómodos y diez algo más ágiles. Siempre conserva un pie apoyado.'],
['seatedmarchinterval','seatedmarch','Marcha sentada por intervalos','interval','Alterna diez segundos de marcha sentada cómoda y diez algo más ágil, sin balancear el torso.'],
['heelcontrolled','heel','Deslizamiento de talón con retorno lento','slow','Extiende una pierna deslizando el talón. Tarda tres segundos en recogerla y cambia de lado.'],
['birdcontrolled','bird','Extensión contralateral con retorno lento','slow','Extiende brazo y pierna contrarios. Regresa en tres segundos antes de cambiar de lado.'],
['abductcontrolled','standingabduct','Abducción con apoyo y retorno lento','slow','Separa una pierna sin inclinar el tronco y regresa en tres segundos. Completa ambos lados.']
];
root.NewExercises=[];
for(const [id,base,name,motion,extra] of rows){const source=Engine.byId(base);if(!source||!MotionDB[base])throw Error('Falta base '+base);
 const e={...source,id,baseId:id,name,variant:'base',family:base,variation:motion,level:Math.max(source.level,motion==='unilateral'?1:source.level),sides:motion==='unilateral'||source.sides,instructions:[...(source.instructions||[source.steps]),extra],steps:source.steps+' '+extra,cue:extra,variantDescription:extra};
 if(motion==='alternate')e.cue+=' Una acción de un brazo cuenta una repetición; alterna de forma equilibrada.';
 Engine.exercises.push(e);NewExercises.push(e);
 const original=MotionDB[base],frames=Array.from({length:48},(_,i)=>{let phase=i;if(motion==='pause')phase=i<18?i*24/18:i<30?24:24+(i-30)*24/18;if(motion==='slow')phase=i<12?i*2:24+(i-12)*24/36;
 let f=JSON.parse(JSON.stringify(original.frames[Math.floor(phase)%48]));
 if(['alternate','unilateral'].includes(motion)){let rest=original.frames[0],near=motion==='unilateral'||i<24;for(const k of near?[5,6]:[3,4])f.p[k]=rest.p[k].slice();if(motion==='alternate'){let a=original.frames[(i%24)*2];for(const k of near?[3,4]:[5,6])f.p[k]=a.p[k].slice();}f.loads=motion==='unilateral'?[f.p[4]]:[f.p[4],f.p[6]];}
 return f;});MotionDB[id]={...original,name,frames};
}
Catalog.baseExercises=Engine.exercises.filter(e=>e.variant==='base').length;
})(window);
(function(){const groups=[
['Brazos alternos',['altcurl','althammer','singletricep']],
['Piernas con pausa',['squathold','splitpause','calfhold']],
['Empuje unilateral',['singlepress','singleoverhead','singlelateral']],
['Cardio sin saltos',['slowmarch','sidestepinterval','boxinterval']],
['Control corporal',['bridgehold','birdcontrolled','heelcontrolled']],
['Mancuernas con control',['goblethold','rowhold','altfloorpress']],
['Trabajo sentado',['altseatedcurl','altseatedpress','seatedmarchinterval']],
['Movilidad y actividad',['abductcontrolled','slowmarch','stepjackinterval']]
];groups.forEach(([name,ids],i)=>{let es=ids.map(id=>Engine.byId(id)),items=es.map(e=>({id:e.id,sets:2,target:e.unit==='sec'?20:8,rest:90}));Catalog.routines.push({id:'v9-'+i,name,category:es.every(e=>e.category==='Cardio')?'Cardio':'Fuerza',level:Math.max(...es.map(e=>e.level)),items,description:'Sesión con variantes técnicas. Revisa la compatibilidad con tu perfil antes de aplicarla.',minutes:Catalog.duration?Catalog.duration(items):20,requirements:[...new Set(es.flatMap(e=>e.requirements))],impact:'Sin saltos'});});})();

;
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

;
/* Nueve ilustraciones originales para un circuito publicado sin equipamiento. */
(function(root){
'use strict';const E=Engine,clone=x=>JSON.parse(JSON.stringify(x)),cycle=t=>(1-Math.cos(t*Math.PI*2))/2;
function standing(){return {p:[[360,190],[360,250],[360,420],[285,335],[280,440],[435,335],[440,440],[320,550],[320,680],[400,550],[400,680]],equipment:[],loads:[],detail:{}};}
function arm(f,side,x,y){const elbow=side?5:3,hand=side?6:4;f.p[hand]=[x,y];f.p[elbow]=[(360+x)/2,(250+y)/2];}
function frame(kind,i){let f=standing(),t=i/48,q=cycle(t),theta=t*Math.PI*2;
 for(let side=0;side<2;side++){const sign=side?1:-1;
  if(kind==='world')arm(f,side,360+sign*190*Math.sin(q*Math.PI),440-365*q);
  if(kind==='forward'||kind==='backward'){const a=theta*(kind==='forward'?1:-1);arm(f,side,360+sign*(185+8*Math.cos(a)),250+16*Math.sin(a));}
  if(kind==='updown'){f.p[side?5:3]=[360+sign*(85-30*q),300-140*q];f.p[side?6:4]=[360+sign*65,225-150*q];}
  if(kind==='pulses'){const front=i>=24,x=front?470+sign*35:360+sign*180,y=260-10*cycle(t*4);arm(f,side,x,y);}
  if(kind==='fly')arm(f,side,360+sign*(190*(1-q))+110*q,250+5*sign*q);
  if(kind==='backswing'){arm(f,side,360+sign*65+100*Math.sin(theta),430-20*Math.abs(Math.sin(theta)));}
  if(kind==='raise'){if(side===0)arm(f,side,185,250);else{f.p[5]=[445-25*q,250-100*q];f.p[6]=[445-20*q,165-100*q];}}
  if(kind==='curl'){const front=i>=24,s=cycle((i%24)/24),x=front?460+sign*30:360+sign*175;f.p[side?5:3]=[x,260];f.p[side?6:4]=[x+sign*35*(1-s),350-170*s];}
 }
 f.detail.grip={world:'Eleva y baja ambos brazos',forward:'Círculos pequeños hacia delante',backward:'Círculos pequeños hacia atrás',updown:'Eleva los brazos y baja los codos',pulses:i<24?'Dos pulsos laterales':'Dos pulsos al frente',fly:'Abre y cierra los brazos',backswing:'Brazos extendidos hacia atrás',raise:'Un brazo lateral y otro se eleva',curl:i<24?'Curl con brazos a los lados':'Curl con brazos al frente'}[kind];return f;
}
const rows=[
 ['pau-armworld','world','Círculos amplios de brazos','Eleva ambos brazos y bájalos por los lados describiendo un círculo controlado.'],
 ['pau-armcircle-f','forward','Círculos de brazos hacia delante','Extiende los brazos lateralmente a la altura de los hombros y realiza círculos pequeños hacia delante.'],
 ['pau-armcircle-b','backward','Círculos de brazos hacia atrás','Mantén los brazos a la altura de los hombros y realiza círculos pequeños hacia atrás.'],
 ['pau-armupdown','updown','Elevación de brazos y descenso de codos','Eleva los brazos. Baja los codos hacia los lados y hacia atrás dentro de un recorrido cómodo.'],
 ['pau-armpulses','pulses','Pulsos laterales y frontales de brazos','Haz dos pulsos pequeños con los brazos a los lados; lleva los brazos al frente y haz dos pulsos más.'],
 ['pau-armfly','fly','Aperturas de brazos sin carga','Lleva los brazos al frente a la altura de los hombros, abre hacia los lados y regresa con control.'],
 ['pau-armsback','backswing','Extensión de brazos hacia atrás sin carga','Con los brazos extendidos hacia abajo, llévalos ligeramente al frente y después hacia atrás sin arquear la espalda.'],
 ['pau-armraise','raise','Elevación de un brazo con sostén lateral','Mantén un brazo extendido lateralmente y eleva el otro sobre la cabeza. Desciende el codo a la altura del hombro; utiliza el lado indicado en el bloque.'],
 ['pau-armcurl','curl','Curl de bíceps lateral y frontal sin carga','Con los brazos abiertos, flexiona y extiende los codos. Luego lleva los brazos al frente y repite la flexión; alterna ambas posiciones.']
];
for(const [id,kind,name,steps] of rows){const e={...clone(E.byId('chestopen')),id,baseId:id,name,variant:'base',family:'chestopen',sourceOnly:true,category:'Fuerza',group:'Hombros',equipment:'body',requirements:[],unit:'sec',sides:false,level:1,floor:false,overhead:['world','updown','raise'].includes(kind),spineMove:false,wristLoad:false,kneeBend:false,zones:['shoulder','elbow','back','hip','knee','ankle'],steps,instructions:[steps],cue:'Movimiento controlado dentro de un recorrido cómodo; detente si aparece dolor.',variantDescription:'Ilustración original con volumen 2.5D. Los lados se reflejan para la guía; la revisión visual del video está pendiente.'};E.exercises.push(e);MotionDB[id]={name,hold:false,frames:Array.from({length:48},(_,i)=>frame(kind,i))};}
root.SourceExercises13=rows.map(r=>r[0]);Catalog.baseExercises=E.exercises.filter(e=>e.variant==='base').length;
})(window);

;
/* Fichas propias: progresiones domésticas. No son rutinas atribuidas a YouTube. */
(function(root){'use strict';
const specs=[
 ['lowhop','Saltos bajos con aterrizaje controlado','rope',1,'power','reps','Pies paralelos, rodillas ligeramente flexionadas. Haz un salto de poca altura y aterriza suave sobre ambos pies. Estabiliza antes de repetir.','Haz 4 saltos de calidad; la altura no es el objetivo.'],
 ['squatjump','Sentadilla con salto y pausa de aterrizaje','squat',2,'power','reps','Haz una sentadilla de recorrido cómodo. Impúlsate en un salto corto, aterriza con caderas y rodillas flexionadas y haz una pausa antes de repetir.','Detén la serie si pierdes equilibrio o el aterrizaje deja de ser suave.'],
 ['skaterhop','Salto lateral de patinador','skater',2,'power','reps','Impúlsate lateralmente y aterriza sobre el pie contrario con la rodilla flexible. Mantén la posición antes de cambiar de lado.','Recorrido corto; cada salto cuenta como una repetición.'],
 ['linehop','Saltos cortos sobre una línea','rope',1,'power','reps','Imagina una línea en el suelo. Salta de un lado al otro con ambos pies y aterriza con control. Mantén la amplitud pequeña.','Despeja el suelo y usa una superficie estable.'],
 ['fastfeet','Pasos rápidos en el lugar','fastmarch',1,'agility','sec','Alterna apoyos cortos y rápidos en el lugar, sin saltar alto. Mantén rodillas suaves, tronco estable y brazos acompañando.','Prioriza precisión antes de aumentar la cadencia.'],
 ['shuffle','Desplazamiento lateral de agilidad','sidestep',1,'agility','sec','Da dos pasos laterales cortos sin cruzar los pies y vuelve. Flexiona ligeramente las rodillas y mantén el centro de gravedad estable.','Necesitas un espacio despejado de dos pasos.'],
 ['highknees','Carrera en el lugar con rodillas altas','jog',2,'agility','sec','Corre en el lugar alternando las rodillas hasta una altura que controles. Acompaña con el brazo contrario y aterriza suave.','No aceleres si necesitas inclinarte hacia atrás.'],
 ['lungeknee','Zancada atrás y elevación de rodilla','reverselunge',1,'strength','reps','Da una zancada hacia atrás. Regresa al apoyo inicial y eleva la rodilla libre con una pausa de equilibrio. Repite y cambia de lado.','Apoyo estable; puedes tocar una pared si lo necesitas.'],
 ['stepcross','Pasos cruzados de coordinación','frontbackstep',1,'agility','sec','Da un paso diagonal corto, vuelve al centro y cambia de lado. Acompaña con el brazo contrario, sin girar bruscamente la rodilla.','Los pasos son cortos y deliberados.'],
 ['skaterreach','Paso lateral con alcance contralateral','skater',1,'agility','sec','Da un paso lateral sin salto. Lleva la mano contraria hacia el muslo del lado de apoyo y vuelve al centro. Alterna.','Flexiona desde cadera y rodilla, sin forzar el giro del tronco.'],
 ['dbmarch','Marcha del granjero con mancuernas','farmerwalk',1,'strength','sec','Sujeta las mancuernas a los lados. Alterna pasos cortos en el lugar, manteniendo hombros relajados y tronco estable.','Usa una carga controlable; no balancees las mancuernas.'],
 ['ankledrive','Balanceo controlado de tobillo de pie','calfstretch',0,'mobility','reps','Frente a una pared y con las manos apoyadas, acerca suavemente una rodilla hacia la pared sin levantar el talón. Vuelve y cambia de lado.','Recorrido cómodo; no empujes a través del dolor.']
];
const ids=[];
for(const [id,name,base,level,family,unit,steps,cue] of specs){
 const template=Engine.byId(base),e={...JSON.parse(JSON.stringify(template)),id,baseId:id,variant:'base',name,level,family,unit,steps,cue,sourceOnly:false,variantDescription:'Movimiento propio del catálogo. Revisa preparación, ejecución y compatibilidad.',category:family==='strength'?'Fuerza':family==='mobility'?'Movilidad':'Cardio',impact:family==='power'||id==='highknees'?'Con saltos':'Sin saltos'};
 if(family==='power')Object.assign(e,{kneeBend:true,zones:['back','hip','knee','ankle'],requirements:[],floor:false,wristLoad:false,equipment:'body',sides:false});
 if(id==='lungeknee')e.sides=true;
 if(id==='ankledrive')e.sides=true;
 e.setup=steps.split('. ')[0]+'.';e.instructions=steps.split(/\.\s+/).filter(Boolean);e.breath='Respira con regularidad, sin mantener el aire.';e.tempo=family==='power'?'Haz una pausa estable entre saltos.':'Controla el movimiento antes de acelerar.';e.control=cue;
 Engine.exercises.push(e);ids.push(id);
 const m=JSON.parse(JSON.stringify(MotionDB[base]));m.name=name;m.id=id;
 if(id==='squatjump'){m.frames=m.frames.map((f,i)=>{const a=i/48*Math.PI*2,jump=Math.max(0,-Math.sin(a))*70;f.p=f.p.map(p=>[p[0],p[1]-jump]);return f;});}
 if(id==='linehop')m.frames=m.frames.map((f,i)=>{const dx=Math.sin(i/48*Math.PI*2)*40;f.p=f.p.map(p=>[p[0]+dx,p[1]]);return f;});
 if(id==='skaterhop')m.frames=m.frames.map((f,i)=>{const lift=Math.abs(Math.sin(i/48*Math.PI*2))*35;f.p=f.p.map(p=>[p[0],p[1]-lift]);return f;});
 if(id==='highknees')m.frames=m.frames.map((f,i)=>{const q=Math.sin(i/48*Math.PI*2);for(const k of [7,8])f.p[k][1]-=Math.max(0,q)*65;for(const k of [9,10])f.p[k][1]-=Math.max(0,-q)*65;return f;});
 if(id==='lungeknee')m.frames=m.frames.map((f,i)=>{const q=Math.max(0,Math.cos(i/48*Math.PI*2));f.p[7][1]-=q*85;f.p[8][1]-=q*80;return f;});
 if(id==='stepcross')m.frames=m.frames.map((f,i)=>{f.p[8][0]+=Math.sin(i/48*Math.PI*2)*25;return f;});
 if(id==='ankledrive'){m.hold=false;m.frames=m.frames.map((f,i)=>{const a=(1-Math.cos(i/48*Math.PI*2))/2;f.p[7][0]+=a*24;f.p[7][1]-=a*10;return f;});}
 MotionDB[id]=m;
}
root.NewExercises14=ids;
})(window);

;
/* El catálogo completo se carga antes de validar los registros guardados. */
(function(){const parent=Engine.byId('frontlift'),seated=MotionDB.seatedcurl,standing=MotionDB.frontlift;
 const e={...parent,id:'front-seated-reference',baseId:'front-seated-reference',name:'Elevación frontal con mancuernas sentado en banco',variant:'base',variantDescription:'Variante sentada con apoyo estable y demostración 2D.',seated:true,requiresFloor:false,bench:true,requirements:['db','bench'],steps:'Siéntate con la espalda apoyada en el banco y los pies firmes. Mantén los codos ligeramente flexionados. Eleva las mancuernas al frente hasta la altura de los hombros y baja con control.',setup:'Banco estable con respaldo, mancuernas ligeras y pies apoyados.',cue:'Evita el balanceo y el dolor. Usa una carga que puedas controlar.',originalVideo15:false};
 Engine.exercises.push(e);MotionDB[e.id]={...standing,id:e.id,frames:standing.frames.map((f,i)=>{const pose=JSON.parse(JSON.stringify(seated.frames[i]));for(const j of [3,4,5,6])pose.p[j]=[...f.p[j]];pose.loads=[pose.p[4],pose.p[6]].map(p=>[...p]);return pose;})};
})();
/* Variantes propias con cadencia explícita. No representan nuevos diagnósticos. */
(function(root){'use strict';
// Ajustes del esquema 2D: apoyo claro de rodillas y despegue/aterrizaje
// diferenciados del movimiento de sentadilla. Trayectorias ilustrativas.
MotionDB.kneepush.frames.forEach(f=>{f.p[8][1]=610;f.p[10][1]=615;});
const upright=MotionDB.squat.frames[0],crouch=MotionDB.squat.frames[24];
const jumpKeys=[[0,upright,0],[9,crouch,0],[17,upright,0],[24,upright,-85],[31,crouch,0],[40,upright,0],[48,upright,0]];
MotionDB.squatjump.frames=Array.from({length:48},(_,i)=>{let k=0;while(jumpKeys[k+1][0]<i)k++;const a=jumpKeys[k],b=jumpKeys[k+1],t=(i-a[0])/(b[0]-a[0]),u=t*t*(3-2*t),f=JSON.parse(JSON.stringify(a[1]));f.p=a[1].p.map((p,j)=>p.map((v,n)=>v+(b[1].p[j][n]-v)*u+(n===1?a[2]+(b[2]-a[2])*u:0)));f.detail.jumpPhase18=i<17?'Preparación':i<31?'Despegue':'Aterrizaje';return f;});
MotionDB.linehop.frames.forEach((f,i)=>{const dx=35*Math.sin(i/48*Math.PI*2);f.p.forEach(p=>p[0]+=dx);});
const specifications=[
 ['squat','Sentadilla con descenso de tres segundos'],['goblet','Sentadilla goblet con descenso controlado'],
 ['rdl','Peso muerto rumano con descenso de tres segundos'],['press','Press de suelo con retorno de tres segundos'],
 ['neutral','Press neutro con retorno controlado'],['benchpress','Press de banca con retorno de tres segundos'],
 ['overheadpress','Press de hombros con descenso controlado'],['incline','Flexión inclinada con descenso de tres segundos'],
 ['kneepush','Flexión con rodillas y descenso controlado'],['curl','Curl de bíceps con descenso de tres segundos'],
 ['hammer','Curl martillo con descenso controlado'],['row','Remo unilateral con retorno de tres segundos'],
 ['bentrow','Remo bilateral con retorno controlado'],['bridge','Puente de glúteos con pausa de dos segundos'],
 ['calf','Elevación de talones con pausa de dos segundos'],['split','Sentadilla dividida con descenso controlado'],
 ['standingabduct','Abducción de cadera con pausa de dos segundos'],['legextend','Extensión de rodilla sentada con pausa'],
 ['bird','Bird dog con pausa de dos segundos'],['deadbug','Dead bug con extensión controlada']
];
const added=[];
for(const [parentId,name] of specifications){
 const parent=Engine.byId(parentId),original=MotionDB[parentId],pause=['bridge','calf','standingabduct','legextend','bird'].includes(parentId),id=parentId+'-control18';
 const cadence=pause?6:5,frames=Array.from({length:48},(_,i)=>{
  const t=i/48,phase=pause?(t<1/3?t*72:t<2/3?24:24+(t-2/3)*72):(t<.4?t*60:24+(t-.4)*40);
  const a=original.frames[Math.floor(phase)%48],b=original.frames[(Math.floor(phase)+1)%48],blend=phase%1;
  const f={...JSON.parse(JSON.stringify(a)),p:a.p.map((p,j)=>p.map((v,k)=>v+(b.p[j][k]-v)*blend)),loads:a.loads.map((p,j)=>p.map((v,k)=>v+((b.loads[j]||p)[k]-v)*blend))};
  f.detail.stage18=pause?(t<1/3?'EJECUCIÓN CONTROLADA':t<2/3?'PAUSA DE DOS SEGUNDOS':'REGRESO CONTROLADO'):(t<.4?'EJECUCIÓN CONTROLADA':'DESCENSO DE TRES SEGUNDOS');return f;
 });
 const description=pause?'Realiza la fase de esfuerzo en dos segundos, mantén dos segundos y regresa en dos segundos.':'Realiza la fase de esfuerzo en dos segundos y el descenso o regreso en tres segundos.';
 const e={...parent,id,baseId:id,variant:'base',originalVideo15:false,name,tempo:description,tempoSeconds18:cadence,variantDescription:'Variante de cadencia propia. '+description,steps:parent.steps+' '+description,instructions:[...parent.instructions,description],cue:parent.cue+' Mantén el control durante toda la repetición.'};
 delete e.media;delete e.demo;Engine.exercises.push(e);MotionDB[id]={...original,id,name,frames,hold:false};added.push(id);
}
root.ExerciseExpansion18={added,source:'https://www.acefitness.org/resources/everyone/exercise-library/'};
})(window);

;
/* Movimientos y variantes 2D propios. Fuentes técnicas enlazadas; dosis de diseño. */
(function(root){
'use strict';
const clone=x=>JSON.parse(JSON.stringify(x)),added=[];
const sources={
 pushup:'https://www.nasm.org/resource-center/exercise-library/push-up',
 decline:'https://www.nasm.org/resource-center/exercise-library/decline-push-up',
 pike:'https://www.nasm.org/resource-center/exercise-library/pike-push-up',
 archer:'https://www.nasm.org/resource-center/exercise-library/archer-push-up',
 unilateral:'https://www.acefitness.org/resources/everyone/exercise-library/329/single-leg-romanian-deadlift/',
 singleRdl:'https://www.acefitness.org/resources/everyone/exercise-library/127/single-arm-single-leg-romanian-dead-lift/',
 singleSquat:'https://www.nasm.org/resource-center/exercise-library/single-leg-squat',
 shoulder:'https://www.acefitness.org/resources/everyone/exercise-library/249/prone-scapular-shoulder-stabilization-series-i-y-t-w-o-formation/',
 library:'https://www.acefitness.org/resources/everyone/exercise-library/'
};
function add(id,parentId,name,level,instructions,extra={},frames){
 const parent=Engine.byId(parentId),original=MotionDB[parent.baseId||parentId];
 if(!parent||!original)throw Error('Falta movimiento padre: '+parentId);
 const e={...clone(parent),id,baseId:id,name,level,variant:'base',originalVideo15:false,sourceOnly:false,
  steps:instructions.join(' '),instructions,variantDescription:'Variante propia con trayectoria 2D específica. '+(extra.variantDescription||''),
  techniqueSources19:[sources.library],...extra};
 delete e.media;delete e.demo;e.progressionTier19=level;
 Engine.exercises.push(e);MotionDB[id]={...clone(original),id,name,instructions,frames:frames||clone(original.frames),hold:e.unit==='sec'&&original.hold};
 added.push(id);return e;
}
function timed(parentId,mapper,hold=false){
 const m=MotionDB[parentId];
 return Array.from({length:48},(_,i)=>{const phase=mapper(i/48),a=m.frames[Math.floor(phase)%48],b=m.frames[(Math.floor(phase)+1)%48],t=phase%1,f=clone(a);
  f.p=a.p.map((p,j)=>p.map((v,k)=>v+(b.p[j][k]-v)*t));f.loads=a.loads.map((p,j)=>p.map((v,k)=>v+((b.loads[j]||p)[k]-v)*t));
  f.detail.stage18=hold?(i<16?'SUBIDA CONTROLADA':i<32?'PAUSA · SIN PERDER LA POSTURA':'RETORNO CONTROLADO'):(i<32?'DESCENSO DE TRES SEGUNDOS':'SUBIDA CONTROLADA');return f;});
}
function oneAndHalf(parentId){const frames=timed(parentId,t=>t<.3?t*80:t<.5?24-(t-.3)*60:t<.7?12+(t-.5)*60:24+(t-.7)*80);for(let i=0;i<48;i++)frames[i].detail.stage18=i<15?'RECORRIDO COMPLETO':i<24?'REGRESO HASTA LA MITAD':i<34?'MEDIO RECORRIDO ADICIONAL':'REGRESO COMPLETO';return frames;}
function keyframes(keys){
 return Array.from({length:48},(_,i)=>{let k=0;while(keys[k+1][0]<i)k++;const a=keys[k],b=keys[k+1],t=(i-a[0])/(b[0]-a[0]),u=t*t*(3-2*t),f=clone(a[1]);
  f.p=f.p.map((p,j)=>p.map((v,n)=>v+(b[1].p[j][n]-v)*u));f.loads=f.loads.map((p,j)=>p.map((v,n)=>v+((b[1].loads[j]||p)[n]-v)*u));return f;});
}
// Una flexión convencional pertenece al escalón intermedio del programa;
// las variantes asimétricas y con cambio de apoyo forman el avanzado.
for(const e of Engine.exercises)if(e.baseId==='pushup')e.level=1;
add('pushup-control19','pushup','Flexión de suelo con descenso de tres segundos',1,[
 'Apoya las manos bajo los hombros y las puntas de los pies; mantén cabeza, tronco y pelvis alineados.',
 'Acerca el pecho al suelo durante tres segundos, con los codos en diagonal respecto al torso.',
 'Empuja con control durante dos segundos. Termina la serie al perder la alineación.'
],{tempoSeconds18:5,techniqueSources19:[sources.pushup]},timed('pushup',t=>t<.6?t*40:24+(t-.6)*60));
const decline=clone(MotionDB.pushup.frames);
for(const f of decline){
 const lift=100;for(const j of [2,7,9])f.p[j][1]-=lift*(j===2?.5:.82);f.p[8][1]-=lift;f.p[10][1]-=lift;
 f.equipment=[['bench',[525,570,650,710]]];
}
add('decline19','pushup','Flexión con pies elevados en banco',2,[
 'Coloca las puntas de los pies sobre un banco estable y las manos en el suelo bajo los hombros.',
 'Mantén el abdomen firme y baja el pecho flexionando los codos sin hundir la pelvis.',
 'Empuja para regresar. Usa primero flexiones de suelo si no puedes conservar la posición.'
],{requirements:['bench'],bench:true,techniqueSources19:[sources.decline],repRange19:[6,12],prerequisite19:'Control de la flexión de suelo.'},decline);
const pikeUp={p:[[240,460],[285,490],[420,330],[280,570],[275,665],[260,570],[250,665],[475,480],[565,665],[450,485],[540,665]],equipment:[],loads:[],detail:{}},
 pikeDown={p:[[210,585],[275,560],[420,365],[325,610],[275,665],[300,620],[250,665],[475,505],[565,665],[450,510],[540,665]],equipment:[],loads:[],detail:{}};
add('pike19','pushup','Flexión pica para hombros',2,[
 'Apoya manos y pies en el suelo y eleva la cadera formando una V invertida.',
 'Flexiona los codos para acercar la cabeza al suelo, conservando la cadera elevada.',
 'Empuja y regresa a la V. No apoyes el peso en la cabeza ni fuerces el cuello.'
],{group:'Hombros',overhead:true,repRange19:[5,10],techniqueSources19:[sources.pike],prerequisite19:'Flexiones de suelo y control de hombros.'},keyframes([[0,pikeUp],[24,pikeDown],[48,pikeUp]]));
// Vista frontal: un brazo se flexiona y el contrario permanece extendido.
const archer=clone(MotionDB.pushup.frames);
for(let i=0;i<48;i++){
 const a=(1-Math.cos(i/48*Math.PI*2))/2,f=archer[i];f.p=[[360-62*a,285+45*a],[360-62*a,350+45*a],[360-40*a,490+20*a],[275-45*a,410+65*a],[195,515],[480-15*a,440+20*a],[570,515],[325,575],[320,690],[405,575],[410,690]];
 f.detail.front19=true;f.detail.stage18=i<24?'CARGA HACIA UN BRAZO':'REGRESO AL CENTRO';
}
add('archer19','pushup','Flexión arquero',2,[
 'En plancha alta, separa las manos más que en una flexión normal; apoya ambos pies.',
 'Lleva el pecho hacia una mano mientras flexionas ese codo y alargas el brazo contrario.',
 'Vuelve al centro y realiza el otro lado. Cuenta una repetición por lado y evita girar la pelvis.'
],{sides:true,repRange19:[4,8],techniqueSources19:[sources.archer],prerequisite19:'Flexiones de suelo con buena estabilidad.'},archer);
for(const [id,parent,name] of [
 ['squat-15-19','squat','Sentadilla de una repetición y media'],
 ['goblet-15-19','goblet','Sentadilla goblet de una repetición y media'],
 ['split-15-19','split','Sentadilla dividida de una repetición y media'],
 ['row-15-19','row','Remo unilateral de una repetición y media'],
 ['overhead-15-19','overheadpress','Press de hombros de una repetición y media']
]){
 const p=Engine.byId(parent),leg=['Piernas','Cadera'].includes(p.group);
 add(id,parent,name,2,[p.instructions[0],leg?'Baja con control, sube hasta la mitad, vuelve a bajar y después sube por completo. Ese ciclo cuenta como una repetición.':'Completa el esfuerzo, vuelve hasta la mitad, repite la fase de esfuerzo y regresa por completo. Ese ciclo cuenta como una repetición.',
  'Conserva el mismo recorrido y evita rebotes. Reduce la carga si pierdes el control.'],
 {repRange19:[6,10],tempoSeconds18:6,techniqueSources19:[sources.library],variantDescription:'Un ciclo completo y medio recorrido adicional por repetición.'},oneAndHalf(parent));
}
const singleSquat=clone(MotionDB.squat.frames);
for(const f of singleSquat){const hip=f.p[2];f.p[9]=[hip[0]+88,hip[1]+48];f.p[10]=[hip[0]+120,hip[1]+118];f.p[8]=[375,700];}
add('single-squat19','squat','Sentadilla a una pierna',2,[
 'Apoya todo un pie, eleva la otra pierna y extiende los brazos para equilibrarte.',
 'Flexiona cadera y rodilla de la pierna apoyada hasta una profundidad que puedas controlar.',
 'Empuja con ese pie para volver a subir. Completa un lado y después el otro.'
],{sides:true,repRange19:[5,10],prerequisite19:'Sentadilla y sentadilla dividida con estabilidad.',techniqueSources19:[sources.singleSquat]},singleSquat);
const singleHinge=clone(MotionDB.rdl.frames);
for(let i=0;i<48;i++){const f=singleHinge[i],a=(1-Math.cos(i/48*Math.PI*2))/2,hip=f.p[2];f.p[9]=[hip[0]-10-90*a,hip[1]+105*(1-a)-20*a];f.p[10]=[hip[0]-215*a,700-210*a];f.loads=[];}
add('singlehinge-free19','singlehinge','Bisagra de cadera a una pierna sin apoyo',2,[
 'Apoya una pierna, deja su rodilla ligeramente flexionada y mantén la pelvis nivelada.',
 'Lleva la cadera atrás al inclinar el tronco y alargar la pierna libre hacia atrás.',
 'Vuelve a la posición inicial sin apoyar el pie libre hasta terminar la repetición. Cambia de lado.'
],{sides:true,repRange19:[6,12],techniqueSources19:[sources.unilateral]},singleHinge);
const singleLoad=clone(singleHinge);for(const f of singleLoad)f.loads=[clone(f.p[4])];
add('single-rdl19','rdl','Peso muerto rumano a una pierna con mancuerna',2,[
 'Sujeta una mancuerna con la mano contraria a la pierna de apoyo; mantén esa rodilla ligeramente flexionada.',
 'Lleva la cadera atrás mientras la pierna libre se alarga y la mancuerna baja cerca de la pierna apoyada.',
 'Vuelve a ponerte de pie sin girar la pelvis. Haz todas las repeticiones y cambia de lado.'
],{sides:true,repRange19:[6,12],techniqueSources19:[sources.singleRdl]},singleLoad);
const sideFull=clone(MotionDB.sideplank.frames);
for(const f of sideFull){f.p[7]=[500,625];f.p[8]=[615,675];f.p[9]=[482,620];f.p[10]=[598,670];}
add('full-sideplank19','sideplank','Plancha lateral con piernas extendidas',2,[
 'Apoya un antebrazo bajo el hombro y el lateral de los pies, con las piernas extendidas.',
 'Eleva la pelvis y conserva una línea desde cabeza a pies mientras respiras.',
 'Baja con control, descansa y repite al otro lado.'
],{unit:'sec',sides:true,repRange19:null},sideFull);
const sideLift=clone(sideFull);for(let i=0;i<48;i++){const f=sideLift[i],a=(1-Math.cos(i/48*Math.PI*2))/2;for(const j of [0,1,2,3,5,6,7,9])f.p[j][1]+=35*(1-a);f.detail.stage18=i<24?'ELEVA LA PELVIS':'BAJA CON CONTROL';}
add('sideplank-lift19','full-sideplank19','Elevación de pelvis en plancha lateral',2,[
 'Apoya antebrazo y lateral de los pies con piernas extendidas.',
 'Desde una pelvis ligeramente baja, eleva la cadera hasta alinear el cuerpo.',
 'Desciende unos centímetros con control sin colapsar el hombro. Completa ambos lados.'
],{unit:'reps',sides:true,repRange19:[6,12]},sideLift);MotionDB['sideplank-lift19'].hold=false;
const reach=clone(MotionDB.pushup.frames[0]);
const reachFrames=Array.from({length:48},(_,i)=>{const f=clone(reach),a=(1-Math.cos(i/48*Math.PI*4))/2,j=i<24?3:5,k=i<24?4:6;f.p[j]=[f.p[1][0]-65*a,f.p[1][1]+95*(1-a)];f.p[k]=[f.p[1][0]-150*a,f.p[1][1]+190*(1-a)];f.detail.stage18=i<24?'ALCANCE DE UN BRAZO':'CAMBIO DE BRAZO';return f;});
add('plankreach19','shouldertap','Plancha alta con alcance alterno',2,[
 'En plancha alta, separa ligeramente los pies y mantén la pelvis quieta.',
 'Eleva una mano y alarga el brazo al frente sin inclinar el torso.',
 'Vuelve a apoyar esa mano antes de cambiar de brazo. Cuenta cada alcance como una repetición.'
],{unit:'reps',sides:false,repRange19:[8,16],overhead:true},reachFrames);
for(const [id,parent,name,level] of [
 ['singlebridge-pause19','singlebridge','Puente a una pierna con pausa de dos segundos',2],
 ['singlecalf-pause19','singlecalf','Elevación de talón a una pierna con pausa',2],
 ['lateral-pause19','lateral','Elevación lateral con pausa y descenso controlado',2],
 ['curl-pause19','curl','Curl de bíceps con pausa y descenso controlado',2]
]){
 const p=Engine.byId(parent);add(id,parent,name,level,[p.instructions[0],p.instructions.slice(1).join(' '),'Mantén la fase de esfuerzo durante dos segundos y regresa durante dos segundos, sin balanceo.'],
 {tempoSeconds18:6,repRange19:[8,14]},timed(parent,t=>t<1/3?t*72:t<2/3?24:24+(t-2/3)*72,true));
}
// Plantillas vistas desde arriba para que W, T e Y no se superpongan.
function shoulderPose(letter,lift){
 const p=[[360,225],[360,295],[360,480],[260,345],[210,280],[460,345],[510,280],[333,585],[330,700],[387,585],[390,700]];
 if(letter==='T'){p[3]=[235,295];p[4]=[145,295];p[5]=[485,295];p[6]=[575,295];}
 if(letter==='Y'){p[3]=[270,210];p[4]=[220,145];p[5]=[450,210];p[6]=[500,145];}
 return {p,equipment:[],loads:[],detail:{topShoulder19:true,letter19:letter,lift19:lift,stage18:lift>.2?'ELEVA SUAVEMENTE LOS BRAZOS':'BRAZOS CERCA DEL SUELO'}};
}
MotionDB.prone.frames=Array.from({length:48},(_,i)=>shoulderPose('W',(1-Math.cos(i/48*Math.PI*2))/2));MotionDB.prone.view19='top';
const w=Engine.byId('prone');w.steps='Túmbate boca abajo con la frente sobre una toalla y el cuello alineado. Dobla los codos formando una W, separados unos 45 grados del torso. Eleva ligeramente manos y codos al juntar suavemente los omóplatos. Baja sin levantar el pecho ni arquear la espalda.';
w.instructions=w.steps.split('. ').filter(Boolean);w.setup='Frente apoyada y abdomen activo; vista superior de los brazos.';w.cue='Recorrido corto desde los hombros. Mantén el pecho apoyado.';w.techniqueSources19=[sources.shoulder];
for(const [id,name,letters,level] of [['w-t19','Secuencia escapular W–T',['W','T'],1],['y-t-w19','Secuencia escapular Y–T–W con pausas',['Y','T','W'],2]]){
 const frames=Array.from({length:48},(_,i)=>{const segment=48/letters.length,k=Math.floor(i/segment),t=(i%segment)/segment,f=shoulderPose(letters[k],t<.75?Math.sin(t/.75*Math.PI):0);if(t>.75){const next=shoulderPose(letters[(k+1)%letters.length],0),u=(t-.75)/.25;f.p=f.p.map((p,j)=>p.map((v,n)=>v+(next.p[j][n]-v)*u));}f.detail.stage18='FORMA '+letters[k]+' · '+(t<.75?'ELEVA Y CONTROLA':'CAMBIA CON LOS BRAZOS BAJOS');return f;});
 add(id,'prone',name,level,['Boca abajo, apoya la frente y conserva el pecho en el suelo.',
  'Forma '+letters.join(', después ')+' con los brazos. En cada forma, eleva las manos y codos unos centímetros y mantén dos segundos.',
  'Baja los brazos antes de cambiar de forma. Toda la secuencia cuenta como una repetición.'],
 {repRange19:[5,10],tempoSeconds18:letters.length*4,techniqueSources19:[sources.shoulder],variantDescription:'Más posiciones y coordinación escapular; no añade carga externa.'},frames);MotionDB[id].view19='top';
}
const shuffle=clone(MotionDB.shuffle.frames);for(let i=0;i<48;i++){const f=shuffle[i],a=(1-Math.cos(i/48*Math.PI*4))/2;for(const j of [0,1,2,3,4,5,6])f.p[j][1]+=a*30;f.detail.stage18=i<24?'DOS PASOS HACIA UN LADO':'CAMBIA DE DIRECCIÓN';}
add('shuffle-touch19','shuffle','Desplazamiento lateral con cambio de dirección',2,[
 'Marca dos referencias cercanas en el suelo y adopta una posición ligeramente flexionada.',
 'Da dos pasos laterales sin cruzar los pies hacia una referencia y frena de forma controlada.',
 'Cambia hacia la otra referencia. Mantén el espacio despejado y la mirada al frente.'
],{unit:'sec',family:'agility',kneeBend:true},shuffle);
add('skater-stick19','skaterhop','Salto de patinador con pausa de equilibrio',2,[
 'Desde una pierna, impulsa un salto lateral corto hacia la otra.',
 'Aterriza flexionando ligeramente la pierna y mantén el equilibrio durante dos segundos.',
 'Repite hacia el otro lado. Cada aterrizaje cuenta como una repetición.'
],{family:'power',repRange19:[4,8],tempoSeconds18:6},timed('skaterhop',t=>t<.3?t*80:t<.6?24:24+(t-.6)*60));
root.ExerciseExpansion19={added,sources,keyframes,timed};
})(window);

;
/* 100 patrones nuevos y dos variantes de cadencia por patrón: 300 fichas. */
(function(root){'use strict';
const clone=x=>JSON.parse(JSON.stringify(x));
const home=[
 ['diamond','Flexión diamante','closepush','push',2,{grip:'diamond'}],
 ['staggered','Flexión con manos escalonadas','pushup','push',2,{stagger:true}],
 ['spiderman','Flexión con rodilla hacia el codo','pushup','push',2,{kneeDrive:true}],
 ['handrelease','Flexión con despegue de manos','pushup','push',1,{release:true}],
 ['hindu','Flexión de recorrido ondulado','pushup','push',2,{dive:true,spineMove:true}],
 ['push-tap','Flexión y toque de hombro','pushup','push',2,{tap:true}],
 ['push-power','Flexión con empuje explosivo','pushup','push',2,{power:true,impact:'Con saltos'}],
 ['pike-bench','Flexión pica con pies en banco','pike19','pike',2,{bench:true}],
 ['push-one','Flexión unilateral con apoyo de mano auxiliar','pushup','push',2,{assistedOne:true}],
 ['cossack','Sentadilla cosaca','lateralunge','lateral-lunge',2,{deep:true}],
 ['skater-squat','Sentadilla de patinador a una pierna','single-squat19','single-squat',2,{rear:true}],
 ['shrimp','Sentadilla camarón con apoyo auxiliar','single-squat19','single-squat',2,{rear:true,support:true}],
 ['pistol-box','Sentadilla pistol hasta banco','single-squat19','single-squat',2,{bench:true}],
 ['pistol-db','Sentadilla pistol con contrapeso de mancuerna','single-squat19','single-squat',2,{db:true}],
 ['step-lateral','Subida lateral a plataforma','stepup','step',1,{lateral:true,step:true}],
 ['step-cross','Subida cruzada a plataforma','stepup','step',2,{cross:true,step:true}],
 ['curtsy','Zancada cruzada posterior','reverselunge','lunge',1,{cross:true}],
 ['walking-lunge','Zancadas caminando','reverselunge','lunge',1,{walking:true}],
 ['squat-lunge','Sentadilla y zancada posterior alternadas','split','squat-lunge',2,{}],
 ['lunge-front','Zancada hacia delante','reverselunge','lunge',1,{forward:true}],
 ['ham-slide','Curl femoral con toalla deslizante','bridge','ham-slide',1,{}],
 ['ham-slide-one','Curl femoral deslizante a una pierna','singlebridge','ham-slide',2,{single:true}],
 ['bridge-walk','Puente con pasos cortos de talones','bridge','ham-slide',1,{walkout:true}],
 ['bridge-long','Puente de glúteos con palanca larga','bridge','bridge',1,{long:true}],
 ['frog','Puente de glúteos con plantas juntas','bridge','bridge',0,{frog:true}],
 ['airplane','Bisagra con apertura de cadera y apoyo','singlehinge','hinge',2,{airplane:true,spineMove:true}],
 ['reverse-plank-lift','Elevación de pelvis en plancha invertida','bridge','reverse-plank',1,{}],
 ['reverse-knee','Plancha invertida con rodilla alterna','bridge','reverse-plank',2,{knee:true}],
 ['hollow-rock','Balanceo abdominal en posición hueca','deadbug','hollow',2,{rock:true,spineMove:true}],
 ['hollow-tuck','Extensión abdominal desde posición agrupada','deadbug','hollow',1,{tuck:true}],
 ['vup','Elevación abdominal en V','curlup','hollow',2,{vup:true,spineMove:true}],
 ['legraise','Elevación de piernas tumbado','deadbug','legraise',1,{}],
 ['flutter','Patadas alternas cortas tumbado','deadbug','legraise',1,{flutter:true}],
 ['scissors','Cruces controlados de piernas tumbado','deadbug','legraise',1,{scissors:true}],
 ['knee-tuck','Recogida de rodillas sentado con apoyo','curlup','knee-tuck',1,{}],
 ['side-thread','Plancha lateral con paso del brazo bajo el torso','full-sideplank19','side-plank',2,{thread:true,spineMove:true}],
 ['side-knee','Plancha lateral con recogida de rodilla','full-sideplank19','side-plank',2,{knee:true}],
 ['body-saw','Plancha de antebrazos con balanceo corto','plank','plank',2,{saw:true}],
 ['plank-toe','Plancha alta con toque lateral de punta','pushup','plank',1,{toe:true}],
 ['bear-tap','Cuadrupedia suspendida con toque de hombro','bearhold','bear',2,{tap:true}],
 ['bear-return','Caminata de oso adelante y atrás','bearcrawl','bear',1,{walk:true}],
 ['crab','Caminata de cangrejo','bridge','reverse-plank',2,{walk:true}],
 ['thruster-db','Sentadilla y press con mancuernas','doublefront','thruster',1,{db:true,overhead:true}],
 ['clean-db','Cargada de mancuernas hasta los hombros','goblet','clean',2,{db:true}],
 ['rack-lunge','Zancada con mancuernas en posición frontal','dbsplit','lunge',1,{db:true,rack:true}],
 ['renegade','Remo en plancha con mancuerna','row','renegade',2,{db:true}],
 ['renegade-knee','Remo en plancha con rodillas apoyadas','row','renegade',1,{db:true,knees:true}],
 ['zottman','Curl Zottman con mancuernas','curl','curl',1,{db:true,pronate:true}],
 ['hammer-cross','Curl martillo hacia el hombro contrario','hammer','curl',1,{db:true,cross:true}],
 ['floor-fly','Apertura de pecho con mancuernas en suelo','press','fly',1,{db:true}]
];
const gym=[
 ['bench-bb','Press de banca con barra','benchpress','bench',0,{gear:'barbell',bench:true}],
 ['close-bb','Press de banca con barra y agarre cerrado','benchpress','bench',1,{gear:'barbell',bench:true,close:true}],
 ['incline-bb','Press inclinado con barra','inclinebench','bench',1,{gear:'barbell',bench:true,incline:true}],
 ['decline-bb','Press declinado con barra','benchpress','bench',2,{gear:'barbell',bench:true,decline:true}],
 ['press-seated-bb','Press de hombros sentado con barra','seatedpress','overhead',1,{gear:'barbell',bench:true,seated:true}],
 ['press-bb','Press militar de pie con barra','overheadpress','overhead',1,{gear:'barbell'}],
 ['front-bb','Sentadilla frontal con barra','goblet','squat',2,{gear:'barbell',front:true}],
 ['back-bb','Sentadilla trasera con barra','squat','squat',2,{gear:'barbell',back:true}],
 ['box-bb','Sentadilla con barra hasta banco','chair','squat',1,{gear:'barbell',back:true,bench:true}],
 ['sumo-bb','Peso muerto sumo con barra','sumodead','deadlift',2,{gear:'barbell',wide:true}],
 ['dead-bb','Peso muerto convencional con barra','rdl','deadlift',2,{gear:'barbell',floorStart:true}],
 ['rdl-bb','Peso muerto rumano con barra','rdl','hinge',1,{gear:'barbell'}],
 ['thrust-bb','Hip thrust con barra y banco','hipthrust','hipthrust',1,{gear:'barbell',bench:true}],
 ['row-bb','Remo inclinado con barra en pronación','bentrow','row',1,{gear:'barbell'}],
 ['row-under-bb','Remo inclinado con barra en supinación','bentrow','row',1,{gear:'barbell',supinate:true}],
 ['pendlay','Remo Pendlay desde el suelo','bentrow','row',2,{gear:'barbell',floorStart:true}],
 ['landmine-press','Press unilateral en landmine','singleoverhead','landmine',1,{gear:'barbell',landmine:true}],
 ['landmine-row','Remo en landmine a dos manos','bentrow','row',1,{gear:'barbell',landmine:true}],
 ['curl-bb','Curl de bíceps con barra','curl','curl',0,{gear:'barbell'}],
 ['preacher-bb','Curl predicador con barra EZ','concentration','curl',1,{gear:'barbell',bench:true,preacher:true}],
 ['lat-wide','Jalón al pecho con agarre ancho','row','pulldown',0,{gear:'cable',wide:true,seated:true}],
 ['lat-neutral','Jalón al pecho con agarre neutro','row','pulldown',0,{gear:'cable',neutral:true,seated:true}],
 ['lat-under','Jalón al pecho con agarre supino','row','pulldown',1,{gear:'cable',supinate:true,seated:true}],
 ['straight-pull','Jalón con brazos rectos en polea','pullover','straight-pull',1,{gear:'cable'}],
 ['cable-row','Remo sentado en polea con agarre neutro','towelrow','cable-row',0,{gear:'cable',seated:true}],
 ['cable-high-row','Remo alto sentado en polea','towelrow','cable-row',1,{gear:'cable',seated:true,high:true}],
 ['cable-one-row','Remo unilateral en polea de pie','row','cable-row',1,{gear:'cable',single:true}],
 ['face-pull','Face pull con cuerda en polea','reversefly','face-pull',1,{gear:'cable'}],
 ['cable-fly','Apertura de pecho de pie en poleas','press','fly',0,{gear:'cable'}],
 ['cable-low-fly','Apertura ascendente desde poleas bajas','press','fly',1,{gear:'cable',low:true}],
 ['cable-high-fly','Apertura descendente desde poleas altas','press','fly',1,{gear:'cable',high:true}],
 ['rope-tricep','Extensión de tríceps con cuerda en polea','tricep','tricep',0,{gear:'cable'}],
 ['cable-one-tricep','Extensión unilateral de tríceps en polea','tricep','tricep',1,{gear:'cable',single:true}],
 ['cable-overhead','Extensión de tríceps sobre la cabeza en polea','pau-overtriceps','tricep',1,{gear:'cable',overhead:true}],
 ['cable-curl','Curl de bíceps desde polea baja','curl','curl',0,{gear:'cable'}],
 ['cable-lateral','Elevación lateral unilateral en polea','lateral','lateral',1,{gear:'cable',single:true}],
 ['wood-chop','Tracción diagonal de tronco en polea','standingcore','woodchop',1,{gear:'cable',spineMove:true}],
 ['pallof','Press Pallof en polea','standingcore','pallof',0,{gear:'cable'}],
 ['leg-press','Prensa de piernas horizontal','squat','legpress',0,{gear:'machine',seated:true}],
 ['hack','Sentadilla en máquina hack','squat','squat',1,{gear:'machine',hack:true}],
 ['smith-squat','Sentadilla en máquina Smith','squat','squat',1,{gear:'smith',back:true}],
 ['smith-calf','Elevación de talones en máquina Smith','calf','calf',0,{gear:'smith',back:true}],
 ['chest-machine','Press de pecho sentado en máquina','press','machine-press',0,{gear:'machine',seated:true}],
 ['pec-deck','Apertura de pecho en máquina pec deck','press','fly',0,{gear:'machine',seated:true}],
 ['leg-extension','Extensión de rodillas en máquina','legextend','leg-extension',0,{gear:'machine',seated:true}],
 ['ham-seated','Curl femoral sentado en máquina','standingcurl','hamcurl',0,{gear:'machine',seated:true}],
 ['ham-lying','Curl femoral tumbado en máquina','standingcurl','hamcurl',0,{gear:'machine',lying:true}],
 ['hip-machine','Abducción de cadera en máquina','seatedhip','hip-machine',0,{gear:'machine',seated:true}],
 ['treadmill','Caminata rápida en cinta de gimnasio','walk','treadmill',0,{gear:'cardio',cardio:true}],
 ['row-erg','Remo en ergómetro','row','erg-row',1,{gear:'cardio',cardio:true}]
];
const rules={
 push:['Apoya las manos y activa el abdomen para mantener cabeza, pelvis y piernas en el mismo plano.','Baja el pecho con control y conserva el apoyo de la variante elegida.','Empuja para regresar sin hundir la espalda ni bloquear los codos.'],
 pike:['Coloca las manos firmes y eleva la cadera formando una V invertida.','Flexiona los codos y acerca la cabeza al suelo sin cargar el cuello.','Empuja para volver a la V, manteniendo estable el apoyo de los pies.'],
 lunge:['Adopta una postura de zancada con el tronco estable y los pies orientados hacia delante.','Flexiona ambas rodillas; el talón delantero permanece apoyado y el posterior puede elevarse.','Empuja el suelo para regresar y cambia de lado cuando corresponda.'],
 squat:['Coloca los pies en una postura estable y sujeta la carga según la ficha.','Flexiona caderas y rodillas sin despegar los talones.','Extiende las piernas con control y conserva la alineación del tronco.'],
 'single-squat':['Busca un apoyo estable y traslada el peso a una sola pierna.','Desciende con control, usando el apoyo o contrapeso indicado.','Empuja con el pie de apoyo para subir sin girar la rodilla hacia dentro.'],
 hinge:['Mantén las rodillas ligeramente flexionadas y desplaza la cadera hacia atrás.','Baja el tronco conservando la columna estable y la carga cerca del cuerpo.','Extiende la cadera para volver sin arquear la espalda al terminar.'],
 row:['Adopta una bisagra estable y deja la carga debajo de los hombros.','Lleva los codos hacia atrás sin levantar el tronco.','Devuelve la carga a la posición inicial con control.'],
 bench:['Ajusta el banco y coloca pies, pelvis, espalda y cabeza en apoyos estables.','Acerca la barra al pecho con muñecas alineadas y codos controlados.','Empuja hacia arriba sin rebotar la carga ni perder los apoyos.'],
 overhead:['Sujeta la carga cerca de los hombros y mantén el abdomen firme.','Empuja por encima de la cabeza dentro de un recorrido cómodo.','Baja con control sin extender la zona lumbar.'],
 curl:['Mantén el tronco estable y los codos junto al cuerpo o en el apoyo indicado.','Flexiona los codos sin balancear los hombros.','Baja con control y conserva la posición de muñecas y antebrazos.'],
 fly:['Estabiliza el tronco y conserva una flexión suave de codos.','Abre o aproxima los brazos en el plano indicado por la demostración.','Regresa con control sin llevar los hombros a un recorrido doloroso.'],
 pulldown:['Ajusta el apoyo del asiento y sujeta la barra por delante de la cabeza.','Acerca la barra al pecho llevando los codos hacia abajo.','Devuelve la barra con control sin balancear el tronco.'],
 'cable-row':['Colócate frente a la polea con el tronco estable.','Acerca el asa al abdomen o al pecho según la variante.','Extiende los brazos lentamente sin redondear la espalda.'],
 tricep:['Fija los codos en la posición mostrada y sujeta el asa o cuerda.','Extiende los codos sin mover el tronco.','Regresa con control, evitando el bloqueo brusco del codo.'],
 legpress:['Ajusta asiento y plataforma manteniendo pelvis y espalda apoyadas.','Empuja con toda la planta del pie sin bloquear las rodillas.','Acerca la plataforma con control sin despegar la pelvis.'],
 hamcurl:['Ajusta el rodillo a la pierna y conserva el apoyo de la pelvis.','Flexiona las rodillas contra la resistencia sin arquear el tronco.','Regresa despacio al recorrido inicial.'],
 'leg-extension':['Alinea la rodilla con el eje de la máquina y ajusta el rodillo.','Extiende las rodillas con control sin golpear el tope.','Baja la carga lentamente y conserva la espalda apoyada.'],
 'hip-machine':['Ajusta el asiento y apoya los muslos en las almohadillas.','Separa las rodillas manteniendo la pelvis estable.','Regresa con control sin chocar las placas.'],
 'lateral-lunge':['Abre una postura amplia con los pies firmes.','Desplaza la pelvis hacia un lado y flexiona esa rodilla; la pierna contraria se alarga.','Empuja el pie de apoyo para regresar al centro; repite al otro lado sin girar la rodilla hacia dentro.'],
 'squat-lunge':['Haz una sentadilla y vuelve a la postura de pie.','Lleva un pie atrás y baja en zancada sin perder el talón delantero.','Regresa al centro; en la siguiente repetición cambia la pierna que llevas atrás.'],
 step:['Apoya todo el pie en una plataforma de altura controlable.','Empuja la plataforma con esa pierna para subir, sin impulsarte con la pierna que queda abajo.','Baja con control y cambia la pierna de apoyo al terminar el lado indicado.'],
 bridge:['Túmbate boca arriba y apoya los pies con las rodillas flexionadas.','Eleva la pelvis mediante la extensión de cadera, conservando hombros apoyados.','Baja la pelvis con control sin arquear la espalda para subir más.'],
 'ham-slide':['Túmbate boca arriba con talones sobre una toalla que deslice y eleva la pelvis.','Aleja los talones gradualmente sin dejar caer la cadera.','Flexiona las rodillas para traer los talones, manteniendo el movimiento controlado; prueba primero un recorrido corto.'],
 'reverse-plank':['Siéntate con las manos detrás de la pelvis y los dedos en una posición cómoda.','Eleva la pelvis manteniendo el pecho abierto y el apoyo de manos y talones.','Baja con control; evita forzar las muñecas o dejar caer la cabeza.'],
 hollow:['Túmbate boca arriba y acerca las costillas a la pelvis, sin separar la zona lumbar del suelo.','Eleva hombros y piernas solo hasta donde puedas conservar ese apoyo.','Vuelve con control; acorta la palanca doblando las rodillas si se arquea la espalda.'],
 legraise:['Túmbate boca arriba con brazos apoyados junto al cuerpo.','Eleva las piernas sin tomar impulso ni despegar la zona lumbar.','Baja solo hasta el punto donde puedas mantener la pelvis estable y luego repite.'],
 'knee-tuck':['Siéntate en el suelo y apoya las manos a los lados de la pelvis.','Acerca las rodillas al pecho con el abdomen activo.','Extiende las piernas sin dejarlas caer; mantén el torso controlado.'],
 'side-plank':['Colócate de lado con el codo debajo del hombro y los apoyos de piernas indicados.','Eleva la pelvis y conserva el hombro de apoyo estable.','Realiza el gesto del brazo o rodilla mostrado sin perder la línea del tronco; baja antes de cambiar de lado.'],
 plank:['Apoya las manos o antebrazos según la variante y alinea cabeza, pelvis y talones.','Realiza el desplazamiento corto indicado manteniendo firme el abdomen.','Vuelve al apoyo inicial sin hundir ni elevar bruscamente la cadera.'],
 bear:['Apoya manos bajo los hombros y rodillas bajo las caderas.','Eleva ligeramente las rodillas; realiza el paso o toque indicado sin girar la pelvis.','Regresa a los cuatro apoyos y baja las rodillas antes de perder el control.'],
 thruster:['Sujeta dos mancuernas delante de los hombros.','Desciende en sentadilla; al subir, enlaza la extensión de piernas con el press de hombros.','Baja las mancuernas a los hombros antes de comenzar la siguiente sentadilla.'],
 clean:['Sujeta las mancuernas cerca de los muslos y adopta una bisagra de cadera.','Extiende la cadera y guía las cargas cerca del cuerpo hacia los hombros.','Recibe las mancuernas con rodillas suaves y bájalas con control; aprende primero con cargas ligeras.'],
 renegade:['Colócate en plancha con una base amplia; usa mancuernas que no rueden.','Mantén una mano apoyada y lleva la otra carga hacia el costado.','Devuelve la carga al suelo sin girar la pelvis y alterna el brazo.'],
 deadlift:['Coloca la barra cerca de las piernas y ajusta la postura de pies indicada.','Flexiona caderas y rodillas para sujetarla con espalda estable.','Empuja el suelo y extiende la cadera; baja la barra cerca del cuerpo, sin rebotes.'],
 hipthrust:['Apoya la parte alta de la espalda en un banco firme y protege el contacto de la barra con la pelvis.','Con pies estables, eleva la cadera hasta alinear tronco y muslos.','Baja con control sin extender la zona lumbar; comprueba que el banco no se desplaza.'],
 'erg-row':['Ajusta los apoyos de pies y sujeta el asa con brazos extendidos.','Empuja con las piernas, desplaza el tronco ligeramente y termina llevando el asa al abdomen.','Devuelve primero los brazos, después el tronco y finalmente flexiona las piernas para volver.'],
 calf:['Ajusta la barra y los seguros de la máquina; apoya los pies de forma estable.','Eleva los talones sin doblar las rodillas ni balancear el tronco.','Baja los talones con control sin rebotar sobre las puntas.'],
 'machine-press':['Ajusta el asiento para que las asas queden a la altura del pecho y apoya la espalda.','Empuja las asas al frente manteniendo muñecas alineadas.','Regresa con control hasta el recorrido inicial sin despegar el tronco del respaldo.'],
 lateral:['Colócate de lado a una polea baja y sujeta el asa con el brazo más alejado.','Eleva ese brazo hasta un recorrido cómodo sin encoger el hombro.','Baja lentamente sin inclinar el cuerpo; cambia de lado al completar la serie.'],
 pallof:['Colócate de lado a la polea y sujeta el asa delante del pecho con las dos manos.','Extiende los brazos al frente resistiendo el giro del tronco.','Acerca las manos al pecho con control y cambia de lado al terminar.'],
 woodchop:['Colócate de lado a una polea alta y sujeta el asa con ambas manos.','Traza una diagonal hacia la cadera contraria, permitiendo que pies y pelvis acompañen el giro.','Vuelve con control; no retuerzas la zona lumbar contra unos pies inmóviles.'],
 'face-pull':['Ajusta la polea alrededor de la altura del rostro y sujeta los extremos de la cuerda.','Acerca la cuerda al rostro separando las manos y guiando los codos hacia atrás.','Devuelve los brazos sin arquear la espalda ni adelantar el cuello.'],
 'straight-pull':['Colócate frente a la polea alta con rodillas suaves y brazos elevados.','Mantén una flexión ligera de codos y lleva el asa hacia los muslos.','Regresa despacio sin cambiar la posición del tronco.'],
 landmine:['Asegura el extremo fijo de la barra y sujeta el otro extremo a la altura del hombro.','Empuja la barra hacia arriba y adelante sin girar la pelvis.','Devuélvela con control al hombro; cambia de lado al terminar.'],
 treadmill:['Coloca el seguro de emergencia de la cinta y comienza con una velocidad baja.','Camina dentro de la zona útil de la cinta con pasos naturales y sin agarrarte para sostener el ritmo.','Reduce la velocidad antes de detenerte o bajar del equipo.']
};
const details={
 diamond:'Acerca las manos debajo del pecho formando un rombo con pulgares e índices; no fuerces las muñecas.',
 staggered:'Una mano queda algo más adelantada que la otra; intercambia su posición en la siguiente serie.',
 spiderman:'Durante el descenso acerca una rodilla al codo del mismo lado; vuelve a plancha antes de alternar.',
 handrelease:'En la posición baja apoya el pecho suavemente y despega las manos un instante; recolócalas antes de empujar.',
 hindu:'Desde una V invertida recorre el suelo hacia delante y vuelve por el mismo camino; no fuerces la extensión lumbar.',
 'push-tap':'Completa la flexión; en plancha alta toca el hombro opuesto con una mano y luego vuelve a apoyarla.',
 'push-power':'El empuje despega brevemente las manos; aterriza con codos suaves y conserva la línea del tronco.',
 'push-one':'La mano auxiliar queda lateral y soporta parte del peso; la mano de trabajo permanece bajo el hombro.',
 shrimp:'Dobla la pierna que no apoya hacia atrás y usa el apoyo auxiliar para controlar el descenso.',
 'pistol-box':'Mantén la pierna libre por delante y toca suavemente el banco, sin dejarte caer.',
 'pistol-db':'Extiende la pierna libre al frente y usa una mancuerna ligera como contrapeso, sin balancearla.',
 'step-lateral':'Comienza al lado de la plataforma, con el pie de apoyo más cercano sobre ella.',
 'step-cross':'Cruza la pierna hacia la plataforma y alinea pelvis y rodilla con ese apoyo; comienza con una altura baja.',
 curtsy:'Lleva la pierna libre atrás y en diagonal; conserva el control de la rodilla delantera y evita un cruce excesivo.',
 'walking-lunge':'Al volver a subir, adelanta la pierna de atrás para iniciar la siguiente zancada; alterna en un espacio despejado.',
 'bridge-walk':'Aleja y acerca los talones con pasos cortos alternados, conservando la pelvis elevada.',
 'bridge-long':'Aleja los talones de la pelvis para aumentar la palanca, manteniendo la misma altura controlada de cadera.',
 frog:'Junta las plantas y abre las rodillas dentro de un recorrido cómodo; observa el detalle frontal de las piernas.',
 airplane:'Usa un apoyo auxiliar y alterna una apertura pequeña de pelvis con su regreso a la bisagra, sin girar la rodilla de apoyo.',
 'reverse-knee':'Con la pelvis elevada acerca una rodilla y vuelve a extender esa pierna antes de alternar.',
 'hollow-rock':'Mantén la forma agrupada del tronco y balancea suavemente como una unidad; no sacudas cabeza o piernas.',
 'hollow-tuck':'Acerca primero las rodillas y después alarga las piernas y brazos conservando la zona lumbar apoyada.',
 vup:'Acerca torso y piernas de forma simultánea para alcanzar las espinillas; evita lanzar los brazos como impulso.',
 flutter:'Alterna pequeñas elevaciones de piernas; el recorrido se mantiene corto y la pelvis quieta.',
 scissors:'Separa y cruza las piernas en el plano frontal; cada cruce cambia cuál pasa por encima.',
 'side-thread':'Pasa el brazo libre bajo el torso y vuelve a abrirlo, manteniendo el hombro que soporta el peso.',
 'side-knee':'Acerca la rodilla de la pierna superior y vuelve a extenderla sin dejar caer la pelvis.',
 'body-saw':'Apoya los antebrazos; desplaza el cuerpo pocos centímetros adelante y atrás, sin doblar las rodillas.',
 'plank-toe':'Conserva ambas manos apoyadas y separa una punta hacia el lado; regresa y alterna.',
 crab:'Con el pecho hacia arriba, desplaza una mano y el pie contrario; alterna pasos cortos manteniendo la pelvis elevada.',
 zottman:'Sube con palmas hacia arriba; en la parte alta gira los antebrazos y baja con palmas hacia abajo.',
 'hammer-cross':'Mantén un agarre neutro y acerca una mancuerna al hombro contrario sin girar el tronco.',
 'floor-fly':'La espalda permanece sobre el suelo; los brazos abren a los lados con codos suaves y después se aproximan sobre el pecho.'
};
const ids=[];
for(const [place,rows] of [['home',home],['gym',gym]])for(const [key,name,parentId,recipe,tier,param]of rows){
 const parent=Engine.byId(parentId);if(!parent||!MotionDB[parent.baseId||parent.id])throw Error('Padre ausente: '+parentId);
 const id=place+'-'+key+'23',gymOnly=place==='gym',extra=clone(param),instructions=rules[recipe]||[
 'Revisa la posición inicial, el espacio y los apoyos mostrados en la demostración.',
 'Realiza el recorrido señalado conservando el control del tronco y de las articulaciones.',
 'Vuelve despacio a la posición inicial y detén la serie si pierdes la técnica.'
 ];
 const setup=gymOnly?'Ajusta el equipo de gimnasio antes de cargar. Usa inicialmente una resistencia que permita un recorrido completo y controlado.':param.step?'Utiliza una plataforma de ejercicio estable.':param.bench?'Utiliza un banco de entrenamiento estable.':param.db?'Prepara mancuernas y espacio para el recorrido.':'Prepara una superficie estable y espacio libre.';
 const requirements=gymOnly?[]:[...(param.db?['db']:[]),...(param.bench?['bench']:[]),...(param.step?['step']:[])];
 if(!rules[recipe])throw Error('Instrucciones ausentes para '+recipe);
 const steps=[setup,...instructions,...(details[key]?[details[key]]:[])];
 const e={...clone(parent),id,baseId:id,name,variant:'base',variantDescription:'Patrón propio con demostración 2D y detalle de apoyos.',level:tier,progressionTier19:tier,progressionTier23:tier,pack23:true,gymOnly23:gymOnly,recipe23:recipe,param23:extra,
  equipment:gymOnly?'gym':param.db?'db':'body',requirements,gymRequirements23:gymOnly?[param.gear,...(param.bench?['bench']:[])]:[],sourceOnly:false,originalVideo15:false,
  unit:param.cardio?'sec':'reps',category:param.cardio?'Cardio':'Fuerza',sides:!!(param.single||param.kneeDrive||['renegade','woodchop','pallof'].includes(recipe)||/unilateral|alterna|a una pierna|zancada|pistol|patinador|camarón/.test(name.toLowerCase())),
  setup,instructions:steps,steps:steps.join(' '),cue:'Mantén el control; reduce la carga o termina la serie al perder la posición.',
  repRange19:[tier===2?5:8,tier===2?12:16],tempoSeconds18:4,loaded23:gymOnly&&!param.cardio||!!param.db,
  overhead:!!param.overhead||recipe==='overhead'||recipe==='pulldown',wristLoad:!!parent.wristLoad||['push','plank','bear','reverse-plank','renegade'].includes(recipe),spineMove:!!param.spineMove,
  kneeBend:['squat','single-squat','lunge','squat-lunge','step','legpress','deadlift','hamcurl','leg-extension','knee-tuck','thruster'].includes(recipe),seated:!!param.seated||recipe==='knee-tuck',floor:!gymOnly&&['push','pike','ham-slide','bridge','hollow','legraise','side-plank','plank','bear','reverse-plank','renegade','fly','knee-tuck'].includes(recipe),
  impact:param.impact||'Sin saltos',family:recipe==='thruster'?'full':parent.family||'control'};
 if(['legpress','leg-extension','hamcurl','hip-machine','squat','single-squat','lunge','step','deadlift'].includes(recipe))e.group=['bridge','hip-machine'].includes(recipe)?'Cadera':'Piernas';
 if(['pulldown','cable-row','erg-row','face-pull','straight-pull'].includes(recipe))e.group='Espalda';
 if(['pallof','woodchop','hollow','legraise','knee-tuck','side-plank','plank','bear'].includes(recipe))e.group='Abdomen';
 if(param.cardio){e.group='Cardio';e.repRange19=[25,60];}
 delete e.media;delete e.demo;
 Engine.exercises.push(e);MotionDB[id]={...clone(MotionDB[parent.baseId||parent.id]),id,name,hold:false,frames:clone(MotionDB[parent.baseId||parent.id].frames)};ids.push(id);
 for(const [suffix,label,seconds]of [['tempo23','con retorno de tres segundos',5],['pause23','con pausa de dos segundos',6]]){
  const v={...clone(e),id:id+'-'+suffix,baseId:id,name:name+' · '+label,variant:suffix==='tempo23'?'controlled':'pause',level:Math.min(2,tier+1),progressionTier19:Math.min(2,tier+1),progressionTier23:Math.min(2,tier+1),tempoSeconds18:seconds,
   renderWarp23:suffix==='tempo23'?'slow':'pause',variantDescription:suffix==='tempo23'?'La vuelta dura tres segundos. Controla la carga sin perder los apoyos.':'Mantén dos segundos el punto de mayor contracción o control, sin bloquear las articulaciones.',repRange19:[tier===2?4:6,tier===2?10:12]};
  v.instructions=[...e.instructions,v.variantDescription];v.steps=v.instructions.join(' ');Engine.exercises.push(v);ids.push(v.id);
 }
}
if(home.length!==50||gym.length!==50||ids.length!==300)throw Error('El paquete debe contener exactamente 300 fichas.');
Object.assign(Catalog.equipment,{gym:'Equipamiento de gimnasio',barbell:'Barra y discos',cable:'Poleas',machine:'Máquinas de fuerza',smith:'Máquina Smith',cardio:'Cinta o ergómetro'});
root.ExercisePack23={home,gym,ids,rules,baseIds:ids.filter(id=>!/-tempo23$|-pause23$/.test(id))};
})(window);

;
/* 70 patrones adicionales y 140 variantes de cadencia con recursos propios. */
(function(root){'use strict';
const cp=x=>JSON.parse(JSON.stringify(x));
const gym=[
 ['press45','Prensa de piernas a 45 grados','gym-leg-press23','legpress',0,'Piernas',{angle:45}],
 ['presssingle','Prensa de piernas unilateral','gym-leg-press23','legpress',1,'Piernas',{single:true}],
 ['pendulum','Sentadilla en máquina péndulo','gym-hack23','squat',1,'Piernas',{pendulum:true}],
 ['belt','Sentadilla en máquina con cinturón','gym-hack23','squat',1,'Piernas',{belt:true}],
 ['vsquat','Sentadilla en máquina de palanca en V','gym-hack23','squat',2,'Piernas',{vlever:true}],
 ['smithsplit','Sentadilla dividida en Smith','dbsplit','lunge',1,'Piernas',{gear:'smith',back:true}],
 ['smiththrust','Hip thrust en máquina Smith','gym-thrust-bb23','hipthrust',1,'Cadera',{gear:'smith',bench:true}],
 ['smithrdl','Peso muerto rumano en Smith','gym-rdl-bb23','hinge',1,'Piernas',{gear:'smith'}],
 ['hamstanding','Curl femoral de pie en máquina','gym-ham-seated23','hamcurl',0,'Piernas',{standing:true,single:true}],
 ['tibialis','Dorsiflexión en máquina de tibial anterior','toelift','ankle',0,'Piernas',{tibialis:true,seated:true}],
 ['calfseat','Elevación de talones en máquina sentado','seatedcalf','calf',0,'Piernas',{seated:true}],
 ['calfdonkey','Elevación de talones en máquina burro','calf','calf',1,'Piernas',{hinged:true}],
 ['adduct','Aducción de cadera en máquina','gym-hip-machine23','hip-machine',0,'Cadera',{adduct:true,seated:true}],
 ['kickmachine','Extensión de cadera en máquina de glúteo','standingextend','hip-extension',0,'Cadera',{support:true,single:true}],
 ['thrustmachine','Hip thrust en máquina de glúteo','hipthrust','hipthrust',0,'Cadera',{belt:true,bench:true}],
 ['abmachine','Flexión abdominal en máquina','curlup','crunch',1,'Abdomen',{seated:true,spineMove:true}],
 ['backmachine','Extensión de tronco sentado en máquina','hinge','back-extension',1,'Espalda',{seated:true,spineMove:true}],
 ['rotary','Rotación controlada en máquina de torso','seatedturn','rotation',1,'Abdomen',{seated:true,spineMove:true}],
 ['pullassist','Dominada en máquina asistida','gym-lat-neutral23','pullup',1,'Espalda',{assisted:true}],
 ['dipassist','Fondos en máquina asistida','gym-rope-tricep23','dip',1,'Brazos',{assisted:true}],
 ['highrow','Remo alto en máquina de palancas','gym-cable-high-row23','cable-row',1,'Espalda',{high:true,seated:true}],
 ['supportrow','Remo en máquina con apoyo de pecho','gym-cable-row23','cable-row',0,'Espalda',{chestSupport:true,seated:true}],
 ['pressone','Press de pecho unilateral en máquina','gym-chest-machine23','machine-press',1,'Pecho',{single:true,seated:true}],
 ['pressincline','Press inclinado de pecho en máquina','gym-chest-machine23','machine-press',1,'Pecho',{incline:true,seated:true}],
 ['shoulderm','Press de hombros en máquina','seatedpress','overhead',0,'Hombros',{seated:true}],
 ['lateralm','Elevación lateral en máquina','seatedlateral','lateral',0,'Hombros',{seated:true}],
 ['reversedeck','Apertura posterior en máquina pec deck','reversefly','fly',0,'Espalda',{reverse:true,seated:true}],
 ['preacherm','Curl predicador en máquina','gym-preacher-bb23','curl',0,'Brazos',{preacher:true,seated:true}],
 ['tricepsm','Extensión de tríceps sentado en máquina','gym-rope-tricep23','tricep',0,'Brazos',{seated:true}],
 ['pulloverm','Pullover sentado en máquina','pullover','straight-pull',1,'Espalda',{seated:true}],
 ['hipflexc','Flexión de cadera en polea con tobillera','standingcore','hip-flexion',1,'Cadera',{gear:'cable',single:true}],
 ['abductc','Abducción de cadera en polea con tobillera','standingabduct','hip-abduction',1,'Cadera',{gear:'cable',single:true}],
 ['adductc','Aducción de cadera en polea con tobillera','standingabduct','hip-adduction',1,'Cadera',{gear:'cable',single:true}],
 ['kickc','Extensión de cadera en polea con tobillera','standingextend','hip-extension',1,'Cadera',{gear:'cable',single:true}],
 ['crunchc','Flexión abdominal arrodillado en polea','curlup','crunch',1,'Abdomen',{gear:'cable',kneeling:true,spineMove:true}],
 ['pullthrough','Extensión de cadera entre piernas en polea','gym-rdl-bb23','hinge',1,'Cadera',{gear:'cable',pullthrough:true}],
 ['stepmill','Subida continua en escaladora de peldaños','stepup','step',0,'Cardio',{gear:'cardio',cardio:true}],
 ['bike','Pedaleo en bicicleta estática','seatedmarch','cycle',0,'Cardio',{gear:'cardio',cardio:true}],
 ['elliptical','Paso coordinado en elíptica','walk','elliptical',0,'Cardio',{gear:'cardio',cardio:true}],
 ['skierg','Tracción en ergómetro de esquí','gym-straight-pull23','ski',1,'Cardio',{gear:'cardio',cardio:true}],
 ['airbike','Pedaleo y empuje alterno en bicicleta de aire','seatedmarch','cycle',1,'Cardio',{gear:'cardio',air:true,cardio:true}],
 ['recumbent','Pedaleo en bicicleta reclinada','seatedmarch','cycle',0,'Cardio',{gear:'cardio',reclined:true,cardio:true}]
];
const home=[
 ['squatnarrow','Sentadilla con postura estrecha','squat','squat',1,'Piernas',{narrow:true}],
 ['squatheels','Sentadilla con talones sobre cuña de ejercicio','squat','squat',1,'Piernas',{heelWedge:true}],
 ['squatpulse','Sentadilla con pulsos en recorrido inferior','squat','squat',1,'Piernas',{pulses:true}],
 ['cyclistdb','Sentadilla ciclista con mancuerna al pecho','goblet','squat',2,'Piernas',{narrow:true,heelWedge:true,db:true}],
 ['lungefront','Zancada al frente con mancuernas','dblunge','lunge',1,'Piernas',{forward:true,db:true}],
 ['lungeoffset','Zancada atrás con mancuerna en un lado','dblunge','lunge',2,'Piernas',{offset:true,db:true}],
 ['splithigh','Sentadilla dividida con pie delantero en plataforma','dbsplit','lunge',2,'Piernas',{frontElevated:true,step:true,db:true}],
 ['rdlstagger','Peso muerto rumano con apoyo en B','rdl','hinge',1,'Piernas',{staggered:true,db:true}],
 ['rdlsuit','Peso muerto rumano con carga unilateral','rdl','hinge',2,'Piernas',{singleLoad:true,db:true}],
 ['bridgemarch','Puente de glúteos con marcha alterna','bridge','bridge',1,'Cadera',{march:true}],
 ['bridgesqueeze','Puente de glúteos con presión de cojín','bridge','bridge',0,'Cadera',{squeeze:true}],
 ['thrustsingle','Hip thrust a una pierna con banco','hipthrust','hipthrust',2,'Cadera',{single:true,bench:true}],
 ['plankjack','Plancha alta con saltos de apertura de pies','pushup','plank',2,'Abdomen',{jack:true,impact:'Con saltos'}],
 ['plankstep','Plancha alta con pasos laterales de manos','pushup','plank',2,'Abdomen',{handwalk:true}],
 ['plankdrag','Plancha alta con arrastre lateral de mancuerna','pushup','plank',2,'Abdomen',{drag:true,db:true}],
 ['sideabduct','Plancha lateral con abducción de pierna','full-sideplank19','side-plank',2,'Abdomen',{legLift:true}],
 ['sidehip','Plancha lateral corta con elevación de pelvis','sideplank','side-plank',1,'Abdomen',{knees:true,hipLift:true}],
 ['bearreach','Cuadrupedia suspendida con alcance al frente','bearhold','bear',2,'Abdomen',{reach:true}],
 ['deadbugpress','Dead bug con presión de manos contra rodillas','deadbug','deadbug',1,'Abdomen',{press:true}],
 ['reversebird','Extensión contralateral tumbado con talón apoyado','deadbug','deadbug',0,'Abdomen',{heelSupport:true}],
 ['scappush','Flexión escapular en plancha alta','pushup','push',1,'Pecho',{scapular:true}],
 ['pushwide','Flexión de suelo con manos algo más separadas','pushup','push',1,'Pecho',{wide:true}],
 ['pushcloseknee','Flexión de manos próximas con rodillas apoyadas','kneepush','push',0,'Pecho',{close:true,knees:true}],
 ['pikepause','Flexión pica con pausa cerca del suelo','pike19','pike',2,'Hombros',{bottomHold:true}],
 ['lateralstepover','Paso lateral sobre línea con rodilla alta','stepcross','agility',1,'Cardio',{over:true}],
 ['hopforward','Salto corto hacia delante con aterrizaje estable','lowhop','jump',1,'Cardio',{forward:true,impact:'Con saltos'}],
 ['hip9090','Rotación de caderas sentado en posición 90–90','hipcircle','hip9090',0,'Movilidad',{seated:true}],
 ['wallangel','Ángel de pared con brazos y codos apoyados','wallslide','wall-angel',0,'Movilidad',{support:true}]
];
const instructions={
 'hip-extension':['Sujeta el apoyo y conserva el abdomen activo y la pelvis estable.','Lleva una pierna hacia atrás desde la cadera sin arquear la espalda.','Devuelve la pierna con control y cambia de lado al terminar.'],
 'hip-flexion':['Sujeta el apoyo y coloca la tobillera cerca del tobillo.','Eleva una rodilla hacia delante sin inclinar el tronco.','Baja el pie con control antes de repetir y cambiar de lado.'],
 'hip-abduction':['Colócate de lado al cable con una mano en el apoyo.','Separa la pierna de trabajo hacia el lado sin subir la pelvis.','Vuelve lentamente y cambia de lado al completar la serie.'],
 'hip-adduction':['Colócate de lado al cable y sujeta el apoyo.','Acerca la pierna con tobillera hacia la línea media sin girar el tronco.','Vuelve al lado de inicio con control y cambia de pierna.'],
 crunch:['Ajusta el apoyo y mantén la pelvis estable; no tires del cuello.','Acerca las costillas a la pelvis con una flexión corta y controlada.','Regresa al recorrido inicial sin impulso ni dolor.'],
 'back-extension':['Ajusta asiento y almohadilla con los pies apoyados.','Mueve el tronco desde una ligera inclinación hasta quedar erguido.','Evita sobreextender la espalda; vuelve despacio al inicio.'],
 rotation:['Ajusta el asiento y apoya pelvis y pies.','Realiza una rotación corta del tronco dentro de un recorrido cómodo.','Regresa sin impulso; trabaja ambos lados con la misma carga.'],
 pullup:['Ajusta la asistencia y sube al apoyo con los agarres firmes.','Acerca el pecho hacia los agarres llevando codos hacia los costados.','Desciende con control y sal del equipo cuando el apoyo esté estable.'],
 dip:['Ajusta la asistencia y coloca las manos en barras paralelas.','Flexiona los codos con el tronco estable, sin forzar el hombro.','Empuja las barras para subir; conserva una carga controlable.'],
 cycle:['Ajusta asiento y pedales para conservar una flexión suave de rodilla al extender la pierna.','Pedalea de forma continua con rodillas dirigidas hacia los pies.','Reduce el ritmo antes de detenerte o bajar de la bicicleta.'],
 elliptical:['Apoya ambos pies y sujeta las asas antes de comenzar.','Alterna piernas y brazo contrario sin rebotar ni girar las rodillas.','Reduce el ritmo gradualmente antes de bajar del equipo.'],
 ski:['Sujeta las asas altas con rodillas suaves y espalda estable.','Lleva las manos hacia los muslos acompañando con una pequeña bisagra de cadera.','Vuelve las asas arriba con control sin perder la postura.'],
 ankle:['Conserva la pierna apoyada y el tobillo libre para moverse.','Acerca la punta del pie hacia la espinilla contra la resistencia.','Devuelve la punta lentamente sin mover la rodilla.'],
 deadbug:['Túmbate boca arriba con costillas y pelvis estables.','Extiende lentamente un brazo y la pierna contraria; conserva el apoyo lumbar.','Vuelve y alterna, acortando el recorrido si pierdes ese apoyo.'],
 agility:['Comienza con pies paralelos en un espacio despejado y sin obstáculos rígidos.','Alterna pasos sobre la línea con rodilla alta y apoyo estable.','Cambia de dirección con pasos cortos, sin cruzar las rodillas.'],
 jump:['Comienza con pies estables y flexiona ligeramente caderas y rodillas.','Impúlsate en un salto corto hacia delante.','Aterriza suave, estabiliza rodillas y pelvis y regresa caminando al inicio.'],
 hip9090:['Siéntate con ambas rodillas flexionadas y apoya las manos detrás si lo necesitas.','Cambia ambas rodillas de un lado al otro desde las caderas sin forzar el recorrido.','Mantén el tronco estable y termina si aparece dolor.'],
 'wall-angel':['Apoya espalda y brazos contra la pared dentro de un recorrido cómodo.','Desliza manos y codos hacia arriba sin arquear la zona lumbar.','Baja con control conservando el contacto posible sin forzar hombros.']
};
const details={squatnarrow:'Pies algo más próximos; rodillas y puntas conservan la misma dirección.',squatheels:'Usa una cuña de ejercicio firme, no objetos que rueden.',squatpulse:'Tras bajar, alterna dos recorridos cortos antes de volver a subir.',cyclistdb:'Cuña estable y carga cerca del pecho; baja solo donde conserves el control.',lungeoffset:'La carga permanece en una mano; cambia de lado entre series.',rdlstagger:'El pie trasero actúa como apoyo auxiliar; la carga principal está en la pierna delantera.',bridgemarch:'Mantén la pelvis elevada mientras despega un pie y después el contrario.',bridgesqueeze:'Coloca un cojín entre rodillas; presiona suavemente sin girar pies ni pelvis.',plankjack:'Abre y cierra los pies con pequeños saltos manteniendo ambas manos fijas.',plankdrag:'Una mano cruza bajo el torso para arrastrar la mancuerna; la otra mantiene el apoyo.',plankstep:'Da un paso lateral con mano y pie y vuelve; conserva el tronco alineado.',sideabduct:'La pierna superior se eleva y vuelve mientras pelvis y hombro de apoyo permanecen estables.',sidehip:'Con rodillas apoyadas, eleva y baja la pelvis sin colapsar el hombro.',deadbugpress:'Presiona mano y rodilla del lado que no se extiende.',reversebird:'El talón que se aleja desliza por el suelo.',scappush:'Codos extendidos: aproxima y separa omóplatos con un recorrido corto.',pikepause:'Mantén una breve pausa cerca del suelo antes de empujar.',press45:'Pies apoyados en el trineo inclinado; espalda y pelvis permanecen sobre el respaldo.',presssingle:'Una pierna empuja y vuelve con control; la otra queda fuera de la plataforma.'};
const ids=[],bases=[];
for(const [place,rows]of [['gym',gym],['home',home]])for(const [key,name,parentId,kind,tier,group,params]of rows){
 const parent=Engine.byId(parentId);if(!parent)throw Error('Padre V30 ausente: '+parentId);
 const gymOnly=place==='gym',p={...params,gear:params.gear||(gymOnly?'machine':undefined)},id=place+'-'+key+'30';
 const steps=[...(instructions[kind]||ExercisePack23.rules[kind]||[]),...(details[key]?[details[key]]:[])];if(steps.length<3)throw Error('Instrucción V30 ausente: '+kind);
 const e={...cp(parent),id,baseId:id,name,variant:'base',level:tier,progressionTier19:tier,progressionTier23:tier,pack23:true,pack30:true,gymOnly23:gymOnly,recipe30:kind,param30:p,sourceOnly:false,originalVideo15:false,
  group,unit:p.cardio||['hip9090','wall-angel'].includes(kind)?'sec':'reps',category:group==='Movilidad'?'Movilidad':group==='Cardio'?'Cardio':'Fuerza',
  equipment:gymOnly?'gym':p.db?'db':'body',requirements:gymOnly?[]:[...(p.db?['db']:[]),...(p.bench?['bench']:[]),...(p.step?['step']:[])],gymRequirements23:gymOnly?[p.gear,...(p.bench?['bench']:[])]:[],
  loaded23:gymOnly&&p.gear!=='cardio'||!!p.db,impact:p.impact||'Sin saltos',spineMove:!!p.spineMove,kneeBend:['squat','lunge','hinge','legpress','hamcurl','step','cycle','jump'].includes(kind),wristLoad:['push','pike','plank','side-plank','bear'].includes(kind),overhead:['overhead','pullup','ski'].includes(kind),
  floor:!gymOnly&&['push','pike','plank','side-plank','bear','bridge','deadbug','hip9090'].includes(kind),seated:!!p.seated||['cycle','legpress'].includes(kind),sides:!!(p.single||p.offset||p.singleLoad||p.march||p.legLift||kind==='lunge'||kind.startsWith('hip-')),
  family:group==='Movilidad'?'mobility':group==='Cardio'?kind==='jump'?'power':'cardio':parent.family||'full',setup:steps[0],instructions:steps,steps:steps.join(' '),cue:'Controla el recorrido y la carga; detén la serie si aparece dolor o pierdes la postura.',variantDescription:details[key]||'Recorrido y apoyos propios de esta variante.',repRange19:group==='Cardio'?[20,60]:[tier===2?5:8,tier===2?12:16],tempoSeconds18:4};
 e.animationParent30=parentId;delete e.recipe23;delete e.param23;delete e.renderWarp23;delete e.media;delete e.demo;
 Engine.exercises.push(e);MotionDB[id]={...cp(MotionDB[parent.baseId||parent.id]),id,name};ids.push(id);bases.push(id);
 for(const [suffix,label,seconds]of [['ecc30','retorno de tres segundos',5],['hold30','pausa de dos segundos',6]]){
  const v={...cp(e),id:id+'-'+suffix,name:name+' · '+label,variant:suffix==='ecc30'?'controlled':'pause',level:Math.min(2,tier+1),progressionTier19:Math.min(2,tier+1),progressionTier23:Math.min(2,tier+1),tempoSeconds18:seconds,renderWarp23:suffix==='ecc30'?'slow':'pause',variantDescription:label+' con los mismos apoyos; no añadas impulso.'};
  v.instructions.push(v.variantDescription);v.steps=v.instructions.join(' ');Engine.exercises.push(v);ids.push(v.id);
 }
}
if(gym.length!==42||home.length!==28||Engine.exercises.length!==1000)throw Error('Catálogo V30: se requieren 1000 fichas.');
root.Catalogue30={ids,bases,gym,home,instructions,version:30};
})(window);

;
/* V9.3 — selección conservadora y variada cuando hay una lesión declarada y actividad autorizada. */
(function(root){
'use strict';
const E=root.Engine,Adapt=root.Adapt,Schedule=root.Schedule,previousItems=Schedule.items;
const avoid={neck:['Hombros','Espalda'],shoulder:['Hombros','Pecho','Espalda','Brazos'],elbow:['Pecho','Espalda','Brazos','Hombros'],wrist:['Pecho','Espalda','Brazos','Hombros'],back:['Espalda','Core','Cadera'],hip:['Cadera','Piernas'],knee:['Piernas','Cadera'],ankle:['Piernas','Cadera'],other:[]};
const prefer={neck:['Piernas','Cadera','Core'],shoulder:['Piernas','Cadera','Core'],elbow:['Piernas','Cadera','Core'],wrist:['Piernas','Cadera','Core'],back:['Pecho','Brazos','Hombros','Piernas'],hip:['Pecho','Espalda','Hombros','Brazos','Core'],knee:['Pecho','Espalda','Hombros','Brazos','Core'],ankle:['Pecho','Espalda','Hombros','Brazos','Core'],other:[]};
const zones=p=>[...new Set(Adapt.active(p).map(x=>x.zone))];
function priorityGroups(p,pool){const activeZones=zones(p),groups=[...new Set(pool.map(e=>e.group))];return groups.map(group=>{let score=0;for(const z of activeZones){if((avoid[z]||[]).includes(group))score-=20;if((prefer[z]||[]).includes(group))score+=4;}if(p.focus==='upper'&&['Pecho','Espalda','Hombros','Brazos'].includes(group))score+=2;if(p.focus==='lower'&&['Piernas','Cadera'].includes(group))score+=2;if(p.focus==='core'&&group==='Core')score+=2;return {group,score};}).filter(x=>x.score>-20).sort((a,b)=>b.score-a.score||a.group.localeCompare(b.group)).map(x=>x.group);}
function rotate(list,n){if(!list.length)return [];const start=((n%list.length)+list.length)%list.length;return [...list.slice(start),...list.slice(0,start)];}
function items(p,kind,rotation,carry){const active=Adapt.active(p),mode=Adapt.mode(p);if(!active.length||!mode.auto)return previousItems(p,kind,rotation,carry);const category=kind==='strength'?'Fuerza':kind==='cardio'?'Cardio':'Movilidad',pool=E.exercises.filter(e=>e.variant==='base'&&e.category===category&&Adapt.allowed(e,p));if(!pool.length)return [];
 const cap=p.minutes===10?2:p.minutes===20?3:5,count=kind==='recovery'?Math.min(3,pool.length):Math.min(cap,pool.length);let chosen=[];
 if(kind==='strength'){const ranked=priorityGroups(p,pool),groups=rotate(ranked,rotation),safePool=ranked.length?pool.filter(e=>ranked.includes(e.group)):pool,used=new Set();for(const group of groups){const options=safePool.filter(e=>e.group===group&&!used.has(e.id));if(!options.length)continue;const pick=options[Math.abs(rotation+chosen.length)%options.length];chosen.push(pick);used.add(pick.id);if(chosen.length>=count)break;}if(chosen.length<count){for(const e of rotate(safePool,rotation)){if(used.has(e.id))continue;chosen.push(e);used.add(e.id);if(chosen.length>=count)break;}}}
 else chosen=rotate(pool,rotation).slice(0,count);
 return chosen.map(e=>({id:e.id,sets:1,target:e.unit==='sec'?(kind==='cardio'?20:15):kind==='recovery'?6:Math.min(8,carry||6),rest:kind==='recovery'?25:kind==='cardio'?45:Math.max(75,p.restSeconds||90)}));
}
function summary(p){const active=Adapt.active(p),mode=Adapt.mode(p);if(!active.length)return null;const names=zones(p).map(z=>Adapt.zones[z]||z);if(!mode.auto)return {auto:false,zones:names,groups:[],text:mode.reason};const pool=E.exercises.filter(e=>e.variant==='base'&&e.category==='Fuerza'&&Adapt.allowed(e,p)),groups=priorityGroups(p,pool).slice(0,5);return {auto:true,zones:names,groups,text:groups.length?`Zona declarada: ${names.join(', ')}. Se priorizan grupos compatibles distintos: ${groups.join(', ')}.`:`Zona declarada: ${names.join(', ')}. No hay suficientes grupos de fuerza compatibles; el sistema buscará movilidad, cardio o descanso.`};}
Schedule.items=items;
root.InjuryAwareV93={items,summary,priorityGroups,zones};
})(typeof window!=='undefined'?window:globalThis);

;
/* Aprendizaje por ejercicio y revisión conservadora; umbrales de producto, no validación clínica. */
(function(root){
const E=root.Engine;
function evidence(logs,id,now){const base=E.byId(id)?.family||E.byId(id)?.baseId||id;return Object.entries(logs||{}).filter(([date,l])=>date<now&&l.complete&&!l.pain&&!l.fatigue&&l.rpe>=1&&l.rpe<=10).flatMap(([date,l])=>(l.items||[]).filter(x=>x.id===id).map(x=>({date,rpe:Number(l.rpe),item:x,base}))).sort((a,b)=>b.date.localeCompare(a.date)).slice(0,12);}
function recommend(logs,item,p,now){const rows=evidence(logs,item.id,now).filter(r=>E.unitOf(r.item)===E.unitOf(item)&&(r.item.side||'both')===(item.side||'both')),mode=Adapt.mode(p),e=E.byId(item.id);let target=item.target,reason='Registra al menos tres sesiones de este ejercicio con esfuerzo y series completas.',action='mantener';
const recent=rows.slice(0,3),full=recent.length===3&&recent.every(r=>r.item.actual?.length>=Math.max(r.item.sets,item.sets)&&r.item.actual.every(a=>a.done&&Number(a.value)>=Math.max(r.item.target,item.target)));
if(!mode.auto||mode.reduced)return {target,action,reason:'Se conserva la dosis por las restricciones del perfil.',samples:rows.length};
if(full&&recent.every(r=>r.rpe<=7)) {const cap=E.unitOf(item)==='sec'?90:p.finalTarget==='endurance'?20:15;const delta=E.unitOf(item)==='sec'?Math.max(1,Math.min(5,Math.floor(target*.1))):1;target=Math.min(cap,target+delta);action=target>item.target?'aumentar':'mantener';reason=action==='aumentar'?'Tres sesiones completas con esfuerzo de 7/10 o menos: progresión de una sola variable.':'Tope de repeticiones alcanzado. Revisa manualmente una carga que puedas controlar; no se aumenta el peso automáticamente.';}
return {target,action,reason,samples:rows.length};}
const previous=Coach.review;
Coach.review=function(c,logs,p,now,feedback={}){const week=Math.min(3,Math.floor(E.diff(now,c.start)/7));if(week<1||c.reviews?.some(r=>r.week===week))return null;
const weekDays=c.days.slice((week-1)*7,week*7).filter(d=>d.items.length),records=weekDays.map(d=>logs[d.date]).filter(Boolean);
// Dolor, esfuerzo alto, fatiga y restricciones conservan la ruta de reducción previa.
if(feedback.pain||feedback.fatigue||records.some(l=>l.pain||l.fatigue||Number(l.rpe)>=8)||!Adapt.mode(p).auto||Adapt.mode(p).reduced)return previous(c,logs,p,now,feedback);
let changed=0,details=[];const complete=records.filter(l=>l.complete).length,adherence=weekDays.length?complete/weekDays.length:0;
if(adherence>=.8)c.days.filter(d=>d.date>=now&&!d.customized&&!d.routineId&&!d.tasks?.some(t=>t.done)&&!logs[d.date]?.complete&&!logs[d.date]?.items?.some(x=>x.missionState||x.selfReported||x.actual?.some(a=>a.done))).forEach(d=>{let modified=false;d.items.forEach(x=>{const result=recommend(logs,x,p,now);details.push({id:x.id,...result});if(result.target>x.target){x.target=result.target;modified=true;}});if(modified){changed++;delete logs[d.date];}});
const r={week,date:now,action:changed?'aumentar':'mantener',reason:changed?'Progresión por ejercicio con tres registros completos: +1 repetición o hasta 10 % de tiempo; conserva series, peso y descansos.':'Se mantiene el plan: faltan tres registros completos por ejercicio, adherencia suficiente o margen de progresión.',adherence,changed,details};(c.reviews||(c.reviews=[])).push(r);return r;};
// Cambia el ciclo conservando progreso solo del MISMO ejercicio, no del primer ejercicio de otra sesión.
const make=E.makeCycle;E.makeCycle=function(p,start,index,last,logs){const c=make(p,start,index,last,logs);if(last&&!GoalPlans.plans[p.finalTarget]?.gentle&&!Adapt.mode(p).reduced&&Adapt.mode(p).auto)c.days.forEach(d=>d.items.forEach(x=>{const matches=last.days.flatMap(y=>y.items).filter(y=>y.id===x.id&&E.unitOf(y)===E.unitOf(x)&&(y.side||'both')===(x.side||'both'));if(matches.length)x.target=Math.min(E.unitOf(x)==='sec'?90:15,matches.at(-1).target);else if(E.unitOf(x)!=='sec')x.target=p.goal==='muscle'?10:8;}));return c;};
root.AdaptiveV9={evidence,recommend};
})(window);
/* Estimador adicional: vecinos del mismo ejercicio, usando dosis y carga declarada. */
(function(root){
const oldPredict=Learning.predict;
function predictExercise(logs,item,now){const rows=AdaptiveV9.evidence(logs,item.id,now).filter(r=>Engine.unitOf(r.item)===Engine.unitOf(item));if(rows.length<3)return null;
const vector=x=>{const actual=(x.actual||[]).filter(a=>a.done),kg=actual.length?actual.reduce((n,a)=>n+(Number(a.kg)||0),0)/actual.length:0;return [Number(x.target)/20,Number(x.sets)/4,(Number.isFinite(Number(x.rest))?Number(x.rest):90)/120,kg/20];};
const q=vector(item);const near=rows.map(r=>({r,d:Math.hypot(...vector(r.item).map((v,i)=>v-q[i]))})).sort((a,b)=>a.d-b.d).slice(0,5);if(near[0].d>.8)return null;let sum=0,weight=0;for(const n of near){let w=1/(n.d+.1);sum+=n.r.rpe*w;weight+=w;}return {effort:Math.round(sum/weight*10)/10,neighbors:near.length,sessions:rows.length};}
AdaptiveV9.predictExercise=predictExercise;
Learning.predict=function(data,items,bump=false){const exact=oldPredict(data,items,bump);if(exact)return exact;
// Convert existing training rows without generating synthetic experiences.
const logs={};for(const r of data){if(r.items)logs[r.date]={complete:true,rpe:r.y,items:r.items};}
const estimates=items.map(x=>predictExercise(logs,{...x,target:x.target+(bump?(Engine.unitOf(x)==='sec'?Math.max(1,Math.floor(x.target*.1)):1):0)},'9999-12-31'));
if(!estimates.length||estimates.some(x=>!x))return null;return {effort:Math.max(...estimates.map(x=>x.effort)),neighbors:Math.min(...estimates.map(x=>x.neighbors)),sessions:Math.min(...estimates.map(x=>x.sessions)),method:'por ejercicio'};};
const samples=Learning.samples;Learning.samples=function(logs,now){return samples(logs,now).map(row=>({...row,items:logs[row.date].items}));};
})(window);

;
/* Dosis de diseño, no prescripción clínica. Las restricciones se evalúan antes. */
(function(root){
'use strict';
const levels={basic:{sets:3,reps:14,seconds:40,cardioSets:3,cardioSeconds:50},intermediate:{sets:4,reps:14,seconds:45,cardioSets:3,cardioSeconds:60},advanced:{sets:4,reps:16,seconds:50,cardioSets:4,cardioSeconds:70}};
const scale=p=>Number.isFinite(p.trainingScale)?Math.max(.4,Math.min(1,p.trainingScale)):1;
function dose(e,p,kind){
 const v=levels[p.difficulty]||levels.basic, reduced=Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle;
 if(reduced||kind==='recovery')return {id:e.id,sets:1,target:e.unit==='sec'?(reduced?15:20):6,rest:kind==='recovery'?20:90};
 if(e.family==='power')return {id:e.id,sets:p.difficulty==='advanced'?4:p.difficulty==='intermediate'?3:2,target:Math.max(2,Math.floor((p.difficulty==='advanced'?6:p.difficulty==='intermediate'?5:4)*scale(p))),rest:Math.max(90,p.restSeconds||90)};
 return {id:e.id,sets:kind==='cardio'?v.cardioSets:v.sets,target:Math.max(e.unit==='sec'?10:4,Math.floor((e.unit==='sec'?(kind==='cardio'?v.cardioSeconds:v.seconds):v.reps)*scale(p))),rest:kind==='cardio'?Math.max(45,p.restSeconds||90):Math.max(90,p.restSeconds||90)};
}
const previous=Schedule.items;
Schedule.items=function(p,kind,rotation,carry){
 const items=previous(p,kind,rotation,carry);
 if(!Adapt.mode(p).auto||Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle||kind==='recovery')return items;
 return items.map(x=>({...x,...dose(Engine.byId(x.id),p,kind),target:Engine.byId(x.id).family==='power'?dose(Engine.byId(x.id),p,kind).target:Math.max(Math.floor(x.target*scale(p)),dose(Engine.byId(x.id),p,kind).target)}));
};
const previousMake=Engine.makeCycle;
Engine.makeCycle=function(p,start,index,last,logs){const c=previousMake(p,start,index,last,logs);if(Adapt.mode(p).auto&&!Adapt.mode(p).reduced&&!GoalPlans.plans[p.finalTarget]?.gentle)c.days.forEach(d=>{if(d.kind==='recovery')return;d.items.forEach(x=>{const base=dose(Engine.byId(x.id),p,d.kind);if(Engine.byId(x.id).family==='power'||last&&scale(p)<1)x.target=base.target;else x.target=Math.max(x.target,base.target);x.sets=base.sets;x.rest=Math.max(x.rest,base.rest);});});return c;};
root.TrainingIntensity={levels,dose,scale};
})(typeof window!=='undefined'?window:globalThis);

;
/* Semanas civiles, migración sin perder registros y revisión por ejercicio. */
(function(root){
'use strict';
const E=root.Engine;
const startOfWeek=date=>E.plus(date,-((new Date(date+'T12:00:00').getDay()+6)%7));
const active=l=>!!l&&(l.complete||l.pain||l.manual||l.items?.some(x=>x.missionState||x.selfReported||x.actual?.some(a=>a.done)));
function month(year,number){const first=new Date(year,number,1,12),count=new Date(year,number+1,0,12).getDate();return {year,number,count,offset:(first.getDay()+6)%7,dates:Array.from({length:count},(_,i)=>E.dateKey(new Date(year,number,i+1,12)))};}
const previousMake=E.makeCycle;
E.makeCycle=function(p,start,index,last,logs={}){const c=previousMake({...p,minutes:30},start,index,last,logs);c.days=c.days.slice(0,7);c.profile=JSON.parse(JSON.stringify(p));c.periodDays=7;c.weeklyVersion=10;c.activeFrom=start;c.reviews=[];return c;};
function migrate(s,today){if(s.weeklyVersion===10&&s.cycles.every(c=>c.days.length===7))return false;const groups=new Map();for(const c of s.cycles){for(const d of c.days){const start=startOfWeek(d.date);let w=groups.get(start);if(!w){w={...c,start,days:[],reviews:[],legacyReviews:c.reviews||[],periodDays:7,weeklyVersion:10,activated:start<startOfWeek(today),activeFrom:c.activeFrom||c.start};groups.set(start,w);}const old=w.days.findIndex(x=>x.date===d.date);if(old<0)w.days.push(d);else if(active(s.logs[d.date])||d.customized||d.routineId)w.days[old]=d;}}
s.cycles=[...groups.values()].sort((a,b)=>a.start.localeCompare(b.start)).map((w,i)=>({...w,index:i,days:Array.from({length:7},(_,n)=>w.days.find(d=>d.date===E.plus(w.start,n))||{date:E.plus(w.start,n),kind:'rest',items:[],tasks:[],title:'Fuera del plan anterior'})}));s.weeklyVersion=10;return true;}
function review(last,next,logs,p,now,feedback={}){if(!last||last.start!==E.plus(next.start,-7))return null;const prior=last.days.filter(d=>d.date>=(last.activeFrom||last.start)&&d.items.length),records=prior.map(d=>logs[d.date]).filter(Boolean),done=records.filter(l=>l.complete&&!l.pain),adherence=prior.length?done.length/prior.length:0,efforts=done.map(l=>l.rpe).filter(x=>x>=1&&x<=10),effort=efforts.length?efforts.reduce((a,b)=>a+b,0)/efforts.length:0,mode=Adapt.mode(p),personalReduced=TrainingIntensity.scale(p)<1;
let action='mantener',reason='Semana nueva: mantén la dosis mientras reúnes registros comparables.',changed=0,details=[];
const pain=feedback.pain||records.some(l=>l.pain),fatigue=feedback.fatigue||records.some(l=>l.fatigue||l.rpe>=8);
if(pain||!mode.auto){action='pausar';reason='Dolor registrado o indicaciones pendientes: revisa tu estado antes de volver a entrenar.';}
else if(fatigue){action='reducir';reason='Esfuerzo alto o fatiga: reduce la dosis de la siguiente semana.';}
else if(mode.reduced)reason='Semana adaptada: mantén la dosis reducida y las restricciones registradas.';
else if(personalReduced)reason='Mantén la reducción personal de carga. Puedes cambiarla desde Perfil; dolor y fatiga conservan prioridad.';
else if(adherence<.8)reason='Mantén la dosis: completa al menos el 80 % de los días de ejercicio antes de progresar.';
for(const d of next.days){if(d.date<now||d.customized||d.routineId||active(logs[d.date])||d.tasks?.some(t=>t.done))continue;let edited=false;
 if(action==='pausar'){if(d.items.length){d.items=[];d.kind='rest';d.tasks=[{id:'health-review',text:'Revisar síntomas e indicaciones antes de entrenar',done:false}];edited=true;}}
 else for(const x of d.items){if(action==='reducir'){x.target=Math.max(E.unitOf(x)==='sec'?10:4,Math.floor(x.target*.8));edited=true;}else if(!mode.reduced&&!personalReduced&&adherence>=.8){const r=AdaptiveV9.recommend(logs,x,p,now);details.push({fromId:x.id,...r});if(r.variantChanged&&root.TrainingProgression19){Object.assign(x,root.TrainingProgression19.applyRecommendation(x,r,p));edited=true;}else if(r.target>x.target){x.target=r.target;edited=true;}}}
 if(edited)changed++;
}
if(action==='mantener'&&changed){action='aumentar';reason=details.some(x=>x.variantChanged)?'Registros completos y adherencia suficiente: se avanza a una variante compatible, recalibrando repeticiones y series.':'Tres registros completos del mismo ejercicio y adherencia suficiente: +1 repetición o hasta 10 % de tiempo.';}
const r={week:1,date:now,from:last.start,action,reason,adherence,effort,changed,details};if(root.ProgressMonitor20)r.analysis20=root.ProgressMonitor20.summary(logs,next.days,p,now);next.reviews=[r];next.decision=reason;return r;}
function current(s,today=E.dateKey()){migrate(s,today);const start=startOfWeek(today);
// La carga de capas antiguas puede consultar el plan antes de que el motor
// actual esté disponible. Solo se crea una vista previa durante ese arranque.
if(root.Runtime20?.initializing){const existing=s.cycles.find(c=>c.start===start);if(existing?.activated)return existing;const last=s.cycles.filter(c=>c.start<start).at(-1),preview=E.makeCycle(s.profile,start,(last?.index??-1)+1,last,s.logs);preview.activeFrom=today;preview.previewBoot20=true;return preview;}
let c=s.cycles.find(c=>c.start===start);if(c?.activated)return c;const last=s.cycles.filter(c=>c.start<start).at(-1),index=c?.index??((s.cycles.at(-1)?.index??-1)+1),fresh=E.makeCycle(s.profile,start,index,last,s.logs);fresh.activeFrom=last?.start===E.plus(start,-7)?start:today;
if(c)fresh.days=fresh.days.map(d=>{const old=c.days.find(x=>x.date===d.date);return old&&(old.customized||old.routineId||active(s.logs[d.date])||old.tasks?.some(t=>t.done))?old:d;});
review(last,fresh,s.logs,s.profile,today,s.weekFeedback?.[today]||{});fresh.activated=true;if(c)s.cycles[s.cycles.indexOf(c)]=fresh;else s.cycles.push(fresh);s.cycles.sort((a,b)=>a.start.localeCompare(b.start));return fresh;}
const dailyXP=kind=>kind==='strength'||kind==='mixed'?100:kind==='cardio'?70:kind==='recovery'?40:20;
function progress(l){if(!l||l.pain)return {done:0,total:0,fraction:0,xp:0};const tasks=l.missionTasks||[],total=(l.items||[]).length+tasks.length;let done=tasks.filter(t=>t.done).length;for(const x of l.items||[]){done+=x.selfReported||x.missionState?1:Math.min(1,(x.actual||[]).filter(a=>a.done).length/Math.max(1,x.sets));}const fraction=l.complete?1:total?Math.min(1,done/total):0;return {done,total,fraction,xp:Math.floor(dailyXP(l.kind)*fraction)};}
const oldStats=E.stats;E.stats=function(logs,today=E.dateKey()){const s=oldStats(logs,today);s.xp=Object.entries(logs).filter(([d])=>d<=today).reduce((a,[,l])=>a+progress(l).xp,0);s.level=1+Math.floor(s.xp/300);return s;};
root.Weekly={startOfWeek,month,migrate,review,current,active,progress,dailyXP};
})(typeof window!=='undefined'?window:globalThis);

;
/* Catálogo de fuentes: los títulos no bastan para inventar una rutina. */
(function(root){
'use strict';
const channels=[
{key:'sergio',name:'Entrena con Sergio Peinado',channelId:'UC9l7xbkF_n-WP61f74AjKvw',url:'https://www.youtube.com/@EntrenaSergioPeinado'},
{key:'pau',name:'Pau Fit',channelId:'UCQ_N_uuyHbTE8qZ_GRIJmKw',url:'https://www.youtube.com/@PauFitYT'},
{key:'naty',name:'NatyGloss Gym',channelId:'UCUqMVnhjUZyW-K5r5AJglNA',url:'https://www.youtube.com/@NatyGlossGym'},
{key:'metal',name:'Metal Pump',channelId:'UCraP6R9xSfBClJbQJ_VNU0Q',url:'https://www.youtube.com/@metal-pump'},
{key:'chuy',name:'Chuy Almada',channelId:'UC6Z3joUwYfM-Zr1U3bZLztQ',url:'https://www.youtube.com/channel/UC6Z3joUwYfM-Zr1U3bZLztQ'}];
const integer=(n,min,max)=>Number.isInteger(n)&&n>=min&&n<=max;
function chapters(description){const out=[];let previous=-1;for(const line of String(description||'').split('\n')){const m=line.trim().match(/^(?:(\d{1,2}):)?(\d{1,2}):(\d{2})\s+(.+)$/);if(!m||+m[3]>=60||(m[1]&&+m[2]>=60))continue;const at=Number(m[1]||0)*3600+Number(m[2])*60+Number(m[3]);if(at<=previous||at>86400)continue;previous=at;out.push({at,label:m[4].trim().slice(0,160)});if(out.length>=150)break;}return out;}
function video(v,channelId){if(!v||!/^[-\w]{11}$/.test(v.id)||typeof v.title!=='string'||!v.title.trim())return null;const parsed=Date.parse(v.published);const marks=Array.isArray(v.chapters)?v.chapters.filter(x=>x&&integer(x.at,0,86400)&&typeof x.label==='string').map(x=>({at:x.at,label:x.label.slice(0,160)})).slice(0,150):chapters(v.description);return {id:v.id,channelId,title:v.title.slice(0,160),published:Number.isFinite(parsed)?new Date(parsed).toISOString():'',chapters:marks,url:'https://www.youtube.com/watch?v='+v.id};}
function normalize(raw){const result={updated:Number.isFinite(raw?.updated)?raw.updated:0,channels:[]},input=Array.isArray(raw?.channels)?raw.channels:[];for(const c of channels){const source=input.find(x=>x&&x.channelId===c.channelId)||{},videos=(Array.isArray(source.videos)?source.videos:[]).slice(0,150).map(v=>video(v,c.channelId)).filter(Boolean);result.channels.push({...c,status:source.status==='ok'?'ok':'unavailable',lastSuccess:Number.isFinite(Number(source.lastSuccess))?Number(source.lastSuccess):0,videos:[...new Map(videos.map(x=>[x.id,x])).values()].sort((a,b)=>b.published.localeCompare(a.published)).slice(0,100)});}return result;}
function merge(old,raw){const next=normalize(raw),prior=normalize(old);for(const c of next.channels){const before=prior.channels.find(x=>x.channelId===c.channelId);c.videos=[...c.videos,...before.videos.filter(x=>!c.videos.some(v=>v.id===x.id))].sort((a,b)=>b.published.localeCompare(a.published)).slice(0,100);if(c.status!=='ok')c.lastSuccess=before.lastSuccess;}return next;}
function validateRoutine(r){if(!r||!/^[-\w]{11}$/.test(r.videoId)||!channels.some(c=>c.channelId===r.channelId)||typeof r.name!=='string'||r.name.length>100||!r.name.trim()||r.verified!==true||typeof r.verificationNote!=='string'||!r.verificationNote.trim()||r.verificationNote.length>500||!Array.isArray(r.steps)||r.steps.length<1||r.steps.length>320)return false;
return r.steps.every(s=>s&&['exercise','rest','preparation','cooldown'].includes(s.kind)&&(s.kind==='exercise'?!!Engine.byId(s.exerciseId)&&integer(s.sets,1,4)&&(s.unit===undefined||s.unit==='sec'||s.unit===Engine.byId(s.exerciseId).unit)&&(s.side===undefined||['left','right','both'].includes(s.side))&&integer(s.target,1,(s.unit||Engine.byId(s.exerciseId).unit)==='sec'?3600:300)&&integer(s.restSeconds,0,600):integer(s.seconds,1,3600)&&typeof s.label==='string'&&s.label.length<=160))&&r.steps.some(s=>s.kind==='exercise')&&r.steps.filter(s=>s.kind==='exercise').length<=96;
}
function asRoutine(r){if(!validateRoutine(r))throw Error('Secuencia incompleta o no verificada');const items=r.steps.filter(s=>s.kind==='exercise').map(s=>({id:s.exerciseId,sets:s.sets,target:s.target,...(s.unit?{unit:s.unit}:{}),...(s.side?{side:s.side,missionTitle:Engine.byId(s.exerciseId).name+(s.side==='left'?' · lado izquierdo':s.side==='right'?' · lado derecho':' · ambos lados')}:{}),rest:s.restSeconds})),categories=[...new Set(items.map(x=>Engine.byId(x.id).category))];return {id:'creator'+r.videoId,name:r.name,description:r.evidenceType==='captions-and-chapters'?'Secuencia textual reconstruida de subtítulos y capítulos. Revisión visual pendiente. Se conservan cantidades y pausas documentadas.':'Secuencia declarada como verificada. Se conserva el orden, las cantidades y los descansos.',items,category:categories.length===1?categories[0]:'Mixta',requirements:[...new Set(items.flatMap(x=>Engine.byId(x.id).requirements))],level:Math.max(...items.map(x=>Engine.byId(x.id).level)),minutes:Math.ceil(r.steps.reduce((n,s)=>n+(s.seconds||s.sets*(s.target*((s.unit||Engine.byId(s.exerciseId).unit)==='sec'?1:4)+s.restSeconds)),0)/60),impact:items.some(x=>Engine.byId(x.id).impact==='Con saltos')?'Con saltos':'Sin saltos',sourceSequence:JSON.parse(JSON.stringify(r)),locked:true};}
function assignWeek(c,p,raw,logs,now){const mode=Adapt.mode(p);if(!mode.auto||mode.reduced||['reducir','pausar'].includes(c.reviews?.at(-1)?.action))return 0;const routines=raw.filter(validateRoutine).map(asRoutine).filter(r=>Adapt.routineCompatible(r,p));let count=0;for(const [i,d] of c.days.entries()){if(d.date<now||!d.items.length||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;const category=d.kind==='cardio'?'Cardio':d.kind==='recovery'?'Movilidad':'Fuerza',candidates=routines.filter(r=>r.category===category||(category==='Fuerza'&&r.category==='Mixta')).map(r=>({r,prediction:Learning.predict(Learning.samples(logs,now),r.items)})).filter(x=>!x.prediction||x.prediction.effort<8).sort((a,b)=>(a.prediction?Math.abs(a.prediction.effort-6):2)-(b.prediction?Math.abs(b.prediction.effort-6):2)||a.r.level-b.r.level);if(!candidates.length)continue;const r=candidates[(c.index+i)%Math.min(3,candidates.length)].r;d.items=JSON.parse(JSON.stringify(r.items));d.title=r.name;d.kind=r.category==='Mixta'?'mixed':d.kind;d.routineId=r.id;d.sourceSequence=JSON.parse(JSON.stringify(r.sourceSequence));d.tasks=[];d.creatorAssigned=true;count++;}return count;}
root.CreatorFeed={channels,chapters,video,normalize,merge,validateRoutine,asRoutine,assignWeek};
})(typeof window!=='undefined'?window:globalThis);

