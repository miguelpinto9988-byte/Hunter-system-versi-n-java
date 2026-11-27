plugins {
    id("com.android.application") version "8.7.3"
    id("org.jetbrains.kotlin.android") version "2.1.21"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
}
android {
    namespace = "com.huntersystem.nativo"
    compileSdk = 35
    defaultConfig {
        applicationId = "com.huntersystem.nativo"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "38.80-native-preview"
    }
    buildFeatures { compose = true }
}
dependencies {
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.compose.material3:material3")
}

// Las demostraciones son medios locales y se reproducen mediante Android MediaPlayer.
android.sourceSets.getByName("main").assets.srcDir("../apk_contents/assets/offline2d")
