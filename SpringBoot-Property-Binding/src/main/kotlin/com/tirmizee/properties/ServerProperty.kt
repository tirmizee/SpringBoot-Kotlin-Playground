package com.tirmizee.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "server")
data class ServerProperty(
    val port: Int,
    val address: String,
    val compression: CompressionProperty,
    val config: Map<String, List<String>>,
    val user: Map<String, CredentialProperty>
) {

    data class CredentialProperty(
        val username: String,
        val password: String
    )

    data class CompressionProperty(
        val enabled: Boolean,
        val minResponseSize: String,
        val mimeTypes: List<String>
    )

}