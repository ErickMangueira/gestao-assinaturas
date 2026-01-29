plugins {
	kotlin("jvm") version "2.2.21"
	kotlin("plugin.spring") version "2.2.21"
	kotlin("plugin.jpa") version "2.2.21"

	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.planify"
version = "0.0.1-SNAPSHOT"
description = "Sistema de gestão de assinaturas"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
	}
}

repositories {
	mavenCentral()
}

dependencies {

	/* Web */
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-validation")

	/* Security */
	implementation("org.springframework.boot:spring-boot-starter-security")

	/* Persistence */
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly("org.postgresql:postgresql")

	/* Flyway */
	implementation("org.flywaydb:flyway-core")
	implementation("org.flywaydb:flyway-database-postgresql")

	/* Redis */
	implementation("org.springframework.boot:spring-boot-starter-data-redis")

	/* Kotlin / Jackson */
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	/* Observability */
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	runtimeOnly("io.micrometer:micrometer-registry-prometheus")

	/* OpenAPI / Swagger */
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	/* Dev */
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")

	/* Tests */
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.testcontainers:junit-jupiter")
	testImplementation("org.testcontainers:postgresql")
	testImplementation("org.testcontainers:redis")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll(
			"-Xjsr305=strict",
			"-Xannotation-default-target=param-property"
		)
	}
}

allOpen {
	annotation("jakarta.persistence.Entity")
	annotation("jakarta.persistence.MappedSuperclass")
	annotation("jakarta.persistence.Embeddable")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
