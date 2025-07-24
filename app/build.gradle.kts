import build.BuildConfig
import build.BuildCreator
import build.BuildDimensions
import deps.DependenciesVersions
import deps.androidTestDependencies
import deps.androidX
import deps.dataModule
import deps.dataStore
import deps.dataStoreModule
import deps.debugTestDependencies
import deps.domainModule
import deps.hilt
import deps.loginModule
import deps.okHttp
import deps.presentationModule
import deps.protoDataStoreModule
import deps.retrofit
import deps.room
import deps.testDependencies
import flavors.BuildFlavor
import release.ReleaseConfig
import signing.BuildSigning
import signing.SigningTypes
import test.TestBuildConfig

plugins {
    id(plugs.BuildPlugins.KOTLIN_ANDROID)
    id(plugs.BuildPlugins.ANDROID_APPLICATION)
    id(plugs.BuildPlugins.ANDROID)
    id(plugs.BuildPlugins.KTLINT)
    id(plugs.BuildPlugins.DETEKT)
    id(plugs.BuildPlugins.KOTLIN_COMPOSE)
    id(plugs.BuildPlugins.HILT) version deps.DependenciesVersions.HILT
    id(plugs.BuildPlugins.KSP)
}

android {
    namespace = BuildConfig.APP_ID
    compileSdk = BuildConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildConfig.APP_ID
        minSdk = BuildConfig.MIN_SDK_VERSION
        targetSdk = BuildConfig.TARGET_SDK_VERSION
        versionCode = ReleaseConfig.VERSION_CODE
        versionName = ReleaseConfig.VERSION_NAME

        testInstrumentationRunner = TestBuildConfig.TEST_INSTRUMENTATION_RUNNER
    }

    signingConfigs {
        BuildSigning.Release(project).create(this)
        BuildSigning.RealeaseExternalQA(project).create(this)
        BuildSigning.Debug(project).create(this)
    }

    buildTypes {
        BuildCreator.Release(project).create(this).apply {
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro",
            )
            signingConfig = signingConfigs.getByName(SigningTypes.RELEASE)
        }

        BuildCreator.Debug(project).create(this).apply {
            // signingConfig = signingConfigs.getByName(signing.SigningTypes.DEBUG)
        }

        BuildCreator.ReleaseExternalQA(project).create(this).apply {
            signingConfig = signingConfigs.getByName(SigningTypes.RELEASE_EXTERNAL_QA)
        }
    }
    flavorDimensions.add(BuildDimensions.APP)
    flavorDimensions.add(BuildDimensions.STORE)

    productFlavors {
        BuildFlavor.Google.create(this)
        BuildFlavor.Huawei.create(this)
        BuildFlavor.Driver.create(this)
        BuildFlavor.Client.create(this)
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
}

dependencies {
    dataModule()
    domainModule()
    presentationModule()
    loginModule()
    dataStoreModule()
    protoDataStoreModule()

    dataStore()
    androidX()
    hilt()
    room()
    okHttp()
    retrofit()

    testDependencies()
    androidTestDependencies()
    debugTestDependencies()
}
