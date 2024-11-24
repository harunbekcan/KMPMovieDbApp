plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    kotlin("plugin.serialization") version "1.8.0"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            api(libs.koin.core)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        // AndroidMain sourceSet
        val androidMain = sourceSets.getByName("androidMain")
        androidMain.dependencies {
            implementation(libs.ktor.client.android)
            api(libs.koin.android)
        }

        val iosX64Main = sourceSets.getByName("iosX64Main")
        val iosArm64Main = sourceSets.getByName("iosArm64Main")
        val iosSimulatorArm64Main = sourceSets.getByName("iosSimulatorArm64Main")

       sourceSets.create("iosMain") {
            dependencies {
                implementation(libs.ktor.client.darwin)
            }
            dependsOn(sourceSets["commonMain"])
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }

        val iosX64Test = sourceSets.getByName("iosX64Test")
        val iosArm64Test = sourceSets.getByName("iosArm64Test")
        val iosSimulatorArm64Test = sourceSets.getByName("iosSimulatorArm64Test")

        sourceSets.create("iosTest") {
            dependsOn(sourceSets["commonTest"])
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}


android {
    namespace = "com.harunbekcan.kmpmoviedbapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
