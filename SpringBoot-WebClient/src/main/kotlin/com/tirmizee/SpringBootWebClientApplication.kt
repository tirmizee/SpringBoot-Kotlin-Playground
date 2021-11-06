package com.tirmizee

import com.tirmizee.providers.WebClientCoroutineProvider
import com.tirmizee.providers.WebClientFluxProvider
import kotlinx.coroutines.runBlocking
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootWebClientApplication

fun main(args: Array<String>) {
	val application = runApplication<SpringBootWebClientApplication>(*args)
	val webClientFlux = application.getBean(WebClientFluxProvider::class.java)
	val webClientCoroutine = application.getBean(WebClientCoroutineProvider::class.java)

	val product = webClientFlux.getProduct(12)
	val products = webClientFlux.listProduct()
	println(product.getOrNull())
	println(products.getOrNull())

	runBlocking {
		val product = webClientCoroutine.getProduct(12)
		val products = webClientCoroutine.listProduct()
		println(product.getOrNull())
		println(products.getOrNull())
	}
}
