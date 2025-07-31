import deps.Dependencies
import deps.implementation
import deps.kotlinX
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.domain"
}

dependencies {
    implementation(Dependencies.ANDROIDX_COMPOSE_RUNTIME)
    kotlinX()
}
