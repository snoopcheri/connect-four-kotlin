import org.gradle.api.JavaVersion.VERSION_11
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.70"
    application
}

group = "name.sargon"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
}

application {
    mainClassName = "name.sargon.connect4.MainKt"
    applicationDefaultJvmArgs += "-ea"
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = VERSION_11.majorVersion
            freeCompilerArgs = freeCompilerArgs + "-Xinline-classes" + "-Xopt-in=kotlin.ExperimentalStdlibApi,kotlin.time.ExperimentalTime"
        }
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = VERSION_11.majorVersion
    }
}
