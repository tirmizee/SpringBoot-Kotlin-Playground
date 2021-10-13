package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootRedisReactiveTemplateApplication

fun main(args: Array<String>) {
	runApplication<SpringBootRedisReactiveTemplateApplication>(*args)
}
