/* Dos fichas autorizadas. No se modifica ninguna variante ni otro modelo. */
(function(root){'use strict';
 const ids=new Set(['home-plankdrag30','altseatedcurl']),before=Motion30.get,cache=new WeakMap();
 const copy=x=>JSON.parse(JSON.stringify(x)),mix=(a,b,t)=>a.map((v,i)=>v+(b[i]-v)*t),ease=t=>(1-Math.cos(Math.PI*Math.max(0,Math.min(1,t))))/2;
 function path(t,keys){let i=keys.findIndex(k=>k[0]>=t);if(i<=0)return copy(keys[0][1]);const a=keys[i-1],b=keys[i];return mix(a[1],b[1],ease((t-a[0])/(b[0]-a[0])));}
 function correct(e,source){
  if(!ids.has(e.id)||source.motionRevision373===373)return source;if(cache.has(source))return cache.get(source);
  const m=copy(source),drag=e.id==='home-plankdrag30';
  m.view21={...m.view21,primary:drag?'top':'front',label:drag?'Superior':'Frontal',project:null,mirror:false,inset:drag?'plankdrag373':'curl373',reason:drag?'Observa una mano fija y el arrastre de la carga bajo el torso. El detalle lateral muestra los apoyos.':'Los brazos quedan a los lados del torso. Alterna la flexión sin desplazar los codos.'};m.posture23=drag?'prone':'upright';m.facing23=drag?'left':null;
  m.frames.forEach((f,i)=>{
   const t=i/48,p=f.p;f.detail={...f.detail,correction373:e.id,cycle373:t,view21:m.view21.primary};
   if(drag){
    const left=t<.5,local=left?t*2:(t-.5)*2,rightLoad=[488,320],leftLoad=[232,320],from=left?rightLoad:leftLoad,to=left?leftLoad:rightLoad,handRest=left?[258,250]:[462,250];
    const grip=path(local,[[0,handRest],[.24,from],[.76,to],[1,handRest]]),load=path(local,[[0,from],[.24,from],[.76,to],[1,to]]);
    p[0]=[360,195];p[1]=[360,260];p[2]=[360,440];p[4]=left?grip:[258,250];p[6]=left?[462,250]:grip;
    // Los brazos verticales se acortan en la proyección superior.
    for(const [el,hand,x,bend]of [[3,4,318,1],[5,6,402,-1]]){const origin=[x,260],distance=Math.hypot(p[hand][0]-x,p[hand][1]-260),length=Math.min(95,distance/2+24);p[el]=Rig2D23.ik(origin,p[hand],length,bend);}
    p[7]=[335,568];p[8]=[310,700];p[9]=[385,568];p[10]=[410,700];f.equipment=[];f.loads=[load];f.detail.front19=true;f.detail.topShoulder19=false;f.detail.support23=true;f.detail.floor23=730;f.detail.feet30={8:[-.08,1],10:[.08,1]};f.detail.activeHand373=left?4:6;f.detail.gripping373=local>=.24&&local<=.76;
    f.detail.stage18=local<.24?'ALCANZA · LA OTRA MANO QUEDA FIJA':local<=.76?'ARRASTRA BAJO EL TORSO · PELVIS ESTABLE':'DEVUELVE LA MANO AL APOYO';
   }else{
    p[0]=[360,260];p[1]=[360,320];p[2]=[360,500];p[3]=[282,420];p[5]=[438,420];const a=t<.5?Math.sin(2*Math.PI*t)**2:0,b=t>=.5?Math.sin(2*Math.PI*t)**2:0;
    for(const [el,hand,u,sign]of [[3,4,a,1],[5,6,b,-1]])p[hand]=[p[el][0]+sign*38*Math.sin(Math.PI*u),p[el][1]+86*Math.cos(Math.PI*u)];
    p[7]=[310,548];p[8]=[293,710];p[9]=[410,548];p[10]=[427,710];f.equipment=[['bench',[270,546,450,727]]];f.loads=[copy(p[4]),copy(p[6])];f.detail.floor23=727;f.detail.feet30={8:[-.08,1],10:[.08,1]};f.detail.curl373=[a,b];f.detail.stage18=a>.02?'FLEXIONA UN BRAZO · CODO FIJO':b>.02?'ALTERNA EL BRAZO · TRONCO ESTABLE':'BRAZOS A LOS LADOS · PIES APOYADOS';
   }
  });m.motionRevision373=373;cache.set(source,m);return m;
 }
 Motion30.get=function(id){const x=before(id);return {...x,model:correct(x.e,x.model)};};
 const inset=Motion30.inset;
 Motion30.inset=function(kind,f,phase,accent){
  if(!['plankdrag373','curl373'].includes(kind)||!ids.has(f.detail.correction373))return inset(kind,f,phase,accent);
  const ink='#dceaf0',red='#fb927d';let title,note,art;
  if(kind==='plankdrag373'){
   title='DE LADO · UNA MANO SOSTIENE EL CUERPO';note='Conserva hombros, caderas y talones alineados. Arrastra por el suelo, sin girar la pelvis.';const load=130+(f.loads[0][0]-232)/256*64;
   art='<path d="M88 707H325" stroke="#7a9aa7" stroke-width="3"/><circle cx="105" cy="655" r="10" fill="#edc5ad"/><path d="M118 662L198 681L286 701M139 667V704" fill="none" stroke="'+ink+'" stroke-width="10" stroke-linecap="round"/><path d="M139 672L'+load+' 698" stroke="'+red+'" stroke-width="6"/><path d="M'+(load-9)+' 704H'+(load+9)+'" stroke="'+accent+'" stroke-width="7"/><text x="345" y="683" font-size="18" fill="'+ink+'">APOYO ESTABLE</text>';
  }else{
   title='DE LADO · FLEXIONA SIN MOVER EL CODO';note='Alterna los brazos. El brazo superior queda estable y la muñeca sigue la línea del antebrazo.';const amount=Math.max(...f.detail.curl373),angle=Math.PI/2-2.8*amount,hand=[168+43*Math.cos(angle),676+43*Math.sin(angle)];
   art='<path d="M130 650V699H166V722M121 648V701" stroke="#7a9aa7" stroke-width="5" fill="none"/><circle cx="146" cy="644" r="10" fill="#edc5ad"/><path d="M146 658V692L179 702V722M151 661L168 676L'+hand.join(' ')+'" stroke="'+ink+'" stroke-width="8" fill="none" stroke-linecap="round"/><circle cx="168" cy="676" r="5" fill="'+red+'"/><path d="M'+(hand[0]-8)+' '+hand[1]+'H'+(hand[0]+8)+'" stroke="'+accent+'" stroke-width="7"/><text x="300" y="682" font-size="18" fill="'+ink+'">UN BRAZO CADA VEZ</text>';
  }
  return '<g data-detail373="'+kind+'"><rect x="58" y="620" width="604" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="74" y="642" font-size="13" fill="'+accent+'" font-family="sans-serif">'+title+'</text>'+art+'<text x="74" y="720" font-size="12" fill="#b0c6d1" font-family="sans-serif">'+note+'</text></g>';
 };
 root.Motion373={ids,correct};Motion2D18.clearBounds();
})(window);
