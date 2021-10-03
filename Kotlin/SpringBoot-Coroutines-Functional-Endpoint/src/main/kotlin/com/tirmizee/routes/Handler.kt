package com.tirmizee.routes

import com.tirmizee.routes.models.HelloResponse
import org.springframework.http.server.ServerHttpRequest
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.util.*

@Component
class Handler {

    suspend fun findAll(serverRequest: ServerRequest) : ServerResponse {
        val list = listOf(
            HelloResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
            HelloResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
            HelloResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
            HelloResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
            HelloResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString()),
            HelloResponse(UUID.randomUUID().toString(), UUID.randomUUID().toString())
        )
        return ok().bodyValueAndAwait(list)
    }

}