/* Hunter System 38.75 - Conservative WebView bridge compatibility layer.
   Existing Android interface is retained. A future WebMessage host may opt in
   only after native origin validation and an explicit capability handshake.
   Never caches backup/import or notification actions. */
(function(root,doc){'use strict';
  if(root.HunterNative3875)return;
  const status={sent:0,skipped:0,failed:0,messageSent:0,legacySent:0};
  const previous=Object.create(null);
  let lastTheme='';
  function nativeObject(){return root.Android||null;}
  function secureMessages(){return root.__HUNTER_WEB_MESSAGES_READY_V1__===true &&
    root.HunterMessages && typeof root.HunterMessages.postMessage==='function';}
  function send(type,payload,legacy){
    try {
      if(secureMessages()){
        root.HunterMessages.postMessage(JSON.stringify({schema:1,type:type,payload:payload}));
        status.messageSent++;status.sent++;return true;
      }
      const bridge=nativeObject();
      if(!bridge||typeof bridge[legacy]!=='function')return false;
      bridge[legacy](type==='syncPlan'?JSON.stringify(payload):payload);
      status.legacySent++;status.sent++;return true;
    }catch(error){status.failed++;return false;}
  }
  function syncPlan(data){
    if(!data||typeof data!=='object'||!Array.isArray(data.days))return false;
    let serialized;try{serialized=JSON.stringify(data);}catch(e){return false;}
    if(serialized===previous.syncPlan){status.skipped++;return true;}
    const ok=send('syncPlan',data,'syncPlan');
    if(ok)previous.syncPlan=serialized;
    return ok;
  }
  function setThemeBackground(color){
    if(typeof color!=='string'||!/^#[0-9a-fA-F]{6}$/.test(color))return false;
    color=color.toLowerCase();
    if(color===lastTheme){status.skipped++;return true;}
    const ok=send('setThemeBackground',color,'setThemeBackground');
    if(ok)lastTheme=color;
    return ok;
  }
  function reset(){previous.syncPlan=null;lastTheme='';}
  doc.addEventListener('visibilitychange',function(){if(doc.hidden)reset();},{passive:true});
  root.HunterNative3875=Object.freeze({version:'38.75',syncPlan,setThemeBackground,
    snapshot:()=>({...status,messageReady:!!secureMessages(),legacyReady:!!nativeObject()})});
})(window,document);
