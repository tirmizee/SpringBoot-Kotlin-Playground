package com.tirmizee.providers

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.tirmizee.extensions.typeReference
import com.tirmizee.properties.WebClientProperty
import com.tirmizee.providers.models.GetProductResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.ParameterizedTypeReference
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient


@Component
class WebClientFluxProvider(
    @Qualifier("webClient") private val webClient: WebClient,
    private val webClientProperty: WebClientProperty
) {

    fun getProduct(id: Int): Result<GetProductResponse?> =
        webClient.runCatching {
            this.get()
                .uri(webClientProperty.getProductUri.replace("{id}", id.toString()))
                .headers{ webClientProperty.headers }
                .retrieve()
                .bodyToMono(GetProductResponse::class.java)
                .block()
        }.onSuccess { response ->
            response
        }.onFailure {
            null
        }

    fun listProduct(): Result<List<GetProductResponse?>?> =
        webClient.runCatching {
            this.get()
                .uri(webClientProperty.allProductUri)
                .headers{ webClientProperty.headers }
                .retrieve()
                .bodyToMono(typeReference<List<GetProductResponse>>())
                .block()
        }.onSuccess { response ->
            response
        }.onFailure {
            null
        }


}