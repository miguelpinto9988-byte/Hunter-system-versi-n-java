/* Hunter System 38.3: safe drawing bounds for Android edge-to-edge WebView.
   Works with the existing ModalViewport.fit rather than adding a second offset.
   The complete-screen body background remains untouched. */
(function () {
  'use strict';
  const root = document.documentElement;
  const isAndroid = /Android/i.test(navigator.userAgent || '');
  let raf = 0;
  let confirmedEdgeToEdge = false;
  const px = n => Math.max(0, Number.isFinite(n) ? n : 0);
  const asPixels = (n) => Math.round(px(n) * 100) / 100 + 'px';

  function cssSafeAreas() {
    // Reading a custom property's text such as "env(safe-area-inset-top)"
    // with parseFloat returns NaN. Measure resolved padding on an element.
    let probe = document.getElementById('hunter-safe-probe383');
    if (!probe) {
      probe = document.createElement('div');
      probe.id = 'hunter-safe-probe383';
      probe.setAttribute('aria-hidden', 'true');
      probe.style.cssText = 'position:fixed!important;inset:auto!important;left:-2000px!important;top:-2000px!important;width:0!important;height:0!important;visibility:hidden!important;pointer-events:none!important;padding:env(safe-area-inset-top, 0px) env(safe-area-inset-right, 0px) env(safe-area-inset-bottom, 0px) env(safe-area-inset-left, 0px)!important;';
      document.body.appendChild(probe);
    }
    const c = getComputedStyle(probe);
    return {
      top: px(parseFloat(c.paddingTop)),
      right: px(parseFloat(c.paddingRight)),
      bottom: px(parseFloat(c.paddingBottom)),
      left: px(parseFloat(c.paddingLeft))
    };
  }

  function occupiesSystemArea() {
    // In edge-to-edge Android WebView the document often fills the screen.
    // When the native Activity already applies insets, avoid double padding.
    if (!isAndroid) return false;
    const screenH = Number(window.screen?.height) || 0;
    const docH = Number(window.innerHeight) || 0;
    if (!screenH || !docH) return false;
    const difference = screenH - docH;
    if (difference >= -12 && difference < 42) confirmedEdgeToEdge = true;
    // Keep the detected edge-to-edge state when the keyboard opens and shrinks innerHeight.
    return confirmedEdgeToEdge;
  }

  function refresh(forceFit = true) {
    if (!document.body) return;
    const area = cssSafeAreas();
    const fullScreen = occupiesSystemArea();
    const landscape = (window.innerWidth || 0) > (window.innerHeight || 0);
    // Fallback for older Android System WebView builds that return env(...)=0
    // despite Android 15+ drawing app content beneath system bars.
    const top = Math.max(area.top, fullScreen ? (landscape ? 24 : 30) : 0);
    const bottom = Math.max(area.bottom, fullScreen ? (landscape ? 12 : 18) : 0);
    const sides = {top, right: area.right, bottom, left: area.left};
    let changed = false;
    Object.entries(sides).forEach(([side, value]) => {
      const property = '--modal-safe-' + side;
      const numeric = asPixels(value);
      if (root.style.getPropertyValue(property) !== numeric) {
        root.style.setProperty(property, numeric);
        changed = true;
      }
    });
    for (const [property, value] of [
      ['--hunter-top-inset383', top],
      ['--hunter-bottom-inset383', bottom]
    ]) {
      const numeric = asPixels(value);
      if (root.style.getPropertyValue(property) !== numeric) {
        root.style.setProperty(property, numeric);
        changed = true;
      }
    }
    root.classList.add('hunter-insets383');
    if (forceFit && changed && document.getElementById('modal')?.open) {
      window.ModalViewport?.fit?.();
    }
  }

  function schedule() {
    if (raf) return;
    raf = requestAnimationFrame(() => { raf = 0; refresh(); });
  }
  if (document.readyState === 'loading') document.addEventListener('DOMContentLoaded', () => refresh(), {once:true});
  else refresh();
  window.addEventListener('resize', schedule, {passive:true});
  window.addEventListener('orientationchange', schedule, {passive:true});
  window.visualViewport?.addEventListener('resize', schedule, {passive:true});
  window.visualViewport?.addEventListener('scroll', schedule, {passive:true});
  window.HunterInsets383 = {refresh, occupiesSystemArea, cssSafeAreas};
})();
