package com.tirmizee.stream

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel

interface StreamChannels {

    companion object {
        const val CUSTOMER_INPUT = "customerConsumer"
        const val CUSTOMER_OUTPUT = "customerProducer"
        const val NOTIFICATION_INPUT = "notificationConsumer"
        const val NOTIFICATION_OUT = "notificationProducer"
    }

    @Input(CUSTOMER_INPUT)
    fun customerConsumer(): SubscribableChannel

    @Output(CUSTOMER_OUTPUT)
    fun customerProducer(): MessageChannel

    @Input(NOTIFICATION_INPUT)
    fun notificationConsumer(): SubscribableChannel

    @Output(NOTIFICATION_OUT)
    fun notificationProducer(): MessageChannel

}