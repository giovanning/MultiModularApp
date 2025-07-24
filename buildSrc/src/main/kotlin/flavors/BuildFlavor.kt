package flavors

import build.BuildDimensions
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.LibraryProductFlavor
import org.gradle.api.NamedDomainObjectContainer

sealed class BuildFlavor(val name: String) {

    //usado para app
    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor

    //usado para modulos
    abstract fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor

    object Google : BuildFlavor(FlavorTypes.GOOGLE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.STORE
                applicationIdSuffix = ".$name"
                versionName = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {

            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.STORE
            }
        }
    }

    object Huawei : BuildFlavor(FlavorTypes.HUAWEI) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.STORE
                applicationIdSuffix = ".$name"
                versionName = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {

            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.STORE
            }
        }
    }

    object Driver : BuildFlavor(FlavorTypes.DRIVER) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.APP
                applicationIdSuffix = ".$name"
                versionName = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.APP
            }
        }
    }

    object Client : BuildFlavor(FlavorTypes.CLIENT) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<ApplicationProductFlavor>): ApplicationProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.APP
                applicationIdSuffix = ".$name"
                versionName = "-$name"
            }
        }

        override fun createLibrary(namedDomainObjectContainer: NamedDomainObjectContainer<LibraryProductFlavor>): LibraryProductFlavor {
            return namedDomainObjectContainer.create(name) {
                dimension = BuildDimensions.APP
            }
        }
    }
}