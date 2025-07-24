import com.android.build.gradle.internal.tasks.factory.dependsOn
import org.gradle.kotlin.dsl.creating

val ktlint: org.gradle.api.artifacts.Configuration by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.49.0") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

tasks.register<JavaExec>("ktlintFormat") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Verificar código em Kotlin e formatar"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
    args(
        "-F",
        "**.kts",
        "!**/build/**",
        "**/src/**/*.kt"
    )
}

tasks {
    named("preBuild").dependsOn("ktlintFormat")
}
