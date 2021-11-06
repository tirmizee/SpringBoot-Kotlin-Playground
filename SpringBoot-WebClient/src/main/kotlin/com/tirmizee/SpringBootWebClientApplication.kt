package com.tirmizee

import com.tirmizee.providers.WebClientCoroutineProvider
import com.tirmizee.providers.WebClientFluxProvider
import com.tirmizee.providers.models.CreateProductRequest
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
	println(product.getOrNull())

	val products = webClientFlux.listProduct()
	println(products.getOrNull())

	val createProductRequest = CreateProductRequest(11,"IOS",true)
	val createProductResponse = webClientFlux.createProduct(createProductRequest)
	println(createProductResponse)

	runBlocking {

		val product = webClientCoroutine.getProduct(12)
		println(product.getOrNull())

		val products = webClientCoroutine.listProduct()
		println(products.getOrNull())

		val createProductRequest = CreateProductRequest(11,"IOS",true)
		val createProductResponse = webClientCoroutine.createProduct(createProductRequest)
		println(createProductResponse)

	}

}
