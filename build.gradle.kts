import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

plugins {
    kotlin("jvm") version "2.4.10" apply false
    kotlin("plugin.serialization") version "2.4.10" apply false
}

subprojects {
    group = "dev.sand"
    version = "1.0.0"

    apply(plugin = "org.jetbrains.kotlin.jvm")
    repositories { mavenCentral() }
    extensions.configure<KotlinJvmProjectExtension> { jvmToolchain(21) }

    dependencies {
        "testImplementation"(kotlin("test"))
        "testImplementation"(platform("org.junit:junit-bom:6.1.3"))
        "testRuntimeOnly"("org.junit.platform:junit-platform-launcher")
    }

    tasks.withType<Test>().configureEach { useJUnitPlatform() }
}
