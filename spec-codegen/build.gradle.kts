plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("buildlogic.kotlin-library-conventions")
}

gradlePlugin {
    plugins {
        create("specCodegen") {
            id = "com.kanuhead.codegen"
            implementationClass = "com.kanuhead.codegen.SpecCodegenPlugin"
        }
    }
}
