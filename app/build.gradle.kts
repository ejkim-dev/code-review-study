plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-parcelize")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.example.baseandroidapp"
        minSdk = 21
        targetSdk = 31
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        // View binding allows efficient, type-safe view access
        // See more at https://developer.android.com/topic/libraries/view-binding
        viewBinding = true
    }
    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(Dependencies.KTX.CORE)
    implementation(Dependencies.Libraries.appCompat)
    implementation(Dependencies.Libraries.material)
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.3")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.ESPRESSO_CORE)

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation"))

    // glide
    implementation(Dependencies.Libraries.glide)
    implementation(Dependencies.Libraries.glideAnnotation)
    kapt(Dependencies.Libraries.glideCompiler)

    // by viewModels
    implementation(Dependencies.Libraries.activity)
    implementation(Dependencies.Libraries.fragment)

    // coroutines
    implementation(Dependencies.Libraries.coroutine)

    // hilt
    implementation(Dependencies.Libraries.hilt)
    kapt(Dependencies.Libraries.hiltCompiler)

    // exoplayer
    implementation(Dependencies.Libraries.exoplayer)
    implementation(Dependencies.Libraries.exoplayerCore)
    implementation(Dependencies.Libraries.exoplayerUI)

    // navigation
    implementation(Dependencies.Libraries.navigationFragment)
    implementation(Dependencies.Libraries.navigationUi)
    implementation(Dependencies.Libraries.navigationFeatureModule)
    androidTestImplementation(Dependencies.Libraries.navigationTest)
    implementation(Dependencies.Libraries.navigationJetpackCompose)

}