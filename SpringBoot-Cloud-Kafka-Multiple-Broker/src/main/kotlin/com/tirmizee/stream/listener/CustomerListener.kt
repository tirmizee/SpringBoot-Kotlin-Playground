package com.tirmizee.stream.listener

import com.tirmizee.stream.StreamChannels
import org.springframework.cloud.stream.annotation.StreamListener
import org.springframework.kafka.support.KafkaHeaders.*
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Component

@Component
class CustomerListener {

    @StreamListener(StreamChannels.CUSTOMER_INPUT_BROKER_0)
    fun customerConsumerBroker0(
        @Payload payload:String,
        @Header(OFFSET) offset:String,
        @Header(RECEIVED_PARTITION_ID) partitionId:String,
        @Header(RECEIVED_TOPIC) topic:String
    ) {
        println("$payload $offset $partitionId $topic")
    }
    @StreamListener(StreamChannels.CUSTOMER_INPUT_BROKER_1)
    fun customerConsumerBroker1(
        @Payload payload: String,
        @Header(OFFSET) offset:String,
        @Header(RECEIVED_PARTITION_ID) partitionId:String,
        @Header(RECEIVED_TOPIC) topic:String
    ) {
        println("$payload $offset $partitionId $topic")
    }

}