/* Hunter System V38.44 — guarded fallback for Android WebView multi-touch
   zoom. Independent of mission state, dialogs, screen orientation or timing.
   One-finger navigation and normal page scrolling are left untouched. */
(function() {
  'use strict';
  if (window.HunterNoZoom3844) return;
  function blockMultiTouch(event) {
    if (event.cancelable && event.touches && event.touches.length > 1) {
      event.preventDefault();
    }
  }
  function blockGesture(event) {
    if (event.cancelable) event.preventDefault();
  }
  // A single passive:false listener handles multi-touch; no polling/timers.
  document.addEventListener('touchmove', blockMultiTouch, {capture:true, passive:false});
  // Some embedded browsers expose non-standard gesture events.
  document.addEventListener('gesturestart', blockGesture, {capture:true, passive:false});
  document.addEventListener('gesturechange', blockGesture, {capture:true, passive:false});
  window.HunterNoZoom3844 = Object.freeze({version:'38.44'});
})();
