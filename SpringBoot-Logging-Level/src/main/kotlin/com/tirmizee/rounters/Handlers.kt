package com.tirmizee.rounters

import com.tirmizee.services.TestLogService
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*

@Component
class Handlers(
    private val testLogService: TestLogService
) {

    suspend fun hello(serverRequest: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            serverRequest.awaitBody<HelloRequest>().let {
                testLogService.testLogLevel(it)
            }
        )

}