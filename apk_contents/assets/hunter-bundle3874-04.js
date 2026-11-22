/* Cantidades explícitas de subtítulos automáticos contrastadas con capítulos. */
(function(root){
 const ids=['pau-altpress','pau-overtriceps','pau-arnold','pau-lateralcurl','hammer','pau-curlcombo','pau-raisecombo','pau-standingfly','bentrow','pau-presscombo','pau-kickback','pau-plankup','plank','pau-pushchild'];
 const steps=[{kind:'preparation',label:'Preparar el circuito: 14 movimientos de 30 s y descansos de 10 s',seconds:10,sourceAt:62}];
 ids.forEach((exerciseId,i)=>{steps.push({kind:'exercise',exerciseId,sets:1,target:30,unit:'sec',restSeconds:0,sourceAt:72+i*40});if(i<ids.length-1)steps.push({kind:'rest',label:'Descanso',seconds:10,sourceAt:102+i*40});});
 root.SourceSequences12=[{videoId:'QdiX-1poVO8',channelId:'UCQ_N_uuyHbTE8qZ_GRIJmKw',name:'Circuito con mancuernas para brazos y espalda · Pau Fit',verified:true,evidenceType:'captions-and-chapters',visualVerified:false,verificationNote:'Secuencia textual contrastada con capítulos y subtítulos automáticos: 14 movimientos de 30 s, 13 descansos de 10 s y preparación de 10 s. La entrenadora declara usar dos mancuernas de 2,5 kg; es una referencia, no tu carga asignada. Revisión visual del video pendiente; los subtítulos automáticos pueden contener errores. Las demostraciones 2D son esquemas propios.',steps}];
})(typeof window!=='undefined'?window:globalThis);

;
/* Hechos de capítulos oficiales contrastados con subtítulos. No es revisión audiovisual. */
(function(root){
'use strict';
const blocks=[
 ['pau-armworld',58,35],['pau-armcircle-f',98,35],['pau-armcircle-b',133,35],
 ['pau-armupdown',178,35],['pau-armpulses',223,35],['pau-armfly',268,35],
 ['pau-armsback',313,35],['shouldertap',358,35],['pau-armraise',403,35,'right'],
 ['pau-armraise',448,35,'left'],['pau-pushchild',493,35],['pau-armcurl',538,35]
];
const steps=[];for(const [i,b] of blocks.entries()){steps.push({kind:'exercise',exerciseId:b[0],sets:1,target:b[2],unit:'sec',restSeconds:0,sourceAt:b[1],...(b[3]?{side:b[3]}:{})});const next=blocks[i+1];if(next&&next[1]>b[1]+b[2])steps.push({kind:'rest',label:'Descanso',seconds:next[1]-b[1]-b[2],sourceAt:b[1]+b[2]});}
root.SourceSequences13=[{videoId:'EDtz3KvMxqc',channelId:'UCQ_N_uuyHbTE8qZ_GRIJmKw',name:'Circuito de brazos y espalda sin equipo · Pau Fit',verified:true,evidenceType:'captions-and-chapters',visualVerified:false,verificationNote:'Capítulos oficiales y subtítulos: 12 movimientos de 35 s; primera pausa de 5 s y nueve de 10 s. Los dos círculos de brazos van seguidos. Las marcas van de 0:58 a 9:33; los tiempos de la transcripción no coinciden con todos los capítulos. Revisión visual del video pendiente. Demostraciones 2.5D propias; no se incluyen clips del entrenador.',steps}];
})(window);

;
/* Secuencia literal de bloques. Un circuito se representa repitiendo bloques en su orden. */
(function(root){
'use strict';
function expand(day){
 let itemIndex=0;
 const out=[],source=day.sourceSequence;
 const blocks=source?.steps||day.items.map(x=>({kind:'exercise',exerciseId:x.id,sets:x.sets,target:x.target,unit:Engine.unitOf(x),side:x.side,restSeconds:x.rest||0}));
 for(const [blockIndex,b] of blocks.entries()){
  if(b.kind!=='exercise'){out.push({kind:b.kind,label:b.label,seconds:b.seconds,blockIndex});continue;}
  const e=Engine.byId(b.exerciseId);if(!e)throw Error('Ejercicio desconocido');
  for(let j=0;j<b.sets;j++){
   const unit=b.unit||e.unit;out.push({kind:'exercise',id:e.id,label:e.name,target:b.target,unit,...(b.side?{side:b.side}:{}),seconds:unit==='sec'?b.target:0,itemIndex,setIndex:j,sets:b.sets,blockIndex});
   if(b.restSeconds>0&&(j<b.sets-1||!source&&blockIndex<blocks.length-1))out.push({kind:'rest',label:'Descanso',seconds:b.restSeconds,blockIndex});
  }
  itemIndex++;
 }
 return out;
}
function signature(steps){let n=2166136261;for(const c of JSON.stringify(steps)){n^=c.charCodeAt(0);n=Math.imul(n,16777619);}return (n>>>0).toString(16).padStart(8,'0');}
function compatible(day,p){return Adapt.mode(p).auto&&day.items.every(x=>Adapt.allowed(Engine.byId(x.id),p));}
root.RoutineSequence={expand,signature,compatible};
})(typeof window!=='undefined'?window:globalThis);

