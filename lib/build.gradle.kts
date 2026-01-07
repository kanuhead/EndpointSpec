plugins {
    id("buildlogic.kotlin-library-conventions")
    alias(libs.plugins.kotlin.serialization)
    id("buildlogic.pre-build-plugin")
}


dependencies {
    implementation(libs.kotlinx.serialization.json)
}
