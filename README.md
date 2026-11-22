# Hunter System — versión 38.79

Proyecto de recuperación de la APK `HunterSystem_38.79_Mision_Dinamica_Actualizada.apk`.

**Importante: la importación íntegra de código y recursos aún no está publicada en este repositorio.** No confundir esta documentación con la aplicación completa.

## Contenido comprobado de la APK

- 4.258 archivos internos.
- 191 scripts JavaScript, 50 estilos CSS y una página HTML principal.
- 342 vídeos MP4 de ejercicios, 2.400 archivos SVG, 1.210 archivos JSON, 51 imágenes WebP, además de PNG, audio y recursos Android.
- La lógica web se conserva en `assets/`; la parte Android se distribuye como `classes.dex`, no como archivos `.java` originales.
- SHA-256 APK 38.79: `b722d7112e3e73ff74c608579efb8129fe3c7e26e60ba3ce68e44ddb513fc40e`.

## Incorporación del contenido completo

La extracción verificada está organizada con la estructura:

```text
apk_contents/
├── assets/             # Interfaz, rutinas, animaciones, datos y recursos
├── res/                # Recursos Android
├── AndroidManifest.xml # Formato binario compilado
├── resources.arsc
└── classes.dex         # Bytecode Android
```

Para añadirla, copiar la carpeta `apk_contents/` y el inventario `inventario_apk_sha256.csv` del paquete extraído a este repositorio; luego realizar commit y push.

Este es un respaldo recuperado de APK, **no un proyecto Java/Android Studio completo y recompilable a partir de fuentes originales**. El proyecto nativo original (Gradle, archivos Java, claves de firma) debe recuperarse de su repositorio original o equipo de desarrollo para garantizar reconstrucciones idénticas.

No publiques certificados, claves de firma ni credenciales.
