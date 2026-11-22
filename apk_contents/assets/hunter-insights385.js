/* Local, read-only training insights. No network, no new UI, no medical recommendations. */
(function (root) {
  'use strict';
  if (root.HunterInsights385) return;
  const dateIsValid = value => /^\d{4}-\d{2}-\d{2}$/.test(value);
  const number = x => (Number.isFinite(Number(x)) && Number(x) >= 0 ? Number(x) : 0);
  const engine = () => root.Engine;
  function groupOf(id) {
    const e = engine()?.byId?.(id);
    return e?.group && typeof e.group === 'string' ? e.group : 'Sin clasificar';
  }
  function history(logs, limit = 90) {
    const entries = Object.entries(logs && typeof logs === 'object' ? logs : {});
    return entries.filter(([day,log]) => dateIsValid(day) && log && Array.isArray(log.items))
      .sort((a,b) => b[0].localeCompare(a[0]))
      .slice(0, Math.max(1, Math.min(365, Math.floor(number(limit) || 90))));
  }
  function completedSets(logs, limit = 90) {
    const sets = [];
    for (const [date,log] of history(logs,limit)) {
      for (const item of log.items) {
        if (!item || !item.id || !Array.isArray(item.actual)) continue;
        for (const record of item.actual) {
          if (!record?.done) continue;
          sets.push({date,id:String(item.id),group:groupOf(item.id),value:number(record.value),kg:number(record.kg)});
        }
      }
    }
    return sets;
  }
  function groupVolume(logs, limit = 90) {
    const out = {};
    for (const set of completedSets(logs, limit)) {
      const group = set.group;
      out[group] ||= {series:0,repeticionesOSegundos:0,cargaKgRegistrada:0};
      out[group].series++;
      // Mixed unit totals are a raw count, NOT a training load calculation.
      out[group].repeticionesOSegundos += set.value;
      out[group].cargaKgRegistrada += set.kg;
    }
    return out;
  }
  function personalBest(logs, limit = 365) {
    const out = {};
    for (const set of completedSets(logs, limit)) {
      const best = out[set.id];
      if (!best) {out[set.id]={id:set.id,date:set.date,maxValue:set.value,maxKg:set.kg};continue;}
      if (set.value > best.maxValue) {best.maxValue=set.value;best.date=set.date;}
      if (set.kg > best.maxKg) best.maxKg=set.kg;
    }
    return out;
  }
  function repeatedMovements(days) {
    const histogram = {};
    for (const day of Array.isArray(days) ? days : []) {
      for (const item of day?.items || []) {
        if (!item?.id) continue;
        histogram[item.id] = (histogram[item.id] || 0)+1;
      }
    }
    return Object.entries(histogram).filter(([,count]) => count >= 3)
      .map(([id,count]) => ({id,count})).sort((a,b) => b.count-a.count || a.id.localeCompare(b.id));
  }
  function catalogCoverage() {
    const exercises = engine()?.exercises || [];
    let groups = 0, zones = 0, equipment = 0, annotated = 0;
    for (const e of exercises) {
      if (e?.group) groups++;
      if (Array.isArray(e?.zones) && e.zones.length) zones++;
      if (e?.equipment) equipment++;
      if (e?.id && e.group && e.equipment && Array.isArray(e.zones) && e.zones.length) annotated++;
    }
    return {catalogItems:exercises.length,groupTags:groups,zoneTags:zones,equipmentTags:equipment,completeForScreening:annotated,
      note:'La presencia de etiquetas no demuestra seguridad biomecánica ni equivalencia entre demostración y ejercicio.'};
  }
  root.HunterInsights385 = Object.freeze({version:'38.5',groupVolume,personalBest,repeatedMovements,catalogCoverage});
})(typeof window !== 'undefined' ? window : globalThis);
