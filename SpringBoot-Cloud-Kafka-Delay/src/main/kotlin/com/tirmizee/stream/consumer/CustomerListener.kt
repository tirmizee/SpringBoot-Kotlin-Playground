package com.tirmizee.stream.consumer

import com.tirmizee.stream.StreamChannels
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.kafka.support.KafkaHeaders.*
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

    @StreamListener(StreamChannels.CUSTOMER_CONSUMER)
    fun customerListener(
        message: Message<String>,
        @Header(RECEIVED_TOPIC) topic: String,
        @Header("X-Kafka-Retry") retry: Int
    ) {
        runBlocking {
            if (retry == 0) {
                println("started ${message.payload} $topic $retry")
                println("ended ${message.payload} $topic $retry")
            } else if (retry <= 5) {
                delay(5000)
                println("started ${message.payload} $topic $retry")
                try {
                    throw NullPointerException()
                } catch (e: Exception) {
                    val payload = "hello world broker"
                    val messageHeader = MessageHeaders(Collections.singletonMap<String, Any>("X-Kafka-Retry", retry + 1))
                    val message = MessageBuilder.createMessage(payload, messageHeader)
                    streamChannels.customerProducer().send(message)
                }
            }
        }
    }
}
