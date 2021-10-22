package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootK8sDeploymentApplication

fun main(args: Array<String>) {
	runApplication<SpringBootK8sDeploymentApplication>(*args)
}
