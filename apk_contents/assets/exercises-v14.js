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
