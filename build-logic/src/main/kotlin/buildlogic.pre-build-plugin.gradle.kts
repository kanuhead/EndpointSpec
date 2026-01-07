import com.kanuhead.endpointspec.buildlogic.tasks.GenerateJsonSchemaTask

// Register the code generation task
val generateSchema = tasks.register<GenerateJsonSchemaTask>("generateSchemaModels") {
    group = "build"
    description = "Generates Kotlin models from JSON Schema"
    
    // Configure input schema - assumes standard location
    schemaFile.set(layout.projectDirectory.file("src/main/resources/schema/http-endpoint-spec.schema.json"))
    
    // Configure target package
    packageName.set("com.kanuhead.endpointspec.spec")
    
    // Configure output directory
    outputDir.set(layout.buildDirectory.dir("generated/source/schema/main/kotlin"))
}

// Add generated code to source sets so it gets compiled
// We use 'afterEvaluate' or configure it immediately if plugins are already applied
plugins.withType<org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper> {
    configure<org.gradle.api.plugins.JavaPluginExtension> {
        sourceSets.named("main") {
            java.srcDir(generateSchema.map { it.outputDir })
        }
    }
}

// Ensure compilation depends on generation
tasks.named("compileKotlin") {
    dependsOn(generateSchema)
}

// Also ensure it runs before Java compilation if present
tasks.matching { it.name == "compileJava" }.configureEach {
    dependsOn(generateSchema)
}
