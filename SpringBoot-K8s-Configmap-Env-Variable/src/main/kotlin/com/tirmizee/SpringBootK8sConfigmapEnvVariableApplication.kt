package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootK8sConfigmapEnvVariableApplication

fun main(args: Array<String>) {
	runApplication<SpringBootK8sConfigmapEnvVariableApplication>(*args)
}
