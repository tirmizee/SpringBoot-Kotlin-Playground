package com.tirmizee.stream.listener

import com.tirmizee.stream.StreamChannels
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CustomerListener {

    @StreamListener(StreamChannels.CUSTOMER_INPUT_BROKER_0)
    fun customerConsumerBroker0(@Payload payload: String) {
        println(payload)
    }

    @StreamListener(StreamChannels.CUSTOMER_INPUT_BROKER_1)
    fun customerConsumerBroker1(@Payload payload: String) {
        println(payload)
    }

}