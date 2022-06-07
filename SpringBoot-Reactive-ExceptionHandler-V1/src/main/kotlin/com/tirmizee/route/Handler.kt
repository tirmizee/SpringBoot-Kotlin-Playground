package com.tirmizee.route

import com.tirmizee.exception.data.BusinessException
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait

@Component
class Handler {

    suspend fun exceptionV1(request: ServerRequest): ServerResponse {
        throw BusinessException("001", "exception")
        return ServerResponse.ok().bodyValueAndAwait("success")
    }

    suspend fun exceptionV2(request: ServerRequest): ServerResponse {
        throw NullPointerException()
        return ServerResponse.ok().bodyValueAndAwait("success")
    }

}