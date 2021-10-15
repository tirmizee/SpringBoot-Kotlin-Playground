package com.tirmizee

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringBootRedisReactiveTemplateApplication : CommandLineRunner {

	@Value("\${test.service-ip}")
	lateinit var serviceIp: String

	@Value("\${test.service-port}")
	lateinit var servicePort: String

	@Value("\${test.protocol}")
	lateinit var protocol: String

	@Value("\${test.service-address}")
	lateinit var serviceAddress: String

	override fun run(vararg args: String?) {
		println("servicePort $servicePort")
		println("serviceIp $serviceIp")
		println("protocol $protocol")
		println("serviceAddress $serviceAddress")
	}

}

fun main(args: Array<String>) {
	runApplication<SpringBootRedisReactiveTemplateApplication>(*args)
}
