/* Hunter System 38.10 - planificación equilibrada entre sesiones del ciclo.
 * Usa únicamente ejercicio ya admitidos por el filtro de V38.9.
 * No diagnostica lesiones, no modifica sesiones comenzadas ni crea ventanas.
 */
(function (root) {
  'use strict';
  if (root.HunterPlan3810) return;
  const training = root.HunterTraining387;
  const engine = root.Engine;
  const validDay = s => /^\d{4}-\d{2}-\d{2}$/.test(String(s || ''));
  const maxSafe = (value, hi) => {
    const n = Number(value);
    return Number.isFinite(n) ? Math.max(0, Math.min(hi, n)) : 0;
  };
  const family = e => String(e?.baseId || e?.family || e?.id || '');
  const pattern = e => training?.pattern?.(e) || null;
  const dayDiff = (a, b) => {
    if (!validDay(a) || !validDay(b)) return Infinity;
    return Math.abs(Math.round((Date.parse(a + 'T12:00:00Z') - Date.parse(b + 'T12:00:00Z')) / 86400000));
  };
  function tracker(cycle, logs = {}, today = '') {
    const planned = [];
    const known = Array.isArray(cycle?.days) ? cycle.days : [];
    for (const day of known) {
      if (!validDay(day?.date) || !Array.isArray(day.items) || !day.items.length || day.date < today) continue;
      const recorded = logs?.[day.date];
      // Sólo incluir sesiones cuya programación no será sustituida por el motor dinámico.
      const locked = !!(day.customized || day.routineId || day.creatorAssigned || day.manual ||
        recorded?.complete || recorded?.manual || recorded?.pain || recorded?.items?.some(x => x.actual?.some(s => s.done)) ||
        day.tasks?.some(t => t.done));
      if (!locked) continue;
      for (const item of day.items) {
        const ex = engine?.byId?.(item.id);
        if (ex) planned.push({date: day.date, group: ex.group || '', id: family(ex),
          pattern: pattern(ex), sets: maxSafe(item.sets, 6)});
      }
    }
    return {
      score(exercise, date) {
        let group = 0, movement = 0, repeats = 0;
        const p = pattern(exercise), f = family(exercise);
        for (const row of planned) {
          if (dayDiff(row.date, date) > 6) continue;
          if (exercise?.group && row.group === exercise.group) group += row.sets;
          if (p && row.pattern === p) movement += row.sets;
          if (f && row.id === f) repeats++;
        }
        return -(Math.min(24, group * 1.25) + Math.min(16, movement * 2) + Math.min(24, repeats * 12)) || 0;
      },
      add(date, exercise, sets) {
        if (!validDay(date) || !exercise?.id) return;
        planned.push({date, group: exercise.group || '', pattern: pattern(exercise),
          id: family(exercise), sets: maxSafe(sets, 6)});
      },
      snapshot() {return planned.map(x => ({...x}));}
    };
  }
  function stats(catalog) {
    const items = Array.isArray(catalog) ? catalog : engine?.exercises || [];
    const groups = {}, categories = {}, patterns = {};
    let annotated = 0, withModel = 0;
    for (const e of items) {
      if (!e?.id) continue;
      if (e.group) groups[e.group] = (groups[e.group] || 0) + 1;
      if (e.category) categories[e.category] = (categories[e.category] || 0) + 1;
      const pat = pattern(e);
      if (pat) patterns[pat] = (patterns[pat] || 0) + 1;
      if (e.group && e.equipment && Array.isArray(e.zones) && e.zones.length) annotated++;
      if (e.demoPath30 && e.modelPath30) withModel++;
    }
    return {total: items.length, annotated, withModel, groups, categories, patterns,
      notice: 'Metadatos y variedad comprobables en catálogo, no validación de posturas ni seguridad clínica.'};
  }
  root.HunterPlan3810 = Object.freeze({version: '38.10', tracker, stats, dayDiff});
})(typeof window !== 'undefined' ? window : globalThis);
