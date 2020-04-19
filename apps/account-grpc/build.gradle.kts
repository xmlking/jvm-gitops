val kotlinCoroutinesVersion: String by project
val guavaVersion: String by project
val grpcVersion: String by project
val grpcKotlinVersion: String by project
val protobufVersion: String by project

dependencies {
    implementation(project(":libs:proto"))

    // Grpc
    implementation("io.grpc:grpc-netty-shaded:$grpcVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-stub:$grpcVersion")
    implementation("io.grpc:grpc-kotlin-stub:$grpcKotlinVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    // Protobuf
    implementation("com.google.protobuf:protobuf-java:$protobufVersion")
    implementation("com.google.protobuf:protobuf-java-util:$protobufVersion")

    // Google
    implementation("com.google.guava:guava:$guavaVersion")
}

application {
    mainClassName = "micro.apps.account.AccountServerKt"
    //    applicationDefaultJvmArgs = listOf("-noverify", "-XX:TieredStopAtLevel=1")
}

jib {
    containerizingMode = "packaged"
    container {
        // since we have many main classes, it cannot be Inferred. lets help
        mainClass = application.mainClassName
    }
//    extraDirectories {
//        setPaths("src/main/custom-extra-dir,/home/user/jib-extras")
//        permissions = mapOf("/work" to "775", "/distr" to "775")
//    }
}

/**
startScripts {
    enabled = false
}

task echoClient(type: CreateStartScripts) {
    mainClassName = 'micro.apps.echo.EchoClientKt'
    applicationName = 'echo-client'
    outputDir = startScripts.outputDir
    classpath = startScripts.classpath
}

task echoServer(type: CreateStartScripts) {
    mainClassName = 'micro.apps.echo.EchoServerKt'
    applicationName = 'echo-server'
    outputDir = startScripts.outputDir
    classpath = startScripts.classpath
}

task accountClient(type: CreateStartScripts) {
    mainClassName = 'micro.apps.account.AccountClientKt'
    applicationName = 'account-client'
    outputDir = startScripts.outputDir
    classpath = startScripts.classpath
}

task accountServer(type: CreateStartScripts) {
    mainClassName = 'micro.apps.account.AccountServerKt'
    applicationName = 'account-server'
    outputDir = startScripts.outputDir
    classpath = startScripts.classpath
}

applicationDistribution.into('bin') {
    from(echoClient)
    from(echoServer)
    from(accountClient)
    from(accountServer)
    fileMode = 0755
}

 **/