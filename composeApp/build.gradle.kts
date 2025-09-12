import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {

        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)

            // Ktor client dependency required for Coil
            implementation(libs.ktor.client.android)

            // Ktor client dependency required for YouTube-dl Boom
            implementation("io.github.farimarwat:youtubedl-boom:1.0.21")
            implementation("io.github.farimarwat:youtubedl-boom-commons:1.2")
            implementation("com.jakewharton.timber:timber:5.0.1") // Replace with the latest Timber version
        }

        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.uiToolingPreview)
            implementation(libs.androidx.lifecycle.viewmodel)
            implementation(libs.androidx.lifecycle.runtimeCompose)

            implementation(compose.components.resources)
            implementation(libs.coil.compose)
            implementation(libs.coil.network.ktor)
            // commonMain timber
            implementation("co.touchlab:kermit:2.0.4")  // or latest version
        }

        iosMain.dependencies {
            // Ktor client dependency required for iOS
            implementation(libs.ktor.client.darwin)
        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        // Android Unit Tests (JUnit 5)
        val androidUnitTest by getting {
            dependencies {
                implementation("org.junit.jupiter:junit-jupiter:5.10.0")
                implementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
                implementation("org.junit.jupiter:junit-jupiter-engine:5.10.0")
                implementation("org.junit.jupiter:junit-jupiter-params:5.10.0") // For parameterized tests
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
                implementation(kotlin("test"))

                // Mockito for mocking (optional)
                implementation("org.mockito:mockito-core:5.5.0")
                implementation("org.mockito.kotlin:mockito-kotlin:5.1.0")
            }
        }

        // Android Instrumented Tests (if needed)
        val androidInstrumentedTest by getting {
            dependencies {
                implementation("androidx.test.ext:junit:1.1.5")
                implementation("androidx.test:runner:1.5.2")
                implementation("androidx.test:rules:1.5.0")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.0")
            }
        }

        // alternatively jvmMain (maybe later)
//        desktopMain.dependencies {
//            // Ktor client dependency required for JVM/Desktop
//            implementation(libs.ktor.client.java)
//            implementation(libs.kotlinx.coroutines.swing)
//        }
    }
}

android {
    namespace = "org.free.youtube"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    defaultConfig {
        applicationId = "org.free.youtube"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"

        // Test runner for instrumented tests
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    // Enable JUnit 5 for Android unit tests
    testOptions {
        unitTests.all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter)
    testImplementation(libs.junit.jupiter)
    debugImplementation(compose.uiTooling)
}