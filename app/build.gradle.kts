plugins {
    application
    checkstyle
    jacoco
    id("org.sonarqube") version "5.1.0.4882"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

application {
    mainClass.set("hexlet.code.App")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
    useJUnitPlatform()
    finalizedBy(tasks.jacocoTestReport)
}

tasks.jacocoTestReport {
    dependsOn(tasks.test)
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}

checkstyle {
    toolVersion = "10.3.4"
    configFile = file("config/checkstyle/checkstyle.xml")
}

sonarqube {
    properties {
        property("sonar.projectKey", "GaraevIM_java-project-78")
        property("sonar.organization", "garaevim")
        property("sonar.coverage.jacoco.xmlReportPaths", "build/reports/jacoco/test/jacocoTestReport.xml")
    }
}