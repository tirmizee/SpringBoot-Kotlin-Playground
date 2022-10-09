package com.tirmizee.filters

import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.*

@Order(1)
@Component
class UniqueFilter : WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        exchange.attributes["requestId"] = UUID.randomUUID().toString()
        return chain.filter(exchange)
    }
}
