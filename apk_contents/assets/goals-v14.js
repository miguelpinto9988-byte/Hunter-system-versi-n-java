/* Seis familias visibles. Conserva los objetivos anteriores en perfiles/historial. */
(function(root){'use strict';
GoalPlans.plans.athletic={name:'Agilidad, saltos y potencia',base:'endurance',pattern:['cardio','strength','cardio'],why:'Alterna precisión de pasos, saltos de pocas repeticiones y fuerza complementaria. Descansa entre bloques de potencia.'};Adapt.goals.athletic=GoalPlans.plans.athletic.name;
const groups={
 build:{name:'Fuerza y masa muscular',target:'muscle',legacy:['strength','muscle','upper','lower','core','dumbbells'],families:['strength','hypertrophy','core','mixed'],why:'Trabaja fuerza y musculatura con peso corporal o mancuernas. El texto puede dar prioridad a fuerza, zona media, brazos o piernas.',measure:'Registra repeticiones, carga y esfuerzo en los mismos movimientos.'},
 fitness:{name:'Resistencia y condición física',target:'endurance',legacy:['endurance','activity'],families:['cardio','mixed','strength'],why:'Combina intervalos de cardio y fuerza complementaria. La actividad puede acompañar el control de peso.',measure:'Registra bloques completados y esfuerzo; compara sesiones equivalentes.'},
 athletic:{name:'Agilidad, saltos y potencia',target:'athletic',legacy:['athletic','coordination'],families:['agility','power','mixed','strength'],why:'Practica pasos precisos, cambios de apoyo, saltos controlados y fuerza. Las restricciones de impacto sustituyen los saltos.',measure:'Anota equilibrio, aterrizaje y calidad además de cantidad.'},
 movement:{name:'Movilidad, equilibrio y control',target:'mobility',legacy:['mobility','balance','flexibility','posture'],families:['mobility','balance','core'],why:'Combina movilidad cómoda, estabilidad y control del movimiento. Más recorrido solo cuando se conserva el control.',measure:'Registra comodidad y estabilidad; no fuerces el recorrido.'},
 habit:{name:'Constancia y bienestar',target:'general',legacy:['general','functional','short','lowimpact'],families:['mixed','strength','cardio','mobility'],why:'Alterna sesiones completas y descanso según tus días. Incluye opciones breves y sin saltos.',measure:'Mide semanas cumplidas y continuidad, junto con el esfuerzo.'},
 adapted:{name:'Actividad adaptada y retorno',target:'returning',legacy:['returning','seated','comfortable'],families:['mobility','balance','strength','cardio'],why:'Busca movimientos compatibles y volumen reducido según restricciones e indicaciones. No asigna un tratamiento por el nombre de la lesión.',measure:'Registra síntomas, tolerancia y las indicaciones recibidas.'}
};
const clean=s=>String(s||'').toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g,'');
function of(p={}){if(groups[p.goalGroup])return p.goalGroup;return Object.keys(groups).find(k=>groups[k].legacy.includes(p.finalTarget||p.goal))||'habit';}
function interpret(text,chosen){const raw=clean(text),healthText=raw.replace(/\b(?:no tengo|sin)\s+(?:ningun[ao]?\s+)?(?:lesion(?:es)?|dolor)[^,.;]*/g,''),medical=/lesion|hernia|fractura|cirug|dolor|duele|molestia|rehabil/.test(healthText),noImpact=/sin salt|sin impacto|evitar salt|no (?:quiero |puedo )?salt/.test(raw);
 const positive=raw.replace(/(?:no quiero|no deseo|no busco|evitar)\s+[^,.;]+/g,'');
 const lexicon={build:[/fuerza|fuerte|muscul|hipertrof|esculp|mancuerna|pesas|biceps|gluteo|abdomen|pecho/g],fitness:[/resist|cardio|grasa|adelgaz|peso|aguantar|cansarme menos/g],athletic:[/agilidad|agil|saltos?|potencia|explosiv|velocidad|deport|coordinacion/g],movement:[/movilidad|equilibrio|flexibilidad|postur|control motor|estirar/g],habit:[/constan|habito|rutina estable|bienestar|salud|regularidad|diari|breve/g],adapted:[/retomar|volver|recuperacion autorizada|sentado|recuperar habito/g]};
 const scores=Object.entries(lexicon).map(([group,patterns])=>({group,score:patterns.reduce((n,regex)=>n+[...positive.matchAll(regex)].length,0)})).filter(x=>x.score).sort((a,b)=>b.score-a.score);
 const ambiguous=scores.length>1&&scores[0].score===scores[1].score,primary=ambiguous?(scores.some(x=>x.group===chosen)?chosen:null):scores[0]?.group||null;
 let target=primary?groups[primary].target:null,focus='full';
 if(primary==='build'){if(/fuerza|fuerte/.test(positive)&&!/muscul|hipertrof|esculp/.test(positive))target='strength';if(/braz|pecho|hombro|tren superior/.test(positive)){focus='upper';target='upper';}else if(/pierna|gluteo|tren inferior/.test(positive)){focus='lower';target='lower';}else if(/abdomen|core|zona media/.test(positive)){focus='core';target='core';}}
 if(primary==='fitness'&&/grasa|adelgaz|bajar peso/.test(positive))target='activity';
 if(primary==='movement'){if(/equilibr/.test(positive))target='balance';else if(/flexibil|estirar/.test(positive))target='flexibility';else if(/postur/.test(positive))target='posture';}
 if(primary==='habit'&&noImpact)target='lowimpact';
 if(primary==='adapted'&&/sentado/.test(positive))target='seated';
 return {group:primary,target,focus,medical,noImpact,alternatives:scores.map(x=>x.group),ambiguous,reason:primary?groups[primary].why:'No distingo una prioridad única. Elige una de las seis familias y revisa el plan propuesto.',measure:primary?groups[primary].measure:''};
}
function plan(p){const key=of(p),group=groups[key],specific=GoalPlans.plans[p.finalTarget]||GoalPlans.plans[group.target],mode=Adapt.mode(GoalPlans.adjusted(p));return {group:key,name:group.name,specific:specific.name,reason:specific.why,measure:group.measure,days:Schedule.defaults(p).filter(x=>x!=='rest').length,mode:p.trainingMode==='routines'?'Rutinas completas':'Desafíos diarios',level:{basic:'Básico',intermediate:'Intermedio',advanced:'Avanzado'}[p.difficulty]||'Básico',compatible:mode.auto,reduced:mode.reduced||!!specific.gentle,healthReason:mode.reason};}
root.GoalGroups14={groups,of,interpret,plan};
})(window);
