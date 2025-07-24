import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.presentation"
}

dependencies {
}
