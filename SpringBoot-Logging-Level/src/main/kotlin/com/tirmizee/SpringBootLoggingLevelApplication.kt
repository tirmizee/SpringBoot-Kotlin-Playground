package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootLoggingLevelApplication

fun main(args: Array<String>) {
	runApplication<SpringBootLoggingLevelApplication>(*args)
}
