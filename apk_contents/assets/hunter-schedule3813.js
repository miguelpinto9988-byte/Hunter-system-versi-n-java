/* Hunter System V38.13 — elegibilidad de equipamiento y dificultad.
 * Solo usa información explícita de cada ejercicio; no sustituye
 * restricciones clínicas, no dibuja interfaces y no modifica el historial.
 */
(function (root) {
  'use strict';
  if (root.HunterSchedule3813) return;
  const own = (obj, key) => Object.prototype.hasOwnProperty.call(obj || {}, key);
  const availableKinds = new Set(['body', 'db', 'gym']);
  function eligible(exercise, profile) {
    if (!exercise || !profile || typeof exercise.id !== 'string') return false;
    const gear = String(profile.equipment || 'body');
    const uses = String(exercise.equipment || '');
    if (!availableKinds.has(uses)) return false; // Equipo desconocido: revisar antes de asignar.
    if (uses === 'db' && gear !== 'db' && gear !== 'gym') return false;
    if ((uses === 'gym' || exercise.gymOnly23 === true) && gear !== 'gym') return false;
    const required = Array.isArray(exercise.requirements) ? exercise.requirements : [];
    if (required.includes('db') && gear !== 'db' && gear !== 'gym') return false;
    if (required.includes('gym') && gear !== 'gym') return false;
    const tiers = ['basic', 'intermediate', 'advanced'];
    const ix = tiers.indexOf(profile.difficulty);
    const maxLevel = ix >= 0 ? ix : profile.experience === 'regular' ? 1 : 0;
    if (!Number.isFinite(exercise.level) || exercise.level < 0 || (root.HunterGroups49?.level(exercise)??exercise.level) > maxLevel) return false;
    return true;
  }
  function weekSegment(dayIndex) {
    return Number.isSafeInteger(dayIndex) && dayIndex >= 0 ? Math.floor(dayIndex / 7) : null;
  }
  function report(cycle) {
    const days = Array.isArray(cycle?.days) ? cycle.days : [];
    const weeks = [{days:0, active:0, assignments:0, families:new Set()},
      {days:0, active:0, assignments:0, families:new Set()},
      {days:0, active:0, assignments:0, families:new Set()}];
    for (const [i,day] of days.entries()) {
      const w = weekSegment(i);
      if (w === null || w > 2) continue;
      const item = weeks[w]; item.days++;
      if (day?.scheduleChoice && day.scheduleChoice !== 'rest') item.active++;
      for (const x of Array.isArray(day?.items) ? day.items : []) {
        if (x?.id) { item.assignments++; item.families.add(x.id); }
      }
    }
    return {weeks:weeks.map(({families,...w})=>({...w,distinct:families.size})),
      note:'Inventario interno: no valida posturas ni recomendaciones médicas.'};
  }
  root.HunterSchedule3813 = Object.freeze({version:'38.13', eligible, weekSegment, report});
})(typeof window !== 'undefined' ? window : globalThis);
