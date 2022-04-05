plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("kotlin-parcelize")
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

    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")

    implementation(project(":domain"))
    implementation(project(":data"))
    implementation(project(":presentation"))

    //glide
    val glide_version = "4.12.0"
    implementation("com.github.bumptech.glide:glide:$glide_version")
    implementation("com.github.bumptech.glide:annotations:$glide_version")
    kapt("com.github.bumptech.glide:compiler:$glide_version")


    //by viewModels
    implementation("androidx.activity:activity-ktx:1.1.0")
    implementation("androidx.fragment:fragment-ktx:1.2.5")

    //coroutines
    val coroutine_version = "1.6.0"
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutine_version")


    // hilt
    val hilt_version = "2.40"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

}