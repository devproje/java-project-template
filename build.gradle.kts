plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"

    application
}

group = "net.projecttl"
version = "1.0.0"

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(17))
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
}

tasks {
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"

        options.encoding = "UTF-8"
    }

    shadowJar {
        archiveBaseName.set(project.name)
        archiveClassifier.set("")
        archiveVersion.set("")

        manifest {
            attributes["Main-Class"] = "net.projecttl.example.Main"
        }
    }

    getByName<Test>("test") {
        useJUnitPlatform()
    }
}

application {
    mainClass.set("net.projecttl.example.Main")
}
