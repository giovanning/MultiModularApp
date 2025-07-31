import deps.androidTestDependencies
import deps.debugTestDependencies
import deps.testDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

android {
    namespace = "com.projetos.filmei.extension"
}

apply<SharedLibraryGradlePlugin>()

dependencies {
    testDependencies()
    androidTestDependencies()
    debugTestDependencies()
}