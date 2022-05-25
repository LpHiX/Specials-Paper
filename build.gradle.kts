plugins {
    java
    `maven-publish`
    idea
    eclipse
    id("io.papermc.paperweight.userdev") version "1.3.6"
}

repositories {
    mavenCentral()
    maven {
        url = uri("https://oss.sonatype.org/content/groups/public/")
    }
    maven {
        url = uri("https://papermc.io/repo/repository/maven-public/")
    }
}

dependencies {
    compileOnly(libs.paper)
    paperDevBundle("1.18.2-R0.1-SNAPSHOT")
}

java.toolchain.languageVersion.set(JavaLanguageVersion.of(17))

group = "me.lphix"
version = "1.0-SNAPSHOT"
description = "Specials"

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks {

    assemble {
        dependsOn(reobfJar)
    }

    withType<JavaCompile> {
        options.encoding = "UTF-8"
    }

    withType<ProcessResources> {
        filesMatching("plugin.yml") {
            duplicatesStrategy = DuplicatesStrategy.INCLUDE
            expand("version" to project.version)
        }
    }

}
