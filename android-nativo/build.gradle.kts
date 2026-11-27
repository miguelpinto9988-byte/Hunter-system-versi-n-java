plugins {
    id("com.android.application") version "8.7.3"
    id("org.jetbrains.kotlin.android") version "2.1.21"
    id("org.jetbrains.kotlin.plugin.compose") version "2.1.21"
}
android {
    namespace = "com.huntersystem.nativo"
    compileSdk = 35
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    defaultConfig {
        applicationId = "com.huntersystem.nativo"
        minSdk = 26
        targetSdk = 34
        versionCode = 2
        versionName = "38.80-native-preview.2"
    }
    testOptions.unitTests.isIncludeAndroidResources = true
    buildFeatures { compose = true }
}
dependencies {
    testImplementation("org.robolectric:robolectric:4.14.1")
    testImplementation("junit:junit:4.13.2")
    testImplementation(platform("androidx.compose:compose-bom:2024.12.01"))
    testImplementation("androidx.compose.ui:ui-test-junit4")
    implementation("androidx.activity:activity-compose:1.9.3")
    implementation(platform("androidx.compose:compose-bom:2024.12.01"))
    implementation("androidx.compose.material3:material3")
}

// Las demostraciones son medios locales y se reproducen mediante Android MediaPlayer.
android.sourceSets.getByName("main").assets.srcDir("../apk_contents/assets/offline2d")
kotlin.compilerOptions.jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
