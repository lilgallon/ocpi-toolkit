plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        named<MavenPublication>("maven") {
            artifactId = "ocpi-annotation-processor"
            groupId = "com.izivia"

            from(components["java"])

            pom {
                name.set("OCPI Annotation processor")
                artifactId = "ocpi-annotation-processor"
                description.set(
                    "This module processes annotations during compilation time in order to generate some code " +
                        "(partial representation, etc ...)"
                )
            }
        }
    }
}

dependencies {
    implementation("com.squareup:kotlinpoet:${Versions.kotlinPoet}")
    implementation("com.squareup:kotlinpoet-ksp:${Versions.kotlinPoet}")
    implementation("com.google.devtools.ksp:symbol-processing-api:${Versions.ksp}")
    implementation(project(":common"))
}

tasks.build {
    dependsOn(tasks.publishToMavenLocal)
}
