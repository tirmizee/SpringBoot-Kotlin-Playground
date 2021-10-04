package com.tirmizee

import com.tirmizee.stream.StreamChannels
import com.tirmizee.stream.model.NotificationPayload
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import java.util.*

@SpringBootApplication
class SpringBootCloudKafkaHeaderApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootCloudKafkaHeaderApplication>(*args)
	val streamChannels = application.getBean(StreamChannels::class.java)

	val payload = NotificationPayload("hello", UUID.randomUUID().toString())
	val messageHeader = MessageHeaders(Collections.singletonMap<String, Any>("CUSTOM_HEADER", "CUSTOM_HEADER_VALUE"))
	val message = MessageBuilder.createMessage(payload, messageHeader)
	streamChannels.notificationProducer().send(message)
}
