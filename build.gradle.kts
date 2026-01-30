@file:Suppress("PropertyName")

plugins {
    id("cc.dvitski.gradle.fabric") version "1.0.0"
}

version = "1.0.1"
group = "cc.dvitski"

fabricDsl {
    modId.set("nexus")
    minecraftVersion.set("1.20.1")
    loaderVersion.set("0.18.4")
    fabricApiVersion.set("0.92.7+1.20.1")
    kotlinVersion.set("2.2.21")
    fabricKotlinVersion.set("1.13.7")
}
