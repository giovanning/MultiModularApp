import deps.Dependencies.PROTOC_BUF_ARTIFACT
import deps.hilt
import deps.protoDataStore
import plugs.SharedLibraryGradlePlugin

plugins {
    id(plugs.BuildPlugins.ANDROID_LIBRARY)
    id(plugs.BuildPlugins.GOOGLE_PROTOBUF)
}

apply<SharedLibraryGradlePlugin>()

android {
    namespace = "com.projetos.filmei.protodatastore"

    protobuf {
        protoc {
            artifact = PROTOC_BUF_ARTIFACT
        }

        generateProtoTasks {
            all().forEach { tasks ->
                tasks.plugins {
                    create("kotlin").apply {
                        option("lite")
                    }
                }
                tasks.plugins {
                    create("java").apply {
                        option("lite")
                    }
                }
            }
        }
    }
}

dependencies {
    protoDataStore()
    hilt()
}
