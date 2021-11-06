package com.tirmizee.configuration

import com.tirmizee.properties.WebClientProperty
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    private val webClientProperty: WebClientProperty
) {

    @Bean(name = ["webClient"])
    fun webClient() : WebClient {
        return WebClient.builder().baseUrl(webClientProperty.baseUrl).build()
    }

    @Bean
    fun webClientCustomize() : WebClient {
        return WebClient.builder().baseUrl(webClientProperty.baseUrl).build()
    }

}