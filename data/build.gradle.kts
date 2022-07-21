plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = 31

    defaultConfig {
        minSdk = 21
        targetSdk = 31
//        versionCode = 1
//        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(Dependencies.KTX.CORE)
    implementation(Dependencies.Libraries.appCompat)
    implementation(Dependencies.Libraries.material)
    testImplementation(Dependencies.Test.JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.JUNIT)
    androidTestImplementation(Dependencies.AndroidTest.ESPRESSO_CORE)

    implementation(project(":domain"))

    // hilt
    implementation(Dependencies.Libraries.hilt)
    kapt(Dependencies.Libraries.hiltCompiler)

    // hilt test
    androidTestImplementation(Dependencies.Test.HILT)
    kaptAndroidTest(Dependencies.Libraries.hiltCompiler)

    // Retrofit2
    implementation(Dependencies.Libraries.retrofit)

    //okhttp3 logging interceptor
    implementation(Dependencies.Libraries.okHttpLoggingInterceptor)

    // coroutines
    implementation(Dependencies.Libraries.coroutine)


}