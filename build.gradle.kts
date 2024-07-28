import org.lwjgl.Lwjgl
import org.lwjgl.lwjgl

plugins {
    id("java")
    id("org.lwjgl.plugin") version "0.0.34"
}

group = "com.harleylizard"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven("https://libraries.minecraft.net")
}

dependencies {
    lwjgl {
        implementation(Lwjgl.Module.glfw, Lwjgl.Module.opengl)
        implementation(Lwjgl.Addons.`joml 1_10_5`)
    }
    implementation("com.google.code.gson:gson:2.11.0")
    implementation("it.unimi.dsi:fastutil:8.5.13")

    implementation("com.mojang:datafixerupper:6.0.8")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}