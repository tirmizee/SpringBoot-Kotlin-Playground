package com.tirmizee.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "server")
class FieldServerProperty {

    var port: Int? = null
    var address: String? = null
    var user: Map<String, CredentialProperty>? = null
    var compression: ServerProperty.CompressionProperty? = null

    class CredentialProperty {
        var username: String? = null
        var password: String? = null
    }

    class CompressionProperty {
        var enabled: Boolean? = null
        var minResponseSize: String? = null
        var mimeTypes: List<String>? = null
    }

}