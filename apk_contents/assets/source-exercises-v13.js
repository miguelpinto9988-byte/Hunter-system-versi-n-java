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
