package com.tirmizee.stream.listener

import com.tirmizee.stream.StreamChannels
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CustomerListener {

    @StreamListener(StreamChannels.CUSTOMER_INPUT)
    fun customerConsumer(@Payload payload: String) {
        println(payload)
    }

}