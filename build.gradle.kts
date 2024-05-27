plugins {
    kotlin("jvm") version "2.0.0"
    id("xyz.jpenilla.run-velocity") version "2.2.3"
    kotlin("kapt") version "1.9.23"
}

group = "com.github.arcticaquila.aquamultiinstance"
version = "1.0.0"

repositories {
    mavenCentral()
    // PaperMC repository
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.9.23")

    // Dependency for Velocity API
    compileOnly("com.velocitypowered:velocity-api:4.0.0-SNAPSHOT")
    kapt("com.velocitypowered:velocity-api:4.0.0-SNAPSHOT")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(17)
}

// Configure Jar task to make FatJar
tasks.withType<Jar> {

    // Declare Main Class attributes in the manifest
    manifest {
        attributes["Main-Class"] = "com.github.arcticaquila.aquamultiinstance.VelocityPluginKt"
    }

    // Exclude duplicate entries from the JAR
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE

    // Include files from the main source set's output directory
    from(sourceSets.main.get().output)

    // Depend on the runtime classpath configuration
    dependsOn(configurations.runtimeClasspath)

    // Include JAR files from the runtime classpath configuration
    from({
        configurations.runtimeClasspath.get().filter {
            it.name.endsWith("jar")
        }.map {
            zipTree(it)
        }
    })
}

tasks {
    runVelocity {
        velocityVersion("3.3.0-SNAPSHOT")
        dependsOn("cleanVelocityPluginsCache")
        dependsOn ("jar")
    }
}
