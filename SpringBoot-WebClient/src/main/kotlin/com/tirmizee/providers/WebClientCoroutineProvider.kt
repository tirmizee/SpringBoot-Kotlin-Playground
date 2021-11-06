package com.tirmizee.providers

import com.tirmizee.extensions.typeReference
import com.tirmizee.properties.WebClientProperty
import com.tirmizee.providers.models.GetProductResponse
import kotlinx.coroutines.reactive.awaitSingle
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WebClientCoroutineProvider (
    @Qualifier("webClient") private val webClient: WebClient,
    private val webClientProperty: WebClientProperty
) {

    suspend fun getProduct(id: Int): Result<GetProductResponse?> =
        webClient.runCatching {
            this.get()
                .uri(webClientProperty.getProductUri.replace("{id}", id.toString()))
                .headers{ webClientProperty.headers }
                .retrieve()
                .bodyToMono(GetProductResponse::class.java)
                .awaitSingle()
        }.onSuccess { response ->
            response
        }.onFailure {
            null
        }

    suspend fun listProduct(): Result<List<GetProductResponse?>?> =
        webClient.runCatching {
            this.get()
                .uri(webClientProperty.allProductUri)
                .headers{ webClientProperty.headers }
                .retrieve()
                .bodyToMono(typeReference<List<GetProductResponse>>())
                .awaitSingle()
        }.onSuccess { response ->
            response
        }.onFailure {
            null
        }

}