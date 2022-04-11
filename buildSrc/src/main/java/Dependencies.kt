object Dependencies {

    object Kotlin {
        const val SDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.21"
    }

    object AndroidX {
        const val MATERIAL = "androidx.compose.material:material:1.0.0-rc02"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.0-rc01"
        const val APP_COMPAT = "androidx.appcompat:appcompat:1.3.1"
    }

    object KTX {
        const val CORE = "androidx.core:core-ktx:1.7.0"
    }

    object Test {
        const val JUNIT = "junit:junit:4.13.2"
        const val ANDROID_JUNIT_RUNNER = "AndroidJUnitRunner"
    }

    object AndroidTest {
        const val TEST_RUNNER = "androidx.test:runner:1.4.0"
        const val JUNIT = "androidx.test.ext:junit:1.1.3"
        const val ESPRESSO_CORE = "androidx.test.espresso:espresso-core:3.4.0"
    }

    object Libraries {
        private object Versions {
            const val activity = "1.1.0"
            const val annotations = "1.1.0"
            const val appCompat = "1.4.1"
            const val cardView = "1.0.0"
            const val constraintLayout = "2.0.2"
            const val coroutine = "1.6.0"
            const val exoplayer = "2.17.1"
            const val fragment = "1.2.5"
            const val glide = "4.12.0"
            const val hilt = "2.40"
            const val ktx = "1.3.2"
            const val lifecycle = "2.4.1"
            const val lifecycleExtensions = "2.1.0"
            const val material = "1.5.0"
            const val navigation = "2.4.2"
            const val okHttpLoggingInterceptor = "4.9.0"
            const val recyclerView = "1.1.0"
            const val retrofit = "2.9.0"
        }

//            const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Kotlin.standardLibrary}"
//    const val kotlinCoroutines =
//        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Kotlin.coroutines}"
//    const val kotlinCoroutinesAndroid =
//        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Kotlin.coroutines}"

        const val androidAnnotations = "androidx.annotation:annotation:${Versions.annotations}"
        const val activity = "androidx.activity:activity-ktx:${Versions.activity}"
        const val fragment = "androidx.fragment:fragment-ktx:${Versions.fragment}"

        const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"

        const val coroutine =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutine}"

        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

        const val ktxCore = "androidx.core:core-ktx:${Versions.ktx}"
        const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycle}"
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
        const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
        const val lifecycleExtensions =
            "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycleExtensions}"
        const val cardView = "androidx.cardview:cardview:${Versions.cardView}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"

        const val exoplayer = "com.google.android.exoplayer:exoplayer:${Versions.exoplayer}"
        const val exoplayerCore = "com.google.android.exoplayer:exoplayer-core:${Versions.exoplayer}"
        const val exoplayerUI = "com.google.android.exoplayer:exoplayer-ui:${Versions.exoplayer}"

        const val material = "com.google.android.material:material:${Versions.material}"

        const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
        const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

        const val navigationFeatureModule = "androidx.navigation:navigation-dynamic-features-fragment:${Versions.navigation}"
        const val navigationTest = "androidx.navigation:navigation-testing:${Versions.navigation}"
        const val navigationJetpackCompose = "androidx.navigation:navigation-compose:${Versions.navigation}"

        const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigation}"

        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glideAnnotation = "com.github.bumptech.glide:annotations:${Versions.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.glide}"


        const val hilt = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:${Versions.hilt}"
        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"

        const val retrofit = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val okHttpLoggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.okHttpLoggingInterceptor}"
    }

}


