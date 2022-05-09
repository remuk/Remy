include 'backbone'

include ':remy'
include ':frontbone'


plugins {
    id("com.google.cloud.tools.jib") version "3.2.0" apply false
    id("de.fayard.refreshVersions") version "0.40.1"
}

refreshVersions {
    versionsPropertiesFile = File("$rootDir/backend/versions.properties")
}

rootProject.name = "Remy"

includeBuild("gradle/scripts")

rootDir.walkTopDown()
    .maxDepth(3)
    .filter { it.isDirectory && File(it, "build.gradle.kts").exists() }
    .filterNot { it == rootDir || it.name == "scripts" }
    .map { it.relativeTo(rootDir).path.replace('/', ':') }
    .forEach { include(it) }

