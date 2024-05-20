plugins {
    java
    jacoco
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("com.google.cloud.tools.jib") version "3.2.0"
    id("org.sonarqube") version "4.4.1.3373"
}

sonar {
    properties {
        property("sonar.projectKey", "advpro-2024-a13_Food-Groceries-Balance-KeranjangBelanja-HistoryRating")
        property("sonar.organization", "advpro-2024-a13")
        property("sonar.host.url", "https://sonarcloud.io")
    }
}

group = "heymart.backend"
version = "0.0.1-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

dependencies {
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.springframework.boot:spring-boot-starter-validation")
    annotationProcessor("org.projectlombok:lombok")

    runtimeOnly("org.postgresql:postgresql")

    compileOnly("org.projectlombok:lombok")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testRuntimeOnly ("org.junit.jupiter:junit-jupiter-engine:5.9.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.1")
    testImplementation("org.junit.platform:junit-platform-runner:1.10.2")

    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.cloud:spring-cloud-gcp-starter-sql-postgresql:1.2.8.RELEASE")
    implementation("com.google.guava:guava:32.0.0-android")
    implementation("com.google.oauth-client:google-oauth-client:1.33.3")
    implementation("com.google.protobuf:protobuf-java:3.21.7")
    implementation("org.springframework.cloud:spring-cloud-gcp-starter:1.1.1.RELEASE")
    implementation("javax.annotation:javax.annotation-api:1.3.2")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("io.jsonwebtoken:jjwt-impl:0.11.5")
    implementation("io.jsonwebtoken:jjwt-jackson:0.11.5")
    implementation(kotlin("script-runtime"))
}

tasks.register<Test>("unitTest") {
    description = "Runs unit tests."
    group = "verification"
}

tasks.withType<Test>().configureEach {
    useJUnitPlatform()
}

tasks.test {
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)

    reports {
        html.required = true
        xml.required = true
    }
}