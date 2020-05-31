plugins {
    kotlin("plugin.allopen")
    id("io.quarkus")
}
val quarkusPlatformGroupId: String by project
val quarkusPlatformVersion: String by project
val quarkusPlatformArtifactId: String by project
val restAssuredVersion: String by project
val slf4jVersion: String by project

dependencies {
    implementation(project(":libs:core"))
    implementation(project(":libs:model"))
    // kotlin
    implementation(enforcedPlatform("$quarkusPlatformGroupId:$quarkusPlatformArtifactId:$quarkusPlatformVersion"))
    implementation("io.quarkus:quarkus-kotlin")
    // rest
    implementation("io.quarkus:quarkus-resteasy")
    implementation("io.quarkus:quarkus-resteasy-jsonb")
    // tooling
    implementation("io.quarkus:quarkus-smallrye-health")
    implementation("io.quarkus:quarkus-smallrye-metrics")
    implementation("io.quarkus:quarkus-smallrye-openapi")
    // deployment FIXME
    implementation("io.quarkus:quarkus-container-image-jib")
    implementation("io.quarkus:quarkus-kubernetes")
    // testing
    testImplementation("io.quarkus:quarkus-junit5")
    testImplementation("io.rest-assured:kotlin-extensions")
}

quarkus {
    setOutputDirectory("$projectDir/build/classes/kotlin/main")
}

allOpen {
    // NOTE: add all classes' annotations that need need hot-reload
    annotation("javax.ws.rs.Path")
    annotation("javax.enterprise.context.ApplicationScoped")
    annotation("io.quarkus.test.junit.QuarkusTest")
}

tasks {
    test {
        systemProperty("java.util.logging.manager", "org.jboss.logmanager.LogManager")
    }

    quarkusDev {
        setSourceDir("$projectDir/src/main/kotlin")
    }

    quarkusBuild {
        isUberJar = true
    }

    buildNative {
        // isEnableHttpUrlHandler = true
        // isEnableHttpsUrlHandler = true
        // dockerBuild = "true"
    }
}