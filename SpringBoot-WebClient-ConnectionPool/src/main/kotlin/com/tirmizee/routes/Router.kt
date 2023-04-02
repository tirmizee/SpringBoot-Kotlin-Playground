package com.tirmizee.routes

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Router {

    @Bean
    fun route(handler: Handler) = coRouter {
        "".nest {
            GET("/test", handler::call)
        }
    }
}