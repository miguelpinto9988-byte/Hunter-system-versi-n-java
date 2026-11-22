/* Hunter System V38.4: bounded animation-model memory, background power discipline and diagnostics.
 * Retains all exercise definitions, graphics, profiles, progress and timing behavior.
 */
(function (root, document) {
  'use strict';
  const VERSION = '38.4';
  const MAX_MODELS = 96;
  const RETAIN_MODELS = 48;
  let cleanupRequest = 0;
  let cleanupTimeout = 0;
  let trims = 0;
  let removedModels = 0;

  function runningDemonstration() {
    const live = root.HunterV373?.demos?.entry;
    const modal = document.getElementById('modal');
    // A profile/settings modal is NOT an active demonstration.
    // Keep active exercise and guided-player resources pinned while visible.
    const exerciseModal = Boolean(modal?.open && (
      modal.dataset.guide === 'true' ||
      modal.querySelector('#guidedCanvas, #guidedSlot, #vectorCanvas, .motion-card18, .offline-demo20')
    ));
    return Boolean((live && live.canvas?.isConnected) || exerciseModal);
  }

  function trimModels(force = false) {
    // Never remove a model in a playing/open demonstration, or a preloaded
    // calibrated model used for an exact trajectory.
    const motion = root.Motion30;
    const cache = motion?.cache;
    if (!cache || !('size' in cache) || cache.size <= MAX_MODELS || runningDemonstration()) return 0;
    const pinned = motion.loaded || new Set();
    const protectedIds = new Set();
    if (root.vectorSession?.id) protectedIds.add(root.vectorSession.id);
    if (root.guide11?.steps?.length && Number.isInteger(root.guide11.index)) {
      protectedIds.add(root.guide11.steps[root.guide11.index]?.id);
    }
    let removed = 0;
    // Insertion order gives a conservative eviction order (oldest generated first).
    for (const id of cache.keys()) {
      if (cache.size <= RETAIN_MODELS) break;
      if (protectedIds.has(id)) continue;
      // An old preloaded model may be discarded once all demonstrations close.
      // Clear its flag too so Motion30.preload can fetch it again on demand.
      if (pinned.has(id)) pinned.delete(id);
      cache.delete(id); removed++;
    }
    if (removed) { trims++; removedModels += removed; }
    return removed;
  }

  function scheduleTrim(force = false) {
    if (cleanupRequest) return;
    if (typeof root.requestIdleCallback === 'function') {
      cleanupRequest = root.requestIdleCallback(() => { cleanupRequest = 0; trimModels(force); }, {timeout: 1500});
    } else {
      cleanupRequest = -1;
      cleanupTimeout = root.setTimeout(() => { cleanupRequest = 0; cleanupTimeout = 0; trimModels(force); }, 400);
    }
  }

  function countActiveMedia() {
    return [...document.querySelectorAll('video')].filter(v => !v.paused).length;
  }

  // Keep large original image files untouched; lazy-load ONLY secondary, offscreen thumbnails.
  // This does not reduce image resolution or JPEG/WebP quality.
  function optimizeThumbnails(scope = document) {
    if (!scope.querySelectorAll) return;
    for (const img of scope.querySelectorAll('.demo-gallery img, .routine-list img, .exercise-grid img')) {
      if (img.closest('.hero-figure33, .evolution-art28') || img.dataset.hunterPriority === 'high') continue;
      img.decoding = 'async';
      if (!img.complete) img.loading = 'lazy';
    }
  }

  document.addEventListener('visibilitychange', () => {
    if (document.hidden) {
      for (const video of document.querySelectorAll('video')) if (!video.paused) video.pause();
      scheduleTrim(true);
    }
  });
  root.addEventListener('pagehide', () => {
    for (const video of document.querySelectorAll('video')) if (!video.paused) video.pause();
    trimModels(true);
  }, {passive:true});
  function initialize() {
    optimizeThumbnails(document);
    const modal = document.getElementById('modal');
    if (modal) modal.addEventListener('close', () => scheduleTrim(), {passive:true});
  }
  if (document.readyState === 'loading') document.addEventListener('DOMContentLoaded', initialize, {once:true});
  else initialize();
  root.HunterResource384 = Object.freeze({
    version: VERSION,
    trimModels,
    optimizeThumbnails,
    snapshot: () => ({
      version: VERSION,
      cachedMotionModels: root.Motion30?.cache?.size ?? null,
      protectedPreloadedModels: root.Motion30?.loaded?.size ?? null,
      modelCacheLimit: MAX_MODELS,
      totalTrimOperations: trims,
      generatedModelsDiscarded: removedModels,
      activeMedia: countActiveMedia(),
      particleDraws: root.Ambient29?.paints373 ?? null,
      note: 'Auditoría interna; FPS, temperatura, batería y GPU deben medirse en un dispositivo Android.'
    })
  });
})(window, document);
