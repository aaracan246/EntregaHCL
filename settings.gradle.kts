plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}
rootProject.name = "ObjetosJava"
include("src:main:Kotlin")
findProject(":src:main:Kotlin")?.name = "Kotlin"
