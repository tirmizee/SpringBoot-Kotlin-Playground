package com.tirmizee

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class SpringBootSftpInboundChannelApplication

fun main(args: Array<String>) {
	runApplication<SpringBootSftpInboundChannelApplication>(*args)
}
