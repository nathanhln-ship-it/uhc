plugins {
  id("uhc.java-library-conventions")
  alias(libs.plugins.shadow)
}

dependencies {
  implementation(project(":api"))
  annotationProcessor(libs.lombok)
  compileOnly(libs.packetevents.spigot)
  compileOnly(libs.spigot)
  compileOnly("net.luckperms:api:5.4")
}

tasks {
  withType<JavaCompile> {
    options.encoding = "UTF-8"
  }

  processResources {
    filteringCharset = "UTF-8"
  }

  shadowJar {
    archiveFileName.set("${rootProject.name}-${project.name}.jar")
  }
}
