package com.kanuhead.codegen

import org.gradle.api.Plugin
import org.gradle.api.Project

class SpecCodegenPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        val projectName = project.name
        project.tasks.register("generateSpec") {
            doLast {
                println("Generating spec for $projectName...")
            }
        }
    }
}
