const { originalActions } = require('./acciones_originales.cjs');
const assert = require('node:assert/strict');
const path = require('node:path');
const { chromium } = require('playwright');

const origin = process.env.HUNTER_TEST_ORIGIN || 'http://127.0.0.1:8575';
const root = path.resolve(__dirname, '..');
const viewports = [
    { width: 393, height: 852 },
    { width: 768, height: 1024 },
    { width: 1024, height: 768 },
];

async function capture(browser, folder, viewport) {
    const page = await browser.newPage({ viewport, timezoneId: 'UTC', reducedMotion: 'reduce' });
    try {
        const errors = [];
        page.on('pageerror', error => errors.push(error.message));
        await page.route('**/apk_contents/assets/fonts/*.ttf', route => route.fulfill({
            path: path.join(root, 'build/hunter-web/fonts', path.basename(route.request().url())),
        }));
        await page.goto(origin + '/' + folder + '/index.html', { waitUntil: 'domcontentloaded' });
        await page.locator('#profileForm').waitFor({ state: 'visible' });
        await page.evaluate(() => document.fonts.ready);
        const profile = await page.locator('#profileForm').evaluate(form => form.outerHTML);
        const result = await page.evaluate(() => {
            const api = window.ModalViewport;
            const dialog = document.getElementById('modal');
            const body = document.body;
            const styles = () => Object.fromEntries(['position', 'top', 'left', 'padding-right', 'width', 'overflow'].map(key =>
                [key, [body.style.getPropertyValue(key), body.style.getPropertyPriority(key)]]));
            const before = styles();
            const generation = api.generation;
            api.begin();
            dialog.innerHTML = '<section class="guided-session"><h2>Sesión guiada</h2><button>Continuar</button></section>';
            dialog.showModal();
            api.reset();
            api.fit();
            const rect = dialog.getBoundingClientRect();
            const open = {
                viewport: api.viewport(), width: rect.width, height: rect.height,
                top: rect.top, left: rect.left, classes: dialog.className, styles: styles(),
                tablet: dialog.classList.contains('hunter-tablet-guide3830'),
                generation: api.generation - generation,
            };
            return { open, before };
        });
        const screenshot = await page.locator('#modal').screenshot({ animations: 'disabled' });
        const after = await page.evaluate(() => {
            window.ModalViewport.beforeNavigation();
            const body = document.body;
            return Object.fromEntries(['position', 'top', 'left', 'padding-right', 'width', 'overflow'].map(key =>
                [key, [body.style.getPropertyValue(key), body.style.getPropertyPriority(key)]]));
        });
        assert.deepEqual(after, result.before);
        assert.equal(result.open.tablet, viewport.width >= 600);
        assert.deepEqual(errors, []);
        return { profile, open: result.open, screenshot };
    } finally {
        await page.close();
    }
}

(async () => {
    const executablePath = process.env.CHROMIUM_PATH || undefined;
    const browser = await chromium.launch({ executablePath, args: ['--no-sandbox'] });
    try {
        for (const viewport of viewports) {
            const expected = await capture(browser, 'apk_contents/assets', viewport);
            const actual = await capture(browser, 'build/hunter-web', viewport);
            assert.equal(originalActions(actual.profile), expected.profile);
            assert.deepEqual(actual.open, expected.open);
            assert.ok(actual.screenshot.equals(expected.screenshot), 'Captura de diálogo diferente a ' + viewport.width);
        }
        console.log('El formulario y los diálogos coinciden en teléfono y tableta, en tres tamaños.');
    } finally {
        await browser.close();
    }
})().catch(error => { console.error(error); process.exitCode = 1; });
