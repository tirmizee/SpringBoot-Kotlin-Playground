package com.tirmizee.config

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer


@Configuration
@EnableWebFlux
class WebConfig : WebFluxConfigurer {

    @Bean
    fun jacksonMapper(): ObjectMapper {
        return ObjectMapper()
    }

}
