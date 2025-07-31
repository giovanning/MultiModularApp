package plugs

import build.BuildConfig
import build.BuildCreator
import build.BuildDimensions
import com.android.build.api.dsl.LibraryExtension
import deps.DependenciesVersions
import flavors.BuildFlavor
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import signing.BuildSigning
import signing.SigningTypes
import test.TestBuildConfig

class SharedLibraryGradlePlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.addPluginConfigurations()
        project.addAndroidConfigurations()
        project.applyKotlinOptions()
    }

    private fun Project.addPluginConfigurations() {
        plugins.apply(BuildPlugins.KOTLIN_ANDROID)
        plugins.apply(BuildPlugins.KSP)
        plugins.apply(BuildPlugins.KOTLIN_COMPOSE)
        plugins.apply(BuildPlugins.KTLINT)
        plugins.apply(BuildPlugins.DETEKT)
        plugins.apply(BuildPlugins.KOTLIN_SERIALIZATION)
    }

    private fun Project.addAndroidConfigurations() {
        extensions.getByType(LibraryExtension::class.java).apply {
            compileSdk = BuildConfig.COMPILE_SDK_VERSION
            defaultConfig {
                minSdk = BuildConfig.MIN_SDK_VERSION
                testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
            }

            signingConfigs {
                BuildSigning.Release(project).create(this)
                BuildSigning.RealeaseExternalQA(project).create(this)
                BuildSigning.Debug(project).create(this)
            }

            buildTypes {
                BuildCreator.Release(project).createLibrary(this).apply {
                    proguardFiles(
                        getDefaultProguardFile("proguard-android-optimize.txt"),
                        "proguard-rules.pro"
                    )
                    signingConfig = signingConfigs.getByName(SigningTypes.RELEASE)
                }

                BuildCreator.Debug(project).createLibrary(this).apply {
                    //signingConfig = signingConfigs.getByName(signing.SigningTypes.DEBUG)
                }

                BuildCreator.ReleaseExternalQA(project).createLibrary(this).apply {
                    signingConfig = signingConfigs.getByName(SigningTypes.RELEASE_EXTERNAL_QA)
                }
            }

            flavorDimensions.add(BuildDimensions.APP)
            flavorDimensions.add(BuildDimensions.STORE)

            productFlavors {
                BuildFlavor.Google.createLibrary(this)
                BuildFlavor.Huawei.createLibrary(this)
                BuildFlavor.Driver.createLibrary(this)
                BuildFlavor.Client.createLibrary(this)
            }

            composeOptions {
                kotlinCompilerExtensionVersion = DependenciesVersions.KOTLIN_COMPILER
            }

            buildFeatures {
                compose = true
                buildConfig = true
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }
        }
    }

    private fun Project.applyKotlinOptions() {
        tasks.withType<KotlinCompile>().configureEach {
            compilerOptions {
                jvmTarget.set(JvmTarget.JVM_11)
            }
        }
    }
}