import org.jetbrains.intellij.platform.gradle.DependencyVersion
import org.jetbrains.intellij.platform.gradle.IntelliJPlatformType
import org.jetbrains.intellij.platform.gradle.models.ProductRelease

plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("org.jetbrains.intellij.platform") version "2.0.0-beta8"
}

group = "com.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    intellijPlatform {
        defaultRepositories()
    }
}

kotlin {
    jvmToolchain(17)
}

val pvVersion: String? = System.getenv("PLUGIN_VERIFIER_VERSION")

dependencies {
    intellijPlatform {
        intellijIdeaUltimate("2024.1")
        pluginVerifier(
            if (pvVersion != null)
                DependencyVersion.Exact(pvVersion)
            else
                DependencyVersion.Latest
        )
        instrumentationTools()

        bundledPlugin("Git4Idea")

        plugin("org.jetbrains.plugins.ruby", "241.14494.240")
    }
}

intellijPlatform {
    pluginConfiguration {
        ideaVersion {
            sinceBuild = "241"
            untilBuild = "242.*"
        }
    }

    verifyPlugin {
        ides {
            recommended()
        }

        freeArgs = listOf("-mute", "ForbiddenPluginIdPrefix")
    }
}
