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
