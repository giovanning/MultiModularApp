import deps.androidTestDependencies
import deps.chucker
import deps.dataStoreModule
import deps.debugTestDependencies
import deps.hilt
import deps.okHttp
import deps.protoDataStoreModule
import deps.retrofit
import deps.testDependencies
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.data"
}

dependencies {
    okHttp()
    retrofit()
    dataStoreModule()
    protoDataStoreModule()
    chucker()
    hilt()
    testDependencies()
    androidTestDependencies()
    debugTestDependencies()
}
