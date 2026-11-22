/* Hunter System V38.5 – progressive enhancements, without creating windows.
 * Excluded roadmap features are not implemented here.
 * No changes to original exercise SVG/JSON, media or session timers.
 */
(function (w, d) {
  'use strict';
  if (w.HunterEvolution385) return;
  const STORAGE = 'hunter.visual.performance.v385';
  const LEVELS = ['E', 'D', 'C', 'B', 'A', 'S'];
  const MODES = new Set(['equilibrado', 'fluido', 'ahorro']);
  let selected = 'equilibrado';
  let appObserver = null;
  let updateScheduled = false;
  let lastRank = null;
  let lastAction = 0;
  let animations = 0;
  let rankTransitions = 0;
  let ignoredInputs = 0;
  let lastKnownCompleted = -1;
  const prefersReduced = () => Boolean(w.matchMedia?.('(prefers-reduced-motion: reduce)').matches);
  const now = () => (w.performance?.now ? w.performance.now() : Date.now());
  try { const choice = w.localStorage.getItem(STORAGE); if (MODES.has(choice)) selected = choice; } catch (_) {}

  function nativeRestricted() {
    const runtime = w.HunterV373?.runtime;
    return !!(d.hidden || prefersReduced() || runtime?.saver || runtime?.thermal >= 2);
  }
  function decideBudget() {
    if (nativeRestricted() || selected === 'ahorro' || w.HunterAdaptive3823?.quality === 'eco') return 'eco';
    // Browser callback Hz is only an estimate; never claim measured display FPS.
    const estimated = Number(w.HunterPerf380?.callbackHz);
    const cost = Number(w.HunterPerf380?.drawMs);
    if ((estimated > 0 && estimated < 49) || (cost > 3.8)) return 'eco';
    if (selected === 'fluido' && (estimated === 0 || estimated >= 59)) return 'alto';
    return 'medio';
  }
  function readRank() {
    try {
      if (typeof state !== 'undefined' && state && typeof Engine !== 'undefined' && Engine?.stats) {
        const level = Number(Engine.stats(state.logs || {}).level) || 1;
        return LEVELS[Math.max(0, Math.min(5, Math.floor((level - 1) / 5)))];
      }
    } catch (_) {}
    const badge = d.querySelector('.header-rank26 b, .rank-pill, .header-rank26');
    const match = badge?.textContent?.match(/\b([EDCBAS])\b/);
    return match ? match[1] : (lastRank || 'E');
  }
  function completedCount() {
    try {
      if (typeof state !== 'undefined' && state && state.logs) {
        return Object.values(state.logs).filter(x => x && x.complete === true).length;
      }
    } catch (_) {}
    return -1;
  }
  function pulse(target, kind) {
    if (!target || !target.isConnected || nativeRestricted()) return;
    if (decideBudget() === 'eco') return;
    if (now() - lastAction < 130) return;
    lastAction = now();
    const cls = kind === 'rank' ? 'hunter-rank-burst385' :
      kind === 'mission' ? 'hunter-mission-burst385' : 'hunter-ui-burst385';
    target.classList.remove(cls);
    void target.offsetWidth; // one intentional reflow after discrete user interaction
    target.classList.add(cls);
    animations++;
    w.setTimeout(() => { if (target.isConnected) target.classList.remove(cls); }, kind === 'rank' ? 1050 : 520);
  }
  function setRank() {
    const rank = readRank();
    d.documentElement.dataset.hunterRank385 = rank;
    if (lastRank && LEVELS.indexOf(rank) > LEVELS.indexOf(lastRank)) {
      rankTransitions++;
      pulse(d.querySelector('.hero, .command-hero, .header-rank26'), 'rank');
    }
    lastRank = rank;
  }
  function setMode(mode) {
    if (!MODES.has(mode)) return false;
    selected = mode;
    try { w.localStorage.setItem(STORAGE, mode); } catch (_) {}
    refresh();
    return true;
  }
  function profilePreference() {
    const form = d.querySelector('#profileForm');
    if (!form || form.querySelector('#hunterPowerMode385')) return;
    const submit = form.querySelector('button[type="submit"], input[type="submit"]');
    if (!submit) return;
    const field = d.createElement('div');
    field.className = 'field hunter-mode-field385';
    const label = d.createElement('label');
    label.className = 'label';
    label.htmlFor = 'hunterPowerMode385';
    label.textContent = 'Rendimiento visual';
    const select = d.createElement('select');
    select.id = 'hunterPowerMode385';
    select.setAttribute('aria-label', 'Rendimiento visual');
    [['equilibrado','Equilibrado'],['fluido','Máxima fluidez'],['ahorro','Ahorro de energía']].forEach(([id, title]) => {
      const option = d.createElement('option'); option.value = id; option.textContent = title;
      select.append(option);
    });
    select.value = selected;
    select.addEventListener('change', () => setMode(select.value), {passive: true});
    field.append(label, select);
    submit.before(field);
  }
  function refresh() {
    const html = d.documentElement;
    html.dataset.hunterFx385 = decideBudget();
    html.dataset.hunterMode385 = selected;
    html.classList.toggle('hunter-reduced385', prefersReduced());
    html.classList.toggle('hunter-hidden385', !!d.hidden);
    setRank();
    profilePreference();
    const cnt = completedCount();
    if (lastKnownCompleted >= 0 && cnt > lastKnownCompleted) {
      pulse(d.querySelector('.hero, .command-hero, .mission-launch'), 'mission');
    }
    if (cnt >= 0) lastKnownCompleted = cnt;
  }
  function queueRefresh() {
    if (updateScheduled) return;
    updateScheduled = true;
    const run = () => { updateScheduled = false; refresh(); };
    if (d.hidden) w.setTimeout(run, 180);
    else w.requestAnimationFrame(run);
  }
  function init() {
    refresh();
    const app = d.getElementById('app');
    if (app && typeof w.MutationObserver === 'function') {
      appObserver = new w.MutationObserver(queueRefresh);
      // Only listen for the app root rendering replacement, not individual SVG joints.
      appObserver.observe(app, {childList: true});
    }
    d.addEventListener('click', evt => {
      const btn = evt.target?.closest?.('button');
      if (!btn || !btn.isConnected) return;
      // Reuse existing buttons; no added popups, tooltips or notification panels.
      if (btn.matches('.btn.primary, .nav button, .day')) pulse(btn, 'ui');
      queueRefresh();
    }, {passive: true});
    d.addEventListener('visibilitychange', refresh, {passive: true});
    w.addEventListener('pageshow', queueRefresh, {passive: true});
    w.addEventListener('pagehide', () => { d.documentElement.classList.add('hunter-hidden385'); }, {passive: true});
    if (w.matchMedia) {
      try { w.matchMedia('(prefers-reduced-motion: reduce)').addEventListener('change', refresh); } catch (_) {}
    }
  }
  w.HunterEvolution385 = Object.freeze({
    version: '38.5',
    get mode() { return selected; },
    get budget() { return decideBudget(); },
    setMode, refresh: queueRefresh,
    diagnostics: () => ({mode: selected, budget: decideBudget(), rank: lastRank,
      transitions: animations, ascensos: rankTransitions, inputsIgnorados: ignoredInputs,
      visibility: d.hidden ? 'oculto' : 'visible',
      notice: 'No son mediciones de FPS ni de consumo de batería.'}),
    dispose: () => { appObserver?.disconnect(); appObserver = null; }
  });
  if (d.readyState === 'loading') d.addEventListener('DOMContentLoaded', init, {once: true});
  else init();
})(window, document);
