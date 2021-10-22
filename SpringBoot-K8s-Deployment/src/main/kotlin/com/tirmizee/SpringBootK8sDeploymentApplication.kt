package com.tirmizee

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootK8sDeploymentApplication : CommandLineRunner {
	override fun run(vararg args: String?) {
		println("MAX MEMORY ${Runtime.getRuntime().maxMemory()}")
		println("FREE MEMORY ${Runtime.getRuntime().freeMemory()}")
		println("USE MEMORY ${Runtime.getRuntime().maxMemory() - Runtime.getRuntime().freeMemory()}")
	}
}

fun main(args: Array<String>) {
	runApplication<SpringBootK8sDeploymentApplication>(*args)
}
