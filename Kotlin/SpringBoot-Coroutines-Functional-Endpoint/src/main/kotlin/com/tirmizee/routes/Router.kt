package com.tirmizee.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Router {

    @Bean
    fun routes1(handler: Handler) = coRouter {
        GET("/all", handler::findAll)
    }

}