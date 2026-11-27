const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const complete = {
    id: 'row', group: 'Espalda', category: 'Fuerza', equipment: 'db',
    zones: ['back'], requirements: [], level: 1, demoPath30: 'a', modelPath30: 'b',
    pattern: 'traccion_horizontal',
};
const catalog = [
    null, { id: '' },
    complete,
    { ...complete, id: 'curl', pattern: 'flexion_codo' },
    { ...complete, id: 'seconds', unit: 'sec', pattern: 'rodilla' },
    { ...complete, id: 'no-pattern', pattern: undefined },
    { ...complete, id: 'incomplete', zones: [], level: '1' },
    { id: 'bare', group: ' ' },
];

function load(folder, withTraining) {
    const file = path.join(__dirname, '..', folder, 'hunter-metadata3812.js');
    const engine = { exercises: catalog };
    const training = {
        pattern(exercise) {
            assert.equal(this, training);
            return exercise.pattern;
        },
    };
    const context = vm.createContext({ Engine: engine, HunterTraining387: withTraining ? training : undefined });
    vm.runInContext(fs.readFileSync(file, 'utf8'), context, { filename: file });
    return context.HunterMetadata3812;
}

function run(api) {
    const analysis = api.analysis();
    const results = [Object.isFrozen(api), Object.isFrozen(analysis), analysis];
    const feedback = [
        undefined, { strain: true, avg: 5, canProgress: true },
        { avg: '7', canProgress: true }, { avg: 8, canProgress: true },
    ];
    const profiles = [
        null, { goal: 'STRENGTH' }, { goal: 'muscle' }, { goal: 'endurance' },
        { goal: 'strength', injuries: [{ stage: 'past' }] },
        { goal: 'strength', injuries: [{ stage: 'current' }] },
        { goal: 'strength', health: { pain: 'unknown' } }, { goal: 'muscle', limits: true },
    ];
    for (const exercise of [...catalog, { id: 'missing' }]) {
        const metadata = api.metadata(exercise?.id);
        results.push(metadata, Object.isFrozen(metadata), Object.isFrozen(metadata?.fields));
        for (const profile of profiles) {
            for (const kind of ['strength', 'cardio']) {
                results.push(api.score(exercise, profile, kind));
                for (const response of feedback) {
                    for (const status of [null, { reduced: true }]) {
                        const current = { target: '14', rest: -20, sets: 5, retained: true };
                        results.push(api.dose(exercise, profile, kind, status, response, '2026-11-22', current));
                        assert.deepEqual(current, { target: '14', rest: -20, sets: 5, retained: true });
                    }
                }
            }
        }
    }
    return JSON.stringify(results);
}

for (const supported of [true, false]) {
    assert.equal(run(load('build/hunter-web', supported)), run(load('apk_contents/assets', supported)));
}
console.log('Los metadatos, puntuaciones y ajustes de Kotlin coinciden con el original.');
