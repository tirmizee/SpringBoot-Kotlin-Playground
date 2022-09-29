package com.tirmizee.stream

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel

interface StreamChannels {

    companion object {
        const val CUSTOMER_CONSUMER = "customerConsumer"
        const val CUSTOMER_PRODUCER = "customerProducer"
    }

    @Input(CUSTOMER_CONSUMER)
    fun customerConsumer(): SubscribableChannel

    @Output(CUSTOMER_PRODUCER)
    fun customerProducer(): MessageChannel
}
