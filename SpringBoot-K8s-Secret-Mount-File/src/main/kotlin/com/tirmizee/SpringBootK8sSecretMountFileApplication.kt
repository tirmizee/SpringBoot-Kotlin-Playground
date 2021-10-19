package com.tirmizee

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.io.Resource
import org.springframework.core.io.ResourceLoader
import org.springframework.util.FileCopyUtils
import java.io.FileNotFoundException
import java.io.IOException


@SpringBootApplication
class SpringBootK8sSecretMountFileApplication : CommandLineRunner {

	@Value("file:/config/config-1.json")
	lateinit var resource1: Resource

	@Value("file:/config/config-2.properties")
	lateinit var resource2: Resource

	override fun run(vararg args: String?) {
		try {
			println("resource1 ${this.resource1.filename} ${this.resource1.contentLength()}")
			println("resource2 ${this.resource2.filename} ${this.resource2.contentLength()}")
		} catch (e: FileNotFoundException) {
			println("FileNotFoundException")
		}
	}
}

fun main(args: Array<String>) {
	runApplication<SpringBootK8sSecretMountFileApplication>(*args)
}
