/* Cuatro patrones corregidos y propagados a todos sus modelos y variantes. */
(function(root){'use strict';
const R=Rig2D23,cp=R.cp,wave=R.wave,reviews=[];
function bear(t,walking=false){const f=R.standing(),p=f.p,a=walking?Math.sin(t*6.283):0;
 f.p=[[190,395],[245,405],[435,405],[245-10*a,509],[245-22*a,613],[232+10*a,497],[232+22*a,601],[435+18*a,575],[548+18*a,609],[422-18*a,563],[535-18*a,597]];
 f.detail={floor23:627,feet24:{8:[[-1,0],true],10:[[-1,0],true]},stage18:walking?'AVANZA CON BRAZO Y PIERNA CONTRARIOS':'RODILLAS SUSPENDIDAS · RESPIRA',family23:'bear'};return f;
}
function hipflex(){const f=R.standing();f.p=[[350,245],[350,308],[350,465],[355,397],[410,460],[342,385],[397,448],[490,500],[490,650],[267,646],[147,646]];
 f.detail={floor23:665,feet24:{8:[[1,0],true],10:[[-1,0],false]},stage18:'RODILLA POSTERIOR APOYADA · PELVIS ESTABLE',family23:'hipflex'};return f;
}
function triceps(t){const f=R.standing(),a=wave(t),angle=2.4*a;f.p[3]=[346,175];f.p[4]=[346-100*Math.sin(angle),175-100*Math.cos(angle)];f.p[5]=[333,173];f.p[6]=[333-100*Math.sin(angle),173-100*Math.cos(angle)];
 f.p[7]=[357,565];f.p[8]=[357,704];f.p[9]=[340,565];f.p[10]=[340,704];f.loads=[R.mix(f.p[4],f.p[6],.5)];f.detail.stage18=a>.7?'FLEXIONA EL CODO · CARGA DETRÁS DE LA CABEZA':'EXTIENDE · BRAZOS SUPERIORES ESTABLES';return f;
}
function bridge(t,walkout=false,single=false){const f=R.standing(),u=wave(t),a=walkout?Math.floor(u*3)/3:u,p=f.p;
 p[0]=[150,621];p[1]=[210,632];p[2]=[385,525];p[3]=[265,629];p[4]=[335,638];p[5]=[251,617];p[6]=[322,626];
 p[8]=[470+145*a,639];p[10]=[458+145*(walkout?Math.min(1,a+.15*Math.sin(t*6.283)):a),627];
 p[7]=R.ik(p[2],p[8],135,1);p[9]=R.ik([p[2][0]-12,p[2][1]-12],p[10],135,1);
 if(single){p[9]=[455,460];p[10]=[568,488];}
 f.detail={floor23:655,feet24:{8:[[1,0],true],10:[[1,0],!single]},stage18:walkout?'PASOS CORTOS ALTERNOS · CONSERVA LA PELVIS':'ALEJA Y ACERCA LOS TALONES CON CONTROL',family23:'ham-slide',param23:{walkout,single}};return f;
}
for(const e of Engine.exercises.filter(e=>e.variant==='base')){
 const m=MotionDB[e.id],v=m.view21,fam=v.family;let make,note;
 if(['bear','bearcrawl','bearhold'].includes(fam)&&!e.pack23){make=t=>bear(t,e.id==='bearcrawl');m.posture23='prone';v.inset=e.id==='bearhold'?'hold23':null;note='Cuello neutro, manos bajo hombros y rodillas por encima del suelo.';}
 if(fam==='hipflex'){make=hipflex;m.posture23='upright';m.facing23='right';v.mirror=false;v.inset='hold23';note='Perfil hacia el pie anterior, rodilla posterior apoyada y pie posterior extendido.';}
 if(e.id==='pau-overtriceps'){make=triceps;m.posture23='upright';m.facing23='right';v.mirror=false;v.inset=null;note='Perfil coherente: codos quietos, mancuerna detrás de la cabeza y pies sin cruces.';}
 if(fam==='ham-slide'){make=t=>bridge(t,!!e.param23?.walkout,!!e.param23?.single);m.posture23='supine';v.mirror=false;v.inset=null;note='Rodillas por encima de los talones, pelvis elevada, apoyo real de talones.';}
 if(!make)continue;
 v.primary='side';v.label='Lateral';v.project=null;
 m.frames=Array.from({length:48},(_,i)=>{const f=make(i/48);f.detail={...f.detail,view21:'side',reviewed24:true};return f;});
 reviews.push({id:e.id,note,models:1,variants:Engine.exercises.filter(x=>x.baseId===e.id).length});
}
for(const e of Engine.exercises.filter(e=>e.baseId==='home-bridge-walk23')){
 e.instructions=['Túmbate boca arriba, dobla las rodillas y apoya los talones en el suelo.','Eleva la pelvis manteniendo hombros y cabeza apoyados.','Aleja los talones con pasos cortos alternos sin dejar caer la pelvis.','Vuelve con pasos cortos; termina la repetición al recuperar el apoyo inicial.'];e.steps=e.instructions.join(' ');e.setup=e.instructions[0];
}
for(const e of Engine.exercises.filter(e=>e.baseId==='pau-overtriceps')){e.sourceOnly=false;e.variantDescription='Vista lateral propia: los codos permanecen junto a la cabeza mientras el antebrazo baja la carga por detrás.';}
const oldDescribe=Anatomy2D22.describe;
Anatomy2D22.describe=function(e,f,m){const result=oldDescribe(e,f,m);for(const foot of result.feet){const override=f.detail.feet24?.[foot.index];if(override){foot.axis=override[0];foot.grounded=override[1];foot.heel=[foot.origin[0]-7*foot.axis[0],foot.origin[1]-7*foot.axis[1]];foot.toe=[foot.origin[0]+28*foot.axis[0],foot.origin[1]+28*foot.axis[1]];}}return result;};
root.RigCorrections24={reviews,bear,hipflex,triceps,bridge};
})(window);
