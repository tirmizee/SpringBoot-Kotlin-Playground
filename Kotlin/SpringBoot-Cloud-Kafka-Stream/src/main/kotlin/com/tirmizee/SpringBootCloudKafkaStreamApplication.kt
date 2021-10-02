package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootCloudKafkaStreamApplication

fun main(args: Array<String>) {
	runApplication<SpringBootCloudKafkaStreamApplication>(*args)
}
