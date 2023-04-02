package com.tirmizee.configs

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration


@Configuration
class WebClientConfig {

    @Bean(name = ["webClient"])
    fun webClient(): WebClient {
        val provider = ConnectionProvider
            .builder("custom")
            .maxConnections(500)
            .maxLifeTime(Duration.ofMinutes(15))
            .maxIdleTime(Duration.ofMinutes(10))
            .build()
        val httpClient = HttpClient.create(provider)
        val httpConnector = ReactorClientHttpConnector(httpClient)
        return WebClient
            .builder()
            .clientConnector(httpConnector)
            .build()
    }
}
