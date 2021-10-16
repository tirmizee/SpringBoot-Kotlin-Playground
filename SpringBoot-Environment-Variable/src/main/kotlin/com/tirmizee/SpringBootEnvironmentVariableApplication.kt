package com.tirmizee

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootEnvironmentVariableApplication : CommandLineRunner {

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

	}

}

fun main(args: Array<String>) {
	runApplication<SpringBootEnvironmentVariableApplication>(*args)
}
