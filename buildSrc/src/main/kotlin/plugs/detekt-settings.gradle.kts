import io.gitlab.arturbosch.detekt.Detekt
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension

val DETEKT_VERSION = "1.23.8"

apply<DetektPlugin>()

configure<DetektExtension> {
    toolVersion = DETEKT_VERSION
    source.from("src/main/java", "src/main/kotlin")
    parallel = false
    config.from("${rootProject.projectDir}/detekt/detekt-config.yml")
    buildUponDefaultConfig = false
    allRules = false
    baseline = file("${rootProject.projectDir}/detekt/${project.name}/detekt-baseline.xml")
    disableDefaultRuleSets = false
    debug = true
    ignoreFailures = false
    ignoredBuildTypes = listOf("release")
    ignoredFlavors = listOf("huawei")
    ignoredVariants = listOf("googleRelease")
    basePath = projectDir.absolutePath

}

tasks.withType<Detekt> {
    include("**/*.kt", "**/*.kts")
    exclude(
        "**/build/**",
        ".*/resources/.*",
        ".*test.*",
        ".*/tmp/.*",
        "**/generated/**"
    )
    reports {
        xml {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/detekt-report.xml"))
        }
        html {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/detekt-report.html"))
        }
        sarif {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/detekt-report.sarif"))
        }
        md {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/detekt-report.md"))
        }
        txt {
            required.set(true)
            outputLocation.set(file("${rootProject.projectDir}/detekt/${project.name}/detekt-report.txt"))
        }
    }
    jvmTarget = JavaVersion.VERSION_1_8.toString()
    dependencies {
        "detektPlugins"("io.gitlab.arturbosch.detekt:detekt-formatting:${DETEKT_VERSION}")
    }
}

tasks.registering(io.gitlab.arturbosch.detekt.report.ReportMergeTask::class) {
    group = "reporting"
    description = "Merges all detekt reports into one report file."
    dependsOn("detekt") // Add this line to declare the dependency
}

/*
In Gradle with Detekt, the detektBaseline task is used to create or update a baseline file.
 The baseline file is a record of existing issues in the codebase that you want to exclude
  from being reported as new issues in future Detekt runs.
   This is particularly useful when you are introducing Detekt to an existing codebase,
 as it allows you to focus on new issues rather than addressing existing ones immediately
 */
tasks.named("detekt") {
    dependsOn("detektBaseline")
    dependsOn(":features:login:detektBaseline")
    dependsOn(":features:signup:detektBaseline")
    dependsOn(":features:home:detektBaseline")
    dependsOn(":core:data:detektBaseline")
    dependsOn(":core:domain:detektBaseline")
    dependsOn(":core:presentation:detektBaseline")
    dependsOn(":core:datastore:detektBaseline")
    dependsOn(":core:protodatastore:detektBaseline")
    dependsOn(":core:navigator:detektBaseline")
    dependsOn(":core:analytics:detektBaseline")
    dependsOn(":core:configuration:detektBaseline")
    dependsOn(":core:extension:detektBaseline")
    dependsOn(":core:mediapicker:detektBaseline")
    dependsOn(":core:payments:detektBaseline")
}

tasks.named("preBuild") {
    dependsOn("detekt")
}
