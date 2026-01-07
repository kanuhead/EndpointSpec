package com.kanuhead.endpointspec.buildlogic.tasks

import net.pwall.json.schema.codegen.CodeGenerator
import org.gradle.api.DefaultTask
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import java.io.File

abstract class GenerateJsonSchemaTask : DefaultTask() {

    @get:InputFile
    abstract val schemaFile: RegularFileProperty

    @get:Input
    abstract val packageName: Property<String>

    @get:OutputDirectory
    abstract val outputDir: DirectoryProperty

    @TaskAction
    fun generate() {
        // Clean output directory first to remove stale files
        val outDir = outputDir.get().asFile
        if (outDir.exists()) {
            outDir.deleteRecursively()
        }
        outDir.mkdirs()

        val generator = CodeGenerator()
        generator.basePackageName = packageName.get()
        generator.baseDirectoryName = outDir.absolutePath
        
        // Generate the code
        generator.generate(schemaFile.get().asFile)
        
        logger.lifecycle("Generated Kotlin models from schema to ${outDir.absolutePath}")
    }
}
