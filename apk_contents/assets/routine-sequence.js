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
