plugins {
    id("com.android.application")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    kotlin("android")
}

android {
    namespace = "com.bubblego.app.android"
    compileSdk = 33
    defaultConfig {
        applicationId = "com.bubblego.app.android"
        minSdk = 26
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.0"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    implementation(project(":shared"))
    implementation("androidx.compose.ui:ui:1.3.3")
    implementation("androidx.compose.ui:ui-tooling:1.3.3")
    implementation("androidx.compose.ui:ui-tooling-preview:1.3.3")
    implementation("androidx.compose.foundation:foundation:1.3.1")
    implementation("androidx.compose.material:material:1.3.1")
    implementation("androidx.activity:activity-compose:1.6.1")
    implementation("androidx.appcompat:appcompat:1.6.0")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1")
    implementation("androidx.navigation:navigation-compose:2.5.3")
    implementation("com.google.maps.android:maps-compose:2.9.1")
    implementation("com.google.android.gms:play-services-maps:18.1.0")
    implementation("com.google.pay.button:compose-pay-button:0.1.0-beta02")
    implementation("com.airbnb.android:lottie:6.0.0")
    implementation("com.airbnb.android:lottie-compose:6.0.0")
}