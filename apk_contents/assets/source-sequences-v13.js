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
