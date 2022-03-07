package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootMdcLogstashApplication

fun main(args: Array<String>) {
	runApplication<SpringBootMdcLogstashApplication>(*args)
}
