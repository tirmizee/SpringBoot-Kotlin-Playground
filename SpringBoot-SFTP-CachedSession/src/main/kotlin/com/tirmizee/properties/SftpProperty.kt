package com.tirmizee.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "sftp.client")
data class SftpProperty(
    val host: String,
    val port: Int,
    val username: String,
    val password: String,
    val timeOut: Int,
    val allowUnknowKey: Boolean,
    val sharedSession: Boolean,
    val cache: SftpCacheProperty
) {
    data class SftpCacheProperty(
        val poolSize: Int,
        val testSession: Boolean,
        val sessionWaitTimeout: Long
    )
}