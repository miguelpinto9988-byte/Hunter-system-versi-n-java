/* Cantidades explícitas de subtítulos automáticos contrastadas con capítulos. */
(function(root){
 const ids=['pau-altpress','pau-overtriceps','pau-arnold','pau-lateralcurl','hammer','pau-curlcombo','pau-raisecombo','pau-standingfly','bentrow','pau-presscombo','pau-kickback','pau-plankup','plank','pau-pushchild'];
 const steps=[{kind:'preparation',label:'Preparar el circuito: 14 movimientos de 30 s y descansos de 10 s',seconds:10,sourceAt:62}];
 ids.forEach((exerciseId,i)=>{steps.push({kind:'exercise',exerciseId,sets:1,target:30,unit:'sec',restSeconds:0,sourceAt:72+i*40});if(i<ids.length-1)steps.push({kind:'rest',label:'Descanso',seconds:10,sourceAt:102+i*40});});
 root.SourceSequences12=[{videoId:'QdiX-1poVO8',channelId:'UCQ_N_uuyHbTE8qZ_GRIJmKw',name:'Circuito con mancuernas para brazos y espalda · Pau Fit',verified:true,evidenceType:'captions-and-chapters',visualVerified:false,verificationNote:'Secuencia textual contrastada con capítulos y subtítulos automáticos: 14 movimientos de 30 s, 13 descansos de 10 s y preparación de 10 s. La entrenadora declara usar dos mancuernas de 2,5 kg; es una referencia, no tu carga asignada. Revisión visual del video pendiente; los subtítulos automáticos pueden contener errores. Las demostraciones 2D son esquemas propios.',steps}];
})(typeof window!=='undefined'?window:globalThis);
