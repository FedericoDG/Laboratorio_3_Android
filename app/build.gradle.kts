import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
}

val envFile = rootProject.file(".env")
val env = Properties()
if (envFile.exists()) {
    env.load(envFile.inputStream())
}

android {
    namespace = "com.federicodg80.inmobiliaria"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.federicodg80.inmobiliaria"
        minSdk = 30
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        val apiBaseUrl = env.getProperty("API_BASE_URL") ?: "https://default.com"
        buildConfigField("String", "API_BASE_URL", "\"$apiBaseUrl\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)
    implementation(libs.legacy.support.v4)
    implementation(libs.android.sdk)
    implementation(libs.recyclerview)
    implementation(libs.glide)
    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    annotationProcessor(libs.glide.compiler)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
