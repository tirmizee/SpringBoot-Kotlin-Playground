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
class CleanLoggingFilter : WebFilter {

    companion object {
        val log: Logger = LoggerFactory.getLogger(CleanLoggingFilter::class.java)
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val time = System.currentTimeMillis()
        val requestId = exchange.attributes["requestId"] as String

        val requestResponseExchange = CaptureRequestResponseExchange(exchange)

        val method = exchange.request.method
        val path = exchange.request.path
        val requestBody = requestResponseExchange.request.bodyBuffer
        val responseBody = requestResponseExchange.response.bodyBuffer
        val status = requestResponseExchange.response.statusCode

        return chain.filter(requestResponseExchange)
            .doOnSuccess {
                log.info("Request $requestId : method=$method, uri=$path, payload=$requestBody")
                log.info("Response $requestId : status=$status, method=$method, uri=$path, time=${System.currentTimeMillis() - time}, payload=$responseBody")
            }.doOnError {
                log.info("Request $requestId : method=$method, uri=$path, payload=$requestBody")
                log.info("Response $requestId : status=$status, method=$method, uri=$path, time=${System.currentTimeMillis() - time}, payload=$responseBody, exception=${it.message}")
            }
    }
}

class CaptureRequestResponseExchange(exchange: ServerWebExchange) : ServerWebExchangeDecorator(exchange) {

    override fun getRequest(): CaptureRequestDecorator = CaptureRequestDecorator(super.getRequest())
    override fun getResponse(): CaptureResponseDecorator = CaptureResponseDecorator(super.getResponse())
}

class CaptureRequestDecorator(delegate: ServerHttpRequest) : ServerHttpRequestDecorator(delegate) {

    val bodyBuffer = StringBuilder()

    override fun getBody(): Flux<DataBuffer> {
        return super.getBody().doOnNext(::capture)
    }

    private fun capture(buffer: DataBuffer) {
        bodyBuffer.append(StandardCharsets.UTF_8.decode(buffer.asByteBuffer().asReadOnlyBuffer()).toString())
    }
}

class CaptureResponseDecorator(delegate: ServerHttpResponse) : ServerHttpResponseDecorator(delegate) {

    val bodyBuffer = StringBuilder()

    override fun writeWith(body: Publisher<out DataBuffer>): Mono<Void> {
        return super.writeWith(Flux.from(body).doOnNext(::capture))
    }

    private fun capture(buffer: DataBuffer) {
        bodyBuffer.append(StandardCharsets.UTF_8.decode(buffer.asByteBuffer().asReadOnlyBuffer()).toString())
    }
}
