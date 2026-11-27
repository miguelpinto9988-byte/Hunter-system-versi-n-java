const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

function run(directory) {
    const events = [];
    const listeners = new Map();
    const document = {
        hidden: false,
        addEventListener(name, callback) { listeners.set(name, callback); },
    };
    const window = { document };
    const file = path.join(__dirname, '..', directory, 'hunter-native3875.js');
    vm.runInNewContext(fs.readFileSync(file, 'utf8'), { window, document }, { filename: file });
    const api = window.HunterNative3875;
    const plan = { days: [{ date: '2026-11-22' }] };
    const results = [api.syncPlan(plan), api.syncPlan({}), api.setThemeBackground('red')];

    window.Android = {
        syncPlan(value) { events.push(['legacy-plan', value, this === window.Android]); },
        setThemeBackground(value) { events.push(['legacy-theme', value]); },
    };
    results.push(api.syncPlan(plan), api.syncPlan(plan), api.setThemeBackground('#AABBCC'));
    window.__HUNTER_WEB_MESSAGES_READY_V1__ = true;
    window.HunterMessages = {
        postMessage(value) { events.push(['message', value, this === window.HunterMessages]); },
    };
    results.push(api.setThemeBackground('#aabbcc'));
    document.hidden = true;
    listeners.get('visibilitychange')();
    results.push(api.syncPlan(plan), api.setThemeBackground('#AABBCC'));
    results.push(api.syncPlan({ days: [], toJSON() { throw new Error('serialization'); } }));
    window.HunterMessages.postMessage = () => { throw 'bridge error'; };
    results.push(api.setThemeBackground('#112233'));
    return { results, events, snapshot: JSON.stringify(api.snapshot()), frozen: Object.isFrozen(api) };
}

assert.deepEqual(run('build/hunter-web'), run('apk_contents/assets'));
console.log('El puente Kotlin coincide con el original en ambas vías y al manejar errores.');
