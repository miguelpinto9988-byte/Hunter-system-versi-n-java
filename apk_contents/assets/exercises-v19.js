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
