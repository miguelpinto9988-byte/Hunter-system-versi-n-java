const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const exercises = [
    { id: 'push', variant: 'base', group: 'Pecho', category: 'Fuerza', floor: true, zones: ['wrist'], wristLoad: true },
    { id: 'step', variant: 'base', group: 'Piernas', category: 'Cardio', standing: true, equipment: 'db', zones: ['knee'], kneeBend: true, impact: 'Con saltos' },
    { id: 'chair', variant: 'base', group: 'Espalda', category: 'Movilidad', requirements: ['db'], zones: ['back'], seated: true, overhead: true, spineMove: true },
    { id: 'incomplete', variant: 'base' },
    { id: 'variant', variant: 'variant' },
    { id: 'source', variant: 'base', sourceOnly: true },
    null,
];

function load(folder) {
    const file = path.join(__dirname, '..', folder, 'hunter-constraints3811.js');
    const context = {
        Adapt: {},
        Engine: { exercises },
        HunterPlanner379: {
            status: profile => ({ ok: !profile.blocked }),
            approved(exercise, profile) {
                if (profile.failure) throw 'planner failure';
                return exercise.id !== 'incomplete';
            },
        },
    };
    vm.runInNewContext(fs.readFileSync(file, 'utf8'), context, { filename: file });
    return context.HunterConstraints3811;
}

function run(api) {
    const results = [Object.isFrozen(api)];
    const keys = ['noImpact', 'noFloor', 'noOverhead', 'noKnee', 'noWrist', 'noLoad', 'seatedOnly', 'noSpine'];
    const profiles = [
        null, {}, { blocked: true }, { failure: true },
        { injuries: [{ zone: 'knee', stage: 'past' }, { zone: 'wrist' }, { zone: 'unknown' }, { zone: 'wrist' }, null] },
        { injuries: ['knee', 'ankle', 'back', 'shoulder'].map(zone => ({ zone })) },
        ...keys.map(key => ({ restrictions: { [key]: true } })),
        { restrictions: Object.fromEntries(keys.map(key => [key, true])) },
    ];
    for (const profile of profiles) {
        results.push(api.activeZones(profile), api.constraints(profile), api.summary(profile));
        for (const exercise of [...exercises, {}, { id: 'odd', group: {}, zones: ['knee'] }]) {
            results.push(api.attributes(exercise), api.review(exercise, profile), api.allowed(exercise, profile), api.rank(exercise, profile));
        }
    }
    const logs = {
        '2026-11-22': { pain: true },
        '2026-11-21': { rpe: '8' },
        '2026-11-18': { fatigue: true },
        '2026-11-16': { rpe: 7 },
        '2026-11-08': { pain: true },
        '2026-11-07': { pain: true },
        'invalid': { pain: true },
    };
    results.push(api.symptomTrend(logs, '2026-11-22'), api.symptomTrend(logs, 'invalid'));
    return JSON.stringify(results);
}
assert.equal(run(load('build/hunter-web')), run(load('apk_contents/assets')));
console.log('Las restricciones y el resumen de síntomas de Kotlin coinciden con el original.');
