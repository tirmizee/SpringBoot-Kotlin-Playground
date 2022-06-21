package com.tirmizee.property

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("message.mapping")
data class ErrorMessageProperty(val errors: Map<String, String>)