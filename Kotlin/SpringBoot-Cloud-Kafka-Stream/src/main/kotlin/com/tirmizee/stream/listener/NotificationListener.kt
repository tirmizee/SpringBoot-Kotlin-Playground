package com.tirmizee.stream.listener

import com.tirmizee.stream.StreamChannels
import com.tirmizee.stream.model.NotificationPayload
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class NotificationListener {

    @StreamListener(StreamChannels.NOTIFICATION_INPUT)
    fun notificationConsumer(@Payload payload: NotificationPayload) {
        println(payload)
    }

}