const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const root = path.join(__dirname, '..');
function load(folder) {
    const exercises = {
        left: { id: 'left', name: 'Brazo 💪', unit: 'rep' },
        clock: { id: 'clock', name: 'Reloj', unit: 'sec' },
    };
    const scope = vm.createContext({
        Engine: { byId: id => exercises[id], unitOf: x => x.unit || exercises[x.id].unit },
        Adapt: { mode: p => p, allowed: (exercise, p) => exercise.id !== p.blocked },
        TrainingProgression19: { rest: p => p.rest || 45 },
        OwnRoutines18: { workSeconds: x => x.target * 2.1 },
    });
    scope.window = scope;
    for (const name of ['routine-sequence', 'blocks-v22']) {
        vm.runInContext(fs.readFileSync(path.join(root, folder, name + '.js'), 'utf8'), scope);
    }
    return scope;
}

const original = load('apk_contents/assets');
const kotlin = load('build/hunter-web');
const days = [
    { items: [] },
    { items: [
        { id: 'left', sets: 3, target: 12, side: 'left', rest: 42 },
        { id: 'clock', sets: 2, target: 30, rest: 60 },
    ] },
    { items: [{ id: 'left' }], sourceSequence: { steps: [
        { kind: 'preparation', seconds: 30, label: 'Movilidad' },
        { kind: 'exercise', exerciseId: 'left', sets: 2, target: 6, side: 'right', restSeconds: 10 },
        { kind: 'rest', seconds: 20, label: 'Pausa' },
        { kind: 'exercise', exerciseId: 'clock', sets: 1, target: 8, unit: 'sec' },
    ] } },
];
for (const day of days) {
    const before = original.RoutineSequence.expand(day);
    const after = kotlin.RoutineSequence.expand(day);
    assert.equal(JSON.stringify(after), JSON.stringify(before));
    assert.equal(kotlin.RoutineSequence.signature(after), original.RoutineSequence.signature(before));
    for (const profile of [{ auto: true }, { auto: true, blocked: 'clock' }, { auto: false }]) {
        assert.equal(kotlin.RoutineSequence.compatible(day, profile), original.RoutineSequence.compatible(day, profile));
    }
    for (const order of [undefined, 'exercise', 'rounds']) {
        const layout = kotlin.BlockSession22.expand(day, order);
        assert.equal(JSON.stringify(layout), JSON.stringify(original.BlockSession22.expand(day, order)));
        for (const block of layout?.blocks || []) {
            block.series.forEach((step, i) => assert.equal(step, layout.steps[block.stepIndices[i]]));
        }
    }
}
console.log('Secuencias, firmas de guardado, descansos, rondas y referencias de las series coinciden con la APK.');
