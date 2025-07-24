import deps.androidTestDependencies
import deps.androidX
import deps.dataModule
import deps.debugTestDependencies
import deps.hilt
import deps.retrofit
import deps.room
import deps.testDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.login"
}

dependencies {
    androidX()
    hilt()
    retrofit()
    dataModule()
    room()
    testDependencies()
    androidTestDependencies()
    debugTestDependencies()
}
