import deps.DependenciesVersions
import deps.androidTestDependencies
import deps.androidX
import deps.dataModule
import deps.debugTestDependencies
import deps.domainModule
import deps.hilt
import deps.navigatorModule
import deps.presentationModule
import deps.retrofit
import deps.room
import deps.testDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.HILT) version deps.DependenciesVersions.HILT
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.login"

    composeOptions {
        kotlinCompilerExtensionVersion = DependenciesVersions.KOTLIN_COMPILER
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    androidX()
    hilt()
    retrofit()
    dataModule()
    domainModule()
    navigatorModule()
    presentationModule()
    room()
    testDependencies()
    androidTestDependencies()
    debugTestDependencies()
}
