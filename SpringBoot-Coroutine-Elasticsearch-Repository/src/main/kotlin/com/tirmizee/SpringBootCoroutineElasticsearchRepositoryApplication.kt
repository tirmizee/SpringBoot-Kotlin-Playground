package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootCoroutineElasticsearchRepositoryApplication

fun main(args: Array<String>) {
	runApplication<SpringBootCoroutineElasticsearchRepositoryApplication>(*args)
}
