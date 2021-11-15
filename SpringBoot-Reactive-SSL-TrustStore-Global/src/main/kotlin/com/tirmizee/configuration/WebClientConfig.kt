package com.tirmizee.configuration

import com.tirmizee.properties.WebClientProperty
import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.InsecureTrustManagerFactory
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.tcp.SslProvider.SslContextSpec
import java.security.KeyStore
import java.security.Provider
import java.security.Security
import javax.crypto.Mac
import javax.net.ssl.TrustManagerFactory


@Configuration
class WebClientConfig(
    private val webClientProperty: WebClientProperty
) {

    companion object {
        public val log = LoggerFactory.getLogger(WebClientConfig::class.java)
    }

    @Bean(name = ["webClient"])
    fun webClient() : WebClient {
        return WebClient.builder().build()
    }

}