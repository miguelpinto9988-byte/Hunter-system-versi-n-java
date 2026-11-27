const assert = require('node:assert/strict');
const { createHash } = require('node:crypto');
const { chromium } = require('playwright');

async function frames(browser, folder) {
    const page = await browser.newPage({ reducedMotion: 'reduce' });
    try {
        const origin = process.env.HUNTER_TEST_ORIGIN || 'http://127.0.0.1:8575';
        await page.goto(origin + '/' + folder + '/index.html', { waitUntil: 'domcontentloaded' });
        const captures = await page.evaluate(() => {
            const canvas = document.createElement('canvas');
            canvas.width = 360;
            canvas.height = 400;
            const host = document.createElement('div');
            host.append(canvas);
            const images = [];
            for (const id of ['chair', 'walk', 'bridge']) {
                for (const phase of [0, 16, 32]) {
                    for (const mirror of [false, true]) {
                        Motion2D18.draw(canvas, id, phase, mirror);
                        images.push(canvas.nextElementSibling.innerHTML);
                    }
                }
            }
            return images;
        });
        return captures.map(image => createHash('sha256').update(image).digest('hex'));
    } finally { await page.close(); }
}

(async () => {
    const browser = await chromium.launch({ executablePath: process.env.CHROMIUM_PATH || undefined, args: ['--no-sandbox'] });
    try {
        const expected = await frames(browser, 'apk_contents/assets');
        assert.ok(new Set(expected).size > 3);
        assert.deepEqual(await frames(browser, 'build/hunter-web'), expected);
        console.log('Coinciden los 18 fotogramas de los tres ejercicios, incluida la vista en espejo.');
    } finally { await browser.close(); }
})().catch(error => { console.error(error); process.exitCode = 1; });
