package com.tirmizee

import com.tirmizee.services.ClientService
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import java.io.File




@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootReactiveSslTrustStoreApplication

fun main(args: Array<String>) {
	val app = runApplication<SpringBootReactiveSslTrustStoreApplication>(*args)
	val clientService = app.getBean(ClientService::class.java)
	runBlocking {
		val pingResponse = clientService.ping()
		println(pingResponse)
	}
}
