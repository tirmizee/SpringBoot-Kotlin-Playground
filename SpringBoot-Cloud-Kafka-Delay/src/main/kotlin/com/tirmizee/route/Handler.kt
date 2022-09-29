package com.tirmizee.route

import com.tirmizee.stream.StreamChannels
import org.springframework.integration.support.MessageBuilder.withPayload
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import java.util.*

@Component
class Handler(
    private val streamChannels: StreamChannels
) {

    suspend fun produce(serverRequest: ServerRequest): ServerResponse {
        val payload = "hello world broker"
        val messageHeader = MessageHeaders(Collections.singletonMap<String, Any>("X-Kafka-Retry", 0))
        val message = MessageBuilder.createMessage(payload, messageHeader)
        streamChannels.customerProducer().send(message)
        return ok().bodyValueAndAwait("success")
    }
}
