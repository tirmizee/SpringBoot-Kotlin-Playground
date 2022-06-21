package com.tirmizee.exception

import com.fasterxml.jackson.databind.ObjectMapper
import com.tirmizee.commons.BaseResponse
import com.tirmizee.constant.Constants
import com.tirmizee.exception.data.BusinessException
import com.tirmizee.property.ErrorMessageProperty
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.server.reactive.ServerHttpResponse
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalErrorWebExceptionHandler(
    private val jacksonMapper: ObjectMapper,
    private val errorMessageProperty: ErrorMessageProperty
): ErrorWebExceptionHandler {

    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        val response = determineMessage(ex)
        return renderResponseJson(exchange.response, response.first, response.second)
    }

    private fun determineMessage(error: Throwable): Pair<HttpStatus, BaseResponse> {
        return when(error) {
            is BusinessException ->
                Pair(HttpStatus.OK, BaseResponse(error.code, errorMessageProperty.errors[error.code]))
            else ->
                Pair(HttpStatus.OK, BaseResponse(Constants.SYSTEM_ERROR, error.message))
        }
    }

    private fun renderResponseJson(
        serverResponse: ServerHttpResponse,
        status: HttpStatus,
        data: Any
    ): Mono<Void> {
        serverResponse.statusCode = status
        serverResponse.headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        val bodyBytes = jacksonMapper.writeValueAsBytes(data)
        val bodyBuffer = serverResponse.bufferFactory().wrap(bodyBytes)
        return serverResponse.writeWith(Mono.just(bodyBuffer))
    }

}