;
/* Selecciona sesiones completas por objetivo, equipo, nivel y restricciones; no inventa fuentes. */
(function(root){
'use strict';
function category(d){return d.kind==='cardio'?'Cardio':d.kind==='recovery'?'Movilidad':'Fuerza';}
function fitness(r,p,d,logs,now){
 if(r.category!==category(d)&&!(category(d)==='Fuerza'&&r.category==='Mixta'))return null;
 if(!Adapt.routineCompatible(r,p))return null;
 if(r.planVersion===14&&(r.level!==['basic','intermediate','advanced'].indexOf(p.difficulty)||!r.goalGroups.includes(GoalGroups14.of(p))))return null;
 const groups=r.items.map(x=>Engine.byId(x.id).group),focus=GoalPlans.plans[p.finalTarget]?.focus||p.focus;
 const target=focus==='upper'?['Pecho','Espalda','Hombros','Brazos']:focus==='lower'?['Piernas','Cadera']:focus==='core'?['Core']:[];
 if(target.length&&!groups.some(g=>target.includes(g)))return null;
 const prediction=Learning.predict(Learning.samples(logs,now),r.items);
 if(prediction&&prediction.effort>=8)return null;
 return target.length?groups.filter(g=>target.includes(g)).length/groups.length:1;
}
function organize(c,p,sourceRoutines,ownRoutines,logs,now){
 const mode=Adapt.mode(p);let count=0;
 if(p.trainingMode!=='routines')return count;
 if(!mode.auto||mode.reduced||GoalPlans.plans[p.finalTarget]?.gentle||['reducir','pausar'].includes(c.reviews?.at(-1)?.action)){
  for(const d of c.days)if(d.items.length)d.planningNote='Rutina adaptada con dosis reducida según tus restricciones y registros.';
  return count;
 }
 const preferred=CreatorFeed.channels.find(x=>x.key===p.preferredTrainer);
 const sources=sourceRoutines.filter(CreatorFeed.validateRoutine).filter(r=>!preferred||r.channelId===preferred.channelId).map(CreatorFeed.asRoutine);
 for(const [i,d] of c.days.entries()){
  if(d.date<now||!d.items.length||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;
  let list=p.preferredTrainer==='own'?[]:sources.map(r=>({r,score:fitness(r,p,d,logs,now)})).filter(x=>x.score!==null).sort((a,b)=>b.score-a.score);
  let original=list.length>0;
  if(!list.length)list=ownRoutines.filter(r=>!r.locked).map(r=>({r,score:fitness(r,p,d,logs,now)})).filter(x=>x.score!==null).sort((a,b)=>(b.r.planVersion===14?1:0)-(a.r.planVersion===14?1:0)||b.score-a.score||a.r.level-b.r.level);
  if(!list.length){d.planningNote='Se mantiene la rutina adaptada: no hay una secuencia completa compatible en la fuente elegida.';continue;}
  const r=list[(c.index+i)%Math.min(5,list.length)].r;
  d.items=r.planVersion===14?OwnRoutines14.adjust(r,p):r.items.map(x=>original?{...x}:{...x,...TrainingIntensity.dose(Engine.byId(x.id),p,d.kind)});
  if(!original&&TrainingIntensity.scale(p)===1)for(const x of d.items){const next=AdaptiveV9.recommend(logs,x,p,now);if(next.target>x.target)x.target=next.target;}
  d.kind=r.category==='Mixta'?'mixed':d.kind;d.title=r.name;d.tasks=[];d.autoTemplateId=r.id;
  if(original){d.routineId=r.id;d.sourceSequence=JSON.parse(JSON.stringify(r.sourceSequence));d.creatorAssigned=true;}
  d.planningNote=original?'Secuencia de la fuente elegida, sin cambiar sus bloques ni cantidades.':p.preferredTrainer==='own'?'Rutina del catálogo ajustada al nivel y al objetivo.':'No hay una secuencia completa comprobada y compatible del entrenador elegido. Se usa una rutina propia adaptada.';
  delete logs[d.date];count++;
 }
 return count;
}
root.RoutinePlanner={organize,fitness};
})(typeof window!=='undefined'?window:globalThis);

;
/* Programas originales: nueve materias, tres niveles y tres extensiones.
 * Las dosis son decisiones de diseño, no transcripciones ni ensayos clínicos. */
(function(root){'use strict';
const families={
 strength:{name:'Fuerza',category:'Fuerza',goals:['build','fitness','athletic','habit'],body:['squat','kneepush','bridge','prone','deadbug','split','pushup'],db:['goblet','press','row','rdl','curl','dbsplit','overheadpress']},
 hypertrophy:{name:'Musculatura',category:'Fuerza',goals:['build'],body:['squathold','kneepush','bridgehold','scapular','curlup','singlebridge','closepush'],db:['goblethold','neutral','bentrow','dbbridge','hammer','lateral','lyingtricep']},
 core:{name:'Zona media',category:'Fuerza',goals:['build','movement','habit'],body:['deadbug','bird','kneelingplank','bridge','sideplank','plank','bearhold'],db:['suitcase','farmerhold','dbbridge','singlehinge','farmerwalk','dbmarch']},
 cardio:{name:'Cardio',category:'Cardio',goals:['fitness','habit','athletic'],body:['fastmarch','stepjack','boxinterval','kneeliftcardio','shadowcombo','jog','jumpjack']},
 agility:{name:'Agilidad y coordinación',category:'Cardio',goals:['athletic','fitness'],body:['fastfeet','shuffle','stepcross','skaterreach','frontbackstep','doubletouch','highknees']},
 power:{name:'Saltos y potencia',category:'Cardio',goals:['athletic'],body:['lowhop','linehop','calf','squat','skaterhop','squatjump']},
 mobility:{name:'Movilidad',category:'Movilidad',goals:['movement','habit','adapted'],body:['ankledrive','hipcircle','shoulder','wallslide','hipflex','thoracic','wrist']},
 balance:{name:'Equilibrio y estabilidad',category:'Movilidad',goals:['movement','athletic','adapted'],body:['tandem','linewalk','wallcalf','standingabduct','singlehinge','balance','birdcontrolled']},
 mixed:{name:'Cuerpo completo',category:'Mixta',goals:['habit','fitness','build','athletic'],body:['squat','kneepush','bridge','deadbug','fastmarch','skater','pushup'],db:['goblet','press','row','dbbridge','dbmarch','hammer','farmerwalk']}
};
const extensions={brief:{name:'Breve',count:3,sets:[2,2,3]},standard:{name:'Completa',count:4,sets:[3,3,4]},long:{name:'Extendida',count:5,sets:[3,4,4]}};
const names=['Básico','Intermedio','Avanzado'],keys=['basic','intermediate','advanced'],routines=[];
function target(e,level,family){if(family==='power'&&e.family==='power')return [4,5,6][level];if(e.unit==='sec')return family==='mobility'||family==='balance'?[20,25,30][level]:[45,55,65][level];return family==='mobility'||family==='balance'?[8,10,12][level]:[14,14,16][level];}
function estimate(items){let seconds=0;for(const [i,x] of items.entries()){const e=Engine.byId(x.id),work=e.unit==='sec'?x.target:x.target*3;seconds+=x.sets*work*(e.sides?2:1)+Math.max(0,x.sets-1)*x.rest+(i<items.length-1?x.rest:0);}return Math.ceil(seconds/60);}
for(const [family,spec] of Object.entries(families))for(let level=0;level<3;level++)for(const [extension,ex] of Object.entries(extensions))for(const equipment of spec.db?['body','db']:['body']){
 const pool=spec[equipment].map(id=>Engine.byId(id)).filter(e=>e.level<=(level===0?1:2)),selected=Array.from({length:Math.min(ex.count,pool.length)},(_,j)=>pool[(j+(extension==='long'?1:0)+level)%pool.length]);
 const items=selected.map(e=>({id:e.id,sets:family==='mobility'||family==='balance'?[1,2,2][level]:family==='power'&&e.family==='power'?[2,3,4][level]:ex.sets[level],target:target(e,level,family),rest:family==='power'?90:family==='mobility'||family==='balance'?30:family==='cardio'||family==='agility'?60:90}));
 const r={id:`own14-${family}-${level}-${extension}-${equipment}`,name:`${spec.name} · ${names[level]} · ${ex.name}${equipment==='db'?' · mancuernas':''}`,category:spec.category,level,items,requirements:equipment==='db'?['db']:[],impact:items.some(x=>Engine.byId(x.id).impact==='Con saltos')?'Con saltos':'Sin saltos',family,goalGroups:spec.goals,extension,planVersion:14,variant:'base',baseRoutineId:'own14-'+family,description:'Rutina propia de Hunter System. '+(family==='power'?'Saltos de calidad con pausas largas; sustituye por una opción sin impacto si hay restricciones.':'Registra cada serie y el esfuerzo final; las revisiones son semanales.'),minutes:estimate(items)};
 routines.push(r);
}
Catalog.routines.push(...routines);
function adjust(r,p){return r.items.map(x=>{const e=Engine.byId(x.id),gentle=Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle;return {...x,sets:gentle?1:x.sets,target:gentle?Math.min(x.target,e.unit==='sec'?15:6):Math.max(e.family==='power'?2:e.unit==='sec'?10:4,Math.floor(x.target*TrainingIntensity.scale(p)))};});}
root.OwnRoutines14={routines,families,extensions,estimate,adjust,names,keys};
})(window);

;
/* Objetivo -> selección -> dosis -> revisión semanal, sin saltar filtros de salud. */
(function(root){'use strict';
const oldItems=Schedule.items;
Schedule.items=function(p,kind,rotation,carry){const base=oldItems(p,kind,rotation,carry),group=GoalGroups14.of(p);if(!Adapt.mode(p).auto||Adapt.mode(p).reduced||GoalPlans.plans[p.finalTarget]?.gentle)return base;
 const specific=group==='athletic'&&kind==='cardio'?['fastfeet','shuffle','stepcross','lowhop','skaterreach','highknees','squatjump']:group==='movement'&&kind==='recovery'?['ankledrive','hipcircle','wallslide','tandem','singlehinge','thoracic','balance']:null;if(!specific)return base;
 const pool=specific.map(id=>Engine.byId(id)).filter(e=>Adapt.allowed(e,p)),count=p.trainingMode==='routines'?4:Math.min(p.dailyMissions||3,pool.length);return pool.length?Array.from({length:Math.min(count,pool.length)},(_,i)=>TrainingIntensity.dose(pool[(rotation+i)%pool.length],p,kind)):base;
};
const make=Engine.makeCycle;
Engine.makeCycle=function(p,start,index,last,logs){const c=make(p,start,index,last,logs),plan=GoalGroups14.plan(p);c.planVersion14=14;c.goalPlan14=plan;c.decision=plan.name+'. '+plan.reason+' '+plan.healthReason+' Revisión semanal según sesiones registradas, esfuerzo y dolor.';return c;};
})(window);

;
/* Personajes vectoriales articulados, reinterpretados de las cinco referencias. */
(function(root){
const stroke='stroke="#334354" stroke-width="4" stroke-linejoin="round"';
const cyan='#74e9ff', dark='#111a25';
const eye=(left,right)=>`<g data-part="eyes" fill="${cyan}">${left}${right}</g>`;
const armor=`<path d="M169 302 L232 302 L244 395 L200 423 L156 393Z" fill="#101923" ${stroke}/><path d="M164 321 L191 333 L201 318 L211 333 L239 320 L224 345 L212 349 L206 373 L199 390 L188 352 L176 346Z" fill="${cyan}"/><path d="M178 378 L197 387 M216 378 L206 395" stroke="${cyan}" stroke-width="5"/>`;
const legs=`<g data-part="legLeft"><path d="M165 391 L195 401 L190 466 Q173 479 150 467 L154 447Z" fill="${dark}" ${stroke}/><path d="M160 439 L187 446" stroke="${cyan}" stroke-width="4"/></g><g data-part="legRight"><path d="M204 402 L233 391 L244 462 Q230 479 209 467Z" fill="${dark}" ${stroke}/><path d="M211 445 L238 439" stroke="${cyan}" stroke-width="4"/></g>`;
const arms=`<g data-part="armLeft"><path d="M163 312 L144 320 L130 377 L143 394 L163 378 L177 331Z" fill="${dark}" ${stroke}/><path d="M140 348 L157 355 M135 363 L151 371" stroke="${cyan}" stroke-width="4"/></g><g data-part="armRight"><path d="M235 312 L255 320 L270 377 L257 394 L237 378 L222 331Z" fill="${dark}" ${stroke}/><path d="M242 350 L260 344 M248 366 L264 361" stroke="${cyan}" stroke-width="4"/></g>`;
const cape=`<path data-part="cape" d="M153 306 Q145 379 119 461 Q180 490 215 470 Q254 488 285 460 Q248 392 247 308Z" fill="#303c4e" stroke="#4f5a6d" stroke-width="3"/><path d="M148 336 L136 458 M249 341 L270 455" stroke="#657183" stroke-width="3" opacity=".45"/>`;
const faces={
knight:()=>`${cape}<path data-part="plume" d="M193 115 Q172 69 208 45 L239 63 L247 110 Q237 172 260 241 Q242 290 271 347 L244 342 Q210 279 224 233 Q205 168 216 121Z" fill="#d9364c" stroke="#701c36" stroke-width="4"/>${legs}${armor}${arms}<g data-part="head"><path d="M122 146 Q78 138 85 86 L116 116 L139 121 M278 146 Q322 138 315 86 L284 116 L261 121" fill="#101722" ${stroke}/><path d="M115 168 Q114 104 202 101 Q286 110 285 178 L277 237 Q249 281 201 286 Q146 281 122 238Z" fill="#101827" ${stroke}/><path d="M200 110 L200 238 L174 257 M200 238 L223 257" fill="none" stroke="#25364b" stroke-width="5"/>${eye('<path d="M136 205 L181 215 L188 230 L147 222Z"/>','<path d="M266 205 L222 215 L214 230 L254 222Z"/>')}<path d="M152 137 Q178 119 194 119" stroke="#3b4e63" fill="none" stroke-width="5" opacity=".5"/></g>`,
warrior:()=>`<g data-part="flames" fill="#78dfff" stroke="#3875aa" stroke-width="3" opacity=".7"><path d="M102 266 Q64 230 99 201 Q75 169 112 149 Q99 112 138 137 Q132 94 164 111 Q151 68 190 64 Q177 97 205 110 Q242 62 250 103 Q295 90 280 141 Q334 132 307 181 Q344 210 311 233 Q329 283 279 280Z"/></g>${legs}${armor}<g data-part="armLeft"><path d="M161 314 L140 325 L130 367 L149 376 L173 337" fill="${dark}" ${stroke}/><path d="M91 335 L181 412" stroke="#182331" stroke-width="17"/><path d="M66 308 Q37 361 102 395 L130 368 Q88 356 96 329Z" fill="#56c5eb" stroke="#152335" stroke-width="6"/><path d="M63 307 L89 288 L109 310 L93 331Z" fill="#142332" ${stroke}/></g><g data-part="armRight"><path d="M236 314 L262 331 L267 373 L242 381Z" fill="${dark}" ${stroke}/><path d="M258 324 L300 349 L293 414 L263 451 L235 416 L234 353Z" fill="#65d6ec" stroke="#11202c" stroke-width="10"/><path d="M260 347 L276 370 L263 419 L249 398Z" fill="#1b3543"/></g><g data-part="head"><path d="M123 149 Q69 106 108 60 L79 72 Q44 134 99 179Z M277 149 Q331 106 292 60 L321 72 Q356 134 301 179Z" fill="#171e26" stroke="#34434c" stroke-width="5"/><path d="M112 174 Q118 105 202 105 Q281 110 289 179 L278 245 Q252 282 202 286 Q146 280 124 243Z" fill="#58cee5" ${stroke}/><path d="M111 171 Q132 112 201 99 Q272 113 290 171 L272 202 L231 186 L201 215 L172 188 L129 201 L146 266 L110 245Z" fill="#161f2a" stroke="#314250" stroke-width="5"/>${eye('<path d="M145 218 Q164 217 179 228 Q173 256 152 246Z"/>','<path d="M255 218 Q236 217 221 228 Q227 256 248 246Z"/>')}<path d="M186 267 Q201 260 216 267" stroke="#183743" stroke-width="3" fill="none"/></g>`,
insect:()=>`<g data-part="wingLeft"><path d="M175 308 Q93 282 85 385 Q117 415 188 351Z" fill="#bcf8ff" fill-opacity=".8" stroke="#49b5ce" stroke-width="4"/></g><g data-part="wingRight"><path d="M225 308 Q307 282 315 385 Q283 415 212 351Z" fill="#bcf8ff" fill-opacity=".8" stroke="#49b5ce" stroke-width="4"/></g>${legs}<path d="M177 296 L161 317 L178 337 L160 347 L180 364 L169 385 L200 420 L230 386 L220 365 L239 347 L222 332 L239 310 L223 295Z" fill="#101923" ${stroke}/><g fill="${cyan}"><path d="M179 317 L202 325 L222 314 L215 336 L190 341Z"/><path d="M179 351 L206 347 L220 344 L212 363 L188 370Z"/><path d="M185 380 L214 375 L200 401Z"/></g>${arms}<g data-part="head"><path d="M111 175 L126 145 L119 115 L162 133 L192 76 L213 128 L264 102 L269 152 Q294 188 276 235 L283 248 L263 247 L253 276 L233 261 Q200 285 168 269 L151 288 L143 262 L122 268 L128 243 Q106 222 111 175Z" fill="#111923" stroke="#bccdda" stroke-width="3"/><g data-part="antennaLeft"><path d="M151 153 L108 113 L69 155" fill="none" stroke="#b5cada" stroke-width="8"/><path d="M151 153 L108 113 L69 155" fill="none" stroke="#121e2d" stroke-width="4"/></g><g data-part="antennaRight"><path d="M251 155 L298 113 L326 192" fill="none" stroke="#b5cada" stroke-width="8"/><path d="M251 155 L298 113 L326 192" fill="none" stroke="#121e2d" stroke-width="4"/></g>${eye('<path d="M144 184 Q173 184 181 222 Q153 235 139 215 Q130 193 144 184Z"/>','<path d="M252 184 Q223 184 215 222 Q243 235 257 215 Q266 193 252 184Z"/>')}<path d="M180 259 L187 272 L195 258 M207 258 L215 273 L222 257" fill="#f0fcff"/></g>`,
bear:()=>`<path d="M141 289 Q97 317 111 410 Q122 460 201 465 Q280 462 291 410 Q303 319 259 289Z" fill="#141d28" ${stroke}/><path d="M135 314 Q182 354 203 332 L183 376 L142 349 M216 347 L260 319 L239 369 L207 394" fill="${cyan}"/><g data-part="armLeft"><path d="M121 313 Q91 320 80 365 Q91 394 130 375 L151 340Z" fill="#101d28" ${stroke}/><path d="M83 350 L74 327 L91 337 L93 312 L106 335 L124 317 L120 346Z" fill="#d8fdff" stroke="#66dcec" stroke-width="3"/></g><g data-part="armRight"><path d="M279 313 Q309 320 320 365 Q309 394 270 375 L249 340Z" fill="#101d28" ${stroke}/><path d="M317 350 L326 327 L309 337 L307 312 L294 335 L276 317 L280 346Z" fill="#d8fdff" stroke="#66dcec" stroke-width="3"/></g><g data-part="legLeft"><ellipse cx="129" cy="427" rx="48" ry="43" fill="#111c29" ${stroke}/><ellipse cx="127" cy="439" rx="23" ry="15" fill="${cyan}"/><path d="M98 410 L99 395 M117 406 L118 390 M137 406 L138 390 M155 412 L156 397" stroke="${cyan}" stroke-width="8" stroke-linecap="round"/></g><g data-part="legRight"><ellipse cx="271" cy="427" rx="48" ry="43" fill="#111c29" ${stroke}/><ellipse cx="273" cy="439" rx="23" ry="15" fill="${cyan}"/><path d="M244 410 L244 395 M264 406 L264 390 M283 406 L283 390 M302 412 L302 397" stroke="${cyan}" stroke-width="8" stroke-linecap="round"/></g><g data-part="head"><path d="M125 172 Q91 151 112 122 Q135 112 153 145 M247 145 Q267 109 290 126 Q308 154 276 173" fill="#192431" stroke="#5b8496" stroke-width="4"/><path d="M120 143 L138 151 L125 159 M279 144 L262 153 L278 158" fill="${cyan}"/><path d="M116 198 Q115 139 200 138 Q285 139 284 207 Q282 293 200 300 Q118 292 116 198Z" fill="#15202b" ${stroke}/><g data-part="eyes"><path d="M143 200 L161 172 L155 201 L176 192 L163 214 L173 226 L151 220 L135 234 L143 214 L132 205Z" fill="#e5ffff"/><circle cx="246" cy="210" r="18" fill="#e5ffff"/></g><ellipse cx="200" cy="246" rx="21" ry="12" fill="${cyan}"/><path d="M200 256 L200 265 Q176 284 160 266 M200 265 Q224 284 242 266" fill="none" stroke="#d6f5fa" stroke-width="3"/></g><path data-part="flames" d="M166 148 Q133 118 167 87 Q165 107 187 102 Q174 66 209 43 Q200 74 227 85 Q263 112 234 145 L212 163 L195 143 L184 159Z" fill="#c9fbff" stroke="#64cde8" stroke-width="3"/>`,
scientist:()=>`<g data-part="legLeft"><path d="M165 386 L194 395 L193 459 Q167 474 145 460Z" fill="#22262d" stroke="#111820" stroke-width="5"/></g><g data-part="legRight"><path d="M207 395 L238 386 L251 459 Q228 477 208 460Z" fill="#22262d" stroke="#111820" stroke-width="5"/></g><path d="M174 293 L229 293 L238 408 L164 408Z" fill="#343941" stroke="#17212a" stroke-width="4"/><path d="M193 305 L204 321 L214 305 L207 369 L198 373Z" fill="#171d24"/><path d="M165 302 L192 314 L178 448 Q155 458 132 443 L150 348Z M232 302 L213 315 L230 448 Q256 458 274 443 L251 348Z" fill="#e7eaf0" stroke="#9da8b6" stroke-width="4"/><path d="M166 305 L159 346 L178 355 L154 375 M233 305 L244 346 L225 355 L249 375" fill="none" stroke="#a4aeb9" stroke-width="4"/><g data-part="armLeft"><path d="M162 313 L140 321 L113 373 L133 389 L161 359Z" fill="#e9edf3" stroke="#929ca8" stroke-width="4"/><path d="M116 374 Q109 392 126 401 Q145 398 141 383" fill="#f3d9d0" stroke="#8a7c80" stroke-width="3"/></g><g data-part="armRight"><path d="M239 313 L261 321 L288 373 L268 389 L240 359Z" fill="#e9edf3" stroke="#929ca8" stroke-width="4"/><path d="M285 374 Q292 392 275 401 Q256 398 260 383" fill="#f3d9d0" stroke="#8a7c80" stroke-width="3"/></g><g data-part="head"><path d="M115 175 Q124 116 201 116 Q278 116 288 185 L278 241 Q258 291 201 294 Q144 286 123 241Z" fill="#f5e8e2" stroke="#26303a" stroke-width="5"/><ellipse cx="150" cy="233" rx="20" ry="10" fill="#dfb1b1" opacity=".6"/><ellipse cx="250" cy="233" rx="20" ry="10" fill="#dfb1b1" opacity=".6"/><g data-part="eyes" fill="#353a42" stroke="#10151a" stroke-width="6"><ellipse cx="155" cy="217" rx="18" ry="23"/><ellipse cx="242" cy="217" rx="18" ry="23"/></g><path data-part="mouth" d="M187 255 Q201 251 215 255 L211 275 L192 275Z" fill="#b97783" stroke="#725864" stroke-width="3"/><path d="M119 212 Q83 160 113 114 L100 93 Q155 101 190 89 Q261 81 289 120 Q320 173 281 230 L277 172 L261 206 L250 162 L231 191 L205 148 L195 222 L160 189 L143 172 L134 208Z" fill="#30353d" stroke="#151e28" stroke-width="5"/><path d="M282 132 Q306 103 285 77" fill="none" stroke="#28323c" stroke-width="5"/><path d="M148 118 Q198 148 245 116" fill="none" stroke="#4e535c" stroke-width="10" stroke-linecap="round" opacity=".55"/></g>`
};
const labels={knight:'IGRIS',warrior:'IRON',insect:'BERU',bear:'TANK',scientist:'DOKJA KIM'};
let instance=0;
function svg(type){const unique=type+'part'+(++instance);if(!faces[type])throw new Error('Personaje desconocido');return `<svg class="igris-svg" viewBox="-70 -50 540 610" preserveAspectRatio="xMidYMid meet" role="img" aria-label="${labels[type]}"><defs><radialGradient id="aura-${unique}"><stop stop-color="#71deff" stop-opacity=".22"/><stop offset="1" stop-color="#71deff" stop-opacity="0"/></radialGradient></defs><ellipse data-part="aura" cx="200" cy="290" rx="170" ry="210" fill="url(#aura-${unique})"/><ellipse data-part="shadow" cx="200" cy="480" rx="81" ry="12" fill="#050912" opacity=".65"/><g data-part="root"><g data-part="body">${faces[type]()}</g></g><g data-part="particles" fill="#9bf0ff"><circle cx="86" cy="280" r="3"/><circle cx="309" cy="246" r="2"/><circle cx="75" cy="350" r="2"/><circle cx="327" cy="344" r="3"/></g></svg>`;}
root.IgrisArt={svg,labels};
})(window);

;
/* Volumen ilustrado 2.5D: conserva las trayectorias 2D del catálogo.
 * La profundidad es un recurso gráfico, no una medición anatómica 3D. */
(function(root){
'use strict';
const rates=[{value:.5,name:'Lenta',label:'0,5×'},{value:1,name:'Normal',label:'1×'},{value:1.5,name:'Rápida',label:'1,5×'}];
let playback=1;
function setRate(value){value=Number(value);if(!rates.some(r=>r.value===value))return false;playback=value;
 if(typeof state!=='undefined'){state.demoSpeed=value;save();}
 if(typeof vectorSession!=='undefined'&&vectorSession)vectorSession.speed=value;
 if(typeof guide11!=='undefined'&&guide11)guide11.demoLast=0;
 document.querySelectorAll('[data-playback-speed]').forEach(b=>b.setAttribute('aria-pressed',String(Number(b.dataset.playbackSpeed)===value)));
 return true;
}
function controls(context=''){return `<fieldset class="playback-speeds"><legend>Velocidad de la demostración${context?' · '+context:''}</legend><div class="speed-options">${rates.map(r=>`<button type="button" data-playback-speed="${r.value}" aria-pressed="${playback===r.value}" onclick="MotionPlayback.set(${r.value})"><b>${r.name}</b><small>${r.label}</small></button>`).join('')}</div></fieldset>`;}
root.MotionPlayback={rates,get rate(){return playback;},set:setRate,controls};
const clamp=(v,a,b)=>Math.max(a,Math.min(b,v));
function shade(color,factor){let rgb=/^#([a-f\d]{6})$/i.exec(color||'');let c=rgb?[0,2,4].map(i=>parseInt(rgb[1].slice(i,i+2),16)):(String(color).match(/[\d.]+/g)||[87,216,242]).slice(0,3).map(Number);return '#'+c.map(v=>Math.round(clamp(v*factor,0,255)).toString(16).padStart(2,'0')).join('');}
function geometry(id,phase){const e=Engine.byId(id),model=MotionDB[e?.baseId||id];if(!model)throw Error('Sin modelo de movimiento: '+id);const f=interpolateMotion(model,((Number(phase)||0)%48+48)%48);return {e,model,f,ground:Math.max(...modelGround(id))};}
function draw(canvas,id,phase=0,mirror=false){if(!canvas)return;const ctx=canvas.getContext('2d');if(!ctx)return;const {model,f,ground}=geometry(id,phase),p=f.p,d=f.detail,style=getComputedStyle(document.documentElement),accent=style.getPropertyValue('--accent').trim()||'#57d8f2',bg=style.getPropertyValue('--input').trim()||'#152538';
 const line=(a,b,w,color)=>{ctx.beginPath();ctx.strokeStyle=color;ctx.lineWidth=w;ctx.lineCap='round';ctx.moveTo(...a);ctx.lineTo(...b);ctx.stroke();};
 const sphere=(a,r,color)=>{const g=ctx.createRadialGradient(a[0]-r*.3,a[1]-r*.35,r*.1,a[0],a[1],r);g.addColorStop(0,shade(color,1.35));g.addColorStop(.45,color);g.addColorStop(1,shade(color,.36));ctx.beginPath();ctx.fillStyle=g;ctx.arc(...a,r,0,Math.PI*2);ctx.fill();};
 const capsule=(a,b,w,color)=>{const dx=b[0]-a[0],dy=b[1]-a[1],length=Math.hypot(dx,dy)||1,nx=-dy/length,ny=dx/length,mx=(a[0]+b[0])/2,my=(a[1]+b[1])/2,g=ctx.createLinearGradient(mx-nx*w/2,my-ny*w/2,mx+nx*w/2,my+ny*w/2);g.addColorStop(0,shade(color,.3));g.addColorStop(.32,shade(color,1.3));g.addColorStop(.6,color);g.addColorStop(1,shade(color,.4));line(a,b,w+3,'#101923');line(a,b,w,g);};
 ctx.clearRect(0,0,720,800);ctx.fillStyle=bg;ctx.fillRect(0,0,720,800);
 const haze=ctx.createRadialGradient(350,360,20,350,360,410);haze.addColorStop(0,shade(accent,.2));haze.addColorStop(1,bg);ctx.fillStyle=haze;ctx.fillRect(0,0,720,800);
 ctx.save();if(mirror){ctx.translate(720,0);ctx.scale(-1,1);}
 // Plano y sombra de apoyo. No cambia la posición de manos/pies en el modelo.
 ctx.globalAlpha=.18;for(let i=0;i<8;i++)line([40+i*90,ground+40],[300+i*20,ground-35],1,'#b2cbda');for(let i=0;i<4;i++)line([40,ground+i*20],[680,ground+i*20],1,'#b2cbda');ctx.globalAlpha=1;
 const sx=(p[8][0]+p[10][0]+p[2][0])/3;ctx.fillStyle='#03070b';ctx.globalAlpha=.5;ctx.beginPath();ctx.ellipse(sx,ground+8,120,17,0,0,Math.PI*2);ctx.fill();ctx.globalAlpha=1;
 for(const [type,rect] of f.equipment){const [x,y,r,b]=rect;ctx.fillStyle='#243747';ctx.fillRect(x+10,y+8,r-x,b-y);ctx.strokeStyle='#a4bac8';ctx.lineWidth=4;ctx.strokeRect(x,y,r-x,b-y);line([x,y],[x+10,y+8],3,'#526b80');line([r,b],[r+10,b+8],3,'#526b80');if(type==='incline')capsule([x,b-30],[r,y],10,'#8aa3b7');}
 for(const [a,b] of [[1,5],[5,6],[2,9],[9,10]])capsule(p[a],p[b],21,shade(accent,.65));
 if(d.spine){ctx.beginPath();ctx.strokeStyle=shade(accent,.45);ctx.lineWidth=36;ctx.lineCap='round';ctx.moveTo(...p[1]);ctx.quadraticCurveTo((p[1][0]+p[2][0])/2,(p[1][1]+p[2][1])/2+d.spine,...p[2]);ctx.stroke();ctx.lineWidth=20;ctx.strokeStyle=accent;ctx.stroke();}else capsule(p[1],p[2],38,accent);
 for(const [a,b] of [[0,1],[1,3],[3,4],[2,7],[7,8]])capsule(p[a],p[b],24,accent);
 for(const i of [1,2,3,5,7,9])sphere(p[i],10,'#9badc0');sphere(p[0],32,'#e1eaf2');sphere([p[0][0]+(d.face||10),p[0][1]-3],4,'#172438');
 for(const i of [8,10]){let q=d.toeAmount??d.amount??0;capsule(p[i],[p[i][0]+28,p[i][1]-(d.feet==='toelift'||d.feet==='heeltoe'?25*q:d.feet==='ankle'?35*(q-.5):0)],14,accent);}
 for(const [x,y] of f.loads){capsule([x-18,y],[x+18,y],8,'#b8c6d6');capsule([x-22,y-14],[x-22,y+14],16,'#e7c76c');capsule([x+22,y-14],[x+22,y+14],16,'#e7c76c');}
 if(d.towel)line(d.towel[0],d.towel[1],5,'#e7c76c');
 if(d.breath!==undefined){ctx.strokeStyle=accent;ctx.lineWidth=3;ctx.beginPath();ctx.arc(p[1][0],p[1][1]+50,35+15*d.breath,0,Math.PI*2);ctx.stroke();}
 ctx.restore();
 if(d.detail){const q=d.amount||0;ctx.fillStyle='#152332';ctx.fillRect(410,45,280,170);ctx.fillStyle='#eff6ff';ctx.font='18px sans-serif';ctx.fillText('Detalle de mano',428,74);capsule([440,162],[525,150],16,accent);if(d.detail==='forearmmob'){ctx.beginPath();ctx.fillStyle=q>.5?'#e9c49f':accent;ctx.ellipse(560,150,30,Math.max(3,18*Math.abs(Math.cos(q*Math.PI))),0,0,Math.PI*2);ctx.fill();}else if(d.detail==='fingers'){for(let j=0;j<5;j++)capsule([525,150],[550+35*q,150+(j-2)*15*q],7,accent);}else capsule([525,150],[590,150+50*(q-.5)],14,accent);}
 if(d.grip){ctx.fillStyle='#edf4ff';ctx.font='19px sans-serif';ctx.fillText(d.grip,30,30);}
 canvas.dataset.renderer='2.5d';return model;
}
root.DepthMotion={draw,geometry,shade};
})(window);

