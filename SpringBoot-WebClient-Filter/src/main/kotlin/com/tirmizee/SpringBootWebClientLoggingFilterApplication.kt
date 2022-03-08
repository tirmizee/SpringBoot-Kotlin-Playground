package com.tirmizee

import com.tirmizee.providers.WebClientFluxProvider
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootWebClientLoggingFilterApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootWebClientLoggingFilterApplication>(*args)

	val webClientFlux = application.getBean(WebClientFluxProvider::class.java)

	val product = webClientFlux.getProduct(12)
	println("process...")
	println(product.getOrNull())
}
