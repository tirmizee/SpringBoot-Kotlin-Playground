package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootReactiveRedisTemplateApplication

fun main(args: Array<String>) {
	runApplication<SpringBootReactiveRedisTemplateApplication>(*args)
}
