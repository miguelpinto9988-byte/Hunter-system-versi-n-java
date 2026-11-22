# Hunter System — versión 38.79 (contenido recuperado de APK)

Este repositorio de trabajo contiene **los archivos recuperables de la APK** `HunterSystem_38.79_Mision_Dinamica_Actualizada.apk`.

## Estado de los archivos

- APK analizada: **4,258 archivos**.
- Archivos extraídos en `apk_contents/`: **4,255**.
- Archivos de fuentes tipográficas que se mantienen únicamente en la APK original: **3**. Los nombres y hashes se encuentran en `inventario_apk_sha256.csv`.
- SHA-256 del APK original: `b722d7112e3e73ff74c608579efb8129fe3c7e26e60ba3ce68e44ddb513fc40e`.

## Estructura

- `apk_contents/assets/`: HTML, JavaScript, CSS, datos, ilustraciones SVG, vídeos MP4, recursos WebP y audio; incluye la interfaz, animaciones y motor de rutinas accesibles.
- `apk_contents/classes.dex`: bytecode Android compilado. **No es el código Java fuente original**.
- `apk_contents/AndroidManifest.xml` y `apk_contents/resources.arsc`: metadatos y recursos Android en formato compilado.
- `apk_contents/res/`: recursos nativos.
- `inventario_apk_sha256.csv`: inventario de cada uno de los archivos internos del APK, con tamaño y checksum SHA-256, incluidos los que no se distribuyen separadamente.
- `tools/verificar_contenido.py`: verifica integridad de los archivos presentes contra ese inventario.

## Importante: código fuente vs. APK

La APK **no incluye necesariamente** fuentes Java originales, proyecto Android Studio, archivo `build.gradle`, `settings.gradle`, certificados de firma ni historia Git. `classes.dex` permite inspección y eventual ingeniería inversa, pero el fuente exacto no se puede recuperar idéntico. Los ficheros web de `assets/` sí son la versión de código que la APK ejecuta.

**Esta copia por sí sola no es un proyecto Android Studio listo para recompilar.** No cambiar ni inventar lógica Java del APK a partir de aproximaciones sin cotejo con su ejecución.

## Verificación

Desde la raíz, usar:

```bash
python3 tools/verificar_contenido.py
```

Para regenerar la carpeta completa a partir de la APK original —incluyendo sus fuentes embebidas—:

```bash
python3 tools/extraer_apk.py ruta/a/HunterSystem_38.79_Mision_Dinamica_Actualizada.apk --destino apk_contents_completo
```

## Publicación en GitHub

Para cargar todos los archivos de esta copia **sin omitir animaciones**, descomprimir este paquete en el clon del repositorio y ejecutar:

```bash
git add -A
git commit -m "Importar Hunter System 38.79 desde APK, con animaciones y recursos"
git push origin main
```

No almacenar claves privadas, contraseñas ni certificados de firma en un repositorio público.
