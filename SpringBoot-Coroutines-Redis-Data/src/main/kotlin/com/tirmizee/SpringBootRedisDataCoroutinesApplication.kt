package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootRedisDataCoroutinesApplication

fun main(args: Array<String>) {
	runApplication<SpringBootRedisDataCoroutinesApplication>(*args)
}
