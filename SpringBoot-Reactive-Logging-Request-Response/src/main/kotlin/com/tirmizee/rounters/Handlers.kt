package com.tirmizee.rounters

import com.tirmizee.models.PaymentRequest
import com.tirmizee.models.PaymentResponse
import com.tirmizee.models.PingResponse
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.*
import java.time.LocalDateTime
import java.util.*

@Component
class Handlers {

    suspend fun ping(serverRequest: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            PingResponse(statusCode = "0000", statusDesc = "SUCCESS")
        )

    suspend fun payment(serverRequest: ServerRequest) =
        ServerResponse.ok().json().bodyValueAndAwait(
            serverRequest.awaitBody<PaymentRequest>().let {
                PaymentResponse(statusCode = "0000", statusDesc = "SUCCESS")
            }
        )

}