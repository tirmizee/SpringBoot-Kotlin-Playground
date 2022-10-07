package com.tirmizee

import com.tirmizee.stream.StreamChannels
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ApplicationContext
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.support.MessageBuilder
import java.util.*

@SpringBootApplication
class SpringBootCloudKafkaDelayApplication : CommandLineRunner {
    @Autowired
    lateinit var applicationContext: ApplicationContext
    override fun run(vararg args: String?) {
        val producer = applicationContext.getBean(StreamChannels::class.java)
        for (i in 1..500) {
            val payload = "hello world broker " + UUID.randomUUID().toString()
            val messageHeader = MessageHeaders(Collections.singletonMap<String, Any>("X-Kafka-Retry", 0))
            val message = MessageBuilder.createMessage(payload, messageHeader)
            producer.customerProducer().send(message)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<SpringBootCloudKafkaDelayApplication>(*args)
}
