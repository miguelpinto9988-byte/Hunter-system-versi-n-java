/* Hunter System V38.6 — EVOLUTION, etapa 2.
 * Gestos contextuales sin ventanas, suspensión fuera de pantalla y
 * control de visibilidad. No altera los 1000 modelos, imágenes, ni el reloj
 * de las demostraciones. No introduce ninguna mejora descartada.
 */
(function (root, document) {
  'use strict';
  if (root.HunterEvolution386) return;
  const Character = root.IgrisCharacter;
  if (!Character || !Character.prototype) return;
  const proto = Character.prototype;
  const original = {
    open: proto.open,
    destroy: proto.destroy,
    sample: proto.sample
  };
  // Aprovechar los cuatro puntos del SVG existente, sin agregar partículas,
  // capas, ventanas, filtros de blur o superficies Canvas adicionales.
  const particleColors = Object.freeze({
    knight:'#ffa09b', warrior:'#95e7ff', bear:'#d5eaff',
    insect:'#91ffe8', scientist:'#f0e7ff'
  });
  proto.sample = function (state, ms) {
    const result = original.sample.call(this, state, ms);
    const dots = this.parts?.particles;
    if (!dots) return result;
    const fill = particleColors[this.type] || '#a5ecff';
    if (dots.getAttribute('fill') !== fill) dots.setAttribute('fill',fill);
    if (this.reduced || document.hidden || root.HunterEvolution385?.budget === 'eco') return result;
    // Destellos breves sobre partículas existentes: entrada, salida y misión.
    // Estados normales mantienen la opacidad calculada por animation.js.
    const duration = state === 'entering' ? 980 : state === 'exiting' ? 760 : 1600;
    const phase = Math.max(0, Math.min(1, (Number(ms) || 0)/duration));
    if (state === 'entering' || state === 'exiting' || state === 'celebrate') {
      const shine = Math.sin(Math.PI*phase);
      const opacity = Math.min(.9, .28 + .52*shine);
      dots.setAttribute('opacity', opacity.toFixed(3));
    }
    return result;
  };
  const tracked = new Set();
  const suspended = new WeakSet();
  const observed = new WeakSet();
  const observers = new WeakMap();
  let suspendedCount = 0, resumedCount = 0, reactions = 0;
  let lastReaction = 0, previousRank = null;
  let rankObserver = null;

  const reduceMotion = () => Boolean(root.matchMedia?.('(prefers-reduced-motion: reduce)').matches);
  const now = () => root.performance?.now ? root.performance.now() : Date.now();
  function active(actor) {
    return !!(actor && actor.host?.isConnected && !actor.destroyed && !actor.paused &&
      actor.state !== 'hidden' && actor.state !== 'exiting');
  }
  function inViewport(actor) {
    if (!actor.host?.getBoundingClientRect) return true;
    const r = actor.host.getBoundingClientRect();
    return r.width > 0 && r.height > 0 && r.right >= 0 && r.bottom >= 0 &&
      r.left < root.innerWidth && r.top < root.innerHeight;
  }
  function attach(actor) {
    if (!actor?.host || observed.has(actor)) return;
    observed.add(actor);
    tracked.add(actor);
    if (!('IntersectionObserver' in root)) return;
    const io = new root.IntersectionObserver((entries) => {
      const entry = entries[entries.length - 1];
      if (!entry || actor.destroyed) return;
      const visible = !!entry.isIntersecting && !document.hidden;
      if (!visible && !actor.paused && actor.state !== 'hidden') {
        suspended.add(actor);
        actor.pause(); // Existing pause() also stops automatic motion and rAF.
        suspendedCount++;
      } else if (visible && suspended.has(actor)) {
        suspended.delete(actor);
        if (actor.paused && actor.state !== 'hidden') {
          actor.resume();
          resumedCount++;
        }
      }
    }, {threshold: 0, rootMargin: '8px'});
    observers.set(actor, io);
    io.observe(actor.host);
  }
  proto.open = function (...args) {
    const result = original.open.apply(this, args);
    attach(this);
    return result;
  };
  proto.destroy = function (...args) {
    const io = observers.get(this);
    if (io) io.disconnect();
    tracked.delete(this);
    suspended.delete(this);
    return original.destroy.apply(this, args);
  };
  // Visibility handlers are owned by IgrisCharacter. Avoid adding another
  // visibilitychange listener per instance, and avoid forcing a manual pause off.
  function findVisibleActor() {
    // Use instances already created by the application. Never instantiate new
    // characters just to play a reaction, avoiding duplicated SVGs and timers.
    for (const actor of tracked) if (active(actor) && inViewport(actor)) return actor;
    return null;
  }
  function react(gesture) {
    if (reduceMotion() || document.hidden) return false;
    if (root.HunterEvolution385?.mode === 'ahorro') return false;
    if (now() - lastReaction < 1800) return false;
    const actor = findVisibleActor();
    if (!actor || typeof actor.perform !== 'function') return false;
    actor.perform(gesture);
    lastReaction = now(); reactions++;
    return true;
  }
  function onMissionCompleted() { react('celebrate'); }
  root.addEventListener('hunter:mission-completed', onMissionCompleted);

  const RANKS = ['E', 'D', 'C', 'B', 'A', 'S'];
  function checkRank() {
    const rank = document.documentElement?.dataset?.hunterRank385;
    if (!RANKS.includes(rank)) return;
    if (previousRank && RANKS.indexOf(rank) > RANKS.indexOf(previousRank)) react('bow');
    previousRank = rank;
  }
  if ('MutationObserver' in root && document.documentElement) {
    rankObserver = new root.MutationObserver(checkRank);
    rankObserver.observe(document.documentElement, {attributes: true, attributeFilter: ['data-hunter-rank385']});
    checkRank();
  }
  root.HunterEvolution386 = Object.freeze({
    version: '38.6',
    diagnostics: () => Object.freeze({actors: tracked.size, suspendedCount, resumedCount, reactions}),
    // Explicit invocation supports component tests. Does not expose controls in UI.
    notifyMissionCompleted: onMissionCompleted
  });
})(window, document);
