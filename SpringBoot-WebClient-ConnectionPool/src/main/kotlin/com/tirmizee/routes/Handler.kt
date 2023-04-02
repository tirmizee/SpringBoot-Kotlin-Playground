package com.tirmizee.routes

import com.tirmizee.services.ClientService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class Handler(val clientService: ClientService) {

    suspend fun call(request: ServerRequest): ServerResponse {
        return ServerResponse.ok().bodyValueAndAwait(clientService.getData() ?: "error")
    }
}