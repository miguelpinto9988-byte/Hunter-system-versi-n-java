const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const root = path.join(__dirname, '..');
function load(directory, groups) {
    const context = vm.createContext({ HunterGroups49: groups });
    const file = path.join(root, directory, 'hunter-schedule3813.js');
    vm.runInContext(fs.readFileSync(file, 'utf8'), context, { filename: file });
    return context.HunterSchedule3813;
}

for (const groups of [undefined, { level: exercise => exercise.adjusted }]) {
    const original = load('apk_contents/assets', groups);
    const migrated = load('build/hunter-web', groups);
    assert.deepEqual(Object.keys(migrated), Object.keys(original));
    assert.equal(Object.isFrozen(migrated), true);

    for (const equipment of [undefined, 'body', 'db', 'gym', 'unknown']) {
        for (const difficulty of [undefined, 'basic', 'intermediate', 'advanced', {}]) {
            for (const level of [-1, 0, 1, 2, NaN, Infinity, '1']) {
                for (const required of [[], ['db'], ['gym'], null]) {
                    for (const uses of ['body', 'db', 'gym', '', {}]) {
                        const exercise = { id: 'test', equipment: uses, level, requirements: required };
                        const profile = { equipment, difficulty, experience: 'regular' };
                        assert.equal(migrated.eligible(exercise, profile), original.eligible(exercise, profile));
                        exercise.gymOnly23 = true;
                        exercise.adjusted = 1;
                        assert.equal(migrated.eligible(exercise, profile), original.eligible(exercise, profile));
                    }
                }
            }
        }
    }
    for (const index of [-1, 0, 6, 7, 13, 14, 20, 21, 7.25, '7', NaN, Number.MAX_SAFE_INTEGER]) {
        assert.equal(migrated.weekSegment(index), original.weekSegment(index));
    }
    const days = Array.from({ length: 24 }, (_, index) => ({
        scheduleChoice: index % 2 ? 'rest' : 'train',
        items: [{ id: 'a' }, { id: 'a' }, { id: index }, null],
    }));
    delete days[5];
    for (const cycle of [null, {}, { days: {} }, { days }]) {
        assert.equal(JSON.stringify(migrated.report(cycle)), JSON.stringify(original.report(cycle)));
    }
}

console.log('El módulo Kotlin coincide con el original en las pruebas de horario y equipamiento.');
