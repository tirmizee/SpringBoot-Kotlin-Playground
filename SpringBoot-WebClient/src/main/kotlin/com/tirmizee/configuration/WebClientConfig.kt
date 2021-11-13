package com.tirmizee.configuration

import com.tirmizee.properties.WebClientProperty
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.*

@Configuration
class WebClientConfig(
    private val webClientProperty: WebClientProperty
) {

    companion object {
        private val log = LoggerFactory.getLogger(WebClientConfig::class.java)
    }

    @Bean(name = ["webClient"])
    fun webClient() : WebClient {
        return WebClient.builder().baseUrl(webClientProperty.baseUrl).build()
    }

}