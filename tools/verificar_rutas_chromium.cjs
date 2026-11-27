const { originalActions } = require('./acciones_originales.cjs');
const assert = require('node:assert/strict');
const path = require('node:path');
const { chromium } = require('playwright');

const origin = process.env.HUNTER_TEST_ORIGIN || 'http://127.0.0.1:8575';
const root = path.resolve(__dirname, '..');
const candidate = process.env.HUNTER_BUILD_PATH || 'build/hunter-web';

async function tour(browser, folder) {
    const page = await browser.newPage({ viewport: { width: 393, height: 852 }, timezoneId: 'UTC', reducedMotion: 'reduce' });
    const errors = [];
    page.on('pageerror', error => errors.push(error.stack));
    try {
        await page.clock.setFixedTime(new Date('2026-11-24T12:00:00Z'));
        await page.route('**/apk_contents/assets/fonts/*.ttf', route => route.fulfill({
            path: path.join(root, 'build/hunter-web/fonts', path.basename(route.request().url())),
        }));
        await page.goto(origin + '/' + folder + '/index.html', { waitUntil: 'domcontentloaded' });
        const form = page.locator('#profileForm');
        await form.waitFor({ state: 'attached' });
        await page.waitForFunction(() => !Runtime20.initializing && !document.documentElement.classList.contains('booting20'));
        await page.evaluate(() => render());
        await form.evaluate(form => {
            for (const [key, value] of Object.entries({ name: 'Ana', age: 30, weight: 65, height: 165 })) form.elements[key].value = value;
            const dumbbells = form.querySelector('[name="equipmentModes3880"][value="db"]');
            if (!dumbbells) throw new Error('El perfil debe ofrecer mancuernas');
            dumbbells.click();
            form.elements.load.value = 5;
            form.requestSubmit();
        });
        const result = await page.evaluate(() => {
            const profile = JSON.parse(JSON.stringify(state.profile));
            const cycle = JSON.parse(JSON.stringify(currentCycle()));
            const routes = [];
            for (const key of ['home', 'plan', 'workout', 'calendar', 'routines', 'book', 'stats', 'goals', 'reminders', 'monarch', 'profile']) {
                go(key);
                const main = document.querySelector('#app main') || document.querySelector('#app');
                routes.push({
                    key, tab, text: main.textContent.replace(/\s+/g, ' ').trim(),
                    controls: [...main.querySelectorAll('input, select, button, textarea')].map(el =>
                        [el.tagName, el.name, el.type, el.getAttribute('onclick'), el.disabled]),
                    media: [...main.querySelectorAll('img, canvas, video')].map(el =>
                        [el.tagName, el.getAttribute('src'), el.className]),
                });
            }
            return { profile, cycle, routes };
        });
        await page.reload({ waitUntil: 'domcontentloaded' });
        await page.waitForFunction(() => !Runtime20.initializing && !document.documentElement.classList.contains('booting20'));
        const storedName = await page.evaluate(() => state.profile?.name);
        assert.equal(storedName, 'Ana', folder + ' debe recuperar el perfil');
        assert.deepEqual(errors, [], folder);
        return result;
    } finally { await page.close(); }
}

(async () => {
    const browser = await chromium.launch({ executablePath: process.env.CHROMIUM_PATH || undefined, args: ['--no-sandbox'] });
    try {
        const expected = await tour(browser, 'apk_contents/assets');
        const actual = await tour(browser, candidate);
        assert.deepEqual(actual.profile, expected.profile, 'Perfil');
        assert.deepEqual(actual.cycle, expected.cycle, 'Plan y dosis');
        for (let i = 0; i < expected.routes.length; i++) {
            for (const control of actual.routes[i].controls) if (control[3]) control[3] = originalActions(control[3]);
            assert.deepEqual(actual.routes[i], expected.routes[i], expected.routes[i].key);
        }
        console.log('Perfil, recuperación del guardado, plan y contenido de once destinos coinciden en Chromium.');
    } finally { await browser.close(); }
})().catch(error => { console.error(error); process.exitCode = 1; });
