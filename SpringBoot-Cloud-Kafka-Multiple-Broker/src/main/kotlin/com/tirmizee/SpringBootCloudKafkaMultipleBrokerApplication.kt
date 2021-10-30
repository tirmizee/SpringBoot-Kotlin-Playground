package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootCloudKafkaMultipleBrokerApplication

fun main(args: Array<String>) {
	runApplication<SpringBootCloudKafkaMultipleBrokerApplication>(*args)
}
