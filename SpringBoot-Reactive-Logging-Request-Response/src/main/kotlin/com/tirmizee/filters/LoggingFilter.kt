package com.tirmizee.filters

import org.reactivestreams.Publisher
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.annotation.Order
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpRequestDecorator
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.http.server.reactive.ServerHttpResponseDecorator
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.ServerWebExchangeDecorator
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.charset.StandardCharsets

@Order(2)
@Component
class LoggingFilter : WebFilter {

    companion object {
        val log: Logger = LoggerFactory.getLogger(LoggingFilter::class.java)
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {

        val time = System.currentTimeMillis()
        val requestId = exchange.attributes["requestId"] as String
        val contentLength = exchange.request.headers.contentLength

        if(contentLength < 0) {
            log.info("Request $requestId : method=${exchange.request.method}, uri=${exchange.request.path}")
        }

        return chain.filter(RequestResponseExchange(exchange, requestId, time))
    }

}

class RequestResponseExchange(
    exchange: ServerWebExchange,
    private val requestId: String,
    private val time: Long
): ServerWebExchangeDecorator(exchange) {

    override fun getRequest(): ServerHttpRequest = RequestLoggingDecorator(super.getRequest(), requestId)
    override fun getResponse(): ServerHttpResponse = ResponseLoggingDecorator(super.getResponse(), requestId, time)

}

class RequestLoggingDecorator(
    delegate: ServerHttpRequest,
    private val requestId: String
) : ServerHttpRequestDecorator(delegate) {

    override fun getBody(): Flux<DataBuffer> =
        super.getBody().doOnNext{ buffer ->
            val body = StandardCharsets.UTF_8.decode(buffer.asByteBuffer()).toString()
            LoggingFilter.log.info("Request $requestId : method=${delegate.method}, uri=${delegate.path}, payload=$body")
        }

}

class ResponseLoggingDecorator(
    delegate: ServerHttpResponse,
    private val requestId: String,
    private val time: Long
) : ServerHttpResponseDecorator(delegate) {

    override fun writeWith(body: Publisher<out DataBuffer>): Mono<Void> {
        val buffer = Flux.from(body).doOnNext { dataBuffer ->
            val bodyBuffer = StandardCharsets.UTF_8.decode(dataBuffer.asByteBuffer()).toString()
            LoggingFilter.log.info("Response $requestId : status=${delegate.statusCode}, time=${System.currentTimeMillis()- time}, payload=$bodyBuffer")
        }
        return super.writeWith(buffer)
    }

}

