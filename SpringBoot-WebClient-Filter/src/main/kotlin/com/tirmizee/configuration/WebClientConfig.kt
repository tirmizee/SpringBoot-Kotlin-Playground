package com.tirmizee.configuration

import com.tirmizee.properties.WebClientProperty
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.*
import reactor.core.publisher.Mono


@Configuration
class WebClientConfig(
    private val webClientProperty: WebClientProperty
) {

    companion object {
        public val log = LoggerFactory.getLogger(WebClientConfig::class.java)
    }

    @Bean(name = ["webClient"])
    fun webClient() : WebClient {

        return WebClient.builder()
            .baseUrl(webClientProperty.baseUrl)
            .filter(ExchangeFilterFunctions.basicAuthentication("user", "password"))
            .filter(logRequestFilter())
            .build()
    }

    fun logRequestFilter() =ExchangeFilterFunction.ofRequestProcessor{ request ->
        var builder = StringBuilder("Request: \n")
        request.headers().forEach { name, values ->
            builder
                .append(name + " : " + StringUtils.join(values, " "))
                .append("\n")
        }
        log.info(builder.toString())
        Mono.just(request)
    }

}