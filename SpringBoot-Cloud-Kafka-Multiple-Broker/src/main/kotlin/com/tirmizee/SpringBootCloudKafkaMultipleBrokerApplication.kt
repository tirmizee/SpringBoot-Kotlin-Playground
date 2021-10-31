package com.tirmizee

import com.tirmizee.stream.StreamChannels
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.messaging.support.MessageBuilder

@SpringBootApplication
class SpringBootCloudKafkaMultipleBrokerApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootCloudKafkaMultipleBrokerApplication>(*args)
	val streamChannels = application.getBean(StreamChannels::class.java)
	streamChannels.customerProducerBroker0().send(MessageBuilder.withPayload("hello world broker 0").build())
	streamChannels.customerProducerBroker1().send(MessageBuilder.withPayload("hello world").build())
}
