const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

function load(folder, scenario) {
    const filename = path.join(__dirname, '..', folder, 'hunter-visibility3874.js');
    const contents = fs.readFileSync(filename, 'utf8');
    const header = contents.indexOf('/* Hunter System 38.79');
    assert.notEqual(header, -1);
    const code = contents.slice(contents.indexOf('*/', header) + 2);
    const date = '2026-11-24';
    const day = { date, customized: scenario === 'custom', items: [{ id: 'press' }] };
    const log = { complete: scenario === 'complete', items: [{ actual: [{ done: true, value: 8, kg: 4 }] }] };
    const state = {
        profile: scenario === 'first' ? null : { name: 'Original', weekFocus: { b: 2, a: 1 } },
        logs: { [date]: log }, cycles: [{ days: scenario === 'no-day' ? [] : [day] }],
    };
    let onDisk;
    const calls = [];
    const context = vm.createContext({
        state, KEY: 'hunter.system.v1', Engine: { dateKey: () => date },
        localStorage: { getItem() { if (scenario === 'storage-error') throw 'error'; return onDisk; } },
        currentCycle() { calls.push('cycle'); },
        save() { calls.push('save'); },
        toast(message) { calls.push(message); },
        render() { calls.push('render'); },
        console: { error(message) { calls.push(message); } },
        HunterStable3828: { safetyKey: profile => profile.name },
        HunterStrict3838: { apply(cycle, profile, logs, key) { calls.push([profile.name, key, cycle.days.length, logs[key].items]); } },
        HunterProfile3829: {
            regenerate(before, oldLog, oldDay) {
                calls.push(['regenerate', before, oldLog, oldDay]);
                if (scenario === 'failure') throw 'error';
                return { status: scenario === 'review' ? 'review' : 'updated' };
            },
        },
        submitProfile(event, extra) {
            calls.push([event.target.id, extra]);
            if (scenario === 'same') state.profile = { weekFocus: { a: 1, b: 2 }, name: 'Original' };
            else state.profile = { name: 'Nuevo' };
            state.logs[date] = {};
            if (state.cycles[0].days.length) state.cycles[0].days[0] = { date };
            onDisk = JSON.stringify({ profile: scenario === 'storage-mismatch' ? {} : state.profile });
            return 'answer';
        },
    });
    context.window = context;
    vm.runInContext(code, context);
    const event = { target: { id: scenario === 'other' ? 'another' : 'profileForm' } };
    const answer = context.submitProfile(event, 123);
    return JSON.parse(JSON.stringify({ answer, state, calls, stats: context.HunterProfileSync3879.diagnostics() }));
}

for (const scenario of ['other', 'first', 'same', 'custom', 'complete', 'no-day', 'updated', 'review', 'failure', 'storage-mismatch', 'storage-error']) {
    assert.deepEqual(load('build/hunter-web', scenario), load('apk_contents/assets', scenario), scenario);
}
console.log('El perfil y los registros coinciden en los once escenarios de sincronización.');
