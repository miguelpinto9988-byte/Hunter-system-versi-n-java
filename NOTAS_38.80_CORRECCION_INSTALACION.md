# Hunter System 38.80 — corrección de instalación

Se corrigió el empaquetado de la APK 38.80, sin cambiar los archivos web de ejercicios ni sus recursos.

- Se alinearon a 4 bytes los 399 archivos ZIP almacenados sin compresión, incluido `resources.arsc`.
- Se ajustó el manifiesto a `versionCode=134`, `versionName=38.80`.
- Se validaron la integridad ZIP y el digest y la firma APK v2.
- Se prepararon dos variantes:
  - **Normal**: identificador `com.huntersystem.p78`. No puede actualizar directamente instalaciones firmadas con otro certificado. Realizar copia de seguridad antes de desinstalar.
  - **Paralela**: identificador `com.huntersystem.p80`, con actividades nativas apuntando a `com.huntersystem.p78.*`. Permite conservar instalada la versión anterior, pero no importa automáticamente sus datos.
- Requiere Android 8.0 o superior (minSdk 26). No se realizó prueba de instalación en un dispositivo físico.

## SHA-256 de las APK corregidas

- Normal: `93f2be01b96f3fc952875796e356e878abb4a3a70efc360fe5edceb980f8d9d7`
- Paralela: `094db85c1ffbe6c2c9ef94ee448889a12a3843a339e8fb0a5ebae3968065a15d`

Los APK firmados se entregaron en la conversación. **Este archivo no sustituye a las APK ni implica que estén alojadas en GitHub.** Las claves privadas de firma no deben añadirse al repositorio.
