package com.kanuhead.endpointspec

import com.kanuhead.endpoint.Endpoint
import java.nio.charset.StandardCharsets

fun main() {
    val specText: String = Thread.currentThread()
        .contextClassLoader
        .getResourceAsStream("openapi.yaml")
        ?.use { input -> input.readBytes().toString(StandardCharsets.UTF_8) }
        ?: error("Resource not found on classpath: openapi.yaml")

    val result = Endpoint().load("openapi.yaml")
    
    result.fold(
        { error -> 
            System.err.println("Error loading spec: ${error.message}")
            error.printStackTrace()
        },
        { 
            println("Hello World!")
        }
    )
}