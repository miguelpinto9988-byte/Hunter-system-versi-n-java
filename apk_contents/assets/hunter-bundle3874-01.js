/* Hunter System 38.75 - Conservative WebView bridge compatibility layer.
   Existing Android interface is retained. A future WebMessage host may opt in
   only after native origin validation and an explicit capability handshake.
   Never caches backup/import or notification actions. */
(function(root,doc){'use strict';
  if(root.HunterNative3875)return;
  const status={sent:0,skipped:0,failed:0,messageSent:0,legacySent:0};
  const previous=Object.create(null);
  let lastTheme='';
  function nativeObject(){return root.Android||null;}
  function secureMessages(){return root.__HUNTER_WEB_MESSAGES_READY_V1__===true &&
    root.HunterMessages && typeof root.HunterMessages.postMessage==='function';}
  function send(type,payload,legacy){
    try {
      if(secureMessages()){
        root.HunterMessages.postMessage(JSON.stringify({schema:1,type:type,payload:payload}));
        status.messageSent++;status.sent++;return true;
      }
      const bridge=nativeObject();
      if(!bridge||typeof bridge[legacy]!=='function')return false;
      bridge[legacy](type==='syncPlan'?JSON.stringify(payload):payload);
      status.legacySent++;status.sent++;return true;
    }catch(error){status.failed++;return false;}
  }
  function syncPlan(data){
    if(!data||typeof data!=='object'||!Array.isArray(data.days))return false;
    let serialized;try{serialized=JSON.stringify(data);}catch(e){return false;}
    if(serialized===previous.syncPlan){status.skipped++;return true;}
    const ok=send('syncPlan',data,'syncPlan');
    if(ok)previous.syncPlan=serialized;
    return ok;
  }
  function setThemeBackground(color){
    if(typeof color!=='string'||!/^#[0-9a-fA-F]{6}$/.test(color))return false;
    color=color.toLowerCase();
    if(color===lastTheme){status.skipped++;return true;}
    const ok=send('setThemeBackground',color,'setThemeBackground');
    if(ok)lastTheme=color;
    return ok;
  }
  function reset(){previous.syncPlan=null;lastTheme='';}
  doc.addEventListener('visibilitychange',function(){if(doc.hidden)reset();},{passive:true});
  root.HunterNative3875=Object.freeze({version:'38.75',syncPlan,setThemeBackground,
    snapshot:()=>({...status,messageReady:!!secureMessages(),legacyReady:!!nativeObject()})});
})(window,document);

;
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

;
/* Catálogo local de ejercicios y sesiones. Las dosis son plantillas editables. */
(function(root){
const E=root.Engine;
const rename={chair:'Sentadilla a silla',goblet:'Sentadilla con mancuerna al pecho',press:'Press de pecho en suelo',neutral:'Press de pecho con agarre neutro',rdl:'Peso muerto rumano con mancuernas',row:'Remo unilateral con mancuerna',bentrow:'Remo inclinado con mancuernas',curl:'Flexión de bíceps con mancuernas',hammer:'Flexión de bíceps con agarre martillo',deadbug:'Extensión alterna de brazo y pierna',bird:'Extensión contralateral en cuadrupedia',plank:'Plancha abdominal',reverse:'Apertura de brazos boca abajo'};
E.exercises.forEach(e=>{e.name=rename[e.id]||e.name;e.category=e.group==='Movilidad'?'Movilidad':'Fuerza';e.requirements=e.equipment==='db'?['db']:[];e.impact='Bajo';});
// id;nombre;grupo;equipo;experiencia;unidad;ambos lados;posición y ejecución;control;alternativa
const rows=[
['benchpress','Press de banca con mancuernas','Pecho','db,bench',1,'reps',false,'Túmbate sobre un banco de entrenamiento estable, con pies apoyados y mancuernas junto al pecho.|Empuja las cargas hacia arriba sin chocar las mancuernas.|Baja con control hasta un recorrido cómodo para tus hombros.','No uses una mesa, cama o silla como banco. Mantén las muñecas alineadas.','press'],
['inclinebench','Press inclinado con mancuernas','Pecho','db,bench',1,'reps',false,'Ajusta un banco de entrenamiento a una inclinación moderada.|Apoya espalda y pies; lleva las mancuernas a los lados del pecho.|Empuja hacia arriba y baja sin rebotes.','El banco debe bloquearse en la posición elegida. No arquees la zona lumbar.','neutral'],
['kneepush','Flexión de brazos con rodillas apoyadas','Pecho','',0,'reps',false,'Apoya manos y rodillas sobre el suelo, con una línea recta desde rodillas a cabeza.|Flexiona los codos y acerca el pecho al suelo.|Empuja para regresar manteniendo el abdomen activo.','No dejes las caderas atrás ni hundas la espalda.','wall'],
['closepush','Flexión con manos próximas','Pecho','',2,'reps',false,'En posición de flexión, acerca ligeramente las manos sin forzar las muñecas.|Baja el pecho con los codos próximos al cuerpo.|Empuja hasta recuperar la posición inicial.','No es necesario juntar las manos en diamante.','kneepush'],
['squeezepress','Press de pecho con mancuernas juntas','Pecho','db',1,'reps',false,'Túmbate en el suelo y sujeta las mancuernas juntas sobre el pecho.|Empuja ambas cargas hacia arriba con las palmas enfrentadas.|Baja despacio hasta apoyar suavemente los brazos.','Usa cargas que no resbalen y evita apretar con fuerza excesiva.','neutral'],
['sumo','Sentadilla con postura amplia','Piernas','',1,'reps',false,'Separa los pies algo más que los hombros y orienta las puntas ligeramente hacia fuera.|Flexiona caderas y rodillas manteniendo los pies apoyados.|Sube con control.','Las rodillas siguen la dirección de los pies; no fuerces la apertura.','squat'],
['dbsumo','Sentadilla amplia con mancuerna','Piernas','db',1,'reps',false,'Sostén una mancuerna delante del cuerpo con los brazos extendidos.|Adopta una postura amplia y baja flexionando caderas y rodillas.|Empuja el suelo para volver a subir.','Mantén la mancuerna cerca y la espalda en una posición cómoda.','sumo'],
['reverselunge','Zancada hacia atrás','Piernas','',1,'reps',true,'De pie junto a un apoyo si lo necesitas, da un paso atrás.|Flexiona ambas rodillas hasta un recorrido cómodo.|Empuja con el pie delantero para volver y completa el otro lado.','Evita pasos demasiado estrechos o perder el equilibrio.','chair'],
['dblunge','Zancada hacia atrás con mancuernas','Piernas','db',2,'reps',true,'Sostén las mancuernas a los lados y da un paso atrás.|Baja con control flexionando ambas rodillas.|Vuelve a la posición inicial y repite en ambos lados.','Domina primero la variante sin cargas.','reverselunge'],
['split','Sentadilla dividida','Piernas','',1,'reps',true,'Coloca un pie delante y otro detrás sin cruzarlos.|Baja verticalmente flexionando ambas rodillas.|Sube manteniendo los pies en el mismo lugar.','Un apoyo estable puede ayudar al equilibrio.','chair'],
['lateralunge','Zancada lateral','Piernas','',1,'reps',true,'Da un paso amplio hacia un lado.|Flexiona la rodilla de ese lado y lleva la cadera atrás.|Empuja con ese pie para volver al centro.','No fuerces profundidad ni estires bruscamente la otra pierna.','squat'],
['stepup','Subida a plataforma baja','Piernas','step',1,'reps',true,'Coloca un pie sobre una plataforma baja de ejercicio fija.|Sube empujando con esa pierna y apoya el otro pie.|Desciende despacio y alterna la pierna de inicio.','No uses sillas, pilas de libros o superficies que se muevan.','march'],
['wallsit','Sentadilla isométrica en pared','Piernas','',1,'sec',false,'Apoya la espalda en una pared y adelanta un poco los pies.|Deslízate hacia abajo hasta un ángulo cómodo.|Mantén la postura respirando y vuelve a subir con control.','No es necesario bajar hasta 90 grados.','chair'],
['singlecalf','Elevación de talón a una pierna','Piernas','',1,'reps',true,'Colócate junto a una pared y levanta ligeramente un pie.|Sube el talón de la pierna apoyada.|Baja despacio y cambia de pierna.','Usa apoyo y no rebotes.','calf'],
['seatedcalf','Elevación de talones sentado','Piernas','',0,'reps',false,'Siéntate en una silla estable con los pies bajo las rodillas.|Eleva ambos talones manteniendo las puntas apoyadas.|Baja lentamente.','Mantén los tobillos alineados con las piernas.','calf'],
['singlebridge','Puente de glúteo a una pierna','Cadera','',2,'reps',true,'Túmbate boca arriba, apoya un pie y eleva la otra pierna.|Sube la pelvis con la pierna apoyada.|Baja lentamente sin girar la cadera.','Si pierdes control, apoya ambos pies.','bridge'],
['dbbridge','Puente de glúteos con mancuerna','Cadera','db',1,'reps',false,'Boca arriba, coloca una mancuerna ligera y acolchada sobre la pelvis y sujétala.|Apoya los pies y eleva la pelvis.|Baja despacio manteniendo la carga estable.','Evita colocar la carga sobre el abdomen.','bridge'],
['kickback','Extensión de cadera en cuadrupedia','Cadera','',0,'reps',true,'Apoya manos y rodillas en el suelo.|Lleva una pierna hacia atrás sin arquear la espalda.|Regresa despacio y repite con la otra pierna.','El movimiento termina antes de que la pelvis gire.','bridge'],
['clam','Apertura de cadera de lado','Cadera','',0,'reps',true,'Túmbate de lado con las rodillas flexionadas y pies juntos.|Abre la rodilla superior sin separar los pies.|Baja con control y cambia de lado.','Evita rodar el torso hacia atrás.','sideleg'],
['singlehinge','Bisagra de cadera con apoyo','Cadera','',1,'reps',true,'Apóyate en una pared con una mano y carga el peso en una pierna.|Lleva la cadera atrás inclinando suavemente el torso.|Vuelve a erguirte y cambia de lado.','El apoyo es para equilibrio; no busques tocar el suelo.','hinge'],
['sumodead','Peso muerto con postura amplia','Cadera','db',1,'reps',false,'Sostén una mancuerna entre las piernas, con pies algo separados.|Lleva cadera atrás y flexiona ligeramente las rodillas.|Vuelve a erguirte sin inclinarte hacia atrás al terminar.','Mantén la carga próxima y el cuello alineado.','hinge'],
['pullover','Extensión de hombros con mancuerna en suelo','Espalda','db',2,'reps',false,'Boca arriba, sujeta una mancuerna ligera sobre el pecho con ambas manos.|Llévala lentamente hacia atrás solo hasta un rango cómodo.|Regresa sobre el pecho sin arquear la espalda.','Evita bajar la carga si molesta el hombro.','prone'],
['reversefly','Apertura posterior con mancuernas','Espalda','db',1,'reps',false,'Inclina el torso desde la cadera con rodillas suaves.|Con cargas ligeras, abre los brazos a los lados.|Baja despacio manteniendo el torso estable.','No uses impulso ni eleves los hombros hacia las orejas.','prone'],
['towelrow','Remo isométrico con toalla','Espalda','',0,'sec',false,'Sentado, pasa una toalla resistente alrededor de las plantas de los pies.|Sujeta ambos extremos y tira suavemente hacia el torso sin mover los pies.|Mantén unos segundos respirando y afloja.','No enrolles la toalla en el cuello ni la ancles a una puerta.','prone'],
['scapular','Retracción de omóplatos de pie','Espalda','',0,'reps',false,'De pie o sentado, coloca los brazos junto al cuerpo.|Acerca suavemente los omóplatos sin elevar los hombros.|Relaja y repite con control.','No arquees la espalda para aumentar el recorrido.','prone'],
['overheadpress','Press de hombros con mancuernas','Hombros','db',1,'reps',false,'Sostén mancuernas ligeras a la altura de los hombros.|Empuja hacia arriba hasta una posición cómoda.|Baja lentamente a la posición inicial.','Mantén las costillas controladas; no compenses arqueando la espalda.','wallslide'],
['frontlift','Elevación frontal con mancuernas','Hombros','db',1,'reps',false,'Sujeta cargas ligeras delante de los muslos.|Eleva los brazos hasta una altura cómoda, como máximo al nivel de los hombros.|Baja despacio.','No balancees el cuerpo ni bloquees los codos.','wallslide'],
['shouldertap','Toque alterno de hombros','Core','',2,'reps',false,'En posición de flexión, separa ligeramente los pies.|Toca un hombro con la mano contraria y vuelve a apoyarla.|Alterna lados con el torso estable.','Cada toque cuenta una repetición.','bird'],
['seatedcurl','Flexión de bíceps sentado','Brazos','db',0,'reps',false,'Siéntate en una silla firme con espalda cómoda y pies apoyados.|Flexiona los codos para elevar las mancuernas.|Baja lentamente sin mover los brazos hacia delante.','No balancees el torso.','curl'],
['concentration','Flexión de bíceps con apoyo','Brazos','db',1,'reps',true,'Sentado, apoya suavemente el brazo en la cara interna del muslo.|Flexiona el codo para elevar una mancuerna.|Baja despacio y cambia de brazo.','No empujes la carga con el cuerpo.','curl'],
['tricep','Extensión de tríceps inclinada','Brazos','db',1,'reps',true,'Inclina el torso y apoya una mano en una superficie fija.|Con el otro brazo junto al cuerpo, extiende el codo hacia atrás.|Flexiona de nuevo sin mover el hombro.','Usa una carga ligera y no bloquees con fuerza el codo.','hammer'],
['lyingtricep','Extensión de tríceps en suelo','Brazos','db',1,'reps',false,'Túmbate en el suelo con cargas ligeras sobre el pecho.|Flexiona los codos llevando las mancuernas a los lados de la cabeza.|Extiende los codos con control.','Evita llevar las cargas sobre la cara.','neutral'],
['reversecurl','Flexión de bíceps con agarre prono','Brazos','db',1,'reps',false,'Sujeta mancuernas ligeras con las palmas hacia atrás.|Flexiona los codos manteniendo las muñecas alineadas.|Baja lentamente.','Reduce la carga si doblas las muñecas.','hammer'],
['sideplank','Plancha lateral con rodillas','Core','',1,'sec',true,'De lado, apoya un antebrazo bajo el hombro y las rodillas flexionadas.|Eleva la cadera formando una línea entre rodillas y cabeza.|Mantén respirando, baja y cambia de lado.','Registra los segundos de un lado tras completar ambos.','heel'],
['kneelingplank','Plancha abdominal con rodillas','Core','',0,'sec',false,'Apoya antebrazos y rodillas en el suelo.|Alinea tronco y muslos con el abdomen activo.|Mantén mientras puedes respirar y sostener la postura.','No hundas la zona lumbar.','heel'],
['reversecrunch','Elevación de pelvis corta','Core','',1,'reps',false,'Boca arriba, eleva las rodillas con las piernas flexionadas.|Acerca suavemente las rodillas al torso elevando un poco la pelvis.|Regresa sin balancear las piernas.','El recorrido es corto; evita impulso.','heel'],
['curlup','Flexión abdominal corta','Core','',1,'reps',false,'Boca arriba con rodillas flexionadas, manos sobre los muslos.|Eleva ligeramente cabeza y hombros deslizando las manos.|Baja despacio.','No tires del cuello ni te impulses.','heel'],
['standingcore','Elevación alterna de rodilla','Core','',0,'reps',false,'De pie junto a un apoyo estable, activa suavemente el abdomen.|Eleva una rodilla hasta una altura cómoda.|Baja y alterna.','Cada elevación cuenta una repetición.','heel'],
['suitcase','Sujeción unilateral de mancuerna','Core','db',1,'sec',true,'De pie, sujeta una mancuerna a un lado del cuerpo.|Mantén el torso erguido sin inclinarte hacia la carga.|Respira y cambia de mano tras el intervalo.','Registra segundos por lado después de hacer ambos.','standingcore'],
['fastmarch','Marcha rápida sin saltos','Cardio','',0,'sec',false,'Marcha en el lugar alternando pies y brazos.|Aumenta ligeramente el ritmo sin saltar.|Mantén un paso estable y reduce la velocidad al terminar.','Deja libre el suelo y usa un ritmo que te permita hablar.','march'],
['sidestep','Paso lateral continuo','Cardio','',0,'sec',false,'Da un paso hacia un lado y acerca el otro pie.|Repite al lado contrario con rodillas suaves.|Acompaña con los brazos sin cruzar los pies.','No deslices los pies sobre una superficie resbaladiza.','march'],
['stepjack','Aperturas laterales sin salto','Cardio','',0,'sec',false,'Abre un pie hacia un lado mientras elevas los brazos hasta una altura cómoda.|Vuelve al centro.|Alterna lados manteniendo siempre un pie apoyado.','No fuerces el recorrido de hombros.','sidestep'],
['box','Boxeo al aire suave','Cardio','',0,'sec',false,'De pie, pies separados y rodillas suaves.|Extiende alternativamente los brazos al frente sin golpear objetos.|Recoge los brazos y mantén un ritmo cómodo.','No bloquees los codos ni uses mancuernas para golpear.','march'],
['heelback','Talones alternos hacia atrás','Cardio','',0,'sec',false,'De pie junto a un apoyo si lo necesitas.|Lleva un talón hacia atrás flexionando la rodilla.|Alterna piernas sin saltos.','No arquees la espalda para subir el talón.','march'],
['kneeliftcardio','Rodillas alternas sin salto','Cardio','',0,'sec',false,'De pie, eleva una rodilla y acompaña con el brazo contrario.|Vuelve a apoyar el pie.|Alterna a un ritmo regular sin saltar.','Escoge una altura que permita mantener el equilibrio.','fastmarch'],
['walk','Caminata continua en casa','Cardio','',0,'sec',false,'Despeja un recorrido corto o camina en el sitio.|Avanza a un ritmo cómodo y gira con pasos pequeños.|Mantén la respiración regular.','Evita girar sobre una rodilla fija.','march'],
['skater','Paso de patinador sin salto','Cardio','',1,'sec',false,'Desplaza el peso de un pie al otro con pasos laterales.|Lleva suavemente la otra pierna hacia atrás sin cruzarla en exceso.|Alterna con el torso controlado.','Mantén el pie de apoyo estable.','sidestep'],
['jog','Trote suave en el lugar','Cardio','',1,'sec',false,'Trota con pasos cortos sobre una superficie despejada.|Aterriza suavemente con las rodillas flexibles.|Reduce a marcha si pierdes control o te falta el aire.','Evita golpes fuertes contra el suelo.','fastmarch'],
['jumpjack','Saltos con apertura de piernas','Cardio','',2,'sec',false,'Desde pies juntos, salta abriendo las piernas y elevando los brazos.|Aterriza con rodillas suaves.|Vuelve con otro salto pequeño.','Necesitas espacio, suelo estable y tolerancia a los saltos.','stepjack'],
['rope','Salto de cuerda simulado','Cardio','',2,'sec',false,'Simula el giro de una cuerda con las muñecas.|Da saltos pequeños con ambos pies.|Aterriza con suavidad y mantén una cadencia controlada.','Evita saltos altos y detente si molestan tobillos o rodillas.','fastmarch'],
['mountain','Escalador lento','Cardio','',2,'sec',false,'Colócate en posición de flexión con manos bajo hombros.|Acerca una rodilla al torso sin mover mucho la pelvis.|Vuelve y alterna lentamente.','No rebotes ni hundas la espalda.','kneeliftcardio'],
['squatstep','Sentadilla y paso lateral','Cardio','',1,'sec',false,'Realiza una sentadilla de recorrido cómodo.|Al subir, da un paso lateral.|Repite cambiando de lado.','Prioriza el control antes que la velocidad.','sidestep'],
['shadowcombo','Boxeo al aire con pasos','Cardio','',1,'sec',false,'Adopta una postura cómoda y da pasos cortos.|Alterna golpes suaves al aire con los brazos.|Desplázate sin cruzar los pies y respira continuamente.','Sin cargas en las manos y sin bloquear los codos.','box'],
['hipcircle','Círculos suaves de cadera','Movilidad','',0,'sec',false,'De pie junto a un apoyo estable, separa los pies.|Dibuja círculos pequeños con la pelvis.|Cambia de dirección lentamente.','No busques el máximo recorrido.','march'],
['chestopen','Apertura suave de pecho','Movilidad','',0,'sec',false,'De pie, coloca las manos cerca de las caderas.|Lleva suavemente los hombros hacia atrás.|Mantén una respiración tranquila sin arquear la espalda.','No fuerces los brazos detrás del cuerpo.','shoulder'],
['thoracic','Rotación torácica de lado','Movilidad','',0,'reps',true,'Túmbate de lado con rodillas flexionadas y brazos al frente.|Abre el brazo superior girando suavemente el pecho.|Vuelve despacio y cambia de lado.','Mantén juntas las rodillas; no fuerces el hombro al suelo.','shoulder'],
['hipflex','Estiramiento de flexores de cadera','Movilidad','',0,'sec',true,'Coloca una rodilla sobre una superficie acolchada y el otro pie delante.|Lleva suavemente la pelvis hacia delante sin arquear la espalda.|Mantén una tensión ligera y cambia de lado.','Evita dolor o presión fuerte sobre la rodilla apoyada.','hipcircle'],
['hamstretch','Estiramiento posterior de muslo','Movilidad','',0,'sec',true,'Sentado en una silla firme, extiende una pierna con el talón apoyado.|Inclina suavemente el torso desde la cadera.|Mantén una tensión ligera y cambia de pierna.','No redondees la espalda buscando llegar al pie.','ankle'],
['calfstretch','Estiramiento de pantorrilla en pared','Movilidad','',0,'sec',true,'Apoya las manos en una pared y lleva un pie atrás.|Mantén el talón trasero apoyado mientras flexionas la rodilla delantera.|Sostén suavemente y cambia de pierna.','El pie trasero apunta al frente.','ankle'],
['wrist','Movilidad de muñecas','Movilidad','',0,'sec',false,'Sentado o de pie, mantén los antebrazos cómodos.|Dibuja círculos pequeños con las muñecas.|Alterna con flexión y extensión suaves.','Sin carga sobre las manos durante este ejercicio.','shoulder'],
['neck','Movilidad suave de cuello','Movilidad','',0,'sec',false,'Siéntate erguido con los hombros relajados.|Gira la cabeza lentamente hacia un lado y vuelve al centro.|Repite al otro lado sin hacer círculos completos.','Interrumpe si aparece mareo o dolor.','shoulder'],
['breathing','Respiración tranquila de recuperación','Movilidad','',0,'sec',false,'Siéntate o túmbate en una posición cómoda.|Inhala de forma tranquila sin forzar la profundidad.|Exhala suavemente y continúa con un ritmo natural.','No retengas el aire ni fuerces respiraciones profundas.','march']
];
for(const r of rows){const [id,name,group,eq,level,unit,sides,steps,cue,alternative]=r;E.exercises.push({id,name,group,equipment:eq.includes('db')?'db':'body',level,unit,sides,steps:steps.replaceAll('|',' '),instructions:steps.split('|'),cue,alternative,category:group==='Cardio'?'Cardio':group==='Movilidad'?'Movilidad':'Fuerza',requirements:eq?eq.split(','):[],impact:['jog','jumpjack','rope'].includes(id)?'Con saltos':'Bajo'});}
const tips={Piernas:['Apoya todo el pie y deja que las rodillas sigan la dirección de las puntas.','Baja sin dejarte caer. Usa una profundidad que puedas controlar.'],Pecho:['Prepara una base estable antes de empezar.','Mantén las muñecas alineadas y controla la bajada.'],Cadera:['Inicia desde la cadera, no desde la zona lumbar.','Mantén la pelvis estable y evita los rebotes.'],Espalda:['Relaja el cuello y mantén el torso estable.','Mueve la carga sin balancearte ni encoger los hombros.'],Brazos:['Coloca los codos en una posición estable.','Evita impulsar la carga con el torso.'],Hombros:['Usa cargas ligeras y un recorrido cómodo.','Evita compensar arqueando la espalda.'],Core:['Activa suavemente el abdomen sin bloquear la respiración.','Termina la serie cuando dejes de controlar la postura.'],Cardio:['Despeja el suelo y comienza a un ritmo suave.','Baja el ritmo si no puedes hablar con comodidad.'],Movilidad:['Busca una tensión suave, no dolor.','Muévete despacio sin rebotes ni posiciones forzadas.']};
E.exercises.forEach(e=>{e.instructions=e.instructions||e.steps.split(/\.\s+/).filter(Boolean);e.setup=tips[e.group][0];e.control=tips[e.group][1];e.breath=e.unit==='sec'?'Respira de forma continua, sin retener el aire.':'Exhala en la fase de esfuerzo e inhala al regresar, sin retener el aire.';e.tempo=e.category==='Fuerza'?(e.unit==='sec'?'Mantén una postura estable durante el intervalo.':'Sube de forma controlada y baja lentamente, sin rebotes.'):'Ritmo cómodo y continuo; reduce la velocidad cuando lo necesites.';e.muscles=({Piernas:'Cuádriceps, glúteos o pantorrillas según el movimiento',Pecho:'Pectoral, tríceps y hombro anterior',Cadera:'Glúteos y musculatura posterior de la cadera',Espalda:'Dorsales y musculatura de la parte alta de la espalda',Brazos:'Bíceps, tríceps o antebrazo según el movimiento',Hombros:'Musculatura del hombro',Core:'Musculatura abdominal y estabilizadora del tronco',Cardio:'Actividad aeróbica general',Movilidad:'Movilidad y control del segmento indicado'})[e.group];});
const specs=[
['base','Cuerpo completo · inicio','Fuerza',0,2,8,['squat','wall','bridge','prone','deadbug'],'Aprende los patrones básicos sin mancuernas.'],
['full','Cuerpo completo · control','Fuerza',1,3,10,['squat','incline','hinge','reverse','bird'],'Una sesión general con control del movimiento.'],
['fullstrong','Cuerpo completo · avanzado','Fuerza',2,3,10,['split','pushup','singlebridge','reverse','shouldertap'],'Para quien ya domina estas variantes.'],
['dbbase','Mancuernas · fundamentos','Fuerza',0,2,8,['goblet','press','row','bridge','curl'],'Una base de fuerza con cargas ligeras.'],
['dbfull','Mancuernas · cuerpo completo','Fuerza',1,3,10,['goblet','neutral','row','rdl','deadbug'],'Cinco patrones con mancuernas.'],
['dbpower','Mancuernas · sesión avanzada','Fuerza',2,3,10,['dblunge','press','bentrow','rdl','suitcase'],'Fuerza general para personas con experiencia.'],
['upper','Tren superior sin mancuernas','Fuerza',1,2,10,['incline','prone','wallslide','kneepush','kneelingplank'],'Empuje y control del torso en casa.'],
['upperdb','Tren superior con mancuernas','Fuerza',1,3,10,['press','row','overheadpress','curl','tricep'],'Pecho, espalda, hombros y brazos.'],
['bench','Pecho · press de banca','Fuerza',1,3,10,['benchpress','neutral','kneepush','lyingtricep'],'Requiere un banco de entrenamiento estable.'],
['benchplus','Pecho · banco inclinado','Fuerza',2,3,10,['inclinebench','benchpress','squeezepress','tricep'],'Variantes de press con banco ajustable.'],
['chestfloor','Pecho · entrenamiento en suelo','Fuerza',1,3,10,['press','neutral','squeezepress','kneepush'],'Trabajo de pecho sin banco.'],
['push','Empuje sin mancuernas','Fuerza',1,2,10,['wall','kneepush','incline','wallslide'],'Varía la inclinación según tu nivel.'],
['pushdb','Empuje con mancuernas','Fuerza',1,3,10,['press','overheadpress','lateral','lyingtricep'],'Pecho, hombros y tríceps.'],
['pull','Espalda y bíceps','Fuerza',1,3,10,['row','bentrow','reversefly','curl'],'Sesión de tracción con mancuernas.'],
['back','Espalda · control sin cargas','Fuerza',0,2,10,['prone','reverse','scapular','bird'],'Control escapular y del tronco.'],
['shoulders','Hombros con mancuernas','Fuerza',1,2,10,['overheadpress','lateral','reversefly','wallslide'],'Usa cargas ligeras y recorrido cómodo.'],
['arms','Brazos con mancuernas','Fuerza',1,3,10,['curl','hammer','tricep','lyingtricep'],'Bíceps y tríceps con control.'],
['armsbase','Brazos · iniciación','Fuerza',0,2,8,['seatedcurl','hammer','wall'],'Una sesión corta y accesible.'],
['legs','Piernas sin mancuernas','Fuerza',1,3,10,['squat','reverselunge','bridge','calf'],'Sentadilla, zancada y extensión de cadera.'],
['legsbase','Piernas · fundamentos','Fuerza',0,2,8,['squat','bridge','calf','sideleg'],'Trabajo básico del tren inferior.'],
['legsdb','Piernas con mancuernas','Fuerza',1,3,10,['goblet','rdl','dbsumo','dbcalf'],'Cargas moderadas y movimientos controlados.'],
['legsadvanced','Piernas · unilateral','Fuerza',2,3,10,['dblunge','split','singlebridge','singlecalf'],'Trabajo por lado para usuarios con experiencia.'],
['glutes','Glúteos sin mancuernas','Fuerza',0,2,10,['bridge','sideleg','clam','kickback'],'Control de cadera con el propio cuerpo.'],
['glutesdb','Glúteos con mancuernas','Fuerza',1,3,10,['dbbridge','rdl','goblet','sideleg'],'Extensión y control de cadera.'],
['posterior','Cadena posterior','Fuerza',1,3,10,['rdl','bridge','row','bird'],'Cadera, espalda y estabilidad.'],
['corebase','Abdominales · fundamentos','Fuerza',0,2,8,['heel','deadbug','bird','kneelingplank'],'Control abdominal sin buscar el agotamiento.'],
['core','Abdominales · control','Fuerza',1,2,10,['deadbug','sideplank','curlup','bird'],'Estabilidad y movimiento controlado.'],
['coreadvanced','Abdominales · avanzado','Fuerza',2,3,10,['plank','sideplank','shouldertap','reversecrunch'],'Requiere dominio de planchas y apoyo de brazos.'],
['quickstrength','Fuerza exprés','Fuerza',0,1,8,['squat','wall','bridge','prone'],'Pocas series para un día con poco tiempo.'],
['quickdb','Mancuernas exprés','Fuerza',1,1,10,['goblet','press','row','rdl'],'Cuatro ejercicios básicos con cargas.'],
['cardiostart','Cardio · primeros pasos','Cardio',0,2,40,['march','sidestep','box','heelback'],'Intervalos suaves sin saltos.'],
['lowimpact','Cardio sin saltos','Cardio',0,3,40,['fastmarch','stepjack','sidestep','kneeliftcardio'],'Movimiento continuo de bajo impacto.'],
['silent','Cardio silencioso','Cardio',0,3,40,['sidestep','box','heelback','walk'],'Sin saltos para espacios compartidos.'],
['cardiobox','Cardio · boxeo al aire','Cardio',1,3,40,['box','shadowcombo','fastmarch','sidestep'],'Golpes al aire sin cargas y pasos controlados.'],
['intervals','Cardio por intervalos','Cardio',1,3,40,['fastmarch','skater','squatstep','stepjack'],'Alterna intervalos y pausas; ajusta el ritmo.'],
['cardiojump','Cardio con saltos','Cardio',2,3,30,['jog','jumpjack','rope','fastmarch'],'Solo si toleras saltos y tienes espacio.'],
['cardioadvanced','Cardio · desafío controlado','Cardio',2,3,30,['jog','mountain','shadowcombo','jumpjack'],'Intervalos exigentes sin buscar esfuerzo máximo.'],
['walk10','Caminata en casa','Cardio',0,1,180,['walk','fastmarch','walk'],'Camina a tu ritmo con pausas cuando las necesites.'],
['cardioquick','Cardio exprés sin saltos','Cardio',0,1,45,['fastmarch','sidestep','box','stepjack'],'Una pausa activa breve.'],
['mix','Fuerza y cardio sin mancuernas','Mixta',1,2,10,['squat','incline','fastmarch','bridge','box'],'Alterna fuerza y movimiento aeróbico.'],
['mixdb','Fuerza y cardio con mancuernas','Mixta',1,2,10,['goblet','press','row','fastmarch','stepjack'],'Deja las cargas en el suelo durante el cardio.'],
['mobility','Movilidad general','Movilidad',0,1,30,['shoulder','hipcircle','ankle','thoracic','wrist'],'Recorrido suave para todo el cuerpo.'],
['desk','Pausa activa de escritorio','Movilidad',0,1,30,['neck','shoulder','wrist','ankle','march'],'Una pausa breve entre periodos sentado.'],
['morning','Activación de mañana','Movilidad',0,1,30,['march','shoulder','hipcircle','ankle','wallslide'],'Empieza con movimiento cómodo.'],
['recovery','Recuperación suave','Movilidad',0,1,30,['walk','shoulder','hipcircle','breathing'],'Una sesión ligera entre días de fuerza.'],
['stretchlegs','Movilidad de piernas','Movilidad',0,1,30,['hipcircle','hipflex','hamstretch','calfstretch','ankle'],'Movilidad y estiramientos sin dolor.'],
['stretchupper','Movilidad de tren superior','Movilidad',0,1,30,['shoulder','chestopen','thoracic','wrist','neck'],'Movilidad suave de torso y brazos.'],
['warmup','Calentamiento general','Movilidad',0,1,40,['march','shoulder','hipcircle','ankle','sidestep'],'Preparación breve antes de una sesión.']
];
const routines=specs.map(([id,name,category,level,sets,target,ids,description])=>{
 const items=ids.map(id=>{const e=E.byId(id);return {id,sets:e.category==='Movilidad'?1:sets,target:e.unit==='sec'?(e.category==='Cardio'?(category==='Cardio'?target:40):e.category==='Movilidad'?target:20):(category==='Movilidad'?8:target),rest:e.category==='Cardio'?30:category==='Movilidad'?15:90};});
 let seconds=items.reduce((n,x)=>{const e=E.byId(x.id),side=e.sides?2:1;return n+x.sets*(x.target*(e.unit==='sec'?1:4)*side+x.rest);},0);
 return {id,name,category,level,items,description,minutes:Math.round(seconds/60)+(category==='Movilidad'?0:5),requirements:[...new Set(ids.flatMap(id=>E.byId(id).requirements))],impact:ids.some(id=>E.byId(id).impact==='Con saltos')?'Con saltos':'Sin saltos'};
});
root.Catalog={routines,levels:['Inicial','Intermedio','Avanzado'],equipment:{db:'Mancuernas',bench:'Banco de entrenamiento',step:'Plataforma de ejercicio'},available(r,p){return r.requirements.every(x=>x==='db'?p?.equipment==='db':!!p?.[x]);}};
})(typeof window!=='undefined'?window:globalThis);

;
/* 124 movimientos base y 248 variantes explicadas; 192 plantillas de sesión. */
(function(root){const E=root.Engine,C=root.Catalog;
const extra=[
['seatedpunch','Boxeo al aire sentado','Cardio','',0,'sec',false,'Siéntate en una silla firme con la espalda cómoda y los pies apoyados.|Alterna golpes cortos al frente sin bloquear los codos.|Mantén el torso estable y vuelve las manos hacia el pecho.','No uses cargas ni golpes explosivos.','shoulder,elbow,wrist,back'],
['seatedmarch','Marcha alterna sentado','Cardio','',0,'sec',false,'Siéntate hacia el centro de una silla estable y apoya los pies.|Eleva un pie unos centímetros y vuelve a apoyarlo.|Alterna lentamente sin inclinarte hacia atrás.','Evita una elevación que cause molestias en la cadera.','hip,knee,back'],
['seatedstep','Pasos laterales sentado','Cardio','',0,'sec',false,'Sentado en una silla fija, apoya ambos pies.|Mueve un pie hacia un lado y devuélvelo al centro.|Alterna pies sin girar sobre la rodilla apoyada.','Haz pasos cortos; no deslices sobre un suelo resbaladizo.','hip,knee,ankle'],
['heeltoe','Apoyo alterno de talón y punta sentado','Movilidad','',0,'reps',false,'Siéntate con ambos pies apoyados.|Eleva las puntas dejando los talones en el suelo.|Apoya las puntas y eleva suavemente los talones.','Una secuencia completa cuenta una repetición.','ankle'],
['legextend','Extensión de rodilla sentado','Piernas','',0,'reps',true,'Siéntate con el muslo apoyado y una rodilla flexionada.|Extiende lentamente la rodilla sin añadir peso.|Regresa y repite con la otra pierna.','No bloquees bruscamente la articulación ni fuerces el recorrido.','knee,hip'],
['seatedhip','Separación de rodillas sentado','Cadera','',0,'reps',false,'Siéntate erguido con pies apoyados y rodillas separadas a una distancia cómoda.|Separa ligeramente las rodillas manteniendo los pies apoyados.|Regresa al centro con control.','Sin bandas ni presión externa; no fuerces la apertura.','hip,knee'],
['toelift','Elevación de puntas sentado','Piernas','',0,'reps',false,'Siéntate con los talones apoyados.|Eleva lentamente las puntas de los pies.|Bájalas con control.','Mantén las rodillas quietas y un recorrido cómodo.','ankle'],
['seatedheel','Elevación alterna de talones sentado','Piernas','',0,'reps',false,'Sentado, deja las puntas de ambos pies apoyadas.|Eleva un talón y bájalo.|Alterna lados sin rebotes.','Cada elevación cuenta una repetición.','ankle'],
['seatedside','Inclinación lateral suave sentado','Movilidad','',0,'reps',false,'Siéntate erguido con las manos sobre los muslos.|Inclina ligeramente el torso hacia un lado sin girarlo.|Vuelve al centro y alterna.','Usa un recorrido pequeño y sin dolor.','back,hip'],
['seatedturn','Giro suave de torso sentado','Movilidad','',0,'reps',false,'Siéntate con la pelvis estable y los pies apoyados.|Gira suavemente el pecho hacia un lado.|Regresa al centro y alterna sin tirar de la silla.','No fuerces la columna ni el cuello.','back,neck'],
['seatedreach','Alcance frontal alterno sentado','Movilidad','',0,'reps',false,'Siéntate con el torso erguido.|Extiende un brazo al frente sin elevar el hombro.|Recógelo y alterna, sin inclinar el cuerpo.','Cada alcance cuenta una repetición.','shoulder,elbow,back'],
['elbowmob','Flexión de codos sin carga','Movilidad','',0,'reps',false,'Siéntate con los brazos al costado y las manos relajadas.|Flexiona los codos acercando las manos a los hombros.|Extiende de nuevo lentamente.','No fuerces el final del movimiento.','elbow'],
['forearmmob','Rotación de antebrazos sentado','Movilidad','',0,'reps',false,'Siéntate con los codos flexionados y apoyados junto al cuerpo.|Gira las palmas hacia arriba y después hacia abajo.|Alterna de forma suave.','No añadas carga ni gires desde el hombro.','elbow,wrist'],
['wristmob','Flexión y extensión de muñecas sentado','Movilidad','',0,'reps',false,'Apoya los antebrazos sobre los muslos dejando las manos libres.|Mueve lentamente las manos arriba y abajo.|Regresa a una posición neutra.','Mantén los dedos relajados y no fuerces el recorrido.','wrist'],
['fingers','Apertura y cierre suave de manos','Movilidad','',0,'reps',false,'Sentado, apoya los antebrazos.|Abre suavemente los dedos.|Cierra las manos sin apretar con fuerza y repite.','No uses pelotas ni resistencia adicional.','wrist'],
['wallcalf','Elevación de talones con apoyo en pared','Piernas','',0,'reps',false,'De pie frente a una pared, apoya las manos con suavidad.|Eleva los talones sin perder el equilibrio.|Baja lentamente manteniendo ambos pies paralelos.','La pared ayuda al equilibrio; no empujes con los brazos.','ankle,knee,hip,wrist,shoulder'],
['standingabduct','Abducción de cadera de pie con apoyo','Cadera','',0,'reps',true,'Colócate junto a una pared y apoya una mano.|Separa una pierna ligeramente hacia un lado sin inclinar el torso.|Vuelve y completa el otro lado.','Evita girar la pelvis o balancearte.','hip,knee,ankle,back,shoulder,wrist'],
['standingextend','Extensión de cadera de pie con apoyo','Cadera','',0,'reps',true,'Sujeta un apoyo fijo y mantén el torso erguido.|Lleva una pierna ligeramente hacia atrás.|Regresa despacio y cambia de lado.','No arquees la espalda para ampliar el recorrido.','hip,knee,ankle,back,shoulder,wrist'],
['standingcurl','Flexión de rodilla de pie con apoyo','Piernas','',0,'reps',true,'Colócate junto a una pared o apoyo fijo.|Flexiona una rodilla llevando el talón hacia atrás.|Baja con control y cambia de pierna.','Mantén los muslos alineados y evita balancear el cuerpo.','knee,hip,ankle,shoulder,wrist'],
['tandem','Equilibrio con pies en línea y apoyo','Movilidad','',0,'sec',false,'Colócate junto a una pared y pon un pie delante del otro con una separación cómoda.|Mantén una mano disponible sobre el apoyo.|Sostén la postura sin cerrar los ojos y cambia el pie delantero.','Evita hacerlo sin apoyo si te sientes inestable.','ankle,knee,hip,back,shoulder,wrist'],
['balance','Equilibrio a una pierna con apoyo','Movilidad','',1,'sec',true,'Sujeta un apoyo fijo y levanta un pie unos centímetros.|Mantén la postura mirando al frente.|Vuelve a apoyar y cambia de pierna.','No retires el apoyo ni cierres los ojos.','ankle,knee,hip,back,shoulder,wrist'],
['linewalk','Caminata de pasos cortos junto a pared','Movilidad','',0,'sec',false,'Sitúate al lado de una pared con el recorrido despejado.|Avanza con pasos cortos y una mano cerca del apoyo.|Gira con varios pasos y vuelve.','No cruces los pies ni camines hacia atrás.','ankle,knee,hip,back'],
['doublefront','Sentadilla con dos mancuernas al pecho','Piernas','db',2,'reps',false,'Sujeta dos mancuernas ligeras junto al pecho, con muñecas cómodas.|Flexiona caderas y rodillas manteniendo los pies apoyados.|Sube sin rebotes y sin inclinarte hacia atrás.','Domina primero la sentadilla sin carga.','ankle,knee,hip,back,shoulder,elbow,wrist'],
['suitdead','Peso muerto con mancuernas a los lados','Cadera','db',1,'reps',false,'Sujeta una mancuerna a cada lado con los pies estables.|Lleva la cadera atrás y flexiona un poco las rodillas.|Sube manteniendo las cargas junto al cuerpo.','No redondees la espalda ni busques tocar el suelo.','ankle,knee,hip,back,shoulder,wrist'],
['dbsplit','Sentadilla dividida con mancuernas','Piernas','db',2,'reps',true,'Sujeta cargas a los lados y separa los pies adelante y atrás.|Flexiona ambas rodillas sin mover los pies.|Sube con control y cambia la pierna delantera.','Usa primero la variante sin cargas si pierdes estabilidad.','ankle,knee,hip,back,shoulder,wrist'],
['seatedpress','Press de hombros sentado','Hombros','db',1,'reps',false,'Siéntate con apoyo firme y mancuernas ligeras junto a los hombros.|Empuja hacia arriba hasta un rango cómodo.|Baja lentamente manteniendo la espalda sin arquear.','No uses una silla inestable ni subas con impulso.','shoulder,elbow,wrist,back'],
['seatedlateral','Elevación lateral sentado','Hombros','db',1,'reps',false,'Siéntate erguido con cargas ligeras a los lados.|Eleva los brazos lateralmente hasta una altura cómoda.|Baja lentamente sin encoger los hombros.','Evita balancear el torso.','shoulder,elbow,wrist,back'],
['seatedfly','Apertura posterior sentado','Espalda','db',1,'reps',false,'Siéntate y realiza una pequeña inclinación desde las caderas.|Abre los brazos con mancuernas ligeras.|Baja con control manteniendo estable la espalda.','No redondees la columna ni eleves las cargas con impulso.','shoulder,elbow,wrist,back,hip'],
['curlhold','Sujeción de bíceps a medio recorrido','Brazos','db',1,'sec',false,'Sujeta cargas ligeras con los codos junto al torso.|Flexiona los codos hasta una posición intermedia cómoda.|Mantén respirando y baja al terminar.','No aprietes con fuerza excesiva ni sostengas hasta perder el control.','elbow,wrist,shoulder,back'],
['farmerhold','Sujeción bilateral de mancuernas','Core','db',1,'sec',false,'De pie, sostiene una mancuerna a cada lado.|Mantén el torso erguido y los hombros relajados.|Respira y deja las cargas en el suelo con control.','No eleves los hombros ni arquees la espalda.','ankle,knee,hip,back,shoulder,wrist'],
['wallplank','Plancha de pie en pared','Core','',0,'sec',false,'Apoya los antebrazos en una pared y aleja ligeramente los pies.|Mantén el cuerpo alineado con el abdomen suavemente activo.|Respira y termina antes de perder la postura.','No dejes que la espalda se hunda.','shoulder,elbow,back,hip,ankle'],
['supinearms','Elevación de brazos tumbado','Movilidad','',0,'reps',false,'Túmbate boca arriba con rodillas en una posición cómoda.|Lleva los brazos hacia arriba y un poco atrás dentro de un rango cómodo.|Regresa despacio sin despegar las costillas.','No fuerces los brazos hasta el suelo ni arquees la espalda.','shoulder,elbow,back']
];
for(const [id,name,group,eq,level,unit,sides,steps,cue,z] of extra){E.exercises.push({id,name,group,equipment:eq?'db':'body',level,unit,sides,steps:steps.replaceAll('|',' '),instructions:steps.split('|'),cue,category:group==='Cardio'?'Cardio':group==='Movilidad'?'Movilidad':'Fuerza',requirements:eq?['db']:[],impact:'Bajo',setup:'Despeja el espacio y comprueba que los apoyos sean firmes.',control:'Usa un recorrido que puedas controlar y detente si aparece dolor.',breath:'Respira de forma continua; no retengas el aire.',tempo:unit==='sec'?'Mantén un ritmo cómodo durante el intervalo.':'Realiza cada repetición despacio y sin rebotes.',zones:z.split(',')});}
const floor=new Set(['press','neutral','bridge','prone','reverse','deadbug','bird','plank','heel','sideleg','cat','kneepush','pushup','closepush','squeezepress','singlebridge','dbbridge','kickback','clam','pullover','shouldertap','lyingtricep','sideplank','kneelingplank','reversecrunch','curlup','mountain','thoracic','hipflex','supinearms']);
const seated=new Set(['seatedpunch','seatedmarch','seatedstep','heeltoe','legextend','seatedhip','toelift','seatedheel','seatedside','seatedturn','seatedreach','elbowmob','forearmmob','wristmob','fingers','seatedpress','seatedlateral','seatedfly','seatedcurl','concentration','seatedcalf','ankle','hamstretch']);
const upperZones=['shoulder','elbow','wrist','back'];
for(const e of E.exercises){e.baseId=e.id;e.variant='base';e.floor=floor.has(e.id);e.seated=seated.has(e.id);e.overhead=['overheadpress','seatedpress','inclinebench','wallslide','pullover','jumpjack','stepjack','supinearms'].includes(e.id);e.wristLoad=['wall','incline','pushup','closepush','kneepush','bird','cat','shouldertap','mountain','kickback'].includes(e.id);e.kneeBend=['chair','squat','goblet','sumo','dbsumo','reverselunge','dblunge','split','dbsplit','lateralunge','stepup','wallsit','squatstep','doublefront','seatedmarch','legextend','seatedhip','standingcurl','heelback','kneeliftcardio'].includes(e.id);
if(!e.zones)e.zones=({Piernas:['ankle','knee','hip','back'],Cadera:['hip','knee','back'],Pecho:upperZones,Espalda:['back','shoulder','elbow','wrist'],Brazos:upperZones,Hombros:upperZones,Core:['back','hip','shoulder','wrist'],Cardio:['ankle','knee','hip','back','shoulder','elbow','wrist'],Movilidad:['back','neck','shoulder','elbow','wrist','hip','knee','ankle']})[e.group].slice();
if(e.id==='ankle')e.zones=['ankle'];if(e.id==='wrist')e.zones=['wrist'];if(e.id==='neck')e.zones=['neck'];if(e.id==='shoulder')e.zones=['shoulder','neck'];if(e.id==='breathing')e.zones=['back'];if(e.id==='seatedcurl')e.zones=upperZones.slice();e.spineMove=['hinge','rdl','bentrow','reversefly','seatedfly','cat','thoracic','seatedside','seatedturn','curlup','reversecrunch','singlehinge','sumodead','suitdead'].includes(e.id);}
const bases=E.exercises.slice();for(const e of bases){const control=e.unit==='reps';for(const [suffix,label,instruction,tempo] of control?[
['slow','retorno lento','En cada repetición, dedica unos tres segundos a la fase de retorno. Reduce las repeticiones o la carga si no puedes mantener ese control.','Retorno de aproximadamente tres segundos; esfuerzo controlado.'],
['pause','pausa de control','Añade una pausa breve de aproximadamente un segundo en una posición intermedia cómoda, sin bloquear las articulaciones. No aumentes la carga para esta variante.','Pausa breve en un punto estable; movimiento sin rebotes.']
]:[
['short','intervalos breves','Divide el trabajo en intervalos breves. Empieza con 15 a 20 segundos y descansa antes de repetir; registra únicamente los segundos activos.','Intervalos cortos con descanso entre ellos.'],
['easy','ritmo suave','Realiza el movimiento a un ritmo deliberadamente suave, con un recorrido cómodo. Reduce la duración cuando lo necesites.','Cadencia suave y respiración continua, sin buscar esfuerzo máximo.']
]){E.exercises.push({...e,id:e.id+'_'+suffix,name:e.name+' · '+label,variant:suffix,variantDescription:instruction,instructions:[...e.instructions,instruction],tempo,baseId:e.id,alternative:e.id});}}
const newSpecs=[
['seatcardio','Cardio sentado · brazos','Cardio',['seatedpunch','seatedreach','elbowmob'],1,30],['seatmix','Cardio sentado · coordinación','Cardio',['seatedmarch','seatedpunch','seatedstep'],2,30],['seatmob','Movilidad sentado','Movilidad',['elbowmob','forearmmob','wristmob','heeltoe'],1,8],['seatupper','Tren superior sentado con mancuernas','Fuerza',['seatedcurl','seatedpress','seatedlateral'],2,8],['seatarm','Brazos sentado · básico','Fuerza',['seatedcurl','elbowmob','forearmmob'],2,8],['nofloor','Cuerpo completo sin suelo','Fuerza',['squat','wall','calf','scapular'],2,8],['nofloordb','Mancuernas sin suelo','Fuerza',['goblet','row','hammer','dbcalf'],2,8],['balancebase','Equilibrio junto a un apoyo','Movilidad',['tandem','linewalk','ankle'],1,20],['hipstanding','Cadera de pie con apoyo','Fuerza',['standingabduct','standingextend','standingcurl'],2,8],['anklemob','Movilidad de tobillos sentado','Movilidad',['heeltoe','toelift','seatedheel','ankle'],1,8],['handmob','Movilidad de manos y antebrazos','Movilidad',['fingers','wristmob','forearmmob','elbowmob'],1,8],['benchfull','Cuerpo completo con banco','Fuerza',['goblet','benchpress','row','rdl'],2,8],['minimal','Sesión básica de tres movimientos','Fuerza',['squat','wall','bridge'],1,8],['seatedbreak','Pausa sentada sin mancuernas','Movilidad',['seatedreach','elbowmob','fingers'],1,8],['standingcore','Control del tronco sin suelo','Fuerza',['wallplank','standingcore','scapular'],2,8],['carriagedb','Agarre y brazos con mancuernas','Fuerza',['farmerhold','hammer','curlhold'],2,8]
];
function duration(items){return Math.ceil(items.reduce((n,x)=>n+x.sets*(x.target*(E.unitOf(x)==='sec'?1:4)*(E.sidesOf(x)?2:1)+(x.rest||60)),0)/60)+3;}
function decorate(r){return {...r,minutes:duration(r.items),requirements:[...new Set(r.items.flatMap(x=>E.byId(x.id).requirements))],level:Math.max(...r.items.map(x=>E.byId(x.id).level)),impact:r.items.some(x=>E.byId(x.id).impact==='Con saltos')?'Con saltos':'Sin saltos'};}
for(const [id,name,category,ids,sets,target] of newSpecs){C.routines.push(decorate({id,name,category,items:ids.map(id=>({id,sets,target:E.byId(id).unit==='sec'?30:target,rest:category==='Fuerza'?90:30})),description:'Plantilla general para revisar y ajustar a tu experiencia y restricciones.'}));}
const rBase=C.routines.slice();C.baseRoutines=rBase.length;for(const r of rBase){r.baseRoutineId=r.id;r.variant='base';for(const v of ['brief','practice']){const brief=v==='brief',items=r.items.map(x=>({...x,id:!brief&&E.byId(x.id+'_slow')?x.id+'_slow':x.id,sets:brief?1:Math.min(2,x.sets),target:E.unitOf(x)==='sec'?(brief?Math.min(25,x.target):Math.min(40,x.target)):(brief?Math.min(6,x.target):Math.min(8,x.target))}));C.routines.push(decorate({...r,id:r.id+'_'+v,baseRoutineId:r.id,variant:v,name:r.name+(brief?' · breve':' · práctica controlada'),description:brief?'Una serie por movimiento y objetivos más cortos. Variante de menor volumen de la plantilla base.':'Hasta dos series por movimiento. Se usa retorno lento cuando esa variante está disponible.',items}));}}
C.baseExercises=bases.length;C.duration=duration;
})(typeof window!=='undefined'?window:globalThis);

