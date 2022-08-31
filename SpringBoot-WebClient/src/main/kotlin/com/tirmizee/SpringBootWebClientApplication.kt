package com.tirmizee

import com.tirmizee.providers.WebClientCoroutineProvider
import com.tirmizee.providers.WebClientFluxProvider
import com.tirmizee.providers.models.CreateProductRequest
import kotlinx.coroutines.runBlocking
import org.apache.tika.Tika
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
//	println(product.getOrNull())

	val products = webClientFlux.listProduct()
//	println(products.getOrNull())

	val createProductRequest = CreateProductRequest(11,"IOS",true)
	val createProductResponse = webClientFlux.createProduct(createProductRequest)
//	println(createProductResponse)

	runBlocking {

		val bytes = webClientCoroutine.getBytes("https://www.orimi.com/pdf-test.pdf")
		val bytes2 = webClientCoroutine.getBytes("https://gist.githubusercontent.com/tirmizee/ad9726d317ee70c89a4efe347bc901b7/raw/866e87d763f0004322b5fd719524db0e95790257/RedisAdapter.java")
		val tika = Tika()
		println(tika.detect("https://www.orimi.com/pdf-test.pdf"))
		println(tika.detect(bytes!!))
		println(tika.detect(bytes2!!))
	}

}
