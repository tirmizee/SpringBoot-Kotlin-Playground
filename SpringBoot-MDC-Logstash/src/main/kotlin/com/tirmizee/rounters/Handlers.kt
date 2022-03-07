package com.tirmizee.rounters

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.time.LocalDateTime
import java.util.*

@Component
class Handlers {

    companion object {
        val log: Logger = LoggerFactory.getLogger(Handlers::class.java)
    }

    suspend fun hello(serverRequest: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            serverRequest.awaitBody<HelloRequest>().let {
                log.info("Handlers hello")
                "hello ${it.name}"
            }
        )

}