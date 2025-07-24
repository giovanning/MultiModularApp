package deps

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import test.TestDependencies
import java.lang.Runtime.version

fun DependencyHandler.loginModule() {
    moduleImplementation(project(":features:login"))
}

fun DependencyHandler.homeModule() {
    moduleImplementation(project(":features:home"))
}

fun DependencyHandler.dataModule() {
    moduleImplementation(project(":core:data"))
}

fun DependencyHandler.domainModule() {
    moduleImplementation(project(":core:domain"))
}

fun DependencyHandler.presentationModule() {
    moduleImplementation(project(":core:presentation"))
}

fun DependencyHandler.dataStoreModule() {
    moduleImplementation(project(":core:datastore"))
}

fun DependencyHandler.protoDataStoreModule() {
    moduleImplementation(project(":core:protodatastore"))
}

fun DependencyHandler.androidX() {
    implementation(Dependencies.ANDROIDX_CORE)
    implementation(Dependencies.ANDROIDX_LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.ANDROIDX_ACTIVITY_COMPOSE)
    implementation(Dependencies.ANDROIDX_UI)
    implementation(Dependencies.ANDROIDX_UI_GRAPHICS)
    implementation(Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
    implementation(Dependencies.ANDROIDX_MATERIAL3)
    implementation(Dependencies.ANDROIDX_WORK_RUNTIME)
    implementation(Dependencies.ANDROIDX_COMPOSE_RUNTIME)
}

fun DependencyHandler.room() {
    implementation(Dependencies.ROOM_KTX)
    implementation(Dependencies.ROOM_RUNTIME)
    ksp(Dependencies.ROOM_COMPILER)
}

fun DependencyHandler.dataStore() {
    implementation(Dependencies.DATA_STORE)
    implementation(Dependencies.KOTLIN_COLLECTIONS)
    implementation(Dependencies.KOTLIN_SERIALIZATION)
}

fun DependencyHandler.protoDataStore() {
    implementation(Dependencies.DATA_STORE)
    implementation(Dependencies.PROTO_BUF_JAVA_LITE)
    implementation(Dependencies.PROTO_BUF_KOTLIN_LITE)
}

fun DependencyHandler.retrofit() {
    implementation(Dependencies.RETROFIT)
    implementation(Dependencies.RETROFIT_CONVERTER_GSON)
    implementation(Dependencies.RETROFIT_COROUTINE_ADAPTER)
}

fun DependencyHandler.okHttp() {
    implementation(Dependencies.OKHTTP)
    implementation(Dependencies.OKHTTP_LOGGING_INTERCEPTOR)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.HILT_ANDROID)
    implementation(Dependencies.HILT_NAVIGATION)
    implementation(Dependencies.HILT_COMPOSE)
    ksp(Dependencies.HILT_COMPILER_KAT)
    ksp(Dependencies.HILT_COMPILER)
    ksp(Dependencies.HILT_AGP)
}

fun DependencyHandler.testDependencies() {
    testImplementation(TestDependencies.ANDROIDX_JUNIT)
}

fun DependencyHandler.androidTestDependencies() {
    androidTestImplementation(TestDependencies.ANDROIDX_JUNIT)
    androidTestImplementation(TestDependencies.ANDROIDX_ESPRESSO_CORE)
    androidTestImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST)
}

fun DependencyHandler.debugTestDependencies() {
    debugImplementation(Dependencies.ANDROIDX_UI_TOOLING_PREVIEW)
    debugImplementation(TestDependencies.ANDROIDX_COMPOSE_UI_TEST_MANIFEST)
}

fun DependencyHandler.chucker() {
    debugImplementation(Dependencies.CHUCKER_DEBUG)
    releaseImplementation(Dependencies.CHUCKER_RELEASE)
}