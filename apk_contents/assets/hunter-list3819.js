/* Hunter System V38.22 — coalesced exercise-library observation.
 * Adds NO windows, explanatory panels or user-facing controls.
 * Existing card markup and 1000 original illustrations are unchanged.
 */
(function (root, document) {
  'use strict';
  if (root.HunterList3819) return;
  const cards = root.exerciseCards;
  if (typeof cards !== 'function') return;
  // Smaller insertion batches shorten the main-thread interruption while the
  // user scrolls. Keep all cards and the native scroller; this is not pagination.
  const BATCH = 12;
  const LIMIT = 72;
  const MORE = 'hunter-more3819';
  const canDefer = typeof root.IntersectionObserver === 'function' &&
    typeof root.MutationObserver === 'function' &&
    typeof root.requestAnimationFrame === 'function';
  let generation = 0, visible = 0, pending = 0;
  let entries = [], marker = null, watching = null;
  let observer = null, mountObserver = null;
  let appended = 0, renderCalls = 0, aborted = 0, maxAppendMs48 = 0;
  // Multiple DOM changes during one render need only one attachment check.
  let attachFrame = 0, attachChecks = 0, coalescedMutations = 0;

  function cancelPending() {
    if (pending) root.cancelAnimationFrame(pending);
    pending = 0;
  }
  function abandon() {
    generation++;
    cancelPending();
    if (observer && watching) observer.unobserve(watching);
    watching = null;
    marker = null;
    entries = [];
    visible = 0;
  }
  function activeMarker() {
    const grid = document.getElementById('exerciseResults');
    return grid && marker && marker.isConnected &&
      marker.parentElement === grid && marker.classList?.contains(MORE);
  }
  function appendBatch(token) {
    pending = 0;
    if (token !== generation || document.hidden || !activeMarker()) {aborted++;return;}
    const end = Math.min(entries.length, visible + BATCH);
    const group = entries.slice(visible, end);
    // Use the same renderer which V38.18 already patches for equipment labels.
    // The selector is never duplicated and a single fragment inserts the cards.
    const began = performance.now();
    if (group.length) marker.insertAdjacentHTML('beforebegin', cards(group));
    maxAppendMs48 = Math.max(maxAppendMs48, performance.now() - began);
    visible = end;
    appended += group.length;
    if (visible >= entries.length) {
      if (observer) observer.unobserve(marker);
      marker.remove();
      marker = watching = null;
      entries = [];
    } else if (observer) {
      // Re-arm after moving the sentinel: a small batch can leave it inside the
      // prefetch margin, which otherwise gives no second intersection event.
      observer.unobserve(marker);
      observer.observe(marker);
    }
  }
  function queueBatch() {
    if (pending || document.hidden || !activeMarker()) return;
    const token = generation;
    pending = root.requestAnimationFrame(() => appendBatch(token));
  }
  function markerNearViewport() {
    if (!activeMarker() || typeof marker.getBoundingClientRect !== 'function') return false;
    const r = marker.getBoundingClientRect();
    return r.top < (root.innerHeight || 640) + 560 && r.bottom >= -560;
  }
  function attach() {
    attachChecks++;
    const grid = document.getElementById('exerciseResults');
    const target = grid?.querySelector('.' + MORE);
    if (!target || !entries.length) {
      if (observer && watching) observer.unobserve(watching);
      watching = null;
      marker = null;
      // Release a deferred list after leaving the library, without retaining its
      // pending rows and an obsolete append request until the next visit.
      if (!grid && entries.length) abandon();
      return;
    }
    if (target === watching) return;
    if (observer && watching) observer.unobserve(watching);
    marker = watching = target;
    observer.observe(target);
  }
  function scheduleAttach() {
    if (document.hidden || !canDefer) return;
    if (attachFrame) {coalescedMutations++; return;}
    attachFrame = root.requestAnimationFrame(() => {
      attachFrame = 0;
      if (!document.hidden) attach();
    });
  }
  root.exerciseCards = function (list) {
    renderCalls++;
    if (!canDefer || !Array.isArray(list) || list.length <= LIMIT) {
      abandon();
      return cards.apply(this, arguments);
    }
    abandon();
    entries = list.slice();
    visible = BATCH;
    const first = cards(entries.slice(0, BATCH));
    // An invisible sentinel only; no new card/panel or altered layout.
    return first + '<div class="' + MORE + '" aria-hidden="true"></div>';
  };
  if (canDefer) {
    observer = new root.IntersectionObserver((intersections) => {
      if (intersections.some(e => e.target === watching && e.isIntersecting)) queueBatch();
    }, {root: null, rootMargin: '560px 0px', threshold: 0});
    const app = document.getElementById('app');
    if (app) {
      mountObserver = new root.MutationObserver(scheduleAttach);
      // Observe DOM attachment, not layout/style mutations.
      mountObserver.observe(app, {childList: true, subtree: true});
    }
    document.addEventListener('visibilitychange', () => {
      if (document.hidden) cancelPending();
      else { scheduleAttach(); if (markerNearViewport()) queueBatch(); }
    }, {passive:true});
    root.addEventListener('pagehide', () => {
      cancelPending();
      if (attachFrame) root.cancelAnimationFrame(attachFrame);
      attachFrame = 0;
    }, {passive:true});
  }
  root.HunterList3819 = Object.freeze({
    version: '38.19',
    refresh: scheduleAttach,
    diagnostics: () => Object.freeze({renderCalls, appended, aborted, deferred:canDefer,
      pendingCount: entries.length ? entries.length - visible : 0,
      hasSentinel:!!watching, attachChecks, coalescedMutations, attachQueued:!!attachFrame,
      batchSize:BATCH,maxAppendMs:maxAppendMs48})
  });
})(window, document);
