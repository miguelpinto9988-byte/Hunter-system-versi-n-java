/* Interpretación local conjunta: familia elegida, texto y metas personales activas. */
(function(root){'use strict';
const clean=s=>String(s||'').toLowerCase().normalize('NFD').replace(/[\u0300-\u036f]/g,''),oldAdjusted=GoalPlans.adjusted;
function personal(){return typeof state==='undefined'?[]:(state.goals||[]).filter(g=>g.repeat==='daily'||!g.dates?.length).map(g=>g.title);}
function interpret(p={},titles=personal()){
 const chosen=GoalGroups14.of(p),text=[p.finalGoal||'',...titles].join('. '),r=GoalGroups14.interpret(text,chosen),plain=clean(text),positive=plain.replace(/(?:no quiero|no deseo|no busco|evitar)\s+[^,.;]+/g,'');
 const priorities=[chosen,...r.alternatives.filter(g=>g!==chosen)],weights=Object.fromEntries(priorities.map((g,i)=>[g,i===0?2:1]));
 const focus=/piernas?|gluteos?|tren inferior/.test(positive)?'lower':/brazos?|pecho|hombros?|tren superior/.test(positive)?'upper':/abdomen|zona media|core/.test(positive)?'core':p.focus||'full';
 const inferred={};if(r.noImpact)inferred.noImpact=true;
 const constraints={noFloor:/sin suelo|no (?:quiero |puedo )?(?:tumbarme|arrodillarme)|evitar (?:el )?suelo/,noOverhead:/sin (?:elevar|levantar)[^.;]*cabeza|(?:no|evitar) (?:quiero |puedo )?(?:levantar|elevar|hacer press)[^.;]*sobre la cabeza/,noLoad:/sin (?:peso|pesas|carga)|no (?:quiero |puedo )?usar (?:peso|pesas)/,noWrist:/sin apoyo[^.;]*manos|no apoyar[^.;]*manos/,noKnee:/sin flexion[^.;]*rodilla|no doblar[^.;]*rodilla/,seatedOnly:/solo sentado|unicamente sentado/};
 for(const [key,re]of Object.entries(constraints))if(re.test(plain)&&!(key==='noLoad'&&/con y sin (?:peso|carga)/.test(plain)))inferred[key]=true;
 const specific=r.group===chosen&&r.target?r.target:p.finalTarget||GoalGroups14.groups[chosen].target;
 return {chosen,priorities,weights,focus,target:specific,inferred,medical:r.medical,text,personalTitles:titles,reason:'Prioridad: '+GoalGroups14.groups[chosen].name+(priorities.length>1?'. Complemento: '+priorities.slice(1).map(g=>GoalGroups14.groups[g].name).join(', '):'')+'. '+(focus!=='full'?'Enfoque: '+({upper:'tren superior',lower:'piernas y glúteos',core:'zona media'}[focus]||focus)+'. ':'')+'Equipo, nivel, días, restricciones y registros ajustan la selección y su dosis.'};
}
function adjusted(p){if(!p)return p;const i=interpret(p),base=oldAdjusted({...p,finalTarget:i.target});return {...base,focus:i.focus,goalIntent24:i,restrictions:{...base.restrictions,...i.inferred}};}
function score(e,p,family){const i=p.goalIntent24||interpret(p),f=clean([e.name,e.family,e.group].join(' '));let n=0;
 for(const [g,w]of Object.entries(i.weights)){
  if(g==='build'&&e.category==='Fuerza')n+=w;
  if(g==='fitness'&&e.category==='Cardio')n+=w;
  if(g==='movement'&&e.category==='Movilidad')n+=w;
  if(g==='athletic'&&/agility|power|salt|salto|skater|shuffle|rapido|agilidad|potencia|pliomet/.test(f))n+=w*2;
 }
 if(i.focus==='lower'&&['Piernas','Cadera'].includes(e.group))n+=3;
 if(i.focus==='upper'&&['Pecho','Espalda','Hombros','Brazos'].includes(e.group))n+=3;
 if(i.focus==='core'&&['Abdomen','Core'].includes(e.group))n+=3;
 return n;
}
GoalPlans.adjusted=adjusted;
root.GoalContext24={interpret,adjusted,score,personal,clean};
})(window);
