pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven {
            url=uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven {
            url=uri("https://oss.sonatype.org/content/repositories/snapshots/")
        }
    }
}

rootProject.name = "BubbleGo"
include(":androidApp")
include(":shared")