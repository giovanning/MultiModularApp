pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MultiModularNani"
include(":app")
include(":features:login")
include(":features:home")
include(":core")
include(":core:data")
include(":core:domain")
include(":core:presentation")
include(":core:datastore")
include(":core:protodatastore")
include(":core:navigator")
include(":features:signup")
include(":core:analytics")
include(":core:extension")
include(":core:configuration")
include(":core:mediapicker")
include(":core:payments")
