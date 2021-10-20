package com.tirmizee

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootK8sConfigmapEnvVariableApplication: CommandLineRunner {

	@Value("\${database.postgres-db}")
	lateinit var postgresDb: String

	@Value("\${database.postgres-user}")
	lateinit var postgresUser: String

	@Value("\${database.pddata}")
	lateinit var pddata: String

	@Value("\${database.expire}")
	lateinit var expire: String

	override fun run(vararg args: String?) {
		println("postgresDb $postgresDb")
		println("postgresUser $postgresUser")
		println("pddata $pddata")
		println("expire $expire")
	}

}

fun main(args: Array<String>) {
	runApplication<SpringBootK8sConfigmapEnvVariableApplication>(*args)
}
