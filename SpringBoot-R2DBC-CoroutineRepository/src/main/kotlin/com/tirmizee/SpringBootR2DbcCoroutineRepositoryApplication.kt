package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootR2DbcCoroutineRepositoryApplication

fun main(args: Array<String>) {
	runApplication<SpringBootR2DbcCoroutineRepositoryApplication>(*args)
}
