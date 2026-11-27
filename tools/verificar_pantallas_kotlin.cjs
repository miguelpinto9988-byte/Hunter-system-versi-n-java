const { originalActions } = require('./acciones_originales.cjs');
const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const root = path.join(__dirname, '..');
const read = filename => fs.readFileSync(path.join(root, filename), 'utf8');
const originalReminders = read('apk_contents/assets/v2.js').match(/^function remindersView\(\).+$/m)[0];
const originalGoals = read('apk_contents/assets/v2.js').match(/^function goalsView\(\).+$/m)[0];
const originalNavigation = read('apk_contents/assets/v3.js').match(/^nav=function\(\).+$/m)[0];
const compiled = read('build/hunter-web/hunter-screens.js');
const context = vm.createContext({
    window: {},
    state: {},
    nav: null,
    goalDone: goal => goal.repeat === 'daily' ? goal.dates.includes('2026-11-22') : goal.dates.length > 0,
    tab: 'home',
    icon: name => '<i>' + name + '</i>',
    icon2: name => '<b>' + name + '</b>',
    esc: value => String(value ?? '').replaceAll('&', '&amp;').replaceAll('"', '&quot;'),
});
vm.runInContext([originalReminders, originalGoals, originalNavigation, compiled].join('\n'), context);
const screens = context.window.HunterKotlinScreens;

for (const tab of ['home', 'plan', 'calendar', 'routines', 'book', 'stats', 'workout', 'goals', 'monarch', 'reminders']) {
    context.tab = tab;
    assert.equal(originalActions(screens.navigation()), context.nav(), tab);
}

for (const mode of ['daily', 'interval']) {
    for (const enabled of [true, false]) {
        for (const interval of [1, 2, 3, 4, 6]) {
            for (const android of [undefined, { notificationStatus() {} }]) {
                context.window.Android = android;
                context.state.reminders = { mode, enabled, interval, time: '08:30', start: '07:00', end: '20:00', days: [1, 3, 7] };
                assert.equal(screens.reminders(), context.remindersView());
            }
        }
    }
}

for (const goals of [[], [
    { id: 'g1', title: 'Fuerza & "técnica"', repeat: 'daily', dates: ['2026-11-22', '2026-11-21'] },
    { id: 'g2', title: 'Movilidad', repeat: 'once', dates: [] },
    { id: 'g3', title: 'Cardio', repeat: 'once', dates: ['2026-11-20'] },
]]) {
    context.state.goals = goals;
    assert.equal(screens.goalsView(), context.goalsView());
}

console.log('Objetivos, Recordatorios y la navegación de Kotlin generan el mismo HTML que el original.');
