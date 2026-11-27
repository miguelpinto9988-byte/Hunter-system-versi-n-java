const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const exercises = [
    { id: 'squat', baseId: 'legs', group: 'Piernas', category: 'Fuerza', equipment: 'body', zones: ['knee'], movement: 'knee', demoPath30: 's.svg', modelPath30: 'm.svg' },
    { id: 'step', family: 'legs', group: 'Piernas', movement: 'knee' },
    { id: 'push', group: 'Pecho', movement: 'press' },
];

function load(folder) {
    const file = path.join(__dirname, '..', folder, 'hunter-plan3810.js');
    const engine = { exercises, byId: id => exercises.find(exercise => exercise.id === id) };
    const context = vm.createContext({ Engine: engine, HunterTraining387: { pattern: exercise => exercise?.movement } });
    vm.runInContext(fs.readFileSync(file, 'utf8'), context, { filename: file });
    return context.HunterPlan3810;
}

function check(api) {
    const cycle = { days: [
        { date: '2026-11-18', customized: true, items: [{ id: 'step', sets: 50 }] },
        { date: '2026-11-22', items: [{ id: 'squat', sets: '3' }] },
        { date: '2026-11-23', items: [{ id: 'missing', sets: 2 }], manual: true },
        { date: '2026-11-24', items: [{ id: 'push', sets: 4 }], tasks: [{ done: true }] },
        { date: '2026-11-25', items: [{ id: 'step', sets: 4 }] },
    ] };
    const logs = { '2026-11-22': { items: [{ actual: [{ done: true }] }] } };
    const tracker = api.tracker(cycle, logs, '2026-11-22');
    const before = tracker.snapshot();
    tracker.add('2026-11-23', exercises[1], '7');
    tracker.add('incorrect', exercises[1], 3);
    tracker.add('2026-11-23', null, 1);
    return {
        frozen: Object.isFrozen(api),
        before,
        after: tracker.snapshot(),
        scores: exercises.map(exercise => ['2026-11-23', '2026-12-01', 'invalid'].map(date => tracker.score(exercise, date))),
        defaultSnapshot: api.tracker(cycle).snapshot(),
        defaultStats: api.stats(),
        emptyStats: api.stats([]),
        dates: [api.dayDiff('2026-11-23', '2026-11-24'), api.dayDiff('', '2026-11-24')],
    };
}

const plain = value => JSON.parse(JSON.stringify(value));
assert.deepEqual(plain(check(load('build/hunter-web'))), plain(check(load('apk_contents/assets'))));
console.log('La planificación Kotlin coincide con el original para sesiones bloqueadas, puntuación y catálogo.');
