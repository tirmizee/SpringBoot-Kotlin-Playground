package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootElasticsearchReactiveRepositoryApplication

fun main(args: Array<String>) {
	runApplication<SpringBootElasticsearchReactiveRepositoryApplication>(*args)
}
