// The server side of api/openapi.yaml. A separate deployable: the app reaches it over HTTP.
plugins {
    kotlin("plugin.serialization")
    application
}

val ktor = "3.5.2"

dependencies {
    implementation("io.ktor:ktor-server-core:$ktor")
    implementation("io.ktor:ktor-server-netty:$ktor")
    implementation("io.ktor:ktor-server-content-negotiation:$ktor")
    implementation("io.ktor:ktor-serialization-kotlinx-json:$ktor")
    implementation("io.ktor:ktor-server-status-pages:$ktor")
    runtimeOnly("ch.qos.logback:logback-classic:1.6.6")

    testImplementation("io.ktor:ktor-server-test-host:$ktor")
}

application {
    mainClass = "dev.sand.host.MainKt"
}
