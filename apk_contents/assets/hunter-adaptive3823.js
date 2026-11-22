/* Hunter System V38.23 — adaptive visual workload & rank badge.
 * Approved roadmap: 20, 28, 72, 73, 81, 82, 88, 89 (partially).
 * No exercise demo changes, new UI windows, rank-based unlocks or ambient auras.
 * Does NOT measure Android screen FPS, battery temperature, or device thermal state.
 */
(function (root, doc) {
  'use strict';
  if (root.HunterAdaptive3823) return;
  const perf = root.HunterPerf380;
  const evolution = root.HunterEvolution385;
  const RANKS = ['E', 'D', 'C', 'B', 'A', 'S'];
  const TICK_MS = 12000;
  let current = 'normal';
  let lastLongTasks = Math.max(0, Number(perf?.longTasks) || 0);
  let highLoadPeriods = 0;
  let stablePeriods = 0;
  let checks = 0;
  let degrades = 0;
  let badgeAnimations = 0;
  let lastRank = null;
  let rankObserver = null;
  let timer = 0;
  let disposed = false;
  const reducedMotion = () => !!root.matchMedia?.('(prefers-reduced-motion: reduce)').matches;
  const manualEco = () => evolution?.mode === 'ahorro';

  function canAnimate() {
    return !disposed && !doc.hidden && !reducedMotion() && !manualEco() &&
      evolution?.budget !== 'eco';
  }
  function checkRank() {
    const rank = doc.documentElement?.dataset?.hunterRank385;
    if (!RANKS.includes(rank)) return;
    const rising = lastRank !== null && RANKS.indexOf(rank) > RANKS.indexOf(lastRank);
    lastRank = rank;
    if (!rising || !canAnimate()) return;
    // An existing badge only: no overlay, additional dialog or cosmetic unlock.
    const badge = doc.querySelector('.header-rank26 > b');
    if (!badge || typeof badge.animate !== 'function') return;
    try {
      badge.animate([
        { transform: 'scale(1)', opacity: 1 },
        { transform: 'scale(1.17)', opacity: 0.83, offset: .45 },
        { transform: 'scale(1)', opacity: 1 }
      ], { duration: 760, iterations: 1, easing: 'ease-out' });
      badgeAnimations++;
    } catch (_) { /* Unsupported in a particular WebView */ }
  }
  function sample() {
    if (disposed || doc.hidden) return;
    const longTasks = Math.max(0, Number(perf?.longTasks) || 0);
    const delta = Math.max(0, longTasks - lastLongTasks);
    lastLongTasks = longTasks;
    const drawMs = Number(perf?.drawMs) || 0;
    const drawCount = Number(perf?.drawCount) || 0;
    const slow = delta >= 4 || (drawCount >= 35 && drawMs > 5.2);
    checks++;
    if (slow) {
      highLoadPeriods++;
      stablePeriods = 0;
    } else {
      stablePeriods++;
      highLoadPeriods = 0;
    }
    if (highLoadPeriods >= 2 && current !== 'eco') {
      current = 'eco'; degrades++;
      root.HunterEvolution385?.refresh?.();
    } else if (stablePeriods >= 4 && current !== 'normal') {
      current = 'normal';
      root.HunterEvolution385?.refresh?.();
    }
    // This is a browser workload heuristic, not a CPU temperature measurement.
  }
  function clear() { if (timer) root.clearTimeout(timer); timer = 0; }
  function schedule() {
    clear();
    if (disposed || doc.hidden) return;
    timer = root.setTimeout(function onTick() { timer = 0; sample(); schedule(); }, TICK_MS);
  }
  function visibility() {
    if (doc.hidden) { clear(); return; }
    lastLongTasks = Math.max(0, Number(perf?.longTasks) || 0);
    stablePeriods = 0;
    highLoadPeriods = 0;
    schedule();
    checkRank();
  }
  function dispose() {
    disposed = true;
    clear();
    rankObserver?.disconnect();
    doc.removeEventListener('visibilitychange', visibility);
  }
  root.HunterAdaptive3823 = Object.freeze({
    version: '38.23',
    get quality() { return current; },
    check: sample,
    diagnostics: () => ({quality: current, checks, degrades, badgeAnimations,
      note: 'Indicadores de carga JavaScript; no equivalen a FPS, batería ni temperatura Android.'}),
    dispose
  });
  function init() {
    if (disposed) return;
    const NodeObserver = root.MutationObserver;
    if (typeof NodeObserver === 'function' && doc.documentElement) {
      rankObserver = new NodeObserver(checkRank);
      rankObserver.observe(doc.documentElement, {attributes: true, attributeFilter: ['data-hunter-rank385']});
    }
    checkRank();
    schedule();
    doc.addEventListener('visibilitychange', visibility, {passive: true});
  }
  if (doc.readyState === 'loading') doc.addEventListener('DOMContentLoaded', init, {once: true});
  else init();
})(window, document);
