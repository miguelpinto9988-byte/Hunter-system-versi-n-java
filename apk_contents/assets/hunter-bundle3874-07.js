/* Recursos por ID: anatomía, aparato, vista y cadencia se resuelven juntos. */
(function(root){'use strict';
const cp=x=>JSON.parse(JSON.stringify(x)),R=Rig2D23,mix=R.mix,wave=R.wave,cache=new Map();
const add=(a,b,k=1)=>a.map((v,i)=>v+b[i]*k),sub=(a,b)=>a.map((v,i)=>v-b[i]),unit=v=>{const d=Math.hypot(...v)||1;return v.map(x=>x/d);};
function ik(a,b,l=140,side=1){return R.ik(a,b,l,side);}
function neutralHead(f){f.p[0]=add(f.p[1],unit(sub(f.p[1],f.p[2])),60);return f;}
function empty(){return {p:cp(R.standing().p),loads:[],equipment:[],detail:{floor23:715}};}
function side(){const f=empty();f.p=[[350,205],[350,265],[350,435],[357,360],[357,455],[344,349],[344,444],[357,567],[357,700],[344,554],[344,687]];return f;}
function front(seated=false){const f=empty();f.p=[[360,205],[360,265],[360,435],[308,360],[308,455],[412,360],[412,455],[330,568],[330,700],[390,568],[390,700]];if(seated){for(const i of [0,1,2,3,4,5,6])f.p[i][1]+=50;f.p[7]=[275,570];f.p[8]=[275,700];f.p[9]=[445,570];f.p[10]=[445,700];f.equipment=[['chair',[260,514,460,715]]];}return f;}
function seated(){const f=side();f.p=[[280,260],[280,320],[280,490],[285,415],[320,492],[272,402],[307,479],[420,490],[420,700],[407,477],[407,687]];f.equipment=[['chair',[230,515,390,715]]];return f;}
function feet(f,a=[1,0],b=a){f.detail.feet30={8:a,10:b};return f;}
function arms(f,ends,l=95,side=1){for(const [k,h,end]of [[3,4,ends[0]],[5,6,ends[1]||add(ends[0],[-13,-13])]]){f.p[h]=end;f.p[k]=ik(f.p[1],end,l,side);}return f;}
function squat(a,p={}){const f=side(),hip=[350-70*a,435+115*a];f.p[2]=hip;f.p[1]=[350+20*a,265+135*a];neutralHead(f);f.p[8]=[365,700];f.p[10]=[352,687];f.p[7]=ik(hip,f.p[8],140,1);f.p[9]=ik(add(hip,[-13,-13]),f.p[10],140,1);arms(f,[[f.p[1][0]+30,f.p[1][1]+20]]);if(p.bench)f.equipment=[['bench',[150,572,325,715]]];if(p.heelWedge)f.equipment.push(['step',[335,710,405,726]]);if(p.hack||p.pendulum||p.vlever){f.p[2]=[340-50*a,445+110*a];f.p[1]=[f.p[2][0]-50,f.p[2][1]-163];neutralHead(f);f.p[8]=[485,695];f.p[10]=[472,682];f.p[7]=ik(f.p[2],f.p[8],150,1);f.p[9]=ik(add(f.p[2],[-13,-13]),f.p[10],150,1);arms(f,[add(f.p[1],[30,25])],95,1);f.detail.hack30=true;}if(p.narrow){f.p[8][0]-=6;f.p[10][0]+=6;}return feet(f);}
function hinge(a,p={}){const f=side(),hip=[350-65*a,435+42*a];f.p[2]=hip;const angle=1.15*a;f.p[1]=[hip[0]+170*Math.sin(angle),hip[1]-170*Math.cos(angle)];neutralHead(f);f.p[8]=[350,700];f.p[10]=[337-(p.staggered?50:0),687];f.p[7]=ik(hip,f.p[8],140,1);f.p[9]=ik(add(hip,[-13,-13]),f.p[10],140,1);arms(f,[[f.p[1][0],f.p[1][1]+188]],95,-1);return feet(f);}
function sumo(a){const f=front(),p=f.p;p[1][1]+=200*a;p[0][1]+=200*a;p[2][1]+=115*a;p[8]=[210,700];p[10]=[510,700];p[7]=ik([335,p[2][1]],p[8],151,-1);p[9]=ik([385,p[2][1]],p[10],151,1);p[3]=[315,p[1][1]+112];p[4]=[315,p[1][1]+225];p[5]=[405,p[1][1]+112];p[6]=[405,p[1][1]+225];f.detail.floor23=735;f.detail.hingeAmount30=a;return feet(f,[-.45,.89],[.45,.89]);}
function lunge(a,p={}){const f=side(),hip=[350,455+110*a];f.p[2]=hip;f.p[1]=[350,hip[1]-170];neutralHead(f);f.p[8]=[470,p.frontElevated?620:700];f.p[10]=[195,p.bench?555:685];f.p[7]=ik(hip,f.p[8],150,1);f.p[9]=ik(hip,f.p[10],150,1);arms(f,[[360,hip[1]+20]],95,-1);if(p.bench)f.equipment=[['bench',[145,578,220,715]]];if(p.frontElevated)f.equipment.push(['step',[445,643,530,715]]);f.detail.rearToe23=10;return feet(f,[1,0],[.9,.45]);}
function push(a,p={},t=0){const f=empty(),ankle=[660,680],angle=.43-.34*a,axis=[Math.cos(angle),Math.sin(angle)];f.p[1]=add(ankle,axis,-430);f.p[2]=add(f.p[1],axis,170);f.p[7]=add(f.p[2],axis,130);f.p[8]=ankle;f.p[9]=add(f.p[7],[-13,-13]);f.p[10]=add(ankle,[-13,-13]);neutralHead(f);const hands=[[270+(p.close?12:0)-(p.wide?12:0),700],[257+(p.stagger?45:0),687]];arms(f,hands,100,1);f.detail.floor23=720;feet(f,[-.3,.95],[-.3,.95]);
 if(p.knees){f.p[7]=[550,699];f.p[9]=[537,686];f.p[2]=mix(f.p[1],f.p[7],.58);f.p[8]=[665,645];f.p[10]=[652,632];neutralHead(f);feet(f,[1,.15],[1,.15]);}
 if(p.bench){const delta=-90;f.p[8][1]+=delta;f.p[10][1]+=delta;f.p[2]=mix(f.p[1],f.p[8],.4);f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=add(f.p[7],[-13,-13]);neutralHead(f);f.equipment=[['bench',[620,612,710,720]]];feet(f,[1,0],[1,0]);}
 if(p.power){const u=t>.72?wave((t-.72)/.28):0;for(const i of [0,1,2,3,4,5,6,7,9])f.p[i][1]-=30*u;}
 if(p.release){const u=a>.9?(a-.9)*10:0;f.p[4][1]-=10*u;f.p[6][1]-=10*u;}
 if(p.tap){const u=t>.6?wave((t-.6)/.4):0;f.p[6]=mix(f.p[6],add(f.p[1],[15,12]),u);f.p[5]=ik(f.p[1],f.p[6],100,1);}
 if(p.kneeDrive){f.p[9]=mix(f.p[9],add(f.p[1],[50,75]),a);f.p[10]=mix(f.p[10],add(f.p[1],[190,95]),a);}
 if(p.scapular){
  // V38.39: escapular movement in a HIGH PLANK, never a deep push-up.
  // Replace the entire base push-up pose with an extended-elbow plank pose.
  const stable=push(0,{...p,scapular:false},t);
  const pts=stable.p;
  pts[1][1]+=15*a;pts[0][1]+=15*a;pts[2][1]+=8*a;
  for(const [el,hand,delta] of [[3,4,[0,0]],[5,6,[-13,-13]]]){
    const shoulder=add(pts[1],delta);
    pts[el]=mix(shoulder,pts[hand],.5);
  }
  for(const [knee,ankle,delta] of [[7,8,[0,0]],[9,10,[-13,-13]]]){
    pts[knee]=mix(add(pts[2],delta),pts[ankle],.5);
  }
  stable.detail.biomechanics3839='scapular-only-elbows-extended';
  return stable;
 }
 return f;
}
function pike(a,p={}){const f=empty();f.p[2]=[390+35*a,365+35*a];f.p[1]=[300+14*a,510+35*a];neutralHead(f);f.p[8]=[640,p.bench?540:700];f.p[10]=[627,p.bench?527:687];f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=add(f.p[7],[-13,-13]);arms(f,[[290,700]],100,1);if(p.bench)f.equipment=[['bench',[605,548,710,720]]];return feet(f,p.bench?[1,0]:[-.6,.8]);}
function plank(t,p={}){let f=push(0,p,t);const a=wave(t);if(p.saw){f.p[3]=[280,700];f.p[4]=[175,700];f.p[5]=[267,687];f.p[6]=[162,687];f.p[1]=[280+18*a,590];f.p[2]=[440+18*a,625];neutralHead(f);f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=add(f.p[7],[-13,-13]);}
 if(p.toe||p.jack){f=topPlank(t,p);}
 if(p.handwalk){f.p[4][0]+=28*a;f.p[8][0]+=28*a;f.p[3]=ik(f.p[1],f.p[4],100,1);}
 if(p.drag){f.p[6]=mix(f.p[6],[340,670],a);f.p[5]=ik(f.p[1],f.p[6],100,1);f.loads=[[340+50*a,705]];}
 return f;}
function topPlank(t,p={}){const f=front(),a=wave(t);f.p=[[360,185],[360,255],[360,440],[305,260],[300,180],[415,260],[420,180],[332,567],[325,700],[388+45*a,567],[395+100*a,700]];if(p.jack){f.p[7][0]-=45*a;f.p[8][0]-=100*a;}f.detail.front19=true;f.detail.support23=true;return feet(f,[0,-1],[0,-1]);}
function renegade(t,p={}){const f=push(0,{knees:p.knees}),a=Math.max(0,Math.sin(t*2*Math.PI)),b=Math.max(0,-Math.sin(t*2*Math.PI));for(const [el,ha,u,dy]of [[3,4,a,0],[5,6,b,-13]]){const end=mix(f.p[ha],[435,570+dy],u);f.p[ha]=end;f.p[el]=ik(f.p[1],end,100,1);}f.loads=[cp(f.p[4]),cp(f.p[6])];return f;}
function sidePlank(t,p={},full=true){const f=empty(),a=wave(t);f.p=[[205,507],[260,520],[425,563],[260,630],[167,630],[275,420],[285,320],[555,598],[685,630],[552,585],[682,617]];if(!full||p.knees){f.p[7]=[550,630];f.p[8]=[653,566];f.p[9]=[547,617];f.p[10]=[650,553];}if(p.hipLift){f.p[2][1]+=35*(1-a);f.p[1][1]+=10*(1-a);f.p[0][1]+=10*(1-a);}if(p.thread){f.p[6]=mix([285,320],[175,597],a);f.p[5]=mix([275,420],[200,548],a);f.detail.turn30=a;}if(p.knee){f.p[9]=mix(f.p[9],[465,470],a);f.p[10]=mix(f.p[10],[555,538],a);}if(p.legLift){f.p[9]=[425+130*Math.cos(.45*a),563-130*Math.sin(.45*a)];f.p[10]=[425+260*Math.cos(.45*a),563-260*Math.sin(.45*a)];}f.detail.floor23=646;return feet(f,[1,0],[1,0]);}
function ankleDrive(a){const f=side();f.p[2]=[360+16*a,465];f.p[1]=[370+18*a,295];neutralHead(f);f.p[8]=[470,700];f.p[10]=[250,687];f.p[7]=ik(f.p[2],f.p[8],140,1);f.p[9]=ik(add(f.p[2],[-13,-13]),f.p[10],140,1);f.p[6]=[555,385];f.p[5]=ik(f.p[1],f.p[6],105,-1);f.p[3]=[380,410];f.p[4]=[380,505];f.equipment=[['wall',[568,225,581,715]]];f.detail.ankle30=a;return feet(f);}
function pressMachine(a,p={}){const f=seated(),s=f.p[1];f.p[4]=[s[0]+60+123*a,s[1]+(p.incline?-32*a:28)];f.p[6]=add(f.p[4],[-13,-13]);f.p[3]=ik(s,f.p[4],96,-1);f.p[5]=ik(add(s,[-13,-13]),f.p[6],96,-1);if(p.single){f.p[5]=[272,415];f.p[6]=[302,492];}f.detail.machine30='press';return feet(f);}
function lift(a,p={},kind='lateral'){const f=front(p.seated),s=f.p[1],left=[s[0]-42,s[1]],right=[s[0]+42,s[1]];for(const [el,ha,start,sign]of [[3,4,left,-1],[5,6,right,1]]){let u=p.single&&sign===1?0:a,th=u*Math.PI/2;f.p[el]=[start[0]+sign*95*Math.sin(th),start[1]+95*Math.cos(th)];f.p[ha]=[start[0]+sign*185*Math.sin(th),start[1]+185*Math.cos(th)];}if(kind==='overhead'){for(const [el,ha,start,sign]of [[3,4,left,-1],[5,6,right,1]]){const u=p.single&&sign===1?0:a;f.p[ha]=[start[0]+sign*(30-15*u),start[1]+5-180*u];f.p[el]=ik(start,f.p[ha],95,-sign);}}return feet(f,[-.08,1],[.08,1]);}
function bridge(a,p={}){const f=empty();f.p=[[140,620],[200,630],[365,620-105*a],[250,645],[320,650],[237,632],[307,637],[465,550],[515,682],[452,537],[502,669]];if(p.long){f.p[8][0]+=85;f.p[10][0]+=85;}for(const [k,z,o]of [[7,8,0],[9,10,-13]])f.p[k]=ik(add(f.p[2],[o,o]),f.p[z],135,1);if(p.single||p.march){const u=p.single?1:a;f.p[9]=mix(f.p[9],[450,445],u);f.p[10]=mix(f.p[10],[550,460],u);}if(p.walkout){const u=80*a;f.p[8][0]+=u;f.p[10][0]+=u;f.p[7]=ik(f.p[2],f.p[8],140,1);f.p[9]=ik(add(f.p[2],[-13,-13]),f.p[10],140,1);}f.detail.floor23=702;if(p.squeeze)f.detail.cushion30=true;return feet(f);}
function thrust(a,p={}){const f=bridge(a,p);f.p[1]=[225,485];f.p[0]=[168,466];f.p[2]=[388,550-75*a];f.p[8]=[515,682];f.p[10]=[502,669];f.p[7]=ik(f.p[2],f.p[8],130,1);f.p[9]=ik(add(f.p[2],[-13,-13]),f.p[10],130,1);if(p.single){f.p[9]=[470,430];f.p[10]=[590,465];}f.equipment=[['bench',[140,516,270,702]]];arms(f,[add(f.p[2],[-10,-5])],105,-1);f.detail.thrust30=true;return f;}
function legpress(a,p={}){const f=seated();f.p[1]=[245,375];f.p[2]=[300,520];neutralHead(f);f.p[8]=[535+90*a,490-65*(p.angle? a:0)];f.p[10]=add(f.p[8],[-13,-13]);f.p[7]=ik(f.p[2],f.p[8],170,1);f.p[9]=ik(add(f.p[2],[-13,-13]),f.p[10],170,1);f.p[3]=[250,467];f.p[4]=[280,545];f.p[5]=[237,454];f.p[6]=[267,532];if(p.single){f.p[9]=[340,650];f.p[10]=[430,690];}f.equipment=[['incline',[180,400,330,720]]];f.detail.plate23=cp(f.p[8]);f.detail.machine30='legpress';return feet(f,[0,-1],p.single?[1,0]:[0,-1]);}
function ham(a,p={}){if(p.lying){const f=push(0);f.p[1]=[240,490];f.p[2]=[410,495];f.p[0]=[180,488];f.p[7]=[545,495];f.p[8]=[545+135*Math.cos(1.3*a),495-135*Math.sin(1.3*a)];f.p[9]=[532,482];f.p[10]=add(f.p[8],[-13,-13]);f.p[4]=[170,520];f.p[6]=[157,507];f.p[3]=[230,565];f.p[5]=[217,552];f.equipment=[['bench',[150,533,600,720]]];f.detail.roller30=cp(f.p[8]);return feet(f,[.7,-.7],[.7,-.7]);}if(p.standing){const f=side();f.p[10]=[344-115*a,687-90*a];f.p[9]=[344,554];f.detail.roller30=cp(f.p[10]);f.p[6]=[535,380];f.p[5]=ik(f.p[1],f.p[6],105,-1);f.equipment=[['wall',[550,265,565,715]]];return feet(f,[1,0],[.6,-.8]);}const f=seated();for(const [k,z,o]of [[7,8,0],[9,10,-13]]){const angle=.2+1.65*a;f.p[k]=[420+o,490+o];f.p[z]=[420+o+145*Math.cos(angle),490+o+145*Math.sin(angle)];}f.detail.roller30=cp(f.p[8]);return feet(f,[1,0]);}
function extension(a){const f=seated();for(const [k,z,o]of [[7,8,0],[9,10,-13]]){const angle=1.5-1.4*a;f.p[k]=[420+o,490+o];f.p[z]=[420+o+145*Math.cos(angle),490+o+145*Math.sin(angle)];}f.detail.roller30=cp(f.p[8]);return feet(f,[1,0]);}
function hipMachine(a,p={}){const f=front(true),u=p.adduct?1-a:a;f.p[7]=[292-45*u,555];f.p[8]=[285-70*u,700];f.p[9]=[428+45*u,555];f.p[10]=[435+70*u,700];f.detail.pads30=true;return feet(f,[-.2,1],[.2,1]);}
function curl(a,p={}){const f=p.seated?seated():side();if(p.preacher)f.equipment.push(['table',[350,420,505,490]]);for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){const angle=-Math.PI/2+2.3*a;f.p[el]=[f.p[1][0]+10+o,f.p[1][1]+95+o];f.p[ha]=[f.p[el][0]+95*Math.cos(angle),f.p[el][1]-95*Math.sin(angle)];}return feet(f);}
function tricep(a,p={}){const f=p.seated?seated():side(),over=p.overhead;for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){f.p[el]=[f.p[1][0]+10+o,f.p[1][1]+(over?-90:95)+o];const angle=over?(.1+2.35*(1-a)):(.25+1.4*a);f.p[ha]=[f.p[el][0]+95*Math.cos(angle),f.p[el][1]+(over?-1:1)*95*Math.sin(angle)];}return feet(f);}
function hipStanding(a,p={},kind='hip-extension'){const f=side();f.p[6]=[535,380];f.p[5]=ik(f.p[1],f.p[6],105,-1);f.equipment=[['wall',[550,265,565,715]]];const angle=(kind==='hip-flexion'?1:-1)*.5*a;f.p[9]=[344+133*Math.sin(angle),435+133*Math.cos(angle)];f.p[10]=[344+266*Math.sin(angle),435+266*Math.cos(angle)];return feet(f);}
function cycle(t,p={}){const f=seated(),th=2*Math.PI*t,center=[450,650];if(p.reclined){f.p[1]=[215,340];f.p[2]=[295,485];neutralHead(f);}for(const [k,z,offset]of [[7,8,0],[9,10,Math.PI]]){f.p[z]=[center[0]+67*Math.cos(th+offset),center[1]+67*Math.sin(th+offset)];f.p[k]=ik(f.p[2],f.p[z],142,1);}arms(f,[[430,425]],100,-1);if(p.air){f.p[4][0]+=40*Math.sin(th);f.p[6][0]-=40*Math.sin(th);f.p[3]=ik(f.p[1],f.p[4],100,-1);f.p[5]=ik(f.p[1],f.p[6],100,-1);}f.detail.bike30={center,air:p.air};return feet(f);}
function poseTrack(t,points){let i=points.findIndex(x=>x[0]>=t);if(i<1)i=1;const a=points[i-1],b=points[i],u=(t-a[0])/(b[0]-a[0]),w=(1-Math.cos(Math.PI*u))/2,f=cp(a[1]);f.p=f.p.map((p,j)=>mix(p,b[1].p[j],w));return f;}
function resolve(e,t,raw){const a=wave(t),p=e.param30||e.param23||{},kind=e.recipe30||e.recipe23||raw.view21.family,id=e.baseId||e.id;let f=null,view=null,posture=null;
 if(['squat','chair','goblet','doublefront'].includes(kind)){f=squat(p.pulses?.7+.3*wave(t*2):a,{...p,bench:p.bench||kind==='chair'});view='side';}
 if(['hinge','rdl','suitdead','deadlift','sumodead'].includes(kind)){f=p.wide?sumo(1-a):hinge(a,p);view=p.wide?'front':'side';}
 if(['split','reverselunge','dblunge','dbsplit','splitpause','bulgarian','lunge'].includes(kind)){f=lunge(a,{...p,bench:p.bench||kind==='bulgarian'});view='side';}
 if(['push','pushup','closepush','kneepush','decline19'].includes(kind)){f=push(a,{...p,close:p.close||kind==='closepush',knees:p.knees||kind==='kneepush',bench:p.bench||kind==='decline19'},t);view='side';posture='prone';
  if(p.grip==='diamond'||p.stagger||p.assistedOne||p.wide){f=topPlank(0,{});f.p[4]=p.grip==='diamond'?[350,215]:p.assistedOne?[275,215]:p.wide?[230,220]:[290,220];f.p[6]=p.grip==='diamond'?[370,215]:p.assistedOne?[565,245]:p.wide?[490,220]:[435,285];f.p[3]=[285-20*a,282+35*a];f.p[5]=[435+20*a,282+35*a];f.detail.pushDepth23=a;view='top';}
  if(p.dive){const phase=t<.5?t*2:(1-t)*2,q=phase<.6?phase/.6:(phase-.6)/.4;const from=phase<.6?pike(0):push(1),to=phase<.6?push(1):push(0);f=cp(from);f.p=from.p.map((point,j)=>mix(point,to.p[j],q));}
 }
 if(['pike','pike19'].includes(kind)){f=pike(a,p);view='side';posture='prone';}
 if(kind==='plank'){f=plank(t,p);view=p.toe||p.jack?'top':'side';posture='prone';if(!e.pack23&&!e.pack30){f.p[1]=[280,599];f.p[2]=[443,633];f.p[0]=[221,587];f.p[3]=[280,700];f.p[4]=[180,700];f.p[5]=[267,687];f.p[6]=[167,687];f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=mix(add(f.p[2],[-13,-13]),f.p[10],.5);}}
 if(kind==='renegade'){f=renegade(t,p);view='side';posture='prone';}
 if(['side-plank','sideplank','full-sideplank19','sideplank-lift19'].includes(kind)){f=sidePlank(t,{...p,hipLift:p.hipLift||kind==='sideplank-lift19'},kind!=='sideplank');view='lying';posture='side-lying';}
 if(kind==='ankledrive'){f=ankleDrive(a);view='side';}
 if(kind==='machine-press'){f=pressMachine(a,p);view='side';}
 if(['lateral','seatedlateral','singlelateral','overhead','overheadpress','seatedpress','singleoverhead'].includes(kind)){f=lift(a,{...p,seated:p.seated||kind.startsWith('seated'),single:p.single||kind.startsWith('single')},kind.includes('overhead')||kind.includes('press')?'overhead':'lateral');view='front';}
 if(['bridge','dbbridge','singlebridge'].includes(kind)){f=bridge(a,{...p,single:p.single||kind==='singlebridge'});view='side';posture='supine';}
 if(kind==='ham-slide'){f=bridge(a,{...p,walkout:true});view='side';posture='supine';}
 if(kind==='hipthrust'){f=thrust(a,p);view='side';posture='supine';}
 if(kind==='legpress'){f=legpress(a,p);view='side';}
 if(kind==='hamcurl'){f=ham(a,p);view='side';posture=p.lying?'prone':'upright';}
 if(['leg-extension','legextend'].includes(kind)){f=extension(a);view='side';}
 if(kind==='hip-machine'){f=hipMachine(a,p);view='front';}
 if(['curl','hammer','seatedcurl','altcurl','althammer'].includes(kind)){f=curl(a,{...p,seated:p.seated||kind==='seatedcurl'});view='side';if(['altcurl','althammer'].includes(kind)){const other=curl(wave(t+.5));f.p[5]=other.p[5];f.p[6]=other.p[6];}if(p.cross){f.p[4][0]-=35*a;f.p[3]=ik(f.p[1],f.p[4],95,-1);}f.detail.grip30=p.pronate?'zottman':/hammer/.test(kind)||p.cross?'neutral':'supinated';}
 if(['frontlift','singlefront','front-seated-reference'].includes(kind)){f=kind==='front-seated-reference'?seated():side();for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){const u=kind==='singlefront'&&o?0:a,th=u*Math.PI/2;f.p[el]=[f.p[1][0]+95*Math.sin(th)+o,f.p[1][1]+95*Math.cos(th)+o];f.p[ha]=[f.p[1][0]+185*Math.sin(th)+o,f.p[1][1]+185*Math.cos(th)+o];}view='side';feet(f);}
 if(kind==='tricep'&&e.gymOnly23){f=tricep(a,p);view='side';}
 if(['hip-extension','hip-flexion'].includes(kind)){f=hipStanding(a,p,kind);view='side';}
 if(['hip-abduction','hip-adduction'].includes(kind)){f=front();const u=kind==='hip-adduction'?1-a:a;f.p[9]=[390+120*Math.sin(.45*u),435+120*Math.cos(.45*u)];f.p[10]=[390+260*Math.sin(.45*u),435+260*Math.cos(.45*u)];view='front';feet(f,[-.05,1],[.1,1]);}
 if(kind==='cycle'){f=cycle(t,p);view='side';}
 if(kind==='squat-lunge'){const stand=squat(0),down=squat(1),step=lunge(0),low=lunge(1);f=poseTrack(t,[[0,stand],[.2,down],[.4,stand],[.5,step],[.7,low],[.86,step],[1,stand]]);view='side';}
 if(kind==='thruster'){const stand=squat(0),down=squat(1),up=squat(0);for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){up.p[ha]=[350+o,115+o];up.p[el]=ik(up.p[1],up.p[ha],95,1);}f=poseTrack(t,[[0,stand],[.3,down],[.55,stand],[.78,up],[1,stand]]);view='side';}
 if(kind==='woodchop'){f=front();f.p[4]=[265+205*a,205+265*a];f.p[6]=add(f.p[4],[20,0]);f.p[3]=mix([278,223],[400,330],a);f.p[5]=mix([360,215],[460,360],a);f.detail.turn30=a;feet(f,[-.1,1],[.1,1]);view='front';}
 if(kind==='calf'&&e.pack30){f=p.seated?seated():p.hinged?hinge(.7):side();for(const i of p.seated?[8,10]:[0,1,2,3,4,5,6,7,8,9,10])f.p[i][1]-=(p.seated?23:20)*a;f.detail.calf30=true;f.detail.calfAmount30=a;view='side';feet(f);}
 if(kind==='bear'&&e.pack30){f=RigCorrections24.bear(t);f.p[6]=mix(f.p[6],[150,415],a);f.p[5]=ik(f.p[1],f.p[6],100,-1);view='side';posture='prone';}
 if(!f&&e.pack30){
  if(kind==='ankle'){f=seated();f.detail.feet='toelift';f.detail.toeAmount=a;view='side';}
  else if(kind==='crunch'||kind==='back-extension'){f=seated();const sign=kind==='crunch'?1:-1;f.p[1]=[280+45*sign*a,320+16*a];neutralHead(f);arms(f,[[f.p[1][0]+35,f.p[1][1]+20]],100,1);if(p.kneeling){f.equipment=[];f.p[7]=[350,700];f.p[8]=[240,700];f.p[9]=[337,687];f.p[10]=[227,687];}view='side';}
  else if(kind==='rotation'){f=front(true);f.detail.turn30=a;f.p[1][0]+=12*(2*a-1);f.p[0][0]+=12*(2*a-1);view='front';}
  else if(kind==='pullup'){f=lift(1-a,{seated:false},'overhead');for(const i of [0,1,2])f.p[i][1]-=80*a;f.p[4]=[250,120];f.p[6]=[470,120];f.p[3]=ik([318,f.p[1][1]],f.p[4],95,-1);f.p[5]=ik([402,f.p[1][1]],f.p[6],95,1);f.p[7]=[330,565-80*a];f.p[9]=[390,565-80*a];f.p[8]=[355,690-80*a];f.p[10]=[415,690-80*a];f.detail.assist30='pull';f.detail.padY30=582-80*a;view='front';}
  else if(kind==='dip'){f=side();for(const i of [0,1,2,7,8,9,10])f.p[i][1]+=40*a-25;arms(f,[[370,440]],100,-1);f.detail.assist30='dip';f.detail.padY30=f.p[7][1]+16;view='side';}
  else if(kind==='hip9090'){f=front();f.p[2]=[360,595];f.p[1]=[360,425];f.p[0]=[360,365];f.p[7]=[240+80*a,650];f.p[8]=[240+125*a,545];f.p[9]=[480-80*a,650];f.p[10]=[580-125*a,650];f.p[3]=[290,505];f.p[4]=[270,650];f.p[5]=[430,505];f.p[6]=[450,650];f.detail.hip9090=true;view='front';}
  else if(kind==='deadbug'){const m=MotionDB.deadbug;f=cp(m.frames[Math.floor(t*48)%48]);if(p.heelSupport){f.p[8][1]=Math.max(f.p[8][1],625);f.detail.floor23=640;}if(p.press)f.detail.opposite23=[wave(t),wave(t+.5)];view='side';posture='supine';}
  else if(kind==='wall-angel'){f=lift(a,{},'overhead');f.equipment=[['wall',[140,160,580,730]]];f.detail.view21='rear';view='rear';}
  else if(kind==='agility'||kind==='step'||kind==='elliptical'){const s=Math.sin(t*Math.PI*2);f=R.march(Math.max(0,s)*.6,Math.max(0,-s)*.6);if(kind==='step'){f.p[2]=[350,430-15*Math.cos(t*Math.PI*4)];f.p[1]=[350,f.p[2][1]-170];neutralHead(f);f.p[8]=[410,635+35*s];f.p[10]=[325,635-35*s];f.p[7]=ik(f.p[2],f.p[8],140,1);f.p[9]=ik(f.p[2],f.p[10],140,1);f.detail.stairs30=true;}if(kind==='elliptical'){f.p[8]=[385+55*s,675+15*Math.cos(t*Math.PI*2)];f.p[10]=[385-55*s,675-15*Math.cos(t*Math.PI*2)];f.p[7]=ik(f.p[2],f.p[8],140,1);f.p[9]=ik(f.p[2],f.p[10],140,1);f.p[4]=[510-40*s,375];f.p[6]=[510+40*s,362];f.p[3]=ik(f.p[1],f.p[4],100,-1);f.p[5]=ik(add(f.p[1],[-13,-13]),f.p[6],100,-1);f.detail.elliptical30=true;}feet(f);view='side';}
  else if(kind==='jump'){f=squat(t<.55?wave(t/.55):0);const flight=t>.55?wave((t-.55)/.45):0;for(const q of f.p){q[0]+=45*flight;q[1]-=48*flight;}view='side';}
  else if(kind==='ski'){f=hinge(.45*a);arms(f,[[470-35*a,195+280*a]],100,-1);f.detail.ski30=true;view='side';}
  else if(kind==='cable-row'||kind==='straight-pull'||kind==='fly'){
   const parent=Engine.byId(e.animationParent30);const m=MotionDB[parent?.baseId||parent?.id]||MotionDB[e.baseId];f=cp(m.frames[Math.floor(t*48)%48]);view=m.view21.primary;if(p.seated&&!f.equipment.some(x=>x[0]==='chair'||x[0]==='bench')){f=kind==='fly'?lift(a,{seated:true}):pressMachine(1-a,{});view=kind==='fly'?'front':'side';}
  }
  else throw Error('Démonstration V30 manquante '+e.id+' '+kind);
 }
 if(!f)return {frame:cp(raw.frames[Math.floor(t*48)%48]),view:raw.view21.primary,posture:Anatomy2D22.posture(raw),corrected:false};
 if(p.rack||kind==='goblet'||kind==='doublefront'){arms(f,[add(f.p[1],[28,25])],95,1);f.loads=kind==='goblet'?[mix(f.p[4],f.p[6],.5)]:[cp(f.p[4]),cp(f.p[6])];}
 if(p.airplane){f.p[9]=mix(f.p[2],[f.p[2][0]-140*a,500-120*a],.5);f.p[10]=[f.p[2][0]-210*a,700-260*a];f.p[6]=[555,400];f.p[5]=ik(f.p[1],f.p[6],105,-1);f.equipment=[['wall',[570,260,582,715]]];f.detail.turn30=a;}
 if(e.loaded23&&!f.loads.length&&!['barbell','smith','machine','cable','cardio'].includes(p.gear))f.loads=p.singleLoad||p.offset?[cp(f.p[4])]:[cp(f.p[4]),cp(f.p[6])];
 if(e.requirements?.includes('db')&&!e.pack30&&!e.pack23&&e.unit!=='sec'&&!f.loads.length)f.loads=[cp(f.p[4]),cp(f.p[6])];
 if(e.gymOnly23){f.detail.gear23=p.gear||'machine';f.detail.recipe23=kind;f.detail.param23=p;if(['barbell','smith'].includes(p.gear)&&p.back)f.detail.barY30=f.p[1][1]+10;if(p.gear==='cable')f.detail.cable23=kind==='hip-flexion'||kind.startsWith('hip-')?[100,700]:kind==='crunch'?[520,150]:['curl','lateral'].includes(kind)?[100,700]:[650,300];}
 if(e.gymOnly23&&!f.equipment.some(x=>x[0]==='bounds23'))f.equipment.push(['bounds23',[95,95,690,735]]);
 if(p.db&&!f.loads.length)f.loads=p.offset||p.singleLoad?[cp(f.p[4])]:[cp(f.p[4]),cp(f.p[6])];
 f.detail.view21=view;return {frame:f,view,posture:posture||'upright',corrected:true};
}
function cadence(e){const name=e.name.toLowerCase(),seconds=e.tempoSeconds18||4,pause=e.renderWarp23==='pause'||e.variant==='pause'||/pausa/.test(name),slow=e.renderWarp23==='slow'||['slow','controlled'].includes(e.variant)||/tres segundos|retorno lento|descenso controlado/.test(name);return{pause,slow,seconds,pauseStart:(1-Math.min(.75,2/seconds))/2,pauseEnd:(1+Math.min(.75,2/seconds))/2,slowSplit:/descenso/.test(name)?Math.min(.8,3/seconds):Math.max(.2,1-3/seconds)};}
function time(e,t){const kind=e.variant||'base',name=e.name.toLowerCase(),c=cadence(e);if(c.pause){if(t<c.pauseStart)return t/c.pauseStart*.5;if(t<c.pauseEnd)return .5;return .5+(t-c.pauseEnd)/(1-c.pauseEnd)*.5;}if(/repetición y media/.test(name)){const points=[[0,0],[.25,.5],[.5,.25],[.7,.5],[1,1]],i=points.findIndex(q=>q[0]>=t),lo=points[Math.max(0,i-1)],hi=points[Math.max(0,i)];return hi[0]===lo[0]?lo[1]:lo[1]+(hi[1]-lo[1])*(t-lo[0])/(hi[0]-lo[0]);}if(c.slow)return t<c.slowSplit?t/c.slowSplit*.5:.5+(t-c.slowSplit)/(1-c.slowSplit)*.5;if(kind==='easy')return t<.5?t*.7:.35+(t-.5)*1.3;if(kind==='short')return t<.6?t/.6:0;return t;}
function get(id){const e=Engine.byId(id);if(!e)throw Error('Ejercicio desconocido '+id);if(!cache.has(id)){
 const parent=e.animationParent30||e.baseId||id,raw=MotionDB[parent]||MotionDB[Engine.byId(parent)?.baseId]||MotionDB[id];if(!raw)throw Error('Modelo fuente ausente '+id);
 const samples=Array.from({length:48},(_,i)=>{const t=time(e,i/48);return root.Motion31?root.Motion31.resolve(e,t,raw,resolve):resolve(e,t,raw);});const first=samples[0],m={...cp(raw),id,name:e.name,specific30:true,hold:raw.hold&&!e.pack30,frames:samples.map(x=>x.frame),view21:{...cp(raw.view21),primary:first.view,label:{side:'Lateral',front:'Frontal',rear:'Posterior',top:'Superior',lying:'Lateral del apoyo'}[first.view],mirror:false},posture23:first.posture,facing23:first.view==='side'&&first.posture==='upright'?'right':first.view==='side'&&first.posture==='prone'?'left':raw.facing23,corrected30:first.corrected};
 if(first.corrected){m.view21.project=null;m.view21.inset=first.view==='top'?(e.recipe23==='push'||e.recipe30==='push'?'pushDepth23':'support23'):m.hold?'hold23':null;if(e.param23?.frog)m.view21.inset='frog23';if(e.param23?.pronate)m.view21.inset='grip23';if(e.param23?.airplane)m.view21.inset='hip23';}
 if(['ankle','toelift','seatedheel','heeltoe'].includes(raw.view21.family)||e.recipe30==='ankle')m.view21.inset='foot30';
 if(e.recipe30==='rotation')m.view21.inset='rotation23';if(e.recipe30==='deadbug'&&e.param30.press)m.view21.inset='opposite23';
 if(e.recipe23==='deadlift'&&e.param23.wide)m.view21.inset='hinge30';
 for(const f of m.frames){f.detail.specific30=id;f.detail.tempo30=e.tempoSeconds18||4;f.detail.stage18=m.hold?'POSTURA · RESPIRA SIN FORZAR':time(e,0)===time(e,1/48)&&e.variant!=='base'?'CONTROL DE CADENCIA':f.detail.stage18;}
 // Reconecta ciclos antiguos que daban un salto al cambiar de subgesto.
 // La media circular solo afecta esos patrones; conserva sus apoyos y vistas.
 if(['pau-armpulses','pau-armcurl','squatjump','lungeknee','y-t-w19'].includes(raw.view21.family)){
  const frames=cp(m.frames);for(let i=0;i<48;i++)for(let j=0;j<11;j++)for(let k=0;k<2;k++)m.frames[i].p[j][k]=[-2,-1,0,1,2].reduce((sum,o)=>sum+frames[(i+o+48)%48].p[j][k],0)/5;
 }
 if(root.Motion31)root.Motion31.configureModel(e,m);
 cache.set(id,m);
 }return {e,base:id,model:cache.get(id)};
}
const oldDescribe=Anatomy2D22.describe;
Anatomy2D22.describe=function(e,f,m){const a=oldDescribe(e,f,m);for(const foot of a.feet){let v=f.detail.feet30?.[foot.index];if(!v)continue;if(f.detail.calf30){foot.toe=[foot.origin[0]+28,foot.index===8?704:691];v=unit(sub(foot.toe,foot.origin));foot.axis=v;foot.heel=add(foot.origin,v,-7);}else{v=unit(v);foot.axis=v;foot.heel=add(foot.origin,v,-7);foot.toe=add(foot.origin,v,28);}const knee=a.knees.find(k=>k.index===(foot.index===8?7:9));if(a.view!=='side')knee.axis=v;}return a;};
function extras(f,accent){if(!f.detail.specific30)return '';const p=f.p,d=f.detail,gear=d.gear23;let s='';const line=(a,b,w=5)=>'<path d="M'+a.join(',')+'L'+b.join(',')+'" fill="none" stroke="#597584" stroke-width="'+w+'" stroke-linecap="round"/>';
 if(gear==='machine'){
  s+=line([115,720],[620,720],8)+line([135,720],[135,180],8);s+='<rect x="98" y="320" width="68" height="280" rx="9" fill="#173341" stroke="#6c8e9c" stroke-width="3"/>';
  for(let i=0;i<8;i++)s+=line([104,350+i*28],[160,350+i*28],2);
  if(d.machine30==='press'){s+=line([150,350],p[4],4)+line([150,337],p[6],3);}
  if(d.plate23)s+=line(add(d.plate23,[d.legPress31?0:20,-55]),add(d.plate23,[d.legPress31?0:20,55]),14)+line([390,715],add(d.plate23,[d.legPress31?0:20,55]),7);
  if(d.roller30)s+='<circle cx="'+d.roller30[0]+'" cy="'+d.roller30[1]+'" r="18" fill="#233d4b" stroke="'+accent+'" stroke-width="4"/>'+line(d.machine30==='ham-standing'?p[9]:[420,490],d.roller30,7);
  if(d.pads30)for(const i of [7,9])s+=line(add(p[i],[-15,0]),add(p[i],[15,0]),16);
  if(d.assist30)s+=line(d.assist30==='pull'?[230,120]:[320,440],d.assist30==='pull'?[490,120]:[420,440],10)+line([235,720],[235,115],6)+line([490,720],[490,115],6)+line([300,d.padY30],[425,d.padY30],16);
  if(d.hack30){s+=line(add(p[1],[-35,10]),add(p[2],[-35,10]),15)+line([170,240],[320,690],8)+line([230,210],[380,675],5)+line([445,712],[550,675],12);}
  if(d.param23?.belt)s+=line(add(p[2],[-20,0]),add(p[2],[20,0]),15)+line(p[2],add(p[2],[0,80]),3)+'<rect x="'+(p[2][0]-20)+'" y="'+(p[2][1]+75)+'" width="40" height="38" rx="4" fill="#2e4d5a"/>';
  if(d.calf30&&d.param23?.seated)s+=line(add(p[7],[-30,-15]),add(p[7],[25,-15]),18)+line([440,690],add(p[7],[25,-15]),8);
 }
 if(gear==='smith')s+=line([p[1][0]-45,130],[p[1][0]-45,720],8)+line([p[1][0]+85,130],[p[1][0]+85,720],8)+line([p[1][0]-70,720],[p[1][0]+110,720],8);
 if(gear==='cable'){const param=d.param23||{},k=d.recipe23,anchor=k==='woodchop'?[100,140]:k==='tricep'?[550,120]:k==='straight-pull'?[620,110]:d.cable23||[650,120];if(k==='fly'){const y=param.low?690:param.high?130:285;s+=line([100,730],[100,110],8)+line([620,730],[620,110],8)+line([100,y],p[4],2)+line([620,y],p[6],2);}else{s+=line([anchor[0],720],[anchor[0],100],8);s+='<circle cx="'+anchor[0]+'" cy="'+anchor[1]+'" r="13" fill="#1d3b4c" stroke="'+accent+'" stroke-width="3"/>';const handle=k?.startsWith('hip-')?p[10]:param.single?p[4]:mix(p[4],p[6],.5);s+=line(anchor,handle,2);if(!param.single&&!k?.startsWith('hip-'))s+=line(p[4],p[6],7);}}
 if(['cable-row','erg-row'].includes(d.recipe23)){s+=line(add(p[8],[-12,10]),add(p[8],[35,-8]),10)+line(add(p[8],[15,2]),[p[8][0]+15,715],6);if(d.param23?.chestSupport)s+=line(add(p[1],[34,35]),add(p[1],[40,125]),15)+line(add(p[1],[40,125]),[p[1][0]+40,715],5);}
 if(d.bike30){const c=d.bike30.center;s+=line([230,715],[600,715],8)+line([280,520],c,7)+line(c,[565,470],7)+'<circle cx="'+c[0]+'" cy="'+c[1]+'" r="85" fill="none" stroke="#496573" stroke-width="8"/>'+line(c,p[8],4)+line(c,p[10],4);}
 if(d.stairs30){for(const i of [8,10])s+=line(add(p[i],[-25,9]),add(p[i],[35,9]),10)+line(add(p[i],[35,9]),[p[i][0]+35,715],5);s+=line([230,715],[560,715],9);}
 if(d.elliptical30){for(const i of [8,10])s+=line(add(p[i],[-15,9]),add(p[i],[35,9]),12);for(const i of [4,6])s+=line(p[i],[p[i][0]-40,715],7);s+=line([215,715],[610,715],10);}
 if(d.ski30){s+=line([600,110],[600,715],8)+line([590,120],p[4],2)+line([610,130],p[6],2)+line([200,715],[640,715],10);}
 if(gear==='cardio'&&!d.bike30&&!d.ski30&&!d.stairs30&&!d.elliptical30)s+=R.extras(f,accent);
 if(d.cushion30)s+='<ellipse cx="460" cy="551" rx="25" ry="13" fill="#60868e"/>';
 return s+(root.Motion31?.extras(f,accent)||'')||'<g data-apparatus30="none"></g>';
}
function inset(kind,f,phase,accent){const v31=root.Motion31?.inset(kind,f,phase,accent);if(v31)return v31;if(kind==='hinge30'){const a=f.detail.hingeAmount30||0,g=hinge(a),origin=g.p[2],q=p=>[185+(p[0]-350)*.18,683+(p[1]-475)*.13];return '<g data-detail30="hinge"><rect x="58" y="620" width="604" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="75" y="642" font-size="13" fill="'+accent+'" font-family="sans-serif">DE LADO · BISAGRA DE CADERA</text><path d="'+[[0,1],[1,2],[2,7],[7,8],[1,3],[3,4]].map(([i,j])=>'M'+q(g.p[i]).join(',')+'L'+q(g.p[j]).join(',')).join('')+'" fill="none" stroke="#dceaf0" stroke-width="6" stroke-linecap="round"/><text x="75" y="720" font-size="12" fill="#b0c6d1" font-family="sans-serif">Barra cerca de las piernas · columna estable · rodillas siguen las puntas</text></g>';}if(kind!=='foot30')return null;const a=f.detail.toeAmount??f.detail.amount??wave(phase/48),angle=-.5+a,turn=angle*180/Math.PI;return '<g data-detail30="foot"><rect x="58" y="620" width="604" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="75" y="642" font-size="13" fill="'+accent+'" font-family="sans-serif">AMPLIACIÓN · TOBILLO Y PUNTA</text><path d="M155 652V683" stroke="#dceaf0" stroke-width="13" stroke-linecap="round"/><g transform="rotate('+turn+' 155 683)"><path d="M150 683H197" stroke="#dceaf0" stroke-width="14" stroke-linecap="round"/></g><circle cx="155" cy="683" r="5" fill="'+accent+'"/><path d="M209 660q22 22 0 43m-5-6 5 6 5-5" fill="none" stroke="'+accent+'" stroke-width="3"/><text x="75" y="720" font-size="12" fill="#b0c6d1" font-family="sans-serif">La rodilla permanece estable · el movimiento se produce en el tobillo</text></g>';}
const loaded30=new Set(),loading30=new Map();
function preload(id){if(!root.fetch||loaded30.has(id))return Promise.resolve();if(loading30.has(id))return loading30.get(id);const work=fetch('demos30/'+id+'.json').then(r=>{if(!r.ok)throw Error('Modelo local ausente');return r.json();}).then(m=>{if(m.id!==id||m.frames?.length!==48||!m.specific30||root.Motion31&&m.motionRevision31!==31)throw Error('Modelo local incompleto');cache.set(id,m);loaded30.add(id);}).catch(()=>{}).finally(()=>loading30.delete(id));loading30.set(id,work);return work;}
root.Motion30={get,resolve,time,cadence,extras,inset,cache,preload,loaded:loaded30,count:()=>Engine.exercises.length,clear:()=>cache.clear(),version:30};
})(window);

