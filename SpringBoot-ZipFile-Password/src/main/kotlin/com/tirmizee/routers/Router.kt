package com.tirmizee.routers

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Router {

    @Bean
    fun zipFile(handler: Handler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "".nest {
                GET("/zip", handler::zipWithPassword)
            }
        }
    }

}