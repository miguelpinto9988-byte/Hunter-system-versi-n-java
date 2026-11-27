# Hunter para Android nativo

Proyecto Android con Kotlin/JVM y Jetpack Compose. La implementación actual incluye
el arranque y el archivo de 1.200 ejercicios con búsqueda, filtros, fichas y
reproducción nativa de las demostraciones MP4 disponibles por identificador.

Abre esta carpeta como proyecto en Android Studio con JDK 17 y Android SDK 35.
La tarea `assembleDebug` genera la APK de prueba. Gradle incorpora los vídeos
de `../apk_contents/assets/offline2d` desde este mismo repositorio.

La interfaz, los estilos, el movimiento de la órbita y la interacción se
implementan en archivos Kotlin. El manifiesto registra la actividad de Android.
El catálogo se distribuye como datos JSON y las imágenes, fuentes y vídeos como
recursos locales. El módulo se instala con el identificador
`com.huntersystem.nativo`.

El perfil, la planificación, las misiones, el calendario, el progreso, el
asistente y los recordatorios de la aplicación anterior siguen pendientes en
esta versión nativa.
