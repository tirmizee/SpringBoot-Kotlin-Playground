package com.tirmizee.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "spring.r2dbc")
data class DatabaseProperty(
    val datasourceInitialization: Boolean,
    val datasourceSchema: String,
    val datasourceData: String
)