;
/* Correcciones V31 compartidas por todas las fichas de cada familia. */
(function(root){'use strict';
const cp=x=>JSON.parse(JSON.stringify(x)),mix=Rig2D23.mix,wave=Rig2D23.wave;
const add=(a,b,k=1)=>a.map((v,i)=>v+b[i]*k),sub=(a,b)=>a.map((v,i)=>v-b[i]),unit=v=>{const d=Math.hypot(...v)||1;return v.map(x=>x/d);};
const ik=Rig2D23.ik;
function head(f){f.p[0]=add(f.p[1],unit(sub(f.p[1],f.p[2])),60);}
function arm(f,end,other=null,length=95,bend=-1){
 for(const [el,ha,offset,tip]of [[3,4,[0,0],end],[5,6,[-13,-13],other||add(end,[-13,-13])]]){
  const origin=add(f.p[1],offset);f.p[ha]=tip;f.p[el]=ik(origin,tip,length,bend);
 }
}
function feet(f,a=[1,0],b=a){f.detail.feet30={8:a,10:b};}
function blank(){return {p:cp(Rig2D23.standing().p),loads:[],equipment:[],detail:{floor23:715}};}
const kindOf=(e,raw)=>e.recipe30||e.recipe23||raw.view21.family;
const squatKinds=new Set(['squat','chair','goblet','doublefront']);
function squatArms(f,p,kind,a){
 const s=f.p[1];
 if(p.back){
  for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){f.p[el]=[s[0]-55+o,s[1]+70+o];f.p[ha]=[s[0]+8+o,s[1]+12+o];}
  f.loads=[];f.detail.barY30=s[1]+12;f.detail.barCenter31=[s[0]+8,s[1]+12];
 }else if(p.front||p.rack||kind==='goblet'||kind==='doublefront'){
  arm(f,add(s,[48,42]),null,95,-1);
  f.loads=kind==='goblet'?[mix(f.p[4],f.p[6],.5)]:p.gear==='barbell'?[]:[cp(f.p[4]),cp(f.p[6])];
 }else if(p.db||p.offset||p.belt||p.hack||p.pendulum||p.vlever){
  arm(f,add(s,[15,185]),null,95,-1);
  if(p.db||p.offset)f.loads=p.offset?[cp(f.p[4])]:[cp(f.p[4]),cp(f.p[6])];
 }else{
  // De brazos relajados a alcance frontal; nunca se doblan hacia la nuca.
  const th=.08+1.42*a;
  for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){
   f.p[el]=[s[0]+95*Math.sin(th)+o,s[1]+95*Math.cos(th)+o];
   f.p[ha]=[s[0]+185*Math.sin(th)+o,s[1]+185*Math.cos(th)+o];
  }
  f.loads=[];
 }
 f.detail.squatArms31=true;f.detail.squatAmount31=a;
}
function wallPush(a,p={}){
 const f=blank(),ankle=[345,700],s=[432+65*a,335];
 f.p[1]=s;f.p[2]=mix(s,ankle,.39);head(f);
 f.p[8]=ankle;f.p[10]=add(ankle,[-13,-13]);f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=mix(add(f.p[2],[-13,-13]),f.p[10],.5);
 arm(f,[548,350],[548,337],100,-1);f.equipment=[['wall',[559,220,571,720]]];feet(f);return f;
}
function calfStretch(){
 const f=blank();f.p[2]=[350,490];f.p[1]=[395,330];head(f);
 f.p[8]=[465,700];f.p[10]=[230,687];f.p[7]=ik(f.p[2],f.p[8],140,1);f.p[9]=mix(add(f.p[2],[-13,-13]),f.p[10],.5);
 arm(f,[548,365],[548,352],100,-1);f.equipment=[['wall',[559,220,571,720]]];feet(f);return f;
}
function towel(){
 const f=blank();f.p=[[215,286],[225,345],[252,515],[276,435],[342,421],[263,422],[329,408],[414,515],[570,515],[401,502],[557,502]];
 f.detail.floor23=538;f.detail.towel31=true;f.detail.towel23=false;feet(f,[0,-1]);return f;
}
function openBook(a){
 const f=blank();f.p=[[175,600],[235,600],[420,615],[238,688],[241,776],[245,688],[245,776],[510,680],[598,653],[497,667],[585,640]];
 const th=Math.PI*a;f.p[5]=[245+88*Math.sin(th),600+88*Math.cos(th)];f.p[6]=[245+176*Math.sin(th),600+176*Math.cos(th)];
 f.detail.openBook31=a;f.detail.floor23=802;feet(f,[1,0]);return f;
}
function crossStep(a){
 const f=blank(),rise=130*a,x=350+64*a;f.p[2]=[x,480-rise];f.p[1]=[x,310-rise];head(f);
 // La pierna que cruza se ve acortada por la perspectiva frontal.
 f.p[8]=[435,602];f.p[7]=[365+60*a,535-55*a];
 f.p[10]=[300+80*a,700-98*a];f.p[9]=[335+32*a,580-80*a];
 f.p[3]=[x-56,405-rise];f.p[4]=[x-56,493-rise];f.p[5]=[x+56,405-rise];f.p[6]=[x+56,493-rise];
 f.equipment=[['step',[390,616,510,715]]];f.detail.cross31=a;feet(f,[-.08,1],[.08,1]);return f;
}
function standingHam(a){
 const f=blank(),k=[337,567],th=Math.PI/2+1.4*a;
 f.p[10]=[k[0]+140*Math.cos(th),k[1]+140*Math.sin(th)];f.p[9]=k;f.p[8]=[357,700];f.p[7]=[357,563];head(f);
 f.p[4]=[535,362];f.p[3]=ik(f.p[1],f.p[4],105,-1);f.p[5]=[337,370];f.p[6]=[337,465];
 f.equipment=[['wall',[548,260,560,715]]];f.detail.roller30=add(f.p[10],[-12,-8]);f.detail.machine30='ham-standing';
 feet(f,[1,0],[Math.sin(th),-Math.cos(th)]);return f;
}
function kneelingCrunch(a){
 const f=blank();f.p[2]=[340,550];f.p[1]=[323+68*a,388+45*a];head(f);
 f.p[7]=[405,700];f.p[8]=[245,700];f.p[9]=[392,687];f.p[10]=[232,687];
 arm(f,add(f.p[1],[28,-24]),null,95,-1);f.detail.spineCurve23=18*a;feet(f,[1,0]);return f;
}
function resolve(e,t,raw,previous){
 const old=previous(e,t,raw),p=e.param30||e.param23||{},kind=kindOf(e,raw),a=wave(t);let f=old.frame,view=old.view,posture=old.posture,changed=false;
 if(squatKinds.has(kind)){
  if(p.gear==='smith'){
   f.p[1][0]=355;head(f);f.detail.smith31=true;
  }
  squatArms(f,p,kind,p.pulses?.7+.3*wave(t*2):a);changed=true;
 }
 // Conserva apoyos unilaterales, salto y aparato de estas variantes.
 if(['single-squat19','single-squat-support19','squatjump'].includes(kind)&&view==='side'){
  squatArms(f,p,kind,a);changed=true;
 }
 if(kind==='wall'||kind==='wallplank'){
  f=wallPush(kind==='wallplank'?0:a,p);view='side';posture='upright';changed=true;
 }
 if(kind==='calfstretch'){f=calfStretch();view='side';posture='upright';changed=true;}
 if(kind==='towelrow'){f=towel();view='side';posture='upright';changed=true;}
 if(kind==='thoracic'){f=openBook(a);view='lying';posture='side-lying';changed=true;}
 if(kind==='step'&&p.cross){f=crossStep(a);view='front';posture='upright';changed=true;}
 if(kind==='hamcurl'&&p.standing){f=standingHam(a);view='side';posture='upright';changed=true;}
 if(['bridge','dbbridge','singlebridge'].includes(kind)){
  feet(f,[-1,0]);
  if(kind==='dbbridge'||p.db||e.requirements?.includes('db')){
   arm(f,add(f.p[2],[-7,-21]),null,100,-1);f.loads=[add(f.p[2],[-7,-21])];f.detail.pelvisLoad31=true;
  }
  changed=true;
 }
 if(kind==='legpress'){
  // Planta contra la plataforma; el pie libre se mantiene apartado.
  f.detail.plate23=add(f.p[8],[8,0]);if(p.single){f.p[9]=[365,641];f.p[10]=[425,700];feet(f,[0,-1],[1,0]);}
  f.detail.legPress31=true;changed=true;
 }
 if(kind==='back-extension'||kind==='crunch'&&p.seated){arm(f,add(f.p[1],[38,64]),null,95,-1);changed=true;}
 if(kind==='crunch'&&p.kneeling){f=kneelingCrunch(a);view='side';posture='upright';changed=true;}
 if(kind==='machine-press'){
  // La segunda mano queda en el asa de apoyo de la variante unilateral.
  if(p.single){f.p[5]=[267,414];f.p[6]=[295,489];}
  f.detail.press31=true;changed=true;
 }
 if(kind==='side-plank'&&p.thread){f.detail.thread31=a;changed=true;}
 if(kind==='plank'&&(p.toe||p.jack)){f.detail.toeSide31=a;changed=true;}
 // Recupera detalles de carga/aparato después de reemplazar una pose.
 if(e.gymOnly23){f.detail.gear23=p.gear||'machine';f.detail.recipe23=kind;f.detail.param23=p;
  if(!f.equipment.some(x=>x[0]==='bounds23'))f.equipment.push(['bounds23',[95,95,690,735]]);
 }
 f.detail.view21=view;f.detail.revision31=31;f.detail.cycle31=t;f.detail.amount31=a;
 if(changed)f.detail.corrected31=kind;
 return {frame:f,view,posture,corrected:old.corrected||changed};
}
function configureModel(e,m){
 const kind=kindOf(e,m),p=e.param30||e.param23||{},f=m.frames[0];m.motionRevision31=31;
 if(kind==='wall'||kind==='wallplank'||kind==='calfstretch')m.facing23='right';
 if(kind==='thoracic')m.view21.inset='openbook31';
 if(kind==='towelrow')m.view21.inset='towel31';
 if(kind==='plank'&&(p.toe||p.jack))m.view21.inset='toes31';
 if(kind==='step'&&p.cross)m.view21.inset='cross31';
 if(kind==='side-plank'&&p.thread)m.view21.inset='thread31';
 if(kind==='machine-press')m.view21.inset='press31';
 if(kind==='crunch'&&p.kneeling)m.view21.inset='crunch31';
 if(squatKinds.has(kind))m.view21.inset='squat31';
 if(kind==='dbbridge'||f.detail.pelvisLoad31)m.view21.inset='pelvis31';
 if(kind==='hamcurl'&&p.standing)m.view21.inset='ham31';
 if(kind==='calfstretch')m.view21.inset='calf31';
}
function extras(f,accent){
 const d=f.detail,p=f.p;let s='';
 if(d.towel31){const q=[p[8][0],p[8][1]-16];s+=`<g data-apparatus31="towel"><path d="M${p[4]}L${q}L${p[6]}" fill="none" stroke="${accent}" stroke-width="6" stroke-linecap="round"/><path d="M${q[0]} ${q[1]-10}q17 12 0 23" fill="none" stroke="${accent}" stroke-width="5"/></g>`;}
 // La barra se dibuja una sola vez por el renderizador de cargas.
 if(d.press31)s+=`<path data-apparatus31="backrest" d="M${p[1][0]-37} ${p[1][1]-20}L${p[2][0]-30} ${p[2][1]-10}" stroke="#527585" stroke-width="21" stroke-linecap="round"/>`;
 return s;
}
function panel(title,note,art,accent,kind){return `<g data-detail31="${kind}" data-view="side-inset"><rect x="58" y="620" width="604" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="74" y="642" font-size="13" fill="${accent}" font-family="sans-serif">${title}</text>${art}<text x="74" y="720" font-size="12" fill="#b0c6d1" font-family="sans-serif">${note}</text></g>`;}
function inset(kind,f,phase,accent){
 const d=f.detail,a=d.amount31??wave(phase/48),ink='#dceaf0',red='#fb927d';let title,note,art;
 if(kind==='towel31'){
  title='TOALLA · TENSIÓN SIN MOVER EL TRONCO';note='Tira hacia el torso y respira; los pies y la espalda quedan estables.';
  const breath=wave(phase/48),u=6*breath;art=`<path d="M96 682H182L202 673V700L182 682" fill="none" stroke="${accent}" stroke-width="5"/><path d="M${123-u} 665L${108-u} 675l15 7M${188+u} 665l15 10-15 7" fill="none" stroke="${red}" stroke-width="4"/><circle cx="269" cy="680" r="${10+8*breath}" fill="none" stroke="${accent}" stroke-width="3"/><text x="298" y="686" fill="${ink}" font-size="17">RESPIRA · SOSTÉN</text>`;
 }else if(kind==='openbook31'||kind==='thread31'){
  title=kind==='openbook31'?'VISTA SUPERIOR · ABRE EL PECHO':'VISTA SUPERIOR · PASA EL BRAZO Y ABRE';
  note=kind==='openbook31'?'Las rodillas siguen juntas; gira el pecho sin forzar el cuello.':'Sostén la pelvis; el brazo libre pasa bajo el torso y vuelve a abrir.';
  const u=kind==='openbook31'?d.openBook31:d.thread31,th=Math.PI*(u??a);
  art=`<path d="M155 657V698" stroke="${ink}" stroke-width="20" stroke-linecap="round"/><circle cx="155" cy="648" r="9" fill="#edc5ad"/><path d="M155 665L211 665M155 666L${155+56*Math.cos(th)} ${666-20*Math.sin(th)}" stroke="${red}" stroke-width="8" stroke-linecap="round"/><path d="M112 653Q155 625 207 653" fill="none" stroke="${accent}" stroke-width="3" stroke-dasharray="5 4"/><path d="M142 702L137 717M168 702L173 717" stroke="${ink}" stroke-width="8"/><text x="258" y="682" font-size="17" fill="${ink}">PELVIS ESTABLE</text>`;
 }else if(kind==='toes31'){
  title='PIERNAS · PUNTA LATERAL SIN MOVER LAS MANOS';note='La punta sale al lado y vuelve; mantén la cadera a la misma altura.';
  const u=d.toeSide31??a;art=`<circle cx="94" cy="665" r="9" fill="#edc5ad"/><path d="M107 672L164 685L220 697M116 674V701H130" fill="none" stroke="${ink}" stroke-width="9"/><path d="M164 685L${197+25*u} ${692-8*u}L${220+31*u} 699" fill="none" stroke="${red}" stroke-width="7"/><path d="M260 656V692M290 656L${290+38*u} 692" fill="none" stroke="${accent}" stroke-width="5"/><text x="353" y="684" font-size="17" fill="${ink}">IDA Y VUELTA</text>`;
 }else if(kind==='cross31'){
  title='APOYO CRUZADO · VISTA DE LAS HUELLAS';note='Apoya todo el pie; sube con esa pierna y baja sin impulso.';
  const u=d.cross31??a;art=`<rect x="92" y="651" width="154" height="49" fill="#173746" stroke="#648491"/><ellipse cx="203" cy="676" rx="10" ry="19" fill="${red}"/><ellipse cx="${129+54*u}" cy="${710-35*u}" rx="10" ry="19" fill="${ink}"/><path d="M134 706Q147 680 188 676" fill="none" stroke="${accent}" stroke-width="3" stroke-dasharray="4 3"/><text x="286" y="682" font-size="17" fill="${ink}">PIE ENTERO EN LA PLATAFORMA</text>`;
 }else if(kind==='squat31'){
  title='DE LADO · RODILLAS Y TALONES';note='Baja caderas y rodillas juntas; conserva el talón y el apoyo de la carga.';
  const u=d.squatAmount31??a;art=`<path d="M99 704H230" stroke="#638592" stroke-width="3"/><path d="M136 ${654+14*u}L${160-23*u} ${676+9*u}L186 679L180 700H200" fill="none" stroke="${ink}" stroke-width="8" stroke-linecap="round"/><path d="M213 660V693m-5-6 5 6 5-6" stroke="${accent}" stroke-width="3" fill="none"/><text x="277" y="683" font-size="17" fill="${ink}">TALÓN APOYADO</text>`;
 }else if(kind==='pelvis31'){
  title='PUENTE · LA CARGA SE SUJETA SOBRE LA PELVIS';note='Sube la pelvis con el abdomen activo; la cabeza permanece apoyada.';
  art=`<circle cx="100" cy="692" r="8" fill="#edc5ad"/><path d="M110 692L165 ${695-24*a}L191 669L211 701H196" fill="none" stroke="${ink}" stroke-width="9"/><path d="M155 ${686-24*a}H175" stroke="${red}" stroke-width="11"/><path d="M238 698V664m-6 8 6-8 6 8" stroke="${accent}" stroke-width="3" fill="none"/>`;
 }else if(kind==='ham31'){
  title='RODILLA · EL MUSLO PERMANECE ESTABLE';note='Acerca el talón hacia atrás; no balancees la pelvis ni arquees la espalda.';
  const th=Math.PI/2+1.4*a;art=`<path d="M160 654V678L${160+32*Math.cos(th)} ${678+32*Math.sin(th)}" fill="none" stroke="${ink}" stroke-width="9" stroke-linecap="round"/><circle cx="160" cy="678" r="5" fill="${red}"/><path d="M197 686Q187 667 170 653" fill="none" stroke="${accent}" stroke-width="3"/>`;
 }else if(kind==='calf31'){
  title='ESTIRAMIENTO · TALÓN TRASERO APOYADO';note='Sostén sin rebotes; la pierna posterior queda larga y las manos al frente.';
  art=`<path d="M99 705H232M195 650V700" stroke="#648491" stroke-width="3"/><path d="M153 652L149 674L113 701H130M149 674L176 681L176 701H194M156 655L190 661" fill="none" stroke="${ink}" stroke-width="7"/><circle cx="155" cy="642" r="8" fill="#edc5ad"/><circle cx="118" cy="701" r="${8+4*a}" fill="none" stroke="${accent}" stroke-width="3"/>`;
 }else if(kind==='press31'){
  title='PRESS · ASA A LA ALTURA DEL PECHO';note='Empuja y regresa sin despegar el tronco; evita encoger los hombros.';
  art=`<path d="M116 652V698H148V718M102 652V700" fill="none" stroke="${ink}" stroke-width="9"/><circle cx="116" cy="642" r="9" fill="#edc5ad"/><path d="M118 659L${127+18*a} ${682-18*a}L${139+33*a} 663" fill="none" stroke="${red}" stroke-width="7"/><path d="M192 664h37m-6-5 6 5-6 5" fill="none" stroke="${accent}" stroke-width="3"/>`;
 }else if(kind==='crunch31'){
  title='ABDOMEN · ACERCA COSTILLAS Y PELVIS';note='La pelvis queda estable; la cuerda acompaña la flexión, sin tirar del cuello.';
  art=`<path d="M${128+21*a} ${650+14*a}Q${129+25*a} 674 137 685L150 705H112" fill="none" stroke="${ink}" stroke-width="9"/><circle cx="${122+21*a}" cy="${639+14*a}" r="8" fill="#edc5ad"/><path d="M180 647Q205 667 183 684m-1-7 1 7 7-3" fill="none" stroke="${accent}" stroke-width="3"/>`;
 }else return null;
 return panel(title,note,art,accent,kind);
}
function wrapped(text,max=44,count=3){const words=text.split(/\s+/),rows=[''];for(const word of words){const i=rows.length-1;if(rows[i]&&(rows[i]+' '+word).length>max)rows.push(word);else rows[i]+=(rows[i]?' ':'')+word;}while(rows.length<count)rows.push('');if(rows.length>count)throw Error('Explicación demasiado larga para el detalle: '+text);return rows;}
function reflowInset(markup){
 if(!markup)return '';const textMatches=[...markup.matchAll(/<text\b([^>]*)>([^<]*)<\/text>/g)],title=textMatches.find(m=>/y="642"/.test(m[1])),note=textMatches.find(m=>/y="71[9]|y="720"/.test(m[1]));
 if(!title||!note)return markup;
 let art=markup.replace(/^<g\b[^>]*>/,'').replace(/<\/g>$/,'').replace(/<rect\b[^>]*\/?>/,'').replace(title[0],'').replace(note[0],'');
 art=art.replace(/font-size="1[23]"/g,'font-size="20"');
 const text=(match,y,size,max,count)=>{const fill=match[1].match(/fill="([^"]+)"/)?.[1]||'#b0c6d1';return `<text x="76" y="${y}" font-size="${size}" fill="${fill}" font-family="sans-serif">`+wrapped(match[2],max,count).map((row,i)=>`<tspan x="76" dy="${i?size+3:0}">${row}</tspan>`).join('')+'</text>';};
 return '<g data-detail-layout31="complete"><rect x="58" y="620" width="604" height="288" rx="14" fill="#102431" stroke="#47606e"/>'+text(title,655,23,40,2)+'<g transform="translate(0 50)">'+art+'</g>'+text(note,818,24,44,3)+'</g>';
}
root.Motion31={resolve,configureModel,extras,inset,reflowInset,wrapped,kindOf,version:31};
Motion30.clear();Motion2D18.clearBounds();
})(window);

