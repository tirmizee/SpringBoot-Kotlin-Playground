package com.tirmizee.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.http.HttpHeaders

@ConstructorBinding
@ConfigurationProperties(prefix = "web-client")
data class WebClientProperty (
    val gateway: Map<String, GatewayProperty>,
    val endpoint: EndpointProperty
) {

    data class GatewayProperty(
        val url: String,
        val headers: HttpHeaders = HttpHeaders.EMPTY
    )

    data class EndpointProperty(
        val inquiry: EndpointDetailProperty,
        val getProfile: EndpointDetailProperty,
    )

    data class EndpointDetailProperty(
        val path: String,
        val gateway: String,
        val headers: HttpHeaders = HttpHeaders.EMPTY
    )

}