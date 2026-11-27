const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

function run(folder, supported) {
    const file = path.join(__dirname, '..', folder, 'hunter-visibility3874.js');
    const script = fs.readFileSync(file, 'utf8').split('\n;\n/* Hunter 38.78')[0];
    const frames = new Map();
    const events = new Map();
    const trace = [];
    const fixture = name => ({
        isConnected: true,
        classList: {
            entries: new Set(),
            contains(value) { return this.entries.has(value); },
            toggle(value, enabled) {
                if (enabled) this.entries.add(value);
                else this.entries.delete(value);
                trace.push([name, value, enabled]);
            },
        },
    });
    const hero = fixture('hero');
    const modal = fixture('modal');
    let nodes = [hero];
    let intersect;
    let mutate;
    let nextFrame = 1;
    class IntersectionObserver {
        constructor(callback, options) { intersect = callback; trace.push(['intersection', options]); }
        observe(node) { trace.push(['observe', node === hero ? 'hero' : 'modal']); }
        unobserve(node) { trace.push(['unobserve', node === hero ? 'hero' : 'modal']); }
    }
    class MutationObserver {
        constructor(callback) { mutate = callback; }
        observe(id, options) { trace.push(['mutation', id, options]); }
    }
    const document = {
        hidden: false,
        documentElement: fixture('document'),
        addEventListener(name, callback) { events.set(name, callback); },
        getElementById: id => id,
        querySelectorAll: () => nodes,
    };
    const window = {
        document,
        IntersectionObserver: supported ? IntersectionObserver : undefined,
        MutationObserver,
        addEventListener(name, callback) { events.set(name, callback); },
        requestAnimationFrame(callback) { const id = nextFrame++; frames.set(id, callback); return id; },
        cancelAnimationFrame(id) { frames.delete(id); trace.push(['cancel', id]); },
    };
    function tick() {
        for (const [id, frame] of [...frames]) {
            frames.delete(id);
            frame();
        }
    }
    vm.runInNewContext(script, { window, document, MutationObserver }, { filename: file });
    const api = window.HunterVisibility3874;
    api.refresh();
    tick();
    intersect?.([{ target: hero, isIntersecting: false }, { target: hero, isIntersecting: false }]);
    intersect?.([{ target: hero, isIntersecting: true }]);
    hero.isConnected = false;
    nodes = [modal];
    mutate?.();
    tick();
    api.refresh();
    document.hidden = true;
    events.get('visibilitychange')();
    tick();
    document.hidden = false;
    events.get('visibilitychange')();
    events.get('pageshow')();
    tick();
    return JSON.stringify({ trace, snapshot: api.snapshot(), frozen: Object.isFrozen(api) });
}

for (const supported of [true, false]) {
    assert.equal(run('build/hunter-web', supported), run('apk_contents/assets', supported));
}
console.log('El módulo Kotlin y el original coinciden al pausar y reanudar animaciones decorativas.');