;
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

;
/* Ocho fichas autorizadas por captura. Las variantes conservan su modelo V36. */
(function(root){'use strict';
 const ids=new Set(['dbbridge','plankreach19','home-thruster-db23','home-clean-db23','home-rack-lunge23','gym-landmine-press23','gym-pullassist30','home-hopforward30']);
 const before=Motion30.get,cache=new WeakMap(),copy=x=>JSON.parse(JSON.stringify(x));
 const add=(a,b)=>a.map((v,i)=>v+b[i]),mix=(a,b,t)=>a.map((v,i)=>v+(b[i]-v)*t),ik=Rig2D23.ik;
 const ease=x=>(1-Math.cos(Math.PI*Math.max(0,Math.min(1,x))))/2;
 function track(t,keys){let i=keys.findIndex(k=>k[0]>=t);if(i<=0)return keys[0][1];const lo=keys[i-1],hi=keys[i];return mix(lo[1],hi[1],ease((t-lo[0])/(hi[0]-lo[0])));}
 function head(p){const dx=p[1][0]-p[2][0],dy=p[1][1]-p[2][1],d=Math.hypot(dx,dy);p[0]=[p[1][0]+60*dx/d,p[1][1]+60*dy/d];}
 function rack(f,u=0){
  for(const [el,hand,o]of [[3,4,0],[5,6,-13]]){const s=add(f.p[1],[o,o]);f.p[hand]=add(s,[42-22*u,-7-181*u]);f.p[el]=ik(s,f.p[hand],100,-1);}
  f.loads=[copy(f.p[4]),copy(f.p[6])];
 }
 function correct(e,source){
  if(!ids.has(e.id)||source.motionRevision37===37)return source;
  if(cache.has(source))return cache.get(source);
  const m=copy(source),id=e.id;
  if(id==='plankreach19'){m.view21={...m.view21,primary:'side',label:'Lateral',project:null,inset:'reach37',reason:'Mano bajo el hombro y un brazo libre cada vez.'};m.posture23='prone';m.facing23='left';}
  if(id==='gym-pullassist30')m.view21.inset='pull37';
  if(id==='gym-landmine-press23')m.view21.inset='landmine37';
  for(let i=0;i<m.frames.length;i++){
   const f=m.frames[i],p=f.p,t=i/48,a=(1-Math.cos(t*2*Math.PI))/2;
   f.detail.correction37=id;f.detail.cycle37=t;
   if(id==='dbbridge'){
    p[0]=[140,684];p[1]=[200,684];p[2]=[365-10*a,665-59*a];p[8]=[515,700];p[10]=[502,687];
    for(const [el,ha,knee,ankle,o]of [[3,4,7,8,0],[5,6,9,10,-13]]){
     const shoulder=add(p[1],[o,o]);p[ha]=add(p[2],[-12+o,-20+o]);p[el]=ik(shoulder,p[ha],88,-1);p[knee]=ik(add(p[2],[o,o]),p[ankle],135,1);
    }
    f.loads=[add(p[2],[-7,-18])];f.detail.floor23=715;f.detail.feet30={8:[1,0],10:[1,0]};
   }else if(id==='plankreach19'){
    p[1]=[210,490];p[2]=[380,562];head(p);p[8]=[640,672];p[10]=[627,659];p[7]=mix(p[2],p[8],.5);p[9]=mix(add(p[2],[-13,-13]),p[10],.5);
    const near=t<.5?Math.sin(t*2*Math.PI)**2:0,far=t>=.5?Math.sin(t*2*Math.PI)**2:0;
    for(const [el,ha,u,o]of [[3,4,near,0],[5,6,far,-13]]){const s=add(p[1],[o,o]),angle=Math.PI/2+2.05*u;p[el]=add(s,[95*Math.cos(angle),95*Math.sin(angle)]);p[ha]=add(s,[190*Math.cos(angle),190*Math.sin(angle)]);}
    f.loads=[];f.equipment=[];f.detail.front19=false;f.detail.topShoulder19=false;f.detail.support23=false;f.detail.floor23=688;f.detail.feet30={8:[-.3,.95],10:[-.3,.95]};f.detail.reach37=near+far;
    f.detail.stage18=near>.15?'ALCANZA · APOYO EN LA OTRA MANO':far>.15?'ALTERNA · PELVIS ESTABLE':'DOS MANOS BAJO LOS HOMBROS';
   }else if(id==='home-thruster-db23'){
    const depth=track(t,[[0,[0]],[.3,[1]],[.55,[0]],[1,[0]]])[0];p[2]=[350-70*depth,420+130*depth];p[1]=add(p[2],[170*Math.sin(.55*depth),-170*Math.cos(.55*depth)]);head(p);p[8]=[365,700];p[10]=[352,687];p[7]=ik(p[2],p[8],140,1);p[9]=ik(add(p[2],[-13,-13]),p[10],140,1);const u=track(t,[[0,[0]],[.55,[0]],[.78,[1]],[1,[0]]])[0];rack(f,u);
    f.detail.stage18=t<.5?'SENTADILLA · CARGA EN LOS HOMBROS':u>.1?'EXTIENDE Y EMPUJA SOBRE LA CABEZA':'REGRESA LA CARGA A LOS HOMBROS';
   }else if(id==='home-rack-lunge23'){
    rack(f);f.detail.stage18='ZANCADA · MANCUERNAS EN LOS HOMBROS';
   }else if(id==='home-clean-db23'){
    const v=track(t,[[0,[315,500,.58,0]],[.28,[350,420,0,0]],[.48,[350,468,0,1]],[.65,[350,420,0,1]],[.8,[350,420,0,1]],[1,[315,500,.58,0]]]);
    p[2]=[v[0],v[1]];p[1]=add(p[2],[170*Math.sin(v[2]),-170*Math.cos(v[2])]);head(p);
    p[8]=[375,700];p[10]=[362,687];p[7]=ik(p[2],p[8],140,1);p[9]=ik(add(p[2],[-13,-13]),p[10],140,1);
    for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){const s=add(p[1],[o,o]);p[ha]=add(s,mix([8,192],[42,-7],v[3]));p[el]=ik(s,p[ha],100,-1);}
    f.loads=[copy(p[4]),copy(p[6])];f.detail.floor23=715;f.detail.feet30={8:[1,0],10:[1,0]};f.detail.stage18=v[3]>.9?'RECIBE EN LOS HOMBROS · CODOS DELANTE':t<.4?'EXTIENDE CADERAS · CARGA CERCA DEL CUERPO':'DESCIENDE CON CONTROL';
   }else if(id==='gym-landmine-press23'){
    p[1]=[350,250];p[2]=[350,420];head(p);p[8]=[375,700];p[10]=[292,687];p[7]=ik(p[2],p[8],140,1);p[9]=ik(add(p[2],[-13,-13]),p[10],140,1);
    const angle=1.06+.28*a;p[4]=[650-530*Math.cos(angle),715-530*Math.sin(angle)];p[3]=ik(p[1],p[4],105,-1);p[5]=[325,392];p[6]=[330,480];
    f.loads=[];f.equipment=[['bounds23',[95,95,690,735]]];f.detail.floor23=715;f.detail.feet30={8:[1,0],10:[1,0]};f.detail.stage18='EMPUJA EN ARCO · SUJETA EL EXTREMO';
   }else if(id==='gym-pullassist30'){
    const rise=160*a;p[1]=[360,290-rise];p[2]=[360,460-rise];head(p);p[4]=[250,105];p[6]=[470,105];p[3]=ik([318,p[1][1]],p[4],100,1);p[5]=ik([402,p[1][1]],p[6],100,-1);
    p[7]=[330,595-rise];p[9]=[390,595-rise];p[8]=[334,637-rise];p[10]=[394,637-rise];f.loads=[];f.detail.padY30=608-rise;f.detail.feet30={8:[0,1],10:[0,1]};f.detail.stage18='MANOS FIJAS · RODILLAS EN LA PLATAFORMA';
   }else if(id==='home-hopforward30'){
    const v=track(t,[[0,[0,500,0,.2,1.8]],[.18,[0,548,0,.3,2.4]],[.34,[30,420,0,.05,-.4]],[.5,[75,360,85,.08,-.2]],[.64,[110,545,0,.3,.6]],[.8,[110,420,0,0,1.65]],[1,[0,500,0,.2,1.8]]]);
    p[2]=[320+v[0],v[1]];p[1]=add(p[2],[170*Math.sin(v[3]),-170*Math.cos(v[3])]);head(p);
    const walk=t>.8?Math.sin((t-.8)/.2*2*Math.PI):0;p[8]=[365+v[0]+10*walk,700-v[2]-12*Math.max(0,walk)];p[10]=[352+v[0]-10*walk,687-v[2]-12*Math.max(0,-walk)];
    p[7]=ik(p[2],p[8],140,1);p[9]=ik(add(p[2],[-13,-13]),p[10],140,1);
    for(const [el,ha,o]of [[3,4,0],[5,6,-13]]){const s=add(p[1],[o,o]),angle=v[4];p[el]=add(s,[95*Math.cos(angle),95*Math.sin(angle)]);p[ha]=add(s,[185*Math.cos(angle),185*Math.sin(angle)]);}
    f.loads=[];f.detail.floor23=715;f.detail.feet30={8:[1,0],10:[1,0]};f.detail.stage18=t<.25?'PREPARA · CADERAS Y RODILLAS':t<.4?'IMPULSO HACIA DELANTE':t<.6?'VUELO · BRAZOS AL FRENTE':t<.8?'ATERRIZA SUAVE · FLEXIONA':'REGRESO CAMINANDO';
   }
  }
  m.motionRevision37=37;cache.set(source,m);return m;
 }
 Motion30.get=function(id){const x=before(id);return {...x,model:correct(x.e,x.model)};};
 const extras=Motion30.extras,loads=Rig2D23.loads,inset=Motion30.inset;
 const line=(a,b,w=6)=>'<path d="M'+a.join(',')+'L'+b.join(',')+'" fill="none" stroke="#7a9aa7" stroke-width="'+w+'" stroke-linecap="round"/>';
 Motion30.extras=function(f,accent){
  if(f.detail.correction37==='gym-landmine-press23'){
   const q=f.p[4],plate=mix(q,[650,715],.06);return '<g data-apparatus37="landmine">'+line([650,715],q,10)+'<circle cx="'+plate[0]+'" cy="'+plate[1]+'" r="24" fill="#243d50" stroke="'+accent+'" stroke-width="3"/><circle cx="650" cy="715" r="13" fill="#243d50" stroke="#7a9aa7" stroke-width="4"/></g>';
  }
  if(f.detail.correction37==='gym-pullassist30')return '<g data-apparatus37="assisted-pullup">'+line([130,725],[600,725],8)+line([180,725],[180,100],7)+line([540,725],[540,100],7)+line([180,105],[540,105],10)+line([298,f.detail.padY30],[422,f.detail.padY30],18)+line([500,f.detail.padY30],[500,720],5)+'</g>';
  return extras(f,accent);
 };
 Rig2D23.loads=function(f,...args){if(f.detail.correction37==='gym-landmine-press23')return '';return loads(f,...args);};
 Motion30.inset=function(kind,f,phase,accent){
  const id=f.detail.correction37;if(!ids.has(id)||!['reach37','pull37','landmine37'].includes(kind))return inset(kind,f,phase,accent);
  let title,note,art;
  if(kind==='reach37'){
   title='VISTA SUPERIOR · ALTERNA UN BRAZO';note='Una mano sostiene el cuerpo; alcanza con la otra sin girar la pelvis.';
   const t=f.detail.cycle37,near=t<.5?Math.sin(t*2*Math.PI)**2:0,far=t>=.5?Math.sin(t*2*Math.PI)**2:0;
   art='<path d="M155 664V699M145 701L137 717M165 701L173 717" stroke="#dceaf0" stroke-width="9" fill="none"/><circle cx="155" cy="651" r="9" fill="#243d50"/>'+line([140,667],[140-9*near,667-30*near],7)+line([170,667],[170+9*far,667-30*far],7);
  }else if(kind==='pull37'){
   title='DE LADO · RODILLAS SOBRE EL APOYO';note='El apoyo acompaña el ascenso. Los pies quedan libres detrás de las rodillas.';
   const rise=12*(1-Math.cos(phase/48*2*Math.PI))/2;art='<path d="M95 643H220M117 648V718" stroke="#7a9aa7" stroke-width="4" fill="none"/><circle cx="157" cy="'+(660-rise)+'" r="8" fill="#edc5ad"/><path d="M157 '+(671-rise)+'V'+(690-rise)+'L180 '+(701-rise)+'H198M180 '+(701-rise)+'L166 '+(715-rise)+'" stroke="#dceaf0" stroke-width="8" fill="none"/><path d="M171 '+(708-rise)+'H202" stroke="'+accent+'" stroke-width="6"/>';
  }else{
   title='BARRA ANCLADA · AGARRE CONTINUO';note='La mano sigue el arco de la barra. El anclaje permanece fijo en el suelo.';
   const a=(1-Math.cos(phase/48*2*Math.PI))/2,q=[154+22*a,679-14*a];art=line([215,716],q,5)+'<circle cx="215" cy="716" r="6" fill="'+accent+'"/><circle cx="'+q[0]+'" cy="'+q[1]+'" r="7" fill="#edc5ad"/>';
  }
  return '<g data-detail37="'+kind+'"><rect x="58" y="620" width="604" height="113" rx="14" fill="#102431" stroke="#47606e"/><text x="74" y="642" font-size="13" fill="'+accent+'" font-family="sans-serif">'+title+'</text>'+art+'<text x="74" y="720" font-size="12" fill="#b0c6d1" font-family="sans-serif">'+note+'</text></g>';
 };
 root.Motion37={ids,correct,version:37};Motion2D18.clearBounds();
})(window);

