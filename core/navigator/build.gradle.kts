import deps.androidTestDependencies
import deps.androidX
import deps.debugTestDependencies
import deps.hilt
import deps.testDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}
apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.navigator"
}

dependencies {
    androidX()
    hilt()
    testDependencies()
    androidTestDependencies()
    debugTestDependencies()
}
