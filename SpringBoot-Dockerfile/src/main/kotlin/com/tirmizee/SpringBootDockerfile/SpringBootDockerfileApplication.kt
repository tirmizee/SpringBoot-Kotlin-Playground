package com.tirmizee.SpringBootDockerfile

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootDockerfileApplication

fun main(args: Array<String>) {
	runApplication<SpringBootDockerfileApplication>(*args)
}
