package com.tirmizee

import com.tirmizee.stream.StreamChannels
import com.tirmizee.stream.model.NotificationPayload
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.integration.support.MessageBuilder
import java.util.*

@SpringBootApplication
class SpringBootCloudKafkaHeaderApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootCloudKafkaHeaderApplication>(*args)
	val streamChannels = application.getBean(StreamChannels::class.java)
	streamChannels.notificationProducer().send(MessageBuilder.withPayload(NotificationPayload("hello",
		UUID.randomUUID().toString())).build())
}
