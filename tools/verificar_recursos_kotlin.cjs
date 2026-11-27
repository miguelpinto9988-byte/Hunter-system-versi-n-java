const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

const read = file => fs.readFileSync(path.join(__dirname, '..', file), 'utf8');
const bindings = [
    ['anatomy-surface', 'AnatomySurface15'], ['asterion-data3868', 'HunterPortableData3868'],
    ['creator-research', 'CreatorResearch'], ['creator-seed', 'CreatorSeed'],
    ['hunter-groups3849-data', 'HunterGroupRows49'], ['offline-manifest-v20', 'OfflineManifest20'],
    ['motions', 'MotionDB'], ['hd-motion-v16', 'HDMotion16'], ['demos', 'DemoLibrary'],
];

function values(folder, kotlin) {
    const context = vm.createContext({});
    context.window = context;
    if (kotlin) vm.runInContext(read(folder + '/HunterResources.js'), context);
    return bindings.map(([file, name]) => {
        vm.runInContext(read(folder + '/' + file + '.js'), context);
        const value = vm.runInContext(name, context);
        return [name, Object.hasOwn(context, name), Object.isFrozen(value), JSON.stringify(value)];
    });
}
assert.deepEqual(values('build/hunter-web', true), values('apk_contents/assets', false));
console.log('Los nueve recursos coinciden en contenido, ámbito global y mutabilidad.');
