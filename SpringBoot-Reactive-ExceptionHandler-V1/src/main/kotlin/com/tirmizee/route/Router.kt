package com.tirmizee.route

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Router {

    @Bean
    fun reconcile(handler: Handler) = coRouter {
        accept(MediaType.APPLICATION_JSON).nest {
            "".nest {
                GET("/v1", handler::exceptionV1)
                GET("/v2", handler::exceptionV2)
            }
        }
    }

}