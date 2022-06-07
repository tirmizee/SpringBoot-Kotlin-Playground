package com.tirmizee.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.tirmizee.exception.data.BusinessException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
@Order(-2)
class GlobalExceptionHandler(
    private val jacksonMapper: ObjectMapper
): ErrorWebExceptionHandler {

    companion object {
        val log: Logger = LoggerFactory.getLogger(javaClass)
    }

    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        log.info(ex.javaClass.simpleName)
        return when (ex) {
            is BusinessException ->
                renderResponseJson(exchange.response, ExceptionResponse(ex.code,ex.message), HttpStatus.OK)
            else ->
                renderResponseJson(exchange.response, ExceptionResponse("999","server error"), HttpStatus.OK)
        }
    }

    private fun renderResponseJson(
        serverResponse: ServerHttpResponse,
        data: Any,
        status: HttpStatus
    ): Mono<Void> {
        serverResponse.statusCode = status
        serverResponse.headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val bodyBytes = jacksonMapper.writeValueAsBytes(data)
        val bodyBuffer = serverResponse.bufferFactory().wrap(bodyBytes)
        return serverResponse.writeWith(Mono.just(bodyBuffer))
    }

}