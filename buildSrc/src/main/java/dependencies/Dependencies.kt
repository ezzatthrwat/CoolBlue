package dependencies

import Versions

object Dependencies {

    object Android {
        const val gradlePlugin = "com.android.tools.build:gradle:${Versions.Build.gradlePlugin}"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.Kotlin.kotlin}"
        const val gradlePlugin =
            "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.Kotlin.kotlin}"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:${Versions.AndroidX.appcompat}"
        const val constraintlayout =
            "androidx.constraintlayout:constraintlayout:${Versions.AndroidX.constraintlayout}"
        const val activity = "androidx.activity:activity:${Versions.AndroidX.activity}"
        const val ktx = "androidx.core:core-ktx:${Versions.AndroidX.ktx}"
        const val recyclerview =
            "androidx.recyclerview:recyclerview:${Versions.AndroidX.recyclerview}"
    }

    object Jetpack {
        const val lifecycleViewModel =
            "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Jetpack.lifecycle}"
        const val lifecycleLivedata =
            "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Jetpack.lifecycle}"
        const val lifecycleRuntime =
            "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Jetpack.lifecycle}"
        const val lifecycleViewModelSavedState =
            "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.Jetpack.lifecycle}"
        const val lifecycleLifecycleCompiler =
            "androidx.lifecycle:lifecycle-compiler:${Versions.Jetpack.lifecycle}"
        const val lifecycleCommonJava8 =
            "androidx.lifecycle:lifecycle-common-java8:${Versions.Jetpack.lifecycle}"
        const val lifecycleService =
            "androidx.lifecycle:lifecycle-service:${Versions.Jetpack.lifecycle}"
        const val lifecycleProcess =
            "androidx.lifecycle:lifecycle-process:${Versions.Jetpack.lifecycle}"
        const val lifecycleReactiveStreams =
            "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.Jetpack.lifecycle}"
    }

    object Google {
        const val material = "com.google.android.material:material:${Versions.Google.material}"
    }

    object Networking {
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.Libraries.retrofit}"
        const val moshiConverter =
            "com.squareup.retrofit2:converter-moshi:${Versions.Libraries.retrofit}"
        const val moshi =
            "com.squareup.moshi:moshi:${Versions.Libraries.moshi}"
        const val moshiAdapters =
            "com.squareup.moshi:moshi-adapters:${Versions.Libraries.moshi}"
        const val moshiCodegen =
            "com.squareup.moshi:moshi-kotlin-codegen:${Versions.Libraries.moshi}"
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.Libraries.okhttp}"
        const val loggingInterceptor =
            "com.squareup.okhttp3:logging-interceptor:${Versions.Libraries.okhttp}"
    }

    object DI {
        const val dagger = "com.google.dagger:dagger:${Versions.Libraries.dagger}"
        const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.Libraries.dagger}"
    }

    object Util {
        const val timber = "com.jakewharton.timber:timber:${Versions.Libraries.timber}"
    }

    object Image {
        const val glide = "com.github.bumptech.glide:glide:${Versions.Libraries.glide}"
        const val glideCompiler = "com.github.bumptech.glide:compiler:${Versions.Libraries.glide}"
    }

    object Test {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val core = "androidx.test:core:${Versions.Test.core}"
        const val arch = "androidx.arch.core:core-testing:${Versions.Jetpack.test}"
        const val mockitoKotlin =
            "org.mockito.kotlin:mockito-kotlin:${Versions.Test.mockitokotlin}"
        const val mockitoInline = "org.mockito:mockito-inline:${Versions.AndroidTest.mockito}"
        const val robolectric = "org.robolectric:robolectric:${Versions.Test.robolectric}"
        const val json = "org.json:json:${Versions.Test.json}"
    }

    object AndroidTest {
        const val runner = "androidx.test:runner:${Versions.AndroidTest.runner}"
        const val espresso = "androidx.test.espresso:espresso-core:${Versions.AndroidTest.espresso}"
        const val espressoContrib =
            "androidx.test.espresso:espresso-contrib:${Versions.AndroidTest.espresso}"
        const val intents =
            "androidx.test.espresso:espresso-intents:${Versions.AndroidTest.espresso}"
        const val okHttpIdlingResource =
            "com.jakewharton.espresso:okhttp3-idling-resource:${Versions.AndroidTest.okHttpIdlingResource}"
        const val rules = "androidx.test:rules:${Versions.AndroidTest.rules}"
        const val core = "androidx.test:core-ktx:${Versions.AndroidTest.core}"
        const val truth = "androidx.test.ext:truth:${Versions.AndroidTest.truth}"
        const val junit = "androidx.test.ext:junit-ktx:${Versions.AndroidTest.junit}"
        const val emptyGuava =
            "com.google.guava:listenablefuture:9999.0-empty-to-avoid-conflict-with-guava"
        const val orchestrator = "androidx.test:orchestrator:${Versions.AndroidTest.orchestrator}"
        const val restMock =
            "com.github.andrzejchm.RESTMock:android:${Versions.AndroidTest.restMock}"
        const val dexmaker =
            "com.linkedin.dexmaker:dexmaker-mockito-inline:${Versions.AndroidTest.dexmaker}"
    }
}