import deps.DependenciesVersions
import deps.androidX
import deps.domainModule
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.presentation"

    composeOptions {
        kotlinCompilerExtensionVersion = DependenciesVersions.KOTLIN_COMPILER
    }

    buildFeatures {
        compose = true
    }
}

dependencies {
    androidX()
    domainModule()
}
