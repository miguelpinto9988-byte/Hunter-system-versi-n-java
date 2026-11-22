/* Los bloques conservan los índices originales de cada serie en el registro. */
(function(root){'use strict';
const modes=['exercise','rounds'],mode=p=>modes.includes(p?.blockMode22)?p.blockMode22:'exercise';
function expand(day,selected='exercise',profile={}){
 if(day.sourceSequence)return null;
 const items=day.items||[],out=[],blocks=[],seconds=TrainingProgression19.rest(profile);
 const pushExercise=(x,itemIndex,setIndex,blockIndex)=>{
  const e=Engine.byId(x.id);if(!e)throw Error('Ejercicio desconocido: '+x.id);
  const unit=Engine.unitOf(x),s={kind:'exercise',id:e.id,label:e.name,target:x.target,unit,...(x.side?{side:x.side}:{}),seconds:unit==='sec'?x.target:Math.ceil(OwnRoutines18.workSeconds(x)),itemIndex,setIndex,sets:x.sets,blockIndex};out.push(s);return s;
 };
 if(!items.length)return {steps:[],blocks:[],mode:selected,restSeconds:seconds};
 out.push({kind:'preparation',label:'Preparación · revisa espacio, equipo y técnica',seconds:60,blockIndex:-1});
 if(selected==='rounds'){
  const rounds=Math.max(...items.map(x=>x.sets));
  for(let j=0;j<rounds;j++){
   const b={index:j,label:'Ronda '+(j+1),stepIndices:[],series:[]};
   items.forEach((x,i)=>{if(j<x.sets){b.stepIndices.push(out.length);b.series.push(pushExercise(x,i,j,j));}});blocks.push(b);
   if(j<rounds-1)out.push({kind:'rest',label:'Descanso entre rondas · '+seconds+' segundos',seconds,blockIndex:j,restScope22:'block',nextBlockIndex22:j+1});
  }
 }else{
  items.forEach((x,i)=>{
   const b={index:i,label:Engine.byId(x.id).name,stepIndices:[],series:[]};
   for(let j=0;j<x.sets;j++){
    b.stepIndices.push(out.length);b.series.push(pushExercise(x,i,j,i));
    if(j<x.sets-1)out.push({kind:'rest',label:'Descanso entre series · '+seconds+' segundos',seconds,blockIndex:i,restScope22:'series'});
   }
   blocks.push(b);
   if(i<items.length-1)out.push({kind:'rest',label:'Descanso entre bloques · '+seconds+' segundos',seconds,blockIndex:i,restScope22:'block',nextBlockIndex22:i+1});
  });
 }
 out.push({kind:'cooldown',label:'Vuelta a la calma · baja el ritmo y respira',seconds:60,blockIndex:blocks.length});
 out.forEach(s=>s.blockCount22=blocks.length);
 return {steps:out,blocks,mode:selected,restSeconds:seconds};
}
root.BlockSession22={expand,mode,modes};
})(window);
