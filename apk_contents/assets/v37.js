/* Solo el diseño aprobado se monta al terminar la inicialización. */
'use strict';
// Constant-time catalogue lookup; the exercise objects and demonstrations stay intact.
const ExerciseLookup372={rows:null,size:0,index:new Map(),refresh(){this.rows=E.exercises;this.size=this.rows.length;this.index=new Map(this.rows.map(e=>[e.id,e]));}};
ExerciseLookup372.refresh();E.byId=function(id){if(ExerciseLookup372.rows!==E.exercises||ExerciseLookup372.size!==E.exercises.length)ExerciseLookup372.refresh();return ExerciseLookup372.index.get(id);};
const Startup37={render,applyTheme};
Runtime20.startup37={renders:0,reveals:0,readyAt:null};
render=function(){if(Runtime20.initializing)return;const value=Startup37.render();Runtime20.startup37.renders++;return value;};
openDiagnostics20=function(){modal('<section><h2>Estado del contenido local</h2><p>'+E.exercises.length+' demostraciones 2D propias · '+Catalog.routines.length+' rutinas sin conexión.</p><p>Versión 37.3. '+Runtime20.errors.length+' errores de interfaz registrados en esta apertura.</p><p>La progresión semanal usa tus registros confirmados, esfuerzo, objetivos y restricciones declaradas.</p><p>Renderizado sincronizado con la pantalla, sin límite interno de FPS. La frecuencia efectiva depende del dispositivo.</p></section>');};
window.HunterV37={version:37,startup:Runtime20.startup37};
// Las migraciones se ejecutaron en memoria. Se guarda el resultado una sola vez.
// V38.48 commits once, after all catalogue, rules and UI modules are installed.
state.appVersion=37;
if(document.readyState==='loading')document.addEventListener('DOMContentLoaded',()=>Boot37.complete(),{once:true});else Boot37.complete();
