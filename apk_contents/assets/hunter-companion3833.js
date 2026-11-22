/* Hunter System V38.33: acompana la sesion guiada en tablets sin cambiar
   los registros, las acciones ni la reproduccion. Reutiliza ShadowActor33. */
(function () {
  'use strict';
  const modal = document.getElementById('modal');
  const landscape = window.HunterV35?.SessionLandscape35;
  if (!modal || !landscape || typeof ShadowActor33 !== 'function') return;

  const phrases = {
    knight: ['Un paso a la vez. Sigue firme.', 'Tu constancia construye tu fuerza.', 'Respira y controla el movimiento.', 'La técnica importa más que la velocidad.'],
    warrior: ['Firme. Controla cada repetición.', 'Vas bien. Mantén tu técnica.', 'Una serie más, a tu ritmo.', 'Puedes descansar cuando lo necesites.'],
    bear: ['Respira, estamos avanzando.', 'Descansar también es entrenar.', 'Sigue a tu ritmo, con calma.', 'Tu esfuerzo cuenta.'],
    insect: ['¡Adelante! Una misión a la vez.', 'Tu próximo paso te espera.', 'Mantén la concentración.', 'Buen trabajo. Sigue con control.'],
    scientist: ['Cada paso suma a tu progreso.', 'Aprende el movimiento sin prisa.', 'Avanza con técnica y atención.', 'Una repetición controlada vale mucho.']
  };
  const names = {knight:'IGRIS', warrior:'IRON', bear:'TANK', insect:'BERU', scientist:'DOKJA KIM'};
  let active = null;
  let phraseTimer = 0;
  function stopPhraseTimer() { clearTimeout(phraseTimer); phraseTimer = 0; }
  function schedulePhrase() {
    stopPhraseTimer();
    if (!active || document.hidden || window.matchMedia('(prefers-reduced-motion: reduce)').matches ||
        document.documentElement.classList.contains('still') ||
        document.documentElement.classList.contains('hunter-hidden385')) return;
    phraseTimer = setTimeout(() => {
      phraseTimer = 0;
      if (!active || !active.node.isConnected) return;
      const list = phrases[active.type];
      active.phraseCycle = (active.phraseCycle || 0) + 1;
      active.phrase.textContent = list[(active.step + active.phraseCycle) % list.length];
      schedulePhrase();
    }, 28000);
  }

  function isTabletGuide() {
    return modal.open && modal.classList.contains('hunter-tablet-guide3830') &&
      modal.classList.contains('session-landscape35') &&
      window.matchMedia('(orientation: landscape) and (min-width: 820px) and (min-height: 520px)').matches;
  }

  function destroy() {
    stopPhraseTimer();
    if (!active) return;
    const old = active;
    active = null;
    old.actor?.destroy();
    old.node.remove();
  }

  function typeNow() {
    const type = typeof selectedCompanion === 'function' ? selectedCompanion() : 'knight';
    return Object.hasOwn(phrases, type) ? type : 'knight';
  }

  function stepNow() {
    return typeof guide11 !== 'undefined' && guide11 ? (Number(guide11.index) || 0) : 0;
  }

  function refreshPhrase() {
    if (!active) return;
    const step = stepNow();
    if (active.step === step) return;
    active.step = step;
    active.phraseCycle = 0;
    const list = phrases[active.type];
    active.phrase.textContent = list[((step % list.length) + list.length) % list.length];
  }

  function sync() {
    const controls = modal.querySelector('.wide-session35 .session-body35 > .session-controls35');
    if (!isTabletGuide() || !controls?.isConnected) { destroy(); return; }
    const type = typeNow();
    if (active && (active.parent !== controls || active.type !== type || !active.node.isConnected)) destroy();

    if (!active) {
      const card = document.createElement('section');
      card.className = 'hunter-guide-companion3833';
      card.setAttribute('aria-label', 'Compañero de entrenamiento');
      const figure = document.createElement('div');
      figure.className = 'hunter-guide-actor3833';
      figure.setAttribute('aria-hidden', 'true');
      const message = document.createElement('div');
      message.className = 'hunter-guide-message3833';
      const eyebrow = document.createElement('small');
      eyebrow.textContent = 'TU COMPAÑERO';
      const name = document.createElement('strong');
      name.textContent = names[type];
      const phrase = document.createElement('p');
      phrase.className = 'hunter-guide-phrase3833';
      phrase.setAttribute('role', 'note'); // Avoid a screen-reader announcement on every step.
      message.append(eyebrow, name, phrase);
      card.append(figure, message);
      controls.prepend(card);
      const actor = new ShadowActor33(figure, type);
      active = {parent:controls, node:card, actor, phrase, step:null, type};
      refreshPhrase();
      if (document.hidden || document.documentElement.classList.contains('still')) actor.pause();
      else void actor.open();
      schedulePhrase();
    } else {
      refreshPhrase();
    }
  }

  const previousSync = landscape.sync.bind(landscape);
  landscape.sync = function () {
    const result = previousSync();
    sync();
    return result;
  };

  modal.addEventListener('close', destroy);
  window.addEventListener('pagehide', destroy);
  document.addEventListener('visibilitychange', function () {
    if (!active) return;
    if (document.hidden) { active.actor.pause(); stopPhraseTimer(); }
    else { active.actor.resume(); schedulePhrase(); }
  });
  const motionSetting = window.matchMedia('(prefers-reduced-motion: reduce)');
  motionSetting.addEventListener?.('change', function () {
    if (!active) return;
    if (motionSetting.matches) { active.actor.pause(); stopPhraseTimer(); }
    else { active.actor.resume(); schedulePhrase(); }
  });
  // A programmatic return to a modal after page restoration can bypass repaint.
  window.addEventListener('pageshow', () => { if (modal.open) landscape.schedule(); });
  landscape.schedule();
  window.HunterGuideCompanion3833 = {sync, destroy, get mounted(){return !!active;}};
})();
