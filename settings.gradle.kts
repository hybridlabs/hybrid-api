@file:Suppress("PropertyName", "LocalVariableName")

pluginManagement {
    val loom_version: String by settings
    val kotlin_version: String by settings


    repositories {
        maven {
            name = "Fabric"
            url = uri("https://maven.fabricmc.net/")
        }
        gradlePluginPortal()
        mavenCentral()
    }

    plugins {
        id("fabric-loom") version loom_version
        id("org.jetbrains.kotlin.jvm") version kotlin_version
    }
}

val mod_id: String by settings
rootProject.name = mod_id
