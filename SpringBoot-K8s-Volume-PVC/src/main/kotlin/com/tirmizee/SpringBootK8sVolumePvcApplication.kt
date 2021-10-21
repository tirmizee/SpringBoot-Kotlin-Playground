package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootK8sVolumePvcApplication

fun main(args: Array<String>) {
	runApplication<SpringBootK8sVolumePvcApplication>(*args)
}
