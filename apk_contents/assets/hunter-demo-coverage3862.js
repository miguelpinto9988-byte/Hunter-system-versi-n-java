/* Hunter System 38.62 — acceso de respaldo a las 1200 animaciones SVG reales.
 * No genera ni sustituye movimientos: utiliza exclusivamente los recursos
 * demos30/<id>.svg ya empaquetados. Sin precargas masivas ni cambios en sesiones.
 */
(function (root, document) {
  'use strict';
  if (root.HunterDemoCoverage3862 || typeof technique !== 'function') return;

  const baseTechnique = technique;
  const $ = (parent, selector) => parent?.querySelector(selector) || null;
  const reduced = () => root.matchMedia?.('(prefers-reduced-motion: reduce)')?.matches === true;
  let active = null;
  const counters = {opened: 0, loaded: 0, failures: 0};

  function stop(session) {
    if (!session) return;
    session.playing = false;
    if (session.raf) root.cancelAnimationFrame(session.raf);
    session.raf = 0;
    if (session.svg?.pauseAnimations) session.svg.pauseAnimations();
    if (active === session) active = null;
  }

  function seek(session, next) {
    if (!session?.svg) return;
    session.time = Math.min(session.duration, Math.max(0, Number(next) || 0));
    session.svg.setCurrentTime?.(session.time === session.duration ? session.duration - 0.001 : session.time);
    if (session.range) session.range.value = String(Math.round(session.time / session.duration * 1000));
    if (session.position) session.position.textContent = Math.round(session.time / session.duration * 100) + ' %';
  }

  function tick(session, now) {
    if (!session.playing || active !== session || !session.host.isConnected || document.hidden) {
      stop(session);
      return;
    }
    if (session.last) {
      const delta = Math.min(0.12, Math.max(0, (now - session.last) / 1000));
      seek(session, (session.time + delta * session.rate) % session.duration);
    }
    session.last = now;
    session.raf = root.requestAnimationFrame(time => tick(session, time));
  }

  function play(session, button) {
    if (!session?.svg) return;
    if (session.playing) {
      stop(session);
      button.textContent = 'Reproducir';
      return;
    }
    if (active && active !== session) stop(active);
    active = session;
    session.playing = true;
    session.last = 0;
    button.textContent = 'Pausar';
    session.raf = root.requestAnimationFrame(now => tick(session, now));
  }

  function validPath(ex) {
    const id = ex?.id;
    if (!/^[A-Za-z0-9_-]{1,90}$/.test(id || '')) return null;
    const path = ex.demoPath30 || 'demos30/' + id + '.svg';
    return /^demos30\/[A-Za-z0-9_-]{1,90}\.svg$/.test(path) ? path : null;
  }

  async function load(session, ex) {
    if (session.started || !session.host.isConnected) return;
    session.started = true;
    const status = $(session.host, '.svg-status3862');
    status.textContent = 'Cargando animación local…';
    try {
      const path = validPath(ex);
      if (!path) throw Error('Ruta no válida');
      const response = await fetch(path);
      if (!response.ok) throw Error('No se encontró el SVG');
      const source = await response.text();
      const parser = new DOMParser().parseFromString(source, 'image/svg+xml');
      if (parser.querySelector('parsererror') || parser.documentElement.localName !== 'svg') {
        throw Error('El archivo SVG no tiene una estructura válida');
      }
      const svg = document.importNode(parser.documentElement, true);
      // Los recursos propios se tratan como datos, sin etiquetas ejecutables.
      svg.querySelectorAll('script,foreignObject').forEach(node => node.remove());
      [svg, ...svg.querySelectorAll('*')].forEach(node => {
        for (const attr of [...node.attributes]) {
          if (/^on/i.test(attr.name) || (/(?:href|src)$/i.test(attr.name) && /^(?:https?:|javascript:|data:)/i.test(attr.value))) {
            node.removeAttribute(attr.name);
          }
        }
      });
      svg.setAttribute('role', 'img');
      svg.setAttribute('aria-label', 'Demostración animada de ' + ex.name);
      const holder = $(session.host, '.svg-view3862');
      holder.replaceChildren(svg);
      svg.pauseAnimations?.();
      session.svg = svg;
      const durText = svg.querySelector('animate,animateTransform,animateMotion')?.getAttribute('dur') || '4s';
      const seconds = parseFloat(durText) || 4;
      session.duration = /ms$/.test(durText) ? seconds / 1000 : seconds;
      session.duration = Math.max(.1, Math.min(120, session.duration));
      session.range = $(session.host, '.svg-seek3862');
      session.position = $(session.host, '.svg-position3862');
      const btn = $(session.host, '.svg-play3862');
      btn.disabled = false;
      session.range.disabled = false;
      seek(session, 0);
      btn.addEventListener('click', () => play(session, btn));
      session.range.addEventListener('input', () => {
        const playing = session.playing;
        if (playing) { stop(session); btn.textContent = 'Reproducir'; }
        seek(session, Number(session.range.value) * session.duration / 1000);
      });
      $(session.host, '.svg-speed3862').addEventListener('change', event => {
        session.rate = Math.max(.5, Math.min(1.5, Number(event.target.value) || 1));
      });
      status.textContent = 'Demostración local lista · reproducción controlada';
      counters.loaded++;
      if (!reduced() && session.host.open && session.host.isConnected) play(session, btn);
    } catch (error) {
      counters.failures++;
      status.textContent = 'No se pudo mostrar esta animación. Conserva las instrucciones de la ficha.';
      session.host.dataset.error = String(error?.message || 'desconocido');
    }
  }

  function addOption(id) {
    const modal = document.querySelector('#modal');
    const ex = root.Engine?.byId?.(id);
    if (!modal?.open || !ex || !validPath(ex)) return;
    // Reutiliza la demostración original sin reemplazarla. En caso de ausencia
    // del visor primario, se abre automáticamente esta alternativa.
    const panel = $(modal, '.motion-card18');
    const fallback = document.createElement('details');
    fallback.className = 'svg-alt3862 card';
    fallback.dataset.exercise = id;
    fallback.innerHTML = '<summary>Ver animación SVG original · sin conexión</summary>' +
      '<div class="svg-view3862" aria-live="off"></div>' +
      '<p class="svg-status3862" role="status">El SVG se abrirá únicamente cuando lo solicites.</p>' +
      '<label>Posición de la animación <input class="svg-seek3862" type="range" min="0" max="1000" value="0" disabled></label>' +
      '<div class="svg-buttons3862"><button type="button" class="btn primary svg-play3862" disabled>Reproducir</button>' +
      '<label>Velocidad <select class="svg-speed3862"><option value="0.5">Lenta</option><option value="1" selected>Normal</option><option value="1.5">Rápida</option></select></label>' +
      '<output class="svg-position3862">0 %</output></div>';
    const session = {host: fallback, svg: null, duration: 4, time: 0, rate: 1, raf: 0, playing: false, last: 0, started: false};
    if (active) stop(active);
    if (panel) panel.insertAdjacentElement('afterend', fallback);
    else modal.append(fallback);
    fallback.addEventListener('toggle', () => {
      if (fallback.open) { counters.opened++; load(session, ex); }
      else { stop(session); const btn = $(fallback, '.svg-play3862'); if (btn) btn.textContent = 'Reproducir'; }
    });
    if (!panel || !$(panel, '#vectorCanvas')) fallback.open = true;
  }

  technique = function (id) {
    const result = baseTechnique.apply(this, arguments);
    addOption(id);
    return result;
  };
  document.querySelector('#modal')?.addEventListener('close', () => stop(active));
  document.addEventListener('visibilitychange', () => {if (document.hidden) stop(active);}, {passive: true});
  root.HunterDemoCoverage3862 = Object.freeze({version: '38.62', stats: () => ({...counters})});
})(window, document);
