import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    kotlin("jvm") version "1.8.21"

    application
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

val junit5Version = "5.9.3"

dependencies {
    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.apache.httpcomponents.client5:httpclient5:5.2.1")

    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junit5Version")
}

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            showCauses = true
            exceptionFormat = TestExceptionFormat.SHORT
            events = setOf(
                TestLogEvent.PASSED,
                TestLogEvent.FAILED,
                TestLogEvent.SKIPPED,
            )
            showExceptions = true
            afterSuite(
                KotlinClosure2({ desc: TestDescriptor, result: TestResult ->
                    if (desc.parent == null) {
                        val output = "Results: ${result.resultType} (" +
                            "${result.testCount} tests, " +
                            "${result.successfulTestCount} passed, " +
                            "${result.failedTestCount} failed, " +
                            "${result.skippedTestCount} skipped)"
                        val startItem = "| "
                        val endItem = " |"
                        val horizontalLine = "-".repeat(output.length + startItem.length + endItem.length)
                        println(
                            """
                                $horizontalLine
                                $startItem$output$endItem
                                $horizontalLine
                            """.trimIndent(),
                        )
                    }
                }),
            )
        }
    }
}
