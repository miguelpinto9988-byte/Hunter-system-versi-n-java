const assert = require('node:assert/strict');
const fs = require('node:fs');
const path = require('node:path');
const vm = require('node:vm');

async function run(folder, scenario) {
    const trace = [];
    const events = new Map();
    const style = {};
    const message = { textContent: '' };
    const progress = { value: 0 };
    const retry = { hidden: true };
    const classList = { add: value => trace.push(['+', value]), remove: value => trace.push(['-', value]) };
    const shell = {
        querySelector: selector => selector === 'progress' ? progress : message, classList,
        setAttribute: (...values) => trace.push(values), remove: () => trace.push('removed'),
    };
    const document = {
        documentElement: { classList, dataset: {}, style: { setProperty(key, value) { style[key] = value; } } },
        addEventListener: (name, callback) => events.set('document.' + name, callback),
        querySelectorAll: () => ({ length: 4 }), getElementById: () => shell,
        querySelector: selector => selector === '#app' ? { children: { length: 1 } } : retry,
        fonts: { load: font => { trace.push(font); return Promise.resolve(); } },
    };
    const windowErrors = [];
    const location = { href: 'https://hunter.invalid/index.html', origin: 'https://hunter.invalid', pathname: '/index.html', reload: () => trace.push('retry') };
    const clock = { now: () => 1234 };
    const scene = { asset: { path: 'scene.png' }, figure: 'figure.png' };
    const raf = cb => { queueMicrotask(() => cb(1234)); return 1; };
    const timeout = (cb, delay) => { if (delay !== 2000) queueMicrotask(cb); };
    const fakeDate = class extends Date { constructor() { super('2026-11-24T00:00:00Z'); } };
    const context = vm.createContext({
        URL, Promise, document, location, performance: clock, Date: fakeDate,
        localStorage: { getItem() { return scenario === 'bad-storage' ? '{' : '{"profile":{"gender":"female"},"appearance33":{"palette":"lunar"}}'; } },
        addEventListener: (name, cb) => { if (name === 'error') windowErrors.push(cb); },
        requestAnimationFrame: raf, setTimeout: timeout, matchMedia: () => ({ matches: true }),
        Motion30: { count: () => scenario === 'failure' ? 9 : 1200 }, state: { profile: {} },
        HunterV33: { Visual33: { selection: () => scene, companionPath: () => 'companion.png' } },
        HunterV34: { Hero34: { decode: file => { trace.push(file); return Promise.resolve(); } } },
        HunterV28: { Evolution28: { layout: () => trace.push('layout') } }, HunterV26: { UI26: { decorate: () => trace.push('decorate') } },
    });
    context.window = context;
    for (const file of ['bootstrap-v20.js', 'boot-v37.js']) {
        vm.runInContext(fs.readFileSync(path.join(__dirname, '..', folder, file), 'utf8'), context);
    }
    const api = context.Boot37;
    assert.equal(api, context.Boot30);
    assert.equal(api.committed, false);
    context.Runtime20.startup37 = { reveals: 0 };
    events.get('document.submit')({ preventDefault: () => trace.push('prevent-submit') });
    events.get('document.click')({
        target: { closest: () => ({ href: '/other', hasAttribute: () => false }) },
        preventDefault: () => trace.push('prevent-navigation'),
    });
    events.get('document.load')({ target: { tagName: 'SCRIPT', src: 'asset.js' } });
    trace.push([message.textContent, progress.value]);
    if (scenario === 'script-error') {
        for (const cb of windowErrors) cb({ target: { tagName: 'SCRIPT' }, message: 'missing', filename: '/a/asset.js' });
    }
    await api.complete();
    api.commit();
    await Promise.all([api.complete(), api.complete()]);
    await api.complete();
    if (!retry.hidden) api.retry();
    return JSON.parse(JSON.stringify({ trace, style, theme: document.documentElement.dataset,
        diagnostics: context.Runtime20, committed: api.committed, finished: api.finished,
        message: message.textContent, progress: progress.value, retry: retry.hidden }));
}

(async () => {
    for (const scenario of ['success', 'failure', 'script-error', 'bad-storage']) {
        assert.deepEqual(await run('build/hunter-web', scenario), await run('apk_contents/assets', scenario), scenario);
    }
    console.log('El arranque Kotlin coincide en disponibilidad, progreso, recuperación, apariencia y eventos.');
})().catch(error => { console.error(error); process.exitCode = 1; });
