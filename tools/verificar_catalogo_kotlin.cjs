const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const read = file => fs.readFileSync(path.join(__dirname, '..', file), 'utf8');
const data = JSON.parse(read('kotlin-web/data/catalog46.json'));

function run(directory) {
    const baseCount = 1200 - data.rows.length;
    const exercises = Array.from({ length: baseCount }, (_, i) => ({ id: data.gaps[i]?.id ?? 'base-' + i, group: 'Core' }));
    const marker = {};
    const engine = {
        exercises, byId: id => exercises.find(e => e.id === id),
        dateKey: () => '2026-11-22', plus: () => '2026-11-08',
    };
    const adapt = {
        allowed(e, p, extra) { return this === marker ? extra : true; },
        reasons() { return ['previa', 'previa']; },
        routineCompatible() { return this === marker; },
    };
    const context = vm.createContext({
        Engine: engine, Adapt: adapt,
        HunterMaterials3814: { eligible: () => true, requirements: e => e.requirements },
    });
    context.window = context;
    vm.runInContext(read(directory + '/hunter-catalog3846.js'), context);
    const api = context.HunterCatalog46;
    const rows = exercises.slice(baseCount);
    const result = {
        exercises, ids: api.ids, newIds: api.newIds, materials: api.materials, capabilities: api.capabilities,
        partitions: Object.keys(api.groups).map(group => api.byGroup(group).map(e => e.id)),
        frozen: [Object.isFrozen(api), Object.isFrozen(context.HunterMaterials3814)],
        data: [],
    };
    const full = { difficulty: 'advanced', age: 25, equipment: 'gym', bench: true, step: true,
        gymEquipment23: ['bench', 'barbell', 'cable'], materials46: Object.keys(api.materials), capabilities46: Object.keys(api.capabilities) };
    for (const e of rows) {
        const p = { difficulty: 'basic', age: 16, trainingScale: 0.5, health: { pain: 'moderate' }, restrictions: { noLoad: true } };
        const item = { sets: 8, rest: 10, target: 50, safetyNote: 'Inicial' };
        const entry = { id: e.id, sets: 1, target: 10, actual: [{ done: true, value: 11 }] };
        const good = { complete: true, rpe: 5, items: [entry] };
        const logs = { '2026-11-18': good, '2026-11-20': good, '2026-11-12': good };
        const context = { date: '2026-11-22', logs, feedback: { canProgress: true } };
        result.data.push(
            api.owned(e), api.owned(e.id), api.reasons(e, p), api.reasons(e, full), api.cap(e, p, item),
            api.cap(e, full, item, context), api.cap(e, full, item, { ...context, logs: { ...logs, '2026-11-19': { fatigue: true } } }),
            adapt.allowed.call(marker, e, full, 'forwarded'), adapt.reasons(e, p),
            adapt.routineCompatible.call(marker, { items: [entry] }, full),
        );
    }
    result.data.push(api.owned(null), api.byGroup('absent'), api.reasons(null, full), api.cap(exercises[0], full, { sets: 1 }));
    return JSON.parse(JSON.stringify(result));
}
assert.deepEqual(run('build/hunter-web'), run('apk_contents/assets'));
console.log('Las 1.200 fichas, filtros, grupos y límites de dosis coinciden con el catálogo original.');
