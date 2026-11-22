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
