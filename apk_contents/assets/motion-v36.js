/* V36: V31 como referencia. Correcciones acotadas; los demás modelos se conservan. */
(function(root){'use strict';
 const original=Motion30.get,patched=new WeakMap(),copy=x=>JSON.parse(JSON.stringify(x));
 const add=(p,q)=>p.map((v,i)=>v+q[i]),mix=(a,b,t)=>a.map((v,i)=>v+(b[i]-v)*t);
 const kinds=new Set(['incline','heeldig','bridge','dbbridge','singlebridge']);
 function corrected(e,source){
  const kind=Motion31.kindOf(e,source);
  if(!kinds.has(kind)||source.motionRevision36===36)return source;
  if(patched.has(source))return patched.get(source);
  const m=copy(source),param=e.param30||e.param23||{};
  if(kind==='incline')m.facing23='right';
  for(const f of m.frames){
   const p=f.p,a=f.detail.amount31??0,t=f.detail.cycle31??0;
   if(kind==='heeldig'){
    const near=Math.max(0,Math.sin(t*2*Math.PI)),far=Math.max(0,-Math.sin(t*2*Math.PI));
    p[0]=[350,215];p[1]=[350,275];p[2]=[350,440];
    for(const [el,hand,knee,ankle,u,o]of [[3,4,7,8,near,0],[5,6,9,10,far,-13]]){
     const shoulder=add(p[1],[o,o]);
     // Guardia y extensión: el codo permanece a la altura del hombro o debajo.
     const upper=1.02*(1-u),forearm=-1.22*(1-u);
     p[el]=add(shoulder,[96*Math.cos(upper),96*Math.sin(upper)]);
     p[hand]=add(p[el],[90*Math.cos(forearm),90*Math.sin(forearm)]);
     p[ankle]=[357+o+90*u,700+o];p[knee]=Rig2D23.ik(add(p[2],[o,o]),p[ankle],140,1);
    }
    f.detail.floor23=715;f.detail.feet30={8:[Math.cos(-.65*near),Math.sin(-.65*near)],10:[Math.cos(-.65*far),Math.sin(-.65*far)]};
   }else if(kind==='bridge'||kind==='dbbridge'||kind==='singlebridge'){
    // Cabeza, escápulas y brazos apoyados; extensión de cadera sin arco excesivo.
    p[0]=[140,674];p[1]=[200,674];p[2]=[365-10*a,665-59*a];
    for(const [knee,ankle,o]of [[7,8,0],[9,10,-13]]){
     const single=kind==='singlebridge'||param.single;
     if(knee===9&&(single||param.march)){
      const u=single?1:a;
      const standingFoot=[515+o+(param.long?85:0),682+o];
      const standingKnee=Rig2D23.ik(add(p[2],[o,o]),standingFoot,135,1);
      p[knee]=mix(standingKnee,add(p[2],[85,-100]),u);
      p[ankle]=mix(standingFoot,add(p[2],[185,-85]),u);
     }else p[knee]=Rig2D23.ik(add(p[2],[o,o]),p[ankle],135,1);
    }
    const loaded=kind==='dbbridge'||f.detail.pelvisLoad31;
    if(loaded){
     p[4]=add(p[2],[-8,-20]);p[6]=add(p[2],[-21,-33]);
     p[3]=Rig2D23.ik(p[1],p[4],96,1);p[5]=Rig2D23.ik(add(p[1],[-13,-13]),p[6],96,1);
     f.loads=[add(p[2],[-7,-18])];
    }else{p[3]=[265,688];p[4]=[335,690];p[5]=[252,675];p[6]=[322,677];}
    f.detail.feet30={8:[1,0],10:kind==='singlebridge'||param.single||param.march?[1,-.12]:[1,0]};
   }
   f.detail.correction36=kind;
  }
  m.motionRevision36=36;patched.set(source,m);return m;
 }
 Motion30.get=function(id){const result=original(id);return {...result,model:corrected(result.e,result.model)};};
 // Leer el archivo local no sustituye el modelo ya montado durante su reproducción.
 // También evita diferencias subpíxel de trigonometría entre versiones de V8.
 const preload=Motion30.preload;
 Motion30.preload=function(id){
  const mounted=Motion30.cache.has(id)?Motion30.get(id).model:null;
  return preload(id).then(()=>{if(mounted&&Motion30.loaded.has(id))Motion30.cache.set(id,mounted);});
 };
 root.Motion36={corrected,kinds,reference:31,version:36};
 Motion2D18.clearBounds();
})(window);
