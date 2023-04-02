package com.tirmizee.services

import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient

@Service
class ClientService(val webClient: WebClient) {

    suspend fun getData(): String? {
        return webClient
            .get()
            .uri("https://www.google.com")
            .retrieve()
            .bodyToMono(String::class.java)
            .awaitSingleOrNull()
    }
}
