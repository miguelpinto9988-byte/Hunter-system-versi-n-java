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
