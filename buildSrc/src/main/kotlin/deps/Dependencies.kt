package deps

object Dependencies {
    const val ANDROIDX_CORE = "androidx.core:core-ktx:${DependenciesVersions.CORE_KTX}"
    const val ANDROIDX_LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.LIFE_CYCLE_RUNTIME_KTX}"
    const val ANDROIDX_ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${DependenciesVersions.ACTIVITY_COMPOSE}"
    const val ANDROIDX_UI = "androidx.compose.ui:ui:${DependenciesVersions.COMPOSE_UI}"
    const val ANDROIDX_UI_GRAPHICS = "androidx.compose.ui:ui-graphics:${DependenciesVersions.COMPOSE_UI}"
    const val ANDROIDX_UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview:${DependenciesVersions.COMPOSE_UI}"
    const val ANDROIDX_MATERIAL3 = "androidx.compose.material3:material3:${DependenciesVersions.MATERIAL3}"
    const val ANDROIDX_WORK_RUNTIME = "androidx.work:work-runtime-ktx:${DependenciesVersions.WORK_RUNTIME}"
    const val ANDROIDX_COMPOSE_RUNTIME = "androidx.compose.runtime:runtime:${DependenciesVersions.COMPOSE_RUNTIME}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${DependenciesVersions.APPCOMPAT}"
    const val ANDROID_MATERIAL = "com.google.android.material:material:${DependenciesVersions.MATERIAL}"
    const val ANDROIDX_ACTIVITY = "androidx.activity:activity-ktx:${DependenciesVersions.ANDROIDX_ACTIVITY}"

    const val COMPOSE_MATERIAL =
        "androidx.compose.material:material:${DependenciesVersions.COMPOSE_MATERIAL}"
    const val COMPOSE_COMPILER =
        "androidx.compose.compiler:compiler:${DependenciesVersions.COMPOSE_COMPILER}"
    const val LIFECYCLE_COMPOSE_RUNTIME = "androidx.lifecycle:lifecycle-runtime-compose:${DependenciesVersions.LIFECYCLE_COMPOSE_RUNTIME}"

    const val HILT_ANDROID = "com.google.dagger:hilt-android:${DependenciesVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${DependenciesVersions.HILT}"
    const val HILT_AGP = "com.google.dagger:hilt-android-gradle-plugin:${DependenciesVersions.HILT}"
    const val HILT_COMPOSE = "androidx.hilt:hilt-work:${DependenciesVersions.HILT_COMPOSE}"
    const val HILT_COMPILER_KAT = "androidx.hilt:hilt-compiler:${DependenciesVersions.HILT_COMPOSE}"
    const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:${DependenciesVersions.HILT_COMPOSE}"

    const val RETROFIT = "com.squareup.retrofit2:retrofit:${DependenciesVersions.RETROFIT}"
    const val RETROFIT_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${DependenciesVersions.RETROFIT}"
    const val RETROFIT_COROUTINE_ADAPTER = "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${DependenciesVersions.RETROFIT_COROUTINE_ADAPTER}"

    const val OKHTTP = "com.squareup.okhttp3:okhttp:${DependenciesVersions.OKHTTP}"
    const val OKHTTP_LOGGING_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.OKHTTP}"

    const val ROOM_RUNTIME = "androidx.room:room-runtime:${DependenciesVersions.ROOM}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${DependenciesVersions.ROOM}"
    const val ROOM_KTX = "androidx.room:room-ktx:${DependenciesVersions.ROOM}"

    const val DATA_STORE = "androidx.datastore:datastore:${DependenciesVersions.DATA_STORE}"
    const val KOTLIN_COLLECTIONS = "org.jetbrains.kotlinx:kotlinx-collections-immutable:${DependenciesVersions.KOTLIN_COLLECTION}"
    const val KOTLIN_SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:${DependenciesVersions.KOTLIN_SERIALIZATION}"

    const val PROTO_BUF_JAVA_LITE = "com.google.protobuf:protobuf-javalite:${DependenciesVersions.PROTO_BUF_JAVA_LITE}"
    const val PROTO_BUF_KOTLIN_LITE = "com.google.protobuf:protobuf-kotlin-lite:${DependenciesVersions.PROTO_BUF_KOTLIN_LITE}"
    const val PROTOC_BUF_ARTIFACT = "com.google.protobuf:protoc:${DependenciesVersions.PROTO_BUF_KOTLIN_LITE}"
    const val CHUCKER_DEBUG = "com.github.chuckerteam.chucker:library:${DependenciesVersions.CHUCKER}"
    const val CHUCKER_RELEASE = "com.github.chuckerteam.chucker:library-no-op:${DependenciesVersions.CHUCKER}"
}