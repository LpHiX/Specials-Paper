plugins {
    java
    `maven-publish`
    idea
    eclipse
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
