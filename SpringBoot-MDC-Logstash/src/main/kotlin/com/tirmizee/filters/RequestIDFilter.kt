package com.tirmizee.filters

import org.slf4j.MDC
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import java.util.*

@Component
class RequestIDFilter : WebFilter {

    companion object {
        const val REQUEST_ID = "requestId"
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val uniqueId = UUID.randomUUID()
        MDC.put(REQUEST_ID, uniqueId.toString())
        exchange.response.headers.add(REQUEST_ID, uniqueId.toString())
        return chain.filter(exchange)
    }

}