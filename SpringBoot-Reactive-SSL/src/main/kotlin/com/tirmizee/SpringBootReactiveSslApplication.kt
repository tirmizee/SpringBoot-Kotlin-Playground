package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootReactiveSslApplication

fun main(args: Array<String>) {
	runApplication<SpringBootReactiveSslApplication>(*args)
}
