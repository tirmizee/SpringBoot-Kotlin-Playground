package com.tirmizee.stream

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel

interface StreamChannels {

    companion object {
        const val CUSTOMER_INPUT_BROKER_0 = "customerConsumer0"
        const val CUSTOMER_OUTPUT_BROKER_0 = "customerProducer0"
        const val CUSTOMER_INPUT_BROKER_1 = "customerConsumer1"
        const val CUSTOMER_OUTPUT_BROKER_1 = "customerProducer1"
    }

    @Input(CUSTOMER_INPUT_BROKER_0)
    fun customerConsumerBroker0(): SubscribableChannel

    @Output(CUSTOMER_OUTPUT_BROKER_0)
    fun customerProducerBroker0(): MessageChannel

    @Input(CUSTOMER_INPUT_BROKER_1)
    fun customerConsumerBroker1(): SubscribableChannel

    @Output(CUSTOMER_OUTPUT_BROKER_1)
    fun customerProducerBroker1(): MessageChannel

}