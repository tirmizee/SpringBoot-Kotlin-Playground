package com.tirmizee.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.util.MultiValueMap

@ConstructorBinding
@ConfigurationProperties(prefix = "web-client")
data class WebClientProperty (
    val baseUrl: String,
    val headers: MultiValueMap<String, String>,
    val allProductUri: String,
    val allProductUrl: String,
    val getProductUri: String,
    val getProductUrl: String,
    val createProductUri: String,
    val createProductUrl: String,
    val updateProductUri: String,
    val updateProductUrl: String
)