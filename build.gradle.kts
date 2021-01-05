plugins {
    kotlin("jvm") version "1.4.21"
    id("com.github.johnrengelman.shadow") version "6.1.0"
}

buildscript {
    configurations.classpath {
        resolutionStrategy {
            activateDependencyLocking()
        }
    }
}

group = "me.scoretwo"
version = "0.13"

defaultTasks = mutableListOf("shadowJar")

repositories {
    mavenLocal()
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    maven("https://repo.codemc.org/repository/maven-public/")
    maven("http://repo.iroselle.com/snapshots/")
    maven("https://repo.maven.apache.org/maven2")
}

dependencies {
    implementation("me.scoretwo:commons-syntaxes:2.0-SNAPSHOT")
    implementation("me.scoretwo:commons-bukkit-configuration-yaml:2.0-SNAPSHOT")
    implementation("org.apache.commons:commons-lang3:3.10")
    implementation("me.clip:placeholderapi:2.10.9")
    implementation("org.spigotmc:spigot-api:1.16.4-R0.1-SNAPSHOT")
}


tasks.withType<com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar> {
    dependencies {
        include(dependency("me.scoretwo:commons-syntaxes:2.0-SNAPSHOT"))
        include(dependency("me.scoretwo:commons-bukkit-configuration-yaml:2.0-SNAPSHOT"))
        include(dependency("org.jetbrains.kotlin:kotlin-stdlib"))
    }
    classifier = null
}

tasks.processResources {
    from("src/main/resources") {
        include("plugin.yml")
        expand(mapOf(
            "name" to project.name,
            "version" to project.version,
            "main" to "${project.group}.${project.name.toLowerCase()}.bukkit.${project.name}"
        ))
    }
}