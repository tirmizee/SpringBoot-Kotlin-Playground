package com.tirmizee.providers

import com.tirmizee.extensions.typeReference
import com.tirmizee.properties.WebClientProperty
import com.tirmizee.providers.models.CreateProductRequest
import com.tirmizee.providers.models.CreateProductResponse
import com.tirmizee.providers.models.GetProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono

@Component
class WebClientCoroutineProvider (
    @Qualifier("webClient") private val webClient: WebClient,
    private val webClientProperty: WebClientProperty
) {

    suspend fun getBytes(url: String): ByteArray? =
        withContext(Dispatchers.IO) {
            webClient.get()
                .uri(url)
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .headers{ webClientProperty.headers }
                .retrieve()
                .bodyToMono(ByteArray::class.java)
                .awaitSingleOrNull()
        }

    suspend fun getProduct(id: Int): Result<GetProductResponse?> =
        withContext(Dispatchers.IO) {
            webClient.runCatching {
                this.get()
                    .uri(webClientProperty.getProductUri.replace("{id}", id.toString()))
                    .accept(MediaType.APPLICATION_JSON)
                    .headers{ webClientProperty.headers }
                    .retrieve()
                    .bodyToMono(GetProductResponse::class.java)
                    .awaitSingle()
            }.onSuccess { response ->
                response
            }.onFailure {}
        }

    suspend fun listProduct(): Result<List<GetProductResponse?>?> =
        withContext(Dispatchers.IO) {
            webClient.runCatching {
                this.get()
                    .uri(webClientProperty.allProductUri)
                    .accept(MediaType.APPLICATION_JSON)
                    .headers{ webClientProperty.headers }
                    .retrieve()
                    .bodyToMono(typeReference<List<GetProductResponse>>())
                    .awaitSingle()
            }.onSuccess { response ->
                response
            }.onFailure {}
        }

    suspend fun createProduct(createProductRequest: CreateProductRequest): Result<CreateProductResponse?> =
        withContext(Dispatchers.IO) {
            webClient.runCatching {
                this.post()
                    .uri(webClientProperty.createProductUri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .headers{ webClientProperty.headers }
                    .bodyValue(createProductRequest)
                    .retrieve()
                    .bodyToMono(CreateProductResponse::class.java)
                    .awaitSingle()
            }.onSuccess { response ->
                response
            }.onFailure {}
        }

}
