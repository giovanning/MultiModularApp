import deps.androidTestDependencies
import deps.debugTestDependencies
import deps.testDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.configuration"
}

dependencies {
    testDependencies()
    androidTestDependencies()
    debugTestDependencies()
}