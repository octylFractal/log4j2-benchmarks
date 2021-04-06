plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    val jmhVersion = "1.29"
    implementation("org.openjdk.jmh:jmh-core:$jmhVersion")
    annotationProcessor("org.openjdk.jmh:jmh-generator-annprocess:$jmhVersion")

    implementation(platform("org.apache.logging.log4j:log4j-bom:2.14.1"))
    implementation("org.apache.logging.log4j:log4j-api")
    implementation("org.apache.logging.log4j:log4j-core")
}
