package com.tirmizee.rounters

import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.time.LocalDateTime
import java.util.*

@Component
class Handlers {

    suspend fun hello(serverRequest: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            serverRequest.awaitBody<HelloRequest>().let {
                "hello ${it.name}"
            }
        )

}