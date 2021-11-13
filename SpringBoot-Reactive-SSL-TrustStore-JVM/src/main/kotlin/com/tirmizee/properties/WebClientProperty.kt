package com.tirmizee.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("webclient")
data class WebClientProperty(
    val ping: String,
    val payment: String
)