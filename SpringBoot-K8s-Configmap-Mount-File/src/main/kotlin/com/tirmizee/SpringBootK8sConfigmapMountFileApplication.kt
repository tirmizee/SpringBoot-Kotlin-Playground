package com.tirmizee

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootK8sConfigmapMountFileApplication: CommandLineRunner {

	@Value("\${database.url}")
	lateinit var url: String

	@Value("\${database.username}")
	lateinit var username: String

	@Value("\${database.password}")
	lateinit var password: String

	override fun run(vararg args: String?) {
		println("url $url")
		println("username $username")
		println("password $password")
	}

}

fun main(args: Array<String>) {
	runApplication<SpringBootK8sConfigmapMountFileApplication>(*args)
}
