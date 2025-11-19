@file:Suppress("PropertyName")

plugins {
    id("fabric-loom")
    id("maven-publish")
    id("org.jetbrains.kotlin.jvm")
}

val maven_group: String by properties
val mod_id: String by properties
val mod_version: String by properties
val minecraft_version: String by properties
val loader_version: String by properties
val fabric_version: String by properties
val kotlin_version: String by properties
val fabric_kotlin_version: String by properties
val geckolib_version: String by properties
val mod_menu_version: String by properties

version = "$mod_version+$minecraft_version"
group = maven_group

base {
    archivesName.set(mod_id)
}

repositories {
    maven("https://maven.terraformersmc.com/releases/")
}

loom {
    accessWidenerPath = file("src/main/resources/$mod_id.accesswidener")
    splitEnvironmentSourceSets()

    mods {
        create(mod_id) {
            sourceSet(sourceSets["main"])
            sourceSet(sourceSets["client"])
        }
    }

}

fabricApi {
    configureDataGeneration {
        client = true
    }
}

dependencies {
    minecraft("com.mojang:minecraft:$minecraft_version")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:$loader_version")

    modImplementation("net.fabricmc.fabric-api:fabric-api:$fabric_version")
    modImplementation("net.fabricmc:fabric-language-kotlin:$fabric_kotlin_version+kotlin.$kotlin_version")

    modRuntimeOnly("com.terraformersmc:modmenu:$mod_menu_version")
}

tasks.processResources {
    inputs.property("version", project.version)
    filesMatching("fabric.mod.json") {
        expand(mapOf("version" to project.version))
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.release.set(17)
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

java {
    withSourcesJar()
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.jar {
    inputs.property("archivesName", base.archivesName)
    from("LICENSE") {
        rename { "${it}_${inputs.properties["archivesName"]}" }
    }
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = base.archivesName.get()
            from(components["java"])
        }
    }

    repositories {
        // Add publishing repositories here
    }
}
