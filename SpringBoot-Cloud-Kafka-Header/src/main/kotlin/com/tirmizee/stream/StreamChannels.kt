package com.tirmizee.stream

import org.springframework.cloud.stream.annotation.Input
import org.springframework.cloud.stream.annotation.Output
import org.springframework.messaging.MessageChannel
import org.springframework.messaging.SubscribableChannel

interface StreamChannels {

    companion object {
        const val NOTIFICATION_INPUT = "notificationConsumer"
        const val NOTIFICATION_OUT = "notificationProducer"
    }

    @Input(NOTIFICATION_INPUT)
    fun notificationConsumer(): SubscribableChannel

    @Output(NOTIFICATION_OUT)
    fun notificationProducer(): MessageChannel

}