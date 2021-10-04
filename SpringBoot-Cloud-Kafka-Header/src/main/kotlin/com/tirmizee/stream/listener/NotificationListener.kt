package com.tirmizee.stream.listener

import com.tirmizee.stream.StreamChannels
import com.tirmizee.stream.model.NotificationPayload
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.Message
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class NotificationListener {

    @StreamListener(StreamChannels.NOTIFICATION_INPUT)
    fun notificationConsumer( message: Message<NotificationPayload>) {
        val payload = message.payload
        val header = message.headers["CUSTOM_HEADER"]
        println("$payload $header")
    }

}