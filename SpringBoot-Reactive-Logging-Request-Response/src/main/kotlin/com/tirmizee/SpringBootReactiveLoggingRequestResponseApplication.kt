package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.server.reactive.ServerHttpRequestDecorator

@SpringBootApplication
class SpringBootReactiveLoggingRequestResponseApplication

fun main(args: Array<String>) {
	runApplication<SpringBootReactiveLoggingRequestResponseApplication>(*args)
}
