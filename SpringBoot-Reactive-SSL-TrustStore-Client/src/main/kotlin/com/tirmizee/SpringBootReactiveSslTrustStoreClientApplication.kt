package com.tirmizee

import com.tirmizee.services.ClientService
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootReactiveSslTrustStoreClientApplication

fun main(args: Array<String>) {
	val app = runApplication<SpringBootReactiveSslTrustStoreClientApplication>(*args)
	val clientService = app.getBean(ClientService::class.java)

	runBlocking {
		val pingResponse = clientService.ping()
		println(pingResponse)
	}
}
