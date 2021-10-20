package com.tirmizee

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import org.springframework.core.env.get

@SpringBootApplication
class SpringBootK8sSecretVariableApplication : CommandLineRunner {

	@Autowired
	lateinit var env: Environment

	@Value("\${token}")
	lateinit var token: String

	@Value("\${database.url}")
	lateinit var url: String

	@Value("\${database.username}")
	lateinit var username: String

	@Value("\${database.password}")
	lateinit var password: String

	override fun run(vararg args: String?) {

		println("token $token")
		println("url $url")
		println("username $username")
		println("password $password")

		println(env["DB_URL"])
		println(env["DB_USERNAME"])
		println(env["DB_PASSWORD"])

	}

}

fun main(args: Array<String>) {
	runApplication<SpringBootK8sSecretVariableApplication>(*args)
}
