const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const read = name => fs.readFileSync(path.join(__dirname, '..', name), 'utf8');

const app = read('apk_contents/assets/app.js');
const v2 = read('apk_contents/assets/v2.js');
const originals = [
    app.match(/^function save\(\).+$/m)[0],
    ...['refreshReminderStatus', 'testNotification', 'openNotificationSettings'].map(
        name => v2.match(new RegExp('^function ' + name + '\\(\\).+$', 'm'))[0],
    ),
].join('\n');
const compiled = read('build/hunter-web/hunter-screens.js');

function exercise(migrated) {
    const calls = [];
    const status = {};
    const window = {};
    const context = vm.createContext({
        window, Runtime20: { initializing: true }, KEY: 'hunter.system.v1',
        state: { version: 1, profile: { name: 'Cazador' }, logs: {} },
        document: { getElementById: id => id === 'nativeStatus' ? status : null },
        $: selector => selector === '#nativeStatus' ? status : null,
        toast: message => calls.push(['toast', message]),
        localStorage: { setItem(key, value) { calls.push(['storage', key, value]); } },
        reminderStatus: null,
    });

    vm.runInContext(migrated ? compiled + `
        const api = window.HunterKotlinScreens;
        const save = api.persistState;
        const refreshReminderStatus = () => api.refreshReminderStatus(value => { reminderStatus = value; });
        const testNotification = api.testNotification;
        const openNotificationSettings = api.openNotificationSettings;
    ` : originals, context);
    const run = source => vm.runInContext(source, context);
    const results = [run('save()')];
    context.Runtime20.initializing = false;
    results.push(run('save()'));
    context.localStorage.setItem = () => { throw 'quota'; };
    results.push(run('save()'));
    run('refreshReminderStatus();testNotification();openNotificationSettings();');

    let response;
    const android = {
        notificationStatus() { return response; },
        testNotification() { assert.equal(this, android); calls.push(['test']); },
        openNotificationSettings() { assert.equal(this, android); calls.push(['settings']); },
    };
    context.Android = window.Android = android;
    for (response of [
        '{"allowed":true,"enabled":true,"next":"20:30"}',
        '{"allowed":true,"enabled":true}',
        '{"allowed":true,"enabled":false}',
        '{"allowed":false}',
        'invalid',
    ]) {
        run('refreshReminderStatus()');
        results.push([status.textContent, context.reminderStatus]);
    }
    run('testNotification();openNotificationSettings();');
    return JSON.stringify({ calls, results });
}

assert.equal(exercise(true), exercise(false));
console.log('El guardado local y las llamadas de notificaciones en Kotlin coinciden con el original.');
