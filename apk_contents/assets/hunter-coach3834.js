/* Hunter System V38.34 — move Guía personal below the selected companion
   in the landscape tablet Compañero dialog. Does not alter training data,
   animation lifecycles, modal dimensions or phone layouts. */
(function () {
  'use strict';
  const modal = document.getElementById('modal');
  if (!modal || typeof window.openCoach !== 'function') return;
  const tablet = window.matchMedia('(orientation: landscape) and (min-width: 820px) and (min-height: 520px)');
  let managed = null;

  function restore() {
    if (!managed) return;
    const { card, anchor } = managed;
    if (anchor.isConnected && card.isConnected) anchor.after(card);
    anchor.remove();
    managed = null;
  }
  function sync() {
    if (!tablet.matches || !modal.open || !modal.classList.contains('coach-window33')) {
      restore();
      return;
    }
    const panel = modal.querySelector('.coach-layout33');
    const left = panel?.querySelector(':scope > .shadow-column33');
    const right = panel?.querySelector(':scope > .coach-content33');
    if (!left || !right) { restore(); return; }
    if (managed && managed.card.parentElement === left && managed.anchor.parentElement === right) return;
    restore();
    const card = right.querySelector(':scope > .learning-card');
    if (!card) return;
    const anchor = document.createComment('V38.34 original Guía personal position');
    card.before(anchor);
    left.append(card);
    managed = { card, anchor };
  }

  const beforeOpen = window.openCoach;
  window.openCoach = function (...args) {
    restore();
    const result = beforeOpen.apply(this, args);
    sync();
    return result;
  };
  modal.addEventListener('close', restore);
  const onViewportChange = () => sync();
  tablet.addEventListener?.('change', onViewportChange);
  window.addEventListener('resize', onViewportChange, { passive: true });
  window.HunterCoachLayout3834 = { sync, restore, get rearranged() { return !!managed; } };
})();
