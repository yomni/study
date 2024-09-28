plugins {
    id("org.jlleitschuh.gradle.ktlint") version "12.1.1"
    kotlin("jvm") version "2.0.20"
}

group = "io.yomni"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
    testImplementation("org.assertj:assertj-core:3.24.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.0") // JUnit 5
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}

ktlint {
    version.set("0.47.1") // Ktlint 버전 설정 (필요에 따라 최신 버전으로 업데이트)
    debug.set(false)
    android.set(false)
    outputToConsole.set(true)
    ignoreFailures.set(false) // 오류가 발생하면 빌드 실패
}

// build 작업에 Ktlint 포맷 작업 추가
tasks.build {
    dependsOn(tasks.ktlintFormat) // 빌드 시 자동으로 포맷 작업 수행
}
