package com.tirmizee

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import org.springframework.core.env.get

@SpringBootApplication
class SpringBootEnvironmentVariableApplication : CommandLineRunner {

	@Autowired
	lateinit var env: Environment

	@Value("\${test.url}")
	lateinit var url: String

	@Value("\${server.port}")
	lateinit var serverPort: String

	@Value("\${test.service-ip}")
	lateinit var serviceIp: String

	@Value("\${test.service-port}")
	lateinit var servicePort: String

	@Value("\${test.protocol}")
	lateinit var protocol: String

	@Value("\${test.service-address}")
	lateinit var serviceAddress: String

	override fun run(vararg args: String?) {

		println("serverPort $serverPort")
		println("servicePort $servicePort")
		println("serviceIp $serviceIp")
		println("protocol $protocol")
		println("serviceAddress $serviceAddress")
		println("url $url")

		println(env["SERVICE_PORT"])
		println(env["SERVICE_IP"])
		println(env["PROTOCOL"])
		println(env["SERVICE_ADDRESS"])

	}

}

fun main(args: Array<String>) {
	runApplication<SpringBootEnvironmentVariableApplication>(*args)
}
