package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootCloudKafkaManualAcknowledgeApplication

fun main(args: Array<String>) {
	runApplication<SpringBootCloudKafkaManualAcknowledgeApplication>(*args)
}
