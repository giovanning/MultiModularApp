package build

import extensions.buildConfigBooleanField
import extensions.buildConfigIntField
import extensions.buildConfigStringField
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.LibraryBuildType
import extensions.getLocalProperty
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

sealed class BuildCreator(val name: String) {

    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType

    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType


    class Debug(private val project: Project) : BuildCreator(BuildTypes.DEBUG) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(name) {
                isMinifyEnabled = Build.Debug.isMinifyEnabled
                enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage
                isDebuggable = Build.Debug.isDebuggable
                applicationIdSuffix = Build.Debug.applicationIdSuffix
                versionNameSuffix = Build.Debug.versionNameSuffix

                buildConfigStringField(
                    BuildVariables.BASE_URL,
                    project.getLocalProperty("dev.debug_endpoint")
                )
                buildConfigIntField(
                    BuildVariables.DATABASE_VERSION,
                    project.getLocalProperty("dev.db_version")
                )
                buildConfigBooleanField(
                    BuildVariables.CAN_CLEAR_CACHE,
                    project.getLocalProperty("dev.clear_cache")
                )
                buildConfigStringField(
                    BuildVariables.MAP_KEY,
                    project.getLocalProperty("dev.map_key")
                )
                buildConfigStringField(
                    BuildVariables.PIN_CERTIFICATE,
                    project.getLocalProperty("dev.project.certificate_pin")
                )
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType {
            return namedDomainObjectContainer.getByName(name) {
                isMinifyEnabled = Build.Debug.isMinifyEnabled
                enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage

                buildConfigStringField(
                    BuildVariables.BASE_URL,
                    project.getLocalProperty("dev.debug_endpoint")
                )
                buildConfigIntField(
                    BuildVariables.DATABASE_VERSION,
                    project.getLocalProperty("dev.db_version")
                )
                buildConfigBooleanField(
                    BuildVariables.CAN_CLEAR_CACHE,
                    project.getLocalProperty("dev.clear_cache")
                )
                buildConfigStringField(
                    BuildVariables.MAP_KEY,
                    project.getLocalProperty("dev.map_key")
                )
                buildConfigStringField(
                    BuildVariables.PIN_CERTIFICATE,
                    project.getLocalProperty("dev.project.certificate_pin")
                )
            }
        }
    }

    class Release(private val project: Project) : BuildCreator(BuildTypes.RELEASE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.getByName(name) {
                isMinifyEnabled = Build.Release.isMinifyEnabled
                enableUnitTestCoverage = Build.Release.enableUnitTestCoverage
                isDebuggable = Build.Release.isDebuggable

                buildConfigStringField(
                    BuildVariables.BASE_URL,
                    project.getLocalProperty("dev.prod_endpoint")
                )
                buildConfigIntField(
                    BuildVariables.DATABASE_VERSION,
                    project.getLocalProperty("dev.db_version")
                )
                buildConfigBooleanField(
                    BuildVariables.CAN_CLEAR_CACHE,
                    project.getLocalProperty("dev.clear_cache")
                )
                buildConfigStringField(
                    BuildVariables.MAP_KEY,
                    project.getLocalProperty("release.map_key")
                )
                buildConfigStringField(
                    BuildVariables.PIN_CERTIFICATE,
                    project.getLocalProperty("release.project.certificate_pin")
                )
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType {
            return namedDomainObjectContainer.getByName(name) {
                isMinifyEnabled = Build.Debug.isMinifyEnabled
                enableUnitTestCoverage = Build.Debug.enableUnitTestCoverage

                buildConfigStringField(
                    BuildVariables.BASE_URL,
                    project.getLocalProperty("dev.debug_endpoint")
                )
                buildConfigIntField(
                    BuildVariables.DATABASE_VERSION,
                    project.getLocalProperty("dev.db_version")
                )
                buildConfigBooleanField(
                    BuildVariables.CAN_CLEAR_CACHE,
                    project.getLocalProperty("dev.clear_cache")
                )
                buildConfigStringField(
                    BuildVariables.MAP_KEY,
                    project.getLocalProperty("dev.map_key")
                )
                buildConfigStringField(
                    BuildVariables.PIN_CERTIFICATE,
                    project.getLocalProperty("release.project.certificate_pin")
                )
            }
        }
    }

    class ReleaseExternalQA(private val project: Project) :
        BuildCreator(BuildTypes.RELEASE_EXTERNAL_QA) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationBuildType>): ApplicationBuildType {
            return namedDomainObjectContainer.create(name) {
                isMinifyEnabled = Build.ReleaseExternalQA.isMinifyEnabled
                enableUnitTestCoverage = Build.ReleaseExternalQA.enableUnitTestCoverage
                isDebuggable = Build.ReleaseExternalQA.isDebuggable
                applicationIdSuffix = Build.ReleaseExternalQA.applicationIdSuffix
                versionNameSuffix = Build.ReleaseExternalQA.versionNameSuffix

                buildConfigStringField(
                    BuildVariables.BASE_URL,
                    project.getLocalProperty("dev.qa_endpoint")
                )
                buildConfigIntField(
                    BuildVariables.DATABASE_VERSION,
                    project.getLocalProperty("dev.db_version")
                )
                buildConfigBooleanField(
                    BuildVariables.CAN_CLEAR_CACHE,
                    project.getLocalProperty("dev.clear_cache")
                )
                buildConfigStringField(
                    BuildVariables.MAP_KEY,
                    project.getLocalProperty("dev.map_key")
                )
                buildConfigStringField(
                    BuildVariables.PIN_CERTIFICATE,
                    project.getLocalProperty("dev.project.certificate_pin")
                )
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryBuildType>): LibraryBuildType {
            return namedDomainObjectContainer.create(name) {
                isMinifyEnabled = Build.ReleaseExternalQA.isMinifyEnabled
                enableUnitTestCoverage = Build.ReleaseExternalQA.enableUnitTestCoverage

                buildConfigStringField(
                    BuildVariables.BASE_URL,
                    project.getLocalProperty("dev.debug_endpoint")
                )
                buildConfigIntField(
                    BuildVariables.DATABASE_VERSION,
                    project.getLocalProperty("dev.db_version")
                )
                buildConfigBooleanField(
                    BuildVariables.CAN_CLEAR_CACHE,
                    project.getLocalProperty("dev.clear_cache")
                )
                buildConfigStringField(
                    BuildVariables.MAP_KEY,
                    project.getLocalProperty("dev.map_key")
                )
                buildConfigStringField(
                    BuildVariables.PIN_CERTIFICATE,
                    project.getLocalProperty("release.project.certificate_pin")
                )
            }
        }
    }

}