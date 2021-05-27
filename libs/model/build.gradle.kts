plugins {
    kotlin("plugin.serialization")
    // which produces test fixtures
    `java-test-fixtures`
}

val coroutinesVersion: String by project
val serializationVersion: String by project
val avro4kVersion: String by project
val kamlVersion: String by project

dependencies {
    // Use Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$serializationVersion") // JSON serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-protobuf:$serializationVersion") // ProtoBuf serialization
    implementation("com.sksamuel.avro4k:avro4k-core:$avro4kVersion") // Avro serialization
    // implementation("com.charleskorn.kaml:kaml:$kamlVersion") // YAML serialization

    // Testing
    testImplementation(testFixtures(project(":libs:test")))
}
