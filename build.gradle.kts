import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
//    `kotlin-dsl`
    kotlin("multiplatform") version "1.5.31"
//    kotlin("jvm") version "1.4.31"
//    id("io.kotest") version "0.3.8"
//    id("io.kotest.multiplatform") version "5.0.0.6"
}

kotlin {
    jvm()
//    js(BOTH) {
//        browser {}
//        nodejs {}
//    }
    sourceSets {

//        val commonMain by getting {
//            dependencies {
////                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:${org.graalvm.compiler.debug.Versions.kotlinSerialization}")
//            }
//        }

        val commonTest by getting {
            dependencies {
//                mplementation("io.kotest:kotest-assertions-core-jvm:0.3.8")
//                implementation("io.kotest:kotest-framework-engine-jvm:0.3.8")
                implementation(kotlin("test"))
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
            }
        }

//        val nativeMain by creating {
//            dependsOn(commonMain)
//        }
    }

//    targets.withType<KotlinNativeTarget> {
//        val main by compilations.getting {
//            defaultSourceSet {
//                val nativeMain by sourceSets.getting
//                dependsOn(nativeMain)
//            }
//        }
//    }
}

repositories {
    mavenCentral()
    google()
    gradlePluginPortal()
}

tasks.wrapper {
    gradleVersion = "7.2"
    distributionType = Wrapper.DistributionType.BIN
}
