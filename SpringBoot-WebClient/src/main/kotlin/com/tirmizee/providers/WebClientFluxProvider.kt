package com.tirmizee.providers

import com.fasterxml.jackson.module.kotlin.jacksonTypeRef
import com.tirmizee.extensions.typeReference
import com.tirmizee.properties.WebClientProperty
import com.tirmizee.providers.models.CreateProductRequest
import com.tirmizee.providers.models.CreateProductResponse
import com.tirmizee.providers.models.GetProductResponse
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
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
                .accept(MediaType.APPLICATION_JSON)
                .headers{ webClientProperty.headers }
                .retrieve()
                .bodyToMono(GetProductResponse::class.java)
                .block()
        }.onSuccess { response ->
            response
        }.onFailure {}

    fun listProduct(): Result<List<GetProductResponse?>?> =
        webClient.runCatching {
            this.get()
                .uri(webClientProperty.allProductUri)
                .accept(MediaType.APPLICATION_JSON)
                .headers{ webClientProperty.headers }
                .retrieve()
                .bodyToMono(typeReference<List<GetProductResponse>>())
                .block()
        }.onSuccess { response ->
            response
        }.onFailure {}

    fun createProduct(createProductRequest: CreateProductRequest): Result<CreateProductResponse?> =
        webClient.runCatching {
            this.post()
                .uri(webClientProperty.createProductUri)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers{ webClientProperty.headers }
                .bodyValue(createProductRequest)
                .retrieve()
                .bodyToMono(CreateProductResponse::class.java)
                .block()
        }.onSuccess { response ->
            response
        }.onFailure {}

}