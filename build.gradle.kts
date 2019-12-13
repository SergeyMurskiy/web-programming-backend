import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.0.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.11"
    kotlin("plugin.spring") version "1.3.11"
}

repositories {
    mavenCentral()
}

group = "com.web-programming"
version = "1.0.2"
java.sourceCompatibility = JavaVersion.VERSION_1_8


repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8"))
    compile("org.jetbrains.kotlin:kotlin-stdlib:1.3.11")
    compile("org.springframework.data:spring-data-elasticsearch:3.0.8.RELEASE")
    compile("org.springframework.boot:spring-boot-starter-web:2.1.9.RELEASE")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
    compile("org.elasticsearch.client:transport:5.6.3")
    compile("org.elasticsearch:elasticsearch:5.6.3")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
