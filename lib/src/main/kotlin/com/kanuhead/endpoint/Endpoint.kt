package com.kanuhead.endpoint

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import io.swagger.parser.OpenAPIParser
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.parser.core.models.ParseOptions

class Endpoint {
    fun load(location: String): Either<Throwable, OpenAPI> {
        return try {
            val options = ParseOptions().apply {
                isResolve = true
                isResolveFully = true
                isResolveCombinators
            }

            val result = OpenAPIParser().readLocation(location, null, options)
            if (!result.messages.isEmpty()) {
                IllegalStateException("Unable to load OpenAPI spec from $location: ${result.messages}").left()
            } else {
                result.openAPI?.right()
                    ?: IllegalStateException("Unable to load OpenAPI spec from $location").left()
            }
        } catch (e: Throwable) {
            e.left()
        }
    }
}