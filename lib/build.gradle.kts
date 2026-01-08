plugins {
    id("buildlogic.kotlin-library-conventions")
    alias(libs.plugins.kotlin.serialization)
}


dependencies {
    implementation(libs.kotlinx.serialization.json)
    api(libs.swagger.core)
    api(libs.swagger.parser)
    api(libs.swagger.models)
    api(libs.arrow.core)
}
