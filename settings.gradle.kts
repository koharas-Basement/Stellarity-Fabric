pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabricmc.net/")
        maven("https://maven.architectury.dev")
        maven("https://maven.minecraftforge.net")
        maven("https://maven.neoforged.net/releases/")
        maven("https://maven.kikugie.dev/snapshots") { name = "KikuGie Snapshots" }
        maven("https://maven.kikugie.dev/releases")
        maven("https://maven.isxander.dev/releases/")
    }
}

plugins {
    id("dev.kikugie.stonecutter") version "0.8.1"
}

stonecutter {
    create(rootProject) {
        // See https://stonecutter.kikugie.dev/wiki/start/#choosing-minecraft-versions
        fun mc(ver: String, vararg loaders: String) {
            for (loader in loaders) {
                version("$loader+$ver", ver)//name, version
            }
        }

        mc("1.21.1", "fabric", "neoforge")
        mc("1.20.1", "fabric", "forge")
        vcsVersion = "fabric+1.20.1"
    }
}

rootProject.name = "Stellarity-Fabric"