;
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

;
/* Todo el catálogo participa en grupos, bloques y rotación semanal. */
(function(root){'use strict';
const E=Engine,T=TrainingProgression19,O=OwnRoutines18,oldReasons=Adapt.reasons,oldMake=E.makeCycle,oldRecommend=AdaptiveV9.recommend;
for(const e of E.exercises){e.sourceOnly=false;e.demoPath30='demos30/'+e.id+'.svg';e.modelPath30='demos30/'+e.id+'.json';e.planFamily30=e.group==='Cardio'?'cardio':e.group==='Movilidad'?'mobility':['Piernas','Cadera'].includes(e.group)?'legs':e.group==='Abdomen'?'core':['Pecho','Hombros'].includes(e.group)?'push':'pull';}
const tier=e=>e.progressionTier23??e.progressionTier19??T.tier(e.id),groups={full:['Piernas','Cadera','Pecho','Espalda','Hombros','Brazos','Abdomen'],push:['Pecho','Hombros'],pull:['Espalda','Brazos'],legs:['Piernas','Cadera'],core:['Abdomen'],cardio:['Cardio'],mobility:['Movilidad'],control:['Piernas','Cadera','Pecho','Espalda','Hombros','Brazos','Abdomen']};
Adapt.reasons=function(e,p,strict=true){const out=oldReasons(e,p,strict);if(e&&p&&tier(e)>T.level(p)&&!O.gentle(p)&&!out.includes('Variante fuera del escalón seleccionado'))out.push('Variante fuera del escalón seleccionado');return [...new Set(out)];};
Adapt.allowed=(e,p)=>!!e&&!Adapt.reasons(e,p).length;
const inFamily=(e,f)=>f==='power'?e.family==='power'||e.impact==='Con saltos':f==='balance'?e.group==='Movilidad'||/equilibrio|a una pierna/.test(e.name.toLowerCase()):f==='agility'?e.group==='Cardio'&&/paso|agilidad|patinador|coordin|lateral|cambio/.test(e.name.toLowerCase()):f==='seated'?e.seated:f==='intervals'?e.group==='Cardio':(groups[f]||groups.full).includes(e.group);
function pool(p,f='full'){return E.exercises.filter(e=>inFamily(e,f)&&tier(e)<=T.level(p)&&Adapt.allowed(e,p)&&(p.equipment==='gym'?e.gymOnly23:!e.gymOnly23));}
function select(list,count,rotation=0,p=null){if(!list.length)return [];const buckets=[...new Set(list.map(e=>e.group))],used=new Set(),out=[];
 for(let i=0;i<count;i++){let candidates=list.filter(e=>!used.has(e.baseId)&&e.group===buckets[i%buckets.length]);if(!candidates.length)candidates=list.filter(e=>!used.has(e.baseId));if(!candidates.length)break;
  const targetTier=p?T.level(p):Math.max(...candidates.map(tier)),high=candidates.filter(e=>tier(e)===targetTier);if(high.length)candidates=high;
  if(p){const goalBest=Math.max(...candidates.map(e=>GoalContext24.score(e,p))),relevant=candidates.filter(e=>GoalContext24.score(e,p)>=goalBest-1);if(relevant.length)candidates=relevant;}
  candidates.sort((a,b)=>a.id.localeCompare(b.id));const pick=candidates[(Math.abs(rotation)+Math.floor(i/buckets.length)*3)%candidates.length];out.push(pick);used.add(pick.baseId);
 }return out;
}
function dose(e,p,kind){const base=Training23.dose(e,p,kind);return {...base,rest:T.rest(p)};}
function choose(profile,kind,rotation=0){const p=GoalContext24.adjusted(profile);if(!Adapt.mode(p).auto)return [];const f=kind==='cardio'?'cardio':kind==='recovery'?'mobility':p.focus==='lower'?'legs':p.focus==='core'?'core':p.focus==='upper'?'upper':'full';let list=f==='upper'?[...pool(p,'push'),...pool(p,'pull')]:pool(p,f);if(!list.length&&kind==='recovery'&&p.equipment==='gym')list=pool(p,'cardio');if(!list.length&&kind==='strength')list=pool(p,'full');const count=kind==='recovery'?3:p.trainingMode==='routines'?5:p.dailyMissions||4;return select(list,count,rotation,p).map(e=>dose(e,p,kind));}
const oldRoutines=Catalog.routines.map(r=>({...r}));
for(const r of oldRoutines){const p={age:30,equipment:r.gymOnly23?'gym':r.requirements.includes('db')?'db':'body',gymEquipment23:Object.keys(Training23.gymEquipment),bench:true,step:true,difficulty:O.keys[r.level],experience:'regular',injuries:[],restrictions:{},health:{pain:'none'},trainingScale:1};const f=r.gymOnly23?r.family.includes('lower')||r.family.includes('glutes')?'legs':r.family.includes('upper')?'full':r.family.includes('push')?'push':r.family.includes('pull')?'pull':r.family.includes('core')?'core':r.category==='Cardio'?'cardio':'full':r.family;const picked=select(pool(p,f),r.items.length,oldRoutines.indexOf(r)+17,p);if(picked.length>=Math.min(3,r.items.length)){r.items=picked.map(e=>dose(e,p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength'));r.requirements=[...new Set(picked.flatMap(e=>e.requirements||[]))];r.gymRequirements23=[...new Set(picked.flatMap(e=>e.gymRequirements23||[]))];r.minutes=O.estimate(r.items);}r.planVersion=30;}
// Colecciones completas, no fichas aisladas: todas las 1000 variantes quedan
// en un bloque secuencial de una rutina correspondiente al lugar y nivel.
const coverage=[];let serial=0;
for(const place of ['home','gym'])for(let n=0;n<3;n++)for(const family of ['push','pull','legs','core','cardio','mobility']){
 const unordered=E.exercises.filter(e=>!!e.gymOnly23===(place==='gym')&&tier(e)===n&&e.planFamily30===family).sort((a,b)=>a.id.localeCompare(b.id)),chunks=[];
 while(unordered.length){const used=new Set(),chunk=[];for(let k=0;k<unordered.length&&chunk.length<5;){if(used.has(unordered[k].baseId)){k++;continue;}const e=unordered.splice(k,1)[0];chunk.push(e);used.add(e.baseId);}chunks.push(chunk);}
 for(const [i,chunk]of chunks.entries()){const p={age:30,equipment:place==='gym'?'gym':chunk.some(e=>e.requirements.includes('db'))?'db':'body',gymEquipment23:Object.keys(Training23.gymEquipment),bench:true,step:true,difficulty:O.keys[n],experience:'regular',injuries:[],restrictions:{},health:{pain:'none'},trainingScale:1};if(chunk.length<3){for(const e of pool(p,family)){if(chunk.some(x=>x.baseId===e.baseId))continue;chunk.push(e);if(chunk.length>=3)break;}}const items=chunk.map(e=>dose(e,p,family==='cardio'?'cardio':family==='mobility'?'recovery':'strength'));coverage.push({id:'collection30-'+(++serial),name:({push:'Empuje y hombros',pull:'Espalda y brazos',legs:'Piernas y cadera',core:'Estabilidad abdominal',cardio:'Cardio y coordinación',mobility:'Movilidad y control'})[family]+' · '+O.names[n]+' · '+(i+1),category:family==='cardio'?'Cardio':family==='mobility'?'Movilidad':'Fuerza',level:n,items,requirements:place==='gym'?[]:[...new Set(chunk.flatMap(e=>e.requirements))],gymOnly23:place==='gym',gymRequirements23:[...new Set(chunk.flatMap(e=>e.gymRequirements23||[]))],family:place==='gym'?'gym-'+family:family,goalGroups:family==='mobility'?['movement','habit']:family==='cardio'?['fitness','athletic','habit']:['build','fitness','athletic','habit'],extension:'standard',impact:chunk.some(e=>e.impact==='Con saltos')?'Con saltos':'Sin saltos',minutes:O.estimate(items),planVersion:30,description:'Sesión original secuencial con cardio inicial y final, series y descansos. Cada bloque usa un patrón distinto dentro de esta sesión.',collection30:true});}
}
Catalog.routines=[...oldRoutines,...coverage];O.routines.splice(0,O.routines.length,...Catalog.routines.filter(r=>!r.gymOnly23));Training23.gymRoutines.splice(0,Training23.gymRoutines.length,...Catalog.routines.filter(r=>r.gymOnly23));Catalog.baseRoutines=Catalog.routines.length;
function adjust(r,p){return r.items.map(x=>({...x,...dose(E.byId(x.id),p,r.category==='Cardio'?'cardio':r.category==='Movilidad'?'recovery':'strength')}));}
O.adjust=adjust;Training23.adjust=adjust;Training23.pool=pool;Training23.choose=choose;Schedule.items=choose;
for(const id of Catalogue30.bases)T.chains.push([id,id+'-ecc30',id+'-hold30']);
// Puentes de dificultad solo entre gestos que comparten apoyos y patrón.
T.chains.push(['gym-leg-press23','gym-presssingle30']);T.chains.push(['gym-chest-machine23','gym-pressone30']);T.chains.push(['bridge','home-bridgemarch30','home-thrustsingle30']);T.chains.push(['plank','home-plankstep30','home-plankdrag30']);
function assign(c,profile,logs={},now=E.dateKey(),last=null){const p=GoalContext24.adjusted(profile),planningKey=JSON.stringify([30,profile,GoalContext24.personal()]),review=c.reviews?.at(-1);if(!Adapt.mode(p).auto||review?.action==='pausar')return 0;if(c.engineVersion30===30&&c.planningKey30===planningKey&&!(review?.action==='reducir'&&!c.reductionApplied30))return 0;let changed=0;
 for(const [i,d]of c.days.entries()){if(d.date<now||d.kind==='rest'||d.customized||d.routineId||Weekly.active(logs[d.date])||d.tasks?.some(t=>t.done))continue;let items=null;
  if(p.trainingMode==='routines'){const goal=GoalContext24.interpret(p),matches=Catalog.routines.filter(r=>r.level===T.level(p)&&!!r.gymOnly23===(p.equipment==='gym')&&r.extension==='standard'&&r.goalGroups.some(g=>goal.priorities.includes(g))&&Adapt.routineCompatible(r,p));let list=matches.filter(r=>d.kind==='cardio'?r.category==='Cardio':d.kind==='recovery'?r.category==='Movilidad':r.category==='Fuerza');if(!list.length&&d.kind==='strength')list=matches;if(list.length){const r=list[(i+c.index*7)%list.length];items=adjust(r,p);d.title=r.name;d.autoTemplateId=r.id;}}
  if(!items?.length){items=choose(p,d.kind,i+c.index*11);d.title='Misiones personales · '+O.names[T.level(p)];}
  if(!items.length)continue;
  // Repite hasta dos movimientos de referencia de la semana anterior. Los
  // accesorios rotan por la biblioteca; las referencias aportan registros
  // comparables para que la progresión no dependa de ejercicios siempre nuevos.
  if(last&&d.kind==='strength'&&!O.gentle(p)&&T.level(last.profile||p)===T.level(p)){
   const validGroups=new Set(items.map(x=>E.byId(x.id).group)),used=new Set(),anchors=[],referenceSets={};
   for(const x of last.days.filter(x=>x.kind==='strength').flatMap(x=>x.items||[])){const e=E.byId(x.id);if(!e||used.has(e.baseId)||!validGroups.has(e.group)||!Adapt.allowed(e,p)||tier(e)>T.level(p))continue;anchors.push(dose(e,p,'strength'));referenceSets[e.id]=x.sets;used.add(e.baseId);if(anchors.length>=Math.min(2,items.length))break;}
   if(anchors.length){items=[...anchors,...items.filter(x=>!used.has(E.byId(x.id).baseId))].slice(0,items.length);d.referenceIds30=anchors.map(x=>x.id);d.referenceSets30=referenceSets;}
  }
  const old={days:[{items:d.items||[]}]};items=items.map(x=>{let carried=T.carry(T.carry(x,last,p),old,p);if(d.referenceIds30?.includes(carried.id)&&!O.gentle(p)){carried={...carried,sets:d.referenceSets30?.[carried.id]||carried.sets};return T.applyRecommendation(carried,AdaptiveV9.recommend(logs,carried,p,now),p);}return carried;});
  if(c.reviews?.at(-1)?.action==='reducir')items=items.map(x=>({...x,target:Math.max(E.unitOf(x)==='sec'?10:3,Math.floor(x.target*.8))}));
  d.items=items;d.tasks=[];d.goalIntent24=GoalContext24.interpret(p).reason;d.planningNote='Selección del catálogo completo según objetivo, equipo, nivel y restricciones. Revisión semanal con series confirmadas.';changed++;
 }c.engineVersion30=30;c.planningKey30=planningKey;if(review?.action==='reducir')c.reductionApplied30=true;return changed;
}
E.makeCycle=function(p,start,index,last,logs={}){const c=oldMake(p,start,index,last,logs);assign(c,p,logs,start,last);return c;};
Training23.assign=assign;
const previousCurrent=currentCycle;
currentCycle=function(){const c=previousCurrent();if(!c||!state.profile)return c;const key=JSON.stringify([30,state.profile,GoalContext24.personal()]);if(c.planningKey30!==key){assign(c,state.profile,state.logs,E.dateKey(),state.cycles.filter(x=>x.start<c.start).at(-1));c.planningKey30=key;save();}return c;};
root.Training30={pool,select,choose,assign,adjust,tier,coverage,version:30};
})(window);

