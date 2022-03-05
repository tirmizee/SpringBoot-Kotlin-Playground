package com.tirmizee.filters

import com.tirmizee.utils.AESUtils
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.core.io.buffer.DataBuffer
import org.springframework.core.io.buffer.DefaultDataBufferFactory
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.http.server.reactive.ServerHttpRequestDecorator
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.ServerWebExchangeDecorator
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.nio.ByteBuffer
import java.nio.charset.StandardCharsets

@Component
class DecryptRequestFilter : WebFilter {

    companion object {
        val log: Logger = LoggerFactory.getLogger(DecryptRequestFilter::class.java)
    }

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val requestResponseExchange = DecryptRequestExchange(exchange)
        return chain.filter(requestResponseExchange)
    }

    class DecryptRequestExchange(exchange: ServerWebExchange) : ServerWebExchangeDecorator(exchange) {
        override fun getRequest(): DecryptRequestDecorator = DecryptRequestDecorator(super.getRequest())
    }

    class DecryptRequestDecorator(delegate: ServerHttpRequest) : ServerHttpRequestDecorator(delegate) {

        private var decryptBuffer : ByteArray? = null

        override fun getBody(): Flux<DataBuffer> {
            return super.getBody().flatMap {
                decryptBuffer = AESUtils.decrypt(StandardCharsets.UTF_8.decode(it.asByteBuffer()).toString())
                Flux.just(DefaultDataBufferFactory().wrap(ByteBuffer.wrap(decryptBuffer)))
            }
        }

    }

}