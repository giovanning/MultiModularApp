plugins {
    `kotlin-dsl`
}

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

dependencies {
    api(kotlin("gradle-plugin:2.0.0"))
    implementation("com.android.tools.build:gradle:8.7.0")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.8")
    implementation("com.squareup:javapoet:1.13.0")
    implementation("org.jetbrains.kotlin:kotlin-serialization:1.9.0")
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.9.4")
}