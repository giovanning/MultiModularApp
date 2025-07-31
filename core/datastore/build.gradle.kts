import deps.Dependencies
import deps.dataStore
import deps.implementation
import deps.kotlinX
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.datastore"
}

dependencies {
    dataStore()
    kotlinX()
    implementation(Dependencies.ANDROIDX_COMPOSE_RUNTIME)
}
