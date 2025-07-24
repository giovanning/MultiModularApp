package signing

import com.android.build.api.dsl.ApkSigningConfig
import extensions.getLocalProperty
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project
import java.io.File

sealed class BuildSigning(val name: String) {

    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>)

    class Release(private val project: Project): BuildSigning(SigningTypes.RELEASE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.create(name) {
                storeFile = File(project.getLocalProperty("release_key.store"))
                storePassword = project.getLocalProperty("release_store.password")
                keyAlias = project.getLocalProperty("release_key.alias")
                keyPassword = project.getLocalProperty("release_key.password")
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    class RealeaseExternalQA(private val project: Project): BuildSigning(SigningTypes.RELEASE_EXTERNAL_QA) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.create(name) {
                storeFile = File(project.getLocalProperty("qa_key.store"))
                storePassword = project.getLocalProperty("qa_store.password")
                keyAlias = project.getLocalProperty("qa_key.alias")
                keyPassword = project.getLocalProperty("qa_key.password")
                enableV1Signing = true
                enableV2Signing = true
            }
        }
    }

    class Debug(val project: Project): BuildSigning(SigningTypes.DEBUG) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.getByName(name) {
             /*   storeFile = File("path/debug/myKeyStore.jks")
                storePassword = "debug_store_password"
                keyAlias = "debug_key_alias"
                keyPassword = "debug_key_password"
                enableV1Signing = true
                enableV2Signing = true */
            }
        }
    }


}