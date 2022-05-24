package com.tirmizee

import com.tirmizee.properties.FieldServerProperty
import com.tirmizee.properties.ServerProperty
import com.tirmizee.properties.WebClientProperty
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootPropertyBindingApplication

fun main(args: Array<String>) {

	val application = runApplication<SpringBootPropertyBindingApplication>(*args)
	val serverProperty = application.getBean(ServerProperty::class.java);
	val webClientProperty = application.getBean(WebClientProperty::class.java);
	val fieldServerProperty = application.getBean(FieldServerProperty::class.java);

//	println(serverProperty)
//	println(serverProperty.config)
//	println(serverProperty.user)
//	println(serverProperty.compression.mimeTypes)
//
//	println(webClientProperty)
//
//	println(webClientProperty.gateway["gateway-dev"]?.url)
//	println(webClientProperty.gateway["gateway-dev"]?.headers?.get("x-api-key"))
//
//	println(webClientProperty.gateway["gateway-uat"]?.url)
//	println(webClientProperty.gateway["gateway-uat"]?.headers?.get("x-api-key"))
//
//	println(webClientProperty.endpoint.getProfile)
//
//	println(webClientProperty.endpoint.inquiry)
//	println(webClientProperty.endpoint.inquiry.path)
//	println(webClientProperty.endpoint.inquiry.gateway)
//	println(webClientProperty.gateway[webClientProperty.endpoint.inquiry.gateway])
//	println(webClientProperty.endpoint.inquiry.headers)
//
//	println(webClientProperty.endpoint.getProfile)
//	println(webClientProperty.endpoint.getProfile.path)
//	println(webClientProperty.endpoint.getProfile.gateway)
//	println(webClientProperty.gateway[webClientProperty.endpoint.getProfile.gateway])
//	println(webClientProperty.endpoint.getProfile.headers)

	println(fieldServerProperty.port)
	println(fieldServerProperty.address)
	println(fieldServerProperty.user)
	println(fieldServerProperty.compression)

}
