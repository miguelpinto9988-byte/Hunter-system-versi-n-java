const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const read = name => fs.readFileSync(path.join(__dirname, '..', name), 'utf8');
const source = read('apk_contents/assets/v3.js');
const original = ['editMission', 'saveMissionEdit'].map(name =>
    source.match(new RegExp('^function ' + name + '\\(.+$', 'm'))[0],
).join('\n');
const compiled = read('build/hunter-web/hunter-screens.js');

function scenario(migrated, operation) {
    const date = '2026-11-22';
    const actions = [];
    const dialog = { dataset: { mission: date }, close: () => actions.push('close') };
    const day = {
        date, kind: 'strength', title: 'Fuerza & técnica',
        items: [{ id: 'press', sets: 2, target: 12 }, { id: 'wall', sets: 1, target: 30 }],
        tasks: [{ id: 'task', text: 'Tarea pendiente', done: false }, { id: 'old', text: 'Tarea hecha', done: true }],
    };
    const log = { items: [
        { ...day.items[0], actual: [{ done: true, value: 12, kg: 7 }, { done: false, value: 12, kg: 0 }] },
        { ...day.items[1], actual: [{ done: false, value: 30, kg: 0 }] },
    ] };
    const exercises = [
        { id: 'press', name: 'Press', variant: 'base' },
        { id: 'wall', name: 'Pared', variant: 'base' },
        { id: 'new', name: 'Espalda', variant: 'base' },
        { id: 'blocked', name: 'Bloqueado', variant: 'base' },
        { id: 'alt', name: 'Variante', variant: 'variant' },
    ];
    const fields = {
        '#modal': dialog,
        '#mi-remove-0': { checked: true },
        '#mi-remove-1': { checked: operation === 'remove' },
        '#mi-title-0': { value: ' Conservado ' },
        '#mi-title-1': { value: ' Actualizado ' },
        '#mi-sets-1': { value: '3' },
        '#mi-target-1': { value: '45' },
        '#mt-0': { value: '  ' },
        '#mt-1': { value: 'Tarea hecha' },
        '#newExercise': { value: operation === 'blocked' ? 'blocked' : 'new' },
        '#newTask': { value: ' Tarea nueva ' },
        '#missionTitle': { value: ' Misión personalizada ' },
    };
    const context = vm.createContext({
        window: {}, state: { logs: operation === 'unstarted' ? {} : { [date]: log }, profile: {} },
        missionDate: null,
        E: { dateKey: () => date, exercises, byId: id => exercises.find(e => e.id === id), unitOf: item => item.id === 'wall' ? 'sec' : 'reps' },
        Adapt: { allowed: exercise => exercise.id !== 'blocked' },
        TrainingIntensity: { dose: exercise => ({ id: exercise.id, sets: 2, target: 8 }) },
        GoalPlans: { adjusted: profile => profile },
        Date: { now: () => 123456 },
        $: id => fields[id],
        esc: value => String(value ?? '').replaceAll('&', '&amp;'),
        dayByDate: value => value === date ? day : null,
        itemFinished: item => item.missionState,
        ensureTasks: item => item.tasks,
        modal: markup => actions.push(markup),
        toast: message => actions.push(message),
        recomputeMission: () => actions.push('recompute'),
        save: () => actions.push('save'),
        showMission: value => actions.push(['show', value]),
        event: { preventDefault: () => actions.push('prevented'), target: { reportValidity: () => operation !== 'invalid' } },
    });
    vm.runInContext(migrated ? compiled + `
        function editMission(date){window.HunterKotlinScreens.editMission(date,value=>{missionDate=value;});}
        function saveMissionEdit(event){return window.HunterKotlinScreens.saveMissionEdit(event,missionDate);}
    ` : original, context);
    vm.runInContext("editMission('2026-11-21'); editMission('2026-11-22'); saveMissionEdit(event);", context);
    if (['success', 'remove'].includes(operation)) {
        assert.equal(log.items[0].actual[0].kg, 7);
        assert.equal(log.items[0].missionTitle, 'Conservado');
        assert.equal(day.tasks[0].done, true);
    }
    return JSON.stringify({ actions, day, log, missionDate: context.missionDate, dialog: dialog.dataset });
}

for (const operation of ['success', 'remove', 'blocked', 'invalid', 'unstarted']) {
    assert.equal(scenario(true, operation), scenario(false, operation), operation);
}
console.log('El editor Kotlin coincide con el original y conserva las series registradas.');
