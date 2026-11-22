/* Rigs de los patrones nuevos; máquinas y cargas siguen sus puntos de contacto. */
(function(root){'use strict';
const R=Rig2D23,cp=R.cp,mix=R.mix,ik=R.ik,wave=R.wave;
const move=(f,indices,x,y)=>{for(const k of indices){f.p[k][0]+=x;f.p[k][1]+=y;}return f;};
const n=v=>Number(v).toFixed(2),pt=p=>p.map(n).join(','),stage=(f,a)=>{f.detail.stage18=a>.85?'PUNTO DE CONTROL':a<.15?'POSICIÓN INICIAL':'RECORRIDO CONTROLADO';return f;};
function front(){const f=R.standing();f.p=[[360,205],[360,270],[360,435],[308,373],[308,478],[412,373],[412,478],[330,568],[330,704],[390,568],[390,704]];return f;}
function topPush(a,param){const f=R.topTap(0);f.p[0][1]+=10*a;f.p[4]=param.grip==='diamond'?[350,290]:[288,265];f.p[6]=param.grip==='diamond'?[370,290]:param.stagger?[432,340]:[510,270];f.p[3]=[param.grip==='diamond'?295:255,335+15*a];f.p[5]=[param.grip==='diamond'?425:465,335+15*a];f.detail.pushDepth23=a;return f;}
function curtsy(a){const f=front(),p=f.p;p[2]=[350,435+120*a];p[1]=[350,270+120*a];p[0]=[350,205+120*a];p[8]=[300,704];p[10]=[425-185*a,690];p[7]=[300,565+35*a];p[9]=[395-75*a,560+45*a];for(const [el,ha,x]of [[3,4,308],[5,6,405]]){p[el]=[x,373+120*a];p[ha]=[x,478+120*a];}return f;}
function stepFront(a,param){const f=front(),p=f.p;p[2]=[350,460-110*a];p[1]=[350,295-110*a];p[0]=[350,230-110*a];p[8]=[param.cross?450:310,580];p[7]=ik(p[2],p[8],153,-1);p[10]=[470,704-124*a];p[9]=ik(p[2],p[10],153,1);p[3]=[290,400-110*a];p[4]=[290,510-110*a];p[5]=[410,400-110*a];p[6]=[410,510-110*a];f.equipment=[['step',[param.cross?390:255,606,param.cross?510:370,715]]];return f;}
function squat(a,param={}){const f=R.standing(),p=f.p;p[2]=[350-75*a,425+135*a];p[1]=[350+20*a,260+155*a];p[0]=[350+40*a,200+152*a];p[8]=[355,704];p[10]=[340,704];p[7]=ik(p[2],p[8],155,1);p[9]=ik(p[2],p[10],155,1);p[3]=[p[1][0]+45,p[1][1]+58];p[4]=[p[1][0]+20,p[1][1]+25];p[5]=[p[1][0]+32,p[1][1]+64];p[6]=[p[1][0]+8,p[1][1]+32];if(param.bench)f.equipment=[['bench',[430,570,600,715]]];return f;}
function hinge(a){const f=R.standing(),p=f.p;p[2]=[350-60*a,435+35*a];p[1]=[350+90*a,270+120*a];p[0]=[350+135*a,210+140*a];p[7]=[365,570];p[9]=[350,570];p[8]=[335,704];p[10]=[320,704];for(const [elbow,hand,x]of [[3,4,0],[5,6,-15]]){p[elbow]=[p[1][0]+x,p[1][1]+108];p[hand]=[p[1][0]+x,p[1][1]+208];}return f;}
function push(a,param={}){const f=R.standing(),p=f.p,shift=param.dive?40*a:0;p[1]=[245+shift,435+100*a];p[2]=[430+shift,495+50*a];p[8]=[690,585];p[10]=[677,573];p[7]=mix(p[2],p[8],.52);p[9]=mix(p[2],p[10],.52);p[0]=[190+shift,415+100*a];const palms=[[245,610],[230+(param.stagger?65:0),600]];p[4]=palms[0];p[6]=palms[1];p[3]=ik(p[1],p[4],95,-1);p[5]=ik(p[1],p[6],95,-1);f.detail.floor23=627;
 if(param.kneeDrive){p[7]=mix(p[7],[280,550],a);p[8]=[440,600];}
 if(param.dive){p[1]=[245+135*a,435+55*Math.sin(a*Math.PI)];p[2]=[430+40*a,350+185*a];p[0]=[p[1][0]-55,p[1][1]-20];p[7]=mix(p[2],p[8],.5);p[9]=mix(p[2],p[10],.5);p[3]=ik(p[1],p[4],115,-1);p[5]=ik(p[1],p[6],115,-1);}
 if(param.release){p[4][1]-=14*a;p[6][1]-=14*a;}
 return f;}
function bridge(a,param={}){const f=R.standing();f.p=[[160,550],[220,565],[420,565-115*a],[272,590],[335,615],[263,577],[323,603],[545,485],[600,625],[532,472],[587,613]];f.detail.floor23=644;
 if(param.long){f.p[7]=[590,540];f.p[8]=[705,625];f.p[9]=[577,527];f.p[10]=[692,613];}
 return f;}
function reversePlank(a,param={}){const f=bridge(.7),p=f.p;p[1]=[240,435+55*(1-a)];p[2]=[440,515+45*(1-a)];p[0]=[183,410+55*(1-a)];p[3]=[230,540];p[4]=[210,640];p[5]=[217,527];p[6]=[197,627];p[8]=[710,640];p[10]=[697,627];p[7]=mix(p[2],p[8],.52);p[9]=mix(p[2],p[10],.52);f.detail.floor23=655;if(param.knee){p[7]=[555,455];p[8]=[565,600];}if(param.walk){p[4][0]+=30*a;p[8][0]-=35*a;}return f;}
function hollow(a,param={}){const f=bridge(0),p=f.p;f.p[1]=[235,545-45*a];f.p[0]=[175,525-50*a];f.p[2]=[405,590];p[3]=[205,485-40*a];p[4]=[140,435-25*a];p[5]=[200,475-40*a];p[6]=[130,425-25*a];p[7]=[535,565-85*a];p[8]=[665,540-130*a];p[9]=[524,553-85*a];p[10]=[654,528-130*a];
 if(param.tuck){p[7]=mix([475,470],[535,540],a);p[8]=mix([590,550],[665,490],a);p[9]=add23(p[7],[-12,-12]);p[10]=add23(p[8],[-12,-12]);}
 if(param.vup){p[1]=[300,540-115*a];p[0]=[245,520-115*a];p[4]=[520,455-70*a];p[6]=[509,443-70*a];p[3]=ik(p[1],p[4],120,-1);p[5]=ik(p[1],p[6],120,-1);}
 if(param.rock)move(f,[0,1,2,3,4,5,6,7,8,9,10],0,10*Math.sin(a*Math.PI*2));return f;}
function legraise(a,param={},t=0){const f=bridge(0),p=f.p,hip=p[2],angle=-.1-a*1.35;for(const [k,z,offset,amount]of [[7,8,0,param.flutter?wave(t):a],[9,10,-12,param.flutter?wave(t+.5):a]]){const th=param.flutter?-.12-.35*amount:angle;p[k]=[hip[0]+140*Math.cos(th),hip[1]+140*Math.sin(th)+offset];p[z]=[hip[0]+280*Math.cos(th),hip[1]+280*Math.sin(th)+offset];}if(param.scissors){p[8][1]+=30*Math.sin(t*Math.PI*2);p[10][1]-=30*Math.sin(t*Math.PI*2);}return f;}
function bench(a,param={}){const f=bridge(0),p=f.p;p[0]=[185,param.incline?310:param.decline?460:420];p[1]=[250,param.incline?345:param.decline?445:420];p[2]=[445,param.incline?465:param.decline?400:430];p[7]=[530,520];p[8]=[570,704];p[9]=[517,507];p[10]=[557,692];p[4]=[275,p[1][1]-40-155*a];p[6]=[261,p[1][1]-28-155*a];p[3]=ik(p[1],p[4],108,-1);p[5]=ik(p[1],p[6],108,-1);f.equipment=[[param.incline||param.decline?'incline':'bench',[150,450,460,715]]];f.detail.floor23=720;return f;}
function overhead(a,param={}){const f=front(),p=f.p;if(param.seated){p[2][1]=460;p[7]=[295,565];p[8]=[295,704];p[9]=[425,565];p[10]=[425,704];f.equipment=[['bench',[260,488,460,715]]];}p[4]=[300,290-165*a];p[6]=[420,290-165*a];p[3]=[255+35*a,340-120*a];p[5]=[465-35*a,340-120*a];return f;}
function curl(a,param={}){const f=R.standing(),p=f.p;p[3]=[357,370];p[5]=[342,370];p[4]=[357+85*a,478-175*a];p[6]=[342+85*a,466-175*a];if(param.cross)p[4][0]-=45*a;if(param.preacher){p[2]=[360,490];p[7]=[410,585];p[8]=[445,704];p[9]=[395,573];p[10]=[430,692];f.equipment=[['bench',[300,510,435,715]],['table',[370,385,525,715]]];p[3]=[390,365];p[5]=[377,354];p[4]=[480-40*a,452-92*a];p[6]=[466-40*a,440-92*a];}return f;}
function row(a,param={}){const f=hinge(1),p=f.p;p[4]=mix([p[1][0],p[1][1]+205],[p[2][0]+15,p[2][1]+10],a);p[6]=add23(p[4],[-15,-12]);p[3]=ik(p[1],p[4],112,-1);p[5]=ik(p[1],p[6],112,-1);return f;}
function cableRow(a,param={}){const f=R.standing(),p=f.p;if(param.seated){p[2]=[320,515];p[1]=[315,340];p[0]=[315,280];p[7]=[435,575];p[8]=[555,620];p[9]=[422,562];p[10]=[542,607];f.equipment=[['bench',[260,545,370,715]]];}p[4]=[p[1][0]+195-135*a,p[1][1]+(param.high?40:105)];p[6]=[p[4][0]-12,p[4][1]-12];p[3]=ik(p[1],p[4],115,-1);p[5]=ik(p[1],p[6],115,-1);return f;}
function pulldown(a,param={}){const f=front(),p=f.p;p[2]=[360,465];p[7]=[300,565];p[8]=[300,704];p[9]=[420,565];p[10]=[420,704];p[4]=[param.wide?215:300,125+210*a];p[6]=[param.wide?505:420,125+210*a];p[3]=[270-30*a,220+150*a];p[5]=[450+30*a,220+150*a];f.equipment=[['bench',[260,495,460,715]]];return f;}
function fly(a,param={},gym=true){let f;if(!gym){f=front();f.p[0]=[360,220];f.p[1]=[360,285];f.p[2]=[360,465];f.p[7]=[320,555];f.p[8]=[320,690];f.p[9]=[400,555];f.p[10]=[400,690];f.p[3]=[255+70*a,290];f.p[4]=[165+180*a,280];f.p[5]=[465-70*a,290];f.p[6]=[555-180*a,280];f.detail.supineTop23=true;return f;}
 f=front();const p=f.p,y=param.low?370-85*a:param.high?250+105*a:310;if(param.seated){p[2][1]=475;p[7]=[300,565];p[8]=[300,704];p[9]=[420,565];p[10]=[420,704];f.equipment=[['bench',[260,510,460,715]]];}p[3]=[255+65*a,y+15];p[4]=[180+168*a,y];p[5]=[465-65*a,y+15];p[6]=[540-168*a,y];return f;}
function tricep(a,param={}){const f=R.standing(),p=f.p;p[3]=[365,370];p[5]=[352,358];p[4]=[430-35*a,340+120*a];p[6]=[417-35*a,328+120*a];if(param.overhead){p[3]=[360,208];p[5]=[347,196];p[4]=[320+35*a,285-180*a];p[6]=[307+35*a,273-180*a];}return f;}
function seatedLeg(a,param={},ham=false){const f=R.standing(),p=f.p;p[0]=[280,275];p[1]=[285,338];p[2]=[305,510];p[7]=[460,510];p[9]=[450,497];let angle=ham?-.15+1.35*a:1.45-1.4*a;p[8]=[460+145*Math.cos(angle),510+145*Math.sin(angle)];p[10]=[450+145*Math.cos(angle),497+145*Math.sin(angle)];p[3]=[300,440];p[4]=[330,525];p[5]=[287,428];p[6]=[317,513];f.equipment=[['chair',[220,535,465,715]]];return f;}
function legpress(a){const f=R.standing(),p=f.p;p[0]=[210,350];p[1]=[245,400];p[2]=[320,535];const ankle=[585+85*a,490];p[7]=ik(p[2],ankle,190,1);p[8]=ankle;p[9]=add23(p[7],[-12,-12]);p[10]=add23(ankle,[-12,-12]);p[3]=[245,465];p[4]=[287,548];p[5]=[232,453];p[6]=[274,536];f.equipment=[['incline',[150,435,355,710]]];f.detail.footAxis23=[0,-1];f.detail.plate23=ankle;return f;}
function add23(p,delta){return p.map((v,i)=>v+delta[i]);}
function modelFrame(e,t){const a=wave(t),k=e.recipe23,param=e.param23;let f;
 if(k==='push'){
  if(param.tap){const flex=t<.6?wave(t/.6):0;f=push(flex,param);const tap=t>=.6?wave((t-.6)/.4):0;f.p[6]=mix(f.p[6],[260,440],tap);f.p[5]=ik(f.p[1],f.p[6],105,-1);}
  else if(param.power){const flex=t<.7?wave(t/.7):0;f=push(flex,param);const flight=t>=.7?wave((t-.7)/.3):0;move(f,[0,1,2,3,4,5,6,7,9],0,-35*flight);}
  else f=param.grip==='diamond'||param.stagger||param.assistedOne?topPush(a,param):push(a,param);
 }
 else if(k==='pike'){f=push(a,param);f.p[2]=[425,345+28*a];f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=mix(f.p[2],f.p[10],.5);if(param.bench){f.p[8][1]-=95;f.p[10][1]-=95;f.equipment=[['bench',[620,480,735,715]]];}}
 else if(k==='lunge')f=param.cross?curtsy(a):R.lunge(a,!param.forward,!!param.db);
 else if(k==='squat-lunge')f=t<.5?squat(wave(t*2)):R.lunge(wave((t-.5)*2));
 else if(k==='squat'){f=squat(a,param);if(param.hack){f.p[0]=[275-45*a,245+105*a];f.p[1]=[305-45*a,305+105*a];f.p[2]=[365-45*a,455+105*a];f.p[8]=[535,704];f.p[10]=[520,692];f.p[7]=ik(f.p[2],f.p[8],165,1);f.p[9]=ik(f.p[2],f.p[10],165,1);f.p[4]=[f.p[1][0]+28,f.p[1][1]+30];f.p[6]=add23(f.p[4],[-13,12]);f.p[3]=ik(f.p[1],f.p[4],100,1);f.p[5]=ik(f.p[1],f.p[6],100,1);f.detail.hack23=true;f.equipment=[['incline',[150,300,365,715]]];}if(param.back||param.front){f.p[4]=[f.p[1][0]+24,f.p[1][1]+10];f.p[6]=add23(f.p[4],[-15,12]);}}
 else if(k==='single-squat'){f=squat(a,param);if(param.rear){f.p[9]=[f.p[2][0]-95,f.p[2][1]+80];f.p[10]=[f.p[9][0]-40,f.p[9][1]+80];}else{f.p[10]=[f.p[2][0]+295,f.p[2][1]+30];f.p[9]=mix(f.p[2],f.p[10],.5);}if(param.support){f.equipment.push(['wall',[520,270,530,715]]);f.p[6]=[520,400+30*a];f.p[5]=ik(f.p[1],f.p[6],112,-1);}if(param.db){f.p[4]=[f.p[1][0]+100,f.p[1][1]+30];f.p[3]=ik(f.p[1],f.p[4],100,-1);}}
 else if(k==='lateral-lunge'){f=front();move(f,[0,1,2,3,4,5,6],-80*a,110*a);f.p[8]=[180,704];f.p[10]=[560,704];f.p[7]=ik(f.p[2],f.p[8],165,-1);f.p[9]=mix(f.p[2],f.p[10],.5);}
 else if(k==='step')f=stepFront(a,param);
 else if(k==='bridge'||k==='hipthrust'){f=bridge(a,param);if(k==='hipthrust'){f.p[0]=[185,425];f.p[1]=[250,450];f.p[2]=[420,565-110*a];f.p[3]=[300,490];f.p[4]=[f.p[2][0]-10,f.p[2][1]];f.p[5]=[288,478];f.p[6]=add23(f.p[4],[-12,-12]);f.equipment=[['bench',[150,490,300,644]]];}}
 else if(k==='ham-slide'){f=bridge(.75,param);f.p[8][0]+=110*(1-a);f.p[10][0]+=110*(1-a);f.p[7]=ik(f.p[2],f.p[8],155,-1);f.p[9]=ik(f.p[2],f.p[10],155,-1);if(param.single){f.p[9]=[530,400];f.p[10]=[650,350];}}
 else if(k==='reverse-plank'){f=reversePlank(param.knee?1:a,param.knee?{}:param);if(param.knee){for(const [knee,foot,u,o]of [[7,8,Math.max(0,Math.sin(t*6.283)),0],[9,10,Math.max(0,-Math.sin(t*6.283)),-12]]){f.p[knee]=mix(f.p[knee],[f.p[2][0]+105,f.p[2][1]-75+o],u);f.p[foot]=mix(f.p[foot],[f.p[2][0]+150,f.p[2][1]+70+o],u);}}}
 else if(k==='hollow'){f=hollow(param.rock?.65:a,param.rock?{}:param);if(param.rock){const origin=f.p[2],theta=.12*(2*a-1);f.p=f.p.map(p=>{const x=p[0]-origin[0],y=p[1]-origin[1];return [origin[0]+x*Math.cos(theta)-y*Math.sin(theta),origin[1]+x*Math.sin(theta)+y*Math.cos(theta)];});}}
 else if(k==='legraise')f=legraise(param.scissors?.35:a,param,t);
 else if(k==='knee-tuck'){f=seatedLeg(a);f.equipment=[];f.p[2]=[305,590];f.p[1]=[270,430];f.p[0]=[245,370];f.p[4]=[255,610];f.p[6]=[242,598];f.p[3]=[265,525];f.p[5]=[252,513];f.p[7]=[450-55*a,570-95*a];f.p[8]=[585-120*a,602-90*a];f.p[9]=add23(f.p[7],[-12,-12]);f.p[10]=add23(f.p[8],[-12,-12]);f.detail.floor23=628;}
 else if(k==='side-plank'){f=R.sidePlank(true,1);if(param.knee){f.p[9]=mix(f.p[9],[475,405],a);f.p[10]=mix(f.p[10],[575,450],a);}if(param.thread){f.p[6]=mix(f.p[6],[175,555],a);f.p[5]=ik(f.p[1],f.p[6],112,-1);}}
 else if(k==='plank'||k==='renegade'){f=push(0);if(k==='renegade'){const left=Math.max(0,Math.sin(t*6.283)),right=Math.max(0,-Math.sin(t*6.283));for(const [elbow,hand,u,offset]of [[3,4,left,0],[5,6,right,-12]]){f.p[hand]=mix(f.p[hand],[405,430+offset],u);f.p[elbow]=ik(f.p[1],f.p[hand],108,-1);}if(param.knees){f.p[7]=[495,610];f.p[8]=[620,585];f.p[9]=[482,598];f.p[10]=[607,573];}}if(param.saw){f.p[1]=[245+28*a,490];f.p[0]=[190+28*a,470];f.p[2]=[430+28*a,535];f.p[3]=[260,620];f.p[4]=[160,620];f.p[5]=[245,608];f.p[6]=[145,608];f.p[7]=mix(f.p[2],f.p[8],.5);f.p[9]=mix(f.p[2],f.p[10],.5);f.detail.floor23=638;}if(param.toe)f.p[10][0]+=45*a;}
 else if(k==='bear'){f=R.cat(.5);f.p[7][1]-=18;f.p[9][1]-=18;delete f.detail.spineCurve23;if(param.tap){f.p[6]=mix(f.p[6],[250,430],a);f.p[5]=ik(f.p[1],f.p[6],105,-1);}if(param.walk){f.p[4][0]+=35*Math.sin(t*Math.PI*2);f.p[8][0]-=35*Math.sin(t*Math.PI*2);}}
 else if(k==='thruster'){const down=t<1/3?wave(t*1.5):t<2/3?1-wave((t-1/3)*1.5):0,press=t<1/3?0:t<2/3?wave((t-1/3)*1.5):1-wave((t-2/3)*1.5);f=squat(down);f.p[4]=[f.p[1][0]+22,f.p[1][1]+20-170*press];f.p[6]=add23(f.p[4],[-15,12]);f.p[3]=ik(f.p[1],f.p[4],110,-1);f.p[5]=ik(f.p[1],f.p[6],110,-1);}
 else if(k==='clean'){f=hinge(1-a);f.p[4]=mix(f.p[4],[350,275],a);f.p[6]=mix(f.p[6],[338,287],a);f.p[3]=ik(f.p[1],f.p[4],110,1);f.p[5]=ik(f.p[1],f.p[6],110,1);}
 else if(k==='hinge'||k==='deadlift'){f=hinge(a);if(param.airplane){f.p[10]=[f.p[2][0]-160*a,704-255*a];f.p[9]=mix(f.p[2],f.p[10],.5);f.p[6]=[515,450];f.p[5]=ik(f.p[1],f.p[6],110,1);f.equipment=[['wall',[540,300,550,715]]];}if(param.wide){const side=hinge(a);f=front();f.p[0][1]+=135*a;f.p[1][1]+=120*a;f.p[2][1]+=40*a;f.p[8]=[215,704];f.p[10]=[505,704];f.p[7]=[230,560+25*a];f.p[9]=[490,560+25*a];f.p[4]=[305,470+155*a];f.p[6]=[415,470+155*a];f.p[3]=mix(f.p[1],f.p[4],.5);f.p[5]=mix(f.p[1],f.p[6],.5);}if(param.floorStart&&!param.wide){f.p[2][1]+=35*a;f.p[4][1]+=55*a;f.p[6][1]+=55*a;f.p[7]=ik(f.p[2],f.p[8],155,1);f.p[9]=ik(f.p[2],f.p[10],155,1);}}
 else if(k==='row'){f=row(a,param);if(param.floorStart){f.p[2]=[300,445];f.p[1]=[455,445];f.p[0]=[510,420];f.p[4]=mix([445,675],[320,450],a);f.p[6]=add23(f.p[4],[-12,-12]);f.p[3]=ik(f.p[1],f.p[4],122,-1);f.p[5]=ik(f.p[1],f.p[6],122,-1);}}
 else if(k==='bench')f=bench(a,param);
 else if(k==='overhead')f=overhead(a,param);
 else if(k==='curl')f=curl(a,param);
 else if(k==='cable-row')f=cableRow(a,param);
 else if(k==='erg-row'){f=R.standing();const hip=[375-125*a,535],pull=Math.max(0,(a-.55)/.45);f.p[2]=hip;f.p[1]=[hip[0]+10-35*pull,370];f.p[0]=[f.p[1][0],310];f.p[8]=[575,580];f.p[10]=[563,568];f.p[7]=ik(hip,f.p[8],172,1);f.p[9]=ik(hip,f.p[10],172,1);f.p[4]=[f.p[1][0]+208-100*pull,430+15*pull];f.p[6]=add23(f.p[4],[-12,-12]);f.p[3]=ik(f.p[1],f.p[4],112,-1);f.p[5]=ik(f.p[1],f.p[6],112,-1);f.equipment=[['bench',[hip[0]-30,567,hip[0]+40,715]]];}
 else if(k==='pulldown')f=pulldown(a,param);
 else if(k==='fly')f=fly(a,param,e.gymOnly23);
 else if(k==='tricep')f=tricep(a,param);
 else if(k==='hamcurl'&&param.lying){f=push(0);f.p[1]=[245,430];f.p[2]=[430,435];f.p[7]=[570,440];f.p[8]=[570+140*Math.cos(a*1.4),440-140*Math.sin(a*1.4)];f.p[9]=[557,427];f.p[10]=[557+140*Math.cos(a*1.4),427-140*Math.sin(a*1.4)];f.p[4]=[185,455];f.p[6]=[174,444];f.p[3]=[230,485];f.p[5]=[219,474];f.equipment=[['bench',[150,472,610,715]]];}
 else if(k==='leg-extension'||k==='hamcurl')f=seatedLeg(a,param,k==='hamcurl');
 else if(k==='legpress')f=legpress(a);
 else if(k==='calf'){f=R.standing();move(f,[0,1,2,3,4,5,6,7,9],0,-25*a);f.p[8][1]-=18*a;f.p[10][1]-=18*a;}
 else if(k==='hip-machine'){f=front();f.p[2][1]=465;f.p[7]=[315-65*a,570];f.p[8]=[305-65*a,704];f.p[9]=[405+65*a,570];f.p[10]=[415+65*a,704];f.equipment=[['bench',[260,500,460,715]]];}
 else if(k==='machine-press'){f=cableRow(1-a,{seated:true});f.p[7]=[425,515];f.p[8]=[425,704];f.p[9]=[412,503];f.p[10]=[412,692];f.p[4][1]-=50;f.p[6][1]-=50;f.p[3]=ik(f.p[1],f.p[4],110,1);f.p[5]=ik(f.p[1],f.p[6],110,1);}
 else if(k==='lateral'){f=front();f.p[3]=[300-75*a,370-75*a];f.p[4]=[285-145*a,475-175*a];f.p[5]=[420,370];f.p[6]=[435,475];}
 else if(k==='pallof'){f=R.standing();f.p[4]=[395+150*a,350];f.p[6]=add23(f.p[4],[-12,-12]);f.p[3]=ik(f.p[1],f.p[4],110,-1);f.p[5]=ik(f.p[1],f.p[6],110,-1);}
 else if(k==='woodchop'){f=front();f.p[4]=[285+180*a,220+245*a];f.p[6]=add23(f.p[4],[20,0]);f.p[3]=ik([315,270],f.p[4],125,1);f.p[5]=ik([405,270],f.p[6],125,-1);f.detail.footAxis23=[.55*a,1];}
 else if(k==='face-pull'){f=front();f.p[4]=[335-80*a,350-100*a];f.p[6]=[385+80*a,350-100*a];f.p[3]=[290-55*a,345];f.p[5]=[430+55*a,345];}
 else if(k==='straight-pull'){f=R.standing();f.p[4]=[480-70*a,235+225*a];f.p[6]=add23(f.p[4],[-12,-12]);f.p[3]=mix(f.p[1],f.p[4],.52);f.p[5]=mix(f.p[1],f.p[6],.52);}
 else if(k==='landmine'){f=R.standing();f.p[4]=[420+105*a,310-110*a];f.p[3]=ik(f.p[1],f.p[4],112,1);}
 else if(k==='treadmill')f=R.march(Math.max(0,Math.sin(t*Math.PI*2))*.6,Math.max(0,-Math.sin(t*Math.PI*2))*.6);
 else throw Error('No existe rig para '+k);
 if(param.rack){f.p[4]=[f.p[1][0]+20,f.p[1][1]+20];f.p[6]=[f.p[1][0]+7,f.p[1][1]+32];}
 if(param.single&&['cable-row','tricep'].includes(k)){f.p[5]=[f.p[1][0]-15,f.p[1][1]+105];f.p[6]=[f.p[1][0]-15,f.p[1][1]+202];}
 if(e.gymOnly23){f.detail.gear23=param.gear;f.detail.recipe23=k;f.detail.param23=param;f.equipment.push(['bounds23',[80,80,730,735]]);if(param.gear==='cable')f.detail.cable23=k==='pulldown'?[360,90]:param.overhead?[150,690]:['curl','lateral'].includes(k)?[650,690]:k==='cable-row'?[650,param.high?380:445]:[650,240];}
 if(param.db)f.loads=[cp(f.p[4]),cp(f.p[6])];
 if(e.gymOnly23)f.loads=[];
 f.detail.param23=param;f.detail.phase23=t;f.detail.family23=k;f.detail.reviewed23=true;return stage(f,a);
}
const frontRecipes=new Set(['overhead','pulldown','lateral','hip-machine','woodchop','face-pull','lateral-lunge']);
for(const e of Engine.exercises.filter(e=>e.variant==='base'&&e.pack23&&!e.pack30)){
 const m=MotionDB[e.id],v=m.view21,param=e.param23,k=e.recipe23;
 const top=k==='push'&&(param.grip==='diamond'||param.stagger||param.assistedOne),floorFly=k==='fly'&&!e.gymOnly23,primary=top||floorFly?'top':frontRecipes.has(k)||k==='step'||param.cross&&k==='lunge'||param.wide&&k==='deadlift'||k==='fly'&&e.gymOnly23?'front':k==='side-plank'?'lying':'side';
 Object.assign(v,{primary,label:{top:'Superior',front:'Frontal',side:'Lateral',lying:'Frontal · de lado'}[primary],family:k,mirror:false,project:null,inset:top?'pushDepth23':floorFly?'floorFly23':k==='pallof'?'pallof23':param.frog?'frog23':param.pronate?'grip23':param.airplane?'hip23':param.scissors?'scissors23':param.close||param.supinate||param.neutral||param.wide?'grip23':null,reason:primary==='front'?'Observa los dos lados y el recorrido de la carga.':'Observa el apoyo y la relación entre cabeza, pelvis y articulaciones.'});
 m.posture23=k==='hamcurl'&&param.lying?'prone':['push','pike','plank','bear','renegade'].includes(k)?'prone':['bridge','ham-slide','hipthrust','hollow','legraise','bench','reverse-plank'].includes(k)||k==='fly'&&!e.gymOnly23?'supine':k==='side-plank'?'side-lying':'upright';
 m.frames=Array.from({length:48},(_,i)=>{const f=modelFrame(e,i/48);f.detail.view21=primary;return f;});m.hold=false;if(m.posture23==='upright')m.facing23='right';
 Rig2D23.reviews.push({id:e.id,name:e.name,action:'Rig y equipo específicos del patrón '+k,changed:true,view:primary,inset:v.inset,hold:false});
}
function extras(f,accent){let out='',d=f.detail,k=d.recipe23,p=f.p;
 if(d.towel23)out+=`<path data-apparatus23="towel" d="M${pt(p[4])}L${pt(p[8])}L${pt(p[6])}" fill="none" stroke="${accent}" stroke-width="5" stroke-linejoin="round"/>`;
 if(d.gear23==='cable'){
  const anchor=d.cable23,handle=d.param23.single?p[4]:mix(p[4],p[6],.5);
  if(k==='fly'){const y=d.param23.low?690:d.param23.high?120:285;out+=`<g data-apparatus23="dual-cable" fill="none" stroke="#7a9aa7" stroke-width="5"><path d="M80 735V120M650 735V120"/><circle cx="80" cy="${y}" r="12"/><circle cx="650" cy="${y}" r="12"/><path d="M80 ${y}L${pt(p[4])}M650 ${y}L${pt(p[6])}" stroke="${accent}" stroke-width="3"/></g>`;}
  else out+=`<g data-apparatus23="cable" fill="none" stroke="#7a9aa7" stroke-width="5"><path d="M650 735V120H${anchor[0]}V${anchor[1]}"/><circle cx="${anchor[0]}" cy="${anchor[1]}" r="12"/><path d="M${pt(anchor)}L${pt(handle)}" stroke="${accent}" stroke-width="3"/><rect x="665" y="440" width="42" height="190" rx="5"/><path d="${d.param23.single?'M'+(handle[0]-12)+' '+handle[1]+'h24':'M'+pt(p[4])+'L'+pt(p[6])}" stroke-width="8"/></g>`;
 }
 if(['machine','smith'].includes(d.gear23)){out+=`<g data-apparatus23="${d.gear23}" fill="none" stroke="#7a9aa7" stroke-width="7"><path d="M115 735V170M700 735V170M115 170H700M100 735H715"/><rect x="640" y="420" width="42" height="200" rx="5"/></g>`;if(d.plate23)out+=`<path d="M${d.plate23[0]+12} ${d.plate23[1]-55}V${d.plate23[1]+28}" stroke="${accent}" stroke-width="12"/>`;if(d.hack23)out+=`<path d="M230 680L400 210M${p[1][0]-30} ${p[1][1]-10}l60-24" stroke="${accent}" stroke-width="8" fill="none"/>`;if(['leg-extension','hamcurl'].includes(k))out+=`<path d="M${pt(p[8])}L${pt(p[10])}" stroke="#fb927d" stroke-width="18" stroke-linecap="round"/>`;}
 if(d.gear23==='cardio'){out+=`<g data-apparatus23="cardio" stroke="#7a9aa7" stroke-width="8" fill="none"><path d="M150 724H665M645 724V480H560"/><rect x="553" y="440" width="55" height="36" rx="6"/>${k==='erg-row'?`<path d="M170 590H610M595 532V615M630 550L${pt(mix(p[4],p[6],.5))}" stroke="${accent}" stroke-width="3"/>`:`<path d="M155 725H632" stroke="${accent}" stroke-width="3" stroke-dasharray="18 24" stroke-dashoffset="${(d.phase23||0)*126}"/>`}</g>`;}
 return out;
}
function loads(f,view,accent){const d=f.detail,p=f.p;if(!['barbell','smith'].includes(d.gear23))return '';
 let q=mix(p[4],p[6],.5);if(d.param23.back)q=[p[1][0]-8,p[1][1]-14];if(d.param23.front)q=[p[1][0]+20,p[1][1]+20];if(d.recipe23==='hipthrust')q=cp(p[2]);if(d.barCenter31)q=cp(d.barCenter31);
 if(d.param23.landmine)return `<g data-load23="landmine" stroke="#7a9aa7" stroke-width="12"><path d="M650 724L${pt(q)}"/><circle cx="${q[0]}" cy="${q[1]}" r="27" fill="#243d50" stroke="${accent}" stroke-width="3"/></g>`;
 const x1=view==='front'?q[0]-175:q[0]-25,x2=view==='front'?q[0]+175:q[0]+25;
 return `<g data-load23="barbell" stroke="#7a9aa7" fill="#243d50"><path d="M${x1} ${q[1]}H${x2}" stroke-width="9"/><${view==='front'?'rect':'ellipse'} ${view==='front'?`x="${x1}" y="${q[1]-35}" width="15" height="70"`:`cx="${x1}" cy="${q[1]}" rx="16" ry="32"`} stroke="${accent}" stroke-width="3"/><${view==='front'?'rect':'ellipse'} ${view==='front'?`x="${x2-15}" y="${q[1]-35}" width="15" height="70"`:`cx="${x2}" cy="${q[1]}" rx="16" ry="32"`} stroke="${accent}" stroke-width="3"/></g>`;
}
Rig2D23.extras=extras;Rig2D23.loads=loads;Rig2D23.phase=(e,phase)=>{const t=((phase%48)+48)%48/48;return e.renderWarp23==='slow'?(t<.4?t/.4*24:24+(t-.4)/.6*24):e.renderWarp23==='pause'?(t<1/3?t*3*24:t<2/3?24:24+(t-2/3)*3*24):phase;};
})(window);
