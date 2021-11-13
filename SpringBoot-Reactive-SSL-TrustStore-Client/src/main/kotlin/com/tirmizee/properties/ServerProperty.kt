package com.tirmizee.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties("server.ssl")
data class ServerProperty (
    val trustStore: String,
    val trustStorePem: String,
    val trustStorePassword: String,
    val trustStoreType: String
)