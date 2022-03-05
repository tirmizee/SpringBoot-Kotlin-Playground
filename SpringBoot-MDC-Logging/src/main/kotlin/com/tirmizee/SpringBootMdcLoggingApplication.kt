package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootMdcLoggingApplication

fun main(args: Array<String>) {
	runApplication<SpringBootMdcLoggingApplication>(*args)
}
