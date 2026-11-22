/* Hunter System V38.9 — articulated gesture transitions.
 * Changes only existing SVG joint rotations; no HTML panels, Canvas, timers,
 * automatic exercise repetition, new breathing/shadows, or data storage.
 * The original exercise-demonstration timing and visual assets are untouched.
 * Roadmap: 22/30/41 (partial), not the excluded items.
 */
(function (win, doc) {
  'use strict';
  if (win.HunterRig389) return;
  const Actor = win.IgrisCharacter;
  if (!Actor || typeof Actor.prototype.sample !== 'function') return;
  const originalSample = Actor.prototype.sample;
  const caches = new WeakMap();
  const limbs = ['head','armLeft','armRight','legLeft','legRight',
    'wingLeft','wingRight','antennaLeft','antennaRight'];
  const RE = /^\s*rotate\(\s*(-?(?:\d+(?:\.\d*)?|\.\d+))(?:\s+(-?(?:\d+(?:\.\d*)?|\.\d+))\s+(-?(?:\d+(?:\.\d*)?|\.\d+)))?\s*\)\s*$/;
  const DURATION = Object.freeze({idle:240,entering:220,exiting:180,
    celebrate:165,bow:165,wave:165,point:165,think:165,nod:165,
    look:165,stretch:165,listen:165,walk:165,flip:140});
  let blended = 0, skipped = 0, changes = 0;
  function decode(value) {
    const hit = typeof value === 'string' && value.match(RE);
    if (!hit) return null;
    const a = +hit[1], x = hit[2] === undefined ? 0 : +hit[2], y = hit[3] === undefined ? 0 : +hit[3];
    return [a,x,y].every(Number.isFinite) ? {a,x,y} : null;
  }
  function pose(actor) {
    const out = new Map();
    for (const part of limbs) {
      const node = actor.parts?.[part];
      if (!node || typeof node.getAttribute !== 'function') continue;
      const parsed = decode(node.getAttribute('transform'));
      if (parsed) out.set(part, parsed);
    }
    return out;
  }
  const curve = t => t*t*(3-2*t);
  function blendAngle(start, finish, t) {
    // Each independent articulated bone uses the shortest signed angular path.
    let diff = ((finish-start+540)%360)-180;
    return start + diff*t;
  }
  function safe(actor) {
    return !doc.hidden && !actor.paused && !actor.destroyed && !actor.reduced &&
      !win.matchMedia?.('(prefers-reduced-motion: reduce)')?.matches &&
      win.HunterEvolution385?.budget !== 'eco';
  }
  Actor.prototype.sample = function (state, ms) {
    // When changing state, capture the ACTUAL displayed joint angles before
    // running the existing character/particle layers to avoid visible jumps.
    let cache = caches.get(this);
    if (!cache) {
      cache = {state: null, from:new Map(), until:0};
      caches.set(this, cache);
    }
    const switched = cache.state !== state;
    if (switched) {
      cache.from = pose(this);
      cache.state = state;
      cache.until = Number(DURATION[state] || 150);
      changes++;
    }
    const out = originalSample.apply(this, arguments);
    if (!safe(this) || state === 'hidden' || !cache.from.size) {skipped++; return out;}
    const elapsed = Number(ms);
    if (!Number.isFinite(elapsed) || elapsed < 0 || elapsed >= cache.until) return out;
    const current = pose(this);
    const t = curve(Math.min(1, elapsed/cache.until));
    for (const [part,target] of current) {
      const previous = cache.from.get(part);
      if (!previous) continue;
      const angle = blendAngle(previous.a,target.a,t);
      if (Math.abs(angle-target.a) < .005) continue;
      const node=this.parts[part];
      // The part's final pivot is preserved; only its rotation is interpolated.
      const val = `rotate(${angle.toFixed(3)} ${target.x} ${target.y})`;
      if (node.getAttribute('transform') !== val) {node.setAttribute('transform',val);blended++;}
    }
    return out;
  };
  win.HunterRig389 = Object.freeze({
    version:'38.9',
    diagnostics:()=>({transitions:changes,blendedJointUpdates:blended,skipped,
      note:'Conteo interno. No constituye una medición de FPS o batería.'})
  });
})(window,document);
