/* Hunter System V38.8 — conservative companion particle motion.
 * Uses ONLY four existing SVG circles. No new overlays, canvases, dialogs,
 * continuous decorative shadows/auras, or exercise-demo changes.
 * Only approved roadmap entries: 11, 12, 13, 16, 18, 20 (partial).
 */
(function (root, doc) {
  'use strict';
  if (root.HunterParticles388) return;
  const Actor = root.IgrisCharacter;
  if (!Actor?.prototype?.sample) return;
  const previousSample = Actor.prototype.sample;
  const positionCache = new WeakMap();
  const types = {
    knight: '#ffa09b', warrior: '#95e7ff', bear: '#d5eaff',
    insect: '#91ffe8', scientist: '#f0e7ff'
  };
  const ranks = {E:'#84919e', D:'#668ab0', C:'#52a5bb', B:'#9a8ce0', A:'#d4b27b', S:'#c9e4ff'};
  const basePositions = [[86,280],[309,246],[75,350],[327,344]];
  let updates = 0, skipped = 0, actors = 0;
  const clamp = (v, min, max) => Math.max(min, Math.min(max, v));
  const num = (v) => Number(v.toFixed(2));
  const reducedMotion = () => !!root.matchMedia?.('(prefers-reduced-motion: reduce)').matches;
  const getBudget = () => root.HunterEvolution385?.budget || 'medio';
  const available = (actor) => !!actor && !actor.destroyed && !!actor.parts?.particles;
  const rgb = (h) => [1,3,5].map(i=>parseInt(h.slice(i,i+2),16));
  const mixColor = (a,b,t) => {
    const x=rgb(a),y=rgb(b);
    return '#' + x.map((v,i)=>Math.round(v*(1-t)+y[i]*t).toString(16).padStart(2,'0')).join('');
  };
  function update(actor, state, milliseconds) {
    const part = actor.parts.particles;
    let cache = positionCache.get(actor);
    if (!cache) {
      const children = [...(part.children || [])].filter(node => node.tagName?.toLowerCase() === 'circle').slice(0,4);
      if (!children.length) return;
      cache={children, last: -Infinity, lastColor:'', lastState:'', done:0};
      positionCache.set(actor,cache); actors++;
    }
    if (doc.hidden || reducedMotion() || actor.reduced || actor.paused || actor.state==='hidden') { skipped++; return; }
    const mode=getBudget();
    const minInterval = mode==='eco' ? 110 : mode==='alto' ? 34 : 55;
    // The displayed animation remains driven by elapsed time, not by number of callbacks.
    const t=Number.isFinite(Number(milliseconds))?Math.max(0,Number(milliseconds)):0;
    if (state===cache.lastState && t>=cache.last && t-cache.last<minInterval) {skipped++;return;}
    cache.last=t; cache.lastState=state;
    const rank=doc.documentElement?.dataset?.hunterRank385;
    const base=types[actor.type]||'#a5ecff';
    const color=ranks[rank]?mixColor(base,ranks[rank],.20):base;
    if(part.getAttribute('fill')!==color){part.setAttribute('fill', color);cache.lastColor=color;}
    const phase=t/1000;
    const gestures = ['celebrate','bow','wave','point','stretch'];
    const event = gestures.includes(state) || state==='entering' || state==='exiting';
    const eventLength = state==='entering' ? 980 : state==='exiting' ? 760 : 2200;
    const p=clamp(t/eventLength,0,1);
    const pulse=event?Math.sin(Math.PI*p):0;
    const intensity=mode==='eco'?.32:mode==='alto'?1:.72;
    // Avoid the character center: orbit in a narrow annular region, no full-screen movement.
    cache.children.forEach((circle,index) => {
      const origin=basePositions[index];
      const angle=phase*(.40+index*.07)+(index*Math.PI/2);
      const radius=(3+Math.sin(phase*.7+index)*2)*intensity;
      const attraction=(state==='think' ? .12 : .045)*intensity;
      const goalX=200,goalY=285;
      const dx=(Math.cos(angle)*radius + (goalX-origin[0])*attraction + Math.sin(phase*2+index)*pulse*8*intensity);
      const dy=(Math.sin(angle)*radius - Math.sin(phase*.9+index)*4*intensity + (goalY-origin[1])*attraction - pulse*8*intensity);
      const x=clamp(origin[0]+dx,55,340), y=clamp(origin[1]+dy,205,392);
      const f=(event && mode!=='eco') ? 1+pulse*.45 : 1;
      // Scale and move an existing circle: an unobtrusive short-lived trail on gestures.
      const scaleX = event && mode!=='eco' ? 1+.36*pulse : 1;
      const transform=`translate(${num(x)} ${num(y)}) scale(${num(scaleX)} ${num(f)}) translate(${-origin[0]} ${-origin[1]})`;
      if(circle.getAttribute('transform')!==transform)circle.setAttribute('transform',transform);
    });
    updates++;
  }
  Actor.prototype.sample=function(state,ms){
    const result=previousSample.apply(this,arguments);
    if(available(this)) update(this,state,ms);
    return result;
  };
  // No per-actor timers, no rAF, no new UI, and no additional DOM observers.
  root.HunterParticles388=Object.freeze({
    version:'38.8',
    snapshot:()=>({updatedFrames:updates,skippedFrames:skipped,trackedActors:actors,
      description:'Valores internos; no son mediciones de FPS, memoria ni batería.'})
  });
})(window,document);
