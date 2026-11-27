const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const read = name => fs.readFileSync(path.join(__dirname, '..', name), 'utf8');
const v2 = read('apk_contents/assets/v2.js');
const route = read('apk_contents/assets/hunter-route3855.js');
const compiled = read('build/hunter-web/hunter-screens.js');

function run(migrated) {
    const today = '2026-11-22';
    const activity = [];
    const goals = [{ id: 'gold', title: 'Fuerza & movilidad', repeat: 'daily', dates: ['2026-11-21'] }];
    const dateValues = ['2026-11-21', ...Array.from({ length: 9 }, (_, i) => '2026-11-' + (i + 22))];
    const days = dateValues.map((date, i) => ({ date, kind: ['strength', 'rest', 'recovery', 'cardio', 'mixed'][i % 5] }));
    const fields = {
        '#modal': { close() { activity.push('close'); } },
        '#goalTitle': { value: '   Objetivo nuevo   ' },
        '#goalRepeat': { value: 'once' },
    };
    const context = vm.createContext({
        window: {}, document: { querySelector: id => fields[id] }, calendar: null,
        state: { goals, logs: { [today]: { complete: true } } },
        E: { dateKey: () => today }, Date: { now: () => 123456 },
        kindLabel: kind => kind.toUpperCase(),
        esc: value => String(value ?? '').replace(/[&<>"']/g, c => ({ '&':'&amp;', '<':'&lt;', '>':'&gt;', '"':'&quot;', "'":'&#39;' })[c]),
        blocked: () => '<p>Bloqueado</p>',
        cycle: { days, start: '2026-11-21', index: 0 },
        save: () => activity.push('save'),
        render: () => activity.push('render'),
        pulse: () => activity.push('pulse'),
        toast: text => activity.push(text),
        go: tab => activity.push(tab),
        modal: text => activity.push(text),
        $: id => fields[id],
        event: { preventDefault() { activity.push('prevented'); } },
        fields,
    });
    vm.runInContext('function currentCycle(){return cycle;}', context);
    if (migrated) {
        vm.runInContext(compiled, context);
        vm.runInContext(`
            var HunterKotlinScreens = window.HunterKotlinScreens;
            calendar = HunterKotlinScreens.calendarGrid;
            var {calendarScreen, goalDone, goalMini, newGoal, addGoal, toggleGoal, deleteGoal} = HunterKotlinScreens;
        `, context);
    } else {
        const functions = ['goalDone', 'goalMini', 'newGoal', 'addGoal', 'toggleGoal', 'deleteGoal']
            .map(name => v2.match(new RegExp('^function ' + name + '\\(.+$', 'm'))[0]);
        vm.runInContext([v2.match(/^calendar=function.+$/m)[0], ...functions, route,
            'var calendarScreen = window.HunterRoute3855.calendarView;'].join('\n'), context);
    }

    const results = [vm.runInContext('calendarScreen()', context)];
    context.cycle = { days: [days[0]], start: '2026-11-21', index: 1 };
    results.push(vm.runInContext('calendarScreen()', context));
    context.cycle = null;
    results.push(vm.runInContext('calendarScreen()', context));
    results.push(vm.runInContext('goalMini()', context));
    vm.runInContext("newGoal(); addGoal(event); toggleGoal('gold'); toggleGoal('gold'); toggleGoal(state.goals[1].id);", context);
    results.push(vm.runInContext('goalMini()', context));
    vm.runInContext("deleteGoal('gold')", context);
    const confirmation = activity.pop();
    results.push(confirmation.replace(/ onclick="[^"]*"/, ''));
    const handler = confirmation.match(/ onclick="([^"]*)"/)[1];
    vm.runInContext(handler, context);
    fields['#goalTitle'].value = '   ';
    vm.runInContext('addGoal(event); toggleGoal("missing");', context);
    context.state.goals = Array.from({ length: 100 }, (_, i) => ({ id: 'g' + i }));
    fields['#goalTitle'].value = 'Otro';
    vm.runInContext('addGoal(event)', context);
    results.push(activity);
    return JSON.stringify(results);
}

assert.equal(run(true), run(false));
console.log('Calendario y acciones de Objetivos en Kotlin coinciden con el original.');
