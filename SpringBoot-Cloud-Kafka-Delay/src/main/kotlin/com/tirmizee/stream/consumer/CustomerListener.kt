package com.tirmizee.stream.consumer

import com.tirmizee.stream.StreamChannels
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.kafka.support.KafkaHeaders.* // ktlint-disable no-wildcard-imports
import org.springframework.messaging.Message
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component
import java.util.*

@Component
class CustomerListener(
    private val streamChannels: StreamChannels
) {

    val log: Logger = LoggerFactory.getLogger(CustomerListener::class.java);

    @StreamListener(StreamChannels.CUSTOMER_CONSUMER)
    fun customerListener(
        message: Message<String>,
        @Header(RECEIVED_TOPIC) topic: String,
        @Header("X-Kafka-Retry") retry: Int
    ) {
        GlobalScope.launch {
            if (retry == 0) {
                log.info("started ${message.payload} $topic Retry $retry")
                try {
                    throw NullPointerException()
                } catch (e: Exception) {
                    val messageHeader = MessageHeaders(Collections.singletonMap<String, Any>("X-Kafka-Retry", retry + 1))
                    val message = MessageBuilder.createMessage(message.payload, messageHeader)
                    streamChannels.customerProducer().send(message)
                }
            } else if (retry <= 5) {
                delay(5000)
                log.info("started ${message.payload} $topic Retry $retry")
                try {
                    throw NullPointerException()
                } catch (e: Exception) {
                    val messageHeader = MessageHeaders(Collections.singletonMap<String, Any>("X-Kafka-Retry", retry + 1))
                    val message = MessageBuilder.createMessage(message.payload, messageHeader)
                    streamChannels.customerProducer().send(message)
                }
            } else {
            }
        }
    }
}
