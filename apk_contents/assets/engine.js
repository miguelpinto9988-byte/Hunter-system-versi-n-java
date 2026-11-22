/* Hunter System — deterministic local training engine. */
(function(root){
'use strict';
const ex=(id,name,group,equipment,level,steps,cue,unit='reps',sides=false)=>({id,name,group,equipment,level,steps,cue,unit,sides});
const exercises=[
ex('chair','Sentarse y levantarse','Piernas','body',0,'Coloca una silla firme contra la pared. Apoya los pies, inclina suavemente el torso y levántate. Baja con control.','Usa una altura que permita moverte sin dolor.'),
ex('squat','Sentadilla libre','Piernas','body',1,'Pies a una distancia cómoda. Lleva las caderas atrás y flexiona las rodillas. Vuelve a subir con control.','Mantén los pies apoyados y un recorrido cómodo.'),
ex('goblet','Sentadilla goblet','Piernas','db',1,'Sujeta una mancuerna junto al pecho. Flexiona caderas y rodillas, y vuelve a subir.','Empieza con una carga que puedas controlar.'),
ex('wall','Flexión en pared','Pecho','body',0,'Apoya las manos en una pared a la altura del pecho. Flexiona los codos y acerca el torso; empuja para volver.','Mantén el cuerpo alineado.'),
ex('incline','Flexión inclinada','Pecho','body',1,'Apoya las manos en una superficie alta, firme y fija. Baja el pecho y empuja con control.','No uses muebles que se deslicen o vuelquen.'),
ex('pushup','Flexión de suelo','Pecho','body',2,'Manos debajo de los hombros. Baja el torso de forma controlada y vuelve a subir.','Evita hundir la zona lumbar.'),
ex('press','Press de suelo','Pecho','db',1,'Túmbate boca arriba con rodillas flexionadas. Empuja las mancuernas desde el pecho y baja hasta apoyar suavemente los brazos.','Muñecas alineadas; no golpees el suelo.'),
ex('neutral','Press de suelo neutro','Pecho','db',1,'En el suelo, palmas enfrentadas. Empuja las mancuernas hacia arriba y baja lentamente.','Mantén los codos cerca del torso.'),
ex('bridge','Puente de glúteos','Cadera','body',0,'Boca arriba, pies apoyados y rodillas flexionadas. Eleva la pelvis y baja lentamente.','No arquees la espalda al subir.'),
ex('hinge','Bisagra de cadera','Cadera','body',0,'Rodillas ligeramente flexionadas. Lleva las caderas atrás con espalda neutra y vuelve a erguirte.','El movimiento parte de la cadera.'),
ex('rdl','Peso muerto rumano','Cadera','db',1,'Mancuernas cerca de los muslos. Lleva las caderas atrás y baja hasta donde controles la espalda; vuelve a subir.','Mantén las cargas cerca del cuerpo.'),
ex('row','Remo con apoyo','Espalda','db',0,'Apoya una mano en una superficie firme. Con la otra, lleva la mancuerna hacia la cadera y baja con control.','Completa ambos lados. No gires el torso.','reps',true),
ex('bentrow','Remo bilateral','Espalda','db',1,'Inclina el torso con una bisagra de cadera. Lleva ambas mancuernas hacia las caderas y bájalas.','Sostén la espalda en una posición cómoda y estable.'),
ex('prone','Elevación en W','Espalda','body',0,'Boca abajo, brazos en W. Eleva suavemente los brazos juntando los omóplatos, y baja.','Recorrido corto; no fuerces cuello ni espalda.'),
ex('reverse','Ángel invertido corto','Espalda','body',1,'Boca abajo, frente sobre una toalla. Desliza los brazos en un arco corto y controlado.','Mantén el cuello relajado.'),
ex('curl','Curl de bíceps','Brazos','db',0,'De pie, brazos junto al cuerpo. Flexiona los codos para subir las mancuernas y bájalas lentamente.','Evita balancear el torso.'),
ex('hammer','Curl martillo','Brazos','db',0,'Palmas enfrentadas, codos al costado. Sube las mancuernas y baja con control.','No levantes los hombros.'),
ex('calf','Elevación de talones','Piernas','body',0,'De pie junto a un apoyo estable. Eleva los talones y desciende despacio.','Usa el apoyo para el equilibrio.'),
ex('dbcalf','Talones con mancuernas','Piernas','db',1,'De pie, sujeta cargas ligeras a los lados. Eleva los talones y baja despacio.','Si pierdes estabilidad, usa la versión sin carga.'),
ex('deadbug','Dead bug','Core','body',0,'Boca arriba, rodillas elevadas. Extiende un brazo y la pierna contraria, vuelve y alterna.','Cada lado cuenta una repetición. No arquees la espalda.'),
ex('bird','Bird dog','Core','body',0,'A cuatro apoyos, extiende un brazo y la pierna contraria. Regresa y alterna.','Cada lado cuenta una repetición; pelvis estable.'),
ex('plank','Plancha de antebrazos','Core','body',2,'Apoya antebrazos y puntas de pies. Mantén el cuerpo alineado mientras respiras.','Termina la serie si pierdes la posición.','sec'),
ex('heel','Deslizamiento de talón','Core','body',0,'Boca arriba con rodillas flexionadas. Desliza un talón hacia delante y vuelve; alterna.','Cada lado cuenta una repetición; respira con normalidad.'),
ex('sideleg','Abducción lateral','Cadera','body',0,'Túmbate de lado. Eleva la pierna superior con un recorrido corto y baja lentamente.','Completa ambos lados sin girar la pelvis.','reps',true),
ex('march','Marcha suave en casa','Movilidad','body',0,'Camina o marcha a un ritmo cómodo, con espacio libre alrededor.','Reduce el ritmo si no puedes hablar con comodidad.','sec'),
ex('shoulder','Círculos de hombros','Movilidad','body',0,'De pie o sentado, dibuja círculos pequeños con los hombros hacia atrás y hacia delante.','Movimiento suave, sin forzar.','sec'),
ex('ankle','Movilidad de tobillos','Movilidad','body',0,'Sentado, mueve los tobillos lentamente arriba, abajo y en círculos.','Alterna los dos tobillos.','sec'),
ex('cat','Movilidad de columna','Movilidad','body',0,'A cuatro apoyos, redondea y extiende suavemente la espalda acompañando la respiración.','Usa un rango pequeño y cómodo.'),
ex('wallslide','Deslizamiento en pared','Hombros','body',0,'De pie junto a una pared, desliza los brazos lentamente hacia arriba y abajo.','No fuerces el recorrido por encima de la cabeza.'),
ex('lateral','Elevación lateral ligera','Hombros','db',1,'Con cargas ligeras, eleva los brazos a los lados hasta una altura cómoda y baja.','Codos suaves; no encojas los hombros.')
];
// Incrementally index the append-only catalogue, including callers that
// capture this local function before later compatibility layers are installed.
const exerciseIndex48=new Map();let indexed48=0;
function byId(id){
 if(indexed48>exercises.length){exerciseIndex48.clear();indexed48=0;}
 for(;indexed48<exercises.length;indexed48++){const e=exercises[indexed48];if(!exerciseIndex48.has(e.id))exerciseIndex48.set(e.id,e);}
 return exerciseIndex48.get(id);
}
function dateKey(d=new Date()){return `${d.getFullYear()}-${String(d.getMonth()+1).padStart(2,'0')}-${String(d.getDate()).padStart(2,'0')}`;}
function plus(date,n){let d=new Date(date+'T12:00:00');d.setDate(d.getDate()+n);return dateKey(d);}
function diff(a,b){return Math.round((Date.parse(a+'T12:00:00Z')-Date.parse(b+'T12:00:00Z'))/86400000);}
function eligible(p){return p.age>=18&&p.age<=64&&!p.limits;}
function makeCycle(p,start,index,previous,logs){
 let reps=8,sets=p.experience==='beginner'?2:3,decision='Adaptación inicial: aprende los movimientos con cargas cómodas.';
 if(previous){
 const sessions=previous.days.filter(d=>d.kind==='strength');
 const done=sessions.map(d=>logs[d.date]).filter(l=>l&&l.complete&&!l.manual);
 const painful=previous.days.some(d=>logs[d.date]?.pain);
 const effort=done.length?done.reduce((a,l)=>a+l.rpe,0)/done.length:0;
 reps=previous.reps;sets=previous.sets;
 if(painful||effort>=9){reps=Math.max(6,reps-2);decision='Menor volumen por molestias o esfuerzo alto. Si hubo dolor, consulta antes de retomar.';}
 else if(sessions.length>0&&done.length>=Math.ceil(sessions.length*.8)&&effort<=7&&done.every(l=>l.rpe>0)){
 reps=Math.min(12,reps+1);decision=reps>previous.reps?'Progresión: +1 repetición por serie, según tu registro.':'Consolidación: mantén 12 repeticiones. Puedes revisar la carga con un profesional.';
 }else decision='Consolidación: conserva las repeticiones y mejora la técnica y la constancia.';
 }
 const db=p.equipment==='db', easy=p.experience==='beginner', rot=index%2;
 let A=db?[easy?'squat':'goblet','press','row','bridge','deadbug']:['squat','wall','prone','bridge','deadbug'];
 let B=db?['rdl','neutral','row','calf','bird']:['hinge','incline','reverse','calf','bird'];
 if(!easy&&!db){A=['squat','incline','reverse','bridge','deadbug'];B=['squat','pushup','prone','sideleg','bird'];}
 if(rot){A[A.length-1]='heel'; B[B.length-1]='deadbug'; if(db){A[1]='neutral';B[1]='press';}}
 const strengthDays=p.frequency===2?[0,3]:[0,2,4];
 let days=Array.from({length:21},(_,i)=>{
 const kind=strengthDays.includes(i%7)?'strength':i%7===6?'rest':'recovery';
 const ids=kind==='strength'?((Math.floor(i/7)*strengthDays.length+strengthDays.indexOf(i%7))%2?B:A):kind==='recovery'?['march','shoulder','ankle']:[];
 return {date:plus(start,i),kind,items:ids.map(id=>{const e=byId(id);return {id,sets:kind==='strength'?sets:1,target:e.unit==='sec'?(id==='march'?180:30):reps};})};
 });
 return {index,start,reps,sets,decision,days,profile:JSON.parse(JSON.stringify(p))};
}
const unitOf=item=>item.unit||byId(item.id).unit;
const sidesOf=item=>byId(item.id).sides&&(!item.side||item.side==='both');
function stats(logs,today=dateKey()){
 const entries=Object.entries(logs).filter(([d])=>d<=today), completed=entries.filter(([,l])=>l.complete);
 let series=0,reps=0,seconds=0,volume=0,exerciseCount=0;
 for(const [,l] of entries)for(const item of l.items||[]){let count=0;for(const s of item.actual||[]){if(s.done){series++;count++;const n=Number(s.value)||0;if(unitOf(item)==='sec')seconds+=n*(sidesOf(item)?2:1);else {const n2=n*(sidesOf(item)?2:1);reps+=n2;volume+=n2*(Number(s.kg)||0);}}}if(count)exerciseCount++;}
 let cursor=completed.some(([d])=>d===today)?today:plus(today,-1), streak=0; const dates=new Set(completed.map(([d])=>d));while(dates.has(cursor)){streak++;cursor=plus(cursor,-1);}
 const xp=completed.reduce((a,[,l])=>a+(l.kind==='strength'?100:l.kind==='cardio'?70:l.kind==='mixed'?100:l.kind==='recovery'?40:20),0);
 return {days:completed.length,strength:completed.filter(([,l])=>l.kind==='strength').length,series,reps,seconds,volume,exerciseCount,streak,xp,level:1+Math.floor(xp/300)};
}
const api={exercises,byId,unitOf,sidesOf,dateKey,plus,diff,eligible,makeCycle,stats};
if(typeof module!=='undefined')module.exports=api;root.Engine=api;
})(typeof window!=='undefined'?window:globalThis);
