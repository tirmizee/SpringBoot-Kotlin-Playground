package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootCloudKafkaDelayApplication

fun main(args: Array<String>) {
	runApplication<SpringBootCloudKafkaDelayApplication>(*args)
}
