const { originalActions } = require('./acciones_originales.cjs');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const read = name => fs.readFileSync(path.join(__dirname, '..', name), 'utf8');
const version = read('apk_contents/assets/v3.js');
const names = [
    'dayByDate', 'ensureTasks', 'ensureDayLog', 'itemFinished', 'missionSummary', 'recomputeMission',
    'showMission', 'missionWindow', 'refreshMission', 'toggleMissionItem', 'toggleMissionTask', 'saveMissionNote',
];
const original = names.map(name => version.match(new RegExp('^function ' + name + '\\(.+$', 'm'))[0]).join('\n');
const compiled = read('build/hunter-web/hunter-screens.js');

function exercise(migrated) {
    const today = '2026-11-22';
    const activity = [];
    const dialog = { open: false, dataset: {}, scrollTop: 123, innerHTML: '' };
    const current = {
        date: today, kind: 'strength', title: 'Fuerza & técnica',
        items: [{ id: 'press', sets: 2, target: 12 }, { id: 'wall', sets: 1, target: 30 }],
        tasks: [{ id: 'water', text: 'Agua & descanso', done: false }],
    };
    const rest = { date: '2026-11-21', kind: 'rest', items: [] };
    const movements = {
        press: { id: 'press', name: 'Press' },
        wall: { id: 'wall', name: 'Pared' },
    };
    const context = vm.createContext({
        window: {},
        state: { cycles: [{ days: [rest, current] }], logs: {}, profile: { auto: true, restricted: false } },
        missionDate: null,
        Date: class extends Date { constructor() { super('2026-11-22T09:00:00Z'); } },
        E: {
            dateKey: () => today, byId: id => movements[id],
            unitOf: item => item.id === 'wall' ? 'sec' : 'reps', sidesOf: item => item.id === 'press',
        },
        Adapt: {
            mode: profile => ({ auto: profile.auto, reason: 'Revisar & verificar' }),
            reasons: (item, profile) => item.id === 'wall' && profile.restricted ? ['Revisión & prueba'] : [],
            allowed: (item, profile) => item.id !== 'wall' || !profile.restricted,
        },
        esc: value => String(value ?? '').replaceAll('&', '&amp;'),
        $: () => dialog,
        save: () => activity.push('save'),
        render: () => activity.push('render'),
        pulse: () => activity.push('pulse'),
        modal: markup => { dialog.innerHTML = markup; dialog.open = true; activity.push('modal'); },
        current,
        rest,
    });
    if (migrated) {
        vm.runInContext(compiled, context);
        for (const name of names) {
            context[name] = context.window.HunterKotlinScreens[name];
        }
        context.showMission = date => context.window.HunterKotlinScreens.showMission(date, value => { context.missionDate = value; });
    } else {
        vm.runInContext(original, context);
    }
    const run = code => vm.runInContext(code, context);
    const results = [
        run("dayByDate('missing')"),
        run('missionWindow(current)'),
        run('missionWindow(rest, true)'),
        run("itemFinished({missionState:null}, {complete:true})"),
    ];
    run("showMission('missing'); showMission();");
    assert.equal(context.missionDate, today);
    assert.equal(dialog.dataset.mission, today);
    results.push(dialog.innerHTML);
    run("saveMissionNote(E.dateKey(), 'A'.repeat(700));");
    run("toggleMissionItem(rest.date, 0, true); toggleMissionItem(current.date, 0, true);");
    run('state.profile.restricted=true; toggleMissionItem(current.date, 1, true);');
    results.push(dialog.innerHTML);
    run('state.profile.auto=false;');
    results.push(run('missionWindow(current)'));
    run('state.profile.auto=true; state.profile.restricted=false; toggleMissionTask(current.date, 0, true); toggleMissionItem(current.date, 1, true);');
    assert.equal(run('state.logs[current.date].complete'), true);
    results.push(dialog.innerHTML, run('missionSummary(current)'));
    run('toggleMissionTask(current.date, 0, false); state.logs[current.date].pain=true; refreshMission();');
    results.push(dialog.innerHTML);
    dialog.open = false;
    run('refreshMission()');
    assert.equal(dialog.scrollTop, 123);
    assert.equal(run('state.logs[current.date].note.length'), 500);
    return JSON.stringify({ results, activity, logs: context.state.logs }, (_, value) => typeof value === "string" ? originalActions(value) : value);
}

assert.equal(exercise(true), exercise(false));
console.log('La ventana, las casillas y el guardado de misiones en Kotlin coinciden con el original.');
