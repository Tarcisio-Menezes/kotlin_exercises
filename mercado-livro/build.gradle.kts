import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.6.6"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.jmailen.kotlinter") version "3.4.0"
	kotlin("jvm") version "1.6.10"
	kotlin("plugin.spring") version "1.6.10"
	kotlin("plugin.jpa") version "1.4.21" // adicionado para funcionar o getById
}

group = "com.mercadolivro"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
	mavenCentral()
	maven("https://packages.confluent.io/maven/")
}

val mockkVersion = "1.12.3"
val kotlinTestVersion = "1.4.31"
val swaggerVersion = "1.6.7"
val springMockkVersion = "3.1.1"
val wiremockVersion = "2.33.0"



dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("com.ninja-squad:springmockk:$springMockkVersion")
	testImplementation("io.mockk:mockk:$mockkVersion")
	testImplementation("com.github.tomakehurst:wiremock-jre8:$wiremockVersion") {
		exclude(group = "commons-io", module = "commons-io")
	}
	testImplementation("org.jetbrains.kotlin:kotlin-test-junit:$kotlinTestVersion")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	runtimeOnly( "mysql:mysql-connector-java")
	implementation("org.flywaydb:flyway-core:7.7.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
