package com.tirmizee

import com.tirmizee.stream.StreamChannels
import com.tirmizee.stream.model.NotificationPayload
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.messaging.support.MessageBuilder
import java.util.*

@SpringBootApplication
class SpringBootCloudKafkaStreamApplication


fun main(args: Array<String>) {
	val application = runApplication<SpringBootCloudKafkaStreamApplication>(*args)
	val streamChannels = application.getBean(StreamChannels::class.java)
	streamChannels.customerProducer().send(MessageBuilder.withPayload("hello world").build())
	streamChannels.customerProducer().send(MessageBuilder.withPayload(NotificationPayload("hello",UUID.randomUUID().toString())).build())
}
