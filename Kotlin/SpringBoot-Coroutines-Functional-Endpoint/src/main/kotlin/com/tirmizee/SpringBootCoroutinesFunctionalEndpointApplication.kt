package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootCoroutinesFunctionalEndpointApplication

fun main(args: Array<String>) {
	runApplication<SpringBootCoroutinesFunctionalEndpointApplication>(*args)
}
