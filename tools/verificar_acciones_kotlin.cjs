const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');
const { handlers } = require('./acciones_originales.cjs');

const root = path.join(__dirname, '..');
const compiled = new vm.Script(fs.readFileSync(path.join(root, 'build/hunter-web/stage0.js'), 'utf8'));
const api = fs.readFileSync(path.join(root, 'kotlin-web/app/stage0/InterfaceActions.kt'), 'utf8');
const globals = Array.from(api.matchAll(/@JsName\("([^"]+)"\)/g), match => match[1]);

function fixture(active) {
    const calls = [];
    function track(name) {
        return new Proxy(function () {}, {
            get(_, key) {
                if (key === Symbol.toPrimitive) return () => name;
                if (key === 'src') return 'preview.svg';
                return track(name + '.' + String(key));
            },
            set(_, key, value) { calls.push([name + '.' + key, String(value)]); return true; },
            apply(_, receiver, args) {
                calls.push([name, ...args.map(arg => typeof arg === 'object' ? JSON.stringify(arg) : String(arg))]);
                return track(name + '()');
            },
        });
    }
    const context = vm.createContext({});
    context.window = context;
    for (const key of globals) {
        if (['window', 'Math', 'Number', 'String', 'Array', 'Object', 'Boolean'].includes(key)) continue;
        context[key] = track(key);
    }
    context.scrollTo = track('scrollTo');
    context.state = { customRoutines: [{ id: '1' }, { id: '2' }] };
    context.tab = active ? 'profile' : 'plan';
    context.builderItems = [{}, {}];
    context.injuryDraft = [{}, {}];
    context.vectorSession = { mirror: active };
    context.element = { value: '12', checked: active, open: active, question: { value: 'Rutina' },
        parentElement: track('parent'), querySelector: track('querySelector'), closest: track('closest') };
    context.event = { preventDefault: track('preventDefault') };
    return { calls, context };
}

function exercise(kotlin, name, params, handler, active) {
    const { context, calls } = fixture(active);
    if (kotlin) compiled.runInContext(context);
    const selection = '1';
    const action = kotlin ? 'HunterActions.' + name + '(' + params + ')' : handler;
    vm.runInContext('(function(event){' + action.replaceAll('@value', selection) + '}).call(element,event)', context);
    const state = Object.fromEntries(globals.filter(key => typeof context[key] !== 'function' && key !== 'window')
        .map(key => [key, context[key]]));
    return JSON.stringify({ calls, state });
}

for (const [name, [params, handler]] of handlers) {
    for (const active of [false, true]) {
        assert.equal(exercise(true, name, params, handler, active), exercise(false, name, params, handler, active), name);
    }
}
console.log(handlers.size + ' acciones de interfaz coinciden con los controles originales.');
