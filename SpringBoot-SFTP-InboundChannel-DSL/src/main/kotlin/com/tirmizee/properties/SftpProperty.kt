package com.tirmizee.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "sftp")
data class SftpProperty(
    val client: ClientProperty,
    val inbound : Inbound
) {

    data class Inbound(
        val remoteDirectory: String,
        val remoteFilesFilter: String,
        val remoteFilesDelete: Boolean,
        val localDirectory: String,
        val localDirectoryAutoCreate: Boolean
    )

    data class ClientProperty(
        val host: String,
        val port: Int,
        val username: String,
        val password: String,
        val timeOut: Int,
        val allowUnknowKey: Boolean,
        val sharedSession: Boolean,
        val cache: CacheProperty
    ) {
        data class CacheProperty(
            val poolSize: Int,
            val testSession: Boolean,
            val sessionWaitTimeout: Long
        )
    }

}