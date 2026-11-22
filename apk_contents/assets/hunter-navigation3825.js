/* Hunter System V38.25 — existing navigation, shared visual transition.
 * No new windows, no panels, no changes to training progress or media.
 * Graceful fallback for WebViews without View Transitions API.
 */
(function (root, doc) {
  'use strict';
  if (root.HunterNavigation3825) return;
  const reduced = () => !!root.matchMedia?.('(prefers-reduced-motion: reduce)')?.matches;
  const eco = () => root.HunterEvolution385?.budget === 'eco' || root.HunterEvolution385?.mode === 'ahorro';
  let started = 0, skipped = 0, failed = 0, sequence48 = 0, active48 = null;
  function supported() { return typeof doc.startViewTransition === 'function'; }
  function canTransition(next) {
    // A persistent route shell has its own stable navigation. Its DOM route must
    // update synchronously so rapid taps never leave the selected tab stale.
    if (doc.querySelector('#app > nav.nav-routes3855')) return false;
    return supported() && !doc.hidden && !reduced() && !eco() &&
      !doc.querySelector('#modal[open]') && typeof tab !== 'undefined' && tab !== next;
  }
  if (typeof go !== 'function') return;
  const navigate = go;
  go = function (target) {
    const ticket48 = ++sequence48;
    // A superseded snapshot must not navigate back after the user's next tap.
    if (active48) { try { active48.skipTransition(); } catch (_) {} active48 = null; }
    if (!canTransition(target)) {
      skipped++;
      return navigate.apply(this, arguments);
    }
    const self = this, args = arguments;
    try {
      const effect = doc.startViewTransition(() => { if (ticket48 === sequence48) return navigate.apply(self, args); });
      active48 = effect;
      started++;
      // The transition may abort (new navigation or WebView lifecycle).
      // Avoid unhandled promise rejections; preserve the original go() fallback.
      if (effect?.finished?.then) effect.finished.then(() => { if (active48 === effect) active48 = null; }, () => { failed++; if (active48 === effect) active48 = null; });
      effect?.ready?.catch?.(() => {});
      effect?.updateCallbackDone?.catch?.(() => {});
      return effect;
    } catch (_) {
      failed++;
      return navigate.apply(this, arguments);
    }
  };
  root.HunterNavigation3825 = Object.freeze({
    version:'38.25', diagnostics: () => ({started,skipped,failed,supported:supported(),
      note:'Ninguna cifra es una medición de FPS ni de rendimiento Android.'})
  });
})(window, document);
