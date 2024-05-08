import org.jetbrains.kotlin.config.JvmTarget

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("appyx-lint")
    id("appyx-detekt")
}

android {
    namespace = "com.bumble.appyx.sample.navigation.compose"
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
        targetSdk = libs.versions.androidTargetSdk.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = JvmTarget.JVM_11.toString()
    }
}

dependencies {
    val composeBom = platform(libs.compose.bom)

    api(composeBom)
    api(project(":libraries:core"))
    api(libs.compose.ui.ui)

    implementation(composeBom)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.compose.material)
    debugImplementation(project(":libraries:testing-ui-activity"))

    androidTestImplementation(composeBom)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.junit)
    androidTestImplementation(libs.compose.ui.test.junit4)
}
