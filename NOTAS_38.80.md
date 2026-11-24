# Hunter System 38.80 — equipamiento combinable

Se añadió un selector de casillas independientes en **creación y edición del perfil**:
- **Peso corporal**: ejercicios que no necesitan mancuernas.
- **Mancuernas**: ejercicios que usan el peso indicado en kilos.
- **Gimnasio**: ejercicios compatibles con las máquinas y el material que se marque.

Se pueden activar una, dos o las tres modalidades. El selector anterior de valor único `profile.equipment` se mantiene de forma interna por compatibilidad. La elección real se guarda en `profile.equipmentModes3880` como arreglo `['body','db','gym']` según las casillas marcadas.

## Ficheros que cambian

`apk_contents/assets/index.html`, `v23.js`, `hunter-bundle3874-08.js`, `hunter-bundle3874-10.js`, `hunter-bundle3874-11.js`, `hunter-bundle3874-12.js`, y los nuevos `hunter-equipment3880.js` y `hunter-equipment3880.css`.

La misión diaria usa la combinación seleccionada. Al modificar las opciones y guardar, el mecanismo de detección de cambios de la versión 38.79 vuelve a evaluar la misión actual. Permanecen aplicándose los controles de restricciones y los filtros del material disponible.

## Validaciones realizadas

- Comprobación sintáctica de JavaScript (`node --check`).
- Pruebas automáticas de casillas y persistencia del formulario: selección inicial, modalidades combinadas y validación de opciones.
- Prueba de generador con grupos simulados: selecciona ejercicios de dos o tres modalidades sin insertar modalidades no marcadas.
- Reconstrucción desde la APK 38.79: integridad ZIP comprobada, archivos originales no modificados idénticos, integridad y firma APK v2 verificadas.

**Aún no se ha probado la instalación ni el funcionamiento en un teléfono Android real.** La APK generada se firmó con un certificado nuevo. Guarda una copia de seguridad de tu perfil antes de cambiar la aplicación, porque Android puede exigir desinstalar la anterior.

Los recursos, vídeos y animaciones originales continúan en `apk_contents/` y en la APK. La APK no equivale al proyecto Java/Gradle original.
