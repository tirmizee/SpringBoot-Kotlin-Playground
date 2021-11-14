package com.tirmizee.rounters

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class Routers {

    @Bean
    fun customerRoutes(handlers: Handlers) =
        coRouter {
            accept(MediaType.APPLICATION_JSON).nest{
                "api".nest {
                    GET("/ping", handlers::ping)
                    POST("/payment", handlers::payment)
                }
            }
        }

}