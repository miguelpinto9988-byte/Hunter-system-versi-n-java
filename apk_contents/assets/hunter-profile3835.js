/* Hunter System V38.35 — explicit acknowledgement of profile save.
 * Scope: only the existing .profile-confirm377 modal.
 * Recalculation and persistence are delegated to V38.29 and older layers.
 * A profile confirmation is NOT an auto-dismiss toast.
 */
(function (root, doc) {
  'use strict';
  if (root.HunterProfileConfirmation3835 || typeof submitProfile !== 'function' ||
      typeof render !== 'function' || typeof modal !== 'function') return;
  const dialog = doc.getElementById('modal');
  if (!dialog) return;
  let saving = false;
  let awaiting = false;
  let acknowledged = false;
  let deferredRender = false;
  let confirmations = 0;
  let rejectedCloses = 0;

  function active() {
    return awaiting && !acknowledged && !!dialog.querySelector('.profile-confirm377');
  }
  const baseClose = dialog.close.bind(dialog);
  dialog.close = function (value) {
    if (active()) { rejectedCloses++; return; }
    return baseClose(value);
  };

  const renderBefore = render;
  render = function () {
    // Legacy profile save may re-render after showing the confirmation.
    // Defer that refresh until the user acknowledges; otherwise the dialog can
    // disappear while a save/plan update is still finishing.
    if (saving && active()) { deferredRender = true; return; }
    return renderBefore.apply(this, arguments);
  };

  function arm() {
    const pane = dialog.querySelector('.profile-confirm377');
    if (!pane || !dialog.open || (awaiting && !acknowledged)) return;
    awaiting = true;
    acknowledged = false;
    confirmations++;
    dialog.classList.add('profile-confirm-wait3835');
    dialog.setAttribute('aria-label', 'Confirmación del perfil guardado');
    const button = pane.querySelector('button[onclick*="closeActiveModal"],button.btn.primary');
    if (button) {
      button.removeAttribute('onclick');
      button.textContent = 'Confirmar y continuar';
      button.setAttribute('aria-label','Confirmar el mensaje de perfil guardado y continuar');
      button.addEventListener('click', acknowledge, {once: true});
    }
  }

  function acknowledge() {
    if (!active()) return false;
    acknowledged = true;
    awaiting = false;
    dialog.classList.remove('profile-confirm-wait3835');
    dialog.removeAttribute('aria-label');
    dialog.close();
    if (deferredRender) {
      deferredRender = false;
      root.requestAnimationFrame(() => render());
    }
    return true;
  }

  // Modal is called by the existing V37.7 profile confirmation itself.
  // Intercepting here arms protection BEFORE the V38.29 regeneration completes.
  const modalBefore = modal;
  modal = function (html) {
    const answer = modalBefore.apply(this, arguments);
    if (typeof html === 'string' && html.includes('profile-confirm377')) arm();
    return answer;
  };
  const submitBefore = submitProfile;
  submitProfile = function (event) {
    if (event?.target?.id !== 'profileForm') return submitBefore.apply(this, arguments);
    saving = true;
    try { return submitBefore.apply(this, arguments); }
    finally { saving = false; if (dialog.open) arm(); }
  };
  // Some WebViews send a native cancel before the app's back-navigation handler.
  // Only intercept cancel for the profile confirmation, not for other windows.
  root.addEventListener('cancel', function (event) {
    if (event.target === dialog && active()) {
      event.preventDefault();
      event.stopImmediatePropagation();
    }
  }, true);
  dialog.addEventListener('click', function (event) {
    if (active() && event.target.closest('.modal-close-x,.modal-back24,.legacy-modal-close')) {
      event.preventDefault();
      event.stopImmediatePropagation();
    }
  }, true);
  root.HunterProfileConfirmation3835 = Object.freeze({
    version:'38.35', acknowledge,
    diagnostics:()=>({confirmations,rejectedCloses,awaiting,deferredRender})
  });
})(window, document);
