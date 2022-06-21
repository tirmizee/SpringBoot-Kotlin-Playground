package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootReactiveExceptionHandlerV2Application

fun main(args: Array<String>) {
	runApplication<SpringBootReactiveExceptionHandlerV2Application>(*args)
}
