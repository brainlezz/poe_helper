import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}
group = "me.user"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}
dependencies {
    testImplementation(kotlin("test-junit5"))

    //retrofit requisites retro+gson
    implementation( "com.squareup.retrofit2:retrofit:2.5.0")
    implementation( "com.squareup.retrofit2:converter-gson:2.5.0")
    implementation( "no.tornado:tornadofx:1.7.20")
}
tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}