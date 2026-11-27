const assert = require('node:assert/strict');
const { chromium } = require('playwright');
const path = require('node:path');
const root = path.resolve(__dirname, '..');
const origin = process.env.HUNTER_TEST_ORIGIN || 'http://127.0.0.1:8575';

(async () => {
    const browser = await chromium.launch({executablePath:process.env.CHROMIUM_PATH || undefined,args:['--no-sandbox']});
    try {
        async function read(folder) {
            const page = await browser.newPage({viewport:{width:393,height:852},reducedMotion:'reduce'});
            const errors = [];
            page.on('pageerror', error => errors.push(error.message));
            await page.route('**/apk_contents/assets/fonts/*.ttf',r=>r.fulfill({path:path.join(root, 'build/hunter-web/fonts', path.basename(r.request().url()))}));
            await page.goto(origin+'/'+folder+'/index.html');
            await page.locator('#profileForm').waitFor({state:'attached'});
            const result = await page.evaluate(() => {
                const key=E.dateKey();
                const day={date:key,title:'Prueba de sesión',kind:'strength',items:[
                    {id:'chair',sets:2,target:6,rest:30},{id:'walk',sets:1,target:10,rest:30}
                ]};
                state.profile={animate:false,blockMode22:'exercise'};
                state.logs[key]={items:day.items.map(x=>({...x,actual:Array.from({length:x.sets},()=>({done:false,value:0,kg:0}))}))};
                state.guidedSessions={};
                // Mantener el mismo día y aislar las escrituras, sin solicitar permisos Android.
                dayByDate=()=>day; getLog=()=>state.logs[key]; save=()=>{}; render=()=>{};
                RoutineSequence.compatible=()=>true;
                const record=()=>({index:guide11.index,remaining:guide11.remaining,signature:guide11.signature,mode:guide11.blockMode22,saved:state.guidedSessions[key]?.index,html:document.querySelector('#guidedSlot')?.innerHTML});
                startGuide11(day,true);
                const snapshots=[record()];
                const index=guide11.steps.findIndex(s=>s.kind==='exercise');
                jumpGuide19(index);snapshots.push(record());
                const form=document.querySelector('#guideRecord');
                form.elements.value.value='5';
                form.elements.completed.checked=true;
                guideCheck19(form.elements.completed);
                confirmGuideSet11({preventDefault(){},target:form});snapshots.push(record());
                pauseGuide11(); const savedIndex=state.guidedSessions[key].index;
                startGuide11(day,true); if(guide11.index!==savedIndex) throw Error('No recuperó la serie');
                snapshots.push(record());
                setGuideMode22('rounds');snapshots.push(record());
                jumpGuide19(guide11.steps.length);snapshots.push(record());
                return {snapshots,log:state.logs[key]};
            });
            assert.deepEqual(errors, [], folder);
            await page.close();
            return result;
        }
        const expected = await read('apk_contents/assets');
        const got = await read('build/hunter-web');
        assert.deepEqual(got,expected);
        console.log('Chromium: registro, reanudación, cambio de orden y HTML de la guía coinciden.');
    } finally { await browser.close(); }
})().catch(e=>{ console.error(e);process.exitCode=1; });
